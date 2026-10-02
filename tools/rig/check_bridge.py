#!/usr/bin/env python3
"""桥接健康度自检：`build/03-adapted-map.csv`（stock 混淆类 ↔ `03` 可读源）四桶分类。

Usage:
    python tools/rig/check_bridge.py [--map build/03-adapted-map.csv] [--json build/_bridge.json]

背景（PENDING **RS-7**，2026-10-01 第 12/14 轮一手实测）
------------------------------------------------------
该 CSV 是**测量类工具**（漏斗 `measure_03_funnel.py`、「改名损伤」普查、RS-9 口径）的输入，
**不在反向构建路径上**（构建走 `tools/fixers/build_reverse_jar.py::load_mapping()`，
只读 `mappings/class-discoveries.csv`）。但它自己的正确性决定了上述测量是否可信。

四桶
----
| 桶 | 定义 | 判据 |
|---|---|---|
| **① 悬空** | `src03_path` 是**真实路径**（非 `<…>` 哨兵）但文件不存在 | **判 FAIL**（测量会静默丢类）|
| **② 可桥接未桥接** | `03` 树里某个 `.java` 未被任何行引用，且**其外层类已被桥接** | **判 FAIL**（同一族的漏桥）|
| **③ 无 stock 对应** | 未被引用，且外层也未桥接 ⇒ `03` 有源但 stock 无对应类 | **INFO**（B/新增，桥不了；其副作用见下）|
| **④ 哨兵行** | `src03_path` 形如 `<02b>…` = 显式「无 03 源，走 02b 兜底」 | **INFO**（不是缺陷）|

⚠️ 桶③的**副作用**（第 13 轮实测确认）：这些 `03` 源在反向构建里**没有混淆名可落位**，
于是被按可读名**原样产出** ⇒ 交付物里多出**零引用的死类**（门禁 **V4b** 就是这么抓到的：
`ChatLog` / `testing` / `DebugDesyncDetector` / `MovementTypeEnum` …）。
⇒ 桶③的**根治**在 `build_reverse_jar.py`（**不产出**无混淆对应的 03 源），不在本 CSV。

退出码：0 = ① ② 均为 0；1 = 有悬空或漏桥。
"""
import argparse
import csv
import json
import re
import sys
from collections import Counter, defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
csv.field_size_limit(10 * 1024 * 1024)
DEF_MAP = ROOT / 'build' / '03-adapted-map.csv'
TREE03 = ROOT / '03-deobfuscated'
SENTINEL = re.compile(r'^<')


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--map', default=str(DEF_MAP))
    ap.add_argument('--json', default='build/_bridge.json')
    args = ap.parse_args()

    with open(args.map, encoding='utf-8-sig', newline='') as f:   # ★ 该 CSV 带 BOM（见 CV-7）
        rows = list(csv.DictReader(f))

    all03 = {p.relative_to(TREE03).as_posix()[:-5] for p in TREE03.rglob('*.java')}
    dangling, sentinel, referenced = [], [], set()
    for r in rows:
        sp = (r.get('src03_path') or '').strip()
        if SENTINEL.match(sp):
            sentinel.append((r['stock_path'], sp))
            continue
        if sp:
            referenced.add(sp)
            if sp not in all03:
                dangling.append((r['stock_path'], sp))

    unref = sorted(all03 - referenced)
    outer_bridged = set()
    src_to_stock = {}
    for r in rows:
        sp = (r.get('src03_path') or '').strip()
        if sp and not SENTINEL.match(sp):
            outer_bridged.add(sp)
            src_to_stock.setdefault(sp, r['stock_path'].strip())
    # ★ ②/③ 的分界必须看 **stock 里到底有没有这个类**：若 `O$N` 在 stock 中不存在，
    #   那就**没有可桥的对象**，属 ③（03 有源、stock 无对应），不是漏桥。
    #   （实测踩过：初版只检查「外层已桥接」⇒ 把 23 个 `WeaponConfig$N` 误判为漏桥，
    #     而 `custom/as$N` 在 stock 中**根本不存在**，且本表 1,698 行 = stock 1,698 类、缺行 0。）
    import zipfile
    stock = ROOT / 'RustedWarfare' / 'game-lib.jar'
    with zipfile.ZipFile(stock) as z:
        stock_cls = {n[:-6] for n in z.namelist() if n.endswith('.class')}
    derivable, no_counterpart = [], []
    for u in unref:
        base = u.split('$')[0]
        cand = None
        if base != u and base in outer_bridged:
            cand = '%s$%s' % (src_to_stock.get(base, ''), u.split('$', 1)[1])
        (derivable if cand and cand in stock_cls else no_counterpart).append(u)

    print('桥接表 %s · 行 %d' % (Path(args.map).name, len(rows)))
    print('  ① 悬空（真实路径但文件不存在）      = %d %s'
          % (len(dangling), '' if not dangling else '★ FAIL'))
    for s, p in dangling[:6]:
        print('       %s → %s' % (s, p))
    print('  ② 可桥接未桥接（外层已桥接的 $ 内部类）= %d %s'
          % (len(derivable), '' if not derivable else '★ FAIL'))
    for u in derivable[:6]:
        print('       %s' % u)
    print('  ③ 无 stock 对应（03 有源、stock 无类）= %d（INFO；副作用见 docstring）' % len(no_counterpart))
    c = Counter('/'.join(u.split('/')[:5]) for u in no_counterpart)
    for k, v in c.most_common(6):
        print('       %-56s ×%d' % (k, v))
    print('  ④ 哨兵行（<02b> 兜底）              = %d（INFO）' % len(sentinel))
    ok = not dangling and not derivable
    print('结论: %s' % ('✅ 桥接健康（① ② 均为 0）' if ok else '❌ 有悬空或漏桥（见上）'))

    (ROOT / args.json).write_text(json.dumps(dict(
        行数=len(rows), 悬空=dangling, 可桥接未桥接=derivable,
        无stock对应=no_counterpart, 哨兵=[s for _, s in sentinel]),
        ensure_ascii=False, indent=2), encoding='utf-8')
    print('→ %s' % (ROOT / args.json))
    return 0 if ok else 1


if __name__ == '__main__':
    sys.exit(main())
