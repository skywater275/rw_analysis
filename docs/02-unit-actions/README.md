# 02-unit-actions — 单位动作与命令

> **职责**：玩家可下达的指令：动作、命令队列、生产

> 本文处于**项目分析上限** ✓：数据全部来自映射库 / 反编译树 / 交付物实测 ✓，口径见 [../STATUS.md](../STATUS.md) ✓。

## 一、数据总览

| 指标 | 值 |
|---|---|
| 映射条目 | **895** 条 |
| 涉及类 | **74** 个 |
| 有可读名 | **67** 个（91%）|
| ★ **已替换为我们的字节** | **24** 个（32%）|
| 主包 | `com.corrodinggames.rts.game.units.a` · `com.corrodinggames.rts.game.units.d` · `com.corrodinggames.rts.game.units.d.a` · `com.corrodinggames.rts.game.units.f` |

### 交付物替换状态（★ 该域我们掌控多少 ✓）

| 状态 | 类数 | 含义 |
|---|---|---|
| **我们的字节** ✓ | **24** | 已由反编译源重建并在交付物中替换 |
| 原版字节 | 43 | 仍使用原版混淆字节（结构一致 ✓）|
| 未进交付物 | 7 | 该域映射涉及、但不属交付物条目 |

## 二、包结构与源码位置

| 混淆包 | 映射行 |
|---|---|
| `com.corrodinggames.rts.game.units.a` | 597 |
| `com.corrodinggames.rts.game.units.d` | 184 |
| `com.corrodinggames.rts.game.units.d.a` | 42 |
| `com.corrodinggames.rts.game.units.f` | 24 |
| `com.corrodinggames.rts.game.units.units.d` | 17 |
| `com.corrodinggames.rts.game.units.custom.f` | 11 |
| `d` | 9 |
| `com.corrodinggames.rts.game.units.b` | 6 |
| `com.corrodinggames.rts.game.units.h` | 5 |

**03 源码位置**（该域主包 ✓）：

| 包（可读路径） | 文件 | 行数 |
|---|---|---|
| `com/corrodinggames/rts/game/units` | 81 | 18732 |
| `com/corrodinggames/rts/game/units/custom` | 92 | 16093 |
| `com/corrodinggames/rts/game` | 24 | 7757 |
| `com/corrodinggames/rts/game/units/actions` | 26 | 3246 |
| `com/corrodinggames/rts/game/units/buildings` | 10 | 1950 |
| `com/corrodinggames/rts/game/units/debug` | 10 | 1596 |
| `com` | 6 | 1080 |
| `com/corrodinggames/rts` | 13 | 658 |

## 三、★ 全量类清单（74 个 ✓）

> `状态`：**我们的字节** ✓ / 原版字节 / 未进交付物 ✓；`字段`/`方法` 为该类**已映射成员数** ✓。

| 混淆类名 | 可读名 | 字段 | 方法 | 状态 |
|---|---|---|---|---|
| `s` | **GameAction** | 1 | 52 | 原版字节 |
| `g` | **BuildAction** | 5 | 45 | **我们的字节** ✓ |
| `d` | **BuildSlot** | 7 | 40 | **我们的字节** ✓ |
| `e` | **CommandCenter** | 10 | 34 | 原版字节 |
| `h` | **ActionWrapper** | 4 | 37 | **我们的字节** ✓ |
| `c` | **ExperimentalHoverUnit** | 10 | 29 | 原版字节 |
| `t` | **ActionCategory** | 9 | 18 | **我们的字节** ✓ |
| `v` | **BuildQueueAction** | 0 | 26 | 原版字节 |
| `z` | **SellAction** | 0 | 24 | 原版字节 |
| `a` | **UnitActionBase** | 0 | 23 | 原版字节 |
| `y` | **StopAction** | 0 | 22 | 原版字节 |
| `d` | **AttackAction** | 0 | 21 | 原版字节 |
| `q` | **TeamChatAction** | 0 | 21 | 原版字节 |
| `r` | **MapPingAction** | 0 | 21 | 原版字节 |
| `w` | **AbstractBuildAction** | 0 | 21 | **我们的字节** ✓ |
| `j` | **PingAction** | 1 | 19 | 原版字节 |
| `l` | **UnitBuildAction** | 0 | 20 | 原版字节 |
| `u` | **ActionTargetType** | 2 | 18 | **我们的字节** ✓ |
| `k` | **PingType** | 1 | 18 | **我们的字节** ✓ |
| `b` | **ActionFilter** | 0 | 18 | **我们的字节** ✓ |
| `e` | **AttackMoveAction** | 0 | 18 | 原版字节 |
| `f` | **GuardAction** | 0 | 18 | 原版字节 |
| `i` | **PatrolAction** | 0 | 18 | 原版字节 |
| `m` | **ReclaimAction** | 0 | 18 | 原版字节 |
| `o` | **RallyPointAction** | 0 | 18 | 原版字节 |
| `p` | **AbstractCutsceneAction** | 0 | 18 | **我们的字节** ✓ |
| `x` | **AbstractImmediateAction** | 0 | 18 | **我们的字节** ✓ |
| `n` | **RepairAction** | 0 | 17 | 原版字节 |
| `j` | **BuilderUnit** | 14 | 0 | **我们的字节** ✓ |
| `b$1` | **BuildActionSlot$1** | 0 | 9 | 原版字节 |
| `b$2` | **BuildActionSlot$2** | 0 | 9 | 原版字节 |
| `b$3` | **BuildActionSlot$3** | 0 | 9 | 原版字节 |
| `b$4` | **BuildActionSlot$4** | 0 | 9 | 原版字节 |
| `b` | — | 6 | 3 | 未进交付物 |
| `b$1` | **ActionFilter$1** | 3 | 5 | **我们的字节** ✓ |
| `h` | **PowerGeneratorUnit** | 3 | 5 | 原版字节 |
| `d` | **RectFilter** | 8 | 0 | **我们的字节** ✓ |
| `c` | **ActionId** | 3 | 4 | **我们的字节** ✓ |
| `g` | **ExperimentalLandFactory** | 5 | 2 | **我们的字节** ✓ |
| `b` | **BuildActionSlot** | 5 | 0 | 原版字节 |
| `g` | **CircleFilter** | 5 | 0 | **我们的字节** ✓ |
| `h` | **LineFilter** | 5 | 0 | **我们的字节** ✓ |
| `b` | **CustomGroundUnit** | 0 | 4 | 原版字节 |
| `a` | **SpatialGridCell** | 4 | 0 | **我们的字节** ✓ |
| `e` | **FactoryAction5** | 4 | 0 | **我们的字节** ✓ |
| `a` | — | 0 | 4 | 未进交付物 |
| `c` | **AttackBehavior** | 3 | 0 | **我们的字节** ✓ |
| `a` | **ExperimentalGroundUnit** | 0 | 3 | 原版字节 |
| `k` | **UnitFactoryHelper** | 3 | 0 | 原版字节 |
| `s` | **AutoRepairCallback** | 3 | 0 | **我们的字节** ✓ |
| `b` | — | 0 | 3 | 未进交付物 |
| `f` | — | 0 | 3 | 未进交付物 |
| `g` | — | 0 | 3 | 未进交付物 |
| `d` | **MoveBehavior** | 2 | 0 | 原版字节 |
| `b` | **ActionFilter** | 2 | 0 | 原版字节 |
| `q$1` | **UnitActionHelper$1** | 0 | 2 | 原版字节 |
| `h` | — | 0 | 2 | 未进交付物 |
| `i` | — | 0 | 2 | 未进交付物 |
| `d$1` | **MoveBehavior$1** | 0 | 1 | 原版字节 |
| `e` | **e** | 0 | 1 | 原版字节 |
| `a$1` | **AbstractCommandSlot$1** | 0 | 1 | 原版字节 |
| `c$1` | **ExperimentalHoverUnit$1** | 0 | 1 | 原版字节 |
| `c$2` | **ExperimentalHoverUnit$2** | 0 | 1 | 原版字节 |
| `f$1` | **ExperimentalWaterUnit$1** | 0 | 1 | 原版字节 |
| `g$1` | **ExperimentalLandFactory$1** | 0 | 1 | 原版字节 |
| `g$2` | **ExperimentalLandFactory$2** | 0 | 1 | 原版字节 |
| `h$1` | **PowerGeneratorUnit$1** | 0 | 1 | 原版字节 |
| `h$2` | **PowerGeneratorUnit$2** | 0 | 1 | 原版字节 |
| `p$1` | **ExperimentalSubUnit$1** | 0 | 1 | 原版字节 |
| `q$2` | **UnitActionHelper$2** | 0 | 1 | 原版字节 |
| `v$1` | **FabricatorUnit$1** | 0 | 1 | 原版字节 |
| `c` | **UnitGeoIndex** | 0 | 1 | **我们的字节** ✓ |
| `f` | **QueryResult** | 0 | 1 | **我们的字节** ✓ |
| `f` | **FactoryAction6** | 1 | 0 | **我们的字节** ✓ |

## 四、关键成员速查（真实可读名 ✓）

| 类 | 成员数 | 可读成员（节选）|
|---|---|---|
| `s`（GameAction） | 53 | `actionConfigFlag(f)` · `canShowAction(m)` · `checkUnitTypeFilterFor(m)` · `getActionCategory(m)` · `getActionId(m)` · `getActionIdAlias(m)` · `getActionIdString(m)` · `getActionScale(m)` · `getActionTargetType(m)` … |
| `g`（BuildAction） | 50 | `buildOrderIndex(f)` · `buildStages(f)` · `buildTargetPos(f)` · `buildTargetPosition(f)` · `buildUnitType(f)` · `canExecute(m)` · `canShowAction(m)` · `checkGameRules(m)` · `checkUnitTypeFilterFor(m)` … |
| `d`（BuildSlot） | 47 | `addBuildProgress(m)` · `animFrame(f)` · `buildStep(f)` · `canBeTransported(m)` · `canMoveAndFire(m)` · `canPatrol(m)` · `canStartConstruction(m)` · `createGhostUnit(m)` · `createPlaceholderInstance(m)` … |
| `e`（CommandCenter） | 44 | `applyShield(m)` · `buildTimeAccumulator(f)` · `calculateBuildSpeed(m)` · `clearSelectedUnit(m)` · `commandTimeout(f)` · `commanderUnit(f)` · `frameCounter(f)` · `getActions(m)` · `getArmor(m)` … |
| `h`（ActionWrapper） | 41 | `ActionWrapper(m)` · `actionCooldown(f)` · `actionPriority(f)` · `canExecute(m)` · `compareByPriority(m)` · `compareTo(m)` · `equals(m)` · `executeAction(m)` · `getActionCategory(m)` … |
| `c`（ExperimentalHoverUnit） | 39 | `actionSlotPool(f)` · `alphaValue(f)` · `bounds(f)` · `buttonTextures(f)` · `calculateEffectValue(m)` · `cancelAction(m)` · `executeAction(m)` · `getActionCategory(m)` · `getActionSlotList(m)` … |
| `t`（ActionCategory） | 27 | `action(f)` · `building(f)` · `getActionCategory(m)` · `getActionScale(m)` · `getActionTargetType(m)` · `getDescription(m)` · `getDisplayString(m)` · `getFirstSelectedUnit(m)` · `getLabel(m)` … |
| `v`（BuildQueueAction） | 26 | `canShowAction(m)` · `getActionCategory(m)` · `getActionScale(m)` · `getActionTargetType(m)` · `getAssociatedUnitType(m)` · `getBuildProgressForUnit(m)` · `getDescription(m)` · `getDisplayString(m)` · `getFirstSelectedUnit(m)` … |

## 五、映射备注（项目分析结论 ✓）

- **`a`**（UnitActionBase）：UnitActionBase: GameAction: UI scale factor for action button
- **`a`**（UnitActionBase）：UnitActionBase: GameAction: action button icon texture
- **`a`**（UnitActionBase）：UnitActionBase: GameAction: alternative texture getter
- **`a`**（UnitActionBase）：UnitActionBase: GameAction: always visible in action panel
- **`b`**（ActionFilter）：ActionFilter: GameAction: UI scale factor for action button
- **`b`**（ActionFilter）：ActionFilter: GameAction: action button icon texture
- **`b`**（ActionFilter）：ActionFilter: GameAction: alternative texture getter
- **`b`**（ActionFilter）：ActionFilter: GameAction: always visible in action panel
- **`c`**（ActionId）：T0: HashMap缓存查找/创建 (动作名驻留, 同名字符串返回同一实例, equals为引用相等的前提)
- **`c`**（ActionId）：T0: k2.j()读UTF; 非null返回a(string)驻留; null返回null (配合au.c字段v82版本门控)
- **`c`**（ActionId）：T0: private static HashMap; a(String)中 缓存命中返回, 未命中 new c(string)+put (字符串驻留池)
- **`c`**（ActionId）：T0: public static final c a = a("-1"); 特殊哨兵ID("-1"); 其他代码如 a.s.i 用作默认动作引用
- **`d`**（AttackAction）："d.java:62-68 button label = current attack mode name via getCurrentAttackMode()"
- **`d`**（AttackAction）：AttackAction: GameAction: UI scale factor for action button

## 核心概念

**单位动作（Action）** 是"玩家能下达的指令"的抽象：
每个按钮（建造/攻击/移动/修理/巡逻…）都是一个 `UnitAction` 子类。

| 层次 | 说明 |
|---|---|
| **UnitAction** | 动作基类：图标、标签、可用性、执行 |
| **Command** | 已下达的**命令实例**（含目标、队列位置） |
| **CommandQueue** | 单位的命令队列（FIFO，可暂停/插队） |
| **Factory** | 生产队列：建造进度、多单位排队 |

## 关键机制

1. **动作注册**：单位类型通过 INI 的 action 段绑定动作列表 ⇒ 按钮即数据 ✓
2. **可用性判定**：`isAvailableInCurrentGameState` / `isEnabled` / 资源是否够
3. **命令序列化**：`Command` 可写入存档与网络包（回放/联机必须一致 ✓）
4. **生产**：`Factory` 维护生产队列 + 进度条 + 出兵点
5. **聚合动作**：一个按钮可展开多个子动作（如"建造"展开单位列表）

## 修改指南

| 想改什么 | 改哪里 |
|---|---|
| 动作按钮文案/图标 | INI 的 action 段 ✓ |
| 新动作类型 | `game/units/a/` 加子类 + 注册 ✓ |
| 命令队列行为 | `CommandQueue` 类 ✓ |
| 生产速度/排队 | `Factory` + INI ✓ |


## 六、子文档

- [`COMMAND-SERIAL.md`](COMMAND-SERIAL.md)
- [`FACTORY.md`](FACTORY.md)
- [`UNIT-ACTIONS.md`](UNIT-ACTIONS.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓


## 六、子文档

- [`COMMAND-SERIAL.md`](COMMAND-SERIAL.md)
- [`FACTORY.md`](FACTORY.md)
- [`UNIT-ACTIONS.md`](UNIT-ACTIONS.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓
