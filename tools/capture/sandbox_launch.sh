#!/bin/bash
# 沙盒启动工具链 (v19.113c) — attach 注入沙盒开局 agent
# 用法: bash tools/capture/sandbox_launch.sh [地图路径]
# 前置: 游戏已运行 (patch 前置 + -debug 5677:local); JDK21 的 Attacher.class 在游戏根目录
# 机制: agent → ScriptEngine.queuedScripts 投 RunnableAction(任务) → 游戏线程执行 (GL 上下文)
#   任务 = loadConfigAndStartNewSandbox 全序列 (bQ/guiEngine/i.a/bv/bL.E/bS.y/guiEngine.f/closeDoc/clearHistory)
MAP="${1:-maps/skirmish/[p2]Beach landing (2p) [by hxyy].tmx}"
PID=$(netstat -ano | grep "5677.*LISTENING" | head -1 | awk '{print $NF}')
if [ -z "$PID" ]; then echo "游戏未运行 (端口 5677 无监听)"; exit 1; fi
echo "游戏 PID=$PID, 地图=$MAP"
# 编译 agent (--release 8 必选: 游戏 JVM 是 JDK 13, 只认 ≤57 字节码)
JAVAC="C:/JDK/oracleJdk-21/bin/javac.exe"
cd "$(dirname "$0")/agent"
"$JAVAC" --release 8 -encoding utf-8 -nowarn \
  -cp "c:/Users/28210/Downloads/Rusted Warfare/game-lib.jar;C:/tmp/agent_out" \
  -d C:/tmp/agent_out SandboxTask2.java SandboxAgent7.java || exit 1
cd C:/tmp/agent_out
printf "Manifest-Version: 1.0\nAgent-Class: SandboxAgent7\nCan-Redefine-Classes: true\nCan-Retransform-Classes: true\n\n" > MANIFEST7.MF
"C:/JDK/oracleJdk-21/bin/jar.exe" cfm C:/tmp/sandbox-agent9.jar MANIFEST7.MF SandboxAgent7.class SandboxTask2.class
# attach (注意: agent 类名在游戏 JVM 内不可复用 — 迭代必须换类名, 类缓存教训)
"C:/JDK/oracleJdk-21/bin/java.exe" -cp "c:/Users/28210/Downloads/Rusted Warfare;C:/tmp/agent_out" \
  Attacher "$PID" C:/tmp/sandbox-agent9.jar "$MAP"
