# tools/rig/ — 游戏完全体·测试台工具（P2）

> 目的：让「游戏本体 + 逆向产物」成为一个**可运行、可复现、可回滚、可 A/B**的测试台，
> 而不是一堆散落的 jar 与手工替换命令。本目录三段式：**生成清单 → 校验现场 → 切换形态**。

## 一、测试台在哪里

| 概念 | 路径 | 说明 |
|---|---|---|
| `{GAME}` 游戏根 | 本仓的**上一级**目录 | 含 `Rusted Warfare - 64.exe`、两套 JRE、`libs/`、`assets/`、`res/`、`font/`、DLL |
| 部署态 jar | `{GAME}/game-lib.jar` | 游戏实际加载的那个，由切换器写入（**禁止手工覆盖**） |
| jar 库 | `{GAME}/jars/` | `stock.jar` / `d3-era.jar` / `current.jar` + `_deployed-prev.jar`（切换前自动备份） |
| 现场清单 | `{GAME}/MANIFEST.json` | 实测生成：三 jar 的 SHA/条目数、当前部署态、哨兵、门禁结果、已知问题 |
| 启动器 | `{GAME}/*.bat` | 本目录 `launcher/` 的副本（源在这里，根目录是部署副本） |

## 二、三个形态

| profile | SHA256（前 16） | 条目 | 定位 |
|---|---|---|---|
| `stock` | `8A550A37E2D8A543` | 1,699 | 原版 v1.15 —— 编译目标 / 地面真值 / 回滚基准 |
| `d3-era` | `6D47AA735A3748EF` | 1,850 | D-3 期产物（2026-08-31）：**回放校验和 `don't match` 7→0 已验**的那版 |
| `current` | `F82B39B9071B4B5A` | 1,777 | H-9 产物（2026-09-24）：当前主线，**尚未做回放回归（P4）** |

> `current` 比 `d3-era` 少 73 个新增类，正是 D-7 的 `java/p$2..p$72`（71 个）+ `animation/n$2` + `units/i$a`。
> 该差异**未被任何文档记录**，也未经行为验证 —— 这正是保留 `d3-era` 与一键切换的原因。

## 三、命令

```bash
# 只看当前部署与可选形态（只读）
python tools/rig/switch_jar.py --list

# 预演切换（默认 dry-run，不落盘）
python tools/rig/switch_jar.py --to stock

# 实际切换（会备份当前态到 jars/_deployed-prev.jar 并更新 MANIFEST.json）
python tools/rig/switch_jar.py --to stock --apply

# 校验现场是否与 MANIFEST.json 一致（只读；退出码 0=全通过）
python tools/rig/verify_rig.py
python tools/rig/verify_rig.py --quiet      # 只报失败项
python tools/rig/verify_rig.py --json       # 机器可读

# 重新生成清单（例如门禁复跑后、切换后想刷新统计）
python tools/rig/make_manifest.py
python tools/rig/make_manifest.py --print   # 只打印
```

游戏根本机为 `C:\Users\28210\Downloads\Rusted Warfare`；如在别处使用，设 `RW_GAME_ROOT` 覆盖。

## 四、根目录启动器（本目录 `launcher/` 为源，根目录为副本）

**只保留 2 个调度器**（2026-09-24 由 5 个独立 bat 收敛而来，避免"一堆启动器"）：

| 文件 | 模式 / 命令 | 用途 |
|---|---|---|
| `启动游戏.bat` | **无参数 / `console`** | ★ **带实时日志窗口启动**：用 `jvm64\bin\java.exe` 在当前控制台前台运行，游戏 stdout 实时可见 |
| | `debug` | 同上 + 调试端口 5677（`Main -debug 5677:local`），供 MCP 桥接；**不加 `-nodisplay`** |
| | `file` | 走 `-log lastrun-bat.log` 写文件（**控制台只剩极少量早期输出**，见下方说明） |
| | `overlay` | 实验：`-cp "patch;game-lib.jar;libs/*"` 前置运行时覆盖层（58 类，见 `RUNTIME-PATCH-PIPELINE.md`），带日志窗口 |
| | `stock` / `d3-era` / `current` | 先切 jar 形态，再带日志窗口启动 |
| | `exe` | 走官方 `Rusted Warfare - 64.exe`（**无日志窗口**：其 java 子进程是 `javaw`，不接控制台） |
| `测试台.bat` | `verify`（默认） | 现场校验 35 项判据（只读） |
| | `list` | 列出 jar 形态与当前部署 |
| | `switch <形态>` | 切换 jar（等价 `switch_jar.py --to … --apply`） |
| | `manifest` | 重新生成 `MANIFEST.json` |
| | `smoke` | 真实 GUI 启动冒烟（`smoke_test.py --wait 40`） |
| | `live` | 实时对局数据面板（`live_stats.py --unlock --append`） |
| `启动游戏.bat` | `verbose` | ★ **日志监管器**：分类上色 + 全量归档到 `{GAME}/logs/` + 每 15s 数据摘要 + 崩溃判据（`run_game.py`） |

**实时日志窗口（2026-09-24 新增）**：

- **为什么改用 `java.exe` 而不是官方 exe**：官方 exe 派生的游戏进程是 `javaw.exe`（GUI 子系统，没有控制台），
  无法附加日志窗口；而 `jvm64\bin\java.exe` 会把游戏的 `System.out` 直接写到当前控制台 —— 这正是
  **原版自带 `fallback64.bat` 的做法**（开发者认可的启动路径）。
- **为什么默认不带 `-log`**：`-log <文件>` 会把 `System.out` 重定向到文件（`GameLauncher.java:216-227`），
  一旦带上就没有实时输出了。要留档请用 `file` 模式或 `debug` 模式 + MCP 读 `game-mcp.log`。
- 窗口标题由 `rig_help.py banner` 用 `SetConsoleTitleW` 设为 **「Rusted Warfare — 游戏实时日志」**（UTF-16，
  不受代码页影响）；启动时先打印横幅（当前 jar 形态 / SHA / 条目数 / 门禁结果）。
- 游戏退出后窗口**保留并等待按键**，方便回看最后几行；**关闭窗口即结束游戏**（前台进程）。
- 实测（2026-09-24）：`启动游戏.bat console` 启动后首次采样即含当刻时间戳的日志行（启动横幅 → 资源/CSS 加载
  → MissionEngine 事件，约 391 行 / 24 KB）；游戏主菜单空闲期间无新行属正常。同时可见
  控制台窗口标题「Rusted Warfare — 游戏实时日志」与游戏窗口「Rusted Warfare」并存。

> 另有 3 个**原版发行自带**的 `fallback.bat` / `fallback64.bat` / `fallback_lowmem.bat`（官方 JRE 回退启动），
> 原样保留、未被本项目修改。`launch_with_engine.bat`（只服务 Python 仿真引擎）已移到
> `{GAME}/refs/derived/rw_engine/`。

**编码约定（踩过两次坑）**：
**编码约定（2026-09-24 统一为 UTF-8）**：
- `.bat` 以 **UTF-8 with BOM + CRLF** 落盘（`.gitattributes` 设 `*.bat -text`，防 git 改动 BOM 与行尾）；
  **BOM 让 cmd 在 Win10+ 直接按 UTF-8 解析**，因此 bat 内可自由使用中文 `echo` / `rem` / `title`；
- bat 在 `@echo off` 之后立即 `chcp 65001 >nul`，把当前控制台切到 UTF-8（本来就是 65001 时为空操作）；
- bat 内 `set PYTHONIOENCODING=utf-8`：Python 输出确定为 UTF-8，不再依赖控制台码页探测；
- Java 侧显式 `-Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8`：
  游戏 stdout 与 `-log` 文件均为 UTF-8（与本仓各工具按 utf-8 读日志的约定一致）；
- 仓目录探测用**显式候选**（`rw-reverse` → `rw源码逆向`）；重命名后无需改启动器。
- ⚠️ **历史教训（勿回退）**：UTF-8 **无 BOM** 的 bat + 中文，曾被 cmd 按 GBK 解析而报
  `'"' is not recognized` / `'do' is not recognized`；**加 BOM 后消失**（2026-09-24 实测）。
  用 macOS/Linux 工具编辑这些 bat 时务必保留 BOM，否则请改用仓库脚本重写。

## 五、实时对局数据与日志详细度（2026-09-24 新增）

```bash
启动游戏.bat verbose     # = 日志监管器：调试端口 5677 + -logcolor + -replay_debug + -debugscript，
                         #   逐行上色打印 + 全量归档 {GAME}/logs/run-<时间戳>.log + 每 15s 数据摘要
                         #   + 退出时崩溃判据摘要（扫 onGameCrash/uncaughtException start/OOM）
python tools/rig/run_game.py --sidebar 15 --filter 失步   # 也可单独跑监管器
测试台.bat live          # 或单独打开实时数据面板（逐行追加，便于回看）
python tools/rig/live_stats.py --once --json       # 单次采样（机器可读）
python tools/rig/live_stats.py --log data.jsonl    # 每次采样追加一行 JSONL，便于事后分析
python tools/rig/live_stats.py --network-debug     # 顺带开 debug.enableExtraNetworkDebug()
```

`live_stats.py` 走 Debug Socket 的 **`function` 通道**（服务端 `tryToCatchCrash=true` → 坏表达式只回
`crash`，**不崩游戏**），并且**每次采样复用一条连接**：`DebugServer` 的 accept 循环是**串行**的
（PENDING #33「会话级阻塞」实测 6.6 s），若每条命令各开一条连接，每秒几十次连接会阻塞其它客户端
（MCP 工具面）。实测采样成本 ≈ 47 ms/表达式往返，默认 12 个全局项 + 每队 3 项。

**实测可取的实时对局数据**（current 形态，2026-09-24）：版本 / 当前界面（`mainMenu.rml` 等）/
使用 Mod / 进程 PID / 联机中 / 人类玩家 / 玩家+AI / 连接数 / 失步错误 / 失步校验通过 / 重同步收发 /
自定义单位类型 / **每队 被消灭·被击败·胜利**；工具会做差分并打印事件（失步计数上升、队伍被消灭、
界面切换、人数变化）。

**本版「日志详细度」有效 / 无效开关清单**（避免白折腾，均实测或源码实证）：

| 开关 | 结论 | 依据 |
|---|---|---|
| `-logcolor` | ✅ 有效（控制台输出出现 ANSI 彩色码） | 实测输出含 `\x1b[` |
| `-replay_debug` | ✅ 有效（`GlobalState.aw`，3 处读取：ReplayEngine/ActionPanel/CustomUnitType） | 源码 |
| `-debug <port>:local` | ✅ 打开调试服务并输出相关日志 | 实测 |
| `-debugscript <文件>` | ✅ 启动即逐行执行，并把 `Running debug script:` / `Running: <行>` / `got: <结果>` 写进日志 | 实测 |
| `debug.enableExtraNetworkDebug()` | ✅ `NetEngine.g=true`（联机/不同步排查看这个） | 实测 `ok true` |
| `-printunits` / `-outputunitimages` / `-devdebug` | ❌ **死开关**：全库只有 `GameLauncher` 有写入点，**无任何读取点** | 全库 grep |
| `debug.enableFeatures(...)` | ❌ **哈希口令门**：入参 SHA-256 十六进制前缀须等于 `221FC410BD29D786`（64 bit），无原像不可解锁；失败抛异常 | product 字节码 + 实测 |
| `debug.getLocalPlayerId()` | ⚠️ 实测 `InvocationTargetException`（与既有 NPE 结论一致） | 实测 |

⚠️ 脚本通道（`-debugscript` / `script`）**没有崩溃保护、且不支持字符串字面量**：写错函数名会在启动阶段
直接崩游戏；`root.logDebug("带空格的串")` 会被当成变量名而无效。三条约束与正确用法写在
`tools/rig/verbose-script.txt` 顶部。

## 六、硬约束

1. **换 jar 必须重跑回放回归**：门禁只保证「成员名 + 产物完整性」，不保证行为（KB-D/E/F）。
2. `jars/*.jar` 与 `MANIFEST.json` 是本测试台的地面真值，**不要手工改**；要换形态走 `switch_jar.py`。
3. `repo/build-skip.txt` 是哨兵，会话期内只读（U-10 红线）；`verify_rig.py` 会校验其 SHA。
4. **`-log` 必须带文件名**（`GameLauncher.java:216-221`）：裸 `-log` → 打印 `-log requires parameters`
   并 `System.exit(1)`，表现为「无窗口 / 无端口 / 无日志」的**假故障**（本目录 2026-09-24 实测踩到）。
   ⚠️ 且 `-log <文件>` 会**重定向 `System.out` 到文件** → 想边玩边看实时日志就**不要**带 `-log`
   （默认 `console` 模式即因此不带它；要留档用 `file` 模式）。
5. `.bat` 采用 **UTF-8 with BOM + `chcp 65001`**（2026-09-24 统一 UTF-8）：BOM 让 cmd 正确解析中文，
   `chcp` 把控制台切到 UTF-8。⚠️ **必须带 BOM** —— 无 BOM 的 UTF-8 bat 曾被 cmd 按 GBK 解析而报
   `'"' is not recognized` / `'do' is not recognized`（实测）；控制台码页、Python 输出与 Java stdio
   的编码见 §四「编码约定」。
6. 启动冒烟用 `python tools/rig/smoke_test.py`（真实 GUI + 端口 5677 + LWJGL 窗口 + 日志崩溃判据 +
   自动终止）；它**不是**回放回归。
7. **注意**：`preferences.ini` 里保留了上次的回放/存档状态，游戏**启动时会自动加载回放**
   （日志可见 `--Now loading:ReplayEngine`）。因此「启动冒烟」会顺带走过回放加载路径，但**不等于**
   完整回放校验和回归（后者要求跑完全部 372 帧校验点，即 P4）。
   > 2026-09-24 实测：`smoke_test.py --wait 40` 在 `current`(1,777 条目) 形态下 `ok=true` ——
   > 主菜单渲染 (`mainMenu.rml`/`*.rcss` 加载) + 端口 5677 + 可见 LWJGL 窗口 + 0 崩溃标记，
   > 终止耗时 0.86 s（PID 消失 0.46 s / 端口释放）。
