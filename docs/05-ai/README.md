# 05-ai — AI 系统

> **职责**：非玩家队伍的自主发展、造兵与进攻

> 本文处于**项目分析上限** ✓：数据全部来自映射库 / 反编译树 / 交付物实测 ✓，口径见 [../STATUS.md](../STATUS.md) ✓。

## 一、数据总览

| 指标 | 值 |
|---|---|
| 映射条目 | **386** 条 |
| 涉及类 | **33** 个 |
| 有可读名 | **32** 个（97%）|
| ★ **已替换为我们的字节** | **11** 个（33%）|
| 主包 | `com.corrodinggames.rts.game.a` · `com.corrodinggames.rts.gameFramework.n` · `game.a.i` |

### 交付物替换状态（★ 该域我们掌控多少 ✓）

| 状态 | 类数 | 含义 |
|---|---|---|
| **我们的字节** ✓ | **11** | 已由反编译源重建并在交付物中替换 |
| 原版字节 | 21 | 仍使用原版混淆字节（结构一致 ✓）|
| 未进交付物 | 1 | 该域映射涉及、但不属交付物条目 |

## 二、包结构与源码位置

| 混淆包 | 映射行 |
|---|---|
| `com.corrodinggames.rts.game.a` | 309 |
| `com.corrodinggames.rts.gameFramework.n` | 76 |
| `game.a.i` | 1 |

**03 源码位置**（该域主包 ✓）：

| 包（可读路径） | 文件 | 行数 |
|---|---|---|
| `com/corrodinggames/rts/gameFramework/network` | 81 | 12066 |
| `com/corrodinggames/rts/gameFramework` | 95 | 11769 |
| `com/corrodinggames/rts/game` | 24 | 7757 |
| `com/corrodinggames/rts/game/ai` | 28 | 5482 |
| `com` | 6 | 1080 |
| `com/corrodinggames/rts` | 13 | 658 |
| `com/corrodinggames/rts/game/ai/strategies` | 4 | 81 |

## 三、★ 全量类清单（33 个 ✓）

> `状态`：**我们的字节** ✓ / 原版字节 / 未进交付物 ✓；`字段`/`方法` 为该类**已映射成员数** ✓。

| 混淆类名 | 可读名 | 字段 | 方法 | 状态 |
|---|---|---|---|---|
| `a` | **AIStrategy** | 71 | 36 | 原版字节 |
| `i` | **CombatMain** | 17 | 47 | 原版字节 |
| `g` | **CombatAction2** | 25 | 13 | 原版字节 |
| `f` | **AIWaveSystem** | 26 | 7 | 原版字节 |
| `a` | **AITask** | 26 | 0 | 原版字节 |
| `n` | **TransporterGroup** | 14 | 8 | 原版字节 |
| `o` | **AIStrategyNode** | 7 | 8 | 原版字节 |
| `h` | **AIUnitGroupBase** | 2 | 10 | 原版字节 |
| `g` | **AIWaveParser** | 8 | 1 | 原版字节 |
| `d` | **UnitBuildStrategy** | 1 | 6 | 原版字节 |
| `l` | **TargetFilter** | 7 | 0 | **我们的字节** ✓ |
| `c` | **BuildPreferenceCache** | 2 | 4 | **我们的字节** ✓ |
| `j` | **BaseZoneType** | 5 | 1 | **我们的字节** ✓ |
| `k` | **BaseZoneStage** | 6 | 0 | **我们的字节** ✓ |
| `b` | **IncludeExcludeMode** | 5 | 0 | **我们的字节** ✓ |
| `e` | **CombatAction** | 3 | 1 | **我们的字节** ✓ |
| `f` | **AIUnitActionUtils** | 0 | 2 | **我们的字节** ✓ |
| `l` | **RallyGroup** | 0 | 2 | **我们的字节** ✓ |
| `a$1` | **AIStrategy$1** | 0 | 1 | 原版字节 |
| `a$10` | **AIStrategy$10** | 0 | 1 | 原版字节 |
| `a$11` | **AIStrategy$11** | 0 | 1 | 原版字节 |
| `a$12` | **AIStrategy$12** | 0 | 1 | 原版字节 |
| `a$13` | **AIStrategy$13** | 0 | 1 | **我们的字节** ✓ |
| `a$2` | **AIStrategy$2** | 0 | 1 | **我们的字节** ✓ |
| `a$3` | **AIStrategy$3** | 0 | 1 | 原版字节 |
| `a$4` | **AIStrategy$4** | 0 | 1 | 原版字节 |
| `a$5` | **AIStrategy$5** | 0 | 1 | 原版字节 |
| `a$6` | **AIStrategy$6** | 0 | 1 | 原版字节 |
| `a$7` | **AIStrategy$7** | 0 | 1 | 原版字节 |
| `a$8` | **AIStrategy$8** | 0 | 1 | 原版字节 |
| `a$9` | **AIStrategy$9** | 0 | 1 | 原版字节 |
| `h` | **AIDifficulty** | 1 | 0 | **我们的字节** ✓ |
| `i` | — | 1 | 0 | 未进交付物 |

## 四、关键成员速查（真实可读名 ✓）

| 类 | 成员数 | 可读成员（节选）|
|---|---|---|
| `a`（AIStrategy） | 107 | `aiBehaviorList(f)` · `aiBehaviors(f)` · `aiDisabled(f)` · `aiSleeping(f)` · `airPathExists(f)` · `airUnitCount(f)` · `allowExpansion(f)` · `amphibiousCount(f)` · `attackingCount(f)` … |
| `i`（CombatMain） | 64 | `attackNearbyEnemies(m)` · `buildOrderList(f)` · `cachedBuildPoint(f)` · `canAttackTarget(m)` · `cancelAttack(m)` · `chooseBuildDirection(m)` · `chooseUnitTypeToBuild(m)` · `countDefenders(m)` · `countEnemiesInZone(m)` … |
| `g`（CombatAction2） | 38 | `assignBaseZone(m)` · `attackMoveEnabled(f)` · `baseZone(f)` · `collectUnits(m)` · `computeMovementType(m)` · `createGroupForUnit(m)` · `currentTarget(f)` · `defenseTimer(f)` · `elapsedTimer(f)` … |
| `f`（AIWaveSystem） | 33 | `aiEnabled(f)` · `attackTargets(f)` · `boolean11(f)` · `boolean12(f)` · `briefingPending(f)` · `checkAutoAttackProximity(m)` · `classicMode(f)` · `getCurrentWave(m)` · `infiniteWaves(f)` … |
| `a`（AITask） | 26 | `completionEventId(f)` · `currentAttempt(f)` · `displayMessage(f)` · `failureEventId(f)` · `hasStarted(f)` · `isActive(f)` · `isCompleted(f)` · `isRepeating(f)` · `mapSpawnRef(f)` … |
| `n`（TransporterGroup） | 22 | `capacity(f)` · `collectTransporterUnits(m)` · `dropX(f)` · `dropY(f)` · `findTargetGroup(m)` · `findTargetTimer(f)` · `hasTargetGroup(m)` · `isEnabled(m)` · `lifetimeTimer(f)` … |
| `o`（AIStrategyNode） | 15 | `aiController(f)` · `centerX(f)` · `centerY(f)` · `destroy(m)` · `destroyed(f)` · `distanceToUnit(m)` · `findBuildPoint(m)` · `getPointF(m)` · `getTargetPoint(m)` … |
| `h`（AIUnitGroupBase） | 12 | `addUnitHook(m)` · `cleanupDead(m)` · `cleanupStranded(m)` · `destroy(m)` · `getUnitCount(m)` · `isInZoneQueue(m)` · `releaseAll(m)` · `removeUnitHook(m)` · `reset(m)` … |

## 五、映射备注（项目分析结论 ✓）

- **`a`**（AIStrategy）："[I] 按单位位置找区域 (tier3Update(eo"
- **`a`**（AIStrategy）："[I] 随机敌方单位位置 (PointF"
- **`a`**（AIStrategy）："[V] ArrayList; 区域快照 (zoneQueue 的拷贝"
- **`a`**（AIStrategy）："[V] float; Tier1 累计器 (25ms 周期"
- **`a$1`**（AIStrategy$1）：位置无关配对导出（02↔反向源唯一性配对）；见 RENAME-DAMAGE/STATUS §〇.2
- **`a$10`**（AIStrategy$10）：位置无关配对导出（02↔反向源唯一性配对）；见 RENAME-DAMAGE/STATUS §〇.2
- **`a$11`**（AIStrategy$11）：位置无关配对导出（02↔反向源唯一性配对）；见 RENAME-DAMAGE/STATUS §〇.2
- **`a$12`**（AIStrategy$12）：位置无关配对导出（02↔反向源唯一性配对）；见 RENAME-DAMAGE/STATUS §〇.2
- **`a$13`**（AIStrategy$13）：位置无关配对导出（02↔反向源唯一性配对）；见 RENAME-DAMAGE/STATUS §〇.2
- **`a$2`**（AIStrategy$2）：位置无关配对导出（02↔反向源唯一性配对）；见 RENAME-DAMAGE/STATUS §〇.2
- **`a$3`**（AIStrategy$3）：位置无关配对导出（02↔反向源唯一性配对）；见 RENAME-DAMAGE/STATUS §〇.2
- **`a$4`**（AIStrategy$4）：位置无关配对导出（02↔反向源唯一性配对）；见 RENAME-DAMAGE/STATUS §〇.2
- **`a$5`**（AIStrategy$5）：位置无关配对导出（02↔反向源唯一性配对）；见 RENAME-DAMAGE/STATUS §〇.2
- **`a$6`**（AIStrategy$6）：位置无关配对导出（02↔反向源唯一性配对）；见 RENAME-DAMAGE/STATUS §〇.2

## 核心概念

**AI 系统**：让非玩家队伍自主发展、造兵、进攻、防守。

| 组成 | 说明 |
|---|---|
| **AI 策略** | 难度分级（Easy…Insane）+ 行为参数表 |
| **经济** | 采矿/建矿场/建工厂的队列与优先级 |
| **军事** | 组队、集结、进攻波次、撤退判定 |
| **威胁评估** | 感知敌方单位/建筑，选择目标 |
| **路径与命令** | 复用单位系统的命令队列 |

## 关键机制

1. **难度参数化**：收入倍率、建造速度、攻击性等由难度表控制
2. **波次系统**：定期组队进攻（含集结与超时）
3. **策略模式**：不同难度/地图可用不同策略实现
4. **与玩家同构**：AI 下达的是**同样的命令** ⇒ 回放/联机一致 ✓
5. **调试通道**：有 AI 调试输出（`ai_debug`）便于观察决策

## 修改指南

| 想改什么 | 改哪里 |
|---|---|
| AI 强度 | 难度参数表 + INI ✓ |
| AI 行为 | `game/ai/` 策略类 ✓ |
| 新策略 | 加策略实现并在难度表注册 ✓ |


## 六、子文档

- [`AI-ARCHITECTURE.md`](AI-ARCHITECTURE.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓


## 六、子文档

- [`AI-ARCHITECTURE.md`](AI-ARCHITECTURE.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓
