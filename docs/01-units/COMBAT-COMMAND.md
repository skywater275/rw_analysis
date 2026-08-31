# Rusted Warfare v1.15 — 战斗系统与指令管线源码逆向
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 关键文件: `CombatMain.java` (700行), `Projectile.java` (1221行), `WeaponAction.java`, `Command.java` (639行), `CommandController.java`
>
> 指令流: 创建 → 验证 → 排队 → 执行 → 单位分派

---

## Part A: 战斗系统

### 1. CombatMain — 战斗群组 AI

**文件**: `com/corrodinggames/rts/game/a/CombatMain.java` (700行)

管理一个战斗单位群组的 AI 行为。继承自 `AIUnitGroupBase` (h)。

#### 1.1 目标获取: `c()` (line 188)

```
遍历所有同队单位:
├── 过滤: 已死亡 (bV) / 忙碌 (bM,bN) / 已分配组 (aB != null)
├── 检查: 能到达目标 (R.h) / 可见 (R.i)
├── 根据组组成选择目标类型:
│   ├── 非防御组 (B=false): 优先空中单位
│   └── 防御组 (B=true): 优先地面单位
└── 将合格单位加入组 (this.a)
```

#### 1.2 状态机: `c(float dt)` (line 287)

```
定时器: l (保持), y (扫描), z (移动), p (防御)

每周期:
├── d() → 检查是否满员
├── c() → 目标获取 (扫描计时器到期)
├── 重新分配路径点:
│   ├── 到关联基地的随机位置
│   └── 或控制区域中心
├── e(dt) → AI 计划攻击
└── d(dt) → 防御行为
```

#### 1.3 防御行为: `d(float dt)` (line 381)

```
1. 找关联基地 (k), 如果没有则分配
2. 检查护盾: 如果控制单位护盾 < 50% → 撤退模式
3. 每个防守 tick:
   ├── 从基地拉取单位
   └── 发送拦截在范围内的敌人
```

#### 1.4 攻击行为: `e(float dt)` (line 448)

```
集结阶段:
├── 在目标附近集结 (80% 随机偏移)
└── 等待最多 17s

攻击阶段:
├── 分配目标
├── 检查是否能到达
├── 发出移动攻击指令
└── 11s 后或 <3 单位 → 解散攻击组
```

#### 1.5 撤退: `b(float dt)` (line 260)

```
1. e() → 获取最后攻击者 (6s 内造成伤害的单位)
2. 有攻击者 + 无防守单位:
   ├── 组有反击能力 → 反击
   └── 无反击能力 → 逃离 (计算远离攻击者的点 + 随机偏移)
```

---

### 2. Projectile — AI 建造策略节点 (1221行)

**文件**: `com/corrodinggames/rts/game/a/Projectile.java`

> 注意: 名字是反编译产物。这个类实际是 AI 建造策略规划器，不是游戏弹丸。

#### 2.1 AI 状态机

| 状态 (sourceY/j) | 含义 |
|------------------|------|
| `j.a` | 早期游戏 |
| `j.b` | 中期游戏 |
| `j.c` | 晚期游戏 |

状态转换: 如果超过 2s 无战斗单位 → 从 `j.a` 推进到 `j.c`

#### 2.2 建造优先级: `v()` (line 1114)

```
随机选择经济方向 (40% 军事 / 30% 经济 / 20% 建造者)
├── ao.b → 军事 (炮塔/工厂/战斗单位)
├── ao.f → 经济 (提取器/资源)
└── ao.d → 混合

权重:
├── 炮塔: <4个高优先级, 递减
├── AA: <3个 (全局计数)
├── 提取器: 0.80/0.35/0.20 (T1/T2/T3)
├── 工厂: 建造者 > 工厂数时
└── 时间因子: >15000 tick → 重防御
```

#### 2.3 目标选择: `c()` (line 204)

评估所有可用单位类型 (`as`)，计算适配度分数:
- 如果玩家某种单位少 → AI 更倾向建造
- 不同类型有不同权重函数
- `fw`~`fD` 字段存储自定义单位的额外权重
- 游戏时间 (`R.o`) 影响决策

#### 2.4 定期任务

| 方法 | 功能 |
|------|------|
| `t()` (line 933) | 基地重叠解决: 两基地 <400 单位 → 移除弱者 |
| `m()` (line 705) | 生成攻击组: 找有足够单位的基地, 创建 RallyGroup, 分配路径点 |

---

### 3. WeaponAction — 武器/动作

**文件**: `com/corrodinggames/rts/game/units/WeaponAction.java`

#### 3.1 字段

| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | av (WeaponTypeEnum) | 武器/动作类型 |
| `b` | as (UnitTypeHandle) | 目标单位类型 (用于建造) |
| `c` | a.c | 动作类型 |
| `d` | int | 弹药数/建造数量 |
| `e` | float | 伤害值/X坐标 |
| `f` | float | 射程/Y坐标 |
| `g` | long | 冷却计时器 |
| `h` | am | 目标单位实例 |
| `i` | ab | 编队/弹丸管理器 |
| `j` | boolean | 是否激活 |
| `k` | float | 当前伤害 |
| `l` | float | 当前射程 |
| `m` | boolean | 是否就绪 |
| `n` | boolean | 是否已发射 |

#### 3.2 18 种动作方法

| 方法 | WeaponType | 用途 |
|------|-----------|------|
| `a(x,y)` | av.a (MOVE) | 移动到坐标 |
| `b(x,y)` | av.h (PATROL) | 巡逻 |
| `a(am)` | av.b (ATTACK) | 攻击目标单位 |
| `a(x,y,as,int)` | av.c (BUILD) | 建造单位类型 |
| `b(am)` | av.d (REPAIR) | 修理目标 |
| `g(am)` | av.e | 特殊动作 |
| `f(am)` | av.g (GUARD) | 守卫目标 |
| `h(am)` | av.i | 目标动作2 |
| `c(x,y)` | av.j (RALLY) | 设集结點 |
| `c(am)` | av.k | 装载进入 |
| `d(am)` | av.m | 装载变体2 |
| `e(am)` | av.n | 装载变体3 |

---

### 4. 伤害计算

**文件**: `com/corrodinggames/rts/game/units/UnitInstance.java`

#### 4.1 伤害应用: `a(attacker, rawDamage, damageType)` (line 1220)

```
1. 护盾吸收 (f5):
   ├── 如果护盾再生计时器 = 0 且 currentShield > 0:
   ├── 伤害先作用于护盾
   ├── 护盾伤害倍率 f5
   └── 溢出穿透 (f6 = 穿透因子)

2. HP 伤害 (f7):
   ├── 剩余伤害作用于 HP
   ├── 记录 lastDamageTime, lastAttacker
   └── HP ≤ 0 → ch() → bv() → bu() (8步死亡)

3. 建造中惩罚:
   └── buildProgress < 1.0 → ×1.75 伤害
```

#### 4.2 治疗/护盾回复: `b(healer, amount, healType)` (line 1179)

```
先回护盾 (最多到 maxShield)
再回 HP (最多到 maxHp)
分别有护盾/船体恢复效率系数
```

---

## Part B: 指令管线

### 5. Command — 玩家指令 (639行)

**文件**: `com/corrodinggames/rts/gameFramework/Command.java`

#### 5.1 Command 字段

| 字段 | 含义 |
|------|------|
| `a`~`h` | 标志位 (stopOrUndo=g) |
| `i` (playerRef) | 玩家引用 |
| `j` (waypointAction) | 路径点/动作 |
| `k` (specialAction) | 特殊动作 |
| `n` (attackMode) | 攻击模式 |
| `q` (playerIndex) | 玩家索引 (2字节, 位图) |
| `r` (systemAction) | 系统动作标志 |
| `s` (changeStepRate) | 游戏速度变更 |
| `u` (systemActionValue) | 系统动作值 (100=投降) |
| `v` (selectedUnits) | 选中单位列表 |

#### 5.2 系统动作值

| 值 | 含义 |
|----|------|
| `1` | 调试: 反同步检测 |
| `2` | 压测: 反同步检测 |
| `5` | 系统生成 (直接创建单位) |
| `100` | 队伍投降 (杀死所有单位) |
| `200` | 排队快速重同步 |

---

### 6. 指令生命周期

#### 阶段1: 创建

```java
// 玩家输入 → 创建 Command
Command cmd = new Command();
cmd.a(unit);              // 添加单位
cmd.a(x, y);              // 设置移动目标
// 或
cmd.a(x, y, unitType, n); // 设置建造目标
// 或
cmd.a(targetUnit);        // 设置攻击目标
```

#### 阶段2: 验证 — `l()` (line 639)

```
prepareAndCheckOnServer():
1. 构建玩家索引位图 (playerIndex)
2. 拒绝非 UnitInstance 的建造指令 (am.a → null)
3. 拒绝 addedByAction 的路径点 (waypointAction.n)
```

#### 阶段3: 排队

```java
CommandController.b(player)
  → cmd.l()      // 验证
  → this.b.add(cmd)  // 加入队列
```

#### 阶段4: 执行 — `k()` (line 413)

```
主执行方法:
│
├── 系统动作? → 立即处理, return
│   ├── changeStepRate → 变速
│   ├── systemActionValue=100 → 投降
│   └── systemActionValue=5 → 系统生成
│
├── 停止/撤销? (stopOrUndo)
│   └── 对每个单位: az() (停止当前动作), R=null
│
├── 路径点处理:
│   ├── waypointAction.c() (转换ID)
│   ├── 创建编队管理器 (ab)
│   ├── 对每个单位:
│   │   ├── this.f → aA() (强制开火)
│   │   ├── !this.e → az() (清空动作)
│   │   ├── isValidNewWaypoint 检查 ← 关键门
│   │   ├── y5.d(waypointAction) (完整动作)
│   │   ├── ab.a(y5, au) (加入编队)
│   │   └── y5.a(au) (分配给单位)
│   └── ab.b() (编队完成 → 发出移动指令)
│
├── 特殊动作 (建造/能力):
│   ├── 找动作 → 检查可用 → 激活
│   └── 可选 ping 地图
│
├── 攻击模式设置
│
└── 集结点设置 (工厂类型)
```

#### 阶段5: 单位分派

```
Command.k() line 571:
  y5.a(au) ← 将路径点动作写入单位
  y5.ar() ← 返回单位的当前动作

编队管理器 (ab/ProjectileManager):
  ab.a(y5, au) ← 将单位加入编队
  ab.b()      ← 编队完成 → 发出最终移动指令
```

---

### 7. isValidNewWaypoint — 关键门

```
Command.k() line 572:
  y5.a(this.waypointAction, c.e < 5)
  // c.e 计数器限制错误日志为 5 条

此方法验证:
├── 目标位置是否有效 (地图范围内)
├── 建造位置是否可建造 (地形/碰撞)
├── 目标单位是否有效 (存活/可达)
└── 单位是否有能力执行该动作

如果返回 false:
  → 指令被静默拒绝 (exec_fail)
  → 不抛出异常
  → 仅前 5 条失败记录日志
```

---

### 8. 完整管线图

```
┌─ 玩家输入 ─────────────────────────────────────────┐
│ 点击/拖拽/快捷键                                    │
└──────────────────┬──────────────────────────────────┘
                   ▼
┌─ Command 创建 ──────────────────────────────────────┐
│ new Command()                                       │
│ + cmd.a(units[])        ← 选择单位                  │
│ + cmd.a(x,y)            ← 移动/建造位置              │
│ + cmd.a(targetUnit)     ← 攻击目标                  │
│ + cmd.a(unitType, n)    ← 建造类型                  │
└──────────────────┬──────────────────────────────────┘
                   ▼
┌─ 验证: l() ─────────────────────────────────────────┐
│ prepareAndCheckOnServer:                            │
│ ├── playerIndex 位图                                │
│ ├── 拒绝无效建造目标                                 │
│ └── 拒绝无效路径点                                   │
└──────────────────┬──────────────────────────────────┘
                   ▼
┌─ 排队 ──────────────────────────────────────────────┐
│ CommandController.b(player):                        │
│ ├── cmd.l() 验证                                    │
│ └── this.b.add(cmd)  ← 入队                        │
└──────────────────┬──────────────────────────────────┘
                   ▼
┌─ 执行: k() ─────────────────────────────────────────┐
│ 每 tick: CommandController.d()                      │
│ ├── 系统动作: 立即处理                               │
│ ├── stopOrUndo: 停止所有单位                         │
│ ├── 路径点:                                         │
│ │   ├── ☆ isValidNewWaypoint 门                     │
│ │   ├── 编队分派                                    │
│ │   └── 写入单位                                    │
│ ├── 特殊动作: 建造/修理/装载                         │
│ ├── 攻击模式                                        │
│ └── 集结点                                          │
└──────────────────┬──────────────────────────────────┘
                   ▼
┌─ 单位执行 ──────────────────────────────────────────┐
│ 单位接收 waypoint                                   │
│ ├── MOVE:    MovementController 导航                 │
│ ├── ATTACK:  WeaponAction 开火                      │
│ ├── BUILD:   Factory/Builder 建造                   │
│ ├── REPAIR:  纳米射线                               │
│ └── GUARD:   跟随/保护                              │
└─────────────────────────────────────────────────────┘
```

---

## 9. 对 RWAgent 的关键启示

1. **isValidNewWaypoint 是静默杀手** — 无异常无日志，仅前5次失败报告
2. **建造指令失败常见原因**:
   - 建造位置不可建造 (地形/重叠)
   - 单位类型无效 (null/已移除)

---

## 6. 单位指令系统 (2026-06-23 新增)

> 关键文件: `game/units/a/`(26个类全部解混淆)
> 详见: [UNIT-ACTIONS.md](../03-actions/UNIT-ACTIONS.md)

### 6.1 15种指令类型

| ID | 类 | GUI键 |
|----|---|-------|
| c_1 | RallyPointAction | gui.actions.setRally |
| c_2 | ReclaimAction | gui.actions.reclaimTarget |
| c_3 | RepairAction | gui.actions.repairTarget |
| c_4 | AttackMoveAction | — |
| c_5 | StopAction | — |
| c_6_{type} | PingAction | menus.ingame.ping.type.* |
| c_7 | AttackAction | — |
| c_8 | GuardAction | gui.actions.guardUnit |
| c_9 | PatrolAction | gui.actions.patrol |
| b_{id} | BuildQueueAction | — |
| u_{id} | UnitBuildAction | — |
| s_{id} | SellAction | — |
| c__cut_chat | TeamChatAction | — |
| c__cut_ping | MapPingAction | — |

### 6.2 GameAction核心基类

`GameAction(s)` implements Comparable — 按优先级排序:
- `g` (float priority) — 越小越高, Stop=-9990, Sell=-9999
- `h` (UnitActionBase) — 操作委托
- `a` (ActionId) — 驻留字符串ID (HashMap缓存)
- `b` (ActionFilter) — 可用性过滤器 (默认 `emptyActionFilter`)

### 6.3 PingType — 12种地图信号

枚举 `a~l`，本地化键 `menus.ingame.ping.type.{name}`
   - 建造者正在执行其他动作
3. **playerIndex 位图** — 必须包含正确的玩家位
4. **指令必须经过 l() 验证** — 否则在 k() 中无效
5. **特殊动作需要 action.b(unit)** 检查可用性
6. **编队管理器** — 批量单位指令通过 ab (ProjectileManager) 协调编队运动



