"""
rwlib.config — 统一路径和 JDK 工具配置

提供项目根路径解析、JDK 工具查找和所有关键目录/文件路径。
这是所有其他 rwlib 模块和 tools/ 脚本的基础依赖。

使用方式:
    from rwlib.config import ROOT, GAME_LIB, SUPPLEMENT_CSV, find_javap

当前解决了以下重复代码:
    - find_javap() — 原在 cross_validate, sig_backfill, resolve_todo_sig_v2 各自定义 (3次)
    - find_javac() — 原在 javac_gate 定义
    - ROOT/DIRS 路径 — 原在 40+ 脚本中用 4 种不同风格定义
"""

import os
from pathlib import Path

# ── 项目根路径（自动检测） ──────────────────────────────────────────
# 从当前文件向上查找包含 game-lib.jar 的目录
_current = Path(__file__).resolve().parent
ROOT = _current
for _ in range(5):
    if (ROOT / "RustedWarfare" / "game-lib.jar").exists() or (ROOT / "mappings" / "supplement.csv").exists():
        break
    ROOT = ROOT.parent

# ── 核心目录 ────────────────────────────────────────────────────────
CLASSES_DIR = ROOT / "01-classes"          # .class 字节码文件 (388个, 真源)
DECOMPILED_DIR = ROOT / "02-decompiled"   # CFR 反编译输出 (1,698个, 混淆名)
DEOBFUSCATED_DIR = ROOT / "03-deobfuscated"  # 解混淆输出 (1,670个, 可读名)
MAPPINGS_DIR = ROOT / "mappings"          # 映射数据库
DOCS_DIR = ROOT / "docs"                  # 文档
TOOLS_DIR = ROOT / "tools"               # 工具脚本
BUILD_OUTPUT = ROOT / "build-output"      # 编译产物

# ── 关键文件 ────────────────────────────────────────────────────────
def _resolve_game_lib():
    """原始混淆 JAR (v1.15, 1,698 class) — 游戏安装目录已移出项目 (v19.133f98),
    候选: 项目内旧位 → 上级目录 'Rusted Warfare/'."""
    candidates = [
        ROOT / "RustedWarfare" / "game-lib.jar",      # 干净游戏本体 (项目内, 用户重放)
        ROOT.parent / "Rusted Warfare" / "game-lib.jar",  # 旧游戏目录 (上级)
        ROOT.parent / "game-lib.jar",                  # 上级直放
    ]
    for c in candidates:
        if c.exists():
            return c
    return candidates[-1]  # 默认末位 (verify 会报告缺失)


GAME_LIB = _resolve_game_lib()
SUPPLEMENT_CSV = MAPPINGS_DIR / "supplement.csv"        # 主映射数据库
MAPPINGS_CSV = MAPPINGS_DIR / "mappings.csv"            # 类重命名
MAPPINGS_JSON = MAPPINGS_DIR / "mappings.json"          # 类重命名 (JSON)
CLASS_DISCOVERIES = MAPPINGS_DIR / "class-discoveries.csv"  # 类发现
GENERATED_DIR = MAPPINGS_DIR / "generated"               # 工具生成文件
UNMAPPED_CSV = GENERATED_DIR / "unmapped-bytecode.csv"   # 未映射成员
DESCRIPTORS_JSON = GENERATED_DIR / "descriptors.json"    # 字节码描述符
PRIORITY_CSV = MAPPINGS_DIR / "mapping-priority.csv"     # 优先级

# ── 动态测试: 调试服务器 (DebugServer, rts.a.a) ──────────────────────
DEBUG_HOST = "127.0.0.1"   # -debugscript 启动的调试服务器地址
DEBUG_PORT = 5677          # 调试服务器端口 (rts.a.a.a(int, String))

# ── JDK 工具查找 ────────────────────────────────────────────────────

def _find_jdk_base():
    """查找 JDK 根目录，返回 Path 或 None。按优先级搜索。"""
    # 1. 游戏自带的完整 JDK 13 (RustedWarfare/jvm64/) — 游戏64位版的实际运行时, 优先
    game_jvm = ROOT / "RustedWarfare" / "jvm64"
    if (game_jvm / "bin" / ("javac.exe" if os.name == "nt" else "javac")).exists():
        return game_jvm

    # 2. 项目附带的 JDK (Rusted Warfare/jdk/jdk-*/)
    for candidate in [
        ROOT.parent / "jdk",           # 项目级 jdk/
        Path("C:/JDK"),                # 系统级 C:/JDK/
    ]:
        if candidate.exists():
            for d in sorted(candidate.glob("jdk-*"), reverse=True):
                if d.is_dir():
                    return d

    # 3. JAVA_HOME 环境变量
    java_home = os.environ.get("JAVA_HOME", "")
    if java_home:
        jh = Path(java_home)
        if jh.exists():
            return jh

    return None


def _find_tool(name):
    """
    查找 JDK 工具 (javap, javac 等)。

    参数:
        name: 工具名, 如 "javap" 或 "javac"

    返回:
        str: 工具完整路径, 或仅工具名 (fallback 到 PATH)

    优先级: 项目 JDK > C:/JDK > JAVA_HOME > PATH
    """
    exe = name + (".exe" if os.name == "nt" else "")
    jdk = _find_jdk_base()
    if jdk:
        tool = jdk / "bin" / exe
        if tool.exists():
            return str(tool)
    # 最后 fallback 到 PATH
    return name


def find_javap():
    """
    查找 javap 路径。

    当前有 5 个脚本各自实现了此逻辑:
        cross_validate_bytecode.py, sig_backfill.py,
        resolve_todo_sig.py, resolve_todo_sig_v2.py, comprehensive_analysis.py
    """
    return _find_tool("javap")


def find_javac():
    """
    查找 javac 路径。

    当前在 javac_gate.py 中定义。
    """
    return _find_tool("javac")


# ── 快速验证 ────────────────────────────────────────────────────────
def verify():
    """验证关键路径和工具是否存在, 返回问题列表。"""
    issues = []
    checks = [
        ("项目根", ROOT, "dir"),
        ("game-lib.jar", GAME_LIB, "file"),
        ("supplement.csv", SUPPLEMENT_CSV, "file"),
        ("01-classes/", CLASSES_DIR, "dir"),
        ("02-decompiled/", DECOMPILED_DIR, "dir"),
        ("03-deobfuscated/", DEOBFUSCATED_DIR, "dir"),
    ]
    for label, path, kind in checks:
        exists = path.exists() if kind == "file" else (path.is_dir() if kind == "dir" else False)
        if not exists:
            issues.append(f"缺少{label}: {path}")

    javap = find_javap()
    javac = find_javac()
    if javap == "javap":
        issues.append("javap 未找到 (不在 PATH 中)")
    if javac == "javac":
        issues.append("javac 未找到 (不在 PATH 中)")

    return issues


# 如果直接运行，打印配置信息
if __name__ == "__main__":
    print("rwlib.config — 项目配置")
    print(f"  ROOT:           {ROOT}")
    print(f"  GAME_LIB:       {GAME_LIB} ({GAME_LIB.exists()})")
    print(f"  SUPPLEMENT_CSV: {SUPPLEMENT_CSV} ({SUPPLEMENT_CSV.exists()})")
    print(f"  CLASSES_DIR:    {CLASSES_DIR} ({CLASSES_DIR.is_dir()})")
    print(f"  javap:          {find_javap()}")
    print(f"  javac:          {find_javac()}")
    issues = verify()
    if issues:
        print(f"\n问题 ({len(issues)}):")
        for i in issues:
            print(f"  - {i}")
    else:
        print(f"\n全部检查通过。")
