#!/usr/bin/env python3
"""
Phase 4 Task 4.1 — Method Catalog Generator
从 02-decompiled 源码中提取方法签名、行号范围、字符串常量和被调用方法。
字符串常量是最快的语义锚点 — 它们直接揭示方法的业务逻辑。

用法: python tools/method_catalog.py [--class GameAction] [--package game/units/a]
输出: docs/generated/method-catalog/{ClassName}.md
"""
import re, sys, os
from pathlib import Path
from collections import defaultdict

ROOT = Path(__file__).resolve().parent.parent.parent  # tools/*/ -> tools/ -> ROOT
DECOMPILED = ROOT / "02-decompiled"
DEOBFUSCATED = ROOT / "03-deobfuscated"
OUT_DIR = ROOT / "docs" / "generated" / "method-catalog"

def extract_methods(java_path):
    """从 Java 源码中提取所有方法的信息。返回 [(name, sig, start_line, end_line, strings, calls, fields)]"""
    try:
        content = java_path.read_text(encoding='utf-8', errors='replace')
    except:
        return []

    lines = content.split('\n')
    methods = []
    i = 0
    while i < len(lines):
        line = lines[i]
        # 匹配方法声明: [修饰符] 返回类型 方法名(参数)
        m = re.match(
            r'^\s*(?:(?:public|private|protected|static|final|abstract|synchronized|'
            r'native|strictfp)\s+)*'  # 修饰符
            r'([\w<>[\],.\s]+?)\s+'   # 返回类型
            r'(\w+)\s*\(([^)]*)\)',   # 方法名(参数)
            line)
        if m:
            ret_type = m.group(1).strip()
            method_name = m.group(2)
            params = m.group(3).strip()

            # 跳过 import/package 行误匹配
            if method_name in ('import', 'package', 'class', 'interface', 'enum', 'new', 'return', 'if', 'for', 'while', 'throw', 'throws', 'super', 'this'):
                i += 1
                continue

            # 找到方法体结束 (大括号匹配)
            depth = 0
            started = False
            end_line = i
            body_lines = []
            strings = []
            called_methods = set()
            fields = set()

            for j in range(i, min(i + 500, len(lines))):  # 最多扫描 500 行
                l = lines[j]
                # 统计大括号
                for ch in l:
                    if ch == '{':
                        depth += 1
                        started = True
                    elif ch == '}':
                        depth -= 1

                if started:
                    body_lines.append(l)
                    # 提取字符串常量
                    for sm in re.finditer(r'"([^"]*)"', l):
                        s = sm.group(1)
                        if len(s) > 3 and not s.startswith('('):  # 跳过短字符串和格式串
                            strings.append(s)
                    # 提取方法调用: obj.method(
                    for cm in re.finditer(r'(\w+)\.(\w+)\s*\(', l):
                        called_methods.add(f"{cm.group(1)}.{cm.group(2)}")
                    # 提取字段访问: this.field
                    for fm in re.finditer(r'\bthis\.(\w+)', l):
                        fields.add(fm.group(1))

                if depth == 0 and started:
                    end_line = j
                    break
                if j == i + 499:
                    end_line = j

            methods.append({
                'name': method_name,
                'return_type': ret_type,
                'params': params,
                'start_line': i + 1,
                'end_line': end_line + 1,
                'line_count': end_line - i + 1,
                'strings': strings[:30],  # 最多30个字符串
                'calls': sorted(called_methods)[:20],
                'fields': sorted(fields)[:15],
            })
            i = end_line + 1
        else:
            i += 1

    return methods


def generate_catalog(java_path, output_path, class_name):
    """为一个类生成方法目录 markdown。"""
    methods = extract_methods(java_path)
    if not methods:
        return 0

    lines = [
        f"# {class_name} — 方法目录",
        f"",
        f"**源文件**: `{java_path.relative_to(ROOT)}`",
        f"**方法总数**: {len(methods)}",
        f"",
        f"---",
        f"",
    ]

    for m in methods:
        sig = f"{m['return_type']} {m['name']}({m['params']})"
        lines.append(f"## `{m['name']}` — {sig}")
        lines.append(f"")
        lines.append(f"- **行号**: {m['start_line']}-{m['end_line']} ({m['line_count']} 行)")
        lines.append(f"- **返回**: `{m['return_type']}`")
        lines.append(f"- **参数**: `{m['params']}`")
        lines.append(f"")

        if m['strings']:
            lines.append(f"### 字符串常量 (语义锚点)")
            for s in m['strings'][:10]:
                lines.append(f"- `\"{s}\"`")
            lines.append(f"")

        if m['calls']:
            lines.append(f"### 调用的方法")
            for c in m['calls'][:10]:
                lines.append(f"- `{c}()`")
            lines.append(f"")

        if m['fields']:
            lines.append(f"### 访问的字段")
            lines.append(f"- {', '.join('`'+f+'`' for f in m['fields'][:8])}")
            lines.append(f"")

        lines.append(f"### 方法体 (前 10 行)")
        # 读取实际方法体
        try:
            content = java_path.read_text(encoding='utf-8', errors='replace')
            body_lines = content.split('\n')[m['start_line']:m['start_line']+10]
            lines.append("```java")
            for bl in body_lines:
                lines.append(bl)
            lines.append("```")
        except:
            pass
        lines.append(f"")
        lines.append(f"---")
        lines.append(f"")

    output_path.parent.mkdir(parents=True, exist_ok=True)
    output_path.write_text('\n'.join(lines), encoding='utf-8')
    return len(methods)


def main():
    print("=" * 60)
    print("Method Catalog Generator — Phase 4")
    print("=" * 60)

    # 解析参数
    target_class = None
    target_package = None
    for i, a in enumerate(sys.argv):
        if a == '--class' and i+1 < len(sys.argv):
            target_class = sys.argv[i+1]
        if a == '--package' and i+1 < len(sys.argv):
            target_package = sys.argv[i+1]

    if target_class:
        # 单个类
        java_path = DEOBFUSCATED / target_class.replace('.', '/') + '.java'
        if not java_path.exists():
            print(f"文件未找到: {java_path}")
            # 尝试 02-decompiled
            java_path = DECOMPILED / target_class.replace('.', '/') + '.java'
            if not java_path.exists():
                print(f"02-decompiled 中也未找到")
                return
        cls_name = target_class.rsplit('.', 1)[-1]
        out = OUT_DIR / f"{cls_name}.md"
        n = generate_catalog(java_path, out, cls_name)
        print(f"  生成: {out} ({n} 方法)")

    elif target_package:
        # 整个包
        pkg_path = DEOBFUSCATED / target_package.replace('.', '/')
        if not pkg_path.exists():
            print(f"包未找到: {pkg_path}")
            return
        total = 0
        for jf in sorted(pkg_path.rglob("*.java")):
            rel = str(jf.relative_to(DEOBFUSCATED)).replace(os.sep, '.').replace('.java', '')
            cls_name = rel.rsplit('.', 1)[-1]
            out = OUT_DIR / f"{cls_name}.md"
            n = generate_catalog(jf, out, cls_name)
            if n > 0:
                print(f"  {cls_name}: {n} 方法")
                total += n
        print(f"\n  总计: {total} 方法, 输出目录: {OUT_DIR}")

    else:
        print("用法: python tools/method_catalog.py --class ClassName")
        print("      python tools/method_catalog.py --package com.corrodinggames.rts.game.units.a")
        print(f"  输出目录: {OUT_DIR}")


if __name__ == '__main__':
    main()
