#!/usr/bin/env python3
"""注册表 **目录**：自动发现全部工具 ✓ + 规格缓存 ✓ + 覆盖合并 ✓。

设计 ✓：
  · **零侵入** —— 扫描 `tools/**/*.py`，从 argparse 自动导出规格 ✓（脚本无需改动 ✓）
  · **可覆盖** —— `tools/registry/overrides/<tool>.json` 可改描述/分类/参数 ✓
  · **可缓存** —— 规格缓存到 `tools/registry/.catalog.json` ✓（带源码 mtime 指纹 ✓）
  · **非硬编码** —— 路径全部来自 `rwlib.paths` ✓
"""
import json
from pathlib import Path
from typing import Dict, List, Optional

from registry.schema import ToolSpec
from registry.spec import spec_from_source

try:
    from rwlib.paths import P
except Exception:                                    # 允许独立运行 ✓
    P = None


def _tools_root() -> Path:
    return P.tools if P else Path(__file__).resolve().parents[1]


def _overrides_dir() -> Path:
    return _tools_root() / 'registry' / 'overrides'


def _cache_file() -> Path:
    return _tools_root() / 'registry' / '.catalog.json'


SKIP_DIRS = {'_archive', 'registry', '__pycache__', 'workspace'}


def discover(use_cache: bool = True, refresh: bool = False) -> List[ToolSpec]:
    """扫描并返回全部工具规格 ✓。"""
    root = _tools_root()
    files = [p for p in root.rglob('*.py')
             if not any(s in p.parts for s in SKIP_DIRS) and p.name != '__init__.py']
    cache = {}
    if use_cache and not refresh and _cache_file().exists():
        try:
            cache = json.loads(_cache_file().read_text(encoding='utf-8'))
        except Exception:
            cache = {}
    specs: List[ToolSpec] = []
    new_cache: Dict[str, dict] = {}
    for p in sorted(files):
        rel = p.relative_to(root.parent).as_posix()      # 相对项目根 ✓
        key = rel
        mt = p.stat().st_mtime_ns
        ent = cache.get(key)
        if ent and ent.get('mtime') == mt:
            specs.append(ToolSpec.from_dict(ent['spec']))
            new_cache[key] = ent
            continue
        try:
            src = p.read_text(encoding='utf-8', errors='replace')
        except Exception:
            continue
        sp = spec_from_source(rel, src)
        specs.append(sp)
        new_cache[key] = {'mtime': mt, 'spec': sp.to_dict()}
    if use_cache:
        try:
            _cache_file().write_text(json.dumps(new_cache, ensure_ascii=False), encoding='utf-8')
        except Exception:
            pass
    # 合并覆盖 ✓
    ov = _overrides_dir()
    if ov.exists():
        by_id = {s.id: s for s in specs}
        for f in ov.glob('*.json'):
            try:
                d = json.loads(f.read_text(encoding='utf-8'))
            except Exception:
                continue
            tid = d.get('id') or f.stem
            base = by_id.get(tid)
            if base is None:
                continue
            if d.get('desc'):
                base.desc = d['desc']
            if d.get('category'):
                base.category = d['category']
            if d.get('tags'):
                base.tags = list(d['tags'])
            if d.get('writes') is not None:
                base.writes = bool(d['writes'])
            if d.get('params'):
                have = {x.name: x for x in base.params}
                for pd in d['params']:
                    from registry.schema import Param
                    np = Param.from_dict(pd)
                    have[np.name] = np
                base.params = list(have.values())
    return sorted(specs, key=lambda s: (s.category, s.id))


def find(tool_id: str, specs: Optional[List[ToolSpec]] = None) -> Optional[ToolSpec]:
    """按 id 或路径片段查找工具 ✓（唯一匹配才返回 ✓）。"""
    ss = specs if specs is not None else discover()
    exact = [s for s in ss if s.id == tool_id]
    if len(exact) == 1:
        return exact[0]
    part = [s for s in ss if tool_id in s.path]
    return part[0] if len(part) == 1 else None


def summary(specs: Optional[List[ToolSpec]] = None) -> dict:
    """分类统计 ✓。"""
    ss = specs if specs is not None else discover()
    out: Dict[str, int] = {}
    for s in ss:
        out[s.category] = out.get(s.category, 0) + 1
    return dict(total=len(ss), categories=dict(sorted(out.items(), key=lambda kv: -kv[1])),
                with_params=sum(1 for s in ss if s.params),
                writers=sum(1 for s in ss if s.writes))
