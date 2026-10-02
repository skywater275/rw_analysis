#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""游戏完全体·测试台 — 中文界面文本（供 .bat 调度器调用）。

编码约定（2026-09-24 统一 UTF-8）：.bat 为 UTF-8(BOM) 并在开头 `chcp 65001`，
同时 `set PYTHONIOENCODING=utf-8`，故本脚本输出的中文确定以 UTF-8 写入控制台。
（历史：曾因 cmd 代码页与 bat 编码不一致出现 U+FFFD 乱码，故一度改为「bat 全 ASCII +
中文交给 Python」；统一 UTF-8 后 bat 自身也可直接使用中文 echo。）

Usage:
    python tools/rig/rig_help.py start     # 启动游戏.bat 的用法
    python tools/rig/rig_help.py tool      # 测试台.bat 的用法
    python tools/rig/rig_help.py banner    # 启动前横幅（当前 jar 形态 + 日志去向），
                                           # 并把控制台标题设为中文（SetConsoleTitleW，Unicode 安全）
"""
import ctypes
import json
import sys
from pathlib import Path

RIG_DIR = Path(__file__).resolve().parent
REPO = RIG_DIR.parent.parent
GAME_ROOT = Path(__import__("os").environ.get("RW_GAME_ROOT") or REPO.parent)

START = """用法: 启动游戏.bat [模式]

  无参数 / console          ★ 带实时日志窗口启动（本窗口即游戏 stdout）
  debug                     实时日志窗口 + 调试端口 5677（供 MCP 桥接）
  verbose                   ★ 日志监管器：分类上色 + 全量归档 + 数据侧栏 + 崩溃判据
                              = run_game.py（调试端口 5677 + -logcolor + -replay_debug
                                + -debugscript verbose-script.txt）
  file                      写文件日志 lastrun-bat.log（控制台仅少量早期输出）
  overlay                   实验：classpath 前置 patch 目录（带实时日志）
  stock / d3-era / current  先切换 jar 形态，再带日志启动
  exe                       用官方启动器（无日志窗口；其 java 子进程不接控制台）
  help                      本帮助

实时日志窗口说明：
  · 游戏以 jvm64\\bin\\java.exe 运行（= 原版 fallback64.bat 的方式），
    它的日志直接打印到本窗口，可实时看到加载/地图/AI/回放/报错
  · 关闭本窗口 = 结束游戏；游戏退出后窗口会保留，方便回看最后几行
  · 需要留档时用 file 模式，或另开 MCP 桥（debug 模式）读 game-mcp.log

日志监管器（verbose 模式的实现，也可单独跑）：
  · `python tools/rig/run_game.py` —— 逐行实时打印并按类别上色（崩溃/错误/对局事件/AI/网络/资源噪声），
    原始行**全量归档**到 {GAME}/logs/run-<时间戳>.log（逐行 flush），`--sidebar N` 每 N 秒插一行实时数据，
    退出时打印摘要并给出崩溃判据（扫 onGameCrash / uncaughtException start / OutOfMemoryError）
  · 实测依据：管道下 Java stdout **仍是行级实时**（391 行，日志时间戳与到达时刻延迟 0.00 s），
    故「边玩边看」与「全量留档」可兼得（游戏自带 -log 会把 stdout 重定向到文件，控制台就没输出了）

实时对局数据（`测试台.bat live`，或监管器的 --sidebar）：
  · `测试台.bat live` 或 `python tools/rig/live_stats.py` —— 经 Debug Socket 的 function
    通道轮询：进程/界面/玩家数/连接数/失步计数/每队是否被消灭·击败·胜利，并做事件差分
  · 加 `--log data.jsonl` 可把每次采样落盘为 JSONL，便于事后分析

其他维护命令：测试台.bat verify / list / switch 形态 / manifest / smoke / live
形态说明：stock=原版 8A550A37 · d3-era=1,850 条目（回放已验）· current=1,777 条目（当前主线）
"""

TOOL = """用法: 测试台.bat [命令]

  verify（默认）   现场校验 35 项判据（只读，退出码 0=PASS）
  list             列出 jar 形态与当前部署
  switch 形态      切换 jar：stock / d3-era / current
  manifest         重新生成 MANIFEST.json
  smoke            真实 GUI 启动冒烟测试（不是回放回归）
  live             实时对局数据面板（需游戏以 -debug 端口运行）
  help             本帮助

启动游戏：启动游戏.bat [console | verbose | debug | file | overlay | stock | d3-era | current | exe]
"""


def set_console_title(text):
    """把控制台标题设为中文（SetConsoleTitleW 走 UTF-16，不受代码页影响）。"""
    try:
        ctypes.windll.kernel32.SetConsoleTitleW(text)
    except Exception:
        pass


def banner():
    man_path = GAME_ROOT / "MANIFEST.json"
    lines = []
    if man_path.exists():
        man = json.loads(man_path.read_text(encoding="utf-8"))
        dep = man.get("deployed", {})
        cur = next((p for p in man.get("profiles", []) if p["name"] == dep.get("profile")), {})
        lines.append("[RIG] 当前部署 jar : %s  %s  条目 %s" %
                     (dep.get("profile"), (dep.get("sha256") or "")[:16], dep.get("entries")))
        if cur.get("note"):
            lines.append("[RIG] 形态说明     : %s" % cur["note"])
        gates = man.get("gates", {})
        if gates:
            jc = gates.get("jar_compare_gate", {})
            lines.append("[RIG] 门禁         : javac %s | jar_compare FAIL %s (REVIEW %s / PASS %s)" %
                         (gates.get("javac_gate", {}).get("verdict"), jc.get("fail"), jc.get("review"), jc.get("pass")))
    else:
        lines.append("[RIG] 未找到 MANIFEST.json —— 请先执行: 测试台.bat manifest")
    lines.append("[RIG] 实时日志窗口 : 本窗口即游戏 stdout（关闭窗口 = 结束游戏）")
    lines.append("[RIG] 文件日志     : 启动游戏.bat file  → lastrun-bat.log")
    lines.append("-" * 72)
    sys.stdout.write("\n".join(lines) + "\n")
    sys.stdout.flush()
    return 0


def main():
    what = (sys.argv[1] if len(sys.argv) > 1 else "tool").lower()
    if what == "banner":
        set_console_title("Rusted Warfare — 游戏实时日志")
        return banner()
    if what == "start":
        set_console_title("Rusted Warfare — 启动/日志")
        sys.stdout.write(START)
        return 0
    sys.stdout.write(TOOL)
    return 0


if __name__ == "__main__":
    sys.exit(main())
