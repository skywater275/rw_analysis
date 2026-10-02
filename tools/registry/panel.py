#!/usr/bin/env python3
"""★ 注册表 **参数面板** —— 三种形态 ✓：

| 形态 | 入口 | 用途 |
|---|---|---|
| **列表** | `panel.py list [--category X]` | 看全部工具与参数 |
| **交互** | `panel.py edit <tool>` | 逐项调参（回车取默认 ✓）→ 预览命令 → 运行 ✓ |
| **HTML** | `panel.py html [-o out.html]` | 生成**图形面板**（下拉/开关/数字框 ✓），可离线浏览与复制命令 ✓ |
| **JSON** | `panel.py json` | 供外部 UI 消费 ✓ |

★ 面板内容**完全由规格渲染** ✓ —— 新增工具/参数**自动出现** ✓（无需改面板 ✓）。
"""
import argparse
import html
import json
import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parents[1]))

from registry import catalog, runner                     # noqa: E402
from registry.schema import ToolSpec                     # noqa: E402


def _fmt_default(p) -> str:
    if p.default is None:
        return ''
    if isinstance(p.default, bool):
        return '是' if p.default else '否'
    return str(p.default)


def cmd_list(args):
    specs = catalog.discover(refresh=args.refresh)
    if args.category:
        specs = [s for s in specs if s.category == args.category]
    if args.grep:
        specs = [s for s in specs if args.grep in s.id or args.grep in s.path]
    print('★ 工具目录：%d 个 ✓（分类：%s）' % (len(specs), catalog.summary(specs)['categories']))
    print()
    cur = None
    for s in specs:
        if s.category != cur:
            cur = s.category
            print('── %s ──' % cur)
        mark = '✎' if s.writes else ' '
        print('   %s %-34s %2d 参  %s' % (mark, s.id, len(s.params), s.desc[:58]))
    print()
    print('   ✎ = 写操作（需 --apply ✓）· 用 `panel.py edit <id>` 调参 ✓')


def cmd_show(args):
    s = catalog.find(args.tool)
    if not s:
        print('未找到工具：%s ✗' % args.tool); return 1
    print('★ %s（%s）' % (s.id, s.category))
    print('   路径：%s' % s.path)
    print('   写操作：%s · 退出码：%s' % ('是' if s.writes else '否', s.exit_ok))
    print('   说明：%s' % s.desc)
    print()
    for g in s.groups():
        print('── %s ──' % g)
        for p in [x for x in s.params if x.group == g]:
            ch = ('  可选：%s' % ','.join(p.choices)) if p.choices else ''
            print('   --%-22s %-9s 默认=%-12s %s%s' % (p.name, p.widget(), _fmt_default(p), p.help[:52], ch))
    return 0


def cmd_run(args):
    s = catalog.find(args.tool)
    if not s:
        print('未找到工具：%s ✗' % args.tool); return 1
    values = {}
    for kv in (args.set or []):
        if '=' not in kv:
            continue
        k, v = kv.split('=', 1)
        p = next((x for x in s.params if x.name == k), None)
        if p and p.type in ('bool', 'flag'):
            values[k] = v.lower() in ('1', 'true', 'yes', 'on')
        elif p and p.type in ('int', 'float'):
            values[k] = float(v) if p.type == 'float' else int(v)
        else:
            values[k] = v
    print('▶ %s' % runner.dry_run(s, values))
    if args.dry_run:
        return 0
    r = runner.run(s, values, timeout=args.timeout)
    return 0 if r['ok'] else 1


def cmd_edit(args):
    """交互式调参 ✓（回车=默认 ✓；`r`=运行 ✓；`q`=退出 ✓）。"""
    s = catalog.find(args.tool)
    if not s:
        print('未找到工具：%s ✗' % args.tool); return 1
    values = {}
    print('★ 调参：%s（回车取默认 ✓，直接输入 r 运行 ✓）' % s.id)
    for g in s.groups():
        print('── %s ──' % g)
        for p in [x for x in s.params if x.group == g]:
            if p.advanced and not args.advanced:
                continue
            tip = '[%s]' % _fmt_default(p) if _fmt_default(p) else ''
            ch = ' (%s)' % '/'.join(p.choices) if p.choices else ''
            try:
                v = input('   %-22s %s%s %s: ' % (p.name, p.widget(), ch, tip)).strip()
            except EOFError:
                break
            if v == 'r':
                break
            if v == '':
                continue
            if p.type in ('bool', 'flag'):
                values[p.name] = v.lower() in ('1', 'true', 'yes', 'y', 'on')
            elif p.type in ('int', 'float'):
                try:
                    values[p.name] = float(v) if p.type == 'float' else int(v)
                except ValueError:
                    print('     !! 需数字 ✗')
            elif p.type == 'list':
                values[p.name] = [x.strip() for x in v.split(',') if x.strip()]
            else:
                values[p.name] = v
    print()
    print('▶ 将执行：%s' % runner.dry_run(s, values))
    try:
        if input('   回车执行 ✓ / n 取消：').strip().lower() in ('', 'y', 'yes'):
            r = runner.run(s, values, timeout=args.timeout)
            return 0 if r['ok'] else 1
    except EOFError:
        pass
    return 0


def cmd_json(args):
    specs = catalog.discover(refresh=args.refresh)
    print(json.dumps([x.to_dict() for x in specs], ensure_ascii=False, indent=2))
    return 0


_CSS = """body{font:14px/1.6 -apple-system,'Segoe UI','Microsoft YaHei',sans-serif;margin:0;background:#f6f7f9;color:#1f2328}
header{background:#24292f;color:#fff;padding:14px 22px}header h1{margin:0;font-size:17px}
header .sub{opacity:.75;font-size:12px;margin-top:4px}
main{padding:18px 22px;max-width:1180px}.cat{margin:18px 0 8px;font-weight:600;color:#57606a;border-bottom:2px solid #d0d7de;padding-bottom:4px}
.tool{background:#fff;border:1px solid #d0d7de;border-radius:8px;margin:8px 0;overflow:hidden}
.tool>summary{cursor:pointer;padding:10px 14px;font-weight:600;display:flex;gap:10px;align-items:center}
.tool>summary:hover{background:#f6f8fa}.badge{font-size:11px;padding:1px 7px;border-radius:10px;background:#ddf4ff;color:#0969da}
.badge.w{background:#fff1e5;color:#bc4c00}.body{padding:0 14px 12px}
.desc{color:#57606a;font-size:12px;margin:6px 0 10px;font-family:ui-monospace,Consolas,monospace}
table{border-collapse:collapse;width:100%}th,td{border:1px solid #d0d7de;padding:5px 8px;text-align:left;font-size:13px}
th{background:#f6f8fa;font-weight:600}input[type=text],input[type=number],select{width:190px;padding:3px 6px;border:1px solid #d0d7de;border-radius:5px}
input[type=checkbox]{transform:scale(1.15)}code{background:#f6f8fa;padding:2px 5px;border-radius:4px;font-family:ui-monospace,Consolas,monospace}
.cmd{margin-top:8px;background:#0d1117;color:#c9d1d9;padding:8px 10px;border-radius:6px;font-family:ui-monospace,Consolas,monospace;font-size:12px;overflow-x:auto}
.hint{color:#57606a;font-size:12px}"""

_JS = """function upd(btn){var box=btn.closest('.body');var parts=[];
box.querySelectorAll('[data-arg]').forEach(function(el){var n=el.dataset.arg,t=el.dataset.type,v;
if(el.type==='checkbox'){v=el.checked;if(v)parts.push('--'+n);return;}
v=el.value;if(v==='')return;(el.dataset.pos==='1')?parts.unshift(v):parts.push('--'+n,v);});
box.querySelector('.cmd').textContent='python '+box.dataset.path+' '+parts.join(' ');}"""


def cmd_html(args):
    specs = catalog.discover(refresh=args.refresh)
    s0 = catalog.summary(specs)
    out = ['<!doctype html><html lang="zh-CN"><head><meta charset="utf-8">',
           '<title>Rusted Warfare 工具链 · 参数面板</title><style>%s</style></head><body>' % _CSS,
           '<header><h1>Rusted Warfare 工具链 · 参数面板</h1>',
           '<div class="sub">共 <b>%d</b> 个工具 · 分类 <b>%d</b> · 带参数 <b>%d</b> · 写操作 <b>%d</b>'
           ' · 由 <code>tools/registry</code> 依据规格自动渲染 ✓</div></header><main>'
           % (s0['total'], len(s0['categories']), s0['with_params'], s0['writers'])]
    cur = None
    for s in specs:
        if s.category != cur:
            cur = s.category
            out.append('<div class="cat">%s（%d）</div>'
                       % (html.escape(cur), sum(1 for x in specs if x.category == cur)))
        badges = ('<span class="badge w">写操作</span>' if s.writes else '') + \
                 '<span class="badge">%d 参数</span>' % len(s.params)
        out.append('<details class="tool"><summary>%s %s</summary>'
                   % (html.escape(s.id), badges))
        out.append('<div class="body" data-path="%s">' % html.escape(s.path))
        out.append('<div class="desc">%s</div>' % html.escape(s.path))
        out.append('<div class="hint">%s</div>' % html.escape(s.desc[:300]))
        if s.params:
            out.append('<table><tr><th>参数</th><th>控件</th><th>默认</th><th>说明</th><th>值</th></tr>')
            for p in s.params:
                if p.type in ('bool', 'flag'):
                    ctl = '<input type="checkbox" data-arg="%s" data-type="bool"%s onchange="upd(this)">' % (
                        p.name, ' checked' if p.default else '')
                elif p.choices:
                    opts = ''.join('<option%s>%s</option>' % (' selected' if x == str(p.default) else '', html.escape(str(x)))
                                   for x in p.choices)
                    ctl = '<select data-arg="%s" data-type="enum" onchange="upd(this)"><option value=""></option>%s</select>' % (p.name, opts)
                elif p.type in ('int', 'float'):
                    ctl = '<input type="number" data-arg="%s" data-type="%s" value="%s" oninput="upd(this)">' % (
                        p.name, p.type, '' if p.default is None else p.default)
                else:
                    ctl = '<input type="text" data-arg="%s" data-type="str" data-pos="%d" value="%s" oninput="upd(this)">' % (
                        p.name, 1 if p.positional else 0, html.escape(str(p.default)) if p.default else '')
                out.append('<tr><td><code>--%s</code></td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>'
                           % (p.name, p.widget(), html.escape(_fmt_default(p)), html.escape(p.help[:120]), ctl))
            out.append('</table>')
        else:
            out.append('<div class="hint">（无参数 ✓ 直接运行即可）</div>')
        out.append('<div class="cmd">python %s</div>' % html.escape(s.path))
        out.append('</div></details>')
    out.append('</main><script>%s</script></body></html>' % _JS)
    dest = Path(args.out) if args.out else (Path(__file__).resolve().parents[2] / 'docs' / 'generated' / 'tool-panel.html')
    dest.parent.mkdir(parents=True, exist_ok=True)
    dest.write_text('\n'.join(out), encoding='utf-8')
    print('✔ 已生成面板：%s（%.1f KB ✓）' % (dest, dest.stat().st_size / 1024))
    return 0


def main():
    ap = argparse.ArgumentParser(description='工具链参数面板（规格驱动 ✓）')
    sub = ap.add_subparsers(dest='cmd', required=True)
    for name, fn, hel in (('list', cmd_list, '列出工具'), ('show', cmd_show, '看某工具的参数'),
                          ('edit', cmd_edit, '交互式调参并运行'), ('run', cmd_run, '按 --set 直接运行'),
                          ('json', cmd_json, '导出规格 JSON'), ('html', cmd_html, '生成 HTML 面板')):
        p = sub.add_parser(name, help=hel)
        p.set_defaults(fn=fn)
        if name in ('list', 'json', 'html'):
            p.add_argument('--refresh', action='store_true', help='忽略缓存重建规格')
        if name == 'list':
            p.add_argument('--category', default='', help='只列某分类')
            p.add_argument('--grep', default='', help='按 id/路径过滤')
        if name in ('show', 'edit', 'run'):
            p.add_argument('tool', help='工具 id 或路径片段')
        if name == 'edit':
            p.add_argument('--advanced', action='store_true', help='含高级参数')
            p.add_argument('--timeout', type=int, default=3600)
        if name == 'run':
            p.add_argument('--set', action='append', help='参数赋值 k=v（可多次）')
            p.add_argument('--dry-run', action='store_true', help='只打印命令')
            p.add_argument('--timeout', type=int, default=3600)
        if name == 'html':
            p.add_argument('-o', '--out', default='', help='输出 HTML 路径')
    args = ap.parse_args()
    return args.fn(args)


if __name__ == '__main__':
    sys.exit(main())
