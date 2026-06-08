# 01 — 经济系统

> 逆向度: 100% | 核心类: `n`(GameState), `s`(TeamUnitTracker), `y`(UnitType), `d.b`(ResourceComponent)
> 验证: game-lib.jar 字节码 + RW-HPS 服务器 + 30回放 + 24个 .ini 文件

---

## 类名意义

| 混淆名 | 完整路径 | 实际含义 |
|--------|---------|---------|
| `n` | `game.n` | **GameState** — 单个玩家/队伍的完整状态 |
| `s` | `game.s` | **TeamUnitTracker** — 队伍单位/收入统计追踪器 |
| `y` | `game.units.y` | **UnitType** — 单位类型基类 (抽象) |
| `am` | `game.units.am` | **UnitInstance** — 地图上的单位实例 |
| `d.b` | `game.units.custom.d.b` | **ResourceComponent** — 资源/经济组件 |
| `d.e` | `game.units.d.e` | **CommandCenter** — 指挥中心单位类 |
| `ay` | `j.ad` 的子对象 | **GameSettings** — 游戏设置 (收入/迷雾/核弹等) |

---

## 收入公式 — 完整链路

### 第1层: 单位实例 → UnitType 收入累积

```java
// y.java — UnitType 基类
public float ab;                              // ★ 收入累积器 (浮点)

// 每帧调用 (game.a.a GameWorld 主循环):
private void i(float delta, au weapon, ad action) {
    // delta    = 帧间隔 (ms)
    // weapon   = 武器类型 (17种枚举)
    // action   = 行为定义
    
    float rate = this.B().a();                // d.b.a() → generation_credits / generation_delay
    this.ab += delta * rate;                  // 累积收入
    
    if (this.ab > 1.0f) {
        n = this.bX;                          // 获取所属玩家 (am.bX→n)
        n.o += (double)this.ab;               // ★ 转入玩家资金
        this.ab -= (int)this.ab;              // 保留小数部分 (如 2.3 → 0.3)
    }
}
```

### 第2层: ResourceComponent → 生成速率

```java
// custom.d.b.java — ResourceComponent
public float a() {                            // 获取生成速率
    return generation_credits / generation_delay;
    // generation_credits = cy() 的值 (来自 .ini)
    // generation_delay   = 40   (默认帧数)
}
```

### 第3层: 队伍追踪器 → 累加所有单位贡献

```java
// s.java — TeamUnitTracker (17字段, 5KB)
public int g;                                 // ★ 队伍总收入率 = Σ cy()

// ★ 5个 ResourceComponent (新增发现!):
public custom.e.f h;  // 资源类型1 (金属?)
public custom.e.f i;  // 资源类型2 (能源?)
public custom.e.f j;  // 资源类型3
public custom.e.f k;  // 资源类型4
public custom.e.f l;  // 资源类型5

// ★ 2个 BuildQueue (新增发现!):
public game.t p;  // 建造队列1 (地面?)
public game.t q;  // 建造队列2 (空中/海军?)

// 其他字段:
public int a;  // 最大单位数限制
public int b;  // 非建筑单位数
public int c;  // 已完成单位数
public int d;  // 累计建造总数
public int e;  // ?
public int f;  // 未完成建造数
public boolean m;  // ?
public int n;  // 取消计数
public int o;  // 回收计数

public void a(am unit) {                      // 单位注册 (完成建造或加入)
    float f3 = unit.cy();                     // 获取单位的 cy() 值
    if (f3 != 0.0f && unit.cm >= 1.0f) {      // 有收入 且 建造完成
        this.g = (int)((float)this.g + f3);   // 累加到队伍收入率
    }
    this.d++;                                 // 累计建造总数 +1
}

public void b(am unit) {                      // 单位注销 (死亡/回收/升级)
    float f3 = unit.cy();
    if (f3 != 0.0f && unit.cm >= 1.0f) {
        this.g = (int)((float)this.g - f3);   // ★ 扣除 (升级时先扣旧再加新)
    }
    this.d--;                                 // 累计建造总数 -1
}
```

### 第4层: 实际每秒收入

```
原始值:   s.g = Σ cy()                       (每40帧的显示值)
帧率:     60 fps                              (gameFPS, 服务端验证)
显示窗口: 40 帧                               (ECONOMY_DISPLAY_FRAMES)
收入倍率: ay.h                                (服务器 /income 命令, 默认1.0)

公式: income/s = s.g × (60 / 40) × ay.h × gameSpeed
               = s.g × 1.5 × 收入倍率 × 游戏速度

示例 (2.5x 速度, ay.h=1.0):
  CC单独:         18 × 1.5 × 2.5 = 67.5/s
  3×extractorT1:  24 × 1.5 × 2.5 = 90.0/s
  CC+3T1:         42 × 1.5 × 2.5 = 157.5/s
```

---

## 全部收入单位 (24个, .ini交叉验证)

| 单位 | cy() | @1.0x /s | @2.5x /s | .ini 来源 |
|------|------|----------|----------|----------|
| commandCenter | 18 | 27.0 | **67.5** | d.e.cy() |
| extractorT1 | 8 | 12.0 | 30.0 | extractor.ini |
| extractorT2 | 12 | 18.0 | 45.0 | extractorT2.ini |
| extractorT3 | 20 | 30.0 | 75.0 | extractorT3.ini |
| extractorT3_overclocked | 30 | 45.0 | 112.5 | overclocked.ini |
| extractorT3_reinforced | 20 | 30.0 | 75.0 | reinforced.ini |
| fabricatorT1 | 2 | 3.0 | 7.5 | fabricatorT1.ini |
| fabricatorT2 | 7 | 10.5 | 26.2 | fabricatorT2.ini |
| fabricatorT3 | 14 | 21.0 | 52.5 | fabricatorT3.ini |
| experimentalSpider | 18 | 27.0 | 67.5 | exp_spider.ini |
| experimentalGunship | 30 | 45.0 | 112.5 | exp_gunship.ini |
| experimentalDropship | 4 | 6.0 | 15.0 | exp_dropship.ini |
| experimentalCarrier | 8 | 12.0 | 30.0 | exp_carrier.ini |
| modularSpider | 30 | 45.0 | 112.5 | modular_spider.ini |
| modularSpider_fabricator | 5 | 7.5 | 18.75 | fabricator.ini |
| modularSpider_fabricatorT2 | 10 | 15.0 | 37.5 | fabricatorT2.ini |
| combatEngineer | 1 | 1.5 | 3.75 | combat_engineer.ini |
| mechEngineer | 1 | 1.5 | 3.75 | mech_engineer.ini |
| bugExtractor | 10 | 15.0 | 37.5 | bug_extractor.ini |
| bugExtractorT2 | 18 | 27.0 | 67.5 | bug_extractorT2.ini |
| bugGenerator | 2 | 3.0 | 7.5 | bug_generator.ini |
| bugGeneratorN | 6 | 9.0 | 22.5 | bug_generator.ini |
| bugGeneratorNT2 | 18 | 27.0 | 67.5 | bug_generator.ini |
| bugGeneratorT2 | 6 | 9.0 | 22.5 | bug_generatorT2.ini |

---

## 建造系统

### 完成判定 — `am.cm` 字段

```
am.cm (float) — Construction Multiplier (建造进度倍率)
  初始值: 1.0   (非建筑单位 — 视为"已完成")
  建造中: < 1.0  (正在建造, 不贡献收入, 受1.75×伤害)
  完成:   >= 1.0 (建造完成, 开始贡献收入)
```

### 进度更新 — `am.r(float)`

```java
// am.java:2087
public void r(float f2) {        // f2 = 新的建造进度值
    if (f2 >= 1.0f) {            // 建造完成!
        if (this.cm < 1.0f) {    // 之前未完成 → 转换
            n.b(this);           // 注销旧状态 (不含收入)
            this.cm = 1.0f;
            n.c(this);           // ★ 注册新状态 (s.a → 加收入到 s.g)
        }
    } else {
        if (this.cm >= 1.0f) {   // 降级: 完成→未完成
            n.b(this);           // 注销 (s.b → 扣收入)
            this.cm = f2;
            n.c(this);           // 注册为未完成 (不加收入)
        } else {
            this.cm = f2;        // 仅更新进度
        }
    }
}
```

### 建造进度 — `units.d.j` (BuildProgress)

```
extends gameFramework.bq (游戏对象基类)

字段:
  a (int)       — 建造槽位号
  b (float)     — buildSpeed (建造速度, 来自 .ini)
  c (d.b)       — ResourceComponent (建造消耗资源)
  d (d.b)       — ResourceComponent2 (额外消耗)
  e (custom.h)  — ★ 要建造的自定义单位类型
  f (boolean)   — 是否已完成
  g (as)        — 目标 UnitType 枚举
  h (PointF)    — 建造目标位置坐标
  i (am)        — 建造者单位 (正在执行建造的单位)
  j (a.c)       — 动作类型 (build)
  m (float)     — 当前建造进度 (0.0 ~ 1.0)
  n (double)    — ★ 累积建造进度 (核心字段 d.j.n)

进度累积:  n += b × delta_ms
完成判定:  n >= 1.0 → f = true → 调用 am.r(1.0f)
```

### 工厂单位 — `units.h.e` (Factory)

```
extends h.f (工厂基类)

静态字段:
  j (a.s)       — 建造动作定义 (static final)
  k (a.s)       — 取消动作定义 (static final)
  l (ArrayList) — 可建造单位列表 (static, 来自 .ini [action_build_*])

方法:
  L()           — ★ 更新建造队列 (检查队列/开始建造)
  s(float)      — 设置队列进度
  a(float)      — 建造tick更新
  F() → bool    — 是否可建造 (资源足够?)
  G() → float   — 获取建造速度
  H() → float   — 获取最大建造进度
  f() → ar      — 获取单位等级 (tier)
```

### 建造队列 — `game.t` (BuildQueue)

```
a (p[]) — 静态队列槽位数组
b (p[]) — 实例队列
c (int) — 当前队列长度

a(p) → bool — 添加/移除建造任务
```

### 建造参数 — `gameFramework.d.f` (BuildParams)

```
a (float)  — maxProgress (最大进度: 工厂280, 其他330)
b (bool)   — 完成标志
t (float)  — 当前进度
u (bool)   — 暂停标志

常量:
  工厂建造: maxProgress=280.0, speed=0.03/tick
  普通建造: maxProgress=330.0, speed=0.10/tick
```

### 建造时间公式

```
实际秒数 = maxProgress / (buildSpeed × FRAME_RATE × speedMultiplier)

工厂: 280 / (0.03 × 60 × 2.5) ≈ 62s
普通: 330 / (0.10 × 60 × 2.5) ≈ 22s


```

### 未完成建筑惩罚

```
伤害倍率: 1.75×  (am.a: if cm < 1.0f → damage *= 1.75f)
收入:     0      (s.a: cm < 1.0f 时不累加 cy())
护盾:     正常   (不受 cm 影响)
```

---

## 升级系统

### 继承覆盖 (非累加)

```
引擎流程 (extractorT1 → extractorT2):
  1. extractorT2 建造完成 → am.r(1.0f)
  2. s.b(extractorT1) → s.g -= 8, s.d--
  3. extractorT1 状态改为 'replaced'
  4. s.a(extractorT2) → s.g += 12, s.d++

结果: s.g 从 18+8=26 变为 18+12=30 (覆盖, 非累加)

花费: T1 700 + T2 升级费 1400 = 2100 (累加)
收入: T1的8 被 T2的12 替换 (覆盖)
HP:   旧建筑移除, 新建筑满HP (覆盖)
```

### 升级链

```
extractorT1 → extractorT2 → extractorT3
                           → extractorT3_overclocked
                           → extractorT3_reinforced
fabricatorT1 → fabricatorT2 → fabricatorT3
bugExtractor → bugExtractorT2
bugGenerator → bugGeneratorT2
bugGeneratorN → bugGeneratorNT2
modularSpider_fabricator → modularSpider_fabricatorT2
```

---

## 退款系统

### 取消建造 (stopOrUndo)

```
触发: e.g = true (Command.stopOrUndo 标志)
退款: 100% 建造费用

字节码 (y.java:2236):
  b6.a((am)this, 1.0, true)    // 1.0 = 100% 退款

流程:
  1. 单位从建造队列移除 (pending_builds)
  2. 资金返还: n.o += buildCost
  3. s.d--, s.f-- (累计建造-1, 未完成-1)
```

### 回收建筑 (reclaim)

```
触发: av.reclaim (武器类型序号 6)
退款: 80% 建造费用

字节码 (y.java:2243):
  b6.a((am)this, (double)0.8f, true)    // 0.8 = 80% 退款

额外退款 (已完成的建筑, y.java:2245):
  if (cm >= 1.0f) b3.a((am)this, (double)0.8f, true);

流程:
  1. 单位从 active_buildings 移除
  2. 资金返还: n.o += buildCost × 0.8
  3. s.g -= cy(), s.d-- (收入率扣除, 累计建造-1)
```


---

## 收入倍率配置

### 服务端

```kotlin
// LinkGameServerData.kt
override var income: Float
    set(value) { GameEngine.netEngine.ay.h = value }
    get() = GameEngine.netEngine.ay.h

// BeanServerConfig.kt
val defIncome: Float = 1f     // 默认收入倍率 1.0

// 命令: /income <value> → 直接设置 ay.h
```

### 引擎端

```java
// n.java:949-954
public final float D() {                    // 收入倍率获取
    l l2 = l.B();                           // 全局状态
    if (l2.O()) {                           // 多人游戏?
        return l2.bX.ay.h;                  // ★ 返回服务端设置的倍率
    }
    return 1.0f;                            // 单人游戏默认 1.0
}
```

### y.i() 字节码补充 (来自 GAME_ENGINE_REFERENCE Ch68)

```java
// 引擎内部计时器: y.cg (float) 累积
void i(float delta, int idx) {
    y.cg += delta;                      // 累加帧时间
    if (y.cg >= 180.0) y.cg -= 360.0;   // 半周期回绕
    if (y.cg < -180.0) y.cg += 360.0;
    
    if (y.bm()) {                       // 单位存活检查
        int count = y.bl();             // 产出数量
        for (int i = 0; i < count && i < idx; i++) {
            resource = y.cL[i];         // 资源组件
            resource.a += delta;        // 累加时间
            if (resource.a >= 180.0) {
                // 触发一次资源产出
                // 产出量 = generation_credits / generation_delay
            }
        }
    }
}
// 360 tick 周期 = 12秒 @30fps (引擎内部)
// 但实际入账通过 d.b.a() → y.ab → n.o 路径
```

### 游戏速度对收入的影响

```
游戏速度 (gameSpeed): 存储在快照 gameSetup 中 (+22 字节, float32 BE)
  默认: 2.5 (标准多人)
  范围: 0.1 ~ 100.0

收入 = s.g × (60/40) × ay.h × gameSpeed
```

### 建造速度常量 (来自 REVERSE_ENGINEERING_COMPLETE)

```
工厂建造:   buildMax=280.0, buildSpeed=0.03/tick
普通建造:   buildMax=330.0, buildSpeed=0.10/tick
CC建造:     buildMax=280.0, buildSpeed=0.03/tick
```

### 回收冷却 (来自 GAME_RUNTIME_LOGIC)

```
回收前置条件 (y.i() offset +27, av.g=ordinal 6):
  1. 目标存在
  2. 目标HP > 0
  3. 目标未死亡 (!bV)
  4. 目标未在运输中 (!bL)
  5. 目标不是己方队伍 (r != own.r)
  6. 冷却 ≥ 100 ticks (~3.3s @30fps)
  
满足后调用: y.g(target, LV10) [440B]
```

### 经济历史采样间隔 (来自 GAME_ENGINE_REFERENCE)

```
bg.b() 动态采样:
  游戏时间 < 1分钟:   每 5秒 采样
  游戏时间 < 30分钟:  每 60秒 采样
  游戏时间 < 60分钟:  每 30秒 采样
  游戏时间 ≥ 60分钟:  每 15秒 采样
```

### 反作弊蜜罐 (来自 REVERSE_ENGINEERING_SUMMARY)

```
n.d(float) [30B]      — 资金修改蜜罐, 0个调用者
n.p(double)           — 始终 dconst_0, 0次读取
e.q(short)            — 序列化但 0次 putfield
d.e.k(float=20.0)     — CC 上的死字段, 从未读取
```
