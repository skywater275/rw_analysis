# Rusted Warfare v1.15 — 单位指令系统源码逆向
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 15种指令类型、PingType(12种信号)、ActionFilter/ActionId
> 关键文件: `game/units/a/`(26个类), `GameAction.java`, `PingType.java`

---

## 1. 指令ID注册表

| ID模式 | 类 | 描述 |
|--------|---|------|
| c_1 | RallyPointAction(o) | 设置工厂集结点 |
| c_2 | ReclaimAction(m) | 回收建筑/单位换资源 |
| c_3 | RepairAction(n) | 修理目标单位 |
| c_4 | AttackMoveAction(e) | 攻击移动 |
| c_5 | StopAction(y) | 停止当前指令 (priority=-9990) |
| c_6_{type} | PingAction(j) | 地图信号 (12种, u.j) |
| c_7 | AttackAction(d) | 攻击目标 (u.k) |
| c_8 | GuardAction(f) | 护卫目标 (u.l, gui.actions.guardUnit) |
| c_9 | PatrolAction(i) | 巡逻 (u.m, gui.actions.patrol) |
| b_{unitId} | BuildQueueAction(v) | 工厂建造队列 (数量, UnitTypeHandle) |
| u_{unitId} | UnitBuildAction(l) | 建造单位指令 (显示造价/属性) |
| s_{unitId} | SellAction(z) | 出售/删除单位 (priority=-9999) |
| c__cut_chat | TeamChatAction(q) | 发送队伍聊天 (UI指令) |
| c__cut_ping | MapPingAction(r) | 发送地图标记 (UI指令) |

---

## 2. 类层次结构

```
GameAction(s) implements Comparable    ← 优先级排序基础
├── UnitActionBase(a)                  ← 顶层操作基类 (操作GameContext am)
│
├── [战斗指令]
│   ├── AttackAction(d)         c_7    攻击
│   ├── AttackMoveAction(e)     c_4    攻击移动
│   ├── GuardAction(f)          c_8    护卫
│   └── PatrolAction(i)         c_9    巡逻
│
├── [经济指令]
│   ├── ReclaimAction(m)        c_2    回收
│   ├── RepairAction(n)         c_3    修理
│   └── StopAction(y)           c_5    停止
│
├── [建造指令]
│   ├── AbstractBuildAction(w)         建造基类
│   │   ├── UnitBuildAction(l)  u_{id} 建造单位
│   │   ├── BuildQueueAction(v) b_{id} 工厂队列
│   │   └── UpgradeToT2Action   (game/units/d/u) 升级T2
│   ├── BuildAction(g)                 建筑放置
│   └── RallyPointAction(o)    c_1    集结点
│
├── [通信指令]
│   ├── AbstractCutsceneAction(p)      UI指令基类 ("c__cut_{name}")
│   │   ├── TeamChatAction(q)  c__cut_chat 队伍聊天
│   │   └── MapPingAction(r)   c__cut_ping  地图信号
│   └── PingAction(j)          c_6_{type}    游戏内信号
│
├── [其他]
│   ├── SellAction(z)           s_{id} 出售
│   ├── ActionWrapper(h)               装饰器 (委托给内部指令a)
│   └── AbstractImmediateAction(x)     即时无目标指令基类 (u.a, t.a)
│
└── [支持类型]
    ├── ActionFilter(b)                可用性检查器 (emptyActionFilter单例)
    ├── ActionId(c)                    驻留字符串标识符 (HashMap缓存)
    ├── ActionCategory(t)              指令类别枚举 (t.a=默认)
    ├── ActionTargetType(u)           目标类型枚举 (u.a~u.m)
    └── PingType(k)                    地图信号类型枚举 (a~l, 12种)
```

---

## 3. GameAction (s) — 核心指令基类

**文件**: `com/corrodinggames/rts/game/units/a/GameAction.java`

实现 `Comparable` 接口，按优先级排序:

| 字段 | 类型 | 含义 |
|------|------|------|
| g | float | 优先级 (=-999.0默认, 越小越高) |
| h | UnitActionBase | 操作委托 (默认=a.a) |
| a | ActionId | 指令ID字符串 |
| b | ActionFilter | 可用性过滤器 |

**关键方法**:
- `m_()` — 获取优先级 (优先使用g，否则从UnitTypeHandle获取)
- `a(s)` — 比较两个指令的优先级 (用于排序)
- `e()` → ActionTargetType — 目标类型
- `f()` → ActionCategory — 指令类别
- `g()` — 是否需要资源目标
- `i()` → UnitTypeHandle — 关联的单位类型

---

## 4. ActionFilter (b) — 可用性过滤器

**文件**: `com/corrodinggames/rts/game/units/a/ActionFilter.java`

```java
public class ActionFilter {
    public static final ActionFilter emptyActionFilter = new ActionFilter();
    
    public boolean isAvailable(GameAction action, UnitInstance unit) {
        return true;  // 默认全部可用
    }
}
```

子类可通过覆盖 `isAvailable()` 实现条件限制（如科技等级、资源需求）。

---

## 5. ActionId (c) — 驻留字符串标识符

**文件**: `com/corrodinggames/rts/game/units/a/ActionId.java`

```java
public class ActionId {
    private static final HashMap<String, ActionId> cache = new HashMap<>();
    public static final ActionId EMPTY = ActionId.from("-1");
    String id;
    
    public static ActionId from(String id) {
        // HashMap驻留: 相同字符串返回同一实例
        return cache.computeIfAbsent(id, ActionId::new);
    }
}
```

---

## 6. PingType (k) — 地图信号类型

**文件**: `com/corrodinggames/rts/game/units/a/PingType.java`

12种地图信号 (枚举 a~l):

| 枚举 | 本地化键 |
|------|---------|
| a | menus.ingame.ping.type.a |
| b | menus.ingame.ping.type.b |
| ... | ... |
| k | menus.ingame.ping.type.k |

每种信号的显示名称通过 `Localization.a("menus.ingame.ping.type.{name}")` 获取。

---

## 7. 指令序列化

指令通过 `InputNetStream(k)` / `OutputNetStream(as)` 序列化:

**写入 (OutputNetStream)**:
```java
action.write(stream) {
    stream.writeInt(action.id);    // 指令ID
    stream.writeFloat(priority);   // 优先级
    // 子类特定字段...
}
```

**读取 (InputNetStream)**:
```java
action.read(stream) {
    action.id = stream.readInt();
    // 子类特定字段...
}
```

---

## 8. 其他包中的指令

### game/units/d/ — 实验单位专属指令

| 类 | 描述 |
|----|------|
| UpgradeToT2Action(u) | 工厂升级T2 (gui.actions.upgradeT2, ar.d.c(2)) |

### game/units/h/ — 工厂操作

| 类 | 描述 |
|----|------|
| FactoryAction1~6 | 6种工厂操作类型 |
