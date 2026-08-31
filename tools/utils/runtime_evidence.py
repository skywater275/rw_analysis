#!/usr/bin/env python3
"""
runtime_evidence — 运行时日志证据消化 (v19.90)

解析游戏动态运行日志 (docs/generated/runtime-logs/*.log), 与静态证据库交叉:
- 游戏阶段字符串/错误消息 → class-evidence.json 常量池反查 → 宿主混淆类
- 崩溃堆栈 (类.方法(SourceFile:N)) → 02-decompiled 对应文件:行 → 方法定位
- 单位键 (unit:XXX) → 运行时单位注册表

输出 mappings/generated/runtime-evidence.csv:
  evidence_type, string, host_class, 02_location, note

Usage: python tools/utils/runtime_evidence.py [--logs <目录>]
"""
import json
import re
import sys
from pathlib import Path

csv_field_size = None
import csv
csv.field_size_limit(10 * 1024 * 1024)

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import GENERATED_DIR

LOGS_DIR = ROOT / "docs" / "generated" / "runtime-logs"
EVIDENCE_JSON = GENERATED_DIR / "class-evidence.json"
OUT_CSV = GENERATED_DIR / "runtime-evidence.csv"

# 字符串反查: 常量池字符串 → 宿主类 (用 class-evidence.json)
def build_string_index():
    ev = json.loads(EVIDENCE_JSON.read_text(encoding="utf-8"))
    idx = {}
    for fqn, e in ev.items():
        for s in e.get("strings", []):
            if len(s) >= 8:
                idx.setdefault(s, set()).add(fqn)
    return idx

def main():
    logs_dir = LOGS_DIR
    for i, a in enumerate(sys.argv):
        if a == "--logs" and i + 1 < len(sys.argv):
            logs_dir = ROOT / sys.argv[i + 1]

    str_index = build_string_index()
    print(f"[1] 字符串反查表: {len(str_index)} 条")

    rows = []
    # 阶段前缀模式: "xxx: message" 或 "--Now loading:xxx"
    phase_pat = re.compile(r"^\d{4}-\d{2}-\d{2} [\d:.]+: (.+)$")
    stack_pat = re.compile(r"at (com\.corrodinggames\.[\w.$]+)\.([\w$]+)\(SourceFile:(\d+)\)")
    unit_pat = re.compile(r'src="unit:([^"]+)"')

    unit_keys = set()
    phase_strings = set()
    stacks = set()

    for logf in sorted(logs_dir.glob("*.log")):
        text = logf.read_text(encoding="utf-8", errors="replace")
        # 单位键
        for m in unit_pat.finditer(text):
            unit_keys.add(m.group(1))
        # 堆栈
        for m in stack_pat.finditer(text):
            stacks.add((m.group(1), m.group(2), int(m.group(3))))
        # 阶段行 (跳过堆栈行/纯数字行)
        for line in text.split("\n"):
            m = phase_pat.match(line.strip())
            if not m:
                continue
            s = m.group(1).strip()
            if len(s) >= 8 and not s.startswith(("at ", "arg:", "Open(", "OpenConverted")):
                phase_strings.add(s[:120])

    print(f"[2] 提取: 单位键 {len(unit_keys)}, 阶段字符串 {len(phase_strings)}, 堆栈帧 {len(stacks)}")

    # 字符串 → 宿主类
    for s in sorted(phase_strings):
        # 前缀匹配 (阶段字符串常带动态部分, 用冒号前缀反查)
        hosts = set()
        key = s.split(":")[0]
        if key in str_index:
            hosts |= str_index[key]
        if s in str_index:
            hosts |= str_index[s]
        rows.append(["phase-string", s, ";".join(sorted(hosts)[:3]), "", ""])

    # 堆栈 → 02 定位
    for cls, method, line in sorted(stacks):
        p02 = ROOT / "02-decompiled" / (cls.replace(".", "/") + ".java")
        loc = f"{cls.replace('.', '/')}.java:{line}"
        exists = "存在" if p02.exists() else "缺"
        rows.append(["stack-frame", f"{cls}.{method}", "", f"02:{loc} ({exists})", ""])

    # 单位键
    for k in sorted(unit_keys):
        rows.append(["unit-key", k, "", "", "运行时单位注册表键"])

    with open(OUT_CSV, "w", encoding="utf-8", newline="") as f:
        w = csv.writer(f)
        w.writerow(["evidence_type", "string", "host_class", "02_location", "note"])
        w.writerows(rows)
    print(f"[3] 输出 {OUT_CSV.name}: {len(rows)} 行")
    sys.exit(0)

if __name__ == "__main__":
    main()
