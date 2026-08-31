#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
fix_bc3_fill.py — 深2: BC3 声明侧方法补全器 (v19.114)

场景: 调用点用语义名 (len>2) 但 03 类声明侧缺该方法 (javac cannot-find 证明)。
本工具从 02b 提取 arity 匹配的混淆方法体, 翻译类型后以语义名插入 03 类:

  1. rev 唯一 → 02b 文件
  2. extract 找 arity 匹配的混淆短名方法 (02b 中唯一 — 结构证据)
  3. find_body 提取方法体 (复用 fix_03_semantic_methods)
  4. 方法名改名 混淆名 → 语义名 (签名 + 体内自调用)
  5. translate_body_types 类型翻译 (复用 fix_03_semantic_methods)
  6. 插入 03 类末尾 (最后一个 '}' 前)
  7. supplement 追加映射 (verified='bc3-fill-v19.114')

防线: 02b arity 匹配方法不唯一 → 跳过; 03 已有同名同 arity → 跳过;
      插后 gate 全量净收益判定 (负收益回退).

用法: python tools/fixers/fix_bc3_fill.py [--dry-run] [--apply] [--limit N]
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
import fix_type_aware_calls as ftac  # noqa: E402
import fix_type_fingerprint as fp  # noqa: E402
from fix_method_pair_generic import extract  # noqa: E402
from fix_03_semantic_methods import find_body, translate_body_types  # noqa: E402

csv.field_size_limit(10 * 1024 * 1024)

SUPPLEMENT_CSV = ftac.SUPPLEMENT_CSV
FILL_EVIDENCE = "bc3-fill-v19.114"


def collect_targets():
    """(类型, 语义名, arity) → 错误列表 (语义名 symbol, 索引无该成员)."""
    fwd = ftac.load_discoveries()
    idx = ftac.load_supplement_index(fwd)
    targets = defaultdict(list)
    for c in ftac.parse_errors():
        if len(c["name"]) <= 2 or c["arity"] is None:
            continue
        cands = [e for e in idx.get(c["type"], []) if e[0] == c["name"]]
        if cands:
            continue
        targets[(c["type"], c["name"], c["arity"])].append(c)
    return targets


def main():
    ap = argparse.ArgumentParser(description="BC3 声明侧方法补全器")
    ap.add_argument("--dry-run", action="store_true")
    ap.add_argument("--apply", action="store_true")
    ap.add_argument("--limit", type=int, default=0)
    args = ap.parse_args()
    if not args.dry_run and not args.apply:
        ap.print_help()
        sys.exit(1)

    fwd = ftac.load_discoveries()
    rev = fp.build_rev(fwd)
    fidx = ftac.build_file_index()
    targets = collect_targets()
    print(f"[BC3fill] 目标组合: {len(targets)}")

    results = []
    reasons = defaultdict(int)
    for (y, name, arity), errs in targets.items():
        pairs = rev.get(y, [])
        if len(pairs) != 1:
            reasons["rev多义或无映射"] += len(errs)
            continue
        pkg, cls = pairs[0]
        p02 = fp.TWOB / pkg.replace(".", "/") / f"{cls}.java"
        if not p02.exists():
            reasons["02b缺失"] += len(errs)
            continue
        p03files = fidx.get(y, [])
        if len(p03files) != 1:
            reasons["03文件多义"] += len(errs)
            continue
        text02 = p02.read_text(encoding="utf-8", errors="replace")
        text03 = p03files[0].read_text(encoding="utf-8", errors="replace")
        # 03 已有同名同 arity → 跳过 (错误另有原因)
        if ftac.find_method_arity(text03, name, arity)[0]:
            reasons["03已有声明"] += len(errs)
            continue
        # 02b 中 arity 匹配的混淆短名方法唯一
        m02 = [x for x in extract(text02) if x["a"] == arity and len(x["n"]) <= 2]
        if len(m02) != 1:
            reasons["02b无唯一arity候选"] += len(errs)
            continue
        obf = m02[0]["n"]
        sig, body = find_body(text02, obf, "")
        if not body:
            reasons["find_body失败"] += len(errs)
            continue
        # 改名 (签名 + 体内自调用)
        body = re.sub(r"\b" + re.escape(obf) + r"\s*\(", name + "(", body)
        sig_new = re.sub(r"\b" + re.escape(obf) + r"\s*\(", name + "(", sig, count=1)
        sig_new = translate_body_types(sig_new)
        body = translate_body_types(body)
        # 签名形态验证: 名字前必须有返回类型 token 且非修饰符关键字
        # (translate 可能把混淆返回类型译成空 → 缺返回类型 = 语法错误 =
        #  javac 提前终止 — v19.114 坍缩教训; 'public name(' 的 public 是修饰符非返回类型)
        MODIFIER_KW = {"public", "private", "protected", "static", "final",
                       "strictfp", "abstract", "synchronized", "native"}
        m_rt = re.search(r"([\w<>\[\].$]+)\s+" + re.escape(name) + r"\s*\(", sig_new)
        if not m_rt or m_rt.group(1) in MODIFIER_KW:
            reasons["签名缺返回类型"] += len(errs)
            continue
        results.append((y, name, arity, obf, pkg, cls, p03files[0], sig_new, body, errs))

    # 跨组合冲突检测: 同一 (类型, 02b混淆名, arity) 只能服务一个语义名 — 多个语义名
    # 抢同一 02b 方法 = 调用点类型标错/继承错位, 全部跳过 (v19.114 WorldGenerator 教训)
    by_obf = defaultdict(list)
    for r in results:
        by_obf[(r[0], r[3], r[2])].append(r)
    conflicts = 0
    results = []
    for key, group in by_obf.items():
        if len(group) > 1:
            conflicts += 1
            continue
        results.append(group[0])
    if conflicts:
        print(f"[BC3fill] 跨组合冲突排除: {conflicts} 组 (同 02b 方法多语义名)")

    print(f"[BC3fill] 可补全组合: {len(results)}")
    print(f"[BC3fill] 原因分布: {dict(reasons)}")
    if args.dry_run:
        for r in results[:30]:
            sig_line = r[7].splitlines()[0][:80] if r[7] else "?"
            print(f"  {r[0]}.{r[3]}({r[2]}参) → 插入 {r[1]} | {sig_line}")
        sys.exit(0)

    if args.limit:
        results = results[:args.limit]
    new_maps = []
    for (y, name, arity, obf, pkg, cls, p03, sig_new, body, errs) in results:
        text = p03.read_text(encoding="utf-8", errors="replace")
        # 插入位置: 最后一个 '}' 前
        last_brace = text.rfind("}")
        if last_brace < 0:
            continue
        # body 已含方法签名 (extract_body 从签名起) — 只插 body, 不再拼 sig
        # (v19.114 教训: sig+body 双拼 = 方法头重复插入 = 语法错误坍缩)
        insertion = "\n\n" + body.replace("\n", "\n    ") + "\n"
        new_text = text[:last_brace] + insertion + text[last_brace:]
        p03.write_text(new_text, encoding="utf-8")
        new_maps.append(["method", pkg, cls, obf, name,
                         f"bc3-fill-v19.114: 声明侧补全 (arity={arity}, 调用点 {y})",
                         FILL_EVIDENCE])
    print(f"[BC3fill] 已补全: {len(new_maps)} 方法")
    if new_maps:
        with open(SUPPLEMENT_CSV, "a", encoding="utf-8", newline="") as f:
            csv.writer(f).writerows(new_maps)
        print(f"[BC3fill] supplement 新映射: {len(new_maps)} 条")


if __name__ == "__main__":
    main()
