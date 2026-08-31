#!/usr/bin/env python3
"""运行时调试客户端 (v19.113) — 稳定操作链接.

签名级白名单 (script_api.json, 02b Debug/Root 提取) + 心跳 + 返回值桥接 + 参数构造.

Usage:
  python tools/capture/debug_client.py ping                          # 心跳
  python tools/capture/debug_client.py call debug.plainTextDebugSave true   # 签名校验调用
  python tools/capture/debug_client.py call root.getVersionName            # 无参调用 (返回值打印)
  python tools/capture/debug_client.py watch --interval 5 --timeout 600    # 存活监控
"""
import json
import socket
import sys
import time
from pathlib import Path

HOST, PORT = '127.0.0.1', 5677
ROOT = Path(__file__).resolve().parents[2]
API = json.load(open(ROOT / 'tools/capture/script_api.json', encoding='utf-8'))


def connect(timeout=10):
    s = socket.socket()
    s.settimeout(timeout)
    s.connect((HOST, PORT))
    return s


def send_raw(cmd: str, wait=8.0):
    """发命令, 收集响应."""
    s = connect()
    s.sendall(cmd.encode('utf-8') + b'\n')
    buf = b''
    s.settimeout(wait)
    try:
        while True:
            d = s.recv(65536)
            if not d:
                break
            buf += d
    except socket.timeout:
        pass
    s.close()
    return buf.decode('utf-8', 'replace')


def ping():
    return 'pong' in send_raw('ping', wait=3)


def format_arg(t: str, v: str):
    """按参数类型构造脚本字面量."""
    t = t.split('.')[-1]
    if t in ('String',):
        return "'" + v.replace("'", "\\'") + "'"
    if t in ('boolean', 'Boolean'):
        return 'true' if v.lower() in ('true', '1') else 'false'
    return v  # int/float/long 数字


def call(obj_fn: str, args='', bridge=True):
    """签名校验调用: obj.fn(args). bridge=True 时返回值经 x=.. + logDebug(x) 回传日志."""
    if '.' not in obj_fn:
        print('用法: call <obj>.<fn> [参数,逗号分隔]')
        return
    obj, fn = obj_fn.split('.', 1)
    if obj not in API or fn not in API[obj]:
        print(f'[拒绝] {obj}.{fn} 不在签名白名单 (防参数不匹配崩溃)')
        return
    rtype, ptypes = API[obj][fn]
    parts = [a.strip() for a in args.split(',') if a.strip()] if args else []
    if len(parts) != len(ptypes):
        print(f'[拒绝] {obj}.{fn} 需要 {len(ptypes)} 个参数 ({",".join(ptypes)}), 给了 {len(parts)}')
        return
    lit = ','.join(format_arg(t, v) for t, v in zip(ptypes, parts))
    expr = f'{obj}.{fn}({lit})'
    # 桥接仅限 String 返回值 (logDebug(String) — 非 String 会 argument type mismatch 崩游戏, v19.113 教训)
    if bridge and rtype.split('.')[-1] == 'String':
        send_raw(f'script x = {expr}', wait=3)
        send_raw('script root.logDebug(x)', wait=3)
        print(f'[调用] {expr} → String 返回值已桥接到游戏日志')
    else:
        send_raw(f'script {expr}', wait=3)
        print(f'[调用] {expr} (返回值 {rtype} 丢弃, 仅副作用)')


def main():
    cmd = sys.argv[1] if len(sys.argv) > 1 else 'ping'
    if cmd == 'ping':
        print('pong' if ping() else '无响应 (游戏未运行/端口未监听)')
    elif cmd == 'call':
        call(sys.argv[2], sys.argv[3] if len(sys.argv) > 3 else '')
    elif cmd == 'watch':
        interval, timeout = 5, 600
        for i, a in enumerate(sys.argv):
            if a == '--interval' and i + 1 < len(sys.argv):
                interval = int(sys.argv[i + 1])
            if a == '--timeout' and i + 1 < len(sys.argv):
                timeout = int(sys.argv[i + 1])
        end = time.time() + timeout
        last_ok = True
        while time.time() < end:
            ok = ping()
            if ok != last_ok:
                print(f'[{time.strftime("%H:%M:%S")}] 游戏{"存活" if ok else "失去响应!"}')
                last_ok = ok
            time.sleep(interval)
    else:
        print(__doc__)


if __name__ == '__main__':
    sys.exit(main())
