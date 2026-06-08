# Rusted Warfare v1.15 — 全层级解混淆参考

> 将7个技术层中的所有混淆/技术名映射为清晰可读的含义
> Java层 (1,698类) + C++原生层 (26 DLL) + 渲染层 (9 Shader) + UI层 (24 RML + 3 RCSS) + 数据层 (131 ini) + 网络层 + 服务器层

---

## 1. 原生DLL层 — 每个库的角色和管线位置

### 1.1 渲染管线

```
游戏帧开始
  │
  ├── lwjgl64.dll (317KB, 2015)
  │   角色: LWJGL原生绑定 — Java↔OpenGL↔Win32桥
  │   导出: Java_org_lwjgl_* (OpenGL函数转发), WGL上下文管理
  │   管线位置: 整个渲染管线的底层
  │   调用: Display.create()→WGL ChoosePixelFormat / SwapBuffers()→wglSwapBuffers
  │
  ├── opengl32.dll (系统)
  │   角色: Windows OpenGL 1.1 入口点 → 转发到GPU驱动ICD
  │
  ├── igxelpicd32.dll / ig12icd32.dll (Intel GPU ICD)
  │   角色: Intel OpenGL→D3D12转换层 (可安装客户端驱动)
  │   ⚠️ 崩溃热点 #1 (97.8%的原生帧) — 固定管线→D3D模拟不稳定
  │
  ├── nvoglv64.dll (NVIDIA) / atioglxx.dll (AMD)
  │   角色: 原生OpenGL驱动 — 直接硬件加速, 稳定
  │
  └── freetype.dll / freetype6.dll (522KB, 2007/2019)
      角色: TrueType/OpenType字体光栅化 → 字形位图
      管线位置: LibRocket文本渲染的字体引擎
      调用: LibRocket → FT_Load_Glyph / FT_Render_Glyph → 字形纹理
```

### 1.2 UI管线

```
LibRocket Java (com.LibRocket)
  │
  ├── rocketConnector64.dll (906KB, 2019)
  │   角色: Java↔LibRocket C++ JNI桥接
  │   导出: Java_com_LibRocket_* — 所有LibRocket Java native方法
  │   JNI方法 (~40个):
  │     Java_com_LibRocket_loadDocument     → C++ RML解析/DOM构建
  │     Java_com_LibRocket_setInnerRML      → C++ DOM内容替换
  │     Java_com_LibRocket_setValueById     → C++ DOM元素值设置
  │     Java_com_LibRocket_getValueById     → C++ DOM元素值读取
  │     Java_com_LibRocket_processMouseMove → ★ C++ 鼠标悬停/事件派发 ← 崩溃热点
  │     Java_com_LibRocket_render           → C++ 布局计算+三角形批处理
  │     Java_com_LibRocket_update           → C++ 动画更新/过渡
  │     Java_com_LibRocket_shutdown         → C++ 资源释放
  │
  ├── librocket64.dll (3.9MB, 2019) — 64位合并版
  │   (内容: Core + Controls + Debugger 的合并)
  │   角色: C++ HTML/CSS UI引擎
  │   子系统:
  │     HTML解析器  — RML → DOM树
  │     CSS引擎     — RCSS → 样式规则 → 布局计算 (盒模型/Flex/Block/Inline)
  │     渲染器      — DOM+样式 → 三角形网格 → OpenGL纹理上传
  │     事件系统    — 输入事件 → 冒泡/捕获 → 回调
  │     ⚠️ 崩溃热点: DOM元素生命周期管理 (快速切换画面时悬垂指针)
  │
  └── 独立32位版 (2017):
       libRocketCore.dll     (39.8MB) — 核心引擎
       libRocketControls.dll (5.4MB)  — 表单控件
       libRocketDebugger.dll (1.4MB)  — 调试器
```

### 1.3 音频管线

```
OpenAL32.dll / OpenAL64.dll (390KB, 2013)
  角色: 3D空间音频引擎 (跨平台OpenAL实现)
  调用: alGenSources → alSource3f (3D位置) → alSourcePlay
  管线位置: 音效/音乐播放层

jogg.dll + jorbis.dll
  角色: OGG Vorbis音频解码
  调用: Java → JOrbis → jorbis.dll → 解码OGG→PCM → OpenAL播放
  管线位置: 音乐文件解码层

ibxm.jar (IBXM)
  角色: MOD/XM模块音轨播放器 (纯Java)
  管线位置: 旧式模块音乐 (Amiga MOD格式)
```

### 1.4 输入管线

```
jinput-dx8_64.dll (65KB, 2009)
  角色: DirectInput 8 输入捕获 (键盘/鼠标/手柄)
  API: IDirectInput8::CreateDevice → SetDataFormat → Acquire → GetDeviceState
  管线位置: 物理输入→Java事件的最底层
  ⚠️ 17年前的库, DirectInput 8在Win11通过兼容层运行

jinput-raw_64.dll (62KB, 2009)
  角色: Raw Input 高精度鼠标输入
  API: RegisterRawInputDevices → GetRawInputData
```

### 1.5 平台管线

```
steam_api64.dll (243KB, 2016) + steamworks4j64.dll (271KB, 2017)
  角色: Steam平台集成
  功能:
    Steam成就   — ISteamUserStats.SetAchievement
    Steam统计   — ISteamUserStats.SetStat
    Steam大厅   — ISteamMatchmaking.CreateLobby
    Steam overlay — 游戏内Shift+Tab

zlib1.dll (132KB, 2013)
  角色: gzip/zlib压缩
  调用位置:
    .replay 初始快照压缩 (TLV内嵌)
    .rwsave 存档压缩
    网络大块数据压缩 (可选)

libgcc_s_dw2-1.dll (115KB, 2002) + libstdc++-6.dll (1.5MB, 1997)
  角色: GCC/MinGW C++运行时库
  依赖: LibRocket C++代码编译时使用MinGW工具链
```

### 1.6 EXE启动器

```
Rusted Warfare.exe (i386, 2017)
  - 32位启动器, 调用 jvm/bin/java.exe

Rusted Warfare - 64.exe (x64, 2022)
  - 64位启动器, 调用 jvm64/bin/java.exe
  - 更现代的构建

fallback.bat:
  jvm\bin\java -Djava.net.preferIPv4Stack=true -Xmx1000M ... -width 800 -height 600

fallback64.bat:
  jvm64\bin\java -Xmx1000M ... -width 800 -height 600
```

---

## 2. Slick2D/LWJGL框架层 — 类角色映射

### 2.1 Slick2D → 游戏引擎桥接

```
org.newdawn.slick.AppGameContainer
  角色: 游戏窗口容器 + 主循环驱动
  初始化: new AppGameContainer(game, width, height, fullscreen)
  主循环: start() → 循环 { update(delta) → render() → Display.update() }
  游戏访问: container.getInput() / container.getGraphics()

org.newdawn.slick.GameContainer
  角色: 游戏容器抽象 — Delta时间计算 (getDelta())
  帧率: 60fps目标, VSync锁定

org.newdawn.slick.Graphics
  角色: 2D绘图文上下文
  方法: drawImage, drawString, drawRect, setColor, setFont
  游戏引擎: game.i (GameScreen) → Graphics → OpenGL calls

org.newdawn.slick.Image
  角色: OpenGL纹理封装
  实现: 内部持有 Texture (GL texture ID) + 精灵坐标
  游戏使用: 单位精灵, UI图片, 瓦片纹理

org.newdawn.slick.SpriteSheet
  角色: 精灵表 (一张大纹理 → 多帧小精灵)
  游戏使用: 单位动画 (4方向×N帧)

org.newdawn.slick.Animation
  角色: 帧动画控制器
  游戏使用: 单位移动/攻击/死亡动画

org.newdawn.slick.Input
  角色: 输入轮询 (→ JInput原生层)
  方法: isKeyDown, isMouseButtonDown, getMouseX/Y

org.newdawn.slick.Sound / Music
  角色: 音频播放封装 (→ OpenAL原生层)
  Sound: 短音效 (WAV, 一次性加载到内存)
  Music: 长音乐 (OGG, 流式播放)

org.newdawn.slick.particles.ParticleSystem
  角色: 粒子系统
  游戏使用: 烟雾/火焰/爆炸/水面波纹
```

### 2.2 LWJGL → OpenGL桥接

```
org.lwjgl.opengl.Display
  角色: 窗口+OpenGL上下文管理
  方法: create() → WGL CreateContext / setFullscreen / update() → SwapBuffers

org.lwjgl.opengl.GL11
  角色: OpenGL 1.1 固定管线 (glBegin/glEnd管线)
  游戏使用: 2D四边形渲染 (地图瓦片, 单位精灵, UI元素)

org.lwjgl.opengl.GL15
  角色: OpenGL 1.5 VBO (顶点缓冲对象)
  游戏使用: Slick2D VBORenderer — 批量渲染优化

org.lwjgl.opengl.GL20
  角色: OpenGL 2.0 Shader支持
  游戏使用: 队伍颜色着色器编译/链接/使用

org.lwjgl.input.Keyboard / Mouse
  角色: 原始输入 (→ JInput)
  方法: next() → 事件轮询 / getEventKey() / getDX()/getDY()

org.newdawn.slick.opengl.Texture / TextureLoader
  角色: OpenGL纹理创建/加载
  实现: glGenTextures → glTexImage2D
  格式: PNG → 解码 → RGBA → GPU上传
```

---

## 3. LibRocket UI层 — 完整画面映射

### 3.1 24个RML画面 → 功能

```
mainMenu.rml                    主菜单
  ├── 开始游戏 / 多人游戏 / 设置 / Mods / 帮助
  └── 版本信息 / 制作人员

singleplayer.rml                单机模式
  ├── 遭遇战 / 战役 / 生存模式
  └── 地图选择 + AI难度设置

multiplayerLobby.rml            多人游戏大厅
  ├── 服务器列表 (刷新/加入)
  └── 玩家名称 / IP输入

multiplayerLobby_hostgame.rml   托管游戏
  ├── 地图选择 / 玩家槽位 / 队伍分配
  └── 游戏设置 (资金/迷雾/核弹/AI)

multiplayerLobby_connecting.rml 连接中
  └── 连接状态显示

battleroom.rml                  对战房间
battleroom_gameoptions.rml      游戏选项
battleroom_player.rml           玩家管理
battleroom_setTeams.rml         队伍设置

settings.rml                    设置
  ├── 渲染 / 音频 / 输入 / 网络
  └── 键位绑定

settingsKeyBinding.rml          键位设置
settingsKeyBindingSet.rml       键位编辑
settingsPlaceholder.rml         占位

levelSelect.rml                 关卡选择
levelOptions.rml                关卡选项

sandboxOptions.rml              沙盒选项

mods.rml                        Mod管理
  ├── Mod列表 / 启用/禁用
  └── Mod设置

leaderboard.rml                 排行榜
credits.rml                     制作人员
help.rml                        帮助
help_quick_mobile.rml           快速帮助(移动版)
help_unitstats.rml              单位数据帮助
debugGameLog.rml                调试日志
messagebox.rml                  消息弹窗
```

### 3.2 3个RCSS样式 → 设计系统

```
common.rcss
  body: font-family=Delicious, font-size=22px, charset=U+0020-007E+U+00C4-00FC
  .needsUnicodeFont: font-family=fallback (中日韩字符 + 俄语)
  → 全局排版基础

forms.rcss
  input.checkbox: 复选框样式
  .checkboxDiv: 45px高, 复选框+标签布局
  .sliderDiv: 滑块控件, 相对定位
  → 表单/控件样式

rkt.rcss
  游戏自定义装饰器:
    sprite / image / gradient / tiled-box
  → Rusted Warfare特有的UI装饰系统
```

### 3.3 LibRocket Java → C++ JNI桥接

```
类层次:
  com.LibRocket                    原生方法声明 (native methods)
    └── com.corrodinggames.librocket.b    抽象桥接基类
          ├── a() → 清空文档缓存
          ├── a(String) → 纹理路径转换 (base:/drawable:/assets: → 实际路径)
          ├── c (ScriptEngine) → UI脚本引擎
          └── 纹理加载 (LoadTexture/ReleaseTexture)

ScriptEngine                      脚本引擎
  ├── execute(script)            执行JS-like脚本
  ├── handleEvent(event)         事件分发
  ├── Action / RunnableAction    延迟/异步动作

Root (164方法)                    主UI控制器
  ├── open(screen)              切换到指定画面 → C++ loadDocument(RML)
  ├── back()                    返回上级画面
  ├── alert/popup/inputPopup    弹窗系统
  ├── getValueById/setValueById HTML数据绑定
  └── hostStart/joinServer      多人游戏控制

子控制器:
  Multiplayer.java               多人游戏逻辑
  Mods.java                      Mod管理UI
  Debug.java                     调试UI
  Settings.java (推断)           设置UI
```

### 3.4 纹理路径系统

```
base:path          → {dataDir}/path                 (游戏数据目录)
drawable:name      → {dataDir}/res/drawable/name    (Android资源图片)
assets:path        → {dataDir}/assets/path          (从assets加载)
无前缀              → {dataDir}/assets/gui/path     (默认GUI路径)

示例:
  blank.png  → {dataDir}/assets/gui/blank.png
  base:maps/custom.tmx → {dataDir}/maps/custom.tmx
  drawable:fog_smooth → {dataDir}/res/drawable/fog_smooth.png
```

---

## 4. OpenGL/Shader层 — 9个GLSL程序

### 4.1 Shader清单

```
┌────────────────────────────────────────────────────────────────┐
│ Shader              版本  类型    角色                         │
├────────────────────────────────────────────────────────────────┤
│ plain.vert          #130 顶点    基础顶点变换 (固定管线兼容)   │
│ plainGDX.vert       #130 顶点    LibGDX兼容版                  │
│ plain.frag          #130 片元    基础纹理采样                  │
│ post_base.frag      #130 片元    后处理基础 (alpha=1强制)      │
│ post_displacement.frag #130 片元 ★位移后处理 (水面/冲击波)     │
│ hueAddTeamColor.frag   #130 片元 队伍颜色-色调叠加             │
│ hueShiftTeamColor.frag #150 片元 队伍颜色-色相偏移             │
│ pureGreenTeamColor.frag #130 片元 ★队伍颜色-纯绿键替换(色度键)│
│ error.frag          #130 片元    调试/错误标记 (全黄色)        │
└────────────────────────────────────────────────────────────────┘
```

### 4.2 每个Shader的算法

**plain.vert — 基础顶点变换**
```
输入: gl_Vertex, gl_Color, gl_MultiTexCoord0
输出: gl_Position = Projection × ModelView × Vertex
      v_color = gl_Color
      v_texCoords = TexCoord0
作用: 固定管线兼容 — 单位/瓦片/UI的所有基础渲染
```

**plain.frag — 基础纹理采样**
```
输入: u_texture (sampler2D), v_texCoords, v_color
输出: gl_FragColor = texture2D(u_texture, uv) × v_color
作用: 标准纹理渲染 — alpha混合通过v_color.a控制
```

**pureGreenTeamColor.frag — 纯绿键替换 ★核心**
```
算法:
  threshold = 0.04
  IF color.g > 0 AND |color.r - color.b| <= threshold:
    lightness  = color.r                    (亮度 = 红色通道)
    greenness  = color.g - lightness        (纯绿色部分)
    color.rgb  = lightness + teamColor.rgb × greenness
  → 绿色像素被替换为队伍颜色, 保留明暗层次
  绿色=队伍的色度键 (chroma key)
```

**hueAddTeamColor.frag — 色调叠加**
```
算法:
  color.r += teamColor.r × teamColorAmount  (可控叠加量)
  color.g += teamColor.g × teamColorAmount
  color.b += teamColor.b × teamColorAmount
  输出: color × v_color
作用: 简单的队伍颜色叠加 — 所有像素都被影响
```

**hueShiftTeamColor.frag — 色相偏移**
```
算法 (#150版本):
  hueness = max(|r-g|, |g-b|, |b-r|)   (颜色差异度)
  IF hueness > 15/256:                  (非灰色像素)
    lightness = min(r, g, b)
    color.rgb = lightness + teamColor.rgb × hueness
作用: 基于色相差异的队伍颜色替换 — 仅影响有颜色像素
```

**post_displacement.frag — 位移后处理 ★水面/冲击波**
```
算法:
  1. 计算屏幕UV: screenUV = gl_FragCoord / (resolution × uiScaling)
  2. 采样位移纹理: spriteColor = u_texture(spriteUV)
  3. 位移量: offset = u_offsetBy × (spriteColor.xy - 128/255) × spriteColor.a × v_color.a
  4. 偏移采样: offsetScreenUV = screenUV + offset
  5. clamp到屏幕范围
  6. screenColor = screenBase(offsetScreenUV) — 采样"前一帧"的屏幕纹理
Uniforms:
  u_texture — 位移图 (法线贴图-like)
  screenBase — 屏幕基础纹理 (前一帧渲染结果)
  u_resolution — 屏幕分辨率
  u_offsetBy — 位移强度
  u_uiScaling — UI缩放因子
作用: 水面波纹/冲击波/热浪 — 需要多pass渲染
```

**post_base.frag — 后处理基础**
```
输出: color.a = 1.0 (强制不透明)
作用: 确保后处理层完全覆盖

error.frag — 调试标记
输出: color.rgb = (1, 1, 0) (纯黄色)
作用: Shader编译失败时显示全黄色, 便于调试
```

### 4.3 队伍颜色系统 — 10种颜色

```
索引  颜色名    十六进制     RGB
────────────────────────────────────
0     GREEN    #00ff00     (0,   255, 0  )  ← 纹理中的"键色"
1     RED      #d02013     (208, 32,  19 )
2     BLUE     #0463f3     (4,   99,  243)
3     YELLOW   #ffff40     (255, 255, 64 )
4     CYAN     #00ffff     (0,   255, 255)
5     WHITE    #d0f8f7     (208, 248, 247)
6     BLACK    #000000     (0,   0,   0  )
7     PINK     #ff00ea     (255, 0,   234)
8     ORANGE   #ff7f18     (255, 127, 24 )
9     PURPLE   #9368c4     (147, 104, 196)
```

---

## 5. 单位.ini属性系统 — 全键位映射

### 5.1 [core] 核心段 — 26个属性

```
属性                    类型      默认     引擎效果
────────────────────────────────────────────────────────────
name:                   string    —       内部名称 (代码引用, 如 commandCenter)
class:                  string    —       单位元数据类 (CustomUnitMetadata)
price:                  int       —       ★ 建造成本 → n.o -= price
maxHp:                  int       —       ★ 最大生命值 → am.cv
mass:                   int       —       质量 → 碰撞/运输判定
radius:                 int       15       ★ 碰撞半径 → am.cj (像素)
techLevel:              int       1        科技等级 (1/2/3)
buildSpeed:             float/str 0.001   ★ 建造速度 (数值=每帧累积, 如35s=35秒)
displayLocaleKey:       string    —       国际化显示键 → Strings.properties
isBio:                  bool      false   生物单位 (受特殊伤害?)
availableInDemo:        bool      true    试玩版可用
showInEditor:           bool      true    地图编辑器可见
selfRegenRate:          float     0       自动回血速率
energyMax:              float     0       能量上限
energyRegen:            float     0       能量恢复速率
energyRegenWhenRecharging: float 0       充能时恢复速率
softCollisionOnAll:     int       —       软碰撞半径
displayRadius:          int       —       UI显示半径 (≠碰撞半径)
overrideAndReplace:     string    —       ★ 替换原版单位 → 所有引用重定向
copyFrom:               string    —       ★ 从其他ini继承所有属性
convertTo:              string    —       ★ morph转换目标单位
builtFrom_1_name:       string    —       建造工厂名称 (如 airFactory)
builtFrom_1_pos:        int/str   —       在建造菜单中的位置 (如 "6")
maxHp:                  int       —       最大生命值
limit:                  int       —       建造数量上限
hideFromEditor:         bool      false   ★ 编辑器中隐藏
```

### 5.2 [weapon_*] 武器段

```
属性                    类型      引擎效果
───────────────────────────────────────────────────────────
type:                   string    ★ 武器类型 → av.java 17种枚举
damage:                 float     ★ 伤害值 → au.e
range:                  float     ★ 射程 → au.f
reload:                 float     装填时间(秒) → au.g冷却
projectile:             string    弹丸类型
burst:                  int       连发数
spray:                  float     散布角度
targetting:             string    索敌类型 (ground/air/both)
splashRadius:           float     溅射半径
splashDamage:           float     溅射伤害
```

### 5.3 [canBuild_*] 建造能力段 (工厂单位)

```
[canBuild_1]            第一个可建造单位
  name:                 单位内部名 → as UnitTypeHandle
  pos:                  在建造菜单中的位置 (1-based)

[canBuild_2] ... [canBuild_N] — 最多N个

引擎效果:
  Factory.h 读取 canBuild → 构建建造菜单
  Command (build) 验证目标在 canBuild 列表中
```

### 5.4 [action_*] / [hiddenAction_*] 行为段

```
[action_actionName]
  text:                 显示文本
  description:          描述
  convertTo:            ★ morph转换目标单位
  resourceAmount:       资源消耗/类型
  resourceAmount_setValue: 设置资源值
  cooldownTimer:        冷却时间
  onlyWhenIdle:         仅空闲时可用

[hiddenAction_autoSwitchBack]
  autoTrigger:          ★ 自动触发条件
    例: if self.energy(empty=true)
  convertTo:            自动转换目标
  resourceAmount:       资源操作
```

### 5.5 [resource_*] 资源段

```
[resource]
  generation_credits:   int/float  ★ 收入生成量 → d.e.cy()
  generation_delay:     int        ★ 收入间隔 (帧)
  resourceType:         string     资源类型

引擎效果:
  generation_credits / generation_delay = 每帧收入速率
  → d.b.a() 方法返回
  → y.ab 累积 → n.o += (int)ab
```

### 5.6 [display_*] 显示段

```
icon:                  纹理路径
displayName:           显示名称 (覆盖 localeKey)
sprite:                精灵表路径
animationSpeed:        动画速度
shadowSize:            阴影大小
```

---

## 6. 网络协议层 — 包结构解混淆

### 6.1 包类型枚举

```
ID    名称              路由    内容                      校验
─────────────────────────────────────────────────────────────
10    TICK              队列    帧号 + 指令列表           checksum
20    GAMECOMMAND       队列    Command序列化 (e.java)    帧同步
35    SYNC              队列    校验和数据                hash校验
105   系统_A            即时    系统消息                  —
108   系统_B            即时    系统消息                  —
110   REGISTER_PLAYER   即时    玩家名 + 认证数据         单位校验
111   系统_C            即时    系统消息                  —
120   START_GAME        即时    游戏开始信号              版本校验
140   CHAT_SEND         即时    发送者ID + 消息文本        —
141   CHAT_RECEIVE      即时    接收者ID + 消息文本        —
160   系统_D            即时    系统消息                  —
```

### 6.2 连接包 (j.au)

```
字段:
  a (int)      包类型ID
  b (byte[])   有效载荷 (序列化数据)
  c (long)     时间戳
  d (n)        发送者引用

序列化: OutputNetStream (j.as)
  → 命名块格式: [2B len][name][4B data_len][data]
```

### 6.3 校验类型

```
游戏校验和 (cs块):
  输入: 帧号 + 游戏状态哈希
  算法: 未知哈希函数 (可能MD5/SHA-256, f.b/f.a)
  验证: 客户端值 == 服务器值
  失败: l++ (失败计数, 最多150)

扩展校验和 (es块):
  输入: 每个活跃连接的独立校验
  验证: 逐连接比对
  成功: "Checksum [{n}]. {a} == {b} (ok)"
  失败: "Checksum [{n}]. {a} != {b} (failed)"

单位校验和:
  输入: 所有核心单位类型的哈希
  验证: 客户端核心单位 == 服务器核心单位
  失败: → 踢出玩家 + "Your core units are different"
  目的: 反作弊 (检测Mod修改核心单位数据)

版本校验和:
  输入: 游戏版本号 (176)
  验证: 客户端版本 == 服务器版本
  失败: → "Your 'Rusted Warfare' client is different"
```

---

## 7. RW-HPS服务器层 — Kotlin↔Java映射

### 7.1 类对应

```
服务器Kotlin类                   引擎Java类              角色
────────────────────────────────────────────────────────────────
PrivateClassLinkPlayer          game.n                玩家状态
  .unitsKilled                  bo.c                  (字段映射验证)
  .buildingsKilled              bo.e
  .experimentalsKilled          bo.d
  .unitsLost                    bo.f
  .buildingsLost                bo.h
  .experimentalsLost            bo.g

LinkGameNet                     j.ad                  NetEngine
  .maxPlayer                    n.e
  .port                         ad.m (5123)
  .hostName                     ad.y

LinkGameServerData              ay (子对象)           游戏设置
  .incomeMultiplier             ay.h
  .credits                      ay.c
  .fog                          ay.d
  .nukes                        ay.i

GameHessData                    a.a + ba             游戏数据/回放
  .existPlayer(pos)             n.k(pos) != null
  .tick                         l.bx

GameCommandActions              av                    武器枚举 (17值)
GameUnitActions                 units.a.c             行为枚举 (7值)
GameExternalUnits               custom.*              自定义单位注册
BeanServerConfig                SettingsEngine        服务器配置
  maxUnit                       bQ.maxUnit
  defIncome                     bQ.defIncome
  maxPlayer                     bQ.maxPlayer

HeadlessModuleManage            —                     房间管理
PlayerManage                    —                     玩家管理
GameMaps                        —                     地图管理
CommandHandler                  —                     命令系统
```

### 7.2 无头模式 (Headless)

```
正常模式:
  Main → AppGameContainer → Display.create() → OpenGL上下文
  → JInput 输入 → Slick2D Graphics 渲染 → SwapBuffers

无头模式 (RW-HPS):
  方式1: -nodisplay 参数
    Display.create() 被跳过 → 无窗口/无OpenGL
    输入: 虚拟输入替代JInput轮询
  
  方式2: ASM字节码替换
    编译时插入字节码: System.out → 虚拟帧缓冲
    Graphics调用 → 空操作
    Input调用 → 虚拟队列

服务器特有:
  AI自动填充 → PlayerManage.addAI() → n.a(teamId)
  脚本引擎 → MissionEngine触发器
  持久化 → SQLite/JSON 配置存储
```

---

## 8. OpenGL渲染管线 — 逐帧命令序列

### 8.1 每帧的OpenGL状态序列

```
1. 帧开始
   glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)

2. 地图层渲染 (5层瓦片)
   FOR each layer IN [Ground, Ground2, Shadow, Object, Fog]:
     glEnable(GL_TEXTURE_2D)
     glEnable(GL_BLEND)
     glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)
     FOR each visible tile:
       glBindTexture(tile_texture)
       glBegin(GL_QUADS)
         glTexCoord2f(0,1) glVertex2f(x,y)
         glTexCoord2f(1,1) glVertex2f(x+w,y)
         glTexCoord2f(1,0) glVertex2f(x+w,y+h)
         glTexCoord2f(0,0) glVertex2f(x,y+h)
       glEnd()

3. 迷雾渲染
   glBindTexture(fog_smooth_texture)
   → 全屏四边形 × fog_alpha混合

4. 单位层渲染 (精灵动画)
   FOR each unit IN visible_units:
     IF hasTeamColor AND shaderEffects:
       glUseProgram(teamColorShader)
       glUniform4f(teamColor, r, g, b, 1.0)
     ELSE:
       glUseProgram(0) ← 回到固定管线

     → Slick2D SpriteSheet → 当前帧精灵坐标
     → Image.draw(x, y, rotation)
       → glBindTexture(sprite_texture)
       → glBegin(GL_QUADS) ... glEnd()

5. HP条/进度条渲染
   glUseProgram(0)
   glColor4f(r, g, b, a)
   → glBegin(GL_QUADS) → 实心条
   glColor4f(1,1,1,1)

6. 特效层 (粒子+弹丸)
   粒子系统:
     → glPointSize / glBegin(GL_POINTS)
   弹丸:
     → glBegin(GL_LINE_STRIP) 轨迹线
     → glBegin(GL_QUADS) 弹丸精灵

7. 后处理 (可选的位移效果)
   IF displacement_active:
     → 渲染到FBO (帧缓冲对象)
     → glUseProgram(displacementShader)
     → glBindTexture(screenBase, FBO_texture)
     → 全屏四边形采样

8. LibRocket UI渲染
   LibRocket::Render()
     → C++ 生成三角形网格
     → glUseProgram(0)
     → FOR each compiled geometry:
       → glBindTexture(ui_texture)
       → glVertexPointer → glDrawArrays(GL_TRIANGLES)

9. SwapBuffers
   Display.update() → wglSwapBuffers()
```

### 8.2 渲染特性开关

```
preferences.ini中的渲染选项 → OpenGL行为:

renderAntiAlias:     true  → glEnable(GL_MULTISAMPLE)
renderClouds:        true  → 云层纹理混合层
renderFancyWater:    false → 简化水面 (无反射)
renderVsync:         true  → wglSwapIntervalEXT(1)
renderSmoothDelta:   false → 固定帧时间步长
shaderEffects:       false → glUseProgram(0) — 全部固定管线
renderExtraLayers:   true  → 第4/5层瓦片 (Object/Fog)
renderExtraShadows:  true  → 单位阴影渲染
renderDoubleScale:   false → 无2x缩放
newRender:           false → 旧版渲染路径
renderWithLineWidth: true  → glLineWidth (弹丸轨迹宽度)
```

---

## 9. 单位升级/替换链 — 完整映射

### 9.1 资源单位升级链

```
extractor (T1基础) → extractorT1 → extractorT2 → extractorT3
                                                   ├── extractorT3_overclocked
                                                   └── extractorT3_reinforced

fabricator (T1基础) → fabricatorT1 → fabricatorT2 → fabricatorT3

bugExtractor → bugExtractorT2
bugGenerator → bugGeneratorT2  (⇄ bugGeneratorN ⇄ bugGeneratorNT2)
```

### 9.2 工厂升级链

```
landFactory → landFactoryT2 (2700 cost)
mechFactory → mechFactoryT2 (5000 cost)
airFactory  → airFactoryT2  (3500 cost)
seaFactory  → seaFactoryT2  (3000 cost)
experimentalLandFactory (无T2)
bugNest (无T2)
```

### 9.3 单位状态转换 (morph)

```
aaBeamGunship ⇄ aaBeamGunship_afterburn        (后燃器开/关)
amphibiousJet ⇄ amphibiousJet_underwater        (水上/水下)
experimentalGunship ⇄ experimentalGunshipLanded (飞行/着陆)
allTransportShip ⇄ allTransportShipLanded       (飞行/着陆)
hovercraft ⇄ hovercraftWater                    (陆地/水面)
```

### 9.4 自定义替换 (c_前缀)

```
原版 ← 替换版 (通过 overrideAndReplace)
tank ← c_tank / custom_tank
experimentalTank ← c_experimentalTank
helicopter ← c_helicopter
airShip ← c_interceptor
laserTank ← c_laserTank
mammothTank ← c_mammothTank
artillery ← c_artillery
turret ← c_turret_t1
turretT2 ← c_turret_t2_gun
turretT3 ← c_turret_t3_gun
antiAirTurret ← c_antiAirTurret
antiAirTurretT2 ← c_antiAirTurretT2
```

---

## 10. 崩溃签名 → 根因映射

```
签名                                           根因
────────────────────────────────────────────────────────────────
C [igxelpicd32.dll+0x?????]                    Intel GPU OpenGL ICD崩溃
  + j VBORenderer.*                            → VBO渲染触发Intel D3D转换层bug
  + j Display.update / SwapBuffers             → SwapBuffers时GPU状态损坏
  + j AppGameContainer.*                        → 主循环渲染崩溃

C [ig12icd32.dll+0x?????]                      Intel GPU ICD v2 崩溃
  + j GL15.glBindBuffer                         → 绑定VBO时驱动崩溃
  + j DrawableGL.*                              → OpenGL可绘制对象状态异常

C [rocketConnector.dll+0x?????]                LibRocket JNI桥崩溃
  + j com.LibRocket.processMouseMove           → 鼠标事件处理时DOM悬垂指针
  + j com.LibRocket.mouseMove                  → 同上

C [librocket64.dll+0x?????]                    LibRocket C++核心崩溃
  + j com.LibRocket.render / update            → C++渲染/更新时内存访问违例
  + j com.LibRocket.loadDocument               → 画面切换时DOM构建崩溃

C [msvcrt.dll+0x?????]                         C运行时崩溃
  + j LibRocket.*                               → 通常与LibRocket内存管理有关
  → 往往是LibRocket分配的内存在msvcrt堆中被破坏

C [graphics-hook64.dll+0x?????]                第三方覆盖层崩溃 (OBS/Discord/GeForce)
  → 与游戏渲染管线冲突

EXCEPTION_STACK_OVERFLOW                        无限递归/深度嵌套
  + C [librocket64.dll]                         → LibRocket DOM遍历递归过深

JVM OOM (OutOfMemoryError)                     内存不足
  -Xmx1000M + 40p地图 + 3000+单位              → GC thrashing → OOM
```

---

*7个技术层全量解混淆 — Java + C++ + GLSL + RML/RCSS + .ini + 网络协议 + 服务器*
*覆盖: 26 DLL, 9 Shader, 24 RML, 3 RCSS, 131 ini, 9种网络包, 4种校验和, 23个JAR*
