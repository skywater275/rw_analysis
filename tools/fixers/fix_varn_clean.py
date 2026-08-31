#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
fix_varn_clean.py — Phase C varN 参数/局部变量清理器 (v19.114)

背景: game-lib.jar 全局无 LocalVariableTable (混淆类+官方类均剥离), 参数名无
T0 字节码证据。本工具用高置信语义模式推导 (证据链: 规则模式 + 上下文),
全部登记 semantic-derived-v19.114 级别 (与字节码铁证明确区分)。

规则库 (仅高置信模式, 禁止泛化猜测):
  R1 catch (Exception varN)           → e     (异常变量惯例)
  R2 for (int varN = 0; ...; varN++)  → i/j/k (循环索引, 嵌套递增)
  R3 varN = <expr>.getX()/getY()/
          getWidth()/getHeight()      → x/y/width/height (语义 getter 回传)

安全防线 (作用域替换模式):
  1. 作用域 = 声明处到所属块结束 (R1/R2) 或所属方法体结束 (R3)
  2. 新名在作用域内已存在 → 跳过 (冲突)
  3. varN 在作用域内声明次数 > 1 → 跳过 (遮蔽风险)
  4. 作用域内 \bvarN\b 全局替换 (声明+引用原子变更, 杜绝未定义变量)
  5. 证据链 CSV 持久化 (file/varN/new_name/rule/context/level)

用法: python tools/fixers/fix_varn_clean.py [--dry-run] [--apply] [--rule R1,R2,R3]
"""

import argparse
import csv
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent.parent
DEOBF = ROOT / "03-deobfuscated"
FIXES_OUT = ROOT / "mappings" / "generated" / "varn-fixes.csv"

csv.field_size_limit(10 * 1024 * 1024)


def brace_end(text: str, open_pos: int) -> int:
    """从 open_pos 处 '{' 匹配到闭合 '}'. 返回 '}' 的索引 (不含), -1 失败."""
    depth, i = 1, open_pos + 1
    while i < len(text) and depth > 0:
        if text[i] == "{":
            depth += 1
        elif text[i] == "}":
            depth -= 1
        i += 1
    return i - 1 if depth == 0 else -1


def method_end(text: str, pos: int) -> int:
    """pos 所在方法的 body 结束索引. 找 pos 前最近的方法声明 '{' → brace_end."""
    for m in re.finditer(r"\b[a-zA-Z_$][a-zA-Z0-9_$]*\s*\([^(){}]*\)\s*(?:throws\s+[\w.,\s$]+)?\{", text):
        if m.start() > pos:
            break
        if m.start() <= pos <= m.end():
            e = brace_end(text, text.find("{", m.start()))
            if e > 0:
                return e
    return -1


def scope_replace(text, start, end, varn, new_name, rule, ctx, fixes, rel):
    """作用域 [start, end) 内原子替换 varn → new_name (含声明处). 冲突/遮蔽 → 跳过."""
    scope = text[start:end]
    if re.search(r"\b" + re.escape(new_name) + r"\b", scope):
        return text  # 新名冲突
    # 字符串/字符字面量内含 varN 文本 → 跳过 (re.sub 会破坏字面量 — 编译破坏)
    if re.search(r'"[^"\n]*\b' + re.escape(varn) + r'\b[^"\n]*"', scope) or \
       re.search(r"'[^'\n]*\b" + re.escape(varn) + r"\b[^'\n]*'", scope):
        return text
    # 遮蔽判定: 只数"类型前缀声明"形态 (int varN / String varN / List<X> varN),
    # 赋值形态 (varN = x) 不计数 — v19.114 修正 (for 体内赋值误伤教训)
    decls = len(re.findall(
        r"\b(?:int|long|byte|short|float|double|boolean|char)\s+" + re.escape(varn) + r"\b"
        r"|\b[A-Z][\w.$<>\[\]]*\s+" + re.escape(varn) + r"\b", scope))
    if decls > 1:
        return text  # 遮蔽风险 (作用域内重复声明)
    if not re.search(r"\b" + varn + r"\b", scope):
        return text
    line = text[:start].count("\n") + 1
    fixes.append((rel, varn, new_name, rule, ctx + f" (line {line})"))
    return text[:start] + re.sub(r"\b" + varn + r"\b", new_name, scope) + text[end:]


def apply_rule_r1(text, fixes, rel):
    """R1: catch (Exception varN) → e."""
    matches = []
    for m in re.finditer(r"catch\s*\(([^(){}]*\b)(var\d+)(\s*[^(){}]*)\)", text):
        varn = m.group(2)
        ob = text.find("{", m.end())
        if ob < 0:
            continue
        eb = brace_end(text, ob)
        if eb < 0:
            continue
        matches.append((m.start(), eb + 1, varn, "e", "R1", f"catch块 {varn}→e"))
    for start, end, varn, new_name, rule, ctx in sorted(matches, key=lambda t: -t[0]):
        text = scope_replace(text, start, end, varn, new_name, rule, ctx, fixes, rel)
    return text


def apply_rule_r2(text, fixes, rel):
    """R2: for (int varN = 0; ...; varN++) → i/j/k (嵌套栈: 只在嵌套时递增).

    循环头分句内允许括号 (方法调用形态), 禁止分号/花括号 (分句边界).
    """
    pat = re.compile(
        r"for\s*\([^{};]*\b(int|long)\s+(var\d+)\s*=[^{};]*;[^{};]*;[^{};]*\)\s*\{")
    stack = []  # 未闭合 for 的结束索引
    matches = []
    for m in pat.finditer(text):
        varn = m.group(2)
        # 循环头验证: 第三分句含 ++varN/varN++/--varN/varN-- (前缀递增是 03 主流形态)
        # 无声明形态 for(var0 = c; ...) 不匹配 (声明在循环外, 作用域替换会破坏) — 正则已挡
        if not re.search(r"(?:\+\+|--)\s*" + varn + r"\b|\b" + varn + r"\s*(?:\+\+|--)",
                         m.group(0)):
            continue
        ob = text.find("{", m.start())
        if ob < 0:
            continue
        eb = brace_end(text, ob)
        if eb < 0:
            continue
        stack = [e for e in stack if e > m.start()]  # 弹出已结束的
        new_name = "ijk"[min(len(stack), 2)]
        stack.append(eb)
        matches.append((m.start(), eb + 1, varn, new_name, "R2",
                        f"for循环索引 {varn}→{new_name}"))
    for start, end, varn, new_name, rule, ctx in sorted(matches, key=lambda t: -t[0]):
        text = scope_replace(text, start, end, varn, new_name, rule, ctx, fixes, rel)
    return text


def apply_rule_r3(text, fixes, rel):
    """R3: varN = <expr>.getX()/getY()/getWidth()/getHeight() → 语义名."""
    table = {"getX": "x", "getY": "y", "getWidth": "width", "getHeight": "height"}
    matches = []
    for m in re.finditer(r"\b(var\d+)\s*=\s*[^;{}]*?\.(getX|getY|getWidth|getHeight)\s*\(\s*\)", text):
        varn, getter = m.group(1), m.group(2)
        new_name = table[getter]
        # 作用域: 所在方法体结束 (声明处起)
        me = method_end(text, m.start())
        if me < 0:
            continue
        line_start = text.rfind("\n", 0, m.start()) + 1
        matches.append((line_start, me, varn, new_name, "R3",
                        f"getter回传 {varn}→{new_name}"))
    for start, end, varn, new_name, rule, ctx in sorted(matches, key=lambda t: -t[0]):
        text = scope_replace(text, start, end, varn, new_name, rule, ctx, fixes, rel)
    return text


def apply_rule_r4(text, fixes, rel):
    """R4: Iterator varN = X.iterator() → it (独立声明或 for 头)."""
    matches = []
    for m in re.finditer(r"\bIterator\s+(var\d+)\s*=\s*[^;{}]*\.iterator\s*\(\s*\)", text):
        varn = m.group(1)
        # for 头形态: m 前 200 字符内有 'for' → 作用域 = 循环体; 否则方法体
        head_start = text.rfind("for", max(0, m.start() - 200), m.start())
        if head_start >= 0:
            ob = text.find("{", m.end())
            if ob >= 0:
                eb = brace_end(text, ob)
                if eb > 0:
                    matches.append((head_start, eb + 1, varn, "it", "R4",
                                    f"迭代器 {varn}→it"))
                    continue
        me = method_end(text, m.start())
        if me > 0:
            matches.append((m.start(), me, varn, "it", "R4", f"迭代器 {varn}→it"))
    for start, end, varn, new_name, rule, ctx in sorted(matches, key=lambda t: -t[0]):
        text = scope_replace(text, start, end, varn, new_name, rule, ctx, fixes, rel)
    return text


def apply_rule_r5(text, fixes, rel):
    """R5: StringBuilder varN = new StringBuilder() → sb."""
    matches = []
    for m in re.finditer(r"\bStringBuilder\s+(var\d+)\s*=\s*new\s+StringBuilder\s*\(\s*\)", text):
        varn = m.group(1)
        me = method_end(text, m.start())
        if me > 0:
            matches.append((m.start(), me, varn, "sb", "R5",
                            f"字符串构建器 {varn}→sb"))
    for start, end, varn, new_name, rule, ctx in sorted(matches, key=lambda t: -t[0]):
        text = scope_replace(text, start, end, varn, new_name, rule, ctx, fixes, rel)
    return text


RULES = {"R1": apply_rule_r1, "R2": apply_rule_r2, "R3": apply_rule_r3,
         "R4": apply_rule_r4, "R5": apply_rule_r5}


def main():
    ap = argparse.ArgumentParser(description="Phase C varN 清理器")
    ap.add_argument("--dry-run", action="store_true")
    ap.add_argument("--apply", action="store_true")
    ap.add_argument("--rule", default="R1,R2,R3",
                    help="启用规则 (逗号分隔, 默认 R1,R2,R3)")
    args = ap.parse_args()
    if not args.dry_run and not args.apply:
        ap.print_help()
        sys.exit(1)
    rule_funcs = [RULES[r.strip()] for r in args.rule.split(",") if r.strip() in RULES]

    total_fixes = []
    changed = []
    for p in sorted(DEOBF.rglob("*.java")):
        text = p.read_text(encoding="utf-8", errors="replace")
        orig = text
        fixes = []
        for rfunc in rule_funcs:
            text = rfunc(text, fixes, str(p.relative_to(DEOBF)))
        if text != orig:
            if args.apply:
                p.write_text(text, encoding="utf-8")
                changed.append(str(p.relative_to(DEOBF)))
            total_fixes.extend(fixes)
    print(f"[PhaseC] 修复处数: {len(total_fixes)}")
    print(f"[PhaseC] 涉及文件: {len(changed)}")

    if args.dry_run:
        for f in total_fixes[:30]:
            print(f"  {f[0]}: {f[1]}→{f[2]} [{f[3]}] {f[4]}")
        sys.exit(0)

    if args.apply and total_fixes:
        FIXES_OUT.parent.mkdir(parents=True, exist_ok=True)
        with open(FIXES_OUT, "w", encoding="utf-8", newline="") as f:
            w = csv.writer(f)
            w.writerow(["file", "varN", "new_name", "rule", "context", "level"])
            for f in total_fixes:
                w.writerow([f[0], f[1], f[2], f[3], f[4], "semantic-derived-v19.114"])
        print(f"[PhaseC] 证据链已写: {FIXES_OUT.relative_to(ROOT)}")


if __name__ == "__main__":
    main()
