#!/usr/bin/env python3
"""工具链 **参数类型系统** —— 面板渲染与校验的元数据 ✓。

`Param.type` 决定**面板控件** ✓；`choices` / `default` / `required` 决定**校验** ✓。
新增类型只需在 `WIDGET` 登记 ✓（面板自动支持 ✓）。
"""
from dataclasses import dataclass, field
from typing import Any, List, Optional

# 类型 → 面板控件 ✓
WIDGET = {
    'bool': 'checkbox',
    'flag': 'checkbox',
    'int': 'number',
    'float': 'number',
    'str': 'text',
    'path': 'file-picker',
    'dir': 'dir-picker',
    'enum': 'select',
    'list': 'tags',
}


@dataclass
class Param:
    """单个工具参数 ✓。"""
    name: str                                  # 参数名（不含 -- ✓）
    type: str = 'str'
    default: Any = None
    help: str = ''
    choices: Optional[List[str]] = None
    required: bool = False
    positional: bool = False
    group: str = '选项'                        # 面板分组 ✓
    advanced: bool = False                     # 面板折叠区 ✓

    def widget(self) -> str:
        """面板控件类型 ✓。"""
        return 'select' if self.choices else WIDGET.get(self.type, 'text')

    def to_dict(self) -> dict:
        return dict(name=self.name, type=self.type, default=self.default, help=self.help,
                    choices=self.choices, required=self.required, positional=self.positional,
                    group=self.group, advanced=self.advanced, widget=self.widget())

    @staticmethod
    def from_dict(d: dict) -> 'Param':
        return Param(name=d.get('name', ''), type=d.get('type', 'str'), default=d.get('default'),
                     help=d.get('help', ''), choices=d.get('choices'),
                     required=bool(d.get('required')), positional=bool(d.get('positional')),
                     group=d.get('group', '选项'), advanced=bool(d.get('advanced')))


@dataclass
class ToolSpec:
    """工具规格 ✓ —— 面板 / 校验 / 执行**共用同一份声明** ✓。"""
    id: str
    path: str                                  # 相对项目根 ✓
    desc: str = ''
    category: str = 'other'
    writes: bool = False                       # 写文件者强制走 --apply ✓
    tags: List[str] = field(default_factory=list)
    params: List[Param] = field(default_factory=list)
    exit_ok: List[int] = field(default_factory=lambda: [0])

    def to_dict(self) -> dict:
        return dict(id=self.id, path=self.path, desc=self.desc, category=self.category,
                    writes=self.writes, tags=list(self.tags),
                    params=[p.to_dict() for p in self.params], exit_ok=list(self.exit_ok))

    @staticmethod
    def from_dict(d: dict) -> 'ToolSpec':
        return ToolSpec(id=d.get('id', ''), path=d.get('path', ''), desc=d.get('desc', ''),
                        category=d.get('category', 'other'), writes=bool(d.get('writes')),
                        tags=list(d.get('tags') or []),
                        params=[Param.from_dict(x) for x in (d.get('params') or [])],
                        exit_ok=list(d.get('exit_ok') or [0]))

    def groups(self) -> List[str]:
        """面板分组顺序 ✓。"""
        seen = []
        for p in self.params:
            if p.group not in seen:
                seen.append(p.group)
        return seen
