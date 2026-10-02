#!/usr/bin/env python3
"""按 build_reverse_jar.main() 第 03 步的同一算法重打包产物 jar（只读 stock + reverse-classes）。

用途：主遍编译当前失败（identity 修复把新文件带进主编译集 → javac 全或无 → 主遍 0 产出），
产物 jar 只能由「活跃源码产出目录」重建；同时清掉早期 apply 缺陷写进去的 `.class.class` 垃圾条目。

用法: python build/_repack_product.py [--out build/game-lib-reverse.jar]
"""
import sys
from pathlib import Path

# ★ 新架构：路径来自**单一来源** ✓（rwlib.paths ✓）—— 不再用 `__file__` 相对推算 ✗
sys.path.insert(0, str(Path(__file__).resolve().parents[1]))
try:
    from rwlib.paths import P
    POOL = P.pool_dir()
    PRODUCT = P.deliverable_jar()
    ROOT = P.root
except Exception:                                    # 兜底（独立运行 ✓）
    ROOT = Path(__file__).resolve().parents[2]
    POOL = ROOT / 'tools' / 'workspace' / 'reverse-classes'
    PRODUCT = ROOT / 'tools' / 'workspace' / 'game-lib-reverse.jar'

import argparse
import zipfile
from pathlib import Path

STOCK = ROOT / 'RustedWarfare' / 'game-lib.jar'
CLS = POOL


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--out', default=str(PRODUCT))
    args = ap.parse_args()
    out = Path(args.out)
    covered = {p.relative_to(CLS).as_posix() for p in CLS.rglob('*.class')}
    with zipfile.ZipFile(STOCK) as zin, zipfile.ZipFile(out, 'w', zipfile.ZIP_DEFLATED) as zout:
        for n in zin.namelist():
            if n in covered:
                continue
            zout.writestr(n, zin.read(n))
        for p in sorted(CLS.rglob('*.class')):
            zout.writestr(p.relative_to(CLS).as_posix(), p.read_bytes())
    with zipfile.ZipFile(STOCK) as zs, zipfile.ZipFile(out) as zp:
        sn = {n for n in zs.namelist() if n.endswith('.class')}
        pn = [n for n in zp.namelist() if n.endswith('.class')]
        diff = [n for n in pn if n in sn and zs.read(n) != zp.read(n)]
        print('重打包 → %s' % out)
        print('  条目类 %d | A 类替换 %d (%.2f%%) | B 新增 %d | 丢失 %d | 异常条目 %d'
              % (len(pn), len(diff), 100.0 * len(diff) / len(sn), len(set(pn) - sn),
                 len(sn - set(pn)), sum(1 for n in pn if n.endswith('.class.class'))))


if __name__ == '__main__':
    main()
