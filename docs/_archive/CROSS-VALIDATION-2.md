# 交叉验证报告 #2 — 常量 + 类识别 + 注释

> 验证 rw_py 字节码常量、新识别的类名、开发者注释 与 实际源码的一致性

---

## 1. rw_py 常量交叉验证

| rw_py 常量 | rw_py 值 | 源码位置 | 实际值 | 状态 |
|-----------|---------|---------|--------|------|
| CC_INCOME_CY | 18.0 | CommandCenter.java:176 | `return 18.0f` | ✅ 精确 |
| BUILD_SPEED_FACTORY | 0.03 | Factory.java:345-351 | C()=0.04f, D()=0.1f | ⚠️ 偏差 |
| BUILD_SPEED_NORMAL | 0.10 | Factory.java:351 | `return 0.1f` (D()) | ✅ 精确 |
| INCOMPLETE_DAMAGE_MULT | 1.75 | UnitInstance.java:1224 | `f2 *= 1.75f` | ✅ 精确 |
| RECLAIM_REFUND | 0.80 | y.java:2200 | `b6.a(this, 0.8f, true)` | ✅ 精确 |
| SHIELD_ABSORB | 0.2 | UnitInstance.java:1557 | `return 0.2f` (c()) | ✅ 精确 |
| SPATIAL_GRID_SIZE | 32 | SpatialGrid.java:34 | `int l = 32` | ✅ 精确 |
| SPATIAL_CELL_SIZE | 50.0 | SpatialGrid.java:95 | `float f9 = 50.0f` | ✅ 精确 |
| STARTING_CREDITS | 4000 | PlayerState.java:57 | `double credits = 4000.0` | ✅ 精确 |
| DEFAULT_SPEED | 2.5 | ah.java:19 (income) | `h = 1.0f` | ❌ 不匹配 |
| incomeMultiplier | — | ah.java:19 | `h = 1.0f` (非 2.5) | ⚠️ 2.5 是游戏速度, 非收入倍率 |

**结论**: 11项验证中 7项精确, 2项近似, 1项不匹配, 1项澄清。准确率 82%。

### 修正

```
收入公式应为: income/s = s.g × (60/40) × ah.h × gameSpeed
  其中 ah.h 默认值 = 1.0 (非 2.5)
  2.5 是 gameSpeed 的默认值 (在 ay.h 或 SettingsEngine 中)
```

## 2. 新类识别交叉验证

| 识别名 | 验证方法 | 结果 |
|--------|---------|------|
| **GameFlag** (game.a) | extends nothing, 3 flags | ✅ 状态标志基类 |
| **NetworkPlayer** (game.c) | extends PlayerState, empty override | ✅ 网络远程玩家 |
| **NeutralPlayer** (game.d) | teamId=-2, extends PlayerState | ✅ 中立 (代码确认 r=-2) |
| **HumanPlayer** (game.e) | 有 name 字段 (v), extends PlayerState | ✅ 本地人类玩家 |
| **TagFilter** (game.h) | 检查 TeamTag 匹配 | ✅ 标签过滤器 |
| **GameMode** (game.o) | 5值枚举 {a-e} | ✅ 5种游戏模式 |
| **ResourceType** (game.q) | 7值枚举 {a-g} | ✅ 7种资源类型 |
| **UnitTypeCount** (game.r) | as + int 字段 | ✅ 单位类型+数量对 |

## 3. 开发者注释交叉验证

| INI 注释 | 源码验证 | 结果 |
|---------|---------|------|
| `#footprint used for collisions (left,up,right,down)` | `RectF(left,top,right,bottom)` — 用于碰撞检测 | ✅ |
| `#spawnProjectilesOnEndOfLife` | EffectEngine spawns child effects on parent death | ✅ |
| `#No confirm needed on mobile` | `alwaysSinglePress: true` — 移动端一键操作 | ✅ |
| `#don't want speed up from nanoFactorySpeed` | Factory.nanoFactorySpeed 独立于 buildSpeed | ✅ |
| `#this also groups actions between units in UI` | ActionId 分组逻辑在 UI 层 | ✅ |
| `#debugMessage: %{ debug( ${var} ) }` | LogicBoolean 变量插值+debug 输出 | ✅ |
| `#So we don't get 2 copies showing` | `overrideAndReplace` 机制避免重复 | ✅ |
