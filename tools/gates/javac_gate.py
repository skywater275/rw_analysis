#!/usr/bin/env python3
"""
Phase 1 Task 1.4 — javac_gate.py: Permanent compile harness for 03-deobfuscated.

Usage: python tools/javac_gate.py [--dry-run] [--quick] [--package game]

Features:
- Compiles 03-deobfuscated against game-lib.jar + auto-generated stubs
- Generates minimal stub .java for unresolved android.*/steamworks/librocket types
- Outputs compile-errors.csv grouped by file and error class
- --quick mode: compile a single package for fast iteration
- Mandatory gate after every future batch_add session
"""

import csv, os, re, subprocess, sys, tempfile, zipfile
from pathlib import Path
from collections import defaultdict

ROOT = Path(__file__).resolve().parent.parent.parent  # tools/gates/ → ROOT
sys.path.insert(0, str(ROOT))
from rwlib.config import find_javac, DEOBFUSCATED_DIR as DEOBFUSCATED, GAME_LIB
STUBS_DIR = ROOT / "tools" / "gates" / "stubs"
ERRORS_CSV = ROOT / "compile-errors.csv"
LIBS_DIR = ROOT / "RustedWarfare" / "libs"  # Real game dependency jars


def generate_stubs():
    """Scan 03-deobfuscated for unresolved imports and generate minimal stubs.

    Only generates stubs for types that are NOT already available in:
    - 03-deobfuscated/ source files (would cause duplicate class errors)
    - game-lib.jar (would cause duplicate class errors)
    - JDK (java.*, javax.*)
    """
    STUBS_DIR.mkdir(parents=True, exist_ok=True)

    # Build set of FQNs already available as source files in 03-deobfuscated
    source_fqns = set()
    for jf in DEOBFUSCATED.rglob("*.java"):
        rel = jf.relative_to(DEOBFUSCATED)
        fqn = str(rel).replace(os.sep, '.').replace('.java', '')
        source_fqns.add(fqn)

    # Build set of FQNs available in game-lib.jar
    jar_fqns = set()
    if GAME_LIB.exists():
        import zipfile
        with zipfile.ZipFile(str(GAME_LIB)) as zf:
            for name in zf.namelist():
                if name.endswith('.class'):
                    jar_fqns.add(name.replace('/', '.').replace('.class', ''))

    # Also index classes from lib jars (slick, lwjgl, android, etc.)
    # These are the REAL dependencies that used to be stubbed
    lib_fqns = set()
    if LIBS_DIR.exists():
        for lib_jar in LIBS_DIR.glob('*.jar'):
            try:
                with zipfile.ZipFile(str(lib_jar)) as zf:
                    for name in zf.namelist():
                        if name.endswith('.class'):
                            lib_fqns.add(name.replace('/', '.').replace('.class', ''))
            except:
                pass

    # Collect all import statements
    imports = set()
    for jf in DEOBFUSCATED.rglob("*.java"):
        try:
            content = jf.read_text(encoding='utf-8', errors='replace')
        except:
            continue
        for line in content.split('\n'):
            m = re.match(r'^\s*import\s+([^;]+);', line)
            if m:
                imports.add(m.group(1).strip())

    # Filter: only generate stubs for types NOT available anywhere
    needed = set()
    for imp in sorted(imports):
        if imp.startswith('java.') or imp.startswith('javax.') or imp.startswith('sun.'):
            continue  # JDK provided
        if imp in source_fqns:
            continue  # Already in 03-deobfuscated sources
        if imp in jar_fqns:
            continue  # Already in game-lib.jar
        if imp in lib_fqns:
            continue  # Already in real dependency jars (slick, lwjgl, etc.)
        needed.add(imp)

    # Group by package
    stub_packages = defaultdict(set)
    for imp in needed:
        # Simple class name is the last part
        parts = imp.rsplit('.', 1)
        pkg = parts[0] if len(parts) == 2 else ''
        cls = parts[-1] if len(parts) == 2 else imp
        stub_packages[pkg].add(cls)

    # Generate stub files
    generated = 0
    for pkg, classes in sorted(stub_packages.items()):
        stub_dir = STUBS_DIR / pkg.replace('.', '/')
        stub_dir.mkdir(parents=True, exist_ok=True)

        for cls in sorted(classes):
            stub_file = stub_dir / f"{cls}.java"
            if stub_file.exists():
                continue

            # Generate minimal stub
            stub_file.write_text(f"""// Auto-generated stub for compilation
package {pkg};

public class {cls} {{
    public {cls}() {{}}
}}
""", encoding='utf-8')
            generated += 1

    if generated:
        print(f"  Generated {generated} stub files in {STUBS_DIR}")
    else:
        print("  All stubs already exist")

    return needed


def ensure_patched_classes():
    """v19.132: 生成 android.jar 内部类污染补丁 (cache/patched-classes/)。

    根因: android.jar Stub 版 BitmapFactory$Options.class 的 InnerClasses 属性
    含 `Config = Bitmap$Config of Bitmap` 条目 (Options.inBitmap 字段类型导致
    内部类表合并)。javac 加载它时 readInnerClasses 中 c != outer → 创建
    flags=0 (package-private) 的嵌套符号并污染全局 flatname 表 → 后续所有
    `import android.graphics.Bitmap$Config` 报 "Config is not public in Bitmap"。
    修复: 删除该条目 (fix_android_innerclasses.py), 补丁类置于 cp 最前。
    """
    fixer = ROOT / "tools" / "fixers" / "fix_android_innerclasses.py"
    patched = ROOT / "cache" / "patched-classes"
    if patched.exists() and any(patched.rglob("*.class")):
        return  # 已生成 (幂等)
    import subprocess as sp
    r = sp.run([sys.executable, str(fixer), "--only-jar", "android.jar",
                "--only-class", "android/graphics/BitmapFactory$Options.class"],
               capture_output=True, text=True)
    if r.returncode != 0:
        print(f"  WARN: fix_android_innerclasses 失败: {r.stderr[-300:]}")


def compile_sources(package_filter=None, dry_run=False):
    """Compile 03-deobfuscated sources against game-lib.jar + stubs.

    Args:
        package_filter: If set, only compile .java files under this package path
        dry_run: If True, don't actually compile, just show what would be compiled
    """
    if not GAME_LIB.exists():
        print(f"  ERROR: game-lib.jar not found at {GAME_LIB}")
        return []

    # Find source files
    if package_filter:
        src_root = DEOBFUSCATED / package_filter.replace('.', '/')
        if not src_root.exists():
            print(f"  ERROR: Package path not found: {src_root}")
            return []
        java_files = list(src_root.rglob("*.java"))
        print(f"  Compiling {len(java_files)} files in package '{package_filter}'...")
    else:
        java_files = list(DEOBFUSCATED.rglob("*.java"))
        print(f"  Compiling all {len(java_files)} files...")

    if dry_run:
        print(f"  DRY RUN — would compile {len(java_files)} files")
        return []

    if not java_files:
        print("  No files to compile")
        return []

    # Build classpath (forward slashes for javac)
    # NOTE v19.116 尝试: android.jar 提前会暴露 ~2,555 条残留区混淆 android API
    # 调用 (paint.a(...) 等, 03 残留区依赖 game-lib.jar 混淆 android 类), 已回退。
    # v19.132: cache/patched-classes 前置 (android.jar 内部类污染补丁, 见
    # ensure_patched_classes 与 fix_android_innerclasses.py 文档头)。
    ensure_patched_classes()
    cp = str(ROOT / "cache" / "patched-classes").replace('\\', '/')
    cp += os.pathsep + str(GAME_LIB).replace('\\', '/')
    if STUBS_DIR.exists():
        cp += os.pathsep + str(STUBS_DIR).replace('\\', '/')
    # Add real game dependency jars (slick, lwjgl, android, httpclient, etc.)
    if LIBS_DIR.exists():
        for jar in sorted(LIBS_DIR.glob('*.jar')):
            cp += os.pathsep + str(jar).replace('\\', '/')

    # Output directory (forward slashes, separate from source)
    out_dir = str(ROOT / 'build-output').replace('\\', '/')

    # Write file list to temp file (avoid command-line length limits)
    # CRITICAL: Use relative paths with FORWARD slashes. Windows backslashes (\a, \0, etc.)
    # are interpreted as escape sequences by javac's @filelist parser, mangling the paths.
    # Using paths relative to cwd avoids the Chinese-char and space-in-path issues.
    with tempfile.NamedTemporaryFile(mode='w', suffix='.txt', delete=False, encoding='utf-8') as f:
        for jf in java_files:
            rel = str(jf.relative_to(ROOT)).replace('\\', '/')
            f.write(rel + '\n')
        filelist = f.name

    javac = find_javac()
    print(f"  Using javac: {javac}")
    print(f"  Classpath: {cp}")

    try:
        result = subprocess.run(
            [javac, '-encoding', 'UTF-8',
             '-J-Duser.language=en',     # Force English error messages
             '--add-exports', 'java.management/sun.management=ALL-UNNAMED',  # v19.132: DebugUI JVM 反射 hack (02b Debug.java L64)
             '-Xmaxerrs', '100000',      # 上限防截断: 实测真实错误数 >20,000 (5,000 截断曾掩盖)
             '-cp', cp,
             '-d', out_dir,
             '-proc:none', '-nowarn', '-Xlint:none',
             f'@{filelist}'],
            capture_output=True, timeout=600,  # 600s timeout for full build with real jars
            cwd=str(ROOT)
        )
    except subprocess.TimeoutExpired:
        print("  ERROR: Compilation timed out (>120s)")
        return [("TIMEOUT", "", "", "Compilation timed out")]
    finally:
        os.unlink(filelist)

    # v19.133: 手动解码 (text=True 在 Windows 用 gbk 解码子进程输出, 大输出含非 GBK 字节时 UnicodeDecodeError → 假 0 错误)
    try:
        stderr_text = result.stderr.decode('utf-8', errors='replace') if result.stderr else ''
        stdout_text = result.stdout.decode('utf-8', errors='replace') if result.stdout else ''
    except (AttributeError, UnicodeDecodeError):
        stderr_text = result.stderr or ''
        stdout_text = result.stdout or ''

    # Parse errors (English output forced via -J-Duser.language=en)
    # javac 错误块格式 (多行):
    #   path.java:123: error: cannot find symbol
    #       Obj obj = ...
    #            ^
    #     symbol:   class Obj
    #     location: class Foo
    # 新格式捕获 symbol/location 两列 (供 fix_by_bytecode 错误驱动环使用)
    errors = []
    all_text = stderr_text + '\n' + stdout_text
    lines = all_text.split('\n')
    header_re = re.compile(r'^(.+?\.java):(\d+):\s*(error|warning):\s*(.+)$')
    i = 0
    while i < len(lines):
        line = lines[i].strip()
        if not line:
            i += 1
            continue
        m = header_re.match(line)
        if not m:
            # 非错误头部行: 跳过 javac 汇总行 "N errors", 其余含 error 的记 UNKNOWN
            if 'error' in line.lower() and not re.match(r'^\d+\s+errors?\s*$', line):
                errors.append(("UNKNOWN", 0, "error", line, "", ""))
            i += 1
            continue

        filepath = m.group(1).replace('\\', '/')  # Normalize backslashes
        line_num = int(m.group(2))
        msg_type = m.group(3)
        message = m.group(4)
        if msg_type == 'warning':
            i += 1
            continue  # v19.132: 只计 error (VMManagement internal proprietary warning 不阻塞)

        # 块内 (至下一个错误头部) 查找 symbol/location 行
        symbol, location = '', ''
        j = i + 1
        while j < len(lines):
            nxt = lines[j].strip()
            if header_re.match(nxt):
                break
            sm = re.match(r'symbol:\s*(?:class|variable|method)\s+(.+)', nxt)
            if sm:
                symbol = sm.group(1).strip()
            lm = re.match(r'location:\s*(.+)', nxt)
            if lm:
                location = lm.group(1).strip()
            j += 1

        # Make path relative
        try:
            filepath = str(Path(filepath).relative_to(ROOT))
        except ValueError:
            pass

        errors.append((filepath, line_num, msg_type, message, symbol, location))
        i = j

    return errors


def analyze_errors(errors):
    """Group and analyze compilation errors."""
    if not errors:
        return

    # Group by error type
    by_type = defaultdict(int)
    by_file = defaultdict(int)
    extends_errors = []
    unresolved_errors = []

    for filepath, line, msg_type, message, symbol, location in errors:
        by_file[filepath] += 1
        # Classify error
        if 'cannot find symbol' in message:
            by_type['unresolved-symbol'] += 1
            unresolved_errors.append((filepath, line, message))
        elif 'already defined' in message or 'duplicate' in message.lower():
            by_type['duplicate-definition'] += 1
        elif 'expected' in message:
            by_type['syntax-error'] += 1
        elif 'extends' in message or 'implements' in message:
            by_type['extends-implements'] += 1
            extends_errors.append((filepath, line, message))
        else:
            by_type['other'] += 1

    print(f"\n  Error summary:")
    print(f"    Total errors: {len(errors)}")
    print(f"    Files with errors: {len(by_file)}")
    for etype, count in sorted(by_type.items(), key=lambda x: -x[1]):
        print(f"    {etype}: {count}")

    return by_type, by_file, extends_errors, unresolved_errors


def write_errors_csv(errors):
    """Write compile errors to CSV for analysis. 含 symbol/location 列 (v19.0)。"""
    with open(ERRORS_CSV, 'w', newline='', encoding='utf-8') as f:
        writer = csv.writer(f)
        writer.writerow(['file', 'line', 'type', 'message', 'symbol', 'location'])
        for filepath, line, msg_type, message, symbol, location in errors:
            writer.writerow([filepath, line, msg_type, message, symbol, location])
    print(f"\n  Errors written to: {ERRORS_CSV}")


def main():
    dry_run = '--dry-run' in sys.argv
    quick = '--quick' in sys.argv

    # Parse --package flag
    package_filter = None
    for i, arg in enumerate(sys.argv):
        if arg == '--package' and i + 1 < len(sys.argv):
            package_filter = sys.argv[i + 1]
            break

    print("=" * 60)
    print("javac_gate.py — Compile Harness for 03-deobfuscated")
    print("=" * 60)

    # Step 1: Generate stubs
    print("\n[1] Generating stubs...")
    generate_stubs()

    # Step 2: Compile
    print("\n[2] Compiling...")
    if quick:
        if not package_filter:
            package_filter = "com/corrodinggames/rts/game/units/a"  # Default: actions
        print(f"  Quick mode: package '{package_filter}'")
    errors = compile_sources(package_filter=package_filter, dry_run=dry_run)

    if dry_run:
        return

    # Step 3: Analyze
    print("\n[3] Analyzing errors...")
    analyze_errors(errors)

    # Step 4: Output
    print("\n[4] Writing report...")
    write_errors_csv(errors)

    # Exit code
    if errors:
        print(f"\n  GATE: FAILED — {len(errors)} compilation errors")
        sys.exit(1)
    else:
        print(f"\n  GATE: PASSED — 0 compilation errors")
        sys.exit(0)


if __name__ == '__main__':
    main()
