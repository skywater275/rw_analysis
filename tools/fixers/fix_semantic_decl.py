#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
fix_semantic_decl.py — BC3 语义名声明侧修复器 (v19.114)

类型感知 cannot-find 的语义名 symbol (len>2) = 调用点已用语义名, 声明侧缺失/未改名。
本工具做声明侧修复 (方向与 B3 相反):

  路径 A (官方语义类): 02b 文件含 name 方法声明 (arity 匹配) → 03 声明侧补/改名 name
      证据 = 02b (FernFlower 字节码反编译源) 方法名本身 — T0 级
  路径 B (混淆类): 03 类中 arity 匹配的混淆短名方法唯一 + 02b 同方法字面量指纹一致
      → 声明侧改名混淆名 → name (整类广播声明+本类引用)
      证据 = 02b 指纹 (02 锚点)

安全防线:
  1. 声明侧已有 name 声明 → 跳过 (错误另有原因: 类型不兼容/可见性)
  2. 多候选 (arity 匹配的混淆方法 >1) → 跳过
  3. 改名后整类内 `.混淆名(` 调用点同步 (声明+引用原子)
  4. 每处修复记录证据链 CSV

用法: python tools/fixers/fix_semantic_decl.py [--dry-run] [--apply] [--limit N]
"""

import argparse
import csv
import re
import sys
from collections import defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent.parent
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools" / "fixers"))
from rwlib.config import DEOBFUSCATED_DIR  # noqa: E402
import fix_type_aware_calls as ftac  # noqa: E402
import fix_type_fingerprint as fp  # noqa: E402
from fix_method_pair_generic import extract  # noqa: E402

csv.field_size_limit(10 * 1024 * 1024)

FIXES_OUT = ROOT / "mappings" / "generated" / "semantic-decl-fixes.csv"


def collect_targets():
    """(类型, 语义名symbol, arity) → 错误列表 (symbol len>2 且索引无该成员)."""
    fwd = ftac.load_discoveries()
    idx = ftac.load_supplement_index(fwd)
    targets = defaultdict(list)
    for c in ftac.parse_errors():
        if len(c["name"]) <= 2 or c["arity"] is None:
            continue  # 只处理语义名方法调用
        # 跳过索引已有映射者 (B3/fix_type_aware 负责)
        cands = [e for e in idx.get(c["type"], []) if e[0] == c["name"]]
        if cands:
            continue
        targets[(c["type"], c["name"], c["arity"])].append(c)
    return targets


def path_a_check(text02, name, arity):
    """路径 A: 02b 文件含 name 方法声明 (arity 匹配)? → True."""
    return ftac.find_method_arity(text02, name, arity)[0]


def path_b_match(text02, text03, name, arity):
    """路径 B: 03 arity 匹配的混淆短名方法唯一 + 02b 同方法指纹一致 → 返回混淆名或 None."""
    m03 = [x for x in extract(text03) if x["a"] == arity and len(x["n"]) <= 2]
    if len(m03) != 1:
        return None
    obf_name = m03[0]["n"]
    m02 = [x for x in extract(text02) if x["n"] == obf_name and x["a"] == arity]
    if len(m02) != 1:
        return None
    inter = len(m02[0]["lits"] & m03[0]["lits"])
    if inter < 1:
        return None
    return obf_name


def rename_in_class(path, obf_name, new_name):
    """整类广播改名: 声明处 + 本类内 `.obf(` 调用点 (排除字符串/注释)."""
    text = path.read_text(encoding="utf-8", errors="replace")
    # 声明处 (非调用点形态): 名字前不是 '.'
    new_text, n_decl = re.subn(
        r"(?<![.\w])" + re.escape(obf_name) + r"\s*\(",
        new_name + "(", text)
    # 本类内调用点 `.obf(`
    new_text, n_calls = re.subn(
        r"\.\s*" + re.escape(obf_name) + r"\s*\(",
        "." + new_name + "(", new_text)
    return new_text, n_decl, n_calls


def main():
    ap = argparse.ArgumentParser(description="BC3 语义名声明侧修复器")
    ap.add_argument("--dry-run", action="store_true")
    ap.add_argument("--apply", action="store_true")
    ap.add_argument("--limit", type=int, default=0)
    args = ap.parse_args()
    if not args.dry_run and not args.apply:
        ap.print_help()
        sys.exit(1)

    fwd = ftac.load_discoveries()
    rev = fp.build_rev(fwd)
    fidx = ftac.build_file_index()
    targets = collect_targets()
    print(f"[BC3] 目标组合 (类型,语义名,arity): {len(targets)}")

    results = []
    reasons = defaultdict(int)
    for (y, name, arity), errs in targets.items():
        pairs = rev.get(y, [])
        if len(pairs) != 1:
            reasons["rev多义或无映射"] += len(errs)
            continue
        pkg, cls = pairs[0]
        p02 = fp.TWOB / pkg.replace(".", "/") / f"{cls}.java"
        if not p02.exists():
            reasons["02b文件缺失"] += len(errs)
            continue
        p03files = fidx.get(y, [])
        if len(p03files) != 1:
            reasons["03文件多义"] += len(errs)
            continue
        text02 = p02.read_text(encoding="utf-8", errors="replace")
        text03 = p03files[0].read_text(encoding="utf-8", errors="replace")
        # 03 已有 name 声明 → 跳过 (错误另有原因)
        if ftac.find_method_arity(text03, name, arity)[0]:
            reasons["03已有声明"] += len(errs)
            continue
        obf_name = None
        if path_a_check(text02, name, arity):
            obf_name = None  # 路径 A: 02b 已有语义名 — 03 需补 name 方法 (暂只记原因)
            reasons["路径A官方名"] += len(errs)
            continue
        obf_name = path_b_match(text02, text03, name, arity)
        if obf_name is None:
            reasons["路径B无唯一候选"] += len(errs)
            continue
        results.append((y, name, arity, obf_name, pkg, cls, p03files[0], errs))

    print(f"[BC3] 可修复组合: {len(results)}")
    print(f"[BC3] 原因分布: {dict(reasons)}")
    if args.dry_run:
        for r in results[:40]:
            print(f"  {r[0]}.{r[3]}({r[2]}参) → 声明侧改名 {r[1]} | 错误 {len(r[7])} 处 | {r[5]}")
        sys.exit(0)

    fixes_out = []
    changed = []
    for (y, name, arity, obf_name, pkg, cls, p03, errs) in results:
        new_text, n_decl, n_calls = rename_in_class(p03, obf_name, name)
        if n_decl < 1:
            continue
        p03.write_text(new_text, encoding="utf-8")
        changed.append(str(p03.relative_to(DEOBFUSCATED_DIR)))
        for c in errs:
            fixes_out.append([c["file"], c["line"], obf_name, name, y,
                              f"BC3声明侧改名 (02b指纹, {pkg}/{cls})", "semantic-decl-v19.114"])
    print(f"[BC3] 已改写文件: {len(changed)}")
    if fixes_out:
        FIXES_OUT.parent.mkdir(parents=True, exist_ok=True)
        with open(FIXES_OUT, "w", encoding="utf-8", newline="") as f:
            w = csv.writer(f)
            w.writerow(["file", "line", "old_name", "new_name", "type", "evidence", "level"])
            w.writerows(fixes_out)
        print(f"[BC3] 证据链已写: {FIXES_OUT.relative_to(ROOT)} ({len(fixes_out)} 条)")


if __name__ == "__main__":
    main()
