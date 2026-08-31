#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
reclassify_dead_mappings.py — 390 suspicious-bc-missing 死映射清理器 (逆4, v19.133f98)

三类处置:
  A. 构造器行 (method + member 形如 '语义名(参数)'): javap 构造器签名核对
     (javap 以混淆类名为构造器名, 故 member 名核对必然失败 → 改为参数列表比对)
     匹配 → verified-exists; 不匹配 → 保持 suspicious
  B. 占位行 (name=do_* 或 notes 含 '[Phase'): 批量脚本插入的垃圾行 → 删除
  C. 其余行: 语义名在 03 全树存在性检查 (子串, 单遍索引)
     存在 → 保持 suspicious + note '归属待迁' (relocate-candidate)
     不存在 → 死映射 (javap member-missing + 03 名缺失) → 删除
     被删行留档 mappings/generated/dead-mappings-removed.csv (含删除原因)

用法: python tools/fixers/reclassify_dead_mappings.py [--dry-run] [--apply] [--workers 8]
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

CTOR_PAT = re.compile(r"^[A-Z]\w*\((.*)\)$")
# 占位名垃圾 (Phase3 批量残留): do_*/get_+字母/类型+数字/常见占位前缀
GARBAGE = re.compile(
    r"^(do_[A-Za-z0-9]{1,3}"
    r"|(get|find|accept)_[A-Za-z]"
    r"|(float|int|short|byte|boolean|long|double|char|string)(Array)?\d+"
    r"|(counter|paint|flag|value|hashMap)\d+"
    r"|am\d+"
    r"|lightingColorFilter\d+)$"
)
DEAD_LOG = ROOT / "mappings" / "generated" / "dead-mappings-removed.csv"
TAG = "逆4-v19.133f98"


def is_garbage_name(name):
    """占位名/描述句/FQN 污染 → 垃圾 (删除)."""
    if GARBAGE.match(name):
        return True
    # 含空格 = 描述句; 含括号/点 = 错位残留/类路径污染
    return any(c in name for c in " .()")


def norm_params(s):
    """参数列表归一化 (去空格, 换行)."""
    return re.sub(r"\s+", "", s)


def javap_ctor_params(fqn):
    """javap -p 提取该类所有构造器参数列表 (含内部类构造器)."""
    r = subprocess.run([find_javap(), "-p", "-classpath", str(GAME_LIB), fqn],
                       capture_output=True, text=True, encoding="utf-8",
                       errors="replace", timeout=60)
    if r.returncode != 0:
        return None
    params = set()
    short = fqn.split(".")[-1]
    for line in r.stdout.splitlines():
        s = line.strip()
        # 构造器行: 以类名(或 $内部类段)开头直接跟 '(' 且以 ');' 结尾
        m = re.match(r"^(?:public|private|protected)?\s*(?:[\w.$]*(?:\$[\w]+)?\.)?([\w$]+)\(", s)
        if m and s.rstrip().endswith(");") and m.group(1) in (short, short.split("$")[-1]):
            # 允许 主类名 或 最后 $ 段 (内部类构造器在 javap 中以 $X 段呈现)
            pm = re.search(r"\((.*)\);", s)
            if pm:
                params.add(norm_params(pm.group(1)))
    return params


def build_name_index(names):
    """单遍索引: 03 全树每个文件包含哪些语义名 (子串)."""
    pat = re.compile("|".join(re.escape(n) for n in names))
    hit = {n: [] for n in names}
    for p in DEOBFUSCATED_DIR.rglob("*.java"):
        try:
            text = p.read_text(encoding="utf-8", errors="replace")
        except OSError:
            continue
        if pat.search(text):
            rel = str(p.relative_to(DEOBFUSCATED_DIR))
            for n in names:
                if n in text:
                    hit[n].append(rel)
    return hit


def main():
    ap = argparse.ArgumentParser(description="390 suspicious 死映射清理器 (逆4)")
    ap.add_argument("--dry-run", action="store_true")
    ap.add_argument("--apply", action="store_true")
    ap.add_argument("--workers", type=int, default=8)
    args = ap.parse_args()
    if not args.dry_run and not args.apply:
        ap.print_help()
        sys.exit(1)

    rows = list(csv.reader(open(ROOT / "mappings" / "supplement.csv",
                                encoding="utf-8", errors="replace")))
    idxs = [i for i, r in enumerate(rows)
            if len(r) >= 7 and r[6] == "suspicious-bc-missing"]
    print(f"[RDM] suspicious 输入: {len(idxs)} 条")

    # ---- 分类 (垃圾名优先 → 构造器 → 其余) ----
    ctor_rows, garbage_rows, rest_rows = [], [], []
    for i in idxs:
        r = rows[i]
        if is_garbage_name(r[4]):
            garbage_rows.append(i)
        elif r[0] == "method" and CTOR_PAT.match(r[3]):
            ctor_rows.append(i)
        else:
            rest_rows.append(i)
    print(f"[RDM] 分类: 构造器 {len(ctor_rows)} | 占位垃圾 {len(garbage_rows)} "
          f"| 待查 {len(rest_rows)}")

    # ---- A: 构造器签名核对 ----
    ctor_ok, ctor_fail = [], []
    for i in ctor_rows:
        r = rows[i]
        pm = CTOR_PAT.match(r[3])
        want = norm_params(pm.group(1))
        got = javap_ctor_params(f"{r[1]}.{r[2].split('(')[0]}")
        if got and want in got:
            ctor_ok.append(i)
        else:
            ctor_fail.append(i)
    print(f"[RDM] 构造器核对: 签名匹配 {len(ctor_ok)} | 不匹配 {len(ctor_fail)}")

    # ---- C: 语义名 03 存在性 ----
    names = {rows[i][4] for i in rest_rows}
    hit = build_name_index(names) if names else {}
    rest_hit, rest_dead = [], []
    for i in rest_rows:
        (rest_hit if hit.get(rows[i][4]) else rest_dead).append(i)
    print(f"[RDM] 待查: 名在03 {len(rest_hit)} | 死映射 {len(rest_dead)}")

    del_rows = sorted(garbage_rows + rest_dead)
    upd_rows = [(i, "verified-exists",
                 f"{TAG}: 构造器签名核对通过 (javap 以混淆类名为构造器名)")
                for i in ctor_ok]
    upd_rows += [(i, "suspicious-bc-missing",
                  f"{TAG}: 语义名在 03 存在 — 归属待迁 (relocate-candidate)")
                 for i in rest_hit]
    print(f"[RDM] 处置: 恢复 {len(ctor_ok)} | 待迁 {len(rest_hit)} | 删除 {len(del_rows)} "
          f"| 构造器待查 {len(ctor_fail)}")

    if args.dry_run:
        print("\n--- 删除样本 (前 15) ---")
        for i in del_rows[:15]:
            r = rows[i]
            print(f"  [{r[0]}] {r[1]}.{r[2]}.{r[3]} -> {r[4]}")
        print("\n--- 构造器恢复样本 ---")
        for i in ctor_ok[:10]:
            r = rows[i]
            print(f"  {r[1]}.{r[2]}  {r[3]}")
        print("\n--- 待迁样本 (前 15) ---")
        for i in rest_hit[:15]:
            r = rows[i]
            print(f"  [{r[0]}] {r[1]}.{r[2]}.{r[3]} -> {r[4]}")
        print("\n--- 构造器核对失败 (前 11) ---")
        for i in ctor_fail:
            r = rows[i]
            print(f"  {r[1]}.{r[2]}  {r[3]}")
        sys.exit(0)

    # ---- 写回 ----
    for i, new_v, note in upd_rows:
        rows[i][6] = new_v
        if note not in (rows[i][5] or ""):
            rows[i][5] = (rows[i][5] + "; " + note).strip("; ") if rows[i][5] else note
    del_set = set(del_rows)
    deleted = [rows[i] for i in del_rows]
    rows = [r for i, r in enumerate(rows) if i not in del_set]
    with open(ROOT / "mappings" / "supplement.csv", "w", encoding="utf-8",
              newline="") as f:
        csv.writer(f).writerows(rows)
    with open(DEAD_LOG, "w", encoding="utf-8", newline="") as f:
        w = csv.writer(f)
        w.writerow(["type", "pkg", "cls", "member", "name", "notes", "verified",
                    "removal_reason"])
        for i, r in zip(del_rows, deleted):
            reason = "占位名垃圾 (do_*/get_+字母/类型+数字/描述句)" if i in garbage_rows \
                else "死映射 (javap member-missing + 03 名缺失)"
            w.writerow(r + [reason])
    print(f"[RDM] 已写回: supplement 更新 {len(upd_rows)} 条, 删除 {len(del_rows)} 条 "
          f"(留档 {DEAD_LOG.relative_to(ROOT)})")


if __name__ == "__main__":
    main()
