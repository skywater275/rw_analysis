# 04 — 单位系统

> 逆向度: 100% | 数据: 90单位 -printunits + 200+ .ini文件

## 单位类型 — `y` (UnitType 基类, 90KB)

181个类型 (70内置 + 111自定义)

| 字段 | 含义 |
|------|------|
| `ab` (float) | ★ 收入累积器 |
| `ah` (short) | 收入缓存 |
| `M, N` | 名称引用 (内部名/显示名) |
| `P` (units.a) | 单位状态机 |
| `au` (k.c) | 价格/成本数据 |
| `av` (af[]) | 单位行为数组 (action_*) |
| `aB` (a.h) | 战斗节点 |
| `aC` (a.i) | 弹丸定义 |
| `aJ` (am[]) | 炮塔数组 |

| 方法 | 含义 |
|------|------|
| `i(float, au, ad)` | ★ 收入更新 (delta, weapon, action) |
| `i(int)` → float | 获取收入参数 |
| `c()` (as) | 价格 (通过 as 枚举) |
| `k()` | 是否为建筑 |
| `B()` → d.b | ResourceComponent (经济) |
| `u()` → d.b | ResourceComponent (战斗) |
| `g(am)` → d.b | 获取单位实例的资源组件 |
| `ao()` → au | 获取武器类型 |
| `S()` | 单位更新 |
| `b(n)` | 设置队伍 |
| `d()` → String | 内部名称 |
| `k()` → String | 显示名称 |

### 自定义单位类型 — `custom/j` (90KB)
```
extends y (UnitType)
implements ak (actions), d (behavior), d.l (factory)

x, z (custom.l)   — 单位类型定义
y (custom.as)      — 行为定义
A (custom.at[])    — 动作数组
dT (b.i[])         — 资源接口
K() — 初始化
C(am) — 注册单位
D(am) — 注销单位
h() → ao — 移动类型
cz() → e.f — 获取资源类型
```

## 单位实例 — `am` (UnitInstance)

完整字段见 [CLASS_DICTIONARY](CLASS_DICTIONARY.md)，核心:

```
HP:     cu(当前) / cv(最大)
护盾:   cx(当前) / max_shield
位置:   eo, ep
半径:   cj
进度:   cm (≥1.0=完成)
收入:   cy()
队伍:   bX → n (玩家对象)
死亡:   bV
出生点: bO(A) / bP(B)
```

## 单位状态机 — `units.a` (7状态枚举)

```
枚举值 (从 a.class 字节码确认):

  a — aggressive      (主动攻击: 攻击范围内所有敌人)
  b — guardArea       (区域守卫: 守卫指定区域)
  c — holdFire        (停火: 不主动攻击)
  d — mixed           (混合模式)
  e — onlyInRange     (仅范围内: 只在射程内攻击)
  f — outOfRange      (超出范围)
  g — returnFire      (反击: 仅在被攻击时还击)
  h — ?               (第8个值)

注意: 这与旧文档中的 idle/moving/attacking/building/repairing/reclaiming/loading 
      不同。旧文档的7状态是单位**当前动作**, 而 units.a 是单位**行为模式/姿态**。
```

## 单位状态 — 行为 vs 动作

```
行为模式 (units.a):  aggressive / guardArea / holdFire / returnFire ...
  决定了单位"是否"攻击

当前动作 (am.bF?):    idle / moving / attacking / building / repairing ...
  决定了单位"正在做什么"

两者独立运作: 一个 returnFire 模式的单位可以在 moving 状态
```

### 武器系统

| 类 | 功能 | 关键字段 |
|----|------|---------|
| `a.c` | 武器/动作类型 (String枚举) | `a(String)`查找, `a()`→名称 |
| `au` | 武器实例 | `a(av)`类型, `e/f`伤害/射程, `g`冷却, `h(am)`目标, `i(ab)`弹丸 |
| `a.s` | 动作定义 (abstract) | `g`消耗, `h(a.a)`处理器, `b(d.b)`资源, `N()`→类型 |
| `af` | 动作参数 | `a/b(float)`参数值 |

## LogicBoolean 脚本引擎 — 215 类

内嵌在单位 .ini 中的条件脚本语言，用于定义行为的触发条件。

### 基类 — `LogicBoolean` (abstract)
```
read(y) → boolean        — 评估条件 (核心方法, abstract)
readNumber(y) → float    — 读取数值
readString(y) → String   — 读取字符串
readUnit(y) → am         — 读取单位引用
create(l, String)        — 从 .ini 字符串创建 LogicBoolean
```

### 逻辑运算 (7个)
```
AndBoolean      — AND 组合
OrBoolean       — OR 组合
NotBoolean      — NOT 取反
StaticBooleanTrue/False  — 常量 true/false
StaticValueBoolean       — 静态值
TimeBoolean     — 时间条件
```

### 比较运算 (15个, CompareJoinerBoolean)
```
CompareEqualNumbers/Strings/Units/Boolean  — 相等
CompareNotEqualNumbers/Strings/Units/Boolean — 不等
CompareGreaterThanNumbers                  — 大于
CompareGreaterThanOrEqualNumbers           — 大于等于
CompareLessThanNumbers                     — 小于
CompareLessThanOrEqualNumbers              — 小于等于
MathAddJoinerBoolean                       — 加法
MathSubtractJoinerBoolean                  — 减法
MathMultiplyJoinerBoolean                  — 乘法
MathDivideJoinerBoolean                    — 除法
MathModulusJoinerBoolean                   — 取模
StringJoinerBoolean                        — 字符串拼接
```

### UnitReference — 22种单位引用

| 引用类型 | 含义 |
|---------|------|
| Self | 自身 |
| Parent | 父单位 (运输者/载具) |
| Nearest | 最近单位 |
| First | 第一个单位 |
| Chained | 链式关联单位 |
| Attachment | 附着单位 (模块蜘蛛模块) |
| Attacking | 当前攻击目标 |
| EventSource | 事件来源 |
| ActiveWaypointTarget | 当前路径点目标 |
| ThisActionTarget | 本动作的目标 |
| Transporting | 正在运输的单位 |
| LastDamagedBy | 上次攻击者 |
| Locked | 锁定目标 |
| Memory1/2 | 内存槽位 (临时存储) |
| GetOffsetAbsolute | 绝对偏移位置 |
| GetOffsetRelative | 相对偏移位置 |
| GetOffsetRelativeStatic | 静态相对偏移 |
| GetAsMarker | 标记点引用 |
| Null | 空引用 |
| Placeholder | 占位符 |

### LogicBooleanLoader — .ini 解析器

```
parseBooleanBlock(l, String, bool) → LogicBoolean
parseNumberBlock(l, String) → LogicBoolean
fixArguments(String) → String
setArgumentsWithMapping(ParameterMapping, ...)
```

### LogicBooleanGameFunctions — 游戏条件函数

```
注册所有游戏相关的条件函数:
  HasResources, ResourceCount, KillsBoolean
  TeamVictory, IsAttacking, IsMoving, IsAlive
  GetDistance, GetCredits, GetIncome, GetHP
  UnitCount, TeamHas, etc.
```

## 自定义动作系统

### 事件 — `custom.k` (Event)
```
a (ae)           — 事件配置 (.ini [action_*] 段)
b (j)            — 所属单位类型
c (am)           — 相关单位实例
d (h)            — 自定义类型引用
e (VariableScope)— 变量作用域
a()              — ★ 触发事件 (执行所有关联的 LogicBoolean)
```

### 动作集 — `custom.as` (ActionSet, 19种动作类型)
```
a (bool)         — 是否启用
b (float)        — 动作消耗
c (int)          — 动作类型ID
d-k (float)      — 8个动作参数 (伤害/范围/速度/冷却/溅射...)
m-o (bool/int)   — 额外标志/参数
p-r (float)      — 更多参数
s,t (static LinkedHashMap) — ★ 全局动作注册表

a(int) → at      — 按ID获取动作定义
a(j,as,at[])     — 从 .ini [action_*] 加载动作
a(j,as,l)        — 合并/继承动作
```

19个内部类 ($1-$19): 每种动作类型的实现

### 动作定义 — `custom.at` (ActionDef, abstract)
```
a (int)          — 动作ID
b (String)       — 动作名称
a() → ReturnType — 返回类型 (Void/Number/Boolean/Unit)
a(j, as) → double— ★ 评估动作消耗
a(j, double)     — 应用动作效果
b() → bool       — 前置条件检查
```

### 自定义类型 — `custom.h` (CustomUnitType)
```
a (g[])          — 单位属性数组
a() → bool       — 是否有效 (非空)
a(h) → bool      — 类型比较 (属性匹配)
b() → int        — 获取内部ID
```

## 空间索引 — `units.f` (SpatialGrid, 4类)

### `f.c` — 空间网格 (367B)
```
a,b (int)        — 网格尺寸
c,d (float)      — 单元大小
e (f.a[][])      — ★ 2D网格数组 (空间哈希)
a(x,y,r,unit,range,cb) — ★ 范围查询 (索敌/碰撞)
a(n,x,y,r,u)     — 按队伍范围查询
a(unit)          — 添加单位
a(b.b)           — 从地图初始化
```

### `f.a` — 网格单元
```
a (f.b)          — 地面单位
b (f.b[])        — 分层单位 (按高度/类型)
c (f.b)          — 空中单位
d (f.b)          — 特殊单位
```

### `f.b` — 单位列表 (动态数组)
```
b (int)          — 大小
c (am[])         — 内部数组
a(am) → bool     — 添加
b(am) → bool     — 移除
a() → am[]       — 获取所有
```

## 行为引擎 — `ag` (BehaviorEngine, 116KB, 全静态)

负责解析 .ini 文件并注册所有单位类型。

### 10 阶段初始化管道
```
ag.a() — 阶段1: 预初始化
ag.b() — 阶段2: 注册基础类型
ag.c() — 阶段3: 加载内置单位
ag.d() — 阶段4: 处理继承关系
ag.e() — 阶段5: 验证/检查
ag.f() — 阶段6: 构建行为树
ag.g() — 阶段7: 注册资源组件
ag.h() — 阶段8: ★ 最终激活 (GameUnitData.reloadUnitData 调用)
ag.i() — 阶段9: 运行时更新
ag.j() — 阶段10: 清理
```

### 注册方法
```
a(l)    — 注册自定义单位类型
b(l)    — 注册自定义单位类型 (第二阶段)
a(as)   — 注册内置单位类型 (UnitType 枚举)
a(e)    — 注册字符串引用
a(e[])  — 批量注册字符串
a(i)    — 从框架注册
```

### .ini 解析结果
```
每个 [action_*] 段 → custom.at (动作定义)
  [core]           → 基础属性 (HP/价格/速度)
  [action_upgrade] → 升级动作 (convertTo)
  [action_build_*] → 建造动作
  [attack]         → 攻击参数
  [movement]       → 移动参数
  [ai]             → AI 参数
  [graphics]       → 渲染参数
```

---

## UnitTypeLoader — `custom.l` (38KB) ★NEW

### 概述
**所有自定义单位类型的总加载器。** 384个字段, 88个方法。实现 `as` 接口 (UnitType interface)，是所有自定义 `.ini` 单位的主入口。

### 核心字段

```
静态/全局:
  a (Rect)              — 空矩形常量 (static final)
  b (custom.l)          — 单例引用 (static)
  c (ArrayList)         — 所有已加载单位类型 (static final)
  d (ArrayList)         — 活跃单位类型
  e (ArrayList)         — 所有单位类型 (含未激活)
  f (HashMap)           — 单位名称→类型映射 (static final)
  g (ArrayList)         — 网络同步单位列表

实例 — 每个单位类型的属性:
  h~q (utility.m ×10)   — 各类数据列表
    h: 动作列表
    i: 行为列表
    j: 资源列表
    k: 内部数据
    l: 炮塔列表
    m: 武器列表
    n: 弹丸列表
    o: 升级列表
    p: 动画列表
    q: 效果列表
  r (VariableMapping)    — LogicBoolean变量映射

  s~B (boolean ×11)     — 单位标志
    s: 是否建筑?      t: 是否飞行?
    u: 是否有护盾?    v: 是否实验?
    w: 是否可移动?    x: 是否工厂?
    y: 是否可攻击?    z: 是否需要资源点?
    A: 是否炮塔?      B: 是否特殊?
    G: Mod启用标志?

  C (Rect)              — 碰撞矩形
  D (String)            — 内部名称 (如 "units.extractor")
  E (String)            — 显示名称 (如 "Extractor")
  F (String)            — 描述文本
  H (int)               — 内部ID
  I (String)            — 分类标签
  J (i.b)               — Mod信息引用
  K/L/M (String)        — 路径/文件名

  N (utility.m)         — 子单位列表
  ... (~340 more fields)
```

### 关键方法

```
c(as) → as (static)            — 单位类型重映射 (处理升级/变体)
d(String) → custom.ay (523B)   — ★ 效果查找
  — 支持格式: "CUSTOM:name", "CUSTOM|name", "BUILTIN:name", "BUILTIN|name"
  — 找不到 → throw "Failed to find custom effect with the name: X"
  — 未知格式 → throw "Unknown effect format: X expected built-in effect or CUSTOM:"

e() → String (104B)            — 获取显示名称 (含翻译)
f() → String (104B)            — 获取描述文本
b(String) → int (99B)          — 按名称查找单位ID

B() → d.b                      — 获取经济ResourceComponent
u() → d.b                      — 获取战斗ResourceComponent
a() → am                       — 创建单位实例
o() → ao                       — 获取移动类型
```

### 单位注册流程

```
1. 扫描 mods/ 目录 → 找到所有 .ini 文件
2. 每个 .ini → custom.l 实例
3. 解析 [core] 段 → 基本属性 (名称/价格/HP/速度)
4. 解析 [action_*] 段 → custom.as (ActionSet)
5. 解析 [attack] 段 → 武器配置
6. 解析 [movement] 段 → 移动类型
7. 解析 [ai] 段 → AI参数
8. 解析 [graphics] 段 → 渲染配置
9. 注册到 f (HashMap) + c (ArrayList)
10. 版本检查 → 网络同步
```

---

## 建造时间

```
buildSpeed (from .ini) × FRAME_RATE(60fps) × speed_multiplier
= 1.0 / buildSpeed / 60 / 2.5 秒
```
