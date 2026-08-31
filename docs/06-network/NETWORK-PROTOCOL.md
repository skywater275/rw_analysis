# Rusted Warfare v1.15 — 网络协议源码逆向
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 确定性锁步模型、30种包类型、Tick同步、校验和反作弊、二进制块协议
>
> 关键文件: `NetEngine.java`(5359行), `InputNetStream.java`, `OutputNetStream.java`

---

## 1. 网络架构: 确定性锁步 (Deterministic Lockstep)

```
模型: 服务器权威 + 客户端锁步
├── 服务器: 收集指令 → 批次广播 → 校验和验证
├── 客户端: 发送指令 → 等待服务器确认 → 执行
└── 前看窗口: Q 帧 (~8帧 = 133ms at 60fps)
```

### 1.1 关键字段 (NetEngine)

| 字段 | 含义 |
|------|------|
| `X` | nextBlockingFrame — 客户端不能超过此帧 |
| `ah` | lastSentSyncFrame — 上次发校验和的帧 |
| `Q` | lookahead (~8帧) — 服务器提前发送量 |
| `R` | 滞后防护 |
| `ag` | needsResync — 检测到不同步 |
| `F` | 单机模式 |

---

## 2. 包格式

### 2.1 线路格式

```
[4B: payload_length (int, BE)] [4B: packet_type (int, BE)] [NB: payload]
```

### 2.2 包数据结构 (au.java)

```java
class au {
    c a;       // 源/目标连接 (PlayerConnect)
    int b;     // 包类型号
    byte[] c;  // 原始载荷
    int d;     // 延迟 (ms, 限速用)
    boolean e; // 紧急标志
}
```

### 2.3 分界线: 类型100

```
类型 ≤ 100  → 游戏状态包 → 在 tick 循环中排队处理 (aN)
类型 > 100  → 系统包 → 在接收线程立即处理
```

---

## 3. 完整包类型目录 (30种)

### 3.1 游戏包 (每Tick处理)

| 类型 | 名称 | 方向 | 用途 |
|------|------|------|------|
| **10** | TICK_BATCH | 服→客 | ★ 主要Tick同步包: `[nextBlockingFrame] [命令数] [命令1]...[命令N]` |
| **20** | CLIENT_CMD | 客→服 | 客户端单个指令, 服务器验证后广播 |
| **30** | SYNC_CHECKSUM | 服→客 | 帧校验和: `[帧号] [总校验和] [组件校验和列表]` |
| **31** | CHECKSUM_REPLY | 客→服 | 校验和比对结果 (匹配/不匹配/逐组件详情) |
| **35** | RESYNC | 双向 | 完整游戏状态传输 (gzip压缩的 gameSave 块) |

### 3.2 系统包 (立即处理)

| 类型 | 名称 | 用途 |
|------|------|------|
| **105** | GET_SERVER_INFO | 客户端请求服务器信息 (名称/玩家/地图) |
| **106** | GAME_SETTINGS | 完整游戏配置 (设置/队伍/Mod/版本检查) |
| **108** | PING | 延迟测量 (含时间戳) |
| **109** | PONG | Ping 回复 (含RTT) |
| **110** | REGISTER_CONNECTION | ★ 玩家注册: 身份hash/版本/用户名/密码hash/单位checksum |
| **111** | DISCONNECT | 断开通知 (含原因) |
| **112** | CLIENT_READY | 客户端已加载, 通知就绪帧号 |
| **115** | TEAM_SYNC | 完整队伍状态同步 (含版本检查) |
| **116** | GAME_ENDED | 游戏结束通知 |
| **120** | START_GAME | ★ 触发游戏启动 (含地图类型+数据) |
| **122** | RETURN_TO_BATTLEROOM | 返回大厅 |
| **140** | CHAT_CLIENT | 客户端聊天 (服务器反垃圾校验) |
| **141** | CHAT_SERVER | 服务器广播聊天 (含发送者/消息类型/队伍目标) |
| **150** | KICK | 踢出通知 (含原因) |
| **151** | CHALLENGE | 反作弊挑战 (Hashcash式工作量证明, 模式0-7) |
| **152** | CHALLENGE_RESPONSE | 挑战回复 |
| **160** | SERVER_INFO_EXCHANGE | 初始握手: 身份/协议版本/构建信息/完整性检查 |
| **161** | PREREGISTER_INFO | 预注册信息 (UUID/网络版本/玩家限制/客户端ID) |
| **170** | BECOME_SERVER | 中继→客户端: 提升为服务器 |
| **172-176** | FORWARD_* | 中继转发连接管理 |

---

## 4. Tick 同步机制

### 4.1 服务器 Tick 循环 (line 1298)

```
每帧:
├── 等待: bx >= X - R (客户端追上阻塞帧-滞后)
├── 计算目标: n3 = X + Q (当前帧+前看)
├── 收集指令: 所有 e.c == X 的 Command
├── 序列化批次:
│   ├── writeInt(n3)            ← nextBlockingFrame
│   ├── writeInt(count)         ← 命令数
│   └── 每个命令: e.a(as2)      ← 序列化
├── 创建包类型10, 标记紧急 → 广播
└── X = n3
```

### 4.2 客户端 Tick (line 1382)

```
客户端: 本地排队指令 → 序列化 → 发送包20
服务器: 接收包20 → 验证 → 加入下一个包10批次
```

### 4.3 帧推进 (line 1448)

```java
if (bx < X) → 游戏可以推进
if (bx >= X) → 阻塞等待 (Y = false)
```

---

## 5. 校验和/反不同步

### 5.1 服务器 (每~150帧)

```
if (ah + ai/2 < bx):
    ├── 计算帧 bx 的校验和 (ak.a)
    ├── 计算逐组件校验和 (al 列表)
    └── 发送包30: [帧号] [总checksum] [组件checksum列表]
```

### 5.2 客户端

```
接收包30:
├── 比对本地帧号 vs ah
├── 计算本地 checksum
├── 逐字节比对
└── 发送包31: [匹配?] [两个checksum值] [逐组件状态]
```

### 5.3 不同步恢复

```
if (checksum != 本地checksum):
    ├── 记录 desync 计数 (c5.y)
    ├── 可能暂停 (pauseOnDesync)
    └── or 发起完整重同步 (包35):
        ├── gzip 压缩 gameSave
        ├── 传输完整游戏状态
        └── 客户端重置 X = frame+1
```

### 5.4 Checksum 组件

ak 聚合所有游戏对象:
- 单位位置 (`Float.floatToRawIntBits`)
- 单位ID, 路径点
- 队伍 credits, 单位计数
- 命名为: "Unit Pos", "Unit Dir", "Unit Hp", "Team Credits" 等

---

## 6. 二进制块协议

### 6.1 写入 (OutputNetStream)

```
原语:
├── writeByte(c), writeBoolean(e), writeInt(f)
├── writeFloat(g), writeLong(i), writeDouble(h)
├── writeUTF(c), writeShort(a)
└── writeGameObject(b): 实体ID (8B long)

块系统:
├── a(String name, boolean compressed): 开始命名块
│   ├── 创建 at 对象 → 写入到独立的 ByteArrayOutputStream
│   └── 可选 GZIP 压缩
├── a(String name): 结束块
│   ├── writeUTF(name)
│   ├── writeInt(byte_length)
│   ├── write(bytes)  [可选压缩]
│   └── 弹出 at → 恢复父流
└── 标记值: 12345 (short) 用于完整性验证
```

### 6.2 读取 (InputNetStream)

```
块系统:
├── a(boolean, boolean): 读取块
│   ├── readUTF(name)
│   ├── readInt(length)
│   ├── 包装为 l 对象 (可选 GZIP 解压)
│   └── 推入块栈 → 切换 DataInputStream
├── d(String name): 结束块 → 弹出栈
└── a(String): 验证标记 (检查 == 12345)
```

---

## 7. 多人游戏消息流

```
1. 连接建立:
   客→服: 105 (获取服务器信息)
   服→客: 106 (游戏配置)
   客→服: 110 (注册连接) [版本/密码/checksum验证]
   服→客: 161 (预注册信息)

2. 游戏启动:
   服→客: 120 (START_GAME) [地图类型+数据]
   客→服: 112 (CLIENT_READY)
   服务器等全部就绪 → 开始 Tick 循环

3. 游戏循环:
   客→服: 20 (指令) [每帧]
   服→客: 10 (批次) [每帧, 含下个阻塞帧]
   服→客: 30 (校验和) [每~150帧]
   客→服: 31 (校验和状态)
   双向: 108/109 (ping/pong) [每1秒]

4. 不同步恢复:
   服→客: 35 (RESYNC) [gzip完整状态]
   客户端替换全部游戏状态

5. 结束:
   服→客: 116 (GAME_ENDED)
   服→客: 122 (RETURN_TO_BATTLEROOM)
```

---

## 8. 反作弊

| 机制 | 实现 |
|------|------|
| **版本检查** | 包160: 游戏identity/协议版本/构建信息 |
| **单位Checksum** | 包110: 客户端提交单位文件hash |
| **完整性Hash** | 服务器比对客户端版本字符串hash |
| **工作量证明** | 包151/152: Hashcash式挑战 (模式0-7) |
| **校验和验证** | 包30/31: 每~150帧逐组件比对 |
| **密码验证** | 包110: 哈希密码 |
| **Ban列表** | 包110验证时检查 |

---

## 9. 对 RWAgent 的启示

1. **指令注入不需要网络层** — 单机模式 (F=true) 跳过锁步, CommandController 直接执行
2. **包10 是理解多人不同步的关键** — `nextBlockingFrame` 决定帧推进速度
3. **校验和包含单位位置** — 修改单位状态会被检测
4. **GZIP 块压缩** — 地图数据/存档传输都用 GZIP
5. **包20 指令** — `e.a(as2)` 序列化格式与 Command 字段映射一致
6. **中继模式** — 通过 Steam/P2P 时类型170-176管理转发

---

## 10. 可靠UDP传输层 (2026-06-23 新增)

> 关键文件: `a/a/a/`(9个类), `a/a/`(19个类)
> 详见: [NETWORK-STACK.md](NETWORK-STACK.md)

### 10.1 数据包类型

自定义可靠UDP协议，位于 `a/a/a/` 包:
```
Packet(h) — 抽象基类 (seq/ack/len/flags + 工厂反序列化)
├── AckPacket(a)      ACK  (flag 0x40, 确认号)
├── DataPacket(b)     DAT  (flag 0x40, byte[]载荷)
├── ExtendedAckPacket(c) EAK (flag 0x20, int[]序列号)
├── FinPacket(d)      FIN  (flag 0x02, 连接结束)
├── NullPacket(e)     NUL  (flag 0x08, 保活)
├── ResetPacket(f)    RST  (flag 0x10, 连接重置)
└── SynPacket(g)      SYN  (flag 0x80, 11字段连接建立)
```

### 10.2 可靠Socket层

位于 `a/a/` 包:
- **ReliableSocket(h)** extends Socket — 核心可靠Socket
- **ReliableServerSocket(b)** extends ServerSocket — UDP服务器
- **ReliableProfile(r)** — 11网络参数: maxSegmentSize=300, retransTimeout=600ms, maxRetrans=3, nullTimeout=2000ms
- **ReliableInputStream(o)** / **ReliableOutputStream(q)** — 缓冲流

### 10.3 GZIP压缩流

`CompressedStream(at)` 位于 `gameFramework/j/`:
```java
GZIPOutputStream → BufferedOutputStream → DataOutputStream
// 或: ByteArrayOutputStream → DataOutputStream (无压缩)
```

---

## 11. 主服务器通信协议 (2026-06-23 新增)

> 关键文件: `gameFramework/j/MasterServerClient.java`, `WebAPIClient.java`

### 11.1 协议格式

HTTP POST请求，响应格式:
```
行1: CORRODINGGAMES[状态]
行2+: CSV键值对
```

### 11.2 API方法

| action | 类 | 用途 |
|--------|---|------|
| list | ServerListLoader | 获取游戏列表 |
| add | MasterServerCreate | 创建房间 |
| update | MasterServerUpdate | 更新房间信息 |
| remove | MasterServerRemove | 移除房间 |
| get | MasterServerClient | 获取服务器连接信息 |
| self_info | SelfInfoFetcher | 获取自身信息 |
| error_report | ErrorReporter | 上报崩溃/错误 |

### 11.3 认证令牌

`SecurityHasher(aq)` — challenge-response哈希:
- 静态密钥: b=2,c=3,d=2,e=3,f=4,g="tx",h="_",i=55,j=66,k=100
- 时间戳令牌 + MD5/自定义哈希

### 11.4 反作弊校验和

`ChecksumCalculator(ak)` — 13字段校验:
- UnitPos, UnitDir, UnitHp, UnitId, Waypoints, WaypointsPos
- TeamCredits, UnitPaths, UnitCount, TeamInfo
- Team1/2/3Credits, CommandCenter2/3


