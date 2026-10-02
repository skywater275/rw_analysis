# 空间网格系统 — 源码交叉验证
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 32×32网格, 队伍桶, 3种查询模式, 每帧重建
>
> 文件: `SpatialGrid.java`, `SpatialGridCell.java`

---

## 1. 网格结构

```java
// SpatialGrid.java:22-35
public final class SpatialGrid {
    int a;                // 地图宽 (tile)
    int b;                // 地图高 (tile)
    float c;              // 1.0/width  (世界→格子)
    float d;              // 1.0/height (世界→格子)
    public a[][] e;       // ★ 32×32 网格, 每格 = SpatialGridCell
    final int l = 32;     // ★ 固定 32×32
    int m;                // 最大队伍索引
}
```

### 坐标系

```java
// line 258-267: 世界坐标 → 网格索引
public int a(float worldX) {
    int cell = (int)(worldX * this.c);  // c = 1.0/mapWidth
    if (cell < 0) cell = 0;
    if (cell >= 32) cell = 31;          // 钳制到 0-31
    return cell;
}
```

## 2. 队伍桶系统

每个网格单元格 (`SpatialGridCell` = `a`) 包含**按队伍分桶的数组**:

```java
// line 130: 队伍桶访问
b bucket = aArray[cellX][cellY].b[teamSlot];  // b[] = 每个队伍一个桶

// 桶内存储: am[] (UnitInstance 数组) + count
```

## 3. 三种查询模式

### 模式1: 简单圆形查询 `a(x, y, radius, result)` [line 54]

```java
// 不检查碰撞半径, 只检查单位中心点是否在圆内
for (cell in overlapping cells):
    for (unit in cell.allUnits):
        if (unit.pos in circle): result.add(unit)
```

### 模式2: 带碰撞半径的圆形查询 `b(x, y, radius, result)` [line 88]

```java
// 考虑单位的 cj (collisionRadius), +50 像素缓冲区
for (cell in overlapping cells):
    for (unit in cell.allUnits):
        r = unit.collisionRadius
        if (circle intersects unit.boundingCircle): result.add(unit)
```

### 模式3: 队伍过滤查询 `a(team, x, y, radius, result)` [line 116]

```java
// 只查特定队伍的桶, 带碰撞半径
int teamSlot = team.playerSlot;
for (cell in overlapping cells):
    b bucket = cell.b[teamSlot];  // ★ 直接访问队伍桶
    for (unit in bucket):
        if (circle intersects unit): result.add(unit)
```

### 模式4: 复杂过滤查询 `a(RectF, e, y, float, i)` [line 145]

```java
// 支持: 排除队伍, 仅敌人, 仅友军, 回调
i2.excludeTeam(y2)    → 排除指定队伍
i2.onlyEnemiesOfTeam(y2) → 仅敌方
i2.onlyTeam(y2)       → 仅友方
i2.callback(y2, f2, unit) → 逐单位回调 (索敌/筛选)
```

## 4. 单位索引 — `a(am)` [line 292]

```java
public void a(am unit) {
    // 已死亡 → 从旧格子移除, 标记 -1
    if (unit.isDead) {
        if (unit.dl != -1)
            e[unit.dl][unit.dm].b(unit);  // 从桶移除
        unit.dl = unit.dm = -1;
        return;
    }
    
    // 计算新格子
    int cellX = this.a(unit.posX);
    int cellY = this.b(unit.posY);
    int team = unit.owningPlayer.playerSlot;
    
    // 如果位置没变 → 跳过
    if (unit.dl == cellX && unit.dm == cellY && unit.dn == team)
        return;
    
    // 从旧格子移除
    if (unit.dl != -1)
        e[unit.dl][unit.dm].b(unit);
    
    // 记录新位置
    unit.dl = cellX;     // ★ 缓存格子X
    unit.dm = cellY;     // ★ 缓存格子Y
    unit.dn = team;      // ★ 缓存队伍
    
    // 加入新格子
    e[cellX][cellY].a(unit);
}
```

## 5. 全局重建 — `a()` [line 280]

```java
// 每帧调用: GameScreen.a(f2) → this.cc.a()
public void a() {
    // 遍历所有单位
    for (unit in am.bE):
        // 检查是否需要更新 (位置/队伍变化)
        if (unit.posX*f2 != unit.dl || unit.posY*f3 != unit.dm || ...)
            this.a(unit);  // 更新索引
}
```

## 6. 对 RWAgent 的意义

| 查询类型 | 使用场景 |
|---------|---------|
| 模式1 (简单圆形) | 快速检查位置是否有单位 |
| 模式2 (带碰撞) | 建造位置验证 — 检查是否与现有单位重叠 |
| 模式3 (队伍过滤) | 只查队友/敌方单位 |
| 模式4 (复杂过滤+回调) | AI索敌, 战斗目标获取 |

### 建造位置检查

```java
// 检查建造位置是否被占用:
spatialGrid.b(buildX, buildY, buildingRadius, result);
if (result.size() > 0) → 位置被占用
```

### 单位位置缓存

每个 UnitInstance 缓存其网格坐标:
- `dl` = lastCellX
- `dm` = lastCellY
- `dn` = lastTeamSlot

变更时自动更新, 不变时跳过 — 高效的增量索引。
