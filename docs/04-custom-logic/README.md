# 04-custom-logic — 自定义逻辑引擎

> **职责**：在 INI 里写布尔/数值表达式来做玩法逻辑

> 本文处于**项目分析上限** ✓：数据全部来自映射库 / 反编译树 / 交付物实测 ✓，口径见 [../STATUS.md](../STATUS.md) ✓。

## 一、数据总览

| 指标 | 值 |
|---|---|
| 映射条目 | **148** 条 |
| 涉及类 | **10** 个 |
| 有可读名 | **7** 个（70%）|
| ★ **已替换为我们的字节** | **6** 个（60%）|
| 主包 | `com.corrodinggames.rts.game.units.custom.b` · `com.corrodinggames.rts.game.units.custom.e` |

### 交付物替换状态（★ 该域我们掌控多少 ✓）

| 状态 | 类数 | 含义 |
|---|---|---|
| **我们的字节** ✓ | **6** | 已由反编译源重建并在交付物中替换 |
| 原版字节 | 4 | 仍使用原版混淆字节（结构一致 ✓）|
| 未进交付物 | 0 | 该域映射涉及、但不属交付物条目 |

## 二、包结构与源码位置

| 混淆包 | 映射行 |
|---|---|
| `com.corrodinggames.rts.game.units.custom.b` | 76 |
| `com.corrodinggames.rts.game.units.custom.e` | 72 |

**03 源码位置**（该域主包 ✓）：

| 包（可读路径） | 文件 | 行数 |
|---|---|---|
| `com/corrodinggames/rts/game/units` | 81 | 18732 |
| `com/corrodinggames/rts/game/units/custom` | 92 | 16093 |
| `com/corrodinggames/rts/game` | 24 | 7757 |
| `com/corrodinggames/rts/game/units/custom/effects` | 9 | 1371 |
| `com` | 6 | 1080 |
| `com/corrodinggames/rts` | 13 | 658 |
| `com/corrodinggames/rts/game/units/custom/effects/config` | 6 | 188 |

## 三、★ 全量类清单（10 个 ✓）

> `状态`：**我们的字节** ✓ / 原版字节 / 未进交付物 ✓；`字段`/`方法` 为该类**已映射成员数** ✓。

| 混淆类名 | 可读名 | 字段 | 方法 | 状态 |
|---|---|---|---|---|
| `a` | **LogicBoolean** | 29 | 3 | 原版字节 |
| `d` | **EffectConfig** | 29 | 1 | 原版字节 |
| `n` | **UnitTrait** | 24 | 1 | **我们的字节** ✓ |
| `d` | **AnimationResourceCurve** | 21 | 0 | **我们的字节** ✓ |
| `i` | — | 17 | 0 | **我们的字节** ✓ |
| `f` | **EffectManager** | 0 | 8 | 原版字节 |
| `f` | **AnimationActivationCurve** | 6 | 0 | **我们的字节** ✓ |
| `e` | — | 5 | 0 | **我们的字节** ✓ |
| `m` | **AnimationTurretCurve** | 0 | 2 | 原版字节 |
| `e` | — | 2 | 0 | **我们的字节** ✓ |

## 四、关键成员速查（真实可读名 ✓）

| 类 | 成员数 | 可读成员（节选）|
|---|---|---|
| `a`（LogicBoolean） | 32 | `abbreviationName(f)` · `allResourcesRegistry(f)` · `ammoResourceType(f)` · `autoCollectEnabled(f)` · `defaultValue(f)` · `depletedWhenEmpty(f)` · `descriptionText(f)` · `displayName(f)` · `effectResourceRef(f)` … |
| `d`（EffectConfig） | 30 | `appendResourceInHUD_whenThisZero(f)` · `applyConfig(m)` · `buildCostValue(f)` · `buildTimeValue(f)` · `displayResourceType(f)` · `displayTextAppendResource(f)` · `displayTextAppendResourceWithGap(f)` · `displayTextPostfix(f)` · `equivalentGlobalResource(f)` … |
| `n`（UnitTrait） | 25 | `boolean15(f)` · `boolean16(f)` · `boolean17(f)` · `boolean18(f)` · `bp(f)` · `buildTime(f)` · `cost1(f)` · `cost2(f)` · `field_f(f)` … |
| `d`（AnimationResourceCurve） | 21 | `activationCondition(f)` · `canAttackFilter(f)` · `canBuildFilter(f)` · `canMoveFilter(f)` · `canReclaimFilter(f)` · `canRepairFilter(f)` · `canTransportFilter(f)` · `checkOwnerTeam(f)` · `executionCondition(f)` … |
| `i` | 17 | `accelerationValue(f)` · `airSpeedMultiplier(f)` · `arrivalDistance(f)` · `avoidanceEnabled(f)` · `brakingDistance(f)` · `canHoverOverride(f)` · `collisionEnabled(f)` · `formationSpacing(f)` · `frictionValue(f)` … |
| `f`（EffectManager） | 8 | `clear(m)` · `copyFrom(m)` · `do_b(m)` · `fromUnit(m)` · `isEmpty(m)` · `overlapsWith(m)` · `reset(m)` · `size(m)` |
| `f`（AnimationActivationCurve） | 6 | `afterBody(f)` · `beforeBody(f)` · `beforeUI(f)` · `inactive(f)` · `onTop(f)` · `shadow(f)` |
| `e` | 5 | `frameHeight(f)` · `frameTextures(f)` · `frameWidth(f)` · `spriteColumns(f)` · `spriteRows(f)` |

## 五、映射备注（项目分析结论 ✓）

- **`d`**（AnimationResourceCurve）：Activation LogicBoolean condition
- **`d`**（AnimationResourceCurve）：Can attack filter (boolean)
- **`d`**（AnimationResourceCurve）：Can build filter (boolean)
- **`d`**（AnimationResourceCurve）：Can move filter (boolean)
- **`e`**：Frame height in pixels (int)
- **`e`**：Frame width in pixels (int)
- **`e`**：Multiple frame textures array (m.e[])
- **`e`**：Sprite sheet columns (int,  default 1)
- **`f`**（AnimationActivationCurve）：枚举常量 INI 名（RS-27 第 47 轮，由 <clinit> 的 ldc 序列提取）
- **`i`**：Acceleration rate (float,  default 0.1)
- **`i`**：Air speed multiplier (float)
- **`i`**：Arrival detection distance (float)
- **`i`**：Braking distance (float)
- **`m`**（AnimationTurretCurve）：证据串自报名(ldc 绑定, A 级)；描述符 (Lcom/corrodinggames/rts/game/units/custom/j;)V

## 核心概念

**自定义逻辑引擎**：把布尔/数值表达式写进 INI，从而**不写 Java 也能做玩法逻辑** ✓
（如"当血量 < 30% 且处于移动中 ⇒ 触发某行为"）。

| 组成 | 说明 |
|---|---|
| **表达式解析** | 词法 → 语法树（含优先级、括号、一元/二元运算） |
| **逻辑布尔函数** | 大量内建函数（比较资源、判定状态、随机、计时…） |
| **变量作用域** | 全局 / 单位 / 局部变量（含数组式数据） |
| **执行** | 每帧或事件触发时求值，结果驱动动作 |

## 关键机制

1. **数据即逻辑**：INI 里写表达式 ⇒ 模组作者可做复杂触发 ✓
2. **函数库**：`logicBooleans` 包（**215 个源文件** ✓ = 项目最大的逻辑包 ✓）
3. **求值上下文**：表达式拿到单位/世界状态 ⇒ 可读可判
4. **调试**：表达式出错时报**可读位置**
5. **性能**：表达式缓存 + 短路求值

## 修改指南

| 想改什么 | 改哪里 |
|---|---|
| 写逻辑（**推荐** ✓） | INI 的 `[logic_*]` 段 ✓ |
| 新增逻辑函数 | `game/units/custom/logicBooleans/` 加函数类 ✓ |
| 修表达式语义 | 解析器/求值器 ✓ |


## 六、子文档

- [`LOGIC-ENGINE.md`](LOGIC-ENGINE.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓


## 六、子文档

- [`LOGIC-ENGINE.md`](LOGIC-ENGINE.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓
