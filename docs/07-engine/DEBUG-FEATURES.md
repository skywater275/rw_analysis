# 游戏作者调试功能体系 (v19.113l 完整记录)

> v19.133f98 整理 | 发掘方法: jar 字符串搜索 + 02b f/g 键位处理链 + agent 运行时触发验证
> 配套工具: tools/capture/ (debug_client/save_diff_align/agent) | 裁决记录见 docs/07-engine 等域文档附录

## 1. 键位调试 (KeyBindings — 02 gameFramework.ac / 03 KeyBindingManager)

处理链: f/g (02 gameFramework/f/g.java ~L729 输入更新) → 逐键位 a() 检查

| 键位 (02b 字段) | 功能 | 处理代码 (02b 铁证) |
|----------------|------|---------------------|
| l / m | Debug Left / Right | 单位循环选择 |
| U | **Debug Editor** | `bv = !bv` — 沙盒/编辑器模式开关 (沙盒 agent 链核心!) |
| V | Debug Editor 子开关 | `bp = !bp` (editorMode) |
| W | **Debug Slow (0.1x)** | `bt = 0.1/1` 切换 |
| J / K | **Debug Slow 阶梯** | bt ± (0.25/0.5/2/4) — 最高 64x |
| L | **Debug Pause** | `bt = 0/1` 切换 + "Game paused" |
| X | Debug HideInterface | NetEngine.U() 测试弹窗 ("Adding test popup") |
| Y | Debug HideInterface Temp | cT = 180 (摄像机) |
| Z | **Debug InvincibleUnits** | `bw = !bw` (HUD "Invincible Units") |
| aa | **debugPrintSelectedUnit** | cG 选中单位 → U() 状态 dump |
| ab | **debugDevModeSwitch** | `bl = !bl` (debugTempMode 前置) |
| ac | debugAIViewSwitch | `AIStrategy.as = !as` (需 bl) |
| ad | debugMapSwitch | `n.f.a = !a` (需 bl) |
| ae / af | Debug Screenshot / High | java/u.java L703 截图逻辑 |

## 2. HUD 沙盒状态 (02 gameFramework/f/g.java ~L1160)

沙盒模式 (bv) 下 HUD 显示状态 (各开关的可见指示):
```
"Editor Active"          ← bv (沙盒模式)
"Game Speed: Xx"         ← bt (游戏速度)
"Invincible Units"       ← bw (无敌)
"AIs frozen"             ← AIStrategy.bG > 0 (官方 AI 冻结开关)
```

## 3. Debug 对象脚本 API (67 方法 — tools/capture/script_api.json)

- 单位: createUnit/createManyUnits/createCustomUnitFromTypeId/killAllUnits/removeAllUnits/moveAllUnitsOnTeam/selectNextUnit
- 网络: networkPause/networkAbort/networkSetUdp/enableFastSync/checkDesync
- 存档: plainTextDebugSave(boolean)/loadSaveFromSystemPath/saveLastGameForReplay
- 对局: setTeamCredits/setTeamAllyGroup/giveAllActionsToAllUnits/completeAllUnitsQueues/isTeamDefeated/isTeamWipedOut
- 速度: overrideDeltaSpeed(float) — GlobalState.bu (脚本覆盖; 与 bt 键位双机制)
- 测试: runAllUnitTests/runAllLeakTests/startRandomUnitStressTest/startRandomUnitDesyncTest

## 4. 启动参数 (-debug)

- `-debug 5677:local` — 调试服务器 TCP 5677 (ping→pong / script <代码>)
- 其它 (Main 30+ flags): -devdebug/-log/-nodisplay/-printunits/-outputunitimages/-debugscript/-connect_lobby

## 5. debugXXX 方法族 (jar 字符串)

| 方法/字符串 | 类 | 说明 |
|------------|-----|------|
| debugUnitCountByType | PlayerState.W() | 按类型单位统计 dump (Units/Buildings 分组+total) — 已验证 |
| debugPrintSelectedUnit | UnitType.U() 链 | 炮塔全状态 dump — 已验证 |
| debugMemory | VariableScope | 内存调试 |
| debugMessage | custom.a.a.f + n/c | 调试消息 |
| debugTempMode | f/g (bl) | 临时调试模式 (DevMode 键) |
| debugSocket | a.a + java/i | 调试服务器 (5677) |

## 6. 运行时触发方式 (三通道)

| 通道 | 工具 | 适用范围 |
|------|------|---------|
| 键盘 | 游戏内键位 (需设置绑定) | 全部键位调试 |
| 脚本 | debug_client.py call (签名白名单) | Debug 对象 67 方法 |
| agent | Attacher + RunnableAction 投递 | 任意字段读改 + 任意方法调用 (cG/U()/W()/bw/bG 已验证) |

## 7. U() 打印链 (选中单位状态 dump 格式)

```
---- Debug for:c_tank id:493---
Dir was:-720.0 for name:1        ← 炮塔角度 (ap.a) + 炮塔名 (fQ[i].a)
lockDelay:4.4000034 shootCooldown:0.0   ← ap.d / ap.e
updateAndShouldResetTurret:true  ← j.b(i, 0.0F)
idleDir:0.0                      ← j.C(i)
diffDir:0.0                      ← 角度差
```

## 8. 已修正的 03 语义 (调试铁证驱动)

- UnitTurret: turretOffsetX→turretAngle / turretPivotY→lockDelay / restAngle→shootCooldown (jar 无旧名字符串 = 无源猜测)
- GlobalState: bt=gameSpeed / bw=invincibleUnits / bl=debugTempMode / bp=editorMode / bv=sandboxMode
- AIStrategy: bG=aiFreezeTimer / as(静态)=aiDebugView
- UnitInstance: cG=isSelected / dz=unitTypeHandle

## 9. 调试编辑器 (units.h = DebugEditor) — 22 行动完整清单

入口: Debug Editor 键 (bv=true) → 自动创建+选中 new h(false) — 编辑器单位选中时显示行动面板

| 行动 (h$N) | 功能 | 实现要点 |
|-----------|------|---------|
| h$1 reloadUnits | 重载全部单位 (mod 热加载) | |
| h$12 reloadOnlyActiveUnits | 重载活动单位 | |
| h$17 unitClone | 克隆单位 | |
| h$18 removeUnits | 移除单位 | |
| h$19 killUnits | 杀死单位 | |
| h$20 finishQueue | 完成建造队列 | |
| h$21 **nukeAt** | **"Create a nuke at a point"** — 编辑器官方核弹按钮 | 点击地图点生成弹丸 |
| h$22 freezeAI | 冻结单个 AI | |
| h$23 changeAlliance | 改变同盟 | |
| h$2 startRecording | 开始录像 | |
| h$3 startReplayPlayback | 开始回放 | |
| h$4 hideInterface | 隐藏界面 | |
| h$5 **freezeAllAI** | "Freeze full high level logic for all AI forever" | h.c 切换 (Freeze AI/Unfreeze AIs) |
| h$6 pauseGame / h$7 slowGame / h$8 fastForward | 暂停/慢速/快进 | |
| h$9 **search** | "Search for units" | h.G 搜索模式 (n.e 枚举) + h.H 过滤文本 |
| h$10 enableDebug | "Show hidden unit information in tooltips including flags, ammo, tags and resources" | l.bl 切换 |
| h$11 enableAIDebug | "AI debug view" | AIStrategy.as 切换 |
| h$13 enableTriggerDebug | "log a message when any auto triggers fire" | l.bn 切换 |

## 10. 网络/回放调试

- "-replay_debug" 启动参数 / "Saving client save for debugging" + "debug game save" (NetEngine j.ad — desync 调试存档)
- "DebugDesyncDetector (stress test)" / "Ignoring incoming resync tagged as debug only"
- "net.rudp.debug" (可靠 UDP 调试) / "network debug:" / "extraDebug:"
- "DebugSocketConnection: waiting for ScriptEngine" (调试服务器启动链)
- "-debugscript" + "Running debug script:" (调试脚本参数)

## 11. Triggers 对象层加载调试 (地图宾语层)

地图设计/读取的对象层 (Triggers 层) — 02 gameFramework.n.f = AIWaveSystem 加载器:

| 日志 (02b n/f.java 铁证) | 触发场景 |
|--------------------------|---------|
| "Found N map triggers" | Triggers 层加载完成统计 |
| "linkedTo target not found: X" + **"Possible IDs:" 全触发器 ID dump + "--------"** | 触发器链接失败时的调试帮助 (列出所有可用 ID!) |
| "linkedFrom target not found: X" / "deactivatedBy: target not found: X" | 反向链接/停用链接失败 |
| "Key was not used: X" (AITask.g) | 触发器属性键未使用检查 |
| "MapTrigger-Debug (id type): msg" (AITask.h) | 触发器实例调试日志 |
| "MapTrigger-Error (id type): msg" (AITask.g) | 触发器实例错误日志 |
| "Classic survial waves selected" / targetType 选项 (allUnitsAndBuildings/allBuildings/mainBuildings/commandCenter/requiredObjectives/survival) | 触发器目标类型解析 |

03 对应: aicore/AIWaveSystem.java:480 ("Found ... map triggers") + AITask
