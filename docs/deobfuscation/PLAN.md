# PLAN.md — Rusted Warfare v1.15 源码逆向工程路线图

> v3.23 | 2026-09-04 | **Phase B B4 运行验证完成** — 反向 jar (game-lib-reverse.jar) 替换 game-lib.jar 后游戏 headless 启动 0 异常, 调试服务器 ping→pong, 回放加载成功, AI 活动正常 (运行时修复 15 轮); 里程碑链: B1 编译清零 (41,402→0) → B2 反向映射核对 (0 缺口) → B3 全量构建 (0 错误) → B4 可运行; **B5 行为一致性收敛完成** (运行时反馈驱动 10 项修复, 启动 0 崩溃 + 功能全通); **B5.5 覆盖率探究: JDK17 双 jar 方案证伪** (类包同名深包=javac 绝对硬限制, 覆盖天花板 ~15%);
> 战役历史详见 PHASE-A-会话历史总览.md 与 sessions/ 各战役记录

---
## 目标

**使 RustedWarfare/ 以未混淆的源码状态运行**

---

## Phase A 瓶颈诊断 (为什么旧路线失败)

03-deobfuscated/ 卡在 **~5,006 个 javac 错误**,陷入"级联-阻断循环"(javac 在第一个语法错误处停报 → 修复 → 下一层又出现约 5,000 个错误,交替反复)。

### 根因 (四个探索代理的完整调查结论)

| # | 根因 | 证据 | 影响 |
|---|------|------|------|
| 1 | **启发式消歧不可靠** | fix_javac_symbols.py 按包前缀打分选单字符类名候选,约 1,940 处误重命名 (如 librocket `l` 错选 HoverUnit,应为 GlobalState) | ~800 处类型引用错名/漏改 |
| 2 | **5 个真丢失类** | BaseUnit (02源 5,131行), ShaderEffect (591行), BitmapTexture (34行错误占位顶替), PacketDecoder (368行), StopAction (49 文件用旧包名) | ~600 处直接引用 + 大量成员级联 |
| 3 | **package_renamer 应用不完整** | GlobalState.java 自身第 12-14 行仍 import 旧混淆包 (`gameFramework.l.PlatformBackend` 等) → 自身编译失败 | 37 处 GlobalState 级联 |
| 4 | **成员访问级联** | eo/ep (各96) / g() (98) / bR / bX 等 — 宿主是编译失败的父类 (UnitInstance 706错, MovementController 826错, PlayerState 481错) | ~2,000 处 |
| 5 | **LogicBoolean$Parameter 嵌套错配** | 字节码证实为顶层类 (无 InnerClasses 属性), 但 154 处引用方写 `@LogicBoolean.LogicBoolean$Parameter` | 154 处 |

### 错误构成 (历史诊断: 5,006, v19.0 时点)

| 类型 | 数量 | 占比 |
|------|------|------|
| cannot find symbol | 4,201 | 84% (class 2,156 / variable 1,296 / method 749) |
| incompatible types | 256 | 5.1% |
| not public (包外访问) | 222 | 4.4% |
| method does not override | 63 | 1.3% |
| name clash (泛型擦除) | 35 | 0.7% |
| dup-def | 16 | 0.3% |
| 其他 | ~213 | 4.3% |

错误集中在 4 个根因文件 (2,266 条/45%): MovementController 826, UnitInstance 706, PlayerState 481, ModUnitRegistry 253。

### 已证伪的路线

- **apply_enhanced_rebuild 分支的 "403→2 errors" 是假象**: javac_gate 自动桩(stubs)掩盖了真实错误 + 471 个 `_pkg` 双副本混乱 + 误删 b.ac 等原始类。**该分支冻结保留,不投入工作**。
- **启发式修复器** (fix_javac_symbols.py 包前缀打分): 无字节码依据的猜测,已造成误改。**废弃**。

---

## 新策略: 确定性重建

### 三层真相源

```
T0 字节码真相   game-lib.jar (1,698 class 全量) + 05-gamelib 解包 (同内容, 直接 javap 源) + 01-classes (388) + libs/*.jar (24)
T1 命名映射     fwd 前向表 (混淆FQN → 意义FQN, 1,063条): class-discoveries.csv (含real_pkg:注释)
                + mappings.csv (class行) + mappings.json['classes'] 三源合并, 以 jar 文件存在性校验
                + PKG_MAP (43包) + 补录映射 (gameFramework.l→core 等)
T2 源码锚点     02-decompiled (混淆名原稿, 1,698个.java 与 jar 一一对应, 完整性已由 05-gamelib 比对验证)
                为"未损坏的原稿"; 03-deobfuscated 为"待修复的当前态"
```

### 铁律

> 每个重命名决策必须由 **T0 常量池证明**(该文件字节码确实引用此混淆类) + **T1 映射表**给出名字,二者缺一则该处保留原样并写入 audit 报告。**禁止任何按包前缀/编辑距离的猜测。**

### 动态测试策略 (v19.90-19.105 十五轮迭代, 回放播放+保存双平台 + 六轮辐射批次)

静态修复边际收益递减后的**新证据源: 直接启动游戏采集运行时证据 + 回放驱动全模块** (docs/deobfuscation/DYNAMIC-TESTING.md):

**已建成能力**:
- **headless 启动**: jvm64 JDK13 + -nodisplay + 真实安装 java.exe (项目副本 jvm 缺 exe); 缺资产链修复记录 (translations/shaders/gui-RML/maps/tilesets)
- **脚本驱动**: `-debug 5677:x` (启动服务器, 字节码证实) + -debugscript <脚本文件> (排队脚本) → DebugServer (ping/crash/script/function 四命令) → ScriptEngine → root.loadReplay — **headless 回放播放**; tools/utils/debug_script.py 客户端 (动态发送)
- **JFR 采样**: 零插桩方法采样 + 异常率; 回放播放期 = 全模块模拟 (game.i 60%+, UnitInstance, Ogg 音频)
- **回放命令流解析器** (tools/utils/replay_parser.py): **14,184 条真实命令** (v19.97 d 子对象修复后, r1-r5) + 2,589 校验和 — 网络/命令管线真实数据验证源
- **回放保存平台** (v19.96): 脚本链 hostStart→mp.multiplayerStart 新游戏 → 自动录制 (ba.a(boolean), 单机 B=true) → debug.createUnit/moveAllUnitsOnTeam 命令注入 → disconnect 保存 — **录制↔解析闭环**
- **运行时互证实例**: MapException (game.b.f 日志↔fwd), ModLoader (Mods data loaded 阶段), 命令执行日志↔解析器 (Command: unset count:2 ↔ 动作名"-1"/argCount=2), es 扩展校验和 (帧 0 全匹配 = 确定性基线)

**已解开的坑**: CJK 文件名 → ASCII 副本绕过; **mods 启用之谜 = 安全模式** (强杀累积 numIncompleteLoadAttempts>1 → ee → bZ.g() 全禁用; 启动前清零计数器); -debug 使地图错误致命 (automated testing); **game.i = GameEngine 已仲裁** (v19.98, 三候选撤销)

**辐射批次方法论 (v19.100-19.105 新增, 六轮 +610 映射)**:
- 锚点辐射: 五基础已映射类 (GameEngine/ReplayEngine/ModEngine/MapEngine/UnitRegistry) → javap 字节码调用图逐层向外 (第二层: 每帧方法调用目标; 第三层: Packet 读写协议)
- 并行 agent 流水线: 8 个 agent (A-H) 分区映射 → 独立 CSV 清单 → 主线程合并 (四元组去重 + evidence_weak 降级 unverified)
- 动态日志交叉验证: 20+ 历史运行日志 grep 新映射类字符串 → verified 升级 (PathEngine 20核/FogMapRenderer/TimeUtils/Packet 运行时)
- 错误映射批量修正: 40+ 条 sig-backfill/RWX-main batch-inferred 历史错误 (calculateChecksum/packetType/isAirUnit 等) 全部 T0 推翻修正
- 总管理器架构: GlobalState (l) 171字段单例 = 对象总管理器 / GameEngine (i) init = 构造器总管理器 / ScriptEngine methods HashMap = 唯一函数注册表

### 编译器策略: 游戏自带 JDK 13

**关键发现**: `RustedWarfare/jvm64/` 是游戏自带的**完整 OpenJDK 13** (javac 13 + javap 13, build 13+33) — 正是游戏 64 位版的实际运行时。`RustedWarfare/jvm/` 是 Java 8 JRE (32位, 无编译器)。

- **主编译器 = 游戏自带 javac 13** (字节码版本与游戏运行时精确匹配; 且可能对 ProGuard 剥离 InnerClasses 属性后的 `import X$Y;` 简单名解析更宽松 — R0 实测确认)
- javac 17 备用 (fix_inner_all.py 的 1,655 处变更就是为 javac 17 严格行为做的)
- 系统无其他 JDK 8 (仅 JRE 8 两个, 无编译工具链)

---


## Phase A 执行计划: 确定性重建 (R0-R5)

### 实际执行进度 (v19.0 → v19.107, 2026-08-13~17) — 已完成

> 基线修正: **真实错误基数 41,402** (maxerrs 5,000 封顶曾把一切截断在 ~5,000 的假象; v19.1 提升至 100,000 后揭晓)
> R0-R5 确定性重建管线: 常量池主 pass 3,397 处 + member_revert 3,023 处 + inner_class_restorer 310 类
> + 循环十二轮 (v19.0 → v19.9: 41,402 → 29,375) + 动态测试 8 轮 (v19.90-97) + 辐射批次 6 轮 (v19.100-105)
> 详细逐轮进度表 (v19.0-v19.107 共 56 行) 已归档于会话记录, 不再在 PLAN 中维护

---

### 损伤家族目录 (核心方法论成果)

每轮坍缩的根源是一个**可字节码证实的损伤家族**, 已发现并修复 24 个:

| # | 家族 | 特征 | 修复 |
|---|------|------|------|
| 1 | 幻影名 | 启发式修复器发明的类名 (ShaderEffect/BaseUnit/AnimPose...) | 02 锚点投票 + 常量池过滤仲裁 |
| 2 | 真丢失类 | PacketDecoder/EffectConfig/resources.CustomActionBase 等 03 无文件 | 02 复制 + renamer 管道 |
| 3 | 误标映射 | y→BaseUnit"v18.3-verified"实为 UnitType; utility.y→"y.DataBuffer" 污染 | 三源对照 + 文件存在性校验 |
| 4 | 旧包 import | units.a.StopAction 等 52 条 | import_rewriter |
| 5 | 半改名 FQN | 包已改名类未改 (ui.g) | 常量池逐文件裁定 (双义: InGameUI vs GameRenderer.g) |
| 6 | 成员声明侧语义改名 | 调用点仍用混淆名 (B()/bX/bO/bQ) | member_revert (supplement 反查) |
| 7 | extends 误改名 | PacketHandler/ConnectionState 等 (CFR 丢包名加剧) | 字节码 super_class 全量审计 (63 处) |
| 8 | 数字内类损伤 | fix_inner_all 把独立数字替换成 X$N (BitmapFont$1/DataStreamReader$1) | 算术位 X$N→N (类名在算术位恒非法) |
| 9 | CFR 点号内类 | h.2 形态 (点号代 $) | 父类 fwd 映射 h.2→Factory$2 |
| 10 | 双写 FQN | 修复器叠加 extends Sprite.Sprite | 全树折叠 + 回退重放 |
| 11 | 类声明错位 | 文件声明他类名 (l.java→ModUnitRegistry) | 内容指纹 + fwd 裁决方向 |
| 12 | InnerClasses 剥离 | $N 为顶层类无法访问私有外层成员 | ctor public 化 |
| 13 | v18.x 语义重复声明 | 语义命名字段与混淆字段并存 | member_revert 重复删除分支 |
| 14 | 缺失内部类 | 377 个 $ 文件缺失 (IntArray$1 等) | inner_class_restorer (310 恢复) |
| 15 | utility.X 误标 | utility.m→WorldGenerator / utility.o→KeyCodeMapper 等 (顶层同名类抢占) | 02 import 逐处锚定 (CustomArrayList/DequeList) |
| 16 | 幻影名 SpriteHandle | 启发式发明 (实际为 m.l=Renderer 与 l=GlobalState 双义) | 02 锚点分义替换 34 处 |
| 17 | 语义名广播滥用 | setFactoryLink/i$a 等被应用到数十个不同方法 (supplement 单条映射广播) | 窗口对齐+参数个数校验回退混淆名 |
| 18 | CFR 丢字段 | 字节码 Fieldref 有/字段表无 (ProGuard 剥离) | 补字段声明 (GameScreen.bX) |
| 19 | 内部类双恢复 | inner_class_restorer 对 ar\$N 恢复两次 (ResourceUnit\$N 正确 + NetworkUtils\$N 错误父类) | 删除错误版 53 文件 |
| 20 | 字段类型误标枢纽 | GlobalState.bX/bL 等枢纽字段类型错 (KeyBinding/ByteSlot) → 数百处级联 | jar javap 锚定真实类型 |
| 21 | CFR enum 三连缺陷 | class extends Enum + 常量 static-final-new 形态 + 匿名体独立 \$N 文件 (ACC_ENUM 字节码证实) | enum 关键字 + 常量语法 + 匿名体内联 + 无参 ctor |
| 22 | 重写丢失文件 | v18.x 语义化重写丢内容 (InGameUI 缺482方法/TileEntry 缺222/GLRenderer 缺100, 29 文件) | 循环十一专项: 02 方法体指纹合并 |
| 23 | 生成文件持久化缺失 | Agent C 直接写 identity-index.json (生成产物) — 索引重建丢失 11 条 (v19.86 修复) | 映射只写源 CSV (class-discoveries), 生成文件永不作持久化载体 |
| 24 | CSV 非模块写截断 | main-deobf 工具重写 class-discoveries 时违反 M4 (~30 行 notes 逗号截断, real_pkg 丢失) | 父版本恢复 + 重放有意修改 (v19.86) |


### 级联-阻断循环的教训

1. **maxerrs 截断假象**: 5,000 封顶把 41k 错误显示为 5k — 测量工具必须先修正
2. **"N 错误"可能是层级顶端**: 修完最后一层语法错误, javac 恢复依赖编译, 下一层浮现 (实录: 35,104→1→34,923) — 每轮必须全量重测
3. **修复器必须幂等且可重放**: 三度回退重放 (git checkout + 管线) 证明确定性管线的价值


### 剩余计划 (v19.124-动画家族 起 — 当前残余, 5,987, 已突破 6,000)

> **剩余错误唯一聚合入口**: [../_archive/剩余错误总清单-5283.md](../_archive/剩余错误总清单-5283.md)
> (678 文件全分组, 类型/符号归类; 生成器 cache/gen_error_list.py, 每次 javac_gate 后可重生成覆盖)

| 优先级 | 目标 | 错误数 (当前) | 说明 |
|--------|------|------|------|
| 1 | MainUIController | 118 | librocket 脚本大文件战役 |
| 2 | ReplayEngine | 100 | 回放族 |
| 3 | LibRocketBridge | 96 | librocket 桥 (ScriptEngine 33 符号) |
| 4 | GameLauncher / ActionPanel / MultiplayerUI | 66 / 66 / 65 | 桌面启动/行动面板/多人 UI |
| 5 | anim/a 56 + BitmapDrawer 56 + filesystem/a 55 | 167 | 动画渲染/位图绘制/文件系统 |
| 6 | ModUnitRegistry 54 + conditions/c 52 | 106 | 自定义单位注册/条件求值 |
| 7 | GLRenderer 50 + InGameActivity 50 + DesktopGameContainer 48 | 148 | 渲染器/应用框架/桌面容器 |
| 8 | 动画家族残留 (AnimationCurve 38 / k 28 / l 11 等) | ~110 | custom/animation 包 |
| 9 | 高频符号族 (b 143 / a 138 / n 74 / f 70 / am 43 / ScriptEngine 33) | ~600 | 逐文件 CSV 行号驱动战役 |
| 10 | ✅ v19.119-124 已清零家族 | 0 | 建筑运输族/行为族/水族/空间网格/ResourceComponent/BuildActionSlot/动画家族/PathfindingUtils/Sprite/ExperimentalUnit 等 |

**每 Round 门禁**: javac_gate 全量 + compile-errors.csv 快照 + 重新生成错误总清单 + git commit; 修复器幂等、损伤先回退重放。

---

### 关键工具 (核心 9 个, v19.0 就位; 全量 70+ 见 CLAUDE.md 工具树)

| 工具 | 位置 | 职责 |
|------|------|------|
| `extract_class_refs()` | rwlib/bytecode.py | 常量池引用提取 (tag-7 + 描述符签名双来源) |
| `identity_index.py` | tools/core/ | fwd/rev 表 + unresolved/misplaced (三源合并+真值校验) |
| `constant_pool_renamer.py` | tools/core/ | 核心重命名器 (--tree 主pass / --tree-revert 逆操作) |
| `member_revert.py` | tools/core/ | 声明侧成员回退 (loc 解析 + supplement 定位) |
| `import_rewriter.py` | tools/core/ | import 三规则重写 (fwd/PKG_MAP/树唯一) |
| `restore_lost_classes.py` | tools/core/ | 幻影仲裁回退 + 真丢失类恢复 (02 锚点投票) |
| `inner_class_restorer.py` | tools/core/ | $ 内部类恢复 (310/377) |
| `fix_method_pair_generic.py` | tools/fixers/ | 四层指纹配对器 (v19.108 管线链核心) |
| javac_gate 升级 | tools/gates/ | symbol/location 列 + maxerrs 100,000 |

---

## 多 agent 并行修复 (循环十一, ✅ 已完成)

> 三路并行 (文件严格分区): A 丢失文件恢复 17/19 (+9,741 行) / B incompatible 对族 (ActionPanel 191→65) /
> C aicore 24/24 身份匹配 — 合并 26,011 → 25,071; 详见会话记录 v19.57-81 段

---

## Phase B: 方法名全局重命名 (Phase A 完成后重估)

> ⚠️ **旧规划 (v3.22 前)**: 实际 Phase B 按新定义执行 — B1 编译清零 / B2 反向映射核对 / B3 全量反向构建 / B4 运行验证, 已全部达成 (见 PHASE-A-会话历史总览)。下表为历史方法路线保留。

**目标**: 跨文件 call-site 同步, javac 类型感知

| 步骤 | 问题 | 状态 |
|------|------|------|
| B1 | javac 类型推断引擎 | ✅ 已验证 (175 类型错误/500文件) |
| B2 | 反向类型映射 (rev_cmap) | ⚠️ 96 映射, 15% 匹配率 |
| B3 | supplement 覆盖扩展 | ⏳ 待 Phase A 编译归零后, javac 报错可精确指位 |
| B4 | 高置信度重命名应用 | ⏳ |

**关键发现**: javac 编译通过后 `location: variable X of type Y` 信息可精确确定调用点类型 — Phase A 归零是 Phase B 的前提。

---

## Phase C: 参数/局部变量清理

⏳ 待 Phase A/B 完成 — varN 参数 146→0

---

## Phase D/E: 映射质量 + 工具文档 (✅ 已完成)

- ✅ class-discoveries 779 游戏类全覆盖
- ✅ cross_validate: 55.1% bytecode 验证率
- ✅ rwlib 100% (32/32) 合规
- ✅ 双分支策略 → **已改为单分支**: apply_enhanced_rebuild 冻结

---


---

## 进度追踪

| 阶段 | 状态 | 关键指标 |
|------|------|---------|
| Phase A | ✅ 清零 (B1) | 编译错误 **0** (41,402 → -100.0%, gate PASSED); 40+ 损伤家族; supplement 10,829 / class-discoveries 1,301; 官方语义名 482 |
| Phase B | ✅ B1-B5 达成 | B1 编译清零 → B2 反向映射核对 → B3 反向构建 → B4 运行验证 → **B5 行为一致性收敛** (运行时反馈驱动 10 项修复, 启动 0 崩溃, 功能全通; 残余: GUI/回放深度验证) |
| Phase C | 🔄 | varN 参数清理 (v19.114 部分) |
| Phase D | ✅ | 类映射 1,301 全覆盖, 字节码验证 53.8% |
| Phase E | ✅ | rwlib 100%, 文档同步 |

---

## 风险与回退策略 (v2 更新)

1. **身份不可解析文件 (~40)**: 不阻塞, R5 内容指纹补身份
2. **修复器误伤**: 每个修复器都有明确的反向操作 (--tree-revert / 回退重放管线); 已三度实践 git checkout + 确定性重放
3. **级联层级假象**: 低错误数可能只是层级顶端 — 每轮全量 javac_gate + CSV 快照; 修语法错误前不轻信数字
4. **maxerrs 截断**: 恒用 100,000; 任何 <100k 的测量必须注明
5. **stubs 掩盖 (67 桩)**: 桩只覆盖 android/steamworks 外部包; 桩清单基线在 R0 记录
6. **javac 13 为主**: 游戏自带编译器; javac 17 备用; 两者画像 99.7% 一致

---


---

## 会话历史 (追加)

| 版本 | 日期 | 核心内容 | 错误数 |
|------|------|----------|--------|
| v19.115u/v/w | 2026-08-23 | AI 包清零 (CombatAction 174/TransporterGroup 79) + MapRenderer 清零 (181) | 11,079 → 10,627 (-452) |
| v19.115t | 2026-08-23 | CombatMain 清零 (类型标注/AIStrategy 字段语义名 17 处/方法名序/补缺 7 文件) | 11,273 → 11,079 (-194) |
| v19.115s | 2026-08-23 | 冗余副本删除+广播三连 (UnitRegistry 冗余/GameEngine bs/CombatMain y-am) | 11,549 → 11,273 (-276) |
| v19.115r | 2026-08-23 | logicBooleans战役 (双重前缀/裸名映射/VariableScope 家族) | 11,754 → 11,549 (-205) |
| v19.115q | 2026-08-23 | ay战役 (CustomEffectTemplate 02b整写 + HUDManager d→HUDAnchor) | 11,893 → 11,754 (-139) |
| v19.115p | 2026-08-23 | base残留名批5直译 (base家族清零: 7文件02b整写+bp签名修复+CustomUnitType 7方法) | 12,030 → 11,893 (-137) |
| v19.115k/l/m/n/o | 2026-08-23 | base家族+utility.ab战役 | 12,427 → 12,030 (-397) |
| v19.115j | 2026-08-23 | 连带清尾 (ProjectileWeapon 24→0/InputNetStream 4→0/ModUnitRegistry.n) | 12,461 → 12,427 (-34) |
| v19.115i | 2026-08-23 | Command战役 (Command 89→0, l=GlobalState 铁证, 15 文件修复/重建) | 12,695 → 12,461 (-234) |
| v19.115h | 2026-08-23 | AI包类名对位 (类名错位广播 5 类 + AIStrategy 324→0) | 13,486 → 12,695 (-791) |
| v19.115g | 2026-08-23 | AIStrategy父类修正 (02b a.a extends n) | 13,578 → 13,486 (-92) |
| v19.115f | 2026-08-23 | PlayerState残余族清理 | 13,636 → 13,578 (-58) |
| v19.115e | 2026-08-23 | PlayerState幻影方法清理 | 13,675 → 13,636 (-39) |
| v19.115d | 2026-08-23 | MapEngine战役+bL广播 | 14,044 → 13,675 (-369) |
| v19.115c | 2026-08-23 | TMI与核心类战役 | 15,169 → 14,044 (-1,125) |
| v19.115 | 2026-08-23 | RenderLayer污染清除+Factory家族战役 | 16,315 → 15,169 (-1,146) |
| v19.114 | 2026-08-23 | 工厂编队战役 (数字损伤逆形态/类名污染) | 16,739 → 16,315 (-424) |
| v19.113 | 2026-08-22 | 运行时代入捕捉 (Phase A 完成) | 15,987 → 16,739 (-693) |
| v19.112 | 2026-08-22 | CustomUnitType攻坚 + BuilderUnit重建 | 18,150 → 15,987 (-718) |
| v19.110 | 2026-08-21 | 测试族攻坚 + 运行时验证 | 19,337 → 15,987 (-1,905) |
| v19.109 | 2026-08-20 | 核心链路 + 批量修复2 | 19,337 → 18,150 (-1,187) |
| v19.108 | 2026-08-19 | 批量脚本化 (四层指纹配对/字段保序/广播级联) | 21,854 → 19,337 (-2,517) |
| v19.107 | 2026-08-17 | Phase A 会话修复 (12 损伤族) | 23,778 → 22,135 (-1,643) |
| v19.116 | 2026-08-23 | UnitRegistry重建 (v19.115s误删勘误) + ModLoader战役清零 (280→<69: n缓存/am-ar遮蔽/actions族/CustomActionBase双包) | 10,086 → 9,653 (-433) |
| v19.115z | 2026-08-23 | ReliableSocket战役清零 (reliableudp包全清: ReliableSocket 174→0 + 包内5文件) | 10,338 → 10,086 (-252) |
| v19.115y | 2026-08-23 | NetEngine战役清零 (151→0 + 网络族全清: PacketDecoder/GameModeEnum/SteamSocket/ReceiveWorker) | 10,627 → 10,338 (-289) |
| v19.117 | 2026-08-23 | GameEngine清零战役 (258→0: onScreenStart误名族6方法还原/updateAllGame-drawAll内部40+处/误命名CommandPathPart副本清理) | 9,653 → 9,354 (-299) |
| v19.117-CUT | 2026-08-23 | CustomUnitType战役 (242→120: WeaponConfig getat幻觉名还原/T0字段裁决MC.g-ap-aP/幻觉方法名族K-D-cY-cF-a_-T-V-y还原) | 9,354 → 9,201 (-153) |
| v19.117-CUT16 | 2026-08-23 | CustomUnitType清零批16a-i (9子批: MovementPath接口裁决/h=UnitConfig链/PacketBuilder→UnitTypeHandle幻觉族/UnitInstance补12方法 javap直译/TMI接口漏声明2处/幻觉参数类型族f(n)+a(ag)/简单名final赋值) | 9,201 → 9,027 (-174) |
| v19.117-CAB | 2026-08-23 | CustomActionBase清零 (幻觉方法名还原42处 02b d/b方法表/EffectManager静态族9个+去重/an缓存类简化/层级坍缩: 50→307语法→清零) | 9,028 → 8,968 (-60) |
| v19.117-MC | 2026-08-24 | MovementController清零 (123→0: w/EffectConfig误标链修正[SpriteBase父类/GameObject字段ej-em-eo-eq/OutputNetStream参数]/HUDAnchor→SoundEffect裁决反转47处改名/bi.a 10参字节码裁决/bp.a(InputNetStream)直译/静态块Paint族/StatsManager参数链/层级坍缩: 8,805→219语法→8,884) | 8,952 → 8,884 (-68) |
| v19.117-MC6 | 2026-08-24 | MovementController批6清零收尾 (9,201 → 8,946 → 8,884: object2 拆分/w-EffectConfig链/bi.a 10参 第6参 utility.m 字节码裁决/bp 直译) | 9,201 → 8,884 (-317) |
| v19.117-HUD | 2026-08-24 | HUDManager清零 (124→0: getint 幻觉名族 9 方法删除/SoundEffect 新建 02b d/d.java 9常量/HUDElement.reset2→c()/b字段 EffectConfig→GameObject/02b 全限定残留 d.d-d.h-d.e-l-ui→语义名) | 8,884 → 8,742 (-142) |
| v19.117-IGUI | 2026-08-24 | InGameUI清零 (140→0: reset 族→a() 重载/补方法 16 个/ui.panels l-a-k 新建 02b 直译/UIContextMenu 幻觉包名/深水区简化 TODO 登记 PENDING) | 8,742 → 8,567 (-175) |
| v19.117-Minimap | 2026-08-24 | Minimap清零 (154→0: ThemePaint→UniquePaint 10处 02b L41 ag/字段当方法族→a-b-c-d()/MinimapTile-Marker-Config 字段保序对齐/LineBuffer 构造器/UpdateChecker$1 幻觉常量) | 8,567 → 8,403 (-164) |
| v19.117-PSR | 2026-08-24 | PathSolverRunner清零 (92→0: m2→NodePool/p4-p5-p6 AssetLoader→PathNode 02b k-p/GameRenderer.a(f,f,f,f)→GameUtils.a/自身静态 b-c()/blockedAirGrid()→d()/var18_19-s12 幻觉变量→by5-s13-s15/+true→+1 误伤/PathCostLocator 新建 02b k-c.java/h.java extends PathNode→PathCostLocator 幻觉名族/PathCostCalc.b FileSystem→TerrainCost/reset4→reset/PathFinder.e→pathSolver/UnitTransform 幻觉重载删除/层级坍缩: 8→8,240 语法) | 8,370 → 8,237 (-133) |
| v19.117-PF | 2026-08-24 | PathFinder清零 (60→0: o KeyCodeMapper→PathSolverRunner/q ByteSlot→MapEngine/CrashHandler→PathSolver x4/a(UnitListIterator)→a(MapEngine)/b2.u.n-o→tileWidth-mapHeight/a(GameSaver)→a(UnitType) 02b y=UnitType 铁证/GameRenderer c-d-b→GameUtils/q.r-s-n-o→float1-float2-tilePixelWidth-Height/a(null)→(UnitType)null x5 歧义/删重复空方法) + PathSolver 签名对齐 (javap k-i.class: a-b-c(GameSaver)→UnitType/a(UnitListIterator)→a(MapEngine) x3/GameInput→MovementTypeEnum/y2.cc→getRenderBounds/b2.C-D→mapHeight-tileWidth/am.bE→UnitInstance.bE) | 8,237 → 8,132 (-105) |
| v19.117-UL | 2026-08-24 | UnitList族清零 (02b k-a=UnitList+k-b=UnitListIterator javap 铁证: pathfinding.a 幻觉包→UnitList/WebAPIClient 误标→AStarNode x6/ByteIndexedMap→UnitList/合成双参构造补) | 8,132 → 8,104 (-28) |
| v19.117-UT | 2026-08-24 | UnitType战役 (42→0: bT() 幻觉方法删除 [UnitInstance.bT final]/unitTypeRegistry UnitRegistry→UnitInstanceList [02b y.aM=utility-u]/(EffectConfig)this 强转删除 x4 [02b y.java L5080 无强转]/o(UnitInstance) 补 [02b L4754]/全库 keyValueB-C→b-c 42 处 [02b ai.java L8-9]/UnitInstance.a(MapEngine,Point) 补 [02b am L2105]/MovementController.au EffectConfig→GameObject [02b f.au=w, az extends w 链]/CustomUnitType 强转删除 [02b j.java L1949]) | 8,104 → 8,049 (-55) |
| v19.117-UI | 2026-08-24 | UnitInstance清零 (5→0: dn() 重复删除/bw() 补 [02b am L1839]/Texture a(int,int,boolean)+a(int,int)+a()+a(Bitmap) 补 [02b m-e L139-178-69-100]) | 8,049 → 8,036 (-13) |
| v19.124-动画家族 | 2026-08-25 | GameSaver 战役清零 (55→0: CommandController→NetworkPlayer/HumanPlayer 族/EffectConfig→GameObject 全族/OSEnum→Factory/UnitRegistry.Y/bX=NetEngine javap 铁证/ToastMessage 新建 02b f-m 直译/PacketBuilder 幻觉参数族 a(OutputNetStream)×4/InGameUI 补 a(Factory)+a(InputNetStream,boolean)) | 7,789 → 7,713 (-76) |
| v19.118-STATS | 2026-08-25 | Stats 族战役 (96→0: f/y=StatsPanel 对照/ProjectileManager→StatsGraph 幻觉族/bj=StatsCategory g-f=DataFieldProvider javap 铁证/bo 字段语义名修正 c=unitsKilled 等 f/e 铁证/readFromPacket→a 改名族/GameTimerTask 字段保序) | 7,713 → 7,580 (-133) |
| v19.124-动画家族2 | 2026-08-25 | GlobalState 战役 (95→0: 02b l.java 1461 行对照/bh=TextureManagerInterface/dS=ANRWatchdog/dy=GlobalStateFactory/bT=KeyBindingManager/PlatformExtension+Factory+PaintRegistration 新建/单例族 al.c/OOM 族 dW-dY-dV-dX/静态块幻觉赋值删除/KeyBindingManager 30→0: 02b ac.java 对照 ae→KeyBinding 轴/ByteSlot.a()→b.a() javap 铁证) | 7,565 → 7,469 (-96) |
| v19.118-UDP | 2026-08-25 | UDPBroadcastListener 战役 (24→0: 02b j/af d=NetEngine + NetEngine 补 t/d(String)/al()/a(GameServerInfo) WiFi 广播地址直译 + GlobalState 02b 名引用修正) | 7,469 → 5,987 (-37) |
| v19.125-四战役 | 2026-08-25 | ScriptEngine 官方名恢复 (UIScriptEngine→ScriptEngine git mv 4 文件+82 处) + $N 数字误用还原 (27 处) + InputAxis=GlobalState (29 处) + f 旧名广播 (GameUtils/ThemeColors/TextFormatter/panels/MovementController/DataFieldProvider 94 处) | 5,987 → 5,799 (-188) |
| v19.126-MI+UST | 2026-08-25 | ModInfo 战役 (调用点错误语义化还原 36 处 + filesystem.g→FilePathSanitizer) + UnitStateTracker 战役 (02b f/an+f/x+f/ao 类型还原 + BridgeUnit 双前缀 + EffectManager 补 b(f,f)) | 5,799 → 5,710 (-89) |
| v19.127-TUT | 2026-08-25 | TeamUnitTracker 战役 (02b game/s 还原: as→UnitTypeHandle/d.b→ResourceComponent/d.l→CarrierUnit 接口/d.j→BuilderUnit/显式 Iterator) | 5,710 → 5,676 (-34) |

## v19.125-127 三会话 (2026-08-25)

- 5,987 → 5,676 (净减 311, 累计 -86.3%)
- v19.125: ScriptEngine 官方名 (05-gamelib 双铁证) / $N 数字误用扫描分类法 / InputAxis=GlobalState / f 旧名广播 (类 f=GameUtils + 子包族)
- v19.126: ModInfo 方法名保序还原 (F40) / UnitStateTracker 家族 (f/an+f/x+f/ao) / 双前缀 extends 损坏 (F41) / 语义化双副本类 (F42)
- v19.127: TeamUnitTracker (02b game/s) / 接口 vs 类误建 (F43) / 接口返回类型链 (F45)
- 关键裁决: 02b librocket/scripts/*=官方名; java/c=java/input; o/a=SteamEngine; e/=filesystem (g=FilePathSanitizer); i/=mods; f/=ui; f/a/=panels; g/=audio; game/f=MovementController; units/d/l=CarrierUnit 接口; units/d/j=BuilderUnit; units/as=UnitTypeHandle
- 方法论 F35-F45 沉淀 (详见各会话记录)
- 残余: UnitTypeHandle 接口返回类型链 (u()/d(int)/B()→ResourceComponent) / anim/a 45 (d=EffectConfig/l=ModUnitRegistry/b 枚举遮蔽) / BitmapDrawer 56 需整写 / FactoryAction1-5 家族

- 7,062 → 5,987 (突破 6,000 关口, 净减 1,075)
- 建筑运输族 (SpecialBuilding 家族 17 文件) / 行为族 (MoveBehavior 等 6+6) / 水族 (WaterUnit 等) / 空间网格 / ResourceComponent / BuildActionSlot 家族 / 动画家族 (AnimationCurve/MovementCurve/TurretCurve/VisibilityCurve)
- 关键裁决: 02b units/e 包全家族映射; b/f=AnimationActivationCurve 枚举; a/g=BuildAction; f/an=UnitStateTracker
- 方法论 F31 遮蔽可见性删除 / F32 混淆包名归属 (custom.l vs custom.b.l) / F33 枚举类映射源 / F34 Object workaround 清理
| v19.128-UTH | 2026-08-25 | UnitTypeHandle 接口链 (02b as 返回 d.b=ResourceComponent: 接口3方法/UnitRegistry/UnitInstance/ResourceComponent 内部 40 处字段语义化/EffectManager 补 do_b) + ModUnitRegistry 补 17 个接口方法 (v/h/f/q/p/k/m/n/o/y/z/u/d(int)/w/c/b(int)/d, 02b l 逐方法锚点) + getCreditCost 修正 (f.get 铁证) | 5,676 → 5,663 (-13, 含反弹清理) |
| v19.129-ANIM | 2026-08-25 | anim/a 双副本战役 (02b e/a: anim/a extends LogicBoolean + d→EffectConfig/l→ModUnitRegistry + bb→LocalizedString + anim.b→effects.b 统一 + getTeamStatModifiers/getStatsCollection) — **anim/a 45→0 清零** | 5,663 → 5,613 (-50) |
- **v19.131-MUR战役** (2026-08-25): ModUnitRegistry 52错误清零 + MUR家族271全灭; 5,566 → 5,375 (-191); 12 新修复器 fix_mur_batch5-16; 方法论 F52-F58

| v19.132-BITMAP | 2026-08-25 | Bitmap\ 符号表污染根因 (android.jar Stub 版 BitmapFactory\ InnerClasses 含 Config-of-Bitmap 条目 → javac enterClass 创建 package-private 嵌套符号; fix_android_innerclasses.py 删条目+cp前置) + not public 五批清零 (幻觉包 projectiles→MovementController 22处/ProduceSlot→Texture 41处/Localization 32处/类型错标 40处) | 5,200 → 4,823 (-377) |
- **v19.132-Bitmap根因战役** (2026-08-25): 5,200 → 4,823 (-377); 新工具 fix_android_innerclasses/fix_notpublic_batch1-4/fix_produceslot_batch5/fix_localization_batch6; 方法论 F19-F22

| v19.132WZ | 2026-08-26 | 三战役: w=NetEngine.a 调用点语义化→registerRelayServer (9处); x=MainUIController 86 清零 (ServerConnector=02b j/an/ContextMenuActivity=02b appFramework/i/PlatformExtension=02b l/a/WebAPIClient=02b j/n/FileWatcher=02b j + StatisticType 误建裁决 → audio/DataFieldProvider); y=ReplayEngine 84 清零 (02b ba.java 911行整写重建 + ReplayWriter.java 新建 + GlobalState 补 bc/bd + 类型映射勘误 F27); z=MultiplayerUI 部分修复 | 4,017 → 3,789 (-228) |
- **v19.132wz 三战役** (2026-08-26): 4,017 → 3,789 (-228); 方法论 F23-F27 (方法序整写重建/重复类裁决/$N 数字污染/重载爆炸陷阱/02b 类型映射勘误)

| v19.133f6 | 2026-08-26 | AIWaveSystem 清零 (9错误, 02b n/f.java 铁证: Iterator/setValue/getLocalizedText/j()/resign/BuildActionSlot) + SAFFileManager 幻影家族清除 (utility/filesystem = 02b utility/a 真源: 类名 a/extends af/GameUtils 调用点; 删除 slots+java/input 2 坏副本; platform 2 重命名 a\/a\) + InGameActivity waveName 字段 + filesystem 家族收尾 (b() 方法/AssetStream/FileRow 迭代) | 2,817 -> 2,783 (-34) |
- **v19.133f6 战役** (2026-08-26): 2,817 -> 2,783 (-34); 方法论 F42-F44 (幻影文件查 02b 全树再删/public 类名不匹配=javac 跳过后续分析/类内全限定静态调用规避字段遮蔽)

| v19.133f7 | 2026-08-26 | 动画家族全清零 (custom/animation 包 93: AnimationCurve/k/l/movement/visibility/turret/e/b/j + UnitTypeComparator 整写 02b game/q 阵营枚举 + PlayerState.a(q,n) 修正 + TraitValueBuilder 5 字段 Texture + BuildAction 构造 UnitType + ModUnitLoader/ModUnitRegistry/CustomUnitType 补方法) + slots/g 35 清零 (02b d/a/g: BuildActionSlot 类型/MovementController/SoundRegistry) | 2,783 -> 2,631 (-152) |
- **v19.133f7 双战役** (2026-08-26): 2,783 -> 2,631 (-152); 方法论 F45-F48 (JLS 禁止局部变量遮蔽/javap -c 追继承方法/枚举类整写/抽象方法冗余引爆 not-abstract)

| v19.133f7c | 2026-08-26 | AI/动作家族清零: config/ActionFilter\ 解析器 (02b f/b\, hprrentChar 16 处) + AttackAction (02b units/a/d: UnitFlag/Command/getDescription) + PingAction (02b units/a/j: PingType.a/w()/Texture j()/合成 i()) + MapPingAction pingMap + ai/strategies 整写 (02b a/a: AIStrategyResult 抽象类 + AIStrategy 抽象枚举) + 孤儿/幻觉类清除 (slots/ActionFilter\-4/AINukeStrategy/AIUnitGroupStrategy/AIStrategyResult\/2) | 2,631 -> 2,515 (-116) |
- **v19.133f7c 战役** (2026-08-26): 2,631 -> 2,515 (-116); 方法论 F49-F51 (字段遮蔽类名陷阱/类内容互换检测/幻觉中间类删除)

| v19.133f8 | 2026-08-26 | AIStrategyNode 家族清零: AIStrategyNode 28 (02b game/a/o 真源: GameUtils 8处/MovementTypeEnum/UnitInstance/PathfindingUtils.c/BuildSlot.a + zoneIdCounter/zoneQueue/zoneSnapshot 语义名) + BuildSlot 6 (改 abstract 02b d/d L18 + import UnitInstance + 补 a(as,f,f,n) L47-56) + InGameUI 补 8参 a blockout (02b f/g L3911-3971 直译) + 6参 a (var4 分支简化 TODO) + GameHUD a(n,y,int)/a(y,y) (02b d/a L93/L100) + FactoryBuildSlot 2 (a 字段 BuildActionSlot, 02b c.java) + BuildActionSlot 合成方法 | 2,515 -> 2,482 (-33) |
- **v19.133f8 战役** (2026-08-26): 2,515 -> 2,482 (-33); 方法论 F52-F54

| v19.133f9 | 2026-08-26 | slots/commands 家族清零: slots 59 (02b units/d/a: d/e/f/h 字段 b 类型/包前缀幻觉→this.b/MovementController/SoundRegistry/turretAngle/getResourceCost) + BuildSlot abstract 化补 10 方法 + MobileBuilderBase 12 (02b units/d/i) + AIUnitActionUtils 整写 (02b game/a/f) + 幻觉清除 (UnitType.cL/a(TeamTag)/getMaxMoveDistance + TagParser 误建) + TextureManagerInterface D→a 铁证 + isVisibleTo→a(f,bl) | 2,482 -> 2,345 (-137) |
| v19.133f12 | 六大域家族清零战役: FactoryBuilding/Building + BuildAction + ScriptEngine + SteamManager + MeleeBugUnit + anim/f 双副本裁决 | 1,820→1,653 (-95.99%) |
| v19.133f13 | ParameterAnimator+opengl批处理家族清零: 字段语义名/幻觉import清除/GLResourceBase继承方向 | 1,653→1,576 (-96.19%) |
| v19.133f14 | ModsUI+filesystem域清零: SteamEngine/ModInfo类型+FileLoader幻觉调用还原 | 1,576→1,514 (-96.34%) |
| v19.133f15 | 测试族+SteamWorkshop清零: TestPerformance幻觉类型+i.java补建+ModInfo字段解析源铁证 | 1,514→1,466 (-96.46%) |
| v19.133f16 | PlayerConnect家族清零: 02b j/c.java 一体类拆分 (PlayerConnect 20 处: d/e→Worker/connectionObj 侧构造/m/c 传 connectionObj/as→OutputNetStream/connectionObj()→closeConnection/KeyBinding.i→NetEngine.i/isAuthenticated()→disconnect/合成方法参数/game.e→HumanPlayer) + PacketDecoder 3 处 (PacketDecoder() 非法方法名合并 c()/cancelNotification→i) + NetEngine 补静态 i(String) (02b ad L2966-3009) | 1,466→1,448 (-96.50%) |
| v19.133f17 | IntArray家族清零: 02b j/ad$N NetEngine 内部类误译 (IntArray$1 extends Shader→PasswordManager j/ae + $3/4/5 extends InputNetStream→panels.k f.a.k javap 铁证 + KeyBinding/PathFinder/an/l/n 幻觉类还原 NetEngine/GlobalState/ServerConnector/WebAPIClient/DialogHelper + $4 V()→reconnectToServer + $8 b(String)→m(String)/b(String,String)→m(String,String)/a(Socket)→registerRelayServer(Socket) + 补建 IntArray$5$1) | 1,448→1,414 (-96.58%) |
| v19.133f18 | ChecksumCalculator家族清零: 02b j/ak 直译 17 处幻觉类/强转逐对还原 (w→GameObject F27/MasterServerCreate→UnitType/SendWorker→CommandCenter units.d.e/NetworkPacket→WeaponAction units.au/WebAPIClient+BaseGameObject→PlayerState game.n) + 第二循环声明 PlayerState | 1,414→1,397 (-96.63%) |
| v19.133f19 | **network域清零里程碑** (02b j 包全包 0 错误): 散件 15 文件 40 处 (OutputNetStream au 字段/ChatSystem f→GameUtils.smoothstep HTML转义/ChatMessage 内部类+PlayerState.i/ServerConnector ad.b→NetEngine.m/NetworkUtils game/i=GameEngine/ShaderUniform$1 isDirty→d/UnitTrait$1 t=MasterServerResult) + 删 ThemeFontEntry$1/2/3 枚举匿名子类重复文件 (GameModeEnum 已内联) | 1,397→1,364 (-96.70%) |
| v19.133f20 | a.java家族清零: 6 文件 27 条 (platform/a 静态方法误写构造器×2+PlatformBridge/AndroidPlatform 幻觉 + com/a/a/a/a 包名+自调用 + config/a CFR 变量污染 8 处 e.reset/fieldDefinition/fieldValue/Map cast + mods/a ModLoader.m→getModsDirectoryPath+ui.g→InGameUI+FileLoader.FileLoader→a + ModLoadEntry VersionChecker→a + java/input/a GameWindow→SteamManager+补建 a$1 + java/graphics/a 删幻觉方法) | 1,364→1,338 (-96.77%) |
| v19.133f21 | e.java家族清零: 3 文件 23 条 (java/input/e GameWindow→SteamManager+双前缀修正 + effects/config/e c→displayName+bb.a=LocalizedString.isEmpty 方法体逐行一致 + audio/backend/e SoundEffect→f+AudioDevice→backend.m m 字段遮蔽 F7b 全限定 + f 构造 AudioManager→e + java/input/a 无参构造误写还原 a() 方法 F58) | 1,338→1,321 (-96.81%) |
| v19.133f22 | MapSpawn家族清零: 02b game/b/a 18 处 (map.a.a→本类静态方法/NodeList cast/字段误调用×4 F59 spawnIndex→a(String)/teamName→d(String)/unitTypeName→b(String)/w.dL→GameObject.dL F27/x.h→EmptyArrays.emptyStringArray 字段保序/LocalizedString.b→getLocalizedText/ad.g→NetEngine.g) | 1,321→1,303 (-96.85%) |
| v19.133f23 | DebugUI家族清零: 02b librocket/scripts/Debug.java 18 处 (EffectConfig.dK→GameObject.dK F27/bF() raw cast×4 F17/补 UnitInstance.cm() 02b am L1541 反编译丢方法补基类 F60/bX.a→registerRelayServer F26 重载×5/ad.r→NetEngine.r/ai→GameModeEnum/e2.u=DebugUI$1→1 02b L619/reliableudp.b 幻觉包→补建 platform/net/b.java/PlayerState.c() cast) | 1,303→1,285 (-96.90%) |
| v19.133f24 | AudioManager家族清零: 02b java/audio/a/i long 键 18 处 (b int[]→long[] 全链同步 F62 + (int)(n&(long)channelCount) cast×7 + 变量 long×12 + 方法签名 b/c/d/f/h/i/a8参 7 个 + h/i 方法体 02b 风格 L444-450 + c(long,Object) int 索引 L257-258 + equals i2.e→i2.d L508) | 1,285→1,269 (-96.93%) |
| v19.133f25 | DataFieldCollector家族清零: 03 重建类 15 处 (f→DataFieldProvider×4/n→PlayerState×3/raw Comparable→Object cast×3 F17/fieldValue 字段误调用删 F59/audio.a.a→本类静态 a+DataFieldFloat.b 合成访问器 F63/LogicBoolean.c.D→静态 a(long,b) 等价 F64) | 1,269→1,252 (-96.97%) |
| v19.133f26 | NodePool家族清零: 02b gameFramework/k/m 9 处 (d→UnitList/e→NodeQueue 字段幻觉 + void m()→NodePool() 构造器还原 F65 + WebAPIClient→AStarNode×3 + import 清理×3) | 1,252→1,235 (-97.02%) |
| v19.133f27 | TreeDecoration家族清零: 02b units/al 16 处 (静态 n()→b()/al.a→本类静态/TextureManagerInterface 链/(Paint)null×2/ao.a→MovementTypeEnum.a/bR.n→bR.a/WaterUnit→MovementController/isOnScreen 补建) + 幻觉抽象 setTeamInternalById 删除 javap 铁证 | 1,235→1,219 (-97.06%) |
| v19.133f28 | ActionWrapper家族清零: 02b game/units/a/g 委托类 13 处整写 (a()→getDescription 覆写/super 委托还原/equals→super.equals/j()/h(am)→Texture/构造器删幻觉/compareTo 链还原 F69-F70) | 1,219→1,203 (-97.10%) |
| v19.133f29 | SettingsEngine家族清零: 02b 同名类 12 项 (bT.al→allBindings/digitToKeycode 委托名/utility.ab 配置解析器/raw for-each 显式迭代×2 F17/CommandController→AndroidUIHelper 拆分裁决 F71/filesystem.a 全限定/isEnabled3) | 1,203→1,188 (-97.13%) |
| v19.133f30 | WeaponConfig家族清零: 02b custom/as 18 条 (s.values()/t 字段还原/AttackWaypoint$N→WeaponConfig$N 19 文件改名/$13·15·17 extends ax javap 铁证/ax→at/孤儿 WeaponConfig$2 删除 F72-F73) | 1,188→1,163 (-97.19%) |
| v19.133f31 | TilesetDef家族清零: 02b game/b/j 15 条 (循环变量污染 i→tileCount/map.j.a→本类静态×4/tilePixelHeight 幻觉×2/TileEntry tilesetIndex·animationDuration→textureLoaded·texture/raw for-each F17 F74-F75) | 1,163→1,148 (-97.23%) |
| v19.133f32 | TeamColorTexture家族清零: 02b m/h 15 条 (BitmapFont→y×3/setRenderTarget 重载还原×6/补 Texture.i() F60/PlayerState.getActivePlayers→b/teamColorC→c×3 F76) | 1,148→1,133 (-97.26%) |
| v19.133f33 | BuildQueueAction家族清零: 02b units/a/v 15 条 (PacketBuilder a→UnitTypeHandle/getCreditCost 返回/am.c→UnitInstance.c/StopAction→UnitType×6/u.b·t.e 枚举语义名/super.g F77) | 1,133→1,118 (-97.30%) |
| v19.133f34 | AndroidUIHelper家族清零: 02b appFramework/c+c$2 23 条 (补 GameUtils.k/b→dialogHelper×3/l.aU→GlobalState/FileLoader.isEnabled4/e.f→AppState.f/game-lib stub 遮蔽→new Handler()+run() F78/字符串还原 F79) | 1,118→1,098 (-97.35%) |
| v19.133f35 | RallyGroup家族清零: 02b game/a/l 17 条 (update/reset→c/a 覆写链还原×6/BaseZoneStage→InputNetStream/y3→y2/readFloat/R.bm→zoneQueue/super.destroy→super.p/补 AIStrategyNode.a(OutputNetStream) F80) | 1,098→1,081 (-97.39%) |
| v19.133f36 | KeyBinding家族清零: 02b gameFramework/ad+af 26 条 (textureId()→a()/bindingName→this.a 委托链×3/ag2.a→textureId×3/KeyBindings.d→SlickToAndroidKeycodes.a/补 TextureCache b·c·d F81) | 1,081→1,055 (-97.45%) |
| v19.133f37 | audio/backend/l家族清零: 02b java/audio/a/l 同构直译 (AudioListener b→AudioManager/b.a(int)→b.d(int)/--b.a→--b.sampleRate F82) | 1,055→1,039 (-97.49%) |
| v19.133f38 | HoverUnit家族清零: 02b game/units/l 直译 (getDescription 抽象覆写 L42/f()·e() 返回 ActionCategory·ActionTargetType L54/L58/Factory.L() 等价链×3 02b h L498/UnitCategory.b/UnitBehaviorEnum.getName×2 F83) | 1,039→1,027 (-97.52%) |
- **v19.133f9 战役** (2026-08-26): 2,482 -> 2,345 (-137); 方法论 F55-F57 (语义名族污染检测/PowerShell 行替换吞符号/03 语义名先于新类) (集合元素类型反推真源/03 语义名优先于补字段/02b 双源版本差异—jar 为 ProGuard 精简混淆版)

- v19.133f10 (2026-08-27): units/commands 家族清零 ($N extends 误映射/ResourceComponent 裁决/02b u/v 误建副本) | 2,211 → 2,039 (-95.07%)
- v19.133f10.1 (2026-08-27): actions/g + GuardAction 清零 (02b custom/a/g 全文对照) | 2,039 → 2,011 (-95.15%)
- v19.133f10.5 (2026-08-27): actions/g+resources/c+d+InGameActivity 清零 | 2,039 → 1,943 (-95.31%)
- v19.133f11 (2026-08-27): SendWorker+NodeQueue 清零 | 1,943 → 1,892 (-95.43%)
- v19.133f11.1 (2026-08-27): FireDecoration 清零 (02b units/ai 全文对照) | 1,892 → 1,866 (-95.49%)
- v19.133f11.2 (2026-08-27): filesystem 家族清零 (DualStorage+StorageBackend, 02b e/c) | 1,866 → 1,820 (-95.61%)
- **v19.133f96 (2026-09-04, Phase A 收尾)**: 清零战役巨型批次 | 486 → **12** (-99.97%): OutputNetStream 写侧 throws 恢复链 (a(String,boolean)/e(String) throws → 覆写 60+ 文件连锁)/synthetic final 去 final×5 (GLTextureRegion/UDPBroadcastListener/DialogHelper/p/ScreenshotSaver)/catch 类型还原×10 (NetEngine 3+ReplayWriter/BackgroundWriter/MusicPlayer/StorageBackend, 02b 铁证)/checked 异常 throws 补链 (bo/MapException/IOException) ×40+ 文件/过度 throws 撤销×4 (MapEngine.a(String,boolean)/Command.f()/ReplayEngine.a(Command,int)/CommandController)/unreachable 删除/变量未初始化两步还原×3 (VariableScope$VariableData*Array)/UnitReference relation() 还原; 新修复器 8 个 (fix_outputnetstream_override_throws_batch 等); 剩余 12 条全部集中于 WebAPIClient.java (11 throws + 1 var13_18 未初始化, 02b j/n.java 已定位), 留待 Phase B
- **v19.133f97 (2026-09-04, Phase B B1 编译清零)**: **41,402 → 0 (-100.0%, 全量 javac_gate PASSED)** 编译零错误里程碑: WebAPIClient 12 条清零 (幻觉 reset2 删除 + 4 方法补 throws + var13_18 两步还原, 02b j/n 铁证) + javac 抑制解除 (F86) 暴露隐藏错误全灭 — synthetic final 去 final×13 (opengl aa/DrawBatch/DrawCallBuffer/GLRenderer×6/BlurEffect, javap 无构造器赋值铁证)/throws 补链连锁 (utility ah+l+filesystem b+c/GameWindow/DesktopGameContainer/Slick2DRenderer/audio backend e+o+s/ThreadedWrapping/Wav/java input k+l+m/PacketDecoder+NetEngine catch 还原/TestLogicBoolean writeToUnit 链 throws bo/platform.a 不可达 return)/reliableudp 家族 51 条 (ReliableSocket 39+ReliableServerSocket 12+ReliableProfile+InputStream+OutputStream+core.Packet 族, Socket/ServerSocket 标准签名); 新修复器 5 个 (fix_webapiclient_b1_batch/fix_opengl_aa_final_batch/fix_reliablesocket_throws_batch/fix_reliableserversocket_throws_batch/fix_reliableprofile_throws_batch)
- **v19.133f98 (2026-09-04, Phase B B2 反向映射核对)**: jar 1,698 类 ↔ 03 1,739 文件全量核对 (新工具 tools/utils/b2_reverse_map_check.py): 游戏类 1,483 **100% 覆盖 0 缺口** (922 语义命中 + 561 混淆直配; 内部类经外层 javac 生成); 第三方 215 (android/librocket/codedisaster) 无需 03; 03 侧 63 no-map 全为映射表/脚本匹配问题非文件缺失; 映射表勘误 2 条 (02b 方法体 1:1 铁证: gameFramework/v ZipReader→GameEngineFactory, java/v DesktopInputProvider→ModDownloader); identity-index.json 重建 (fwd 1,087→1,125, v19.125+ 改名后过期); 产物 b2-jar-to-03-map/b2-gaps/b2-extra/b2-03-reverse/b2-jar-cover.csv (mappings/generated, 可重生成); **B3 构建结论: 全量反向可行无 jar 缺口**- **v19.133f98-B3 (2026-09-04, Phase B B3 全量构建)**: 反向→javac→打包 game-lib-reverse.jar 达成 (0 错误, 362 class, 1,834 类 = 原 1,698 + 新增 136): 攻克 JLS 类包同名硬限制 (40 冲突类+引用方跳过, build-skip.txt 从原 jar 合并)/正则灾难性回溯 3 连/反向盲区 6 连 (fq 全限定/斜杠脏数据/注释类名/\ 完整名/import 误删/keep_cls 重建类); 新工具 tools/fixers/build_reverse_jar.py; class-discoveries 补录 32 删 1 (B2 核对缺口+rule-e 错配)
- **v19.133f98-B4 (2026-09-04, Phase B B4 运行验证)**: **反向 jar 可构建可运行** — game-lib-reverse.jar 替换 game-lib.jar 后 headless 启动 0 异常 (游戏自带 OpenJDK 13, -source/-target 13), 调试服务器 ping→pong, 回放加载成功, AI 活动正常; 运行时修复 15 轮 (宿主感知 per-class 成员映射/方法字段命名空间隔离/枚举字符串恢复 877 常量/原生绑定方法豁免/strip_unused_imports 前置/局部撞车检测/数据勘误 aq→MusicPlayerBase+q 双类+bo/ap 字段); build-skip.txt 136 文件 (jar 原样合并); 残余: B5 行为一致性收敛 (运行时反馈驱动补映射, 待确认)
| v19.133f98-B5 | 2026-08-30 | B5 行为一致性收敛 (运行时反馈 10 项: conf 正则精确化/构造器 \/枚举常量区+显式 super/撞名全限定/声明行跳过/宿主映射优先/类映射勘误 Main-g-GlobalStateFactory/枚举字段补录 q-m; build-skip 136→400 硬限制闭包; 反向 jar 启动 0 崩溃 + 开局/建单位/存档/AI 全通) | 0 |
| v19.133f98-B5.5 | 2026-08-30 | 覆盖率天花板探究 (JDK17 双 jar 方案证伪: 类包同名深包 javac 17/21 均报冲突; 核验: 覆盖 251/1698=14.8%, 运行时反向类 14.4%, 映射 unverified 49.3%; fq 回退匹配尝试 102 类移出 skip 但覆盖净负已回退; 目标修订为行为一致混合运行) | 0 |
- **v19.133f98-逆4 (2026-08-31, 映射库清理战役)**: 390 可疑映射分类闭环 — **334 死映射删除 + 7 构造器恢复 + 15 宿主迁移 + 19 保持注记** (10,797 → 10,448 条): 根因三族 (Phase3 占位名 148 条 do_*/get_+字母/类型+数字 / 语义名从未落地 03 的死映射 186 条 / 列错位残留 1 条 ActionWrapper 加引号修复); 新工具 2 个 (reclassify_dead_mappings.py 三分类清理器 / relocate_members.py 归属迁移器: 03 声明提取×B2类映射×javap类型兼容×顺序zip×域一致性); 键盘家族 aj(抽象空类)→ac(KeyBindings) 宿主+成员双修正 (cameraUpKey=ac.n 等 15 条); 域一致性启发式拦下同名不同义 (TMXMapLoader.String backgroundColor/MapLayerRenderer 纹理 startTileX); 新发现 **528 条 verified 列垃圾值 (292 种, HEAD 确认历史遗留)** 登记 PENDING §5-8 (逆5 候选); 19 条保持 suspicious 均带逆4d 分析注记 (8 多候选 + 11 异域同名, gScore→AStarNode 等高置信跨域候选待家族级迁移)
| v19.133f98-逆4 | 2026-08-31 | 映射库清理战役 (390 可疑 → 334 删+7 构造器+15 迁移+19 注记; 528 垃圾 verified 新发现; 新工具 reclassify_dead_mappings + relocate_members) | 0 |
- **v19.133f98-逆5 (2026-08-31, 映射库质量收尾)**: **528 垃圾 verified 全清零 + 42 陈旧名清除 + 7 高价值迁移** (10,448 → 10,395 条): ① 逆5a 列漂移修复器 (fix_verified_column.py: 原始行字符扫描+括号深度追踪重建成员签名/name/notes; javap 参数级验证含 B2 可读参数名翻译 — supplement 'Texture'↔javap 'e' 兼容; 处置: ini 恢复 211 + exists 258 + 18 截断行删除留档) ② 逆5b 归属迁移器 v2 (type_compat 修饰符 bug 修复 — 'public int' 取末词; k.h↔pathfinding/h 宿主身份覆盖 (构造器+方法序同一性铁证) → gScore→AStarNode.c; 家族跨域放行白名单制 → productionRate/consumptionRate→ResourceRate; 同域 zip: damageMultiplier/displayColor/blockedWaterGrid×2; EffectEngine d.c 28 条陈旧名删除; MapLayerRenderer 纹理同名族三次拦截) ③ 终态: **垃圾 verified 0 / 空 verified 0 / suspicious 15 (带注记) / 7列完好**
| v19.133f98-逆5 | 2026-08-31 | 映射库质量收尾 (528 垃圾 verified 清零 + 7 迁移 + 42 删; 终态 10,395 条全列规范) | 0 |
