#!/usr/bin/env python3
"""03 缺失方法补全器 (v19.111, P5) — 02b(FernFlower 完整) 方法体移植到 03.

背景: CFR 03 侧丢方法 (UnitType 缺 96 个 jar 方法: 大写 B-Z + 双字母组);
02b 方法表与 javap 一致 (FF 67/67 vs CFR 30/67 铁证) → 02b 为移植源.
类型翻译: 混淆类型 → 03 语义类型 (class-discoveries TYPE_MAP).

Usage: python tools/fixers/fix_03_fill_missing.py --class UnitType [--apply] [--limit N]
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

# 03 语义类型 → 02 混淆简单名 (class-discoveries)
TYPE_MAP = {}
for r in csv.reader(open(ROOT / 'mappings/class-discoveries.csv', encoding='utf-8')):
    if r and r[0] == 'class' and len(r) >= 4:
        pkg, obf, readable = r[1], r[2], r[3]
        if readable and not readable.startswith('class,') and len(obf) <= 2:
            TYPE_MAP.setdefault(readable, obf)

# 02 混淆简单名 → 03 语义名 (反向)
OBF_TO_SEM = {}
for r in csv.reader(open(ROOT / 'mappings/class-discoveries.csv', encoding='utf-8')):
    if r and r[0] == 'class' and len(r) >= 4:
        pkg, obf, readable = r[1], r[2], r[3]
        if readable and not readable.startswith('class,'):
            OBF_TO_SEM.setdefault(obf, readable)

# 常用 JDK 类型
JDK = {'String', 'Object', 'int', 'float', 'boolean', 'long', 'double', 'short',
       'byte', 'char', 'void', 'PointF', 'Point', 'Rect', 'RectF', 'Paint',
       'ArrayList', 'File', 'InputStream', 'OutputStream'}


def javap_names(fq):
    """[(方法名, arity)] javap 全集."""
    r = subprocess.run([JAVAP, '-p', '-classpath', str(ROOT.parent / 'game-lib.jar'), fq],
                       capture_output=True, text=True, encoding='utf-8', errors='replace',
                       timeout=60)
    out = []
    if r.returncode != 0:
        return out
    for l in r.stdout.splitlines():
        m = re.match(r'\s*(?:public|protected|private|static|final|abstract|strictfp|'
                     r'synchronized|native|\s)+[\w<>\[\].$]+ (\w+)\(([^)]*)\)', l)
        if m:
            params = m.group(2).strip()
            arity = 0 if not params else len(params.split(','))
            out.append((m.group(1), arity))
    return out


def methods03(path):
    """[(名, arity)] 03 现有."""
    src = path.read_text(encoding='utf-8', errors='ignore')
    out = []
    for m in re.finditer(r'(?:public|protected|private|static|final|abstract|strictfp|\s)+'
                         r'[\w<>\[\].$]+ (\w+)\(([^)]*)\)', src):
        params = m.group(2).strip()
        arity = 0 if not params else len(params.split(','))
        out.append((m.group(1), arity))
    return out


def extract_body(src, sig_match, start):
    """从 02b 文本提取方法体 (签名行 + 括号配对, 从给定位置开始 —
    防 src.find 重新从头找导致同名签名错位, v19.111 实测 ae() 吞 static 块)."""
    idx = start
    if idx < 0:
        return None
    open_idx = src.find('{', idx)
    if open_idx < 0:
        return None
    depth = 0
    i = open_idx
    while i < len(src):
        if src[i] == '{':
            depth += 1
        elif src[i] == '}':
            depth -= 1
            if depth == 0:
                return src[idx:i + 1]
        i += 1
    return None


def translate_types(code, import_obf):
    """类型翻译: 默认 no-op 保留 02b 短名 (GameUtils 先例; gate 驱动逐个映射).
    教训: 全局/import 级翻译都会错翻 (bh→CrashHandler、aD→ShaderProgram, v19.111 实测) —
    单字母混淆名在不同包是不同类, 源码级翻译不可靠."""
    return code


def imports_of(src02b):
    """02b 文件的 import 混淆类名集合."""
    out = set()
    for m in re.finditer(r'import [\w.]+;', src02b):
        last = m.group(0).rsplit('.', 1)[1].rstrip(';')
        if len(last) <= 2:
            out.add(last)
    return out


def main():
    cls = None
    apply = '--apply' in sys.argv
    limit = None
    for idx, a in enumerate(sys.argv):
        if a == '--class' and idx + 1 < len(sys.argv):
            cls = sys.argv[idx + 1]
        if a.startswith('--limit='):
            limit = int(a.split('=', 1)[1])
    if not cls:
        print('用法: --class UnitType')
        return 1
    # 02 目标 (混淆名)
    pkg02, obf02 = None, None
    for r in csv.reader(open(ROOT / 'mappings/class-discoveries.csv', encoding='utf-8')):
        if r and r[0] == 'class' and r[3] == cls:
            pkg02, obf02 = r[1], r[2]
    if not pkg02:
        print(f'{cls} 无类映射')
        return 1
    p03 = None
    for p in (ROOT / '03-deobfuscated').rglob(cls + '.java'):
        p03 = p
        break
    p02b = ROOT / '02b-decompiled' / pkg02.replace('.', '/') / (obf02 + '.java')
    if not p03 or not p02b.exists():
        print(f'03 或 02b 文件缺失: {p03} / {p02b}')
        return 1
    have = set(methods03(p03))
    want = javap_names(pkg02 + '.' + obf02)
    missing = [(n, a) for n, a in want if (n, a) not in have]
    print(f'{cls}: javap {len(want)} 方法, 03 有 {len(have)}, 缺 {len(missing)}')
    src2b = p02b.read_text(encoding='utf-8', errors='ignore')
    import_obf = imports_of(src2b)
    n_ok = 0
    fills = []
    for name, arity in missing:
        if limit and n_ok >= limit:
            break
        # 按 arity 匹配签名 (重载同名: 提取正确的参数个数版本)
        found = None
        for m in re.finditer(r'(?:public|protected|private|static|final|abstract|strictfp|\s)+'
                             r'[\w<>\[\].$]+ ' + re.escape(name) + r'\(([^)]*)\)', src2b):
            params = m.group(1).strip()
            a = 0 if not params else len(params.split(','))
            if a == arity:
                found = m
                break
        if not found:
            continue
        body = extract_body(src2b, found.group(0), found.start())
        if not body:
            continue
        # 质量校验: body 首个非空行必须是签名行 (含方法名+括号) — 否则提取错位跳过
        first = next((l for l in body.splitlines() if l.strip()), '')
        if name + '(' not in first or not re.match(r'\s*(?:public|protected|private)', first):
            continue
        body = translate_types(body, import_obf)
        # 构造器: 02b 名 == 混淆类名 → 03 侧改类名 (y(boolean) → UnitType(boolean))
        if name == obf02:
            body = re.sub(r'\b' + re.escape(obf02) + r'\s*\(', cls + '(', body, count=1)
        fills.append(body)
        n_ok += 1
    print(f'提取方法体: {len(fills)}')
    for b in fills[:3]:
        print('  ---')
        print('\n'.join('    ' + l for l in b.splitlines()[:6]))
    if apply and fills:
        s = p03.read_text(encoding='utf-8')
        idx = s.rstrip().rfind('}')
        blocks = []
        for b in fills:
            body_lines = []
            for l in b.splitlines():
                if l.strip():
                    body_lines.append('    ' + l.strip())
                else:
                    body_lines.append('')
            blocks.append('\n'.join(body_lines).rstrip())
        insert = '\n\n    // v19.111 02b(FernFlower 完整) 补齐 jar 缺失方法\n' + \
                 '\n\n'.join(blocks)
        s = s[:idx] + insert + '\n' + s[idx:]
        p03.write_text(s, encoding='utf-8')
        print(f'已插入 {len(fills)} 个方法 → {p03.name}')
    return 0


if __name__ == '__main__':
    sys.exit(main())
