# 07-engine — 引擎核心

> **职责**：游戏循环、状态机、对局生命周期、存档、日志

> 本文处于**项目分析上限** ✓：数据全部来自映射库 / 反编译树 / 交付物实测 ✓，口径见 [../STATUS.md](../STATUS.md) ✓。

## 一、数据总览

| 指标 | 值 |
|---|---|
| 映射条目 | **920** 条 |
| 涉及类 | **44** 个 |
| 有可读名 | **44** 个（100%）|
| ★ **已替换为我们的字节** | **20** 个（45%）|
| 主包 | `com.corrodinggames.rts.gameFramework` · `com.corrodinggames.rts.gameFramework.i` · `com.corrodinggames.rts.gameFramework.e` · `com.corrodinggames.rts.gameFramework.a` |

### 交付物替换状态（★ 该域我们掌控多少 ✓）

| 状态 | 类数 | 含义 |
|---|---|---|
| **我们的字节** ✓ | **20** | 已由反编译源重建并在交付物中替换 |
| 原版字节 | 24 | 仍使用原版混淆字节（结构一致 ✓）|
| 未进交付物 | 0 | 该域映射涉及、但不属交付物条目 |

## 二、包结构与源码位置

| 混淆包 | 映射行 |
|---|---|
| `com.corrodinggames.rts.gameFramework` | 823 |
| `com.corrodinggames.rts.gameFramework.i` | 63 |
| `com.corrodinggames.rts.gameFramework.e` | 17 |
| `com.corrodinggames.rts.gameFramework.a` | 8 |
| `com.corrodinggames.rts.gameFramework.c` | 8 |
| `com.corrodinggames.rts.gameFramework.h` | 1 |

**03 源码位置**（该域主包 ✓）：

| 包（可读路径） | 文件 | 行数 |
|---|---|---|
| `com/corrodinggames/rts/gameFramework/network` | 81 | 12066 |
| `com/corrodinggames/rts/gameFramework` | 95 | 11769 |
| `com/corrodinggames/rts/gameFramework/ui` | 61 | 8881 |
| `com/corrodinggames/rts/gameFramework/utility` | 64 | 7954 |
| `com/corrodinggames/rts/game` | 24 | 7757 |
| `com/corrodinggames/rts/gameFramework/rendering` | 35 | 4743 |
| `com/corrodinggames/rts/gameFramework/pathfinding` | 17 | 3906 |
| `com/corrodinggames/rts/gameFramework/opengl` | 43 | 3158 |
| `com/corrodinggames/rts/gameFramework/aicore` | 27 | 2512 |
| `com/corrodinggames/rts/gameFramework/effects` | 8 | 1986 |

## 三、★ 全量类清单（44 个 ✓）

> `状态`：**我们的字节** ✓ / 原版字节 / 未进交付物 ✓；`字段`/`方法` 为该类**已映射成员数** ✓。

| 混淆类名 | 可读名 | 字段 | 方法 | 状态 |
|---|---|---|---|---|
| `l` | **GlobalState** | 195 | 92 | 原版字节 |
| `SettingsEngine` | **SettingsEngine** | 125 | 12 | 原版字节 |
| `ac` | **KeyBindings** | 63 | 10 | 原版字节 |
| `ba` | **ReplayEngine** | 40 | 31 | 原版字节 |
| `f` | **GameUtils** | 21 | 26 | 原版字节 |
| `a` | **VersionChecker** | 6 | 32 | 原版字节 |
| `e` | **Command** | 23 | 10 | 原版字节 |
| `bs` | **GamePhase** | 30 | 0 | 原版字节 |
| `b` | **ModInfo** | 25 | 0 | 原版字节 |
| `w` | **EffectConfig** | 11 | 8 | 原版字节 |
| `y` | **GameSaver** | 4 | 13 | 原版字节 |
| `a` | **CollisionEngine** | 14 | 0 | **我们的字节** ✓ |
| `bo` | **StatsRecord** | 12 | 2 | 原版字节 |
| `c` | **CommandController** | 2 | 7 | 原版字节 |
| `bf` | **UnitGroup** | 8 | 0 | **我们的字节** ✓ |
| `bn` | **StatsHistory** | 1 | 7 | 原版字节 |
| `a` | **DebugServer** | 8 | 0 | **我们的字节** ✓ |
| `a` | **FileLoader** | 0 | 7 | 原版字节 |
| `u` | **ResourceDomainEnum** | 7 | 0 | **我们的字节** ✓ |
| `bu` | **FrameCounter** | 5 | 0 | **我们的字节** ✓ |
| `d` | **CommandPathPart** | 5 | 0 | 原版字节 |
| `i` | **Sound** | 4 | 0 | **我们的字节** ✓ |
| `aa` | **GroupController** | 1 | 3 | 原版字节 |
| `ad` | **KeyBinding** | 4 | 0 | 原版字节 |
| `af` | **TextureCache** | 4 | 0 | **我们的字节** ✓ |
| `bg` | **StatsHandler** | 0 | 4 | 原版字节 |
| `c` | **StorageBackend** | 3 | 1 | 原版字节 |
| `ag` | **KeyTrigger** | 3 | 0 | **我们的字节** ✓ |
| `at` | **MusicFolder** | 1 | 2 | 原版字节 |
| `b` | **CollisionGroup** | 2 | 1 | **我们的字节** ✓ |
| `br` | **ExtraManager** | 3 | 0 | 原版字节 |
| `b` | **FileAccessFlags** | 3 | 0 | **我们的字节** ✓ |
| `e` | **SoundRegistry** | 2 | 0 | **我们的字节** ✓ |
| `h` | **SoundFactory** | 1 | 1 | **我们的字节** ✓ |
| `ae` | **ShaderProgram** | 2 | 0 | **我们的字节** ✓ |
| `bh` | **StatsSample** | 2 | 0 | **我们的字节** ✓ |
| `bp` | **Attachment** | 2 | 0 | **我们的字节** ✓ |
| `bq` | **BaseGameObject** | 0 | 2 | **我们的字节** ✓ |
| `g` | **FilePathSanitizer** | 0 | 2 | **我们的字节** ✓ |
| `ah` | **AxisTrigger** | 1 | 0 | **我们的字节** ✓ |
| `e` | **DualStorage** | 0 | 1 | **我们的字节** ✓ |
| `g` | **PlatformDetector** | 0 | 1 | 原版字节 |
| `a` | **Localization** | 0 | 1 | **我们的字节** ✓ |
| `l$4` | **GlobalState$4** | 0 | 1 | 原版字节 |

## 四、关键成员速查（真实可读名 ✓）

| 类 | 成员数 | 可读成员（节选）|
|---|---|---|
| `l`（GlobalState） | 287 | `activateRenderSurface(m)` · `aiSpawner(f)` · `androidContext(f)` · `androidDeviceId(f)` · `antiCheatEnabled(f)` · `applyInitialZoomPaints(m)` · `applyRenderScale(m)` · `assetsReloaded(f)` · `audioEngine(f)` … |
| `SettingsEngine`（SettingsEngine） | 137 | `aiDifficulty(f)` · `allowGameRecording(f)` · `androidNoSoundPrioritiesDebug(f)` · `autosaving(f)` · `banTimeInSecondsAfterKick(f)` · `batterySaving(f)` · `classicInterface(f)` · `disableDigitGrouping(f)` · `disableModLazyLoad(f)` … |
| `ac`（KeyBindings） | 73 | `ad44(f)` · `ad45(f)` · `ad46(f)` · `ad47(f)` · `ad48(f)` · `ad50(f)` · `ad51(f)` · `ad52(f)` · `ad53(f)` … |
| `ba`（ReplayEngine） | 71 | `calculateChecksum(m)` · `checksumCalc(f)` · `checksumMismatchCount(f)` · `commandCount(f)` · `commandsIssued(f)` · `commandsRead(f)` · `commandsSinceChecksum(f)` · `cycleGameSpeed(m)` · `deleteReplay(m)` … |
| `f`（GameUtils） | 47 | `atanOctant3(f)` · `atanOctant4(f)` · `atanOctant5(f)` · `atanOctant6(f)` · `atanOctant7(f)` · `atanOctant8(f)` · `bytesToHex(m)` · `clamp(m)` · `collisionPoint(f)` … |
| `a`（VersionChecker） | 38 | `addExtraMapEntry(m)` · `addModEntry(m)` · `applyMods(m)` · `checkGameVersion(m)` · `checkMinVersion(m)` · `checkTask(f)` · `clearExtraMaps(m)` · `countActiveMods(m)` · `countEnabledMods(m)` … |
| `e`（Command） | 33 | `applySpecialActions(m)` · `attackMode(f)` · `backupPosition(f)` · `commandController(f)` · `commandingPlayer(f)` · `convertUnitRefsToIds(m)` · `enablePathfinding(m)` · `execute(m)` · `flagH(f)` … |
| `bs`（GamePhase） | 30 | `draw(f)` · `draw_end(f)` · `draw_game(f)` · `draw_game_effects(f)` · `draw_game_unit(f)` · `draw_gui(f)` · `draw_setup(f)` · `draw_setup_clip(f)` · `draw_setup_drawMap(f)` … |

## 五、映射备注（项目分析结论 ✓）

- **`SettingsEngine`**（SettingsEngine）：Phase3: name verified from 02-decompiled SettingsEngine.java (obfuscated 2-char name unrecoverable - jar rebuilt)
- **`SettingsEngine`**（SettingsEngine）：Phase3: name verified from 02-decompiled SettingsEngine.java (obfuscated 2-char name unrecoverable - jar rebuilt; field 
- **`a`**（CollisionEngine）：全部碰撞组注册表。T0: a(byte) 94-103行 'for (b b2 : this.n) ... this.n.add(b3)' — 按组号查找/新增, 存 CollisionGroup 列表
- **`a`**（CollisionEngine）：碰撞组1(默认地面单位组)。T0: am.bR() (am.java:625) 非空中非潜水时 n2=1 且 am.bU 默认值 1 (am.java:86) — 所有常规地面单位走此组; 矩阵(构造器 41-47行): 自碰撞+组3,4,
- **`a`**（CollisionEngine）：碰撞组10。T0: 构造器 30行 this.e=this.a((byte)10); 矩阵(58-63行): 自碰撞+组3,4,13,40 — 高互连组。无单位类型对应证据 — 保守命名
- **`a`**（CollisionEngine）：碰撞组11。T0: 构造器 31行 this.f=this.a((byte)11); 矩阵(64-68行): 自碰撞+组3,10,13,40。无单位类型对应证据 — 保守命名
- **`e`**（SoundRegistry）：v19.113n: 音效常量 (y:5102 a.e.p 攻击音)
- **`e`**（SoundRegistry）：v19.113n: 音效常量. 03曾误标PlatformAudio (platform.e 实为另一类; jar无platform.e, 铁证 y:5102 a.e.o/p)
- **`h`**（SoundFactory）：Unit add click sound (static i)
- **`h`**（SoundFactory）：real-class-inferred: com.corrodinggames.rts.gameFramework.a.h
- **`i`**（Sound）：Audio file path (String)
- **`i`**（Sound）：Audio loaded into memory (boolean)
- **`i`**（Sound）：Streaming audio flag (boolean)
- **`i`**（Sound）：Volume gain multiplier (float,  default 1.0)

## 核心概念

**引擎核心**：游戏循环、主状态机、存档、日志、事件。

| 组成 | 说明 |
|---|---|
| **游戏主循环** | 固定步长更新 + 渲染；帧率无关逻辑 |
| **主状态机** | 主菜单 → 大厅 → 对局 → 结算 |
| **对局生命周期** | 开局、初始化、运行、胜负判定 |
| **存档/读档** | 全量状态序列化（单位/资源/命令/随机种子 ✓） |
| **日志与统计** | 分级日志 + 运行统计 |

## 关键机制

1. **确定性更新**：逻辑按固定步长推进 ⇒ 回放/联机可复现 ✓
2. **存档完整性**：单位、命令队列、AI 状态、随机数状态都要存
3. **崩溃处理**：`CrashHandler` 捕获并落盘（本项目抓崩溃的主要通道 ✓）
4. **事件/动作表**：引擎级事件与动作解耦
5. **调试开关**：`Debug` 类的功能位（**有参数白名单约束** ✓）

## 修改指南

| 想改什么 | 改哪里 |
|---|---|
| 游戏规则 | `gameFramework/` 引擎类 ✓ |
| 存档内容 | 存档类（**改后必须验存档兼容** ⚠️） |
| 新增调试能力 | 调试类（注意白名单 ✓） |


## 六、子文档

- [`API-SURFACE.md`](API-SURFACE.md)
- [`DEBUG-FEATURES.md`](DEBUG-FEATURES.md)
- [`EVENTS-ACTIONS.md`](EVENTS-ACTIONS.md)
- [`GAMELOOP.md`](GAMELOOP.md)
- [`LOGGING.md`](LOGGING.md)
- [`MATCH-LIFECYCLE.md`](MATCH-LIFECYCLE.md)
- [`SAVELOAD.md`](SAVELOAD.md)
- [`STATS.md`](STATS.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓


## 六、子文档

- [`API-SURFACE.md`](API-SURFACE.md)
- [`DEBUG-FEATURES.md`](DEBUG-FEATURES.md)
- [`EVENTS-ACTIONS.md`](EVENTS-ACTIONS.md)
- [`GAMELOOP.md`](GAMELOOP.md)
- [`LOGGING.md`](LOGGING.md)
- [`MATCH-LIFECYCLE.md`](MATCH-LIFECYCLE.md)
- [`SAVELOAD.md`](SAVELOAD.md)
- [`STATS.md`](STATS.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓
