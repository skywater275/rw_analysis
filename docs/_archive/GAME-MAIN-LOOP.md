# 游戏主链路 — 静态结构 + 动态验证 + 解混淆落库

> v19.98 | 2026-08-16 | T0 字节码字符串自证 + 回放平台动态互证


> ⚠️ **已归档** (与 07-engine/GAMELOOP.md 重复 (合并后冗余), 2026-09-04 域归档)


## 1. 主链路全景

```
java.b (AppGameContainer) gameLoop
  → updateAndRender
    → java.u.render          渲染帧
    → GameEngine.update(float) (game.i.a(float), 1352-1376)  每帧主模拟:
        bX.c(f2)              NetworkEngine 步进
        cf.c()                CommandController 更新
        cb.a(f2)              ReplayEngine 帧推进 (命令注入帧)
        ++bx                  帧号
        n.g(f2)               Team 更新 (收入/视野)
        bL.e(f2)              地图
        am.bF()               单位更新 (全单位)

## 2. 身份仲裁: game.i = GameEngine

| 证据层 | 证据 |
|--------|------|
| T0 常量池 | "GameEngine:init()" / "GameEngine init has already been called" / "GameEngine catch currentGameView" |
| T0 结构 | init(Context) 内初始化 16 个引擎字段 (h("NetworkEngine") 等阶段字符串) |
| T0 继承 | extends gameFramework.l (GlobalState, 其构造打印 "GameEngine:GameEngine()") |
| 动态 | JFR 采样 150+ 帧 (60%+ 主模拟热路径); 回放播放帧推进驱动 |
| 撤销 | 原映射 GameInputHandler (v18.3) / GameScreen (03文件名) / onScreenStart/onScreenRender (v13 猜测) 全部撤销 |

**gameFramework.d = CommandPathPart** (撤销 GameEngine 映射): 49 行纯序列化对 a(as)/a(k) — long 实体 id + 4 浮点 + int 帧号 + ao 枚举 + 嵌套 k.k 路径子流, 与 v19.97 破解的 d 子对象格式完全一致。

## 3. 16 引擎字段映射 (T0 字符串自证, supplement.csv 落库 verified)

| 字段 | 类 | 引擎 | init 行 |
|------|-----|------|---------|
| bT | ac | InputController | 220-221 |
| bQ | SettingsEngine | SettingsEngine | 223-224 |
| bM | a.e | AudioEngine | 320-321 |
| bN | am | MusicController | 325-326 |
| bU | k.l | PathEngine | 374-375 |
| bV | aa | GroupController | 376-377 |
| bP | a | CollisionEngine | 378-379 |
| bS | g | InterfaceEngine | 380-381 |
| bX | j.ad | NetworkEngine | 384-385 |
| bY | bg | StatsHandler | 387-388 |
| bZ | i.a | ModEngine | 389-390 |
| cf | c | CommandController | 395-396 |
| ca | y | GameSaver | 397-398 |
| cb | ba | ReplayEngine | 399-400 |
| cc | units.f.c | UnitGeoIndex | 402-403 |

## 4. 动态验证 (回放平台互证)

play2 轮 (r-recorded 重放) 单轮证据全景:

| 引擎 | 动态证据 |
|------|---------|
| ReplayEngine | 152 条 "Replay:" 日志 (帧推进/es 校验/命令执行) |
| GameSaver | "gameSaver:Loading save from version: 96" → "--- Save file load complete ---" → GameObject.fastGameObjectList:208 |
| NetworkEngine | "applyPendingNetworkUnits: Applying new network units from server (121 units)" — **回放 gamesave 经网络管线应用单位** |
| CommandController | 69 条 updateGameFrame + "Command: unset (0) count:2 id:1" 执行 |
| 每帧主模拟 | es 校验点帧号 301/602/903/.../9632 推进 (确定性基线帧 0 全匹配) |
| 启动阶段序列 | "--Now loading:GameEngine→Asset Index→InputController→SettingsEngine→AudioEngine→MusicController→..." 与静态 init 序列精确对应 |

## 5. 落库记录

- class-discoveries.csv: game.i GameInputHandler→**GameEngine** (v19.98 T0仲裁); gameFramework.d GameEngine→**CommandPathPart**; 删除格式错误重复行; i$a→GameEngine$a 级联
- supplement.csv (9,164→9,179): 15 引擎字段 + init(Context) + update(float) 落库 verified; a() onScreenStart→**isBusy** (方法体 bS.u||dH.b()); 删除悬空 c→onScreenRender (v1.15 无零参 c())
- 03-deobfuscated: GameScreen.java→GameEngine.java + GameInputHandler$a.java→GameEngine$a.java (12 文件引用同步, 全树 0 残留)
- 编译门禁: 24,965 → **24,961** (-4, 消除 PlayerState 双名混用)

## 6. 待办

- GameEngine 其余成员语义名 (bp/aq/bH/bG/bL/bR 等标志位) — 需逐个 T0 证明
- 引擎类名级联 (v19.98 部分完成): am=MusicController ✓ (T0 音乐字符串), ac=KeyBindings ✓ (135 键绑定字符串, InputController 键绑定主体), f.g=InGameUI ✓ (bS 真实类型); **冲突留档** aa (GroupController vs ProjectileWeapon) / g (FileSystem vs 平台检测 os.name) → arbitration-candidates.csv; 待落库: bg=StatsHandler, i.a=ModEngine, k.l=PathEngine, units.f.c=UnitGeoIndex (h() 字段角色证据, 需类自身佐证)
   → ✅ 已在 v19.100-102 辐射批次完成落库 (见 RADIATION-BATCHES.md); 冲突项 aa/g 也已在 v19.101 仲裁落库 (GroupController/PlatformDetector)
- java.u / java.b 桌面容器身份 (渲染帧/游戏循环)
