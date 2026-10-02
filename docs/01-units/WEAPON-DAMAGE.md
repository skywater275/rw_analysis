# 伤害系统 — 完整源码追踪
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 3系数伤害类型, 护盾吸收→穿透→HP, 建造惩罚×1.75
>
> 文件: `UnitInstance.java:1220-1264`, `game.f`(伤害类型)

---

## 1. 伤害类型 `com.corrodinggames.rts.game.f`

```java
// 三个伤害系数:
f3.ak  → shieldMultiplier     // 护盾吸收倍率
f3.al  → shieldBleedThrough   // 护盾穿透因子
f3.am  → hullMultiplier       // 船体伤害倍率
```

## 2. 完整伤害计算 (UnitInstance.java:1220-1264)

```java
public float a(am attacker, float rawDamage, f damageType) {
    // ── 阶段0: 建造中惩罚 ──
    if (this.buildProgress < 1.0f) {
        rawDamage *= 1.75;  // ★ 建造中 +75% 伤害
    }
    
    float shieldMult = 1.0f;
    float bleedThrough = 1.0f;
    float hullMult = 1.0f;
    if (damageType != null) {
        shieldMult = damageType.ak;    // 护盾吸收倍率
        bleedThrough = damageType.al;  // 穿透因子
        hullMult = damageType.am;      // 船体倍率
    }
    
    float remainingDmg = rawDamage;
    float totalApplied = 0.0f;
    
    // ── 阶段1: 护盾吸收 ──
    if (shieldRegenTimer == 0.0f && currentShield > 0.0f) {
        float shieldDmg = remainingDmg * shieldMult;
        
        if (currentShield < shieldDmg) {
            // 护盾不够 → 全部击穿
            remainingDmg -= currentShield * bleedThrough;
            totalApplied += currentShield;
            currentShield = 0.0f;
        } else {
            // 护盾足够
            currentShield -= shieldDmg;
            remainingDmg -= remainingDmg * bleedThrough;
            totalApplied += shieldDmg;
        }
    }
    
    // ── 阶段2: HP伤害 ──
    if (remainingDmg > 0.0f) {
        float hullDmg = remainingDmg * hullMult;
        
        if (currentHp < hullDmg) {
            // HP不够 → 死亡
            remainingDmg -= currentHp;
            totalApplied += currentHp;
            this.setHp(0.0f);  // o(0.0f)
            this.hpChangeAnimation += currentHp;
        } else {
            // HP足够
            this.setHp(currentHp - hullDmg);  // o(newHp)
            totalApplied += hullDmg;
            remainingDmg -= hullDmg;
            this.hpChangeAnimation -= hullDmg;
        }
    }
    
    // ── 阶段3: 记录 ──
    this.lastDamageTime = currentTick;
    this.lastAttacker = attacker;
    // 返回实际造成的伤害
    return totalApplied;
}
```

## 3. 治疗/修理 (UnitInstance.java:1179)

```java
public float b(am healer, float amount, f healType) {
    // 先回护盾 (最高到 maxShield)
    // 再回HP (最高到 maxHp)
    // 系数类似但方向相反
}
```

## 4. 死亡触发

```java
// UnitInstance.java:1278
public void checkDeath() {
    if (!this.isDead && this.currentHp <= 0.0f) {
        this.deathOrchestrate();  // bv()
    }
}
```

## 5. 关键数值

| 常量 | 值 | 行号 |
|------|-----|------|
| 建造中伤害惩罚 | ×1.75 | 1224 |
| 默认护盾吸收倍率 | 1.0 | 1226 |
| 默认穿透因子 | 1.0 | 1227 |
| 默认船体伤害倍率 | 1.0 | 1228 |
| 护盾吸收条件 | shieldRegenTimer==0 && shield>0 | 1236 |

## 6. 对 RWAgent 的意义

- 建造中的建筑受到1.75倍伤害 → 保护建造中的建筑很重要
- 护盾需要 `shieldRegenTimer==0` 才能吸收 → 刚受击后有短暂的无护盾窗口
- `f3` 参数允许不同类型武器有不同护盾/船体效率
