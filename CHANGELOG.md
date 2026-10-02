# 变更日志 (CHANGELOG)

> 格式: 每个战役节点一行 (版本/内容/错误数变化)。
> 完整战役史:  (86 篇会话记录)。
> 更早历史 (v8-v10): git tag (v10.4-FINAL 等) + docs/deobfuscation/PHASE-A-早期战役史.md。

## B 系列: 从可编译到可运行 (2026-08-30 ~ 08-31)

| 版本 | 里程碑 | 结果 |
|------|--------|------|
| **B1** | 编译清零 — 41,402 → **0 错误** (javac_gate PASSED) | ✅ |
| **B2** | 反向映射核对 — **0 缺口** | ✅ |
| **B3** | 反向构建 — game-lib-reverse.jar (1,834 类 = 原 1,698 + 新增 136, **B3 时点值**; 当前实测 **1,850 条目 / 1,849 class** 见 docs/STATUS.md) 0 错误 | ✅ |
| **B4** | 运行验证 — 反向 jar 替换后 headless 启动/回放/AI 正常 | ✅ |
| **B5** | 行为一致性收敛 — 运行时反馈 10 项修复, 0 崩溃全通 | ✅ |
| B5.5 | 覆盖率探究 — JDK17 双 jar 方案证伪, 目标口径修订 | 结论固化 |
| B5.6 | 映射验证 v2 — 726 可疑映射构建器跳过 (安全失败) | 进行中 |
| **H-8** | 声明侧产物名守卫 (2026-09-23) — 反向器只生成目标类字节码中确实存在的成员名; 配套宿主键别名 (可读名→混淆名) 与 CFR 消歧残名遮蔽护栏。修复 3 处成品/原版成员名不一致 | ✅ 编译 0 错误; 门禁 FAIL 3 → **0** |
| **H-9** | **根因修复 (2026-09-24)** — 不打补丁, 改数据源: ① `supplement.csv` **列错位 172 行**修复 (方法签名未加引号的逗号致列左移, 语义名被挤出) → 恢复 45 条方法映射; ② **短写包名 1,353 行**确定性解析 (`mappings/generated/host-resolve.csv`); ③ **宿主键口径归一** + D-4 宿主约束放开到全部类 (原先对有类映射者早退, 正是 P1 存活机制) | ✅ 编译 0 错误; 门禁 FAIL **0** (487 REVIEW / 1252 PASS); 守卫拦截 8→4 |

> **H-8 新增门禁**: `tools/gates/jar_compare_gate.py` — 逐文件 JAR 对照门禁 (原版 jar 为地面真值),
> 1,739 个 03 文件逐个独立出结论+证据。后续扩展: 无类映射但原版有同名类的校验分支 + W3 描述符级检查。
> **H-9 新增工具**: `tools/_archive/campaign/fixers/fix_supplement_columns.py` (列错位修复器, 默认只读)、
> `tools/_archive/campaign/fixers/gen_host_resolve.py` (宿主解析表生成器)。

## v19 系列: 错误消减战役 (2026-08-13 ~ 08-30)

| 版本 | 战役 | 错误数 |
|------|------|--------|
| v19.107 | Phase A 会话 (12 损伤族) | 23,778 → 22,135 |
| v19.108 | 批量脚本化 (四层指纹配对器) | 21,854 → 19,337 |
| v19.109 | 核心链路 + 批量修复2 | 19,337 → 18,150 |
| v19.110 | 测试族攻坚 | → 15,987 |
| v19.113 | 运行时代入捕捉 — **Phase A 完成** | — |
| v19.114 | 类型感知 + 02b 指纹 + varN + 工厂编队 | 16,739 → 16,315 |
| v19.115 | RenderLayer 污染 + Factory 家族 | 16,315 → 15,169 |
| v19.115c-z | TMI/MapEngine/PlayerState/AI/Command/base/ay/logicBooleans/ReliableSocket/NetEngine/MapRenderer/AI包 清零系列 | 15,169 → 10,086 |
| v19.116 | UnitRegistry 重建 + ModLoader 清零 | 10,086 → 9,653 |
| v19.117 | GameEngine/CustomUnitType/CustomActionBase/MovementController/HUD/InGameUI/Minimap/PathSolverRunner/PathFinder 族清零系列 | 9,653 → 8,036 |
| v19.118-128 | GameSaver/建筑运输/行为/水族/动画/ScriptEngine/TeamUnitTracker/UnitTypeHandle/anima 双副本 系列 | 8,036 → 5,613 |
| v19.132 | Bitmap$Config 符号表污染根因 + not public 五批 | 5,200 → 4,823 |
| v19.132w-z | NetEngine/MainUIController/ReplayEngine/MultiplayerUI 四战役 | 4,823 → 3,789 |

## 项目基础设施

| 版本 | 内容 |
|------|------|
| v19.133f98 | 四 README 合并归一 + CLAUDE 精简 + 会话归档 (86 篇) + mcp 独立服务器 + 行尾规范 (.gitattributes) + 编辑器规范 (.editorconfig) |
| v19.133f98 | 协作规范化: CONTRIBUTING.md + .github (PR/ISSUE 模板 + CI) + 本 CHANGELOG + supplement 表头修复 + 口径统一 (10,797/1,294) |

## 口径速查 (当前)

> ⚠️ 2026-09-21 口径修订 (KBC): 本节数字经实测同步至 [docs/STATUS.md](docs/STATUS.md) (唯一口径来源)。
> 历史条目行保留当时值, 不回改。

- 编译错误: **0** (41,402 → -100.0%)
- supplement: **10,395** (字段 6,072 + 方法 4,323)  ← 原记 10,797/6,323/4,474 为逆4 前旧值
- class-discoveries: **1,294**
- 损伤家族: 40+; 官方语义名: 482; 真实未解析: **189**  ← 原记 162 为 v19.85 重生成前旧值
- 映射验证状态: 30 种标签 + 16 行空标签 (`verified` 996 / `verified-exists` 4,432 / `unverifiable-rebuilt` 772; 2026-09-24 复核实测) 见 [docs/STATUS.md](docs/STATUS.md)
