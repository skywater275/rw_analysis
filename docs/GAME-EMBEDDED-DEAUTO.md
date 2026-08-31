# 游戏自带"反编译"机制全索引 (v19.113q 系统性调查)

> 2026-08-22 | 用户指令: "系统性的寻找游戏自带的反编译" — 结果: 作者为 mod 系统内置了 6 层名称还原机制, 已全部定位并工具化

## 机制总览

| # | 机制 | 还原对象 | 规模 | 工具 | 状态 |
|---|------|---------|------|------|------|
| M1 | **mod 配置键表** (ag.java 加载器) | ModUnitRegistry 字段语义名 | 157 + custom.as 9 | fix_modkeys.py | ✅ 已提取 (supplement mod-key-verified) |
| M2 | **函数类语义名** (GameFunctions$XXX) | 游戏字段语义 (函数名=说明书) | 72 类 → 16+ 推断 | extract_func_semantics.py | ✅ 已提取 |
| M3 | **枚举 toString=name** | 52 单位类型等枚举 | 52+ | 存档 #Enum + clinit 位序 | ✅ v19.113h 已还原 |
| M4 | **@Parameter 运行时注解** | mod 函数参数语义名 | 51 类 (key 值) | extract_annotations.py | ⚠️ 提取率低 (多数注解无显式 key) |
| M5 | **VariableScope 运行时名称表** | mod 变量名注册 (interned) | 运行时动态 | agent dump existingVariableName | 📋 待运行时 |
| M6 | **类名语义保留** (内部类) | GameFunctions/枚举/内部类名 | 数百 | jar 类清单 | ✅ 天然保留 |

## M1 — mod 配置键表 (最完整, 157 字段)

`02b custom/ag.java` 加载器逐键读取 mod 配置:

```java
var17.bM = var11.a(var12, "dustEffect", Boolean.valueOf(false)).booleanValue();
var17.bi = var11.a(var12, "dieOnConstruct", Boolean.valueOf(false)).booleanValue();
```

- **键名 = 作者 mod API 文档语义名** (T0 级铁证)
- 提取模式: `var17.X = var11.[a-z](var1[2-4], "键名", ...)` → 157 对
- 03 改名战役: 需类型感知同步 v2 (stash@{0} modkeys-wip 待恢复 — 断裂 246 处根因: 短名跨类误伤)

## M2 — 函数类语义名 (作者自留说明书)

ProGuard 混淆了游戏本体, 但 **mod 逻辑函数类未被混淆** (作者保留):

```
LogicBooleanGameFunctions$SpeedBoolean    ← 类名=语义
  .read(UnitType y)                       ← 方法=语义
    invokevirtual y.z():F                 ← 混淆调用, 但上下文=速度!
    getfield y.cc:F / y.cd:F / y.cf:F     ← 速度分量/当前速度!
```

**函数名 → 成员绑定** (javap -c 反汇编 + 语义上下文):

| 函数 | 成员 (am) | 语义名 | 类型铁证 |
|------|----------|--------|---------|
| Speed/SpeedValue | cc/cd/cf | velocityX/velocityY/speedCurrent | float×3 |
| MaxMoveSpeed | z() | getMaxMoveSpeed | y 方法 |
| Shield/MaxShield | cx/cA | shieldCurrent/maxShield | float×2 |
| Energy/MaxEnergy | cB | energyMax | float |
| Ammo | cE | ammoCount | int |
| HasFlag | cF | unitFlags | int |
| Kills | cU | killCount | int |
| OverWater/OverClift/TouchWater | cJ/cI/cH | isOverWater/isOverCliff/isTouchingWater | float/bool/int |
| Moving | cK | isMoving | boolean |
| UnitId | eh | unitId | long (存档 eh=492 互证) |

- 陷阱: 反汇编常量池引用写 "y.X" (静态类型) 但字段真身在 am (继承) — 必须 javap 双类核对
- 陷阱: 同名方法/字段 (bs/bA/bB/de/dr/dI/cL/bi) — Method 与 Field 引用需区分, 8 条已剔除

### M2 深化 (v19.113q 第二轮): 66 函数完整绑定 + 官方注册名

- `LogicBooleanGameFunctions.loadTypes()` 反汇编: `new XXXBoolean` + `ldc "注册名"` 精确配对 → **66 函数官方名表** (mod 文档函数目录)
- 变体名机制: HeightBoolean 三变体 (underwater=true/ground=true/flying=true), EnergyBoolean (full=true/empty=true), SpeedBoolean (atTopSpeed=true)
- Field/Method 精确分离后新增 12 条绑定: cm=buildProgress / bA=customTimer / cB=energy / bd()=getEnergy / by()=includesQueued / ci=isReversing / cg=rotation / eq=height / bs=damageTaken / de()=handleCallbackCount / bz=creationSequence / bi()=isMovingFast
- **v19.113 语义名互证**: cu/cv=hp/maxHp (Hp/MaxHp 函数) ✓ / bV=isDead ✓ / bX=player (TeamXXX 函数) ✓ / eo/ep=坐标 (PositionX/Y) ✓

### M7 — mod API 函数目录 (数字/字符串/单位引用族)

- 数字函数: Distance/DistanceBetween/DistanceSquared/Length/SquareRoot/Direction/DirectionBetween/Rnd/createMarker
- 字符串函数: UpperString/LowerString/Substring/Select/StringCast/DebugPassthrough
- 单位引用: parent/attacking/attachment/activeWaypointTarget/customTarget1/customTarget2/lastDamagedBy/transporting/EventSource/NearestUnit (≤1500 距离)/globalSearchForFirstUnit/ThisActionTarget/getAsMarker/getOffsetAbsolute/getOffsetRelative/getOffsetRelativeStatic
- 全部 = 作者 mod 文档函数名 (语义保留)

## M3 — 枚举 toString=name (v19.113h 已还原)

52 单位类型枚举 (ar) + UnitState/WeaponTypeEnum 等: ProGuard 保留 Enum.name → 三重互证 (clinit 位序 ↔ 存档 #Enum ↔ toString)。

## M4 — @Parameter 注解 (部分有效)

`LogicBoolean$Parameter` 运行时注解 (type/required/positional/key): 51 类带注解, 但多数**无显式 key 值** (默认值) — 提取到 2 条 (default/index)。价值有限, 机制保留备用。

## M5 — VariableScope 运行时名称表 (待运行时)

`VariableScope$VariableName.existingVariableName` (HashMap) — mod 解析过的所有变量名 interned。**运行时 agent dump 计划**: 加载 mod 后 dump HashMap 全部 key → 变量语义名全集。

## M6 — 类名语义保留清单

- logicBooleans 包: 72 函数类 + 51 注解类 (全部语义名)
- 内部类: ParameterMapping/FieldOrMethod/VariableMapping/VariableName (反射成员解析器)
- 03 已有: 部分类名译名与 jar 语义名一致 (互证面)

### M8 — jar 混淆边界图 (v19.113q 第三轮)

1,005 个顶层类分布: **语义保留 137 / 混淆 868**:

| 包 | 语义/总数 | 性质 |
|----|----------|------|
| com.codedisaster | 68/68 | 第三方音频库 (无价值) |
| librocket (6类) + scripts (Root/Debug/Mods/Multiplayer/ScriptContext/ScriptEngine) | 6/6 | UI 脚本层 — script_api.json 已提取 |
| game.units.custom | 10/52 | mod 配置层部分保留 |
| gameFramework | 1/281 | **全混淆** (靠 M1-M3 还原) |
| game | 0/140 | **全混淆** (靠 M1-M3 还原) |

**结论: 无隐藏反编译器。混淆边界精确: 游戏本体全混淆, mod API 层语义保留 — 还原面 = M1 键名 + M2 函数绑定 + M3 枚举 + 存档序列化, 四法互证即当前方法论。**

## 结论

游戏**没有**传统意义的反编译器 (无 mapping.txt/无字符串还原器), 但作者的 **mod 系统本身就是最大的"反编译"**: 每个 mod 可配置字段有键名 (M1), 每个 mod 逻辑函数有语义名 (M2), 每个枚举有 name (M3)。三管齐下 + 运行时注册表 (M5) 可覆盖大部分语义还原面 — 这就是"游戏自带反编译"的系统性答案。

## 工具清单

- tools/fixers/fix_modkeys.py — M1 提取+改名 (v2 需类型感知)
- tools/fixers/extract_func_semantics.py — M2 绑定提取
- tools/fixers/extract_annotations.py — M4 注解提取
- 运行时: agent dump M5 (待写)
