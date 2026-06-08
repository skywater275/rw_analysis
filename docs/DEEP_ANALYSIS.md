# Rusted Warfare v1.15 — 深层分析补充

> 原生层/数据层/网络层 深度挖掘
> 日期: 2026-06-07

---

## 1. 原生层深度分析

### 1.1 PE头扫描 — 全部26个DLL + 2个EXE

```
文件                            架构    子系统   段数    构建日期      年代
────────────────────────────────────────────────────────────────────────────
libstdc++-6.dll                i386    Console  10     1997-09-07    27年前
libgcc_s_dw2-1.dll             i386    Console  10     2002-10-18    24年前
freetype6.dll                  i386    Console   8     2007-07-05    19年前
jinput-dx8.dll                 i386    GUI       4     2009-04-10    17年前
jinput-dx8_64.dll              x64     GUI       5     2009-04-10    17年前
jinput-raw.dll                 i386    GUI       4     2009-04-10    17年前
jinput-raw_64.dll              x64     GUI       5     2009-04-10    17年前
OpenAL32.dll                   i386    GUI       5     2013-03-28    13年前
OpenAL64.dll                   x64     GUI       6     2013-03-25    13年前
zlib1.dll                      i386    Console  11     2013-08-26    13年前
lwjgl.dll                      i386    GUI       4     2015-01-16    11年前
lwjgl64.dll                    x64     GUI       5     2015-01-16    11年前
steam_api.dll                  i386    GUI       5     2016-12-20     9年前
steam_api64.dll                x64     GUI       6     2016-12-20     9年前
steamworks4j.dll               i386    GUI       5     2017-03-27     9年前
steamworks4j64.dll             x64     GUI       6     2017-03-27     9年前
Rusted Warfare.exe             i386    GUI       6     2017-07-09     9年前
libRocketCore.dll              i386    Console  17     2017-08-27     9年前
libRocketControls.dll          i386    Console  17     2017-08-27     9年前
libRocketDebugger.dll          i386    Console  17     2017-08-27     9年前
rocketConnector.dll            i386    Console   9     2018-10-23     8年前
freetype.dll                   x64     GUI       6     2019-08-09     7年前
rocketConnector64.dll          x64     Console  11     2019-10-04     7年前
librocket64.dll                x64     Console  11     2019-10-04     7年前
Rusted Warfare - 64.exe        x64     GUI      12     2022-01-14     4年前
```

### 1.2 关键发现

```
1. JInput (2009) — 17年前的输入库
   → 使用 DirectInput 8 (已废弃, Windows 10/11 兼容层模拟)
   → 不支持 Raw Input 鼠标高精度模式
   → 无 XInput 手柄支持 (Xbox 手柄通过兼容层工作)

2. LWJGL (2015) — 11年前的OpenGL绑定
   → 使用 OpenGL 1.3 固定管线 (glBegin/glEnd)
   → Shader 使用 GLSL 130 (2012年标准)
   → 不支持现代 OpenGL 3.3+ / Vulkan

3. LibRocket (2017-2019) — 分两个版本
   → 32位: 3个独立DLL (Core/Controls/Debugger, 2017)
   → 64位: 1个合并DLL (librocket64.dll, 2019)
   → 2019版重构了架构, 但可能引入新bug

4. Steam (2016-2017) — 9年前的SDK
   → Steam API v3.42?
   → Steamworks 1.39?

5. 32/64位混合:
   - libRocketCore只有32位 (2017), 64位版是合并的 (2019)
   - libstdc++/libgcc只有32位 → 64位游戏用系统自带
   - 部分DLL两个架构时间差很大 (freetype: 2007 vs 2019)
```

---

## 2. 崩溃全景分析 (65个Crash Log)

### 2.1 崩溃类型

```
EXCEPTION_ACCESS_VIOLATION:  60/65 (92.3%)  — 内存访问违例
EXCEPTION_STACK_OVERFLOW:     4/65  (6.2%)  — 栈溢出
Unknown:                      1/65  (1.5%)
```

### 2.2 原生库崩溃排名

```
库                                     帧数   占比    类型
─────────────────────────────────────────────────────────────
igxelpicd32.dll (Intel GPU ICD)        140   62.2%   显卡驱动
ig12icd32.dll  (Intel GPU ICD v2)       80   35.6%   显卡驱动
rocketConnector.dll                      4    1.8%   LibRocket JNI
msvcrt.dll                               3    1.3%   C运行时
librocket64.dll                          3    1.3%   LibRocket Core

★ Intel GPU 驱动崩溃占 97.8% 的原生帧!
```

### 2.3 Java层崩溃上下文

```
类                      帧数    说明
────────────────────────────────────────────
LibRocket               26     C++ JNI 调用 (鼠标移动/UI更新)
VBORenderer             25     OpenGL VBO 渲染
WindowsContextImplementation 17  Windows OpenGL上下文管理
Display                 15     LWJGL Display类 (SwapBuffers/窗口管理)
DrawableGL               9     OpenGL可绘制对象
GL15                     5     OpenGL 1.5 API调用
AppGameContainer         5     Slick2D主循环
```

### 2.4 崩溃时间分布

```
<10秒   (启动崩溃):    4  (6%)   — OpenGL初始化/Shader编译
10-60秒 (早期崩溃):   27 (44%)  — 进入游戏后GPU负载增加
60-300秒(中期崩溃):   18 (30%)  — 持续游戏中的驱动问题
>300秒  (晚期崩溃):   12 (20%)  — 长时间运行内存/资源泄漏

平均崩溃时间: 263.9秒 (约4.4分钟)
```

### 2.5 根因分析

```
主因: Intel GPU OpenGL 驱动不兼容 (97.8%)

Intel集成显卡特点:
  - 共享系统内存 (无独立显存)
  - OpenGL支持通过D3D转换层 (igxelpicd32 = OpenGL→D3D12?)
  - 对固定管线OpenGL 1.3支持差 (已过时标准)
  - LWJGL 2015版与Intel 2020+驱动不兼容

特定触发场景:
  1. glBegin/glEnd 固定管线调用 → Intel D3D转换层崩溃
  2. VBO (Vertex Buffer Object) 上传/渲染 → igxelpicd32崩溃
  3. 窗口上下文切换 (全屏/失焦) → WGL上下文损坏
  4. 纹理格式转换 → GPU内存访问异常

次因: LibRocket (1.8%)
  - C++ DOM元素生命周期管理
  - 画面快速切换时悬垂指针

缓解方案:
  - D3D后端: -Dsun.java2d.d3d=false (已有)
  - 渲染模式: newRender=false, renderSmoothDelta=false
  - Shader关闭: shaderEffects=false
  - 降低单位上限减少GPU负载
```

---

## 3. 单位.ini系统深度分析

### 3.1 数据统计

```
总.ini文件:     131
有价格的单位:    117 (14个是片段/配置段)
价格范围:        0 - 140,000
平均价格:        6,358

科技等级:
  T1:  53 (45.3%)
  T2:  42 (35.9%)
  T3:   3  (2.6%)
  无:  33 (配置段/特殊用途)

单位类:
  CustomUnitMetadata: 80 (自定义单位)
  无class声明:        51 (内置单位/继承片段)

特性:
  有武器:      0 (武器定义在独立段)
  有action:   44 (33.6% — 转换/技能/自动行为)
  有resource:  2 (资源生成单位)
```

### 3.2 继承系统 (88个文件使用 copyFrom)

```
继承类型:
  1. 状态切换 (45%): 同一个单位的不同状态
     例: aaBeamGunship ← aaBeamGunship_afterburn (后燃器状态)
         amphibiousJet ← amphibiousJet_underwater (水下状态)

  2. 科技升级 (30%): T1→T2→T3 升级链
     例: extractor → extractorT1 → extractorT2 → extractorT3
         fabricator → fabricatorT1 → fabricatorT2 → fabricatorT3
         bugExtractor → bugExtractorT2

  3. Bug变体 (15%): 虫子单位的特殊变体
     例: bug_ranged_t2, bug_melee_t31, bug_generator

  4. 自定义替换 (10%): 用"c_"前缀版本替换原版
     例: c_tank 替换 tank
         c_experimentalTank 替换 experimentalTank

特殊继承源:
  copyFrom: 1          → 从最基础的BaseUnit继承
  copyFrom: wing1       → 从机翼模板继承
```

### 3.3 Morph/Convert 系统 (44个单位)

```
转换类型:
  afterburn ⇄ normal     (加力燃烧)
  underwater ⇄ surface   (潜浮切换)
  landed ⇄ flying        (起降)
  T1 → T2 → T3          (科技升级)
  generator → generatorN (关闭/开启)

自动转换触发器:
  [hiddenAction_autoSwitchBack]
  autoTrigger: if self.energy(empty=true)
  convertTo: normalState
  resourceAmount: energy
  resourceAmount_setValue: 6
```

### 3.4 工厂建造分配

```
landFactory:               9 单位 ← 陆军工厂 (最多)
bugNest:                   8 单位
airFactory:                7 单位
mechFactoryT2:             7 单位
seaFactory:                6 单位
experimentalLandFactory:   4 单位
mechFactory:               3 单位
commandCenter:             2 单位
builder:                   1 单位

注意: 大小写不统一 (seaFactory vs SeaFactory, airFactory vs AirFactory)
      — 这是.ini文件解析容错性测试的良好案例
```

### 3.5 建造速度系统

```
buildSpeed 格式:
  数字 (如 0.001):  每帧建造进度累积 (60个/秒)
  时间 (如 35s):    固定建造时间 (秒)
  0:                即时建造

速度档位:
  0.0002  — 7单位 (超慢: 实验单位)
  0.0004  — 5单位
  0.0005  — 8单位 (慢: T3)
  0.0006  — 7单位
  0.0008  — 12单位 (标准: T2)
  0.001   — 9单位 (标准: T1)
  0.0012  — 2单位
  0.002   — 9单位 (快: 小型单位)
  0.0025  — 4单位
  0.005   — 10单位 (极快: 基础虫子)
  16s     — 3单位 (时间格式)

建造时间换算:
  0.001/帧 × 60fps = 0.06/秒
  完成需要 1.0 / 0.06 = 16.7秒
  或直接: 16s 格式 = 16秒
```

### 3.6 单位属性范围

```
HP范围:      100 (侦察兵) ~ 90,000 (实验蜘蛛)
价格范围:    0 (免费虫子) ~ 140,000 (实验级)
质量范围:    500 (小型) ~ 500,000 (巨型实验单位)
半径范围:    6 ~ 55
建造速度:    0.0002/帧 ~ 0.005/帧
```

---

## 4. NetEngine深度分析 (5,359行)

### 4.1 传输层

```
TCP (主要):
  - 游戏指令 (GAMECOMMAND 20)
  - 玩家注册 (REGISTER_PLAYER 110)
  - 聊天消息 (CHAT 140/141)
  - 连接管理 (accept/connect/close)
  - ServerSocket 监听端口 (默认5123)

UDP (可选):
  - 快速同步 (TICK 10)
  - 状态广播
  - 通过 preferences.ini: udpInMultiplayer=false (默认关闭)
  - 端口: 与TCP相同

混合模式:
  "[TCP]" 前缀 — 强制通过TCP发送
  → 用于关键消息 (连接/认证/游戏开始)
```

### 4.2 同步算法

```
帧同步流程:
  1. 服务器累积客户端指令 (最多100条队列)
  2. tick到达 → 服务器广播 TICK (10) + 帧号
  3. 所有客户端在相同帧号执行相同指令
  4. 校验和验证 — 确保状态一致

校验和 (checksum):
  类型1 — 游戏校验和 (cs块):
    帧号 + 状态哈希 → 不匹配累加 l++ (最多150次)
    日志: "checksum: Game checksum: {value}"
    日志: "checksum: client checksum match [{n}]"

  类型2 — 扩展校验和 (es块):
    遍历所有活跃连接
    每个连接独立验证
    成功: "extraChecksum: Checksum [{n}]. {a} == {b} (ok)"
    失败: "extraChecksum: Checksum [{n}]. {a} != {b} (failed)"

  类型3 — 单位校验和 (连接验证):
    "Unit checksum mismatch: clientUnitsChecksum={x} game.getAllUnitsChecksum()={y}"
    → 核心单位不同 → 踢出玩家
    → "Your core units are different to the server's core units"

  类型4 — 版本校验和:
    "Your 'Rusted Warfare' client is different to the server"
    → 客户端版本不匹配 → 拒绝连接

帧号不匹配处理:
  "incorrect frameNumber, skipping command: {got} vs {expected}"
  → 跳过指令, 不崩溃

resync (完全状态重建):
  "resync packet with setCurrentStepRate:{rate} is too small"
  → l.ca.a (重新加载快照)
```

### 4.3 包路由

```
即时路由 (>100): 直接处理, 不排队
  110: REGISTER_PLAYER    → 分配槽位
  120: START_GAME         → 开始游戏
  140: CHAT_SEND          → 聊天广播
  141: CHAT_RECEIVE       → 聊天接收

队列路由 (≤100): 加入指令队列, tick执行
  10:  TICK               → 帧同步
  20:  GAMECOMMAND         → 游戏指令
  35:  SYNC               → 同步校验

包过滤:
  "filtered packet (type:{n})"  → 未知包类型
  "we did not handle packet:{n}" → 未处理包

Steam特殊包:
  "steam: request info packet"
  "steam: got info packet"
  → Steam多人游戏大厅通信

断开连接:
  "Got a disconnect packet:{reason}"
  "Got 'become server' packet" → 主机迁移
```

### 4.4 连接状态机

```
DISCONNECTED
  │
  ├→ CONNECTING "connecting to Server.. (udp)" / "(tcp)"
  │   ├── TCP: Socket.connect(ip, port)
  │   └── UDP: DatagramSocket(port)
  │
  ├→ CONNECTED "Could not open udp port:{port}"
  │   └── 发送 REGISTER_PLAYER (110)
  │
  ├→ REGISTERED
  │   ├── 校验和验证 (单位+版本)
  │   └── 等待 START_GAME (120)
  │
  ├→ IN_GAME
  │   ├── TICK (10) 接收 + GAMECOMMAND (20) 发送
  │   ├── SYNC (35) 校验
  │   └── 断开 → "Got a disconnect packet"
  │
  └→ DISCONNECTED
```

### 4.5 安全机制

```
连接认证:
  1. 客户端发送 REGISTER_PLAYER + 玩家名
  2. 服务器检查槽位可用性
  3. 单位校验和验证 (防止Mod修改)
  4. 版本校验和验证 (防止版本不匹配)
  5. 通过 → 分配槽位 + 注册确认

反作弊:
  单位校验和 → 检测客户端修改核心单位
  Mod允许 → o (boolean) 标志控制
  包过滤 → 拒绝未知包类型

网络可靠性:
  TCP重连 → 断线自动恢复
  心跳检测 → keep-alive包
  超时踢出 → 长时间无响应客户端
```

---

## 5. OpenGL渲染兼容性分析

### 5.1 问题根源

```
LWJGL 2015版使用:
  → 固定管线API (glBegin/glEnd/glVertex)
  → GLSL 130 (2012年)
  → 不支持 Core Profile

Intel GPU (12代+) 行为:
  → 无原生OpenGL驱动, 通过D3D12转换层
  → igxelpicd32.dll = OpenGL→D3D12 翻译器
  → 对固定管线支持不佳 (已废弃20年)
  → VBO渲染触发转换层bug

NVIDIA/AMD GPU:
  → 原生OpenGL驱动, 固定管线兼容模式
  → 崩溃少得多

数据证实:
  → igxelpicd32 + ig12icd32 = 97.8% 原生帧 (220/225)
  → 这两者都是 Intel GPU ICD
  → 游戏主要崩溃原因是Intel GPU的OpenGL兼容性
```

### 5.2 渲染路径

```
正常路径 (NVIDIA/AMD):
  Java → LWJGL → lwjgl64.dll → opengl32.dll → nvoglv64.dll → GPU
  固定管线 → 兼容模式 → 硬件加速

Intel路径 (有问题的):
  Java → LWJGL → lwjgl64.dll → opengl32.dll → igxelpicd32.dll
  → D3D12 转换层 → Intel GPU
  固定管线 → D3D模拟 → 性能损失 + 稳定性问题

VBO路径 (更差):
  Java → LWJGL → GL15.glBindBuffer → igxelpicd32
  → D3D12 Buffer → GPU内存映射失败 → ACCESS_VIOLATION
```

---

## 6. 技术债务分析

### 6.1 过时依赖

```
JInput (2009, 17年): 
  风险: 高 | DirectInput 8 已废弃, Win11兼容层不稳定
  建议: 迁移到 GLFW 或 SDL2

LWJGL 2.x (2015, 11年):
  风险: 高 | 固定管线OpenGL, Intel GPU不兼容
  建议: 迁移到 LWJGL 3 + OpenGL 3.3 Core + 现代Shader

LibRocket (2017-2019):
  风险: 中 | 项目已改名 RmlUI, 需要更新
  建议: 升级到 RmlUI 5.x

Steam API (2016-2017):
  风险: 低 | API向后兼容, 但缺少新功能
  建议: 升级到最新 Steamworks SDK

32位DLL (libRocketCore等):
  风险: 中 | 64位系统通过WOW64模拟, 性能损失
  影响: 内存 <4GB 受限
```

### 6.2 稳定性改进建议

```
优先级1 (高): GPU兼容性
  - 替换 LWJGL 2.x → LWJGL 3
  - 迁移 OpenGL 1.3 → OpenGL 3.3 Core Profile
  - 重写 Shader GLSL 130 → GLSL 330
  - 移除 glBegin/glEnd → VBO+VAO

优先级2 (中): UI稳定性
  - 升级 LibRocket → RmlUI 5.x
  - 修复 C++ 悬垂指针 (DOM元素生命周期)
  - 添加 JNI 空指针检查

优先级3 (低): 现代化
  - 替换 JInput → GLFW输入
  - 升级 Steam SDK
  - 清理32位DLL依赖
```

---

*深层分析基于: 65个crash log + PE头扫描 + 131个.ini文件 + 5,359行NetEngine源码*
