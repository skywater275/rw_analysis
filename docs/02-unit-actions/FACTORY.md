# 建造系统 — 完整管线交叉验证
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 从 RWAgent 注入 Command 到单位出现在地图上, 每条路径的精确源码追踪
>
> 关键文件: `Factory.java`(930行), `BuilderUnit.java`, `Command.java:413-585`, `UnitType.java:3314`

---

## 1. 建造指令完整路径

```
RWAgent.createCommand()
│
├── 1. 构造 Command 对象
│   ├── cmd.a(unit)                     ← 添加建造者单位
│   └── cmd.a(x, y, unitType, stages)  ← 设置建造目标 [Command.java:338]
│       └── waypointAction.d = av.c     ← BUILD 类型
│       └── waypointAction.b = unitType ← ★ 目标单位类型 (必须!)
│       └── waypointAction.e = x        ← 建造X坐标
│       └── waypointAction.f = y        ← 建造Y坐标
│
├── 2. 预验证: cmd.l()                    [Command.java:639]
│   └── 检查1: unitType 必须 instanceof y (OrderableUnit) [line 649]
│
├── 3. 排队: CommandController.b(player)
│   └── this.b.add(cmd)
│
├── 4. 执行: cmd.k()                      [Command.java:413]
│   │
│   ├── [line 544] stopOrUndo? → 跳过
│   │
│   ├── [line 567] 攻击移动合并? → 跳过 (建造指令不是)
│   │
│   ├── [line 572] ★ isValidNewWaypoint
│   │   ├── waypoint != null ✓
│   │   ├── waypoint.b != null ✓ (unitType已设置)
│   │   ├── 动作查找: this.a(unitType, av.c, false)
│   │   │   └── 遍历 N() 动作列表 [UnitType.java:3126]
│   │   │       └── 匹配: actionTarget == unitType
│   │   │           └── AND action.b(this) == true (可用)
│   │   │           └── AND action.a(this, false) == true (过滤器)
│   │   ├── 动作未锁定: action.g(this) == false
│   │   └── 动作可用: action.b(this) == true
│   │
│   ├── [line 580] 克隆: au copy = y5.d(waypoint)
│   │
│   └── [line 582] 写入: y5.a(copy)
│       └── UnitType.a(au) [line 3265]
│           └── g[f] = copy, f++ ← 路径点入队
│
└── 5. 单位获得路径点后
    ├── MovementController 导航到建造位置
    ├── 到达位置 → 开始建造动画
    └── buildProgress += buildSpeed × dt
        └── buildProgress >= 1.0 → 建造完成
```

## 2. Factory 建造系统

### 2.1 6个建造槽位

```java
// Factory.java:65-66
PointF[] buildSlots = new PointF[6];         // a 字段
PointF[] slotWorkPositions = new PointF[6];   // b 字段
```

### 2.2 建造队列

```java
// Factory.java:737
ArrayList buildQueue = new ArrayList();       // D 字段
```

### 2.3 工厂激活/停用

```java
// Factory.java:646
this.isActive = bl2;                          // c 字段

// isActive=true  → 工厂可以生产
// isActive=false → 工厂暂停 (AI通过此标志控制)
```

### 2.4 每帧更新

```java
// Factory.java:240
public void a(float f2) {
    if (f2 < 0.3f) f2 = 0.3f;  // 最小步进
    
    // 自动队伍分配
    if (this.autoAssignTeam && this.bX.b()) {
        for (int i = 0; i < n.c; i++) {
            n player = n.k(i);
            if (player == null || player.b()) continue;
            this.e(player);  // 分配队伍
            break;
        }
    }
    
    super.a(f2);  // 父类 UnitInstance 更新
}
```

## 3. BuilderUnit — 纳米建造

### 3.1 核心字段

| 混淆字段 | 含义 |
|---------|------|
| `a` (int) | 建造进度计数 |
| `b` (float) | 建造速度 |
| `g` (as) | 目标建造类型 |
| `h` (PointF) | 目标建造位置 |
| `i` (am) | 目标单位 (用于修理) |
| `j` (c) | 当前动作 (s.i = BUILD) |
| `k` (boolean) | 是否正在建造 |
| `l` (boolean) | 是否已完成 |

### 3.2 建造状态机

```
BuilderUnit 状态:
├── 空闲 (ar() == null)
│   └── AI 分配建造任务 → j = BUILD, g = targetType, h = targetPos
├── 移动中 (hasActiveWaypoint)
│   └── 导航到建造位置
├── 建造中 (j == BUILD 且 atPosition)
│   └── buildProgress += buildSpeed × dt
└── 完成 (buildProgress >= 1.0)
    └── 创建 UnitInstance → 放置到地图
```

## 4. 建造失败诊断速查

| 失败点 | 检查方法 | RWAgent 诊断 |
|--------|---------|-------------|
| waypoint.b == null | 反射读 `waypointAction.b` | `no_buildType` |
| 动作未找到 | `unit.N()` 遍历 | `no_action` |
| 动作锁定 | `action.g(unit)` | `action_locked` |
| 动作不可用 | `action.b(unit)` | `action_unavailable` |
| 工厂 isActive=false | `factory.c` | `factory_inactive` |
| 建造者 busy | `unit.ar() != null` | `builder_busy` |
| 资源不足 | `canAfford(price)` | `cant_afford` |

## 5. 交叉验证

| 之前的理解 | 源码验证 | 状态 |
|-----------|---------|------|
| 6个建造槽位 | Factory.java:65 `PointF[6]` | ✅ |
| buildSlots 字段 | `a` 字段 | ✅ |
| isActive 控制生产 | `c` 字段, line 646 | ✅ |
| BuilderUnit 有 g/h/i 目标参数 | BuilderUnit.java:25-27 | ✅ |
| 建造进度 ≥1.0 完成 | UnitInstance.java:686 | ✅ |
| cmd.a(x,y,as,int) 设置建造 | Command.java:338 | ✅ |
