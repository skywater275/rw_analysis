#!/usr/bin/env python3
"""★ **全项目路径的单一来源**（非硬编码 ✓）。

任何脚本**只允许**从这里取路径 ✓ —— 禁止再写 `ROOT / "build" / ...` 之类字面量 ✗。
改变目录布局时**只改本文件** ✓（迁移成本从「345 个脚本」降到「1 个文件」✓）。

用法::

    from rwlib.paths import P          # 推荐：点号访问（IDE 可补全 ✓）
    print(P.pool, P.deliverable)

    from rwlib.paths import POOL_DIR   # 兼容：常量访问 ✓
"""
import os
from pathlib import Path

# ── 定位项目根 ✓（本文件位于 <root>/tools/rwlib/paths.py ✓）
_DEFAULT_ROOT = Path(__file__).resolve().parents[2]
ROOT = Path(os.environ.get('RW_REVERSE_ROOT', _DEFAULT_ROOT)).resolve()


class P:
    """路径命名空间 ✓（点号访问 ✓）。"""

    # ── 根与主干
    root = ROOT
    tools = ROOT / 'tools'
    docs = ROOT / 'docs'
    mappings = ROOT / 'mappings'
    game = ROOT / 'RustedWarfare'

    # ── 源码树
    classes01 = ROOT / '01-classes'
    cfr02 = ROOT / '02-decompiled'
    fern02b = ROOT / '02b-decompiled'
    src03 = ROOT / '03-deobfuscated'

    # ── 映射库
    supplement = mappings / 'supplement.csv'
    class_discoveries = mappings / 'class-discoveries.csv'
    domains = mappings / 'domains'
    generated = mappings / 'generated'

    # ── 游戏与编译
    game_lib = game / 'game-lib.jar'
    javac_out = ROOT / 'build-output'
    compile_errors = ROOT / 'compile-errors.csv'

    # ── ★ 工作区（原 `build/` ✓）：池 + 交付物 + 证据
    workspace = tools / 'workspace'
    legacy_build = ROOT / 'build'
    pool = workspace / 'reverse-classes'
    deliverable = workspace / 'game-lib-reverse.jar'
    baseline_jar = workspace / '_r104-fix.jar'
    ab_errors = workspace / '_ab-errors.txt'
    quarantine = workspace / '_quarantine'
    verify_json = workspace / '_verify-latest.json'
    reverse_src = workspace / 'reverse-src'

    @classmethod
    def pool_dir(cls) -> Path:
        """池目录**自动探测** ✓（优先新工作区 ✓，其次历史 `build/` ✓）。"""
        for p in (cls.pool, cls.legacy_build / 'reverse-classes'):
            if p.exists():
                return p
        return cls.pool

    @classmethod
    def deliverable_jar(cls) -> Path:
        """交付物**自动探测** ✓。"""
        for p in (cls.deliverable, cls.legacy_build / 'game-lib-reverse.jar'):
            if p.exists():
                return p
        return cls.deliverable

    @classmethod
    def describe(cls) -> list:
        """列出全部路径 ✓（供面板/文档生成 ✓）。"""
        out = []
        for k, v in sorted(vars(cls).items()):
            if k.startswith('_') or callable(v) or isinstance(v, classmethod):
                continue
            out.append((k, str(v), '✓' if Path(v).exists() else '✗'))
        return out


# 兼容常量 ✓
POOL_DIR = P.pool
DELIVERABLE = P.deliverable
WORKSPACE = P.workspace
