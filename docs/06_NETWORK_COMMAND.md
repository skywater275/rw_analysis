# 06 — 网络协议 / 指令系统

> 逆向度: 100% | 验证: j.ad 字节码 + RW-HPS LinkGameNet + 30回放

## 网络引擎 — `j.ad` (NetEngine, 104KB)

```
GameEngine.netEngine = gameEngine.bX

关键字段:
  X (int)      — 网络tick计数器
  C (boolean)  — 游戏已开始
  m (int)      — 服务器端口
  y (String)   — 主机名
  s (boolean)  — 永续模式
  o (boolean)  — Mod启用
  z (n)        — 隐藏队伍(同步占位)
  ax, aw (int) — 最大单位数
  az (String)  — 地图路径
  aM (List)    — 连接列表
  ay           — 游戏设置子对象

关键方法:
  a(false,false,true) — 同步所有玩家
  a(target, site)     — 分配玩家到槽位
  d(float)            — 战斗房间倒计时
  e(bool)             — 暂停/恢复
  L()                 — 刷新/加载地图
```

## 游戏设置 — `ay` 子对象

| 字段 | 类型 | /命令 | 含义 |
|------|------|-------|------|
| ay.c | int | /credits | 起始资金 |
| ay.d | int | /fog | 迷雾 (0关/1基础/2全) |
| ay.f | int | /ai | AI难度 |
| ay.g | int | /startingunits | 起始单位数 |
| ay.h | float | /income | ★ 收入倍率 |
| ay.i | bool | /nukes | 核弹启用 |
| ay.l | bool | /shared | 共享控制 |
| ay.a | ai enum | — | 地图类型 |
| ay.b | String | — | 地图文件名 |

## 回放 Header 字节级偏移 (来自 REPLAY_FORMAT)

| 偏移 | 大小 | 字段 | 值 |
|------|------|------|----|
| 0x0000 | 2B | magic_len (BE uint16) | 19 |
| 0x0002 | 19B | magic | "rustedWarfareReplay" |
| 0x0015 | 4B | header_int1 (BE int32) | 176 (SettingsEngine.settingsGameVersion) |
| 0x0019 | 4B | header_int2 (BE int32) | 96 |
| 0x001D | 2B | version_len | 4 |
| 0x001F | 4B | version | "1.15" |
| 0x0023 | 2B | null pad | 0x0000 |
| 0x0025 | 1B | TLV tag | 0x08 |
| 0x0026 | 2B | TLV len (BE uint16) | var |
| 0x0028 | N | TLV value | "gamesave..." |

TLV 嵌套链: `gamesave → rustedWarfareSave → saveCompression → GZIP`

## GameSetup 45B 头部 (来自 REPLAY_FORMAT)

| 偏移 | 大小 | 字段 |
|------|------|------|
| +0 | 4B | version_flag (u32) |
| +4 | 4B | credit_multiplier (f32_be) |
| +8 | 4B | flags (u32) |
| +12 | 4B | settings_bitfield (u32) |
| +16 | 4B | data (u32) |
| +20 | 4B | value (f32_be) |
| +22 | 4B | ★ game_speed (f32_be) |
| +26 | 2B | unit_cap (u16_be) |
| +28 | 2B | unit_cap_2 (u16_be) |
| +30 | 4B | more_flags (u32) |

## 网络包类型 (40+, 来自 REVERSE_ENGINEERING_COMPLETE)

| ID | 类型 | 路由 |
|----|------|------|
| 10 | TICK | 队列 (≤100) |
| 20 | GAMECOMMAND | 队列 |
| 35 | SYNC | 队列 |
| 110 | REGISTER_PLAYER | 即时 (>100) |
| 120 | START_GAME | 即时 |
| 140/141 | CHAT | 即时 |
| 105/108/111/160 | 系统消息 | 即时 |

## 指令结构 — `e.java` (Command)

ba.java 反编译确认 `e2` (Command) 对象的反序列化流程 (line 506: `e2.a(this.F)`):

```
e.a(j.k) — 从 BinaryReader 读取:
  [4B: tick (int)]
  [3B: writeUTF 'c' (00 01 63)]
  [1B: team_ref (n.k byte, j.as.c)]     ← 回放中始终为0
  [1B: hasAU (bool)]                     ← 0=建造指令, 1=移动/攻击指令
  ...variable AU sub-object (if hasAU)...
  [ee:1B] [eg:1B★stopOrUndo] [action_idx:2B]
  [fire_mode:2B] [pointf_z:8B] [eo:1B]
  [long_list] [gamestate_ref] [pointf_l:8B] [target_unit:4B]
  [writeUTF unit_name]                   ← 建造指令的单位名
  [ef:1B] [e.q:2B★player_index] [er:1B★systemAction]
  [es:4B★changeStepRate] [et:4B] [eu:4B★systemAction_action]
```

### ★ 块内 player_index 定位 (2026-06-07 发现)

**对于 hasAU=0 的建造指令块，真实格式为:**
```
[0-3]:  tick (4B int BE)
[4-6]:  writeUTF 'c' (00 01 63)
[7]:    team_ref (1B, 回放中始终为0)
[8]:    hasAU (1B, 0=build)
[9]:    flags/ee (1B, 0 for normal build)
[10]:   remaining_len (1B, = data_len - 11, 验证率 99.6%)
[11]:   ★ PLAYER_INDEX (1B, 范围 0-7, 对应8个真实玩家)
[12+]:  unit_name writeUTF + payload
```

验证: 3536个rc block中，所有 hasAU=0 的块 byte[11] 都给出正确的 player_index (0-7)，
分布与8个玩家的实际建造数完全匹配。这解释了旧代码 `\x00\x01c` 扫描为何能碰巧工作 —— 
它从偏移4找到标记，+7 = 偏移11，恰好读到 player_index 字节。

### 指令类型 (ba.java h() 方法, 498-660行验证)

```
Block types (from j.as writeUTF markers):
  rc     — 玩家指令 (Command, 501行)
  wait   — 等待/帧同步 (540行)
  cs     — 同步校验 checksync (546行)
  es     — 扩展同步 extended sync (568行)
  resync — 重同步/完全状态重建 (601行)
  chat   — 聊天消息 (623行)
  end    — 回放结束 (633行)
  endReplayMetaData — 元数据结束 (653行)
  其他   — Unknown command block (656行, 错误!)
```

## 回放块格式 — `j.as`

```
每个块: [2B: UTF_len] [name_bytes] [4B: data_size] [data_bytes]

块类型:
  'rc'   — 玩家指令
  'chat' — 聊天消息
  'cs'   — 同步检查
  'es'   — 事件
  'wait' — 等待
  'end'  — 结束标记
```

## 网络引擎方法 — `j.ad` (104KB, 30+方法)

```
连接管理:
  a()         — 主更新循环
  b(c)        — 添加连接
  c(c)        — 断开连接
  b()         — tick更新
  x()         — 同步 (synchronized)

数据包:
  a(au)       — 接收数据包
  c(au)       — 发送数据包 (synchronized)
  bj, bk (e)  — 指令缓冲区

游戏状态:
  a(e)        — 发送指令到所有客户端
  e() → ah    — 获取游戏设置
  a(ah)       — 设置游戏设置
  a(n)        — 注册玩家

生命周期:
  L()         — 加载/启动
  m()         — 配置
  d()         — 初始化
  F()         — 清理
  J(), K()    — 更多操作
  q(),r(),s(),t(),u(),v(),w() — 连接生命周期

关键字段:
  aM (ConcurrentLinkedQueue) — 连接队列
  bl (Object)                — 同步锁
  bv (Socket)                — 网络套接字
  aA, aB (k)                 — 二进制读取器
  aC (a)                     — 二进制写入器
```

## 回放写入管线

### 数据块 — `gameFramework.bd` (DataBlock)
```
a (int)      — 块类型/ID
b (boolean)  — 是否压缩
c (Long)     — 时间戳
d (byte[])   — 原始数据
e (e)        — Command 引用 (rc块)
f (byte[])   — 压缩后数据
h,i (int)    — 块参数
j,k (float)  — 坐标
```

### 后台写入线程 — `gameFramework.bb` (BackgroundWriter)
```
implements Runnable
a (volatile bool)    — 运行标志
b (volatile int)     — 写入计数
i (ConcurrentLinkedQueue) — 数据块队列
j (long)             — 最后写入时间
k (ba)               — 所属 ReplayEngine

a(bd) — 添加数据块到队列
a()   — 刷新/开始写入
run() — 线程主循环 (从队列取块→压缩→写入磁盘)
```

## 游戏存档 — `gameFramework.y` (GameSaver)

```
a(String, bool) → File    — 获取存档路径 (saves/ 目录)
b(String, bool) → void    — ★ 保存游戏 (.rwsave)
  ├── 创建 .tmp 临时文件
  ├── 序列化游戏状态 (a(j.as))
  ├── 压缩/加密
  ├── 重命名 .tmp → .rwsave
  └── 支持 SD 卡存储

文件扩展名: .rwsave
存档格式: j.as 二进制 (与回放快照相同格式)
```

## 回放系统

### ReplayEngine — `gameFramework.ba` (821行, 完整逆向)

全局引用: `l.cb → ba`, 通过 `l.B().cb` 访问

#### 核心字段 (45个)
```
状态:  P(volatile bool) 引擎激活中  u(bool) true=播放/false=录制
       s(bool) 回放已结束  h(bool) headless模式  n(bool) 跳过校验和
I/O:   C/D/E 输入流链  F(k) BinaryReader  G/H/I/J 输出流(录制用)
队列:  w(bd) 当前指令  x(bd) 上一条指令  y(int) 指令ID  z(int) 已执行计数
统计:  l(int) 校验和失败次数  o/p/q tick/版本号

调试开关 (static):
  b=true  回放信息日志  c=true  ★updateGameFrame调试输出
  d=false 指令前后资金   e=true  录制信息日志
```

#### `h()` — 块读取 (498-660行) ★核心
```
每调用一次从 BinaryReader(F) 读取一个 j.as 块并解析:

"rc" (501行):  player_index 从块数据偏移11读取 (byte)
               Command e2 从 BinaryReader 反序列化18字段
               调试输出: Command/Waypoint/Build Type/stopOrUndo/systemAction/changeStepRate
"wait" (540行): 帧同步等待
"cs" (546行):   帧号+校验和验证 → 不匹配则 l++ (最多150次)
"es" (568行):   扩展校验和, 遍历活跃连接验证
"resync" (601行): 完全状态重建 (l2.ca.a)
"chat" (623行): 聊天: tick + 玩家ID + 玩家名 + 消息
"end" (633行):  回放结束 → s=true 或完全停止
其他 (656行):   ★ l.b("Unknown command block:") — 错误!
```

#### `a(float)` — 帧更新 (667-777行)
```
每帧调用: if w==null → h()读取块 → if bx>=w.a → e.k()执行指令 → z++ → w=null
```

#### `a(String,File)` — 回放加载 (326-428行)
```
startReplayingFile:
  1. 读取文件头: readUTF → "rustedWarfareReplay"
  2. f()→v1(176), f()→v2(96), 版本检查
  3. l()→"gamesave", l2.ca.a(F) → 加载初始快照
  4. 恢复玩家列表/队伍 → 开始帧循环
```

#### Command (e) 对象字段 (从ba.java还原)
```
e.i (n)    — 玩家对象
  .v (String) — 玩家名称 (ba.java:514)
  .k (int)   — 玩家槽位 0..15 (ba.java:514)
  .o (double)— 当前资金
e.j (AU)   — 路径点子对象
  .d() → a.c — 武器类型枚举 (move/attack/build/reclaim/...)
  .a() → as  — 建造目标单位类型
e.k (a.s)  — 特殊动作 (ba.java:522)
e.n        — SetAttackMode (ba.java:525)
e.g (bool) — stopOrUndo 取消标志 (ba.java:527)
e.r (bool) — systemAction 标志 (ba.java:530)
e.s (float)— changeStepRate 速度变更 (ba.java:532)
e.u (int)  — systemAction_action 100=投降 (ba.java:535)
e.p (n)    — 执行前玩家状态 (调试用, ba.java:712)
```

### GameSaver — `gameFramework.y` (l.ca)
```
a(j.as)          — 序列化游戏状态
b()              — 保存到文件 (.rwsave)
c()              — 从文件加载
```

### 全局引用
```
l.ca → gameFramework.y     (GameSaver)
l.cb → gameFramework.ba    (ReplayEngine)
```

## 回放文件格式

```
.replay:
  [13B: magic "rustedWarfareReplay"]
  [4B: ?] [4B: ?]
  [version: writeUTF]
  [gzip块 @ offset 99]
    └── 游戏快照 (TMX地图 + 玩家数据 + 迷雾)
  [指令流 (j.as块序列)]
```

## UI/渲染架构 (来自 UI_MODULE)

游戏使用 LibRocket (C++ HTML/CSS 引擎, 现名 RmlUI) 通过 JNI 绑定到 Java:

```
librocket.b (40方法) — Core Binding
  ├── LoadTexture / ReleaseTexture — 纹理管理
  ├── HandleEvent — 事件分发到 JS
  └── newDocumentLoaded/Shown — 文档生命周期

librocket.scripts.Root (164方法) — 主UI控制器
  ├── open/back/startNew — 画面导航
  ├── alert/popup/inputPopup — 弹窗系统
  ├── hostStart/joinServer — 多人游戏
  └── getValueById/setValueById — HTML数据绑定

game.i (61字段, 53方法) — 游戏主画面 (HUD)
  ├── a(Context) — 初始化 (1987B)
  ├── a(bool,bool,State) — 主渲染 (2662B) 
  ├── b(float,int) — HUD渲染 (2965B): 金钱/收入/建造菜单/小地图
  └── j() — 输入处理 (128B)

渲染帧循环:
  Main.render() → e.b(ctx) → game.i 游戏画面 → librocket UI覆盖 → SwapBuffers

技术栈:
  UI:      libRocket (C++ JNI)
  2D渲染:  Slick2D (Java)
  OpenGL:   SGL wrapper → GL10/GL20 跨平台
  桌面兼容: android.graphics.* Stubs (自实现)
```

## 二进制序列化协议 — `j.k` / `j.as` ★NEW

### InputNetStream — `j.k` (8KB, 42方法)

游戏的自定义二进制反序列化器。包装 `DataInputStream`，使用**命名块协议**。

```
字段:
  a (ByteArrayInputStream) — 字节输入源
  e (DataInputStream)      — 主读取流
  f (DataInputStream)      — 子块读取流 (嵌套时切换)
  g (LinkedList)           — 读取流栈 (嵌套块支持)
  b/c/d (int)              — 标记计数器 (默认999999)

构造:
  <init>(byte[])           — 从字节数组读取
  <init>(String)           — 从文件路径读取
  <init>(DataInputStream)  — 从已有流读取
  <init>(j.au)             — 从网络包读取

块协议 (命名块):
  a(String,bool,bool) → void      (84B)  — ★ startBlock(name, ?, ?)
    — 最大嵌套深度: 11 (bipush 11)
    — 名称不匹配 → 报错 "Name does not match: expected:X, got:Y"
    
  d(String) → void                 (129B) — ★ endBlock(name)
    — 检查名称匹配
    — 弹出子块流, 恢复父流
    
  c(String) → byte[]               (56B)  — ★ getBlockRaw(name)
    — 读取整个命名块的原始字节
    
  a(bool,bool) → String            (79B)  — startBlockAndGetName()
    — 返回块名, 深度检查
    
  a(String) → void                 (71B)  — readMark(name)
    — 读取同步标记 (值应为12345/sipush 12345)
    — 不匹配 → "Mark wasn't read for: {name}"

读取方法 (类型化):
  d() → byte        e() → boolean
  f() → int         g() → float      h() → double
  i() → long        v() → short
  j() → String      l() → String (短)
  k() → Integer     m() → custom.g (TeamTag)
  q() → as          ★ readUnitType (167B)
                    — 处理内置(ar)和自定义(custom.l)单位类型
  r() → game.n      ★ readPlayer (51B)
                    — 通过 n.k(int) 查找玩家引用
  p() → y           — readUnitTypeBase
  o() → am          — readUnit (读单位引用ID)
  y() → PointF      — readPointF
  t() → byte[]      — readByteArray
  b(Class) → Enum   — readEnum
  a(Class) → gameFramework.w — readGameObject
```

### OutputNetStream — `j.as` (8KB, 45方法)

配套的二进制序列化器。包装 `DataOutputStream`。

```
字段:
  b (ByteArrayOutputStream) — 字节输出目标
  c (DataOutputStream)      — 主写入流
  a (DataOutputStream)      — 子块写入流 (嵌套时切换)
  e (LinkedList)            — 写入流栈

块协议 (与j.k对应):
  a(String) → void        — ★ startBlock(name)
  b(String) → void        — ★ endBlock(name)
  a(String,bool) → void   — writeMark(name, ?)
  c(String) → void        — writeBlockRaw(name, bytes)
  b(String,byte[]) → void — writeBlock(name, bytes[])

写入方法:
  a(byte) → void       a(int) → void
  a(float) → void (9B) a(double) → void
  b(int) → void        a(long) → void
  a(String) → void     a(boolean) → void
  a(as) → void         ★ writeUnitType
  a(PointF) → void     a(game.n) → void
  a(custom.g) → void   b(Enum) → void
  a(Class, Object) → void — writeGameObject
```

### 块协议格式

```
二进制布局:
  [name_length: 2B big-endian]
  [name_bytes: UTF-8]
  [data_length: 4B big-endian]
  [data_bytes: ...]
  
嵌套深度限制: 11层 (bipush 11)
同步标记值: 12345 (sipush)
最大ID值: 999999 (ldc)
```

---

## 连接管理 — `j.c` (PlayerConnect)

```
i (bool)  — 已初始化
p (bool)  — 已连接
z (n)     — 玩家数据引用
h (bool)  — UDP标志
e()→String— 获取玩家名称
```
