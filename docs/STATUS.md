# Rusted Warfare v1.15 解混淆项目 — 状态报告

> 更新: 2026-09-04 | **v19.133f98 (B3 全量构建 + B4 运行验证)** | **反向 jar 可构建可运行**: game-lib-reverse.jar 替换 game-lib.jar 后 headless 启动 0 异常, 回放加载成功; B1: 41,402 → **0** 编译清零; B2: jar 1,483 游戏类 100% 覆盖 0 缺口
> 战役详情: [PHASE-A-v19.133f98-B4.md](deobfuscation/sessions/PHASE-A-v19.133f98-B4.md)
> 完整战役轨迹 (v19.107 起): PHASE-A-会话历史总览.md; 早期战役史 (v19.0-v19.102): [PHASE-A-早期战役史.md](deobfuscation/PHASE-A-早期战役史.md)

## 编译基线

| 分支 | 基线 | 当前 | 状态 |
|------|------|------|------|
| **old_deobfuscated** (主) | 41,402 (真实基数, v19.1) | **0** (-100.0%) | v19.133f97 (B1 编译清零, gate PASSED) |
| apply_enhanced_rebuild | 466 | 冻结保留 | 不投入工作 (docs/deobfuscation/PLAN.md 已证伪其"2 errors"假象) |
| **B3 反向构建** | — | **0 错误** | v19.133f98 (反向→javac→game-lib-reverse.jar) |
| **B4 运行验证** | — | **可启动可运行** | v19.133f98-B4 (反向 jar 替换 headless 启动 0 异常) |

> 41,402 为真实基数 (v19.1 maxerrs 100,000 揭晓; 旧 ~5,000 为截断假象)。
> **B1 里程碑 (2026-09-04)**: 编译错误全部清零 (41,402 → 0)。Phase A 收尾时剩余 12 条 (WebAPIClient.java) 修复后, javac 抑制解除 (F86) 暴露并清零全部隐藏错误:
> synthetic final 去 final×13 (opengl 家族, javap 无构造器赋值铁证) / throws 补链连锁 (utility 家族/audio 家族/slick 家族/input 家族/Socket 覆写链) / reliableudp 重建库 51 条 (v19.115z 战役 javac 抑制掩盖)。

### 当前残余 (v19.133f98 实测, 0)

```
无编译错误。全量 javac_gate PASSED (1,739 文件, 24 真实 jars classpath)。
B2 反向映射核对: jar 1,483 游戏类 100% 覆盖 0 缺口 (tools/utils/b2_reverse_map_check.py,
产物 b2-*.csv 于 mappings/generated/, 可重生成)。
B3 反向构建: 反向源码 javac 0 错误 (tools/fixers/build_reverse_jar.py --apply),
产物 build/game-lib-reverse.jar (1,834 类) + build-skip.txt (136 文件 JLS 类包同名限制, jar 原样合并)。
```

残余错误主要类型: 无。B4 运行验证已完成 (反向 jar 替换 game-lib.jar headless 启动 0 异常/回放加载成功/AI 正常, 详见 B4 会话记录)。下一阶段 B5: 行为一致性收敛 (运行时反馈驱动补映射, 待确认)。

## 文件统计

| 目录 | 数量 | 说明 |
|------|------|------|
| 01-classes (.class 字节码) | 388 | 从 game-lib.jar 提取的核心类 |
| 02-decompiled (CFR 反编译) | 1,698 | 原始混淆名, CFR 0.152 输出 |
| 02b-decompiled (FernFlower) | 1,698 | 交叉验证源 (v19.111 起) |
| 03-deobfuscated (解混淆输出) | 1,672 | 可读类名/方法名 |
| 05-gamelib (jar 直接解包) | 1,698 | 完整未动引擎 (字节码真源) |
| 06-lib (第三方 jar 解包) | 20 个 jar | 编译环境 |

## 映射数据库

| 指标 | 数量 |
|------|------|
| 字段映射 | 6,335 |
| 方法映射 | 4,476 |
| 总映射数 | **10,395** (supplement.csv, 实测) |
| 域拆分 | 12 域 (mappings/domains/, 已同步) |
| 类映射 | **1,294** (class-discoveries.csv) |
| 映射验证率 | 5,216/9,692 (53.8%, v19.114 Phase D 实测) |
| 真实未解析混淆类 | **162** (mappings/generated/unresolved.txt) |
| 官方语义名 | 482 (05-gamelib 28.4%; 0 缺口) |
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

## 距项目目标的距离 (v19.110 评估框架)

> 目标: 使 RustedWarfare/ 以未混淆的源码状态运行

| 层 | 当前 | 总量 | 完成度 | 说明 |
|----|------|------|--------|------|
| 映射数据库 | supplement 10,395 + class-discoveries 1,294 + mappings.csv 238 | ✅ 完成 | 100% | 三源架构 |
| 编译错误 | 0 | 41,402 (基数) | **100%** | v19.133f97 B1 编译清零 (gate PASSED) |
| 运行时替换 | 58 类 patch 实测 | 1,698 jar 类 | **3.4%** | 管线就绪, 每批 18 类 ≈ 2-3 分钟游戏验证 |
| 行为验证 | 主链路(AI对战/渲染) + 测试族 7 套件 0 断言失败 | — | 机制建立 ✅ | NoSuchFieldError 驱动反馈 + 基线对照归责 |

**总体估计: ~75-80%** (映射完成 + 编译 100% 清零 + 运行时管线就绪但替换量仍小; 待 Phase B 推进)

## 下一步

- 残余编译错误族: 见 [PENDING.md](PENDING.md) §2 (含 v19.117 CustomUnitType 批16 残余 6 族)
- 待定类身份/假设/映射冲突: 见 [PENDING.md](PENDING.md) §1/§4/§5
- 战役计划: [deobfuscation/PLAN.md](deobfuscation/PLAN.md) (v3.23, Phase B B1-B4 达成, B5 待确认)

## 历史文档导航

| 时段 | 文档 |
|------|------|
| v19.0 → v19.102 (R0-R4 循环/环境资源/辐射批次) | [PHASE-A-早期战役史.md](deobfuscation/PHASE-A-早期战役史.md) |
| v19.107 → v19.117 (战役会话) | PHASE-A-会话历史总览.md + [sessions/](deobfuscation/sessions/) |
| 勘误日志 (v19.89 后停更) | [ERRATA.md](_archive/ERRATA.md) |
