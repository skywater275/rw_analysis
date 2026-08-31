# Rusted Warfare v1.15 — 音频与HUD系统源码逆向
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 音频: SoundPool + MediaPlayer 两层、26个静态音效注册
> HUD: 元素管理器 + 绘制特效 + 云层渲染 + 绘制层级
> 关键文件: `gameFramework/a/`(9个类), `gameFramework/d/`(8个类)

---

## 第一部分: 音频系统 (`gameFramework/a/`)

### 1. 架构

```
SoundFactory(h) — 抽象工厂基类 (HashMap声音缓存)
├── AndroidSoundFactory(a) — Android SoundPool实现 (15槽池)
│   ├── SoundThread(d) — 音频工作线程 (BlockingQueue)
│   │   └── SoundPlayRequest(c) — 播放请求 (位置/音量/优先级)
│   ├── SoundInstance(b) — SoundPool播放实例
│   │   └── SoundPool.play(id, leftVol, rightVol, priority, loop, rate)
│   └── SoundRegistry(e) — 26个静态音效引用 (d~F)
└── NullSoundFactory(f) — 空工厂 (返回NullSound)
    └── NullSound(g) — 静音实现 (no-op)

Sound(i) — 抽象声音基类
├── SoundInstance(b) — 真实SoundPool声音
└── NullSound(g) — 静音回退
```

### 2. 核心类

#### SoundFactory (h) — 抽象工厂

**文件**: `gameFramework/a/SoundFactory.java`

| 字段 | 类型 | 含义 |
|------|------|------|
| h | HashMap | 声音缓存 (名称→Sound) |

抽象方法:
- `a(int)` — 从资源ID创建声音
- `a(String, StreamHandle, boolean)` — 从文件创建声音
- `a(Context)` — 初始化 (Android上下文)

#### AndroidSoundFactory (a)

**文件**: `gameFramework/a/AndroidSoundFactory.java`

| 字段 | 类型 | 含义 |
|------|------|------|
| a | LinkedBlockingQueue | 播放请求队列 |
| b | int | 15 (声音池大小) |
| c | ObjectPool(15) | SoundPlayRequest对象池 |
| d | SoundThread | 音频线程 |
| e | int | 1000 (优先级计数器) |
| g | SoundPool | Android SoundPool (16路, 3声道, 0质量) |

#### SoundRegistry (e) — 26个静态音效

**文件**: `gameFramework/a/SoundRegistry.java`

```java
public static Sound s_d, s_e, s_f, s_g, s_h, s_i, s_j, s_k,
                     s_l, s_m, s_n, s_o, s_p, s_q, s_r, s_s,
                     s_t, s_u, s_v, s_w, s_x, s_y, s_z,
                     s_A, s_B, s_C, s_D, s_E, s_F;
```

#### SoundThread (d) — 音频工作线程

**文件**: `gameFramework/a/SoundThread.java`

```java
class SoundThread extends Thread {
    public void run() {
        while (true) {
            SoundPlayRequest req = factory.queue.take();  // 阻塞获取
            req.play();                                    // 执行播放
            factory.pool.recycle(req);                     // 回收对象
        }
    }
}
```

---

## 第二部分: HUD/Overlay系统 (`gameFramework/d/`)

### 3. 架构

```
HUDManager(c) — HUD元素管理器
├── HUDElement(e)[] — HUD元素数组
│   ├── ay (effectTemplate) — 特效模板
│   ├── g[] — 绘制命令数组
│   ├── q (DrawLayer) — 绘制层级
│   └── 属性: 位置/大小/颜色/可见性/闪烁...
├── HUDElementRenderer(g) — 纹理渲染器
│   └── 生成暗色纹理副本 + 帧计数绘制
├── DrawEffect(f) — 地图绘制特效 (爆炸/建造)
│   ├── 可序列化 (InputNetStream/OutputNetStream)
│   └── 位置/速度/渐隐动画
├── CloudRenderer(b) — 云层/噪声覆盖
│   └── R$drawable.noise 纹理 + 滚动动画
├── DrawLayer(h) — 绘制层级枚举 (5层)
│   └── 序数比较: a < b < c < d < e
└── HUDAnchor(d) — 屏幕锚点枚举 (9值)
    └── 用于HUD元素定位
```

### 4. 核心类

#### HUDManager (c)

**文件**: `gameFramework/d/HUDManager.java`

| 字段 | 类型 | 含义 |
|------|------|------|
| a~e | int | 配置值 (0,80,100,110,120) |
| f | HUDElement[] | HUD元素数组 |
| g | int | 元素计数 |
| h | boolean | HUD是否启用 |
| s | DrawCommand[] | 绘制命令数组 |
| t | DrawLayer | 当前绘制辅助器 |
| l/m | Texture | HUD纹理 |
| k | Texture | (静态) 单位图标纹理 |

#### HUDElement (e)

**文件**: `gameFramework/d/HUDElement.java`

| 字段 | 类型 | 含义 |
|------|------|------|
| a | EffectTemplate | 特效模板 (默认=defaultEffectTemplate) |
| b | DrawEffect | 绑定的绘制特效 |
| c~s | boolean | 15+ 状态标志 (可见/选中/闪烁/动画...) |
| g | int | 状态常量 (h=1,i=2,j=3,k=4,l=5,m=6,n=7) |
| q | DrawLayer | 绘制层级 |
| t | float | 计时器 |
| w | float | 缩放 (默认1.0) |
| x | int | 额外数据 |

#### DrawEffect (f) — 地图特效

**文件**: `gameFramework/d/DrawEffect.java`

| 字段 | 类型 | 含义 |
|------|------|------|
| a~r | float | 位置/速度/大小参数 |
| i | HUDElement | 关联的HUD元素 |
| j/k/l | int | 帧计数/类型/标识 |
| s | DrawLayer | 绘制层级 |
| t | float | 计时器 |

可序列化 (网络同步特效)。

#### CloudRenderer (b) — 云层渲染

**文件**: `gameFramework/d/CloudRenderer.java`

| 字段 | 类型 | 含义 |
|------|------|------|
| b | Texture | 噪声纹理 (R$drawable.noise) |
| c | Paint | 绘制画笔 |
| d | RectF | 绘制区域 |
| e/f | float | 滚动偏移 (0.2×dt, 0.07×dt) |

受 `Settings.renderClouds` 控制。

#### DrawLayer (h) — 绘制层级

**文件**: `gameFramework/d/DrawLayer.java`

```java
enum DrawLayer { a, b, c, d, e }
// a < b < c < d < e (通过ordinal比较)
```

用于Z轴排序: 地面 < 单位 < 特效 < UI < 顶层。

#### HUDAnchor (d) — 锚点

**文件**: `gameFramework/d/HUDAnchor.java`

```java
enum HUDAnchor { a, b, c, d, e, f, g, h, i }
// 9种屏幕位置锚定
```

---

## 第三部分: 音乐系统 (顶层 gameFramework)

### 5. 音乐播放

```
MusicFactory(an) — MediaPlayer音乐工厂
├── MediaPlayer池 (2实例)
├── MusicPlayer(ap) — 音乐播放器 (extends PacketBuilder)
│   └── MediaPlayer控制: start/pause/stop/volume
└── MusicFolder(at) — 音乐来源枚举 (3值)
    └── 扫描目录: 内部/外部/自定义
```

### 6. 关联文件

| 文件 | 位置 | 用途 |
|------|------|------|
| Sound | gameFramework/a/Sound.java | 抽象声音 |
| Music | java/audio/Music.java | 桌面音乐 (Slick2D) |
| Audio | java/audio/Audio.java | 音频引擎接口 |
| AudioDevice | java/audio/AudioDevice.java | 音频设备抽象 |



