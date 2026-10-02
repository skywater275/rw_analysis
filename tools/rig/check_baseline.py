#!/usr/bin/env python3
"""基线锁自检：核对 `BASELINE.sha256` 里的**冻结不变量**是否仍成立。

Usage:
    python tools/rig/check_baseline.py [--file BASELINE.sha256]

为什么需要它
------------
`README.md` 声明「基线锁：原版 `game-lib.jar` 与只读哨兵 `build-skip.txt` **冻结不变**」，
但仓库里**长期没有 `BASELINE.sha256` 这个文件**，也没有任何校验器
（全仓 grep `BASELINE.sha256` 在 2026-10-01 之前 **0 命中**）⇒ 该声明**不可验证**。
本工具把这份声明变成可执行的判据：逐条重算 sha256 并与清单比对。

退出码：0 = 全部一致；1 = 有漂移或缺失。
"""
import argparse
import hashlib
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]


def sha256(p: Path) -> str:
    h = hashlib.sha256()
    with open(p, 'rb') as f:
        for chunk in iter(lambda: f.read(1 << 20), b''):
            h.update(chunk)
    return h.hexdigest()


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--file', default=str(ROOT / 'BASELINE.sha256'))
    args = ap.parse_args()

    p = Path(args.file)
    if not p.exists():
        print('缺少清单: %s' % p)
        return 1
    rows = []
    for line in p.read_text(encoding='utf-8').splitlines():
        line = line.strip()
        if not line or line.startswith('#'):
            continue
        parts = line.split(None, 1)
        if len(parts) == 2:
            rows.append((parts[0], parts[1].strip()))

    ok = 0
    bad = []
    for want, rel in rows:
        q = (ROOT / rel).resolve()
        if not q.exists():
            bad.append((rel, '缺失', want[:16], '-'))
            print('  [MISS] %-34s 文件不存在' % rel)
            continue
        got = sha256(q)
        if got == want:
            ok += 1
            print('  [ OK ] %-34s %s' % (rel, got[:16]))
        else:
            bad.append((rel, '漂移', want[:16], got[:16]))
            print('  [FAIL] %-34s 期望 %s / 实测 %s' % (rel, want[:16], got[:16]))

    print('\n基线锁: %d/%d OK' % (ok, len(rows)))
    if bad:
        print('⚠️ 漂移/缺失 %d 项：' % len(bad))
        for rel, why, a, b in bad:
            print('   %-34s %s (%s → %s)' % (rel, why, a, b))
        return 1
    print('✅ 全部冻结不变量未变')
    return 0


if __name__ == '__main__':
    sys.exit(main())
