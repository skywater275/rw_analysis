#!/usr/bin/env python3
"""对比 02b 接口/实现类与 03 实现类的方法签名, 输出差异报告 (TMI 实现类同步用).

Usage: python tools/fixers/diff_tmi_impl.py [--impl 03路径] [--ob 02b路径]
"""
import re
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))

# 默认: 02b m/x.java(TextureManager) vs 03 TextureManager.java
OB_DEFAULT = ROOT / "02b-decompiled/com/corrodinggames/rts/gameFramework/m/x.java"
IMPL_DEFAULT = ROOT / "03-deobfuscated/com/corrodinggames/rts/gameFramework/rendering/TextureManager.java"

METHOD_RE = re.compile(
    r"^\s*(?P<mods>public|protected|private)\s+(?P<ret>[^;\n{]+?)\s+(?P<name>[A-Za-z0-9_$]+)\s*\((?P<params>[^;{]*?)\)\s*(?:throws\s+[^{]+)?\{",
    re.MULTILINE,
)


def extract(path):
    """返回 [(lineno, name, ret, params_str)]"""
    text = path.read_text(encoding="utf-8")
    out = []
    for m in METHOD_RE.finditer(text):
        lineno = text[: m.start()].count("\n") + 1
        ret = " ".join(m.group("ret").split())
        params = " ".join(m.group("params").split())
        out.append((lineno, m.group("name"), ret, params))
    return out


def main():
    ob = extract(OB_DEFAULT)
    impl = extract(IMPL_DEFAULT)
    print(f"02b x.java: {len(ob)} methods | 03 TextureManager.java: {len(impl)} methods")
    print()
    print("=== 03 方法 (行号: 签名) — 与 02b 保序 zip 对比 ===")
    n = max(len(ob), len(impl))
    for i in range(n):
        o = ob[i] if i < len(ob) else None
        p = impl[i] if i < len(impl) else None
        tag = "  "
        if o and p:
            # 简化参数类型: 去掉包名/变量名, 只留类型序列
            def types(s):
                return [t.split()[-1] for t in s.split(",") if t.strip()]

            if o[1] != p[1] or o[2] != p[2] or types(o[3]) != types(p[3]):
                tag = "!!"
        elif o is None:
            tag = "--"
        elif p is None:
            tag = "++"
        oline = f"02b@{o[0]}: {o[1]}({o[3]}) : {o[2]}" if o else "(缺失)"
        pline = f"03@{p[0]}: {p[1]}({p[3]}) : {p[2]}" if p else "(缺失)"
        print(f"{tag} {pline}")
        if o and tag == "!!":
            print(f"      {oline}")


if __name__ == "__main__":
    main()
