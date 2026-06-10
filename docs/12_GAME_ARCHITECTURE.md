# 12 — Rusted Warfare v1.15 完整架构解析

> 基于 1698 类字节码逆向 | 全部系统完整还原

---

## 一、游戏主循环

### 1.1 启动流程

```
Main.main()
  → l.B().a(context)              // GlobalState 初始化
    → l.<init>(context)            // 214字段初始化
      → fw.k.o 创建                // GameLoop 性能统计
      → fw.z 创建+启动             // GameThread 主线程
      → fw.j.ad 初始化             // NetEngine 网络引擎
      → fw.e.a 初始化              // 文件路径管理
      → fw.i.a 加载Mod            // Mod系统初始化
    → ag.h()                       // 行为引擎: 注册所有内置单位
    → am.bH()                      // 静态初始化所有单位类型
  → game.i.<init>(context)         // GameScreen (主游戏实例, extends l)
  → game.i.a(bool,bool,state)      // 主渲染循环 (2662B)
```

### 1.2 帧循环结构 (每帧)

```
GameThread.run() [fw.z]:
  while running:
    long now = nanoTime()
    
    // 计算delta
    delta = (now - lastFrame) / 1_000_000_000.0  // 秒
    
    // === 第1层: 网络同步 (每tick) ===
    l.bX.d(delta)                                // NetEngine 收发包
    
    // === 第2层: 游戏逻辑更新 ===
    game.i.a(delta):                              // GameScreen 主更新
      
      // 2a. 地图更新 (每帧)
      l.bL.c()                                   // MapEngine 刷新瓦片/迷雾
      
      // 2b. 单位更新 (所有活跃单位)
      for unit in am.bE:                         // 遍历全局单位注册表
        unit.r().a(delta, unit, ...)             // UnitType 更新 (y.a)
          → unit.a(delta)                        // 单位实例更新
          → 收入累积 (y.i)
          → 武器更新 (y.c/d/e/f/g/h)
          → 行为检测 (LogicBoolean 条件)
      
      // 2c. 战斗更新 (80ms批次)
      if tick % batch == 0:
        fw.ab.c()                                // 弹丸碰撞检测
        CombatManager 更新
      
      // 2d. AI更新 (250ms批次)
      if tick % 15 == 0:                         // 250ms @60fps
        for aiPlayer in AI_players:
          aiPlayer.i(delta)                      // AIPlayer 主决策
            → aiPlayer.m(delta)                  // Zone 更新
            → aiPlayer.n(delta)                  // 建造管理
            → aiPlayer.a(delta)                  // 经济更新
      
      // 2e. 战败检测 (每10帧)
      if tick % 10 == 0:
        for player in players:
          player.e(delta)                        // 检查是否战败
    
    // === 第3层: 渲染 ===
    game.i.a(bool,bool,state):                   // 主渲染 (2662B)
      → l.bO 渲染地图                            // SpriteBatch 渲染
      → l.bM 渲染单位                            // 单位精灵
      → l.bP 渲染UI                              // HUD/菜单
      → librocket 渲染HTML覆盖层
    
    // === 帧率控制 ===
    sleep(max(0, targetFrameTime - elapsed))
```

### 1.3 时间常量

```
TICK_RATE = 30          // 回放指令时间戳速率
FRAME_RATE = 60          // 游戏渲染/更新帧率
ECONOMY_WINDOW = 40      // 经济数据显示窗口(帧)
GAME_SPEED = 2.5         // 默认游戏速度倍率

实际每秒 = 显示值 × (60/40) × 2.5 = 显示值 × 3.75
```

---

## 二、单位系统

### 2.1 类层次结构

```
y (UnitType, 90KB, 115F/253M)           ← 所有单位类型的抽象基类
├── ar (UnitTypeRegistry, 20KB)         ← 内置单位枚举 (~70种)
│   └── ar$1~ar$45                       ← 各内置单位类型实现
├── w (UnitBase)                         ← 中间基类
│   └── custom.j (90KB, 60F/290M)       ← ★ 自定义单位实现
│       implements ak(行为), d(状态), d.l(工厂)
├── v (MobileUnit)                       ← 可移动单位
│   ├── al (TreeUnit, 8.9KB)            ← 树木装饰
│   └── ai (FireUnit, 7.3KB)            ← 火焰特效
├── x (StructureUnit)                    ← 结构/建筑
│   ├── f (DamagingBorder, 7.8KB)       ← 伤害边界
│   ├── u (1.4KB)                       ←
│   └── t (1.3KB)                       ← 皮肤/变体
├── d.i (BuildingBase)                   ← 建筑基类
│   ├── d.e (CommandCenter, 7.2KB)      ← CC指挥中心
│   └── d.a.b (10KB)                    ← 大型单位
└── e.j (FactoryBaseExt)                 ← 工厂扩展基类
    ├── h (FactoryBase, 20KB, 37F/56M)  ← 工厂基类
    │   └── h.e (Factory, 7.6KB, 12F/41M) ← 具体工厂
    └── g (BuildingBase2, 7.3KB)        ← 建筑基类2

am (UnitInstance, 43KB, 124F/212M)      ← ★ 地图上的单位实例
```

### 2.2 单位实例 (am) 核心字段

```
位置/物理:
  eo (float)      — X坐标 (⚠ 在父类w上, 非am声明)
  ep (float)      — Y坐标 (⚠ 在父类w上, 非am声明)
  cj (float)      — 碰撞半径
  cg (float)      — 朝向角度 (度)
  cl (float)      — 质量/重量

HP/护盾:
  cu (float)      — 当前HP
  cv (float)      — 最大HP
  cx (float)      — 当前护盾
  cy (float)      — 护盾累积吸收量
  cz (float)      — 特殊伤害标志

建造:
  cm (float)      — 建造进度 (≥1.0 = 完成)

归属:
  bX (ref(n/d))   — 所属队伍/玩家 (声明n, 运行时为子类d)
  bW (long)       — 出生时间戳
  cN (ref(am))    — 父单位 (运输者/附着目标)

状态:
  bT (bool)       — 存活标志
  bV (bool)       — 死亡标志
  bs (int)        — 最后伤害时间戳
  bt (ref(am))    — 最后攻击者

收入:
  cy() → float    — 每40帧收入值

移动:
  cL (ap[])       — 移动参数数组 (位置/速度/加速度)
```

### 2.3 单位行为模式 (units.a)

```
aggressive      — 主动攻击范围内所有敌人
guardArea       — 守卫指定区域
holdFire        — 停火, 不主动攻击
returnFire      — 仅在被攻击时还击
mixed           — 混合模式
onlyInRange     — 仅在射程内攻击
outOfRange      — 超出范围
```

### 2.4 武器类型 (av, 17种)

```
move (0)        — 移动
attack (1)      — 攻击
build (2)       — 建造
repair (3)      — 维修
loadInto (4)    — 装载进入
unloadAt (5)    — 卸载到位置
reclaim (6)     — 回收 (80%退款)
attackMove (7)  — 攻击移动
loadUp (8)      — 装载
patrol (9)      — 巡逻
guard (10)      — 守卫
guardAt (11)    — 守卫位置
touchTarget(12) — 接触目标
follow (13)     — 跟随
triggerAction(14)    — 触发动作
triggerWhenInRange(15)— 范围内触发
setPassiveTarget(16) — 设置被动目标
```

### 2.5 移动类型 (ao, 8种)

```
AIR              — 空中
LAND             — 地面
HOVER            — 悬浮
BUILDING         — 建筑 (不可移动)
NONE             — 无移动
OVER_CLIFF       — 越崖
OVER_CLIFF_WATER — 越崖水面
ROOT             — 根植/固定
```

### 2.6 单位生命周期

```
1. 创建: 工厂建造完成 → h.e.s() 进度≥1.0
         → am.r(1.0f) 设置完成标志
         → s.a(am) 注册到队伍追踪器
         → spatialGrid.a(am) 加入空间网格
         → am.bE.add(am) 加入全局注册表

2. 运行: 每帧 y.a(delta, unit, ...)
         → 收入累积: if ab ≥ 1.0: n.o += ab; ab -= int(ab)
         → 武器冷却: g -= delta
         → 行为检测: LogicBoolean 条件评估

3. 受伤: applyDamage(incoming, attacker)
         → 护盾吸收: shieldDamage = incoming × shieldMult
         → HP伤害:  hpDamage = remaining × hpMult
         → 1.75×倍率:  if cm < 1.0 (未完成建筑)

4. 死亡: am.ch() 检测 cu ≤ 0
         → bv() 死亡序列入口
         → bu() 核心清理:
           - 空间网格移除
           - 全局注册表移除
           - 队伍追踪器注销 (s.b)
         → bx() 父单位解绑
         → bC() ResourceComponent 重算
         → cX() 死亡特效创建
```

---

## 三、经济系统

### 3.1 收入公式链

```
第1层: UnitType 收入累积 [y.i(float, au, ad), 2196B]
  ab += delta × rate                           // ab = 收入累积器
  rate = B().a()                               // ResourceComponent 速率
      = generation_credits / generation_delay

第2层: 资金入账 [y.ab ≥ 1.0 触发]
  n.o += (double)ab                            // 转入玩家资金
  ab -= (int)ab                                // 保留小数

第3层: 队伍统计 [s.a(am) 注册]
  if unit.cy() != 0 && unit.cm >= 1.0:
      s.g += (int)unit.cy()                    // 累加收入率

第4层: 实际每秒收入
  per_second = s.g × (60fps / 40帧) × ay.h × gameSpeed
             = s.g × 1.5 × incomeMultiplier × speed

示例 (2.5×速度):
  CC:    18 × 1.5 × 2.5 = 67.5/s
  T1提取: 8 × 1.5 × 2.5 = 30.0/s
  T2提取: 12 × 1.5 × 2.5 = 45.0/s
  T3提取: 20 × 1.5 × 2.5 = 75.0/s
```

### 3.2 建造系统

```
BuildProgress (d.j, 14字段):
  a (int)        — 建造槽位号
  b (float)      — buildSpeed (建造速度)
  c (d.b)        — ResourceComponent 消耗
  d (d.b)        — ResourceComponent 额外消耗
  e (custom.h)   — 目标单位类型
  f (bool)       — 是否已完成
  h (PointF)     — 目标位置
  i (am)         — 建造者
  m (float)      — 当前进度 (0.0~1.0)
  n (double)     — ★ 累积建造进度

进度累积:
  n += b × delta_ms
  完成: n ≥ 1.0 → f = true → am.r(1.0f)

工厂建造 (h.e):
  buildSpeed = 0.03/tick, maxProgress = 280
  实际秒数 = 280 / (0.03 × 60 × 2.5) ≈ 62s

普通建造:
  buildSpeed = 0.10/tick, maxProgress = 330
  实际秒数 = 330 / (0.10 × 60 × 2.5) ≈ 22s

未完成建筑:
  伤害倍率: 1.75×
  收入: 0 (cm < 1.0 不贡献)
```

### 3.3 升级系统

```
继承覆盖 (非累加):
  extractorT1 → extractorT2:
    1. T2建造完成 → am.r(1.0f)
    2. s.b(T1) → s.g -= 8 (扣除旧收入)
    3. T1标记为 'replaced'
    4. s.a(T2) → s.g += 12 (添加新收入)
  结果: 收入 8→12 (替换), 花费 700+1400=2100 (累加)
```

### 3.4 退款系统

```
取消建造 (stopOrUndo):  100% 退款
回收建筑 (reclaim):     80% 退款 (0.8f)
  → b6.a(am, 0.8, true)  // 引擎调用
  → n.o += buildCost × 0.8
  → s.g -= cy()           // 扣除收入
```

### 3.5 队伍追踪器 (s, 17字段)

```
核心字段:
  a (int)        — 最大单位限制
  b (int)        — 非建筑单位数
  c (int)        — 已完成单位数
  d (int)        — 累计建造总数
  e (int)        — 工厂建造槽位数
  f (int)        — 未完成建造数
  g (int)        — ★ 总收入率 = Σ cy()
  h~l (e.f×5)    — 5个 ResourceComponent
  p (t)          — 建造队列1
  q (t)          — 建造队列2
  n (int)        — 回收计数
  o (int)        — 取消计数

注册流程 [s.a(am), 358B]:
  1. d++ (累计建造)
  2. if cm<1.0: f++ else: c++
  3. if !isBuilding(): b++
  4. 添加 ResourceComponent 到 h~l
  5. 如果是工厂: b+=槽位数, 处理建造队列
  6. if cy()>0 && cm≥1.0: g += (int)cy()  ★

注销流程 [s.b(am), 327B]:
  (反向操作, 最后 g -= cy())
```

---

## 四、战斗系统

### 4.1 伤害计算公式

```java
float applyDamage(float incoming, UnitInstance attacker, MovementController moveCtx):
    // 倍率来自 game.f (移动控制器)
    float shieldMult = moveCtx.al;     // 护盾伤害倍率
    float shieldAbsorb = moveCtx.am;   // 护盾吸收系数
    float hpMult = moveCtx.an;         // HP伤害倍率
    
    float remaining = incoming;
    float absorbed = 0;
    
    // === 阶段1: 未完成建筑惩罚 ===
    if this.cm < 1.0:
        remaining *= 1.75;              // ★ 1.75× 伤害
    
    // === 阶段2: 护盾吸收 ===
    if this.cz == 0 && this.cx > 0:     // 无特殊标志, 有护盾
        shieldDmg = remaining * shieldMult;
        if this.cx >= shieldDmg:        // 护盾充足
            remaining -= this.cx * shieldAbsorb;
            absorbed += this.cx;
            this.cy += this.cx;         // 累积护盾吸收
            this.cx = 0;                // 护盾归零
        else:                           // 护盾不足
            this.cx -= shieldDmg;
            this.cy += shieldDmg;
            absorbed += shieldDmg;
            remaining -= remaining * shieldAbsorb;
    
    // === 阶段3: HP伤害 ===
    if remaining > 0:
        hpDmg = remaining * hpMult;
        if this.cu >= hpDmg:            // HP充足
            remaining -= this.cu;
            absorbed += this.cu;
            this.setHP(0);              // ★ HP归零
            this.cC += this.cu;         // 累计HP损失
        else:                           // HP部分损失
            this.setHP(this.cu - hpDmg);
            absorbed += hpDmg;
            remaining -= hpDmg;
            this.cC -= hpDmg;
    
    // === 阶段4: 记录+触发 ===
    this.bs = l.by;                     // 伤害时间戳
    this.bt = attacker;                 // 最后攻击者
    this.ch();                          // ★ 检查死亡
    return remaining;                   // 溢出伤害 (溅射)
```

### 4.2 死亡链 (8步)

```
Step 1: am.ch() [21B]
  if !bV && cu <= 0: bv()

Step 2: am.bv() [20B]
  bu()                              // 核心清理
  if !e(): a()                      // 资源释放
  bt()                              // 最终清理

Step 3: am.bu() [98B]
  l.bS.l(this)                      // 从GameUI注销 (运行时验证)
  n.a(this)                         // ★ 从追踪器注销 (s.b)
  am.bE.remove(this)                // ★ 从全局注册表移除
  if cu > 0: cu = 0                 // 强制HP归零
  清除 cL[] 移动参数
  l.cc.a(this)                      // ★ 从空间网格移除

Step 4-6: 条件释放/资源回收

Step 7: y.bx() [80B]
  if cO != null: 父单位解绑
    正常解绑失败 → "Deattach failed, forcing deattach"

Step 8: y.bC() [83B]
  重算 ResourceComponent → n.b()/n.c() 重注册
```

### 4.3 弹丸系统 (fw.ab)

```
弹丸碰撞检测 [c(), 630B]:
  1. 获取弹丸平均位置 (所有活跃弹丸中心点)
  2. 遍历每个弹丸:
     - 计算弹丸→目标距离
     - 距离 < 阈值 → 触发碰撞
  3. 碰撞参数:
     - 最小偏移: 0.4px
     - 弹丸速度系数: 80
     - 最大检测距离: 160
     - 生命周期: 3600 ticks (60s @60fps)
     - 超时: 14400 ticks (4min)
     - 远距离判定: 200² (40000)
     - 超远距离: 400² (160000)
```

### 4.4 空间索引 (f.c, SpatialGrid)

```
网格: 32×32 单元格
单元格大小: 50.0px
覆盖范围: 1600×1600px

查询类型:
  CircleFilter (f.g)   — 圆形索敌
  RectFilter (f.d)     — 矩形区域
  LineFilter (f.h)     — 弹道检测
  TeamFilter (f.i)     — 队伍过滤 (敌方/己方)

添加: a(am) → 242B
  1. 计算网格坐标: col = eo/50, row = ep/50
  2. 放入对应 f.a 网格单元
  3. 按单位类型分层 (地面/空中/特殊)
```

---

## 五、移动/寻路系统

### 5.1 移动控制器 (game.f, 118字段, 36KB)

```
每个移动中的单位有一个 game.f 实例:

核心字段:
  j (am)         — 所属单位
  g (game.g)     — 弹丸引用
  n/o (float)    — 目标X/Y
  h/i (float)    — 当前速度X/Y
  p/q/r (float)  — 加速度参数
  s/t/u/v/w/x/y  — 移动状态
  al/am/an       — 伤害倍率参数 (传给 applyDamage!)

主更新 [a(float), 6276B] ★ 最大的方法:
  1. 计算方向向量 (目标 - 当前位置)
  2. 距离检查: if dist < radius → 到达
  3. 速度 = moveSpeed × delta
  4. 碰撞检测 + 位置更新
  5. 空间网格更新
```

### 5.2 A* 寻路系统

```
PathPool (fw.k.l, 37F/34M):
  — 工作线程池
  — "cores, creating extra solvers" — 多核并行
  — "lowPriority", "allowedDelay" — 优先级控制

PathSolver (fw.k.i, 17F/14M):
  a(y)→void [661B]:        构建/替换成本网格
    — 分配 byte[b×c] 网格
    — 限制50次调用 ("buildAndReplaceClearanceCost being skipped")
    — 复制旧网格数据
  
  d()→void [654B]:         ★ A* 求解主循环
    — 遍历网格行/列
    — 检查可通过性 (d[]/e[] byte数组)
    — 设置通行成本 (j[] byte数组)
  
  e()→void [438B]:         路径重建
    — 从目标回溯到起点
    — 生成路点序列
  
  b(y)→void [353B]:        清除路径数据

PathCostCalc (fw.k.k, 26F/15M):
  — 基础移动成本: 160000 (400²)
  — 压缩路径: writeOutCompressedPath / readInCompressedPath
  
寻路网格常量:
  — 最大相邻节点: 8
  — 最大开放节点: 16
  — 最大迭代: 50
  — 最大路径长度: 127
  — 节点成本: 5/10/20
  — 权重: 0.333 (1/3)
```

### 5.3 寻路工具 (utility.y, 33方法)

```
地形检测:
  isInMap(x,y)             — 坐标在地图内
  isOverClift(x,y)         — 在悬崖上
  isOverLiquid(x,y)        — 在水面上
  a(x,y,ao)→bool           — 移动类型能否通过

编组检测:
  a(am)→bool               — 单位在路径组中
  "Could not find groupSize for:"
  "Unload, attachment data is null"
  a(n,PointF)→void         — 为队伍寻找路径 (177B)

路点值:
  b(x,y,ao)→short (91B)    — 获取路点通行值
  c(x,y,ao)→int (116B)     — 获取分组值
```

---

## 六、AI 系统

### 6.1 AI 架构

```
AIPlayer (game.a.a, 44KB, 71F/79M)  ← 每个AI玩家一个实例
  extends game.n (GameState)

决策层次:
  i(float)→void [2451B]    — ★ 主AI循环 (每250ms)
    ├── a(float)→void       — 经济决策 (925B)
    │   └── 检查资金/收入, 决定建造优先级
    ├── m(float)→void       — Zone管理 (2048B)
    │   └── 遍历所有Zone, 分配单位
    ├── n(float)→void       — 攻击管理 (1994B)
    │   └── 编组攻击单位, 选择目标
    └── l(float)→void       — 工厂队列管理 (343B)

Zone体系 (5种):
  a.o (Zone抽象, 5.6KB)
  ├── a.i (AttackZone, 29KB)     — 攻击编组+目标选择
  ├── a.g (BuildZone, 14KB)      — 建造区: 分配建造任务
  ├── a.n (TransporterGroup, 8KB) — 运输编组
  ├── a.l (2.1KB)                — 定时器区
  └── a.m (PlainZone, 250B)      — 默认/通用区
```

### 6.2 AIPlayer 初始化

```
av()→void [153B]:
  aL = 100/9 ≈ 11.1       — 建造阶段初始延迟 (秒)
  aN = 202/19 ≈ 10.6      — 攻击波次间隔 (秒)
  aP = 50                  — 侦察间隔 (帧)
  aW = 4200                — 扩展距离 (70 tiles)
  aT = 3500                — 炮塔检测距离
  aU = 7500                — 最大检测距离

初始编组:
  br = attackingUnitsLand    — 地面攻击编组
  bs = attackingUnitsHover   — 悬浮攻击编组
  bt = attackingUnitsAir     — 空中攻击编组
  ...

初始资金: 3000 (= starting credits + n.o offset?)
```

### 6.3 AttackZone 决策

```
c()→as [1026B] — 选择要建造的单位类型:
  1. 获取可用单位列表
  2. 筛选: 资源足够, 科技解锁, 可建造
  3. 按优先级排序 (cost, buildTime, weight)
  4. 随机选择 (带权重): roll in [0, totalWeight]

d(float)→void [927B] — 主更新:
  1. 检查建造者可用性
  2. 推进建造队列
  3. 分配建造者到目标位置

v()→void [772B] — 执行攻击波次:
  1. 检查攻击者数量 ≥ minAttackers
  2. 选择最近/最弱敌方目标
  3. 发送攻击指令

BuildZone (a.g):
  建造者搜索范围: 100px
  区域范围: 1000px (默认)
  最大搜索: 4000px
  超远距离: 28900px (170²)
```

### 6.4 波次/任务系统

```
WaveSystem (n.f, 26KB, 46F):
  — 难度倍率 M = 3000.0
  — 波次间隔 u (由难度决定)
  — 波次计时器 z
  — 无限波次模式 q

MissionEngine 流程:
  1. n.c (MissionParser, 6KB) — 从 .ini 解析 AITask
  2. n.d (MissionExecutor, 7KB) — 执行 AITask:
     - 显示全局消息 (globalMessage)
     - n.i.a(x,y) — 调用 AISpawnList 在地图生成单位
  3. n.b (AITaskQueue) — 跟踪任务完成状态
  4. n/a/c (UnitCountCondition) — 条件检测 (遍历 am.bE)

AISpawnList (n.i, 3.3KB):
  a(float,float)→void [287B]:
    — 创建波次专用队伍 (n.k(1))
    — 位置随机化: ±85px
    — 朝向随机: -180° ~ +180°
    — 添加到空间网格
```

### 6.5 AI 关键阈值

```
距离:
  100px   — 建造者搜索
  200px   — 最近单位搜索
  290px   — 最近单位
  300px   — CC接近检测
  320px   — 扩展距离
  360px   — 攻击检测
  400²    — 远距离 (160000)
  700²    — 超远距离 (490000)

时间:
  3000ms  — AI初始延迟
  ~11s    — 建造阶段
  ~10.6s  — 攻击波次间隔
  50帧    — 侦察间隔

概率:
  0-100   — 随机决策范围
  90%     — 高概率阈值
  98%     — 接近完成比例
```

---

## 七、网络/存档系统

### 7.1 序列化协议

```
InputNetStream (j.k, 8KB, 42M):
  — 命名块协议
  — 最大深度: 11层
  — 最大ID: 999999
  — 同步标记: 12345

OutputNetStream (j.as, 8KB, 45M):
  — 配套写入器
  — 块格式: [name_len:2B][name:UTF8][data_len:4B][data:bytes]

UnitInstance 序列化 (am):
  版本标记: 26
  字段顺序: 34个字段 (见 11_CONSTANTS.md)
  版本兼容: 读取时按版本号跳过未知字段
```

### 7.2 回放格式

```
.replay 文件:
  [13B: magic "rustedWarfareReplay"]
  [4B: header_int1 = 176 (SettingsEngine.settingsGameVersion)]
  [4B: header_int2 = 96]
  [version: "1.15"]
  [TLV: gamesave → rustedWarfareSave → saveCompression → GZIP]
  [指令流: j.as 块序列]
    ├── rc: 玩家指令 (建造/移动/攻击)
    ├── chat: 聊天
    ├── cs: 同步检查
    └── end: 块结束
```

### 7.3 存档 (.rwsave)

```
GameSaver (fw.y, 27KB, 15M):
  b(String,bool)→void [648B]:    — 保存
    1. 创建 .tmp 临时文件
    2. j.as 序列化游戏状态
    3. 压缩/加密
    4. 重命名 .tmp → .rwsave

  a(j.k,bool,bool,bool)→bool [4102B]: — 加载
    — 解析存档块
    — 重建游戏对象
    — 错误检测: "errors were found in this save"
```

---

## 八、地图系统

### 8.1 地图引擎 (game.b.b, 38KB, 71F/71M)

```
核心数据:
  C/D (int)           — 地图宽/高 (像素)
  T/U (int)           — 迷雾宽/高
  u~y (b.e ×5)        — ★ 5个瓦片层
    0: 地面层 (ground)
    1: 生物群系 (biome)
    2: 装饰层 (decoration)
    3: 资源层 (resource)
    4: 覆盖层 (overlay)
  M/N (byte[][])      — 迷雾数据层
  B (b.g[])           — 地面细节
  k (bool[])          — 可见性数组

迷雾:
  "Building smoothFog_cache"
  "No team fog on this map.."
  "Setting up team fog.."
  "Unknown map fog type: "
  "seeThoughFogOfWarTimes:"

TMX加载:
  b.e (13KB) — TMX解析器
    "only gzip base64 is supported" (Tile数据格式)
    1472B 主解析方法
```

### 8.2 瓦片/地面

```
GroundDetails (game.b.g, 8.6KB):
  — 随机瓦片选择
  — "randomTileBy:", "randomTileFixedOffset:"
  — 1615B 方法

TilesetLoader (game.b.j, 9.3KB):
  — "Embedded tilesetBitmap is null"
  — base64 图像解码

TileRenderer (game.b.h, 5.2KB):
  — 5图层渲染管线
```

---

## 九、框架/工具

### 9.1 全局状态 (l, 32KB, 214F/146M)

```
l.B() — 全局单例入口

子系统引用:
  bL (b.b)       — 地图引擎
  bX (j.ad)      — 网络引擎 (104KB)
  bS (f.g)       — GameUI (运行时验证: 含bZ选中列表)
  bU (recycler)  — 单位回收队列
  bY (bg)        — 统计管理器 (StatsManager, 内部使用 bo=StatsData)
  cb (ba)        — 回放引擎
  cc (f.c)       — 空间网格
  cf (cmdMgr)    — 指令包管理器
  bQ (Settings)  — 游戏设置引擎
  bs (n)         — 当前玩家引用
  by (int)       — ★ 游戏 tick 计数器

内存限制: 2.5MB / 5MB
OS检测: Linux/MacOS/Windows/Other
```

### 9.2 核心数据结构

```
utility.m (7KB, 34M)    — 自定义 ArrayList (403处引用)
  Object[] + int count
  扩容: 小→翻倍, 大→+50%

utility.u (7KB, 36M)    — 全局单位注册表 (am.bE)
  am[] + int count
  类型安全的 ArrayList<UnitInstance>

utility.o (6KB, 32M)    — 双端队列 (DequeList)
  两个 utility.m: front + back
  支持 addFirst/addLast

utility.g (3KB, 24M)    — 环形缓冲区
  head/tail 指针, 自动扩容
  "Sorry, deque too big"

utility.s (7KB, 38M)    — GameObject 列表 (fw.w[])
  fw.w[] 类型化列表
  带调试名称字段

utility.y (12KB, 33M)   — 寻路工具 (全静态)
```

### 9.3 数学工具 (gameFramework.f, 36KB, 128M)

```
随机数:
  a(int,int,int)→int (166B)      — LCG: seed=(seed×999950+13131313)%range
  a(float,float,int)→float       — 浮点随机
  2个 Random 实例 (a + b)

三角函数:
  10个预计算查找表 (float[])
  sin/cos/tan → 避免每帧计算

几何:
  i(float,float)→float (237B)    — 复杂角度计算
  c(float,float)→float           — 2D距离
  a(PointF,PointF,PointF,RectF)  — 点投影/碰撞

格式化:
  a(long)→String (203B)          — 时间格式化 "1h 23m 45s"
  b(double,int)→String (118B)    — 精度浮点
  h(float)→String (64B)          — 字节大小 "1.5 MB"

哈希:
  b(byte[])→String (MD5)
  a(byte[])→String (SHA-256)
```

---

## 十、LogicBoolean 脚本系统 (215类)

### 10.1 系统结构

```
LogicBoolean (16KB, 42M)          — 抽象基类
  read(y)→bool                    — 条件评估
  readNumber(y)→float             — 读取数值
  readString(y)→String            — 读取字符串
  readUnit(y)→am                  — 读取单位引用

逻辑运算 (19):
  AndBoolean, OrBoolean, NotBoolean
  StaticBooleanTrue/False
  TimeBoolean

比较运算 (22):
  CompareEqual/NotEqual (Numbers/Strings/Units/Boolean)
  CompareGreaterThan/LessThan (Numbers)
  MathAdd/Subtract/Multiply/Divide/Modulus
  StringJoinerBoolean

游戏函数 (73):
  HpBoolean, ShieldBoolean, EnergyBoolean     — HP/护盾/能量
  MovingBoolean, IsAttackingBoolean          — 状态检测
  HasResourcesBoolean, PriceCreditsBoolean   — 资源/价格
  NumberOfUnitsInTeam, TeamVictoryBoolean     — 队伍统计
  OverCliftBoolean, OverWaterBoolean          — 地形检测
  TimeAliveBoolean, CustomTimerBoolean        — 计时器
  TagsBoolean, TeamTagBoolean                — 标签匹配

数值函数 (17):
  FunctionDistance, FunctionDistanceBetween
  FunctionSin, FunctionCos
  FunctionRnd, FunctionMin, FunctionMax
  FunctionSquareRoot, FunctionInt

字符串 (13):
  StaticString, LowerString, UpperString
  Playername, TeamName, Substring

单位引用 (26):
  Self, Parent, Nearest, First, Attacking
  Chained, Attachment, Transporting
  LastDamagedBy, Locked, Memory1/2
  GetOffsetAbsolute/Relative

变量系统 (29):
  VariableScope, VariableMapping
  MemoryWriter, CachedWriter
  VariableData (Number/String/Boolean/Unit/Array)
```

### 10.2 条件评估流程

```
1. .ini [action_*] 段定义触发条件
2. LogicBooleanLoader 解析字符串 → LogicBoolean 树
3. 事件触发时 (custom.k Event):
   - 获取当前单位上下文 (y)
   - 遍历 LogicBoolean 树
   - read(y) 递归评估
   - 返回 true/false
4. 条件满足 → 执行对应动作 (custom.at ActionDef)
```

---

## 总结

```
Rusted Warfare v1.15 — 完整架构:
  ├── 主循环: 3层 (网络→逻辑→渲染) @60fps
  ├── 单位: 8层继承, 17种武器, 8种移动, 7种行为模式
  ├── 经济: 4层收入链, 建造/升级/退款
  ├── 战斗: 护盾→HP→死亡 3阶段伤害, 弹丸碰撞
  ├── 移动: A*寻路 (8邻域/50迭代), 32×32空间网格
  ├── AI: 5种Zone, 波次/任务, 250ms决策周期
  ├── 网络: 命名块协议 (11层深度), 存档/回放
  ├── 地图: 5瓦片层, TMX, 迷雾
  ├── 脚本: 215类 LogicBoolean 条件系统
  └── 框架: 自定义数据结构, 数学工具, 常量参数

所有数据从 game-lib.jar 1698类字节码直接还原
```
