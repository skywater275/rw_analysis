# 命名策略 — 残留单字符文件分类
> ⚠️ 历史文档 (v10.x 方法论, 2026-08-09) — 当前确定性重建方法学见 [PLAN.md](../deobfuscation/PLAN.md)

> 日期: 2026-08-09 | v10.0

## 背景

03-deobfuscated 中仍有 ~280 个文件使用单字符或双字符混淆名。本文档定义分类策略, 确保后续会话集中精力处理核心类, 不浪费在第三方库/桩代码上。

## 分类策略

### A 类: 核心需重命名

包路径包含 `com.corrodinggames.rts.game` 或 `com.corrodinggames.rts.gameFramework` 且:
- 文件 > 1KB (非空文件)
- 不在已知第三方列表中

**处理方式**: 添加到 class-discoveries.csv 并运行 apply_enhanced.py

### B 类: 第三方库保留

包路径以以下前缀开头:
- `com.codedisaster.steamworks` — Steam API 绑定
- `com.corrodinggames.librocket` — LibRocket UI 库
- `org.a/a/` 或 `a/a/a/` — 可靠 UDP 库 (KryoNet 变体)
- `java/` — Java 标准库桩
- `android/` — Android SDK 桩

**处理方式**: 保留原名, 不进行解混淆

### C 类: Android/平台桩代码

- `android.*` 下的所有文件 — SDK 桩
- `java.*` 下的所有文件 — 标准库桩

**处理方式**: 保留, 这些是 CFR 生成的桩, 不是游戏代码

### D 类: 自动生成/资源文件

- 文件名匹配 `*$N` 模式 (匿名内部类) — 通过 rename_inner_classes.py 处理
- GLSL shader 字符串 — 不是 Java 源文件
- CFR 合成类 — 反编译器生成的辅助类

**处理方式**: 内部类重命名; 其他保留

## 已知残留清单 (前 30)

| 文件 | 大小 | 分类 | 说明 |
|------|------|------|------|
| game/units/custom/b/c.java | 33KB | A | 大型自定义类, 需重命名 |
| game/units/custom/ay.java | 23KB | A | 自定义单位相关 |
| game/units/custom/bp.java | 20KB | A | 自定义单位相关 |
| gameFramework/utility/al.java | 15KB | A | 引擎工具类 |
| game/units/custom/b/m.java | 12KB | A | 资源管理 |
| java/audio/a/e.java | 10KB | C | Java音频桩 |
| gameFramework/utility/a/a.java | 8KB | A | 引擎工具 |
| game/units/custom/b/h.java | 8KB | A | 自定义单位 |
| game/units/custom/bi.java | 7KB | A | 自定义单位 |
| gameFramework/b/a/b.java | 6KB | A | 地图工具 |
| units/d/a/a.java | 5KB | A | 建造者子类 |
| game/units/custom/c/c.java | 5KB | A | 配置数据 |
| com/codedisaster/steamworks/* | — | B | Steam API |
| a/a/a/* | — | B | 可靠UDP库 |
| android/* | — | C | SDK桩 |
| java/* | — | C | 标准库桩 |

完整清单见 `mapping-priority.csv`。
