#!/usr/bin/env python3
"""v19.133f10 文档收尾: D1 口径同步 + CLAUDE.md 版本头/状态表 + 会话记录 + PLAN.md + 旧会话标注.
Usage: python tools/fixers/finish_docs_f10.py"""
import io
import re
from pathlib import Path

ROOT = Path(r'C:\Users\28210\Downloads\Rusted Warfare\rw源码逆向')


def rd(p: Path) -> str:
    return io.open(p, encoding='utf-8').read()


def wr(p: Path, t: str) -> None:
    io.open(p, 'w', encoding='utf-8', newline='').write(t)


# ---------- D1: 四文件数字同步 ----------
NEW = "2,039"
OLDPCT = "94.34"
NEWPCT = "95.07"
F10 = "v19.133f10 (units/commands 家族清零)"

claude = rd(ROOT / 'CLAUDE.md')
# 版本头
old_head = '> **最新**: v19.133f9 战役 (slots/commands 家族清零) | 2026-08-26 | 41,402 → **2,345** (-94.34%): slots 家族 59 清零 (02b units/d/a: d/e/f/h 字段 b 类型/包前缀幻觉/MovementController/SoundRegistry/turretAngle) + BuildSlot abstract 化补 10 方法 + AIUnitActionUtils 整写 (02b game/a/f) + TextureManagerInterface D→a 铁证 + 幻觉清除 (UnitType.cL/a(TeamTag)/getMaxMoveDistance); 详见 docs/deobfuscation/sessions/PHASE-A-v19.133f9-slots-commands家族清零.md'
new_head = '> **最新**: v19.133f10 战役 (units/commands 家族清零) | 2026-08-27 | 41,402 → **2,039** (-95.07%): $N 家族 extends 误映射修复 (f$1/p$1/v$1/q$1/q$2→AbstractBuildAction/GameAction, 02b 锚点) + a()/b()/c()→getDescription/getLabel/getResourceCost 全量改名 (javac 逐层暴露) + ResourceComponent 误建副本裁决 (custom.d.b=CustomActionBase, 02b b.java L17 m 铁证, 广播 UnitInstance.bx/by+UnitRegistry.ah) + ExperimentalUnit=02b d/d 全文对照 (MapEngine placementCheck/ignorePlacementCheck 补字段) + PowerGeneratorUnit=02b d/h + UnitActionHelper=02b d/q (F25 $N 数字污染) + utility/UnitRegistry=02b u 误建副本 (合成访问器) + UnitRegistryIterator=02b v (合成构造) + PowerShell Set-Content BOM 陷阱清理 23 文件; 详见 docs/deobfuscation/sessions/PHASE-A-v19.133f10-units-commands家族清零.md'
if old_head in claude:
    claude = claude.replace(old_head, new_head)
    print('CLAUDE head OK')
else:
    print('CLAUDE head MISS')

# 状态表
claude = claude.replace('| **old_deobfuscated** (主) | 41,402 (真实基数, v19.1) | **2,345** (-94.34%) | v19.133f9 (slots/commands 家族清零) |',
                        '| **old_deobfuscated** (主) | 41,402 (真实基数, v19.1) | **2,039** (-95.07%) | ' + F10 + ' |')
claude = claude.replace('当前剩余 2,345 errors 主要为成员级联 (cannot find symbol) + incompatible 对族 + 残余大文件 (InGameActivity 27/resources/c 27/SendWorker 26 等)。',
                        '当前剩余 2,039 errors 主要为成员级联 (cannot find symbol) + incompatible 对族 + 残余大文件 (InGameActivity 27/resources/c 27/SendWorker 26 等)。')
claude = claude.replace('| 编译错误 (old_deobfuscated) | **2,345** (41,402 → -94.34%) |',
                        '| 编译错误 (old_deobfuscated) | **2,039** (41,402 → -95.07%) |')
claude = claude.replace('> 编译基线 41,402 → 2,345 errors (-94.34%), 40+ 损伤家族已修复, patch 58 类游戏内实测,',
                        '> 编译基线 41,402 → 2,039 errors (-95.07%), 40+ 损伤家族已修复, patch 58 类游戏内实测,')
claude = claude.replace('## 当前状态 (v19.133f8)', '## 当前状态 (v19.133f10)')
# 工具登记 (D6)
claude = claude.replace('│   │   ├── [v19.112] fix_teamtagset_merge.py ← TeamTagSet→UnitConfig 双译名统一广播 (幂等精确替换)',
                        '│   │   ├── [v19.133f10] fix_commands_batch3.py ← $N 家族 extends 误映射 (f$1/p$1/v$1/q$1/q$2→AbstractBuildAction/GameAction) + a()/b()/c() 语义名 (02b $N 逐方法锚点)\n│   │   ├── [v19.133f10] fix_commands_batch4.py ← ResourceComponent 误建副本裁决 → CustomActionBase 广播 (02b custom/d/b L17 m 铁证)\n│   │   ├── [v19.133f10] fix_commands_batch5.py ← UnitActionHelper 家族 (02b d/q: F25 $N 数字污染/a(GameAction,...) 参数)\n│   │   ├── [v19.133f10] fix_commands_batch6.py ← $N 家族 a()/b()/c()→getDescription/getLabel/getResourceCost 批量改名\n│   │   ├── [v19.112] fix_teamtagset_merge.py ← TeamTagSet→UnitConfig 双译名统一广播 (幂等精确替换)')
wr(ROOT / 'CLAUDE.md', claude)
print('CLAUDE.md done')

# README.md / docs/README.md / docs/STATUS.md
for rel in ['README.md', 'docs/README.md', 'docs/STATUS.md']:
    p = ROOT / rel
    t = rd(p)
    t2 = t.replace('2,345', NEW).replace(OLDPCT, NEWPCT)
    if t2 != t:
        wr(p, t2)
        print(f'{rel} numbers OK')
    else:
        print(f'{rel} NO CHANGE')

# ---------- D2: 会话记录 ----------
session = ROOT / 'docs/deobfuscation/sessions/PHASE-A-v19.133f10-units-commands家族清零.md'
body = """# PHASE-A v19.133f10 战役: units/commands 家族清零

> 日期: 2026-08-27 | 基线 2,211 → **2,039** (-95.07%, 41,402 起点) | old_deobfuscated 分支
> 上承: [PHASE-A-v19.133f9-slots-commands家族清零.md](PHASE-A-v19.133f9-slots-commands家族清零.md)

## 战役目标
units/commands 家族 (Experimental*/Custom*/UnitActionHelper/PowerGeneratorUnit/FabricatorUnit 等) 编译错误清零。

## 批次轨迹

| 批次 | 内容 | 错误数 | 说明 |
|------|------|--------|------|
| 起点 | units.d 批2 剩余 61 (ESU 重复/ELU/ELF/CBU/ExperimentalUnit) | 2,211 | checkpoint 基线 |
| 批1 | ESU 删误版 a(InputNetStream) + ELU 双 @Override + ELF return w + CustomGroundUnit 3 处 + CBU getLabel | — | 02b d/b=CustomGroundUnit 确认 |
| 批2 | ExperimentalUnit=02b d/d 全文对照 12 处 (MapEngine placementCheck/airSolver 补字段/显式迭代) + EWU previewTexture (BuildSlot 补字段) | — | 02b d/d 实为 ExperimentalUnit (原映射 BuildSlot 勘误) |
| 批3 | $N 家族 5 文件 extends 误映射 (ExperimentalWallUnit/AutoRepairCallback→AbstractBuildAction/GameAction) | 1,759 | fix_commands_batch3.py |
| 批4 | ResourceComponent 误建副本裁决 → CustomActionBase 广播 (UnitInstance.bx/by/UnitRegistry.ah/UnitTypeHandle.u) + UnitType 补 g(UnitInstance) | — | 02b custom/d/b L17 m 铁证 |
| 批5 | UnitActionHelper 家族 (F25 $N 数字污染/buildState→j/a(GameAction,...)) | — | fix_commands_batch5.py |
| 批6 | $N 家族 a()/b()/c() 语义名批量改名 | 1,559→1,558 | fix_commands_batch6.py; javac 逐层暴露 |
| BOM 清理 | PowerShell Set-Content BOM 陷阱 23 文件 | 59→2,050 | UnitType 语法错误掩盖真相 |
| 收尾 | UnitType 注释吞 3 处 + utility/UnitRegistry=02b u 误建副本 (合成访问器) + UnitRegistryIterator=02b v (合成构造) + CustomUnitType 注释吞 3 处 | 2,039 | 稳定全量 |

## 方法论沉淀

1. **F28 PowerShell BOM 陷阱**: PowerShell `Set-Content -Encoding UTF8` 写入 UTF-8 **BOM**, javac 报
   `illegal character: '\\ufeff'` + 全文件语法错误 (UnitType 一度 1,557 错)。修复后必须用 Python 批量清理
   BOM, 或全部改用 Python 写文件。
2. **F56 重演 (注释吞符号 8 处)**: 历史遗留的 `// 注释` 后跟代码 (if/return/this/}) 在同一行被吞 —
   UnitType L3496/L3515/L4823/L5108、CustomUnitType L3574/L4779-4790/L4817、BuildSlot L122/L149。
   系统性扫描: 正则 `//.*\\S\\s{4,}(if|return|this\\.|...)` 全树排查。
3. **F29 合成访问器模式**: 02b 编译器生成的 `static int a/b/c/d(容器类)` (modCount 桥) + `$1` 合成构造 —
   utility/UnitRegistry (02b u) 与 UnitRegistryIterator (02b v) 修复。
4. **F30 javac 抽象方法逐层暴露**: 类未实现多个抽象方法时 javac 每类只报**首个**, 修一个爆一个 —
   $N 家族 getLabel/getDescription/getResourceCost 必须一次性全部改名。
5. **F24 重演**: ResourceComponent 与 CustomActionBase 双副本 → 02b custom/d/b.java L17
   `private static final f m = (new f()).a()` == CustomActionBase L19 m 铁证 → ResourceComponent 误建。

## 新工具
- tools/fixers/fix_commands_batch3.py / batch4.py / batch5.py / batch6.py (v19.133f10, 02b $N/ResourceComponent 锚点)

## 残余清单 (下战役行动依据)
- 2,039 全量: InGameActivity 27 / custom/resources/c 27 / SendWorker 26 / FireDecoration 25 /
  NodeQueue 25 / DualStorage 25 / FactoryBuilding 24 / custom/actions/g 23 / batch/b 21 /
  ScriptEngine 21 / BuildAction 21 / SteamManager 21 / MeleeBugUnit 20 / anim/f 20 / ModsUI 20
- 下轮建议: resources/c + actions/g + anim/f (custom 域) 或 InGameActivity + SendWorker (app/network 域)
"""
wr(session, body)
print('session doc done')

# ---------- D5: PLAN.md 追加 ----------
plan = ROOT / 'PLAN.md'
if plan.exists():
    t = rd(plan)
    t += '\n- v19.133f10 (2026-08-27): units/commands 家族清零 ($N extends 误映射/ResourceComponent 裁决/02b u/v 误建副本) | 2,211 → 2,039 (-95.07%)\n'
    wr(plan, t)
    print('PLAN.md done')
else:
    print('PLAN.md NOT FOUND')

# ---------- D2: 旧会话标注 ----------
old_s = ROOT / 'docs/deobfuscation/sessions/PHASE-A-v19.133f9-slots-commands家族清零.md'
if old_s.exists():
    t = rd(old_s)
    if '已被 v19.133f10 超越' not in t:
        t = '> ⚠️ **已被 v19.133f10 超越** (2026-08-27, units/commands 家族清零 → 2,039)\n\n' + t
        wr(old_s, t)
        print('old session annotated')
else:
    print('old session NOT FOUND')

print('ALL DONE')
