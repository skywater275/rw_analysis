# FernFlower 反编译器分析与集成 (v19.111)

> 2026-08-20 | 下载: https://the.bytecode.club/fernflower.jar (245KB, C:/tmp/fernflower.jar)
> 工具: tools/analyze/fernflower_02b.py | 产出: 02b-decompiled/ (第二反编译源)


> ⚠️ **已归档** (反编译工具分析类历史, 方法论已由 METHODOLOGY.md 承接, 2026-09-04 域归档)


## 背景

游戏文件中曾发现 `// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA)` 头 —
**游戏作者本人使用 FernFlower**。本会话下载 FernFlower 并实测其解混淆逻辑,
评估为项目第二反编译源。

## FernFlower 是什么

IntelliJ IDEA 内置的开源 Java 反编译器 (JetBrains/fernflower, Apache 2.0)。
架构: 字节码 → 结构化 SSA 解析 → 表达式树重建 → 文本输出。
与 CFR 同为字节码反编译器, 但解析策略不同:
- CFR: 保守还原 + 详细注释 (WARNING/REMOVED TRY CATCHING)
- FernFlower: 激进表达式合并 + 完整方法表 + 极简输出

## 解混淆逻辑实测 (game-lib.jar 关键类对比)

| 维度 | FernFlower | CFR (02 现状) |
|------|-----------|---------------|
| 方法完整性 | **m.y 接口 67/67** = jar 真身 | 30/67, 丢 37 个 (曾需 javap 手工重建) |
| 临时变量 | **内联消除** (`m m2 = this.a.l` → 直接内联使用) | 保留声明 (错标 utility.m, javap 仲裁真身 CustomArrayList) |
| 参数命名 | var1/var2 (无法恢复时) | 原混淆名 |
| 修饰符 | 隐式省略 (接口方法无 public) | 全显式 |
| strictfp | 保留 ✅ (127 处与 CFR 一致) | 保留 ✅ |
| 注释 | 无 (无 synthetic/WARNING 标记) | 详细 (含 `/* synthetic */` 跨行断裂风险) |
| -ren 模式 | 混淆名→class_0/method_0/var_0 占位重命名 | 无 |

### 铁证对比

1. **m.y (TextureManagerInterface)**: FF 67 方法 vs CFR 30 — FF 完胜 (CFR 已知丢接口方法缺陷)
2. **custom.e (ParameterAnimator) L173**: FF 无冗余变量行 (直接内联), CFR 保留 `m m2` 错标行
3. **自举反编译**: FF 反编译自身 220 类全部成功 (jar 级输出重新打包)

## 项目集成方案

```
tools/analyze/fernflower_02b.py --classes ParameterAnimator,PlayerState   # 语义名自动解析
tools/analyze/fernflower_02b.py --full                                    # 全量 1,698 类

**使用场景**:
1. **CFR 缺陷类交叉验证**: 丢方法/错标字段/注释断裂 → 02b 对照 javap 仲裁
2. **深水区整类重建**: HUDManager (跨类污染) / 四大文件 — FF 输出作为重建底稿
3. **运行时 patch 候选**: FF 更完整的方法表 → 反向管线候选质量提升

**三源结构升级**: T0 字节码 (javap) / T1 映射表 / T2a CFR 原稿 + **T2b FernFlower 原稿**

## PENDING

- 02b 全量完成后: 与 02 做方法数差异扫描 (定位全部 CFR 丢方法类清单)
- FF 的 -ren 重命名模式 (class_0 系列) 可做"混淆名→占位名"第一遍标准化,
  但会丢失字母组信息 (ProGuard 保序对齐依赖字母组) — 仅用于可读性场景
