#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
fix_type_aware_calls.py — Phase B 类型感知调用点修复器 (v19.114)

利用 javac 的 location 类型信息 ("variable X of type Y") 精确确定调用点接收者类型,
经 supplement 映射查表 (混淆成员 → 语义名), 做跨文件调用点改名。

数据链:
  compile-errors.csv  →  类型感知 cannot-find 错误 (location 含 of type)
  class-discoveries  →  (混淆包, 混淆类) → 可读类名 (B2 反向类型映射)
  supplement.csv     →  (类, 混淆成员) → 语义名 [铁证级 verified 白名单]
  03-deobfuscated    →  声明侧检查 (语义名已声明且 arity 匹配) + 行级精确替换

用法:
  python tools/fixers/fix_type_aware_calls.py [--dry-run] [--apply] [--limit N]

铁律落地:
  F3 证据链 — 每处改名的锚点 = supplement 行的 (verified, notes), 持久化到
  mappings/generated/type-aware-fixes.csv, 可逐条审计。
  声明侧检查 — 语义名必须在接收者类文件中已声明 (方法: arity 匹配; 字段: 非调用形态),
  防止把调用点改名到不存在的成员。
  行内唯一 — 目标形态在该行出现次数 != 1 则跳过 (F4 上下文限定兜底)。
"""

import argparse
import csv
import re
import sys
from collections import defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent.parent
DEOBF = ROOT / "03-deobfuscated"
ERRORS_CSV = ROOT / "compile-errors.csv"
SUPPLEMENT_CSV = ROOT / "mappings" / "supplement.csv"
DISCOVERIES = ROOT / "mappings" / "class-discoveries.csv"
FIXES_OUT = ROOT / "mappings" / "generated" / "type-aware-fixes.csv"

csv.field_size_limit(10 * 1024 * 1024)

# 铁证级 verified 白名单 (02 锚点 / 字节码 / 运行时验证的条目)
EVIDENCE_PREFIXES = (
    "verified",              # 纯 verified
    "body-verified",         # 方法体字节码验证 (v1007-v1500 系列)
    "bytecode-verified",     # 字节码验证
    "game-prefs-verified",   # 游戏偏好字段验证
    "ini-verified",          # INI 参数验证 (v16)
    "mod-key-verified",      # mod 键表验证 (M1 机制)
    "func-semantics-verified",  # 函数类语义名验证 (M2 机制)
    "runtime-verified",      # 运行时铁证
    "main-deobf-anchored",   # 主解混淆锚定
    "real-class-v1200",      # 真实类验证
    "p3-deep-curated",       # 深度整理
    "field-order-zip",       # 字段保序 zip 配对 (v19.114, 02b 字段序 × 03 声明序双验证)
    "type-aware-fp",         # 02b 字面量指纹配对 (v19.114)
    "string-evidence",       # 字符串常量池证据 (v19.114 深3 仲裁)
)

# 命名质量过滤器 — 排除占位名 (CFR 风格 floatN/intN/stringN) 与推断拼接名
GARBAGE_NAME = re.compile(
    r"^(float|int|long|boolean|byte|short|double|char|string|Paint)\d*$"  # float30/int5/string4 占位名
    r"|^get[a-z]{1,3}$"           # getas/getat/getint 拼接残留
    r"|\s"                        # 含空白 (getstrictfp void / getstrictfp boolean 拼接残)
    r"|^constructor$"             # CFR 构造器占位
)


def is_evidence(verified: str) -> bool:
    """判断 verified 列是否属于铁证级白名单."""
    v = (verified or "").strip()
    return any(v == p or v.startswith(p) for p in EVIDENCE_PREFIXES)


def is_garbage_name(name: str) -> bool:
    """判断语义名是否为占位/拼接垃圾名."""
    return bool(GARBAGE_NAME.match(name.strip()))


def load_discoveries():
    """(混淆包, 混淆类) → 可读类名."""
    fwd = {}
    with open(DISCOVERIES, encoding="utf-8", errors="replace") as f:
        for r in csv.reader(f):
            if len(r) >= 4 and r[0] == "class":
                fwd[(r[1], r[2])] = r[3]
    return fwd


def load_supplement_index(fwd):
    """可读简单类名 → [(混淆成员名, 语义名, type, notes, verified, 参数串)] (仅铁证级)."""
    idx = defaultdict(list)
    with open(SUPPLEMENT_CSV, encoding="utf-8", errors="replace") as f:
        reader = csv.reader(f)
        header = next(reader)
        col = {name: i for i, name in enumerate(header)}
        for r in reader:
            if len(r) < 7 or not is_evidence(r[col.get("verified", 6)]):
                continue
            name = r[col["meaningful_name"]]
            if is_garbage_name(name):
                continue
            pkg, cls = r[col["obfuscated_package"]], r[col["obfuscated_class"]]
            if len(cls) <= 2:
                rd = fwd.get((pkg, cls))
                if rd is None:
                    continue
            else:
                rd = cls
            simple = rd.split(".")[-1]
            member, params = parse_member_sig(r[col["obfuscated_member"]])
            idx[simple].append((
                member,
                r[col["meaningful_name"]],
                r[col["type"]],
                r[col["notes"]],
                r[col["verified"]],
                params,
                pkg,
            ))
    return idx


def parse_member_sig(member: str):
    """member 列签名解析: 'a(RectF ->  RectF)' → ('a', 'RectF'); 'a(l)' → ('a', 'l'); 'b' → ('b', None)."""
    if "(" not in member or ")" not in member:
        return member.strip(), None  # 无签名或列损坏 (括号不成对) → 按无签名处理
    mname = member.split("(")[0].strip()
    inner = member[member.index("(") + 1: member.rindex(")")]
    params = inner.split("->")[0].strip() if "->" in inner else inner.strip()
    return mname, params


BASIC_TYPES = {"int", "float", "boolean", "void", "byte", "short", "long", "double", "char", "String"}


def split_params(s: str):
    """顶层逗号分割参数串 (括号/泛型深度感知)."""
    parts, depth, cur = [], 0, ""
    for ch in s + ",":
        if ch in "(<[":
            depth += 1
        elif ch in ")>]":
            depth -= 1
        if ch == "," and depth == 0:
            parts.append(cur.strip())
            cur = ""
        else:
            cur += ch
    return [p for p in parts if p]


def translate_param(p: str, fwd, pkg: str) -> str:
    """参数类型译名: 混淆类名 → 可读简单名; 基本类型恒等; 无法解析 → '?'."""
    base = re.split(r"[\[<]", p)[0].strip()
    if base in BASIC_TYPES:
        return base
    rd = fwd.get((pkg, base))
    if rd:
        return rd.split(".")[-1]
    return "?"


def disambiguate(cands, sym_params: str, arity: int, fwd):
    """签名细分: 在多名候选中按 (参数个数, 类型串) 匹配找唯一候选. 返回唯一条目或 None."""
    if sym_params is None:
        return None
    sym_parts = split_params(sym_params)
    narrowed = []
    for e in cands:
        params = e[5]
        if params is None:
            continue
        parts = split_params(params)
        if len(parts) != arity:
            continue
        # 位置比对: 译名 == symbol 参数 (数组去 []) 或译名 '?' 跳过
        ok, all_unknown = True, True
        for sp, pp in zip(sym_parts, parts):
            sbase = re.split(r"[\[<]", sp)[0].strip()
            t = translate_param(pp, fwd, e[6])
            if t != "?":
                all_unknown = False
                if t != sbase:
                    ok = False
                    break
        if ok and not all_unknown:
            narrowed.append(e)
    if len(narrowed) == 1:
        return narrowed[0]
    return None


def build_file_index():
    """可读简单类名 → [03 文件路径] (同名多文件记录全部)."""
    fidx = defaultdict(list)
    for p in DEOBF.rglob("*.java"):
        fidx[p.stem].append(p)
    return fidx


def top_arity(params: str) -> int:
    """顶层逗号数 + 1 (括号/泛型深度感知)."""
    params = params.strip()
    if not params:
        return 0
    depth = 0
    count = 1
    for ch in params:
        if ch in "(<[":
            depth += 1
        elif ch in ")>]":
            depth -= 1
        elif ch == "," and depth == 0:
            count += 1
    return count


def find_method_arity(text: str, name: str, want_arity: int):
    """检查 name(...) 声明: 返回 (arity匹配, 声明总数).

    声明判别: 名字前紧邻 '.' 是调用点 (receiver.name(...)), 跳过 —
    只匹配声明侧, 防止类内部调用点被误判为声明 (v19.114 LineBuffer/StatsPanel 教训).
    v1 保守: 声明总数 > 1 (重载类) 时返回 (False, n) — 实参类型兼容性无法
    从 javac symbol 判定 (StatsPanel.createPanel 5 重载教训).
    """
    arity_ok = False
    total = 0
    for m in re.finditer(r"\b" + re.escape(name) + r"\s*\(", text):
        if m.start() > 0 and text[m.start() - 1] == ".":
            continue  # 调用点形态
        total += 1
        # 括号匹配提取参数段
        i, depth = m.end(), 1
        start = m.end()
        while i < len(text) and depth > 0:
            if text[i] == "(":
                depth += 1
            elif text[i] == ")":
                depth -= 1
            i += 1
        if depth == 0 and top_arity(text[start:i - 1]) == want_arity:
            arity_ok = True
    return arity_ok, total


def check_declaration(fidx, type_name, new_name, kind, arity):
    """声明侧检查 — 语义名必须已在接收者类文件中存在."""
    files = fidx.get(type_name, [])
    if not files or len(files) > 1:
        return None  # 无文件或同名多文件 → 跳过 (保守)
    text = files[0].read_text(encoding="utf-8", errors="replace")
    if kind == "method":
        arity_ok, total = find_method_arity(text, new_name, arity)
        return arity_ok and total == 1  # 重载类 (total>1) 保守跳过
    else:
        # 字段: 存在非调用形态 (排除同名方法碰撞)
        if re.search(r"\b" + re.escape(new_name) + r"\s*\(", text):
            return False  # 同名方法存在 → 字段改名碰撞风险
        return re.search(r"\b" + re.escape(new_name) + r"\b", text) is not None


def parse_errors():
    """解析类型感知 cannot-find 错误 → 候选列表."""
    cands = []
    with open(ERRORS_CSV, encoding="utf-8", errors="replace") as f:
        for r in csv.reader(f):
            if len(r) < 6 or "cannot find symbol" not in r[3]:
                continue
            loc_m = re.search(r"of type (\S+)", r[5])
            if not loc_m:
                continue
            y = loc_m.group(1)
            if len(y) <= 2 or y == "Object":
                continue
            sym = r[4].strip()
            if not sym:
                continue
            if "(" in sym:
                name = sym.split("(")[0].strip()
                params = sym[sym.index("(") + 1: sym.rindex(")")]
                kind, arity = "method", top_arity(params)
            else:
                name, params, kind, arity = sym, None, "field", None
            cands.append({
                "file": r[0], "line": int(r[1]), "sym": sym,
                "name": name, "kind": kind, "arity": arity, "type": y,
                "params": params,
            })
    return cands


def collect_fixes(idx, fidx, fwd):
    """查表 + 签名细分 + 声明检查 → 可应用修复清单."""
    fixes = []
    skipped = defaultdict(int)
    for c in parse_errors():
        cands = [e for e in idx.get(c["type"], []) if e[0] == c["name"]]
        if not cands:
            skipped["索引无此成员"] += 1
            continue
        names = {e[1] for e in cands}
        if len(names) != 1:
            # v2 签名细分: 方法调用按 (参数个数, 类型串) 在多名候选中找唯一
            entry = disambiguate(cands, c.get("params"), c["arity"], fwd) \
                if c["kind"] == "method" else None
            if entry is None:
                skipped["多候选歧义"] += 1
                continue
        else:
            entry = cands[0]
        new_name = entry[1]
        if entry[2] != c["kind"]:
            skipped["成员类型不符"] += 1
            continue
        if not check_declaration(fidx, c["type"], new_name, c["kind"], c["arity"]):
            skipped["声明侧检查失败"] += 1
            continue
        fixes.append({**c, "new_name": new_name, "notes": entry[3], "verified": entry[4]})
    return fixes, skipped


def apply_all(fixes):
    """按文件分组应用替换 (整文件一次性回写)."""
    by_file = defaultdict(list)
    for fx in fixes:
        by_file[fx["file"]].append(fx)
    changed = []
    for fname, fxs in by_file.items():
        path = ROOT / fname
        lines = path.read_text(encoding="utf-8", errors="replace").splitlines(keepends=True)
        for fx in fxs:
            ln = fx["line"] - 1
            if not (0 <= ln < len(lines)):
                continue
            line = lines[ln]
            if fx["kind"] == "method":
                pat = re.compile(r"\b" + re.escape(fx["name"]) + r"\s*\(")
                m = list(pat.finditer(line))
                if len(m) != 1:
                    continue
                m = m[0]
                ws_len = len(m.group(0)) - len(fx["name"]) - 1
                replaced = fx["new_name"] + " " * ws_len + "("
                lines[ln] = line[:m.start()] + replaced + line[m.end():]
            else:
                pat = re.compile(r"\.\s*" + re.escape(fx["name"]) + r"\b(?!\s*\()")
                m = list(pat.finditer(line))
                if len(m) != 1:
                    continue
                m = m[0]
                dot_pos = m.group(0).index(fx["name"])
                lines[ln] = line[:m.start()] + m.group(0)[:dot_pos] + fx["new_name"] + line[m.end():]
        path.write_text("".join(lines), encoding="utf-8")
        changed.append(fname)
    return changed


def main():
    ap = argparse.ArgumentParser(description="Phase B 类型感知调用点修复器")
    ap.add_argument("--dry-run", action="store_true", help="只预览不写文件")
    ap.add_argument("--apply", action="store_true", help="实际写入修复")
    ap.add_argument("--limit", type=int, default=0, help="最多应用 N 条 (0=全部)")
    args = ap.parse_args()
    if not args.dry_run and not args.apply:
        ap.print_help()
        sys.exit(1)

    fwd = load_discoveries()
    idx = load_supplement_index(fwd)
    fidx = build_file_index()
    print(f"[PhaseB] 铁证级归一化索引: {len(idx)} 类 / {sum(len(v) for v in idx.values())} 条目")
    print(f"[PhaseB] 03 文件索引: {len(fidx)} 类")

    fixes, skipped = collect_fixes(idx, fidx, fwd)
    print(f"[PhaseB] 候选修复: {len(fixes)}")
    print(f"[PhaseB] 过滤原因: {dict(skipped)}")

    # 行内唯一检查 (apply 时二次验证; dry-run 预检)
    ok = []
    for fx in fixes:
        path = ROOT / fx["file"]
        if not path.exists():
            skipped["文件不存在"] += 1
            continue
        lines = path.read_text(encoding="utf-8", errors="replace").splitlines(keepends=True)
        ln = fx["line"] - 1
        if not (0 <= ln < len(lines)):
            skipped["行号越界"] += 1
            continue
        line = lines[ln]
        if fx["kind"] == "method":
            pat = re.compile(r"\b" + re.escape(fx["name"]) + r"\s*\(")
        else:
            pat = re.compile(r"\.\s*" + re.escape(fx["name"]) + r"\b(?!\s*\()")
        if len(list(pat.finditer(line))) != 1:
            skipped["行内唯一失败"] += 1
            continue
        ok.append(fx)

    if args.limit:
        ok = ok[:args.limit]
    print(f"[PhaseB] 行内唯一通过: {len(ok)}")

    if args.dry_run:
        print(f"[PhaseB] --- 预览 (前 30 条) ---")
        for fx in ok[:30]:
            print(f"  {fx['file'].split('03-deobfuscated/')[-1]}:{fx['line']} "
                  f"{fx['kind']} {fx['name']} → {fx['new_name']} "
                  f"[{fx['type']}] ({fx['verified'][:24]})")
        sys.exit(0)

    # 应用
    changed = apply_all(ok)
    print(f"[PhaseB] 已改写文件: {len(changed)}")

    # 证据链持久化 (F3)
    FIXES_OUT.parent.mkdir(parents=True, exist_ok=True)
    with open(FIXES_OUT, "w", encoding="utf-8", newline="") as f:
        w = csv.writer(f)
        w.writerow(["file", "line", "kind", "old_name", "new_name", "type", "notes", "verified"])
        for fx in ok:
            w.writerow([fx["file"], fx["line"], fx["kind"], fx["name"],
                        fx["new_name"], fx["type"], fx["notes"], fx["verified"]])
    print(f"[PhaseB] 证据链已写: {FIXES_OUT.relative_to(ROOT)} ({len(ok)} 条)")


if __name__ == "__main__":
    main()
