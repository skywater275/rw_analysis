#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
fix_short_type_residual.py — 误2: 03 类型标注残留修复器 (v19.114)

03 文件中 '单字符 变量 = new 单字符(' 形态 = 类型标注未升级 (如 ReplayEngine:833
`k k2 = new k(...)` 应为 SleepThread). 按文件 import/同包消歧 → fwd 可读名替换.

消歧 (铁律):
  1. 文件 import 段含 'import X.Y.<单字符>;' → 候选键 (Y包, 单字符)
  2. 无 import → 文件自身混淆包 (03 可读包 → 混淆包反查太绕, 用 02 同文件路径的包)
  3. fwd 唯一映射 → 替换声明行 (含 new)
  4. 多候选 → 跳过

用法: python tools/fixers/fix_short_type_residual.py [--dry-run] [--apply]
"""

import argparse
import csv
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent.parent
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools" / "fixers"))
from rwlib.config import DEOBFUSCATED_DIR  # noqa: E402
import fix_type_fingerprint as fp  # noqa: E402

csv.field_size_limit(10 * 1024 * 1024)

NEW_PAT = re.compile(r"\b([a-z])\s+(\w+)\s*=\s*new\s+\1\s*\(")


def resolve(fwd, p03, short):
    """(pkg, cls) 混淆键候选: import 段 + 同包 → fwd 可读名 (唯一)."""
    text = p03.read_text(encoding="utf-8", errors="replace")
    cands = set()
    for m in re.finditer(r"import\s+([\w.]+)\.([a-z]);", text):
        if m.group(2) == short:
            cands.add((m.group(1), m.group(2)))
    # 同包: 03 文件包 → 02 混淆包 (用 fwd 反查: 可读包名相同的键)
    pkg3 = ".".join(p03.relative_to(DEOBFUSCATED_DIR).parts[:-1])
    for (pk, cn), v in fwd.items():
        if cn == short and pk == pkg3:
            cands.add((pk, cn))
    names = set()
    for key in cands:
        v = fwd.get(key)
        if v:
            names.add(v.split(".")[-1])
    if len(names) == 1:
        return names.pop()
    return None


def main():
    ap = argparse.ArgumentParser(description="03 类型标注残留修复器")
    ap.add_argument("--dry-run", action="store_true")
    ap.add_argument("--apply", action="store_true")
    args = ap.parse_args()
    if not args.dry_run and not args.apply:
        ap.print_help()
        sys.exit(1)

    fwd = fp.ftac.load_discoveries()
    fixes = []
    skipped = 0
    for p in DEOBFUSCATED_DIR.rglob("*.java"):
        text = p.read_text(encoding="utf-8", errors="replace")
        for m in NEW_PAT.finditer(text):
            short = m.group(1)
            name = resolve(fwd, p, short)
            if not name or name == short:
                skipped += 1
                continue
            line = text[:m.start()].count("\n") + 1
            fixes.append((p, line, short, name))
    print(f"[STR] 可修复: {len(fixes)} | 跳过 (多义/无映射): {skipped}")

    if args.dry_run:
        for p, line, short, name in fixes[:30]:
            print(f"  {p.relative_to(DEOBFUSCATED_DIR)}:{line} {short} -> {name}")
        sys.exit(0)

    changed = set()
    for p, line, short, name in fixes:
        text = p.read_text(encoding="utf-8", errors="replace")
        # 仅该行替换 (行号定位)
        lines = text.splitlines(keepends=True)
        if 1 <= line <= len(lines):
            lines[line - 1] = re.sub(r"\b" + short + r"\s+(\w+)\s*=\s*new\s+" + short + r"\s*\(",
                                     name + r" \1 = new " + name + r"(", lines[line - 1])
            p.write_text("".join(lines), encoding="utf-8")
            changed.add(str(p.relative_to(DEOBFUSCATED_DIR)))
    print(f"[STR] 已改写文件: {len(changed)}")


if __name__ == "__main__":
    main()
