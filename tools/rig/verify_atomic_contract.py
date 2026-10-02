#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""校验：当前 build/reverse-classes 重打包后是否与既有产物逐条目一致（原子契约自检）。

用法: python .tmp/qavprobe/verify_repack.py [--product build/game-lib-reverse.jar]
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
import hashlib
import subprocess
import sys
import zipfile
from pathlib import Path



def entries(path):
    with zipfile.ZipFile(path) as z:
        return {n: hashlib.sha256(z.read(n)).hexdigest() for n in z.namelist()}


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--product', default=str(PRODUCT))
    args = ap.parse_args()
    tmp = PRODUCT.parent / '_repack-check.jar'
    # ★ 新架构：重打包工具与本国**同目录**（tools/rig/ ✓），且**检查返回码** ✓
    #   （原实现指向已废弃的 `_repack_product.py` ✗ 且 capture_output 吞掉失败 ✗）
    repack = Path(__file__).resolve().with_name('repack_product.py')
    r = subprocess.run([sys.executable, str(repack), '--out', str(tmp)],
                       cwd=str(ROOT), capture_output=True, text=True, errors='replace')
    if r.returncode != 0 or not tmp.exists():
        print('!! 重打包失败（rc=%d）✗ ⇒ 无法判定原子契约' % r.returncode)
        print((r.stdout or '')[-400:] + (r.stderr or '')[-400:])
        return 1
    a, b = entries(Path(args.product)), entries(tmp)
    only_p = sorted(set(a) - set(b))
    only_r = sorted(set(b) - set(a))
    diff = sorted(n for n in set(a) & set(b) if a[n] != b[n])
    print('产物条目 %d / 重打包条目 %d' % (len(a), len(b)))
    print('仅产物有 %d：%s' % (len(only_p), only_p[:5]))
    print('仅重打包有 %d：%s' % (len(only_r), only_r[:5]))
    print('同条目不同字节 %d：%s' % (len(diff), diff[:5]))
    print('结论: %s' % ('一致（原子契约完好）' if not (only_p or only_r or diff) else '不一致'))
    return 0 if not (only_p or only_r or diff) else 1


if __name__ == '__main__':
    sys.exit(main())
