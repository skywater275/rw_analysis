# 26 — 工厂生产队列注入 (FactoryQueueManager)

> 日期: 2026-06-18 | 通过 RWX 源码 + JVM Agent 反射验证

---

## 一、问题背景

此前通过 Command 系统注入 `produce` 指令（4 策略：m4/strB/a1/ucT），单位**能创建**但**绕过工厂队列**：
- 单位瞬间出现（无建造时间）
- `prod_queue[]` 始终为空
- `prod_progress` 始终为 0

需要找到直接操作工厂生产队列的方法。

---

## 二、类层次结构 (RWX 源码确认)

```
FactoryQueueInterface            ← 接口: dA(), dw(), dx(), a(Projectile)...
  └── FactoryWithQueue (d.i)     ← 抽象基类, 持有 FactoryQueueManager z
        ├── LandFactory
        ├── AirFactory
        ├── SeaFactory
        ├── CommandCenter
        └── CustomUnit (custom.l)
```

### FactoryQueueManager (d.k)

```java
// com.corrodinggames.rts.game.units.d.k
public class FactoryQueueManager {
    OrderableUnit a;                         // 所属工厂
    public PointF b = null;                  // 集结点
    public final FastArrayList<Projectile> c = new FastArrayList();  // ★ 主队列
    final FastArrayList<Projectile> d = new FastArrayList<>();       // 溢出队列(升级等)
    public float e;                          // 当前建造进度
    Projectile f;                            // ★ 当前建造中的项目
}
```

### Projectile (d.j) — 队列条目

```java
// com.corrodinggames.rts.game.units.d.j
public class Projectile {
    int a;           // count (队列数量)
    float b;         // buildSpeed (建造速度)
    UnitPrice c;     // 主费用
    UnitPrice d;     // 附加费用
    AnimationSet e;  // 建造动画
    boolean f;       // 高优先级
    UnitType g;      // ★ 要建造的单位类型
    PointF h;        // 目标点
    BaseUnit i;      // 目标单位
    ActionId j;      // 动作 ID
    boolean k;       // 取消标记
    boolean l;       // 攻击动作标记
    float m;         // 建造进度 (0.0~1.0, -1=空闲)
    double n;        // 累计已花费资源
}
```

---

## 三、核心方法 (RWX 源码反混淆)

### FactoryQueueManager.b(UnitType) → AbstractUnitAction

```java
/* JADX: renamed from: b */
public AbstractUnitAction b(UnitType unitType) {
    ArrayList arrayListN = this.a.getAvailableActions();  // 工厂的可用动作列表
    for each action in arrayListN:
        if action instanceof PopupQueueAction:
            if action.getUnitType() == unitType:
                return action;   // ★ 返回对应单位类型的 PopupQueueAction
    return null;
}
```

### FactoryQueueManager.a(AbstractUnitAction, boolean, PointF, BaseUnit) → Projectile

```java
/* JADX: renamed from: a */
public Projectile a(AbstractUnitAction action, boolean z, PointF pointF, BaseUnit baseUnit) {
    if (action instanceof PopupQueueAction) {
        PopupQueueAction popup = (PopupQueueAction) action;
        if (!z) {  // z=false = ADD, z=true = CANCEL
            // 检查可建造性、费用
            return a(popup, false, pointF, baseUnit);  // ★ 内部方法创建 Projectile 并加入队列
        }
        // 取消逻辑...
    }
    return null;
}
```

### FactoryQueueManager.a(PopupQueueAction, boolean, PointF, BaseUnit) → Projectile (内部)

```java
Projectile a(PopupQueueAction popup, boolean z, PointF pointF, BaseUnit baseUnit) {
    Projectile projectile = new Projectile();
    projectile.j = popup.getActionId();
    projectile.h = pointF;
    projectile.i = baseUnit;
    projectile.a = 1;
    projectile.b = popup.K();              // buildSpeed
    projectile.c = popup.getDisplayText();  // 费用
    projectile.d = popup.getAdditionalCost();
    projectile.e = popup.getAnimationSet();
    projectile.f = popup.isHighPriority();
    projectile.g = popup.getUnitType();     // ★ 设置要建造的单位类型
    projectile.l = popup.isAttack();
    
    if (!z) {
        PlayerTeam.b(this.a);  // 开始事务
        if (projectile.l) {
            // 攻击动作插入到所有攻击动作之后
            this.c.add(/*i*/ projectile);  // TODO: i 未在上下文中定义, 待RWX源码确认
        } else {
            this.c.add(projectile);  // ★ 加入主队列
        }
        PlayerTeam.c(this.a);  // 结束事务
    } else {
        this.d.add(projectile);  // 加入溢出队列
    }
    return projectile;
}
```

### FactoryQueueManager.a(float f) — 每帧更新

```java
public void a(float f) {
    if (!a()) {  // 队列非空
        Projectile projectile = (Projectile) f().get(0);  // 取队首
        if (this.f != projectile) {
            if (projectile.m < 0.0f) {
                projectile.m = 0.0f;
                ((FactoryQueueInterface) this.a).b(projectile);  // 开始建造回调
            }
            if (this.f != null) {
                this.e = projectile.m;
            }
            a(projectile);  // 设为当前项目
        }
        // ★ 核心: 每帧累加建造进度
        float fCx = projectile.b * this.a.getUnitAIPathfindMemory() * f;
        projectile.m += fCx;
        // 扣费逻辑...
        if (projectile.m >= 1.0f) {
            // ★ 建造完成 → 生成单位
            ((FactoryQueueInterface) this.a).a(projectile);
            this.c.remove(0);  // 移出队列
        }
    }
}
```

---

## 四、反射注入实现

### Step 1: 定位 FactoryQueueManager 类和方法

```java
// 类: com.corrodinggames.rts.game.units.d.k
fqmClass = findClass("com.corrodinggames.rts.game.units.d.k");

// 方法 b(UnitType): 1 参数, 类型含 "units.y" 或 "units.ar" 或 "units.as"
for (Method m : fqmClass.getDeclaredMethods()) {
    Class<?>[] pt = m.getParameterTypes();
    if (pt.length == 1) {
        String pn = pt[0].getName();
        if (pn.contains("game.units.y") || pn.contains("game.units.ar")
            || pn.contains("game.units.as")) {
            fqmFindActionMethod = m; break;
        }
    }
}

// 方法 a(action, boolean, PointF, BaseUnit): 4 参数
for (Method m : fqmClass.getDeclaredMethods()) {
    Class<?>[] pt = m.getParameterTypes();
    if (pt.length == 4 && pt[1] == boolean.class
        && pt[3] == unitClass && m.getReturnType() != void.class) {
        fqmAddToQueueMethod4 = m; break;
    }
}
```

### Step 2: 查找工厂 + 注入生产

```java
// 1. 读取工厂的 z 字段 → FactoryQueueManager
Object fqm = getFactoryQueueManager(factory);  // 层次搜索

// 2. 查找 UnitType 对应的 PopupQueueAction
Object action = fqmFindActionMethod.invoke(fqm, unitType);

// 3. 加入队列: a(action, false, null, null)
fqmAddToQueueMethod4.invoke(fqm, action, false, null, null);
```

### Step 3: 多工厂负载均衡

```java
// 遍历所有玩家工厂, 读取队列长度 (c.size())
// 选择队列最短的工厂注入
Object best = null; int bestQLen = Integer.MAX_VALUE;
for (Object u : allUnits) {
    if (isFactory(u) && owner == 0) {
        int qlen = getQueueSize(u);  // fqm.c.size()
        if (qlen < bestQLen) { bestQLen = qlen; best = u; }
    }
}
```

---

## 五、验证结果

### 成功条件
```
v5.4_produce_..._fac=true_fqm=true_act=true_fqmOK
```
- `fac=true`: 找到工厂
- `fqm=true`: 读取到 FactoryQueueManager
- `act=true`: 找到对应 PopupQueueAction
- `fqmOK`: 成功加入队列

### 失败回退
当 `findUnitType` 失败或 FQM 方法不可用时，回退到 Command 方式的 4 策略。

---

## 六、关键教训

| # | 发现 | 影响 |
|---|------|------|
| 1 | FactoryQueueManager 通过 `z` 字段持有, 需层次搜索 | 不搜索父类会找不到 |
| 2 | `b(UnitType)` 遍历 `getAvailableActions()` 查找 PopupQueueAction | 内置单位 (ar 枚举) 也能找到 |
| 3 | `a(action, false, null, null)` 创建 Projectile 并加入队列 | 游戏引擎自动处理进度和完成 |
| 4 | Command 方式绕过工厂队列, FQM 方式才是正确途径 | produce 必须走 FQM |
| 5 | 多工厂时需选最短队列, 否则总是同一个工厂生产 | 负载均衡 |
