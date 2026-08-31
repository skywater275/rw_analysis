# METHODOLOGY — 修复脚本工作规范 (方法论沉淀)

> 版本: v19.133f98 | 日期: 2026-08-31 | 本文件承接 CLAUDE.md 精简前的修复脚本工作规范 (F1-F27 等, 2026-08-31 精简战役移出)
> 导航: [CLAUDE.md](../../CLAUDE.md) | 战役轨迹: PLAN.md 会话行

## 修复脚本工作规范 (v19.108 管线链方法论沉淀)

> 这些规范来自 v19.108 批量会话的实战验证, 新写修复脚本必须遵守:

### 管线链总原则

1. **广播源优先**: 一个声明端/类名错误会级联出全项目数百个调用点错误 —
   先修声明端 (类改名/字段改名/方法改名), 调用点由 javac 自动匹配或 CSV 驱动修复。
   (v19.108 实证: j.k 类名一处纠正 → 全项目 400+ 调用点自动消错)
2. **CSV 行号驱动**: compile-errors.csv 的 symbol/location 列是唯一修复入口 —
   location 过滤宿主类, 行号定位调用点, 禁止全树盲目替换。
3. **层级坍缩警惕**: 错误数骤降至个位数 = 有语法错误阻塞 javac 解析,
   修复语法错误后错误会重新爆发 — 每次大改后必须全量重跑 javac_gate。
4. **广播原子提交**: 跨文件改名必须在同一个 commit 内完成, 禁止半修复状态入库。

### 修复脚本必守规则 (在 M1-M7 基础上)

| # | 规则 | 说明 |
|---|------|------|
| F1 | **路径解析** | CSV file 列是相对路径 (`03-deobfuscated/...`) — 用 `ROOT / fname`, 禁止拼 `DEOBFUSCATED_DIR / fname` (双前缀 bug 曾致 0 替换) |
| F2 | **方法调用排除** | 字段替换必须加负向前瞻 `(?!\s*\()` 排除方法调用形态 (`.a(` 是调用不是字段) |
| F3 | **02 锚点必附** | 每个批量替换必须有 02 对应行/import/指纹证据, 禁止纯猜测 — 铁律不变 |
| F4 | **上下文限定** | 行内替换需限定宿主上下文 (变量名/强转/类型), 防同名字段误伤其他对象 |
| F5 | **重载按参数数** | 方法映射键 = (02名, 参数个数); 类型串辅助; 多候选歧义时跳过并记录 |
| F6 | **dry-run 先验** | 写操作前先跑预览模式, 检查替换数量与预期一致再 --apply |

### 四层指纹配对器 (fix_method_pair_generic.py)

任意 02/03 类对的方法配对标准流程 (保序 zip 在混合损伤下失效):
1. 字符串字面量重合 ≥2 (重命名只改标识符, 字符串保留)
2. 字面量重合 ≥1
3. 归一化 token 流 LCS 相似 ≥0.55 (无字面量方法)
4. 行号线性回归插值 (锚点斜率, 最近候选 <90 行)

使用: `python tools/fixers/fix_method_pair_generic.py <02路径> <03路径> [--fix-callsites]`
使用: `python tools/fixers/auto_align.py [--dry-run] [--max-rounds N] [--rules R1,R3,R5]` — v19.113o 全自动编译错误补全 (7规则+gate循环+净收益回退, 详见文档头)
已实证: PlayerState 103/128、ModLoader 67/67、NetEngine、UnitInstance、GlobalState、j/k 42↔41。

### 字段保序同步模式

02/03 字段声明序列 1:1 保序时 (类型可不同), zip 对齐生成映射表,
CSV location 过滤宿主 → 行号驱动调用点替换。已实证: UnitTurret 12 字段、
WeaponConfig 17 字段、actions.d 25 字段、MapEngine 18 字段。

### v19.115r 补充方法论 (logicBooleans 战役沉淀)

| # | 规则 | 说明 |
|---|------|------|
| F7 | **遮蔽 import 陷阱** | 显式 import 优先于同包类 — `extends LogicBoolean` 可被 `import effects.LogicBoolean` 劫持 → "does not override"/"cannot find symbol" 假象 (v19.115r 两处) |
| F8 | **`$` 顶级类引用** | 03 侧 `$` 文件是顶级类, 引用必须 `$` 裸名或全限定; 点号形式 = 嵌套类语义 → javac 报错 (37 处 LogicBooleanContext 广播源) |
| F9 | **替换子串误伤** | 短规则 (`n.c`/`q.f`) 会误伤长标识符子串 (`LogicBoolean.c`/`Pattern.c`) — 替换必须带单词边界或更长上下文, 并全量扫描 `(\w+)PlayerState\.` 类残留 |
| F10 | **混淆歧义类名** | 同名混淆类跨包是不同类 (custom.t=ModifierApplier vs units.t=AmphibiousUnit) — 以 02b import 行铁证裁决 |
| F11 | **追加前读字段区** | append_to_class 前必须读目标类字段区 (03 字段常与 javap 完全一致) — 重复追加致 duplicate 暴涨 |
| F12 | **查证正则含参数名** | `b(int, int, int)` 匹配不到 `b(int n2, int n3, int n4)` — 三次假阴性致重复追加 (v19.115q 教训) |

### v19.115t 补充方法论 (CombatMain 战役沉淀)

| # | 规则 | 说明 |
|---|------|------|
| F13 | **保序字段对照法** | 02b↔03 字段区 1:1 保序 zip → 混淆字段名一次映射 (AIStrategy 18 处: aY→allowExpansion, bC→buildingFactories 等); 序列化顺序二次校验 |
| F14 | **方法名序错位** | 03 方法名整体偏移时用方法体内容对照 (非行号); 顺序替换链 (m→n→o→q) 会级联污染 — 从后往前或按行号修正 |
| F15 | **子串串扰再犯** | `this.R.bu`→attackingUnitsWater 误伤 `buildingFactories` → "attackingUnitsWaterildingFactories" — 短规则替换必须带更长上下文 (F9 重演) |
| F16 | **幻觉枚举文件** | 03 WeaponTypeEnum 早期误建, 但 02b av.java 17 常量 (move→setPassiveTarget a-q) 与之完全一致 → av=WeaponTypeEnum 成立; 类注释 (CustomWeaponAction 头部) 也是锚点 |
| F17 | **raw 集合 enhanced-for** | 02b 显式 `(o)var3.next()` 强转 → 03 enhanced-for 报 Object 不兼容, 需改回显式迭代+强转 |
| F18 | **工厂链补全** | `l2.cf.a(this.R)` = CommandController.a(PlayerState)→b(PlayerState)→new Command(this); 补方法沿调用链一次补全 (02b c.java L44/L48) |

### v19.132wz 补充方法论 (NetEngine调用点/MainUIController/ReplayEngine 三战役沉淀)

| # | 规则 | 说明 |
|---|------|------|
| F23 | **方法序整写重建** | 03 方法名语义化错位 + 重复方法 + 幻觉类型三重损伤时修补不如整写: 02b 全文直译, 方法名保持混淆名 (外部调用点匹配), 类型逐项探针确认 (ReplayEngine 84 清零实证) |
| F24 | **重复类裁决** | 同名语义类双副本时以方法体完整度 + 家族引用分布裁决: 保留有完整方法体+被引用的, 删除简化误建副本并修正引用 (StatisticType/StatsGrouping 误建 → audio/DataFieldProvider/DataFieldInt 胜出) |
| F25 | **$N 数字污染** | 混淆类名 $N 顶替字面量数字 (MainUIController$2→2 等 5 处): 02b 对照行号还原 |
| F26 | **重载爆炸陷阱** | 大量同名重载 (registerRelayServer ×178) 时裸 null 参数报 ambiguous; 显式强转 (String) null 消除; 该类污染编译可通过但语义全错, 需 02b 逐方法核对 |
| F27 | **02b 类型映射勘误** | 旧映射纠错: gameFramework/w=GameObject (非 EffectConfig), j/ak=ChecksumCalculator (非 NullInput), units/custom/d/b=ResourceComponent, game/a/a=AIStrategy, appFramework/i=ContextMenuActivity, appFramework/j=ButtonActivity, gameFramework/j=FileWatcher, gameFramework/l/a=PlatformExtension, librocket/a=LibRocketContext |

