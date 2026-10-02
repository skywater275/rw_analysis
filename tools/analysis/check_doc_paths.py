#!/usr/bin/env python3
"""★ **文档路径门禁** —— 防止文档里出现失效的项目路径 ✓（防回归 ✓）。

判据 ✓：提取文档里的路径式 token（反引号内、代码块内 ✓），
  凡以**已知顶层目录**开头者（`tools/` `build/` `docs/` `mappings/` `01-classes/`
  `02-decompiled/` `02b-decompiled/` `03-deobfuscated/` `RustedWarfare/` ✓）
  **必须存在** ✓，否则判 FAIL ✓。

排除 ✓：Java 包路径（`com/corrodinggames/...` ✓）· jar 内条目 · 通配/占位符（`X` `N` `*` `<>` ✓）
      · 归档目录之外的相对引用（由 `check_doc_links.py` 负责 ✓）· 明确的"历史/已删"描述 ✓

用法::

    python tools/analysis/check_doc_paths.py            # 存活文档（退出码 0=通过 ✓）
    python tools/analysis/check_doc_paths.py --all      # 含归档 ✓（归档不判 FAIL ✓，只统计 ✓）
    python tools/analysis/check_doc_paths.py --json     # 机器可读 ✓
"""
import argparse
import json
import re
import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parents[1]))
try:
    from rwlib.paths import P
    ROOT = P.root
except Exception:
    ROOT = Path(__file__).resolve().parents[2]

TOP = ('tools/', 'build/', 'docs/', 'mappings/', '01-classes/', '02-decompiled/',
       '02b-decompiled/', '03-deobfuscated/', 'RustedWarfare/', 'build-output/')
TOK = re.compile(r'`([A-Za-z0-9_./-]{5,})`')
# 排除：占位符 / 通配 / 历史描述 ✓
SKIP_PAT = re.compile(r'(X|N|\*|<|>|\{|\}|\.\.\.|ARCHIVE|历史|已删|已归档|原 |旧 )')


def scan(include_archive: bool = False):
    docs = [p for p in list((ROOT / 'docs').rglob('*.md')) + list(ROOT.glob('*.md'))]
    if not include_archive:
        docs = [p for p in docs if '_archive' not in p.parts]
    bad, checked = [], 0
    for p in docs:
        txt = p.read_text(encoding='utf-8', errors='replace')
        for m in TOK.finditer(txt):
            s = m.group(1).strip()
            if not s.startswith(TOP):
                continue
            if SKIP_PAT.search(s) or s.endswith('/'):
                continue
            checked += 1
            cand = s.rstrip('.,;:')
            if (ROOT / cand).exists() or (p.parent / cand).exists():
                continue
            bad.append(dict(doc=p.relative_to(ROOT).as_posix(), token=cand))
    return checked, bad


def main():
    ap = argparse.ArgumentParser(description='文档路径门禁（防失效路径回归 ✓）')
    ap.add_argument('--all', action='store_true', help='含归档文档（只统计 ✓）')
    ap.add_argument('--json', action='store_true')
    a = ap.parse_args()
    checked, bad = scan(include_archive=a.all)
    if a.json:
        print(json.dumps(dict(checked=checked, bad=bad), ensure_ascii=False, indent=2))
        return 0 if not bad else 1
    print('★ 文档路径门禁：检查 %d 个路径 token ✓' % checked)
    if not bad:
        print('✅ 无失效项目路径 ✓')
        return 0
    by_doc = {}
    for b in bad:
        by_doc.setdefault(b['doc'], []).append(b['token'])
    print('⚠ 失效 **%d** 处（%d 篇）✗' % (len(bad), len(by_doc)))
    for d, toks in sorted(by_doc.items()):
        print('   %-52s %s' % (d[:52], toks[:4]))
    return 1


if __name__ == '__main__':
    sys.exit(main())
