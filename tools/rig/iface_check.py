#!/usr/bin/env python3
"""iface_check.py — 逐条自检《接口规范》(docs/deobfuscation/INTERFACES.md) 里的接触面。

覆盖：
  D1/D2/D3/D6 调试接口：真启动一次带 `-debug 5677:local` 的 GUI → ping → 取版本 → 停止耗时
  D4/D5       观测接口：日志文件生成 + LWJGL 窗口证据
  C1/C2       链条替换契约：REV_CLS 存在 ⇔ 产物条目与原版不同；不存在 ⇔ 相同
  G1/G6       数据输入：回放解析（魔数/版本/记录）+ 环境复位可用
  A1-A4       API：关键模块可导入

用法: python tools/rig/iface_check.py [--json OUT]
退出码: 0 = 全部 PASS
"""
import argparse
import ctypes
import ctypes.wintypes as wt
import json
import os
import re
import subprocess
import sys
import time
import zipfile
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
GAME = ROOT.parent
LOG = ROOT / 'build' / '_iface.log'
R = []


def add(i, ok, detail):
    R.append({'接口': i, '结论': 'PASS' if ok else 'FAIL', '证据': detail})


def window():
    u = ctypes.windll.user32
    hwnd = u.FindWindowW('LWJGL', None)
    if not hwnd:
        return None
    r = wt.RECT()
    u.GetWindowRect(hwnd, ctypes.byref(r))
    b = ctypes.create_unicode_buffer(256)
    u.GetWindowTextW(hwnd, b, 256)
    return {'hwnd': hwnd, 'title': b.value, 'w': r.right - r.left, 'h': r.bottom - r.top,
            'visible': bool(u.IsWindowVisible(hwnd))}


def debug_interfaces():
    """D1/D2/D3/D4/D5/D6：一次真实启动把调试链路全走一遍。"""
    subprocess.run([sys.executable, str(ROOT / 'tools' / 'rig' / 'env_baseline.py'),
                    '--reset', '--apply'], cwd=str(ROOT), capture_output=True)
    if LOG.exists():
        LOG.unlink()
    con = subprocess.run([sys.executable, str(ROOT / 'tools' / 'capture' / 'stop_game.py')],
                         cwd=str(GAME), capture_output=True)
    java = GAME / 'jvm64' / 'bin' / 'java.exe'
    p = subprocess.Popen([str(java), '-Xmx1000M', '-Dfile.encoding=UTF-8',
                          '-Djava.library.path=.', '-cp', 'game-lib.jar;libs/*',
                          'com.corrodinggames.rts.java.Main', '-log', str(LOG),
                          '-width', '1024', '-height', '768', '-debug', '5677:local'],
                         cwd=str(GAME), stdout=subprocess.DEVNULL, stderr=subprocess.STDOUT)
    ready = None
    win = None
    t0 = time.time()
    for i in range(45):
        time.sleep(1)
        win = win or window()
        if LOG.exists():
            txt = LOG.read_text(encoding='utf-8', errors='replace')
            if 'assets/gui/' in txt and '.rcss' in txt:
                ready = i + 1
                break
        if p.poll() is not None:
            break
    add('D3 就绪判据（assets/gui/*.rcss）', ready is not None,
        '第 %s 秒就绪' % ready if ready else '未就绪（进程退出码 %s）' % p.poll())
    add('D4 日志通道（-log）', LOG.exists() and LOG.stat().st_size > 0,
        '日志 %d 字节' % (LOG.stat().st_size if LOG.exists() else 0))
    add('D5 窗口通道（LWJGL）', bool(win), str(win))
    # D1/D2：调试端口 + 脚本通道
    ping = subprocess.run([sys.executable, str(ROOT / 'tools' / 'utils' / 'debug_script.py'),
                           '--verb', 'ping', '--timeout', '8'], cwd=str(ROOT),
                          capture_output=True, text=True, encoding='utf-8', errors='replace')
    add('D1 调试端口 5677', 'pong' in (ping.stdout or '').lower(),
        (ping.stdout or ping.stderr or '').strip()[:90])
    ver = subprocess.run([sys.executable, str(ROOT / 'tools' / 'utils' / 'debug_script.py'),
                          '--verb', 'function', '--timeout', '12', 'root.getVersionName()'],
                         cwd=str(ROOT), capture_output=True, text=True, encoding='utf-8',
                         errors='replace')
    add('D2 脚本通道（function/script）', '1.15' in (ver.stdout or ''),
        (ver.stdout or ver.stderr or '').strip()[:90])
    t0 = time.time()
    subprocess.run([sys.executable, str(ROOT / 'tools' / 'capture' / 'stop_game.py')],
                   cwd=str(GAME), capture_output=True)
    dt = time.time() - t0
    add('D6 停止接口（stop_game.py）', dt < 5.0, '耗时 %.2fs' % dt)


def chain_interfaces():
    """C1/C2：替换契约抽样（REV_CLS 有 ⇒ 产物不同；无 ⇒ 产物相同）。"""
    zs = zipfile.ZipFile(ROOT / 'RustedWarfare' / 'game-lib.jar')
    stock = {n: zs.read(n) for n in zs.namelist() if n.endswith('.class')}
    zp = zipfile.ZipFile(ROOT / 'build' / 'game-lib-reverse.jar')
    import random
    random.seed(7)
    present = sorted(p.relative_to(ROOT / 'build' / 'reverse-classes').as_posix()
                     for p in (ROOT / 'build' / 'reverse-classes').rglob('*.class')
                     if p.relative_to(ROOT / 'build' / 'reverse-classes').as_posix() in stock)
    absent = sorted(n for n in stock if n not in set(present))
    bad = []
    for rel in random.sample(present, min(30, len(present))):
        if rel in zp.namelist() and zp.read(rel) == stock[rel]:
            bad.append(rel + '(有替换类却与原版相同)')
    for rel in random.sample(absent, min(30, len(absent))):
        if rel in zp.namelist() and zp.read(rel) != stock[rel]:
            bad.append(rel + '(无替换类却与原版不同)')
    add('C1/C2 替换契约（存在⇔替换）', not bad, '抽样 60 条，违反 %d 条%s'
        % (len(bad), '' if not bad else '：%s' % bad[:3]))


def data_interfaces():
    """G1/G6：回放解析 + 环境复位。"""
    reps = sorted((GAME / 'replays').glob('*.replay'))
    if not reps:
        add('G1 回放输入（*.replay）', False, 'replays/ 下无回放文件')
    else:
        r = subprocess.run([sys.executable, str(ROOT / 'tools' / 'utils' / 'replay_parser.py'),
                            str(reps[-1])], cwd=str(ROOT), capture_output=True, text=True,
                           encoding='utf-8', errors='replace')
        t = (r.stdout or '') + (r.stderr or '')
        magic = 'rustedWarfareReplay' in t
        ver = re.search(r'version=(\d+)', t)
        rec = re.search(r"记录统计: (\{.*?\})", t, re.S)
        add('G1 回放输入（魔数/版本/记录）', magic and bool(ver),
            '%s；version=%s；记录 %s' % (reps[-1].name[:40], ver.group(1) if ver else '?',
                                        rec.group(1)[:70] if rec else '?'))
    r = subprocess.run([sys.executable, str(ROOT / 'tools' / 'rig' / 'env_baseline.py'),
                        '--reset', '--apply'], cwd=str(ROOT), capture_output=True, text=True,
                       encoding='utf-8', errors='replace')
    add('G6 环境复位（已知干净态）', '复位完成' in (r.stdout or ''), (r.stdout or '').strip()[-70:])


def api_interfaces():
    """A1-A4：关键模块可导入。"""
    sys.path.insert(0, str(ROOT / 'tools'))
    ok, det = True, []
    try:
        from rwlib.config import GAME_LIB, find_javac          # noqa: F401
        det.append('rwlib.config OK')
    except Exception as e:
        ok = False
        det.append('rwlib.config 失败: %s' % e)
    try:
        from fixers.repair_member_names import ClassFile       # noqa: F401
        det.append('ClassFile OK')
    except Exception as e:
        ok = False
        det.append('ClassFile 失败: %s' % e)
    for script in ('tools/gates/replacement_verify.py', 'tools/rig/env_baseline.py',
                   'tools/rig/verify_rig.py', 'tools/rig/switch_jar.py'):
        if not (ROOT / script).exists():
            ok = False
            det.append('缺 %s' % script)
    add('A1-A4 API/CLI 可用', ok, '；'.join(det)[:110])


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--json')
    ap.add_argument('--skip-debug', action='store_true')
    args = ap.parse_args()
    if not args.skip_debug:
        debug_interfaces()
    else:
        add('D1-D6 调试接口', None, '按参数跳过')
    chain_interfaces()
    data_interfaces()
    api_interfaces()
    print('=== 接口自检（docs/deobfuscation/INTERFACES.md）===')
    for r in R:
        print('  [%-7s] %-34s %s' % (r['结论'], r['接口'], r['证据']))
    n_pass = sum(1 for r in R if r['结论'] == 'PASS')
    n_fail = sum(1 for r in R if r['结论'] == 'FAIL')
    print('=== 汇总：PASS %d / FAIL %d ===' % (n_pass, n_fail))
    if args.json:
        Path(args.json).write_text(json.dumps(R, ensure_ascii=False, indent=2), encoding='utf-8')
    return 1 if n_fail else 0


if __name__ == '__main__':
    sys.exit(main())
