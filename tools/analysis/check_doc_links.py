#!/usr/bin/env python3
"""检查 docs/ 与根 README 中的**相对链接是否失效**（清理归档后必需）。

用法: python tools/analysis/check_doc_links.py
"""
import re
import sys
from pathlib import Path

sys.stdout.reconfigure(encoding='utf-8')
ROOT = Path(__file__).resolve().parents[2]
DOCS = ROOT / 'docs'

SCAN = [DOCS, ROOT / 'README.md', ROOT / 'CLAUDE.md']
LINK = re.compile(r'\[[^\]]*\]\(([^)#\s]+)(?:#[^)]*)?\)')

broken = []
total = 0
for target in SCAN:
    files = []
    if target.is_dir():
        files = sorted(target.rglob('*.md'))
    else:
        files = [target] if target.exists() else []
    for f in files:
        if '_archive' in f.parts:
            continue
        txt = f.read_text(encoding='utf-8', errors='replace')
        for m in LINK.finditer(txt):
            url = m.group(1)
            if url.startswith(('http://', 'https://', 'mailto:', '#')):
                continue
            total += 1
            # 解析相对路径
            p = (f.parent / url).resolve()
            if not p.exists():
                broken.append((str(f.relative_to(ROOT)), url))

print('检查相对链接 %d 条' % total)
print()
if not broken:
    print('✅ 无失效链接')
    sys.exit(0)
print('⚠ 失效链接 %d 条：' % len(broken))
for src, url in broken[:60]:
    print('  %-58s → %s' % (src[-56:], url))
if len(broken) > 60:
    print('  …（共 %d）' % len(broken))
