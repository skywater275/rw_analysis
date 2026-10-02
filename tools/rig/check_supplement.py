#!/usr/bin/env python3
"""`mappings/supplement.csv` 数据完整性自检（PENDING **#40** 的常驻判据）。

Usage:
    python tools/rig/check_supplement.py [--json build/_supplement.json]

为什么需要它
------------
`#40` 曾登记五项「残留数据问题」（387 行空 `col3` / 36 行列错位 / 148 行 `col2` 异常 /
99 行 `col4` 说明文字 / 26 宿主 311 行真歧义）。2026-10-01 第 17 轮**逐项复测**后：
- 四项**已不复现或被证明是误判**（见各判据旁注）；
- 若不留下**可机检的判据**，这类「已修但会静默回归」的问题无法防止复发。

判据（全部可机检）
------------------
| # | 判据 | 期望 |
|---|---|---|
| ① | 每行字段数 == 表头字段数 | 0 异常（列错位/引号未闭合会直接在这里暴露）|
| ② | 必填列非空（`type`/`obfuscated_package`/`obfuscated_class`/`obfuscated_member`/`meaningful_name`）| 0 空 |
| ③ | `type` ∈ {field, method} | 0 越界 |
| ④ | `obfuscated_class` 不含 `.` | 0 |
| ⑤ | `obfuscated_class` 以大写开头者 ⇒ 其 FQN **必须在 stock.jar 中真实存在**（= 官方未混淆名）| 0 缺席 |
| ⑥ | `meaningful_name` 不含 `:`（说明文字不得占位语义名）| 0 |

★ 判据 ⑤ 是**唯一允许「大写开头」的正当理由**：原版就有未混淆的官方类名
（实测 200 行命中 `Main` / `Root` / `ScriptEngine` / `SettingsEngine` / `LogicBooleanGameFunctions$*`）。

★ 判据 ① 说明：`notes` 里出现**未配对括号**是**合法**的 —— 只要整个字段被双引号包裹，
CSV 解析就正确（实测 `L329`/`L984`/`L3030` 三行**全部 7 字段**，当年记的「36 行列错位」是误判）。

退出码：0 = 全部判据通过；1 = 有异常。
"""
import argparse
import csv
import json
import sys
import zipfile
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
csv.field_size_limit(10 * 1024 * 1024)
SUP = ROOT / 'mappings' / 'supplement.csv'
STOCK = ROOT / 'RustedWarfare' / 'game-lib.jar'
REQUIRED = ('type', 'obfuscated_package', 'obfuscated_class',
            'obfuscated_member', 'meaningful_name')


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--json', default='build/_supplement.json')
    args = ap.parse_args()

    with open(SUP, encoding='utf-8', newline='') as f:
        raw = list(csv.reader(f))
    header, body = raw[0], raw[1:]
    rows = [dict(zip(header, r)) for r in body]

    with zipfile.ZipFile(STOCK) as z:
        stock = {n[:-6] for n in z.namelist() if n.endswith('.class')}

    bad_width = [i + 2 for i, r in enumerate(body) if len(r) != len(header)]
    empty = [(i + 2, c) for i, r in enumerate(body) for c in REQUIRED
             if not (dict(zip(header, r)).get(c) or '').strip()]
    bad_type = [(i + 2, r.get('type')) for i, r in enumerate(rows)
                if (r.get('type') or '') not in ('field', 'method')]
    dotted = [(i + 2, r['obfuscated_class']) for i, r in enumerate(rows)
              if '.' in (r.get('obfuscated_class') or '')]
    cap = [(i + 2, r) for i, r in enumerate(rows)
           if (r.get('obfuscated_class') or '')[:1].isupper()]
    cap_miss = [(i, '%s.%s' % (r['obfuscated_package'], r['obfuscated_class']))
                for i, r in cap
                if '%s/%s' % (r['obfuscated_package'].replace('.', '/'),
                              r['obfuscated_class']) not in stock]
    prose = [(i + 2, r['meaningful_name']) for i, r in enumerate(rows)
             if ':' in (r.get('meaningful_name') or '')
             or '：' in (r.get('meaningful_name') or '')]

    print('supplement.csv 数据完整性（%d 数据行 / %d 列）' % (len(rows), len(header)))
    print('  ① 字段数异常            = %d %s' % (len(bad_width), bad_width[:5]))
    print('  ② 必填列为空            = %d %s' % (len(empty), empty[:5]))
    print('  ③ type 越界             = %d %s' % (len(bad_type), bad_type[:5]))
    print('  ④ obfuscated_class 含点 = %d %s' % (len(dotted), dotted[:5]))
    print('  ⑤ 大写开头但不在 stock  = %d %s（在 stock 中的 %d 行为官方名，合法）'
          % (len(cap_miss), cap_miss[:4], len(cap) - len(cap_miss)))
    print('  ⑥ meaningful_name 含冒号 = %d %s' % (len(prose), prose[:4]))
    ok = not (bad_width or empty or bad_type or dotted or cap_miss or prose)
    print('结论: %s' % ('✅ 全部判据通过' if ok else '❌ 有异常（见上）'))

    (ROOT / args.json).write_text(json.dumps({
        '数据行': len(rows), '字段数异常': bad_width, '必填为空': empty,
        'type越界': bad_type, '含点': dotted, '大写非官方': cap_miss, '冒号': prose,
    }, ensure_ascii=False, indent=2), encoding='utf-8')
    print('→ %s' % (ROOT / args.json))
    return 0 if ok else 1


if __name__ == '__main__':
    sys.exit(main())
