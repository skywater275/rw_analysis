# 05 — 玩家/队伍系统

> 逆向度: 98% | 验证: n.java 字节码 + RW-HPS PrivateClassLinkPlayer

## 玩家槽位 — `n.java`

```
静态数组: n.as[n.f] = n.as[c+d] = n.as[10]   (10个槽位)
获取玩家: n.k(int index) → n.as[index]
扩展槽位: n.b(count, bool)
初始化:   n.F()

最大玩家: n.c = 10 (默认)
```

## 玩家字段

| 字段 | 类型 | 含义 | 服务器映射 |
|------|------|------|-----------|
| `k` | int | 槽位索引 (0..9) | PrivateClassLinkPlayer.index |
| `r` | int | 队伍ID (0=A..9=J, -3=Spec) | playerData.r |
| `v` | String | 玩家名称 | playerData.v |
| `o` | double | 当前资金 (初始4000) | playerData.o |
| `O` | String | 连接Hex ID | connectHexID |
| `A` | int | 起始单位 | startUnit |
| `C` | int | 玩家颜色 | color |
| `T` | s | 队伍追踪器 | — |

## 存活判定

```
存活: !b() && !G && !F && !E
  b() — 已死亡/淘汰
  G   — 已投降
  F   — 已断开连接
  E   — 已战败
```

## 队伍分配

```
初始: n.c(index, bool) → k=index, r=index
服务器: player.team = lobby_team → 覆盖 r
观战: r = -3 (0xFFFFFFFD)
断开: r = -2 (0xFFFFFFFE)

队伍ID: 0=A, 1=B, 2=C, ..., 9=J
显示颜色: n.h(index) = index % 2 → 交替A/B
```

## 快照中的玩家

```
快照格式: [4B: 4000(资金)] [4B: 队伍ID] [1B: 0x01] [2B: 名字长度] [CESU-8名字]

快照只包含有名字的玩家 (player.v != null)
空槽位和AI不包含在快照中

解析器按发现顺序分配索引 (0,1,2...)
→ 可能与游戏真实索引不匹配
→ 已通过 _resolve_unknown_players() 检测P-格式占位符
```

## AI 分配 (服务器特有)

```
时序:
  1. 游戏引擎创建N个空槽位: n.b(maxPlayer, true)
  2. 人类玩家分配队伍: playerData.r = lobby_team
  3. 快照写入 (仅人类玩家)
  4. 游戏开始 → PlayerManage.addAI()
     → 遍历0..maxPlayer
     → 首个 existPlayer(pos)=true 且 getPlayer(pos)=null 的槽位
     → 创建 AiPlayer, 继承该槽位的 n.r (队伍)
  5. AI名称: "AI-{难度}", connectHexID: 随机

AI不在快照中 → 回放显示为 P9 (占位符)
当前处理: _resolve_unknown_players() → team=AI
```

## 游戏结束判定

```
GameHessData.getWin(position):
  player = n.k(position)
  return !player.b() && !player.G && !player.F && !player.E

胜利条件: 仅剩一个队伍的玩家存活
```

## 游戏启动流程 (来自 GAME_RUNTIME_LOGIC)

```
引擎初始化 i.a(hasLocalPlayer, replayMode, gameMode):
  1. 设置队伍上限: bC = SettingsEngine.teamUnitCap
  2. 注册内置单位类型: ar 枚举 (8 tier)
  3. 初始化子系统:
     n.X()           — 清空队伍注册表
     by = 0          — 游戏时间归零
     bx = 0          — 帧计数器归零
     bX.a(10)        — 网络输出初始化
     bX.t()          — 网络连接
  4. 加载自定义单位:
     if !replay: ag.b(true)  — 加载 .ini 定义
     else:       ag.d()      — 回放模式用缓存
  5. 创建/分配玩家队伍:
     if hasLocalPlayer:
       bs = new e(0)          — 创建本地玩家
       for i in 1..8: new a.a(i) — 8个AI
  6. 加载地图: MapGenerator.init(mapName)
  7. 初始化统计: bY = new bg()
  8. 初始化回放: ba.a() 或 ba.initReplay()
```

## 服务器玩家管理

```
PlayerManage:
  playerGroup  — 在线玩家
  playerAll    — 全部玩家(含AI)
  
  addAI()          — 填充空槽位
  getPlayer(idx)   — 按索引查找
  addAbstractPlayer(con, data) — 添加人类玩家
```
