# 03-custom-core — 自定义单位核心

> **职责**：INI 驱动的模组单位体系（模组生态基石）

> 本文处于**项目分析上限** ✓：数据全部来自映射库 / 反编译树 / 交付物实测 ✓，口径见 [../STATUS.md](../STATUS.md) ✓。

## 一、数据总览

| 指标 | 值 |
|---|---|
| 映射条目 | **1317** 条 |
| 涉及类 | **42** 个 |
| 有可读名 | **31** 个（74%）|
| ★ **已替换为我们的字节** | **26** 个（62%）|
| 主包 | `com.corrodinggames.rts.game.units.custom` · `com.corrodinggames.rts.game.units.custom.a` · `com.corrodinggames.rts.game.units.custom.d` · `com.corrodinggames.rts.game.units.custom.logicBooleans` |

### 交付物替换状态（★ 该域我们掌控多少 ✓）

| 状态 | 类数 | 含义 |
|---|---|---|
| **我们的字节** ✓ | **26** | 已由反编译源重建并在交付物中替换 |
| 原版字节 | 16 | 仍使用原版混淆字节（结构一致 ✓）|
| 未进交付物 | 0 | 该域映射涉及、但不属交付物条目 |

## 二、包结构与源码位置

| 混淆包 | 映射行 |
|---|---|
| `com.corrodinggames.rts.game.units.custom` | 1212 |
| `com.corrodinggames.rts.game.units.custom.a` | 44 |
| `com.corrodinggames.rts.game.units.custom.d` | 38 |
| `com.corrodinggames.rts.game.units.custom.logicBooleans` | 14 |
| `com.corrodinggames.rts.game.units.custom.c` | 9 |

**03 源码位置**（该域主包 ✓）：

| 包（可读路径） | 文件 | 行数 |
|---|---|---|
| `com/corrodinggames/rts/game/units` | 81 | 18732 |
| `com/corrodinggames/rts/game/units/custom` | 92 | 16093 |
| `com/corrodinggames/rts/game/units/custom/logicBooleans` | 215 | 10716 |
| `com/corrodinggames/rts/game` | 24 | 7757 |
| `com/corrodinggames/rts/game/units/custom/animation` | 14 | 2097 |
| `com/corrodinggames/rts/game/units/custom/actions/base` | 17 | 1991 |
| `com/corrodinggames/rts/game/units/custom/resources` | 5 | 1829 |
| `com/corrodinggames/rts/game/units/custom/effects` | 9 | 1371 |
| `com/corrodinggames/rts/game/units/custom/anim` | 6 | 1205 |
| `com` | 6 | 1080 |

## 三、★ 全量类清单（42 个 ✓）

> `状态`：**我们的字节** ✓ / 原版字节 / 未进交付物 ✓；`字段`/`方法` 为该类**已映射成员数** ✓。

| 混淆类名 | 可读名 | 字段 | 方法 | 状态 |
|---|---|---|---|---|
| `l` | **ModUnitRegistry** | 644 | 28 | 原版字节 |
| `j` | **CustomUnitType** | 40 | 194 | 原版字节 |
| `bn` | **ModUnitLoader** | 87 | 1 | 原版字节 |
| `ba` | **TraitValueBuilder** | 59 | 1 | **我们的字节** ✓ |
| `b` | **CustomActionBase** | 8 | 19 | 原版字节 |
| `d` | — | 25 | 1 | 原版字节 |
| `ag` | **ModLoader** | 4 | 16 | 原版字节 |
| `as` | **WeaponConfig** | 16 | 2 | **我们的字节** ✓ |
| `f` | **UnitParameter** | 16 | 1 | 原版字节 |
| `i` | **CollisionShape** | 16 | 0 | **我们的字节** ✓ |
| `c` | **c** | 12 | 0 | 原版字节 |
| `LogicBoolean$ReturnType` | — | 10 | 0 | **我们的字节** ✓ |
| `az` | — | 9 | 0 | **我们的字节** ✓ |
| `n` | **DirectionType** | 9 | 0 | **我们的字节** ✓ |
| `d` | **CurveType** | 8 | 0 | 原版字节 |
| `g` | **TagParser** | 1 | 7 | 原版字节 |
| `aj` | **LocalizedTextParser** | 6 | 1 | **我们的字节** ✓ |
| `bb` | **LocalizedStringBuilder** | 3 | 3 | **我们的字节** ✓ |
| `p` | **EffectConfig** | 6 | 0 | **我们的字节** ✓ |
| `a` | — | 5 | 0 | **我们的字节** ✓ |
| `m` | **DirectionConfig** | 5 | 0 | **我们的字节** ✓ |
| `bf` | — | 4 | 0 | **我们的字节** ✓ |
| `bo` | **ResourceException** | 4 | 0 | **我们的字节** ✓ |
| `f` | — | 4 | 0 | **我们的字节** ✓ |
| `a` | **CommandSlotBase** | 2 | 2 | **我们的字节** ✓ |
| `c` | **c** | 2 | 2 | 原版字节 |
| `r` | **Modifier** | 4 | 0 | **我们的字节** ✓ |
| `b` | — | 3 | 0 | **我们的字节** ✓ |
| `g` | **g** | 2 | 1 | 原版字节 |
| `av` | **LogicWriterOperation** | 3 | 0 | **我们的字节** ✓ |
| `d` | — | 3 | 0 | **我们的字节** ✓ |
| `k` | **WeaponMount** | 3 | 0 | **我们的字节** ✓ |
| `VariableScope$CachedWriter$Operator` | — | 3 | 0 | **我们的字节** ✓ |
| `s` | **ModifierType** | 3 | 0 | **我们的字节** ✓ |
| `bc` | — | 2 | 0 | **我们的字节** ✓ |
| `aa` | **ModFileWatcher** | 1 | 0 | **我们的字节** ✓ |
| `at` | **UnitDataWriter** | 0 | 1 | **我们的字节** ✓ |
| `ay` | **CustomEffectTemplate** | 0 | 1 | 原版字节 |
| `bp` | **TeamRuleValidator** | 0 | 1 | 原版字节 |
| `c` | **AnimationCurve** | 0 | 1 | 原版字节 |
| `LogicBooleanGameFunctions$IsResourceLargerThan` | — | 0 | 1 | **我们的字节** ✓ |
| `z` | **CustomVisuals** | 1 | 0 | 原版字节 |

## 四、关键成员速查（真实可读名 ✓）

| 类 | 成员数 | 可读成员（节选）|
|---|---|---|
| `l`（ModUnitRegistry） | 672 | `KeyframeTimeScale(f)` · `actionCache(f)` · `actionHandler(f)` · `actionList(f)` · `action_1_buildSpeed(f)` · `action_1_convertTo(f)` · `action_1_description(f)` · `action_1_displayType(f)` · `action_1_pos(f)` … |
| `j`（CustomUnitType） | 234 | `acceleration(f)` · `actionCooldowns(f)` · `applyActionToQueue(m)` · `beginUnloading(m)` · `cachedDisplayString(f)` · `canAddToTransport(m)` · `canAffordWeaponFire(m)` · `canAttackUnit(m)` · `canAttackUnitByConfig(m)` … |
| `bn`（ModUnitLoader） | 88 | `activeInstance(f)` · `activeModNames(f)` · `activeModsList(f)` · `aiConfig(f)` · `allModsList(f)` · `allowCustomUnits(f)` · `allowModDownloads(f)` · `assetsReloaded(f)` · `availableUnitFilter(f)` … |
| `ba`（TraitValueBuilder） | 60 | `aimOffsetSpread(f)` · `allowRetaliation(f)` · `ammoCapacity(f)` · `ammoResourceType(f)` · `burstCount(f)` · `burstDelay(f)` · `canAttack(f)` · `canAttackAir(f)` · `canAttackFlyingUnits(f)` … |
| `b`（CustomActionBase） | 27 | `addToUnit(m)` · `ammo(f)` · `applyFlagChanges(m)` · `canAfford(m)` · `canAffordForBuild(m)` · `checkFlagRequirements(m)` · `clampUnitResources(m)` · `compareTo(m)` · `costsOverlap(m)` … |
| `d` | 26 | `actionAnimation(f)` · `actionBindings(f)` · `actionButtonPos(f)` · `actionCategory(f)` · `actionCooldown(f)` · `actionCost(f)` · `actionDescription(f)` · `actionFlags(f)` · `actionHotkey(f)` … |
| `ag`（ModLoader） | 20 | `buildModList(m)` · `clearAllCaches(m)` · `clearModData(m)` · `currentlyLoadingMod(f)` · `doReloadModsInternal(m)` · `do_a(m)` · `dumpTimerStats(m)` · `getBuiltinModsDirName(m)` · `getBuiltinModsEnabledFile(m)` … |
| `as`（WeaponConfig） | 18 | `accuracySpread(f)` · `aimingTime(f)` · `ammoPerBurst(f)` · `bulletCount(f)` · `cooldownTime(f)` · `damageMultiplier(f)` · `getas(m)` · `getat(m)` · `maxAmmoCount(f)` … |

## 五、映射备注（项目分析结论 ✓）

- **`b`**：Key binding string (String)
- **`b`**：Referenced action ID (String)
- **`b`**：Target unit type filter (String); 逆4-v19.133f98: 语义名在 03 存在 — 归属待迁 (relocate-candidate); 逆4d-v19.133f98: 保持-仅异域同名声明 (d 在
- **`c`**（c）：Action can repeat (boolean)
- **`c`**（c）：Action cancel result binding (b)
- **`c`**（c）：Action failure result binding (b)
- **`c`**（c）：Action success result binding (b)
- **`d`**：Action LogicBoolean script
- **`d`**：Action animation reference (String)
- **`d`**：Action behavior flags (int)
- **`d`**：Action button position index (int)
- **`g`**（g）：Group display name (LocalizedString)
- **`g`**（g）：Grouped action IDs list (ArrayList)
- **`g`**（g）：real-class-inferred: com.corrodinggames.rts.game.units.custom.a.g

## 核心概念

**自定义单位核心**：Rusted Warfare 的模组生态基石 ——
一切"非原版单位"（社区模组单位）都由这里的**INI 解析器 + 数据模型**驱动。

| 子模块 | 说明 |
|---|---|
| **INI 解析** | 解析 `[core]` / `[turret_*]` / `[action_*]` 等段，含继承与 `copyFrom` |
| **数据模型** | `CustomUnitData`（单位）、`CustomTurretData`、`CustomActionData` |
| **表达式/参数** | 资源成本、建造条件、标签系统（tags） |
| **模组管理** | 扫描目录、加载/卸载、启用/禁用、数据重载 |

## 关键机制

1. **段式 INI + 继承**：`copyFrom` 复制已有单位再覆盖 ⇒ 模组写法核心 ✓
2. **键值解析**：支持多值、列表、逻辑表达式（见 04 域）
3. **参数校验与报错**：解析失败给出**可读错误**（模组作者的调试入口 ✓）
4. **热重载**：`reloadAllMods` 类能力 ⇒ 不重启改数据 ✓
5. **标签与条件**：单位可带标签，动作/升级按标签过滤

## 修改指南

| 想改什么 | 改哪里 |
|---|---|
| 新建模组单位 | 在模组目录加 INI（**推荐入口** ✓ 无需改 Java ✓） |
| 新增 INI 键 | `game/units/custom/` 的解析器 + 数据模型 ✓ |
| 模组加载策略 | 模组管理类 ✓ |


## 六、子文档

- [`CUSTOM-UNIT.md`](CUSTOM-UNIT.md)
- [`INI-PARSING.md`](INI-PARSING.md)
- [`MOD-PARAMETERS.md`](MOD-PARAMETERS.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓


## 六、子文档

- [`CUSTOM-UNIT.md`](CUSTOM-UNIT.md)
- [`INI-PARSING.md`](INI-PARSING.md)
- [`MOD-PARAMETERS.md`](MOD-PARAMETERS.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓
