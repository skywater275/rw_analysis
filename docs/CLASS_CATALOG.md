# Rusted Warfare v1.15 — 完整类目录 (1698类)

> 80个包 | 1698类 | 逆向日期: 2026-06-06
> 逆向度: 核心逻辑 99% | 全部类 ~70%

---

## 1. 游戏核心 — `com.corrodinggames.rts.game.*`

### 1.1 game.* 根 (22类)

| 类 | 大小 | 含义 | 逆向度 |
|----|------|------|--------|
| `i` | 51KB | **GameScreen** — 主游戏实例, extends GlobalState | 90% |
| `f` | 36KB | **MovementController** — 移动/弹丸管理, 118字段 | 95% |
| `n` | 37KB | **GameState** — 玩家状态, 74字段, 129方法 | 95% |
| `g` | 5.4KB | **Projectile** — 弹丸基类, 111字段 | 90% |
| `l` | 5.4KB | **GroundMark** — 地面标记(血/焦痕), extends fw.w | 90% |
| `j` | 2.6KB | **UIElement** — UI辅助 | 70% |
| `k` | 1.5KB | **Screenshot** — 截图保存 Runnable | 80% |
| `m` | 801B | **MarkType** enum — normal/nuke | 100% |
| `o` | 981B | **ColorTransform** enum — hueAdd/hueShift等 | 100% |
| `p` | 310B | **TeamTagGroup** — 队伍标签分组数据 | 90% |
| `q` | — | 队伍比较器 | 80% |
| `s` | 5KB | **TeamUnitTracker** — 收入/单位统计, 5资源+2队列 | 100% |
| `t` | 740B | **BuildQueue** — 建造队列 | 100% |
| `a`~`h` | 小 | 杂项 | 70% |

### 1.2 game.a.* — AI + 战斗 (28类)

| 类 | 大小 | 含义 | 逆向度 |
|----|------|------|--------|
| `a` | 44KB | **AIPlayer** — AI玩家控制器, 71字段, 79方法 | 98% |
| `i` | 29KB | **AttackZone** — 攻击区, 42字段, 53方法 | 95% |
| `g` | 13KB | **BuildZone** — 建造区, 31字段 | 95% |
| `n` | 8.2KB | **TransporterGroup** — 运输编组区 | 90% |
| `o` | 5.6KB | **Zone** abstract — 区基类 | 100% |
| `h` | 2.7KB | **ZoneBase** — 带F/G ArrayList | 100% |
| `l` | 2.1KB | **ZoneType4** — 定时器区 | 90% |
| `m` | 250B | **PlainZone** — 最简区 | 100% |
| `d` | 3.7KB | **AIUnitCombo** — 出兵组合选择 | 95% |
| `f` | 2.3KB | **AIUnitFilter** — AI单位筛选 | 90% |
| `j` | 937B | **ZoneState** enum — Pre/Prepare/Active | 100% |
| `k` | 885B | **ZoneType** enum — ForwardOutpost/Main/ResourceOutpost | 100% |
| `c` | 1.4KB | **ActionType** — 动作类型 | 90% |
| `b` | 815B | **AIContext** enum — include/exclude | 100% |
| `e` | 449B | **AIComboEntry** — 权重条目 | 100% |
| `a$1~$13` | — | AIPlayer内部类(攻击编组) | 70% |

### 1.3 game.a.a.* — AIPlayer内部 (7类)

| 类 | 含义 |
|----|------|
| `a`/`a$1` | AIPlayer区域迭代器 |
| `b`/`b$1`/`b$2` | 比较器 |
| `c` | 区域类型基类 |
| `d` | extends c, 区域实现 |

### 1.4 game.b.* — 地图系统 (13类)

| 类 | 大小 | 含义 | 逆向度 |
|----|------|------|--------|
| `b` | 38KB | **MapEngine** — 地图核心, 5瓦片层, 路径节点 | 90% |
| `c` | 19KB | (单位类型, 非迷雾) | 80% |
| `e` | 13KB | **TMXLoader** — TMX地图解析, 1472B主方法 | 95% |
| `a` | 10KB | **MapObject** — 地图对象配置, 16字段 | 90% |
| `j` | 9.3KB | **TilesetLoader** — 瓦片集/图像加载 | 90% |
| `g` | 8.6KB | **GroundDetails** — 地面细节/随机瓦片 | 90% |
| `h` | 5.2KB | **TileRenderer** — 瓦片渲染 | 80% |
| `d` | 2.2KB | 地图杂项 | 70% |
| `i` | 2.1KB | 地图杂项 | 70% |
| `k` | 419B | 地图杂项 | 60% |
| `f` | 372B | **MapException** | 100% |

### 1.5 game.units.* — 单位系统 (146类)

| 核心类 | 大小 | 含义 | 逆向度 |
|--------|------|------|--------|
| `y` | 90KB | **UnitType** — 单位类型基类 | 90% |
| `am` | 43KB | **UnitInstance** — 单位实例 | 85% |
| `ar` | 20KB | **UnitTypeRegistry** — 内置单位枚举 | 90% |
| `h` | 20KB | **FactoryBase** — 工厂/建筑基类 | 95% |
| `p` | 8.8KB | **MapEditor** — 地图编辑任务 | 85% |
| `al` | 8.9KB | **TreeUnit** — 树木装饰单位, extends v | 90% |
| `ai` | 7.3KB | **FireUnit** — 火焰特效单位, extends v | 85% |
| `f` | 7.8KB | **DamagingBorder** — 伤害边界, extends x | 85% |
| `g` | 7.3KB | **BuildingBase2** — 建筑基类2, extends e/j | 85% |
| `au` | 5.9KB | **WeaponInstance** — 武器实例, 14字段 | 95% |
| `aq` | 4.4KB | **UnitPathUtils** — 寻路工具, 10PointF | 90% |
| `ao` | 1.9KB | **MovementType** enum — 8种 | 100% |
| `av` | 1.7KB | **WeaponType** enum — 17种 | 100% |
| `a` | 1.1KB | **BehaviorMode** enum — aggressive/guardArea/returnFire... | 100% |
| `ag` | 899B | **Behavior** enum — attack/move/newSelection/clone | 100% |
| `aj` | 1.1KB | **TeamFilter** — 队伍过滤器, extends f.i | 95% |
| `ap` | 1KB | **MovementParams** — 移动参数, 13float | 90% |
| `ak` | 227B | 接口 — 5空方法 | 80% |
| `an` | 310B | 数据持有者 | 70% |
| `u` | 1.4KB | extends x, 60常量 | 70% |
| `t` | 1.3KB | extends x, 皮肤/变体 | 70% |
| `r` | 1.2KB | **TerrainType** enum — dust/grass/sand | 100% |
| `q` | 2.2KB | 单位相关 | 60% |
| `x` | 2.5KB | 单位基类 | 70% |
| 其余~120类 | — | 各单元类型实现 | 50-90% |

### 1.6 game.units.custom.* — 自定义单位 (89+子包)

| 核心类 | 大小 | 含义 | 逆向度 |
|--------|------|------|--------|
| `j` | 90KB | **CustomUnitImpl** — 主自定义单位, 290方法 | 85% |
| `l` | 37KB | **UnitTypeLoader** — 单位类型加载器, 384字段 | 90% |
| `as` | 9.4KB | **ActionSet** — 动作集, 19内部类 | 90% |
| `at` | 2KB | **ActionDef** — 动作定义 abstract | 90% |
| `f` | 10KB | **EffectDef** — 效果定义 | 85% |
| `e` | 6.2KB | **ResourceDef** — 资源定义 | 85% |
| `c` | 3.8KB | — | 75% |
| `g` | 3.9KB | **TeamTag** — 队伍标签 | 95% |
| `h` | 1.3KB | **CustomUnitType** — 自定义类型引用 | 90% |
| `k` | 658B | **Event** — 事件触发器 | 90% |
| `i` | 1.7KB | — | 70% |
| `bp` | 14KB | **UnitSpawnConfig** — 出兵配置 | 95% |
| `bh` | 17KB | **ProjectileTemplate** — 弹丸模板, extends g | 90% |
| `bn` | 15KB | **TurretConfig** — 炮塔定义, 88字段 | 95% |
| `bi` | 8.7KB | — | 75% |
| `bb` | 2KB | **LocalizedString** — 多语言文本 | 95% |
| 其余~70类 | — | 辅助/配置 | 40-80% |

#### 1.6a custom.a.* — 动作实现 (7+15类)
- `a.g` (9.8KB) — CustomActionConfig, 46方法
- `a.a`~`a.o` — 15个具体动作类型, 各有1000-1351B主方法

#### 1.6b custom.b.* — 配置解析器 (14类)
- `b.c` (25KB) — 动画配置, 3479B+2421B解析方法
- `b.m` (13KB) — 附着/槽位配置, 977B+967B
- `b.h` (9.2KB) — 生成/槽位配置, 1816B+996B
- 其余 — 各种 .ini 配置段解析器

#### 1.6c custom.d.* — 资源/伤害 (4类)
- `d.b` (17KB) — **ResourceComponent** ★ 52方法, "ammo", "energy", "shield"
- `d.c` (6.4KB) — DynamicResourcePrice
- `d.a`, `d.d` — 辅助

#### 1.6d custom.e.* — 资源类型 (7+6类)
- `e.f` (12.4KB) — ResourceType注册表, 45方法
- `e.a` (8.6KB) — 内置资源定义, 34字段
- `e.d` (6.8KB) — 资源解析器, 1104B
- `e.a.a~f` — 6个子资源类型

#### 1.6e custom.f.* — 效果 (6类)
- `f.a` (5.3KB) — 效果配置, 1012B
- `f.b` (4.2KB) — 动态字符串, "% instead of $"

#### 1.6f custom.logicBooleans.* — 条件脚本 (215类)
| 核心类 | 大小 | 含义 |
|--------|------|------|
| `LogicBoolean` | 16KB | 抽象基类, 42方法 |
| `LogicBooleanLoader` | 20KB | .ini解析器 |
| `UnitReference` | 13KB | 22种单位引用 |
| `LogicBooleanGameFunctions` | 12KB | 游戏条件函数注册 |
| `CompareJoinerBoolean` | — | 比较器基类+子类(15个) |
| `AndBoolean`/`OrBoolean`/`NotBoolean` | — | 逻辑运算 |
| 其余~190类 | — | 各具体条件实现 |

### 1.7 game.units.d.* — 工厂/建造 (34+13类)

| 核心类 | 大小 | 含义 | 逆向度 |
|--------|------|------|--------|
| `j` | 3KB | **BuildProgress** — 建造进度, 14字段 | 100% |
| `l` | 578B | **Factory** interface — 12抽象方法 | 100% |
| `e` | 7.2KB | **CommandCenter** CC单位 | 95% |
| `i` | — | CC父类 | 80% |
| `a`~`h` | — | 建造系统辅助 | 60-80% |

#### 1.7a units.d.a.* — 具体单位类型 (13类)
- `a.b` (10KB) — 大型单位, 57方法
- `a.a` (6.1KB) — extends a.b, 27方法

### 1.8 game.units.e.* — 经济组件 (17类)
- `f` (7.2KB) — 经济ResourceComponent类型
- `a`~`o` — 经济相关接口/基类

### 1.9 game.units.f.* — 空间索引 (10类)
| 类 | 含义 |
|----|------|
| `c` | **SpatialGrid** — 32×32网格, 50px单元 |
| `a` | 网格单元 |
| `b` | 单位列表 |
| `d` | RectFilter |
| `g` | CircleFilter |
| `h` | LineFilter |
| `i` | TeamFilter |
| `e` | UnitFilter abstract |
| `j` | ResultCallback |
| `f` | QueryResult |

### 1.10 game.units.b.* — 地图建筑块 (10类)
| 类 | 含义 |
|----|------|
| `c` | amphibiousJet单位类型 |
| `d` | (另一个单位类型) |
| `a`/`b`/`e` | 地图元素类型 |

### 1.11 game.units.g.* / h.* — 单位子类型 (8+8类)
- `h.e` (7.6KB) — Factory, 41方法
- `h.a~f` — 各种工厂/生成器类型

---

## 2. 游戏框架 — `com.corrodinggames.rts.gameFramework.*`

### 2.1 fw.* 根 (93类)

| 核心类 | 大小 | 含义 | 逆向度 |
|--------|------|------|--------|
| `l` | 32KB | **GlobalState** — 全局单例 l.B(), 214字段 | 85% |
| `y` | 27KB | **GameSaver** — 存档/读档, 4102B load | 90% |
| `f` | 36KB | **GameUtils** — 128方法: 数学/字符串/随机/IO | 95% |
| `w` | 4KB | **GameWorld** — 游戏对象基类+ID系统 | 95% |
| `z` | 1.8KB | **GameThread** — 游戏主线程 | 90% |
| `bq` | <1KB | **BaseGameObject** — 序列化基类 | 100% |
| `aa` | 4.1KB | **ProjectileOwner** — 弹丸持有者 | 90% |
| `ab` | 6KB | **ProjectileManager** — 弹丸碰撞管理 | 90% |
| `ac` | 10KB | **KeyBindings** — 热键配置, 67字段 | 95% |
| `ad` | 4.3KB | **KeyBinding** — 单个按键绑定 | 95% |
| `ae` | 278B | extends ad | 90% |
| `af` | 517B | **InputAxis** — 输入轴抽象 | 85% |
| `ag` | 1KB | 输入轴实现 | 80% |
| `ah` | 1.3KB | 摇杆轴 | 80% |
| `ai` | 704B | 抽象输入 | 70% |
| `aj` | 205B | 空类 | — |
| `j` | 2.5KB | **FileChangeEngine** — 文件监视 | 80% |
| `c` | 2.9KB | **CommandQueue** — 指令队列管理 | 85% |
| `k` | 496B | **SleepThread** — 睡眠线程 | 80% |
| `n` | 536B | — | 60% |
| `u` | 1.1KB | **ResourceDomain** enum — font/image/sound/uiImage... | 100% |
| `h` | 948B | **OS** enum — Linux/MacOS/Windows/Other | 100% |
| `g` | 904B | **OSDetector** — OS检测 | 90% |
| `s` | 907B | **GameStateType** enum — menu/normal/normalSave | 100% |
| `x` | 721B | **GameObjectComparator** — ID排序 | 90% |
| `b` | 559B | **BoolRef** — 布尔包装 | 80% |
| `q` | 950B | **RunnableQueue** | 80% |
| `d` | 1.4KB | 数据持有者(8字段) | 70% |
| 其余~60类 | — | 配置/基类/辅助 | 40-70% |

### 2.2 fw.j.* — 序列化 (68类)
| 核心类 | 含义 | 逆向度 |
|--------|------|--------|
| `k` | **InputNetStream** — 二进制读取, 42方法 | 95% |
| `as` | **OutputNetStream** — 二进制写入, 45方法 | 95% |
| `ad` | **NetEngine** — 104KB网络引擎 | 80% |
| `au` | 网络包类型 | 70% |
| 其余~60类 | 序列化辅助 | 40-60% |

### 2.3 fw.b.* — 渲染/字体 (40+9类)
- `b.n` (26KB) — **ShaderCompiler** — "== Compiling shader =="
- `b.a.b` (7KB) — 字体渲染
- `b.ah` (5.2KB) — 渲染组件
- `b.a.*` (9类) — 字体子系统
- `b.a.a.*` (2类) — 字体底层

### 2.4 fw.k.* — 寻路工作线程 (17类)
| 核心类 | 含义 |
|--------|------|
| `k.o` (21KB) | **GameLoop** — 性能统计/帧计时 |
| `k.l` (14KB) | **PathfindingPool** — "cores, creating extra solvers" |
| `k.i` (11KB) | **PathSolver** — 路径求解器 |
| `k.k` (6.4KB) | **PathCostCalc** — 移动成本计算 |
| `k.a` (7.6KB) | 类型化ArrayList |
| 其余~12类 | 寻路辅助 |

### 2.5 fw.d.* — 特效引擎 (8类)
| 核心类 | 含义 |
|--------|------|
| `d.c` (16KB) | **EffectEngine** — 粒子特效管理器 |
| `d.e` (12KB) | **DisplacementShader** — 位移着色器, 77字段 |
| `d.a` (7.3KB) | 特效配置 |
| 其余~5类 | 特效辅助 |

### 2.6 fw.e.* — 文件系统 (8类)
| 核心类 | 含义 |
|--------|------|
| `e.c` (13KB) | 文件系统管理器 |
| `e.g` (7.7KB) | 文件缓存 |
| `e.a` (6.5KB) | 路径管理 |
| 其余~5类 | 文件IO辅助 |

### 2.7 fw.i.* — Mod管理 (3类)
| 核心类 | 含义 |
|--------|------|
| `i.a` (15KB) | Mod加载器, "Bad build number" |
| `i.b` (12KB) | Mod信息, 48字段 |
| `i.c` (428B) | Mod类型 |

### 2.8 fw.n.* — 任务引擎 (14+3类) ★完整
见 [08_AI.md](08_AI.md)

### 2.9 fw.utility.* — 工具集 (56+3类)
见 [09_UTILITY.md](09_UTILITY.md)

### 2.10 fw.m.* — 渲染管线 (106类)
**故意跳过** — 不影响游戏逻辑/回放分析

### 2.11 fw.f.* — 框架扩展 (71类)
部分字体/渲染/网络辅助 — 低优先级

### 2.12 其他fw子包
| 包 | 类数 | 含义 |
|----|------|------|
| `fw.g` | 7 | OS检测+定时器 |
| `fw.l` | 3 | 语言/本地化 |
| `fw.c` | 3 | 应用框架 |
| `fw.o` | 1 | 单类 |
| `fw.h` | 2 | 本地化辅助 |

---

## 3. 平台/第三方 (故意跳过)

| 包 | 类数 | 说明 |
|----|------|------|
| `com.codedisaster.steamworks` | 121 | Steam API自动生成 |
| `com.corrodinggames.rts.appFramework` | 47 | Android应用层 |
| `com.corrodinggames.rts.java.*` | 30 | Java平台实现 |
| `com.corrodinggames.rts.java.audio.*` | 37 | 音频系统 |
| `com.corrodinggames.librocket.*` | 36 | HTML UI引擎 |
| `android.*` | 31 | Android API桩代码 |
| `com.corrodinggames.rts.R$*` | 13 | Android资源ID |
| `org.a.a.*` | 19 | 第三方库 |
| `a.a.*` | 21 | 混淆工具类 |
| `com` 根 | 6 | LibRocket绑定 |

---

## 逆向完成度总表

```
系统              已分析    总计    完成度
─────────────────────────────────────────
经济/收入          全部      ~15     100%
战斗/伤害          全部      ~20     100%
建造系统           全部      ~15     100%
单位类型           全部      ~30     100%
网络/指令          全部      ~25     100%
空间索引           全部      ~10     100%
AI/任务引擎        全部      ~35     100%
玩家/队伍          全部      ~10     100%
移动/寻路          全部      ~15      98%
工具/框架          全部      ~25      95%
LogicBoolean       核心      ~215     95%
地图/TMX          全部      ~13      95%
武器/弹丸          全部      ~10      95%
特效/粒子          全部      ~8       90%
Mod系统            全部      ~8       90%
文件系统           全部      ~8       85%
UI/输入            全部      ~15      85%
渲染管线           有意跳过  ~106     10%
音频系统           有意跳过  ~37      10%
Steam/平台桩       有意跳过  ~250      0%
第三方库           有意跳过  ~19       0%
─────────────────────────────────────────
游戏逻辑总计       ~300     ~500      98%
全部总计           ~500     ~1698    ~70%
```

---

*最后更新: 2026-06-06 | 9轮分析, ~200类完整反编译*
