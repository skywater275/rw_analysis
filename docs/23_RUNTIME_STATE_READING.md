# 23 — 运行时游戏状态读取验证

> 日期: 2026-06-17 | 通过 JVM Agent 反射读取 v1.15 游戏内存

---

## 一、架构总览

```
GameEngine (l) ──B()→ 单例
  ├── bS → GameUI (f.g) ──bZ→ UnitList (选中单位)
  ├── cf → CommandController (c) ──b→ pendingCommands
  └── bL → MapEngine (b.b) ──C/D→ 宽高, A→ 资源点, z→ 三层

Player (n/d) ──k(int)→ 单例获取
  ├── o=资金(double), v=名字(String), r=队伍(int), E=战败(bool)
  └── T→ TeamUnitTracker(s) ──g=收入, c/d/f/b=单位统计

UnitInstance (am) extends GameObject (w)
  ├── w.eo/ep = 坐标 (父类, 需层级搜索!)
  ├── am.cu/cv = HP, am.bX = owner, am.bs = last_dmg
  ├── am.cm = build_progress, am.cx = shield, am.bL = on_map
  └── r() → UnitType (custom.l 或 ar$N)
```

---

## 二、验证的类与字段

### 2.1 PlayerTeam (n → 运行时 d)

| 字段 | 类型 | 含义 | 验证 |
|------|------|------|------|
| `o` | double | 资金 | ✅ 精确 |
| `v` | String | 玩家名 | ✅ 需层级搜索 |
| `r` | int | 队伍ID | ✅ |
| `E` | boolean | 战败 | ✅ |
| `T` | →TeamTracker | 单位追踪器 | ✅ |
| `k` | int | 槽位索引 | ✅ 需运行时层级搜索 |

**教训**: `p0.getClass().getDeclaredField("v")` 在运行时子类 `d` 上返回错误值(`,`), 必须从声明类 `n` 的层级搜索。使用 `playerNameField` 缓存声明类的 Field 引用。

### 2.2 TeamUnitTracker (s)

| 字段 | 类型 | 含义 | 验证 |
|------|------|------|------|
| `g` | int | 收入率 (raw cy/40帧) | ✅ 实际收入=×1.5/s |
| `c` | int | 已完成单位 | ✅ |
| `d` | int | 累计建造 | ✅ |
| `f` | int | 建造中 | ✅ |
| `b` | int | 非建筑单位 | ✅ |

### 2.3 UnitInstance (am) + GameObject (w)

| 字段 | 所在类 | 类型 | 含义 | 验证 |
|------|--------|------|------|------|
| `eo` | **w** | float | X坐标 | ✅ 父类, 需层级搜索 |
| `ep` | **w** | float | Y坐标 | ✅ 父类, 需层级搜索 |
| `cu` | am | float | 当前HP | ✅ |
| `cv` | am | float | 最大HP | ✅ |
| `cx` | am | float | 护盾 | ✅ |
| `cm` | am | float | 建造进度 | ✅ <1.0=在建 |
| `bT` | am | boolean | 存活 | ✅ |
| `bX` | am | →Player | 所有者 | ✅ 运行时类型→k字段 |
| `bL` | am | boolean | 在地图上 | ✅ |
| `bs` | am | int | 最后受伤害时间 | ✅ -9999=未受损, >0=战斗中 |
| `bE` | am | static List | 全局单位注册表 | ✅ ~106-200元素 |
| `em` | **子类** | int | 行为状态 | ✅ 运行时子类搜索 |

**em 字段值** (运行时子类, 不在基类 am 上):

| 值 | 含义 | 典型单位 |
|----|------|---------|
| 1 | 水中 | attackSubmarine |
| 2 | 地面可移动 | tanks, turrets, builders |
| 3 | 完全静止 | trees, factories, CC, extractors |
| 5 | 空中 | lightGunship |

**教训**: `em` 字段在 `al`(tree), `e.e`(tank) 等子类上, 基类 `am` 没有。必须用 `u.getClass()` 运行时层级搜索, 不能用缓存的基类 Field。

### 2.4 UnitType

| 字段 | 适用 | 类型 | 含义 | 验证 |
|------|------|------|------|------|
| `M` | custom.l | String | 类型名 | ✅ c_tank, mechGun... |
| `name` | ar$N | String | 内部名 | ✅ heavyTank... (非public!) |
| `bR` | custom.l | float | 碰撞半径 | ✅ 11.0 |
| `ca` | custom.l | float | 武器射程 | ✅ 60.0 |
| `bo` | custom.l | float | 速度/加速度 | ✅ 250.0 |
| `bT` | custom.l | float | 伤害 | ✅ 5.0 (需进一步验证) |
| `bX` | custom.l | float | 装甲 | ✅ 5.0 (需进一步验证) |
| `U` | custom.l | int | 移动类型 | ✅ 1-4 |

**教训**: `ar$N` 枚举类型**没有** float 字段, 只有 `custom.l` 有。`getField("name")` 失败因为 name 不是 public → 必须用 `getDeclaredField` + 层级搜索。

### 2.5 GameUI (f.g)

| 字段 | 类型 | 含义 | 验证 |
|------|------|------|------|
| `bZ` | UnitList | 选中列表 | ✅ 真正的选中 |
| `bM` | ArrayList | (废弃) | ❌ 永远为空 |
| `aX` | int | 选中计数 | ✅ |

---

## 三、UnitType 字段发现

通过 `type_fields` 诊断暴力扫描 `custom.l` (mechGun) 所有字段:

### Integer 字段 (部分):
`H`=1094172666, `R`=1, `S`=0, `U`=1(mov_type), `V`=2147483647,
`af`=18, `ag`=31, `bq`=4, `cl`=1, `cr`=40

### Float 字段:
`aj`=0, `aG`=1.0, `aL`=0, `bb`=0.2, `bc`=1.02, `bd`=1.0, `be`=0.0051,
`bf`=1.0, `bh`=0, `bH`=1.0, `bI`=1.0, `bo`=250(speed), `bp`=5400,
`bR`=11(collision), `bT`=5(dmg), `bX`=5(armor), `ca`=60(range), `ck`=0.0012

**总计**: 103 字段 (Int+Float+String+Boolean+Object)

---

## 四、MapEngine (b.b)

| 字段 | 类型 | 含义 | 验证 |
|------|------|------|------|
| `C` | int | 地图宽(tile) | ✅ 135 |
| `D` | int | 地图高(tile) | ✅ 125 |
| `A` | ArrayList(7) | 资源点 | ✅ Point(a=tileX, b=tileY) |
| `z` | ArrayList(3) | 图层 | ✅ Ground/Items/Units |
| `t` | ArrayList(32) | tile区域 | ✅ b.j, j=tile类型 |
| `P` | ArrayList(1) | 未知 | ✅ b.i |
| `V` | PointF | 参考点 | ✅ |

### MapEngine 方法

| 方法 | 参数 | 返回 | 验证 |
|------|------|------|------|
| `a(Point)` | `Point(a,b)` | `PointF(x,y)` | ✅ tile→world: ×20 |

**验证数据**:
```
tile(35,15) → PointF(700, 300) = 35×20, 15×20
world(700,300) → PointF(14000, 6000) = 700×20, 300×20
```

### android.graphics.Point (运行时)

实际 int 字段是 `a` 和 `b` (**不是** `x` 和 `y`!):
- `a` = tile X 坐标
- `b` = tile Y 坐标

### 图层 (b.e ×3)

| 层 | 名称 | grid | tile数组 | 说明 |
|----|------|------|---------|------|
| 0 | "Ground" | 135×125 | q[S] 16875 | 地面层 |
| 1 | "Items" | 135×125 | q[S] 16875 | 装饰/资源地 |
| 2 | "Units" | 135×125 | — | 初始单位 |

---

## 五、关键教训

| # | 问题 | 解决方案 |
|---|------|---------|
| 1 | `getDeclaredField` 不搜索父类 | `for sc=c; sc!=Object; sc=sc.superclass` |
| 2 | `getField` 只找 public | 必须 `getDeclaredField` + `setAccessible` |
| 3 | 运行时类型≠声明类型 | 用运行时类的 `getClass()` 搜索 |
| 4 | premain 时类未加载 | 懒加载 `ensureInit()` |
| 5 | 全类扫描触发 OpenGL 崩溃 | 只用 `am.bE` 静态字段, 不遍历 |
| 6 | `android.graphics.Point` 字段是 a/b | 不是 x/y |
| 7 | 子类字段不在基类 | `em` 需逐单位 `u.getClass()` 搜索 |
| 8 | `esc(null)` → `""` | 避免 JSON 注入, 需 null 检查 |
| 9 | `ar` 枚举无 float 字段 | 只对 `custom.l` 读 UnitType 属性 |
