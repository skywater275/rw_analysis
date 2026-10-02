# Rusted Warfare v1.15 — INI 解析系统源码逆向
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 追踪 .ini 文件 → 内存数据结构 → 运行时单位的完整链路
>
> 关键文件: `utility/ab.java` (解析器), `units/custom/ag.java` (加载器), `ModUnitRegistry.java`, `CustomUnitType.java`

---

## 1. 解析入口 — ag.java (扫描/加载)

**文件**: `com/corrodinggames/rts/game/units/custom/ag.java`

### 1.1 顶层加载: `h()`

```
ag.h()
├── 清空所有缓存和单位列表
├── ag.a("units", 1, false, null, ...)    ← 加载核心 units/ 目录
└── 遍历每个 mod:
    └── ag.a(modPath, 2, true, modInfo, ...)  ← 加载 mod 目录
```

### 1.2 目录扫描: `a(String path, int depth, ...)` (line 991)

递归遍历目录。关键行 **1044**:
```java
if (string5.toLowerCase(Locale.ENGLISH).endsWith(".ini") && !bl3) {
```
匹配所有 `.ini` 扩展名的文件（跳过 `desktop.ini`，line 1051）。

### 1.3 单文件解析: `a(String, InputStream, ...)` (line 1096)

约 1300 行的巨型方法，解析单个 `.ini` 文件到一个 ModUnitRegistry 对象。

**流程**:
```
1. 创建 ab2 = new ab(inputStream, string)    ← INI 解析器 (line 1147)
2. 创建 l5 = new ModUnitRegistry()            ← 元数据容器 (line 1158)
3. 读取各节参数 (lines 1159-2360):
   ├── [core] 节 — name, class, price, maxHp, tags...
   ├── [graphics] 节 — image, frames, offsets, shadows...
   ├── [attack] 节 — turrets, range, damage, projectiles...
   ├── [movement] 节 — speed, turnSpeed, height, moveType...
   └── [ai] 节 — AI 优先级, builder/harvester/attacker 标志...
4. 注册到 ModUnitRegistry.c 列表 (line 2384)
```

### 1.4 模板继承 (line 1176)

```java
ag.a(l5, ab2, l5.L + "/all-units.template", "AUTO units.template", true);
```
自动加载同目录下的 `all-units.template` 文件，所有参数可被覆盖。

### 1.5 共享文件引用 (lines 2978-3166)

| 前缀 | 路径 | 说明 |
|------|------|------|
| `SHARED:` | `units/shared/common.ini` | 跨 mod 共享 |
| `CORE:` | `units/common.ini` | 游戏核心 |
| `ROOT:` | `<mod>/common.ini` | mod 根目录 |

通过 `@copyFromSection` 机制从一个节复制参数到另一个节。

---

## 2. INI 解析器 — utility/ab.java

**文件**: `com/corrodinggames/rts/gameFramework/utility/ab.java`

### 2.1 正则表达式

```java
// 节头匹配 (line 47)
Pattern h = Pattern.compile("\\s*\\[([^]]*)\\]\\s*");
// 匹配: [sectionName], 捕获组1 = sectionName

// 键值对匹配 (line 48)
Pattern i = Pattern.compile("\\s*([^=:]*)(?:=|:)(.*)");
// 匹配: key=value 或 key:value
// 捕获组1 = key, 捕获组2 = value
```

### 2.2 主解析方法: `a(BufferedReader)` (line 153)

```
逐行读取:
├── 跳过空行
├── 跳过 # 注释行
├── 匹配 """ → 多行字符串模式 (直到下一个 """)
├── 匹配 [section] 正则 → 设当前节名
├── 匹配 key=value 正则 → 存入 LinkedHashMap
│   └── 结构: Map<sectionName, Map<key, value>>
├── 报告重复 key → d 列表
└── 报告不识别格式 → e 列表
```

### 2.3 内部存储

```java
private LinkedHashMap j = new LinkedHashMap();
// 结构:
// {
//   "core": {
//     "name": "MyUnit",
//     "price": "500",
//     "maxHp": "300"
//   },
//   "graphics": { ... },
//   "attack": { ... }
// }
```

### 2.4 类型化访问器 (lines 262-850)

```java
// 字符串
e(section, key)         → String  (必须存在, 否则抛异常)
a(section, key, default) → String  (有默认值)

// 布尔
a(section, key, Boolean default) → boolean

// 整数
g(section, key)                  → int     (必须)
b(section, key, Integer default) → int     (有默认)

// 浮点
h(section, key)                  → float   (必须)
a(section, key, Float default)   → float   (有默认)

// 逻辑布尔 (支持表达式如 "if not self.isOverLiquid()")
a(l, section, key, LogicBoolean default) → LogicBoolean

// 枚举
a(section, key, Enum default, Class) → Enum
```

### 2.5 合并/继承: `a(ab other)` (line 814)

将另一个 INI 的内容合并到当前对象，跳过标记为 `@copyFrom_skipThisSection` 的节。

---

## 3. ModUnitRegistry (元数据容器)

**文件**: `com/corrodinggames/rts/game/units/custom/ModUnitRegistry.java`

实现 `as` (UnitTypeHandle) 接口。所有已解析的单位参数存储为公开字段。

### 3.1 核心字段映射

| INI 键 | 字段 | 行号 | 含义 |
|--------|------|------|------|
| `[core] name` | `M` | 103 | 单位名称 |
| `[core] mass` | `cL.b` | 258 | 重量 |
| `[core] maxHp` | `cL.c` | 258 | 最大HP |
| `[core] price` | `ch` | 228 | 造价 |
| `[core] radius` | `cL.a` | 258 | 半径 |
| `[graphics] image` | `ad` | 120 | 贴图路径 |
| `[graphics] total_frames` | `U` | 111 | 帧数 |
| `[graphics] frame_width` | `W` | 113 | 帧宽 |
| `[movement] moveSpeed` | `cL.j` | 258 | 移动速度 |
| `[attack] maxAttackRange` | `cL.i` | 258 | 攻击范围 |

### 3.2 静态注册表

```java
static ArrayList c;  // 所有已注册单位类型 (line 67)
static ArrayList d;  // 活跃 Mod 单位列表 (line 68)
static ArrayList e;  // 额外列表 (line 69)
static HashMap f;    // 名称→单位类型 映射 (line 70)
```

### 3.3 注册流程

解析完成后 (ag.java line 2384):
```java
com.corrodinggames.rts.game.units.custom.l.c.add(l5);
```

---

## 4. CustomUnitType — 运行时单位实例

**文件**: `com/corrodinggames/rts/game/units/custom/CustomUnitType.java` (4699行)

### 4.1 与 ModUnitRegistry 的链接

```java
public class CustomUnitType extends w implements ak, d, d.l {
    public l x;  // 引用的 ModUnitRegistry (line 82)
}
```

构造函数 (line 1053):
```java
public CustomUnitType(boolean bl, l l2) {
    super(bl);
    this.a(l2, true, false);  // 从 ModUnitRegistry 复制所有属性
}
```

### 4.2 属性复制方法: `a(l, boolean, boolean)` (line 831)

将所有元数据从 `ModUnitRegistry` 复制到运行时实例:
- 基本属性: mass, radius, maxHp, speed
- 武器: 所有 `WeaponAction` 数组
- 移动: 移动类型, 速度, 转向速度
- 图形: 贴图路径, 帧信息
- 逻辑: 所有逻辑布尔条件

---

## 5. 完整数据流

```
用户 .ini 文件 (UTF-8 文本)
│
├── [section]          ← 节头正则: \s*\[([^]]*)\]\s*
├── name: value        ← 键值正则: \s*([^=:]*)(?:=|:)(.*)
├── # 注释              ← 跳过
└── """多行字符串"""    ← 特殊处理
│
▼ ag.java (文件扫描器)
├── 检查 .ini 扩展名 (line 1044)
├── 读取 common.ini / all-units.template
└── 处理 @copyFromSection 引用
│
▼ ab.java (解析器)
├── BufferedReader 逐行读取
├── LinkedHashMap<section, LinkedHashMap<key, value>>
└── 类型化 getter (int/float/bool/enum/logicBoolean)
│
▼ ModUnitRegistry.java (元数据)
├── 公开字段直接映射 INI 键
├── 静态列表 c, d, e, f (注册表)
└── 实现 as (UnitTypeHandle) 接口
│
▼ CustomUnitType.java (运行时)
├── 从 ModUnitRegistry 复制属性
├── 每帧更新 (HP, 位置, 状态)
└── 被 AI/玩家使用
```

---

## 6. 关键发现

1. **解析器类** `ab.java` 是一个通用 INI 解析器，不仅用于单位，也可用于地图/设置等
2. **支持两种分隔符**: `=` 和 `:` (半角冒号)
3. **多行字符串**: 使用 `"""` 包裹，用于复杂配置值
4. **模板系统**: `all-units.template` + `@copyFromSection` + `@copyFrom_skipThisSection`
5. **版本检查**: `a(String, int, String, String)` 方法确保 Mod 声明最低版本
6. **单位覆盖**: `overrideAndReplace` 参数允许 Mod 替换内置单位
7. **路径前缀**: `SHARED:`, `CORE:`, `ROOT:` 前缀用于跨 mod 引用
8. **逻辑布尔**: 支持完整的条件表达式引擎 (logicBooleans 包)，如 `"if not self.isOverLiquid()"`
