#!/usr/bin/env python3
"""
成员名回退 (R4-A) — 声明侧语义名 → 混淆名, 修复调用点级联。

javac 报错 "cannot find symbol: sym, location: class X" 且 sym 是 X 的成员混淆名,
说明 X 的声明被语义改名而调用点未同步。用 supplement 反向映射在 X 的类文件中
把声明处的语义名回退为混淆名 (声明 + 类内 self 引用), 调用点即刻恢复。

Usage: python tools/core/member_revert.py [--dry-run] [--csv compile-errors.csv]
"""
import csv
import json
import re
import sys
from collections import defaultdict
from pathlib import Path

csv.field_size_limit(10 * 1024 * 1024)

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import DEOBFUSCATED_DIR, MAPPINGS_DIR

# 类型 token 不参与 (R3 的职责): 单/双字符 + 首字母大写形态过滤在调用处
def main():
    dry_run = "--dry-run" in sys.argv
    csv_path = "compile-errors.csv"
    for i, a in enumerate(sys.argv):
        if a == "--csv" and i + 1 < len(sys.argv):
            csv_path = sys.argv[i + 1]

    # supplement: (混淆类, 混淆成员) -> 语义名
    # 归一: 成员名去参数表 (obfuscated_member 可能含 "b(boolean)"); 跳过含空白的语义名
    supp = {}
    with open(MAPPINGS_DIR / "supplement.csv", encoding="utf-8") as f:
        for row in csv.DictReader(f):
            if row.get("type") not in ("field", "method"):
                continue
            op = (row.get("obfuscated_package") or "").strip()
            oc = (row.get("obfuscated_class") or "").strip()
            om = (row.get("obfuscated_member") or "").strip().split("(")[0]
            mn = (row.get("meaningful_name") or "").strip()
            if op and oc and om and mn and om != mn and " " not in mn:
                supp.setdefault((op + "." + oc, om), mn)

    # rev: 意义FQN → 混淆FQN (loc 的类名 → 混淆类 → supplement 查询)
    idx = json.loads((ROOT / "mappings" / "generated" / "identity-index.json")
                     .read_text(encoding="utf-8"))
    rev = idx["rev"]
    fwd = idx["fwd"]

    # 03 树: 简单类名 → FQN 集合 (loc 解析用)
    tree = defaultdict(set)
    for jf in DEOBFUSCATED_DIR.rglob("*.java"):
        fqn = jf.relative_to(DEOBFUSCATED_DIR).as_posix().replace(".java", "").replace("/", ".")
        tree[fqn.rsplit(".", 1)[-1]].add(fqn)

    # 错误解析: (符号, 类型简单名) — 从 CSV 的 symbol/location 列
    # loc 为空 → 目标为错误文件自身的类 (自引用成员错误)
    targets = defaultdict(set)  # (class_fqn, member) 集合
    with open(csv_path, encoding="utf-8") as f:
        for row in csv.DictReader(f):
            if "cannot find symbol" not in row.get("message", ""):
                continue
            sym = (row.get("symbol") or "").strip()
            loc = (row.get("location") or "").strip()
            if not sym:
                continue
            m = re.search(r"(?:class|interface|type)\s+([A-Za-z_$][\w$]*)\s*$", loc)
            loc_simple = m.group(1) if m else None
            # 成员名归一: 去参数表 (sym 可能为 "b(boolean,com.x.as)") — 只留名字部分
            member = sym.split("(")[0]
            if not re.fullmatch(r"[A-Za-z_$][\w$]*", member):
                continue
            if loc_simple:
                targets[loc_simple].add(member)
            else:
                # 自文件目标: 用错误文件路径推导 (在消费端解析)
                file_cls = row.get("file", "").replace("\\", "/").rsplit("/", 1)[-1]
                if file_cls.endswith(".java"):
                    targets["@self:" + file_cls[:-5]].add(member)

    print(f"目标: {len(targets)} 个类, {sum(len(v) for v in targets.values())} 个成员")

    fixed_files = 0
    fixed_members = 0
    for loc_simple, members in sorted(targets.items()):
        if loc_simple.startswith("@self:"):
            # 自文件目标: 由错误文件名推导类 FQN (可能歧义, 用文件路径树)
            fqns = tree.get(loc_simple[6:], set())
            if len(fqns) != 1:
                continue
            cls_fqn = next(iter(fqns))
        else:
            fqns = tree.get(loc_simple, set())
            if len(fqns) != 1:
                print(f"  loc 歧义/缺失: {loc_simple} -> {sorted(fqns)[:3]}")
                continue
            cls_fqn = next(iter(fqns))
        obf_fqn = rev.get(cls_fqn)
        if not obf_fqn:
            print(f"  无混淆身份: {cls_fqn}")
            continue
        jf = DEOBFUSCATED_DIR / (cls_fqn.replace(".", "/") + ".java")
        if not jf.exists():
            continue
        content = jf.read_text(encoding="utf-8", errors="replace")
        changed = 0
        for member in sorted(members):
            mn = supp.get((obf_fqn, member))
            if not mn:
                continue
            # 混淆名已存在于类中 → v18.x 曾新增语义重复声明 → 删除语义声明
            # 否则: 语义名在声明位回退为混淆名
            mods = (r"(?:public|protected|private|static|final|volatile|transient"
                    r"|synchronized|abstract|strictfp|native|\s)")
            field_pat = (rf"({mods}+)([\w<>.\[\]?,]+\s+){re.escape(mn)}(\s*[=;])")
            method_pat = (rf"({mods}+)([\w<>.\[\]?,]+\s+)?{re.escape(mn)}(\s*\()")
            # 判定"混淆名已存在"必须是声明位 (修饰词+类型+名字), 用法行 (l2.bX;) 不算
            has_decl = re.search(
                rf"(?:^|[;\n])\s*{mods}+[\w<>.\[\]?,]+\s+{re.escape(member)}\s*[=;]",
                content)
            if has_decl:
                # 删除语义重复: 字段整行 / 方法整块 (花括号平衡)
                new, n1 = re.subn(
                    rf"^[ \t]*{mods}*[\w<>.\[\]?,]+\s+{re.escape(mn)}\s*[=;][^\n]*\n",
                    "", content, flags=re.MULTILINE)
                n2 = 0
                pos = 0
                while True:
                    m = re.search(
                        rf"^[ \t]*(?:{mods}+)[\w<>.\[\]?,]+\s+{re.escape(mn)}\s*\([^{{]*\{{",
                        new, re.MULTILINE)
                    if not m:
                        break
                    # 花括号平衡找块尾
                    depth = 0
                    j = m.start()
                    while j < len(new):
                        if new[j] == "{":
                            depth += 1
                        elif new[j] == "}":
                            depth -= 1
                            if depth == 0:
                                break
                        j += 1
                    # 删到块尾后的换行
                    k = new.find("\n", j)
                    new = new[:m.start()] + new[k+1:]
                    n2 += 1
                n3 = n4 = 0
            else:
                new, n1 = re.subn(field_pat, rf"\1\2{member}\3", content) \
                    if re.search(rf"(?<![\w$]){re.escape(mn)}\s*[=;]", content) else (content, 0)
                # 方法声明
                new, n2 = re.subn(method_pat, rf"\1\2{member}\3", new)
                # 类内 self 引用: this.mn / super.mn
                new, n3 = re.subn(rf"\bthis\.{re.escape(mn)}\b", f"this.{member}", new)
                new, n4 = re.subn(rf"\bsuper\.{re.escape(mn)}\b", f"super.{member}", new)
            if n1 + n2 + n3 + n4:
                content = new
                changed += n1 + n2 + n3 + n4
        if changed:
            fixed_files += 1
            fixed_members += changed
            if not dry_run:
                jf.write_text(content, encoding="utf-8")
            print(f"  {cls_fqn}: {changed} 处回退")

    print(f"\n回退 {fixed_members} 处, {fixed_files} 文件" + (" (DRY RUN)" if dry_run else ""))
    sys.exit(0)


if __name__ == "__main__":
    main()
