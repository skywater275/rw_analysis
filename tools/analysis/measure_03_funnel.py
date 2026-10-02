#!/usr/bin/env python3
"""交付漏斗：1,698 原版类 → 多少能由 `03` 逆向编译进交付物。

Usage:
    python tools/analysis/measure_03_funnel.py [--json build/_03-funnel.json]

阶段（每一阶段都是**上一阶段的子集**，逐级交代损失）：
  S0  原版类总数（`{RW}/jars/stock.jar`）
  S1  有类映射（`build/03-adapted-map.csv` 有 stock_path 行）
  S2  其 `src03_path` 源文件**确实存在**于 `03-deobfuscated`
  S3  未被 `build-skip.txt` 跳过
  S4  该源**不在** javac_gate 的编译错误表内（`compile-errors.csv`）
  S5  交付物中**同名且字节与原版不同**   ← 本目标「03 对游戏核心类的逆向编译」的达成口径

只读，不写产物（除 `--json`）。
"""
import argparse
import csv
import json
import sys
import zipfile
from collections import Counter
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT / 'tools'))
csv.field_size_limit(10 * 1024 * 1024)

STOCK = ROOT.parent / 'jars' / 'stock.jar'
PROD = ROOT / 'build' / 'game-lib-reverse.jar'
BRIDGE = ROOT / 'build' / '03-adapted-map.csv'
DEOB = ROOT / '03-deobfuscated'
SKIP_CANDS = [ROOT / 'build-skip.txt', ROOT / 'build' / 'build-skip.txt',
              ROOT.parent / 'build-skip.txt']
ERRS = ROOT / 'compile-errors.csv'

NON_GAME = ('com/codedisaster/steamworks/', 'com/corrodinggames/rts/java/audio/a/',
            'org/a/a/', 'a/a/', 'com/a/a/', 'android/', 'network/reliableudp/')


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--json', default='')
    args = ap.parse_args()

    with zipfile.ZipFile(STOCK) as z:
        s0 = sorted(n for n in z.namelist() if n.endswith('.class'))
    with zipfile.ZipFile(PROD) as z:
        prod = {n: z.read(n) for n in z.namelist() if n.endswith('.class')}
    with zipfile.ZipFile(STOCK) as z:
        stock = {n: z.read(n) for n in z.namelist() if n.endswith('.class')}

    bridge = {}
    with open(BRIDGE, encoding='utf-8-sig', newline='') as f:
        for r in csv.DictReader(f):
            bridge[r['stock_path']] = r['src03_path']

    skip = set()
    for p in SKIP_CANDS:
        if p.exists():
            for ln in p.read_text(encoding='utf-8', errors='replace').splitlines():
                ln = ln.strip()
                if ln and not ln.startswith('#'):
                    skip.add(ln.replace('\\', '/'))
            break

    err_files = set()
    if ERRS.exists():
        with open(ERRS, encoding='utf-8', newline='') as f:
            for r in csv.DictReader(f):
                err_files.add((r.get('file') or '').replace('\\', '/'))

    cur = list(s0)
    stages = []
    stages.append(('S0 原版类', len(cur)))

    def step(name, pred):
        nonlocal cur
        cur = [n for n in cur if pred(n)]
        stages.append((name, len(cur)))

    step('S1 有类映射', lambda n: n[:-6] in bridge)
    step('S2 03 源文件存在',
         lambda n: (DEOB / (bridge[n[:-6]] + '.java')).exists())
    step('S3 未被 build-skip 跳过',
         lambda n: n[:-6] not in skip and bridge[n[:-6]] not in skip
         and bridge[n[:-6]] + '.java' not in skip)
    step('S4 不在编译错误表内',
         lambda n: ('03-deobfuscated/%s.java' % bridge[n[:-6]]) not in err_files)
    step('S5 交付物中已换字节',
         lambda n: n in prod and prod[n] != stock[n])

    game = [n for n in cur if not n.startswith(NON_GAME)]
    res = dict(阶段=[dict(阶段=k, 类数=v) for k, v in stages],
               交付且可溯源=len(cur), 其中游戏本体=len(game),
               原版类总数=len(s0),
               可溯源占比=round(100.0 * len(cur) / len(s0), 2),
               游戏本体占比=round(100.0 * len(game) / len(s0), 2))
    print(json.dumps(res, ensure_ascii=False, indent=2))

    # S5 之后剩下没进的，按原因归类
    lost = [n for n in s0 if n not in set(cur)]
    c = Counter()
    for n in lost:
        if n[:-6] not in bridge:
            c['无类映射'] += 1
        elif not (DEOB / (bridge[n[:-6]] + '.java')).exists():
            c['03 源文件缺失'] += 1
        elif (n[:-6] in skip or bridge[n[:-6]] in skip
              or bridge[n[:-6]] + '.java' in skip):
            c['build-skip'] += 1
        elif ('03-deobfuscated/%s.java' % bridge[n[:-6]]) in err_files:
            c['编译错误'] += 1
        else:
            c['★ 有源、可编译，但交付物未换字节'] += 1
    print('\n未达成 %d 的原因分布：' % len(lost))
    for k, v in c.most_common():
        print('  %-38s %5d' % (k, v))
    res['未达成原因'] = dict(c)
    res['未达成样例'] = lost[:20]

    if args.json:
        Path(args.json).write_text(json.dumps(res, ensure_ascii=False, indent=2),
                                   encoding='utf-8')
        print('\n→ %s' % args.json)
    return 0


if __name__ == '__main__':
    sys.exit(main())
