#!/usr/bin/env python3
"""
成员级合并恢复 — 为 19 个"重写丢失"文件补回 02 锚点内容。

对每个文件对 (02 混淆原稿, 03 现状):
1. 用 constant_pool_renamer.rename_02_file 全文件确定性语义化 (dry_run, 不写盘)
   — 期间 monkey-patch obf_to_readable 应用误标覆盖 (gameFramework.f → GameUtils)
2. 对语义化文本补跑 R3 规则: 体内 FQN 重写 (保护集 + 最长后缀, 不碰 import 行)
   + 强转位 (cast) 重写
3. 按成员粒度 (字段/方法/构造器/静态块) 解析 02 与 03 双方
4. 匹配: 方法按 (static, 参数个数, 参数类型FQN元组, 返回类型FQN) 及
   (名字, 参数个数) 及体首行指纹; 字段按 (名字, static) — 匹配到的保留 03 版,
   未匹配的 02 成员逐字追加
5. 追加位: 字段/静态块 → 类头字段区; 方法 → 类体最后一个 } 之前
6. import: 仅补追加文本在类型位置用到的、语义化且 03 树存在对应文件的缺失项

铁律: 追加内容逐行来自 02; 成员名/字段名保持 02 混淆名; 只写 19 个目标文件。
每个文件处理完立即写盘。

Usage: python tools/core/merge_members_19.py [--dry-run] [--only <03rel>]
"""
import re
import sys
import time
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import DECOMPILED_DIR, DEOBFUSCATED_DIR
import tools.core.constant_pool_renamer as cpr

_ORIG_OBF_TO_READABLE = cpr.obf_to_readable

# ── 已知 fwd 误标覆盖 (以 03 树现存文件身份为准) ──
OVERRIDES = {
    # fwd 把 gameFramework.f 标为 GameRenderer, 但 03 树实际身份是 GameUtils.java
    "com.corrodinggames.rts.gameFramework.f":
        "com.corrodinggames.rts.gameFramework.GameUtils",
}


def obf_to_readable_ov(obf_fqn, fwd):
    """fwd + 误标覆盖。"""
    if obf_fqn in OVERRIDES:
        return OVERRIDES[obf_fqn]
    if "$" in obf_fqn:
        parent, suffix = obf_fqn.split("$", 1)
        if parent in OVERRIDES:
            return OVERRIDES[parent] + "$" + suffix
    return _ORIG_OBF_TO_READABLE(obf_fqn, fwd)


PAIRS = [
    ("game/n.java", "game/PlayerState.java"),
    ("game/a/i.java", "game/ai/CombatMain.java"),
    ("game/b/b.java", "game/map/TileEntry.java"),
    ("game/units/custom/bn.java", "game/units/custom/ModUnitLoader.java"),
    ("game/units/custom/ba.java", "game/units/custom/TraitValueBuilder.java"),
    ("game/units/custom/f.java", "game/units/custom/UnitParameter.java"),
    ("gameFramework/e.java", "gameFramework/Command.java"),
    ("gameFramework/ba.java", "gameFramework/ReplayEngine.java"),
    ("gameFramework/d/e.java", "gameFramework/effects/HUDElement.java"),
    ("gameFramework/d/c.java", "gameFramework/effects/HUDManager.java"),
    ("gameFramework/e/a.java", "gameFramework/filesystem/FileLoader.java"),
    ("gameFramework/e/c.java", "gameFramework/filesystem/StorageBackend.java"),
    ("gameFramework/j/n.java", "gameFramework/network/WebAPIClient.java"),
    ("gameFramework/b/n.java", "gameFramework/opengl/GLRenderer.java"),
    ("gameFramework/k/d.java", "gameFramework/pathfinding/AStarSearch.java"),
    ("gameFramework/m/aa.java", "gameFramework/rendering/FontRenderer.java"),
    ("gameFramework/f/g.java", "gameFramework/ui/InGameUI.java"),
    ("gameFramework/f/ae.java", "gameFramework/ui/ThemeColors.java"),
    ("gameFramework/f/al.java", "gameFramework/ui/UnitRenderer.java"),
]

KEYWORDS = {
    "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
    "class", "const", "continue", "default", "do", "double", "else", "enum",
    "extends", "final", "finally", "float", "for", "goto", "if", "implements",
    "import", "instanceof", "int", "interface", "long", "native", "new",
    "package", "private", "protected", "public", "return", "short", "static",
    "strictfp", "super", "switch", "synchronized", "this", "throw", "throws",
    "transient", "try", "void", "volatile", "while", "true", "false", "null",
}

THIRD_PARTY_PREFIXES = (
    "android.", "com.codedisaster.", "com.badlogic.", "org.", "network.",
    "slick.", "lwjgl.", "java.", "javax.", "jdk.", "sun.",
)

CLASS_DECL_RE = re.compile(
    r"(?m)^[ \t]*(?:(?:public|protected|private|abstract|final|strictfp|static)"
    r"[ \t]+)*(?:class|interface|enum|@interface)[ \t]+\w+")


# ── 词法扫描 (字符串/注释/字符字面量安全) ───────────────────────────

def scan_java(text):
    """返回 token 事件: ('{', i), ('}', i), (';', i), ('(', i), (')', i), (',', i)
    跳过字符串/字符/注释内容。"""
    i, n = 0, len(text)
    out = []
    while i < n:
        c = text[i]
        if c == "/" and i + 1 < n and text[i + 1] == "/":
            j = text.find("\n", i)
            i = n if j < 0 else j + 1
            continue
        if c == "/" and i + 1 < n and text[i + 1] == "*":
            j = text.find("*/", i + 2)
            i = n if j < 0 else j + 2
            continue
        if c in "\"'":
            q = c
            j = i + 1
            while j < n:
                if text[j] == "\\":
                    j += 2
                    continue
                if text[j] == q:
                    break
                j += 1
            i = j + 1
            continue
        if c in "{}(),;":
            out.append((c, i))
        i += 1
    return out


def split_members(text):
    """把 Java 类文件切分为:
    return (preamble, header_end_pos, members, close_pos)
    members = [(kind, start, end)]  kind: 'field'|'method'|'ctor'|'static'|'instance'
    close_pos = 类体结束 } 的位置
    """
    m = CLASS_DECL_RE.search(text)
    if not m:
        return text, 0, [], len(text)
    events = scan_java(text)
    open_pos = None
    for c, i in events:
        if c == "{" and i > m.start():
            open_pos = i
            break
    if open_pos is None:
        return text, len(text), [], len(text)
    members = []
    cur = open_pos + 1
    depth = 1
    ev = [e for e in events if e[1] > open_pos]
    k, n = 0, len(ev)
    while k < n:
        c, pos = ev[k]
        if depth == 1:
            if c == ";":
                seg = text[cur:pos + 1]
                members.append((classify_member(seg), cur, pos + 1))
                cur = pos + 1
                k += 1
                continue
            if c == "{":
                head = text[cur:pos]
                head_flat = re.sub(r"\s+", " ", head).strip()
                is_block = False
                if head_flat == "" or head_flat == "static":
                    is_block = True  # 静态/实例初始化块
                elif "(" in head_flat:
                    is_block = True  # 方法体
                if is_block:
                    d = 1
                    k2 = k + 1
                    close = None
                    while k2 < n:
                        c2, p2 = ev[k2]
                        if c2 == "{":
                            d += 1
                        elif c2 == "}":
                            d -= 1
                            if d == 0:
                                close = p2
                                k2 += 1
                                break
                        k2 += 1
                    if close is None:
                        break
                    seg_end = close + 1
                    if k2 < n and ev[k2][0] == ";":
                        seg_end = ev[k2][1] + 1
                        k2 += 1
                    members.append((classify_member(text[cur:seg_end]), cur, seg_end))
                    cur = seg_end
                    k = k2
                    continue
                depth += 1  # 字段初始化大括号
                k += 1
                continue
            if c == "}":
                members.append(("close", cur, pos))
                return text, open_pos + 1, members, pos
            k += 1
        else:
            if c == "{":
                depth += 1
            elif c == "}":
                depth -= 1
            k += 1
    return text, open_pos + 1, members, len(text)


def strip_annotations(text):
    return re.sub(r"@[A-Za-z_$][\w$]*(?:\([^)]*\))?", " ", text)


def classify_member(seg):
    head = seg.split("{", 1)[0] if "{" in seg else seg
    head_flat = re.sub(r"\s+", " ", strip_annotations(head)).strip()
    if head_flat in ("static", "") or head_flat.endswith(" static"):
        return "static"
    if re.search(r"\bclass\b", head_flat):
        return "nested"
    # 字段初始化器里的 ( 不算方法参数表: = 在第一个 ( 之前 → 字段
    fp = head_flat.find("(")
    eq = head_flat.find("=")
    if fp >= 0 and (eq < 0 or eq > fp):
        return "method"
    return "field"


def member_head_info(seg, import_table, pkg):
    """提取成员头部信息: {kind, name, static, abstract, arity, params, ret}。"""
    body_start = seg.find("{")
    head = seg[:body_start] if body_start >= 0 else seg
    head = head.rstrip()
    if head.endswith(";"):
        head = head[:-1]
    flat = re.sub(r"\s+", " ", strip_annotations(head)).strip()
    static = bool(re.search(r"\bstatic\b", flat))
    abstract = bool(re.search(r"\babstract\b", flat))
    fp = flat.find("(")
    eq = flat.find("=")
    pm = re.search(r"([A-Za-z_$][\w$]*)\s*\(", flat) if (fp >= 0 and (eq < 0 or eq > fp)) else None
    if pm:
        name = pm.group(1)
        open_idx = flat.find("(")
        close_idx = flat.rfind(")")
        params = flat[open_idx + 1:close_idx] if close_idx > open_idx else ""
        arity = 0
        param_fqns = []
        if params.strip():
            parts = split_params(params.strip())
            arity = len(parts)
            for p in parts:
                param_fqns.append(type_fqn(p, import_table, pkg))
        prefix = flat[:pm.start()].strip()
        ret = None
        if prefix:
            toks = [t for t in re.split(r"\s+", prefix) if t and t not in
                    ("public", "protected", "private", "static", "final",
                     "strictfp", "abstract", "synchronized", "native",
                     "transient", "volatile")]
            if toks:
                ret = type_fqn(" ".join(toks), import_table, pkg)
        return {"kind": "method", "name": name, "static": static,
                "abstract": abstract, "arity": arity,
                "params": tuple(param_fqns), "ret": ret}
    # 字段: 取首个 = 之前 (或 ; 之前) 的最后一个标识符
    name_part = flat.split("=", 1)[0].strip()
    nm = re.findall(r"[A-Za-z_$][\w$]*", name_part)
    name = nm[-1] if nm else None
    return {"kind": "field", "name": name, "static": static,
            "arity": 0, "params": (), "ret": None}


def split_params(params):
    parts = []
    depth = 0
    cur = []
    for ch in params:
        if ch in "([<":
            depth += 1
        elif ch in ")]>":
            depth -= 1
        if ch == "," and depth == 0:
            parts.append("".join(cur).strip())
            cur = []
        else:
            cur.append(ch)
    if "".join(cur).strip():
        parts.append("".join(cur).strip())
    return parts


def type_fqn(t, import_table, pkg):
    """类型文本 → 意义 FQN (近似)。"""
    t = t.strip()
    if not t:
        return None
    m = re.match(r"^([\w.$]+)<", t)
    if m:
        t = m.group(1)
    if t.endswith("..."):
        t = t[:-3].strip()
    if t.endswith("[]"):
        return type_fqn(t[:-2].strip(), import_table, pkg) + "[]"
    if t in KEYWORDS:
        return t
    if "$" in t or "." in t:
        return t
    fqn = import_table.get(t)
    if fqn:
        return fqn
    return pkg + "." + t if pkg else t


def load_import_table(text):
    tbl = {}
    for line in text.split("\n"):
        m = re.match(r"^\s*import\s+(?:static\s+)?([\w.$]+)\s*;", line)
        if m:
            tbl[m.group(1).rsplit(".", 1)[-1]] = m.group(1)
    return tbl


def body_first_line(seg):
    """方法体首行指纹 (规范化)。"""
    bi = seg.find("{")
    if bi < 0:
        return ""
    rest = seg[bi + 1:]
    line = rest.split("\n", 1)[0].strip()
    return re.sub(r"\s+", " ", line).strip()[:60]


def body_tokens(text):
    return re.findall(r"[A-Za-z_$][\w$]*|\d+(?:\.\d+)?", text)


def match_overlap(t2, t3):
    if not t2:
        return 0.0
    s2, s3 = set(t2), set(t3)
    return len(s2 & s3) / len(s2)


# ── R3 风格补充规则 (作用于语义化 02 全文) ──────────────────────────

def r3_extra(content2, src02_text, obf_fqn, meaning_fqn, fwd, refs):
    """补跑: 体内 FQN 重写 (保护集+最长后缀, 跳过 import 行) + 强转位重写。"""
    rep = {"fqn": 0, "cast": 0}
    M = {}
    for r in refs.get(obf_fqn, {}).get("refs", []):
        mr = obf_to_readable_ov(r, fwd)
        if mr and mr != meaning_fqn:
            M[r] = mr
    # 保护集: 本类常量池引用 + 02 import 表 + 自身 (fwd 全集不用 — 会让
    # `com.x.f.a(...)` 这种对类 f 的静态调用因 fwd 里有更长键而误跳过)
    protected = set(refs.get(obf_fqn, {}).get("refs", []))
    protected.add(obf_fqn)
    for line in src02_text.split("\n"):
        m = re.match(r"^\s*import\s+(?:static\s+)?([\w.]+)\s*;", line)
        if m:
            protected.add(m.group(1))

    def fqn_repl(r, mr):
        nonlocal content2
        pat = re.compile(re.escape(r) + r"(?![A-Za-z0-9_$])")
        cnt = 0
        out = []
        last = 0

        def cb(m):
            nonlocal cnt
            rest = content2[m.end():]
            # r 后的第一点号段: 若已知类 (常量池/import/自身) → 不替换
            msuf = re.match(r"\.([A-Za-z_$][\w$]*)", rest)
            if msuf:
                first = m.group(0) + msuf.group(0)
                if first in protected:
                    return m.group(0)
            cnt += 1
            return mr
        content2 = pat.sub(cb, content2)
        rep["fqn"] += cnt

    for r, mr in sorted(M.items(), key=lambda kv: -len(kv[0])):
        fqn_repl(r, mr)
    # 强转位重写: 锚定 02 原稿 import 表
    anchor = {}
    for line in src02_text.split("\n"):
        m = re.match(r"^\s*import\s+(?:static\s+)?([\w.]+)\s*;", line)
        if m:
            imp = m.group(1)
            mr = obf_to_readable_ov(imp, fwd)
            if mr:
                anchor[imp.rsplit(".", 1)[-1]] = mr
    for r, mr in M.items():
        simple = r.rsplit(".", 1)[-1]
        if simple in anchor:
            continue
        if sum(1 for rr in M if rr.rsplit(".", 1)[-1] == simple) == 1:
            anchor.setdefault(simple, mr)
    anchor.setdefault(obf_fqn.rsplit(".", 1)[-1], meaning_fqn)
    for simple, mr in sorted(anchor.items(), key=lambda kv: -len(kv[0])):
        ms = mr.rsplit(".", 1)[-1]
        if ms == simple:
            continue
        cast_pat = (r"(?<=\()\s*" + re.escape(simple) + r"(?:\s*\[\s*\])?\s*"
                    r"(?=\)\s*(?!\s*[),;.]))")
        content2, n = re.subn(cast_pat, ms, content2)
        rep["cast"] += n
        # 静态接收器: Type.method( — 与 fix_single_char_types.py pat_static 同约定
        static_pat = (r"(?<![\w$.\x5c])" + re.escape(simple)
                      + r"(?=\.[A-Za-z_$][\w$]*\s*\()")
        content2, n = re.subn(static_pat, ms, content2)
        rep["cast"] += n
    return content2, rep


# ── 追加文本的 import 需求分析 ─────────────────────────────────────

TYPE_POS_PAT = re.compile(
    r"(?:(?<=new\s)|(?<=instanceof\s)|(?<=extends\s)|(?<=implements\s)|(?<=\()"
    r"([A-Za-z_$][\w$]*)(?![\w$])|"
    r"(?<![\w$.\x5c])([A-Za-z_$][\w$]*)(?=\s+[A-Za-z_$][\w$]*\s*[=;,([]|"
    r"\s*\[)|"
    r"(?<![\w$.\x5c])([A-Za-z_$][\w$]*)\s*\()")


def needed_imports(appended, import_table2, pkg3, cur_imports):
    """追加文本类型位置用到的简单名 → 需要补的 import FQN (语义化且 03 树有文件)。"""
    need = set()
    for m in TYPE_POS_PAT.finditer(appended):
        tok = m.group(1) or m.group(2) or m.group(3)
        if not tok or tok in KEYWORDS:
            continue
        fqn = import_table2.get(tok)
        if not fqn:
            continue
        if fqn.rsplit(".", 1)[0] == pkg3:
            continue
        if fqn.startswith("java.lang."):
            continue
        if cur_imports.get(tok) == fqn:
            continue
        # 语义化验证: 非第三方 FQN 必须在 03 树中存在对应文件
        if not fqn.startswith(THIRD_PARTY_PREFIXES):
            rel = fqn.replace(".", "/") + ".java"
            if not (DEOBFUSCATED_DIR / rel).exists():
                continue
        need.add(fqn)
    return need


# ── 主合并 ─────────────────────────────────────────────────────────

def merge_pair(src_rel, dst_rel, fwd, refs, dry_run=False):
    src_rel = "com/corrodinggames/rts/" + src_rel
    dst_rel = "com/corrodinggames/rts/" + dst_rel
    src = DECOMPILED_DIR / src_rel
    dst = DEOBFUSCATED_DIR / dst_rel
    if not src.exists():
        return {"file": dst_rel, "error": "02 源不存在"}
    if not dst.exists():
        return {"file": dst_rel, "error": "03 目标不存在"}

    obf_fqn = src_rel.replace("/", ".").replace(".java", "")
    meaning_fqn = dst_rel.replace("/", ".").replace(".java", "")
    pkg3 = meaning_fqn.rsplit(".", 1)[0]

    # 1. 全文件语义化 (管道原生, 应用误标覆盖, 不写盘)
    orig = cpr.obf_to_readable
    cpr.obf_to_readable = obf_to_readable_ov
    try:
        c2, rep = cpr.rename_02_file(src_rel, dst_rel, fwd, dry_run=True)
    finally:
        cpr.obf_to_readable = orig
    if c2 is None:
        return {"file": dst_rel, "error": rep.get("error")}

    # 2. R3 补充 (FQN + cast)
    src02_text = src.read_text(encoding="utf-8", errors="replace")
    c2, rep2 = r3_extra(c2, src02_text, obf_fqn, meaning_fqn, fwd, refs)

    # 3. 解析双方
    p3 = dst.read_text(encoding="utf-8", errors="replace")
    _, h2, mems2, _ = split_members(c2)
    _, h3, mems3, close3 = split_members(p3)

    imp2 = load_import_table(c2)
    imp3 = load_import_table(p3)

    def parse(mems, source, imp, pkg):
        methods = {}
        fields = {}
        statics = []
        for kind, s, e in mems:
            if kind in ("close", "nested"):
                continue
            seg = source[s:e]
            if kind == "static":
                statics.append(seg)
                continue
            info = member_head_info(seg, imp, pkg)
            if info["kind"] == "field":
                fields.setdefault((info["name"], info["static"]), []).append(seg)
            else:
                mkey = (info["static"], info["arity"], info["params"], info["ret"])
                methods.setdefault(mkey, []).append(seg)
        return methods, fields, statics

    methods2, fields2, statics2 = parse(mems2, c2, imp2, pkg3)
    methods3, fields3, statics3 = parse(mems3, p3, imp3, pkg3)

    # 4. 匹配
    missing_methods = []
    for mkey, segs in methods2.items():
        static, arity, params, ret = mkey
        if mkey in methods3:
            continue
        matched = False
        for seg2 in segs:
            nm = member_head_info(seg2, imp2, pkg3)["name"]
            line2 = body_first_line(seg2)
            for s3 in methods3.get(mkey, []):
                i3 = member_head_info(s3, imp3, pkg3)
                if i3["name"] == nm and i3["arity"] == arity:
                    matched = True
                    break
                if line2 and len(line2) >= 15 and i3["arity"] == arity:
                    line3 = body_first_line(s3)
                    if line3 and line3[:40] == line2[:40]:
                        matched = True
                        break
            if matched:
                break
        if not matched:
            missing_methods.extend(segs)

    missing_fields = []
    for fkey, segs in fields2.items():
        if fkey not in fields3:
            missing_fields.extend(segs)

    missing_statics = []
    for seg in statics2:
        t2 = body_tokens(seg)
        best = 0.0
        for s3 in statics3:
            best = max(best, match_overlap(t2, body_tokens(s3)))
        if best < 0.5:
            missing_statics.append(seg)

    # 5. 组装
    appended_methods = "\n\n".join(x.rstrip("\n") for x in missing_methods)
    appended_fields = "\n\n".join(x.rstrip("\n") for x in missing_statics + missing_fields)

    to_add = sorted(needed_imports(appended_methods + "\n" + appended_fields,
                                   imp2, pkg3, imp3))

    out = p3
    lines_added = 0
    if appended_fields:
        out = out[:h3] + "\n" + appended_fields + "\n" + out[h3:]
        lines_added += appended_fields.count("\n") + 1
    if appended_methods:
        out = out[:close3] + "\n" + appended_methods + "\n" + out[close3:]
        lines_added += appended_methods.count("\n") + 1
    if to_add:
        pm = re.search(r"^package\s+[\w.]+\s*;", out, re.MULTILINE)
        ins = pm.end() if pm else 0
        out = out[:ins] + "\n" + "".join("import %s;\n" % f for f in to_add) + out[ins:]

    report = {
        "file": dst_rel, "methods": len(missing_methods),
        "fields": len(missing_fields), "statics": len(missing_statics),
        "imports": to_add, "lines_added": lines_added,
        "rep2": rep2, "tokens2": len(c2),
    }
    if not dry_run:
        dst.write_text(out, encoding="utf-8")
    return report


def main():
    dry_run = "--dry-run" in sys.argv
    only = None
    if "--only" in sys.argv:
        only = sys.argv[sys.argv.index("--only") + 1].replace("\\", "/")
    fwd, rev, refs = cpr.load_indexes()
    t0 = time.time()
    total = {"methods": 0, "fields": 0, "statics": 0, "lines": 0, "files": 0}
    for src_rel, dst_rel in PAIRS:
        if only and dst_rel != only and "com/corrodinggames/rts/" + dst_rel != only:
            continue
        rep = merge_pair(src_rel, dst_rel, fwd, refs, dry_run=dry_run)
        if "error" in rep:
            print(f"== {rep['file']}: 错误: {rep['error']}")
            continue
        total["methods"] += rep["methods"]
        total["fields"] += rep["fields"]
        total["statics"] += rep["statics"]
        total["lines"] += rep["lines_added"]
        total["files"] += 1
        print(f"== {rep['file']}: +方法 {rep['methods']} +字段 {rep['fields']} "
              f"+静态块 {rep['statics']} +行 {rep['lines_added']} "
              f"+import {len(rep['imports'])} "
              f"(fqn={rep['rep2']['fqn']}, cast={rep['rep2']['cast']})")
        for imp in rep["imports"]:
            print(f"     import {imp}")
    print(f"\n总计: 文件 {total['files']} 方法 {total['methods']} "
          f"字段 {total['fields']} 静态块 {total['statics']} "
          f"行 {total['lines']} ({time.time()-t0:.1f}s)"
          + (" [DRY RUN]" if dry_run else ""))


if __name__ == "__main__":
    main()
