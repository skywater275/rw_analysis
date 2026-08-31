#!/usr/bin/env python3
"""update_doc_v19.107_stage2 — 追加脚本化批量修复阶段到会话记录 (v19.107 阶段2)"""
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))

STAGE2 = """## 脚本化批量修复阶段 (用户指令: 大类脚本化, 小属性跳过, 管线链扩散)

编译 22,135 → **21,870** (阶段 -265) | 4 commits

### GameUtils 方法名广播两轮管线 (fix_gameutils_names.py, M1-M7)

**发现**: 02 f.java 与 03 GameUtils.java 的 124 个静态方法**严格保序** —
mapper 用 formatDuration/md5Hex/randomInt/getString* 7 个名字循环广播了 63+ 方法
(部分正确语义名 sinFast/cosFast/parseInt/smoothstep 等保留)。

- 轮1 (--rename): 91 个声明按序改回 02 名 (a/b/c/d/e/f/g...); 映射持久化
  mappings/generated/gameutils-rename-map.json 供轮2
- 轮2 (--fix-callsites): compile-errors.csv symbol 列 (名+类型串) 驱动 296 处调用点
- 补充: randomInt→c 172 处 / getString*→e/f 4 处 / 包私有 2 方法 / 内部调用 6 处
- 坑: md5Hex 含数字致正则 [a-zA-Z]+ 失败 → [a-zA-Z][a-zA-Z0-9]*
- 坑: 轮2 需轮1 前映射 → JSON 持久化

### ui 幻影包清除

02 无任何 `gameFramework.ui.` 引用 (0 处) — 03 的 492 处全为幻影。
`ui.小写(` → `GameUtils.X(` 113 处 (大写类名 ui.ActionCooldown 等真实 UI 类保留)。

### ai 幻影包清除

- ai.n→TransporterGroup 等 13 类 FQN (class-discoveries 485-494 行映射)
- ai.b→CombatSubAction (02 game.a.b enum{a,b})
- 坑: MAP 占位符 '?' 造成 ai.?. 语法错误 (层级坍缩至 15 错误假象)

### TileEntry 重建 (22,328 → 21,903, -425)

**根因**: 03 TileEntry.java 曾容纳 02 b/b.java (MapEngine) 的完整副本;
真实 TileEntry 是 02 b/k.java (19 行)。

- TileEntry.java 重建为 k 类 (7 字段, animationDuration 类型 TMXMapLoader→Texture)
- 内部类双恢复: TileEntry$1/2 删除 + ActionFilter$1/2→MapEngine$1/2 (02 b$1/b$2)
- 引用面修正: MapRenderer/MapLayer/MapLayerDef 的 TileEntry→MapEngine
  (02 锚点: b/b.java 1056 b b2 = l2.bL / g.java 82 a(b,e) / a.java 56 ctor)
- TileEntry.al → MapEngine.al (b/b 静态字段)

### NetEngine 修复

- j.ad. 静态自调用残留 33 处 → NetEngine. (class-discoveries 647)
- InputNetStream 恢复丢失方法 readString (02 k.java l()=readUTF; 插入位置曾误入 k() 内)
- DialogHelper.o→n 16 处 (02 n.java 109 方法体 l2.bX.O() 锚定)
- strategies/SAFFileManager$1 空壳副本删除 (duplicate class)

## 管线链方法论

1. 大族识别: compile-errors.csv 符号分布 TOP-N
2. 02/03 保序验证: 方法序列 zip 对比 (类型名可不同, 参数个数必须一致)
3. 批量修复: 声明改名 → javac 报错 → 调用点按 symbol 列驱动
4. 每轮 javac_gate 全量重测 (层级坍缩假象: 15 错误可能是语法错误挡路)

## 待办（下一会话）"""


def main():
    P = ROOT / 'docs/deobfuscation/PHASE-A-v19.107-会话修复记录.md'
    t = P.read_text(encoding='utf-8')
    if '脚本化批量修复阶段' in t:
        print('已存在, 跳过')
        sys.exit(0)
    t = t.replace('## 待办（下一会话）', STAGE2, 1)
    P.write_text(t, encoding='utf-8')
    print('已追加阶段2记录')
    sys.exit(0)


if __name__ == '__main__':
    main()
