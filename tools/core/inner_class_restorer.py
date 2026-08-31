#!/usr/bin/env python3
"""
内部类恢复 (R4) — 02 有 693 个 $ 文件, 03 缺失 ~377 个。

对每个 02 的 $N 文件: 父类混淆FQN → fwd → 意义父类; 若 03 已有意义父类文件
且缺 $N 文件 → 经 renamer 管道恢复 (父意义名$后缀)。

Usage: python tools/core/inner_class_restorer.py [--dry-run]
"""
import json
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import DECOMPILED_DIR, DEOBFUSCATED_DIR
from tools.core.constant_pool_renamer import load_indexes, rename_02_file


def main():
    dry_run = "--dry-run" in sys.argv
    fwd, rev, refs = load_indexes()

    restored = skipped = 0
    for src in sorted(DECOMPILED_DIR.rglob("*.java")):
        rel = src.relative_to(DECOMPILED_DIR).as_posix()
        if "$" not in src.stem:
            continue
        obf_fqn = rel.replace(".java", "").replace("/", ".")
        parent_obf, suffix = obf_fqn.split("$", 1)
        meaning_parent = fwd.get(parent_obf)
        if not meaning_parent:
            skipped += 1
            continue
        # 目标路径: 意义父类路径 + $后缀
        dst_rel = (meaning_parent.replace(".", "/") + "$" + suffix + ".java")
        if (DEOBFUSCATED_DIR / dst_rel).exists():
            continue  # 已存在
        content, rep = rename_02_file(rel, dst_rel, fwd, dry_run=dry_run)
        if content is None:
            skipped += 1
            continue
        restored += 1
        print(f"  恢复: {dst_rel}")
    print(f"\n内部类恢复: {restored}, 跳过: {skipped}" + (" (DRY RUN)" if dry_run else ""))
    sys.exit(0)


if __name__ == "__main__":
    main()
