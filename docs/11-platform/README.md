# 11-platform — 平台层 (文档待建)

> 版本: v19.133f98 | 日期: 2026-08-31 | 占位目录: 按键/输入/Steam/LibRocket 平台层文档待建
> ⚠️ 证据时点 v19.133f98 — 本目录为 12 域归档补位, 暂无文档; 域边界见 [ORGANIZATION.md §2](../ORGANIZATION.md)

## 域边界 (ORGANIZATION.md §2)

| 域 | 游戏功能 | 应有文档 | 边界判定 |
|----|---------|---------|---------|
| **11-platform** | 平台层 | 按键/输入/Steam/LibRocket | java.* 平台后端 + librocket |

## 待建清单

- [ ] 输入系统 (按键绑定/触摸/鼠标) — KeyBinding/InputAxis 相关 (已清零家族战役见会话记录)
- [ ] Steam 集成 — SteamEngine/SteamWorkshop (v19.133f15 战役清零)
- [ ] LibRocket UI 桥接 — librocket 原生绑定 (B4 运行时验证 NATIVE_BIND_METHODS 豁免)
- [ ] 文件系统与存储后端 — filesystem 域 (v19.133f14 战役清零)

## 相关映射与资料

- 相关源码: `03-deobfuscated/java/` + `03-deobfuscated/librocket/`
- 相关战役: v19.133f14 ModsUI/filesystem 域清零 / v19.133f15 测试族+SteamWorkshop 清零 / v19.133f36 KeyBinding 家族清零 (轨迹见 PLAN.md 会话行)
- 待定信息: [PENDING.md](../PENDING.md)
