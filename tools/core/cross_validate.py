#!/usr/bin/env python3
"""
Phase 2 Task 2.1 — Bytecode Cross-Validation Script v3
- Auto-detects javap (JAVA_HOME → project JDK → PATH)
- Outputs mappings/descriptors.json (full descriptor database)
- Outputs mappings/unmapped-bytecode.csv (Phase 3 TODO list)
- Verifies field/method existence against 01-classes/
"""
import csv, json, os, re, subprocess, sys
csv.field_size_limit(10 * 1024 * 1024)
from pathlib import Path
from collections import defaultdict

ROOT = Path(__file__).resolve().parent.parent.parent  # tools/core/ → ROOT
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import find_javap, CLASSES_DIR, SUPPLEMENT_CSV as SUPP_CSV
from rwlib.config import GENERATED_DIR
DESC_JSON = GENERATED_DIR / "descriptors.json"
UNMAPPED_CSV = GENERATED_DIR / "unmapped-bytecode.csv"

def run_javap(classfile):
    """Run javap -p on a .class file, return {'fields':{name:type}, 'methods':{sig:ret}}."""
    javap = find_javap()
    if not javap:
        return None
    try:
        r = subprocess.run([javap, "-p", classfile], capture_output=True, text=True, timeout=15)
        if r.returncode != 0:
            return None
    except:
        return None

    fields = {}
    methods = {}
    for line in r.stdout.split('\n'):
        line = line.strip()
        # Field: "public int fieldName;"
        fm = re.match(r'^\s*(?:\w+\s+)+(\w[\w<>[\].]*)\s+(\w+)\s*;', line)
        if fm:
            fields[fm.group(2)] = fm.group(1)
            continue
        # Method: "public void methodName(params);" or "public static ReturnType methodName(params);"
        mm = re.match(r'^\s*(?:\w+\s+)*(\w[\w<>[\].]*)\s+(\w+)\s*\(([^)]*)\)', line)
        if mm:
            ret = mm.group(1)
            name = mm.group(2)
            params = mm.group(3).strip()
            methods[(name, params)] = ret
    return {'fields': fields, 'methods': methods}

def build_reverse_class_map():
    """meaningful_name -> obfuscated_FQN"""
    rev = {}
    for csv_path in [ROOT / "mappings" / "mappings.csv"]:
        if csv_path.exists():
            with open(csv_path, encoding='utf-8') as f:
                for r in csv.DictReader(f):
                    if r['type'] == 'class' and r.get('meaningful_name'):
                        rev[r['meaningful_name']] = f"{r['obfuscated_package']}.{r['obfuscated_class']}"
    mp = ROOT / "mappings" / "mappings.json"
    if mp.exists():
        with open(mp, encoding='utf-8') as f:
            for obf, deobf in json.load(f).get("classes", {}).items():
                rev[deobf] = obf
    disc = ROOT / "mappings" / "class-discoveries.csv"
    if disc.exists():
        with open(disc, encoding='utf-8') as f:
            for r in csv.DictReader(f):
                if r.get('type') == 'class' and r.get('meaningful_name'):
                    rev[r['meaningful_name']] = f"{r['obfuscated_package']}.{r['obfuscated_class']}"
    return rev

def main():
    print("=" * 60)
    print("Bytecode Cross-Validation v3")
    print("=" * 60)

    javap = find_javap()
    print(f"\n  javap: {javap or 'NOT FOUND'}")

    # 1. Load mappings
    print("\n[1] Loading mappings...")
    mappings = defaultdict(lambda: {'fields': [], 'methods': []})
    with open(SUPP_CSV, encoding='utf-8') as f:
        for r in csv.DictReader(f):
            pkg = r.get('obfuscated_package', '')
            cls = r.get('obfuscated_class', '')
            mem_raw = r.get('obfuscated_member') or ''
            mem = mem_raw.strip().split('(')[0]
            new_name = (r.get('meaningful_name') or '').strip()
            key = f"{pkg}.{cls}"
            if r['type'] == 'field':
                mappings[key]['fields'].append((mem, new_name))
            elif r['type'] == 'method':
                sig = (r.get('obfuscated_member') or '').strip()
                mappings[key]['methods'].append((sig, new_name))

    print(f"  {len(mappings)} classes with mappings")

    # 2. Build class index from 01-classes
    print("\n[2] Indexing .class files...")
    class_files = {}
    for cf in CLASSES_DIR.rglob("*.class"):
        rel = str(cf.relative_to(CLASSES_DIR)).replace(os.sep, '.').replace('.class', '')
        class_files[rel] = str(cf)
    print(f"  {len(class_files)} .class files indexed")

    # 3. Cross-validate
    print("\n[3] Cross-validating...")
    rev_map = build_reverse_class_map()
    descriptors = {}
    unmapped = defaultdict(lambda: {'fields': [], 'methods': []})
    phantoms = []
    verified_fields = 0
    verified_methods = 0
    total_fields = 0
    total_methods = 0
    fallback_matches = 0  # 降级匹配计数 (潜在幻影)

    for fqn, data in mappings.items():
        # 查找 .class 文件: 先精确匹配, 再包路径匹配 (防止幻影归因)
        cf_path = class_files.get(fqn)
        if not cf_path:
            # 降级匹配: 要求最后 2 段相同 (父包 + 类名), 防止跨包误匹配
            # 之前仅按简单名 endswith 导致 gameFramework.f.y 匹配到 game.units.y
            parts = fqn.rsplit('.', 2)  # 分成 [前缀..., 父包, 类名]
            suffix2 = '.'.join(parts[-2:]) if len(parts) >= 2 else parts[-1]
            suffix1 = parts[-1]
            best_match = None
            for obf_fqn, path in class_files.items():
                if obf_fqn == fqn:
                    best_match = path
                    break
                if obf_fqn.endswith('.' + suffix2):
                    best_match = path  # 2段匹配: 优先
                elif best_match is None and obf_fqn.endswith('.' + suffix1):
                    best_match = path  # 1段匹配: 仅当无2段匹配时使用
            if best_match and best_match != class_files.get(fqn):
                fallback_matches += 1  # 非精确匹配, 可能产生幻影
            cf_path = best_match
        if not cf_path:
            continue

        bytecode = run_javap(cf_path)
        if not bytecode:
            continue

        bc_fields = bytecode['fields']
        bc_methods = bytecode['methods']

        # Build descriptor entry for this class
        desc_entry = {'fields': {}, 'methods': {}}

        # Verify fields
        for obf_field, new_name in data['fields']:
            total_fields += 1
            if obf_field in bc_fields:
                verified_fields += 1
                desc_entry['fields'][obf_field] = {
                    'type': bc_fields[obf_field],
                    'name': new_name,
                    'verified': True
                }
            else:
                phantoms.append((fqn, 'field', obf_field, new_name))

        # Verify methods
        for sig, new_name in data['methods']:
            total_methods += 1
            # Parse signature: "name(params)" or "name()" or "name"
            if '(' in sig and ')' in sig:
                method_name = sig[:sig.index('(')]
                params = sig[sig.index('(')+1:sig.index(')')]
            else:
                method_name = sig
                params = ''

            key = (method_name, params)
            if key in bc_methods:
                verified_methods += 1
                desc_entry['methods'][sig] = {
                    'return': bc_methods[key],
                    'name': new_name,
                    'verified': True
                }
            else:
                phantoms.append((fqn, 'method', sig, new_name))

        # Collect unmapped bytecode members
        mapped_fields = {f[0] for f in data['fields']}
        mapped_sigs = set()
        for sig, _ in data['methods']:
            if '(' in sig and ')' in sig:
                mn = sig[:sig.index('(')]
                ps = sig[sig.index('(')+1:sig.index(')')]
            else:
                mn, ps = sig, ''
            mapped_sigs.add((mn, ps))

        for name, typ in bc_fields.items():
            if name not in mapped_fields:
                unmapped[fqn]['fields'].append((name, typ))

        for (name, params), ret in bc_methods.items():
            if (name, params) not in mapped_sigs:
                unmapped[fqn]['methods'].append((name, params, ret))

        if desc_entry['fields'] or desc_entry['methods']:
            descriptors[fqn] = desc_entry

    # 4. Report
    print(f"\n[4] Results:")
    print(f"  Fields: {verified_fields}/{total_fields} verified ({100*verified_fields/max(1,total_fields):.1f}%)")
    print(f"  Methods: {verified_methods}/{total_methods} verified ({100*verified_methods/max(1,total_methods):.1f}%)")
    print(f"  Phantoms: {len(phantoms)}")
    print(f"  Fallback matches (可能幻影): {fallback_matches}")
    print(f"  Unmapped bytecode members: {sum(len(v['fields'])+len(v['methods']) for v in unmapped.values())}")

    # 5. Write descriptors.json
    with open(DESC_JSON, 'w', encoding='utf-8') as f:
        json.dump(descriptors, f, indent=2, ensure_ascii=False)
    print(f"\n  Descriptors written to: {DESC_JSON}")

    # 6. Write unmapped-bytecode.csv
    with open(UNMAPPED_CSV, 'w', newline='', encoding='utf-8') as f:
        w = csv.writer(f)
        w.writerow(['fqn', 'type', 'name', 'params', 'return_type'])
        for fqn, data in sorted(unmapped.items()):
            for name, typ in sorted(data['fields']):
                w.writerow([fqn, 'field', name, '', typ])
            for name, params, ret in sorted(data['methods']):
                w.writerow([fqn, 'method', name, params, ret])
    print(f"  Unmapped bytecode written to: {UNMAPPED_CSV}")


if __name__ == '__main__':
    main()
