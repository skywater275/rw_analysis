#!/usr/bin/env python3
"""重建 `fix_android_innerclasses.py` —— 修补 android.jar 的 InnerClasses 污染。

★ 本文件是**重建品**：`tools/gates/javac_gate.py::ensure_patched_classes()` 会调用它，
  但它在工作区整理中丢失（门禁日志长期报
  `WARN: fix_android_innerclasses 失败: [Errno 2] No such file or directory`）。

契约（由 javac_gate 调用方固定）：
    python tools/fixers/fix_android_innerclasses.py \
        --only-jar android.jar \
        --only-class android/graphics/BitmapFactory$Options.class

根因（javac_gate.py L134-141 原文）：
    android.jar Stub 版 `BitmapFactory$Options.class` 的 InnerClasses 属性含
    `Config = Bitmap$Config of Bitmap` 条目（由 Options.inBitmap 字段类型导致的
    内部类表合并）。javac 加载它时 `readInnerClasses` 中 `c != outer` ⇒ 创建
    flags=0（package-private）的嵌套符号并**污染全局 flatname 表**
    ⇒ 之后所有 `import android.graphics.Bitmap$Config` 报
       「Config is not public in Bitmap」。

修法：
    只改 `InnerClasses` 属性 —— 删掉那条「inner 属于本类之外的 outer」的条目，
    并把 `number_of_classes` 减 1。常量池与其他属性**逐字节不动** ⇒ 所有 CP 下标仍然有效。
    补丁类写到 `cache/patched-classes/<原路径>`，门禁把它放在 classpath 最前。

用法:
    python tools/fixers/fix_android_innerclasses.py --only-jar android.jar \
        --only-class 'android/graphics/BitmapFactory$Options.class' [--apply]
"""
import argparse
import struct
import sys
import zipfile
from pathlib import Path

sys.stdout.reconfigure(encoding='utf-8')
ROOT = Path(__file__).resolve().parents[2]
LIBS = ROOT / 'RustedWarfare' / 'libs'
PATCHED = ROOT / 'cache' / 'patched-classes'


class Reader:
    def __init__(self, data):
        self.d = data
        self.i = 0

    def u1(self):
        v = self.d[self.i]
        self.i += 1
        return v

    def u2(self):
        v = struct.unpack_from('>H', self.d, self.i)[0]
        self.i += 2
        return v

    def u4(self):
        v = struct.unpack_from('>I', self.d, self.i)[0]
        self.i += 4
        return v

    def take(self, n):
        v = self.d[self.i:self.i + n]
        self.i += n
        return v


def parse_cp(data):
    """→ (constant_pool, offset_after_cp)。cp[i] = ('U', utf8) / ('C', name_idx) / (tag,)"""
    r = Reader(data)
    r.i = 8
    cnt = r.u2()
    cp = [None] * cnt
    i = 1
    while i < cnt:
        tag = r.u1()
        if tag == 1:
            ln = r.u2()
            cp[i] = ('U', r.take(ln).decode('utf-8', 'replace'))
        elif tag == 7:
            cp[i] = ('C', r.u2())
        elif tag in (8, 16, 19, 20):
            cp[i] = (tag, r.u2())
        elif tag in (9, 10, 11, 12, 17, 18):
            cp[i] = (tag, r.u2(), r.u2())
        elif tag in (3, 4):
            cp[i] = (tag, r.u4())
        elif tag in (5, 6):
            cp[i] = (tag, r.u4(), r.u4())
            i += 1
        elif tag == 15:
            cp[i] = (tag, r.u1(), r.u2())
        else:
            raise ValueError('未知常量池 tag %d @%d' % (tag, r.i))
        i += 1
    return cp, r.i


def utf8(cp, idx):
    if not idx or idx >= len(cp) or cp[idx] is None:
        return None
    v = cp[idx]
    return v[1] if v[0] == 'U' else None


def class_name(cp, idx):
    if not idx or idx >= len(cp) or cp[idx] is None:
        return None
    v = cp[idx]
    if v[0] == 'C':
        return utf8(cp, v[1])
    return None


def patch(data, verbose=True):
    """删除 InnerClasses 中「inner 的 outer 不是本类」的条目。→ (新字节, 删除数)"""
    cp, off = parse_cp(data)
    this_idx = struct.unpack_from('>H', data, off + 2)[0]
    this_name = class_name(cp, this_idx)
    # 跳到 attributes
    p = off + 6
    ni = struct.unpack_from('>H', data, p)[0]
    p += 2 + 2 * ni
    nfields = struct.unpack_from('>H', data, p)[0]
    p += 2

    def skip_members(p, n):
        for _ in range(n):
            p += 6
            na = struct.unpack_from('>H', data, p)[0]
            p += 2
            for _ in range(na):
                _n = struct.unpack_from('>H', data, p)[0]
                ln = struct.unpack_from('>I', data, p + 2)[0]
                p += 6 + ln
        return p

    p = skip_members(p, nfields)
    nmethods = struct.unpack_from('>H', data, p)[0]
    p += 2
    p = skip_members(p, nmethods)
    nattrs = struct.unpack_from('>H', data, p)[0]
    p += 2

    removed = 0
    pieces = [data[:p]]
    for _ in range(nattrs):
        aoff = p
        aname_idx = struct.unpack_from('>H', data, p)[0]
        alen = struct.unpack_from('>I', data, p + 2)[0]
        p += 6
        body = data[p:p + alen]
        p += alen
        if utf8(cp, aname_idx) == 'InnerClasses' and alen >= 2:
            n = struct.unpack_from('>H', body, 0)[0]
            kept = []
            for k in range(n):
                e = body[2 + 8 * k:2 + 8 * (k + 1)]
                ic, oc, inm, fl = struct.unpack('>HHHH', e)
                inner = class_name(cp, ic)
                outer = class_name(cp, oc)
                # ★ 污染判据：该 InnerClasses 条目的**两端都不涉及本类**。
                #   实测 BitmapFactory$Options.class 含
                #       inner=Bitmap$Config  outer=Bitmap          ← 本类既非 inner 也非 outer ⇒ 污染
                #       inner=BitmapFactory$Options outer=BitmapFactory  ← inner == 本类 ⇒ 正常
                #   先前误用「inner 前缀与 outer 不符」判据 —— 对 `Bitmap$Config` of `Bitmap`
                #   前缀恰好相符 ⇒ 漏判，修补 0 条（实测踩过）。
                involved = (inner == this_name) or (outer == this_name)
                if inner is not None and outer is not None and not involved:
                    removed += 1
                    if verbose:
                        print('   删除条目: inner=%s outer=%s flags=%d' % (inner, outer, fl))
                else:
                    kept.append(e)
            nb = struct.pack('>H', len(kept)) + b''.join(kept)
            pieces.append(struct.pack('>HI', aname_idx, len(nb)) + nb)
        else:
            pieces.append(data[aoff:p])
    return b''.join(pieces), removed


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--only-jar', default='android.jar')
    ap.add_argument('--only-class', default='')
    ap.add_argument('--apply', action='store_true', default=True)
    a = ap.parse_args()

    jar = LIBS / a.only_jar
    if not jar.exists():
        print('✘ 找不到 %s' % jar)
        return 1
    with zipfile.ZipFile(jar) as z:
        names = [i.filename for i in z.infolist() if i.filename.endswith('.class')]
        targets = [a.only_class] if a.only_class else names
        n_ok = n_rm = 0
        for nm in targets:
            if nm not in names:
                print('  [跳过] %s 不在 %s' % (nm, a.only_jar))
                continue
            data = z.read(nm)
            try:
                new, removed = patch(data, verbose=False)
            except Exception as e:
                print('  [失败] %s : %s' % (nm, e))
                continue
            if removed:
                dst = PATCHED / nm
                dst.parent.mkdir(parents=True, exist_ok=True)
                dst.write_bytes(new)
                n_ok += 1
                n_rm += removed
                print('  [修补] %-58s 删除 %d 条 InnerClasses' % (nm, removed))
        print()
        print('修补 %d 个类，共删 %d 条污染条目 → %s' % (n_ok, n_rm, PATCHED))
    return 0


if __name__ == '__main__':
    sys.exit(main())
