# Rusted Warfare v1.15 — 地图系统源码逆向
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> TMX 格式解析、瓦片GID编码、压缩管线(base64+gzip/zlib)、战争迷雾(行进方块)、块渲染
>
> 关键文件: `MapEngine.java`(1523行), `e.java`(MapLayerData,517行), `MapRenderer.java`(901行), `MapLayer.java`(303行)

---

## 1. 地图系统总览

### 1.1 核心类关系

```
MapEngine (b/b.java, 1523行)
├── tilesets:     ArrayList<j>    ← Tileset 列表 (j.java, 280行)
├── layers:       ArrayList<e>    ← 图层数据 (e.java, 517行)
│   ├── "Ground"           ← u (e) — 地面层
│   ├── "GroundDetails"    ← v (e) — 地面细节
│   ├── "GroundDetails2"   ← w (e) — 地面细节2
│   ├── "Items"/"Objects"  ← y (e) — 物体层
│   └── "PathingOverride"  ← x (e) — 寻路覆盖层
├── objectGroups: ArrayList<i>    ← 对象组 (i.java, 63行)
│   └── objects: ArrayList<a>    ← 地图对象 (a.java, 280行)
├── tileMaster:   g[]            ← 全局瓦片实例数组
├── renderer:     MapRenderer    ← 块渲染器 (c/c.java, 901行)
│   └── chunks:   d[][]          ← 渲染块网格 (d.java, 67行)
├── atlasCache:   h              ← 瓦片图集缓存 (h.java, 180行)
└── fogCache:     M[][], N[][]   ← 平滑迷雾缓存 (byte)
```

### 1.2 各层说明

| 图层 | TMX名称 | 存储字段 | 用途 |
|------|---------|---------|------|
| Ground | `ground` | `this.u` | 基础地形 (草地/水/山) |
| GroundDetails | `grounddetails` | `this.v` | 地形装饰 (树/岩石) |
| GroundDetails2 | `grounddetails2` | `this.w` | 第二层装饰 |
| Items | `items`/`objects` | `this.y` | 可碰撞物体 (建筑/资源) |
| PathingOverride | `pathingoverride` | `this.x` | 寻路覆盖 |

---

## 2. 瓦片数据结构

### 2.1 MapLayer (g) — 瓦片实例

**文件**: `MapLayer.java` (line 290-302 内嵌 g 类)

```java
class g {
    j  a;     // 所属 Tileset 引用
    int b;    // Tileset 内瓦片索引
    int c;    // 缓存元索引 (默认 -2)
    short d;  // 瓦片全局ID (在 tileMaster 中的索引)
    boolean e;  // 水域瓦片
    boolean f;  // 水桥瓦片
    boolean g;  // 熔岩瓦片
    boolean h;  // 悬崖/软悬崖
    boolean i;  // 资源池 (矿点)
    byte    j;  // 阻挡类型: 0=无, 40=小岩石, -1=完全阻挡
    boolean k;  // 大型悬崖/树木 (阻挡)
    boolean l;  // 阻挡建筑
    g[]    m;  // 变体瓦片数组 (随机变化)
}
```

### 2.2 瓦片全局ID系统 (tileMaster)

MapEngine 维护 `g[] B` 数组——所有唯一瓦片实例的全局注册表:
```java
a(g tile)     → short  // 注册/查找瓦片，返回其在 B[] 中的索引
a(short id)   → g      // 通过索引查找瓦片实例
```

图层中的瓦片网格 (`e.q`, `short[]`) 存储的是到 `B[]` 的索引，而非原始GID。

---

## 3. TMX 瓦片GID编码 — 4字节格式

### 3.1 GID 位布局 (e.java, lines 370-377)

```java
int gid = 0;
gid |= inputStream.read();        // byte 0 (低8位)
gid |= inputStream.read() << 8;   // byte 1
gid |= inputStream.read() << 16;  // byte 2
gid |= inputStream.read() << 24;  // byte 3 (高8位)

// 翻转标志 (高位3个bit)
boolean flipH = (gid & 0x80000000) != 0;  // 水平翻转
boolean flipV = (gid & 0x40000000) != 0;  // 垂直翻转
boolean flipD = (gid & 0x20000000) != 0;  // 对角翻转

// 实际GID (低29位)
gid &= 0x1FFFFFFF;  // 最大 536,870,911
```

### 3.2 GID 到瓦片映射

```
GID = 0              → 空格 (无瓦片)
GID = 1~tileset.l    → 第一个tileset的瓦片
  tileIndex = GID - tileset.firstGid
GID = nextFirst~...  → 后续tileset的瓦片
```

### 3.3 瓦片去重逻辑 (e.java, lines 362-405)

```
对每个瓦片GID:
├── GID == 0 → 跳过 (空格)
├── GID == 上一个GID → 复用同一个 g 实例
├── GID 在 HashMap 中 → 从缓存取
└── 新GID:
    ├── 通过 firstGid 查找对应 Tileset (b2.a(n3))
    ├── MapLayer.a(b2, e2, j2, tileIndex, x, y, bl)
    │   ├── 读取 TMX tile properties (unit/customUnit/team/showFog)
    │   ├── 设置 water/lava/cliff/blocking 标志
    │   └── 创建 g 实例
    └── 存入 HashMap 缓存
```

---

## 4. 压缩管线

### 4.1 完整链路

```
TMX <data> 元素:
├── encoding="base64"     ← 必需 (唯一支持)
└── compression="gzip"    ← 可选: "gzip" / "zlib" / ""
```

```
TMX XML <data> 文本节点 (Base64字符串)
│
▼ e.a(char[]) — 自定义 Base64 解码
├── 256字节查找表 (静态初始化)
│   ├── 'A'-'Z' → 0-25
│   ├── 'a'-'z' → 26-51
│   ├── '0'-'9' → 52-61
│   ├── '+' → 62
│   └── '/' → 63
├── 跳过非Base64字符 (如换行符)
└── 输出 byte[]
│
▼ 解压缩 (按 compression 属性)
├── "gzip"  → GZIPInputStream(new ByteArrayInputStream(bytes))
├── "zlib"  → InflaterInputStream(new ByteArrayInputStream(bytes))
└── ""      → ByteArrayInputStream(bytes)  (无压缩)
│
▼ e.a(InputStream) — 解析4字节GID
├── 逐行逐列读取 (o 行 × n 列)
├── 每个瓦片读取4字节 → 解析GID + 翻转标志
└── 存储到 this.q[col * height + row] (short[])
│
▼ 最终存储: g[] B 全局瓦片数组 + e.q[] 瓦片索引
```

### 4.2 自定义 Base64 解码器 (e.java, lines 433-465)

```java
static byte[] a(char[] chars) {
    // 1. 统计有效Base64字符数
    // 2. 计算输出字节数: n3/4*3 (+2 for 3余, +1 for 2余)
    // 3. 每次读入4个Base64字符 → 合并为6bit×4=24bit
    // 4. 拆分为3个8bit字节输出
    // 5. 从查找表 byArray[char] 获取6bit值
}
```

> 注意: 未使用 `android.util.Base64`，因为游戏可能在Android/Desktop多平台运行。

### 4.3 支持的压缩格式

| 压缩 | Java类 | 说明 |
|------|--------|------|
| `gzip` | `GZIPInputStream` | 标准 gzip (推荐) |
| `zlib` | `InflaterInputStream` | 原始 Deflate (zlib) |
| `""` | `ByteArrayInputStream` | 无压缩 (调试用) |

---

## 5. 地图加载管线

### 5.1 MapEngine.a(InputStream, boolean) — TMX XML 解析 (line 812)

```
1. 创建 XML DOM 解析器 (DocumentBuilder)
   ├── setExpandEntityReferences(false)
   ├── setIgnoringComments(true)
   └── EntityResolver → 空流 (阻止DTD加载)

2. 解析 <map> 元素:
   ├── orientation (固定 "orthogonal")
   ├── width, height (瓦片数)
   └── tilewidth, tileheight (瓦片像素大小, 默认 20×20)

3. 计算地图尺寸:
   ├── C, D = 地图瓦片数
   ├── n, o = 瓦片像素大小
   ├── r = 1.0/n, s = 1.0/o (逆尺寸)
   └── p = n/2, q = o/2 (半瓦片)

4. 解析 <tileset> 元素:
   ├── firstgid → 起始GID
   ├── source → 外部 .tsx 文件路径
   ├── image → 瓦片集贴图
   ├── tilewidth/tileheight
   └── <tile> 子元素 (单个瓦片属性)

5. 解析图层 (按顺序):
   ├── <layer> → 创建 e 实例
   │   ├── 读取 <data> 编码和压缩
   │   └── 解析瓦片数据
   └── <objectgroup> → 创建 i 实例
       └── <object> → 创建 a 实例

6. 设置战争迷雾:
   ├── 遍历 objectgroups 查找 "fog" 属性
   ├── 类型: "basic" / "los" / "noFog"
   └── 设置 E (迷雾启用), F (视线启用)

7. 道路连通性:
   ├── 遍历 tileset 查找 "road" / "waterDeep" 属性
   └── 设置 T, U (道路/深水瓦片索引)
```

### 5.2 瓦片属性处理 (MapLayer.a, line 82)

从 TMX `<tile>` 元素的 `<properties>` 读取:

| 属性 | 效果 |
|------|------|
| `unit` | 生成内置单位 (按名称) |
| `customUnit` | 生成 Mod 单位 (按名称) |
| `team` | 单位所属队伍 (-1=中立) |
| `showFog` | 显示迷雾 |
| 无 | 普通地形瓦片 (设置 water/lava/cliff 等标志) |

---

## 6. 战争迷雾 (Fog of War)

### 6.1 迷雾类型

| 类型 | 常量 | 说明 |
|------|------|------|
| `basic` | — | 标准迷雾 (已探索灰/当前可见) |
| `los` | — | 视线迷雾 (Line of Sight) |
| `noFog` | — | 无迷雾 |

### 6.2 迷雾存储

```java
byte[][] M;  // 已探索 (explored) 迷雾 — 永久
byte[][] N;  // 当前可见 (revealed) — 每帧更新
```

### 6.3 迷雾更新: `a(float x, float y, int team, PlayerState, boolean)` (line 1178)

```
1. 计算瓦片坐标 (x/n, y/o)
2. 对每个队伍独立的迷雾数组:
   ├── 获取队伍的 byte[][] 迷雾层
   └── 根据单位视野范围更新可见性
3. 限制边界 (0 ~ C-1, 0 ~ D-1)
```

### 6.4 行进方块 (Marching Squares) 平滑迷雾 (line 310)

MapEngine 静态初始化阶段 (line 174):
```java
e fogTex = l2.bO.a(R$drawable.fog_smooth);
```

`a(int[], int, int, boolean, e, y, e)` — 生成平滑迷雾纹理:
```
对每个迷雾 tile (2×2 单元):
├── 4个角 → 16种组合 (2⁴)
├── 每种组合 → 唯一的行进方块纹理
└── 渲染到迷雾纹理缓存
```

### 6.5 迷雾边缘计算: `a(int, int, byte[][], byte)` (line 1206)

```java
byte a(int x, int y, byte[][] fog, byte val) {
    // 检查 (x,y) 位置的迷雾状态
    // 对周围 2×2 区域使用行进方块计算
    // 返回平滑后的迷雾值 (0-15)
}
```

---

## 7. 瓦片渲染 — 块系统

### 7.1 MapRenderer (c.java, 901行)

```
Tile Render Chunk Grid:
├── 默认 7×7 块网格
├── 每块 = h × h 像素 (h = 瓦片大小 × 块瓦片数)
└── 惰性渲染 + 缓存
```

### 7.2 块结构 (d.java — TileRenderChunk)

| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | y (Bitmap) | 渲染好的瓦片位图 |
| `d` | e (Bitmap) | 主缓冲区 |
| `e` | e (Bitmap) | 淡出缓冲区 |
| `g` | float | 淡出 alpha |
| `k` | boolean | 脏标记 (需重绘) |
| `l` | boolean | 强制重绘 |
| `n` | boolean | 小地图脏标记 |

### 7.3 块滚动 (lines in MapRenderer)

```
a(int) — 垂直滚动: 移动行 + 标记新行为脏
b(int) — 水平滚动: 移动列 + 标记新列为脏
```

### 7.4 单个块渲染: `c(int, int, y)` (MapRenderer)

```
渲染顺序:
1. 地面层 (Ground) — 基础地形
2. 地面细节 (GroundDetails) — 装饰
3. 地面细节2 (GroundDetails2)
4. 物体层 (Items) — 建筑物/资源
5. 战争迷雾叠层
6. 单位渲染
```

---

## 8. 地图文件 I/O

### 8.1 加载路径

```java
// 从 assets 加载
a(String path, boolean isAsset)

// 从文件系统加载
// 支持 .tmx 扩展名过滤 (gameFramework/e/c.java)
```

### 8.2 地图保存: `b(String, String)` (line 704)

```
1. 创建 XML Document
2. 遍历地图对象:
   ├── 所有单位 → 序列化为 <object> 元素
   │   ├── 位置 (x, y)
   │   ├── 类型 (unit/customUnit)
   │   ├── 队伍 (team)
   │   └── HP 等属性
   └── 写入到新 TMX
3. 使用 Transformer 序列化 XML
4. 写入文件
```

### 8.3 存档压缩

在 `GameSaver.java` 中引用 `saveCompression` 属性:
```java
// 地图保存时可选压缩
// 通过 GZIPOutputStream 对存档数据压缩
```

---

## 9. 完整数据流图

```
┌─ 地图创作 ──────────────────────────────────────────┐
│ Tiled Map Editor (.tmx + .tsx + .png)                │
└───────────────────┬─────────────────────────────────┘
                    ▼
┌─ XML DOM 解析 ───────────────────────────────────────┐
│ DocumentBuilder (禁用DTD/注释)                        │
│ ├── <map> → width, height, tilewidth, tileheight     │
│ ├── <tileset> → firstgid, source, image, tile props  │
│ ├── <layer> → name, width, height, <data>            │
│ └── <objectgroup> → <object> → name, type, pos       │
└───────────────────┬─────────────────────────────────┘
                    ▼
┌─ 瓦片数据解码 ───────────────────────────────────────┐
│ Base64 字符串                                         │
│ ├── 自定义解码器 (256字节查找表)                      │
│ ├── GZIPInputStream / InflaterInputStream             │
│ └── 4字节GID × (宽×高)                               │
│     ├── bit[31] = 水平翻转                            │
│     ├── bit[30] = 垂直翻转                            │
│     ├── bit[29] = 对角翻转                            │
│     └── bit[28:0] = GID (0x1FFFFFFF)                  │
└───────────────────┬─────────────────────────────────┘
                    ▼
┌─ 瓦片实例化 ─────────────────────────────────────────┐
│ GID → Tileset查找 (firstGid ≤ GID < lastGid)         │
│ ├── 读取 tile properties (unit/customUnit/team)      │
│ ├── 设置 water/lava/cliff/resource/blocking 标志     │
│ ├── 创建 MapLayer (g) 实例                           │
│ └── 注册到 tileMaster (g[] B)                        │
└───────────────────┬─────────────────────────────────┘
                    ▼
┌─ 运行时网格 ─────────────────────────────────────────┐
│ e.q[] = short[width × height]                        │
│ ├── 值 = 到 tileMaster 的索引 (非原始GID)            │
│ └── 0 = 空格                                        │
│                                                       │
│ 战争迷雾:                                             │
│ ├── M[][] = 已探索 (byte, 永久)                      │
│ └── N[][] = 当前可见 (byte, 每帧更新)                 │
└───────────────────┬─────────────────────────────────┘
                    ▼
┌─ 渲染 ───────────────────────────────────────────────┐
│ MapRenderer: 7×7 块网格                               │
│ ├── 惰性渲染 (脏标记)                                 │
│ ├── 滚动时移动块 + 标记新块                           │
│ └── 渲染顺序: 地面 → 细节 → 物体 → 迷雾 → 单位       │
└──────────────────────────────────────────────────────┘
```

---

## 10. 关键常量

| 常量 | 值 | 说明 |
|------|-----|------|
| 瓦片大小 | 20×20px (默认) | 某些平台 60×60 |
| 渲染块网格 | 7×7 | 默认块数 |
| GID最大值 | 0x1FFFFFFF | 约 5.37亿 |
| 翻转标志位 | bit 31/30/29 | H/V/D 翻转 |
| 阻挡类型 | 0/40/-1 | 无/小岩石/完全 |
| 迷雾类型 | basic/los/noFog | TMX 属性 |
| 行进方块 | 16种组合 | 2×2角 → 平滑纹理 |
| Base64查找表 | 256字节 | A-Za-z0-9+/ |

---

## 11. 对 RWAgent 的影响

1. **地图坐标转换**: 游戏坐标 → 瓦片坐标: `tileX = worldX / tileWidth`, `tileY = worldY / tileHeight`
2. **瓦片阻挡检查**: 建造位置需要检查该瓦片的 `j` (blocking) 和 `l` (blockBuildings) 标志
3. **迷雾状态**: 通过 `M[][]` 和 `N[][]` 可查询任意位置的迷雾可见性
4. **资源池位置**: MapEngine 的 `A` (ArrayList\<Point\>) 存储所有矿点坐标

---

## 8. Map子包新发现类 (2026-06-23 新增)

> 关键文件: `game/b/`(11个类全部解混淆)

| 类 | 原混淆名 | 描述 |
|----|---------|------|
| MapSpawn | a | 地图出生点/任务数据XML解析器 (Element, Properties, RectF) |
| MapLayerRenderer | d | 单层地图渲染器 (Paint, Texture, Sprite) |
| TMXMapLoader | e | TMX文件加载器 (GZIPInputStream, InflaterInputStream, Base64) |
| MapException | f | 地图相关异常 (extends Exception) |
| TileDrawer | h | 单个瓦片渲染 (Paint, Rect, Sprite, Texture) |
| MapLayerDef | i | 地图层定义 (name, width, height, spawns ArrayList) |
| TilesetDef | j | 瓦片集数据 (image, tileWidth/Height, spacing, properties) |
| TileEntry | k | 单个瓦片条目 (id, image, properties, package-private) |
5. **寻路覆盖**: PathingOverride 层 (`this.x`) 可修改默认寻路行为
6. **单位生成**: 地图对象 (a.java) 在加载时生成预置单位 (通过 TMX object properties)


