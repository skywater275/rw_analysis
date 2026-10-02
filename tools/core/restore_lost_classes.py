#!/usr/bin/env python3
"""
丢失类恢复 (R1) — 确定性重建第 2 步。

两类工作:
1. --fix-phantom <可读名>: 幻影名仲裁回退 — 某可读类型名被启发式误改 (如 ShaderEffect
   实为 HUDElement), 用 02 锚点 + 文件常量池引用集逐文件投票裁定真实类型并回退。
2. --restore <02相对路径> --target <03相对路径>: 真丢失类恢复 — 从 02 复制并经
   constant_pool_renamer 重命名管道写入 03。

Usage: python tools/core/restore_lost_classes.py --fix-phantom ShaderEffect [--dry-run]
       python tools/core/restore_lost_classes.py --restore <02rel> --target <03rel> [--dry-run]
"""
import json
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import (DECOMPILED_DIR, DEOBFUSCATED_DIR, GENERATED_DIR)
from tools.core.constant_pool_renamer import (load_indexes, rename_02_file,
                                              obf_to_readable)

# 02 文件自身的 import 表: {简单名: FQN}
def load_imports(path):
    imps = {}
    for line in Path(path).read_text(encoding="utf-8", errors="replace").split("\n"):
        m = re.match(r"^\s*import\s+(?:static\s+)?([\w.]+)\s*;", line)
        if m:
            imps[m.group(1).rsplit(".", 1)[-1]] = m.group(1)
    return imps


def resolve_phantom_file(three_file, phantom, fwd, rev, refs, dry_run=False):
    """
    对单个 03 文件裁定幻影名的真实类型并回退。

    返回 report dict; dry_run 时不写文件。
    """
    rel = three_file.relative_to(DEOBFUSCATED_DIR).as_posix()
    fqn = rel.replace(".java", "").replace("/", ".")
    obf = rev.get(fqn)
    report = {"file": rel, "phantom": phantom, "obf": obf, "winner": None,
              "votes": {}, "replaced": 0}
    if not obf:
        report["note"] = "无身份 (跳过, 需人工)"
        return report
    obf_rel = obf.replace(".", "/") + ".java"
    src02 = DECOMPILED_DIR / obf_rel
    if not src02.exists():
        report["note"] = f"02 源不存在: {obf_rel}"
        return report

    content3 = three_file.read_text(encoding="utf-8", errors="replace")
    lines3 = content3.split("\n")
    content2 = src02.read_text(encoding="utf-8", errors="replace")
    lines2 = content2.split("\n")
    imps2 = load_imports(src02)

    # 03 中幻影名的出现次数 (词边界)
    count3 = len(re.findall(rf"(?<![\w$.]){re.escape(phantom)}(?![\w$])", content3))

    # 引用集内全部映射候选 (FQN 级) — 字节码证明的合法名字空间
    cand_meanings = set()
    for r in refs.get(obf, {}).get("refs", []):
        meaning = obf_to_readable(r, fwd)
        if meaning:
            cand_meanings.add(meaning)

    def tok_meaning(tok):
        """类型 token → 意义FQN (限定名查 fwd, 简单名查 import/同包)。"""
        if "." in tok:
            return obf_to_readable(tok, fwd)
        obf_fqn = imps2.get(tok) or (obf.rsplit(".", 1)[0] + "." + tok)
        return obf_to_readable(obf_fqn, fwd)

    votes = {}
    # 每行含幻影名的 03 锚点: 声明 P var / 强转 ((P)var)
    for line in lines3:
        if phantom not in line:
            continue
        tokens3 = set(re.findall(r"[A-Za-z_$][\w$]*", line)) - {phantom}
        anchors = re.findall(rf"\b{re.escape(phantom)}\s+(\w+)\s*[=;,\[\]]", line)
        anchors += re.findall(rf"\(\s*\(\s*{re.escape(phantom)}\s*\)\s*(\w+)\s*\)", line)
        for a in anchors:
            best_score, best_tok = 0, None
            # 02 中声明同锚点变量的行, 按行重叠度选最佳 (跨方法同名变量防误配)
            for l2 in lines2:
                m = re.search(rf"([\w.]+)\s+{re.escape(a)}\s*[=;,\[\]]", l2)
                if not m:
                    continue
                tokens2 = set(re.findall(r"[A-Za-z_$][\w$]*", l2))
                score = len(tokens3 & tokens2)
                if score > best_score:
                    best_score, best_tok = score, m.group(1)
            if best_tok and best_score >= 1:
                meaning = tok_meaning(best_tok)
                if meaning:
                    votes[meaning] = votes.get(meaning, 0) + best_score

    # 投票过滤: 只保留字节码引用集证明的候选
    votes = {m: v for m, v in votes.items() if m in cand_meanings}
    report["votes"] = {k.rsplit(".", 1)[-1]: v for k, v in votes.items()}

    winner = max(votes, key=votes.get) if votes else None
    if not winner:
        report["note"] = "无锚点投票 (需人工)"
        return report
    report["winner"] = winner

    # 回退: 全文件 P → 胜者简单名
    simple = winner.rsplit(".", 1)[-1]
    new_content, n = re.subn(rf"(?<![\w$.]){re.escape(phantom)}(?![\w$])",
                             simple, content3)
    report["replaced"] = n
    if simple == phantom:
        report["note"] = "胜者与幻影同名 (无需改)"
        return report
    # 补 import (若胜者包 ≠ 本文件包 且未导入)
    pkg3 = re.search(r"^package\s+([\w.]+)\s*;", new_content, re.MULTILINE)
    pkg3 = pkg3.group(1) if pkg3 else ""
    if winner.rsplit(".", 1)[0] != pkg3 and not re.search(
            rf"^import\s+{re.escape(winner)}\s*;", new_content, re.MULTILINE):
        new_content = re.sub(r"(^package\s+[\w.]+\s*;)",
                             rf"\g<1>\nimport {winner};", new_content,
                             count=1, flags=re.MULTILINE)
    if not dry_run:
        three_file.write_text(new_content, encoding="utf-8")
    return report


def apply_manual(rel, winner, phantom, dry_run=False):
    """按手工裁决表替换文件内全部幻影名 (不依赖身份/锚点)。"""
    jf = DEOBFUSCATED_DIR / rel
    if not jf.exists():
        return {"file": rel, "note": "文件不存在"}
    content = jf.read_text(encoding="utf-8", errors="replace")
    simple = winner.rsplit(".", 1)[-1]
    new_content, n = re.subn(rf"(?<![\w$.]){re.escape(phantom)}(?![\w$])",
                             simple, content)
    pkg3 = re.search(r"^package\s+([\w.]+)\s*;", new_content, re.MULTILINE)
    pkg3 = pkg3.group(1) if pkg3 else ""
    if winner.rsplit(".", 1)[0] != pkg3 and not re.search(
            rf"^import\s+{re.escape(winner)}\s*;", new_content, re.MULTILINE):
        new_content = re.sub(r"(^package\s+[\w.]+\s*;)",
                             rf"\g<1>\nimport {winner};", new_content,
                             count=1, flags=re.MULTILINE)
    if not dry_run:
        jf.write_text(new_content, encoding="utf-8")
    return {"file": rel, "winner": winner, "replaced": n}


def main():
    dry_run = "--dry-run" in sys.argv
    fwd, rev, refs = load_indexes()

    # ── 手工裁决表: --manual <文件相对路径>=<胜者FQN> (可多次) ──
    manual = {}
    excludes = set()
    for i, a in enumerate(sys.argv):
        if a == "--manual" and i + 1 < len(sys.argv):
            rel, winner = sys.argv[i + 1].split("=", 1)
            manual[rel.replace("\\", "/")] = winner
        if a == "--exclude-file" and i + 1 < len(sys.argv):
            excludes.add(sys.argv[i + 1].replace("\\", "/"))

    # ── 幻影名回退 ──
    phantoms = []
    for i, a in enumerate(sys.argv):
        if a == "--fix-phantom" and i + 1 < len(sys.argv):
            phantoms.append(sys.argv[i + 1])

    for phantom in phantoms:
        print(f"\n=== 幻影名回退: {phantom} ===")
        for jf in sorted(DEOBFUSCATED_DIR.rglob("*.java")):
            rel = jf.relative_to(DEOBFUSCATED_DIR).as_posix()
            content = jf.read_text(encoding="utf-8", errors="replace")
            if phantom not in content:
                continue
            if rel in excludes:
                print(f"  {rel}: 排除 (真引用)")
                continue
            # 键归一: 兼容带/不带 com/corrodinggames/rts/ 前缀的手工表键
            rel_short = rel.replace("com/corrodinggames/rts/", "", 1)
            mkey = rel if rel in manual else (rel_short if rel_short in manual else None)
            if mkey:
                rep = apply_manual(rel, manual[mkey], phantom, dry_run=dry_run)
                print(f"  {rel}: MANUAL winner={rep['winner']} replaced={rep['replaced']}")
                continue
            rep = resolve_phantom_file(jf, phantom, fwd, rev, refs, dry_run=dry_run)
            print(f"  {rep['file']}: winner={rep['winner']} "
                  f"replaced={rep['replaced']} votes={rep.get('votes')} "
                  f"{rep.get('note', '')}")

    # ── 真丢失类恢复 ──
    restores = []
    for i, a in enumerate(sys.argv):
        if a == "--restore" and i + 2 < len(sys.argv):
            restores.append((sys.argv[i + 1].replace("\\", "/"),
                             sys.argv[i + 2].replace("\\", "/")))
    for src_rel, dst_rel in restores:
        print(f"\n=== 恢复: {src_rel} -> {dst_rel} ===")
        content, rep = rename_02_file(src_rel, dst_rel, fwd, dry_run=dry_run)
        print(" ", json.dumps(rep, ensure_ascii=False))

    sys.exit(0)


if __name__ == "__main__":
    main()
