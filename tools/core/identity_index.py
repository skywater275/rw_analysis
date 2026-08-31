#!/usr/bin/env python3
"""
身份索引 — 确定性重建 (R0) 的命名映射地基。

三源合并 (class-discoveries.csv + mappings.csv class行 + mappings.json['classes'])
+ PKG_MAP (package_renamer 43包) + real_pkg: 注释
→ fwd 表 (混淆FQN → 意义FQN) + rev 表 (意义FQN → 混淆FQN, 03 文件身份)

同时预计算 game-lib.jar 全部类的常量池引用集 → class-refs.json (R3 的字节码真相源)。
以 01-classes/game-lib.jar 文件存在性为真值校验, 剔除畸形映射行。

Usage: python tools/core/identity_index.py [--dry-run] [--refs-only]
"""
import csv
import json
import re
import sys
import zipfile
from collections import defaultdict
from pathlib import Path

csv.field_size_limit(10 * 1024 * 1024)

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import (CLASSES_DIR, CLASS_DISCOVERIES, DEOBFUSCATED_DIR,
                          GAME_LIB, GENERATED_DIR, MAPPINGS_CSV, MAPPINGS_JSON)
from rwlib.bytecode import extract_class_refs_from_bytes
from tools.fixers.package_renamer import PKG_MAP

IDENTITY_JSON = GENERATED_DIR / "identity-index.json"
CLASS_REFS_JSON = GENERATED_DIR / "class-refs.json"
UNRESOLVED_TXT = GENERATED_DIR / "unresolved.txt"
MISPLACED_TXT = GENERATED_DIR / "misplaced.txt"

# 无需身份的第三方/平台包前缀
THIRD_PARTY_PREFIXES = (
    "android.", "com.codedisaster.", "com.badlogic.", "org.",
    "network.", "slick.", "lwjgl.", "java.", "javax.", "jdk.", "sun.",
)
# 引用集只保留游戏自身命名空间 (重命名不触及 JRE/第三方)
REF_KEEP_PREFIXES = ("com.corrodinggames.", "a.")


def load_class_maps():
    """三源合并类映射。返回 (entries, conflicts)。

    entries: {混淆FQN: (意义类名, real_pkg或None, 来源)}
    畸形行剔除: 混淆FQN == 意义名 (自指)、空字段。
    """
    entries = {}
    conflicts = []

    def add(obf_fqn, name, real_pkg, source):
        if not obf_fqn or not name:
            return
        if obf_fqn == name or "." not in obf_fqn:
            return  # 自指或畸形
        if obf_fqn in entries:
            old_name, old_pkg, old_src = entries[obf_fqn]
            if old_name != name:
                conflicts.append((obf_fqn, old_name, name, old_src, source))
            return  # 保留先注册的 (class-discoveries 优先)
        entries[obf_fqn] = (name, real_pkg, source)

    # 1. class-discoveries.csv (主源, notes 含 real_pkg: 注释)
    with open(CLASS_DISCOVERIES, encoding="utf-8") as f:
        for row in csv.DictReader(f):
            if row.get("type") != "class":
                continue
            pkg = (row.get("obfuscated_package") or "").strip()
            cls = (row.get("obfuscated_class") or "").strip()
            name = (row.get("meaningful_name") or "").strip()
            m = re.search(r"real_pkg:\s*([\w.]+)", row.get("notes") or "")
            real_pkg = m.group(1) if m else None
            if pkg and cls and name:
                add(f"{pkg}.{cls}", name, real_pkg, "class-discoveries")

    # 2. mappings.csv (class 行)
    with open(MAPPINGS_CSV, encoding="utf-8") as f:
        for row in csv.DictReader(f):
            if row.get("type") != "class":
                continue
            pkg = (row.get("obfuscated_package") or "").strip()
            cls = (row.get("obfuscated_class") or "").strip()
            name = (row.get("meaningful_name") or "").strip()
            if pkg and cls and name:
                add(f"{pkg}.{cls}", name, None, "mappings.csv")

    # 3. mappings.json['classes'] (103条 FQN→可读名)
    with open(MAPPINGS_JSON, encoding="utf-8") as f:
        data = json.load(f)
    json_classes = data.get("classes", {})
    if isinstance(json_classes, dict):
        for obf_fqn, name in json_classes.items():
            if isinstance(name, str) and name.strip():
                add(obf_fqn, name.strip(), None, "mappings.json")

    return entries, conflicts


def build_jar_index():
    """建立 game-lib.jar 类清单 (FQN 集合)。"""
    fqns = set()
    with zipfile.ZipFile(GAME_LIB) as zf:
        for name in zf.namelist():
            if name.endswith(".class"):
                fqns.add(name.replace("/", ".").replace(".class", ""))
    return fqns


def build_class_refs():
    """预计算全 jar 1,698 类的常量池引用集 → class-refs.json。"""
    refs_out = {}
    with zipfile.ZipFile(GAME_LIB) as zf:
        entries = [n for n in zf.namelist() if n.endswith(".class")]
        for name in entries:
            info = extract_class_refs_from_bytes(zf.read(name))
            if not info:
                continue
            this = info["this_class"]
            # 只保留游戏自身命名空间的引用 (重命名不触及 JRE/第三方)
            keep = sorted(r for r in info["classes"] if r.startswith(REF_KEEP_PREFIXES))
            refs_out[this] = {
                "super": info["super_class"],
                "refs": keep,
            }
    return refs_out


def main():
    dry_run = "--dry-run" in sys.argv
    refs_only = "--refs-only" in sys.argv
    print("=" * 60)
    print("identity_index — 确定性重建身份索引 (R0)")
    print("=" * 60)

    # ── Step 1: 三源合并 ──
    entries, conflicts = load_class_maps()
    print(f"[1] 三源合并: {len(entries)} 条候选映射, {len(conflicts)} 冲突")

    # ── Step 2: 真值校验 (01-classes / game-lib.jar 文件存在性) ──
    jar_fqns = build_jar_index()
    class_files = {str(p.relative_to(CLASSES_DIR)).replace("\\", "/").replace(".class", "").replace("/", ".")
                   for p in CLASSES_DIR.rglob("*.class")}

    fwd = {}   # 混淆FQN → 意义FQN
    rejected = []
    for obf_fqn, (name, real_pkg, source) in sorted(entries.items()):
        if obf_fqn not in jar_fqns and obf_fqn not in class_files:
            rejected.append((obf_fqn, name, source))
            continue
        obf_pkg = obf_fqn.rsplit(".", 1)[0]
        pkg = real_pkg or PKG_MAP.get(obf_pkg, obf_pkg)
        fwd[obf_fqn] = f"{pkg}.{name}"
    print(f"[2] 真值校验: {len(fwd)} 条有效 (剔除 {len(rejected)} 畸形行)")

    # ── Step 3: rev 表 + unresolved + misplaced (基于 03 文件树) ──
    rev = {}
    unresolved = []   # (fqn, 分类, 说明)
    misplaced = []    # (fqn, 实际包, 期望包)
    for jf in DEOBFUSCATED_DIR.rglob("*.java"):
        rel = jf.relative_to(DEOBFUSCATED_DIR)
        fqn = str(rel).replace("\\", "/").replace(".java", "").replace("/", ".")
        pkg = fqn.rsplit(".", 1)[0]
        if fqn in rev:
            continue
        obf = next((o for o, m in fwd.items() if m == fqn), None)
        if obf:
            rev[fqn] = obf
            expected = fwd[obf]
            if expected != fqn:
                misplaced.append((fqn, pkg, expected.rsplit(".", 1)[0]))
            continue
        # 未解析: 分类
        if fqn.startswith(THIRD_PARTY_PREFIXES):
            unresolved.append((fqn, "third-party", ""))
        elif re.search(r"(^|\.)[a-z]\d*\.", pkg) or re.fullmatch(r"[a-z]\d*", fqn.rsplit(".", 1)[-1]):
            unresolved.append((fqn, "obfuscated-name", "保留原名"))
        else:
            unresolved.append((fqn, "readable-unmapped", "需内容指纹补身份"))
    print(f"[3] 03 文件身份: {len(rev)} 解析, {len(unresolved)} 未解析 ({len(misplaced)} 错位)")

    # ── Step 4: class-refs.json (全 jar 引用集) ──
    refs_out = build_class_refs()
    print(f"[4] class-refs: {len(refs_out)} 类的常量池引用集")

    # ── Step 5: 输出 ──
    GENERATED_DIR.mkdir(parents=True, exist_ok=True)
    result = {
        "fwd": fwd,
        "rev": rev,
        "stats": {
            "mapped": len(fwd),
            "rev_resolved": len(rev),
            "unresolved": len(unresolved),
            "misplaced": len(misplaced),
            "conflicts": len(conflicts),
            "rejected": len(rejected),
            "class_refs": len(refs_out),
        },
        "conflicts": conflicts,
        "rejected": rejected,
        "unresolved": [list(u) for u in unresolved],
        "misplaced": [list(m) for m in misplaced],
    }
    if dry_run:
        print("\nDRY RUN — 未写入文件")
        sys.exit(0)

    IDENTITY_JSON.write_text(json.dumps(result, ensure_ascii=False, indent=1), encoding="utf-8")
    CLASS_REFS_JSON.write_text(json.dumps(refs_out, ensure_ascii=False), encoding="utf-8")
    UNRESOLVED_TXT.write_text(
        "\n".join(f"{c}\t{fqn}\t{note}" for fqn, c, note in unresolved) + "\n", encoding="utf-8")
    MISPLACED_TXT.write_text(
        "\n".join(f"{fqn}\t实际:{pkg}\t期望:{exp}" for fqn, pkg, exp in misplaced) + "\n", encoding="utf-8")
    print(f"    已写入: {IDENTITY_JSON.name}, {CLASS_REFS_JSON.name}, {UNRESOLVED_TXT.name}, {MISPLACED_TXT.name}")
    sys.exit(0)


if __name__ == "__main__":
    main()
