# 02 — 战斗系统

> 逆向度: 100% | 核心类: `am`(UnitInstance), `bg`(StatsManager/l.bY), `bo`(StatsData), `a.g`(CombatMaintenance), `a.i`(Projectile)
> 验证: am.java 字节码 + RW-HPS + 30回放

---

## 类名意义

| 混淆名 | 完整路径 | 实际含义 |
|--------|---------|---------|
| `am` | `game.units.am` | **UnitInstance** — 单位实例 (HP/位置/状态) |
| `bo` | `gameFramework.bo` | **StatsData** — 单条统计数据 (击杀/损失) |
| `bg` | `gameFramework.bg` | **StatsManager** — 统计管理器 (l.bY) |
| `a.g` | `game.a.g` | **CombatMaintenance** — 战斗维护节点 |
| `a.c` | `game.a.c` | **CombatManager** — 战斗管理器 |
| `a.i` | `game.a.i` | **Projectile** — 弹丸 (状态机+定时器) |
| `av` | `game.units.av` | **WeaponType** — 武器类型枚举 (17种) |
| `ao` | `game.units.ao` | **MovementType** — 移动类型枚举 (8种) |

---

## HP/护盾系统

### 字段定义

```
am.cu (float) — Current Unit HP       (当前生命值)
am.cv (float) — Current Vitality      (最大生命值)
am.cx (float) — Current Shield        (当前护盾值)
am.cz (float) — Shield regen timer    (护盾再生计时器, >0 时护盾不吸收伤害)
am.cj (float) — Collision radius      (碰撞半径)
am.cm (float) — Construction Multiplier (建造进度, <1.0 时受 1.75× 伤害)
```

### HP 比率

```java
// am.java:734-735
public float x() {                   // HP比率 (用于血条显示)
    if (this.cu < this.cv)
        return this.cu / this.cv;    // 0.0 ~ 1.0
    return -1.0f;                    // -1 表示满血 (不显示血条)
}
```

### 低HP阈值

```java
// am.java:1173
if (this.cu < this.cv * 0.33f)       // HP < 33%
    → 触发烟雾/火焰特效
    烟雾计时器: > 10秒 → 重置
    火焰计时器: > 30秒 → 重置
```

---

## 伤害计算 — `am.a(am, float, f)` 完整字节码还原

### 方法签名

```java
// am.java:1264
public float a(am attacker, float rawDamage, f damageModifiers) {
    // attacker         — 攻击者 (可为 null)
    // rawDamage        — 原始伤害值
    // damageModifiers  — 伤害修正 (null = 默认 1.0)
    // 返回值           — 溢出伤害 (>0 表示伤害超出了目标HP)
```

### 第1阶段: 建造惩罚

```java
if (this.cm < 1.0f) {                // 未完成的建筑
    rawDamage *= 1.75f;              // ★ 1.75× 伤害惩罚
}
```

### 第2阶段: 伤害修正系数

```java
float shieldMult  = 1.0f;           // f3.ak — 护盾伤害倍率
float shieldAbsorb = 1.0f;          // f3.al — 护盾吸收率
float hpMult      = 1.0f;           // f3.am — HP 伤害倍率

if (damageModifiers != null) {
    shieldMult   = damageModifiers.ak;
    shieldAbsorb = damageModifiers.al;
    hpMult       = damageModifiers.am;
}
```

### 第3阶段: 护盾吸收

```java
float remaining = rawDamage;
float totalDealt = 0.0f;

if (this.cz == 0.0f && this.cx > 0.0f) {  // 护盾再生完毕 且 有护盾
    float effectiveDamage = remaining * shieldMult;
    
    if (this.cx < effectiveDamage) {       // 护盾破碎
        remaining -= this.cx * shieldAbsorb;
        totalDealt += this.cx;
        this.cx = 0.0f;                    // 护盾归零
    } else {                               // 护盾吸收
        this.cx -= effectiveDamage;
        totalDealt += effectiveDamage;
        remaining -= remaining * shieldAbsorb;
    }
}
```

### 第4阶段: HP 伤害

```java
if (remaining > 0.0f) {
    float effectiveDamage = remaining * hpMult;
    float savedHP = this.cu;               // ★ 保存 (清零前)
    
    if (savedHP < effectiveDamage) {       // 溢出伤害
        remaining -= savedHP;              // 返回溢出值
        this._setHP(0.0f);                 // HP = 0
        this.hpAnim += savedHP;            // 伤害动画
    } else {                               // 正常伤害
        this._setHP(savedHP - effectiveDamage);
        remaining -= effectiveDamage;      // 剩余 = 0 (无溢出)
        this.hpAnim -= effectiveDamage;
    }
}
```

### 第5阶段: 记录 + 检查死亡

```java
this.lastDamageTime = currentTime;          // bs = l2.by
this.lastAttacker = attacker;              // bt = am2
this.checkDeath();                         // ch() → cu<=0 && !bV → bv()
return remaining;                          // 溢出伤害
```

### 伤害计算示例

```
场景: T1坦克 (HP=210) 攻击 未完成提取器 (HP=800, cm=0.5)
  原始伤害: 25
  建造惩罚: 25 × 1.75 = 43.75
  护盾:     无
  HP伤害:   800 - 43.75 = 756.25 (剩余)
  溢出:     0

场景: T1坦克攻击 有护盾单位 (HP=100, 护盾=30)
  原始伤害: 25
  护盾吸收: 25 × 1.0 = 25, 护盾=30≥25 → 护盾=5, 剩余=0
  HP伤害:   无 (护盾完全吸收)

场景: 实验炮艇 (伤害=300) 攻击 T1坦克 (HP=210)
  原始伤害: 300
  护盾:     无
  HP伤害:   210 < 300 → 溢出!
  HP=0,     溢出=300-210=90 (可继续伤害其他单位)
  坦克死亡
```

---

## 射击精度/散布 (来自 REVERSE_ENGINEERING_COMPLETE)

```
角度散布:  angle × 0.6 (最小 720)
距离偏移:  80% 概率 ±110px
散布范围:  80% 概率 ≥ 450px
```

## 能量系统 (来自 GAME_ENGINE_REFERENCE Ch86)

```
am.cB (float) — 当前能量
am.cE (int)   — 弹药计数

能量消耗: 每次攻击消耗, 从 .ini 的 energyPerShot
弹药消耗: 每次攻击 -1, 0 时无法攻击
```

## 维修 — `am.b(am, float, f)`

```java
float remaining = repairAmount;

// 第1阶段: 维修护盾
if (shield < maxShield) {
    deficit = maxShield - shield;
    repair = remaining * shieldRepairMult;
    if (deficit > repair) {
        shield += repair;
        remaining -= repair * 0.8f;        // 消耗 80% 维修量
    } else {
        shield = maxShield;
        remaining -= repair * 0.8f;
    }
}

// 第2阶段: 维修HP
if (remaining > 0 && hp < maxHP) {
    deficit = maxHP - hp;
    repair = remaining * hpRepairMult;
    if (deficit > repair) hp += repair;
    else hp = maxHP;
}

// 维修进度上限: am.cw ≤ am.cv × 2.0  (防止无限累积)
```

---

## 死亡链 — 6步清理

```
am.ch()                    ← 每次受伤后调用
  if (cu <= 0 && !bV):
    am.bv()                ← 死亡入口
      am.bu()              ← 核心死亡处理
        ├─ bS.l(this)      ← 1. 从地图/寻路系统注销
        ├─ n.a(this)       ← 2. 从队伍追踪器移除 (s.b → g-=cy, d--)
        ├─ bE.remove(this) ← 3. 从全局单位列表移除
        ├─ bV = true       ← 4. 设置死亡标志
        ├─ bW = time       ← 5. 记录死亡时间
        ├─ cu = 0          ← 6. 清零HP (保险)
        ├─ cL[].j = null   ← 7. 清除所有炮塔目标
        └─ cc.a(this)      ← 8. 通知 CombatManager
      e()? → 额外清理      ← (子类覆盖)
      bt() → 死亡回调      ← (子类覆盖)
```

---

## 弹丸物理 — `game.a.i`

### 状态机

```
  a (idle)    ──→  b (flying)  ──→  c (exploding)
  空闲            飞行中            爆炸中

状态转换:
  idle → flying:     首个 update_physics 调用
  flying → exploding: lifetime > 11000ms (自毁)
                     或碰撞到目标
```

### 定时器系统

```
lifetime (k):       累积时间, > 11000ms → 自毁爆炸
activeTimer (v):    活跃计时, > 6000ms → 失活 (不追踪新目标)
smokeTimer (i):     烟雾效果, 衰减到0后重置
fireTimer (j):      火焰效果, 衰减到0后重置为 100+(pid%15)
effectTimer (e):    特效计时, 衰减到0后重置为 270+(pid%15)
speedTimer (g):     速度修正, 衰减
accumulatedDelta:   累积时间, ≥ 50ms 时更新物理+状态

更新批次: 25ms (GameWorld 三层循环第一层)
```

### 爆炸范围

```
移动单位:  800px (PROJECTILE_EXPLOSION_RADIUS)
已部署单位: 540px (PROJECTILE_EXPLOSION_HEIGHT)
碰撞边距:  120px (PROJECTILE_COLLISION_MARGIN = U + 120)
```

---

## 战斗管理层

### CombatManager — `game.a.c`

```
职责: 管理所有单位的战斗状态

方法:
  a()       — 初始化战斗系统
  a(as)     — 按 UnitType 注册 (单位类型上线)
  a(y)      — 按 UnitType 实例注册 (具体单位加入)
  b()       — 战斗维护更新 (80ms 周期, GameWorld 第二层)
```

### CombatMaintenance — `game.a.g` (extends `a.h`)

```
职责: 单个单位的战斗维护 (索敌/攻击/追击)

字段:
  D (am)        — 管理的单位实例
  a(a.a, y)     — 创建战斗节点 (静态工厂)

方法:
  a(y)          — 初始化, 设置目标单位类型
  c()           — 战斗更新 (检查范围/攻击/冷却)
  e() → am      — 获取攻击者
  f() → am      — 获取目标
  h()           — ★ 执行战斗 (伤害计算 + 击杀判定)
  i() → ao      — 获取攻击者移动类型
  j() → ao      — 获取目标移动类型
  k()           — 额外战斗逻辑 (特殊武器/效果)
```

---

## 战斗统计 — 3层架构 (bg→bo→bn)

> 源码验证: `bg.java:116-133` + `d/d.java:343`(bI=实验单位) + `e/c.java:364`(dd=建筑)

### StatsManager — `bg` (l.bY)

全局统计入口, 持有 `bo[]` 数组(每玩家一个)。击杀时按 `killed.bI()`(实验) 和 `killed.dd()`(建筑) 分类记录。

### StatsRecord — `bo` (单玩家数据, 10计数器)

```
bo.c — unitsKilled         (击杀普通单位)    ← bg.java:130 else分支
bo.d — experimentalsKilled (击杀实验单位)    ← bg.java:125 bI()=true
bo.e — buildingsKilled     (摧毁建筑)        ← bg.java:128 dd()=true
bo.f — unitsLost           (损失普通单位)    ← bg.java:131 else分支
bo.g — experimentalsLost   (损失实验单位)    ← bg.java:126 bI()=true
bo.h — buildingsLost       (损失建筑)        ← bg.java:129 dd()=true
bo.k — timestamp           (时间戳, long)
bo.l — StatsHistory (bn)   (时间线数据)
```

> ⚠️ 修正: 旧版文档 d↔e 和 g↔h 字段映射颠倒 (基于服务端字段名推测), 已根据 bg.java 源码修正

### StatsManager 关键方法 — `bg`

```
bg.a(player) → bo          — 按玩家槽位获取 StatsRecord
bg.a(killer, killed, dmg)  — ★ 击杀记录 (根据 unit type 分类)
bg.b()                     — 周期采样 (自适应 1000-30000ms)
bg.c()                     — 游戏结束最终采样
```

---

## 死亡链 ★字节码完整还原

### 8步死亡流程

```
Step 1: am.ch() [21B] — 触发器
  if !bV && cu <= 0:
      bv()                         // 进入死亡序列

Step 2: am.bv() [20B] — 死亡序列入口
  bu()                            // 核心清理
  if !e(): a()                    // 可选: 资源释放
  bt()                            // 最终清理

Step 3: am.bu() [98B] — 核心清理
  l.bS.l(this)                    // 从工厂/生成器移除
  n.a(this)                       // ★ 从队伍追踪器注销(s.b)
  am.bE.remove(this)              // ★ 从全局单位注册表移除
  if cu > 0: cu = 0               // 强制HP归零
  for each cL[i]:                 // 清除移动参数
      cL[i].j = null              // (目标引用清空)
  l.cc.a(this)                    // ★ 从空间网格移除

Step 4: am.e() [26B] — 可选条件
  (检查单位是否有特殊死亡行为)

Step 5: am.a() [43B] — 资源释放
  (触发死亡时的资源/效果)

Step 6: am.bt() — 最终清理
  (释放引用/动画)

Step 7: y.bx() [80B] — 父单位解绑
  if cO != null && !cO.bV:
      if !b(cO):                  // 无法正常解绑?
          log "Deattach failed, forcing deattach"
          cO = null; cP = null    // 强制清除

Step 8: y.bC() [83B] — ResourceComponent 重算
  newRC = bE() (combat RC)
  oldRC = bD() (economic RC)  
  merged = merge(oldRC, newRC)
  n.b(this)                       // 注销旧状态
  dJ = merged
  n.c(this)                       // 注册新状态
```

### 死亡效果 — am.cX() [198B]

```
if l.bs != null && l.bs != this.bX && l.bs.k >= 0:
    create death effect (fw.d.a):
      d = this.r()           // 单位类型
      g = this.eo            // 死亡X坐标
      h = this.ep            // 死亡Y坐标
      n = false              // 不循环
      e = this.bX            // 所属队伍
      f = an.b               // 特效类型
      j = l.bs               // 目标玩家
      u = this.c_()          // 额外标志
      v = this               // 源单位
```

---

## 武器类型 — `av` (17种) ★字节码确认

```
从 av.class <clinit> (354B) 确认:

 0 = move              移动
 1 = attack            攻击
 2 = build             建造
 3 = repair            维修
 4 = loadInto          装载进入
 5 = unloadAt          卸载到位置
 6 = reclaim           回收
 7 = attackMove        攻击移动
 8 = loadUp            装载
 9 = patrol            巡逻
10 = guard             守卫
11 = guardAt           守卫位置
12 = touchTarget       接触目标
13 = follow            跟随
14 = triggerAction     触发动作
15 = triggerWhenInRange 范围内触发
16 = setPassiveTarget  设置被动目标
```

---

## 武器实例 — `au` (6KB, 14字段, 30方法) ★字节码确认

```
字段:
  a (av)       — 武器类型枚举
  b (as)       — 目标单位类型
  c (a.c)      — 动作类型
  d (int)      — 弹药计数
  e (float)    — 伤害值
  f (float)    — 射程
  g (long)     — 冷却计时器
  h (am)       — 当前目标单位实例
  i (fw.ab)    — ★ 弹丸管理器引用 (ProjectileManager)
  j (boolean)  — 是否激活
  k (float)    — 当前伤害
  l (float)    — 当前射程
  m (boolean)  — 是否就绪
  n (boolean)  — 是否已发射

关键方法:
  a(am)→void              — 设置目标单位
  b(am)→void              — 设置次要目标
  c()→void (150B)         — ★ 武器更新/发射 (convertUnitIds)
  e()→void (77B)          — 重置武器状态
  f()→boolean (86B)       — 检查冷却是否完成
  g()→float (27B)         — 获取当前伤害
  h()→float (27B)         — 获取当前射程
  a(au)→boolean (40B)     — 比较武器类型
  b(au)→boolean (96B)     — 详细比较 (含参数)
```

---

## 弹丸管理器 — `fw.ab` (6KB, 8字段, 10方法) ★NEW

```
字段:
  a (utility.m)    — 活跃弹丸列表
  b (boolean)      — 是否激活
  c/d (float)      — 碰撞检测参数
  e (int)          — 弹丸计数
  f (boolean)      — 标志
  g (utility.m)    — 待移除弹丸
  h (fw.aa)        — 所属对象 (final)

碰撞常量:
  0.4, 80.0, 160.0, 360.0, 3600.0, 14400.0, 40000.0, 160000.0

关键方法:
  a(y,au)→void             — 添加弹丸 (单位+武器)
  a(y)→void (23B)          — 按单位移除弹丸
  a()→void (120B)          — ★ 主更新: 移动弹丸/碰撞检测
  c()→void (630B)          — ★ 碰撞检测主循环
  a(utility.m,float,float,bool)→y (118B) — 检查碰撞
  a(float,float,bool)→utility.m (95B)    — 范围查询碰撞
```

---

## 移动类型 — `ao` (8种) ★字节码确认

```
从 ao.class <clinit> (165B) 确认:

  a = AIR              (空中)
  b = LAND             (地面) ★ 最常用
  c = HOVER            (悬浮)
  d = BUILDING         (建筑)  ← 修正: 不是两栖!
  e = NONE             (无移动)
  f = OVER_CLIFF       (越崖)  ← NEW!
  g = OVER_CLIFF_WATER (越崖水面) ← NEW!
  h = ROOT             (根植/固定) ← NEW!

重映射规则 (a(String,String)→ao, 121B):
  输入 "key" → 输出对应类型
  未知key → 错误 "' possible type: ... on key: ..."
```
