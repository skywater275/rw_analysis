#!/usr/bin/env python3
"""★ **从脚本的 argparse 自动导出工具规格** —— 让现有 491 个工具**零改动**即可面板化 ✓。

原理：**静态解析**源码里的 `add_argument(...)`（不执行脚本 ✓，无副作用 ✓）。
覆盖不到的部分，用 `tools/registry/overrides/<tool>.json` **覆盖/补充** ✓。
"""
import ast
import re
from pathlib import Path
from typing import List, Optional

from registry.schema import Param, ToolSpec

_ACTION_TYPE = {
    'store_true': ('bool', False),
    'store_false': ('bool', True),
    'count': ('int', 0),
    'append': ('list', None),
}


def _lit(node) -> object:
    """AST 字面量 → Python 值 ✓（取不到返回 None ✓）。"""
    try:
        return ast.literal_eval(node)
    except Exception:
        return None


def parse_argparse(src: str) -> List[Param]:
    """静态提取 `add_argument` ✓。"""
    params: List[Param] = []
    try:
        tree = ast.parse(src)
    except SyntaxError:
        return params
    for node in ast.walk(tree):
        if not (isinstance(node, ast.Call) and isinstance(node.func, ast.Attribute)
                and node.func.attr == 'add_argument'):
            continue
        flags, kw = [], {}
        for a in node.args:
            v = _lit(a)
            if isinstance(v, str):
                flags.append(v)
        for k in node.keywords:
            if k.arg:
                kw[k.arg] = _lit(k.value)
        if not flags:
            continue
        positional = not flags[0].startswith('-')
        name = (flags[0] if positional else flags[0].lstrip('-')).replace('-', '_')
        action = kw.get('action') or 'store'
        typ, dflt = _ACTION_TYPE.get(action, ('str', kw.get('default')))
        if action in ('store_true', 'store_false'):
            typ = 'bool'
        elif kw.get('type') == 'int':
            typ = 'int'
        elif kw.get('type') == 'float':
            typ = 'float'
        hel = kw.get('help') or ''
        if isinstance(hel, str):
            hel = re.sub(r'\s+', ' ', hel).strip()[:200]
        ch = kw.get('choices')
        group = '位置参数' if positional else (
            '写操作' if name in ('apply', 'write', 'out', 'out_jar') else
            ('显示' if name in ('verbose', 'quiet', 'json', 'debug') else '选项'))
        params.append(Param(
            name=name,
            type='enum' if isinstance(ch, (list, tuple)) else typ,
            default=(dflt if action in _ACTION_TYPE else kw.get('default')),
            help=hel or '（无说明）',
            choices=[str(x) for x in ch] if isinstance(ch, (list, tuple)) else None,
            required=bool(kw.get('required')) or positional,
            positional=positional,
            group=group,
            advanced=name in ('verbose', 'quiet', 'debug'),
        ))
    seen, out = set(), []
    for p in params:
        if p.name in seen:
            continue
        seen.add(p.name)
        out.append(p)
    return out


def spec_from_source(rel: str, src: str, desc: str = '') -> ToolSpec:
    """从脚本源码构建规格 ✓。"""
    parts = rel.split('/')
    cat = parts[1] if len(parts) >= 3 and parts[0] == 'tools' else 'other'
    writes = bool(re.search(r"add_argument\(\s*['\"]--(apply|write)['\"]", src))
    doc = ''
    m = re.match(r"\s*(?:\"\"\"|''')(.*?)(?:\"\"\"|''')", src, re.S)
    if m:
        doc = re.sub(r'\s+', ' ', m.group(1)).strip()[:240]
    return ToolSpec(id=Path(rel).stem, path=rel, desc=(desc or doc), category=cat,
                    writes=writes, params=parse_argparse(src))
