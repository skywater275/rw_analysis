# Rusted Warfare v1.15 — 存档系统与设置引擎源码逆向
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> .rwsave 双重格式(二进制+文本)、14步序列化、SettingsEngine 100+参数、preferences.ini
>
> 关键文件: `GameSaver.java`(972行), `SettingsEngine.java`(698行), `KeyBindings.java`(319行)

---

## 1. .rwsave 文件格式

### 1.1 双重格式

每个 `.rwsave` 文件同时包含两种格式:
```
[二进制数据块]     ← DataOutputStream → OutputNetStream
[纯文本数据块]     ← PrintStream → aw (调试诊断)
```

> 纯文本部分是二进制数据的冗余重复，用于人类可读和调试。

### 1.2 写入流程 (GameSaver.b(), line 68)

```
1. 创建临时文件: saves/<name>.rwsave.tmp
2. 二进制写入: OutputNetStream (DataOutputStream)
3. 纯文本写入: aw (PrintStream)
4. 原子重命名: .tmp → .rwsave
```

### 1.3 文件头

```
"rustedWarfareSave"     ← 魔数 (区别于 "rustedWarfareReplay")
version (int)           ← 96 (当前格式版本)
saveType (int)          ← 保存类型
gameFrame (long)        ← 当前游戏帧
```

---

## 2. 序列化顺序 (14步)

```
GameSaver.a(OutputNetStream):              [line 166]
│
├──  1. 版本信息
│       "rustedWarfareSave" + version(96) + saveType + gameFrame
│
├──  2. 自定义单位
│       "saveCompression" 块
│       "customUnitsBlock" 块 — 所有已注册的自定义单位定义
│
├──  3. 游戏设置
│       "gameSetup" 块 — bX (NetEngine) 游戏规则
│
├──  4. 地图引用
│       dl: 地图路径 ("maps/normal/l010;...tmx")
│       布尔: 远程地图流?
│       如果是: 原始地图流数据
│
├──  5. 设置数组
│       by (地图宽度)
│       cy+cI (地图高度偏移)
│       cz+cJ (地图高度偏移)
│       cV (地图比例)
│       bV.a (下一个单位ID)
│       int 0 (填充)
│       writeShort(12345) (结束标记)
│
├──  6. 地形
│       bL.a(as2) — MapEngine 序列化
│       bL.E/F/G — 地图属性
│
├──  7. 任务引擎
│       可选: ce (MissionEngine) 序列化
│
├──  8. 队伍
│       bs.k (当前玩家槽位)
│       n.c (队伍总数)
│       每队: 布尔标志 (AI/网络/空)
│
├──  9. 单位外壳
│       "Section: unit shells"
│       单位总数
│       每个单位: 类型鉴别器 (1=ar枚举/2=基础/3=自定义)
│                  + 特定类型的实体数据
│
├── 10. 单位ID
│       "Section: CurrentUnitId"
│       下一个可用单位ID
│
├── 11. 额外管理器
│       bV.a(k2) — 单位ID管理器
│       bS.a(k2) — 选择/UI管理器
│       bY.a(k2) — 团队升级管理器
│
├── 12. 详细队伍数据 (第二轮)
│
├── 13. 单位完整数据
│       每个单位的 a(k2) — 位置/HP/状态/所有者...
│
└── 14. 结束
        "saveCompression" 结束
        "<SAVE END>"
```

---

## 3. 加载流程 (GameSaver.a(k, boolean...), line 365)

```
1. 头部验证:
   ├── 读取字符串
   ├── != "rustedWarfareSave" → 拒绝
   └── == "rustedWarfareReplay" → 特定错误

2. 版本检查:
   ├── version > 96 → 拒绝 ("用更新的游戏制作")
   └── ≤ 96 → 向前兼容处理

3. 自定义单位: version≥54 → 读取可选块

4. 游戏设置: version≥56 → 应用地图/玩家规则

5. 地图加载: 读取路径 → l2.a(true, ...) 加载

6. 设置: 读取地图尺寸/比例/下一个单位ID

7. 地形: 读取地形数据

8. 队伍重建:
   ├── 读取队伍 → 创建 AI/网络/本地玩家
   └── ID重映射 (多人重同步)

9. 单位创建:
   ├── 读取所有外壳 (类型鉴别+数据)
   └── 创建对象, 设置正确 eh (实体ID)

10. 单位反序列化: 读取完整状态

11. 后处理:
    ├── 检查ID重叠并修复
    ├── 清除已死亡实体 (bV标志)
    ├── 修复地图代价
    ├── 重建单位缓存
    └── 重新初始化AI

12. 失败清理: 清除所有单位
```

---

## 4. SettingsEngine (698行)

### 4.1 存储

```
Android: SharedPreferences
Desktop: preferences.ini ← INI 格式
路径: /SD/rustedWarfare/preferences.ini
```

### 4.2 全部字段及默认值

**音频**:
| 字段 | 默认 | 说明 |
|------|------|------|
| enableSounds | true | 启用音效 |
| masterVolume | 桌面1.0/移动0.5 | 主音量 |
| gameVolume | 1.0 | 游戏音量 |
| interfaceVolume | 0.8 | 界面音量 |
| musicVolume | 0.25 | 音乐音量 |

**渲染**:
| 字段 | 默认 | 说明 |
|------|------|------|
| renderBackground | true | 渲染背景 |
| renderFancyWater | false | 精美水面 |
| renderClouds | 移动true/桌面false | 云层 |
| softFogFading | 移动true/桌面false | 软迷雾过渡 |
| renderAntiAlias | true | 抗锯齿 |
| highGraphics | true | 高画质 |
| shaderEffects | false | Shader后处理 |
| renderVsync | false | 垂直同步 |
| highRefreshRate | 移动true/桌面false | 高刷新率 |

**输入/控制**:
| 字段 | 默认 | 说明 |
|------|------|------|
| scrollSpeed | 1.0 | 滚动速度 |
| edgeScrollSpeed | 1.0 | 边缘滚动速度 |
| onscreenControls | true | 屏幕控制 |
| mouseOrders | 1 | 鼠标指令模式 |
| mousePlacement | 1 | 鼠标放置模式 |
| smartSelection_v2 | true | 智能选择v2 |
| doubleClickToAttackMove | true | 双击攻击移动 |
| showZoomButton | true | 显示缩放按钮 |

**UI**:
| 字段 | 默认 | 说明 |
|------|------|------|
| showHp | true | 显示血条 |
| showHpChanges | true | 显示HP变化 |
| showUnitIcons | true | 显示单位图标 |
| showFps | false | 显示FPS |
| useMinimapAllyColors | true | 小地图友军色 |

**游戏**:
| 字段 | 默认 | 说明 |
|------|------|------|
| aiDifficulty | 0 | AI难度 |
| teamUnitCapSinglePlayer | 1000 | 单机单位上限 |
| teamUnitCapHostedGame | 250 | 多人单位上限 |
| autosaving | true | 自动保存 |

**网络**:
| 字段 | 默认 | 说明 |
|------|------|------|
| networkPort | 5123 | 网络端口 |
| udpInMultiplayer | false | 多人UDP |
| banTimeInSecondsAfterKick | 60 | 踢出后封禁时间 |
| saveMultiplayerReplays | 移动true/桌面false | 保存多人回放 |

**队伍颜色**:
```
teamColors: "#00ff00,#d02013,#0463f3,#ffff40,#00ffff,#d0f8f7,#000000,#ff00ea,#ff7f18,#9368c4"
teamColorsNames: "GREEN,RED,BLUE,YELLOW,CYAN,WHITE,BLACK,PINK,ORANGE,PURPLE"
```

---

## 5. preferences.ini 格式

```ini
[settings]
enableSounds:true
masterVolume:1.0
scrollSpeed:1.0
showHp:true
...
key.camera_up:W
key.camera_down:S
key.save_game:CTRL+S

[keys]
camera_up:W
camera_down:S
save_game:CTRL+S
select_whole_army:CTRL+A
attack_move:A
stop:S
guard:G
patrol:P
```

### 5.1 按键绑定序列化

```
格式: "CTRL+A", "SHIFT+SPACE"
修饰键: bit 0=Ctrl, bit 1=Shift, bit 2=Alt
未修改: "DEFAULT"
显式清除: "CLEARED"
多个键: 逗号分隔
```

### 5.2 默认按键绑定

| 操作 | 默认键 |
|------|--------|
| 镜头上下左右 | UP/DOWN/LEFT/RIGHT + NUMPAD |
| 保存 | CTRL+S |
| 聊天 | ENTER |
| 队伍聊天 | SHIFT+ENTER |
| Ping地图 | CTRL+M / CTRL+P |
| 菜单 | ESCAPE / F10 |
| 取消选择 | SPACE |
| 选择全军 | CTRL+A |
| 选择指挥中心 | CTRL+C |
| 攻击移动 | A |
| 停止 | S |
| 守卫 | G |
| 巡逻 | P |
| 升级 | U |
| 集结點 | R |
| 暂停 | BREAK |
| 减速/加速 | minus/equals |

---

## 6. 自动保存

```
触发: 每 300,000ms (5分钟)
文件: saves/autosave.rwsave
实现: GameSaver.b("autosave", true) [line 951]
```

---

## 7. 存档版本兼容性

```
当前版本: 96
向前兼容: version ≤ 96
拒绝: version > 96 → "是用更新的游戏制作的"

条件读取:
├── version ≥ 54 → 自定义单位块
├── version ≥ 56 → 游戏设置块
├── version ≥ 49 → 压缩队伍数组
└── version ≥ 3 → 单位ID修复
```
