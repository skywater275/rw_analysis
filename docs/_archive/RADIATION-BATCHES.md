# 全面解混淆 — 五大基础辐射批次

> v19.100 | 2026-08-16 | 以主链路/回放校验/mod/地图/单位加载五基础为锚点, 字节码调用图辐射解混淆
> 本文记录 v19.100-19.102 辐射批次; 此后 v19.103+ 继续增长至 **10,119** (当前, 见 STATUS.md)
> 历史口径: 9,112 (v18.4) → 9,394 → 9,535 (本文) → 10,119 (v19.108)


> ⚠️ **已归档** (辐射批次方法论历史 (v19.100-102), 已被后续管线方法论承接, 2026-09-04 域归档)


## 方法学

1. **锚点**: 五大基础已映射类 (GameEngine/ReplayEngine/ModEngine/MapEngine/UnitRegistry/NetworkEngine/CommandController/GameSaver/PathEngine/StatsHandler)
2. **辐射**: javap 字节码调用图 → 每帧调用目标 → 字符串/结构 T0 仲裁 → 落库 supplement.csv
3. **动态验证**: play2 回放播放日志 + pipelines 干净启动轮 → 映射运行时互证

## 已完成批次 (主线程)

| 批次 | 内容 | 条数 |
|------|------|------|
| 辐射批1 | GameEngine.update(float) 36调用目标 → 10 方法落库 (MapEngine.update/CommandController.update/ReplayEngine.updateGameFrame/NetworkEngine.updateStep/PathEngine.update/PlayerState.updateAllTeams/GroupController.update/StatsHandler.update/CommandPathPart.update/UnitGeoIndex.update) | 9 |
| 辐射批2 | es 校验 15 条目 (ak=ChecksumCalculator 字段 c-q) unverified→**verified** (play2 帧0全ok动态升级) | 15 |
| 辐射批3-Network | NetworkEngine (ad): startSingleplayer/hostNetworkGame/disconnect/startGame/returnToBattleroom + isNetworking/isServer/checksumCalculator 等 | 5 |
| 辐射批3-Command | CommandController (c): newCommand/submitCommand/processLocalCommands/processNetworkCommands/localCommandQueue/networkCommandQueue | 7 |
| 辐射批3-Saver | GameSaver (y): saveGame/loadGame/loadFromStream/saveToStream/postLoad | 5 |
| 辐射批3-标志位 | GameEngine: currentAIStrategy/gameStarted/gameRunning/isBusyFlag (保守 unverified) | 4 |
| 辐射批3-Path | PathEngine (k.l) 类+成员: setupMap/findPath/update ("PathEngine: Ready" 字符串自证) | 5 |
| 辐射批3-Stats | StatsHandler (bg) 类+成员: writeStats/readStats/update/recordKill | 5 |

## 并行 agent 合并结果 (v19.100)

| Agent | 类 | 建议 | 合并后 |
|-------|-----|------|--------|
| A | ReplayEngine (ba) + ModEngine (i.a) | 90 (verified 77 + weak 13) | 已合并 |
| B | MapEngine (b.b) + UnitRegistry (ar) | 81 | 已合并 |

- **合并: 151 verified + 26 unverified(weak) = 177 条**, supplement 9,112 → **9,394**
- **冲突修正 15 条** (agentA 发现 sig-backfill 批错误映射): ba.f()=calculateChecksum (原cyclePlaybackSpeed), ba.g()=stopIfRecording (原captureFogSnapshot), ba.h()=updateGameFrameReadRecord (原startPlaybackThread), ba.i/j/k=isActive/isPlaying/isRecording, ba.l()=markAIPlayersForRecording, ba.f=traceChecksumsWriting 等 — **03 侧 ReplayEngine.java 同步修正** (7 错误方法名)
- 新证据: bb.java 揭示 ba.A=recordedCommandCount / ba.B=recordedResyncCount; SettingsEngine 揭示 i.a.a/b=errorAccumulator/lastErrorMessage; i.b 字段与 03 ModInfo 对齐 (q=sourceFolder 对齐错误发现)

## 基线

- supplement.csv: 9,112 → **9,394** (+282 本轮辐射)
- 编译: 24,961 (稳定 — 映射数据不引入编译错误)
- class-discoveries: 新增 PathEngine/StatsHandler 类映射 + agent 揭示待处理 (ModInfo 对齐等)

## v19.101 辐射批4 + 仲裁消化

| 辐射批4-Command | gameFramework.e 13 字段修正 (v19.97 写侧字节码 T0: team/waypoint/specialAction/commandingPlayer/unitRefs/pathParts...) + 6 getstrictfp 占位删除 + traceSource 补缺 | +8 净 |
| 仲裁-aa | ProjectileWeapon → **GroupController** (b() 递增 id 分组 + 单位编组算法 + h() 字段角色三重证据) + 3 成员 (createGroup/groupUnits/groupCounter) | +3 |
| 仲裁-g | FileSystem → **PlatformDetector** (a() os.name 检测返回 h 平台枚举) + detectPlatform | +2 |
| Agent C/D | 单位域类 (units.y/au/a.c) + 第二层调用目标 — 运行中 | — |

- 冲突仲裁消化完成: arbitration-candidates.csv 中 aa/g 两条已裁决落库

## v19.102 第三层辐射 + 动态验证

| agentE | Packet层 (PacketWriter 46/PacketReader 24/Packet 1) — 回放头部格式/块协议/包类型键 T0 证据链 | +71 |
| 错误修正 | au.b=packetType/au.d=extraId/k.m=readCustomUnitTag/k.e=mainDataInputStream/as.e=beginBlock (5处) | 5 |
| y 字段复核 | V/W/X/Y: maxHp/speed/turnSpeed/turretTurnSpeed → waypointElapsedTime/pathStallTimer/pathStallTimerX/idleWaitTimer (路径点计时器 T0: 3257清零+2736累积+阈值重寻路) | 4 |
| 动态验证 | 43 条升级 verified: PathEngine 20核求解器/FogMapRenderer smooth fog/TimeUtils took:计时/Packet 网络包 运行时日志互证 | 43 |

- **supplement 累计: 9,112 → 9,535 (+423, 七轮辐射)**; 编译 24,961 稳定
- 后续 (v19.103-19.108): supplement 继续增长至 **10,119** (字段 5,940 + 方法 4,179); 编译降至 **19,337**
- 回放验证轮 (rw-dyn-verify): 回放加载成功 (Starting frame:0) 但 headless LibRocket 渲染偶发崩溃 — 已改用 20+ 历史日志交叉验证
