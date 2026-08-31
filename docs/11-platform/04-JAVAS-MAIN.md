# Main.class 反混淆 — 04-javas 首个产物 (GameLauncher)

> v19.84 | 2026-08-16 | 来源类: `com.corrodinggames.rts.java.Main` (桌面版游戏启动器)


> ⚠️ **已归档** (Main.class→GameLauncher 反混淆 (java.* 平台后端), 2026-09-04 由 deobfuscation/ 移入 11-platform 域)


## 概览

将 01-classes 中唯一的 `com/corrodinggames/rts/java/Main.class` (18,926 字节) 完整解混淆为
`04-javas/com/corrodinggames/rts/java/GameLauncher.java`, 连同 4 个内部类 (GameLauncher$1..$4),
共 5 个文件。类名 Main→GameLauncher 沿用 mappings.csv 既有映射。

```
04-javas/com/corrodinggames/rts/java/
├── GameLauncher.java     — 启动器主体 (21 字段 + 21 方法 + 构造器, 与字节码逐一对应)
├── GameLauncher$1.java   — 匿名 Runnable: 启动 stdin 读取循环 (原版仅构造未启动)
├── GameLauncher$2.java   — 消息循环线程: 完整性检查 + Looper.prepare/loop + 就绪信号
├── GameLauncher$3.java   — startGameEvent 主线程任务: 地图加载检查 + 恢复游戏 UI
└── GameLauncher$4.java   — 聊天消息转发到 UI 脚本引擎

## 验证结果 (三重门禁)

| 门禁 | 结果 |
|------|------|
| javap 字段对齐 | 22/22 (顺序、类型与字节码一致) |
| javap 方法对齐 | 21 方法 + 1 构造器 = 22/22 |
| 常量池字符串交叉验证 | 120/121 出现在源文件 (1 个为正则伪匹配) |
| javac (24 真实 jars) | 0 个真实代码错误; 其余均为派生类名未落地导致的符号缺失 (预期) |

字节码真源: `01-classes/com/corrodinggames/rts/java/Main.class` 是该包唯一 .class 文件,
其余引用类 (b/c/d/u/v...) 均来自 `RustedWarfare/game-lib.jar` (混淆名) — 因此 04-javas 中的
派生类名 (见下) 需在后续重命名批次中落地到 03-deobfuscated 树方可整树编译。

## 命名决策 (evidence-anchored)

### GameLauncher 自身成员

| 混淆 | 新名 | 依据 |
|------|------|------|
| a (static boolean) | forceVertexArrayRenderer | `if(a) Renderer.setRenderer(2)`; slick.jar 字节码: 2=VAOGLRenderer。**推翻 supplement isRunning** |
| b/c/d/e | useSlick2D / windowTitle / platformConfig / versionString | supplement 沿用 |
| f (utility.aj) | taskQueue | aj = ConcurrentLinkedQueue\<Runnable\> + 入队/全执行 |
| g | stdinLoopActive | runStdinLoop 的 while 条件 |
| h (j.ad) | netEngine | `this.h = globalState.netEngine`。**推翻 supplement gameInstance** |
| i (java.b.a) | commonGuiEngine | 类内自证 "CommonGuiEngine already exists" |
| j (java.u) | gameContainer | 类型 DesktopGameContainer。**推翻 supplement gameSettings** |
| k (java.b) | gameWindow | GameWindow (AppGameContainer 包装) |
| l/m/n/o | startupArgs / instance / stdinErrorCount / startupTimeNanos | 用法直读 |
| p (java.d.a) | libRocketRenderer | 类内自证 "not supported on SlickLibRocket" |
| q (gameFramework.n) | desktopPlatform | `= new DesktopPlatform(this)` |
| r/s/t | displayThread / consoleCommandsEnabled / lock | 用法直读 |
| u/v | fullscreenMode / targetFPS | supplement 沿用 |

方法: main / logLine / runStdinLoop / waitForMessageLoop / initializeFromArgs /
updateProgressText / initializeGame / startGameEvent / runQueuedTasks / exitGame /
isCommandAllowed / onChatMessage / refreshLobbyUI / queueChatMessage / handleChatMessage /
getPlayerDisplayName / onPrivateMessage / onPlayerDisconnect / reapplyWindowSettings /
closeBattleroom / promptPassword。其中 NetworkAuth 覆盖方法以基类日志锚定
("NetworkCallbacks:startGameEvent()"), closeBattleroom 以 librocket.a.o() 体
("closeBattleroomIfOpen") 锚定。

### 类映射冲突修正 (class-discoveries.csv 已更新)

| 混淆类 | 原映射 | 修正为 | 锚点 |
|--------|--------|--------|------|
| java.b.a | TextureProxy (03树) | **CommonGuiEngine** | 类内异常消息自证 |
| java.d.a | filesystem.a (03树) | **SlickLibRocket** | 类内异常消息自证 |
| java.k | GameConfig | **ApacheHttpClientPool** | extends j.r, Apache HttpClient create/close |
| java.l | ResourceLoader | **DesktopMusicPlayer** | OpenALAudio 构造 + extends aq 音乐基类 |
| java.o | UpdateChecker | **OpenALSoundEngine** | implements a.h (SoundFactory) |
| java.v | ModDownloader | **DesktopInputProvider** | extends ai (InputProvider), 键码映射+0手柄 |
| gameFramework.aq | TeamColor | **MusicPlayer** | 抽象音乐播放器基类 (protected am e) |
| gameFramework.av | PingTimer | **NullMusicPlayer** | 日志 "Null musicFactory - load" |
| rts.a.a | (无) | **DebugServer** | ServerSocket + 脚本队列 (-debug/-debugscript) |
| utility.aj / utility.l | (无) | **MainThreadTaskQueue / BufferedLineReader** | 类体直读 |
| gameFramework.j.r | (已有) | HttpClientPool | 项目库已有正确条目, 未改动 |

### GlobalState 开关批量修正 (supplement.csv 已更新)

Main 的 -flag 解析是 GlobalState 静态开关的直接锚点。以下 supplement 行与用法矛盾, 已修正
(原 log* 系列与 isAmazon/isSteam 呈批量错位):

| 字段 | 原映射 | 修正为 | -flag |
|------|--------|--------|-------|
| aH | logInput | **steamEnabled** | -steam |
| ax | crashOnUpdateThread | **colorLogOutput** | -logcolor |
| ay | nativeCrashDetected | **backgroundDisabled** | -nobackground |
| aI | logMemory | **sandboxModeEnabled** | -sandbox |
| aJ | logLifecycle | **modsDisabled** | -nomods |
| aK | logTagPrefix | **lobbyAddress** | +connect_lobby |
| aO | useVulkan | **safeModeEnabled** | -safemode |
| aP | useANGLE | **extraSafeModeEnabled** | -extrasafemode |
| aQ | gpuRendererString | **devDebugParameter** | -devdebug |
| aE | logNetwork | **printUnitsEnabled** | -printunits |
| aF | logAudio | **outputUnitImagesEnabled** | -outputunitimages |
| aG | logRendering | **oldReplayFormatEnabled** | -oldreplays |
| aC | debugAllocations | **textureAtlasDisabled** | -disable_atlas |
| aM | shaderEffectsSupported | **postProcessingEnabled** | -postprocessing |
| bb | isAmazon | **displayInitialized** | 桌面启动置真 (低置信) |
| aW | reloadAssets | **resourcesEnabled** | 仅资源启用分支置真 (低置信) |
| bg | mainActivityClass | **rendererClass** | 值为渲染器 Class 对象 |
| ck | mouseScreenPos | **screenSize** | new Point(宽,高) |
| aB | (v1200占位) | **resourcesDisabled** | -noresources |
| aD | getContext (v1200占位) | **canvasGLMode** | -canvasgl |
| aw | (v1200占位) | **replayDebugEnabled** | -replay_debug |

另新增: game.i.b → is64BitSystem (= Sys.is64Bit()), game.i.c(字段) → isMacOS,
am.a → musicPlayer, a.e.c → soundFactory, h.a.d → languageOverride, o.a.a → instance,
java.s.F → textureReadEnabled, j.n.d → httpClientPool, j.ad.d → authHandler,
j.ad.u() → waitForShutdown, j.ad.b(String) → disconnect, o.a.b() → init,
java.e.c() → captureRenderer, java.b.a.p() → getInstance, java.b.a.a(b,f) → attachRenderer,
rts.a.a 三静态 → startDebugServer/addDebugScript/runDebugScripts。

### 未锚定项 (保留混淆名 + 行内注释)

- GlobalState.aU (isDedicatedServer) / aX (skipRendering): 沿用 supplement, 语义低置信
- GlobalState.c(boolean) (int 返回, 疑为 game code)
- NetEngine.y (bindAddress) — 沿用 supplement, "unset" 赋值
- GameLauncher$3 内: appFramework.n.r(), gameRenderer.W, netEngine.af()/bd, librocket.a.f(), commonGuiEngine.c(boolean)
- main() 中 `new GameLauncher$1()` 未启动 (字节码忠实还原, 原版如此)

## 映射库更新

- supplement.csv: **+51 新增 / 21 修正** (verified=main-deobf-anchored)
- class-discoveries.csv: **+5 新增 / 6 修正** (notes=main-deobf-anchored)
- 备份: mappings/_archive/{supplement,class-discoveries}-20260816-main-deobf-backup.csv
- 回写脚本: tools/fixers/main_class_mappings.py (--dry-run/--apply)

## 后续建议

1. 将 04-javas 的派生类名批量落地到 03-deobfuscated (TextureProxy→CommonGuiEngine 等 11 类)
2. GlobalState 开关修正同步应用到 l.java (GlobalState.java) 本体
3. 04-javas 目录纳入 .gitignore 或作为第二输出目录管理 (当前未入库约定待定)

## 战役裁决与发现附录

> 各战役在本域的关键裁决/发现索引 (详细会话记录已删除, 映射证据见 mappings/ notes)

- **v19.133f14-ModsUI与filesystem域清零战役**: —
- **v19.133f15-测试族与SteamWorkshop清零战役**: —
- **v19.133f17-IntArray家族清零战役**: 字节码裁决: f.a.k ≠ ActionPanel
- **v19.133f37-audio-backend-l家族清零战役**: —
- **v19.133f6-AIWaveSystem清零-SAFFileManager幻影家族**: AIWaveSystem 9 错误清零 / SAFFileManager 幻影家族 / filesystem 家族收尾 / InGameActivity 连锁

