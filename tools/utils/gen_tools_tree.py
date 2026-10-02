#!/usr/bin/env python3
"""★ **重新生成工具索引**（新架构 ✓）—— 数据源 = **规格注册表**（`tools/registry` ✓）。

与旧版的区别 ✓：
  · 数据源从「已归档的 `manager.py` 注册表」✗ 改为「**规格注册表**（argparse 自动导出 ✓）」
  · **只扫活工具** ✓（跳过 `_archive/` ✗）⇒ 索引不再继承历史路径 ✓
  · 输出含**参数与面板控件**（与 `cli.py panel` 同源 ✓）⇒ 文档即面板说明 ✓
  · 不再报"注册表健康度"（旧注册表已归档 ✓，由 `cli.py doctor` 取代 ✓）

用法::

    python tools/utils/gen_tools_tree.py            # 预览（打印统计 ✓）
    python tools/utils/gen_tools_tree.py --apply    # 写 docs/deobfuscation/TOOLS-INVENTORY.md
"""
import argparse
import sys
from collections import defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT / 'tools'))

from registry import catalog  # noqa: E402

CAT_CN = {
    'gates': '门禁（验收判据 ✓）', 'rig': '测试台（构建 / A-B / 校验 ✓）',
    'core': '核心（反混淆与改名引擎 ✓）', 'analysis': '测量与审计 ✓',
    'fixers': '构建链修复器 ✓', 'utils': '通用工具 ✓', 'capture': '抓取与动态测试 ✓',
    'registry': '集成注册表（规格 / 执行 / 面板 ✓）', 'rwlib': '共享库（路径 / 字节码 / 映射 ✓）',
    'audit': '审计 ✓', 'analyze': '分析 ✓', 'resolvers': '解析器 ✓', 'other': '其他 ✓',
}


def _sanitize(desc: str) -> str:
    """★ 过滤描述里的**失效项目路径** ✓（docstring 常引用已清理的历史文件 ✗）。"""
    import re as _re

    def fix(m):
        s = m.group(0)
        return s if (ROOT / s).exists() else '（历史文件，已清理 ✓）'

    desc = _re.sub(r'(?:build|tools|mappings|docs)/[A-Za-z0-9_./-]+\.(?:json|csv|txt|jar|py)', fix, desc)
    return desc


def main():
    ap = argparse.ArgumentParser(description='重新生成工具索引（新架构 · 规格注册表驱动 ✓）')
    ap.add_argument('--apply', action='store_true', help='写入文档（默认只预览 ✓）')
    a = ap.parse_args()
    specs = catalog.discover(refresh=True)
    s = catalog.summary(specs)
    by_cat = defaultdict(list)
    for sp in specs:
        by_cat[sp.category].append(sp)

    L = ['# 工具索引（自动生成 ✓ 禁止手改 ✗）', '',
         '> 生成器：`python tools/utils/gen_tools_tree.py --apply`',
         '> 数据源：**规格注册表**（`tools/registry` ✓ —— 从各脚本 `argparse` **自动导出** ✓）',
         '> 口径：活工具链（**跳过 `tools/_archive/` ✓**）· 数字见 [../STATUS.md](../STATUS.md) ✓', '',
         '| 指标 | 值 |', '|---|---|',
         '| 工具总数 | **%d** |' % s['total'],
         '| 分类数 | **%d** |' % len(s['categories']),
         '| 带参数 | **%d** |' % s['with_params'],
         '| 写操作（需 `--apply` ✓） | **%d** |' % s['writers'], '',
         '★ 统一入口：`python tools/cli.py list | show <id> | edit <id> | panel` ✓', '',
         '---', '']
    for cat in sorted(by_cat):
        items = sorted(by_cat[cat], key=lambda x: x.id)
        L += ['## %s（`%s` · %d 个 ✓）' % (CAT_CN.get(cat, cat), cat, len(items)), '',
              '| 工具 | 参数 | 写 | 说明 |', '|---|---|---|---|']
        for sp in items:
            desc = _sanitize(sp.desc or '—').replace('|', '/')[:110]
            L.append('| [`%s`](../../%s) | %d | %s | %s |'
                     % (sp.id, sp.path, len(sp.params), '✎' if sp.writes else '', desc))
        L.append('')
        detailed = [x for x in items if x.params]
        if detailed:
            L += ['<details><summary>参数明细（%d 个工具 ✓）</summary>' % len(detailed), '']
            for sp in detailed:
                L.append('**`%s`** — `%s`' % (sp.id, sp.path))
                L.append('')
                L.append('| 参数 | 类型 | 控件 | 默认 | 说明 |')
                L.append('|---|---|---|---|---|')
                for p in sp.params:
                    d = ('`%s`' % p.default) if p.default not in (None, '') else '—'
                    if isinstance(p.default, bool):
                        d = '是' if p.default else '否'
                    L.append('| `--%s` | %s | %s | %s | %s |'
                             % (p.name, p.type, p.widget(), d,
                                _sanitize(p.help or '—').replace('|', '/')[:90]))
                L.append('')
            L += ['</details>', '']
    out = ROOT / 'docs' / 'deobfuscation' / 'TOOLS-INVENTORY.md'
    txt = '\n'.join(L) + '\n'
    # ★ 兜底：对**整篇输出**再做一次失效路径过滤 ✓（覆盖 desc/help 之外的来源 ✓）
    import re as _re
    txt = _re.sub(r'(?:build|tools|mappings|docs)/[A-Za-z0-9_./-]+\.(?:json|csv|txt|jar|py)',
                  lambda m: m.group(0) if (ROOT / m.group(0)).exists() else '（历史文件，已清理 ✓）',
                  txt)
    print('★ 工具 %d 个 · 分类 %d · 带参数 %d · 写操作 %d ✓'
          % (s['total'], len(s['categories']), s['with_params'], s['writers']))
    print('   文档规模 = %d 行 / %.1f KB ✓' % (txt.count('\n') + 1, len(txt.encode()) / 1024))
    if a.apply:
        out.write_text(txt, encoding='utf-8')
        print('   ✔ 已写入 %s ✓' % out.relative_to(ROOT).as_posix())
    else:
        print('   [预览] 加 --apply 写入 ✓')


if __name__ == '__main__':
    main()
