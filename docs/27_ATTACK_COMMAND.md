# 27 — 定点攻击命令注入 (Command.setAttackTarget)

> 日期: 2026-06-18 | 通过 RWX 源码 + JVM Agent 反射验证

---

## 一、问题背景

此前只实现了 `attack_move`（A+点击, 移动到位置攻击沿途敌人），缺少**定点攻击指定敌方单位**的能力。

---

## 二、RWX 源码确认

### Command.setAttackTarget(BaseUnit)

```java
// RWX Command.java:374-378
/* JADX: renamed from: a */
public void setAttackTarget(BaseUnit baseUnit) {
    this.unitCommand = new UnitCommand();          // 创建 UnitCommand
    this.unitCommand.setAttackTarget(baseUnit);    // 委托给 UnitCommand
}
```

**混淆后**: `Command.a(am)` — 1 个 `am` (BaseUnit) 参数的方法。

### UnitCommand.setAttackTarget(BaseUnit)

```java
// RWX UnitCommand.java:217-222
/* JADX: renamed from: a */
public void setAttackTarget(BaseUnit baseUnit) {
    resetCommand();                           // 重置所有字段为默认值
    this.commandType = UnitCommandType.attack; // av 枚举值 1
    this.targetUnit = baseUnit;               // au.h 字段
}
```

**混淆后字段**:
- `au.a` = commandType (av 枚举)
- `au.h` = targetUnit (am)

### 关键: 动态追踪目标位置

```java
// RWX UnitCommand.java:181-194
/* JADX: renamed from: g */
public float getTargetX() {
    if (isUnitTargetCommand() && this.targetUnit != null) {
        return this.targetUnit.posX;  // ★ 读取目标的实时坐标!
    }
    return this.targetX;
}

/* JADX: renamed from: h */
public float getTargetY() {
    if (isUnitTargetCommand() && this.targetUnit != null) {
        return this.targetUnit.posY;  // ★ 目标移动时会自动追踪
    }
    return this.targetY;
}
```

这意味着攻击单位会**实时追踪移动中的目标**，不需要反复更新坐标。

### isUnitTargetCommand() — 哪些命令类型使用目标单位

```java
/* JADX: renamed from: f */
public boolean isUnitTargetCommand() {
    return this.commandType == UnitCommandType.attack      // 攻击 (1)
        || this.commandType == UnitCommandType.repair       // 维修 (3)
        || this.commandType == UnitCommandType.reclaim      // 回收 (6)
        || this.commandType == UnitCommandType.loadInto     // 装载 (4)
        || this.commandType == UnitCommandType.loadUp       // 装载 (8)
        || this.commandType == UnitCommandType.guard        // 守卫 (10)
        || this.commandType == UnitCommandType.touchTarget  // 触碰 (12)
        || this.commandType == UnitCommandType.follow;      // 跟随 (13)
}
```

### UnitCommandType (av) 全部枚举值

| 值 | 名称 | 目标类型 | 注入状态 |
|----|------|---------|---------|
| 0 | move | 坐标 | ✅ |
| **1** | **attack** | **单位引用 (au.h)** | **✅ v5.4** |
| 2 | build | 坐标+UnitType | ✅ |
| 3 | repair | 单位引用 | ❌ |
| 4 | loadInto | 单位引用 | ❌ |
| 5 | unloadAt | 坐标 | ❌ |
| 6 | reclaim | 单位引用 | ❌ |
| 7 | attackMove | 坐标 | ✅ |
| 8 | loadUp | 单位引用 | ❌ |
| 9 | patrol | 坐标 | ❌ |
| 10 | guard | 单位引用 | ❌ |
| 11 | guardAt | 坐标 | ❌ |
| 12 | touchTarget | 单位引用 | ❌ |
| 13 | follow | 单位引用 | ❌ |
| 14 | triggerAction | ? | ❌ |
| 15 | triggerWhenInRange | ? | ❌ |
| 16 | setPassiveTarget | ? | ❌ |

---

## 三、Command 类全部方法 (运行时发现)

```
c()                              → no-arg
c(am)                            → unit param
c(float,float)                   → alt coordinate
b(am)                            → setRepairTarget
b()                              → no-arg
b(float,float)                   → setAttackMoveTarget
k()                              → executeCommand (危险!)
g()                              → no-arg
d(am)                            → unit param
d()                              → no-arg
f(am)                            → unit param
f()                              → no-arg
j()                              → no-arg
i()                              → no-arg
h()                              → no-arg
a(n,n)                           → team params
a(PointF)                        → PointF
a(a)                             → attack mode
a(c,PointF,am)                   → action+pos+unit
a(c)                             → ActionId
a(float,float)                   → setMoveTarget
a(y)                             → UnitType
a(AbstractList)                  → list
a(k)                             → ?
a(as)                            → UnitType (built-in)
a()                              → no-arg
a(float,float,as,int)            → setBuildTarget
a(am,boolean)                    → setAttackTarget + queued
a(am)                            → ★ setAttackTarget (v5.4)
a(float,float,boolean)           → ?
e()                              → no-arg
e(am)                            → unit param
l()                              → no-arg
```

**注意**: `a(am)` 有**多个重载**！`setAttackTarget(BaseUnit)` 是 1 参数版本，`setAttackTarget(BaseUnit, boolean)` 是 2 参数版本（带 shift-queue）。

---

## 四、反射注入实现

### Step 1: 缓存方法和枚举

```java
// 缓存 av.attack 枚举值
for (Object ev : ucTypeEnum.getEnumConstants()) {
    if (ev.toString().equals("attack")) ucAttackType = ev;
}

// 缓存 Command.a(am) = setAttackTarget
for (Method m : cmdClass.getDeclaredMethods()) {
    Class<?>[] pt = m.getParameterTypes();
    if (pt.length == 1 && pt[0] == unitClass && m.getName().equals("a")) {
        cmdSetAttackTarget = m; break;
    }
}

// 缓存 UnitCommand 字段和方法 (回退用)
ucTargetUnitField = ucClass.getDeclaredField("h");   // au.h
ucCommandTypeField = ucClass.getDeclaredField("a");   // au.a
// ucSetAttackTarget = au.a(am) 方法
```

### Step 2: 查找目标单位

```java
static Object findClosestEnemyUnit(String typeFilter, float refX, float refY) {
    // 遍历 am.bE 全局注册表
    // 过滤: owner > 0 (敌方)
    // 匹配: typeFilter (空=任意, 非空=类型名匹配)
    // 返回: 距离 (refX,refY) 最近的单位
    // 无匹配时回退到任意敌方单位
}
```

### Step 3: 注入攻击指令

```java
// 主方案: Command.a(am) = setAttackTarget
Object targetUnit = findClosestEnemyUnit(targetType, mx, my);
if (targetUnit != null && cmdSetAttackTarget != null) {
    cmdSetAttackTarget.invoke(cmd, targetUnit);  // 一步到位
}

// 回退方案: 直接操作 UnitCommand 字段
Object uc = cmdUC.get(cmd);
if (uc == null) uc = ucClass.getDeclaredConstructor().newInstance();
ucCommandTypeField.set(uc, ucAttackType);       // type = attack
ucTargetUnitField.set(uc, targetUnit);           // target = enemy
```

---

## 五、验证结果

### 成功日志
```
v5.4_attack_..._tgt=mechGun_tgu=true_atkOK
```
- `tgt=mechGun`: 目标类型
- `tgu=true`: 找到目标单位实例
- `atkOK`: Command.setAttackTarget 调用成功

### 多次调用效果
- 第 1 次：`ui=1_n=1` — 1 个选中单位执行攻击
- 第 2 次：`ui=8_n=8` — 游戏自动框选了 8 个单位

---

## 六、关键教训

| # | 发现 | 影响 |
|---|------|------|
| 1 | `Command.a(am)` ≠ `Command.a(float,float)` — 都是 `a` 但参数类型不同 | 必须按参数类型精确匹配 |
| 2 | `getTargetX/Y()` 动态读取 `targetUnit.posX/Y` | 攻击单位会自动追踪移动目标 |
| 3 | 先用 `findClosestEnemyUnit` 根据坐标筛选, 比传 unit_id 更实用 | LLM 只需传坐标+类型名 |
| 4 | UnitCommandType 有 17 个值, 其中 6 个使用单位引用作为目标 | repair/guard/reclaim 等也可复用此模式 |
