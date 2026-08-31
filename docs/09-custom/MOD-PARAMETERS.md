# Rusted Warfare — Mod 单位参数完整参考 (INI 格式)

> 来源: Rusted Warfare Mod 文档/wiki
> 
> 使用方式: 在 Mod 的 .ini 文件中定义单位属性，游戏启动时由 ModUnitRegistry 解析加载

---

## 0. INI 文件格式

### 基本格式

```
UTF-8 纯文本文件。游戏通过读取以 .ini 为扩展名的文件来获得某个单位的参数信息。
内容主要由节 (Section) 和参数 (Parameter) 两部分组成。
```

**节 (Section)** — 由两个半角方括号包裹，单独占一行:
```ini
[section]
```

**参数 (Parameter)** — 由名称和值构成，使用半角冒号分隔，通常单独占一行:
```ini
name: value
```

> 参数的从属关系: 参数从属于节。节写在参数行的上方，其下的参数即属于该节。
> 
> 允许重复书写一个节使其分布在文档的不同位置。
> 
> 部分节可以自行添加名称或序号（如 `NAME` 填名称，`#` 填序号 1-20），同类型不同名称的节各自独立。

### 节的类型 (v1.15, 共 20 种)

| 节名 | 描述 |
|------|------|
| `[core]` | **基础节** — 单位名称、生命值、体积、建筑与否等基本设置 |
| `[canBuild_NAME]` | **可建造节** — 单位能建造生产哪些单位、建造方式和条件 |
| `[graphics]` | **图像节** — 单位主体贴图等设置 |
| `[attack]` | **攻击节** — 单位能攻击与否、最大攻击范围等设置 |
| `[turret_NAME]` | **炮塔节** — 炮塔贴图、旋转速度、攻击间隔等设置 |
| `[projectile_NAME]` | **炮弹节** — 弹丸贴图、寿命、飞行速度、伤害等设置 |
| `[movement]` | **移动节** — 移动类型、速度、转向速度等设置 |
| `[ai]` | **AI节** — AI玩家对该单位的建造/升级概率及部分单位特性 |
| `[arm_#]` | **臂节** — 单位臂装饰物的位置、贴图等 (#=1~20) |
| `[leg_#]` | **腿节** — 同上 |
| `[attachment_NAME]` | **附属节** — 子附属单位的位置、能攻击与否等设置 |
| `[action_NAME]` | **操作节** — 资源手动添加、手动升级按钮、添加路径点等 |
| `[hiddenAction_NAME]` | **隐藏操作节** — 同上但不可见 |
| `[effect_NAME]` | **特效节** — 炮塔开火、弹丸飞行/爆炸等生成的特效 |
| `[animation_NAME]` | **动画节** — 移动、部署、攻击等时机播放的帧动画 |
| `[placementRule_NAME]` | **放置规则节** — 单位建造时放置的规则 |
| `[global_resource_NAME]` | **全局资源节** — 队伍内通用的自定义资源名称/样式 |
| `[resource_NAME]` | **资源节** — 具体单位自用的自定义资源名称/样式 |
| `[template_NAME]` | **模板节** — 节之间复制参数时选用 |
| `[comment_NAME]` | **注释节** — 大篇幅注释 |
| `[decal_NAME]` | **贴花节** — 单位身上的贴花、伪3D等设置 |

### 值的类型

| 类型 | 说明 | 示例 |
|------|------|------|
| `int` | 整数 (-2147483648~2147483647) | `500` |
| `float` | 浮点数 (支持 `infinity`, `-infinity`, `NaN`, `0`) | `1.5`, `3.0s` |
| `boolean`/`bool` | 布尔值 | `true`, `false` |
| `logicBoolean` | 逻辑布尔值 (true/false + 逻辑表达式) | `if not self.isOverLiquid()` |
| `string` | 字符串 | `"MyUnit"` |
| `unit` | 单位名 (不用 `()*`) | `tank` |
| `tag(s)` | 标签 (多个) | `ground, armored` |
| `unitsList(s)` | 单位列表 | `tank, artillery` |
| `resourceList(s)` | 资源列表 | `100, 50` |
| `effectsList(s)` | 特效列表 | `explosion, smoke` |
| `filePath(.ogg)(s)` | 文件路径 | `sounds/boom.ogg` |

### 特殊语法

- `${}` — 变量引用，引用其他参数的值
- `"""` — 多行字符串包裹
- 模板 `[template_NAME]` — 可复用的参数集，通过 `copyFrom` 引用

---

## [core] 核心参数

### 基础属性

| 参数 | 类型 | 描述 |
|------|------|------|
| `name` | string | 单位名称 |
| `class` | `"CustomUnitMetadata"` | 固定值，标识这是一个自定义单位 |
| `mass` | int | 重量 |
| `radius` | int | 半径 |
| `price` | int/resourceList | 造价 |
| `maxHp` | int | 最大血量 |
| `maxShield` | int | 最大护盾值 |
| `startShieldAtZero` | bool | 初始护盾值为0 |
| `shieldRegen` | float | 护盾自动回复速度 |
| `shieldRenderRadius` | int | 护盾显示半径 |
| `shieldDisplayOnlyDeflection` | bool | 护盾仅在受攻击时显示 |
| `shieldDeflectionDisplayRate` | float | 护盾受到攻击后闪烁消失速度 |
| `selfRegenRate` | float | 自动回血速度 |
| `buildSpeed` | float/float+"s" | 建造速度 |
| `selfBuildRate` | float | 自动自我建造速度 |
| `techLevel` | `"1"`/`"2"`/`"3"` | 科技等级 |
| `strictLevel` | `"0"`/`"1"` | 严格等级 |
| `armour` | int | 护甲值 |
| `armourMinDamageToKeep` | int | 伤害值低于护甲值时造成的伤害 |
| `altNames` | string(s) | 其他名称（多个） |

### 建造相关

| 参数 | 类型 | 描述 |
|------|------|------|
| `isBuilding` | bool | 是建筑物 |
| `footprint` | -int,-int,int,int | 建筑物占地大小 (左,上,右,下) |
| `constructionFootprint` | -int,-int,int,int | 摆放时建筑物的占地大小 |
| `displayFootprint` | -int,-int,int,int | 显示的建筑物占地大小 |
| `buildingSelectionOffset` | — | 建筑选择偏移 |
| `buildingToFootprintOffsetX` | — | 建筑到占地X偏移 |
| `buildingToFootprintOffsetY` | — | 建筑到占地Y偏移 |
| `placeOnlyOnResPool` | bool | 仅允许放置在矿坑上 |
| `isBuilder` | bool | 是建造者 |

### 实验/科技

| 参数 | 类型 | 描述 |
|------|------|------|
| `experimental` | bool | 该单位是实验单位（影响图标、统计、图层） |
| `isBio` | bool | 是生物 |
| `isBug` | bool | 是虫 |

### 流式建造

| 参数 | 类型 | 描述 |
|------|------|------|
| `streamingCost` | resourceList | 流式建造造价 |
| `switchPriceWithStreamingCost` | bool | 普通建造转换为流式建造 |

### 能量系统

| 参数 | 类型 | 描述 |
|------|------|------|
| `energyMax` | float | 最大能量值 |
| `energyRegen` | float | 能量自动回复速度 |
| `energyStartingPercentage` | float | 初始能量值占比 |
| `energyNeedsToRechargeToFull` | bool | 需要充能 |
| `energyRegenWhenRecharging` | float | 充能时能量回复速度 |
| `dieOnZeroEnergy` | bool | 能量值等于/低于0时死亡 |

### 资源/经济

| 参数 | 类型 | 描述 |
|------|------|------|
| `borrowResourcesWhileAlive` | resourceList | 存活时借取资源 |
| `borrowResourcesWhileBuilt` | resourceList | 完成建造时借取资源 |
| `generation_resources` | resourceList | 自动生成资源-每次生成量 |
| `generation_active` | logicBoolean | 自动生成资源激活条件 |
| `generation_credits` | int | 自动生成资金-每次生成量 |
| `generation_delay` | int | 自动生成资源-间隔时间 |
| `resourceRate` | float | 资源速率 |

### 资源回收

| 参数 | 类型 | 描述 |
|------|------|------|
| `canReclaimResources` | bool | 可以回收资源 |
| `canReclaimResourcesNextSearchRange` | int | 自动回收临近相似资源的距离 |
| `canReclaimResourcesOnlyWithTags` | tag(s) | 仅允许回收具有这些标签的资源 |
| `canReclaimUnitsOnlyWithTags` | tag(s) | 仅允许回收具有这些标签的单位 |
| `resourceReclaimMultiplier` | float | 回收资源的速度倍数 |
| `reclaimPrice` | resourceList | 回收价格（默认=price） |
| `resourceMaxConcurrentReclaimingThis` | int | 同时回收该资源的最大单位数量 |
| `similarResourcesHaveTag` | tag(s) | 归类为相似资源的标签 |

### 维修

| 参数 | 类型 | 描述 |
|------|------|------|
| `canRepairUnitsOnlyWithTags` | tag(s) | 仅允许修理具有这些标签的单位 |
| `canRepairBuildings` | bool | 允许修理建筑物 |
| `canRepairUnits` | bool | 允许修理单位 |
| `autoRepair` | bool | 主动修理（不会为了修理而移动） |
| `isUnrepairableUnit` | bool | 不可被纳米射线维修 |
| `nanoRange` | int | 纳米射线范围 |
| `nanoRepairSpeed` | float | 维修速度 |
| `nanoBuildSpeed` | float | 建造速度倍数 |
| `nanoUnbuildSpeed` | float | 反建造速度倍数 |
| `nanoReclaimSpeed` | float | 回收速度 |
| `nanoRangeForRepairIsMelee` | bool | 贴身进行维修 |
| `nanoRangeForReclaimIsMelee` | bool | 贴身进行回收 |
| `nanoRangeForRepair` | int | 修理纳米射线范围 |
| `nanoRangeForReclaim` | int | 回收纳米射线范围 |
| `nanoFactorySpeed` | float | 内部工厂建造速度倍数 |
| `extraBuildRangeWhenBuildingThis` | int | 建造该单位特例使用的纳米射线距离 |

### 运输

| 参数 | 类型 | 描述 |
|------|------|------|
| `transportSlotsNeeded` | int | 运输该单位所需槽位数量 |
| `maxTransportingUnits` | int | 该单位具有的运输槽位数量 |
| `transportUnitsRequireTag` | tag(s) | 仅允许运输具有这些标签的单位 |
| `transportUnitsRequireMovementType` | movementType(s) | 仅允许运输这些移动类型的单位 |
| `transportUnitsBlockAirAndWaterUnits` | bool | 不允许运输空中和水中单位 (默认=true) |
| `transportUnitsEachUnitAlwaysUsesSingleSlot` | bool | 每个单位只占一格运输槽位 |
| `transportUnitsKeepBuiltUnits` | bool | 建造完成的单位被放到运输槽位中 |
| `transportUnitsCanUnloadUnits` | logicBoolean | 单位允许卸载-条件 |
| `transportUnitsAddUnloadOption` | bool | 添加卸载按钮 (默认=true) |
| `transportUnitsUnloadDelayBetweenEachUnit` | float | 卸载时单位出来的时间间隔 |
| `transportUnitsKillOnDeath` | logicBoolean | 运输单位死亡时也杀死被运输的单位-条件 |
| `transportUnitsHealBy` | float | 被运输的单位获得的治疗速度 |
| `transportUnitsBlockOtherTransports` | bool | 不允许运输具有运输能力的单位 (默认=true) |
| `whileNeutralTransportAnyTeam` | bool | 中立时运输任何队伍的单位 |
| `whileNeutralConvertToTransportedTeam` | bool | 中立时转换为被运输单位的队伍 |
| `convertToNeutralIfNotTransporting` | bool | 若未运输任何单位则转换为中立单位 |
| `transportUnitsOnTeamChangeKeepCurrentTeam` | bool | 队伍变更时保持当前队伍 |

### 显示/UI

| 参数 | 类型 | 描述 |
|------|------|------|
| `displayText` | string | 显示的名称 |
| `displayText_{LANG}` | string | 显示名称（其他语言） |
| `displayDescription` | string | 显示的单位描述 |
| `displayDescription_{LANG}` | string | 显示描述（其他语言） |
| `displayLocaleKey` | string | 引用内置本地化文件的文本 |
| `displayRadius` | int | 显示的半径 |
| `uiTargetRadius` | int | 作为玩家命令目标的半径 |
| `showInEditor` | bool | 在沙盒模式中显示 |
| `showOnMinimap` | bool | 在小地图中显示 |
| `showOnMinimapToEnemies` | bool | 在敌方小地图中显示 |
| `showActionsWithMixedSelectionIfOtherUnitsHaveTag` | tag(s) | 选中混合部队时显示它们的action |

### 视野/碰撞

| 参数 | 类型 | 描述 |
|------|------|------|
| `fogOfWarSightRange` | int | 战争迷雾中的视野范围 |
| `fogOfWarSightRangeWhileNotBuilt` | int | 未完成建造时的迷雾视野 |
| `softCollisionOnAll` | int | 软碰撞 |
| `disableAllUnitCollisions` | bool | 禁用碰撞体积 |

### 选择/控制

| 参数 | 类型 | 描述 |
|------|------|------|
| `isUnselectable` | bool | 不能被选中 |
| `isUnselectableAsTarget` | bool | 不能作为指令的目标 |
| `canNotBeDirectlyAttacked` | bool | 不能被直接攻击（可受范围伤害） |
| `canNotBeDamaged` | bool | 受到伤害时不会损失生命值 |
| `canNotBeGivenOrdersByPlayer` | bool | 玩家不能给该单位下达指令 |
| `canOnlyBeAttackedByUnitsWithTags` | tag(s) | 仅允许具有这些标签的单位攻击 |
| `disableDeathOnZeroHp` | bool | 生命值不影响生死 |
| `dieOnConstruct` | bool | 进行纳米建造时死亡 |

### 初始单位配置

| 参数 | 类型 | 描述 |
|------|------|------|
| `isPickableStartingUnit` | bool | 可作为初始单位 |
| `startFallingWhenStartingUnit` | bool | 作为初始单位时从天而降 |
| `onNewMapSpawn` | enum | 开局时生成位置: `emptyResourcePools_asNeutral`/`emptyOrOccupiedResourcePools_asNeutral`/`mapCenter_asNeutral`/`mapCenter_eachActiveTeam`/`spawnPoint_eachActiveTeam` |

### 中立/队伍

| 参数 | 类型 | 描述 |
|------|------|------|
| `stayNeutral` | bool | 维持中立（如果单位归属中立队伍） |
| `createNeutral` | bool | 生成时归属中立队伍 |
| `createOnAggressiveTeam` | bool | 生成时归属敌意队伍 |
| `allowCaptureWhenNeutralByAI` | bool | 为中立单位时允许被AI捕获 |

### 标签/内存

| 参数 | 类型 | 描述 |
|------|------|------|
| `tags` | tag(s) | 单位标签（作为筛选条件） |
| `defineUnitMemory` | dataType variableName(s) | 定义单位内存变量 |
| `updateUnitMemory` | — | 更新单位内存 |
| `updateUnitMemoryRate` | — | 更新单位内存频率 |
| `@memory` | — | 内存引用 |

### 死亡效果

| 参数 | 类型 | 描述 |
|------|------|------|
| `numBitsOnDeath` | int | 死亡时爆炸生成碎片数量 |
| `nukeOnDeath` | bool | 死亡时核爆 |
| `nukeOnDeathRange` | int | 死亡核爆伤害范围 |
| `nukeOnDeathDamage` | int | 死亡核爆伤害值 |
| `nukeOnDeathDisableWhenNoNuke` | bool | 禁核模式时不会核爆死亡 |
| `fireTurretXAtSelfOnDeath` | turretsList | 死亡时使用该炮塔向自己位置开火 |
| `explodeOnDeath` | bool | 死亡时爆炸 |
| `explodeOnDeathGroundCollision` | bool | 死亡时（空中单位）撞击地面后爆炸 |
| `explodeTypeOnDeath` | enum | 爆炸类型: `verysmall`/`small`/`normal`/`large`/`largeUnit`/`building`/`buildingNoShockwaveOrSmoke`/`verylargeBuilding` |
| `effectOnDeath` | effectsList(s) | 死亡时生成的效果 |
| `effectOnDeathGroundCollision` | effectsList(s) | 死亡时撞击地面生成的效果 |
| `unitsSpawnedOnDeath` | unitsSpawned | 死亡时生成的单位 |
| `unitsSpawnedOnDeath_setToTeamOfLastAttacker` | bool | 死亡生成的单位归最后攻击者队伍 |
| `hideScorchMark` | bool | 隐藏死亡后焦痕 |
| `soundOnDeath` | filePath(.ogg)(s) | 死亡时播放声音 |
| `effectOnDeathIfUnbuilt` | effectsList(s) | 未完成建造时死亡生成的效果 |

### 音效

| 参数 | 类型 | 描述 |
|------|------|------|
| `soundOnAttackOrder` | filePath(.ogg)(s) | 下达攻击命令时播放声音 |
| `soundOnMoveOrder` | filePath(.ogg)(s) | 下达移动命令时播放声音 |
| `soundOnNewSelection` | filePath(.ogg)(s) | 被选中时播放声音 |

### 自动触发 (Action)

| 参数 | 类型 | 描述 |
|------|------|------|
| `autoTriggerCooldownTime` | int/(float)s | action自动触发冷却时间 |
| `autoTriggerCooldownTime_allowDangerousHighCPU` | bool | 解除安全限制 |
| `autoTriggerCheckRate` | enum | 检测频率: `everyFrame`/`every4Frames`/`every8Frames` |
| `autoTriggerCheckWhileNotBuilt` | bool | 未完成建造时进行触发检测 |

### Mod 管理

| 参数 | 类型 | 描述 |
|------|------|------|
| `copyFrom` | filePath(s) | 从其他ini文件中复制代码 |
| `dont_load` | bool | 不被游戏加载 |
| `overrideAndReplace` | unitsList(s) | 覆盖这些单位 |
| `isLocked` | bool | 在建造列表中锁定 |
| `isLockedIfGameModeNoNuke` | bool | 无核模式时在建造列表中锁定 |
| `ignoreInUnitCapCalculation` | bool | 不计入单位数量上限统计（建筑物默认=true） |

### 出口配置

| 参数 | 类型 | 描述 |
|------|------|------|
| `exit_x` | float | 出口相对位置X |
| `exit_y` | float | 出口相对位置Y |
| `exit_dirOffset` | float | 出口相对角度 |
| `exit_heightOffset` | float | 出口相对高度 |
| `exit_moveAwayAmount` | float | 出口自动移动距离 |
| `exitHeightIgnoreParent` | — | 出口忽略父级 |

---

## [canBuild_NAME] 可建造列表项

定义工厂/建造者的建造列表中每个可建造的单位。

| 参数 | 类型 | 描述 |
|------|------|------|
| `name` | string(s) | 名称 |
| `pos` | float | 在建造列表中的顺序（值越大越靠后） |
| `tech` | `1`/`2`/`3` | 指定科技等级（对陆军工厂/空军基地等有效） |
| `forceNano` | bool | 使用纳米光线进行建造 |
| `isVisible` | logicBoolean | （在建造列表中）可见-条件 |
| `isLocked` | logicBoolean | 在建造列表中锁定-条件 |
| `isLockedMessage` | string | 锁定时显示消息 |
| `isLockedMessage_{LANG}` | string | 锁定时显示消息-其他语言 |
| `isLockedAlt` | logicBoolean | 另一种锁定情形-条件 |
| `isLockedAltMessage` | string | 另一种锁定时显示消息 |
| `isLockedAlt2` | logicBoolean | 另一种锁定情形2-条件 |
| `isLockedAlt2Message` | string | 另一种锁定时显示消息2 |
| `addResources` | int/resourceList(s) | 开始建造时添加资源 |
| `price` | int/resourceList(s) | 造价（覆盖被建造单位原来的造价） |
| `isGuiBlinking` | logicBoolean | 建造列表中该按钮闪烁-条件 |

### 建造源: builtFrom_{NUM}

| 参数 | 类型 | 描述 |
|------|------|------|
| `builtFrom_{NUM}_name` | unitsList(s) | 建造自哪个单位 |
| `builtFrom_{NUM}_pos` | float | 在建造列表中的顺序 |
| `builtFrom_{NUM}_forceNano` | bool | 使用纳米射线 |
| `builtFrom_{NUM}_isLocked` | logicBoolean | 锁定建造条件 |
| `builtFrom_{NUM}_isLockedMessage` | string(s) | 锁定时消息 |

---

## 其他 INI 段

| 段名 | 描述 |
|------|------|
| `[graphics]` | 图形/渲染配置 |
| `[attack]` | 攻击配置 |
| `[turret_NAME]` | 炮塔定义 |
| `[projectile_NAME]` | 弹丸定义 |
| `[movement]` | 移动配置: `movementType:none/land/air/sea/...`, `movementEffect:smoke/...` |
| `[ai]` | AI 参数 |
| `[arm_#]` / `[leg_#]` | 肢体动画部件 |
| `[attachment_NAME]` | 挂载点 |
| `[action_NAME]` / `[hiddenAction_NAME]` | 动作定义 |
| `[effect_NAME]` | 特效定义 |
| `[animation_NAME]` | 动画定义 |
| `[placementRule_NAME]` | 放置规则 |
| `[resource_NAME]` | 资源定义 |
| `[global_resource_NAME]` | 全局资源定义 |
| `[decal_NAME]` | 贴花定义 |
| `[template_NAME]` | 模板定义 |
| `[comment_NAME]` | 注释 |

---

## 附录: 值的类型

| 类型 | 说明 | 示例 |
|------|------|------|
| `int` | 整数 (-2147483648~2147483647) | `500` |
| `float` | 浮点数 (支持 infinity, -infinity, NaN, 0) | `1.5`, `3.0s` |
| `boolean` / `bool` | 布尔值 | `true`, `false` |
| `logicBoolean` | 逻辑布尔值 (true/false + 逻辑表达式) | `if not self.isOverLiquid()` |
| `string` | 字符串 | `"MyUnit"` |
| `unit` | 单位名 (不用 `()*`) | `tank` |
| `tag(s)` | 标签 (多个) | `ground, armored` |
| `unitsList(s)` | 单位列表 | `tank, artillery` |
| `resourceList(s)` | 资源列表 | `100, 50` |
| `effectsList(s)` | 特效列表 | `explosion, smoke` |
| `filePath(.ogg)(s)` | 文件路径 | `sounds/boom.ogg` |

### 特殊语法
- `${}` — 变量引用
- `"""` — 多行字符串
- 模板 — 可复用的参数集
