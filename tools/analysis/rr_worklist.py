#!/usr/bin/env python3
"""判不符类的**分类工单**（A 无 03 源 / B 类名撞车 / C 真的缺方法 / D 名字未改 ✓）。
用法: python tools/analysis/rr_worklist.py
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

with zipfile.ZipFile(ROOT / 'RustedWarfare' / 'game-lib.jar') as z:
    stock = {n[:-6]: z.read(n) for n in z.namelist() if n.endswith('.class')}
with zipfile.ZipFile(ROOT / 'build' / '_r104-fix.jar') as z:
    fix = {n[:-6]: z.read(n) for n in z.namelist() if n.endswith('.class')}
A = {k for k in fix if k in stock and fix[k] != stock[k]}
bad, _, _ = rv._v4_scan2({k: (fix[k], stock[k]) for k in A})
bad = sorted(set(bad))

P2 = ROOT / '02b-decompiled'
cd = list(csv.DictReader(open(ROOT / 'mappings' / 'class-discoveries.csv', encoding='utf-8', newline='')))
name_of = {(r['obfuscated_package'], r['obfuscated_class']): (r.get('meaningful_name') or '') for r in cd}
T = ROOT / '03-deobfuscated'

A_, B_, C_, D_ = [], [], [], []
for rel in bad:
    pkg, cls = rel.rsplit('/', 1)
    readable = name_of.get((pkg.replace('/', '.'), cls), '')
    srcs = list(T.rglob(readable + '.java')) if readable else []
    src_lines = len(srcs[0].read_text(encoding='utf-8', errors='replace').splitlines()) if len(srcs) == 1 else 0
    sm = {(n, d) for n, d in ClassFile(stock[rel]).declared()['method']}
    mm = {(n, d) for n, d in ClassFile(fix[rel]).declared()['method']}
    s_ms = {n for n, _ in sm} - {'<init>', '<clinit>'}
    m_ms = {n for n, _ in mm} - {'<init>', '<clinit>'}
    # 02b 侧方法名
    p2 = P2 / (rel + '.java')
    p2_ms = set()
    if p2.exists():
        t = p2.read_text(encoding='utf-8', errors='replace')
        p2_ms = {m.group(1) for m in re.finditer(r'(?m)^\s*(?:public\s+|private\s+|protected\s+|static\s+|'
                                                 r'final\s+|abstract\s+|synchronized\s+|native\s+)*'
                                                 r'[\w$<>\[\].]+\s+([\w$]+)\s*\(', t)} - {'if', 'while', 'for', 'switch', 'catch'}
        # ★★ 第 72 轮修正：**排除构造器** ✗ —— 形如 `private t(String,int) {}`（类名 == "方法名" ✓）
        #   实测：`game/units/a/t` 与 `gameFramework/k/k` 的"缺方法"全是**构造器误判** ✗✓
        p2_ms -= {cls, cls.split('$')[-1]}
    miss_by_02b = sorted(p2_ms - m_ms)
    # ★ 第 72 轮细分：02b 有而我方无的这个名，是**真的缺** ✗ 还是**改了名** ✗？
    #   判据：我方是否存在**同描述符、不同名**的方法 ✓ ⇒ 有 = 改名 ✗（属 D 类机制问题）
    #         （`gameFramework/e/a` 实测：stock 的 3 个 `c` ✓ 我方是 `reset`/`getString2`/`isEnabled3` ✗ 同一描述符 ✓）
    renamed = False
    for nm in miss_by_02b:
        ds = {d for n, d in ClassFile(stock[rel]).declared()['method'] if n == nm}
        if ds & {d for n, d in ClassFile(fix[rel]).declared()['method'] if n != nm}:
            renamed = True
    if src_lines == 0:
        A_.append((rel, '无 03 源 ✗（R8 扁平化）'))
    elif not (s_ms & m_ms):
        B_.append((rel, '我方产物与 stock 方法名完全不相交 ⇒ 疑似类名撞车 ✗'))
    elif miss_by_02b and not renamed:
        C_.append((rel, '**真的缺** 02b 有而我方无：%s ✓ 可补' % miss_by_02b[:6]))
    elif miss_by_02b and renamed:
        D_.append((rel, '改名 ✗（同描述符已在，只是没改名）：%s' % miss_by_02b[:4]))
    else:
        D_.append((rel, '结构近似但成员名不同（歧义丢弃 ✗）'))
print('判不符 = %d ⇒ 分类工单（基线 `_r104-fix.jar`）' % len(bad))
print()
for tag, lst, note in (('A 无 03 源（不可改源 ✗）', A_, ''),
                       ('B 类名撞车（结构性 ✗）', B_, ''),
                       ('C 缺方法（**可从 02b 补** ✓）', C_, '★ 优先'),
                       ('D 名字未改（机制问题 ✗）', D_, '')):
    print('=== %s —— %d 个 %s' % (tag, len(lst), note))
    for rel, why in lst:
        print('   %-46s %s' % (rel.split('rts/')[-1][:46], why[:64]))
    print()
