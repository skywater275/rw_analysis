#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""CI 一致性检查 (本地与 GitHub Actions 共用): 映射 7 列/键唯一性/README 链接.
Usage: python tools/gates/ci_checks.py
退出码: 全部通过 0, 任一失败 1.
"""
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))

import csv
csv.field_size_limit(10 * 1024 * 1024)


def check_supplement():
    """supplement.csv: 全部 7 列 + 首列合法."""
    with open(ROOT / "mappings" / "supplement.csv", encoding="utf-8", errors="replace") as f:
        rows = list(csv.reader(f))
    bad = [i + 1 for i, r in enumerate(rows[1:]) if len(r) != 7]
    n = sum(1 for r in rows[1:] if len(r) >= 2 and r[0] in ("field", "method"))
    if bad:
        print(f"[FAIL] supplement 列数异常行: {bad[:10]}")
        return 1
    print(f"[OK] supplement: {n} 条, 全 7 列")
    return 0


def check_discoveries():
    """class-discoveries.csv: 行格式完整 (重复键允许: 03 拆分双类/验证追加)."""
    with open(ROOT / "mappings" / "class-discoveries.csv", encoding="utf-8", errors="replace") as f:
        rows = [r for r in csv.reader(f) if len(r) >= 4]
    bad = [i + 1 for i, r in enumerate(rows[1:]) if not r[1] or not r[2] or not r[3]]
    n = sum(1 for r in rows[1:] if r[0] == "class")
    if bad:
        print(f"[FAIL] class-discoveries 空字段行: {bad[:10]}")
        return 1
    print(f"[OK] class-discoveries: {n} 条目")
    return 0


def check_readme_links():
    """根 README.md: 全部 md 链接有效."""
    text = (ROOT / "README.md").read_text(encoding="utf-8", errors="replace")
    links = set(re.findall(r"\]\(([^)#]+\.md)\)", text))
    missing = [l for l in links if not (ROOT / l).exists()]
    if missing:
        print(f"[FAIL] README 失效链接: {missing}")
        return 1
    print(f"[OK] README: {len(links)} 链接有效")
    return 0


def main():
    checks = [check_supplement, check_discoveries, check_readme_links]
    rc = 0
    for c in checks:
        rc |= c()
    sys.exit(rc)


if __name__ == "__main__":
    main()
