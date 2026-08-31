# 动态测试 — 启动游戏获取运行时逆向证据

> v19.97 | 2026-08-16 | 静态分析边际收益递减后的新证据源
> ⚠️ 证据时点 v19.98 — 本日志为当时值; 平台方法学仍可复用 (启动命令/日志采集/脚本链路)
> v19.96-19.97: 回放保存平台建成 + v96 命令格式完全破解 + mods 启用之谜解开

## 1. 原理

游戏本身可完整启动 (v1.15, Build #28, LWJGL 2.9.3, Slick #84)。运行时输出 = **T0 级动态证据**:

- `-log` 日志含游戏阶段字符串 (与常量池字符串交叉 → 宿主混淆类定位)
- 崩溃堆栈 `类.方法(SourceFile:N)` + LineNumberTable 95.5% 保留 → 精确映射 02 源码行
- `-printunits` 输出运行时单位注册表 (42 个内部单位键)
- 主服务器通信/崩溃上报流程的真实网络行为

## 2. 启动手册

**环境**: 项目副本 `RustedWarfare/` (隔离真实安装);启动器用真实安装的 `jvm64/bin/java.exe` (项目副本 jvm 目录缺 java.exe, 仅 DLL);缺的资产已补: `assets/translations/` (Strings.properties 必需) + `assets/shaders/`。

```bash
cd rw源码逆向/RustedWarfare
"<真实安装>/jvm64/bin/java.exe" -Xmx1000M -Dfile.encoding=UTF-8 -Djava.library.path=. \
  -cp "game-lib.jar;libs/*" com.corrodinggames.rts.java.Main \
  -nodisplay -nomods -printunits -log "../docs/generated/runtime-logs/<轮次>.log"
```

**要点**:
- `-nodisplay`: 跳过显示模式 (TargetDisplayMode 10x10), 渲染循环仍运行
- `-printunits`: 打印单位注册表 (unit:XXX 键 + 中文帮助 HTML) 后**正常退出 (exit 0)** — 最佳采集轮次
- 缺失资产导致崩溃 = 证据: "Root locate file:Strings.properties is missing" → 需 translations; "document==null" → 需 shaders
- 崩溃时自动向 gs1.corrodinggames.com 上报 (SendErrorReport 流程本身是证据)

## 3. 已采集证据 (docs/generated/runtime-logs/)

| 轮次 | 文件 | 收获 |
|------|------|------|
| 基线 1 | rw-dyn-baseline.log (97行) | Strings 缺失崩溃路径 + 崩溃上报流程 + 字体管线 |
| 基线 2 | rw-dyn-baseline2.log (399行) | 完整启动 10 阶段 + slickToAndroidCodes/gdxToSlickCodes 键码映射输出 + document==null 崩溃堆栈 |
| printunits | rw-dyn-printunits.log (914行) | **42 单位键 + 中文单位帮助 HTML** + C++ 原生回调 ("Hello World from C++!") + exit 0 |

## 4. 证据消化

`tools/utils/runtime_evidence.py` → `mappings/generated/runtime-evidence.csv` (338 行):

- **42 unit-key**: NukeLaucher/AntiNukeLaucher/airFactory/attackSubmarine... — 内置单位内部键, 供 units.* 域命名
- **275 phase-string**: 19 条有静态宿主:
  - `========= Mods data loaded ===========` → custom.ag — **运行时确认 v19.88 Rule E 映射 (ag=ModLoader)** ✅
  - `iniParse:/iniClose:/actionParse:/imageLoad: Nms` → custom.ah — 每单位 INI 加载计时 (新证据, 待 T1 命名)
  - `------- createIndex -------` → utility.i — 资产索引构建
- **21 stack-frame**: java.u.render(1382)/java.b.gameLoop(146) 等 → 02 定位全存在

## 5. v19.91 第二轮: mod 加载管线 + 单位图像导出

### mod 加载管线全程追踪 (设计者说明: 单位/mod/地图同一套逻辑)

探针 mod (`mods/units/dynProbeMod/` + mod-info.txt) 被成功发现:
- **mod 发现**: "openAssetCached: Reading: /SD/mods/units/dynProbeMod/mod-info.txt" (缓存系统带时间戳失效检测)
- **加载管线阶段序列** (单位/mod/地图共用): `imageLoad → imageLoadOrGet → soundLoad → soundLoadOrGet → iniParse → unitParse → iniOpen → iniClose → iniSetup → actionParse → unitParsePartA/B/C/D → enableAll` → "Done loading custom units" → `getAllUnitsChecksum` (单位校验和)
- **禁用逻辑定位** (02 源码): ag.java ~940 行 "Disabled mod at:" (b2.f 标志);i/a.java mod 管理器 (loadSelection 按 name|hash|state 三分段匹配)
- **modSettings hash 已破解**: `sha256(模组目录名)` 大写 hex (mega_builders 条目验证命中)
- 新 mod 默认禁用 (`Disabling all new mods for first/new load` / `Too many new mods found`),偏好文件在退出时被游戏重写

### 单位图像导出 ✅

`-outputunitimages` headless 完整运行 (exit 0): 导出 **43 张单位 PNG** (output_all_unit_images/, 146KB) — 单位注册表→图形→渲染→导出全链路动态验证。文件名 = 42 单位键 (airFactory/antiAirTurretT2...)。

## 6. v19.92 第三轮: 地图加载捕捉 + debug 轮次

### 地图加载管线 (与单位/mod 同逻辑 — 设计者说明再次印证)

补 assets/maps + tilesets 后, 菜单地图加载全程追踪:
- 阶段序列: `--- Loading map ---` → `Mapfile: assets/maps/menu_background/menuN.tmx` → `---- Loading map data ----` → `Setting up team fog..` → tileset 解析 → 单位层解析 ("Could not find unit type of:modularSpider at x:906.0, y:2186.0" — mod 单位缺失, 非致命) → `smoothFog load took:Nms` / `Precalculating map fog`
- **语义类名运行时确认**: `com.corrodinggames.rts.game.b.f: Could not find map: ...` → fwd 已映射 **game.map.MapException** ✓ (运行时输出与映射表互证)
- 错误路径: `alert:Error loading map:` → `slick queuing-alert:` (UI 告警队列)
- 缺失资产链修复记录: maps/ → tilesets/ (terrain 等) → 单位层依赖 mods

### debug 轮次

`-debugscript -devdebug`: 430 行, 无额外脚本输出 (脚本调试仅当 UI 脚本实际运行时激活 — 需交互模式)。FBO 创建序列 (OpenGL 缓冲) 记录完整。

## 7. v19.93 第四轮: JFR 飞行记录器 (零插桩方法级采样)

游戏 JVM (JDK 13) 自带 jfr/jcmd:
- `-XX:StartFlightRecording=filename=...,settings=profile,duration=90s` 零插桩采集
- **音频热路径**: `OggInputStream.readPCM/getPageAndPacket` (332/278 行) — OGG 解码是启动期最忙的 Java 代码 (音乐播放)
- **异常率**: 渲染循环中 ~80 异常/秒 (52→134 累积) — 游戏用异常做控制流
- 限制: 渲染负载在 native 代码 (269 NativeMethodSample vs 23 ExecutionSample),菜单渲染崩溃 (document==null) 限制采样窗口;ExceptionStatistics 无堆栈 (JavaExceptionThrow=0, 需 custom settings)

## 8. v19.94 回放管线 — 带动全部模块的动态测试平台 ✅

### 回放启动链路 (完整逆向)

`-debugscript <脚本文件>` → DebugServer (rts.a.a) 队列 → 逐行 `script <行>` → ScriptEngine.processScript → **`root.loadReplay('名字.replay')`** → l.a.a(File) 委托 → ReplayEngine.c(name) → startReplayingFile。调试服务器协议: `ping`/`crash`/`script`/`function` 四命令 (端口 5677)。

### headless 回放播放 ✅ (真实回放文件)

- 35 个真实回放 (用户 replays/) → ASCII 名副本 test_beach.replay 后 `root.loadReplay('test_beach.replay')` → **"Replay: Loading save from version: 96 / Loading replay initial save / Unit cap: 500 / Starting frame:0"** — 回放开始播放!
- 回放格式: 魔数 "rustedWarfareReplay" + gameCode 176 + version 96 + 版本串 + 布尔 + 内嵌 gamesave 块 (ba.java 463 写侧镜像)
- 关键坑: **CJK 文件名/无扩展名 → "Failed to read replay file"**;ASCII 名 + .replay 后缀可用
- 前提修复: assets/gui 的 24 个 RML/RCSS/FNT 文件 (真实安装复制) — 修复 document==null 崩溃, 菜单完整加载

### JFR × 回放 = 全模块模拟采样 (用户假设证实)

回放播放期 150s JFR: 游戏模拟真正跑起来:
| 采样帧 | 类 | 角色 |
|------|------|------|
| 150+146+119+118+108 | **game.i** a/b/c(l,float) | 每帧主模拟 (extends GlobalState, inSpace 状态机) — **GameInputHandler 映射存疑, 动态证据指向屏幕状态机, 待 T1 仲裁** |
| 28 | units.am.a(l) (UnitInstance) | 单位更新 |
| 9+8+8 | OggInputStream | 音频解码 |
| 5+4 | java.u.render / b.gameLoop | 渲染循环 |

### 回放平台的后续用法

- 指定帧/倍速回放 → 定向采样单一模块 (网络重放/战斗/AI)
- JFR custom settings 异常带堆栈 → 控制流异常定位
- 长回放 + -replay_debug → 命令序列日志 (rc/命令记录流)

## 9. v19.95 回放命令流解析器 — 静态管线验证平台

`tools/utils/replay_parser.py` (M1-M7): 完整解码回放文件格式 (ba.java/e.java/j.k.java 三源交叉):
- **帧结构**: [UTF 标签][int 载荷长度][载荷];gamesave 块 = [UTF][byte[]] (尾部标记 12345 + "<SAVE END>")
- **记录类型**: rc(命令)/wait(帧等待)/cs(校验和)/es(扩展校验和)/chat/resync/end/endReplayMetaData
- **Command 完整反序列化** (版本门控 16→96): team byte/wp 内联字段(单位类型+坐标)/动作枚举/攻击模式/目标坐标/参数列表(long 实体 id)/PlayerState(byte)/UnitInstance(long)/动作名/步速/子对象列表
- **5 个真实回放全解析**: **8,717 条真实命令 + 2,589 个校验和** (r4 Crossing Large 10p 最大: 4,123 命令)
- 产出: mappings/generated/replay-commands-all.csv (replay,frame,team,action,argCount,stopOrUndo,waypoint,attackMode,hasStepRate)
- 意义: **网络/命令管线获得真实数据验证源** — 命令类型分布/帧分布/队伍行为可与 NETWORK-PROTOCOL.md/COMMAND-SERIAL.md 互证;校验和流 = 确定性模拟验证基线

## 10. v19.96 回放保存平台 — 录制管线动态测试 ✅

### 关键前置突破: mods 启用之谜解开

- **根因**: `game.i` 初始化 `if (this.ee) this.bZ.g()` — **安全模式**全禁用 mods。ee 触发: `numIncompleteLoadAttempts > 1 || numLoadsSinceRunningGameOrNormalExit > 3` — **每次强杀/崩溃进程累加, 第 2 次后安全模式激活 → mods 静默禁用** (i.java:231-259, 392)
- 游戏真正运行 (bx>5 帧) 时自动清零 (i.java:1021)。**对策: 每次启动前 sed 清零 preferences.ini 两个计数器, 或脚本正常退出**
- 证据: record3 日志 "starting game in safe mode" + "Disabled mod at:spiderMod" vs record4 (清零后) "Loading mod at:/SD/mods/units/spiderMod"
- 附带发现: mods/units/ 目录 mod 默认启用 (a(...,bl=true) → f=false), builtin_mods 默认禁用 (i/a.java:417-424)

### 新游戏启动链 (脚本全通)

`debug.setGameSetting('saveMultiplayerReplays','true')` → `root.hostStart(false)` → 战斗室 (battleroom.rml) → `mp.multiplayerStart()` → 地图加载 → **自动录制开始**

- "Starting new network game (uuid)" + "Mapfile: assets/maps/skirmish/[z;p10]Crossing Large (10p).tmx" + **"Replay: Recording replay to: Crossing Large (10p) [v1.15] (16 8月 2026 18.45.05).replay"**
- 录制触发链: game.i:875 `cb.a(bl3)` → ba.a(boolean) 检查 `bX.B(网络/单机游戏) && saveMultiplayerReplays` → ba.d(name) 开始录制 (ba:421-487)
- 命令注入: `debug.createUnit('tank', 800, 800, 0, false)` (直接 API, 不入命令流) + `debug.moveAllUnitsOnTeam(0, 1500, 1500)` (**经 cf 命令管线 → c.java:86 cb.a(e,n) 录制**)
- 保存: `mp.disconnect('exited')` → **"Replay: stop requested at:20690 / Background writer stopping / Remainding commands: 0 / Commands issued: 3"** — 3 条命令 = 3 条移动
- 新工具: `tools/utils/debug_script.py` (M1-M7) — DebugServer 客户端 (script 命令/--wait 延迟/超时容错)
- 启动参数铁律: **`-debug 5677:x` 才启动服务器监听** (字节码证实 a(int,String) 只开 ServerSocket), `-debugscript 文件` 只排队脚本

### 其他踩坑

- `-debug` 使 l.aT=true (automated testing) → 地图错误从非致命变致命崩溃 ("Crashing on allowed map error")
- 菜单背景轮换 menu1/2/3 (nextBackgroundMap 1-3 循环, i.java:2085-2087) — 缺文件即崩, 需三图齐全
- headless LibRocket 原生渲染偶发段错误 (EnableScissorRegion/postUpdate) — 概率通过, 重试即可
- 强杀计数器 + 安全模式 = 本项目 headless 测试最大的隐性坑

## 11. v19.97 v96 命令格式完全破解 — 录制↔解析闭环 ✅

### 写侧字节码权威 (e.a(as) 全序)

```
子块"c": team(byte) wp(bool+units.au内联: enum/单位id/2浮点/long/版本门控字节×4)
  flag_e(bool) flag_g(bool) int-1(占位) attackMode(enum int) z(bool+2浮点) flag_o(bool)
  argCount(int)+longs p(bool+PlayerState) l(bool+2浮点) m(UnitInstance long)
  动作名(UTF, 如"-1") flag_f(bool) q(short) r(bool+步速: byte+2浮点+int)
  d子对象数(int) × [long实体id+4浮点+int帧号+enum路径类型+bool(a!=null)
    +bool(k.x!=null)+子流"p"([UTF名][int长度][gzip差分节点数据])]
  flag_h(bool)  [as.a("c")=endBlock不产字节]
```

- 关键修正: d 子对象路径 = **at 压缩子流** (as.a(String,boolean) 创建, 嵌入 [UTF名][int长][gzip]) — 旧 read_bytes 错误
- as.a(String) = endBlock (校验名匹配, 不写字节); as.e(String) = 开始子块

### 录制↔解析闭环

- 新录制回放 r-recorded.replay: 3 条 rc 全解析 (移动1200/1500 + argCount 2/4 = 坦克 id 207-210) ✓
- **r1-r5 回归: 命令覆盖 8,717 → 14,184** (r4: 4,123→6,742 — d 子对象修复收益)
- 产出: parse_rc_v96.py (字段级对照), dump_rc.py (原始字节 dump) — 均 M1-M7

### 播放侧验证 (r-recorded 重放)

- 命令执行日志与解析器**精确互证**: "updateGameFrame: Command: unset (0) count:2 id:1" + "Waypoint: move" = 解析器的动作名"-1"/argCount=2/wp enum=0
- **es 扩展校验和帧 0 全匹配** (Unit Pos/Dir/Hp/Id/Waypoints/Team Credits/UnitPaths/Unit Count/Team Info/Command center 全 ok) — 确定性模拟基线成立
- **帧 301+ 线性差异模式**: Team Credits 每 301 帧 +135 (0.4485/帧 = AI 收入率) — 播放侧 AI 帧 0 起赚钱, 录制侧前 1204 帧恒 4000 — **AI 管线动态证据: 回放不重放 AI 随机种子, AI 实时决策分叉, es 系统检测并报告**
- 弹窗阻塞模拟: mod 错误弹窗冻结帧推进 (closePopup 在游戏态无效) — 消除弹窗源是硬要求
- test_beach.replay 现在可加载 (aaBeamGunship ∈ spiderMod, mods 启用后无 "requires the unit" 报错) — 完整播放留待后续 (headless 渲染偶发崩溃)

## 12. v19.98 主链路验证 + 解混淆落库

- **game.i 身份仲裁完成**: GameInputHandler/GameScreen/屏幕状态机 全部候选撤销 — T0 常量池 "GameEngine:init()" + 16 引擎字段 + JFR 热路径 → **game.i = GameEngine** (详见 docs/deobfuscation/GAME-MAIN-LOOP.md)
- 主链路: java.b.gameLoop → updateAndRender → GameEngine.update(float) (每帧: NetworkEngine步进→CommandController→ReplayEngine→帧号→Team→地图→单位)
- 16 引擎字段落库 (supplement verified) + am: GameRenderer→**MusicController** (T0 音乐字符串) + gameFramework.d→**CommandPathPart** + a()→isBusy + 悬空 c→onScreenRender 删除
- 03 侧统一改名 GameScreen/GameInputHandler→GameEngine (12 文件, 编译 24,965→**24,961**)
- 冲突留档: aa (GroupController vs ProjectileWeapon)、g (FileSystem vs 平台检测) → arbitration-candidates.csv

## 13. 后续轮次 (按需)

- test_beach 完整播放 → 真实命令执行日志 ↔ 14,184 目录互证 (渲染崩溃重试)
- es 校验差异根因深挖 (AI 随机种子/帧推进方式) — 网络协议确定性边界
- 命令流 ↔ COMMAND-SERIAL.md 交叉验证 (类型/字段映射, 写侧格式已齐)
- `-lang en`;交互模式 -debugscript;倍速回放定向采样
- **Java agent 动态追踪** (Attacher.java 已就绪): BTrace/自写 transformer — 方法级调用序列
- game.i 身份仲裁 (GameInputHandler vs 屏幕状态机) — JFR 证据已记录
