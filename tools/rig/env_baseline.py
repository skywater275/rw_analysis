#!/usr/bin/env python3
"""env_baseline.py — 把**游戏运行环境**做成「已知、可校验、可复位」的基线（挂现有 rig 链条）。

为什么需要：验证结论（GUI 是否崩、回放是否过）会被环境状态污染。实测发现游戏会写：
  · `cache/mods-info.cachedata/**`  ← **mod 信息缓存**（我上次把单位挪进 `mods/units/` 时被缓存下来，
     之后即使单位搬回，缓存仍在 → 直接改变"启动会不会因 mod 单位崩"的表现）
  · `crashes.txt`（历史崩溃累积） · `preferences.ini`（用户设置，游戏会改写）
  · `MANIFEST.json`（本项目 rig 生成）

三条子命令：
  --snapshot   记录基线与不动项指纹 → `{RW}/build/_env-baseline.json` + `{GAME}/ENV-BASELINE.json`
  --verify     对照基线，报告漂移（缓存新增/删除、单位增删、部署 jar 变更等）
  --reset      复位为**已知干净态**：清 `cache/`（游戏会重建）+ 清 `crashes.txt` + 恢复本工具记录的
               单位/共享资源清单一致性（不删用户资源；缺失的从 `build/_asset-backup` 取回）
  --clean-run  复位后跑一次真窗口冒烟并判定（=「已知环境下的已知结论」）

用法:
  python tools/rig/env_baseline.py --snapshot | --verify | --reset [--apply] | --clean-run
"""
import argparse
import ctypes
import ctypes.wintypes as wt
import hashlib
import json
import os
import shutil
import subprocess
import sys
import time
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT / 'tools'))
GAME = ROOT.parent
BASE = ROOT / 'build' / '_env-baseline.json'
MUTABLE_DIRS = ['assets/units', 'mods', 'cache']
MUTABLE_FILES = ['preferences.ini', 'jars/stock.jar', 'jars/d3-era.jar', 'jars/current.jar',
                 'game-lib.jar']


def sha(p, limit=None):
    h = hashlib.sha256()
    with open(p, 'rb') as f:
        while True:
            b = f.read(1 << 20)
            if not b:
                break
            h.update(b)
    return h.hexdigest().upper()


def inventory(rel):
    d = GAME / rel
    out = {}
    if not d.exists():
        return out
    for p in sorted(d.rglob('*')):
        if p.is_file():
            out[p.relative_to(GAME).as_posix()] = [p.stat().st_size, sha(p)[:16]]
    return out


def snapshot():
    snap = {'generated': time.strftime('%Y-%m-%d %H:%M:%S'), 'dirs': {}, 'files': {}}
    for r in MUTABLE_DIRS:
        snap['dirs'][r] = inventory(r)
    for r in MUTABLE_FILES:
        p = GAME / r
        snap['files'][r] = [p.stat().st_size, sha(p)[:16]] if p.exists() else None
    BASE.write_text(json.dumps(snap, ensure_ascii=False, indent=1), encoding='utf-8')
    (GAME / 'ENV-BASELINE.json').write_text(
        json.dumps({k: len(v) if isinstance(v, dict) else v for k, v in snap['dirs'].items()}
                   | {'files': snap['files'], 'generated': snap['generated'],
                      'note': '环境基线快照（由 rw-reverse/tools/rig/env_baseline.py 生成）'},
                   ensure_ascii=False, indent=1), encoding='utf-8')
    print('已快照：%d 个目录项, %d 个文件' %
          (sum(len(v) for v in snap['dirs'].values()), len(snap['files'])))
    for r, v in snap['dirs'].items():
        print('   %-14s %d 个文件' % (r, len(v)))


def verify():
    if not BASE.exists():
        print('无基线，请先 --snapshot')
        return 1
    snap = json.loads(BASE.read_text(encoding='utf-8'))
    drift = 0
    for r in MUTABLE_DIRS:
        cur = inventory(r)
        old = snap['dirs'].get(r, {})
        added = sorted(set(cur) - set(old))
        removed = sorted(set(old) - set(cur))
        changed = sorted(k for k in set(cur) & set(old) if cur[k] != old[k])
        if added or removed or changed:
            drift += len(added) + len(removed) + len(changed)
            print('[漂移] %s：新增 %d / 删除 %d / 变更 %d' % (r, len(added), len(removed), len(changed)))
            for k in (added[:4] + removed[:4] + changed[:4]):
                print('        %s' % k)
        else:
            print('[一致] %s（%d 个文件）' % (r, len(cur)))
    for r in MUTABLE_FILES:
        p = GAME / r
        cur = [p.stat().st_size, sha(p)[:16]] if p.exists() else None
        if cur != snap['files'].get(r):
            drift += 1
            print('[漂移] %s：%s → %s' % (r, snap['files'].get(r), cur))
    print('=== 环境校验：漂移项 %d ===' % drift)
    return 0 if drift == 0 else 2


def window():
    user32 = ctypes.windll.user32
    hwnd = user32.FindWindowW('LWJGL', None)
    if not hwnd:
        return None
    rect = wt.RECT()
    user32.GetWindowRect(hwnd, ctypes.byref(rect))
    return '%dx%d' % (rect.right - rect.left, rect.bottom - rect.top)


def reset(apply_):
    targets = [GAME / 'cache', GAME / 'crashes.txt']
    for t in targets:
        if t.exists():
            print('   %s %s' % ('删除' if apply_ else '将删除', t.relative_to(GAME)))
            if apply_:
                shutil.rmtree(t) if t.is_dir() else t.unlink()
    # 资源一致性：备份里有、游戏里没有的单位取回（不删用户资源）
    bak = ROOT / 'build' / '_asset-backup' / 'units-moved'
    n = 0
    if bak.exists():
        for p in sorted(bak.rglob('*')):
            if p.is_dir() and any(p.glob('*.ini')):
                rel = p.relative_to(bak)
                t = GAME / 'assets' / 'units' / rel
                if not t.exists():
                    print('   %s 取回 units/%s' % ('已' if apply_ else '将', rel))
                    if apply_:
                        t.parent.mkdir(parents=True, exist_ok=True)
                        shutil.move(str(p), str(t))
                        n += 1
    print('复位%s：清理 %d 项，取回 %d 个单位'
          % ('完成' if apply_ else '（dry-run）', len(targets), n))
    if apply_:
        print('提示：cache/ 会在下次启动时由游戏重建 → 这就是「已知干净态」')


def clean_run(seconds=24):
    reset(True)
    smoke = ROOT / 'build' / '_env-smoke.py'
    log = ROOT / 'build' / '_env-smoke.log'
    if log.exists():
        log.unlink()
    java = GAME / 'jvm64' / 'bin' / 'java.exe'
    p = subprocess.Popen([str(java), '-Xmx1000M', '-Dfile.encoding=UTF-8',
                          '-Djava.library.path=.', '-cp', 'game-lib.jar;libs/*',
                          'com.corrodinggames.rts.java.Main', '-log', str(log),
                          '-width', '1024', '-height', '768'],
                         cwd=str(GAME), stdout=subprocess.DEVNULL, stderr=subprocess.STDOUT)
    win = None
    for i in range(seconds):
        time.sleep(1)
        win = win or window()
        if p.poll() is not None:
            break
    win = win or window()
    subprocess.run([sys.executable, str(ROOT / 'tools' / 'capture' / 'stop_game.py')],
                   cwd=str(GAME), capture_output=True)
    txt = log.read_text(encoding='utf-8', errors='replace') if log.exists() else ''
    crash = 'uncaughtException start' in txt
    sig = ''
    if crash:
        import re
        m = re.search(r'cause:(.*)', txt)
        sig = m.group(1)[:70] if m else '?'
    print('=== 已知环境冒烟：窗口=%s 崩溃=%s %s' % (win or '无', crash, sig))
    print('    （cache/ 已清空，这是已知干净态下的结论）')
    return 0 if (win and not crash) else 1


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--snapshot', action='store_true')
    ap.add_argument('--verify', action='store_true')
    ap.add_argument('--reset', action='store_true')
    ap.add_argument('--clean-run', action='store_true')
    ap.add_argument('--apply', action='store_true')
    args = ap.parse_args()
    if args.snapshot:
        snapshot()
    elif args.verify:
        return verify()
    elif args.reset:
        reset(args.apply)
    elif args.clean_run:
        return clean_run()
    else:
        ap.print_help()
    return 0


if __name__ == '__main__':
    sys.exit(main())
