#!/usr/bin/env python3
"""
fix_method_pair_generic — 通用 02/03 方法四层指纹配对器 (v19.108 Batch 3)

复用 PlayerState 管线模式, 参数化类对:
  1. 字符串字面量重合 ≥2
  2. 字面量重合 ≥1
  3. 归一化 token 流 LCS 相似 ≥0.55 (无字面量方法)
  4. 行号线性回归插值 (<90 行)

输出: 02名+参数个数 → 03名 映射 (名字不同才记录) + CSV 覆盖统计。

Usage:
  python tools/fixers/fix_method_pair_generic.py <02路径> <03路径> [--apply-fix <csv符号过滤>]
  例: python tools/fixers/fix_method_pair_generic.py \
        com/corrodinggames/rts/game/units/custom/ag.java \
        com/corrodinggames/rts/game/units/custom/ModLoader.java --analyze
"""
import csv
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import DEOBFUSCATED_DIR, DECOMPILED_DIR

csv.field_size_limit(10 * 1024 * 1024)

KW = {'if', 'for', 'while', 'switch', 'catch', 'new', 'return', 'throw',
      'else', 'do', 'case'}


def norm_body(body):
    b = re.sub(r'"([^"\\]|\\.)*"', '""', body)
    b = re.sub(r"'([^'\\]|\\.)*'", "''", b)
    b = re.sub(r'/\*.*?\*/', ' ', b, flags=re.S)
    b = re.sub(r'//[^\n]*', ' ', b)
    return re.findall(r'[A-Za-z_$][A-Za-z0-9_$]*|\d+', b)


def extract(src):
    out = []
    src2 = re.sub(r'/\*.*?\*/', ' ', src, flags=re.S)
    src2 = re.sub(r'//[^\n]*', ' ', src2)
    for m in re.finditer(
            r'\b([a-zA-Z_$][a-zA-Z0-9_$]*)\s*\(([^(){}]*)\)\s*(?:throws\s+[\w.,\s$]+)?\{',
            src2):
        nm = m.group(1)
        if nm in KW or nm == 'super' or nm == 'this':
            continue
        args = m.group(2).strip()
        nargs = 0 if not args else len(args.split(','))
        depth, i = 1, m.end()
        while i < len(src2) and depth > 0:
            if src2[i] == '{':
                depth += 1
            elif src2[i] == '}':
                depth -= 1
            i += 1
        body = src2[m.end():i - 1]
        lits = set(re.findall(r'"([^"\\]{3,})"', body))
        toks = norm_body(body)
        line = src2[:m.start()].count('\n') + 1
        out.append({'n': nm, 'a': nargs, 'lits': lits, 'toks': toks, 'line': line})
    return out


def sim(a, b):
    if not a or not b:
        return 0.0
    m, n = len(a), len(b)
    if m * n > 40000:
        sa, sb = set(a), set(b)
        return 2.0 * len(sa & sb) / max(1, len(sa) + len(sb))
    dp = [0] * (n + 1)
    for i in range(1, m + 1):
        prev = 0
        for j in range(1, n + 1):
            tmp = dp[j]
            if a[i - 1] == b[j - 1]:
                dp[j] = prev + 1
            elif dp[j] < dp[j - 1]:
                dp[j] = dp[j - 1]
            prev = tmp
    return 2.0 * dp[n] / (m + n)


def build_map(p02, p03):
    m02 = extract(p02.read_text(encoding='utf-8'))
    m03 = extract(p03.read_text(encoding='utf-8'))
    used = set()
    pairs = {}
    # 轮1: 字面量 >= 2
    for i, x in enumerate(m02):
        if not x['lits']:
            continue
        best, bestscore = None, 0
        for j, y in enumerate(m03):
            if j in used or x['a'] != y['a']:
                continue
            inter = len(x['lits'] & y['lits'])
            if inter > bestscore:
                best, bestscore = j, inter
        if best is not None and bestscore >= 2:
            pairs[i] = (best, bestscore, 'lit2')
            used.add(best)
    # 轮2: 字面量 >= 1
    for i, x in enumerate(m02):
        if i in pairs or not x['lits']:
            continue
        best, bestscore = None, 0
        for j, y in enumerate(m03):
            if j in used or x['a'] != y['a']:
                continue
            inter = len(x['lits'] & y['lits'])
            if inter > bestscore:
                best, bestscore = j, inter
        if best is not None and bestscore >= 1:
            pairs[i] = (best, bestscore, 'lit1')
            used.add(best)
    # 轮3: token 相似
    for i, x in enumerate(m02):
        if i in pairs or not x['toks']:
            continue
        best, bestscore = None, 0.0
        for j, y in enumerate(m03):
            if j in used or x['a'] != y['a']:
                continue
            s = sim(x['toks'], y['toks'])
            if s > bestscore:
                best, bestscore = j, s
        if best is not None and bestscore >= 0.55:
            pairs[i] = (best, bestscore, 'tok')
            used.add(best)
    # 轮4: 行号回归
    anchors = [(m02[i]['line'], m03[j]['line'])
               for i, (j, _, _) in pairs.items()]
    if len(anchors) >= 3:
        xs = [a[0] for a in anchors]
        ys = [a[1] for a in anchors]
        xm = sum(xs) / len(xs)
        slope = (sum((x - xm) * y for x, y in anchors) /
                 max(1e-9, sum((x - xm) ** 2 for x in xs)))
        inter = sum(ys) / len(ys) - slope * xm
        for i, x in enumerate(m02):
            if i in pairs:
                continue
            pred = slope * x['line'] + inter
            best, bestdist = None, 1e9
            for j, y in enumerate(m03):
                if j in used or x['a'] != y['a']:
                    continue
                d = abs(y['line'] - pred)
                if d < bestdist:
                    best, bestdist = j, d
            if best is not None and bestdist < 90:
                pairs[i] = (best, bestdist, 'line')
                used.add(best)
    mp = {}
    for i, (j, s, tier) in pairs.items():
        n2, a2 = m02[i]['n'], m02[i]['a']
        n3 = m03[j]['n']
        if n2 != n3:
            mp[(n2, a2)] = n3
    return mp, (len(m02), len(m03), len(pairs), m02, m03, pairs)


def main():
    if len(sys.argv) < 3:
        print(__doc__)
        sys.exit(1)
    p02 = (DECOMPILED_DIR / sys.argv[1])
    p03 = (DEOBFUSCATED_DIR / sys.argv[2])
    if not p02.exists() or not p03.exists():
        print(f'文件不存在: {p02} / {p03}')
        sys.exit(1)
    mp, stats = build_map(p02, p03)
    n02, n03, np, _, _, _ = stats
    print(f'02 {p02.name}: {n02} 方法 | 03 {p03.name}: {n03} 方法 | 配对: {np}')
    print(f'映射 (02名,参数个数 -> 03名) {len(mp)} 条:')
    for (nm, na), new in sorted(mp.items()):
        print(f'  {nm}({na}参) -> {new}')
    if '--fix-callsites' in sys.argv:
        fix_callsites(mp, p03)
    sys.exit(0)


def fix_callsites(mp, p03):
    """CSV 驱动调用点修复 (宿主=03 类名, 同文件优先)"""
    errs = ROOT / 'compile-errors.csv'
    if not errs.exists():
        print('无 compile-errors.csv')
        return
    cls = p03.stem
    jobs = {}
    with open(errs, encoding='utf-8') as f:
        for row in csv.reader(f):
            if len(row) < 6 or row[2] != 'error':
                continue
            if cls not in row[5]:
                continue
            m = re.match(r'^([a-zA-Z_$][a-zA-Z0-9_$]*)\((.*)\)$', row[4])
            if not m:
                continue
            args = m.group(2).strip()
            nargs = 0 if not args else len(args.split(','))
            new = mp.get((m.group(1), nargs))
            if not new:
                continue
            fname, line = row[0], int(row[1])
            jobs.setdefault(fname, []).append((line, m.group(1), new))
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
        for line, old, new in sorted(items, reverse=True):
            if line > len(ls):
                continue
            ln = ls[line - 1]
            n = len(re.findall(r'\.' + re.escape(old) + r'\(', ln))
            ln2 = re.sub(r'\.' + re.escape(old) + r'\(', '.' + new + '(', ln)
            if ln2 != ln:
                ls[line - 1] = ln2
                total += n
        p.write_text('\n'.join(ls), encoding='utf-8')
    print(f'调用点修复: {len(jobs)} 行 {total} 处')


if __name__ == '__main__':
    main()
