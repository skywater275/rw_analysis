# -*- coding: utf-8 -*-
"""剩余错误总清单生成器: compile-errors.csv → docs/deobfuscation/剩余错误总清单-5283.md"""
import csv, collections, io, sys
sys.stdout.reconfigure(encoding='utf-8', errors='replace')

from pathlib import Path
ROOT = Path(__file__).resolve().parents[2]
CSV = str(ROOT / 'compile-errors.csv')
OUT = str(ROOT / 'docs' / 'deobfuscation' / '剩余错误总清单-5283.md')

cnt = collections.Counter()          # 文件 → 错误数
msg = collections.Counter()          # message 分类
syms = collections.Counter()         # symbol
by_file_sym = collections.defaultdict(collections.Counter)  # 文件 → symbol 计数
by_file_msg = collections.defaultdict(collections.Counter)  # 文件 → message 计数
domains = collections.Counter()      # 包域 → 错误数

with open(CSV, encoding='utf-8') as f:
    for row in csv.reader(f):
        if len(row) < 5 or not row[0].startswith('03-'):
            continue
        fn = row[0].replace('03-deobfuscated/', '')
        cnt[fn] += 1
        m = row[3]
        # 归类 message
        if 'cannot find symbol' in m: k = 'cannot find symbol'
        elif 'incompatible types' in m: k = 'incompatible types'
        elif 'no suitable method found' in m: k = 'no suitable method'
        elif 'method does not override' in m: k = 'method does not override/implement'
        elif 'cannot override' in m: k = 'cannot override (返回类型/可见性冲突)'
        elif 'not abstract and does not override' in m: k = '抽象方法未实现'
        elif 'private access' in m: k = 'private access'
        elif 'already defined' in m: k = '重复定义'
        elif 'package' in m and 'does not exist' in m: k = '包不存在'
        elif 'for-each not applicable' in m: k = 'for-each 不适用'
        elif 'dereferenced' in m: k = '不可解引用'
        elif 'array required' in m: k = '数组操作错误'
        elif 'lossy conversion' in m: k = '精度丢失转换'
        elif 'incomparable types' in m: k = '不可比较类型'
        elif 'cannot be applied' in m: k = '参数不匹配'
        elif 'not public' in m: k = '可见性不足'
        elif 'static context' in m: k = '静态上下文引用'
        elif 'cannot be accessed directly' in m: k = '抽象方法直接访问'
        else: k = '其他: ' + m[:40]
        msg[k] += 1
        by_file_msg[fn][k] += 1
        s = row[4].strip()
        if s:
            syms[s] += 1
            by_file_sym[fn][s] += 1
        # 域
        dom = fn.split('/')[1] if fn.count('/') >= 1 else fn
        if dom == 'game': dom = 'game/' + fn.split('/')[2]
        domains[dom] += 1

TOTAL = sum(cnt.values())

def sym_str(c, n=6):
    top = [s for s, _ in c.most_common(n) if s]
    return ', '.join(top) if top else '—'

lines = []
def A(s=''):
    lines.append(s)
A('# 剩余错误总清单 (5,283)')
A()
A('> 生成: v19.129 anim/a 战役后 | 数据源: `compile-errors.csv` (javac_gate 全量实测) | 口径: 41,402 → **5,613** (-86.4%)')
A('> 本清单为**唯一错误聚合入口**: 按文件分组 → 按类型归类 → 按符号频率排序, 供后续战役直接引用。')
A()
A('## 1. 总量与类型分布')
A()
A(f'- 总错误: **{TOTAL}** (文件数: {len(cnt)})')
A('- 错误类型分布 (Top 15):')
A()
A('| 类型 | 数量 | 占比 |')
A('|------|------|------|')
for k, n in msg.most_common(15):
    A(f'| {k} | {n} | {n*100//TOTAL}% |')
A()
A('## 2. 高频符号 (混淆名残留 = 战役目标)')
A()
A('| 符号 | 次数 | 说明/猜测 |')
A('|------|------|----------|')
hints = {
    'b': '02b 方法/字段混淆名', 'a': '02b 方法/字段混淆名', 'n': 'PlayerState 简写?', 'f': '02b 方法名',
    'c': '02b 字段', 'j': '02b 类/字段', 'am': 'UnitInstance 残留', 'd': '02b 字段',
    'k': '02b 字段/类', 'b()': '02b 方法调用', 't': '02b 类 (ActionCategory?)', 'ScriptEngine': 'java 包缺失类',
    'q()': '02b 方法调用', 'g': '02b 字段', 'e': '02b 字段', 'a()': '02b 方法调用',
    'i': '02b 类/字段', 'l': '02b 类 (ModUnitRegistry?)', 'u': '02b 类 (ActionTargetType?)', 'h': '02b 类',
    'ar': 'UnitRegistry 残留', 'p': '02b 字段', 'm': '02b 字段', 'ai': 'InputProvider 残留',
    'q': '02b 字段', 'd()': '02b 方法调用', 'e(String)': 'GlobalState 日志?', 'InputAxis': 'appFramework 缺失', 'w': '02b 类', 'v': '02b 字段',
}
for s, n in syms.most_common(30):
    A(f'| `{s}` | {n} | {hints.get(s, "")} |')
A()
A('## 3. 文件分组清单 (按错误数降序, 全部文件)')
A()
A('| # | 错误数 | 文件 | 主要 symbol |')
A('|---|--------|------|-------------|')
for i, (fn, n) in enumerate(cnt.most_common(), 1):
    A(f'| {i} | {n} | `{fn}` | {sym_str(by_file_sym[fn])} |')
A()
A('## 4. 按包域聚合')
A()
A('| 域 | 错误数 | 文件数 |')
A('|-----|--------|--------|')
dom_files = collections.Counter()
for fn in cnt: dom_files[fn.split('/')[1]] += 1
for d, n in domains.most_common():
    A(f'| {d} | {n} | {dom_files[d.split("/")[0]]} |')
A()
A('## 5. 大文件战役优先级 (≥30 错误)')
A()
A('| 错误数 | 文件 | 类型画像 |')
A('|--------|------|----------|')
for fn, n in cnt.most_common():
    if n >= 30:
        ms = by_file_msg[fn].most_common(3)
        profile = '; '.join(f'{k}×{v}' for k, v in ms)
        A(f'| {n} | `{fn}` | {profile} |')
A()
A('---')
A('*本清单由 `tools/utils/gen_error_list.py` 生成, 每次 javac_gate 后可重新生成覆盖。*')

with io.open(OUT, 'w', encoding='utf-8', newline='') as f:
    f.write('\n'.join(lines))
print('生成:', OUT, f'({TOTAL} 错误, {len(cnt)} 文件)')
