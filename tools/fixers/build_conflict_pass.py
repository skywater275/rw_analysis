#!/usr/bin/env python3
"""build_conflict_pass.py — 「类与包同名」冲突的**影子类路径**分遍编译。

问题（实测 2026-09-26）
----------------------
原版 jar 里有 46 个路径**既是类又是包**（如 `com/corrodinggames/rts/gameFramework/l`
既是 GlobalState 类、又是包目录）。`-source 8` 的 javac 在下列两种情形必报错，
且**与 source level 无关**（javac 13 在 -source 8/11/13 下一致，最小复现已验）：

  A. 编译**位于该包内**的源码 → `package a.b clashes with class of same name`（543 文件）
  B. 引用**该包内的类**（`import a.b.C;`）→ javac 把 `a.b` 当类 → `cannot find symbol`
     （实测 388 文件；这是 A 之外的独立族）

于是 `build/reverse-src` 里这两族（A 类候选 504 + 325）全部编不过 —— 是替换率最大的一块余量。

做法：**只改类，不改包**
------------------------
① 冲突路径集合 C = jar 类集合 ∩ jar 包目录集合（46 个）；
② 给每个 c ∈ C 分配**等长**临时类名 T(c)：**改父段的段首字符**（末段 = 简单名保持不变，
   于是源码里的简单名用法 `l` / `l.field` / `new l()` 完全不用改；长度不变 → 常量池
   可以纯字节等长回写，不必重建 CP）。T 之间不得互相嵌套、不得与 jar 任何类/包重名；
③ 造**影子 jar**：把 GAME_LIB 里对 c 类（含 `c$N` 内嵌类）的**全部引用**改写成 T(c)
   （字节级，`c` 后不得跟 `[\w/]`：`com/…/l/X`（包成员）不动，`com/…/l$1`（内嵌类）要动）；
   **包成员一律不动** —— 于是 javac 眼里：
     · 包 `a.b` 照旧存在（包成员引用 `a.b.C` 正常解析，族 B 直接消失）；
     · 类 `a.b` 变成 T(c)（族 A 的 clash 消失）；
④ 源码引用改写：只在「引用的是**类** c 而不是包成员」的位置把 `c` → `T(c)`
   （判据：`c.` 后面跟的是不是包内直接类名/直接子包名；是则为包引用，留）；
   本文件自身的 `package c;` 声明除外；
⑤ 收敛式编译（有错就丢报错文件重编）；`-XDshould-stop.ifError=ATTR` 保证一轮看到
   **全部**错误（否则解析期一旦有错 javac 就不再进入属性分析，会看到失真的「只有 17 个错」）；
⑥ 产出 .class 做等长字节回写 T(c) → c 后注入 `build/reverse-classes/`。

用法:
  python tools/fixers/build_conflict_pass.py --world all            # dry-run, 编译全部可编源码
  python tools/fixers/build_conflict_pass.py --world all --apply    # 注入 reverse-classes 并重打包
  python tools/fixers/build_conflict_pass.py --dump-errors build/_conflict-errors.txt
"""
import argparse
import os
import re
import shutil
import struct
import subprocess
import sys
import zipfile
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT / 'tools'))
from rwlib.config import GAME_LIB, find_javac  # noqa: E402

BUILD = ROOT / 'build'
REV_SRC = BUILD / 'reverse-src'
REV_CLS = BUILD / 'reverse-classes'
OUT_JAR = Path(os.environ.get('RW_REVERSE_OUT_JAR', str(BUILD / 'game-lib-reverse.jar')))
STUBS = ROOT / 'tools' / 'gates' / 'stubs'
PATCHED = ROOT / 'cache' / 'patched-classes'
LIBS = ROOT / 'RustedWarfare' / 'libs'
WORK = BUILD / '_conflict_world'
# ★ RS-15（第 11 轮）：包根**别名**。`a` 被同名字段/变量遮蔽时 Java 无语法可引用该包，
#   于是在 classpath 上再提供一份「同一批类、换一个不冲突的根」的副本，
#   受影响文件改用 `zom.a.…` 限定名即可解析（保留限定形态 ⇒ 不触发简单名撞名）。
#   置空（`RW_PKG_ALIAS=`）可关闭做 A/B 对照。
PKG_ALIAS_ROOT = os.environ.get('RW_PKG_ALIAS', 'zom')
SKIP_FILE = Path(os.environ.get('RW_BUILD_SKIP_FILE', str(ROOT / 'build-skip.txt')))
CAND = 'qzxwvyuQZXWVYUrRSTUV'   # 临时名候选字符
STOP_FLAG = '-XDshould-stop.ifError=ATTR'

# ── 额外搬入冲突世界的类（2026-09-28）──────────────────────────────────
# 有些类本身**不是**「类与包同名」冲突，但被搬走的冲突类需要与它们同包才能访问
# 包私有成员（实测：指令链路 c/d/e 搬到 qom...gameFramework 后，`:82 var2.a.add(...)`
# 访问 `ab` 的包私有字段报 `a is not public in ab`；`w.b(...)` 同理）。
# 默认**空** ⇒ 行为与改动前逐字节一致；需要时用逗号分隔的类路径开启：
#   RW_CONFLICT_EXTRA=com/corrodinggames/rts/gameFramework/ab,com/corrodinggames/rts/gameFramework/w
import os as _os
EXTRA_WORLD = {x.strip() for x in _os.environ.get('RW_CONFLICT_EXTRA', '').split(',')
               if x.strip()}


# ── ① 冲突路径与临时名 ────────────────────────────────────────────────
def jar_index(jar):
    with zipfile.ZipFile(jar) as z:
        names = [n for n in z.namelist() if n.endswith('.class')]
    classes = {n[:-6] for n in names}
    pkgs = {n.rsplit('/', 1)[0] for n in classes if '/' in n}
    return classes, pkgs


def pkg_member_names(classes, pkgs, c):
    """包 c 的「直接类名」与「直接子包名」：`c.X` 里 X 命中其一 ⇒ 是包引用。"""
    direct_cls, direct_pkg = set(), set()
    pre = c + '/'
    for n in classes:
        if n.startswith(pre):
            rest = n[len(pre):]
            if '/' in rest:
                direct_pkg.add(rest.split('/', 1)[0])
            else:
                direct_cls.add(rest)
    for n in pkgs:
        if n.startswith(pre):
            rest = n[len(pre):]
            direct_pkg.add(rest.split('/', 1)[0])
    return direct_cls, direct_pkg


def assign_temp(conflicts, classes, pkgs):
    """等长临时类名：改**父段**段首字符（保末段简单名），且互不嵌套。"""
    used = set(classes) | set(pkgs)
    temps = []
    mapping = {}
    for c in sorted(conflicts, key=len):
        parts = c.split('/')
        temp = None
        for pos in range(len(parts) - 1):
            for ch in CAND:
                if ch == parts[pos][:1]:
                    continue
                trial = list(parts)
                trial[pos] = ch + trial[pos][1:]
                cand = '/'.join(trial)
                if len(cand) != len(c) or cand in used:
                    continue
                # 互不嵌套（否则临时世界里又会造出「类 T1 与包 T1/」的新冲突）
                if any(cand.startswith(t + '/') or t.startswith(cand + '/') for t in temps):
                    continue
                temp = cand
                break
            if temp:
                break
        if temp is None:
            raise SystemExit('无法为 %s 分配等长临时类名' % c)
        used.add(temp)
        temps.append(temp)
        mapping[c] = temp
    return mapping


def _mk_rename(c, t, is_bytes):
    """构造「冲突类 c → 临时名 t」的改名正则。

    前边界分两支：① 描述符形态 `L<path>`（替换时保留 `L`）；② 独立形态
    （常量池 Class 名 UTF8 / 条目名），要求前一字符不是标识符/斜杠/点。
    ⚠ 不可用白名单式 `(?<![^L])`：常量池 Class 名是独立 UTF8（前导非 L），会全部漏改。
    """
    if is_bytes:
        e = re.escape(c.encode())
        rx = re.compile(rb'(L)' + e + rb'(?![\w/])|(?<![A-Za-z0-9_$/])' + e + rb'(?![\w/])')
        return rx, t.encode(), b'L'
    e = re.escape(c)
    rx = re.compile(r'(L)' + e + r'(?![\w/])|(?<![A-Za-z0-9_$/])' + e + r'(?![\w/])')
    return rx, t, 'L'


def _rename_patterns(mapping):
    items = sorted(mapping.items(), key=lambda kv: -len(kv[0]))
    return ([_mk_rename(c, t, True) for c, t in items],
            [_mk_rename(c, t, False) for c, t in items])


# ── ★ 2026-10-01 第 35 轮（PENDING RS-21）：**按常量池正确重写**的类改名器 ──────────
# 为什么需要它：`_rename_patterns()` 走的是**原始字节替换**，只对**等长**改名成立
# （`CAND` 临时名字符表就是按同长度设计的）。`zom` 别名把 `a/a/b` 变成 `zom/a/a/b`（**长 4 字节**）
# ⇒ 常量池 UTF8 条目的 `u2` 长度字段与实际字节数不符 ⇒ **常量池整体错位** ✗
# 实测（第 34 轮）：`shadow.jar` 里 `zom/a/a/a.class` **tag=47 于偏移 169**、
# `zom/a/a/b.class` **tag=47 于 299**，`ClassFile` 解析均失败；与 javac 报的
# `bad class file … bad constant pool tag: 47` **逐字吻合** ⇒ 冲突世界 **416 个
# `cannot access` / `bad class file` 错误的根源**。
# 本实现**只改 UTF8 条目的内容并同步改写其 `u2` 长度**，其余字节（索引/字段表/方法表/属性表）
# **逐字节原样搬运** ⇒ 结构上不可能错位。本地验证：`a/**` 全部 **30/30** 通过 ✓
def _cp_spans(data):
    """扫常量池，返回 `(cp_count, spans, cp_end)`；`spans[i] = (start, end, is_utf8)`，空槽为 None。"""
    n = struct.unpack_from('>H', data, 8)[0]
    pos, spans = 10, [None] * n
    i = 1
    while i < n:
        tag = data[pos]
        start = pos
        pos += 1
        if tag == 1:
            ln = struct.unpack_from('>H', data, pos)[0]
            pos += 2 + ln
            spans[i] = (start, pos, True)
        elif tag in (7, 8, 16, 19, 20):
            pos += 2
            spans[i] = (start, pos, False)
        elif tag == 15:
            pos += 3
            spans[i] = (start, pos, False)
        elif tag in (3, 4, 9, 10, 11, 12, 17, 18):
            pos += 4
            spans[i] = (start, pos, False)
        elif tag in (5, 6):
            # ★ LONG/DOUBLE **占两个槽位**且带 8 字节载荷 —— 第 35 轮原型首版漏拷这 8 字节，
            #   导致产物凭空少 536 字节、解析失败；此处必须记录整段。
            pos += 8
            spans[i] = (start, pos, False)
            i += 1
        else:
            raise ValueError('未知常量池 tag %d 于偏移 %d' % (tag, pos - 1))
        i += 1
    return n, spans, pos


def _rename_class_bytes(data, mapping):
    """把 class 里**所有 UTF8 条目**按 `mapping`（旧名→新名，最长优先）改写并修正长度。"""
    n, spans, cp_end = _cp_spans(data)
    pairs = sorted(mapping.items(), key=lambda kv: -len(kv[0]))
    out = bytearray(data[:10])
    for i in range(1, n):
        sp = spans[i]
        if sp is None:
            continue
        start, end, is_utf8 = sp
        if not is_utf8:
            out += data[start:end]
            continue
        ln = struct.unpack_from('>H', data, start + 1)[0]
        txt = data[start + 3:start + 3 + ln].decode('utf-8', 'surrogateescape')
        for old, new in pairs:
            if old in txt:
                txt = txt.replace(old, new)
        nb = txt.encode('utf-8', 'surrogateescape')
        out += b'\x01' + struct.pack('>H', len(nb)) + nb
    out += data[cp_end:]
    return bytes(out)


def _force_public(data):
    """把类的 `ACC_PUBLIC` 置位（★ 修正：**access_flags 在常量池之后**，不是偏移 8）。

    旧写法 `struct.unpack_from('>H', data, 8)` 取到的是 **`constant_pool_count`**，
    回写 `cp_count|1` 会**直接损坏 class 文件** ⇒ 该路径由 `RW_SHADOW_PUBLIC=1` 门控（默认关闭），
    属**休眠 bug**；此处按正确偏移处理。
    """
    _, _, cp_end = _cp_spans(data)
    af = struct.unpack_from('>H', data, cp_end)[0]
    if af & 0x0001:
        return data
    return data[:cp_end] + struct.pack('>H', af | 0x0001) + data[cp_end + 2:]


def make_revcls_shadow(mapping, out_dir):
    """把 `reverse-classes` 也**影子化**（冲突类改临时名），供 classpath 使用。

    ★ 2026-09-29 根因修复：`REV_CLS` 若仍含「冲突类」（实测 5 个：`a/a/a`、
    `com/corrodinggames/rts/game/a`、`.../game/units/d`、`.../units/custom/a/a`、
    `.../gameFramework/n`），javac 会把它当**类**加载 → `import <冲突类>.<成员>;`
    被解析为「类 X 的成员」而非「包 X 下的类」 →
    `cannot find symbol: class m / location: interface d`。
    实测 `units/ar$N` 系列（1 错近失类的主体）正因此失败：
    `import com.corrodinggames.rts.game.units.d.m;` 中 `units.d` 被当接口。
    """
    pats, str_pats = _rename_patterns(mapping)
    if out_dir.exists():
        shutil.rmtree(out_dir)
    out_dir.mkdir(parents=True)
    renamed = 0
    for p in REV_CLS.rglob('*.class'):
        rel = p.relative_to(REV_CLS).as_posix()
        new = rel
        for rx, t, lb in str_pats:
            new = rx.sub(lambda m, t=t, lb=lb: (lb if m.group(1) else '') + t, new)
        data = p.read_bytes()
        for rx, t, lb in pats:
            data = rx.sub(lambda m, t=t, lb=lb: (lb if m.group(1) else b'') + t, data)
        dst = out_dir / new
        dst.parent.mkdir(parents=True, exist_ok=True)
        dst.write_bytes(data)
        if new != rel:
            renamed += 1
    return renamed


# ── ② 影子 jar ────────────────────────────────────────────────────────
def build_keepconflict_jar(conflicts, mapping, out_jar):
    """把冲突类**以原名**（不改名）单独打包，供 `RW_KEEP_CONFLICT` 实验加入 classpath。

    只放**类条目本身**（如 `com/…/units/b.class`），**不放**其同名包成员
    （`com/…/units/b/X.class`）—— 否则同一 jar 内即构成「类与包同名」。
    """
    with zipfile.ZipFile(GAME_LIB) as zin, zipfile.ZipFile(out_jar, 'w', zipfile.ZIP_DEFLATED) as zout:
        want = set(conflicts)
        for item in zin.infolist():
            name = item.filename
            if not name.endswith('.class'):
                continue
            base = name[:-6]
            # 只收「冲突类本体」：base 恰为某个冲突类，且不是 `$N` 内层
            if '$' in base:
                continue
            if base in want:
                zout.writestr(name, zin.read(name))


def build_shadow_jar(conflicts, mapping, out_jar):
    key = '%s|%s|%s' % (GAME_LIB.stat().st_size, GAME_LIB.stat().st_mtime_ns,
                        ','.join('%s>%s' % kv for kv in sorted(mapping.items())))
    keyf = out_jar.with_name(out_jar.name + '.key')
    if out_jar.exists() and keyf.exists() and keyf.read_text(encoding='utf-8') == key:
        return
    # ★ 2026-09-29 根因修复（第二版）：原实现只有后边界 `(?![\w/])`，模式可在**任意段边界**匹配：
    #   · 条目名/常量池 `com/a/a/a/a` 里 index=6 的 `a/a/a` 后随 `.`（或串尾）→ 被匹配
    #     → 误改名 `com/a/q/a/a.class` → 真实类 `com.a.a.a.a` 在 shadow 中消失
    #     → javac `package com.a.a.a does not exist`（实测 105 条）。
    #   · 跨段误匹配：`java/a/a.class` 末段 `a` + `/a/a` 构成 `a/a/a` → `java` 被写成 `javq`
    #     （实测 shadow 内 `com/corrodinggames/rts/javq/a/a.class`）。
    # 修法：加**前边界**，分两支 ——
    #   ① 描述符形态 `L<path>`（替换时保留前缀 `L`）；
    #   ② 独立形态（常量池 Class 名 UTF8 / 条目名），要求前一字符不是标识符/斜杠/点。
    # ⚠ 不可用 `(?<![^L])`（要求前一字符是 `L` 或串首）：常量池里 Class 名是**独立 UTF8 串**
    #   （前导为二进制字节，非 `L`），那样会全部漏改 → 实测产出 0、收敛失败。
    pats, str_pats = _rename_patterns(mapping)
    # ★ 2026-09-29 第四十轮候选路线 (b)：**影子世界里把被改名的冲突类提升为 public**。
    # 动机：冲突类 `custom.d` 被改名为 `qom.…custom.d` 后，我们的源（在 `com…custom` 包）
    # 报 `d is not public in qom.…custom; cannot be accessed`（实测 `ag` 只剩这 1 错、
    # `custom/j` 剩 2 错，合计 207 KB 被 `custom.a`/`custom.d` 两点挡住）。
    # **运行时安全性论证**：真实运行时 `ag` 与 `custom.d` 同在 `com…units.custom` 包内，
    # 包私有访问本就合法；编译期失败纯粹是「临时包搬迁」的假象 ⇒
    # **只提升影子副本（classpath 侧）的可见性，不触及 stock jar，也不改变产物的字节码**。
    # 环境变量 `RW_SHADOW_PUBLIC=1` 开启（默认关闭，便于 A/B 对照）。
    import struct as _struct
    _shadow_public = os.environ.get('RW_SHADOW_PUBLIC') == '1'
    _conf_names = set(mapping.keys())
    with zipfile.ZipFile(GAME_LIB) as zin, zipfile.ZipFile(out_jar, 'w', zipfile.ZIP_DEFLATED) as zout:
        for item in zin.infolist():
            name = item.filename
            data = zin.read(name)
            if name.endswith('.class'):
                _orig = name[:-6]
                for rx, t, lb in pats:
                    data = rx.sub(lambda m, t=t, lb=lb: (lb if m.group(1) else b'') + t, data)
                # **条目名也要改**（否则 javac 按原路径索引、内部名却已改 → 找不到类）。
                # 包成员（`c/X.class`）因后随 `/` 被 lookahead 排除，保持原名 ✓
                for rx, t, lb in str_pats:
                    name = rx.sub(lambda m, t=t, lb=lb: (lb if m.group(1) else '') + t, name)
                if _shadow_public and _orig in _conf_names:
                    data = _force_public(data)
            zout.writestr(name, data)
        # ★ RS-15（第 11 轮）：为「包名被遮蔽」的文件额外提供一份**别名根**下的副本。
        #   动机：`a` 既是包根又被同名字段/变量遮蔽时，Java **没有语法**能引用该包；
        #   但如果 classpath 上同一批类**另有一个不冲突的根**（`zom`），
        #   受影响文件只要把限定引用写成 `zom.a.a.i` 就能解析 —— 且**保留限定形态**，
        #   因而不会重演第 10 轮「改成简单名 ⇒ reference to i is ambiguous」的失败。
        #   只增不改：原 `a/**` 条目保持原位，未受影响文件完全不受影响。
        if PKG_ALIAS_ROOT:
            with zipfile.ZipFile(GAME_LIB) as z2:
                a_classes = [n[:-6] for n in z2.namelist()
                             if n.endswith('.class') and (n[:-6] == 'a' or n[:-6].startswith('a/'))]
            # ★ 2026-10-01 第 31 轮修复（RS-15 残余 / **别名根自我遮蔽**）：
            #   `a/a/a` **既是类又是包**（stock 里 `a/a/a.class` 与 `a/a/a/h.class` **并存**）。
            #   把它一并复制进别名根 ⇒ 产生 `zom/a/a/a.class`，而 JLS 规定
            #   **类型优先于同名的包** ⇒ `zom.a.a.a` 被解析成「类 `a`」而不是包
            #   ⇒ 世界里 32 处 `import a.a.a.<成员>`（改写后为 `zom.a.a.a.<成员>`）全部落到
            #   「类 `zom.a.a.a` 的成员」上 ⇒ javac 报
            #   `cannot access a` + `bad class file: shadow.jar(/zom/a/a.class)`
            #   ⇒ **枢纽类 `a.a.h`（1,265 行）整份只有这 1 个错却永不产出** ⇒ 16 个文件连锁阻塞。
            #   实测判据（世界源码里对 `a.a.a` 的 **41** 处引用）：
            #     **32 处是包前缀**（`import a.a.a.h;` 等）、**9 处是 `package a.a.a;` 声明**、
            #     **没有任何一处把它当类用** ⇒ 把它**排除出别名副本**即可（包成员照常复制）✓
            _a_pkgs = set()
            for _c in a_classes:
                _p = _c.split('/')
                for _i in range(1, len(_p)):
                    _a_pkgs.add('/'.join(_p[:_i]))
            _skip_alias = sorted(c for c in a_classes if c in _a_pkgs)
            if _skip_alias:
                print('  别名根排除「既是类又是包」者 %d 个: %s' % (len(_skip_alias), _skip_alias))
            alias_map = {c: (PKG_ALIAS_ROOT + '/' + c[2:] if c != 'a' else PKG_ALIAS_ROOT)
                         for c in a_classes if c not in _a_pkgs}
            # ★ 2026-10-01 第 35 轮修复（PENDING **RS-21**）：**改用按常量池正确重写的改名器**。
            #   旧写法用 `_rename_patterns()` 做**原始字节替换**，而该函数是**为等长改名设计的**
            #   （`CAND` 临时名字符表就是同长度）—— 别名把 `a/a/b` 变成 **`zom/a/a/b`（长 4 字节）**，
            #   于是**常量池 UTF8 条目的 `u2` 长度字段与实际字节数不符** ⇒ 常量池整体错位 ✗✗
            #   实测复现：`shadow.jar` 里 `zom/a/a/a.class` **常量池 tag=47 于偏移 169**、
            #   `zom/a/a/b.class` **tag=47 于 299**，两者 `ClassFile` 解析均失败
            #   （`tag=47` 即 ASCII `/` ⇒ 解析器落在字符串中间），与 javac 报的
            #   `bad class file … bad constant pool tag: 47` **逐字吻合** ⇒ 冲突世界里
            #   **416 个 `cannot access` / `bad class file` 错误的来源**。
            #   新写法只改 **UTF8 条目的内容并同步改写其 `u2` 长度**，其余字节（索引/字段表/方法表/属性表）
            #   **逐字节原样搬运** ⇒ 结构上不可能错位。本地已验：`a/**` 全部 **30/30** 重写后
            #   `ClassFile` 解析成功且成员数一致 ✓
            n_alias = 0
            for item in zin.infolist():
                rel = item.filename[:-6] if item.filename.endswith('.class') else None
                if rel is None or rel not in alias_map:
                    continue
                data = _rename_class_bytes(zin.read(item.filename), alias_map)
                new = alias_map[rel]
                if _shadow_public:
                    data = _force_public(data)
                zout.writestr(new + '.class', data)
                n_alias += 1
            print('  别名根副本: %d 个 → %s/' % (n_alias, PKG_ALIAS_ROOT))
    keyf.write_text(key, encoding='utf-8')


# ── ③ 源码改写 ────────────────────────────────────────────────────────
def conflict_class_members(mapping, jar=GAME_LIB):
    """冲突类的**声明成员名**索引（含一级父类）：{类路径: {成员名}}。

    用于消解 `…gameFramework.f.o(x)` 的歧义：`o` 既可能是包 `f` 里的直接类，也可能是
    类 `f` 的方法；实测把它当包处理会让 505 个文件报
    `cannot find symbol: f @ package com.corrodinggames.rts.gameFramework`。
    """
    import struct
    import sys as _sys
    _sys.path.insert(0, str(Path(__file__).resolve().parent))
    from repair_member_names import ClassFile
    out = {}
    with zipfile.ZipFile(jar) as z:
        names = set(z.namelist())
        for c in mapping:
            try:
                cf = ClassFile(z.read(c + '.class'))
            except Exception:
                out[c] = {'field': set(), 'method': set()}
                continue
            ms = {'field': set(), 'method': set()}
            for kind in ('field', 'method'):
                for _off, ni, _di in cf.members[kind]:
                    ms[kind].add(cf.u(ni))
            try:
                si = struct.unpack_from('>H', cf.raw, cf.tail_start + 4)[0]
                sup = cf.u(cf.cp[si][1])
                if sup + '.class' in names:
                    pcf = ClassFile(z.read(sup + '.class'))
                    for kind in ('field', 'method'):
                        for _off, ni, _di in pcf.members[kind]:
                            ms[kind].add(pcf.u(ni))
            except Exception:
                pass
            out[c] = ms
    return out


def make_rewriters(mapping, classes, pkgs, cls_members=None):
    rw = {}
    cls_members = cls_members or {}
    for c, t in mapping.items():
        direct_cls, direct_pkg = pkg_member_names(classes, pkgs, c)
        # ★ 2026-09-29 第三十三轮根因修复：**补左边界**。
        # 原正则 `re.escape(cd) + r'(?:\.(名)|\$内嵌)?(?![\w$])'` **没有前边界** ⇒
        # 冲突类 `a/a/a` 的模式会匹配**无关路径的后缀**：
        #   `com…game.units.custom.a.a.a` 里的 `a.a.a` 被当成该类 → 改写成其临时名 `q.a.a`
        #   ⇒ 产出 `com…game.units.custom.q.a.a` ⇒ javac
        #   `package com.corrodinggames.rts.game.units.custom.q.a does not exist`
        #   （实测 `_diag52` 中该错误 **79 次**，波及 `y`(90.8KB) 等大体积目标）。
        # 修法与 `_mk_rename`（影子字节改名）一致：要求前一字符不是标识符/点/斜杠
        # —— 但**允许 `L` 前缀**（描述符形态 `La/a/a;`）。
        rw[c] = (c.replace('/', '.'), t.replace('/', '.'),
                 re.compile(r'(?:(?<=L)|(?<![A-Za-z0-9_$./]))' +
                            re.escape(c.replace('/', '.')) +
                            r'(?:\.([A-Za-z_$][\w$]*)|(\$[\w$]+))?(?![\w$])'),
                 direct_cls | direct_pkg,
                 cls_members.get(c) or {'field': set(), 'method': set()})
    return rw


def rewrite_src(txt, rw):
    """把「对冲突**类**的引用」改成临时类名；包成员引用与本文件 package 声明不动。"""
    for c, (cd, td, rx, members, cls_mem) in rw.items():
        if cd not in txt:
            continue
        # 保护本文件自身的 package 声明（`package c;` 指的是包，不是类）
        own = re.search(r'(?m)^(\s*package\s+)' + re.escape(cd) + r'(\s*;)', txt)
        if own:
            txt = txt[:own.start()] + '\x00PKG\x00' + txt[own.end():]
        out, pos = [], 0
        for m in rx.finditer(txt):
            name = m.group(1)
            if name is not None and name in members:
                # 默认按**包**引用处理。只有「紧跟左括号 + 是冲突类的方法成员」才按类解释：
                # `…gameFramework.f.o(str)`（o 是类 f 的方法，静态调用）必须改成临时类；
                # 而 `…gameFramework.c.a.c`（a 是包 c 的类）绝不能改（补丁 4 曾一律按成员
                # 解释 → `boolean cannot be dereferenced` 等 192 条错误）。
                nxt = txt[m.end():m.end() + 1]
                if not (nxt == '(' and name in cls_mem['method']):
                    continue
            out.append(txt[pos:m.start()])
            # 只替换**类名那一截**；匹配里若吃掉了 `.成员` 或 `$内嵌` 后缀必须原样保留
            # （曾漏掉这一步 → `...gameFramework.l.e(...)` 被改写成 `...l(...)`，
            #   成员访问丢失 → 大量 cannot find symbol）。
            out.append(td + txt[m.start() + len(cd):m.end()])
            pos = m.end()
        if out:
            out.append(txt[pos:])
            txt = ''.join(out)
        txt = txt.replace('\x00PKG\x00', 'package ' + cd + ';')
    # ── 同包内「冲突类简单名」的类型引用（2026-09-26 补）────────────────────────
    # 影子 jar 把冲突**类**改名后，同包文件里 `final l c;` 这样的简单名不再解析。
    # 只改类型位置（保守）：前置 ( , [ < new/修饰符/行首；后置不是标识符/`(`/`.`。
    m_pkg = re.search(r'(?m)^\s*package\s+([\w.]+)\s*;', txt)
    if m_pkg:
        file_pkg = m_pkg.group(1)
        for c, (cd, td, _rx, _members, _cm) in rw.items():
            parent, _, simple = cd.rpartition('.')
            if parent != file_pkg:
                continue
            # 临时名方案只换**首段包名**（等长要求）→ 简单名不变，必须写全限定
            # 临时名才能在类型位置解析（实测只换简单名等于没改）。
            tsimple = td
            pat = re.compile(
                r'(?P<pre>(?:^|[([,<]\s*|new\s+|'
                r'\b(?:public|private|protected|static|final|abstract|volatile|transient)\s+))'
                + re.escape(simple) + r'(?![\w$(.])', re.M)
            lines = txt.split('\n')
            for i, ln in enumerate(lines):
                st = ln.lstrip()
                if st.startswith(('//', '*', '/*')):
                    continue                      # 注释行不动（只在代码里改类型引用）
                lines[i] = pat.sub(lambda m: m.group('pre') + tsimple, ln)
            txt = '\n'.join(lines)
    return txt


RE_IMPORT = re.compile(r'(?m)^\s*import\s+(?!static\s)(?P<fqn>[\w.$]+)\s*;\s*$')
RE_TYPE_DECL = re.compile(r'\b(?:class|interface|enum|record)\s+([A-Za-z_$][\w$]*)')


def needs_pkg_alias(txt, pkg_roots):
    """该文件是否**被同名的包根遮蔽**（RS-15 判据，与 `audit_shadowed_package_refs.py` 同源）：
    存在一个「简单名恰为真实包根」的单类型导入**或**类级声明，且正文里该简单名作限定名首段出现。"""
    body = re.sub(r'(?m)^\s*(?:package|import)\s+[\w.]+\s*;', ' ', txt)
    names = set()
    for fq in re.findall(r'(?m)^\s*import\s+([\w.]+)\s*;', txt):
        s = fq.rsplit('.', 1)[-1]
        if '.' in fq and len(s) <= 3:
            names.add(s)
    for nm in re.findall(r'(?m)^\s{4}(?:public |private |protected |static |final |transient |volatile )*'
                         r'[\w.<>\[\]$]+\s+(\w+)\s*(?:=|;)', txt):
        if len(nm) <= 3:
            names.add(nm)
    for s in names:
        if s in pkg_roots and re.search(r'(?<![\w.$])' + re.escape(s) + r'\.\w+\.\w+', body):
            return True
    return False


def alias_shadowed_refs(txt, a_fqns, root):
    """把受遮蔽文件里的 `a.<类 FQN>` 限定引用改写到**别名根** `root` 下。

    只改**确切的已知类 FQN**（取最长匹配），因此不会误伤 `a.a.r.a` 这类字段链
    —— 那种写法在 Java 里本就不可能（`byte[]` 没有字段 `a`）。**保留限定形态**是关键：
    第 10 轮改成简单名的做法会与单字母成员撞名（`reference to i is ambiguous`）。
    """
    if not a_fqns:
        return txt, 0
    masked = re.sub(r'"(?:\\.|[^"\\])*"', lambda m: ' ' * len(m.group(0)), txt)
    hits, taken = [], [False] * len(masked)
    for f in a_fqns:
        for m in re.finditer(r'(?<![\w$.])' + re.escape(f.replace('/', '.')) + r'(?![\w$])', masked):
            if any(taken[m.start():m.end()]):
                continue
            for k in range(m.start(), m.end()):
                taken[k] = True
            hits.append((m.start(), m.end()))
    if not hits:
        return txt, 0
    hits.sort()
    out, pos = [], 0
    for s, e in hits:
        out.append(txt[pos:s])
        out.append(root + '.' + txt[s:e])
        pos = e
    out.append(txt[pos:])
    return ''.join(out), len(hits)


def drop_shadowing_imports(txt, pkg_roots):
    """RS-15 修复：删掉「**简单名恰为真实包根**」的单类型导入。

    背景（2026-10-01 第 9/10 轮一手根因）：混淆世界里包名与成员名同一命名空间（都是
    `a`/`b`/`e`…）。反向改名把混淆名还原后，同一文件里可能出现

        package a.a;
        import a.a.a.a;                              // 简单名 `a` 进入作用域
        private a.a.a.i E = new a.a.a.i("rudp-…");  // `a` 已绑定为**类型** ⇒ 按成员访问解释

    按 JLS 6.5.2，左端标识符在作用域内若是类型/变量，整串按成员访问解释 ⇒ **包名被遮蔽**，
    而 Java **没有任何语法**能绕过被遮蔽的包名。**但导入声明本身不受字段/变量遮蔽影响**
    （导入在编译单元作用域解析）⇒ 删掉这条导入即可解除遮蔽。

    实测：`build/_conflict_world/src/s0017/h.java`（= `a/a/h`）**300 条错误 → 13 条**。

    安全性：删 import 只会让「裸用该简单名」的地方报**可见的** cannot find symbol，
    不会产生静默错误；且本函数只在「该简单名确实是包根 **且** 正文里被当作限定名首段使用」
    时才动手（全树仅 14 处 / 21 个文件）—— 否则 `ar.x.y` 这类对类 `ar` 的合法静态访问会被误伤。
    """
    if not pkg_roots:
        return txt
    out = []
    for line in txt.splitlines(keepends=True):
        m = re.match(r'^(\s*import\s+)([\w.]+)(\s*;\s*)$', line)
        if m:
            fq = m.group(2)
            simple = fq.rsplit('.', 1)[-1]
            if fq.count('.') >= 1 and simple in pkg_roots:
                body = txt.replace(line, '', 1)
                # 该简单名是否在正文里作「限定名首段 + 至少还有 2 段」出现
                if re.search(r'(?<![\w.$])' + re.escape(simple) + r'\.\w+\.\w+', body):
                    continue          # ★ 命中 ⇒ 丢弃这一行（不 append）
        out.append(line)
    return ''.join(out)


def fix_selfname_imports(txt):
    """修「同名导入」语法病：`import p.C;` 而本编译单元**自己声明了名为 C 的类型**。

    Java 规则：单类型导入声明的简单名若与本单元声明的类型同名 ⇒
    `C is already defined in this compilation unit`（**语法错误**，javac 在此停止分析
    该文件，掩盖后续语义错误 —— 与已知的「语法病掩盖语义病」规律一致）。

    为什么反向源频繁触发：反向改名让不同包下的短名类（`a`/`f`/`l`/`j`…）拿到**相同简单名**，
    于是 `package ...game.b;` 的文件在 `import ...gameFramework.h.a;` 时与本类 `a` 撞名。

    修法（保守两步）：
      1. 收集本文件声明的类型名（含嵌套；**不剥 `$` 后段** —— `class a$2$2` 的简单名
         就是 `a$2$2`，剥掉会变成 `2` 这类非法标识符，本项目上一会话已为此付过代价）。
      2. 对每条 `import p.C;`（C 命中上述集合）：删掉该 import；若 `C` 在文件其余位置
         仍被使用（排除类型声明行与构造器名）⇒ 把那些用法改成全限定 `p.C`。
    ★ 只删 import，**绝不替换用法** —— 文本层面无法区分「引用导入类 C」与「引用本类 C」
      （两者同名）。实测第一版会替换用法，且因把检测副本 `probe` 赋回 `txt`，
      连 `public class a` 都被改写成 `public class com....h.a`
      ⇒ 11 个 dup-def 被换成 13 个 class-enum、错误行 30,169 → 77,525。

      删掉 import 后：裸 `C` 依 JLS 归**本类**；若原本指向导入类，会变成**可见的类型错误**
      （可被门禁发现），而不是静默错误 —— 这正是我们要的语义。
    """
    ltypes = set(m.group(1) for m in RE_TYPE_DECL.finditer(txt))
    if not ltypes:
        return txt
    for m in list(RE_IMPORT.finditer(txt)):
        fqn = m.group('fqn')
        simple = fqn.split('.')[-1]          # 简单名 = 最后一个 `.` 段（可含 `$`）
        if simple in ltypes:
            txt = txt.replace(m.group(0), '', 1)
    return txt


def references_conflict(txt, rw):
    for c, (cd, td, rx, members) in rw.items():
        if cd in txt:
            return True
    return False


def world_files(mapping, rw, mode, classes, pkgs, include_skip=False, exclude=None):
    skip = set()
    if SKIP_FILE.exists():
        skip = {l.strip() for l in SKIP_FILE.read_text(encoding='utf-8').splitlines() if l.strip()}
    files = {}
    n_self = 0
    n_excl = 0
    for p in REV_SRC.rglob('*.java'):
        rel = p.relative_to(REV_SRC).as_posix()
        cls = rel[:-5]
        # 冲突**类自身**的源码（`…/gameFramework/m.java` 对 `…/gameFramework/m/` 包）：
        # 与包内源码同批编译会立刻造出「类 m 与包 m 同名」→ 必须单独一遍，
        # 否则包内 99 个文件全被它拖死（实测）。这里先剔除。
        if exclude and (cls in exclude or cls.split('$')[0] in exclude):
            n_excl += 1
            continue
        if cls in pkgs and cls in classes:
            n_self += 1
            continue
        if mode == 'all':
            # 第三方（com.codedisaster.*）2026-09-26 起**纳入本遍**：反向源已改为
            # 「03 原样出源」（成员名未被混淆，无需反向），实测整族 0 错 / 121 class，
            # 全部是 A 类替换。
            if rel in skip and not include_skip:
                continue
            files[rel] = p
            continue
        if any(cls.startswith(c + '/') for c in mapping):
            files[rel] = p
            continue
        txt = p.read_text(encoding='utf-8', errors='ignore')
        if references_conflict(txt, rw):
            files[rel] = p
    if n_self:
        print('  剔除「冲突类自身」源码 %d 个（需单独一遍）' % n_self)
    if n_excl:
        print('  批次外让位（签名与原版不一致，调用方回落原版类）%d 个' % n_excl)
    return files


# ── 主流程 ────────────────────────────────────────────────────────────
def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--world', choices=['refs', 'all'], default='refs',
                    help='refs=只编受冲突影响的源码; all=全部可编源码（不含第三方）')
    ap.add_argument('--include-skip', action='store_true',
                    help='world=all 时**忽略 build-skip.txt**（重试历史失败清单）')
    ap.add_argument('--apply', action='store_true', help='产出注入 reverse-classes 并重打包')
    ap.add_argument('--dump-errors', help='每轮 javac 完整输出追加写入该文件')
    ap.add_argument('--max-rounds', type=int, default=12)
    ap.add_argument('--exclude', help='排除清单（每行一个类的斜杠 FQN）：这些类不注入，'
                                      '改由原版字节码提供 —— 用于「成员集与原版不一致、'
                                      '会让原版调用方 NoSuchMethodError」的风险类让位')
    ap.add_argument('--exclude-batch-file',
                    help='批次级让位清单文件（**只**把源移出编译批次，让调用方回落原版类；'
                         '与 --exclude 分离，实测基础清单合并进批次会大幅减少产出）')
    ap.add_argument('--exclude-batch', action='store_true',
                    help='让 --exclude 清单同时作用于编译批次（默认关）')
    args = ap.parse_args()

    classes, pkgs = jar_index(GAME_LIB)
    pkg_roots = {c.split('/')[0] for c in classes if '/' in c}
    _DROP_SHADOW_IMP = os.environ.get('RW_DROP_SHADOW_IMPORT', '1') != '0'
    print('包根 %d 个（RS-15 遮蔽导入清理：%s）'
          % (len(pkg_roots), '开' if _DROP_SHADOW_IMP else '关'))
    conflicts = sorted(classes & pkgs)
    mapping = assign_temp(sorted(set(conflicts) | EXTRA_WORLD), classes, pkgs)
    print('冲突路径 %d 个（既当类又当包）→ 等长临时类名已分配' % len(conflicts))
    for c in conflicts[:3]:
        print('   %s  →  %s' % (c, mapping[c]))

    if WORK.exists():
        shutil.rmtree(WORK)
    (WORK / 'src').mkdir(parents=True)
    shadow = WORK / 'shadow.jar'
    build_shadow_jar(conflicts, mapping, shadow)
    print('影子 jar: %s' % shadow)

    cls_members = conflict_class_members(mapping)
    print('冲突类声明成员索引: %d 个类（用于消解 类.X 与 包.X 的歧义）' % len(cls_members))
    rw = make_rewriters(mapping, classes, pkgs, cls_members)
    _ex = set()
    if args.exclude and Path(args.exclude).exists():
        _ex = {l.strip() for l in Path(args.exclude).read_text(encoding='utf-8').splitlines()
               if l.strip() and not l.startswith('#')}
    # 让位清单默认**只影响注入**（不把源移出编译批次）：实测把基础清单也作用于批次时，
    # 产出从 740 类（A 695）掉到 694 类（A 649）——见 PENDING #91。需要批次级让位时
    # 用 `--exclude-batch` 显式开启。
    _exb = None
    if getattr(args, 'exclude_batch_file', None) and Path(args.exclude_batch_file).exists():
        _exb = {l.strip() for l in Path(args.exclude_batch_file).read_text(
            encoding='utf-8').splitlines() if l.strip() and not l.startswith('#')}
    elif getattr(args, 'exclude_batch', False):
        _exb = _ex
    picked = world_files(mapping, rw, args.world, classes, pkgs, args.include_skip, _exb)
    print('参与本遍的源码: %d 个（world=%s%s）'
          % (len(picked), args.world, ' +skip' if args.include_skip else ''))
    srcs = []
    manifest = []
    n_alias_files = 0
    a_class_fqns = sorted((c for c in classes if c == 'a' or c.startswith('a/')),
                          key=len, reverse=True)
    for i, (rel, p) in enumerate(sorted(picked.items())):
        txt = p.read_text(encoding='utf-8', errors='ignore')
        new = rewrite_src(txt, rw)
        # ★ 2026-09-30：修「同名导入」语法病（`import p.C;` 而本单元自己声明了 C）。
        #   必须在 rewrite_src **之后**跑，看到的是最终文本（含 qom./zom. 临时包前缀）。
        new = fix_selfname_imports(new)
        # ★ RS-15（2026-10-01 第 10 轮）：删「简单名恰为真实包根」的单类型导入，解除包名遮蔽。
        if _DROP_SHADOW_IMP:
            new = drop_shadowing_imports(new, pkg_roots)
        # ★ RS-15（第 11 轮）：受遮蔽文件改用**别名根**限定名（保留限定形态，避免简单名撞名）。
        if PKG_ALIAS_ROOT and needs_pkg_alias(new, pkg_roots):
            new, _nref = alias_shadowed_refs(new, a_class_fqns, PKG_ALIAS_ROOT)
            if _nref:
                n_alias_files += 1
        # javac 要求 public 类与文件名一致 → 逐文件独立目录 + 原名
        d = WORK / 'src' / ('s%04d' % i)
        d.mkdir(parents=True, exist_ok=True)
        q = d / p.name
        q.write_text(new, encoding='utf-8', errors='ignore')
        srcs.append(q)
        manifest.append('s%04d\t%s\t%s' % (i, rel, 'A候选' if rel[:-5] in classes else 'B/新'))
    (WORK / 'manifest.tsv').write_text('\n'.join(manifest) + '\n', encoding='utf-8')
    if PKG_ALIAS_ROOT:
        print('  RS-15 别名改写: %d 个受遮蔽源文件（根 → %s）' % (n_alias_files, PKG_ALIAS_ROOT))

    # ★ 2026-09-29 根因修复：`reverse-classes` 也必须影子化（见 make_revcls_shadow 文档串）
    rev_shadow = WORK / 'revcls-shadow'
    _rn = make_revcls_shadow(mapping, rev_shadow)
    print('影子化 reverse-classes: %d 个条目改名 → %s' % (_rn, rev_shadow))
    # ★ 2026-09-29 第二十六轮实验（`RW_KEEP_CONFLICT=1` 开启）：
    #   把冲突类**以原名**再提供一份到 classpath。
    #   动机（R23/R25 实证）：`ar` 等类引用的 `b` 是**冲突类**（`…units/b` 既当类又当包），
    #   影子 jar 把它改名为 `qom/…/units/b` ⇒ 原名 `b` 消失 ⇒ `cannot find symbol`。
    #   若额外提供 `com/…/units/b.class`（**不带**包成员 `b/X.class`），
    #   则「只把 `b` 当类用」的文件可解析；只有**同时**把 `b` 当包用的文件才会
    #   触发 JLS「类与包同名」冲突（javac 惰性解析，理论上可共存）。
    keepc = WORK / 'keepconflict.jar'
    if os.environ.get('RW_KEEP_CONFLICT'):
        build_keepconflict_jar(conflicts, mapping, keepc)
        print('原名冲突类 jar: %s' % keepc)
    cp = ';'.join(([str(shadow), str(keepc)] if keepc.exists() and os.environ.get('RW_KEEP_CONFLICT')
                   else [str(shadow)]) +
                  [str(PATCHED), str(rev_shadow)] +
                  ([str(STUBS)] if STUBS.exists() else []) +
                  [str(x) for x in sorted(LIBS.glob('*.jar'))])
    print('类路径: 影子 jar%s + patched + revcls-shadow + stubs + libs'
          % (' + 原名冲突类 jar' if os.environ.get('RW_KEEP_CONFLICT') else ''))

    out_cls = WORK / 'cls'
    if out_cls.exists():
        shutil.rmtree(out_cls)
    out_cls.mkdir(parents=True)
    err_pat = re.compile(r'^(.+?\.java):\d+: error:', re.M)

    def run_javac(paths):
        argfile = WORK / 'files.txt'
        argfile.write_text('\n'.join(str(Path(q).relative_to(ROOT)).replace('\\', '/')
                                     for q in paths), encoding='utf-8')
        r = subprocess.run([find_javac(), '-encoding', 'UTF-8', '-J-Duser.language=en',
                            '-source', '8', '-target', '8', '-Xmaxerrs', '200000',
                            STOP_FLAG, '-cp', cp, '-d', str(out_cls),
                            '-proc:none', '-nowarn', '-Xlint:none', '@' + str(argfile)],
                           capture_output=True, timeout=7200, cwd=str(ROOT))
        txt = ((r.stdout or b'').decode('utf-8', 'replace')
               + (r.stderr or b'').decode('utf-8', 'replace'))
        bad = {str((ROOT / m.group(1)).resolve()).replace('\\', '/') for m in err_pat.finditer(txt)}
        return txt, bad

    # ── 收敛策略（2026-09-26 改造：单调累积 + 回炉重试）──────────────────
    # 旧策略「每轮把报错文件全部丢掉再重编，且清空 -d 目录」会**级联塌方**：
    # 实测 1,353 源最后只剩 648（丢 705）——因为丢掉的 B/幽灵类文件不再提供符号，
    # 依赖它的文件下一轮集体 cannot find symbol。
    # 新策略：① -d 目录**不清空**（累积产出）且始终挂在 classpath 上；
    #         ② 每轮把报错文件摘出来，先编剩下的（产出累积），再把摘出来的**回炉重试**
    #            ——它们的依赖此时已作为 class 存在，很多文件能在后续轮次编过；
    #         ③ 直到无进展（回炉集合不再变小）为止。
    remaining = [str(q) for q in srcs]
    cp = cp + ';' + str(out_cls)

    def norm(q):
        return str(Path(q).resolve()).replace('\\', '/')

    total_rounds = 0
    while remaining and total_rounds < args.max_rounds:
        total_rounds += 1
        batch = list(remaining)
        compiled_any = False
        for inner in range(1, 17):
            txt, bad = run_javac(batch)
            n_err = len(re.findall(r':\d+: error:', txt))
            print('  轮 %2d.%d: 源 %4d / 报错文件 %4d (错误行 %5d) / 已产出 %d class'
                  % (total_rounds, inner, len(batch), len(bad), n_err,
                     len(list(out_cls.rglob('*.class')))))
            if args.dump_errors:
                with open(args.dump_errors, 'a', encoding='utf-8') as fh:
                    fh.write('\n===== 轮 %d.%d（源 %d / 报错文件 %d）=====\n'
                             % (total_rounds, inner, len(batch), len(bad)))
                    fh.write(txt)
            if not bad:
                compiled_any = True
                break
            nb = [q for q in batch if norm(q) not in bad]
            if not nb or len(nb) == len(batch):
                break
            batch = nb
        # ★ 2026-09-29 根因修复（收敛判据 BUG）：
        #   旧写法 `done = {...} if compiled_any else set()`，而 `compiled_any` 只在
        #   `if not bad: compiled_any = True` 时置位 —— 只要**还剩哪怕 1 个**编不过的文件
        #   （inner 循环 16 次后 bad 通常非空），compiled_any 恒为 False ⇒ done 为空
        #   ⇒ failed == remaining ⇒ 误判「无进展」直接停止 ⇒ **本轮摘出的 800+ 文件
        #   从未回炉重试**（实测：轮 1.5 摘出 663 个 / 6059 错误行后被整体丢弃）。
        #   正确判据：本轮 batch 比 remaining 缩小了，就说明有进展，成功的那些可入 done。
        done = {norm(q) for q in batch} if len(batch) < len(remaining) else set()
        failed = [q for q in remaining if norm(q) not in done]
        if not failed:
            print('      ✅ 全数编过')
            break
        if len(failed) >= len(remaining):
            print('      ⛔ 本轮无进展，停止（余 %d 文件编不过）' % len(failed))
            break
        remaining = failed
    made = sorted(out_cls.rglob('*.class'))
    if not made:
        print('产出 0 个 class（收敛失败）')
        return 1

    back = [(re.compile(re.escape(t.encode()) + rb'(?![\w/])'), c.encode())
            for c, t in sorted(mapping.items(), key=lambda kv: -len(kv[1]))]
    # ★ RS-15（第 11 轮）：**别名根的逆变换** —— 受遮蔽文件编译时用的是 `zom.a.…`，
    #   产物里必须还原成原名 `a.…`，否则运行时找不到类（NoClassDefFoundError）。
    if PKG_ALIAS_ROOT:
        back += [(re.compile(re.escape((PKG_ALIAS_ROOT + '/' + c[2:]).encode()) + rb'(?![\w/])'),
                  c.encode()) for c in a_class_fqns if c != 'a']
    items = []
    for cls in made:
        rel = cls.relative_to(out_cls).as_posix()
        data = cls.read_bytes()
        for rx, c in back:
            data = rx.sub(lambda m, c=c: c, data)
        items.append((rel, data))
    if args.exclude and Path(args.exclude).exists():
        ex = {l.strip() for l in Path(args.exclude).read_text(encoding='utf-8').splitlines()
              if l.strip() and not l.startswith('#')}
        before = len(items)
        items = [(n, d) for (n, d) in items if n[:-6] not in ex]
        print('排除清单: %d 条 → 让位 %d 个类（由原版字节码提供）' % (len(ex), before - len(items)))
    a_hit = [n for n, _ in items if n[:-6] in classes]
    print('产出类 %d 个 = A 类替换 %d + B 新增 %d' % (len(items), len(a_hit), len(items) - len(a_hit)))

    if not args.apply:
        print('[dry-run] 未落盘（临时世界留在 %s）' % WORK)
        return 0

    for rel, data in items:
        # rel 已含 `.class` 后缀（来自 out_cls 里的文件路径）—— 早期版本在这里又拼了一次
        # `.class`，把注入条目写成 `X.class.class`（产物 jar 里多出 793 个错名条目），务必别再加。
        dst = REV_CLS / rel
        dst.parent.mkdir(parents=True, exist_ok=True)
        dst.write_bytes(data)
    print('已注入 %d 个 class → %s' % (len(items), REV_CLS))
    if OUT_JAR.exists():
        want = {rel for rel, _ in items}
        tmp = WORK / 'new.jar'
        with zipfile.ZipFile(OUT_JAR) as zin, zipfile.ZipFile(tmp, 'w', zipfile.ZIP_DEFLATED) as zout:
            for n in zin.namelist():
                if n in want:
                    continue
                zout.writestr(n, zin.read(n))
            for rel, data in items:
                zout.writestr(rel, data)
        shutil.move(str(tmp), str(OUT_JAR))
        print('已重打包 %s' % OUT_JAR)
    return 0


if __name__ == '__main__':
    sys.exit(main())
