# 三管线验证 — 地图导入 / 单位表 / mod 加载

> v19.99 | 2026-08-16 | 以主链路 (GameEngine) 为基, 静态调用链 + 动态阶段序列双验证
> 当前状态: v19.108 (编译 19,337, -53.3%); 03 侧后续改名 (如 CommandPathPart) 以 03 实际为准

## 1. 主链路中的三管线位置 (GameEngine.init 序列)

```
GameEngine.init (game.i.a(Context))
  ├─ h("ModEngine")      bZ = i.a (ModEngine)          ← mod 管线入口
  ├─ h("CommandController") → h("GameSaver") → h("ReplayEngine")
  ├─ h("UnitGeoIndex")   cc = units.f.c
  ├─ h("Precalculating map fog")  ← 地图雾预计算 (启动期)
  ├─ ScorchMark → Projectile → Emitter
  ├─ h("Unit.loadAllUnits")  am.bH()                   ← 单位表管线
  │    → ModEngine.a(bl,bl) 加载 mod 单位 (含 spiderMod 49 单位)
  │    → getAllUnitsChecksum                           ← 单位校验和
  └─ 菜单背景地图加载 (x() → nextBackgroundMap 轮换)   ← 地图导入管线
       → "--- Loading map ---" (b.b = MapEngine.a)
```

## 2. 地图导入管线

### 静态链

| 类 | 角色 | T0 证据 |
|----|------|---------|
| game.b.b | **MapEngine** (v19.99 仲裁, 与 03 统一) | 132 地图字符串: "--- Loading map ---"/"---- Loading map data ----"/"Could not find map"/"Error exporting map"/"--- Saving map:".tmx + 2252 行 |
| game.b.e | TMXMapLoader | GZIP/Inflater/Base64 TMX 解析 |
| game.b.i / j / k | MapLayerDef / TilesetDef / TileEntry | 图层/图集/瓦片定义 |
| game.b.a / d / h | MapSpawn / MapLayerRenderer / TileDrawer | 生成点/图层渲染/瓦片绘制 |
| game.b.f | MapException | v19.92 运行时确认 |

### 动态阶段序列 (rw-dyn-pipelines.log)

```
smoothFog load took:27.940ms          ← 雾缓存
--- Loading map ---                   ← MapEngine.a (b.b)
Mapfile: assets/maps/menu_background/menu3.tmx
---- Loading map data ----
Map size: 120, 120
Setting up team fog..
MapLayer create: Ground               ← MapLayerDef 逐层
MapLayer create: Items
Building smoothFog_cache              ← 雾缓存构建 (Precalculating map fog)
```

## 3. 单位表管线

### 静态链

| 类 | 角色 | T0 证据 |
|----|------|---------|
| units.ar | **UnitRegistry** (v19.99 仲裁) | 137 字符串: "unit:"/"AntiNukeLaucher"/".name"/HTML 帮助模板 (`<img src="unit:`)/"Attack Range"; abstract class + a(boolean)/b() 抽象方法 (697 行); Debug.createUnit 用 ar.a(String) 按键查询 |
| units.as | **UnitType** (v19.99 仲裁) | interface + a() 工厂返回 am (单位实例创建) + 属性查询方法; Debug.createUnit 经 ar.a(String) 取得此类型 |
| am.bH() | Unit.loadAllUnits 入口 | init 序列 h("Unit.loadAllUnits") |

### 动态证据

- **42 内置单位键** (v19.90 printunits): AntiNukeLaucher/airFactory/builder/... + 中文帮助 HTML
- **43 单位 PNG 导出** (v19.91 -outputunitimages)
- **getAllUnitsChecksum** — 内置+mod 全单位校验和 (确定性验证基线, 与回放 es 校验 UnitPos/UnitHp 等互证)
- spiderMod 49 单位加载后: "Done loading custom units. image cacheHits:402 image cacheMisses:305 (in: 434.37ms)" — 707 张图缓存

## 4. mod 加载管线

### 静态链

| 类 | 角色 | T0 证据 |
|----|------|---------|
| gameFramework.i.a | **ModEngine** (bZ 字段) | h("ModEngine") 后 new; a() = k() 发现 + f() loadSelection |
| units.custom.ag | **ModLoader** (Rule E 202 共串 J=0.99) | "Loading units from mod"/"Disabled mod at"/INI 解析 |
| gameFramework.i.b | mod 条目 (hash/名/状态) | e() 保存 modSettings |

### 动态阶段序列 (rw-dyn-pipelines.log)

```
Number of mods:2                       ← k() 第一遍发现
Mod: 'Mega Builders' / 'spiderMod'
Number of mods:2                       ← 第二遍 (k() 单独)
Loading units from mod: spiderMod      ← ModLoader 解析 49 单位
Done loading custom units.
  image cacheHits:402 cacheMisses:305 (434.37ms)
========= Mods data loaded ===========
Mod: 'Mega Builders' - Memory use:0.00 mb - disabled
Mod: 'spiderMod' - Memory use:29.66 mb  ← enabled (安全模式清零后)
```

- **安全模式机制** (v19.96): numIncompleteLoadAttempts>1 → bZ.g() 全禁用 — 启动前清零
- **menu3.tmx 的 modularSpider 引用** = mod 管线与地图管线的汇合点 (mod 单位在地图单位层解析)

## 5. 三管线汇合验证 (设计者说明印证)

单位/mod/地图同一套逻辑 (设计者说明, v19.91):
- 共用加载管线: IniFile (utility.ab) / iniParse 计时器 (custom.ah) / FileLoader 缓存 (openAssetCached)
- 地图单位层引用 mod 单位 (modularSpider@menu3) → mod 禁用时地图加载报错
- 单位校验和 (getAllUnitsChecksum) 覆盖内置+mod 单位 → 回放 es 校验

## 6. 落库记录

- class-discoveries: game.b.b TileEntry→**MapEngine** (与 03 MapEngine.java 统一, 消除与 k=TileEntry 重复); units.ar ResourceUnit→**UnitRegistry** (与 03 UnitRegistry.java 一致; supplement 成员早已用 UnitRegistry 语义); 57 内部类级联 (b$1-2, ar$1-55)
- 遗留: 03 侧 ResourceUnit.java (2256 行) 对应类待确认
