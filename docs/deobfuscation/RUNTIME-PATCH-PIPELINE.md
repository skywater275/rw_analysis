# 运行时 Patch 管线 — 动态解混淆 (v19.110 扩展版)

> 03 源码 → 反向重命名 (03类名→02混淆名) → javac --release 8 → RustedWarfare/patch/ → 游戏 classpath 前置加载
> 主链路 + 测试族双验证: 游戏启动/AI对战/渲染 + DebugUI 测试套件断言

## 管线组件

| 工具 | 用途 |
|------|------|
| tools/fixers/runtime_patch_batch.py | 保守批量管线 (全短名限制, 17 类基线) |
| tools/fixers/fix_test_patch.py | 测试族专用 (platform.net→a.a, 冲突 jar 绕过, stub f/l/j.ad) |
| tools/fixers/fix_patch_expand.py | **主链路扩展** (放宽限制 + 字段 zip + 分组二分安装) |

## v19.110 扩展机制

### 1. 候选池 (848 反向 → 80 可编译)
- 0错误 + 有映射 + 非枚举 + 非类-包同名 + com.corrodinggames 前缀 + BLACKLIST
- 逐文件编译判定 (混批编译在本 JDK 全批零输出 + cannot-access 级联)

### 2. 字段保序 zip (field_zip)
- jar javap 字段名 vs 03 声明字段名 zip 替换
- **数量不符 → 候选排除** (NoSuchFieldError: a/b 实测: units.aa/ad 语义字段名 vs jar 短名)
- 铁证: `game.b.i` MapLayerDef (layerName→a 等 6 字段), `game.b.k` (6 字段), `game.units.ap` (13 字段)

### 3. 分组二分验证
- 每组 18 类安装 → 游戏主链路 (启动/AI对战/MissionEngine/渲染) → 测试族 (script debug.runAllUnitTests)
- 异常 → 隔离嫌疑类 → 逐个恢复定位

### 4. 类-包同名冲突 (46 类)
- jar ProGuard 产物: 类 X.class 与包 X/ 同名
- javac 拒绝 (import 与全限定均失败) → 编译用冲突清理 jar (C:/tmp/game-lib-noaa.jar) + stub f/l/j.ad
- 运行时原 jar + patch (JVM 无冲突)

## 当前 patch 状态 (58 类)

- 17 基线 (runtime_patch_batch) + 10 测试族 + 31 主链路扩展 (隔离 2 坏类)
- 主链路新增: appFramework 5 / AI 核心 (CombatMain/AINukeStrategy/UnitBuildStrategy/AIUnitGroupBase/TransporterGroup/AIStrategyNode) / MapLayerDef / GameEngine / GameSettings / LobbyPlayer / PathStorage / GameServerInfo / PacketSerializer / DefaultInputConfig / GameInput / GameEvent / AssetLoader 等

## 验证记录

| 轮次 | patch | 主链路 | 测试族 |
|------|-------|--------|--------|
| v19.110-1 | 27 类 | ✅ AI对战 4min | ✅ 7套件 0断言失败 |
| v19.110-2 | 44 类 | ✅ (00:08 一次 NoSuchFieldError:a 未复现) | ✅ 0断言失败 |
| v19.110-3 | 60 类 | ❌ NoSuchFieldError:a (units.aa/ad 字段漂移) | — |
| v19.110-4 | 58 类 | ✅ | ✅ 7套件 0断言失败 |

崩溃基线对照: 测试后渲染 NPE = jar 版 m.java (Unit Reference 测试) 自身副作用, 无 patch 同现。

## PENDING

- units/aa/ad 字段数漂移: 03 字段语义名 (productionRate/attachType) vs jar 短名, 保序 zip 数量不符 → 需手工字段映射后恢复
- DirectionType (units/custom/n) raw Enum vs Enum<n> 运行时机制 (§22)
