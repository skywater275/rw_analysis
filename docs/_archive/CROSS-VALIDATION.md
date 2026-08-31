# Rusted Warfare v1.15 — 交叉验证与系统集成分析

> 跨系统追踪: 游戏循环 → 指令管线 → AI → 回放 → 地图 → 单位
>
> 方法论: 从多个子系统交叉引用同一字段/方法，消除歧义

---

## 1. 完整游戏循环 (GameThread → 每帧)

### 1.1 调用链

```
GameThread.run()                          [gameFramework/GameThread.java:28]
│
├── f2 = delta * 0.06f                    ← 归一化为 60fps
│
└── GameScreen.a(float f2, int n2)        [game/GameScreen.java:962]
    │  synchronized(this.aj)
    │
    └── GameScreen.b(float f2, int n2)    [game/GameScreen.java:973]
        │
        ├── [预更新阶段]
        │   ├── NetEngine.b(f2)           ← 网络接收
        │   └── GameSaver 自动保存检查
        │
        ├── [追赶循环] (网络/回放同步)
        │   └── while (G > bX.c()):
        │       └── GameScreen.a(f2)      ← ★ 核心游戏更新
        │
        ├── [后更新]
        │   ├── PathEngine.a(f2)          ← 路径查找预更新
        │   ├── AudioEngine.b(f2)
        │   └── InputController.b()
        │
        └── [渲染阶段]
            └── 绘制场景/小地图/HUD
```

### 1.2 核心更新: GameScreen.a(float) — 严格顺序

```
GameScreen.a(f2)                          [line 1352]
│
├──  1. NetEngine.c(f2)                   ← 网络状态处理
├──  2. CommandController.c()             ← ★ 指令队列执行
├──  3. ReplayEngine.a(f2)                ← 回放帧更新
├──  4. PlayerState.g(f2)                 ← 帧前清除 (重置脏标志)
├──  5. for-each BaseGameObject: w.a(f2)  ← ★ 所有游戏对象更新
│       └── GameWorld.a(f2)  [AI 玩家]    ← 通过继承链 (GameWorld extends PlayerState)
├──  6. SpatialGrid.a()                   ← 空间索引重建
├──  7. Projectile.g(f2)                  ← 弹丸更新
├──  8. CustomUnitType.s(f2)             ← 自定义单位逻辑
├──  9. PlayerState.f(f2)                ← 帧后: 收入/战败检查
├── 10. ParticleEffects.a(f2)
├── 11. EffectManager.a(f2)
├── 12. GroupController.a(f2)
├── 13. MinimapHandler.a(f2)
└── 14. PathEngine.b(f2)                 ← 路径查找更新
```

### 1.3 关键时序影响

| 阶段 | 对 RWAgent 的影响 |
|------|-------------------|
| 2. CommandController | RWAgent 注入的 Command 在此执行 |
| 5. GameWorld.a(f2) | AI tick (Tier1/2/3 时钟累加) |
| 5. UnitInstance.a(f2) | 建造进度、HP 回复 |
| 9. PlayerState.f(f2) | 收入发放 (每 90 帧一次) |

**关键发现**: CommandController 在 GameWorld 之前执行。这意味着同一帧注入的建造指令会先于 AI 逻辑执行，AI 可以看到指令的结果。

---

## 2. 回放系统交叉验证

### 2.1 回放文件格式 (二进制)

```
[文件头]
  0x0000: 2B  magic_len (BE uint16)      ← 19
  0x0002: 19B magic                      ← "rustedWarfareReplay"
  0x0015: 4B  gameVersion (BE int32)     ← 176 (SettingsEngine.settingsGameVersion)
  0x0019: 4B  header_int2 (BE int32)     ← 96 (硬编码)
  0x001D: 2B  version_len (BE uint16)    ← 4
  0x001F: 4B  version                    ← "1.15"
  ...:    "gamesave" 块                  ← GameSaver 序列化
  ...:    "gamesave" 块尾

[数据块] (BackgroundWriter 线程写入)
  块类型: "rc" / "wait" / "cs" / "gs" / "chat" / ...
```

### 2.2 回放块类型

| 块名 | 含义 | 内容 |
|------|------|------|
| `rc` | **Record Command** | 完整 Command 对象序列化 |
| `wait` | 等待帧 | 无指令的空帧标记 |
| `cs` | Checksum | 反同步检测校验和 |
| `gs` | GameSave | 完整游戏状态快照 |
| `chat` | 聊天消息 | 玩家聊天 |
| `start` | 开始块 | 游戏开始标记 |

### 2.3 指令录制流程

```
CommandController.b(player):
  ├── cmd.l()                          ← 验证
  ├── this.b.add(cmd)                  ← 加入执行队列
  └── ReplayEngine.a(cmd, playerId)    ← ★ 录制到回放
      │
      └── ReplayEngine.a(e, int) [line 152]:
          ├── 创建 bd (DataBlock)
          ├── bd.e = cmd.f()           ← 指令序列化
          ├── bd.a = playerId
          ├── K.a(bd)                   ← BackgroundWriter 写入
          └── 每 5 条指令插入 tick 标记
```

### 2.4 回放回放流程

```
ReplayEngine.a(float f2) [line 658]:
  ├── F = 当前 InputNetStream
  ├── 读取块名: F.x()
  │
  ├── "rc" → 读取指令:
  │   ├── y++ (指令计数)
  │   ├── cmd = CommandController.b()  ← 创建空 Command
  │   ├── cmd.a(F)                    ← 从流反序列化
  │   ├── cmd.a = true                ← 标记为回放指令
  │   ├── w = cmd                     ← 缓存当前指令
  │   └── p++ (帧指令计数)
  │
  ├── "wait" → 空帧
  │
  ├── "cs" → 校验和检查
  │   ├── 比对游戏状态 hash
  │   └── 不匹配 → desync 检测
  │
  └── "gs" → GameSave 快照
      └── ca.a(F) ← GameSaver 加载
```

### 2.5 回放字段交叉验证

| ReplayEngine 字段 | 类型 | 从多个源确认的含义 |
|-------------------|------|-------------------|
| `P` | boolean | 是否正在录制/播放 |
| `u` | boolean | 是否正在播放 (vs 录制) |
| `v` | int | 回放速度倍率 (1/2/4/8/16/32/64) |
| `o` | int | 当前帧的指令ID |
| `p` | int | 当前帧指令计数 |
| `q` | int | 最后处理的帧号 |
| `y` | int | 总指令计数 |
| `w` | bd | 当前待处理的指令数据块 |
| `F` | k (InputNetStream) | 当前块读取器 |
| `K` | bb (BackgroundWriter) | 后台写入线程 |

---

## 3. 指令注入管线交叉验证

### 3.1 完整路径

```
RWAgent.createCommand()                  ← JVM Agent 端
│
├── 通过反射构造 Command 对象
├── e.l() — prepareAndCheckOnServer      ← 验证阶段
└── CommandController.b(player)          ← 提交
    │
    ▼
[游戏端]
CommandController.b(player) [line 48]:
├── cmd.l()                              ← 再次验证
├── this.b.add(cmd)                      ← 加入队列
└── ReplayEngine.a(cmd, playerId)        ← 录制
    │
    ▼
GameScreen.a(f2) → CommandController.c() [每帧]:
├── this.d() (或 this.e())
│   └── for (cmd : this.b):
│       ├── ReplayEngine.a(cmd, n2)      ← 回放引用
│       └── cmd.k()                      ← ★ 执行
│           │
│           ├── [系统动作] → 立即处理
│           ├── [stopOrUndo] → 停止单位
│           ├── [路径点] → isValidNewWaypoint → 编队分派
│           └── [特殊动作] → 建造/修理
│
└── this.b.clear()
```

### 3.2 isValidNewWaypoint 门 — 交叉验证

| 检查项 | 来源 | 失败模式 |
|--------|------|---------|
| 建造位置可建造 | 地图瓦片 j/l 标志 | exec_fail (静默) |
| 目标单位有效 | bV=false, R != null | exec_fail |
| 单位类型有效 | as != ar.Y (空类型) | exec_fail |
| 路径点动作有效 | waypointAction.k != null | exec_fail |
| 单位可控 | canNotBeGivenOrdersByPlayer | exec_fail |
| 错误日志限制 | c.e < 5 | 前5条后静默 |

**交叉引用确认**: `c.e` 计数器在 `CommandController` 中，限制错误日志为 5 条——这解释了为什么 RWAgent 观察到的 exec_fail 率从 ~9% 到 ~50% 波动但日志只显示前几次失败。

---

## 4. 玩家收入系统交叉验证

### 4.1 收入累积链

```
PlayerState.a(float f2) [line 899]:      ← 从 GameScreen.a() → PlayerState.f() 调用
├── this.an += f2                        ← 收入计时器累加
└── if (this.an > 90.0f):               ← 每 90 帧 (~1.5s) 发放一次
    ├── this.an = 0.0f
    └── this.am.a()                      ← 发放收入
```

### 4.2 收入率来源

```
TeamUnitTracker (game.s):
├── s.g = Σ cy()                         ← 所有单位收入贡献总和
│   ├── CommandCenter.cy() = 18.0f
│   ├── Extractor.cy() = 变化值
│   └── Factory.cy() = 变化值
├── s.h/i/j = 资源流修正 (5种资源类型)
└── s.k/l = 正/负资源流修正

收入倍率:
├── ay.h = 收入倍率 (默认 2.5)
└── 实际收入 = s.g × ay.h × (其他修正)
```

### 4.3 交叉验证: 收入数据流

```
单位注册 (s.a):
├── s.g += unit.cy()
├── s.d++ (totalBuilt)
└── 建造完成时触发

单位注销 (s.b):
├── s.g -= unit.cy()
├── s.d-- (totalBuilt)
└── 单位死亡/回收时触发

收入发放 (n.am.a):
├── credits += s.g × ay.h / 帧率
└── 每 ~1.5s 一次
```

---

## 5. 建造系统交叉验证

### 5.1 建造指令 → 完成

```
1. Command.k() — 执行建造指令
   ├── cmd.j (waypointAction) = BUILD 类型
   ├── cmd.j.a() = as (要建造的单位类型)
   ├── cmd.j.e() = posX
   └── cmd.j.f() = posY

2. unit.ar() — 单位接收
   ├── 单位类型 = BuilderUnit (d.j)
   └── 当前动作 = BUILD (a.c.d)

3. Factory.a(s, boolean, PointF, am) [line 499]
   ├── 检查价格 (canAfford)
   ├── 检查槽位
   └── 开始建造 (buildProgress = 0)

4. 每帧建造更新 (Factory.a(float)):
   ├── buildProgress += buildSpeed × dt
   └── if buildProgress >= 1.0:
       ├── 创建 UnitInstance
       ├── s.a(am) — 注册收入
       └── Built++

5. RWAgent 观察:
   ├── Built 计数器增加
   └── 新单位出现在 TeamUnitTracker
```

### 5.2 建造失败模式 (交叉验证)

| 失败点 | 源码位置 | RWAgent 诊断 |
|--------|---------|-------------|
| 无建造者 | i.y() = null | `no_builder:<type>` |
| 建造者忙碌 | ar() != null | `busy` |
| 建造者已指派 | aB != null | `assigned` |
| 价格不足 | canAfford = false | `cantAfford` |
| 位置无效 | isValidNewWaypoint = false | `exec_fail` |
| 建造者无此能力 | action.isAvailable = false | `incapable` |

---

## 6. 地图/单位/AI 跨系统引用

### 6.1 地图 → AI

```
MapEngine:
├── A: ArrayList<Point>                 ← 所有资源池位置
│   └── GameWorld.an() 遍历查找最近的矿点
├── u (Ground 层)                       ← AI 检查地形可建造性
├── x (PathingOverride)                 ← AI 寻路覆盖
├── M[][], N[][] (迷雾)                 ← AI 视野
└── T, U (道路/深水索引)                ← AI 连通性检查
```

### 6.2 单位 → AI

```
UnitInstance (am):
├── bX: PlayerState                     ← AI 获取所属玩家
├── bI(): boolean                       ← 实验单位 (影响建造决策)
├── dd(): boolean                       ← 建筑 (影响区域统计)
├── u(): boolean                        ← 运输单位 (影响运输组)
├── r(): UnitTypeHandle                 ← 单位类型 (影响建造选择)
└── aB: UnitGroup                       ← 已分配组 (影响组管理)
```

### 6.3 回放 → 所有系统

```
ReplayEngine.h():
├── "rc" → Command.a(F) → 执行 → 影响所有系统
├── "gs" → GameSaver.a(F) → 完整状态恢复
└── "cs" → 校验和 → 反同步检测
```

---

## 7. 发现的文档不一致及修正

### 7.1 字段名修正

| 之前文档中的名称 | 修正为 | 原因 |
|-----------------|--------|------|
| `posX`/`posY` 在 am 上 | `eo`/`ep` 在父类 GameObject(w) 上 | 字段声明在父类，不在 UnitInstance |
| UnitGroup.g 中的 `g` 字段 | `g` = `c` (重新评估计时器) | 字段命名冲突澄清 |
| `ProjectileManager` | **FormationManager** (编队管理器) | 不管理弹丸，管理单位编队 |
| `game.a.i` (Projectile) | **AIStrategyNode** 子类 | 不是弹丸，是AI策略节点 |

### 7.2 方法名修正

| 官方混淆方法 | 之前推断 | 修正为 | 来源 |
|-------------|---------|--------|------|
| `am.bI()` | isExperimental | 确认 | d/d.java 中返回 true |
| `am.dd()` | isBuilding | 确认 | e/c.java 中返回 true |
| `am.u()` | isTransport | 确认 | 运输检查 |
| `am.ar()` | getCurrentWaypoint | 确认 | 返回 au 对象 |
| `e.l()` | prepareAndCheckOnServer | 确认 | Command.java line 639 |
| `e.k()` | execute | 确认 | Command.java line 413 |

### 7.3 常量修正

| 之前值 | 修正为 | 来源 |
|--------|--------|------|
| 收入帧间隔 ~1.5s | 90 帧 (1.5s at 60fps) | PlayerState.a(): `an > 90.0f` |
| 迷雾更新 ~4.3s | 260 帧 (~4.33s) | MapEngine.f(): `ar > 260.0f` |
| 回放 gameVersion | 176 | ReplayEngine line 465: `l2.c(true)` |

---

## 8. 补全的字段映射

### 8.1 ReplayEngine 字段

| 混淆字段 | 含义 | 类型 | 行号 |
|----------|------|------|------|
| `a` | replayDir ("replays/") | String | 35 |
| `g` | packetBuffer | ak | 41 |
| `h` | isActive | boolean | 42 |
| `P` | isRecordingOrPlaying | boolean | 54 |
| `t` | replayFilename | String | 55 |
| `u` | isPlaying | boolean | 56 |
| `v` | playbackSpeed | int | 57 |
| `w` | currentDataBlock | bd | 58 |
| `x` | lastDataBlock | bd | 59 |
| `y` | totalCommandCount | int | 60 |
| `z` | currentFrameCommands | int | 61 |
| `o` | lastCommandTick | int | 49 |
| `p` | commandsThisFrame | int | 50 |
| `q` | lastProcessedFrame | int | 51 |
| `F` | blockReader (InputNetStream) | k | 67 |
| `K` | backgroundWriter | bb | 72 |
| `L` | writerThread | Thread | 73 |

### 8.2 GameScreen 字段 (部分)

| 混淆字段 | 含义 | 来源 |
|----------|------|------|
| `cf` | CommandController | GlobalState.cf |
| `cb` | ReplayEngine | GlobalState.cb |
| `bX` | NetEngine | GlobalState.bX |
| `bU` | PathEngine/Recycler | GlobalState.bU |
| `bL` | MapEngine | GlobalState.bL |
| `ca` | GameSaver | GlobalState.ca |
| `bR` | EffectManager | GlobalState |
| `bQ` | SettingsEngine | GlobalState.bQ |

---

## 9. RWAgent 注入优化建议

基于交叉验证的关键发现:

1. **CommandController 在 GameWorld 之前执行** — 注入的指令在 AI 逻辑之前生效，AI 可以看到本帧的建造结果

2. **isValidNewWaypoint 门限**:
   - 建造位置必须通过 MapEngine 瓦片阻挡检查 (tile.j/l)
   - 资源池位置应在 MapEngine.A 中查找

3. **收入时序**:
   - 每 90 帧发放一次 (PlayerState.an > 90.0f)
   - 新建造完成的单位在下一个 90 帧周期才开始贡献收入

4. **迷雾更新**:
   - 每 260 帧更新一次 (MapEngine.ar > 260.0f)
   - RWAgent 注入的指令不要依赖迷雾状态实时变化

5. **回放兼容性**:
   - 指令录制在 CommandController.b() 中，非 k() 中
   - 注入的指令会被录制到回放 (如果录制中)
   - 回放时需要完整的 Command 序列化

6. **建造失败排查**:
   - 先检查 `c.e < 5` 限制 (只有前5次 isValidNewWaypoint 失败有日志)
   - 通过反射读取 `c.e` 计数器判断是否被限流
   - 在 AI 诊断中输出 `c.e` 值
