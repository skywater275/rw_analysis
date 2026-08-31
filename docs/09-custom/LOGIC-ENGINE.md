# Rusted Warfare v1.15 — 逻辑脚本引擎源码逆向
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 完整的嵌入式脚本语言: 120+文件, 70+内置函数, 类型化变量, 单位引用, INI解析
>
> 关键文件: `LogicBoolean.java`, `LogicBooleanGameFunctions.java`, `VariableScope.java`, `LogicBooleanLoader.java`

---

## 1. 架构总览

```
LogicBoolean (抽象基类)
├── 布尔运算
│   ├── AndBoolean, OrBoolean, NotBoolean
│   └── StaticBooleanTrue, StaticBooleanFalse
│
├── 比较器 (CompareJoinerBoolean)
│   ├── CompareEqual (Boolean/Numbers/Strings/Units)
│   ├── CompareGreaterThan/LessThan (Numbers)
│   ├── CompareNotEqual (Boolean/Numbers/Strings/Units)
│   └── MathAdd/Multiply/Divide/Subtract/Modulus (Numbers)
│
├── 游戏函数 (LogicBooleanGameFunctions, 70+)
│   ├── self.* — 单位属性
│   ├── game.* — 全局状态
│   └── team functions — 队伍查询
│
├── 数值函数 (LogicNumberFunction, 14个)
│   ├── 三角: sin, cos
│   ├── 距离: distance, direction
│   ├── 数学: sqrt, rnd, min, max, int, length
│   └── 标记: createMarker
│
├── 字符串 (LogicString)
│   ├── upper, lower, substring, select
│   ├── playername, teamName
│   └── debug, staticString
│
├── 单位引用 (UnitReference)
│   ├── self, parent, lastDamagedBy
│   ├── nearest, transporting, attachment
│   └── getOffset, memory, chained
│
└── 变量系统 (VariableScope)
    ├── VariableData: number, boolean, string, unit, null
    ├── VariableDataArray: number[], boolean[], unit[]
    ├── MemoryWriter: 持久化变量
    └── CachedWriter: 优化重求值
```

---

## 2. 解析: 从 INI 字符串到表达式树

### 2.1 入口

```java
LogicBoolean.create(String expression, ModUnitRegistry mod, String debugInfo)
```

解析器 (`LogicBooleanLoader`) 将 INI 字符串如:
```
"if not self.isOverLiquid() and not self.isMoving()"
"if self.hp < self.maxHp * 0.33"
"if numberOfUnitsInEnemyTeam(greaterThan=5)"
```
转换为 `LogicBoolean` 表达式树。

### 2.2 ParameterMapping — 参数绑定

每个函数类有 `ParameterMapping`，通过反射扫描字段，将 INI 参数绑定到 Java 字段:

```java
// "greaterThan=5, lessThan=10"
// → LogicBoolean 对象上设置 greaterThan=5, lessThan=10

// "full=true"
// → LogicBoolean 对象上设置 full=true
```

### 2.3 .with() 链式调用

函数注册时使用 `.with()` 预设参数:
```java
new HpBoolean()                              // self.hp
new HpBoolean().with("full=true")            // self.isHpFull
new SpeedBoolean().with("atTopSpeed=true")   // self.isAtTopSpeed
new HeightBoolean().with("flying=true")      // self.isFlying
new HeightBoolean().with("underwater=true")  // self.isUnderwater
```

---

## 3. 完整内置函数目录 (70+)

### 3.1 单位属性 (self.*)

#### 位置/状态
| 函数 | 别名 | 返回 | 说明 |
|------|------|------|------|
| `self.x` | — | number | X坐标 |
| `self.y` | — | number | Y坐标 |
| `self.height` | `self.z` | number | 高度 |
| `self.dir` | — | number | 朝向角度 |
| `self.speed` | — | number | 当前速度 |
| `self.maxMoveSpeed` | — | number | 最大移速 |
| `self.isMoving` | — | bool | 正在移动 |
| `self.hasActiveWaypoint` | — | bool | 有活跃路径点 |
| `self.numberOfQueuedWaypoints` | — | number | 排队路径点数 |
| `self.isAtTopSpeed` | `self.maxspeed` | bool | 达到极速 |
| `self.isReversing` | — | bool | 倒车中 |

#### 地形
| 函数 | 别名 | 说明 |
|------|------|------|
| `self.ground` | `self.isAtGroundHeight` | 在地面高度 |
| `self.flying` | `self.isFlying` | 飞行中 |
| `self.underwater` | `self.isUnderwater` | 在水下 |
| `self.inwater` | `self.isInWater` | 接触水面 |
| `self.isOverwater` | — | 在水面上方 |
| `self.isOverLiquid` | — | 在液体上方 |
| `self.isOverClift` | `self.isOverCliff` | 在悬崖上方 |
| `self.isOverPassableTile` | — | 在可行走瓦片上 |
| `self.isOverOpenLand` | — | 在开阔陆地 (type=LAND) |
| `self.isInMap` | — | 在地图内 |

#### HP/护盾/能量
| 函数 | 别名 | 说明 |
|------|------|------|
| `self.hp` | — | 当前HP (number) |
| `self.maxHp` | — | 最大HP |
| `self.maxShield` | — | 最大护盾 |
| `self.shield` | — | 当前护盾 |
| `self.energy` | — | 当前能量 |
| `self.energyIncludingQueued` | — | 含排队消耗的能量 |
| `self.isEnergyFull` | — | 能量满 |
| `self.isEnergyEmpty` | — | 能量空 |
| `self.isEnergyRecharging` | — | 充能中 |
| `self.maxEnergy` | — | 最大能量 |
| `self.ammo` | — | 弹药 (number) |
| `self.isAmmoEmpty` | — | 弹药用尽 |
| `self.ammoIncludingQueued` | — | 含排队消耗的弹药 |
| `self.hasTakenDamage` | — | 受过伤害 |

#### 标签/队伍
| 函数 | 别名 | 说明 |
|------|------|------|
| `self.tags` | `self.hasTags` | 拥有标签 |
| `self.globalTeamTags` | `self.hasGlobalTeamTags` | 全局队伍标签 |
| `self.hasFlag` | — | 持有旗帜 |
| `self.id` | — | 单位ID |
| `self.teamId` | — | 队伍ID |
| `self.isOnNeutralTeam` | — | 中立队伍 |
| `self.isControlledByAI` | — | AI控制 |

#### 建造/工厂
| 函数 | 说明 |
|------|------|
| `self.builtAmount` | 已建造数量 |
| `self.completed` | 是否完成建造 |
| `self.priceCredits` | 价格 |
| `self.queueSize` | 队列大小 |
| `self.numberOfConnections` | 连接数 |
| `self.numberOfAttachedUnits` | 附着单位数 |
| `self.hasParent` | 有父单位 |
| `self.hasResources` | 有资源 |
| `self.isResourceLargerThan` | 资源大于 |
| `self.resource` | 资源计数 |

#### 运输
| 函数 | 说明 |
|------|------|
| `self.transportingCount` | 运输中数量 |
| `self.transportingUnitWithTags` | 运输带标签单位 |
| `self.isTransportUnloading` | 正在卸载 |

#### 其他
| 函数 | 说明 |
|------|------|
| `self.kills` | 击杀数 |
| `self.timeAlive` | 存活时间 |
| `self.lastConverted` | 最后转换时间 |
| `self.customTimer` | 自定义计时器 |
| `self.isAttacking` | 攻击中 |
| `self.isDead` | 已死亡 |

### 3.2 全局 (game.*)

| 函数 | 说明 |
|------|------|
| `game.nukesEnabled` | 核弹启用 |
| `game.mapWidth` | 地图宽度 |
| `game.mapHeight` | 地图高度 |

### 3.3 队伍查询

| 函数 | 别名 | 参数 |
|------|------|------|
| `self.numberOfUnitsInTeam` | — | greaterThan, lessThan |
| `self.hasUnitInTeam` | — | greaterThan=0, lessThan=-1 |
| `self.noUnitInTeam` | — | greaterThan=-1, lessThan=1 |
| `self.numberOfUnitsInNeutralTeam` | — | neutralTeam=true |
| `self.numberOfUnitsInAggressiveTeam` | — | aggressiveTeam=true |
| `self.numberOfUnitsInAllTeams` | — | allTeams=true |
| `self.numberOfUnitsInEnemyTeam` | — | ally=false |
| `self.numberOfUnitsInAllyTeam` | `self.numberOfUnitsInAllyNotOwnTeam` | ally=true |
| `self.teamDefeatedTech` | — | 队伍科技战败 |
| `self.teamWipedOut` | — | 队伍被消灭 |
| `self.teamVictory` | — | 队伍胜利 |

### 3.4 动作 (thisActionIndex)

| 函数 | 别名 | 说明 |
|------|------|------|
| `thisActionIndex` | `index` | 动作重复次数索引 |

---

## 4. 数值函数

| 函数 | 说明 |
|------|------|
| `sin(x)` | 正弦 |
| `cos(x)` | 余弦 |
| `sqrt(x)` | 平方根 |
| `rnd(min, max)` | 随机数 |
| `distance(x, y)` | 到目标距离 |
| `distanceBetween(x1, y1, x2, y2)` | 两点间距离 |
| `distanceSquared` / `distanceBetweenSquared` | 距离平方 |
| `direction(x, y)` | 方向角 |
| `directionBetween` | 两点间方向 |
| `min(a, b)` / `max(a, b)` | 最小/最大 |
| `int(x)` | 取整 |
| `length(x)` | 长度 |
| `createMarker` | 创建标记 |

---

## 5. 字符串函数

| 函数 | 说明 |
|------|------|
| `upper(s)` | 转大写 |
| `lower(s)` | 转小写 |
| `substring(s, start, end)` | 子串 |
| `select(index, a, b, c...)` | 选择第N个 |
| `staticString("text")` | 静态字符串 |
| `playername` | 玩家名称 |
| `teamName` | 队伍名称 |
| `debug(s)` | 调试输出 |
| `numberToString(n)` | 数值转字符串 |

---

## 6. 单位引用 (UnitReference)

| 引用 | 说明 |
|------|------|
| `self` | 当前单位 (默认) |
| `parent` | 父单位 (运输/附着) |
| `lastDamagedBy` | 最后伤害来源 |
| `transporting` | 正在运输的单位 |
| `nearest(type, tags)` | 最近的匹配单位 |
| `attachment(name)` | 指定挂载点单位 |
| `activeWaypointTarget` | 当前路径点目标 |
| `eventSource` | 事件来源单位 |
| `thisActionTarget` | 当前动作目标 |
| `chained` | 链式单位 |
| `memory1` / `memory2` | 内存槽单位 |
| `firstUnit` | 第一个单位 |
| `getOffset(x, y)` | 偏移位置 |
| `getOffsetRelative(x, y)` | 相对偏移 |
| `null` | 空引用 |
| `placeholder` | 占位符 (解析时) |

---

## 7. 比较和数学运算

### 7.1 比较器

| 操作 | 适用类型 |
|------|---------|
| `==` (CompareEqual) | number, boolean, string, unit |
| `!=` (CompareNotEqual) | number, boolean, string, unit |
| `>` (CompareGreaterThan) | number |
| `>=` (CompareGreaterThanOrEqual) | number |
| `<` (CompareLessThan) | number |
| `<=` (CompareLessThanOrEqual) | number |

### 7.2 数学连接

| 操作 | 说明 |
|------|------|
| `+` (MathAdd) | 加法 |
| `-` (MathSubtract) | 减法 |
| `*` (MathMultiply) | 乘法 |
| `/` (MathDivide) | 除法 |
| `%` (MathModulus) | 取模 |

### 7.3 字符串连接

| 操作 | 说明 |
|------|------|
| `+` (StringJoiner) | 字符串拼接 |

---

## 8. 变量系统 (VariableScope)

### 8.1 数据类型

| VariableData 子类 | 类型 |
|-------------------|------|
| `VariableDataNumber` | 数值 |
| `VariableDataBoolean` | 布尔 |
| `VariableDataString` | 字符串 |
| `VariableDataUnit` | 单位引用 |
| `VariableDataNull` | 空值 |
| `VariableDataNumberArray` | 数值数组 |
| `VariableDataBoolArray` | 布尔数组 |
| `VariableDataUnitArray` | 单位数组 |

### 8.2 单位内存 (`defineUnitMemory`)

```ini
[core]
defineUnitMemory: number killCount, unit lastTarget
```

定义的单位内存变量通过 `VariableScope` 持久化存储:
- `MemoryWriter` — 写入变量值
- `MemoryWriterFactory` — 创建写入器
- `CachedWriter` — 缓存优化 (避免每帧重计算)
- `@memory` — INI 中引用内存变量

### 8.3 事件内存

```java
ReadEventMemoryLogicBoolean  — 读取事件触发时的内存快照
ReadUnitMemoryLogicBoolean   — 读取单位当前内存
```

---

## 9. 解析示例

```ini
# 条件: 不在液体上方 且 不在移动
condition: if not self.isOverLiquid() and not self.isMoving()

# 条件: HP < 最大HP的33%  
condition: if self.hp < self.maxHp * 0.33

# 条件: 敌军 > 5
condition: if numberOfUnitsInEnemyTeam(greaterThan=5)

# 条件: 拥有标签"ground"
condition: if self.hasTags("ground")

# 可见性: 能量满时显示按钮
[canBuild_superweapon]
isVisible: if self.isEnergyFull()
```

### 解析树

```
"if not self.isOverLiquid() and not self.isMoving()"
    │
    └── AndBoolean
        ├── NotBoolean
        │   └── OverLiquidBoolean (self context)
        └── NotBoolean
            └── MovingBoolean (self context)
```

---

## 10. 用途全覆盖

LogicBoolean 在 Mod 系统中的使用场景:

| INI 参数 | 用途 |
|----------|------|
| `isVisible` | 建造按钮可见条件 |
| `isLocked` | 建造按钮锁定条件 |
| `isLockedAlt` / `isLockedAlt2` | 替代锁定条件 |
| `autoTriggerCooldownTime` | 动作自动触发条件 |
| `generation_active` | 资源生成激活条件 |
| `transportUnitsCanUnloadUnits` | 卸载条件 |
| `transportUnitsKillOnDeath` | 运载死亡条件 |
| `canOnlyBeAttackedByUnitsWithTags` | 受攻击限制 |
| `showActionsWithMixedSelectionIfOtherUnitsHaveTag` | 混合选择显示 |
| Action `isAvailable` | 动作可用条件 |
| Action `isVisible` | 动作可见条件 |
| `condition` (事件) | 事件触发条件 |

---

## 11. 调用上下文

```
CallContext_self         — 默认: 条件在 self 单位上求值
CallContext_selfAndTarget — self + target 两个上下文
```

通过 `UnitContextChangingContext` 可以在表达式树中切换求值单位:
```ini
# 检查运输单位的标签
self.transportingUnitWithTags("elite")
```

---

## 12. 代码统计

```
logicBooleans/ 目录: 120+ 文件
├── LogicBoolean.java             — 抽象基类 + 内嵌类 (10个)
├── LogicBooleanGameFunctions.java — 70+ 游戏函数注册
├── LogicBooleanLoader.java       — INI→表达式树 解析器
├── CompareJoinerBoolean.java     — 比较+数学运算 (15个内嵌类)
├── LogicNumberFunction.java      — 数值函数 (14个内嵌类)
├── LogicString.java              — 字符串函数 (10个内嵌类)
├── UnitReference.java            — 单位引用 (18个内嵌类)
└── VariableScope.java            — 变量系统 (20个内嵌类)
```
