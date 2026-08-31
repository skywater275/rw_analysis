# Rusted Warfare v1.15 — 对局生命周期源码逆向
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 完整追踪每场对局: 大厅→加载→运行→结束→返回大厅
>
> 关键文件: `NetEngine.java`(5359行), `PlayerState.java`(1883行), `GameEngine.java`(2204行), `GameThread.java`(65行)

---

## 1. 对局状态机

### 1.1 GameStateType (s 枚举, 3值)

| 值 | 含义 |
|----|------|
| `a` | MENU/LOBBY — 大厅等待 |
| `b` | LOADING — 加载地图/单位 |
| `c` | PLAYING — 对局进行中 |

### 1.2 关键全局标志 (GlobalState / NetEngine)

| 字段 | 类 | 含义 |
|------|-----|------|
| `bl` | GlobalState | isInMatch — 对局运行中 |
| `bx` | GlobalState | gameTick — 游戏 tick 计数 (每帧+1) |
| `by` | GlobalState | currentFrame — 累积帧时间 (ms, `+= dt×16.667`) |
| `aW` | NetEngine | gameHasBeenStarted — 游戏已开始 |
| `aY` | NetEngine | returnToBattleroomFlag — 返回大厅中 |
| `aZ` | NetEngine | returnTimerActive — 返回倒计时中 |

---

## 2. 阶段1: 大厅 (Lobby)

### 2.1 启动服务器

**单机模式** (NetEngine.S(), line 3084):
```
1. B=true, C=true (网络运行中)
2. F=true (单机模式)
3. ay.a = 地图类型, ay.b = 地图名
4. aa() 生成 UUID
5. z = l2.bs (当前玩家 = 服务器玩家)
```

**多人主机** (NetEngine.b(boolean), line 3107):
```
1. q() 清理重置
2. B=true, C=true
3. aa() 生成服务器 UUID
4. 启动 TCP/UDP 监听线程
5. c(bl) 本地玩家注册为服务器玩家
```

**客户端连接** (NetEngine.a(Socket), line 3426):
```
1. B=true, C=false (客户端模式)
2. 创建连接对象 (c)
3. 发送 PREREGISTER_INFO (包类型 161)
```

### 2.2 玩家注册 (REGISTER_CONNECTION, 包类型 110)

```
服务器收到包 110 (line 2229-2423):
├── 验证: 版本/ban/checksum/密码/房间锁
├── 重连玩家? → 恢复已有 PlayerState
├── 新玩家? → 创建 PlayerState (game.e 子类)
│   ├── 分配空槽位 (G() 或 H())
│   └── teamId = playerSlot (默认一人一队)
├── 设置名称/连接ID
└── 通知所有客户端更新玩家列表
```

### 2.3 队伍分配

**PlayerState 子类型**:
| 类 | 含义 |
|----|------|
| `game.e` | 人类玩家 |
| `game.c` | 网络玩家 |
| `game.a.a` | AI 玩家 |
| `game.d` | 特殊状态 (空白/断开) |

**队伍布局** (NetEngine.b(am), line 4533):
```
am.a → random_split (2队)
am.b → random_split (3队)
am.c → free_for_all (各自为战)
am.d → all_spectators (全观战)
```

**队伍管理命令**:
- `-teamlock on/off` — 锁定队伍
- `-self_move <slot> [team]` — 移动自己
- `-team <slot> <team>` — 设置玩家队伍

---

## 3. 阶段2: 游戏启动

### 3.1 启动序列

**主机触发** (NetEngine.a(c, boolean), line 4022):
```
sendStartGame:
1. 创建包 120 (PACKET_START_GAME)
2. 嵌入地图数据:
   ├── ai.a (遭遇战) → l2.ca.a(ay.b, as2)  保存地图
   ├── ai.b (自定义)  → b.a(az, as2)        加载自定义地图
   └── ai.c (存档)    → 已序列化
3. 发送到所有客户端
4. 本地调用 aB()
```

**客户端接收** (line 2516):
```
processSystemPacket case 120:
1. 读取地图类型
2. 读取地图数据 → aA/aB
3. 读取地图名
4. 调用 aB() → 触发本地游戏启动
```

### 3.2 aB() — 实际启动 (line 4084)

```
aB():
1. aY = false  (取消待返回)
2. aW = true   (gameHasBeenStarted)
3. n.p()        → Android UI 启动加载 Activity
4. d.b()        → 更新 UI
```

### 3.3 地图加载 (appFramework/n.java, r() 方法)

```
switch (gameType):
├── ai.c (存档):
│   ├── 主机: l2.ca.c(ay.b, true)        从文件加载存档
│   └── 客户端: l2.ca.a(aA, true, ...)   从网络加载存档
├── ai.b (自定义): l2.a(true, s.b)       加载自定义地图
└── ai.a (遭遇战):
    ├── l2.dl = ay.b                     地图名
    └── l2.a(true, s.b)                  加载遭遇战地图
```

### 3.4 对局初始化 (GameEngine.a(boolean, boolean, s))

```
GameEngine.a():
1. F()            重置玩家数组 (10个槽位清null)
2. 创建玩家:
   ├── 单机: new e(0) "Player" + AI 玩家 1-7
   └── 多人: bs = bX.z (网络配置的本地玩家)
3. Z()            重置每个玩家状态
4. e()            重建活跃玩家列表
5. 加载地图 / 初始化迷雾 / 放置起始单位
6. bx = 0         gameTick 归零
7. by = 0         currentFrame 归零
8. bX.a(1L)       初始化 RNG 种子
```

---

## 4. 阶段3: 对局运行

### 4.1 游戏主循环

```
GameThread.run():                       [每帧]
├── f2 = delta × 0.06f                  ← 归一化到 60fps
└── GameEngine.a(f2, n2)
    │
    └── GameEngine.b(f2, n2)            ← 主帧方法
        │
        ├── NetEngine.b(f2)             ← 网络接收
        │
        └── GameEngine.a(f2)            ← ★ 核心更新 (18步)
            │
            ├──  1. NetEngine.c(f2)     ← 网络状态
            ├──  2. CommandController.c() ← 指令执行
            ├──  3. ReplayEngine.a(f2)  ← 回放更新
            ├──  4. PlayerState.g(f2)   ← 帧前清除
            ├──  5. for-each obj: w.a(f2) ← 游戏对象更新
            ├──  6. SpatialGrid.a()     ← 空间索引
            ├──  7. Projectile update
            ├──  8. CustomUnitType update
            ├──  9. PlayerState.f(f2)   ← ★ 收入/战败检查
            ├── 10. ParticleEffects
            ├── 11. EffectManager
            ├── 12. GroupController
            ├── 13. MinimapHandler
            └── 14. PathEngine.b(f2)
```

### 4.2 收入发放 (PlayerState.a(float), line 899)

```
每帧累加 an (收入计时器)
if (an > 90.0f):                       ← 每 90 帧 (~1.5s)
    an = 0.0f
    am.a()                               ← 发放收入
    实际收入 = s.g × ay.h × 帧率修正
```

### 4.3 AI 玩家更新

```
PlayerState.h(f2):                      [line 1632]
├── 遍历所有 AI 玩家
└── 每个 AI 的 GameWorld.a(f2) 被调用 (通过对象更新循环)
```

### 4.4 战败检查: PlayerState.e(float) (line 1440)

每 ~10 帧执行一次 (由 `ai` 计数器控制):

```
e(float f2):
├── if (已投降 或 回放中) return
│
├── 遍历所有我方单位:
│   ├── 非尸体 (cT()==false)? → bl2=true (有单位)
│   ├── 有战斗力? → bl3=true (有战斗单位)
│   └── 纯尸体? → bl6=true
│
├── 遍历所有友军单位 (共享控制):
│   └── 非尸体 → bl4=true
│
├── 情况1: 无己方单位且无友军单位
│   └── hasSurrendered = true
│       杀死所有剩余单位
│       NetEngine.i(this) → 宣布"被消灭"
│
└── 情况2: 无战斗单位
    └── hasDisconnected = true
        NetEngine.h(this) → 宣布"战败"
```

### 4.5 胜利判定: PlayerState.Q() (line 1622)

```
Q():
├── 遍历所有非观战、未战败玩家
└── 对每个: NetEngine.g(n2) → 宣布"胜利"
```

---

## 5. 阶段4: 对局结束

### 5.1 投降流程

**发起投票** (`-surrender` 命令, NetEngine line 5222):
```
if (游戏已开始 && 未投票):
    au = 当前时间戳 (投降投票时间)
    检查 m() 是否可以投降
    广播: "[player]: Is voting to surrender"
    广播: "[Votes to surrender X/Y]"
```

**投票计数** (PlayerState.b(int teamId), line 569):
```
统计同队中: au >= 0 且 m()==true 的玩家数
```

**120秒过期** (PlayerState.e(int teamId), line 626):
```
if (最后投票时间 > 120s):
    重置所有投票
```

**队伍投降执行** (PlayerState.d(int teamId), line 606):
```
if (队伍未标记 defeat && 游戏运行中):
    at(team) = true
    添加作弊命令消灭全队
```

### 5.2 断开连接

```
服务器每 tick (NetEngine.O()):
├── 检测超时玩家 (无 ping > 60s)
│   └── 标记 afk, 检查是否共享控制
├── afk > 180s:
│   └── 扩大阈值到 160s
└── 超时 → PlayerState.hasDisconnected = true
```

### 5.3 主机强制结束 (`-endgame`, NetEngine line 4989)

```
只有主机可执行:
├── d(5.0f)                              ← 设返回倒计时
├── ba = 5.0f
├── aZ = true
└── 消息: "Game ended by host. Returning to battleroom in 5 seconds"
```

---

## 6. 阶段5: 返回大厅

### 6.1 返回序列

```
NetEngine.d(float) → 每帧:
├── if (aZ):
│   ├── ba -= dt/60
│   └── if (ba <= 0):
│       ├── aZ = false
│       └── i(null)  → 发送 PACKET_RETURN_TO_BATTLEROOM (包122)
│
└── 客户端接收包122 → aC():
    └── aY = true → aD()
```

### 6.2 aD() — 实际返回清理 (line 4155)

```
aD():
1. "----- returnToBattleroom -----"
2. aY = false
3. l2.cb.e()          ← 清除游戏世界 (移除所有单位/重置状态)
4. n2 = this.z        ← 保存当前玩家引用
5. l2.g()             ← 重置游戏引擎状态
6. s()                ← 重置 NetEngine 字段
7. this.z = n2        ← 恢复玩家引用
8. l2.bx = 0          ← gameTick 归零
9. l2.by = 0          ← currentFrame 归零
10. A()               ← 重置客户端就绪标志
11. PlayerState.n()   ← 重置所有玩家状态
12. J()               ← 刷新大厅 UI
13. aA()              ← 生成新房间码
```

### 6.3 玩家状态重置: PlayerState.Z() (line 1840)

```
Z():
├── hasLost = false
├── hasDisconnected = false
├── hasSurrendered = false
├── H = false (victory)
├── credits = 4000.0
├── creditBuffer = 0.0
├── teamTracker = new s()     ← 新的空单位追踪器
├── S = true                  ← 需要缓存更新
└── 重置所有投票/时间戳
```

---

## 7. 玩家/队伍系统

### 7.1 槽位系统

| 槽位 | 含义 |
|------|------|
| 0-9 | 正常玩家槽 (c = maxPlayers = 10) |
| -1 | 特殊"所有人" (i, teamId=-1) |
| -2 | 特殊"中立/空白" (h, teamId=-2) |
| -3 | 观战者 (teamId=-3 → b()=true) |
| c~f-1 | 额外观战槽位 |

### 7.2 队伍判断

```java
// 同一队伍?
d(PlayerState other):
    return this.teamId == other.teamId

// 观战者?
b():
    return this.teamId == -3
```

### 7.3 玩家数组生命周期

```
F()          — 清空所有槽位 (as[] = null)
b(int, bool) — 扩容 (最小10, 最大100)
G()          — 找第一个空闲槽 0~c-1
H()          — 优先观战槽 c~f-1, 回退正常槽
I()          — 从数组中移除自己
c(int, bool) — 分配到指定槽位 (teamId 默认 = slot)
Z()          — 对局间重置状态 (不解散槽位分配)
```

---

## 8. 完整状态转换图

```
┌─────────────────────────────────────────────────────────┐
│                     LOBBY (s.a)                         │
│  服务器启动 → 玩家注册(包110) → 队伍分配 → 就绪检查     │
└──────────────────────┬──────────────────────────────────┘
                       │ 主机发送 PACKET_START_GAME (120)
                       ▼
┌─────────────────────────────────────────────────────────┐
│                   LOADING (s.b)                         │
│  加载地图 → 初始化玩家数组 → 放置起始单位 → 初始化迷雾   │
└──────────────────────┬──────────────────────────────────┘
                       │ aW=true, bx=0, by=0
                       ▼
┌─────────────────────────────────────────────────────────┐
│                   PLAYING (s.c)                         │
│  ┌─────────────────────────────────────────────────┐   │
│  │ 每帧循环 (GameThread.run):                       │   │
│  │   NetEngine 网络同步                              │   │
│  │   CommandController 指令执行                      │   │
│  │   游戏对象更新 (包括 AI)                          │   │
│  │   收入发放 (每90帧)                               │   │
│  │   战败检查 (每~10帧)                              │   │
│  │   迷雾更新 (每260帧)                              │   │
│  └─────────────────────────────────────────────────┘   │
│                                                         │
│  结束触发:                                              │
│  ├── 全队被消灭 → 胜利判定 → 宣布胜利                   │
│  ├── 投降投票 → 全票通过 → 队伍消灭                     │
│  ├── 断开超时 → 共享控制 or 战败                        │
│  └── -endgame → 返回倒计时                              │
└──────────────────────┬──────────────────────────────────┘
                       │ PACKET_RETURN_TO_BATTLEROOM (122)
                       ▼
┌─────────────────────────────────────────────────────────┐
│                  RETURNING                              │
│  aD(): 清除游戏世界 → 重置引擎 → 重置玩家 → 刷新大厅    │
└──────────────────────┬──────────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────────┐
│                   LOBBY (s.a)                           │
│  所有玩家重置 (Z()), 准备下一局                          │
└─────────────────────────────────────────────────────────┘
```

---

## 9. 关键常量

| 常量 | 值 | 来源 |
|------|-----|------|
| 最大玩家数 | 10 | PlayerState.c |
| 最大扩容 | 100 | PlayerState.e |
| 默认起始资金 | 4000.0 | PlayerState.o |
| 收入发放间隔 | 90帧 (~1.5s) | PlayerState.an > 90.0f |
| 战败检查间隔 | ~10帧 | PlayerState.ai |
| 迷雾更新间隔 | 260帧 (~4.33s) | MapEngine.ar > 260.0f |
| 投降投票过期 | 120秒 | PlayerState.e() |
| AFK 阈值 | 60秒 | NetEngine.O() |
| AFK 扩大 | 180秒 | NetEngine.O() |
| 返回倒计时 | 5秒 | NetEngine.d(5.0f) |
| UUID 长度 | 36字符 | NetEngine.aa() |

---

## 10. 对 RWAgent 的意义

1. **注入时机**: CommandController 在对象更新(含 GameWorld)之前执行，指令注入应在每帧早期
2. **收入同步**: 收入每90帧发放，建造完成的单位在下个90帧周期才贡献收入
3. **战败检测**: PlayerState.e() 每~10帧检查，AI 玩家通过 GameWorld.a() 更新
4. **状态重置**: 对局结束时 Z() 重置 credits=4000，所有单位被清除
5. **槽位管理**: 通过 playerSlot 和 teamId 确定身份，反射访问时注意 playerSlot 可能为 -1
6. **对局检测**: `GlobalState.bl` (isInMatch) 判断是否在对局中
7. **帧计数器**: `bx`(gameTick) 每帧递增，可用于指令时间戳
