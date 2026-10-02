# 文档索引

> 口径来源：[STATUS.md](STATUS.md) ✓ · 本索引覆盖公开仓库**全部文档** ✓
> （研究日志 / 计划 / 会话记录等过程文档仅存于内部工作副本，不进公开仓库）

## 一、核心（7 篇，精炼）

| 文档 | 内容 |
|---|---|
| [../README.md](../README.md) | 项目总览 + 快速开始 + 铁律 |
| [../CLAUDE.md](../CLAUDE.md) | 操作规范（AI/开发者） |
| [STATUS.md](STATUS.md) | ★ **唯一口径**（全部实测数字） |
| [ARCHITECTURE.md](ARCHITECTURE.md) | 结构 / 构建管线 / 数据流 |
| [VERIFICATION.md](VERIFICATION.md) | 验收体系 V0–V8 |
| [STRUCTURE.md](STRUCTURE.md) | ★ **项目结构 + 清理规则**（权威） |
| [FEASIBILITY.md](FEASIBILITY.md) | 能否基于本工程做开发 |

## 二、14 个功能域（★ 中文详细版 ✓）

> 每个域一篇**丰富 README** ✓（职责 · 真实数据 · 核心机制 · 关键成员速查 · 修改指南 ✓），另有若干子文档深入单点 ✓。

| 域 | 主题 | 文档数 |
|---|---|---|
| [01-units](01-units/README.md) | 单位系统 | 6 篇 |
| [02-unit-actions](02-unit-actions/README.md) | 单位动作与命令 | 4 篇 |
| [03-custom-core](03-custom-core/README.md) | 自定义单位核心 | 4 篇 |
| [04-custom-logic](04-custom-logic/README.md) | 自定义逻辑引擎 | 2 篇 |
| [05-ai](05-ai/README.md) | AI 系统 | 2 篇 |
| [06-world](06-world/README.md) | 世界与地图 | 6 篇 |
| [07-engine](07-engine/README.md) | 引擎核心 | 9 篇 |
| [08-rendering](08-rendering/README.md) | 渲染系统 | 2 篇 |
| [09-ui-audio](09-ui-audio/README.md) | 界面与音频 | 3 篇 |
| [10-network](10-network/README.md) | 网络与联机 | 4 篇 |
| [11-utility](11-utility/README.md) | 工具库 | 3 篇 |
| [12-platform](12-platform/README.md) | 平台层 | 6 篇 |
| [13-librocket](13-librocket/README.md) | LibRocket 桥 | 1 篇 |
| [14-thirdparty](14-thirdparty/README.md) | 第三方库 | 1 篇 |

## 三、维护规则

1. **数字只写在一处** ⇒ `STATUS.md` ✓；其它文档引用它，不重复记数 ✗
2. **新增文档**：先看本索引是否有归属；无则加进「核心」或对应域 ✓
3. **改完必跑** `python tools/analysis/check_doc_paths.py` ⇒ **0 broken** ✓
