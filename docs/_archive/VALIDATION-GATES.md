# Rusted Warfare v1.15 — 指令验证门控系统源码逆向
> ⚠️ 历史文档 (v10.x 方法论, 2026-08-09) — 当前确定性重建方法学见 [PLAN.md](../deobfuscation/PLAN.md)

> 精确追踪指令从创建到执行的每一步校验，定位每个可能的失败点
>
> 关键文件: `Command.java`(639行), `UnitType.java`, `units/a/s.java`(动作), `units/a/v.java`(建造动作)

---

## 1. 验证管线总览

```
Command 创建 → l() 预验证 → CommandController 排队 → k() 执行 → 逐单位验证 → 动作分派
                                  │                          │
                                  ├── 检查1: 建造目标类型     ├── 检查4: 所有权/控制
                                  ├── 检查2: addedByAction    ├── 检查5: isValidNewWaypoint
                                  └── 检查3: 暂停状态         │   ├── 5a: null waypoint
                                                              │   ├── 5b: 无建造类型
                                                              │   ├── 5c: 无匹配动作
                                                              │   ├── 5d: 动作被锁定
                                                              │   └── 5e: 动作不可用
                                                              └── 检查6: 特殊动作可用
```

---

## 2. 预验证: Command.l() (line 639)

在指令排队前执行:

### 检查1: 建造目标类型 (line 649-652)

```java
if (gameVersion < 127
    && waypointAction != null
    && waypointAction.d() == av.c     // BUILD 类型
    && (as = waypointAction.a()) != null
    && (am = am.a(as)) != null        // 解析单位类型
    && !(am instanceof y)) {          // 不是 OrderableUnit
    → REJECT: "Rejecting non OrderableUnit build order"
}
```

> **RWAgent影响**: 确保建造目标的 UnitTypeHandle 解析为有效的 `y` (OrderableUnit) 子类。

### 检查2: addedByAction (line 653-656)

```java
if (waypointAction != null && waypointAction.n) {  // addedByAction 标志
    → REJECT: "Rejecting waypoint with addedByAction true"
}
```

> `n` 字段标记由动作链接间接产生的方式点，禁止作为独立指令重新提交。

### 检查3: 暂停状态 (line 418)

```java
if (ReplayEngine.isPlaying() && !this.a) {  // 回放暂停 + 非忽略暂停
    → RETURN (静默跳过)
}
```

---

## 3. 执行验证: Command.k() (line 413)

### 检查4: 所有权/控制 (line 510-543)

```java
for (每个目标单位 y5):
    if (y5 不属于 preExecPlayerState
        && 不共享控制) {
        → REMOVE: "Warning AI: gave an order to unit with team:X"
    }
    if (y5 有 canNotBeGivenOrdersByPlayer) {
        → REMOVE: "Warning unit has canNotBeGivenOrdersByPlayer set"
    }
```

> **RWAgent影响**: 确保注入指令的 `playerRef` (i字段) 与目标单位的所有者一致。

---

## 4. isValidNewWaypoint — 核心门 (UnitType.java line 3314)

### 完整方法

```java
public boolean a(au waypoint, boolean showLog) {
    // 5a: null waypoint
    if (waypoint == null) {
        if (showLog) log("isValidNewWaypoint: Skipping null waypoint");
        return false;
    }

    // 5b: 建造指令缺少建造类型
    if (waypoint.d() == av.c && waypoint.b == null) {
        if (showLog) log("isValidNewWaypoint: Skipping build waypoint with no buildType");
        return false;
    }

    // 5c: 查找匹配动作
    s action = this.a(waypoint.b, waypoint.d, false);
    if (action == null) {
        if (showLog) log("Unit '" + this.c() + "' can not queue build:<" + waypoint.b + ">");
        return false;
    }

    // 5d + 5e: 锁定/可用性 (仅非 addedByAction)
    if (!waypoint.n) {
        if (action.g(this)) {  // 5d: 被锁定
            if (showLog) log("Builder tried to queue a locked building:" + action);
            return false;
        }
        if (!action.b(this)) {  // 5e: 不可用
            if (showLog) log("Builder tried to queue a unavailable building:" + action);
            return false;
        }
    }

    return true;
}
```

### 5a: null waypoint

`waypointAction == null` → 指令没有附加路径点/动作数据。

### 5b: 无建造类型

`waypointAction.d() == av.c` (BUILD) 但 `waypointAction.b == null` (目标单位类型缺失)。

**RWAgent 修复**: `createCommand()` 中设置 `waypointAction.b = targetUnitTypeHandle`

### 5c: 无匹配动作 — 最常见失败

`UnitType.a(as targetType, int actionType, false)` (line 3125):

```java
public s a(as targetType, int actionType, boolean bl) {
    ArrayList actions = this.N();  // 获取单位的所有动作
    for (s action : actions) {
        as actionTarget = action.y();  // 动作的目标单位类型
        if (actionTarget != targetType) continue;
        if (actionType != -1 && actionType != action.t()) continue;
        if (!action.b(this) || !action.a(this, false)) continue;  // 需可用+通过过滤器
        return action;  // 找到可用动作
    }
    return null;  // 无匹配
}
```

> **失败原因**: 建造者的 `N()` 返回的动作列表不包含目标单位类型。
> 检查方法: `unit.N()` 遍历查看哪些类型可建造；`action.y()` 返回建造目标类型。

### 5d: 动作被锁定

`action.g(unit)` → 委托到 `action.h.b(unit)`。

默认 `a.b(am)` 返回 `false` (未锁定)。建造动作 `v.java` 覆盖:
```java
// 实验工厂/先进工厂 + 玩家有实验可见性 → 锁定
if (targetType == ar.D || targetType == ar.C) {
    if (player.hasExperimentalVisibility()) return true;
}
// 单位类型标记为隐藏 w() → 锁定
if (targetType.w()) return true;
```

### 5e: 动作不可用

`action.b(unit)` → 委托到 `action.h.a(unit, false)`。

默认 `a.a(am, false)` 返回 `true` (可用)。可通过以下方式覆盖:
- 包装动作 `h.java`: `this.b.isAvailable(this, unit)` 检查
- 组动作 `g.java`: `this.c.isAvailable(this, unit)` 检查
- Mod 单位: `isAvailable` 逻辑布尔条件

---

## 5. 动作系统

### 5.1 动作基类 (units/a/s.java)

| 方法 | 含义 | 行号 |
|------|------|------|
| `y()` → as | 动作的目标单位类型 | — |
| `t()` → int | 动作类型ID | — |
| `b(am)` → boolean | 是否可用 (isAvailable) | 215 |
| `g(am)` → boolean | 是否锁定 (isLocked) | 170 |
| `a(am, boolean)` → boolean | 过滤器检查 | — |

### 5.2 建造动作 (units/a/v.java)

```java
class v extends s {
    as a;   // 要建造的单位类型
    int b;  // 科技等级 (1/2/3)
    a h;    // 动作过滤器 (锁定/可用性)
}
```

动作ID格式: `"b_" + unitType.v()` (+ `"_" + techLevel`)

### 5.3 包装器动作

| 类 | 用途 |
|----|------|
| `h.java` | 包装动作 + isAvailable 过滤器 |
| `g.java` | 组动作 + 目标特定 isAvailable 过滤器 |

---

## 6. 特殊动作验证 (line 587-613)

```java
for (每个单位 y5):
    s specialAction = y5.a(this.specialAction);  // 查找动作
    if (!specialAction.b(y5)) {                   // 可用?
        log("!isAvailable specialAction:" + specialAction);
        continue;
    }
    specialAction.a(y5);  // 激活
```

---

## 7. 日志限流机制

```java
// Command.java line 572
y5.a(this.waypointAction, com.corrodinggames.rts.gameFramework.c.e < 5);
```

`c.e` 是静态计数器，前5次失败记录日志，之后静默拒绝。

**RWAgent 建议**: 通过反射读取 `c.e` 判断是否被限流。

---

## 8. 完整失败原因速查

| # | 失败点 | 日志关键字 | 修复建议 |
|---|--------|-----------|---------|
| 1 | 非 OrderableUnit | `Rejecting non OrderableUnit build order` | 确保 as 解析为 y 子类 |
| 2 | addedByAction | `Rejecting waypoint with addedByAction true` | 清除 n 标志 |
| 3 | 游戏暂停 | (无) | 检查 GlobalState 暂停状态 |
| 4 | 所有权不匹配 | `Warning AI: gave an order to unit` | 设置正确 playerRef |
| 5 | canNotBeGivenOrders | `has canNotBeGivenOrdersByPlayer` | 检查单位属性 |
| 6 | null waypoint | `Skipping null waypoint` | 确保 waypointAction 已设置 |
| 7 | 无建造类型 | `Skipping build waypoint with no buildType` | 设置 waypointAction.b |
| 8 | 无匹配动作 | `can not queue build` | 检查单位 N() 动作列表 |
| 9 | 动作锁定 | `tried to queue a locked building` | 检查科技/可见性 |
| 10 | 动作不可用 | `tried to queue a unavailable building` | 检查 isAvailable 条件 |

---

## 9. 对 RWAgent 的具体修复

```java
// 修复前: 可能被检查5b、5c、8拒绝
Command cmd = createCommand();
cmd.waypointAction.a = BUILD;
// 缺少: cmd.waypointAction.b = targetType;

// 修复后:
Command cmd = createCommand();
cmd.waypointAction.d = av.c;                    // BUILD
cmd.waypointAction.b = targetUnitTypeHandle;     // ← 关键: 设置建造类型
cmd.waypointAction.e = buildX;                   // X坐标
cmd.waypointAction.f = buildY;                   // Y坐标
cmd.i = playerRef;                               // ← 关键: 玩家引用
cmd.q = playerIndex;                             // ← 关键: 玩家位图
```
