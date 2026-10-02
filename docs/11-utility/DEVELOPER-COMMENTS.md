# 开发者原始注释 — 多源提取

> 来源1: `assets/units/` 126个 .ini 文件, 共1268条注释
> 来源2: `crashes.txt` 1075行崩溃日志 — 包含原始方法名
> 来源3: `rw_py/core/game_engine.py` — 字节码验证公式
> 来源4: `RWX-main/` JADX 反编译注释 — 原始字段名
> 
> 这些是游戏开发者 (Luke, corrodinggames) 在单位定义中留下的原始笔记

---

## 1. 游戏机制揭示

### 碰撞/占地
```
#footprint used for collisions with units (left, up, right, down)
#used for placement, and placement of other buildings
#softCollisionOnAll: 3
```

### 移动系统
```
#moveSlidingMode :false        ← 潜艇滑动模式
#moveSlidingMode :true
#moveSlidingDir:  45           ← 滑动方向
#moveIgnoringBody:false        ← 忽略身体朝向
#moveIgnoringBody:true
#startingTurnSpeed:1
#targetTurnSpeed:3
#targetSpeed: 6
#turnSpeedAcceleration: 1.5
```

### 武器/炮塔
```
#left missile pod              ← 炮塔位置标签
#right flak cannon
#back turret
#missile turret
#anti-sub                      ← 反潜武器
#bomb anti-sub
#laserTurret
#=== for the beam effect ===   ← 光束特效配置段
#laserEffect: true
#idleSpin: 0.8                 ← 炮塔空闲旋转
#limitingAngle: 90             ← 炮塔限位角
#turretRotateWithBody: false
```

### 弹道系统
```
#ballistic: true               ← 弹道模式
#ballistic_height: 4           ← 弹道高度
#ballistic_delaymove_height:0  ← 弹道延迟移动高度
#initialUnguidedSpeedHeight: 1.5
#instant:true                  ← 瞬间命中
```

### 建造系统
```
#buildSpeed: 10s
#buildSpeed: 12s
#buildSpeed: 30s
#don't want a speed up from nanoFactorySpeed  ← 不希望受纳米工厂加速
#No need for 'builtFrom' as this replaces the existing builder
```

### 生成/弹丸
```
#spawnProjectilesOnEndOfLife: torpedo_split(offsetDir=90), torpedo_split(offsetDir=-90), torpedo_split(offsetDir=00)
#don't wait for nuke building   ← 反核: 不等核弹建筑
```

### 特效
```
#shoot_flame:small
#shoot_flame:large
#warmupStartEffect:CUSTOM:lightSlowFadeBase
#explodeEffect: CUSTOM:lightSlowFade  ← 自定义爆炸光效
#explodeEffectOnShield: CUSTOM:hitLightFlash  ← 护盾击中闪光
```

## 2. 设计决策/注释

### 平台特定
```
#No confirm needed on mobile     ← 移动端无需确认
```

### 功能开关
```
#Temporarily disable             ← 临时禁用
#canAttack: false                ← 禁用攻击
#infoOnly: true                  ← 仅信息显示
#displayRemainingStockpile: true ← 显示剩余库存
```

### 开发状态
```
#THIS UNIT IS NOT YET USED IN-GAME (but is mostly done)
#test                            ← 测试标记
#Undo from copy                  ← 从副本撤销
#So we don't get 2 copies showing ← 避免重复显示
```

### 性能
```
#note: joinsGroupFormations=false could cause slowdowns for groups of non-flying units
```

### 调试
```
#debugMessage: Updated: moveSpeed = %{ debug( ${newMoveSpeed} ) }
#image: SHARED:debug_marker.png  ← 调试标记贴图
```

### 动作/UI
```
#this also groups actions between units in UI
#section here to control display order  ← 节顺序控制显示顺序
#just in case this action gets triggered (but shouldn't happen with cooldown timer)
```

### 特殊机制
```
#fireTurretXAtGround_onlyOverPassableTileOf: HOVER
#fireTurretXAtGround_withTarget: eventData('blinkTarget', type='unit')
#energyUsage: 1
#landOnGround: onlyIdle
#canNotBeDirectlyAttacked: true
#drawUnderUnits:true
```

---

## 3. 发现的未文档化参数

| 参数 | 用途 | 来源文件 |
|------|------|---------|
| `moveSlidingMode` | 潜艇滑动模式 | light_sub.ini |
| `moveSlidingDir` | 滑动方向角度 | light_sub.ini |
| `moveIgnoringBody` | 忽略身体朝向 | light_sub.ini |
| `targetSpeed` | 目标速度 | heavy_aa_ship.ini |
| `startingTurnSpeed` | 起始转向速度 | heavy_sub.ini |
| `targetTurnSpeed` | 目标转向速度 | heavy_sub.ini |
| `turnSpeedAcceleration` | 转向加速 | mech_lightning.ini |
| `ballistic_height` | 弹道飞行高度 | missile_airship.ini |
| `ballistic_delaymove_height` | 弹道延迟移动高度 | missile_airship.ini |
| `initialUnguidedSpeedHeight` | 初始无制导速度高度 | heavy_artillery.ini |
| `limitingAngle` | 炮塔限位角度 | mech_flame.ini |
| `idleSpin` | 炮塔空闲旋转速度 | turret_t1.ini |
| `warmupStartEffect` | 预热开始特效 | modular_spider/artillery.ini |
| `explodeEffectOnShield` | 护盾被击中时的爆炸特效 | plasma_tank.ini |
| `fireTurretXAtGround_withTarget` | 向地面开火并指定目标 | modular_spider.ini |
| `fireTurretXAtGround_onlyOverPassableTileOf` | 仅对特定移动类型可通行瓦片开火 | modular_spider.ini |
| `spawnProjectilesOnEndOfLife` | 弹丸生命结束时生成新弹丸 | heavy_sub.ini |
| `infoOnly` | 仅信息显示(不可建造) | experimental_carrier.ini |
| `displayRemainingStockpile` | 显示剩余库存 | experimental_carrier.ini |

---

## 4. 调试语法发现

```
%{ debug( ${variableName} ) }  ← 变量插值+调试输出
eventData('blinkTarget', type='unit')  ← 事件数据引用
CUSTOM:lightSlowFade, CUSTOM:hitLightFlash  ← 自定义特效名
SHARED:debug_marker.png  ← 共享资源引用
```

---

## 5. 崩溃日志原始方法名 (crashes.txt, 1075行)

### 从堆栈跟踪提取的原始混淆方法名

```
game-lib.jar 崩溃堆栈中保留的原始方法名:

UI/LibRocket:
  HandleEvent     ← librocket.b.HandleEvent
  loadReplay      ← librocket.scripts.Root.loadReplay
  checkForErrors  ← librocket.scripts.ScriptEngine.checkForErrors
  processArg      ← librocket.scripts.ScriptEngine.processArg
  processFunction ← librocket.scripts.ScriptEngine.processFunction
  processScript   ← librocket.scripts.ScriptEngine.processScript
  runFunction     ← librocket.scripts.ScriptEngine.runFunction

游戏核心:
  gameLoop        ← rts.java.b.gameLoop (游戏主循环)
  closeRequested  ← rts.java.t.closeRequested (关闭请求)
  updateAndRender ← 每帧更新+渲染
  render          ← 渲染方法
  mouseReleased   ← 鼠标事件

对象生命周期:
  <init>          ← 构造函数 (多个类)
  <clinit>        ← 静态初始化块
```

### 堆栈揭示的类关系

```
com.corrodinggames.rts.java.b.gameLoop     ← b = 游戏主循环类
com.corrodinggames.rts.java.t.closeRequested ← t = 窗口关闭处理
com.corrodinggames.rts.java.i.run          ← i = 主线程入口
com.corrodinggames.rts.game.i.a            ← GameScreen 的多个重载方法
com.corrodinggames.rts.game.i.b            ← GameScreen.b (主帧方法)
com.corrodinggames.rts.gameFramework.ba.a  ← ReplayEngine 方法
com.corrodinggames.rts.game.b.b.a          ← MapEngine 方法
```

---

## 6. 字节码验证公式 (rw_py/core/game_engine.py)

### 已验证常量
```
FRAME_RATE = 60.0           ← 游戏帧率
TICK_RATE = 30.0            ← 回放帧率
ECONOMY_WINDOW = 40.0       ← 经济窗口
DEFAULT_SPEED = 2.5         ← 默认游戏速度
STARTING_CREDITS = 4000.0   ← 起始资金
CC_INCOME_CY = 18.0         ← 指挥中心收入贡献
BUILD_MAX_FACTORY = 280.0   ← 工厂最大建造时间
BUILD_MAX_NORMAL = 330.0    ← 普通最大建造时间
BUILD_SPEED_FACTORY = 0.03  ← 工厂建造速度
BUILD_SPEED_NORMAL = 0.10   ← 普通建造速度
INCOMPLETE_DAMAGE_MULT = 1.75 ← 建造中伤害倍率
RECLAIM_REFUND = 0.80       ← 回收退款率
CANCEL_REFUND = 1.00        ← 取消退款率
SHIELD_ABSORB = 0.2         ← 护盾吸收率
PROJECTILE_LIFETIME = 3600.0 ← 弹丸寿命(ticks)
PROJECTILE_SPEED = 80.0     ← 弹丸速度(px/tick)
SPATIAL_GRID_SIZE = 32      ← 空间网格尺寸
SPATIAL_CELL_SIZE = 50.0    ← 网格单元大小(px)
BUILDER_SEARCH_RANGE = 100.0 ← 建造者搜索范围
ATTACK_DETECT_RANGE = 360.0  ← 攻击检测范围
```

### 已验证公式
```
1. 收入: income/s = teamTracker.g × (60/40) × ay.h × gameSpeed
2. 伤害: rawDmg → ×1.75(建造中) → shieldAbsorb(0.2) → HP
3. 建造: progress += buildSpeed × delta_ms
4. 退款: cancel=100%, reclaim=80%
5. 弹丸: speed=80px, lifetime=3600ticks, collision=radius+15
6. 寻路: A* Octile, 8-neighbor, 双向搜索
```

---

## 7. JADX 字段名映射 (RWX-main 社区重构)

### 从 JADX 注释提取的原始字段名

```
android.graphics.Point:
  /* renamed from: a */ → x
  /* renamed from: b */ → y

android.graphics.PointF:
  /* renamed from: a */ → x
  /* renamed from: b */ → y

android.graphics.RectF:
  /* renamed from: a */ → left
  /* renamed from: b */ → top
  /* renamed from: c */ → right
  /* renamed from: d */ → bottom

appFramework 类:
  g → InGameActivity
  c → GameView
  e → ProgressDialog
  d → Handler
  f → running (boolean)
```

---

## 8. 汇总: 所有原始信息来源

| 来源 | 数量 | 内容 |
|------|------|------|
| `assets/units/*.ini` | 1268条注释 | 开发者设计笔记、未文档化参数、调试语法 |
| `crashes.txt` | 1075行 | 原始方法名、类关系 |
| `rw_py/core/` | ~23K行Python | 字节码验证公式、交叉验证常量 |
| `RWX-main/` | ~100文件 | JADX字段名映射 |
| `preferences.ini` | ~50设置 | SettingsEngine 字段默认值 |

**结论**: 原始注释确实存在于游戏分发包中——在 INI 文件、崩溃日志、以及社区重构的 JADX 注释里。核心游戏逻辑的注释已基本恢复。


