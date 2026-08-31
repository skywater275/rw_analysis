#!/usr/bin/env python3
"""分析可批量 patch 的 03 文件候选 (v19.109 运行时管线).

条件: 0 编译错误 + class-discoveries 有 02 反向映射 + 方法名全短名(≤2字母).
输出: 候选列表 (03路径 / 03类名 / 02目标路径 / 方法数)
Usage: python tools/fixers/analyze_patch_candidates.py
"""
import csv
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
csv.field_size_limit(10 * 1024 * 1024)

err_files = set()
for r in csv.reader(open(ROOT / 'compile-errors.csv', encoding='utf-8')):
    if r[0].startswith('03-deobfuscated/'):
        err_files.add(r[0])

# 03 类名 → 02 目标路径
mapping = {}
for r in csv.reader(open(ROOT / 'mappings/class-discoveries.csv', encoding='utf-8')):
    if not r or r[0] != 'class' or len(r) < 4:
        continue
    pkg, obf, readable = r[1], r[2], r[3]
    if not readable or readable.startswith('class,'):
        continue
    if obf == readable:
        continue
    mapping[readable] = f"{pkg}/{obf}"

candidates = []
for java in (ROOT / '03-deobfuscated').rglob('*.java'):
    rel = str(java).replace('\\', '/')
    if rel in err_files:
        continue
    src = java.read_text(encoding='utf-8', errors='ignore')
    m = re.search(r'(?:public |final |abstract |strictfp )*(?:class|enum|interface) (\w+)', src)
    if not m:
        continue
    cls = m.group(1)
    if cls not in mapping:
        continue
    methods = re.findall(
        r'(?:public|protected|private)\s+(?:static\s+)?(?:final\s+)?(?:strictfp\s+)?[\w<>\[\].]+\s+(\w+)\s*\(',
        src)
    if not methods:
        continue
    if not all(len(x) <= 2 for x in methods):
        continue
    # 字段短名检查: [type] [name]; 形态
    keywords = {'if','for','while','return','new','switch','case','break','continue',
                'catch','finally','try','throw','synchronized','class','interface',
                'extends','implements','import','package','public','private','protected',
                'static','final','this','super','true','false','null','int','long','float',
                'double','boolean','byte','char','short','void','strictfp','volatile','transient'}
    fields = re.findall(r'(?:public|protected|private|static|final|transient|volatile|strictfp|\s)+([\w<>\[\].]+)\s+(\w+)\s*(?:=|;|,|\)|$)',
                        src)
    short_fields = True
    for _t, _n in fields:
        if _n in keywords:
            continue
        if len(_n) > 2:
            short_fields = False
            break
    if short_fields:
        candidates.append((rel, cls, mapping[cls], len(methods)))

print(f"候选数: {len(candidates)}")
for c in sorted(candidates, key=lambda x: x[0]):
    print(f"  {c[0][len('03-deobfuscated/'):]:60s} {c[1]:20s} -> {c[2]}  ({c[3]}方法)")
