# 03 — 移动/寻路系统

> 逆向度: 95% | 核心类: `game.f`(移动控制器), `game.b.*`(地图/寻路), `gameFramework.f`(数学工具)
> 验证: game-lib.jar javap + RW-HPS + 30回放

---

## 类名意义

| 混淆名 | 完整路径 | 实际含义 |
|--------|---------|---------|
| `game.f` | `game.f` | **MovementController** — 移动控制器/寻路节点 (36KB) |
| `game.g` | `game.g` | **MovementTarget** — 移动目标状态 |
| `game.b.b` | `game.b.b` | **MapEngine** — 地图引擎 (38KB) |
| `game.b.e` | `game.b.e` | **TileLayer** — 地块层 (瓦片数据) |
| `game.b.g` | `game.b.g` | **PathNode** — A* 路径节点 |
| `game.b.j` | `game.b.j` | **TileType** — 地块类型枚举 |
| `game.a.a` | `game.a.a` | **GameWorld** — 游戏世界 (44KB, 三层循环) |
| `f.a` | `gameFramework.f` | **MathUtils** — 数学工具 (60KB) |
| `f.g` | `gameFramework.f.g` | **VectorUtils** — 向量/几何工具 (82KB) |

---

## 寻路系统完整结构 (修正)

### 之前的误判 (已修正)

```
❌ 误判: "寻路在外部库中, 无A*, f(x,y)直接位置设置"
✅ 实际: 寻路100%在 game-lib.jar 内, A*节点结构已确认
   19+ 个类: game.f(36KB) + game.b(11类) + f.a/f.g(142KB)
```

### 系统架构

```
gameFramework.f (数学工具, 142KB)
  ├── f.a    — distance², distance, decay_to_zero, move_toward, clamp
  └── f.g    — 向量运算, 几何计算, 碰撞检测
       │
game.b (地图引擎, 11类)
  ├── b.b    — 核心: 5瓦片层, 通行性数组, 迷雾, C/D=宽高
  ├── b.e    — 地块层: short[] 瓦片, a(x,y)→路径节点
  ├── b.g    — A* 路径节点: cost, flags, neighbors[]
  ├── b.j    — 地块类型: 名称/通行性/ID
  └── b.k    — 碰撞检测: 单位-地形
       │
game.f (移动控制器, 36KB)
  ├── O[]    — 路径点坐标数组 (float[])
  ├── q      — 路径节点链表 (game.f → game.f → ...)
  ├── k,P,Q,R— 路径索引 (short)
  ├── j,l    — 单位引用 (am)
  └── 20+    — 状态布尔标志
       │
game.a.a (GameWorld, 44KB)
  ├── bE     — CombatManager
  ├── av()   — 主更新 (每帧)
  └── a(a.a.a)— 处理玩家指令
```

---

## `game.f` — 移动控制器 (36KB, 完整字段表)

### 核心引用

| 字段 | 类型 | 含义 |
|------|------|------|
| `j` | am | **控制的目标单位** |
| `l` | am | **目标/敌方单位** |
| `g` | game.g | **移动目标状态** (目标坐标/路径参数) |
| `q` | game.f | **★ 路径节点链表** (自引用, 单链表) |
| `O` | float[] | **★ 路径点坐标数组** (x1,y1, x2,y2, ...) |
| `bm` | static f | **单例缓存** |

### 位置/速度

| 字段 | 含义 |
|------|------|
| `h, i` | 当前位置 (x, y) |
| `n, o, p` | 速度向量/方向 |
| `r, s, t` | 中间计算值 |
| `u, v, w, x, y` | 目标坐标/路径点 |

### 路径索引

| 字段 | 类型 | 含义 |
|------|------|------|
| `k` | short | **当前路径点索引** |
| `P` | short | 路径起点索引 |
| `Q` | short | 路径长度/终点索引 |
| `R` | short | 备用索引 |

### 状态标志 (20+)

| 标志 | 含义 | 触发条件 |
|------|------|---------|
| `m` | **移动中** | 有未完成的路径 |
| `z` | **到达目标** | 当前路径点距离 < 阈值 |
| `A` | **路径已计算** | 寻路完成 |
| `B` | **避障中** | 检测到障碍物 |
| `C` | **强制移动** | 忽略某些限制 |
| `D` | **卡住检测** | 长时间未移动 |
| `E` | **重新寻路** | 路径被阻塞 |
| `G` | **忽略单位碰撞** | 穿透友军 |
| `M` | **到达路径终点** | k >= Q |
| `S` | **路径状态A** | 路径有效 |
| `T` | **路径状态B** | 备用路径 |
| `V` | **转向中** | 正在旋转朝向 |
| `aa` | **地形对齐** | 贴地移动 |
| `ab` | **水面模式** | 水上移动 |
| `ac` | **编队模式** | 跟随编队 |
| `ad` | **跟随模式** | 跟随目标单位 |
| `ae` | **撤退模式** | 反向逃离 |

### 物理属性

| 字段 | 含义 | 来源 |
|------|------|------|
| `F` | 当前加速度 | .ini maxAcceleration |
| `H` | 当前减速度 | .ini maxDeceleration |
| `I` | 转向加速度 | .ini turnAcceleration |
| `J` | 当前转向速度 | 动态计算 |
| `K` | 最大转向速度 | .ini maxTurnSpeed |
| `L` | 目标转向角度 | atan2(dy, dx) |
| `N` | **当前速度** | 动态计算 |
| `U` | 速度限制 | .ini maxSpeed |
| `W` | 角速度 | 动态计算 |
| `X` | 目标速度 | 向目标逼近 |
| `Y` | 当前角速度 | 动态计算 |
| `Z` | 速度平滑 | 避免突变 |

---

## `game.b.g` — A* 路径节点 (确认结构)

```
字段:
  a (b.j)    — 地块类型引用 (TileType)
  b, c (int) — 瓦片坐标 (tileX, tileY)
  d (short)  — ★ g_cost (从起点的实际代价)
  e (bool)   — 可通过 (walkable/passable)
  f (bool)   — 在关闭列表 (closed set)
  g (bool)   — 在开放列表 (open set)
  h (bool)   — 已访问 (visited)
  i (bool)   — 预留
  j (byte)   — 地形标志 (陆地/水域/悬崖)
  k (bool)   — 单位占据
  l (bool)   — 建筑占据
  m (g[])    — ★ 相邻节点数组 (8方向: 上下左右+对角)

静态方法:
  a(g, g) → bool — 节点比较 (优先队列排序: 按 f_cost = g_cost + h_cost)
  a() → g        — 克隆节点 (用于路径重建)
  a(b, e, j, int, short, short, bool) → g  — 工厂方法

A* 启发式: 曼哈顿距离或欧几里得距离
  h_cost = abs(tileX - goalX) + abs(tileY - goalY)  或
  h_cost = sqrt((tileX-goalX)² + (tileY-goalY)²)
```

---

## 移动公式 — 字节码还原

### 每帧移动更新

```
给定: game.f 节点, delta_ms (帧间隔)

1. 检查路径状态:
   if not z (未到达目标) and k < Q (还有路径点):
   
2. 获取当前目标路径点:
   idx = k * 2
   targetX = O[idx]
   targetY = O[idx + 1]
   
3. 计算距离和方向:
   dx = targetX - j.eo           (单位当前X → 目标X)
   dy = targetY - j.ep           (单位当前Y → 目标Y)
   dist = sqrt(dx² + dy²)
   
4. 到达判定:
   if dist < arrivalThreshold:   (通常 = 速度 × delta × 2)
     k++                          (下一个路径点)
     if k >= Q:                   (到达终点)
       z = true                   (设置到达标志)
       M = true                   (路径完成)
       return
   
5. 计算移动速度:
   maxSpeed = min(U, dist / delta_ms)   (不超过最大速度)
   actualSpeed = moveToward(N, maxSpeed, F * delta_ms)  (平滑加速)
   
6. 应用位移:
   moveX = dx / dist * actualSpeed * delta_ms
   moveY = dy / dist * actualSpeed * delta_ms
   j.eo += moveX
   j.ep += moveY
   
7. 旋转朝向:
   targetAngle = atan2(dy, dx)
   j.rotation = rotateToward(j.rotation, targetAngle, J * delta_ms)
```

### 单位位置设置

```
am.f(float x, float y):          // 直接位置设置 (非物理引擎)
  this.eo = x
  this.ep = y
  
说明: 单位没有速度/加速度的物理模拟。
位置由寻路系统每帧计算后直接赋值。
```

---

## GameWorld 三层仿真循环

```
game.a.a (44KB, extends game.n)

每帧 (variable delta):
  
  ┌─ 25ms 批次: 弹丸物理更新
  │   for each projectile:
  │     accumulated += delta
  │     while accumulated >= 50ms:
  │       update_physics(accumulated)
  │       update_state(accumulated)
  │       accumulated -= 50ms
  │
  ├─ 80ms 批次: 战斗维护 (CombatManager.b())
  │   索敌检查 (canAttack check)
  │   攻击冷却更新
  │   伤害计算
  │   击杀判定
  │
  └─ 250ms 批次: AI 决策 (n.f.c())
      波次计时器检查
      单位生成
      目标选择
      建造决策

关键字段:
  bE (a.c) — CombatManager 实例
  内部类 $1-$13 — 指令处理器/状态机
  a.a.a (abstract) — 指令基类
  a.a.b (enum) — 指令类型 (2值)
```

---

## 空间网格 — `units.f.c` (SpatialGrid, 8.7KB) ★已确认

```
字段 (13):
  a/b (int)          — 网格尺寸 (默认32×32)
  c/d (float)        — 单元格大小 (默认50.0px)
  e (f.a[][])        — ★ 2D网格数组 (空间哈希)
  f (f.d)            — RectFilter (矩形查询)
  g (f.g)            — CircleFilter (圆形查询, 索敌核心)
  h (f.h)            — LineFilter (线段查询, 弹道)
  i (utility.u)      — 空结果列表 (final, 复用)
  j (f.f)            — QueryResult (结果迭代器)
  k (Rect)           — 查询矩形 (复用, 减少GC)
  l (int)            — 查询计数器
  m (int)            — 最大查询深度

查询方法:
  a(x,y,r,unit,range,callback)→void    — 范围查询 (57B)
  a(x,y,r)→f.f                         — 圆形查询 (34B)
  a(x,y,r,utility.u)→void              — 圆形查询 (197B)
  b(x,y,r)→f.f                         — 另一个圆形查询 (34B)
  b(x,y,r,utility.u)→void              — ★ 231B, 详细查询
  a(n,x,y,r,utility.u)→void            — ★ 244B, 按队伍查询
  a(RectF,filter,unit,callback)→void   — ★ 885B, 通用矩形查询
  a(am)→void                           — ★ 242B, 添加单位到网格
  a(b.b)→void                          — 从地图初始化 (115B)
  a()→void                             — 清空网格 (121B)

坐标→网格索引:
  a(float)→int (25B) — X坐标→网格列
  b(float)→int (25B) — Y坐标→网格行

网格常量: 32×32, cell=50.0px → 覆盖1600×1600px 地图
```

---

## 寻路工具 — `utility.y` (12.7KB, 33方法) ★NEW

```
全静态工具类。处理移动类型与地形的交互。

字段:
  a (Paint)       — 调试渲染画笔
  b (RectF)       — 复用矩形
  c (ArrayList)   — 缓存数据
  d/e (Rect/RectF) — 复用几何对象
  f (Paint)       — 备用画笔
  g (utility.z[]) — 寻路缓存
  h (boolean)     — 初始化标志

地形检测:
  isInMap(x,y)→bool         — 坐标是否在地图内
  isOverClift(x,y)→bool     — 是否在悬崖上
  isOverLiquid(x,y)→bool    — 是否在水面上
  a(x,y,ao)→bool            — 移动类型能否通过该位置
  b(x,y,ao)→short           — ★ 路点值 (91B)
  c(x,y,ao)→int             — ★ 分组值 (116B)

编组/寻路:
  a(am)→bool                — 单位是否在路径组中
  a(am,float,...)→void      — 更新单位路径
  a(n,PointF)→void (177B)   — ★ 为队伍寻找路径
  b(am,float,float)→bool    — 单位坐标是否可达
  a(am,y)→void              — 设置单位路径目标

单位渲染:
  a(y,m.e,float,int)→void   — 渲染单位路径 (115B)
  a(y,int)→void (174B)      — 渲染单位路径点
  a(am,bool,bool)→bool      — 检查孤立组 (89B)
  "Could not find groupSize for:" — 孤立组检测失败
  "Unload, attachment data is null" — 卸载检测

Ao路径检查:
  a(x1,y1,x2,y2,ao)→bool   — ★ 移动类型能否从A到B (137B)
```

---

## 数学工具 — `gameFramework.f`

### `f.a` — 基础数学 (60KB)

```java
// 距离平方
static float a(float x1, float y1, float x2, float y2) {
    return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
}

// 欧几里得距离
static float b(float x1, float y1, float x2, float y2) {
    return sqrt(a(x1, y1, x2, y2));
}

// 衰减到零
static float a(float value, float step) {
    if (value > step) return value - step;
    if (value < -step) return value + step;
    return 0.0f;
}

// 移向目标
static float a(float current, float target, float step) {
    if (current > target + step) return current - step;
    if (current < target - step) return current + step;
    return target;
}

// 限制范围
static float b(float value, float lo, float hi) {
    if (value > hi) return hi;
    if (value < lo) return lo;
    return value;
}
```

### 碰撞检测

```java
// 圆形碰撞
static boolean collideCircles(float x1, float y1, float r1,
                               float x2, float y2, float r2) {
    return distanceSq(x1, y1, x2, y2) < (r1 + r2) * (r1 + r2);
}
```
