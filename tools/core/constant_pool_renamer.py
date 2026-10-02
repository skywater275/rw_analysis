#!/usr/bin/env python3
"""
常量池驱动重命名器 — 确定性重建 (R1/R3) 的核心引擎。

对单个文件执行字节码确定性重命名:
1. 用类映射表 (fwd) 重写包声明/类声明/extends/implements
2. import 块四选一: 混淆FQN∈fwd→改写; ∉fwd→保留; 可读但前身∉引用集→标注
3. 类型 token 重写: import 锚定规则 (被 import 的混淆简单名 → 可读名)
4. $N 内部类名跟随父类映射 (y$1 → BaseUnit$1)

铁律: 只替换由 fwd 表 + 该文件常量池引用集共同证明的 token, 禁止猜测。

Usage: python tools/core/constant_pool_renamer.py --file <02相对路径> --target <03相对路径> [--dry-run]
"""
import json
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import (DECOMPILED_DIR, DEOBFUSCATED_DIR, GENERATED_DIR,
                          MAPPINGS_DIR)
from rwlib.bytecode import extract_class_refs
from tools.fixers.package_renamer import PKG_MAP

IDENTITY_JSON = GENERATED_DIR / "identity-index.json"
CLASS_REFS_JSON = GENERATED_DIR / "class-refs.json"


def load_indexes():
    """加载身份索引 (R0 产物): fwd, rev, refs。"""
    idx = json.loads(IDENTITY_JSON.read_text(encoding="utf-8"))
    refs = json.loads(CLASS_REFS_JSON.read_text(encoding="utf-8"))
    return idx["fwd"], idx["rev"], refs


def obf_to_readable(obf_fqn, fwd):
    """混淆FQN → 意义FQN; $N 内部类跟随父类映射 (y$1 → BaseUnit$1)。"""
    if obf_fqn in fwd:
        return fwd[obf_fqn]
    # 内部类: 父类映射 + $后缀
    if "$" in obf_fqn:
        parent, suffix = obf_fqn.split("$", 1)
        if parent in fwd:
            return fwd[parent] + "$" + suffix
    return None


def rename_02_file(src_rel, dst_rel, fwd, dry_run=False):
    """
    将 02-decompiled 的混淆名源文件重命名为 03 目标 (确定性)。

    返回: (new_content, report_dict) — report 记录每类变更数。
    """
    src = DECOMPILED_DIR / src_rel
    dst = DEOBFUSCATED_DIR / dst_rel
    if not src.exists():
        return None, {"error": f"02 源不存在: {src_rel}"}
    content = src.read_text(encoding="utf-8", errors="replace")
    report = {"src": src_rel, "dst": dst_rel,
              "pkg": 0, "class": 0, "imports": 0, "tokens": 0, "inner": 0,
              "unmapped_imports": []}

    # 源文件自身身份: 02 路径 → 混淆FQN (03 目标给出意义身份)
    obf_fqn = src_rel.replace("/", ".").replace(".java", "")
    meaning_fqn = dst_rel.replace("/", ".").replace(".java", "")
    meaning_pkg, meaning_cls = meaning_fqn.rsplit(".", 1)
    obf_pkg, obf_cls = obf_fqn.rsplit(".", 1)

    # ── Step 1: 包声明 ──
    new_content, n = re.subn(rf"(^package\s+){re.escape(obf_pkg)}(\s*;)",
                             rf"\g<1>{meaning_pkg}\g<2>", content, count=1, flags=re.MULTILINE)
    report["pkg"] = n

    # ── Step 2: 类声明 + 构造器名 + extends/implements ──
    new_content, n = re.subn(
        rf"\b(class|interface|enum|@interface)\s+{re.escape(obf_cls)}\b",
        rf"\g<1> {meaning_cls}", new_content)
    report["class"] = n
    # 构造器名 (与类同名的方法, CFR 生成的构造器)
    new_content, n = re.subn(
        rf"(\b(?:public|protected|private|static|final|\s)*)\b{re.escape(obf_cls)}\s*\(",
        rf"\g<1>{meaning_cls}(", new_content)
    report["class"] += n
    # 内部类自引用 (new y$1( → new BaseUnit$1()
    for m in re.finditer(rf"\b{re.escape(obf_cls)}(\$\d+(?:\$\d+)*)\b", new_content):
        pass  # $N 引用在 token 步统一处理

    # ── Step 3: import 块重写 ──
    # 收集本文件的 import FQN 列表 (混淆简单名 → FQN)
    import_lines = []
    for line in new_content.split("\n"):
        m = re.match(r"^\s*import\s+(?:static\s+)?([\w.]+)\s*;", line)
        if m:
            import_lines.append(m.group(1))
    # 构建: 混淆简单名 → (混淆FQN, 意义FQN或None)
    simple_map = {}   # simple -> (obf_fqn, meaning_fqn)
    for imp in import_lines:
        simple = imp.rsplit(".", 1)[-1]
        meaning = obf_to_readable(imp, fwd)
        if meaning:
            simple_map[simple] = (imp, meaning)
    for imp in import_lines:
        meaning = obf_to_readable(imp, fwd)
        if meaning and meaning != imp:
            new_content, n = re.subn(rf"(\bimport\s+){re.escape(imp)}(\s*;)",
                                     rf"\g<1>{meaning}\g<2>", new_content, count=1)
            report["imports"] += n
        elif not meaning and not imp.startswith(("java.", "javax.", "android.",
                                                 "com.codedisaster.", "org.",
                                                 "com.badlogic.", "network.",
                                                 "slick.", "lwjgl.")):
            report["unmapped_imports"].append(imp)

    # ── Step 4: 类型 token 重写 (import 锚定规则, 仅类型位置) ──
    # 被 import 且已映射的混淆简单名 → 可读名
    # 类型位置判定 (防误伤成员名/局部变量名/关键字/长词前缀):
    #   a. 前接 new/instanceof/extends/implements 关键字 (定宽后视)
    #   b. 后接 空白+标识符 或 [ (声明位: T var / T[] var / 返回类型 T name)
    # 排除: this.x/super.x/.x 成员访问; "Socket d;" 中 d 后接 ; 不匹配;
    #       "final"/"new"/"import com" 等 — 要求空白分隔, 零宽连续不匹配
    for simple, (obf_imp, meaning) in sorted(simple_map.items(), key=lambda kv: -len(kv[0])):
        meaning_simple = meaning.rsplit(".", 1)[-1]
        if meaning_simple == simple:
            continue  # 名字未变
        # 各关键字后视均为定宽 (new\s/instanceof\s/extends\s/implements\s)
        kw_pat = (rf"(?<![\w$.\x5c])(?:(?<=new\s)|(?<=instanceof\s)|(?<=extends\s)|(?<=implements\s))"
                  rf"{re.escape(simple)}(?![\w$])")
        decl_pat = rf"(?<![\w$.\x5c]){re.escape(simple)}(?=\s+[A-Za-z_$][\w$]*|\s*\[)"
        new_content, n1 = re.subn(kw_pat, meaning_simple, new_content)
        new_content, n2 = re.subn(decl_pat, meaning_simple, new_content)
        report["tokens"] += n1 + n2

    # 自类引用: 类型位置的 obf_cls → meaning_cls (字段声明/返回类型)
    new_content, n = re.subn(
        rf"(?<![\w$.\x5c]){re.escape(obf_cls)}(?=\s+[A-Za-z_$][\w$]*|\s*\[)",
        meaning_cls, new_content)
    report["tokens"] += n

    # CFR 点号内类形态: 父类混淆名.N → 父类意义名$N (h.2 → Factory$2)
    # 本文件为内部类时用其父类; 否则用自身 (h.N 形式也可能指本类内部类)
    dot_pairs = []
    if "$" in obf_cls:
        parent_obf = obf_cls.split("$")[0]
        parent_meaning = obf_to_readable(obf_pkg + "." + parent_obf, fwd)
        if parent_meaning:
            dot_pairs.append((parent_obf, parent_meaning.rsplit(".", 1)[-1]))
    dot_pairs.append((obf_cls, meaning_cls))
    for dot_obf, dot_meaning in dot_pairs:
        new_content, n = re.subn(
            rf"(?<![\w$.]){re.escape(dot_obf)}\.(\d+)(?![\w$])",
            rf"{dot_meaning}$\1", new_content)
        report["tokens"] += n

    # ── Step 5: 写目标 (03) ──
    if not dry_run:
        dst.parent.mkdir(parents=True, exist_ok=True)
        dst.write_text(new_content, encoding="utf-8")
    return new_content, report


def rename_03_file_inplace(jf, obf, fwd, refs, dry_run=False):
    """
    03 就地重写 (R3 主 pass) — 对已有 03 文件做体部类型 token 确定性重写。

    四路规则 (字节码证明 + 02 import 锚定):
    1. 常量池 FQN 重写: 体内出现的 refs∩fwd 混淆FQN → 意义FQN (限定名引用)
    2. 裸 token 锚定: 02 对应文件的 import 表给出该简单名的唯一混淆FQN → 意义名
       (Java 语义: 裸简单名至多对应一个 import, CFR 对多义用限定名)
    3. 同包引用: 简单名在 refs 中唯一映射 → 重写
    4. 自类引用: obf_cls → meaning_cls
    类型位置: new/instanceof/extends/implements 后、声明位 (空白+标识符 或 [)、
    强转位 (T) 后接非 [),;] — 防误伤成员名/局部变量/关键字。

    返回 report dict。歧义/跳过写入 report["skipped"]。
    """
    rel = jf.relative_to(DEOBFUSCATED_DIR).as_posix()
    fqn = rel.replace(".java", "").replace("/", ".")
    meaning_fqn = fwd.get(obf, fqn)
    meaning_pkg, meaning_cls = meaning_fqn.rsplit(".", 1)
    obf_pkg, obf_cls = obf.rsplit(".", 1)
    report = {"file": rel, "fqn": 0, "tokens": 0, "self": 0,
              "imports_added": [], "skipped": []}

    content = jf.read_text(encoding="utf-8", errors="replace")
    lines = content.split("\n")
    # 现有 import 集 (简单名 → FQN)
    cur_imports = {}
    for line in lines:
        m = re.match(r"^import\s+(?:static\s+)?([\w.]+)\s*;", line)
        if m:
            cur_imports[m.group(1).rsplit(".", 1)[-1]] = m.group(1)

    # 引用集 M: {混淆FQN: 意义FQN}
    M = {}
    for r in refs.get(obf, {}).get("refs", []):
        mr = obf_to_readable(r, fwd)
        if mr and mr != meaning_fqn:
            M[r] = mr
    if not M:
        return report

    # ── 规则1: FQN 重写 (体内限定名) ──
    # 词边界守卫: r 后必须接非标识符字符 (防 gameFramework.f 命中 filesystem 前缀)
    for r, mr in sorted(M.items(), key=lambda kv: -len(kv[0])):
        if r not in content:
            continue
        content, n = re.subn(rf"{re.escape(r)}(?![A-Za-z0-9_])", mr, content)
        report["fqn"] += n

    # ── 规则2+3+4: 裸 token 锚定 ──
    # 02 import 表 (同名文件), 映射: 简单名 → 意义FQN
    src02 = DECOMPILED_DIR / (obf.replace(".", "/") + ".java")
    anchor = {}
    if src02.exists():
        for line in src02.read_text(encoding="utf-8", errors="replace").split("\n"):
            m = re.match(r"^\s*import\s+(?:static\s+)?([\w.]+)\s*;", line)
            if m:
                imp = m.group(1)
                mr = obf_to_readable(imp, fwd)
                if mr:
                    anchor[imp.rsplit(".", 1)[-1]] = mr
    # 同包唯一引用
    for r, mr in M.items():
        simple = r.rsplit(".", 1)[-1]
        if simple in anchor:
            continue
        if sum(1 for rr in M if rr.rsplit(".", 1)[-1] == simple) == 1:
            anchor.setdefault(simple, mr)

    def rewrite_token(simple, mr):
        nonlocal content
        ms = mr.rsplit(".", 1)[-1]
        # 冲突检查: 目标简单名已被另一个 FQN import
        if ms in cur_imports and cur_imports[ms] != mr:
            report["skipped"].append(f"{simple}->{ms}: import冲突 {cur_imports[ms]}")
            return
        kw_pat = (rf"(?<![\w$.\x5c])(?:(?<=new\s)|(?<=instanceof\s)|(?<=extends\s)|(?<=implements\s))"
                  rf"{re.escape(simple)}(?![\w$])")
        decl_pat = rf"(?<![\w$.\x5c]){re.escape(simple)}(?=\s+[A-Za-z_$][\w$]*|\s*\[)"
        # 只替换括号内的 token, 保留括号: (?<=\() T ([])? (?=\)) 且括号后非 [),;.]
        cast_pat = (rf"(?<=\()\s*{re.escape(simple)}(?:\s*\[\s*\])?\s*"
                    rf"(?=\)\s*(?!\s*[),;.]))")
        n1 = len(re.findall(kw_pat, content))
        try:
            n2 = len(re.findall(decl_pat, content))
        except re.error:
            print(f"  [DEBUG] decl_pat 编译失败: simple={simple!r} pat={decl_pat!r}")
            raise
        n3 = len(re.findall(cast_pat, content))
        if n1 + n2 + n3 == 0:
            return
        content = re.sub(kw_pat, ms, content)
        content = re.sub(decl_pat, ms, content)
        content = re.sub(cast_pat, ms, content)
        report["tokens"] += n1 + n2 + n3
        # 补 import (不同包且未导入)
        if mr.rsplit(".", 1)[0] != meaning_pkg and ms not in cur_imports:
            report["imports_added"].append(mr)

    for simple, mr in sorted(anchor.items(), key=lambda kv: -len(kv[0])):
        rewrite_token(simple, mr)
    # 自类引用
    rewrite_token(obf_cls, meaning_fqn)

    # 写回 (补 import)
    if report["imports_added"]:
        for imp in sorted(set(report["imports_added"])):
            m = re.search(r"^package\s+[\w.]+\s*;", content, re.MULTILINE)
            if m:
                content = content[:m.end()] + "\nimport " + imp + ";" + content[m.end():]
    if not dry_run:
        jf.write_text(content, encoding="utf-8")
    return report


def revert_03_file_inplace(jf, obf, fwd, refs, dry_run=False):
    """
    逆操作 (--tree-revert): 将 R3 的 kw/decl/FQN 重写逆转回混淆名。
    cast 损伤位点 (ms 紧跟非空白) 不被逆模式匹配 — 留待修复版规则重放。
    补录的 import 行保留 (无害)。
    """
    fqn = jf.relative_to(DEOBFUSCATED_DIR).as_posix().replace(".java", "").replace("/", ".")
    meaning_fqn = fwd.get(obf, fqn)
    meaning_cls = meaning_fqn.rsplit(".", 1)[-1]
    obf_cls = obf.rsplit(".", 1)[-1]
    report = {"file": fqn, "fqn": 0, "tokens": 0}

    content = jf.read_text(encoding="utf-8", errors="replace")
    M = {}
    for r in refs.get(obf, {}).get("refs", []):
        mr = obf_to_readable(r, fwd)
        if mr and mr != meaning_fqn:
            M[r] = mr
    if not M:
        return report
    # FQN 回退 (后应用, 先做 token 回退避免互相干扰)
    anchor = {}
    src02 = DECOMPILED_DIR / (obf.replace(".", "/") + ".java")
    if src02.exists():
        for line in src02.read_text(encoding="utf-8", errors="replace").split("\n"):
            m = re.match(r"^\s*import\s+(?:static\s+)?([\w.]+)\s*;", line)
            if m:
                mr = obf_to_readable(m.group(1), fwd)
                if mr:
                    anchor[m.group(1).rsplit(".", 1)[-1]] = mr
    for r, mr in M.items():
        simple = r.rsplit(".", 1)[-1]
        if simple in anchor:
            continue
        if sum(1 for rr in M if rr.rsplit(".", 1)[-1] == simple) == 1:
            anchor.setdefault(simple, mr)
    for simple, mr in sorted(anchor.items(), key=lambda kv: -len(kv[0])):
        ms = mr.rsplit(".", 1)[-1]
        kw_pat = (rf"(?<![\w$.\x5c])(?:(?<=new\s)|(?<=instanceof\s)|(?<=extends\s)|(?<=implements\s))"
                  rf"{re.escape(ms)}(?![\w$])")
        decl_pat = rf"(?<![\w$.\x5c]){re.escape(ms)}(?=\s+[A-Za-z_$][\w$]*|\s*\[)"
        n1 = len(re.findall(kw_pat, content))
        n2 = len(re.findall(decl_pat, content))
        content = re.sub(kw_pat, simple, content)
        content = re.sub(decl_pat, simple, content)
        report["tokens"] += n1 + n2
    ms_self = meaning_cls
    kw_pat = (rf"(?<![\w$.\x5c])(?:(?<=new\s)|(?<=instanceof\s)|(?<=extends\s)|(?<=implements\s))"
              rf"{re.escape(ms_self)}(?![\w$])")
    decl_pat = rf"(?<![\w$.\x5c]){re.escape(ms_self)}(?=\s+[A-Za-z_$][\w$]*|\s*\[)"
    content = re.sub(kw_pat, obf_cls, content)
    content = re.sub(decl_pat, obf_cls, content)
    # FQN 回退
    for r, mr in sorted(M.items(), key=lambda kv: -len(kv[1])):
        n = content.count(mr)
        content = content.replace(mr, r)
        report["fqn"] += n
    if not dry_run:
        jf.write_text(content, encoding="utf-8")
    return report


def main():
    dry_run = "--dry-run" in sys.argv
    tree_mode = "--tree" in sys.argv
    revert_mode = "--tree-revert" in sys.argv
    fwd, rev, refs = load_indexes()

    if tree_mode or revert_mode:
        # ── R3 主 pass / 逆操作: 全树 03 就地重写 ──
        total = {"fqn": 0, "tokens": 0, "self": 0, "files": 0}
        # 逆包映射: 意义包 → 混淆包 (obf 命名文件的身份推导)
        rev_pkg = sorted(((mp, op) for op, mp in PKG_MAP.items()), key=lambda x: -len(x[0]))
        def to_obf_pkg(mp):
            for m, o in rev_pkg:
                if mp == m or mp.startswith(m + "."):
                    return o + mp[len(m):]
            return mp
        for jf in sorted(DEOBFUSCATED_DIR.rglob("*.java")):
            rel = jf.relative_to(DEOBFUSCATED_DIR).as_posix()
            fqn = rel.replace(".java", "").replace("/", ".")
            obf = rev.get(fqn)
            if not obf and not revert_mode:
                # obf 命名文件: 逆包映射推导混淆身份, 02 文件存在性校验
                pkg, simple = fqn.rsplit(".", 1)
                obf_guess = to_obf_pkg(pkg) + "." + simple
                if (DECOMPILED_DIR / (obf_guess.replace(".", "/") + ".java")).exists():
                    obf = obf_guess
            if not obf:
                continue
            if revert_mode:
                rep = revert_03_file_inplace(jf, obf, fwd, refs, dry_run=dry_run)
            else:
                rep = rename_03_file_inplace(jf, obf, fwd, refs, dry_run=dry_run)
                if rep.get("skipped"):
                    print(f"  跳过: {rel}: {rep['skipped']}")
            if rep["fqn"] + rep["tokens"]:
                total["files"] += 1
                total["fqn"] += rep["fqn"]
                total["tokens"] += rep["tokens"]
        print(f"全树({'逆操作' if revert_mode else '主pass'}): {total['files']} 文件, "
              f"FQN {total['fqn']} + token {total['tokens']}" +
              (" (DRY RUN)" if dry_run else ""))
        sys.exit(0)

    src_rel = dst_rel = None
    for i, a in enumerate(sys.argv):
        if a == "--file" and i + 1 < len(sys.argv):
            src_rel = sys.argv[i + 1].replace("\\", "/")
        if a == "--target" and i + 1 < len(sys.argv):
            dst_rel = sys.argv[i + 1].replace("\\", "/")
    if not src_rel or not dst_rel:
        print("用法: python tools/core/constant_pool_renamer.py --file <02相对路径> --target <03相对路径> [--dry-run]")
        print("       python tools/core/constant_pool_renamer.py --tree [--dry-run]  # R3 主 pass")
        sys.exit(1)

    content, report = rename_02_file(src_rel, dst_rel, fwd, dry_run=dry_run)
    if content is None:
        print(f"错误: {report['error']}")
        sys.exit(1)
    print(json.dumps(report, ensure_ascii=False, indent=1))
    sys.exit(0)


if __name__ == "__main__":
    main()
