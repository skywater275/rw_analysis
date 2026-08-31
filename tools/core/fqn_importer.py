#!/usr/bin/env python3
"""
FQN Importer — 将代码中的混淆FQN替换为短类名+import语句

Usage:
    python tools/core/fqn_importer.py --dry-run     # 预览变更
    python tools/core/fqn_importer.py --apply        # 应用变更
    python tools/core/fqn_importer.py --stats        # 仅统计
"""
import sys, os, re, csv
from pathlib import Path
from collections import defaultdict

ROOT = Path(__file__).resolve().parents[2]  # tools/core/ → ROOT
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import CLASS_DISCOVERIES, DEOBFUSCATED_DIR

csv.field_size_limit(10 * 1024 * 1024)


def load_class_map():
    """Load {obf_FQN: (import_pkg, simple_name)} from class-discoveries.csv

    Supports 'real_pkg:' in notes field for classes that were moved by package renamer.
    When real_pkg is set, the import uses that package instead of obfuscated_package.
    Format in notes: 'real_pkg:com.corrodinggames.rts.game.units.custom.b'
    """
    cls_map = {}
    if not CLASS_DISCOVERIES.exists():
        print("ERROR: class-discoveries.csv not found")
        return cls_map

    import re
    with open(CLASS_DISCOVERIES, encoding='utf-8') as f:
        for r in csv.DictReader(f):
            if r.get('type') != 'class':
                continue
            name = r.get('meaningful_name', '').strip()
            pkg = r.get('obfuscated_package', '').strip()
            cls = r.get('obfuscated_class', '').strip()
            notes = r.get('notes', '').strip()
            if not name or not pkg or not cls:
                continue
            # Skip unmapped (name == obfuscated name) unless real_pkg is set
            if name == cls and 'real_pkg:' not in notes:
                continue
            fqn = f"{pkg}.{cls}"
            # Skip inner classes (contain $)
            if '$' in name or '$' in cls:
                continue

            # Check for real_pkg override (package renamer fix)
            real_match = re.search(r'real_pkg:(\S+)', notes)
            import_pkg = real_match.group(1) if real_match else pkg

            cls_map[fqn] = (import_pkg, name)
    return cls_map


def analyze_file(filepath, cls_map):
    """Analyze a Java file for replaceable FQN references.
    Returns: [{fqn, pkg, new_name, old_simple, occurrences}]
    """
    with open(filepath, 'r', encoding='utf-8', errors='replace') as f:
        content = f.read()

    lines = content.split('\n')

    # Get existing imports and the file's own package
    file_package = ''
    existing_imports = {}
    for line in lines:
        line = line.strip()
        if line.startswith('package '):
            file_package = line.split()[1].rstrip(';')
        elif line.startswith('import '):
            imp = line.split()[1].rstrip(';')
            simple = imp.rsplit('.', 1)[-1]
            existing_imports[simple] = imp

    # Find fully-qualified class name references
    # Match pattern: com.corrodinggames...*.SingleChar
    replacements = []
    for obf_fqn, (import_pkg, new_name) in cls_map.items():
        obf_simple = obf_fqn.rsplit('.', 1)[-1]

        # Skip if not in file
        if obf_fqn not in content:
            continue

        # Count occurrences
        pattern = r'\b' + obf_fqn.replace('.', r'\.') + r'\b'
        occurrences = len(re.findall(pattern, content))
        if occurrences == 0:
            continue

        # Determine target import using the real_pkg if set (package renamer fix)
        target_import = f"{import_pkg}.{new_name}"

        # Skip if this is self-referencing (file's own package)
        if file_package == import_pkg:
            continue

        replacements.append({
            'obf_fqn': obf_fqn,
            'obf_simple': obf_simple,
            'pkg': import_pkg,
            'new_name': new_name,
            'target_import': target_import,
            'occurrences': occurrences,
        })

    return replacements, lines, existing_imports, file_package


def apply_replacements(filepath, replacements, lines, existing_imports, file_package):
    """Apply FQN→short name replacements and add imports."""
    content = '\n'.join(lines)
    original = content
    imports_to_add = []

    for repl in replacements:
        obf_fqn = repl['obf_fqn']
        new_name = repl['new_name']
        target_import = repl['target_import']

        # Replace all FQN occurrences with short name
        pattern = r'\b' + obf_fqn.replace('.', r'\.') + r'\b'
        content = re.sub(pattern, new_name, content)

        # Check if we need to add import
        if new_name not in existing_imports and target_import not in existing_imports.values():
            imports_to_add.append(target_import)
            existing_imports[new_name] = target_import  # Track to avoid duplicates

    if content == original:
        return None, 0

    # Insert new imports
    if imports_to_add:
        new_lines = content.split('\n')
        insert_pos = 0
        for i, line in enumerate(new_lines):
            if line.strip().startswith('package '):
                insert_pos = i + 1
            elif line.strip().startswith('import '):
                insert_pos = i + 1

        # Insert after last import, skip blank lines to find insertion point
        while insert_pos < len(new_lines) and new_lines[insert_pos].strip() == '':
            insert_pos += 1

        for imp in sorted(set(imports_to_add)):
            new_lines.insert(insert_pos, f'import {imp};')
            insert_pos += 1

        content = '\n'.join(new_lines)

    return content, len(replacements)


def main():
    dry_run = '--apply' not in sys.argv
    stats_only = '--stats' in sys.argv

    print("=" * 60)
    print(f"FQN Importer {'(DRY RUN)' if dry_run else '(APPLY)'}")
    print("=" * 60)

    cls_map = load_class_map()
    print(f"Loaded {len(cls_map)} class mappings")

    if stats_only:
        # Just count potential replacements
        total_occurrences = 0
        total_files = 0
        by_class = defaultdict(int)
        for root, dirs, files in os.walk(DEOBFUSCATED_DIR):
            dirs[:] = [d for d in dirs if not d.startswith('.')]
            for fn in files:
                if not fn.endswith('.java'): continue
                fp = os.path.join(root, fn)
                repls, _, _, _ = analyze_file(fp, cls_map)
                if repls:
                    total_files += 1
                    for r in repls:
                        total_occurrences += r['occurrences']
                        by_class[r['obf_fqn']] += r['occurrences']

        print(f"\nPotential replacements:")
        print(f"  Files: {total_files}")
        print(f"  Occurrences: {total_occurrences}")
        print(f"\nTop classes:")
        for fqn, cnt in sorted(by_class.items(), key=lambda x: -x[1])[:20]:
            name = cls_map[fqn][1]
            print(f"  {cnt:5d} {fqn} -> {name}")
        return

    # Process all files
    changed_files = 0
    total_replacements = 0
    errors = []

    for root, dirs, files in os.walk(DEOBFUSCATED_DIR):
        dirs[:] = [d for d in dirs if not d.startswith('.')]
        for fn in files:
            if not fn.endswith('.java'): continue
            fp = os.path.join(root, fn)

            repls, lines, existing_imports, file_package = analyze_file(fp, cls_map)
            if not repls:
                continue

            if dry_run:
                rel = os.path.relpath(fp, DEOBFUSCATED_DIR)
                names = ', '.join(f'{r["obf_simple"]}->{r["new_name"]}' for r in repls[:3])
                more = f' +{len(repls)-3}' if len(repls) > 3 else ''
                print(f"  {rel}: {names}{more}")
                changed_files += 1
                total_replacements += sum(r['occurrences'] for r in repls)
            else:
                try:
                    new_content, n = apply_replacements(fp, repls, lines, existing_imports, file_package)
                    if new_content:
                        with open(fp, 'w', encoding='utf-8') as f:
                            f.write(new_content)
                        changed_files += 1
                        total_replacements += n
                except Exception as e:
                    errors.append(f"{fp}: {e}")

    print(f"\nResults:")
    print(f"  Files changed: {changed_files}")
    print(f"  Occurrences replaced: {total_replacements}")
    if errors:
        print(f"  Errors: {len(errors)}")
        for e in errors[:5]:
            print(f"    - {e}")

    if dry_run:
        print("\nDRY RUN — run with --apply to apply changes")
        print("  Also try: --stats for detailed counts")


if __name__ == "__main__":
    main()
