#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
extract_annotations.py — 游戏自带反编译机制提取器 (v19.113q)

机制: 游戏 mod 系统用运行时注解 @LogicBoolean$Parameter(key="语义名", ...) 标记方法/字段。
ProGuard 混淆成员名但注解+key 字符串保留 → javap -v 提取 (类, 成员, 语义名) 三元组。

用法: python tools/fixers/extract_annotations.py [--apply]
输出: supplement.csv 追加 method 条目 (mod-annotation-verified)
"""

import csv
import re
import subprocess
import sys
import zipfile
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent.parent
GAME_LIB = ROOT.parent / "game-lib.jar"
SUPP = ROOT / "mappings" / "supplement.csv"
JAVAP = "javap"


def find_annotated_classes():
    zf = zipfile.ZipFile(GAME_LIB)
    hits = []
    for name in zf.namelist():
        if not name.endswith(".class"):
            continue
        data = zf.read(name)
        if b"RuntimeVisibleAnnotations" in data and b"Parameter" in data and b"logicBooleans" in data:
            hits.append(name[:-6].replace("/", "."))
    zf.close()
    return hits


def extract_keys(cls):
    """javap -v 提取 (成员, key) 对"""
    r = subprocess.run([JAVAP, "-v", "-cp", str(GAME_LIB), cls],
                       capture_output=True, encoding="utf-8", errors="replace")
    out = r.stdout
    pairs = []
    # javap -v 结构: 成员名: 后跟 RuntimeVisibleAnnotations 段, 含 key="..."
    # 简化: 逐行扫描, 跟踪当前成员
    current = None
    in_ann = False
    for line in out.split("\n"):
        if re.match(r"^\s*(public|protected|private)", line):
            m = re.search(r"([\w$]+)\([^)]*\);", line)
            if m:
                current = m.group(1)
            else:
                m = re.search(r"\s([\w$]+);$", line)
                if m:
                    current = m.group(1)
            in_ann = False
        elif "RuntimeVisibleAnnotations" in line:
            in_ann = True
        elif in_ann and "key=" in line:
            m = re.search(r'key="?([^",]+)"?', line)
            if m and current:
                pairs.append((current, m.group(1)))
            in_ann = False
        elif in_ann and "LogicBoolean" not in line and "Parameter" not in line and line.strip() and not line.startswith(" "):
            in_ann = False
    return pairs


def main():
    classes = find_annotated_classes()
    print(f"含 Parameter 注解的类: {len(classes)}")
    all_pairs = {}
    for cls in classes:
        try:
            pairs = extract_keys(cls)
        except Exception:
            continue
        for member, key in pairs:
            all_pairs.setdefault((cls, member), key)
        if pairs:
            print(f"  {cls}: {len(pairs)} 成员")
    print(f"总计 (类,成员,key) 三元组: {len(all_pairs)}")
    if "--apply" not in sys.argv:
        for (cls, member), key in sorted(all_pairs.items()):
            print(f"  {cls}::{member} = {key}")
        print("\nDRY RUN — 使用 --apply 写入 supplement")
        return 0
    with open(SUPP, "a", encoding="utf-8", newline="") as f:
        w = csv.writer(f)
        for (cls, member), key in sorted(all_pairs.items()):
            pkg, cname = cls.rsplit(".", 1)
            w.writerow(["method", pkg, cname, member, key,
                        "v19.113q 游戏自带注解 @Parameter(key=...) 提取 (mod函数参数语义名)", "mod-annotation-verified"])
    print(f"supplement 追加 {len(all_pairs)} 条")
    return 0


if __name__ == "__main__":
    sys.exit(main())
