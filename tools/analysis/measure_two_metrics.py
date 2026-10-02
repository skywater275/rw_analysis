#!/usr/bin/env python3
"""**双度量**测量（度量① 可用类 ✓ · 度量② 编译错误行数 ✓）—— 任何实验的验收依据 ✓。
注意：该脚本里的 jar 名与基线数为**当轮快照** ✓，换轮次时需同步更新 ✓。
用法: python tools/analysis/measure_two_metrics.py
"""
import importlib.util
import re
import sys
import zipfile
from pathlib import Path

ROOT = Path('.').resolve()
sys.path.insert(0, str(ROOT / 'tools'))
spec = importlib.util.spec_from_file_location('rv', ROOT / 'tools' / 'gates' / 'replacement_verify.py')
rv = importlib.util.module_from_spec(spec)
try:
    spec.loader.exec_module(rv)
except SystemExit:
    pass
SUF = re.compile(r'\(\+\d+ 合成\)\s*$')

with zipfile.ZipFile(ROOT / 'RustedWarfare' / 'game-lib.jar') as z:
    stock = {n[:-6]: z.read(n) for n in z.namelist() if n.endswith('.class')}

res, oks = {}, {}
for tag, jar, key in (('A 基线 _r104', '_r104-fix.jar', 'A'), ('B 遮蔽修复 _r146', '_r146-fix.jar', 'B')):
    with zipfile.ZipFile(ROOT / 'build' / jar) as z:
        d = {n[:-6]: z.read(n) for n in z.namelist() if n.endswith('.class')}
    A = {k for k in d if k in stock and d[k] != stock[k]}
    bad, syn, _ = rv._v4_scan2({k: (d[k], stock[k]) for k in A})
    bad = set(bad)
    syn_in = {SUF.sub('', s).strip() for s in syn} & A
    ok = A - bad - syn_in
    res[key] = (len(ok), len(bad), len(A), len(d)); oks[key] = ok
    print('%-16s A类 %-5d 判不符 %-4d 可用类 %-4d' % (tag, len(A), len(bad), len(ok)))
print()
print('★★ 度量① 可用类 %d → %d（%+d）· A 类 %d → %d（%+d）· 判不符 %d → %d'
      % (res['A'][0], res['B'][0], res['B'][0] - res['A'][0],
         res['A'][2], res['B'][2], res['B'][2] - res['A'][2], res['A'][1], res['B'][1]))
print('   新转合格：%s' % sorted(x.split('rts/')[-1] for x in (oks['B'] - oks['A']))[:12])
print('   掉出合格：%s' % sorted(x.split('rts/')[-1] for x in (oks['A'] - oks['B']))[:12])
print('   验收：%s' % ('✅ 通过' if (res['B'][0] >= res['A'][0] and res['B'][3] >= res['A'][3]) else '❌ 未通过'))
ERR = re.compile(r'^(.*?)\.java:(\d+): error: (.*)$')
lines = (ROOT / 'build' / '_ab-errors.txt').read_text(encoding='utf-8', errors='replace').splitlines()
n = sum(1 for l in lines if ERR.match(l.strip()))
print()
print('★★ 度量② 编译错误行 = %d（基线 31,263）⇒ %+d（%+.1f%%）'
      % (n, n - 31263, 100.0 * (n - 31263) / 31263))
