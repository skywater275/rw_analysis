#!/usr/bin/env python3
"""按「**完整覆盖**」判据（RS-68 ✓）筛判不符类：补映射是否可能转合格 ✓。
用法: python tools/analysis/mapping_gap_screen.py
"""
import csv
import re
import sys
import zipfile
from pathlib import Path

ROOT = Path('.')
csv.field_size_limit(10 * 1024 * 1024)
sys.path.insert(0, str(ROOT / 'tools'))
from fixers.repair_member_names import ClassFile  # noqa: E402
import importlib.util  # noqa: E402
spec = importlib.util.spec_from_file_location('rv', ROOT / 'tools' / 'gates' / 'replacement_verify.py')
rv = importlib.util.module_from_spec(spec)
try:
    spec.loader.exec_module(rv)
except SystemExit:
    pass

P2 = ROOT / '02b-decompiled'
T = ROOT / '03-deobfuscated'
with zipfile.ZipFile(ROOT / 'RustedWarfare' / 'game-lib.jar') as z:
    stock = {n[:-6]: z.read(n) for n in z.namelist() if n.endswith('.class')}
with zipfile.ZipFile(ROOT / 'build' / '_r104-fix.jar') as z:
    fix = {n[:-6]: z.read(n) for n in z.namelist() if n.endswith('.class')}
A = {k for k in fix if k in stock and fix[k] != stock[k]}
bad, _, _ = rv._v4_scan2({k: (fix[k], stock[k]) for k in A})
bad = sorted(set(bad))

cd = list(csv.DictReader(open(ROOT / 'mappings' / 'class-discoveries.csv', encoding='utf-8', newline='')))
name_of = {(r['obfuscated_package'], r['obfuscated_class']): (r.get('meaningful_name') or '') for r in cd}

print('判不符 = %d' % len(bad))
print()
print('%-44s %-8s %-10s %-10s %s' % ('类', '失败成员', '枚举可推导', '成员可推导', '结论'))
for rel in bad:
    pkg, cls = rel.rsplit('/', 1)
    rd = name_of.get((pkg.replace('/', '.'), cls), '')
    sm = ClassFile(stock[rel]).declared()
    mm = ClassFile(fix[rel]).declared()
    n_fail = (len(set(sm['method']) - set(mm['method'])) + len(set(sm['field']) - set(mm['field'])))
    # 枚举可推导
    p2 = P2 / (rel + '.java')
    enum_pairs = []
    if p2.exists():
        enum_pairs = re.findall(r'(?m)^\s*([A-Za-z_$][\w$]*)\("([^"]*)"\s*,\s*\d+\)',
                                p2.read_text(encoding='utf-8', errors='replace'))
    # 成员可推导（03 源声明的可读名数 ✓，仅作上界 ✓）
    srcs = list(T.rglob(rd + '.java')) if rd else []
    n_decl = 0
    if len(srcs) == 1:
        t = srcs[0].read_text(encoding='utf-8', errors='replace')
        n_decl = len(set(re.findall(r'(?m)^\s*(?:public\s+|private\s+|protected\s+|static\s+|final\s+|'
                                    r'abstract\s+|synchronized\s+)*[\w$<>\[\],.]+\s+([\w$]+)\s*[\(;=]', t)))
    verdict = ('★ 枚举可覆盖 ✓' if len(enum_pairs) >= n_fail and n_fail else
               ('枚举不足 ✗' if enum_pairs else '无枚举 ✗'))
    print('%-44s %-8d %-10d %-10d %s' % (rel.split('rts/')[-1][:44], n_fail, len(enum_pairs), n_decl, verdict))
