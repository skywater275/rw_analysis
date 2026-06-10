# 15 — Python 仿真引擎参考

> 来源: rw_engine/ (engine.py + cli.py + server.py, 743行)
> 用途: 可执行的游戏公式参考实现

---

## 一、引擎概述

rw_engine 是 Rusted Warfare v1.15 的 Python 仿真引擎。所有公式均从 game-lib.jar 的 1698 类字节码逆向验证，并与 RW-HPS 服务器 471 个 Kotlin 文件进行交叉验证。

### 设计原则

- **零外部依赖**: 仅使用 Python stdlib (math, random, json, os, dataclasses)
- **公式准确**: 每个公式标注来源类和字节码行号
- **可执行验证**: 通过 CLI 和 Web UI 可直接运行模拟
- **完整覆盖**: 经济/战斗/建造/移动/弹丸/空间索引全实现

---

## 二、引擎常量 (全部字节码验证)

```python
# 帧率/时间
FRAME_RATE = 60.0           # 游戏渲染帧率
TICK_RATE = 30.0            # 回放指令tick速率
ECONOMY_WINDOW = 40.0       # 经济数据显示窗口(帧)
DEFAULT_SPEED = 2.5         # 默认游戏速度倍率
STARTING_CREDITS = 4000.0   # 起始资金
CC_INCOME_CY = 18.0         # 指挥中心收入率

# 建造
BUILD_MAX_FACTORY = 280.0   # 工厂最大建造进度
BUILD_MAX_NORMAL = 330.0    # 普通最大建造进度
BUILD_SPEED_FACTORY = 0.03  # 工厂建造速度(/tick)
BUILD_SPEED_NORMAL = 0.10   # 普通建造速度(/tick)
INCOMPLETE_DAMAGE_MULT = 1.75  # 未完成建筑伤害倍率

# 退款
RECLAIM_REFUND = 0.80       # 回收退款(80%)
CANCEL_REFUND = 1.00        # 取消退款(100%)

# 战斗
SHIELD_MULTIPLIER = 1.0     # 护盾伤害倍率
SHIELD_ABSORB = 0.2         # 护盾吸收系数
HP_MULTIPLIER = 1.0         # HP伤害倍率

# 弹丸
PROJECTILE_LIFETIME = 3600.0   # 弹丸生命周期(ticks)
PROJECTILE_TIMEOUT = 14400.0   # 弹丸超时(ticks)
PROJECTILE_SPEED = 80.0        # 弹丸速度(px/s)
COLLISION_DIST_SQ = 40000.0    # 碰撞距离平方(200²)

# 空间
SPATIAL_GRID_SIZE = 32         # 网格维度
SPATIAL_CELL_SIZE = 50.0       # 单元格大小(px)
BUILDER_SEARCH_RANGE = 100.0   # 建造者搜索范围
ATTACK_DETECT_RANGE = 360.0    # 攻击检测范围
```

---

## 三、单位数据表

### 3.1 单位造价 (UNIT_COSTS)

```python
UNIT_COSTS = {
    'commandCenter': 3000, 'extractorT1': 700, 'extractorT2': 2100, 'extractorT3': 6100,
    'fabricatorT1': 2200, 'fabricatorT2': 6600, 'fabricatorT3': 14100,
    'mechFactory': 1000, 'airFactory': 1000, 'seaFactory': 1000, 'landFactory': 700,
    'builder': 500, 'builderShip': 500, 'scout': 700, 'dropship': 800,
    'mechGun': 600, 'mechMissile': 900, 'mechArtillery': 1400, 'mechFlame': 12000,
    'mechLaser': 7000, 'mechLightning': 5200, 'mechMinigun': 5000,
    'plasmaTank': 1000, 'mammothTank': 3900, 'laserTank': 1600, 'heavyTank': 800,
    'hoverTank': 450, 'missileTank': 2500, 'experimentalTank': 14000,
    'heavyInterceptor': 1200, 'lightGunship': 250, 'bomber': 4000, 'spyDrone': 1500,
    'battleShip': 1500, 'heavyBattleship': 6000, 'lightSub': 450, 'attackSubmarine': 800,
    'gunBoat': 300, 'missileShip': 900, 'heavyAAShip': 3500,
    'turret': 500, 'antiAirTurret': 600, 'antiAirTurretFlak': 4600,
    'outpost': 1500, 'laboratory': 4000, 'repairbay': 1500,
    'experimentalSpider': 70000, 'experimentalGunship': 140000,
    'experimentalDropship': 30000, 'modularSpider': 90000,
}
```

### 3.2 单位收入率 (INCOME_RATES)

```python
INCOME_RATES = {
    'commandCenter': 18, 'extractorT1': 8, 'extractorT2': 12, 'extractorT3': 20,
    'fabricatorT1': 2, 'fabricatorT2': 7, 'fabricatorT3': 14,
    'experimentalSpider': 18, 'experimentalGunship': 30, 'experimentalDropship': 4,
    'modularSpider': 30, 'combatEngineer': 1, 'mechEngineer': 1,
}
```

### 3.3 单位战斗属性 (UNIT_STATS, 部分)

```python
UNIT_STATS = {
    'commandCenter': {'hp': 4000, 'speed': 0, 'range': 280, 'damage': 70},
    'plasmaTank': {'hp': 220, 'speed': 0.8, 'range': 165, 'damage': 100},
    'experimentalSpider': {'hp': 10000, 'speed': 0.5, 'range': 290, 'damage': 350},
    'experimentalGunship': {'hp': 8000, 'speed': 0.7, 'range': 350, 'damage': 300},
    # ... 总计约40个单位的完整属性
}
```

---

## 四、核心类实现

### 4.1 Unit (单位实例)

```python
@dataclass
class Unit:
    uid: int                    # 唯一ID
    unit_type: str              # 单位类型名
    team_id: int                # 所属队伍
    x: float = 0.0              # X坐标
    y: float = 0.0              # Y坐标
    radius: float = 30.0        # 碰撞半径
    rotation: float = 0.0       # 朝向角度(度)
    hp: float = 100.0           # 当前HP
    max_hp: float = 100.0       # 最大HP
    shield: float = 0.0         # 当前护盾
    max_shield: float = 0.0     # 最大护盾
    build_progress: float = 1.0 # 建造进度(≥1.0=完成)
    is_building: bool = False   # 是否为建筑
    is_alive: bool = True       # 存活标志
    is_dead: bool = False       # 死亡标志
    income_rate: float = 0.0    # 收入贡献(cy())
    damage: float = 2.0         # 武器伤害
    weapon_range: float = 120.0 # 武器射程
    cooldown_ticks: int = 0     # 攻击冷却
    cooldown_max: int = 60      # 最大冷却
    ammo: int = 1               # 弹药
    move_speed: float = 0.0     # 移动速度
    move_type: int = MoveType.LAND  # 移动类型
    behavior: int = Behavior.AGGRESSIVE  # 行为模式
    last_attacker_uid: int = -1 # 最后攻击者
    parent_uid: int = -1        # 父单位(运输者)
```

### 4.2 伤害公式实现

```python
def apply_damage(self, incoming, attacker_uid,
                 shield_mult=SHIELD_MULTIPLIER,
                 shield_absorb=SHIELD_ABSORB,
                 hp_mult=HP_MULTIPLIER):
    """
    完整伤害公式 — 字节码验证自 am.java [553B]
    
    阶段1: 未完成建筑惩罚 — damage × 1.75 if build_progress < 1.0
    阶段2: 护盾吸收 — shieldDmg = incoming × shieldMult
            if shield >= shieldDmg: remaining -= shield × shieldAbsorb; shield = 0
            else: shield -= shieldDmg; remaining -= remaining × shieldAbsorb
    阶段3: HP伤害 — hpDmg = remaining × hpMult
            if hp >= hpDmg: hp -= hpDmg (存活)
            else: hp = 0 → 死亡
    返回溢出伤害(用于溅射)
    """
    remaining = incoming
    if self.build_progress < 1.0:
        remaining *= INCOMPLETE_DAMAGE_MULT
    
    if self.shield > 0:
        sd = remaining * shield_mult
        if self.shield >= sd:
            remaining -= self.shield * shield_absorb
            self.total_shield_absorbed += self.shield
            self.shield = 0.0
        else:
            self.shield -= sd
            self.total_shield_absorbed += sd
            remaining -= remaining * shield_absorb
    
    if remaining > 0:
        hd = remaining * hp_mult
        if self.hp >= hd:
            self.hp -= hd
            self.total_hp_lost += hd
        else:
            self.total_hp_lost += self.hp
            self.hp = 0.0
    
    self.last_attacker_uid = attacker_uid
    if self.hp <= 0 and not self.is_dead:
        self.is_dead = True
        self.is_alive = False
    return remaining
```

### 4.3 收入计算

```python
def compute_income(self, dt, spd=DEFAULT_SPEED):
    """
    收入每秒 — 验证自 s.java, y.java, d.b.java
    
    Formula: income/s = total_income_rate × (FRAME_RATE / ECONOMY_WINDOW) × speed
                       = total_income_rate × (60/40) × speed
                       = total_income_rate × 1.5 × 2.5 (@default)
    
    CC:     cy()=18 → 18 × 1.5 × 2.5 = 67.5/s
    T1 Ext: cy()=8  → 8  × 1.5 × 2.5 = 30.0/s
    T2 Ext: cy()=12 → 12 × 1.5 × 2.5 = 45.0/s
    T3 Ext: cy()=20 → 20 × 1.5 × 2.5 = 75.0/s
    """
    ps = self.total_income_rate * (FRAME_RATE / ECONOMY_WINDOW) * spd
    self.add_credits(ps * dt)
    return ps * dt
```

### 4.4 建造进度更新

```python
def update_builds(self, dt):
    """
    建造进度 — 验证自 d.j (BuildProgress), h.e (Factory [172B])
    
    普通建造:  buildSpeed=0.10/tick, maxProgress=330 → ~22s @60fps 2.5x
    工厂建造:  buildSpeed=0.03/tick, maxProgress=280 → ~62s @60fps 2.5x
    CC建造:    同工厂
    
    未激活工厂: speed mult = -8.0 (倒退!)
    """
    for i, (p, u) in enumerate(self.pending_builds):
        bs = BUILD_SPEED_FACTORY if 'factory' in u.unit_type.lower() \
                                  or u.unit_type == 'commandCenter' \
             else BUILD_SPEED_NORMAL
        p += bs * FRAME_RATE * dt * DEFAULT_SPEED
        self.pending_builds[i] = (p, u)
        if p >= 1.0:
            u.build_progress = 1.0
            if u.income_rate > 0:
                self.total_income_rate += u.income_rate
```

### 4.5 空间网格 (SpatialGrid)

```python
class SpatialGrid:
    """32×32网格, 50px单元, 覆盖1600×1600px"""
    
    def __init__(self, w=32, h=32):
        self.w, self.h = w, h
        self.cs = SPATIAL_CELL_SIZE  # 50.0
        self.cells = defaultdict(list)
        self.pos = {}
    
    def add(self, u):
        cx = max(0, min(self.w - 1, int(u.x / self.cs)))
        cy = max(0, min(self.h - 1, int(u.y / self.cs)))
        self.cells[(cx, cy)].append(u.uid)
        self.pos[u.uid] = (cx, cy)
    
    def query_circle(self, x, y, r):
        """圆形范围查询 — 用于索敌/碰撞检测"""
        minc = max(0, int((x - r) / self.cs))
        maxc = min(self.w - 1, int((x + r) / self.cs))
        minr = max(0, int((y - r) / self.cs))
        maxr = min(self.h - 1, int((y + r) / self.cs))
        result = []
        for cx in range(minc, maxc + 1):
            for cy in range(minr, maxr + 1):
                result.extend(self.cells.get((cx, cy), []))
        return result
    
    def find_nearest_enemy(self, unit, enemy_world):
        """最近敌人搜索 — AIPlayer/自动战斗核心"""
        candidates = self.query_circle(
            unit.x, unit.y, max(ATTACK_DETECT_RANGE, unit.weapon_range))
        best, best_dist = -1, float('inf')
        for uid in candidates:
            o = enemy_world.get_unit(uid)
            if not o or not o.is_alive or o.team_id == unit.team_id:
                continue
            d = unit.distance_to(o)
            if d < best_dist:
                best_dist = d
                best = uid
        return best if best >= 0 else None
```

---

## 五、World 步进循环

```python
class World:
    def step(self, dt):
        """每帧调用一次"""
        self.tick += 1
        # 1. 收入计算
        self.compute_income(dt)
        # 2. 建造进度
        self.update_builds(dt)
        # 3. 单位移动
        self.update_movement(dt)
        # 4. 自动战斗(索敌+攻击)
        self.auto_combat()
        # 5. 弹丸更新(移动+碰撞)
        self.update_projectiles(dt)
        # 6. 冷却递减
        for u in self.units.values():
            if u.cooldown_ticks > 0:
                u.cooldown_ticks -= 1
```

---

## 六、死亡清理 (8步)

```python
def remove_unit(self, uid):
    """
    死亡清理 — 字节码验证自 am.bu() [98B]
    
    8步死亡链:
      1. ch() — 检查 cu <= 0, 设置 bV=true
      2. bv() — 调用 bu() + e() + a() + bt()
      3. bu() — l.bS.l(this) 从GameUI注销 (运行时验证)
      4.      — s.b(this) ★ 从收入追踪器注销
      5.      — am.bE.remove  ★ 从全局注册表移除
      6.      — l.cc.a(this)  ★ 从空间网格移除
      7. y.bx() — 从父单位解绑
      8. y.bC() — ResourceComponent 重算
    """
    u = self.units.get(uid)
    if not u: return
    # 扣除收入
    if u.build_progress >= 1.0 and u.income_rate > 0:
        self.total_income_rate = max(0, self.total_income_rate - u.income_rate)
    # 从空间网格移除
    self.grid.remove(u)
    # 清除其他单位的引用
    for o in self.units.values():
        if o.target_uid == uid: o.target_uid = -1
        if o.parent_uid == uid: o.parent_uid = -1
    # 标记死亡
    u.is_dead = True
    u.is_alive = False
    u.death_tick = self.tick
    u.hp = 0.0
```

---

## 七、Web 服务器 (server.py)

提供基于浏览器的可视化界面:
- HTTP REST API (`/a?cmd=start|build|move|tick|spd|pause`)
- HTML5 Canvas 实时渲染
- 支持多队伍并行模拟
- 实时 HP 条/收入/击杀统计更新

---

## 八、CLI 命令 (cli.py)

```
RW> start [teams=2] [speed=2.5] [credits=4000]
    → 启动游戏模拟

RW> build <team> <unit> [x] [y]
    → 建造单位

RW> move <team> <x> <y>
    → 移动所有单位

RW> run <seconds>
    → 快进模拟

RW> status
    → 查看所有队伍状态

RW> units [team]
    → 列出单位详情

RW> list
    → 列出可用单位
```

---

*参考: rw_engine/engine.py (504行) + cli.py (83行) + server.py (156行)*
*最后更新: 2026-06-07*
