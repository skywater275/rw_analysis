# Rusted Warfare v1.15 解混淆项目 — 状态报告

> 更新: 2026-08-31 | **v19.133f98 (B1-B5 达成 + 逆1-逆5 映射库收尾)** | **混合形态可运行**: B1 编译清零 (41,402→0) → B2 反向核对 0 缺口 → B3 反向构建 game-lib-reverse.jar → B4 运行验证通过 → B5 行为一致性收敛 (0 崩溃全通); 映射库 10,395 条全列规范 (垃圾 verified 0/空值 0/suspicious 15 带注记)
> 战役轨迹: [deobfuscation/PLAN.md](deobfuscation/PLAN.md) (B1-B5 + 逆1-逆5 会话行) + [早期战役史](_archive/PHASE-A-早期战役史.md) (v19.0-v19.102)

## 编译基线

| 分支 | 基线 | 当前 | 状态 |
|------|------|------|------|
| **old_deobfuscated** (主) | 41,402 (真实基数, v19.1) | **0** (-100.0%) | v19.133f97 (B1 编译清零, gate PASSED) |
| apply_enhanced_rebuild | 466 | 冻结保留 | 不投入工作 (docs/deobfuscation/PLAN.md 已证伪其"2 errors"假象) |
| **B3 反向构建** | — | **0 错误** | v19.133f98 (反向→javac→game-lib-reverse.jar) |
| **B4 运行验证** | — | **可启动可运行** | v19.133f98-B4 (反向 jar 替换 headless 启动 0 异常) |

> 41,402 为真实基数 (v19.1 maxerrs 100,000 揭晓; 旧 ~5,000 为截断假象)。
> **B1 里程碑 (2026-08-31)**: 编译错误全部清零 (41,402 → 0)。Phase A 收尾时剩余 12 条 (WebAPIClient.java) 修复后, javac 抑制解除 (F86) 暴露并清零全部隐藏错误:
> synthetic final 去 final×13 (opengl 家族, javap 无构造器赋值铁证) / throws 补链连锁 (utility 家族/audio 家族/slick 家族/input 家族/Socket 覆写链) / reliableudp 重建库 51 条 (v19.115z 战役 javac 抑制掩盖)。

### 当前残余 (v19.133f98 实测, 0)

```
无编译错误。全量 javac_gate PASSED (1,739 文件, 24 真实 jars classpath)。
B2 反向映射核对: jar 1,483 游戏类 100% 覆盖 0 缺口 (tools/utils/b2_reverse_map_check.py,
产物 b2-*.csv 于 mappings/generated/, 可重生成)。
B3 反向构建: 反向源码 javac 0 错误 (tools/fixers/build_reverse_jar.py --apply),
产物 build/game-lib-reverse.jar (1,834 类) + build-skip.txt (136 文件 JLS 类包同名限制, jar 原样合并)。
```

残余错误主要类型: 无。B4 运行验证已完成 (反向 jar 替换 game-lib.jar headless 启动 0 异常/回放加载成功/AI 正常), B5 行为一致性收敛已完成 (运行时反馈 10 项修复, 启动 0 崩溃 + 开局/建单位/存档/AI 全通)。残余: GUI/回放深度验证 + 撞车剔除 500 项 (见 PENDING §5)。

## 文件统计

| 目录 | 数量 | 说明 |
|------|------|------|
| 01-classes (.class 字节码) | 388 | 从 game-lib.jar 提取的核心类 (不入库) |
| 02-decompiled (CFR 反编译) | 1,698 | 原始混淆名, CFR 0.152 输出 |
| 02b-decompiled (FernFlower) | 1,698 | 交叉验证源 (v19.111 起) |
| 03-deobfuscated (解混淆输出) | 1,739 | 可读类名/方法名, 编译主线 |
| game-lib.jar (字节码真源) | 1,698 类 | 编译目标, 直接 javap 源 (05/06-lib 解包目录已删) |

## 映射数据库

| 指标 | 数量 |
|------|------|
| 字段映射 | 6,072 |
| 方法映射 | 4,323 |
| 总映射数 | **10,395** (supplement.csv, 实测; 垃圾 verified 0/空值 0) |
| 域拆分 | 12 域 (mappings/domains/, 已同步) |
| 类映射 | **1,294** (class-discoveries.csv) |
| 真实未解析混淆类 | **189** (mappings/generated/unresolved.txt, 124 未映射+65 缺失) |
| 官方语义名 | 482 (game-lib.jar 审计, 0 缺口) |
| 包目录重命名 | 43 (obfuscated→readable) |

## 工具链

| 指标 | 数值 |
|------|------|
| manager.py 注册工具 | **616** (常驻 39 + 战役修复器 577; v19.133f98 分类统一 6 类) |
| fixers/ 脚本 | 564 (战役修复器, 组索引见根 README 第五节 + TOOLS-TREE.md) |
| javac_gate.py | ✅ 编译门禁 (compile-errors.csv: file/line/type/message/symbol/location) |
| 确定性重放管线 | ✅ 三度实践回退重放 |
| 运行时管线 | ✅ patch 58 类游戏内实测 (反向+编译+分组二分) |

## 数据质量

| 指标 | 数值 |
|------|------|
| supplement.csv | 10,395 条, 表头已修复无损坏 (v19.133f98 整理) |
| 域CSV | 12 域一致, 无重复 (v19.114 已同步) |
| class-discoveries | 1,294 条, 游戏类全覆盖 |
| 幻影FQN | 0 (apply_enhanced) / 少量 (old_deobfuscated) |

## 距项目目标的距离 (v19.133f98-B5.5 修订)

> 目标: 使 RustedWarfare/ 以未混淆的源码状态运行 → **修订为行为一致可运行的混合形态** (B5.5 证伪纯源码路线: 类包同名深包 = javac 绝对硬限制, 覆盖天花板 ~15%)

| 层 | 当前 | 完成度 | 说明 |
|----|------|--------|------|
| 映射数据库 | supplement 10,395 + class-discoveries 1,294 | ✅ 完成 | 全列规范 (逆1-逆5 收尾) |
| 编译错误 | 0 | **100%** | v19.133f97 B1 清零 (gate PASSED) |
| 反向构建 | game-lib-reverse.jar (1,834 类) | **100%** | B3, build-skip.txt 136 类 jar 原样合并 |
| 运行验证 | headless 0 异常 + 回放 + AI | ✅ 达成 | B4 |
| 行为一致性 | 0 崩溃 + 开局/建单位/存档/AI 全通 | ✅ 达成 | B5 |

**总体: 修订目标已达成** (混合形态可运行、行为一致)。残余为质量长尾: GUI/回放深度验证 + 撞车剔除 500 项 + 15 条保持 suspicious (PENDING §5)。

## 下一步

- 待定类身份/假设/映射冲突: 见 [PENDING.md](PENDING.md) §1/§3/§4
- 当前残余 (GUI/回放验证 + 撞车剔除 + 15 条 suspicious): 见 [PENDING.md](PENDING.md) §5
- 战役计划: [deobfuscation/PLAN.md](deobfuscation/PLAN.md) (v3.23, B1-B5 达成)

## 历史文档导航

| 时段 | 文档 |
|------|------|
| v19.0 → v19.102 (R0-R4 循环/环境资源/辐射批次) | [PHASE-A-早期战役史.md](_archive/PHASE-A-早期战役史.md) |
| v19.107 → v19.133f98 (B1-B5 + 逆1-逆5) | [PLAN.md](deobfuscation/PLAN.md) 会话行 (会话详细记录已按要求删除, 结论沉淀于域文档) |
