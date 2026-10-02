# 12-platform — 平台层

> **职责**：桌面/Android/Steam 适配与平台能力探测

> 本文处于**项目分析上限** ✓：数据全部来自映射库 / 反编译树 / 交付物实测 ✓，口径见 [../STATUS.md](../STATUS.md) ✓。

## 一、数据总览

| 指标 | 值 |
|---|---|
| 映射条目 | **161** 条 |
| 涉及类 | **35** 个 |
| 有可读名 | **31** 个（89%）|
| ★ **已替换为我们的字节** | **24** 个（69%）|
| 主包 | `com.corrodinggames.rts.appFramework` · `com.corrodinggames.rts.java` · `com.corrodinggames.rts.java.audio.a` · `com.corrodinggames.rts.java.c` |

### 交付物替换状态（★ 该域我们掌控多少 ✓）

| 状态 | 类数 | 含义 |
|---|---|---|
| **我们的字节** ✓ | **24** | 已由反编译源重建并在交付物中替换 |
| 原版字节 | 11 | 仍使用原版混淆字节（结构一致 ✓）|
| 未进交付物 | 0 | 该域映射涉及、但不属交付物条目 |

## 二、包结构与源码位置

| 混淆包 | 映射行 |
|---|---|
| `com.corrodinggames.rts.appFramework` | 99 |
| `com.corrodinggames.rts.java` | 45 |
| `com.corrodinggames.rts.java.audio.a` | 9 |
| `com.corrodinggames.rts.java.c` | 5 |
| `com.corrodinggames.rts.a.a` | 3 |

**03 源码位置**（该域主包 ✓）：

| 包（可读路径） | 文件 | 行数 |
|---|---|---|
| `com/corrodinggames/rts/java` | 34 | 5549 |
| `com/corrodinggames/rts/appFramework` | 56 | 2909 |
| `com/corrodinggames/rts/java/audio/backend` | 20 | 2293 |
| `com/corrodinggames/rts/java/audio/lwjgl` | 17 | 2097 |
| `com/corrodinggames/rts/java/input` | 15 | 1561 |
| `com` | 6 | 1080 |
| `com/corrodinggames/rts` | 13 | 658 |
| `com/corrodinggames/rts/java/filesystem` | 2 | 354 |
| `com/corrodinggames/rts/java/audio` | 6 | 140 |
| `com/corrodinggames/rts/java/graphics` | 1 | 48 |

## 三、★ 全量类清单（35 个 ✓）

> `状态`：**我们的字节** ✓ / 原版字节 / 未进交付物 ✓；`字段`/`方法` 为该类**已映射成员数** ✓。

| 混淆类名 | 可读名 | 字段 | 方法 | 状态 |
|---|---|---|---|---|
| `e` | **Slick2DRenderer** | 12 | 15 | 原版字节 |
| `l` | **MultiTouchHandler** | 18 | 0 | **我们的字节** ✓ |
| `m` | **TouchState** | 18 | 0 | 原版字节 |
| `n` | **DialogHelper** | 8 | 2 | **我们的字节** ✓ |
| `i` | **AudioManager** | 9 | 0 | 原版字节 |
| `e` | **AppState** | 6 | 0 | **我们的字节** ✓ |
| `o` | **PlayerNameFormatter** | 6 | 0 | **我们的字节** ✓ |
| `c` | **AndroidUIHelper** | 4 | 1 | **我们的字节** ✓ |
| `g` | **InGameActivity** | 4 | 1 | **我们的字节** ✓ |
| `t` | **SlickImageData** | 5 | 0 | 原版字节 |
| `c$2` | **AndroidUIHelper$2** | 3 | 1 | 原版字节 |
| `g$4` | **InGameActivity$4** | 3 | 1 | **我们的字节** ✓ |
| `Main` | — | 3 | 1 | 原版字节 |
| `b` | **SteamManager** | 0 | 4 | 原版字节 |
| `f` | **AppFramework** | 0 | 3 | **我们的字节** ✓ |
| `g$6` | **InGameActivity$6** | 2 | 1 | **我们的字节** ✓ |
| `i` | **ContextMenuActivity** | 0 | 3 | 原版字节 |
| `p` | — | 3 | 0 | **我们的字节** ✓ |
| `s` | **StorageFolderPicker** | 3 | 0 | 原版字节 |
| `n` | **DesktopMusicPlayer2** | 0 | 3 | **我们的字节** ✓ |
| `h` | **UIRunnable** | 2 | 0 | **我们的字节** ✓ |
| `p$1` | — | 0 | 2 | **我们的字节** ✓ |
| `s` | **SlickTexture** | 0 | 2 | 原版字节 |
| `c` | **TestLogicBoolean** | 0 | 1 | **我们的字节** ✓ |
| `f` | **TestPerformance** | 0 | 1 | **我们的字节** ✓ |
| `n` | **TestRunner** | 0 | 1 | **我们的字节** ✓ |
| `j` | **ButtonActivity** | 0 | 1 | **我们的字节** ✓ |
| `k` | **ReplayComparator2** | 1 | 0 | **我们的字节** ✓ |
| `q` | **ReplayFolderManager** | 1 | 0 | **我们的字节** ✓ |
| `r` | **ReplayComparator** | 1 | 0 | **我们的字节** ✓ |
| `Main$3` | — | 0 | 1 | **我们的字节** ✓ |
| `g` | **SteamWorkshop** | 0 | 1 | **我们的字节** ✓ |
| `i` | **DesktopPlatform** | 0 | 1 | 原版字节 |
| `k` | **ApacheHttpClientPool** | 0 | 1 | **我们的字节** ✓ |
| `o` | **OpenALSoundEngine** | 0 | 1 | **我们的字节** ✓ |

## 四、关键成员速查（真实可读名 ✓）

| 类 | 成员数 | 可读成员（节选）|
|---|---|---|
| `e`（Slick2DRenderer） | 27 | `activeSprite(f)` · `activeTexture(f)` · `clearAlphaMap(m)` · `clearColor(f)` · `darkOverlayColor(f)` · `destroyGraphics(m)` · `flushAndReset(m)` · `flushBuffer(m)` · `flushCleanupQueue(m)` … |
| `l`（MultiTouchHandler） | 18 | `activePointerCount(f)` · `activePointers(f)` · `doubleTapActive(f)` · `gestureDetector(f)` · `lastTouchTime(f)` · `lastTouchX(f)` · `lastTouchY(f)` · `maxPointers(f)` · `pinchActive(f)` … |
| `m`（TouchState） | 18 | `activePointer(f)` · `clickCount(f)` · `currentX(f)` · `currentY(f)` · `downStartX(f)` · `downStartY(f)` · `dragDistance(f)` · `gestureType(f)` · `isDoubleClick(f)` … |
| `n`（DialogHelper） | 10 | `ae(f)` · `alertDialog(f)` · `boolean2(f)` · `boolean3(f)` · `handler1(f)` · `handler2(f)` · `isEnabled(m)` · `n(f)` · `reset(m)` … |
| `i`（AudioManager） | 9 | `audioFormat(f)` · `audioPlayerA(f)` · `audioPlayerB(f)` · `audioSessionId(f)` · `bufferSize(f)` · `channelCount(f)` · `sampleRate(f)` · `streamType(f)` · `volumeLevel(f)` |
| `e`（AppState） | 6 | `dynamicDefault(f)` · `multiThreadedNonSurface(f)` · `multiThreadedSurface(f)` · `opengl(f)` · `singleThreadedSurface(f)` · `singleThreadedSurfaceIfHardware(f)` |
| `o`（PlayerNameFormatter） | 6 | `int1(f)` · `int2(f)` · `int3(f)` · `string(f)` · `testConfigField1(f)` · `testConfigField2(f)` |
| `c`（AndroidUIHelper） | 5 | `activity(f)` · `dialogHelper(f)` · `getNumberOfPlayersInMap(m)` · `lastToastTime(f)` · `toastQueue(f)` |

## 五、映射备注（项目分析结论 ✓）

- **`c`**（TestLogicBoolean）：证据串自报名(ldc 绑定, A 级)；描述符 (Ljava/lang/String;Z)V
- **`f`**（TestPerformance）：证据串自报名(ldc 绑定, A 级)；描述符 ()V
- **`n`**（TestRunner）：证据串自报名(ldc 绑定, A 级)；描述符 (Ljava/lang/String;Ljava/lang/String;)V
- **`c`**（AndroidUIHelper）：Activity reference (Activity)
- **`c`**（AndroidUIHelper）：Dialog helper reference
- **`c`**（AndroidUIHelper）：Last toast display time (long)
- **`c`**（AndroidUIHelper）：Toast message queue
- **`c$2`**（AndroidUIHelper$2）："Phase3-inferred: captured l (GlobalState); sets storageType=2 + hasSelectedAStorageType"
- **`c$2`**（AndroidUIHelper$2）：Phase3-inferred: captured Activity
- **`c$2`**（AndroidUIHelper$2）：Phase3-inferred: captured Runnable run after storage setup
- **`c$2`**（AndroidUIHelper$2）：already meaningful (DialogInterface.OnClickListener)
- **`e`**（AppState）：枚举常量 INI 名（RS-27 第 47 轮，由 <clinit> 的 ldc 序列提取）
- **`f`**（AppFramework）：real-class-inferred: com.corrodinggames.rts.appFramework.f
- **`g`**（InGameActivity）：real-class-inferred: com.corrodinggames.rts.appFramework.g

## 核心概念

**平台层**：把游戏逻辑与具体平台（桌面/Android/Steam）解耦。

| 组成 | 说明 |
|---|---|
| **桌面入口** | `java/` 下的启动器、窗口、输入适配 |
| **Android 适配** | `appFramework/` 的 Activity/UI 助手 |
| **Steam 集成** | Steamworks 封装（成就/好友/大厅/云存档） |
| **文件系统** | 各平台路径规则 |

## 关键机制

1. **入口分离**：`java/GameLauncher`（桌面）与 Android `Activity` 各自引导
2. **能力探测**：运行时判断平台（`isDesktop`/`isMobile`）⇒ 条件逻辑
3. **Steamworks 绑定**：`com.codedisaster.steamworks`（**121 个源文件** ✓）
4. **路径抽象**：统一 `assets/`、存档目录、模组目录

## 修改指南

| 想改什么 | 改哪里 |
|---|---|
| 启动参数/窗口 | `java/` 启动器 ✓ |
| Steam 功能 | steamworks 封装层 ⚠️ 需 Steam 环境 |
| 平台判断 | 引擎的能力探测 ✓ |


## 六、子文档

- [`01-input.md`](01-input.md)
- [`02-steam.md`](02-steam.md)
- [`03-librocket.md`](03-librocket.md)
- [`04-JAVAS-MAIN.md`](04-JAVAS-MAIN.md)
- [`04-filesystem.md`](04-filesystem.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓


## 六、子文档

- [`01-input.md`](01-input.md)
- [`02-steam.md`](02-steam.md)
- [`03-librocket.md`](03-librocket.md)
- [`04-JAVAS-MAIN.md`](04-JAVAS-MAIN.md)
- [`04-filesystem.md`](04-filesystem.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓
