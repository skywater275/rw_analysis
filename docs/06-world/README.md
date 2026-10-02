# 06-world — 世界与地图

> **职责**：地形 / 瓦片 / 寻路 / 空间索引 / 视野

> 本文处于**项目分析上限** ✓：数据全部来自映射库 / 反编译树 / 交付物实测 ✓，口径见 [../STATUS.md](../STATUS.md) ✓。

## 一、数据总览

| 指标 | 值 |
|---|---|
| 映射条目 | **1021** 条 |
| 涉及类 | **35** 个 |
| 有可读名 | **32** 个（91%）|
| ★ **已替换为我们的字节** | **20** 个（57%）|
| 主包 | `com.corrodinggames.rts.game` · `com.corrodinggames.rts.game.b` · `com.corrodinggames.rts.gameFramework.k` · `game.b.k` |

### 交付物替换状态（★ 该域我们掌控多少 ✓）

| 状态 | 类数 | 含义 |
|---|---|---|
| **我们的字节** ✓ | **20** | 已由反编译源重建并在交付物中替换 |
| 原版字节 | 12 | 仍使用原版混淆字节（结构一致 ✓）|
| 未进交付物 | 3 | 该域映射涉及、但不属交付物条目 |

## 二、包结构与源码位置

| 混淆包 | 映射行 |
|---|---|
| `com.corrodinggames.rts.game` | 730 |
| `com.corrodinggames.rts.game.b` | 225 |
| `com.corrodinggames.rts.gameFramework.k` | 58 |
| `game.b.k` | 6 |
| `game.b.a` | 1 |
| `game.b.b` | 1 |

**03 源码位置**（该域主包 ✓）：

| 包（可读路径） | 文件 | 行数 |
|---|---|---|
| `com/corrodinggames/rts/game/units` | 81 | 18732 |
| `com/corrodinggames/rts/game/units/custom` | 92 | 16093 |
| `com/corrodinggames/rts/gameFramework/network` | 81 | 12066 |
| `com/corrodinggames/rts/gameFramework` | 95 | 11769 |
| `com/corrodinggames/rts/game/units/custom/logicBooleans` | 215 | 10716 |
| `com/corrodinggames/rts/gameFramework/ui` | 61 | 8881 |
| `com/corrodinggames/rts/gameFramework/utility` | 64 | 7954 |
| `com/corrodinggames/rts/game` | 24 | 7757 |
| `com/corrodinggames/rts/game/units/commands` | 41 | 6098 |
| `com/corrodinggames/rts/game/ai` | 28 | 5482 |

## 三、★ 全量类清单（35 个 ✓）

> `状态`：**我们的字节** ✓ / 原版字节 / 未进交付物 ✓；`字段`/`方法` 为该类**已映射成员数** ✓。

| 混淆类名 | 可读名 | 字段 | 方法 | 状态 |
|---|---|---|---|---|
| `n` | **PlayerState** | 257 | 91 | 原版字节 |
| `b` | **MapEngine** | 71 | 68 | 原版字节 |
| `f` | **MovementController** | 115 | 19 | 原版字节 |
| `g` | **GameSettings** | 112 | 2 | **我们的字节** ✓ |
| `i` | **GameEngine** | 44 | 42 | 原版字节 |
| `s` | **TeamUnitTracker** | 15 | 5 | **我们的字节** ✓ |
| `l` | **PathFinder** | 13 | 5 | 原版字节 |
| `a` | **MapSpawn** | 16 | 0 | 原版字节 |
| `d` | **MapLayerRenderer** | 16 | 0 | **我们的字节** ✓ |
| `e` | **TMXMapLoader** | 13 | 0 | 原版字节 |
| `g` | **MapLayer** | 13 | 0 | 原版字节 |
| `j` | **TilesetDef** | 12 | 0 | 原版字节 |
| `h` | **h** | 7 | 1 | **我们的字节** ✓ |
| `k` | **PathCostCalculator** | 7 | 1 | **我们的字节** ✓ |
| `h` | **TagFilter** | 7 | 0 | **我们的字节** ✓ |
| `q` | **UnitTypeComparator** | 7 | 0 | **我们的字节** ✓ |
| `i` | **MapLayerDef** | 6 | 0 | **我们的字节** ✓ |
| `k` | **TileEntry** | 6 | 0 | **我们的字节** ✓ |
| `i` | **PathSolver** | 6 | 0 | 原版字节 |
| `o` | **PathSolverRunner** | 5 | 1 | 原版字节 |
| `k` | — | 6 | 0 | 未进交付物 |
| `o` | **GameMode** | 5 | 0 | **我们的字节** ✓ |
| `p` | **UnitManager** | 5 | 0 | **我们的字节** ✓ |
| `f` | **PathCostCalc** | 1 | 3 | **我们的字节** ✓ |
| `c` | **FogMapRenderer** | 0 | 3 | **我们的字节** ✓ |
| `b` | **UnitListIterator** | 3 | 0 | 原版字节 |
| `n` | **AStarNode** | 3 | 0 | **我们的字节** ✓ |
| `m` | **ProjectileType** | 2 | 0 | **我们的字节** ✓ |
| `h` | **TileDrawer** | 0 | 1 | **我们的字节** ✓ |
| `t` | **BuildQueue** | 0 | 1 | **我们的字节** ✓ |
| `u` | **LobbyPlayer** | 0 | 1 | **我们的字节** ✓ |
| `d` | **FastNodeQueue** | 0 | 1 | **我们的字节** ✓ |
| `p` | **PathNode** | 0 | 1 | **我们的字节** ✓ |
| `a` | — | 1 | 0 | 未进交付物 |
| `b` | — | 0 | 1 | 未进交付物 |

## 四、关键成员速查（真实可读名 ✓）

| 类 | 成员数 | 可读成员（节选）|
|---|---|---|
| `n`（PlayerState） | 348 | `action_set_rally(f)` · `action_upgrade(f)` · `activePlayerCount(f)` · `addTagFilter(m)` · `add_group_to_selection_1(f)` · `add_group_to_selection_10(f)` · `add_group_to_selection_2(f)` · `add_group_to_selection_3(f)` · `add_group_to_selection_4(f)` … |
| `b`（MapEngine） | 139 | `blitFogEdgeTiles(m)` · `blockedTilePaintA(f)` · `blockedTilePaintB(f)` · `blockingGrid(f)` · `buildBlockingGrid(f)` · `buildFogCache(m)` · `buildMaskCombinations(m)` · `buildableTilePaintA(f)` · `buildableTilePaintB(f)` … |
| `f`（MovementController） | 134 | `accelMultiplier(f)` · `acceleration(f)` · `activePaint(f)` · `am1(f)` · `am2(f)` · `applyGravity(m)` · `arrivalDistance(f)` · `attachedGameObject(f)` · `attemptHitUnit(m)` … |
| `g`（GameSettings） | 114 | `acceleration(f)` · `aiEnabled(f)` · `airDamageMultiplier(f)` · `allowCheats(f)` · `allowMidGameJoin(f)` · `allowSpectators(f)` · `alpha(f)` · `altDamage(f)` · `altMaxRange(f)` … |
| `i`（GameEngine） | 86 | `arrayList2(f)` · `arrayList3(f)` · `audioEngine(f)` · `captureScreenshot(m)` · `checkWinCondition(m)` · `collisionEngine(f)` · `commandController(f)` · `deltaTime(f)` · `deltaTimeBackup(f)` … |
| `s`（TeamUnitTracker） | 20 | `buildQueue1(f)` · `buildQueue2(f)` · `buildingCapacityUsed(f)` · `buildingCount(f)` · `completedCount(f)` · `decrementTypeCount(m)` · `decrementUnitCount(m)` · `hasMobileUnits(f)` · `incomeRate(f)` … |
| `l`（PathFinder） | 18 | `airSolver(f)` · `cacheEnabled(f)` · `cancellingPaths(f)` · `debugPaint(f)` · `findPath(m)` · `getSolver(m)` · `landSolver(f)` · `pathSolver(f)` · `queuedPaths(f)` … |
| `a`（MapSpawn） | 16 | `boundsRect(f)` · `customProperties(f)` · `maxUnitCount(f)` · `playerRef(f)` · `spawnHeight(f)` · `spawnId(f)` · `spawnIndex(f)` · `spawnInterval(f)` · `spawnWidth(f)` … |

## 五、映射备注（项目分析结论 ✓）

- **`a`**（MapSpawn）：Custom properties from TMX (Properties)
- **`a`**（MapSpawn）：List of spawned units (m=ArrayList)
- **`a`**（MapSpawn）：Max units to spawn (int  -1=unlimited)
- **`a`**（MapSpawn）：Player reference name (String)
- **`b`**（MapEngine）：Build blocking grid
- **`b`**（MapEngine）：Camera focus point (PointF)
- **`b`**（MapEngine）：Check tile buildable
- **`b`**（MapEngine）：Clear all fog of war arrays
- **`c`**（FogMapRenderer）：证据串自报名(ldc 绑定, A 级)；描述符 ()V
- **`c`**（FogMapRenderer）：证据串自报名(ldc 绑定, A 级)；描述符 (I)V
- **`d`**（MapLayerRenderer）：Current tileset (TilesetDef)
- **`d`**（MapLayerRenderer）：Currently rendering layer (MapLayer)
- **`d`**（MapLayerRenderer）：Dirty/redraw rectangle (Rect)
- **`d`**（MapLayerRenderer）：First visible tile column (int)

## 核心概念

**世界与地图**：地形、地块、寻路网格、碰撞、视野。

| 组成 | 说明 |
|---|---|
| **地图** | TMX 地图加载：地块层、资源点、出生点 |
| **地块/瓦片** | 地形类型（可通行/不可通行/水面/悬崖） |
| **寻路** | A* + 分层/缓存（大量单位同帧寻路必须高效） |
| **空间索引** | 单位/物体的邻近查询（碰撞、攻击目标） |
| **视野/迷雾** | 可见性判定 |

## 关键机制

1. **A\* 寻路**：节点代价 + 启发式；带**路径缓存**避免重复计算
2. **路径跟随**：路径点序列 → 转向/加速/避障
3. **碰撞与分离**：单位间推开，避免重叠
4. **空间哈希**：把邻近查询从 O(n²) 降到近似 O(n)
5. **地图数据驱动**：TMX + tileset ⇒ 可自制地图 ✓

## 修改指南

| 想改什么 | 改哪里 |
|---|---|
| 做地图 | 地图编辑器导出 TMX ✓ |
| 寻路代价 | 寻路类 ✓ |
| 单位体积/碰撞 | INI + 移动类 ✓ |


## 六、子文档

- [`ASTAR-PATHFINDING.md`](ASTAR-PATHFINDING.md)
- [`MAP-SYSTEM.md`](MAP-SYSTEM.md)
- [`MOVEMENT.md`](MOVEMENT.md)
- [`PATHFIND.md`](PATHFIND.md)
- [`SPATIAL.md`](SPATIAL.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓


## 六、子文档

- [`ASTAR-PATHFINDING.md`](ASTAR-PATHFINDING.md)
- [`MAP-SYSTEM.md`](MAP-SYSTEM.md)
- [`MOVEMENT.md`](MOVEMENT.md)
- [`PATHFIND.md`](PATHFIND.md)
- [`SPATIAL.md`](SPATIAL.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓
