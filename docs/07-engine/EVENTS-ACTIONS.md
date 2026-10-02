# Rusted Warfare v1.15 — 事件系统与自定义动作源码逆向
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 任务事件引擎(11种触发器)、动作类层级(25类)、ActionId系统、从输入到执行的完整链路
>
> 关键文件: `MissionEvent.java`(73行), `units/a/s.java`(动作基类,478行), `MissionExecutor.java`(221行)

---

## Part A: 游戏事件/触发器系统

### 1. MissionEvent — 11种事件类型

| 枚举 | 字符串名 | 用途 |
|------|---------|------|
| `a` | `objective` | 标记任务目标完成 |
| `b` | `move` | 命令单位移动到目标点 |
| `c` | `changeCredits` | 修改队伍资金 (set/add) |
| `d` | `teamTags` | 添加/移除队伍标签 |
| `e` | `unitAdd` | 在地图位置生成单位 |
| `f` | `unitRemove` | 移除(杀死)匹配单位 |
| `g` | `mapText` | 在地图位置显示文本 |
| `h` | `moveCamera` | 移动摄像机到目标点 |
| `i` | `unitDetect` | 检测单位到达/存在 |
| `j` | `teamTagDetect` | 检测队伍标签 |
| `k` | `basic` | 基础空操作触发器 |

### 2. 触发配置解析

`MissionParser.java` 从 TMX `<objectgroup>` (triggers 层) 解析:

```
触发属性:
├── type: MissionEvent 类型 ("move", "unitAdd"...)
├── id: 触发名称
├── team: 所属队伍
├── delay: 延迟激活
├── repeatDelay: 重复间隔
├── repeatCount: 重复次数
├── resetActivationAfter: 重置计时
├── warmup: 预热
├── allToActivate: 全激活
├── globalMessage: 全局消息文本
├── text: 显示文本
├── textOffsetX/Y: 文本偏移
├── spawnUnits: 生成单位配置
├── addTeamTags/removeTeamTags: 标签操作
├── activateIds: 链接激活ID列表
├── alsoActivate: 同时激活ID列表
├── whenActivatedIds/activatedBy: 接收其他触发
└── deactivatedBy: 被其他触发的停用ID列表
```

### 3. 触发条件 (gameFramework/n/a/)

```
TaskCondition (基类)
├── a(): 预检查
├── b(): 执行检查
└── c(): 激活后检查

UnitCountCondition (91行)
├── minUnits, maxUnits: 单位数量范围
├── unitType: 指定单位类型
├── onlyBuildings, onlyIdle, onlyTechLevel: 过滤条件
├── onlyAttack, onlyAttackAir: 攻击能力过滤
├── onlyWithTag: 标签过滤
├── onlyIfEmpty: 仅空时
└── includeIncomplete: 包含未完成建造

TeamTagDetect (42行)
└── 检查队伍是否有指定标签
```

### 4. 触发执行 (MissionExecutor, 221行)

```
MissionExecutor.a(AIWaveSystem, AITask):
├── objective → 标记目标完成
├── move → 找匹配单位 → 命令移动到目标
├── unitAdd → 在触发位置生成单位
├── changeCredits → n2.o = value (set/add)
├── teamTags → 添加/移除队伍标签
├── unitRemove → 杀死匹配单位
├── mapText → 显示全局消息 (带逐字延迟+颜色)
├── moveCamera → 摄像机平移到位置
└── 所有类型: 触发 activateIds / alsoActivate 链
```

---

## Part B: 自定义动作系统

### 5. 动作类层级 (25个文件)

```
s (抽象动作基类, 478行)
├── p (建造动作基类, e()=u.i)
│   ├── q (团队聊天)
│   └── r (地图信号)
├── d (攻击模式切换, e()=u.k)
├── e (攻击移动, e()=u.h)
├── f (守卫单位, e()=u.l)
├── i (巡逻, e()=u.m)
├── m (回收, e()=u.e)
├── n (修复, e()=u.f)
├── o (设置集结点, e()=u.d)
├── x (根级别动作, e()=u.a)
├── y (单位信息, e()=u.i)
├── z (单位类型选择器, e()=u.i)
├── v (构建单位 — 制造队列, e()=u.b)
├── w (构建/制造抽象, e()=u.c)
│   └── l (单位建造动作)
├── j (地图信号, e()=u.j)
├── g (目标单位动作包装器)
└── h (条件动作包装器)
```

### 6. 动作交互类型枚举 (u.java, 14种)

| 值 | 用途 | 对应用法 |
|----|------|---------|
| `a` | build | 建造UI |
| `b` | 单位 | 选择单位类型 |
| `c` | 构建 | 构建/制造 |
| `d` | 集结点 | 设置工厂集结点 |
| `e` | 回收 | 回收资源 |
| `f` | 修复 | 修理单位 |
| `g` | 持有 | 暂停/恢复 |
| `h` | 攻击移动 | 攻击移动 |
| `i` | 信息/聊天 | 显示信息 |
| `j` | 信号 | 地图Ping |
| `k` | 攻击模式 | 切换攻击模式 |
| `l` | 守卫 | 守卫单位 |
| `m` | 巡逻 | 巡逻路径 |

### 7. 动作ID系统 (c.java, 64行)

```java
// ActionId 格式:
"u_" + unitType.v()     // 单位建造动作 (l)
"b_" + unitType.v()     // 构建动作 (v)
"s_" + unitType.v()     // 单位类型选择器 (z)
"c_N"                   // 核心游戏动作 (N为数字)
"c__cut_" + name        // 建造类别UI

// 查找:
am.a(ActionId) → 在单位的动作列表中查找匹配动作
```

### 8. 动作可用性检查链

```
s.b(am) — isAvailable
  ├── 委托到 s.h.a(am, false)
  │   ├── a.java (基础过滤器): 默认返回 true
  │   └── h.java (包装器): 检查 this.b.isAvailable(this, am)
  │       └── LogicBoolean 条件: 如 "if self.isEnergyFull()"
  └── g.java (目标单位包装器): 检查 this.c.isAvailable(this, am)

s.a(am, boolean) — 完整可用性
  ├── s.b(am) — 基础可用性
  └── 资源检查: 检查单位是否有足够资源
```

### 9. 动作执行流程

```
UI点击动作 → CommandContainer
│
├── 设置 ActionId: e.a(action.z())
├── 设置 WaypointAction (如有):
│   ├── MOVE: a(x, y)
│   ├── ATTACK: a(targetUnit)
│   ├── BUILD: a(x, y, unitType, stages)
│   └── REPAIR: b(targetUnit)
│
├── CommandController.b(player) — 排队
│
└── Command.k() — 执行
    │
    ├── [WaypointAction] → isValidNewWaypoint → unit.ar(au)
    └── [SpecialAction] → unit.a(c) 查找动作 → action.a(unit) 激活
```

### 10. INI [action_NAME] 定义 (推断)

```ini
[action_buildTank]
# 建造动作
type=build        # u.a
unitType=tank     # 目标单位类型
techLevel=1       # 科技等级
isLocked=if not self.isEnergyFull()
isVisible=true

[action_repair]
# 修复动作
type=repair       # u.f
target=selected   # 目标选择模式
nanoRange=100     # 纳米射线范围
repairSpeed=2.0   # 修理速度

[action_attackMove]
# 攻击移动
type=attackMove   # u.h
isVisible=true
```

---

## 11. 对 RWAgent 的启示

1. **动作可用性检查**: `s.b(unit)` + `s.a(unit, false)` 必须在建造前通过
2. **ActionId 格式**: 建造动作ID = `"b_" + unitType.i()`，单位动作ID = `"u_" + unitType.i()`
3. **动作查找**: `unit.a(ActionId)` 可验证单位是否支持某动作
4. **事件系统**: 通过 `MissionEvent` 可触发 AI 行为 (move/spawn/changeCredits)
5. **触发条件**: `UnitCountCondition` 可做复杂的单位数量检测



