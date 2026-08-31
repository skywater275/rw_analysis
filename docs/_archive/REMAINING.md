# 剩余系统全覆盖 — 任务/生成/计时/数据块
> ⚠️ 历史文档 (v10.x 方法论, 2026-08-09) — 当前确定性重建方法学见 [PLAN.md](../deobfuscation/PLAN.md)

> 剩余工作清单已迁移至 [PLAN.md 剩余计划](../deobfuscation/PLAN.md) — 本文档为 v10.x 历史快照。


> 覆盖之前未文档化的所有剩余子系统
>
> 文件: MissionParser, AISpawnList, BackgroundWriter, DataBlock, PeriodicTimer, ExtraManager, TargetFilter

---

## 1. 任务解析器 (MissionParser.java)

```java
// 从地图 TMX <objectgroup> 的 <object> 元素解析 AITask
public static AITask a(AIWaveSystem, MapObject obj) {
    // 读取 type → MissionEvent 枚举
    e2 = e.a(string4);  // "move", "unitAdd", "changeCredits"...
    
    a3.g = e2;          // 事件类型
    a3.b = string3;     // 触发名称
    a3.y = n.k(team);   // 所属队伍
    
    // 时间参数:
    a3.r = delay;
    a3.p = repeatDelay;
    a3.o = repeatCount;
    a3.q = resetActivationAfter;
    a3.s = warmup;
    
    // 条件参数:
    a3.h = allToActivate;
    
    // 链接触发:
    // activateIds, alsoActivate, whenActivatedIds, activatedBy, deactivatedBy
}
```

## 2. AI生成系统

### AISpawnList — 出兵列表
```java
class AISpawnList {
    boolean a;           // 是否启用
    m b = new m();       // SpawnWeight 列表 ← 加权出兵项
    
    // 添加单位类型到生成列表
    b(as unitType, int count):  // 累加计数
    
    // 实际生成单位到地图
    a(float x, float y):        // 在坐标周围随机散布(±85)生成所有单位
}
```

### SpawnEntry (j) — 出兵条目
```java
class SpawnEntry {
    as a;        // UnitTypeHandle — 单位类型
    int b = 1;   // 数量
}
```

### SpawnWeight (k) — 加权出兵
```java
class SpawnWeight {
    as a;           // UnitTypeHandle
    float b = 1.0f; // 权重 (用于随机选择)
}
```

## 3. 任务管理

### TaskStatus — 任务状态
```java
class TaskStatus {
    a a;  // 关联的 AITask
    
    String a();  // 获取状态文本 (来自 AITask.z)
    boolean b(); // 是否完成 (来自 AITask.j)
}
```

### TargetFilter — 目标筛选 (7值枚举)
```java
enum TargetFilter {
    a, b, c, d, e, f, g;
    // a=none, b=allUnitsAndBuildings, c=allBuildings,
    // d=mainBuildings, e=commandCenter,
    // f=noConstructionOrTech, g=requiredObjectives
}
```

## 4. 回放/数据基础设施

### BackgroundWriter (bb) — 回放后台写入线程
```java
class BackgroundWriter implements Runnable {
    volatile boolean a = true;              // 运行标志
    ConcurrentLinkedQueue i;                // ★ 无锁队列 (bd/DataBlock)
    long j = 0L;                           // 最后写入时间
    int f;                                  // 最后一个指令帧
    
    // 添加数据块到队列
    a(bd block):  i.add(block), notifyAll()
    
    // 停止录制
    a():  a=false, 记录停止时的 tick
}
```

### DataBlock (bd) — 回放数据块
```java
class DataBlock {
    int a;           // tick/帧号
    boolean b;       // isWait (空帧)
    Long c;          // 聊天/UI数据
    byte[] d;        // 事件数据
    e e;             // ★ Command 对象 (rc块)
    byte[] f;        // 重同步数据
    bc g;            // 游戏状态数据
    int h, i;        // 额外字段
    float j, k;      // 坐标数据
}
```

### PeriodicTimer (bl) — 周期定时器
```java
class PeriodicTimer {
    m a;  // 回调列表 (bk)
    
    // 触发所有注册的回调
    a(am unit1, am unit2):
        for (bk callback : a):
            callback.a(unit1, unit2, null);
}
```

## 5. 引擎辅助

### ExtraManager (br) — 额外管理器
```java
class ExtraManager {
    l a;           // GlobalState 引用
    int b = 0;     // 计数器
    int c = 40;    // 静态常量
    int d = 0;     // 计数器2
    
    // 性能计时工具:
    static long a():      System.nanoTime()
    static float a(long): (now - t0) / 1e6  // ms since
}
```

## 6. 完整类目录 (gameFramework.n 包)

| 类 | 文件 | 行数 | 用途 |
|----|------|------|------|
| AITask | a.java | ~100 | 单个AI任务 (29字段) |
| AITaskQueue | b.java | ~50 | 任务队列 |
| MissionParser | c.java | 151 | 地图→AITask 解析 |
| MissionExecutor | d.java | 221 | 任务执行 |
| MissionEvent | e.java | 73 | 11种事件枚举 |
| AIWaveSystem | f.java | ~1000 | AI波次系统 |
| AIDifficulty | h.java | 9 | 难度枚举 |
| AISpawnList | i.java | ~80 | 出兵列表 |
| SpawnEntry | j.java | 17 | 出兵条目 |
| SpawnWeight | k.java | 17 | 加权出兵 |
| TargetFilter | l.java | 16 | 目标筛选 |
| TaskStatus | m.java | 21 | 任务状态 |

## 7. 覆盖率总结

```
gameFramework.n/ 包 — 14个类 — 100% 覆盖 ✅
gameFramework 根 — 20+ 核心类 — 100% 覆盖 ✅
gameFramework.j/ — 网络包 — 100% 覆盖 ✅
game/ — 游戏逻辑 — 100% 覆盖 ✅
game/a/ — AI系统 — 100% 覆盖 ✅
game/b/ — 地图系统 — 100% 覆盖 ✅
game/units/ — 单位系统 — 100% 覆盖 ✅
game/units/custom/ — Mod系统 — 逻辑引擎+参数 ✅
```
