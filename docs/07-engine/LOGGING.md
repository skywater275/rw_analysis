# 游戏日志体系 — 开关参数 / 日志 API 层 / 输出点七类 / 格式 / MCP 接口 / 文件位置

> v19.133f98 | 2026-09-21 | Rusted Warfare v1.15 桌面 Java 版日志通道全盘点（含 MCP 侧 12 类结构化事件定义）
> ⚠️ 命名时点: 本文类名以 **03 解混淆名** 为准；括号内给出 02/02b 混淆名与 jar 原始类名。
>   对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)。
> ⚠️ 证据口径: 结论以 **`RustedWarfare/game-lib.jar` 字节码（javap / 常量池）** 与 **实测日志原文件** 为准；
>   `03-deobfuscated/` 源码仅作可读索引，凡与字节码冲突处以字节码为准并显式标注。
> ⚠️ 引文体例: 文中代码块为**节选**并做过**缩进/换行重排**（去掉反编译残留的 `var` 噪声、合并单行 `if`），
>   **权威定位一律以标注的 `文件:行号` 与 javap 输出为准**。
> 配套: [DEBUG-FEATURES.md](DEBUG-FEATURES.md)（调试键位/Debug API/触发通道） · [API-SURFACE.md](API-SURFACE.md)（五类接口索引）

---

## 0. 定位与总览

本域回答一个问题：**「游戏把什么、写到哪里、以什么格式」**。

### 0.1 三条输出通道（互不等价，务必区分）

```
                       ┌──────────────────────────────────────────────┐
 游戏内 e()/b()/a() ──▶ │ GlobalState.c(String)  ← 唯一汇聚点 (sink)   │
                       └───────────────┬──────────────────────────────┘
                                       ▼
                       android.util.Log.b("RustedWarfare", msg)   ← 桌面 shim
                                       ▼
                       Log.a(0, tag, msg) → System.out.println( 时间戳 + ": " + msg )
                                       │
        ┌──────────────────────────────┼───────────────────────────────┐
        ▼                              ▼                               ▼
 ① stdout（控制台/javaw 无控制台）  ② `-log <file>` 重定向的 PrintStream  ③ MCP 启动时的 OS 级 stdout 重定向
```

| 通道 | 载体 | 生效条件 | 是否含时间戳 |
|------|------|---------|-------------|
| ① 标准输出 | `System.out` | 恒有（`javaw` 下无控制台 = 丢弃） | 是 |
| ② 日志文件 | `-log <file>` 指定的 `PrintStream` | **必须显式传 `-log`** | 是 |
| ③ MCP 捕获文件 | `{GAME}/game-mcp.log` | 仅经 `mcp/mcp_game_server.py --launch-game` 启动 | 是 |

> **关键结论**：桌面版**不会自动写日志文件**。`{GAME}/lastrun*.log` 全部来自调用方显式传入 `-log <名>.log`。
> 证据：`game-lib.jar` 全类常量池内不存在 `lastrun` 字符串（复现命令见 §9-1），而 `-log` 的处理见 §1.1。

### 0.2 与调试 socket 的关系（重要否证）

**调试 socket（TCP 127.0.0.1:5677）不是日志通道。** 它只回显「一条命令 → 一条响应」，不做任何日志推送：

- `com/corrodinggames/rts/a/a.class`（03: `rts/platform/a.java`）`run()` 只 `ServerSocket.accept()` 并发线程；
- 其 `DebugSession`（02b `rts/a/b.java`）循环只有 `readLine() → b(line) → printWriter.print(响应); flush();`，**无异步写回**。

→ 因此 MCP 的 `game_read_log` 读到的是 **MCP 自己累积的「历史命令响应」环形缓冲**，不是游戏日志（详见 §5.3）。

---

## 1. 日志开关与启动参数

全部日志相关 flag 的**唯一处理点**是启动类 `com.corrodinggames.rts.java.Main`（03: `java/GameLauncher.java`），
在 `a(String[])`（03 L152–511）的单次遍历中解析。

### 1.1 `-log <file>` — 唯一能落地日志文件的开关

```java
// 03-deobfuscated/.../java/GameLauncher.java:216-233
if (((String)object).equals("-log")) {
    if (++n2 >= stringArray.length) { GameLauncher.a("-log requires parameters"); System.exit(1); }
    string2 = stringArray[n2];
    try {
        PrintStream printStream = new PrintStream(string2);   // ← 默认平台字符集, 截断语义
        System.setOut(printStream);
        System.setErr(printStream);
        com.corrodinggames.rts.gameFramework.GlobalState.e("File logging started");
    } catch (FileNotFoundException fileNotFoundException) {
        com.corrodinggames.rts.gameFramework.GlobalState.a("Cannot open log file:" + string2);
        fileNotFoundException.printStackTrace();
    }
    continue;
}
```

| 项 | 结论 | 证据 |
|----|------|------|
| 参数形态 | `-log <路径>`，缺参即 `System.exit(1)` | GameLauncher.java:216-220 |
| 成功标志 | 日志首行 `File logging started` | GameLauncher.java:226；实测 `{GAME}/lastrun.log:1` |
| 失败标志 | `--- ERROR: Cannot open log file:<路径>` + 栈 | GameLauncher.java:229；`GlobalState.a(String)` 加 `--- ERROR: ` 前缀（§2） |
| 覆盖语义 | `new PrintStream(String)` **截断**已存在文件 → 每次运行覆盖，**无轮转、无时间戳命名** | Java API 语义 + `{GAME}/lastrun.log` 单次运行内容 |
| 作用范围 | 同时替换 `System.out` **与** `System.err` | GameLauncher.java:224-225 |
| 字符集 | `new PrintStream(String)` 用 `Charset.defaultCharset()`；`jvm64` = **OpenJDK 13** → 中文 Windows 默认 GBK，须配 `-Dfile.encoding=UTF-8`（§4.4） | `jvm64/bin/java.exe -version` = `openjdk version "13" 2019-09-17` |

02b 交叉验证（同一逻辑，混淆名 `com.corrodinggames.rts.java.Main`）：`02b-decompiled/.../java/Main.java:201-206`，
常量池证据：`'File logging started'` / `'Cannot open log file:'` 仅出现在 `com/corrodinggames/rts/java/Main.class`。

### 1.2 `-nologfile` — **空实现**（不是「关闭日志文件」）

```java
// 03-deobfuscated/.../java/GameLauncher.java:234
if (((String)object).equals("-nologfile")) continue;
```

- 语义：**仅被识别，不做任何动作**。它的唯一作用是让该 token 不落入末尾的
  `GameLauncher.a("Unknown option: " + object)` 分支（GameLauncher.java:372-373）。
- 为什么「关不掉」：桌面启动类**本来就不写文件**（§1.1），无默认日志文件可关。
- 字节码证据：`-nologfile` 是 `com/corrodinggames/rts/java/Main.class` 的常量池成员，且该 switch 链里紧跟它的
  下一条是 `-lang`（GameLauncher.java:235）。
- **待验证**：Android/移动端是否存在真正的日志文件通道（本文只覆盖桌面 Java 启动路径）。

### 1.3 `-logcolor` — 打开 ANSI 颜色包装

```java
// 03-deobfuscated/.../java/GameLauncher.java:243-246
if (((String)object).equals("-logcolor")) {
    com.corrodinggames.rts.gameFramework.GlobalState.ax = true;   // 02b l.ax = colorLogOutput
    continue;
}
```

`GlobalState.ax`（映射名 `colorLogOutput`，见 `mappings/domains/12-utility.csv:537`）只在颜色包装器里被读：

```java
// 03-deobfuscated/.../GlobalState.java:634-639
public static String a(String string, String string2) {
    if (ax && !string.contains("\u001b[0m")) {
        string = string2 + string + "\u001b[0m";   // 前缀色 + 尾重置
    }
    return string;
}
```

只用色两处：`a(String)` → `"\u001b[31m"`（红，ERROR）；`b(String)` → `"\u001b[33m"`（黄，WARN）。**INFO 通道 `e()` 永不着色**。

> 实测提醒：本机既有日志（`{GAME}/game-mcp.log`）中 **不存在 ESC 字节**（`0x1b` 未出现），说明这些运行均未传 `-logcolor`。
> 复现：`python -c "print(b'\x1b' in open('game-mcp.log','rb').read())"` → `False`。

### 1.4 改变日志**体量**的相邻 flag（同一解析循环内）

| flag | 03 行 | 落点字段 | 对日志的影响 |
|------|-------|---------|-------------|
| `-debug <port>:<host>` | 196-206 | `DebugServer.a(int,String)`（真实类 `com/corrodinggames/rts/a/a`） | 开 5677 调试端口；**并使 `debug.*` 脚本对象被注册**（§5.1 / API-SURFACE §2.2），后续脚本调用会打 `ScriptEngine:HandleEvent:` |
| `-debugscript <file>` | 207-215 | `DebugServer.a(String)` | 把脚本文件加入队列，逐行执行并打 `Running debug script:` / `Running: ` / `got: `（02b `rts/a/a$1.java:14-34`） |
| `-devdebug <val>` | 331-338 | `GlobalState.aQ` | 开发者调试开关（注：缺参报错文案误写为 `-debugscript requires parameters`） |
| `-replay_debug` | 255-258 | `GlobalState.aw` | 回放开调试日志（配合 `Replay: ` 前缀族） |
| `-printunits` | 311-314 | `GlobalState.aE` | 单位打印 |
| `-outputunitimages` | 315-318 | `GlobalState.aF` | 单位图像输出 |
| `-nodisplay` | 247-250 | 局部 `bl` | headless；屏幕设为 10×10（GameLauncher.java:476-479），日志出现 `TargetDisplayMode: 10 x 10 x 0 @0Hz` |
| `-width/-height` | 359-362 / 182-191 | 局部 `n3/n4` | 触发 `Overriding width to:` / `Overriding height to:`（GameLauncher.java:481-486） |

> `-debug` 的 `port:host` 解析：`string2.split(":")[0]` = 端口，`[1]` = 主机串，直接 `Integer.parseInt`（GameLauncher.java:202-203）→
> `-debug 5677`（无冒号）会 **ArrayIndexOutOfBoundsException**；MCP 与 QAV 一律写 `-debug 5677:local`。

### 1.5 完整 flag 清单（36 个 token）

复现提取命令见 §9-2。带值 5 个（`-debug` / `-debugscript` / `-log` / `-lang` / `-devdebug`）+ 两段式 3 个
（`-width` / `-height` / `+connect_lobby`）+ 其余 28 个布尔开关。与日志无直接关系的 28 个布尔开关的完整语义清单
见 [API-SURFACE.md](API-SURFACE.md) §4（命令行参数全表）。

---

## 2. 日志 API 层（`GlobalState` 语义与调用链）

### 2.1 方法语义总表

`GlobalState`（02/02b: `com.corrodinggames.rts.gameFramework.l`）是**唯一的日志 API 层**。

| 方法 | 03 行 | 语义 | 前缀/颜色 | 出口 |
|------|-------|------|----------|------|
| `e(String)` | 673-675 | **INFO / 通用**（使用量最大） | 无 | `c()` |
| `c(String)` | 661-667 | **汇聚点 sink** | 无 | `android.util.Log.b("RustedWarfare", msg)` |
| `d(String)` | 669-671 | `c()` 别名 | 无 | `c()` |
| `b(String)` | 645-647 | **WARN** | `\u001b[33m` 黄 + `\u001b[0m`（仅当 `ax`） | `e()` |
| `a(String)` | 641-643 | **ERROR** | `--- ERROR: ` + `\u001b[31m` 红（仅当 `ax`） | `e()` |
| `g(String)` | 649-652 | **WARN + 全栈** | 同 `b()`，后接 `T()` | `b()` + `T()` |
| `a(String,Exception)` | 629-632 | INFO + `printStackTrace()` | 无 | `e()` + 栈 |
| `a(String,Throwable)` | 654-659 | WARN + `toString` + `cause:` + 栈 | 黄 | `b()`/`e()`/栈 |
| `b(String,String)` | 677-679 | **`tag:message` 复合行** | 无 | `c(tag + ":" + msg)` |
| `f(String)` | 681-683 | 带 `System.nanoTime()` 的计时行 | 无 | `c(msg + " (at " + nanoTime + ")")` |
| `T()` | 685-690 | 打印当前栈每帧（各成一行） | 无 | `e()` × N |
| `U()` | 692-699 | 返回栈字符串（不打印） | — | return |
| `e(String,String)` | 1221-1235 | **写崩溃文件（不是日志）** | — | `crashes.txt`（§6.3） |
| `a(String,String)` | 634-639 | 颜色包装器（被 `a`/`b` 调用） | 传参 | return |
| `a(ResourceDomainEnum,Throwable)` | 1406-1417 | OOM 上报 | — | `e()`/`c(Throwable)` |
| `c(Throwable)` | 1419-1426 | `printStackTrace()` 包装 | 无 | stderr |
| `logAndNetworkSend(String)` | 1505-1510 | `GlobalState.b(msg)` + `T()` | 黄 | `b()`/`T()` |

**没有任何方法产生 `INFO:` / `WARN:` / `ERROR:` 之类的级别文本前缀**（唯一例外是 `--- ERROR: ` 字面串）。
所谓「级别」只体现在 ①是否着色 ②是否有 `--- ERROR: ` ③是否跟随栈。

### 2.2 分支等价的「日志开关」`GlobalState.aX`（bytecode 实证的怪点）

```java
// 03-deobfuscated/.../GlobalState.java:661-667
public static void c(String string) {
    if (aX) { Log.b("RustedWarfare", string); return; }
    Log.b("RustedWarfare", string);
}
```

`javap -c` 证实这是**字节码层面的两个等价分支**（原版即如此，非解混淆损伤）：

```
  0: getstatic  #218  // Field aX:Z
  3: ifeq       14
  6: ldc        #47   // String RustedWarfare
  9: invokestatic #377 // Method android/util/Log.b:(Ljava/lang/String;Ljava/lang/String;)I
 13: return
 14: ldc        #47   // String RustedWarfare
 17: invokestatic #377 // Method android/util/Log.b:...
 21: return
```

`aX` 由启动流程置真（`GameLauncher.h()`：03 L525 / L529），因此 **`-canvasgl` 等分支对本日志行为无影响**。
推测为 Android 端遗留（原始两分支写向不同目标，桌面移植后同化），本条按「原版固有无意义分支」记录。

### 2.3 出口：`android.util.Log` 是**桌面 shim**（时间戳的产生地）

游戏**未打包 Android 运行时的 `android.util.Log`**，而是自带同名 shim 类：

```java
// 02-decompiled/android/util/Log.java:17-27, 60-69
public static int a(String tag, String msg) { return Log.a(0, 2, tag, msg); }   // VERBOSE
public static int b(String tag, String msg) { return Log.a(0, 3, tag, msg); }   // DEBUG ← GlobalState 走这条
public static int c(String tag, String msg) { return Log.a(0, 5, tag, msg); }   // WARN
public static int d(String tag, String msg) { return Log.a(0, 6, tag, msg); }   // ERROR

public static int a(int n, int n2, String tag, String msg) { Log.a(n, tag, msg); return 0; }

public static int a(int n, String tag, String msg) {
    String string3 = ((SimpleDateFormat) a.get()).format(new Date());
    System.out.println(string3 + ": " + msg);     // ← 唯一的实际输出语句
    return 0;
}
```

```java
// 02-decompiled/android/util/Log$1.java:13-15  (ThreadLocal 每线程一个 SimpleDateFormat)
protected SimpleDateFormat a() { return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); }
```

由此得到两条**硬结论**：

1. **`tag` 被丢弃**——输出里没有 `RustedWarfare` 字样；行内容只剩时间戳 + 消息本体。
2. **优先级参数被丢弃**（`n2` 未使用）——`Log.b`/`Log.c`/`Log.d` 输出完全同形，无法据输出区分级别。

### 2.4 上层便捷包装（跨域日志点）

| 包装 | 03 行 | 展开 | 实测样例 |
|------|-------|------|---------|
| `ReplayEngine.a(String)` | 83-85 | `GlobalState.e("Replay: " + s)` | `Replay: Loading replay initial save` |
| `ReplayEngine.b(String)` | 87-89 | `GlobalState.b("Replay: " + s)` | `Replay: stoppedFrame<lastCommandFrame: ...` |
| `ReplayEngine.a(String,Exception)` | 91-93 | `GlobalState.a("Replay: " + s, t)` | （异常路径） |
| `AIStrategy.d(String)` | 532-534 | `GlobalState.b("ai_debug(" + this.k + ")", s)` | `ai_debug(5):firstRun: no command center found` |
| `Root.logDebug(String)`（03 `MainUIController`） | 73-75 | `GlobalState.e("ui[debug]: " + s)` | 脚本 `root.logDebug(x)` 的落点 |
| `Root.logWarn(String)` | 77-79 | **`GlobalState.e("ui[warn]: " + s)`**（注意：走 INFO，不黄） | `ui[warn]: Could not find team:3` |
| `ScriptEngine.logError(String)` | 03 `ScriptEngine.java`(02b L462-464) | `GlobalState.e("ScriptEngine - error: " + s)` | `ScriptEngine - error: method: x already exists` |
| `ScriptEngine.logCritical(String)` | 02b L466-471 | `GlobalState.e("ScriptEngine - critical: " + s)`；**`isStrict()` 时抛异常** | `ScriptEngine - critical: Could not find function: xxx` |

> `ScriptEngine.isStrict()` 的实现是 `return com.corrodinggames.rts.a.a.a();`（02b `ScriptEngine.java:39-41`），
> 即**「是否由 `-debug`/`-debugscript` 置位」**——所以 `-debug` 下脚本错误会**从日志升级为崩溃**。这是「脚本调用崩游戏」的第一根因（详见 API-SURFACE §2.4）。

---

## 3. 日志输出点分类（七类）

样例一律取自实测日志原文件（`{GAME}/`），标注 `文件:行号`；来源标注 `类.java:行号`。

### 3.1 引擎启动 / 资源加载

| 样例（实测原文，去掉时间戳后） | 来源 |
|------------------------------|------|
| `Reading args` | `GameLauncher.java:170`（`GlobalState.e`） |
| `Game arguments:` / `arg: -width` / `arg: 1024` | `GameLauncher.java:375-379`（`a(String)`→`e()`） |
| `OS name: Windows 10` / `Build Number: #28` / `Game Version: 1.15` / `Game Code: 176` / `Is 64bit: true` | `GameLauncher.java:391-398` |
| `JVM maxMemory:1048576000` / `JVM totalMemory:262144000` | `GameLauncher.java:399-401` |
| `--Now loading:NetworkEngine`（另有 `ReplayEngine`/`GameSaver`/`getAllUnitsChecksum`） | `DesktopGameContainer.java:564` |
| `----- Game init finished in:... ms` | `GameLauncher.java:595` |
| `Saving settings to: <绝对路径>` | `SettingsEngine.java:251` |
| `PathEngine:We have 20 cores, creating extra solvers` | `b("PathEngine", ...)` 族 |

### 3.2 网络

| 样例 | 来源 |
|------|------|
| `Connect to server: <ip:port> (force tcp:false)` | `NetEngine.java:3240` |
| `===============================` / `Connect to: <addr>` / `connecting to Server.. (udp)` | `NetEngine.java:3364-3371` |
| `network debug: <msg>` | `NetEngine.java:1152` |
| `extraDebug:<msg>` | `NetEngine.java:2490` |
| `Adding quick resync command` / `Loading quick resync save data (bytes:16423)` | `NetEngine.java:900` / `935` |
| `--- Desync for frame: <N> ---` | `NetEngine.java:1709` |
| `<x> Checksum [<N>]. server:117967264 client:117974928` | `NetEngine.java:1762`（`GlobalState.b`） |
| `desync:<原因>` + **紧随整段栈帧** | `NetEngine.java:1168-1173`（`registerRelayServer`）——`AITask.g()` 的触发器错误也走这里 |
| `processSystemPacket:New player: <名>, networkVersion:... existing:false` | `NetEngine.java:2415`（`b(tag,msg)`） |
| RUDP 开关 | `network/reliableudp/ReliableSocket.java:81`：`Boolean.getBoolean("net.rudp.debug")`（JVM 系统属性，非启动 flag） |

实测样例（`{GAME}/lastrun-replay-new.log`）：`434` `updateGameFrame: replay: Skipping wait on first resync...`、
`926` `Loading quick resync save data (bytes:16423)`、`1119` `replay:updateGameFrame:message: SERVER:Player: <名> Disconnection control has been shared`。

### 3.3 AI

| 样例 | 来源 |
|------|------|
| `ai_debug(5):firstRun: no command center found` | `AIStrategy.java:974` → `d()` → `533` |
| `ai_debug(N):firstRunDelayed: Found damagingBorder` | `AIStrategy.java:961` |
| `ai_debug(N):pathPossible: no isolatedGroups found! (<s>)` | `AIStrategy.java:214` |
| `ai_debug(N):baseOverlapCount:<N>` | `AIStrategy.java:1463` |
| `ai_debug(N):Skipping add of component: <name>` | `AIStrategy.java:1921` |

格式固定为 **`ai_debug(<AI 序号>):<阶段>: <细节>`**；`<AI 序号>` = `AIStrategy.k`。
实测：`{GAME}/lastrun.log:323-335`（`ai_debug(3)..ai_debug(9)` 各一条 `firstRun: no command center found`）。

### 3.4 触发器 / 地图宾语层（AITask / AIWaveSystem）

| 样例 | 来源 |
|------|------|
| `Found 6 map triggers` | `aicore/AIWaveSystem.java:484` |
| `MissionEngine:triggerLog:firstActivation: move at:2083 for teamId:1 to targetId:6 (#units:5)` | `aicore/AIWaveSystem.java:173`（`GlobalState.b("MissionEngine:triggerLog", s)`） |
| `MapTrigger-Debug (<taskName> type:<事件>): <msg>` | `aicore/AITask.java:219-221`（`GlobalState.e`，**唯一直接落日志的 AITask 触发器入口**） |
| `desync:MapTrigger-Error (<taskId> id:<taskName> type:<事件>): <msg>` + 栈 | `aicore/AITask.java:215-217` → `NetEngine.registerRelayServer` → `NetEngine.java:1168-1173` |
| `linkedTo target not found: <id>`（+ `Possible IDs:` 全量 dump） | `aicore/AIWaveSystem.java:443` |
| `Key was not used: <键>` | `aicore/AIWaveSystem.java:487` |

> ⚠️ **对 [DEBUG-FEATURES.md](DEBUG-FEATURES.md) §11 的订正**：该节列 `"MapTrigger-Error (id type): msg" (AITask.g)`。
> 实测 `AITask.g(String)`（03 L215-217）**不直接调 `GlobalState`**，而是走 `NetEngine.registerRelayServer(String,boolean)`，
> 最终以 `desync:` 前缀 + 整段栈输出；真正直接落日志的是 `AITask.h(String)`（`MapTrigger-Debug`）。
> 另 `AITask.a(String,Exception)`（L202-213）只**构造** `MapException` 文本，不打印。

### 3.5 回放

| 样例 | 来源 |
|------|------|
| `Replay: Loading save from version: 96` | `ReplayEngine.java:414`（`a()`） |
| `Replay: Loading replay initial save` | `ReplayEngine.java:438` |
| `Loading mods from replay` | `GameSaver.java:432` |
| `replay:updateGameFrame:message: <b>:<c>` | `ReplayEngine.java:857`（`b("replay:updateGameFrame", ...)`） |
| `Replay: checksum: checksums are matching frameNumber:0` | 回放校验和族（`Replay:` 前缀） |
| `Replay: extraChecksum: Unit Hp Checksum [0]. 41700 == 41700 (ok)` | 回放逐项校验和族（11 项，见内部 api-log-inventory 交接单与 QAV 判据签名卡） |
| `Replay:addCommand skipped due to stopped recording` | `ReplayWriter.java:38` / `BackgroundWriter.java:32` |

> 回放日志是**最规整、最适合作为判据**的一族：`Replay: ` 前缀 + 固定项数校验和。QAV 的「回放判据签名」即建立其上。

### 3.6 存档

| 样例（**日志**行） | 来源 |
|--------------------|------|
| `GameSaver:saveGame took:1234` | `GameSaver.java:302`（`b("GameSaver", ...)`，大写 S） |
| `gameSaver:Loading save from version: 96` | `GameSaver.java:415`（`b("gameSaver", ...)`，**小写 s** —— 同一域两种 tag） |
| `Finished writing save, renaming to final filename` | `GameSaver.java:126` |
| `renameFile: <src> to:<dst>` | `filesystem/StorageBackend.java:544` |
| `Failed to rename to final file` / `Auto save failed: ...` | `GameSaver.java:129-130` / `137` |
| `Autosaved (6.173ms)` | `GameSaver.java:968` |
| `Loading quick resync save data (bytes:N)` | `NetEngine.java:935`（也是「存档」语义） |

> ⚠️ **`Saving unit:<类型> (id<N>)` 不是日志行**，它是**纯文本存档内的标记**：
> `GameSaver.java:289` 写入的是 `ByteArrayPacketBuilder` 风格的存档流，而非 `GlobalState`。
> 实测佐证：全量 `{GAME}/lastrun*.log` + `game-mcp.log` 中 `Saving unit:` **0 命中**；而 `saves/*.rwsave` 中大量出现。
> 同族存档标记（`#int:` / `#writeFloat` / `#unitType:` / `#team:` / `Section: unit shells` / `saveCompression` / `<SAVE END>`）
> 见 `network/ByteArrayPacketBuilder.java:113-337`。**MCP 的 `game_unit_dump` 读的是存档，不是日志**（§5.3）。

### 3.7 调试

| 样例 | 来源 |
|------|------|
| `----- Debug Active ----`（前后各 2 行 `-----`） | `commands/DebugServer.java:32-36`（原始类 `com/corrodinggames/rts/gameFramework/c/a`） |
| `DebugSocketConnection: waiting for ScriptEngine to start....` / `started` | `rts/platform/a.java:102` / `105`（原始类 `com/corrodinggames/rts/a/a`；`GlobalState.b` 黄） |
| `Got IOException on debugSocket connection` | `rts/platform/a.java:78` |
| `ScriptEngine:HandleEvent:<表达式>` | `librocket/scripts/ScriptEngine.java`（02b L200；`System.out.println` 直写，**无时间戳**） |
| `Running debug script:<文件>` / `Running: <行>` / `got: <响应>` | 02b `rts/a/a$1.java:14-34`（`-debugscript`） |
| `----------- onGameCrash ----------` / `onGameCrash: Not showing popup message due to active debugSocket` / `onGameCrash: end` | `java/DesktopPlatform.java:63 / 69 / 83` |
| `new DebugDesyncDetector` / `new DebugDesyncDetector (stress test)` / `queue quick resync` | `gameFramework/Command.java:492 / 496 / 522` |
| `Replay:addCommand skipped due to stopped recording` | 见 §3.5 |

---

## 4. 日志格式与解析

### 4.1 行格式（通道 ①②③ 的「游戏自有行」）

```
2026-09-01 22:45:08.116: File logging started
└──────────┬──────────┘ └┬┘└───────┬───────┘
   时间戳 (19 字符)       ": "     消息本体 (tag 已被丢弃)
```

正则：`^(?<ts>\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}\.\d{3}): (?<msg>.*)$`

`SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")`（`android/util/Log$1.java:14`）——**本地时区、24 小时制、毫秒 3 位**；
**日志内无年份歧义的 ISO 格式、无级别字段、无线程名、无进程号**。

### 4.2 「级别前缀」的真相（重要）

任务书提到的 `INFO:` 前缀**不属于游戏日志体系**。它来自 **JUL（`java.util.logging`）默认格式**，由 Slick2D/LWJGL 直接写入同一个 `System.out`：

```
Mon Sep 21 20:38:18 CST 2026 INFO:Slick Build #84
Mon Sep 21 20:38:18 CST 2026 INFO:LWJGL Version: 2.9.3
Mon Sep 21 20:38:20 CST 2026 DEBUG:Creating FBO 1024x1024
```

- 格式：`EEE MMM dd HH:mm:ss zzz yyyy <LEVEL>:`，`<LEVEL>` ∈ {`INFO`, `DEBUG`, `WARNING`, `SEVERE`}（JUL 默认 `SimpleFormatter` 的 `%4$s: %5$s%6$s%n` 变体，无类名/方法名）。
- 实测出处：`{GAME}/lastrun-replay-new.log` 非时间戳行共 69 行，其中 JUL 行 44 行（`INFO:` 22 + `DEBUG:` 22）。

**因此解析「级别」时必须先分流**：带 `yyyy-MM-dd` 时间戳 = 游戏自有行（无级别）；
带 `EEE MMM` = 第三方 JUL 行（有级别，但与游戏逻辑无关）。

### 4.3 无前缀的「裸直写」行（第三类）

`System.out.println` 直接被第三方/原生代码调用，**既无时间戳也无级别**：

| 样例 | 来源 |
|------|------|
| `corroding: nativePollOnce:100,0` | 原生库直写 |
| `In Java with: Hello from C++` | 原生库直写 |
| `getNewTextureHolder: append:1`；`getActiveDocumentCss: assets/gui/common.rcss` | 渲染/LibRocket 直写 |
| `ScriptEngine:HandleEvent:<表达式>` | `ScriptEngine.processScript`（02b L200）直写 |
| `fogMode: 2` / `revealedMap: true` / `aiDifficulty: 1` / `randomSeed: 53576196` | 回放头部参数 dump 直写 |
| `processArg: setting: x=...` / `processArg: no variable:x` / `SlickLibRocket:HandleEvent: failed to match:x` | `ScriptEngine.processArg` 直写（调试脚本时大量出现） |

→ **建议解析规则**：只把 `^\d{4}-\d{2}-\d{2} ` 开头的行当作游戏语义日志；其余归「噪声/第三方」。

### 4.4 CJK 编码注意（**必读**）

| 环节 | 事实 | 影响 |
|------|------|------|
| JVM 版本 | `jvm64` = **OpenJDK 13**（`jvm64/bin/java.exe -version`） | `Charset.defaultCharset()` = **平台 ANSI**（中文 Windows = GBK/CP936），**不会**默认 UTF-8（Java 18+ 才是 UTF-8） |
| `-log` 写文件 | `new PrintStream(String)` → 用默认字符集 | 未加 `-Dfile.encoding=UTF-8` 时，日志中的中文（`replay:updateGameFrame:message: SERVER:Player: 银河_用户名 ...`）按 GBK 落盘 |
| 读取方 | MCP `game_read_log_file` / `game_log_stream` 固定 `encoding="utf-8", errors="replace"`（`mcp_game_server.py:406` / `484`） | 若写出端为 GBK → **必然 mojibake**（`replace` 不报错，静默替换为 `\ufffd`） |
| 既有实测文件 | 全部 `lastrun*.log` 的启动命令都带了 `-Dfile.encoding=UTF-8`（QAV 交接单一致口径），故可 UTF-8 正常读 | 实测 `lastrun-replay-new.log:1119` 中文正常 |
| MCP 自身启动 | `_launch_game` 参数为 `[java, -cp, game-lib.jar;libs\*, Main, -nodisplay/-(无), -debug, port:local]`（`mcp_game_server.py:163-166`）——**既无 `-log` 也无 `-Dfile.encoding=UTF-8`** | MCP 启动路径的中文日志**编码无保证**；`game-mcp.log` 实测恰好无中文（ASCII only），未暴露问题 |

**建议（后续工作项）**：MCP `_launch_game` 补 `-Dfile.encoding=UTF-8`；或在读取侧按 BOM/探测决定编码。

### 4.5 空行 / 空消息

`NetEngine.java:3363` 会执行 `GlobalState.e("")` → 产生一行只有时间戳的日志（`2026-...: `）。
解析器应对「消息为空」的行单独归类，勿当作格式异常。

---

## 5. MCP 侧日志接口

来源：`mcp/mcp_game_server.py`（1,254 行）。三个接口语义**完全不同**，务必区分。

### 5.1 `game_log_stream` — 增量文件流 + 结构化事件

实现位置 `mcp_game_server.py:416-502`：

```python
_LOG_POS = 0                       # 模块级文件游标 (跨次调用保持)
text = Path(LOG_FILE).read_text(encoding="utf-8", errors="replace")
if total < _LOG_POS: _LOG_POS = 0  # 文件被截断/重启覆盖 → 重置基线
chunk = text[_LOG_POS:]; _LOG_POS = total
```

- **增量语义**：只返回上次读取后的新字节；首次调用返回「最近 `lines` 行」建立基线（`baseline` 字段）。
- **数据源**：`LOG_FILE = GAME_ROOT / "game-mcp.log"`（`mcp_game_server.py:36`）——**文件**，不是 socket。
- **输入过滤**：`grep` 为**子串**过滤（非正则）。
- **返回**：`new`（原始行，尾部最多 300 行）+ `events`（结构化，尾部最多 100 条）+ `baseline`。

#### 🔴 「结构化事件 9 类」定义（逐类列明）

`_EVENT_PATTERNS`（`mcp_game_server.py:422-455`）实际定义 **15 条正则 → 12 个类别**。
`mcp/README.md:61` 所称「结构化事件 **9 类**」与源码不符（**3 类出入**，见 §8 矛盾清单第 1 条）。

| # | 类别 | 触发正则（`re.search`，非锚定） | 结构化字段 |
|---|------|-------------------------------|-----------|
| 1 | `mission` | `MissionEngine:triggerLog:(\w+):\s*(.+?) for teamId:(\d+) to targetId:(\d+) \(#units:(\d+)\)` | `trigger`, `action`, `team`, `target`, `units` |
| 2 | `ai` | `ai_debug\((\d+)\):([\w_]+):\s*(.*)` | `ai`, `phase`, `detail` |
| 3 | `team_start` | `Team\(id: (\d+), name:(.+?)\):Being replaced` | `team`, `name` |
| 4 | `map_info` | `Map size: (\d+), (\d+)` | `width`, `height` |
| 5 | `map_info` | `Found (\d+) map triggers` | `triggers` |
| 6 | `map_info` | `global Seed: (\d+)` | `seed` |
| 7 | `mod_info` | `Number of mods:(\d+)` | `count` |
| 8 | `mod_info` | `Mod: '(.+?)'` | `name` |
| 9 | `game_state_log` | `(?:--- )?(setRunning\|setStopped\|setStoppedIfNotInGameThread)` | `state` |
| 10 | `loading_stage` | `MapLayer create: (\w+)` | `layer` |
| 11 | `asset_missing` | `FileLoader: Could not find asset:(\S+)` | `asset` |
| 12 | `texture` | `getNewTextureHolder: append:(\d+)` | `append` |
| 13 | `victory` | `(?:\S+\s+\S+: )?\s*(.*?) has been wiped out \(Team: ([A-P])\)` | `wiped_out`, `team_letter` |
| 14 | `error` | `Could not find type:\s*(\S+)` | `type` |
| 15 | `crash` | `onGameCrash` | `{}`（空） |

**12 个去重类别**：`mission` / `ai` / `team_start` / `map_info` / `mod_info` / `game_state_log` /
`loading_stage` / `asset_missing` / `texture` / `victory` / `error` / `crash`。

补充事实：
- `_parse_event`（L458-467）**短路返回首个命中**，故一条日志最多归一类；`conv(m)` 抛异常时降级为 `{"raw": 行尾 120 字符}`。
- 全部正则**无 `^` 锚定**，因此 `MissionEngine:triggerLog:` 可出现在行内任意位置。
- 该表的 3 条样例注释（L419-421）来源是**实测日志**，可作为回归基准。

### 5.2 三个接口的差异对照

| 项 | `game_read_log` | `game_read_log_file` | `game_log_stream` |
|----|-----------------|----------------------|-------------------|
| 定义行 | `mcp_game_server.py:390-393` | `:396-412` | `:470-502` |
| **数据源** | `CONN.tail(n)` = `GameConnection` 的 `deque(maxlen=300)` 环形缓冲 | `Path(LOG_FILE)` 全文读 | `Path(LOG_FILE)` **游标增量**读 |
| 缓冲内容 | **调试 socket 的「命令响应」**（`send_raw` 把每次响应按行 append，L129-131） | 游戏日志文件 | 游戏日志文件 |
| 是否日志 | ❌ **不是游戏日志** | ✅ 是 | ✅ 是 |
| 上限 | `min(lines,300)` | `min(lines,300)` | `new` ≤300 行 / `events` ≤100 条 |
| 过滤 | 无 | `grep` 子串 | `grep` 子串 |
| 结构化 | 无 | 无 | ✅ 12 类 |
| 增量 | 否 | 否 | ✅（`_LOG_POS`） |
| 自称 | 「调试 socket 推送缓冲」 | 「日志文件验证通道」 | 「增量日志流」 |

> **`game_read_log` 的语义澄清**：自述「调试 socket 推送缓冲」中的「推送」二字与实现不符——
> 调试 socket 无日志推送通道（§0.2）。该缓冲实际是 **MCP 自己历次命令的响应文本**，
> 因此在「尚未发过任何命令」时为空；命令成功时通常只有 `done` / `ok` 之类的回显。
> 需要真实日志证据时必须用 `game_read_log_file`（或先 `-log` 启动）。
> 这也是既交接单反复出现的「以**被控端日志文件**为判据」的根因。

### 5.3 与日志无直接关系但常被混淆的接口

| 工具 | 真实数据源 | 说明 |
|------|-----------|------|
| `game_victory_check`（`:893-946`） | **日志文件** | 关键词 `wiped out` / `has been defeated` / `eliminated` / `victory`；并从命中行提取 `\(Team: ([A-P])\)` 字母 |
| `game_realtime_stats`（`:505-531`） | 日志文件（近 300 行）+ 窗口 + ping | 关键词固定 5 个：`MissionEngine` / `ai_debug` / `wiped out` / `Could not find type` / `onGameCrash` |
| `game_unit_dump`（`:837-890`） | **纯文本存档** | 解析 `Saving unit:` 分段与 `#writeFloat`/`#int:`/`#unitType:`/`#team:` 标记；**不读日志** |
| `game_highfreq_monitor`（`:534-620`） | 日志增量行数 + `GetProcessTimes` CPU 时钟 + 窗口前台 | 「近帧级」是**替代指标**：自述「游戏无 FPS/帧计数通道」（L542-543） |

### 5.4 MCP 状态机对日志的依赖

`GameConnection._update_state`（`:76-95`）把日志**当状态推断源**：

| 触发文本 | 推断状态 |
|---------|---------|
| `onGameCrash` / `uncaughtException` / `连接重置` | `CRASHED`（`crash_count += 1`） |
| `loadDocument:assets/gui/mainMenu.rml` | `MAIN_MENU` |
| `loadReplay` | `REPLAY` |
| `Team(id:` + `Being replaced` | `STARTING` |
| `Mapfile:` + `skirmish` | `IN_GAME` |
| `Game init finished`（仅当已 `UNKNOWN`） | `MAIN_MENU` |

> ⚠️ 该表读的是 `send_raw` 的**响应文本**，而响应文本里通常不含上述日志（§5.2），
> 故 `refresh_state()`（`:138-141`）采用「发一条无害命令 `script root.getVersionName()` 期望回带日志」的做法——
> 该期望与 §0.2 的「socket 不推日志」相冲突。**待验证**：实际运行时状态机是否真能推进（QAV G-2 范围）。

---

## 6. 日志文件位置与轮转

### 6.1 实测文件清单（`{GAME}/` 顶层）

| 文件 | 生成者 | 生成条件 | 覆盖行为 |
|------|--------|---------|---------|
| `lastrun.log` | QAV/工具启动命令 | 显式传 `-log lastrun.log` | 每次运行**截断覆盖** |
| `lastrun-replay*.log`（30+ 份） | QAV 批次 | 显式传 `-log lastrun-replay-<件名>.log` | 同上 |
| `lastrun-gui-replay.log` | QAV G-2（本任务并行） | 同上 | 同上 |
| `game-mcp.log` | `mcp_game_server.py` | 仅 `_launch_game`（`:170-175`）：`stdout=open(LOG_FILE,"w")` | **每次 MCP 启动覆盖**（`"w"`） |
| `crashes.txt` | `CrashHandler` | 见 §6.3 | **追加**（`FileLoader.a(file, true)`） |
| `jvm/bin/crashes.txt`、`rw_py/crashes.txt` | 同上 | CWD 不同 → 落在 CWD | 追加 |

### 6.2 轮转

**不存在任何轮转机制。** 证据：

- 游戏侧无「保留 N 份 / 按日期命名」逻辑（`-log` 就是 `new PrintStream(路径)`，GameLauncher.java:223）。
- 文件名完全由调用方决定；`lastrun-replay-*.log` 的 30+ 份是 **QAV 手工按批次命名** 的产物，不是自动轮转。
- `MCP` 侧固定单文件 `game-mcp.log`，且以 `"w"` 打开 → **上一次的证据必被抹掉**（这是 MCP 日志证据链的已知弱点）。

> **建议**：需要对局证据时，一律用 `-log <唯一名>.log` 显式启动（QAV 现行做法），不要依赖 `game-mcp.log`。

### 6.3 崩溃文件 `crashes.txt`

```java
// 03-deobfuscated/.../GlobalState.java:1210-1235
public static File getCrashLogFile() {
    FileLoader.d();
    String string = "/SD/rustedWarfare/crashes.txt";       // 移动端路径
    if (GlobalState.at()) { string = "/SD/rustedWarfare/crashes.txt"; }
    return new File(FileLoader.e(string));                 // FileLoader.e() 做平台路径映射
}

public static void e(String string, String string2) {      // ← 注意: 与日志用 e(String) 不同重载
    OutputStream os = FileLoader.a(file, true);            // append = true
    PrintWriter pw = new PrintWriter(os);
    String ts = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    pw.write("\r\n" + string + " (at " + ts + " - " + "1.15" + "" + ")\n");
    pw.write(string2 + "\r\n");
    pw.close();
}
```

- 唯一调用点：`CrashHandler.java:87` → `GlobalState.e("fatal", string)`。
- 实测格式（`{GAME}/crashes.txt`，362 B）：
  ```
  （空行）
  fatal (at 2026-08-30 21:19:09.884 - 1.15)
  java.lang.NoSuchMethodError: 'void com.corrodinggames.rts.java.Main$2.<init>(...)'
      at com.corrodinggames.rts.java.Main.g(SourceFile:236)
      ...
  ```
- **落在进程 CWD**，因此同一台机器会出现多份（实测：`{GAME}/crashes.txt`、`{GAME}/rw_py/crashes.txt`、`{GAME}/jvm/bin/crashes.txt`）。
- 格式要点：每条记录以 `\r\n` 开头（首条因此前面有个空行）、`\n` 结束标题行、`\r\n` 结束正文；标题含**游戏版本 `1.15`**。

---

## 7. 内嵌日志缓冲（LibRocket 侧）——**死通道**

LibRocket 存在一套「最近 3000 行」的内存日志缓冲，并被脚本 API 暴露：

```java
// 03-deobfuscated/.../LibRocketContext.java:179-181
public LinkedList k() { return null; }        // ← 桌面构建永远是 null
```

- `Root.writeGameLog(String id)`（`MainUIController.java:2076-2104`）：取 `LibRocketContext.a().k()`，
  若为 `null` → `alert("Internal game logging not active")` 并返回。
- `Root.exportGameLog()`（`:2109-2148`）：同判断；成功时会写
  `/SD/rustedWarfare/RustedWarfareLog-<d_MMM_yyyy_HH.mm.ss>.txt`（`:2133`）。
- **字节码级全类扫描**：整个 `game-lib.jar` 中只有 `com/corrodinggames/librocket/a.class` 声明
  `()Ljava/util/LinkedList;`，且其 `k()` 体为 `aconst_null; areturn`（返回 null）→ **无任何子类覆写**。

→ 结论：**桌面 Java 版 `root.writeGameLog` / `root.exportGameLog` 必定走「Internal game logging not active」分支**，
该 3000 行缓冲在本发行版上是**死通道**。复现命令见 §9-4。

---

## 8. 已知矛盾与订正清单

| # | 冲突点 | 事实（证据） | 处置 |
|---|--------|-------------|------|
| 1 | `mcp/README.md:61` 称 `game_log_stream` 有「结构化事件 **9 类**」 | 源码 `_EVENT_PATTERNS` = **15 条正则 / 12 个类别**（`mcp_game_server.py:422-455`） | 以源码为准；本文 §5.1 给全表 |
| 2 | `mcp/README.md:62` 称 `game_read_log` 为「调试 socket **推送**缓冲」 | 调试 socket **无推送**（§0.2）；该缓冲是 MCP 自己的命令响应 | 语义澄清见 §5.2；建议 MCP 文档订正（后续工作项） |
| 3 | `DEBUG-FEATURES.md:131` 记 `MapTrigger-Error ... (AITask.g)` 为日志行 | `AITask.g` 走 `NetEngine.registerRelayServer` → 落成 **`desync:` 前缀 + 栈**；直接落日志的是 `AITask.h`（`MapTrigger-Debug`） | 本文 §3.4 订正；`DEBUG-FEATURES.md` 待同步（KBC 范围） |
| 4 | 直觉「`-nologfile` 关闭日志文件」 | 空实现（GameLauncher.java:234），且桌面版无默认日志文件 | 本文 §1.2 |
| 5 | 直觉「日志级别前缀 `INFO:` 属于游戏」 | 来自 JUL/Slick2D；游戏自有行**无级别字段**，`tag` 与优先级均被 shim 丢弃 | 本文 §4.2 |
| 6 | 「`Saving unit:` 是日志行」 | 是**纯文本存档标记**（`GameSaver.java:289`）；全量 `*.log` 0 命中 | 本文 §3.6 |
| 7 | MCP 自述「调试 socket 推送日志」（`GameConnection.send_raw` 注释 L104、`refresh_state` L139） | 与 §0.2 冲突 | 「待验证」登记 §10；影响 MCP 状态机可靠性 |

---

## 9. 复现命令（关键结论）

```powershell
# 0) 环境锚点
$RW = "C:\Users\28210\Downloads\Rusted Warfare\rw-reverse"
$GAME = "C:\Users\28210\Downloads\Rusted Warfare"
$JAR = "$RW\RustedWarfare\game-lib.jar"

# 1) 「桌面版无自动日志文件」：全 jar 常量池搜 lastrun → 期望 0 命中
python -c "import zipfile,re;z=zipfile.ZipFile(r'$JAR');print([n for n in z.namelist() if n.endswith('.class') and b'lastrun' in z.read(n)])"

# 2) 提取 36 个 CLI flag token（期望 35 个 '-' + 1 个 '+'）
Select-String -Path "$RW\03-deobfuscated\com\corrodinggames\rts\java\GameLauncher.java" -Pattern 'equals\("([-+][^"]+)"\)' -AllMatches |
  ForEach-Object { $_.Matches } | ForEach-Object { $_.Groups[1].Value } | Sort-Object -Unique

# 3) 字节码验证日志 API 层（看 a/b/e 的前缀常量与颜色码）
& "$GAME\jvm64\bin\javap.exe" -p -c -classpath $JAR com.corrodinggames.rts.gameFramework.l |
  Select-String -Pattern "String --- ERROR|String \\u001b" -Context 0,2

# 4) 验证内嵌日志缓冲是死通道（期望: k() 体为 aconst_null; areturn）
& "$GAME\jvm64\bin\javap.exe" -p -c -classpath $JAR com.corrodinggames.librocket.a |
  Select-String -Pattern "LinkedList k\(\)" -Context 0,4

# 5) 验证「调试 socket 无日志推送」：DebugSession 只有一条 print 语句
& "$GAME\jvm64\bin\javap.exe" -p -c -classpath $JAR com.corrodinggames.rts.a.b

# 6) 验证 script/function 协议返回（NUL 终止符在 modified-UTF8 中为 C0 80）
python -c "import zipfile;d=zipfile.ZipFile(r'$JAR').read('com/corrodinggames/rts/a/a.class');print('NUL const at', d.find(b'\x01\x00\x02\xc0\x80'), 'empty at', d.find(b'\x01\x00\x00'))"

# 7) 实测日志格式与三类行分流（需要 $GAME\lastrun*.log 至少一份）
python -c "import re,glob;[print(sum(1 for l in open(f,encoding='utf-8',errors='replace') if re.match(r'^\d{4}-\d{2}-\d{2} ',l)),'/',sum(1 for l in open(f,encoding='utf-8',errors='replace') if l.strip()),f) for f in glob.glob(r'$GAME\lastrun*.log')[:5]]"

# 8) 显式落地一份日志（QAV 口径；**会启动游戏，属 QAV G-2 范围**）
#    & "$GAME\jvm64\bin\java.exe" -Xmx2000M -Dfile.encoding=UTF-8 -Djava.library.path=. `
#      -cp "game-lib.jar;libs/*" com.corrodinggames.rts.java.Main `
#      -nodisplay -debug 5677:local -log lastrun-replay-<件名>.log
```

---

## 10. 待验证项（已登记 PENDING）

| # | 待验证 | 影响 | 验证方法 | PENDING |
|---|--------|------|---------|---------|
| V1 | MCP 状态机是否真能靠 `send_raw` 响应推进（`refresh_state` 假设 socket 回带日志） | MCP 状态过滤（debug.* 仅对局中可调）的可靠性 | 游戏运行中连续调 `game_status` 观察 `state` 是否随对局推进；对照 `game-mcp.log` 是否含状态关键词 | #26 |
| V2 | `game_read_log` 在真实对局中的内容形状（是否始终只有 `done`/`ok`） | 判据选型 | 对局内调用若干 `debug_*` 后调 `game_read_log`，与 `lastrun*.log` 逐行对照 | #27 |
| V3 | `-nologfile` 在**移动端/其它后端**是否有真实语义 | 文档完备性 | 全 jar 扫描其引用点（当前仅 `Main.class` 常量池） | #28 |
| V4 | MCP 启动路径（无 `-Dfile.encoding`）下中文日志的落盘编码 | `game_log_stream` / `game_read_log_file` 的中文解析正确性 | 经 `--launch-game` 启动、对局内触发中文日志（如 `replay:updateGameFrame:message: ... <中文名>`），以 `utf-8` 与 `gbk` 两种方式解码对比 | #29 |
| V5 | libRocket 3000 行缓冲在**移动端**是否真实可用 | `root.writeGameLog` 的可用性判定 | 检查移动端 `com.corrodinggames.librocket.b` 是否覆写 `k()`（本发行版 jar 内无覆写） | #30 |

---

## 11. 交叉引用

- [DEBUG-FEATURES.md](DEBUG-FEATURES.md) — 调试键位 / Debug 对象 API / 5677 调试服务器 / 运行时三通道
- [API-SURFACE.md](API-SURFACE.md) — 五类接口面索引（Debug Socket / ScriptEngine / MCP / CLI / 网络）
- [GAMELOOP.md](GAMELOOP.md) · [MATCH-LIFECYCLE.md](MATCH-LIFECYCLE.md) · [SAVELOAD.md](SAVELOAD.md) — 日志内容对应的生命周期域
- [../06-network/NETWORK-PROTOCOL.md](../10-network/NETWORK-PROTOCOL.md) — §3.2 网络日志的协议上下文
- [../STATUS.md](../STATUS.md) — 全局口径唯一来源
- PENDING — §10 待验证项登记处（内部跟踪，不在本仓库）
