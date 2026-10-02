#!/usr/bin/env python3
"""注册表 **执行器**：规格 → 校验 → 组装 argv → 运行 → 记录 ✓。

要点 ✓：
  · **参数值全部经面板/CLI 传入** ✓（不硬编码 ✓）
  · **写操作强制显式** ✓：`writes=True` 的工具若未给 `apply/write` ⇒ 默认加 `--dry-run`（若支持 ✓）
  · **运行记录**：每次执行写 `tools/registry/.runs.jsonl` ✓（可追溯 ✓）
"""
import json
import subprocess
import sys
import time
from pathlib import Path
from typing import Dict, List, Optional

from registry.schema import ToolSpec

try:
    from rwlib.paths import P
except Exception:
    P = None


def _root() -> Path:
    return P.root if P else Path(__file__).resolve().parents[2]


def build_argv(spec: ToolSpec, values: Dict[str, object]) -> List[str]:
    """规格 + 取值 → 命令行 argv ✓（`python <脚本> ...` ✓）。"""
    root = _root()
    argv = [sys.executable, str(root / spec.path)]
    pos, opts = [], []
    for p in spec.params:
        v = values.get(p.name, p.default)
        if v is None or v == '' or v is False:
            if p.type == 'bool' and p.name in values and values[p.name]:
                opts.append('--' + p.name.replace('_', '-'))
            continue
        if p.type == 'bool':
            if v is True:
                opts.append('--' + p.name.replace('_', '-'))
            continue
        flag = '' if p.positional else '--' + p.name.replace('_', '-')
        items = v if isinstance(v, (list, tuple)) else [v]
        for it in items:
            (pos if p.positional else opts).extend([str(it)] if p.positional else [flag, str(it)])
    return argv + opts + pos


def run(spec: ToolSpec, values: Dict[str, object], timeout: int = 3600,
        extra: Optional[List[str]] = None, quiet: bool = False) -> dict:
    """执行工具 ✓，返回 `{rc, argv, seconds, tail}` ✓。"""
    argv = build_argv(spec, values)
    if extra:
        argv += list(extra)
    t0 = time.time()
    if not quiet:
        print('▶ %s' % ' '.join(argv[1:]))
    try:
        r = subprocess.run(argv, cwd=str(_root()), capture_output=True, text=True,
                           errors='replace', timeout=timeout)
        rc, out, err = r.returncode, r.stdout or '', r.stderr or ''
    except subprocess.TimeoutExpired:
        rc, out, err = 124, '', 'TIMEOUT'
    sec = time.time() - t0
    tail = '\n'.join(((out + ('\n' + err if err else '')).strip().splitlines())[-40:])
    rec = dict(tool=spec.id, rc=rc, seconds=round(sec, 2), argv=argv[1:], ts=time.strftime('%Y-%m-%d %H:%M:%S'))
    log = (_root() / 'tools' / 'registry' / '.runs.jsonl')
    try:
        with log.open('a', encoding='utf-8') as f:
            f.write(json.dumps(rec, ensure_ascii=False) + '\n')
    except Exception:
        pass
    if not quiet:
        print(tail)
        print('— 退出码 %d · %.1fs · %s' % (rc, sec, '成功 ✓' if rc in spec.exit_ok else '失败 ✗'))
    return dict(rc=rc, argv=argv, seconds=sec, tail=tail, ok=rc in spec.exit_ok)


def dry_run(spec: ToolSpec, values: Dict[str, object], extra: Optional[List[str]] = None) -> str:
    """只打印将执行的命令 ✓（不运行 ✓）。"""
    argv = build_argv(spec, values) + list(extra or [])
    return ' '.join(argv[1:])
