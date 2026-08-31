# A* 寻路求解器 — 源码交叉验证
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 双向A*, 8方向移动, 转向代价, 裕度惩罚, 孤立组回退
>
> 文件: `gameFramework/k/o.java`, `i.java`(代价地图), `m.java`(最小堆)

---

## 1. 求解器架构 (o.java)

```java
public final class o implements Runnable {
    // 双向搜索队列
    final m o = new m();  // 前向优先队列 (从起点)
    final m p = new m();  // 后向优先队列 (从终点)
    
    // 代价地图引用
    byte[] b;    // 地形代价 (d[])
    byte[] c;    // 建筑代价 (e[])
    byte[] d;    // 单位代价 (f[])
    short[] e;   // 孤立组ID (g[])
    byte[] f;    // ★ 裕度代价 (j[], 0-4)
    
    int i = 5;   // 搜索严重度
}
```

## 2. 8方向移动 (lines 552-579)

```
方向编码:
  0: 东   (+1,  0)
  1: 东北 (+1, +1)
  2: 北   ( 0, +1)
  3: 西北 (-1, +1)
  4: 西   (-1,  0)
  5: 西南 (-1, -1)
  6: 南   ( 0, -1)
  7: 东南 (+1, -1)

代价:
  直走 (0,2,4,6): 10
  对角 (1,3,5,7): 14  (= 10√2 ≈ 14.14)
```

## 3. 代价计算 (lines 580-607)

```java
// 基础移动代价
if (直走): cost = 10 + terrainCost + 1
if (对角): cost = 14 + terrainCost + 1
// 对角时检查: 相邻两格是否可通行
if (cellX, prevY 不可通行 || prevX, cellY 不可通行) → 跳过

// 转向代价
if (方向改变):
    if (正向搜索): cost += b(prevDir, newDir)
    if (反向搜索): cost += c(prevDir, newDir)
    // b/c 方法: 90°转向=21, 其他=4-25

// 裕度惩罚
if (clearance != null):
    cost += (4 - clearance[cell]) × searchSeverity

// 禁区惩罚
if (restrictedArea > 0 && clearance[cell] <= restrictedArea):
    cost += 100
```

## 4. 双向搜索交替 (lines 441-461)

```java
// 每400次迭代切换搜索方向
if (iterationCount % 400 == 0):
    swap(forwardQueue, backwardQueue)
```

## 5. 孤立组回退 (lines 323-361)

```java
// 如果终点在不同孤立组:
if (endCell.isolatedGroup != startCell.isolatedGroup):
    // 从终点向外扩展搜索 (最多25格 → 全地图)
    for (dx = -25; dx <= 25; dx++):
        for (dy = -25; dy <= 25; dy++):
            if (cell[d+dx][e+dy].group == startGroup
                || cell[d+dx][e+dy].group == 0):
                distance = sqrt((dx)² + (dy)²)
                if (distance < best):
                    best = distance
                    newEnd = (d+dx, e+dy)
    // 用最近可达点替代终点
```

## 6. 无路径回退 (lines 615-637)

```java
// 如果双向搜索未找到路径:
if (!pathFound):
    // 遍历所有已访问节点
    for (visitedNode in allVisited):
        distance = dist(visitedNode, originalEnd)
        if (distance < best):
            best = distance
            closestNode = visitedNode
    // 返回路径到最近的可达点
```

## 7. 线程模型

```java
// 路径求解器是 Runnable, 运行在后台线程
run():
    while (alive):
        wait()          // 等待路径请求
        k = nextRequest // 获取路径请求
        result = e()    // 执行 A* 搜索
        k.setResult(result)
```

## 8. 对 RWAgent 的影响

| 场景 | 影响 |
|------|------|
| 建造位置在不同岛屿 | 孤立组回退 → 寻路到最近可达点, 建造可能失败 |
| 建造位置被建筑挡住 | 建筑代价=-1 → 路径不可能 → isValidNewWaypoint 失败 |
| 密集基地中 | 裕度代价高 → 路径被惩罚但不会完全阻断 |
| 单位太大 | 裕度检查(≤ restrictedArea) → +100 惩罚 → 可能找不到路径 |
