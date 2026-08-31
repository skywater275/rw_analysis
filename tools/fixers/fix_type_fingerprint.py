#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
fix_type_fingerprint.py — Phase B B3: 02b 字面量指纹自动配对器 (v19.114)

对 compile-errors.csv 中"索引无成员"的类型感知 cannot-find (调用点用混淆短名,
location 类型可读), 用 02b 反编译源的字面量指纹在 03 声明侧找语义名方法:

  1. (可读类型Y, 混淆symbol, arity) 按错误去重
  2. Y → 混淆类 (rev 查 class-discoveries) → 02b 文件
  3. 02b 中 symbol 方法 → 字符串字面量指纹
  4. 03 Y.java 中 arity 匹配方法 → 指纹比对 (字面量重合≥2 → ≥1 → token sim≥0.55)
  5. 唯一命中 → 映射写入 supplement (verified='type-aware-fp-v19.114')
  6. 调用点改名 (行内唯一匹配)

铁律落地:
  指纹 = 02b (FernFlower 字节码反编译源) 字符串字面量 — 字节码衍生证据;
  03 侧存在同名方法 (未改名) → 跳过 (cannot-find 另有原因, 改名无益);
  多候选歧义/非唯一最佳 → 跳过。

用法: python tools/fixers/fix_type_fingerprint.py [--dry-run] [--apply] [--limit N]
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
from rwlib.config import DEOBFUSCATED_DIR, DECOMPILED_DIR  # noqa: E402
import fix_type_aware_calls as ftac  # noqa: E402
from fix_method_pair_generic import extract, sim  # noqa: E402
sys.path.insert(0, str(ROOT / "tools" / "utils"))
from identify_readable import fp_source  # noqa: E402

csv.field_size_limit(10 * 1024 * 1024)

TWOB = ROOT / "02b-decompiled"
SUPPLEMENT_CSV = ftac.SUPPLEMENT_CSV
FP_EVIDENCE = "type-aware-fp-v19.114"


def build_rev(fwd):
    """可读简单名 → [(混淆包, 混淆类)] — 02b 文件存在性校验过滤恒等映射残留.

    class-discoveries 存在恒等映射残留行 (cls 列已存可读名, 如
    ('units.am','UnitInstance')→'UnitInstance') — 其混淆键指向不存在的 02b 文件,
    造成 rev 双义 (v19.114 Phase D 诊断). 校验 02b 文件存在即排除假键.
    """
    rev = defaultdict(list)
    for (pkg, cls), rd in fwd.items():
        p02 = TWOB / pkg.replace(".", "/") / f"{cls}.java"
        if not p02.exists():
            continue  # 混淆键无 02b 文件 → 恒等映射残留, 跳过
        rev[rd.split(".")[-1]].append((pkg, cls))
    return rev


def collect_targets():
    """从类型感知 cannot-find 收集 (类型, symbol, arity, 错误行列表) — 索引无成员者."""
    fwd = ftac.load_discoveries()
    idx = ftac.load_supplement_index(fwd)
    targets = defaultdict(list)
    for c in ftac.parse_errors():
        if len(c["name"]) > 2:
            continue  # 只处理混淆短名调用点 (语义名调用点属声明侧战场)
        if c["arity"] is None:
            continue  # 字段不在 B3 v1 范围 (方法调用优先)
        cands = [e for e in idx.get(c["type"], []) if e[0] == c["name"]]
        if cands:
            continue  # 索引已有 → fix_type_aware_calls 负责
        key = (c["type"], c["name"], c["arity"])
        targets[key].append(c)
    return targets


KW_NOBODY = {"if", "for", "while", "switch", "return", "throw", "new", "do"}


def extract_no_body(text):
    """接口/抽象方法无体形态: name(params); (仅 interface 文件适用 — 调用语句形态被
    调用点排除: 名字前紧邻 '.' 或名字是关键字)."""
    out = []
    for m in re.finditer(r"\b([a-zA-Z_$][a-zA-Z0-9_$]*)\s*\(([^(){}]*)\)\s*;", text):
        nm = m.group(1)
        if nm in KW_NOBODY or m.start() > 0 and text[m.start() - 1] == ".":
            continue
        args = m.group(2).strip()
        out.append({"n": nm, "a": 0 if not args else len(args.split(","))})
    return out


def interface_zip(text02, text03, symbol, arity):
    """接口方法保序 zip 配对: 02b 接口方法序列 ↔ 03 接口方法序列 (arity 序列一致才配).

    接口方法无方法体 → 字面量指纹不适用; 顺序 = 字节码方法表顺序 (T0 结构证据,
    与字段保序同步同方法论). symbol 在 02b 序列位置 k → 03 位置 k 的名字.
    """
    if not re.search(r"\binterface\s+[\w$]+", text02):
        return None, "非接口"
    m02 = extract_no_body(text02)
    m03 = extract_no_body(text03)
    if not m02 or not m03:
        return None, "无接口方法"
    idxs = [i for i, x in enumerate(m02) if x["n"] == symbol and x["a"] == arity]
    if len(idxs) != 1:
        return None, "02b位置不唯一"
    k = idxs[0]
    a2 = [x["a"] for x in m02]
    a3 = [x["a"] for x in m03]
    if len(a2) != len(a3) or any(x != y for x, y in zip(a2, a3)):
        return None, "arity序列不等"
    n3 = m03[k]["n"]
    if n3 == symbol:
        return None, "03同名未改名"
    return n3, f"接口保序zip位置{k}"


def fingerprint_match(text02, text03, symbol, arity):
    """02b symbol 方法 → 03 arity 匹配方法, 唯一最佳指纹命中 → 返回 03 方法名或 None."""
    m02 = [x for x in extract(text02) if x["n"] == symbol and x["a"] == arity]
    m03_all = extract(text03)
    if not m02:
        # BC2 接口方法 fallback: 无体方法 → 保序 zip (arity 序列一致)
        n3, msg = interface_zip(text02, text03, symbol, arity)
        if n3:
            return n3, msg
        return None, "02b无此方法"
    # 03 侧存在同名同 arity 方法 → 未改名, cannot-find 另有原因
    if any(y["n"] == symbol and y["a"] == arity for y in m03_all):
        return None, "03同名未改名"
    m03 = [y for y in m03_all if y["a"] == arity and not ftac.is_garbage_name(y["n"])]
    if not m03:
        return None, "03无arity候选"
    hits = []
    for x in m02:
        # 轮1: 字面量 ≥2
        scored = sorted(((y, len(x["lits"] & y["lits"])) for y in m03),
                        key=lambda t: -t[1])
        best, s = scored[0]
        if s >= 2 and (len(scored) == 1 or scored[1][1] < s):
            hits.append(best["n"])
            continue
        # 轮2: 字面量 ≥1
        if x["lits"] and s >= 1 and (len(scored) == 1 or scored[1][1] < s):
            hits.append(best["n"])
            continue
        # 轮3: token 相似 ≥0.55
        if x["toks"]:
            scored = sorted(((y, sim(x["toks"], y["toks"])) for y in m03),
                            key=lambda t: -t[1])
            best, s = scored[0]
            if s >= 0.55 and (len(scored) == 1 or scored[1][1] < s):
                hits.append(best["n"])
    names = set(hits)
    if len(names) == 1 and hits:
        return names.pop(), "指纹命中"
    if not hits:
        return None, "指纹未命中"
    return None, "多候选歧义"


def main():
    ap = argparse.ArgumentParser(description="B3 02b 字面量指纹自动配对器")
    ap.add_argument("--dry-run", action="store_true")
    ap.add_argument("--apply", action="store_true")
    ap.add_argument("--limit", type=int, default=0)
    args = ap.parse_args()
    if not args.dry_run and not args.apply:
        ap.print_help()
        sys.exit(1)

    fwd = ftac.load_discoveries()
    rev = build_rev(fwd)
    fidx = ftac.build_file_index()
    targets = collect_targets()
    print(f"[B3] 目标组合 (类型,symbol,arity): {len(targets)}")

    results = []   # (type, symbol, arity, new_name, pkg, cls, 错误列表)
    reasons = defaultdict(int)
    for (y, symbol, arity), errs in targets.items():
        pairs = rev.get(y, [])
        if len(pairs) == 0:
            reasons["rev无映射"] += len(errs)
            continue
        p03files = fidx.get(y, [])
        if len(p03files) != 1:
            reasons["03文件多义"] += len(errs)
            continue
        if len(pairs) > 1:
            # 深1: rev 多义 → 类级字符串指纹消歧 (02b 各候选 × 03 文件)
            text03 = p03files[0].read_text(encoding="utf-8", errors="replace")
            strs3, _ = fp_source(text03)
            scored = []
            for pkg2, cls2 in pairs:
                p02b = TWOB / pkg2.replace(".", "/") / f"{cls2}.java"
                if not p02b.exists():
                    continue
                strs2, _ = fp_source(p02b.read_text(encoding="utf-8", errors="replace"))
                scored.append((len(strs2 & strs3), pkg2, cls2))
            scored.sort(key=lambda t: -t[0])
            if not scored or scored[0][0] < 2 or \
                    (len(scored) > 1 and scored[1][0] >= scored[0][0]):
                reasons["rev多义指纹未消歧"] += len(errs)
                continue
            pkg, cls = scored[0][1], scored[0][2]
        else:
            pkg, cls = pairs[0]
        p02 = TWOB / pkg.replace(".", "/") / f"{cls}.java"
        if not p02.exists():
            reasons["02b文件缺失"] += len(errs)
            continue
        new_name, msg = fingerprint_match(
            p02.read_text(encoding="utf-8", errors="replace"),
            p03files[0].read_text(encoding="utf-8", errors="replace"),
            symbol, arity)
        if new_name is None:
            reasons[msg] += len(errs)
            continue
        # 声明侧检查 (语义名存在 + 重载唯一) — 复用 fix_type_aware_calls 防线
        if not ftac.check_declaration(fidx, y, new_name, "method", arity):
            reasons["声明侧检查失败"] += len(errs)
            continue
        results.append((y, symbol, arity, new_name, pkg, cls, errs))

    print(f"[B3] 配对成功组合: {len(results)}")
    print(f"[B3] 原因分布: {dict(reasons)}")
    if args.dry_run:
        for r in results[:40]:
            print(f"  {r[0]}.{r[1]}@{r[2]}参 → {r[3]} | 错误 {len(r[6])} 处 | {r[4]}/{r[5]}")
        sys.exit(0)

    # 写 supplement: 存在 (归一化类名, symbol) → 升级 verified 列; 不存在 → 追加新行
    all_rows = []
    upgraded = 0
    with open(SUPPLEMENT_CSV, encoding="utf-8", errors="replace") as f:
        reader = csv.reader(f)
        header = next(reader)
        col = {n: i for i, n in enumerate(header)}
        all_rows.append(header)
        for row in reader:
            all_rows.append(row)
    result_keys = {(r[0], r[1]) for r in results}  # (可读类型名, symbol)
    for row in all_rows[1:]:
        if len(row) < 7:
            continue
        pkg, cls, member = row[1], row[2], row[3]
        rd = cls if len(cls) > 2 else fwd.get((pkg, cls))
        if rd is None:
            continue
        if (rd.split(".")[-1], member) in result_keys:
            row[col["verified"]] = FP_EVIDENCE  # 指纹验证升级 (如 InGameUI MANUAL 条目)
            upgraded += 1
    new_rows = []
    for (y, symbol, arity, new_name, pkg, cls, errs) in results:
        if (y, symbol) in result_keys and any(
                len(r) >= 7 and r[3] == symbol and (
                    (r[2] if len(r[2]) > 2 else fwd.get((r[1], r[2]), "").split(".")[-1]
                     ) == y)
                for r in all_rows[1:]):
            continue  # 已存在 (升级过)
        new_rows.append(["method", pkg, cls, symbol, new_name,
                         f"type-aware-fp-v19.114: 02b字面量指纹配对 (arity={arity}, {y})",
                         FP_EVIDENCE])
    if upgraded or new_rows:
        with open(SUPPLEMENT_CSV, "w", encoding="utf-8", newline="") as f:
            csv.writer(f).writerows(all_rows + new_rows)
        print(f"[B3] supplement: 升级 {upgraded} 条 + 新映射 {len(new_rows)} 条")

    # 调用点改名 (复用 fix_type_aware_calls 的行级替换)
    fixes = []
    for (y, symbol, arity, new_name, pkg, cls, errs) in results:
        for c in errs:
            fixes.append({**c, "new_name": new_name, "notes": FP_EVIDENCE,
                          "verified": FP_EVIDENCE})
    if args.limit:
        fixes = fixes[:args.limit]
    changed = ftac.apply_all(fixes)
    print(f"[B3] 调用点改名: {len(fixes)} 处, 改写文件 {len(changed)}")


if __name__ == "__main__":
    main()
