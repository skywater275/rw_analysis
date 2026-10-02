#!/usr/bin/env python3
"""A/B 旁路重建运行器（快照 → stage1 → 恢复池 → stage2 → **再次恢复池** ✓ → 校验池计数 ✓）。
用法: python tools/rig/ab_run.py <out_jar> [--pool build/reverse-classes] [--snapshot <dir>]
"""
import argparse
import shutil
import subprocess
import sys
import zipfile
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]


def count(d):
    return sum(1 for _ in d.rglob('*.class'))


def safe_copy(src, dst):
    """★ 安全拷贝：先清空目标再 `Copy-Item "src\\*"`（PowerShell 语义）—— 等价于「替换」✓。"""
    if dst.exists():
        shutil.rmtree(dst)
    dst.mkdir(parents=True, exist_ok=True)
    for item in src.iterdir():
        tgt = dst / item.name
        if item.is_dir():
            shutil.copytree(item, tgt)
        else:
            shutil.copy2(item, tgt)


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('out_jar', help='旁路产物 jar，如 build/_rXX-fix.jar')
    ap.add_argument('--pool', default='build/reverse-classes')
    ap.add_argument('--snapshot', default=None, help='池快照目录（默认 <jar 同名>-pool）')
    args = ap.parse_args()

    pool = ROOT / args.pool
    out_jar = ROOT / args.out_jar
    snap = ROOT / (args.snapshot or (str(out_jar)[:-4] + '-pool'))
    n0 = count(pool)
    print('[1/6] 池初始 = %d' % n0)
    safe_copy(pool, snap)
    assert count(snap) == n0, '快照计数不符 ✗'
    print('      快照 → %s（%d）' % (snap.relative_to(ROOT).as_posix(), count(snap)))

    env = {'RW_REVERSE_OUT_JAR': str(out_jar)}
    import os
    e = dict(os.environ); e.update(env)

    print('[2/6] stage 1：build_reverse_jar --apply')
    r = subprocess.run([sys.executable, 'tools/fixers/build_reverse_jar.py', '--apply'],
                       cwd=str(ROOT), capture_output=True, text=True, errors='replace', env=e)
    for line in (r.stdout or '').splitlines():
        if '错误 CSV' in line:
            print('      %s' % line.strip())
    safe_copy(snap, pool)
    print('      恢复池 → %d' % count(pool))

    print('[3/6] stage 2：build_conflict_pass --apply')
    # ★★★ 2026-10-01 第 80 轮（PENDING RS-59）：**运行前截断错误日志** ✓
    #   为什么必须：`build/_ab-errors.txt` 是**追加**写入 ✗（实测跨轮单调增长：
    #   2,999,508 → 3,282,648 → **4,503,018** 行 ✓，而最后一次的版本**落地了修复**
    #   错误本应**更少** ✗）⇒ **跨轮比较其绝对行数无效** ✗✗
    #   （RS-56 曾据「345,706 → 376,987 = +9.0%」判断净负 ✗ —— 该数不可作依据 ✓；
    #    其回退结论仍成立 ✓，因另有独立证据：`loadImageFromResource` 自身 6,368 → 7,008 ✓）
    #   ⇒ 截断后，日志即代表**本次运行**的真实错误 ✓ ⇒ 第二度量才可用 ✓。
    err_log = ROOT / 'build' / '_ab-errors.txt'
    if err_log.exists():
        before = err_log.stat().st_size
        err_log.unlink()
        print('      ★ 已截断旧错误日志（%d 字节 ✓）⇒ 本次运行的错误可精确计数 ✓' % before)
    r2 = subprocess.run([sys.executable, 'tools/fixers/build_conflict_pass.py', '--world', 'all',
                         '--include-skip', '--apply', '--dump-errors',
                         str(ROOT / 'build' / '_ab-errors.txt')],
                        cwd=str(ROOT), capture_output=True, text=True, errors='replace', env=e)
    for line in (r2.stdout or '').splitlines():
        if '产出类' in line:
            print('      %s' % line.strip())
    if err_log.exists():
        print('      本次错误日志 = %.1f MB ✓（未追加 ✓）' % (err_log.stat().st_size / 1e6))

    print('[4/6] ★ stage 2 之后**再次恢复池**（stage 2 会把产出类写回池 ✗）')
    safe_copy(snap, pool)
    n1 = count(pool)
    print('      池 = %d（应等于 %d）%s' % (n1, n0, '✓' if n1 == n0 else '✗ 不一致！'))

    print('[5/6] 产物摘要')
    if out_jar.exists():
        with zipfile.ZipFile(out_jar) as z:
            n = sum(1 for x in z.namelist() if x.endswith('.class'))
        print('      %s ⇒ %d 个类' % (out_jar.relative_to(ROOT).as_posix(), n))
    else:
        print('      ✗ 产物不存在')

    print('[6/6] 完成 —— 池已复原 ✓，可安全进入指标测量')
    return 0 if n1 == n0 else 1


if __name__ == '__main__':
    sys.exit(main())
