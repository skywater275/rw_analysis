#!/usr/bin/env python3
"""
tools/manager.py — 工具管理器: 列出/检查/状态/运行

Usage:
    python tools/manager.py list              — 列出所有工具及描述
    python tools/manager.py check             — 全面健康检查
    python tools/manager.py status            — 项目状态报告
    python tools/manager.py run <tool-name>   — 运行指定工具
"""
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]  # tools/ → 项目根
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))

import ast, csv, io, os, re, subprocess, json, time
from collections import defaultdict, Counter

sys.stdout.reconfigure(encoding="utf-8", errors="replace")  # 输出统一 UTF-8 (v19.133f98 整理)
csv.field_size_limit(10 * 1024 * 1024)

# ── 工具注册表 ──────────────────────────────────────────────
TOOLS = {
    # core/ — 核心引擎
    "apply_enhanced": {
        "path": "tools/core/apply_enhanced.py",
        "desc": "6阶段反混淆主引擎 (类→导入→类型→字段→方法→内部类→extends)",
        "category": "core",
        "writes": True,
    },
    "cross_validate": {
        "path": "tools/core/cross_validate.py",
        "desc": "字节码交叉验证 (javap对比, 输出descriptors.json + unmapped-bytecode.csv)",
        "category": "core",
        "writes": True,
    },
    "sig_renamer": {
        "path": "tools/core/sig_renamer.py",
        "desc": "签名驱动方法重命名 (声明+调用点, 输出rename-audit.csv)",
        "category": "core",
        "writes": True,
    },
    "type_renamer": {
        "path": "tools/core/type_renamer.py",
        "desc": "类型引用位置重命名 (extends/cast/instanceof/new/泛型/字段/返回类型)",
        "category": "core",
        "writes": True,
    },
    # gates/ — 门禁
    "javac_gate": {
        "path": "tools/gates/javac_gate.py",
        "desc": "编译门禁 (javac编译03-deobfuscated, 含auto-stubs, 输出compile-errors.csv)",
        "category": "gate",
        "writes": True,
    },
    "stats": {
        "path": "tools/gates/stats.py",
        "desc": "状态报告自动生成器 (→ docs/STATUS.md)",
        "category": "gate",
        "writes": True,
    },
    "comprehensive": {
        "path": "tools/gates/comprehensive.py",
        "desc": "覆盖率分析报告 (映射密度/字节码覆盖Top/Bottom)",
        "category": "gate",
        "writes": False,
    },
    # fixers/ — 修复
    "extends_fix": {
        "path": "tools/fixers/extends_fix.py",
        "desc": "单字符extends/implements修复 (含MANUAL_OVERRIDES表)",
        "category": "fixer",
        "writes": True,
    },
    "fix_ctors": {
        "path": "tools/fixers/fix_all_ctors_v2.py",
        "desc": "匿名内部类构造器通用修复 (4种畸形模式)",
        "category": "fixer",
        "writes": True,
    },
    "fix_keywords": {
        "path": "tools/fixers/fix_keyword_methods.py",
        "desc": "关键字作方法名修复 (boolean/int/float→启发式命名)",
        "category": "fixer",
        "writes": True,
    },
    "fix_signatures": {
        "path": "tools/fixers/fix_malformed_signatures.py",
        "desc": "畸形方法签名修复 + 全局残留扫描",
        "category": "fixer",
        "writes": True,
    },
    "keyword_scan": {
        "path": "tools/fixers/keyword_collisions.py",
        "desc": "Java关键字冲突扫描 + MovementController特化修复",
        "category": "fixer",
        "writes": True,
    },
    "package_renamer": {
        "path": "tools/fixers/package_renamer.py",
        "desc": "包级重命名 (移动文件+改package声明+重写import)",
        "category": "fixer",
        "writes": True,
    },
    "add_class_names": {
        "path": "tools/fixers/add_class_names.py",
        "desc": "批量添加类名映射到class-discoveries.csv (一次性)",
        "category": "fixer",
        "writes": True,
    },
    # ── [v19.116] ModLoader/UnitRegistry 战役 ──
    "fix_unitregistry_batch1": {
        "path": "tools/fixers/fix_unitregistry_batch1.py",
        "desc": "[v19.116] UnitRegistry 重建 (v19.115s 误删勘误: ResourceUnit→UnitRegistry git mv + 107文件广播 + 依赖补缺, --dry-run/--apply)",
        "category": "fixer",
        "writes": True,
    },
    "fix_modloader_batch2": {
        "path": "tools/fixers/fix_modloader_batch2.py",
        "desc": "[v19.116] ModLoader 类型族 (n缓存/DirectionType误标/am+ar遮蔽/actions.f→ActionRegistry改名, --dry-run/--apply)",
        "category": "fixer",
        "writes": True,
    },
    "fix_modloader_batch3": {
        "path": "tools/fixers/fix_modloader_batch3.py",
        "desc": "[v19.116] ModLoader 批3 (CurveType→EffectConfig/TagFilter→UnitConfig/ao→MovementTypeEnum + 依赖补缺)",
        "category": "fixer",
        "writes": True,
    },
    "fix_modloader_batch4": {
        "path": "tools/fixers/fix_modloader_batch4.py",
        "desc": "[v19.116] ModLoader 批4 回归修复 (object22/object1722/object18 + EffectManager补方法 + L1909行重写)",
        "category": "fixer",
        "writes": True,
    },
    "fix_modloader_batch5": {
        "path": "tools/fixers/fix_modloader_batch5.py",
        "desc": "[v19.116] ModLoader 批5 (cp-cq→EffectManager/cI if链/r→Modifier/bo2语义名/CAB.k类型)",
        "category": "fixer",
        "writes": True,
    },
    "fix_modloader_batch6": {
        "path": "tools/fixers/fix_modloader_batch6.py",
        "desc": "[v19.116] ModLoader 批6 (13行 action 解析链按 02b L3222-3234 + base 11文件 7参a() d全限定)",
        "category": "fixer",
        "writes": True,
    },
    "fix_modloader_batch7": {
        "path": "tools/fixers/fix_modloader_batch7.py",
        "desc": "[v19.116] ModUnitRegistry 收尾 (implements AttackWaypoint 删除/x()/CurveType→d静态/ModInfo k(long)+m())",
        "category": "fixer",
        "writes": True,
    },
    # ── [v19.117] GameEngine 清零战役 ──
    "fix_gameengine_batch": {
        "path": "tools/fixers/fix_gameengine_batch.py",
        "desc": "[v19.109] GameEngine 早期广播修复 (02 game/i.java 锚点, 管线链首发)",
        "category": "fixer",
        "writes": True,
    },
    "fix_gameengine_batch1": {
        "path": "tools/fixers/fix_gameengine_batch1.py",
        "desc": "[v19.117] GameEngine 战役批1: init 方法族 + 手动实例类型修正 (02b i.java 对照)",
        "category": "fixer",
        "writes": True,
    },
    "fix_gameengine_batch2": {
        "path": "tools/fixers/fix_gameengine_batch2.py",
        "desc": "[v19.117] GameEngine 战役批2: 方法族 (CrashHandler/onScreenStart/W()/b(int,int)/b(boolean)/K()/endG)",
        "category": "fixer",
        "writes": True,
    },
    "fix_gameengine_batch3": {
        "path": "tools/fixers/fix_gameengine_batch3.py",
        "desc": "[v19.117] GameEngine 战役批3: 依赖补缺 (MapException/byte cast/bF 字段/UnitRegistry 常量)",
        "category": "fixer",
        "writes": True,
    },
    "fix_gameengine_batch4": {
        "path": "tools/fixers/fix_gameengine_batch4.py",
        "desc": "[v19.117] GameEngine 战役批4: 收尾 (bF 字段/bV+player/ci()/dc()/ce.q/b(float,float)/selectionBoxPaint)",
        "category": "fixer",
        "writes": True,
    },
    "fix_gameengine_batch5": {
        "path": "tools/fixers/fix_gameengine_batch5.py",
        "desc": "[v19.117] GameEngine 战役批5: 回归批4 + 收尾 (GameStateEnum2/ProjectileType2 删除/textPaint)",
        "category": "fixer",
        "writes": True,
    },
    "fix_gameengine_batch6": {
        "path": "tools/fixers/fix_gameengine_batch6.py",
        "desc": "[v19.117] GameEngine 战役批6: 中间收尾 (ProjectileType2 全限定/GameHUD.a/g()→cameraController/StatisticType)",
        "category": "fixer",
        "writes": True,
    },
    "fix_gameengine_batch7": {
        "path": "tools/fixers/fix_gameengine_batch7.py",
        "desc": "[v19.117] GameEngine 战役批7: 收尾批2 (ProjectileType2 比较/EffectConfig cast/c(String,String)/GameUtils.f)",
        "category": "fixer",
        "writes": True,
    },
    "fix_gameengine_batch8": {
        "path": "tools/fixers/fix_gameengine_batch8.py",
        "desc": "[v19.117] GameEngine 战役批8 (bX.c(float) 错译修正/aj()+waterTexture3() 错译删除/a(StatisticType,StatsGrouping)+findNextMapLevel 补)",
        "category": "fixer",
        "writes": True,
    },
    "fix_gameengine_batch8b": {
        "path": "tools/fixers/fix_gameengine_batch8b.py",
        "desc": "[v19.117] gameFramework/GameEngine.java 误命名清理 (CommandPathPart 损坏副本删除/k.fulator→PathCostCalculator/5 引用点修正)",
        "category": "fixer",
        "writes": True,
    },
    "fix_gameengine_batch9": {
        "path": "tools/fixers/fix_gameengine_batch9.py",
        "desc": "[v19.117] GameEngine 战役批9: updateAllGame/drawAll 循环家族 (onScreenStart 误名族 6 方法还原 + 内部调用 15 处)",
        "category": "fixer",
        "writes": True,
    },
    "fix_gameengine_batch10": {
        "path": "tools/fixers/fix_gameengine_batch10.py",
        "desc": "[v19.117] GameEngine 战役批10: drawAll/updateAllGame 调用点 (42 调用点修正 + NetEngine.a(float,boolean)/Renderer.c())",
        "category": "fixer",
        "writes": True,
    },
    "fix_gameengine_batch11": {
        "path": "tools/fixers/fix_gameengine_batch11.py",
        "desc": "[v19.117] GameEngine 战役批11: 残余 32 清零 (o2.headNode/TeamColorTexture.G/clearScreen/w→EffectConfig)",
        "category": "fixer",
        "writes": True,
    },
    # _archive/v12/ — 已归档批量生成
    "p3_infer": {
        "path": "tools/_archive/v12/p3_infer.py",
        "desc": "P3.1: 从JVM描述符自动推断占位名 (value3/flag0/e1模式)",
        "category": "fixer",
        "writes": True,
    },
    "p3_deep_curate": {
        "path": "tools/_archive/v12/p3_deep_curate.py",
        "desc": "P3.3: 深度精修占位名 (per-class, JVM类型+上下文)",
        "category": "fixer",
        "writes": True,
    },
    "p5_safe_rename": {
        "path": "tools/_archive/v12/p5_safe_rename.py",
        "desc": "P5.1: 安全方法重命名 (非重载方法, 声明+this/super调用点)",
        "category": "fixer",
        "writes": True,
    },
    "resolve_todosig": {
        "path": "tools/_archive/v12/resolve_todosig_v3.py",
        "desc": "TODO-SIG消歧v3 (javap签名精确匹配)",
        "category": "fixer",
        "writes": True,
    },
    "batch_ini": {
        "path": "tools/_archive/v12/batch_ini_params.py",
        "desc": "从游戏INI文件提取参数→supplement.csv字段映射",
        "category": "fixer",
        "writes": True,
    },
    "batch_actions": {
        "path": "tools/_archive/v12/batch_actions.py",
        "desc": "GameAction子类通用接口方法批量映射",
        "category": "fixer",
        "writes": True,
    },
    "batch_rendering": {
        "path": "tools/_archive/v12/batch_rendering.py",
        "desc": "08-rendering域批量映射 (TextureManagerInterface等)",
        "category": "fixer",
        "writes": True,
    },
    "batch_utility": {
        "path": "tools/_archive/v12/batch_utility.py",
        "desc": "12-utility域批量映射 (StatsPanel/GameUtils等)",
        "category": "fixer",
        "writes": True,
    },
    "batch_custom_j": {
        "path": "tools/_archive/v12/batch_custom_j_final.py",
        "desc": "CustomUnitType(custom.j)完整精修映射 (v14最终版)",
        "category": "fixer",
        "writes": True,
    },
    "batch_final_v15": {
        "path": "tools/_archive/v12/batch_final_v15.py",
        "desc": "v15.0集成批次 (BaseUnit/Projectile/NetEngine/MapEngine等)",
        "category": "fixer",
        "writes": True,
    },
    "batch_real_classes": {
        "path": "tools/_archive/v12/batch_real_classes.py",
        "desc": "真实类签名推断 (仅01-classes中存在者, javap签名→getter/setter)",
        "category": "fixer",
        "writes": True,
    },
    "batch_sig_infer": {
        "path": "tools/_archive/v12/batch_sig_infer.py",
        "desc": "通用签名推断 (getter/setter/is*/has*/find*/compute*模式)",
        "category": "fixer",
        "writes": True,
    },
    "p2_batch6": {
        "path": "tools/_archive/v12/p2_batch6.py",
        "desc": "P2最终类重命名 (直接操作03-deobfuscated文件)",
        "category": "fixer",
        "writes": True,
    },
    # resolvers/
    "mark_ambiguous": {
        "path": "tools/resolvers/mark_ambiguous.py",
        "desc": "歧义方法标记 (无签名method行→[TODO-SIG]注解)",
        "category": "resolver",
        "writes": True,
    },
    # utils/
    "split_mappings": {
        "path": "tools/utils/split_mappings.py",
        "desc": "按12游戏功能域拆分supplement.csv→mappings/domains/",
        "category": "util",
        "writes": True,
    },
    "verify_ini": {
        "path": "tools/utils/verify_ini_params.py",
        "desc": "游戏INI参数与supplement.csv交叉验证 (只读)",
        "category": "util",
        "writes": False,
    },
    "method_catalog": {
        "path": "tools/utils/method_catalog.py",
        "desc": "方法目录生成器 (签名+字符串常量+调用关系→docs/generated/)",
        "category": "util",
        "writes": True,
    },
    "classdump": {
        "path": "tools/utils/classdump.py",
        "desc": ".class文件二进制解析器 (fields/methods常量池dump)",
        "category": "util",
        "writes": False,
    },
    "fernflower_02b": {
        "path": "tools/analyze/fernflower_02b.py",
        "desc": "FernFlower 第二反编译源生成 (v19.111) — 02b-decompiled/ 交叉验证 CFR 缺陷.",
        "category": "analyze",
        "writes": False,
    },
    "match_ini_params": {
        "path": "tools/analyze/match_ini_params.py",
        "desc": "{单位名: {字段: 值}}.",
        "category": "analyze",
        "writes": False,
    },
    "obfuscation_fingerprint": {
        "path": "tools/analyze/obfuscation_fingerprint.py",
        "desc": "混淆器指纹分析 (v19.110) — 判定 game-lib.jar 的混淆工具与配置, 输出反推证据链.",
        "category": "analyze",
        "writes": False,
    },
    "debug_client": {
        "path": "tools/capture/debug_client.py",
        "desc": "签名校验调用: obj.fn(args). bridge=True 时返回值经 x=.. + logDebug(x) 回传日志.",
        "category": "capture",
        "writes": False,
    },
    "save_diff_align": {
        "path": "tools/capture/save_diff_align.py",
        "desc": "按 Saving unit 分段 → {id: (类型, 段文本)}",
        "category": "capture",
        "writes": False,
    },
    "constant_pool_renamer": {
        "path": "tools/core/constant_pool_renamer.py",
        "desc": "逆操作 (--tree-revert): 将 R3 的 kw/decl/FQN 重写逆转回混淆名。",
        "category": "core",
        "writes": False,
    },
    "fqn_importer": {
        "path": "tools/core/fqn_importer.py",
        "desc": "Apply FQN→short name replacements and add imports.",
        "category": "core",
        "writes": False,
    },
    "identity_index": {
        "path": "tools/core/identity_index.py",
        "desc": "预计算全 jar 1,698 类的常量池引用集 → class-refs.json。",
        "category": "core",
        "writes": False,
    },
    "import_rewriter": {
        "path": "tools/core/import_rewriter.py",
        "desc": "从 fwd 经验推导补录包映射: 混淆包全部映射类同包 → 包段替换。",
        "category": "core",
        "writes": False,
    },
    "inner_class_restorer": {
        "path": "tools/core/inner_class_restorer.py",
        "desc": "内部类恢复 (R4) — 02 有 693 个 $ 文件, 03 缺失 ~377 个。",
        "category": "core",
        "writes": False,
    },
    "member_revert": {
        "path": "tools/core/member_revert.py",
        "desc": "成员名回退 (R4-A) — 声明侧语义名 → 混淆名, 修复调用点级联。",
        "category": "core",
        "writes": False,
    },
    "merge_members_19": {
        "path": "tools/core/merge_members_19.py",
        "desc": "追加文本类型位置用到的简单名 → 需要补的 import FQN (语义化且 03 树有文件)。",
        "category": "core",
        "writes": False,
    },
    "restore_lost_classes": {
        "path": "tools/core/restore_lost_classes.py",
        "desc": "按手工裁决表替换文件内全部幻影名 (不依赖身份/锚点)。",
        "category": "core",
        "writes": False,
    },
    "analyze_patch_candidates": {
        "path": "tools/fixers/analyze_patch_candidates.py",
        "desc": "分析可批量 patch 的 03 文件候选 (v19.109 运行时管线).",
        "category": "fixer",
        "writes": False,
    },
    "auto_align": {
        "path": "tools/fixers/auto_align.py",
        "desc": "R7: this.X(...) 调用无声明 → 02b 方法体指纹匹配找 03 名",
        "category": "fixer",
        "writes": False,
    },
    "build_reverse_jar": {
        "path": "tools/fixers/build_reverse_jar.py",
        "desc": "删除未使用的 import (反向后简单名冲突根源: 03 全限定用法 + 冗余 import 反向后撞名)。",
        "category": "fixer",
        "writes": False,
    },
    "diff_tmi_impl": {
        "path": "tools/fixers/diff_tmi_impl.py",
        "desc": "返回 [(lineno, name, ret, params_str)]",
        "category": "fixer",
        "writes": False,
    },
    "extract_annotations": {
        "path": "tools/fixers/extract_annotations.py",
        "desc": "javap -v 提取 (成员, key) 对",
        "category": "fixer",
        "writes": False,
    },
    "extract_func_semantics": {
        "path": "tools/fixers/extract_func_semantics.py",
        "desc": "extract_func_semantics.py — 游戏自带\"反编译\"最强机制提取器 (v19.113q)",
        "category": "fixer",
        "writes": False,
    },
    "field_align": {
        "path": "tools/fixers/field_align.py",
        "desc": "field_align 工具 (无 docstring)",
        "category": "fixer",
        "writes": False,
    },
    "finish_docs_f10": {
        "path": "tools/fixers/finish_docs_f10.py",
        "desc": "v19.133f10 文档收尾: D1 口径同步 + CLAUDE.md 版本头/状态表 + 会话记录 + PLAN.md + 旧会话标注.",
        "category": "fixer",
        "writes": False,
    },
    "fix_03_batch_fill": {
        "path": "tools/fixers/fix_03_batch_fill.py",
        "desc": "03 继承链已声明的字段/方法名集合 (父类字段不算未知).",
        "category": "fixer",
        "writes": False,
    },
    "fix_03_fill_missing": {
        "path": "tools/fixers/fix_03_fill_missing.py",
        "desc": "02b 文件的 import 混淆类名集合.",
        "category": "fixer",
        "writes": False,
    },
    "fix_03_semantic_methods": {
        "path": "tools/fixers/fix_03_semantic_methods.py",
        "desc": "02b 源里按名字+参数提示找方法体.",
        "category": "fixer",
        "writes": False,
    },
    "fix_abstractcutsceneaction_batch": {
        "path": "tools/fixers/fix_abstractcutsceneaction_batch.py",
        "desc": "for (GameObject w2 : GameObject.er) {",
        "category": "fixer",
        "writes": False,
    },
    "fix_actionaddcredits_batch": {
        "path": "tools/fixers/fix_actionaddcredits_batch.py",
        "desc": "f97by ActionAddCredits 家族修复器: 02b b/d.java + b/d$1|$2 直译.",
        "category": "fixer",
        "writes": False,
    },
    "fix_actions_d_fields": {
        "path": "tools/fixers/fix_actions_d_fields.py",
        "desc": "fix_actions_d_fields — actions.d 字段调用点同步 (v19.108 Batch 3)",
        "category": "fixer",
        "writes": False,
    },
    "fix_actions_g": {
        "path": "tools/fixers/fix_actions_g.py",
        "desc": "v19.133f11 actions/g.java (02b custom/a/g) 全文对照修复 + GameAction.v() 返回类型 + UnitActionBas...",
        "category": "fixer",
        "writes": False,
    },
    "fix_actionsb_batch": {
        "path": "tools/fixers/fix_actionsb_batch.py",
        "desc": "f97bh custom/actions/b 修复器: 02b custom/a/b.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_actionsg_batch": {
        "path": "tools/fixers/fix_actionsg_batch.py",
        "desc": "f97bu custom/actions/g.java 修复器: 02b custom/a/g.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_actiontype1_batch": {
        "path": "tools/fixers/fix_actiontype1_batch.py",
        "desc": "f97j ActionType$1 三文件修复器 (混淆名撞车家族).",
        "category": "fixer",
        "writes": False,
    },
    "fix_actiontype2_l_batch": {
        "path": "tools/fixers/fix_actiontype2_l_batch.py",
        "desc": "ui/ActionType$2 混淆类型 l -> GlobalState (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_actionwrapper_batch": {
        "path": "tools/fixers/fix_actionwrapper_batch.py",
        "desc": "ActionWrapper 家族 16 条清零 (02b game/units/a/g 逐方法直译:",
        "category": "fixer",
        "writes": False,
    },
    "fix_ae_family_batch": {
        "path": "tools/fixers/fix_ae_family_batch.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_ag_family_batch": {
        "path": "tools/fixers/fix_ag_family_batch.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_ai_bw_call_batch": {
        "path": "tools/fixers/fix_ai_bw_call_batch.py",
        "desc": "AIStrategy$11/12 transportUnits.a -> isUnitTypeAllowed (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_ai_pkg_batch4": {
        "path": "tools/fixers/fix_ai_pkg_batch4.py",
        "desc": "AI 包战役批4: CombatMain/CombatAction/AIStrategyNode 方法名对齐 02b.",
        "category": "fixer",
        "writes": False,
    },
    "fix_ai_pkg_batch4b": {
        "path": "tools/fixers/fix_ai_pkg_batch4b.py",
        "desc": "AI 包战役批4b: CombatMain private 方法名 + 调用点对齐 02b.",
        "category": "fixer",
        "writes": False,
    },
    "fix_ai_pkg_batch6": {
        "path": "tools/fixers/fix_ai_pkg_batch6.py",
        "desc": "AI 包战役批6: AIStrategy 剩余错误综合修复 + AIStrategyNode/TransporterGroup/CombatAction 修正.",
        "category": "fixer",
        "writes": False,
    },
    "fix_ai_pkg_batch7": {
        "path": "tools/fixers/fix_ai_pkg_batch7.py",
        "desc": "AI 包战役批7: AIStrategy 剩余错误综合修复 (含 BuildSlot/CommandController/UnitType 依赖).",
        "category": "fixer",
        "writes": False,
    },
    "fix_ai_pkg_batch8": {
        "path": "tools/fixers/fix_ai_pkg_batch8.py",
        "desc": "+ anchor",
        "category": "fixer",
        "writes": False,
    },
    "fix_airunit_batch": {
        "path": "tools/fixers/fix_airunit_batch.py",
        "desc": "public ActionTargetType e() {",
        "category": "fixer",
        "writes": False,
    },
    "fix_aistrategy10_batch": {
        "path": "tools/fixers/fix_aistrategy10_batch.py",
        "desc": "f97k AIStrategy$10 修复器: 02b game/a/a$10.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_aistrategy1678_batch": {
        "path": "tools/fixers/fix_aistrategy1678_batch.py",
        "desc": "f97bi AIStrategy$1/$6/$7/$8 修复器: 与 $2-13 同构.",
        "category": "fixer",
        "writes": False,
    },
    "fix_aistrategy245_batch": {
        "path": "tools/fixers/fix_aistrategy245_batch.py",
        "desc": "f97x AIStrategy$2/$4/$5 修复器: 02b game/a/a$2|a$4|a$5 直译对照 ($10 同构).",
        "category": "fixer",
        "writes": False,
    },
    "fix_aistrategy359111213_batch": {
        "path": "tools/fixers/fix_aistrategy359111213_batch.py",
        "desc": "f97as AIStrategy$3/$9/$11/$12/$13 修复器: 02b game/a/a$N 直译对照 ($2/4/5/10 同构).",
        "category": "fixer",
        "writes": False,
    },
    "fix_aistrategy_batch": {
        "path": "tools/fixers/fix_aistrategy_batch.py",
        "desc": "f64c AIStrategy 连带修复 (UnitInstanceList 包修正, 4 条清零)",
        "category": "fixer",
        "writes": False,
    },
    "fix_aistrategy_batch1": {
        "path": "tools/fixers/fix_aistrategy_batch1.py",
        "desc": "AIStrategy 战役批1: 字段类型修正 (NeutralPlayer→UnitBuildStrategy) + L161 PathFinder.",
        "category": "fixer",
        "writes": False,
    },
    "fix_aistrategy_batch2": {
        "path": "tools/fixers/fix_aistrategy_batch2.py",
        "desc": "AIStrategy 战役批2: 方法名对齐 02b (writeToStream 系列幻影名 → 02b 名).",
        "category": "fixer",
        "writes": False,
    },
    "fix_aistrategy_batch3": {
        "path": "tools/fixers/fix_aistrategy_batch3.py",
        "desc": "AIStrategy 战役批3: 内部引用修正.",
        "category": "fixer",
        "writes": False,
    },
    "fix_aistrategy_batch5": {
        "path": "tools/fixers/fix_aistrategy_batch5.py",
        "desc": "在 ((CombatMain) X).MEMBER 模式内替换成员名.",
        "category": "fixer",
        "writes": False,
    },
    "fix_aitask_throws": {
        "path": "tools/fixers/fix_aitask_throws.py",
        "desc": "AITask 3 方法补 throws MapException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_aitask_throws2": {
        "path": "tools/fixers/fix_aitask_throws2.py",
        "desc": "AITask 4 方法补 throws MapException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_aitask_throws3": {
        "path": "tools/fixers/fix_aitask_throws3.py",
        "desc": "AITask.a(String,String,boolean) 补 throws MapException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_aiwaveparser_throws": {
        "path": "tools/fixers/fix_aiwaveparser_throws.py",
        "desc": "AIWaveParser.reset(String) 补 throws MapException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_aiwavesystem_4": {
        "path": "tools/fixers/fix_aiwavesystem_4.py",
        "desc": "AIWaveSystem 4 处修复 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_ajava_family_batch": {
        "path": "tools/fixers/fix_ajava_family_batch.py",
        "desc": "a.java 家族批量修复器 (v19.133f20)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_android_innerclasses": {
        "path": "tools/fixers/fix_android_innerclasses.py",
        "desc": "删除 InnerClasses 属性中 outer_class != 当前类 的条目 (污染源)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_androidsoundfactory_batch": {
        "path": "tools/fixers/fix_androidsoundfactory_batch.py",
        "desc": "SoundInstance soundInstance = new SoundInstance(this, string, a2);",
        "category": "fixer",
        "writes": False,
    },
    "fix_androiduihelper_batch": {
        "path": "tools/fixers/fix_androiduihelper_batch.py",
        "desc": "AndroidUIHelper 家族 19 条清零 (02b appFramework/c + c$2 直译):",
        "category": "fixer",
        "writes": False,
    },
    "fix_anima_batch": {
        "path": "tools/fixers/fix_anima_batch.py",
        "desc": "v19.129 anim/a 战役: 02b e/a 双副本还原 (anim/a extends LogicBoolean) + import/类型/遮蔽修复.",
        "category": "fixer",
        "writes": False,
    },
    "fix_anima_batch2": {
        "path": "tools/fixers/fix_anima_batch2.py",
        "desc": "v19.129b anim/a 收尾: bb→LocalizedString/anim.b→effects.b/i,v→LogicBoolean/y,k→Texture/di...",
        "category": "fixer",
        "writes": False,
    },
    "fix_anima_batch3": {
        "path": "tools/fixers/fix_anima_batch3.py",
        "desc": "v19.129c anim/a 统一: anim.b 全限定→effects.b (6处) + LocalizedString.b()→getLocalizedText() ...",
        "category": "fixer",
        "writes": False,
    },
    "fix_animf_batch": {
        "path": "tools/fixers/fix_animf_batch.py",
        "desc": "v19.133f12j — anim/f.java 双副本战役修复器 (02b custom/e/ 子包对照).",
        "category": "fixer",
        "writes": False,
    },
    "fix_animf_batch2": {
        "path": "tools/fixers/fix_animf_batch2.py",
        "desc": "v19.133f12k — anim/f + UnitBuildAction + MeleeBugUnit 收尾修复器.",
        "category": "fixer",
        "writes": False,
    },
    "fix_anr_l_batch": {
        "path": "tools/fixers/fix_anr_l_batch.py",
        "desc": "ANRWatchdog$3/MovementPath$3/l.java 修复 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_anrerror_batch": {
        "path": "tools/fixers/fix_anrerror_batch.py",
        "desc": "f97p ANRError 家族修复器: 02b utility/a|b|c.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_anrwatchdog_batch": {
        "path": "tools/fixers/fix_anrwatchdog_batch.py",
        "desc": "f97aq ANRWatchdog 修复器: 02b utility/d.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_appframework_p_batch": {
        "path": "tools/fixers/fix_appframework_p_batch.py",
        "desc": "f97u appFramework/p.java 修复器: 02b appFramework/p.java + p$1.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_assetindex_batch": {
        "path": "tools/fixers/fix_assetindex_batch.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_assetstream_close": {
        "path": "tools/fixers/fix_assetstream_close.py",
        "desc": "AssetStream.close() 补 throws IOException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_assetstream_throws": {
        "path": "tools/fixers/fix_assetstream_throws.py",
        "desc": "AssetStream 补 throws (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_astarnode_batch": {
        "path": "tools/fixers/fix_astarnode_batch.py",
        "desc": "f97n AStarNode 修复器: 02b gameFramework/k/n.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_attackmoveaction_batch": {
        "path": "tools/fixers/fix_attackmoveaction_batch.py",
        "desc": "f97am AttackMoveAction 修复器: 与 PatrolAction/RepairAction 同构 (GameAction 抽象覆写链).",
        "category": "fixer",
        "writes": False,
    },
    "fix_attackwaypoint_batch9": {
        "path": "tools/fixers/fix_attackwaypoint_batch9.py",
        "desc": "v19.132 fix_attackwaypoint_batch9.py — AttackWaypoint 家族 extends 修复",
        "category": "fixer",
        "writes": False,
    },
    "fix_attributelocation_batch": {
        "path": "tools/fixers/fix_attributelocation_batch.py",
        "desc": "f68 AttributeLocation 家族修复器 (02b gameFramework/b/o.java 对照, 8 条清零)",
        "category": "fixer",
        "writes": False,
    },
    "fix_au_batch": {
        "path": "tools/fixers/fix_au_batch.py",
        "desc": "f97s custom/au.java 修复器: 02b custom/au.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_audiobackend_h_batch": {
        "path": "tools/fixers/fix_audiobackend_h_batch.py",
        "desc": "f97ai java/audio/backend/h.java 修复器: 02b java/audio/a/h.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_audiobackend_l_batch": {
        "path": "tools/fixers/fix_audiobackend_l_batch.py",
        "desc": "java/audio/backend/l 家族 13 条清零 (02b java/audio/a/l 同构直译):",
        "category": "fixer",
        "writes": False,
    },
    "fix_audiobackend_o_batch": {
        "path": "tools/fixers/fix_audiobackend_o_batch.py",
        "desc": "f97b java/audio/backend/o.java 修复器: 02b java/audio/a/o.java 直译对照 (ObjectMap).",
        "category": "fixer",
        "writes": False,
    },
    "fix_audiom_mc_batch": {
        "path": "tools/fixers/fix_audiom_mc_batch.py",
        "desc": "f97bk audio/m + MainUIController 修复器.",
        "category": "fixer",
        "writes": False,
    },
    "fix_audiomanager_batch": {
        "path": "tools/fixers/fix_audiomanager_batch.py",
        "desc": "AudioManager 修复器 (v19.133f24): long 键家族 18 处。",
        "category": "fixer",
        "writes": False,
    },
    "fix_audiomanager_longint": {
        "path": "tools/fixers/fix_audiomanager_longint.py",
        "desc": "AudioManager.java long→int 恢复 (v19.109 批次17).",
        "category": "fixer",
        "writes": False,
    },
    "fix_audiotrack_batch": {
        "path": "tools/fixers/fix_audiotrack_batch.py",
        "desc": "f97bj AudioTrack 修复器: 02b java/audio/a/r.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_ay_batch1": {
        "path": "tools/fixers/fix_ay_batch1.py",
        "desc": "v19.115q ay 战役: CustomEffectTemplate (custom.ay) 02b 直译 + 依赖补缺.",
        "category": "fixer",
        "writes": False,
    },
    "fix_ay_batch1b": {
        "path": "tools/fixers/fix_ay_batch1b.py",
        "desc": "ay 战役精确修复: 删多余 aA + 补 GameUtils k/j + ay L436 stripIndex 简化",
        "category": "fixer",
        "writes": False,
    },
    "fix_ay_rollback": {
        "path": "tools/fixers/fix_ay_rollback.py",
        "desc": "ay 战役回滚: 删除重复追加 (字段/方法已存在于 03)",
        "category": "fixer",
        "writes": False,
    },
    "fix_ay_throws": {
        "path": "tools/fixers/fix_ay_throws.py",
        "desc": "ay.java a(ModUnitRegistry,ab,String) 补 throws bo (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_backendos_batch": {
        "path": "tools/fixers/fix_backendos_batch.py",
        "desc": "f97cc backend/o + backend/s 修复器.",
        "category": "fixer",
        "writes": False,
    },
    "fix_backendp_batch": {
        "path": "tools/fixers/fix_backendp_batch.py",
        "desc": "f97bo backend/p.java 修复器: 02b java/audio/a/p.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_base_batch1": {
        "path": "tools/fixers/fix_base_batch1.py",
        "desc": "base 家族批1 (v19.115k): ActionBase 新建 + CustomWeaponAction/c.java 02b 直译 + 依赖补方法",
        "category": "fixer",
        "writes": False,
    },
    "fix_base_batch2": {
        "path": "tools/fixers/fix_base_batch2.py",
        "desc": "base 家族批2 (v19.115l): CustomSpawnAction/n.java 02b 直译 + 依赖字段/方法补全",
        "category": "fixer",
        "writes": False,
    },
    "fix_base_batch3": {
        "path": "tools/fixers/fix_base_batch3.py",
        "desc": "base 家族批3 (v19.115m): TransportAction/RepairAction/CustomActionBase/UnitActionDef 直译 + ...",
        "category": "fixer",
        "writes": False,
    },
    "fix_base_batch5": {
        "path": "tools/fixers/fix_base_batch5.py",
        "desc": "批5: base 残留名文件 02b 直译 + bp 签名修复 + 11 处依赖补缺.",
        "category": "fixer",
        "writes": False,
    },
    "fix_base_batch5b": {
        "path": "tools/fixers/fix_base_batch5b.py",
        "desc": "批5 第二轮: 类型包修正 + enhanced-for 强转 + bp 残留 + 依赖补缺 2.",
        "category": "fixer",
        "writes": False,
    },
    "fix_base_batch5c": {
        "path": "tools/fixers/fix_base_batch5c.py",
        "desc": "批5c: 重复追加清理 + KeyframePoint/ModUnitRegistry 修正",
        "category": "fixer",
        "writes": False,
    },
    "fix_batch4_batch": {
        "path": "tools/fixers/fix_batch4_batch.py",
        "desc": "f97cf 批量修复 (TeamChatAction/SubmarineUnit/Modifier/NullSoundFactory).",
        "category": "fixer",
        "writes": False,
    },
    "fix_batch5_batch": {
        "path": "tools/fixers/fix_batch5_batch.py",
        "desc": "public boolean b(UnitType y2) {",
        "category": "fixer",
        "writes": False,
    },
    "fix_batch_bd": {
        "path": "tools/fixers/fix_batch_bd.py",
        "desc": "v19.133f13b — opengl batch b/d 家族修复器 (02b b/a/b.java + b/a/d.java 对照).",
        "category": "fixer",
        "writes": False,
    },
    "fix_batch_rest": {
        "path": "tools/fixers/fix_batch_rest.py",
        "desc": "v19.133f13c — opengl batch 全家收尾修复器 (02b b/a/ 子包对照).",
        "category": "fixer",
        "writes": False,
    },
    "fix_bc3_fill": {
        "path": "tools/fixers/fix_bc3_fill.py",
        "desc": "(类型, 语义名, arity) → 错误列表 (语义名 symbol, 索引无该成员).",
        "category": "fixer",
        "writes": False,
    },
    "fix_be_throws": {
        "path": "tools/fixers/fix_be_throws.py",
        "desc": "be.java b(ModUnitRegistry,ab) 补 throws bo (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_be_throws2": {
        "path": "tools/fixers/fix_be_throws2.py",
        "desc": "be.java a(ModUnitRegistry,ab) 补 throws bo (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_bg_throws": {
        "path": "tools/fixers/fix_bg_throws.py",
        "desc": "bg.java a(ModUnitRegistry,...) 补 throws bo (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_bh_throws": {
        "path": "tools/fixers/fix_bh_throws.py",
        "desc": "bh.java a(bh,...) 补 throws bo (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_bitmap_innerclasses": {
        "path": "tools/fixers/fix_bitmap_innerclasses.py",
        "desc": "从 game-lib.jar 读 Bitmap.class, 补 InnerClasses 属性, 写 cache/patched-classes/。",
        "category": "fixer",
        "writes": False,
    },
    "fix_blockers_v2": {
        "path": "tools/fixers/fix_blockers_v2.py",
        "desc": "CustomArrayList: iterator ctor null arg",
        "category": "fixer",
        "writes": False,
    },
    "fix_blureffect_batch": {
        "path": "tools/fixers/fix_blureffect_batch.py",
        "desc": "f97ay BlurEffect 修复器: 02b gameFramework/b/i.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_bm_batch": {
        "path": "tools/fixers/fix_bm_batch.py",
        "desc": "f97at custom/bm.java 修复器: 02b 声音条目类直译.",
        "category": "fixer",
        "writes": False,
    },
    "fix_bp_throws": {
        "path": "tools/fixers/fix_bp_throws.py",
        "desc": "bp.java b(ModUnitRegistry,...) 补 throws bo (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_bp_throws2": {
        "path": "tools/fixers/fix_bp_throws2.py",
        "desc": "bp.java 2 个 a() 包装补 throws bo (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_buildaction_batch": {
        "path": "tools/fixers/fix_buildaction_batch.py",
        "desc": "v19.133f12b — BuildAction.java 战役修复器 (02b units/a/g.java 逐行对照).",
        "category": "fixer",
        "writes": False,
    },
    "fix_buildaction_batch2": {
        "path": "tools/fixers/fix_buildaction_batch2.py",
        "desc": "v19.133f12g — AbstractBuildAction/UnitBuildAction/BuildAction 修复器.",
        "category": "fixer",
        "writes": False,
    },
    "fix_buildingbase_batch": {
        "path": "tools/fixers/fix_buildingbase_batch.py",
        "desc": "public void n() {",
        "category": "fixer",
        "writes": False,
    },
    "fix_buildqueueaction_batch": {
        "path": "tools/fixers/fix_buildqueueaction_batch.py",
        "desc": "BuildQueueAction 家族 15 条清零 (02b game/units/a/v 直译):",
        "category": "fixer",
        "writes": False,
    },
    "fix_buildwaypoint_batch": {
        "path": "tools/fixers/fix_buildwaypoint_batch.py",
        "desc": "f97ba BuildWaypoint 修复器: 02b gameFramework/f/ar.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_bytearraypacketbuilder_io_batch": {
        "path": "tools/fixers/fix_bytearraypacketbuilder_io_batch.py",
        "desc": "try {",
        "category": "fixer",
        "writes": False,
    },
    "fix_bytearraypacketbuilder_textstream_batch": {
        "path": "tools/fixers/fix_bytearraypacketbuilder_textstream_batch.py",
        "desc": "ByteArrayPacketBuilder.a(String) 局部消化 ax2.a()/ax2.b() IOException (v19.133f96).",
        "category": "fixer",
        "writes": False,
    },
    "fix_bytearraystream_throws": {
        "path": "tools/fixers/fix_bytearraystream_throws.py",
        "desc": "ByteArrayStream.close() 补 throws IOException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_checksumcalculator_batch": {
        "path": "tools/fixers/fix_checksumcalculator_batch.py",
        "desc": "ChecksumCalculator 家族修复器 (v19.133f18)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_cloudrenderer_batch": {
        "path": "tools/fixers/fix_cloudrenderer_batch.py",
        "desc": "f97v CloudRenderer 修复器: 02b gameFramework/d/b.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_combataction_batch1": {
        "path": "tools/fixers/fix_combataction_batch1.py",
        "desc": "v19.115u CombatAction 批1: 字段/方法名/类型错位修复 (02b g.java 逐行铁证)",
        "category": "fixer",
        "writes": False,
    },
    "fix_combataction_batch2": {
        "path": "tools/fixers/fix_combataction_batch2.py",
        "desc": "v19.115u CombatAction 批2: isPersistentGroup 误标→02b 方法名 + GameRenderer→GameUtils + 补方法",
        "category": "fixer",
        "writes": False,
    },
    "fix_combataction_batch3": {
        "path": "tools/fixers/fix_combataction_batch3.py",
        "desc": "v19.115u CombatAction 批3: g()/k()/d/e/h 方法内部误标 + 补丁清理",
        "category": "fixer",
        "writes": False,
    },
    "fix_combatmain_batch2": {
        "path": "tools/fixers/fix_combatmain_batch2.py",
        "desc": "v19.115t CombatMain 批2: 类型标注+方法名错位修复 (02b i.java 铁证)",
        "category": "fixer",
        "writes": False,
    },
    "fix_combatmain_batch3": {
        "path": "tools/fixers/fix_combatmain_batch3.py",
        "desc": "v19.115t CombatMain 批3: 调用点误标+字段类型修复",
        "category": "fixer",
        "writes": False,
    },
    "fix_combatmain_batch4": {
        "path": "tools/fixers/fix_combatmain_batch4.py",
        "desc": "v19.115t CombatMain 批4: AIStrategy 字段/方法语义名 + 变量重复 + 补方法",
        "category": "fixer",
        "writes": False,
    },
    "fix_combatmain_batch5": {
        "path": "tools/fixers/fix_combatmain_batch5.py",
        "desc": "v19.115t CombatMain 批5: 收尾 5 错",
        "category": "fixer",
        "writes": False,
    },
    "fix_combatmain_batch6": {
        "path": "tools/fixers/fix_combatmain_batch6.py",
        "desc": "v19.115t 批6: au=WeaponAction 修正 (class-discoveries v19.111 铁证)",
        "category": "fixer",
        "writes": False,
    },
    "fix_combatmain_batch7": {
        "path": "tools/fixers/fix_combatmain_batch7.py",
        "desc": "v19.115t 批7: av=WeaponTypeEnum 修正 (02b av.java 17 常量 a-q 铁证)",
        "category": "fixer",
        "writes": False,
    },
    "fix_command_2": {
        "path": "tools/fixers/fix_command_2.py",
        "desc": "Command 2 处修复 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_command_batch1": {
        "path": "tools/fixers/fix_command_batch1.py",
        "desc": "Command 战役批1: import 混淆残留 ab/c/d/w → 语义名 (02b gameFramework/ab=ProjectileManager, c=Com...",
        "category": "fixer",
        "writes": False,
    },
    "fix_command_batch2": {
        "path": "tools/fixers/fix_command_batch2.py",
        "desc": ")",
        "category": "fixer",
        "writes": False,
    },
    "fix_command_f": {
        "path": "tools/fixers/fix_command_f.py",
        "desc": "Command.f() throws + catch 还原 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_commandcenter_tex_batch8": {
        "path": "tools/fixers/fix_commandcenter_tex_batch8.py",
        "desc": "v19.132 fix_commandcenter_tex_batch8.py — CommandCenter 错标返回类型 → Texture",
        "category": "fixer",
        "writes": False,
    },
    "fix_commandcontroller_c": {
        "path": "tools/fixers/fix_commandcontroller_c.py",
        "desc": "CommandController.c() 补 throws IOException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_commandcontroller_import": {
        "path": "tools/fixers/fix_commandcontroller_import.py",
        "desc": "CommandController 补 IOException import (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_commandcontroller_throws": {
        "path": "tools/fixers/fix_commandcontroller_throws.py",
        "desc": "CommandController 2 方法补 throws IOException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_commands_batch3": {
        "path": "tools/fixers/fix_commands_batch3.py",
        "desc": "v19.133f10 units/commands $N 家族修复: extends 错误映射 (ExperimentalWallUnit/AutoRepairCallbac...",
        "category": "fixer",
        "writes": False,
    },
    "fix_commands_batch4": {
        "path": "tools/fixers/fix_commands_batch4.py",
        "desc": "v19.133f10 ResourceComponent 误建副本裁决: 02b custom/d/b = CustomActionBase 铁证 (02b b.java L...",
        "category": "fixer",
        "writes": False,
    },
    "fix_commands_batch5": {
        "path": "tools/fixers/fix_commands_batch5.py",
        "desc": "v19.133f10 UnitActionHelper 家族修复 (02b d/q 逐方法锚点).",
        "category": "fixer",
        "writes": False,
    },
    "fix_commands_batch6": {
        "path": "tools/fixers/fix_commands_batch6.py",
        "desc": "v19.133f10 commands $N 家族 a()/b()/c() -> getDescription()/getLabel()/getResourceCost() ...",
        "category": "fixer",
        "writes": False,
    },
    "fix_componenttype_batch": {
        "path": "tools/fixers/fix_componenttype_batch.py",
        "desc": "UC_REPL = [",
        "category": "fixer",
        "writes": False,
    },
    "fix_configb_batch": {
        "path": "tools/fixers/fix_configb_batch.py",
        "desc": "f94 config/b (ActionFilter) 家族修复器 (02b custom/f/b.java 对照, 6 条清零)",
        "category": "fixer",
        "writes": False,
    },
    "fix_configf_batch": {
        "path": "tools/fixers/fix_configf_batch.py",
        "desc": "f97al config/f.java 修复器: 02b custom/bb=LocalizedString 铁证 (compacted f52).",
        "category": "fixer",
        "writes": False,
    },
    "fix_contextmenuactivity_batch": {
        "path": "tools/fixers/fix_contextmenuactivity_batch.py",
        "desc": "MenuItem menuItem3 = contextMenu.add(4, view2.getId(), 0, (CharSequence)(\"From Mod: \" +...",
        "category": "fixer",
        "writes": False,
    },
    "fix_ctor_first": {
        "path": "tools/fixers/fix_ctor_first.py",
        "desc": "构造器形态修复 (R4 循环七) — javac \"call to super must be first\" 三形态:",
        "category": "fixer",
        "writes": False,
    },
    "fix_customaction_comparator_batch": {
        "path": "tools/fixers/fix_customaction_comparator_batch.py",
        "desc": "修复 CustomSpawnAction/CustomWeaponAction 的 ResourceType->UnitTypeComparator (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_customactionbase_throws": {
        "path": "tools/fixers/fix_customactionbase_throws.py",
        "desc": "CustomActionBase 3 方法补 throws bo (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_customactionbase_throws2": {
        "path": "tools/fixers/fix_customactionbase_throws2.py",
        "desc": "CustomActionBase 4 方法补 throws bo (v19.133f96 收尾)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_customaq_family_batch": {
        "path": "tools/fixers/fix_customaq_family_batch.py",
        "desc": "f97g custom aq 家族修复器: 02b custom/aq|an|ao|ap|al|am 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_customarraylistiterator_batch": {
        "path": "tools/fixers/fix_customarraylistiterator_batch.py",
        "desc": "f97af CustomArrayListIterator 修复器: 02b utility/n.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_customeffecthandler_batch": {
        "path": "tools/fixers/fix_customeffecthandler_batch.py",
        "desc": "修复 getCustomEffectHandler 返回类型与调用点局部变量类型。",
        "category": "fixer",
        "writes": False,
    },
    "fix_customsounds_broadcast": {
        "path": "tools/fixers/fix_customsounds_broadcast.py",
        "desc": "03 文件相对路径 -> 02 文件绝对路径 (或 None)",
        "category": "fixer",
        "writes": False,
    },
    "fix_customunittype_local": {
        "path": "tools/fixers/fix_customunittype_local.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_customunittype_ser_local": {
        "path": "tools/fixers/fix_customunittype_ser_local.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_customvisuals_c": {
        "path": "tools/fixers/fix_customvisuals_c.py",
        "desc": "CustomVisuals.c() 补 throws bo (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_datafield_batch": {
        "path": "tools/fixers/fix_datafield_batch.py",
        "desc": "f97bc DataField 修复器: 02b gameFramework/g/b.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_datafieldcollector_batch": {
        "path": "tools/fixers/fix_datafieldcollector_batch.py",
        "desc": "DataFieldCollector 修复器 (v19.133f25): 17 处 (03 重建类, 内部一致性 + 03 现有 API)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_datastreamreader2_batch": {
        "path": "tools/fixers/fix_datastreamreader2_batch.py",
        "desc": "f92 DataStreamReader$2 家族修复器 (02b l$2 对照, 6 条清零)",
        "category": "fixer",
        "writes": False,
    },
    "fix_datavalue1_batch": {
        "path": "tools/fixers/fix_datavalue1_batch.py",
        "desc": "f97au debug/DataValue$1 修复器: 02b units/h/e$1.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_datavalue2_batch": {
        "path": "tools/fixers/fix_datavalue2_batch.py",
        "desc": "f97bx DataValue$2 修复器: 与 DataValue$1 同构 (02b units/h/e$2).",
        "category": "fixer",
        "writes": False,
    },
    "fix_debugmonitortask_batch": {
        "path": "tools/fixers/fix_debugmonitortask_batch.py",
        "desc": "f97ax DebugMonitorTask 修复器: 02b DebugServer 合成字段直译.",
        "category": "fixer",
        "writes": False,
    },
    "fix_debugsession_batch": {
        "path": "tools/fixers/fix_debugsession_batch.py",
        "desc": "f97bz DebugSession 修复器: 与 DebugMonitorTask 同模式.",
        "category": "fixer",
        "writes": False,
    },
    "fix_debugui_batch": {
        "path": "tools/fixers/fix_debugui_batch.py",
        "desc": "DebugUI 家族修复器 (v19.133f23): 18 处 + 补 UnitInstance.cm() + 补建 platform/net/b.java。",
        "category": "fixer",
        "writes": False,
    },
    "fix_dequelist_batch": {
        "path": "tools/fixers/fix_dequelist_batch.py",
        "desc": "f43 DequeList 家族修复器 (02b gameFramework/utility/o.java 对照, 11 条清零)",
        "category": "fixer",
        "writes": False,
    },
    "fix_dequelistiterator_batch": {
        "path": "tools/fixers/fix_dequelistiterator_batch.py",
        "desc": "f60 DequeListIterator 家族修复器 (02b gameFramework/utility/p.java 对照, 9 条清零)",
        "category": "fixer",
        "writes": False,
    },
    "fix_desktopplatform_l_batch": {
        "path": "tools/fixers/fix_desktopplatform_l_batch.py",
        "desc": "DesktopPlatform$1/2 混淆 l.e -> GlobalState.e (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_desktopwindow_batch": {
        "path": "tools/fixers/fix_desktopwindow_batch.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_desktopwindow_h_batch": {
        "path": "tools/fixers/fix_desktopwindow_h_batch.py",
        "desc": "DesktopWindow bX.H() -> showPlayerListPopup() (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_dialoghelper_n_batch": {
        "path": "tools/fixers/fix_dialoghelper_n_batch.py",
        "desc": "f97cm DialogHelper 补 n() 修复器: 02b appFramework/n L60-81 直译 (聊天日志刷新).",
        "category": "fixer",
        "writes": False,
    },
    "fix_dollarn_digits": {
        "path": "tools/fixers/fix_dollarn_digits.py",
        "desc": "$N 数字误用批量修复: 内部类名误替换为数字 1/2/4/5 的还原战役.",
        "category": "fixer",
        "writes": False,
    },
    "fix_drawcontext_batch": {
        "path": "tools/fixers/fix_drawcontext_batch.py",
        "desc": "@Override",
        "category": "fixer",
        "writes": False,
    },
    "fix_draweffect_batch": {
        "path": "tools/fixers/fix_draweffect_batch.py",
        "desc": "f97ac DrawEffect 修复器: 02b gameFramework/d/f.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_duplicate_defs": {
        "path": "tools/fixers/fix_duplicate_defs.py",
        "desc": "Fix duplicates in one file. dup_lines = list of line numbers with duplicate errors.",
        "category": "fixer",
        "writes": False,
    },
    "fix_effectrenderer_batch": {
        "path": "tools/fixers/fix_effectrenderer_batch.py",
        "desc": "f61 EffectRenderer 家族修复器 (02b custom/e/c.java 对照, 9 条清零)",
        "category": "fixer",
        "writes": False,
    },
    "fix_enhanced_for_cast": {
        "path": "tools/fixers/fix_enhanced_for_cast.py",
        "desc": "fix_enhanced_for_cast — enhanced-for 泛型擦除修复 (v19.108 Batch 4)",
        "category": "fixer",
        "writes": False,
    },
    "fix_enum_ctors": {
        "path": "tools/fixers/fix_enum_ctors.py",
        "desc": "Fix one enum file. Returns number of fixes.",
        "category": "fixer",
        "writes": False,
    },
    "fix_extractorbuilding_batch": {
        "path": "tools/fixers/fix_extractorbuilding_batch.py",
        "desc": "public float z() {",
        "category": "fixer",
        "writes": False,
    },
    "fix_f_oldname_batch": {
        "path": "tools/fixers/fix_f_oldname_batch.py",
        "desc": "v19.125 f 旧名广播战役:",
        "category": "fixer",
        "writes": False,
    },
    "fix_factory2_family_batch": {
        "path": "tools/fixers/fix_factory2_family_batch.py",
        "desc": "f97bt Factory$2$1/$2/$22 + AIStrategy.bG 修复器: 02b h$2$1/h$2/h$22 + a/a L119 直译.",
        "category": "fixer",
        "writes": False,
    },
    "fix_factory4_batch": {
        "path": "tools/fixers/fix_factory4_batch.py",
        "desc": "f97bs Factory$4 + AIStrategy.as 修复器.",
        "category": "fixer",
        "writes": False,
    },
    "fix_factory_action_names": {
        "path": "tools/fixers/fix_factory_action_names.py",
        "desc": "fix_factory_action_names.py — Factory 内部类方法名语义化广播 (v19.115)",
        "category": "fixer",
        "writes": False,
    },
    "fix_factory_family": {
        "path": "tools/fixers/fix_factory_family.py",
        "desc": "Factory.java: 字段声明/签名/方法体引用修复。",
        "category": "fixer",
        "writes": False,
    },
    "fix_factory_l_batch": {
        "path": "tools/fixers/fix_factory_l_batch.py",
        "desc": "f97cj Factory.l() + UnitType a() 参数修复.",
        "category": "fixer",
        "writes": False,
    },
    "fix_factory_label_batch": {
        "path": "tools/fixers/fix_factory_label_batch.py",
        "desc": "修复 Factory$6/7/8/10 getLabel()/getDescription() 命名错位。",
        "category": "fixer",
        "writes": False,
    },
    "fix_factoryaction1_batch": {
        "path": "tools/fixers/fix_factoryaction1_batch.py",
        "desc": "public float z() {",
        "category": "fixer",
        "writes": False,
    },
    "fix_factoryaction2_batch": {
        "path": "tools/fixers/fix_factoryaction2_batch.py",
        "desc": "public float z() {",
        "category": "fixer",
        "writes": False,
    },
    "fix_factoryaction3_1_batch": {
        "path": "tools/fixers/fix_factoryaction3_1_batch.py",
        "desc": "f96 Factory$3$1 修复器: 02b units/h$3$1.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_factoryaction3_batch": {
        "path": "tools/fixers/fix_factoryaction3_batch.py",
        "desc": "public float z() {",
        "category": "fixer",
        "writes": False,
    },
    "fix_factoryaction3_family_batch": {
        "path": "tools/fixers/fix_factoryaction3_family_batch.py",
        "desc": "f96b Factory$3 家族修复器: 02b units/h$3|h$3$2|h$3$3 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_factoryaction4_batch": {
        "path": "tools/fixers/fix_factoryaction4_batch.py",
        "desc": "public float z() {",
        "category": "fixer",
        "writes": False,
    },
    "fix_factoryaction5_batch": {
        "path": "tools/fixers/fix_factoryaction5_batch.py",
        "desc": "public float z() {",
        "category": "fixer",
        "writes": False,
    },
    "fix_factorybuilding_batch": {
        "path": "tools/fixers/fix_factorybuilding_batch.py",
        "desc": "MovementController.java 补 e() 无参 (02b game/f.java L659-666).",
        "category": "fixer",
        "writes": False,
    },
    "fix_fd_final": {
        "path": "tools/fixers/fix_fd_final.py",
        "desc": "v19.133f11 FireDecoration 收尾: 补 aj() + UnitInstance.a(float) + tilePixelWidth/Height.",
        "category": "fixer",
        "writes": False,
    },
    "fix_fd_lines": {
        "path": "tools/fixers/fix_fd_lines.py",
        "desc": "v19.133f11 重写 FireDecoration 7 行 GameUtils.a 调用 (02b ai L102-106/131/152).",
        "category": "fixer",
        "writes": False,
    },
    "fix_fd_parens": {
        "path": "tools/fixers/fix_fd_parens.py",
        "desc": "v19.133f11 修复 FireDecoration F56 吞参数 5 处 + UnitRegistry 分号.",
        "category": "fixer",
        "writes": False,
    },
    "fix_field_zip": {
        "path": "tools/fixers/fix_field_zip.py",
        "desc": "字段类错误组合: (可读类型, 混淆字段名) — 无括号 symbol.",
        "category": "fixer",
        "writes": False,
    },
    "fix_fileloader_batch": {
        "path": "tools/fixers/fix_fileloader_batch.py",
        "desc": "v19.133f14d — FileLoader.java 修复器 (02b gameFramework/e/a.java 对照).",
        "category": "fixer",
        "writes": False,
    },
    "fix_fileshader_batch": {
        "path": "tools/fixers/fix_fileshader_batch.py",
        "desc": "FileShader 覆写方法名修正 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_filesystem2_batch": {
        "path": "tools/fixers/fix_filesystem2_batch.py",
        "desc": "v19.133f14e — FileSystem/FilePathSanitizer 修复器.",
        "category": "fixer",
        "writes": False,
    },
    "fix_filesystema_ctor_batch": {
        "path": "tools/fixers/fix_filesystema_ctor_batch.py",
        "desc": "java/filesystem/a.java CFR 构造器误判修复 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_final_ctor": {
        "path": "tools/fixers/fix_final_ctor.py",
        "desc": "从报错行向上找最近的方法声明行 (单行签名), 返回 (方法名, 声明行idx) 或 None.",
        "category": "fixer",
        "writes": False,
    },
    "fix_firedecoration": {
        "path": "tools/fixers/fix_firedecoration.py",
        "desc": "v19.133f11 FireDecoration (02b units/ai) 全文对照修复 20 处.",
        "category": "fixer",
        "writes": False,
    },
    "fix_five_small_batch": {
        "path": "tools/fixers/fix_five_small_batch.py",
        "desc": "f97br 五处小修复 (SoundThread/ap/config-d/Ogg$Sound/UnitInstance.f 补建).",
        "category": "fixer",
        "writes": False,
    },
    "fix_float_dot": {
        "path": "tools/fixers/fix_float_dot.py",
        "desc": "Fix mangled float literals: digit-dot-WORD$digit → digit-dot-digit.",
        "category": "fixer",
        "writes": False,
    },
    "fix_floatcomponent_batch": {
        "path": "tools/fixers/fix_floatcomponent_batch.py",
        "desc": "f97aa FloatComponent 修复器: 02b units/g/d.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_four2_batch": {
        "path": "tools/fixers/fix_four2_batch.py",
        "desc": "f97cg 四小修复 (custom/ae/UnitList$1/SoundPlayRequest/UnitTurret).",
        "category": "fixer",
        "writes": False,
    },
    "fix_four_small_batch": {
        "path": "tools/fixers/fix_four_small_batch.py",
        "desc": "f97cd 四小修复 (SteamFriendsCallback/ANRError/appFramework-s/rendering-s).",
        "category": "fixer",
        "writes": False,
    },
    "fix_framebuffertexture_batch": {
        "path": "tools/fixers/fix_framebuffertexture_batch.py",
        "desc": "f97ad FramebufferTexture 修复器: 02b gameFramework/b/ad.java + b/b.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_gameaction_o_batch": {
        "path": "tools/fixers/fix_gameaction_o_batch.py",
        "desc": "f97ci GameAction 补 O() 修复器: 02b a/s L104-106 直译.",
        "category": "fixer",
        "writes": False,
    },
    "fix_gamehud_batch": {
        "path": "tools/fixers/fix_gamehud_batch.py",
        "desc": "f50 GameHUD 家族修复器 (02b gameFramework/d/a.java 对照, 10 条清零)",
        "category": "fixer",
        "writes": False,
    },
    "fix_gameobject_batch": {
        "path": "tools/fixers/fix_gameobject_batch.py",
        "desc": "f97aw GameObject + UnitInstance.bG 修复器: 02b gameFramework/w.java L113/L118/L140 直译.",
        "category": "fixer",
        "writes": False,
    },
    "fix_gameobjectcomparator_batch": {
        "path": "tools/fixers/fix_gameobjectcomparator_batch.py",
        "desc": "f97m GameObjectComparator 修复器: 02b gameFramework/x.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_gamephase_batch": {
        "path": "tools/fixers/fix_gamephase_batch.py",
        "desc": "f97l GamePhase F84 整写修复器: 02b gameFramework/bs.java 直译 (30 常量纯 enum).",
        "category": "fixer",
        "writes": False,
    },
    "fix_gamerenderer_batch7": {
        "path": "tools/fixers/fix_gamerenderer_batch7.py",
        "desc": "03 GameUtils 补缺失方法 (02b f.java 直译)",
        "category": "fixer",
        "writes": False,
    },
    "fix_gamerenderer_batch7b": {
        "path": "tools/fixers/fix_gamerenderer_batch7b.py",
        "desc": "import → MusicController; 代码中类型引用 GameRenderer → MusicController (跳过注释)",
        "category": "fixer",
        "writes": False,
    },
    "fix_gamesaver_7": {
        "path": "tools/fixers/fix_gamesaver_7.py",
        "desc": "}",
        "category": "fixer",
        "writes": False,
    },
    "fix_gamesaver_c_local": {
        "path": "tools/fixers/fix_gamesaver_c_local.py",
        "desc": "GameSaver.c(String,boolean) 撤销 throws + close 局部消化 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_gamesaver_close_local": {
        "path": "tools/fixers/fix_gamesaver_close_local.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_gamesaver_tail2": {
        "path": "tools/fixers/fix_gamesaver_tail2.py",
        "desc": "// 02b: catch(IOException) 合并入上方 catch(RuntimeException) (v19.133f96 清理)",
        "category": "fixer",
        "writes": False,
    },
    "fix_gameutils_names": {
        "path": "tools/fixers/fix_gameutils_names.py",
        "desc": "类型串取简单名: com.a.b.C -> C",
        "category": "fixer",
        "writes": False,
    },
    "fix_gamewindow_batch": {
        "path": "tools/fixers/fix_gamewindow_batch.py",
        "desc": "public String getDescription() {",
        "category": "fixer",
        "writes": False,
    },
    "fix_getlabel_batch": {
        "path": "tools/fixers/fix_getlabel_batch.py",
        "desc": "f97ck getLabel() 批量修复: GameAction 抽象 getLabel() 覆写.",
        "category": "fixer",
        "writes": False,
    },
    "fix_gl_batch24": {
        "path": "tools/fixers/fix_gl_batch24.py",
        "desc": "v19.131 战役批24: GLObject 语法/方法修复 (02b b/b.java 对照).",
        "category": "fixer",
        "writes": False,
    },
    "fix_gl_batch25": {
        "path": "tools/fixers/fix_gl_batch25.py",
        "desc": "if s.count(anchor) == 1:",
        "category": "fixer",
        "writes": False,
    },
    "fix_gl_batch26": {
        "path": "tools/fixers/fix_gl_batch26.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_gl_batch27": {
        "path": "tools/fixers/fix_gl_batch27.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_globalstate4_batch": {
        "path": "tools/fixers/fix_globalstate4_batch.py",
        "desc": "f97h GlobalState$4/DataStreamReader$4 修复器: 02b l$4.java 直译对照 (ANR 检测).",
        "category": "fixer",
        "writes": False,
    },
    "fix_gltextureregion_actionbinding_batch": {
        "path": "tools/fixers/fix_gltextureregion_actionbinding_batch.py",
        "desc": "GLTextureRegion 合成字段 + ActionBinding throws 修复器 (v19.133f96).",
        "category": "fixer",
        "writes": False,
    },
    "fix_hoverunit_batch": {
        "path": "tools/fixers/fix_hoverunit_batch.py",
        "desc": "HoverUnit 家族 13 条清零 (02b game/units/l 直译):",
        "category": "fixer",
        "writes": False,
    },
    "fix_hudelement_ctor": {
        "path": "tools/fixers/fix_hudelement_ctor.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_hudelementrenderer_batch": {
        "path": "tools/fixers/fix_hudelementrenderer_batch.py",
        "desc": "f73 HUDElementRenderer 家族修复器 (02b gameFramework/d/g.java 对照, 8 条清零)",
        "category": "fixer",
        "writes": False,
    },
    "fix_hudmanager_getint_115c": {
        "path": "tools/fixers/fix_hudmanager_getint_115c.py",
        "desc": "修复 03 HUDManager.java getint 污染 (v19.115c 批3d).",
        "category": "fixer",
        "writes": False,
    },
    "fix_iga_dollarn": {
        "path": "tools/fixers/fix_iga_dollarn.py",
        "desc": "v19.133f11 InGameActivity $N 内部类幻觉名修复 (02b appFramework/g$N 对照).",
        "category": "fixer",
        "writes": False,
    },
    "fix_iga_f25": {
        "path": "tools/fixers/fix_iga_f25.py",
        "desc": "v19.133f11 InGameActivity (02b appFramework/g) F25 $N 数字污染 + l.aZ 静态修复.",
        "category": "fixer",
        "writes": False,
    },
    "fix_iga_h_ac": {
        "path": "tools/fixers/fix_iga_h_ac.py",
        "desc": "v19.133f11 补 03 GameEngine.H() (02b ad.java L1246 showPlayerListPopup) + aC 字段 (02b j/a...",
        "category": "fixer",
        "writes": False,
    },
    "fix_iga_parens": {
        "path": "tools/fixers/fix_iga_parens.py",
        "desc": "v19.133f11 修复 $4/$6/IGA 主文件 3 处 F56 吞括号.",
        "category": "fixer",
        "writes": False,
    },
    "fix_inner_all": {
        "path": "tools/fixers/fix_inner_all.py",
        "desc": "Restore X$N.digit → N.digit (mangled float literals), skip import lines.",
        "category": "fixer",
        "writes": False,
    },
    "fix_inner_class_imports": {
        "path": "tools/fixers/fix_inner_class_imports.py",
        "desc": "Fix one file. Returns number of fixes.",
        "category": "fixer",
        "writes": False,
    },
    "fix_inputaxis_batch": {
        "path": "tools/fixers/fix_inputaxis_batch.py",
        "desc": "InputAxis/SteamEngine 战役: InputAxis=GlobalState(02b gameFramework/l)、java.c.l=k、坏副本 Act...",
        "category": "fixer",
        "writes": False,
    },
    "fix_inputd_batch": {
        "path": "tools/fixers/fix_inputd_batch.py",
        "desc": "f97bp java/input/d.java 修复器: 02b java/c/d.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_inputf_batch": {
        "path": "tools/fixers/fix_inputf_batch.py",
        "desc": "f97ah java/input/f.java 修复器: 02b java/c/f.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_inputj_batch": {
        "path": "tools/fixers/fix_inputj_batch.py",
        "desc": "f97 java/input/j.java 修复器: 02b java/c/j.java 直译对照 (SteamUGCCallback 回调).",
        "category": "fixer",
        "writes": False,
    },
    "fix_inputnetstream_internal": {
        "path": "tools/fixers/fix_inputnetstream_internal.py",
        "desc": "InputNetStream 读方法内部 try-catch 消化 IOException (v19.133f96 v2)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_insert_misplace_restore": {
        "path": "tools/fixers/fix_insert_misplace_restore.py",
        "desc": "修复 MovableUnit.java / DialogHelper.java 的插入错位语法破坏。",
        "category": "fixer",
        "writes": False,
    },
    "fix_intarray_batch": {
        "path": "tools/fixers/fix_intarray_batch.py",
        "desc": "IntArray$N 家族修复器 (v19.133f17)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_javac_symbols": {
        "path": "tools/fixers/fix_javac_symbols.py",
        "desc": "Parse javac output: {filepath: {symbol: count}}.",
        "category": "fixer",
        "writes": False,
    },
    "fix_keybinding_batch": {
        "path": "tools/fixers/fix_keybinding_batch.py",
        "desc": "KeyBinding 家族 14 条清零 (02b gameFramework/ad + af 字段区直译):",
        "category": "fixer",
        "writes": False,
    },
    "fix_keybindingmanager_import": {
        "path": "tools/fixers/fix_keybindingmanager_import.py",
        "desc": "KeyBindingManager MissingKey import 修正 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_keyframepoint_ghost_batch": {
        "path": "tools/fixers/fix_keyframepoint_ghost_batch.py",
        "desc": "}",
        "category": "fixer",
        "writes": False,
    },
    "fix_landunit_batch": {
        "path": "tools/fixers/fix_landunit_batch.py",
        "desc": "public ActionTargetType e() {",
        "category": "fixer",
        "writes": False,
    },
    "fix_librocketcontext2_batch": {
        "path": "tools/fixers/fix_librocketcontext2_batch.py",
        "desc": "f54b LibRocketContext$2$1/$2$2 修复器 (02b librocket/a$2$1·a$2$2 对照, 6 条清零)",
        "category": "fixer",
        "writes": False,
    },
    "fix_licensevalidator1_batch": {
        "path": "tools/fixers/fix_licensevalidator1_batch.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_localization_batch6": {
        "path": "tools/fixers/fix_localization_batch6.py",
        "desc": "v19.132 fix_localization_batch6.py — 本地化调用 `a.a(\"...\")` → `Localization.a(\"...\")`",
        "category": "fixer",
        "writes": False,
    },
    "fix_logicbooleans_batch1": {
        "path": "tools/fixers/fix_logicbooleans_batch1.py",
        "desc": "logicBooleans 批1: 双重前缀错位修复 (Outer.Outer$Inner → Outer$Inner).",
        "category": "fixer",
        "writes": False,
    },
    "fix_logicbooleans_batch2": {
        "path": "tools/fixers/fix_logicbooleans_batch2.py",
        "desc": "logicBooleans 批2: 裸名映射 + 错位类型修正 + 依赖补缺.",
        "category": "fixer",
        "writes": False,
    },
    "fix_logicbooleans_batch3": {
        "path": "tools/fixers/fix_logicbooleans_batch3.py",
        "desc": "logicBooleans 批3: 误伤还原 + source/type 类型修正 + 依赖补缺.",
        "category": "fixer",
        "writes": False,
    },
    "fix_logicbooleans_batch4": {
        "path": "tools/fixers/fix_logicbooleans_batch4.py",
        "desc": "logicBooleans 批4: VariableScope 家族错位 + ParameterMapping Iterator 修复.",
        "category": "fixer",
        "writes": False,
    },
    "fix_logicbooleans_batch5": {
        "path": "tools/fixers/fix_logicbooleans_batch5.py",
        "desc": "logicBooleans 批5: 收尾 (遮蔽 import + 误伤还原 + 零散个案).",
        "category": "fixer",
        "writes": False,
    },
    "fix_logicbooleans_batch6": {
        "path": "tools/fixers/fix_logicbooleans_batch6.py",
        "desc": "logicBooleans 批6: 终批 (最后 23 处零散).",
        "category": "fixer",
        "writes": False,
    },
    "fix_logicbooleans_batch7": {
        "path": "tools/fixers/fix_logicbooleans_batch7.py",
        "desc": "logicBooleans 批7: 最终清零.",
        "category": "fixer",
        "writes": False,
    },
    "fix_map_gap_classes": {
        "path": "tools/fixers/fix_map_gap_classes.py",
        "desc": "03 可读名文件 → (字符串集, 方法数, 路径).",
        "category": "fixer",
        "writes": False,
    },
    "fix_mapengine_a3": {
        "path": "tools/fixers/fix_mapengine_a3.py",
        "desc": "MapEngine.a(String,int,int) 补 throws MapException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_mapengine_a3_io": {
        "path": "tools/fixers/fix_mapengine_a3_io.py",
        "desc": "MapEngine.a(String,int,int) 补 throws IOException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_mapengine_e_io": {
        "path": "tools/fixers/fix_mapengine_e_io.py",
        "desc": "MapEngine.e(String) 补 throws IOException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_mapengine_imports": {
        "path": "tools/fixers/fix_mapengine_imports.py",
        "desc": "MapEngine 补 xml transform imports (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_mapengine_throws": {
        "path": "tools/fixers/fix_mapengine_throws.py",
        "desc": "MapEngine 批量补 throws (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_mapengine_throws2": {
        "path": "tools/fixers/fix_mapengine_throws2.py",
        "desc": "MapEngine 剩余 throws 批量 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_maplayer_import": {
        "path": "tools/fixers/fix_maplayer_import.py",
        "desc": "MapLayer 补 IOException import (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_maplayer_throws": {
        "path": "tools/fixers/fix_maplayer_throws.py",
        "desc": "MapLayer.a(MapEngine,...) 补 throws MapException, IOException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_maplayerdef_batch": {
        "path": "tools/fixers/fix_maplayerdef_batch.py",
        "desc": "f97bq MapLayerDef 修复器: 03 MapSpawn 字段语义名映射 (02b a=spawnIndex/b=unitTypeName).",
        "category": "fixer",
        "writes": False,
    },
    "fix_maplayerdef_throws": {
        "path": "tools/fixers/fix_maplayerdef_throws.py",
        "desc": "MapLayerDef 构造器补 throws MapException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_maprenderer_batch1": {
        "path": "tools/fixers/fix_maprenderer_batch1.py",
        "desc": "v19.115w MapRenderer 批1: 字段类型+语义名同步 (02b b/c.java + b/d.java 逐行铁证)",
        "category": "fixer",
        "writes": False,
    },
    "fix_maprenderer_batch2": {
        "path": "tools/fixers/fix_maprenderer_batch2.py",
        "desc": "v19.115w MapRenderer 批2: 字段类型+方法名+接口补缺 (02b b/c.java + javap 铁证)",
        "category": "fixer",
        "writes": False,
    },
    "fix_maprenderer_batch3": {
        "path": "tools/fixers/fix_maprenderer_batch3.py",
        "desc": "v19.115w MapRenderer 批3: 字段语义名同步 + Texture/接口补缺 (javap 铁证)",
        "category": "fixer",
        "writes": False,
    },
    "fix_maprenderer_batch4": {
        "path": "tools/fixers/fix_maprenderer_batch4.py",
        "desc": "v19.115w MapRenderer 批4: d2 字段语义名 + TileEntry→MapEngine (02b b/d.java 铁证)",
        "category": "fixer",
        "writes": False,
    },
    "fix_mapspawn_batch": {
        "path": "tools/fixers/fix_mapspawn_batch.py",
        "desc": "MapSpawn 修复器 (v19.133f22): 18 处 (02b game/b/a.java 全文对照)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_mapspawn_throws": {
        "path": "tools/fixers/fix_mapspawn_throws.py",
        "desc": "MapSpawn 构造器 + c(String) 补 throws MapException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_mapspawn_throws2": {
        "path": "tools/fixers/fix_mapspawn_throws2.py",
        "desc": "MapSpawn.a(Element,String) 补 throws MapException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_mechanical_renames": {
        "path": "tools/fixers/fix_mechanical_renames.py",
        "desc": "Fix 5: be/bh/bp — m type fields → CustomArrayList for for-each",
        "category": "fixer",
        "writes": False,
    },
    "fix_meleebugunit_batch": {
        "path": "tools/fixers/fix_meleebugunit_batch.py",
        "desc": "v19.133f12i — MeleeBugUnit.java 修复器 (02b units/c/a.java 逐行对照).",
        "category": "fixer",
        "writes": False,
    },
    "fix_method_pair_generic": {
        "path": "tools/fixers/fix_method_pair_generic.py",
        "desc": "CSV 驱动调用点修复 (宿主=03 类名, 同文件优先)",
        "category": "fixer",
        "writes": False,
    },
    "fix_missingkey_throws": {
        "path": "tools/fixers/fix_missingkey_throws.py",
        "desc": "SlickToAndroidKeycodes.a(String) throws MissingKey 恢复 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_missionexecutor_batch": {
        "path": "tools/fixers/fix_missionexecutor_batch.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_missionparser_batch": {
        "path": "tools/fixers/fix_missionparser_batch.py",
        "desc": "f97bg MissionParser 修复器: f58 n/a 包迁移 aicore 后的 import 残留.",
        "category": "fixer",
        "writes": False,
    },
    "fix_moddownloader_j_batch": {
        "path": "tools/fixers/fix_moddownloader_j_batch.py",
        "desc": "ModDownloader GlobalState.j -> modifierMaskToString (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_modinfo_batch": {
        "path": "tools/fixers/fix_modinfo_batch.py",
        "desc": "ModInfo 战役: 03 调用点被早期替换器错误语义化 → 还原 02b 保序方法名.",
        "category": "fixer",
        "writes": False,
    },
    "fix_modinfo_batch2": {
        "path": "tools/fixers/fix_modinfo_batch2.py",
        "desc": "catch (bo bo2) {",
        "category": "fixer",
        "writes": False,
    },
    "fix_modinfo_k": {
        "path": "tools/fixers/fix_modinfo_k.py",
        "desc": "ModInfo.k() 补 throws IOException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_modkeys": {
        "path": "tools/fixers/fix_modkeys.py",
        "desc": "声明行改名 + 类内 this.X 同步",
        "category": "fixer",
        "writes": False,
    },
    "fix_modloader_q_batch": {
        "path": "tools/fixers/fix_modloader_q_batch.py",
        "desc": "f97bv ModLoader 修复 + 补建 custom/q.java.",
        "category": "fixer",
        "writes": False,
    },
    "fix_modsa_merge_batch": {
        "path": "tools/fixers/fix_modsa_merge_batch.py",
        "desc": "裁决 gameFramework/mods/a.java 双副本 -> VersionChecker (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_modsui_batch": {
        "path": "tools/fixers/fix_modsui_batch.py",
        "desc": "v19.133f14 — ModsUI.java 修复器 (02b Mods.java 对照).",
        "category": "fixer",
        "writes": False,
    },
    "fix_modsui_batch2": {
        "path": "tools/fixers/fix_modsui_batch2.py",
        "desc": "v19.133f14c — ModsUI/ModInfo/ModsUI$1/filesystem b 收尾修复器.",
        "category": "fixer",
        "writes": False,
    },
    "fix_modunitloader_throws": {
        "path": "tools/fixers/fix_modunitloader_throws.py",
        "desc": "ModUnitLoader.a(ModUnitLoader,...) 补 throws bo (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_modunitregistry_throws": {
        "path": "tools/fixers/fix_modunitregistry_throws.py",
        "desc": "ModUnitRegistry 4 方法补 throws bo/bd (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_movableunit_batch": {
        "path": "tools/fixers/fix_movableunit_batch.py",
        "desc": "f97w MovableUnit + BuildingBase 修复器: 02b units/x.java + e/b.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_movableunit_m_batch": {
        "path": "tools/fixers/fix_movableunit_m_batch.py",
        "desc": "f97cn MovableUnit 补 m() 修复器: 02b x L65-67 直译.",
        "category": "fixer",
        "writes": False,
    },
    "fix_movementcontroller_local": {
        "path": "tools/fixers/fix_movementcontroller_local.py",
        "desc": "MovementController.a(InputNetStream) 撤销 throws + 局部 try-catch (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_movementcontroller_throws": {
        "path": "tools/fixers/fix_movementcontroller_throws.py",
        "desc": "MovementController.a(InputNetStream) 补 throws IOException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_muc_catch_cleanup": {
        "path": "tools/fixers/fix_muc_catch_cleanup.py",
        "desc": "l2.bX.m(\"starting new\");",
        "category": "fixer",
        "writes": False,
    },
    "fix_muc_throws_chain": {
        "path": "tools/fixers/fix_muc_throws_chain.py",
        "desc": "MainUIController catch 链 throws 补齐 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_a_cv": {
        "path": "tools/fixers/fix_mur_a_cv.py",
        "desc": "ModUnitRegistry.a(String,CustomVisuals) 补 throws bo (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_batch10": {
        "path": "tools/fixers/fix_mur_batch10.py",
        "desc": "v19.131 MUR 战役批10: aj.java (本地化文本) 完整对照修复 (02b custom/aj.java 直译).",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_batch11": {
        "path": "tools/fixers/fix_mur_batch11.py",
        "desc": "v19.131 MUR 战役批11: bf extends SpatialQuery 返回类型 + aj 残留.",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_batch12": {
        "path": "tools/fixers/fix_mur_batch12.py",
        "desc": "s = s.rstrip() + \"\\n\" + c_method",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_batch13": {
        "path": "tools/fixers/fix_mur_batch13.py",
        "desc": "public CustomArrayList k(String var1, String var2) {",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_batch14": {
        "path": "tools/fixers/fix_mur_batch14.py",
        "desc": "for (Iterator iterator = m2.iterator(); iterator.hasNext(); ) {",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_batch15": {
        "path": "tools/fixers/fix_mur_batch15.py",
        "desc": "public static float g(float f2, float f3) { // 02b f.g(float,float) L401-403: min",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_batch16": {
        "path": "tools/fixers/fix_mur_batch16.py",
        "desc": "v19.131 MUR 战役批16: 4参 int 调用点歧义 cast(GameObject) + bh Iterator import + GameUtils f 重复.",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_batch17": {
        "path": "tools/fixers/fix_mur_batch17.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_batch18": {
        "path": "tools/fixers/fix_mur_batch18.py",
        "desc": "v19.131 战役批18: UnitTypeHandle 接口 u()/d(int)/B() 返回 d/b=CustomActionBase 广播修正.",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_batch19": {
        "path": "tools/fixers/fix_mur_batch19.py",
        "desc": "if s.count(anchor) == 1:",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_batch20": {
        "path": "tools/fixers/fix_mur_batch20.py",
        "desc": "ArrayList arrayList3 = null;",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_batch21": {
        "path": "tools/fixers/fix_mur_batch21.py",
        "desc": "if (n2 >= 3) {",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_batch22": {
        "path": "tools/fixers/fix_mur_batch22.py",
        "desc": "v19.131 战役批22: bi/bj 14 错误修复.",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_batch23": {
        "path": "tools/fixers/fix_mur_batch23.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_batch5": {
        "path": "tools/fixers/fix_mur_batch5.py",
        "desc": "public static void do_A() {",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_batch6": {
        "path": "tools/fixers/fix_mur_batch6.py",
        "desc": "v19.131 MUR 战役批6: 外围文件连锁修复 (MUR 相关调用点).",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_batch7": {
        "path": "tools/fixers/fix_mur_batch7.py",
        "desc": "private CustomVisuals(ModUnitRegistry l2, String string) {",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_batch8": {
        "path": "tools/fixers/fix_mur_batch8.py",
        "desc": "CustomVisuals(ModUnitRegistry l2, String string, ModUnitRegistry$1 modUnitRegistry$1) {...",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_batch9": {
        "path": "tools/fixers/fix_mur_batch9.py",
        "desc": "v19.131 MUR 战役批9: SellAction 完整对照修复 (02b units/a/z.java 直译).",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_du": {
        "path": "tools/fixers/fix_mur_du.py",
        "desc": "v19.128l ModUnitRegistry 补 d(int)+u() 委托 (02b l d(int)/u() = return this.ch; 03 getCost...",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_dup": {
        "path": "tools/fixers/fix_mur_dup.py",
        "desc": "v19.128i 修复 MUR 重复 j() + 补 y() (02b l).",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_f": {
        "path": "tools/fixers/fix_mur_f.py",
        "desc": "v19.128e ModUnitRegistry 补 f() (02b l L738-751: 本地化描述, 与 e() 同构).",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_h": {
        "path": "tools/fixers/fix_mur_h.py",
        "desc": "v19.128d ModUnitRegistry 补 h() (02b l L793-803: 科技等级动作列表初始化).",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_jk": {
        "path": "tools/fixers/fix_mur_jk.py",
        "desc": "v19.128h ModUnitRegistry 补 j/k/m/n/o (02b l L753-775: return aH/aI/fq/fr/fg).",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_p": {
        "path": "tools/fixers/fix_mur_p.py",
        "desc": "v19.128g ModUnitRegistry 补 p() (02b l L777-779: return this.aJ).",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_q": {
        "path": "tools/fixers/fix_mur_q.py",
        "desc": "v19.128f ModUnitRegistry 补 q() (02b l L781-783: return this.ff).",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_wcd": {
        "path": "tools/fixers/fix_mur_wcd.py",
        "desc": "v19.128m ModUnitRegistry 补 w()/c()/b(int)/d() (02b l L680-693/L857-864: isLocked/getCre...",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_y": {
        "path": "tools/fixers/fix_mur_y.py",
        "desc": "v19.128j ModUnitRegistry 补 y() (02b l L1119-1121: return this.gr=usesCreditResources, 日...",
        "category": "fixer",
        "writes": False,
    },
    "fix_mur_z": {
        "path": "tools/fixers/fix_mur_z.py",
        "desc": "v19.128k ModUnitRegistry 补 z() (02b l L1560-1562: return this.aw).",
        "category": "fixer",
        "writes": False,
    },
    "fix_musicfolder_batch": {
        "path": "tools/fixers/fix_musicfolder_batch.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_musicplayer_batch": {
        "path": "tools/fixers/fix_musicplayer_batch.py",
        "desc": "f97ab MusicPlayer 修复器: 02b gameFramework/ap.java L61-74 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_batch": {
        "path": "tools/fixers/fix_netengine_batch.py",
        "desc": "UnitTurret 字段 e→restAngle, j→targetUnit (02 ap.java:13 锚点)",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_batch2": {
        "path": "tools/fixers/fix_netengine_batch2.py",
        "desc": "在 anchor 后插入方法",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_batch2b": {
        "path": "tools/fixers/fix_netengine_batch2b.py",
        "desc": "fix_netengine_batch2b.py — NetEngine 战役批2b (v19.115y)",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_batch2c": {
        "path": "tools/fixers/fix_netengine_batch2c.py",
        "desc": "fix_netengine_batch2c.py — NetEngine 战役批2c (v19.115y)",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_batch2d": {
        "path": "tools/fixers/fix_netengine_batch2d.py",
        "desc": "fix_netengine_batch2d.py — NetEngine 战役批2d (v19.115y)",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_batch2e": {
        "path": "tools/fixers/fix_netengine_batch2e.py",
        "desc": "fix_netengine_batch2e.py — NetEngine 战役批2e (v19.115y) 收尾",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_batch2f": {
        "path": "tools/fixers/fix_netengine_batch2f.py",
        "desc": "fix_netengine_batch2f.py — NetEngine 战役批2f (v19.115y) 依赖文件收尾",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_batch2g": {
        "path": "tools/fixers/fix_netengine_batch2g.py",
        "desc": "fix_netengine_batch2g.py — NetEngine 战役批2g (v19.115y) 最后 4 处",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_batch2h": {
        "path": "tools/fixers/fix_netengine_batch2h.py",
        "desc": "fix_netengine_batch2h.py — NetEngine 战役批2h (v19.115y)",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_batch2i": {
        "path": "tools/fixers/fix_netengine_batch2i.py",
        "desc": "fix_netengine_batch2i.py — NetEngine 战役批2i (v19.115y) 收尾",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_batch2j": {
        "path": "tools/fixers/fix_netengine_batch2j.py",
        "desc": "fix_netengine_batch2j.py — NetEngine 战役批2j (v19.115y)",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_batch2k": {
        "path": "tools/fixers/fix_netengine_batch2k.py",
        "desc": "fix_netengine_batch2k.py — NetEngine 战役批2k (v19.115y) 收尾",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_bd_cast": {
        "path": "tools/fixers/fix_netengine_bd_cast.py",
        "desc": "NetEngine catch(RuntimeException) 体内 bd 字段强转 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_bd_cast2": {
        "path": "tools/fixers/fix_netengine_bd_cast2.py",
        "desc": "NetEngine bd 双重强转 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_bd_restore": {
        "path": "tools/fixers/fix_netengine_bd_restore.py",
        "desc": "NetEngine catch(bd) 还原 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_bytearray_catch_batch": {
        "path": "tools/fixers/fix_netengine_bytearray_catch_batch.py",
        "desc": "NetEngine registerRelayServer(byte[]) catch 还原 IOException (v19.133f96).",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_catch_cleanup": {
        "path": "tools/fixers/fix_netengine_catch_cleanup.py",
        "desc": "从 start(含)找括号匹配的结束行(返回行索引)。start 行含 '{'。",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_catch_io": {
        "path": "tools/fixers/fix_netengine_catch_io.py",
        "desc": "OutputNetStream as2 = new OutputNetStream();",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_catch_io2": {
        "path": "tools/fixers/fix_netengine_catch_io2.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_catch_type": {
        "path": "tools/fixers/fix_netengine_catch_type.py",
        "desc": "NetEngine 无效 catch 类型改写 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_command_catch_batch": {
        "path": "tools/fixers/fix_netengine_command_catch_batch.py",
        "desc": "NetEngine Command.a(as) 两处 catch 还原 IOException (v19.133f96).",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_m_throws": {
        "path": "tools/fixers/fix_netengine_m_throws.py",
        "desc": "NetEngine.m(String,boolean) 静态工厂补 throws (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_rr_np": {
        "path": "tools/fixers/fix_netengine_rr_np.py",
        "desc": "NetEngine.registerRelayServer(NetworkPacket) 补 throws IOException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_serversync_catch_batch": {
        "path": "tools/fixers/fix_netengine_serversync_catch_batch.py",
        "desc": "NetEngine sendServerInfo(c) catch 还原 IOException (v19.133f96).",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_tail3": {
        "path": "tools/fixers/fix_netengine_tail3.py",
        "desc": "NetEngine 收尾 3 处 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_netengine_throws_io": {
        "path": "tools/fixers/fix_netengine_throws_io.py",
        "desc": "NetEngine.m(String)/registerRelayServer(Socket) 补 throws IOException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_netexc_l_batch": {
        "path": "tools/fixers/fix_netexc_l_batch.py",
        "desc": "NetworkException$1 混淆 l -> GlobalState (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_netm_batch": {
        "path": "tools/fixers/fix_netm_batch.py",
        "desc": "v19.133f15b — platform/net/m.java 修复器 (02b a/a/m.java 对照).",
        "category": "fixer",
        "writes": False,
    },
    "fix_network_round2_batch": {
        "path": "tools/fixers/fix_network_round2_batch.py",
        "desc": "network 域第二轮补充修复 (v19.133f19b)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_network_scatter_batch": {
        "path": "tools/fixers/fix_network_scatter_batch.py",
        "desc": "network 域散件批量修复器 (v19.133f19)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_nodepool_batch": {
        "path": "tools/fixers/fix_nodepool_batch.py",
        "desc": "NodePool 修复器 (v19.133f26): 17 处 (02b gameFramework/k/m.java 全文对照)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_notpublic_batch1": {
        "path": "tools/fixers/fix_notpublic_batch1.py",
        "desc": "v19.132 fix_notpublic_batch1.py — 修复 not public 成员访问错误 (类型标注错位)",
        "category": "fixer",
        "writes": False,
    },
    "fix_notpublic_batch2": {
        "path": "tools/fixers/fix_notpublic_batch2.py",
        "desc": "v19.132 fix_notpublic_batch2.py — 幻觉包 com.corrodinggames.rts.game.projectiles 批量修复",
        "category": "fixer",
        "writes": False,
    },
    "fix_notpublic_batch3": {
        "path": "tools/fixers/fix_notpublic_batch3.py",
        "desc": "v19.132 fix_notpublic_batch3.py — not public 批量修复 (类型错标) 第三批",
        "category": "fixer",
        "writes": False,
    },
    "fix_notpublic_batch4": {
        "path": "tools/fixers/fix_notpublic_batch4.py",
        "desc": "v19.132 fix_notpublic_batch4.py — PlayerState not public 残留 (批4)",
        "category": "fixer",
        "writes": False,
    },
    "fix_numeric_literals": {
        "path": "tools/fixers/fix_numeric_literals.py",
        "desc": "Restore numeric literals mangled by fix_inner_class_imports.py.",
        "category": "fixer",
        "writes": False,
    },
    "fix_ogginputstream_batch": {
        "path": "tools/fixers/fix_ogginputstream_batch.py",
        "desc": "f74 OggInputStream 家族修复器 (02b java/audio/lwjgl/OggInputStream 对照, 8 条清零)",
        "category": "fixer",
        "writes": False,
    },
    "fix_ons_file_close": {
        "path": "tools/fixers/fix_ons_file_close.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_openal_dollar1_batch": {
        "path": "tools/fixers/fix_openal_dollar1_batch.py",
        "desc": "OpenALAudio \\$1 数字污染修复 (v19.133f96, F25)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_opengl_aa_final_batch": {
        "path": "tools/fixers/fix_opengl_aa_final_batch.py",
        "desc": "opengl/aa.java synthetic final 去 final (B1 隐藏错误清零)",
        "category": "fixer",
        "writes": False,
    },
    "fix_openglf_batch": {
        "path": "tools/fixers/fix_openglf_batch.py",
        "desc": "f97aj opengl/f.java 修复器: 02b gameFramework/b/f.java L41-50 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_order_align": {
        "path": "tools/fixers/fix_order_align.py",
        "desc": "双序列对齐: (名, arity) 指纹. 同名+同arity 锚定; 剩余按位置+arity 插值.",
        "category": "fixer",
        "writes": False,
    },
    "fix_outputnetstream_internal": {
        "path": "tools/fixers/fix_outputnetstream_internal.py",
        "desc": "OutputNetStream 写方法内部 try-catch 消化 IOException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_outputnetstream_override_throws_batch": {
        "path": "tools/fixers/fix_outputnetstream_override_throws_batch.py",
        "desc": "a(OutputNetStream) 覆写链批量补 throws IOException (v19.133f96).",
        "category": "fixer",
        "writes": False,
    },
    "fix_outputnetstream_tail6": {
        "path": "tools/fixers/fix_outputnetstream_tail6.py",
        "desc": "public void a(ByteArrayOutputStream byteArrayOutputStream) {",
        "category": "fixer",
        "writes": False,
    },
    "fix_outputnetstream_throws": {
        "path": "tools/fixers/fix_outputnetstream_throws.py",
        "desc": "OutputNetStream 写方法补 throws IOException (v19.133f96 根源修复)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_packetdecoder_catch": {
        "path": "tools/fixers/fix_packetdecoder_catch.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_parameteranimator_batch": {
        "path": "tools/fixers/fix_parameteranimator_batch.py",
        "desc": "v19.133f13 — ParameterAnimator.java 修复器 (02b custom/e.java 对照).",
        "category": "fixer",
        "writes": False,
    },
    "fix_patch_expand": {
        "path": "tools/fixers/fix_patch_expand.py",
        "desc": "返回 [(02目标, 03类名)] 新可编译且未安装的类 (批量编译, 单个 javac 进程).",
        "category": "fixer",
        "writes": False,
    },
    "fix_pathfinding_helper": {
        "path": "tools/fixers/fix_pathfinding_helper.py",
        "desc": "fix_pathfinding_helper.py — PathfindingHelper 跨类误植修复 (v19.115)",
        "category": "fixer",
        "writes": False,
    },
    "fix_pathfindinghelper_batch": {
        "path": "tools/fixers/fix_pathfindinghelper_batch.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_pathfindingutils_batch": {
        "path": "tools/fixers/fix_pathfindingutils_batch.py",
        "desc": "DequeList dequeList = com.corrodinggames.rts.game.units.UnitInstance.bF();",
        "category": "fixer",
        "writes": False,
    },
    "fix_pathnode_batch": {
        "path": "tools/fixers/fix_pathnode_batch.py",
        "desc": "this.a = n2.tileX;",
        "category": "fixer",
        "writes": False,
    },
    "fix_pathsolver_s2": {
        "path": "tools/fixers/fix_pathsolver_s2.py",
        "desc": "PathSolverRunner.s2 初始化修复 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_patrolaction_batch": {
        "path": "tools/fixers/fix_patrolaction_batch.py",
        "desc": "f97c PatrolAction 修复器: 02b units/a/i.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_platform_a_batch": {
        "path": "tools/fixers/fix_platform_a_batch.py",
        "desc": "platform/a.java 修复 (v19.133f20): 4 处。",
        "category": "fixer",
        "writes": False,
    },
    "fix_platformb_batch": {
        "path": "tools/fixers/fix_platformb_batch.py",
        "desc": "f97bf platform/b.java 修复器: 02b a/b.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_platformb_shadow_batch": {
        "path": "tools/fixers/fix_platformb_shadow_batch.py",
        "desc": "platform/b.java 字段遮蔽类名修复 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_playerconnect_batch": {
        "path": "tools/fixers/fix_playerconnect_batch.py",
        "desc": "edits: list of (old, new, expect_count). 返回替换后的文本。",
        "category": "fixer",
        "writes": False,
    },
    "fix_playerstate_b_throws": {
        "path": "tools/fixers/fix_playerstate_b_throws.py",
        "desc": "PlayerState.b(int,boolean) 补 throws IOException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_playerstate_methods": {
        "path": "tools/fixers/fix_playerstate_methods.py",
        "desc": "对 CSV 报错的每个符号做定向指纹配对诊断",
        "category": "fixer",
        "writes": False,
    },
    "fix_powerbuilding_batch": {
        "path": "tools/fixers/fix_powerbuilding_batch.py",
        "desc": "public float z() {",
        "category": "fixer",
        "writes": False,
    },
    "fix_produceslot_batch5": {
        "path": "tools/fixers/fix_produceslot_batch5.py",
        "desc": "v19.132 fix_produceslot_batch5.py — 幻觉类型 ProduceSlot 批量修复 (→Texture / e.N())",
        "category": "fixer",
        "writes": False,
    },
    "fix_r3_residual": {
        "path": "tools/fixers/fix_r3_residual.py",
        "desc": "02 文件 import 表: {简单名: FQN}",
        "category": "fixer",
        "writes": False,
    },
    "fix_radarbuilding_batch": {
        "path": "tools/fixers/fix_radarbuilding_batch.py",
        "desc": "public float z() {",
        "category": "fixer",
        "writes": False,
    },
    "fix_rallygroup_batch": {
        "path": "tools/fixers/fix_rallygroup_batch.py",
        "desc": "RallyGroup 家族 14 条清零 (02b game/a/l 直译 + o/h 覆写链):",
        "category": "fixer",
        "writes": False,
    },
    "fix_rallypointaction_batch": {
        "path": "tools/fixers/fix_rallypointaction_batch.py",
        "desc": "return Localization.a(\"gui.actions.setRally\", new Object[0]);",
        "category": "fixer",
        "writes": False,
    },
    "fix_receiveworker_throws": {
        "path": "tools/fixers/fix_receiveworker_throws.py",
        "desc": "ReceiveWorker.a() 补 throws IOException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_reliableprofile_throws_batch": {
        "path": "tools/fixers/fix_reliableprofile_throws_batch.py",
        "desc": "ReliableProfile.java throws 清零修复器 (B1 网络库收尾)",
        "category": "fixer",
        "writes": False,
    },
    "fix_reliableserversocket_throws_batch": {
        "path": "tools/fixers/fix_reliableserversocket_throws_batch.py",
        "desc": "ReliableServerSocket.java throws 清零修复器 (B1 网络库收尾)",
        "category": "fixer",
        "writes": False,
    },
    "fix_reliablesocket_batch1": {
        "path": "tools/fixers/fix_reliablesocket_batch1.py",
        "desc": "fix_reliablesocket_batch1.py — ReliableSocket 战役批1 (v19.115z)",
        "category": "fixer",
        "writes": False,
    },
    "fix_reliablesocket_batch2": {
        "path": "tools/fixers/fix_reliablesocket_batch2.py",
        "desc": "fix_reliablesocket_batch2.py — ReliableSocket 战役批2 (v19.115z)",
        "category": "fixer",
        "writes": False,
    },
    "fix_reliablesocket_batch3": {
        "path": "tools/fixers/fix_reliablesocket_batch3.py",
        "desc": "fix_reliablesocket_batch3.py — ReliableSocket 战役批3 (v19.115z)",
        "category": "fixer",
        "writes": False,
    },
    "fix_reliablesocket_batch4": {
        "path": "tools/fixers/fix_reliablesocket_batch4.py",
        "desc": "fix_reliablesocket_batch4.py — ReliableSocket 战役批4 (v19.115z)",
        "category": "fixer",
        "writes": False,
    },
    "fix_reliablesocket_batch5": {
        "path": "tools/fixers/fix_reliablesocket_batch5.py",
        "desc": "fix_reliablesocket_batch5.py — ReliableSocket 战役批5 (v19.115z) 收尾",
        "category": "fixer",
        "writes": False,
    },
    "fix_reliablesocket_throws_batch": {
        "path": "tools/fixers/fix_reliablesocket_throws_batch.py",
        "desc": "ReliableSocket.java 39 条 throws 清零修复器 (B1 网络库收尾)",
        "category": "fixer",
        "writes": False,
    },
    "fix_renderlayer_pollution": {
        "path": "tools/fixers/fix_renderlayer_pollution.py",
        "desc": "RenderLayer 污染名战役 (v19.115): 清除 03 侧 RenderLayer 类名污染双副本。",
        "category": "fixer",
        "writes": False,
    },
    "fix_repairaction_batch": {
        "path": "tools/fixers/fix_repairaction_batch.py",
        "desc": "f97f RepairAction 修复器: 02b units/a/n.java 直译对照 (与 PatrolAction 同构).",
        "category": "fixer",
        "writes": False,
    },
    "fix_replaycomparator_batch": {
        "path": "tools/fixers/fix_replaycomparator_batch.py",
        "desc": "f97ar ReplayComparator 修复器: CFR 字段/方法混淆.",
        "category": "fixer",
        "writes": False,
    },
    "fix_replayengine_catch": {
        "path": "tools/fixers/fix_replayengine_catch.py",
        "desc": "ReplayEngine 3 处 catch->RuntimeException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_replayengine_h": {
        "path": "tools/fixers/fix_replayengine_h.py",
        "desc": "ReplayEngine h() throws + L761 catch 还原 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_replaywriter_catch": {
        "path": "tools/fixers/fix_replaywriter_catch.py",
        "desc": "ReplayWriter 2 处 catch->RuntimeException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_resourcedisplay_batch": {
        "path": "tools/fixers/fix_resourcedisplay_batch.py",
        "desc": "f97bn ResourceDisplay 修复器.",
        "category": "fixer",
        "writes": False,
    },
    "fix_resourceloader_batch": {
        "path": "tools/fixers/fix_resourceloader_batch.py",
        "desc": "f97d ResourceLoader 修复器: 02b game/j.java 直译对照 (78 行全文).",
        "category": "fixer",
        "writes": False,
    },
    "fix_resourceloader_batch10": {
        "path": "tools/fixers/fix_resourceloader_batch10.py",
        "desc": "46 处 ResourceLoader.xxx 静态调用 → GlobalState.xxx",
        "category": "fixer",
        "writes": False,
    },
    "fix_resources_c": {
        "path": "tools/fixers/fix_resources_c.py",
        "desc": "v19.133f11 resources/c.java (02b custom/d/c) 全文对照修复: DynamicResourcePrice.",
        "category": "fixer",
        "writes": False,
    },
    "fix_ringbuffer_batch": {
        "path": "tools/fixers/fix_ringbuffer_batch.py",
        "desc": "f48 RingBuffer 家族修复器 (02b gameFramework/utility/g.java 对照, 11 条清零)",
        "category": "fixer",
        "writes": False,
    },
    "fix_ringbuffer_synth_batch": {
        "path": "tools/fixers/fix_ringbuffer_synth_batch.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_s1_batch": {
        "path": "tools/fixers/fix_s1_batch.py",
        "desc": "f97ce 补建 appFramework/s$1.java: 02b appFramework/s$1.java 直译.",
        "category": "fixer",
        "writes": False,
    },
    "fix_sb_final": {
        "path": "tools/fixers/fix_sb_final.py",
        "desc": "v19.133f11 StorageBackend 剩余 6 处 (02b e/c 对照).",
        "category": "fixer",
        "writes": False,
    },
    "fix_scatter96_batch": {
        "path": "tools/fixers/fix_scatter96_batch.py",
        "desc": "v19.133f96 散件批处理: BlurEffect/FileShader/SoundInstance/UnitInfoPanel 等 (--dry-run/--apply)",
        "category": "fixer",
        "writes": False,
    },
    "fix_screenshotcapture_batch": {
        "path": "tools/fixers/fix_screenshotcapture_batch.py",
        "desc": "f97ca ScreenshotCapture 修复器: 02b java/r.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_screenutils_batch": {
        "path": "tools/fixers/fix_screenutils_batch.py",
        "desc": "f97bb ScreenUtils 修复器: 02b gameFramework/f/j.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_scriptengine_batch": {
        "path": "tools/fixers/fix_scriptengine_batch.py",
        "desc": "ScriptEngine 战役: 03 误命名 UIScriptEngine → 官方语义名 ScriptEngine (05-gamelib/02b 双铁证).",
        "category": "fixer",
        "writes": False,
    },
    "fix_scriptengine_batch2": {
        "path": "tools/fixers/fix_scriptengine_batch2.py",
        "desc": "v19.133f12c — ScriptEngine.java 战役修复器 batch2 (02b librocket/scripts/ScriptEngine.java 对照).",
        "category": "fixer",
        "writes": False,
    },
    "fix_scriptengine_inner_batch": {
        "path": "tools/fixers/fix_scriptengine_inner_batch.py",
        "desc": "v19.133f12h — ScriptEngine$Action/$RunnableAction 修复器.",
        "category": "fixer",
        "writes": False,
    },
    "fix_semantic_decl": {
        "path": "tools/fixers/fix_semantic_decl.py",
        "desc": "整类广播改名: 声明处 + 本类内 `.obf(` 调用点 (排除字符串/注释).",
        "category": "fixer",
        "writes": False,
    },
    "fix_sendworker": {
        "path": "tools/fixers/fix_sendworker.py",
        "desc": "v19.133f11 SendWorker 修复: f 类型 PlatformBackend→PacketDecoder + au/ServerResult/ChatSyst...",
        "category": "fixer",
        "writes": False,
    },
    "fix_sendworker_ctor2": {
        "path": "tools/fixers/fix_sendworker_ctor2.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_sendworker_throws": {
        "path": "tools/fixers/fix_sendworker_throws.py",
        "desc": "SendWorker 构造器补 throws IOException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_serializetostream_throws_batch": {
        "path": "tools/fixers/fix_serializetostream_throws_batch.py",
        "desc": "serializeToStream 覆写链补 throws IOException (v19.133f96).",
        "category": "fixer",
        "writes": False,
    },
    "fix_settingsengine_batch": {
        "path": "tools/fixers/fix_settingsengine_batch.py",
        "desc": "SettingsEngine 家族 15 条清零 (02b gameFramework/SettingsEngine 同名直译:",
        "category": "fixer",
        "writes": False,
    },
    "fix_shader_d_throws": {
        "path": "tools/fixers/fix_shader_d_throws.py",
        "desc": "Shader.d() 补 throws IOException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_short_type_residual": {
        "path": "tools/fixers/fix_short_type_residual.py",
        "desc": "(pkg, cls) 混淆键候选: import 段 + 同包 → fwd 可读名 (唯一).",
        "category": "fixer",
        "writes": False,
    },
    "fix_sig_align": {
        "path": "tools/fixers/fix_sig_align.py",
        "desc": "03 方法: [(名, [参数类型文本], 返回类型文本)].",
        "category": "fixer",
        "writes": False,
    },
    "fix_single_char_types": {
        "path": "tools/fixers/fix_single_char_types.py",
        "desc": "Apply single-char type renames to one line. Returns new line + count.",
        "category": "fixer",
        "writes": False,
    },
    "fix_slick2d_rebuild_115c": {
        "path": "tools/fixers/fix_slick2d_rebuild_115c.py",
        "desc": "(参数序列 tuple) -> 方法名列表 (保留全部, 兼容同名冲突)",
        "category": "fixer",
        "writes": False,
    },
    "fix_slickfilesystem_batch": {
        "path": "tools/fixers/fix_slickfilesystem_batch.py",
        "desc": "v19.133f14b — java/filesystem a+b 修复器 (SlickLibRocket 桌面桥).",
        "category": "fixer",
        "writes": False,
    },
    "fix_slickimagedata_batch": {
        "path": "tools/fixers/fix_slickimagedata_batch.py",
        "desc": "f79 SlickImageData 家族修复器 (02b java/t.java 对照, 7 条清零)",
        "category": "fixer",
        "writes": False,
    },
    "fix_slickkeycodes_batch": {
        "path": "tools/fixers/fix_slickkeycodes_batch.py",
        "desc": "f97i SlickToAndroidKeycodes 修复器: 02b utility/SlickToAndroidKeycodes.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_slickkeys_iter_batch": {
        "path": "tools/fixers/fix_slickkeys_iter_batch.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_soundinstance_batch": {
        "path": "tools/fixers/fix_soundinstance_batch.py",
        "desc": "SoundInstance 调用点对齐 ad 语义名 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_soundregistry_batch": {
        "path": "tools/fixers/fix_soundregistry_batch.py",
        "desc": "f97bm SoundRegistry 修复器: 02b 静态字段访问直译.",
        "category": "fixer",
        "writes": False,
    },
    "fix_soundthread_batch": {
        "path": "tools/fixers/fix_soundthread_batch.py",
        "desc": "f97az SoundThread + AndroidSoundFactory 修复器: 02b gameFramework/a/d.java + a/a.java 直译.",
        "category": "fixer",
        "writes": False,
    },
    "fix_spatialgrid_batch": {
        "path": "tools/fixers/fix_spatialgrid_batch.py",
        "desc": "f97ap SpatialGrid + QueryResult 修复器: 02b units/f/c.java + utility/u 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_specialaction_ai_batch": {
        "path": "tools/fixers/fix_specialaction_ai_batch.py",
        "desc": "修复 SpecialActionType 方法命名 + AIStrategy$11/12 bw 引用 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_specialactiontype_batch": {
        "path": "tools/fixers/fix_specialactiontype_batch.py",
        "desc": "public ActionCategory f() {",
        "category": "fixer",
        "writes": False,
    },
    "fix_specialbuilding_batch": {
        "path": "tools/fixers/fix_specialbuilding_batch.py",
        "desc": "Iterator iterator = this.h.iterator();",
        "category": "fixer",
        "writes": False,
    },
    "fix_steamapi_throws": {
        "path": "tools/fixers/fix_steamapi_throws.py",
        "desc": "SteamAPI.init(String) 补 throws SteamException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_steamcallback_dollar_batch": {
        "path": "tools/fixers/fix_steamcallback_dollar_batch.py",
        "desc": "修复 codedisaster 三个回调接口的 \\$ 顶级类点号引用(F8)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_steamcallbacks_batch": {
        "path": "tools/fixers/fix_steamcallbacks_batch.py",
        "desc": "f97ao Steam 回调接口 F8 修复器 (codedisaster/steamworks).",
        "category": "fixer",
        "writes": False,
    },
    "fix_steamgameserver_throws_batch": {
        "path": "tools/fixers/fix_steamgameserver_throws_batch.py",
        "desc": "SteamGameServer 补 throws SteamException (v19.133f96 清零收尾)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_steamgsapi_throws": {
        "path": "tools/fixers/fix_steamgsapi_throws.py",
        "desc": "SteamGameServerAPI init 补 throws SteamException (v19.133f96, 同 SteamAPI 模式)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_steamhttp_throws_batch": {
        "path": "tools/fixers/fix_steamhttp_throws_batch.py",
        "desc": "SteamHTTP 补 throws SteamException (v19.133f96 清零收尾, 同 SteamGameServer 模式)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_steammanager_batch": {
        "path": "tools/fixers/fix_steammanager_batch.py",
        "desc": "v19.133f12d — SteamManager.java 战役修复器 (02b java/c/b.java 对照).",
        "category": "fixer",
        "writes": False,
    },
    "fix_steammanager_batch2": {
        "path": "tools/fixers/fix_steammanager_batch2.py",
        "desc": "v19.133f12e — SteamManager 收尾修复器 (02b java/c/b.java + b$1.java + j/ad.java 对照).",
        "category": "fixer",
        "writes": False,
    },
    "fix_steamshlib_throws": {
        "path": "tools/fixers/fix_steamshlib_throws.py",
        "desc": "SteamSharedLibraryLoader throws 修正 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_steamshlib_throws2": {
        "path": "tools/fixers/fix_steamshlib_throws2.py",
        "desc": "SteamSharedLibraryLoader.loadLibraries(String) 补 throws SteamException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_steamsocket_field_batch": {
        "path": "tools/fixers/fix_steamsocket_field_batch.py",
        "desc": "SteamSocket 家族字段类型修复 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_steamworks_throws_all": {
        "path": "tools/fixers/fix_steamworks_throws_all.py",
        "desc": "steamworks 全包补 throws SteamException (v19.133f96 清零收尾)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_steamworkshop2_batch": {
        "path": "tools/fixers/fix_steamworkshop2_batch.py",
        "desc": "v19.133f15c — SteamWorkshop.java 修复器 (02b java/c/g.java + i/b.java 对照).",
        "category": "fixer",
        "writes": False,
    },
    "fix_steamworkshop_batch": {
        "path": "tools/fixers/fix_steamworkshop_batch.py",
        "desc": "v19.133f12f — SteamWorkshop/SteamManager$1 收尾修复器.",
        "category": "fixer",
        "writes": False,
    },
    "fix_storagebackend": {
        "path": "tools/fixers/fix_storagebackend.py",
        "desc": "v19.133f11 StorageBackend 修复: 删 7 个重复方法 (02b e/c 单版本) + 补 a(String,boolean) isDirectory.",
        "category": "fixer",
        "writes": False,
    },
    "fix_storagebuilding_batch": {
        "path": "tools/fixers/fix_storagebuilding_batch.py",
        "desc": "f97y StorageBuilding 修复器: 02b units/e/n.java 直译对照 (e/n=StorageBuilding).",
        "category": "fixer",
        "writes": False,
    },
    "fix_subbuildingtype1_batch": {
        "path": "tools/fixers/fix_subbuildingtype1_batch.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_subbuildingtype2_batch": {
        "path": "tools/fixers/fix_subbuildingtype2_batch.py",
        "desc": "public boolean bj() {",
        "category": "fixer",
        "writes": False,
    },
    "fix_submarineunit_family_batch": {
        "path": "tools/fixers/fix_submarineunit_family_batch.py",
        "desc": "public ActionTargetType e() {",
        "category": "fixer",
        "writes": False,
    },
    "fix_tail7_batch": {
        "path": "tools/fixers/fix_tail7_batch.py",
        "desc": "收尾 7 条: RingBuffer 合成构造调用/SteamResult 赋值序/UnitTypeComparator 常量/AIStrategy a() 签名 (v19....",
        "category": "fixer",
        "writes": False,
    },
    "fix_teamchataction_batch": {
        "path": "tools/fixers/fix_teamchataction_batch.py",
        "desc": "f97bl TeamChatAction 家族修复器: 02b a/p.java + a/q.java 直译.",
        "category": "fixer",
        "writes": False,
    },
    "fix_teamcolortexture_batch": {
        "path": "tools/fixers/fix_teamcolortexture_batch.py",
        "desc": "TeamColorTexture 家族 15 条清零 (02b gameFramework/m/h 直译):",
        "category": "fixer",
        "writes": False,
    },
    "fix_teamtag_throws": {
        "path": "tools/fixers/fix_teamtag_throws.py",
        "desc": "TeamTag.parseSingleTag 补 throws bo (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_teamtagset_merge": {
        "path": "tools/fixers/fix_teamtagset_merge.py",
        "desc": "02 custom.h 双译名统一: TeamTagSet→UnitConfig 全库广播 (v19.112).",
        "category": "fixer",
        "writes": False,
    },
    "fix_teamunittracker_batch": {
        "path": "tools/fixers/fix_teamunittracker_batch.py",
        "desc": "TeamUnitTracker 战役: 02b game/s.java + units/as 接口 + units/d/l=CarrierUnit 接口 + units/d/...",
        "category": "fixer",
        "writes": False,
    },
    "fix_teamunittracker_batch2": {
        "path": "tools/fixers/fix_teamunittracker_batch2.py",
        "desc": "f97o TeamUnitTracker 批2: 02b game/s.java L47/L77 勘误 (f127 误标 ResourceComponent 修正).",
        "category": "fixer",
        "writes": False,
    },
    "fix_teamunittracker_batch3": {
        "path": "tools/fixers/fix_teamunittracker_batch3.py",
        "desc": "f97r TeamUnitTracker 批3 + EffectManager 修正: 02b s.java L77/f.java L173-192 对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_test_family": {
        "path": "tools/fixers/fix_test_family.py",
        "desc": "subs: [(old, new, count)] 逐项替换; MISS 仅警告 (预防性条目允许未命中), 全部 MISS 才跳过.",
        "category": "fixer",
        "writes": False,
    },
    "fix_test_patch": {
        "path": "tools/fixers/fix_test_patch.py",
        "desc": "测试族反向 patch 管线 (v19.110).",
        "category": "fixer",
        "writes": False,
    },
    "fix_testperformance_batch": {
        "path": "tools/fixers/fix_testperformance_batch.py",
        "desc": "v19.133f15 — TestPerformance + platform/net i/j 修复器 (02b a/a/ 子包对照).",
        "category": "fixer",
        "writes": False,
    },
    "fix_tilesetdef_batch": {
        "path": "tools/fixers/fix_tilesetdef_batch.py",
        "desc": "TilesetDef 家族 15 条清零 (02b game/b/j 直译):",
        "category": "fixer",
        "writes": False,
    },
    "fix_tilesetdef_throws": {
        "path": "tools/fixers/fix_tilesetdef_throws.py",
        "desc": "TilesetDef 3 方法补 throws MapException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_tilesetdef_throws2": {
        "path": "tools/fixers/fix_tilesetdef_throws2.py",
        "desc": "TilesetDef 3 方法补 throws MapException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_tilesetdef_throws3": {
        "path": "tools/fixers/fix_tilesetdef_throws3.py",
        "desc": "TilesetDef throws 修正 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_tilesetdef_throws4": {
        "path": "tools/fixers/fix_tilesetdef_throws4.py",
        "desc": "TilesetDef.a(String) 补 throws IOException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_tilesetdef_throws5": {
        "path": "tools/fixers/fix_tilesetdef_throws5.py",
        "desc": "TilesetDef.c() 补 throws IOException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_tmi_callsites_115c": {
        "path": "tools/fixers/fix_tmi_callsites_115c.py",
        "desc": "修复 03 全库 TMI 调用点误标 (v19.115c 批3b).",
        "category": "fixer",
        "writes": False,
    },
    "fix_tmi_texturemanager_115c": {
        "path": "tools/fixers/fix_tmi_texturemanager_115c.py",
        "desc": "修复 03 TextureManager.java — TMI 实现类方法名同步 (v19.115c 批3).",
        "category": "fixer",
        "writes": False,
    },
    "fix_tmxmaploader_a": {
        "path": "tools/fixers/fix_tmxmaploader_a.py",
        "desc": "TMXMapLoader.a(InputStream) 补 throws (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_tmxmaploader_throws": {
        "path": "tools/fixers/fix_tmxmaploader_throws.py",
        "desc": "TMXMapLoader 2 方法补 throws (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_tolist_iterator_batch": {
        "path": "tools/fixers/fix_tolist_iterator_batch.py",
        "desc": "TypedObjectListIterator 合成 next() 修复 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_transportergroup_batch1": {
        "path": "tools/fixers/fix_transportergroup_batch1.py",
        "desc": "v19.115v TransporterGroup 批1: 方法名/类型/调用点修复 (02b n.java 逐行铁证)",
        "category": "fixer",
        "writes": False,
    },
    "fix_treedecoration_batch": {
        "path": "tools/fixers/fix_treedecoration_batch.py",
        "desc": "TreeDecoration 家族 16 条清零 (02b units/al 直译:",
        "category": "fixer",
        "writes": False,
    },
    "fix_turretbuilding_batch": {
        "path": "tools/fixers/fix_turretbuilding_batch.py",
        "desc": "public float z() {",
        "category": "fixer",
        "writes": False,
    },
    "fix_type_aware_calls": {
        "path": "tools/fixers/fix_type_aware_calls.py",
        "desc": "按文件分组应用替换 (整文件一次性回写).",
        "category": "fixer",
        "writes": False,
    },
    "fix_type_fingerprint": {
        "path": "tools/fixers/fix_type_fingerprint.py",
        "desc": "02b symbol 方法 → 03 arity 匹配方法, 唯一最佳指纹命中 → 返回 03 方法名或 None.",
        "category": "fixer",
        "writes": False,
    },
    "fix_typedobjectlist_batch": {
        "path": "tools/fixers/fix_typedobjectlist_batch.py",
        "desc": "public GameObject get(int n) {",
        "category": "fixer",
        "writes": False,
    },
    "fix_ui_dup": {
        "path": "tools/fixers/fix_ui_dup.py",
        "desc": "v19.133f11 删 UnitInstance 重复 a(UnitInstance,float,MovementController) (L2826 副本) + Fire...",
        "category": "fixer",
        "writes": False,
    },
    "fix_uiac_batch": {
        "path": "tools/fixers/fix_uiac_batch.py",
        "desc": "f97q ui/ac 修复器: 02b gameFramework/f/ac.java 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_uielementbase_batch": {
        "path": "tools/fixers/fix_uielementbase_batch.py",
        "desc": "f97ak panels/UIElementBase 补全修复器: 02b gameFramework/f/a/l.java L37/L259 直译.",
        "category": "fixer",
        "writes": False,
    },
    "fix_uniformlocation_batch": {
        "path": "tools/fixers/fix_uniformlocation_batch.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_unitactionenum_batch": {
        "path": "tools/fixers/fix_unitactionenum_batch.py",
        "desc": "f97e UnitActionEnum F84 整写修复器: 02b units/r.java 直译 (4 常量 grass/sea/sand/dust).",
        "category": "fixer",
        "writes": False,
    },
    "fix_unitbuildstrategy_a_batch": {
        "path": "tools/fixers/fix_unitbuildstrategy_a_batch.py",
        "desc": "f97cl UnitBuildStrategy 补 a(UnitTypeHandle,MovementTypeEnum) 修复器: 02b a/d L84-99 直译.",
        "category": "fixer",
        "writes": False,
    },
    "fix_unitbuildstrategy_batch": {
        "path": "tools/fixers/fix_unitbuildstrategy_batch.py",
        "desc": "f82 UnitBuildStrategy 家族修复器 (02b game/a/d.java 对照, 7 条清零)",
        "category": "fixer",
        "writes": False,
    },
    "fix_unitbuildstrategyentry_batch": {
        "path": "tools/fixers/fix_unitbuildstrategyentry_batch.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_unitcountcondition_batch": {
        "path": "tools/fixers/fix_unitcountcondition_batch.py",
        "desc": "UT_ANCHOR = \" public strictfp UnitTransform aF() {\"",
        "category": "fixer",
        "writes": False,
    },
    "fix_unitinfopanel_c_batch": {
        "path": "tools/fixers/fix_unitinfopanel_c_batch.py",
        "desc": "UnitInfoPanel ThemeColors.c -> accentColor (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_unitinstance_staticinit": {
        "path": "tools/fixers/fix_unitinstance_staticinit.py",
        "desc": "UnitInstance 静态块补字段初始化 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_unitinstance_staticinit2": {
        "path": "tools/fixers/fix_unitinstance_staticinit2.py",
        "desc": "UnitInstance 静态块批量补残留 final 初始化 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_unitinstance_tail2": {
        "path": "tools/fixers/fix_unitinstance_tail2.py",
        "desc": "UnitInstance dr 静态初始化 + Q(int) throws (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_unitinstance_throws": {
        "path": "tools/fixers/fix_unitinstance_throws.py",
        "desc": "UnitInstance 3 方法补 throws (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_unitparameter_batch": {
        "path": "tools/fixers/fix_unitparameter_batch.py",
        "desc": "f97t UnitParameter 修复器: 02b custom/f.java L171-201 直译对照 (变量拆分).",
        "category": "fixer",
        "writes": False,
    },
    "fix_unitregistry_cleanup": {
        "path": "tools/fixers/fix_unitregistry_cleanup.py",
        "desc": "v19.130 UnitRegistry+EffectConfig 清理.",
        "category": "fixer",
        "writes": False,
    },
    "fix_unitstatetracker_batch": {
        "path": "tools/fixers/fix_unitstatetracker_batch.py",
        "desc": "UnitStateTracker 战役: 02b f/an + f/x(BridgeUnit) + f/ao 类型/方法还原 + EffectManager 补 b(f,f).",
        "category": "fixer",
        "writes": False,
    },
    "fix_unittrait_del": {
        "path": "tools/fixers/fix_unittrait_del.py",
        "desc": "f97an 删 units/UnitTrait$1-6 残留修复器.",
        "category": "fixer",
        "writes": False,
    },
    "fix_unitturret_fields": {
        "path": "tools/fixers/fix_unitturret_fields.py",
        "desc": "fix_unitturret_fields — UnitTurret 字段调用点同步 (v19.108 Batch 2)",
        "category": "fixer",
        "writes": False,
    },
    "fix_unittypehandle_chain": {
        "path": "tools/fixers/fix_unittypehandle_chain.py",
        "desc": "v19.128 UnitTypeHandle 接口链战役: 02b as 接口返回 d.b=ResourceComponent 铁证链式修正.",
        "category": "fixer",
        "writes": False,
    },
    "fix_unittypehandle_chain2": {
        "path": "tools/fixers/fix_unittypehandle_chain2.py",
        "desc": "v19.128b 反弹清理: UnitTypeHandle 链修复的级联清理.",
        "category": "fixer",
        "writes": False,
    },
    "fix_unittypehandle_chain3": {
        "path": "tools/fixers/fix_unittypehandle_chain3.py",
        "desc": "v19.128c ResourceComponent 收尾 + MUR import + EffectManager 补 do_b(f,am,double).",
        "category": "fixer",
        "writes": False,
    },
    "fix_updatechecker_del": {
        "path": "tools/fixers/fix_updatechecker_del.py",
        "desc": "f97av 删 units/UpdateChecker$1-5 残留修复器.",
        "category": "fixer",
        "writes": False,
    },
    "fix_utility_ab": {
        "path": "tools/fixers/fix_utility_ab.py",
        "desc": "utility/ab.java 战役 (v19.115n): 02b utility/ab.java 1156 行完整直译 + 依赖补全",
        "category": "fixer",
        "writes": False,
    },
    "fix_utility_ab_throws": {
        "path": "tools/fixers/fix_utility_ab_throws.py",
        "desc": "utility/ab.java 补 throws (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_utility_ab_throws2": {
        "path": "tools/fixers/fix_utility_ab_throws2.py",
        "desc": "utility/ab.java bo throws 批量 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_utility_ab_throws3": {
        "path": "tools/fixers/fix_utility_ab_throws3.py",
        "desc": "utility/ab.java 构造器+aj 方法 throws (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_utility_ah_batch": {
        "path": "tools/fixers/fix_utility_ah_batch.py",
        "desc": "ZipEntry zipEntry = enumeration.nextElement();",
        "category": "fixer",
        "writes": False,
    },
    "fix_utility_i_catch": {
        "path": "tools/fixers/fix_utility_i_catch.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "fix_utilityal_batch": {
        "path": "tools/fixers/fix_utilityal_batch.py",
        "desc": "f89 utility/al 家族修复器 (02b utility/al.java 对照, 6 条清零)",
        "category": "fixer",
        "writes": False,
    },
    "fix_utilityl_batch": {
        "path": "tools/fixers/fix_utilityl_batch.py",
        "desc": "f97cb utility/l.java 修复器: 02b utility/l.java 直译对照 (BufferedReader 副本).",
        "category": "fixer",
        "writes": False,
    },
    "fix_variablescope_3": {
        "path": "tools/fixers/fix_variablescope_3.py",
        "desc": "VariableScope$MemoryWriter variableScope$MemoryWriter = new VariableScope$MemoryWriter();",
        "category": "fixer",
        "writes": False,
    },
    "fix_variablescope_dynamic": {
        "path": "tools/fixers/fix_variablescope_dynamic.py",
        "desc": "VariableScope.readInDynamicData 补 throws IOException (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_varn_clean": {
        "path": "tools/fixers/fix_varn_clean.py",
        "desc": "R5: StringBuilder varN = new StringBuilder() → sb.",
        "category": "fixer",
        "writes": False,
    },
    "fix_versionchecker_throws": {
        "path": "tools/fixers/fix_versionchecker_throws.py",
        "desc": "VersionChecker.a(String,String) + ModUnitRegistry.a(InputNetStream) throws (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_versionchecker_throws2": {
        "path": "tools/fixers/fix_versionchecker_throws2.py",
        "desc": "VersionChecker 2 方法补 throws bo (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_void_ctor": {
        "path": "tools/fixers/fix_void_ctor.py",
        "desc": "构造器去 void (R4 循环七) — `void <类名>(...)` 形态的 CFR 构造器残留。",
        "category": "fixer",
        "writes": False,
    },
    "fix_wallbuilding_batch": {
        "path": "tools/fixers/fix_wallbuilding_batch.py",
        "desc": "public float z() {",
        "category": "fixer",
        "writes": False,
    },
    "fix_wav_audioutils_batch": {
        "path": "tools/fixers/fix_wav_audioutils_batch.py",
        "desc": "Wav$WavInputStream AudioUtils -> s (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_wavsound_batch": {
        "path": "tools/fixers/fix_wavsound_batch.py",
        "desc": "f97be Wav$Sound/Wav$Music 修复器: 02b java/audio/lwjgl 直译对照.",
        "category": "fixer",
        "writes": False,
    },
    "fix_weaponconfig_batch": {
        "path": "tools/fixers/fix_weaponconfig_batch.py",
        "desc": "WeaponConfig 家族 18 条清零 (02b custom/as 直译 + javap 铁证):",
        "category": "fixer",
        "writes": False,
    },
    "fix_weaponconfig_fields": {
        "path": "tools/fixers/fix_weaponconfig_fields.py",
        "desc": "fix_weaponconfig_fields — WeaponConfig 字段调用点同步 (v19.108 Batch 3)",
        "category": "fixer",
        "writes": False,
    },
    "fix_weaponconfig_tail2": {
        "path": "tools/fixers/fix_weaponconfig_tail2.py",
        "desc": "try {",
        "category": "fixer",
        "writes": False,
    },
    "fix_weaponconfig_throws": {
        "path": "tools/fixers/fix_weaponconfig_throws.py",
        "desc": "WeaponConfig/CachedWriter throws 修复 (v19.133f96)。",
        "category": "fixer",
        "writes": False,
    },
    "fix_weapons_actionfilter1_del": {
        "path": "tools/fixers/fix_weapons_actionfilter1_del.py",
        "desc": "f97z 删 weapons/ActionFilter$1 误建副本修复器.",
        "category": "fixer",
        "writes": False,
    },
    "fix_webapiclient_b1_batch": {
        "path": "tools/fixers/fix_webapiclient_b1_batch.py",
        "desc": "import sys",
        "category": "fixer",
        "writes": False,
    },
    "main_class_mappings": {
        "path": "tools/fixers/main_class_mappings.py",
        "desc": "Main.class 反混淆成果回写映射库: 新增 GameLauncher 成员映射 + 修正与用法矛盾的既有映射.",
        "category": "fixer",
        "writes": False,
    },
    "merge_lost": {
        "path": "tools/fixers/merge_lost.py",
        "desc": "把 02 中 03 缺失的成员合并进 03 文本.",
        "category": "fixer",
        "writes": False,
    },
    "run_verify_cycle": {
        "path": "tools/fixers/run_verify_cycle.py",
        "desc": "运行时验证一键循环 (v19.111, P3) — 装组→启动→主链路检测→触发测试→判定→坏类自动隔离.",
        "category": "fixer",
        "writes": False,
    },
    "runtime_patch_batch": {
        "path": "tools/fixers/runtime_patch_batch.py",
        "desc": "03 源码 → 02 名反向 (类名 + import + 全限定 + 裸引用 + 构造器)",
        "category": "fixer",
        "writes": False,
    },
    "b2_reverse_map_check": {
        "path": "tools/utils/b2_reverse_map_check.py",
        "desc": "语义类名 → [(02包, 02名)]; 混淆类名 → 语义名",
        "category": "util",
        "writes": False,
    },
    "backfeed_03": {
        "path": "tools/utils/backfeed_03.py",
        "desc": "backfeed_03 — 映射反哺 03: 把 supplement 修正后的语义名应用到 03 源码 (v19.106)",
        "category": "util",
        "writes": False,
    },
    "class_evidence": {
        "path": "tools/utils/class_evidence.py",
        "desc": "提取单个类的证据.",
        "category": "util",
        "writes": False,
    },
    "comment_audit": {
        "path": "tools/utils/comment_audit.py",
        "desc": "注释检测 v1: 03-deobfuscated 注释覆盖率统计。",
        "category": "util",
        "writes": False,
    },
    "debug_script": {
        "path": "tools/utils/debug_script.py",
        "desc": "发送一条 script 命令, 返回服务器响应文本.",
        "category": "util",
        "writes": False,
    },
    "dump_rc": {
        "path": "tools/utils/dump_rc.py",
        "desc": "dump_rc — 回放 rc 记录原始字节 dump (v19.96)",
        "category": "util",
        "writes": False,
    },
    "gamelib_audit": {
        "path": "tools/utils/gamelib_audit.py",
        "desc": "在 03 中定位类的文件. 返回 (status, project_name, note).",
        "category": "util",
        "writes": False,
    },
    "gen_error_list": {
        "path": "tools/utils/gen_error_list.py",
        "desc": "剩余错误总清单生成器: compile-errors.csv → docs/deobfuscation/剩余错误总清单-5283.md",
        "category": "util",
        "writes": False,
    },
    "identify_readable": {
        "path": "tools/utils/identify_readable.py",
        "desc": "指纹: 字符串字面量集合 + 方法声明数.",
        "category": "util",
        "writes": False,
    },
    "map_scriptengine": {
        "path": "tools/utils/map_scriptengine.py",
        "desc": "map_scriptengine — ScriptEngine/ScriptContext 成员映射落库 (v19.105)",
        "category": "util",
        "writes": False,
    },
    "parse_rc_v96": {
        "path": "tools/utils/parse_rc_v96.py",
        "desc": "按写侧格式解析 v96 命令, 返回字段字典; 打印每字段偏移.",
        "category": "util",
        "writes": False,
    },
    "replay_parser": {
        "path": "tools/utils/replay_parser.py",
        "desc": "按 e.java 的 a(k) 反序列化 (版本门控) 解析一条命令, 返回字段字典.",
        "category": "util",
        "writes": False,
    },
    "runtime_evidence": {
        "path": "tools/utils/runtime_evidence.py",
        "desc": "runtime_evidence — 运行时日志证据消化 (v19.90)",
        "category": "util",
        "writes": False,
    },
    "update_doc_v19.107_stage2": {
        "path": "tools/utils/update_doc_v19.107_stage2.py",
        "desc": "update_doc_v19.107_stage2 — 追加脚本化批量修复阶段到会话记录 (v19.107 阶段2)",
        "category": "util",
        "writes": False,
    },
}

# ── 命令实现 ──────────────────────────────────────────────

def fixer_group(name):
    """从修复器文件名提取战役组名: fix_mur_batch5 → mur, fix_netengine_batch2k → netengine"""
    s = name
    if s.startswith("fix_"):
        s = s[4:]
    s = re.sub(r"_(?:batch|b)?\d+$", "", s)  # _batch5/_b2/_5 尾部
    s = re.sub(r"_115$", "", s)
    m = re.match(r"([a-z0-9]+)", s)
    return m.group(1) if m else s


def cmd_list():
    """列出工具 (v19.133f98 优化: 常驻全列 + 战役修复器折叠)

    参数:
        --all          全量列出 616 个 (含战役修复器)
        --group <组>   只列指定战役组 (如 mur/netengine/logicbooleans)
        --phase        按 Phase 分组 (A 清零战役 / B 构建运行 / 常驻)
    """
    args = [a for a in sys.argv[2:]]
    show_all = "--all" in args
    group = None
    if "--group" in args:
        group = args[args.index("--group") + 1]
    by_phase = "--phase" in args

    resident = {"core", "gate", "util", "resolver", "analyze", "capture"}
    fixer_items = []
    resident_items = []
    for name, info in TOOLS.items():
        (resident_items if info["category"] in resident else fixer_items).append((name, info))

    print(f"\n{'='*70}")
    print(f"  tools/ — {len(TOOLS)} 个注册工具 (常驻 {len(resident_items)} + 战役修复器 {len(fixer_items)})")
    print(f"{'='*70}")

    cat_names = {
        "core": "核心引擎 (core/)", "gate": "门禁/报告 (gates/)",
        "util": "小工具 (utils/)", "resolver": "消歧义 (resolvers/)",
        "analyze": "分析 (analyze/)", "capture": "运行时捕捉 (capture/)",
    }

    if by_phase:
        # Phase 分组视角
        phases = {"常驻工具 (B5 维护期)": resident_items, "Phase A 清零战役修复器": fixer_items}
        for label, items in phases.items():
            print(f"\n  [{label}] ({len(items)} 个)")
            for name, info in sorted(items)[:60]:
                rw = "✎" if info["writes"] else "🔍"
                grp = fixer_group(name) if info["category"] == "fixer" else ""
                tag = f" [{grp}]" if grp else ""
                print(f"    {rw} {name:25s}{tag} — {info['desc'][:60]}")
            if len(items) > 60:
                print(f"    ... 其余 {len(items)-60} 个 (--all 全量)")
        return

    # 常驻工具全列
    by_cat = defaultdict(list)
    for name, info in resident_items:
        by_cat[info["category"]].append((name, info))
    for cat in ["core", "gate", "util", "resolver", "analyze", "capture"]:
        items = by_cat.get(cat, [])
        if not items:
            continue
        print(f"\n  [{cat_names.get(cat, cat)}]")
        for name, info in sorted(items):
            rw = "✎" if info["writes"] else "🔍"
            print(f"    {rw} {name:25s} — {info['desc'][:80]}")

    # 战役修复器: 按组折叠
    print(f"\n  [战役修复器 (fixers/, {len(fixer_items)} 个)]")
    if group:
        g_items = [(n, i) for n, i in fixer_items if fixer_group(n) == group]
        if not g_items:
            print(f"    组 '{group}' 无工具 (可用组: {sorted({fixer_group(n) for n, _ in fixer_items})[:20]})")
        for name, info in sorted(g_items):
            rw = "✎" if info["writes"] else "🔍"
            print(f"    {rw} {name:25s} — {info['desc'][:80]}")
    elif show_all:
        for name, info in sorted(fixer_items):
            rw = "✎" if info["writes"] else "🔍"
            print(f"    {rw} {name:25s} [{fixer_group(name)}] — {info['desc'][:60]}")
    else:
        groups = defaultdict(list)
        for name, info in fixer_items:
            groups[fixer_group(name)].append(name)
        top = sorted(groups.items(), key=lambda x: -len(x[1]))[:15]
        print(f"    战役组 ({len(groups)} 组):")
        for g, names in top:
            print(f"      {g:16s} {len(names):3d} 个  例: {names[0][:30]}")
        other = sum(len(v) for k, v in groups.items() if k not in [t[0] for t in top])
        if other:
            print(f"      ... 其余 {len(groups)-len(top)} 组 {other} 个")
        print(f"    用法: --group <组> 查组 | --all 全量 | --phase 阶段分组")

def cmd_check():
    """全面健康检查"""
    print(f"\n{'='*70}")
    print(f"  tools/manager.py check — 健康检查")
    print(f"{'='*70}")

    issues = []
    warnings = []
    passed = 0
    total = 0
    check_all = "--all" in sys.argv[2:]
    resident = {"core", "gate", "util", "resolver", "analyze", "capture"}
    targets = [(n, i) for n, i in TOOLS.items() if check_all or i["category"] in resident]
    if not check_all:
        print(f"(默认常驻 {len(targets)} 个; --all 全量 {len(TOOLS)} 个)")

    # 1. AST语法检查
    print(f"\n[1] AST 语法检查")
    for name, info in sorted(targets):
        total += 1
        fp = ROOT / info["path"]
        if not fp.exists():
            issues.append(f"{name}: 文件不存在 {info['path']}")
            continue
        try:
            with open(fp, encoding='utf-8') as f:
                ast.parse(f.read())
            passed += 1
        except SyntaxError as e:
            issues.append(f"{name}: 语法错误 — {e}")
        except Exception as e:
            issues.append(f"{name}: 读取失败 — {e}")
    print(f"  {passed}/{total} 通过")

    # 2. rwlib导入检查
    print(f"\n[2] rwlib 导入检查")
    no_rwlib = []
    for name, info in sorted(TOOLS.items()):
        fp = ROOT / info["path"]
        if not fp.exists(): continue
        with open(fp, encoding='utf-8', errors='replace') as f:
            content = f.read()
        if 'from rwlib' not in content and 'import rwlib' not in content:
            no_rwlib.append(name)
    if no_rwlib:
        warnings.append(f"未使用rwlib ({len(no_rwlib)}): {', '.join(no_rwlib[:10])}...")
        print(f"  合规: {len(TOOLS)-len(no_rwlib)}/{len(TOOLS)}, 不合规: {len(no_rwlib)}")
    else:
        print(f"  全部合规!")
        passed += len(TOOLS)

    # 3. CWD相对路径检查
    print(f"\n[3] CWD 相对路径检查")
    cwd_scripts = []
    for name, info in sorted(TOOLS.items()):
        fp = ROOT / info["path"]
        if not fp.exists(): continue
        with open(fp, encoding='utf-8', errors='replace') as f:
            content = f.read()
        # Check for CWD-relative patterns
        if re.search(r'= "(03-deobfuscated|02-decompiled|01-classes|mappings)"', content):
            cwd_scripts.append(name)
    if cwd_scripts:
        issues.append(f"CWD相对路径 ({len(cwd_scripts)}): {', '.join(cwd_scripts)}")
        print(f"  BROKEN: {len(cwd_scripts)} 脚本使用CWD相对路径")
    else:
        print(f"  全部通过!")

    # 4. 硬编码javap/javac检查
    print(f"\n[4] 硬编码 javap/javac 路径检查")
    hardcoded = []
    for name, info in sorted(TOOLS.items()):
        fp = ROOT / info["path"]
        if not fp.exists(): continue
        with open(fp, encoding='utf-8', errors='replace') as f:
            content = f.read()
        if re.search(r"JAVAP\s*=\s*['\"]C:/|JAVAC\s*=\s*['\"]C:/", content):
            hardcoded.append(name)
    if hardcoded:
        warnings.append(f"硬编码javap/javac路径 ({len(hardcoded)}): {', '.join(hardcoded)}")
        print(f"  硬编码: {len(hardcoded)}")
    else:
        print(f"  全部通过!")

    # 5. 引用已删除文件检查
    print(f"\n[5] 已删除文件引用检查")
    deleted_refs = 0
    for name, info in sorted(TOOLS.items()):
        fp = ROOT / info["path"]
        if not fp.exists(): continue
        with open(fp, encoding='utf-8', errors='replace') as f:
            content = f.read()
        for deleted in ['enhanced-fields.csv', 'mapping-priority.csv']:
            if deleted in content and 'for csv_name' not in content:
                warnings.append(f"{name}: 引用已删除文件 {deleted}")
                deleted_refs += 1
    print(f"  引用已删文件: {deleted_refs}" if deleted_refs else f"  全部通过!")

    # 6. supplement.csv 完整性检查
    print(f"\n[6] supplement.csv 完整性检查")
    try:
        from rwlib.config import SUPPLEMENT_CSV
        with open(SUPPLEMENT_CSV, encoding='utf-8') as f:
            reader = csv.DictReader(f)
            rows = list(reader)
        # 只查超长 notes; \"\" 是 CSV 合法引号转义 (描述空字符串字面量), 非损坏 — v19.114 修正
        bad = sum(1 for r in rows if len(r.get('notes') or '') > 10000)
        size_mb = SUPPLEMENT_CSV.stat().st_size / 1024 / 1024
        print(f"  条目: {len(rows)}, 损坏: {bad}, 大小: {size_mb:.1f}MB")
        if bad > 0:
            issues.append(f"supplement.csv: {bad} 行损坏")
        else:
            print(f"  [OK] 数据干净")
    except Exception as e:
        issues.append(f"supplement.csv 读取失败: {e}")

    # 总结
    print(f"\n{'='*70}")
    print(f"  结果: {len(issues)} 错误, {len(warnings)} 警告")
    if issues:
        print(f"\n  [ERROR] 错误:")
        for i in issues:
            print(f"     - {i}")
    if warnings:
        print(f"\n  [WARN] 警告:")
        for w in warnings:
            print(f"     - {w}")
    if not issues:
        print(f"  [OK] 全部通过!")
    print()


def cmd_status():
    """项目状态报告"""
    print(f"\n{'='*70}")
    print(f"  Rusted Warfare v1.15 — 项目状态")
    print(f"  {time.strftime('%Y-%m-%d %H:%M')}")
    print(f"{'='*70}")

    # 文件统计
    print(f"\n── 文件统计")
    from rwlib.config import CLASSES_DIR, DECOMPILED_DIR, DEOBFUSCATED_DIR, MAPPINGS_DIR, DOCS_DIR

    def count_files(d, ext):
        return sum(1 for _ in Path(d).rglob(f"*.{ext}")) if Path(d).exists() else 0

    stats = [
        ("01-classes", count_files(CLASSES_DIR, "class"), ".class"),
        ("02-decompiled", count_files(DECOMPILED_DIR, "java"), ".java (混淆名)"),
        ("03-deobfuscated", count_files(DEOBFUSCATED_DIR, "java"), ".java (可读名)"),
        ("docs", count_files(DOCS_DIR, "md"), ".md 文档"),
        ("tools/ 活跃脚本", len(TOOLS), ".py"),
        ("supplement.csv", 0, ""),
    ]
    for label, cnt, unit in stats:
        if label == "supplement.csv":
            from rwlib.config import SUPPLEMENT_CSV
            if SUPPLEMENT_CSV.exists():
                sz = SUPPLEMENT_CSV.stat().st_size / 1024 / 1024
                with open(SUPPLEMENT_CSV, encoding='utf-8') as f:
                    rows = sum(1 for _ in csv.DictReader(f))
                print(f"  {label:25s} {rows:5d} 条目  {sz:.1f}MB")
            else:
                print(f"  {label:25s} 不存在!")
        else:
            print(f"  {label:25s} {cnt:5d} {unit}")

    # 映射统计
    print(f"\n── 映射统计")
    from rwlib.config import SUPPLEMENT_CSV, CLASS_DISCOVERIES
    if SUPPLEMENT_CSV.exists():
        with open(SUPPLEMENT_CSV, encoding='utf-8') as f:
            rows = list(csv.DictReader(f))
        fields = sum(1 for r in rows if r['type'] == 'field')
        methods = sum(1 for r in rows if r['type'] == 'method')
        verified = sum(1 for r in rows if r.get('verified','') not in ('', 'unverified'))
        print(f"  总映射:       {len(rows)}")
        print(f"  字段:         {fields}")
        print(f"  方法:         {methods}")
        print(f"  已验证:       {verified} ({100*verified/len(rows):.1f}%)")

    if CLASS_DISCOVERIES.exists():
        with open(CLASS_DISCOVERIES, encoding='utf-8') as f:
            cd_rows = list(csv.DictReader(f))
        print(f"  类映射:       {len(cd_rows)}")

    # Git
    print(f"\n── Git")
    try:
        result = subprocess.run(["git", "log", "--oneline"], capture_output=True, text=True, cwd=ROOT, encoding='utf-8', errors='replace')
        commits = len(result.stdout.strip().split('\n')) if result.stdout.strip() else 0
        result2 = subprocess.run(["git", "log", "--oneline", "-1"], capture_output=True, text=True, cwd=ROOT)
        print(f"  Commits:      {commits}")
        print(f"  HEAD:         {result2.stdout.strip()}")
    except:
        print(f"  (git 不可用)")

    # 工具健康
    print(f"\n── 工具健康")
    no_rwlib = 0
    hardcoded = 0
    cwd_broken = 0
    for name, info in TOOLS.items():
        fp = ROOT / info["path"]
        if not fp.exists(): continue
        with open(fp, encoding='utf-8', errors='replace') as f:
            content = f.read()
        if 'from rwlib' not in content: no_rwlib += 1
        if re.search(r"JAVAP\s*=\s*['\"]C:/", content): hardcoded += 1
        if re.search(r'= "(03-deobfuscated|02-decompiled|01-classes|mappings)"', content): cwd_broken += 1

    print(f"  合规(rwlib):  {len(TOOLS)-no_rwlib}/{len(TOOLS)}")
    print(f"  硬编码javap:  {hardcoded}")
    print(f"  CWD相对路径:  {cwd_broken}")
    health = "良好" if no_rwlib == 0 and hardcoded == 0 and cwd_broken == 0 else "需改进"
    print(f"  综合:         {health}")
    print()


def cmd_run(tool_name):
    """运行指定工具"""
    if tool_name not in TOOLS:
        print(f"未知工具: {tool_name}")
        print(f"可用工具: {', '.join(sorted(TOOLS.keys()))}")
        sys.exit(1)

    info = TOOLS[tool_name]
    fp = ROOT / info["path"]
    if not fp.exists():
        print(f"文件不存在: {fp}")
        sys.exit(1)

    print(f"运行: {tool_name} ({info['path']})")
    result = subprocess.run([sys.executable, str(fp), "--dry-run"],
                          cwd=ROOT, capture_output=True, text=True)
    print(result.stdout[-2000:] if len(result.stdout) > 2000 else result.stdout)
    if result.stderr:
        print(f"STDERR:\n{result.stderr[-1000:]}")
    sys.exit(result.returncode)


# ── 入口 ──────────────────────────────────────────────────
if __name__ == "__main__":
    if len(sys.argv) < 2:
        print(__doc__)
        print("命令: list | check | status | run <name>")
        sys.exit(0)

    cmd = sys.argv[1]
    if cmd == "list":
        cmd_list()
    elif cmd == "check":
        cmd_check()
    elif cmd == "status":
        cmd_status()
    elif cmd == "run":
        if len(sys.argv) < 3:
            print("用法: python tools/manager.py run <tool-name>")
            sys.exit(1)
        cmd_run(sys.argv[2])
    else:
        print(f"未知命令: {cmd}")
        print("可用: list | check | status | run <name>")
        sys.exit(1)
