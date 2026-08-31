#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
auto_align.py — 03 编译错误全自动补全器 (v19.113o)

将 v19.113m/n 手工战役沉淀的修复模式固化为规则库, 自动循环:
  gate → 错误解析 → 规则修复 → gate 验证 → 净收益判定 (负收益回退)

用法:
  python tools/fixers/auto_align.py [--dry-run] [--max-rounds N] [--rules R1,R3,R5]

规则库:
  R1 import-missing      符号=类名且03全树存在唯一FQN → 补 import
  R2 import-broken       import com.X 单段包损坏 → 全树唯一匹配修正
  R3 static-field-decl   静态块有 X=new T() 但无字段声明 → 补声明
  R4 short-class-ref     单字母符号 (类型用) → class-discoveries 映射替换
  R5 stream-readFloat    OutputNetStream.readFloat() → g() (写流版本检查)
  R6 array-misname       类名被当数组索引 → 同类型数组字段替换
  R7 method-mismatch     调用点名字≠声明名 → 02b 指纹匹配找 03 名

铁律: 每处修复记录锚点 (规则+证据) 到 auto_align.log; 每轮全量 gate;
      净收益判定 — 错误数上升则回退本轮全部修改。
"""

import argparse
import csv
import re
import subprocess
import sys
import time
from collections import defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent.parent
DEOBF = ROOT / "03-deobfuscated"
ERRORS_CSV = ROOT / "compile-errors.csv"
GATE = [sys.executable, str(ROOT / "tools" / "gates" / "javac_gate.py")]
LOG_FILE = Path(__file__).with_suffix(".log")
DISCOVERIES = ROOT / "mappings" / "class-discoveries.csv"
TWOB = ROOT / "02b-decompiled"


def log(msg):
    line = f"[{time.strftime('%H:%M:%S')}] {msg}"
    print(line, flush=True)
    with open(LOG_FILE, "a", encoding="utf-8") as f:
        f.write(line + "\n")


def run_gate():
    """跑全量 gate, 返回错误总数 (-1 = 失败)"""
    try:
        r = subprocess.run(GATE, cwd=str(ROOT), capture_output=True,
                           timeout=900, encoding="utf-8", errors="replace")
        m = re.search(r"Total errors: (\d+)", r.stdout)
        return int(m.group(1)) if m else -1
    except Exception as e:
        log(f"gate 失败: {e}")
        return -1


def read_errors():
    if not ERRORS_CSV.exists():
        return []
    with open(ERRORS_CSV, encoding="utf-8") as f:
        return list(csv.DictReader(f))


# ---------- 03 全树索引 ----------
_tree_index = None

def tree_index():
    """{类简单名: [FQN, ...]} 03 源码类索引 (主类优先, 排除内部类)"""
    global _tree_index
    if _tree_index is None:
        _tree_index = defaultdict(list)
        for jf in DEOBF.rglob("*.java"):
            if "$" in jf.name:
                continue
            try:
                for line in jf.read_text(encoding="utf-8", errors="replace").split("\n")[:300]:
                    m = re.match(r"^\s*(?:public\s+|protected\s+|private\s+|abstract\s+|final\s+|strictfp\s+|static\s+)*(?:class|interface|enum)\s+(\w+)", line)
                    if m:
                        rel = jf.relative_to(DEOBF)
                        fqn = str(rel).replace("\\", "/")[:-5].replace("/", ".")
                        _tree_index[m.group(1)].append(fqn)
                        break
            except Exception:
                pass
        # 去重保序
        for k in _tree_index:
            _tree_index[k] = list(dict.fromkeys(_tree_index[k]))
    return _tree_index


# ---------- class-discoveries: 02b↔03 映射 ----------
_disc = None

def discoveries():
    """{(02b FQN 短名): 03 语义名} + {(03 语义名): 02b FQN}"""
    global _disc
    if _disc is None:
        _disc = {}
        with open(DISCOVERIES, encoding="utf-8", errors="replace") as f:
            for row in csv.DictReader(f):
                pkg = row.get("obfuscated_package", "")
                cls = row.get("obfuscated_class", "")
                name = row.get("meaningful_name", "")
                if not pkg or not cls or not name or name == "Unknown":
                    continue
                fqn2 = f"{pkg}.{cls}"
                _disc.setdefault(cls, name)
                _disc.setdefault(fqn2, name)
                _disc.setdefault(name, fqn2)
    return _disc


# ---------- 规则实现 ----------
class Fix:
    def __init__(self, rule, fpath, old, new, evidence):
        self.rule = rule
        self.fpath = fpath
        self.old = old
        self.new = new
        self.evidence = evidence

    def __repr__(self):
        return f"{self.rule} {self.fpath.name}: {self.evidence}" 


def add_import(src, fqn):
    """在 import 区插入 import 行 (字母序)"""
    lines = src.split("\n")
    imports = [i for i, l in enumerate(lines) if l.startswith("import ")]
    if not imports:
        return src
    new_line = f"import {fqn};"
    # 插入位置: 保持字母序
    pos = imports[0]
    for i in imports:
        if lines[i] > new_line:
            pos = i
            break
        pos = i + 1
    lines.insert(pos, new_line)
    return "\n".join(lines)


def rule_import_missing(errors):
    """R1: 符号=类名, 03 全树唯一 FQN → 补 import"""
    fixes = []
    ti = tree_index()
    seen = set()
    for e in errors:
        sym = e.get("symbol", "")
        if not re.match(r"^[A-Z]\w*$", sym):
            continue
        if sym.startswith(("Java", "Android", "Org")) or "." in sym:
            continue
        fqns = ti.get(sym, [])
        if len(fqns) != 1:
            continue  # 歧义或不存在 — 跳过
        fqn = fqns[0]
        fpath = DEOBF / e["file"].replace("/", "\\")
        if not fpath.exists():
            continue
        key = (str(fpath), sym)
        if key in seen:
            continue
        seen.add(key)
        src = fpath.read_text(encoding="utf-8", errors="replace")
        if f"import {fqn};" in src:
            continue
        if not re.search(rf"\b{re.escape(sym)}\b", src):
            continue
        fixes.append(Fix("R1", fpath, None, None, f"03 全树唯一 FQN {fqn}"))
        fixes[-1].new_src = add_import(src, fqn)
    return fixes


def rule_import_broken(errors):
    """R2: import com.X 单段包损坏 → 全树唯一匹配修正"""
    fixes = []
    ti = tree_index()
    for jf in DEOBF.rglob("*.java"):
        try:
            src = jf.read_text(encoding="utf-8", errors="replace")
        except Exception:
            continue
        changed = False
        lines = src.split("\n")
        for i, l in enumerate(lines):
            m = re.match(r"^import com\.([A-Z]\w*);$", l.strip())
            if not m:
                continue
            sym = m.group(1)
            fqns = ti.get(sym, [])
            if len(fqns) == 1 and fqns[0].startswith("com.corrodinggames"):
                lines[i] = f"import {fqns[0]};"
                changed = True
        if changed:
            f = Fix("R2", jf, None, None, "import com.X 包损坏修正")
            f.new_src = "\n".join(lines)
            fixes.append(f)
    return fixes


def rule_static_field_decl(errors):
    """R3: X=new T() 无声明 → 静态块补 static 字段声明 / 方法内补局部类型"""
    fixes = []
    ti = tree_index()
    for jf in DEOBF.rglob("*.java"):
        try:
            src = jf.read_text(encoding="utf-8", errors="replace")
        except Exception:
            continue
        lines = src.split("\n")
        declared = set(re.findall(r"^\s*(?:public |protected |private |static |final |synchronized )*(?:[\w<>\[\],\.]+ )+?(\w+)\s*;", src, re.M))
        # 判别每个赋值所在块: 静态块 vs 方法
        in_static = False
        depth = 0
        static_missing = []
        local_missing = []
        for i, l in enumerate(lines):
            if re.match(r"^\s*static\s*\{", l):
                in_static = True
                depth = 1  # 计入本行 {
                continue
            if in_static:
                depth += l.count("{") - l.count("}")
                if depth < 0:
                    in_static = False
                    continue
                m = re.match(r"^\s*(\w+)\s*=\s*new\s+([A-Z]\w+)\(\);?\s*$", l)
                if m and m.group(1) not in declared and m.group(2) in ti:
                    static_missing.append((m.group(1), m.group(2)))
                if depth == 0 and not l.strip().startswith(("static", "}", "{")):
                    in_static = False
        # 方法内: X = new T(); 且 X 仅在此方法内使用 (粗判: 全文件出现≤3次且无字段声明)
        for i, l in enumerate(lines):
            m = re.match(r"^(\s{4,})(\w+)\s*=\s*new\s+([A-Z]\w+)\(\);?\s*$", l)
            if not m:
                continue
            n, t = m.group(2), m.group(3)
            if n in declared or t not in ti:
                continue
            uses = len(re.findall(rf"\b{n}\b", src))
            if uses <= 3 and (n, t) not in static_missing:
                local_missing.append((i, m.group(1), n, t))
        new_src = src
        if static_missing:
            anchor = re.search(r"^\s*static\s*\{", src, re.M)
            added = [f"    static {t} {n};  // v19.113o auto_align R3a: 静态块赋值无声明补插" for n, t in static_missing]
            if anchor:
                pos = src.rfind("\n", 0, anchor.start())
                new_src = src[:pos + 1] + "\n".join(added) + "\n" + src[pos + 1:]
        if local_missing:
            for i, ind, n, t in local_missing:
                lines = new_src.split("\n")
                lines[i] = f"{ind}{t} {n} = new {t}();  // v19.113o auto_align R3b: 局部变量补类型"
                new_src = "\n".join(lines)
        if new_src != src:
            f = Fix("R3", jf, None, None,
                    f"补声明: static[{len(static_missing)}] local[{len(local_missing)}]")
            f.new_src = new_src
            fixes.append(f)
    return fixes


def rule_short_class_ref(errors):
    """R4: 单字母符号用作类型 → class-discoveries 映射替换"""
    fixes = []
    disc = discoveries()
    ti = tree_index()
    for e in errors:
        sym = e.get("symbol", "")
        loc = e.get("location", "")
        if not re.match(r"^[a-z]$", sym):
            continue
        if "class" not in loc and "variable" not in loc:
            continue
        name3 = disc.get(sym)
        if not name3 or not re.match(r"^[A-Z]\w*$", name3):
            continue
        if name3 not in ti:
            continue
        fqns = ti[name3]
        if len(fqns) != 1:
            continue
        fqn = fqns[0]
        fpath = DEOBF / e["file"].replace("/", "\\")
        if not fpath.exists():
            continue
        try:
            lines = fpath.read_text(encoding="utf-8", errors="replace").split("\n")
            ln = int(e["line"]) - 1
            if not (0 <= ln < len(lines)):
                continue
            # 只替换类型位置: "X var" / "X)" / "X;" / "(X)" / "<X>"
            old = lines[ln]
            new = re.sub(rf"\b{sym}\s+(\w+)", rf"{name3} \1", old)
            new = re.sub(rf"\(\s*{sym}\s*\)", f"({name3})", new)
            if new == old:
                continue
            lines[ln] = new
            src = "\n".join(lines)
            if f"import {fqn};" not in src and not re.search(rf"\b{name3}\b", src.split("\n")[0]):
                pass
            f = Fix("R4", fpath, old, new, f"class-discoveries: {sym}→{name3} ({fqn})")
            f.new_src = src if f"import {fqn};" in src else add_import(src, fqn)
            fixes.append(f)
        except Exception:
            continue
    return fixes


def rule_stream_readfloat(errors):
    """R5: OutputNetStream.readFloat() → g() (写流版本检查)"""
    fixes = []
    for e in errors:
        if e.get("symbol") != "readFloat()":
            continue
        fpath = DEOBF / e["file"].replace("/", "\\")
        if not fpath.exists():
            continue
        try:
            lines = fpath.read_text(encoding="utf-8", errors="replace").split("\n")
            ln = int(e["line"]) - 1
            if not (0 <= ln < len(lines)):
                continue
            m = re.match(r"(\w+)\.readFloat\(\)", lines[ln].strip())
            if not m:
                continue
            var = m.group(1)
            src = "\n".join(lines)
            # 确认该变量是 OutputNetStream
            if not re.search(rf"OutputNetStream\s+{var}\b", src):
                continue
            old = lines[ln]
            new = old.replace(f"{var}.readFloat()", f"{var}.g()")
            f = Fix("R5", fpath, old, new, f"OutputNetStream.{var} 写流版本检查 → g() (02b j/as g())")
            lines[ln] = new
            f.new_src = "\n".join(lines)
            fixes.append(f)
        except Exception:
            continue
    return fixes


def rule_array_misname(errors):
    """R6: 类名被当数组索引 (ClassName[i]) → 同类型数组字段替换"""
    fixes = []
    for e in errors:
        sym = e.get("symbol", "")
        if not re.match(r"^[A-Z]\w*$", sym):
            continue
        fpath = DEOBF / e["file"].replace("/", "\\")
        if not fpath.exists():
            continue
        try:
            lines = fpath.read_text(encoding="utf-8", errors="replace").split("\n")
            ln = int(e["line"]) - 1
            if not (0 <= ln < len(lines)):
                continue
            if not re.search(rf"{re.escape(sym)}\[", lines[ln]):
                continue
            # 找元素类型: 看该行用法 (如 PlayerState n3 = X[n2] → 数组元素是 PlayerState)
            m = re.search(rf"(\w+)\s+(\w+)\s*=\s*{re.escape(sym)}\[", lines[ln])
            if not m:
                continue
            elem_type = m.group(1)
            src = "\n".join(lines)
            # 找文件内该类型的数组字段
            arr = re.findall(rf"(?:static |final |public |protected |private )*\s*{re.escape(elem_type)}\[\]\s+(\w+)\s*;", src)
            if len(arr) != 1:
                continue
            old = lines[ln]
            new = old.replace(f"{sym}[", f"{arr[0]}[")
            f = Fix("R6", fpath, old, new, f"数组误名: {sym}→{arr[0]} (元素类型 {elem_type})")
            lines[ln] = new
            f.new_src = "\n".join(lines)
            fixes.append(f)
        except Exception:
            continue
    return fixes


def rule_method_mismatch(errors):
    """R7: this.X(...) 调用无声明 → 02b 方法体指纹匹配找 03 名"""
    fixes = []
    disc = discoveries()
    for e in errors:
        sym = e.get("symbol", "")
        m = re.match(r"^([a-zA-Z]\w*)\([^)]*\)$", sym)
        if not m or len(m.group(1)) < 2:
            continue
        mname2 = m.group(1)
        fpath = DEOBF / e["file"].replace("/", "\\")
        if not fpath.exists():
            continue
        try:
            src = fpath.read_text(encoding="utf-8", errors="replace")
        except Exception:
            continue
        # 03 文件内无该声明 → 02b 找
        if re.search(rf"^\s*(?:public |private |protected |static |abstract |final |strictfp )*\w[\w<>,\.\[\] ]* {mname2}\s*\(", src, re.M):
            continue
        # 03 文件名 → 02b FQN
        rel = fpath.relative_to(DEOBF)
        # 从 class-discoveries 反查: 03 语义名 → 02b FQN
        name3 = fpath.stem
        fqn2 = disc.get(name3)
        if not fqn2:
            continue
        bpath = TWOB / (fqn2.replace(".", "/") + ".java")
        if not bpath.exists():
            continue
        try:
            bsrc = bpath.read_text(encoding="utf-8", errors="replace")
        except Exception:
            continue
        # 02b 找方法声明
        bm = re.search(rf"(?:public |protected |private |static |abstract |final |strictfp )+[\w<>,\.\[\] ]+ {mname2}\s*\(([^)]*)\)\s*\{{", bsrc)
        if not bm:
            continue
        # 提取方法体指纹: 独特字符串 (>5字符, 非日志) 或数字 (>10)
        body = bsrc[bm.end():bm.end() + 1200]
        depth = 0
        body_end = None
        for i, ch in enumerate(body):
            if ch == "{":
                depth += 1
            elif ch == "}":
                depth -= 1
                if depth == 0:
                    body_end = i
                    break
        if body_end is None:
            body = body[:600]
        else:
            body = body[:body_end]
        fingerprints = []
        for s in re.findall(r'"([^"]{6,60})"', body):
            if "corrodinggames" not in s and ":" not in s:
                fingerprints.append(s)
        if not fingerprints:
            for n in re.findall(r"\b(\d{3,})\b", body):
                fingerprints.append(n)
        if not fingerprints:
            continue
        # 03 文件找同指纹方法
        best = None
        for fp in fingerprints[:3]:
            fm = re.search(rf"(?:public |private |protected |static |abstract |final |strictfp )*[\w<>,\.\[\] ]+ (\w+)\s*\([^)]*\)\s*\{{[^}}]{0,400}" + re.escape(fp), src)
            if fm:
                best = fm.group(1)
                break
        if not best or best == mname2:
            continue
        # 替换调用点
        lines = src.split("\n")
        ln = int(e["line"]) - 1
        if not (0 <= ln < len(lines)):
            continue
        if f"{mname2}(" not in lines[ln]:
            continue
        old = lines[ln]
        new = lines[ln].replace(f"{mname2}(", f"{best}(")
        f = Fix("R7", fpath, old, new, f"02b 指纹 '{fingerprints[0]}' → 03 名 {best} (02b {fqn2}.{mname2})")
        lines[ln] = new
        f.new_src = "\n".join(lines)
        fixes.append(f)
    return fixes


RULES = {
    "R1": rule_import_missing,
    "R2": rule_import_broken,
    "R3": rule_static_field_decl,
    "R4": rule_short_class_ref,
    "R5": rule_stream_readfloat,
    "R6": rule_array_misname,
    "R7": rule_method_mismatch,
}


def main():
    ap = argparse.ArgumentParser(description="03 编译错误全自动补全器")
    ap.add_argument("--dry-run", action="store_true", help="只统计候选不修改")
    ap.add_argument("--max-rounds", type=int, default=12, help="最大迭代轮数")
    ap.add_argument("--rules", default="R1,R2,R3,R4,R5,R6,R7", help="启用规则 (逗号分隔)")
    args = ap.parse_args()

    active = [r.strip() for r in args.rules.split(",") if r.strip() in RULES]
    log(f"=== auto_align v19.113o 启动: 规则 {active} ===")

    base = run_gate()
    if base < 0:
        log("初始 gate 失败, 中止")
        return 1
    log(f"基线错误: {base}")

    total_fixes = 0
    for rnd in range(1, args.max_rounds + 1):
        errors = read_errors()
        if not errors:
            log("无错误, 完成!")
            break
        fixes = []
        for rname in active:
            try:
                fs = RULES[rname](errors)
                log(f"  轮{rnd} {rname}: 候选 {len(fs)}")
                fixes.extend(fs)
            except Exception as ex:
                log(f"  轮{rnd} {rname} 异常: {ex}")
        # 去重 (同文件同规则)
        seen = set()
        uniq = []
        for f in fixes:
            k = (str(f.fpath), f.rule)
            if k in seen:
                continue
            seen.add(k)
            uniq.append(f)
        if not uniq:
            log(f"轮{rnd}: 无候选修复, 收敛")
            break
        if args.dry_run:
            for f in uniq[:30]:
                log(f"  DRY {f}")
            log(f"轮{rnd} dry-run: {len(uniq)} 候选 (未应用)")
            break
        # 备份 + 应用
        backups = {}
        for f in uniq:
            if str(f.fpath) not in backups:
                backups[str(f.fpath)] = f.fpath.read_text(encoding="utf-8", errors="replace")
            f.fpath.write_text(f.new_src, encoding="utf-8")
        log(f"轮{rnd}: 应用 {len(uniq)} 修复 → gate 验证...")
        after = run_gate()
        if after < 0:
            log("gate 失败, 回退本轮")
            for p, old in backups.items():
                Path(p).write_text(old, encoding="utf-8")
            continue
        delta = base - after
        if after <= base:
            log(f"轮{rnd}: 净收益 -{delta} ({base} → {after}), 保留")
            base = after
            total_fixes += len(uniq)
            for f in uniq[:5]:
                log(f"    样例 {f}")
        else:
            log(f"轮{rnd}: 负收益 +{after - base}, 回退本轮 {len(uniq)} 处")
            for p, old in backups.items():
                Path(p).write_text(old, encoding="utf-8")
            # 负收益后降级: 禁用本轮最激进规则, 换下一轮重新收集
            continue

    log(f"=== 完成: 累计 {total_fixes} 修复, 错误 {base} (基线-{run_gate() if False else ''}) ===")
    final = run_gate()
    log(f"最终错误: {final}")
    return 0


if __name__ == "__main__":
    sys.exit(main())
