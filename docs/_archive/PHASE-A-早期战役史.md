# PHASE-A 早期战役史 (v19.0 → v19.102)

> 2026-08-23 | 由 docs/STATUS.md 历史节迁移而来 (去重整理), 内容原样保留
> 覆盖范围: R0-R4 循环 (v19.0-v19.9) + R4 循环七~十一 (v19.10-v19.81) + 环境资源期 (v19.82-v19.97) + 辐射批次 (v19.100-102)
> 当前战役进度见 [STATUS.md](../STATUS.md); v19.107 起的战役会话见 [PHASE-A-会话历史总览.md](PHASE-A-会话历史总览.md)

## R0-R4 循环 (v19.0-v19.9, 确定性重建工具地基)

### v19.0 (R0: 确定性重建工具地基) 修复记录

1. **主编译器切换: 游戏自带 JDK 13** (RustedWarfare/jvm64, OpenJDK 13+33) — 实测与 javac 17 错误画像 99.7% 一致 (4,287 vs 4,281 条, 共同 4,266), InnerClasses 行为无差异; javac 13 为游戏 64 位版原生运行时, 字节码版本精确匹配
2. **rwlib/bytecode.py: extract_class_refs()** — 常量池引用提取 (tag-7 Class 条目 + Utf8 描述符 `L<FQN>;` 签名扫描双来源); **顺带修复 tag-8 CONSTANT_String 反序列化漂移 bug** (旧 parse_class_binary 将 String 误并入 +4 组, 50 个类解析失败, 修复后 1,698/1,698 全部成功)
3. **mappings/generated/class-refs.json** — 全 jar 1,698 类常量池引用集 (仅游戏命名空间, R3 重命名器的字节码真相源)
4. **tools/core/identity_index.py** — 三源合并身份索引: 804 候选 → 650 fwd 有效 (154 畸形剔除) / 608 rev (03 文件身份) / 40 冲突 / 1,087 未解析 (third-party 207 + obfuscated-name 125 + readable-unmapped 755)
5. **javac_gate.py 升级** — compile-errors.csv 增加 symbol/location 列 (4,239 symbol / 3,695 location 捕获)
6. **重要发现**: fwd 三源一致映射 d.e→HUDElement — 探索代理早前"ShaderEffect=d.e"判断有误, ShaderEffect/BitmapTexture 的真实混淆身份需 R1 字节码裁定 (常量池引用 + 成员签名比对)

### v19.1 (R1: 幻影名仲裁 + 丢失类恢复) 修复记录

1. **字节码仲裁推翻"5 丢失类"结论** — 真相: 只有 2 个真丢失 (PacketDecoder, UnitType$1); 其余 3 个是启发式修复器发明的幻影名:
   - `ShaderEffect` (199处/13文件) = **HUDElement** (d.e) 的误改名 — 仲裁: 02 同位置锚点 `l2.bR.d(...)` 返回 d.e
   - `BitmapTexture` (172处/20文件) = **Texture** (m.e) 的误改名 — opengl/BitmapTexture.java (b.e, 34行) 是真类保留, 仅 opengl/f.java 为真引用排除
   - `BaseUnit` (142处/18文件) = **UnitType** (y) 或 **UnitInstance** (am) 的误改名 — y=UnitType 已存在 03 (5,134行), class-discoveries "y→BaseUnit v18.3-verified" 是误标, 已修正
2. **幻影仲裁算法**: 02 锚点行重叠度投票 (防同名局部变量跨方法误配) + 字节码引用集过滤 (双重索引 bug 修复后) + 手工裁决表 (20 文件)
3. **真丢失类恢复**: PacketDecoder (02 j/c.java 368行) + UnitType$1 (02 y$1.java, 原错名为 BitmapFont$1) 经 constant_pool_renamer 管道恢复; renamer 类型位置判定修正 (关键字守卫 final/false/new/long, 空白分隔要求, 自类引用)
4. **StopAction 旧包修正**: 52 条 `units.a.X` → `units.actions.X` import (49 文件) + 20 条重复 import 清理
5. **真实错误基数揭晓: 41,402 条** (1,196 文件) — maxerrs 5,000→100,000; 此前所有"~5,000 错误"测量均为封顶截断假象, 真实规模 8 倍: unresolved-symbol 31,257 / other 10,064 / dup-def 81
6. 新画像: 前 5 缺失符号 l(1,446) a(956) B()(817) b(806) e(719); 前 5 文件 CustomUnitType(2,438) UnitType(1,893) ModLoader(1,412) NetEngine(1,342) GameScreen(1,268)

### v19.2 (R2: import 全树重写 + LogicBoolean) 修复记录

1. **import_rewriter.py 三条规则** (幂等, 二遍零变更): fwd 整条重写 + PKG_MAP 最长前缀 (58包, 目标 FQN 须在 03 树/fwd 值中, 防 jar 混淆类误改) + 树唯一位置解析 (包未迁移残留 import → 03 实际位置)
2. 应用: **244 条 import 重写** (153 文件, audit 存 mappings/generated/import-audit.csv); tree-unique 106 文件零误伤
3. **LogicBoolean 方案B**: `LogicBoolean.LogicBoolean$` → `LogicBoolean$` 163 处/58 文件 (字节码证实 Parameter/ReturnType 为顶层类)
4. **混淆文件名重命名推迟**: fwd 未覆盖这 205 个类 (如 slots/d, custom/af) — 保留原名不阻塞编译, R5 内容指纹补身份
5. 门禁: 41,402 → **41,326** (-76); 印证 import 层修复仅小步 — 体部混淆 token 是主杠杆 (R3)

### v19.3 (R3 常量池主 pass + R4-A 成员回退) 修复记录

1. **R3 --tree 应用**: 271 文件, FQN 1,005 + token 2,392 (词边界守卫防 filesystem 前缀误伤; cast 保留括号; \ 十六进制 lookbehind 规避 Python 转义怪癖; 转义序列守卫)
2. **member_revert.py** (R4-A): 错误驱动的声明侧成员名回退 — 3,023 处/82 文件 (supplement 归一化: obfuscated_member 去参数表; 含空白语义名跳过; 混淆名已存在→删除 v18.x 语义重复声明; 用法行不触发删除)
3. **级联坍缩**: GlobalState B() 686 处 → 0; bX/bQ 大幅下降; 41,402 → **38,961** (symbol 31,257→28,385)
4. **确定性重放管线**: 03 树全量还原+重放 (用户授权) — checkout HEAD → R3 --tree → member_revert 全部幂等可复现
5. **剩余画像**: 半改名FQN ui.g 472 / UnitInstance 根因 575 (PacketHandler/ResourceCost/AnimPose 类型 + TimedBomb/AmphibiousUnit CFR 推断错误) / HoverUnit 误改名 (l2.bO 模式) / 81 dup-def 为 v19.2 既有 CFR 产物

### v19.4 (R4: 系列错误驱动修复器) 修复记录

1. **半改名FQN修复** (144 文件 619 处): meaning包.obf类名 → meaning FQN, 词边界守卫 (v1 无边界曾误伤 filesystem 前缀, 已逆转 392 处)
2. **ui.X 双义裁定** (333 处): 逐文件常量池证明 — 类 f.g (InGameUI) vs 类 f 静态方法 g (GameRenderer.g)
3. **幻影名第三批**: AnimPose → UnitTrait (34处), ResourceCost → CustomActionBase (112处); resources/CustomActionBase (02 custom/d/b.java) 经 renamer 恢复 (真丢失类 #3)
4. **member_revert 空loc扩展**: loc 为空的成员错误按 supplement 定位声明类回退 — 2,250 处, 引爆 35,104 → 1 的分层坍缩
5. **覆写回退** (475 处): @Override 方法名按 supplement 反查回混淆名 (父类回退后子类同步)
6. **obf 命名文件类声明修复** (5 处): 类声明被误改可读名 → 恢复文件名一致
7. **R3 --tree 扩展**: 逆 PKG_MAP 推导混淆身份, 覆盖 obf 命名文件 (+41 文件 676 token)
8. **级联分层坍缩实录**: 每修完一层语法/结构错误, javac 恢复依赖编译暴露下一层: 41,402 → 38,961 → 35,177 → 35,104 → 1 → 34,923 (层级循环进行中)

### v19.5 (R4 循环第二轮) 修复记录

1. **@Override 清理**: 2,801 处不匹配注解删除 (父类成员回退后子类注解失效; 编译目标优先, Phase B 可恢复) — 修了空行还原 bug (`"" or 原行` 惯用法)
2. **BitmapFont$1 双源修复**: 89 处 — 算术位还原数字 1 (23 处, fix_inner_all 历史损伤) + 类型位替换 UnitType$1 (66 处)
3. **继承链真相** (字节码 super_class 链): UnitInstance extends PacketHandler 是误改名 — am → ay(Sprite) → az(SpriteBase) → w(EffectConfig) → bq(BaseGameObject); eo/ep/eq 声明在 EffectConfig — 修复 extends + 恢复丢失的 gameFramework/EffectConfig.java (02 w.java 经 renamer)
4. 41,402 → **31,392**

### v19.6 (R4 循环三: 内部类恢复) 修复记录

1. **inner_class_restorer.py**: 02 的 693 个 $ 文件 vs 03 缺失 — 310 个内部类经 renamer 管道恢复 (父类 fwd 映射 + $ 后缀); 跳过 345 (父类无映射/已存在)
2. **批量 public 化**: 87 个 "not public" 错误类加 public (AssetLoader 等)
3. **点号内类形态修复**: CFR 的 h.2 → Factory$2 (5 文件)
4. 层级坍缩实录: 31,392 → 12 → 31,931 → 15 → 33,443 (内部类恢复暴露更深层)

### v19.7 (R4 循环四: 数字损伤族) 修复记录

1. **算术位 X$N → N**: 346 处/66 文件 (fix_inner_all 历史损伤的家族性修复 — 内类名出现在算术位恒非法; 一度误伤注释 */ 与成员访问 a$N.c, 已部分修复)
2. **点号内类形态内建 renamer**: h.2 → Factory$2 规则加入 rename_02_file (父类 fwd 映射, 内部类文件用父类混淆名)
3. **DataStreamReader$1 双源** (23处) + @Override 清理 624 + EffectConfig ctor 验证
4. 坍缩实录: 33,443 → 3 → 32,799 → 53 → 1 → **32,356**

### v19.8 (R4 循环五: extends 审计) 修复记录

1. **extends 全量审计+修复**: 63 处 extends 子句与字节码 super_class 不一致 (AbstractUnitBase→UnitInstance 等) — javap 证实 02 的 CFR 会丢包名 (k extends x 实为 a.x), 提取器为准
2. **l.B() 族**: 359 处 → GlobalState.B() (成员回退后方法名为 B 非 getInstance, 363 处 getInstance 已回转)
3. **DataBuffer 误改名族**: utility.y 的污染映射 (y.DataBuffer) → 实为 PathfindingUtils — 83 处替换 + import 修正 + 双写 FQN 清理
4. GlobalState import 补齐 (UnitInstance 参数类型正确但缺 import)
5. 41,402 → **30,813**

### v19.9 (R4 循环六) 修复记录

1. **双写FQN族**: extends 审计叠加产生的 Sprite.Sprite 形态 (6 文件折叠; 495 处全树双写折叠后经用户授权重放修正)
2. **EffectConfig 私有构造器 public 化** (144 处 — $N 为剥离 InnerClasses 的顶层类, 无法访问私有外层成员)
3. **类声明错位族**: 5 个 obf 命名文件的类声明被误改为他类名 (l.java→ModUnitRegistry 等) — 声明改回文件名
4. **UnitInstance extends 修正**: Sprite.corrodinggames... 双写 → 正确 FQN, 继承链恢复
5. 41,402 → **29,375**

## R4 循环七~十一 (v19.10-v19.81)

### R4 循环七 (v19.10-v19.13) 摘要

| 修复族 | 数量 | 方式 |
|--------|------|------|
| utility.network 幽灵包 | 82 处 | 02 锚定: utility.a → filesystem.SAFFileManager |
| KeyEvent 数字损伤 | 4 处 | 02 锚定: KeyEvent$1 → 常量 1; Callback 接口并入源 |
| final ctor 族 (构造器改名+去void) | 103+117 | fix_final_ctor + fix_void_ctor |
| super-first ctor 族 | 124 | fix_ctor_first 三形态 (改名/移动/synthetic_init) |
| SpriteHandle 幻影名族 | 34 处 | 02 锚定分义: m.l→Renderer / l→GlobalState |
| utility.X 误标族 (WorldGenerator/KeyCodeMapper/GameStateEnum/UnitBehaviorEnum/PacketType) | ~25 处 | 02 import 逐处锚定: utility.m→CustomArrayList, utility.o→DequeList |
| @Override 重复/非法 | 13 处 | 空行分隔双注解去重 + 构造器注解删除 |
| 新损伤: aicore 包未解析身份 | 24 文件 | 冻结, 待内容指纹 (PLAN 剩余#6) |

### R4 循环八~十摘要 (v19.16-v19.71)

| 循环 | 家族 | 代表修复 |
|------|------|----------|
| 八 | 类型误标/语义名广播/CFR丢字段/枢纽类 | AirUnit×11, UIComponent×171, SubBuildingType1×48, setFactoryLink 44方法回退, NetEngine 数字损伤×63, GameScreen i$a×200, f-to-GameUtils 127映射表 |
| 九 | 缺import/冗余副本/字段类型枢纽 | import×237, NetworkUtils$N×53删除, GlobalState.bX KeyBinding→NetEngine (-228), bL ByteSlot→TileEntry (-143), dup-def 105→33 |
| 十 | 真丢失类/字段对齐管线/幻影名清零 | ResourceUnit 恢复+enum化+52匿名体内联, field_align 三批356处, UIContextMenu×218/UIPanel/UITextField 清零, UnitInstance.bX→PlayerState (-64), ByteIndexedMap getb 丢方法恢复 |

**循环十一(进行中)**: 多 agent 并行 — A=19丢失文件恢复(InGameUI 缺482方法/TileEntry 缺222) / B=incompatible 对族 / C=ResourceUnit+aicore 身份

### R4 循环十一摘要 (v19.78-v19.81, 三路 agent 并行)

| 路 | 成果 | 效果 |
|----|------|------|
| A 丢失文件恢复 | 19 文件 02 方法体指纹合并 (merge_lost.py 工具) — TileEntry 20→1501 行 (223 方法), InGameUI +97 方法, FontRenderer +350 行 | 恢复 ~10k 行内容 |
| B incompatible 对族 | ActionPanel 191→65 (9 家族 02 锚定), SelectionGroup 恢复 4 方法, EffectConfig 补 2 静态方法 | -126 行错误 |
| C ResourceUnit+aicore | aicore 24/24 身份解析 (jar gameFramework.n.*), 24 fwd+27 rev 映射, MissionEvent 枚举重建 | 映射补全 |

合并后 gate: **26,011 → 25,071** (-940)。撕裂文件 (A 中断产物) 经 merge_lost 深度跟踪修正重做。

## v19.82-v19.97 摘要 (环境资源 + 动态测试平台)

### v19.82-v19.89 (环境资源 + 映射扩张 + 循环十二开跑)

| 版本 | 内容 | 错误 |
|------|------|------|
| v19.82-19.85 | 04-javas/05-gamelib/06-lib 建立; 02=05 完整性验证; 482 官方名审计; unresolved 修正 1,037→562 | 25,071 |
| v19.86 | org/a=joda-primitives (19类); 3 误标修正; 03 org 子树恢复; CSV 截断修复; aicore 25 固化; L2 bh/ab | 25,048 |
| v19.87 | Rule D: $N 内部类继承外类名 +314 | 25,048 |
| v19.88 | Rule E: 反向字符串指纹 +64 (ModLoader/SAFFileManager/ReplayComparator...) | 25,048 |
| v19.89 | 循环十二: InGameUI/UnitInstance/InputNetStream 字节码真型修复 + av→WeaponTypeEnum×12 | **24,965** |

### v19.90-v19.97 (动态测试平台八轮迭代 — 回放播放+保存双平台)

| 版本 | 内容 |
|------|------|
| v19.90 | 游戏 headless 启动 + 42 单位键 + runtime_evidence 工具 (338 行证据) |
| v19.91 | 探针 mod 追踪: 加载管线阶段序列 + modSettings hash 破解 + 43 单位图导出 |
| v19.92 | 地图加载管线 + MapException 运行时确认 + debug 轮次 |
| v19.93 | JFR 零插桩: OggInputStream 热路径 + 异常率 ~80/s |
| v19.94 | 回放管线逆向 + headless 播放成功 (root.loadReplay 脚本链路) + JFR 全模块采样 |
| v19.95 | 回放命令流解析器: 8,717 真实命令 + 2,589 校验和 (网络管线真实数据验证源) |
| v19.96 | **回放保存平台建成**: 新游戏脚本链 + 自动录制 (Commands issued: 3) + **mods 启用之谜解开 (安全模式)** + debug_script.py 客户端 |
| v19.97 | **v96 命令格式完全破解** (写侧字节码全序 + d 子对象 "p" 压缩子流): 命令覆盖 8,717→**14,184**; 执行日志↔解析器互证; es 校验和帧0全匹配 + AI 分叉模式 |

## v19.100-v19.102 辐射批次

详见 [../_archive/RADIATION-BATCHES.md](../_archive/RADIATION-BATCHES.md) (五大基础辐射批次方法论 + 4 批落库)

## v19.108 批量脚本化会话 (21,854 → 19,337, -2,517)

| 批次 | 内容 | 错误数 | 降幅 |
|------|------|--------|------|
| Batch 1 | PlayerState 四层指纹配对 + GameUtils 轮2 + WeaponConfig 类改名 + amilesystem 假包 | 21,299 | -555 |
| Batch 2 | NetEngine 确定性替换 + UnitTurret 字段保序 + 数字位广播 + 内部类双恢复清除 | 21,276 | -23 |
| Batch 3 | WeaponConfig/actions.d 字段保序 + br→ExtraManager + ModLoader 67/67 + actions 类 + j/k 仲裁 | 20,894 | -382 |
| Batch 4 | 泛型擦除 cast (-320) + k2 全局 229 + ScreenshotSaver + TileEntry→MapEngine + 5 类指纹配对 + libRocket + amilesystem 包残留 | 19,337 | -1,557 |

**方法论沉淀** (已写入 CLAUDE.md 修复脚本工作规范 F1-F6):
- 四层指纹配对器通用化 (fix_method_pair_generic.py): 字面量≥2→≥1→token LCS→行号回归
- 字段保序 1:1 同步 ×4 (UnitTurret 12 / WeaponConfig 17 / actions.d 25 / MapEngine 18 字段)
- 广播源级联: j.k 类名一处纠正 → 全项目 400+ 调用点自动消错 (PacketHandler/InputNetStream/ScreenshotSaver 三变体)
- 关键身份裁决: 03 ExtraManager = 02 br; 03 InputNetStream = 02 j.k 内容; PacketHandler = j.ay (super(175)); ScriptContext.libRocket = LibRocketBridge

## v18.6-v18.9 修复记录 (R4 前史)

1. package_renamer应用 — 43个混淆包→可读名 (697文件迁移, 所有import重写)
2. 额外6个包冲突修复 (ai→aicore, i→mods, l→core, o→steamworks, utility.a→filesystem, units.c→special)
3. do-keyword 3处引用修复 (GameScreen:306, CustomUnitType:1092,1111)
4. LogicBooleanLoader object42初始化 (Object object42 = string3)
5. @Override 174处无效标注移除
6. **v18.7**: extends/implements 4处结构错误修复 (BuildingBase, DataStreamReader, DesktopAppFramework, FactoryAction2/FactoryBuilding)
7. **v18.7**: A13 — fix_enum_ctors.py: 28个enum文件CFR构造参数修复 (301 errors → 0)
8. **v18.8**: A14 — fix_duplicate_defs.py: 96处重复定义重命名 (104 → 31, -70%)
9. **v18.12**: fix_inner_all.py — 内层类导入修复 (1,655 refs, javac17 ProGuard InnerClasses 问题)
10. **v18.13**: fix_single_char_types.py — 上下文感知单字符类型重命名 (4,972 refs, 420文件)
11. **v18.15**: fix_javac_symbols.py — javac反馈修复器 (1,940 refs)
12. **v18.16**: core→GlobalState (1,789 refs) + 7类public化

## Git 分支史 (v18.6 时代)

```
old_deobfuscated (主)
├── 699fd6d v18.6: package_renamer + do-keyword + LogicBooleanLoader + @Override
├── 68e26f8 v18.5: Update docs — 双分支策略, 12 errors baseline
├── 9d05286 old_deobfuscated branch: 5,007→12 errors
└── ... (Phase A修复链)

apply_enhanced_rebuild (重建)
├── 6ffe7be apply_enhanced: 403→2 errors
├── 62c7f51 Step 0: apply_enhanced clean rebuild (466 errors)
└── ... (一致性重建链)
```
