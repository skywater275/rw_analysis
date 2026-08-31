#!/usr/bin/env python3
"""
Phase 1 — Type Position Renamer v1.0
Scope-aware renamer: extends, casts, instanceof, new, generics, field/param types.
Usage: python tools/type_position_renamer.py [--dry-run]
"""
import csv, json, os, re, sys, time
from pathlib import Path
from collections import defaultdict

ROOT = Path(__file__).resolve().parent.parent.parent  # tools/*/ -> tools/ -> ROOT
DEOBFUSCATED = ROOT / "03-deobfuscated"
MAPPINGS_DIR = ROOT / "mappings"

def load_class_map():
    """Build {obf_FQN: deobf_name} and {pkg: {obf_cls: deobf_cls}}."""
    class_map = {}
    pkg_map = defaultdict(dict)

    def add(pkg, cls, deobf):
        if pkg and cls and deobf:
            fqn = f"{pkg}.{cls}"
            class_map[fqn] = deobf
            pkg_map[pkg][cls] = deobf

    # mappings.csv
    with open(MAPPINGS_DIR / "mappings.csv", encoding='utf-8') as f:
        for r in csv.DictReader(f):
            if r['type'] == 'class' and r.get('meaningful_name'):
                add(r['obfuscated_package'], r['obfuscated_class'], r['meaningful_name'])

    # mappings.json
    with open(MAPPINGS_DIR / "mappings.json", encoding='utf-8') as f:
        for obf, deobf in json.load(f).get("classes", {}).items():
            p = obf.rsplit('.', 1)
            add(p[0] if len(p)==2 else '', p[1] if len(p)==2 else obf, deobf)

    # class-discoveries.csv
    disc = MAPPINGS_DIR / "class-discoveries.csv"
    if disc.exists():
        with open(disc, encoding='utf-8') as f:
            for r in csv.DictReader(f):
                if r.get('type') == 'class' and r.get('meaningful_name'):
                    add(r.get('obfuscated_package',''), r.get('obfuscated_class',''), r['meaningful_name'])

    print(f"  Loaded {len(class_map)} class mappings, {len(pkg_map)} packages")
    return class_map, pkg_map

def build_deobf_to_obf(class_map):
    """{deobf_simple -> obf_simple} for short obfuscated names."""
    d2o = {}
    for fqn, deobf in class_map.items():
        parts = fqn.rsplit('.', 1)
        obf = parts[1] if len(parts)==2 else fqn
        if len(obf) <= 3:
            d2o[deobf] = obf
    return d2o

def build_file_scope(content, file_pkg, pkg_map, d2o):
    """{obf_char: deobf_name} from imports + same-package."""
    scope = {}
    for m in re.finditer(r'^import\s+([\w.]+)\.(\w+)\s*;', content, re.MULTILINE):
        imp = m.group(2)
        if imp in d2o:
            scope[d2o[imp]] = imp
    if file_pkg and file_pkg in pkg_map:
        for obf, deobf in pkg_map[file_pkg].items():
            if len(obf) <= 3:
                # Same-package overrides imports (common for single-char names)
                scope[obf] = deobf
    return scope

# ── Rename engine ───────────────────────────────────────────────

def rename_in_file(content, scope):
    """Apply type renames. Returns (new_content, change_count)."""
    if not scope:
        return content, 0

    # Fast pre-filter: which chars appear in type-like positions?
    active = {}
    for obf, deobf in scope.items():
        if obf == deobf or len(obf) > 3:
            continue
        # Extended pre-filter: also check for field/return type patterns
        if any(pat in content for pat in [
            f' {obf} ', f'({obf}', f'<{obf}>', f'new {obf}',
            f'extends {obf}', f'implements {obf}', f'instanceof {obf}',
            # P4.2 additions: field types and return types
            f' {obf} ',  # catches field: 'public e texture;' and return: 'public e get()'
        ]):
            active[obf] = deobf
    if not active:
        return content, 0

    lines = content.split('\n')
    out = []
    changes = 0

    for line in lines:
        mod = line
        if re.match(r'^\s*(?:import|package)\s+', mod):
            out.append(mod)
            continue

        for obf, deobf in active.items():
            old = mod
            # extends/implements
            mod = re.sub(rf'\b(extends|implements)\s+{re.escape(obf)}\b', rf'\1 {deobf}', mod)
            # casts
            mod = re.sub(rf'\(\s*{re.escape(obf)}\s*\)\s*(?=[\w.(])', rf'({deobf}) ', mod)
            # instanceof
            mod = re.sub(rf'\binstanceof\s+{re.escape(obf)}\b', rf'instanceof {deobf}', mod)
            # new
            mod = re.sub(rf'\bnew\s+{re.escape(obf)}\s*([(<])', rf'new {deobf}\1', mod)
            # generics <X> <X,
            mod = re.sub(rf'<\s*{re.escape(obf)}\s*([>,])', rf'<{deobf}\1', mod)
            # param types: (X name or , X name
            mod = re.sub(rf'\(\s*{re.escape(obf)}\s+(\w)', rf'({deobf} \1', mod)
            mod = re.sub(rf',\s*{re.escape(obf)}\s+(\w)', rf', {deobf} \1', mod)
            # P4.2: field declarations: access X name; (skip import/package lines)
            mod = re.sub(rf'((?:public|private|protected)\s+(?:static\s+|final\s+|volatile\s+|transient\s+)*){re.escape(obf)}\s+(\w+\s*;)', rf'\1{deobf} \2', mod)
            # P4.2: return types: access X methodName(
            mod = re.sub(rf'((?:public|private|protected)\s+(?:static\s+|final\s+|abstract\s+|synchronized\s+|native\s+)*){re.escape(obf)}\s+(\w+\s*\()', rf'\1{deobf} \2', mod)
            if mod != old:
                changes += 1

        out.append(mod)

    return '\n'.join(out), changes

# ── Main ────────────────────────────────────────────────────────

def main():
    dry = '--dry-run' in sys.argv
    print("=" * 60)
    print("Type Position Renamer v1.0")
    print("=" * 60)

    t0 = time.time()
    class_map, pkg_map = load_class_map()
    d2o = build_deobf_to_obf(class_map)
    print(f"  {len(d2o)} short-name reverse mappings")

    files = list(DEOBFUSCATED.rglob("*.java"))
    print(f"  Processing {len(files)} files...")

    total_changes = 0
    files_changed = 0
    extends_fixed = 0

    for i, jf in enumerate(files):
        if i % 200 == 0:
            print(f"    {i}/{len(files)}...")

        try:
            content = jf.read_text(encoding='utf-8', errors='replace')
        except:
            continue

        pkg_m = re.search(r'^package\s+([\w.]+)\s*;', content, re.MULTILINE)
        file_pkg = pkg_m.group(1) if pkg_m else ''

        scope = build_file_scope(content, file_pkg, pkg_map, d2o)
        if not scope:
            continue

        ext_before = len(re.findall(r'\b(?:extends|implements)\s+([a-z])\b', content))
        new_content, changes = rename_in_file(content, scope)

        if changes > 0:
            ext_after = len(re.findall(r'\b(?:extends|implements)\s+([a-z])\b', new_content))
            extends_fixed += max(0, ext_before - ext_after)

            if not dry:
                bak = jf.with_suffix('.java.bak')
                if not bak.exists():
                    jf.rename(bak)
                jf.write_text(new_content, encoding='utf-8')

            total_changes += changes
            files_changed += 1

    print(f"\n  Done in {time.time()-t0:.1f}s")
    print(f"  Files changed: {files_changed}")
    print(f"  Total renames: {total_changes}")
    print(f"  extends/implements fixes: {extends_fixed}")
    if dry:
        print("  DRY RUN — no files modified")


if __name__ == '__main__':
    main()
