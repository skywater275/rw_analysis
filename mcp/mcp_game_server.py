#!/usr/bin/env python3
"""Rusted Warfare 游戏 MCP 服务器 (v19.133f98) — 桥接调试服务器协议为标准 MCP 工具.

全量映射 script_api.json 222 方法 (debug 67 + root 155) + 游戏生命周期 + 扩展命令.
协议: TCP 127.0.0.1:5677, 命令 'script <expr>' / 'ping'.
危险方法 (原版固有 NPE, 如 debug.getLocalPlayerId / debug.getPlayerName) 在描述中标注.

Usage:
  python mcp/mcp_game_server.py                # stdio MCP 服务器 (自包含, 独立于源码逆向项目)
  python mcp/mcp_game_server.py --list-tools   # 列出注册工具
  python mcp/mcp_game_server.py --self-test    # 自测 (需游戏运行, 可选 --launch)
  python mcp/mcp_game_server.py --launch-game  # 仅启动游戏并退出
环境: RW_GAME_ROOT 可覆盖游戏根路径 (默认: 本目录上级的上级)
"""
import inspect
import asyncio
import json
import os
import re
import signal
import socket
import subprocess
import sys
import time
from collections import deque
from pathlib import Path

sys.stdout.reconfigure(encoding="utf-8", errors="replace")

MCP_DIR = Path(__file__).resolve().parent  # 本目录 = 唯一依赖 (自包含, 与源码逆向项目隔离)
GAME_ROOT = Path(os.environ.get("RW_GAME_ROOT") or MCP_DIR.parent.parent)  # 默认: 项目内顶层 mcp/ → 上级的上级 = 游戏根; 可用环境变量 RW_GAME_ROOT 覆盖
API_PATH = MCP_DIR / "script_api.json"
HOST, PORT = "127.0.0.1", 5677
JAVAW = GAME_ROOT / "jvm64/bin/javaw.exe"
JAVA = GAME_ROOT / "jvm64/bin/java.exe"
LOG_FILE = GAME_ROOT / "game-mcp.log"

TYPE_MAP = {"String": str, "boolean": bool, "int": int, "float": float, "long": int}
# 原版固有 NPE 的危险查询方法 (startNew 模式无玩家连接信息, 对照验证过)
# 及对局中调用会崩溃的开局前设置方法 (v19.133f98 MCP 实测)
DANGEROUS = {
    "debug.getLocalPlayerId": "⚠️ 危险: 原版固有 NPE (对局中调用会崩游戏), 勿用",
    "debug.getPlayerName": "⚠️ 危险: 原版固有 NPE (对局中调用会崩游戏), 勿用",
    "debug.setNetworkaiDifficulty": "⚠️ 危险: 开局前设置方法, 对局中调用 NPE 崩游戏 (MCP 实测), 勿用",
    "debug.setNetworkStartingUnits": "⚠️ 危险: 开局前设置方法, 对局中调用可能崩溃, 慎用",
    "debug.enableFastSync": "⚠️ 危险: 网络同步开关, 对局中调用可能引起异常, 慎用",
    "debug.checkDesync": "⚠️ 危险: 同步校验方法, 可能中断对局, 慎用",
}
# 谨慎方法 (不拦截, 但描述标注 ⚠️ 警告; 状态过滤已保证 debug.* 仅对局中可调)
WARN_NOTES = {
    "debug.createUnit": "⚠️ 谨慎: 对局中偶发连接重置 (实测), 优先用 debug.createManyUnits",
    "debug.plainTextDebugSave": "⚠️ 谨慎: 仅对局内调用 (生成文本存档用), 对局外/主菜单调用会崩游戏",
    "debug.removeAllUnits": "⚠️ 谨慎: 删除地图上所有单位 (含中立/建筑), 破坏性操作",
    "debug.killAllUnits": "⚠️ 谨慎: 杀死地图上所有单位, 破坏性操作",
    "debug.runAllUnitTests": "⚠️ 谨慎: 运行全部单位测试, 可能改变对局状态/卡顿",
    "debug.startRandomUnitStressTest": "⚠️ 谨慎: 压力测试, 可能卡顿/改变对局",
    "debug.startRandomUnitDesyncTest": "⚠️ 谨慎: 同步测试, 可能中断对局",
    "debug.setPathSpeedConf": "⚠️ 谨慎: 路径计算配置, 可能影响性能",
    "debug.overrideDeltaSpeed": "⚠️ 谨慎: 覆盖游戏速度, 可能造成不同步",
    "debug.throwIfAnyPlayerNotInSync": "⚠️ 谨慎: 不同步时抛异常中断对局",
    "root.logDebug": "⚠️ 谨慎: logDebug 仅接受 String 字面量参数, 传 int/float/变量会崩游戏 (v19.113 实测)",
    "root.logWarn": "⚠️ 谨慎: logWarn 同上, 仅接受 String 字面量",
}


class GameConnection:
    """调试服务器协议桥接 (复用 debug_client.py 逻辑 + 日志环形缓冲 + 状态机防护)."""

    def __init__(self, port=PORT, log_cap=300):
        self.port = port
        self.log = deque(maxlen=log_cap)
        # v19.133f98 防护: 状态机 (崩溃防护核心)
        self.state = "UNKNOWN"  # UNKNOWN/STARTING/MAIN_MENU/IN_GAME/REPLAY/CRASHED
        self.crash_count = 0

    def _update_state(self, text):
        """从日志流推断游戏状态 (防护过滤依据)."""
        if "onGameCrash" in text or "uncaughtException" in text or "连接重置" in text:
            self.state = "CRASHED"
            self.crash_count += 1
            return
        if "loadDocument:assets/gui/mainMenu.rml" in text:
            self.state = "MAIN_MENU"
            return
        if "loadReplay" in text:
            self.state = "REPLAY"
            return
        if "Team(id:" in text and "Being replaced" in text:
            self.state = "STARTING"
            return
        if "Mapfile:" in text and "skirmish" in text:
            self.state = "IN_GAME"
            return
        if self.state == "UNKNOWN" and "Game init finished" in text:
            self.state = "MAIN_MENU"

    def connect(self, timeout=8):
        s = socket.socket()
        s.settimeout(timeout)
        s.connect((HOST, self.port))
        return s

    def send_raw(self, cmd, wait=4.0):
        """发命令, 收集响应 (游戏日志推送), 存入环形缓冲."""
        try:
            s = self.connect()
        except OSError as e:
            return f"[连接失败] {e} (游戏未运行/端口未监听)"
        s.sendall(cmd.encode("utf-8") + b"\n")
        buf = b""
        s.settimeout(wait)
        try:
            while True:
                d = s.recv(65536)
                if not d:
                    break
                buf += d
        except socket.timeout:
            pass
        except OSError as e:
            # 连接被重置 = 游戏端异常/崩溃 (如危险方法调用)
            buf += f"\n[连接重置] {e}".encode("utf-8")
        finally:
            try:
                s.close()
            except OSError:
                pass
        text = buf.decode("utf-8", "replace")
        for ln in text.splitlines():
            if ln.strip():
                self.log.append(ln.strip())
        self._update_state(text)
        return text

    def ping(self):
        return "pong" in self.send_raw("ping", wait=3)

    def refresh_state(self):
        """主动拉取日志更新状态: 发无害 script 命令 (游戏会推送当前场景日志)."""
        self.send_raw("script root.getVersionName()", wait=2)
        return self.state

    def call_script(self, expr):
        """执行 script 表达式, 返回 (响应文本, 成功与否)."""
        resp = self.send_raw(f"script {expr}", wait=4)
        ok = "Exception" not in resp and "Error" not in resp[:200] and "连接重置" not in resp
        return resp, ok

    def tail(self, n=20):
        return list(self.log)[-n:]


CONN = GameConnection()
_game_proc = None  # 生命周期管理的游戏进程


# ── 游戏生命周期 ──────────────────────────────────────────
def _launch_game(headless: bool, debug_port: int, extra_args: str) -> dict:
    global _game_proc
    exe = JAVA if headless else JAVAW
    if not exe.exists():
        return {"ok": False, "error": f"JVM 不存在: {exe}"}
    args = [str(exe), "-cp", "game-lib.jar;libs\\*", "com.corrodinggames.rts.java.Main"]
    if headless:
        args.append("-nodisplay")
    args += ["-debug", f"{debug_port}:local"]
    if extra_args:
        args += extra_args.split()
    try:
        _game_proc = subprocess.Popen(
            args, cwd=str(GAME_ROOT),
            stdout=open(LOG_FILE, "w", encoding="utf-8", errors="replace"),
            stderr=subprocess.STDOUT,
            creationflags=getattr(subprocess, "CREATE_NO_WINDOW", 0),
        )
        CONN.state = "UNKNOWN"  # 新进程, 重置状态机
        CONN.crash_count = 0
        CONN.log.clear()
        # 等待调试端口就绪 (最多 25 秒)
        for _ in range(50):
            time.sleep(0.5)
            if CONN.ping():
                CONN.refresh_state()  # 主动拉日志, 推断 MAIN_MENU 等状态
                return {"ok": True, "pid": _game_proc.pid, "port": debug_port,
                        "headless": headless, "state": CONN.state, "log": str(LOG_FILE)}
        return {"ok": True, "pid": _game_proc.pid, "port": debug_port,
                "warning": "进程已启动但调试端口未就绪 (可能仍在加载)", "log": str(LOG_FILE)}
    except Exception as e:
        return {"ok": False, "error": str(e)}


def _stop_game() -> dict:
    global _game_proc
    killed = []
    if _game_proc and _game_proc.poll() is None:
        _game_proc.terminate()
        try:
            _game_proc.wait(timeout=5)
        except subprocess.TimeoutExpired:
            _game_proc.kill()
        killed.append(_game_proc.pid)
        _game_proc = None
    # 兜底: 清理残留 javaw/java 游戏进程
    for p in subprocess.run(["powershell", "-NoProfile", "-Command",
                             "Get-Process javaw,java -ErrorAction SilentlyContinue | Where-Object {$_.Path -like '*Rusted*'} | Select-Object -ExpandProperty Id"],
                            capture_output=True, text=True).stdout.split():
        try:
            os.kill(int(p), signal.SIGTERM)
            killed.append(int(p))
        except OSError:
            pass
    return {"ok": True, "killed": killed}


def _game_status() -> dict:
    alive = _game_proc is not None and _game_proc.poll() is None
    ping = CONN.ping()
    return {"ok": True, "managed_process_alive": alive,
            "pid": _game_proc.pid if alive else None,
            "debug_socket": "pong" if ping else "无响应"}


# ── MCP 服务器 ────────────────────────────────────────────
from mcp.server.mcpserver import MCPServer

mcp = MCPServer(
    name="rusted-warfare",
    description="Rusted Warfare v1.15 游戏调试桥接 (headless/GUI 运行 + 222 个 debug/root 方法 + 生命周期). 游戏根: %s" % GAME_ROOT,
)


@mcp.tool()
def game_ping() -> str:
    """心跳: 检测游戏调试服务器是否存活."""
    return json.dumps({"ok": CONN.ping()}, ensure_ascii=False)


@mcp.tool()
def game_status() -> str:
    """游戏运行状态: 托管进程存活 + 调试端口响应 + 状态机 (UNKNOWN/STARTING/MAIN_MENU/IN_GAME/REPLAY/CRASHED) + 崩溃建议."""
    alive = _game_proc is not None and _game_proc.poll() is None
    ping = CONN.ping()
    CONN.refresh_state()  # 主动拉日志更新状态
    advice = ""
    if CONN.state == "CRASHED":
        advice = "游戏已崩溃 — 请 game_reset 清理后 game_launch 重启"
    elif CONN.state == "MAIN_MENU":
        advice = "主菜单 — 可 root_startNew(map) 开局 或 root_loadReplay"
    elif CONN.state == "IN_GAME":
        advice = "对局中 — debug_*/流程工具可用"
    elif CONN.state == "REPLAY":
        advice = "回放中 — debug_* 查询/操作可用"
    return json.dumps({"ok": True, "managed_process_alive": alive,
                       "pid": _game_proc.pid if alive else None,
                       "debug_socket": "pong" if ping else "无响应",
                       "state": CONN.state, "crash_count": CONN.crash_count,
                       "window": _game_window(),  # GUI 模式窗口信息 (识别游戏窗口)
                       "advice": advice}, ensure_ascii=False)


@mcp.tool()
def game_reset() -> str:
    """崩溃恢复: 清理残留游戏进程 + 重置状态机 (崩溃后必调, 再 game_launch)."""
    r = _stop_game()
    CONN.state = "UNKNOWN"
    CONN.crash_count = 0
    CONN.log.clear()
    return json.dumps({"ok": True, "killed": r.get("killed", []),
                       "state": CONN.state, "advice": "已重置 — 可 game_launch 重新启动"}, ensure_ascii=False)


@mcp.tool()
def game_force_state(state: str) -> str:
    """专家工具: 手动声明游戏状态 (防护过滤依据).

    state: UNKNOWN/STARTING/MAIN_MENU/IN_GAME/REPLAY/CRASHED.
    用于状态机推断失误时的纠正 (如开局命令返回异常但游戏实际已进入对局).
    """
    valid = {"UNKNOWN", "STARTING", "MAIN_MENU", "IN_GAME", "REPLAY", "CRASHED"}
    if state not in valid:
        return json.dumps({"ok": False, "error": f"非法状态: {state}, 可用: {sorted(valid)}"},
                          ensure_ascii=False)
    CONN.state = state
    return json.dumps({"ok": True, "state": state}, ensure_ascii=False)


def _game_window():
    """Windows 窗口识别: 枚举可见窗口找 'Rusted' 标题的游戏窗口 (Win32 API).

    返回窗口 dict 或 None (无窗口/非 Windows). 供 game_window_info/game_launch/game_status 共用.
    """
    if sys.platform != "win32":
        return None
    try:
        import ctypes
        from ctypes import wintypes
        user32 = ctypes.windll.user32

        class RECT(ctypes.Structure):
            _fields_ = [("left", ctypes.c_long), ("top", ctypes.c_long),
                        ("right", ctypes.c_long), ("bottom", ctypes.c_long)]

        found = []

        @ctypes.WINFUNCTYPE(wintypes.BOOL, wintypes.HWND, wintypes.LPARAM)
        def _cb(hwnd, _lp):
            if not user32.IsWindowVisible(hwnd):
                return True
            buf = ctypes.create_unicode_buffer(256)
            user32.GetWindowTextW(hwnd, buf, 256)
            if "Rusted" in buf.value or "rustedwarfare" in buf.value.lower():
                found.append(hwnd)
            return True

        user32.EnumWindows(_cb, 0)
        if not found:
            return None
        hwnd = found[0]
        rect = RECT()
        user32.GetWindowRect(hwnd, ctypes.byref(rect))
        title = ctypes.create_unicode_buffer(256)
        user32.GetWindowTextW(hwnd, title, 256)
        cls = ctypes.create_unicode_buffer(256)
        user32.GetClassNameW(hwnd, cls, 256)
        return {
            "handle": int(hwnd), "title": title.value, "class_name": cls.value,
            "x": rect.left, "y": rect.top,
            "width": rect.right - rect.left, "height": rect.bottom - rect.top,
            "visible": bool(user32.IsWindowVisible(hwnd)),
            "foreground": bool(user32.GetForegroundWindow() == hwnd),
        }
    except Exception:
        return None


@mcp.tool()
def game_window_info() -> str:
    """识别游戏窗口 (Windows): 标题/句柄/尺寸/位置/可见/前台/类名.

    仅 GUI 模式 (game_launch headless=False) 有窗口; headless 返回 window=None.
    窗口类名为 LWJGL (游戏渲染窗口), 可用于窗口操作 (前台/截图等).
    """
    w = _game_window()
    if w is None:
        return json.dumps({"ok": True, "window": None,
                           "note": "未找到游戏窗口 (headless 模式或游戏未启动 GUI); "
                                   "用 game_launch(headless=False) 启动 GUI"}, ensure_ascii=False)
    return json.dumps({"ok": True, "window": w}, ensure_ascii=False)


@mcp.tool()
def game_launch(headless: bool = False, debug_port: int = 5677, extra_args: str = "") -> str:
    """启动游戏 (GUI 或 headless) 并等待调试端口就绪.

    headless: True=无窗口模式 (B5 回放/自动测试), False=正常游戏窗口 (窗口信息可查 game_window_info).
    extra_args: 附加命令行参数 (空格分隔).
    """
    r = _launch_game(headless, debug_port, extra_args)
    if r.get("ok") and not headless:
        # GUI 模式: 等待窗口出现 (最多 12s)
        for _ in range(6):
            time.sleep(2)
            w = _game_window()
            if w:
                r["window"] = w
                break
        if "window" not in r:
            r["window"] = None
            r["window_note"] = "窗口未在 12s 内出现 (可能仍在加载)"
    return json.dumps(r, ensure_ascii=False)


@mcp.tool()
def game_stop() -> str:
    """停止游戏进程 (托管进程 + 清理残留)."""
    return json.dumps(_stop_game(), ensure_ascii=False)


@mcp.tool()
def game_load_replay(replay_name: str) -> str:
    """加载回放 (replays/ 目录下文件名, 或游戏根相对路径).

    等价: root.loadReplay('name.replay')
    """
    resp, ok = CONN.call_script(f"root.loadReplay('{replay_name}')")
    return json.dumps({"ok": ok, "replay": replay_name,
                       "log_tail": CONN.tail(5)}, ensure_ascii=False)


@mcp.tool()
def game_read_log(lines: int = 30) -> str:
    """读取最近游戏日志 (调试 socket 推送缓冲)."""
    return json.dumps({"log": CONN.tail(max(1, min(lines, 300)))}, ensure_ascii=False)


@mcp.tool()
def game_read_log_file(lines: int = 30, grep: str = "") -> str:
    """读取游戏日志文件 (game-mcp.log, 真实验证通道).

    grep: 可选过滤关键字 (如 'Could not find type' / 'wiped out' / 'Exception').
    用途: 验证单位创建 (无 Could not find type 警告=类型有效)、胜利判定 (wiped out)、崩溃检测.
    """
    p = Path(LOG_FILE)
    if not p.exists():
        return json.dumps({"ok": False, "error": f"日志文件不存在: {p}"}, ensure_ascii=False)
    text = p.read_text(encoding="utf-8", errors="replace")
    ls = text.splitlines()
    if grep:
        hits = [l for l in ls if grep in l]
        return json.dumps({"ok": True, "grep": grep, "hits": len(hits),
                           "log": hits[-max(1, min(lines, 300)):]}, ensure_ascii=False)
    return json.dumps({"ok": True, "log": ls[-max(1, min(lines, 300)):]}, ensure_ascii=False)


# ── 实时数据层 (B5.5: 窗口识别 + 日志流实时监控) ─────────────────
_LOG_POS = 0  # game_log_stream 的文件读取游标 (增量监控)

# 日志事件结构化解析 (游戏日志的实时数据 → JSON)
# 实测日志格式: MissionEngine:triggerLog:firstActivation: move at:9213 for teamId:1 to targetId:5 (#units:1)
#              ai_debug(5):firstRun: no command center found
#              Team(id: 2, name:AI - Hard):Being replaced
_EVENT_PATTERNS = [
    (r"MissionEngine:triggerLog:(\w+):\s*(.+?) for teamId:(\d+) to targetId:(\d+) \(#units:(\d+)\)",
     "mission", lambda m: {"trigger": m.group(1), "action": m.group(2),
                            "team": int(m.group(3)), "target": int(m.group(4)),
                            "units": int(m.group(5))}),
    (r"ai_debug\((\d+)\):([\w_]+):\s*(.*)",
     "ai", lambda m: {"ai": int(m.group(1)), "phase": m.group(2), "detail": m.group(3)}),
    (r"Team\(id: (\d+), name:(.+?)\):Being replaced",
     "team_start", lambda m: {"team": int(m.group(1)), "name": m.group(2)}),
    (r"Map size: (\d+), (\d+)",
     "map_info", lambda m: {"width": int(m.group(1)), "height": int(m.group(2))}),
    (r"Found (\d+) map triggers",
     "map_info", lambda m: {"triggers": int(m.group(1))}),
    (r"global Seed: (\d+)",
     "map_info", lambda m: {"seed": int(m.group(1))}),
    (r"Number of mods:(\d+)",
     "mod_info", lambda m: {"count": int(m.group(1))}),
    (r"Mod: '(.+?)'",
     "mod_info", lambda m: {"name": m.group(1)}),
    (r"(?:--- )?(setRunning|setStopped|setStoppedIfNotInGameThread)",
     "game_state_log", lambda m: {"state": m.group(1)}),
    (r"MapLayer create: (\w+)",
     "loading_stage", lambda m: {"layer": m.group(1)}),
    (r"FileLoader: Could not find asset:(\S+)",
     "asset_missing", lambda m: {"asset": m.group(1)}),
    (r"getNewTextureHolder: append:(\d+)",
     "texture", lambda m: {"append": int(m.group(1))}),
    (r"(?:\S+\s+\S+: )?\s*(.*?) has been wiped out \(Team: ([A-P])\)",
     "victory", lambda m: {"wiped_out": m.group(1).strip(), "team_letter": m.group(2)}),
    (r"Could not find type:\s*(\S+)",
     "error", lambda m: {"type": m.group(1)}),
    (r"onGameCrash",
     "crash", lambda m: {}),
]


def _parse_event(line):
    """日志行 → (类别, 结构化 dict) 或 None."""
    for pat, cat, conv in _EVENT_PATTERNS:
        m = re.search(pat, line)
        if m:
            try:
                return cat, conv(m)
            except Exception:
                return cat, {"raw": line[-120:]}
    return None


@mcp.tool()
def game_log_stream(lines: int = 30, grep: str = "") -> str:
    """增量日志流 (实时监控): 只返回上次读取后的新日志行 (结构化解析).

    首次调用: 返回最近 lines 行建立基线; 后续: 只返回新内容 (秒级轮询).
    grep: 关键字过滤 (如 'MissionEngine' / 'ai_debug' / 'wiped out').
    返回: new (原始行) + events (结构化解析: mission/ai/victory/error/crash).
    数据源: 游戏自身日志 (含动态数值 teamId/targetId/units/资金事件) — 动态通道实证:
    logDebug/logWarn/writeGameLog 仅 String 字面量 (变量/数字字面量均崩, javap 实证无重载).
    """
    global _LOG_POS
    p = Path(LOG_FILE)
    if not p.exists():
        return json.dumps({"ok": False, "error": f"日志文件不存在: {p}"}, ensure_ascii=False)
    text = p.read_text(encoding="utf-8", errors="replace")
    total = len(text)
    if total < _LOG_POS:  # 文件被截断/启动覆盖 → 重置基线
        _LOG_POS = 0
    chunk = text[_LOG_POS:]
    _LOG_POS = total
    ls = [l for l in chunk.splitlines() if l.strip()]
    if grep:
        ls = [l for l in ls if grep in l]
    events = []
    for l in ls:
        e = _parse_event(l)
        if e:
            events.append(e)
    return json.dumps({"ok": True, "new_lines": len(ls),
                       "new": ls[-max(1, min(lines, 300)):],
                       "events": events[-100:],
                       "baseline": _LOG_POS == total and not chunk.strip()},
                      ensure_ascii=False)


@mcp.tool()
def game_realtime_stats() -> str:
    """实时状态聚合 (秒级, 非存档): 窗口识别 + 调试端口 + 日志事件解析.

    数据源: 窗口 (Win32, GUI 模式) + 调试服务器 ping + 游戏日志实时事件
    (MissionEngine AI 活动 / ai_debug / wiped out 胜利 / Could not find type 错误).
    单位精确数据 (坐标/血量) 需 game_unit_dump (存档快照, 慢几秒但真实).
    """
    win = _game_window()
    ping = CONN.ping()
    events = []
    p = Path(LOG_FILE)
    if p.exists():
        ls = p.read_text(encoding="utf-8", errors="replace").splitlines()
        for kw in ("MissionEngine", "ai_debug", "wiped out",
                   "Could not find type", "onGameCrash"):
            for l in ls[-300:]:
                if kw in l:
                    events.append(l[-140:])
    return json.dumps({
        "ok": True,
        "window": win,
        "debug_socket": "pong" if ping else "无响应",
        "game_state": CONN.state,
        "recent_events": events[-30:],
        "note": "实时数据来自窗口识别+端口+日志事件 (秒级); 单位精确数据用 game_unit_dump",
    }, ensure_ascii=False)


@mcp.tool()
def game_highfreq_monitor(interval_ms: int = 100, duration_ms: int = 5000) -> str:
    """高频监控 (近帧级, 毫秒采样): 日志增量 + 游戏进程 CPU 时钟 + 窗口状态 时间序列.

    interval_ms: 采样间隔 (默认 100ms, 最小 20ms); duration_ms: 总时长 (默认 5s, 上限 30s).
    返回: time_series = [{t_ms, new_log_lines, cpu_ms_delta, window_foreground}, ...].
    数据源: 游戏日志毫秒事件 (事件驱动最高频) + GetProcessTimes CPU 时钟 (~15ms 粒度)
            + 窗口状态.
    说明: 游戏无 FPS/帧计数通道 — 脚本引擎返回值不可达 (logDebug 仅 String 字面量),
    渲染类为原版不可插桩; 此为架构下最高频可用通道 (毫秒级, 近似帧率可用接口).
    """
    if sys.platform not in ("win32",):
        return json.dumps({"ok": False, "error": "仅 Windows 支持高频监控"},
                          ensure_ascii=False)
    interval = max(20, min(interval_ms, 2000))
    duration = max(200, min(duration_ms, 30000))
    try:
        import ctypes
        from ctypes import wintypes
        kernel32 = ctypes.windll.kernel32
        # GetProcessTimes
        handles = []
        for p in subprocess.run(["powershell", "-NoProfile", "-Command",
                                 "Get-Process javaw,java -ErrorAction SilentlyContinue | "
                                 "Where-Object {$_.Path -like '*Rusted*'} | "
                                 "Select-Object -ExpandProperty Id"],
                                capture_output=True, text=True).stdout.split():
            try:
                h = ctypes.windll.kernel32.OpenProcess(
                    0x0400, False, int(p))  # PROCESS_QUERY_LIMITED_INFORMATION
                if h:
                    handles.append(h)
            except OSError:
                pass
        if not handles:
            return json.dumps({"ok": False, "error": "未找到游戏进程"}, ensure_ascii=False)

        def cpu_ms(h):
            ct, et, kt, ut = (ctypes.c_ulonglong(), ctypes.c_ulonglong(),
                              ctypes.c_ulonglong(), ctypes.c_ulonglong())
            kernel32.GetProcessTimes(h, ctypes.byref(ct), ctypes.byref(et),
                                     ctypes.byref(kt), ctypes.byref(ut))
            return (kt.value + ut.value) // 10000  # 100ns → ms

        global _LOG_POS
        p = Path(LOG_FILE)
        series = []
        t0 = time.time()
        last_cpu = sum(cpu_ms(h) for h in handles)
        last_pos = _LOG_POS
        last_win = False
        while (time.time() - t0) * 1000 < duration:
            t_ms = int((time.time() - t0) * 1000)
            cur_cpu = sum(cpu_ms(h) for h in handles)
            win = _game_window()
            fg = bool(win and win.get("foreground"))
            new_lines = 0
            if p.exists():
                text = p.read_text(encoding="utf-8", errors="replace")
                total = len(text)
                if total < last_pos:
                    last_pos = 0
                chunk = text[last_pos:]
                last_pos = total
                new_lines = len([l for l in chunk.splitlines() if l.strip()])
            series.append({"t_ms": t_ms, "new_log_lines": new_lines,
                           "cpu_ms_delta": cur_cpu - last_cpu,
                           "window_foreground": fg})
            last_cpu = cur_cpu
            time.sleep(interval / 1000.0)
        for h in handles:
            ctypes.windll.kernel32.CloseHandle(h)
        # 汇总
        total_cpu = sum(s["cpu_ms_delta"] for s in series)
        total_log = sum(s["new_log_lines"] for s in series)
        return json.dumps({
            "ok": True,
            "interval_ms": interval, "duration_ms": duration,
            "samples": len(series),
            "total_cpu_ms": total_cpu, "total_log_lines": total_log,
            "avg_cpu_ms_per_sample": round(total_cpu / max(len(series), 1), 2),
            "time_series": series,
            "note": "近帧级: 毫秒采样 (游戏无 FPS 通道, 见 game_log_stream 说明); "
                    "avg_cpu_ms_per_sample≈采样间隔 → 持续高负载; 日志行数=事件密度",
        }, ensure_ascii=False)
    except Exception as e:
        return json.dumps({"ok": False, "error": f"监控失败: {e}"}, ensure_ascii=False)


# ── 单位数据层 (v19.133f98+: 全单位真实名字 + 存档真实数据 + 胜利判定) ──────
# 名字表: unit_type_names.json = 52 内置 UnitType 枚举名 (原版 ar.class <clinit> 实证提取)
#         unit_names.json      = 125 ini 注册名 (assets/units/*.ini 的 name: 字段)
UNIT_TYPES_PATH = MCP_DIR / "unit_type_names.json"
UNIT_NAMES_PATH = MCP_DIR / "unit_names.json"
SAVES_DIR = GAME_ROOT / "saves"
_NAMES_CACHE = None


def _load_names():
    """加载两份单位名字表 (惰性缓存)."""
    global _NAMES_CACHE
    if _NAMES_CACHE is not None:
        return _NAMES_CACHE
    builtin = []
    try:
        builtin = json.load(open(UNIT_TYPES_PATH, encoding="utf-8")).get("unit_types", [])
    except Exception:
        pass
    ini_units = {}
    try:
        ini_units = json.load(open(UNIT_NAMES_PATH, encoding="utf-8")).get("units", {})
    except Exception:
        pass
    _NAMES_CACHE = (builtin, ini_units)
    return _NAMES_CACHE


def _invalid_unit_type(name: str) -> str:
    """单位类型预检: 合法返回空串, 非法返回错误说明 (A2: 消灭'调用成功但没刷出单位')."""
    builtin, ini_units = _load_names()
    if name in builtin or name in ini_units:
        return ""
    # 大小写不敏感兜底
    low = name.lower()
    if any(n.lower() == low for n in builtin) or any(n.lower() == low for n in ini_units):
        return ""
    # 常见错误提示: 给出最接近的候选
    cands = [n for n in builtin if low in n.lower()][:4]
    cands += [n for n in ini_units if low in n.lower()][:4]
    hint = f"; 相近名字: {cands}" if cands else "; 用 game_unit_names('') 查全表"
    return (f"无效单位类型 {name!r} (不在 52 内置枚举名/125 ini 注册名中){hint}")


def _warn_note(full: str) -> str:
    """危险/谨慎方法的描述标注 (A4: 不拦截, 但 AI 可见警告)."""
    return WARN_NOTES.get(full, "")


@mcp.tool()
def game_unit_names(prefix: str = "") -> str:
    """查询全单位真实名字 (两份权威表).

    返回: builtin = 52 个内置单位类型名 (原版 UnitType 枚举, 如 tank/heavyTank/mammothTank/battleShip),
          ini = 125 个 ini 注册名 (assets/units 的 name: 字段, 如 c_mammothTank/c_tank/c_artillery/bomber).
    prefix: 可选过滤 (不区分大小写, 匹配名字或 ini 路径).
    说明: 两类名字均可直接用于 debug.createManyUnits/createUnit (实测有效).
    """
    builtin, ini_units = _load_names()
    p = prefix.strip().lower()
    bl = [n for n in builtin if not p or p in n.lower()]
    il = [{"name": n, "ini": ini_units[n]} for n in ini_units
          if not p or p in n.lower() or p in ini_units[n].lower()]
    return json.dumps({
        "ok": True,
        "builtin_count": len(bl), "builtin": bl,
        "ini_count": len(il), "ini": il,
        "note": "builtin=内置枚举名(无 ini), ini=注册名(有 ini 属性可查 game_unit_info)",
    }, ensure_ascii=False)


@mcp.tool()
def game_unit_info(unit_name: str) -> str:
    """查询单位真实属性 (直接读游戏 assets/units/*.ini 静态数据).

    unit_name: ini 注册名 (如 c_mammothTank/c_tank/bomber, 可用 game_unit_names 查询).
    返回: 该单位 ini 的 [core] 段属性 (maxHp/price/mass/movespeed/maxattackrange/directdamage 等).
    若名字是内置枚举名 (无 ini) 会标注; 找不到会提示用 game_unit_names 查询.
    """
    builtin, ini_units = _load_names()
    q = unit_name.strip()
    # 大小写不敏感匹配 ini 注册名
    hit = next((n for n in ini_units if n.lower() == q.lower()), None)
    if hit is None:
        # 宽松匹配 (子串)
        hits = [n for n in ini_units if q.lower() in n.lower()]
        if hits:
            hit = hits[0]
            return json.dumps({"ok": True, "matched": "fuzzy", "unit_name": hit,
                               "ini": ini_units[hit],
                               "suggestions": hits[:20],
                               "note": "未精确匹配, 已取首个模糊命中; 用 game_unit_names 可查全表"},
                              ensure_ascii=False)
        if q in builtin:
            return json.dumps({"ok": True, "unit_name": q, "builtin": True,
                               "note": "内置枚举单位类型 (无独立 ini), 可直接 createUnit; "
                                       "如需属性数据, 用 game_unit_names 查对应 ini 注册名"},
                              ensure_ascii=False)
        return json.dumps({"ok": False, "error": f"未找到单位 {q!r}; "
                                                 "用 game_unit_names('') 查询全表"}, ensure_ascii=False)
    ini_rel = ini_units[hit]
    p = GAME_ROOT / "assets" / ini_rel
    if not p.exists():
        return json.dumps({"ok": True, "unit_name": hit, "ini": ini_rel,
                           "error": f"ini 文件不存在: {p}"}, ensure_ascii=False)
    core = {}
    section = ""
    raw = p.read_text(encoding="utf-8", errors="replace")
    for l in raw.splitlines():
        l = l.strip()
        if not l or l.startswith("#"):
            continue
        if l.startswith("[") and l.endswith("]"):
            section = l[1:-1].strip().lower()
            continue
        if ":" in l:
            k, v = l.split(":", 1)
            core[f"{section}.{k.strip().lower()}"] = v.strip()
    # 常用键精选 (大小写不敏感)
    picks = {}
    for k, v in core.items():
        kl = k.split(".", 1)[-1]
        if kl in ("name", "maxhp", "price", "mass", "movespeed", "speed", "maxattackrange",
                  "directdamage", "techlevel", "buildspeed", "turnspeed", "radius",
                  "turretturnspeed", "description", "displaylocalekey", "class",
                  "canattacklandunits", "canattackflyingunits", "canattackunderwaterunits"):
            picks[k] = v
    return json.dumps({"ok": True, "unit_name": hit, "ini": ini_rel,
                       "core": picks, "all_keys": len(core)}, ensure_ascii=False)


# 存档单位段解析 (自包含, 复用存档差分法的 am 模板 — 02b am.a(as) 实证字段序)
# 关键字段位置 (四连特征起点 bM,bQ,bR,bS 后): +5=bV(isDead) +20=cu(hp) +21=cv(maxHp)
# 位置: 段尾 float 值流倒数第 3/第 2 个 = x/y (super.a 父类序列化, 实证 megaTank(500,500))
def _parse_save_units(text: str) -> list:
    """纯文本存档 → 单位数据列表 [{id,type,team,hp,maxHp,fullHp,isDead,x,y}]."""
    segs = {}
    cur_id = cur_type = None
    cur = []
    for l in text.split("\n"):
        m = re.search(r"Saving unit:(\S+) \(id(\d+)\)", l)
        if m:
            if cur_id is not None:
                segs[cur_id] = (cur_type, "\n".join(cur))
            cur_id, cur_type, cur = m.group(2), m.group(1), []
            continue
        if cur_id is not None:
            cur.append(l)
    if cur_id is not None:
        segs[cur_id] = (cur_type, "\n".join(cur))
    out = []
    for uid, (typ, seg) in segs.items():
        # 值流解析
        vals = []
        lines = seg.split("\n")
        i = 0
        while i < len(lines):
            l = lines[i].rstrip()
            nxt = lines[i + 1].strip() if i + 1 < len(lines) else ""
            if l.startswith("#writeFloat"):
                vals.append(("float", nxt)); i += 2; continue
            if l.startswith("#int:"):
                vals.append(("int", nxt)); i += 2; continue
            if l.startswith("#writeLong"):
                vals.append(("long", nxt)); i += 2; continue
            if l.startswith("#writeGameObject:"):
                vals.append(("gobj", nxt)); i += 2; continue
            if l.startswith("#unitType:"):
                vals.append(("unittype", nxt)); i += 2; continue
            if l.startswith("#Enum:"):
                vals.append(("enum", l.split(":", 1)[1].strip()))
                vals.append(("int", nxt)); i += 2; continue
            if l.startswith("#writeMark:"):
                vals.append(("mark", nxt)); i += 2; continue
            if l.startswith("#writeShort"):
                vals.append(("short", nxt)); i += 2; continue
            if l.startswith("#team:"):
                vals.append(("team", nxt)); i += 2; continue
            if l in ("true", "false"):
                vals.append(("bool", l)); i += 1; continue
            if re.match(r"^-?\d+$", l):
                vals.append(("int", l)); i += 1; continue
            if re.match(r"^-?\d+\.\d+$", l):
                vals.append(("float", l)); i += 1; continue
            i += 1
        item = {"id": int(uid), "type": typ}
        tm = [v for t, v in vals if t == "team"]
        if tm:
            try:
                item["team"] = int(tm[0])
            except ValueError:
                item["team"] = tm[0]
        floats = [float(v) for t, v in vals if t == "float"]
        if len(floats) >= 4:  # 段尾: cn(1.0), x, y, z(0.0)
            item["x"] = floats[-3]
            item["y"] = floats[-2]
        # am 四连特征定位 (bM bool + bQ/bR gobj + bS float)
        start = -1
        for k in range(len(vals) - 3):
            if vals[k][0] == "bool" and vals[k + 1][0] == "gobj" and \
               vals[k + 2][0] == "gobj" and vals[k + 3][0] == "float":
                start = k
                break
        if start >= 0 and start + 21 < len(vals):
            item["isDead"] = vals[start + 5][1] == "true"
            hp = float(vals[start + 20][1])
            mhp = float(vals[start + 21][1])
            item["hp"] = hp
            item["maxHp"] = mhp
            item["fullHp"] = hp < 0  # -1 = 满血哨兵值 (游戏内部约定)
        out.append(item)
    return out


@mcp.tool()
def game_unit_dump(save_name: str = "") -> str:
    """解析游戏存档, 返回每个单位的真实数据 (类型/队伍/血量/位置/存活).

    save_name: 存档文件名 (默认: saves/ 下最新修改的文本存档).
    数据来源: plainTextDebugSave 生成的纯文本存档 (含 'Saving unit:' 分段) —
              hp/maxHp 为存档真实值 (hp=-1 表示满血哨兵), x/y 为世界坐标.
    若无文本存档: 需先在游戏中执行 plainTextDebugSave(true) 后存档 (对局内调用, 非对局会崩).
    """
    # 定位存档
    target = None
    if save_name.strip():
        cand = [SAVES_DIR / save_name.strip(), GAME_ROOT / save_name.strip()]
        for c in cand:
            if c.exists():
                target = c
                break
        if target is None:
            return json.dumps({"ok": False, "error": f"存档不存在: {save_name}",
                               "saves_dir": str(SAVES_DIR)}, ensure_ascii=False)
    else:
        if SAVES_DIR.exists():
            txs = [f for f in SAVES_DIR.iterdir()
                   if f.is_file() and f.suffix.lower() in (".rwsave", ".save", ".txt")]
            txs.sort(key=lambda f: f.stat().st_mtime, reverse=True)
            for f in txs:
                try:
                    head = f.read_text(encoding="utf-8", errors="replace")[:400]
                except Exception:
                    continue
                if "Saving unit:" in head or "rustedWarfareSave" in head:
                    target = f
                    break
        if target is None:
            return json.dumps({"ok": False, "error": "saves/ 下无文本存档; "
                               "需对局内调用 plainTextDebugSave(true) 后存档生成",
                               "saves_dir": str(SAVES_DIR)}, ensure_ascii=False)
    try:
        text = target.read_text(encoding="utf-8", errors="replace")
    except Exception as e:
        return json.dumps({"ok": False, "error": f"读取存档失败: {e}"}, ensure_ascii=False)
    if "Saving unit:" not in text:
        return json.dumps({"ok": False, "error": f"{target.name} 不是纯文本存档 (无 Saving unit 分段); "
                           "需 plainTextDebugSave(true) 生成文本存档"}, ensure_ascii=False)
    units = _parse_save_units(text)
    types = {}
    for u in units:
        types[u["type"]] = types.get(u["type"], 0) + 1
    return json.dumps({
        "ok": True, "save": target.name, "save_path": str(target),
        "unit_count": len(units), "types": types,
        "units": units[:200], "truncated": len(units) > 200,
        "note": "hp=-1 表示满血哨兵值; x/y 为世界坐标; team=-1 表示中立/无主",
    }, ensure_ascii=False)


@mcp.tool()
def game_victory_check(team: int = -1) -> str:
    """胜利判定 (按游戏标准: 敌方队伍被消灭 = wiped out/defeated/eliminated).

    team: 可选过滤队伍 id (1v1 中我方 0 / AI 1). 不填则检查全部.
    证据 (双通道):
      1) 主证据: 游戏日志文件 (game-mcp.log) 中 'wiped out'/'has been defeated'/'eliminated'
         命中行 (游戏真实广播, 如 'AI - Hard has been wiped out (Team: 1)');
      2) 辅助: 对局中调用 debug.isTeamInVictory(team) (调用成功≠胜利, 仅作存在性辅助).
    注意: 脚本引擎无法取返回值, 胜利判定以文件日志证据为准 (防止误报).
    """
    p = Path(LOG_FILE)
    all_hits = []
    evidence = []
    if p.exists():
        ls = p.read_text(encoding="utf-8", errors="replace").splitlines()
        for kw in ("wiped out", "has been defeated", "eliminated", "victory"):
            h = [l for l in ls if kw.lower() in l.lower()]
            all_hits += h[-6:]
        # 从全量命中提取被灭队伍的显示字母 (如 'AI - Hard has been wiped out (Team: A)')
        import re as _re
        letters = sorted({m.group(1) for l in all_hits
                          for m in [_re.search(r"\(Team: ([A-P])\)", l)] if m})
        if team >= 0:
            # 队伍显示名可能为数字 (Team: 1) 或字母 (Team: A, 内部索引≠字母序, 不可靠)
            # 故数字形式严格过滤; 字母形式不过滤, 由调用者从证据行判断
            evidence = [l for l in all_hits if f"team: {team}" in l.lower()
                        or f"(team: {team})" in l.lower() or f"team {team}" in l.lower()]
            if not evidence and letters:
                evidence_note = (f"检测到队伍被灭但数字过滤未命中; 被灭队伍显示字母: {letters} "
                                 f"(内部 team 索引 {team} 的显示字母可能不同, 建议不传 team 全量检查)")
            else:
                evidence_note = ""
        else:
            evidence = all_hits
            evidence_note = ""
    aux = []
    if CONN.state in ("IN_GAME", "REPLAY") and team >= 0:
        resp, ok = CONN.call_script(f"debug.isTeamInVictory({team})")
        aux.append({"call": f"debug.isTeamInVictory({team})", "ok": ok,
                    "note": "调用成功仅表示方法可调, 不代表胜利"})
    return json.dumps({
        "ok": True,
        "victory": len(evidence) > 0,
        "team": team,
        "evidence": evidence[-12:],
        "evidence_count": len(evidence),
        "wiped_out_team_letters": letters,
        "evidence_note": evidence_note,
        "aux_checks": aux,
        "game_state": CONN.state,
        "note": "胜利标准: 日志出现敌方队伍 wiped out/defeated/eliminated (游戏真实广播); "
                "队伍显示字母与内部索引可能不同, 建议不传 team 全量检查",
    }, ensure_ascii=False)


@mcp.tool()
def game_script(expression: str) -> str:
    """直接执行 script 表达式 (高级, 绕过签名白名单, 谨慎使用).

    expression: 单条表达式或分号分隔的多条 (依次注入, 每条独立 script 命令).
    注意: 脚本引擎不支持字符串拼接/嵌套函数调用/运算; logDebug 仅接受 String 字面量.
    """
    exprs = [e.strip() for e in expression.split(";") if e.strip()]
    results = []
    for e in exprs:
        resp, ok = CONN.call_script(e)
        results.append({"expr": e, "ok": ok,
                        "resp_tail": resp.splitlines()[-3:]})
    if len(exprs) == 1:
        r = results[0]
        return json.dumps({"ok": r["ok"], "expression": r["expr"],
                           "response_tail": r["resp_tail"]}, ensure_ascii=False)
    return json.dumps({"ok": all(r["ok"] for r in results), "count": len(exprs),
                       "results": results}, ensure_ascii=False)


# ── 流程聚合工具 (v19.133f98 全覆盖扩展) ──────────────────────
def _wait_ready(seconds=10):
    time.sleep(seconds)
    return CONN.tail(6)


def _guard_crashed():
    """流程工具崩溃守卫: 返回 None 表示可继续, 否则返回拦截 JSON."""
    if CONN.state == "CRASHED":
        return json.dumps({"ok": False, "blocked": True,
                           "reason": f"游戏已崩溃 (第 {CONN.crash_count} 次) — 请先 game_reset + game_launch",
                           "game_state": CONN.state}, ensure_ascii=False)
    return None


@mcp.tool()
def game_skirmish_start(map_path: str) -> str:
    """开始单机 AI 对局 (root.startNew 封装): 地图路径如 maps/skirmish/b5map.tmx, 等待 10s 就绪."""
    g = _guard_crashed()
    if g:
        return g
    resp, ok = CONN.call_script(f"root.startNew('{map_path}')")
    if ok:
        CONN.state = "IN_GAME"
    tail = _wait_ready()
    return json.dumps({"ok": ok, "map": map_path, "game_state": CONN.state,
                       "log_tail": tail}, ensure_ascii=False)


@mcp.tool()
def game_skirmish_prep(map_path: str, team: int = 0, credits: int = 100000,
                       unit_type: str = "c_mammothTank", unit_count: int = 5) -> str:
    """开局 + 备战: startNew → 给队伍资金 → 创建单位 (单机 AI 对局操作入口).

    team: 队伍 id (默认 0); credits: 资金; unit_type: 单位类型 (如 c_mammothTank/c_tank/bomber,
    可用 game_unit_names 查询全表); unit_count: 数量.
    """
    g = _guard_crashed()
    if g:
        return g
    # 类型预检: 无效类型直接报错 (避免调用成功但没刷出单位)
    bad = _invalid_unit_type(unit_type)
    if bad:
        return json.dumps({"ok": False, "error": bad}, ensure_ascii=False)
    r1, ok1 = CONN.call_script(f"root.startNew('{map_path}')")
    if ok1:
        CONN.state = "IN_GAME"
    time.sleep(10)
    r2, ok2 = CONN.call_script(f"debug.setTeamCredits({team}, {credits})")
    r3, ok3 = CONN.call_script(
        f"debug.createManyUnits('{unit_type}', 500.0, 500.0, {team}, false, {unit_count})")
    tail = CONN.tail(6)
    return json.dumps({"ok": ok1 and ok2 and ok3, "map": map_path, "team": team,
                       "credits": credits, "units": unit_count, "game_state": CONN.state,
                       "log_tail": tail}, ensure_ascii=False)


@mcp.tool()
def game_load_save(save_path: str) -> str:
    """加载存档 (debug.loadSaveFromSystemPath 封装): 存档文件路径."""
    g = _guard_crashed()
    if g:
        return g
    resp, ok = CONN.call_script(f"debug.loadSaveFromSystemPath('{save_path}')")
    return json.dumps({"ok": ok, "save": save_path, "game_state": CONN.state,
                       "log_tail": CONN.tail(6)}, ensure_ascii=False)


@mcp.tool()
def game_replay_flow(replay_name: str, watch_seconds: int = 20) -> str:
    """回放流程: loadReplay → 等待 watch_seconds → 日志摘要 (无异常/活动迹象)."""
    g = _guard_crashed()
    if g:
        return g
    r1, ok1 = CONN.call_script(f"root.loadReplay('{replay_name}')")
    time.sleep(max(5, min(watch_seconds, 120)))
    tail = CONN.tail(10)
    crashed = CONN.state == "CRASHED"
    return json.dumps({"ok": ok1 and not crashed, "replay": replay_name,
                       "watched_seconds": watch_seconds, "crashed": crashed,
                       "game_state": CONN.state, "log_tail": tail}, ensure_ascii=False)


@mcp.tool()
def game_unit_army(team: int, unit_type: str, count: int, x: float = 500.0, y: float = 500.0) -> str:
    """为队伍创建部队 (debug.createManyUnits 封装).

    unit_type: 单位类型名 — 可用 game_unit_names 查询全表 (52 内置枚举名 + 125 ini 注册名).
    无效类型会直接报错 (不再出现"调用成功但没刷出单位").
    """
    bad = _invalid_unit_type(unit_type)
    if bad:
        return json.dumps({"ok": False, "error": bad}, ensure_ascii=False)
    resp, ok = CONN.call_script(
        f"debug.createManyUnits('{unit_type}', {x}, {y}, {team}, false, {count})")
    return json.dumps({"ok": ok, "team": team, "type": unit_type, "count": count,
                       "log_tail": CONN.tail(4)}, ensure_ascii=False)


@mcp.tool()
def game_view(x: float, y: float, zoom: float = 1.0) -> str:
    """视角控制: lookAt(x,y) + setZoom(zoom) 组合."""
    r1, ok1 = CONN.call_script(f"debug.lookAt({x}, {y})")
    r2, ok2 = CONN.call_script(f"debug.setZoom({zoom})")
    return json.dumps({"ok": ok1 and ok2, "x": x, "y": y, "zoom": zoom}, ensure_ascii=False)


# ── 安全状态层 (v19.133f98: 避开原版固有 NPE 危险方法) ────────
# 实测安全的方法白名单 (对局中调用已验证不崩)
SAFE_QUERY = {
    "debug.numberOfHumanPlayers": "人类玩家数",
    "debug.numberOfPlayersPlusAI": "玩家+AI 总数",
    "debug.isNetworkGameActive": "网络对局是否激活",
    "debug.currentPid": "进程 PID",
    "debug.isTeamWipedOut": "队伍是否被灭 (需 team 参数)",
    "debug.isTeamDefeated": "队伍是否战败 (需 team 参数)",
    "debug.getNumberOfDesyncErrors": "同步错误数",
    "debug.getNumberOfDesyncPasses": "同步通过数",
}


@mcp.tool()
def game_state() -> str:
    """安全游戏状态查询: 日志解析 + 安全方法白名单 (避开 getLocalPlayerId/getPlayerName 等原版 NPE 方法).

    返回: 日志中 ai_debug/MissionEngine/异常/崩溃 活动迹象 + 安全查询结果.
    """
    logs = list(CONN.log)
    ai = [ln[:80] for ln in logs if "ai_debug" in ln][-3:]
    mission = [ln[:80] for ln in logs if "MissionEngine" in ln][-3:]
    errors = [ln[:100] for ln in logs if "Exception" in ln or "onGameCrash" in ln][-3:]
    return json.dumps({
        "ok": True,
        "log_buffer": len(logs),
        "ai_activity": ai,
        "mission_triggers": mission,
        "errors_or_crashes": errors,
        "note": "查询类方法有原版 NPE 风险, 状态以日志解析为主; 如需调用可试 debug_numberOfHumanPlayers 等白名单方法",
    }, ensure_ascii=False)


@mcp.tool()
def game_safe_query(query: str, team: int = 0) -> str:
    """安全调用白名单查询方法 (game_state 的补充).

    query: numberOfHumanPlayers / numberOfPlayersPlusAI / isNetworkGameActive / currentPid /
           isTeamWipedOut / isTeamDefeated (后两者需 team 参数).
    """
    full = f"debug.{query}"
    if full not in SAFE_QUERY:
        return json.dumps({"ok": False, "error": f"不在安全白名单: {full}; 可用: {list(SAFE_QUERY)}"},
                          ensure_ascii=False)
    if query in ("isTeamWipedOut", "isTeamDefeated"):
        expr = f"{full}({team})"
    else:
        expr = f"{full}()"
    resp, ok = CONN.call_script(expr)
    return json.dumps({"ok": ok, "query": expr, "desc": SAFE_QUERY[full],
                       "log_tail": CONN.tail(3)}, ensure_ascii=False)


# ── 动态注册 script_api.json 全量 222 方法 ──────────────────
API = json.load(open(API_PATH, encoding="utf-8"))


def _format_literal(ptype: str, value):
    """按参数类型构造脚本字面量 (与 debug_client.format_arg 一致)."""
    t = ptype.split(".")[-1]
    if t == "String":
        return "'" + str(value).replace("'", "\\'") + "'"
    if t in ("boolean", "Boolean"):
        return "true" if str(value).lower() in ("true", "1", "yes") else "false"
    if t == "float":
        return str(float(value))
    return str(value)  # int/long


def _check_call(obj: str, fn: str) -> tuple:
    """调用防护: 返回 (allowed, reason). 崩溃防护核心 (v19.133f98).

    规则:
    1. 硬编码危险方法 (DANGEROUS) → 拦截 (原版固有 NPE, 调用必崩)
    2. multiplayer.* → 拦截 (联机专用, 单机场景调用崩)
    3. 状态过滤: CRASHED 全拦; debug.* 仅 IN_GAME/REPLAY 允许;
       root.*/mods.* 在 MAIN_MENU/IN_GAME/REPLAY 允许; UNKNOWN 保守拦截 debug.*
    """
    full = f"{obj}.{fn}"
    if full in DANGEROUS:
        return False, f"危险方法已拦截 (原版固有 NPE/对局中崩溃): {full} — {DANGEROUS[full]}"
    if obj == "multiplayer":
        return False, f"multiplayer.* 联机大厅专用, 主菜单/单机调用会崩游戏, 已拦截: {full}"
    st = CONN.state
    if st == "CRASHED":
        return False, f"游戏已崩溃 (检测到 onGameCrash/连接重置, 第 {CONN.crash_count} 次) — 请先 game_stop + game_launch 重启"
    if obj == "debug" and st != "IN_GAME" and st != "REPLAY":
        hint = "对局中" if st in ("MAIN_MENU", "STARTING") else "开局进入对局 (root_startNew)"
        return False, f"debug.* 方法仅对局中安全 (实测主菜单调用会崩), 当前状态 {st} — 请先 {hint}"
    return True, ""


def _make_tool(obj: str, fn: str, rtype: str, ptypes: list):
    """闭包生成单个游戏方法工具."""
    full = f"{obj}.{fn}"
    py_params = []
    annos = {}
    for i, t in enumerate(ptypes):
        name = f"arg{i}"
        pytype = TYPE_MAP.get(t.split(".")[-1], str)
        annos[name] = pytype
        py_params.append(inspect.Parameter(name, inspect.Parameter.POSITIONAL_OR_KEYWORD, annotation=pytype))
    danger_note = DANGEROUS.get(full, "")
    if not danger_note and obj == "multiplayer":
        danger_note = "⚠️ 联机大厅专用: 主菜单/单机场景调用可能 NPE 崩游戏 (MCP 实测 loadUsername), 仅在联机对局中使用"
    warn_note = _warn_note(full)

    def tool(*args, **kwargs):
        allowed, reason = _check_call(obj, fn)
        if not allowed:
            return json.dumps({"ok": False, "blocked": True, "method": full,
                               "reason": reason, "game_state": CONN.state}, ensure_ascii=False)
        vals = list(args)
        for i in range(len(vals), len(ptypes)):
            vals.append(kwargs.get(f"arg{i}"))
        if len(vals) != len(ptypes):
            return json.dumps({"ok": False, "error": f"参数数不符: 需要 {len(ptypes)}"}, ensure_ascii=False)
        lits = ",".join(_format_literal(t, v) for t, v in zip(ptypes, vals))
        expr = f"{full}({lits})"
        resp, ok = CONN.call_script(expr)
        if ok:
            # 确定性状态推进: 开局命令成功 → IN_GAME; 回放加载成功 → REPLAY
            if obj == "root" and fn == "startNew":
                CONN.state = "IN_GAME"
            elif obj == "root" and fn == "loadReplay":
                CONN.state = "REPLAY"
        return json.dumps({"ok": ok, "method": expr, "game_state": CONN.state,
                           "log_tail": CONN.tail(4)}, ensure_ascii=False)

    tool.__name__ = f"{obj}_{fn}"
    notes = " ".join(x for x in (danger_note, warn_note) if x)
    tool.__doc__ = (f"调用游戏方法 {full}({', '.join(ptypes)}) → {rtype}. "
                    f"参数: {ptypes}. {notes}" if notes else
                    f"调用游戏方法 {full}({', '.join(ptypes)}) → {rtype}. 参数: {ptypes}.")
    tool.__signature__ = inspect.Signature(py_params)
    return tool


def register_all_tools():
    for obj, methods in API.items():
        for fn, (rtype, ptypes) in methods.items():
            tool = _make_tool(obj, fn, rtype, ptypes)
            mcp.add_tool(tool)
            globals()[tool.__name__] = tool  # 同时挂模块命名空间, 便于 CLI/脚本直接调用


# ── CLI ───────────────────────────────────────────────────
def main():
    args = sys.argv[1:]
    if "--list-tools" in args:
        register_all_tools()

        async def _dump():
            for t in await mcp.list_tools():
                print(f"{t.name:35s} {t.description.splitlines()[0][:80]}")
        asyncio.run(_dump())
        return 0
    if "--self-test" in args:
        if "--launch" in args:
            print(json.dumps(_launch_game(False, PORT, ""), ensure_ascii=False))
        register_all_tools()
        print("ping:", CONN.ping())
        print("tools:", len(mcp.list_tools()))
        return 0
    if "--launch-game" in args:
        print(json.dumps(_launch_game(False, PORT, ""), ensure_ascii=False))
        return 0
    if "--stop-game" in args:
        print(json.dumps(_stop_game(), ensure_ascii=False))
        return 0
    register_all_tools()
    mcp.run()  # stdio transport
    return 0


if __name__ == "__main__":
    sys.exit(main())
