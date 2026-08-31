# docs/ 整理分类总要求

> v19.117 | 2026-08-23 | docs/ 目录的组织规范 — 新增/修改文档前必读

## 一、目录分类总则

docs/ 按**游戏功能域**划分, 共六层结构。新文档必须归入以下位置之一, 禁止散落顶层 (全局情报文档除外, 见下):

```
docs/
├── STATUS.md          ← 唯一状态报告 + 数字口径唯一来源
├── ORGANIZATION.md    ← 本文件 (组织规范)
├── PENDING.md         ← 待定信息集中存放 (见第四节)
├── 01-units/ ~ 12-utility/   ← 12 个游戏功能域 (见第二节)
├── deobfuscation/     ← 反混淆工程策略与记录 (会话记录/验证/辐射批次/早期战役史)
├── generated/         ← 工具自动生成 (禁止手改, 见 generated/README.md)
└── _archive/          ← 历史归档 (只进不出, git mv 保留历史)
```

> ⚠️ **v19.133f98 四 README 合并归一**: 原 docs/README.md (唯一导航索引) 已并入根 [README.md](../README.md) 第三节文档导航, 本文档登记要求改为"所有文档必须在根 README.md 第三节登记"。

**全局情报文档** (跨域机制调查, 允许顶层, 均在 README 元文档表登记):
- `GAME-EMBEDDED-DEAUTO.md` — 游戏自带"反编译"机制索引 (M1-M8)

> v19.133f98 域归档: 05-GAMELIB-RESOURCES 已归档 _archive/ (资源盘点 v19.88 快照);
> DEBUG-FEATURES 已移入 07-engine 域 (调试功能主宿主)。

新机制调查类文档若跨 3 域以上, 先评估是否入全局情报层; 单域调查归对应功能域。

## 二、12 个游戏功能域 — 边界与文档要求

| 域 | 游戏功能 | 应含文档 | 边界判定 |
|----|---------|---------|---------|
| **01-units** | 单位系统 | 生命周期/加载注册/武器伤害/战斗指令 | UnitInstance/UnitType/MovementType 等实例与类型层 |
| **02-buildings** | 建筑与工厂 | 建造管线 (Command→BuilderUnit→完成) | 继承 UnitType 的建筑子类与工厂逻辑 |
| **03-actions** | 指令系统 | 15 种 GameAction/序列化/命令格式 | 玩家指令从生成到执行的链路 |
| **04-ai** | AI 系统 | 三层时钟/Zone/UnitGroup/AIStrategy | game.ai + gameFramework.aicore |
| **05-map** | 地图系统 | TMX 解析/战争迷雾/块渲染/MapEngine | game.map + game.b |
| **06-network** | 网络通信 | 协议栈/包类型/Tick 同步 | gameFramework.network + 可靠UDP |
| **07-engine** | 引擎核心 | 主循环/生命周期/存档/统计/事件 | GameEngine/GlobalState/ReplayEngine |
| **08-rendering** | 渲染与音频 | 渲染管线/音频/HUD | gameFramework.m + rendering + effects |
| **09-custom** | 自定义/Mod | CustomUnitType/INI/LogicBoolean/参数参考 | units.custom 全树 |
| **10-pathfinding** | 寻路系统 | A*/PathFinder/移动/空间网格 | gameFramework.k + MovementController |
| **11-platform** | 平台层 | 按键/输入/Steam/LibRocket (文档待建) | java.* 平台后端 + librocket |
| **12-utility** | 工具/杂项 | 开发者注释/数据结构 | 跨域工具类与资料集 |

**文档要求**:
1. 每域至少一篇核心文档, 标题格式 `# <主题> — <一句话定位>`
2. 头部必须含命名时点标注 (见第三节)
3. 域间交叉引用用相对链接, 禁止绝对路径

## 三、文档头部模板 (强制)

```markdown
# <标题>

> <版本 vNNN 或 命名时点> | <日期> | <一句话主题>
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化,
>   对应关系查 [mappings/class-discoveries.csv](../mappings/class-discoveries.csv)
```

历史/证据类文档额外加 `> ⚠️ 证据时点 vNNN — 本日志为当时值` 标签。

## 四、待定信息规则 (PENDING.md)

**所有不确定/待验证/待裁决的信息必须写入 [PENDING.md](PENDING.md), 禁止散落在域文档中。**

| 类别 | 存放节 | 规则 |
|------|--------|------|
| 待定类身份 | PENDING §1 | 类身份有争议/缺证据的, 记录候选+证据状态 |
| 残余编译错误族 | PENDING §2 | 会话跳过项按族登记, 附文件:符号/规模/下一步 |
| 未解析混淆类 | PENDING §3 | 指向 mappings/generated/unresolved.txt |
| 待验证假设 | PENDING §4 | 标注"假设"与验证方法 |
| 映射冲突留档 | PENDING §5 | 指向 arbitration-candidates.csv |

流转规则:
- **入**: 会话中遇到不确定项 → 立即登记 PENDING, 不留在一时性对话中
- **出**: 项被裁决/修复后 → 从 PENDING 移除, 结论写入对应域文档 + 会话记录
- 每次会话结束, PENDING 的增减变化写入会话记录

## 五、数字口径规范

- 编译错误数/映射数/类映射数**只允许出现在 STATUS.md**; 其他文档引用时写"见 STATUS.md"
- 域文档正文不写项目级数字 (如总错误数), 只写域内数据 (行数/方法数可写, 需标注测量时点)
- 会话记录 (deobfuscation/PHASE-A-*.md) 可写当时值, 头部必须有版本号

## 六、归档规则

- 被取代的文档 → `git mv` 到 docs/_archive/, 在根 README.md 移除条目
- 归档条件: ① 方法论被新方法取代 ② 快照类记录 (vNNN 时点) ③ 合并后冗余
- 归档文档不改内容, 如需修正只在头部加 ⚠️ 说明

## 七、维护清单 (与 CLAUDE.md 任务收尾清单 D1-D6 对应)

每次任务后按 CLAUDE.md「任务收尾更新清单」执行, 其中与 docs/ 直接相关的:
- D1: 口径同步 (STATUS/README 系列)
- D2: 会话记录新篇
- D4: 类名改动全文档同步
- D5: README 索引补新删旧 + PENDING 流转 + 归档
