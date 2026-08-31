# Rusted Warfare v1.15 — A*寻路引擎源码逆向
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 双向A* + 代价地图 + 背景线程 + 预分配节点池 (GC-free)
> 关键文件: `gameFramework/k/`(17个类), `PathFinder.java`, `AStarSearch.java`

---

## 1. 架构总览

```
PathFinder(l)                     ← 主管理器 (静态实例)
├── PathSolver(i)[]               ← 每区块一个求解器实例 (byte[]代价图)
├── PathSolverRunner(o)           ← 后台工作线程 (LinkedList任务队列)
│   ├── AStarSearch(d)            ← PriorityQueue A*实现 (975节点数组)
│   │   ├── AStarNode(n)          ← 搜索节点 (Comparable, x/y/cost)
│   │   ├── NodePool(m)           ← 预分配节点池 (1000节点, GC-free)
│   │   └── NodeQueue(e)          ← 两级优先级队列 (低/高代价桶)
│   ├── PathCostCalc(f)           ← 移动代价计算器
│   │   └── PathCostCalculator(k) ← 基础代价计算器
│   ├── TerrainCost(g)            ← 地形移动代价图 (byte[]数组)
│   └── PathNode(p)               ← 最终路径位置 (short x/y)
└── UnitList(a) / UnitListIterator(b) ← 自定义列表结构
```

---

## 2. 核心类详解

### 2.1 PathFinder (l) — 主管理器

**文件**: `com/corrodinggames/rts/gameFramework/k/PathFinder.java` (601行)

| 字段 | 类型 | 含义 |
|------|------|------|
| e | PathCostCalc | 静态代价计算器 |
| o | PathSolverRunner | 工作线程 |
| v | PathSolver[] | 求解器数组 |
| x/y/z/A | PathSolver | 当前使用的求解器引用 |
| u | ArrayList | 求解器注册列表 |
| k | ArrayList | 静态路径缓存 |
| w | Paint | 调试绘制画笔 |

**静态标志**:
- `a=false` — 调试模式
- `b` — 启用寻路 (!as标志)
- `c/d` — 额外调试标志
- `m` — 最终启用标志

### 2.2 PathSolver (i) — 区块求解器

**文件**: `com/corrodinggames/rts/gameFramework/k/PathSolver.java` (514行)

每个地图区块一个实例:

| 字段 | 类型 | 含义 |
|------|------|------|
| b/c | int | 宽度/高度 (区块尺寸) |
| d/e/f | byte[] | 代价图 (多层: 基本/合并/最终) |
| g | short[] | 方向/路径数据 |
| h | HashMap | 碰撞/障碍缓存 |
| i | int | 路径状态码 |
| k | int | 当前代价 (-99=未初始化) |
| l | int | 区块索引 |
| a | MovementTypeEnum | 移动类型 (地面/水面/空中...) |

### 2.3 PathSolverRunner (o) — 工作线程

**文件**: `com/corrodinggames/rts/gameFramework/k/PathSolverRunner.java` (976行)

实现 `Runnable`，在后台线程运行A*搜索:

| 字段 | 类型 | 含义 |
|------|------|------|
| b/c/d | byte[] | 代价数组 (3层) |
| e | short[] | 路径数据 |
| f | byte[] | 标记数组 |
| l | int[][] | 分层代价表 |
| m | byte[][] | 分层标记表 |
| n | PathSolver | 当前求解器 |
| t | LinkedList | 任务队列 (静态共享) |
| w | Thread | 工作线程 |
| s | boolean | 运行标志 (volatile) |

### 2.4 AStarSearch (d) — A*核心实现

**文件**: `com/corrodinggames/rts/gameFramework/k/AStarSearch.java` (242行)

使用 `PriorityQueue` 的标准A*:

| 字段 | 类型 | 含义 |
|------|------|------|
| o | AStarNode[] | 搜索节点数组 (975容量) |
| p | NodePool | 节点池 (100容量) |
| q | PriorityQueue | 开放列表 |
| r | NodePool | 关闭列表池 (300容量) |
| j/k/l/m/n | int | 搜索起点/终点/状态 |

**静态统计**:
- `a~g` — 搜索计数器
- `h/i` — 时间统计

### 2.5 AStarNode (n) — 搜索节点

**文件**: `com/corrodinggames/rts/gameFramework/k/AStarNode.java` (45行)

实现 `Comparable`:

| 字段 | 类型 | 含义 |
|------|------|------|
| a | short | X坐标 |
| b | short | Y坐标 |
| c | int | 总代价 (f = g + h) |

**启发式函数**: `a(int g, int dx, int dy)`
```
cost = g + (|dx| + |dy|) * 11 + (dx,dy对角) * -7
```
使用 Manhattan距离×11 + 对角线修正-7。

### 2.6 NodePool (m) — 节点池

**文件**: `com/corrodinggames/rts/gameFramework/k/NodePool.java` (68行)

预分配策略:
```java
class NodePool {
    UnitList pool = new UnitList(1000 + 100);
    
    NodePool() {
        for (int i = 0; i < 1000; i++) {
            pool.add(new AStarNode());  // 预分配1000节点
        }
    }
    
    AStarNode obtain() {
        if (pool.isEmpty()) {
            stats.misses++;  // 池为空时分配新节点
            return new AStarNode();
        }
        return pool.pop();
    }
}
```

### 2.7 NodeQueue (e) — 两级优先级队列

**文件**: `com/corrodinggames/rts/gameFramework/k/NodeQueue.java` (117行)

| 字段 | 类型 | 含义 |
|------|------|------|
| c | UnitList(100) | 低代价桶 (当前代价) |
| d | UnitList(900) | 高代价桶 (高于阈值) |
| b | int | 代价阈值 |

节点先放入低代价桶，当低代价桶为空时切换高代价桶并更新阈值。

### 2.8 PathNode (p) — 路径位置

**文件**: `com/corrodinggames/rts/gameFramework/k/PathNode.java` (182行)

| 字段 | 类型 | 含义 |
|------|------|------|
| a | short | X坐标 |
| b | short | Y坐标 |

关键方法:
- `a(PathSolverRunner)` — 从3层代价图计算该位置的总代价 (b + c + d×10)

### 2.9 TerrainCost (g) — 地形代价图

**文件**: `com/corrodinggames/rts/gameFramework/k/TerrainCost.java` (46行)

| 字段 | 类型 | 含义 |
|------|------|------|
| a/b | int | 宽度/高度 |
| e/f | byte[] | 代价数组 (2层) |

---

## 3. 寻路流程

```
1. PathFinder.findPath(from, to, moveType)
   ├── 选择/创建 PathSolver 实例
   └── 提交任务到 PathSolverRunner 队列

2. PathSolverRunner.run() [后台线程]
   ├── 从队列取出任务
   ├── 初始化代价图 (b/c/d/f数组)
   ├── AStarSearch.search()
   │   ├── 从NodePool获取节点
   │   ├── PriorityQueue排序
   │   ├── 扩展邻居 (8方向)
   │   ├── 计算代价 (TerrainCost + PathCostCalc)
   │   └── 到达目标 → 回溯路径
   ├── 构建路径列表 (PathNode[])
   └── 回调 PathSolver 完成

3. PathCostCalc(f).calculate(unit)
   ├── 获取移动类型 (MovementTypeEnum)
   ├── 查询地形代价 (TerrainCost)
   ├── 应用单位尺寸修正
   └── 返回最终代价

4. MovementController 使用路径
   ├── 遍历 PathNode 列表
   ├── Bresenham直线检测 (PathfindingHelper)
   └── 沿路径移动单位
```

---

## 4. 支持文件

| 文件 | 位置 | 用途 |
|------|------|------|
| MovementController | game/MovementController.java | 每帧移动更新 |
| PathfindingHelper | game/units/PathfindingHelper.java | Bresenham直线检测 |
| SpatialGrid | game/units/f/SpatialGrid.java | 32×32空间网格 |
| MovementTypeEnum | game/units/MovementTypeEnum.java | 8种移动类型 |

**交叉验证**: MASTER_MAPPING.md — `fw.k.i=PathSolver` `fw.k.k=PathCostCalc` ✅



