# Phase B — 高效快速方法设计 (v19.111)

> 2026-08-20 | 基线 17,650 (-57.4%) | patch 58 类 | 基于 v19.110 全部机制沉淀
> 目标: 以最高吞吐推进"未混淆源码运行" (编译清零 + 运行时替换)

## 现状盘点 (实测)

| 瓶颈 | 数据 |
|------|------|
| 12 大文件 | 3,614 错误 (UnitType 462 / CustomUnitType 405 / AIStrategy 391 / ModLoader 363 / GameEngine 313 / Projectile 289 / Command 238 / PlayerState 229 / MapEngine 222 / EffectManager 214 / UnitRegistry 206 / NetEngine 205) |
| 单字母符号族 | b×285 a×250 f×217 c×172 n×162 h×141 e×132 d×128 g×120 l×101 j×97 ≈ **1,800** (广播断链) |
| patch 轮次 | 逐文件编译 736×~1.5s ≈ 18 分钟/轮 (无缓存) |
| 候选池 | 848 反向仅 80 可编译 (0错误限制 + 字段zip校验) |
| 保序对齐 | 已验证 (f↔GameUtils 99+25) 但 02 CFR 丢方法致噪声 |

## 五路并行流水线

### P1 广播收割 (~1,800 错误, 最高杠杆)
单字母符号族按族批量修: CSV 行号定位 → javap 签名仲裁方向 → 声明/调用端批量改 → gate 验证。
- 工具: `fix_broadcast_symbols.py --symbol b` (族参数化)
- 每族 -100~-300; 目标 2-4 族/会话 → -400~-800

### P2 签名对齐 (四大文件 1,621)
javap 完整方法表 (参数描述符) ↔ 03 方法签名归一化比对 → 生成签名修正 (覆盖族/重载族).
- 工具: `fix_sig_align.py --class UnitType` (继承树自上而下: UnitInstance→UnitType→CustomUnitType)
- 目标 -300~-500/会话

### P3 运行时验证一键化 (patch 扩展)
编译缓存 (CACHE_JSON 已建) + 一轮命令: 装组→启动→日志检测→触发测试→判定→坏类自动隔离.
- 工具: `run_verify_cycle.py --group N`
- 轮次 18 分钟 → <2 分钟 (缓存命中); 每会话 +30~60 类 patch

### P4 保序补全升级 (supplement 扩展)
fix_order_align 改 javap 方法表 (完整保序) + 签名参数类型匹配 (class-discoveries 反向归一化)
→ 高可信映射批量入 supplement → 反哺 P1 广播器.
- 工具: `fix_order_align.py --sig-verified` (升级版)

### P5 深水区移植 (已备料)
02 方法体已提取的依赖链 (CustomUnitType.b(PlayerState)/C(UnitInstance)/e(PlayerState) +
PlayerState 静态 a/b/c + TeamUnitTracker.a/b + UnitType.b/S/M/N) → 一次性移植
→ TestLogicBoolean/m/TestPerformance 三测试类进 patch → 断言验证.

## 吞吐预估

| 会话 | 编译 (-) | patch (+) | 机制 |
|------|----------|-----------|------|
| v19.109 | -1,187 | +17 | 手动批处理 |
| v19.110 | -187 | +41 | 测试族+管线 |
| v19.111 目标 | **-800~-1,200** | **+60~120** | 五路流水线 |

## 执行顺序 (依赖拓扑)

```
P4 (签名级对齐升级, 供映射) ──┐
                              ├→ P1 (广播收割, 消耗映射) ──→ 错误数大降
P2 (四大文件签名对齐, 独立) ──┘        │
                                      ↓
                              P3 (候选池扩大 ← 0错误类增多, 缓存轮次)
                                      ↓
                              P5 (深水区移植 → 测试类全进 patch)
```

## 铁律保持

- 每族/每类修改: 02 锚点或 javap T0 仲裁; 禁止猜测 (P4 噪声教训)
- 每批: gate 实测 → commit → 规则 7 文档
- patch 每轮: 主链路 + 测试族双验证 + 基线对照归责
