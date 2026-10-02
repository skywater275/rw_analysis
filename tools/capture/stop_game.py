#!/usr/bin/env python3
"""
stop_game — 游戏进程「确定性停止」入口 (D-9② 落库, 2026-09-21)

背景（QAV 实测证据，handoff-QAV-05/06/07 + KB-AM/KB-BF）：
  · `taskkill /PID <pid> /T /F` 对 `jvm64\\bin\\java.exe` 游戏进程**恒报 `ERROR: Access denied`**
    —— 是**误报**，进程其实在被杀；但**终止是异步的**：实测约 **47.1 s** 才完全退出、端口才释放。
  · 用进程句柄 `TerminateProcess`（即 Python `Popen.terminate()` 的底层调用）实测
    **0.5 s 退出 / 2.0 s 端口释放**，是 `taskkill` 的确定性替代。
  · 「已终止」的判据**不是**任何返回码，而是**轮询**两个客观条件：
      ① 目标 PID 全部消失（`OpenProcess` + `GetExitCodeProcess`）
      ② 调试端口（默认 5677）不再接受连接
    本脚本把该口径固化为可复用函数 + CLI（可 `import` 复用，见 `terminate_processes()`）。

Usage:
  python tools/capture/stop_game.py                       # 自动发现并停止 (全部候选游戏根下的 jvm64\bin\java*.exe)
  python tools/capture/stop_game.py --pid 13000           # 指定 PID (可重复)
  python tools/capture/stop_game.py --list                # 只列出候选进程
  python tools/capture/stop_game.py --dry-run             # 只报告将停止什么 (S1: 不落任何副作用)
  python tools/capture/stop_game.py --game-root "D:\\RW"  # 限定游戏根 (含 jvm64\\bin\\java.exe)
  python tools/capture/stop_game.py --port 5677 --timeout 30 --json stop.json

参数:
  --pid N          目标 PID (可重复给出); 给出后不再自动发现
  --list           仅列出候选/目标进程, 不做任何终止
  --dry-run        同 --list 但额外打印将执行的动作 (不终止)
  --game-root P    限定游戏根目录 (默认: 自动探测 **全部** 含 jvm64/bin/java.exe 的候选根 —— 本机
                   同时存在部署态 {GAME} 与项目内 {RW}/RustedWarfare 两份安装, 只取第一个会漏)
  --port P         调试端口 (默认 5677; 0 = 跳过端口判据)
  --timeout S      轮询「已终止」的上限秒数 (默认 30)
  --interval S     轮询间隔 (默认 0.25)
  --json FILE      结构化结果落盘 (utf-8)

退出码: 0 = 目标已全部消失且端口已释放 (或本就是停止态); 1 = 超时/无法定位/失败。
"""
import ctypes
import json
import socket
import sys
import time
from ctypes import wintypes
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import DEBUG_HOST, DEBUG_PORT, GAME_LIB  # noqa: E402

# ── Win32 常量 ───────────────────────────────────────────────────────
PROCESS_TERMINATE = 0x0001
PROCESS_QUERY_LIMITED_INFORMATION = 0x1000
TH32CS_SNAPPROCESS = 0x00000002
INVALID_HANDLE_VALUE = ctypes.c_void_p(-1).value
STILL_ACTIVE = 259
ERROR_INVALID_PARAMETER = 87
ERROR_ACCESS_DENIED = 5

JAVA_EXES = ("java.exe", "javaw.exe")


class PROCESSENTRY32W(ctypes.Structure):
    """CreateToolhelp32Snapshot/Process32NextW 的进程条目（Unicode 版）。"""
    _fields_ = [
        ("dwSize", wintypes.DWORD),
        ("cntUsage", wintypes.DWORD),
        ("th32ProcessID", wintypes.DWORD),
        ("th32DefaultHeapID", ctypes.POINTER(ctypes.c_ulong)),
        ("th32ModuleID", wintypes.DWORD),
        ("cntThreads", wintypes.DWORD),
        ("th32ParentProcessID", wintypes.DWORD),
        ("pcPriClassBase", ctypes.c_long),
        ("dwFlags", wintypes.DWORD),
        ("szExeFile", wintypes.WCHAR * 260),
    ]


if sys.platform == "win32":
    _k32 = ctypes.WinDLL("kernel32", use_last_error=True)
    _k32.CreateToolhelp32Snapshot.restype = wintypes.HANDLE
    _k32.CreateToolhelp32Snapshot.argtypes = [wintypes.DWORD, wintypes.DWORD]
    _k32.Process32FirstW.argtypes = [wintypes.HANDLE, ctypes.POINTER(PROCESSENTRY32W)]
    _k32.Process32NextW.argtypes = [wintypes.HANDLE, ctypes.POINTER(PROCESSENTRY32W)]
    _k32.OpenProcess.restype = wintypes.HANDLE
    _k32.OpenProcess.argtypes = [wintypes.DWORD, wintypes.BOOL, wintypes.DWORD]
    _k32.TerminateProcess.argtypes = [wintypes.HANDLE, wintypes.UINT]
    _k32.GetExitCodeProcess.argtypes = [wintypes.HANDLE, ctypes.POINTER(wintypes.DWORD)]
    _k32.QueryFullProcessImageNameW.argtypes = [
        wintypes.HANDLE, wintypes.DWORD, wintypes.LPWSTR, ctypes.POINTER(wintypes.DWORD)]
    _k32.CloseHandle.argtypes = [wintypes.HANDLE]
else:  # 非 Windows: 仅保留接口, 调用时明确报错 (项目为 Windows 专用, 此处保证可导入)
    _k32 = None


def image_path(pid: int) -> str:
    """取进程可执行文件全路径（失败返回空串）。"""
    if _k32 is None:
        return ""
    h = _k32.OpenProcess(PROCESS_QUERY_LIMITED_INFORMATION, False, pid)
    if not h:
        return ""
    try:
        buf = ctypes.create_unicode_buffer(1024)
        size = wintypes.DWORD(1024)
        if _k32.QueryFullProcessImageNameW(h, 0, buf, ctypes.byref(size)):
            return buf.value
        return ""
    finally:
        _k32.CloseHandle(h)


def pid_alive(pid: int):
    """PID 是否仍存活。返回 (alive, 说明)。打不开句柄时区分「已消失」与「拒绝访问」两义。"""
    if _k32 is None:
        return (False, "非 Windows 平台")
    h = _k32.OpenProcess(PROCESS_QUERY_LIMITED_INFORMATION, False, pid)
    if not h:
        err = ctypes.get_last_error()
        if err == ERROR_INVALID_PARAMETER:
            return (False, "进程不存在")
        if err == ERROR_ACCESS_DENIED:
            return (True, "存在但拒绝查询 (Access denied)")
        return (False, f"OpenProcess 失败 err={err}")
    try:
        code = wintypes.DWORD()
        if _k32.GetExitCodeProcess(h, ctypes.byref(code)):
            return (code.value == STILL_ACTIVE, f"exit_code={code.value}")
        return (True, "GetExitCodeProcess 失败")
    finally:
        _k32.CloseHandle(h)


def list_java_processes():
    """枚举全部 java.exe / javaw.exe 进程, 附带映像全路径。"""
    out = []
    if _k32 is None:
        return out
    snap = _k32.CreateToolhelp32Snapshot(TH32CS_SNAPPROCESS, 0)
    if not snap or snap == INVALID_HANDLE_VALUE:
        return out
    try:
        e = PROCESSENTRY32W()
        e.dwSize = ctypes.sizeof(PROCESSENTRY32W)
        ok = _k32.Process32FirstW(snap, ctypes.byref(e))
        while ok:
            if e.szExeFile.lower() in JAVA_EXES:
                out.append({"pid": e.th32ProcessID, "name": e.szExeFile,
                            "path": image_path(e.th32ProcessID)})
            ok = _k32.Process32NextW(snap, ctypes.byref(e))
    finally:
        _k32.CloseHandle(snap)
    return out


def candidate_game_roots(explicit: str = None):
    """返回**全部**含 `jvm64/bin/java(.exe)` 的游戏根目录（显式指定者优先）。

    为什么是列表而不是单个：本机存在两份 Rusted Warfare 安装 —— 部署态
    `{GAME}/jvm64/bin/java.exe`（QAV 实测实际启动的那份）与项目内
    `{RW}/RustedWarfare/jvm64/bin/java.exe`。若只取「第一个」会在自动发现时漏掉真正
    在跑的进程（2026-09-21 首轮实测踩到：`--pid` 模式不受影响，但无参自动发现会漏）。
    """
    cands = []
    if explicit:
        cands.append(Path(explicit))
    # ROOT.parent = 部署态游戏根 ({GAME}); ROOT/'RustedWarfare' = 项目内干净副本。
    # ⚠️ 必须显式带上 ROOT.parent —— rwlib.config.GAME_LIB 优先解析**项目内**那份 jar,
    #    只由 GAME_LIB 推导会漏掉 QAV 实际启动的部署态 {GAME}（2026-09-21 实测踩到）。
    for p in (GAME_LIB.parent, ROOT.parent, ROOT / "RustedWarfare",
              GAME_LIB.parent.parent, Path.cwd()):
        if p not in cands:
            cands.append(p)
    return [c for c in cands
            if any((c / "jvm64" / "bin" / e).exists() for e in ("java.exe", "javaw.exe"))]


def find_game_root(explicit: str = None):
    """首个候选游戏根（仅用于显示/默认展示）；实际筛选见 discover_game_pids。"""
    roots = candidate_game_roots(explicit)
    return roots[0] if roots else None


def discover_game_pids(roots):
    """按「映像路径位于 <root>/jvm64/bin 下」筛选游戏进程（不误伤系统 JDK 的 java）。

    参数 roots 可为单个目录或目录列表；命中项附带 `root` 字段标明来自哪份安装。
    """
    if isinstance(roots, (str, Path)):
        roots = [roots]
    prefixes = [str(Path(r) / "jvm64" / "bin").lower() for r in roots]
    hits = []
    for p in list_java_processes():
        pth = (p["path"] or "").lower()
        if not pth:
            continue
        for pre in prefixes:
            if pth.startswith(pre):
                q = dict(p)
                q["root"] = pre
                hits.append(q)
                break
    return hits


def port_listening(host: str = DEBUG_HOST, port: int = DEBUG_PORT, timeout: float = 0.3) -> bool:
    """端口是否可连接（connect 成功 = 仍在监听）。"""
    try:
        with socket.create_connection((host, port), timeout=timeout):
            return True
    except OSError:
        return False


def terminate_pid(pid: int):
    """用进程句柄 TerminateProcess 终止一个 PID（= Python `Popen.terminate()` 的底层调用）。

    返回 (ok, 说明)。**不以返回码判「已终止」** —— 终止是异步的，必须轮询 (见 wait_terminated)。
    """
    if _k32 is None:
        return (False, "非 Windows 平台")
    h = _k32.OpenProcess(PROCESS_TERMINATE, False, pid)
    if not h:
        err = ctypes.get_last_error()
        return (False, f"OpenProcess(PROCESS_TERMINATE) 失败 err={err}")
    try:
        ok = bool(_k32.TerminateProcess(h, 1))
        err = ctypes.get_last_error()
        return (ok, "TerminateProcess 已发出" if ok else f"TerminateProcess 失败 err={err}")
    finally:
        _k32.CloseHandle(h)


def wait_terminated(pids, host: str = DEBUG_HOST, port: int = DEBUG_PORT,
                    timeout: float = 30.0, interval: float = 0.25):
    """轮询直到「目标 PID 全部消失」+「端口释放」。

    返回 dict: {ok, elapsed_s, proc_gone_s, port_free_s, alive_pids, port_listening}
    —— 判据完全客观, 不依赖任何终止 API 的返回码（KB-AM）。
    """
    pids = [p for p in pids]
    t0 = time.time()
    proc_gone_s = None
    port_free_s = None
    alive = []
    listening = None
    while True:
        alive = [p for p in pids if pid_alive(p)[0]]
        listening = port_listening(host, port) if port else False
        if proc_gone_s is None and not alive:
            proc_gone_s = round(time.time() - t0, 2)
        if port_free_s is None and not listening:
            port_free_s = round(time.time() - t0, 2)
        if not alive and not listening:
            break
        if time.time() - t0 >= timeout:
            break
        time.sleep(interval)
    return {
        "ok": (not alive) and (not listening),
        "elapsed_s": round(time.time() - t0, 2),
        "proc_gone_s": proc_gone_s,
        "port_free_s": port_free_s,
        "alive_pids": alive,
        "port_listening": bool(listening),
    }


def stop_game(pids=None, game_root=None, port: int = DEBUG_PORT, timeout: float = 30.0,
              interval: float = 0.25, dry_run: bool = False):
    """可复用入口：停止游戏进程并轮询到「已终止」。

    参数:
        pids      : 显式 PID 列表 (None = 按候选游戏根自动发现, 覆盖本机全部安装)
        game_root : 显式游戏根 (None = 自动探测全部候选根)
        port      : 调试端口 (0 = 跳过端口判据)
        timeout   : 轮询上限秒数
    返回:
        dict 结果（含 ok/targets/terminate 明细/轮询判据）
    """
    roots = candidate_game_roots(game_root)
    if pids:
        targets = [{"pid": p, "name": "", "path": image_path(p), "source": "explicit"} for p in pids]
    else:
        if not roots:
            return {"ok": False, "error": "未找到游戏根 (含 jvm64/bin/java.exe); 请用 --game-root 或 --pid",
                    "game_roots": [], "targets": []}
        targets = discover_game_pids(roots)
    if not targets:
        # 无候选: 以端口状态定「是否已是停止态」
        listening = port_listening(port=port) if port else False
        return {"ok": not listening, "game_roots": [str(r) for r in roots],
                "targets": [], "note": "未发现游戏进程" + ("" if not listening else "，但端口仍被占用"),
                "port_listening_before": listening}

    actions = []
    if not dry_run:
        for t in targets:
            ok, detail = terminate_pid(t["pid"])
            actions.append({"pid": t["pid"], "ok": ok, "detail": detail})
    wait = wait_terminated([t["pid"] for t in targets], port=port,
                           timeout=timeout, interval=interval)
    return {"ok": bool(wait["ok"]), "game_roots": [str(r) for r in roots],
            "targets": targets, "terminate": actions, "wait": wait,
            "dry_run": dry_run}


def main():
    argv = sys.argv[1:]
    pids, json_path = [], None
    game_root, mode = None, "stop"
    port, timeout, interval = DEBUG_PORT, 30.0, 0.25
    i = 0
    while i < len(argv):
        a = argv[i]
        if a in ("-h", "--help"):
            print(__doc__)
            return 0
        elif a == "--pid" and i + 1 < len(argv):
            pids.append(int(argv[i + 1])); i += 2
        elif a == "--game-root" and i + 1 < len(argv):
            game_root = argv[i + 1]; i += 2
        elif a == "--port" and i + 1 < len(argv):
            port = int(argv[i + 1]); i += 2
        elif a == "--timeout" and i + 1 < len(argv):
            timeout = float(argv[i + 1]); i += 2
        elif a == "--interval" and i + 1 < len(argv):
            interval = float(argv[i + 1]); i += 2
        elif a == "--json" and i + 1 < len(argv):
            json_path = Path(argv[i + 1]); i += 2
        elif a == "--list":
            mode = "list"; i += 1
        elif a == "--dry-run":
            mode = "list"; i += 1
        else:
            print(f"错误: 未知参数 {a!r}（见 --help）")
            return 1

    roots = candidate_game_roots(game_root)
    if pids:
        targets = [{"pid": p, "name": "", "path": image_path(p), "source": "explicit"} for p in pids]
    else:
        targets = discover_game_pids(roots) if roots else []

    print(f"[环境] 候选游戏根 ({len(roots)}):")
    for r in roots:
        print(f"        {r}")
    print(f"[环境] 调试端口 {port} 当前{'在监听' if port and port_listening(port=port) else '未监听'}")
    print(f"[目标] {len(targets)} 个进程")
    for t in targets:
        alive, detail = pid_alive(t["pid"])
        src = t.get("root", t.get("source", ""))
        print(f"   PID {t['pid']:<7} {t['name']:<9} alive={alive} ({detail})  {t['path']}")
        print(f"           来源: {src}")

    if mode == "list":
        print("[--list/--dry-run] 未执行任何终止（S1）")
        return 0

    if not targets:
        listening = port_listening(port=port) if port else False
        if listening:
            print(f"错误: 端口 {port} 仍在监听但未定位到游戏进程 → 请用 --pid 指定")
            return 1
        print("无候选进程且端口未监听 → 已是停止态 ✅")
        return 0

    t0 = time.time()
    print("[动作] 用进程句柄 TerminateProcess 终止（= Popen.terminate()，非 taskkill）")
    for t in targets:
        ok, detail = terminate_pid(t["pid"])
        print(f"   PID {t['pid']:<7} → {'已发出' if ok else '失败'} ({detail})")
    wait = wait_terminated([t["pid"] for t in targets], port=port, timeout=timeout, interval=interval)

    print(f"[判据] 轮询「进程消失 + 端口释放」（不看返回码）")
    print(f"   进程全部消失: {wait['proc_gone_s']} s")
    print(f"   端口 {port} 释放: {wait['port_free_s']} s")
    print(f"   总耗时: {wait['elapsed_s']} s, 残留 PID: {wait['alive_pids']}, "
          f"端口仍在监听: {wait['port_listening']}")
    print(f"[结果] {'已确认终止 ✅' if wait['ok'] else '未确认终止 ❌'}")

    if json_path is not None:
        json_path.write_text(json.dumps(
            {"game_roots": [str(r) for r in roots], "targets": targets,
             "wall_elapsed_s": round(time.time() - t0, 2), "wait": wait,
             "port": port, "timeout": timeout}, ensure_ascii=False, indent=2), encoding="utf-8")
        print(f"[落盘] {json_path}")
    return 0 if wait["ok"] else 1


if __name__ == "__main__":
    sys.exit(main())
