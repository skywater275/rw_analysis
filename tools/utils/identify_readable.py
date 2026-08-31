#!/usr/bin/env python3
"""
identify_readable — Rule E 反向身份匹配 (v19.88)

03 树中有语义命名但身份未知的文件 (readable-unmapped) ↔ 02 树未解析混淆类,
用标识符无关的字符串字面量指纹做匹配 (重命名不影响字符串)。

输出 mappings/generated/_rule-e-matches.json:
- unique: 唯一高分匹配 (可直接落库)
- multi: 多候选需仲裁

Usage: python tools/utils/identify_readable.py [--jac 0.6] [--out 路径]
"""
import json
import re
import sys
import zipfile
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import GENERATED_DIR, GAME_LIB

# 字符串字面量 (排除纯标识符/描述符形态)
STR_RE = re.compile(r'"((?:[^"\\]|\\.)*)"')
IDENT_RE = re.compile(r"[\w.$/<>\[\]();]+")


def fp_source(text):
    """指纹: 字符串字面量集合 + 方法声明数."""
    strs = set()
    for m in STR_RE.finditer(text):
        s = m.group(1)[:100]
        if len(s) >= 4 and not IDENT_RE.fullmatch(s):
            strs.add(s)
    methods = len(re.findall(
        r"(?m)^\s*(?:public|private|protected|static|final|synchronized|strictfp|\s)*"
        r"[\w<>.\[\]?,]+\s+[\w$]+\([^;{]*\)\s*\{", text))
    return strs, methods


def main():
    jac_min = 0.6
    mode_all = "--all03" in sys.argv
    for i, a in enumerate(sys.argv):
        if a == "--jac" and i + 1 < len(sys.argv):
            jac_min = float(sys.argv[i + 1])

    idx = json.loads((GENERATED_DIR / "identity-index.json").read_text(encoding="utf-8"))
    jar_fqns = {n.replace("/", ".").replace(".class", "") for n in
                zipfile.ZipFile(GAME_LIB).namelist() if n.endswith(".class")}

    unresolved = [l.strip() for l in
                  (GENERATED_DIR / "unresolved.txt").read_text(encoding="utf-8").splitlines()
                  if l.strip() and not l.startswith("#")]
    print(f"[02 侧] 未解析混淆类: {len(unresolved)}")

    if mode_all:
        # 全 03 树模式: 捕获已改名但索引缺条目的类
        targets = sorted(
            str(p.relative_to(ROOT / "03-deobfuscated")).replace("\\", "/").replace(".java", "").replace("/", ".")
            for p in (ROOT / "03-deobfuscated").rglob("*.java"))
    else:
        ru = [u[0] for u in idx["unresolved"] if u[1] == "readable-unmapped"]
        cascade_targets = {t for _, t in json.loads(
            (GENERATED_DIR / "_cascade-pairs.json").read_text(encoding="utf-8"))}
        targets = sorted(f for f in ru if f not in jar_fqns and f not in cascade_targets)
    print(f"[目标] 03 侧: {len(targets)}")

    fp02 = {}
    for fqn in unresolved:
        p = ROOT / "02-decompiled" / (fqn.replace(".", "/") + ".java")
        if p.exists():
            fp02[fqn] = fp_source(p.read_text(encoding="utf-8", errors="replace"))
    fp03 = {}
    for fqn in targets:
        p = ROOT / "03-deobfuscated" / (fqn.replace(".", "/") + ".java")
        if p.exists():
            fp03[fqn] = fp_source(p.read_text(encoding="utf-8", errors="replace"))
    print(f"[指纹] 02 侧 {len(fp02)} / 03 侧 {len(fp03)}")

    matches = []
    for f3, (s3, m3) in fp03.items():
        if not s3:
            continue
        best = []
        for f2, (s2, m2) in fp02.items():
            if not s2:
                continue
            inter = len(s3 & s2)
            if inter == 0:
                continue
            jac = inter / len(s3 | s2)
            if jac >= 0.4:
                best.append((round(jac, 3), inter, f2, m2))
        if best:
            best.sort(reverse=True)
            matches.append((f3, best))
    unique = [(f3, b[0]) for f3, b in matches if len(b) == 1 and b[0][0] >= jac_min]
    multi = [(f3, b[:3]) for f3, b in matches
             if len(b) > 1 or (len(b) == 1 and b[0][0] < jac_min)]
    print(f"[匹配] 唯一高分 {len(unique)} / 需仲裁 {len(multi)}")
    for f3, (jac, inter, f2, m2) in unique:
        print(f"  {f2}  ->  {f3}  (J={jac}, 共串{inter})")

    out = GENERATED_DIR / "_rule-e-matches.json"
    out.write_text(json.dumps(
        {"unique": [[f3, jac, inter, f2, m2] for f3, (jac, inter, f2, m2) in unique],
         "multi": [[f3] + [[j, i, f, m] for j, i, f, m in b] for f3, b in multi]},
        ensure_ascii=False, indent=1), encoding="utf-8")
    print(f"[输出] {out.name}")
    sys.exit(0)


if __name__ == "__main__":
    main()
