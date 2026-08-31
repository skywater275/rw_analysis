# TOOLS-TREE — 工具链完整结构树

> 版本: v19.133f98 | 日期: 2026-09-04 | 本文件承接 CLAUDE.md 精简前的 tools/ + rwlib/ 完整结构树 (2026-09-04 精简战役移出)
> 导航: [CLAUDE.md](../../CLAUDE.md) | 工具清单: python tools/manager.py list

│
├── tools/               — 70+ 脚本 + manager.py (34 修复器 + 16 工具 + 33 归档)
│   ├── manager.py       ← 工具管理器 (list/check/status/run)
│   ├── core/            — 核心引擎 (4)
│   │   ├── apply_enhanced.py   ← 6阶段反混淆主引擎 (一致性重建流水线)
│   │   ├── cross_validate.py   ← 字节码交叉验证 [rwlib]
│   │   ├── type_renamer.py     ← 类型引用重命名 [rwlib]
│   │   └── sig_renamer.py      ← 签名驱动方法重命名 [rwlib]
│   ├── gates/           — 门禁 (3)
│   │   ├── javac_gate.py       ← 编译门禁 (24真实jars, 输出 compile-errors.csv) [rwlib]
│   │   ├── stats.py            ← 状态报告生成 [rwlib]
│   │   └── comprehensive.py    ← 覆盖率分析 [rwlib]
│   ├── fixers/          — 修复脚本 (75+, 含 auto_align 自动补全器/fix_modkeys 键名提取/extract_func_semantics 函数绑定/extract_annotations 注解提取 + 历史R4循环家族修复器)
│   │   ├── [逆1] verify_suspicious.py ← 726 suspicious 映射复核器 (javap member 存在性, 8并行, 误报恢复 verified-exists)
│   │   ├── [逆4] reclassify_dead_mappings.py ← 390 死映射三分类清理 (占位名垃圾/构造器签名核对/03 存在性, 删除留档 dead-mappings-removed.csv)
│   │   ├── [逆4d] relocate_members.py ← 归属迁移器 (03 声明提取×B2类映射×javap类型兼容×顺序zip×域一致性, 宿主/成员双修正; 逆5b: 宿主身份覆盖+家族白名单)
│   │   ├── [逆5a] fix_verified_column.py ← 528 垃圾 verified 列漂移修复器 (字符扫描括号深度追踪重建 + javap 参数级验证 + B2 可读参数名翻译)
│   │   ├── [v19.125] fix_scriptengine_batch.py ← ScriptEngine 官方名恢复 (03 UIScriptEngine→ScriptEngine, git mv 4 文件+全库广播, 05-gamelib/02b 双铁证)
│   │   ├── [v19.125] fix_dollarn_digits.py ← $N 数字误用还原 (数组索引/System.exit/group() 中 `$N`→数字, 02b 锚点)
│   │   ├── [v19.125] fix_inputaxis_batch.py ← InputAxis=GlobalState + java.c.l 字段修复 + 坏副本删除 + SteamEngine 包修正 (02b gameFramework/l 全限定铁证)
│   │   ├── [v19.125] fix_f_oldname_batch.py ← f 旧名广播 (类 f=GameUtils 方法 + 子包 ae→ThemeColors/d→TextFormatter/af→ui.af/f.a.*→panels.*/game.f.a→MovementController.a/g.f→DataFieldProvider + anim 直译残留)
│   │   ├── [v19.126] fix_modinfo_batch.py ← ModInfo 调用点错误语义化还原 (modVersion()→b()/modId()→a() 等 36 处 + filesystem.g→FilePathSanitizer, 02b i/b 方法序锚点)
│   │   ├── [v19.126] fix_unitstatetracker_batch.py ← UnitStateTracker 家族 (f/an+f/x+f/ao: am→UnitInstance/y→UnitType/d.b→ResourceComponent/an→自身/BridgeUnit 双前缀/ao.e 字段/EffectManager 补 b(f,f))
│   │   ├── [v19.127] fix_teamunittracker_batch.py ← TeamUnitTracker (02b game/s: as→UnitTypeHandle/d.b→ResourceComponent/d.l→CarrierUnit 接口/d.j→BuilderUnit/显式 Iterator + EffectManager 参数 d.b 修正)
│   │   ├── [v19.128] fix_unittypehandle_chain.py / chain2.py / chain3.py ← as 接口返回 d.b=ResourceComponent 链式修正 (UnitTypeHandle 3方法/UnitRegistry/UnitInstance/ResourceComponent 内部字段语义化/EffectManager 补 do_b(f,am,double))
│   │   ├── [v19.128] fix_mur_h/f/p/q/y/z/jk/du/wcd/dup.py ← ModUnitRegistry 接口方法族补齐 17 个 (02b l 逐方法锚点: v=return M/h=specialActionsByTier/f=本地化描述/q=return ff/p=return aJ/y=usesCreditResources/z=return aw/d=actionHandler 等)
│   │   ├── [v19.129] fix_anima_batch.py / fix_anima_batch2.py / fix_anima_batch3.py ← anim/a 双副本还原 (extends LogicBoolean + d→EffectConfig/l→ModUnitRegistry + bb→LocalizedString + anim.b→effects.b 统一 + i/v→LogicBoolean/y,k→Texture/displayResourceType)
│   │   ├── [v19.131] fix_mur_batch5.py ← MUR 内部 28 项 (UnitState→custom.ab/readString/ad 类型/ModInfo 语义名/补 getUnitTypeName+E/误伤还原)
│   │   ├── [v19.131] fix_mur_batch6.py ← 外围 12 文件调用点 (be bL=MapEngine/bf GameUtils.a/bg-bh TagFilter→UnitConfig/aa GlobalState/animation-k ModUnitRegistry/UnitParameter reset)
│   │   ├── [v19.131] fix_mur_batch7.py ← CustomVisuals 3参合成构造 (02b z L85)/be 字段语义化/bf TeamTag→UnitConfig/bg ResourceType+SellAction e 字段
│   │   ├── [v19.131] fix_mur_batch8.py ← bf extends SpatialQuery (02b f/i)/bg game/q=ResourceType (class-discoveries)
│   │   ├── [v19.131] fix_mur_batch9.py ← SellAction 完整对照 (02b units/a/z: UnitTypeHandle/UnitType/Factory/UnitInstance.bE/ActionTargetType/ActionCategory/f())
│   │   ├── [v19.131] fix_mur_batch10.py ← aj.java 字段语义化 (02b aj 直译: effectDuration/effectFlags + bc localeCode/translatedText)
│   │   ├── [v19.131] fix_mur_batch11.py ← bf 返回 PlayerState (02b n)/aj raw ArrayList+cast
│   │   ├── [v19.131] fix_mur_batch12.py ← PlayerState 清理 (删误建/重复/误补方法) + NetEngine 补 aq()/b(PlayerState)/b(int) (02b j/ad L4961)
│   │   ├── [v19.131] fix_mur_batch13.py ← bh 37错误 (m.e 变量/InputNetStream 参数/TagFilter 语义字段/DirectionConfig→CustomArrayList) + GameUtils formatDuration 族 + MovementController.p + UnitInstance.a(float×5)
│   │   ├── [v19.131] fix_mur_batch14.py ← GameUtils a(EffectConfig→GameObject) (02b w) + 调用点 formatDuration→a + ab.java m→CustomArrayList
│   │   ├── [v19.131] fix_mur_batch15.py ← GameUtils 委托歧义 cast (交叉具体性 F55) + 重复方法清理
│   │   └── [v19.131] fix_mur_batch16.py ← 4参调用点 cast(GameObject) (bh/Projectile/ELU/FireDecoration) + bh Iterator import
│   │   ├── [v19.116] fix_unitregistry_batch1.py ← UnitRegistry 重建 (v19.115s 误删勘误: ResourceUnit→UnitRegistry
│   │   │     git mv + 107文件329处广播 + UnitTypeHandle.x() Factory→UnitConfig + MUR Texture族/T=ad)
│   │   ├── [v19.116] fix_modloader_batch2.py ← ModLoader 类型族 (n缓存/DirectionType误标/am+ar遮蔽/
│   │   │     actions.f→ActionRegistry改名/resources.CustomActionBase全限定, --dry-run/--apply)
│   │   ├── [v19.116] fix_modloader_batch3.py ← CurveType→EffectConfig/TagFilter→UnitConfig/ao→MovementTypeEnum/
│   │   │     Boolean.valueOf/ec=PathResult锚点 + 依赖补缺 (k(String)/do_/InGameUI.K)
│   │   ├── [v19.116] fix_modloader_batch4.py ← 回归修复 (object22/object1722/object18) + EffectManager补方法/
│   │   │     EffectConfig a(l,ab,String,String)/effects.e.java + L1909行重写
│   │   ├── [v19.116] fix_modloader_batch5.py ← cp-cq→EffectManager/cI if链/r→Modifier/bo2语义名/
│   │   │     CAB.k类型/FactoryBuilding Texture族
│   │   ├── [v19.116] fix_modloader_batch6.py ← 13行 action 解析链按 02b L3222-3234 顺序 + base 11文件 7参a() d全限定
│   │   └── [v19.116] fix_modloader_batch7.py ← MUR 收尾 (implements AttackWaypoint 删除/x()/CurveType→d静态/
│   │         ModInfo k(long)+m())
│   │   ├── [v19.109] fix_gameengine_batch.py ← GE 早期广播修复 (02 game/i.java 锚点, 管线链首发)
│   │   ├── [v19.117] fix_gameengine_batch1.py ← GE 战役批1 (init 方法族 + 手动实例类型修正, 02b i.java 对照)
│   │   ├── [v19.117] fix_gameengine_batch2.py ← GE 战役批2 (CrashHandler/onScreenStart/W()/b(int,int)/b(boolean)/K()/endG)
│   │   ├── [v19.117] fix_gameengine_batch3.py ← GE 战役批3 (依赖补缺: MapException/byte cast/bF 字段/UnitRegistry 常量)
│   │   ├── [v19.117] fix_gameengine_batch4.py ← GE 战役批4 (bF 字段/bV+player/ci()/dc()/ce.q/b(float,float)/selectionBoxPaint)
│   │   ├── [v19.117] fix_gameengine_batch5.py ← GE 战役批5 (回归批4 + GameStateEnum2/ProjectileType2 删除/textPaint)
│   │   ├── [v19.117] fix_gameengine_batch6.py ← GE 战役批6 (ProjectileType2 全限定/GameHUD.a/g()→cameraController/
│   │   │     StatisticType+StatsGrouping)
│   │   └── [v19.117] fix_gameengine_batch7.py ← GE 战役批7 (ProjectileType2 比较/EffectConfig cast/c(String,String)/GameUtils.f)
│   │   ├── [v19.117] fix_gameengine_batch8.py ← GameEngine 批8 (bX.c(float) 错译修正/aj()+waterTexture3() 错译删除/
│   │   │     a(StatisticType,StatsGrouping)+findNextMapLevel 补/InGameUI.a(float))
│   │   ├── [v19.117] fix_gameengine_batch8b.py ← gameFramework/GameEngine.java 误命名清理 (CommandPathPart
│   │   │     损坏副本删除/k.fulator→PathCostCalculator/5 引用点修正, --dry-run/--apply)
│   │   ├── [v19.117] fix_gameengine_batch9.py ← onScreenStart 误名族 6 方法还原 (02b L452/1485/1604/1634/2088/2384) +
│   │   │     updateAllGame/drawAll 内部 15 处 + GS.ay()/GameHUD.a(float)/HUDManager.a(float)/MapEngine T,U/
│   │   │     MapLayer g,h/CustomUnitType.s(float)/GameEngine$a.java 新建
│   │   ├── [v19.117] fix_gameengine_batch10.py ← 42 调用点修正 + NetEngine.a(float,boolean)/Renderer.c()/
│   │   │     AppFramework 接口修正/ResourceLoader 4 字段类型/GameHUD.b(float)+d(float)/HUDManager.b(float)/
│   │   │     CloudRenderer.b(float)/InGameUI.e()/UnitInstance.bX/静态 String f/c(float)
│   │   └── [v19.117] fix_gameengine_batch11.py ← 残余 32 清零 (o2.headNode/TeamColorTexture.G/clearScreen/
│   │         w→EffectConfig/loadImageFromResource/ce.geti/ai.a→AIStrategy + GS 幻觉抽象删除+h(int)/
│   │         NetEngine.ad/AppFramework.c()/TextureManagerInterface.a(float,float)/UnitInstance.cf())
│   │   ├── [v19.115z] fix_reliablesocket_batch1.py ← ReliableSocket 批1 (类型 a.h→core.Packet + 调用点 30 项: receiveBuffer→a/inputStream→e/outputStream→getOutputStream + instanceof Packet 族 + b() 方法体重写)
│   │   ├── [v19.115z] fix_reliablesocket_batch2.py ← 批2 (ReliableProfile.a 类型 + ConnectionEntry→SynPacket + raw cast + 合成构造)
│   │   ├── [v19.115z] fix_reliablesocket_batch3.py ← 批3 (Timer→TaskRunner 构造 + TaskRunner 补 c-d-e-f + TagFilter→ReliableSocket 静态族)
│   │   ├── [v19.115z] fix_reliablesocket_batch4.py ← 批4 (ReliableServerThread/ReliableServerSocket/ReliableClientSocket/ServerConnectionListener/ReliableSocketThread 02b 对照)
│   │   └── [v19.115z] fix_reliablesocket_batch5.py ← 批5 (ConnectionEntry.b + 字段名 c/g/d→udpSocket/profile/remoteAddress + 残留修正)
│   │   ├── [v19.115y] fix_netengine_batch2.py ← NetEngine 战役批2 (151错误清零: 字段去括号纠错/方法名还原 02b 锚点
│   │   │     23 项 + 跨类 25 项: GameRenderer→GameUtils/TileEntry→MapEngine/steamworks.a→SteamEngine/
│   │   │     mods.b→ModInfo/j.am→ConnectionState/aj→ServerInfo, --dry-run/--apply)
│   │   ├── [v19.115y] fix_netengine_batch2b.py ← 批2b (P()/L()/ar()/ad() 补同名方法 + 调用点修正 + L4059→onNetworkGameStarted)
│   │   ├── [v19.115y] fix_netengine_batch2c.py ← 批2c (误伤修正 ++this.P/++this.ar + Iterator 拆分 +
│   │   │     GameModeEnum 常量类型 + getMapType 返回类型 + panels/f ActionPanel import)
│   │   ├── [v19.115y] fix_netengine_batch2d.py ← 批2d (++this.P() 误伤 + object3 迭代 12 空格 + InGameUI.a(panels.f))
│   │   ├── [v19.115y] fix_netengine_batch2e.py ← 批2e (m(float) object2 拆分 + ReplayEngine.a(ChecksumCalculator) 简化)
│   │   ├── [v19.115y] fix_netengine_batch2f.py ← 批2f (PacketDecoder 幻觉清理 + GameModeEnum 重写 enum +
│   │   │     SteamSocket/SteamInputStream 字段类型 + SecurityHasher n→WebAPIClient + NetEngine$6/7 + WebAPIClient 补 a(List,String,String))
│   │   ├── [v19.115y] fix_netengine_batch2g.py ← 批2g (ReceiveWorker 单参构造 + as2.PacketDecoder→c(String) +
│   │   │     MinimapPanel/UIElementBase import ui.a→ui.panels)
│   │   ├── [v19.115y] fix_netengine_batch2h.py ← 批2h (ReceiveWorker 合成 2 参构造 T0 javap)
│   │   ├── [v19.115y] fix_netengine_batch2i.py ← 批2i (ReceiveWorker.b PlatformBackend→PacketDecoder + HUDOverlay public)
│   │   ├── [v19.115y] fix_netengine_batch2j.py ← 批2j (ReceiveWorker PlatformBackend→PacketDecoder 合成静态)
│   │   └── [v19.115y] fix_netengine_batch2k.py ← 批2k (ReceiveWorker au2 字段语义化 packetData/connection/packetLength)
│   │   ├── [v19.115u/v/w] fix_combataction_batch1-3.py ← CombatAction 三批 (isPersistentGroup 反向语义化→02b 方法名/
│   │   │     GameRenderer→GameUtils/字段误当方法/raw 集合显式迭代, --dry-run/--apply)
│   │   ├── [v19.115v] fix_transportergroup_batch1.py ← TransporterGroup 批1 (方法名还原 02b c/d/e/a(f,bl)/a(bl)/f/
│   │   │     writeToStream(BaseZoneStage)→a(InputNetStream)/Command+ActionId 强转)
│   │   ├── [v19.115w] fix_maprenderer_batch1-4.py ← MapRenderer 四批 (字段类型 NeutralPlayer→MapLayerRenderer/
│   │   │     e=UniquePaint/offsetX/Y=Texture/s=MapRenderer/TileEntry→MapEngine/幻觉段清理/接口补缺)
│   │   ├── [v19.115t] fix_combatmain_batch2.py ← CombatMain 类型标注批 (PacketBuilder→UnitTypeHandle×11/
│   │   │     custom.l→ModUnitRegistry/commands.l→CarrierUnit/s→GameAction/ResourceCost→CustomActionBase/
│   │   │     l()→m()/n()/o()/q() 方法序, --dry-run/--apply)
│   │   ├── [v19.115t] fix_combatmain_batch3.py ← 调用点误标批 (updateZoneUnits/chooseUnitTypeToBuild→b/c/
│   │   │     aY→allowExpansion/bC→buildingFactories/e→Command/q,r→UnitInstanceList)
│   │   ├── [v19.115t] fix_combatmain_batch4.py ← AIStrategy 字段语义名 17 处保序对照 + 变量重复 +
│   │   │     enhanced-for 显式迭代 + 7 文件补方法
│   │   ├── [v19.115t] fix_combatmain_batch5.py ← 收尾 (buildingFactories 串扰/raw 集合迭代/import)
│   │   ├── [v19.115t] fix_combatmain_batch6.py ← au=WeaponAction 修正 (v19.111 字段序铁证)
│   │   └── [v19.115t] fix_combatmain_batch7.py ← av=WeaponTypeEnum 回滚修正 (02b av.java 17 常量 a-q 铁证)
│   │   ├── [v19.115r] fix_logicbooleans_batch1.py ← logicBooleans 双重前缀修复 (Outer.Outer$Inner→Outer$Inner 51 处;
│   │   │     \$ 顶级类 vs 点号嵌套引用根因, 37 处 LogicBooleanContext 广播源)
│   │   ├── [v19.115r] fix_logicbooleans_batch2.py ← 裸名映射 86 处 (n/am/av/q/j → PlayerState/UnitInstance/
│   │   │     WeaponTypeEnum/UnitTypeComparator/CustomUnitType) + TagFilter/ActionParticle 错位修正
│   │   ├── [v19.115r] fix_logicbooleans_batch3.py ← 误伤还原 (LogicBoolean.c/Pattern.c 子串) + source/type→effects
│   │   ├── [v19.115r] fix_logicbooleans_batch4.py ← VariableScope 家族 (DirectionConfig→CustomArrayList,
│   │   │     n2→PlayerState, t.a=AmphibiousUnit.a) + ParameterMapping Iterator+Field
│   │   ├── [v19.115r] fix_logicbooleans_batch5.py ← 遮蔽 import (effects 遮蔽同包 extends) + 误伤残留
│   │   ├── [v19.115r] fix_logicbooleans_batch6.py ← 零散个案 (PlayerState.b/c(TeamTag)、cc/cI/cL 等)
│   │   ├── [v19.115r] fix_logicbooleans_batch7.py ← 终批 (ab 泛型冲突清除、_withTag→TeamTag、UnitType 补缺)
│   │   ├── [v19.115q] fix_ay_batch1.py ← ay战役 (custom.ay CustomEffectTemplate 518行 02b 整写直译 +
│   │   │     HUDManager d→HUDAnchor 签名修复 + b() 补缺; ModUnitRegistry.c(String)/GameUtils k/j, --dry-run/--apply)
│   │   ├── [v19.115q] fix_ay_batch1b.py ← ay战役精确修复 (删多余 aA + GameUtils k/j + stripIndex 简化)
│   │   ├── [v19.115q] fix_ay_rollback.py ← ay战役回滚 (重复追加清理; 教训: 用精确文本锚点勿用块删除)
│   │   ├── [v19.115p] fix_base_batch5.py ← base 残留名批5 (7 文件 02b 整写直译 + bp.java 签名修复 +
│   │   │     9 处依赖补缺: CustomUnitType 7 方法/ModUnitRegistry aA+i/NetEngine.a(String,String)/
│   │   │     VariableScope 构造/PlayerState.W/UnitType.i/KeyframePoint.h/UnitInstance.c(am,Z), --dry-run/--apply)
│   │   ├── [v19.115p] fix_base_batch5b.py ← 批5b (类型包修正: aj/双 LogicBoolean effects vs logicBooleans +
│   │   │     enhanced-for 泛型擦除强转 + 依赖补缺2)
│   │   ├── [v19.115p] fix_base_batch5c.py ← 批5c (重复追加清理 + KeyframePoint/ModUnitRegistry 全限定修正)
│   │   ├── [v19.115i] fix_command_batch2.py ← Command 战役批2 (Command.java 33 处类型/调用点修复 +
│   │   │     15 依赖类补方法/重建: ProjectileManager 02b ab 直译/ProjectileWeapon 补 3 方法/
│   │   │     MapPingAction 静态注册表/InputNetStream d(String)/DebugDesyncDetector 新建等, --dry-run/--apply)
│   │   ├── [v19.115h] fix_aistrategy_batch1.py ← AIStrategy 字段类型修正 (NeutralPlayer→UnitBuildStrategy×13, PathFinder)
│   │   ├── [v19.115h] fix_aistrategy_batch2.py ← AIStrategy 方法名对齐 02b (writeToStream 21 幻影重载→a/b/c/l/m/n/o, 声明+调用点)
│   │   ├── [v19.115h] fix_aistrategy_batch3.py ← AIStrategy 内部引用修正 (AIStrategy/局部变量/strategies.AIStrategy)
│   │   ├── [v19.115h] fix_aistrategy_batch5.py ← AIStrategy CombatMain/CombatAction 成员引用对齐 03 语义名
│   │   ├── [v19.115h] fix_ai_pkg_batch4.py/4b.py ← CombatMain/CombatAction/AIStrategyNode 方法名对齐 02b (isPersistentGroup 系列→a/b/c/d)
│   │   ├── [v19.115h] fix_ai_pkg_batch6.py ← AIStrategyNode/TransporterGroup/CombatAction 修正 + AIStrategy 综合
│   │   ├── [v19.115h] fix_ai_pkg_batch7.py ← AIStrategy 61 处综合修复 (BuildSlot extends/gete4/UnitFlag 等)
│   │   └── [v19.115h] fix_ai_pkg_batch8.py ← 依赖方法补全 (cr/cH/h(am)/a(ArrayList)/n+m(am)/a(y,d.b,boolean))
│   │   ├── [v19.115] fix_renderlayer_pollution.py ← RenderLayer 污染双副本清除
│   │   │     (26 文件删除 + import 清理 + HUDElement/DrawEffect 字段→DrawLayer)
│   │   ├── [v19.115] fix_factory_family.py ← Factory 字段区重建 + 内部类残留
│   │   │     (静态 GameAction 字段 Position 误标 / h$N→Factory$N / NetEngine.a(PasswordManager) 恢复)
│   │   ├── [v19.115] fix_factory_action_names.py ← Factory 内部类方法名语义化
│   │   │     (a()/b()/c()/d()/b(am,bl)→getDescription/getLabel/getResourceCost/getDisplayString, 16 文件)
│   │   ├── [v19.115] fix_pathfinding_helper.py ← PathfindingHelper 字段误标类修复 (14 处)
│   │   │     (02b aq.java 逐行: g/h/i/j PointF 字段 + aq. 前缀 + PathFinder 类型)
│   │   └── [v19.114] fix_short_type_residual.py ← 03 类型标注残留修复器
│   │         (单字符 new 形态 import/包消歧; 注意: 需方法级同步联动)
│   │   └── [v19.114] fix_field_zip.py ← 字段保序 zip 映射生成器
│   │         (三级查找: 02b 字段区/extends 父链/javap 字节码表 + 03 丢字段补声明)
│   │   └── [v19.114] fix_bc3_fill.py ← 深2 BC3 声明侧方法补全器
│   │         (02b 方法体插入, 三道防线: 签名返回类型验证/跨组合冲突检测/单body插入)
│   │   ├── [v19.114] fix_semantic_decl.py ← BC3 语义名声明侧修复器
│   │   │     (路径A 官方名 / 路径B 02b指纹 → 声明侧改名, 诊断: 50丢失+47重载 → 方法级战场)
│   │   └── [v19.114] fix_map_gap_classes.py ← BC4 未映射类 Rule E 指纹配对器
│   │         (187 类 → 6 铁证映射 + 181 人工清单, 拆分/合并冲突检测)
│   │   ├── [v19.114] fix_type_aware_calls.py ← Phase B 类型感知调用点修复器
│   │   │     (javac location 类型 + supplement 铁证白名单 + 声明侧三防线: 声明判别/重载唯一/行内唯一)
│   │   ├── [v19.114] fix_type_fingerprint.py ← B3 02b 字面量指纹自动配对器
│   │   │     (rev 查混淆类 → 02b 指纹 → supplement 写入/升级 + 调用点改名)
│   │   └── [v19.114] fix_varn_clean.py ← Phase C varN 清理器
│   │         (R1 catch→e / R2 for索引→i/j/k / R3 getter回传, 作用域替换四防线)
│   │   ├── [v19.110] obfuscation_fingerprint.py (analyze/) ← 混淆器指纹分析
│   │   ├── [v19.111] fernflower_02b.py (analyze/) ← FernFlower 第二反编译源
│   │   │     (02b-decompiled/ 交叉验证 CFR 缺陷: 丢方法/错标字段)
│   │   │     (判定 ProGuard + 配置推断, 10 指纹)
│   │   ├── [v19.110] fix_order_align.py ← 保序对齐映射补全 (原型, javap签名校验待加)
│   │   ├── [v19.108 管线链核心] fix_method_pair_generic.py ← 通用四层指纹配对器
│   │   │     (02/03 类对 → 字面量≥2→≥1→token LCS→行号回归 → 映射 + CSV调用点修复)
│   │   ├── [v19.108] fix_playerstate_methods.py ← PlayerState 四层指纹 (同模式首发)
│   │   ├── [v19.108] fix_gameutils_names.py ← GameUtils 广播名两轮管线 (声明→JSON→调用点)
│   │   ├── [v19.108] fix_unitturret_fields.py / fix_weaponconfig_fields.py /
│   │   │     fix_actions_d_fields.py ← 字段保序 1:1 同步 (CSV行号驱动)
│   │   ├── [v19.108] fix_enhanced_for_cast.py ← enhanced-for 泛型擦除 cast
│   │   ├── [v19.108] fix_netengine_batch.py ← NetEngine 已验证符号批量替换
│   │   ├── [v19.108] fix_customsounds_broadcast.py ← units.y/custom.y 双广播
│   │   ├── [v19.110] runtime_patch_batch.py ← 运行时patch批量管线 (03→02反向→javac→patch目录)
│   │   ├── [v19.110] fix_test_family.py ← 测试族调用点批量修复 (AStarSearch拆分/FastNodeQueue新建)
│   │   ├── [v19.110] fix_test_patch.py ← 测试族反向patch (platform.net→a.a, 冲突jar+stub, 追加安装)
│   │   └── [v19.110] fix_patch_expand.py ← 主链路扩展 (字段保序zip+分组二分, --only-group/--list)
│   │   ├── [v19.133f26] fix_nodepool_batch.py ← NodePool 家族 (02b gameFramework/k/m: d→UnitList/e→NodeQueue 字段幻觉/void m()→NodePool() 构造器还原 F65/WebAPIClient→AStarNode, --dry-run/--apply)
│   │   ├── [v19.133f27] fix_treedecoration_batch.py ← TreeDecoration 家族 (02b units/al: 静态 n()→b() L29/al.a→本类静态/(Paint)null×2/ao.a→MovementTypeEnum.a/bR.n→bR.a L293/WaterUnit→MovementController 覆写签名/isOnScreen 补建 Q() L232 + 幻觉抽象 setTeamInternalById 删除 javap 铁证 + 广播 2 调用点, --dry-run/--apply)
│   │   ├── [v19.133f28] fix_actionwrapper_batch.py ← ActionWrapper 家族 (02b game/units/a/g 委托类: a()→getDescription 覆写 s L110 抽象/a(GameAction)→super.getDescription/d(am)→getDisplayString(am)/d()→getDisplayString()/equals→super.equals/j()·h(am)→Texture/构造器删幻觉/compareTo→this.getDescription 链 F69-F70, --dry-run/--apply)
│   │   ├── [v19.133f29] fix_settingsengine_batch.py ← SettingsEngine 家族 (02b gameFramework/SettingsEngine 同名直译: bT.al→allBindings×2/digitToKeycode 委托×3/KeyBinding.b→isActive/utility.ab 配置解析器/raw for-each 显式迭代×2 F17/CommandController→AndroidUIHelper 拆分裁决 F71/filesystem.a 全限定/FileLoader.f→isEnabled3, --dry-run/--apply)
│   │   ├── [v19.133f30] fix_weaponconfig_batch.py ← WeaponConfig 家族 (02b custom/as 直译 + javap 双分组铁证: s.values()/t 字段还原×8/AttackWaypoint$N→WeaponConfig$N 19 文件改名/$13·15·17 extends TextStream→ax/ax→at/静态块 s+t.put/孤儿 WeaponConfig$2 删除 F72-F73, --dry-run/--apply)
│   │   ├── [v19.133f31] fix_tilesetdef_batch.py ← TilesetDef 家族 (02b game/b/j 直译: 循环变量污染 i→tileCount/map.j.a→本类静态×4/tilePixelHeight 幻觉→this.o.tilePixelHeight×2/TileEntry tilesetIndex·animationDuration→textureLoaded·texture/raw Map.Entry for-each F17 F74-F75, --dry-run/--apply)
│   │   ├── [v19.133f32] fix_teamcolortexture_batch.py ← TeamColorTexture 家族 (02b gameFramework/m/h 直译: BitmapFont→y×3/rendering.h.C()→本类 C()/H.a(this)→setRenderTarget(Texture)/a(Shader)→setRenderTarget×5/补 Texture.i() F60/PlayerState.getActivePlayers→b/teamColorC→c×3 F76, --dry-run/--apply)
│   │   ├── [v19.133f33] fix_buildqueueaction_batch.py ← BuildQueueAction 家族 (02b game/units/a/v 直译: PacketBuilder a→UnitTypeHandle/getCreditCost 返回类型/am.c→UnitInstance.c/StopAction→UnitType×6/u.b→ActionTargetType.b/t.e→ActionCategory.e/super.isInstantAction→super.g F77, --dry-run/--apply)
│   │   ├── [v19.133f34] fix_androiduihelper_batch.py ← AndroidUIHelper 家族 (02b appFramework/c+c$2 直译: 补 GameUtils.k 文件名提取/object 数组强转/b→dialogHelper×3/Looper stub 遮蔽→new Handler()+run() F78/c.c→本类/l.aU→GlobalState.aU/FileLoader.isEnabled4/e.f→AppState.f/$2 FileAccessFlags·AndroidUIHelper·GameActivity/字符串还原 F79, --dry-run/--apply)
│   │   ├── [v19.133f35] fix_rallygroup_batch.py ← RallyGroup 家族 (02b game/a/l 直译 + o/h 覆写链: update/reset 误名→c/a 还原×6/BaseZoneStage→InputNetStream/y3→y2 变量污染/k2.g()→readFloat/R.bm→zoneQueue/super.destroy→super.p/StopAction→UnitType×3/补 AIStrategyNode.a(OutputNetStream) F80, --dry-run/--apply)
│   │   ├── [v19.133f36] fix_keybinding_batch.py ← KeyBinding 家族 (02b gameFramework/ad+af 直译: textureId()→a()/bindingName 字段当方法→this.a 委托链×3/ag2.a→textureId×3/KeyBindings.d→SlickToAndroidKeycodes.a/补 TextureCache b·c·d 字段 F81, --dry-run/--apply)
│   │   ├── [v19.133f37] fix_audiobackend_l_batch.py ← audio/backend/l 家族 (02b java/audio/a/l 同构直译: AudioListener b→AudioManager/b.a(int)→b.d(int) 02b i L356/--b.a→--b.sampleRate F82, --dry-run/--apply)
│   │   ├── [v19.133f38] fix_hoverunit_batch.py ← HoverUnit 家族 (02b game/units/l 直译: getDescription 抽象覆写 L42/f()·e() 返回 ActionCategory·ActionTargetType L54/L58/Factory.L() 等价链×3 02b h L498/UnitCategory.b/UnitBehaviorEnum.getName×2 F83, --dry-run/--apply)
│   │   ├── [v19.133f96] fix_gltextureregion_actionbinding_batch.py ← GLTextureRegion 去 final + ActionBinding throws bo (synthetic final 去 final×5 之一)
│   │   ├── [v19.133f96] fix_bytearraypacketbuilder_io_batch.py ← ByteArrayPacketBuilder 覆写方法 IOException 内部消化
│   │   ├── [v19.133f96] fix_bytearraypacketbuilder_textstream_batch.py ← ax2.a()/ax2.b() 局部消化
│   │   ├── [v19.133f96] fix_netengine_bytearray_catch_batch.py / fix_netengine_serversync_catch_batch.py / fix_netengine_command_catch_batch.py ← NetEngine 3 处 catch 还原 IOException (02b 铁证 F87)
│   │   ├── [v19.133f96] fix_serializetostream_throws_batch.py ← UnitInstance/UnitType/CustomUnitType 覆写链补 throws (F85)
│   │   └── [v19.133f96] fix_outputnetstream_override_throws_batch.py ← 全库 21 处 a(OutputNetStream) 覆写批量补 throws (F84/F89)
│   │   ├── [v19.133f97] fix_webapiclient_b1_batch.py ← WebAPIClient 12 条清零 (幻觉 reset2 删除 + 4 方法补 throws + var13_18 两步还原, 02b j/n 铁证, B1 首发)
│   │   ├── [v19.133f98] build_reverse_jar.py ← B3/B4 全量反向构建器 (03 语义源码→混淆名→javac→game-lib-reverse.jar: 宿主感知 per-class 成员映射+方法字段命名空间隔离+枚举字符串恢复 (02b 对齐)+原生绑定方法豁免+strip_unused_imports 前置+类包同名 JLS 限制跳过 (build-skip.txt 136 文件 jar 原样合并)+keep_cls 部分反向+迭代收敛; -source/-target 8 兼容官方启动器 JRE 8 (v19.133f98 JNI 修复: 原 class 57 致 exe 启动失败); --apply/--skip-compile; 输出 build/reverse-src|reverse-classes|game-lib-reverse.jar)
│   │   ├── [v19.133f97] fix_opengl_aa_final_batch.py ← opengl 家族 synthetic final 去 final×13 + throws 补链连锁 (utility/audio/slick/input/CustomActionBase 覆写 try-catch, javap 铁证 F88/F91)
│   │   ├── [v19.133f97] fix_reliablesocket_throws_batch.py ← ReliableSocket 39 条 throws 清零 (Socket 覆写标准签名 + 内部链, 独立重建库)
│   │   ├── [v19.133f97] fix_reliableserversocket_throws_batch.py ← ReliableServerSocket 12 条 throws 清零 (ServerSocket 覆写标准签名)
│   │   └── [v19.133f97] fix_reliableprofile_throws_batch.py ← ReliableProfile throws 清零 (a(String,int,int,int) 链)
│   │   ├── [v19.133f25] fix_datafieldcollector_batch.py ← DataFieldCollector 家族 (03 重建类: f→DataFieldProvider/n→PlayerState/raw cast F17/DataFieldFloat 合成访问器 F63/LogicBoolean 静态格式化等价 F64, --dry-run/--apply)
│   │   ├── [v19.133f24] fix_audiomanager_batch.py ← AudioManager long 键家族 (02b java/audio/a/i: b int[]→long[] 全链同步 F62/cast 模式×7/方法签名 7 个/h-i 方法体/c(long,Object) 索引, --dry-run/--apply)
│   │   ├── [v19.133f23] fix_debugui_batch.py ← DebugUI 家族 (02b librocket/scripts/Debug.java: GameObject.dK/bF() raw cast×4/补 UnitInstance.cm() F60/bX.a→registerRelayServer F26/补建 platform/net/b.java, --dry-run/--apply)
│   │   ├── [v19.133f22] fix_mapspawn_batch.py ← MapSpawn 家族 (02b game/b/a: map.a.a→本类静态/NodeList cast/字段误调用×4/x.h→EmptyArrays.emptyStringArray/LocalizedString.b→getLocalizedText/ad.g→NetEngine.g, --dry-run/--apply)
│   │   ├── [v19.133f20] fix_ajava_family_batch.py ← a.java 家族 (6 文件 27 条: platform/a 静态方法误写构造器/config/a CFR 变量污染/mods/a ModLoader.m→getModsDirectoryPath/ModLoadEntry VersionChecker→a/补建 java/input/a\$1, 02b 锚点, 幂等, --dry-run/--apply)
│   │   ├── [v19.133f19] fix_network_scatter_batch.py ← network 散件批1 (12 文件 34 处: OutputNetStream au 字段/ChatSystem f→GameUtils/ChatMessage 内部类/NetworkUtils game/i=GameEngine/ShaderUniform$1 isDirty→d 等, 02b 锚点, --dry-run/--apply)
│   │   ├── [v19.133f19] fix_network_round2_batch.py ← network 散件批2 (HttpClientPool 接口化/KeepAliveTimer+PasswordManager 调用点 + 删 ThemeFontEntry$1/2/3 枚举匿名子类重复文件)
│   │   ├── [v19.133f18] fix_checksumcalculator_batch.py ← ChecksumCalculator 家族 (02b j/ak: 17 处幻觉类/强转逐对还原 w→GameObject/MasterServerCreate→UnitType/SendWorker→CommandCenter/NetworkPacket→WeaponAction/WebAPIClient→PlayerState, --dry-run/--apply)
│   │   ├── [v19.133f17] fix_intarray_batch.py ← IntArray$N 家族 (02b j/ad$N NetEngine 内部类: extends panels.k 抽象类 javap 铁证/幻觉类逐项还原/补建 IntArray$5$1, --dry-run/--apply)
│   │   ├── [v19.133f16] fix_playerconnect_batch.py ← PlayerConnect 家族 (02b j/c.java 一体类拆分: PlayerConnect 20 处/PacketDecoder 3 处/NetEngine 补静态 i(String) L2966-3009, game.e→HumanPlayer 铁证, --dry-run/--apply)
│   │   ├── [v19.133f10] fix_commands_batch3.py ← $N 家族 extends 误映射 (f$1/p$1/v$1/q$1/q$2→AbstractBuildAction/GameAction) + a()/b()/c() 语义名 (02b $N 逐方法锚点)
│   │   ├── [v19.133f10] fix_commands_batch4.py ← ResourceComponent 误建副本裁决 → CustomActionBase 广播 (02b custom/d/b L17 m 铁证)
│   │   ├── [v19.133f10] fix_commands_batch5.py ← UnitActionHelper 家族 (02b d/q: F25 $N 数字污染/a(GameAction,...) 参数)
│   │   ├── [v19.133f10] fix_commands_batch6.py ← $N 家族 a()/b()/c()→getDescription/getLabel/getResourceCost 批量改名
│   │   ├── [v19.112] fix_teamtagset_merge.py ← TeamTagSet→UnitConfig 双译名统一广播 (幂等精确替换)
│   │   └── [v19.112] fix_03_semantic_methods.py ← 语义方法补全 (supplement映射+02b方法体+类型白名单翻译+已有声明检查, --class/--methods/--only-extra)
│   │   ├── capture/          — 运行时捕捉工具链 (v19.113 新增) [实际路径: tools/capture/, 结构树历史误标 fixers/capture]
│   │   ├── debug_client.py     ← 调试服务器客户端 (签名白名单+String桥接+心跳)
│   │   ├── script_api.json     ← Debug/Root 222 方法签名清单 (02b 提取)
│   │   ├── save_diff_align.py  ← 存档差分对齐 v2 (循环感知模板+am段四连定位)
│   │   ├── sandbox_launch.sh   ← 一键沙盒启动链 (Attacher+agent编译打包)
│   │   └── agent/              ← javaagent 注入任务 71 件套 (Sandbox 7-13 开局/核弹/存档NPE; Ind 18-21 dF验证; Place 22-26 放置差分; Scope 27 名称表; Replay 28-32 回放采样; Live 33-38 实时AI+经济轨迹; Lobby 39-47 大厅+自动加入+全属性dump)
│   ├── [R4循环] extends_fix / fix_all_ctors_v2 / fix_keyword_methods /
│   │   │     fix_malformed_signatures / fix_final_ctor / fix_void_ctor /
│   │   │     fix_ctor_first / fix_enum_ctors / fix_duplicate_defs /
│   │   │     fix_numeric_literals / fix_inner_all / merge_lost / field_align 等
│   │   └── package_renamer.py      ← 包级重命名 (解决game-lib.jar冲突) [rwlib]
│   ├── _archive/        — 归档脚本 (33, 含v12批量批次)
│   ├── resolvers/       — 消歧 (1): mark_ambiguous.py [rwlib]
│   ├── utils/           — 工具 (18)
│   │   ├── split_mappings.py   ← 12域拆分 [rwlib]
│   │   ├── verify_ini_params.py ← INI参数验证 [rwlib]
│   │   ├── method_catalog.py   ← 方法目录生成 [rwlib]
│   │   ├── classdump.py        ← .class二进制解析 (独立工具)
│   │   ├── gamelib_audit.py    ← 05-gamelib 语义名审计 (v19.85, 重生成 unresolved.txt)
│   │   ├── class_evidence.py   ← 字节码证据提取 (v19.87, 并行 javap: super/接口/字符串)
│   │   ├── identify_readable.py ← Rule E 反向字符串指纹匹配 (v19.88)
│   │   ├── replay_parser.py    ← 回放命令流解析器 (v19.95, v19.97 d子对象子流修复)
│   │   ├── debug_script.py     ← DebugServer 客户端 (v19.96, script 命令/--wait)
│   │   ├── parse_rc_v96.py     ← v96 命令写侧格式字段级对照 (v19.97)
│   │   ├── dump_rc.py          ← 回放 rc 原始字节 dump (v19.97)
│   │   ├── gen_error_list.py ← 剩余错误总清单生成器 (v19.124: compile-errors.csv → docs/deobfuscation/剩余错误总清单-5283.md, 669 文件全分组/类型/符号归类, 可重生成)
│   │   ├── b2_reverse_map_check.py ← [v19.133f98] B2 反向映射核对 (jar 1,698 ↔ 03 1,739, 输出 b2-*.csv 至 mappings/generated/, 可重生成; 产物: b2-jar-to-03-map/b2-jar-cover/b2-gaps/b2-03-reverse/b2-extra)
│   │   ├── comment_audit.py ← [v19.133f98] 注释覆盖率审计 (总行/注释行/注释率 + 类头/类声明前/方法/字段注释率 → build/comment-audit.csv, 可重生成)
│   │   ├── runtime_evidence.py ← 运行时日志证据提取 (v19.90)
│   │   ├── backfeed_03.py / map_scriptengine.py ← 映射反哺 03 / 脚本引擎映射
│   │   └── update_doc_v19.107_stage2.py ← [v19.107] 一次性: 会话记录追加脚本化批量修复阶段 (stage2)
│   └── gates/stubs/     — 编译桩 (javac_gate自动生成, gitignored)
│
└── rwlib/              — 共享 Python 库 (4)
    ├── bytecode.py     ← CJK安全javap封装 (P0.1)
    ├── config.py       ← 路径/JDK配置 + find_javap/find_javac
    ├── mappings.py     ← supplement.csv读写 (csv.reader, v18.2修复)
    └── (v19.133f98 删除 utils.py — 0 引用死代码, 共享库 v1.1)
```

