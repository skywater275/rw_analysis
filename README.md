# Rusted Warfare v1.15 — 源码逆向工程

将混淆的 Rusted Warfare v1.15 反编译源码恢复为**可编译、可读、可运行**的未混淆状态。

> **当前进度**: 编译错误 **0** / 41,402 (-100.0%, B1 全量 javac_gate PASSED) + **B2 反向映射核对 0 缺口** + **B3 反向构建 0 错误** (game-lib-reverse.jar) + **B4 运行验证通过** (反向 jar 替换 game-lib.jar headless 启动 0 异常)
> 口径唯一来源: [docs/STATUS.md](docs/STATUS.md) | 战役历史: 会话总览 | 规则: [CLAUDE.md](CLAUDE.md) | v19.133f98 (2026-09-04) 四 README 合并归一

---

## 一、核心数据

| 指标 | 数值 |
|------|------|
| 编译错误 (old_deobfuscated 主分支) | **0** (41,402 → -100.0%; B1 清零实测) |
| **B3 反向构建** | **0 错误** (362 class 编译; game-lib-reverse.jar 1,834 类 = 原 1,698 + 新增 136) |
| **B4 运行验证** | **通过** (headless 启动 0 异常 / ping→pong / 回放加载成功 / AI 正常) |
| 总映射数 (supplement.csv) | **10,395** (字段 6,072 + 方法 4,323) |
| 类映射 (class-discoveries) | **1,294** (B5: 删 Main/g 错误映射 6 行 + 补 GlobalStateFactory) |
| 官方语义名 (05-gamelib) | **482** (0 缺口) |
| 损伤家族已修复 | **40+** (清单见 docs/deobfuscation/PLAN.md) |
| 真实未解析 | **162** (mappings/generated/unresolved.txt) |
| 下一阶段 | **B5 行为一致性收敛** (运行时反馈驱动补映射, 待确认) |

## 二、核心类速查

| 混淆 | 解混淆 | 系统 |
|------|--------|------|
| `gameFramework.l` | GlobalState | 全局引擎 |
| `gameFramework.j.ad` | NetEngine | 网络引擎 |
| `gameFramework.f.g` | InGameUI | 游戏UI |
| `game.n` | PlayerState | 玩家状态 |
| `game.b` | MapEngine | 地图引擎 |
| `game.f` | MovementController | 移动/物理 |
| `game.units.am` | UnitInstance | 单位实例 |
| `game.units.y` | UnitType | 单位类型基类 |
| `game.units.h` | Factory | 工厂/建造 |
| `game.units.custom.j` | CustomUnitType | 自定义单位 |
| `game.units.custom.l` | ModUnitRegistry | Mod注册表 |
| `game.units.a.s` | GameAction | 动作基类 |
| `game.units.custom.e.a` | LogicBoolean | 逻辑表达式 |

## 三、文档导航 (docs/)

### 项目元文档

| 文件 | 内容 |
|------|------|
| [docs/STATUS.md](docs/STATUS.md) | **状态报告** — 数字口径唯一来源 |
| [docs/ORGANIZATION.md](docs/ORGANIZATION.md) | docs/ 整理分类总要求 (12域边界/头部模板/口径/归档) |
| [docs/PENDING.md](docs/PENDING.md) | 待定信息集中存放 (待定身份/残余错误族/未解析/假设/教训) |
| [docs/_archive/05-GAMELIB-RESOURCES.md](docs/_archive/05-GAMELIB-RESOURCES.md) | ⚠️ 已归档: 05-gamelib 资源盘点 (v19.88 快照) |
| [docs/GAME-EMBEDDED-DEAUTO.md](docs/GAME-EMBEDDED-DEAUTO.md) | 游戏自带"反编译"机制全索引 (M1-M8) |
| [docs/07-engine/DEBUG-FEATURES.md](docs/07-engine/DEBUG-FEATURES.md) | 作者调试功能体系 (键位/Debug API/触发通道) |

### 游戏功能域文档 (12 域, 32 篇)

| 域 | 文档 |
|----|------|
| 01-units 单位系统 | COMBAT-COMMAND / UNIT-INI-PARAMS / UNIT-LIFECYCLE / UNIT-LOADING / WEAPON-DAMAGE |
| 02-buildings 建筑 | FACTORY |
| 03-actions 指令 | COMMAND-SERIAL / UNIT-ACTIONS |
| 04-ai AI | AI-ARCHITECTURE |
| 05-map 地图 | MAP-SYSTEM |
| 06-network 网络 | NETWORK-PROTOCOL / NETWORK-STACK |
| 07-engine 引擎 | EVENTS-ACTIONS / GAMELOOP / MATCH-LIFECYCLE / SAVELOAD / STATS |
| 08-rendering 渲染 | AUDIO-HUD / AUDIO-INPUT / RENDERING |
| 09-custom 自定义 | CUSTOM-UNIT / INI-PARSING / LOGIC-ENGINE / MOD-PARAMETERS |
| 10-pathfinding 寻路 | ASTAR-PATHFINDING / MOVEMENT / PATHFIND / SPATIAL |
| 11-platform 平台 | README (占位) / 04-JAVAS-MAIN |
| 12-utility 工具 | DEVELOPER-COMMENTS |

### 反混淆工程 (docs/deobfuscation/)

| 文件 | 内容 |
|------|------|
| [PLAN.md](docs/deobfuscation/PLAN.md) | 工程路线图 v3.23 (B1-B4 达成, B5 待确认) |
| 会话总览 | 战役导航/方法论/损伤家族修复史 |
| [PHASE-A-早期战役史.md](docs/_archive/PHASE-A-早期战役史.md) | 早期战役 (v19.0-v19.102) |
| [PHASE-B-高效方法.md](docs/_archive/PHASE-B-高效方法.md) | Phase B 高效方法 (v19.111 五路流水线) |
| [TOOLS-TREE.md](docs/deobfuscation/TOOLS-TREE.md) | 工具链完整结构树 (含每个修复器条目) |
| [METHODOLOGY.md](docs/deobfuscation/METHODOLOGY.md) | 修复脚本工作规范 F1-F27 + 管线链原则 |
| RUNTIME-PATCH-PIPELINE / PIPELINES-VERIFIED / DYNAMIC-TESTING | 运行时管线/验证/动态测试记录 |
| [sessions/](docs/_archive/sessions/) | 战役详细记录 **86 篇** (v19.107 → v19.133f98-B4) |

### 归档与生成

- [docs/_archive/](docs/_archive/) — 历史归档 **26 篇** (v8-v10 + v19.133f98 域归档 8 篇, 只进不出)
- [docs/generated/](docs/generated/) — 工具自动生成 (禁止手改): method-catalog 24 篇 + runtime-logs (不入库)

## 四、映射库 (mappings/)

### 映射三源 (输入)

| 文件 | 行数 | 内容 | 角色 |
|------|------|------|------|
| `class-discoveries.csv` | **1,301** | 类映射 (混淆包/类 → 语义名 + notes) | 类身份主源 |
| `mappings.csv` / `mappings.json` | 238 | 早期类重命名记录 (v18.x 时代) | 历史补充源 |
| `supplement.csv` | **10,395** | 成员映射 (字段 6,072 + 方法 4,323) | 成员名映射库 |

三源经 `tools/core/identity_index.py` 合并 → `generated/identity-index.json`:
- **fwd 表** (混淆FQN → 语义FQN): **1,125 条** (v19.133f98 B2 重建), 重命名管道权威方向表
- **rev 表** (语义FQN → 混淆FQN): 03 文件身份反查表
- 合并规则: 01-classes/ 文件存在性 + 字节码常量池校验 (铁律: 常量池证明 + 映射命名, 禁止猜测)

### domains/ — 12 域拆分

supplement.csv 按游戏域拆分的 12 个 CSV (01-units ~ 12-utility, 含 11-platform 88 映射), 供域分析文档对照。
supplement 增长后重跑: `python tools/utils/split_mappings.py` (含 domains/README.md 生成)。

### generated/ — 派生数据 (可重生成, 禁手改)

| 分类 | 代表产物 | 生成工具 |
|------|---------|----------|
| 证据与索引 | class-refs.json / descriptors.json / identity-index.json / 05-semantic-audit.csv / unresolved.txt (162) | rwlib.bytecode / cross_validate / identity_index / gamelib_audit |
| Phase B/C 证据链 | type-aware-fixes.csv / varn-fixes.csv (重跑可再生成) | fix_type_aware_calls / fix_varn_clean |
| 回放平台 | replay-command-catalog.csv / runtime-evidence.csv (重跑可再生成) | replay_parser / runtime_evidence |
| 修复器持久化 | gameutils-rename-map.json | fix_gameutils_names (重跑可再生成) |
| **B2 反向核对** | b2-03-reverse.csv / b2-gaps.csv / b2-jar-cover.csv (重跑可再生成) | b2_reverse_map_check.py |

### _archive/ — 备份与已消费产物

- 日期快照: class-discoveries/supplement 20260816 备份 (修复前基线)
- generated-202608/: 已消费中间产物 (radiate-agentA~J ×10 / 旧版 unmapped-bytecode / 诊断输出, 只读审计)

## 五、工具管理 (tools/)

### manager.py (616 注册 = 常驻 39 + 战役修复器 577)

```bash
python tools/manager.py list            # 常驻全列 + 战役修复器按组折叠 (mur 30/netengine 26/...)
python tools/manager.py list --all      # 全量 616
python tools/manager.py list --group mur  # 查指定战役组
python tools/manager.py list --phase    # 阶段分组 (常驻 B5 维护期 vs Phase A 清零战役)
python tools/manager.py check           # 健康检查 (默认常驻 39, --all 全量 616: AST/导入/路径)
python tools/manager.py status          # 项目状态报告 (文件/映射/编译)
python tools/manager.py run <名称>      # 运行指定工具
```

### 目录结构

```
tools/
├── manager.py       ← 工具管理器 (616 注册, v19.133f98 优化: 分类统一 6 类 + list 折叠)
├── core/            ← 核心引擎 (apply_enhanced 6阶段主引擎 / cross_validate / type_renamer / sig_renamer 等 12)
├── gates/           ← 门禁 (javac_gate 编译门禁 / stats 状态报告 / comprehensive 覆盖率; stubs 自动重建)
├── fixers/          ← 战役修复器 564 py (顶层扁平, manager 注册路径稳定; 按战役组: mur/netengine/logicbooleans/...)
├── utils/           ← 小工具 18 (split_mappings / b2_reverse_map_check / comment_audit / method_catalog 等)
├── capture/         ← 运行时捕捉 (debug_client / save_diff_align / sandbox_launch + agent/ 71 java 任务)
├── analyze/         ← 分析 (fernflower_02b / obfuscation_fingerprint / match_ini_params)
├── resolvers/       ← 消歧 (mark_ambiguous)
└── _archive/        ← 归档 47 (历史脚本 + patch_115/ + v12/)
```

### 战役修复器 (577, 按组折叠)

- 每组均有 02b/字节码锚点, `--dry-run` 预览 → `--apply` 应用模式
- 主要战役组: mur 30 / netengine 26 / logicbooleans 7 / combatmain 6 / mapengine 6 / reliablesocket 6 / ai 6 / base 6 / factoryaction 7 ...
- 完整索引: [docs/deobfuscation/TOOLS-TREE.md](docs/deobfuscation/TOOLS-TREE.md)

### 脚本开发规范

所有脚本必须遵守 [CLAUDE.md](CLAUDE.md) 约束 #8 (M1-M7) 与 METHODOLOGY.md 工作规范 (F1-F27):
M1 路径用 rwlib.config / M2 javap/javac 用 find_* / M3-M4 supplement 读写用 rwlib.mappings / M5 CSV 用 csv+field_size_limit / M6 退出码 / M7 utf-8
标准模板见 CLAUDE.md (ROOT = parents[3] + rwlib.config 导入)。

## 六、共享库 (rwlib/, v1.1)

| 模块 | 内容 | 引用 |
|------|------|------|
| `rwlib.config` | 项目路径常量 + JDK 工具查找 (find_javap/find_javac) | 269 个脚本 |
| `rwlib.mappings` | supplement.csv 统一读写 + 类映射加载 | 15 个脚本 |
| `rwlib.bytecode` | javap 封装 (CJK 安全) + 字节码解析 | 3 个脚本 |

> v19.133f98 删除 rwlib.utils (0 引用死代码)。新脚本强制使用 rwlib (manager check 会报告未用 rwlib 的脚本)。

## 七、常用命令

```bash
python tools/manager.py list --phase    # 工具总览 (阶段视角)
python tools/gates/javac_gate.py        # 全量编译门禁 (输出 compile-errors.csv)
python tools/fixers/build_reverse_jar.py --apply   # B3 反向构建 (→ build/game-lib-reverse.jar)
python tools/core/cross_validate.py     # 字节码交叉验证
python tools/utils/split_mappings.py    # 映射域拆分
python tools/utils/b2_reverse_map_check.py  # B2 反向映射核对 (可重生成 b2-*.csv)
python tools/utils/comment_audit.py     # 注释覆盖率审计
python -m rwlib.bytecode                # 字节码分析 (CJK 安全)
```

## 八、当前工作流 (B1-B4 达成后)

```
javac_gate.py                # 编译门禁 (基线 0, 任何大改后全量重跑)
build_reverse_jar.py --apply # 反向构建 (03 → game-lib-reverse.jar)
b2_reverse_map_check.py      # 反向映射核对 (0 缺口)
游戏 headless 启动验证        # 反向 jar 替换 game-lib.jar (B4 流程, 见会话记录)
```

> 下一步 B5: 行为一致性收敛 (运行时 NoSuchMethodError/NoSuchFieldError 反馈驱动补映射, 待确认)。

## 九、法律与用途声明

- 本项目是 Rusted Warfare (锈战) v1.15 的**源码逆向研究工程**, 仅用于**学习与研究** —
  理解游戏机制、分析反编译损伤、研究字节码验证方法论。
- 游戏版权归原作者 **Luke Hoschke** (Corroding Games) 所有, 本项目不主张任何游戏资产
  与代码的权利; 游戏本体 (RustedWarfare/ 目录) 不入库、不分发。
- 逆向产出 (反编译源码/映射数据库) 仅限**项目内部协作**使用, 禁止用于商业用途。
- 如版权方提出要求, 将立即移除相关内容。

## 十、参与协作

- 问题与映射请求: [.github/ISSUE_TEMPLATE](.github/ISSUE_TEMPLATE)
- 变更历史: [CHANGELOG.md](CHANGELOG.md)
