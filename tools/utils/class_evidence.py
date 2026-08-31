#!/usr/bin/env python3
"""
class_evidence — 05-gamelib 字节码证据提取 (v19.87)

对 unresolved.txt 的未映射混淆类并行提取证据 (单次 javap -p -v 解析):
- super 类 + 接口列表 (类声明头)
- 常量池全部 UTF8 字符串
- Rule A 自证候选: 形如 "ClassName: Expected/Unknown/Failed..." 的错误前缀

输出:
- mappings/generated/class-evidence.json   — 每类完整证据
- mappings/generated/class-evidence-report.csv — 上下文证据报告 (Rule B/C 参考)

Usage: python tools/utils/class_evidence.py [--limit N] [--workers N]
"""
import csv
import json
import re
import subprocess
import sys
from concurrent.futures import ThreadPoolExecutor
from pathlib import Path

csv.field_size_limit(10 * 1024 * 1024)

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import find_javap, GENERATED_DIR, SUPPLEMENT_CSV

GAME_LIB = None  # 见函数内动态导入 (05-gamelib 已删, v19.133f98)
UNRESOLVED_TXT = GENERATED_DIR / "unresolved.txt"
CLASS_REFS_JSON = GENERATED_DIR / "class-refs.json"
EVIDENCE_JSON = GENERATED_DIR / "class-evidence.json"
REPORT_CSV = GENERATED_DIR / "class-evidence-report.csv"

# 自证错误前缀模式: "ClassName: <错误关键词>"
SELF_ID_PAT = re.compile(
    r"([A-Z][a-zA-Z]{3,}[a-zA-Z0-9]*): "
    r"(Expected|Unknown|Failed|Could not|Cannot|Invalid|Unexpected|Unsupported|"
    r"requires|is not|doesn|must|Missing|Unhandled)")
# 常见非类名前缀 (排除)
EXCLUDED_NAMES = {
    "Java", "Android", "Runtime", "Exception", "Error", "Warning", "Info",
    "Note", "Line", "File", "System", "Thread", "Opengl", "OpenGL", "Failed",
    "Unknown", "Expected", "Missing", "Invalid", "Warning", "Null", "Empty",
    "Custom", "Unit", "Units", "Cannot", "Could", "Loaded", "Loading",
    "Setting", "Settings", "FailedTo", "Unable", "Unsupported", "Resource",
    "Sound", "Music", "Image", "Texture", "CouldNot", "Index", "Data",
}


def parse_javap_output(out):
    """解析 javap -p -v 输出: (extends, [interfaces], [strings])."""
    super_cls = None
    interfaces = []
    strings = []
    in_cp = False
    for line in out.split("\n"):
        s = line.strip()
        # 类声明头: public class X extends Y implements A, B {
        m = re.match(r"^(?:public\s+|final\s+|abstract\s+)*(?:class|interface)\s+\S+"
                     r"(?:\s+extends\s+(\S+))?(?:\s+implements\s+(.+?))?\{?$", s)
        if m and "class" in s and not s.startswith("#"):
            if m.group(1):
                super_cls = m.group(1)
            if m.group(2):
                interfaces = [i.strip() for i in m.group(2).split(",")]
        if s.startswith("Constant pool:"):
            in_cp = True
            continue
        if in_cp:
            if s.startswith("{"):
                continue
            if s.startswith("}"):
                in_cp = False
                continue
            m = re.match(r"^#\d+\s*=\s*Utf8\s{2,}(.+)$", s)
            if m and len(m.group(1).strip()) > 1:
                strings.append(m.group(1).strip())
    return super_cls, interfaces, list(set(strings))


def extract_one(fqn):
    """提取单个类的证据."""
    javap = find_javap()
    try:
        from rwlib.config import GAME_LIB as _GAME_LIB
        r = subprocess.run([javap, "-p", "-v", "-classpath", str(_GAME_LIB), fqn],
                           capture_output=True, text=True, timeout=30)
        if r.returncode != 0:
            return fqn, {"error": "javap 失败"}
    except (subprocess.TimeoutExpired, FileNotFoundError, OSError) as e:
        return fqn, {"error": str(e)}
    super_cls, interfaces, strings = parse_javap_output(r.stdout)
    return fqn, {
        "super": super_cls,
        "interfaces": interfaces,
        "strings": strings,
        "string_count": len(strings),
    }


def main():
    limit = None
    workers = 8
    for i, a in enumerate(sys.argv):
        if a == "--limit" and i + 1 < len(sys.argv):
            limit = int(sys.argv[i + 1])
        if a == "--workers" and i + 1 < len(sys.argv):
            workers = int(sys.argv[i + 1])

    unresolved = [l.strip() for l in UNRESOLVED_TXT.read_text(encoding="utf-8").splitlines()
                  if l.strip() and not l.startswith("#")]
    if limit:
        unresolved = unresolved[:limit]
    print(f"[1] 待提取: {len(unresolved)} 类 ({workers} 线程)")

    # class-refs (super 已有) + fwd (语义 super 链)
    refs = json.loads(CLASS_REFS_JSON.read_text(encoding="utf-8"))
    fwd = json.loads((GENERATED_DIR / "identity-index.json").read_text(encoding="utf-8"))["fwd"]

    # 已占用名字 (防自证候选撞名): fwd 值简单名 + 03 文件 stem
    taken = {v.rsplit(".", 1)[-1] for v in fwd.values()}
    taken |= {p.stem for p in (ROOT / "03-deobfuscated").rglob("*.java")}

    # supplement 成员线索
    supp = {}
    from rwlib.mappings import load_supplement
    _, rows = load_supplement()
    for r in rows:
        key = (r["obfuscated_package"], r["obfuscated_class"])
        supp.setdefault(key, []).append(r["meaningful_name"])

    # 并行提取
    evidence = {}
    with ThreadPoolExecutor(max_workers=workers) as ex:
        for fqn, ev in ex.map(extract_one, unresolved):
            evidence[fqn] = ev
    print(f"[2] 提取完成: {sum(1 for e in evidence.values() if 'error' not in e)} 成功")

    # Rule A 自证扫描 + 报告行组装
    report_rows = []
    self_id_hits = []
    for fqn in sorted(evidence):
        ev = evidence[fqn]
        if "error" in ev:
            report_rows.append([fqn, "", "", "", f"ERROR: {ev['error']}", ""])
            continue
        super_cls = ev["super"] or ""
        sem_super = fwd.get(super_cls, "") if super_cls else ""
        # super 链: 若 super 未映射则标 obfuscated
        chain = sem_super if sem_super else super_cls
        pkg, cls = fqn.rsplit(".", 1)
        supp_members = supp.get((pkg, cls), [])
        supp_preview = ";".join(sorted(set(supp_members))[:6])

        # Rule A: 自证候选
        candidates = []
        for s in ev["strings"]:
            for m in SELF_ID_PAT.finditer(s):
                name = m.group(1)
                if name in EXCLUDED_NAMES or name in taken:
                    continue
                candidates.append((name, s[:90]))
        cand_names = sorted(set(n for n, _ in candidates))
        if cand_names:
            self_id_hits.append((fqn, candidates))
        report_rows.append([
            fqn, super_cls, chain, ";".join(ev["interfaces"]),
            ";".join(cand_names), supp_preview, "",
        ])

    # 输出
    EVIDENCE_JSON.write_text(json.dumps(evidence, ensure_ascii=False, indent=1),
                             encoding="utf-8")
    header = ["fqn", "super", "semantic_super", "interfaces", "self_id_candidates",
              "supplement_members", "suggestion"]
    with open(REPORT_CSV, "w", encoding="utf-8", newline="") as f:
        w = csv.writer(f)
        w.writerow(header)
        w.writerows(report_rows)

    print(f"[3] Rule A 自证候选: {len(self_id_hits)} 个类")
    for fqn, cands in self_id_hits:
        for name, sample in sorted(set(cands))[:3]:
            print(f"    {fqn}  ->  {name}  |  {sample}")
    print(f"[输出] {EVIDENCE_JSON.name}, {REPORT_CSV.name}")
    sys.exit(0)


if __name__ == "__main__":
    main()
