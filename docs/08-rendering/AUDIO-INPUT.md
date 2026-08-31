# Rusted Warfare v1.15 — 音频与输入系统源码逆向
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


> 三层音频架构(Java→OpenAL→SoundEngine), 输入→Command完整链路
>
> 关键文件: `a/e.java`(SoundEngine,216行), `KeyBindings.java`(319行), `gameFramework/f/a.java`(UI交互)

---

## Part A: 音频系统

### 1. 三层音频架构

```
[第3层] SoundEngine (gameFramework/a/e.java, 216行)
   ├── 游戏框架API: play音效/音乐/UI音
   └── 音量路由: masterVolume × (gameVolume|interfaceVolume) × soundDefault

[第2层] OpenAL 实现 (java/audio/lwjgl/, 16文件)
   ├── OpenALAudio — AL.create(), 源池管理
   ├── OpenALSound — PCM→AL缓冲区→播放
   ├── OpenALMusic — 流式音乐(后台线程)
   └── 编解码: OGG Vorbis + WAV

[第1层] Java音频接口 (java/audio/, 5接口)
   ├── Audio — 工厂: newSound(), newMusic()
   ├── Sound — 音效: play/loop/stop/volume/pan/pitch
   ├── Music — 音乐流: play/pause/position/looping
   └── AudioDevice / AudioRecorder
```

### 2. SoundEngine 音效注册表 (第78-109行)

```java
// 26个内置音效
attack       → R.raw.attack       // 攻击1
attack2      → R.raw.attack2      // 攻击2
move         → R.raw.move          // 移动
click        → R.raw.click         // UI点击
click_add    → R.raw.click_add     // 添加到选择
click_remove → R.raw.click_remove  // 从选择移除
warning      → R.raw.warning       // 警告
message      → R.raw.message       // 消息
missile_fire → R.raw.missile_fire  // 导弹发射
missile_hit  → R.raw.missile_hit   // 导弹命中
unit_explode → R.raw.unit_explode  // 单位爆炸
buiding_explode → R.raw.buiding_explode  // 建筑爆炸
tank_firing  → R.raw.tank_firing   // 坦克开火
cannon_firing → R.raw.cannon_firing // 火炮开火
gun_fire     → R.raw.gun_fire      // 枪声
firing3      → R.raw.firing3       // 武器3
firing4      → R.raw.firing4       // 武器4
large_gun_fire1 → ...              // 大型武器1
large_gun_fire2 → ...              // 大型武器2
bug_die      → R.raw.bug_die       // 虫族死亡
bug_attack   → R.raw.bug_attack    // 虫族攻击
interface_error → ...              // 界面错误
nuke_explode → R.raw.nuke_explode  // 核爆
nuke_launch  → R.raw.nuke_launch   // 核弹发射
laser_deflect → R.raw.laser_deflect // 激光偏转1
laser_deflect2 → ...              // 激光偏转2
```

### 3. 3D空间音效 (e.java)

```java
// 空间音效: 带距离衰减和屏幕外消音
a(i sound, float volume, float pitch, float x, float y)
  → 距离衰减 = f(相机距离, sound.d)
  → 屏幕外? → 额外消音

// UI音效: 忽略游戏音量
b(i sound, float volume)
  → 实际音量 = masterVolume * interfaceVolume * sound.d

// 游戏音效: 
c(i sound, float volume)
  → 实际音量 = masterVolume * gameVolume * sound.d
```

### 4. 全局引用

```java
l2.bM  → SoundEngine (全局单例, GlobalState.bM)
l2.bN  → MusicController (背景音乐)
```

---

## Part B: 输入系统

### 5. KeyBindings (319行, 50+绑定)

**摄像机**:
| 绑定 | 键 |
|------|-----|
| Camera Up/Down/Left/Right | 方向键 + NUMPAD8/2/4/6 |
| Zoom In/Out | minus/equals + NUMPAD加减 |

**选择**:
| 绑定 | 键 |
|------|-----|
| Select Whole Army | CTRL+A |
| Select Command Center | CTRL+C |
| Cycle Builders | CTRL+B |
| Cycle Extractors | CTRL+E |
| Cycle Factories (Land) | CTRL+L |
| Cycle Factories (Air) | CTRL+K |
| Deselect | SPACE |

**单位动作**:
| 绑定 | 键 |
|------|-----|
| Attack Move | A |
| Stop | S |
| Guard | G |
| Patrol | P |
| Upgrade | U |
| Set Rally | R |

**编组**:
| 绑定 | 键 |
|------|-----|
| Create Group 1-10 | CTRL+数字 |
| Select Group 1-10 | 数字 |
| Add to Selection 1-10 | SHIFT+数字 |

**聊天/UI**:
| 绑定 | 键 |
|------|-----|
| Chat | ENTER / T |
| Team Chat | SHIFT+ENTER / Y |
| Ping Map | CTRL+M / CTRL+P |
| Menu | ESCAPE / F10 |
| Pause | BREAK |
| Speed Up/Down | equals/minus |

**手柄**: 左摇杆移动, 右摇杆瞄准, A/RT射击

### 6. 输入→Command 完整链路

```
1. 触摸/鼠标/键盘事件
   ↓
2. gameFramework/f/g.java — 主UI控制器
   ├── I = 触摸按下, H = 保持, T = 拖拽
   ├── J = 在UI元素上, K = 在迷你地图上
   └── 坐标: l2.b(0)/c(0)/d(0)
   ↓
3. gameFramework/f/a.java (UI层)
   ├── 框选: Rect → 遍历 am.bE → 命中测试
   ├── 动作栏: 枚举选中单位的 s(动作) → 渲染网格
   └── 点击检测: a.ac = 选中的动作
   ↓
4. 命令构造
   ├── 创建 e (CommandContainer)
   ├── 设置 WaypointAction:
   │   ├── MOVE:    a(f2,f3)     → 目标坐标
   │   ├── ATTACK:  a(am)        → 目标单位
   │   ├── BUILD:   a(f2,f3,as,n) → 建造类型/位置/阶段
   │   └── REPAIR:  b(am)        → 修理目标
   ├── 设置 specialAction (ActionId)
   └── 设置 attackMode
   ↓
5. CommandController.b(player)
   ├── cmd.l() — 验证
   └── this.b.add(cmd) — 排队
   ↓
6. Command.k() — 下一帧执行
```

---

---

## Part B-2: 音频系统详解 (2026-06-23 新增)

> 关键文件: `gameFramework/a/`(9个类)
> 详见: [AUDIO-HUD.md](AUDIO-HUD.md)

### 音频架构

```
SoundFactory(h) — 抽象工厂 (HashMap声音缓存)
├── AndroidSoundFactory(a) — SoundPool实现 (15槽池, 16路3声道)
│   ├── SoundThread(d) — 音频线程 (BlockingQueue.take()+play+recycle)
│   ├── SoundPlayRequest(c) — 播放请求数据
│   └── SoundInstance(b) — SoundPool播放实例
└── NullSoundFactory(f) — 空工厂 (OOM回退)
    └── NullSound(g) — 静音 (no-op)

SoundRegistry(e) — 26个静态音效引用 (d~F)
```

### 音乐系统

```
MusicFactory(an) — MediaPlayer工厂 (2实例池)
├── MusicPlayer(ap) — 音乐播放 (extends PacketBuilder)
└── MusicFolder(at) — 音乐来源枚举 (3值: 内部/外部/自定义)
```

### 桌面音频

`java/audio/` — Slick2D Music + OpenAL:
- Audio, AudioDevice, Sound, Music
- LWJGL OpenAL绑定 (`java/audio/lwjgl/`)

---

## Part C: 对 RWAgent 的启示

1. **音频无影响** — RWAgent 不需要处理音频，音频系统完全独立
2. **指令注入点**: 跳过输入层，直接在 CommandController.b() 注入 Command
3. **KeyBindings 可读取**: 通过反射获取当前键位配置，用于调试工具
4. **UI 交互可模拟**: 通过 a.java 的 `a.ac` 可直接设置动作选择，无需实际点击
5. **手柄支持**: 游戏支持 Xbox/通用手柄，可用作自动化测试输入源
