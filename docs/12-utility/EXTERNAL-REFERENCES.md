# 外部文献索引与机制交叉验证

> v19.133f98 | 2026-09-04 | 搜集 RTS 公开文献, 用于逆向交叉验证与映射补充
> 用途: ①机制事实对照 (文献 ↔ 映射) ②新协作者文献入口 ③INI 关键字权威参照

## 1. Rusted Warfare 官方/社区文献

| 来源 | 内容 | 价值 |
|------|------|------|
| [官方 Wiki](https://rustedwarfare.org/wiki/) | 游戏官方维基 (rustedwarfare.org — 有活跃编辑历史) | 机制/单位/版本说明 |
| [Fandom: Custom units](https://rustedwarfare.fandom.com/wiki/Custom_units) | 单位 .ini 完整关键字文档 + mod 电子表格 | **INI 关键字权威参照** |
| [Steam 指南: Plug and Play Modding](https://steamcommunity.com/sharedfiles/filedetails/?l=english&id=3102310259) | mod 打包/安装流程 | mod 结构验证 |
| [Modder's Manual By ES](https://www.scribd.com/document/944678450/Modder-s-Manual-By-ES-v0-1) | 社区手册: 全部 section 关键字目录 + 平衡建议 | 关键字补全 |
| [KosGames Modding Guide](https://kosgames.com/rusted-warfare-rts-modding-guide-49219/) | 综合 mod 指南 | 入门 |
| [namu.wiki 单位文档](https://en.namu.wiki/w/러스티드%20워페어/유닛) | 50+ 单位逐个机制说明 | 单位语义对照 |
| [维基百科: 铁锈战争](https://zh.wikipedia.org/zh-cn/铁锈战争) | 游戏概述 (开发历史/平台) | 背景 |
| [百度百科: 铁锈战争](https://baike.baidu.com/item/铁锈战争) | 中文概述 | 背景 |

## 2. 开源 RTS 架构文献 (交叉参考)

| 来源 | 内容 | 项目对应 |
|------|------|---------|
| [OpenRA Pathfinding System (wiki)](https://github.com/guidebee/OpenRA/wiki/OpenRA-Pathfinding-System) | 2D 引擎 3D 坐标体系 (CPos/WPos/MPos) + A* + **分层寻路** (HierarchicalPathFinder, GridSize=10) + Locomotor 移动层 | 10-pathfinding 域: PathFinder/PathSolver/AStarSearch (ASTAR-PATHFINDING.md) — 架构同构可互证命名 |
| [OpenRA Pathfinder refactor PR #7430](https://github.com/OpenRA/OpenRA/pull/7430) | 寻路缓存策略/性能预算讨论 | 项目 PathFinder 缓存语义 |
| [OpenRA 仓库](https://github.com/OpenRA/OpenRA) | 完整开源 C&C 类 RTS (C#) | 单位模拟/寻路/武器系统参考实现 |

## 3. 机制交叉验证表 (文献 ↔ 项目映射)

### 3.1 AI 系统 (04-ai 域)

| 文献事实 | 项目映射对照 | 状态 |
|---------|-------------|------|
| AI 难度 6 级 (Very Easy~Impossible) | AIDifficulty (gameFramework.n.h) 枚举 | ✓ 已映射 |
| 难度影响 AI 资源收入速度 (Hard = 1.4× 玩家) | AIStrategy 收入/经济字段 (aiTeam 相关) | 待验证倍率字段 |
| AI 经济决策: buildPriority/noneInBaseExtraPriority/maxEachBase (INI [ai] 节) | AIStrategy 建造优先级字段 | 映射候选 |

### 3.2 单位体系 (01-units 域)

| 文献事实 | 项目映射对照 | 状态 |
|---------|-------------|------|
| 50+ 单位 4 大类 (land/air/sea/experimental + bio 隐藏) | UnitType/ExperimentalUnit 家族 | ✓ 已映射 |
| 实验工厂生产时间 83.3 秒 | ExperimentalLandFactory 生产计时字段 | 验证候选 |
| 单位 tag 系统 (small/medium/tank; canOnlyAttackUnitsWithTags) | TagFilter/TeamTag 家族 | ✓ 已映射 |
| mutator1_ifUnitWithTags/areaDamageMultiplier | ProjectileWeapon 伤害倍率字段 | 映射候选 |

### 3.3 建筑树 (02-buildings 域)

| 文献事实 | 项目映射对照 | 状态 |
|---------|-------------|------|
| Command Center (导弹攻击) / Extractor (3 升级+守卫/超频模式) / 4 工厂 / RepairBay | Factory/RepairBayUnit/CommandCenter 家族 | ✓ 已映射 |
| 防御塔线: Cannon→T2(机枪/喷火/炮)→T3 重机枪 + 激光/闪电/炮/SAM/Flak | Turret 家族 (turret_1/main) | ✓ 已映射 |

### 3.4 INI 关键字 (09-custom 域 — 与 UNIT-INI-PARAMS.md 交叉)

| 文献 section | 关键字段 (官方) | 项目映射 |
|-------------|---------------|---------|
| [core] | price/maxHp/maxShield/shieldRegen/techLevel/buildSpeed/mass/armour/radius/energyMax/selfRegenRate/numBitsOnDeath | supplement ini-verified 190 条已覆盖大部分 ✓ |
| [attack] | canAttackFlyingUnits/maxAttackRange/shootDelay/turretTurnSpeed | ✓ |
| [turret_N] | barrelX/barrelY/recoilOffset/limitingAngle/energyUsage | 部分已映射 (UnitTurret 家族) |
| [projectile_N] | directDamage/areaDamage/pushForce(与 mass 互作用)/armourIgnoreAmount/mutatorN | **补充候选** (伤害系统细节) |
| [movement] | moveAccelerationSpeed/moveDecelerationSpeed/maxTurnSpeed/reverseSpeedPercentage | 移动参数 (MovementController) 已映射 ✓ |
| [effect_N] | spawnChance/attachedToUnit/scaleFrom/scaleTo/alpha/drawUnderUnits | EffectConfig 家族 ✓ |
| [ai] | buildPriority/noneInBaseExtraPriority/maxEachBase | AIStrategy 建造字段 (候选) |

## 4. 文献事实反哺映射 (下一步候选)

1. **AI 难度收入倍率** (6 档, Hard 1.4×) → AIStrategy/AIDifficulty 经济倍率字段定位
2. **projectile mutator 系统** (tag 条件伤害倍率) → ProjectileWeapon 字段补充
3. **pushForce ↔ mass 互作用** → 击退物理 (mass/radius 已映射, pushForce 待补)
4. **实验单位 83.3s 生产** → Factory 生产计时验证
5. **effect scaleFrom/scaleTo/alpha 动画参数** → ParameterAnimator 字段对照
