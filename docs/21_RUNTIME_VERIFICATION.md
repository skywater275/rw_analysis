# 21 — 运行时验证补充

> 来源: RWAgent v4.0+ 系列 JVM Agent 运行时反射测试
> 日期: 2026-06-10 | 验证: 游戏进程内 Instrumentation API + 反射

---

## 一、运行时确认的完整字段映射

### 1.1 am (UnitInstance) 完整字段列表

以下是通过 `getDeclaredFields()` 在运行时获取的 `am` 类所有实例字段:

```
float:  br, bS, bZ, ca, cc, cd, ce, cf, cg, ch, cj, ck, cl, cm, cn,
        cu, cv, cw, cx, cy, cz, cA, cB, cC, cD, cJ, cV, do, dp, dq
int:    bs, bz, bA, bB, bC, bU, cE, cF, cH, cQ, cS, cT, cU, dl, dm, dn
boolean: bD, bL, bM, bN, bO, bP, bT, bV, bY, cb, ci, co, cp, cq, cr, cs, ct, cG, cI, cK, cM, cR
Object:  bt:am, bu:am, bv:am, bw:VariableScope, bx:b, by:b,
         bQ:am, bR:am, bX:n, cN:am, cO:y, cP:n
Array:   cL:ap[], dF:an[]
Other:   dH:f, dI:c, dJ:b
```

**已验证可读的字段**:

| 字段 | 类型 | 含义 | 运行时示例 |
|------|------|------|-----------|
| `cu` | float | 当前HP | 450.0 (CC), 210.0 (tank) |
| `cv` | float | 最大HP | 450.0, 210.0 |
| `cx` | float | 护盾 | 0.0 |
| `cm` | float | 建造进度 | 1.0 (已完成) |
| `bT` | boolean | 存活 | true |
| `bV` | boolean | 死亡 | false |
| `bX` | Object(d) | 所属玩家 | PlayerTeam子类实例 |
| `bL` | boolean | 在地图上 | true |

**在父类 w 上**:

| 字段 | 类型 | 含义 | 运行时示例 |
|------|------|------|-----------|
| `eo` | float | X坐标 | 2570.0, 1230.0 |
| `ep` | float | Y坐标 | 110.0 |

### 1.2 UnitType (y/custom.l) 字符串字段

| 字段 | 含义 | 示例 |
|------|------|------|
| `M` | ★ 单位类型标识 | `c_turret_t1`, `mechGun`, `commandCenter` |
| `D` | 内部资源路径 | `units/turrets/turret_t1.ini` |
| `E` | 资源文件路径 | `assets/units/turrets/turret_t1.ini` |

### 1.3 GameUI (f.g) 关键字段

| 字段 | 类型 | 运行时值 | 含义 |
|------|------|---------|------|
| `bM` | ArrayList | 始终 0 | (废弃) |
| `bZ` | UnitList | 选中时 ≥1 | ★ 当前选中单位 |
| `aX` | int | 选中数 | 选中计数 |
| `bO` | boolean | true(PC) | PC界面标志 |
| `bP` | boolean | true(PC) | PC界面标志 |
| `bQ` | boolean | true(PC) | PC界面标志 |

### 1.4 Command (e) 关键字段

| 字段 | 类型 | 含义 | 验证方式 |
|------|------|------|---------|
| `i` | n | team | ✅ 成功设置 |
| `j` | au | unitCommand | ✅ 成功设置 |
| `v` | List | selectedUnits | ✅ 成功添加单位 |
| `g` | boolean | stopCurrentAction | 文档 |
| `r` | boolean | isSystemAction | ✅ 投降测试 |
| `u` | int | systemActionType | ✅ 100=投降 |
| `s` | float | gameSpeedChange | 文档 |

### 1.5 CommandController (c)

| 字段 | 类型 | 含义 |
|------|------|------|
| `b` | ArrayList | ★ pendingCommands (指令队列) |

---

## 二、运行时验证的功能

### 2.1 全局单位注册表

| 访问方式 | 返回类型 | 大小 | 首个元素 | 验证 |
|----------|---------|------|---------|------|
| `am.bE` (static field) | `utility.u` (UnitList) | 106 | `al` (TreeUnit) | ✅ |
| `am.bF()` (static method) | `utility.o` (DequeList) | 111 | — | ✅ |

两者都 extends/implements `java.util.List`。

### 2.2 原生命令注入链路（完整验证）

```
用户框选单位 → GameUI.bZ (UnitList)
              → RWAgent 读取选中单位
              → CommandController.b() 创建 Command
              → Command.i = playerTeam
              → Command.a(x,y) / setMoveTarget
              → Command.v.addAll(selectedUnits)
              → CommandController.b.add(cmd) 加入队列
              → 游戏主线程下一帧执行
              → ✅ 单位成功移动
```

关键发现:
- 使用 `pendingCommands` 队列比直接调用 `executeCommand()` 安全（避免跨线程崩溃）
- GameUI 的 `bZ` (UnitList) 是唯一有效的选中来源
<<<<<<< HEAD

=======
>>>>>>> 320ccf9bcfe9ef4406bc23f0dd81fc7d87e056eb
### 2.3 游戏状态读取（getState 完整链路）

```
getState():
  ├── PlayerTeam (n/d): credits, team, name, defeated ✅
  ├── TeamUnitTracker (s): income_rate, units_completed, buildings_built ✅
  ├── Global units (am.bE): 遍历所有单位 ✅
  │     ├── w.eo/ep → x/y 坐标 ✅
  │     ├── am.cu/cv → HP ✅
  │     ├── am.bX → owner (类d, 搜索k字段) ✅
  │     ├── am.r() → UnitType → M字段 → 类型名 ✅
  │     └── am.cm → 建造进度 ✅
  └── Players: 遍历 n.k(0..9) ✅
```

### 2.4 已知可用的 UnitCommandType (av)

| 序号 | 枚举值 | 含义 | 测试状态 |
|------|--------|------|---------|
| 0 | move | 移动 | ✅ 已验证 |
| 7 | attackMove | 攻击移动 | 待测试 |
| — | stop | 停止 (通过 Command.g) | 待测试 |
| — | surrender | 投降 (systemAction=100) | 待测试 |

---

## 三、Agent 通信协议

### 请求/响应格式

```
TCP 127.0.0.1:9876
JSON 行协议 (每行一个消息, \n 分隔)
```

### 已验证的请求

| type | 含义 | 参数 | 响应关键字段 |
|------|------|------|------------|
| `ping` | 连接测试 | — | `status: "pong"` |
| `get_state` | 获取完整游戏状态 | — | `credits, income_rate, units[], players[], alive_units` |
| `diagnose` | 命令注入系统诊断 | — | `diag: "v4.0_..."` (所有反射发现的状态) |
| `inject` | 原生命令注入 | `cmd_type, x, y, speed` | `status: "ok", native: true` |
| `command` | Robot 键鼠 | `action, key, x, y` | `status: "ok"` |

### get_state 响应结构(列子:来自战役第一关)

```json
{
  "credits": 8858.0,
  "team": 0,
  "is_defeated": false,
  "income_rate": 26,
  "buildings_built": 5,
  "units_completed": 14,
  "in_construction": 2,
  "non_building_units": 4,
  "player_name": "Player",
  "players": [
    {"index": 0, "name": "Player", "team": 0, "is_defeated": false}
  ],
  "units": [
    {"x": 2570.0, "y": 110.0, "hp": 450.0, "max_hp": 450.0,
     "owner": 0, "type": "commandCenter", "is_building": true, "build_progress": 1.0}
  ],
  "alive_units": 100
}
```

### inject 指令参数

```json
{"type": "inject", "cmd_type": "move", "x": "800", "y": "500"}
{"type": "inject", "cmd_type": "stop"}
{"type": "inject", "cmd_type": "surrender"}
{"type": "inject", "cmd_type": "speed", "speed": "1.5"}
```

---

## 四、反射访问的关键教训

### 4.1 `getDeclaredField` vs 类层级

`Class.getDeclaredField(name)` **只查找当前类声明的字段**，不检查父类。

**解决方案**: 沿类层级向上搜索:
```java
for (Class<?> sc = unitClass; sc != null && sc != Object.class; sc = sc.getSuperclass()) {
    try { return sc.getDeclaredField(name); } catch (NoSuchFieldException e) {}
}
```

### 4.2 运行时类型 vs 声明类型

字段声明类型可能与运行时实例类型不同:
- `am.bX` 声明类型为 `n`，运行时返回类型为 `d`
- UnitType 声明类型为 `y`，运行时为 `custom.l` 或 `ar$N`

**解决方案**: 始终使用运行时对象的 `getClass()` 进行字段/方法搜索。

### 4.3 扫描 vs 直接访问

遍历所有已加载类并访问其字段**可能导致游戏崩溃**（OpenGL 上下文错误）。已确认安全的操作:
- ✅ 直接访问已知类名/字段名
- ✅ 扫描 `getAllLoadedClasses()` 但**不访问列表元素**
- ❌ 扫描列表元素（如 `list.get(i)`）可能导致崩溃
- ❌ 访问非 `com.corrodinggames` 包的类字段

### 4.4 初始化时机

`premain` (JVM Agent) 在游戏启动时运行，此时大部分游戏类尚未加载。必须在**首次使用时懒加载**反射引用，不能依赖 `initReflection()` 一次完成的假设。

---

## 五、内置单位类型补充

### 运行时观察到的类型名

| M值 | 类别 | HP示例 |
|-----|------|--------|
| `commandCenter` | CC | 4000 |
| `c_turret_t1` | 炮塔 | 800 |
| `c_antiAirTurret` | 防空 | 800 |
| `c_tank` | 坦克 | 210 |
| `mechGun` | 机甲 | 500 |
| `lightGunship` | 炮艇 | 50 |
| `extractorT1` | 提取器 | 800 |

### 建筑识别规则

单位以 `c_` 开头 → 建筑（如 `c_turret_t1`）
HP ≥ 4000 → 总指挥部（`commandCenter`）

---

*运行时验证: 2026-06-10 | RWAgent v4.0 系列 | 游戏进程内 Instrumentation + 反射*
