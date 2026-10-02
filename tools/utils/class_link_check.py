#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""产物 jar 链接期自洽性校验 —— 常量池成员引用是否都能解析（NoSuchField/MethodError 预检）。

为什么需要（2026-09-24 结构指纹通道发现）：
  逆向构建会把「可读名 → 混淆名」重命名后再编译。若某个字段/方法的映射**缺失或错认**，
  编译出的成员名就与原版不同，于是两类错误只会在**运行时**才炸：
    · 我们自己编译的类引用它 → 同一份源码内自洽，看不出来；
    · **原版遗留类**（未替换的 1,300+ 个类）按原版名字引用它 → 运行时 NoSuchFieldError /
      NoSuchMethodError（例：`appFramework/s.c` 在产物里变成了 `boolean1`）。
  启动日志只覆盖被走到的代码路径，这类缺陷可能长期潜伏。本工具不看日志，直接把
  「产物自己的常量池」与「产物自己的成员表」对齐核验，**不需要运行游戏**。

方法（纯 Python 解析 class 文件，不调 javap，快且精确）：
  ① 建索引：每个类的 this/super/interfaces + 声明的字段(name:desc) + 方法(name+desc)
  ② 收集引用：常量池里所有 Fieldref / Methodref / InterfaceMethodref
  ③ 逐条解析：沿 super / interfaces 链查找同名同描述符成员（含继承）
     · 找不到成员 → 链接错误候选
     · 所属类在产物里完全不存在（且不是 JDK/三方包）→ 类缺失候选

Usage:
    python tools/utils/class_link_check.py                       # 全量校验产物 jar
    python tools/utils/class_link_check.py --jar <jar>           # 换一个 jar
    python tools/utils/class_link_check.py --top 40 --out build/_link_check.md
    python tools/utils/class_link_check.py --only com/corrodinggames/rts/appFramework/s.class
"""
import argparse
import struct
import sys
import zipfile
from collections import defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import ROOT as PROJ  # noqa: E402

GAME_ROOT = PROJ.parent
DEFAULT_JAR = PROJ / "build" / "game-lib-reverse.jar"

# 产物里不指望存在的包前缀（JDK / Android / LibRocket 原生桥 / 三方）
EXTERNAL_PREFIXES = (
    "java/", "javax/", "jdk/", "sun/", "com/sun/", "org/lwjgl/", "org/newdawn/",
    "android/", "dalvik/", "com/LibRocket", "org/json/", "org/apache/", "net/",
    "com/google/", "com/steadypixel/", "org/",
    "com/jcraft/", "com/jorbis/", "com/codedisaster/steamworks/",
)


def component(desc_name):
    """Class 常量里的数组描述符 → 组件类名；原始类型数组 → None。"""
    n = desc_name
    while n.startswith("["):
        n = n[1:]
    if n.startswith("L") and n.endswith(";"):
        return n[1:-1]
    return None


class Reader:
    def __init__(self, data):
        self.d = data
        self.i = 0

    def u1(self):
        v = self.d[self.i]
        self.i += 1
        return v

    def u2(self):
        v = struct.unpack_from(">H", self.d, self.i)[0]
        self.i += 2
        return v

    def u4(self):
        v = struct.unpack_from(">I", self.d, self.i)[0]
        self.i += 4
        return v

    def skip(self, n):
        self.i += n


def parse_class(data):
    """→ dict(this, super, interfaces, fields, methods, refs)。refs: [(owner, name, desc, kind)]"""
    r = Reader(data)
    if r.u4() != 0xCAFEBABE:
        raise ValueError("不是 class 文件")
    r.u2(); r.u2()
    cp_count = r.u2()
    cp = [None] * cp_count
    i = 1
    while i < cp_count:
        tag = r.u1()
        if tag == 1:                                      # Utf8
            n = r.u2()
            cp[i] = ("utf8", data[r.i:r.i + n].decode("utf-8", "replace"))
            r.skip(n)
        elif tag in (3, 4):                               # Integer / Float
            cp[i] = ("num", r.u4())
        elif tag in (5, 6):                               # Long / Double（占两个槽）
            cp[i] = ("num2", r.u4(), r.u4())
            i += 1
        elif tag == 7:                                    # Class
            cp[i] = ("class", r.u2())
        elif tag == 8:                                    # String
            cp[i] = ("string", r.u2())
        elif tag in (9, 10, 11):                          # Fieldref / Methodref / InterfaceMethodref
            cp[i] = ("ref", tag, r.u2(), r.u2())
        elif tag == 12:                                   # NameAndType
            cp[i] = ("nat", r.u2(), r.u2())
        elif tag == 15:                                   # MethodHandle
            cp[i] = ("mh", r.u1(), r.u2())
        elif tag == 16:                                   # MethodType
            cp[i] = ("mt", r.u2())
        elif tag in (17, 18):                             # Dynamic / InvokeDynamic
            cp[i] = ("dyn", r.u2(), r.u2())
        elif tag in (19, 20):                             # Module / Package
            cp[i] = ("mod", r.u2())
        else:
            raise ValueError("未知常量池 tag %d @%d" % (tag, i))
        i += 1

    def utf(idx):
        e = cp[idx]
        return e[1] if e and e[0] == "utf8" else "?"

    def cls(idx):
        e = cp[idx]
        return utf(e[1]) if e and e[0] == "class" else "?"

    r.u2()                                                # access_flags
    this_cls = cls(r.u2())
    sup = cls(r.u2())
    ifaces = [cls(r.u2()) for _ in range(r.u2())]

    def members():
        out = []
        for _ in range(r.u2()):
            r.u2()                                        # access
            nm = utf(r.u2())
            desc = utf(r.u2())
            for _ in range(r.u2()):                       # attributes
                r.u2()
                r.skip(r.u4())
            out.append((nm, desc))
        return out

    fields = members()
    methods = members()
    source_file = ""
    for _ in range(r.u2()):                               # 类级 attributes
        aname = utf(r.u2())
        alen = r.u4()
        if aname == "SourceFile" and alen >= 2:
            source_file = utf(r.u2())
        else:
            r.skip(alen)

    refs = []
    for e in cp:
        if e and e[0] == "ref":
            _, tag, cidx, nidx = e
            nat = cp[nidx]
            if not nat or nat[0] != "nat":
                continue
            kind = {9: "field", 10: "method", 11: "method"}[tag]
            refs.append((cls(cidx), utf(nat[1]), utf(nat[2]), kind))
    return {"this": this_cls, "super": sup, "interfaces": ifaces,
            "fields": fields, "methods": methods, "refs": refs,
            "source_file": source_file}


def collect(jar_path, only=None):
    info = {}
    with zipfile.ZipFile(jar_path) as z:
        names = [n for n in z.namelist() if n.endswith(".class")]
        if only:
            names = [n for n in names if n == only]
        for n in names:
            try:
                info[n[:-6]] = parse_class(z.read(n))
            except Exception as exc:  # noqa: BLE001
                print("[警告] 解析失败 %s: %s" % (n, exc))
    return info


def resolves(info, owner, name, desc, _seen=None):
    """沿继承链查找成员。

    返回 (True, 命中类) / (None, "外部祖先") / (False, "真实缺失")。
    **关键**：链走到产物之外的类（JDK / Slick / Android 等）时必须判为「不可判定」而不是
    「缺失」—— 否则 java.lang.Enum.ordinal()、Slick GameContainer 的字段这类继承成员
    会被误报成 NoSuchMethodError（首版实测 198 条里绝大多数是这种假阳性）。
    """
    if _seen is None:
        _seen = set()
    if owner in _seen:
        return False, "继承环"
    _seen.add(owner)
    if owner not in info:
        return None, "外部祖先 %s" % owner
    c = info[owner]
    pool = c["methods"] if "(" in desc else c["fields"]
    if (name, desc) in pool:
        return True, owner
    external = None
    for nxt in [c["super"]] + c["interfaces"]:
        if not nxt or nxt == "java/lang/Object":
            continue
        ok, where = resolves(info, nxt, name, desc, _seen)
        if ok:
            return True, where
        if ok is None:
            external = where
    if external:
        # 2026-09-25 精修（实测假阴性）：链走到产物之外时**不能一律判不可判定**。
        # 外部祖先（JDK / Slick / Android）**不可能**声明形参或返回类型是游戏内类型的方法
        # —— 实测漏洞：我们的 `game/units/f/f` 实现了 `java/util/Iterator`，原版保留类
        # `game/units/f/c` 调 `f.a(Lcom/corrodinggames/rts/gameFramework/utility/u;)V`
        # 时被判「外部祖先 → 不可判定」而漏报，运行期直接
        # `NoSuchMethodError: game.units.f.f.a(gameFramework.utility.u)` 崩游戏。
        # 判据：描述符含 `Lcom/corrodinggames/rts/` → 只可能来自产物内的类 → 真实缺失。
        if "Lcom/corrodinggames/rts/" in desc:
            return False, "外部祖先不可能提供（描述符含游戏内类型）: %s" % external
        return None, external
    return False, "本链内未声明"



def cross_check(info, stock_jar, top=40, prod_jar=None):
    """跨向校验（2026-09-25 新增）：**原版保留类 → 我们替换类**的成员引用必须可解析。

    为什么必须独立一道门禁：产物内部自洽（本工具默认模式）只覆盖「我们编译的类」之间的
    引用；而**原版保留的类**（未参与反向编译、字节码原样）对**我们替换掉的类**的调用，
    其名字/描述符是 R8 产物口径 —— 一旦我们的重建类成员名或描述符与之不符，运行时才炸
    （实测：`NoSuchMethodError: game.units.f.f.a(gameFramework.utility.u)`，
     调用方 `game.units.f.c` 是原版保留类，我们的 `f` 把该方法名留成了语义名 `reset`）。

    返回 (cross_unresolved, cross_missing_owner, stats)：
      cross_unresolved: {原版引用方: [(owner, name, desc, kind, 原因, 线索)]}
    """
    with zipfile.ZipFile(stock_jar) as zs, zipfile.ZipFile(prod_jar or DEFAULT_JAR) as zp:
        prod_bytes = {n: zp.read(n) for n in zp.namelist() if n.endswith(".class")}
        stock_bytes = {n: zs.read(n) for n in zs.namelist() if n.endswith(".class")}
        retained = {n for n in stock_bytes if prod_bytes.get(n) == stock_bytes[n]}
        replaced = {n[:-6] for n in prod_bytes
                    if n in stock_bytes and prod_bytes[n] != stock_bytes[n]}
        unresolved = defaultdict(list)
        missing_owner = defaultdict(set)
        unreachable = defaultdict(list)   # 不可达：我方类已不再引用该原版内层类（枚举常量体等）
        n_refs = 0
        for n in sorted(retained):
            cls = n[:-6]
            try:
                c = parse_class(stock_bytes[n])
            except Exception:
                continue
            for owner, name, desc, kind in c["refs"]:
                n_refs += 1
                if owner not in info:
                    if not owner.startswith("[") and not owner.startswith(EXTERNAL_PREFIXES):
                        missing_owner[cls].add(owner)
                    continue
                ok, why = resolves(info, owner, name, desc)
                if ok is None or ok:
                    continue
                # 不可达分类（2026-09-25，实测 gameFramework/bs 族）：
                # 引用方是**宿主类的原版内层类**（`Owner$N`），而宿主类已被我们替换且
                # **不再引用该内层类**（真 enum 自建实例，原版常量体成为死代码）→ 该调用
                # 在运行期不会发生，属门禁不可达误报，不计入缺陷面。
                if cls.startswith(owner + "$") and not any(
                        r[0] == cls for r in info[owner]["refs"]):
                    unreachable[cls].append((owner, name, desc, kind, why))
                    continue
                # 线索：区分「名字不对」与「描述符不对」，便于定位映射缺陷
                pool = info[owner]["methods"] if "(" in desc else info[owner]["fields"]
                hint = ""
                if any(d == desc for _n, d in pool):
                    hint = "同描述符存在但名字不同（成员改名缺陷）"
                elif any(_n == name for _n, _d in pool):
                    hint = "同名存在但描述符不同（参数/返回类型缺陷）"
                unresolved[cls].append((owner, name, desc, kind, why, hint))
    return unresolved, missing_owner, {"refs": n_refs, "retained": len(retained),
                                       "replaced": len(replaced),
                                       "unreachable": sum(len(v) for v in unreachable.values()),
                                       "unreachable_detail": unreachable}


def main():
    ap = argparse.ArgumentParser(description="产物 jar 链接期自洽性校验")
    ap.add_argument("--jar", default=str(DEFAULT_JAR))
    ap.add_argument("--only", help="只校验一个类（jar 内相对路径）")
    ap.add_argument("--top", type=int, default=40, help="每类问题最多列多少条")
    ap.add_argument("--out", help="Markdown 报告落盘")
    ap.add_argument("--cross", metavar="STOCK_JAR",
                    help="跨向校验：原版保留类 → 我们替换类的成员引用（缺省跳过）")
    args = ap.parse_args()

    jar = Path(args.jar)
    info = collect(jar, args.only)
    print("解析类数: %d（jar: %s）" % (len(info), jar))

    unresolved = defaultdict(list)      # 引用方 → [(owner,name,desc,kind,原因)]
    missing_owner = defaultdict(set)    # 引用方 → {缺失类}
    external_skipped = 0
    total_refs = 0
    for cls, c in sorted(info.items()):
        for owner, name, desc, kind in c["refs"]:
            total_refs += 1
            if owner not in info:
                # 数组类型（`[Lcom/x/Y;`）出现在方法属主里只有一种情况：对数组调 clone()，
                # 实际解析到 java.lang.Object，属于 JDK 侧 → 不可判定，不算缺失。
                if owner.startswith("[") or owner.startswith(EXTERNAL_PREFIXES):
                    external_skipped += 1
                else:
                    missing_owner[cls].add(owner)
                continue
            ok, why = resolves(info, owner, name, desc)
            if ok is None:
                external_skipped += 1
            elif not ok:
                unresolved[cls].append((owner, name, desc, kind, why))

    # 跨向校验前置（若启用）：其结果同时用于把「不可达」条目从内部结果里剔除
    # （同一引用方在两个方向都会被看到，分类只做一次）。
    cross_unresolved = {}
    cross_stats = {}
    cross_missing = {}
    if args.cross:
        cross_unresolved, cross_missing, cross_stats = cross_check(info, args.cross, args.top, jar)
        und = cross_stats.get("unreachable_detail") or {}
        for ucls, items in und.items():
            keys = {(o, n2, d) for o, n2, d, _k, _w in items}
            keep = [x for x in unresolved.get(ucls, []) if (x[0], x[1], x[2]) not in keys]
            if keep:
                unresolved[ucls] = keep
            else:
                unresolved.pop(ucls, None)

    lines = []
    lines.append("# 产物 jar 链接期自洽性校验\n")
    lines.append("- jar: `%s`" % jar)
    lines.append("- 解析类数: %d，常量池成员引用总数: %d" % (len(info), total_refs))
    lines.append("- 判为「外部祖先/外部类」（JDK、Slick、Android 等，不在产物内，不可判定）: %d" % external_skipped)
    lines.append("- **无法解析的成员引用: %d 条，涉及 %d 个类**" % (sum(len(v) for v in unresolved.values()), len(unresolved)))
    lines.append("- **引用了产物中不存在的类: %d 条，涉及 %d 个类**\n"
                 % (sum(len(v) for v in missing_owner.values()), len(missing_owner)))

    if unresolved:
        lines.append("## 无法解析的成员引用（NoSuchFieldError / NoSuchMethodError 候选）\n")
        for cls, items in sorted(unresolved.items(), key=lambda x: -len(x[1])):
            lines.append("### `%s` — %d 条" % (cls, len(items)))
            for owner, name, desc, kind, why in items[:args.top]:
                inner = "本类" if owner == cls else ""
                lines.append("- `%s.%s %s`（%s%s，%s）" % (owner, name, desc, kind, "," + inner if inner else "", why))
            lines.append("")

    if missing_owner:
        lines.append("## 引用了产物中不存在的类\n")
        for cls, owners in sorted(missing_owner.items(), key=lambda x: -len(x[1])):
            lines.append("- `%s` → %s" % (cls, ", ".join("`%s`" % o for o in sorted(owners)[:args.top])))
        lines.append("")

    cross_unresolved = cross_unresolved or {}
    if args.cross:
        cu, cst = cross_unresolved, cross_stats
        lines.append("## 跨向校验：原版保留类 → 我们替换类（`--cross`）\n")
        lines.append("- 原版 jar: `%s`" % args.cross)
        lines.append("- 原版保留类 %d 个 / 我们替换类（A 类）%d 个，检查引用 %d 条"
                     % (cst["retained"], cst["replaced"], cst["refs"]))
        lines.append("- **跨向无法解析的成员引用: %d 条，涉及 %d 个原版类**\n"
                     % (sum(len(v) for v in cu.values()), len(cu)))
        if cst.get("unreachable"):
            lines.append("- 判为**不可达**（引用方是宿主类的原版内层类，而我方宿主类已不再引用它，"
                         "如真 enum 替换后原版常量体成为死代码）: %d 条\n" % cst["unreachable"])
            for cls, items in sorted(cst["unreachable_detail"].items()):
                lines.append("- `%s` → `%s`（%d 条，运行期不加载）"
                             % (cls, items[0][0], len(items)))
            lines.append("")
        if cu:
            for cls, items in sorted(cu.items(), key=lambda x: -len(x[1])):
                lines.append("### `%s` — %d 条" % (cls, len(items)))
                for owner, name, desc, kind, why, hint in items[:args.top]:
                    lines.append("- `%s.%s %s`（%s，%s）%s"
                                 % (owner, name, desc, kind, why,
                                    (" ← " + hint) if hint else ""))
                lines.append("")

    text = "\n".join(lines)
    print(text[:6000])
    if args.out:
        Path(args.out).write_text(text + "\n", encoding="utf-8")
        print("\n[落盘] %s" % args.out)
    if cross_unresolved:
        return 1
    return 0


if __name__ == "__main__":
    sys.exit(main())
