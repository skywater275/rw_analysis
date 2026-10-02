# 10-network — 网络与联机

> **职责**：大厅、房间、锁步同步、断线重连、回放

> 本文处于**项目分析上限** ✓：数据全部来自映射库 / 反编译树 / 交付物实测 ✓，口径见 [../STATUS.md](../STATUS.md) ✓。

## 一、数据总览

| 指标 | 值 |
|---|---|
| 映射条目 | **674** 条 |
| 涉及类 | **42** 个 |
| 有可读名 | **39** 个（93%）|
| ★ **已替换为我们的字节** | **27** 个（64%）|
| 主包 | `com.corrodinggames.rts.gameFramework.j` · `a.a` · `a.a.a` · `a.a.a.g` |

### 交付物替换状态（★ 该域我们掌控多少 ✓）

| 状态 | 类数 | 含义 |
|---|---|---|
| **我们的字节** ✓ | **27** | 已由反编译源重建并在交付物中替换 |
| 原版字节 | 13 | 仍使用原版混淆字节（结构一致 ✓）|
| 未进交付物 | 2 | 该域映射涉及、但不属交付物条目 |

## 二、包结构与源码位置

| 混淆包 | 映射行 |
|---|---|
| `com.corrodinggames.rts.gameFramework.j` | 595 |
| `a.a` | 68 |
| `a.a.a` | 6 |
| `a.a.a.g` | 5 |

**03 源码位置**（该域主包 ✓）：

| 包（可读路径） | 文件 | 行数 |
|---|---|---|
| `com/corrodinggames/rts/gameFramework` | 95 | 11769 |
| `com/corrodinggames/rts/game` | 24 | 7757 |
| `com` | 6 | 1080 |
| `com/corrodinggames/rts` | 13 | 658 |

## 三、★ 全量类清单（42 个 ✓）

> `状态`：**我们的字节** ✓ / 原版字节 / 未进交付物 ✓；`字段`/`方法` 为该类**已映射成员数** ✓。

| 混淆类名 | 可读名 | 字段 | 方法 | 状态 |
|---|---|---|---|---|
| `ad` | **NetEngine** | 84 | 115 | 原版字节 |
| `c` | **PacketDecoder** | 48 | 19 | 原版字节 |
| `as` | **PacketWriter** | 5 | 42 | 原版字节 |
| `k` | **InputNetStream** | 7 | 36 | 原版字节 |
| `h` | **ReliableSocket** | 35 | 0 | 原版字节 |
| `g` | **GameServerInfo** | 23 | 2 | **我们的字节** ✓ |
| `ah` | **MatchConfig** | 16 | 7 | **我们的字节** ✓ |
| `ao` | **ServerListener** | 18 | 5 | 原版字节 |
| `aw` | **ByteArrayPacketBuilder** | 4 | 16 | 原版字节 |
| `aq` | **SecurityHasher** | 12 | 6 | **我们的字节** ✓ |
| `ak` | **ChecksumCalculator** | 17 | 0 | **我们的字节** ✓ |
| `r` | **ReliableProfile** | 12 | 0 | **我们的字节** ✓ |
| `an` | **ServerConnector** | 8 | 3 | 原版字节 |
| `e` | **SendWorker** | 6 | 4 | 原版字节 |
| `b` | **ReliableServerSocket** | 9 | 0 | **我们的字节** ✓ |
| `a` | **ChatSystem** | 1 | 8 | **我们的字节** ✓ |
| `at` | **CompressedStream** | 6 | 2 | **我们的字节** ✓ |
| `b` | **ChatMessage** | 6 | 2 | **我们的字节** ✓ |
| `ax` | **TextStream** | 5 | 2 | **我们的字节** ✓ |
| `i` | **TaskRunner** | 4 | 2 | **我们的字节** ✓ |
| `ae` | **PasswordManager** | 6 | 0 | **我们的字节** ✓ |
| `i` | **SocketStats** | 5 | 0 | 原版字节 |
| `aj` | **ServerInfo** | 3 | 2 | **我们的字节** ✓ |
| `ap` | **DosAttemptEntry** | 5 | 0 | **我们的字节** ✓ |
| `au` | **Packet** | 5 | 0 | **我们的字节** ✓ |
| `n` | **WebAPIClient** | 3 | 2 | 原版字节 |
| `f` | — | 4 | 0 | 未进交付物 |
| `o` | **ReliableInputStream** | 4 | 0 | **我们的字节** ✓ |
| `ai` | **GameModeEnum** | 3 | 1 | **我们的字节** ✓ |
| `am` | **TeamAssignMode** | 4 | 0 | **我们的字节** ✓ |
| `l` | — | 3 | 1 | 原版字节 |
| `q$1` | **ServerListLoader$1** | 3 | 1 | **我们的字节** ✓ |
| `q` | **ReliableOutputStream** | 3 | 0 | **我们的字节** ✓ |
| `av` | **KeepAliveTimer** | 3 | 0 | **我们的字节** ✓ |
| `ay` | **SequencePacket** | 2 | 1 | **我们的字节** ✓ |
| `d` | **ReceiveWorker** | 2 | 1 | 原版字节 |
| `w` | **ServerResult** | 3 | 0 | **我们的字节** ✓ |
| `ar` | **NetworkUtils** | 0 | 2 | **我们的字节** ✓ |
| `x` | **ServerStatus** | 2 | 0 | **我们的字节** ✓ |
| `i` | — | 0 | 1 | 未进交付物 |
| `ab` | **MasterServerClient** | 0 | 1 | **我们的字节** ✓ |
| `al` | **ChecksumField** | 1 | 0 | **我们的字节** ✓ |

## 四、关键成员速查（真实可读名 ✓）

| 类 | 成员数 | 可读成员（节选）|
|---|---|---|
| `ad`（NetEngine） | 199 | `addAIToGame(m)` · `addAItoGame(m)` · `assertAllClientsSynced(m)` · `assignTeamSlots(m)` · `banCheckLock(f)` · `bandwidthLimited(f)` · `bindAddress(f)` · `broadcastCommand(m)` · `broadcastToAllIncludingRelay(m)` … |
| `c`（PacketDecoder） | 67 | `assignedSlot(f)` · `clientId(f)` · `clientVersion(f)` · `closeConnection(m)` · `commandHandler(f)` · `commandRateCount(f)` · `commandRateLimited(f)` · `commandRateStartTime(f)` · `connectTime(f)` … |
| `as`（PacketWriter） | 47 | `beginBlock(m)` · `bitCount(f)` · `blockStack(f)` · `byteArrayBuffer(f)` · `close(m)` · `currentStream(f)` · `dataOutputStream(f)` · `do_a(m)` · `endBlock(m)` … |
| `k`（InputNetStream） | 43 | `beginBlock(m)` · `beginBlockChecked(m)` · `beginBlockChecked2(m)` · `blockStack(f)` · `byteStream(f)` · `compressionLevel(f)` · `dataInputStream(f)` · `depthCount(f)` · `getCompressionFlag(m)` … |
| `h`（ReliableSocket） | 35 | `activeConnections(f)` · `autoStart(f)` · `closed(f)` · `closing(f)` · `connected(f)` · `cumulativeAckTimer(f)` · `debugEnabled(f)` · `eventListeners(f)` · `globalDebugFlag(f)` … |
| `g`（GameServerInfo） | 25 | `address(f)` · `countryCode(f)` · `description(f)` · `extraInfoA(f)` · `extraInfoB(f)` · `extraInfoC(f)` · `extraInfoD(f)` · `extraInfoE(f)` · `gameId(f)` … |
| `ah`（MatchConfig） | 23 | `allowCustomUnits(f)` · `allowSpectators(f)` · `cloneSettings(m)` · `disableNukes(f)` · `fogMode(f)` · `gameDuration(f)` · `gameSpeed(f)` · `getString(m)` · `getah(m)` … |
| `ao`（ServerListener） | 23 | `acceptLoop(m)` · `attemptEntries(f)` · `attemptLock(f)` · `connectionLockdownFlag(f)` · `debugLogging(f)` · `ipExcessFlagged(f)` · `isIpAllowed(m)` · `isRunning(f)` · `isUdp(f)` … |

## 五、映射备注（项目分析结论 ✓）

- **`f`**：[Phase3-inferred] type=float[]
- **`f`**：[Phase3-inferred] type=short
- **`i`**："[Phase3-method] returns void"
- **`i`**（TaskRunner）：Cancelled before execution (private boolean)
- **`i`**（TaskRunner）：Has executed at least once (private boolean)
- **`i`**（TaskRunner）：Run as daemon thread (private boolean)
- **`i`**（TaskRunner）：Wait/notify lock object (private Object)
- **`b`**（ReliableServerSocket）：Accept synchronization lock (Object)
- **`b`**（ReliableServerSocket）：Accept thread reference (Thread)
- **`b`**（ReliableServerSocket）：Bound to port flag (boolean)
- **`b`**（ReliableServerSocket）：Connection backlog size (int)
- **`h`**（ReliableSocket）：Active connection list (private ArrayList)
- **`h`**（ReliableSocket）：Auto-start connection on creation (private boolean)
- **`h`**（ReliableSocket）：Background receiver thread (private Thread)

## 核心概念

**网络与联机**：大厅、房间、同步、断线重连、回放。

| 组成 | 说明 |
|---|---|
| **大厅/服务器列表** | 发现、加入、直连 |
| **房间（Battleroom）** | 玩家表、队伍、地图、开局选项 |
| **同步引擎** | 命令广播 + 校验和比对（**不同步检测** ✓） |
| **可靠 UDP** | 自研可靠传输（分包/重传/序号） |
| **回放** | 记录命令流，回放时重演 |

## 关键机制

1. **锁步（lockstep）**：所有端跑同样的命令序列 ⇒ 必须确定性 ✓
2. **校验和**：定期比对状态指纹，不一致即 `desync`
3. **命令广播**：玩家操作 → 广播 → 各端同帧执行
4. **重同步**：desync 后请求全量状态
5. **回放即命令流**：回放文件不含画面，只含命令 ⇒ 体积极小 ✓

★ 本项目**用回放做验收**（V6 ✓）：`don't match` 计数必须为 0。

## 修改指南

| 想改什么 | 改哪里 |
|---|---|
| 联机逻辑 | `gameFramework/j/` 网络类 ⚠️ **改动极易破坏同步** |
| 回放行为 | 回放类 ⇒ **必须跑 V6** ✓ |


## 六、子文档

- [`NETWORK-PROTOCOL.md`](NETWORK-PROTOCOL.md)
- [`NETWORK-STACK.md`](NETWORK-STACK.md)
- [`PERFORMANCE-LAG-ANALYSIS.md`](PERFORMANCE-LAG-ANALYSIS.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓


## 六、子文档

- [`NETWORK-PROTOCOL.md`](NETWORK-PROTOCOL.md)
- [`NETWORK-STACK.md`](NETWORK-STACK.md)
- [`PERFORMANCE-LAG-ANALYSIS.md`](PERFORMANCE-LAG-ANALYSIS.md)

---

> 数字口径见 [../STATUS.md](../STATUS.md) ✓ · 修改后验证见 [../VERIFICATION.md](../VERIFICATION.md) ✓ · 结构与清理规则见 [../STRUCTURE.md](../STRUCTURE.md) ✓
