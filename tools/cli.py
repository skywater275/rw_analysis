#!/usr/bin/env python3
"""★ **项目工具链统一入口** —— 一个命令访问全部工具 ✓。

```bash
python tools/cli.py list                        # 全部工具（按分类 ✓）
python tools/cli.py show  javac_gate            # 看参数
python tools/cli.py run   javac_gate            # 运行（默认参数 ✓）
python tools/cli.py run   ab_run --set out_jar=build/x.jar
python tools/cli.py edit  replacement_verify    # ★ 交互式调参（回车=默认 ✓）
python tools/cli.py panel                       # ★ 生成 HTML 参数面板
python tools/cli.py paths                       # 全部路径（单一来源 ✓）
python tools/cli.py doctor                      # 自检（注册表/路径/缓存 ✓）
```

设计 ✓：**路径来自 `rwlib.paths`**（非硬编码 ✓）· **规格自动导出**（零侵入 ✓）·
**面板由规格渲染**（加工具即出现 ✓）。
"""
import argparse
import json
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent
sys.path.insert(0, str(ROOT))

from registry import catalog, panel, runner          # noqa: E402


def cmd_paths(args):
    from rwlib.paths import P
    print('★ 路径单一来源（rwlib.paths.P ✓）—— 共 %d 项' % len(P.describe()))
    print()
    for k, v, ok in P.describe():
        print('   %s %-18s %s' % (ok, k, v))
    return 0


def cmd_doctor(args):
    ok = True
    from rwlib.paths import P
    print('★ 工具链自检')
    print()
    checks = [
        ('路径模块', (ROOT / 'rwlib' / 'paths.py').exists()),
        ('注册表 schema', (ROOT / 'registry' / 'schema.py').exists()),
        ('规格导出器', (ROOT / 'registry' / 'spec.py').exists()),
        ('目录扫描器', (ROOT / 'registry' / 'catalog.py').exists()),
        ('执行器', (ROOT / 'registry' / 'runner.py').exists()),
        ('面板', (ROOT / 'registry' / 'panel.py').exists()),
        ('池目录', P.pool_dir().exists()),
        ('交付物', P.deliverable_jar().exists()),
        ('游戏 jar', P.game_lib.exists()),
        ('03 源树', P.src03.exists()),
        ('映射库', P.supplement.exists()),
    ]
    for name, good in checks:
        print('   %s %s' % ('✓' if good else '✗', name))
        ok = ok and good
    specs = catalog.discover(refresh=True)
    s = catalog.summary(specs)
    print()
    print('   工具 %d 个 · 分类 %d · 带参数 %d · 写操作 %d'
          % (s['total'], len(s['categories']), s['with_params'], s['writers']))
    print('   池 = %d 个类 · 交付物 = %s'
          % (sum(1 for _ in P.pool_dir().rglob('*.class')) if P.pool_dir().exists() else 0,
             P.deliverable_jar().name if P.deliverable_jar().exists() else '缺 ✗'))
    print()
    print('   %s' % ('全部通过 ✓' if ok else '有问题 ✗'))
    return 0 if ok else 1


def cmd_panel(args):
    a = argparse.Namespace(refresh=args.refresh, out=args.out)
    return panel.cmd_html(a)


def main():
    ap = argparse.ArgumentParser(description='项目工具链统一入口（规格驱动 ✓）')
    sub = ap.add_subparsers(dest='cmd', required=True)
    sp = sub.add_parser('paths', help='列出全部路径（单一来源 ✓）')
    sp.set_defaults(fn=cmd_paths)
    sp = sub.add_parser('doctor', help='工具链自检')
    sp.set_defaults(fn=cmd_doctor)
    p = sub.add_parser('panel', help='生成 HTML 参数面板')
    p.add_argument('-o', '--out', default='')
    p.add_argument('--refresh', action='store_true')
    p.set_defaults(fn=cmd_panel)
    # 透传 list/show/edit/run 给 panel ✓（单一实现 ✓）
    for name in ('list', 'show', 'edit', 'run', 'json'):
        sp = sub.add_parser(name, help='（透传 registry.panel ✓）')
        sp.add_argument('rest', nargs=argparse.REMAINDER)
        sp.set_defaults(fn=None, passthrough=name)
    # 透传子命令：REMAINDER（rest ✓）+ 未识别参数（unknown，含以 `-` 开头者 ✓）
    args, unknown = ap.parse_known_args()
    if getattr(args, 'fn', None) is None:
        tail = list(getattr(args, 'rest', []) or []) + list(unknown)
        sys.argv = [sys.argv[0], args.passthrough] + tail
        return panel.main()
    return args.fn(args)


if __name__ == '__main__':
    sys.exit(main())
