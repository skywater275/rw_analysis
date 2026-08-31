# 待定信息 — 未确认项集中存放

> v19.133f98 | 2026-09-04 | 所有不确定/待验证/待裁决信息集中于此, 禁止散落在域文档
> 流转规则: 入 = 会话中立即登记; 出 = 裁决后结论写入域文档 + 映射 notes, 本条删除
> 历史已决战役记录已清理 (结论沉淀于域文档「战役裁决与发现附录」+ mappings/ notes)

## §1 待定类身份 (身份有争议/缺证据)

| 项 | 候选 | 证据状态 | 下一步 |
|----|------|---------|--------|
| GameFlagImpl 继承结构 | 02 b.java 是空类 (extends a) | 方法全在父类 02 game/a.java | 核对 03 GameFlag/GameFlagImpl 是否缺失父类方法 |
| effects vs logicBooleans 双包 | 两个 LogicBoolean 均为真类 | 02 只有 logicBooleans 包; 03 effects 包对应 02 的 e/ 子包体系 | 02 effects 内容定位 (custom/e 子包?) |
| actions.base vs resources.CustomActionBase | 双包同名 | 02 只有一个 custom/d.b | 调用点逐行 02 锚定归属 |
| read(CustomUnitType) 宿主 | x.eW 字段类型类的 read 方法缺失 | 02 j.java 同写法, eW 类型在 03 声明丢失 | 定位 02 custom/x.java 的 eW 字段类型 |
| f→GameRenderer 条目疑误标 | 03 GameRenderer 02 真名待查 | import ConnectionState 指纹 | class-discoveries 复核 |
| 03 UnitFlag enum 身份 | UnitInstance:504 调用 UnitFlag.a()/a(UnitInstance) | 02b 对应类待查 | javap 枚举对照 |
| projectiles 包名 | CustomUnitType L1774 引用 com...game.projectiles.a | 02 真包待查 | 02 import 对照 |

## §2 未解析混淆类

- 真实未解析: **162** (清单: mappings/generated/unresolved.txt, gamelib_audit.py 重生成)
- 仲裁候选: mappings/generated/arbitration-candidates.csv (已裁决: aa→GroupController / g→PlatformDetector)

## §3 待验证假设

| # | 假设 | 验证方法 |
|---|------|---------|
| 1 | 03 缺失的 PlayerState 方法是被 mapper 合并而非真丢失 | 四层配对未覆盖的 02 方法逐个在 03 全文件搜指纹 |
| 2 | 幻觉命名系统性残留 (编译自洽但语义错) | 独立战役: 02b 字符串/签名对照全库复核 |

## §4 映射冲突留档

- 详见 mappings/generated/arbitration-candidates.csv
- supplement.csv 幻觉名条目 (待废弃): j/ad.h(String)→showError (与字节码 iconst_1 矛盾, v19.108 发现)

## §5 当前残余 (B5/B5.6 阶段)

| # | 项 | 状态 |
|---|----|------|
| 1 | **GUI/回放深度验证** | headless 已通 (启动/开局/建单位/存档/AI); GUI 渲染/音频线程与回放同步性 (ChecksumCalculator) 未深测 — 待确认 |
| 2 | **撞车剔除 500 项残余验证** | skip_global 宿主优先已修; 其余撞车剔除成员运行时风险待逐个验证 |
| 3 | ~~映射验证战役 (B5.6): 726 可疑映射~~ | ✅ 逆1-逆4d 已闭环 (2026-08-31): 726 → 复核恢复 304+32 → **390 真可疑 → 334 死映射删除 + 7 构造器恢复 + 15 宿主迁移 + 19 保持注记**; **suspicious 现存 19 条** (均带逆4d 分析注记, 见下) |
| 4 | ~~unverified 49.3%~~ | ✅ 逆1/逆2 已处理 (2026-09-04): 726 复核 304 恢复+32 重分类; 空 verified 387 全清零 (列错位根因) |
| 5 | 幻觉命名独立战役 | 编译自洽但语义待还原 (B5 收敛后长尾) |
| 6 | NetEngine.t 字段 (UDP 广播端口) | javap 存在但 02b 无赋值点 — 默认 0, 语义待验证 |
| 7 | EffectConfig 引用核对 | 03 全树 EffectConfig 引用 vs 02 双类归属 (OutputNetStream 周边/hud 链) |
| 8 | ~~verified 列垃圾值 528 条~~ | ✅ 逆5a 已清零 (2026-08-31): 字符扫描重建 510 (ini 恢复 211 + exists 258 + suspicious 38) + 18 截断行删除留档; **垃圾 verified 现存 0** |
| 9 | ~~19 条保持 suspicious~~ | ✅ 逆5b 已处理 (2026-08-31): 7 修复 (gScore→AStarNode / ResourceRate 家族 / 寻路网格) + 4 删除 + 15 保持注记 (4 条 MapLayerRenderer 纹理同名族 + SteamAPI 异域等, 语义宿主 03 无声明) |
| 10 | **15 条保持 suspicious** (逆5b 终态) | 均带逆5b 注记: 4 条 MapLayerRenderer 纹理同名族 (startTileX/Y/endTileX/Y — A* 语义宿主 03 无此名, 疑 AStarSearch 字段异名) + resourceTypeRef×2/priorityLevel/backgroundColor 等异域或多候选 — 需 02b 语义深挖 (收益低, 可长期挂起) |

## §6 未解析混淆类 (2026-09-04 刷新)

- unresolved.txt 重生成: 124 未映射 + 65 缺失 = 189 (gamelib_audit jar 遍历修复后)
- 其中 obfuscated-name ~118 个: 大部分为**有意保留** (v19.115-129 战役家族清零后的
  短名残留 + $N 内部类 — 03 已短名共存, 命名收益低); 高价值类已命名
  (PlayerNameFormatter/ReplayFolderManager/ReplayComparator/StorageFolderPicker 等)
- 剩余命名候选: 方法签名级深挖 (appFramework.p 桥接子类无字符串等)

## §7 文档待建

- 11-platform 域文档: 按键/输入/Steam/LibRocket 四类文档待补 (占位 README + 战役附录已有)
