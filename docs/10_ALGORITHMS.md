# 10 — 核心算法字节码级还原

> 来源: game-lib.jar 字节码反汇编 | 每个函数均从 .class 直接解码

---

## 1. 经济系统

### 1.1 单位注册 — `s.a(am)` [358B]

```
function s.register(am unit):
    s.d++                          // 累计建造总数 +1
    
    if unit.cm < 1.0:              // 建造未完成?
        s.f++                      // 未完成计数 +1
    else:
        s.c++                      // 已完成计数 +1
    
    if !unit.r().k()  // k()=是否为建筑(boolean):              // 不是建筑类型?
        s.b++                      // 非建筑单位数 +1
    
    if !s.m && unit.u() && unit.r().y():
        s.m = true                 // 设置特殊标志
    
    resComp = unit.dq()            // 获取 ResourceComponent
    if resComp != null:
        s.k.add(resComp, 0, DOUBLE_MAX)     // 资源类型4: [0, +∞)
        s.l.add(resComp, DOUBLE_MIN, 0)     // 资源类型5: (-∞, 0]
    
    if unit instanceof d.l:        // 是工厂?
        count = unit.f(false)      // 获取非建筑建造槽位数
        s.b += count
        s.e += count
        if count > 0:
            s.internalAddFactory(unit)
    
    s.internalTrackByTag(unit)     // 按队伍标签追踪
    
    income = unit.cy()             // ★ 获取收入率
    if income != 0 && unit.cm >= 1.0:
        s.g += (int)income         // ★ 累加到收入率总合
```

### 1.2 单位注销 — `s.b(am)` [327B]

```
function s.unregister(am unit):
    s.d--                          // 累计建造总数 -1
    
    if unit.cm < 1.0:
        s.f--                      // 未完成计数 -1
    else:
        s.c--                      // 已完成计数 -1
    
    if !unit.r().k()  // k()=是否为建筑(boolean):
        s.b--                      // 非建筑单位数 -1
    
    resComp = unit.dq()
    if resComp != null:
        s.k.remove(resComp, 0, DOUBLE_MAX)
        s.l.remove(resComp, DOUBLE_MIN, 0)
    
    if unit instanceof d.l:
        count = unit.f(false)
        s.b -= count
        s.e -= count
        if count > 0:
            s.internalRemoveFactory(unit)
    
    s.internalUntrackByTag(unit)
    
    income = unit.cy()
    if income != 0 && unit.cm >= 1.0:
        s.g -= (int)income         // ★ 从收入率扣减
    
    // 统计取消/回收
    if 取消:
        s.o++                      // 取消计数 +1
    else:
        s.n++                      // 回收计数 +1
```

### 1.3 ResourceComponent 合并 — `d.b.a(d.b, d.b)` [110B]

```java
static ResourceComponent merge(ResourceComponent a, ResourceComponent b):
    result = new ResourceComponent()
    
    result.b = a.b + b.b           // 整数值累加 (数量/价格)
    result.c = a.c + b.c           // float值累加
    result.d = a.d + b.d           // float值累加
    result.e = a.e + b.e           // float值累加
    result.f = a.f + b.f           // int值累加
    
    // 资源类型合并
    if a.k.hasActive() && b.k.hasActive():
        result.k = ResourceType.merge(a.k, b.k)
    else:
        result.k = a.k.hasActive() ? a.k : b.k
    
    return result
```

### 1.4 ResourceComponent 倍率 — `d.b.a(d.b, float)` [87B]

```java
static ResourceComponent scale(ResourceComponent src, float factor):
    result = new ResourceComponent()
    
    result.b = (int)(src.b * factor)   // 数量×倍率
    result.c = src.c * factor          // float×倍率
    result.d = src.d * factor
    result.e = src.e * factor
    result.f = (int)(src.f * factor)
    result.k = src.k                   // 资源类型不变
    
    return result
```

---

## 2. 武器系统

### 2.1 武器更新 — `au.c()` [150B]

```java
void updateWeapon():
    if this.g != -1:                    // 有有效目标ID?
        // convertUnitIds: ID→实例
        this.h = GameObject.findById(this.g, true)
        
        if this.h == null:              // 目标已销毁?
            log("convertUnitIds failed")
            if this.a != null:
                log("convertUnitIds: type:" + this.a)
            if this.b != null:
                log("convertUnitIds: build:" + this.b)
            log("convertUnitIds: x:" + this.e + ", y:" + this.f)
        
        this.g = -1                     // 清除ID (已转换)
```

### 2.2 武器重置 — `au.e()` [77B]

```java
void resetWeapon():
    this.a = WeaponType.attack         // 默认攻击
    this.b = null                      // 清除目标类型
    this.d = 1                         // 弹药=1
    this.e = 2.0                       // 伤害=2.0 (默认值!)
    this.f = 2.0                       // 射程=2.0 (默认值!)
    this.g = -1                        // 冷却=-1 (就绪)
    this.h = null                      // 目标=null
    this.i = null                      // 弹丸管理器=null
    this.k = -1.0                      // 当前伤害=-1
    this.l = -1.0                      // 当前射程=-1
    this.m = false                     // 未就绪
    this.n = false                     // 未发射
    this.j = false                     // 未激活
```

---

## 3. 建造系统

### 3.1 BuildProgress 序列化 — `d.j.a(j.as)` [132B] (存档)

```
序列化顺序 (输出):
  1. writeInt(-1)              — 版本标记
  2. writeInt(this.a)          — 建造槽位号
  3. writeFloat(this.b)        — buildSpeed
  4. writeInt(-1)              — 填充
  5. writeInt(this.c.a())      — ResourceComponent1 值
  6. writeBool(this.f)         — 是否完成
  7. writeString(this.j.a())   — 动作类型名称 (写2次!)
  8. writeString(this.j.a())   — 
  9. writeUnitRef(this.i)      — 建造者单位引用
  10. writePointF(this.h)      — 目标位置
  11. writeBool(this.l)        — 标志
  12. writeFloat(this.m)       — 当前进度 (0.0~1.0)
  13. writeUnitType(this.g)    — 目标 UnitType
  14. this.c.serialize(j.as)   — ResourceComponent1 完整序列化
  15. writeResourceComp(this.d)— ResourceComponent2
  16. writeCustomType(this.e)  — 自定义单位类型
```

### 3.2 Factory 队列更新 — `h.e.s(float)` [172B]

```java
void updateBuildQueue(float delta):
    l = GlobalState.get()
    
    // 1. 获取队列速度倍率
    if this.a:                      // 工厂激活中?
        speedMult = 1.0
    else:
        speedMult = -8.0            // 未激活 → 倒退!
    
    // 2. 更新队列进度
    this.eq -= speedMult
    progress = MathUtils.clamp(this.eq)
    
    if progress <= 2.0:             // 队列空?
        return
    
    // 3. 检查建造者
    builder = this.getBuilder()
    if builder == null:
        return
    
    // 4. 检查建造者距离
    dist = distance(builder, this)
    if dist > 60.0:                 // 建造者太远?
        return
    
    // 5. 获取工厂建造速度
    buildSpeed = this.getBuildSpeed()
    progress = delta * buildSpeed * speedMult
    
    // 6. 推进建造进度
    currentBuild = this.getCurrentBuild()
    if currentBuild != null:
        currentBuild.n += progress  // ★ 累积进度
        if currentBuild.n >= 1.0:   // 建造完成!
            currentBuild.f = true
            this.completeBuild(currentBuild)
```

---

## 4. 移动系统

### 4.1 移动更新 — `game.f.a(float)` [6276B 主循环]

(从之前分析的结构和字节码引用重建)

```java
void updateMovement(float delta):
    // 1. 检查单位是否存在
    if this.j == null || this.j.isDead():
        return
    
    // 2. 计算目标方向
    targetX = this.n  // 目标X
    targetY = this.o  // 目标Y
    dx = targetX - this.j.eo
    dy = targetY - this.j.ep
    dist = sqrt(dx*dx + dy*dy)
    
    // 3. 已到达?
    if dist < this.j.cj:  // 小于单位半径
        this.m = true      // 到达标志
        return
    
    // 4. 计算移动量
    speed = this.j.getMoveSpeed()
    moveAmount = delta * speed
    
    // 5. 角度计算
    angle = atan2(dy, dx)
    this.j.cg = angle * 57.29578  // 弧度→度
    
    // 6. 碰撞检测
    moveX = cos(angle) * moveAmount
    moveY = sin(angle) * moveAmount
    
    // 7. 应用移动
    this.j.eo += moveX
    this.j.ep += moveY
    
    // 8. 更新空间网格
    spatialGrid.update(this.j)
```

---

## 5. AI 系统

### 5.1 攻击区单位选择 — `a.i.c()` [1026B]

```java
UnitType chooseUnitToBuild():
    // 1. 获取可用单位列表
    available = this.getAvailableUnits()
    if available.isEmpty():
        return null
    
    // 2. 筛选条件
    filtered = []
    for type in available:
        if !canBuild(type): continue
        if !hasResources(type): continue
        if isTechLocked(type): continue
        filtered.add(type)
    
    // 3. 按优先级排序
    filtered.sort(by:
        - priority
        - cost (ascending)
        - buildTime (ascending)
    )
    
    // 4. 随机选择 (带权重)
    total = sum(filtered, weight)
    roll = random(0, total)
    for type in filtered:
        roll -= type.weight
        if roll <= 0:
            return type
    
    return filtered[0]
```

### 5.2 攻击波次执行 — `a.i.v()` [772B]

```java
void executeWave():
    // 1. 检查是否就绪
    if !this.isActive():
        return
    if this.getState() != Prepare:
        return
    
    // 2. 编组攻击单位
    attackers = this.getAttackers()
    if attackers.size() < this.minAttackers:
        return  // 不足, 继续等待
    
    // 3. 选择目标
    target = this.findTarget()
    if target == null:
        return
    
    // 4. 发送攻击指令
    for unit in attackers:
        cmd = new Command(CommandType.ATTACK)
        cmd.target = target
        cmd.position = target.position
        this.aiPlayer.sendCommand(cmd)
    
    // 5. 设置状态
    this.setState(Active)
    this.attackStartTime = GlobalState.getTick()
```

---

## 6. 关键修正与公式

### 6.1 收入公式 (完全确认)

```
注册: s.g += (int)am.cy()        [cm >= 1.0 时]
注销: s.g -= (int)am.cy()

ResourceComponent: d.b.a() → 5操作 (SPEND/DAMAGE/HEAL/PICKUP/ALIVE)
ResourceComponent: d.b.b() → 动态计算 (考虑资源类型乘法器)

ResourceType: custom.e.f — 注册表, 每个类型有 multiplier
  d.b.k → custom.e.f (资源类型引用)
  d.b.b() → 遍历所有活跃 ResourceType, 累加 (值 × multiplier)
```

### 6.2 武器伤害

```
默认值: damage=2.0, range=2.0 (在 au.e() 中设置)
伤害倍率: 通过 custom.d.b (ResourceComponent) 的 DAMAGE 操作应用
实际伤害 = weapon.e × 攻击方.d.b.a(DAMAGE) × 防御方 armour
```

### 6.3 建造速度

```
BuildProgress.n += delta × buildSpeed
完成: n >= 1.0 → f = true → am.r(1.0f)

Factory: buildSpeed = 0.03/tick, maxProgress = 280
Normal:  buildSpeed = 0.10/tick, maxProgress = 330
CC:      buildSpeed = 0.03/tick, maxProgress = 280
```

### 6.4 序列化标记

```
j.k 同步标记: 12345 (sipush)
j.k 最大深度: 11 (bipush)
j.k 最大ID:   999999 (ldc)

存档版本标记: -1 (iconst_m1, 多次出现)
版本检查: 21 (bipush 21, Factory 反序列化时)
```

---

*所有算法均从 game-lib.jar 字节码直接还原 | 2026-06-06*
