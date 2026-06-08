# 07 — 地图/迷雾系统

> 逆向度: 90% | 核心类: `game.b.b`(MapEngine), `game.b.e`(TileLayer), `game.b.g`(PathNode)
> 验证: game-lib.jar javap + RW-HPS mapEngine + 30回放快照

---

## 类名意义

| 混淆名 | 完整路径 | 实际含义 |
|--------|---------|---------|
| `b.b` | `game.b.b` | **MapEngine** — 地图核心引擎 (38KB) |
| `b.e` | `game.b.e` | **TileLayer** — 瓦片数据层 |
| `b.g` | `game.b.g` | **PathNode** — A* 寻路节点 |
| `b.j` | `game.b.j` | **TileType** — 地块类型定义 |
| `b.i` | `game.b.i` | **MapMetadata** — 地图元数据 |
| `b.k` | `game.b.k` | **CollisionDetector** — 碰撞检测 |
| `b.f` | `game.b.f` | **FogUpdater** — 迷雾更新器 |
| `b.a` | `game.b.a` | **MapRenderer** — 地图渲染器 |
| `b.c` | `game.b.c` | **MapConfig** — 地图配置 |

---

## 地图引擎 — `game.b.b` (38KB, javap确认)

### 5 个瓦片层

```
b.b.u — 地形层 (terrain)    — 陆地/水域/悬崖/资源点
b.b.v — 单位层 (units)      — 单位占据的瓦片
b.b.w — 资源层 (resources)  — 资源池位置
b.b.x — 迷雾层 (fog)        — 战争迷雾覆盖
b.b.y — 装饰层 (decoration) — 树木/岩石等装饰
```

### 核心字段

```
C (int)           — 地图宽度 (像素)   // GameFunction.kt:34 验证
D (int)           — 地图高度 (像素)   // GameFunction.kt:34 验证
B (b.g[])         — ★ 路径节点数组 (A* 图的所有节点)
Q (b.i)           — 地图元数据 (尺寸/玩家数/起始位置)
K (m.e)           — 静态渲染引用
L (m.y)           — 静态渲染器
al (b.c)          — 静态地图配置
l, m (b.h)        — 静态瓦片渲染器
```

### 主要方法

```
a(Document, OutputStream)  — 导出 TMX
a(InputStream, OutputStream)— 导入 TMX
e()                        — 初始化/重新加载
a(l)                       — 从游戏引擎加载
f(), g(), h()              — 更新阶段 (迷雾/视野/单位)
a(as) → Integer            — 获取单位类型对应的瓦片索引
k(), l()                   — 额外更新
a(j.as) / a(j.k)           — 序列化/反序列化
```

---

## 地块层 — `game.b.e`

### 字段

```
i (b.b)         — 所属地图引擎
j (int)         — 层索引 (0=地形, 1=单位, ...)
k, l (String)   — 层名称
q (short[])     — ★ 瓦片数据数组 (每个瓦片的类型ID)
m (boolean)     — 是否可见
n, o (int)      — 层尺寸 (瓦片数)
```

### 方法

```
a(int x, int y) → b.g        — ★ 获取 (x,y) 处的路径节点
a(int x, int y, b.g, bool)   — 设置节点
a() → short[]                 — 获取瓦片数组
b()                           — 清除/重置
```

---

## 迷雾系统

### 迷雾值含义

```
0       — 不可见 (黑色, 未探索)
1-4     — 已探索但无当前视野 (灰色)
5       — 当前可见 (明亮, 可通行)
≥ 5     — 阻塞/不可通行
```

### 迷雾块格式 (快照中)

```
每个玩家独立的迷雾块:
  [4B: marker  0xFF 0xFF 0xD8 0xF1]    ← 固定标记
  [20B: header]                          ← 玩家索引/版本/标志
    offset +20: [2B: width]              ← 迷雾宽度 (瓦片数)
    offset +24: [2B: height]             ← 迷雾高度 (瓦片数)
    offset +28: [4B: extra]              ← 额外数据
  [w*h bytes: fog values]               ← 迷雾数据
  [80B: tail]                            ← 尾部数据

块大小: 30 + w*h + 80 字节
```

### 视野计算 (推测)

```
每帧更新 (b.f FogUpdater):
  for each unit:
    range = unit.sightRange             // 单位视野范围 (来自 .ini)
    以单位位置为中心, range 为半径:
      设置圆形区域内迷雾值 = 5 (当前可见)
  
  衰减:
    之前可见但当前不在任何单位视野内的瓦片:
      迷雾值从 5 衰减到 1-4 (已探索但无视野)
```

---

## TMX 地图格式

### 快照中的 TMX

```xml
<?xml version="1.0" encoding="UTF-8"?>
<map version="1.0" orientation="orthogonal"
     width="120" height="120"           ← 瓦片数 (120×120 = 2400×2400px)
     tilewidth="20" tileheight="20">    ← 每瓦片 20px
  <tileset firstgid="1" source="units.tsx"/>
  <layer name="terrain">...</layer>
  <layer name="units">...</layer>
  <objectgroup name="resources">...</objectgroup>
</map>
```

### 地图文件路径

```
格式: maps/skirmish/[flags]mapname.tmx
标志: [z;p10] = 无特殊标志; 10玩家
      [z]     = 标准
      [p6]    = 6玩家

示例:
  maps/skirmish/[z;p10]Crossing Large (10p).tmx
  maps/skirmish/Valley Arena (10p) [by_uber]@[z;p10].tmx
```

---

## 快照中的地图数据区域

```
快照结构 (decompressed):
  1. unit_types           — 自定义单位类型列表 (开头)
  2. "gameSetup" + header — 游戏设置 (45+ 字节, 含速度 @ +22)
  3. TMX XML              — 地图瓦片数据
  4. 地图参数块           — 85 字节 (map params)
  5. 玩家记录             — 每人 15+name_len 字节
     [4B:4000] [4B:team] [1B:0x01] [2B:len] [name]
  6. 迷雾块               — 每人 w*h+116 字节
  7. "stats" TLV块        — 初始对战统计 (开局全0)
```

---

## 碰撞检测

```
地形碰撞 (b.k):
  检查 unit.cj (碰撞半径) 与地图瓦片的通行性

单位碰撞 (f.g):
  distanceSq(u1.x, u1.y, u2.x, u2.y) < (u1.cj + u2.cj)²

通行性检查:
  tile = map.getTile(worldX/20, worldY/20)
  if tile.walkable == false → 不可通行
  if fog[tileX][tileY] < 5 → 迷雾阻塞
```
