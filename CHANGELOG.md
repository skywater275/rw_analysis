# 变更日志 (CHANGELOG)

> 格式: 每个战役节点一行 (版本/内容/错误数变化)。
> 完整战役史:  (86 篇会话记录)。
> 更早历史 (v8-v10): git tag (v10.4-FINAL 等) + docs/deobfuscation/PHASE-A-早期战役史.md。

## B 系列: 从可编译到可运行 (2026-08-30 ~ 08-31)

| 版本 | 里程碑 | 结果 |
|------|--------|------|
| **B1** | 编译清零 — 41,402 → **0 错误** (javac_gate PASSED) | ✅ |
| **B2** | 反向映射核对 — **0 缺口** | ✅ |
| **B3** | 反向构建 — game-lib-reverse.jar (1,834 类) 0 错误 | ✅ |
| **B4** | 运行验证 — 反向 jar 替换后 headless 启动/回放/AI 正常 | ✅ |
| **B5** | 行为一致性收敛 — 运行时反馈 10 项修复, 0 崩溃全通 | ✅ |
| B5.5 | 覆盖率探究 — JDK17 双 jar 方案证伪, 目标口径修订 | 结论固化 |
| B5.6 | 映射验证 v2 — 726 可疑映射构建器跳过 (安全失败) | 进行中 |

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

- 编译错误: **0** (41,402 → -100.0%)
- supplement: **10,797** (字段 6,323 + 方法 4,474)
- class-discoveries: **1,294**
- 损伤家族: 40+; 官方语义名: 482; 真实未解析: 162
