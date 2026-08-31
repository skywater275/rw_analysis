#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
fix_modkeys.py — mod 配置键批量字段改名 (v19.113p)

铁证来源: 02b custom/ag.java (ModLoader) 的 var17.X = var11.a(var12, "键名", ...) 模式 —
mod 配置键名 = 作者定义的字段语义名 (T0 级证据, 游戏 mod API 文档键)。

用法:
  python tools/fixers/fix_modkeys.py [--apply]

步骤:
  1. 提取 02b ag.java 全部 (字段, 键名) 对 (var17.X + var17.cL.X 两族)
  2. 03 ModUnitRegistry.java / WeaponConfig.java 字段声明改名 + 类内 this.X 同步
  3. supplement.csv 批量追加 (field custom.l / custom.as)
  4. gate 验证 (调用方同步)
"""

import csv
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent.parent
AG = ROOT / "02b-decompiled" / "com" / "corrodinggames" / "rts" / "game" / "units" / "custom" / "ag.java"
MUR = ROOT / "03-deobfuscated" / "com" / "corrodinggames" / "rts" / "game" / "units" / "custom" / "ModUnitRegistry.java"
WC = ROOT / "03-deobfuscated" / "com" / "corrodinggames" / "rts" / "game" / "units" / "custom" / "WeaponConfig.java"
SUPP = ROOT / "mappings" / "supplement.csv"

JAVA_KEYWORDS = {"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
                 "class", "const", "continue", "default", "do", "double", "else", "enum",
                 "extends", "final", "finally", "float", "for", "goto", "if", "implements",
                 "import", "instanceof", "int", "interface", "long", "native", "new",
                 "package", "private", "protected", "public", "return", "short", "static",
                 "strictfp", "super", "switch", "synchronized", "this", "throw", "throws",
                 "transient", "try", "void", "volatile", "while", "true", "false", "null"}


def extract_pairs():
    src = AG.read_text(encoding="utf-8", errors="replace")
    mur = {}   # custom.l (ModUnitRegistry) 字段
    wc = {}    # custom.as (WeaponConfig) 字段
    for m in re.finditer(r'var17\.(\w+)\.(\w+)\s*=\s*var11\.[a-z]\((var12|var17),\s*"([^"]+)"', src):
        if m.group(1) == "cL":
            wc.setdefault(m.group(2), m.group(4))
    for m in re.finditer(r'var17\.(\w+)\s*=\s*var11\.[a-z]\((var12|var13|var14),\s*"([^"]+)"', src):
        mur.setdefault(m.group(1), m.group(3))
    # 键名合法性检查
    def valid(k):
        return re.match(r"^[A-Za-z_]\w*$", k) and k not in JAVA_KEYWORDS
    mur = {f: k for f, k in mur.items() if valid(k)}
    wc = {f: k for f, k in wc.items() if valid(k)}
    return mur, wc


def rename_fields(fpath, pairs, tag):
    """声明行改名 + 类内 this.X 同步"""
    src = fpath.read_text(encoding="utf-8", errors="replace")
    lines = src.split("\n")
    renamed = []
    for i, l in enumerate(lines):
        # 声明: (修饰符)* T X; 或 T X = ...;
        m = re.match(r"^(\s*(?:public |protected |private |static |final |transient |volatile )*)([\w<>\[\],\.]+ )(\w+)(\s*[=;].*)$", l)
        if not m:
            continue
        fname = m.group(3)
        if fname in pairs and fname != pairs[fname]:
            lines[i] = f"{m.group(1)}{m.group(2)}{pairs[fname]}{m.group(4)}  // v19.113p mod键: {fname}={pairs[fname]}"
            renamed.append((fname, pairs[fname]))
    new_src = "\n".join(lines)
    # 类内 this.X → this.键名 (按声明序, 防连带替换)
    applied = {}
    for old, new in renamed:
        applied[old] = new
    # 短名→长名: 先替换长键名 (避免 a→b 链式误伤): 按 02b 名长度降序
    for old in sorted(applied, key=len, reverse=True):
        new = applied[old]
        new_src = re.sub(rf"\bthis\.{re.escape(old)}\b", f"this.{new}", new_src)
    return new_src, renamed


def main():
    apply = "--apply" in sys.argv
    mur, wc = extract_pairs()
    print(f"提取: ModUnitRegistry {len(mur)} 字段 / WeaponConfig {len(wc)} 字段")

    if not apply:
        print("DRY RUN — 使用 --apply 应用")
        for f, k in sorted(mur.items()):
            print(f"  ModUnitRegistry.{f} = {k}")
        for f, k in sorted(wc.items()):
            print(f"  WeaponConfig.{f} = {k}")
        return 0

    # 1. ModUnitRegistry 改名
    src1, r1 = rename_fields(MUR, mur, "custom.l")
    MUR.write_text(src1, encoding="utf-8")
    print(f"ModUnitRegistry.java: {len(r1)} 字段改名")
    # 2. WeaponConfig 改名
    src2, r2 = rename_fields(WC, wc, "custom.as")
    WC.write_text(src2, encoding="utf-8")
    print(f"WeaponConfig.java: {len(r2)} 字段改名")

    # 3. supplement 追加
    with open(SUPP, "a", encoding="utf-8", newline="") as f:
        w = csv.writer(f)
        for old, new in r1:
            w.writerow(["field", "game.units.custom", "l", old, new,
                        "v19.113p mod配置键铁证 (02b ag.java var11.a(var12, 键名))", "mod-key-verified"])
        for old, new in r2:
            w.writerow(["field", "game.units.custom", "as", old, new,
                        "v19.113p mod配置键铁证 (02b ag.java var17.cL)", "mod-key-verified"])
    print(f"supplement 追加 {len(r1) + len(r2)} 条")
    return 0


if __name__ == "__main__":
    sys.exit(main())
