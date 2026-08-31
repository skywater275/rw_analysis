#!/usr/bin/env python3
"""
backfeed_03 — 映射反哺 03: 把 supplement 修正后的语义名应用到 03 源码 (v19.106)

对已 T0 仲裁修正的成员映射 (旧名→新名), 在 03 对应类文件中做词边界替换
(声明侧 + 类内调用), 并可选全树调用点同步。

Usage:
  python tools/utils/backfeed_03.py --dry-run    # 预览改动
  python tools/utils/backfeed_03.py --apply      # 应用改动
"""
import csv
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import DEOBFUSCATED_DIR

csv.field_size_limit(10 * 1024 * 1024)

# 反哺清单: (03文件相对路径, 旧名, 新名, 说明)
# 全部来自 v19.98-105 T0 仲裁修正 (supplement notes 含"修正/撤销"的 verified 行)
BACKFEED = [
    # PlayerState (game.n) — v19.104 agentG
    ("com/corrodinggames/rts/game/PlayerState.java", "getTotalPlayTime", "getCreditsTotal",
     "agentG: 166ms缓存aq/ar + o+p (1867行)"),
    ("com/corrodinggames/rts/game/PlayerState.java", "onPlayerDefeated", "markAllPlayersDirty",
     "agentG: n.i.S=n.h.S=true全体标记 (1539行)"),
    # UnitInstance (units.am) — v19.104 agentG
    ("com/corrodinggames/rts/game/units/UnitInstance.java", "getMinimapScaleX", "getMapOriginX",
     "agentG: bL.p被减除 (1966行)"),
    ("com/corrodinggames/rts/game/units/UnitInstance.java", "getMinimapScaleY", "getMapOriginY",
     "agentG: bL.q被减除"),
    ("com/corrodinggames/rts/game/units/UnitInstance.java", "isAutoAttack", "hasSpawnedDeathEffect",
     "agentG: y.java:4530 if(!bO()) bo()生成特效"),
    ("com/corrodinggames/rts/game/units/UnitInstance.java", "isCloaked", "isAirUnit",
     "agentG: custom/j eq<=-1.0f"),
]


def find_file(rel):
    p = DEOBFUSCATED_DIR / rel
    return p if p.exists() else None


def main():
    dry_run = "--apply" not in sys.argv
    mode = "预览" if dry_run else "应用"
    print(f"backfeed_03 — 反哺 03 ({mode}模式)")
    total = 0
    for rel, old, new, note in BACKFEED:
        p = find_file(rel)
        if p is None:
            print(f"  [跳过] 文件不存在: {rel}")
            continue
        text = p.read_text(encoding="utf-8")
        count = len(re.findall(r"\b" + re.escape(old) + r"\b", text))
        if count == 0:
            continue
        total += count
        print(f"  {rel.split('/')[-1]}: {old} → {new} ({count} 处) [{note}]")
        if not dry_run:
            new_text = re.sub(r"\b" + re.escape(old) + r"\b", new, text)
            p.write_text(new_text, encoding="utf-8")
    status = "未应用" if dry_run else "已应用"
    print(f"合计: {total} 处 ({status})")
    sys.exit(0)


if __name__ == "__main__":
    main()
