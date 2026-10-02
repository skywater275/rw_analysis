#!/usr/bin/env python3
"""剪除交付物里的**孤儿条目**（非 stock 同位 + 零外部引用）—— PENDING **RS-2** ②/③ 的落地。

Usage:
    python tools/fixers/prune_orphan_entries.py [--dry-run|--apply]
                                                [--product build/game-lib-reverse.jar]

判据（与 `replacement_verify.py` 的 **V4b** 同一算法，避免两处口径漂移）：
  ① 设 P = 产物中**包路径不在 stock** 的 `.class` 条目（B/新增类）；
  ② 扫遍产物全部类的字节，统计每个**内部名**被多少个类引用（每个类最多计 1）；
  ③ 某条目只被自己引用（计数 ≤ 1）⇒ **零外部引用 = 孤儿**。

安全阀（本次新增，因删除是不可逆操作）：
  · **反射字符串检查** —— 孤儿类的**简单名**若作为字符串常量出现在**别的**类里，
    则可能是 `Class.forName("…")` 的目标 ⇒ **不剪**，只报警。
  · 孤儿一律 **移动到 `build/_quarantine/orphan-entries/`**（`shutil.move`，可一键还原），
    不直接删除。
  · `--apply` 后必须重跑 `_repack_product.py`（产物 = stock + 池 ⇒ **原子契约由构造保证**）
    并重跑 `replacement_verify.py`（含 V5 真窗口 + V6 回放）。

⚠️ 本工具**只动 `build/reverse-classes` 与产物 jar**，不触碰 `03-deobfuscated/` 与 `mappings/`。
"""
import argparse
import re
import shutil
import sys
import zipfile
from collections import Counter
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
STOCK = ROOT / 'RustedWartare'  # 占位，运行时被下面覆盖
STOCK = ROOT / 'RustedWarfare' / 'game-lib.jar'
PROD = ROOT / 'build' / 'game-lib-reverse.jar'
POOL = ROOT / 'build' / 'reverse-classes'
QUAR = ROOT / 'build' / '_quarantine' / 'orphan-entries'
RX = re.compile(rb'[A-Za-z_$][A-Za-z0-9_$]*(?:/[A-Za-z_$][A-Za-z0-9_$]*)+')


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--product', default=str(PROD))
    ap.add_argument('--dry-run', action='store_true', default=False)
    ap.add_argument('--apply', action='store_true')
    args = ap.parse_args()
    prod_path = Path(args.product)
    dry = not args.apply

    with zipfile.ZipFile(STOCK) as z:
        stock_names = {n[:-6] for n in z.namelist() if n.endswith('.class')}
    with zipfile.ZipFile(prod_path) as z:
        blobs = {n: z.read(n) for n in z.namelist() if n.endswith('.class')}

    extra = [n[:-6] for n in blobs if n[:-6] not in stock_names]
    refs = Counter()
    for b in blobs.values():
        refs.update(set(RX.findall(b)))

    orphan, safe, risky = [], [], []
    for e in extra:
        if refs.get(e.encode(), 0) <= 1:
            orphan.append(e)
        else:
            safe.append(e)

    # 反射字符串检查：孤儿的**简单名**是否作为**独立 token** 出现在别的类的字符串常量里
    # （★ 不能用裸子串搜索：单字母简单名如 `a`/`b$2` 会在任何类里命中 ⇒ 96 个孤儿里误报 80 个。
    #  改成「可打印 ASCII 串按非标识符切词 + token 全等」，且忽略长度 < 4 的短名。）
    TOK = re.compile(rb'[A-Za-z_$][A-Za-z0-9_$]{3,}')
    token_owner = {}
    for n, b in blobs.items():
        for t in set(TOK.findall(b)):
            token_owner.setdefault(t, set()).add(n[:-6])
    risky = []
    for e in orphan:
        simple = e.rsplit('/', 1)[-1].split('$')[0]
        if len(simple) < 4:
            continue
        owners = token_owner.get(simple.encode(), set()) - {e}
        if owners:
            risky.append(e)
    risky = sorted(set(risky))

    print('产物 %s' % prod_path.name)
    print('  非 stock 条目 = %d（其中**零外部引用** = %d）' % (len(extra), len(orphan)))
    print('  可安全剪除 = %d · ★反射可疑（不剪，只报警）= %d' % (len(orphan) - len(risky), len(risky)))
    for e in risky[:8]:
        print('     ⚠ %s' % e)
    print('  样例（将剪除）:', orphan[:6])

    if dry:
        print('\n[dry-run] 未改动。加 --apply 落盘（剪除 → 隔离目录，再重打包产物）。')
        return 0

    todo = [e for e in orphan if e not in set(risky)]
    if not todo:
        print('\n无可剪除项。')
        return 0
    QUAR.mkdir(parents=True, exist_ok=True)
    moved = 0
    for e in todo:
        src = POOL / (e + '.class')
        if not src.exists():
            continue
        dst = QUAR / (e + '.class')
        dst.parent.mkdir(parents=True, exist_ok=True)
        shutil.move(str(src), str(dst))
        moved += 1
    print('\n已隔离 %d 个孤儿 → %s' % (moved, QUAR))
    print('★ 下一步（必须）：python build/_repack_product.py；'
          'python tools/gates/replacement_verify.py --json build/_verify-latest.json')
    return 0


if __name__ == '__main__':
    sys.exit(main())
