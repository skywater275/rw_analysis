# Rusted Warfare v1.15 — 混淆名→实际含义 完整映射

> 来源: game-lib.jar CFR 0.152 全量解混淆 (1,698类, 188,456行)
> 交叉验证: RW-HPS 服务器 + rwTool + 30回放
> 日期: 2026-06-07

---

## 1. 包级映射

| 混淆包路径 | 实际含义 |
|-----------|---------|
| `com.corrodinggames.rts.gameFramework` | 游戏引擎框架 (核心引擎类) |
| `com.corrodinggames.rts.gameFramework.j` | 网络/二进制IO |
| `com.corrodinggames.rts.gameFramework.n` | AI/任务系统 (14类) |
| `com.corrodinggames.rts.gameFramework.f` | 工具/数学/三角函数 |
| `com.corrodinggames.rts.gameFramework.utility` | 数据结构 (Array/Deque/RingBuffer) |
| `com.corrodinggames.rts.gameFramework.m` | 资源/纹理管理 |
| `com.corrodinggames.rts.gameFramework.g` | 数据访问对象 |
| `com.corrodinggames.rts.game` | 游戏逻辑层 |
| `com.corrodinggames.rts.game.a` | 战斗/世界/弹丸 |
| `com.corrodinggames.rts.game.b` | 地图/渲染 |
| `com.corrodinggames.rts.game.units` | 单位系统 |
| `com.corrodinggames.rts.game.units.a` | 行为/动作枚举 |
| `com.corrodinggames.rts.game.units.d` | 地面/实验单位 |
| `com.corrodinggames.rts.game.units.e` | 建筑 |
| `com.corrodinggames.rts.game.units.custom` | 自定义单位/Mod |
| `com.corrodinggames.rts.game.units.custom.d` | 组件系统 (Resource/Weapon/...) |
| `com.corrodinggames.rts.game.units.custom.e` | 数据组件 (Resource/Income/...) |
| `com.corrodinggames.rts.java` | 平台入口/音频 |
| `com.corrodinggames.librocket` | LibRocket HTML UI JNI |
| `com.corrodinggames.librocket.scripts` | UI脚本控制器 |

---

## 2. 类映射 (57个核心类)

### 2.1 游戏引擎框架 (gameFramework/)

| 混淆名 | 完整路径 | 实际含义 | 行数 | 说明 |
|--------|---------|---------|------|------|
| `l` | gameFramework.l | **GlobalState** | 1486 | 全局单例, 所有引擎引用入口 |
| `n` | gameFramework.n | **GameState** | — | 抽象基类, 玩家/队伍状态 |
| `ba` | gameFramework.ba | **ReplayEngine** | 821 | 回放录制/播放 |
| `e` | gameFramework.e | **Command** | 639 | 玩家指令反序列化 (20字段) |
| `y` | gameFramework.y | **GameSaver** | — | .rwsave 存档管理 |
| `bb` | gameFramework.bb | **BackgroundWriter** | — | 后台写入线程 |
| `bd` | gameFramework.bd | **DataBlock** | — | 回放数据块结构 |
| `bg` | gameFramework.bg | **StatsManager** | 141 | 统计管理器 (l.bY) |
| `bo` | gameFramework.bo | **StatsRecord** | 63 | 单玩家统计数据 |
| `bn` | gameFramework.bn | **StatsHistory** | 181 | 统计历史时间线 |
| `bh` | gameFramework.bh | **StatsSample** | — | 采样点 {tick, value} |
| `bj` | gameFramework.bj | **StatsCategory** | — | 统计分类枚举 (4值) |
| `bl` | gameFramework.bl | **PeriodicTimer** | — | 周期采样定时器 |
| `br` | gameFramework.br | **ExtraManager** | — | 额外管理器 (l.cd) |
| `w` | gameFramework.w | **GameObject** | — | 序列化对象基类 |
| `bq` | gameFramework.bq | **BaseGameObject** | — | 所有序列化对象基类 |

### 2.2 网络/IO (gameFramework.j/)

| 混淆名 | 完整路径 | 实际含义 | 行数 | 说明 |
|--------|---------|---------|------|------|
| `ad` | gameFramework.j.ad | **NetEngine** | 5359 | 网络引擎, 连接/包路由/同步 |
| `k` | gameFramework.j.k | **InputNetStream** | — | 二进制反序列化读取器 |
| `as` | gameFramework.j.as | **OutputNetStream** | — | 二进制序列化写入器 |
| `c` | gameFramework.j.c | **PlayerConnect** | — | 玩家连接管理 |

### 2.3 游戏逻辑层 (game/)

| 混淆名 | 完整路径 | 实际含义 | 行数 | 说明 |
|--------|---------|---------|------|------|
| `n` | game.n | **PlayerState** | — | 玩家/GameState (extends gameFramework.n) |
| `s` | game.s | **TeamUnitTracker** | 140 | 队伍收入/建造统计 |
| `t` | game.t | **BuildQueue** | — | 建造队列 |
| `f` | game.f | **MovementController** | 1761 | 寻路/移动/碰撞回避 |
| `i` | game.i | **GameScreen** | 2204 | 游戏主画面 (HUD/输入/建造菜单) |
| `p` | game.p | **UnitManager** | — | 单位管理器 |

### 2.4 战斗系统 (game/a/)

| 混淆名 | 完整路径 | 实际含义 | 行数 | 说明 |
|--------|---------|---------|------|------|
| `a` | game.a.a | **GameWorld** | 1910 | 主循环 (收入/战斗/更新) |
| `g` | game.a.g | **CombatMain** | 700 | 战斗维护 (索敌/伤害/溅射) |
| `i` | game.a.i | **Projectile** | 1221 | 弹丸物理/碰撞/自毁 |
| `c` | game.a.c | **CombatManager** | — | 战斗管理器 |
| `e` | game.a.e | **CombatAction** | — | 抽象战斗行为 |
| `b` | game.a.b | **CombatSubAction** | — | 子战斗行为 |

### 2.5 地图系统 (game/b/)

| 混淆名 | 完整路径 | 实际含义 | 行数 | 说明 |
|--------|---------|---------|------|------|
| `b` | game.b.b | **MapEngine** | 1523 | 地图加载/渲染/迷雾/TMX解析 |
| `c` | game.b.c | **MapRenderer** | — | 地图渲染器 |
| `g` | game.b.g | **MapLayer** | — | 地图图层 |

### 2.6 单位系统 (game/units/)

| 混淆名 | 完整路径 | 实际含义 | 行数 | 说明 |
|--------|---------|---------|------|------|
| `am` | game.units.am | **UnitInstance** | 1878 | 单位实例 (HP/位置/状态) |
| `y` | game.units.y | **UnitType** | — | 单位类型基类 (抽象) |
| `av` | game.units.av | **WeaponTypeEnum** | 27 | 17种武器枚举 |
| `au` | game.units.au | **WeaponAction** | — | 武器动作实例 (14字段) |
| `ao` | game.units.ao | **MovementTypeEnum** | — | 8种移动枚举 |
| `ar` | game.units.ar | **UnitRegistry** | — | 内置单位注册表 |
| `as` | game.units.as | **UnitTypeHandle** | — | 单位类型句柄/引用 |
| `h` | game.units.h | **Factory** | 930 | 工厂 (建造队列/进度/分配) |
| `j` | game.units.d.j | **BuilderUnit** | — | 建造者单位 |

### 2.7 单位子包 (game/units/*/)

| 混淆名 | 完整路径 | 实际含义 | 说明 |
|--------|---------|---------|------|
| `d.e` | game.units.d.e | **CommandCenter** | cy()=18 |
| `d.b` | game.units.custom.d.b | **ResourceComponent** | 资源/经济组件 |
| `d.l` | game.units.d.l | **CarrierUnit** | 运输/装载单位 |
| `d.t` | game.units.d.t | **Structures** | 建筑基类 |
| `e.c` | game.units.e.c | **Building** | dd()=true (建筑) |
| `d.d` | game.units.d.d | **ExperimentalUnit** | bI()=true (实验单位) |
| `custom.j` | game.units.custom.j | **CustomUnitType** | 4699行, 自定义单位全属性 |

### 2.8 AI/任务系统 (gameFramework/n/)

| 混淆名 | 完整路径 | 实际含义 | 行数 | 说明 |
|--------|---------|---------|------|------|
| `d` | gameFramework.n.d | **MissionExecutor** | 222 | 任务执行/triggerLog |
| `f` | gameFramework.n.f | **AIWaveSystem** | 1066 | AI波次/难度/生成 |
| `a` | gameFramework.n.a | **AITask** | — | 单个AI任务 (29字段) |
| `b` | gameFramework.n.b | **AITaskQueue** | — | 任务队列 |
| `c` | gameFramework.n.c | **MissionParser** | — | 地图.ini→AITask 解析 |
| `e` | gameFramework.n.e | **MissionEvent** | — | 12种任务事件枚举 |
| `h` | gameFramework.n.h | **Difficulty** | — | AI难度 |
| `i` | gameFramework.n.i | **AISpawnList** | — | AI出兵列表 |
| `j` | gameFramework.n.j | **SpawnEntry** | — | 出兵条目 (单位类型+数量) |
| `k` | gameFramework.n.k | **SpawnWeight** | — | 加权出兵项 |
| `l` | gameFramework.n.l | **TargetFilter** | — | 7种目标筛选枚举 |
| `m` | gameFramework.n.m | **TaskStatus** | — | 任务状态 |

### 2.9 UI/入口

| 混淆名 | 完整路径 | 实际含义 | 说明 |
|--------|---------|---------|------|
| `Main` | com...java.Main | **GameLauncher** | 游戏启动入口 |
| `Root` | com...librocket.scripts.Root | **MainUIController** | UI主控制器 (164方法) |
| `ScriptEngine` | com...librocket.scripts.ScriptEngine | **UIScriptEngine** | UI脚本引擎 |

### 2.10 工具/数据结构

| 混淆名 | 完整路径 | 实际含义 | 说明 |
|--------|---------|---------|------|
| `utility.m` | gameFramework.utility.m | **CustomArrayList** | 7KB, 34方法 |
| `utility.u` | gameFramework.utility.u | **UnitRegistry** | 全局活跃单位注册表 |
| `utility.o` | gameFramework.utility.o | **DequeList** | 双端队列 |
| `utility.g` | gameFramework.utility.g | **RingBuffer** | 环形缓冲区 |
| `utility.y` | gameFramework.utility.y | **PathfindingUtils** | 12.7KB, 寻路工具 |
| `f` | gameFramework.f | **GameUtils** | 36KB, LCG随机/MD5/SHA/三角函数 |
| `g.b` | gameFramework.g.b | **DataField** | 统计分类数据字段 |
| `g.c` | gameFramework.g.c | **DataFieldInt** | 整型数据字段 |
| `g.d` | gameFramework.g.d | **DataFieldFloat** | 浮点数据字段 |
| `g.e` | gameFramework.g.e | **DataFieldLong** | 长整型数据字段 |
| `f.a` | game.units.f.a | **SpatialGridCell** | 空间网格单元格 |
| `f.c` | game.units.f.c | **SpatialGrid** | 空间哈希网格 (32x32) |
| `f.d` | game.units.f.d | **RectFilter** | 矩形查询过滤器 |
| `f.g` | game.units.f.g | **CircleFilter** | 索敌圆形查询 |
| `f.h` | game.units.f.h | **LineFilter** | 弹道线段查询 |
| `f.f` | game.units.f.f | **QueryResult** | 查询结果迭代器 |
| `custom.bb` | game.units.custom.bb | **LocalizedString** | 本地化文本 |
| `custom.g` | game.units.custom.g | **TeamTag** | 队伍标签 |
| `custom.h` | game.units.custom.h | **UnitConfig** | 单位配置数据 |
| `custom.l` | game.units.custom.l | **ModUnitRegistry** | Mod单位注册表 |

---

## 3. 全局引用链 (l.B() = GlobalState 单例)

```
l.al         → static l              全局单例
l.bs         → game.n                当前玩家
l.bL         → game.b.b              MapEngine (地图引擎)
l.bQ         → SettingsEngine        设置引擎
l.bS         → f.g                  UnitFactory (单位工厂)
l.bU         → k.l                  Recycler (单位回收队列)
l.bX         → j.ad                 NetEngine (网络引擎)
l.bY         → bg                    StatsManager (统计管理器)
l.bZ         → i.a                  ModDataHolder (Mod数据)
l.ca         → y                    GameSaver (存档管理)
l.cb         → ba                   ReplayEngine (回放引擎)
l.cc         → f.c                  空间网格 (SpatialGrid)
l.cd         → br                    ExtraManager
l.ce         → n.f                  MissionEngine (AI系统)
l.cf         → —                     指令数据包管理器
l.bx         → int                  游戏tick计数器
l.by         → int                  当前帧号
```

---

## 4. 关键字段映射 (按类)

### 4.1 PlayerState (game.n = gameFramework.n)

| 字段 | 类型 | 含义 | 默认值 |
|------|------|------|--------|
| `k` | int | 玩家槽位索引 | -1 |
| `r` | int | 队伍ID (0=A,1=B,...,9=J,-3=Spec) | — |
| `v` | String | 玩家名称 | — |
| `o` | double | 当前资金 | 4000.0 |
| `p` | double | 反延迟资金缓冲 | — |
| `O` | String | 连接hex ID | — |
| `A` | int | 起始单位选择 | — |
| `C` | int | 玩家颜色 | — |
| `G` | boolean | 已投降 | — |
| `F` | boolean | 已断开连接 | — |
| `E` | boolean | 已战败 | — |
| `x` | int | AI难度(后备) | — |
| `z` | Integer | AI难度(主要) | null |
| `T` | s | TeamUnitTracker | — |
| `w` | boolean | AI启用标志 | — |
| `c` | static int | 最大玩家数 | 10 |

### 4.2 TeamUnitTracker (game.s)

| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | int | 最大单位数限制 |
| `b` | int | 非建筑单位数 |
| `c` | int | 已完成单位数 |
| `d` | int | 累计建造总数 |
| `e` | int | 排队中单位数 |
| `f` | int | 建造中单位数 |
| `g` | int | ★ 收入率 = Σ cy() |
| `h` | f | 自定义收入率修正 |
| `i` | f | 资源类型2 |
| `j` | f | 资源类型3 |
| `k` | f | 正向资源流修正 |
| `l` | f | 负向资源流修正 |
| `m` | boolean | 有非固定单位 |
| `n` | int | 建筑容量占用 |
| `o` | int | 单位容量占用 |
| `p` | t | 建造队列1 |
| `q` | t | 建造队列2 |

### 4.3 UnitInstance (game.units.am)

| 字段 | 类型 | 含义 |
|------|------|------|
| `cu` | float | ★ 当前HP |
| `cv` | float | ★ 最大HP |
| `cx` | float | ★ 当前护盾值 |
| `cz` | float | ★ 护盾再生计时器 |
| `cm` | float | ★ 建造进度 (≥1.0=完成) |
| `cj` | float | ★ 碰撞半径 |
| `eo` | float | X坐标 |
| `ep` | float | Y坐标 |
| `bV` | boolean | 已死亡标志 |
| `bW` | float | 死亡时间戳 |
| `bX` | n | ★ 所属玩家引用 |
| `bO` | boolean | 出生点A标志 |
| `bP` | boolean | 出生点B标志 |
| `bY` | boolean | 已注册到队伍追踪器 |
| `bL` | boolean | 在地图上 (非运输中) |
| `bs` | int | 上次受伤时间 |
| `bt` | am | 上次攻击者引用 |
| `cC` | float | HP变化动画累积 |
| `cD` | float | HP动画时间系数 |
| `cw` | float | 维修进度 |
| `cL` | am[] | 炮塔目标数组 |

### 4.4 WeaponAction (game.units.au)

| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | av | 武器类型枚举 (17种) |
| `b` | as | 目标单位类型 |
| `c` | a.c | 动作类型 |
| `d` | int | 弹药计数 |
| `e` | float | 伤害值 |
| `f` | float | 射程 |
| `g` | long | 冷却计时器 |
| `h` | am | 当前目标单位实例 |
| `i` | fw.ab | 弹丸管理器引用 |
| `j` | boolean | 是否激活 |
| `k` | float | 当前伤害 |
| `l` | float | 当前射程 |
| `m` | boolean | 是否就绪 |
| `n` | boolean | 是否已发射 |

### 4.5 Command (gameFramework.e)

| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | boolean | 标志A |
| `b` | String | 消息/名称 |
| `c` | int | tick |
| `d` | int | ID |
| `e` | boolean | 标志E |
| `f` | boolean | 标志F |
| `g` | boolean | ★ stopOrUndo (取消) |
| `h` | boolean | 标志H |
| `i` | n | ★ 玩家对象 |
| `j` | au | ★ 路径点/动作 |
| `k` | units.a.c | ★ SpecialAction |
| `n` | a | SetAttackMode |
| `o` | boolean | 标志O |
| `p` | n | 执行前玩家状态 (调试) |
| `q` | short | ★ player_index (2B) |
| `r` | boolean | ★ systemAction |
| `s` | float | ★ changeStepRate (速度变更) |
| `t` | float | 参数T |
| `u` | int | ★ systemAction值 (100=投降) |
| `x` | boolean | 标志X |

### 4.6 StatsManager / StatsRecord / StatsHistory (gameFramework)

**bg = StatsManager (l.bY)**:
| 字段 | 类型 | 含义 |
|------|------|------|
| `b` | bo | 默认/回退 StatsRecord |
| `c` | bo[] | ★ 每玩家一个 StatsRecord |
| `d` | int | 采样计数 |
| `e` | boolean | 活跃标志 |
| `f` | bl | 周期采样定时器 |

**bo = StatsRecord**:
| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | int | 分类1 |
| `b` | int | 分类2 |
| `c` | int | ★ unitsKilled |
| `d` | int | ★ experimentalsKilled (bI()=true → bg.java:125) |
| `e` | int | ★ buildingsKilled (dd()=true → bg.java:128) |
| `f` | int | ★ unitsLost |
| `g` | int | ★ experimentalsLost (bI()=true → bg.java:126) |
| `h` | int | ★ buildingsLost (dd()=true → bg.java:129) |
| `i` | int | 其他分类 |
| `j` | int | 其他分类 |
| `k` | long | 时间戳 |
| `l` | bn | ★ StatsHistory |

**bn = StatsHistory**:
| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | int | 玩家ID |
| `b` | bi[] | ★ 4条时间线 (bj.a/b/c/d 各一条) |

**bh = StatsSample**: `a`=tick(int), `b`=value(int)
**bi = ArrayList\<bh\>** (单条时间线)

### 4.7 NetEngine (gameFramework.j.ad)

| 字段 | 类型 | 含义 |
|------|------|------|
| `X` | int | 网络tick计数器 |
| `C` | boolean | 游戏已开始 |
| `m` | int | 服务器端口 |
| `y` | String | 主机名 |
| `s` | boolean | 永续模式 |
| `o` | boolean | Mod启用 |
| `z` | n | 隐藏队伍 (同步占位) |
| `ax` | int | 最大单位数 |
| `aw` | int | 最大单位数(备用) |
| `az` | String | 地图路径 |
| `aM` | Collection | 连接列表 |
| `ay` | — | 游戏设置子对象 |

### 4.8 游戏设置 (ay 子对象)

| 字段 | 类型 | 含义 |
|------|------|------|
| `ay.a` | ai enum | 地图/AI类型 |
| `ay.b` | String | 地图文件名 |
| `ay.c` | int | 起始资金 |
| `ay.d` | int | 迷雾 (0=关, 1=基础, 2=全) |
| `ay.f` | int | AI难度 |
| `ay.g` | int | 起始单位数 |
| `ay.h` | float | ★ 收入倍率 |
| `ay.i` | boolean | 核弹启用 |
| `ay.l` | boolean | 共享控制 |

### 4.9 AIWaveSystem (gameFramework.n.f)

| 字段 | 类型 | 含义 |
|------|------|------|
| `b/c` | int | 波次计数器 (当前/总数) |
| `r` | int | 当前波次号 (0-based) |
| `L` | int | 任务计数 |
| `M` | float | 难度倍率 (默认3000.0) |
| `u` | int | 波次间隔 (ticks) |
| `v` | int | 单位数量基数 |
| `w` | int | 单位类型多样性 |
| `x` | int | 最大同时波次数 |
| `z` | float | 波次计时器 |
| `A` | float | 波次倍率 (递增) |
| `B` | float | 紧急倍率 |
| `k` | boolean | 全局AI启用 |
| `q` | boolean | 无限波次模式 |
| `g` | ArrayList | 活跃AI任务列表 |
| `J` | ArrayList | 待生成单位队列 |
| `O` | ArrayList | 攻击目标列表 |

---

## 5. 枚举值映射

### 5.1 WeaponType — av (17值)

| 枚举值 | 含义 |
|--------|------|
| `a` | directFire (直射) |
| `b` | indirectFire (曲射/火炮) |
| `c` | melee (近战) |
| `d` | laser (激光) |
| `e` | missile (导弹) |
| `f` | torpedo (鱼雷) |
| `g` | flamethrower (火焰喷射) |
| `h` | electricBolt (电击) |
| `i` | artillery (炮击) |
| `j` | bomb (炸弹) |
| `k` | nuke (核弹) |
| `l` | shield (护盾) |
| `m` | repair (维修) |
| `n` | build (建造) |
| `o` | reclaim (回收) |
| `p` | capture (占领) |
| `q` | customWeapon (自定义武器) |

### 5.2 MovementType — ao (8值)

| 枚举值 | 含义 |
|--------|------|
| `a` | ground (地面) |
| `b` | water (水面) |
| `c` | air (空中) |
| `d` | hover (悬浮) |
| `e` | amphibious (两栖) |
| `f` | submarine (潜艇) |
| `g` | building (建筑/固定) |
| `h` | custom (自定义) |

### 5.3 UnitBehavior — units.a.c (动作类型, 7+10=17值)

**基础7行为**:
| 枚举值 | 含义 |
|--------|------|
| `a` | idle (空闲) |
| `b` | moving (移动中) |
| `c` | attacking (攻击中) |
| `d` | building (建造中) |
| `e` | repairing (维修中) |
| `f` | reclaiming (回收中) |
| `g` | loading (装载中) |

**扩展17指令类型**:
| # | 含义 |
|---|------|
| 0 | move (移动) |
| 1 | attack (攻击) |
| 2 | build (建造) |
| 3 | repair (维修) |
| 4 | loadInto (装载进入) |
| 5 | unloadAt (卸载到位置) |
| 6 | reclaim (回收) |
| 7 | attackMove (攻击移动) |
| 8 | loadUp (装载) |
| 9 | patrol (巡逻) |
| 10 | guard (守卫) |
| 11 | guardAt (守卫位置) |
| 12 | touchTarget (接触目标) |
| 13 | follow (跟随) |
| 14 | triggerAction (触发动作) |
| 15 | triggerWhenInRange (范围内触发) |
| 16 | setPassiveTarget (设置被动目标) |

### 5.4 MissionEvent — n.e (12值)

| 枚举值 | 含义 |
|--------|------|
| `a` | event_missionStart |
| `b` | event_waveStart |
| `c` | event_waveEnd |
| `d` | event_unitDeath |
| `e` | event_unitBuilt |
| `f` | event_creditsChange |
| `g` | event_timerExpire |
| `h` | event_playerDefeated |
| `i` | event_allUnitsDead |
| `j` | event_customCondition |
| `k` | event_missionComplete |
| `l` | (reserved) |

### 5.5 StatsCategory — bj (4值)

| 枚举值 | 关联数据字段 | 含义 |
|--------|------------|------|
| `a` | g.f.b | 分类A (资金历史?) |
| `b` | g.f.c | 分类B (单位计数?) |
| `c` | g.f.d | 分类C (收入率?) |
| `d` | g.f.e | 分类D (战力?) |

### 5.6 TargetFilter — n.l (7值)

| 值 | 含义 |
|----|------|
| none | 无筛选 |
| allUnitsAndBuildings | 所有单位和建筑 |
| allBuildings | 所有建筑 |
| mainBuildings | 主建筑 |
| commandCenter | 指挥中心 |
| noConstructionOrTech | 无建造/科技 |
| requiredObjectives | 必需目标 |

### 5.7 Fog — ay.d (3值)

| 值 | 含义 |
|----|------|
| 0 | off (关) |
| 1 | basic (基础) |
| 2 | full (全迷雾) |

---

## 6. 关键方法映射

### 6.1 全局访问

| 方法 | 含义 | 返回类型 |
|------|------|---------|
| `l.B()` | 获取全局单例 | static l |
| `n.k(int)` | 按槽位获取玩家 | static n |
| `n.b(int, bool)` | 扩展玩家数组 | static void |
| `n.F()` | 初始化玩家数组 | static void |

### 6.2 经济/收入

| 方法 | 含义 |
|------|------|
| `d.e.cy()` | 获取收入贡献值 (返回18.0f) |
| `y.a(float, au, ad)` | 每帧累积收入 |
| `s.a(am)` | 注册单位: g+=cy(), d++ |
| `s.b(am)` | 注销单位: g-=cy(), d-- |

### 6.3 HP/战斗

| 方法 | 含义 |
|------|------|
| `am.x()` | HP比率: cu<cv→cu/cv, 否则-1.0f |
| `am.ch()` | 检查死亡 (HP≤0→bv) |
| `am.bv()` | 死亡序列入口 |
| `am.bu()` | 执行死亡清理 (8步) |
| `am.bI()` | 是否为实验单位 (d/d.java:true) |
| `am.dd()` | 是否为建筑 (e/c.java:true) |
| `am.u()` | 是否为运输单位 |

### 6.4 回放

| 方法 | 含义 |
|------|------|
| `ba.h()` | ★ 读取下一个 j.as 块 (9种类型) |
| `ba.a(float)` | 帧更新 (执行指令) |
| `ba.f()` | 获取当前帧号 |
| `e.a(k)` | 从 BinaryReader 反序列化 Command (18字段) |

### 6.5 二进制IO

| 方法 | 含义 |
|------|------|
| `k.a(String, bool, bool)` | startBlock (最大深度11) |
| `k.d(String)` | endBlock |
| `k.c(String)` | getBlockRaw |
| `k.a(String)` | readMark (值=12345) |
| `k.f()` | readInt |
| `k.g()` | readFloat |
| `k.e()` | readBoolean |

### 6.6 统计

| 方法 | 含义 |
|------|------|
| `bg.a(am)` / `bg.a(n)` | 按玩家获取 StatsRecord |
| `bg.a(am, am, float)` | ★ 击杀记录 (按 unit type 分类) |
| `bg.b()` | 周期采样 |
| `bg.c()` | 游戏结束采样 |
| `bn.a(n, int, bool)` | 对某玩家采样 |
| `bn.a(bj, int)` | 查询统计分类值 |

### 6.7 AI

| 方法 | 含义 |
|------|------|
| `n.c.a(n.f, b.a)` | 从地图.ini 解析 AITask |
| `n.d.a(n.f, n.a)` | 执行 AITask (出兵/消息/计时) |
| `n.i.a(float, float)` | 实际生成AI单位到地图 |
| `n/a/c.e(n.a)` | 单位数量条件检测 |

---

## 7. 回放二进制格式 (ba.java)

### 7.1 文件头

| 偏移 | 大小 | 字段 | 值 |
|------|------|------|----|
| 0x0000 | 2B | magic_len (BE uint16) | 19 |
| 0x0002 | 19B | magic | "rustedWarfareReplay" |
| 0x0015 | 4B | header_int1 (BE int32) | 176 (SettingsEngine.settingsGameVersion) |
| 0x0019 | 4B | header_int2 (BE int32) | 96 (hardcoded) |
| 0x001D | 2B | version_len | 4 |
| 0x001F | 4B | version | "1.15" |

### 7.2 rc 块 (建造指令, hasAU=0)

| 偏移 | 大小 | 字段 |
|------|------|------|
| 0 | 4B | tick (BE int) |
| 4 | 3B | writeUTF 'c' (00 01 63) |
| 7 | 1B | team_ref (回放中=0) |
| 8 | 1B | hasAU (0=build) |
| 9 | 1B | flags/ee |
| 10 | 1B | remaining_len (= data_len-11) |
| 11 | 1B | ★ player_index (0-7) |
| 12+ | var | unit_name writeUTF + payload |

### 7.3 j.as 块格式

```
[2B: UTF_len (BE)] [name_bytes] [4B: data_size (BE)] [data_bytes]
最大嵌套深度: 11
同步标记值: 12345
最大ID值: 999999
```

---

## 8. 网络包类型

| ID | 类型 | 路由 |
|----|------|------|
| 10 | TICK | 队列 (≤100) |
| 20 | GAMECOMMAND | 队列 |
| 35 | SYNC | 队列 |
| 110 | REGISTER_PLAYER | 即时 (>100) |
| 120 | START_GAME | 即时 |
| 140/141 | CHAT | 即时 |
| 105/108/111/160 | 系统消息 | 即时 |

---

## 9. 关键常量

| 常量 | 值 | 来源 |
|------|-----|------|
| CC收入 (cy) | 18.0f | d/e.java:175 |
| 默认起始资金 | 4000.0 | n.java:57 |
| 建造伤害惩罚 | 1.75x | am.java:1224 |
| 低HP阈值 | 33% | am.java:1129 |
| 取消退款率 | 100% | y.java (flag) |
| 回收退款率 | 80% | y.java (i():0.8f) |
| 经济帧率 | 60fps | GameHessData |
| 回放帧率 | 30fps | — |
| 空间网格 | 32×32, cell=50px | f/c.java |
| header_int1 | 176 | SettingsEngine.java:521 |
| header_int2 | 96 | ba.java:365 |
| 块协议深度 | 11 | j/k.java |
| 同步标记 | 12345 | j/as.java |
| 默认收入倍率 | 2.5 | ay.h |

---

## 10. 跨版本映射 (rwTool ↔ v1.15)

| rwTool 类 | v1.15 类 | 含义 |
|-----------|---------|------|
| `ce` | `am` | UnitInstance |
| `p` | `n` | PlayerState |
| `cj` | `as` | UnitTypeHandle |
| `bp` | — | Attachment |
| `aj` | `l` | GlobalState |
| `ah` | `fw.w` | GameObject |

| rwTool 字段 | v1.15 字段 | 含义 |
|------------|-----------|------|
| `cw` | `cu` | 当前HP |
| `cx` | `cv` | 最大HP |
| `cz` | `cx` | 当前护盾 |
| `cC` | — | 最大护盾 |
| `cD` | `cB` | 能量值 |
| `cG` | `cE` | 弹药计数 |
| `ej` | — | 单位ID |
| `eq` | `eo` | X坐标 |
| `er` | `ep` | Y坐标 |
| `es` | `eh` | 高度层级 |
| `co` | `cm` | 建造进度 |
| `ci` | `cg` | 朝向角度 |
| `ce/cf` | — | 速度分量 |
| `cQ` | `cN` | 父单位引用 |
| `bZ` | `bX` | 所属玩家 |
| `l` | `r` | 队伍ID |
| `w` | `v` | 玩家名称 |
| `x` | `w` | AI标志 |

> **注意**: 字段名差异表明 rwTool 的游戏版本与 v1.15 混淆名不同，但核心逻辑不变。

---

## 11. 数据流速查

### 收入流
```
d.e.cy()=18.0 → s.h/i/j/k/l (5资源组件) → s.g = Σ cy() → am.cm≥1.0 时触发
每40帧 (经济帧): n.o += s.g × 收入倍率
```

### 伤害流
```
武器发射 → a.i (弹丸物理) → 碰撞检测 → am.a(attacker, rawDamage, modifiers)
  1. cm<1.0 → ×1.75
  2. 护盾吸收 (cz==0 && cx>0)
  3. HP扣除 (cu -= damage)
  4. cu≤0 → bV=true → 8步死亡链
```

### 建造流
```
Command(rc/build) → Factory.h 队列 → d.j.n += buildSpeed×delta → ≥1.0 → 完成
→ s.a(am): g+=cy(), d++
```

### 统计流
```
击杀: bg.a(killer, killed, dmg)
  bI()(实验) → bo.d++ / bo.g++
  dd()(建筑)  → bo.e++ / bo.h++
  else        → bo.c++ / bo.f++
采样: bg.b() → bn.a(player, tick) → bh{tick, value}
```

---

*57 核心类 | 280+ 字段映射 | 17 枚举 | 12 关键公式 | CFR 0.152 × game-lib.jar × RW-HPS × 30回放*
