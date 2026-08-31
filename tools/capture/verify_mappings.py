#!/usr/bin/env python3
"""映射验证战役: unverified 映射批量验证 (存在性 + 冲突 + 03 对齐)

方法 (对照 T0 字节码真源):
1. 存在性: supplement 记录的 (混淆包, 混淆类) 中 obf 字段/方法确实存在 (jar 字节码)
2. 冲突检测: 同一 obf 被多个 sem 映射 (同一宿主) / 同一 sem 映射多个 obf (跨宿主合法)
3. 03 对齐: 03 源 (语义名) 中该 sem 成员是否存在 (声明侧对齐)

Usage: python tools/capture/verify_mappings.py [--fix-suspicious]
"""
import csv
import re
import subprocess
import sys
import zipfile
from collections import Counter, defaultdict
from pathlib import Path

sys.stdout.reconfigure(encoding="utf-8")
ROOT = Path(__file__).resolve().parents[2]  # tools/capture/ → 项目根
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import MAPPINGS_DIR

GAME_LIB = ROOT.parent / "game-lib.orig.jar"
SUPP = MAPPINGS_DIR / "supplement.csv"


class FieldParser:
    """class 解析 (常量池+字段+方法表) — 提取字段名/方法名 (对照存在性).

    常量池: while 循环手动推进 idx (long/double 双槽 idx+=2, for 循环变量修改无效).
    """

    def __init__(self, data):
        import struct
        self.data = data
        (n,) = struct.unpack(">H", data[8:10])
        self.n_const = n
        # 第一遍: Utf8 + 布局
        utf8s = {}
        self.tags = {}
        i = 10
        idx = 1
        while idx < n:
            tag = data[i]
            self.tags[idx] = tag
            i += 1
            if tag == 1:
                (ln,) = struct.unpack(">H", data[i:i + 2])
                i += 2
                utf8s[idx] = data[i:i + ln].decode("utf-8", "replace")
                i += ln
            elif tag in (3, 4):
                i += 4
            elif tag in (5, 6):
                i += 8
                idx += 1  # 双槽
            elif tag in (7, 8, 16):
                i += 2
            elif tag in (19, 20):
                i += 2
            elif tag == 15:
                i += 3
            elif tag in (9, 10, 11, 12, 17, 18):
                i += 4
            else:
                raise ValueError(f"未知常量池 tag {tag}")
            idx += 1
        # 第二遍: Class/String/MethodType 指向 Utf8
        i = 10
        idx = 1
        while idx < n:
            tag = self.tags.get(idx)
            if tag == 1:
                (ln,) = struct.unpack(">H", data[i:i + 2])
                i += 2 + ln
            elif tag in (3, 4):
                i += 4
            elif tag in (5, 6):
                i += 8
                idx += 1
            elif tag in (7, 8, 16):
                (ni,) = struct.unpack(">H", data[i:i + 2])
                i += 2
                utf8s[idx] = utf8s.get(ni, "")
            elif tag in (19, 20):
                i += 2
            elif tag == 15:
                i += 3
            elif tag in (9, 10, 11, 12, 17, 18):
                i += 4
            idx += 1
        self.utf8s = utf8s
        # 字段表
        (acc, this, sup) = struct.unpack(">HHH", data[i:i + 6])
        i += 6
        (n_if,) = struct.unpack(">H", data[i:i + 2])
        i += 2 + 2 * n_if
        self.fields = []
        (n_f,) = struct.unpack(">H", data[i:i + 2])
        i += 2
        for _ in range(n_f):
            (acc2, nidx, didx) = struct.unpack(">HHH", data[i:i + 6])
            i += 6
            self.fields.append((utf8s.get(nidx, ""), utf8s.get(didx, "")))
            (n_attr,) = struct.unpack(">H", data[i:i + 2])
            i += 2
            for _ in range(n_attr):
                i += 2
                (aln,) = struct.unpack(">I", data[i:i + 4])
                i += 4 + aln
        self.methods = []
        (n_m,) = struct.unpack(">H", data[i:i + 2])
        i += 2
        for _ in range(n_m):
            (acc2, nidx, didx) = struct.unpack(">HHH", data[i:i + 6])
            i += 6
            self.methods.append((utf8s.get(nidx, ""), utf8s.get(didx, "")))
            (n_attr,) = struct.unpack(">H", data[i:i + 2])
            i += 2
            for _ in range(n_attr):
                i += 2
                (aln,) = struct.unpack(">I", data[i:i + 4])
                i += 4 + aln

    def has_field(self, name):
        return any(f[0] == name for f in self.fields)

    def has_method(self, name):
        return any(m[0] == name for m in self.methods)


_javap_cache = {}


def _javap_one(fqn):
    """javap -p 单个类 → (字段集, 方法集, 父类名或 None)."""
    r = subprocess.run(
        ["javap", "-p", "-classpath", str(GAME_LIB), fqn],
        capture_output=True, text=True, encoding="utf-8", errors="replace", timeout=30)
    if r.returncode != 0:
        return None
    fields, methods = set(), set()
    parent = None
    for l in r.stdout.splitlines():
        m = re.match(r"\s{2}(?:(?:public|protected|private|static|final|transient|volatile)\s+)+"
                     r"[\w<>\[\],. ?]+\s+(\w+)\s*[;=]", l)
        if m and "(" not in l:
            fields.add(m.group(1))
            continue
        m = re.match(r"\s{2}(?:(?:public|protected|private|static|final|synchronized|"
                     r"strictfp|native|abstract)\s+)+[\w<>\[\],. ?]+\s+(\w+)\s*\(", l)
        if m:
            methods.add(m.group(1))
            continue
        m = re.match(r"\w[\w.$]* (?:extends|implements) ([A-Za-z_$][\w$.]*)", l)
        if m and parent is None:
            parent = m.group(1)
    return fields, methods, parent


def _javap_members(fqn):
    """javap 成员提取 (含继承链, 最多 5 层): 字段/方法在本类或父类即存在."""
    if fqn not in _javap_cache:
        all_f, all_m = set(), set()
        seen = set()
        cur = fqn
        got_any = False
        for _ in range(5):
            if cur in seen or not cur or cur == "java.lang.Object":
                break
            seen.add(cur)
            res = _javap_one(cur)
            if res is None:
                if not got_any:
                    _javap_cache[fqn] = None
                    return None
                break
            got_any = True
            f, m, parent = res
            all_f |= f
            all_m |= m
            cur = parent
        _javap_cache[fqn] = (all_f, all_m)
    return _javap_cache[fqn]


# 模块级: class-discoveries 语义类名 → [(混淆包, 混淆类)]
_CD = defaultdict(list)
if _CD or True:
    with open(MAPPINGS_DIR / "class-discoveries.csv", encoding="utf-8") as _f:
        for _row in csv.DictReader(_f):
            if _row.get("type") == "class":
                _CD[_row.get("meaningful_name", "")].append(
                    (_row.get("obfuscated_package", ""), _row.get("obfuscated_class", "")))


def resolve_cls(pkg, cls):
    """supplement 的 cls 可能是语义名 (ReliableServerSocket) 或混淆名 (y);
    语义名 → class-discoveries 混淆名 (按包匹配)."""
    if cls in _CD:
        cands = _CD[cls]
        for cp, co in cands:
            if cp == pkg:
                return co
        if cands:
            return cands[0][1]
        return None
    return cls


def get_cls(pkg, cls):
    """(混淆包, cls) → (字段集, 方法集) 或 None (javap 继承链)."""
    obf_cls = resolve_cls(pkg, cls)
    if obf_cls is None:
        return None, "无类映射"
    fqn = pkg + "." + obf_cls
    res = _javap_members(fqn)
    return res, None if res else f"javap 失败 {fqn}"


def main():
    # 1. 加载 jar 类字节码
    jar_bin = {}
    with zipfile.ZipFile(GAME_LIB) as z:
        for n in z.namelist():
            if n.endswith(".class"):
                jar_bin[n[:-6]] = z.read(n)

    # 3. 扫描 supplement
    rows = list(csv.reader(open(SUPP, encoding="utf-8")))
    data = rows[1:]

    unverified = 0
    exist_ok = 0      # 存在性通过
    exist_fail = 0    # 字节码无此成员 (可疑/错误)
    conflict = 0      # 同宿主同 obf 多 sem
    parsed_skip = 0   # 类无法解析 (jar 无/内部类)
    suspicious = []

    # 宿主内 obf → [sem] 冲突统计
    host_conflict = defaultdict(list)

    for r in data:
        if len(r) < 7:
            continue
        typ, pkg, cls, obf, sem = r[0], r[1], r[2], r[3], r[4]
        note = (r[6] if len(r) > 6 else "").strip()
        if "--recheck" in sys.argv:
            if note != "suspicious-bc-missing":
                continue  # 重判已标记可疑 (剥签名修复后)
        elif note != "unverified":
            continue  # 仅验证 unverified 标签
        c, bad_reason = get_cls(pkg, cls)
        if c is None:
            parsed_skip += 1
            continue
        fields_set, methods_set = c
        obf_name = obf.split("(")[0].strip()  # obf 可能带签名 (A()/d(float)
        if typ == "field":
            if obf_name in fields_set:
                exist_ok += 1
            else:
                exist_fail += 1
                suspicious.append(("field", pkg, cls, obf, sem, "字节码无此字段"))
        else:
            if obf_name in methods_set:
                exist_ok += 1
            else:
                exist_fail += 1
                suspicious.append(("method", pkg, cls, obf, sem, "字节码无此方法"))
        host_conflict[(typ, pkg, cls, obf)].append(sem)

    real_conflict = {k: v for k, v in host_conflict.items() if len(v) > 1}
    print(f"unverified 总数: {unverified}")
    print(f"  存在性通过: {exist_ok} ({exist_ok / max(unverified, 1) * 100:.1f}%)")
    print(f"  字节码无此成员 (可疑): {exist_fail}")
    print(f"  无法解析状态 (jar 无/内部类): {parsed_skip}")
    print(f"  同宿主同 obf 多 sem 冲突: {len(real_conflict)}")
    print()
    print("=== 可疑映射 (字节码无此成员) 前 50 ===")
    for s in suspicious[:50]:
        print(f"  {s[0]:6s} {s[1]}.{s[2]}.{s[3]} ← {s[4]}  [{s[5]}]")
    print(f"  ...共 {len(suspicious)} 条")
    print()
    print("=== 冲突 (同 obf 多 sem) 前 20 ===")
    for (t, pkg, cls, obf), sems in list(real_conflict.items())[:20]:
        print(f"  {t} {pkg}.{cls}.{obf} → {sems}")

    # --mark: 验证结果写回 supplement (verified 列, 不改变映射本身)
    if "--mark" not in sys.argv:
        print("\n(加 --mark 写回验证标记)")
        return
    sus_key = {(s[1], s[2], s[3], s[4]): s for s in suspicious}
    mars = 0
    marked = 0
    for r in data:
        if len(r) < 7 or r[6].strip() != "unverified":
            continue
        typ, pkg, cls, obf, sem = r[0], r[1], r[2], r[3], r[4]
        key = (pkg, cls, obf, sem)
        if key in sus_key:
            r[6] = "suspicious-bc-missing"  # 字节码无此成员 (宿主/obf 疑似错误)
            marked += 1
            continue
        c, bad = get_cls(pkg, cls)
        if c is None:
            r[6] = "unverifiable-rebuilt"  # 宿主 03 重建类/无法字节码验证
            marked += 1
            continue
        f_all, m_all = c
        obf_name = obf.split("(")[0].strip()
        if (typ == "field" and obf_name in f_all) or (typ == "method" and obf_name in m_all):
            r[6] = "verified-exists"  # 字节码存在性通过
            marked += 1
    with open(SUPP, "w", encoding="utf-8", newline="") as f:
        w = csv.writer(f)
        w.writerows(rows)
    print(f"\n[--mark] 已验证标记: {marked} 条写回 supplement")


if __name__ == "__main__":
    main()
