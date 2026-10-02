# API 接口面 — 五类入口索引（Debug Socket / ScriptEngine / MCP 工具层 / 命令行 / 网络协议）

> v19.133f98 | 2026-09-21 | Rusted Warfare v1.15 对外可调用接口面的统一索引与深度说明（① Debug Socket 与 ② ScriptEngine 为深度重点）
> ⚠️ 命名时点: 本文类名以 **03 解混淆名** 为准，括号内给出 02b 混淆名 / jar 原始类名。
>   对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)。
> ⚠️ 证据口径: 协议帧、返回格式、对象注册条件**一律以 `RustedWarfare/game-lib.jar` 字节码（javap / 常量池解析）为准**；
>   `03-deobfuscated/` 仅作可读索引。凡 03/02b/字节码三者冲突，以字节码裁定并显式标注。
> ⚠️ 引文体例: 文中代码块为**节选**并做过**缩进/换行重排**（如把 `var` 名还原为语义名以便阅读），
>   **权威定位一律以标注的 `文件:行号`、`文件:偏移量` 与 javap 输出为准**。
> 配套: [LOGGING.md](LOGGING.md)（日志体系） · [DEBUG-FEATURES.md](DEBUG-FEATURES.md)（调试键位与 Debug 对象） · [../06-network/NETWORK-PROTOCOL.md](../10-network/NETWORK-PROTOCOL.md)

---

## 0. 五类接口总览

| # | 接口面 | 入口 | 协议 | 清单规模 | 最小可复现调用 | 深度 |
|---|--------|------|------|---------|---------------|------|
| ① | **Debug Socket** | `com.corrodinggames.rts.a.a`（03: `rts/platform/a.java`）<br>启动：`-debug <port>:<host>` | TCP 127.0.0.1:5677，**行分隔文本**，5 条命令 | 5 条命令（`ping`/`crash`/`script`/`function`/`functionNoTimeout`） | `§1.9` | ★★★ 深度 |
| ② | **ScriptEngine 脚本 API** | `com.corrodinggames.librocket.scripts.ScriptEngine` | 反射式「对象.方法(参数)」文本表达式，`;` 分隔 | **4 对象 271 方法**（debug 67 / root 155 / mods 16 / multiplayer 33） | `§2.7` | ★★★ 深度 |
| ③ | **MCP 工具层** | `mcp/mcp_game_server.py`（MCP stdio server） | JSON-RPC over stdio（MCP SDK 2.x `MCPServer`） | **297 工具**（26 静态 + 271 动态） | `§3.5` | ★★ 分类索引 |
| ④ | **命令行参数** | `com.corrodinggames.rts.java.Main#main`（03: `java/GameLauncher.java:95`） | 进程 argv，单次线性解析 | **36 token**（35 `-xxx` + 1 `+connect_lobby`） | `§4.2` | ★★ 全表 |
| ⑤ | **网络协议 API** | `gameFramework.network.NetEngine`（02b `j/ad`） | 4 层协议栈（可靠 UDP → 包层 → 网络引擎 → 主服务器 HTTP） | 30 种游戏/系统包 | `§5.2` | ★ 指向 + 层级索引 |

---

## ① Debug Socket 协议（深度）

### 1.1 类定位：存在**两套同名实现**（关键区分）

`game-lib.jar` 中有**两个**都自称 "DebugServer" 的类，行为完全不同：

| 类（jar 原始名） | 03 对应文件 | 特征字符串 | `script` 命令实现 |
|-----------------|------------|-----------|------------------|
| **`com/corrodinggames/rts/a/a`** | `com/corrodinggames/rts/platform/a.java` | `DebugSocketConnection: waiting for ScriptEngine to start....`、`Got IOException on debugSocket connection`、`test crash` | **真实执行**（`ScriptEngine.addScriptToQueue` + `waitForCompletionOrCrash`） |
| `com/corrodinggames/rts/gameFramework/c/a` | `com/corrodinggames/rts/gameFramework/commands/DebugServer.java` | `----- Debug Active ----`、`----- createDebugSocket ----`、`password: `、`port: ` | **桩**：`script` → `"todo"` |

**裁定（字节码常量池）**：`com/corrodinggames/rts/java/Main.class` 的常量池**只引用 `com/corrodinggames/rts/a/a`**
（不引用 `gameFramework/c/a`）→ **`-debug` 走的是前者（真实实现）**。

```powershell
# 复现：Main.class 常量池中的 debug 相关类引用（期望出现 com/corrodinggames/rts/a/a）
python -c "import zipfile;d=zipfile.ZipFile(r'RustedWarfare/game-lib.jar').read('com/corrodinggames/rts/java/Main.class');print(b'com/corrodinggames/rts/a/a' in d, b'com/corrodinggames/rts/gameFramework/c/a' in d)"
```

> ⚠️ **陷阱**：`mappings/class-discoveries.csv` 把两个类**都**命名为 `DebugServer`（`:147` 与 `:735`），
> 因此按类名检索源码会命中错误的那个（03 的 `commands/DebugServer.java` 是 `gameFramework/c/a`，非 `-debug` 路径）。
> 本文后续 §1.2–§1.8 一律以 **`com/corrodinggames/rts/a/a`** 为对象。

### 1.2 握手 / 激活流程

```
java Main -debug 5677:local
   │
   ├─ GameLauncher.a(String[])            03 java/GameLauncher.java:196-206
   │     port = argv[i].split(":")[0]  (Integer.parseInt)
   │     host = argv[i].split(":")[1]
   │     └─▶ DebugServer.a(port, host)      = com.corrodinggames.rts.a.a.a(int,String)
   │            ├─ c = true                 ← 静态「调试已启用」标志
   │            ├─ GlobalState.aT = true
   │            └─ if (port != -1) { new ServerSocket(port); new Thread(this).start(); }
   │
   └─（每次建立连接）
        a.run()                            03 rts/platform/a.java:66-87
          socket.setTcpNoDelay(true)       ← Nagle 关闭（低延迟）
          new DebugSession(this, socket) → Thread.run()   ← 注意：run() 而非 start()
          ↑ 这是 bug：run() 同步执行，:75；后果见 §1.6
```

**ScriptEngine 惰性等待**（`b(String)` 首段，03 `rts/platform/a.java:101-116`）：

```java
if (ScriptEngine.getInstance() == null) {
    GlobalState.b("DebugSocketConnection: waiting for ScriptEngine to start....");   // 黄字 WARN
    for (int j = 0; j < 100; ++j) {                        // 最多 100 轮
        if (ScriptEngine.getInstance() != null) { GlobalState.b("started"); break; }
        Thread.sleep(100L);                                // × 100 ms = 最长 10 s
    }
}
```

- 激活时机：`ScriptEngine` 由 UI（LibRocket）初始化时 `createScriptEngine(...)` 建立（02b `ScriptEngine.java:64-71`，
  已存在则抛 `"scriptEngine already exists"`）。因此**在 UI 起来之前连接 socket，命令会阻塞至多 10 s**。
- 若 10 s 内仍未就绪：**不报错**，直接继续走到命令分派 → `ScriptEngine.getInstance()` 返回 null →
  `script` 分支会 **NPE**（`scriptEngine.addScriptToQueue` 对 null 解引用，字节码偏移 183）。
- 主菜单期即可用：`-debug` 后 socket 在主菜单阶段就能执行 `root.*`（这也是 MCP「开局」流程的入口）。

### 1.3 命令帧格式（客户端 → 服务端）

- **帧边界**：`BufferedReader.readLine()`（02b `rts/a/b.java:28`）→ 以 `\n`（或 `\r\n`）结束的**单行文本**，UTF-8。
- **语法**：

```
<LINE>   ::= <CMD> [ " " <ARGS> ]
<CMD>    ::= 首个空格之前的全部字符, 转小写 (Locale.ENGLISH)
<ARGS>   ::= 首空格之后的剩余全部字符 (可含空格; 同时按 " " 切成 <ARGS_ARRAY>)
```

解析实现（03 `rts/platform/a.java:89-100`）：

```java
int n2 = string.indexOf(" ");
if (n2 == -1) n2 = string.length();                 // 无空格 → 整个串是命令
String cmd = string.substring(0, n2).toLowerCase(Locale.ENGLISH);
if (n2 != -1 && string.length() >= n2 + 1) {
    argStr   = string.substring(n2 + 1);            // 命令后原文（保留内部空格）
    argArray = argStr.split(" ");                   // 再切成数组（script/function 只用 argStr）
}
```

命令分派表（字节码核对：`javap -c ... com.corrodinggames.rts.a.a`，偏移 128–446）：

| 命令 | 分派（`equalsIgnoreCase`） | 行为 |
|------|--------------------------|------|
| `ping` | 偏移 128-139 | 立即返回 `"pong"` |
| `crash` | 偏移 140-158 | `throw new RuntimeException("test crash")` ← **故意崩游戏**，用于测崩溃处理 |
| `script <expr>` | 偏移 159-210 | `addScriptToQueue(expr)` → `waitForCompletionOrCrash(false)` |
| `function <expr>` | 偏移 211-229 + 236-392 | `addRunnableToQueue(a$2)`，`tryToCatchCrash = true`，超时 = 30 s |
| `functionNoTimeout <expr>` | 同上（偏移 275-285 置 noTimeout） | 同 `function`，但**永不超时**（`waitForCompletionOrCrash(true)` 内部把计数清零） |
| 其它 | 偏移 444-446 | 返回 `"unknown command"` |

缺失参数（仅 `script`/`function*` 有判空）：返回 `"argString==null"`（常量 `#7`）。

### 1.4 返回帧格式（服务端 → 客户端）— **无换行，终止符因命令而异**

`DebugSession` 写回（02b `rts/a/b.java:33-35`）：

```java
String var4 = a.b(var3);
var1.print(var4);      // ← print 而非 println: 不追加换行
var1.flush();
```

因此响应**不含 `\n`**，客户端必须按「终止符」或「已知长度」收包。终止符逐命令不同（**字节码常量池实证**）：

| 命令 | 成功响应 | 失败/异常响应 | 终止符 |
|------|---------|--------------|--------|
| `ping` | `pong` | — | **无** |
| `script <expr>` | `done`（恒为 `done`，见 §2.4） | 崩溃文本（`caughtCrash`；默认不捕获，见下） | **无** |
| `function <expr>` | `ok\n<值>\u0000`；值为 null → `ok\n<NULL>\u0000` | `crash\n<崩溃文本>\u0000`；超时 → `crash\nTime Out\u0000` | **`\u0000`** |
| `functionNoTimeout <expr>` | 同 `function` | 同（除超时） | **`\u0000`** |
| 其它 | — | `unknown command` | **无** |

**终止符字节码实证**（`com/corrodinggames/rts/a/a.class`）：

```
#1 // String                      ← 空串 ""          (偏移 350, 拼在值之前)
#2 // String \u0000                ← NUL (U+0000)     (偏移 380 与 431, 响应末位)
#3 // String " "                   ← 单个空格         (偏移 5/65, indexOf/split 的分隔符)
```

> ⚠️ **`\u0000` 在 class 文件中以 modified-UTF-8 的 `C0 80` 两字节编码**，直接搜 `\x00` 会漏检。
> 复现：`python -c "import zipfile;d=zipfile.ZipFile(r'RustedWarfare/game-lib.jar').read('com/corrodinggames/rts/a/a.class');print('NUL@',d.find(b'\x01\x00\x02\xc0\x80'))"` → `NUL@ 564`。
> 另注：02b FernFlower 把该 NUL 渲染为 `" "`（不可见字符），CFR（03）正确渲染为 `"\u0000"` —— **此处 CFR 更可信**。

`function` 返回值桥接链：`a$2.run()` → `this.c = ScriptEngine.processArg(expr)`（02b `rts/a/a$2.java:19-27`），
`a$2.c` 字段继承自抽象基类 `com/corrodinggames/rts/a/c`（dummy 类，仅一个 `Object c` 字段）。
`a$2.run()` 期间置 `ScriptEngine.inDebugScript = true`（同一实现，finally 复位）。

### 1.5 连接生命周期

```
accept() ──▶ setTcpNoDelay(true) ──▶ new DebugSession ──▶ run()  [同步, 见 §1.6]
   │                                                          │
   │                                       readLine() 循环 ◀───┘
   │                                          │
   │  client 断连 (readLine == null) ─────────┘─▶ break ─▶ socket.close()
   │
   └─ ServerSocket 循环条件: DebugServer.i (实例字段, 默认 true)
        —— 无「优雅关停」入口: 仅在 run() 抛 IOException 时整个线程终止 (RuntimeException 冒泡)
```

- **单连接语义**：不维护连接表；`a(j)` 静态 `ArrayList` 只存 `-debugscript` 的**脚本文件路径队列**，与连接无关。
- **多客户端**：可以并发连接（每次 `accept()` 一个新 `DebugSession`）。但由于 §1.6 的同步执行，实际是**串行服务**。
- **断连处理**：`readLine()` 返回 null → break → `finally { socket.close() }`，服务端不记录、不重试。
- **异常处理**：`IOException` → `printStackTrace()` + 关 socket；`RuntimeException`（如 `crash` 命令）→ **冒泡出 `run()`**，
  使该服务线程死亡（`a.run()` 外层只捕获 `IOException`，字节码 `Exception table` 仅含 `InterruptedException`）。

### 1.6 并发 / 超时行为（含一处**原版缺陷**）

| 项 | 行为 | 证据 |
|----|------|------|
| 每连接线程 | `new Thread(b2)` 后调 **`run()`** 而非 `start()` | 03 `rts/platform/a.java:74-75`；02b `rts/a/a.java:65-66` 同样 |
| 后果 | `run()` 同步执行 → **`accept()` 循环被阻塞**：同一时刻只服务一条连接，且整条会话期间无法接受新连接 | 由上面两行直接推出的原版缺陷；现象层复核（长命令期间并发 `ping` 是否被阻塞）**待 QAV 实测** |
| `script` 等待上限 | `ScriptEngine$Action.waitForCompletionOrCrash`：`for (i=0; i<3000; i++) { if(completed) return; sleep(10); }` → **≈30 s**，超时返回 `"Time Out"` | 02b `ScriptEngine$Action.java:32-50` |
| `function` 等待上限 | 同上（`noTimeout=false`） | 同上 |
| `functionNoTimeout` | 同上但 `if (var1) var2 = 0;` → **永不自增**，即**无限等待** | 02b `ScriptEngine$Action.java:44-46` |
| 异常传播 | `script` 分支 `tryToCatchCrash = false` → 表达式异常被包成 `RuntimeException` **重抛**；`function` 分支为 `true` → 捕获并记录到 `caughtCrash` | `ScriptEngine$Action.java:16-30`；`rts/a/a.java:268-269`（function 置 true） |
| 客户端侧超时 | MCP `wait=4.0` s（`mcp_game_server.py:103,145`）；`debug_client.py` `wait=8.0` / ping `3`（`debug_client.py:30,49`）；`tools/utils/debug_script.py` socket 超时 30 s | 各文件行号 |

### 1.7 与 `gameFramework/c/a` 的差异（供对照）

| 项 | `rts/a/a`（`-debug` 实走） | `gameFramework/c/a`（`Debug Active` 路径） |
|----|---------------------------|------------------------------------------|
| 触发 | `-debug <port>:<host>` | 游戏内「Developer」入口（`DebugServer.a()`） |
| 日志标志 | 无 `----- Debug Active ----` | 打 `-----` ×2 + `----- Debug Active ----` + `-----` ×2 |
| 默认端口 | 由 argv 决定（QAV/MCP 用 5677） | 硬编码 `a(5677, "")`（03 `commands/DebugServer.java:44`） |
| `script` | 真实执行 | `"todo"`（桩） |
| 额外字段 | — | `debugRenderEnabled`、`e`(float)、`eb`(任务队列) |
| 复核 | 03 `rts/platform/a.java` 全文 163 行 | 03 `commands/DebugServer.java` 全文 129 行 |

### 1.8 客户端清单与已知缺陷

| 客户端 | 路径 | 用到的命令 | 缺陷 |
|--------|------|-----------|------|
| `debug_client.py` | `tools/_archive/campaign/capture/debug_client.py`（114 行） | `ping`、`script` | ① 每次命令**新建 socket**（`:23-27`），无法利用 `function`；② 返回值桥接只支持 `String`，方式为 `script x = expr` + `script root.logDebug(x)`（两条命令，`:80-81`）；③ 非 String 返回值直接丢弃（`:83-85`） |
| `debug_script.py` | `tools/utils/debug_script.py`（2.5 KB） | `script` | **等待 `\x00` 结尾**（`:31-33`），但 `script` 响应**永不含 `\x00`**（§1.4）→ 必然挂到 30 s socket 超时后才返回。已登记为 PENDING §5-15（D-9①） |
| `GameConnection` | `mcp/mcp_game_server.py:66-150` | `ping`、`script root.getVersionName()` | ① 同样只用 `script`；② `ok` 判定为「响应不含 `Exception`/`Error`/`连接重置`」（`:146`）——因 `script` 恒返回 `done`，该判定**几乎恒真**，无区分力 |

> **结论**：三套客户端**都没有使用 `function` 通道**，因此「脚本引擎无法取返回值」在工程上成立，
> 但**在协议层并不成立**（§2.4）。这是本盘点最重要的可用性缺口。

### 1.9 最小可复现调用

```powershell
$GAME = "...\Rusted Warfare"
$RW   = "$GAME\rw-reverse"

# 1) 启动游戏并开调试端口（**会启动游戏进程；QAV G-2 范围**）
& "$GAME\jvm64\bin\java.exe" -Xmx2000M -Dfile.encoding=UTF-8 -Djava.library.path=. `
   -cp "game-lib.jar;libs/*" com.corrodinggames.rts.java.Main `
   -nodisplay -debug 5677:local -log lastrun-api-probe.log

# 2) 心跳（协议最小用例；期望服务端回 "pong"，无换行）
python -c "import socket;s=socket.create_connection(('127.0.0.1',5677),10);s.sendall(b'ping\n');print(repr(s.recv(64)));s.close()"
#   期望输出: b'pong'

# 3) 执行脚本（期望回 "done"；无终止符）
python -c "import socket;s=socket.create_connection(('127.0.0.1',5677),10);s.sendall(b\"script root.loadReplay('test_replay.replay')\n\");s.settimeout(3);print(repr(s.recv(4096)));s.close()"

# 4) 取返回值（唯一可用通道；期望 "ok\n<值>\u0000"）
python -c "import socket;s=socket.create_connection(('127.0.0.1',5677),10);s.sendall(b'function root.getVersionName()\n');s.settimeout(35);\
buf=b''
while not buf.endswith(b'\x00'): buf+=s.recv(4096)
print(repr(buf)); s.close()"

# 5) 复现「script 无终止符」缺陷（期望 30 s 超时）
python -X utf8 "$RW\tools\utils\debug_script.py" --wait 0 "root.getVersionName()"
```

---

## ② ScriptEngine 脚本 API（深度）

### 2.1 引擎与对象模型

`com.corrodinggames.librocket.scripts.ScriptEngine`（03: `librocket/scripts/ScriptEngine.java`）是**单一全局单例**。

```java
// 02b ScriptEngine.java:64-93
public static ScriptEngine createScriptEngine(b var0) {
    if (scriptEngine != null) throw new RuntimeException("scriptEngine already exists");
    scriptEngine = new ScriptEngine(var0); return scriptEngine;
}

private ScriptEngine(b var1) {
    this.root = new Root();      setupScriptContext(root);   setGlobalVariable("root", root);
    Multiplayer mp = new Multiplayer(root); setupScriptContext(mp);
                                 setGlobalVariable("multiplayer", mp); setGlobalVariable("mp", mp);
    Mods mods = new Mods(root);  setupScriptContext(mods);   setGlobalVariable("mods", mods);
    root.multiplayer = mp; root.mods = mods;
    if (a.a()) {                 // ← 关键条件: DebugSocketConnection 是否已启用
        Debug dbg = new Debug(root); setupScriptContext(dbg); setGlobalVariable("debug", dbg);
    }
}
```

| 全局变量名 | 类（02b） | 03 对应文件 | 说明 |
|-----------|----------|------------|------|
| `root` | `Root` | `librocket/scripts/MainUIController.java` | 恒注册；UI/流程/存档/回放/设置 |
| `multiplayer` / `mp` | `Multiplayer` | `MultiplayerUI.java` | 恒注册；联机大厅（**两个名字同对象**） |
| `mods` | `Mods` | `ModsUI.java` | 恒注册 |
| `debug` | `Debug` | `DebugUI.java` | **条件注册**：仅当 `com.corrodinggames.rts.a.a.a()` 为真 |

**`debug` 的注册条件（字节码/ScriptEngine 源码实证）**：

```java
// 02b ScriptEngine.java:39-41
public static boolean isStrict() { return a.a(); }     // a = com.corrodinggames.rts.a.a
// 02b rts/a/a.java:24-26
public static strictfp boolean a() { return c; }       // c 由 a(int,String) / a(String) 置 true
```

→ `c = true` 的两处：`a(int,String)`（即 `-debug`，L43）与 `a(String)`（即 `-debugscript`，L29）。
**没有 `-debug`/`-debugscript` 就没有 `debug` 对象**，`debug.xxx()` 会被脚本引擎报
`ScriptEngine - critical: Could not find context for: debug.xxx`。

> ⚠️ **术语陷阱**：`isStrict()` 的名字与语义不符——它表示「调试通道已启用」，
> 而该标志的副作用是 **`logCritical` 从「记日志」升级为「抛 RuntimeException」**（§2.5）。

### 2.2 方法注册规则（= `script_api.json` 的唯一正确抽取口径）

```java
// 02b ScriptEngine.java:95-115
public void setupScriptContext(ScriptContext var1) {
    var1.libRocket = this.slickLibRocket;
    var1.guiEngine = com.corrodinggames.librocket.a.a();
    var1.scriptEngine = this;
    for (Method m : var1.getClass().getMethods()) {     // ← 反射枚举全部 public 方法 (含继承)
        String name = m.getName();
        if (!name.equals("wait") && !name.equals("getClass")) {
            if (var1.methods.get(name) != null) logError("method: " + name + " already exists");
            var1.methods.put(name, m);
        }
    }
}
```

**推论（可机械化复现）**：

1. 脚本可调方法集 = 该对象类（含继承链）的 **public 方法集 − {`wait`, `getClass`}**；
2. **同名方法只保留最后一个**（`methods` 是 `HashMap<String, Method>` → 重载被去重，且**保留哪一个取决于 `getMethods()` 顺序**——非确定）；
3. 抽取器应基于字节码（`javap`）或反射清单，**不得**基于源码文本差分。

### 2.3 表达式语法（`processArg` 递归下降）

`processArg(String)`（02b `ScriptEngine.java:240-291`）按**固定顺序**做整体匹配（`Matcher.matches()` = 全串匹配）：

| 顺序 | 模式 | 结果 | 备注 |
|------|------|------|------|
| 1 | `""`（trim 后为空） | `null` | |
| 2 | 字面量 `null` | `null` | |
| 3 | `'(.*)'` | **String**（经 `f.o(...)` 反转义） | 单引号；`debug_client._format_arg` 用 `\\'` 转义内部引号 |
| 4 | `(-?\d*)` | **Integer** | 注意排在 Float 之前 |
| 5 | `(-?\d*\.\d*)` | **Float** | |
| 6 | `\s*([^\s"']*)\s*=(.*)` | **赋值**：`x = expr` → `setLocalVariable(x, v)`，返回 v | 直写日志 `processArg: setting: x=...` |
| 7 | `\s*([\w\.]+)\((.*)\)\s*` | **函数调用** → `processFunction` | 参数按 `,` 切分后**递归** `processArg` |
| 8 | `false` / `true`（忽略大小写） | Boolean | |
| 9 | 其它 | **变量查名** | 查找链：活动文档 metadata → popup metadata → active document → `globals`；失败打 `processArg: no variable:X` + `SlickLibRocket:HandleEvent: failed to match:X` 并**返回 null** |

**语句分隔**：`processScript(String)` 先 `al.a(script, ';')` 切分，逐句 `processArg`（02b L198-217）。
→ 一条 `script a(); b()` 会被拆成两句依次执行。

**函数调用的名字约束**（`runFunction`，02b L388-460）：

- 以 `.` 切分，**最多 2 段**（`对象.方法`）；`> 2` 段 → `logCritical("Unsupported nameParts: ...")` 并返回 null；
- 1 段（无点）时默认宿主为 **`root`**；
- 2 段时第一段必须是已注册的 `ScriptContext`，否则 `logCritical("Could not find context for: ...")`；
- 方法名不在 `methods` 表 → `logCritical("Could not find function: ...")`；
- 实参多于形参 → `logCritical("function: X does not accept N parameters")`（**但不中断**，多余实参被丢弃）；
- 实参不足 → 缺失位补 `null`（`var10 = null`）；
- 反射调用异常（`IllegalArgumentException` / `InvocationTargetException`）→ 打 `convertedParameters:` 与各参数类型
  （`l.b("=null")` / `l.b("=" + class.getName())`）→ `throw new RuntimeException`。

**不支持**（源码级证据）：字符串拼接、算术/比较运算、多语句表达式、`for/while/if` 等控制流——
`processArg` 的模式表里没有任何运算符分支。

### 2.4 返回值限制 —— 「脚本引擎无法取返回值」的准确定义

| 通道 | 能否取返回值 | 字节码/源码证据 |
|------|-------------|----------------|
| `script <expr>` | ❌ **不能** | `ScriptEngine$Action.caughtCrash` 仅在 `tryToCatchCrash=true` 时被赋值；`addScriptToQueue` 默认 `false`（02b L185-187）→ 成功时 `waitForCompletionOrCrash` 返回 `null` → `rts/a/a` 返回常量 `"done"`（字节码偏移 205） |
| `function <expr>` | ✅ **能** | `a$2.run()` → `this.c = engine.processArg(expr)`；响应 `"ok\n" + (c==null?"<NULL>":"" + c) + "\u0000"` |
| `functionNoTimeout <expr>` | ✅ 能，且**无限等待** | 同上 + `noTimeout=true` |
| 反射内部 | ✅ 能 | `runFunction` 末尾 `Object var15 = var14.invoke(var4, var7.toArray()); return var15;`（02b L433-434），`processFunction` 原样返回（L385） |

**裁定**：

- 「脚本引擎**无法取返回值**」**只对 `script` 成立**，它是三条 MCP/工具客户端唯一使用的通道（§1.8）；
- 协议层**存在**取返回值通道（`function`），但**无任何客户端实现**；
- 因此 MCP 被迫用「旁路通道」拿数据：日志文件（`game_read_log_file`/`game_victory_check`）、
  纯文本存档（`game_unit_dump`）、静态 ini（`game_unit_info`）、静态名字表（`game_unit_names`）——
  见 `mcp/README.md:74-92`。
- `function` 通道的**可用性风险**：`tryToCatchCrash=true` 会吞掉表达式异常（转为 `crash\n...`），
  这意味着 `function` 比 `script` **更安全**（不会因表达式错误崩游戏）——与直觉相反，值得实测验证。

### 2.5 危险方法清单与成因

#### 成因 A（通用）：`logCritical` 在 strict 模式下抛异常

```java
// 02b ScriptEngine.java:462-471
public static void logError(String s)    { l.e("ScriptEngine - error: " + s); }
public static void logCritical(String s) {
    l.e("ScriptEngine - critical: " + s);
    if (isStrict()) throw new RuntimeException("ScriptEngine - critical:" + s);   // ← 崩游戏
}
```

`logCritical` 的调用点（02b L392/398/408/414）= 名字段数 > 2、宿主不存在、方法不存在、**实参过多**。
`isStrict()` = 「`-debug` 已启用」（§2.1）→ **凡 `-debug` 下写错方法名/参数个数，游戏必崩**，
而在无 `-debug` 时同样错误只留一条 `ScriptEngine - critical:` 日志。
这解释了 MCP 必须维护「签名白名单 + 参数个数校验」的全部动机（`debug_client.py:68-75`、`mcp_game_server.py:1147-1167`）。

#### 成因 B：方法内访问未初始化对象（NPE）

| 方法 | 成因（源码 + 字节码） | MCP 处置 |
|------|---------------------|---------|
| `debug.getLocalPlayerId()` | 03 `DebugUI.java:378-381` → `GlobalState.B().bX.z.k`。**javap 实证**：`invokestatic l.B() → getfield l.bX → getfield j/ad.z → getfield game/n.k`。`bX.z`（本地 `PlayerState`）在单机 `startNew` 下为 null → NPE | `DANGEROUS` 硬拦截（`mcp_game_server.py:42`） |
| `debug.getPlayerName(int)` | 03 `DebugUI.java:261-268`：`PlayerState.u(n)` 为 null 时**已做保护**（logWarn 后返回 null）→ **源码层面非 NPE** | `DANGEROUS`（`:43`）→ **与源码不符，待验证**（可能实际崩因是别处，或历史结论基于别的方法） |
| `debug.setNetworkaiDifficulty(int)` | 03 `DebugUI.java:544-549` → `MatchConfig ah2 = l2.bX.kickTeam(); ah2.f = n2;` —— 非房间/非开局上下文取不到 `MatchConfig` → `ah2` 为 null → NPE | `DANGEROUS`（`:44`） |
| `debug.setNetworkStartingUnits(int)` | 同型（`:551-556`） | `DANGEROUS`（`:45`） |
| `debug.checkDesync(int)` | 03 `DebugUI.java:482-492`：**主动 `throw new RuntimeException`**（`ap != 0` 或 `aq < n2`）→ 异常冒泡 → 崩 | `DANGEROUS`（`:47`） |
| `debug.enableFastSync()` | 03 `DebugUI.java:398-401`：`l2.bX.ai = 30` → `bX` 为 null 时 NPE；且改变同步策略 | `DANGEROUS`（`:46`） |
| `debug.plainTextDebugSave(boolean)` | 03 `DebugUI.java:476-480`：**仅** `GameSaver.a = bl2`（写一个静态开关）→ **本方法自身不崩** | `WARN_NOTES`（`:52`）「主菜单调用会崩」→ **成因待验证**（疑在随后的自动存档写盘路径） |
| `multiplayer.*`（33 个） | 需联机大厅上下文；`loadUsername` 等在主菜单 NPE（MCP 实测） | **整对象硬拦截**（`mcp_game_server.py:1159-1160`） |

#### 成因 C：崩溃防护层自身

`_check_call`（`mcp_game_server.py:1147-1167`）三道规则：
① `DANGEROUS` 白名单硬拦截 → ② `multiplayer.*` 整对象拦截 → ③ **状态机过滤**
（`CRASHED` 全拦；`debug.*` 仅 `IN_GAME`/`REPLAY` 允许；`root.*`/`mods.*` 在 `MAIN_MENU`/`IN_GAME`/`REPLAY` 允许）。
`WARN_NOTES`（12 条，`:50-63`）只加描述不改行为。

### 2.6 四对象方法清单（271 = 67 + 155 + 16 + 33）

清单来源：`tools/capture/script_api.json`（**权威副本判定见 §6**）。抽取口径 = §2.2（反射枚举 public 方法）。
测量时点：2026-09-21，`script_api.json` SHA256 前 16 位 `48e3b6f3d6f4376d`（17,418 B）。

| 对象 | 方法数 | 覆盖内容（代表） | 参数含对象类型（不可脚本化） |
|------|--------|-----------------|---------------------------|
| `debug` | **67** | 单位生成/清理（`createUnit`/`createManyUnits`/`createCustomUnitFromTypeId`/`removeAllUnits`/`killAllUnits`/`selectNextUnit`）、资金与外交（`setTeamCredits`/`setTeamAllyGroup`/`moveAllUnitsOnTeam`）、网络（`networkPause`/`networkAbort`/`networkSetUdp`/`enableFastSync`/`checkDesync`/`getNumberOfDesyncErrors`）、存档（`plainTextDebugSave`/`loadSaveFromSystemPath`）、速度（`overrideDeltaSpeed`）、测试（`runAllUnitTests`/`runAllLeakTests`/`startRandomUnitStressTest`/`startRandomUnitDesyncTest`）、查询（`isTeamWipedOut`/`isTeamDefeated`/`isTeamInVictory`/`currentPid`/`getPathSpeed`） | **0** |
| `root` | **155** | 流程（`startNew`/`loadReplay`/`loadGame`/`saveGame`/`back`/`resume`/`exit`/`showMainMenu`）、UI（`open`/`alert`/`showPopup`/`getElementById`/`clickElement`/`setValueById`）、设置（`loadSettings`/`saveSettings`/`applyResolution`）、服务器列表（`refreshServerList`/`displayServerList`/`clickedServer`/`joinServer`）、联机大厅入口（`hostStart`/`hostStartWithPassword`/`showBattleroom`）、地图（`showMaps`/`getMapDetails`/`getMapThumbnail`/`exportMap`）、日志（`logDebug`/`logWarn`/`trace`/`writeGameLog`/`exportGameLog`）、帧回调（`onFrameUpdate`） | **17** |
| `mods` | **16** | `loadMods`/`saveMods`/`refreshModList`/`updateMods`/`disableAll`/`disableAllAsk`/`deleteMod`/`deleteModPopup`/`uploadMod`/`uploadModAsk`/`viewMod`/`openWorkshop`/`setModFilter`/`applyModFilter`/`reloadModDataAsk` | 0 |
| `multiplayer` | **33** | 联机大厅（`multiplayerStart`/`refreshUI`/`loadUsername`/`addAI`/`kick`/`setTeam`/`showPlayerConfig` …）；**MCP 全对象硬拦截** | 0（MCP 未注册亦可） |

**17 个「不可脚本化」的 root 方法**（参数类型非 `String/boolean/int/float/long`；全 17 条列全）：

```
openIfNotDemo(String, Object, String)          delayedOpenNoHistory(String, Object)
open(String, Object)                           clickElement(Element)
showPopupWithButtons(String, String, boolean, e, e)
showPopupWithButtonsAndInput(String, String, boolean, String, e, e)
showMapsWithDoc(ElementDocument)               getKeyBindingAction(int, ad, int)
receiveChatMessage(int, String, String, com.corrodinggames.rts.gameFramework.j.c)
updateTableTextOnly(String, Root$TableData, Root$TableData)
refreshTable(String, Root$TableData)           createAndShowPopup(String, Object, String)
createPopupHidden(String, Object, String)      tryToShowPopupDocument(ElementDocument)
runRunnable(Runnable)                          getModeMapPath(Element, String)
ifCondition(boolean, Object, Object)
```

> ⚠️ **H1 隐患（推断，实测待 QAV 复核）**：这 17 条在 MCP 中**仍被注册为可调用工具**
> （`register_all_tools` 无过滤，`mcp_game_server.py:1216-1221`），
> 而 `TYPE_MAP.get(t.split(".")[-1], str)` 把未知类型**一律回退为 `str`**（`:38`），
> `_format_literal` 对未知类型**返回裸文本**（`:1135-1144`，不引号、不转义）
> → 生成的表达式形如 `root.clickElement(myElement)` → 脚本引擎按**变量查名**解析 → 查不到 → 传 `null`
> → 方法内 NPE → `Action.tryToCatchCrash=false` → 异常冒泡 → **游戏崩溃**。
> `mcp/README.md:72` 已声明「含对象参数的方法不可脚本化」，但**未在调用层做任何拦截或参数校验**。
> 建议（后续工作项）：把这 17 条并入 `_check_call` 的拦截集，或在 `TYPE_MAP` 缺省时直接返回 `blocked`。

### 2.7 最小可复现调用

```powershell
$RW = "C:\Users\28210\Downloads\Rusted Warfare\rw-reverse"

# 1) 签名白名单校验调用（需游戏在跑；bridge 仅支持 String 返回值）
python "$RW\tools\capture\debug_client.py" ring        # 无参: 打印用法
python "$RW\tools\capture\debug_client.py" ping
python "$RW\tools\capture\debug_client.py" call root.getVersionName
python "$RW\tools\capture\debug_client.py" call root.logDebug hello

# 2) 拒绝路径（验证白名单生效，不需游戏）
python "$RW\tools\capture\debug_client.py" call debug.noSuchMethod      # [拒绝] 不在签名白名单
python "$RW\tools\capture\debug_client.py" call debug.lookAt 1          # [拒绝] 需要 2 个参数

# 3) 复现抽取口径：4 对象方法清单（离线，不依赖游戏）
python "$RW\mcp\mcp_game_server.py" --list-tools | Measure-Object -Line   # 期望 297
python -c "import json;d=json.load(open(r'$RW\mcp\script_api.json',encoding='utf-8'));print({k:len(v) for k,v in d.items()}, sum(len(v) for v in d.values()))"
#   期望 {'debug': 67, 'root': 155, 'mods': 16, 'multiplayer': 33} 271

# 4) 直接注入（绕过白名单，高级；危险）
python -c "import socket;s=socket.create_connection(('127.0.0.1',5677),10);s.sendall(b'script x = root.getVersionName()\n');print(repr(s.recv(256)));s.close()"
```

---

## ③ MCP 工具层（297 工具）

### 3.1 计数与构成（实测）

```bash
python mcp/mcp_game_server.py --list-tools | wc -l     # → 297
```

| 组成 | 数量 | 产生方式 |
|------|------|---------|
| 静态 `@mcp.tool()`（`game_*`） | **26** | 手写函数，`mcp_game_server.py:232-1128` |
| 动态注册（`debug_` / `root_` / `mods_` / `multiplayer_`） | **271** | `register_all_tools()`（`:1216-1221`）遍历 `script_api.json` 的 4 对象 |
| **合计** | **297** | |

动态部分明细：`debug_*` 67 / `root_*` 155 / `mods_*` 16 / `multiplayer_*` 33（= §2.6）。

### 3.2 分类索引

| 类别 | 数量 | 工具 | 数据源 |
|------|------|------|--------|
| **实时数据层** | 4 | `game_window_info` · `game_log_stream` · `game_realtime_stats` · `game_highfreq_monitor` | Win32 窗口 / 日志文件 / 进程 CPU 时钟 |
| **生命周期与基础** | 8 | `game_ping` · `game_status` · `game_reset` · `game_force_state` · `game_launch` · `game_stop` · `game_load_replay` · `game_script` | 进程 + socket |
| **日志读取（扩展）** | 2 | `game_read_log`（socket **响应**缓冲）· `game_read_log_file`（日志文件） | 见 [LOGGING.md](LOGGING.md) §5（`game_log_stream` 已计入实时数据层，勿重复计数） |
| **流程聚合** | 8 | `game_skirmish_start` · `game_skirmish_prep` · `game_load_save` · `game_replay_flow` · `game_unit_army` · `game_view` · `game_state` · `game_safe_query` | 组合上述原语 |
| **单位数据层** | 4 | `game_unit_names`（52 内置 + 125 ini）· `game_unit_info`（ini）· `game_unit_dump`（纯文本存档）· `game_victory_check`（日志） | 静态表 / ini / 存档 / 日志 |
| **四对象方法层** | 271 | `debug_*` 67 · `root_*` 155 · `mods_*` 16 · `multiplayer_*` 33 | `script_api.json` |
| **静态小计 / 总计** | **26 / 297** | 静态 26 = 4 + 8 + 2 + 8 + 4；总计 = 26 + 271 | — |

> ⚠️ `mcp/README.md:57-68` 的分类表把 `game_window_info` **同时列入「实时数据层(4)」与「扩展工具(11)」**，
> 相加得 4+11+8+4+271 = **298 ≠ 297**（重计 1）。实际静态工具数 = **26**（4+11+8+4 − 1 重复）。
> 另 `CLAUDE.md` 结构树记「293 工具」亦为旧值。

### 3.3 动态注册规则与类型映射陷阱

```python
# mcp_game_server.py:38
TYPE_MAP = {"String": str, "boolean": bool, "int": int, "float": float, "long": int}
# :1177
pytype = TYPE_MAP.get(t.split(".")[-1], str)     # ← 未知类型回退 str
```

- 参数命名统一为 `arg0..argN`（`:1175-1179`），`__signature__` 由 `inspect.Parameter` 合成（`:1212`）；
- 工具名 = `f"{obj}_{fn}"`（`:1207`），故脚本方法名中的 `$`（内部类）不会出现在工具名里；
- 工具描述 = `f"调用游戏方法 {obj}.{fn}({', '.join(ptypes)}) → {rtype}. ..."` + `DANGEROUS`/`WARN_NOTES` 注记；
- **陷阱**：未知类型 → `str` → `_format_literal` 裸文本（§2.6 H1）。`long` 被映射为 `int`（`:38`），
  与 `_format_literal` 的 `return str(value)` 一致，无精度问题（脚本引擎只有 Integer/Float）。

### 3.4 状态机与崩溃防护层

**状态机**（`GameConnection.state`，`:73`）：`UNKNOWN → STARTING → MAIN_MENU → IN_GAME ↔ REPLAY → CRASHED`

| 机制 | 位置 | 要点 |
|------|------|------|
| 状态推断 | `_update_state`，`:76-95` | 从**响应文本**匹配 6 组关键词（见 [LOGGING.md](LOGGING.md) §5.4） |
| 主动刷新 | `refresh_state`，`:138-141` | 发 `script root.getVersionName()`「期望回带日志」——与「socket 不推日志」冲突，**待验证** |
| 硬拦截 | `_check_call`，`:1147-1167` | `DANGEROUS`(6) + `multiplayer.*`(33) + 状态过滤 |
| 描述告警 | `WARN_NOTES`，`:50-63` | 12 条，不拦截 |
| 单位类型预检 | `_invalid_unit_type`，`:651-664` | 52 内置 + 125 ini，含大小写兜底与相近名提示 |
| 崩溃检测 | `send_raw` 捕获 `OSError` → `[连接重置]`，`:120-122` | → `CRASHED` + `crash_count += 1` |
| 崩溃恢复 | `game_reset`（`:261-269`）→ `game_launch`（`:351-370`） | `_stop_game` 用 PowerShell 兜底清理 `javaw/java` |
| 专家纠正 | `game_force_state`（`:272-284`） | 手动声明 6 态之一 |
| 流程守卫 | `_guard_crashed`，`:976-982` | 流程聚合工具的前置拦截 |

### 3.5 最小可复现调用（**CLI 只读模式，不启动游戏**）

```powershell
$RW = "...\Rusted Warfare\rw-reverse"

python "$RW\mcp\mcp_game_server.py" --list-tools      # 列出 297 工具（只读，安全）
python "$RW\mcp\mcp_game_server.py" --self-test       # 自测（需游戏已运行；加 --launch 会自动启动 → QAV 范围）
# 注意: --launch-game / --stop-game 会操作游戏进程, 属 QAV G-2 工作项范围, 本文不执行。

# DSH 侧当前未注册该 MCP 服务器（mcp_manager_list = none）→ 只能走上述 CLI。
```

---

## ④ 命令行参数（36 token 全表）

### 4.1 解析模型

```java
// 03-deobfuscated/.../java/GameLauncher.java:152-374 (节选骨架)
public synchronized void a(String[] stringArray) {
    GlobalState.e("Reading args");
    String twoPart = null;                       // 两段式参数的「等值」暂存
    for (int i = 0; i < stringArray.length; ++i) {
        String tok = stringArray[i].trim().toLowerCase(Locale.ENGLISH);   // ← 全部转小写后比较
        if (twoPart != null) { /* 消费等值: +connect_lobby / -width / -height */ }
        if (tok.equals("-debug")) { /* 消费下一 token */ }
        ...
        else if (tok.startsWith("+")) { GameLauncher.a("Unknown steam option: " + tok); }
        else if (tok.trim().length() == 0) continue;
        else { GameLauncher.a("Unknown option: " + tok); unknown = ...; }
    }
    GlobalState.e("Game arguments:");
    for (...) GameLauncher.a("arg: " + stringArray[i].trim().toLowerCase(Locale.ENGLISH));  // 全量回显
    if (unknown != null) {
        if (GlobalState.aH) GameLauncher.a("Unknown options but running anyway due to being in steam");
        else { GameLauncher.a("Exiting due to unknown option: " + unknown); System.exit(1); }
    }
}
```

**三条通用性质**：

1. **token 一律 `toLowerCase(ENGLISH)` 后比较** → 参数名大小写不敏感，但**参数值**也被写回 `stringArray[i]` 原值
   （`-lang` 分支除外：它把值再赋给 `string2`）。
2. **未识别 token 且非 `-steam` 模式 → `System.exit(1)`**；`-steam` 模式下仅告警。
3. 解析结束后**回显一次全量参数**：`Game arguments:` 标题 + 逐 token 的 `arg: <token>`（均小写），
   位于 `03-deobfuscated/.../java/GameLauncher.java:375-379`；实测 `{GAME}/lastrun.log:2-8`（6 token → 6 行）。

### 4.2 全表（35 个 `-xxx` + 1 个 `+xxx`）

| flag | 取值 | 03 行 | 落点 | 语义 |
|------|------|-------|------|------|
| `+connect_lobby <id>` | 值 | 176-181 / 363-367 | `GlobalState.aK` | Steam 大厅连接 ID；打 `connect lobby:<id>` |
| `-debug <port>:<host>` | `a:b` | 196-206 | `DebugServer.a(int,String)` | **开调试 socket**（§1） |
| `-debugscript <file>` | 值 | 207-215 | `DebugServer.a(String)` | 脚本文件入队，逐行执行 |
| `-log <file>` | 值 | 216-233 | `System.setOut/setErr(PrintStream)` | **文件日志**（[LOGGING.md](LOGGING.md) §1.1） |
| `-nologfile` | — | 234 | 无 | **空实现** |
| `-lang <code>` | 值 | 235-242 | `Localization.d` | 语言代码 |
| `-logcolor` | — | 243-246 | `GlobalState.ax = true` | ANSI 着色（ERROR 红 / WARN 黄） |
| `-nodisplay` | — | 247-250 | 局部 `bl` | headless：屏幕降为 10×10、不调 `Display` |
| `-width <int>` | 值 | 359-362 / 182-186 | 局部 `n3` | 窗口宽（打 `Overriding width to:`） |
| `-height <int>` | 值 | 359-362 / 187-191 | 局部 `n4` | 窗口高 |
| `-canvasgl` | — | 251-254 | `GlobalState.aD = true` | canvas + OpenGL 后端 |
| `-replay_debug` | — | 255-258 | `GlobalState.aw = true` | 回放调试日志 |
| `-nopreferipv4` | — | 259-262 | 局部 `bl4` | 跳过 `java.net.preferIPv4Stack=true` |
| `-noresources` | — | 263-266 | `GlobalState.aB = true` | 不加载资源 |
| `-nosound` | — | 267-270 | 局部 `bl2` | 无声（→ `NullSoundFactory`） |
| `-nomusic` | — | 271-274 | 局部 `bl3` | 无音乐（→ `PingTimer`） |
| `-safemode` | — | 275-278 | `GlobalState.aO = true` | 安全模式 |
| `-extrasafemode` | — | 279-282 | `GlobalState.aP = true` | 加强安全模式 |
| `-disable_vbos` | — | 283-286 | 局部 `bl7` | 禁 VBO |
| `-disable_atlas` | — | 287-290 | `GlobalState.aC = true` | 禁图集 |
| `-force_vbos` | — | 291-294 | 局部 `bl8` | 强制 VBO（mac 上默认禁用） |
| `-allowsoftwarerender` | — | 295-298 | 局部 `bl5` | 置 `org.lwjgl.opengl.Display.allowSoftwareOpenGL=true` |
| `-fullscreen` | — | 299-302 | 局部 `bl6` | 全屏 |
| `-nobackground` | — | 303-306 | `GlobalState.ay = true` | 无背景 |
| `-nomods` | — | 307-310 | `GlobalState.aJ = true` | 不加载 mod |
| `-printunits` | — | 311-314 | `GlobalState.aE = true` | 打印单位 |
| `-outputunitimages` | — | 315-318 | `GlobalState.aF = true` | 输出单位图像 |
| `-oldreplays` | — | 319-322 | `GlobalState.aG = true` | 旧回放兼容 |
| `-teamshaders` | — | 323-326 | `GlobalState.aN = true` | 队伍着色 shader |
| `-noteamshaders` | — | 327-330 | `GlobalState.aN = false` | 关队伍着色 |
| `-devdebug <val>` | 值 | 331-338 | `GlobalState.aQ` | 开发者调试串（缺参报错文案误写为 `-debugscript requires parameters`） |
| `-postprocessing` | — | 339-342 | `GlobalState.aM = true` | 后处理开 |
| `-nopostprocessing` | — | 343-346 | `GlobalState.aM = false` | 后处理关 |
| `-disabletextureread` | — | 347-350 | `SlickTexture.F = false` | 禁纹理读回 |
| `-sandbox` | — | 351-354 | `GlobalState.aI = true` | 沙盒模式 |
| `-steam` | — | 355-358 | `GlobalState.aH = true` | Steam 初始化（`Early steam init`） |

> 复现提取：见 §7-2。`equals("...")` 共 36 个 token；`-width`/`-height` 在代码中出现两次（取值消费 + 标记），token 数仍各计 1。

### 4.3 QAV / MCP 现行启动命令（基线口径）

```powershell
# QAV 回放/验证口径（带 UTF-8 与显式日志文件）
& "$GAME\jvm64\bin\java.exe" -Xmx2000M -Dfile.encoding=UTF-8 -Djava.library.path=. `
   -cp "game-lib.jar;libs/*" com.corrodinggames.rts.java.Main `
   -nodisplay -debug 5677:local -log lastrun-replay-<件名>.log

# QAV GUI 口径（本任务硬约束：禁 -nodisplay）
& "$GAME\jvm64\bin\java.exe" -Xmx1000M -Dfile.encoding=UTF-8 -Djava.library.path=. `
   -cp "game-lib.jar;libs/*" com.corrodinggames.rts.java.Main -width 1024 -height 768 -log lastrun.log

# MCP 口径（注意：**无 -log、无 -Dfile.encoding**，stdout 由 OS 重定向到 game-mcp.log）
# 见 mcp_game_server.py:163-175
```

---

## ⑤ 网络协议 API

本节**不重复**协议内容，只给层级索引与入口类；协议细节见
[../06-network/NETWORK-PROTOCOL.md](../10-network/NETWORK-PROTOCOL.md) 与 [../06-network/NETWORK-STACK.md](../10-network/NETWORK-STACK.md)。

### 5.1 四层索引与入口类

| 层 | 03 包 / 关键类 | 02b 混淆名 | 协议要点 | 深读位置 |
|----|---------------|-----------|---------|---------|
| **L1 可靠 UDP 传输** | `network.reliableudp.core.*`（`Packet`/`AckPacket`/`DataPacket`/`ExtendedAckPacket`/`FinPacket`/`NullPacket`/`ResetPacket`/`SynPacket`/`TaskRunner`，9 类）<br>`network.reliableudp.ReliableSocket` / `ReliableServerSocket` / `ReliableClientSocket`（19 类） | `a/a/a/*`、`a/a/*` | 类 UDT/KryoNet：标志位 `0x40=ACK`/`0x20=EAK`/`0x10=RST`/`0x08=NUL`/`0x02=FIN`/`0x80=SYN`；`Packet.b(byte[],int,int)` 工厂反序列化 | NETWORK-STACK.md §1；NETWORK-PROTOCOL.md §10 |
| **L2 包编解码** | `gameFramework.network.InputNetStream` / `OutputNetStream` / `PacketDecoder` / `PacketSerializer` / `NetworkPacket` / `PacketHandler` / `PacketType` / `CompressedStream` | `j/k`、`j/o`、`j/c` 等 | 线路格式 `[4B len BE][4B type BE][payload]`；**类型 ≤100 = 游戏包（tick 队列），>100 = 系统包（立即处理）** | NETWORK-PROTOCOL.md §2、§6 |
| **L3 网络引擎（状态同步）** | `gameFramework.network.NetEngine`（02b `j/ad`，5,502 行）· `PlayerConnect`（`j/c`）· `MatchConfig`（`j/ah`）· `ChecksumCalculator`/`ChecksumField` | `j/ad`、`j/c`、`j/ah` | 确定性锁步：服务器权集 + 客户端锁步；前看窗口 `Q`；每 ~150 帧校验和；30 种游戏/系统包 | NETWORK-PROTOCOL.md §1、§3、§4、§5；PERFORMANCE-LAG-ANALYSIS.md |
| **L4 主服务器 / Web API** | `MasterServerClient` / `MasterServerAuth` / `MasterServerCreate` / `MasterServerRemove` / `MasterServerUpdate` / `MasterServerResult` / `WebAPIClient` / `HttpClientPool` / `ServerListFetcher` / `ServerListLoader` | — | HTTP 主服务器（房间列表/创建/心跳）+ 令牌认证 | NETWORK-PROTOCOL.md §11 |

### 5.2 最小可复现调用（只读索引，不发起连接）

```powershell
$RW = "C:\Users\28210\Downloads\Rusted Warfare\rw-reverse"
$JAR = "$RW\RustedWarfare\game-lib.jar"
$JAVAP = "C:\Users\28210\Downloads\Rusted Warfare\jvm64\bin\javap.exe"

# 入口类方法面
& $JAVAP -p -classpath $JAR com.corrodinggames.rts.gameFramework.network.NetEngine | Select-Object -First 40

# 包类型目录
Select-String -Path "$RW\03-deobfuscated\com\corrodinggames\rts\gameFramework\network\PacketType.java" -Pattern "^\s+[A-Za-z_]" | Select-Object -First 40

# 协议文档层级索引
Select-String -Path "$RW\docs\06-network\NETWORK-PROTOCOL.md" -Pattern "^#{1,3} "
```

> 联机相关**不做运行时调用**（无授权网络对局语境，且 `multiplayer.*` 在单机场景会崩）——本项仅做静态索引。

---

## ⑥ `script_api.json` 两套副本裁决（★ 任务 3）

### 6.1 逐项比对结果

| 文件 | 路径 | 字节 | SHA256[:16] | 对象 | 方法数 |
|------|------|------|-------------|------|--------|
| **A** | `tools/capture/script_api.json` | 14,756 B | `2ee5711da1cb3d6d` | `debug`, `root` | **222** |
| **B** | `tools/capture/script_api.json` | 17,418 B | `48e3b6f3d6f4376d` | `debug`, `root`, `mods`, `multiplayer` | **271** |

复现命令：

```bash
python -c "import json,hashlib;d=json.load(open('tools/capture/script_api.json',encoding='utf-8'));print({k:len(v) for k,v in d.items()})"
python -c "import json,hashlib;d=json.load(open('tools/capture/script_api.json',encoding='utf-8'));print({k:len(v) for k,v in d.items()})"
```

| 差异类别 | 数量 | 代表性条目 |
|---------|------|-----------|
| **仅 A 有** | **0** | —（无任何 A 独有方法） |
| **仅 B 有** | **49** | **整个 `mods` 对象（16 条）**：`openWorkshop` `uploadModAsk` `uploadMod` `viewMod` `deleteModPopup` `deleteMod` `setModFilter` `applyModFilter` `updateMods` `refreshModList` `loadMods` `saveMods` `disableAllAsk` `disableAll` `reloadModDataAsk` `reloadModData`<br>**整个 `multiplayer` 对象（33 条）**：`multiplayerStart` `refreshUI` `loadUsername` `addAI` `playerConfig_kick` `playerConfig_apply` `showPlayerConfig` `askPassword` `surrender` `disconnect` `battleroomSetup` … |
| **同名不同签名** | **0** | — |
| **共享子树（debug 67 + root 155）** | 逐项**完全一致** | `json.dumps(..., sort_keys=True)` 相等；且**键插入顺序亦逐一相同** → 非「漂移」，是**严格超集** |

> **结论（与任务书 §2.2-1 的假设不同）**：这不是「两套副本漂移」，而是 **B = A + 49 条扩展（零签名分歧）**。
> `mcp/README.md` 自述与自述之间的矛盾，是 **README 文本陈旧**，不是**数据不一致**。

### 6.2 权威裁决：**B（`tools/capture/script_api.json`）为权威**

| 判据 | A（`tools/capture/`） | B（`mcp/`） | 胜出 |
|------|---------------------|------------|------|
| **是否为实际被执行的数据** | 仅 `debug_client.py:20` 读取（人工 CLI 调用） | `mcp_game_server.py:32` 读取，并被 `register_all_tools()`（`:1216-1221`）**直接生成 271 个工具** | **B** |
| **覆盖面** | 2 对象 / 222 方法 | **4 对象 / 271 方法**（含 `mods`、`multiplayer`） | **B** |
| **与实测工具数一致性** | 222 → 与 297 不符 | 271 + 26 静态 = **297**（`--list-tools` 实测 297 行） | **B** |
| **抽取来源标注** | 无（生成器缺失） | 有（`mcp/README.md:71`：「02b 源码提取」；且与 §2.2 反射口径一致） | **B** |
| **是否可复现生成** | ✗ 无生成器、无来源声明 | ✗ **同样无生成器**（手写/一次性产物） | 平手（**两者均缺可复现性**） |

补充：A 的**唯一**存在理由是为 `tools/_archive/campaign/capture/debug_client.py` 提供「签名白名单 + 参数构造」
（该文件第 68-75 行做白名单与参数个数校验）——**功能上是 B 的 `debug`+`root` 子集**。

### 6.3 同步方案（**只给方案，本工作项不动手改**）

| 方案 | 做法 | 优点 | 代价 | 建议 |
|------|------|------|------|------|
| **S1（推荐）统一生成器** | 新增 `tools/_archive/campaign/utils/extract_script_api.py`（REV/DEV 后续工作项）：以 `game-lib.jar` 为真源，用 `javap`/反射口径（§2.2）导出 4 对象全量 → **直接生成 B**；同时以 `--objects debug,root` **生成 A** 作为 2 对象投影 | ① 与「01-classes/ jar 是唯一真源」核心约束一致；② 消除手工维护；③ 保留 `debug_client.py` 的隔离性（A 仍在 `tools/capture/`）；④ 可进 CI 校验 | 需写一个抽取器（签名解析要处理 `$` 内部类与数组） | ✅ **首选** |
| S2 单副本 + 引用 | 删除 A；`debug_client.py` 改读 `../tools/capture/script_api.json` | 一步去重 | 破坏方向性隔离：`mcp/` 自述「完全自包含、与项目隔离」（`mcp/README.md:5`），反向让 `tools/` 依赖 `mcp/` 产物，耦合方向混乱 | ❌ 不推荐 |
| S3 保留双副本 + 守卫 | A、B 均保留，加一条 CI/自检：断言 `A == projection(B, ["debug","root"])` 且 B 的共享子树键序一致 | 零结构改动，立即防漂移 | 漂移根因（无生成器）仍在 | ⭕ 作为 S1 的**过渡护栏**可接受 |
| S4 文档订正（无论选哪个都必做） | 修正 `mcp/README.md` 3 处陈旧表述：`:12`「222 方法签名白名单（自带的独立副本）」→ 应为 **271 方法（4 对象）**；`:126`「协议依赖 tools/capture/…script_api.json (222 方法)」→ 服务器实际读 **本目录 `script_api.json`**；`:57-68` 分类表 `game_window_info` 重复计数（298→297） | 消除自相矛盾 | 无 | ✅ **必做** |

**推荐组合：S1（生成器）+ S4（文档订正）；S3 作为 S1 落地前的临时守卫。**
两套 json 的后续处置**已登记 PENDING**（见 §7 末尾引用）。

### 6.4 顺带发现：两套副本均无生成器

- 全库检索未见任何生成 `script_api.json` 的脚本（`tools/cli.py` 注册表 616 项，无相关条目）。
- 按 §2.2 的反射口径，**生成是可行的且确定性的**（`getMethods()` 顺序问题需以「按类声明顺序排序」显式消解，
  否则重载去重的保留项不确定）。
- → 这一点也解释了为何 A 停在 222：它是在 `mods`/`multiplayer` 被加入脚本引擎**之前**的快照，之后只更新了 B。

---

## ⑦ 复现命令汇总

```powershell
$GAME = "C:\Users\28210\Downloads\Rusted Warfare"
$RW   = "$GAME\rw-reverse"
$JAR  = "$RW\RustedWarfare\game-lib.jar"
$JAVAP = "$GAME\jvm64\bin\javap.exe"

# 1) 两套 script_api.json 差异（§6.1）
python -c "import json;A=json.load(open(r'$RW\tools\capture\script_api.json',encoding='utf-8'));B=json.load(open(r'$RW\mcp\script_api.json',encoding='utf-8'));print('A',{k:len(v) for k,v in A.items()},'B',{k:len(v) for k,v in B.items()});[print(o,'onlyA',sorted(set(A[o])-set(B[o])),'onlyB',sorted(set(B[o])-set(A[o])),'sigdiff',[k for k in A[o] if k in B[o] and A[o][k]!=B[o][k]]) for o in ('debug','root')]"

# 2) 36 个 CLI flag token（§4.2）
Select-String -Path "$RW\03-deobfuscated\com\corrodinggames\rts\java\GameLauncher.java" `
  -Pattern 'equals\("([-+][^"]+)"\)' -AllMatches |
  ForEach-Object { $_.Matches } | ForEach-Object { $_.Groups[1].Value } | Sort-Object -Unique

# 3) MCP 工具计数与构成（§3.1）
python "$RW\mcp\mcp_game_server.py" --list-tools | Measure-Object -Line        # 297
python "$RW\mcp\mcp_game_server.py" --list-tools | Select-String -Pattern "^(debug|root|mods|multiplayer)_" |
  Group-Object { ($_ -split "_")[0] } | Select-Object Name,Count

# 4) Debug Socket 协议：命令分派与终止符（§1.3/§1.4）
& $JAVAP -p -c -classpath $JAR com.corrodinggames.rts.a.a |
  Select-String -Pattern "String (ping|pong|crash|script|function|ok|done|unknown|\\\\u0000|<NULL>|test crash|argString)"
python -c "import zipfile;d=zipfile.ZipFile(r'$JAR').read('com/corrodinggames/rts/a/a.class');print('emptyC#1@',d.find(b'\x01\x00\x00'),'NUL#2@',d.find(b'\x01\x00\x02\xc0\x80'),'space#3@',d.find(b'\x01\x00\x01\x20'))"

# 5) ScriptEngine 对象注册条件（§2.1）
& $JAVAP -p -c -classpath $JAR com.corrodinggames.librocket.scripts.ScriptEngine |
  Select-String -Pattern "setGlobalVariable|isStrict|createScriptEngine" -Context 1,1

# 6) getLocalPlayerId 的 NPE 成因（§2.5 成因 B）
& $JAVAP -p -c -classpath $JAR com.corrodinggames.librocket.scripts.Debug |
  Select-String -Pattern "getLocalPlayerId" -Context 0,10

# 7) 不可脚本化的 17 个 root 方法（§2.6）
python -c "import json;P={'String','boolean','Boolean','int','float','long','double','short','char','void'};B=json.load(open(r'$RW\mcp\script_api.json',encoding='utf-8'));n=[(f,p) for ms in B.values() for f,(r,p) in ms.items() if any(t.split('.')[-1] not in P for t in p)];print('count',len(n));[print(' ',f,p) for f,p in n]"
```

---

## ⑧ 已知矛盾 / 待验证 / PENDING 登记

| # | 项 | 事实 | 状态 |
|---|----|------|------|
| M1 | `mcp/README.md:12` 称 `script_api.json` 为「222 方法签名白名单（自带的独立副本）」 | 实测 **271 方法 / 4 对象**，且**不是副本**而是**严格超集** | 待 KBC/DEV 订正文档（§6.3 S4）· PENDING **#19** |
| M2 | `mcp/README.md:126` 称「协议依赖 tools/_archive/campaign/capture/debug_client.py + script_api.json（222 方法）」 | 服务器实际加载 `MCP_DIR/script_api.json`（`mcp_game_server.py:32`），**不依赖 tools/ 任何文件** | 同上 · PENDING **#19** |
| M3 | `CLAUDE.md` 结构树记 `mcp_game_server.py` **293 工具** | 实测 `--list-tools` = **297** | 口径订正（D1 范围）· PENDING **#19** |
| M4 | `mcp/README.md:57-68` 分类表求和 298，`game_window_info` 重复计入 | 静态工具实为 **26** | 同上 · PENDING **#19** |
| M5 | `mappings/class-discoveries.csv:147` 与 `:735` **两个不同类都命名为 `DebugServer`** | `rts/a/a`（真实现）与 `gameFramework/c/a`（桩）；`-debug` 只走前者 | **待处置**（REV 映射范围）· PENDING **#20** |
| M6 | 03 `commands/DebugServer.java` 内容来自 `gameFramework/c/a`，而 `GameLauncher` 调用的却是 `commands.DebugServer`（映射自 `rts/a/a`） | 按名检索会取到非 `-debug` 路径的类 | 与 M5 同批处置 · PENDING **#20** |
| M7 | MCP 三套客户端**均不使用** `function` 通道，导致「无法取返回值」被误认为协议限制 | 协议层**有**该通道（§2.4） | **待处置**· PENDING **#21** |
| M8 | 17 个对象参数 root 方法在 MCP 中可调用但必然失败（§2.6 H1） | 成因链前 3 环有源码证据，末环「崩溃」按 MCP 实测口径 | **待处置**（建议入 `_check_call`）· PENDING **#22** |
| V1 | `debug.getPlayerName(int)` 的 MCP「固有 NPE」结论与源码保护逻辑不符 | 见 §2.5 成因 B | **待验证** · PENDING **#23** |
| V2 | `debug.plainTextDebugSave(boolean)` 自身只写静态开关，MCP 的「主菜单调用会崩」成因待定 | 见 §2.5 成因 B | **待验证** · PENDING **#24** |
| V3 | `function` 通道在真实对局中是否可用、是否真比 `script` 安全 | §2.4 末段 | **待验证** · PENDING **#25** |
| V4 | Debug Socket 单连接串行（`Thread.run()` 误用）在现象层是否可观测（长命令期间并发 `ping` 被阻塞） | §1.6 | **待验证** · PENDING **#33** |

> 上述 M1–M8 / V1–V4 **全部**已登记 PENDING「2026-09-21 登记 (api-log-inventory G-1, REV)」（#18–#33）；
> ⑥ 两套 `script_api.json` 的裁决与同步方案见 PENDING **#18**（裁决完成，处置待落地）。

---

## ⑨ 交叉引用

- [LOGGING.md](LOGGING.md) — 日志开关 / 日志 API 层 / 输出点七类 / MCP 三个日志接口 / 文件位置
- [DEBUG-FEATURES.md](DEBUG-FEATURES.md) — 调试键位 / Debug 对象 67 方法 / 运行时三通道 / 5677 服务器
- [../06-network/NETWORK-PROTOCOL.md](../10-network/NETWORK-PROTOCOL.md) · [../06-network/NETWORK-STACK.md](../10-network/NETWORK-STACK.md) — ⑤ 网络协议的深读位置
- [../STATUS.md](../STATUS.md) — 全局口径唯一来源
- PENDING — 本文 §⑧ 的登记处
- `../../mcp/mcp_game_server.py` · `../../tools/capture/script_api.json` · `../../tools/_archive/campaign/capture/debug_client.py` · `../../tools/utils/debug_script.py` — ③ 的工具实现
