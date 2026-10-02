#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""GUI 冒烟验证（真窗口，非 headless）：临时把产物 jar 部署到游戏根，启动真实 GUI 窗口
约 N 秒，采集窗口证据（标题/句柄/尺寸/可见/前台）与日志崩溃判据，然后**恢复原 jar**。

软编码说明
----------
路径 / 观察秒数 / 窗口类名 / 崩溃判据词表 / 环境性崩溃特征 / 归因扫描行数
全部来自 `rwlib.tuning`（`smoke.*`），不再写死在脚本里。

退出码（历史缺陷修复：本脚本曾**恒返回 0**，崩溃只能靠人看 stdout）
--------
0 = 拿到窗口且无崩溃判据命中；
1 = 命中崩溃判据或进程异常退出（判据见 stdout 的 `崩溃判据命中:` / `cause:` 行）；
2 = 连日志都没生成（无法判定）。

用法: python build/_gui_smoke.py [--seconds N] [--product PATH]
"""
import argparse
import ctypes
import ctypes.wintypes as wt
import shutil
import subprocess
import sys
import time
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT / 'tools'))

from rwlib.tuning import T  # noqa: E402

GAME = ROOT.parent
JAR = GAME / 'game-lib.jar'


def window_info(win_cls):
    user32 = ctypes.windll.user32
    out = []
    hwnd = user32.FindWindowW(win_cls, None)
    if not hwnd:
        return out
    rect = wt.RECT()
    user32.GetWindowRect(hwnd, ctypes.byref(rect))
    buf = ctypes.create_unicode_buffer(512)
    user32.GetWindowTextW(hwnd, buf, 512)
    out.append(dict(hwnd=hwnd, title=buf.value,
                    w=rect.right - rect.left, h=rect.bottom - rect.top,
                    visible=bool(user32.IsWindowVisible(hwnd)),
                    foreground=(user32.GetForegroundWindow() == hwnd)))
    return out


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--seconds', type=int, default=int(T.get('smoke.seconds')))
    ap.add_argument('--product', default=str(ROOT / 'build' / 'game-lib-reverse.jar'))
    args = ap.parse_args()

    prod = Path(args.product)
    assert prod.exists(), prod
    log = T.path('smoke.log')
    crash_log = T.path('smoke.crash_log')
    backup = T.path('smoke.backup')
    win_cls = str(T.get('smoke.window_class'))
    markers = list(T.get('smoke.crash_markers'))
    env_signs = list(T.get('smoke.env_crash_signs'))
    head_lines = int(T.get('smoke.cause_scan_lines'))
    frames_max = int(T.get('smoke.cause_frames'))

    shutil.copy2(JAR, backup)
    st = JAR.stat()
    shutil.copy2(prod, JAR)
    import os
    os.utime(JAR, ns=(st.st_atime_ns, st.st_mtime_ns))     # 回写 mtime（项目硬约束）
    print('部署 %s → %s' % (prod.name, JAR), flush=True)

    # JVM 临时目录（2026-09-28 修复）：受限文件沙箱下真实 %TEMP% 不可写 ⇒
    # `javax.imageio.IIOException: Can't create cache file!` → `SlickException:
    # Failed to set the icon` → 进程在窗口出现前退出（**原版 jar 同样触发**，
    # 故此前 V5 恒为「窗口=none」，与本批产物无关）。
    # 仅 `-Djava.io.tmpdir` 覆盖不到 ImageIO 缓存目录，必须同时设 TEMP/TMP；
    # 与 `mcp_game_server._launch_game` 的既有修法保持一致。
    jvm_tmp = GAME / '.tmp' / 'jvm-tmp'
    jvm_tmp.mkdir(parents=True, exist_ok=True)
    child_env = dict(os.environ)
    child_env['TEMP'] = str(jvm_tmp)
    child_env['TMP'] = str(jvm_tmp)
    # JVM 自身 stdout/stderr 落盘：原先 DEVNULL ⇒ 启动期异常无任何证据可查
    jvm_log = log.with_name(log.stem + '.jvm.log')

    java = GAME / 'jvm64' / 'bin' / 'java.exe'
    cmd = [str(java), '-Xmx1000M', '-Dfile.encoding=UTF-8', '-Djava.library.path=.',
           '-Djava.io.tmpdir=%s' % jvm_tmp,
           '-cp', 'game-lib.jar;libs/*', 'com.corrodinggames.rts.java.Main',
           '-log', str(log), '-width', '1024', '-height', '768']
    crashed_early = False
    jvm_log.parent.mkdir(parents=True, exist_ok=True)
    fh = open(jvm_log, 'wb')
    try:
        p = subprocess.Popen(cmd, cwd=str(GAME), env=child_env, stdout=fh,
                             stderr=subprocess.STDOUT)
        print('进程 PID %d，观察 %d 秒' % (p.pid, args.seconds), flush=True)
        ev = []
        for i in range(args.seconds):
            time.sleep(1)
            if i % 5 == 4:
                ev.extend(window_info(win_cls))
            if p.poll() is not None:
                crashed_early = True
                print('!! 进程在第 %d 秒退出（码 %s）' % (i + 1, p.returncode), flush=True)
                break
        for w in ev[:4]:
            print('窗口证据: %s' % w, flush=True)
        if not ev:
            print('窗口证据: 未捕获到 %s 窗口' % win_cls, flush=True)
        if crashed_early:
            fh.flush()
            tail = jvm_log.read_text(encoding='utf-8', errors='replace').splitlines()[-12:]
            print('JVM 日志尾部（%s）:' % jvm_log.name, flush=True)
            for l in tail:
                print('  ' + l, flush=True)
    finally:
        try:
            fh.close()
        except OSError:
            pass
        try:
            subprocess.run([sys.executable, str(ROOT / 'tools' / 'capture' / 'stop_game.py')],
                           cwd=str(GAME), capture_output=True)
        finally:
            shutil.copy2(backup, JAR)
            os.utime(JAR, ns=(st.st_atime_ns, st.st_mtime_ns))
            print('已恢复部署 jar（mtime 已回写）', flush=True)

    if not log.exists():
        print('未生成日志文件', flush=True)
        print('崩溃判据命中: 无（原因：无日志，结论不可信）', flush=True)
        return 2

    txt = log.read_text(encoding='utf-8', errors='replace')
    hits = []
    for k in markers:
        n = txt.count(k)
        if n:
            hits.append('%s × %d' % (k, n))
    print('日志行数 %d；崩溃判据命中: %s' % (len(txt.splitlines()), '、'.join(hits) or '无'),
          flush=True)
    # 环境性崩溃分类（工作区 assets/units 里的 mod 单位会让**原版同样崩**）
    env_hits = [k for k in env_signs if k in txt]
    if env_hits and hits:
        print('!! 其中属**环境性崩溃**（原版 jar 同样触发）: %s' % env_hits, flush=True)
        print('   判定口径: `tools/rig/switch_jar.py --to stock --apply` + 同一冒烟对照',
              flush=True)

    keep = [l for l in txt.splitlines()
            if any(k in l for k in ('uncaughtException', 'onGameCrash', 'Error', 'Exception'))]
    lines = txt.splitlines()
    start = None
    for i, l in enumerate(lines):
        if any(k in l for k in markers):
            start = i
            break
    cause = None
    if start is not None:
        exc = lines[start].strip()
        frames = []
        for l in lines[start + 1:start + 1 + head_lines]:
            s = l.strip()
            if s.startswith('at '):
                frames.append(s)
            if len(frames) >= frames_max:
                break
        cause = '%s @ %s' % (exc[:120], ' | '.join(frames))
        print('cause: %s' % cause, flush=True)
    else:
        print('cause: (无异常块)', flush=True)

    mode = 'a' if bool(T.get('smoke.append_crash_log')) else 'w'
    crash_log.parent.mkdir(parents=True, exist_ok=True)
    with open(crash_log, mode, encoding='utf-8') as f:
        f.write('\n=== %s | product=%s | 崩溃判据: %s | 早退: %s ===\n'
                % (time.strftime('%Y-%m-%d %H:%M:%S'), prod.name,
                   '、'.join(hits) or '无', crashed_early))
        f.write('\n'.join(keep)[:20000])
        if cause:
            f.write('\n=== cause ===\n' + cause + '\n')
    print('崩溃证据 → %s（%s）' % (crash_log.name, '追加' if mode == 'a' else '覆盖'), flush=True)

    if hits or crashed_early:
        return 1
    return 0


if __name__ == '__main__':
    sys.exit(main())
