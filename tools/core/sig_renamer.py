#!/usr/bin/env python3
"""
Phase 2 Task 2.3 — Signature-Driven Method Renamer
Replaces apply_enhanced.py's bare-name method renamer with descriptor matching.

Key features:
- Declarations: match by name(params) from supplement.csv — EXACT match
- Call sites: disambiguate by argument count from source
- Single-overload classes: safe bulk rename
- Multi-overload classes: per-signature rename + arg-count disambiguation
- Outputs rename-audit.csv for review

Usage: python tools/sig_rename.py [--dry-run] [--file GameAction.java]
"""
import csv, os, re, sys, time
from pathlib import Path
from collections import defaultdict

csv.field_size_limit(10 * 1024 * 1024)

ROOT = Path(__file__).resolve().parent.parent.parent  # tools/core/ → ROOT
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import SUPPLEMENT_CSV as SUPP, DEOBFUSCATED_DIR as DEOBF
DEOBFUSCATED = ROOT / "03-deobfuscated"
SUPP_CSV = ROOT / "mappings" / "supplement.csv"
AUDIT_CSV = ROOT / "rename-audit.csv"

def load_sig_mappings():
    """Load method mappings WITH signatures from supplement.csv.
    Returns: {fqn: {(old_name, params): (new_name, notes)}}
    """
    sig_map = defaultdict(dict)
    stats = defaultdict(lambda: {'total': 0, 'with_sig': 0})

    with open(SUPP_CSV, encoding='utf-8') as f:
        for r in csv.DictReader(f):
            if r['type'] != 'method':
                continue
            pkg = (r.get('obfuscated_package') or '').strip()
            cls = (r.get('obfuscated_class') or '').strip()
            mem = (r.get('obfuscated_member') or '').strip()
            new_name = (r.get('meaningful_name') or '').strip()
            if not pkg or not cls or not mem or not new_name:
                continue

            fqn = f"{pkg}.{cls}"
            stats[fqn]['total'] += 1

            if '(' in mem and ')' in mem:
                method_name = mem[:mem.index('(')]
                params = mem[mem.index('(')+1:mem.index(')')]
                stats[fqn]['with_sig'] += 1
            else:
                method_name = mem
                params = ''

            key = (method_name, params)
            # Last mapping wins (consistent with existing behavior)
            sig_map[fqn][key] = (new_name, r.get('notes', ''))

    # Stats
    total_cls = len(sig_map)
    multi_cls = sum(1 for f in sig_map if stats[f]['total'] > 1)
    sig_cls = sum(1 for f in sig_map if stats[f]['with_sig'] > 0)
    print(f"  {total_cls} classes with method mappings")
    print(f"  {sig_cls} with signatures, {multi_cls} multi-method classes")
    return sig_map

def count_args(params_str):
    """Count comma-separated arguments. Empty string = 0."""
    if not params_str.strip():
        return 0
    return len([p for p in params_str.split(',') if p.strip()])

def rename_file_methods(content, class_mappings, file_fqn):
    """Apply signature-aware method renames to a single file.
    Returns (new_content, audit_entries).
    """
    if not class_mappings:
        return content, []

    audit = []
    # Group: old_bare_name -> list of (params, new_name)
    by_old_name = defaultdict(list)
    for (old_name, params), (new_name, notes) in class_mappings.items():
        by_old_name[old_name].append((params, new_name, notes))

    lines = content.split('\n')
    out = []
    renames_applied = set()

    for line in lines:
        mod = line
        if re.match(r'^\s*(?:import|package)\s+', mod):
            out.append(mod)
            continue

        for old_name, overloads in by_old_name.items():
            if len(old_name) > 20:  # Safety
                continue

            # Case 1: Single overload → safe to rename everywhere
            if len(overloads) == 1:
                params, new_name, notes = overloads[0]
                if old_name == new_name:
                    continue

                old_line = mod
                # this.method( + super.method(
                mod = re.sub(rf'\bthis\.{re.escape(old_name)}\s*\(', f'this.{new_name}(', mod)
                mod = re.sub(rf'\bsuper\.{re.escape(old_name)}\s*\(', f'super.{new_name}(', mod)
                # Method declaration: [type] methodName(
                mod = re.sub(rf'^(\s*(?:\w+\s+)*?){re.escape(old_name)}\s*\(',
                           rf'\1{new_name}(', mod)
                if mod != old_line:
                    renames_applied.add((old_name, new_name))
                    audit.append((file_fqn, old_name, params, new_name, 'single-overload', 'ok'))

            # Case 2: Multiple overloads → be careful
            else:
                # Build a per-signature rename map
                for params, new_name, notes in overloads:
                    if old_name == new_name:
                        continue
                    n_args = count_args(params)

                    # Declaration match: check if this line is a declaration
                    # with the right number of params
                    decl_match = re.match(
                        rf'^(\s*(?:\w+\s+)*?){re.escape(old_name)}\s*\(([^)]*)\)',
                        mod)
                    if decl_match:
                        line_params = decl_match.group(2).strip()
                        line_n_args = count_args(line_params)
                        if line_n_args == n_args:
                            old_line = mod
                            mod = re.sub(
                                rf'^(\s*(?:\w+\s+)*?){re.escape(old_name)}\s*\(',
                                rf'\1{new_name}(', mod, count=1)
                            if mod != old_line:
                                renames_applied.add((old_name, new_name))
                                audit.append((file_fqn, old_name, params, new_name,
                                             'multi-overload-decl', 'ok'))
                                break  # Found the matching overload

                    # Call site: this.method( with arg count
                    call_match = re.search(
                        rf'\bthis\.{re.escape(old_name)}\s*\(', mod)
                    if call_match and n_args > 0:
                        # Get argument count after the (
                        rest = mod[call_match.end():]
                        # Count commas until matching )
                        depth = 1
                        commas = 0
                        for ch in rest:
                            if ch == '(':
                                depth += 1
                            elif ch == ')':
                                depth -= 1
                                if depth == 0:
                                    break
                            elif ch == ',' and depth == 1:
                                commas += 1
                        call_n_args = commas + 1 if rest.strip()[0] != ')' else 0
                        if call_n_args == n_args:
                            mod = re.sub(
                                rf'\bthis\.{re.escape(old_name)}\s*\(',
                                f'this.{new_name}(', mod, count=1)
                            audit.append((file_fqn, old_name, params, new_name,
                                         'multi-overload-call', 'ok'))

        out.append(mod)

    return '\n'.join(out), audit

def main():
    dry = '--dry-run' in sys.argv
    single_file = None
    for i, a in enumerate(sys.argv):
        if a == '--file' and i+1 < len(sys.argv):
            single_file = sys.argv[i+1]

    print("=" * 60)
    print("Signature-Driven Method Renamer")
    print("=" * 60)

    t0 = time.time()
    print("\n[1] Loading signature mappings...")
    sig_map = load_sig_mappings()

    # Build reverse: deobf_class_name -> obf_FQN
    # (needed to match files in 03-deobfuscated to mappings)
    print("\n[2] Loading class map for FQN translation...")
    import json
    class_map = {}  # {obf_FQN: deobf_name}
    mp = ROOT / "mappings" / "mappings.json"
    if mp.exists():
        with open(mp, encoding='utf-8') as f:
            class_map.update(json.load(f).get("classes", {}))
    mc = ROOT / "mappings" / "mappings.csv"
    if mc.exists():
        for r in csv.DictReader(open(mc, encoding='utf-8')):
            if r['type'] == 'class' and r.get('meaningful_name'):
                class_map[f"{r['obfuscated_package']}.{r['obfuscated_class']}"] = r['meaningful_name']
    print(f"  {len(class_map)} class mappings")

    print("\n[3] Building file index...")
    file_index = {}  # {deobf_FQN: (path, simple)}
    for jf in DEOBFUSCATED.rglob("*.java"):
        rel = str(jf.relative_to(DEOBFUSCATED)).replace(os.sep, '.').replace('.java', '')
        simple = rel.rsplit('.', 1)[-1]
        file_index[rel] = (str(jf), simple)
    print(f"  {len(file_index)} files indexed")

    print("\n[4] Applying signature-aware renames...")
    total_files = 0
    total_renames = 0
    all_audit = []

    for obf_fqn, mappings in sig_map.items():
        # Translate obfuscated FQN → deobfuscated FQN
        deobf_fqn = class_map.get(obf_fqn)
        if not deobf_fqn:
            # Try: obfuscated FQN is a.b.c, deobfuscated would be a.b.MeaningfulName
            # Build by replacing last segment with deobf name
            parts = obf_fqn.rsplit('.', 1)
            pkg = parts[0] if len(parts) == 2 else ''
            obf_cls = parts[1] if len(parts) == 2 else obf_fqn
            # Check if any class_map value ends with this pattern
            for mapped_fqn, deobf in class_map.items():
                if mapped_fqn.endswith('.' + obf_cls):
                    deobf_fqn = mapped_fqn.rsplit('.', 1)[0] + '.' + deobf
                    break

        if not deobf_fqn:
            continue

        # Find in file_index
        if deobf_fqn in file_index:
            jf_path, _ = file_index[deobf_fqn]
        else:
            # Try partial match
            jf_path = None
            for fq, (fp, _) in file_index.items():
                if fq.endswith('.' + deobf_fqn.rsplit('.', 1)[-1]):
                    jf_path = fp
                    break

        if not jf_path:
            continue
        if single_file and single_file not in jf_path:
            continue

        try:
            content = Path(jf_path).read_text(encoding='utf-8', errors='replace')
        except:
            continue

        new_content, audit = rename_file_methods(content, mappings, obf_fqn)
        if audit:
            total_renames += len(set((a[2], a[3]) for a in audit))  # unique (old,new) pairs
            all_audit.extend(audit)

            if not dry:
                bak = Path(jf_path).with_suffix('.java.bak')
                if not bak.exists():
                    Path(jf_path).rename(bak)
                Path(jf_path).write_text(new_content, encoding='utf-8')

            total_files += 1

    print(f"\n  Files with renames: {total_files}")
    print(f"  Unique method renames: {total_renames}")
    print(f"  Audit entries: {len(all_audit)}")

    # Write audit
    with open(AUDIT_CSV, 'w', newline='', encoding='utf-8') as f:
        w = csv.writer(f)
        w.writerow(['fqn', 'old_name', 'params', 'new_name', 'context', 'status'])
        w.writerows(all_audit)
    print(f"\n  Audit: {AUDIT_CSV}")
    print(f"  Time: {time.time()-t0:.1f}s")

    if dry:
        print("  DRY RUN — no changes")


if __name__ == '__main__':
    main()
