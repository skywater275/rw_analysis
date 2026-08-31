#!/usr/bin/env python3
"""03 语义方法批量补全 (v19.112) — supplement 映射驱动 + 符号存在性过滤.

方法: supplement.csv 的 02 名→03 语义名方法映射 (custom.j 类) → 02b 提取方法体
→ 02 名替换为语义名 → 符号存在性过滤 (复用 fix_03_batch_fill) → 插入 03 类末尾.

Usage: python tools/fixers/fix_03_semantic_methods.py [--dry-run] [--apply] [--class CustomUnitType]
"""
import csv
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.stdout.reconfigure(encoding='utf-8', errors='replace')
csv.field_size_limit(10 * 1024 * 1024)

from tools.fixers.fix_03_batch_fill import (JDK_TYPES, COMMON_METHODS, KEYWORDS,
                                            symbols_of, known_symbols,
                                            extract_body)

# 类型翻译表 (02 短名 → 03 语义名) — 全部来自 class-discoveries 已验证条目或 javap 铁证.
# 教训 (v19.111): 不做全局翻译 (bh→CrashHandler 错翻) — 仅本表白名单, 逐词边界替换.
TYPE_MAP = {
    'am': 'UnitInstance', 'bn': 'ModUnitLoader', 'l': 'ModUnitRegistry',
    'm': 'DirectionConfig', 'ao': 'MovementTypeEnum', 'af': 'af',
    'h': 'UnitConfig', 'y': 'UnitType', 'as': 'UnitTypeHandle',
    'ag': 'ModLoader', 'g': 'TeamTag', 's': 'GameAction', 'au': 'WeaponAction',
}


def translate_body_types(body, extra_map=None):
    """方法体类型名白名单翻译.
    extra_map: per-class 短名→03全限定名 (v19.112d).
    extra_map 只翻类型位置 (强转/声明/new) — 防方法名/调用误伤 (a→AIStrategy 教训)."""
    for short, long in dict(TYPE_MAP).items():
        body = re.sub(r'\b' + short + r'\b', long, body)
    if extra_map:
        for short, long in extra_map.items():
            if short in TYPE_MAP:
                continue
            # (X) 强转 / (X)var
            body = re.sub(r'\(' + short + r'\)(?!\s*\w*\s*\()',
                          '(' + long + ')', body)
            # X var 声明 (行首/;/{ 后跟空格+标识符)
            body = re.sub(r'(?<=[\s;{])\b' + short + r'\s+(?=[a-zA-Z_]\w*\s*[;=)])',
                          long + ' ', body)
            # new X(
            body = re.sub(r'\bnew\s+' + short + r'\s*\(', 'new ' + long + '(', body)
    return body

# (02 方法名, 参数形态, 03 语义名) — 02 名来自 supplement.csv 映射 (custom.j)
SEMANTIC_METHODS = [
    # (02 名, 02 参数签名片段, 03 语义名)
    ('bI', '', 'isSeaUnitByConfig'),
    ('bp', '', 'getExplosionParticleCount'),
    ('bv', '', 'finalizeUnitRemoval'),
    ('y', 'int', 'getTurretMuzzleVelocity'),
    ('a_', 'boolean', 'getUnitTextureFrameRect'),
    ('c', 'int', 'getTurretFireCooldownOverride'),
]

# 无 supplement 语义名, 保持 02 短名
SHORT_METHODS = [
    ('dF', ''),
    ('Q', ''),
    ('bS', ''),
]


def find_body(src2b, name, param_hint):
    """02b 源里按名字+参数提示找方法体."""
    for m in re.finditer(r'(?:public|protected|private|static|final|abstract|strictfp|\s)+'
                         r'[\w<>\[\].$]+ ' + re.escape(name) + r'\(([^)]*)\)\s*\{', src2b):
        params = m.group(1).strip()
        if param_hint:
            if param_hint not in params:
                continue
        body = extract_body(src2b, m.group(0), m.start())
        if body:
            return m.group(0), body
    return None, None


def main():
    cls = 'CustomUnitType'
    apply = '--apply' in sys.argv
    extra = []  # --methods a,b,c 追加 0 参短名方法
    only_extra = '--only-extra' in sys.argv
    for idx, a in enumerate(sys.argv):
        if a == '--class' and idx + 1 < len(sys.argv):
            cls = sys.argv[idx + 1]
        if a == '--methods' and idx + 1 < len(sys.argv):
            extra = [(m, '') for m in sys.argv[idx + 1].split(',')]
    pkg02, obf02 = None, None
    for r in csv.reader(open(ROOT / 'mappings/class-discoveries.csv', encoding='utf-8')):
        if r and r[0] == 'class' and r[3] == cls:
            pkg02, obf02 = r[1], r[2]  # 末条 wins (纯包条目在后)
    p03 = next((ROOT / '03-deobfuscated').rglob(cls + '.java'), None)
    p02b = ROOT / '02b-decompiled' / pkg02.replace('.', '/') / (obf02 + '.java')
    if not p03 or not p02b.exists():
        print('文件缺失')
        return 1
    src2b = p02b.read_text(encoding='utf-8', errors='ignore')
    fields, methods = known_symbols(p03)
    print(f'{cls}: 已声明字段 {len(fields)}, 方法 {len(methods)}')
    # 03 方法名+arity 集合 (防重复插入)
    methods_arity = set()
    src3 = p03.read_text(encoding='utf-8', errors='ignore')
    for m in re.finditer(r'(?:public|protected|private|static|final|strictfp|\s)+'
                         r'[\w<>\[\].$]+ (\w+)\(([^)]*)\)', src3):
        p = m.group(2).strip()
        methods_arity.add((m.group(1), 0 if not p else len(p.split(','))))
    inserts = []
    builtin = [] if only_extra else SEMANTIC_METHODS + [(n, h, None) for n, h in SHORT_METHODS]
    for name, hint, semantic in builtin + [(n, h, None) for n, h in extra]:
        sig, body = find_body(src2b, name, hint)
        if not body:
            print(f'  {name}({hint}) → {semantic}: 02b 无方法体, 跳过')
            continue
        if semantic:
            # 先改名 (防 \by\b 类翻译误伤方法名), 再译类型
            body = re.sub(r'\b' + re.escape(name) + r'\s*\(', semantic + '(', body, count=1)
            # 体内自调用也改名
            body = re.sub(r'\b' + re.escape(name) + r'\s*\(', semantic + '(', body)
        body = translate_body_types(body)
        # CFR/02b 共同显示 bug: 字节码小写 s(int) 被两版反编译器显示为大写 S( — 归一 (javap 铁证: 仅 s(int) 存在)
        body = re.sub(r'\bS\(', 's(', body)
        first = next((l for l in body.splitlines() if l.strip()), '')
        newname = semantic or name
        # 已有声明检查 (v19.112 Q() 重复教训: 03 可能已保留同名短名方法)
        params = re.search(r'\(([^)]*)\)', first.split(newname, 1)[-1])
        arity = 0 if not params or not params.group(1).strip() else len(params.group(1).split(','))
        if (newname, arity) in methods_arity:
            print(f'  {name} → {newname}: 03 已有同名同参声明, 跳过')
            continue
        if not re.match(r'\s*(?:public|protected|private)', first):
            print(f'  {name}: 方法体质量不合格, 跳过')
            continue
        refs = symbols_of(body, cls)
        newname = semantic or name
        unknown = [x for x in refs
                   if x[1] != newname and x[1] != name
                   and ((x[0] == 'field' and x[1] not in fields and x[1] not in methods)
                        or (x[0] == 'method' and x[1] not in methods)
                        or (x[0] == 'type' and x[1] not in JDK_TYPES))]
        if unknown:
            print(f'  {name} → {newname}: 未知引用 {sorted(set(x[1] for x in unknown))[:10]}, 跳过')
            continue
        inserts.append((newname, body))
        print(f'  {name} → {newname}: OK ({body.count(chr(10))} 行)')
    if apply and inserts:
        s = p03.read_text(encoding='utf-8', errors='ignore')
        idx = s.rstrip().rfind('}')
        blocks = []
        for newname, body in inserts:
            lines = ['    ' + l.strip() if l.strip() else '' for l in body.splitlines()]
            blocks.append('\n'.join(lines).rstrip())
        insert = '\n\n    // v19.112 语义方法补全 (supplement 02 名映射 + 02b 方法体)\n' + '\n\n'.join(blocks)
        s = s[:idx] + insert + '\n' + s[idx:]
        p03.write_text(s, encoding='utf-8')
        print(f'已插入 {len(inserts)} 个方法')
    else:
        print(f'可插入 {len(inserts)} 个方法 ({"dry-run" if not apply else "apply"})')
    return 0


if __name__ == '__main__':
    sys.exit(main())
