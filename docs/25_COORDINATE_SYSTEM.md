# 25 — 地图坐标系统运行时发现

> 日期: 2026-06-17 | 通过 JVM Agent 反射读取 MapEngine + 图层数据

---

## 一、坐标空间

Rusted Warfare 使用两种坐标空间:

| 空间 | 单位 | 范围 | 说明 |
|------|------|------|------|
| **Tile** | tile cell | 0~135 (×0~125) | 地图编辑器的格子 |
| **World** | pixels | 0~2700+ | 游戏内单位实际坐标 |

### 转换公式 (官方验证)

```
world = tile × 20          (tile corner, MapEngine.a 返回值)
tile  = floor(world / 20)
```

> **注意**: MapEngine.a(Point) 返回 tile corner 坐标。实际单位 world 位置通常在 tile center，即 `world = tile × 20 + 10`（+10 为半个 tile 的偏移量）。见[[#四、资源地/Extractor 位置验证]]。

### 官方验证

通过 `MapEngine.a(android.graphics.Point) → PointF` 调用（该方法仅做 `×20` 乘法，输入标签仅为语义标注）:

| 输入 | 输出 PointF | 验证 |
|------|-----------|------|
| Point(tileX=35, tileY=15) | (700, 300) | 35×20=700 ✓ |
| Point(worldX=700, worldY=300) | (14000, 6000) | 700×20=14000 ✓ |
| Point(extrX=310, extrY=1430) | (6200, 28600) | 310×20=6200 ✓ |

**结论**: `MapEngine.a(Point)` 对所有输入统一 ×20 缩放。tile_size = 20px 是官方硬编码值。

### 动态 tile_factor

实际地图的 pixel 尺寸可能略大于 tile×20 (因为边距):
- `tile_factor = maxWorldX / mapWidth(tiles)`
- 实测: `2630 / 135 ≈ 19.48`
- 资源地 occupancy 匹配用固定 20 (更精确), 输出坐标用 tile×20

---

## 二、MapEngine (b.b) 结构

### 核心字段

| 字段 | 类型 | 值(135×125地图) | 含义 |
|------|------|----------------|------|
| `C` | int | 135 | 地图宽 (tile数) |
| `D` | int | 125 | 地图高 (tile数) |
| `A` | ArrayList(7) | Point(35,15)... | **资源地坐标** (tile) |
| `z` | ArrayList(3) | b.e×3 | **图层列表** |
| `t` | ArrayList(32) | b.j×32 | tile类型区域 |
| `P` | ArrayList(1) | b.i | (用途未确认) |
| `V` | PointF | — | 参考坐标 |

### 方法

| 方法 | 参数 | 返回 | 功能 |
|------|------|------|------|
| `a(Point)` | Point(a,b) | PointF(x,y) | 坐标缩放 (×20) |

### MapEngine 定位

```
GameEngine.l.B() → 单例
  → getDeclaredFields() → 找类型含 "game.b.b" 的字段
  → 该字段值即为 MapEngine 实例
```

---

## 三、图层系统 (b.e, ArrayList z)

### 三层结构

| 索引 | 名称 (k字段) | 类型 | Grid | Tile数组 | 说明 |
|------|------------|------|------|---------|------|
| 0 | "Ground" | b.e | 135×125 | q[S] 16875 | 地面层 (必备) |
| 1 | "Items" | b.e | 135×125 | q[S] 16875 | **装饰/资源/矿坑** (必备) |
| 2 | "Units" | b.e | 135×125 | — | 初始单位 (可选) |

### 层 (b.e) 字段

| 字段 | 类型 | 含义 |
|------|------|------|
| `k` | String | 层名称 (Ground/Items/Units) |
| `l` | String | 备用名称 |
| `n` | Integer | grid 宽 (tile数) |
| `o` | Integer | grid 高 (tile数) |
| `q` | short[] | tile 索引数组 (n×o 元素) |
| `x` | byte[] | tile 数据 (n×o 元素) |
| `b` | m.ag[] | sprite 数组 (放置的物品) |
| `h` | m.ag[] | sprite 数组2 |
| `t` | Rect | 像素边界 (可能为null) |
| `u` | Rect | 备用边界 |
| `v` | RectF | 浮点边界 |
| `j` | Integer | 层编号 (0=Ground, 1=Items, 2=Units) |
| `m` | Boolean | 可见性 |
| `i` | b.b | 反向引用→MapEngine |

### 资源地在 Items 层

资源地存储在 Items 层 (index 1) 的 tile 数据中。Items 层的 `q` 数组 (short[16875]) 编码了每个 tile 的类型。

**发现**: Items 层的 tile 数组在本测试地图上全为 0 (资源地通过 MapEngine.A 坐标表示, 而非 tile 编码)。

---

## 四、资源地发现

### MapEngine.A — 资源地坐标

`A` 是 `ArrayList<android.graphics.Point>`, 包含全部资源地 tile 坐标。

**android.graphics.Point 运行时字段**: `a` (tile X), `b` (tile Y) — **不是** `x` 和 `y`!

### 示例数据 (135×125 地图, 7个资源地)

| # | Tile (a,b) | World (×20) | 占用状态 |
|---|-----------|-------------|---------|
| 0 | (35, 15) | (700, 300) | 空闲 |
| 1 | (87, 24) | (1740, 480) | 已占 (extractorT1) |
| 2 | (118, 31) | (2360, 620) | 空闲 |
| 3 | (90, 54) | (1800, 1080) | 空闲 |
| 4 | (15, 71) | (300, 1420) | 已占 (extractorT1) |
| 5 | (99, 71) | (1980, 1420) | 空闲 |
| 6 | (102, 77) | (2040, 1540) | 已占 (extractorT1) |

### 占用检测

```
extractor 世界坐标 → floor(world/20) → tile坐标
tile坐标 匹配 MapEngine.A → occupied = true
```

**验证**:
- extractor at world(310, 1430) → tile(15, 71) → 匹配 A[4] ✓
- extractor at world(1750, 490) → tile(87, 24) → 匹配 A[1] ✓

### 读取代码

```java
// Get MapEngine
Class<?> ge = findClass("com.corrodinggames.rts.gameFramework.l");
Object geInst = ge.getDeclaredMethod("B").invoke(null);
// Find MapEngine field on GameEngine
for (Field f : ge.getDeclaredFields()) {
    Object v = f.get(geInst);
    if (v.getClass().getName().equals("com.corrodinggames.rts.game.b.b")) {
        // Read A list
        Field af = v.getClass().getDeclaredField("A");
        List pts = (List) af.get(v);
        for (Object pt : pts) {
            int tx = pt.getClass().getDeclaredField("a").getInt(pt);
            int ty = pt.getClass().getDeclaredField("b").getInt(pt);
            // world = tile × 20
        }
    }
}
```

---

## 五、AI 系统类发现

> **注**: 本节内容为坐标测试过程中附带发现的AI相关类，不属于坐标系统主题。详细AI分析见 [08_AI.md](08_AI.md)。

### BuildZone (com.corrodinggames.rts.game.a.g)

方法数: 26

关键方法:
| 方法 | 参数 | 推测用途 |
|------|------|---------|
| `a(y)` | UnitType | 查询可建造位置 |
| `a(float,float)` | 坐标 | 设置建造区域 |
| `a(String)` | 类型名 | 按名称查询 |
| `a(am,boolean)` | 单位+flag | 单位相关建造 |
| `a()` | — | 重置/初始化 |
| `b(float,float)` | 坐标 | 备选坐标设置 |

构造器: `(a)`, `(a,boolean)` — 需要 AIPlayer 上下文

### AttackZone (com.corrodinggames.rts.game.a.i)

方法数: 52

关键方法:
| 方法 | 参数 | 推测用途 |
|------|------|---------|
| `a(as,PointF,boolean)` | 目标+位置+flag | 设置攻击区域 |
| `a(am,PointF,boolean)` | 单位+位置+flag | 单位攻击目标 |
| `a(y)` | UnitType | 按类型设置 |
| `a(y,boolean)` | UnitType+flag | 类型开关 |

### AIPlayer (com.corrodinggames.rts.game.a.a)

方法数: 76

关键方法:
| 方法 | 参数 | 推测用途 |
|------|------|---------|
| `a(y,s,PointF,am)` | UT+tracker+位置+单位 | 综合AI指令 |
| `a(PointF)` | 位置 | 位置相关AI |
| `a(y)` | UnitType | 类型相关AI |
| `b(PointF)` | 位置 | 备选AI |
| `a(float,float,o,ao)` | 多参数 | 复杂AI操作 |

---

## 六、关键教训

| # | 发现 | 影响 |
|---|------|------|
| 1 | tile×20 = world (官方验证) | 坐标转换公式 |
| 2 | `android.graphics.Point` 字段是 a/b | 反射时不能用 x/y |
| 3 | PointF 同时有 x/y 和 a/b 字段 | Float 字段名不同 |
| 4 | 资源地通过 MapEngine.A 坐标列表 | 不是 tile 数组编码 |
| 5 | 图层名称在 k 字段 | "Ground"/"Items"/"Units" |
| 6 | BuildZone 需要 AIPlayer 上下文 | 不能独立实例化 |
| 7 | tile_factor 动态 vs 固定20 | 地图边距影响动态值 |
