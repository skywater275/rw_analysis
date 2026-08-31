# Rusted Warfare v1.15 — 渲染管线源码逆向
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 从 GameEngine 到像素: 绘制顺序、瓦片块缓存、特效粒子、战争迷雾、Shader 后处理
>
> 关键文件: `GameEngine.java`(2204行), `MapRenderer.java`(901行), `d/c.java`(EffectEngine,906行), `d/e.java`(特效实例,591行)

---

## 1. 完整渲染调用链

```
GameEngine.b(l2, f2)                        ← 主帧渲染入口
│
├── Canvas 绑定 + 背景清除
├── [可选] 离屏渲染 + Shader 后处理
│
└── GameEngine.c(l2, f2)                    ← ★ 主游戏渲染
    │
    ├── 1. 可见性剔除: 遍历所有对象 → 设置 el 标志
    ├── 2. 排序: Collections.sort(W, ei)   ← 按绘制层排序
    ├── 3. 设置裁剪矩形
    │
    ├── 4. 背景/水面特效
    │   ├── water_cloud (云纹理滚动)
    │   ├── water_layer1 (水面层1)
    │   └── water_layer2 (水面层2)
    │
    ├── 5. 地图装饰动画更新
    │
    ├── 6. ★ 按对象类型绘制 (从后到前):
    │   ├── em==0: 对象主绘制 d(f2)         ← 单位精灵
    │   ├── EffectEngine.b(f2)              ← 爆炸/烟雾粒子
    │   ├── ProjectileManager.b(f2)         ← 弹丸层
    │   ├── ProjectileManager.a(f2, layer1) ← 弹丸第二层
    │   ├── 阴影/选择圈 d(f2)
    │   ├── 屏外指示器 e(f2)
    │   ├── 高亮/血条 a(f2, false)
    │   ├── 后绘制 p(f2)
    │   └── em==10: 最顶层对象
    │
    ├── 7. UI 叠加层
    └── 8. 画布恢复
```

---

## 2. 瓦片渲染 — 块缓存系统

### 2.1 块结构 (MapRenderer, 901行)

```
MapRenderer:
├── N×N 块网格 (默认 7×7)
├── 每块 = d.java (TileRenderChunk)
│   ├── a: 渲染好的位图 (y)
│   ├── d: 主缓冲区 (e)
│   ├── e: 淡出缓冲区 (e) — 迷雾过渡用
│   ├── g: 当前淡出 alpha
│   ├── k: 脏标记 (需重绘)
│   └── l: 强制重绘标记
└── 惰性渲染: 每帧只重绘脏块
```

### 2.2 相机滚动

```java
a(int) — 垂直滚动: 移动行 → 标记新行为脏
b(int) — 水平滚动: 移动列 → 标记新列为脏
```

### 2.3 单块渲染 (c(int, int, y), line 351)

```
渲染顺序 (每块):
├── 1. 清除块画布
├── 2. 基础地形层 (b2.u) — ground
├── 3. 装饰层 (b2.v) — 岩石/草地
├── 4. 资源层 (b2.w) — 水晶/矿
├── 5. 叠加层 (b2.z) — 额外层
├── 6. 地面特效 (焦痕/血迹) — l 对象
├── 7. 迷雾 XOR 叠层
├── 8. 战争迷雾黑色覆盖 + 切割可见区
├── 9. 标记清洁: k=false, l=false
└── 10. 合成到屏幕
```

---

## 3. 特效/粒子系统 (EffectEngine, 906行)

### 3.1 架构

```
EffectEngine (d/c.java):
├── e[] f: 预分配的固定数组池
├── g: 活跃特效计数
├── 20 种精灵表 (s[0~19]):
│   ├── [0] effects.png (25×25)
│   ├── [1] explode_big.png (39×40)
│   ├── [3] flame.png (20×25)
│   ├── [5] smoke_black.png (50×40)
│   ├── [6] shockwave.png (50×50)
│   ├── [10] plasma_shot.png (20×25)
│   ├── [11] shockwave_large.png (104×104)
│   ├── [12] explode_bits.png (20×20)
│   ├── [15] projectiles.png (20×20)
│   └── ... 等共20种
└── 工厂方法: 爆炸/烟雾/火焰/碎片/冲击波
```

### 3.2 特效实例 (d/e.java, 591行)

| 属性 | 字段 | 说明 |
|------|------|------|
| 位置 | I,J,K | 世界坐标 X,Y,Z |
| 速度 | P,Q,R | 速度分量 |
| 寿命 | V | 总存活时间 |
| 缩放 | F→G | 从 F 渐变到 G |
| 透明度 | E | 全局 alpha |
| 动画帧 | ap,ae,ak,aj | 当前帧/动画/计时器/速度 |
| 颜色 | x→y→z | 初始→渐变→最终色 |
| 附着 | b | 跟随的游戏对象 |
| 团队色 | B | LightingColorFilter |
| 重力 | v,w | 下落物理 |
| 烟雾 | u | 烟雾上升/漂移物理 |
| 阴影 | as | 暗色轮廓模式 |

### 3.3 特效更新 (b(float), line 269)

```
每帧:
├── 寿命递减 → 过期移除
├── 动画帧推进
├── 速度更新 (烟雾/重力)
├── 位置更新
└── 尾部特效生成
```

### 3.4 特效绘制 (a(l, boolean), line 368)

```
1. 从精灵表获取源矩形
2. 3D→2D 投影
3. 缩放插值 (基于寿命比例)
4. 视锥体裁剪
5. 相机变换
6. Alpha 计算
7. 旋转: 保存画布 → 旋转 → 缩放 → 绘制 → 恢复
8. 团队色 LightingColorFilter
9. 文本标签 (如有)
10. 最终绘制: y2.a(texture, srcRect, destRect, paint)
```

---

## 4. 战争迷雾渲染

### 4.1 三层迷雾

```
层1: Team Fog XOR 叠层
   └── 在地形瓦片上 XOR 叠加迷雾图案

层2: 黑色迷雾覆盖
   ├── 填充黑色
   └── fog_smooth 纹理 "切割" 出可见区 (行进方块 16种组合)

层3: 迷雾淡入淡出
   ├── 旧块位图保存到 e
   ├── g = 1.0 → 每帧递减 0.1×dt
   └── 旧图淡出 (alpha = (1-g)×255) + 新图叠加
```

### 4.2 迷雾画笔

| 画笔 | 颜色 | 用途 |
|------|------|------|
| ac | 绿 (255,0,255,0) | 完全可见 |
| ad | 暗绿 (100,0,185,0) | 部分可见 |
| ae | 红 (255,175,0,0) | 迷雾中 |
| af | 暗红 (155,175,0,0) | 阴影迷雾 |
| ag | CLEAR 模式 | 迷雾打孔 |

---

## 5. 图形抽象层

### 5.1 纹理 (m/e.java)

```
e (纹理):
├── k: Android Bitmap
├── p,q: 宽/高
├── a,b,c: 团队色变体数组 (e[])
├── i: 可选 Shader (ae)
└── o: 使用团队色标志
```

### 5.2 画布 (m/k.java, 333行, OpenGL实现)

```
k (OpenGL画布):
├── b(e, rect, rectF, paint): 主绘制 — 纹理→GL→着色器→四边形
├── b(int): ARGB→float[4] 转换 (给OpenGL)
└── 支持 ae (Shader) 后处理
```

### 5.3 Shader (m/ae.java)

从 `assets/shaders/` 加载:
- `post_base.frag` — 基础后处理
- `post_displacement.frag` — 水面 displacement 效果

Uniform 设置: float/vec2/int/texture

---

## 6. 后处理

```java
GameEngine render:
├── if (后处理启用 && Shader 编译成功):
│   ├── 绑定 post_base.frag
│   ├── 渲染游戏到离屏缓冲
│   ├── 解绑基础 Shader
│   └── if (displacement Shader 可用):
│       └── 应用水面 displacement 效果
└── 参数: u_resolution, u_offsetBy, u_uiScaling
```

---

## 7. 绘制顺序总结 (后→前)

```
1.  黑色背景清除
2.  逐块渲染 (每块):
    2a. 基础地形瓦片
    2b. 装饰层 (岩石/草)
    2c. 资源层 (水晶)
    2d. 地面特效 (焦痕/血迹)
    2e. 迷雾 XOR
    2f. 迷雾黑色覆盖
3.  水面特效叠层
4.  地图装饰动画
5.  em==0 对象 (地面单位)
6.  特效粒子 (爆炸/烟雾)
7.  弹丸层1
8.  单位阴影/选择圈
9.  屏外指示器
10. 高亮/血条
11. 后绘制
12. em==10 对象 (顶层)
13. UI 叠加

---

## 6. HUD/Overlay系统 (2026-06-23 新增)

> 关键文件: `gameFramework/d/`(8个类全部解混淆)
> 详见: [AUDIO-HUD.md](AUDIO-HUD.md)

### 6.1 架构

```
HUDManager(c) — 元素管理器
├── HUDElement(e) — 单个HUD元素 (15+状态标志, DrawLayer层级)
├── HUDElementRenderer(g) — 纹理渲染 (暗色副本+帧计数)
├── DrawEffect(f) — 地图绘制特效 (可序列化, 爆炸/建造动画)
├── CloudRenderer(b) — 云层/噪声覆盖 (R$drawable.noise, 滚动动画)
├── DrawLayer(h) — 5层绘制: a<b<c<d<e
└── HUDAnchor(d) — 9种屏幕锚点定位
```

### 6.2 绘制层级 (DrawLayer)

```java
enum DrawLayer { a, b, c, d, e }
// a = 地面层 (最低)
// b = 单位层
// c = 特效层
// d = UI层
// e = 顶层 (最高)
```

### 6.3 游戏循环渲染阶段

`GamePhase(bs)` 枚举定义了30个计时阶段，包括:
- `draw_end` → `draw_gui` → `draw_game_effects`
- `draw_game_unit` → `draw_setup` → `surface_draw`
- `realdraw_in_drawthread` (OpenGL线程)
14. FPS 计数器
15. Shader 后处理

---

## 7. OpenGL ES 2.0 渲染引擎 (2026-06-23 新增)

> 关键文件: `gameFramework/b/`(GLES20后端), `gameFramework/m/`(纹理/精灵管理)

### 7.1 架构层次

```
[应用层]     InGameUI → ActionPanel → Minimap → StatsPanel
[精灵层]     TextureManager(x,904行) → Texture(e) → FontRenderer(aa)
[渲染抽象]   Renderer(l) ← CanvasRenderer(j) / SpriteBatch(k)
[GL后端]     GLRenderer(n,1151行) → GLTexture(ah) → DrawBatch(y)
[OpenGL]     GLES20 / GL10 / GL11
```

### 7.2 gameFramework/b/ — GLES20渲染引擎

| 类 | 行数 | 描述 |
|----|------|------|
| **GLRenderer(n)** | 1151 | 主SpriteBatch渲染器 |
| **GLTexture(ah)** | 251 | GL纹理封装 (GLUtils.texImage2D) |
| **GLObject(b)** | 169 | 抽象GL对象基类 |
| DrawBatch(y) | 192 | 绘制批次 |
| DrawCall(aj) | 151 | 单次绘制调用 |
| TextureAtlas(ac) | — | 纹理图集打包器 |
| VertexBuffer(aa) | — | GPU顶点缓冲 (VBO) |
| DrawCallBuffer(al) | — | DrawCall顶点缓冲 |
| RenderTexture(x) | — | 离屏渲染目标 (FBO) |
| FramebufferTexture(ad) | — | 帧缓冲纹理 |
| BlurEffect(i) | — | 纹理模糊特效 |
| BitmapTexture(e) | — | Bitmap直绑纹理 |

**着色器系统**:
| 类 | 描述 |
|----|------|
| SpriteShader(d) | 标准精灵着色器 (纹理+颜色) |
| CircleShader(g) | 圆形轮廓着色器 |
| ShaderSource(h) | GLSL源码接口 |
| ShaderLayout(z) | 属性布局 (aPosition/aTexCoord/aColor) |
| GLUniform(q) | uniform抽象基类 |
| AttributeLocation(o) | 顶点属性定位器 |
| UniformLocation(s) | uniform定位器 |

### 7.3 gameFramework/m/ — 纹理/精灵管理层

| 类 | 行数 | 描述 |
|----|------|------|
| **TextureManager(x)** | 904 | 主纹理/资源管理器 |
| **Texture(e)** | 276 | 核心纹理类 (队伍颜色变体) |
| **FontRenderer(aa)** | 381 | 文本渲染 |
| **Renderer(l)** | — | 渲染抽象接口 |
| **CanvasRenderer(j)** | — | Android Canvas后端 |
| **DrawContext(o)** | 312 | 绘制上下文 (对象池) |
| TeamColorTexture(h) | 191 | 队伍颜色纹理 (3着色器) |
| Shader(ae) | 169 | GLSL着色器程序 |
| FileShader(i) | — | 文件加载着色器 |
| ShaderUniform(af) | — | uniform值持有者 |
| ObjectPool(q) | — | 类型化对象池 |
| UniquePaint(ag) | — | 线程安全Paint |
| Sprite(f) | — | 可渲染精灵 |
| TextureFrame(g) | — | 纹理子区域/帧 |
| NullRenderer(n) | — | 空渲染器回退 |
| DrawCommand(m) | — | 抽象绘制指令 |
| BitmapDrawer(k) | — | Bitmap渲染工具 |

### 7.4 跨平台渲染抽象

```
Renderer(l) — 渲染接口
├── CanvasRenderer(j) — Android Canvas (drawBitmap/clipRect)
├── SpriteBatch(k, gameFramework/b/) — GLES20 (begin/draw/end/flush)
└── (Slick2D Graphics, java/) — 桌面 (Image/Font/Graphics)
```
