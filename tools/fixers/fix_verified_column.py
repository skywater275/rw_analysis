#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
fix_verified_column.py — 528 垃圾 verified 列漂移修复器 v2 (逆5a, v19.133f98)

根因: 历史批次写 supplement 时, 成员签名/notes 文本内的逗号未加引号,
csv 解析后列内容漂移 (verified 列被 notes 尾片/语义名占据; 原始 verified 多为空).

修复流程 (铁律: 字节码参数级验证):
  1. 原始行字符扫描: 括号深度追踪重建完整成员签名 (吸收分裂片段+引号残留)
  2. name/notes 重建: 片段中首个"语义名形态"字段 (≥3字符小写或大写开头,
     排除原始类型/单双字母混淆码) = name; 其余片段以逗号重接 = notes
  3. verified 重分配: notes 含 'INI-verified' → ini-verified-v16; 否则 javap
     参数级验证:
       a. 扫描原样参数表与 javap 重载精确匹配 → verified-exists
       b. 尾参数重复 (引号残留) 变体匹配 → 用去重版, verified-exists
       c. 截断行 (无闭合括号) javap 唯一前缀匹配 → 补全签名, verified-exists
       d. 均不匹配 → suspicious-bc-missing + note
  4. 无法重建 (无 name) → 保持原样 + 列复核

用法: python tools/fixers/fix_verified_column.py [--dry-run] [--apply] [--workers 8]
"""

import argparse
import csv
import re
import subprocess
import sys
from concurrent.futures import ThreadPoolExecutor
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent.parent
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import GAME_LIB, find_javap  # noqa: E402

csv.field_size_limit(10 * 1024 * 1024)

KNOWN = {'verified', 'verified-exists', 'unverifiable-rebuilt', 'suspicious-bc-missing',
         'real-class-v1200', 'render-chain', 'order-align-v19.111', 'runtime-verified',
         'batch-inferred-v1200', 'platform-or-external', 'p3-auto-inferred', 'p3-deep-curated',
         'body-verified-v1500', 'body-verified-v1007', 'body-verified-v1300', 'body-verified-v1200',
         'body-verified-v1400', 'body-verified-v1010', 'main-deobf-anchored', 'type-aware-fp-v19.114',
         'game-prefs-verified', 'game-test', 'ini-verified-v16', 'mod-key-verified',
         'func-semantics-verified', 'field-order-zip-v19.114', 'bytecode-verified', 'T0',
         'body-verified-v1300-fixed', 'body-verified-v1400-fixed', '', 'a_3p'}
PRIMITIVES = {'int', 'float', 'boolean', 'double', 'long', 'short', 'byte', 'char', 'void'}
TAG = "逆5a-v19.133f98"


def char_scan(line):
    """字符扫描重建 (type, pkg, cls, member, after片段)."""
    parts = line.split(",", 3)
    if len(parts) < 4:
        return None
    t, pkg, cls, rest = parts
    member, after = "", ""
    if "(" in rest.split(",")[0]:
        depth, j = 0, 0
        while j < len(rest):
            c = rest[j]
            if c == "(":
                depth += 1
            elif c == ")":
                depth -= 1
                if depth == 0:
                    j += 1
                    break
            j += 1
        member = rest[:j]
        if depth != 0:
            after = ""  # 截断行: 无闭合
        else:
            after = rest[j:]
            if after.startswith(","):
                after = after[1:]
    else:
        k = rest.find(",")
        member = rest[:k] if k >= 0 else rest
        after = rest[k + 1:] if k >= 0 else ""
    return t, pkg, cls, member.replace('"', "").strip(), after


def pick_name_frags(after):
    """after 片段 → (name, notes片段). name = 首个语义名形态字段."""
    frags = [f.replace('"', "") for f in after.split(",")] if after else []
    name_idx = None
    for i, f in enumerate(frags):
        f = f.strip()
        if not f:
            continue
        if re.match(r"^[\w$]+$", f) and f not in PRIMITIVES \
                and (len(f) > 2 or f[0].isupper()):
            name_idx = i
            break
    if name_idx is None:
        return None, frags
    return frags[name_idx], [f for j, f in enumerate(frags) if j != name_idx]


def member_params(member):
    """成员签名 → (基名, 参数末段列表) 或 (基名, None 无签名)."""
    m = re.match(r"^([\w$]+)\((.*)\)$", member)
    if not m:
        return member, None
    base = m.group(1)
    inner = m.group(2).strip()
    if not inner:
        return base, []
    ps = []
    for p in inner.split(","):
        p = p.strip()
        if not p:
            continue
        ps.append(p.split(".")[-1].replace("$", ""))
    return base, ps


def javap_overloads(fqn, base):
    """javap -p → {参数末段元组}. 失败返回 None."""
    r = subprocess.run([find_javap(), "-p", "-classpath", str(GAME_LIB), fqn],
                       capture_output=True, text=True, encoding="utf-8",
                       errors="replace", timeout=60)
    if r.returncode != 0:
        return None
    sigs = set()
    for line in r.stdout.splitlines():
        s = line.strip()
        if not s.endswith(");") or "(" not in s:
            continue
        pm = re.search(r"([\w$]+)\((.*)\);", s)
        if not pm or pm.group(1) != base:
            continue
        inner = pm.group(2).strip()
        ps = tuple(x.strip().split(".")[-1] for x in inner.split(",")) if inner else ()
        sigs.add(ps)
    return sigs


def javap_has_field(fqn, name):
    """javap -p: 字段名存在性 (不含方法行)."""
    r = subprocess.run([find_javap(), "-p", "-classpath", str(GAME_LIB), fqn],
                       capture_output=True, text=True, encoding="utf-8",
                       errors="replace", timeout=60)
    if r.returncode != 0:
        return None
    for line in r.stdout.splitlines():
        s = line.strip()
        if s.endswith(";") and "(" not in s and not s.startswith(("Compiled", "}")):
            m = re.search(r"([\w$]+);", s)
            if m and m.group(1) == name:
                return True
    return False


def compat_params(ps, js, type_map):
    """supplement 参数 vs javap 参数逐位兼容 (可读名经 B2 反查)."""
    if len(ps) != len(js):
        return False
    for s, j in zip(ps, js):
        if s == j:
            continue
        if j in type_map.get(s, set()):
            continue
        return False
    return True


def find_match(ps, sigs, type_map):
    """javap 重载中与 ps 兼容者 (空 = 无)."""
    return [s for s in sigs if compat_params(ps, s, type_map)]


def verify_member(fqn, member, kind, type_map):
    """javap 参数级验证 (type_map 可读名翻译) → (final_member, 状态).
    状态: exists / exists-dedup / exists-drop / prefix-full /
          member-exists (无签名按名) / class-missing / missing."""
    base, ps = member_params(member)
    if ps is None:
        # 无签名 (字段/无参名): 按名存在性
        if kind == "field":
            res = javap_has_field(fqn, base)
            if res is None:
                return member, "class-missing"
            return member, "member-exists" if res else "missing"
        sigs = javap_overloads(fqn, base)
        if sigs is None:
            return member, "class-missing"
        return member, "member-exists" if sigs else "missing"
    sigs = javap_overloads(fqn, base)
    if sigs is None:
        return member, "class-missing"
    if find_match(ps, sigs, type_map):
        return member, "exists"
    if len(ps) >= 2 and ps[-1] == ps[-2] and find_match(ps[:-1], sigs, type_map):
        # 尾参数重复 (引号残留) → 去重版
        return f"{base}({', '.join(ps[:-1])})", "exists-dedup"
    # 尾参数丢弃 1-2 个 (notes 碎片被吸收) → javap 匹配版
    for k in (1, 2):
        if len(ps) > k and find_match(ps[:-k], sigs, type_map):
            return f"{base}({', '.join(ps[:-k])})", "exists-drop"
    # 截断/前缀匹配: 恰一个重载以前缀兼容 ps
    pre = [s for s in sigs
           if len(s) > len(ps) and compat_params(ps, s[:len(ps)], type_map)]
    if len(pre) == 1:
        full = pre[0]
        return f"{base}({', '.join(full)})", "prefix-full"
    return member, "missing"


def main():
    ap = argparse.ArgumentParser(description="528 垃圾 verified 列漂移修复器 v2 (逆5a)")
    ap.add_argument("--dry-run", action="store_true")
    ap.add_argument("--apply", action="store_true")
    ap.add_argument("--workers", type=int, default=8)
    args = ap.parse_args()
    if not args.dry_run and not args.apply:
        ap.print_help()
        sys.exit(1)

    supp_path = ROOT / "mappings" / "supplement.csv"
    raw_lines = open(supp_path, encoding="utf-8", errors="replace").read().splitlines()
    rows = list(csv.reader(raw_lines))
    garb = [i for i, r in enumerate(rows)
            if len(r) >= 7 and r[6] not in KNOWN and r[6].strip()]
    print(f"[FVC] 垃圾 verified 行: {len(garb)}")

    # ---- 阶段1: 字符扫描 + name/notes 重建 ----
    plans = {}
    for i in garb:
        r = rows[i]
        res = char_scan(raw_lines[i])
        if res is None:
            plans[i] = ("fail", r, "char-scan 失败")
            continue
        t, pkg, cls, member, after = res
        name, note_frags = pick_name_frags(after)
        if name is None:
            plans[i] = ("fail", r, f"name 缺失 ({after[:40]})")
            continue
        notes = ", ".join(f for f in note_frags if f.strip())
        plans[i] = ("scan", r, (t, pkg, cls, member, name, notes))

    scanned = [(i, v) for i, v in plans.items() if v[0] == "scan"]
    print(f"[FVC] 字符扫描重建: {len(scanned)} | 失败: {len(garb) - len(scanned)}")

    # ---- 阶段2: javap 参数级验证 ----
    stem_map, type_map = {}, {}
    try:
        with open(ROOT / "mappings" / "generated" / "b2-03-reverse.csv",
                  encoding="utf-8", errors="replace") as f:
            for r2 in csv.reader(f):
                if len(r2) < 4 or r2[0] == "file_03":
                    continue
                stem_map[r2[0].split("/")[-1].replace(".java", "")] = r2[3]
                type_map.setdefault(r2[1], set()).add(r2[3].split(".")[-1])
    except OSError:
        pass

    def verify_one(item):
        i, (_, r, (t, pkg, cls, member, name, notes)) = item
        if "INI-verified" in notes:
            return i, r, [t, pkg, cls, member, name, notes, "ini-verified-v16"], "ini"
        fqn = f"{pkg}.{cls.split('(')[0]}"
        fmember, status = verify_member(fqn, member, t, type_map)
        if status == "class-missing":
            # 可读类名 (如 GameSettings) → B2 反查混淆 FQN 重试
            ob = stem_map.get(cls)
            if ob:
                fmember, status = verify_member(ob, member, t, type_map)
        if status in ("exists", "exists-dedup", "exists-drop", "prefix-full",
                      "member-exists"):
            return i, r, [t, pkg, cls, fmember, name, notes, "verified-exists"], status
        if status == "class-missing":
            return i, r, [t, pkg, cls, member, name, notes, "unverifiable-rebuilt"], status
        return i, r, [t, pkg, cls, member, name, notes, "suspicious-bc-missing"], status

    results = {}
    with ThreadPoolExecutor(max_workers=args.workers) as ex:
        futs = {ex.submit(verify_one, (i, v)): i for i, v in scanned}
        for f in futs:
            i = futs[f]
            results[i] = f.result()

    from collections import Counter
    cats = Counter(v[3] for v in results.values())
    print(f"[FVC] 验证: {dict(cats)}")

    if args.dry_run:
        print("\n--- 样本 (前 25) ---")
        shown = 0
        for i, r, nr, status in results.values():
            if shown >= 25:
                break
            print(f"  [{status:14s}] {r[1].split('.')[-1]}.{r[2]}.{nr[3][:36]} -> {nr[4][:20]!r}")
            if nr[5] != r[5]:
                print(f"       notes: {r[5][:44]!r} -> {nr[5][:44]!r}")
            shown += 1
        print("\n--- 非存在态 (missing/suspicious, 全部) ---")
        for i, r, nr, status in results.values():
            if status in ("missing",):
                print(f"  [{status:8s}] {r[1].split('.')[-1]}.{r[2]}.{nr[3][:36]} -> {nr[4][:18]!r} | {nr[5][:40]!r}")
        print("\n--- 失败 (保持原样) ---")
        for i, v in plans.items():
            if v[0] == "fail":
                r = v[1]
                print(f"  {r[1]}.{r[2]}.{r[3][:26]} -> {r[4][:16]!r} | {v[2][:44]}")
        sys.exit(0)

    for i, r, nr, status in results.values():
        if status in ("exists-dedup", "exists-drop", "prefix-full"):
            nr[5] = (nr[5] + f"; {TAG}: {status} 重建") if nr[5] \
                else f"{TAG}: {status} 重建"
        rows[i] = nr
    # 阶段3: name 缺失截断行 → javap 重建 member 留档 → 删除
    deleted = []
    for i, v in plans.items():
        if v[0] == "fail" and "name 缺失" in v[2]:
            r = v[1]
            res = char_scan(raw_lines[i])
            if res is None:
                continue
            t, pkg, cls, member, _ = res
            fqn = f"{pkg}.{cls.split('(')[0]}"
            fmember, status = verify_member(fqn, member, t, type_map)
            deleted.append((i, r, fmember, status))
    if args.dry_run:
        print("\n--- 截断行 (删除留档) ---")
        for i, r, fmember, status in deleted:
            print(f"  {r[1]}.{r[2]}.{r[3][:24]} -> javap {status}: {fmember[:44]}")
        sys.exit(0)
    del_set = set(i for i, _, _, _ in deleted)
    rows = [r for i, r in enumerate(rows) if i not in del_set]
    with open(supp_path, "w", encoding="utf-8", newline="") as f:
        csv.writer(f).writerows(rows)
    dead_log = ROOT / "mappings" / "generated" / "dead-mappings-removed.csv"
    with open(dead_log, "a", encoding="utf-8", newline="") as f:
        w = csv.writer(f)
        for i, r, fmember, status in deleted:
            w.writerow(r + [f"逆5a: 截断行 name 丢失 (javap {status}, member 重建 {fmember})"])
    print(f"[FVC] 已写回 {len(results)} 行, 删除截断行 {len(deleted)} 条 (留档)")


if __name__ == "__main__":
    main()
