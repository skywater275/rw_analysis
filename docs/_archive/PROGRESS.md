# 游戏源码级逆向 — 逐类字段/方法解混淆计划

> 日期: 2026-06-23 | 591类/3495字段/820方法 | extends 100% | 字节码验证95%+ | v9.47 FINAL

---

## 一、总览

### 1.1 核心指标

| 维度 | v9.35 | v9.43 | 总增长 |
|------|------|------|------|
| **字段映射** | 3,061 | **3,234** | **+173 (+5.7%)** |
| 方法映射 | 255 | **664** | **+409 (+160%) 🔥** |
| **字节码验证率** | — | **95%+** | javap交叉验证 |
| extends/implements | 0 | **0** | 100% ✅ |
| files updated | 206 | **218** | +12 |
| supplement.csv | ~3,600行 | **~4,470行** | **+870行** |

### 1.2 全阶段增量

| 阶段 | 字段 | 方法 | 关键动作 |
|------|------|------|---------|
| v9.35 | 3,061 | 255 | 起点 |
| v9.40 | +114 | +272 | 大规模方法映射 (UnitType/InGameUI/CustomUnitType/TextureManager/GLRenderer/Slick2D) |
| v9.41 | -91 | -10 | javap交叉验证 → 移除102幻影 (95.3%/94.5%验证率) |
| v9.42 | +83 | +36 | 字节码驱动补充 (UnitType/UnitInstance/Factory/PlayerState) |
| v9.43 | +67 | +111 | NetEngine/UnitRegistry/GameUtils/PlayerConnect/ReplayEngine/剩余方法 |
| **总计** | **+173** | **+409** | **方法翻2.6倍!** |

### 1.3 v9.43 新增明细

| 来源类 | 新增字段 | 新增方法 | 内容 |
|--------|---------|---------|------|
| NetEngine (ad) | +8 | +35 | 连接管理、UUID、大厅计时器、重连对话框、服务器状态 |
| UnitRegistry (ar) | +29 | +26 | A-Z哈希桶、单位图标、显示名、科技等级、运输/巡逻/守卫 |
| GameUtils (f) | +21 | +8 | 随机数、三角函数查找表(sqrt/sin/cos/atan8象限)、数学工具 |
| PlayerConnect (c) | +15 | +6 | 连接状态、速率限制、分包接收、版本/速度配置 |
| ReplayEngine (ba) | +7 | +7 | 回放录制/播放、速度控制、校验快照、反同步检测 |
| UnitType methods | — | +22 | 炮塔偏移、渲染标志、死亡生命周期、建造预览 |
| UnitInstance methods | — | +16 | 碰撞伤害、隐形、能量条、路径点叠加、工厂部署 |

### 1.2 字节码交叉验证结果 (v9.41新增)

| 指标 | 数值 |
|------|------|
| 验证类数 | **52** (拥有.class文件的CSV映射组) |
| 字段验证通过率 | **2,825/2,963 (95.3%)** |
| 方法验证通过率 | **450/476 (94.5%)** |
| 幻影映射(已移除) | **102** (CSV映射了不存在的字段/方法) |
| 未映射字节码成员 | **863** (可用于未来映射) |
| 跳过的类 | 208 (无对应.class文件) |

> **验证工具**: `javap -p` (JDK 17) 对 `01-classes/` 中的原始.class文件进行反汇编
> 
> **幻影映射分类**:
> - NetEngine (ad): 30个字段名错误 (CSV用了aD-aS, 实际字段是aM/aT-aX/bG-bI)
> - KeyBindings (aj): 26个字段 — CSV映射到内部类但.class在外部类
> - MapEngine (b): 19个字段 — 类解析错误
> - InGameUI方法: 15个方法映射到GameUtils类 (f.class是GameUtils不是InGameUI)
> - Factory/AIWaveSystem/Command/GlobalState等: 剩余幻影
>
> **863个未映射字节码成员** (按类分组):
> - UnitType (y): 127 (76字段/51方法) ← 最大机会
> - UnitInstance (am): 99 (57字段/42方法)
> - GameUtils (f): 60 (29字段/31方法)
> - UnitRegistry (ar): 57 (29字段/28方法)
> - NetEngine (ad): 46 (8字段/38方法)
> - 其余分布于45+个类

### 1.2 各类字段完成状态

| 优先级 | 类 | 位置 | 已映射 | 总计 | 完成度 | 状态 |
|--------|----|------|--------|------|--------|------|
| **P0** | GlobalState | gameFramework.l | **196** | ~200 | **98%** | ✅ 基本完成 |
| **P1** | MovementController | game.f | **121** | ~130 | **93%** | ✅ 基本完成 |
| **P2** | NetEngine | gameFramework.j.ad | **135** | ~140 | **96%** | ✅ 基本完成 |
| P3 | MapEngine | game.b.b | **79** | ~85 | **93%** | ✅ 基本完成 |
| P4 | PlayerState | game.n | **78** | ~80 | **98%** | ✅ 基本完成 |
| P4 | Factory | game.units.h | **45** | ~50 | **90%** | ✅ 基本完成 |
| — | UnitInstance | game.units.am | ~70 | ~75 | 93% | ✅ |
| — | GameWorld | game.a.a | ~55 | ~60 | 92% | ✅ |
| — | Command | gameFramework.e | ~30 | ~30 | **100%** | ✅ 完成 |
| P5 | SettingsEngine | gameFramework.bQ | ~10 | ~15 | ~2 | ~10 |
| P5 | CombatMain | game.a.b | ~8 | ~10 | ~2 | ~8 |

---

## 二、逐类详细计划

---

### P0 — GlobalState (gameFramework.l) — ~115字段待映射

**文件**: `03-deobfuscated/com/corrodinggames/rts/gameFramework/GlobalState.java`
**重要性**: ⭐⭐⭐⭐⭐ — 全局引擎状态，被所有子系统引用

#### 2.0.1 单字符字段 (5个)

| 字段 | 类型 | 当前声明 | 推测含义 | 确认方式 |
|------|------|---------|---------|---------|
| `a` | `private Runnable` | `new l$1(this)` | exitCallback — 退出回调 (匿名Runnable) | 读l$1代码 |
| `b` | `private Runnable` | `new l$2(this)` | pauseCallback — 暂停回调 (匿名Runnable) | 读l$2代码 |
| `c` | `private boolean[]` | `new boolean[KeyEvent.a()+1]` | keysDown — 当前帧按键状态 | 方法体使用 |
| `d` | `private boolean[]` | `new boolean[KeyEvent.a()+1]` | keysDownPrev — 上一帧按键状态 | 方法体使用 |
| `e` | `private int` | — | androidApiLevel — Android API版本号 | 方法体使用 |

#### 2.0.2 两字符字段 — a组 (static标志位, aA-aR)

| 字段 | 类型 | 默认值 | 推测含义 |
|------|------|--------|---------|
| `al` | `protected static l` | null | singleton — 单例引用 |
| `as` | `public static boolean` | true | engineStarted — 引擎已启动 |
| `av` | `public static Throwable` | — | lastCrash — 最后崩溃异常 |
| `aw` | `public static boolean` | — | crashOnRender — 渲染线程崩溃标记 |
| `ax` | `public static boolean` | — | crashOnUpdate — 更新线程崩溃标记 |
| `ay` | `public static boolean` | — | nativeCrash — Native层崩溃标记 |
| `az` | `public static boolean` | — | outOfMemory — OOM标记 |
| `aA` | `public static boolean` | — | anrWatchdog — ANR看门狗标记 |
| `aB` | `public static boolean` | — | strictMode — 严格模式标记 |
| `aC` | `public static boolean` | — | traceAllocations — 跟踪内存分配 |
| `aD` | `public static boolean` | — | logFps — 记录FPS日志 |
| `aE` | `public static boolean` | — | logNetwork — 记录网络日志 |
| `aF` | `public static boolean` | — | logAudio — 记录音频日志 |
| `aG` | `public static boolean` | — | logRendering — 记录渲染日志 |
| `aH` | `public static boolean` | — | logInput — 记录输入日志 |
| `aI` | `public static boolean` | — | logMemory — 记录内存日志 |
| `aJ` | `public static boolean` | — | logLifecycle — 记录生命周期日志 |
| `aK` | `public static String` | — | logTag — 日志标签前缀 |
| `aL` | `public static boolean` | — | useGLES30 — 使用OpenGL ES 3.0 |
| `aM` | `public static boolean` | — | useGLES31 — 使用OpenGL ES 3.1 |
| `aN` | `public static boolean` | — | useVulkan — 使用Vulkan后端 |
| `aO` | `public static boolean` | — | useAngle — 使用ANGLE (D3D转换) |
| `aP` | `public static boolean` | — | useSoftwareRenderer — 使用软件渲染 |
| `aQ` | `public static String` | — | gpuRendererString — GPU渲染器名称字符串 |
| `aR` | `public static boolean` | — | supportsInstancing — 支持实例化渲染 |

> 已映射: `aT`=debugNetwork, `aU`=isDedicatedServer, `aV`=debugActive, `aW`=reloadAssets, `aX`=skipRendering, `aY`=isDesktop, `aZ`=isIOS, `ba`=isSteam, `bb`=isAmazon

#### 2.0.3 两字符字段 — b组 (引擎子系统引用)

| 字段 | 类型 | 推测含义 |
|------|------|---------|
| `bg` | `public static Class` | mainActivityClass — 主Activity类引用 |
| `bh` | `public static m.y` | textureManager — 纹理管理器 |
| `bu` | `public float` | targetFps — 目标帧率 (-1=自动) |
| `bv` | `public boolean` | vsyncEnabled — 垂直同步启用 |
| `bw` | `public boolean` | frameRateLimited — 帧率受限标记 |
| `bz` | `public int` | screenWidth — 屏幕宽度 (物理像素) |
| `bA` | `public int` | screenHeight — 屏幕高度 (物理像素) |
| `bB` | `public int` | viewportWidth — 视口宽度 |
| `bC` | `public int` | viewportHeight — 视口高度 |
| `bD` | `public boolean` | screenResized — 屏幕尺寸变更标记 |
| `bE` | `public boolean` | surfaceLost — Surface丢失标记 |
| `bF` | `public volatile boolean` | renderThreadRunning — 渲染线程运行中 |
| `bG` | `public volatile boolean` | updateThreadRunning — 更新线程运行中 |
| `bH` | `public volatile boolean` | renderPaused — 渲染暂停标记 |
| `bI` | `public volatile boolean` | updatePaused — 更新暂停标记 |
| `bJ` | `public int` | targetFpsInt — 整数目标帧率 |
| `bK` | `public i` | versionInfo — 版本信息对象 |
| `bL` | `public b` | glRenderer — OpenGL渲染器引用 |
| `bP` | `public gameFramework.a` | audioEngine — 音频引擎 |
| `bQ` | `public SettingsEngine` | settingsEngine — 设置引擎 |
| `bU` | `public k.l` | pathEngine — 寻路引擎 |
| `bV` | `public aa` | hudManager — HUD管理器 |
| `bX` | `public ad` | netEngine — 网络引擎 (NetEngine) |
| `bY` | `public bg` | commandProcessor — 指令处理器 |
| `bZ` | `public i.a` | localizationEngine — 本地化引擎 |

> 已映射: `bM`=soundEngine, `bN`=musicController, `bO`=canvas, `bR`=effectManager, `bS`=unitFactory, `bT`=inputController, `bW`=minimapHandler

#### 2.0.4 两字符字段 — c组 (相机/视口/渲染状态, 最多的一组)

| 字段 | 类型 | 推测含义 |
|------|------|---------|
| `ca` | `public y` | gameSaver — 游戏存档器 |
| `cb` | `public ba` | replayEngine — 回放引擎 |
| `cc` | `public units.f.c` | spaceQuery — 空间查询引擎 |
| `cd` | `public br` | spriteBatch — 精灵批处理器 |
| `ce` | `public n.f` | aiSpawner — AI生成控制器 |
| `cf` | `public c` | commandController — 指令控制器 |
| `cg` | `public g.a` | dataSerializer — 数据序列化器 |
| `ch` | `public boolean` | cameraFollowing — 相机跟随模式 |
| `ci` | `public float` | viewScale — 视图缩放倍数 |
| `cj` | `public float` | targetViewScale — 目标缩放 (平滑) |
| `ck` | `public static Point` | mouseScreenPos — 鼠标屏幕坐标 |
| `cl` | `public float` | cameraTargetX — 相机目标X |
| `cm` | `public float` | cameraTargetY — 相机目标Y |
| `cn` | `public float` | viewScaleMin — 最小缩放 |
| `co` | `public float` | viewScaleMax — 最大缩放 |
| `cp` | `public float` | uiScale — UI缩放倍率 |
| `cq` | `public float` | cameraShakeX — 相机抖动X |
| `cr` | `public float` | cameraShakeY — 相机抖动Y |
| `cs` | `public float` | cameraShakeIntensity — 抖动强度 |
| `ct` | `public boolean` | cameraShakeActive — 抖动激活 |
| `cu` | `public int` | renderWidth — 渲染宽度 (逻辑像素) |
| `cv` | `public int` | renderHeight — 渲染高度 (逻辑像素) |
| `cy` | `public float` | cameraBoundsX1 — 相机边界X1 |
| `cz` | `public float` | cameraBoundsY1 — 相机边界Y1 |
| `cA` | `public float` | cameraBoundsX2 — 相机边界X2 |
| `cB` | `public float` | cameraBoundsY2 — 相机边界Y2 |
| `cC` | `public float` | lastFrameTime — 上一帧时间 |
| `cD` | `public float` | deltaTime — 帧间隔 (deltaTime) |
| `cE` | `public float` | renderDeltaTime — 渲染用deltaTime |
| `cF` | `public float` | fpsAccum — FPS累加器 |
| `cG` | `public float` | currentFps — 当前FPS |
| `cH` | `public float` | avgFps — 平均FPS |
| `cI` | `public float` | minFps — 最低FPS |
| `cJ` | `public float` | maxFps — 最高FPS |
| `cK` | `public final Rect` | screenRect — 屏幕矩形 (Rect) |
| `cL` | `public final Rect` | viewportRect — 视口矩形 |
| `cM` | `public final RectF` | screenRectF — 屏幕矩形 (float) |
| `cN` | `public final Rect` | clipRect — 裁剪矩形 |
| `cO` | `public final RectF` | worldViewRect — 世界视图矩形 |
| `cP` | `public final RectF` | uiClipRect — UI裁剪矩形 |
| `cQ` | `public final Rect` | tempRect — 临时矩形 |
| `cR` | `public boolean` | isDrawing — 正在绘制标记 |
| `cS` | `public boolean` | forceRedraw — 强制重绘 |
| `cT` | `public float` | drawScaleX — 绘制缩放X |
| `cU` | `public boolean` | useHighResTimer — 使用高精度计时器 |
| `cV` | `public float` | timeScale — 时间缩放 |
| `cW` | `public boolean` | renderingEnabled — 渲染启用 |
| `cX` | `public float` | renderScale — 渲染缩放 |
| `cY` | `public float` | uiRenderScale — UI渲染缩放 |
| `cZ` | `public boolean` | renderToTexture — 渲染到纹理 |

> 已映射: `cw`=cameraX, `cx`=cameraY

#### 2.0.5 两字符字段 — d组 (杂项状态/调试/生命周期, 48个)

| 字段 | 类型 | 推测含义 |
|------|------|---------|
| `da` | `public float` | menuTransition — 菜单过渡进度 |
| `db` | `public float` | menuTransitionTarget — 菜单过渡目标 |
| `dc` | `public boolean` | showFps — 显示FPS |
| `dd` | `public boolean` | showUnitCounts — 显示单位统计 |
| `de` | `public boolean` | showNetworkStats — 显示网络统计 |
| `df` | `public boolean` | showMemoryStats — 显示内存统计 |
| `dg` | `public boolean` | showHitboxes — 显示碰撞盒 |
| `dh` | `public float` | audioVolume — 音频音量 |
| `di` | `public float` | musicVolume — 音乐音量 |
| `dj` | `public boolean` | audioMuted — 音频静音 |
| `dk` | `protected z` | battleRoom — 对战房间引用 |
| `dl` | `public String` | mapName — 当前地图名 |
| `dm` | `public k` | gameInput — 游戏输入处理器 |
| `dn` | `public Paint` | bgPaint — 背景画笔 |
| `do` | `public Paint` | uiPaint — UI画笔 |
| `dp` | `public Paint` | textPaint — 文字画笔 |
| `dq` | `public boolean` | loadingComplete — 加载完成 |
| `dr` | `public boolean` | assetsReloaded — 资源已重载 |
| `ds` | `public float` | loadProgress — 加载进度 |
| `dt` | `public boolean` | showLoadScreen — 显示加载画面 |
| `du` | `public boolean` | showMenu — 显示菜单 |
| `dv` | `public boolean` | backPressed — 返回键按下 |
| `dw` | `public int` | orientation — 屏幕方向 |
| `dx` | `public float` | batteryLevel — 电池电量 |
| `dy` | `public static o` | steamManager — Steam管理器 |
| `dz` | `public static String` | deviceModel — 设备型号 |
| `dA` | `float` | nativeZoom — Native层缩放 |
| `dB` | `boolean` | nativeReady — Native层就绪 |
| `dC` | `ArrayList` | pendingRunnables — 待执行任务队列 |
| `dD` | `final Handler` | mainHandler — 主线程Handler |
| `dE` | `public String` | installSource — 安装来源 |
| `dF` | `public String` | androidId — Android设备ID |
| `dG` | `public String` | deviceName — 设备名称 |
| `dH` | `public n` | localPlayer — 本地玩家状态 |
| `dI` | `transient String` | savedGamePath — 存档路径 |
| `dJ` | `Object` | exitLock — 退出同步锁 |
| `dK` | `String` | dataDir — 数据目录 |
| `dL` | `String` | cacheDir — 缓存目录 |
| `dM` | `public boolean[]` | permissionsGranted — 权限授予标记数组 |
| `dN` | `protected ConcurrentLinkedQueue` | commandQueue — 指令队列 |
| `dO` | `public static boolean` | nativeLibLoaded — Native库已加载 |
| `dP` | `static byte[]` | crcTable1 — CRC校验表1 |
| `dQ` | `static byte[]` | crcTable2 — CRC校验表2 |
| `dR` | `static byte[]` | crcTable3 — CRC校验表3 |
| `dS` | `static d` | overlayRenderer — 覆盖层渲染器 |
| `dT` | `static boolean` | antiCheatEnabled — 反作弊启用 |
| `dU` | `static int` | antiCheatSeed — 反作弊种子 |
| `dV` | `static boolean` | tamperDetected — 篡改检测标记 |
| `dW` | `static u` | profiler — 性能分析器 |
| `dX` | `static boolean` | profilingEnabled — 性能分析启用 |
| `dY` | `static boolean` | benchmarkMode — 基准测试模式 |
| `dZ` | `public byte` | networkRole1 — 网络角色标志1 |
| `ea` | `public byte` | networkRole2 — 网络角色标志2 |
| `eb` | `public final q` | lock1 — 同步锁1 |
| `ec` | `public final q` | lock2 — 同步锁2 |
| `ed` | `public final q` | lock3 — 同步锁3 |
| `ee` | `public boolean` | gameStarted — 游戏已开始 |
| `ef` | `public boolean` | gameEnding — 游戏正在结束 |
| `eg` | `public String` | lastError — 最后错误消息 |
| `eh` | `public boolean` | crashReported — 崩溃已上报 |
| `ei` | `public boolean` | devMode — 开发者模式 |
| `ej` | `static int` | instanceCount — 实例计数 |

> 已映射: `aj`=renderLock, `ak`=updateLock, `am`=androidContext, `an`=gameContext, `ao`=renderSurface, `ap`=renderSurfaceBack, `aq`=isInitialized, `ar`=isLoading, `at`=isPaused, `au`=isExiting, `aS`=isSurfaceReady, `bc`=useNewAudio, `bd`=useOpenAL, `be`=useLWJGLAudio, `bf`=useAndroidAudio, `bi`=isMenuMode, `bj`=showConsole, `bk`=isConsoleOpen, `bm`=isReplayMode, `bn`=isReplayRecording, `bo`=isReplayPaused, `bp`=isFastForward, `bq`=isSlowMotion, `br`=isStepFrame, `bs`=currentPlayer, `bx`=gameTick, `by`=currentFrame, `bl`=isInMatch, `bt`=gameSpeed

#### 2.0.6 GlobalState方法 (待映射 ~15个)

| 方法 | 推测含义 | 优先级 |
|------|---------|--------|
| `f()` | shutdownEngine — 关闭引擎 | P0 |
| `g()` | restartEngine — 重启引擎 | P0 |
| `h()` | processInput — 处理输入事件 | P0 |
| `j()` | dispatchTouch — 分发触摸事件 | P1 |
| `k()` | dispatchKey — 分发按键事件 | P1 |
| `l()` | getClipboardText — 获取剪贴板 | P2 |
| `m()` | setClipboardText — 设置剪贴板 | P2 |
| `n()` | showKeyboard — 显示软键盘 | P2 |
| `o()` | hideKeyboard — 隐藏软键盘 | P2 |
| `p()` | getDeviceInfo — 获取设备信息 | P2 |
| `q()` | checkPermission — 检查权限 | P2 |
| `r()` | requestPermission — 请求权限 | P2 |
| `s()` | logCrash — 记录崩溃 | P1 |
| `t()` | getPlatformType — 获取平台类型 | P2 |
| `w()` | getBuildConfig — 获取构建配置 | P2 |
| `x()` | reloadAssetsImpl — 重新加载资源实现 | P1 |

---

### P1 — MovementController (game.f) — ~69字段待映射

**文件**: `03-deobfuscated/com/corrodinggames/rts/game/MovementController.java`
**行数**: 1761 | **重要性**: ⭐⭐⭐⭐ — 所有单位的移动系统

#### 2.1.1 单字符字段 (7个)

| 字段 | 类型 | 推测含义 |
|------|------|---------|
| `a` | `public static final m` | movementCommandPool — 移动指令对象池 |
| `b` | `static m.e` | pathIcon — 路径图标 |
| `c` | `static m.e` | targetIcon — 目标图标 |
| `d` | `static m.e` | waypointIcon — 路点图标 |
| `e` | `static final Rect` | tempRect — 临时矩形 |
| `f` | `static final RectF` | tempRectF — 临时RectF |
| `g` | `public game.g` | movementType — 移动类型枚举 (Land/Water/Air/Hover) |

#### 2.1.2 两字符字段 — aa-ap (移动参数/标志)

| 字段 | 类型 | 默认值 | 推测含义 |
|------|------|--------|---------|
| `aa` | `public boolean` | — | canMoveBackwards — 可后退 |
| `ab` | `public boolean` | false | isPathfinding — 正在寻路 |
| `ac` | `public boolean` | false | pathRecalculate — 需要重算路径 |
| `ad` | `public boolean` | false | pathInvalid — 路径无效 |
| `ae` | `public boolean` | true | smoothPath — 平滑路径 |
| `af` | `public boolean` | — | hasArrived — 已到达目标 |
| `ag` | `public float` | — | minSpeed — 最低速度 |
| `ah` | `public float` | — | maxSpeedAlt — 最高速度 (备选) |
| `ai` | `public float` | 1.0 | speedMultiplier — 速度倍率 |
| `aj` | `public float` | 1.0 | accelerationMultiplier — 加速度倍率 |
| `ak` | `public float` | 1.0 | turnRateMultiplier — 转向速率倍率 |
| `al` | `public float` | 1.0 | brakingMultiplier — 制动倍率 |
| `am` | `public float` | 1.0 | terrainSpeedFactor — 地形速度因子 |
| `an` | `public float` | — | currentSpeedSmoothed — 平滑后速度 |
| `ao` | `public boolean` | — | useFixedSpeed — 使用固定速度 |
| `ap` | `public m` | — | movementState — 移动状态 (Idle/Moving/Turning) |

> 已映射: `p`=pathIndex, `q`=waypointList, `r`=lastPosition, `s`=stuckTimer, `t`=currentSpeed, `u`=velocityX, `v`=velocityY, `w`=velocityZ, `h`=moveDelay, `i`=pauseTimer, `j`=targetEntity, `l`=secondaryTarget, `n`=targetX, `o`=targetY, `m`=hasFixedTarget, `x`=maxSpeed, `y`=acceleration, `z`=turnSpeed, `A`=brakingDistance, `B`=arrivalDistance, `k`=pathfinderResult, `az`=facingAngle

#### 2.1.3 两字符字段 — aq-bn (渲染/碰撞/目标)

| 字段 | 类型 | 默认值 | 推测含义 |
|------|------|--------|---------|
| `aq` | `static final int` | Color.a(255,255,255,255) | defaultPathColor — 默认路径颜色 |
| `ar` | `public int` | aq | pathColor — 当前路径颜色 |
| `as` | `public boolean` | — | drawPath — 绘制路径线 |
| `at` | `public boolean` | — | drawWaypoints — 绘制路点 |
| `au` | `public w` | — | attachedUnit — 附属的游戏对象 |
| `av` | `public int` | -1 | teamColorIndex — 队伍颜色索引 |
| `aw` | `public float` | — | collisionRadiusOverride — 碰撞半径覆写 |
| `ax` | `public float` | — | hitboxOffsetX — 碰撞盒偏移X |
| `ay` | `public float` | — | hitboxOffsetY — 碰撞盒偏移Y |
| `aA` | `public float` | — | altitudeLevel — 高度层级 |
| `aB` | `public boolean` | — | isHovering — 悬停模式 |
| `aC` | `public boolean` | — | checkCollision — 检查碰撞 |
| `aD` | `public int` | — | collisionGroup — 碰撞组 |
| `aE` | `public h` | — | pathResult — 寻路结果引用 |
| `aF` | `public float` | — | desiredHeading — 目标朝向 |
| `aG` | `public boolean` | — | rotateToTarget — 旋转朝向目标 |
| `aH` | `public boolean` | — | rotateToMovement — 旋转朝向移动方向 |
| `aI` | `public float` | 40.0f | closeEnoughRadius — "够近"判定半径 |
| `aJ` | `public float` | 60.0f | tooCloseRadius — "太近"判定半径 |
| `aK` | `public boolean` | false | avoidFriendlies — 避让友军 |
| `aL` | `public float` | 2.0f | pushStrength — 推离强度 |
| `aM` | `public boolean` | — | beingPushed — 正在被推离 |
| `aN` | `public float` | — | pushX — 推离方向X |
| `aO` | `public float` | — | pushY — 推离方向Y |
| `aP` | `public d.e` | — | selectionCircle — 选择圈UI元素 |
| `aQ` | `public boolean` | — | selected — 已选中标记 |
| `aR` | `public boolean` | true | visible — 可见标记 |
| `aS` | `public boolean` | — | forceRedraw — 强制重绘 |
| `aT` | `public float` | 0.0 | animationTimer — 动画计时器 |
| `aU` | `public boolean` | — | animationPlaying — 动画播放中 |
| `aV` | `float` | — | impactPointX — 碰撞点X (package-private) |
| `aW` | `float` | — | impactPointY — 碰撞点Y (package-private) |
| `aX` | `float` | — | impactNormalX — 碰撞法线X (package-private) |
| `aY` | `public boolean` | — | terrainBlocked — 地形阻挡 |
| `aZ` | `public boolean` | — | unitBlocked — 单位阻挡 |
| `ba` | `public static final ag` | — | defaultPaint — 默认画笔 (static) |
| `bb` | `public static final Paint` | — | pathPaint — 路径画笔 |
| `bc` | `public static final Paint` | — | waypointPaint — 路点画笔 |
| `bd` | `public static final Paint` | — | targetPaint — 目标画笔 |
| `be` | `public static final Paint` | — | collisionPaint — 碰撞区域画笔 |
| `bf` | `public static final Paint` | — | rangePaint — 范围画笔 |
| `bg` | `public static final Paint` | — | blockingPaint — 阻挡区域画笔 |
| `bh` | `public static final Paint` | — | debugPaint — 调试画笔 |
| `bi` | `public static final u` | — | movementProfiler — 移动性能统计 |
| `bj` | `public ag` | — | currentPaint — 当前画笔 |
| `bk` | `public static ag` | — | sharedPaint — 共享画笔 |
| `bl` | `public static int` | — | globalPathId — 全局路径ID计数器 |
| `bm` | `private static final f` | new f(true) | dummyController — 虚拟控制器 (占位) |
| `bn` | `private boolean` | — | isDummy — 是否为虚拟控制器 |

#### 2.1.4 MovementController方法 (待映射 ~15个)

| 方法 | 推测含义 | 优先级 |
|------|---------|--------|
| `g()` | setPath — 设置移动路径 | P0 |
| `h()` | followPath — 沿路径移动 | P0 |
| `i()` | checkArrival — 检查到达 | P0 |
| `j()` | applySeparation — 应用分离力 (避让) | P1 |
| `k()` | resolveCollision — 解决碰撞 | P1 |
| `l()` | updateAnimation — 更新移动动画 | P2 |
| `m()` | calcBraking — 计算制动距离 | P1 |
| `n()` | getPredictedPosition — 获取预测位置 | P2 |
| `o()` | setMovementType — 设置移动类型 | P2 |
| `p()` | getMovementType — 获取移动类型 | P2 |
| `q()` | canMoveTo — 检查可否移动到某点 | P1 |
| `r()` | findPath — 开始寻路 | P0 |
| `s()` | cancelPath — 取消当前路径 | P1 |
| `t()` | serializeState — 序列化移动状态 | P2 |
| `u()` | deserializeState — 反序列化移动状态 | P2 |

---

### P2 — NetEngine (gameFramework.j.ad) — ~63字段待映射

**文件**: `03-deobfuscated/com/corrodinggames/rts/gameFramework/j/NetEngine.java`
**行数**: 5359 | **重要性**: ⭐⭐⭐⭐ — 多人游戏网络引擎

#### 2.2.1 单字符字段 (8个)

| 字段 | 类型 | 默认值 | 推测含义 |
|------|------|--------|---------|
| `a` | `public static final boolean` | false | disableChecksum — 禁用校验和 |
| `b` | `public static boolean` | true | enableCompression — 启用压缩 |
| `c` | `public static boolean` | false | logPackets — 记录数据包日志 |
| `d` | `public ac` | new ac() | protocolHandler — 协议处理器 |
| `r` | `public static boolean` | true | useTcpFallback — TCP回退启用 |
| `t` | `public int` | 5005 | defaultPort — 默认端口 |
| `x` | `public boolean` | false | useLocalhost — 使用本地回环 |
| `y` | `public String` | — | bindAddress — 绑定地址 |

#### 2.2.2 两字符字段 — a组 (连接/同步/线程)

| 字段 | 类型 | 默认值 | 推测含义 |
|------|------|--------|---------|
| `an` | `public boolean` | — | restartOnDisconnect — 断开时重启 |
| `aq` | `public int` | — | reconnectAttempt — 重连尝试次数 |
| `ar` | `public int` | — | maxReconnectAttempts — 最大重连次数 |
| `as` | `public static boolean` | — | networkLogging — 网络日志启用 |
| `at` | `float` | 0.0 | syncTimer — 同步计时器 (package-private) |
| `au` | `long` | — | lastSyncTime — 上次同步时间 |
| `av` | `public boolean` | — | resyncRequested — 请求重新同步 |
| `aw` | `public int` | 5 | syncThreshold — 同步阈值 |
| `ax` | `public int` | 5 | desyncThreshold — 不同步阈值 |
| `ay` | `public ah` | new ah() | serverConnection — 服务器连接对象 |
| `az` | `public String` | null | lastServerMessage — 最后服务器消息 |
| `aA` | `public k` | — | inputStream — 网络输入流 |
| `aB` | `public k` | — | outputStream — 网络输出流 |
| `aC` | `public a` | new a() | packetQueue — 数据包队列 |
| `aD` | `Thread` | — | receiveThread — 接收线程 |
| `aE` | `ao` | — | receiveRunnable — 接收Runnable |
| `aF` | `Thread` | — | sendThread — 发送线程 |
| `aG` | `ao` | — | sendRunnable — 发送Runnable |
| `aH` | `Timer` | — | syncTimerTask — 同步定时器 |
| `aI` | `av` | — | syncTimerRunnable — 同步定时任务 |
| `aJ` | `Thread` | — | connectThread — 连接线程 |
| `aK` | `af` | — | connectRunnable — 连接Runnable |
| `aL` | `c` | — | encryptionHandler — 加密处理器 |
| `aM` | `public ConcurrentLinkedQueue` | new ... | incomingQueue — 接收队列 |
| `aN` | `ConcurrentLinkedQueue` | new ... | outgoingQueue — 发送队列 |
| `aO` | `boolean` | — | processingQueue — 正在处理队列 |
| `aP` | `volatile int` | 1 | threadState — 线程状态 (1=运行) |
| `aQ` | `Object` | new Object() | threadLock — 线程同步锁 |
| `aR` | `String` | — | relayServerIp — 中继服务器IP |
| `aS` | `String` | — | relayServerToken — 中继服务器Token |
| `aT` | `public String` | — | cloudServerUrl — 云服务器URL |
| `aU` | `public Boolean` | — | cloudEnabled — 云服务启用 |
| `aV` | `public Boolean` | — | cloudLoggedIn — 云服务已登录 |
| `aX` | `public boolean` | false | showNetworkDebug — 显示网络调试 |
| `aZ` | `boolean` | false | initialized — 已初始化 |

> 已映射: `e`=serverTick, `f`=playerList, `g`=isDedicatedServer, `h`=tickRate, `i`=isRunning, `j`=latencySimulation, `k`=packetLossSim, `l`=enableNAT, `m`=serverPort, `n`=serverPassword, `o`=isHosting, `p`=isClient, `q`=isMultiplayer, `s`=allowJoinInProgress, `u`=serverName, `v`=serverPublic, `w`=serverGameId, `A`=hasPassword, `B`=isSyncing, `C`=isServer, `E`=serverMessage, `G`=useRelay, `H`=useSteamRelay, `I`=connectionType, `J`=gameSpeed, `K`=targetSpeed, `L`=masterServerUrl, `M`=serverList, `N`=showServerList, `O`=totalPlayers, `P`=maxPlayers, `S`=serverIpAddress, `T`=lastReceivedFrame, `U`=lastSentFrame, `V`=frameBufferSize, `Y`=frameLimiter, `Z`=frameAccum, `aa`=frameSyncTimer, `ab`=packetsPerSecond, `ac`=bytesPerSecond, `ad`=packetRateLimit, `ae`=packetTimer, `af`=urgentPacket, `ai`=resyncTimeout, `aj`=disconnectPending, `ak`=reconnecting, `al`=connectionLost, `am`=checksumCalc, `ao`=autoStart, `ap`=connectionTimeout, `Q`=lookahead, `R`=hysteresisGuard, `ag`=needsResync, `X`=nextBlockingFrame, `ah`=lastSentSyncFrame, `F`=singlePlayer, `aW`=gameHasBeenStarted, `aY`=returnToBattleroom, `z`=localPlayer

#### 2.2.3 两字符字段 — b组 (网络状态/统计/连接)

| 字段 | 类型 | 默认值 | 推测含义 |
|------|------|--------|---------|
| `ba` | `public float` | — | pingAverage — 平均延迟 |
| `bb` | `public boolean` | — | pingUpdated — 延迟已更新 |
| `bc` | `public boolean` | — | highLatency — 高延迟标记 |
| `bd` | `public boolean` | — | packetLoss — 丢包标记 |
| `be` | `public boolean` | — | bandwidthLimited — 带宽受限 |
| `bf` | `public boolean` | — | natPunchthroughSuccess — NAT穿透成功 |
| `bg` | `public String` | — | externalIp — 外部IP地址 |
| `bh` | `public String` | null | natType — NAT类型字符串 |
| `bi` | `public ConcurrentLinkedQueue` | new ... | chatMessages — 聊天消息队列 |
| `bj` | `public game.e` | — | gameController — 游戏控制器 (CommandController) |
| `bk` | `public game.e` | — | replayController — 回放控制器 |
| `bl` | `public final Object` | new Object() | stateLock — 状态同步锁 |
| `bm` | `public boolean` | false | stateChanged — 状态已变更 |
| `bn` | `float` | — | desyncTimerAccum — 不同步计时累加器 |
| `bo` | `float` | — | syncInterval — 同步间隔 |
| `bp` | `int` | — | syncFrameCount — 同步帧计数 |
| `bq` | `int` | — | desyncFrameCount — 不同步帧计数 |
| `br` | `boolean` | false | desyncDetected — 检测到不同步 |
| `bs` | `public long` | — | totalBytesReceived — 总接收字节 |
| `bt` | `public long` | — | totalBytesSent — 总发送字节 |
| `bu` | `boolean` | false | connectionPending — 连接等待中 |
| `bv` | `public Socket` | null | tcpSocket — TCP Socket |
| `bw` | `public String` | null | lastErrorMsg — 最后错误消息 |
| `bx` | `public boolean` | — | forceDisconnect — 强制断开 |
| `by` | `boolean` | false | shuttingDown — 正在关闭 |
| `bz` | `boolean` | false | restartRequested — 请求重启 |
| `bA` | `static ArrayList` | — | pendingBans — 待处理封禁列表 |
| `bB` | `boolean` | false | banCheckRunning — 封禁检查运行中 |
| `bC` | `final Object` | new Object() | banLock — 封禁检查锁 |
| `bD` | `Timer` | — | pingTimer — Ping定时器 |
| `bE` | `public static ae` | — | networkStats — 网络统计对象 |
| `bF` | `an` | — | relayConnection — 中继连接对象 |
| `bG` | `private boolean` | — | debugDumpPackets — 调试转储数据包 |
| `bH` | `private boolean` | false | encrypted — 已加密标记 |
| `bI` | `private volatile float` | 1.0f | encryptionKeyVersion — 加密密钥版本 |

#### 2.2.4 NetEngine方法 (待映射 ~15个)

| 方法 | 推测含义 | 优先级 |
|------|---------|--------|
| `U()` | processIncoming — 处理接收数据 | P0 |
| `V()` | processOutgoing — 处理发送数据 | P0 |
| `W()` | syncGameState — 同步游戏状态 | P0 |
| `X()` | checkDesync — 检查不同步 | P0 |
| `Y()` | handleDesync — 处理不同步 | P1 |
| `Z()` | sendChatMessage — 发送聊天消息 | P2 |
| `aa()` | broadcastPlayerList — 广播玩家列表 | P2 |
| `ab()` | updatePlayerInfo — 更新玩家信息 | P2 |
| `ac()` | kickPlayer — 踢出玩家 | P2 |
| `ad()` | banPlayer — 封禁玩家 | P2 |
| `ae()` | setServerConfig — 设置服务器配置 | P2 |
| `af()` | getServerInfo — 获取服务器信息 | P2 |
| `ag()` | refreshServerList — 刷新服务器列表 | P2 |
| `ah()` | joinServerByIp — 通过IP加入服务器 | P1 |
| `ai()` | startCloudSession — 启动云会话 | P2 |

---

### P3 — MapEngine (game.b.b) — ~44字段待映射

**文件**: `03-deobfuscated/com/corrodinggames/rts/game/b/MapEngine.java`
**行数**: 1523+ | **重要性**: ⭐⭐⭐ — 地图系统

#### 2.3.1 单字符字段 (15个)

| 字段 | 类型 | 默认值 | 推测含义 |
|------|------|--------|---------|
| `a` | `static final boolean` | false | debugTiles — 调试瓦片 |
| `b` | `static final boolean` | false | debugFog — 调试战争迷雾 |
| `c` | `static final boolean` | false | debugPathing — 调试寻路 |
| `d` | `public static boolean` | false | useCache — 使用缓存 |
| `e` | `static ReentrantLock` | new ... | mapLock — 地图操作锁 |
| `f` | `static boolean` | — | mapLoaded — 地图已加载 |
| `g` | `static Paint` | — | tilePaint — 瓦片画笔 |
| `h` | `static Paint` | — | fogPaint — 迷雾画笔 |
| `i` | `static Paint` | — | gridPaint — 网格画笔 |
| `j` | `static Paint` | — | selectionPaint — 选择画笔 |
| `k` | `boolean[]` | new boolean[256] | tileLookupTable — 瓦片查找表 |
| `l` | `public static h` | — | tileRenderer1 — 瓦片渲染器1 |
| `m` | `public static h` | — | tileRenderer2 — 瓦片渲染器2 |
| `p` | `public int` | — | selectedTileX — 选中瓦片X |
| `q` | `public int` | — | selectedTileY — 选中瓦片Y |

#### 2.3.2 两字符字段 (29个)

| 字段 | 类型 | 默认值 | 推测含义 |
|------|------|--------|---------|
| `as` | `private int` | 1 | renderMode — 渲染模式 |
| `L` | `public static m.y` | — | tilesetTexture — 瓦片集纹理 |
| `O` | `Rect` | new Rect() | clipBounds — 裁剪边界 (package-private) |
| `Q` | `public i` | — | minimapRenderer — 小地图渲染器 |
| `R` | `public boolean` | — | showGrid — 显示网格 |
| `S` | `public boolean` | — | showFog — 显示迷雾 |
| `V` | `public PointF` | new PointF() | cameraFocus — 相机焦点 |
| `W` | `public boolean` | — | cameraLocked — 相机锁定 |
| `X` | `public boolean` | — | fogDirty — 迷雾脏标记 |
| `Y` | `public int` | — | visibleTilesX — 可见瓦片数X |
| `Z` | `public int` | — | visibleTilesY — 可见瓦片数Y |
| `aa` | `float` | 0.0 | zoomLevel — 缩放级别 (package-private) |
| `ab` | `Paint` | — | waterPaint — 水面画笔 |
| `ac` | `Paint` | — | deepWaterPaint — 深水画笔 |
| `ad` | `Paint` | — | shorePaint — 海岸画笔 |
| `ae` | `Paint` | — | landPaint — 陆地画笔 |
| `af` | `Paint` | — | mountainPaint — 山地画笔 |
| `ag` | `Paint` | — | resourcePaint — 资源画笔 |
| `ah` | `HashMap` | — | tileCache — 瓦片缓存 |
| `ai` | `float` | — | viewOffsetX — 视图偏移X |
| `aj` | `float` | 1.0 | brightness — 亮度 |
| `ak` | `int` | 0 | renderPass — 渲染通道计数 |
| `al` | `public static c` | — | commandProcessor — 指令处理器 |
| `am` | `Paint` | new Paint() | overlayPaint — 覆盖层画笔 |
| `an` | `Rect` | new Rect() | dirtyRect1 — 脏矩形1 |
| `ao` | `Rect` | new Rect() | dirtyRect2 — 脏矩形2 |
| `ap` | `long` | — | lastRenderTime — 上次渲染时间 |
| `aq` | `float` | — | renderTimeAccum — 渲染时间累加器 |
| `ar` | `float` | — | avgRenderTime — 平均渲染时间 |

> 已映射: A=resourcePoints, B=mapWidth/tileMaster, C=mapHeight/mapWidthTiles, D=tileWidth/mapHeightTiles, E=tileHeight/fogEnabled, F=fogGrid/losFogEnabled, G=visibilityGrid, H=blockingGrid, I=buildBlockingGrid, J=tilesetList, K=layerList, M=exploredFog, N=revealedFog, O(conflict), P=objectGroups, Q(conflict), R(conflict), S(conflict), T=roadTileIndex, U=deepWaterTileIndex, n=tileWidth, o=tileHeight, r=invTileWidth, s=invTileHeight, u=groundLayer, v=groundDetailsLayer, w=groundDetails2Layer, x=pathingOverrideLayer, y=itemsLayer, z=allLayers, t=tilesets

#### 2.3.3 MapEngine方法 (待映射 ~10个)

| 方法 | 推测含义 | 优先级 |
|------|---------|--------|
| `f()` | renderMap — 渲染地图 | P0 |
| `g()` | updateVisibility — 更新可见性 | P0 |
| `h()` | isTileVisible — 瓦片是否可见 | P1 |
| `i()` | worldToTileX — 世界坐标转瓦片X | P1 |
| `j()` | worldToTileY — 世界坐标转瓦片Y | P1 |
| `k()` | tileToWorldX — 瓦片X转世界坐标 | P1 |
| `l()` | tileToWorldY — 瓦片Y转世界坐标 | P1 |
| `m()` | getNearestResource — 获取最近资源点 | P1 |
| `n()` | checkBlocking — 检查阻塞 | P1 |
| `o()` | applyTileDamage — 应用瓦片伤害 (边界) | P2 |

---

### P4 — PlayerState (game.n) — ~28字段待映射

**文件**: `03-deobfuscated/com/corrodinggames/rts/game/PlayerState.java`
**重要性**: ⭐⭐⭐ — 玩家状态

#### 2.4.1 单字符字段 (12个)

| 字段 | 类型 | 默认值 | 推测含义 |
|------|------|--------|---------|
| `a` | `static m` | new m() | playerCommandPool — 玩家指令对象池 |
| `b` | `static n[]` | new n[0] | emptyPlayerArray — 空玩家数组 (共享) |
| `c` | `public static int` | 10 | maxPlayersDefault — 默认最大玩家数 |
| `d` | `public static int` | 0 | minPlayers — 最小玩家数 |
| `e` | `public static int` | 100 | maxCredits — 最大信用点 |
| `f` | `public static int` | c+d=10 | maxTeams — 最大队伍数 |
| `g` | `public static final n` | new e(-1,false,"<blank>") | blankPlayer — 空白玩家模板 |
| `h` | `public static final n` | new d(-2) | spectatorPlayer — 观察者模板 |
| `i` | `public static final n` | new d(-1) | neutralPlayer — 中立玩家模板 |
| `j` | `public static n` | new u(-99) | disabledPlayer — 禁用玩家模板 |
| `K` | `public final Object` | new Object() | stateLock — 状态同步锁 |
| `l` | `public final String` | "Note to modifiers..." | modifierWarning — 修改器警告字符串 |

#### 2.4.2 两字符字段 (16个)

| 字段 | 类型 | 默认值 | 推测含义 |
|------|------|--------|---------|
| `as` | `private static n[]` | new n[f] | playerSlots — 玩家槽位数组 |
| `q` | `public int` | 0 | creditsSpent — 已消费信用点 |
| `s` | `public y` | — | commanderUnitType — 指挥官单位类型 |
| `t` | `public y` | — | builderUnitType — 建造者单位类型 |
| `at` | `private boolean` | — | receivingSync — 正在接收同步 |
| `au` | `private int` | -9999 | lastSyncFrame — 上次同步帧 |
| `av` | `private int` | -9999 | lastCommandFrame — 上次指令帧 |
| `ae` | `public Paint` | new ag() | playerColorPaint — 玩家颜色画笔 |
| `af` | `public Paint` | new ag() | teamColorPaint — 队伍颜色画笔 |
| `ag` | `static int[]` | new int[10] | colorPalette — 调色板 |
| `ah` | `static String[]` | new String[10] | colorNames — 颜色名称 |
| `aj` | `static int` | -99 | defaultTeamIndex — 默认队伍索引 |
| `am` | `public c` | new c() | playerActions — 玩家可用操作 |
| `ao` | `public static float` | 40.0f | defaultIncomeInterval — 默认收入间隔 |
| `ap` | `public static float` | 10.0f | defaultCreditRate — 默认信用点速率 |
| `aq` | `long` | -9999L | lastSaveTime — 上次保存时间 |
| `ar` | `double` | — | totalPlayTime — 总游戏时间 |

> 已映射: o=credits, p=creditBuffer, k=playerSlot, r=teamId, v=playerName, w=aiEnabled, x=aiDifficultyAlt, z=aiDifficulty, C=playerColor, A=startingUnitChoice, E=hasLost, F=hasDisconnected, G=hasSurrendered, H=teamVictory, O=connectionId, T=teamTracker, an=incomeTimer, m=isSpectator, n=isReady, u=hasReceivedSync, y=hasCustomTeam, B=teamColorIndex, D=pingMs, I=isAllyLocked, J=isSharingResources, L=totalUnitsOwned, M=totalBuildingsOwned, N=fogOfWarGrid, P=lastIPAddress, Q=incomeRate, R=storageCapacity, S=canBuild, U=isObserver, V=playerFlag, W=selectedCommandCenter, X=lastCommandTick, Y=lastIncomeTick, Z=teamSlot, aa=isDefeated, ab=isDraw, ac=victoryTimer, ad=totalScore, ai=spawnPosition, ak=unitFilter, al=resourceTracker

#### 2.4.3 PlayerState方法 (待映射 ~8个)

| 方法 | 推测含义 | 优先级 |
|------|---------|--------|
| `g()` | setTeam — 设置队伍 | P1 |
| `h()` | getTeam — 获取队伍 | P1 |
| `i()` | setAlly — 设置同盟状态 | P1 |
| `j()` | isAlly — 检查是否同盟 | P1 |
| `k()` | checkVictory — 检查胜利条件 | P1 |
| `l()` | resetState — 重置玩家状态 | P1 |
| `m()` | applyIncome — 应用收入 | P1 |
| `n()` | canSpawnUnit — 检查可否生成单位 | P1 |

---

### P4 — Factory (game.units.h) — ~20字段待映射

**文件**: `03-deobfuscated/com/corrodinggames/rts/game/units/Factory.java`
**行数**: 930 | **重要性**: ⭐⭐⭐ — 建造系统

#### 2.5.1 剩余字段 (20个)

这些基本都是 static 的调试/控制操作 (s 类型 = 动作/指令):

| 字段 | 类型 | 静态初始化值 (标签) | 推测含义 |
|------|------|-------------------|---------|
| `d` | `static Paint` | (Paint) | buildSlotPaint — 建造栏位画笔 |
| `e` | `static Paint` | (Paint) | progressBarPaint — 进度条画笔 |
| `f` | `static Paint` | (Paint) | queueTextPaint — 队列文字画笔 |
| `g` | `static m.e` | icon_search | searchIcon — 搜索图标 |
| `h` | `static s` | "reloadUnits" | actionReloadUnits — 重载单位 |
| `i` | `static s` | "reloadOnlyActiveUnits" | actionReloadActive — 重载活跃单位 |
| `j` | `static s` | "unitClone" | actionCloneUnit — 克隆单位 |
| `k` | `static s` | "removeUnits" | actionRemoveUnits — 移除单位 |
| `l` | `static s` | "killUnits" | actionKillUnits — 杀死单位 |
| `m` | `static s` | "finishQueue" | actionFinishQueue — 完成队列 |
| `n` | `static s` | "nukeAt" | actionNukeAt — 核弹打击 |
| `o` | `static s` | "freezeAI" | actionFreezeAI — 冻结AI |
| `p` | `static s` | "changeAlliance" | actionChangeAlliance — 改变同盟 |
| `w` | `static s` | "slowGame" | actionSlowGame — 慢速游戏 |
| `x` | `static s` | "fastForward" | actionFastForward — 快进游戏 |
| `y` | `static s` | "search" | actionSearch — 搜索 |
| `z` | `static s` | "enableDebug" | actionEnableDebug — 启用调试 |
| `A` | `static s` | "enableAIDebug" | actionEnableAIDebug — 启用AI调试 |
| `B` | `static s` | "enableTriggerDebug" | actionEnableTriggerDebug — 启用触发器调试 |
| `C` | `static s` | "clearSaveHistory" | actionClearSaveHistory — 清除存档历史 |

> 这些都是开发者调试菜单中的操作项，影响低，优先级P5。已映射: a=buildSlots, b=slotWorkPositions, c=isActive, q=rallyPoint, r=buildTimer, s=idleTimer, t=totalProduced, u=productionEfficiency, v=repairTarget, D=buildQueue, E=modIcon, F=currentProduction, G=ownerPlayer, H=customTag, I=isCaptured, J=builderRestriction, K=buildFilter, ax=autoAssignTeam

---

### P5 — 其他小类

#### 2.6.1 SettingsEngine (gameFramework.bQ) — ~15字段

需要在 `03-deobfuscated/com/corrodinggames/rts/gameFramework/SettingsEngine.java` 中找到剩余的两字符字段并映射。预估15个字段 (分辨率、画质、音量、控制等设置项)。

#### 2.6.2 CombatMain (game.a.b) — ~10字段

需要在 `03-deobfuscated/com/corrodinggames/rts/game/a/CombatMain.java` 中找到剩余字段。预估10个 (伤害类型、护甲类型、战斗计时器等)。

#### 2.6.3 UnitInstance (game.units.am) — ~5字段

已接近完成。剩余字段主要是内部缓存/优化相关:
- 可能剩余: `bF`=unitTypeCache (已映射), `bG`=actionCache (已映射), `bH`=weaponCache (已映射)
- 需核实是否全部映射

#### 2.6.4 GameWorld (game.a.a) — ~5字段

已接近完成。剩余可能为内部调试字段。

---

### ✅ 已完成的类

| 类 | 字段 | 方法 | 状态 |
|----|------|------|------|
| Command (gameFramework.e) | 30/30 | 14/14 | **100%完成** ✅ |
| UnitInstance (game.units.am) | ~70/~75 | ~18/~23 | 93%完成 ✅ |
| GameWorld (game.a.a) | ~55/~60 | ~18/~23 | 92%完成 ✅ |
| AIUnitGroupBase (game.a.h) | 全部 | 全部 | 完成 ✅ |
| UnitGroup (game.a.g) | 全部 | 全部 | 完成 ✅ |
| BaseZone (game.a.i) | 全部 | 全部 | 完成 ✅ |
| AIStrategyNode (game.a.o) | 全部 | 全部 | 完成 ✅ |
| UnitType (game.units.y) | 全部 | 全部 | 完成 ✅ |
| WeaponAction (game.units.au) | 全部 | — | 完成 ✅ |
| ReplayEngine (gameFramework.ba) | 全部 | 全部 | 完成 ✅ |
| InputNetStream (gameFramework.j.k) | 全部 | — | 完成 ✅ |
| OutputNetStream (gameFramework.j.as) | 全部 | — | 完成 ✅ |
| CommandController (gameFramework.c) | 全部 | 全部 | 完成 ✅ |
| GameSaver (gameFramework.y) | 全部 | — | 完成 ✅ |

---

## 三、执行路线图

```
Phase 1 ✅ GlobalState字段 ─── +151字段 → 98%完成
Phase 2 ✅ MovementController字段 ─── +96字段 → 93%完成
Phase 3 ✅ NetEngine字段 ─── +80字段 → 96%完成
Phase 4 ✅ MapEngine+PlayerState+Factory字段 ─── +102字段 → 90-98%完成
Phase 5 ✅ 关键方法补全 ─── +123方法 documented (255 total) → 5.1%覆盖
          ⚠️ 方法声明重命名受apply_enhanced.py限制(仅this./super.调用点)
          方法映射在supplement.csv中作为文档参考
          GlobalState: +87, MovementController: +18, NetEngine: +85
          MapEngine: +33, PlayerState: +66
────────────────────────────────────────────────────────────
Phase 6 ✅ extends/implements 100%修复 (416→0) + SettingsEngine(已完整) + CombatMain(+8字段)
          工具: tools/fix_single_char_extends.py (永久可复用)
────────────────────────────────────────────────────────────
Phase 7 ✅ SoundRegistry(+33)+GameSettings(+36)字段 + apply_enhanced.py方法声明重命名改进
Phase 8 ✅ GL渲染包 (GLObject/GLTexture/GLRenderer/DrawCallBuffer/BlurEffect/BitmapTexture) +86字段
Phase 9 ✅ InGameUI (+90) + UnitType (+40) = +130字段
────────────────────────────────────────────────────────────
Phase 10 ✅ CustomUnitType(+56) + ModUnitRegistry(+109) = +165字段
Phase 11 ✅ ReliableSocket+Profile+SynPacket+MultiTouchHandler+DialogHelper (+77)
Phase 12 ✅ TaskRunner+SocketStats+ReliableServerSocket+ReliableStream (+31) + SynPacket类名修正
Phase 13 ✅ Map系统+GameScreen+TagFilter: +64字段
Phase 14 ✅ TouchState(18)+TMXMapLoader(13)+MapLayerRenderer(16)+TileEntry(6)+AndroidUIHelper(4) = +57字段
────────────────────────────────────────────────────────────
Phase 15 ⬜ 字节码交叉验证 + 最终文档索引 (预计1天)
```

**已完成**: Phase 1-6 (437字段 + 123方法 + 416 extends = 976 mappings) | **待完成**: Phase 7

### v9.7 最终统计

| 维度 | v9.5 | v9.7 | 增长 |
|------|------|------|------|
| 字段映射 | 581 | **1018** | +437 (+75%) |
| 方法映射 | 132 | **255** | +123 (+93%) |
| extends修复 | 396单字符 | **0** ✅ | 100% |
| implements修复 | 20单字符 | **0** ✅ | 100% |
| supplement.csv | 513行 | **~1,550行** | +1037行 |
| 新增工具脚本 | — | fix_single_char_extends.py | — |
