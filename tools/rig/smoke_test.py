#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""游戏完全体·测试台 — 启动冒烟测试（真实 GUI，非回放回归）。

流程（全部在一个进程内完成，避免调用方进程回收掉游戏子进程）：
  1) 用 jvm64 的 javaw 以调试参数启动游戏（**不加 -nodisplay**：硬约束要求真实 GUI 窗口）
  2) 轮询取证：调试端口 5677 / LWJGL 类窗口（可见）/ `-log` 文件尾部 / 进程 stdout
  3) 用 OpenProcess+TerminateProcess 终止，并轮询「进程消失 + 端口释放」（不看返回码）
  4) 输出结构化 JSON 结论

⚠️ 已踩过的坑（务必保留）：
  · **`-log` 必须带文件名**（`GameLauncher.java:216-221`：`if (arg.equals("-log"))` 后取下一个参数，
    缺失则打印 `-log requires parameters` 并 `System.exit(1)`）。裸 `-log` 会让游戏**立即退出**，
    表现为「无窗口 / 无端口 / 无日志」的假故障。
  · 崩溃判据必须扫**被控端 `-log` 文件**（debugSocket 激活时游戏崩溃**不写** crashes.txt）。

Usage:
    python tools/rig/smoke_test.py                  # 默认等待 45 s 后取证并终止
    python tools/rig/smoke_test.py --wait 60
    python tools/rig/smoke_test.py --keep-running    # 取证后不终止（人工接手）
    python tools/rig/smoke_test.py --no-debug        # 不带调试端口（纯启动冒烟）
"""
import argparse
import ctypes
import json
import os
import socket
import subprocess
import sys
import tempfile
import time
from ctypes import wintypes
from pathlib import Path

RIG_DIR = Path(__file__).resolve().parent
REPO = RIG_DIR.parent.parent
GAME_ROOT = Path(os.environ.get("RW_GAME_ROOT") or REPO.parent)
PORT = 5677
LOG_NAME = "lastrun-smoke.log"
LOG = GAME_ROOT / LOG_NAME

user32 = ctypes.windll.user32
kernel32 = ctypes.windll.kernel32
PROCESS_TERMINATE = 0x0001


def port_open(port=PORT, host="127.0.0.1"):
    with socket.socket() as s:
        s.settimeout(0.4)
        return s.connect_ex((host, port)) == 0


def windows_of_class(class_name="LWJGL"):
    found = []

    @ctypes.WINFUNCTYPE(ctypes.c_bool, wintypes.HWND, wintypes.LPARAM)
    def cb(hwnd, _):
        buf = ctypes.create_unicode_buffer(256)
        user32.GetClassNameW(hwnd, buf, 256)
        if buf.value == class_name:
            t = ctypes.create_unicode_buffer(256)
            user32.GetWindowTextW(hwnd, t, 256)
            r = wintypes.RECT()
            user32.GetWindowRect(hwnd, ctypes.byref(r))
            found.append({
                "hwnd": int(hwnd), "title": t.value, "class": buf.value,
                "visible": bool(user32.IsWindowVisible(hwnd)),
                "w": r.right - r.left, "h": r.bottom - r.top,
            })
        return True

    user32.EnumWindows(cb, 0)
    return found


def tail(path, n=40):
    if not path or not Path(path).exists():
        return []
    return Path(path).read_text(encoding="utf-8", errors="replace").splitlines()[-n:]


def terminate(pid):
    t0 = time.time()
    h = kernel32.OpenProcess(PROCESS_TERMINATE, False, pid)
    ok = bool(h) and bool(kernel32.TerminateProcess(h, 1))
    if h:
        kernel32.CloseHandle(h)
    while time.time() - t0 < 20:
        out = subprocess.run(["tasklist", "/FI", f"PID eq {pid}"],
                             capture_output=True, text=True, errors="replace").stdout
        if str(pid) not in out:
            break
        time.sleep(0.2)
    while time.time() - t0 < 20 and port_open():
        time.sleep(0.2)
    return {"terminate_called": ok, "pid_gone_s": round(time.time() - t0, 2),
            "port_free": not port_open(), "elapsed_s": round(time.time() - t0, 2)}


def main():
    ap = argparse.ArgumentParser(description="测试台启动冒烟测试 (真实 GUI)")
    ap.add_argument("--wait", type=int, default=45, help="启动后等待秒数 (默认 45)")
    ap.add_argument("--keep-running", action="store_true", help="取证后不终止")
    ap.add_argument("--no-debug", action="store_true", help="不带 -debug 端口")
    args = ap.parse_args()

    javaw = GAME_ROOT / "jvm64" / "bin" / "javaw.exe"
    if not javaw.exists():
        print(json.dumps({"ok": False, "reason": "缺少 jvm64/bin/javaw.exe"}, ensure_ascii=False))
        return 1
    if port_open():
        print(json.dumps({"ok": False, "reason": f"端口 {PORT} 已被占用，先停掉现有游戏"}, ensure_ascii=False))
        return 1
    if LOG.exists():
        LOG.unlink()

    argv = [str(javaw), "-Xmx1000M", "-Dfile.encoding=UTF-8", "-Djava.library.path=.",
            "-cp", "game-lib.jar;libs/*", "com.corrodinggames.rts.java.Main"]
    if not args.no_debug:
        argv += ["-debug", f"{PORT}:local"]
    argv += ["-log", LOG_NAME]

    outf = Path(tempfile.gettempdir()) / "rw_smoke_stdout.txt"
    errf = Path(tempfile.gettempdir()) / "rw_smoke_stderr.txt"
    print(f"[启动] {' '.join(argv[3:])}   (cwd={GAME_ROOT})", flush=True)
    with open(outf, "w", encoding="utf-8", errors="replace") as o, \
            open(errf, "w", encoding="utf-8", errors="replace") as e:
        proc = subprocess.Popen(argv, cwd=str(GAME_ROOT), stdout=o, stderr=e)

    ev = {"pid": proc.pid, "port_seen_at_s": None, "window_seen_at_s": None, "windows": []}
    t0 = time.time()
    while time.time() - t0 < args.wait:
        time.sleep(1.0)
        if ev["port_seen_at_s"] is None and port_open():
            ev["port_seen_at_s"] = round(time.time() - t0, 1)
        w = windows_of_class()
        if w and ev["window_seen_at_s"] is None:
            ev["window_seen_at_s"] = round(time.time() - t0, 1)
        ev["windows"] = w
        if proc.poll() is not None:
            ev["exited_early"] = proc.returncode
            ev["exited_early_at_s"] = round(time.time() - t0, 1)
            break

    ev["log_lines"] = tail(LOG)
    ev["stdout_tail"] = tail(outf, 15)
    ev["stderr_tail"] = tail(errf, 15)
    ev["crash_markers"] = [l for l in ev["log_lines"] + ev["stdout_tail"]
                           if "uncaughtException start" in l or "onGameCrash" in l]
    visible = [w for w in ev["windows"] if w["visible"] and w["w"] > 200 and w["h"] > 200]
    ev["fatal_hint"] = [l for l in ev["stdout_tail"] if "requires parameters" in l or "Cannot open log" in l]
    ok = bool(visible) and not ev["crash_markers"] and not ev["fatal_hint"] and not ev.get("exited_early")
    if not args.no_debug:
        ok = ok and bool(ev["port_seen_at_s"])

    if args.keep_running:
        ev["stopped"] = False
    else:
        ev["stopped"] = True
        ev["stop_result"] = terminate(proc.pid)

    ev["ok"] = ok and (ev.get("stop_result", {}).get("port_free", True))
    print(json.dumps(ev, ensure_ascii=False, indent=2))
    return 0 if ev["ok"] else 1


if __name__ == "__main__":
    sys.exit(main())
