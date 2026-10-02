#!/usr/bin/env python3
"""
Enhanced deobfuscation script v3.0 — uses supplement.csv for
comprehensive field + method renames within each class.

Usage: python apply_enhanced.py [--dry-run] [--stage class|imports|types|fields|methods|inner|extends|all]
Stages:
  class   — Phase 1: rename class files
  imports — Phase 2: update import statements
  types   — NEW v3.0: rename type positions (extends, casts, fields, params)
  fields  — Phase 3: field renames
  methods — Phase 3: method renames (signature-stripped, keyword-guarded)
  inner   — Phase 5: inner class renames
  extends — Phase 6: extends/implements fixes
  all     — All stages (default)
Output: ../03-deobfuscated/ (overwrites previous)
"""

import csv, json, os, re, shutil, sys
csv.field_size_limit(10 * 1024 * 1024)
from pathlib import Path
from collections import defaultdict

ROOT = Path(__file__).resolve().parent.parent.parent  # tools/core/ → ROOT
sys.path.insert(0, str(ROOT))
from rwlib.config import MAPPINGS_DIR, DEOBFUSCATED_DIR as DEOBFUSCATED, CLASS_DISCOVERIES, MAPPINGS_CSV, MAPPINGS_JSON
DECOMPILED = ROOT / "02-decompiled"

def load_enhanced_mappings():
    """Load from supplement.csv: {fqn: {fields: {old:new}, methods: {old:new}}}"""
    data = defaultdict(lambda: {"fields": {}, "methods": {}})

    for csv_name in ["supplement.csv"]:
        csv_path = MAPPINGS_DIR / csv_name
        if csv_path.exists():
            with open(csv_path, encoding='utf-8') as f:
                for row in csv.DictReader(f):
                    if row['type'] not in ('field', 'method'):
                        continue
                    if not row.get('meaningful_name') or not row.get('obfuscated_member'):
                        continue
                    pkg = row['obfuscated_package']
                    cls = row['obfuscated_class']
                    fqn = f"{pkg}.{cls}" if pkg and cls else (pkg or cls)
                    old_name = row['obfuscated_member'].strip()
                    new_name = row['meaningful_name'].strip()
                    # Strip method signature: "name(float,int)" -> "name"
                    if '(' in old_name:
                        old_name = old_name[:old_name.index('(')]
                    if '(' in new_name:
                        new_name = new_name[:new_name.index('(')]
                    if row['type'] == 'field':
                        data[fqn]["fields"][old_name] = new_name
                    else:
                        data[fqn]["methods"][old_name] = new_name

    # Also load main class mappings
    class_map = {}
    csv_path2 = MAPPINGS_DIR / "mappings.csv"
    if csv_path2.exists():
        with open(csv_path2, encoding='utf-8') as f:
            for row in csv.DictReader(f):
                if row['type'] == 'class' and row.get('meaningful_name'):
                    pkg = row['obfuscated_package']
                    cls = row['obfuscated_class']
                    full = f"{pkg}.{cls}" if pkg and cls else (pkg or cls)
                    class_map[full] = row['meaningful_name']
    json_path = MAPPINGS_DIR / "mappings.json"
    if json_path.exists():
        with open(json_path, encoding='utf-8') as f:
            jdata = json.load(f)
        for obf, mean in jdata.get("classes", {}).items():
            class_map[obf] = mean

    # Add AI class mappings from our research
    ai_extras = {
        "com.corrodinggames.rts.game.a.o": "AIStrategyNode",
        "com.corrodinggames.rts.game.a.h": "AIUnitGroupBase",
        "com.corrodinggames.rts.game.a.n": "TransporterGroup",
        "com.corrodinggames.rts.game.a.l": "RallyGroup",
        "com.corrodinggames.rts.game.a.m": "PlainZone",
        "com.corrodinggames.rts.game.a.d": "UnitBuildStrategy",
        "com.corrodinggames.rts.game.a.c": "BuildPreferenceCache",
        "com.corrodinggames.rts.game.a.f": "AIUnitActionUtils",
        "com.corrodinggames.rts.game.a.j": "BaseZoneType",
        "com.corrodinggames.rts.game.a.k": "BaseZoneStage",
        "com.corrodinggames.rts.gameFramework.c": "CommandController",
    }
    for k, v in ai_extras.items():
        if k not in class_map:
            class_map[k] = v

    # Add newly discovered class mappings
    disc_path = MAPPINGS_DIR / "class-discoveries.csv"
    if disc_path.exists():
        with open(disc_path, encoding='utf-8') as f:
            for row in csv.DictReader(f):
                if row['type'] == 'class' and row.get('meaningful_name'):
                    pkg = row['obfuscated_package']
                    cls = row['obfuscated_class']
                    full = f"{pkg}.{cls}" if pkg and cls else (pkg or cls)
                    if full not in class_map:
                        class_map[full] = row['meaningful_name']

    return dict(data), class_map

def build_path_index(base_dir):
    index = {}
    for java_file in base_dir.rglob("*.java"):
        rel = str(java_file.relative_to(base_dir).with_suffix('')).replace(os.sep, '.')
        index[rel] = java_file
    return index

def phase1_rename_classes(class_map, dry_run=False):
    src_index = build_path_index(DECOMPILED)
    fqn_rename = {}
    skipped = set()
    for old_fqn, mean_name in class_map.items():
        if old_fqn in src_index:
            pkg = old_fqn.rsplit('.', 1)[0]
            fqn_rename[old_fqn] = pkg + '.' + mean_name
            skipped.add(old_fqn)

    renamed = 0
    copied = 0
    for fqn, src_path in src_index.items():
        if fqn in skipped:
            new_fqn = fqn_rename[fqn]
            mean_name = new_fqn.rsplit('.', 1)[-1]
            pkg = new_fqn.rsplit('.', 1)[0]
            rel = Path(new_fqn.replace('.', os.sep) + '.java')
            dst = DEOBFUSCATED / rel
            old_simple = fqn.rsplit('.', 1)[-1]
            if not dry_run:
                content = src_path.read_text(encoding='utf-8', errors='replace')
                content = re.sub(r'^package\s+[\w.]+;', f'package {pkg};', content, count=1, flags=re.MULTILINE)
                content = re.sub(
                    rf'(\b(?:public\s+|private\s+|protected\s+|abstract\s+|final\s+|static\s+|strictfp\s+)*(?:class|interface|enum)\s+){re.escape(old_simple)}\b',
                    rf'\1{mean_name}', content, count=1)
                content = re.sub(
                    rf'(\b(?:public|private|protected)\s+){re.escape(old_simple)}\s*\(',
                    rf'\1{mean_name}(', content)
                dst.parent.mkdir(parents=True, exist_ok=True)
                dst.write_text(content, encoding='utf-8')
            renamed += 1
        else:
            rel = Path(fqn.replace('.', os.sep) + '.java')
            dst = DEOBFUSCATED / rel
            if not dry_run:
                dst.parent.mkdir(parents=True, exist_ok=True)
                shutil.copy2(src_path, dst)
            copied += 1
    print(f"  Classes renamed: {renamed}, copied: {copied}, total: {renamed + copied}")
    return fqn_rename

def phase2_update_imports(class_map, fqn_rename, dry_run=False):
    updated = 0
    for java_file in DEOBFUSCATED.rglob("*.java"):
        if not java_file.is_file(): continue
        content = java_file.read_text(encoding='utf-8', errors='replace')
        original = content
        for old_fqn, new_fqn in fqn_rename.items():
            old_simple = old_fqn.rsplit('.', 1)[-1]
            new_simple = new_fqn.rsplit('.', 1)[-1]
            if old_simple == new_simple: continue
            content = content.replace(f'import {old_fqn};', f'import {new_fqn};')
        if content != original:
            if not dry_run: java_file.write_text(content, encoding='utf-8')
            updated += 1
    print(f"  Import-updated files: {updated}")

def phase3_enhanced_rename(all_data, class_map, dry_run=False):
    """Apply field renames (safe) + method renames (conservative: only multi-char or this. prefix)."""
    files_updated = 0
    total_renames = 0

    for obf_fqn, mappings in all_data.items():
        java_file = _find_file(obf_fqn, class_map)
        if not java_file:
            continue

        content = java_file.read_text(encoding='utf-8', errors='replace')
        original = content

        # Collect class simple names from imports to avoid renaming class names as methods
        class_names = _collect_class_names(content)

        # Apply field renames
        for old_name, new_name in mappings["fields"].items():
            if old_name == new_name: continue
            content = _replace_field(content, old_name, new_name)

        # Apply method renames (conservative)
        for old_name, new_name in mappings["methods"].items():
            if old_name == new_name: continue
            # SKIP if old name matches a known class name (would break 'new ClassName()')
            if old_name in class_names:
                continue
            content = _replace_method(content, old_name, new_name)

        if content != original:
            if not dry_run: java_file.write_text(content, encoding='utf-8')
            files_updated += 1
            total_renames += len(mappings["fields"]) + len(mappings["methods"])

    print(f"  Files with field/method renames: {files_updated}")
    print(f"  Total renames applied: {total_renames}")

def _collect_class_names(content):
    """Collect all simple class names from import statements."""
    names = set()
    for m in re.finditer(r'import\s+[\w.]+\.(\w+);', content):
        names.add(m.group(1))
    # Also add java.lang classes
    names.update(['String', 'Integer', 'Float', 'Boolean', 'Long', 'Double', 'Object',
                  'Class', 'Thread', 'Exception', 'RuntimeException', 'System', 'Math'])
    return names

def _replace_field(content, old_name, new_name):
    """Replace field name: this.field + field declarations. Import-safe."""
    if not _is_safe_name(new_name):
        print(f"    WARNING: Skipping unsafe field rename '{old_name}' -> '{new_name}' (keyword/JRE type)")
        return content
    lines = content.split('\n')
    result = []
    for line in lines:
        if re.match(r'^\s*import\s+', line):
            result.append(line)
            continue
        modified = line
        modified = re.sub(rf'\bthis\.{re.escape(old_name)}\b', f'this.{new_name}', modified)
        modified = re.sub(rf'(\s)({re.escape(old_name)})(\s*[;=])', rf'\1{new_name}\3', modified)
        result.append(modified)
    return '\n'.join(result)

# Phase 1 Task 1.3 — Java keyword / JRE type name guard
_JAVA_RESERVED = {
    'abstract', 'assert', 'boolean', 'break', 'byte', 'case', 'catch', 'char',
    'class', 'const', 'continue', 'default', 'do', 'double', 'else', 'enum',
    'extends', 'final', 'finally', 'float', 'for', 'goto', 'if', 'implements',
    'import', 'instanceof', 'int', 'interface', 'long', 'native', 'new',
    'package', 'private', 'protected', 'public', 'return', 'short', 'static',
    'strictfp', 'super', 'switch', 'synchronized', 'this', 'throw', 'throws',
    'transient', 'try', 'void', 'volatile', 'while', 'true', 'false', 'null',
}

def _is_safe_name(name):
    """Reject Java keywords, primitives, and JRE types as identifiers."""
    return name and name not in _JAVA_RESERVED and not name[0].isdigit()

def _replace_method(content, old_name, new_name):
    """Replace method name: this.method( + super.method( + method declarations."""
    if not _is_safe_name(new_name):
        print(f"    WARNING: Skipping unsafe method rename '{old_name}' -> '{new_name}' (keyword/JRE type)")
        return content
    lines = content.split('\n')
    result = []
    for line in lines:
        if re.match(r'^\s*import\s+', line):
            result.append(line)
            continue
        modified = line
        # this.methodName( — 100% reliable
        modified = re.sub(rf'\bthis\.{re.escape(old_name)}\s*\(', f'this.{new_name}(', modified)
        # super.methodName( — in overrides/calls
        modified = re.sub(rf'\bsuper\.{re.escape(old_name)}\s*\(', f'super.{new_name}(', modified)
        # Method DECLARATION: "[modifiers] ReturnType methodName("
        # Matches: one or more modifier/type words, then old_name, then (
        # The pattern: known_type_word (more_type_words)* old_name(
        known_types = r'(?:public|private|protected|static|final|abstract|synchronized|native|strictfp|void|boolean|int|float|double|long|short|byte|char|String|Object|Class|Thread|ArrayList|HashMap|ConcurrentLinkedQueue|Socket|Timer|Handler|Context|Paint|Point|PointF|Rect|RectF|Throwable|Exception|Integer|Float|Boolean|Long|Short|Byte)'
        # Allow intermediate words (additional modifiers, generic types, etc.)
        modified = re.sub(
            rf'({known_types}(?:\s+[\w.<>[\],]+)*)\s+({re.escape(old_name)})\s*\(',
            rf'\1 {new_name}(',
            modified
        )
        result.append(modified)
    return '\n'.join(result)

def _find_file(obf_fqn, class_map):
    pkg = obf_fqn.rsplit('.', 1)[0] if '.' in obf_fqn else ''
    new_simple = class_map.get(obf_fqn, obf_fqn.rsplit('.', 1)[-1])
    new_fqn = pkg + '.' + new_simple if pkg else new_simple
    rel = Path(new_fqn.replace('.', os.sep) + '.java')
    f = DEOBFUSCATED / rel
    if f.exists(): return f
    for candidate in DEOBFUSCATED.rglob(f"{new_simple}.java"):
        try:
            first = candidate.read_text(encoding='utf-8', errors='replace')[:200]
            m = re.search(r'^package\s+([\w.]+);', first, re.MULTILINE)
            if m and m.group(1) == pkg: return candidate
        except: pass
    return None

def _replace_member(content, old_name, new_name, is_method=False):
    """Replace field or method name conservatively within a class file."""
    lines = content.split('\n')
    result = []
    for line in lines:
        if re.match(r'^\s*import\s+', line):
            result.append(line)
            continue

        modified = line

        if is_method:
            # Method declaration: "ReturnType name("
            modified = re.sub(
                rf'(\s){re.escape(old_name)}\s*\(',
                rf'\1{new_name}(',
                modified
            )
            # this.method(
            modified = re.sub(
                rf'\bthis\.{re.escape(old_name)}\s*\(',
                f'this.{new_name}(',
                modified
            )
            # super.method(  (in constructor calls)
            modified = re.sub(
                rf'\bsuper\.{re.escape(old_name)}\s*\(',
                f'super.{new_name}(',
                modified
            )
        else:
            # this.field
            modified = re.sub(rf'\bthis\.{re.escape(old_name)}\b', f'this.{new_name}', modified)
            # Field declaration: "Type name;" or "Type name ="
            modified = re.sub(rf'(\s)({re.escape(old_name)})(\s*[;=])', rf'\1{new_name}\3', modified)

        result.append(modified)
    return '\n'.join(result)

def phase4_additional_ai_classes(dry_run=False):
    """Apply extra AI class renames from our research."""
    ai_extras = {
        "com.corrodinggames.rts.game.a.o": "AIStrategyNode",
        "com.corrodinggames.rts.game.a.h": "AIUnitGroupBase",
        "com.corrodinggames.rts.game.a.n": "TransporterGroup",
        "com.corrodinggames.rts.game.a.l": "RallyGroup",
        "com.corrodinggames.rts.game.a.m": "PlainZone",
        "com.corrodinggames.rts.game.a.d": "UnitBuildStrategy",
        "com.corrodinggames.rts.game.a.c": "BuildPreferenceCache",
        "com.corrodinggames.rts.game.a.f": "AIUnitActionUtils",
        "com.corrodinggames.rts.game.a.j": "BaseZoneType",
        "com.corrodinggames.rts.game.a.k": "BaseZoneStage",
        "com.corrodinggames.rts.gameFramework.c": "CommandController",
    }
    renamed = 0
    for obf_fqn, mean_name in ai_extras.items():
        pkg = obf_fqn.rsplit('.', 1)[0]
        old_simple = obf_fqn.rsplit('.', 1)[-1]
        java_file = None
        for candidate in DEOBFUSCATED.rglob(f"{old_simple}.java"):
            try:
                first = candidate.read_text(encoding='utf-8', errors='replace')[:200]
                m = re.search(r'^package\s+([\w.]+);', first, re.MULTILINE)
                if m and m.group(1) == pkg:
                    java_file = candidate
                    break
            except: pass
        if not java_file: continue
        content = java_file.read_text(encoding='utf-8', errors='replace')
        content = re.sub(
            rf'(\b(?:public\s+|private\s+|protected\s+|abstract\s+|final\s+|static\s+|strictfp\s+)*(?:class|interface|enum)\s+){re.escape(old_simple)}\b',
            rf'\1{mean_name}', content, count=1)
        content = re.sub(rf'(\b(?:public|private|protected)\s+){re.escape(old_simple)}\s*\(', rf'\1{mean_name}(', content)
        new_path = java_file.parent / f"{mean_name}.java"
        if not dry_run:
            java_file.rename(new_path)
            new_path.write_text(content, encoding='utf-8')
        renamed += 1

    if renamed > 0 and not dry_run:
        updated = 0
        for other_file in DEOBFUSCATED.rglob("*.java"):
            oc = other_file.read_text(encoding='utf-8', errors='replace')
            orig = oc
            for obf_fqn, mean_name in ai_extras.items():
                pkg = obf_fqn.rsplit('.', 1)[0]
                oc = oc.replace(f'import {obf_fqn};', f'import {pkg}.{mean_name};')
            if oc != orig:
                other_file.write_text(oc, encoding='utf-8')
                updated += 1
        print(f"  Import-updated files: {updated}")
    print(f"  Additional AI classes: {renamed}")

def main():
    dry_run = '--dry-run' in sys.argv
    # Parse --stage flag
    stage = 'all'
    for i, arg in enumerate(sys.argv):
        if arg == '--stage' and i + 1 < len(sys.argv):
            stage = sys.argv[i + 1]
            break

    valid_stages = {'class', 'imports', 'types', 'fields', 'methods', 'inner', 'extends', 'all'}
    if stage not in valid_stages:
        print(f"Invalid stage: {stage}. Valid: {valid_stages}")
        sys.exit(1)

    run = lambda s: stage in (s, 'all')

    print("=" * 60)
    print(f"Enhanced Deobfuscation v3.0 — stage: {stage}")
    print("=" * 60)

    print("\n[1] Loading enhanced mappings...")
    all_data, class_map = load_enhanced_mappings()
    total_fields = sum(len(v["fields"]) for v in all_data.values())
    total_methods = sum(len(v["methods"]) for v in all_data.values())
    print(f"  Classes: {len(class_map)}, Fields: {total_fields}, Methods: {total_methods}")

    if not dry_run:
        if DEOBFUSCATED.exists():
            shutil.rmtree(DEOBFUSCATED)
        DEOBFUSCATED.mkdir(parents=True, exist_ok=True)

    if run('class'):
        print("\n[2] Phase 1: Class renames...")
        fqn_rename = phase1_rename_classes(class_map, dry_run)
    else:
        fqn_rename = {}

    if run('imports'):
        print("\n[3] Phase 2: Import updates...")
        phase2_update_imports(class_map, fqn_rename, dry_run)

    if run('fields') or run('methods'):
        print("\n[4] Phase 3: Field + method renames...")
        phase3_enhanced_rename(all_data, class_map, dry_run)

    if run('all'):
        print("\n[5] Phase 4: Additional AI classes...")
        phase4_additional_ai_classes(dry_run)

    if run('inner'):
        print("\n[6] Phase 5: Inner class renames...")
        phase5_rename_inner_classes(class_map, dry_run)

    if run('extends'):
        print("\n[7] Phase 6: extends/implements clause fixes...")
        phase6_fix_extends(class_map, dry_run)

    if run('types'):
        print("\n[8] Phase 7: Type position renames (type_position_renamer.py)...")
        import subprocess as sp
        result = sp.run(
            [sys.executable, str(ROOT / "tools" / "core" / "type_renamer.py")]
            + (['--dry-run'] if dry_run else []),
            cwd=str(ROOT))
        if result.returncode != 0:
            print("  WARNING: type_position_renamer returned non-zero")

    print("\n" + "=" * 60)
    if not dry_run:
        total = sum(1 for _ in DEOBFUSCATED.rglob("*.java"))
        print(f"Done! {total} Java files -> {DEOBFUSCATED}")
    else:
        print("Dry run complete.")
    print("=" * 60)

def phase6_fix_extends(class_map, dry_run=False):
    """Fix extends/implements clauses that still use old class names."""
    # Build mapping from old simple name to new simple name
    name_map = {}
    for old_fqn, new_simple in class_map.items():
        old_simple = old_fqn.rsplit('.', 1)[-1]
        if old_simple != new_simple and len(old_simple) >= 2:  # Skip single-char: too many cross-package conflicts
            name_map[old_simple] = new_simple

    fixed = 0
    for java_file in DEOBFUSCATED.rglob("*.java"):
        content = java_file.read_text(encoding='utf-8', errors='replace')
        orig = content
        for old_name, new_name in name_map.items():
            # extends OldName / implements OldName (with word boundaries)
            content = re.sub(
                rf'\b(extends\s+){re.escape(old_name)}\b',
                rf'\1{new_name}',
                content
            )
            content = re.sub(
                rf'\b(implements\s+.*?\b){re.escape(old_name)}\b',
                rf'\1{new_name}',
                content
            )
        if content != orig:
            if not dry_run:
                java_file.write_text(content, encoding='utf-8')
            fixed += 1
    print(f"  Files with extends/implements fixes: {fixed}")

def phase5_rename_inner_classes(class_map, dry_run=False):
    """Rename inner class files based on parent class renames."""
    # Build parent_old -> parent_new mapping from class_map
    parent_renames = {}
    for old_fqn, new_simple in class_map.items():
        old_simple = old_fqn.rsplit('.', 1)[-1]
        if old_simple != new_simple:
            parent_renames[old_simple] = new_simple

    renamed = 0
    for java_file in sorted(DEOBFUSCATED.rglob("*$*.java")):
        name = java_file.stem
        if '$' not in name:
            continue
        parent_old, inner_suffix = name.split('$', 1)
        if parent_old not in parent_renames:
            continue

        parent_new = parent_renames[parent_old]
        new_name = f"{parent_new}${inner_suffix}"
        new_path = java_file.parent / f"{new_name}.java"

        if dry_run:
            renamed += 1
            continue

        content = java_file.read_text(encoding='utf-8', errors='replace')
        content = re.sub(rf'(\bclass\s+){re.escape(name)}\b', rf'\1{new_name}', content)
        content = re.sub(rf'\bnew\s+{re.escape(name)}\s*\(', rf'new {new_name}(', content)
        content = re.sub(rf'(\b(?:public|private|protected)\s+){re.escape(name)}\s*\(', rf'\1{new_name}(', content)

        new_path.write_text(content, encoding='utf-8')
        java_file.unlink()
        renamed += 1

    # Update imports for inner classes
    if renamed > 0 and not dry_run:
        updated = 0
        for java_file in DEOBFUSCATED.rglob("*.java"):
            content = java_file.read_text(encoding='utf-8', errors='replace')
            orig = content
            for parent_old, parent_new in parent_renames.items():
                if parent_old == parent_new:
                    continue
                # Replace import of inner class: import pkg.OldParent$N -> import pkg.NewParent$N
                content = re.sub(
                    rf'(import\s+[\w.]+\.){re.escape(parent_old)}(\$\d+;)',
                    rf'\1{parent_new}\2',
                    content
                )
            if content != orig:
                java_file.write_text(content, encoding='utf-8')
                updated += 1
        print(f"  Import-updated files: {updated}")

    print(f"  Inner classes renamed: {renamed}")

if __name__ == "__main__":
    main()
