#!/usr/bin/env python3
"""rwlib.debugproto — 游戏 Debug Socket (5677) 协议客户端底座 (D-9 修复, 2026-09-21)

**协议真源**（字节码实证见 `docs/07-engine/API-SURFACE.md` §1.3/§1.4；
`com/corrodinggames/rts/a/a.class`，02b `rts/a/a.java` + `rts/a/b.java:33-35`）：

| 命令 | 成功响应 | 失败响应 | 终止符 |
|------|---------|---------|--------|
| `ping` | `pong` | — | **无** |
| `script <expr>` | `done`（恒为 `done`） | 崩溃文本 | **无** |
| `function <expr>` | `ok\\n<值>` | `crash\\n<文本>` / `crash\\nTime Out` | **`\\u0000`** |
| `functionNoTimeout <expr>` | 同 `function` | 同（除超时） | **`\\u0000`** |
| 其它 | — | `unknown command` | **无** |

服务端写回用 `print()`（**非** `println()`）→ 响应**不含换行**；且**只有 `function*` 系列**
带 `\\u0000` 终止符。因此「一律等到 `\\x00` 才收包」的客户端在 `script`/`ping` 上**必然挂到
socket 超时**（D-9① 的缺陷本体；`tools/utils/debug_script.py` 旧实现即如此，实测每次白等 30s）。

另注（REV-01 §3 的字节级事实）：`\\u0000` 在 class 常量池里是 modified-UTF8 的 `C0 80` 两字节；
02b FernFlower 把它渲染成**空格**（CFR 才渲染为 `\\u0000`）。本模块因此**同时接受** `\\x00`
与 `\\xc0\\x80`（后者为「服务端若按 modified-UTF8 直接写常量池字节」的防御性兼容）。

本模块只依赖标准库（socket/时间），不 import rwlib.config，便于独立复用。
"""
import socket
import time
from dataclasses import dataclass, field

DEFAULT_HOST = "127.0.0.1"
DEFAULT_PORT = 5677

# 无终止符（收到首字节后按「空闲间隔」判读完整）
TERMINATOR_NONE = None
# \\u0000 的两种字节形态：标准 UTF-8 单字节 / modified-UTF8 双字节
NUL_UTF8 = b"\x00"
NUL_MODIFIED_UTF8 = b"\xc0\x80"

# verb → 终止符（None = 协议上无终止符）
VERB_TERMINATORS = {
    "ping": TERMINATOR_NONE,
    "script": TERMINATOR_NONE,
    "function": NUL_UTF8,
    "functionnotimeout": NUL_UTF8,
    "raw": TERMINATOR_NONE,
}

# 服务端等待上限：ScriptEngine$Action.waitForCompletionOrCrash = 3000×10ms ≈ 30s
SERVER_WAIT_LIMIT_S = 30.0


@dataclass
class DebugResponse:
    """一次命令往返的结构化结果。

    mode 取值（**判读依据**，调用方应据此而非「有没有异常」判断）：
      - 'terminator'     : 命中协议终止符（function* 的正常路径）
      - 'idle'           : 无终止符 verb，首字节到达后空闲 idle_timeout 仍无新数据 → 响应完整
      - 'closed'         : 服务端关闭连接（响应完整）
      - 'timeout-empty'  : max_wait 内**一个字节都没收到**（真·无响应/命令仍在执行）
      - 'timeout-partial': 收到部分数据但终止符始终未出现（协议不符/被截断）
    """
    verb: str
    command: str
    raw: bytes = b""
    text: str = ""
    mode: str = "timeout-empty"
    elapsed: float = 0.0
    detail: str = ""
    host: str = DEFAULT_HOST
    port: int = DEFAULT_PORT
    terminator: bytes = field(default=TERMINATOR_NONE)

    @property
    def ok(self) -> bool:
        """响应是否完整取回（'closed' 亦视为完整：对端主动关闭即本次响应结束）。"""
        return self.mode in ("terminator", "idle", "closed")

    @property
    def timed_out(self) -> bool:
        return self.mode.startswith("timeout")

    def diagnose(self) -> str:
        """中文诊断（含修复前后差异说明，便于调用方打印/落盘）。"""
        if self.mode == "timeout-empty":
            return (f"服务端在 {self.elapsed:.1f}s 内未返回任何字节（命令已发出，端口连通）。"
                    f"注意：{self.verb} 通道服务端自身等待上限约 "
                    f"{SERVER_WAIT_LIMIT_S:.0f}s，如需更长请调大 --timeout。")
        if self.mode == "timeout-partial":
            return (f"已收到 {len(self.raw)} 字节但未见终止符 "
                    f"{self.terminator!r}（{self.elapsed:.1f}s）→ 协议不符/响应被截断；"
                    f"已收内容: {self.text[:200]!r}")
        if self.mode == "terminator":
            return f"命中终止符 {self.terminator!r}（{self.elapsed:.3f}s）"
        if self.mode == "idle":
            return (f"该 verb 协议上无终止符，按空闲间隔判定响应完整"
                    f"（{self.elapsed:.3f}s, {len(self.raw)} 字节）")
        return f"服务端关闭连接，响应完整（{self.elapsed:.3f}s, {len(self.raw)} 字节）"


def split_verb(command: str):
    """从命令行原文取 verb（第一个空格前的 token，小写）与剩余参数。"""
    s = command.strip()
    if not s:
        return "", ""
    i = s.find(" ")
    if i == -1:
        return s.lower(), ""
    return s[:i].lower(), s[i + 1:]


def terminator_for(verb: str):
    """按 verb 查协议终止符；未知 verb 视为无终止符（`unknown command` 路径）。"""
    return VERB_TERMINATORS.get((verb or "").lower(), TERMINATOR_NONE)


def strip_terminator(raw: bytes, terminator) -> bytes:
    """去掉响应尾部的协议终止符（同时兼容 modified-UTF8 双字节形态）。"""
    if terminator is NUL_UTF8:
        while raw.endswith(NUL_MODIFIED_UTF8):
            raw = raw[:-len(NUL_MODIFIED_UTF8)]
        while raw.endswith(NUL_UTF8):
            raw = raw[:-1]
        return raw
    return raw


def port_listening(host: str = DEFAULT_HOST, port: int = DEFAULT_PORT, timeout: float = 0.3) -> bool:
    """端口是否可连接（用于「已终止」判据中的端口释放轮询；不发送任何命令）。"""
    try:
        with socket.create_connection((host, port), timeout=timeout):
            return True
    except OSError:
        return False


def send_command(command: str, host: str = DEFAULT_HOST, port: int = DEFAULT_PORT,
                 verb: str = None, max_wait: float = 35.0, idle_timeout: float = 0.4,
                 connect_timeout: float = 15.0) -> DebugResponse:
    """向 DebugServer 发送一行命令并按**协议真实字节**收响应。

    参数:
        command       : 命令行原文（如 `script root.getVersionName()`）；verb=None 时自动识别
        verb          : 强制指定 verb（None = 取 command 的首 token）。`script` 客户端应传 'script'
        max_wait      : 总等待上限（秒）。默认 35 —— 必须 > 服务端 30s 等待上限，
                        否则 `script` 的长命令会被客户端提前掐断（旧实现 30s 超时即此问题）
        idle_timeout  : 无终止符 verb 的「响应完整」判据：首字节到达后静默该时长即认为读完
        connect_timeout: TCP 连接超时
    返回:
        DebugResponse（含 mode/diagnose()，调用方据此判定，不要只看异常）
    """
    if verb is None:
        verb, _ = split_verb(command)
    term = terminator_for(verb)
    t0 = time.time()
    resp = DebugResponse(verb=verb, command=command, host=host, port=port, terminator=term)
    sock = None
    try:
        sock = socket.create_connection((host, port), timeout=connect_timeout)
        sock.settimeout(max_wait)
        sock.sendall((command.rstrip("\n") + "\n").encode("utf-8"))

        buf = b""
        deadline = t0 + max_wait
        while True:
            remaining = deadline - time.time()
            if remaining <= 0:
                resp.mode = "timeout-partial" if buf else "timeout-empty"
                break
            # 首字节前用剩余总预算；拿到数据后（无终止符 verb）用空闲间隔判读完
            sock.settimeout(remaining if not buf else min(idle_timeout, remaining))
            try:
                chunk = sock.recv(65536)
            except socket.timeout:
                if buf:
                    if term is None:
                        resp.mode = "idle"      # 协议无终止符 → 空闲即完整
                    else:
                        resp.mode = "timeout-partial"
                else:
                    resp.mode = "timeout-empty"
                break
            if not chunk:
                resp.mode = "closed"            # 对端关闭 → 本次响应结束
                break
            buf += chunk
            if term is not None and (buf.endswith(term) or buf.endswith(NUL_MODIFIED_UTF8)):
                resp.mode = "terminator"
                break
        resp.raw = buf
        resp.text = strip_terminator(buf, term).decode("utf-8", errors="replace")
        resp.elapsed = time.time() - t0
        resp.detail = resp.diagnose()
        return resp
    except OSError as e:
        resp.elapsed = time.time() - t0
        resp.mode = "timeout-empty" if isinstance(e, socket.timeout) else "connect-error"
        resp.detail = f"连接/收发失败: {e}"
        return resp
    finally:
        if sock is not None:
            try:
                sock.close()
            except OSError:
                pass


if __name__ == "__main__":
    # 自检：打印协议表（不连游戏，避免误碰运行时）
    print("rwlib.debugproto — Debug Socket 协议表（verb → 终止符）")
    for v, t in VERB_TERMINATORS.items():
        print(f"  {v:<18} 终止符 = {t!r}")
    print(f"  未知 verb 默认 = {TERMINATOR_NONE!r}（unknown command 路径）")
