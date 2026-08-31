#!/usr/bin/env python3
"""ini 参数 ↔ dump 字段值匹配 (v19.111) — 自动生成字段语义映射.

方法: 解析 assets/units/*.ini 参数 (maxHp/price/mass/radius...) → 与运行时 dump
(runtime-unit-dump.txt, 全单位字段值) 按单位名对齐 → 值相等 = 键-字段铁证对.

Usage: python tools/analyze/match_ini_params.py [--dump PATH]
"""
import csv
import re
import sys
from collections import defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
GAME_DIR = ROOT.parent
sys.stdout.reconfigure(encoding='utf-8', errors='replace')


def parse_ini(path):
    """[(键, 值)] core 段参数."""
    out = {}
    for line in path.read_text(encoding='utf-8', errors='ignore').splitlines():
        line = line.strip()
        if not line or line.startswith(('#', '[', ';')):
            continue
        if ':' in line:
            k, v = line.split(':', 1)
            out[k.strip()] = v.strip()
    return out


def parse_dump(path):
    """{单位名: {字段: 值}}."""
    out = {}
    for line in path.read_text(encoding='utf-8', errors='ignore').splitlines():
        m = re.match(r'.*DUMP_UNIT:(\S+) (.*)', line)
        if not m:
            continue
        name, rest = m.group(1), m.group(2)
        fields = {}
        for fv in rest.split():
            if '=' in fv:
                k, v = fv.split('=', 1)
                fields[k] = v
        out[name] = fields
    return out


def main():
    dump_path = None
    for i, a in enumerate(sys.argv):
        if a == '--dump' and i + 1 < len(sys.argv):
            dump_path = Path(sys.argv[i + 1])
    if not dump_path:
        dump_path = ROOT / 'mappings/generated/runtime-unit-dump.txt'
    units = parse_dump(dump_path)
    print(f'dump 单位: {len(units)}')
    # ini 索引: name 键 → 参数
    inis = {}
    for ini in (GAME_DIR / 'assets/units').rglob('*.ini'):
        p = parse_ini(ini)
        if 'name' in p:
            inis[p['name']] = (ini.name, p)
    print(f'ini 单位: {len(inis)}')
    # 匹配: 同名单位 → 参数值 vs 字段值 (过滤零值巧合 + 核心数值键白名单)
    CORE_KEYS = {'maxHp', 'price', 'mass', 'radius', 'techLevel', 'maxShield',
                 'shieldRegen', 'armour', 'energyMax', 'energyRegen', 'buildSpeed',
                 'selfRegenRate', 'fogOfWarSightRange', 'nanoRange', 'transportSlotsNeeded',
                 'maxTransportingUnits', 'reclaimPrice', 'displayRadius', 'uiTargetRadius',
                 'nanoRepairSpeed', 'nanoBuildSpeed', 'generation_credits', 'generation_delay'}
    matches = []
    matched_names = 0
    for name, fields in sorted(units.items()):
        if name not in inis:
            continue
        matched_names += 1
        ini_name, params = inis[name]
        for k, v in params.items():
            if k not in CORE_KEYS:
                continue
            if v in ('0', '0.0', 'true', 'false'):
                continue
            for fname, fval in fields.items():
                if fval in ('0', '0.0', 'null'):
                    continue
                # 容差匹配: 相等 / 2x倍数 / 0.5x (建造中HP减半/等级加成)
                try:
                    iv = float(v)
                    fv = float(fval)
                except ValueError:
                    if v == fval:
                        matches.append((name, k, v, fname))
                    continue
                if iv == 0:
                    continue
                ratio = fv / iv
                if abs(ratio - 1) < 0.001:
                    matches.append((name, k, v, fname, 'eq'))
                elif abs(ratio - 2) < 0.001 or abs(ratio - 0.5) < 0.001:
                    matches.append((name, k, v, fname, 'x2'))
                elif abs(ratio - 3) < 0.001 or abs(ratio - 0.33) < 0.001:
                    matches.append((name, k, v, fname, 'x3'))
    print(f'同名匹配单位: {matched_names}')
    print(f'值相等键-字段对: {len(matches)}')
    # 聚合同字段的参数键
    grouped = defaultdict(lambda: defaultdict(int))
    for m in matches:
        name, k, v, fname, mode = m
        grouped[fname][(k, mode)] += 1
    print('字段 → 参数键 (次数, eq/x2/x3):')
    for fname in sorted(grouped, key=lambda f: -max(grouped[f].values())):
        top = sorted(grouped[fname].items(), key=lambda x: -x[1])[:3]
        print(f'  {fname}: {top}')
    # 输出细节 (容差匹配样例)
    print('容差匹配样例 (前 12):')
    for m in matches[:12]:
        print(f'  {m[0]}: {m[1]}={m[2]} ↔ {m[3]} ({m[4]})')
    return 0


if __name__ == '__main__':
    sys.exit(main())
