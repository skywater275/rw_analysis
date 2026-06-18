# 28 — 单位类型名发现与匹配

> 日期: 2026-06-18 | 通过 JVM Agent 反射三源收集

---

## 一、问题

`findUnitType("landFactory")` 返回 null，因为：

1. **内置单位**（Land Factory, Tank, Builder...）存在 `ar` 枚举中，不在 `custom.l.f` HashMap
2. **命名差异**：代码 "landFactory" vs 游戏 "Land Factory"（空格+大小写）
3. `custom.l.f` 只包含自定义 mod 单位（`c_` 前缀）

---

## 二、单位类型的三层存储

### 层 1: ar 枚举 — 内置单位

```
com.corrodinggames.rts.game.units.ar
```

约 55 个枚举常量，包含所有游戏自带单位类型。名称字段通过 `getTypeName()` 读取（尝试 M/name/k/ac 字段）。

**示例内置名** (来自运行时反射):

```
Land Factory, Air Factory, Sea Factory, Extractor
Command, Builder, Tank, Artillery, Heavy Tank
Helicopter, Interceptor, Gun Ship, Battle Ship
Turret, Anti-air Turret, Repair Bay
Mega Tank, Laser Tank, Mammoth Tank, Experimental Tank
Fabricator, Supply Depot, Nuke Launcher, Anti-Nuke Defence
Attack Submarine, Gun Boat, Missile Ship, Landing Craft
Builder Ship, Amphibious Jet, Dropship
```

### 层 2: custom.l.f — 自定义/Mod 单位

```
com.corrodinggames.rts.game.units.custom.l → static HashMap f
```

约 18 个条目（根据当前测试地图的 .ini 配置），以 `c_` 前缀为主。

**示例自定义名**:

```
c_tank, c_artillery, c_antiAirTurret, c_turret_t1
c_laserTank, c_mammothTank, c_experimentalTank
c_helicopter, c_interceptor
c_turret_t1_artillery, c_turret_t2_gun, c_turret_t2_flame, c_turret_t3_gun
extractorT1, fabricatorT1, c_antiAirTurretT2
antiNukeLauncherC, nukeLauncherC
```

### 层 3: am.bE 全局单位注册表 — 存活实例

```
com.corrodinggames.rts.game.units.am → static List bE
```

包含所有当前存活的单位实例。通过 `unit.r()` → UnitType 对象 → `getTypeName()` 可获取其实时类型名。

**此方法能覆盖内置和自定义单位，但只能看到当前地图上存在的类型。**

---

## 三、完整类型清单 (72 种, 运行时收集)

### 建筑 (is_building=true)

| 名称 | 来源 | 说明 |
|------|------|------|
| Air Factory | ar | 空军工厂 |
| Anti-air Turret | ar | 防空炮塔 |
| Experimental Factory | ar | 实验工厂 |
| Extractor | ar | 资源提取器(内置) |
| Fabricator | ar | 制造厂 |
| Land Factory | ar | 陆军工厂 |
| Sea Factory | ar | 海军工厂 |
| Turret | ar | 炮塔 |
| Turret T2 | ar | 二级炮塔 |
| Turret T3 | ar | 三级炮塔 |
| c_antiAirTurret | custom | 自定义防空炮塔 |
| c_antiAirTurretT2 | custom | 自定义防空炮塔T2 |
| c_turret_t1 | custom | 自定义炮塔T1 |
| c_turret_t1_artillery | custom | 自定义火炮炮塔T1 |
| c_turret_t2_flame | custom | 自定义火焰炮塔T2 |
| c_turret_t2_gun | custom | 自定义炮塔T2 |
| c_turret_t3_gun | custom | 自定义炮塔T3 |
| extractorT1 | custom | 自定义资源提取器T1 |
| fabricatorT1 | custom | 自定义制造厂T1 |

> **注**: 以下"移动单位"中的 Anti-Nuke Defence、Nuke Launcher、Repair Bay、Supply Depot、Wall 等在游戏语义上是固定建筑，但分类依据的是游戏引擎 `is_building` 字段的返回值。此分类可能不完全反映实际单位性质。

### 移动单位 (is_building=false)

| 名称 | 来源 | 说明 |
|------|------|------|
| Amphibious Jet | ar | 两栖喷气机 |
| Anti-Nuke Defence | ar | 反核防御 |
| Artillery | ar | 火炮 |
| Attack Submarine | ar | 攻击潜艇 |
| Battle Ship | ar | 战列舰 |
| Builder | ar | 建造者 |
| Builder Ship | ar | 建造船 |
| Command | ar | 指挥官 |
| Crystal | ar | 水晶资源 |
| Dropship | ar | 运输机 |
| Experimental Tank | ar | 实验坦克 |
| Flamethrower | ar | 火焰喷射器 |
| Gun Boat | ar | 炮艇 |
| Gun Ship | ar | 武装直升机 |
| Heavy Hovertank | ar | 重型悬浮坦克 |
| Heavy Tank | ar | 重型坦克 |
| Helicopter | ar | 直升机 |
| Hovertank | ar | 悬浮坦克 |
| Interceptor | ar | 截击机 |
| Landing Craft | ar | 登陆艇 |
| Laser Shield | ar | 激光护盾 |
| Laser Tank | ar | 激光坦克 |
| Ladybug | ar | 瓢虫 |
| Mammoth Tank | ar | 猛犸坦克 |
| Mega Tank | ar | 巨型坦克 |
| Missile Ship | ar | 导弹舰 |
| Nuke Launcher | ar | 核弹发射器 |
| Repair Bay | ar | 维修湾 |
| Shielded Hovertank | ar | 护盾悬浮坦克 |
| Supply Depot | ar | 补给站 |
| Tank | ar | 坦克 |
| Unnamed Tank | ar | 未命名坦克 |
| Wall (V) | ar | 墙(垂直) |
| c_artillery | custom | 自定义火炮 |
| c_experimentalTank | custom | 自定义实验坦克 |
| c_helicopter | custom | 自定义直升机 |
| c_interceptor | custom | 自定义截击机 |
| c_laserTank | custom | 自定义激光坦克 |
| c_mammothTank | custom | 自定义猛犸坦克 |
| c_tank | custom | 自定义坦克 |
| antiNukeLauncherC | custom | 自定义反核发射器 |
| nukeLauncherC | custom | 自定义核弹发射器 |
| lightGunship | custom | 轻型武装直升机 |
| mechGun | custom | 机甲枪兵 |
| scout | custom | 侦察兵 |

### 系统/特殊单位

| 名称 | 说明 |
|------|------|
| Tree | 树（装饰） |
| fire | 火焰（特效） |
| damagingBorder | 伤害边界 |
| zoneMarker | 区域标记 |
| editorOrBuilder | 编辑器用 |
| dummyNonUnitWithTeam | 伪单位 |
| system_fogRevealer | 迷雾揭示器 |

---

## 四、getTypeName() 实现

```java
static String getTypeName(Object ut) {
    if (ut == null) return "?";
    for (Class<?> cl = ut.getClass(); cl != null && cl != Object.class; cl = cl.getSuperclass()) {
        try { Field mf = cl.getDeclaredField("M");
            Object mv = mf.get(ut);
            if (mv instanceof String && !((String)mv).isEmpty()) return (String)mv;
        } catch (Exception e) {}
        try { Field nf = cl.getDeclaredField("name");
            Object nv = nf.get(ut);
            if (nv instanceof String && !((String)nv).isEmpty()) return (String)nv;
        } catch (Exception e) {}
        try { Field kf = cl.getDeclaredField("k");
            Object kv = kf.get(ut);
            if (kv instanceof String && !((String)kv).isEmpty()) return (String)kv;
        } catch (Exception e) {}
        try { Field acf = cl.getDeclaredField("ac");
            Object acv = acf.get(ut);
            if (acv instanceof String && !((String)acv).isEmpty()) return (String)acv;
        } catch (Exception e) {}
    }
    String ts = ut.toString();
    if (ts != null && !ts.isEmpty() && !ts.contains("@")
        && !ts.equals(ut.getClass().getSimpleName()))
        return ts;
    return "?";
}
```

**优先级**: M → name → k → ac → toString() → "?"

- `custom.l` 实例用 `M` 字段返回自定义名（如 "c_tank"）
- `ar` 枚举用 `k` 字段返回显示名（如 "Land Factory"）
- 部分通过 `name` 字段（可能是父类 Enum.name）

---

## 五、模糊匹配 (findUnitType)

```java
static Object findUnitType(String typeName) {
    // Source 1: custom.l.f (mod 单位)
    // Source 2: ar 枚举 (内置单位)
    // Source 3: fuzzyFindUnitType (模糊匹配)
}

static Object fuzzyFindUnitType(String typeName) {
    // 去除空格、下划线、连字符 → 全小写比较
    // "Land Factory" → "landfactory"
    // "landFactory"  → "landfactory"   ← 匹配!
    // "landfactory"  → "landfactory"   ← 匹配!
}
```

---

## 六、关键教训

| # | 发现 | 影响 |
|---|------|------|
| 1 | `custom.l.f` 只有 18 个 mod 类型，内置单位全在 `ar` 枚举 | `findUnitType` 必须搜两个来源 |
| 2 | 内置名有空格和大小写（"Land Factory"），代码习惯无空格（"landFactory"） | 模糊匹配必不可少 |
| 3 | `M` 字段只在 custom.l 上有值，ar 枚举用 `k` 字段 | `getTypeName` 需层次搜索多个字段名 |
| 4 | `am.bE` 全局注册表能覆盖所有存活类型 | 最可靠的实时类型名来源 |
