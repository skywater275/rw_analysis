#!/usr/bin/env python3
"""Mark ambiguous (unsigned) method rows in supplement.csv with [TODO-SIG] annotation."""
import sys, csv
from pathlib import Path
from collections import defaultdict

csv.field_size_limit(10 * 1024 * 1024)

ROOT = Path(__file__).resolve().parent.parent.parent  # tools/resolvers/ → ROOT
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import SUPPLEMENT_CSV as SUPP

rows = []
with open(SUPP, encoding='utf-8') as f:
    reader = csv.DictReader(f)
    fieldnames = reader.fieldnames
    for row in reader:
        rows.append(row)

marked = 0
details = defaultdict(list)

for row in rows:
    typ = row.get('type', '')
    mem = row.get('obfuscated_member', '')

    if typ == 'method' and '(' not in mem:
        pkg = row.get('obfuscated_package', '')
        cls = row.get('obfuscated_class', '')
        existing = row.get('notes', '')
        if 'TODO-SIG' not in existing:
            tag = '[TODO-SIG: multi-overload — needs manual disambiguation]'
            row['notes'] = f"{tag} {existing}".strip()
        marked += 1
        details[f"{pkg}.{cls}"].append(mem)

# Write with csv module (v18.2 fix: prevent quote-doubling bug)
with open(SUPP, 'w', encoding='utf-8', newline='') as f:
    writer = csv.DictWriter(f, fieldnames=fieldnames)
    writer.writeheader()
    writer.writerows(rows)

print(f"Marked {marked} ambiguous methods with [TODO-SIG]")
print(f"Across {len(details)} classes:")
for fqn, methods in sorted(details.items()):
    method_list = ", ".join(methods[:6])
    more = f" +{len(methods)-6} more" if len(methods) > 6 else ""
    print(f"  {fqn}: {len(methods)} methods ({method_list}{more})")
print("Done.")
