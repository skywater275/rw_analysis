# Rusted Warfare v1.15 — 勘误与修正
> ⚠️ 历史文档 (v10.x 方法论, 2026-08-09) — 当前确定性重建方法学见 [PLAN.md](../deobfuscation/PLAN.md)

> 交叉验证发现的文档错误及修正
> 最后更新: 2026-08-09 (v10.1)
> ⚠️ v19.89 后停更 — 后续勘误直接记录在 STATUS.md 各版本摘要中


> ⚠️ **已归档** (v19.89 起停更 (新勘误入 PENDING/会话记录), 2026-09-04 域归档)


---

## 1. 字段声明位置修正

### 1.1 posX / posY

| 项目 | 错误 | 修正 |
|------|------|------|
| 声明类 | `UnitInstance` (am) | **GameObject** (gameFramework.w) |
| 字段名 | `eo`, `ep` | 在父类 w 上声明，am 继承使用 |
| 影响 | 无法通过 `am.getDeclaredField("eo")` 获取 | 需沿类层级向上搜索 (findFieldInHierarchy) |

```java
// 正确的字段查找方式
Field posXField = findFieldInHierarchy(unit, "eo");  // 在 GameObject(w) 上
Field posYField = findFieldInHierarchy(unit, "ep");  // 在 GameObject(w) 上
```

### 1.2 其他在父类上的字段

| 字段 | 实际声明类 | 用途 |
|------|-----------|------|
| `eo`, `ep` | GameObject (w) | 世界坐标 |
| `eh` | GameObject (w) | 高度层级 |
| `bV` | UnitInstance (am) | 死亡标志 (在 am 上，不在父类) |
| `bX` | UnitInstance (am) | 所属玩家 (声明为 n，运行时为 d 子类) |


## 2. 类名修正

### 2.1 Projectile (game.a.i)

| 文件名 | `Projectile.java` | 实际是 **AI 建造策略节点** |
| 用途 | 不是游戏弹丸 | 管理 AI 建造优先级、状态转换 (early/mid/late) |
| 包 | `game.a` (AI 包) | AI 系统组件，非战斗系统 |

真正的游戏弹丸在 `gameFramework.ab` (ProjectileManager/FormationManager) 中管理。

### 2.2 ProjectileManager (gameFramework.ab)

| 文件名 | `ProjectileManager.java` | 实际是 **FormationManager (编队管理器)** |
| 用途 | 不管理弹丸 | 管理单位编队、路径点分发 |
| 关键方法 | — | `a(y, au)` 将单位加入编队, `b()` 编队完成 |

### 2.3 Recycler (GlobalState.bU)

| 字段名 | `recycler` | 实际是 **PathEngine (路径查找引擎)** |
| 类型 | `k.l` | `gameFramework.k.l` = 路径引擎协调器 |
| 用途 | 不回收对象 | 管理 A* 路径请求 |


## 3. 对象池修正

### 3.1 没有 UnitInstance 对象池

| 假设 | 存在单位对象池/回收器 | **不存在** — 单位通过 `new` 创建，GC 回收 |
| 死亡后 | 假设有回收队列 | 死亡后移除所有引用 → Java GC |

单位创建: `new SpecificUnitType(false)` 在 `ar$N.a(false)` 中
单位清理: `bu()` 移除所有引用 → 不可达 → GC

### 3.2 全局容器

| 容器 | 类型 | 用途 |
| `UnitInstance.bE` | utility.u (动态数组) | 地图上所有单位 |
| `UnitInstance.a` | 追踪列表 | 辅助追踪 |
| `TeamUnitTracker` | game.s | 队伍统计 |
| `SpatialGrid (cc)` | units.f.c | 空间索引 |


## 4. 方法名修正

### 4.1 UnitInstance 方法

| 混淆名 | 实际含义 | 行号 |
|--------|---------|------|
| `ch()` | checkDeath (HP≤0 触发死亡) | 1278 |
| `bv()` | deathOrchestrate (死亡序列入口) | 1327 |
| `bu()` | deathCore (核心死亡操作, 8步) | 1296 |
| `ci()` | quickKill (跳过工厂弹出, 用于核弹) | 1317 |
| `cj()` | markForDeath (设置 HP=-1) | 1323 |
| `a()` | fullCleanup (完全注销) | 593 |
| `e()` | factoryEject (工厂弹出内部单位, 子类覆盖) | 1287 |
| `bS()` | initTurretTargets | 构造时 |
| `bI()` | isExperimental → 返回 `d/d.java:true` | — |
| `dd()` | isBuilding → 返回 `e/c.java:true` | — |


## 5. 收入系统常量修正

| 项目 | 之前值 | 修正 | 来源 |
|------|--------|------|------|
| 收入发放间隔 | ~1.5s (估算) | **90 帧** = 1.5s at 60fps | PlayerState.a(): `an > 90.0f` |
| 迷雾更新间隔 | ~4.3s (估算) | **260 帧** = 4.33s | MapEngine.f(): `ar > 260.0f` |
| 回放 gameVersion | — | **176** (明确) | ReplayEngine: `l2.c(true)` |
| 回放 header_int2 | — | **96** (硬编码) | ReplayEngine line 466 |


## 6. 影响范围

以下文档已受勘误影响，已同步修正:

| 文档 | 修正项 |
|------|--------|
| AI-ARCHITECTURE.md | bU 描述从 "recycler" 改为 "PathEngine" |
| UNIT-LOADING.md | posX/posY 父类标注 |
| COMBAT-COMMAND.md | Projectile 类名说明 (AI策略节点非弹丸), ProjectileManager → FormationManager |
| CROSS-VALIDATION.md | 所有修正已同步 |


## 8. v10.x Phase 1-6 修复记录 (2026-08)

### 8.1 MovementController.java 关键字冲突

| apply_enhanced.py `_replace_method` | 方法名 `a` 被重命名为 Java 关键字 `float` | 添加 `_is_safe_name()` 关键字防护, 修复 21 处 `float()` → 正确名称 |

### 8.2 cross_validate_bytecode.py 幻影归因 bug

| 降级匹配逻辑 | 仅按简单名后缀匹配 (`.y`, `.am`) 导致跨包误归因 | 改为要求最后2段相同 (父包+类名), 新增 fallback_matches 计数器 |

### 8.3 GAME-ACTION-METHOD-MAPPING.md 枚举字母

| ActionTargetType / ActionCategory | 文档中 `u`(13值) 和 `t`(9值) 的类字母标反 | 按实际源码修正: `u`=ActionCategory, `t`=ActionTargetType |

### 8.4 文档统计不一致

| 项目 | v9.47 声明 | v10.1 实际 |
|------|-----------|-----------|
| extends/implements | "100%" | 151 处残留 (gameFramework/m:73, game/a:19, 其他:59) |
| 字段覆盖率 | — | 74.1% |
| 方法覆盖率 | — | 69.2% (action 方法无法字节码验证) |
| supplement.csv | "3,495字段/820方法" | 5,807映射 (3,922字段/1,885方法) |

### 8.5 SettingsEngine 解混淆状态

| 文档/计划声称 | "bQ = SettingsEngine, 136成员 @ 0%覆盖率" | bQ.class 不存在; SettingsEngine 已命名完毕 (127字段为 SharedPreferences 键值); bq (2成员) 才是真正的未映射串行化回调类 |

### 8.6 v1005_render.py / v1003_overloads.py 空模板

两个脚本被提交为完整工具但实际是空模板 (无映射数据)。已删除，渲染链映射标记为后续工作。

### 8.7 v19.86 映射库损伤修复 (2026-08-16)

本次"562 映射"任务中发现并修复 4 个映射库损伤:

1. **main-deobf 工具截断 CSV** (b58118e2): main_class_mappings.py 重写 class-discoveries.csv 时未用 csv 模块 (违反 M4), ~30 行 notes 在逗号处被截断 (如 "slot=-99, lobby placeholder" → "slot=-99"), 连带丢失 real_pkg 注释。已从父版本恢复 + 重放 6 条有意修正 + 5 条新增。
2. **Agent C 直接写生成文件**: aicore 24 条身份映射直接写入 identity-index.json (生成产物) 而未写入源 CSV — 索引重建后丢失 11 条 (MissionEvent$1-11)。已固化 25 条到 class-discoveries.csv。教训: 生成文件永不作持久化载体。
3. **org.a.a.c 误标** (Phase 4.2): 被标为 LibRocketElement/RocketDocument/RocketElement, 实为 joda-primitives 的 FloatIterator/IntIterator/PrimitiveIterator (空接口 extends java.util.Iterator)。已修正。
4. **org.a.a.* 包错改名**: 03 的 org 子树被错误改名为 org.network.reliableudp.* + 语义类名, 包/文件路径错配致编译错误。已从 02 原稿恢复 19 文件, 编译错误 -23 (25,071 → 25,048)。

### 8.8 v19.89 循环十二根因修复 (2026-08-16)

字节码真型修复 3 文件 -83 错误 (javap 锚定):
1. **InGameUI.bY/bZ**: 03 声明 `ResourceDomainEnum bY = new MinimapUnit()` — 字节码 f.g 真型 `utility.u` (UnitRegistry, AbstractList)。连带 clearActionPanel 返回类型 + 调用点 (MinimapUnit 为 f.u, 非列表类)。
2. **UnitInstance 字段**: bE 声明 TimedBomb → 真型 utility.u (UnitRegistry, static final); 静态字段 a 声明 UnitBehaviorEnum → 真型 utility.o (DequeList); dH 及 3 个方法返回类型 WaterUnit → 真型 custom.e.f (EffectManager); av.×12 → WeaponTypeEnum (fwd+rev 确认 units.av)。
3. **InputNetStream.o()/r()**: o() 声明 ConnectionState → 真型 units.am (UnitInstance); r() 声明 WebAPIClient → 真型 game.n (PlayerState)。根源: 语义名广播把 ConnectionState (枚举) 当流方法返回类型。
4. 未修 (下轮): GameUtils 125 处 cannot-find-symbol (语义名广播: formatDuration/md5Hex/UIScrollBar$a 全错位) + LocalizedString.b() + ThemePaint/CameraMode。
