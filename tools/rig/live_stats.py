#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""实时对局数据面板 — 通过 Debug Socket 的 `function` 通道轮询游戏状态。

设计要点（都是实测/源码结论，勿随手改）：
1. **每次采样复用一条连接**：`DebugServer` 的 accept 循环是**串行**的（一个会话跑完才接下一个，
   见 PENDING #33「会话级阻塞」，实测阻塞 6.6 s）。因此不能「每条命令各开一个连接」——那样
   每秒几十次连接会把 socket 占满并阻塞其它客户端（MCP 工具面）。本工具每次采样 connect 一次、
   在同一条连接上依次发全部命令并读取，采样结束立即关闭，采样间隙让出 socket。
2. **异常安全**：走 `function` 通道（服务端 `tryToCatchCrash=true`），坏表达式返回 `crash\\n…`
   而不会崩游戏；因此对历史上有 NPE 记录的取值方法（如 `debug.getPlayerName`）也可安全试探。
3. **只读**：只发送取值表达式，不发 set*/create*/kill*/checkDesync/plainTextDebugSave 等会改变
   对局状态或触发同步校验的命令。
4. **事件差分**：与上一次采样比较，打印「队伍被消灭 / 失步计数上升 / 界面切换 / 玩家人数变化」。

Usage:
    python tools/rig/live_stats.py                     # 1 Hz 实时面板（ANSI 原地刷新）
    python tools/rig/live_stats.py --interval 2 --teams 8
    python tools/rig/live_stats.py --unlock            # 先解锁 allFeatures 再采样
    python tools/rig/live_stats.py --once --json       # 单次采样 → JSON
    python tools/rig/live_stats.py --append            # 逐行追加（不做清屏，便于重定向/留档）
    python tools/rig/live_stats.py --log data.jsonl    # 每次采样追加一行 JSONL
    python tools/rig/live_stats.py --wait 90           # 等游戏端口就绪（最多 90 s）
"""
import argparse
import json
import os
import socket
import sys
import time
from datetime import datetime
from pathlib import Path

RIG_DIR = Path(__file__).resolve().parent
REPO = RIG_DIR.parent.parent
sys.path.insert(0, str(REPO))
sys.path.insert(0, str(REPO / "tools"))
from rwlib.debugproto import (  # noqa: E402
    DEFAULT_HOST, DEFAULT_PORT, NUL_MODIFIED_UTF8, port_listening, strip_terminator, terminator_for,
)

FEATURE_MAGIC = "221FC410BD29D786"      # DebugUI.enableFeatures 要求的魔法前缀（源码实证）

GLOBAL_ROWS = [
    ("版本",         "root.getVersionName()",                    "str"),
    ("当前界面",     "root.getCurrentDocumentPath()",             "str"),
    ("使用 Mod",     "root.usingMods()",                         "bool"),
    ("进程 PID",     "debug.currentPid()",                       "int"),
    ("联机中",       "debug.isNetworkGameActive()",              "bool"),
    ("人类玩家",     "debug.numberOfHumanPlayers()",             "int"),
    ("玩家+AI",      "debug.numberOfPlayersPlusAI()",            "int"),
    ("连接数",       "debug.numberOfPlayerConnections()",        "int"),
    ("失步错误",     "debug.getNumberOfDesyncErrors()",           "int"),
    ("失步校验通过", "debug.getNumberOfDesyncPasses()",           "int"),
    ("重同步收发",   "debug.getNumberOfResyncSendsOrRecv()",      "int"),
    ("自定义单位类型", "debug.getMaxCustomUnitTypeId()",           "int"),
]

TEAM_ROWS = [
    ("被消灭", "debug.isTeamWipedOut(%d)", "bool"),
    ("被击败", "debug.isTeamDefeated(%d)", "bool"),
    ("胜利",   "debug.isTeamInVictory(%d)", "bool"),
]


class Session:
    """一次采样所用的一条连接：send(expr) → (ok, text)。"""

    def __init__(self, host, port, timeout=8.0):
        self.sock = socket.create_connection((host, port), timeout=timeout)
        self.sock.settimeout(timeout)

    def send(self, expr):
        """发送 `function <expr>`，按 function 通道的 NUL 终止符收完为止。"""
        verb = "function"
        term = terminator_for(verb)
        self.sock.sendall(("%s %s\n" % (verb, expr)).encode("utf-8"))
        buf = b""
        while True:
            chunk = self.sock.recv(65536)
            if not chunk:
                break
            buf += chunk
            if buf.endswith(term) or buf.endswith(NUL_MODIFIED_UTF8):
                break
        text = strip_terminator(buf, term).decode("utf-8", errors="replace")
        if text.startswith("ok\n"):
            return True, text[3:].strip()
        if text.startswith("crash\n"):
            return False, "crash: " + text[6:].strip()
        return False, text.strip()

    def close(self):
        try:
            self.sock.close()
        except OSError:
            pass


def sample(host, port, teams, use_getplayer=False):
    """采一次样：全部表达式在**同一条连接**上顺序执行；返回 (数据字典, 错误列表, 耗时)。"""
    exprs = [(label, expr, kind) for label, expr, kind in GLOBAL_ROWS]
    for t in range(teams):
        for label, tmpl, kind in TEAM_ROWS:
            exprs.append(("队伍%d-%s" % (t, label), tmpl % t, kind))
    if use_getplayer:
        for t in range(teams):
            exprs.append(("队伍%d-玩家名" % t, "debug.getPlayerName(%d)" % t, "str"))

    data, errors = {}, []
    t0 = time.time()
    s = Session(host, port)
    try:
        for label, expr, kind in exprs:
            try:
                ok, val = s.send(expr)
            except OSError as e:
                errors.append("%s: %s" % (label, e))
                break
            if not ok:
                data[label] = None
                errors.append("%s → %s" % (label, val[:60]))
                continue
            if kind == "bool":
                data[label] = (val.strip().lower() == "true")
            elif kind == "int":
                try:
                    data[label] = int(float(val))
                except ValueError:
                    data[label] = None
            else:
                data[label] = val
    finally:
        s.close()
    return data, errors, time.time() - t0


def diff_events(prev, cur):
    """比较两次采样，产出人类可读事件（实时数据的关键：变化量才是信息）。"""
    ev = []
    if not prev:
        return ev
    for key in ("失步错误", "重同步收发"):
        a, b = prev.get(key), cur.get(key)
        if isinstance(a, int) and isinstance(b, int) and b > a:
            ev.append("⚠ %s +%d（累计 %d）" % (key, b - a, b))
    for key in ("人类玩家", "连接数", "玩家+AI"):
        a, b = prev.get(key), cur.get(key)
        if a != b and None not in (a, b):
            ev.append("%s: %s → %s" % (key, a, b))
    a, b = prev.get("当前界面"), cur.get("当前界面")
    if a != b:
        ev.append("界面切换: %s → %s" % (a, b))
    for key, val in cur.items():
        if key.endswith("-被消灭") and val is True and prev.get(key) is not True:
            ev.append("💀 %s" % key.replace("-被消灭", " 被消灭"))
        if key.endswith("-被击败") and val is True and prev.get(key) is not True:
            ev.append("🏳 %s" % key.replace("-被击败", " 被击败"))
        if key.endswith("-胜利") and val is True and prev.get(key) is not True:
            ev.append("🏆 %s" % key.replace("-胜利", " 胜利"))
    return ev


def render_panel(data, events, errors, teams, interval, elapsed, idx):
    out = []
    out.append("\x1b[2J\x1b[H")                                  # 清屏 + 光标归位
    out.append("=" * 78)
    out.append(" Rusted Warfare — 实时对局数据   采样 #%d   %s   本轮 %.2fs / 间隔 %ss"
               % (idx, datetime.now().strftime("%H:%M:%S"), elapsed, interval))
    out.append("=" * 78)
    for label, _, _ in GLOBAL_ROWS:
        v = data.get(label, "?")
        out.append("  %-14s %s" % (label, "—" if v is None else v))
    if teams:
        out.append("-" * 78)
        out.append("  队伍状态（T=队伍号）:")
        for t in range(teams):
            w, d, v = (data.get("队伍%d-%s" % (t, k)) for k in ("被消灭", "被击败", "胜利"))
            mark = "被消灭" if w else ("被击败" if d else ("胜利" if v else "进行中"))
            out.append("    T%-2d  %s" % (t, mark))
    out.append("-" * 78)
    if events:
        out.append("  最新事件:")
        out.extend("    " + e for e in events[-8:])
    else:
        out.append("  最新事件: （无变化）")
    if errors:
        out.append("  取不到的项（不影响其它项）: %d 项，例: %s" % (len(errors), errors[0][:70]))
    out.append("=" * 78)
    return "\n".join(out)


def main():
    ap = argparse.ArgumentParser(description="实时对局数据面板（function 通道轮询）")
    ap.add_argument("--host", default=DEFAULT_HOST)
    ap.add_argument("--port", type=int, default=DEFAULT_PORT)
    ap.add_argument("--interval", type=float, default=1.0, help="采样间隔秒（默认 1）")
    ap.add_argument("--teams", type=int, default=6, help="轮询队伍号上限（默认 6，即 T0–T5）")
    ap.add_argument("--unlock", action="store_true",
                    help="尝试开启调试功能（enableFeatures 为哈希口令门，实测不可用；同时开额外网络调试）")
    ap.add_argument("--network-debug", action="store_true",
                    help="调用 debug.enableExtraNetworkDebug()（开 NetEngine.g，联机排查看这个）")
    ap.add_argument("--once", action="store_true", help="只采一次")
    ap.add_argument("--json", action="store_true", help="输出 JSON（配合 --once）")
    ap.add_argument("--append", action="store_true", help="逐行追加输出（不做 ANSI 清屏）")
    ap.add_argument("--log", help="把每次采样追加为一行 JSONL 到该文件")
    ap.add_argument("--wait", type=float, default=45.0, help="等待游戏端口就绪的最长秒数")
    ap.add_argument("--player-names", action="store_true", help="额外轮询 debug.getPlayerName（历史上曾 NPE）")
    args = ap.parse_args()

    t0 = time.time()
    while not port_listening(args.host, args.port) and time.time() - t0 < args.wait:
        if not args.once and not args.json:
            sys.stdout.write("\r等待游戏调试端口 %s:%d …（%.0fs）" % (args.host, args.port, time.time() - t0))
            sys.stdout.flush()
        time.sleep(1.0)
    if not port_listening(args.host, args.port):
        print("\n[错误] 调试端口 %s:%d 未就绪 —— 请用「启动游戏.bat verbose」（含 -debug 5677:local）启动游戏。"
              % (args.host, args.port))
        return 1

    if args.unlock or args.network_debug:
        try:
            s = Session(args.host, args.port)
            if args.unlock:
                # enableFeatures 的真实语义（product 字节码实证）：
                #   Debug.enableFeatures(s) → gameFramework/f.e(s) = hex(SHA-256(s))，
                #   再判 startsWith("221FC410BD29D786") → 属**哈希口令门**，无原像不可解锁。
                ok, val = s.send('debug.enableFeatures("%s")' % FEATURE_MAGIC)
                print("[enableFeatures] %s —— 说明: 该方法把入参 SHA-256 后取十六进制前缀比对常量，"
                      "无口令不可解锁（返回 crash 属预期，不代表端口异常）"
                      % ("意外成功: %s" % val if ok else "不可用（预期）"))
            ok, val = s.send("debug.enableExtraNetworkDebug()")
            print("[额外网络调试] %s" % ("已开启（NetEngine.g=true）" if ok and val.strip() == "true" else "失败 → %s" % val[:80]))
            s.close()
        except OSError as e:
            print("[解锁/网络调试] 连接失败: %s" % e)

    prev, idx, logf = None, 0, (open(args.log, "a", encoding="utf-8") if args.log else None)
    try:
        while True:
            idx += 1
            try:
                data, errors, elapsed = sample(args.host, args.port, args.teams, args.player_names)
            except OSError as e:
                print("[错误] 采样失败: %s（游戏是否已退出？）" % e)
                return 1
            data["_ts"] = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            data["_sample"] = idx
            if logf:
                logf.write(json.dumps(data, ensure_ascii=False) + "\n")
                logf.flush()
            events = diff_events(prev, data)
            if args.json:
                print(json.dumps({"data": data, "events": events, "errors": errors}, ensure_ascii=False, indent=2))
            elif args.append:
                line = "[%s] #%d 界面=%s 玩家=%s+%s 连接=%s 失步=%s/%s" % (
                    data["_ts"], idx, data.get("当前界面"), data.get("人类玩家"), data.get("玩家+AI"),
                    data.get("连接数"), data.get("失步错误"), data.get("失步校验通过"))
                print(line + ("  | " + " ; ".join(events) if events else ""))
                for e in errors:
                    print("      [取不到] " + e)
            else:
                print(render_panel(data, events, errors, args.teams, args.interval, elapsed, idx))
            prev = data
            if args.once:
                return 0
            time.sleep(max(0.1, args.interval))
    except KeyboardInterrupt:
        print("\n已停止。")
        return 0
    finally:
        if logf:
            logf.close()


if __name__ == "__main__":
    sys.exit(main())
