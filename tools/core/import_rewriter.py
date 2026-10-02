#!/usr/bin/env python3
"""
全树 import 重写器 (R2) — package_renamer 补跑的正确形式。

逐文件逐 import 行, 三条规则 (幂等, 二遍零变更):
1. FQN ∈ fwd → 整条重写为意义FQN (类级映射)
2. 包段最长前缀 ∈ 包映射表 → 替换包段 (PKG_MAP 43包 + fwd 推导的补录映射)
3. 未命中 → 保留

包映射表 = PKG_MAP ∪ 补录映射, 补录映射由 fwd 经验推导:
某混淆包的全部已映射类都指向同一意义包 → 该包段整体可替换。
(实证: gameFramework.l 未在 PKG_MAP 但全部类 → gameFramework.core)

Usage: python tools/core/import_rewriter.py [--dry-run] [--audit <文件>]
"""
import csv
import json
import re
import sys
from collections import defaultdict
from pathlib import Path

csv.field_size_limit(10 * 1024 * 1024)

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import DEOBFUSCATED_DIR, GENERATED_DIR
from tools.fixers.package_renamer import PKG_MAP
from tools.core.constant_pool_renamer import load_indexes

IMPORT_RE = re.compile(r"^(\s*import\s+(?:static\s+)?)([\w.]+)(\s*;)")


def derive_supplement_pkg_map(fwd):
    """从 fwd 经验推导补录包映射: 混淆包全部映射类同包 → 包段替换。

    返回 {混淆包: 意义包}。PKG_MAP 已有的跳过。
    """
    by_pkg = defaultdict(set)   # 混淆包 → {意义包}
    for obf_fqn, meaning in fwd.items():
        if "$" in obf_fqn or "$" in meaning:
            continue  # 内部类不参与包推导
        op = obf_fqn.rsplit(".", 1)[0]
        mp = meaning.rsplit(".", 1)[0]
        by_pkg[op].add(mp)
    supp = {}
    for op, mps in sorted(by_pkg.items()):
        if op in PKG_MAP:
            continue
        if len(mps) == 1 and len(by_pkg.get(op, set())) == 1:
            mp = next(iter(mps))
            if mp != op:
                supp[op] = mp
    return supp


def main():
    dry_run = "--dry-run" in sys.argv
    fwd, rev, refs = load_indexes()
    supp = derive_supplement_pkg_map(fwd)
    pkg_map = {**supp, **PKG_MAP}  # PKG_MAP 优先 (人工映射)
    print(f"包映射: PKG_MAP {len(PKG_MAP)} + 补录 {len(supp)} = {len(pkg_map)}")
    for op, mp in sorted(supp.items()):
        print(f"  补录: {op} → {mp}")

    # 03 树索引: {简单类名: {FQN}} — 规则3 的实际位置解析
    tree_index = defaultdict(set)
    tree_fqns = set()
    for jf in DEOBFUSCATED_DIR.rglob("*.java"):
        rel = jf.relative_to(DEOBFUSCATED_DIR).as_posix()
        fqn = rel.replace(".java", "").replace("/", ".")
        tree_index[fqn.rsplit(".", 1)[-1]].add(fqn)
        tree_fqns.add(fqn)
    # game-lib.jar 类清单 — 校验规则2/3 (jar 可解析的 import 不改写)
    from tools.core.identity_index import build_jar_index
    jar_fqns = build_jar_index()

    audit_rows = []
    files_changed = 0
    total_fixes = 0
    for jf in sorted(DEOBFUSCATED_DIR.rglob("*.java")):
        content = jf.read_text(encoding="utf-8", errors="replace")
        lines = content.split("\n")
        changed = False
        for i, line in enumerate(lines):
            m = IMPORT_RE.match(line)
            if not m:
                continue
            imp = m.group(2)
            # 规则1: 类级映射 (fwd 整条重写)
            if imp in fwd and fwd[imp] != imp:
                lines[i] = m.group(1) + fwd[imp] + m.group(3)
                audit_rows.append((str(jf.relative_to(DEOBFUSCATED_DIR)), imp,
                                   fwd[imp], "fwd"))
                total_fixes += 1
                changed = True
                continue
            # 规则2: 包段最长前缀替换
            pkg = imp.rsplit(".", 1)[0]
            best = None
            for op, mp in pkg_map.items():
                if pkg == op or pkg.startswith(op + "."):
                    if best is None or len(op) > len(best[0]):
                        best = (op, mp)
            if best:
                op, mp = best
                new_imp = mp + imp[len(op):]
                # 校验: 目标 FQN 必须在 03 树或 fwd 值中 (jar 中仍为混淆名的不改写)
                if new_imp != imp and (new_imp in tree_fqns or new_imp in fwd.values()):
                    lines[i] = m.group(1) + new_imp + m.group(3)
                    audit_rows.append((str(jf.relative_to(DEOBFUSCATED_DIR)),
                                       imp, new_imp, f"pkg:{op}"))
                    total_fixes += 1
                    changed = True
                continue
            # 规则3: import 不在03树且不在jar (纯残留), 简单名在03唯一 → 实际位置重写
            if imp not in jar_fqns and imp not in tree_fqns:
                simple = imp.rsplit(".", 1)[-1]
                cands = tree_index.get(simple, set())
                if len(cands) == 1 and next(iter(cands)) != imp:
                    new_imp = next(iter(cands))
                    lines[i] = m.group(1) + new_imp + m.group(3)
                    audit_rows.append((str(jf.relative_to(DEOBFUSCATED_DIR)),
                                       imp, new_imp, "tree-unique"))
                    total_fixes += 1
                    changed = True
        if changed:
            files_changed += 1
            if not dry_run:
                jf.write_text("\n".join(lines), encoding="utf-8")

    print(f"重写 {total_fixes} 条 import, {files_changed} 文件" +
          (" (DRY RUN)" if dry_run else ""))

    # audit 输出
    if "--audit" in sys.argv:
        i = sys.argv.index("--audit")
        if i + 1 < len(sys.argv):
            with open(sys.argv[i + 1], "w", newline="", encoding="utf-8") as f:
                w = csv.writer(f)
                w.writerow(["file", "old", "new", "rule"])
                w.writerows(audit_rows)
            print(f"audit 写入: {sys.argv[i + 1]} ({len(audit_rows)} 条)")
    sys.exit(0)


if __name__ == "__main__":
    main()
