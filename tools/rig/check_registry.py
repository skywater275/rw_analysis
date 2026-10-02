#!/usr/bin/env python3
"""工具注册表健康度自检（PENDING **RS-11** 的验收判据）。

Usage:
    python tools/rig/check_registry.py [--json build/_registry.json]

背景
----
`tools/manager.py` 的 `TOOLS` 注册表长期是**手工维护**的：2026-10-01 实测 **617 条中 553 条
（89.6%）的脚本早已不存在** ⇒ 依赖它的 `list` / `check` / `status` 输出不可信。

第 15 轮起 `manager.py` 在模块级调用 `_reconcile_registry()` 做**文件系统对账**
（丢死条目 + 自动登记漏登项）。本工具把对账结果量化，作为 RS-11 的判据：

| 指标 | 判据 |
|---|---|
| 注册条目**缺失率** | 必须 **0%**（对账后不可能有死条目）|
| 覆盖率（注册 / 磁盘 `.py`） | 必须 **100%**（自动登记保证）|
| 本次对账丢弃 / 新增 | 信息项（首轮应丢弃 ~553、新增一批）|

退出码：0 = 缺失率 0 且覆盖率 100%；1 = 否则。
"""
import argparse
import json
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT / 'tools'))
sys.path.insert(0, str(ROOT))


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--json', default='build/_registry.json')
    args = ap.parse_args()

    import manager  # noqa: E402 — 导入即执行模块级对账
    tools = manager.TOOLS
    audit = getattr(manager, 'REGISTRY_AUDIT', {})

    missing = [(n, v.get('path')) for n, v in tools.items()
               if v.get('path') and not (ROOT / v['path']).exists()]
    disk = {p.relative_to(ROOT).as_posix()
            for p in (ROOT / 'tools').rglob('*.py') if '__pycache__' not in p.parts}
    covered = {v.get('path') for v in tools.values()} & disk
    rate = 100.0 * len(missing) / max(len(tools), 1)
    cov = 100.0 * len(covered) / max(len(disk), 1)

    print('注册表对账（tools/manager.py::TOOLS）')
    print('  注册条目            = %d' % len(tools))
    print('  其中 path 不存在    = %d（**缺失率 %.1f%%**，判据 0%%）' % (len(missing), rate))
    print('  磁盘 tools/**/*.py  = %d · 已登记 = %d（**覆盖率 %.1f%%**，判据 100%%）'
          % (len(disk), len(covered), cov))
    print('  本次对账：丢弃 %d 条死条目 · 自动登记 %d 条'
          % (len(audit.get('dropped', [])), len(audit.get('added', []))))
    for n, p in missing[:5]:
        print('     [MISS] %s → %s' % (n, p))
    ok = not missing and cov >= 99.9
    print('结论: %s' % ('✅ 注册表与文件系统一致' if ok else '❌ 仍有死条目或覆盖不足'))

    (ROOT / args.json).write_text(json.dumps({
        '注册条目': len(tools), '缺失': missing, '缺失率': round(rate, 2),
        '磁盘脚本': len(disk), '覆盖率': round(cov, 2),
        '丢弃': audit.get('dropped', []), '自动登记': audit.get('added', []),
    }, ensure_ascii=False, indent=2), encoding='utf-8')
    print('→ %s' % (ROOT / args.json))
    return 0 if ok else 1


if __name__ == '__main__':
    sys.exit(main())
