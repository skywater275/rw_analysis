# 单位 ini 参数全集 (mod 规范)

> 来源: 用户提供的官方 mod 参数文档 (v19.111 入档)
> 用途: dump 字段值 ↔ 参数键对齐 → UnitType 字段语义推断

## 核心段

| 键 | 值类型 | 描述 |
|----|--------|------|
| name | string | 名称 |
| mass | int | 重量 |
| radius | int | 半径 |
| price | int | 造价 |
| maxHp | int | 最大血量 |
| selfRegenRate | float | 自动回血速度 |
| buildSpeed | float | 建造速度 |
| techLevel | "1"/"2"/"3" | 科技等级 |
| altNames | string(s) | 其他名称 |
| strictLevel | "0"/"1" | 严格等级 |
| isBio / isBug / isBuilder | bool | 生物/虫/建造者 |
| maxShield | int | 最大护盾 |
| shieldRegen | float | 护盾回复 |
| energyMax / energyRegen | float | 能量上限/回复 |
| armour | int | 护甲值 |
| showOnMinimap | bool | 小地图显示 |
| isBuilding | bool | 是建筑物 |
| footprint | -l,-t,r,b | 占地 |
| fogOfWarSightRange | int | 迷雾视野 |
| nanoRange / nanoRepairSpeed / nanoBuildSpeed | int/float | 纳米射线 |
| transportSlotsNeeded / maxTransportingUnits | int | 运输槽位 |
| nukeOnDeath / explodeOnDeath | bool | 死亡核爆/爆炸 |
| soundOnAttackOrder 等 | path(s) | 声音 |

## 匹配方法论 (v19.111)

1. dump 单位: `UnitRegistry.ae` (UnitTypeHandle 列表) → `UnitInstance.a(handle)` → 反射全字段
2. ini 参数: `assets/units/<name>/<name>.ini` (`name:` 键 ≠ 内部名 — 需文件名/内部名对照)
3. **值匹配**: dump 字段值 = ini 参数值 → 键-字段对 (铁证)
4. 已证: am.cu/cv=maxHp(当前/最大), am.cj/ck=radius(视野), am.cg=turretAngle, am.bT=isBuilder/移动, w.em=techLevel

## PENDING

- 内部名 ↔ ini 文件对照表 (dump 的 landFactory/commandCenter 与 ini 文件名 mechFactory 等不一致)
- builtin_mods 加载路径 (mega_builders mod-info → 内置单位清单)
