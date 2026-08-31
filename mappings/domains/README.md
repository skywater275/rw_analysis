# mappings/domains/ — 按游戏功能域拆分的映射库

> 自动生成 | 总计 10,797 条映射 | 12 个功能域

## 概览

`supplement.csv` 是主映射数据库，本目录将其按游戏功能系统拆分为独立文件，
便于按功能域进行针对性的解混淆工作。

| # | 域文件 | 映射数 | 类数 | 核心类 |
|---|--------|--------|------|--------|
| 1 | [01-units.csv](01-units.csv) | 1,200 | 31 | UnitInstance(am), UnitType(y), MovableUnit(x), WeaponType, UnitRegistry, TeamUnitTracker, CombatMain |
| 2 | [02-buildings.csv](02-buildings.csv) | 523 | 28 | Factory(h), CommandCenter(d.e), BuilderUnit(d.j), Building(e.c), ExperimentalUnit(d.d) |
| 3 | [03-actions.csv](03-actions.csv) | 732 | 55 | GameAction(s), AttackAction(d), BuildAction(g), GuardAction, Patrol, Stop, Command(e), CommandController(c) |
| 4 | [04-ai.csv](04-ai.csv) | 270 | 18 | GameWorld(a.a), AIWaveSystem(n.f), AITask, MissionParser, MissionExecutor, AISpawnList |
| 5 | [05-map.csv](05-map.csv) | 255 | 14 | MapEngine(b.b), MapRenderer(b.c), MapLayer(b.g), MapSpawn, TMX解析 |
| 6 | [06-network.csv](06-network.csv) | 832 | 83 | NetEngine(j.ad), InputNetStream(j.k), OutputNetStream(j.as), PlayerConnect, 可靠UDP(a.a) |
| 7 | [07-engine.csv](07-engine.csv) | 1,047 | 226 | GlobalState(l), GameObject(w), ReplayEngine(ba), GameSaver(y), StatsManager, PlayerState(n), GameScreen(i) |
| 8 | [08-rendering.csv](08-rendering.csv) | 1,677 | 115 | EffectConfig(m), InGameUI(f.g), HUDManager(d), SoundFactory(a), Slick2DRenderer |
| 9 | [09-custom.csv](09-custom.csv) | 1,736 | 70 | CustomUnitType(j), ModUnitRegistry(l), ResourceComponent, LogicBoolean(215类), INI解析 |
| 10 | [10-pathfinding.csv](10-pathfinding.csv) | 148 | 20 | PathFinder(k), AStarSearch, SpatialGrid(cc), MovementController(f) |
| 11 | [11-platform.csv](11-platform.csv) | 602 | 115 | Steamworks, LibRocket, AppFramework, KeyBindingManager, android/javax桩, ModInfo |
| 12 | [12-utility.csv](12-utility.csv) | 1,775 | 114 | GameUtils(f), RingBuffer, CustomArrayList, DataField, 本地化, 文件IO, 序列化工具 |

## 详细说明

### 1. 单位核心 (`01-units.csv`)

**单位实例、类型、武器、队伍追踪**。

包含 UnitInstance(am) 的所有字段和方法、UnitType(y) 的类型树、MovableUnit(x) 的移动接口、WeaponTypeEnum(av)、WeaponAction(au)、UnitRegistry(ar) 和 UnitTypeHandle(as)。

参见: `docs/01-units/UNIT-LIFECYCLE.md`, `UNIT-LOADING.md`, `WEAPON-DAMAGE.md`

### 2. 建筑/工厂 (`02-buildings.csv`)

**工厂、建造队列、建筑类型**。

包含 Factory(h) 的建造逻辑、CommandCenter(d.e)、ExperimentalUnit(d.d)、BuilderUnit(d.j)、Building(e.c) 基类和 Structures(d.t)。

参见: `docs/02-buildings/FACTORY.md`

### 3. 指令系统 (`03-actions.csv`)

**15种 GameAction + Command 序列化**。

包含 GameAction(s) 基类及所有子类：Attack(d)、Build(g)、Guard、Patrol、Stop、Sell、Repair、Reclaim、Ping、MapPing、TeamChat、RallyPoint 等。Command(e) 二进制序列化和 CommandController(c)。

参见: `docs/03-actions/UNIT-ACTIONS.md`, `GAME-ACTION-METHODS.md`

### 4. AI系统 (`04-ai.csv`)

**AI 玩家和任务引擎**。

包含 GameWorld(a.a) 三层时钟系统（0.25/2.0/4.5s）、Zone 系统、UnitGroup 状态机；AIWaveSystem(n.f) 波次管理、AITask、MissionParser、MissionExecutor、MissionEvent、AISpawnList。

参见: `docs/04-ai/AI-ARCHITECTURE.md`

### 5. 地图系统 (`05-map.csv`)

**TMX 地图加载和渲染**。

包含 MapEngine(b.b)、MapRenderer(b.c)、MapLayer(b.g)、MapSpawn、战争迷雾和地形系统。

参见: `docs/05-map/MAP-SYSTEM.md`

### 6. 网络通信 (`06-network.csv`)

**3层网络协议栈**。

包含 NetEngine(j.ad) 主网络引擎、InputNetStream(j.k)、OutputNetStream(j.as)、PlayerConnect(j.c)；可靠UDP传输层 (a.a.*)。

参见: `docs/06-network/NETWORK-STACK.md`, `NETWORK-PROTOCOL.md`

### 7. 引擎核心 (`07-engine.csv`)

**全局状态、主循环、回放、统计、玩家**。

包含 GlobalState(l) 引擎单例、GameObject(w) 实体基类、ReplayEngine(ba) 回放、GameSaver(y) 存档、StatsManager(bg) 统计、PlayerState(n)、GameScreen(i) 主界面。

参见: `docs/07-engine/GAMELOOP.md`, `MATCH-LIFECYCLE.md`

### 8. 渲染与音频 (`08-rendering.csv`)

**OpenGL ES 2.0 渲染、UI、音频**。

包含 EffectConfig(m) 特效引擎、InGameUI(f.g) 游戏界面、HUDManager(d) 抬头显示、SoundFactory(a) 音频引擎、Slick2DRenderer 桌面渲染。

参见: `docs/08-rendering/RENDERING.md`, `AUDIO-HUD.md`

### 9. 自定义/Mod (`09-custom.csv`)

**Mod 系统和自定义单位**。

包含 CustomUnitType(j) 自定义单位类型、ModUnitRegistry(l) Mod注册表、ResourceComponent(d.b) 资源成本、LogicBoolean 脚本引擎(215类)、INI 配置解析、TeamTag 和所有 custom.* 子包。

参见: `docs/09-custom/CUSTOM-UNIT.md`, `LOGIC-ENGINE.md`, `INI-PARSING.md`

### 10. 寻路系统 (`10-pathfinding.csv`)

**A* 寻路和空间查询**。

包含 PathFinder(k) A*引擎、PathSolver、AStarSearch、NodePool、MovementController(f) 移动控制器、SpatialGrid(cc) 空间网格。

参见: `docs/10-pathfinding/ASTAR-PATHFINDING.md`, `SPATIAL.md`

### 11. 平台层 (`11-platform.csv`)

**平台抽象层**。

包含 Steamworks API 绑定、LibRocket UI 框架、AppFramework 应用框架、KeyBindingManager(ac) 按键管理、android/javax 桩代码。

参见: `docs/11-platform/` (待补充)

### 12. 工具/数据结构 (`12-utility.csv`)

**引擎工具类和数据结构**。

包含 GameUtils(f) 数学工具（含 360° 三角函数表）、CustomArrayList(m)、RingBuffer(g)、DequeList(o)、DataField 序列化、本地化、文件IO。

参见: `docs/12-utility/DEVELOPER-COMMENTS.md`

---

## 使用说明

### 与 supplement.csv 的关系
- `supplement.csv` 是**唯一主数据库**，本目录的域文件是其快照
- 新增映射应添加到 `supplement.csv`，然后重新运行 `split_mappings.py` 更新域文件
- 域文件用于**查询特定功能域的所有映射**，不用于编辑

### 重新生成
```bash
cd rw源码逆向 && python tools/utils/split_mappings.py
```

### 与文档的对应
每个域文件对应 `docs/` 中的一个或多个系统文档，详见上方各域说明中的 `参见` 链接。

---

## 统计摘要

| 指标 | 数值 |
|------|------|
| 总映射数 | 10,797 |
| 功能域数 | 12 |
| 字段映射 | 6,323 |
| 方法映射 | 4,474 |
| 覆盖类数 | 889 (含跨域重复) |

> 生成日期: 2026-08-10 | 工具: `tools/utils/split_mappings.py`