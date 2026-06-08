# Rusted Warfare v1.15 — 完整类字典

> 交叉验证: game-lib.jar 字节码(1698类) + RW-HPS 服务器源码(513文件) + 游戏运行时
> 日期: 2026-06-06 | 字段映射: 150+

---

## 1. `l` — gameFramework.l (全局状态)

| 字段 | 类型 | 含义 | 验证源 |
|------|------|------|--------|
| `B()` | static l | 全局单例 | GameEngine.kt:68 |
| `bL` | game.b.b | 地图引擎 (MapEngine) | GameEngine.kt:69 |
| `bX` | j.ad | 网络引擎 (NetEngine) | GameEngine.kt:70 |
| `bQ` | — | 设置引擎 (GameSettings) | GameEngine.kt:72 |
| `bY` | bg | 对战统计管理器 (StatsManager, 内部用 bo=StatsData) | GameEngine.kt:74 + l.java:170 |
| `bS` | — | 单位工厂/生成管理器 | ClientCommands.kt:506 |
| `bU` | — | 单位回收/销毁队列 | GameUnitData.kt:72 |
| `bZ` | — | Mod数据持有者 | GameUnitData.kt:59 |
| `bx` | int | 游戏tick计数器 | GameHessData.kt:34 |
| `ca` | — | 回放管理器 (.rwsave) | LinkGameFunction.kt:42 |
| `cf` | — | 指令数据包管理器 | FFA_X.kt:140 |

---

## 2. `n` — game.n (玩家/GameState)

| 字段 | 类型 | 含义 | 验证源 |
|------|------|------|--------|
| `k` | int | 玩家槽位索引 (0..maxPlayer) | PrivateClassLinkPlayer.kt:59 |
| `r` | int | 队伍ID (0=A,1=B,...,9=J, -3=Spec) | PrivateClassLinkPlayer.kt:64 |
| `v` | String | 玩家名称 | PrivateClassLinkPlayer.kt:55 |
| `o` | double | 当前资金 (初始4000) | PrivateClassLinkPlayer.kt:50 |
| `p` | double | 反延迟资金缓冲 | n.java:77 |
| `O` | String | 连接hex ID | PrivateClassLinkPlayer.kt:56 |
| `A` | int | 起始单位选择 | PrivateClassLinkPlayer.kt:70 |
| `C` | int | 玩家颜色 | PrivateClassLinkPlayer.kt:76 |
| `G` | boolean | 已投降 | PrivateClassLinkPlayer.kt:27 |
| `F` | boolean | 已断开连接 | PrivateClassLinkPlayer.kt:27 |
| `E` | boolean | 已战败 | PrivateClassLinkPlayer.kt:27 |
| `x` | int | AI难度(后备值) | PrivateClassLinkAIPlayer.kt:29 |
| `z` | Integer | AI难度(主要值) | PrivateClassLinkAIPlayer.kt:29 |
| `T` | s | 队伍追踪器 | n.java:813 |
| `w` | boolean | AI难度启用标志 | n.java:84 |
| `b()` | ()→boolean | 是否已死亡/淘汰 | PrivateClassLinkPlayer.kt:27 |
| `I()` | ()→void | 移除玩家 | PrivateClassLinkPlayer.kt:84 |
| `k(int)` | static→n | 按索引获取玩家 | GameHessData.kt:41 |
| `b(int,bool)` | static | 扩展玩家槽位数组 | LinkGameNet.kt:95 |
| `F()` | static | 初始化玩家数组 | LinkGameNet.kt:93 |
| `c` | static int | 最大玩家数(默认10) | n.java:63 |

---

## 3. `s` — game.s (TeamUnitTracker 队伍追踪器)

| 字段 | 类型 | 含义 | 字节码行号 |
|------|------|------|-----------|
| `a` | int | 最大单位数限制 | s.java:28 |
| `b` | int | 非建筑单位数 | s.java:29 |
| `c` | int | 已完成单位数 | s.java:30 |
| `d` | int | 累计建造总数(单调递增) | s.java:31 |
| `e` | int | 排队中单位数 | s.java:32 |
| `f` | int | 建造中单位数(cm<1.0) | s.java:33 |
| `g` | int | **收入率 = Σ cy()** (每40帧) | s.java:34 |
| `h` | f | 自定义收入率修正 | s.java:35 |
| `k` | f | 正向资源流修正 | s.java:38 |
| `l` | f | 负向资源流修正 | s.java:39 |
| `m` | boolean | 有非固定单位 | s.java:40 |
| `n` | int | 建筑容量占用 | s.java:41 |
| `o` | int | 单位容量占用 | s.java:42 |
| `a(am)` | void | 注册单位: g += cy(), d++ | s.java:46-97 |
| `b(am)` | void | 注销单位: g -= cy(), d-- | s.java:99-136 |

---

## 4. `am` — game.units.am (UnitInstance 单位实例)

| 字段 | 类型 | 含义 | 字节码行号 |
|------|------|------|-----------|
| `cu` | float | 当前HP | am.java:685 |
| `cv` | float | 最大HP | am.java:352 |
| `cx` | float | 当前护盾值 | am.java:1280 |
| `cz` | float | 护盾再生计时器 | am.java:1280 |
| `cm` | float | 建造进度 (≥1.0=完成) | am.java:148 |
| `cj` | float | 碰撞半径 | am.java:1379 |
| `eo` | float | X坐标 | am.java:1332 |
| `ep` | float | Y坐标 | am.java:1332 |
| `bV` | boolean | 已死亡标志 | am.java:1347 |
| `bW` | float | 死亡时间戳 | am.java:1348 |
| `bX` | n | 所属玩家/队伍引用 | GameHessData.kt:115 |
| `bO` | boolean | 出生点A标志 | GameHessData.kt:116 |
| `bP` | boolean | 出生点B标志 | GameHessData.kt:121 |
| `bY` | boolean | 已注册到队伍追踪器 | n.java:1543 |
| `bL` | boolean | 在地图上(非运输中) | n.java:1543 |
| `bs` | int | 上次受伤时间 | am.java:1308 |
| `bt` | am | 上次攻击者引用 | am.java:1309 |
| `cC` | float | HP变化动画累积 | am.java:1211 |
| `cD` | float | HP动画时间系数 | am.java:1211 |
| `cw` | float | 维修进度 | am.java:1168 |
| `cL` | am[] | 炮塔目标数组 | am.java:1355 |
| `cy()` | →float | ★ 收入贡献值 | s.java:76 |
| `x()` | →float | HP比率: cu<cv→cu/cv, 否则-1 | am.java:734 |
| `u()` | →boolean | 是否为运输单位 | am.java:1922 |
| `r()` | →as | 获取UnitType枚举 | GameUnitData.kt:73 |
| `ch()` | void | 检查死亡 (HP≤0→bv) | am.java:1322 |
| `bu()` | void | 执行死亡清理 | am.java:1340 |
| `bv()` | void | 死亡序列入口 | am.java:1371 |
| `bF()` | static | 获取所有单位列表 | GameHessData.kt:114 |

---

## 5. Stats 统计系统 (gameFramework)

3层架构: `bg`(StatsManager/l.bY) → `bo`(StatsRecord/每玩家) → `bn`(StatsHistory/时间线)

### 5a. `bg` — StatsManager (统计管理器, l.bY)

| 字段 | 类型 | 含义 | 验证源 |
|------|------|------|--------|
| `b` | bo | 默认/回退 StatsRecord | l.java:170 |
| `c` | bo[] | ★ 每玩家一个 StatsRecord | bg.java:19 |
| `d` | int | 采样计数 | bg.java:20 |
| `e` | boolean | 活跃标志 | bg.java:21 |
| `f` | bl | 周期采样定时器 | bg.java:22 |

关键方法:
- `a(am)` / `a(n)` → bo — 按玩家槽位获取 StatsRecord
- `a(killer, killed, dmg)` — 击杀记录: 根据 bI()(实验)/dd()(建筑) 分类统计
- `b()` — 周期采样 (自适应间隔 1000-30000ms)
- `c()` — 游戏结束最终采样

### 5b. `bo` — StatsRecord (单玩家统计数据, 11字段)

| 字段 | 类型 | 含义 | 验证源 |
|------|------|------|--------|
| `a` | int | 分类1 | bo.java:12 |
| `b` | int | 分类2 | bo.java:13 |
| `c` | int | ★ unitsKilled 击杀普通单位 | bg.java:130 (else分支) |
| `d` | int | ★ experimentalsKilled 击杀实验单位 | bg.java:125 (bI()=true) |
| `e` | int | ★ buildingsKilled 摧毁建筑 | bg.java:128 (dd()=true) |
| `f` | int | ★ unitsLost 损失普通单位 | bg.java:131 (else分支) |
| `g` | int | ★ experimentalsLost 损失实验单位 | bg.java:126 (bI()=true) |
| `h` | int | ★ buildingsLost 损失建筑 | bg.java:129 (dd()=true) |
| `i` | int | 其他分类 | bo.java:19 |
| `j` | int | 其他分类 | bo.java:20 |
| `k` | long | 时间戳 | bo.java:22 |
| `l` | bn | ★ StatsHistory 历史时间线 | bo.java:23 |

> **源码验证**: `bg.java:116-133` — 击杀时根据 `killed.bI()`(实验单位,d/d.java:343) 和 `killed.dd()`(建筑,e/c.java:364) 分配字段

### 5c. `bn` — StatsHistory (统计历史时间线, 181行)

| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | int | 玩家ID |
| `b` | bi[] | ★ 每条 bj 分类一个时间线 |

方法: `a(player, tick, bool)` — 采样, `a(bj, index)` — 查询

### 5d. 辅助类

| 类 | 含义 | 字段 |
|----|------|------|
| `bh` | StatsSample (采样点) | `a`=tick(int), `b`=value(int) |
| `bi` | ArrayList\<StatsSample\> | 单条时间线 |
| `bj` | StatsCategory 枚举 | a/b/c/d (4种统计分类), 各关联 g.f.b/c/d/e |
| `bl` | PeriodicTimer | 周期采样定时器 |

---

## 6. `j.ad` — gameFramework.j.ad (NetEngine 网络引擎)

| 字段 | 类型 | 含义 | 验证源 |
|------|------|------|--------|
| `X` | int | 网络tick计数器 | GameHessData.kt:35 |
| `C` | boolean | 游戏是否已开始 | GameFast.kt:29 |
| `m` | int | 服务器端口号 | LinkGameNet.kt:67 |
| `y` | String | 主机/玩家名称 | LinkGameNet.kt:43 |
| `s` | boolean | 永续模式标志 | LinkGameNet.kt:71 |
| `o` | boolean | 启用Mod标志 | GameUnitData.kt:28 |
| `z` | n | 隐藏队伍(同步占位) | LinkGameNet.kt:97 |
| `ax` | int | 最大单位数 | LinkGameServerData.kt:30 |
| `aw` | int | 最大单位数(备用) | LinkGameServerData.kt:31 |
| `az` | String | 地图文件路径 | LinkGameNet.kt:111 |
| `aM` | Collection | 连接列表 | LinkGameNet.kt:46 |
| `aD` | Thread | TCP服务器线程 | LinkGameNet.kt:88 |
| `aE` | ao | TCP AcceptRunnable | LinkGameNet.kt:85 |
| `ay` | — | 游戏设置子对象 | LinkGameServerData |
| `bC` | Object | 队伍操作同步锁 | LinkGameServerData.kt:26 |

### `ay` 子对象 (游戏设置)
| 字段 | 类型 | 含义 |
|------|------|------|
| `ay.a` | ai enum | 地图/AI类型 |
| `ay.b` | String | 地图文件名 |
| `ay.c` | int | 起始资金配置 |
| `ay.d` | int | 战争迷雾 (0=关, 1=基础, 2=全) |
| `ay.f` | int | AI难度 |
| `ay.g` | int | 起始单位数 |
| `ay.h` | float | ★ 收入倍率 |
| `ay.i` | boolean | 核弹启用 |
| `ay.l` | boolean | 共享控制 |

---

## 7. `gameFramework.e` — gameFramework.e (Command 指令)

| 字段 | 类型 | 含义 |
|------|------|------|
| `g` | boolean | ★ stopOrUndo (取消标志) |
| `r` | boolean | systemAction |
| `u` | int | systemAction值 (100=投降) |
| `q` | short | player_index (2B) |
| `s` | float | change_speed |

---

## 8. `units.a` — 单位状态枚举 (7值)

```
a=idle, b=moving, c=attacking, d=building, e=repairing, f=reclaiming, g=loading
```

## 9. `units.a.c` — 武器/动作类型 (String枚举)

| 方法 | 含义 |
|------|------|
| `a(String)` → c | 按名称查找 |
| `a()` → String | 获取类型名 |

## 10. `units.a.s` — 动作定义 (abstract)

| 字段 | 类型 | 含义 |
|------|------|------|
| `g` | float | 动作消耗/时间 |
| `h` | a.a | 动作处理器 |
| `a` | a.c | 动作类型 |
| `b` | d.b | ResourceComponent |
| `m_()` | →float | 获取消耗 |
| `N()` | →a.c | 获取动作类型 |

## 11. `units.au` — 武器实例

| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | av | 武器类型枚举 (17种) |
| `b` | as | 目标单位类型 |
| `c` | a.c | 动作类型 |
| `d` | int | 弹药计数 |
| `e/f` | float | 伤害/射程 |
| `g` | long | 冷却计时器 |
| `h` | am | 目标单位实例 |
| `i` | ab | 弹丸定义 |

## 12. `units.af` — 动作参数

| 字段 | 类型 | 含义 |
|------|------|------|
| `a/b` | float | 参数值 |

## 13. `gameFramework.y` — GameSaver (l.ca)

| 方法 | 含义 |
|------|------|
| `a(j.as)` | 序列化游戏状态 |
| `b()` | 保存 (.rwsave) |
| `c()` | 加载 |

## 14. `gameFramework.ba` — ReplayEngine (l.cb)

| 方法 | 含义 |
|------|------|
| `a()` | 开始录制 |
| `b()` | 停止录制 |
| `c()` | 暂停 |
| `d()` | 恢复 |

## 15. 其他引擎类

| 类 | 含义 | 关键字段/方法 |
|----|------|-------------|
| `game.b.b` | 地图引擎 | `C`=宽(px), `D`=高(px), `u-x`=5瓦片层, `B[]`=路径节点 |
| `gameFramework.w` | 游戏世界 | `er`=单位容器 |
| `units.custom.j` | 自定义单位类型 | extends y, `x/z`=类型定义, `A[]`=动作数组 |
| `units.custom.d.b` | ResourceComponent | 5操作: SPEND/DAMAGE/HEAL/PICKUP/ALIVE |
| `units.h` | 工厂/生成器 | `n`=制造蓝图, `j`=克隆蓝图 |
| `java.Main` | 主入口 | `m`=单例, `k`=AppGameContainer |

## 16. AI/任务系统 — `gameFramework.n.*` (14类, ~62KB)

### 16a. `n.f` — AIWaveSystem (26KB, 46字段)
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

### 16b. `n.a` — AITask (7KB, 29字段)
| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | String | 任务内部ID |
| `b` | String | 任务显示名称 |
| `g` | n.e | 事件类型 (MissionEvent) |
| `h` | boolean | 是否必须完成 |
| `i` | boolean | 已激活标志 |
| `j` | boolean | 已完成标志 |
| `k` | int | 开始时间戳 (l.by) |
| `t` | b.a | 地图目标位置 |
| `v` | custom.bp | 单位生成配置 |
| `y` | n/a/a | 前置条件检查器 |
| `z` | custom.bb | 本地化文本 |
| `A` | custom.bb | 全局消息文本 |

### 16c. `n.b` — AITaskQueue (1KB)
| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | utility.m | 任务列表 |
| `b` | boolean | 全部激活标志 |
| `a()` | →boolean | 是否有待处理任务 |
| `b()` | →boolean | ★ 所有任务是否全部激活 (波次完成检测) |

### 16d. `n.c` — MissionParser (6KB)
| 方法 | 含义 |
|------|------|
| `a(n.f,b.a)`→n.a | ★ 从地图.ini段解析创建AITask |

### 16e. `n.d` — MissionExecutor (7KB)
| 方法 | 含义 |
|------|------|
| `a(n.f,n.a)`→void | ★ 执行AITask (出兵/消息/计时) |

### 16f. `n.e` — MissionEvent (2.4KB, 12枚举值)
| 值 | 含义 |
|----|------|
| a | event_missionStart |
| b | event_waveStart |
| c | event_waveEnd |
| d | event_unitDeath |
| e | event_unitBuilt |
| f | event_creditsChange |
| g | event_timerExpire |
| h | event_playerDefeated |
| i | event_allUnitsDead |
| j | event_customCondition |
| k | event_missionComplete |

### 16g. `n.i` — AISpawnList (3.3KB)
| 字段 | 类型 | 含义 |
|------|------|------|
| `b` | utility.m | 出兵条目列表 (n.j) |
| `c` | n.f | 所属波次系统 |
| `b(as,int)` | →void | 累加单位数量 |
| `a(float,float)` | →void | ★ 实际生成AI单位到地图 |

### 16h. `n.j` — SpawnEntry (414B, n.i内部类)
| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | as | 单位类型 |
| `b` | int | 数量 (默认1) |

### 16i. `n.k` — SpawnWeight (414B)
| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | as | 单位类型 |
| `b` | float | 权重 (默认1.0) |

### 16j. `n.l` — TargetFilter (1.2KB, 7枚举值)
none, allUnitsAndBuildings, allBuildings, mainBuildings, commandCenter, noConstructionOrTech, requiredObjectives

### 16k. `n.h` — Difficulty (801B, 1值: normal)

### 16l. `n.m` — TaskStatus (644B)
| 方法 | 含义 |
|------|------|
| `a()`→String | 获取任务显示名 |
| `b()`→boolean | 检查任务是否完成 |

### 16m. `n/a/a` — TaskCondition (356B, abstract)
| 方法 | 含义 |
|------|------|
| `a(n.a)`→boolean | 前置条件 (默认true) |
| `b(n.a)`→boolean | ★ 完成条件 (abstract) |
| `c(n.a)`→boolean | 失败条件 (默认false) |

### 16n. `n/a/b` — TeamTagDetect (1.6KB, extends n/a/a)
| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | game.n | 检测队伍 |
| `b` | custom.g | 队伍标签 |
| `b(n.a)`→boolean | 检查队伍是否有指定标签 |

### 16o. `n/a/c` — UnitCountCondition (4.4KB, extends n/a/a, 16字段)
| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | Integer | maxUnits |
| `b` | Integer | minUnits |
| `c` | game.n | 目标队伍 |
| `d` | as | 单位类型筛选 |
| `e` | boolean | onlyBuildings |
| `f` | boolean | onlyIdle |
| `g` | boolean | onlyMainBuildings |
| `h` | boolean | onlyOnResourcePool |
| `o` | custom.g | 队伍标签筛选 |
| `p` | boolean | includeIncomplete |
| `e(n.a)`→boolean | ★ 遍历全局单位列表进行条件检测 (395B) |

## 17. 工具/框架基础类

### 17a. `utility.m` — CustomArrayList (7KB, 34方法)
| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | int | 当前大小 |
| `b` | Object[] | 内部数组 |
| `add()` | →boolean | 追加 |
| `a(int)` | →Object | 无边界检查get |
| `b()` | →Object | pop (移除最后一个) |
| `c()` | →Object | removeFirst |
| `iterator()` | →Iterator | 返回 utility.n |

### 17b. `utility.u` — UnitRegistry (7KB, 36方法)
| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | static am[] | 空数组常量 |
| `b` | int | 当前单位数 |
| `c` | am[] | 内部单位数组 |
| 用途 | — | ★ am.bE — 全局活跃单位注册表 |

### 17c. `utility.o` — DequeList (6KB, 32方法)
| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | utility.m | 前端列表 |
| `b` | utility.m | 后端列表 |
| `a(Object)` | →void | addFirst |
| `b(Object)` | →void | addLast |
| `a()` | →void | rebalance |

### 17d. `utility.g` — RingBuffer (3KB, 24方法)
| 字段 | 类型 | 含义 |
|------|------|------|
| `b` | Object[] | 存储数组 |
| `c` | int | head指针 |
| `d` | int | tail指针 |
| `a()` | →Object | removeFirst (出队) |

### 17e. `gameFramework.f` — GameUtils (36KB, 128方法, 21字段)
| 关键方法 | 含义 |
|---------|------|
| `a(int,int,int)→int` | ★ LCG随机整数 (166B, 被n.i调用) |
| `i(float,float)→float` | 复杂角度计算 (237B) |
| `a(long)→String` | 时间格式化 (203B) |
| `b(byte[])→String` | MD5哈希 |
| `a(byte[])→String` | SHA-256哈希 |
| `c()→int` | CPU核心数检测 |
| 10个 `float[]` | 三角函数查找表 (预计算sin/cos/tan) |

### 17f. `custom.bb` — LocalizedString (2KB)
| 字段 | 类型 | 含义 |
|------|------|------|
| `b` | bc[] | 翻译条目数组 |
| `c` | String | 原始key |
| `b()` | →String | ★ 获取翻译文本 |
| `a()` | →boolean | 是否有翻译 |

### 17g. `gameFramework.bq` — BaseGameObject
所有需要序列化的游戏对象的基类。`a(j.as)` 为序列化钩子。

---

## 收入公式 (完整验证)

```
引擎: s.a(am) → g += am.cy()    (cm >= 1.0f 时)
      s.b(am) → g -= am.cy()
      
收入率: s.g = Σ cy()          (每40帧显示值)

实际每秒: income/s = s.g × (60fps ÷ 40帧) × ay.h
                  = s.g × 1.5 × 收入倍率

CC:  cy()=18 → 18 × 1.5 × 2.5 = 67.5/s  ✓
T1:  cy()=8  →  8 × 1.5 × 2.5 = 30.0/s  ✓
```

---

## AI 分配逻辑 (服务器特有)

```
服务器: PlayerManage.addAI()
  1. gameScriptMultiPlayer.addAi()  # 引擎侧创建AI控制器
  2. for pos in 0..maxPlayer:
       if getPlayer(pos)==null && existPlayer(pos):
         AiPlayer(pos)  # 填入第一个空槽位
  3. AI继承该槽位的 n.r (队伍)、n.k (索引)
  4. AI名称: "AI-{难度}" (不在快照中)
```

---

## 18. rwTool 备选字段映射 (版本差异参考)

> 来源: rwTool/dataUtil/src/UnitUntil.java — 直接引用混淆类

| rwTool字段 (ce类) | v1.15引擎 (am类) | 含义 | 类型 |
|-------------------|-----------------|------|------|
| `cw` | `cu` | ★ 当前HP | float |
| `cx` | `cv` | ★ 最大HP | float |
| `cz` | `cx` | ★ 当前护盾 | float |
| `cC` | — | ★ 最大护盾 | float |
| `cD` | `cB` | ★ 能量值 | float |
| `cG` | `cE` | ★ 弹药计数 | int |
| `ej` | — | ★ 单位ID | long |
| `eq` | `eo` | ★ X坐标 | float |
| `er` | `ep` | ★ Y坐标 | float |
| `es` | `eh` | ★ 高度层级 | float |
| `co` | `cm` | ★ 建造进度 | float |
| `ci` | `cg` | ★ 朝向角度 | float |
| `ce` | — | X速度分量 | float |
| `cf` | — | Y速度分量 | float |
| `cQ` | `cN` | ★ 父单位引用 | ref(bp) |
| `bZ` | `bX` | ★ 所属玩家 | ref(p) |

### rwTool 玩家字段 (p类 ← → n类)

| rwTool | v1.15 | 含义 |
|--------|-------|------|
| `l` | `r` | ★ 队伍ID |
| `w` | `v` | ★ 玩家名称 |
| `s` | — | 备用队伍/特殊标志 |
| `x` | `w` | ★ AI标志 (boolean) |
| `c` | `c` | ★ 最大玩家数 (static) |

### rwTool 类名映射

| rwTool | v1.15 | 含义 |
|--------|-------|------|
| `ce` | `am` | UnitInstance (单位实例) |
| `p` | `n` | GameState/Player (游戏状态) |
| `cj` | `as` | UnitType (单位类型枚举) |
| `bp` | — | Attachment类型 (父单位) |
| `aj` | `l` | GameFramework/GlobalState |
| `ah` | `fw.w` | 游戏对象基类 |

> **注意**: 字段名差异表明 rwTool 引用的游戏版本与 v1.15 不同，混淆字段名已被重新排列。核心逻辑和常量值不受影响。

---

*280+ 字段映射 | 31个核心类 | 14处误判修正 | 服务器 + rwTool 交叉验证*
