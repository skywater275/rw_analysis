#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
verify_suspicious.py — 726 suspicious-bc-missing 映射复核器 (v19.133f98 逆向)

B5.6 判定 suspicious-bc-missing (字节码缺失) 的映射逐条复核:
  1. FQN 解析: pkg (短顶层包如 a.a 或完整包) + cls — 02 树文件存在性验证
  2. javap -classpath game-lib.jar: member 存在性 (字段: 名; 方法: 名( 剥签名)
  3. 分类: 存在 → 误报恢复 verified-exists; 类缺失 → unverifiable-rebuilt;
     真缺失 → 保持 suspicious (映射错误候选)

用法: python tools/fixers/verify_suspicious.py [--dry-run] [--apply] [--workers 8]
"""

import argparse
import csv
import re
import subprocess
import sys
from concurrent.futures import ThreadPoolExecutor
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent.parent
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import GAME_LIB, find_javap  # noqa: E402

csv.field_size_limit(10 * 1024 * 1024)
TWOB_SRC = ROOT / "02-decompiled"  # 02 树 (顶层包 a/ 等 + com 包)

MEMBER_SIG = re.compile(r"^([\w$]+)(?:\(.*)?$")


def fqn_valid(pkg, cls):
    """02 树文件存在性验证 FQN (pkg 含短顶层包或完整包)."""
    p = TWOB_SRC / pkg.replace(".", "/") / f"{cls}.java"
    return p.exists()


def javap_members(fqn):
    """javap -p 输出该类的字段与方法名集合."""
    r = subprocess.run([find_javap(), "-p", "-classpath", str(GAME_LIB), fqn],
                       capture_output=True, text=True, encoding="utf-8",
                       errors="replace", timeout=60)
    if r.returncode != 0:
        return None
    fields, methods = set(), set()
    for line in r.stdout.splitlines():
        s = line.strip()
        if "(" in s and s.endswith(";"):
            m = re.match(r"[\w.$<>\[\]]+\s+([\w$]+)\(", s)
            if m:
                methods.add(m.group(1))
        elif "(" not in s and s.endswith(";") and not s.startswith(("Compiled", "}")) and "{" not in s:
            m = re.match(r"(?:[\w$<>\[\].]+\s)+([\w$]+);", s)
            if m:
                fields.add(m.group(1))
    return fields, methods


def verify_one(row):
    """复核一条 → (类别, fqn, member, name)."""
    pkg, cls, member, name = row[1], row[2], row[3], row[4]
    # 类名含签名 (j()) → 剥括号
    cls_clean = cls.split("(")[0]
    if not fqn_valid(pkg, cls_clean):
        return ("class-missing", pkg, cls, member, name)
    fqn = f"{pkg}.{cls_clean}"
    res = javap_members(fqn)
    if res is None:
        return ("class-missing", pkg, cls, member, name)
    fields, methods = res
    m = MEMBER_SIG.match(member)
    mname = m.group(1) if m else member
    if row[0] == "field":
        return ("exists" if mname in fields else "member-missing", pkg, cls, member, name)
    return ("exists" if mname in methods else "member-missing", pkg, cls, member, name)


def main():
    ap = argparse.ArgumentParser(description="726 可疑映射复核器")
    ap.add_argument("--dry-run", action="store_true")
    ap.add_argument("--apply", action="store_true")
    ap.add_argument("--workers", type=int, default=8)
    args = ap.parse_args()
    if not args.dry_run and not args.apply:
        ap.print_help()
        sys.exit(1)

    with open(ROOT / "mappings" / "supplement.csv", encoding="utf-8", errors="replace") as f:
        rows = list(csv.reader(f))
    susp = [(i, r) for i, r in enumerate(rows) if len(r) >= 7 and r[6] == "suspicious-bc-missing"]
    print(f"[VS] 复核目标: {len(susp)} 条")

    results = []
    with ThreadPoolExecutor(max_workers=args.workers) as ex:
        for idx, r in susp:
            fut = ex.submit(verify_one, r)
            results.append((idx, fut))
    from collections import Counter
    cats = Counter()
    updates = []
    for idx, fut in results:
        cat, pkg, cls, member, name = fut.result()
        cats[cat] += 1
        if cat == "exists":
            updates.append((idx, "verified-exists"))
        elif cat == "class-missing":
            updates.append((idx, "unverifiable-rebuilt"))
    print(f"[VS] 分类: {dict(cats)}")
    if args.dry_run:
        sys.exit(0)
    for idx, new_v in updates:
        rows[idx][6] = new_v
    with open(ROOT / "mappings" / "supplement.csv", "w", encoding="utf-8", newline="") as f:
        csv.writer(f).writerows(rows)
    print(f"[VS] 已更新: {len(updates)} 条 (exists→verified-exists / class-missing→unverifiable-rebuilt)")


if __name__ == "__main__":
    main()
