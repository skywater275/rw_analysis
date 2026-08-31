# 解混淆最终状态 — v8.5

> 5轮深入逆向 | +202类映射 | 61.5%→73.3%可读
> 所有游戏逻辑关键代码已完全可读

---

## 1. 核心指标

| 指标 | 基线(v7.2) | 最终(v8.5) | 提升 |
|------|-----------|-----------|------|
| 类映射 | 221 | **423** | +202 |
| 可读率 | 61.5% | **73.3%** | +11.8pp |
| 内类重命名 | 369 | **377** | +8 |
| extends修复 | 139 | **153** | +14 |
| 导入更新文件 | — | **1048** | — |

---

## 2. 完整系统覆盖

### 23/27包完全解混淆

**12个核心游戏包** (100%):
| 包 | 文件数 | 内容 |
|----|--------|------|
| a/a/a/ | 9 | 可靠UDP数据包类型 |
| a/a/ | 19 | 可靠UDP Socket层 |
| game/a/ | 15 | AI/战斗系统 |
| game/b/ | 11 | 地图/TMX/瓦片集 |
| game/units/a/ | 26 | 单位指令系统 |
| game/units/b/ | 6 | 单位行为 |
| game/units/c/ | 1 | 近战Bug单位 |
| game/units/d/ | 23 | 实验/自定义单位 |
| game/units/e/ | 15 | 建筑类型 |
| game/units/f/ | 10 | 空间查询 |
| game/units/g/ | 5 | 单位组件系统 |
| game/units/h/ | 6 | 工厂操作 |

**11个gameFramework子包** (100%):
| 包 | 文件数 | 内容 |
|----|--------|------|
| a/ | 9 | 音频系统 |
| c/ | 3 | 调试服务器 |
| d/ | 8 | HUD/Overlay |
| e/ | 8 | 文件存储 |
| g/ | 6 | 数据字段 |
| h/ | 2 | 本地化 |
| i/ | 3 | 版本检查 |
| k/ | 17 | A*寻路引擎 |
| l/ | 3 | 性能监控 |
| n/ | 13 | AI系统 |
| o/ | 1 | Steam引擎 |

### 4个剩余包 (平台/渲染代码)

| 包 | 文件数 | 类型 |
|----|--------|------|
| b/ | 38 | GLSL着色器 |
| f/ | 47 | Android UI/Menu |
| j/ | 51 (11残留) | 网络引擎 |
| m/ | 33 (32残留) | OpenGL渲染 |

---

## 3. 发现的关键系统

### 网络栈 (63个类)
- 可靠UDP传输层 (30类): Packet→SYN/FIN/ACK/RST/DAT/EAK/NUL + ReliableSocket
- 主服务器通信 (11类): 列表/创建/更新/移除/自身信息
- 游戏服务器 (7类): NetEngine/ServerListener/Connector/Receive/Send/KeepAlive
- P2P/Steam (3类): SteamSocket/InputStream/OutputStream
- 序列化 (7类): Input/Output/ByteArray/GZIP/Text/Debug/PacketBuilder
- 安全与配置 (5类): 13字段反作弊/认证令牌/对局配置/密码

### A*寻路引擎 (13类)
PathFinder → PathSolver → AStarSearch(PriorityQueue+975节点)
→ PathSolverRunner(后台线程) → AStarNode(Manhattan+对角线) → NodePool(1000预分配)

### 单位指令系统 (26类)
15种指令类型: c_1~c_9 + b/u/s_{id} + c__cut_* + PingType(12种信号)

### 音频系统 (8类)
SoundFactory → Sound → SoundInstance + NullSound + SoundRegistry(26音效) + SoundThread

### HUD系统 (8类)
HUDManager → HUDElement → DrawEffect + CloudRenderer + DrawLayer(5层) + HUDAnchor(9锚点)

### 文件存储 (8类)
StorageBackend → PathStorage/DualStorage/NullStorage + FilePathSanitizer + InputStreamHolder

### 桌面平台层 (java/)
Slick2DRenderer(1669行) + DesktopGameContainer(840行) + SteamManager + SteamWorkshop

### 关键顶层类
- KeyBindings(ac) — 50+键位绑定
- GameRenderer(am) — 522行主游戏循环
- GameSaver(be) — 存档管理器
- GamePhase(bs) — 30阶段游戏循环枚举
- PerformanceTimer(bt) — 作用域性能计时器
- ProjectileManager(ab) — 弹道管理器
- InputProvider(ai) — 抽象输入源

---

## 4. 游戏循环 (30阶段)

```
draw_end → draw_gui → draw_game_effects →
update_game_shouldDraw → update_game_sortRender →
update_do_all_collisions → update_do_all_collisions2 →
update_all_team_and_ai → update_geo_indexes →
update_minimap → update_groupcontroller →
draw_game_unit → draw_setup → draw_setup_fill →
draw_setup_clip → draw_setup_drawMap →
surface_draw → realdraw_in_drawthread →
update_waiting_on_draw → draw_waiting_on_update →
load_total → load_map → load_units → load_compression →
init_total → init_unitcolour
```

---

## 5. 交叉验证 (MASTER_MAPPING.md)

| 引用 | 含义 | 验证 |
|------|------|------|
| j.ad | NetEngine (5358行) | ✅ |
| j.k | InputNetStream | ✅ |
| j.as | OutputNetStream | ✅ |
| fw.k.i | PathSolver | ✅ |
| fw.k.k | PathCostCalc | ✅ |
| fw.ab | ProjectileManager | ✅ |
| fw.d | GameEngine (abstract) | ✅ |
| game.a.a | GameWorld (44KB) | ✅ |
| game.b.b | MapEngine | ✅ |
| game.b.e | TMXMapLoader | ✅ |
| fw.z | GameThread | ✅ |
| fw.m | WorldGenerator | ✅ |

---

## 6. 工具链

- **CFR 0.152** — Java反编译器
- **apply_enhanced.py v2.0** — 6阶段解混淆脚本
- **class-discoveries.csv** — 285+条类映射
- **enhanced-fields.csv** — 331条字段映射 + 75条方法映射
- **mappings.csv / mappings.json / mappings.proguard.txt** — 多种格式映射

### 当前已知限制
- 单字符extends子句无法自动修复 (跨包歧义)
- 方法声明保留混淆名 (重载风险)
- ~343单字符文件在平台/渲染/第三方代码中
