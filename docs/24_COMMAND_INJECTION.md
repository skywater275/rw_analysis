# 24 — 原生命令注入运行时验证

> 日期: 2026-06-17 | 通过 JVM Agent 反射注入 Command 对象

---

## 一、注入架构

```
Python (ai_test.py)
  │  {"type":"inject","cmd_type":"move","type_filter":"all_mobile","x":"800","y":"500"}
  ▼
RWAgent.inject()
  │
  ├── Step 1: CommandController.createCommand() → Command 实例
  ├── Step 2: cmd.i = playerTeam0 (设置队伍)
  ├── Step 3: 设置 UnitCommand (move/attack_move/build/produce)
  ├── Step 4: type_filter 过滤 am.bE → 加入 cmd.v (selectedUnits)
  ├── Step 5: CommandController.b (pendingCommands) → add(cmd)
  │
  ▼
游戏主线程 → 安全执行 (不会触发 OpenGL 崩溃)
```

**关键**: 通过 pendingCommands 队列注入，而非直接调用 `Command.k()` (executeCommand)，避免跨线程 OpenGL 崩溃。

---

## 二、Command 类 (e)

### 字段

| 字段 | 类型 | 含义 | 注入用途 |
|------|------|------|---------|
| `i` | n/d | 队伍引用 | 设为 playerTeam0 |
| `j` | au | UnitCommand | 设定动作类型+坐标 |
| `v` | List | selectedUnits | type_filter 筛选加入 |
| `g` | boolean | stop/undo | STOP 指令 |
| `r` | boolean | systemAction | 投降/变速标志 |
| `u` | int | systemAction值 | 100=投降 |
| `s` | float | changeSpeed | 游戏速度 |
| `b` | String | 单位名 | BUILD/PRODUCE 目标类型名 |

### 方法 (运行时发现的全部签名)

```
a(float,float)             → setMoveTarget        [MOVE]
b(float,float)             → setAttackMoveTarget  [ATTACK_MOVE]
a(float,float,as,int)      → setBuildTarget       [BUILD]  (as=UnitTypeHandle)
a(y)                       → UnitType参数          [PRODUCE?]  (y=UnitType基类)
a(PointF)                  → PointF参数
a()                        → no-arg
a(am)                      → unit参数
a(AbstractList)            → list参数
a(float,float,boolean)     → 3-param variant
c(float,float)             → alternate coordinate
k()                        → executeCommand (直接执行, 危险!)
```

---

## 三、UnitCommand (au) + UnitCommandType (av)

### UnitCommandType 枚举 (17种)

| 值 | 名称 | 注入状态 |
|----|------|---------|
| 0 | move | ✅ 已实现 |
| 1 | attack | ❌ |
| 2 | build | ✅ 已实现 |
| 3 | repair | ❌ |
| 4 | loadInto | ❌ |
| 5 | unloadAt | ❌ |
| 6 | reclaim | ❌ |
| 7 | attackMove | ✅ 已实现 |
| 8 | loadUp | ❌ |
| 9 | patrol | ❌ |
| 10 | guard | ❌ |
| 11 | guardAt | ❌ |
| 12 | touchTarget | ❌ |
| 13 | follow | ❌ |
| 14 | triggerAction | ❌ |
| 15 | triggerWhenInRange | ❌ |
| 16 | setPassiveTarget | ❌ |

### UnitCommand 字段

| 字段 | 类型 | 含义 |
|------|------|------|
| `a` | av (enum) | UnitCommandType |
| `e` | float | targetX |
| `f` | float | targetY |
| `b` | ? | 目标 UnitType (用于 build) |

---

## 四、AI 自主选单位 (type_filter)

### 过滤逻辑

遍历 `am.bE` 全局注册表, 匹配条件:
- `getOwner(u) == 0` (我方)
- `unit alive`
- type 匹配:

| type_filter | 匹配规则 | 用途 |
|-------------|---------|------|
| `"all"` | 所有我方存活单位 | 全选 |
| `"all_mobile"` | 非建筑 + 非tree | 战斗单位 |
| `"builder"` | 含 builder/fabricator/Engineer | 建造者 |
| `"factory"` | 含 Factory/laboratory | 工厂生产 |
| `"c_tank"` 等 | 精确类型名匹配 | 特定单位 |

### 实现代码
```java
if (!typeFilter.isEmpty()) {
    List all = getUnitList();
    for (Object u : all) {
        if (getOwner(u) != 0) continue;
        String tn = getTypeName(getUnitType(u));
        boolean match = typeFilter.equals("all")
            || (typeFilter.equals("all_mobile") && !looksLikeBuilding(tn) && !tn.equals("?"))
            || (typeFilter.equals("builder") && (tn.contains("builder") || tn.contains("fabricator") || tn.contains("Engineer")))
            || (typeFilter.equals("factory") && (tn.contains("Factory") || tn.equals("laboratory")))
            || tn.equals(typeFilter);
        if (match) { target.add(u); added++; }
    }
}
```

---

## 五、已实现指令

### move
```json
{"type":"inject","cmd_type":"move","type_filter":"all_mobile","x":"800","y":"500"}
```
- Step 3: `cmd.a(float,float)` → setMoveTarget
- Step 4: type_filter 筛选单位
- 结果: `_cc0_tOK_xy=800,500_mvOK_auto=8_n=8_qOK`

### attack_move
```json
{"type":"inject","cmd_type":"attack_move","type_filter":"all_mobile","x":"800","y":"500"}
```
- Step 3: `cmd.b(float,float)` → setAttackMoveTarget
- 结果: `_amOK_auto=8_n=8_qOK`

### build
```json
{"type":"inject","cmd_type":"build","build_type":"extractorT1","x":"500","y":"300"}
```
- Step 3: `findUnitType(build_type)` → 遍历 `custom.l.f` HashMap values, 匹配 `M` 字段
- Step 3: `cmd.a(float,float,UnitType,int)` → setBuildTarget
- type_filter 自动="builder"

### produce (工厂造兵, 4策略)
```json
{"type":"inject","cmd_type":"produce","unit_type":"c_tank","type_filter":"factory"}
```
- Step 3: 4种策略依次尝试:
  1. `cmd.a(float,float,UnitType,int)` with (0,0,ut,1)
  2. `cmd.b` String field = unit type name
  3. `cmd.a(UnitType)` 单参数方法
  4. UnitCommand target type field
- 结果: `_m4=true` 或 `_strB=true` 等

### stop / surrender / speed
```json
{"type":"inject","cmd_type":"stop"}
{"type":"inject","cmd_type":"surrender"}
{"type":"inject","cmd_type":"speed","speed":"2.0"}
```

---

## 六、CommandController (c)

| 字段 | 类型 | 含义 |
|------|------|------|
| `b` | List | pendingCommands (安全注入点!) |

### createCommand()
通过反射调用无参方法: `cmdControllerClass.getDeclaredMethods() → 无参 + 返回 cmdClass → invoke()`

---

## 七、UnitType 查找 (build/produce)

```java
static Object findUnitType(String typeName) {
    Map map = (Map) utHashMapField.get(null);  // custom.l.f
    for (Object v : map.values()) {
        for (Class<?> cl = v.getClass(); cl != Object.class; cl = cl.getSuperclass()) {
            Field mf = cl.getDeclaredField("M");  // type name field
            if (typeName.equals((String)mf.get(v))) return v;
        }
    }
    return null;
}
```

**教训**: HashMap key 不是 M 字段值, 必须遍历 values 逐个匹配。

---

## 八、关键教训

| # | 问题 | 解决 |
|---|------|------|
| 1 | 直接 executeCommand() → OpenGL 崩溃 | 注入 pendingCommands 队列 |
| 2 | HashMap key ≠ M 值 | 遍历 values + 层级搜索 M |
| 3 | setBuildTarget 4参数方法 | 反射匹配 `(float,float,?,int)` |
| 4 | Android Rect 不可直接 cast | 全部用反射读取 |
| 5 | produce 策略需 try-catch 保护 | 每种策略独立 try-catch |
| 6 | 注入失败无详细反馈 | debug 字符串返回各步骤状态 |
