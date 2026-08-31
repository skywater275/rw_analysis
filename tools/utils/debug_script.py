#!/usr/bin/env python3
"""
debug_script — 游戏调试服务器客户端 (v19.96)

通过 TCP 连接 DebugServer (rts.a.a, -debugscript 启动, 默认端口 5677),
发送 script 命令逐条执行 (ScriptEngine.processScript 语法: root.xxx('...')).

Usage:
  python tools/utils/debug_script.py "root.loadReplay('r5.replay')"
  python tools/utils/debug_script.py --wait 3 "root.hostStart(false)" "mp.multiplayerStart()"
  python tools/utils/debug_script.py --host 127.0.0.1 --port 5677 "debug.currentPid()"
"""
import socket
import sys
import time
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import DEBUG_HOST, DEBUG_PORT  # noqa: E402


def send_one(host, port, command):
    """发送一条 script 命令, 返回服务器响应文本."""
    sock = socket.create_connection((host, port), timeout=15)
    sock.settimeout(30)
    try:
        sock.sendall(("script " + command + "\n").encode("utf-8"))
        # 响应以 \0 结尾 (见 rts.a.a.b 协议)
        buf = b""
        while not buf.endswith(b"\x00"):
            chunk = sock.recv(4096)
            if not chunk:
                break
            buf += chunk
        return buf.decode("utf-8", errors="replace").rstrip("\x00")
    finally:
        sock.close()


def main():
    args = [a for a in sys.argv[1:] if not a.startswith("--")]
    wait = 0
    host = DEBUG_HOST
    port = DEBUG_PORT
    i = 1
    while i < len(sys.argv):
        a = sys.argv[i]
        if a == "--wait" and i + 1 < len(sys.argv):
            wait = float(sys.argv[i + 1])
            i += 2
        elif a == "--host" and i + 1 < len(sys.argv):
            host = sys.argv[i + 1]
            i += 2
        elif a == "--port" and i + 1 < len(sys.argv):
            port = int(sys.argv[i + 1])
            i += 2
        else:
            i += 1
    if not args:
        print("Usage: python tools/utils/debug_script.py [--wait N] [--host H] [--port P] <命令> [命令...]")
        sys.exit(1)

    failed = False
    for n, cmd in enumerate(args):
        if n > 0 and wait > 0:
            time.sleep(wait)
        try:
            resp = send_one(host, port, cmd)
            print(f"[{cmd}] -> {resp}")
        except Exception as e:
            print(f"[{cmd}] -> 连接失败: {e}")
            failed = True
    sys.exit(1 if failed else 0)


if __name__ == "__main__":
    main()
