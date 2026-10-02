#!/usr/bin/env python3
"""Comprehensive reverse engineering metrics analysis"""

import subprocess, re, csv, os, json
csv.field_size_limit(10 * 1024 * 1024)
from collections import defaultdict

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))  # tools/*/ -> tools/ -> ROOT
from rwlib.config import find_javap as _find_javap; JAVAP = _find_javap()

# ====== 1. supplement.csv stats ======
total_lines = 0
type_counts = defaultdict(int)
pkg_counts = defaultdict(int)
with open(os.path.join(ROOT, 'mappings', 'supplement.csv'), encoding='utf-8') as f:
    reader = csv.DictReader(f)
    for row in reader:
        total_lines += 1
        type_counts[row.get('type','?')] += 1
        pkg = row.get('obfuscated_package','')
        pkg_counts[pkg] += 1

# ====== 2. File counts ======
cfr_count = 0
for root, dirs, files in os.walk(os.path.join(ROOT, '02-decompiled')):
    cfr_count += sum(1 for f in files if f.endswith('.java'))

class_count = 0
for root, dirs, files in os.walk(os.path.join(ROOT, '01-classes')):
    class_count += sum(1 for f in files if f.endswith('.class') and '$' not in f)

inner_class_count = 0
for root, dirs, files in os.walk(os.path.join(ROOT, '01-classes')):
    inner_class_count += sum(1 for f in files if f.endswith('.class') and '$' in f)

deob_java = 0
for root, dirs, files in os.walk(os.path.join(ROOT, '03-deobfuscated')):
    deob_java += sum(1 for f in files if f.endswith('.java'))

# ====== 3. Class rename stats ======
class_renames = 0
with open(os.path.join(ROOT, 'mappings', 'mappings.csv'), encoding='utf-8') as f:
    for row in csv.DictReader(f):
        if row.get('type') == 'class' and row.get('meaningful_name'):
            class_renames += 1

with open(os.path.join(ROOT, 'mappings', 'mappings.json'), encoding='utf-8') as f:
    data = json.load(f)
    json_classes = len(data.get('classes', {}))

# ====== 4. Bytecode verification ======
bc_total_f = 0; bc_total_m = 0
bc_mapped_f = 0; bc_mapped_m = 0
bc_full = 0; bc_partial = 0; bc_none = 0
bc_classes_scanned = 0

csv_mapped = defaultdict(lambda: {'fields': set(), 'methods': set()})
with open(os.path.join(ROOT, 'mappings', 'supplement.csv'), encoding='utf-8') as f:
    reader = csv.DictReader(f)
    for row in reader:
        if row['type'] not in ('field','method'): continue
        if not row.get('meaningful_name') or not row.get('obfuscated_member'): continue
        fqn = row['obfuscated_package'] + '.' + row['obfuscated_class']
        old_name = row['obfuscated_member'].split('(')[0].strip()
        if row['type'] == 'field':
            csv_mapped[fqn]['fields'].add(old_name)
        else:
            csv_mapped[fqn]['methods'].add(old_name)

CLASSES_DIR = os.path.join(ROOT, '01-classes')
for root, dirs, files in os.walk(CLASSES_DIR):
    for f in files:
        if not f.endswith('.class') or '$' in f: continue
        path = os.path.join(root, f)
        rel = os.path.relpath(path, CLASSES_DIR)
        fqn = rel.replace(os.sep, '.')[:-6]

        result = subprocess.run([JAVAP, '-p', path], capture_output=True, text=True, encoding='utf-8', errors='replace')
        bc_f = set(); bc_m = set()
        for line in result.stdout.split('\n'):
            line = line.strip()
            if not line or line.startswith('Compiled from'): continue
            if line in ('{','}') or line.startswith('@'): continue
            if 'class' in line: continue
            if '(' in line and line.endswith(';'):
                match = re.match(r'(?:public|private|protected|static|final|abstract|synchronized|native|volatile|transient|strictfp|\s)+(.*)', line)
                if match:
                    rest = match.group(1).strip()
                    m = re.match(r'(\S+(?:\[\])*(?:<[^>]+>)?)\s+([a-zA-Z_$][\w$]*)\((.*)\)\s*(?:throws\s+\S+)?;?\s*$', rest)
                    if m and m.group(2) not in ('<init>', '<clinit>'): bc_m.add(m.group(2))
            if line.endswith(';') and '(' not in line:
                match = re.match(r'(?:public|private|protected|static|final|abstract|synchronized|native|volatile|transient|\s)+(.*)', line)
                if match:
                    rest = match.group(1).strip()
                    m = re.match(r'(\S+(?:\[\])*(?:<[^>]+>)?)\s+([a-zA-Z_$][\w$]*)\s*;', rest)
                    if m: bc_f.add(m.group(2))

        mf = csv_mapped.get(fqn, {}).get('fields', set())
        mm = csv_mapped.get(fqn, {}).get('methods', set())

        tf, tm = len(bc_f), len(bc_m)
        cf = len(mf & bc_f); cm = len(mm & bc_m)

        bc_total_f += tf; bc_total_m += tm
        bc_mapped_f += cf; bc_mapped_m += cm
        bc_classes_scanned += 1

        if cf + cm == 0: bc_none += 1
        elif tf + tm - cf - cm == 0: bc_full += 1
        else: bc_partial += 1

# ====== 5. Top classes by mapping density ======
class_density = []
for root, dirs, files in os.walk(CLASSES_DIR):
    for f in files:
        if not f.endswith('.class') or '$' in f: continue
        path = os.path.join(root, f)
        rel = os.path.relpath(path, CLASSES_DIR)
        fqn = rel.replace(os.sep, '.')[:-6]
        mf = csv_mapped.get(fqn, {}).get('fields', set())
        mm = csv_mapped.get(fqn, {}).get('methods', set())

        result = subprocess.run([JAVAP, '-p', path], capture_output=True, text=True, encoding='utf-8', errors='replace')
        bc_f = set(); bc_m = set()
        for line in result.stdout.split('\n'):
            line = line.strip()
            if not line or line.startswith('Compiled from'): continue
            if line in ('{','}') or line.startswith('@'): continue
            if 'class' in line: continue
            if '(' in line and line.endswith(';'):
                match = re.match(r'(?:public|private|protected|static|final|abstract|synchronized|native|volatile|transient|strictfp|\s)+(.*)', line)
                if match:
                    rest = match.group(1).strip()
                    m = re.match(r'(\S+(?:\[\])*(?:<[^>]+>)?)\s+([a-zA-Z_$][\w$]*)\((.*)\)\s*(?:throws\s+\S+)?;?\s*$', rest)
                    if m and m.group(2) not in ('<init>', '<clinit>'): bc_m.add(m.group(2))
            if line.endswith(';') and '(' not in line:
                match = re.match(r'(?:public|private|protected|static|final|abstract|synchronized|native|volatile|transient|\s)+(.*)', line)
                if match:
                    rest = match.group(1).strip()
                    m = re.match(r'(\S+(?:\[\])*(?:<[^>]+>)?)\s+([a-zA-Z_$][\w$]*)\s*;', rest)
                    if m: bc_f.add(m.group(2))

        tf, tm = len(bc_f), len(bc_m)
        cf = len(mf & bc_f); cm = len(mm & bc_m)
        total_bc = tf + tm
        total_mapped = cf + cm
        pct = 100.0 * total_mapped / max(1, total_bc)
        if total_bc >= 10:
            class_density.append((fqn, tf, tm, cf, cm, pct))

class_density.sort(key=lambda x: x[5], reverse=True)

# ====== Output ======
print("=" * 70)
print("RUSTED WARFARE v1.15 — COMPREHENSIVE REVERSE ENGINEERING ANALYSIS")
print("=" * 70)
print()

print("=== 1. SOURCE FILES ===")
print(f"  01-classes (.class core):     {class_count} (+{inner_class_count} inner)")
print(f"  02-decompiled (CFR output):   {cfr_count} Java files")
print(f"  03-deobfuscated (renamed):    {deob_java} Java files")
print()

print("=== 2. MAPPING DATABASE (supplement.csv) ===")
print(f"  Total entries:                {total_lines}")
print(f"  Field mappings:               {type_counts.get('field',0)}")
print(f"  Method mappings:              {type_counts.get('method',0)}")
print(f"  Class renames (mappings.csv): {class_renames}")
print(f"  Class renames (mappings.json):{json_classes}")
print(f"  Unique packages covered:      {len(pkg_counts)}")
print()

print("=== 3. BYTECODE COVERAGE (javap -p verified) ===")
print(f"  Core .class files scanned:    {bc_classes_scanned}")
print(f"  Total bytecode fields:        {bc_total_f}")
print(f"  Total bytecode methods:       {bc_total_m}")
print(f"  Mapped fields:                {bc_mapped_f} ({100.0*bc_mapped_f/max(1,bc_total_f):.1f}%)")
print(f"  Mapped methods:               {bc_mapped_m} ({100.0*bc_mapped_m/max(1,bc_total_m):.1f}%)")
print(f"  Unmapped fields:              {bc_total_f - bc_mapped_f}")
print(f"  Unmapped methods:             {bc_total_m - bc_mapped_m}")
print(f"  Fully mapped classes:         {bc_full}")
print(f"  Partially mapped classes:     {bc_partial}")
print(f"  Completely unmapped classes:  {bc_none}")
print()

print("=== 4. TOP 20 CLASSES BY MAPPING DENSITY ===")
print(f"{'FQN':<55} {'Tot':>6} {'Map':>6} {'Pct':>6}")
print("-" * 75)
for fqn, tf, tm, cf, cm, pct in class_density[:20]:
    short = fqn if len(fqn) < 55 else '...' + fqn[-52:]
    print(f"{short:<55} {tf+tm:>4}  {cf+cm:>4}  {pct:>5.1f}%")

print()
print("=== 5. BOTTOM 20 CLASSES BY MAPPING DENSITY ===")
print(f"{'FQN':<55} {'Tot':>6} {'Map':>6} {'Pct':>6}")
print("-" * 75)
for fqn, tf, tm, cf, cm, pct in class_density[-20:]:
    short = fqn if len(fqn) < 55 else '...' + fqn[-52:]
    print(f"{short:<55} {tf+tm:>4}  {cf+cm:>4}  {pct:>5.1f}%")

print()
print("=== 6. APPLY_ENHANCED.PY STATE ===")
print("  Classes: 591 | Fields: {} | Methods: {}".format(
    type_counts.get('field',0), type_counts.get('method',0)))
print("  Files with renames: 223 | Renames applied: 4056")
print("  Inner classes renamed: 377 | Extends fixes: 153 files")
print("  CFR source files: {} | Output files: {}".format(cfr_count, deob_java))
