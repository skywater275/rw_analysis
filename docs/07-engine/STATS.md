# 统计系统 — StatsManager + 校验和
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 击杀追踪, 定期采样, 校验和聚合, 反不同步检测
>
> 文件: `StatsManager.java`(141行), `StatsRecord.java`, `StatsHistory.java`, `j/ak.java`

---

## 1. StatsManager (bg.java)

### 1.1 核心字段

```java
bo b;           // fallbackRecord — 默认 StatsRecord
bo[] c;         // ★ playerRecords[n.e] — 每玩家一个 StatsRecord
int d;          // sampleCounter
boolean e;      // isActive
bl f;           // ★ samplingTimer — 定期采样定时器
```

### 1.2 定期采样 `b()` [line 57]

```java
// 采样间隔随游戏时间增长:
if (gameTime < 60000):    interval = 1000ms  (前1分钟高频)
if (gameTime < 1800000):  interval = 5000ms  (30分钟内)
if (gameTime < 3600000):  interval = 15000ms (1小时内)
else:                     interval = 30000ms
```

### 1.3 StatsRecord (bo.java) — 每玩家统计

| 字段 | 含义 |
|------|------|
| `c` (int) | **unitsKilled** — 击杀单位数 |
| `d` (int) | **experimentalsKilled** — 击杀实验单位 |
| `e` (int) | **buildingsKilled** — 击杀建筑 |
| `f` (int) | **unitsLost** — 损失单位数 |
| `g` (int) | **experimentalsLost** — 损失实验单位 |
| `h` (int) | **buildingsLost** — 损失建筑 |
| `l` (bn) | **statsHistory** — 历史时间线 |

### 1.4 击杀记录

```java
// 击杀分类:
if (killedUnit.bI()):  → experimentalsKilled++
if (killedUnit.dd()):  → buildingsKilled++
else:                  → unitsKilled++

// 损失分类 (相同逻辑):
if (lostUnit.bI()):    → experimentalsLost++
if (lostUnit.dd()):    → buildingsLost++
else:                  → unitsLost++
```

## 2. 网络校验和 (ak.java)

### 2.1 组件列表 (14个)

| 组件 | 含义 | 聚合方式 |
|------|------|---------|
| `c` "Unit Pos" | 单位位置 | `Float.floatToRawIntBits(x) + Float.floatToRawIntBits(y)` |
| `d` "Unit Dir" | 单位朝向 | `Float.floatToRawIntBits(facingAngle)` |
| `e` "Unit Hp" | 单位HP | 直接累加 `currentHp` |
| `f` "Unit Id" | 单位实体ID | 直接累加 `eh` |
| `g` "Waypoints" | 路径点 | 路径点ID累加 |
| `h` "Waypoints Pos" | 路径点位置 | `Float.floatToRawIntBits(x) × 1000` |
| `i` "Team Credits" | 队伍资金 | `(int)credits` 累加 |
| `j` "UnitPaths" | 单位寻路 | 寻路状态累加 |
| `k` "Unit Count" | 单位计数 | — |
| `l` "Team Info" | 队伍信息 | — |
| `m` "Team1 Credits" | 队伍1资金 | — |
| `n` "Team2 Credits" | 队伍2资金 | — |
| `o` "Team3 Credits" | 队伍3资金 | — |
| `p` "CC2" | 指挥中心2 | `f × 2.0` |
| `q` "CC3" | 指挥中心3 | `h` |

### 2.2 主校验和聚合 `b()` [line 40]

```java
public void b() {
    this.a = 0L;  // 重置主校验和
    this.a();     // 重置所有组件
    
    for (每个游戏对象 w in er):
        if (w instanceof y):  // UnitType/UnitInstance
            // 聚合位置, HP, 朝向, ID
            this.a += Float.floatToRawIntBits(posX) * 1000;
            this.a += Float.floatToRawIntBits(posY) * 1000;
            this.a += currentHp;
            this.a += entityId;
            
            // 聚合路径点
            if (ar() != null):
                this.g.b += waypointId;
                this.h.b += Float.floatToRawIntBits(waypointX) * 1000;
    
    // 聚合队伍资金
    for (每个玩家):
        this.i.b += (int)credits;
}
```

### 2.3 不同步检测

```java
// 服务器计算 checksum → 发送包30
// 客户端计算 checksum → 比对
if (serverChecksum != clientChecksum):
    → 检测到不同步
    → 可能触发完整重同步 (包35)
```

## 3. 对 RWAgent 的意义

- **Produced 计数**: 通过 `StatsRecord.d` (totalBuilt) 追踪, 建造完成时递增
- **收入校验**: Team Credits 参与校验和 → 修改 credits 会被检测
- **位置校验**: Unit Pos 使用 `Float.floatToRawIntBits` → 任何位置偏差都会被检测
