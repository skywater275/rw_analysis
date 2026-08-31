#!/usr/bin/env python3
"""保序对齐映射补全 (v19.110/111) — ProGuard 规律: 方法名按原始声明序分配, 重载共享字母.

v19.111 升级: 改用 javap 方法表 (完整保序) + **参数个数指纹** (混淆不变)
替代 02 CFR 提取 (丢方法致错位噪声, §24).

产出两类映射建议:
  A. 03 已语义化 + 02 字母名未映射 → 新 supplement 条目 (自动补全)
  B. 03 仍短名 + 02 字母名已映射   → 03 应改语义名 (调用点广播候选)

Usage: python tools/fixers/fix_order_align.py [--limit N] [--apply-a]
"""
import csv
import re
import subprocess
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.stdout.reconfigure(encoding='utf-8', errors='replace')
csv.field_size_limit(10 * 1024 * 1024)
JAVAP = r'C:\JDK\oracleJdk-21\bin\javap.exe'


def load_supplement():
    """{(pkg, obf_name): 语义名} 方法映射."""
    m = {}
    for r in csv.reader(open(ROOT / 'mappings/supplement.csv', encoding='utf-8')):
        if r and r[0] == 'method' and len(r) >= 4:
            m[(r[1], r[2])] = r[3]
    return m


def load_class_map():
    """02 pkg.class → 03 语义类名."""
    m = {}
    for r in csv.reader(open(ROOT / 'mappings/class-discoveries.csv', encoding='utf-8')):
        if r and r[0] == 'class' and len(r) >= 4:
            pkg, obf, readable = r[1], r[2], r[3]
            if readable and not readable.startswith('class,'):
                m[(pkg, obf)] = readable
    return m


def javap_methods(pkg02, obf):
    """javap 方法表: [(混淆名, 参数个数)] 保序完整."""
    fq = pkg02 + '.' + obf
    r = subprocess.run([JAVAP, '-p', '-classpath',
                        str(ROOT.parent / 'game-lib.jar'), fq],
                       capture_output=True, text=True, encoding='utf-8',
                       errors='replace', timeout=60)
    out = []
    if r.returncode != 0:
        return out
    for l in r.stdout.splitlines():
        l = l.strip()
        # 跳过字段行与类行
        if '(' not in l or not l.endswith(')') and ';' not in l:
            pass
        m = re.match(r'(?:public|protected|private|static|final|abstract|'
                     r'strictfp|synchronized|native|\s)+[\w<>\[\].$]+ (\w+)\(([^)]*)\)', l)
        if m:
            params = m.group(2).strip()
            arity = 0 if not params else len(params.split(','))
            out.append((m.group(1), arity))
    return out


def methods_of(path):
    """03 方法序列: [(名, 参数个数)]."""
    src = open(path, encoding='utf-8').read()
    out = []
    for m in re.finditer(
        r'public (?:static |final |strictfp |abstract |synchronized |native )*'
        r'[\w<>\[\].$]+ (\w+)\(([^)]*)\)', src):
        params = m.group(2).strip()
        arity = 0 if not params else len(params.split(','))
        out.append((m.group(1), arity))
    return out


def align(m2, m3):
    """双序列对齐: (名, arity) 指纹. 同名+同arity 锚定; 剩余按位置+arity 插值.
    返回 [(02名, 03名, arity, 位置)] 差异对."""
    out = []
    i = j = 0
    n2, n3 = len(m2), len(m3)
    while i < n2 and j < n3:
        a, ar2 = m2[i]
        b, ar3 = m3[j]
        if a == b:
            # 同名锚 (arity 不一致 = 签名漂移信号, 仍前进)
            i += 1
            j += 1
            continue
        # 差异: 保序 1:1 配对 (javap 完整保序 → 直接配对)
        out.append((a, b, ar2, ar3, i))
        i += 1
        j += 1
    # 尾部剩余 (CFR 丢方法只影响 03 提取, javap 侧完整 — 直接配对)
    while i < n2 and j < n3:
        a, ar2 = m2[i]
        b, ar3 = m3[j]
        out.append((a, b, ar2, ar3, i))
        i += 1
        j += 1
    return out


def main():
    limit = None
    for a in sys.argv:
        if a.startswith('--limit'):
            limit = int(a.split('=')[1])
    supp = load_supplement()
    cmap = load_class_map()
    d3 = ROOT / '03-deobfuscated'
    idx3 = {}
    for p in d3.rglob('*.java'):
        idx3[p.stem] = p

    results_a = []  # (02fq, obf名, 03语义名, 位置)
    results_b = []  # (02fq, obf名, 已有语义名, 03名, 位置)
    pairs = skipped = 0
    for (pkg02, obf), readable in sorted(cmap.items()):
        if '.' in obf or '$' in obf:
            continue
        p3 = idx3.get(readable)
        if not p3:
            continue
        m2 = javap_methods(pkg02, obf)
        m3 = methods_of(p3)
        if not m2 or not m3:
            continue
        # arity 指纹: 差异对中 arity 必须一致 (混淆不变) 才算可信
        pairs += 1
        if limit and pairs > limit:
            break
        for a, b, ar2, ar3, pos in align(m2, m3):
            if ar2 != ar3:
                skipped += 1
                continue
            key = (pkg02, a)
            semantic = supp.get(key)
            # B 类过滤: semantic 为签名条目 (含'(') 或过时名 — 需签名仲裁, 本轮仅列出
            if (semantic and '(' not in semantic and b != semantic
                    and b not in ('values', 'valueOf', '<init>')):
                results_b.append((pkg02 + '.' + obf, a, semantic, readable, b, pos))
            elif (not semantic and len(b) > 2 and not b.startswith('$')
                  and len(a) <= 2):
                results_a.append((pkg02 + '.' + obf, a, b, pos))
    # 去重 + Object 方法噪声过滤: 同 (pkg, obf名) 语义名唯一才可信 (重载组分裂为噪声)
    from collections import defaultdict
    sem_by_key = defaultdict(set)
    for fq, obfm, sem, pos in results_a:
        sem_by_key[(fq.rsplit('.', 1)[0], obfm)].add(sem)
    uniq = [(fq, obfm, sem, pos) for fq, obfm, sem, pos in results_a
            if len(sem_by_key[(fq.rsplit('.', 1)[0], obfm)]) == 1
            and sem not in ('toString', 'hashCode', 'equals', 'clone', 'finalize', 'getClass')]
    print(f'配对类: {pairs}, arity 不符跳过: {skipped}')
    print(f'A 类建议 (语义名唯一+去Object噪声): {len(uniq)} (原始 {len(results_a)})')
    for fq, obfm, sem, pos in uniq[:25]:
        print(f'  {fq}: {obfm} → {sem} (位置{pos})')
    print(f'B 类建议 (03 应改语义名): {len(results_b)}')
    for fq, obfm, sem, readable, cur, pos in results_b[:10]:
        print(f'  {fq}: {obfm} 已映射 {sem}, 03 {readable}.{cur} 位置{pos} 待广播')
    if '--apply-a' in sys.argv and uniq:
        with open(ROOT / 'mappings/supplement.csv', 'a', encoding='utf-8', newline='') as f:
            w = csv.writer(f)
            for fq, obfm, sem, pos in uniq:
                pkg02 = fq.rsplit('.', 1)[0]
                w.writerow(['method', pkg02, obfm, sem,
                            f'v19.111 保序对齐+arity指纹: javap方法表↔03声明 (位置{pos})'])
        print(f'已写入 supplement: {len(uniq)} 条')
    return 0


if __name__ == '__main__':
    sys.exit(main())
