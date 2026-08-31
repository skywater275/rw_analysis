#!/usr/bin/env python3
"""
fix_enhanced_for_cast — enhanced-for 泛型擦除修复 (v19.108 Batch 4)

根因: CFR 丢容器泛型 Signature (02 m=AbstractList raw 类型), enhanced-for
元素被当 Object → "Object cannot be converted to X" 报错 (全项目 320+ 处)。

修复: 报错行 `for (X var : container)` → `for (X var : (java.util.Collection<X>) (java.util.Collection) container)`
运行时无操作 (erasure), 语义等价。

Usage:
  python tools/fixers/fix_enhanced_for_cast.py [--apply] [--file 文件名过滤]
"""
import csv
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))

csv.field_size_limit(10 * 1024 * 1024)


def main():
    apply = '--apply' in sys.argv
    filt = None
    if '--file' in sys.argv:
        filt = sys.argv[sys.argv.index('--file') + 1]
    errs = ROOT / 'compile-errors.csv'
    if not errs.exists():
        print('无 compile-errors.csv, 先运行 javac_gate')
        sys.exit(1)
    jobs = {}
    with open(errs, encoding='utf-8') as f:
        for row in csv.reader(f):
            if len(row) < 6 or row[2] != 'error':
                continue
            m = re.search(r'Object cannot be converted to ([A-Za-z_$.][\w$.]*)', row[3])
            if not m:
                continue
            if filt and filt not in row[0]:
                continue
            tgt = m.group(1)
            p = Path(row[0])
            if not p.is_absolute():
                p = ROOT / row[0]
            if not p.exists():
                continue
            ls = p.read_text(encoding='utf-8').split('\n')
            ln = int(row[1])
            if ln > len(ls):
                continue
            src = ls[ln - 1]
            fm = re.search(r'for\s*\(\s*' + re.escape(tgt) + r'\s+\w+\s*:\s*([^)]+)\)', src)
            if not fm:
                continue
            container = fm.group(1).strip()
            jobs.setdefault(row[0], []).append((ln, tgt, container))
    if not jobs:
        print('无待修行')
        return
    total = 0
    for fname, items in jobs.items():
        p = Path(fname)
        if not p.is_absolute():
            p = ROOT / fname
        if not p.exists():
            print(f'[跳过] {fname}')
            continue
        t = p.read_text(encoding='utf-8')
        ls = t.split('\n')
        for ln, tgt, container in sorted(items, reverse=True):
            if ln > len(ls):
                continue
            src = ls[ln - 1]
            cast = (f'(java.util.Collection<{tgt}>) (java.util.Collection) {container}')
            src2 = re.sub(r'for\s*\(\s*' + re.escape(tgt) + r'\s+\w+\s*:\s*[^)]+\)',
                          f'for ({tgt} item : {cast})', src, count=1)
            # 保留原变量名
            vm = re.search(r'for\s*\(\s*' + re.escape(tgt) + r'\s+(\w+)\s*:\s*[^)]+\)', src)
            if not vm:
                continue
            var = vm.group(1)
            src2 = re.sub(r'for\s*\(\s*' + re.escape(tgt) + r'\s+' + var + r'\s*:\s*[^)]+\)',
                          f'for ({tgt} {var} : {cast})', src, count=1)
            if src2 != src:
                ls[ln - 1] = src2
                total += 1
        if apply:
            p.write_text('\n'.join(ls), encoding='utf-8')
        else:
            print(f'[预览] {fname}: {len(items)} 行')
    mode = '应用' if apply else '预览'
    print(f'{mode}: {len(jobs)} 行')
    if not apply:
        print('提示: 加 --apply 实际写入')


if __name__ == '__main__':
    main()
