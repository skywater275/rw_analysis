# Rusted Warfare v1.15 — AI 系统源码逆向分析
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 来源: `game-lib.jar` CFR 0.152 反编译 + 字节码验证
> 
> 关键文件: `GameWorld.java` (1909行), `BaseZone(i)`, `UnitGroup(g)`, `AIUnitGroupBase(h)`, `AIStrategyNode(o)`

---

## 1. AI 系统总览

### 1.1 核心架构

```
GameWorld (game.a.a, 1909行)
├── 三层时钟系统 (每帧累加 dt, 到阈值触发)
│   ├── Tier1: aP += dt → >0.25s 触发 (资源/建筑循环)
│   ├── Tier2: aL += dt → >2.0s 触发 (AI逻辑/单位组)
│   └── Tier3: aN += dt → >4.5s 触发 (核心逻辑/区域)
│
├── 区域系统 (Zone System)
│   ├── AIStrategyNode (o) — 抽象基类
│   ├── BaseZone (i) — 核心区域 (建造/防御/扩展)
│   ├── PlainZone (m) — 简单区域
│   ├── RallyGroup (l) — 集结组 (区域子类)
│   └── TransporterGroup (n) — 运输组
│
├── 单位组系统 (UnitGroup System)
│   ├── AIUnitGroupBase (h) — 抽象单位组基类
│   └── UnitGroup (g) — 战斗集结组 (攻击/防御/巡逻/撤退)
│
├── 建造系统 (Build System)
│   ├── UnitBuildStrategy (d) — 建造策略
│   ├── BuildPreferenceCache (c) — 建造偏好缓存
│   └── AIUnitActionUtils (f) — AI单位动作工具
│
└── 寻路/连通性
    ├── bh: 陆路连通
    ├── bi: 水路连通
    ├── bj: 港口连通
    └── bk: 空中连通
```

### 1.2 类继承链

```
n (PlayerState/GameState 基类)
└── GameWorld (a.a)    ← AI 主控制器, 每个AI玩家一个实例

o (AIStrategyNode)     ← 抽象策略节点基类
├── i (BaseZone)        ← 核心区域
├── m (PlainZone)       ← 简单区域
├── h (AIUnitGroupBase) ← 单位组基类
│   ├── g (UnitGroup)   ← 战斗集结组
│   ├── l (RallyGroup)  ← 集结组
│   └── n (TransporterGroup) ← 运输组
└── d (UnitBuildStrategy) ← 建造策略
```

---

## 2. 三层时钟系统 (Three-Tier Clock)

### 2.1 字段定义 (GameWorld)

| 字段 | 类型 | 含义 | 阈值 |
|------|------|------|------|
| `aP` | float | Tier1 累积器 (资源/收入) | 0.25s (25.0f ticks) |
| `aL` | float | Tier2 累积器 (AI逻辑) | 2.0s |
| `aN` | float | Tier3 累积器 (核心逻辑) | 4.5s |
| `aQ` | float | 信用累积器 | — |

### 2.2 触发流程

```
GameWorld.a(float dt)           ← 主更新入口 (每帧调用)
│
├── dt 累加到 aP, aQ
│
├── if aP > 25.0f (0.25s):
│   ├── 遍历所有区域 bm
│   │   ├── i (BaseZone): i.b(dt)  — 资源更新
│   │   │   ├── 统计区域内单位 (非建筑/建筑/工厂/提取器/建造者)
│   │   │   ├── 计算资源分数 (a)
│   │   │   ├── 计算防御分数 (e)
│   │   │   └── 计算回收分数 (d)
│   │   └── g (UnitGroup): g.c(dt) — 单位组状态机
│   │       ├── 攻击移动/巡逻/防御
│   │       ├── 撤退判定 (低HP)
│   │       └── 补充招募
│   └── aP -= 25.0f
│
├── if aL > XXX (Tier2, ~2s):   ← l(float dt)
│   ├── 遍历所有我方单位
│   │   ├── 分配单位到区域 (aC = zone)
│   │   ├── 检查路径连通性
│   │   └── 更新单位行为状态
│   └── aL 重置
│
└── if aN > XXX (Tier3, ~4.5s):  ← m(float dt), n(float dt)
    ├── m(): 核心AI逻辑
    │   ├── 更新所有区域 (b() 方法)
    │   ├── 创建新区域 (Tier3 计时器 aT)
    │   │   ├── Main区域: 从起始位置扩展, 半径 360
    │   │   └── Forward区域: 从工厂前方扩展, 半径 310
    │   ├── 统计攻击/防御单位
    │   └── 派遣建造者 (builder dispatch)
    │
    └── n(): 建造者派遣 + 闲置单位处理
        ├── 更新 AI 行为 (bJ 列表)
        ├── 更新单位组
        ├── 派遣闲置建造者到区域
        └── 处理滞留单位
```

---

## 3. 区域系统 (Zone System)

### 3.1 BaseZone (i) — 核心区域字段

| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | float | 资源分数 (resourceScore) |
| `b` | j (enum) | 区域类型 (Main/Resource/Forward) |
| `c` | k (enum) | 区域阶段 (Pre/Active/Forward) |
| `d` | float | 回收分数 (reclaimScore) |
| `e` | float | 防御分数 (defensiveScore) |
| `f` | float | 上次最佳建筑优先级 |
| `g` | float | 更新计时器 |
| `h` | int | 区域ID |
| `i` | float | 搜索半径X偏移 |
| `j` | float | 搜索半径Y偏移 |
| `S` | float | 中心X (继承自 o) |
| `T` | float | 中心Y (继承自 o) |
| `U` | float | 半径 (继承自 o) |
| `s` | boolean | 是否受到攻击 |
| `G` | int | 提取器数量 |
| `I` | int | 工厂数量 |
| `J` | int | 战斗单位数量 |
| `K` | int | 闲置建造者数量 |
| `Q` | int | 策略ID |
| `R` | a (GameWorld) | AI控制器引用 |

### 3.2 区域类型 (BaseZoneType, j 枚举)

| 枚举值 | 含义 | 说明 |
|--------|------|------|
| `a` | **Main** | 主基地区域 (从指挥中心扩展) |
| `b` | **ResourceOutpost** | 资源前哨 (资源点附近) |
| `c` | **ForwardOutpost** | 前线前哨 (工厂前方) |

### 3.3 区域阶段 (BaseZoneStage, k 枚举)

| 枚举值 | 含义 | 说明 |
|--------|------|------|
| `a` | **Pre** | 预创建 (刚初始化) |
| `b` | **Active** | 活跃 (正在建造/扩展) |
| `c` | **Forward** | 前线 (工厂前方扩展) |

### 3.4 区域生命周期

```
1. 创建
   ├── n() 方法中检测: 无 Main 区域 → 从指挥中心创建 (半径420, Main/Pre)
   ├── n() 方法中检测: 建造者单位 → 创建 (半径420, ForwardOutpost/Pre)
   ├── m() 方法中检测: aT 计时器到期 + 有工厂且 >2 Main → 创建 Forward 区域
   └── m() 方法中检测: aU 计时器到期 + 有工厂 → 创建 Forward 区域

2. 激活
   ├── i.d(float dt) — zone 更新循环
   │   ├── 资源评估 (提取器数量, 矿点距离)
   │   ├── 防御评估 (敌人位置, 炮塔数量)
   │   └── 建造决策 (选择最佳建筑类型)
   └── i.y() — 寻找闲置建造者

3. 建造执行
   ├── i.g(UnitType) — 执行建造
   │   ├── 检查价格 (canAfford)
   │   ├── 寻找建造者 (findBuilder, 20%随机)
   │   ├── 寻找建造位置 (findBuildLocation)
   │   │   ├── 网格搜索: 12环 × 20-53角度
   │   │   ├── 密度检查: 300单位内 ≥2 建筑 → 跳过
   │   │   └── 从外环向内搜索
   │   └── 创建 Command → 发送给建造者
   └── 建造成功后添加到区域记录

4. 清理
   ├── 区域受到攻击 (s=true) → 标记为不安全
   ├── 区域被摧毁区覆盖 → 移除
   └── 区域无建筑且无建造者 → 可能被移除
```

---

## 4. 单位组系统 (UnitGroup System)

### 4.1 AIUnitGroupBase (h) — 抽象基类

| 字段 | 类型 | 含义 |
|------|------|------|
| `F` | ArrayList | 单位列表 (units) |
| `G` | ArrayList | 滞留单位列表 (strandedUnits) |

关键方法:
- `a(y)` — 添加单位到组 (同时设置 y.aB = this)
- `b(y)` — 从组中移除单位
- `n()` — 清理死亡/无效单位
- `o()` — 清理滞留单位
- `q()` — 释放所有单位 (设置 aB=null)
- `l()` — 获取单位数量
- `p()` — 销毁组 (先释放所有单位)

### 4.2 UnitGroup (g) — 战斗集结组

| 字段 | 类型 | 含义 |
|------|------|------|
| `h` | boolean | 是否攻击者 (isAttacker) |
| `f` | boolean | 是否逃跑 (isFleeing) |
| `q` | boolean | 是否集结 (isStaging) |
| `k` | i (BaseZone) | 关联区域 |
| `n` | float | 随机移动计时器 |
| `o` | float | 任务计时器 |
| `w` | am (UnitInstance) | 当前目标 (敌人) |
| `a` | boolean | 是否持久 (isPersistent) |
| `c` | boolean | 招募启用 (recruitingEnabled) |
| `d` | boolean | 攻击移动启用 (attackMoveEnabled) |
| `l` | float | 闲置计时器 |
| `m` | float | 重新评估计时器 |
| `p` | float | 防御计时器 |
| `r` | boolean | 有攻击目标 |
| `t` | float | 集结计时器 |
| `v` | boolean | 撤退中 |
| `y` | float | 招募计时器 |
| `z` | float | 交战冷却 |
| `A` | int | 组大小限制 |
| `B` | boolean | 巡逻模式 |

### 4.3 单位组状态机

```
UnitGroup.c(float dt) — 每 Tier1 触发
│
├── [侦察阶段] 如果单位数 < 2:
│   ├── 随机移动 (n 计时器)
│   └── 重新评估加入其他组
│
├── [攻击阶段] 如果有攻击目标 (w != null):
│   ├── 发送攻击移动指令
│   ├── 检查目标是否死亡/太远
│   └── 如果撤退 → 返回区域中心
│
├── [防御阶段] 如果在防御中:
│   ├── 移动到防御位置
│   └── 检查是否需要增援
│
├── [巡逻阶段] 如果巡逻模式 (B=true):
│   ├── 沿巡逻路线移动
│   └── 检查遭遇敌人
│
├── [集结阶段] 如果集结中 (q=true):
│   ├── 在区域中心等待
│   └── 达到数量 → 出发
│
└── [撤退阶段] 如果 HP < 33%:
    ├── 标记为撤退 (v=true)
    └── 移动到最近维修点
```

---

## 5. 建造系统 (Build System)

### 5.1 建造决策流程

```
GameWorld.n(float dt)
│
├── 1. 找到需要建造者的区域
│   └── 遍历 bm (所有区域)
│       └── i.d(float dt) — 区域更新
│           ├── 统计资源/单位
│           ├── 计算建造优先级
│           └── i.c() — 选择最佳建筑类型
│               ├── 提取器: 矿点附近, 分数 0.80/0.35/0.20
│               ├── 工厂: 有足够建造者时
│               ├── 炮塔: <4个时高优先级, 递减
│               ├── AA塔: <3个时
│               └── 维修湾: 无维修能力时
│
├── 2. 寻找闲置建造者
│   └── i.y() — findIdleBuilder()
│       ├── 检查建造者是否已分配 (aB != null)
│       ├── 检查建造者是否空闲 (ar() 方法)
│       └── 返回最近可用建造者
│
├── 3. 执行建造
│   └── i.g(UnitType) — executeBuild()
│       ├── i.a(UnitType, PointF, boolean) — findBuilder (20%随机选择)
│       ├── 计算建造目标位置
│       │   ├── 网格搜索 (12环 × 20-53角度)
│       │   ├── 从外环向内搜索
│       │   ├── 检查地形 (山/水/不可建造)
│       │   ├── 密度检查 (300单位内 ≥2 → 跳过)
│       │   └── 最小半径 500
│       ├── 创建 Command
│       │   ├── e.a(float,float,as,int) — setBuildTarget
│       │   └── 设置建造类型, 位置, 阶段数
│       └── 通过 CommandController 发送指令
│
└── 4. 后备逻辑
    └── 如果无区域需要建造:
        ├── 寻找未分组的建造者
        └── 让它们协助最近的工厂或建造者
```

### 5.2 建筑评分系统

| 建筑类型 | 评分权重 | 条件 |
|----------|---------|------|
| extractorT1 | 0.80 | 有矿点, 无提取器 |
| extractorT2 | 0.35 | 同上 |
| extractorT3 | 0.20 | 同上 |
| Land Factory | 按需 | 建造者 > 工厂数 |
| fabricatorT1 | 按需 | 科技升级 |
| turretT1 | 递减 | <4个, 附近有炮塔则降分 |
| antiAirTurret | 递减 | <3个 (全局) |
| Repair Bay | 中 | 无维修能力 |
| 其他 | 低 | 按场景 |

---

## 6. AI 行为扩展 (bJ 列表)

### 6.1 内建行为 (GameWorld 内部类 a$1~a$13)

| 内部类 | 字段 | 含义 |
|--------|------|------|
| `a$1` | `br` | attackingUnitsLand — 攻击用陆面单位组 |
| `a$6` | `bs` | attackingUnitsHover — 攻击用悬浮单位组 |
| `a$7` | `bt` | attackingUnitsAir — 攻击用空中单位组 |
| `a$8` | `bu` | attackingUnitsWater — 攻击用水面单位组 |
| `a$9` | `bv` | buildingUnits — 建造单位组 |
| `a$10` | `bw` | transportUnits — 运输单位 |
| `a$11` | `bx` | transportUnitsFlying — 飞行运输 |
| `a$12` | `by` | transportUnitsNonFlying — 非飞行运输 |
| `a$13` | `bz` | builderUnits — 建造者单位组 |
| `a$2` | `bA` | harvesterUnits — 采集者单位组 |
| `a$3` | `bB` | extractorUnits — 提取器单位组 |
| `a$4` | `bC` | buildingFactories — 工厂建筑组 |
| `a$5` | `bD` | buildingFactoriesForBuilders — 建造者用工厂组 |

### 6.2 行为接口 (game.a.a.a)

每个行为实现两个方法:
- `a(float dt, GameWorld)` — 添加单位时调用
- `b(float dt, GameWorld)` — 每帧更新时调用

---

## 7. 关键常量

| 常量 | 值 | 含义 |
|------|-----|------|
| 指挥中心收入 (cy) | 18.0f/s | `d/e.java:175` |
| 默认起始资金 | 4000.0 | — |
| Tier1 间隔 | 0.25s | 资源/建筑更新 |
| Tier2 间隔 | 2.0s | AI 逻辑/单位组 |
| Tier3 间隔 | 4.5s | 核心逻辑 |
| 区域扩展最小半径 | 420 | 新区域创建 |
| Forward 区域半径 | 310 | 工厂前方 |
| Main 区域半径 | 360 | 主基地区域 |
| 建造位置搜索环数 | 12 | 从外到内 |
| 每环角度数 | 20-53 | 角度步进 |
| 建造密度检查半径 | 300 | 2个建筑内跳过 |
| 建造者扫描范围 | 500-800 | 寻找建造位置 |
| 建造者随机选择率 | 20% | 避免同一建造者过载 |

---

## 8. 与 RWAgent 的对应关系

| 官方方法 | RWAgent 实现 | 说明 |
|----------|-------------|------|
| `GameWorld.a(float)` | `micro_ai_tick()` | 主AI tick |
| `GameWorld.m(float)` | `zoneUpdate_m()` | 核心逻辑 |
| `GameWorld.n(float)` | `zoneUpdate_n()` | 建造者派遣 |
| `i.d(float)` | `zoneUpdate_d()` | 区域建造循环 |
| `i.c()` | `getBestBuilding()` | 最佳建筑选择 |
| `i.g(UnitType)` | `execBuildOrder()` | 执行建造 |
| `i.y()` | `findIdleBuilder()` | 寻找建造者 |
| `i.a(UnitType,PointF,boolean)` | `findBuilder()` | 分配建造者 |
| `am.ar()` | `waypointCheck()` | 检查是否有路径点 |

*来源: game-lib.jar CFR 0.152 反编译, 1909行 GameWorld + 关联类, 2026-06-22*



