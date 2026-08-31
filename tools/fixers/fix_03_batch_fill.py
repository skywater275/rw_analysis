#!/usr/bin/env python3
"""03 缺失方法按依赖层分批补全 (v19.111, P5) — 符号存在性过滤 + 分批插入.

策略 (fix_03_fill_missing 一次性插入 +587 的修正):
1. 提取 03 缺失方法 (javap 基准) 的方法体 (02b 源)
2. **符号存在性过滤**: 方法体内的短名引用 (this.X 字段 / X.y 调用 / 类型名)
   全部必须在 03 目标类已声明 (字段/方法) 或 JDK 类型 → 批 0 安全
3. 批 0 插入 → 全量 gate 判定净收益
4. 负收益批次回退 (git checkout)

Usage: python tools/fixers/fix_03_batch_fill.py --class UnitType [--apply] [--batch N]
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

from tools.fixers.fix_03_fill_missing import (javap_names, methods03, extract_body,
                                              translate_types, imports_of)

JDK_TYPES = {'String', 'Object', 'int', 'float', 'boolean', 'long', 'double', 'short',
             'byte', 'char', 'void', 'Paint', 'Point', 'PointF', 'Rect', 'RectF',
             'ArrayList', 'File', 'Exception', 'RuntimeException'}

KEYWORDS = {'for', 'if', 'else', 'return', 'new', 'while', 'switch', 'case', 'continue',
            'break', 'instanceof', 'this', 'super', 'true', 'false', 'null', 'throw',
            'catch', 'finally', 'try', 'class', 'do'}

COMMON_METHODS = {'size', 'clear', 'add', 'get', 'put', 'remove', 'isEmpty', 'equals',
                  'hashCode', 'toString', 'valueOf', 'values', 'clone', 'contains',
                  'iterator', 'hasNext', 'next', 'length', 'floatValue', 'intValue',
                  'doubleValue', 'longValue', 'booleanValue', 'charValue', 'byteValue',
                  'shortValue', 'parseInt', 'parseFloat', 'append', 'substring',
                  # v19.112d: 全限定名包段 (方法抓取误报源)
                  'com', 'corrodinggames', 'rts', 'java', 'android', 'util', 'lang',
                  'io', 'net', 'game', 'gameFramework', 'units', 'custom', 'network',
                  'ai', 'map', 'rendering', 'effects', 'utility', 'pathfinding',
                  'printStackTrace', 'currentTimeMillis', 'close', 'endsWith',
                  'equalsIgnoreCase', 'startsWith', 'toLowerCase', 'toUpperCase',
                  'trim', 'split', 'replace', 'format', 'getName', 'getClass',
                  'ordinal', 'compareTo', 'floatToIntBits', 'max', 'min', 'abs'}

# 02 字节码方法名命中 Java 关键字的 (混淆器合法/javac 拒绝) — 03 侧已有改名惯例 (do→do_), 跳过
JAVA_KEYWORDS = {'do', 'int', 'float', 'boolean', 'void', 'long', 'double', 'byte',
                 'short', 'char', 'for', 'if', 'else', 'while', 'switch', 'case',
                 'new', 'class', 'return', 'try', 'catch', 'finally', 'throw', 'throws'}


def collect_missing(cls):
    """[(name, arity, body)] 缺失方法 + 方法体."""
    pkg02, obf02 = None, None
    for r in csv.reader(open(ROOT / 'mappings/class-discoveries.csv', encoding='utf-8')):
        if r and r[0] == 'class' and r[3] == cls:
            pkg02, obf02 = r[1], r[2]
    p03 = next((ROOT / '03-deobfuscated').rglob(cls + '.java'), None)
    p02b = ROOT / '02b-decompiled' / pkg02.replace('.', '/') / (obf02 + '.java')
    if not p03 or not p02b.exists():
        return None, None, None, None
    have = set(methods03(p03))
    want = javap_names(pkg02 + '.' + obf02)
    # v19.112d: javap 输出 (name,arity) 可重复 (同签名多行) — 去重防同方法体提取 N 次
    want = sorted(set(want))
    missing = [(n, a) for n, a in want if (n, a) not in have]
    src2b = p02b.read_text(encoding='utf-8', errors='ignore')
    out = []
    for name, arity in missing:
        if name in JAVA_KEYWORDS:
            continue  # 03 侧已有改名惯例 (do→do_ 等), 跳过防重复
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
        first = next((l for l in body.splitlines() if l.strip()), '')
        if name + '(' not in first or not re.match(r'\s*(?:public|protected|private)', first):
            continue
        # 构造器改名: 仅当首行无返回类型 (构造器声明: 修饰符+类名+( 直接跟参数)
        # v19.112d 教训: AIStrategy 02 类名 a 与大量方法同名 — 返回类型判别防误改
        if name == obf02 and not re.search(r'[\w<>\[\].$]+ ' + re.escape(obf02) + r'\s*\(', first[:200]):
            body = re.sub(r'\b' + re.escape(obf02) + r'\s*\(', cls + '(', body, count=1)
        # v19.112d: 类型短名译表 (02b 方法体直插 03 必需; 全局白名单 + per-class import 反查)
        from tools.fixers.fix_03_semantic_methods import translate_body_types
        body = translate_body_types(body, per_class_type_map(src2b))
        out.append((name, arity, body))
    pcm = per_class_type_map(src2b)
    return p03, obf02, out, pcm


_M03NAME = None  # 全局缓存: 03 类名 → 03 全限定名


def _m03name_cache():
    """03 类名 → 03 全限定名 (文件名+package 声明, 全树一次扫描)."""
    global _M03NAME
    if _M03NAME is not None:
        return _M03NAME
    out = {}
    import os
    for root, dirs, fs in os.walk(ROOT / '03-deobfuscated'):
        for f in fs:
            if f.endswith('.java'):
                p = Path(root) / f
                src3 = p.read_text(encoding='utf-8', errors='ignore')
                pm = re.search(r'package ([\w.]+);', src3)
                if pm:
                    out[f[:-5]] = pm.group(1) + '.' + f[:-5]
    _M03NAME = out
    return out


def per_class_type_map(src2b):
    """per-class 类型译表: 02b import 短名 → 03 全限定名 (class-discoveries 反查).

    v19.112d: 白名单全局译表无法覆盖每类专属类型 (a→AIStrategy, ai→?) —
    用 02b 的 import 行 + class-discoveries 反查, 03 文件存在才收录.
    返回 {短名: 03全限定名}."""
    m03name = _m03name_cache()
    out = {}
    cd_rows = list(csv.reader(open(ROOT / 'mappings/class-discoveries.csv', encoding='utf-8')))
    # 02b imports
    pkg = None
    pm = re.search(r'package ([\w.]+);', src2b)
    if pm:
        pkg = pm.group(1)
    for imp in re.findall(r'import ([\w.]+);', src2b):
        short = imp.rsplit('.', 1)[1]
        pkg02 = imp.rsplit('.', 1)[0]
        for r in cd_rows:
            if len(r) > 3 and r[0] == 'class' and r[1] == pkg02 and r[2] == short and r[3] in m03name:
                out[short] = m03name[r[3]]
                break
    # 同包类 (无 import): 02b package + 短名
    if pkg:
        for r in cd_rows:
            if len(r) > 3 and r[0] == 'class' and r[1] == pkg and r[3] in m03name:
                out[r[2]] = m03name[r[3]]
    return out


def symbols_of(body, cls):
    """方法体中的符号引用: 对象.X 字段 / X(...) 方法调用 / 类型名."""
    refs = set()
    # 只抓 this.X (类内字段可验); var2.X 是局部变量 (类型方法体内, 过滤无法验证)
    for m in re.finditer(r'this\.(\w+)', body):
        if m.group(1)[:1].isdigit():
            continue
        refs.add(('field', m.group(1)))
    for m in re.finditer(r'([a-zA-Z][\w$]*)\(', body):
        n = m.group(1)
        if n not in JDK_TYPES and n not in KEYWORDS and n not in COMMON_METHODS:
            refs.add(('method', n))
    # 类型名 (new X / 声明)
    for m in re.finditer(r'new ([A-Za-z][\w$]*)', body):
        refs.add(('type', m.group(1)))
    return refs


def known_symbols(p03):
    """03 继承链已声明的字段/方法名集合 (父类字段不算未知).
    继承链: UnitType→AbstractUnitBase→UnitInstance (03 语义)."""
    srcs = [p03.read_text(encoding='utf-8', errors='ignore')]
    for parent in ['AbstractUnitBase', 'UnitInstance', 'CustomUnitBase']:
        # 全 03 树搜 (父类不在同目录 — rglob 只在子树)
        p = next(iter((ROOT / '03-deobfuscated').rglob(parent + '.java')), None)
        if p:
            srcs.append(p.read_text(encoding='utf-8', errors='ignore'))
    src = '\n'.join(srcs)
    fields = set()
    for m in re.finditer(r'(?:public|protected|private|static|final|strictfp|\s)+'
                         r'[\w<>\[\].$]+ (\w+)\s*(?:=|;)', src):
        fields.add(m.group(1))
    methods = set(m.group(1) for m in re.finditer(
        r'(?:public|protected|private|static|final|strictfp|\s)+[\w<>\[\].$]+ (\w+)\(', src))
    return fields, methods


def main():
    cls = None
    apply = '--apply' in sys.argv
    batch = 10
    for idx, a in enumerate(sys.argv):
        if a == '--class' and idx + 1 < len(sys.argv):
            cls = sys.argv[idx + 1]
        if a.startswith('--batch='):
            batch = int(a.split('=', 1)[1])
    if not cls:
        print('用法: --class UnitType')
        return 1
    p03, obf02, missing, pcm = collect_missing(cls)
    if p03 is None:
        print('文件缺失')
        return 1
    fields, methods = known_symbols(p03)
    # 缺失方法名加入 known (依赖序: R() 调 aO() — aO 也在缺失清单, 前置插入后满足)
    methods |= {n for n, a, b in missing}
    # v19.112d: per-class 译表值 + 全局白名单值 (03 类名) 的跨类静态调用放过 (gate 兜底)
    from tools.fixers.fix_03_semantic_methods import TYPE_MAP
    known_types = set(pcm.values()) | set(TYPE_MAP.values())
    print(f'{cls}: 缺失方法 {len(missing)} (02b 提取成功)')
    # 符号过滤: 引用全部已知 → 批 0
    safe, unsafe = [], []
    for name, arity, body in missing:
        refs = symbols_of(body, cls)
        unknown = [r for r in refs
                   if r[1] != name  # 方法自身递归引用不算未知
                   and ((r[0] == 'field' and r[1] not in fields and r[1] not in methods)
                        or (r[0] == 'method' and r[1] not in methods and r[1] not in known_types)
                        or (r[0] == 'type' and r[1] not in JDK_TYPES and r[1] not in known_types))]
        if not unknown:
            safe.append((name, arity, body))
        else:
            unsafe.append((name, arity, body, unknown))
    print(f'批0 安全 (引用全已知): {len(safe)}, 不安全: {len(unsafe)}')
    for n, a, b, u in unsafe[:8]:
        print(f'  {n}({a}): 未知引用 {sorted(set(x[1] for x in u))[:8]}')
    print('安全方法样例:')
    for n, a, b in safe[:10]:
        first = next(l.strip() for l in b.splitlines() if l.strip())[:70]
        print(f'  {n}({a}): {first}')
    if apply and safe:
        s = p03.read_text(encoding='utf-8')
        idx = s.rstrip().rfind('}')
        blocks = []
        for name, arity, body in safe[:batch]:
            lines = []
            for l in body.splitlines():
                lines.append('    ' + l.strip() if l.strip() else '')
            blocks.append('\n'.join(lines).rstrip())
        insert = '\n\n    // v19.111 分批补全 (符号存在性过滤, 批0)\n' + '\n\n'.join(blocks)
        s = s[:idx] + insert + '\n' + s[idx:]
        p03.write_text(s, encoding='utf-8')
        print(f'已插入批0 前 {min(batch, len(safe))} 个方法')
    return 0


if __name__ == '__main__':
    sys.exit(main())
