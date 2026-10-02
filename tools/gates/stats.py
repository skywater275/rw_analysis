#!/usr/bin/env python3
"""Phase 6: 自动统计生成器 — 输出 docs/STATUS.md 作为唯一真相源"""
import csv, json, subprocess, sys, time
csv.field_size_limit(10 * 1024 * 1024)
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent.parent  # tools/gates/ → ROOT
sys.path.insert(0, str(ROOT))
from rwlib.config import SUPPLEMENT_CSV as SUPP_CSV, MAPPINGS_CSV, MAPPINGS_JSON

def count_files(d):
    return sum(1 for _ in Path(d).rglob("*.java")) if Path(d).exists() else 0

def count_lines(f):
    try:
        return sum(1 for _ in open(f, encoding='utf-8'))
    except:
        return 0

def main():
    t0 = time.time()
    print("生成 STATUS.md ...")

    n01 = len(list((ROOT / "01-classes").rglob("*.class")))
    n02 = count_files(ROOT / "02-decompiled")
    n03 = count_files(ROOT / "03-deobfuscated")

    single_char = sum(1 for jf in (ROOT / "03-deobfuscated").rglob("*.java")
                      if len(jf.stem) <= 2 and jf.stem[0].islower())

    # supplement.csv
    n_fields = n_methods = n_ambiguous = n_verified = 0
    cls_set = set()
    with open(ROOT / "mappings" / "supplement.csv", encoding='utf-8') as f:
        for r in csv.DictReader(f):
            if r['type'] == 'field': n_fields += 1
            elif r['type'] == 'method': n_methods += 1
            pkg = r.get('obfuscated_package','')
            cl = r.get('obfuscated_class','')
            if pkg and cl: cls_set.add(f"{pkg}.{cl}")
            if 'TODO-SIG' in (r.get('notes','') or ''): n_ambiguous += 1
            if r.get('verified',''): n_verified += 1

    n_m_csv = count_lines(ROOT / "mappings" / "mappings.csv") - 1
    n_disc = count_lines(ROOT / "mappings" / "class-discoveries.csv") - 1
    with open(ROOT / "mappings" / "mappings.json", encoding='utf-8') as f:
        n_m_json = len(json.load(f).get("classes", {}))

    # javac gate
    r = subprocess.run([sys.executable, str(ROOT / "tools" / "gates" / "javac_gate.py"),
        "--quick", "--package", "com/corrodinggames/rts/game"],
        capture_output=True, text=True, timeout=60, cwd=str(ROOT))
    gate = "0 错误" if "PASSED" in (r.stdout + r.stderr) else "有错误"

    # coverage from cross_validate
    r = subprocess.run([sys.executable, str(ROOT / "tools" / "core" / "cross_validate.py")],
        capture_output=True, text=True, timeout=120, cwd=str(ROOT))
    fc = mc = "?"
    for l in (r.stdout + r.stderr).split('\n'):
        if 'Fields:' in l and 'verified' in l:
            fc = l.split('(')[1].split('%')[0] if '(' in l else '?'
        if 'Methods:' in l and 'verified' in l:
            mc = l.split('(')[1].split('%')[0] if '(' in l else '?'

    n_tools = len(list((ROOT / "tools").rglob("*.py")))  # 递归统计子目录
    n_docs = len(list((ROOT / "docs").rglob("*.md")))

    # git
    try:
        r = subprocess.run(["git","log","--oneline"], capture_output=True, text=True, timeout=5, cwd=str(ROOT))
        n_git = len([l for l in r.stdout.split('\n') if l.strip()])
    except:
        n_git = 0

    status = f"""# Rusted Warfare v1.15 解混淆项目 — 状态报告

> 自动生成: {time.strftime('%Y-%m-%d %H:%M')} | v10.0 FINAL

## 文件统计

| 目录 | 数量 |
|------|------|
| 01-classes (.class 字节码) | {n01} |
| 02-decompiled (CFR 反编译) | {n02} |
| 03-deobfuscated (解混淆输出) | {n03} |
| 残留单字符文件 | {single_char} |

## 映射数据库

| 指标 | 数量 |
|------|------|
| 字段映射 | {n_fields} |
| 方法映射 | {n_methods} |
| 覆盖类数 | {len(cls_set)} |
| 歧义标记 [TODO-SIG] | {n_ambiguous} |
| 已验证 (verified) | {n_verified} |
| 总映射数 | {n_fields + n_methods} |

## 类映射来源

| 来源 | 数量 |
|------|------|
| mappings.csv | {n_m_csv} |
| mappings.json | {n_m_json} |
| class-discoveries.csv | {n_disc} |

## 验证状态

| 指标 | 值 |
|------|-----|
| 编译门禁 (核心包) | {gate} |
| 字段覆盖率 | {fc}% |
| 方法覆盖率 | {mc}% |

## 项目规模

| 指标 | 数量 |
|------|------|
| Python 工具 | {n_tools} |
| 文档文件 | {n_docs} |
| Git 提交 | {n_git} |

## Phase 进度

| 阶段 | 状态 |
|------|------|
| Phase 1 可编译性 | ✅ |
| Phase 2 签名引擎 | ✅ |
| Phase 3 核心类补全 | ✅ |
| Phase 4 语义解混淆 | ✅ |
| Phase 5 内部类清理 | ✅ |
| Phase 6 文档整合 | ✅ |
"""
    out = ROOT / "docs" / "STATUS.md"
    out.write_text(status, encoding='utf-8')
    print(f"  -> {out} ({time.time()-t0:.1f}s)")


if __name__ == '__main__':
    main()
