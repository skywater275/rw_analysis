# Rusted Warfare v1.15 — 完整网络栈源码逆向
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 3层协议栈: 可靠UDP传输层 → 网络引擎 → 主服务器通信
> 关键文件: `a/a/a/Packet.java`, `a/a/ReliableSocket.java`, `NetEngine.java`(5358行), `gameFramework/j/`(51文件)

---

## 1. 可靠UDP传输层 (`a/a/a/` + `a/a/`)

### 1.1 数据包类型 (`a/a/a/` — 9个类)

自定义可靠UDP协议，类似ENet/KryoNet:

```
Packet(h) — 抽象基类
├── AckPacket(a)      ACK  (flag 0x40, 无载荷, 确认号)
├── DataPacket(b)     DAT  (flag 0x40, byte[]载荷)
├── ExtendedAckPacket(c) EAK (flag 0x20, extends AckPacket, int[]序列号)
├── FinPacket(d)      FIN  (flag 0x02, 连接结束)
├── NullPacket(e)     NUL  (flag 0x08, 保活心跳)
├── ResetPacket(f)    RST  (flag 0x10, 连接重置)
├── SynPacket(g)      SYN  (flag 0x80, 11字段连接建立)
└── TaskRunner(i)     Runnable包装器 (wait/notify调度, daemon线程)
```

**Packet基类字段**:
| 字段 | 含义 |
|------|------|
| `a` | 标志位 (0x40=ACK, 0x20=EAK, 0x10=RST, 0x08=NUL, 0x02=FIN, 0x80=SYN) |
| `b` | 载荷长度 |
| `c` | 序列号 (SEQ) |
| `d` | ACK号 (-1=无ACK) |
| `e` | 重传计数 |

**工厂反序列化**: `Packet.b(byte[], int, int)` 根据标志位自动识别类型并创建实例。

### 1.2 可靠Socket层 (`a/a/` — 19个类)

```
ReliableServerSocket(b) extends ServerSocket
├── DatagramSocket + HashMap路由
├── ReliableServerThread(d) — "ReliableServerSocket" daemon接收线程
└── ServerConnectionListener(f) — 连接处理器 (50连接上限)

ReliableSocket(h) extends Socket
├── DatagramSocket + 序列号管理
├── ReliableSocketThread(m) — "ReliableSocket" daemon分发线程
│   └── 分发: SYN→connect, EAK→ack, DAT→recv, ACK→confirm
├── ReliableInputStream(o) — 缓冲读取
├── ReliableOutputStream(q) — 缓冲写入+flush
├── PacketSender(n) — 刷新排队包
├── NullPacketSender(l) — 发送NUL保活
├── SocketStats(i) — 收发/丢弃统计
└── SocketInitTask(j) / SocketStartTask(k)

ReliableClientSocket(e) extends ReliableSocket
└── 阻塞队列接收 (wait/notify)

ReliableProfile(r) — 11个网络参数
├── maxSendQueueSize=32      最大发送队列
├── maxRecvQueueSize=32      最大接收队列
├── maxSegmentSize=300       最大段大小(字节)
├── maxOutstandingSegs=70    最大未确认段
├── maxRetrans=0             最大重传次数
├── maxCumulativeAcks=3      最大累积ACK
├── maxOutOfSequence=3       最大乱序容忍
├── maxAutoReset=3           最大自动重置
├── nullSegmentTimeout=2000  空段超时(ms)
├── retransmissionTimeout=600 重传超时(ms)
└── cumulativeAckTimeout=300 累积ACK超时(ms)
```

---

## 2. 网络引擎层 (`gameFramework/j/`)

### 2.1 架构总览

```
[主服务器通信]
MasterServerClient(ab)     — 获取游戏列表
MasterServerCreate(y)      — 创建游戏房间
MasterServerUpdate(aa)     — 更新房间信息
MasterServerRemove(z)      — 移除房间
SelfInfoFetcher(p)         — 获取自身信息
ServerListLoader(q)        — 服务器列表编排器
ServerListFetcher(u)       — 获取服务器列表
ServerResult(w)            — 操作结果容器
ServerStatus(x)            — SUCCESS/FAILURE枚举
RequestTimeout(o)          — HTTP请求超时 (5秒)
HttpClientPool(r)          — 线程安全HTTP客户端池

[游戏服务器]
NetEngine(ad, 5358行)      — 核心网络引擎
ServerListener(ao)         — 多人服务器端口监听 (Runnable, IP过滤)
ServerConnector(an)        — 异步连接游戏服务器
PlayerConnect(b)           — 玩家连接状态
ReceiveWorker(d)           — "ReceiveWorker-{id}" 接收线程
SendWorker(e)              — "SendWorker-{id}" 发送线程
KeepAliveTimer(av)         — 1秒心跳定时器 (包类型108)

[P2P/Steam]
SteamSocket(h)             — Steam P2P Socket封装
SteamInputStream(i)        — Steam接收流 (LinkedBlockingDeque)
SteamOutputStream(j)       — Steam发送流

[序列化]
InputNetStream(k)          — 二进制反序列化
OutputNetStream(as)        — 二进制序列化
ByteArrayPacketBuilder(aw) — Print-based包构建器
CompressedStream(at)       — GZIP压缩流
TextStream(ax)             — 文本模式输出流
DebugPacketBuilder(f)      — 调试追踪包构建器
PacketBuilder(as,顶层)    — 抽象包构建器基类

[安全与配置]
ChecksumCalculator(ak)     — 13字段反作弊校验和
ChecksumField(al)          — 单字段追踪 (名称+值+启用标志)
SecurityHasher(aq)         — 主服务器认证令牌 (challenge-response)
MatchConfig(ah)            — 对局配置 (implements Cloneable)
GameServerInfo(g)          — 服务器列表条目
PasswordManager(ae)        — 密码保护房间 (包类型118)
NetworkAuth(ac)            — 玩家认证/授权
ChatSystem(a)              — 聊天系统
ChatMessage(b)             — 单条聊天消息
```

### 2.2 主服务器协议

所有主服务器通信使用HTTP POST，响应格式:
```
第1行: CORRODINGGAMES[状态]
第2行+: CSV键值对 (key,value)
```

**API方法**:
| action | 用途 | 关键参数 |
|--------|------|---------|
| list | 获取游戏列表 | game_version, game_version_beta |
| add | 创建游戏房间 | user_id, game_name, game_version, private_token |
| update | 更新房间信息 | id, private_token |
| remove | 移除房间 | id, private_token |
| get | 获取服务器连接信息 | game_id, c, p_hash |
| self_info | 获取自身信息 | port, id |
| error_report | 上报崩溃/错误 | game_version, device_model, sdk_version |

**认证令牌**: SecurityHasher(aq) 使用静态密钥 (b=2,c=3,d=2,e=3,f=4,g="tx",h="_") 生成时间戳+哈希令牌。

### 2.3 反作弊校验和

ChecksumCalculator(ak) 追踪13个游戏状态字段:
```
Unit Pos       — 单位位置 (eo,ep)
Unit Dir       — 单位朝向 (cg)
Unit Hp        — 单位生命值 (cu)
Unit Id        — 单位ID (eh)
Waypoints      — 路径点
Waypoints Pos  — 路径点位置
Team Credits   — 队伍资金
UnitPaths      — 单位路径
Unit Count     — 单位数量
Team Info      — 队伍信息
Team 1/2/3 Credits — 各队伍资金
Command center2/3 — 指挥中心状态
```

### 2.4 对局配置 (MatchConfig/ah)

| 字段 | 类型 | 默认值 | 含义 |
|------|------|--------|------|
| a | GameModeEnum | a | 游戏模式 |
| b | String | "[z;p10]Crossing Large (10p).tmx" | 地图文件 |
| c | int | 0 | 初始资金 |
| d | int | 2 | 迷雾模式 |
| e | boolean | true | 地图可见 |
| f | int | 1 | AI难度 |
| g | int | 1 | 初始单位 |
| h | float | 1.0 | 收入倍率 |
| i | boolean | false | 禁止核弹 |
| l | boolean | false | 共享控制 |
| o | boolean | true | 允许观众 |
| p | boolean | false | 锁定房间 |
| q | int | — | 随机种子 |

---

## 3. 包格式与通信流程

### 3.1 线路格式
```
[4B: payload_length (int, BE)] [4B: packet_type (int, BE)] [NB: payload]
```

### 3.2 包分界线
```
类型 ≤ 100  → 游戏状态包 → tick循环排队处理
类型 > 100  → 系统包 → 接收线程立即处理
```

### 3.3 关键系统包类型
| 类型 | 用途 |
|------|------|
| 108 | 心跳/Ping |
| 118 | 密码认证 |

### 3.4 通信流程
```
客户端                          主服务器                      游戏服务器
  │                                │                              │
  ├─ list ──────────────────────→  │                              │
  │←─ CORRODINGGAMES + 服务器列表─┤                              │
  │                                │                              │
  ├─ add ──────────────────────→  │                              │
  │←─ CORRODINGGAMES + 游戏ID ────┤                              │
  │                                │                              │
  ├─ get ──────────────────────→  │                              │
  │←─ CORRODINGGAMES + 连接信息───┤                              │
  │                                │                              │
  ├─────────── TCP连接 ────────────────────────────────────────→ │
  │←─────────── SYN/ACK (可靠UDP) ──────────────────────────────┤ │
  ├─────────── 密码认证 (包118) ────────────────────────────────→ │
  │←─────────── 游戏状态同步 ───────────────────────────────────┤ │
  │              ...                                               │
  ├─ update ──────────────────→  │  (定期更新房间信息)              │
  ├─ remove ──────────────────→  │  (服务器关闭时)                  │
```
