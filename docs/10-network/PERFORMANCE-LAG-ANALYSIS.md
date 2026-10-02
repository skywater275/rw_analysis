# 多人联机卡顿/延迟/不同步成因分析 — 锁步帧同步与路径求解阻塞链路

> v19.133f98 追加 | 2026-09-21 | 从 GameEngine 主循环 / NetEngine / PathFinder / ChecksumCalculator 四段源码追踪"大量单位操作 → 卡顿 / 延迟 / desync"的完整因果链
> ⚠️ 命名时点: 本文直接引用 **03-deobfuscated** 侧语义化类名（NetEngine/PathFinder/ChecksumCalculator 等），行号以 03 侧文件为准；与 02 混淆名的对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)

---

## 0. 结论摘要

Rusted Warfare 联机采用**确定性锁步（deterministic lockstep）+ 权威服务器**模型：客户端只上传"指令"，不上传单位状态；所有端在**同一个帧号**执行**同一批指令**，靠周期校验和发现分叉。

在这个模型下，**卡顿不是"网络带宽不够"，而是"某一端主线程算不完/等不到，整局帧号就不推进"**。三条独立的阻塞源都会让 `GameEngine` 主动放弃推进本帧：

| # | 阻塞源 | 判定点 | 后果 |
|---|--------|--------|------|
| A | 路径求解未完成 | `PathFinder.e()` → `NetEngine.shouldGameBePaused()` | 本渲染帧完全不推进游戏帧（硬卡顿） |
| B | 服务器批次包未到 | `NetEngine.Y`（`bx >= X` 时置位） | 客户端停在 `X` 帧等包（网络卡顿） |
| C | 校验和周期全表扫描 | `ChecksumCalculator.b()` 遍历全部单位 | 每约 300 帧一次毫秒级尖峰 |

而"大量单位操作"恰好同时放大 A、B、C 三条：一次框选 N 个单位下达移动/攻击指令 = N 条路径请求，而**全局只有 1~2 个路径求解线程**。

---

## 1. 数据处理总模型

### 1.1 关键字段（NetEngine）

| 03 字段 | 含义 | 初值/证据 |
|---------|------|-----------|
| `bx` | 当前游戏帧号（GlobalState 侧） | 每推进一帧 `GameEngine.a()` 内 `++` |
| `X` | **nextBlockingFrame** — 本端允许推进到的最大帧 | 初始 0（`NetEngine.java:666`） |
| `Q` | 前看窗口 lookahead，服务器提前多少帧发包 | **10**（`NetEngine.java:672`） |
| `R` | 滞后保护量 | 0（`NetEngine.java:673`） |
| `bI` / `c()` | 每帧步长（step rate），可被服务器改写 | 1.0f（`NetEngine.java:338-343`） |
| `Y` | "本端已到阻塞帧"标志 | `bx >= X` 时置 true（`NetEngine.java:1559`） |
| `ai` | 校验和刷新间隔（帧） | 300（`NetEngine.java:164`） |
| `am` | 校验和计算器 | `ChecksumCalculator` |

### 1.2 一帧的完整时序（`GameEngine.java:1157-1230`）

```java
if (this.bX.B) {                        // B = 处于联机对局
    float f8 = f2;
    if (this.cb.v != 1) f8 *= this.cb.v;
    this.bX.c(f8);                      // ① 网络层更新：收包/发指令/刷新校验和
    if (!this.a(true) && !this.bX.Y) {
        this.stepAccumulator += f8;
        while (this.stepAccumulator > this.bX.c()) {          // 按固定步长消耗时间
            if (this.bX.shouldGameBePaused()) {               // ② A 类阻塞：路径没算完
                this.bX.Y = true; break;
            }
            this.stepAccumulator -= this.bX.c();
            this.bX.a(this.bX.c(), false);                    // 网络帧钩子
            if (this.bX.Y) break;                             // ③ B 类阻塞：没收到批次包
            this.a(this.bX.c());                              // ④ 真正推进游戏世界一帧
        }
        ... // 帧追赶逻辑，见 §4.3
    }
}
```

要点：**游戏帧推进被包在 `while` 里，两次 `break` 都意味着"这一帧不模拟"**。渲染照常（画面还在画），但世界冻结 → 玩家感知为卡顿/停顿。

### 1.3 线上数据（指令，而非状态）

- 客户端 → 服务器：包 **20**（`CLIENT_CMD`），载荷 = `Command.a(OutputNetStream)` 序列化结果（`NetEngine.java:1397-1404`）。
- 服务器 → 全体：包 **10**（`TICK_BATCH`）＝ `[nextBlockingFrame][命令数][命令…]`（`NetEngine.java:1350-1370`）。
- 玩家状态包（≤100）在 tick 循环排队处理，系统包（>100，如 108 Ping/110 注册）在接收线程立即处理。
- **一个 `Command` 可携带一整批单位 ID**（`Command.java:187` 写入 `v.size() + A.size()` 个单位），所以"框选 100 个单位下一个命令"在网络上仍是一条指令、体积不大 —— **瓶颈不在带宽，在本机确定性模拟**。

---

## 2. 卡顿成因

### 2.1 A 类：路径求解阻塞游戏帧（最主要）

**证据链一：帧推进前的硬闸门**

```java
// GameEngine.java:1166
if (this.bX.shouldGameBePaused()) { this.bX.Y = true; break; }
```

`shouldGameBePaused()`（`NetEngine.java:1537-1551`）只做一件事：

```java
if (l2.bU.e()) { ...return true; }      // bU = PathFinder（GameEngine.java:406）
```

`PathFinder.e()`（`PathFinder.java:416-422`）扫描进行中路径列表 `I`，只要存在 `t <= 0` 且尚未解出的路径就返回 true —— **整局帧号停在这里**。

**证据链二：求解线程数量固定为 1~2**

```java
// PathFinder.java:270-283（构造器）
this.H.add(new PathSolverRunner(this));
int n2 = GameUtils.c();                 // CPU 核心数
if (n2 > 1) this.H.add(new PathSolverRunner(this));   // 只多加一个
```

无论 8 核还是 32 核，**最多两个** `PathSolver-N` 线程（`PathSolverRunner.java:136-145`，`setPriority(10)`、daemon）。
且每个求解器**一次只能算一条路径**：

```java
// PathSolverRunner.java:59-67
if (!this.s) throw new RuntimeException("setupNewPath: last path not yet finished");
this.s = false;
```

**证据链三：请求量与等待预算脱节**

`PathFinder.a(k2, bl, bl2)`（`PathFinder.java:323-368`）为每条路径按距离设置超时预算 `t`：

| 起终点距离差 | 预算 t（帧时间单位） |
|---|---|
| < 15 / 50 / 200 / 400 / 1000 / 2000 px | 12 / 16 / 24 / 50 / 100 / 200 |
| 单机非网络（`!bX.B && !cb.i()`） | 180 或 360（更宽松） |

预算耗尽而路径未解出时，`PathFinder.d(float)`（`PathFinder.java:445-470`）会走到：

```java
if (!k2.c()) {
    if (GlobalState.B().isNetworkedOrReplay())
        GlobalState.b("PathEngine", "updateUnfinishedPaths: path wasn't solved, isGoingToBlockThisFrame did not protect");
    this.a(k2);
}
```

这条日志是**联机卡顿与潜在不同步的直接指纹**：说明本该"阻塞帧等路径"的保护没赶上，路径结果将在之后的帧才生效 → 模拟分叉。

**为什么"大量单位操作"最致命**：一次移动/攻击指令让 N 个单位各自提交路径请求，队列进入 1~2 个求解器串行处理；求解未完成期间 `shouldGameBePaused()` 持续为 true，主循环**每帧都 break**，直到队列消化完。表现为"下令后整场卡住零点几秒到数秒"。

### 2.2 B 类：网络等待阻塞（锁步固有）

**服务器侧**（`NetEngine.java:1316-1370`）只有在自己帧号追上阻塞帧时才发包：

```java
if (l2.bx >= this.X - this.R && !bl) {
    n3 = this.X + this.Q;              // 目标帧 = 当前阻塞帧 + 10
    ...                                // 只收集 e2.c == this.X 的指令
    object = ((OutputNetStream) object2).b(10);   // 打包类型 10
    this.sendIncorrectPassword((NetworkPacket) object);
    this.X = n3;                       // 阻塞帧前移到 +10
}
```

**客户端侧**（`NetEngine.java:1458-1460` / `1553-1564`）：

```java
if (l2.bx < this.X) this.Y = false;            // 还没到阻塞帧 → 不阻塞
...
public void registerRelayServer(float f2, boolean bl) {
    if (l2.bx >= this.X) {                     // 到了阻塞帧
        if (l2.bx > this.X) throw new RuntimeException(...);   // 越界即崩
        this.Y = true;                          // 必须等下一批包
    }
    ...
}
```

因此客户端的推进上限就是服务器上一次广播的 `X`。**服务器不发包（因为它自己卡在 A 类，或网络抖动丢包、重传），全部客户端一起停在 `X`。**
房主作为服务器时，这条链路意味着"**房主一卡，全场卡**"。

可靠 UDP 侧的重传参数进一步放大抖动（见 [NETWORK-STACK.md](NETWORK-STACK.md) §1.2）：`retransmissionTimeout=600ms`、`maxSegmentSize=300` 字节 —— 包 10 一旦丢失，最坏要等约 600ms 重传，客户端在此期间只能停帧。

### 2.3 C 类：校验和周期尖峰

```java
// ChecksumCalculator.java:39-83
public void b() {
    this.a = 0L; this.a();                       // 清零
    for (GameObject bq2 : GameObject.er) {       // ★ 遍历全部游戏对象
        if (!(bq2 instanceof UnitType)) continue;
        UnitType y2 = (UnitType) bq2;
        ... // 位置 x/y、朝向、HP、ID、CommandCenter 字段
        this.j.b += y2.computePathHash();        // ★ 再遍历该单位全部航路点
    }
    for (int i2 = 0; i2 < PlayerState.c; ++i2) { ... }   // 队伍资金/单位数
}
```

`computePathHash()`（`UnitType.java:4010-4019`）对该单位 `aw` 个 `UnitTransform` 逐点做 `Float.floatToRawIntBits` 累加。
即 **单次校验和成本 = O(单位数 × 平均航路点数)**，且无任何增量缓存。

触发侧（`NetEngine.java:345-350`、`1501-1535`）：

```java
public void sendIncorrectPassword() {          // 实际作用 = 刷新校验和帧
    this.resyncStageCounter = l2.bx;
    this.am.b();                               // ★ 全表重算
    this.an = false;
}
...
if (this.B && (this.resyncStageCounter + this.ai < l2.bx || this.resyncStageCounter == -1)) {
    this.sendIncorrectPassword();              // 每 ai = 300 帧刷新
}
if (this.C && !this.an && this.resyncStageCounter + this.ai / 2 < l2.bx && ...) {
    ... 发送包 30（帧号 + 总校验和 + 逐字段校验和）// 服务器每 150 帧比对一次
}
```

规模 2000+ 单位时，这一次全表扫描形成可观测的周期性掉帧（约 5 秒一次），也是**不同步误判/漏判**的窗口：校验和本身用 `float` 累加再转 `long`（`this.a = (long)((float)this.a + ...)`），有效精度只有 24 位尾数，大数量级下会丢失低位差异。

### 2.4 D 类：每帧常态 O(N) 负载

| 每帧动作 | 位置 | 复杂度 |
|---|---|---|
| 逐单位更新（移动/武器/计时器/AI） | `GameEngine.a(float)` 单位循环 | O(单位数) |
| 空间网格重建 | `GameEngine.java:1430` → `SpatialGrid.a()`（`SpatialGrid.java:288-298`） | 遍历 `UnitInstance.bE` 全部单位，逐单位判断是否换格/换队 |
| AI（三层时钟 + 分组） | `GameEngine` 单位/世界更新段 | O(单位数 + 队伍) |
| 路径引擎帧推进 | `GameEngine.java:1264` → `PathFinder.a(float)` | O(进行中路径数) |

这一层在单机同样存在，是"单位数一多就掉帧"的基线；联机只是在这之上叠加了 A/B/C 三条**硬阻塞**。

---

## 3. 延迟成因

1. **前看窗口固定 10 帧**（`Q`，`NetEngine.java:672`）：服务器必须提前 10 帧广播。60fps 下 = 166ms 的**结构性输入延迟下限**，与网络好坏无关。
2. **指令要等下一批**：客户端包 20 到达服务器后，只有当服务器推进到 `e2.c == this.X` 的那一帧才被打包进包 10（`NetEngine.java:1354-1362`），再广播回来。一次操作生效 ≥ 半个 RTT + 若干帧。
3. **服务器只在自身帧推进时发包**（`1316`）：服务器端一帧算得慢 → 发包节奏整体变慢 → 所有客户端输入延迟同步变大。
4. **可靠 UDP 重传**：丢包时 `retransmissionTimeout=600ms`，期间阻塞帧不前进。
5. **帧追赶制造"加速"而非"降低延迟"**：落后时游戏选择快进（见 §4.3），玩家的输入延迟并不会变小，只是画面追上进度。

---

## 4. "平行/不平滑/不同步"的三种具体表现

中文语境下"游戏平行"通常指下列之一，源码里对应三套不同机制：

### 4.1 画面不平滑、跳帧、忽快忽慢 —— 帧追赶（fast-forward）

`GameEngine.java:1175-1229` 按落后幅度分级处理：

```java
if (!this.bX.ad && this.bx < this.bX.X - this.bX.Q - 10) {   // 落后 20 帧
    this.bX.sendIncorrectPassword("we are slightly out of frame range, speeding up");
    this.bX.ad = true;
}
if (!this.bX.af && this.bx < this.bX.X - this.bX.Q - 30) {   // 落后 40 帧
    this.bX.sendIncorrectPassword("we are out of frame range, fast forwarding (" + this.bx + "->" + this.bX.X + ")");
    this.bX.af = true;
}
if (this.bX.af) { this.bX.a(this.bX.c(), true); this.a(this.bX.c()); }   // 每渲染帧多跑一整个游戏帧
if (this.bx < this.bX.X - 90)  { ... this.a(this.bX.c()); }              // 落后 90 帧：再多跑
if (this.bx < this.bX.X - 120) { ... this.a(this.bX.c()); }              // 落后 120 帧
if (this.bx < this.bX.X - 600) { ... this.a(this.bX.c()); }              // 落后 600 帧
```

同一渲染帧内跑多个游戏帧 = **画面时间流速突然变快**，同时 A/B 类阻塞又让画面突然冻结。二者交替出现，就是玩家描述的"一卡一卡、忽快忽慢、不平滑"。

回放/单机路径则用 `stepAccumulator` 固定步长推进（`GameEngine.java:1231-1253`），并限制 `stepAccumulator ≤ 100`，不会出现这种跳变。

### 4.2 不同步（desync）—— 校验和分叉与重同步

- 检测：服务器每 `ai/2`（150 帧）接受一次校验和比对，客户端回包 **31**（匹配? + 双方校验值 + 逐字段状态）；日志形如 `--- Desync for frame: N ---`、`client:X desync [N]`、`desync_count:N`（`NetEngine.java:1700-1790`）。
- 处置：`pauseOnDesync` 生效则暂停整局；否则 `quickResync()`（`NetEngine.java:910-945`）/ 包 **35** 用 gzip 传输完整 `gameSave` 覆盖状态，然后 `this.X = n4 + 1`、`resyncStageCounter = X + 1` 回到同一轨道继续。
- 根因来源（源码可指认的三处）：
  1. §2.1 的 `"path wasn't solved, isGoingToBlockThisFrame did not protect"` —— 路径结果晚一帧生效；
  2. §2.3 的 `float` 累加精度不足；
  3. 跨平台浮点差异（类级 `strictfp` 已声明，但反序列化/压缩路径与自定义单位 mod 逻辑仍可能破例）。

### 4.3 平行 = 并行计算 —— 求解器并行度只有 2

若"平行"指并行：`PathFinder` 构造时**按核心数只决定"1 个还是 2 个"求解器**（`PathFinder.java:273-279`），日志会打印 `We have N cores, creating extra solvers` 或 `We only have one core, using single solver`。多核机器上路径吞吐**不随核心数扩展**，这是大规模单位操作时 A 类阻塞被放大的直接原因。

---

## 5. 因果链一句话总结

```
玩家框选 500 单位下达移动
  → 500 条 PathCostCalculator 请求（客户端本地生成）
  → 服务器/各客户端主线程 before-frame 闸门 shouldGameBePaused() == true
  → GameEngine while 循环每帧 break，游戏帧号 bx 不推进
  → 服务器不发包 10（X 不变）→ 全体客户端 bx 也停（Y = true）
  → 表现为：下令瞬间全场冻结 → 队列消化后由帧追赶快进补回 → 画面不平滑
  → 若某个客户端在此期间路径结果晚帧生效 → 校验和不一致 → desync → 暂停/重同步
```

**核心矛盾**：确定性锁步要求"同一帧、同一批指令、同一结果"，因此路径求解**不能跨帧延迟返回**（否则分叉），只能**阻塞帧**等待完成；而求解线程数被硬编码为 ≤2，于是"大量单位操作"把确定性正确地变成了全局停顿。

---

## 6. 可验证的观测点（实验方法）

| 现象 | 日志/观测锚点 |
|---|---|
| 路径保护失效 | `PathEngine` 域: `updateUnfinishedPaths: path wasn't solved, isGoingToBlockThisFrame did not protect` |
| 帧追赶 | `we are slightly out of frame range, speeding up` / `we are out of frame range, fast forwarding (a->b)` |
| 阻塞进出 | `shouldGameBePaused: isGoingToBlockThisFrame()==true/false: <PathFinder.f() 摘要>`（含 `total:N [distance:…, allowedDelay:…, lowPriority:…]`） |
| 求解器并行度 | `PathEngine: We have N cores, creating extra solvers` / `We only have one core, using single solver` |
| 校验和节奏 | `Sent checksum to client [frame]`、`--- Desync for frame: N ---`、`client:X desync [N]`、`desync_count:N` |
| 网络接收积压 | `Receiving network data: <已收>/<总量>`（`NetEngine.java:1488-1496`） |

> 注：以上日志走 `GlobalState.e()/b()` 通道，headless 回放时写入 `-log` 文件；按 MEMORY 记录的两条硬陷阱（`loadReplay` 需等 GUI 就绪、debugSocket 激活时崩溃不写 `crashes.txt`），解析请以 `-log` 为准。

---

## 7. 优化切入点（若做逆向改造/Mod）

| 优先级 | 切入点 | 位置 | 说明 |
|---|---|---|---|
| 高 | 求解器数量解绑 | `PathFinder.java:273-279` | 把"1 或 2"改为按核数/负载动态扩展（需保持结果确定性：同批请求分配策略必须全端一致） |
| 高 | 批量路径共享 | `PathFinder.a(PathCostCalculator, boolean, boolean)` | 同目标点/同编队的请求复用一条解（RW 已用 `cacheEnabled`/`lowPriority` 部分做，可加同目标去重） |
| 中 | 校验和增量维护 | `ChecksumCalculator.b()` | 改为单位级脏标记 + 增量累加，避免周期性全表扫描 |
| 中 | 指令帧预算 | `NetEngine.java:1354-1362` | 服务器打包时对单帧命令数设上限，避免单帧超载 |
| 低 | 追赶分级调参 | `GameEngine.java:1181-1228` | 落后的 6/10/30/90/120/600 帧档位可调，减少视觉跳变 |

> 注意：任何改动路径求解顺序/线程数都会改变确定性执行顺序，**必须全端一致**，否则直接触发 desync 检测。

---

## 8. 交叉引用

- 协议与包类型: [NETWORK-PROTOCOL.md](NETWORK-PROTOCOL.md)
- 可靠 UDP 与网络栈: [NETWORK-STACK.md](NETWORK-STACK.md)
- 主循环与定时器: [../07-engine/GAMELOOP.md](../07-engine/GAMELOOP.md)
- 寻路与空间网格: [../10-pathfinding/PATHFIND.md](../06-world/PATHFIND.md) / [../10-pathfinding/SPATIAL.md](../06-world/SPATIAL.md)
- 指令序列化: [../03-actions/COMMAND-SERIAL.md](../02-unit-actions/COMMAND-SERIAL.md)
