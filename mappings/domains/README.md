# mappings/domains/ — 按游戏功能域拆分的映射库

> 自动生成 | 总计 7,800 条映射 | 14 个功能域

## 概览

`supplement.csv` 是主映射数据库，本目录将其按游戏功能系统拆分为独立文件，
便于按功能域进行针对性的解混淆工作。

| # | 域文件 | 映射数 | 类数 | 核心类 |
|---|--------|--------|------|--------|
| 1 | [01-units.csv](01-units.csv) | 975 | 47 | UnitInstance(am), UnitType(y), MovableUnit(x), WeaponType, WeaponAction, 投射物 |
| 2 | [02-unit-actions.csv](02-unit-actions.csv) | 895 | 73 | GameAction(s), AttackAction(d), BuildAction(g), Command, CommandSlot, 建筑, 寻路(单位侧), 调试 |
| 3 | [03-custom-core.csv](03-custom-core.csv) | 1,317 | 42 | CustomUnitType(j), ModUnitRegistry, ResourceComponent, INI 配置, 条件, 资源 |
| 4 | [04-custom-logic.csv](04-custom-logic.csv) | 148 | 10 | LogicBoolean(215 类, 全为 $ 类), 动画(anim/animation), 特效, 自定义动作 |
| 5 | [05-ai.csv](05-ai.csv) | 386 | 33 | GameWorld(a.a), AIWaveSystem, AITask, MissionParser, MissionExecutor |
| 6 | [06-world.csv](06-world.csv) | 1,021 | 35 | MapEngine(b.b), MapRenderer(b.c), MapLayer, TMSP, GameEngine(本级), PathFinder(k) |
| 7 | [07-engine.csv](07-engine.csv) | 920 | 44 | GlobalState, GameObject, ReplayEngine, GameSaver, 命令, Mods, Steam, 平台适配, 文件系统 |
| 8 | [08-rendering.csv](08-rendering.csv) | 712 | 57 | EffectConfig(m), OpenGL(b), 渲染管线, 绘制特效 |
| 9 | [09-ui-audio.csv](09-ui-audio.csv) | 433 | 25 | InGameUI(f.g), HUDManager, UI 面板, SoundFactory |
| 10 | [10-network.csv](10-network.csv) | 674 | 42 | NetEngine(j.ad), InputNetStream, OutputNetStream, 可靠 UDP(a.a) |
| 11 | [11-utility.csv](11-utility.csv) | 82 | 22 | GameUtils, RingBuffer, CustomArrayList, DataField, 序列化工具 |
| 12 | [12-platform.csv](12-platform.csv) | 161 | 35 | AppFramework, JDK 仿真层(java.audio/input/filesystem), platform.net, 根类 |
| 13 | [13-librocket.csv](13-librocket.csv) | 75 | 9 | LibRocket UI 脚本引擎(scripts) |
| 14 | [14-thirdparty.csv](14-thirdparty.csv) | 1 | 1 | Steamworks 绑定(codedisaster), org.a.*(第三方), 杂项(com) |

## 详细说明

### 1. units (`01-units.csv`)

**单位实例、类型、武器、队伍追踪**。

包含 UnitInstance(am) 的所有字段和方法、UnitType(y) 的类型树、MovableUnit(x) 的移动接口、WeaponTypeEnum(av)、WeaponAction(au)、UnitRegistry(ar) 和 UnitTypeHandle(as)。

参见: `docs/01-units/UNIT-LIFECYCLE.md`, `UNIT-LOADING.md`, `WEAPON-DAMAGE.md`

### 2. unit-actions (`02-unit-actions.csv`)

**工厂、建造队列、建筑类型**。

包含 Factory(h) 的建造逻辑、CommandCenter(d.e)、ExperimentalUnit(d.d)、BuilderUnit(d.j)、Building(e.c) 基类和 Structures(d.t)。

参见: `docs/02-buildings/FACTORY.md`

### 3. custom-core (`03-custom-core.csv`)

**15种 GameAction + Command 序列化**。

包含 GameAction(s) 基类及所有子类：Attack(d)、Build(g)、Guard、Patrol、Stop、Sell、Repair、Reclaim、Ping、MapPing、TeamChat、RallyPoint 等。Command(e) 二进制序列化和 CommandController(c)。

参见: `docs/03-actions/UNIT-ACTIONS.md`, `GAME-ACTION-METHODS.md`

### 4. custom-logic (`04-custom-logic.csv`)

**AI 玩家和任务引擎**。

包含 GameWorld(a.a) 三层时钟系统（0.25/2.0/4.5s）、Zone 系统、UnitGroup 状态机；AIWaveSystem(n.f) 波次管理、AITask、MissionParser、MissionExecutor、MissionEvent、AISpawnList。

参见: `docs/04-ai/AI-ARCHITECTURE.md`

### 5. ai (`05-ai.csv`)

**TMX 地图加载和渲染**。

包含 MapEngine(b.b)、MapRenderer(b.c)、MapLayer(b.g)、MapSpawn、战争迷雾和地形系统。

参见: `docs/05-map/MAP-SYSTEM.md`

### 6. world (`06-world.csv`)

**3层网络协议栈**。

包含 NetEngine(j.ad) 主网络引擎、InputNetStream(j.k)、OutputNetStream(j.as)、PlayerConnect(j.c)；可靠UDP传输层 (a.a.*)。

参见: `docs/06-network/NETWORK-STACK.md`, `NETWORK-PROTOCOL.md`

### 7. engine (`07-engine.csv`)

**全局状态、主循环、回放、统计、玩家**。

包含 GlobalState(l) 引擎单例、GameObject(w) 实体基类、ReplayEngine(ba) 回放、GameSaver(y) 存档、StatsManager(bg) 统计、PlayerState(n)、GameScreen(i) 主界面。

参见: `docs/07-engine/GAMELOOP.md`, `MATCH-LIFECYCLE.md`

### 8. rendering (`08-rendering.csv`)

**OpenGL ES 2.0 渲染、UI、音频**。

包含 EffectConfig(m) 特效引擎、InGameUI(f.g) 游戏界面、HUDManager(d) 抬头显示、SoundFactory(a) 音频引擎、Slick2DRenderer 桌面渲染。

参见: `docs/08-rendering/RENDERING.md`, `AUDIO-HUD.md`

### 9. ui-audio (`09-ui-audio.csv`)

**Mod 系统和自定义单位**。

包含 CustomUnitType(j) 自定义单位类型、ModUnitRegistry(l) Mod注册表、ResourceComponent(d.b) 资源成本、LogicBoolean 脚本引擎(215类)、INI 配置解析、TeamTag 和所有 custom.* 子包。

参见: `docs/09-custom/CUSTOM-UNIT.md`, `LOGIC-ENGINE.md`, `INI-PARSING.md`

### 10. network (`10-network.csv`)

**A* 寻路和空间查询**。

包含 PathFinder(k) A*引擎、PathSolver、AStarSearch、NodePool、MovementController(f) 移动控制器、SpatialGrid(cc) 空间网格。

参见: `docs/10-pathfinding/ASTAR-PATHFINDING.md`, `SPATIAL.md`

### 11. utility (`11-utility.csv`)

**平台抽象层**。

包含 Steamworks API 绑定、LibRocket UI 框架、AppFramework 应用框架、KeyBindingManager(ac) 按键管理、android/javax 桩代码。

参见: `docs/11-platform/` (待补充)

### 12. platform (`12-platform.csv`)

**引擎工具类和数据结构**。

包含 GameUtils(f) 数学工具（含 360° 三角函数表）、CustomArrayList(m)、RingBuffer(g)、DequeList(o)、DataField 序列化、本地化、文件IO。

参见: `docs/12-utility/DEVELOPER-COMMENTS.md`

### 13. librocket (`13-librocket.csv`)

（待补充）

### 14. thirdparty (`14-thirdparty.csv`)

（待补充）

---

## 使用说明

### 与 supplement.csv 的关系
- `supplement.csv` 是**唯一主数据库**，本目录的域文件是其快照
- 新增映射应添加到 `supplement.csv`，然后重新运行 `split_mappings.py` 更新域文件
- 域文件用于**查询特定功能域的所有映射**，不用于编辑

### 重新生成
```bash
cd rw-reverse && python tools/utils/split_mappings.py
```

### 与文档的对应
每个域文件对应 `docs/` 中的一个或多个系统文档，详见上方各域说明中的 `参见` 链接。

---

## 统计摘要

| 指标 | 数值 |
|------|------|
| 总映射数 | 7,800 |
| 功能域数 | 14 |
| 字段映射 | 4,610 |
| 方法映射 | 3,190 |
| 覆盖类数 | 475 (含跨域重复) |

> 生成日期: 2026-08-10 | 工具: `tools/utils/split_mappings.py`