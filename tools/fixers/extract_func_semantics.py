#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
extract_func_semantics.py — 游戏自带"反编译"最强机制提取器 (v19.113q)

机制: 作者的 mod 逻辑函数类 (LogicBooleanGameFunctions$XXXBoolean) 保留语义类名/方法名
(ProGuard 未混淆), 其 read() 方法体通过混淆名调用游戏 API → 函数语义 = 成员语义说明书。

例: SpeedBoolean.read → y.z()/y.bi()/y.cc/y.cd/y.cf → 全与"速度"相关 → 语义推断。

用法: python tools/fixers/extract_func_semantics.py [--apply]
输出: (函数类, 目标类, 成员引用, 频率) 候选表 + 推断语义名 → supplement
"""

import re
import subprocess
import sys
import zipfile
from collections import defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent.parent
GAME_LIB = ROOT.parent / "game-lib.jar"
SUPP = ROOT / "mappings" / "supplement.csv"

# 目标类 FQN → 03 语义名 (从 class-discoveries)
TARGETS = {
    "com.corrodinggames.rts.game.units.y": "UnitType",
    "com.corrodinggames.rts.game.units.am": "UnitInstance",
    "com.corrodinggames.rts.game.n": "PlayerState",
    "com.corrodinggames.rts.game.units.custom.l": "ModUnitRegistry",
    "com.corrodinggames.rts.gameFramework.l": "GlobalState",
    "com.corrodinggames.rts.game.units.au": "WeaponAction",
    "com.corrodinggames.rts.game.units.ap": "UnitTurret",
    "com.corrodinggames.rts.game.b.b": "MapEngine",
}


def find_func_classes():
    zf = zipfile.ZipFile(GAME_LIB)
    hits = []
    for name in zf.namelist():
        if "LogicBooleanGameFunctions$" in name and name.endswith(".class"):
            hits.append(name[:-6].replace("/", "."))
    zf.close()
    return hits


def disasm(cls):
    r = subprocess.run(["javap", "-p", "-c", "-cp", str(GAME_LIB), cls],
                       capture_output=True, encoding="utf-8", errors="replace")
    return r.stdout


def main():
    classes = find_func_classes()
    print(f"mod 逻辑函数类: {len(classes)}")
    # (目标类短名 → {成员: 次数})
    refs = defaultdict(lambda: defaultdict(int))
    func_names = []
    for cls in classes:
        out = disasm(cls)
        func_names.append(cls.split("$")[-1].replace("Boolean", ""))
        for m in re.finditer(r"// (?:Method|Field) ([\w/$.]+)\.([\w$]+)[:(]", out):
            fqn = m.group(1).replace("/", ".")
            member = m.group(2)
            for t, name in TARGETS.items():
                if fqn == t:
                    refs[name][member] += 1
    print("函数类名 (语义):", ", ".join(sorted(set(func_names))))
    print()
    print("=== 目标类成员引用频率 (候选语义推断) ===")
    for t in TARGETS.values():
        if refs[t]:
            print(f"[{t}]")
            for member, n in sorted(refs[t].items(), key=lambda x: -x[1]):
                print(f"  {member} x{n}")
    return 0


if __name__ == "__main__":
    sys.exit(main())
