# 20 — 插件/Mod系统完全解析

> 来源: RW-HPS (Plugin/PluginCenter/PluginManage) + game-lib.jar (ag/fw.i/custom.*) + rwTool (rwmodProtect)
> 验证: 运行时插件加载 + .ini结构分析

---

## 一、游戏Mod系统 (引擎侧)

### 1.1 Mod加载器 (fw.i.a, 15KB)

```
Mod系统目录:
  mods/
  └── <modname>/
      ├── mod-info.txt          — Mod元信息
      │   name: <显示名称>
      │   description: <描述>
      │   author: <作者>
      │   version: <版本号>
      │   minGameVersion: <最低游戏版本>
      │
      ├── units/                 — 自定义单位定义
      │   ├── myUnit.ini
      │   └── ...
      ├── maps/                  — 自定义地图
      ├── graphics/              — 自定义图像
      ├── sounds/                — 自定义音效
      └── scripts/               — 自定义脚本

加载流程:
  1. 扫描 mods/ 目录
  2. 解析 mod-info.txt
  3. 版本检查: "Bad build number"
  4. 加载 assets (图像/音效)
  5. 解析 units/*.ini → UnitTypeLoader
  6. 注册单位类型 → ag (BehaviorEngine)
```

### 1.2 Mod信息 (fw.i.b, 12KB, 48字段)

```
ModInfo (fw.i.b):
  name: String                — Mod名称
  description: String         — 描述
  author: String              — 作者
  version: String             — 版本
  minGameVersion: String      — 最低游戏版本
  dependencies: List<String>  — 依赖的其他Mod
  loadOrder: Int              — 加载顺序
  enabled: Boolean            — 是否启用
  path: String                — 文件路径
  icon: Texture               — 图标
  tags: List<String>          — 标签
```

### 1.3 自定义单位加载 (custom.l, 38KB)

```
UnitTypeLoader — 384字段, 88方法

加载管道:
  1. 扫描 .ini 文件
  2. 解析 [core] 段 → 基本属性
  3. 解析 [action_*] 段 → 动作定义
  4. 解析 [attack] 段 → 武器配置
  5. 解析 [movement] 段 → 移动参数
  6. 解析 [ai] 段 → AI行为
  7. 解析 [graphics] 段 → 渲染配置
  8. 注册到全局HashMap (名称→类型)
  9. 版本检查 → 网络同步
```

### 1.4 行为引擎 (ag, 116KB, 全静态)

```
BehaviorEngine — 10阶段初始化管道:

  ag.a() — 阶段1: 预初始化
  ag.b() — 阶段2: 注册基础类型
  ag.c() — 阶段3: 加载内置单位 (ar枚举, 53值)
  ag.d() — 阶段4: 处理继承关系 (copyFrom)
  ag.e() — 阶段5: 验证/检查
  ag.f() — 阶段6: 构建行为树 (LogicBoolean)
  ag.g() — 阶段7: 注册资源组件
  ag.h() — 阶段8: ★ 最终激活 (所有单位可用)
  ag.i() — 阶段9: 运行时更新
  ag.j() — 阶段10: 清理

注册方法:
  a(l)     — 注册自定义单位类型
  b(l)     — 注册自定义单位 (第二阶段)
  a(as)    — 注册内置单位类型
  a(e)     — 注册字符串引用
  a(i)     — 从框架注册
```

---

## 二、LogicBoolean 脚本引擎 (215类)

### 2.1 系统总览

```
LogicBoolean — 游戏内置的脚本条件引擎
  215个类实现完整的条件判断系统
  
  基类 (LogicBoolean, 16KB, 42方法):
    read(y) → boolean        — 条件评估 (核心)
    readNumber(y) → float    — 读取数值
    readString(y) → String   — 读取字符串
    readUnit(y) → am         — 读取单位引用
```

### 2.2 逻辑运算 (7个)

```
AndBoolean              — AND 组合
OrBoolean               — OR 组合
NotBoolean              — NOT 取反
StaticBooleanTrue       — 常量 true
StaticBooleanFalse      — 常量 false
StaticValueBoolean      — 静态值
TimeBoolean             — 时间条件
```

### 2.3 比较/数学运算 (15个)

```
CompareEqualNumbers          — 数值相等
CompareEqualStrings          — 字符串相等
CompareEqualUnits            — 单位相等
CompareEqualBoolean          — 布尔相等
CompareNotEqualNumbers       — 数值不等
CompareNotEqualStrings       — 字符串不等
CompareNotEqualUnits         — 单位不等
CompareNotEqualBoolean       — 布尔不等
CompareGreaterThanNumbers    — 大于
CompareGreaterThanOrEqualNumbers — 大于等于
CompareLessThanNumbers       — 小于
CompareLessThanOrEqualNumbers — 小于等于
MathAddJoinerBoolean         — 加法
MathSubtractJoinerBoolean    — 减法
MathMultiplyJoinerBoolean    — 乘法
MathDivideJoinerBoolean      — 除法
MathModulusJoinerBoolean     — 取模
StringJoinerBoolean          — 字符串拼接
```

### 2.4 游戏条件函数 (73+)

```
状态检测:
  HpBoolean, ShieldBoolean, EnergyBoolean
  MovingBoolean, IsAttackingBoolean, IsBuildingBoolean
  IsAliveBoolean, IsIdleBoolean
  IsTransportingBoolean, IsBeingTransportedBoolean

资源/经济:
  HasResourcesBoolean, PriceCreditsBoolean
  GetCredits, GetIncome, GetHP, GetShield

队伍/统计:
  NumberOfUnitsInTeam, TeamVictoryBoolean
  TeamHasBoolean, PlayerCountBoolean
  KillsBoolean, LossesBoolean

地形检测:
  OverCliftBoolean, OverWaterBoolean
  InMapBoolean, DistanceCheckBoolean

计时器:
  TimeAliveBoolean, CustomTimerBoolean
  GameTimeBoolean, WaveTimerBoolean

标签系统:
  TagsBoolean, TeamTagBoolean
  HasTagBoolean, TagValueBoolean

特殊:
  UnitCount, TeamHas, GetDistance
  RandomBoolean, AlwaysTrue/AlwaysFalse
```

### 2.5 数值函数 (17个)

```
FunctionDistance            — 距离计算
FunctionDistanceBetween     — 两点距离
FunctionSin, FunctionCos    — 三角函数
FunctionRnd                 — 随机数
FunctionMin, FunctionMax    — 最值
FunctionSquareRoot          — 平方根
FunctionInt                 — 取整
FunctionAbs                 — 绝对值
FunctionPow                 — 幂运算
```

### 2.6 单位引用 (26个, UnitReference)

```
Self                        — 自身
Parent                      — 父单位 (运输者/载具)
Nearest                     — 最近单位
First                       — 第一个单位
Chained                     — 链式关联
Attachment                  — 附着单位 (模块蜘蛛模块)
Attacking                   — 当前攻击目标
EventSource                 — 事件来源
ActiveWaypointTarget        — 当前路径点目标
ThisActionTarget            — 本动作目标
Transporting                — 正在运输的单位
LastDamagedBy               — 上次攻击者
Locked                      — 锁定目标
Memory1/2                   — 内存槽位
GetOffsetAbsolute           — 绝对偏移位置
GetOffsetRelative           — 相对偏移位置
GetOffsetRelativeStatic     — 静态相对偏移
GetAsMarker                 — 标记点引用
Null                        — 空引用
Placeholder                 — 占位符
```

### 2.7 条件评估示例

```ini
# .ini 中的条件表达式
[action_attack]
autoTrigger: And HasResources credits >= 100 IsAlive true

[action_special]
triggerCondition: Or UnitCount plasmaTank >= 5 TeamHas enemy unit

[action_death]
autoTriggerOnEvent: UnitDeath TargetIs experimentalSpider

# 复杂条件
condition: And 
  CompareGreaterThanNumbers (GetDistance Self Nearest) 200 
  HasResources energy >= 50 
  Not (IsAttacking true)
```

---

## 三、服务器插件系统 (RW-HPS)

### 3.1 插件架构

```
Plugin (抽象基类):
  生命周期钩子:
    onEnable()                  — 插件启用 (最先执行, -1)
    init()                      — 所有插件注册后 (-5)
    onDisable()                 — 服务器退出 (-6)
    
  命令注册:
    registerCoreCommands(handler)       — ★ 全局命令
    registerServerCommands(handler)     — 服务器协议命令
    registerRelayCommands(handler)      — Relay协议命令
    registerServerClientCommands(handler) — 客户端命令
    registerRelayClientCommands(handler)  — Relay客户端命令
    
  事件注册:
    registerEvents(eventManage)         — ★ Hess事件
    registerGlobalEvents(eventManage)   — ★ 全局事件
    
  资源:
    loadLang(lang, ini)                — 语言注入
    
PluginLoadData:
  name: String                — 插件名称
  description: String         — 描述
  author: String              — 作者
  version: String             — 版本
  supportedVersions: String   — 支持的服务端版本
  internalName: String        — 内部ID
```

### 3.2 内置插件

```kotlin
// LoadCoreCustomPlugin.kt
[Core Plugin]
  HessMain              — ★ 无头服务端核心 (headless Rusted Warfare)
  
[Core Plugin Extend]
  UpListMain            — 服务器列表上报
  ConnectLimit          — ★ 连接频率限制 (1min/10次, 24h/400次BAN)
  RW-HPS Web Api        — HTTP API接口
  BindPlayer            — ★ 玩家绑定 (UUID记忆)
  
[Amusement Plugin]
  ClosingBorder         — 边界收缩 (吃鸡模式)
  NeverEndGame          — 永续模式
  
[Example Plugin]
  JavaScriptPluginTest  — JS插件示例
```

### 3.3 插件事件系统

```
全局事件 (Global):
  ServerLoadEvent             — 服务器加载
  ServerStartTypeEvent        — 服务器启动类型
  NetConnectNewEvent          — ★ 新连接建立
  NetConnectCloseEvent        — 连接关闭
  ServerConsolePrintEvent     — 控制台输出

Hess事件 (Hess/房间级):
  ServerGameStartEvent        — 游戏开始
  ServerGameOverEvent         — 游戏结束
  ServerHessStartPort         — ★ Hess端口启动
  PlayerJoinEvent             — 玩家加入
  PlayerLeaveEvent            — 玩家离开
  PlayerChatEvent             — 玩家聊天
  PlayerBanEvent              — 封禁玩家
  PlayerUnBanEvent            — 解封
  PlayerIpBanEvent            — IP封禁
  PlayerIpUnBanEvent          — IP解封
  PlayerOperationFactoryBuildUnitEvent — 工厂建造
```

### 3.4 插件示例: 连接限制

```kotlin
class ConnectLimit: Plugin() {
    override fun registerGlobalEvents(eventManage: EventGlobalManage) {
        eventManage.registerListener(FuckEvent())
    }
    
    private class FuckEvent: EventListenerHost {
        // IP: 60秒最多10次连接
        private val limit = ObjectMap<String, TimeAndNumberX>()
        
        @EventListenerHandler
        fun limitConnect(event: NetConnectNewEvent) {
            if (event.ip == "127.0.0.1" || 
                event.ip.startsWith("192.168.")) return
            
            val checker = limit[event.ip, { TimeAndNumberX(60, 10) }]
            
            // 24小时内连接超400次 → BAN
            if (!checker.countLimit.checkStatus()) {
                sendKick("[CMM-D]")
                bannedIP24.add(event.ipLong24)
            }
            
            // 1分钟内连接超10次 → Kick
            if (!checker.checkStatus()) {
                sendKick("[CMM-M]")
            }
        }
    }
}
```

### 3.5 JavaScript插件支持

```
JavaScriptPlugin (RW-HPS):
  引擎: Rhino/Nashorn (JVM内置)
  接口: 与Kotlin插件相同的生命周期钩子
  用途: 快速脚本/热重载

示例:
  // myscript.js
  function onEnable() {
      print("JS Plugin loaded!");
  }
  
  function registerServerCommands(handler) {
      handler.register("js-test", "a JS test command") { args, log ->
          log("Hello from JavaScript! Args: " + args.join(", "));
      }
  }
```

---

## 四、Mod混淆系统 (rwTool)

### 4.1 混淆目标

```
rwmodProtect — 保护Mod知识产权:
  
  1. 封装加固:
     - 对.ini文件内容加密/编码
     - 对图像文件水印/加密
     - 防止文本编辑器直接查看
     
  2. 文件树重构:
     - 扁平化目录结构
     - 随机化文件名 (特殊Unicode字符)
     - 移除copyFrom路径关系
     
  3. 代码精简:
     - 移除注释
     - 移除无用.ini段
     - 展开${section.key}引用
     - 展开all-units.template
     - 最大ZIP压缩
     
  4. 静态衍射:
     - 展开多态引用
     - 内联继承链
     - 移除虚引用
```

### 4.2 兼容性问题

```
已知限制:
  ✗ 已加壳的文件 → 不支持重复加密
  ✗ 循环引用 → 卡死
  ✗ ${section.key}访问变更内容 → 异常
  ✗ all-units.template → 部分不兼容
  ✗ @copyFrom_skipThisSection=false/0 → 移除兼容
  ✗ 虚引用 → 额外文件被打包
  ✗ 错误数学表达式 → 栈溢出
  ✗ 路径分隔符不规范 → 错误
  ✗ 重排序 → rwsave兼容问题
  ✗ dont_load → 错误 (如果被引用)
```

### 4.3 ZIP工具

```
zippack / zipunpack:
  - 封包: 将文件夹伪装为普通文件
  - 拆包: 还原伪装
  - 配置项:
    head: ZIP文件头配置
    end: Zip64尾部伪装
    split: 路径分割 (NN格式, 0-9)
    safe: 安全模式 (避免损坏)
    raw: 原始流模式 (不解压, 更快)
    lib: 启用内置依赖
```

---

## 五、单位类型注册系统

### 5.1 内置单位 (ar, 20KB)

```
UnitTypeRegistry (ar):
  ~53个内置单位类型枚举 (8个tier)
  
  tier 0: commandCenter (CC)
  tier 1: extractorT1, fabricatorT1, builder, scout...
  tier 2: extractorT2, fabricatorT2, mechGun, plasmaTank...
  tier 3: extractorT3, fabricatorT3, battleShip...
  tier 4: mechLaser, heavyBattleship...
  tier 5: mechFlame, experimentalSpider...
  tier 6: experimentalGunship, modularSpider...
  tier 7: experimentalDropship, experimentalCarrier...
  
  每个枚举项包含:
    - 内部名称 (如 "extractorT1")
    - 显示名称 (如 "Extractor T1")
    - .ini文件路径
    - 默认属性 (价格/HP/速度/伤害)
```

### 5.2 服务器外部单位列表 (111个枚举)

```kotlin
// GameExternalUnits.kt — 所有已知单位名称
标准单位 (约40个):
  extractorT1/T2/T3, fabricatorT1/T2/T3
  mechGun/Missile/Artillery/Flame/Laser/Lightning/Minigun
  plasmaTank, mammothTank, laserTank, heavyTank, hoverTank
  heavyInterceptor, lightGunship, bomber, spyDrone
  battleShip, heavyBattleship, lightSub, attackSubmarine
  antiAirTurretFlak, outpost, laboratory, repairbay
  combatEngineer, mechEngineer, scout
  
实验单位 (8个):
  experimentalSpider/Gunship/Dropship/Carrier/Tank
  experimentalGunshipLanded, experimentalHoverTank
  
模块蜘蛛 (16个):
  modularSpider + _antiair/_antiairFlak/_antiairT2
  + _antinuke/_artillery/_fabricator/_fabricatorT2
  + _gunturret/_gunturretT2/_laserdefense/_lightning
  + _shieldGen/_smallgunturret/_smallgunturretT2
  
虫子单位 (17个):
  bugExtractor/T2, bugGenerator/T2, bugGeneratorN/NT2
  bugMelee/Large/Small/T31, bugRanged/T2, bugBee, bugFly
  bugSpore, bugTurret, bugPickup, bugWasp, bugNest
  
特殊单位:
  nautilusSubmarine/Land/Surface, robotCrab/Water
  missileAirship, heavyMissileShip, heavySub
  creditsCrates, crystal_mid
```

### 5.3 工厂可建造单位 (87个枚举)

```kotlin
// GameFactoryBuildUnits.kt
地面工厂:
  u_combatEngineer, u_builder, u_scout
  u_plasmaTank, u_heavyTank, u_mammothTank
  u_missileTank, u_laserTank, u_hoverTank
  u_heavyArtillery, u_experimentalTank
  
机甲工厂:
  u_mechGun, u_mechMissile, u_mechArtillery
  u_mechLaser, u_mechLightning, u_mechMinigun
  u_mechFlame, u_mechHeavyMissile, u_mechBunker, u_mechEngineer
  
空军工厂:
  u_lightGunship, u_interceptor, u_heavyInterceptor
  u_bomber, u_spyDrone, u_dropship, u_gunShip
  u_missileAirship, u_amphibiousJet, u_helicopter
  u_fireBee, u_experimentalDropship, u_experimentalGunship
  
海军工厂:
  u_builderShip, u_gunBoat, u_lightSub
  u_battleShip, u_heavyBattleship, u_attackSubmarine
  u_missileShip, u_heavyAAShip, u_heavySub
  u_hovercraft, u_heavyMissileShip
  u_nautilusSubmarine, u_experimentalCarrier
  
建筑:
  c_turret_t2_gun, c_turret_t3_gun
  c_turret_t1_artillery, c_turret_t2_flame
  c_turret_t1_lightning
  c_antiAirTurretT2, c_antiAirTurretT3
  antiAirTurretFlak, mechFactoryT2
  
特殊:
  _0 (核弹生产), _1 (核弹发射)
```

---

*参考: game-lib.jar ag(116KB) + fw.i(3类) + custom.*(89子包) + RW-HPS Plugin/PluginManage + rwTool rwmodProtect*
*最后更新: 2026-06-07*
