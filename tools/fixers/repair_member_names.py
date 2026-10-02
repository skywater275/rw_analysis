#!/usr/bin/env python3
"""repair_member_names.py — 把「我们产出的类」的**声明成员名**按描述符对齐回原版 jar 的名字。

为什么需要
----------
反向器只能按 `supplement.csv` 的「语义名 → 混淆名」改名，而当某类里**多个混淆成员共用
一个语义名**时（实测 `gameFramework.e.a`：`isEnabled` 对应 b/f/g/i 四个方法、`getString`
对应 d/e/h），语义名不唯一 → 映射无法落地 → 声明侧保留可读名（`reset`/`getString2`/
`isEnabled3`）→ 原版调用方按混淆名调用 → **运行时 NoSuchMethodError**（实测启动即崩：
`NoSuchMethodError: java.lang.String gameFramework.e.a.e()`）。
实测 759 个替换类里有 **117 个**存在「成员集与原版不一致」（丢失成员）→ 全部是同类风险。

做法（零猜测，按描述符唯一配对）
--------------------------------
对每个产物类 C（其 FQN 在原版 jar 里也存在）：
  ① 解析双方 class 文件的**声明成员集** {(名, 描述符)}；
  ② 对「我们有、原版没有」的 (n, d)：若「我们的 d 只对应一个成员」且「原版的 (d) 也只
     对应一个未被占用的成员」→ 唯一配对，把 n 改名成原版名；
  ③ 改写：成员表 name_index + 本类对自身成员的 Methodref/Fieldref（新建 NameAndType，
     避免污染指向别的类的同名引用）。常量池不足时**追加**条目（索引只增不改，安全）。
配对不唯一的一律不动（宁可不改，也不猜）。

用法:
  python tools/fixers/repair_member_names.py                      # dry-run（只报告）
  python tools/fixers/repair_member_names.py --apply              # 就地改 build/reverse-classes
  python tools/fixers/repair_member_names.py --classes-dir DIR --stock-jar JAR
"""
import argparse
import struct
import sys
import zipfile
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT / 'tools'))
from rwlib.config import GAME_LIB  # noqa: E402

TAGS = {1: 'Utf8', 3: 'Integer', 4: 'Float', 5: 'Long', 6: 'Double', 7: 'Class',
        8: 'String', 9: 'Fieldref', 10: 'Methodref', 11: 'InterfaceMethodref',
        12: 'NameAndType', 15: 'MethodHandle', 16: 'MethodType', 17: 'Dynamic',
        18: 'InvokeDynamic', 19: 'Module', 20: 'Package'}


class ClassFile:
    """极简 class 文件读写（只做到：解析常量池/成员表、改成员名与自身引用）。"""

    def __init__(self, data):
        self.raw = bytearray(data)
        self.cp = []
        self._read_cp()
        self.tail_start = self._pos
        self._read_structure()

    # ── 解析 ──────────────────────────────────────────────
    def _read_cp(self):
        d = self.raw
        count = struct.unpack_from('>H', d, 8)[0]
        pos = 10
        cp = [None] * count
        i = 1
        while i < count:
            tag = d[pos]
            pos += 1
            if tag == 1:
                ln = struct.unpack_from('>H', d, pos)[0]
                pos += 2
                cp[i] = [1, bytes(d[pos:pos + ln])]
                pos += ln
            elif tag in (7, 8, 16, 19, 20):
                cp[i] = [tag, struct.unpack_from('>H', d, pos)[0]]
                pos += 2
            elif tag in (3, 4, 9, 10, 11, 12, 17, 18):
                cp[i] = [tag] + list(struct.unpack_from('>HH', d, pos))
                pos += 4
            elif tag in (5, 6):
                cp[i] = [tag] + list(struct.unpack_from('>II', d, pos))
                pos += 8
                i += 1
            elif tag == 15:
                cp[i] = [15] + list(struct.unpack_from('>BH', d, pos))
                pos += 3
            else:
                raise ValueError('未知常量池 tag %d' % tag)
            i += 1
        self.cp = cp
        self.cp_count = count
        self._pos = pos

    @property
    def utf8(self):
        return {bytes(e[1]).decode('utf-8', 'replace'): i
                for i, e in enumerate(self.cp) if e and e[0] == 1}

    def u(self, idx):
        return bytes(self.cp[idx][1]).decode('utf-8', 'replace')

    def _read_structure(self):
        d = self.raw
        pos = self._pos
        pos += 2                                   # access
        self.this_idx = struct.unpack_from('>H', d, pos)[0]; pos += 2
        pos += 2                                   # super
        n = struct.unpack_from('>H', d, pos)[0]; pos += 2 + 2 * n
        self.members = {'field': [], 'method': []}
        for kind in ('field', 'method'):
            cnt = struct.unpack_from('>H', d, pos)[0]; pos += 2
            for _ in range(cnt):
                acc, name_i, desc_i, acnt = struct.unpack_from('>HHHH', d, pos)
                self.members[kind].append([pos, name_i, desc_i])
                pos += 8
                for _k in range(acnt):
                    ln = struct.unpack_from('>I', d, pos + 2)[0]
                    pos += 6 + ln
        self.this_name = self.u(self.cp[self.this_idx][1])

    def declared(self):
        out = {}
        for kind in ('field', 'method'):
            s = set()
            for _off, name_i, desc_i in self.members[kind]:
                s.add((self.u(name_i), self.u(desc_i)))
            out[kind] = s
        return out

    # ── 改写 ──────────────────────────────────────────────
    def _ensure_utf8(self, s):
        m = self.utf8
        if s in m:
            return m[s]
        self.cp.append([1, s.encode('utf-8')])
        return len(self.cp) - 1

    def _ensure_nat(self, name_idx, desc_idx):
        for i, e in enumerate(self.cp):
            if e and e[0] == 12 and e[1] == name_idx and e[2] == desc_idx:
                return i
        self.cp.append([12, name_idx, desc_idx])
        return len(self.cp) - 1

    def rename(self, renames):
        """renames: {(kind, 旧名, 描述符): 新名}。返回实际改动条数。"""
        n = 0
        # ① 成员表 name_index
        for kind in ('field', 'method'):
            for rec in self.members[kind]:
                off, name_i, desc_i = rec
                key = (kind, self.u(name_i), self.u(desc_i))
                if key in renames:
                    rec[1] = self._ensure_utf8(renames[key])
                    n += 1
        # ② 本类对自身成员的引用（新建 NameAndType，避免污染同名它类引用）
        this_idx = self.this_idx
        for i, e in enumerate(self.cp):
            if not e or e[0] not in (9, 10, 11):
                continue
            cls_i, nat_i = e[1], e[2]
            if cls_i != this_idx:
                continue
            nat = self.cp[nat_i]
            oname = self.u(nat[1])
            desc = self.u(nat[2])
            kind = 'field' if e[0] == 9 else 'method'
            key = (kind, oname, desc)
            if key in renames:
                e[2] = self._ensure_nat(self._ensure_utf8(renames[key]), nat[2])
                n += 1
        return n

    def dumps(self):
        cp_bytes = bytearray()
        i = 1
        while i < len(self.cp):
            e = self.cp[i]
            if e is None:
                i += 1
                continue
            tag = e[0]
            cp_bytes.append(tag)
            if tag == 1:
                cp_bytes += struct.pack('>H', len(e[1])) + e[1]
            elif tag in (7, 8, 16, 19, 20):
                cp_bytes += struct.pack('>H', e[1])
            elif tag in (3, 4, 9, 10, 11, 12, 17, 18):
                cp_bytes += struct.pack('>HH', e[1], e[2])
            elif tag in (5, 6):
                cp_bytes += struct.pack('>II', e[1], e[2])
                i += 1
            elif tag == 15:
                cp_bytes += struct.pack('>BH', e[1], e[2])
            i += 1
        tail = bytearray(self.raw[self.tail_start:])
        # 成员表 name_index 就地回填（tail 起点不变，偏移相对 raw 有效）
        # ⚠ 记录的是**成员记录起始偏移**（access_flags 处），name_index 在其后 2 字节；
        # 早期版本直接写起始偏移 → 把 access_flags 覆盖成常量池下标 →
        # 运行时 `ClassFormatError: Illegal field modifiers … 0xF`（冒烟实测）。
        for kind in ('field', 'method'):
            for off, name_i, _desc in self.members[kind]:
                abs_off = off + 2
                if abs_off >= self.tail_start:
                    struct.pack_into('>H', tail, abs_off - self.tail_start, name_i)
        out = bytearray(self.raw[:8])
        out += struct.pack('>H', len(self.cp))
        out += cp_bytes
        out += tail
        return bytes(out)


def load_host_rows():
    """supplement 的宿主精确成员映射: {宿主点分 FQN: {语义名: [混淆名...]}}。

    用于消解「描述符多候选」的歧义（如 `gameFramework.e.a` 同时缺 c/e/f/j，而 reset/
    getString3/getj2/isEnabled4 都是我们的**多余别名**）—— 映射表里 `reset → c`
    这类行恰好给出唯一答案。
    """
    import csv
    csv.field_size_limit(10 * 1024 * 1024)
    out = {}
    path = ROOT / 'mappings' / 'supplement.csv'
    if not path.exists():
        return out
    with open(path, encoding='utf-8') as fh:
        for r in csv.DictReader(fh):
            host = (r.get('obfuscated_package') or '') + '.' + (r.get('obfuscated_class') or '')
            sem = (r.get('meaningful_name') or '').strip()
            obf = (r.get('obfuscated_member') or '').split('(')[0].strip()
            if host and sem and obf:
                out.setdefault(host, {}).setdefault(sem, [])
                if obf not in out[host][sem]:
                    out[host][sem].append(obf)
    return out


def plan_renames(od, td, host_rows=None):
    """给出「按描述符对齐 + 映射行消歧」的改名方案（每个原版成员至多被认领一次）。"""
    renames = {}
    for kind in ('field', 'method'):
        by_desc_o, by_desc_t = {}, {}
        for nm, ds in od[kind]:
            by_desc_o.setdefault(ds, []).append(nm)
        for nm, ds in td[kind]:
            by_desc_t.setdefault(ds, []).append(nm)
        missing = {(n, d) for (n, d) in td[kind] if (n, d) not in od[kind]}
        used = set()
        for (nm, ds) in sorted(od[kind] - td[kind]):
            # 构造器/静态初始化块绝不参与改名（实测把方法改成 `<init>` → 运行期
            # `VerifyError: Bad type on operand stack … uninitializedThis is not assignable`）
            if nm in ('<init>', '<clinit>'):
                continue
            cand = [m for (m, d2) in missing
                    if d2 == ds and (m, d2) not in used and m not in ('<init>', '<clinit>')]
            pick = None
            if len(cand) == 1:
                pick = cand[0]
            elif host_rows:
                mapped = [x for x in host_rows.get(nm, []) if x in cand]
                if len(mapped) == 1:
                    pick = mapped[0]
            if pick:
                renames[(kind, nm, ds)] = pick
                used.add((pick, ds))
    return renames


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--classes-dir', default=str(ROOT / 'build' / 'reverse-classes'))
    ap.add_argument('--stock-jar', default=str(GAME_LIB))
    ap.add_argument('--apply', action='store_true')
    ap.add_argument('--verbose', action='store_true')
    args = ap.parse_args()
    cls_dir = Path(args.classes_dir)
    with zipfile.ZipFile(args.stock_jar) as z:
        stock = {n[:-6]: z.read(n) for n in z.namelist() if n.endswith('.class')}

    n_cls = n_fix = n_ren = 0
    detail = []
    host_rows_all = load_host_rows()
    for p in sorted(cls_dir.rglob('*.class')):
        rel = p.relative_to(cls_dir).as_posix()[:-6]
        if rel not in stock:
            continue
        n_cls += 1
        ours, theirs = ClassFile(p.read_bytes()), ClassFile(stock[rel])
        renames = plan_renames(ours.declared(), theirs.declared(),
                               host_rows_all.get(rel.replace('/', '.')))
        touched = len(renames)
        if not renames:
            continue
        n_fix += 1
        n_ren += touched
        if args.verbose:
            detail.append('%s: %s' % (rel, ', '.join(
                '%s→%s' % (k[1], v) for k, v in list(renames.items())[:5])))
        if args.apply:
            ours.rename(renames)
            p.write_bytes(ours.dumps())

    print('扫描产物类 %d 个；需改名 %d 个类 / %d 处成员%s'
          % (n_cls, n_fix, n_ren, '（已就地改写）' if args.apply else '（dry-run）'))
    for d in detail[:20]:
        print('   ', d)
    return 0


if __name__ == '__main__':
    sys.exit(main())
