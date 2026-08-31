# Rusted Warfare v1.15 — 单位完整生命周期源码逆向
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 从创建到死亡: 构造→地图放置→每帧更新→死亡8步清理→GC
>
> 关键文件: `UnitInstance.java`(2215行), `UnitRegistry.java`(枚举), `PlayerState.java`, `TeamUnitTracker.java`

---

## 1. 创建流程

### 1.1 入口: UnitRegistry 枚举 (ar)

每个内置单位类型是 `ar$N` 枚举常量，实现工厂方法:

```java
// ar$1.java (CommandCenter) line 23
public am a(boolean preview) {
    return new g(preview);  // g extends UnitInstance
}
```

`preview` 参数:
- `false` → 真实地图单位 (设置 isOnMap=true, 注册到全局列表)
- `true` → UI预览缩略图 (不注册)

### 1.2 构造函数链

```
UnitInstance(boolean preview) [line 580]:
├── super(preview)                  → ay → az → am 构造链
├── this.bS()                       → 初始化炮塔目标数组
├── if (!preview):
│   ├── this.isOnMap = true
│   ├── bE.a(this)                  → 添加到全局单位集合
│   └── a.a(this)                   → 添加到辅助追踪列表
├── this.bz = l.B().by              → 记录创建时的 game tick
└── this.dz = this.r()              → 缓存 UnitTypeHandle 引用
```

### 1.3 地图放置: PlayerState.c(am) (line 1523)

```java
PlayerState.c(unit):
├── if (unit.bX != null && !unit.bY && unit.bL && !unit.bV):
│   ├── unit.bY = true              → 标记已注册
│   ├── team.T.a(unit)              → TeamUnitTracker 登记
│   └── unit.di()                   → 单位添加回调
```

### 1.4 TeamUnitTracker 登记

```java
TeamUnitTracker.a(unit):
├── completedCount++ 或 buildingCount++
├── incomeRate += unit.cy()         → 累加收入贡献
├── totalBuilt++
├── 更新资源流 (5种资源类型)
└── 更新容量占用
```

---

## 2. 每帧更新

### 2.1 更新入口

每帧通过 `GameScreen.a(f2)` → `for-each obj: w.a(f2)` 调用 `UnitInstance.a(f2)`。

### 2.2 UnitInstance.a(float) 核心更新

```
a(f2):
├── [死亡检查] if (isDead || currentHp <= 0) → 跳过
├── [建造进度] if (buildProgress < 1.0):
│   ├── 累加建造速度 × dt
│   └── if (buildProgress >= 1.0) → 建造完成
├── [HP回复] if (repairProgress > 0):
│   └── 回复 HP (受 maxHp 限制)
├── [护盾回复] shieldRegenTimer 管理 + 护盾吸收
├── [低HP特效] if (currentHp < maxHp * 0.33):
│   └── 生成烟雾/火花特效
├── [动画] hpChangeAnimation 衰减
└── [死亡触发] if (currentHp <= 0) → ch() → bv()
```

---

## 3. 死亡序列

### 3.1 完整调用链

```
ch() [line 1278]          ← 检查 HP≤0 触发
│
├── bv() [line 1327]      ← 死亡序列入口
│   ├── bu()              ← 核心死亡操作
│   ├── e()               ← 工厂弹出检查
│   └── a()               ← 完全清理
│
└── OR:
    ci() [line 1317]      ← 快速击杀 (跳过工厂弹出，用于核弹)
    ├── bu()
    └── a()
```

### 3.2 bu() — 核心死亡操作 (line 1296, 8步)

```
1. l2.bS.l(this)          → 从 HUD 选择中移除
2. n.a(this)              → 从 TeamUnitTracker 注销
3. bE.remove(this)        → 从全局单位集合移除
4. this.isDead = true     → 标记死亡
5. this.deathTimestamp = l2.by  → 记录死亡时间
6. if (currentHp > 0) currentHp = 0  → 钳制HP
7. 清除所有炮塔目标       → turretTargets[i].j = null
8. l2.cc.a(this)          → 更新空间网格索引
```

### 3.3 e() — 工厂弹出 (line 1287)

```java
public boolean e() {
    l2.bR.b(this.posX, this.posY, this.eq);  // 死亡粒子特效
    return false;  // 基类默认 false
}
```

工厂子类覆盖为 `true` — 弹出内部单位后延迟完全清理。

### 3.4 a() — 完全清理 (line 593)

```java
public void a() {
    n.a(this);              // 再次从队伍追踪器移除
    if (this.isOnMap) {
        bE.remove(this);    // 再次从全局集合移除
        a.b(this);          // 从辅助追踪器移除
    }
    l2.bS.l(this);          // 再次从HUD移除
    super.a();              // 父类清理
}
```

### 3.5 替代路径

| 入口 | 用途 | 跳过 |
|------|------|------|
| `bv()` | 正常死亡 | 无 |
| `ci()` | 快速击杀 (核弹/删除指令) | e() 工厂弹出 |
| `cj()` | 标记待死 (设置 HP=-1) | 全部 — 下帧 ch() 触发 |

---

## 4. 伤害系统

### 4.1 伤害应用: a(attacker, rawDamage, damageType) (line 1220)

```
1. 建造中惩罚:
   if (buildProgress < 1.0) rawDamage *= 1.75

2. 护盾吸收:
   if (shieldRegenTimer == 0 && currentShield > 0):
       shieldDamage = rawDamage * shieldMultiplier
       overflow = rawDamage * bleedThrough
       currentShield -= shieldDamage

3. HP伤害:
   currentHp -= overflow
   lastDamageTime = currentTick
   lastAttacker = attacker

4. 死亡触发:
   if (currentHp <= 0) → ch() → bv()
```

### 4.2 治疗: b(healer, amount, healType) (line 1179)

```
1. 护盾回复 (优先):
   if (currentShield < maxShield):
       currentShield += amount * shieldHealMultiplier

2. HP回复:
   if (currentHp < maxHp):
       currentHp += amount * hullHealMultiplier
```

---

## 5. 继承层级

```
am                          ← 序列化基类
└── az                      ← 游戏对象基类
    └── ay                  ← 精灵/尺寸基类
        └── UnitInstance    ← ★ 所有单位的抽象基类 (2215行)
            ├── w           ← 可移动单位基类
            │   ├── j       ← 地面单位
            │   │   ├── b           ← 建造者
            │   │   ├── Factory     ← 工厂 (930行)
            │   │   └── e/o/etc.    ← 坦克/火炮等
            │   ├── h               ← 悬浮单位
            │   └── i               ← 实验单位 (custom)
            ├── x                   ← 不可移动基类
            │   ├── u               ← 定时炸弹
            │   └── y               ← 建筑基类
            │       ├── d/g         ← 指挥中心
            │       ├── d/m         ← 工厂外壳
            │       └── Factory     ← (也继承自 j)
            ├── al                  ← 飞行器
            └── custom.j            ← Mod 自定义单位 (4699行)
```

---

## 6. 全局容器

| 容器 | 类型 | 位置 | 用途 |
|------|------|------|------|
| `bE` (static) | utility.u | UnitInstance | 地图上所有单位 |
| `a` (private static) | 追踪列表 | UnitInstance | 辅助追踪 |
| `teamTracker` (per-team) | game.s | PlayerState | 队伍统计 |
| `cc` (SpatialGrid) | units.f.c | GlobalState | 空间索引 |
| `bS.bZ` (HUD选择) | utility.u | gameFramework.f.g | 已选择单位 |

---

## 7. 关键常量

| 常量 | 值 | 来源 |
|------|-----|------|
| 建造中伤害惩罚 | ×1.75 | UnitInstance.a() line 1224 |
| 低HP阈值 | 33% maxHp | UnitInstance line 1129 |
| 建造完成阈值 | buildProgress ≥ 1.0 | UnitInstance line 686 |
| 最大玩家槽位 | 10 | PlayerState.c |
| 默认起始资金 | 4000.0 | PlayerState.o |

---

## 8. 无对象池

**重要**: Rusted Warfare 不使用对象池。单位通过 `new` 创建，死亡后移除所有引用，由 Java GC 回收。

`GlobalState.bU` 被错误标记为 "recycler"，实际是 **PathEngine** (路径查找引擎)。

单位创建: `new SpecificUnitType(false)` 在 `ar$N.a(false)` 中
单位销毁: `bu()` → 移除引用 → 不可达 → GC
