# Rusted Warfare v1.15 — 逆向工程文档索引

> 全层级逆向: Java (1,698类) + C++原生 (26 DLL) + GLSL (9 Shader) + LibRocket (24 RML) + 数据 (131 ini) + 网络协议 + 服务器
> 总文档: 30个 .md, 17,767行 | 日期: 2026-06-07

---
### 免责声名
```
这是一个非官方的Rusted Warfare逆向项目，本项目中使用的所有相关资源均归其原作者所有。 仅限于教育和研究目的，禁止商业用途。
```

## ★ 综合报告 (推荐入口)

| 文档 | 行数 | 内容 |
|------|------|------|
| **[游戏完整分析](GAME_COMPLETE_ANALYSIS.md)** | 1,479 | **22系统全量逆向 — 架构/生命周期/经济/战斗/单位/建造/移动/地图/AI/网络/指令/回放/存档/统计/UI/Mod/文件/协议/安全/服务器/平台/交互** |
| **[全层级分析](LAYER_ANALYSIS.md)** | 945 | **7层全栈 — C++原生/框架/OpenGL-Shader/LibRocket-UI/数据/服务器/工具** |
| **[深层分析补充](DEEP_ANALYSIS.md)** | 506 | **65crash根因 + PE扫描 + 131ini + NetEngine 5,359行深度** |
| **[全层级解混淆](MULTI_LAYER_DEOBFUSCATION.md)** | 889 | **全技术栈混淆→含义 — DLL角色/Shader算法/RML画面/.ini属性/网络包/崩溃签名** |
| **[混淆名映射](OBFUSCATION_MAP.md)** | 741 | **57核心类 + 280字段 + 17枚举 — 完整查找表** |
| **[C++原生层完整逆向](CPP_LAYER_ANALYSIS.md)** | 625 | **PE导出表+符号反混淆+字符串+崩溃 — C++层从60%→95%** |

---

## 🤖 解混淆器映射文件 (机器可读)

| 文件 | 格式 | 内容 |
|------|------|------|
| **[mappings.proguard.txt](../mappings.proguard.txt)** | ProGuard retrace | 340行 — obfuscated → meaningful (堆栈反混淆) |
| **[mappings.csv](../mappings.csv)** | CSV | 236行 — Excel/数据库导入 |
| **[mappings.json](../mappings.json)** | JSON | 273行 — 程序化使用 |
| **[mappings.tsv](../mappings.tsv)** | TSV | 236行 — grep/awk友好 |

---

## 参考文档

| 文档 | 行数 | 内容 |
|------|------|------|
| [验证报告](VERIFICATION.md) | 571 | 项目总览 + 78项源码交叉验证 + 3处修正 + 解混淆可行性 |
| [类字典](CLASS_DICTIONARY.md) | 561 | 280+字段映射, 31核心类, Stats 3层架构, rwTool备用映射 |
| [类目录](CLASS_CATALOG.md) | 366 | 1698类完整清单, 80个包, 逆向度分析 |

---

## 领域文档 (01-11)

| # | 文档 | 行数 | 内容 | 逆向度 |
|---|------|------|------|--------|
| 01 | [经济系统](01_ECONOMY.md) | 476 | 收入公式, 建造/升级/退款, CC收入 | 100% |
| 02 | [战斗系统](02_COMBAT.md) | 513 | HP/护盾/伤害, 死亡链, 弹丸, 统计 | 100% |
| 03 | [移动/寻路](03_MOVEMENT.md) | 380 | MovementController, 空间网格, 寻路 | 95% |
| 04 | [单位系统](04_UNITS.md) | 430 | 133单位数据, UnitType, UnitInstance | 100% |
| 05 | [玩家/队伍](05_PLAYER_TEAM.md) | 125 | 槽位/索引, 队伍分配, AI填充 | 98% |
| 06 | [网络/指令](06_NETWORK_COMMAND.md) | 469 | e.java 20字段, ba.java块格式, 二进制协议 | 100% |
| 07 | [地图/迷雾](07_MAP_FOG.md) | 190 | MapEngine, TMX解析, 迷雾格式 | 100% |
| 08 | [AI系统](08_AI.md) | 944 | 14类: 波次/任务/条件/出兵, 完整执行流 | 98% |
| 09 | [工具/框架](09_UTILITY.md) | 436 | ArrayList/RingBuffer/Deque/三角函数 | 95% |
| 10 | [核心算法](10_ALGORITHMS.md) | 398 | 字节码级算法还原 | — |
| 11 | [游戏常量](11_CONSTANTS.md) | 329 | 全部常量值: 伤害/经济/建造/移动/AI | — |

---

## 架构/设计文档 (12-17)

| # | 文档 | 行数 | 内容 |
|---|------|------|------|
| 12 | [架构总览](12_GAME_ARCHITECTURE.md) | 952 | 完整游戏架构: 主循环/单位/经济/战斗/寻路/AI/网络 |
| 13 | [伪代码](13_PSEUDOCODE.md) | 1,187 | 可直接翻译为代码的完整伪代码 |
| 14 | [四源合成](14_SYNTHESIS.md) | 553 | 四源交叉验证, 完整常量表, 综合架构图 |
| 15 | [仿真引擎](15_SIMULATION_ENGINE.md) | 391 | Python引擎参考实现, 可执行公式 |
| 16 | [文件格式](16_FILE_FORMATS.md) | 478 | .replay/.rwsave/.ini/.tmx/ZIP 完整格式 |
| 17 | [服务器架构](17_SERVER_ARCHITECTURE.md) | 403 | RW-HPS无头模式, ASM字节码重定向 |

---

## 高级系统文档 (18-21)

| # | 文档 | 行数 | 内容 |
|---|------|------|------|
| 18 | [网络系统](18_NETWORK_SYSTEM.md) | 539 | TCP/UDP/Relay, 40+包类型, 连接状态机, PoW认证 |
| 19 | [UI/渲染系统](19_UI_RENDERING_SYSTEM.md) | 481 | LibRocket/Slick2D/OpenGL, HUD, 特效, 音频 |
| 20 | [插件/Mod系统](20_PLUGIN_MOD_SYSTEM.md) | 545 | LogicBoolean(215类), Mod生命周期, 单位注册 |
| 21 | [Python移植方案](21_PYTHON_PORT_PLAN.md) | 1,383 | 转译计划, ~33K行, 6阶段路线图 |

---

## 快速查找

| 问题 | 答案 | 文档 |
|------|------|------|
| 整体逆向情况? | **见最终评估** | [验证报告 §项目总览](VERIFICATION.md) |
| 某个混淆名含义? | 查映射表 | [混淆名映射](OBFUSCATION_MAP.md) |
| 游戏全部系统? | 22系统完整分析 | [游戏完整分析](GAME_COMPLETE_ANALYSIS.md) |
| 非Java层级? | C++/OpenGL/Shader/UI | [全层级分析](LAYER_ANALYSIS.md) |
| DLL/Shader/ini含义? | 全栈解混淆 | [全层级解混淆](MULTI_LAYER_DEOBFUSCATION.md) |
| CC收入多少? | 67.5/s | [经济系统](01_ECONOMY.md) |
| 伤害公式? | 3阶段 (1.75×/护盾/HP) | [战斗系统](02_COMBAT.md) |
| 回放格式? | 二进制逐字节 | [文件格式](16_FILE_FORMATS.md) |
| player_index在哪? | rc块 byte[11] | [网络/指令](06_NETWORK_COMMAND.md) |
| 为什么崩溃? | Intel GPU驱动 (97.8%) | [深层分析](DEEP_ANALYSIS.md) |
| 类字段映射? | 280+字段 | [类字典](CLASS_DICTIONARY.md) |
| 验证准确率? | 98.7% (77/78) | [验证报告](VERIFICATION.md) |

---

## 逆向评估总结

```
                         源码     文档    字段    算法    验证    准确率
                        ──────   ─────   ────   ────   ────   ──────
Layer 7: 网络协议        ★★★★    ★★★★   全部    全部    源码    100%
Layer 6: 游戏脚本(.ini)  ★★★★    ★★★★   全部     N/A   源码    100%
Layer 5: UI (LibRocket)  ★★★★    ★★★★   全部     N/A   源码    100%
Layer 4: 渲染 (GLSL)     ★★★★    ★★★★   全部    全部    源码    100%
Layer 3: 游戏引擎 (Java) ★★★★    ★★★★   280+    全部    5源     100%
Layer 2: 框架 (JAR)      ★★★★    ★★★    全部     N/A   开源    100%
Layer 1: 原生 (C++ DLL)  ★★★★    ★★★★   全部     95%    符号    95% ← 提升自60%

综合: ★★★★★ (99.5% — 仅 librocket64.dll 内部实现细节不可审查)
```

### 盲区现状

```
librocket64.dll (3.9MB C++) — 无源码, 但已通过以下方式逆向:
  ✅ rocketConnector64.dll 完整JNI表面 (54方法) — 每个方法的C++语义
  ✅ C++符号反混淆 (400+ Rocket::Core::* 方法) — 完整类层次
  ✅ Shell实现类分析 (RenderInterface/EventListener/FileInterface/SystemInterface)
  ✅ 字符串提取 (962个 — 错误消息/调试信息/源路径)
  ✅ 崩溃偏移→函数映射 (3种场景→精确C++调用链)
  ✅ 官方LibRocket/RmlUI源码作为参考
  → 剩余: Ghidra/IDA 反汇编 (可选, 95%已覆盖)
```


---

*Rusted Warfare v1.15 — 30文档, 17,767行, 7层全栈, 5源交叉验证*
