#!/usr/bin/env python3
"""
debug_script — 游戏调试服务器客户端 (v19.96 → D-9 修复 2026-09-21)

通过 TCP 连接 DebugServer (rts.a.a, `-debug <port>:local` 启动, 默认端口 5677),
发送命令并取回响应。

**D-9① 修复要点（协议真实字节）**：服务端写回用 `print()`（不含换行），且**只有
`function` / `functionNoTimeout` 带 `\\u0000` 终止符**；`ping`(`pong`) 与 `script`(`done`)
在协议上**没有终止符**。旧实现「一直等到 `\\x00` 结尾」→ 对 `script` 必然挂满 30s socket
超时后返回 `连接失败: timed out`（而命令其实早已送达，见 handoff-QAV-05/06/07 与
API-SURFACE §1.4）。现按 verb 查协议终止符：
  · `function*` → 等 `\\x00`（兼容 modified-UTF8 `C0 80` 形态）
  · 其它      → 首字节到达后按空闲间隔（--idle）判定响应完整
并给出明确诊断与可调上限（--timeout），不再白等。

Usage:
  python tools/utils/debug_script.py "root.loadReplay('r5.replay')"
  python tools/utils/debug_script.py --wait 3 "root.hostStart(false)" "mp.multiplayerStart()"
  python tools/utils/debug_script.py --host 127.0.0.1 --port 5677 "debug.currentPid()"
  python tools/utils/debug_script.py --verb ping                       # 心跳 (立即回 pong)
  python tools/utils/debug_script.py --verb function "root.getVersionName()"   # 取返回值 (真返回)
  python tools/utils/debug_script.py --verb raw "ping"                 # 原样发送, 不加 verb 前缀
  python tools/utils/debug_script.py --json out.json "root.getVersionName()"

参数:
  <命令...>         ScriptEngine 表达式；默认按 `script <表达式>` 发送（与历史调用一致）
  --verb V          script(默认) | ping | function | functionNoTimeout | raw
  --wait N          多条命令之间的间隔秒数（默认 0）
  --host H/--port P 目标地址（默认取 rwlib.config.DEBUG_HOST/DEBUG_PORT）
  --timeout S       单条命令最大等待秒数（默认 35；必须大于服务端自身 ≈30s 等待上限，
                    否则长命令会被客户端提前掐断）
  --idle S          无终止符 verb 的「响应已读完」空闲判据（默认 0.4）
  --json FILE       把每条命令的结构化结果（mode/字节数/耗时/诊断）落盘

退出码: 0 = 全部命令都取回响应; 1 = 有命令失败（连接失败/无响应）。
"""
import json
import sys
import time
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import DEBUG_HOST, DEBUG_PORT  # noqa: E402
from rwlib.debugproto import send_command  # noqa: E402

VERBS = ("script", "ping", "function", "functionNoTimeout", "raw")


def build_wire(command: str, verb: str) -> str:
    """按 verb 组装实际发送的行（raw = 原样发送）。"""
    if verb == "raw":
        return command.strip()
    if verb == "ping":
        return "ping"
    return f"{verb} {command}".strip()


def main():
    argv = sys.argv[1:]
    wait = 0.0
    host, port = DEBUG_HOST, DEBUG_PORT
    timeout, idle = 35.0, 0.4
    verb = "script"
    json_path = None
    commands = []
    i = 0
    while i < len(argv):
        a = argv[i]
        if a in ("-h", "--help"):
            print(__doc__)
            return 0
        elif a == "--wait" and i + 1 < len(argv):
            wait = float(argv[i + 1]); i += 2
        elif a == "--host" and i + 1 < len(argv):
            host = argv[i + 1]; i += 2
        elif a == "--port" and i + 1 < len(argv):
            port = int(argv[i + 1]); i += 2
        elif a == "--timeout" and i + 1 < len(argv):
            timeout = float(argv[i + 1]); i += 2
        elif a == "--idle" and i + 1 < len(argv):
            idle = float(argv[i + 1]); i += 2
        elif a == "--verb" and i + 1 < len(argv):
            verb = argv[i + 1]; i += 2
            if verb not in VERBS:
                print(f"错误: --verb 只支持 {', '.join(VERBS)}（收到 {verb!r}）")
                return 1
        elif a == "--json" and i + 1 < len(argv):
            json_path = Path(argv[i + 1]); i += 2
        elif a.startswith("--"):
            print(f"错误: 未知参数 {a!r}（见 --help）")
            return 1
        else:
            commands.append(a); i += 1

    if not commands and verb != "ping":
        print("Usage: python tools/utils/debug_script.py [--wait N] [--host H] [--port P] "
              "[--timeout S] [--verb V] <命令> [命令...]")
        return 1
    if verb == "ping" and not commands:
        commands = ["ping"]  # --verb ping 可无参

    # 兼容历史调用：位置参数可能是带空格的多个词, 逐条处理
    failed = False
    results = []
    for n, cmd in enumerate(commands):
        if n > 0 and wait > 0:
            time.sleep(wait)
        wire = build_wire(cmd, verb)
        r = send_command(wire, host=host, port=port, verb=verb if verb != "raw" else None,
                         max_wait=timeout, idle_timeout=idle)
        results.append({
            "command": cmd, "wire": wire, "verb": r.verb, "mode": r.mode, "ok": r.ok,
            "bytes": len(r.raw), "elapsed_s": round(r.elapsed, 3),
            "text": r.text, "diagnostic": r.detail,
        })
        if r.ok:
            print(f"[{cmd}] -> {r.text}")
        elif r.mode == "connect-error":
            print(f"[{cmd}] -> 连接失败: {r.detail}")
            failed = True
        else:
            print(f"[{cmd}] -> {r.detail}")
            failed = True

    if json_path is not None:
        json_path.write_text(json.dumps(
            {"host": host, "port": port, "verb": verb, "timeout": timeout,
             "results": results}, ensure_ascii=False, indent=2), encoding="utf-8")
    return 1 if failed else 0


if __name__ == "__main__":
    sys.exit(main())
