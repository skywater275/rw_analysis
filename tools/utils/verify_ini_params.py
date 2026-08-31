#!/usr/bin/env python3
"""从游戏 INI 文件提取参数名, 与 supplement.csv 交叉验证"""

import os, re, glob, sys
from collections import defaultdict, Counter

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
sys.path.insert(0, ROOT)

GAME_DIR = os.path.join(ROOT, "RustedWarfare")

# 1. Parse all INI files
all_params = Counter()
section_params = defaultdict(Counter)
unit_params = defaultdict(set)

for ini_path in glob.glob(f"{GAME_DIR}/assets/units/**/*.ini", recursive=True):
    unit_name = os.path.basename(os.path.dirname(ini_path))
    try:
        with open(ini_path, "r", encoding="utf-8") as f:
            content = f.read()
    except:
        continue

    section = "core"
    for line in content.split("\n"):
        line = line.strip()
        if not line or line.startswith("#") or line.startswith("//"):
            continue
        if line.startswith("["):
            section = line[1:-1].strip()
            continue
        m = re.match(r"^(\w+)\s*[:=]\s*(.+)", line)
        if m:
            key = m.group(1)
            all_params[key] += 1
            section_params[section][key] += 1
            unit_params[unit_name].add(key)

print(f"INI 参数总数: {len(all_params)}")
print(f"INI 区块(section)数: {len(section_params)}")
print(f"单位文件数: {len(unit_params)}")

# 2. Load supplement.csv
from rwlib.mappings import load_supplement
h, rows = load_supplement()

# Get meaningful_names for custom unit fields
custom_names = set()
for r in rows:
    if r.get("type") == "field" and "custom" in r.get("obfuscated_package", ""):
        name = r.get("meaningful_name", "")
        if name:
            custom_names.add(name)

# 3. Cross-reference
matched = all_params.keys() & custom_names
unmatched_params = all_params.keys() - custom_names

print(f"\nINI参数已映射到supplement: {len(matched)}")
print(f"INI参数未映射: {len(unmatched_params)}")

# Top 40 unmapped (candidates for new field mappings)
print("\n=== 未映射的 INI 参数 (TOP 40) ===")
for p in sorted(unmatched_params, key=lambda x: -all_params[x])[:40]:
    print(f"  {all_params[p]:3d}  {p}")

# 4. Check sections with most unmapped params
print("\n=== 未映射参数最多的区块 ===")
section_unmapped = {}
for sec, params in section_params.items():
    unmapped = {p for p in params if p not in custom_names}
    if unmapped:
        section_unmapped[sec] = len(unmapped)

for sec in sorted(section_unmapped, key=lambda s: -section_unmapped[s])[:15]:
    count = section_unmapped[sec]
    sample = sorted({p for p in section_params[sec] if p not in custom_names})[:8]
    print(f"  [{sec}]: {count} unmapped — {', '.join(sample)}")
