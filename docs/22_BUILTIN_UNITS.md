# Rusted Warfare v1.15 — 完整单位类型表 (173种)

> 来源: RWAgent list_types — 扫描 `ar` 枚举 + `custom.l` 加载器
> 日期: 2026-06-11 | 标记: ar=内置枚举, c=自定义加载器

---

## 总计: 52 (内置) + 121 (自定义) = 173 种

---

## 一、经济/资源 (14)

| 类型名 | 来源 | 说明 |
|--------|------|------|
| `commandCenter` | ar | 指挥中心 |
| `extractor` | ar | 提取器基类 |
| `extractorT1` | c | 提取器 T1 |
| `extractorT2` | c | 提取器 T2 |
| `extractorT3` | c | 提取器 T3 |
| `extractorT3_reinforced` | c | 提取器 T3 强化 |
| `extractorT3_overclocked` | c | 提取器 T3 超频 |
| `fabricator` | ar | 制造机基类 |
| `fabricatorT1` | c | 制造机 T1 |
| `fabricatorT2` | c | 制造机 T2 |
| `fabricatorT3` | c | 制造机 T3 |
| `crystalResource` | ar | 水晶资源点 |
| `creditsCrates` | c | 资金箱 |
| `crystal_mid` | c | 水晶 |

## 二、工厂 (7)

| 类型名 | 来源 | 说明 |
|--------|------|------|
| `landFactory` | ar | 陆军工厂基类 |
| `airFactory` | ar | 空军工厂基类 |
| `seaFactory` | ar | 海军工厂基类 |
| `experimentalLandFactory` | ar | 实验工厂 |
| `mechFactory` | c | 机甲工厂 |
| `mechFactoryT2` | c | 机甲工厂 T2 |
| `supplyDepot` | ar | 补给站 |

## 三、炮塔/防御 (31)

| 类型名 | 来源 | 说明 |
|--------|------|------|
| `turret` | ar | 炮塔基类 |
| `turretT2` | ar | 炮塔 T2 |
| `turretT3` | ar | 炮塔 T3 |
| `turret_artillery` | ar | 火炮炮塔基类 |
| `turret_flamethrower` | ar | 火焰炮塔基类 |
| `c_turret_t1` | c | 炮塔 T1 |
| `c_turret_t2_gun` | c | 炮塔 T2 机枪 |
| `c_turret_t3_gun` | c | 炮塔 T3 机枪 |
| `c_turret_t1_artillery` | c | 炮塔 T1 火炮 |
| `c_turret_t2_artillery` | c | 炮塔 T2 火炮 |
| `c_turret_t1_lightning` | c | 炮塔 T1 雷电 |
| `c_turret_t2_lightning` | c | 炮塔 T2 雷电 |
| `c_turret_t2_flame` | c | 炮塔 T2 火焰 |
| `antiAirTurret` | ar | 防空炮基类 |
| `antiAirTurretT2` | ar | 防空炮 T2 |
| `c_antiAirTurret` | c | 防空炮 |
| `c_antiAirTurretT2` | c | 防空炮 T2 |
| `c_antiAirTurretT3` | c | 防空炮 T3 |
| `antiAirTurretFlak` | c | 防空高炮 |
| `laserDefence` | ar | 激光防御 |
| `outpostT1` | c | 前哨站 T1 |
| `outpostT2` | c | 前哨站 T2 |
| `repairbay` | ar | 维修站 |
| `laboratory` | c | 实验室 |
| `wall_v` | ar | 墙壁 |
| `antiNukeLauncherC` | c | 反核发射器 |
| `nukeLauncherC` | c | 核弹发射器 |
| `NukeLaucher` | ar | 核弹发射器基类 |
| `AntiNukeLaucher` | ar | 反核发射器基类 |
| `damagingBorder` | ar | 伤害边界 |
| `fogRevealer` | ar | 探雾器 |

## 四、地面 — 坦克系 (16)

| 类型名 | 来源 | 说明 |
|--------|------|------|
| `tank` | ar | 坦克基类 |
| `c_tank` | c | 坦克 |
| `plasmaTank` | c | 等离子坦克 |
| `heavyTank` | ar | 重型坦克基类 |
| `megaTank` | ar | 巨型坦克 |
| `mammothTank` | ar | 猛犸坦克 |
| `c_mammothTank` | c | 猛犸坦克 |
| `laserTank` | ar | 激光坦克基类 |
| `c_laserTank` | c | 激光坦克 |
| `hoverTank` | ar | 悬浮坦克 |
| `heavyHoverTank` | ar | 重型悬浮坦克 |
| `experimentalHoverTank` | ar | 实验悬浮坦克 |
| `experimentalTank` | ar | 实验坦克 |
| `c_experimentalTank` | c | 实验坦克 |
| `tankDestroyer` | ar | 坦歼 |
| `missileTank` | c | 导弹坦克 |

## 五、地面 — 机甲系 (13)

| 类型名 | 来源 | 说明 |
|--------|------|------|
| `mechGun` | c | 机甲机枪 |
| `mechMissile` | c | 机甲导弹 |
| `mechArtillery` | c | 机甲火炮 |
| `mechLaser` | c | 机甲激光 |
| `mechLightning` | c | 机甲雷电 |
| `mechMinigun` | c | 机甲转管 |
| `mechFlame` | c | 机甲火焰 |
| `mechHeavyMissile` | c | 机甲重导弹 |
| `mechBunker` | c | 机甲碉堡 |
| `mechBunkerDeployed` | c | 机甲碉堡(部署) |
| `mechEngineer` | c | 机甲工程师 |
| `mechFlyingLanded` | c | 机甲飞行(降落) |
| `mechFlyingTakeoff` | c | 机甲飞行(起飞) |

## 六、地面 — 其他 (6)

| 类型名 | 来源 | 说明 |
|--------|------|------|
| `builder` | ar | 建造者 |
| `scout` | c | 侦察兵 |
| `combatEngineer` | c | 战斗工程师 |
| `c_artillery` | c | 火炮 |
| `heavyArtillery` | c | 重型火炮 |
| `ladybug` | ar | 瓢虫 |

## 七、空中单位 (18)

| 类型名 | 来源 | 说明 |
|--------|------|------|
| `helicopter` | ar | 直升机基类 |
| `c_helicopter` | c | 直升机 |
| `lightGunship` | c | 轻型炮艇 |
| `heavyInterceptor` | c | 重型截击机 |
| `c_interceptor` | c | 截击机 |
| `airShip` | ar | 飞艇 |
| `gunShip` | ar | 炮艇 |
| `missileAirship` | c | 导弹飞艇 |
| `bomber` | c | 轰炸机 |
| `spyDrone` | c | 侦察无人机 |
| `dropship` | ar | 运输机 |
| `amphibiousJet` | ar | 两栖喷气机 |
| `c_amphibiousJet` | c | 两栖喷气机 |
| `c_amphibiousJet_transition` | c | 两栖喷气(转换中) |
| `c_amphibiousJet_underwater` | c | 两栖喷气(水下) |
| `fireBee` | c | 火蜂 |
| `aaBeamGunship` | c | AA光束炮艇 |
| `aaBeamGunship_afterburn` | c | AA光束炮艇(加力) |

## 八、海军单位 (16)

| 类型名 | 来源 | 说明 |
|--------|------|------|
| `battleShip` | ar | 战列舰 |
| `heavyBattleship` | c | 重型战列舰 |
| `gunBoat` | ar | 炮艇 |
| `missileShip` | ar | 导弹舰 |
| `heavyMissileShip` | c | 重型导弹舰 |
| `heavyAAShip` | c | 重型防空舰 |
| `lightSub` | c | 轻型潜艇 |
| `attackSubmarine` | ar | 攻击潜艇 |
| `heavySub` | c | 重型潜艇 |
| `builderShip` | ar | 建造船 |
| `hovercraft` | ar | 气垫船 |
| `nautilusSubmarine` | c | 鹦鹉螺潜艇 |
| `nautilusSubmarineLand` | c | 鹦鹉螺(陆地) |
| `nautilusSubmarineSurface` | c | 鹦鹉螺(水面) |
| `robotCrab` | c | 机器蟹 |
| `robotCrabWater` | c | 机器蟹(水面) |

## 九、实验单位 (5)

| 类型名 | 来源 | 说明 |
|--------|------|------|
| `experimentalSpider` | c | 实验蜘蛛 |
| `experimentalGunship` | c | 实验炮艇 |
| `experimentalGunshipLanded` | c | 实验炮艇(降落) |
| `experimentalDropship` | c | 实验运输机 |
| `experiementalCarrier` | c | 实验航母 (sic) |

## 十、模块蜘蛛 (20)

| 类型名 | 来源 | 说明 |
|--------|------|------|
| `modularSpider` | c | 模块蜘蛛 |
| `modularSpider_nonEmpty` | c | 模块蜘蛛(非空) |
| `modularSpider_emptySlot` | c | 模块蜘蛛(空槽) |
| `modularSpider_antiair` | c | +防空 |
| `modularSpider_antiairFlak` | c | +防空高炮 |
| `modularSpider_antiairT2` | c | +防空 T2 |
| `modularSpider_antinuke` | c | +反核 |
| `modularSpider_artillery` | c | +火炮 |
| `modularSpider_blink` | c | +闪现 |
| `modularSpider_fabricator` | c | +制造机 |
| `modularSpider_fabricatorT2` | c | +制造机 T2 |
| `modularSpider_gunturret` | c | +机枪炮塔 |
| `modularSpider_gunturretT2` | c | +机枪炮塔 T2 |
| `modularSpider_laserdefense` | c | +激光防御 |
| `modularSpider_lightning` | c | +雷电 |
| `modularSpider_shieldGen` | c | +护盾发生器 |
| `modularSpider_smallgunturret` | c | +小型炮塔 |
| `modularSpider_smallgunturretT2` | c | +小型炮塔 T2 |
| `modularSpider_speed` | c | +速度模块(完成) |
| `modularSpider_speedIncomplete` | c | +速度模块(建造中) |

## 十一、虫子阵营 (19)

| 类型名 | 来源 | 说明 |
|--------|------|------|
| `bugGenerator` | c | 虫族发电机 |
| `bugGeneratorN` | c | 虫族发电机 N |
| `bugGeneratorNT2` | c | 虫族发电机 N T2 |
| `bugGeneratorT2` | c | 虫族发电机 T2 |
| `bugExtractor` | c | 虫族提取器 |
| `bugExtractorT2` | c | 虫族提取器 T2 |
| `bugMelee` | c | 虫族近战 |
| `bugMeleeSmall` | c | 虫族近战(小) |
| `bugMeleeLarge` | c | 虫族近战(大) |
| `bugMeleeT31` | c | 虫族近战 T31 |
| `bugRanged` | c | 虫族远程 |
| `bugRangedT2` | c | 虫族远程 T2 |
| `bugFly` | c | 虫族飞行 |
| `bugBee` | c | 虫族蜜蜂 |
| `bugSpore` | c | 虫族孢子 |
| `bugWasp` | c | 虫族黄蜂 |
| `bugPickup` | c | 虫族拾取 |
| `bugNest` | c | 虫巢 |
| `bugTurret` | c | 虫族炮塔 |

## 十二、特殊/装饰/编辑器 (7)

| 类型名 | 来源 | 说明 |
|--------|------|------|
| `tree` | ar | 树木 |
| `editorOrBuilder` | ar | 编辑器/建造器 |
| `dummyNonUnitWithTeam` | ar | 占位非单位(带队伍) |
| `spreadingFire` | ar | 蔓延火焰 |
| `missing` | c | 缺失单位(占位符) |
| `flare_10s` | c | 信号弹(10秒) |
| `zoneMarker` | ar | 区域标记 |

---

## 十三、统计

```
内置枚举 (ar):      51
自定义加载器 (c):   121
总计:               172
```

---

## 十四、命名规律速查

| 前缀/模式 | 含义 |
|----------|------|
| `c_*Turret*` / `c_turret_*` | 炮塔(建筑) |
| `c_antiAir*` / `antiAir*` | 防空 |
| `c_tank`, `c_laserTank` 等 | 陆地单位(非建筑) |
| `c_helicopter`, `c_interceptor` | 空中单位(非建筑) |
| `c_amphibious*` | 两栖单位 |
| `mech*` | 机甲 |
| `bug*` | 虫族 |
| `experimental*` | 实验单位 |
| `modularSpider*` | 模块蜘蛛 |
| `extractor*`, `fabricator*` | 资源/生产建筑 |
| `nautilus*` | 鹦鹉螺潜艇系列 |
| `robotCrab*` | 机器蟹系列 |

---

*来源: RWAgent list_types — 扫描 ar 枚举 + custom.l.c 加载器, 2026-06-11*
