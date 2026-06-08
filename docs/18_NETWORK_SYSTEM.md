# 18 — 网络连接系统完全解析

> 来源: RW-HPS (NetServer/NetService/GameVersionRelay) + game-lib.jar (j.ad/j.c/j.au) + rw_engine
> 验证: 服务器实际运行 + 30回放 + 40+包类型枚举

---

## 一、网络架构总览

```
Rusted Warfare 网络层次:

┌─────────────────────────────────────────────────┐
│  应用层: 游戏指令 (rc/cs/chat/end)                │
│  ├─ GameCommandPacket — 玩家操作指令              │
│  ├─ ChatPacket — 聊天消息                        │
│  └─ SyncPacket — 同步检查                        │
├─────────────────────────────────────────────────┤
│  会话层: Hess协议 (Game Hession Protocol)         │
│  ├─ GameVersionServer — 游戏服务端               │
│  ├─ GameVersionRelay — 中继客户端/服务端          │
│  └─ GameVersionPacket — 通用包处理               │
├─────────────────────────────────────────────────┤
│  传输层: TCP/UDP                                  │
│  ├─ TCP: Netty NIO (主要传输)                    │
│  │   ├─ 端口: 默认5201 (可配置范围扫描)          │
│  │   └─ 选项: SO_BACKLOG=1024, TCP_NODELAY, SO_KEEPALIVE │
│  ├─ UDP: RUDP (可靠UDP, 游戏自带, 服务端可禁用)   │
│  │   └─ ReliableServerSocket/ReliableClientSocket │
│  └─ 多协议复用: StartMixProtocol (TCP+TLS+HTTP)   │
├─────────────────────────────────────────────────┤
│  安全层:                                          │
│  ├─ Proof-of-Work (NetConnectProofOfWork)        │
│  │   └─ 7种验证类型 (0-6), SHA-256哈希挑战       │
│  ├─ IP过滤: isIpAllowed                          │
│  ├─ UUID检测: 防止重复连接/崩溃恢复              │
│  ├─ 连接频率限制: 60秒10次, 24小时400次BAN       │
│  └─ 服务器密码 (可选)                            │
└─────────────────────────────────────────────────┘
```

---

## 二、NetEngine — 游戏引擎网络核心 (j.ad, 104KB)

### 2.1 核心字段

```
j.ad (NetEngine):
  X (int)            — 网络tick计数器
  C (boolean)        — 游戏是否已开始
  m (int)            — 服务器端口号
  y (String)         — 主机/玩家名称
  s (boolean)        — 永续模式标志 (never end)
  o (boolean)        — 启用Mod标志
  z (n)              — 隐藏队伍 (服务端同步占位符)
  ax (int)           — 最大单位数
  aw (int)           — 最大单位数(备用)
  az (String)        — 地图文件路径
  aM (ConcurrentLinkedQueue) — 连接列表
  aD (Thread)        — TCP Accept线程
  aE (ao)            — ServerAcceptRunnable (TCP监听)
  ay                 — 游戏设置子对象 (GameSettings)
  bC (Object)        — 队伍操作同步锁
  aN (Queue)         — 高优先级包队列 (typeInt > 100)
  bl (Object)        — 同步锁
```

### 2.2 网络包类型 (PacketType — 40+种)

```kotlin
// 来自 PacketType.kt — RW-HPS 完整枚举

// 游戏核心包 (typeInt < 100):
PACKET_DOWNLOAD_PENDING(4)     // 下载等待
TICK(10)                       // ★ 游戏tick同步
GAMECOMMAND_RECEIVE(20)        // ★ 游戏指令接收
SYNC_CHECK(30)                 // 同步校验
SYNCCHECKSUM_STATUS(31)        // 同步校验状态
SYNC(35)                       // 同步数据

// 预注册/认证 (typeInt 100-160):
REGISTER_PLAYER(110)           // ★ 玩家注册
ACCEPT_START_GAME(112)         // 接受开始游戏
PASSWD_ERROR(113)              // 密码错误
TEAM_LIST(115)                 // ★ 队伍列表
RELAY_117(117)                 // Relay协议
RELAY_118_117_RETURN(118)      // Relay协议返回
SERVER_INFO(106)               // 服务器信息
HEART_BEAT(108)                // ★ 心跳
HEART_BEAT_RESPONSE(109)       // ★ 心跳响应

// 游戏控制 (typeInt 120-150):
START_GAME(120)                // ★ 开始游戏
RETURN_TO_BATTLEROOM(122)      // 返回房间
CHAT_RECEIVE(140)              // 聊天接收
CHAT(141)                      // 聊天发送
KICK(150)                      // 踢出
DISCONNECT(111)                // 断开连接

// Relay协议 (typeInt 150-180):
RELAY_POW(151)                 // Proof-of-Work挑战
RELAY_POW_RECEIVE(152)         // PoW应答
PREREGISTER_INFO_RECEIVE(160)  // 预注册信息
PREREGISTER_INFO(161)          // 预注册信息
RELAY_VERSION_INFO(163)        // Relay版本信息
RELAY_BECOME_SERVER(170)       // 成为Relay服务端
FORWARD_CLIENT_ADD(172)        // 转发客户端添加
FORWARD_CLIENT_REMOVE(173)     // 转发客户端移除
PACKET_FORWARD_CLIENT_FROM(174) // 来自客户端的转发包
PACKET_FORWARD_CLIENT_TO(175)  // 到客户端的转发包
PACKET_FORWARD_CLIENT_TO_REPEATED(176) // 重复转发
PACKET_RECONNECT_TO(178)       // 重连

// 自定义扩展 (typeInt >= 2000):
SERVER_DEBUG_RECEIVE(2000)     // 调试接收
SERVER_DEBUG(2001)             // 调试发送
GET_SERVER_INFO_RECEIVE(3000)  // 获取服务器信息
GET_SERVER_INFO(3001)          // 服务器信息
```

### 2.3 包路由规则

```
包处理优先级:
  typeInt > 100  → 即时处理 (c(packet)) — 认证/聊天/系统消息
  typeInt ≤ 100  → 队列处理 (aN.add(packet)) — tick/指令/同步

包大小限制:
  最大包大小: 50000000 (50MB, NetService.maxPacketSizt)
  包头长度: 8B (headerSize)
  minLowWaterMark: 512KB (512 * 1024)
```

---

## 三、连接管理层

### 3.1 连接协议类型 (IRwHps.NetType)

```kotlin
enum class NetType {
    ServerProtocol,          // 标准游戏服务端协议
    ServerProtocolOld,       // 旧版本服务端协议
    ServerTestProtocol,      // 测试版服务端协议
    RelayProtocol,           // 中继协议 (单播)
    RelayMulticastProtocol,  // 中继协议 (多播)
    DedicatedToTheBackend,   // 专用后端
    HttpProtocol,            // HTTP服务
    RemoteControlProtocol,   // 远程控制 (RCON)
    GlobalProtocol,          // 全局协议
    GameProtocol,            // 游戏协议 (内部)
    NullProtocol             // 空协议 (fallback)
}
```

### 3.2 连接状态机 (Relay协议)

```
RelayStatus 状态流转:
  InitialConnection         — 初始连接
  ↓ (发送Relay服务器信息)
  CertifiedConnection       — 通过版本验证
  ↓ (PoW验证 / 密码验证)
  PlayerPermission          — 获得游戏权限
  ↓ (注册玩家UUID)
  PlayerJoinPermission      — 加入游戏许可
  ↓ (可选的Host权限)
  HostPermission            — 房主权限
  ↓ (断开或游戏结束)
  CertifiedEnd              — 认证结束/断开
```

### 3.3 连接认证 — Proof-of-Work 系统

```
NetConnectProofOfWork — 7种验证类型:

Type 0: 简单整数验证
  挑战: 随机生成 initInt_1
  验证: 客户端返回相同的数字字符串
  
Type 1: 简单整数验证
  挑战: 随机生成 initInt_2
  验证: 客户端返回相同的数字字符串

Type 2: (1.15新增, 选择性启用)
  挑战: Game.connectKeyLast(initInt_1)
  验证: 客户端计算游戏内置密钥

Type 3/4: SHA-256哈希验证
  挑战: initInt_1, initInt_2
  目标: SHA-256("initInt_1|initInt_2") 的前14位十六进制
  验证: 客户端返回匹配的哈希值

Type 5/6: 哈希+偏移验证 (最复杂)
  挑战: fixedInitial (随机4字符), off (0-10), maximumNumberOfCalculations
  目标: SHA-256(fixedInitial + "" + off) 的前14位
  验证: 客户端返回正确的偏移值
```

### 3.4 连接频率限制 (ConnectLimit插件)

```
IP连接限制:
  1分钟: 最多10次连接 (6s/次)
  24小时: 超过400次 → 自动BAN (144s/次平均)
  
本地豁免:
  127.0.0.1 和 192.168.* 不受限制

UUID重复检测:
  加入房间前检查是否有相同UUID的已连接玩家
  如果是崩溃后的重连 → 允许
  如果是重复连接 → Kick
```

---

## 四、NetService — 服务端网络服务

### 4.1 TCP服务器启动

```kotlin
// NetService.openPort(port, startPort, endPort)
1. 创建 EventLoopGroup (boss + worker)
   - Epoll (Linux) 或 NIO (其他平台)
   - IO ratio: 由 configNet.nettyIoRatio 控制
   
2. ServerBootstrap 配置:
   - SO_BACKLOG: 1024
   - TCP_NODELAY: true
   - SO_KEEPALIVE: true
   
3. ChannelHandler (childHandler = start):
   - GamePacketDecoder  — 解析游戏包
   - GamePacketEncoder  — 编码游戏包
   - NewServerHandler   — 新连接处理器

4. 端口范围扫描:
   for (i in startPort..endPort):
       serverBootstrap.bind(i)
```

### 4.2 包编解码

```
GamePacketDecoder (TCP):
  1. 读取包头 (8B)
  2. 解析包类型 (typeInt)
  3. 读取包体 (bytes)
  4. 创建 Packet 对象

GamePacketEncoder (TCP):
  1. 写入包类型 (typeInt → 4B)
  2. 写入包长度 (data.length → 4B)  
  3. 写入包数据 (bytes)

NetEnginePackaging:
  将 RW-HPS Packet ↔ game-lib PacketHess (au) 互转
  typeInt > 100 → 即时处理 (netEngine.c)
  typeInt ≤ 100 → 队列处理 (netEngine.aN.add)
```

---

## 五、Relay 中继协议

### 5.1 中继架构

```
┌─────────┐                  ┌─────────────┐                  ┌──────────┐
│ 客户端A  │ ←── TCP/UDP ──→ │ Relay服务器  │ ←── TCP/UDP ──→ │ 客户端B   │
│ (HOST)  │                  │ (RW-HPS)     │                  │ (CLIENT)  │
└─────────┘                  └─────────────┘                  └──────────┘
                                   │
                            ┌──────┴──────┐
                            │ RelayRoom   │
                            │  ├─ admin: HOST
                            │  ├─ groupNet: 群发
                            │  └─ 转发表 (site→connect)
                            └─────────────┘
```

### 5.2 Relay房间管理

```kotlin
RelayRoom:
  serverUuid: String         — 服务器UUID
  id: Int                    — 房间内部ID
  internalID: Int            — 房间显示ID
  isMod: Boolean             — Mod标志
  isStartGame: Boolean       — 游戏是否开始
  admin: GameVersionRelay?   — 房主连接
  groupNet: GroupNet         — 群发网络
  abstractNetConnectIntMap   — 连接映射 (site → GameVersionRelay)
  relayPlayersData           — 玩家数据 (UUID → PlayerRelay)
  relayKickData              — 踢出/封禁数据
  gamePacket                 — 游戏包缓存
  closeRoom: Boolean         — 房间关闭标志
  
  getRelay(id) → RelayRoom  — 按ID查找/创建房间
  getSize() → Int           — 当前玩家数
  setAddPosition() → Int    — 分配site位置
```

### 5.3 Relay转发流程

```
PACKET_FORWARD_CLIENT_FROM (HOST ← 客户端):
  1. 客户端发送指令包到Relay
  2. Relay包装: [site:4B] [packet_size+8:4B] [data_size:4B] [type:4B] [bytes]
  3. 转发到HOST (admin.sendPacket)

PACKET_FORWARD_CLIENT_TO (客户端 ← HOST):
  1. HOST发送响应包到Relay
  2. Relay解析目标site
  3. 查找对应连接 (abstractNetConnectIntMap[target])
  4. 转发到目标客户端

FORWARD_CLIENT_ADD (客户端加入):
  1. 新客户端连接
  2. 分配site位置
  3. 通知HOST: [version:1B] [site:4B] [UUID] [IP]

FORWARD_CLIENT_REMOVE (客户端离开):
  1. 客户端断开
  2. 通知HOST移除site对应的转发
```

### 5.4 自定义房间参数 (Relay EX)

```
房间ID格式解析:
  R<id>         — 标准房间 (如 R123)
  C<custom>@<id> — 自定义参数房间
  new/mod/news  — 创建新房间

自定义参数 (P/p开头):
  P10,I2.5      — 10人, 收入2.5倍
  P8,U150        — 8人, 150单位限制
  P6,U200,I1.5   — 6人, 200单位, 1.5倍收入

预定义房间:
  old → "R<上次ID>"
  R + id → 直接加入现有房间
```

---

## 六、连接套接字覆盖

### 6.1 CustomServerSocket — BIO→NIO替换

RW-HPS 覆盖游戏引擎的 `ServerAcceptRunnable (j.ao)`:

```kotlin
class CustomServerSocket(netEngine: ad): ServerAcceptRunnable(netEngine) {
    // 替换原生Socket监听为Netty NIO
    
    override fun run() {
        1. 获取端口: port = netEngine.m
        2. 创建 IRwHps 实例 (ServerProtocol)
        3. 启动 NetService (NIO)
        4. 注册到 NetStaticData
    }
    
    override fun close() {
        // Netty优雅关闭
        netService.stop()
    }
}
```

### 6.2 HessSocket — 虚拟套接字

```kotlin
class HessSocket(connect: ConnectionAgreement): Socket() {
    // 为每个连接提供虚拟Socket适配器
    // 使Netty连接能被游戏引擎的BIO代码使用
}
```

### 6.3 游戏自带网络功能移除

```kotlin
// NetPacketRedirections.kt
// 移除以下游戏自带功能:
replace("com/corrodinggames/rts/gameFramework/j/ao")  — 原生Socket监听
replace("com/corrodinggames/rts/gameFramework/j/d")   — 原生连接管理
replace("com/corrodinggames/rts/gameFramework/j/e")   — 原生客户端
replace("com/corrodinggames/rts/gameFramework/j/n")   — 主服务器列表
replace("com/corrodinggames/rts/gameFramework/j/u")   — MasterServer注册
replace("a/a/d")                                       — RUDP实现
```

---

## 七、连接生命周期

### 7.1 客户端加入完整流程

```
1. TCP连接建立
   → NetService.openPort → ServerBootstrap
   → NewServerHandler 处理新连接
   
2. 协议握手 (Relay)
   → 客户端发送 Relay版本信息
   → 服务器返回 RelayServerInfo
   → 客户端发送 房间ID选择
   
3. Proof-of-Work 验证
   → 服务器发送 NetConnectProofOfWork 挑战
   → 客户端计算并返回答案
   → 验证通过 → 进入 PlayerPermission
   
4. 玩家注册
   → REGISTER_PLAYER(110): [name] [UUID] [IP]
   → UUID重复检测
   → IP封禁检测
   → 分配 PlayerRelay 数据
   
5. 转发建立
   → FORWARD_CLIENT_ADD(172) → HOST
   → HOST 确认 → PACKET_FORWARD_CLIENT_FROM
   → 状态: PlayerJoinPermission
   
6. 游戏同步
   → TEAM_LIST(115): 队伍分配
   → START_GAME(120): 开始游戏
   → TICK(10): 游戏tick同步
   → GAMECOMMAND_RECEIVE(20): 游戏指令
```

### 7.2 心跳机制

```
HEART_BEAT(108) / HEART_BEAT_RESPONSE(109):
  客户端定期发送心跳
  服务器响应心跳 + 返回当前游戏状态
  
HEART_BEAT_RESPONSE 格式:
  [8B: 从请求复制] [1B: 协议版本(0)]  
```

### 7.3 断开处理

```
客户端断开触发:
  1. RelayPlayerDisconnect
  2. 通知HOST移除 (FORWARD_CLIENT_REMOVE)
  3. 如果是HOST断开:
     a. 转移到下一个可用客户端 (adminMoveNew)
     b. 重新发送RelayServerId
  4. 如果剩余玩家=0:
     a. 关闭房间 (relayRoom.re())
  5. 清理连接数据
     a. groupNet移除
     b. relayPlayersData移除
     c. 释放site位置
```

---

## 八、包格式细节

### 8.1 Packet 二进制格式

```
[4B: typeInt (BE)] — 包类型标识
[4B: dataLength (BE)] — 数据长度
[dataLength bytes] — 包数据

GameCommandPacket 内部格式:
  [2B: padding]
  [2B: tick (short)]
  [1B: len + 'c' marker]
  [4B: team_ref]
  [1B: player_index]
  [1B: hasAU flag]
  [... variable length sub-objects ...]
```

### 8.2 转发包格式

```
PACKET_FORWARD_CLIENT_FROM (174):
  [4B: site (源客户端位置)] — BE int32
  [4B: packetSize + 8]      — 总大小
  [4B: dataSize]             — 数据大小
  [4B: typeInt]              — 原始包类型
  [dataSize bytes]           — 原始包数据

PACKET_FORWARD_CLIENT_TO (175):
  [4B: site (目标客户端位置)]
  [4B: packetSize + 8]
  [4B: dataSize]
  [4B: typeInt]
  [dataSize bytes]

FORWARD_CLIENT_ADD (172):
  [1B: version (0 or 1)]
  [4B: site]
  [String: UUID]
  [String?: optional UUID (version 1)]
  [String?: IP (version 1)]
```

### 8.3 预注册/服务器信息包

```
PREREGISTER_INFO (161):
  [String: serverID]
  [Int: 1]
  [Int: clientVersion]
  [Int: clientVersion (重复)]
  [String: "com.corrodinggames.rts.server"]
  [String: serverUuid]
  [Int: hashCode]

SERVER_INFO (106):
  [String: serverID]
  [Int: version]
  [Int: mapType]
  [String: mapName]
  [Int: credits (起始资金)]
  [Int: fog (迷雾: 0=关, 1=基础, 2=全)]
  [Boolean: sharedControl]
  [Int: aiDifficulty]
  [Byte: startingUnits]
  [Boolean: nukesEnabled]
  [Boolean: modsEnabled]
```

---

*参考: RW-HPS NetService.kt + GameVersionRelay.kt (1062行) + PacketType.kt + NetConnectProofOfWork.kt*
*最后更新: 2026-06-07*
