#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
fix_map_gap_classes.py — BC4 未映射混淆类批量配对器 (v19.114)

对 class-mapping-gaps.csv 的 187 个未映射混淆类, 用 Rule E 反向字符串指纹
(02b 源码字面量 × 03 可读文件字面量) 找身份配对:

  背景: 03 有 113 个未映射类无同名短名文件 — 03 侧可能已改名但 class-discoveries
  映射遗漏; 74 个有同名短名文件 (03 未改名, 需命名而非配对 — 输出人工清单).

  配对: 02b 类字符串集 ∩ 03 文件字符串集 ≥2, 唯一最佳 (次佳 < 最佳) →
  写 class-discoveries (verified='rule-e-fp-v19.114')

用法: python tools/fixers/fix_map_gap_classes.py [--dry-run] [--apply] [--min-overlap N]
"""

import argparse
import csv
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent.parent
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools" / "utils"))
from rwlib.config import DEOBFUSCATED_DIR  # noqa: E402
from identify_readable import fp_source  # noqa: E402

csv.field_size_limit(10 * 1024 * 1024)

TWOB = ROOT / "02b-decompiled"
GAPS_CSV = ROOT / "mappings" / "generated" / "class-mapping-gaps.csv"
DISCOVERIES = ROOT / "mappings" / "class-discoveries.csv"
MANUAL_OUT = ROOT / "mappings" / "generated" / "gap-classes-manual.csv"

STR_RE = re.compile(r'"((?:[^"\\]|\\.)*)"')


def load_gaps():
    with open(GAPS_CSV, encoding="utf-8", errors="replace") as f:
        return [(r[0], r[1]) for r in csv.reader(f)
                if len(r) >= 2 and r[0] != "obfuscated_package"]


def build_03_index():
    """03 可读名文件 → (字符串集, 方法数, 路径)."""
    idx = {}
    for p in DEOBFUSCATED_DIR.rglob("*.java"):
        if len(p.stem) <= 2:
            continue  # 短名孤儿不参与 (是同一类未改名, 非配对目标)
        text = p.read_text(encoding="utf-8", errors="replace")
        strs, methods = fp_source(text)
        if strs:
            idx[p.stem] = (strs, methods, p)
    return idx


def main():
    ap = argparse.ArgumentParser(description="BC4 未映射混淆类批量配对器")
    ap.add_argument("--dry-run", action="store_true")
    ap.add_argument("--apply", action="store_true")
    ap.add_argument("--min-overlap", type=int, default=2)
    args = ap.parse_args()
    if not args.dry_run and not args.apply:
        ap.print_help()
        sys.exit(1)

    gaps = load_gaps()
    idx03 = build_03_index()
    # 已映射可读名集合 (冲突检测: 目标已被其他混淆类映射 → 拆分/合并关系, 人工裁决)
    with open(DISCOVERIES, encoding="utf-8", errors="replace") as f:
        mapped_names = {r[3] for r in csv.reader(f) if len(r) >= 4 and r[0] == "class"}
    print(f"[BC4] 未映射类: {len(gaps)} | 03 可读文件索引: {len(idx03)} | 已映射名: {len(mapped_names)}")

    pairs = []      # (pkg, cls, 03名, overlap, 次佳, 错误原因或None)
    manual = []     # (pkg, cls, 最佳03名, overlap, 原因)
    for pkg, cls in gaps:
        p02 = TWOB / pkg.replace(".", "/") / f"{cls}.java"
        if not p02.exists():
            manual.append((pkg, cls, "", 0, "02b文件缺失"))
            continue
        text02 = p02.read_text(encoding="utf-8", errors="replace")
        strs2, m2 = fp_source(text02)
        if not strs2:
            manual.append((pkg, cls, "", 0, "02b无字符串"))
            continue
        scored = sorted(((stem, len(strs2 & s3)) for stem, (s3, _, _) in idx03.items()),
                        key=lambda t: -t[1])
        best, s = scored[0]
        second = scored[1][1] if len(scored) > 1 else 0
        if s >= args.min_overlap and s > second:
            if best in mapped_names:
                # 目标已被其他混淆类映射 → 拆分/合并/内联关系, 人工裁决 (铁律)
                manual.append((pkg, cls, best, s, f"重合{s}但目标已映射 (拆分/合并?)"))
            else:
                pairs.append((pkg, cls, best, s, second))
        else:
            manual.append((pkg, cls, best, s,
                           f"重合{s}<阈值或并列 (次佳{second})"))
    print(f"[BC4] 高置信配对: {len(pairs)} | 人工清单: {len(manual)}")

    if args.dry_run:
        for p in pairs[:40]:
            print(f"  {p[0]}.{p[1]} → {p[2]} (重合{p[3]}, 次佳{p[4]})")
        sys.exit(0)

    # 写 class-discoveries (追加, 去重)
    if pairs:
        with open(DISCOVERIES, encoding="utf-8", errors="replace") as f:
            existing = {(r[1], r[2]) for r in csv.reader(f) if len(r) >= 4}
        new_rows = []
        for pkg, cls, stem, s, second in pairs:
            if (pkg, cls) in existing:
                continue
            new_rows.append([f"class", pkg, cls, stem,
                             f"rule-e-fp-v19.114: 02b字符串指纹配对 (重合{s}, 次佳{second})"])
        if new_rows:
            with open(DISCOVERIES, "a", encoding="utf-8", newline="") as f:
                csv.writer(f).writerows(new_rows)
            print(f"[BC4] class-discoveries 新映射: {len(new_rows)} 条")
    # 人工清单
    with open(MANUAL_OUT, "w", encoding="utf-8", newline="") as f:
        w = csv.writer(f)
        w.writerow(["obfuscated_package", "obfuscated_class", "best_03_name",
                    "overlap", "reason"])
        w.writerows(manual)
    print(f"[BC4] 人工清单已写: {MANUAL_OUT.relative_to(ROOT)} ({len(manual)} 条)")


if __name__ == "__main__":
    main()
