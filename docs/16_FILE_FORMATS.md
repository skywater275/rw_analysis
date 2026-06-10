# 16 — 游戏文件格式完全解析

> 来源: rwTool (savedump.java, zipReader.java, section.java) + game-lib.jar (j.k/j.as) + RW-HPS (CompressionUtils)
> 验证: 30个回放 + 200+ .ini文件

---

## 一、回放文件 (.replay)

### 1.1 完整二进制结构

```
Offset  Size    Field               Value/Description
──────  ────    ─────               ────────────────
0x0000  2B      magic_len           BE uint16 = 19
0x0002  19B     magic               "rustedWarfareReplay"
0x0015  4B      header_int1         BE int32 = 176 (SettingsEngine.settingsGameVersion)
0x0019  4B      header_int2         BE int32 = 96
0x001D  2B      version_len         4
0x001F  4B      version_str         "1.15"
0x0023  2B      null_pad            0x0000
0x0025  1B      tlv_tag            0x08
0x0026  2B      tlv_len            BE uint16 (variable)
0x0028  N       tlv_value          "gamesave..."
──────  ────    ─────               ────────────────
[TLV嵌套链]:
  "gamesave" → "rustedWarfareSave" → "saveCompression" → [GZIP数据]

GZIP解压后内容:
  1. "customUnitsBlock" — 自定义单位类型定义
  2. "gameSetup"         — 游戏设置 (45+字节头部)
  3. TMX XML             — 地图瓦片数据
  4. Map Params          — 85字节地图参数
  5. Player Records      — 每人15+name_len字节
  6. Fog Blocks          — 每人 w*h+116字节
  7. "stats" TLV         — 初始对战统计
  8. 指令流              — j.as块序列 (rc/cs/chat/end)
```

### 1.2 GameSetup 头部 (45字节)

```
Offset  Size  Field               Type
──────  ────  ─────               ────
+0      4B    version_flag        uint32 BE
+4      4B    credit_multiplier   float32 BE
+8      4B    flags               uint32 BE
+12     4B    settings_bitfield   uint32 BE
+16     4B    data                uint32 BE
+20     4B    value               float32 BE
+22     4B    ★ game_speed        float32 BE  ← 默认2.5
+26     2B    unit_cap            uint16 BE
+28     2B    unit_cap_2          uint16 BE
+30     4B    more_flags          uint32 BE
```

### 1.3 玩家记录 (快照中)

```
每人格式:
  [4B: credits (BE float32)]     ← 起始资金, 通常4000
  [4B: team_id (BE int32)]       ← 队伍ID (0=A, 1=B...)
  [1B: 0x01]                     ← 标记字节
  [2B: name_len (BE uint16)]     ← 名字长度
  [N: name (CESU-8)]             ← 玩家名称
  
快照只包含有名字的玩家 (player.v != null)
空槽位和AI不包含在快照中
```

### 1.4 迷雾块

```
每个玩家独立的迷雾块:
  [4B: marker 0xFF 0xFF 0xD8 0xF1]    ← 固定标记
  [20B: header]                         ← 玩家索引/版本/标志
    +20: [2B: width]                    ← 迷雾宽度(瓦片)
    +24: [2B: height]                   ← 迷雾高度(瓦片)
    +28: [4B: extra]                    ← 额外数据
  [w*h bytes: fog values]              ← 迷雾值(0=不可见,1-4=探索,5=可见)
  [80B: tail]                           ← 尾部数据
```

### 1.5 指令块序列

```
每个块: [2B: name_len] [name_UTF8] [4B: data_size] [data_bytes]

块类型:
  'rc'    — 玩家指令 (建造/移动/攻击)
  'chat'  — 聊天消息
  'cs'    — 同步检查
  'es'    — 事件
  'wait'  — 等待
  'end'   — 块结束标记
```

---

## 二、存档文件 (.rwsave)

### 2.1 文件结构

```
.rwsave:
  ┌─ "rustedWarfareSave" (writeUTF)
  ├─ 4B header1 (BE int32)
  ├─ 4B header2 (BE int32)
  ├─ 1B boolean
  ├─ writeUTF (block name)
  └─ "saveCompression" block
      ├─ 4B gzip_size
      └─ GZIP compressed:
          ├─ "customUnitsBlock"
          │   ├─ "customUnits"
          │   └─ [mod单位定义列表]
          ├─ "gameSetup" (45B头部)
          ├─ Map TMX (String + boolean + byte[])
          ├─ Player Records
          ├─ Fog Blocks
          └─ "stats" TLV
```

### 2.2 GameSaver 保存流程

```java
// gameFramework.y (GameSaver, 27KB)
b(String path, boolean writeTmx):
  1. 创建 .tmp 临时文件
  2. 序列化游戏状态 (j.as OutputNetStream)
  3. 压缩 (GZIP)
  4. 重命名 .tmp → .rwsave
  5. 支持SD卡存储
  
// 保存时调用:
// l.ca.b() → GameSaver.save()
// l.ca.a(j.as) → 序列化
```

---

## 三、Mod文件 (.ini)

### 3.1 基本结构

```ini
[core]
name: UnitName
displayName: Unit Display Name
price: 1000
hp: 500
speed: 0.8
range: 150
damage: 25
mass: 10
radius: 30
buildSpeed: 0.03
maxHp: 500
isBuilding: false
isAir: false
canAttack: true

[action_build_mechGun]
type: build
target: mechGunType
buildTime: 17.0
resource: credits=600
...

[attack]
damage: 25
range: 150
cooldown: 60
ammo: 1
projectile: defaultBullet
...

[movement]
type: LAND
maxSpeed: 0.8
acceleration: 0.1
turnSpeed: 120
...

[ai]
priority: 5
weight: 1.0
buildZone: true
attackZone: true
...

[graphics]
image: unit.png
size: 64
animations: idle=4, move=4, attack=8
...
```

### 3.2 继承系统

```ini
# copyFrom: 继承另一个.ini的所有配置
[core]
copyFrom: units/mechBase.ini

# @copyFrom_skipThisSection: 控制继承行为
#   false/0/IGNORE → 丢弃之前继承的该节所有键
#   true/非0 → 保留之前继承的键

# all-units.template: 统一模板(有兼容性问题, 不推荐)
```

### 3.3 动作段 ([action_*])

```ini
[action_attack]
type: attack
target: enemyUnits
damage: 25
range: 150
cooldown: 60
projectile: defaultBullet
triggerCondition: HasResources credits >= 0

[action_build_extractorT1]
type: build
target: extractorT1
resource: credits=700
buildTime: 33.0
condition: HasResources credits >= 700
```

### 3.4 LogicBoolean 条件语法

```ini
# 在.ini中的条件表达式
triggerCondition: And HasResources credits >= 100 UnitCount plasmaTank < 5
autoTrigger: Or IsAttacking IsMoving
autoTriggerOnEvent: UnitDeath TargetIs enemy
```

---

## 四、地图文件 (.tmx)

### 4.1 TMX XML 结构

```xml
<?xml version="1.0" encoding="UTF-8"?>
<map version="1.0" orientation="orthogonal"
     width="120" height="120"           ← 瓦片数
     tilewidth="20" tileheight="20">    ← 每瓦片20px
  <tileset firstgid="1" source="units.tsx"/>
  <tileset firstgid="..." source="terrain.tsx"/>
  
  <!-- 5个瓦片层 -->
  <layer name="ground">      ← 0: 地面层
  <layer name="biome">       ← 1: 生物群系
  <layer name="decoration">  ← 2: 装饰层
  <layer name="resource">    ← 3: 资源层
  <layer name="overlay">     ← 4: 覆盖层
  
  <!-- 对象组 -->
  <objectgroup name="resources">
    <object name="resourcePool" x="500" y="600" .../>
  </objectgroup>
  <objectgroup name="units">
    <object name="spawnPoint" x="400" y="400" .../>
  </objectgroup>
</map>
```

### 4.2 地图文件路径格式

```
格式: maps/skirmish/[flags]mapname.tmx

标志:
  [z]        — 标准地图
  [z;p10]    — 10玩家
  [p6]       — 6玩家

示例:
  maps/skirmish/[z;p10]Crossing Large (10p).tmx
  maps/skirmish/Valley Arena (10p) [by_uber]@[z;p10].tmx
```

### 4.3 压缩格式

```
TMX内的瓦片数据通常使用:
  - Base64编码
  - GZIP压缩
  - "only gzip base64 is supported" — 游戏引擎提示

地图精简器(rwmapOpt, 基于RwMapCompressor)提供:
  - 无损压缩
  - v1模板: 最强压缩比
```

---

## 五、资源包 (ZIP)

### 5.1 游戏资源包结构

```
rusted_warfare.jar (or .apk):
├── assets/
│   ├── units/          — 内置单位.ini定义
│   ├── maps/           — 内置地图.tmx
│   │   └── skirmish/   — 对战地图
│   ├── graphics/       — 单位/地形图像
│   ├── sounds/         — 音效/音乐
│   └── ui/             — UI资源 (LibRocket RML)
├── mods/               — Mod目录
│   └── <modname>/
│       ├── mod-info.txt
│       └── units/      — 自定义单位
├── saves/              — 存档目录
└── replays/            — 回放目录
```

### 5.2 ZIP伪装 (rwTool功能)

```
封包 (zippack/zipunpack):
  - 伪造文件夹: 将文件条目名修改为目录名
  - 重构文件树: 扁平化+特殊字符干扰
  - 规避常规文件浏览器

配置:
  - head: ZIP文件头配置("最小大小,随机大小,...")
  - end: 伪装Zip64尾部
  - split: 路径分割("最大分割数-1,最大分割长度-1")
  - safe: 安全解包模式
```

---

## 六、命名块序列化协议

### 6.1 二进制布局

```
Block:
  [name_length: 2B big-endian]
  [name_bytes: UTF-8]
  [data_length: 4B big-endian]
  [data_bytes: variable]

协议限制:
  - 最大嵌套深度: 11层
  - 同步标记值: 12345
  - 最大对象ID: 999999
  - 序列化版本: 26 (UnitInstance)
```

### 6.2 InputNetStream (j.k) — 反序列化

```java
// 关键方法
a(String, bool, bool) → void     // startBlock — 开始读取命名块
d(String) → void                 // endBlock — 结束块, 验证名称
c(String) → byte[]               // getBlockRaw — 获取块原始字节
a(bool, bool) → String           // startBlockAndGetName
a(String) → void                 // readMark — 读取同步标记(12345)

// 类型化读取
d() → byte       e() → boolean   f() → int
g() → float      h() → double    i() → long
j() → String     q() → as        // readUnitType (167B)
r() → game.n     // readPlayer (51B)
o() → am         // readUnit
```

### 6.3 OutputNetStream (j.as) — 序列化

```java
// 关键方法
a(String) → void       // startBlock
b(String) → void       // endBlock
a(String, bool) → void // writeMark

// 类型化写入
a(byte)  a(int)  a(float)  a(double)  a(long)
a(String)  a(boolean)  a(as)  a(PointF)  a(game.n)
```

---

## 七、Mod混淆格式 (rwmodProtect)

### 7.1 混淆层次

```
1. 封装加固:
   - 对文件内容进行加密/编码
   - 不支持已加壳的文件

2. 重构文件树:
   - 移除明显路径关系
   - 使用特殊Unicode字符干扰阅读
   - 路径分隔符严格规范 (/和\\不能作头/尾/连续)

3. 精简文件:
   - 移除无用.ini段
   - 移除注释
   - 最大ZIP压缩
   - 保留copyFrom重复区域代码

4. 动态引用展开:
   - ${section.key} → 直接替换为值
   - all-units.template → 展开到每个引用文件
   - 多态暴力衍射 (可能引起兼容性问题)
```

### 7.2 兼容性注意事项

```
已知问题:
  - 循环引用会导致卡死
  - ${section.key}访问变更内容有问题
  - all-units.template优先于copyFrom
  - 非法符号与混淆字符冲突
  - @copyFrom_skipThisSection不支持false/0/IGNORE
  - 虚引用导致未使用资源被打包
  - 错误数学表达式导致栈溢出
  - rwsave兼容性问题 (重排序所致)
```

---

## 八、数据工具 (dataUtil)

### 8.1 UnitUntil.java — 反射字段映射

此文件通过直接引用混淆后的游戏类提供字段访问:

```java
// 单位字段映射 (版本可能不同)
unitnode.cw → HP当前值      (am.cu in v1.15)
unitnode.cx → HP最大值      (am.cv in v1.15)
unitnode.cz → 护盾当前值    (am.cx in v1.15)
unitnode.cC → 护盾最大值    (am.maxShield)
unitnode.cD → 能量值        (am.cB)
unitnode.co → 建造进度      (am.cm)
unitnode.ci → 朝向角度      (am.cg)
unitnode.ce → X速度         (am.velocityX)
unitnode.cf → Y速度         (am.velocityY)
unitnode.cG → 弹药数        (am.cE)
unitnode.cQ → 父单位引用    (am.cN)
unitnode.ej → 单位ID(long)  (am.uid)
unitnode.eq → X坐标         (w.eo, 父类GameObject)
unitnode.er → Y坐标         (w.ep, 父类GameObject)
unitnode.es → 高度/Z轴      (am.eh)
unitnode.bZ → 所属玩家      (am.bX)

// 玩家字段映射
player.l → 队伍ID           (n.r)
player.w → 玩家名称         (n.v)
player.s → 备用队伍字段     (n.s)
player.x → AI标志           (n.w)
player.c → 最大玩家数       (n.c)

// 类名映射 (此版本)
ce     → am  (UnitInstance)
p      → n   (GameState/Player)
cj     → as  (UnitType)
bp     → attachment类型
aj     → gameFramework 类 (对应l?)
ah     → gameFramework 基类 (对应fw.w?)
```

---

*参考: rwTool/savedump.java + game-lib.jar j.k/j.as + RW-HPS CompressionUtils*
*最后更新: 2026-06-07*
