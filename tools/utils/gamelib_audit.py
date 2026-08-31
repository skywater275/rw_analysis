#!/usr/bin/env python3
"""
05-gamelib 语义名审计 (v19.85) — 扫描 05-gamelib (game-lib.jar 直接解包) 类清单,
与 identity-index.json / supplement.csv / 02-decompiled / 03-deobfuscated 交叉核对。

分类规则:
- 混淆名: 基名 (去 $N) 全小写字母 (ProGuard 单字符 a-z + 溢出 aa/ab...)
- 语义名: 其余 (官方 CamelCase 名, 如 Main/Audio/SteamAPI)

核对 (语义组):
- fqn 在 fwd 表键 → RENAMED (项目把官方名改名, fwd[fqn] 为 03 名)
- fqn 在 fwd 表值 → MAPPED-VIA-OBF (混淆名指向官方名; count>1 即多对一歧义)
- 03 有同名文件 → IDENTITY (03 已直接使用官方名, identity-index 不记录恒等映射)
- 外类被改名且 03 有改名后内部类文件 → RENAMED-CASCADE (改名级联, 非缺口)
- 03 外类文件中内联声明 → INLINED (03 合并进外类文件, 非缺口)
- 均无 → MISSING (真实缺口)
- supplement.csv 成员映射覆盖计数 (obfuscated_package+class == FQN)

核对 (混淆组): fwd 键 → MAPPED / UNMAPPED。

另外做 02-decompiled vs 05-gamelib 的 FQN 集合 diff (02 完整性验证)。

Usage: python tools/utils/gamelib_audit.py [--csv 输出路径]
"""
import csv
import json
import re
import sys
from collections import Counter, defaultdict
from pathlib import Path

csv.field_size_limit(10 * 1024 * 1024)

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import DECOMPILED_DIR, DEOBFUSCATED_DIR, GENERATED_DIR, SUPPLEMENT_CSV

GAME_LIB = None  # 动态导入 (05-gamelib 已删, 改 jar zipfile 遍历 v19.133f98)
IDENTITY_INDEX = GENERATED_DIR / "identity-index.json"
OUT_CSV = GENERATED_DIR / "05-semantic-audit.csv"


def collect_fqns(src_path, suffix):
    """收集 jar 或目录内所有类的 FQN (点号分隔, 内部类用 $), 返回 {fqn: 条目路径}.

    v19.133f98: 05-gamelib 目录已删 — jar 走 zipfile 遍历, 目录走 rglob.
    """
    import zipfile
    fqns = {}
    sp = Path(src_path)
    if sp.is_dir():
        for p in sorted(sp.rglob("*." + suffix)):
            rel = p.relative_to(sp)
            fqn = ".".join(list(rel.with_suffix("").parts))
            fqns[fqn] = rel.as_posix()
        return fqns
    with zipfile.ZipFile(sp) as z:
        for name in sorted(z.namelist()):
            if name.endswith("." + suffix):
                fqn = name[: -len(suffix) - 1].replace("/", ".")
                fqns[fqn] = name
    return fqns


def classify(fqn):
    """按基名分类: 全小写字母 → obfuscated, 否则 → semantic."""
    base = fqn.rsplit(".", 1)[-1]
    base = base.split("$")[0]
    if re.fullmatch(r"[a-z]+", base):
        return "obfuscated"
    return "semantic"


def main():
    out_csv = OUT_CSV
    for i, a in enumerate(sys.argv):
        if a == "--csv" and i + 1 < len(sys.argv):
            out_csv = ROOT / sys.argv[i + 1]

    # ── 1. 收集三棵树 ──────────────────────────────────────────────
    from rwlib.config import GAME_LIB as _GAME_LIB
    gamelib = collect_fqns(_GAME_LIB, "class")
    decompiled = collect_fqns(DECOMPILED_DIR, "java")

    # ── 2. 02 vs 05 完整性 diff ────────────────────────────────────
    only_05 = sorted(set(gamelib) - set(decompiled))
    only_02 = sorted(set(decompiled) - set(gamelib))
    print(f"[02完整性] 05-gamelib {len(gamelib)} 类 vs 02-decompiled {len(decompiled)} 文件")
    if not only_05 and not only_02:
        print("[02完整性] 完全一致 — 02-decompiled 是 game-lib.jar 的完整 CFR 输出")
    else:
        print(f"[02完整性] 不一致! 仅05有 {len(only_05)} 个; 仅02有 {len(only_02)} 个")
        for f in only_05[:10]:
            print(f"   仅05: {f}")
        for f in only_02[:10]:
            print(f"   仅02: {f}")

    # ── 3. 载入映射库 ──────────────────────────────────────────────
    idx = json.loads(IDENTITY_INDEX.read_text(encoding="utf-8"))
    fwd, rev = idx["fwd"], idx["rev"]
    fwd_values = defaultdict(list)      # 语义FQN → [混淆FQN...]
    for obf, sem in fwd.items():
        fwd_values[sem].append(obf)

    # supplement 成员映射覆盖: (package, class) → 成员数
    from rwlib.mappings import load_supplement
    _, rows = load_supplement()
    supp_counter = Counter()
    for r in rows:
        supp_counter[(r["obfuscated_package"], r["obfuscated_class"])] += 1

    # 03 同名文件索引: 类简单名 → [03 相对路径]
    stem_index = defaultdict(list)
    for p in sorted(DEOBFUSCATED_DIR.rglob("*.java")):
        stem_index[p.stem].append(p.relative_to(DEOBFUSCATED_DIR).as_posix())

    # ── 4. 交叉核对 ────────────────────────────────────────────────
    semantic_rows = []
    obf_mapped = obf_unmapped = 0
    status_count = Counter()

    def locate_in_03(fqn):
        """在 03 中定位类的文件. 返回 (status, project_name, note)."""
        stem = fqn.rsplit(".", 1)[-1]
        stems = stem_index.get(stem, [])
        if stems:
            return "IDENTITY", "", "03: " + ";".join(stems[:3])
        # 改名级联: 外类被改名 → 内部类 stem 随之改名 (如 Root$1 → MainUIController$1)
        outer, sep, inner = fqn.partition("$")
        if sep and outer in fwd:
            new_base = fwd[outer].rsplit(".", 1)[-1]
            stems = stem_index.get(new_base + "$" + inner, [])
            if stems:
                return ("RENAMED-CASCADE", fwd[outer],
                        "03: " + ";".join(stems[:3]))
        # 内联: 03 外类文件里直接声明了该内部类
        outer_stems = stem_index.get(outer.rsplit(".", 1)[-1], [])
        for os in outer_stems[:3]:
            p = DEOBFUSCATED_DIR / os
            content = p.read_text(encoding="utf-8", errors="replace")
            if re.search(rf"\b(?:class|interface|enum)\s+{re.escape(inner)}\b", content):
                return "INLINED", "", "03 合并进: " + os
        return "MISSING", "", "03无同名文件 — 真实缺口"

    for fqn in sorted(gamelib):
        group = classify(fqn)
        pkg, cls = fqn.rsplit(".", 1)
        supp_n = supp_counter.get((pkg, cls), 0)

        if group == "obfuscated":
            if fqn in fwd:
                obf_mapped += 1
                continue
            obf_unmapped += 1
            continue

        # 语义组完整核对
        row = {"fqn": fqn, "group": group, "status": "", "project_name": "",
               "fwd_value_count": 0, "supplement_members": supp_n, "note": ""}
        in_fwd_key = fqn in fwd
        value_hits = fwd_values.get(fqn, [])

        if in_fwd_key:
            row["status"] = "RENAMED"
            row["project_name"] = fwd[fqn]
        elif value_hits:
            row["status"] = "MAPPED-VIA-OBF"
            row["fwd_value_count"] = len(value_hits)
            row["project_name"] = value_hits[0]
            if len(value_hits) > 1:
                row["note"] = "多对一歧义: " + ";".join(sorted(value_hits))
        else:
            st, pn, note = locate_in_03(fqn)
            row["status"], row["project_name"], row["note"] = st, pn, note
        status_count[row["status"]] += 1
        semantic_rows.append(row)

    # ── 5. 输出 CSV ────────────────────────────────────────────────
    header = ["fqn", "group", "status", "project_name", "fwd_value_count",
              "supplement_members", "note"]
    with open(out_csv, "w", encoding="utf-8", newline="") as f:
        w = csv.DictWriter(f, fieldnames=header)
        w.writeheader()
        for r in semantic_rows:
            w.writerow(r)

    # ── 6. 重生成 unresolved.txt (真实未解析清单) ──────────────────
    missing = [r["fqn"] for r in semantic_rows if r["status"] == "MISSING"]
    true_unresolved = sorted(
        f for f in gamelib if classify(f) == "obfuscated" and f not in fwd) + missing
    unresolved_txt = GENERATED_DIR / "unresolved.txt"
    with open(unresolved_txt, "w", encoding="utf-8") as f:
        f.write("# 真实未解析清单 (05-gamelib 1698 类, v19.85 gamelib_audit 重生成)\n")
        f.write(f"# 混淆未映射 {obf_unmapped} + 官方名缺失 {len(missing)}"
                f" = {len(true_unresolved)}\n")
        f.write(f"# 官方名恒等/已改名类不再计为未解析 (旧版 1037 条高估)\n\n")
        for fqn in true_unresolved:
            f.write(fqn + "\n")

    # ── 7. 控制台摘要 ──────────────────────────────────────────────
    n_sem = len(semantic_rows)
    n_obf = obf_mapped + obf_unmapped
    print(f"\n[分类] 共 {len(gamelib)} 类: 语义名 {n_sem} ({n_sem * 100.0 / len(gamelib):.1f}%)"
          f" / 混淆名 {n_obf}")
    print(f"[混淆组] 已映射 (fwd) {obf_mapped} / 未映射 {obf_unmapped}")
    print(f"[语义组] {n_sem} 个官方名交叉核对:")
    for st in ("RENAMED", "MAPPED-VIA-OBF", "IDENTITY", "RENAMED-CASCADE", "INLINED", "MISSING"):
        print(f"    {st:15s} {status_count.get(st, 0)}")
    supp_covered = sum(1 for r in semantic_rows if r["supplement_members"] > 0)
    print(f"[supplement] 语义类中有成员映射的: {supp_covered}/{n_sem}")
    print(f"[真实未解析] 混淆未映射 {obf_unmapped} + 语义缺失 {status_count.get('MISSING', 0)}"
          f" = {obf_unmapped + status_count.get('MISSING', 0)} (unresolved.txt 已重生成)")
    print(f"[输出] {out_csv.relative_to(ROOT)}")
    sys.exit(0)


if __name__ == "__main__":
    main()
