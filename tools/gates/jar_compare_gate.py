#!/usr/bin/env python3
"""逐文件 JAR 对照门禁 (原版 game-lib.jar 为地面真值)。

定位
----
本项目现有门禁都无法回答「**某个 03 java 文件**的成员名对不对」:

| 既有门禁 | 验证的东西 | 能抓成员名跨类误改吗 |
|---|---|---|
| `gates/javac_gate.py` | 03 全树能否编译 | ❌ |
| `utils/b2_reverse_map_check.py` | 映射覆盖率 | ❌ |
| `fixers/reverse_member_census.py` | **类级** (jar 逐条目 + 逐成员) 全量差集 | ✅ 但只有类级台账, 无逐文件结论 |
| 回放校验和一致性 | 确定性 | ❌ |

本工具补上缺的那一环: **一个 03 文件 = 一个独立检查单元**, 逐文件出结论与证据。

地面真值 (两道) 与判据
----------------------
**真值① 原版 jar 字节码成员名** (`RustedWarfare/game-lib.jar`, R8 产物 = 运行时真值)。
**真值② supplement.csv 语义名** (仅当该记录宿主 == 本文件所代表的混淆类时才成立)。

逐文件检查内容:

| 规则 | 判据 | 级别 |
|---|---|---|
| E0 | 类身份解析冲突/死件/黑名单 → 该文件不产出反向源 | REVIEW |
| E1 | 字段声明名既非「本类字节码成员名」, 又非「本类 supplement 记录**成立**的语义名」 → 运行时 `NoSuchFieldError` | **FAIL** |
| E1M | 方法同上 (`javac` 重载差异会先暴露) | **FAIL** |
| E2 | `this.X` 访问在「本类 + 继承链字节码」里不存在 | **FAIL** |
| E3 | 声明名 == 本类 supplement 记录的**混淆名** 且语义名未出现 (残留混淆) | REVIEW |
| E4 | 声明名在本类字节码中无对应, 也无 supplement 记录 → 新增/幻觉成员 | REVIEW |
| E5 | 本类字节码成员已配 supplement 记录 (混淆名→语义名), 但语义名声明缺失 | REVIEW |
| W1 | 源声明成员在本类**已编译产物**字节码中不存在 (硬证据, 需 build/game-lib-reverse.jar) | REVIEW |
| W2 | 类条目字节内容不同 (非成员违规, 仅信息) | INFO |

E1/E1M/E2 是**运行期硬缺陷**; E3/E4/E5 是需要人判的语义残留; W1 是编译产物的硬证据。

零副作用
--------
默认**只读** (只打印中文摘要), 落盘需显式 `--out-dir DIR --write`。
所有遍历显式 `sorted()` → 同一输入两次运行输出逐字节一致。

Usage:
    python tools/gates/jar_compare_gate.py                       # 全量只读摘要
    python tools/gates/jar_compare_gate.py --only librocket/scripts/Root$TableData
    python tools/gates/jar_compare_gate.py --only 03-deobfuscated/com/.../PlayerState.java -v
    python tools/gates/jar_compare_gate.py --out-dir build/_jar_gate --write
    python tools/gates/jar_compare_gate.py --no-built            # 跳过编译产物对照 (快)

退出码: 0 = 门禁跑完 (有失败文件也返回 0, 判据见 --strict); 1 = 输入缺失/javap 失败;
        2 = --strict 且存在 FAIL 级文件。
"""
import argparse
import collections
import csv
import hashlib
import os
import re
import subprocess
import sys
import zipfile
from pathlib import Path

csv.field_size_limit(10 * 1024 * 1024)

# 控制台输出统一 UTF-8 (中文 Windows 默认 GBK, 会把中文摘要写成乱码)
for _s in (sys.stdout, sys.stderr):
    try:
        _s.reconfigure(encoding='utf-8', errors='replace')
    except (AttributeError, ValueError):
        pass

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / 'tools'))

from rwlib.config import (DEOBFUSCATED_DIR, GAME_LIB, ROOT as _R,  # noqa: E402
                          SUPPLEMENT_CSV, find_javap)
from rwlib.tuning import T  # noqa: E402
from fixers.build_reverse_jar import (BLACKLIST, JDK_METHOD_NAMES,  # noqa: E402
                                      NATIVE_BIND_METHODS,
                                      OFFICIAL_IFACE_METHODS,
                                      load_mapping, load_member_hosts,
                                      load_member_map,
                                      load_member_map_by_class)

DEFAULT_REV_JAR = ROOT / 'build' / 'game-lib-reverse.jar'

# ── javap 输出解析 ─────────────────────────────────────────────────
CLASS_HEAD_RE = re.compile(
    r'^(?:(?:public|protected|private|final|abstract|static|strictfp|'
    r'synchronized|transient|volatile)\s+)*'
    r'(?:class|interface|enum)\s+([^\s{<]+)(?:\s+extends\s+([^\s{<]+))?')
DESC_RE = re.compile(r'^    descriptor: (\S+)$')
# javac 合成成员 (原版 R8 产物中不存在 → 不参与「成员名被改坏」判据)
SYNTHETIC_MEMBER_RE = re.compile(
    r'^\$VALUES$|^\$SWITCH_TABLE\$|^this\$|^access\$|^\$assertionsDisabled$|^\$jacocoInit$')

# ── 源文件成员声明提取 ──────────────────────────────────────────────
# 字段: 修饰符 + 类型 + 名 + (= 或 ;)
DECL_FIELD_RE = re.compile(
    r'^[ \t]+(?:(?:public|protected|private|static|final|transient|volatile)\s+)+'
    r'[\w$<>\[\].,?\s]+?\s+([A-Za-z_$][\w$]*)\s*(?:=[^;]*)?;')
# 接口/enum 无修饰符字段
DECL_FIELD_NOMOD_RE = re.compile(
    r'^[ \t]+[\w$<>\[\].]+\s+([A-Za-z_$][\w$]*)\s*(?:=[^;]*)?;')
# 方法: 修饰符 + 返回类型 + 名 + (
DECL_METHOD_RE = re.compile(
    r'^[ \t]+(?:(?:public|protected|private|static|final|synchronized|strictfp|'
    r'native|abstract|default)\s+)+[\w$<>\[\].,?\s]+?\s+([A-Za-z_$][\w$]*)\s*\(')
# 类头 (含嵌套): class/enum/interface 名
CLASS_DECL_RE = re.compile(
    r'^[ \t]*(?:(?:public|protected|private|abstract|final|static|strictfp)\s+)*'
    r'(class|interface|enum)\s+([A-Za-z_$][\w$]*)')
# this.X 成员访问
THIS_ACCESS_RE = re.compile(r'\bthis\s*\.\s*([A-Za-z_$][\w$]*)')

JAVA_KEYWORDS = {
    'abstract', 'assert', 'boolean', 'break', 'byte', 'case', 'catch', 'char',
    'class', 'const', 'continue', 'default', 'do', 'double', 'else', 'enum',
    'extends', 'final', 'finally', 'float', 'for', 'goto', 'if', 'implements',
    'import', 'instanceof', 'int', 'interface', 'long', 'native', 'new',
    'package', 'private', 'protected', 'public', 'return', 'short', 'static',
    'strictfp', 'super', 'switch', 'synchronized', 'this', 'throw', 'throws',
    'transient', 'try', 'void', 'volatile', 'while', 'true', 'false', 'null',
}
# 控制流/表达式行首关键字 —— 排除后避免把语句当成员声明
STMT_PREFIX = {
    'if', 'for', 'while', 'switch', 'catch', 'return', 'throw', 'super',
    'else', 'case', 'default', 'new', 'do', 'try', 'finally', 'break',
    'continue', 'synchronized', 'assert', 'import', 'package',
}
# 任何类都必然继承自 Object 的成员 (javap 不列出, 但 this.X 合法)
OBJECT_MEMBERS = {
    'getClass', 'hashCode', 'equals', 'toString', 'clone', 'notify',
    'notifyAll', 'wait', 'finalize',
}


# ══════════════════════════════════════════════════════════════════
# 1. jar 索引与 javap
# ══════════════════════════════════════════════════════════════════
def jar_class_fqns(jar):
    """→ ({fqn: entry_name}, {fqn: sha256}) — 只含 .class 条目, 确定性。"""
    fqns, shas = {}, {}
    with zipfile.ZipFile(jar) as z:
        for name in sorted(z.namelist()):
            if not name.endswith('.class'):
                continue
            fqn = name[:-len('.class')].replace('/', '.')
            fqns[fqn] = name
            shas[fqn] = hashlib.sha256(z.read(name)).hexdigest()
    return fqns, shas


def parse_javap(text):
    """javap -p -s 输出 → {fqn: {'fields':[(n,d)], 'methods':[(n,d)], 'super':str|None}}。

    缩进口径 (实测, 见 build/_jar_gate/probe_parse.py):
      0 空格 = 类头行 / 类结束 `}`
      2 空格 = 该类(或恰在其内的嵌套类)的**直接成员**声明
      4 空格 = 成员内部行 (descriptor: / throws / 枚举常量等) —— **不是成员**
    故成员识别只认「缩进恰为 2」且非 `descriptor:` 的行, 并在其后的
    `descriptor:` 行落账 (拿到真实描述符)。
    **合成成员排除**: `$VALUES`/`$SWITCH_TABLE$*`/`this$0`/`access$*` 等由 javac 生成,
    原版 R8 产物中不存在, 不属于「成员名被改坏」的判据范围。
    """
    out = {}
    cur, pending = None, None
    for line in text.splitlines():
        if not line:
            continue
        indent = len(line) - len(line.lstrip(' '))
        if indent == 0:
            if line.strip() == '}':
                cur, pending = None, None
                continue
            m = CLASS_HEAD_RE.match(line)
            if m and ('class ' in line or 'interface ' in line or 'enum ' in line):
                cur = m.group(1)
                out.setdefault(cur, {'fields': [], 'methods': [],
                                     'super': m.group(2)})
            pending = None
            continue
        if cur is None:
            continue
        if indent >= 4:
            md = DESC_RE.match(line)
            if md and pending is not None:
                kind, name = pending
                desc = md.group(1)
                # 排除合成成员: ① javac 名 ($VALUES/access$*/this$0/...)
                # ② 枚举的「常量数组」字段本身 (R8 保留原名如 E, javac 改 $VALUES) —
                #    属编译器簿记, 不属「成员名被改坏」判据 (否则误报)
                enum_array = (kind == 'field'
                              and desc == '[L' + cur.replace('.', '/') + ';')
                if not SYNTHETIC_MEMBER_RE.match(name) and not enum_array:
                    out[cur][kind + 's'].append((name, desc))
                pending = None
            continue
        body = line.strip().rstrip(';')
        if not body:
            continue
        body = re.sub(r'\s*=\s*.*$', '', body)
        if body.endswith('{}') or body == 'static {}':
            pending = None
            continue
        if '(' in body:
            pending = ('method', body[:body.index('(')].split()[-1])
        else:
            parts = body.split()
            if parts:
                pending = ('field', parts[-1])
    return out


def javap_batch(jar, fqns, javap, batch=150, known=None):
    """批量 javap -p -s (classpath = jar); → {fqn: parsed}。

    known: 该 jar 中**确实存在**的 FQN 集合 (来自 zipfile 条目枚举)。只对这些调用:
    javap 对不存在的类会整体返回 rc=1, 混在批量里会中断解析 (实测 java/p$64 等)。
    """
    result = {}
    pool = set(fqns)
    todo = sorted(pool if known is None else (pool & set(known)))
    for i in range(0, len(todo), batch):
        chunk = todo[i:i + batch]
        r = subprocess.run(
            [javap, '-p', '-s', '-cp', str(jar)] + chunk,
            capture_output=True, text=True, encoding='utf-8', errors='replace')
        if not r.stdout:
            raise RuntimeError(
                f'javap 失败 (rc={r.returncode}), 无任何输出: {r.stderr[:300]}')
        parsed = parse_javap(r.stdout)
        for fqn in chunk:
            result[fqn] = parsed.get(fqn)
    return result


def build_super_closure(members, fqn, max_depth=None):
    """沿 super 链展开成员名集合 (字段+方法+描述符), 返回 (names, sigs)。

    `max_depth=None` ⇒ 用 `rwlib.tuning` 的**推导值**（默认 0 = 从 jar 字节码推导真实链长）。
    历史缺陷: 此处曾写死 `max_depth=8`, 而单位族真实链长 11 层 ⇒ 继承成员集被截断,
    E2 里出现 318 条「8 层内查不到、12 层就查到」的伪条目。
    """
    if max_depth is None:
        max_depth = T.walk_depth()
    names, sigs = set(), set()
    seen, cur, depth = set(), fqn, 0
    while cur and cur not in seen and depth < max_depth:
        seen.add(cur)
        info = members.get(cur)
        if not info:
            break
        for n, d in info['fields']:
            names.add(n)
            sigs.add(('field', n, d))
        for n, d in info['methods']:
            names.add(n)
            sigs.add(('method', n, d))
        cur = info.get('super')
        depth += 1
    return names, sigs


def make_readable_class_predicate(mapping, supplement_hosts=()):
    """→ 判定「描述符里的类名是否属已知可读(语义)名」的谓词。

    来源只读映射库, 不做猜测性配对:
      ① class-discoveries 的可读类名 (mapping 的键);
      ② supplement 宿主列里出现过的类名 (含只登记在 supplement 的类, 如 `PingTimer`)。
    用途: W3 里当「原版混淆类名 ↔ 产物语义类名」的差异属等价改写时不算缺陷。
    """
    names = set()
    for readable in mapping:
        names.add(readable.split('$')[0])
    for _pkg, cls in supplement_hosts:
        names.add(cls.split('$')[0])
    return lambda cls_slash: cls_slash.split('/')[-1].split('$')[0] in names


def is_synthetic_ctor(cls_simple, name, desc):
    """枚举/普通类的构造器 (parse_javap 输出的成员名是**类名**, 不是 `<init>`):
    javac 生成的枚举构造器带 (String,int[,值…]) 形参, 而 R8 侧常只剩 (String,int)
    → 直接比描述符会误报; 且枚举构造器不可被显式调用, NoSuchMethodError 无从发生。
    """
    if name != cls_simple and name != '<init>':
        return False
    return desc.startswith('(Ljava/lang/String;I')


# ══════════════════════════════════════════════════════════════════
# 2. 源文件解析
# ══════════════════════════════════════════════════════════════════
def strip_noise(src):
    """去注释/字符串/字符字面量, 且**保持花括号计数不变** (关键)。

    实现要点: 用一个小状态机整体扫描, 而不是逐行正则 —— 字符串字面量里会出现
    `{`/`}`/`;` (如 `"{"`), 逐行处理会让 brace_depths() 失准, 进而把方法体里的
    局部变量误判成类字段 (实测: LibRocketBridge 的 matcher/bl/string2 全部误报)。
    字符串/注释内容替换为空格 (换行保留), 以便行号一一对应。
    """
    out = []
    i, n = 0, len(src)
    while i < n:
        c = src[i]
        if c == '/' and i + 1 < n and src[i + 1] == '/':
            while i < n and src[i] != '\n':
                i += 1
        elif c == '/' and i + 1 < n and src[i + 1] == '*':
            i += 2
            while i + 1 < n and not (src[i] == '*' and src[i + 1] == '/'):
                out.append('\n' if src[i] == '\n' else ' ')
                i += 1
            i = min(i + 2, n)
        elif c == '"':
            out.append(' ')          # 开引号本身也抹掉
            i += 1
            while i < n and src[i] != '"':
                if src[i] == '\\' and i + 1 < n:
                    out.append(' ')
                    i += 1
                out.append('\n' if src[i] == '\n' else ' ')
                i += 1
            if i < n:
                out.append(' ')
                i += 1
        elif c == "'":
            out.append(' ')
            i += 1
            while i < n and src[i] != "'":
                if src[i] == '\\' and i + 1 < n:
                    out.append(' ')
                    i += 1
                out.append(' ')
                i += 1
            if i < n:
                out.append(' ')
                i += 1
        else:
            out.append(c)
            i += 1
    return ''.join(out)


def brace_depths(src):
    """→ [(line_start_depth, line_end_depth)] — 逐字符扫描 (跳过字符串/注释)。

    line_start_depth = 该行第一个字符之前的 {} 深度。
    顶层类体成员 = 1, 嵌套类体成员 = 2, 方法体内局部变量 >= 2 → 用 <=2 且
    「非方法体」判据时必须靠这个精确值 (逐行数括号会错位)。
    """
    starts, depth, line_start = [], 0, 0
    i, n = 0, len(src)
    while i < n:
        c = src[i]
        if c == '\n':
            starts.append((line_start, depth))
            line_start = depth
            i += 1
        elif c == '/' and i + 1 < n and src[i + 1] == '/':
            while i < n and src[i] != '\n':
                i += 1
        elif c == '/' and i + 1 < n and src[i + 1] == '*':
            i += 2
            while i + 1 < n and not (src[i] == '*' and src[i + 1] == '/'):
                if src[i] == '\n':
                    starts.append((line_start, depth))
                    line_start = depth
                i += 1
            i = min(i + 2, n)
        elif c in '"\'':
            quote, i = c, i + 1
            while i < n and src[i] != quote:
                if src[i] == '\\' and i + 1 < n:
                    i += 1
                if src[i] == '\n':
                    starts.append((line_start, depth))
                    line_start = depth
                i += 1
            i += 1
        else:
            if c == '{':
                depth += 1
            elif c == '}':
                depth -= 1
            i += 1
    starts.append((line_start, depth))   # 末行 (无换行结尾)
    return starts


def extract_source_members(src):
    """→ (decls, class_headers, this_accesses, balanced)。

    decls:         [(line, kind, name)]  kind='field'|'method'
    class_headers: [(line, name)]        含嵌套类 (按行号升序)
    this_accesses: [(line, name)]
    balanced:      花括号是否配平 (False → 深度判据不可信, 调用方应降级)

    关键判据 (为什么不能只看深度): CFR 输出中方法体内部语句的行首深度同样是 2,
    与顶层类的**成员**行深度相同 (实测: LibRocketBridge 的 matcher/bl/string2 全被
    误判成字段)。本实现改为「**类体区间**」判据:
      类头行 (行首深度 d)
        → 向下找类体 `{` (最多 8 行, 遇 `;` 视为非类声明)
        → 类体区间 = (成员起始行, 配对 `}` 行]
        → 区间内**行首深度恰为 d+1** 的声明行 = 该类直接成员
      (方法体内部深度 ≥ d+2 → 自然排除; 嵌套类成员挂在嵌套类区间内)
    """
    clean = strip_noise(src)
    lines = clean.split('\n')
    starts = brace_depths(src)
    balanced = (len(starts) >= len(lines) and starts[len(lines) - 1][1] == 0)
    scan_limit = min(len(lines), len(starts))

    def depth_at(i):
        return starts[i][0] if i < len(starts) else 0

    class_bodies = []      # [(start_line_idx, end_line_idx, 成员行首深度, 类名)]
    headers = []
    for idx in range(scan_limit):
        m_cls = CLASS_DECL_RE.match(lines[idx])
        if not m_cls:
            continue
        d = depth_at(idx)
        brace_at = idx if '{' in lines[idx] else None
        if brace_at is None:
            for j in range(idx + 1, min(idx + 9, scan_limit)):
                if '{' in lines[j]:
                    brace_at = j
                    break
                if ';' in lines[j]:
                    break
        if brace_at is None:
            continue
        # 配对 `}`: 从 `{` 起数括号, 回到 0 为止 (跳过字符串/注释, 用去噪后文本)
        bal, end = 0, None
        for j in range(brace_at, scan_limit):
            for ch in lines[j]:
                if ch == '{':
                    bal += 1
                elif ch == '}':
                    bal -= 1
            if bal <= 0:
                end = j
                break
        if end is None:
            continue
        headers.append((idx + 1, m_cls.group(2)))
        # 成员起始行: `{` 之后有内容 → 本行; 否则 → 下一行
        after = lines[brace_at][lines[brace_at].index('{') + 1:].strip()
        body_start = brace_at if after else brace_at + 1
        class_bodies.append((body_start, end, d + 1, m_cls.group(2)))

    # 行 → 允许出现直接成员的最内层类体区间 (取最内层 = 行号区间最小的那个)
    def member_owner(idx):
        best = None
        for (bs, be, want_depth, name) in class_bodies:
            if bs <= idx < be:
                if best is None or (be - bs) < (best[1] - best[0]):
                    best = (bs, be, want_depth, name)
        return best

    decls, accesses = [], []
    for idx in range(scan_limit):
        line = lines[idx]
        lineno = idx + 1
        stripped = line.strip()
        if not stripped:
            continue
        owner = member_owner(idx)
        if owner is not None and depth_at(idx) == owner[2]:
            head = stripped.split()[0].rstrip('(') if stripped.split() else ''
            if head not in STMT_PREFIX:
                m = DECL_METHOD_RE.match(line)
                if m:
                    decls.append((lineno, 'method', m.group(1)))
                else:
                    m = DECL_FIELD_RE.match(line)
                    if not m:
                        m = DECL_FIELD_NOMOD_RE.match(line)
                    if m and m.group(1) not in JAVA_KEYWORDS:
                        decls.append((lineno, 'field', m.group(1)))
        for am in THIS_ACCESS_RE.finditer(line):
            accesses.append((lineno, am.group(1)))
    return decls, headers, accesses, balanced


# ══════════════════════════════════════════════════════════════════
# 3. 类身份解析 (与 build_reverse_jar.py D-7 同口径)
# ══════════════════════════════════════════════════════════════════
def collect_entries(mapping):
    """逐文件收集身份候选 (不改写, 不写盘)。"""
    entries = []
    for java in sorted(DEOBFUSCATED_DIR.rglob('*.java')):
        rel = java.relative_to(DEOBFUSCATED_DIR).as_posix()
        if rel in BLACKLIST:
            # 2026-09-24 修复：黑名单条目也必须带 src —— 下游 check_file() 先取 entry['src']
            # 再判 dropped，缺键会 KeyError 崩门禁（本分支长期未触发，是因为老键全部失效，见 PENDING #53）
            entries.append(dict(rel=rel, java=java,
                                src=java.read_text(encoding='utf-8', errors='ignore'),
                                cls03=java.stem, pkg03=java.parent.as_posix().split('03-deobfuscated/')[-1].replace('/', '.'),
                                tgt=None, suffix2='', dropped='黑名单'))
            continue
        src = java.read_text(encoding='utf-8', errors='ignore')
        stem = java.stem
        m = re.search(
            r'^(\s*(?:public |protected |final |abstract |strictfp |static )*'
            r'(?:class|enum|interface) )([\w$]+)', src, re.M)
        cls03 = m.group(2) if m else stem
        outer03 = cls03.split('$')[0]
        suffix = cls03[len(outer03):]
        pkg03 = java.parent.as_posix().split('03-deobfuscated/')[-1].replace('/', '.')
        direct = mapping.get(cls03, [])
        if direct:
            cands, suffix2 = direct, ''
        elif suffix:
            cands, suffix2 = mapping.get(outer03, []), suffix
        else:
            cands, suffix2 = [], ''
        tgt = None
        if len(cands) == 1:
            tgt = cands[0]
        elif cands:
            best, bl = None, -1
            for p2, o2 in cands:
                c = sum(1 for a, b in zip(p2.split('.'), pkg03.split('.')) if a == b)
                if c > bl:
                    bl, best = c, (p2, o2)
            tgt = best
        entries.append(dict(rel=rel, java=java, src=src, cls03=cls03,
                            pkg03=pkg03, tgt=tgt, suffix2=suffix2, dropped=None))
    # D-7 判据① 同名竞争消歧
    top_claims = collections.defaultdict(list)
    for e in entries:
        if e.get('dropped'):
            continue
        if e['tgt'] and not e['suffix2']:
            top_claims[e['tgt']].append(e)
    exact_owner = {}
    for key, es in sorted(top_claims.items()):
        exact = [e for e in es if e['pkg03'] == key[0]]
        if len(exact) == 1:
            exact_owner[key] = exact[0]
            if len(es) > 1:
                for e in es:
                    if e is not exact[0]:
                        e['tgt'] = None
                        e['dropped'] = 'D-7 同目标顶层竞争让位'
    # D-7 判据② 内层类归属约束
    for e in entries:
        if e.get('dropped') or not e['tgt'] or not e['suffix2']:
            continue
        win = exact_owner.get(e['tgt'])
        if win is None or e['pkg03'] == win['pkg03']:
            continue
        outer_name = e['cls03'].split('$')[0]
        refs = len(re.findall(r'(?<![\w$])' + re.escape(outer_name) + r'(?![\w$])',
                              e['src']))
        if refs > 1:
            continue
        e['dropped'] = 'D-7 内层类归属目录不符'
    return entries


# ══════════════════════════════════════════════════════════════════
# 4. 单文件检查
# ══════════════════════════════════════════════════════════════════
def check_file(entry, ctx):
    """检查单个 03 文件 → (findings, info)。

    findings: [(rule, level, line, kind, name, evidence)]
    info: dict 统计信息
    """
    rel, src = entry['rel'], entry.get('src', '')   # 2026-09-24: 容错，黑名单条目曾缺 src（见 PENDING #53）
    cls03, pkg03, tgt, suffix2 = (entry['cls03'], entry['pkg03'],
                                  entry['tgt'], entry['suffix2'])
    findings = []
    info = {'ref_fqn': '', 'rev_fqn': '', 'ref_members': 0, 'src_decls': 0,
            'byte_state': '无对应'}

    if entry.get('dropped'):
        findings.append(('E0', 'REVIEW', 0, 'class', cls03,
                         f"类身份未解析/让位: {entry['dropped']} → 该文件不产出反向源"))
        return findings, info

    decls, headers, accesses, balanced = extract_source_members(src)
    info['src_decls'] = len(decls)
    if not balanced:
        findings.append(('E0', 'REVIEW', 0, 'class', cls03,
                         '源花括号不配平 → 成员深度判据不可信, 本文件成员级结论仅供参考'))

    # ── 类身份: 有类映射 → 映射目标; 无类映射但原版有同名类 → **该文件自身的
    #    混淆身份** (03 源码顶替重编译原版类: 139 文件, 此前完全不校验成员名) ──
    no_map = not tgt
    if tgt:
        pkg02, obf02 = tgt
        obf_full = obf02 + suffix2
    else:
        fqn_self = pkg03.replace('.', '/') + '/' + cls03
        if fqn_self not in ctx['ref_fqns']:
            info['ref_fqn'] = fqn_self
            info['byte_state'] = '03 重建类 (原版 jar 无)'
            findings.append(('E0', 'INFO', 0, 'class', cls03,
                             f'{fqn_self} 不在原版 jar → 03 重建类, 成员名无地面真值可比对'))
            return findings, info
        pkg02, obf02 = pkg03, cls03
        obf_full = cls03
    pkg02_slash = pkg02.replace('.', '/')
    ref_fqn = pkg02_slash.replace('/', '.') + '.' + obf_full
    rev_fqn = ref_fqn            # 反向类身份 = 原版类身份 (D-7 已保证一一对应)
    rev_entry = pkg02_slash + '/' + obf_full + '.class'
    info['ref_fqn'] = ref_fqn
    info['rev_fqn'] = rev_fqn

    # ── 真值① 原版 jar 本类成员 ──────────────────────────────────
    ref_info = ctx['ref_members'].get(ref_fqn)
    # jar 无此类 = 03 重建类 (该类是新增的, 成员无从对照) → REVIEW 而非缺陷
    ref_absent = ref_fqn not in ctx['ref_fqns']
    if ref_info is None:
        findings.append(('E0', 'INFO' if ref_absent else 'REVIEW', 0, 'class', cls03,
                         (f'原版 jar 中无此类 {ref_fqn} → 03 重建类, 成员名无地面真值可比对'
                          if ref_absent else
                          f'类映射目标 {ref_fqn} 不在原版 jar 且非可解析条目 (映射可疑)')))
        return findings, info
    ref_names, ref_sigs = build_super_closure(ctx['ref_members'], ref_fqn)
    own = ctx['ref_members'][ref_fqn]
    info['ref_members'] = len(own['fields']) + len(own['methods'])
    own_names = {n for n, _ in own['fields']} | {n for n, _ in own['methods']}

    # ── 真值② supplement 本宿主记录 (仅宿主==本类才成立) ──────────
    host_key = (pkg02, obf02)
    sem2obf_f = ctx['per_f'].get(host_key, {})   # 语义名 → 混淆名 (本宿主, 唯一)
    sem2obf_m = ctx['per_m'].get(host_key, {})
    obf2sem_f = {v: k for k, v in sem2obf_f.items()}
    obf2sem_m = {v: k for k, v in sem2obf_m.items()}
    # 全库任意宿主记录 (仅用于证据描述, 不作成立判据)
    any_f = ctx['fmap']
    any_m = ctx['mmap']

    declared = set()
    for lineno, kind, name in decls:
        declared.add(name)
        sem_map = sem2obf_f if kind == 'field' else sem2obf_m
        obf_map_any = any_f if kind == 'field' else any_m
        other = sem2obf_m if kind == 'field' else sem2obf_f
        # ── 第一级: 解码链判据 (最强, 与反向器声明侧行为一一对应) ──────
        # 源名 --(反向器实际映射)--> 产物名, 产物名必须在原版字节码里:
        #   ① 本宿主精确映射 (per_map) 优先 → 产物名 = sem_map[name]
        #   ② 否则全局映射 (mmap/fmap, 未过滤) → 产物名 = obf_map_any[name]
        #   ③ 都没有 → 产物名 = name 本身
        eff = name
        via = '无映射 (恒等)'
        if name in sem_map:
            eff, via = sem_map[name], f'本宿主记录 {name}→{sem_map[name]}'
        elif name in obf_map_any and obf_map_any[name] != name:
            eff, via = obf_map_any[name], f'全局映射 {name}→{obf_map_any[name]}'
        prod_names = own_names
        if ctx['rev_jar'] is not None and info['rev_fqn'] in ctx['rev_members']:
            ri = ctx['rev_members'][info['rev_fqn']]
            prod_names = {n for n, _ in ri['fields']} | {n for n, _ in ri['methods']}
        # ── 产物确证改名 (最强, 硬缺陷): 源名就是原版字节码成员名, 但编译产物
        #    把它改成了 eff → 成品 jar 里该成员名与原版不一致, 运行期必崩。
        #    (P1 缺陷族 rows→h 的最终形态; 与 W1 同轴, 但按文件/成员给出定位) ──
        if (name in own_names and eff != name and eff in prod_names
                and name not in prod_names):
            err = ('NoSuchFieldError' if kind == 'field' else 'NoSuchMethodError')
            findings.append(('E1D' if kind == 'field' else 'E1DM', 'FAIL',
                             lineno, kind, name,
                             f'**产物确证**: 原版字节码 @{ref_fqn} 的成员名就是 {name!r}, '
                             f'但编译产物里它变成了 {eff!r} (解码链 {via}) → 成品与原版'
                             f'不一致; 运行期 {err}: {name}'))
            continue
        if eff not in own_names and eff not in prod_names:
            flds = ", ".join(sorted(n for n, _ in own['fields']))[:160]
            findings.append(('E1D' if kind == 'field' else 'E1DM', 'REVIEW',
                             lineno, kind, name,
                             f'源级预测: 解码链 {via} ⇒ 产物名 {eff!r}, 而原版字节码 '
                             f'@{ref_fqn} 与本类产物都无该成员 (真实字段: {flds or "(无)"}) '
                             f'→ 若该映射确实被套用则运行期崩'
                             f'; 运行期 {"NoSuchFieldError" if kind == "field" else "NoSuchMethodError"}: {eff}'))
            continue
        # ── 第二级: 语义级判据 (解码链已过) ────────────────────────────
        if name in own_names:
            continue   # 源名 == 字节码真实成员名 → 完全正确
        if name in sem_map:
            exp = sem_map[name]
            if exp in own_names:
                # 源写语义名 name, 而字节码成员名是 exp → **这正是正确的解混淆**:
                # 声明侧按本宿主记录反向后产物名 = exp = 字节码真名 → 运行时正确。
                continue
            findings.append(('E1' if kind == 'field' else 'E1M', 'FAIL',
                             lineno, kind, name,
                             f'本宿主记录 {name}→{exp} 成立 (真值②), 但原版字节码里**既无** '
                             f'{name!r} 也**无** {exp!r} → 记录与字节码不符, 反向会产出'
                             f'原版不存在的成员名'))
            continue
        # (全局映射的情形已由第一级「解码链」判据覆盖: eff = obf_map_any[name])
        if name in other:
            findings.append(('E-KIND?', 'REVIEW', lineno, kind, name,
                             f'{name} 是**另一命名空间** ({("方法" if kind == "field" else "字段")}) '
                             f'的语义名 → {other[name]}, 命名空间可能串用'))
            continue
        if name in obf2sem_f or name in obf2sem_m:
            findings.append(('E-KIND?', 'REVIEW', lineno, kind, name,
                             f'{name} 是混淆名 (存在语义名记录) → 疑似反向未落地'))
            continue
        # 无任何映射证据: 既不在字节码, 又无记录
        # 03 重建类 (jar 无此类) 的成员天然无出处 → INFO; 否则需人判
        if name in ctx['reverse_safe']:
            findings.append(('E3', 'REVIEW', lineno, kind, name,
                             f'{name} 在字节码中不存在, 仅因「反向安全名」(豁免/撞车剔除/'
                             f'无唯一映射) 而被保留 — 需确认本类语义'))
        elif ref_absent:
            findings.append(('E4', 'INFO', lineno, kind, name,
                             f'{name} 在 supplement 无出处, 但本类是 03 重建类 '
                             f'(jar 无) → 成员名无地面真值'))
        else:
            findings.append(('E4', 'REVIEW', lineno, kind, name,
                             f'{name} 在原版字节码与 supplement **都无出处** → '
                             f'新增/幻觉成员候选'))
        continue

    # ── E2: this.X 访问必须在本类作用域内存在 ────────────────────
    #
    # 作用域判据 (三道依次放宽, 避免误报「内部类访问外壳私有成员」):
    #   ① 该访问所在类的继承链字节码
    #   ② 最外层类的继承链 (外层私有成员对内层类可见)
    #   ③ **本文件内**任何类的字节码 (嵌套类互相访问)
    # 三道全不命中 → 该 `this.X` 必然指向别类成员 (跨类误改) → FAIL。
    hdrs = sorted(headers)
    top_fqn = ref_fqn

    def enclosing_fqn(lineno):
        """→ 该行所属类的 FQN (按最近的类头归属)。"""
        owner = None
        for hline, hname in hdrs:
            if hline <= lineno:
                owner = hname
            else:
                break
        if owner is None or owner == cls03:
            return top_fqn
        if owner.startswith(cls03 + '$'):
            return top_fqn + owner[len(cls03):]
        return f'{top_fqn}${owner}'

    # ③ 本文件全部类的作用域名集合
    file_scope = set()
    scope_fqns = {top_fqn}
    for _, hname in hdrs:
        bridge = top_fqn + '$' + hname
        if hname.startswith(cls03 + '$'):
            bridge = top_fqn + hname[len(cls03):]
        scope_fqns.add(bridge)
    for f in sorted(scope_fqns):
        # $N 匿名类: 用外壳解析
        base = f
        while base not in ctx['ref_members'] and '$' in base:
            base = base.rsplit('$', 1)[0]
        if base in ctx['ref_members']:
            names, _ = build_super_closure(ctx['ref_members'], base)
            file_scope |= names
    # 源自身声明的名字 (含未进入字节码的) 也算本文件作用域
    file_scope |= declared

    for lineno, name in accesses:
        owner_fqn = enclosing_fqn(lineno)
        # 深层匿名类 (R$1$1) 逐级回退到可解析外壳
        probe = owner_fqn
        while probe not in ctx['ref_members'] and '$' in probe:
            probe = probe.rsplit('$', 1)[0]
        names = set()
        if probe in ctx['ref_members']:
            names, _ = build_super_closure(ctx['ref_members'], probe)
        if name in names or name in file_scope or name in OBJECT_MEMBERS:
            continue
        # 成员访问侧会是**语义名**: 只要该名在 supplement 里作为语义名出现
        # (任意宿主), 就可能是合法的语义化访问 → 不下硬结论, 只登记待判
        if name in ctx['sem_names']:
            findings.append(('E2', 'REVIEW', lineno, 'access', name,
                             f'this.{name} 不在 {owner_fqn} 的字节码命名空间 (原版名/'
                             f'继承链) 也不在本文件声明中, 但 supplement 中存在语义名 '
                             f'{name} → 语义化访问与字节码名不一致, 需逐个核对'))
            continue
        # 溯源证据: 若有别宿主语义名记录把 name 映到本类真实成员名
        hint = ''
        if name in sem2obf_f and sem2obf_f[name] in own_names:
            hint = (f'; supplement 记录 ({host_key[0]}.{host_key[1]}) '
                    f'{name}→{sem2obf_f[name]} 成立, 字节码成员名应为 {sem2obf_f[name]}')
        elif name in sem2obf_m and sem2obf_m[name] in own_names:
            hint = (f'; supplement 记录 ({host_key[0]}.{host_key[1]}) '
                    f'{name}→{sem2obf_m[name]} 成立, 字节码成员名应为 {sem2obf_m[name]}')
        # 归为**辅助判据** (REVIEW): 成员访问侧的名称可能是语义名/继承自未解析的
        # 外部类, 本门禁无法在源级完全判定, 故不给出硬结论, 只登记待核对。
        findings.append(('E2', 'REVIEW', lineno, 'access', name,
                         f'this.{name} 不在 {owner_fqn} 的字节码命名空间 (继承链/外壳类/'
                         f'本文件声明/语义名表) → 疑跨类误改, 需逐项核对{hint}'))

    # ── E5: 本宿主记录 (混淆名→语义名) 成立, 但源未声明该语义名 ──────
    # 两种可能: ① 源**已声明混淆名** (合法: 该类未语义化) → INFO;
    #           ② 源既无语义名也无混淆名 (成员真缺失) → REVIEW
    for kind_tag, obf2sem in (('field', obf2sem_f), ('method', obf2sem_m)):
        for obf, sem in sorted(obf2sem.items()):
            if obf not in own_names or sem in declared or sem.startswith('<'):
                continue
            lvl = 'INFO' if obf in declared else 'REVIEW'
            findings.append(('E5', lvl, 0, kind_tag, sem,
                             f'本宿主记录 {sem}→{obf} 成立 (字节码有 {obf}), '
                             + (f'源已声明混淆名 {obf} (该类未语义化, 属正常)'
                                if obf in declared else
                                f'源既未声明 {sem} 也未声明 {obf} → 成员可能真缺失')))

    # ── W1/W3: 编译产物对照 (硬门禁轴: 产物 vs 地面真值逐成员) ──────
    # 注意 ctx['rev_entries'] 是 {FQN: 条目名} 映射, 键是**点分 FQN**
    if ctx['rev_jar'] is not None:
        if rev_fqn in ctx['rev_entries']:
            rev_info = ctx['rev_members'].get(rev_fqn)
            if rev_info is not None:
                rev_names = ({n for n, _ in rev_info['fields']}
                             | {n for n, _ in rev_info['methods']})
                info['byte_state'] = ('同' if ctx['ref_shas'].get(ref_fqn)
                                      == ctx['rev_shas'].get(rev_fqn) else '异')
                # 硬结论: 原版类的成员名必须在编译产物中以同名存在 (改名即运行期崩)
                own_field_names = {n for n, _ in own['fields']}
                for name in sorted(own_names):
                    if name not in rev_names and not name.startswith('<'):
                        err = ('NoSuchFieldError' if name in own_field_names
                               else 'NoSuchMethodError')
                        findings.append(('W1', 'FAIL', 0, 'member', name,
                                         f'原版字节码成员 {name!r} 在**编译产物** {rev_fqn} 中'
                                         f'不存在 (产物成员名: {sorted(rev_names)[:14]}) '
                                         f'→ 该成员被反向改名, 运行期 {err}: {name}'))
                # W3 描述符级: 同名但**类型/参数不同**。
                #   枚举类整体跳过构造器比对: javac 枚举构造器恒为 (String,int[,值…]),
                #   而 R8 侧常只剩合成的 (String,int); 枚举构造器**不可被显式调用**,
                #   故该差异不会产生运行期错误 (实测 6/8 条属此, 均为误报)。
                #   其余情况若差异只来自「原版混淆类名 ↔ 产物语义名」的等价改写 → 同样跳过;
                #   仍存疑者只登记 REVIEW (类映射本身可能已被订正, 如 av 的映射由
                #   PingTimer 更正为 NullMusicPlayer, 属 KB 侧知识, 门禁不下硬结论)。
                is_readable = ctx['is_readable_class']
                is_enum = (own.get('super') or '').endswith('java.lang.Enum')

                def cls_names(d):
                    return set(re.findall(r'L([\w$/]+);', d or ''))

                for kind_tag, key in (('field', 'fields'), ('method', 'methods')):
                    for name, desc in own.get(key, []):
                        if name.startswith('<'):
                            continue
                        if kind_tag == 'method' and is_enum and is_synthetic_ctor(
                                ref_fqn.rsplit('.', 1)[-1], name, desc):
                            continue
                        rev_pairs = [d for n, d in rev_info.get(key, []) if n == name]
                        if not rev_pairs or desc is None:
                            continue          # 名字缺失已由 W1 报出, 此处只比同名的
                        if desc in rev_pairs:
                            continue          # 完全相同 → 通过
                        ref_other = {c for c in cls_names(desc) if not is_readable(c)}
                        same_shape = any(
                            re.sub(r'L[\w$/]+;', 'L?;', desc)
                            == re.sub(r'L[\w$/]+;', 'L?;', d)
                            and {c for c in cls_names(d) if not is_readable(c)} == ref_other
                            for d in rev_pairs)
                        if same_shape:
                            continue
                        findings.append(('W3', 'REVIEW', 0, kind_tag, name,
                                         f'原版 {kind_tag} {name!r} 描述符 {desc} 与编译产物同名'
                                         f'描述符 {sorted(rev_pairs)[:4]} 不一致 → 需核对是否'
                                         f'仅为类名改写 (含已订正的类映射)'))
            else:
                info['byte_state'] = '产物解析失败'
        else:
            info['byte_state'] = '编译产物缺条目'
            findings.append(('W1', 'REVIEW', 0, 'class', cls03,
                             f'编译产物 jar 中无条目 {rev_entry} → 该类未参与编译 '
                             f'(走 build-skip 由原版 jar 提供, 或 D-7 让位)'))
    return findings, info


# ══════════════════════════════════════════════════════════════════
# 5. 主流程
# ══════════════════════════════════════════════════════════════════
def main():
    ap = argparse.ArgumentParser(
        description='逐文件 JAR 对照门禁 (原版 game-lib.jar 为地面真值)',
        formatter_class=argparse.RawDescriptionHelpFormatter,
        epilog='默认只读; 落盘需 --out-dir DIR --write; --strict 时 FAIL 返回 2')
    ap.add_argument('--ref-jar', default=str(GAME_LIB), help='地面真值 jar')
    ap.add_argument('--rev-jar', default=str(DEFAULT_REV_JAR),
                    help='编译产物 jar (默认 build/game-lib-reverse.jar)')
    ap.add_argument('--no-built', action='store_true', help='跳过编译产物对照')
    ap.add_argument('--only', default='', help='只检查路径/类名匹配该子串的文件')
    ap.add_argument('--limit', type=int, default=0, help='最多检查 N 个文件 (0=全部)')
    ap.add_argument('--out-dir', default=str(ROOT / 'build' / '_jar_gate'),
                    help='台账输出目录')
    ap.add_argument('--write', action='store_true', help='显式落盘 (默认只读)')
    ap.add_argument('--strict', action='store_true', help='存在 FAIL 时退出码 2')
    ap.add_argument('-v', '--verbose', action='store_true', help='打印 FINDING 明细')
    args = ap.parse_args()

    ref_jar, rev_jar = Path(args.ref_jar), Path(args.rev_jar)
    if not ref_jar.exists():
        print(f'错误: 地面真值 jar 不存在: {ref_jar}', file=sys.stderr)
        return 1
    javap = find_javap()
    print(f'地面真值 jar : {ref_jar}')
    print(f'编译产物 jar : {rev_jar if (rev_jar.exists() and not args.no_built) else "(不参与)"}')
    print(f'javap        : {javap}')

    ref_fqns, ref_shas = jar_class_fqns(ref_jar)
    use_rev = (not args.no_built) and rev_jar.exists()
    if use_rev:
        rev_entries, rev_shas = jar_class_fqns(rev_jar)
    else:
        rev_entries, rev_shas = {}, {}

    print(f'原版 class 条目 = {len(ref_fqns)} | 编译产物 = {len(rev_entries)}')

    # ── 映射与过滤集 (与 build_reverse_jar.py 同源) ────────────────
    mapping = {k: [(p.replace('/', '.'), o) for p, o in v]
               for k, v in load_mapping().items()}
    mmap, fmap = load_member_map()
    per_m, per_f = load_member_map_by_class()
    print(f'类映射(可读名) = {len(mapping)} | 全局成员映射 = 方法 {len(mmap)} / 字段 {len(fmap)}')
    print(f'宿主映射 = 方法 {len(per_m)} 类 / 字段 {len(per_f)} 类')

    # 反向安全名: 全局撞车剔除 / 原生绑定 / 官方接口 / JDK 方法
    # (这些语义名在反向时被有意保留 → 出现在源里属正常)
    reverse_safe = (set(NATIVE_BIND_METHODS) | set(OFFICIAL_IFACE_METHODS)
                    | set(JDK_METHOD_NAMES))

    # ── 类身份解析 (D-7 同口径) ────────────────────────────────────
    entries = collect_entries(mapping)
    print(f'03 文件 = {len(entries)} (黑名单 {sum(1 for e in entries if (e.get("dropped") or "") == "黑名单")} / '
          f'D-7 让位 {sum(1 for e in entries if (e.get("dropped") or "").startswith("D-7"))})')

    if args.only:
        entries = [e for e in entries if args.only in e['rel']]
        print(f'--only 过滤后 = {len(entries)} 个文件')
    if args.limit:
        entries = entries[:args.limit]

    # ── 待 javap 的类集合 (仅涉及的文件, 按需) ────────────────────
    # 只收**原版 jar 条目里确实存在**的 FQN (javap 对不存在的类整体 rc=1)
    ref_need = set()
    for e in entries:
        if not e.get('tgt'):
            continue
        pkg02, obf02 = e['tgt']
        fqn = pkg02.replace('.', '/').replace('/', '.') + '.' + obf02 + e['suffix2']
        if fqn in ref_fqns:
            ref_need.add(fqn)
    # 需要 super 链 → 反复补齐直至无新增 (最多 4 轮)
    ref_members = {}
    for _ in range(4):
        got = javap_batch(ref_jar, sorted(ref_need - set(ref_members)), javap,
                          known=ref_fqns)
        ref_members.update({k: v for k, v in got.items() if v})
        new_need = set()
        for v in ref_members.values():
            s = v.get('super')
            if s and s not in ref_members and s in ref_fqns:
                new_need.add(s)
        if not new_need:
            break
        ref_need |= new_need
    print(f'javap 解析原版类 = {len(ref_members)} (请求 {len(ref_need)})')

    rev_members = {}
    if use_rev:
        rev_need = set()
        for e in entries:
            if not e.get('tgt'):
                continue
            pkg02, obf02 = e['tgt']
            fqn = pkg02.replace('.', '/').replace('/', '.') + '.' + obf02 + e['suffix2']
            if fqn in rev_entries:          # rev_entries: {FQN: 条目名}
                rev_need.add(fqn)
        got = javap_batch(rev_jar, sorted(rev_need), javap, known=rev_entries.keys())
        rev_members = {k: v for k, v in got.items() if v}
        print(f'javap 解析编译产物类 = {len(rev_members)} (请求 {len(rev_need)})')

    # supplement 宿主列 (供 W3 识别「已知可读类名」)
    supp_hosts = set()
    with open(SUPPLEMENT_CSV, encoding='utf-8') as f:
        rd = csv.reader(f)
        next(rd, None)
        for r in rd:
            if len(r) >= 5 and r[0] in ('field', 'method'):
                supp_hosts.add((r[1].strip(), r[2].strip()))
    print(f'supplement 宿主 (包,类) 组合 = {len(supp_hosts)}')

    ctx = dict(ref_fqns=ref_fqns, ref_shas=ref_shas, ref_members=ref_members,
               rev_entries=rev_entries, rev_shas=rev_shas, rev_members=rev_members,
               rev_jar=rev_jar if use_rev else None, mapping=mapping,
               mmap=mmap, fmap=fmap, per_m=per_m, per_f=per_f,
               reverse_safe=reverse_safe,
               is_readable_class=make_readable_class_predicate(mapping, supp_hosts),
               sem_names=(set(mmap) | set(fmap)))

    # ── 逐文件检查 ────────────────────────────────────────────────
    file_rows, find_rows = [], []
    cats = collections.Counter()
    lv = collections.Counter()
    for i, e in enumerate(entries, 1):
        findings, info = check_file(e, ctx)
        levels = {f[1] for f in findings}
        if 'FAIL' in levels:
            verdict = 'FAIL'
        elif 'REVIEW' in levels:
            verdict = 'REVIEW'
        else:
            verdict = 'PASS'   # 仅 INFO 亦视为通过
        file_rows.append([e['rel'], e['cls03'], e['pkg03'],
                          (e['tgt'][0] + '.' + e['tgt'][1]) if e['tgt'] else '',
                          info['ref_fqn'], verdict, len(findings),
                          info['ref_members'], info['src_decls'], info['byte_state']])
        for rule, level, lineno, kind, name, ev in findings:
            find_rows.append([rule, level, e['rel'], info['ref_fqn'], lineno,
                              kind, name, ev])
            cats[rule] += 1
            lv[level] += 1
        if args.verbose and findings:
            print(f'\n[{verdict}] {e["rel"]}')
            for rule, level, lineno, kind, name, ev in findings:
                loc = f':{lineno}' if lineno else ''
                print(f'  {level:6s} {rule:6s} {kind:7s} {name}{loc} — {ev}')
        if i % 400 == 0:
            print(f'  ... 已检查 {i}/{len(entries)}')

    # ── 摘要 ──────────────────────────────────────────────────────
    vv = collections.Counter(r[5] for r in file_rows)
    print()
    print('=== 逐文件结论 ===')
    for k in ('FAIL', 'REVIEW', 'PASS'):
        print(f'  {k:7s} {vv.get(k, 0)}')
    print(f'  {"总计":7s} {len(file_rows)}')
    print()
    print('=== 规则命中计数 ===')
    for k in sorted(cats):
        print(f'  {k:8s} {cats[k]}')
    print()
    print('=== 级别计数 ===')
    for k in sorted(lv):
        print(f'  {k:8s} {lv[k]}')

    fails = [r for r in file_rows if r[5] == 'FAIL']
    if fails:
        print(f'\n=== FAIL 文件: {len(fails)} 个 ===')
        for r in fails[:60]:
            f_rules = sorted({f[0] for f in find_rows if f[2] == r[0]})
            print(f'  {r[0]}  [{",".join(f_rules)}]')
        if len(fails) > 60:
            print(f'  ... 共 {len(fails)} 个')

    if args.write:
        out = Path(args.out_dir)
        out.mkdir(parents=True, exist_ok=True)
        with open(out / 'jar-gate-files.csv', 'w', encoding='utf-8', newline='') as f:
            w = csv.writer(f)
            w.writerow(['file', 'class03', 'pkg03', 'target_obf', 'ref_fqn',
                        'verdict', 'findings', 'ref_members', 'src_decls', 'byte_state'])
            w.writerows(sorted(file_rows))
        with open(out / 'jar-gate-findings.csv', 'w', encoding='utf-8', newline='') as f:
            w = csv.writer(f)
            w.writerow(['rule', 'level', 'file', 'ref_fqn', 'line', 'kind',
                        'name', 'evidence'])
            w.writerows(sorted(find_rows))
        with open(out / 'jar-gate-summary.txt', 'w', encoding='utf-8') as f:
            f.write(f'ref_jar={ref_jar}\nrev_jar={rev_jar if use_rev else "(未参与)"}\n')
            f.write(f'files={len(file_rows)}\n')
            for k in ('FAIL', 'REVIEW', 'PASS'):
                f.write(f'{k}={vv.get(k, 0)}\n')
            for k in sorted(cats):
                f.write(f'rule_{k}={cats[k]}\n')
            for k in sorted(lv):
                f.write(f'level_{k}={lv[k]}\n')
        print(f'\n[write] 台账已落盘: {out}')
    else:
        print('\n[只读模式] 未写入任何文件 (落盘需 --out-dir DIR --write)')

    if args.strict and vv.get('FAIL'):
        return 2
    return 0


if __name__ == '__main__':
    sys.exit(main())
