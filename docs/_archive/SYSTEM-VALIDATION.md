# 验证系统 — 源码交叉验证 (逐行追溯)
> ⚠️ 历史文档 (v10.x 方法论, 2026-08-09) — 当前确定性重建方法学见 [PLAN.md](../deobfuscation/PLAN.md)

> 精确追踪每个验证检查点的源码位置，与 VALIDATION-GATES.md 交叉验证
>
> 关键文件: `UnitType.java:3314`, `UnitType.java:3125`, `UnitInstance.java:1392`, `Command.java:572`

---

## 1. 验证管线 — 完整调用链 (源码行号验证)

```
RWAgent 注入 Command
│
├── CommandController.b(player)
│   └── cmd.l()  ← 预验证 [Command.java:639]
│
└── GameScreen.a(f2) → CommandController.c() [每帧]
    └── cmd.k()  ← ★ 执行 [Command.java:413]
        │
        ├── [line 467] 系统生成检查: waypoint.d()==av.c && a()!=null
        ├── [line 510] 所有权检查: preExecPlayerState 验证
        ├── [line 544] stopOrUndo 处理
        ├── [line 567] attack move 合并检查
        │
        ├── [line 572] ★ isValidNewWaypoint ← UnitType.java:3314
        │   ├── check1: au2 == null              [line 3315]
        │   ├── check2: au2.b == null (BUILD)    [line 3321]
        │   ├── check3: s2 == null (action找不)  [line 3328]
        │   ├── check4: s2.g(this) (锁定)        [line 3336]
        │   └── check5: !s2.b(this) (不可用)     [line 3342]
        │
        ├── [line 580] y5.d(waypoint) ← 克隆动作
        ├── [line 581] ab.a(y5, au)  ← 加入编队
        └── [line 582] y5.a(au)       ← 写入单位
            │
            └── UnitType.a(au) [line 3265]
                └── 设置 g[f] = au, f++ ← 路径点入队
```

## 2. isValidNewWaypoint — 逐行源码追踪

### 文件: UnitType.java (y.java), line 3314-3351

```java
// 3314: Method signature
public boolean a(au waypoint, boolean showLog) {
    // ─── CHECK 1: null waypoint ───
    // 3315
    if (waypoint == null) {
        if (showLog) {
            l.b("isValidNewWaypoint: Skipping null waypoint");  // 3317
        }
        return false;  // 3319
    }

    // ─── CHECK 2: Build without buildType ───
    // 3321: 只有 BUILD (av.c) 类型才检查
    if (waypoint.d() == av.c) {
        // 3322: b 字段 = 目标单位类型 (as/UnitTypeHandle)
        if (waypoint.b == null) {
            if (showLog) {
                l.b("isValidNewWaypoint: Skipping build waypoint with no buildType");  // 3324
            }
            return false;  // 3326
        }

        // ─── CHECK 3: Action not found ───
        // 3328: a(as, int, boolean) 查找建造动作
        s action = this.a(waypoint.b, waypoint.d, false);
        if (action == null) {
            if (showLog) {
                l.b("Unit '" + this.r().i()     // 3331
                    + "' can not queue build:" + waypoint.b.i());
            }
            return false;  // 3333
        }

        // ─── CHECK 4+5: Locked/Unavailable ───
        // 3335: n 标志 = "由动作添加" (跳过锁定检查)
        if (!waypoint.n) {
            // 3336: g(am) → isLocked
            if (action.g(this)) {
                if (showLog) {
                    l.b("Builder '" + this.r().i()       // 3338
                        + "' tried to queue a locked building:" + action.O());
                }
                return false;  // 3340
            }
            // 3342: b(am) → isAvailable
            if (!action.b(this)) {
                if (showLog) {
                    l.b("Builder '" + this.r().i()       // 3344
                        + "' tried to queue a unavailable building:" + action.O());
                }
                return false;  // 3346
            }
        }
    }
    return true;  // 3350
}
```

### 交叉验证: VALIDATION-GATES.md vs 源码

| VALIDATION-GATES 记录 | 源码行号 | 精确匹配 |
|----------------------|---------|---------|
| 检查1: null waypoint | 3315 | ✅ 完全一致 |
| 检查2: 无建造类型 | 3321-3326 | ✅ 完全一致 |
| 检查3: 无匹配动作 | 3328-3333 | ✅ 完全一致 |
| 检查4: 动作锁定 | 3336-3340 | ✅ 完全一致 |
| 检查5: 动作不可用 | 3342-3346 | ✅ 完全一致 |
| `au2.n` 跳过锁定/可用 | 3335 | ✅ 完全一致 |
| 日志限流 `c.e < 5` | Command.java:572 | ✅ 完全一致 |

**结论: VALIDATION-GATES.md 100%准确，无需修正。**

## 3. 动作查找 — 源码追踪

### 文件: UnitType.java (y.java), line 3125-3142

```java
// 3125: 动作查找方法
public s a(as targetType, int actionType, boolean bl) {
    // 3126: N() → UnitInstance.N() → 返回 dx (动作列表)
    ArrayList actions = this.N();
    s lastMatch = null;

    if (actions.size() > 0) {
        for (s action : actions) {
            as actionTarget = action.y();  // 3131: 动作的目标类型

            // 3132: 包装器动作 → 获取实际目标
            if (bl && (actionTarget = action.E()) != null) {
                actionTarget = actionTarget;
            }

            // 3135: 匹配目标类型 + 可选动作类型
            if (actionTarget != targetType
                || actionType != -1 && actionType != action.t()) {
                continue;
            }

            lastMatch = action;  // 3136

            // 3137: 必须可用 + 通过过滤器
            if (!action.b(this) || !action.a((am)this, false)) {
                continue;
            }
            return action;  // 3138: 找到可用动作!
        }
    }
    return lastMatch;  // 3141: 返回最后一个匹配 (可能不可用)
}
```

### N() — 动作列表来源

```java
// UnitInstance.java:1392
public ArrayList N() {
    return dx;  // dx 是静态/实例字段, 存储该单位类型的所有动作
}
```

每个单位子类覆盖 `N()` 返回其特定的动作列表。例如 Factory 提供建造动作，BuilderUnit 提供 repair/reclaim/build 动作。

## 4. 路径点存储系统

### 文件: UnitType.java (y.java)

```java
private au[] g = O;    // 77: 路径点数组 (O = new au[0])
private int f = 0;     // 75: 当前路径点数

// 3280: 获取新路径点槽位
public au ap() {
    this.m(this.f);     // 扩展数组如果需要
    if (this.g[this.f] == null) {
        this.g[this.f] = new au();  // 3283
    }
    au au2 = this.g[this.f];
    au2.e();            // 清空
    this.f++;           // 计数+1
    return au2;
}

// 3363: 获取当前路径点 (最优先)
public au ar() {
    if (this.f == 0) return null;  // 3364
    return this.g[0];              // 3367
}

// 3370: 获取下一个路径点
public au as() {
    if (this.f <= 1) return null;  // 3371
    return this.g[1];              // 3374
}

// 3377: 获取最后添加的路径点
public au at() {
    if (this.f == 0) return null;  // 3378
    return this.g[this.f - 1];     // 3381
}
```

### RWAgent 影响

`ar()` 返回 `null` → 单位空闲 → 可以分配新任务
`ar()` 返回非null → 单位有当前路径点 → 需要等待或取消

## 5. Command.k() 建造执行路径

### 文件: Command.java, line 460-502 (系统生成)

```java
// 465: systemActionValue == 5 → 系统生成单位
if (this.systemActionValue == 5) {
    // 467: 必须为 BUILD 类型且有目标类型
    if (waypointAction == null
        || waypointAction.d() != av.c    // 不是建造
        || waypointAction.a() == null) {  // 无目标类型
        l.e("system command spawn - failed");
        return;
    }

    // 471-472: 获取建造参数
    int stages = waypointAction.b();
    as unitType = waypointAction.a();

    // 477-478: 创建单位实例
    am unit = unitType.a();     // UnitTypeHandle.a() → new SpecificUnit()
    unit.eo = waypointAction.g();  // X
    unit.ep = waypointAction.h();  // Y

    // 480-484: 设置所有者
    if (playerRef != null) unit.f(playerRef);
    else unit.f(n.i);  // 中立

    // 486-488: 设置建造阶段
    if (stages != 1 && unit instanceof y) {
        ((y)unit).a(stages);
    }

    unit.cP();  // 完成初始化
    // 497: 注册到团队
    n.c(unit);
}
```

## 6. 交叉验证总结

| 之前的理解 | 源码验证 | 状态 |
|-----------|---------|------|
| isValidNewWaypoint 5个检查 | UnitType.java:3314-3351 | ✅ 精确 |
| 动作查找 `a(as,int,boolean)` | UnitType.java:3125-3142 | ✅ 精确 |
| N() 返回动作列表 | UnitInstance.java:1392 → dx | ✅ 精确 |
| ar() 返回当前路径点 | UnitType.java:3363 → g[0] | ✅ 精确 |
| c.e 日志限流 | Command.java:572 | ✅ 精确 |
| waypoint.b = buildType | UnitType.java:3322 | ✅ 精确 |
| waypoint.n = addedByAction | UnitType.java:3335 | ✅ 精确 |
| systemActionValue=5 系统生成 | Command.java:465-502 | ✅ 精确 |
| av.c = BUILD 类型 | UnitType.java:3321 | ✅ 精确 |

**全部 9 项交叉验证通过，无偏差。**
