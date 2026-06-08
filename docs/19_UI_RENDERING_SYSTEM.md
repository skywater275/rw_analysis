# 19 — UI/渲染系统完全解析

> 来源: game-lib.jar (game.i/fw.b/fw.d/fw.m) + RW-HPS (重定向/UI注入) + CLASS_CATALOG
> 验证: 字节码分析 + 服务器无头模式实现

---

## 一、渲染技术栈

```
┌─────────────────────────────────────────────┐
│         Rusted Warfare 渲染体系              │
├─────────────────────────────────────────────┤
│                                             │
│  HTML/CSS UI层                               │
│  ┌─ LibRocket (C++ JNI) ─────────────────┐ │
│  │  librocket.b (Core Binding, 40方法)     │ │
│  │  ├─ LoadTexture/ReleaseTexture          │ │
│  │  ├─ HandleEvent → JS事件分发            │ │
│  │  └─ newDocumentLoaded/Shown             │ │
│  │  librocket.scripts.Root (164方法)       │ │
│  │  ├─ open/back/startNew — 画面导航       │ │
│  │  ├─ alert/popup — 弹窗系统              │ │
│  │  ├─ hostStart/joinServer — 多人游戏     │ │
│  │  └─ getValueById/setValueById — 数据绑定│ │
│  └────────────────────────────────────────┘ │
│                                             │
│  2D渲染层                                    │
│  ┌─ Slick2D (Java 2D引擎) ────────────────┐ │
│  │  ├─ SpriteBatch — 精灵批渲染            │ │
│  │  ├─ Texture/Image — 纹理管理            │ │
│  │  └─ Graphics/Font — 图形/字体           │ │
│  ├─ GameScreen (game.i, 51KB) ─────────────┤ │
│  │  ├─ a(bool,bool,State) — 主渲染(2662B)  │ │
│  │  └─ b(float,int) — HUD渲染(2965B)       │ │
│  └────────────────────────────────────────┘ │
│                                             │
│  OpenGL层                                    │
│  ┌─ SGL Wrapper ──────────────────────────┐ │
│  │  ├─ GL10 (OpenGL ES 1.0)               │ │
│  │  ├─ GL20 (OpenGL ES 2.0)               │ │
│  │  └─ 跨平台兼容 (Android/Desktop)        │ │
│  └────────────────────────────────────────┘ │
│                                             │
│  原生库 (26个 .dll/.so)                      │
│  ┌─ 渲染: lwjgl.dll / lwjgl64.dll         │ │
│  │  UI: libRocketCore/Controls/Debugger    │ │
│  │  桥接: rocketConnector.dll              │ │
│  │  字体: freetype.dll / freetype6.dll     │ │
│  │  音频: OpenAL32.dll / jogg/jorbis       │ │
│  │  输入: jinput-*.dll (4个)               │ │
│  └────────────────────────────────────────┘ │
└─────────────────────────────────────────────┘
```

---

## 二、GameScreen — 游戏主画面 (game.i, 51KB)

### 2.1 类结构

```
game.i extends l (GlobalState)
  61字段 / 53方法

核心方法:
  a(Context)                    — 初始化 (1987B)
  a(bool, bool, State)          — ★ 主渲染 (2662B)
  b(float, int)                 — ★ HUD渲染 (2965B)
  j()                           — 输入处理 (128B)
```

### 2.2 主渲染循环

```
game.i.a(bool, bool, State) [2662B]:
  1. 清除帧缓冲 (glClear)
  2. 渲染地图层:
     - l.bO → 地图 SpriteBatch
     - 5个瓦片层叠加渲染
     - 迷雾覆盖层
  3. 渲染单位层:
     - l.bM → 单位精灵
     - for each unit in am.bE:
         render sprite at (eo, ep)
         渲染血条 (HP bar)
         渲染护盾 (shield bar)
         渲染建造进度
         渲染特效 (火焰/烟雾)
  4. 渲染UI覆盖层:
     - l.bP → HUD渲染
     - 小地图
     - 建造菜单
     - 资源显示 (资金/收入)
  5. LibRocket HTML覆盖层:
     - 弹窗/对话框
     - 设置菜单
     - 多人游戏大厅
  6. SwapBuffers — 交换缓冲区
```

### 2.3 HUD渲染

```
game.i.b(float, int) [2965B]:
  渲染内容:
    1. 顶部信息栏:
       - 当前资金 (n.o)
       - 收入率 (income/s)
       - 游戏时间
       - 单位计数
    2. 底部工具栏:
       - 建造按钮 (工厂可建造列表)
       - 单位选择面板
       - 动作按钮 (移动/攻击/停止/巡逻...)
    3. 小地图:
       - 地图缩略图
       - 单位位置标记
       - 视野范围指示
    4. 选中单位信息:
       - 单位类型/名称
       - HP/护盾条
       - 武器信息
       - 经验/等级
    5. 消息区域:
       - 聊天消息
       - 系统消息
       - 攻击提示
```

---

## 三、LibRocket UI 系统

### 3.1 架构

```
LibRocket = C++ HTML/CSS渲染引擎 (现名 RmlUI)
通过 JNI (rocketConnector.dll) 绑定到 Java

目录结构:
  librocket/
  ├── librocket.b (Core Binding, 40方法)
  │   ├── LoadTexture / ReleaseTexture
  │   ├── HandleEvent
  │   ├── newDocumentLoaded / newDocumentShown
  │   ├── EnableScissorRegion
  │   └── ... (底层渲染接口)
  │
  ├── scripts/
  │   ├── Root (主UI控制器, 164方法)
  │   │   ├── open(url) / back()
  │   │   ├── startNew(url) — 导航到新画面
  │   │   ├── alert(msg) / popup(msg) / inputPopup(msg)
  │   │   ├── hostStart(...) / joinServer(...)
  │   │   ├── getValueById(id) / setValueById(id, value)
  │   │   ├── hidePopup()
  │   │   └── ... (UI操作方法)
  │   │
  │   ├── Multiplayer — 多人游戏UI
  │   ├── ScriptContext — 脚本上下文
  │   └── ... (其他UI控制器)
  │
  └── 文档格式: RML (类似HTML) + RCSS (类似CSS)
```

### 3.2 UI画面导航

```
画面栈 (Root管理的导航):
  MainMenu          — 主菜单
  ├── SinglePlayer  — 单人游戏设置
  ├── Multiplayer   — 多人游戏大厅
  │   ├── HostGame  — 创建游戏
  │   ├── JoinGame  — 加入游戏 (IP输入)
  │   └── Lobby     — 游戏房间 (队伍选择/设置)
  ├── Settings      — 游戏设置
  ├── Mods          — Mod管理
  ├── Maps          — 地图选择
  ├── About         — 关于
  └── InGame        — 游戏内HUD叠加
      ├── BuildMenu — 建造菜单
      ├── ChatPanel — 聊天面板
      └── PauseMenu — 暂停菜单
```

### 3.3 服务器无头模式处理

```kotlin
// GameEngine.kt — 创建虚拟LibRocket实例
val root = Root().apply {
    multiplayer = Multiplayer::class.java
        .accessibleConstructor(Root::class.java)
        .newInstance(this)
    
    // 设置空操作的LibRocket绑定
    ScriptContext::class.java
        .findField("libRocket")!!
        .set(this, object: b() {
            override fun EnableScissorRegion(p0: Boolean) {
                // 忽略所有渲染调用
            }
        })
}

// LwjglRedirections.kt — 替换OpenGL调用
- 所有GL调用替换为无操作
- Display/PBO/纹理管理全部跳过

// SlickRedirections.kt — 替换Slick2D渲染
- DrGraphics: 无头图形上下文
- 所有渲染操作写入空设备

// FPSSleepRedirections.kt — 移除帧率限制
- Thread.sleep(FPS控制) → 跳过
- 服务器全速运行, 不受60fps限制
```

---

## 四、特效引擎 (fw.d)

### 4.1 EffectEngine (fw.d.c, 16KB)

```
粒子特效管理器:
  职责:
    - 管理所有活跃粒子效果
    - 每帧更新粒子位置/透明度/大小
    - 清理过期粒子
  
  效果类型:
    - 爆炸效果 (爆炸波/碎片)
    - 烟雾效果 (单位受伤/建筑损坏)
    - 火焰效果 (低HP单位)
    - 闪光效果 (护盾吸收/能量武器)
    - 死亡效果 (am.cX()触发)
    - 建造效果 (建造完成)
    - 核弹效果 (特殊效果, 大面积)
```

### 4.2 DisplacementShader (fw.d.e, 12KB)

```
位移着色器 — 77字段:
  用途: 水面波纹/热浪扭曲/护盾闪光
  
  效果参数:
    - 位移强度
    - 波动频率
    - 衰减半径
    - 颜色偏移
```

---

## 五、渲染管线 (fw.m, 106类)

### 5.1 管线结构

```
fw.m (106类) — 完整的2D渲染管线:

  1. 批次管理:
     SpriteBatch — 按纹理分组渲染
     减少纹理切换, 提高性能

  2. 渲染排序:
     按层排序: 地面 → 单位 → 特效 → UI
     按深度排序: Y坐标 (等距/顶视角)
     
  3. 相机系统:
     Camera — 视口变换
     支持缩放/平移/旋转
     
  4. 着色器程序:
     ShaderCompiler (fw.b.n, 26KB)
     "== Compiling shader =="
     GLSL着色器编译/缓存
     
  5. 纹理管理:
     TextureAtlas — 纹理图集
     TextureRegion — 纹理区域引用
     PBO (Pixel Buffer Object) — 异步纹理上传
```

### 5.2 字体渲染 (fw.b.a, 7KB)

```
TrueType字体渲染:
  基于 FreeType (freetype.dll)
  
  字体管理:
    - 字体加载/缓存
    - 字形渲染到纹理
    - 多语言支持 (中文/英文/俄文...)
    
  文本渲染:
    - 字符串 → 字形序列
    - 按纹理图集批量渲染
    - 支持颜色/大小/样式
```

---

## 六、音频系统

### 6.1 音频架构

```
音频引擎: OpenAL (跨平台3D音频)
  原生库:
    OpenAL32.dll / OpenAL64.dll — OpenAL实现
    jogg / jorbis               — OGG Vorbis解码

  音频类别:
    ┌─ 音效 (SFX):
    │   ├─ 武器开火音效
    │   ├─ 爆炸音效
    │   ├─ 建造完成音效
    │   ├─ 单位选择音效
    │   └─ UI交互音效
    │
    └─ 音乐 (BGM):
        ├─ 主菜单音乐
        ├─ 游戏音乐 (地图相关)
        └─ 胜利/失败音乐

  音频包: com.corrodinggames.rts.java.audio.* (37类)
    ├─ Music — 长音频 (背景音乐, OGG流式播放)
    ├─ Sound — 短音频 (音效, 预加载到内存)
    └─ AudioDevice — OpenAL设备管理
```

### 6.2 音频特性

```
3D音效:
  - 距离衰减: 音量随距离减小
  - 方位: 左右声道平衡
  - 多普勒效应: (不支持)

音效触发条件:
  - 武器开火: 每种武器类型独立音效
  - 爆炸: 按爆炸大小选择音效
  - 单位受伤: HP < 33% → 烟雾/火焰特效 + 警告音
  - 单位死亡: 死亡特效 + 爆炸音
  - 建造: 开始建造/建造完成
  - 收入: 资源入账音效
  - 升级: 升级完成音效
```

---

## 七、输入系统

### 7.1 输入架构

```
Java端: jinput (跨平台输入库)
  原生库: jinput-dx8.dll, jinput-raw.dll, jinput-wintab.dll
  
输入设备:
  ├─ 键盘 (Keyboard)
  │   └─ 热键系统 (KeyBindings, fw.ac, 10KB, 67字段)
  ├─ 鼠标 (Mouse)
  │   ├─ 点击: 选择单位/建造/攻击
  │   ├─ 拖动: 框选/移动视角
  │   └─ 滚轮: 缩放
  └─ 触屏 (Touch/MultiTouch)
      ├─ 单指: 选择/点击
      ├─ 双指: 缩放/旋转
      └─ 长按: 右键菜单
```

### 7.2 热键系统 (fw.ac, 10KB)

```
KeyBindings (67字段, 30方法):
  - 键位映射: 动作→按键
  - 支持修饰键: Ctrl/Shift/Alt
  - 支持组合键
  - 配置文件持久化

默认键位:
  A        — 攻击移动 (Attack-Move)
  S        — 停止 (Stop)
  P        — 巡逻 (Patrol)
  G        — 守卫 (Guard)
  Delete   — 回收 (Reclaim)
  Ctrl+1-9 — 编队
  Space    — 跳到最新事件
  Tab      — 切换单位
  Esc      — 取消/菜单
```

### 7.3 输入轴 (fw.af/fw.ag/fw.ah)

```
InputAxis (fw.af, 517B):
  抽象输入轴 — 用于摇杆/手柄

  实现:
    fw.ag (1KB) — 键盘模拟轴 (WASD → 方向)
    fw.ah (1.3KB) — 摇杆轴 (Android)

  轴映射:
    水平轴 → 左右移动
    垂直轴 → 上下移动
    缩放轴 → 滚轮/双指缩放
```

---

## 八、无头模式 — 渲染重定向实现

### 8.1 渲染调用分类处理

```kotlin
// 类别1: 纯渲染调用 → 替换为空操作
- OpenGL: glClear, glDraw, glBindTexture... → 全部跳过
- Slick2D: SpriteBatch.draw, Graphics.fill... → 跳过
- LibRocket: LoadTexture, RenderDocument... → 跳过

// 类别2: 需要逻辑的调用 → 保留但去渲染化
- Texture.bind → 跳过纹理上传, 但返回纹理信息
- Font.getWidth → 保留 (UI布局仍需要)
- 屏幕尺寸 → 返回服务端配置的分辨率

// 类别3: 完全保留 → 不重定向
- 游戏逻辑: update, tick, check
- 网络: 发送/接收包
- 文件: 加载/保存
```

### 8.2 无头图形上下文 (DrGraphics)

```kotlin
// SlickRedirections.kt
DrGraphics — 无操作图形上下文:
  - 所有draw操作 → null safe跳过
  - 所有get操作 → 返回默认值
  - 所有set操作 → 缓存值 (不影响渲染)
  
用途:
  - 服务器端仍可运行游戏逻辑
  - 但无任何GPU/渲染开销
  - 通过ASM在类加载时替换
```

---

## 九、桌面平台 — Android API Stub

### 9.1 自实现的Android图形类

游戏桌面版不依赖Android SDK，而是自己实现了兼容层:

```
com.corrodinggames.rts.java.* (30类):
  ├── android.graphics.* Stubs
  │   ├── Bitmap — 自实现位图
  │   ├── Canvas — 自实现画布
  │   ├── Paint  — 自实现画笔
  │   ├── Rect/RectF — 矩形
  │   └── Color — 颜色
  │
  ├── OpenGL (SGL Wrapper)
  │   ├── GL10 / GL20 — OpenGL ES封装
  │   └── 跨平台: 桌面用LWJGL, Android用GLES
  │
  └── Main (主入口)
      ├── Main.main() — 启动入口
      ├── AppGameContainer — Slick2D容器
      └── m (static) / k (container) — 单例
```

---

*参考: game-lib.jar game.i(51KB) + fw.b(40类) + fw.d(8类) + fw.m(106类) + RW-HPS 重定向系统*
*最后更新: 2026-06-07*
