# 项目结构（权威）

> 本文件是**布局与清理规则的唯一依据** ✓。改动目录结构时**必须同步本文件** ✓。
> 数字口径见 [STATUS.md](STATUS.md) ✓。

## 一、顶层布局

```
rw-reverse/
├── README.md / CLAUDE.md / CHANGELOG.md / LICENSE    入口与规范
├── BASELINE.sha256        ★ 基线锁（冻结不变量）—— **不可删** ✗
├── build-skip.txt         ★ 构建跳过表 —— **被源文件引用** ⇒ **不可删** ✗
├── .gitignore / .gitattributes / .editorconfig / .github / .mcp.json
│
├── RustedWarfare/         游戏本体（471 MB · **不入 git**）· game-lib.jar = 编译目标
├── 01-classes/            388 个原始 .class（字节码真样抽样 ✓）
├── 02-decompiled/         CFR 反编译（1,698 · 混淆名 · 交叉参考）
├── 02b-decompiled/        FernFlower 反编译（1,698 · 混淆名 · **结构地面真值** ✓）
├── 03-deobfuscated/       ★ 解混淆源码（1,577 / 71 包 · **编译与开发主线**）
├── mappings/              supplement.csv（7,800）· class-discoveries.csv（1,202）· domains/（14 域）
├── tools/                 491 个脚本（见 §三）
├── mcp/                   游戏 MCP 服务器（脚本通道 · 动态测试）
├── docs/                  文档（见 §四）
└── build/                 ★ 暂存区（**不入 git**）—— 见 §二
```

## 二、`build/` —— 暂存区规则

**只保留以下内容** ✓（其余一律视为临时产物 ✗）：

| 条目 | 性质 | 可删？ |
|---|---|---|
| `reverse-classes/` | ★ **候选池 = 交付物唯一来源** | **绝对不可删** ✗✗ |
| `game-lib-reverse.jar` | ★ **交付物本体** | **绝对不可删** ✗✗ |
| `_r104-fix.jar` | 双度量**基线** jar | 否（度量需要它 ✓） |
| `_ab-errors.txt` | 反向世界**错误证据**（普查数据源 ✓） | 否（要重跑 A/B 才能重建 ✓） |
| `ab_run.py` · `tools/rig/repack_product.py` · `tools/rig/verify_atomic_contract.py` | **关键工具** | 否 ✗ |
| `_verify-latest.json` · `_smoke-crash.txt` | **验收证据** | 否（可重跑但费时 ✓） |
| `_quarantine/` | 历轮备份（**保留最新 3 项** ✓） | 旧的可以 ✓ |
| `_*.py`（探针/同步脚本） | 工作记录（小 ✓） | 可以（但建议留作追溯 ✓） |

**已清理**（2026-10-02 ✓）：`build/_archive/`（历史探针，已清理 ✓）(30 MB) · `build-output/`(5.6 MB javac 输出 ✓ 可再生成) ·
旧旁路 jar · `_verify_jar_gate/` · `_humanify/` · GUI 日志 · 超过 3 项的旧备份 · 根 `compile-errors.csv` ✓

## 二·五、★ 门禁活产物（**删了会立刻回来，属正常** ✓）

| 路径 | 内容 | 谁生成 | 说明 |
|---|---|---|---|
| `build-output/` | javac 编译出的 `.class`（≈1,855 个 / 5 MB ✓） | `tools/gates/javac_gate.py` | 门禁的编译输出目录 ✓ |
| `cache/patched-classes/` | 补过 `InnerClasses` 属性的类（门禁 classpath 用 ✓） | 置备工具（`tools/cli.py` 登记 ✓） | 极小的派生类 ✓ |
| `compile-errors.csv` | 门禁的错误明细（含 `location` ⇒ **类型推断的宝库** ✓） | `javac_gate.py` | 分析脚本的数据源 ✓ |

⇒ 这三者**每次跑 `javac_gate` 都会重建** ✓ ⇒ **不要当成垃圾反复删** ✗
  （要清就清，但清完跑一次门禁它们就回来了 ✓ —— 这是**正确行为** ✓）

## 三、`tools/` —— 集成工具链（**净化后 60 个脚本** ✓）

★ **只保留"活工具链"** ✓ —— 阶段性战役的一次性脚本已全部移入 `tools/_archive/` ✗（可恢复 ✓）。

| 目录 | 数量 | 作用 |
|---|---|---|
| `rig/` | 16 | ★ 测试台：`ab_run` · `repack_product` · `verify_atomic_contract` · `verify_rig` · `env_baseline` · `switch_jar` ✓ |
| `core/` | 12 | 核心：交叉验证 · 签名改名 · 反混淆主引擎 ✓ |
| `rwlib/` | 8 | ★ **共享库**：`paths`（路径单一来源 ✓）· `config` · `bytecode` · `mappings` · `classfile` ✓ |
| `analysis/` | 6 | 测量与审计：`measure_03_funnel` · `measure_two_metrics` · `check_doc_links` · `rr_worklist` · `reverse_batchprobe` · `mapping_gap_screen` ✓ |
| `gates/` | 6 | ★ **门禁**：`javac_gate` · `replacement_verify` · `jar_compare_gate` · `class_link_check` ✓ |
| `registry/` | 5 | ★ **集成注册表**：`schema` · `spec` · `catalog` · `runner` · `panel` ✓ |
| `fixers/` | 4 | ★ **构建链**：`build_reverse_jar` · `build_conflict_pass` · `repair_member_names` · `prune_orphan_entries` ✓ |
| `utils/` | 2 | `gen_tools_tree`（索引生成 ✓）· `split_mappings` ✓ |
| 顶层 | 2 | ★ `cli.py`（统一入口 ✓）· `cfr.jar`（反编译器 ✓） |
| **`_archive/`** | **528** | 历史脚本（`campaign/` 439 · `build-probes/` 88 · `manager-legacy.py` ✓）|

### 统一用法（★ 一个入口访问全部工具 ✓）

```bash
python tools/cli.py list                     # 全部工具（按分类 ✓）
python tools/cli.py show  javac_gate         # 看参数（含类型/默认值/控件 ✓）
python tools/cli.py edit  replacement_verify # ★ 交互式调参 → 预览 → 运行 ✓
python tools/cli.py run   javac_gate         # 直接运行 ✓
python tools/cli.py panel                    # ★ 生成 HTML 参数面板 ✓
python tools/cli.py paths / doctor           # 路径总览 / 自检 ✓
```

★ **非硬编码** ✓：所有路径来自 `rwlib/paths.py`（25 项 · 环境变量可覆盖 ✓）
★ **规格驱动** ✓：`registry/spec.py` 从各脚本的 `argparse` **自动导出参数规格** ✓（零侵入 ✓）
★ **旧注册表 `tools/cli.py` 已被取代** ✓ ⇒ 归档为 `tools/_archive/manager-legacy.py` ✓

## 三·五、`tools/workspace/` —— 数据区（原 `build/` ✓）

**只放数据，不放脚本** ✓：池 `reverse-classes/`（**1,281** ✓）· 交付物 `game-lib-reverse.jar` ✓ ·
基线 `_r104-fix.jar` ✓ · 证据 `_ab-errors.txt` / `_verify-latest.json` / `_smoke-crash.txt` ✓ · 备份 `_quarantine/` ✓

★ **兼容层** ✓：`build/` 目录内以 **junction/hardlink** 指向本工作区 ✓
  ⇒ 仍写 `build/...` 的历史脚本**无需改动** ✓（实测经 `build/` 读池 = 1,281 ✓、读交付物可用 ✓）

## 四、`docs/` 结构

| 位置 | 内容 |
|---|---|
| **核心 9 篇** | `README` · `CLAUDE` · `STATUS`（唯一口径）· `ARCHITECTURE` · `VERIFICATION` · `FEASIBILITY` · `PENDING` · `STRUCTURE`（本文）· `INDEX` |
| `deobfuscation/` | `PLAN`（路线图）· `METHODOLOGY`（方法论）· `BUILD-SCRIPTS` · `DYNAMIC-TESTING` · `TOOLS-TREE` · `TOOLS-INVENTORY`（自动生成 ✓） |
| `01-units/` … `14-thirdparty/` | **14 个功能域**，每域一篇丰富中文 README + 子文档 ✓ |
| `_archive/` | 被取代文档（**152 篇** ✓）+ 索引 README ✓ |

## 五、清理规则（★ 定期执行）

```bash
# ① 派生缓存（可随时删 ✓ 见 ARCHITECTURE §五「缓存契约」）
rm -rf .cache cache                     # 分析缓存 / 门禁 classpath 缓存（会自动重建 ✓）

# ② 暂存区临时产物（★ 先确认保护清单 ✓）
#    build/reverse-classes/ 与 build/game-lib-reverse.jar **绝不能删** ✗✗

# ③ 工具索引重生成（脚本增删后必做 ✓）
python tools/utils/gen_tools_tree.py --apply

# ④ 文档链接检查（改文档后必做 ✓）
python tools/analysis/check_doc_links.py     # 相对链接必须 0 失效 ✓
python tools/analysis/check_doc_paths.py     # ★ 项目路径必须 0 失效 ✓（防硬编码回归）
```

## 六、不变量（任何整理后必须复核 ✓）

```bash
python tools/gates/javac_gate.py            # 编译门禁 0 错误 ✓
python tools/rig/verify_atomic_contract.py     # 原子契约「产物 == 池重打包」一致 ✓
ls build/reverse-classes | wc -l            # 池 1,281 ✓
python tools/gates/replacement_verify.py    # 完整验收 PASS 10 / FAIL 0 ✓
```

## 七、★ 归档红线（血泪教训 ✓）

归档 `tools/` 下任何脚本前，**必须同时**检查两条判据 ✓：

| # | 判据 | 检查方式 |
|---|---|---|
| 1 | **`import` 闭包** | 被保留脚本 import 的模块**必须保留** ✓ |
| 2 | **路径引用** ★ | 被保留脚本以**字符串路径**调用的脚本（含 `subprocess` ✓）**必须保留** ✓ |

★ **真实事故（2026-10-02 ✓）**：只查了判据 1 ✗ ⇒ 归档 `fixers/fix_android_innerclasses.py` ✗
⇒ `javac_gate.ensure_patched_classes()` 无法生成 `cache/patched-classes/` ✗
⇒ 门禁报 **11 个** `Config is not public in Bitmap` ✗ ⇒ 已取回 6 个误归档依赖 ✓ 并生成
`tools/_archive/LIVE-DEPS.md`（活依赖清单 ✓）供归档前核对 ✓。

★ 推论 ✓：**`cache/patched-classes/` 不是"可删缓存"** ✗ —— 它是**门禁 classpath 的必需输入** ✓
（由上述 fixer 生成 ✓）。同类的还有 `tools/gates/stubs/` ✓。
