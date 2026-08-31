#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
fix_field_zip.py — 跨3: 字段保序 zip 映射生成器 (v19.114)

对跨类误植/字段缺失错误, 用 02b 与 03 的字段声明序列保序配对生成 supplement 映射:
  02b 字段序 [a=-1, b=0, c, d] × 03 字段序 [textureId=-1, referenceCount=0, isLoaded, isDirty]
  -> 类型+初始值+可见性验证一致 -> 映射 (02b名 -> 03名) 写 supplement
  -> fix_type_aware_calls 收割调用点 (已验证: KeyBinding ag2.a -> textureId, -1 净收益)

铁律: 保序配对必须类型+初始值 (若有) 双验证; 03 侧语义名 (len>2) 才生成;
      每映射记录 02b/03 双方声明形态到 notes.

用法: python tools/fixers/fix_field_zip.py [--dry-run] [--apply]
"""

import argparse
import csv
import re
import sys
from collections import defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent.parent
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools" / "fixers"))
from rwlib.config import DEOBFUSCATED_DIR  # noqa: E402
import fix_type_fingerprint as fp  # noqa: E402
import fix_type_aware_calls as ftac  # noqa: E402

csv.field_size_limit(10 * 1024 * 1024)

SUPPLEMENT_CSV = ftac.SUPPLEMENT_CSV
ZIP_EVIDENCE = "field-order-zip-v19.114"

FIELD_RE = re.compile(
    r"^\s*(public|protected|private)?\s*(static\s+)?(final\s+)?"
    r"([\w.$<>\[\]]+)\s+([a-zA-Z_$][\w$]*)\s*(?:=\s*([^;]+?))?;"
)


def extract_fields(text):
    """提取类级字段声明序列 [(可见性, 类型, 名, 初值)] — 类声明后到首个方法前.

    方法体被花括号包围 — 类声明 { 之后深度 1, 深度 >1 的行是方法体, 跳过.
    """
    # 类声明 { 定位
    cm = re.search(r"\b(?:public\s+)?(?:abstract\s+|final\s+)?(?:class|interface|enum)\s+[\w$]+[^{]*\{", text)
    if not cm:
        return []
    depth = 1
    i = cm.end()
    fields = []
    while i < len(text) and depth >= 1:
        # 当前行
        eol = text.find("\n", i)
        if eol < 0:
            eol = len(text)
        line = text[i:eol]
        # 方法声明行 (含 '(' 且以 '{' 或 ';' 结尾) -> 字段区结束
        if re.search(r"\([^)]*\)\s*[{\;]", line):
            break
        m = FIELD_RE.match(line)
        if m:
            fields.append((m.group(1) or "package", m.group(4), m.group(5),
                           (m.group(6) or "").strip()))
        # 深度更新 (多行初值/数组初始化)
        depth += line.count("{") - line.count("}")
        i = eol + 1
    return fields


def javap_fields(fqn):
    """javap -p 字节码字段表 [(类型, 名)] — T0 顺序 (FF 丢字段 fallback)."""
    import subprocess
    sys.path.insert(0, str(ROOT))
    from rwlib.config import find_javap, GAME_LIB
    r = subprocess.run([find_javap(), "-p", "-classpath", GAME_LIB, fqn],
                       capture_output=True, text=True, encoding="utf-8",
                       errors="replace", timeout=60)
    fields = []
    for line in r.stdout.splitlines():
        s = line.strip()
        # 字段行: 'int a;' / 'public static final int X;' — 无 '(' 且以 ';' 结尾
        if "(" in s or not s.endswith(";") or s.startswith(("Compiled", "}")) or "{" in s:
            continue
        m = re.match(r"(?:(?:public|protected|private|static|final|volatile|transient)\s+)*"
                     r"([\w.$<>\[\]]+)\s+([\w$]+);", s)
        if m:
            fields.append((m.group(1), m.group(2)))
    return fields


def collect_field_targets():
    """字段类错误组合: (可读类型, 混淆字段名) — 无括号 symbol."""
    fwd = ftac.load_discoveries()
    idx = ftac.load_supplement_index(fwd)
    targets = defaultdict(list)
    for c in ftac.parse_errors():
        if c["kind"] != "field" or len(c["name"]) > 2:
            continue
        cands = [e for e in idx.get(c["type"], []) if e[0] == c["name"]]
        if cands:
            continue
        targets[(c["type"], c["name"])].append(c)
    return targets


def main():
    ap = argparse.ArgumentParser(description="字段保序 zip 映射生成器")
    ap.add_argument("--dry-run", action="store_true")
    ap.add_argument("--apply", action="store_true")
    args = ap.parse_args()
    if not args.dry_run and not args.apply:
        ap.print_help()
        sys.exit(1)

    fwd = ftac.load_discoveries()
    rev = fp.build_rev(fwd)
    fidx = ftac.build_file_index()
    targets = collect_field_targets()
    print(f"[FZ] 字段类目标组合: {len(targets)}")

    maps = []  # (pkg, cls, obf_field, semantic_name, notes)
    reasons = defaultdict(int)
    for (y, obf_field), errs in targets.items():
        pairs = rev.get(y, [])
        if len(pairs) != 1:
            reasons["rev多义或无映射"] += len(errs)
            continue
        pkg, cls = pairs[0]
        p02 = fp.TWOB / pkg.replace(".", "/") / f"{cls}.java"
        p03files = fidx.get(y, [])
        if not p02.exists() or len(p03files) != 1:
            reasons["文件缺失或多义"] += len(errs)
            continue
        text02 = p02.read_text(encoding="utf-8", errors="replace")
        text03 = p03files[0].read_text(encoding="utf-8", errors="replace")
        f2 = extract_fields(text02)
        f3 = extract_fields(text03)
        # 02b 中 obf_field 的位置 — 自身字段区, 缺则沿 extends 父链
        pos = [i for i, f in enumerate(f2) if f[2] == obf_field]
        f2_used, f3_used = f2, f3
        if len(pos) != 1:
            m_sup = re.search(r"extends\s+([\w.$]+)", text02)
            if m_sup:
                sup_name = m_sup.group(1).split(".")[-1]
                p_sup = p02.parent / f"{sup_name}.java"
                if p_sup.exists():
                    f_sup = extract_fields(p_sup.read_text(encoding="utf-8", errors="replace"))
                    pos = [i for i, f in enumerate(f_sup) if f[2] == obf_field]
                    if len(pos) == 1:
                        f2_used = f_sup
                        # 03 侧对应父链: 03 Y.java 的 extends 名 → 其 03 文件字段区
                        m3_sup = re.search(r"extends\s+([\w.$]+)", text03)
                        if not m3_sup:
                            reasons["03无extends"] += len(errs)
                            continue
                        sup3 = m3_sup.group(1).split(".")[-1]
                        p3_sup = p03files[0].parent / f"{sup3}.java"
                        if not p3_sup.exists():
                            reasons["03父类文件缺失"] += len(errs)
                            continue
                        f3_used = extract_fields(
                            p3_sup.read_text(encoding="utf-8", errors="replace"))
        if len(pos) != 1:
            # javap fallback: FF 丢字段时用字节码字段表 (T0 顺序)
            fq = pkg + "." + cls
            fj = javap_fields(fq)
            pos = [i for i, f in enumerate(fj) if f[1] == obf_field]
            if len(pos) != 1:
                reasons["02b字段位置不唯一"] += len(errs)
                continue
            k = pos[0]
            if k >= len(f3_used):
                # 第三分支: 03 丢字段 (CFR 丢字段损伤族) → 补混淆名字段声明 (零改名消错)
                typ2, n2 = fj[k][0], fj[k][1]
                base2 = typ2.split(".")[-1]
                rd = fwd.get((pkg, base2))
                if rd:
                    base2 = rd.split(".")[-1]
                if base2 not in ("int", "float", "boolean", "byte", "short", "long",
                                 "double", "char", "String") and not re.match(
                        r"^[A-Z][\w$]*$", base2):
                    reasons["补字段类型不可译"] += len(errs)
                    continue
                maps.append(("ADD_FIELD", p03files[0], typ2, obf_field,
                             f"field-order-zip-v19.114(javap): 03 丢字段补声明 "
                             f"{typ2} {n2} (位置{k}, {y})"))
                print(f"[FZ] {y}: 补字段 {base2} {obf_field} (javap 位置{k})")
                continue
            typ2, n2, init2 = fj[k][0], fj[k][1], ""
            vis3, typ3, n3, init3 = f3_used[k]
            if len(n3) <= 2:
                reasons["03侧未语义化"] += len(errs)
                continue
            # 类型验证: javap 混淆类型 → fwd 译简单名 == 03 简单名 (译不出跳过)
            base2 = typ2.split(".")[-1]
            rd = fwd.get((pkg, base2))
            if rd:
                base2 = rd.split(".")[-1]
            if base2 not in ("int", "float", "boolean", "byte", "short", "long",
                             "double", "char", "String", "Object") and \
                    base2 != typ3.split(".")[-1]:
                reasons["类型不符"] += len(errs)
                continue
            maps.append((pkg, cls, obf_field, n3,
                         f"field-order-zip-v19.114(javap): 字节码 {typ2} {n2} "
                         f"<-> 03 {typ3} {n3}={init3 or '-'} (位置{k}, {y})"))
            print(f"[FZ] {y}.{obf_field} -> {n3} (javap位置{k}: {typ2} <-> {typ3}={init3 or '-'})")
            continue
        k = pos[0]
        if k >= len(f3_used):
            reasons["03字段序不足"] += len(errs)
            continue
        vis2, typ2, n2, init2 = f2_used[k]
        vis3, typ3, n3, init3 = f3_used[k]
        if len(n3) <= 2:
            reasons["03侧未语义化"] += len(errs)
            continue
        # 双验证: 初值一致 (若有) 或 类型一致 (简单名)
        if init2 and init2 != init3:
            reasons["初值不符"] += len(errs)
            continue
        if init2 and not init3:
            reasons["初值缺失"] += len(errs)
            continue
        if not init2 and typ2.split(".")[-1] != typ3.split(".")[-1]:
            reasons["类型不符"] += len(errs)
            continue
        maps.append((pkg, cls, obf_field, n3,
                     f"field-order-zip-v19.114: 02b {typ2} {n2}={init2 or '-'} "
                     f"<-> 03 {typ3} {n3}={init3 or '-'} (位置{k}, {y})"))
        print(f"[FZ] {y}.{obf_field} -> {n3} (位置{k}: {typ2}={init2 or '-'} <-> {typ3}={init3 or '-'})")

    print(f"[FZ] 可生成映射: {len(maps)} | 原因: {dict(reasons)}")
    if args.dry_run:
        sys.exit(0)

    if maps:
        added_fields = []
        new_maps = 0
        with open(SUPPLEMENT_CSV, "a", encoding="utf-8", newline="") as f:
            w = csv.writer(f)
            for entry in maps:
                if entry[0] == "ADD_FIELD":
                    added_fields.append(entry)
                else:
                    pkg, cls, obf, name, notes = entry
                    w.writerow(["field", pkg, cls, obf, name, notes, ZIP_EVIDENCE])
                    new_maps += 1
        # 补字段: 03 类字段区末尾插入 '<译名> <混淆名>;'
        for tag, p03, typ2, obf, notes in added_fields:
            text = p03.read_text(encoding="utf-8", errors="replace")
            # 幂等检查: 字段名已存在 (前次插入或原有) → 跳过 (v19.114 重复插入教训)
            if re.search(r"\b" + re.escape(obf) + r"\b", text):
                continue
            # 译名: 基本类型恒等; 引用类型 fwd 按类名反查 (03 文件包上下文)
            base2 = typ2.split(".")[-1]
            pkg3 = ".".join(p03.relative_to(DEOBFUSCATED_DIR).parts[:-1])
            rd = next((v for (pk, cn), v in fwd.items()
                       if cn == base2 and pk.replace(".", "/") in pkg3.replace(".", "/")),
                      None)
            field_type = rd.split(".")[-1] if rd else base2
            # 插入位置: 类声明 { 后第一个字段前 (或字段区末尾 — 用类头后)
            cm = re.search(r"\b(?:public\s+)?(?:abstract\s+|final\s+)?"
                           r"(?:class|interface|enum)\s+([\w$]+)[^{]*\{", text)
            if not cm:
                continue
            # 类名匹配: 声明的类必须是文件名 (防插进其他顶层类/内部类 — v19.114)
            if cm.group(1) != p03.stem:
                continue
            insert = cm.end()
            decl = f"\n    public {field_type} {obf};"
            text = text[:insert] + decl + text[insert:]
            p03.write_text(text, encoding="utf-8")
        print(f"[FZ] supplement 新映射: {new_maps} 条 | 补字段声明: {len(added_fields)} 处")
        print(f"[FZ] 提示: 接着跑 python tools/fixers/fix_type_aware_calls.py --apply 收割调用点")


if __name__ == "__main__":
    main()
