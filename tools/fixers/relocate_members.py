#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
relocate_members.py — 49 relocate-candidate 归属迁移器 v2 (逆4d, v19.133f98)

suspicious-bc-missing 且语义名在 03 存在的行: 宿主类记录错误 (甚至成员名也错).
证据链 (铁律: 双证 — 语义名在03声明 + 字节码类型兼容):
  1. 03 全树声明提取: 花括号深度感知, 只取类体层的字段/方法声明 (名+类型+序)
  2. 直接命中: 候选 03 类 → class-discoveries 反查混淆类 → javap 成员存在 + 类型兼容
  3. 顺序 zip 回退: 03 声明序与 javap 成员序对齐 → 修正成员名+宿主
  4. 无 03 声明 (仅子串命中) → 陈旧名 → 删除留档

用法: python tools/fixers/relocate_members.py [--dry-run] [--apply] [--workers 8]
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
from rwlib.config import GAME_LIB, DEOBFUSCATED_DIR, find_javap  # noqa: E402

csv.field_size_limit(10 * 1024 * 1024)

TAG = "逆5b-v19.133f98"
DEAD_LOG = ROOT / "mappings" / "generated" / "dead-mappings-removed.csv"

# 宿主身份覆盖 (b2 obf-filename 行 FQN 不可信时的 02↔03 同一性铁证):
# javap k.h 构造器 h(f) + 方法序 a/d/e/b/c + 静态 Point c ↔ 03 pathfinding/h.java 完全一致
HOST_DIR_OVERRIDES = {
    "com.corrodinggames.rts.gameFramework.k.h":
        "com/corrodinggames/rts/gameFramework/pathfinding",
}
# 家族跨域放行白名单 (宿主FQN → 允许的 03 站点 stem):
# 铁证: 03 ResourceRate.java 同时声明 productionRate + consumptionRate (均 float,
# 与 custom.d.a 行家族语义一致); 拒绝泛化共现 (MapLayerRenderer 纹理同名族陷阱)
FAMILY_ALLOW = {
    "com.corrodinggames.rts.game.units.custom.d.a": {"ResourceRate"},
}
MODS = re.compile(r"^\s*(?:public|private|protected|static|final|volatile|"
                  r"transient|synchronized|abstract|\s)+")


def load_b2_maps():
    """B2 反向映射 (b2-03-reverse.csv):
    03文件stem → obf_fqn; 03类名 → {obf_fqn 末段}; obf_fqn → 03目录."""
    stem_map, type_map, obf_dir = {}, {}, {}
    with open(ROOT / "mappings" / "generated" / "b2-03-reverse.csv",
              encoding="utf-8", errors="replace") as f:
        for r in csv.reader(f):
            if len(r) < 4 or r[0] == "file_03":
                continue
            stem = r[0].split("/")[-1].replace(".java", "")
            stem_map[stem] = r[3]
            cls = r[1]
            type_map.setdefault(cls, set()).add(r[3].split(".")[-1])
            obf_dir[r[3]] = "/".join(r[0].split("/")[:-1])
    return stem_map, type_map, obf_dir


def extract_decls(text):
    """类体层 (深度1) 字段与方法声明: [(kind, type, name)] 保序.
    字段行可能含初始化调用 (含括号), 故先按首个 '=' 截断, 再以剩余部分是否含
    '(' 区分方法 (抽象方法以 ';' 结尾, 方法体行以 '{' 结尾自然被过滤)."""
    decls = []
    depth = 0
    for line in text.splitlines():
        s = line.split("//")[0]
        depth += s.count("{") - s.count("}")
        if depth != 1:
            continue
        s = MODS.sub("", s).strip()
        if not s or not s.endswith(";"):
            continue
        s = s.rstrip(";").strip()
        decl_part = s.split("=")[0].strip()  # 字段初始化截断; 方法无 '=' 不变
        if "(" in decl_part:
            kind = "method"
            pm = re.search(r"([\w$]+)\(", decl_part)
            if not pm:
                continue
            name = pm.group(1)
            typ = decl_part[:pm.start()].strip()
        else:
            kind = "field"
            toks = decl_part.split()
            if len(toks) < 2:
                continue
            name = toks[-1]
            typ = " ".join(toks[:-1])
        if not re.match(r"^\w+$", name) or not typ:
            continue
        decls.append((kind, typ, name))
    return decls


def build_decl_index():
    """03 全树: 名 → [(文件相对路径, kind, type)] (声明级, 非子串)."""
    idx = {}
    for p in DEOBFUSCATED_DIR.rglob("*.java"):
        try:
            text = p.read_text(encoding="utf-8", errors="replace")
        except OSError:
            continue
        rel = p.relative_to(DEOBFUSCATED_DIR).as_posix()
        for kind, typ, name in extract_decls(text):
            idx.setdefault(name, []).append((rel, kind, typ))
    return idx


def javap_members(fqn):
    """javap -p → (fields: {名: 类型}, methods: {名: 返回类型})."""
    r = subprocess.run([find_javap(), "-p", "-classpath", str(GAME_LIB), fqn],
                       capture_output=True, text=True, encoding="utf-8",
                       errors="replace", timeout=60)
    if r.returncode != 0:
        return None, None
    fields, methods = {}, {}
    for line in r.stdout.splitlines():
        s = line.strip()
        if s.endswith(";") and not s.startswith(("Compiled", "}")) and "{" not in s:
            if "(" in s:
                pm = re.search(r"([\w$]+)\((.*)\);", s)
                if pm:
                    methods[pm.group(1)] = s[:pm.start()].strip()
            else:
                m = re.search(r"([\w$]+);", s)
                if m:
                    fields[m.group(1)] = s[:m.start()].strip()
    return fields, methods


def type_compat(t3, tj, type_map):
    """03 类型 vs javap 类型兼容: 原始类型相等; 对象类型经 B2 反查."""
    arr3 = "[]" in t3
    arrj = "[]" in tj
    if arr3 != arrj:
        return False
    b3 = t3.replace("[]", "").split()[-1].split(".")[-1]
    bj = tj.replace("[]", "").split()[-1].split(".")[-1]
    if b3 == bj:
        return True
    # 03 可读类型名 → B2 混淆名集合 → javap 末段在内
    return bj in type_map.get(b3, set())


def resolve_one(row, decl_idx, stem_map, type_map, obf_dir):
    """一条 → (动作, 新pkg, 新cls, 新member, 详情).
    证据优先级: 顺序 zip (位置强) > 直接命中 (成员名+类型, 弱 — 成员名可能
    随历史布局漂移). 域一致性: 声明站点 03 目录必须与行宿主混淆类的 03 目录
    一致 (防止同名不同义 — 如 TMXMapLoader 的 String backgroundColor);
    家族跨域放行仅限 FAMILY_ALLOW 白名单 (铁证登记制)."""
    kind, pkg, cls, member, name = row[0], row[1], row[2], row[3], row[4]
    host_fqn = f"{pkg}.{cls.split('(')[0]}"
    host_dir = obf_dir.get(host_fqn) or HOST_DIR_OVERRIDES.get(host_fqn)
    sites = [d for d in decl_idx.get(name, []) if d[1] == kind]
    if not sites:
        return ("delete", None, None, None, f"名未在03声明 (仅子串命中)")

    zips, directs, skipped = [], [], []
    for rel, _, t3 in sites:
        stem = rel.split("/")[-1].replace(".java", "")
        fqn = stem_map.get(stem)
        if not fqn:
            continue
        fields, methods = javap_members(fqn)
        if fields is None:
            continue
        ob_pkg, ob_cls = fqn.rsplit(".", 1)
        site_dir = "/".join(rel.split("/")[:-1])
        family = (host_fqn in FAMILY_ALLOW and stem in FAMILY_ALLOW[host_fqn])
        if host_dir is None:
            # 宿主无 B2 映射 → 无法证明域一致 → 禁止跨类修复
            skipped.append((stem, site_dir))
            continue
        if site_dir != host_dir and not family:
            skipped.append((stem, site_dir))
            continue
        pool = fields if kind == "field" else methods
        # 顺序 zip: 03 声明序 → javap 成员序
        p3 = DEOBFUSCATED_DIR.joinpath(*rel.split("/"))
        try:
            text = p3.read_text(encoding="utf-8", errors="replace")
        except OSError:
            continue
        order = [n for kk, _, n in extract_decls(text) if kk == kind]
        if name in order:
            ji = order.index(name)
            jnames = list(pool)
            if ji < len(jnames) and type_compat(t3, pool[jnames[ji]], type_map):
                zips.append((ob_pkg, ob_cls, jnames[ji], ji))
        # 直接命中回退
        if member in pool and type_compat(t3, pool[member], type_map):
            directs.append((ob_pkg, ob_cls, member))
    uniq_z = {(p, c, m) for p, c, m, _ in zips}
    if len(uniq_z) == 1:
        p, c, m = uniq_z.pop()
        ji = zips[0][3]
        detail = (f"顺序zip {p}.{c}.{m} (序 {ji})"
                  + (f", 原成员 {member} 修正" if m != member else ""))
        return ("fix", p, c, m, detail)
    if not zips:
        uniq_d = set(directs)
        if len(uniq_d) == 1:
            p, c, m = uniq_d.pop()
            return ("fix", p, c, m, f"直接命中 {p}.{c}.{m} (名声明+类型兼容)")
    if skipped and not zips and not directs:
        return ("keep", None, None, None,
                f"仅异域同名声明 ({skipped[0][0]} 在 {skipped[0][1]}), 语义存疑")
    return ("keep", None, None, None,
            "声明在03但无法唯一证实 (多候选/类型不符)")


def main():
    ap = argparse.ArgumentParser(description="49 relocate-candidate 归属迁移器 v2")
    ap.add_argument("--dry-run", action="store_true")
    ap.add_argument("--apply", action="store_true")
    ap.add_argument("--workers", type=int, default=8)
    args = ap.parse_args()
    if not args.dry_run and not args.apply:
        ap.print_help()
        sys.exit(1)

    rows = list(csv.reader(open(ROOT / "mappings" / "supplement.csv",
                                encoding="utf-8", errors="replace")))
    susp = [(i, r) for i, r in enumerate(rows)
            if len(r) >= 7 and r[6] == "suspicious-bc-missing"]
    print(f"[RM] 待迁输入: {len(susp)} 条")

    stem_map, type_map, obf_dir = load_b2_maps()
    decl_idx = build_decl_index()
    print(f"[RM] 03 声明索引: {len(decl_idx)} 名 | B2 类映射: {len(stem_map)}")

    results = {}
    with ThreadPoolExecutor(max_workers=args.workers) as ex:
        futs = {ex.submit(resolve_one, r, decl_idx, stem_map, type_map, obf_dir): i
                for i, r in susp}
        for f in futs:
            i = futs[f]
            results[i] = f.result()

    fixes, deletes, keeps = [], [], []
    for i, r in susp:
        act = results[i]
        if act[0] == "fix":
            fixes.append((i, r, act))
        elif act[0] == "delete":
            deletes.append((i, r, act))
        else:
            keeps.append((i, r, act))
    print(f"[RM] 处置: 修复 {len(fixes)} | 删除 {len(deletes)} | 保持 {len(keeps)}")

    if args.dry_run:
        print("\n--- 修复 ---")
        for i, r, (_, np_, nc, nm, detail) in fixes:
            print(f"  {r[1]}.{r[2]}.{r[3]} -> {np_}.{nc}.{nm}  [{r[4]}] | {detail[:60]}")
        print("\n--- 删除 ---")
        for i, r, (_, _, _, _, detail) in deletes:
            print(f"  {r[1]}.{r[2]}.{r[3]} -> {r[4]} | {detail[:60]}")
        print("\n--- 保持 ---")
        for i, r, (_, _, _, _, detail) in keeps:
            print(f"  {r[1]}.{r[2]}.{r[3]} -> {r[4]} | {detail[:60]}")
        sys.exit(0)

    for i, r, (_, np_, nc, nm, detail) in fixes:
        r[1], r[2], r[3] = np_, nc, nm
        r[6] = "verified-exists"
        r[5] = (r[5] + f"; {TAG}: {detail}") if r[5] else f"{TAG}: {detail}"
    for i, r, (_, _, _, _, detail) in keeps:
        if f"{TAG}:" not in (r[5] or ""):
            r[5] = (r[5] + f"; {TAG}: 保持-{detail}") if r[5] \
                else f"{TAG}: 保持-{detail}"
    del_set = set(i for i, _, _ in deletes)
    deleted_rows = [r for i, r, _ in deletes]
    del_reasons = [a[4] for _, _, a in deletes]
    rows = [r for i, r in enumerate(rows) if i not in del_set]
    with open(ROOT / "mappings" / "supplement.csv", "w", encoding="utf-8",
              newline="") as f:
        csv.writer(f).writerows(rows)
    with open(DEAD_LOG, "a", encoding="utf-8", newline="") as f:
        w = csv.writer(f)
        for r, reason in zip(deleted_rows, del_reasons):
            w.writerow(r + [reason])
    print(f"[RM] 已修复 {len(fixes)} 条, 删除 {len(deletes)} 条 (追加留档)")


if __name__ == "__main__":
    main()
