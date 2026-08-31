#!/usr/bin/env python3
"""
map_scriptengine — ScriptEngine/ScriptContext 成员映射落库 (v19.105)

T0 证据: ScriptEngine.java 420 行源码全文已读 (v19.96 静态探查),
字段/方法语义直接来自源码结构与运行日志 (ScriptEngine:HandleEvent 等).

Usage: python tools/utils/map_scriptengine.py
"""
import csv
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import CLASS_DISCOVERIES, SUPPLEMENT_CSV

csv.field_size_limit(10 * 1024 * 1024)


def main():
    # 类映射
    rows = list(csv.reader(open(CLASS_DISCOVERIES, encoding="utf-8")))
    existing_cls = {(r[1], r[2]) for r in rows[1:] if r[0] == "class"}
    cls_batch = [
        ["class", "com.corrodinggames.librocket.scripts", "ScriptEngine", "ScriptEngine",
         "v19.105 T0: 脚本队列处理器 + methods反射注册表 + processArg脚本表达式解析器 (420行已全读)"],
        ["class", "com.corrodinggames.librocket.scripts", "ScriptContext", "ScriptContext",
         "v19.105 T0: 脚本上下文基类 (methods HashMap 函数注册表 + libRocket/guiEngine/scriptEngine 引用)"],
    ]
    for r in cls_batch:
        if (r[1], r[2]) not in existing_cls:
            rows.append(r)
    with open(CLASS_DISCOVERIES, "w", encoding="utf-8", newline="") as f:
        csv.writer(f, lineterminator="\n").writerows(rows)

    # 成员映射 (ScriptEngine 420行源码全文已读, T0 直接证据)
    rows = list(csv.reader(open(SUPPLEMENT_CSV, encoding="utf-8")))
    existing = {(r[0], r[1], r[2], r[3]) for r in rows[1:]}
    batch = [
        ("field", "com.corrodinggames.librocket.scripts", "ScriptEngine", "scriptEngine", "instance",
         "v19.105: 静态单例 (createScriptEngine设置, getInstance返回)"),
        ("field", "com.corrodinggames.librocket.scripts", "ScriptEngine", "inDebugScript", "inDebugScript",
         "v19.105: 静态标志, a$2.run() 设置 true/false 包裹 function 命令"),
        ("field", "com.corrodinggames.librocket.scripts", "ScriptEngine", "queuedScripts", "queuedScripts",
         "v19.105: 待执行脚本队列 (synchronized add/remove, update逐帧消费)"),
        ("field", "com.corrodinggames.librocket.scripts", "ScriptEngine", "runningScripts", "runningScripts",
         "v19.105: 本帧运行中脚本 (update 中迭代后 clear)"),
        ("field", "com.corrodinggames.librocket.scripts", "ScriptEngine", "globals", "globals",
         "v19.105: 全局变量表 (setGlobalVariable: root/mp/mods/debug 四上下文)"),
        ("field", "com.corrodinggames.librocket.scripts", "ScriptEngine", "scriptError", "scriptError",
         "v19.105: 延迟异常 (throwDelayedException 首个错误保存)"),
        ("field", "com.corrodinggames.librocket.scripts", "ScriptEngine", "root", "rootContext",
         "v19.105: Root 脚本上下文实例"),
        ("field", "com.corrodinggames.librocket.scripts", "ScriptEngine", "b", "libRocketBridge",
         "v19.105: slickLibRocket (librocket b 桥接)"),
        ("method", "com.corrodinggames.librocket.scripts", "ScriptEngine", "isStrict()", "isStrict",
         "v19.105: return a.a.a() DebugServer是否启用 (严格模式抛异常)"),
        ("method", "com.corrodinggames.librocket.scripts", "ScriptEngine", "checkThreadAccess()", "checkThreadAccess",
         "v19.105: ThreadLocal 主脚本线程断言 (违规打日志)"),
        ("method", "com.corrodinggames.librocket.scripts", "ScriptEngine", "getInstance()", "getInstance",
         "v19.105: 静态单例访问器"),
        ("method", "com.corrodinggames.librocket.scripts", "ScriptEngine",
         "createScriptEngine(com.corrodinggames.librocket.b)", "createScriptEngine",
         "v19.105: 静态工厂 (已存在则抛异常; 构造root+四上下文注册)"),
        ("method", "com.corrodinggames.librocket.scripts", "ScriptEngine",
         "setupScriptContext(com.corrodinggames.librocket.scripts.ScriptContext)", "setupScriptContext",
         "v19.105: 反射注册上下文全部公开方法到 methods HashMap (跳过wait/getClass)"),
        ("method", "com.corrodinggames.librocket.scripts", "ScriptEngine", "update(float)", "update",
         "v19.105: 每帧: 队列消费(framesDelay递减)+runningScripts执行+root.onFrameUpdate"),
        ("method", "com.corrodinggames.librocket.scripts", "ScriptEngine",
         "addScriptToQueue(java.lang.String, boolean)", "addScriptToQueue",
         "v19.105: 脚本入队 (tryToCatchCrash=bl)"),
        ("method", "com.corrodinggames.librocket.scripts", "ScriptEngine",
         "addScriptToQueueIfNotAlreadyQueued(java.lang.String)", "addScriptToQueueIfNotAlreadyQueued",
         "v19.105: 去重入队 (相同脚本已在队列则返回null)"),
        ("method", "com.corrodinggames.librocket.scripts", "ScriptEngine",
         "addRunnableToQueue(java.lang.Runnable)", "addRunnableToQueue",
         "v19.105: Runnable 入队 (RunnableAction 包装)"),
        ("method", "com.corrodinggames.librocket.scripts", "ScriptEngine",
         "processScript(java.lang.String)", "processScript",
         "v19.105: 按;分割逐条processArg; 日志 ScriptEngine:HandleEvent:"),
        ("method", "com.corrodinggames.librocket.scripts", "ScriptEngine",
         "throwDelayedException(java.lang.String, java.lang.Throwable)", "throwDelayedException",
         "v19.105: 延迟异常保存 (首个scriptError)"),
        ("method", "com.corrodinggames.librocket.scripts", "ScriptEngine", "checkForErrors()", "checkForErrors",
         "v19.105: 有 scriptError 则抛出"),
        ("method", "com.corrodinggames.librocket.scripts", "ScriptEngine",
         "processArg(java.lang.String)", "processArg",
         "v19.105: 脚本表达式解析器: 单引号字符串/整数/浮点/赋值/函数调用/布尔/变量查找"),
        ("method", "com.corrodinggames.librocket.scripts", "ScriptEngine",
         "getScriptVariable(java.lang.String, boolean)", "getScriptVariable",
         "v19.105: 变量查找链: document metadata→popup→b→globals"),
        ("method", "com.corrodinggames.librocket.scripts", "ScriptEngine",
         "setLocalVariable(java.lang.String, java.lang.Object)", "setLocalVariable",
         "v19.105: 活动文档 metadata 写入"),
        ("method", "com.corrodinggames.librocket.scripts", "ScriptEngine",
         "setGlobalVariable(java.lang.String, java.lang.Object)", "setGlobalVariable",
         "v19.105: globals HashMap 写入"),
        ("method", "com.corrodinggames.librocket.scripts", "ScriptEngine",
         "processFunction(java.lang.String, java.util.regex.Matcher)", "processFunction",
         "v19.105: 参数逗号分割+逐个processArg→runFunction"),
        ("method", "com.corrodinggames.librocket.scripts", "ScriptEngine",
         "runFunction(java.lang.String, java.lang.Object[])", "runFunction",
         "v19.105: 最多两级ctx.func; methods表查找+反射invoke (参数类型适配)"),
        ("method", "com.corrodinggames.librocket.scripts", "ScriptEngine",
         "match(java.lang.String, java.lang.String)", "match",
         "v19.105: 正则全匹配辅助"),
        ("field", "com.corrodinggames.librocket.scripts", "ScriptContext", "methods", "methods",
         "v19.105: 函数注册表 HashMap<String,Method> (setupScriptContext反射填充)"),
        ("field", "com.corrodinggames.librocket.scripts", "ScriptContext", "libRocket", "libRocket",
         "v19.105: LibRocket 桥引用 (文档操作)"),
        ("field", "com.corrodinggames.librocket.scripts", "ScriptContext", "guiEngine", "guiEngine",
         "v19.105: librocket.a.a() GUI引擎"),
        ("field", "com.corrodinggames.librocket.scripts", "ScriptContext", "scriptEngine", "scriptEngine",
         "v19.105: 归属 ScriptEngine 反向引用"),
    ]
    added = 0
    for r in batch:
        key = (r[0], r[1], r[2], r[3])
        if key in existing:
            continue
        rows.append(list(r) + ["verified"])
        added += 1
    with open(SUPPLEMENT_CSV, "w", encoding="utf-8", newline="") as f:
        csv.writer(f, lineterminator="\n").writerows(rows)
    print(f"ScriptEngine+ScriptContext 落库: +{added}, 总数 {len(rows)-1}")
    sys.exit(0)


if __name__ == "__main__":
    main()
