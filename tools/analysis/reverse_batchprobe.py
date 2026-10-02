#!/usr/bin/env python3
"""**秒级批量探针**：对多个 03 源跑 `reverse_members` 并保存/比对 ✓（RS-17 方法 ✓）。
用法: python tools/analysis/reverse_batchprobe.py save <tag> | diff <tagA> <tagB>
"""
import importlib.util
import json
import sys
from pathlib import Path

ROOT = Path('.').resolve()
sys.path.insert(0, str(ROOT / 'tools'))
spec = importlib.util.spec_from_file_location('brj', ROOT / 'tools' / 'fixers' / 'build_reverse_jar.py')
m = importlib.util.module_from_spec(spec)
try:
    spec.loader.exec_module(m)
except SystemExit:
    pass

# 掉出的 8 个类（可读名 → 03 源 ✓；a/a/a/i 无可读名 ⇒ 跳过 ✓）
TARGETS = [
    ('TypedObjectList', 'gameFramework/utility/s'),
    ('TypedObjectListIterator', 'gameFramework/utility/t'),
    ('TextureCache', 'gameFramework/af'),
    ('TextStream', 'gameFramework/j/ax'),
    ('TextureProxy', 'gameFramework/m/ad'),
    ('WeaponConfig', 'game/units/custom/as'),
    ('UnitTrait', 'game/units/custom/b/n'),
]
OUT = ROOT / 'build' / '_r109_probe'
T = ROOT / '03-deobfuscated'


def load_env():
    mmap, fmap = m.load_member_map()
    per_map = None
    try:
        rp = m.load_host_pkg_resolve(); al = m.readable_host_alias(m.load_mapping())
        per_map = m.load_member_map_by_class(rp, al); per_map, _ = m.apply_host_alias(per_map, al)
    except Exception as e:
        print('per_map 失败：%s' % e)
    return mmap, fmap, per_map


def host_of(rel):
    """混淆 rel（gameFramework/utility/s）→ host key (pkg, cls) ✓"""
    pkg, cls = rel.rsplit('/', 1)
    return ('com.corrodinggames.rts.' + pkg.replace('/', '.'), cls)


def main():
    mode = sys.argv[1] if len(sys.argv) > 1 else 'save'
    if mode == 'save':
        tag = sys.argv[2] if len(sys.argv) > 2 else 'base'
        mmap, fmap, per_map = load_env()
        res = {}
        for rd, rel in TARGETS:
            hits = list(T.rglob(rd + '.java'))
            if len(hits) != 1:
                print('   %-26s 03 源 %d 个 ⇒ 跳过' % (rd, len(hits))); continue
            src = hits[0].read_text(encoding='utf-8', errors='replace')
            out = m.reverse_members(src, mmap, fmap, (), per_map, host_of(rel), None,
                                    gt_members=None, gt_names=None)
            res[rd] = out
            print('   %-26s 已反向（%d 行）' % (rd, out.count('\n') + 1))
        OUT.mkdir(parents=True, exist_ok=True)
        (OUT / (tag + '.json')).write_text(json.dumps(res, ensure_ascii=False), encoding='utf-8')
        print('✔ 保存 → %s' % (OUT / (tag + '.json')).relative_to(ROOT).as_posix())
        return 0
    # diff
    a = json.loads((OUT / (sys.argv[2] + '.json')).read_text(encoding='utf-8'))
    b = json.loads((OUT / (sys.argv[3] + '.json')).read_text(encoding='utf-8'))
    for rd in a:
        if rd not in b:
            continue
        la, lb = a[rd].splitlines(), b[rd].splitlines()
        diffs = [(i, x, y) for i, (x, y) in enumerate(zip(la, lb), 1) if x != y]
        print('=== %s：差异 %d 行 ===' % (rd, len(diffs) + abs(len(la) - len(lb))))
        for i, x, y in diffs[:6]:
            print('   %4d| 干净: %s' % (i, x.strip()[:84]))
            print('       | 守卫: %s' % y.strip()[:84])
    return 0


if __name__ == '__main__':
    sys.exit(main())
