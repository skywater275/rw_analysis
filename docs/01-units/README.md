# 01-units — 单位系统

> **职责**：定义与驱动所有游戏实体（单位 / 建筑 / 炮塔）

> 本文处于**项目分析上限** ✓：数据全部来自映射库 / 反编译树 / 交付物实测 ✓，口径见 [../STATUS.md](../STATUS.md) ✓。

## 一、数据总览

| 指标 | 值 |
|---|---|
| 映射条目 | **975** 条 |
| 涉及类 | **47** 个 |
| 有可读名 | **47** 个（100%）|
| ★ **已替换为我们的字节** | **17** 个（36%）|
| 主包 | `com.corrodinggames.rts.game.units` · `com.corrodinggames.rts.game.units.e` · `com.corrodinggames.rts.game.units.g` |

### 交付物替换状态（★ 该域我们掌控多少 ✓）

| 状态 | 类数 | 含义 |
|---|---|---|
| **我们的字节** ✓ | **17** | 已由反编译源重建并在交付物中替换 |
| 原版字节 | 30 | 仍使用原版混淆字节（结构一致 ✓）|
| 未进交付物 | 0 | 该域映射涉及、但不属交付物条目 |

## 二、包结构与源码位置

| 混淆包 | 映射行 |
|---|---|
| `com.corrodinggames.rts.game.units` | 945 |
| `com.corrodinggames.rts.game.units.e` | 28 |
| `com.corrodinggames.rts.game.units.g` | 2 |

**03 源码位置**（该域主包 ✓）：

| 包（可读路径） | 文件 | 行数 |
|---|---|---|
| `com/corrodinggames/rts/game/units` | 81 | 18732 |
| `com/corrodinggames/rts/game/units/custom` | 92 | 16093 |
| `com/corrodinggames/rts/game/units/custom/logicBooleans` | 215 | 10716 |
| `com/corrodinggames/rts/game` | 24 | 7757 |
| `com/corrodinggames/rts/game/units/commands` | 41 | 6098 |
| `com/corrodinggames/rts/game/units/projectiles` | 19 | 3954 |
| `com/corrodinggames/rts/game/units/actions` | 26 | 3246 |
| `com/corrodinggames/rts/game/units/custom/animation` | 14 | 2097 |
| `com/corrodinggames/rts/game/units/custom/actions/base` | 17 | 1991 |
| `com/corrodinggames/rts/game/units/buildings` | 10 | 1950 |

## 三、★ 全量类清单（47 个 ✓）

> `状态`：**我们的字节** ✓ / 原版字节 / 未进交付物 ✓；`字段`/`方法` 为该类**已映射成员数** ✓。

| 混淆类名 | 可读名 | 字段 | 方法 | 状态 |
|---|---|---|---|---|
| `am` | **UnitInstance** | 120 | 215 | 原版字节 |
| `y` | **UnitType** | 117 | 176 | 原版字节 |
| `ar` | **UnitRegistry** | 9 | 49 | 原版字节 |
| `au` | **WeaponAction** | 14 | 29 | **我们的字节** ✓ |
| `h` | **Factory** | 15 | 28 | 原版字节 |
| `as` | **UnitTypeHandle** | 0 | 26 | **我们的字节** ✓ |
| `x` | **MovableUnit** | 0 | 25 | 原版字节 |
| `ap` | **UnitTurret** | 13 | 0 | **我们的字节** ✓ |
| `f` | **WaterUnit** | 10 | 0 | 原版字节 |
| `j` | **AbstractBuildingBase** | 5 | 4 | **我们的字节** ✓ |
| `n` | **UnitCategory** | 6 | 3 | 原版字节 |
| `b` | **FactoryBuilding** | 8 | 0 | **我们的字节** ✓ |
| `o` | **UnitBehaviorEnum** | 5 | 3 | 原版字节 |
| `ac` | **DecorType1** | 7 | 0 | **我们的字节** ✓ |
| `r` | **UnitActionEnum** | 5 | 2 | **我们的字节** ✓ |
| `ad` | **UnitAttachment** | 5 | 0 | **我们的字节** ✓ |
| `ah` | **DecorType3** | 5 | 0 | **我们的字节** ✓ |
| `c` | **Building** | 5 | 0 | **我们的字节** ✓ |
| `h$3` | **Factory$3** | 0 | 5 | 原版字节 |
| `aa` | **ResourceRate** | 4 | 0 | **我们的字节** ✓ |
| `ae` | **DecorType2** | 4 | 0 | **我们的字节** ✓ |
| `h$9` | **Factory$9** | 0 | 4 | 原版字节 |
| `ag` | **PathState** | 3 | 0 | **我们的字节** ✓ |
| `an` | **PlayerUnitIndicator** | 3 | 0 | **我们的字节** ✓ |
| `h` | **AbstractSubBuilding** | 3 | 0 | **我们的字节** ✓ |
| `h$1` | **Factory$1** | 0 | 3 | 原版字节 |
| `h$12` | **Factory$12** | 0 | 3 | 原版字节 |
| `h$17` | **Factory$17** | 0 | 3 | 原版字节 |
| `h$18` | **Factory$18** | 0 | 3 | 原版字节 |
| `h$19` | **Factory$19** | 0 | 3 | 原版字节 |
| `h$20` | **Factory$20** | 0 | 3 | 原版字节 |
| `h$21` | **Factory$21** | 0 | 3 | 原版字节 |
| `w` | **CustomUnitBase** | 0 | 3 | **我们的字节** ✓ |
| `i` | **SpecialBuilding** | 2 | 0 | 原版字节 |
| `b` | **ComponentType** | 2 | 0 | 原版字节 |
| `al` | **TreeDecoration** | 0 | 1 | **我们的字节** ✓ |
| `e` | **BuildingBase** | 0 | 1 | 原版字节 |
| `i$1` | **SpecialBuilding$1** | 0 | 1 | 原版字节 |
| `h$13` | **Factory$13** | 0 | 1 | 原版字节 |
| `h$14` | **Factory$14** | 0 | 1 | 原版字节 |
| `h$2` | **Factory$2** | 0 | 1 | 原版字节 |
| `h$22` | **Factory$22** | 0 | 1 | 原版字节 |
| `h$23` | **Factory$23** | 0 | 1 | 原版字节 |
| `h$3$1` | **Factory$3$1** | 0 | 1 | 原版字节 |
| `h$3$3` | **Factory$3$3** | 0 | 1 | 原版字节 |
| `h$5` | **Factory$5** | 0 | 1 | 原版字节 |
| `q` | **SpecialActionType** | 0 | 1 | 原版字节 |

## 四、关键成员速查（真实可读名 ✓）

| 类 | 成员数 | 可读成员（节选）|
|---|---|---|
| `am`（UnitInstance） | 335 | `accept_B(m)` · `actionCache(f)` · `actionsCache(f)` · `addAction(m)` · `allUnits(f)` · `am9(f)` · `animatedScreenBounds(f)` · `applyDamage(m)` · `applyHealing(m)` … |
| `y`（UnitType） | 293 | `activePathCount(f)` · `addAttackWaypoint(m)` · `addWaypoint(m)` · `advancePathNode(m)` · `attackWithTurret(m)` · `blockedFrameCount(f)` · `blockedTimer(f)` · `buildCost(f)` · `buildProgressFilter(f)` … |
| `ar`（UnitRegistry） | 58 | `allUnitTypes(f)` · `buildStatLine(m)` · `builderProviderCache(f)` · `cachedDescription(f)` · `cachedDisplayName(f)` · `canBeBuilt(m)` · `canGuard(m)` · `canHoldPosition(m)` · `canPatrol(m)` … |
| `au`（WeaponAction） | 43 | `approachRangeLimit(f)` · `buildSlotIndex(f)` · `buildUnitType(f)` · `bypassBuildChecks(f)` · `cachedPathEntry(f)` · `chainToNextWaypoint(f)` · `copyFrom(m)` · `deserialize(m)` · `getBuildSlotIndex(m)` … |
| `h`（Factory） | 43 | `a_2p(m)` · `actionChangeAlliance(f)` · `actionClearSaveHistory(f)` · `actionCloneUnit(f)` · `actionEnableAIDebug(f)` · `actionEnableTriggerDebug(f)` · `actionFastForward(f)` · `actionFreezeAI(f)` · `actionNukeAt(f)` … |
| `as`（UnitTypeHandle） | 26 | `availableInDemo(m)` · `buildTechTree(m)` · `createUnitInstance(m)` · `getBaseCost(m)` · `getBuildSpeed(m)` · `getCreditCost(m)` · `getCreditCostForTechLevel(m)` · `getImageTexture(m)` · `getLocalizedDescription(m)` … |
| `x`（MovableUnit） | 25 | `canBodyRotateToAim(m)` · `getAcceleration(m)` · `getAngularAcceleration(m)` · `getBaseTexture(m)` · `getBuildTime(m)` · `getDeceleration(m)` · `getMaxSpeed(m)` · `getMovementType(m)` · `getMovingTexture(m)` … |
| `ap`（UnitTurret） | 13 | `currentAngle(f)` · `hasLimitedArc(f)` · `isTurning(f)` · `lockDelay(f)` · `maxAngleLimit(f)` · `maxRotationAngle(f)` · `minAngleLimit(f)` · `shootCooldown(f)` · `targetAngle(f)` … |

## 五、映射备注（项目分析结论 ✓）

- **`aa`**（ResourceRate）：Consumption per second (float)
- **`aa`**（ResourceRate）：Production per second (float)
- **`aa`**（ResourceRate）：Resource type reference (e)
- **`aa`**（ResourceRate）：Storage capacity (float)
- **`ac`**（DecorType1）：Alpha/opacity value (float)
- **`ac`**（DecorType1）：Animation/lifetime timer (float)
- **`ac`**（DecorType1）：Color tint ARGB (int)
- **`ac`**（DecorType1）：Decoration type enum (int)
- **`ad`**（UnitAttachment）：Attachment offset X (float)
- **`ad`**（UnitAttachment）：Attachment offset Y (float)
- **`ad`**（UnitAttachment）：Attachment rotation (float)
- **`ad`**（UnitAttachment）：Attachment scale (float)
- **`ae`**（DecorType2）：Animation type enum (int)
- **`ae`**（DecorType2）：Fade-in enabled (boolean)

## 核心概念

**单位系统**是游戏玩法的中心：每个可见实体（坦克/建筑/飞机/船）都是 `UnitInstance`，
其"种类定义"在 `UnitType`（**数据驱动**，由 INI 加载）。

| 层次 | 说明 |
|---|---|
| **UnitType** | 单位**种类**（原型）：属性、武器、贴图、造价、建造时间 —— 一份数据 |
| **UnitInstance** | 单位**实例**：位置、血量、朝向、当前命令、所属队伍 |
| **UnitTurret** | 炮塔：独立朝向、独立开火计时 |
| **UnitMovement** | 移动：路径点序列 + 速度/转向 |

## 关键机制

1. **数据驱动**：131 个 `assets/units/*.ini` 定义全部单位；改 INI 即可加/改单位 ✓
2. **生命周期**：创建 → 初始化（`onSpawnInit`）→ 每帧更新（移动/攻击/动画）→ 死亡（爆炸/残骸）
3. **炮塔解耦**：车体转向与炮塔瞄准相互独立，各自有转向速率与就绪判定
4. **伤害模型**：武器 vs 护甲的乘算矩阵 + 溅射 + 弹道/命中判定
5. **选择与指令**：框选 → 命令（移动/攻击/建造/巡逻）→ 队列执行

## 修改指南

| 想改什么 | 改哪里 | 验证 |
|---|---|---|
| 单位数值（血量/速度/造价） | `assets/units/*.ini` | 直接进游戏看 ✓ |
| 新单位 | 复制一份 INI + 贴图 | 进出单位菜单 ✓ |
| 单位逻辑（Java） | `03-deobfuscated/…/game/units/` | `javac_gate` 0 错 + **V5 真窗口** ✓ |
| 武器/伤害 | INI 的 weapon 段 + `Weapon`/`Projectile` 类 | 实战打一场 ✓ |


## 六、子文档

- [`COMBAT-COMMAND.md`](COMBAT-COMMAND.md)
- [`UNIT-INI-PARAMS.md`](UNIT-INI-PARAMS.md)
- [`UNIT-LIFECYCLE.md`](UNIT-LIFECYCLE.md)
- [`UNIT-LOADING.md`](UNIT-LOADING.md)
- [`WEAPON-DAMAGE.md`](WEAPON-DAMAGE.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓


## 六、子文档

- [`COMBAT-COMMAND.md`](COMBAT-COMMAND.md)
- [`UNIT-INI-PARAMS.md`](UNIT-INI-PARAMS.md)
- [`UNIT-LIFECYCLE.md`](UNIT-LIFECYCLE.md)
- [`UNIT-LOADING.md`](UNIT-LOADING.md)
- [`WEAPON-DAMAGE.md`](WEAPON-DAMAGE.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓
