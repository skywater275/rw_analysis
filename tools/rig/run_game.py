#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""游戏完全体·测试台 — 日志监管器：实时高亮 + 全量归档 + 数据侧栏 + 崩溃判据。

为什么需要它（2026-09-24 实测结论）：
- 直接 `java.exe` 前台跑到控制台 → 真·实时，但**无法归档/高亮/过滤**；
- 用游戏自带 `-log <文件>` → 能归档，但会把 `System.out` 重定向到文件（`GameLauncher.java:216-227`），
  控制台只剩少量早期输出；
- **管道下 Java 的 stdout 仍是行级实时**（实测 391 行、日志时间戳与到达时刻延迟 0.00 s）
  → 于是可以用本脚本做「边玩边看 + 全量留档 + 高亮 + 侧栏」，两者兼得。

功能：
- 逐行实时打印游戏 stdout/stderr；按类别上色（错误/警告/崩溃/对局事件/AI/网络/资源噪声）
- 原始行**全量**写入 `{GAME}/logs/run-<时间戳>.log`（默认开；`--no-archive` 关）
- `--sidebar N`：每 N 秒把一次实时对局数据摘要插进日志流（复用 live_stats 的 function 通道采样）
- 崩溃判据（KB-D/E/F）：扫 `onGameCrash` / `uncaughtException start` / `OutOfMemoryError`
  —— debugSocket 激活时游戏崩溃**不写** `crashes.txt`，必须扫日志
- 退出时打印摘要（时长、各类行数、崩溃标记、归档路径），有崩溃标记则退出码 2
- Ctrl+C → 终止游戏并打印摘要

Usage:
    python tools/rig/run_game.py                          # 默认：GUI + 调试端口 5677 + 归档
    python tools/rig/run_game.py --no-debug                # 不带调试端口
    python tools/rig/run_game.py --sidebar 10              # 每 10s 插一行实时数据摘要
    python tools/rig/run_game.py --filter 失步             # 只显示含关键词的行（仍全量归档）
    python tools/rig/run_game.py --no-color --no-archive   # 纯文本、不留档
    python tools/rig/run_game.py --extra "-logcolor" "-replay_debug"
"""
import argparse
import ctypes
import os
import re
import subprocess
import sys
import threading
import time
from datetime import datetime
from pathlib import Path

RIG_DIR = Path(__file__).resolve().parent
REPO = RIG_DIR.parent.parent
GAME_ROOT = Path(os.environ.get("RW_GAME_ROOT") or REPO.parent)
LOG_DIR = GAME_ROOT / "logs"

sys.path.insert(0, str(RIG_DIR))

# ── 行分类规则（顺序即优先级） ─────────────────────────────────────────────
RULES = [
    ("crash", (255, 60, 60), re.compile(r"onGameCrash|uncaughtException start|OutOfMemoryError|"
                                        r"Exception in thread \"main\"|FATAL")),
    ("error", (255, 120, 120), re.compile(r"\bERROR\b|\berror:|Exception|Could not find|Cannot open|"
                                          r"failed to|NoSuchFieldError|NoClassDefFound|InvocationTargetException")),
    ("match", (255, 210, 80), re.compile(r"wiped out|has been defeated|inVictory|checksum|don't match|"
                                         r"dont match|extraChecksum|desync|Resync|sync")),
    ("warn", (230, 180, 90), re.compile(r"\bWARN(ING)?\b|warning|deprecated")),
    ("net", (120, 200, 255), re.compile(r"network:|NetEngine|ReliableSocket|ServerSocket|"
                                        r"DebugSocketConnection|PacketDecoder")),
    ("ai", (170, 220, 170), re.compile(r"MissionEngine|ai_debug|AITask|triggerLog|AIStrategy|"
                                       r"firstActivation")),
    ("save", (200, 170, 255), re.compile(r"GameSaver|Saving|saveGame|Loading save|mapSave")),
    ("noise", (110, 110, 110), re.compile(r"getNewTextureHolder|OpenConverted?\(|getActiveDocumentCss|"
                                          r"loadDocument|loadGlyphs|createIndex|Recreating image data")),
]
NOISE_CATS = {"noise"}
RESET = "\x1b[0m"


def classify(line):
    for name, rgb, rx in RULES:
        if rx.search(line):
            return name, rgb
    return "info", (200, 200, 200)


def set_title(text):
    try:
        ctypes.windll.kernel32.SetConsoleTitleW(text)
    except Exception:
        pass


def sidebar_once(port):
    """一次实时数据采样 → 单行摘要（失败则返回 None）。"""
    try:
        from live_stats import sample  # 同目录
        data, _errs, _el = sample("127.0.0.1", port, teams=4)
        return ("[数据] 界面=%s 玩家=%s+%s 连接=%s 失步=%s 通过=%s PID=%s" % (
            data.get("当前界面"), data.get("人类玩家"), data.get("玩家+AI"), data.get("连接数"),
            data.get("失步错误"), data.get("失步校验通过"), data.get("进程 PID")))
    except Exception as e:  # 端口未就绪/游戏已退出
        return "[数据] 暂不可用（%s）" % str(e)[:60]


def sidebar_loop(stop_evt, port, interval, out, lock):
    while not stop_evt.wait(interval):
        line = sidebar_once(port)
        with lock:
            print("\x1b[38;2;120;255;200m%s\x1b[0m" % line, flush=True)
            if out:
                out.write(line + "\n")
                out.flush()


def set_prefs(pairs):
    """启动前把 preferences.ini 的若干键钉成定值（差分采集的去噪控制）。

    为什么需要：游戏每次启动会把 `nextBackgroundMap` 自增并落盘（GameEngine.x()），
    于是「连续两次启动」必然加载不同的菜单背景图（menu1→2→3 循环），日志差异里
    绝大部分其实是**地图不同**而不是逆向缺陷。先把该键钉成定值，两次采集才可比。
    """
    prefs = GAME_ROOT / "preferences.ini"
    if not prefs.exists():
        print("[警告] 找不到 %s，--pref 跳过" % prefs)
        return
    raw = prefs.read_bytes()
    bak = prefs.with_suffix(".ini.rwrig-bak")
    if not bak.exists():
        bak.write_bytes(raw)
        print("[备份] preferences.ini → preferences.ini.rwrig-bak（仅首次）")
    text = raw.decode("utf-8", "replace")
    crlf = "\r\n" in text
    lines = text.replace("\r\n", "\n").split("\n")
    wanted = {}
    for p in pairs:
        k, _, v = p.partition(":")
        wanted[k.strip()] = v.strip()
    hit = set()
    for i, ln in enumerate(lines):
        k = ln.split(":", 1)[0].strip()
        if k in wanted:
            lines[i] = "%s:%s" % (k, wanted[k])
            hit.add(k)
    for k, v in wanted.items():
        if k not in hit:
            lines.append("%s:%s" % (k, v))
            print("[钉值] 新增 %s:%s" % (k, v))
        else:
            print("[钉值] %s = %s" % (k, v))
    out = "\n".join(lines)
    if crlf:
        out = out.replace("\n", "\r\n")
    prefs.write_bytes(out.encode("utf-8"))


def main():
    ap = argparse.ArgumentParser(description="游戏日志监管器（实时高亮 + 归档 + 数据侧栏）")
    ap.add_argument("--debug-port", type=int, default=5677, help="调试端口（0 = 不开调试服务）")
    ap.add_argument("--no-debug", action="store_true", help="等价于 --debug-port 0")
    ap.add_argument("--sidebar", type=float, default=0.0, help="每 N 秒插入一行实时数据摘要（0=关）")
    ap.add_argument("--filter", help="只显示含该关键词的行（仍全量归档）")
    ap.add_argument("--no-color", action="store_true")
    ap.add_argument("--no-archive", action="store_true")
    ap.add_argument("--show-noise", action="store_true", help="显示资源加载类噪声行（默认折叠为灰点）")
    ap.add_argument("--logcolor", dest="logcolor", action="store_true", default=True,
                    help="彩色日志开关（默认开，透传 -logcolor 给游戏）")
    ap.add_argument("--no-logcolor", dest="logcolor", action="store_false")
    ap.add_argument("--replay-debug", action="store_true", help="透传 -replay_debug（回放/动作面板调试行）")
    ap.add_argument("--debugscript", help="透传 -debugscript <文件>（启动即执行调试脚本）")
    ap.add_argument("--max-seconds", type=float, default=0.0,
                    help="运行满 N 秒后自动停机并归档（0=跑到游戏自己退出；差分采集用）")
    ap.add_argument("--tag", default="run", help="归档文件名前缀（默认 run，差分采集建议 diff-<profile>）")
    ap.add_argument("--pref", action="append", default=[],
                    metavar="KEY:VALUE", help="启动前钉住 preferences.ini 的键（可重复；差分采集去噪）")
    ap.add_argument("--extra", default="", help='追加给游戏的参数整串（shlex 切分），如 --extra "-width 1280 -height 720"')
    args = ap.parse_args()

    java = GAME_ROOT / "jvm64" / "bin" / "java.exe"
    if not java.exists():
        print("[错误] 缺少 %s" % java)
        return 1
    argv = [str(java), "-Xmx1000M", "-Dfile.encoding=UTF-8", "-Dsun.stdout.encoding=UTF-8",
            "-Dsun.stderr.encoding=UTF-8", "-Djava.library.path=.", "-cp", "game-lib.jar;libs/*",
            "com.corrodinggames.rts.java.Main"]
    port = 0 if args.no_debug else args.debug_port
    if port:
        argv += ["-debug", "%d:local" % port]
    if args.logcolor:
        argv.append("-logcolor")
    if args.replay_debug:
        argv.append("-replay_debug")
    if args.debugscript:
        argv += ["-debugscript", args.debugscript]
    if args.extra:
        import shlex
        argv += shlex.split(args.extra)

    archive = None
    if not args.no_archive:
        LOG_DIR.mkdir(exist_ok=True)
        archive = LOG_DIR / ("%s-%s.log" % (args.tag, datetime.now().strftime("%Y%m%d-%H%M%S")))
    set_title("Rusted Warfare — 实时日志（监管器）")
    print("=" * 78)
    print(" 日志监管器 | 归档: %s" % (archive if archive else "(关)"))
    print(" 参数: %s" % " ".join(argv[3:]))
    print(" 颜色: crash=红 error=浅红 match=黄 warn=橙 net=蓝 ai=绿 save=紫 noise=灰")
    print("=" * 78)

    t0 = time.time()
    counts = {}
    crash_lines = []
    filt = re.compile(re.escape(args.filter)) if args.filter else None
    if args.pref:
        set_prefs(args.pref)
    fh = open(archive, "w", encoding="utf-8") if archive else None
    lock = threading.Lock()

    try:
        proc = subprocess.Popen(argv, cwd=str(GAME_ROOT), stdout=subprocess.PIPE,
                                stderr=subprocess.STDOUT, text=True, encoding="utf-8",
                                errors="replace", bufsize=1)
    except OSError as e:
        print("[错误] 启动失败: %s" % e)
        if fh:
            fh.close()
        return 1

    stop_evt = threading.Event()
    side = None
    if args.sidebar > 0 and port:
        side = threading.Thread(target=sidebar_loop, args=(stop_evt, port, args.sidebar, fh, lock), daemon=True)
        side.start()

    def deadline_kill():
        """限时停机：到点温和终止游戏（差分采集必须收敛，否则会挂到用户手动关窗）。"""
        if not stop_evt.wait(args.max_seconds):
            with lock:
                print("\n[监管器] 已运行 %.0fs，按 --max-seconds 自动停机 …" % args.max_seconds, flush=True)
            try:
                proc.terminate()
            except Exception:
                pass

    if args.max_seconds > 0:
        threading.Thread(target=deadline_kill, daemon=True).start()
        print("[监管器] 限时 %.0fs 后自动停机（差分采集模式）" % args.max_seconds)

    try:
        for raw in proc.stdout:
            line = raw.rstrip("\n")
            if fh:
                fh.write(line + "\n")
                fh.flush()          # 逐行落盘：硬中断也能留住已产生的日志
            if not line.strip():
                continue
            cat, rgb = classify(line)
            counts[cat] = counts.get(cat, 0) + 1
            if cat == "crash":
                crash_lines.append(line)
            if filt and not filt.search(line):
                continue
            if cat in NOISE_CATS and not args.show_noise:
                continue
            with lock:
                if args.no_color:
                    print(line, flush=True)
                else:
                    print("\x1b[38;2;%d;%d;%dm%s\x1b[0m" % (rgb[0], rgb[1], rgb[2], line), flush=True)
    except KeyboardInterrupt:
        print("\n[监管器] 收到中断，终止游戏 …", flush=True)
    finally:
        stop_evt.set()
        try:
            proc.terminate()
            proc.wait(timeout=15)
        except Exception:
            try:
                proc.kill()
            except Exception:
                pass
        if fh:
            fh.flush()
            fh.close()

    dur = time.time() - t0
    print("=" * 78)
    print(" 游戏已退出 | 运行 %.1fs | 各类行数: %s" % (
        dur, ", ".join("%s=%d" % (k, v) for k, v in sorted(counts.items(), key=lambda x: -x[1]))))
    if archive:
        print(" 归档: %s（%d 行）" % (archive, sum(counts.values())))
    if crash_lines:
        print(" ⚠ 崩溃判据命中 %d 行，示例: %s" % (len(crash_lines), crash_lines[0][:100]))
    else:
        print(" 崩溃判据: 未命中（onGameCrash / uncaughtException start / OutOfMemoryError）")
    print("=" * 78)
    return 2 if crash_lines else 0


if __name__ == "__main__":
    sys.exit(main())
