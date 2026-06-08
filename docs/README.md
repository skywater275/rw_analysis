# Rusted Warfare v1.15 — 逆向工程文档

> CFR 0.152 全量解混淆 (1,698类) + RW-HPS 服务器 + 30 回放验证

## 综合报告

| 文档 | 说明 |
|------|------|
| [GAME_COMPLETE_ANALYSIS.md](GAME_COMPLETE_ANALYSIS.md) | 22 系统完整分析 (1,479行) |
| [LAYER_ANALYSIS.md](LAYER_ANALYSIS.md) | 7 层全栈分析 (945行) |
| [CPP_LAYER_ANALYSIS.md](CPP_LAYER_ANALYSIS.md) | C++ 原生层 PE/符号/导出分析 (625行) |
| [OBFUSCATION_MAP.md](OBFUSCATION_MAP.md) | 混淆名→实际含义映射参考 (741行) |
| [VERIFICATION.md](VERIFICATION.md) | 交叉验证报告 (732行) |

## 参考

| 文档 | 说明 |
|------|------|
| [CLASS_DICTIONARY.md](CLASS_DICTIONARY.md) | 280+ 字段映射, 31 核心类 |
| [CLASS_CATALOG.md](CLASS_CATALOG.md) | 1,698 类清单 |

## 领域文档

| # | 文档 | 主题 |
|---|------|------|
| 01 | [经济系统](01_ECONOMY.md) | 收入公式, 建造/升级/退款 |
| 02 | [战斗系统](02_COMBAT.md) | HP/护盾/伤害, 死亡链, 弹丸 |
| 03 | [移动/寻路](03_MOVEMENT.md) | 寻路, 空间网格 |
| 04 | [单位系统](04_UNITS.md) | 单位类型, 实例, 自定义单位 |
| 05 | [玩家/队伍](05_PLAYER_TEAM.md) | 槽位, 队伍, AI 填充 |
| 06 | [网络/指令](06_NETWORK_COMMAND.md) | 协议, Command 字段, 回放格式 |
| 07 | [地图/迷雾](07_MAP_FOG.md) | TMX 解析, 战争迷雾 |
| 08 | [AI 系统](08_AI.md) | 波次系统, 任务, 条件 |
| 09 | [工具/框架](09_UTILITY.md) | 数据结构, 数学工具 |
| 10 | [核心算法](10_ALGORITHMS.md) | 字节码级算法还原 |
| 11 | [游戏常量](11_CONSTANTS.md) | 已验证的游戏常量 |

## 系统文档

| 文档 | 说明 |
|------|------|
| [12_GAME_ARCHITECTURE](12_GAME_ARCHITECTURE.md) | 完整架构总览 |
| [15_SIMULATION_ENGINE](15_SIMULATION_ENGINE.md) | Python 参考实现 |
| [16_FILE_FORMATS](16_FILE_FORMATS.md) | .replay/.rwsave/.ini 格式 |
| [17_SERVER_ARCHITECTURE](17_SERVER_ARCHITECTURE.md) | RW-HPS 无头服务器 |
| [18_NETWORK_SYSTEM](18_NETWORK_SYSTEM.md) | TCP/UDP, 包类型, 同步 |
| [19_UI_RENDERING](19_UI_RENDERING_SYSTEM.md) | LibRocket/Slick2D/OpenGL |
| [20_PLUGIN_MOD](20_PLUGIN_MOD_SYSTEM.md) | Mod/插件生命周期 |

---

## 免责声明

这是一个非官方的 Rusted Warfare 逆向工程项目。项目中引用的所有资源均归其原作者所有。仅限于教育和研究目的，禁止商业用途。

*Rusted Warfare v1.15 — CFR 0.152 解混淆源码 (1,698类) + RW-HPS 服务器 + 30 回放*
