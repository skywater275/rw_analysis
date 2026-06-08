# 17 — RW-HPS 服务器架构

> 来源: RW-HPS (Kotlin/Java, AGPLv3, ~500文件) + game-lib.jar (1698类)
> 验证: 与游戏引擎字节码交叉验证

---

## 一、项目概览

RW-HPS 是 Rusted Warfare 的开源服务端实现，使用 Kotlin 编写，通过 ASM 字节码修改让游戏引擎以无头模式运行在服务器上。

### 模块结构

```
RW-HPS/
├── Server-Core/      — 核心服务器 (Kotlin)
├── Server-All/       — 打包模块 (fat JAR)
├── ASM-Framework/    — 字节码修改框架
├── TimeTaskQuartz/   — 定时任务 (Quartz调度)
├── Java-Doc/         — Dokka生成的API文档
├── UPList/           — 更新列表
├── buildSrc/         — Gradle构建脚本 (版本管理)
├── docs/             — 用户文档
└── data/             — 默认配置数据
```

### 技术栈

```
语言:       Kotlin (JVM 11 target), Java
构建:       Gradle (Kotlin DSL)
字节码:     ASM Framework
序列化:     Gson (JSON)
网络:       TCP/UDP (原始Socket)
日志:       自实现Log系统
插件:       JavaScript (Rhino/Nashorn) + Kotlin
定时任务:    Quartz Scheduler
文档:       Dokka (Kotlin-as-Java)
```

---

## 二、无头模式实现

### 2.1 字节码重定向架构

```
游戏引擎 (game-lib.jar)
  ┌─ 渲染方法 (OpenGL/Slick2D)     → 替换为空操作
  ├─ 文件系统 (Android Context)     → 重定向到服务器文件系统
  ├─ 帧率控制 (Thread.sleep)        → 移除FPS限制
  ├─ 网络层 (NetEngine)             → 保留+增强
  └─ UI (LibRocket/GameScreen)      → 跳过UI渲染

ASM-Framework/
  ├─ ClassVisitor       — 遍历游戏类
  ├─ MethodVisitor      — 替换方法体
  └─ FieldVisitor       — 修改字段访问
```

### 2.2 重定向类别

#### 游戏重定向 (game/)
```
FileLoaderRedirections.kt  — 文件加载重定向
  → 将 Android AssetManager 替换为服务器文件系统
  → mods/, maps/, saves/ 路径映射

GameMainRedirections.kt    — 主循环重定向
  → 移除 AppGameContainer 依赖
  → 替换游戏启动流程

NetPacketRedirections.kt   — 网络包处理重定向
  → 拦截 NetEngine 的包收发
  → 注入自定义包处理逻辑

FPSSleepRedirections.kt    — 帧率控制重定向
  → 移除 Thread.sleep FPS限制
  → 服务器全速运行

CleanRedirections.kt       — 渲染清理
  → 移除所有渲染调用 (OpenGL/Camera/SpriteBatch)

CustomRedirections.kt      — 自定义扩展点
```

#### LWJGL重定向 (lwjgl/)
```
LwjglRedirections.kt       — OpenGL绑定替换
  → 所有GL调用替换为空操作
  → 移除Display/PBO/纹理管理

LwjglClassProperties.kt    — LWJGL类属性
MemASCIIRedirection.kt     — 内存/ASCII重定向
STBIImageRedirection.kt    — 图像加载重定向
```

#### Slick2D重定向 (slick/)
```
SlickRedirections.kt       — Slick2D渲染替换
DrGraphics.kt              — 无头图形上下文
SilckClassPathProperties.kt — 类路径属性
ZipFileSystemLocation.kt   — ZIP文件系统位置
```

---

## 三、网络架构

### 3.1 连接管理

```
NetServer (Kotlin)
├── TCP Acceptor Thread
│   └── for each connection:
│       ├── PlayerConnect (j.c)
│       ├── Packet receive loop
│       └── GameHess protocol handler
│
├── UDP Channel (optional)
│   └── Relay Protocol
│
└── NetService
    ├── RelayProtocol       — 游戏直连
    ├── RelayMulticastProtocol — 多播中继
    ├── ServerProtocol      — 游戏服务器
    └── ServerTestProtocol  — 测试服务器
```

### 3.2 游戏房间管理

```
HeadlessModuleManage
└── hessLoaderMap: Map<RoomID, GameHess>
    └── GameHess (每个游戏房间)
        ├── room: GameRoom
        │   ├── maps: GameMaps
        │   ├── playerManage: PlayerManage
        │   │   ├── playerGroup — 在线人类玩家
        │   │   ├── playerAll   — 全部玩家(含AI)
        │   │   └── addAI()     — AI填充空槽位
        │   └── roomID: String
        └── useClassLoader: ClassLoader
```

### 3.3 玩家管理

```kotlin
PlayerManage:
  playerGroup  — 在线玩家列表
  playerAll    — 全部玩家(含AI填充)
  
  addAbstractPlayer(con, data)   — 添加人类玩家
    → 分配槽位 (0..maxPlayer-1)
    → 设置队伍 (n.r = lobby_team)
    → 注册到NetEngine (j.ad)
  
  addAI()                        — AI填充
    → 游戏开始后自动调用
    → 遍历0..maxPlayer
    → 填入首个空槽位
    → AI名称: "AI-{难度}"
    → 继承该槽位的队伍(n.r)
  
  getPlayer(idx)                 — 按索引查找
  removePlayer(idx)              — 移除玩家
  getWin(position)               — 检查胜利条件
```

---

## 四、游戏命令系统

### 4.1 服务器管理命令

```
/help               — 显示帮助
/version            — 显示版本信息
/stop               — 关闭服务器
/exit               — 退出程序

# 游戏设置命令 (通过GameEngine.netEngine.ay设置):
/start <map> <mode> — 开始游戏
/credits <n>        — 起始资金 (ay.c)
/fog <0|1|2>        — 战争迷雾 (ay.d)
/income <n>         — 收入倍率 (ay.h)
/ai <difficulty>    — AI难度 (ay.f)
/startingunits <n>  — 起始单位 (ay.g)
/nukes <on|off>     — 核弹启用 (ay.i)
/shared <on|off>    — 共享控制 (ay.l)

# 游戏中命令:
/kick <player>      — 踢出玩家
/ban <player>       — 封禁玩家
/vote kick <player> — 发起投票踢人
/vote start         — 发起投票开始
```

### 4.2 事件系统

```
EventGlobal (单例)
└── EventGlobalManage
    └── listeners: List<Event>

游戏事件:
  ServerLoadEvent            — 服务器加载
  ServerStartTypeEvent       — 服务器启动类型
  NetConnectNewEvent         — 新连接
  ServerConsolePrintEvent    — 控制台输出

玩家事件:
  PlayerJoinEvent            — 玩家加入
  PlayerLeaveEvent           — 玩家离开
  PlayerChatEvent            — 玩家聊天
  PlayerBanEvent             — 封禁玩家
  PlayerUnBanEvent           — 解封玩家

游戏事件:
  ServerGameStartEvent       — 游戏开始
  ServerGameOverEvent        — 游戏结束
  PlayerOperationFactoryBuildUnitEvent — 工厂建造
```

---

## 五、服务器配置

### 5.1 核心配置 (BeanServerConfig)

```kotlin
// rwhps.config.server (JSON)
{
  serverID: "abc12",           // 服务器ID (5位随机)
  passwd: "",                  // 服务器密码
  maxPlayer: 10,               // 最大玩家数
  oneAdmin: true,              // 单人管理员模式
  startMinPlayerSize: -1,      // 最少开始人数 (-1禁用)
  autoStartMinPlayerSize: 4,   // 自动开始最小人数
  maxGameIngTime: 7200,        // 最大游戏时间(秒, 2小时)
  maxOnlyAIGameIngTime: 3600,  // 纯AI最大游戏时间(1小时)
  enableAI: false,             // AI启用
  maxMessageLen: 40,           // 最大消息长度
  maxUnit: 200,                // 最大单位数(服务端限制)
  defIncome: 1.0,              // 默认收入倍率
  isAfk: true,                 // AFK检测
  muteAll: false,              // 全员禁言
  saveRePlayFile: true,        // 是否保存回放
  modsLoadErrorPrint: false,   // Mod加载错误输出
  turnStoneIntoGold: false     // 点石成金(调试)
}
```

### 5.2 地图管理 (GameMaps)

```
GameMaps:
  mapName: String          — 地图文件名
  mapPath: String          — 地图路径
  maxPlayer: Int           — 地图支持的最大玩家
  startingPoints: List     — 出生点坐标
  resourcePools: List      — 资源点坐标
  iniSections: Map         — 地图.ini配置段
```

---

## 六、UnitUntil.java 字段映射 — rwTool版本

此文件直接引用游戏引擎的混淆类，提供了**另一版本**的字段名映射。对比两个版本的字段名变化有助于追踪游戏更新。

### 类名映射

| rwTool类名 | v1.15引擎类名 | 实际含义 |
|-----------|-------------|---------|
| `ce` | `am` | UnitInstance (单位实例) |
| `p` | `n` | GameState/Player (玩家) |
| `cj` | `as` | UnitType (单位类型) |
| `bp` | — | Attachment (附着/父单位类型) |
| `aj` | `l`? | GameFramework类 |
| `ah` | `fw.w`? | 游戏对象基类 |
| `ec` | — | 另一单位类型 |

### 字段映射 (ce ← → am)

| rwTool | v1.15 | 含义 | 类型 |
|--------|-------|------|------|
| `cw` | `cu` | 当前HP | float |
| `cx` | `cv` | 最大HP | float |
| `cz` | `cx` | 当前护盾 | float |
| `cC` | — | 最大护盾 | float |
| `cD` | `cB` | 当前能量 | float |
| `cG` | `cE` | 弹药计数 | int |
| `ej` | — | 单位ID | long |
| `eq` | `eo` | X坐标 | float |
| `er` | `ep` | Y坐标 | float |
| `es` | `eh` | 高度 | float |
| `co` | `cm` | 建造进度 | float |
| `ci` | `cg` | 朝向角度 | float |
| `ce` | — | X速度 | float |
| `cf` | — | Y速度 | float |
| `cQ` | `cN` | 父单位引用 | ref |
| `bZ` | `bX` | 所属玩家 | ref |

### 字段映射 (p ← → n, Player)

| rwTool | v1.15 | 含义 |
|--------|-------|------|
| `l` | `r` | 队伍ID |
| `w` | `v` | 玩家名称 |
| `s` | — | 备用队伍 |
| `x` | `w` | AI标志 |
| `c` | `c` | 最大玩家数 |

---

## 七、服务器与引擎的继承关系

```
RW-HPS Server
  │
  ├─ 游戏引擎层 (game-lib.jar, 字节码修改后运行)
  │   ├─ l.B()                    — GlobalState 全局单例
  │   ├─ l.bX → j.ad              — NetEngine 网络引擎
  │   ├─ l.bL → game.b.b          — MapEngine 地图引擎
  │   ├─ l.bS → UnitFactory        — 单位工厂
  │   ├─ l.bY → bg                 — 统计管理器 (StatsManager)
  │   └─ l.ca → GameSaver          — 存档管理
  │
  ├─ 服务器控制层 (Kotlin)
  │   ├─ HeadlessModuleManage      — 房间管理
  │   ├─ PlayerManage              — 玩家管理
  │   ├─ GameMaps                  — 地图管理
  │   └─ CommandHandler            — 命令系统
  │
  ├─ 网络层
  │   ├─ NetServer (TCP/UDP)
  │   ├─ GameHess (Hess协议)
  │   └─ Relay (中继协议)
  │
  └─ 扩展层
      ├─ PluginCenter (JS/Kotlin插件)
      ├─ EventSystem (事件驱动)
      └─ QuartzTasks (定时任务)
```

---

## 八、关键服务器/引擎交互

### 8.1 游戏启动流程

```
1. 客户端连接 → NetServer.accept()
2. 玩家加入 → PlayerManage.addAbstractPlayer()
3. 大厅阶段:
   - 选择地图 → GameMaps.load()
   - 选择队伍 → playerData.r = lobby_team
   - 设置选项 → ay.c/d/f/g/h/i/l
4. 开始游戏触发:
   - 所有人类玩家已加入
   - autoStartMinPlayerSize 满足
   - 或 /start 命令
5. 游戏初始化:
   - i.a(hasLocalPlayer, replayMode, gameMode)
   - 注册单位类型 (ar枚举)
   - 加载自定义单位 (ag.h)
   - 创建玩家队伍
   - 加载地图 (MapGenerator.init)
   - 初始化统计 (bg)
   - 初始化回放 (ba.a)
6. PlayerManage.addAI() — 填充空槽位
7. 主循环启动 — GameThread.run()
```

### 8.2 收入修改流程

```
/income 2.0 命令
  → CommandHandler → ay.h = 2.0f
  → GameEngine.netEngine.ay.h
  → 所有玩家收入公式: s.g × 1.5 × 2.0 × gameSpeed
```

### 8.3 单位数量限制

```
服务器层:
  BeanServerConfig.maxUnit = 200  (服务端限制)

引擎层:
  j.ad.ax = maxUnit               (网络同步)
  s.a = maxUnit                   (队伍追踪器限制)
  
检查:
  s.a(am)注册时检查: if s.b >= s.a → 拒绝注册
  (b = 非建筑单位数)
```

---

*参考: RW-HPS Server-Core (~400 Kotlin文件) + ASM-Framework + 配置系统*
*最后更新: 2026-06-07*
