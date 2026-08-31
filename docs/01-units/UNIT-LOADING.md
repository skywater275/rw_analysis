# Rusted Warfare v1.15 — 单位加载系统源码逆向分析
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 来源: `game-lib.jar` CFR 0.152 反编译
>
> 关键文件: `UnitRegistry.java`, `UnitType.java` (UnitType), `Factory.java` (930行), `CustomUnitType.java` (4699行), `ModUnitRegistry.java`

---

## 1. 单位系统总览

### 1.1 类层次结构

```
UnitRegistry (ar, Enum)
├── 53个枚举常量 (a~ZZ, 每个是一个内置单位类型)
└── 实现 UnitTypeHandle (as) 接口

UnitType (y, abstract)
├── 单位类型的核心属性定义
├── HP, 速度, 武器, 价格, 建造时间等
└── 被 UnitRegistry 枚举常量子类化

UnitTypeHandle (as, interface)
├── a() → am    创建单位实例
├── e() → String 获取显示名称
├── f() → String 获取描述
├── c() → int   价格
├── g() → int   建造阶段数
└── d() → z     单位配置数据

UnitInstance (am)
├── 每个游戏中的单位是一个 UnitInstance
├── dz → UnitTypeHandle (as) 类型引用
└── 运行时状态: HP, 位置, 状态等

自定义单位系统:
├── CustomUnitType (custom.j, 4699行)
│   ├── 继承 w (UnitType的变体)
│   └── 从 .ini 文件解析
├── UnitConfig (custom.h)
│   └── 单个单位的配置容器
├── ModUnitRegistry (custom.l)
│   ├── 管理所有 mod 单位
│   ├── 从 zip 中的 .ini 加载
│   └── 按路径/名称查找单位类型
└── 组件系统 (custom.d/, custom.e/)
    ├── ResourceComponent (custom.d.b)
    ├── 武器组件, 移动组件, 等等
    └── e.f = 数据组件
```

### 1.2 关键包结构

| 包路径 | 内容 |
|--------|------|
| `game.units` | 核心单位系统 (UnitInstance, UnitType, Factory, etc.) |
| `game.units.a` | 行为/动作类 |
| `game.units.b` | 单位子类集合 |
| `game.units.d` | 地面/实验单位 (BuilderUnit, CommandCenter, Carrier, etc.) |
| `game.units.e` | 建筑类 (Building) |
| `game.units.f` | 空间网格 (SpatialGrid) |
| `game.units.custom` | Mod/Custom 单位系统 |
| `game.units.custom.d` | 组件定义 (Resource, Weapon, Movement, etc.) |
| `game.units.custom.e` | 数据组件 (Income, Resource, etc.) |
| `game.units.custom.logicBooleans` | 逻辑布尔表达式引擎 |

---

## 2. UnitRegistry (ar) — 内置单位注册表

### 2.1 53个内置单位类型

```java
public abstract class UnitRegistry extends Enum implements UnitTypeHandle {
    // 枚举常量 = 每种内置单位一个实例
    public static final ar a  = new ar$1();   // 单位 #1
    public static final ar b  = new ar$12();  // builderType
    public static final ar c  = new ar$23();  // turretType
    public static final ar d  = new ar$34();  // factoryType
    public static final ar e  = new ar$45();
    public static final ar f  = new ar$50();  // artilleryType
    public static final ar g  = new ar$51();  // aaType
    public static final ar h  = new ar$52();
    public static final ar i  = new ar$53();
    public static final ar j  = new ar$2();
    public static final ar k  = new ar$3();
    // ... 共 53 个 (a ~ ZZ)
    public static final ar Y  = new ar$47();  // 空类型/默认
    public static final ar Z  = new ar$48();
}
```

### 2.2 已知单位类型映射

| 枚举常量 | 内部类 | 含义 (来自内存文件) |
|----------|--------|-------------------|
| `b` | ar$12 | **builderType** — 建造者 |
| `c` | ar$23 | **turretType** — 炮塔 |
| `d` | ar$34 | **factoryType** — 工厂 |
| `f` | ar$50 | **artilleryType** — 火炮 |
| `g` | ar$51 | **aaType** — 防空炮塔 |
| `y` | ar$18 | **scoutType** — 侦察兵 |
| `B` | ar$21 | **heavyTankType** — 重型坦克 |
| `D` | ar$24 | **antiNukeType** — 反核 |
| `G` | ar$27 | **superWeaponType** — 超级武器 |
| `J` | ar$30 | **economicBuildings** — 经济建筑群 |

### 2.3 关键静态字段

| 字段 | 类型 | 含义 |
|------|------|------|
| `ae` | ArrayList | **allUnitTypes** — 所有单位类型列表 |
| `af` | at[] | 单位变体数组 |
| `ag` | boolean | 是否已初始化 |
| `ah` | b | 单位资源数据 |

### 2.4 单位查找方法

```java
// 按名称查找单位类型 (支持模糊匹配)
ar.a(String name, boolean fuzzy)
  → 遍历 ae, 匹配 name()
  → 空名称 → 返回 Y (默认空类型)

// 获取单位显示名称 (从翻译文件)
e() → String
  → 查找 key: "units.<name>.name"
  → 回退到 name()

// 获取单位描述
f() → String
  → 查找 key: "units.<name>.description"
```

---

## 3. UnitType (y) — 单位类型属性定义

### 3.1 核心字段

| 字段 | 类型 | 含义 |
|------|------|------|
| `S` | float | 尺寸X (宽度) |
| `T` | float | 尺寸Y (高度) |
| `U` | float | 半径 (碰撞/选择) |
| `V` | float | 最大HP |
| `W` | float | 速度 |
| `X` | float | 转向速度 |
| `Y` | float | 炮塔转向速度 |
| `ab` | float | **收入累积器** (incomeAccumulator) |
| `ac` | int | 建造阶段数 |
| `g` | au[] | **武器动作数组** (WeaponAction[]) |
| `P` | a | 当前行为 (idle/moving/attacking/...) |
| `R` | am | 单位实例引用 |
| `Z` | am | 目标单位 |
| `aa` | int | 目标计数 |

### 3.2 单位属性 (私有字段)

| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | int | 内部ID |
| `b` | float | 碰撞半径 |
| `c` | float | 视野范围 |
| `d` | float | 高度层级 |
| `e` | float | 重量/质量 |
| `f` | int | 标志位 |
| `h` | boolean | 是否可移动 |
| `i` | int | 移动类型标志 |
| `l` | float | 加速度 |
| `m` | float | 减速度 |
| `n` | int | 科技等级 |
| `o` | float | 建造速度 |
| `p` | float | 维修速度 |
| `q` | byte | 装甲类型 |
| `r` | int | 造价 |
| `s` | float | 能量消耗 |
| `t` | boolean | 是否可运输 |

### 3.3 关键方法

| 方法 | 含义 |
|------|------|
| `a(float, au, ad)` | 每帧累积收入 |
| `cy()` | 获取收入贡献值 |
| `r()` → as | 获取 UnitTypeHandle |
| `i()` → String | 获取内部名称 |
| `be()` → d | 获取单位类别 |
| `dd()` → boolean | 是否为建筑 |
| `bI()` → boolean | 是否为实验单位 |
| `u()` → boolean | 是否为运输单位 |
| `aj()` → boolean | 是否空闲 |
| `aq()` → boolean | AI 可控制 |
| `aS()` → boolean | AI 可招募 |
| `c(am)` → boolean | 是否可见 |
| `h(am)` → boolean | 是否可攻击目标 |
| `x()` → float | HP 比率 |
| `ch()` → void | 检查死亡 |
| `ab()` → am | 获取当前目标 |

---

## 4. Factory (h) — 工厂单位 (930行)

### 4.1 工厂字段

| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | PointF[6] | **6个建造槽位** |
| `b` | PointF[6] | 槽位工作位置 |
| `c` | boolean | 是否激活 |
| `r` | String | 工厂名称 |
| `D` | ArrayList | 建造队列 |
| `E` | i.b | Mod 图标引用 |
| `F` | o | 当前生产目标 |
| `G` | n | 所有者玩家 |
| `H` | String | 自定义标签 |
| `I` | boolean | 是否被占领 |
| `J` | String | 建造者限定 |

### 4.2 工厂静态资源 (s)

```java
static s h;   // 建造图标
static s i;   // 生产进度条
static s j;   // 队列显示
static s k;   // 等等...
// ... 约26个静态资源字段 (h~C)
```

### 4.3 工厂关键方法

| 方法 | 含义 |
|------|------|
| `a(float dt)` | **主更新** — 处理建造进度 |
| `a(am, int slot)` | 将单位放入指定槽位 |
| `y()` → int | 获取最大槽位数 (850000) |
| `m()` → float | 获取建造速度 (30.0f) |
| `b(am)` → float | 获取到槽位的距离 (1.0E7f = 极远) |
| `c(am)` → float | 获取装载距离 (1.0E7f) |
| `z()` → float | 能量消耗 (0.0f) |
| `A()` → float | 碰撞高度 (9.8f) |
| `B()` → float | 选择高度 (9.35f) |
| `l()` → boolean | 是否有空槽位 |
| `u()` → boolean | 是否可以装载 |
| `d(am)` → boolean | 检查单位类型是否可建造 |
| `f()` → ar | 返回自己的 UnitRegistry 类型 (Y) |

### 4.4 建造流程

```
1. 玩家选择单位类型 → Command 发送到工厂
2. Factory.a(s, boolean, PointF, am) — 处理建造指令
   ├── 检查价格 (canAfford)
   ├── 检查空槽位 (6个)
   ├── 检查科技要求
   └── 创建建造任务 → 添加到队列
3. Factory.a(float dt) — 每帧更新
   ├── 累加建造进度 (建造者速度 × dt)
   ├── cm ≥ 1.0 → 构造完成
   ├── 创建 UnitInstance
   └── 从槽位移出
4. s.a(am) — 注册到 TeamUnitTracker (收入+)
```

---

## 5. CustomUnitType (custom.j) — 自定义单位 (4699行)

### 5.1 继承链

```
w (UnitType 的变体, extends UnitType?)
└── CustomUnitType (j)
    ├── implements ak, d, d.l
    └── 从 .ini 文件加载所有属性
```

### 5.2 核心字段

| 字段 | 类型 | 含义 |
|------|------|------|
| `b` | custom.e | 数据组件集 |
| `c` | float | 缩放 (1.0) |
| `d` | float | 碰撞半径 |
| `e` | float | 选择半径 |
| `f` | float | 质量 |
| `g` | boolean | 可移动 |
| `h` | boolean | 可攻击 |
| `i` | boolean | 可转身 |
| `j` | float | 转向速度 |
| `k` | boolean | 是否浮空 |
| `l` | boolean | 是否可推动 |
| `p` | boolean | 是否可选中 |
| `x` | l (ModUnitRegistry) | 所属 Mod |
| `y` | as | 武器目标类型 |
| `A` | at[] | 武器数组 |
| `E` | PointF[] | 建造槽位 |
| `F` | PointF[] | 槽位工作位置 |
| `G` | f[] | 移动控制器数组 |

### 5.3 单位类别路径

CustomUnitType 通过继承链支持多种单位类别:
```
CustomUnitType
├── ground units      (地面单位)
├── water units       (水面单位)
├── air units         (空中单位)
├── hover units       (悬浮单位)
├── buildings         (建筑)
│   ├── factories     (工厂)
│   ├── turrets       (炮塔)
│   └── extractors    (提取器)
├── experimental      (实验单位)
└── custom behaviors  (自定义行为, 通过 logicBooleans)
```

---

## 6. ModUnitRegistry (custom.l) — Mod 单位注册

### 6.1 核心静态字段

| 字段 | 类型 | 含义 |
|------|------|------|
| `b` | l | **单例** — 全局 ModUnitRegistry |
| `c` | ArrayList | 所有已注册单位类型列表 |
| `d` | ArrayList | 待注册列表 |
| `e` | ArrayList | 额外列表 |
| `f` | HashMap | **名称→单位类型 映射** |
| `g` | ArrayList | 全局排序列表 |

### 6.2 单位查找

```java
// 按名称查找
static s(String name) → as
  → l.a(name, true)

// 按名称+模糊查找
static a(String name, boolean fuzzy) → as
  → 先查 UnitRegistry.ar (内置单位)
  → 再查 ModUnitRegistry
  → 返回 UnitTypeHandle (as)
```

### 6.3 Mod 加载流程

```
1. 游戏启动 → 扫描 mods/ 目录
   ├── 遍历 .zip 文件
   └── 读取 mod-info.txt

2. 对每个 mod:
   ├── 解析 .ini 文件 (单位定义)
   │   ├── [unit] 段 → 基本属性
   │   ├── [weapon] 段 → 武器定义
   │   ├── [action] 段 → 动作定义
   │   └── [logic] 段 → 逻辑条件
   ├── 创建 CustomUnitType 实例
   ├── 注册到 ModUnitRegistry
   └── 加载纹理/模型资源

3. 版本检查:
   └── a(String name, int minVer, String modId, String feature)
       └── 检查 minVersion 是否满足
```

---

## 7. 组件系统 (Component System)

### 7.1 组件包结构

```
game.units.custom.d/
├── b (ResourceComponent)     — 资源/收入组件
├── (WeaponComponent)         — 武器组件
├── (MovementComponent)       — 移动组件
└── (其他组件)

game.units.custom.e/
├── f                         — 数据组件 (DataComponent)
├── a                         — 动作数据
└── (其他数据组件)
```

### 7.2 ResourceComponent (custom.d.b)

| 字段 | 类型 | 含义 |
|------|------|------|
| — | float | 资源产量 |
| — | float | 资源消耗 |
| — | int | 资源类型 (1/2/3) |

### 7.3 逻辑条件引擎 (logicBooleans)

```
LogicBoolean
├── VariableScope  — 变量作用域 (self, target, etc.)
│   ├── VariableMapping
│   └── CachedWriter
└── 布尔表达式树 (AND/OR/NOT/比较)
    例: "if not self.isOverLiquid() and not self.isMoving()"
```

---

## 8. 单位生命周期

### 8.1 创建流程

```
1. 单位类型解析 (启动时, 一次性)
   ├── 内置: UnitRegistry 53个枚举常量初始化
   └── Mod: ModUnitRegistry 从 .ini 文件加载

2. 单位实例创建 (运行时)
   ├── 玩家下达建造指令 → Command 发送到工厂
   ├── Factory 处理建造
   │   ├── 检查资源/槽位/科技
   │   │
   │   └── 建造进度达到100%:
   │       ├── UnitTypeHandle.a() → 创建 UnitInstance
   │       │   ├── new UnitInstance()
   │       │   ├── 设置 dz = this (UnitTypeHandle)
   │       │   ├── 设置 HP = maxHp
   │       │   ├── 设置位置 = 工厂槽位
   │       │   └── 设置 owningPlayer
   │       │
   │       └── 后处理:
   │           ├── s.a(am) → 注册到 TeamUnitTracker
   │           ├── 触发 AI 行为 (GameWorld.d/e)
   │           └── 添加到空间网格
   │
   └── 单位从工厂移出 → 准备就绪
```

### 8.2 死亡流程

```
1. am.ch() — 检查 HP ≤ 0
2. am.bv() — 死亡序列入口
3. am.bu() — 8步死亡清理:
   ├── 设置 bV = true (isDead)
   ├── 记录死亡时间戳
   ├── 释放占用 (从工厂槽位/运输)
   ├── 通知 AI (从区域/单位组移除)
   ├── 通知统计 (bg.a → StatsManager)
   ├── 创建爆炸效果/残骸
   ├── s.b(am) → 从 TeamUnitTracker 注销
   └── 添加到回收队列 (bU → Recycler)
```

---

## 9. 武器系统关联

### 9.1 WeaponAction (au)

| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | av (WeaponTypeEnum) | 武器类型 (17种) |
| `b` | as (UnitTypeHandle) | 目标单位类型 |
| `c` | a.c | 动作类型 |
| `d` | int | 弹药数 |
| `e` | float | 伤害值 |
| `f` | float | 射程 |
| `g` | long | 冷却计时器 |
| `h` | am (UnitInstance) | 目标单位 |
| `i` | ab (ProjectileManager) | 弹丸管理器 |
| `j` | boolean | 是否激活 |
| `k` | float | 当前伤害 |
| `l` | float | 当前射程 |
| `m` | boolean | 是否就绪 |
| `n` | boolean | 是否已发射 |

### 9.2 WeaponTypeEnum (av) — 17种武器

| 值 | 含义 | 示例 |
|----|------|------|
| `a` | directFire | 直射 (坦克炮) |
| `b` | indirectFire | 曲射 (火炮) |
| `c` | melee | 近战 |
| `d` | laser | 激光 |
| `e` | missile | 导弹 |
| `f` | torpedo | 鱼雷 |
| `g` | flamethrower | 火焰 |
| `h` | electricBolt | 闪电 |
| `i` | artillery | 炮击 |
| `j` | bomb | 炸弹 |
| `k` | nuke | 核弹 |
| `l` | shield | 护盾 |
| `m` | repair | 维修 |
| `n` | build | 建造 |
| `o` | reclaim | 回收 |
| `p` | capture | 占领 |
| `q` | customWeapon | 自定义 |

---

## 10. 关键常量

| 常量 | 值 | 来源 |
|------|-----|------|
| 内置单位类型数 | 53 | UnitRegistry 枚举 |
| 工厂槽位数 | 6 | Factory.a[6] |
| 工厂最大槽位 | 850000 | Factory.y() |
| 工厂建造速度 | 30.0f | Factory.m() |
| CC 收入 | 18.0f/s | CommandCenter.cy() |
| 默认起始资金 | 4000.0 | PlayerState |
| 建造伤害惩罚 | 1.75× | UnitInstance.a() |
| 低HP阈值 | 33% | UnitInstance |
| 回收退款率 | 80% | UnitType.i() |
| 空间网格 | 32×32 cell, 50px/cell | SpatialGrid |

---

## 11. ini 单位定义格式 (推断)

```ini
[unit]
name=MyUnit
displayName=units.myUnit.name
description=units.myUnit.description
price=500
maxHp=300
speed=1.5
radius=15
mass=100
techLevel=1

[weapon]
type=directFire
damage=50
range=200
cooldown=60
targetType=ground

[action]
type=move
speed=1.5

[logic]
condition=if not self.isOverLiquid()
```

*来源: game-lib.jar CFR 0.152 反编译, 2026-06-22*
