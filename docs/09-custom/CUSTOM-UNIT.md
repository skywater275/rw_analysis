# CustomUnitType 属性加载 — INI参数→Java字段映射
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 4699行的核心: `a(l,boolean,boolean)` 从ModUnitRegistry复制所有属性到运行时
>
> 文件: `CustomUnitType.java:835-970`

---

## 1. 属性复制方法 `a(l, boolean, boolean)` [line 835]

```java
// l = ModUnitRegistry (INI解析结果)
// bl2 = isNewUnit (新建 vs 复用)
// bl3 = isUpgrade (升级 vs 初始)

public void a(l modReg, boolean isNew, boolean isUpgrade) {
    l4 = l.B();                    // GlobalState
    l3 = this.x;                   // 旧的 ModUnitRegistry
    as2 = this.y;                  // 旧的 UnitTypeHandle
    this.x = modReg;               // ★ 链接新的 ModUnitRegistry
    
    // ── 武器数组重映射 ──
    if (modReg.fQ.length != l3.fQ.length) {
        // 武器槽数量变化 → 重新分配数组
        ap[] newTurrets = new ap[modReg.fQ.length];
        // 尝试保留旧炮塔状态 (按名称匹配)
        // 填充空槽
        this.cL = newTurrets;
    }
    
    // ── 基本属性 ──
    this.cj = this.x.cW;           // ★ 碰撞半径 ← INI [core] radius
    this.ck = this.x.dd;           // 选择半径 ← INI [core] displayRadius
    
    // ── HP ──
    float oldMaxHp = this.cv;
    this.cv = this.y.c;                        // ★ maxHp ← as.c
    if (isNew):
        this.o(this.cv);                       // 满血
    else:
        this.o(this.cu / oldMaxHp * this.cv);  // 按比例缩放
    
    // ── 护盾 ──
    this.cA = this.y.g;                        // ★ maxShield ← as.g
    if (startShieldAtZero):
        // 从0开始
    else if (isNew):
        this.cx = this.cA;                     // 满盾
    else:
        this.cx = this.cx / oldShield * this.cA; // 按比例
    
    // ── 能量 ──
    this.cB = isNew ?
        this.y.d * this.x.cS :                 // ★ maxEnergy × 起始百分比
        this.cB / oldEnergy * this.y.d;
    
    // ── 朝向 ──
    if (this.x.aH && isNew):
        this.cg = -90.0f;                      // 默认朝上
}
```

## 2. INI参数 → Java字段速查

| INI [core] 参数 | Java字段 | 类型 | 行号 |
|----------------|---------|------|------|
| `radius` | `cW` → `this.cj` | float | 913 |
| `displayRadius` | `dd` → `this.ck` | float | 914 |
| `maxHp` | `as.c` → `this.cv` | float | 921 |
| `maxShield` | `as.g` → `this.cA` | float | 930 |
| `startShieldAtZero` | `x.cM` | boolean | 931 |
| `energyMax` | `as.d` → `this.cB` | float | 943 |
| `energyStartingPercentage` | `x.cS` | float | 943 |
| `buildSpeed` | 通过动作系统 | — | — |
| `mass` | `cL.b` (via as) | int | — |
| `price` | `ch` (via as) | int | — |
| `techLevel` | 动作过滤 | — | — |
| `isBuilding` | 类继承 (Building) | — | — |
| `footprint` | 通过放置规则 | — | — |

## 3. 武器系统链接

```java
// ModUnitRegistry.fQ[] — 武器定义数组
// 每个元素 = bn (武器配置)
//   .a = 武器名称
//   .e = 武器槽索引
// 
// this.cL[] — UnitInstance的炮塔数组 (ap[])
// 按名称匹配保留状态, 填充新槽
```

## 4. 对 RWAgent 的意义

反射读取单位属性时:
```java
// 正确的方式:
unit.cv  → maxHp    (via UnitTypeHandle)
unit.cj  → collisionRadius
unit.cA  → maxShield

// 注意:
// 这些值在 a(l,boolean,boolean) 中被设置
// 升级时会按比例缩放 HP/护盾/能量
```



