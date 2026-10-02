#!/usr/bin/env python3
"""B3 全量反向构建器: 03 源码 → 混淆名 → javac → jar 替代 game-lib.jar

管线:
  1. 类名反向 (fast_reverse_source: package/类声明/import/全限定/裸引用/构造器)
  2. 方法/字段名反向 (supplement rev: 语义名→混淆名, 仅无跨类冲突的 91%; 冲突 9% 保持语义名)
  3. javac 全量编译 (@filelist, classpath 同 javac_gate)
  4. 打包: 反向编译产物 + 原 jar 未反向类 (第三方/无映射) → build/game-lib-reverse.jar

用法: python tools/fixers/build_reverse_jar.py [--apply] [--skip-compile]
输出: build/reverse-src/ (反向源码) / build/reverse-classes/ (编译产物) / build/game-lib-reverse.jar

修复记录 (2026-09-21 H-1 工作项):
  D-4 (声明侧宿主约束): reverse_members 的**声明侧**成员反向只应用「supplement 宿主
    证据包含本文件宿主」的映射, 取代旧的「全局同名撞车抑制」跨类连带 —— 删除
    PlayerState 的重复声明不再把无关类 com/corrodinggames/rts/R$layout 的常量字段
    credits 改名为 o。访问侧映射与默认 CLI 语义不变。
  D-7 (路径感知类身份解析): 类声明名键控解析升级为「路径 + 包/类声明」双重判据,
    消除跨包同名类串包 (71 个 .../rendering/LicenseValidator$N.java → java/p$N.java)
    与反向目标同名竞争死件 (.../utility/UnitRegistry.java vs .../game/units/UnitRegistry.java)。
"""
import csv
import json
import os
import re
import shutil
import subprocess
import sys
import tempfile
import zipfile
from pathlib import Path

csv.field_size_limit(10 * 1024 * 1024)

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import (CLASS_DISCOVERIES, DEOBFUSCATED_DIR, GAME_LIB,
                          MAPPINGS_CSV, ROOT, SUPPLEMENT_CSV, find_javac, find_javap)


def load_mapping():
    """03 语义类名 → [(02 包路径, 02 混淆类名)] (支持多映射)。

    前置修复 (A-1, 2026-09-21): 原实现 `from tools.fixers.runtime_patch_batch import
    load_mapping, reverse_source` 中的模块在 eb158820「tools 与 rwlib 合并 + 脚本大精简
    (665→73)」中被删除且无替代, 导致本脚本导入即 ModuleNotFoundError, B3 重建完全不可用。
    这里按被删版本 (eb158820^ :tools/fixers/runtime_patch_batch.py:33-45) 等价内联;
    同 import 中的 reverse_source 在本脚本内无任何调用点 (已改用 fast_reverse_source),
    故不再保留该依赖。
    """
    mapping = {}
    for r in csv.reader(open(CLASS_DISCOVERIES, encoding='utf-8')):
        if not r or r[0] != 'class' or len(r) < 4:
            continue
        pkg, obf, readable = r[1], r[2], r[3]
        if not readable or readable.startswith('class,'):
            continue
        if obf == readable:
            # 恒等行（可读名 == 混淆名）：默认跳过（它们不提供「改名」信息）。
            # 但它们**提供混淆包信息** —— 03 树里有一批包被「可读化」了
            # （`java/audio/a`→`java/audio/backend`、`java/c`→`java/input`、
            # `custom/b`→`custom/animation` …），而这些类的**名字没变**，
            # 于是它们查不到映射 → 走「无类映射」分支 → 在可读包里新增一个幽灵类，
            # 原版类不被替换（PENDING #54/#60）。
            # `RW_IDENTITY_MAPPING=1` 时纳入恒等行，让「包名前缀段数」消歧把这类
            # 同名类放回**真混淆包**（名字不变 → 直接同名覆盖原版）。
            if os.environ.get('RW_IDENTITY_MAPPING') != '1':
                continue
        mapping.setdefault(readable, []).append((pkg, obf))
    return mapping

LIBS_DIR = ROOT / 'RustedWarfare' / 'libs'

# 引用补充映射（2026-09-26）：mappings/generated/b2-03-reverse.csv 是**按 03 文件路径**
# 建立的「可读名 → 混淆 FQN」对照（1,739 行 = 每个 03 文件一行），覆盖面比
# class-discoveries（简单名键，1,227 行）多 **447 个可读类**——实测据此补上的
# `com.corrodinggames.rts.gameFramework.network.OutputNetStream → ...gameFramework.j.as`
# 一行，就直接解开了 **87 个文件**的 cannot find symbol。
# 只作**引用解析**的补充（不动「文件自身的反向目标」判定，避免 attribution 风险）：
# 采信条件 = status 为 jar-ok 且 obf_fqn 确实存在于原版 jar，且该可读名在
# class-discoveries 里**没有**行（有行时以经过验证的 class-discoveries 为准）。
B2_SUPPLEMENT = {}

BUILD = ROOT / 'build'
REV_SRC = BUILD / 'reverse-src'
REV_CLS = BUILD / 'reverse-classes'
# 默认输出路径与默认行为保持不变; 仅提供环境变量钩子供变体工具
# (tools/fixers/build_reverse_jar_variant.py, D-6) 重定向输出与 skip 清单,
# 避免改写受版本管理的 build-skip.txt。
OUT_JAR = Path(os.environ.get('RW_REVERSE_OUT_JAR', str(BUILD / 'game-lib-reverse.jar')))

# B5: 反向撞名全限定检查用的 jar 类全集 (main() 初始化; 点分全名集合)
JAR_CLASS_SET = None

# 引用消歧索引 (2026-09-25): 可读全限定名 → (混淆包, 混淆类名)。
# main() 逐 03 文件建立 (含 $N 内层类; 目标含包迁移结果)。fast_reverse_source 在
# **引用点** (import 行 / 全限定文本 / 同包简单名) 用它做精确解析 —— 见 _ref_maps 文档串。
REF_INDEX = {}


def _ref_maps(mapping, pkg02):
    """简单名 → (混淆全限定名, 混淆简单名), 按**本文件目标混淆包**消歧。

    2026-09-25 缺陷修复（「提量战役」唯一残余项）。原实现:

        name_to_fqn = {c03: cands[0][0] + '.' + cands[0][1] for c03, cands in mapping.items()}

    一律取 `cands[0]` —— 即 class-discoveries.csv 的行序首条，完全无视引用发生的上下文。
    映射库中**跨包同名**很常见（`EffectConfig` 有 4 条: game.units.custom.p /
    gameFramework.m.p / game.units.custom.e.d / gameFramework.w），于是：

        03 gameFramework/rendering/EffectConfig$1.java
            final class EffectConfig$1 extends EffectConfig {}
        → reverse-src gameFramework/m/p$1.java
            final class p$1 extends com.corrodinggames.rts.game.units.custom.p {}   ← 错包

    正确目标应是同包的 gameFramework.m.p（该枚举 72 个常量体的父类）。错包后：
    ① 72 个内层类继承了**无关类**；② 原版外层枚举 <clinit> 调
    `p$N.<init>(Ljava/lang/String;I)V` 时 72 处链接失败（class_link_check 唯一残余）。

    消歧口径与 main() 的「包名前缀接近度」同源，但在引用点判定得更准：
      ① REF_INDEX 精确命中（可读全限定名，含同包裸引用）—— 唯一权威；
      ② 退：候选里混淆包 == 本文件目标混淆包 pkg02（Java 同包类优先语义的混淆域等价）；
      ③ 再退：保持原行为 cands[0]。
    返回值按 pkg02 缓存（构建期仅数十个不同目标包，避免每文件重建 1 万条字典）。
    """
    key = pkg02 or ''
    hit = _REF_MAP_CACHE.get(key)
    if hit is not None:
        return hit
    fqn_map = {}
    obf_map = {}
    for c03, cands in mapping.items():
        pick = cands[0]
        if pkg02 and len(cands) > 1:
            for cand in cands:
                if cand[0] == pkg02:
                    pick = cand
                    break
        fqn_map[c03] = pick[0] + '.' + pick[1]
        obf_map[c03] = pick[1]
    _REF_MAP_CACHE[key] = (fqn_map, obf_map)
    return fqn_map, obf_map


_REF_MAP_CACHE = {}


def load_dir_aliases():
    """可读「目录别名」映射：{可读全限定名: (真混淆包, 类名)}。

    判据（零猜测，全部由实测证据给出）：
      ① 03 树里存在**没有类声明**的空壳文件（如 …/gameFramework/ui/panels/c.java），
         其注释记录 `02 gameFramework/f/a/c.java` —— 即「该目录是某批类的别名副本」；
      ② 该空壳目录下的**其他同辈文件**（混淆名）若能在 jar 的**目标目录**里找到同名类，
         则该可读路径是完全无用的别名 → 登记为「指向真目录」的引用目标。
    实测收益：解开 `gameFramework/ui/panels/` 的 61 处引用 → 保住 36 个报错文件与
    随之级联失败的 489 个文件。
    """
    out = {}
    if not DEOBFUSCATED_DIR.exists():
        return out
    jset = JAR_CLASS_SET
    if not jset:
        import zipfile as _zip
        with _zip.ZipFile(GAME_LIB) as _z:
            jset = {n[:-6] for n in _z.namelist() if n.endswith('.class')}
    decl = re.compile(r'(?m)^\s*(?:public\s+|protected\s+|private\s+|static\s+|final\s+|'
                      r'abstract\s+|strictfp\s+|synthetic\s+)*(?:class|interface|enum|'
                      r'@interface)\s+\w+')
    stubs = []
    for f in DEOBFUSCATED_DIR.rglob('*.java'):
        txt = f.read_text(encoding='utf-8', errors='ignore')
        if decl.search(txt):
            continue
        m = re.search(r'02\s+([\w/$]+)\.java', txt)
        if m:
            # 注意：正则捕获组已经停在 `.java` **之前**，不能再切 [:-5]（早期多切一次 →
            # 目标目录被截短成 gameFramework/f → 别名全指向 gameFramework 包，全错）。
            stubs.append((f.relative_to(DEOBFUSCATED_DIR).as_posix()[:-5], m.group(1)))
    for stub_rel, tgt in stubs:
        stub_dir = stub_rel.rsplit('/', 1)[0]
        simple = stub_rel.rsplit('/', 1)[-1]
        tgt_dir = None
        for c in (tgt, 'com/corrodinggames/rts/' + tgt,
                  stub_dir + '/' + tgt.rsplit('/', 1)[-1]):
            d = c.rsplit('/', 1)[0]
            if (d + '/' + simple) in jset:
                tgt_dir = d
                break
        if not tgt_dir:
            continue
        dpath = DEOBFUSCATED_DIR / stub_dir
        if not dpath.exists():
            continue
        for sib in dpath.glob('*.java'):
            name = sib.stem
            if (tgt_dir + '/' + name) in jset:
                out[stub_dir.replace('/', '.') + '.' + name] = (tgt_dir.replace('/', '.'), name)
    return out


def build_ref_index(entries, pkg_learn=None):
    """建立 REF_INDEX: 可读全限定名 → (混淆包, 混淆类名)。

    键同时登记「03 路径推出的包」与「源内 package 声明」两种写法（03 树存在
    目录与声明包不一致的错位文件），值取该文件的最终目标 tgt（含 $N 后缀与包迁移）。
    无类映射 (tgt=None) 的文件不入索引 —— 它们保留自身身份，引用走原有回退链；
    **但**（2026-09-26 修复）若其可读包已由 `compute_pkg_moves` 学出真混淆包 Q，
    且 `(Q, 类名)` 确实存在于原版 jar，则把该可读 FQN 登记为「指向 Q 的引用目标」。
    这条修的是实测**运行时崩溃**：03 `gameFramework/rendering/CustomColorFilter.java`
    的构造器参数写成可读包里的 `rendering.w`（= jar 里的 `gameFramework.m.w`），
    引用不改写 → 我们编出的 `m/v.<init>(rendering.w)` 与原版调用方
    `m/v.<init>(m.w)` 签名不符 → 启动即
    `NoSuchMethodError: void gameFramework.m.v.<init>(gameFramework.m.w)`。
    """
    REF_INDEX.clear()
    n_id = 0
    n_b2 = 0
    n_learn = 0
    n_alias = 0
    for e in entries:
        tgt = e.get('tgt')
        cls03 = e['cls03']
        suffix2 = e.get('suffix2') or ''
        if e.get('identity'):
            # 恒等映射（2026-09-26）：自身 FQN 已在 jar → 引用必须解析回**自身**，
            # 否则会被 class-discoveries 的非恒等行（如 `LogicBoolean → custom.e.a`）
            # 带去错误目标 → `cannot find symbol: class a$Parameter`。
            pkgs = []
            if e.get('pkg03'):
                pkgs.append(e['pkg03'])
            m = re.search(r'(?m)^\s*package\s+([\w.]+)\s*;', e.get('src') or '')
            if m and m.group(1) not in pkgs:
                pkgs.append(m.group(1))
            for p in pkgs:
                REF_INDEX.setdefault(p + '.' + cls03, (p, cls03))
            n_id += 1
            continue
        if not tgt:
            # 无类映射：若可读包学出了真混淆包 Q 且 (Q, 类名) 在 jar → 登记为引用目标
            if pkg_learn:
                q = pkg_learn.get(e.get('pkg03') or '')
                if q and (q.replace('.', '/') + '/' + cls03) in JAR_CLASS_SET:
                    for p in {e.get('pkg03') or '', }:
                        if p:
                            REF_INDEX.setdefault(p + '.' + cls03, (q, cls03))
                        n_learn += 1
            continue
        pkgs = []
        if e.get('pkg03'):
            pkgs.append(e['pkg03'])
        m = re.search(r'(?m)^\s*package\s+([\w.]+)\s*;', e.get('src') or '')
        if m and m.group(1) not in pkgs:
            pkgs.append(m.group(1))
        for p in pkgs:
            REF_INDEX.setdefault(p + '.' + cls03, (tgt[0], tgt[1] + suffix2))
    # 引用补充映射（b2-03-reverse.csv）：只填**尚未命中**的可读全限定名
    for fqn, obf in B2_SUPPLEMENT.items():
        if fqn not in REF_INDEX:
            pkg2, _, clso = obf.rpartition('.')
            REF_INDEX[fqn] = (pkg2, clso)
            n_b2 += 1
    # 目录别名（2026-09-26 新）：03 树里有若干**可读目录**其实是「同一批类的别名副本」
    # （文件名用混淆名，而 jar 里该目录下**一个类都没有**）。实测 `gameFramework/ui/panels/`
    # 的 12 个类 (a,b,c,d,e,f,f$1,g,i,k,l,m) 在 jar 里全部位于 `gameFramework/f/a/`，
    # 03 里也有「Duplicate of 02 gameFramework/f/a/c.java」注释记录这种重复关系。
    # 不收编的后果（实测）：20 个源文件 / 61 处引用可读路径 → `cannot find symbol` →
    # 冲突遍第 1.4 轮 36 个文件报错被丢 → 第 1.5 轮**级联 489 个文件**失败（最大一处级联损失）。
    for fqn, val in load_dir_aliases().items():
        if fqn not in REF_INDEX:
            REF_INDEX[fqn] = val
            n_alias += 1
    if n_alias:
        print(f'引用消歧索引: 目录别名 {n_alias} 个（可读目录 → 真混淆目录）')
    if n_learn:
        print(f'引用消歧索引: 学出包的引用目标 {n_learn} 个（可读包 → 真混淆包）')
    if n_b2:
        print(f'引用消歧索引: b2 补充映射 {n_b2} 个（class-discoveries 缺行的可读类）')
    if n_id:
        print(f'引用消歧索引: 恒等映射 {n_id} 个（自身 FQN 已在 jar → 引用解析回自身）')
    return len(REF_INDEX)

# 与 jar 类名冲突的包前缀 (jar 有 a/a/a.class 类 + a/a/a/ 包) — 复用 runtime_patch 策略
# 第三方 steamworks 反向源白名单（2026-09-25 实测可编译；其余同族文件保持不编译）
# 第三方包：其成员名**未被 R8 混淆**（实测原版 `com/codedisaster/steamworks/
# SteamCallbackAdapter` 字段真名就是 `callback`）→ 成员名反向必须整体禁用，否则
# 访问侧按游戏映射改名、声明侧被宿主约束挡下 → 源码自相矛盾、无法编译。
NO_MEMBER_REVERSE_PKGS = ('com/codedisaster/',)

CODEDISASTER_KEEP = {
    'com/codedisaster/steamworks/SteamAPI.java',
    'com/codedisaster/steamworks/SteamAPICall.java',
    'com/codedisaster/steamworks/SteamAPIWarningMessageHook.java',
    'com/codedisaster/steamworks/SteamException.java',
    'com/codedisaster/steamworks/SteamNativeHandle.java',
}
BLACKLIST = {
    # 2026-09-24 结案：以下 7 条原写作**混淆路径**（`game/units/af.java` 等），而本集合的键口径是
    # **03 树相对路径（可读名）** → 03 树里不存在这些路径 → 从未命中过。**实测它们也是纯空转**：
    # 这 7 个目标类根本没进编译集（`game/units/n.java`、`game/units/custom/n.java` 在
    # `build-skip.txt`；`units/af`、`units/q`、`units/u`、`game/j`、`units/h` 被前置过滤挡掉，
    # `build/reverse-classes` 里都没有它们）→ 产物里本来就是原版字节码。故**直接删除**这 7 条，
    # 不再"修键启用回退"（无回退可启用）。见 docs/PENDING.md #53。
    # 2026-09-24（日志驱动普查发现）：ProjectileType 的常量在原版为 a="normal"/b="nuke"，
    # 而反向重命名把常量标识符改成 a/b → javac 生成的 name() 变成 "a"/"b"（Java 源码**无法**表达
    # 「字段名 a、name() 返回 normal」）→ ByteArrayPacketBuilder 写出的 `#Enum:m : nuke` 会变成
    # `#Enum:m : b`，破坏存档/回放/联机包文本。此类只能回落原版字节码（A 类让位）。
    'com/corrodinggames/rts/game/ProjectileType.java',   # 键=03 树相对路径（可读名）而非混淆路径
    # 2026-09-25（包迁移机制掀开掩蔽后实测）：音频后端家族有 3 个 03 重建文件**自身编译不过**。
    # 它们此前之所以"没报错"，是因为包名列写在可读包 + 输出路径与同名文件冲突 → 从未被真正编译
    # （`written` 去重直接跳过），缺陷被掩蔽。数据修好、路径不再冲突后缺陷暴露：
    #   AudioManager.java → audio/a/i.java 报 77 错（`variable n is already defined` /
    #     `int cannot be dereferenced` / 大量 cannot find symbol）
    #   AudioTrack.java   → audio/a/r.java 报 8 错
    #   e.java            → audio/a/e.java 报 2 错
    # 处置：回落原版字节码（ProjectileType 先例；这三类的行为 = 原版，且不再产生幽灵类）。
    # 源码级修复属 REV 独立批次（PENDING #61）。
    'com/corrodinggames/rts/java/audio/backend/AudioManager.java',
    'com/corrodinggames/rts/java/audio/backend/AudioTrack.java',
    'com/corrodinggames/rts/java/audio/backend/e.java',
    # ── 2026-09-25「提量战役」：JLS 分批编译新纳入的类里有 7 个**重建缺陷**（此前从未被编译过，
    #    缺陷因此被掩盖）。逐条由三道判据实测确证，处置＝回落原版字节码（行为＝原版）：
    #      · EffectConfig.java（rendering）：链接期缺 25 处成员 + 字面量缺 72 个
    #      · UnitList.java（game/units）：jar 门禁 W1 20 条 + 链接期缺 20 处成员
    #      · UnitList.java（pathfinding）：jar 门禁 E1DM+W1 22 条（与上者是同目标竞争件）
    #      · ShaderLayout.java（opengl）：链接期缺 5 处成员
    #      · TextureManagerInterface.java（rendering）：链接期缺 2 处成员
    #      · MessagePanel.java（ui）：链接期缺 1 处成员
    #      · TileEntry.java（game/map）：jar 门禁 W1 2 条 + 链接期缺 2 处成员
    # 2026-09-25（根系修复后重估）：本条拉黑是**误判**。当时把它单独拉黑，而它的 72 个
    #   常量体（EffectConfig$1..$72 → gameFramework/m/p$1..p$72）仍在编译 → 家族撕裂：
    #   原版外层枚举 <clinit> 调 `p$N.<init>(String,int)`，我们的 p$N 却是**另一个包的**
    #   无关类的子类（跨包同名错位）→ 72 处链接失败（class_link_check 唯一残余）。
    #   真正的缺陷在引用消歧（simple name 一律取 cands[0]），已由 _ref_maps + REF_INDEX 修复。
    #   枚举语义实测复核：原版 1698 类中**无一处**使用 m.p 的枚举 API
    #   (values/valueOf/ordinal/name/compareTo)，仅 72 个常量的 getstatic + 构造器；
    #   且 03 外层自身已提供 values()/valueOf()/$VALUES(au)。故外层可安全替换。
    #   家族必须全有或全无：内层是「枚举常量匿名体」，无法在源码层作为独立文件继承
    #   一个 private 构造器的枚举（同 #63 RingBufferIterator 的 JLS 硬约束）。
    #   残余差异（行为非等价）登记 docs/PENDING.md：非 enum 类 → toString() 不再是常量名。
    # 'com/corrodinggames/rts/gameFramework/rendering/EffectConfig.java',
    'com/corrodinggames/rts/game/units/UnitList.java',
    'com/corrodinggames/rts/gameFramework/pathfinding/UnitList.java',
    'com/corrodinggames/rts/gameFramework/opengl/ShaderLayout.java',
    'com/corrodinggames/rts/gameFramework/rendering/TextureManagerInterface.java',
    'com/corrodinggames/rts/gameFramework/ui/MessagePanel.java',
    'com/corrodinggames/rts/game/map/TileEntry.java',
}

METHOD_RE = re.compile(
    r'(?m)^\s*(?:public|protected|private)\s+(?:static\s+)?(?:final\s+)?(?:synchronized\s+)?'
    r'(?:strictfp\s+)?(?:native\s+)?(?:abstract\s+)?[\w<>\[\].,? ]+\s+(\w+)\s*\(')

# 字符串/字符字面量切分（保留分隔符）—— 用于「只改代码、不改字面量」的替换器
# 注：裸名替换器若进入字面量，会把线程名/显示文本/枚举提示语改成混淆名（实测缺陷，2026-09-24）
LITERAL_SPLIT = re.compile(r'("(?:\\.|[^"\\])*"|\'(?:\\.|[^\'\\])*\')')


JAVA_KEYWORDS = {
    'abstract', 'assert', 'boolean', 'break', 'byte', 'case', 'catch', 'char',
    'class', 'const', 'continue', 'default', 'do', 'double', 'else', 'enum',
    'extends', 'final', 'finally', 'float', 'for', 'goto', 'if', 'implements',
    'import', 'instanceof', 'int', 'interface', 'long', 'native', 'new',
    'package', 'private', 'protected', 'public', 'return', 'short', 'static',
    'strictfp', 'super', 'switch', 'synchronized', 'this', 'throw', 'throws',
    'transient', 'try', 'void', 'volatile', 'while', 'true', 'false', 'null',
}

# 原生绑定/官方接口方法豁免: jar 中官方名方法 (LibRocket 原生桥接等) 被 supplement
# 全局映射误伤后, 运行时 NoSuchMethodError (B4 实证: com.LibRocket.render 被反向成 b())。
# 这些方法名全局保持语义名 (对应 jar 官方名, 运行时一致)。
NATIVE_BIND_METHODS = {
    'render', 'mouseMove', 'processMouseMove', 'processMouseButtonDown',
    'processMouseButtonUp', 'processMouseWheel', 'RenderGeometry',
    'RenderGeometryPossiblyCompiled', 'RenderCompiledGeometry', 'GetTexture',
    'ReleaseTexture', 'LoadTexture', 'GenerateTexture', 'getFromTextureHolderFactory',
    'getTextureHolderFactory', 'EnableScissorRegion', 'SetScissorRegion',
    'HandleEvent', 'TranslateString', 'getFileLastModified', 'postUpdate',
    'newDocumentLoaded', 'newDocumentShown', 'findTextureHolder', 'getNewTextureHolder',
}

# B5: 官方音频接口方法 (javap 实证) — 接口实现类 (Wav$Music 等) 反向后方法名与
# 官方接口不一致 → "does not override abstract method"; 全局豁免 (混淆名均为短名, 不会撞)
OFFICIAL_IFACE_METHODS = {
    # Music
    'play', 'pause', 'stop', 'isPlaying', 'setLooping', 'isLooping', 'setVolume',
    'getVolume', 'setPan', 'setPosition', 'getPosition', 'dispose', 'setOnCompletionListener',
    # Sound
    'loop', 'resume', 'setPitch', 'getBytesUsed',
    # AudioRecorder / OnCompletionListener
    'read', 'onCompletion',
}

# B5: JDK 方法名豁免 (成员访问侧 — supplement 游戏方法映射误伤 JDK 方法调用,
#     如 this.a.matcher() 的 matcher 被反向成 a; 混淆名均为短名, 不冲突)
JDK_METHOD_NAMES = {
    # java.util.regex
    'matcher', 'matches', 'find', 'group', 'groupCount', 'start', 'end', 'lookingAt',
    'region', 'regionStart', 'regionEnd', 'hitEnd', 'requireEnd', 'appendReplacement',
    'appendTail', 'replaceAll', 'replaceFirst', 'quoteReplacement', 'split', 'pattern',
    'compile', 'quote', 'flags',
    # java.text
    'parse', 'format', 'applyPattern', 'toPattern', 'applyLocalizedPattern',
    'toLocalizedPattern', 'getNumberInstance', 'getDateFormatInstance',
    'getDateTimeInstance', 'getTimeInstance', 'getDateInstance', 'setLenient',
    'getCalendar', 'getTimeZone', 'setTimeZone', 'setCalendar',
    # java.lang.String / 常用
    'length', 'substring', 'charAt', 'indexOf', 'lastIndexOf', 'startsWith',
    'endsWith', 'contains', 'equals', 'equalsIgnoreCase', 'compareTo', 'compareToIgnoreCase',
    'concat', 'replace', 'trim', 'toLowerCase', 'toUpperCase', 'split', 'join', 'format',
    'valueOf', 'isEmpty', 'intern', 'getBytes', 'toCharArray', 'copyValueOf',
    # 数字/包装
    'parseInt', 'parseLong', 'parseFloat', 'parseDouble', 'parseBoolean', 'valueOf',
    'intValue', 'longValue', 'floatValue', 'doubleValue', 'byteValue', 'shortValue',
    'booleanValue', 'charValue', 'toString', 'hashCode', 'compare', 'signum', 'abs',
    'min', 'max', 'pow', 'sqrt', 'floor', 'ceil', 'round', 'random', 'toIntExact',
    'toRadians', 'toDegrees', 'sin', 'cos', 'tan', 'log', 'exp', 'copySign', 'floorDiv',
    # 集合/IO/其他
    'add', 'remove', 'get', 'set', 'size', 'clear', 'contains', 'iterator', 'hasNext',
    'next', 'read', 'write', 'flush', 'close', 'available', 'skip', 'reset', 'mark',
    'markSupported', 'getProperty', 'getenv', 'currentTimeMillis', 'nanoTime',
    'arraycopy', 'sort', 'binarySearch', 'fill', 'copyOf', 'copyOfRange', 'asList',
    'toArray', 'toString', 'clone', 'wait', 'notify', 'notifyAll', 'getClass',
    'getName', 'getSimpleName', 'getSuperclass', 'getMethod', 'getField', 'isInstance',
    'newInstance', 'getResourceAsStream', 'getResource', 'loadLibrary', 'load',
    'exit', 'gc', 'getRuntime', 'availableProcessors', 'freeMemory', 'totalMemory',
    'maxMemory', 'currentThread', 'sleep', 'yield', 'interrupt', 'isInterrupted',
    'join', 'run', 'start', 'getStackTrace', 'printStackTrace', 'fillInStackTrace',
    'initCause', 'getMessage', 'getLocalizedMessage', 'getCause', 'getSuppressed',
    'addSuppressed', 'setStackTrace',
}

# B5: JDK 常用类 (成员访问侧豁免 — supplement 全局映射误伤 Math.abs→a 等, 实测)
JDK_SAFE_CLASSES = {
    'Math', 'String', 'Integer', 'Long', 'Float', 'Double', 'Boolean', 'Short',
    'Byte', 'Character', 'Object', 'Class', 'System', 'Thread', 'Runtime', 'Arrays',
    'Collections', 'List', 'ArrayList', 'Map', 'HashMap', 'Set', 'HashSet', 'Iterator',
    'Iterable', 'StringBuilder', 'StringBuffer', 'Exception', 'RuntimeException',
    'IllegalArgumentException', 'IllegalStateException', 'IOException', 'InputStream',
    'OutputStream', 'File', 'Random', 'Enum', 'Comparable', 'Serializable', 'Cloneable',
    'Runnable', 'Stack', 'Vector', 'LinkedList', 'TreeMap', 'TreeSet', 'LinkedHashMap',
    'Properties', 'UUID', 'Date', 'Calendar', 'Timer', 'TimerTask', 'Process',
    'ProcessBuilder', 'Throwable', 'Error', 'Number', 'BigInteger', 'BigDecimal',
    'AtomicInteger', 'AtomicBoolean', 'AtomicLong', 'ConcurrentHashMap', 'Queue',
    'Deque', 'ArrayDeque', 'PriorityQueue', 'Pattern', 'Matcher', 'Charset',
    'ByteBuffer', 'CharBuffer', 'FileInputStream', 'FileOutputStream',
    'BufferedInputStream', 'BufferedOutputStream', 'ByteArrayInputStream',
    'ByteArrayOutputStream', 'DataInputStream', 'DataOutputStream',
    'ObjectInputStream', 'ObjectOutputStream', 'PrintStream', 'PrintWriter',
    'Reader', 'Writer', 'BufferedReader', 'BufferedWriter', 'InputStreamReader',
    'OutputStreamWriter', 'FileReader', 'FileWriter', 'RandomAccessFile',
    'ZipInputStream', 'ZipOutputStream', 'GZIPInputStream', 'GZIPOutputStream',
    'URL', 'URI', 'Socket', 'ServerSocket', 'DatagramSocket', 'InetAddress',
    'InetSocketAddress', 'StandardCharsets', 'Locale', 'TimeZone',
    'SimpleDateFormat', 'DecimalFormat', 'StringJoiner', 'Optional',
}


def load_learned_members():
    """读 `mappings/generated/learned-members.csv`（由 tools/fixers/learn_member_names.py
    用 **02 原始混淆源按声明顺序对齐** 学出，并已用原版字节码逐条校验）。

    返回 (host_m, host_f, glob_m, glob_f)：
      host_*  宿主精确映射（声明侧用；同一宿主同一语义名出现多个目标 → 丢弃该条）
      glob_*  全局兜底（**仅当该语义名在所有宿主上目标一致**时登记 —— 零猜测；
              用于访问侧，如 `h.profile` 这种接收者类型难以静态确定的写法）
    """
    path = MAPPINGS_CSV.parent / 'generated' / 'learned-members.csv'
    if not path.exists():
        return {}, {}, {}, {}
    host_m, host_f = {}, {}
    seen = {}                       # (kind, sem) -> set(obf)
    for r in csv.DictReader(open(path, encoding='utf-8')):
        sem = (r.get('semantic') or '').strip()
        obf = (r.get('obf') or '').strip()
        host = (r.get('host') or '').strip()
        kind = (r.get('kind') or 'method').strip()
        if not sem or not obf or not host or obf in JAVA_KEYWORDS:
            continue
        if sem in JAVA_KEYWORDS or sem[0].isupper() or obf == sem:
            continue
        pkg, _, cls = host.rpartition('.')
        target = host_m if kind == 'method' else host_f
        cur = target.setdefault((pkg, cls), {})
        if cur.get(sem) not in (None, obf):
            cur.pop(sem, None)      # 宿主内冲突 → 丢弃（不猜）
            seen.setdefault((kind, sem), set()).add('__conflict__')
            continue
        cur[sem] = obf
        seen.setdefault((kind, sem), set()).add(obf)
    glob_m = {s: next(iter(v)) for (k, s), v in seen.items()
              if k == 'method' and len(v) == 1 and '__conflict__' not in v}
    glob_f = {s: next(iter(v)) for (k, s), v in seen.items()
              if k == 'field' and len(v) == 1 and '__conflict__' not in v}
    return host_m, host_f, glob_m, glob_f


def load_member_map():
    """supplement → 全局成员映射: 语义名(纯名) → 混淆名(纯名), 仅无跨类冲突的。

    返回 (method_map, field_map): {sem_name: obf_name}
    过滤: 畸形语义名 (签名残渣) / 映射到 Java 关键字 / <init>。
    """
    method_sem = {}  # sem -> set(obf)
    field_sem = {}
    for r in csv.reader(open(SUPPLEMENT_CSV, encoding='utf-8')):
        if not r or len(r) < 5 or r[0] not in ('field', 'method'):
            continue
        # B5.6: 跳过验证标记 suspicious-bc-missing 的映射 (字节码无此成员,
        # 宿主/obf 疑似错误 — 访问侧误用会运行时崩溃; 跳过后反向保持语义名,
        # 编译错误会暴露而非静默错, 见 docs/PENDING.md 映射验证战役)
        if len(r) > 6 and r[6].strip() in ('suspicious-bc-missing',):
            continue
        typ, pkg, cls, obf, sem = r[0], r[1], r[2], r[3], r[4]
        # ★ 2026-09-29 第十轮根因修复：supplement 有一批行的**成员名落到了 class 列**
        # （`obfuscated_member` 为空，真实编码是 `obfuscated_package` + '.' + `obfuscated_class`
        #   = `包.类.成员`）。实测 **121 行**（非 order-align），例如：
        #   · `game.n` + `c`          → 包 game / 类 n / 成员 c      （isEnemy）
        #   · `…game.units` + `ar.e`  → 类 ar / 成员 e               （UnitRegistry 枚举常量 tank/…）
        #   · `units.am` + `bX`       → 类 am / 成员 bX              （player）
        # 不恢复则这些映射**全部装载失败** → 可读成员名（player/hp/…）留在产物里
        # → `cannot find symbol: variable player`（实测 3726 条 cannot-find-symbol 中最宽的一族：
        #   目标域 70 个文件 / 152 条）。
        # `mappings/` 是受保护边界（不改 CSV），只在此处容错读取。
        # 注意：`order-align-*` 行同样是空成员列，但那是**文档行**（成员名只写在 notes 的
        # 「(位置N)」里），按本规则会凭空造出成员 → 必须排除。
        _ver = r[6].strip() if len(r) > 6 else ''
        if not obf and cls and pkg and not _ver.startswith('order-align'):
            _seg = ('%s.%s' % (pkg.strip(), cls.strip())).split('.')
            if len(_seg) >= 3 and all(_seg):
                pkg, cls, obf = '.'.join(_seg[:-2]), _seg[-2], _seg[-1]
        if not sem or not obf:
            continue
        # 纯名剥离签名 + 过滤畸形 (语义名必须是合法 Java 标识符)
        obf_name = obf.split('(')[0].strip()
        sem_name = sem.split('(')[0].strip()
        if not obf_name or not sem_name or obf_name in ('<init>',):
            continue
        if not re.fullmatch(r'[A-Za-z_$][\w$]*', sem_name):
            continue  # 畸形语义名 (如 'n)'/'boolean)' 签名残渣)
        if obf_name in JAVA_KEYWORDS or sem_name in JAVA_KEYWORDS:
            continue  # 映射到关键字 (如 do) 无法作为标识符
        if obf_name == sem_name:
            continue  # 恒等 (官方未混淆成员) — 反向无变化, 且避免自撞剔除
        if sem_name[0].isupper():
            continue  # 大写开头 = 构造器/类型名残渣 (String/Rect/PointF) —
            # 构造器名由 fast_reverse_source 类声明替换处理, 无需成员映射
        target = method_sem if typ == 'method' else field_sem
        target.setdefault(sem_name, set()).add(obf_name)
    mmap = {k: next(iter(v)) for k, v in method_sem.items() if len(v) == 1}
    fmap = {k: next(iter(v)) for k, v in field_sem.items() if len(v) == 1}
    # 02 对齐学出的映射（全局无歧义部分）：补 supplement 缺行的可读名
    try:
        _hm, _hf, gm, gf = load_learned_members()
        if os.environ.get('RW_LEARN_GLOBAL') != '1':
            # 默认关闭全局套用（见本函数上方注释与 PENDING #90：全局套用会误伤访问侧）
            gm, gf = {}, {}
        n_lm = n_lf = 0
        for s, o in gm.items():
            if s not in mmap:
                mmap[s] = o
                n_lm += 1
        for s, o in gf.items():
            if s not in fmap:
                fmap[s] = o
                n_lf += 1
        if n_lm or n_lf:
            print(f'成员映射: 02 对齐学出（全局）方法 {n_lm} / 字段 {n_lf}')
    except Exception as e:                                     # 保守：出问题不阻断构建
        print(f'[warn] 学出映射接入失败: {e}')
    return mmap, fmap


def load_host_pkg_resolve():
    """读 `mappings/generated/host-resolve.csv` → {supplement 包列值: 斜杠形式真实包}。

    根因修复 (H-9): supplement 的 `obfuscated_package` 列口径不统一 —— 多数写全限定包,
    但 1,353 行写成**短写** (`f` / `m` / `game.units.custom` 等, 实为包路径的末段)。
    反向器按 `(混淆包, 混淆类)` 查宿主映射, 短写值永远命不中 → 这些记录静默失效
    (实测: P1 `rows→h` 的宿主 `(f, y)` 因此被全局套用到 `Root$TableData`)。
    本表由 `tools/fixers/gen_host_resolve.py` 用 jar 类名对号**确定性**生成。
    """
    path = MAPPINGS_CSV.parent / 'generated' / 'host-resolve.csv'
    out = {}
    if not path.exists():
        return out
    for r in csv.reader(open(path, encoding='utf-8')):
        if len(r) >= 2 and r[0] and r[1] and r[0] != 'package_in':
            out[r[0].strip()] = r[1].strip()
    return out


def _norm_host_pkg(pkg, cls, resolve, alias):
    """把 supplement 的 (包列, 类列) 规范化为反向器查找用的 (点分包, 类)。

    ① 包列短写 → 全限定包 (host-resolve 表);
    ② 类列写成**可读名** (`ResourceRate`) → 混淆名 (`aa`) (class-discoveries 权威)。
    两项都只做**键归一**, 不新增/不删除任何映射记录。
    """
    p = pkg.strip()
    if p in resolve:
        p = resolve[p].replace('/', '.')
    c = cls.strip()
    obf_c = alias.get(c.split('$')[0])
    if obf_c:
        c = obf_c + c[len(c.split('$')[0]):]
    return p, c


def load_member_map_hostscoped(our_classes):
    """补齐「全局歧义过滤」丢弃、但在我方类集中**唯一宿主**的成员映射。

    为什么要它
    ----------
    全局 `load_member_map()` 只保留「语义名 → **唯一**混淆名」——因为反向替换
    **不校验宿主类**，歧义名套用会跨类误改（P1 族，见 Mnemon `5c49e6eb`）。
    但若某语义名的**全部映射宿主中只有一个是我方替换类**，该名字在我方世界里就无歧义。

    实测：`player` 在 supplement 有 **3 个宿主**——
        field  d.a        e    player
        field  f.ab       a    player
        field  units.am   bX   player
    `d.a` / `f.ab` 都**不在**我方 reverse-src（1670 类）里，只有 `units.am` 在
    → `player → bX` 可安全全局套用（stock 确有 `public …game.n bX;` 字段；
       `units.am` 另有同名方法 `bX()` —— 字段/方法命名空间独立，合法）。
    不套用则 `player` 这个可读名残留在产物里 → `cannot find symbol: variable player`
    （实测 3726 条 cannot-find-symbol 中最宽的一族：目标域 70 文件 / 152 条）。

    返回 (method_map, field_map)：只含全局表里**尚未有**的名字。
    """
    ours = set(our_classes)

    def _hosts(pkg, cls):
        parts = ((pkg + '.' + cls).strip('.')).split('.')
        n = len(parts)
        return {c for c in ours if c.split('/')[-n:] == parts}

    agg = {}          # (typ, sem) -> {obf: set(host)}
    try:
        fh = open(SUPPLEMENT_CSV, encoding='utf-8')
    except OSError:
        return {}, {}
    with fh:
        for r in csv.reader(fh):
            if not r or len(r) < 5 or r[0] not in ('field', 'method'):
                continue
            typ, pkg, cls, obf, sem = r[0], r[1], r[2], r[3], r[4]
            _ver = r[6].strip() if len(r) > 6 else ''
            if _ver == 'suspicious-bc-missing':
                continue
            # 与 load_member_map 相同的「成员列错位」容错恢复
            if not obf and cls and pkg and not _ver.startswith('order-align'):
                _seg = ('%s.%s' % (pkg.strip(), cls.strip())).split('.')
                if len(_seg) >= 3 and all(_seg):
                    pkg, cls, obf = '.'.join(_seg[:-2]), _seg[-2], _seg[-1]
            obf_name = obf.split('(')[0].strip()
            sem_name = sem.split('(')[0].strip()
            if not obf_name or not sem_name or obf_name in ('<init>',):
                continue
            if not re.fullmatch(r'[A-Za-z_$][\w$]*', sem_name):
                continue
            if obf_name in JAVA_KEYWORDS or sem_name in JAVA_KEYWORDS:
                continue
            if obf_name == sem_name or sem_name[0].isupper():
                continue
            h = _hosts(pkg, cls)
            if not h:
                continue
            agg.setdefault((typ, sem_name), {}).setdefault(obf_name, set()).update(h)

    mmap, fmap = load_member_map()
    out_m, out_f = {}, {}
    for (typ, sem), obfs in agg.items():
        if sem in (mmap if typ == 'method' else fmap):
            continue
        hosts = {h for hs in obfs.values() for h in hs}
        if len(hosts) != 1:
            continue                      # 我方类集中仍有多个宿主 → 仍歧义，放弃
        cand = [o for o, hs in obfs.items() if next(iter(hosts)) in hs]
        if len(cand) != 1:
            continue                      # 同一宿主映射到多个混淆名 → 放弃
        (out_m if typ == 'method' else out_f)[sem] = cand[0]
    return out_m, out_f


def load_member_map_by_class(resolve=None, alias=None):
    """supplement → 宿主感知成员映射: {(混淆包, 混淆类): {语义名: 混淆名}}

    同名语义方法跨类映射冲突 (如 isEnabled→a/c/e/f/l/t... 10+ 类) 被全局唯一化
    过滤后全部放弃, 运行时 NoSuchMethodError 反馈 (B4 实证: ObjectPool.isEnabled)。
    宿主感知: 声明侧按宿主类精确反向 (冲突名按宿主解析), 访问侧仍全局兜底。
    返回 (per_m, per_f): 方法/字段命名空间分离 — 字段映射不得用于方法声明
    (B4 实证: callback 字段映射误伤方法声明, 原生回调 NoSuchMethodError)。

    H-9: 传入 resolve/alias 时, 宿主键先做**口径归一** (短写包名 + 可读类名),
    使 supplement 的宿主列与反向器查找键真正对齐。
    """
    if resolve:
        alias = alias or {}
    per_m, per_f = {}, {}
    for r in csv.reader(open(SUPPLEMENT_CSV, encoding='utf-8')):
        if not r or len(r) < 5 or r[0] not in ('field', 'method'):
            continue
        # B5.6: 同上 — 可疑映射 (字节码无此成员) 不参与宿主感知反向
        if len(r) > 6 and r[6].strip() in ('suspicious-bc-missing',):
            continue
        typ, pkg, cls, obf, sem = r[0], r[1], r[2], r[3], r[4]
        # ★ 2026-09-29 第十轮根因修复：supplement 有一批行的**成员名落到了 class 列**
        # （`obfuscated_member` 为空，真实编码是 `obfuscated_package` + '.' + `obfuscated_class`
        #   = `包.类.成员`）。实测 **121 行**（非 order-align），例如：
        #   · `game.n` + `c`          → 包 game / 类 n / 成员 c      （isEnemy）
        #   · `…game.units` + `ar.e`  → 类 ar / 成员 e               （UnitRegistry 枚举常量 tank/…）
        #   · `units.am` + `bX`       → 类 am / 成员 bX              （player）
        # 不恢复则这些映射**全部装载失败** → 可读成员名（player/hp/…）留在产物里
        # → `cannot find symbol: variable player`（实测 3726 条 cannot-find-symbol 中最宽的一族：
        #   目标域 70 个文件 / 152 条）。
        # `mappings/` 是受保护边界（不改 CSV），只在此处容错读取。
        # 注意：`order-align-*` 行同样是空成员列，但那是**文档行**（成员名只写在 notes 的
        # 「(位置N)」里），按本规则会凭空造出成员 → 必须排除。
        _ver = r[6].strip() if len(r) > 6 else ''
        if not obf and cls and pkg and not _ver.startswith('order-align'):
            _seg = ('%s.%s' % (pkg.strip(), cls.strip())).split('.')
            if len(_seg) >= 3 and all(_seg):
                pkg, cls, obf = '.'.join(_seg[:-2]), _seg[-2], _seg[-1]
        if not sem or not obf:
            continue
        obf_name = obf.split('(')[0].strip()
        sem_name = sem.split('(')[0].strip()
        if not obf_name or not sem_name or obf_name in ('<init>',):
            continue
        if not re.fullmatch(r'[A-Za-z_$][\w$]*', sem_name):
            continue
        if obf_name in JAVA_KEYWORDS or sem_name in JAVA_KEYWORDS:
            continue
        if obf_name == sem_name:
            continue
        if sem_name[0].isupper():
            continue
        host = _norm_host_pkg(pkg, cls, resolve or {}, alias or {}) if resolve else \
            (pkg.replace('/', '.'), cls)
        per = per_m if typ == 'method' else per_f
        per.setdefault(host, {}).setdefault(sem_name, set()).add(obf_name)
    clean = lambda d: {h: {s: next(iter(v)) for s, v in x.items() if len(v) == 1}
                       for h, x in d.items()}
    per_m, per_f = clean(per_m), clean(per_f)
    # 02 对齐学出的**宿主精确**映射（声明侧）：补 supplement 的语义名歧义/缺行
    try:
        hm, hf, _gm, _gf = load_learned_members()
        n = 0
        for src, dst in ((hm, per_m), (hf, per_f)):
            for host, rows in src.items():
                d = dst.setdefault(host, {})
                for s, o in rows.items():
                    if s not in d:
                        d[s] = o
                        n += 1
        if n:
            print(f'成员映射: 02 对齐学出（宿主精确）{n} 条')
    except Exception as e:
        print(f'[warn] 学出映射（宿主）接入失败: {e}')
    return per_m, per_f


def load_member_hosts(resolve=None, alias=None):
    """supplement → 每个语义名对应的「宿主类」集合 (D-4 声明侧宿主约束的判据来源)。

    返回 (hosts_m, hosts_f): {语义名: frozenset((混淆包, 混淆类))}。
    宿主 = supplement.csv 行内的 (obfuscated_package, obfuscated_class), 即该映射
    在字节码层真实归属的类。用于判定「某条成员映射是否属于本文件所代表的类」。

    D-4 背景: 旧实现只用全局 `fmap`/`mmap` 做**声明侧**反向 (成员名不区分宿主),
    于是 A-1 删除 PlayerState 的重复声明后, `credits→o` 会跨类作用到无关类
    `com/corrodinggames/rts/R$layout` (其常量字段被改名为 `o`)。本函数提供
    「宿主证据」, 使声明侧只应用**宿主匹配**的映射 (宿主类内约束)。
    过滤口径与 load_member_map/load_member_map_by_class 完全一致 (只读, 不新增映射)。
    """
    hosts_m, hosts_f = {}, {}
    for r in csv.reader(open(SUPPLEMENT_CSV, encoding='utf-8')):
        if not r or len(r) < 5 or r[0] not in ('field', 'method'):
            continue
        if len(r) > 6 and r[6].strip() in ('suspicious-bc-missing',):
            continue
        typ, pkg, cls, obf, sem = r[0], r[1], r[2], r[3], r[4]
        # ★ 2026-09-29 第十轮根因修复：supplement 有一批行的**成员名落到了 class 列**
        # （`obfuscated_member` 为空，真实编码是 `obfuscated_package` + '.' + `obfuscated_class`
        #   = `包.类.成员`）。实测 **121 行**（非 order-align），例如：
        #   · `game.n` + `c`          → 包 game / 类 n / 成员 c      （isEnemy）
        #   · `…game.units` + `ar.e`  → 类 ar / 成员 e               （UnitRegistry 枚举常量 tank/…）
        #   · `units.am` + `bX`       → 类 am / 成员 bX              （player）
        # 不恢复则这些映射**全部装载失败** → 可读成员名（player/hp/…）留在产物里
        # → `cannot find symbol: variable player`（实测 3726 条 cannot-find-symbol 中最宽的一族：
        #   目标域 70 个文件 / 152 条）。
        # `mappings/` 是受保护边界（不改 CSV），只在此处容错读取。
        # 注意：`order-align-*` 行同样是空成员列，但那是**文档行**（成员名只写在 notes 的
        # 「(位置N)」里），按本规则会凭空造出成员 → 必须排除。
        _ver = r[6].strip() if len(r) > 6 else ''
        if not obf and cls and pkg and not _ver.startswith('order-align'):
            _seg = ('%s.%s' % (pkg.strip(), cls.strip())).split('.')
            if len(_seg) >= 3 and all(_seg):
                pkg, cls, obf = '.'.join(_seg[:-2]), _seg[-2], _seg[-1]
        if not sem or not obf:
            continue
        obf_name = obf.split('(')[0].strip()
        sem_name = sem.split('(')[0].strip()
        if not obf_name or not sem_name or obf_name in ('<init>',):
            continue
        if not re.fullmatch(r'[A-Za-z_$][\w$]*', sem_name):
            continue
        if obf_name in JAVA_KEYWORDS or sem_name in JAVA_KEYWORDS:
            continue
        if obf_name == sem_name:
            continue
        if sem_name[0].isupper():
            continue
        host = (_norm_host_pkg(pkg, cls, resolve, alias) if resolve
                else (pkg.replace('/', '.'), cls.replace('/', '.')))
        tgt = hosts_m if typ == 'method' else hosts_f
        tgt.setdefault(sem_name, set()).add(host)
    return ({k: frozenset(v) for k, v in hosts_m.items()},
            {k: frozenset(v) for k, v in hosts_f.items()})


def complete_enum_ctor_args(src):
    """枚举常量参数补齐（2026-09-25）。

    凡显式声明了 `(String,int)` 构造器的枚举，其**每个常量**都必须带 `("标识", 下标)`：
    R8 字节码里的合成枚举构造器被 CFR 照实反出，而 `restore_enum_strings` 只给 02b 里
    能对上的常量补参 → 末位常量裸写（实测 codedisaster 33 个文件由此报
    `constructor cannot be applied to given types; required: String,int; found: no arguments`）。
    补齐后签名与名序与原版一致（原版成员: values/valueOf/<init>/byOrdinal/$VALUES）。
    """
    if 'enum ' not in src or not re.search(r'\(\s*String\s+\w+\s*,\s*int\s+\w+\s*\)', src):
        return src
    m = re.search(r'\benum\s+[\w$]+\s*\{', src)
    if not m:
        return src
    head, body = src[:m.end()], src[m.end():]
    # 常量区 = 到第一个 ';' 或第一个成员声明前的 '}' 为止
    end = len(body)
    semi = body.find(';')
    if semi != -1:
        end = semi
    zone, rest = body[:end], body[end:]
    if not re.fullmatch(r'[\s\w$,"()]*', zone):      # 形态不符则不动（保守）
        return src
    parts = zone.split(',')
    out, idx = [], 0
    for p in parts:
        s = p.strip()
        if not s:
            continue
        mm = re.fullmatch(r'([A-Za-z_$][\w$]*)\s*(\([^()]*\))?', s)
        if not mm:
            out.append(s)
            continue
        name, args = mm.group(1), mm.group(2)
        out.append('%s("%s", %d)' % (name, name, idx) if args is None else '%s%s' % (name, args))
        idx += 1
    sep = ',' if ',' in zone else ','
    joined = (sep + '\n    ').join(out)
    return head + joined + rest



def rename_enum_constants(src, name_to_obf, host_scoped=False):
    """★ 2026-10-01 第 47 轮（PENDING RS-27）：**枚举常量声明**的反向改名通道。

    背景：`reverse_members` 只认「字段/方法声明」，**不认枚举常量声明**
    （形如 `include,` / `exclude;`，第 41 轮本地探针实测零改动 ✗）。
    而 stock 里枚举常量的 `name()` 就是 INI 字符串、字段名才是混淆名
    ⇒ 03 源若写作 `enum X { <INI字符串…>; }`（javac 得 (String,int) ✓、字符串保留 ✓），
      常量名就必须在这里被改成混淆字段名，否则字段名与原版不符 ✗。

    做法：定位**枚举常量区**（`enum X {` 之后到第一个 `;` 或成员声明前），
          逐行把裸常量名按 `name_to_obf` 改名，**保留末尾分隔符**（`,` 或 `;`）。
          仅当该行**整行就是一个标识符 + 可选分隔符**时才动（保守，避免误伤）。
    """
    # ★★★ 2026-10-01 第 52 轮（第二次修正）：**确定性修复「已被 restore_enum_strings 破坏」的形态**。
    #   实测机制：`reverse_members` 的**字段声明通道**会把枚举常量改名（枚举常量长得像字段 ✗）
    #   ⇒ 常量变成混淆名 `a`、`b`… ⇒ 随后 `restore_enum_strings` 按 02b 的键 `a` **命中** ⇒
    #   补 `("INI串", N)` 实参 **并插入** `private X(String,int){}` 构造器 ✗ ⇒ javac 合成
    #   `(String,int,String,int)` ⇒ 结构核对判不符（实测 17 个类）。
    #   此时纯名守卫会拒绝（1 字符名不在映射里 ✗），所以这里**先修这个形态**：
    #     `a("singleThreadedSurface", 0),` … + `private e(String,int){}`
    #   ⇒ 改为 `a,` … 且**删除该自定义构造器** ✓ ⇒ javac 得 `(String,int)` ✓ 与 stock 一致 ✓
    _lines = src.splitlines()
    _ci = None
    for _i, _l in enumerate(_lines):
        if re.match(r'^\s*(?:public\s+|final\s+|abstract\s+|strictfp\s+)*enum\s+[\w$]+\s*\{', _l):
            _ci = _i
            break
    if _ci is not None:
        _body = _lines[_ci + 1:]
        _semi = next((k for k, l in enumerate(_body) if l.strip().endswith(';') and
                      re.search(r'\)\s*;', l)), None)
        if _semi is not None:
            _zone = _body[:_semi + 1]
            _pat = re.compile(r'^(\s*)([A-Za-z_$][\w$]*)\("([^"]*)"\s*,\s*(\d+)\)\s*([,;])\s*$')
            _ms = [_pat.match(l) for l in _zone]
            _ms = [m for m in _ms if m] if all(m or not l.strip() for m, l in zip(_ms, _zone)) else []
            # ★★ 第 68 轮（RS-40）：门槛由「≥2」放宽为「≥1」——**仅当调用方给了宿主精确表** ✓。
            #   实测缺陷：`gameFramework/n/h`（AIDifficulty）**只有一个常量** ✗ ⇒
            #   `len(_ms) >= 2` 恒假 ⇒ 破损形态（`a("normal", 0);` + 自定义构造器 ✗）
            #   从不被修复 ⇒ 产物 `<init>` 同时有 `(String,int)` 与 `(String,int,String,int)` ✗、
            #   `ldc` 多出 `"a"` ✗ ⇒ 结构核对判不符。
            _min_n = 1 if host_scoped else 2
            if len(_ms) >= _min_n and len(_ms) == sum(1 for l in _zone if l.strip()):
                # 形态确认：常量全为 `名("串", N)`
                _newzone = []
                for _k, _m in enumerate(_ms):
                    # ⚠️ 第 68 轮**失败实验记录（勿重做）**：曾把此处改为用**字符串**作常量名
                    #   （`a("normal",0)` ⇒ `normal;` ✗，理由是「保留 INI 键语义」）。
                    #   **实测净负** ✗：`gameFramework/n/h` 确实转合格 ✓，但 **3 个多常量类掉出** ✗
                    #   （`game/units/custom/ad` / `af` / `ah`）—— 它们的 `串 → 混淆名` 在宿主表里
                    #   **不存在** ✗ ⇒ 常量名停在字符串上 ⇒ 字段名与原版不符 ⇒ 判不符 ✗。
                    #   **可用类 860 → 858（−2）** ⇒ 已回退，恢复「只去实参、**保留原名**」✓。
                    _newzone.append('%s%s%s' % (_m.group(1), _m.group(2), _m.group(5)))
                _rest = _lines[_ci + 1 + _semi + 1:]
                # ★★ 第 54 轮修正：删掉被插入的自定义构造器 —— **必须兼容跨行形态** ✗
                #   实测 `game/q` 的反向源是
                #       private q(String string, int n2) {
                #       }
                #   而上一版只匹配**单行** `… ) {}` ⇒ 漏删 ⇒ 该枚举仍带自定义构造器 ⇒
                #   编译报「constructor cannot be applied to given types」（常量已无实参 ✗）
                #   ⇒ 该文件进 `compile-errors-skip.txt` ⇒ **整类不再产出** ✗（实测 13 个类）。
                #   这里改为**先按行筛（单行），再对剩余文本做跨行正则** ✓。
                _rest = [l for l in _rest
                         if not re.match(r'^\s*private\s+[\w$]+\s*\(\s*String\s+\w+\s*,\s*int\s+\w+\s*\)\s*\{\s*\}\s*$', l)]
                _tail_txt = '\n'.join(_rest)
                _tail_txt = re.sub(
                    r'(?m)^\s*private\s+[\w$]+\s*\(\s*String\s+\w+\s*,\s*int\s+\w+\s*\)\s*\{\s*\n\s*\}\s*\n?',
                    '', _tail_txt)
                _tail_txt = re.sub(
                    r'(?m)^\s*(?:private|public|protected)?\s*[\w$]+\s*\(\s*String\s+\w+\s*,\s*int\s+\w+\s*\)\s*\{\s*\}\s*\n?',
                    '', _tail_txt)
                src = '\n'.join(_lines[:_ci + 1] + _newzone + _tail_txt.splitlines())
    if 'enum ' not in src or not name_to_obf:
        return src
    m = re.search(r'\benum\s+[\w$]+\s*\{', src)
    if not m:
        return src
    head, body = src[:m.end()], src[m.end():]
    end = len(body)
    semi = body.find(';')
    if semi != -1:
        end = semi
    zone, rest = body[:end], body[end:]
    if not re.fullmatch(r'[\s\w$,"()\[\].]*', zone):   # 形态不符则不动（保守）
        return src
    # ★ 守卫（第 47 轮实测必需）：**只有该枚举的「全部」常量都在映射中时才改名**。
    #   理由：`name_to_obf` 是**宿主无关**的可读名→混淆名表，而 03 树里确有枚举的常量名
    #   恰好与映射中的成员名同名（实测 `logicBooleans/Logic…` 的 `unit`/`point` ✗）
    #   ⇒ 若不加守卫会**误改**它们。RS-27 那类枚举的常量**全部**命中 ✓，误报类只中一两个 ✗。
    _names = [x for x in re.findall(r'(?m)^\s*([A-Za-z_$][\w$]*)\s*[,;]?\s*$', zone)]
    # ★ 第 68 轮（RS-40）：同上 —— 宿主精确表下允许**单常量**枚举 ✓（守卫理由见上，
    #   它针对的是**宿主无关**表的误报风险 ✗，宿主精确表已消除该风险 ✓）。
    if len(_names) < (1 if host_scoped else 2) or any(n not in name_to_obf for n in _names):
        return src
    out = []
    for line in zone.split('\n'):
        mm = re.match(r'^(\s*)([A-Za-z_$][\w$]*)([,;]?)(\s*)$', line)
        if mm and mm.group(2) in name_to_obf:
            out.append('%s%s%s%s' % (mm.group(1), name_to_obf[mm.group(2)], mm.group(3), mm.group(4)))
        else:
            out.append(line)
    return head + ' /*RS27-INI*/' + '\n'.join(out) + rest


def _rs27_host_map():
    """★★★ 2026-10-01 第 67 轮（PENDING RS-39）：**直接读 supplement** 的宿主精确表。

    为什么必须绕过 `fmap`/`mmap`/`per_map`：这三张表都由 `load_member_map*()` 构建，而它们对
    **同名多行**的映射一律**整名丢弃** ✗（RS-35 已证「全局回退」为负 ✗；第 66 轮又实测
    `per_map[1][宿主]` 对 `game.a.j`/`game.a.k` **根本没有键** ✗）。枚举常量名恰是常见词
    （`Pre`/`Prepare`/`Active`/`Main`/`normal` ✗）⇒ 查不到 ⇒ 常量名不改 ⇒ 产物
    `putstatic` 仍是 INI 串 ✗ 而 stock 是 `a`/`b`/`c` ✓ ⇒ 结构核对判不符。
    对照：`include`/`exclude` **唯一** ⇒ 未被丢弃 ⇒ `game/a/b` 已合格 ✓。

    本表来自 `supplement.csv`（**唯一映射数据库** ✓）：
      · 宿主列已由 **H-9 宿主键归一** 处理（短写包名 → 全限定包 ✓）；
      · **不做歧义丢弃** ✓ ⇒ 零新增歧义（与 RS-35 的全局改动**本质不同** ✓）；
      · 只在**本文件宿主**下查 ✓ ⇒ 宿主精确 ✓。
    键 = `(混淆包, 混淆类)`（与构建的 `(pkg02, obf02)` 一致 ✓）；值 = `{可读名 → 混淆成员}`；
    字段行优先于方法行（枚举常量是字段 ✓）。
    """
    global _RS27_HOSTS
    if _RS27_HOSTS is None:
        d = {}
        try:
            sp = CLASS_DISCOVERIES.parent / 'supplement.csv'
            for r in csv.DictReader(open(sp, encoding='utf-8', newline='')):
                pkg = (r.get('obfuscated_package') or '').strip()
                cls = (r.get('obfuscated_class') or '').strip()
                sem = (r.get('meaningful_name') or '').strip()
                obf = (r.get('obfuscated_member') or '').strip()
                # ★★★ 2026-10-01 第 86 轮（PENDING RS-66）：**剥掉 `obfuscated_member` 的参数部分** ✓
                #   为什么必须：该列**含参数**（如 `closePopup(java.lang.String)` ✗、
                #   `a(int)` ✗ —— RS-55/76 的补行也用了这种写法 ✓）⇒ 若原样当**成员名**写进代码 ✗，
                #   会产出 `this.alert(java.lang.String)(string2);` ✗ / `this.closePopup()()` ✗
                #   ⇒ javac `';' expected` / `')' expected` / `not a statement` ✗
                #   —— 实测第 84 轮（RS-64）的访问侧回退就是这样把错误量推高 **+56.5%** ✗（11,448 条 ✓）。
                #   该表同时被**枚举常量通道**使用 ✓ —— 那里的值本就是裸名 ✓ ⇒ 本处理对其**无副作用** ✓。
                obf = obf.split('(')[0].strip()
                if not (pkg and cls and sem and obf) or len(sem) < 2:
                    continue
                slot = d.setdefault((pkg, cls), {})
                if r.get('type') != 'field' and sem in slot:
                    continue          # 字段行优先 ✓
                slot[sem] = obf
            # ★★★ 2026-10-01 第 72 轮（PENDING RS-43）：**宿主作用域覆盖文件**优先 ✓。
            #   背景（RS-42 已查明 ✓）：`supplement.csv` **不是"纯增量"面** ✗ —— 直接加行会经
            #   `load_member_map()` 建的**宿主无关全局表** ✗ 把某个可读名从唯一变歧义 ⇒ 整名丢弃 ✗
            #   ⇒ 破坏**无关类**（实测：加 `gameFramework/d/h` 的 5 行 ⇒ `gameFramework/b/p` 掉出 ✗）。
            #   故这类「宿主精确、全局会误伤」的映射改放**独立文件** ✓：
            #     `mappings/generated/host-const-overrides.csv`
            #   只在本函数（宿主精确查询 ✓）里读取 ⇒ 全局表**零扰动** ✓✓。
            ov = CLASS_DISCOVERIES.parent / 'generated' / 'host-const-overrides.csv'
            if ov.exists():
                for r in csv.DictReader(open(ov, encoding='utf-8', newline='')):
                    pkg = (r.get('obfuscated_package') or '').strip()
                    cls = (r.get('obfuscated_class') or '').strip()
                    sem = (r.get('meaningful_name') or '').strip()
                    obf = (r.get('obfuscated_member') or '').strip()
                    # ★ 第 87 轮（RS-67）：与上方 supplement 装载点**同口径**剥参数 ✓
                    #   （`host-const-overrides.csv` 现已被删 ✓ ⇒ 属**潜在**风险 ✓；
                    #    两条装载路径必须行为一致 ✓，否则该表复活时又会引入 `名(参数)` ✗）
                    obf = obf.split('(')[0].strip()
                    if not (pkg and cls and sem and obf) or len(sem) < 2:
                        continue
                    d.setdefault((pkg, cls), {})[sem] = obf
        except Exception:
            pass
        _RS27_HOSTS = d
    return _RS27_HOSTS


def _rs27_name_to_obf(host=None):
    """★ 2026-10-01 第 52 轮：为 `rename_enum_constants` 提供**模块级**映射（懒加载一次）。

    为什么不用 `reverse_members` 内的 `name_to_obf`：那个函数对「无需成员改名」的文件
    **提前 return** ✗ ⇒ 调用点等于永不执行（本轮实测：反向源里既无标记、又被
    `restore_enum_strings` 补回实参与构造器 ✗）。故改在 `main()` 里、**紧跟
    `restore_enum_strings` 之后**调用本通道 —— 那里能拿到 `rev`，但拿不到函数内的映射，
    所以这里自建一份（与探针实测所用同源：`load_member_map()` 的 fmap/mmap）。

    ★ 第 67 轮（RS-39）：支持传入**混淆宿主键** `(pkg02, obf02)` ✓ ⇒ 用 `_rs27_host_map()`
    的宿主精确行**覆盖**基础表 ✓（基础表对常见词查不到 ✗）。
    """
    global _RS27_N2O
    if _RS27_N2O is None:
        mmap, fmap = load_member_map()
        d = {}
        for s, o in list(fmap.items()) + list(mmap.items()):
            if len(s) >= 2:                      # 与 `reverse_members` 内同口径（保守）
                d.setdefault(s, o)
        _RS27_N2O = d
    if host:
        hm = _rs27_host_map().get(host)
        if hm:
            d = dict(_RS27_N2O)                  # 叠加：宿主精确优先 ✓
            d.update(hm)
            return d
    return _RS27_N2O


_RS27_N2O = None
# ★ 第 67 轮（RS-39）：supplement 直读的宿主精确表 `{(混淆包, 混淆类): {可读名 → 混淆成员}}`
_RS27_HOSTS = None
# ★ 第 58 轮（RS-33）：stock 类 FQN 的「末两段」集合缓存（用于 access_re 的类型引用守卫）
_RS_TYPE_PAIRS = None


def restore_enum_strings(src, rel):
    """从 02b 同名文件恢复枚举常量字符串参数 (INI 解析键)。

    B4 实证: 03 中大量枚举的字符串构造参数丢失 (如 UnitState 的 a("verysmall", 0)
    被简化为 a,), 运行时 INI 解析 "Unknown value" 崩溃。
    02b 按混淆路径组织, 与反向产物路径一致 → 常量名对齐直接补参。
    """
    # ★★ 顺序守卫（2026-10-01 第 52 轮）：若该源已由 `rename_enum_constants` 处理过
    #   （枚举头带 `/*RS27-INI*/` 标记），则**必须跳过本函数** ✗——
    #   否则会按 02b 的键（此时常量名已是混淆名 `a`/`b`…，恰好命中 ✗）把
    #   `("INI串", N)` 实参**和** `private X(String,int){}` 构造器又加回来 ⇒
    #   javac 合成 `(String,int,String,int)` ✗ ⇒ 结构核对判不符（本轮实测 17 个类正是如此）。
    if '/*RS27-INI*/' in src:
        return src
    if 'enum ' not in src:
        return src
    b2 = ROOT / '02b-decompiled' / rel
    if not b2.exists():
        return src
    b2src = b2.read_text(encoding='utf-8', errors='ignore')
    pairs = {}
    for m in re.finditer(r'^\s*([A-Za-z_$][\w$]*)\("([^"]*)"(?:\s*,\s*(\d+))?\)', b2src, re.M):
        pairs.setdefault(m.group(1), (m.group(2), m.group(3)))
    if not pairs:
        return src
    mcls = re.search(r'enum\s+([A-Za-z_$][\w$]*)', src)
    enum_name = mcls.group(1) if mcls else None
    need_ctor = False
    lines = src.split('\n')
    out = []
    for line in lines:
        # ★ 2026-10-01 第 45 轮（PENDING **RS-26**）：正则原为 `(,?)`，**匹配不到最后一个常量** ——
        #   枚举常量区的末位写作 `notOwn;`（带分号）⇒ 它永远拿不到 `("字符串", 下标)` 实参，
        #   而构造器是 `(String,int,String[,int])` 形态 ⇒ javac 报
        #   `constructor cannot be applied to given types` ⇒ **该类在冲突遍里编不过、不再产出** ✗
        #   本项是 RS-23 族修复无法落地的**真正原因**（第 34/35 轮两次「修对了却不产出」）。
        #   改法：`(,?)` → `([,;]?)` 且**保留**捕获到的分隔符（末位是 `;`，不能一律写 `,`）。
        m = re.match(r'^(\s*)([A-Za-z_$][\w$]*)([,;]?)\s*$', line)
        if m and m.group(2) in pairs and not line.lstrip().startswith('//'):
            name = m.group(2)
            s, n = pairs[name]
            args = f'("{s}"' + (f', {n}' if n else '') + ')'
            out.append(f'{m.group(1)}{name}{args}{m.group(3)}')
            need_ctor = True
            continue
        out.append(line)
    if need_ctor and enum_name:
        if ('private ' + enum_name + '(') not in src and (enum_name + '(String') not in src:
            # 构造器必须插入枚举体内 (最后一个 '}' 之前)
            for i in range(len(out) - 1, -1, -1):
                if out[i].strip() == '}':
                    out.insert(i, f'    private {enum_name}(String string, int n) {{}}')
                    break
    return '\n'.join(out)


# ── 产出守卫正则 (2026-09-29)：声明名位置不得出现 FQN ──────────────────────
# 见 fast_reverse_source 末尾的调用点与说明。
_MODS_G = (r'(?:public|private|protected|static|final|transient|volatile|'
           r'strictfp|synchronized|native)')
_BASIC_G = r'(?:boolean|byte|char|short|int|long|float|double|void|String|Object)'
_FQNN_G = r'([A-Za-z_$][\w$]*(?:\.[A-Za-z_$][\w$]*)+)'
_GUARD_PATS = (
    # 情形 A：修饰符+ 类型 含点的名字 [=;(]  —— 排除 package/import/return 等无修饰符行
    re.compile(r'(?m)^([ \t]*(?:' + _MODS_G + r'[ \t]+)+[A-Za-z_$][\w$.<>\[\],?]*[ \t]+)'
               + _FQNN_G + r'([ \t]*[=;(])'),
    # 情形 B：无修饰符但类型是基本类型/常见简单类型（包私有字段）
    re.compile(r'(?m)^([ \t]*(?:' + _BASIC_G + r')[ \t]+)' + _FQNN_G + r'([ \t]*[=;(])'),
    # ★★★ 情形 C（2026-10-01 第 79 轮，PENDING RS-58）：**无修饰符 + 单个简单标识符类型（可带 `[]`）**
    #   **+ 含点的名字 + 行尾分号** ✓
    #   为什么必须补：情形 A **要求有修饰符** ✗、情形 B 的类型**只限基本类型** ✗
    #   ⇒ **包私有**字段全部漏网 ✗，实测（A/B 反向编译日志 ✓）：
    #       `q[] com.corrodinggames.rts.gameFramework.b.f;`   ← 情形 A 漏（无修饰符 ✗）
    #       `Main qom.corrodinggames.rts.a.a;`                ← 情形 B 漏（类型 `Main` 非基本类型 ✗）
    #   代价：`';' expected` + `<identifier> expected` 合计 **35,292 条** ✗（族第 2、第 5 位 ✓）
    #   ⚠️ **第 78 轮那次过宽实现造成 −735 大倒退（RS-57 ✗）**，根因是**类型组允许点** ✗
    #      ⇒ `import java.util.ArrayList;` ✗ 被当成「类型 `import` + 名字 `java.util.ArrayList`」✗
    #      ⇒ **把每个 import 截断成末段** ✗（03 树实测误伤 **12,626 行** ✗）。
    #   本版四条约束（已离线校验 ✓ + 12 条字面样本全部符合预期 ✓）：
    #      ① 行首**不是关键字** ✗（import/package/return/throw/new/… 全排除 ✓）
    #      ② 类型 = **单个简单标识符**（**不含点** ✗，可带 `[]` ✓）
    #      ③ 名字**含点** ✓（合法的「`FQN 类型 简单名;`」因此天然不匹配 ✓）
    #      ④ 行尾**恰为 `;`** ✓（排除赋值/调用等 ✓）
    re.compile(r'(?m)^([ \t]*(?!(?:import|package|return|throw|new|else|case|default|break|continue|'
               r'assert|do|while|if|for|switch|try|catch|finally|yield|this|super|instanceof|class|'
               r'interface|enum|extends|implements)\b)'
               r'[A-Za-z_$][\w$]*(?:\[\])?[ \t]+)' + _FQNN_G + r'([ \t]*;[ \t]*$)'),
)


def fast_reverse_source(src, cls03, tgt, mapping, keep_cls=False):
    """快速类名反向 (交替正则一次扫描, 替代 runtime_patch 的逐条 sub)。
    同 reverse_source 语义: package/类声明/构造器/import/全限定/裸引用。
    keep_cls=True: 类名/包名保持 (03 重建类无 jar 对应), 仅反向引用 (import/全限定/裸引用)。
    """
    pkg02, obf02 = tgt
    # 本文件**声明的**可读包名 —— 裸引用 (同包简单名) 的解析基准。
    # 必须在第 0 步改写 package 之前取。
    _pm = re.search(r'(?m)^\s*package\s+([\w.]+)\s*;', src)
    pkg_src = _pm.group(1) if _pm else ''
    if not keep_cls:
        # 0. package
        src = re.sub(r'^package [^;]+;', 'package ' + pkg02 + ';', src, count=1, flags=re.M)
        # 1. 类声明名 (锚定行首修饰符, 避免误中 import 行 WeaponConfig$1)
        cls_re = re.compile(r'^(\s*(?:(?:public|protected|private|abstract|final|strictfp|static)\s+)*(?:class|enum|interface)\s+)' + re.escape(cls03) + r'(\s*(?:extends|implements|[{<]))', re.M)
        def cls_repl(m):
            return m.group(1) + obf02 + m.group(2)
        src2 = cls_re.sub(cls_repl, src, count=1)
        if src2 == src:
            # 兜底: 无修饰符形态 (类声明行直接 class X)
            src = re.sub(r'(?m)^(\s*(?:class|enum|interface)\s+)' + re.escape(cls03) + r'\b',
                         lambda m: m.group(1) + obf02, src, count=1)
        else:
            src = src2
        # 1b. 构造器名 (多级内部类 $N$M 需整体捕获, B5 修复: (\$\w+)* 的 group 只留最后段,
        #     h$3$1 曾漏替换成 h$1)
        base03 = cls03.split('$')[0]
        base02 = obf02.split('$')[0]
        src = re.sub(r'\b' + re.escape(base03) + r'((?:\$\w+)*)\s*\(',
                     lambda m: base02 + (m.group(1) or '') + '(', src)
    # 2. 交替正则: 全部映射 (语义类名 → 02包.02名); 查找用 dict O(1)
    # 2026-09-25: 候选消歧交给 _ref_maps（按本文件目标混淆包 + REF_INDEX 精确命中），
    # 不再无条件取 cands[0] —— 详见 _ref_maps 文档串。
    name_to_fqn, name_to_obf = _ref_maps(mapping, pkg02)
    # 本文件 import 的目标 (可读简单名 → 混淆全限定名)。
    # JLS 6.4.1: single-type-import **遮蔽**同包的同名顶层类 → 裸引用解析必须让
    # import 先于「同包 REF_INDEX」判定，否则「import 了别的包的 Thing + 同包也有
    # Thing」的文件会出现 import 与本体指向两个不同类的静默错位。
    imported = {}
    # ★ 2026-09-29 第九轮根因修复：import 去重表（**混淆**简单名 → 首个可读名）。
    # `imp_repl` 逐条独立改写 import，**不检查多条 import 改写后是否会落到同一个混淆简单名**。
    # 两个不同可读类映射到同简单名的混淆类（`TaskRunner`→`a.a.a.i` 与 `SocketStats`→`a.a.i` 同叫 `i`）
    # 时，产物里就有两条 `import ...i;` → javac 硬错误
    #   `a type with the same simple name is already defined by the single-type-import`（实测 277 条）
    # 以及随之而来的 `reference to i is ambiguous`（实测 176 条）。
    _emitted_imp = {}
    # ── 1c. 撞名预扫 (2026-09-26 缺陷修复) ────────────────────────────────
    # 反向只做「可读简单名 → 混淆名」的**逐名替换**，不校验替换后是否撞车：
    # 两个不同包的可读类名映射后可能同简单名（实测 `TaskRunner`→a.a.a.i 与
    # `SocketStats`→a.a.i 同叫 `i`；全局 `l`/`a`/`y` 等同理），于是产物里出现
    #   ① `a type with the same simple name is already defined by the single-type-import`
    #      （轮 1 实测 200 条 / 92 文件）
    #   ② `reference to X is ambiguous`（轮 1 实测 250+ 条）
    # —— 两族都是 javac 硬错误，直接把文件踢出编译。
    # 修法：扫描本文件**代码段**实际用到的可读简单名 → 各自解析出混淆 FQN →
    # 按混淆简单名分组；同组有 >1 个不同 FQN 时保留 1 个用简单名（优先「就在本文件
    # 目标包里」的那个，其次用得最多的），其余**全部改用 FQN**并丢弃其 import。
    imp_pre = {}
    for m in re.finditer(r'import ([^;]+);', src):
        body = m.group(1).strip()
        hit = REF_INDEX.get(body)
        fqn = (hit[0] + '.' + hit[1]) if hit is not None \
            else name_to_fqn.get(body.rsplit('.', 1)[-1].strip())
        if fqn:
            imp_pre[body.rsplit('.', 1)[-1].strip()] = fqn

    def _resolve(tok):
        """裸 token → 混淆 FQN（与 bare_repl 同判据；未知返回 None）。"""
        if tok in imp_pre:
            return imp_pre[tok]
        if pkg_src:
            hit = REF_INDEX.get(pkg_src + '.' + tok)
            if hit is not None:
                return hit[0] + '.' + hit[1]
        return name_to_fqn.get(tok)

    _usage = {}
    for _line in src.split('\n'):
        if _line.lstrip().startswith(('import ', 'package ')):
            continue
        _parts = LITERAL_SPLIT.split(_line)
        for _i in range(0, len(_parts), 2):
            for _t in re.findall(r'(?<![\w.])([A-Za-z_$][\w$]*)(?![\w$])', _parts[_i]):
                _usage[_t] = _usage.get(_t, 0) + 1
    _by_simple = {}
    for _tok, _n in _usage.items():
        _fqn = _resolve(_tok)
        if _fqn:
            _by_simple.setdefault(_fqn.rsplit('.', 1)[-1], {})[_fqn] = _tok
    force_fqn = {}
    for _simple, _fqns in _by_simple.items():
        if len(_fqns) < 2:
            continue
        _same_pkg = [f for f in _fqns if pkg02 and f.rsplit('.', 1)[0] == pkg02]
        if _same_pkg:
            _win = _same_pkg[0]
        else:
            _win = max(_fqns, key=lambda f: (_usage.get(_fqns[f], 0), f))
        for _f, _tok in _fqns.items():
            if _f != _win:
                force_fqn[_tok] = _f
    if force_fqn:
        print('  撞名预扫: 强制全限定 %d 个可读类名（%s）'
              % (len(force_fqn), ', '.join('%s→%s' % (k, v) for k, v in
                                           list(force_fqn.items())[:4])))
    # import 行: ① REF_INDEX 按可读全限定名精确命中 (可跨包同名正确落位);
    #           ② 退化为按简单名查 dict (等价旧 1,140 项循环)
    def imp_repl(m):
        line = m.group(0)
        body = line[len('import '):].rstrip().rstrip(';').strip()
        _s0 = body.rsplit('.', 1)[-1].strip()
        if _s0 in force_fqn:
            # 该可读名已全部改用 FQN → 丢弃 import（否则与赢家同名双 import 仍是硬错误）
            imported[_s0] = force_fqn[_s0]
            return ''

        def _emit(readable_key, fqn):
            """按**混淆简单名**去重后写入 import；撞名则丢弃并强制该可读名走 FQN。"""
            obf = fqn.rsplit('.', 1)[-1]
            if _emitted_imp.get(obf) not in (None, readable_key):
                # 该混淆简单名已被另一条 import 占用 → 本 import 必须丢弃，
                # 并把该可读名的所有裸引用**强制全限定**（否则裸名会绑到另一个类）
                force_fqn[readable_key] = fqn
                imported[readable_key] = fqn
                return ''
            _emitted_imp[obf] = readable_key
            imported[readable_key] = fqn
            return 'import ' + fqn + ';'

        hit = REF_INDEX.get(body)
        if hit is not None:
            return _emit(body.rsplit('.', 1)[-1], hit[0] + '.' + hit[1])
        simple = body.rsplit('.', 1)[-1].strip()
        if simple in name_to_fqn:
            return _emit(simple, name_to_fqn[simple])
        return line
    src = re.sub(r'import [^;]+;', imp_repl, src)
    # 全限定 (任意包前缀且至少含 1 个点, 末段类名在映射 → 整条替换为混淆 FQN)
    # 覆盖 com.corrodinggames.rts.* 与重建包 (network.reliableudp.core.Packet → a.a.c);
    # 字典驱动单次扫描 (1,140 分支交替正则在长文件上灾难性回溯, 弃用);
    # 单 token 类名留给裸引用替换 (构造器/类名引用不得加包前缀);
    # this./super. 开头是成员访问形态, 跳过 (字段大写开头罕见, 错误迭代兜底)
    fq_out = []
    fq_last = 0
    for m in re.finditer(r'(?<![\w.])([A-Za-z_$][\w$]*(\.[A-Za-z_$][\w$]*)+)', src):
        tok = m.group(1)
        segs = tok.split('.')
        if segs[0] in ('this', 'super'):
            continue
        # 2026-09-26 缺陷修复（本族是反向源编不过的**头号**原因）：
        # 原实现只在「整条 token」上查映射，而 03 树里最常见的形态是
        # `com.corrodinggames.rts.gameFramework.GlobalState.b("...")` ——
        # 「可读类 FQN + 成员名」，整条 token 的末段是**成员名**而非类名，
        # 于是 REF_INDEX / name_to_fqn 双双落空 → 可读 FQN 原样留在产物里 →
        # javac `cannot find symbol: class GlobalState/GameUtils/PlayerState/UnitRegistry`。
        # 实测该形态上千处（轮 2 级联 3,671 条 cannot find symbol 的主因）。
        # 修法：**按前缀由长到短**匹配，命中即替换前缀、成员后缀原样保留。
        # 前缀用 REF_INDEX（可读 FQN 精确命中，跨包同名安全）；末段启发（name_to_fqn）
        # 只在整条 token 或「前缀末段首字母大写（可读类名形态）」时启用，避免把
        # `player.unit.stats` 这类成员链误当类名。
        repl, rest = None, []
        # ★ 2026-09-29 根因修复：整条 token 若已是「原版 jar 中真实存在的类 FQN」，则**原样保留**。
        # 缺此保护时，`com.a.a.a.a`（KryoNet 真实类，恒等）因 REF_INDEX 未命中而落入下方
        # 「末段启发」→ 被替换成 `name_to_fqn['a']` = `android.net.http.a`
        # → 产物 `android/util/SparseArray` 引用**包私有**类 `android.net.http.a`
        # → 运行时 `IllegalAccessError: failed to access class android.net.http.a`
        # → V5 GUI 冒烟崩溃、V6 回放 BLOCKED（实测 2026-09-29）。
        if JAR_CLASS_SET and tok.replace('.', '/') in JAR_CLASS_SET:
            continue
        for k in range(len(segs), 1, -1):
            prefix = '.'.join(segs[:k])
            hit = REF_INDEX.get(prefix)
            if hit is not None:
                repl, rest = hit[0] + '.' + hit[1], segs[k:]
                break
            last = segs[k - 1]
            if last in name_to_fqn and (k == len(segs) or last[:1].isupper()):
                repl, rest = name_to_fqn[last], segs[k:]
                break
        if repl is None:
            continue
        fq_out.append(src[fq_last:m.start()])
        fq_out.append(repl + ('.' + '.'.join(rest) if rest else ''))
        fq_last = m.end()
    if fq_out:
        src = ''.join(fq_out) + src[fq_last:]
    # 裸引用 (单词边界, 非 import 行; 字典驱动单 token 扫描, 无回溯)
    # B5 修复: 跨包类型简单名与目标同包类撞名 (如 custom/k 的 UnitInstance→am 被同包
    # custom/am 抢占, 字段类型错位 → 运行时 NoSuchFieldError) → 撞名时全限定
    #
    # D-7 修复 (保留自身身份时的类名保护): keep_cls=True 表示本文件保留 03 的包/类
    # 身份 (无类映射分支或 D-7 让位者)。此时**本文件声明的类名与其外层类名**不得被
    # 裸引用替换器改名 —— 否则会出现 javac 硬错误
    # 「class X is public, should be declared in a file named X.java」或
    # 「duplicate class」 (实测: TimeUtils.java 的类名被改成 br, UnitRegistry.java
    # 被改成 ar)。外层类名一并保护: $N 内层类必须仍归属其 03 外层名。
    keep_names = set()
    if keep_cls:
        keep_names.add(cls03)
        keep_names.add(cls03.split('$')[0])
    elif obf02:
        # 2026-09-26 缺陷修复（家族级）：**反向后的类声明名/构造器名不得再被裸名替换器改写**。
        # 实测：`UnitTypeComparator`（03 包 …custom）反向成 `com/corrodinggames/rts/game/q.java`，
        # 声明先被 steps 0/1 正确改成 `q`，但随后的裸名 pass 又把 `q` 按「同包撞名 → 全限定」
        # 规则改成 `com.corrodinggames.rts.game.units.custom.q` → 产物里出现
        # `public enum com.corrodinggames.rts.game.units.custom.q {`：
        #   ① 该类所在包（`…game`）从此有一个名为 `com` 的类 → **把 `com` 包名遮蔽**，
        #      同包其它 10 个文件的全部 `com.corrodinggames.*` 全限定引用集体报
        #      `cannot find symbol: class corrodinggames, location: class com`（实测 119 条）；
        #   ② 另有 9 条 `';' expected` 语法错。
        keep_names.add(obf02)
        keep_names.add(obf02.split('$')[0])
    # ★ 2026-09-29 第二十轮根因修复：**JDK 类型简单名不得被裸名替换器改写**。
    # 实测 `src-readable/…/game/units/UnitRegistry.java` L90 的 `extends Enum`
    # （= `java.lang.Enum`，JDK 类）被改成 **`extends a.d`** ——
    # `a.d` 既不是 `java.lang.Enum`、也不是任何映射目标，纯属误改名。
    # 受影响文件 **6 个**：`game/a/a/b`、`game/units/{ar,n,o}`、`gameFramework/{at,m/p}`
    # —— 全都是「**带常量体的枚举被 CFR 渲染成 abstract class**」的那一族，
    # 它们因此报 `cannot find symbol` / `interface expected here`。
    # `Enum` 是非限定的 JDK 名，任何「可读简单名 → 混淆名」的映射都不应命中它。
    keep_names.add('Enum')
    out_lines = []
    # ★ 2026-09-29：「import 遮蔽表」= 混淆简单名 → 被该 import 绑定的混淆 FQN。
    # `imported` 的**键是可读简单名**（如 ExperimentalHoverUnit），而 bare_repl 产出的是
    # **混淆简单名**（如 c），故必须另建按混淆简单名索引的表才能检测遮蔽。
    _imp_obf = {}
    for _f in imported.values():
        if _f:
            _imp_obf[_f.rsplit('.', 1)[-1]] = _f
    # ★ 2026-09-29：区分「声明名」用的关键字集。**必须排除基本类型**
    # （`float`/`boolean`/`void`/`int`…），因为 `<基本类型> <名>` 正是**声明**形态。
    _DECL_KW = {
        'abstract', 'assert', 'case', 'catch', 'class', 'const', 'continue', 'default',
        'do', 'else', 'enum', 'extends', 'final', 'finally', 'for', 'goto', 'if',
        'implements', 'import', 'instanceof', 'interface', 'native', 'new', 'package',
        'private', 'protected', 'public', 'record', 'return', 'sealed', 'static',
        'strictfp', 'super', 'switch', 'synchronized', 'this', 'throw', 'throws',
        'transient', 'try', 'var', 'volatile', 'while', 'yield', 'true', 'false', 'null',
    }
    pkg_classes = set()  # 目标包的同包类名 (jar 混淆名)
    if pkg02 and JAR_CLASS_SET:
        pre = pkg02.replace('.', '/') + '/'
        pkg_classes = {n[len(pre):].split('$')[0] for n in JAR_CLASS_SET if n.startswith(pre)}
    # ★ 2026-09-29 第十二轮根因修复：**枚举常量区不得走裸名替换**。
    # 常量区里的 `a, b, c, d, …` 是**枚举常量名**，但裸名替换器会把它们当成
    # **可读类简单名**去套 `name_to_obf` / `force_fqn` /「同包 REF_INDEX」
    # （`a`/`b`/`c`/`d` 确实是大量类的可读简单名）→ 常量被逐位改名后互相撞名。
    # 实测 `gameFramework/bs`（03 源 `GamePhase.java` 的 `a("total") … D("init_unitcolour")`
    # 共 30 个互不相同常量）被改成 `a,b,c,f,e,i,b,s,i,b,a,l,m,c,a,p,…` →
    # `variable X is already defined in enum Y` **197 条 / 21 个枚举全废**
    # （`bs`/`af`/`ah`/`WeaponTypeEnum`/`MovementTypeEnum`…），
    # 并让 43~82 个引用它们的文件级联失败。
    _enum_zone = set()
    _ls = src.split('\n')
    for _i, _l in enumerate(_ls):
        if not re.match(r'\s*(?:public\s+|private\s+|protected\s+)?(?:strictfp\s+)?'
                        r'enum\s+[A-Za-z_$][\w$]*\s*\{', _l):
            continue
        # 常量区 = enum 头之后，直到第一个「顶层成员起点」（`;` / `}` / 修饰符 / 注解）
        for _j in range(_i + 1, len(_ls)):
            _s = _ls[_j].strip()
            if (_s.startswith(';') or _s.startswith('}')
                    or re.match(r'(?:public|private|protected|static|final|abstract|'
                                r'strictfp|volatile|transient|synchronized|native|@)', _s)):
                break
            _enum_zone.add(_j)
        break

    for _li, line in enumerate(src.split('\n')):
        if _li in _enum_zone:
            out_lines.append(line)          # 枚举常量区：原样保留，绝不改名
            continue
        if line.lstrip().startswith('import ') or line.lstrip().startswith('package '):
            out_lines.append(line)
            continue
        def _is_decl_name(s, pos):
            """`pos` 处 token 是否处于「声明名」位置（前面紧跟一个**非关键字**标识符）。

            如 `public float ai;` 的 `ai`、`public strictfp au n(...)` 的 `n`。
            裸名替换器按**裸标识符**统计并混用「类型/字段/方法」三个命名空间，
            不保护就会把**字段名/方法名**也改成 FQN → `';' expected` /
            `<identifier> expected` / `variable com is already defined`
            （实测 `units/y.java` L116 / L3197 / L6130）。
            前置词是**关键字**（`extends`/`new`/`implements`/`return`…）时不算声明名——
            那些位置正是**类型引用**，必须照常替换。
            """
            j = pos - 1
            while j >= 0 and s[j] in ' \t':
                j -= 1
            if j < 0 or not (s[j].isalnum() or s[j] in '_$'):
                return False
            k = j
            while k >= 0 and (s[k].isalnum() or s[k] in '_$'):
                k -= 1
            return s[k + 1:j + 1] not in _DECL_KW

        def bare_repl(m2):
            tok = m2.group(0)
            if tok in keep_names:
                return tok
            # 1c 撞名预扫的裁决：该可读名一律用混淆 FQN（避免与同简单名的另一类撞车）
            if tok in force_fqn:
                if _is_decl_name(m2.string, m2.start()):
                    return tok
                return force_fqn[tok]
            # ① import 遮蔽 (JLS 6.4.1): 本文件 import 过的类型优先于同包同名类
            if tok in imported:
                fqn = imported[tok]
                obf = fqn.rsplit('.', 1)[-1]
                obf_pkg = fqn.rsplit('.', 1)[0]
                if pkg02 and obf_pkg != pkg02 and obf in pkg_classes:
                    return fqn
                return obf
            # ② 同包可读全限定名精确命中 (2026-09-25): 简单名在本文件声明的包内解析 ——
            #    Java 同包类优先语义。修掉「extends EffectConfig → 别的包的同类名类」这类
            #    跨包同名错位 (详见 _ref_maps 文档串)。
            if pkg_src:
                hit = REF_INDEX.get(pkg_src + '.' + tok)
                if hit is not None:
                    if _is_decl_name(m2.string, m2.start()):
                        return tok
                    fqn = hit[0] + '.' + hit[1]
                    obf = hit[1]
                    if pkg02 and hit[0] != pkg02 and obf in pkg_classes:
                        return fqn
                    # ★ 2026-09-29 根因修复（JLS 6.4.1：单类型导入**遮蔽**同包类型）。
                    # 若要输出的裸混淆名 `obf` 已被本文件某条 import 绑定到**另一个**类型，
                    # 裸名会绑定到「被导入者」而非同包类 → 必须改用全限定名。
                    # 实例：UnitType.java `extends AbstractUnitBase`（同包，正确目标是
                    # `com...units.c`），但该文件另有 `import com...units.d.c;`
                    # （另一条 import 合法映射而来）→ 裸 `c` 被绑到 `units.d.c`
                    # → `y → d.c → d.i → d.d → y` **继承环**
                    # → `cyclic inheritance involving y` → 类声明崩溃 → 185 错
                    # → 347 条下游 `cannot find symbol: class y`（目标域最大单一错误族）。
                    if _imp_obf.get(obf) not in (None, fqn):
                        return fqn
                    return obf
            obf = name_to_obf.get(tok)
            if obf is None:
                return tok
            # ★ 声明名保护：此处 `tok` 是**字段名/方法名**（前面紧跟一个非关键字标识符，
            # 如 `public float ai;` 的 `ai`），不是类型引用 → 原样保留。
            # 不保护时下方「同包撞名 → 全限定」会把它改成 FQN
            # → `';' expected` / `variable com is already defined`。
            if _is_decl_name(m2.string, m2.start()):
                return tok
            if pkg02:
                fqn = name_to_fqn.get(tok)
                obf_pkg = fqn.rsplit('.', 1)[0] if fqn else pkg02
                if obf_pkg != pkg02 and obf in pkg_classes:
                    return fqn  # 同包撞名 → 全限定 (成员访问形态不匹配本正则, 安全)
                # ★ 2026-09-29 同上：防 import 遮蔽（见②分支文档串）
                if fqn and _imp_obf.get(obf) not in (None, fqn):
                    return fqn
            return obf
        # 2026-09-24 修复（字面量误伤）：裸名替换器**不得进入字符串/字符字面量**。
        # 实测缺陷: 03 `super("GameThread" + a)` → reverse-src `super("z" + a)` → 产物线程名变 "z"；
        # `"GameMode("` → `"o("`（`"ANRWatchdog"` 同族）。字面量是**数据**，不是标识符引用；
        # 需要按类名改写的只有「FQN 形态」字面量（反射/序列化），那类由 site 2 的 `X.Y` 访问替换器处理。
        parts = LITERAL_SPLIT.split(line)
        for i in range(0, len(parts), 2):                     # 偶数位=代码段，奇数位=字面量（原样保留）
            parts[i] = re.sub(r'(?<![\w.])([A-Za-z_$][\w$]*)(?![\w$])', bare_repl, parts[i])
        out_lines.append(''.join(parts))
    src = '\n'.join(out_lines)
    # 3. 桥方法补丁 (PlayerState 子类 compareTo)
    if ('import com.corrodinggames.rts.game.n;' in src and 'extends n' in src
            and 'abstract class' not in src):
        bridge = '''
    @Override
    public int compareTo(Object object) {
        return this.a((com.corrodinggames.rts.game.n) object);
    }
'''
        idx = src.rstrip().rfind('}')
        if idx > 0:
            src = src[:idx] + bridge + src[idx:]
    # ── 产出守卫 (2026-09-29)：声明名位置不得出现 FQN ─────────────────────
    # 形态：`public boolean com.x.y.p;` / `public void com.x.y.b() {`
    # 成因：撞名裁决（force_fqn）或引用解析把**成员名**误判为类名，
    #       写成「可读FQN + 原名」；Java 不允许限定名作变量/方法名
    #       → `';' expected` / `<identifier> expected`（实测 NetEngine L116/L630/L5386）。
    # 判据：行首「修饰符+ 类型 含点的名字 [=;(]」——合法的「FQN 类型 + 简单名」不匹配。
    # 实测：全量 reverse-src 命中 13 处 / 5 文件（守卫后成员名还原为末段）。
    for _gp in _GUARD_PATS:
        src = _gp.sub(lambda mm: mm.group(1) + mm.group(2).rsplit('.', 1)[-1] + mm.group(3), src)
    return src


def collect_method_decls(src):
    """收集类内方法声明签名: {(名, 参数个数)} — 用于反向撞车检测。

    字节码允许同名同参不同返回类型并存 (R8 产物), 但 javac 源码禁止;
    残留混淆名方法与语义名方法反向撞车时, 该语义名必须保持 (全局跳过)。
    """
    sigs = set()
    # 注意: 类型组字符类不含空格 (含空格会与 \s+ 形成 (A+)+ 灾难性回溯)
    for m in re.finditer(
            r'(?m)^\s*(?:(?:public|protected|private)\s+)?'
            r'(?:(?:static|final|synchronized|strictfp|native|abstract|transient)\s+)*'
            r'(?:[\w<>\[\].,?]+\s+)+([\w$]+)\s*\(([^)]*)\)', src):
        params = m.group(2)
        n = 0 if params.strip() == '' else params.count(',') + 1
        sigs.add((m.group(1), n))
    return sigs


def _line_brace_depths(src):
    """每行**行首**的花括号深度（忽略字符串/字符/注释里的括号）。

    ★ 2026-09-30 第4轮：`decl_re` 与 `collect_field_decls` 都用 `^\\s*` ⇒ **匹配任意缩进**，
      于是**方法内的局部声明**（`        Object object;`）也被当成「字段声明」，
      被字段映射改成混淆名（`object` → `b`），而**使用处仍是 `object`** ⇒
      javac 报 cannot find symbol。实测这是本轮最大的单一根因：
      缺失符号 Top 里 `object`(1,525) / `string3`(964) / `string4`(846) / `am2`(807)
      **全是 CFR 的局部变量名**；宿主 `variable k2 of type l` 一个就占 3,298 条。
      对照证据：`03-deobfuscated/.../GameLauncher.java` L157 是 `Object object;` 且
      L174/L177/L183 全用 `object`（**自洽**）；而 `reverse-src` 同名文件 L149 变成
      `Object b;`、L166+L169+L175 仍用 `object`（**不自洽**）⇒ 是反向管线改坏的。

    判据：字段声明位于**类体**（深度 = 类声明所在深度 + 1），
          方法内局部声明位于**更深**的块里。
    """
    depths = []
    depth = 0
    i, n = 0, len(src)
    in_s = None          # 当前字符串/字符定界符
    while i < n:
        c = src[i]
        if c == '\n':
            depths.append(depth)
            i += 1
            continue
        if i == 0:
            depths.append(depth)
        if in_s:
            if c == '\\':
                i += 2
                continue
            if c == in_s:
                in_s = None
            i += 1
            continue
        if c in '"\'':
            in_s = c
            i += 1
            continue
        if c == '/' and i + 1 < n and src[i + 1] == '/':
            j = src.find('\n', i)
            i = n if j < 0 else j
            continue
        if c == '/' and i + 1 < n and src[i + 1] == '*':
            j = src.find('*/', i + 2)
            i = n if j < 0 else j + 2
            continue
        if c == '{':
            depth += 1
        elif c == '}':
            depth -= 1
        i += 1
    if not depths:
        depths.append(0)
    return depths


def _method_body_spans(src):
    """方法体 `{…}` 的字符区间列表（起点, 终点）。

    用途：`decl_re` 会把**方法内的局部声明**也当成「字段声明」——
          因为它用 `^\\s*` 匹配任意缩进（见 `_line_brace_depths` 的说明）。
          有了方法体区间，就能判定「该声明是否在方法体内」，从而只对**类体**的声明反向。

    识别：`{` 且其**同一行**之前的文本形如方法/构造器签名收尾 —— 即匹配
          `\\w+\\s*\\([^)]*\\)\\s*(throws\\s+[\\w.,\\s]+)?$`。
          ★ 2026-09-30 第5轮修：原判据「前一个实义字符是 `)`」**漏掉带 `throws` 的签名**
            （`… String string) throws bo {` —— `{` 前是 `o`，不是 `)`）⇒ 整个方法体不在
            区间内 ⇒ 其中的局部声明仍被字段映射误改。实测
            `game/units/custom/bh.java`：03 的 `String string4 = string;` 被改成
            `String u = string;`，而使用处仍是 `string4` ⇒ 单文件 805 条 cannot find symbol。
          `if (…) {` / `for (…) {` / `while (…) {` 也匹配该式，但它们**不是**字段所在层级，
          把它们算进来只会**更保守**（更少改名），与该修复的方向一致。
    """
    spans = []
    i, n = 0, len(src)
    in_s = None
    stack = []
    _sig = re.compile(r'\w+\s*\([^)]*\)\s*(?:throws\s+[\w.,\s]+)?$')
    while i < n:
        c = src[i]
        if in_s:
            if c == '\\':
                i += 2
                continue
            if c == in_s:
                in_s = None
            i += 1
            continue
        if c in '"\'':
            in_s = c
            i += 1
            continue
        if c == '/' and i + 1 < n and src[i + 1] == '/':
            j = src.find('\n', i)
            i = n if j < 0 else j
            continue
        if c == '/' and i + 1 < n and src[i + 1] == '*':
            j = src.find('*/', i + 2)
            i = n if j < 0 else j + 2
            continue
        if c == '{':
            # 本行从行首到 `{` 的文本
            ls = src.rfind('\n', 0, i) + 1
            head = src[ls:i]
            stack.append(i if _sig.search(head) else None)
        elif c == '}':
            if stack:
                st = stack.pop()
                if st is not None:
                    spans.append((st, i))
        i += 1
    return spans


def collect_field_decls(src):
    """收集类内字段声明名集合 — 用于字段反向撞车检测。

    2026-09-25 修复（枚举常量未反向的根因）：CFR 把「带常量体的枚举」反成
    `public abstract class X { public static final /* enum */ X a = new X$1(); }` ——
    块注释卡在修饰符与类型之间，旧正则 `(?:[\\w<>\\[\\].,?]+\\s+)+` 在此处失配 →
    72 个枚举常量**一个都没被识别/反向**（实测 `gameFramework/m/p` 的
    `effectTypeP`/`effectTypeW` 未改成 `p`/`w` → 原版 `m.o` getstatic 时 2 处
    NoSuchFieldError）。故允许「声明前的单行块注释」。
    """
    names = set()
    for m in re.finditer(
            r'(?m)^\s*(?:(?:public|protected|private)\s+)?'
            r'(?:(?:static|final|transient|volatile|synchronized)\s+)*'
            r'(?:/\*.*?\*/\s*)?'
            r'(?:[\w<>\[\].,?]+\s+)+([\w$]+)\s*(?:=|;)', src):
        names.add(m.group(1))
    return names


def reverse_members(src, mmap, fmap, skip=None, per_map=None, host_key=None, host_map=None,
                    gt_members=None, gt_names=None, stats=None):
    """方法/字段名反向 — 仅成员访问形态, 不碰裸 token (lambda 参数/局部变量/类型名)。

    安全规则:
      1. 字段声明: ^修饰符 类型 semName (;|=)  — 反向 (仅用字段映射)
      2. 方法声明: ^修饰符 类型 semName(       — 反向 (仅用方法映射, 字段映射不得误伤
         同名方法 — B4 实证: callback 字段映射改坏原生回调方法)
      3. 成员访问: .semName (this.x / obj.x / Class.x)  — 反向 (合并映射)
    跳过 import/package 行; skip = 全局撞车剔除集 (声明+访问均保持语义名);
    per_map/host_key = 宿主感知映射 (冲突名按宿主类精确反向, 覆盖全局过滤)。

    D-4 修复 (声明侧宿主约束): 传入 host_map=(hosts_m, hosts_f) 时, **声明侧**
    (decl_re 字段/方法声明 + 枚举常量名) 只应用「宿主证据包含本文件宿主」的映射:
    若某语义名 sem 在 supplement 中明确归属若干宿主类, 而本文件宿主不在其中,
    则该映射对本文件不成立 → 声明保持语义名 (避免把无关类的成员改名)。
    访问侧 (name_to_obf) 仍按全局映射反向 —— 与旧行为一致, 保证跨文件引用一致。

    H-8 声明侧产物名守卫 (新增, P1 缺陷族的产物层根治):
    传入 gt_members=(本文件目标类的原版字节码成员名集合) 时, **任何声明侧改名都必须
    让产物名落在该集合内**, 否则该改名会让成品 jar 出现原版不存在的成员名
    (运行期 NoSuchFieldError/NoSuchMethodError)。同时补充 D-4 的**条件宿主证据**:
    仅当语义名本身就是原版真名时, 才用宿主证据拦下「归属别的宿主」的改名
    —— 这样既治 P1 (rows→h: 真值含 rows, 故拦), 又不会像「无条件宿主约束」那样
    把 194 处「语义名不在真值」的合法改名一起拦掉而破坏编译 (实测量化结论)。
    """
    combined = dict(fmap)
    combined.update(mmap)
    mmap_eff = dict(mmap)
    fmap_eff = dict(fmap)
    # D-4: 声明侧宿主约束 (在 per_map 宿主精确映射之前应用; 先于 skip 剔除)
    #
    # 安全护栏 (2026-09-21 实测): 03 树普遍存在「语义名声明 + 混淆名引用」并存的
    # 字段分裂缺陷 (如 Root$TableData.java 声明 rows 但代码用 .h), 此时声明侧
    # 反向 (rows→h) 是**自修复**动作, 一旦按宿主证据抑制反而让文件无法编译
    # (cannot find symbol)。故仅当该混淆名**未在本文件内以引用形态出现**时才抑制。
    # H-8 编译安全评估 (实测结论, 勿轻易改动):
    #   仅当「语义名 sem 本身就是原版字节码真名」时, 改名才可能把**正确的名字改坏**
    #   → 只有这种情况需要拦; 其余情况 (sem 不在真值里) 一律放行 —— 额外阻拦会制造
    #   编译错误 (实证: 试过「遮蔽同文件类型名即拦」「宿主证据即拦」两种启发式, 分别
    #   造成 1 处 / 194 处编译风险, 故均不采用)。
    #
    # H-8 编译安全口径 (实测取舍, 勿轻易加码):
    #   只保留**零编译代价**的守卫 —— 任何「额外拦截」都被实测证明会破坏
    #   声明/访问一致性或启用危险改名:
    #     · 保留: 产物名必须在目标类原版字节码中 (decl_repl 的 GT 守卫) → 治 P1。
    #     · 保留: 宿主键别名 (可读名 → 混淆名) → 治 ResourceRate。
    #     · 否决: 「宿主证据即拦」(194 处编译风险); 「遮蔽同文件类型名即拦」
    #       (破坏 OpenALMusic 的 this.a / private int sampleRate 一致性);
    #       「自修复护栏收紧」同理。
    #   下方保持**改动前原样**, 不做额外拦截。
    if host_map and host_key:
        hm, hf = host_map
        for eff, hs_of in ((mmap_eff, hm), (fmap_eff, hf)):
            for sem in list(eff):
                hs = hs_of.get(sem)
                if not hs or host_key in hs:
                    continue
                obf = eff[sem]
                # A40（2026-09-30）：护栏只在**混淆名长度 ≥ 3** 时生效。
                # 判据原为「该混淆名以裸字面在本文件出现过」——但**单字母混淆名**
                # （a/b/c/d/e…）在任何 Java 文件里几乎必然出现（局部变量/方法名/别的字段）
                # ⇒ 护栏对短名**恒真** ⇒ 宿主约束在最需要它的场合（短名跨类撞车）完全失效。
                # 实例：supplement 的 `appFramework.h.b → 语义名 g`（全局 g→b）使
                # `AbstractCutsceneAction.this.g = 0.0f`（父类混淆名 g）被改成 `this.b`
                # ⇒ incompatible types: float cannot be converted to a。
                # 短名的「裸出现」不构成有效证据，故对 len(obf) < 3 不再受护栏保护。
                if len(obf) >= 3 and re.search(
                        r'(?<![\w$])' + re.escape(obf) + r'(?![\w$])', src):
                    continue  # 本文件存在混淆名引用 → 保留该反向 (自修复, 破坏即编译失败)
                eff.pop(sem, None)
    if per_map and host_key:
        pm, pf = per_map
        for sem, obf in pm.get(host_key, {}).items():
            mmap_eff[sem] = obf  # 宿主精确方法映射优先
        for sem, obf in pf.get(host_key, {}).items():
            fmap_eff[sem] = obf
        for sem, obf in list(pm.get(host_key, {}).items()) + list(pf.get(host_key, {}).items()):
            combined[sem] = obf
    if skip:
        for s in skip:
            # B5 修复: 宿主精确映射优先于全局撞车剔除 — skip_global 是跨宿主全局剔除,
            # 但该宿主 (per_map) 的精确映射应保留 (如 q 枚举 ally→b 被其他类互撞连坐剔除,
            # 导致声明侧不反向 → 运行时 NoSuchFieldError)
            if per_map and host_key and s in pm.get(host_key, {}):
                continue
            if per_map and host_key and s in pf.get(host_key, {}):
                continue
            combined.pop(s, None)
            mmap_eff.pop(s, None)
            fmap_eff.pop(s, None)
    # 原生绑定方法豁免 (声明+访问均保持官方名)
    for n in NATIVE_BIND_METHODS:
        combined.pop(n, None)
        mmap_eff.pop(n, None)
        fmap_eff.pop(n, None)
    # B5: 官方接口方法豁免 (声明+访问保持 — 接口实现不匹配修复, 如 Wav$Music)
    for n in OFFICIAL_IFACE_METHODS:
        combined.pop(n, None)
        mmap_eff.pop(n, None)
        fmap_eff.pop(n, None)
    # 局部撞车: 反向目标 obf 已被类内残留同名 (字段/方法) 占用 → 该 sem 本文件保持
    # (03 常见缺陷: 语义字段与残留混淆字段同源并存, 如 TextureCache 的 c+isLoaded)
    if per_map and host_key and combined:
        mdecls = collect_method_decls(src)
        fdecls = collect_field_decls(src)
        for sem, obf in list(combined.items()):
            sem_ns = {n for (nm, n) in mdecls if nm == sem}
            obf_ns = {n for (nm, n) in mdecls if nm == obf}
            if sem_ns & obf_ns:
                combined.pop(sem, None)
                mmap_eff.pop(sem, None)
                fmap_eff.pop(sem, None)
                continue
            if sem in fdecls and obf in fdecls:
                combined.pop(sem, None)
                mmap_eff.pop(sem, None)
                fmap_eff.pop(sem, None)
    # 仅保留长度 >= 2 的语义名 (单字符不反向 — 03 单字符名即混淆残留)
    name_to_obf = {s: o for s, o in combined.items() if len(s) >= 2}
    # H-8 一致性: 声明侧若因「产物名不在真值且语义名本就是真值」被守卫拦下,
    # 访问侧必须同样拦下 —— 否则会出现「声明 rows + 引用 this.h」的自相矛盾源码
    # (javac 直接报 cannot find symbol)。守卫判据与 decl_repl 完全一致。
    if gt_names is not None:
        blocked = set()
        for sem, obf in name_to_obf.items():
            if obf != sem and obf not in gt_names and sem in gt_names:
                blocked.add(sem)
        if blocked:
            for sem in blocked:
                name_to_obf.pop(sem, None)
            if stats is not None:
                stats['blocked_access'] = stats.get('blocked_access', 0) + len(blocked)
    if not name_to_obf:
        return src

    # H-8 修复 (最小化): 原正则强制要求可见性修饰符, 使**私有/包级**成员中写法为
    #   `private RectF positionX;` 的能匹配, 但 `RectF positionX;` (包级私有) 匹配不到。
    # 实测取舍: 全面放宽 (修饰符整体可选) 会额外启用 78 个文件的包级私有改名, 其中
    #   `appFramework/s.java` 的 `boolean1→c` 遮蔽同包类 `c` → javac "boolean cannot
    #   be dereferenced" (1 处编译失败)。故只做**必要的最小放宽**: 仅把 `private`
    #   纳入可选修饰符 (覆盖 TextDrawEntry 的 `private RectF positionX`),
    #   包级私有仍保持原语义 (不动 78 个文件里引发问题的那些)。
    # H-8 修饰符处理 (实测取舍):
    #   原严格式要求可见性修饰符 → **包级私有成员完全匹配不到** (实测
    #   `RectF positionX;` 永不改名 → 产物字段名 positionX ≠ 原版 b → NoSuchFieldError)。
    #   全面放宽 (修饰符整体可选) 又会额外启用 78 个文件的包级私有改名, 其中
    #   `appFramework/s.java` 的 `boolean1→c` 遮蔽同包类 `c` → "boolean cannot be
    #   dereferenced"; 该残名是 CFR 的**消歧后缀** (`boolean1/2`), 真值里根本不存在。
    #   折中 (默认): 修饰符段可选; 但**无修饰符**时只接受「非消歧残名」的成员
    #   (残名 = ^[A-Za-z]+[0-9]+$), 从源头挡掉 boolean1/string2 一类误改名。
    if os.environ.get('RW_H8_MOD_STRICT'):
        _mod = r'(?:public|protected|private)\s+'
    else:
        _mod = r'(?:(?:public|protected|private)\s+)?'
    _nomod_guard = not os.environ.get('RW_H8_MOD_STRICT')
    _disambig_re = re.compile(r'^[A-Za-z_$]+[0-9]+$')
    # 「接收者位置」标识符 (X.y 形态) —— 用于判断产物名是否与文件里的**类型名**撞名
    _receiver_re = re.compile(r'(?<![\w$.])([A-Za-z_$][\w$]*)\s*\.\s*[A-Za-z_$]')
    _receiver_names = set(_receiver_re.findall(src))
    decl_re = re.compile(
        r'^(\s*' + _mod + r'(?:static\s+)?(?:final\s+)?'
        r'(?:synchronized\s+)?(?:strictfp\s+)?(?:transient\s+)?(?:volatile\s+)?'
        # 2026-09-25: 允许「声明前的单行块注释」—— CFR 把带常量体的枚举反成
        # `public static final /* enum */ X a = new X$1();`，注释卡在修饰符与类型之间，
        # 旧正则失配 → 72 个常量名未被反向（m.p 的 effectTypeP/effectTypeW 未成 p/w）。
        r'(?:/\*.*?\*/\s*)?'
        r'(?:[\w<>\[\].,?]+\s+)+)'
        r'([A-Za-z_$][\w$]*)(\s*[=;(])', re.M)
    renamed_decl = []          # 本次声明侧实际改名的 (语义名 → 产物名)，供扁平枚举裸引用同步用
    # ★ 2026-09-30 第4轮：方法体区间（按起点排序），供 `decl_repl` 排除方法内局部声明。
    _mbody_spans = sorted(_method_body_spans(src))
    # 宿主精确映射（本类专属）—— 供「JDK 同名方法豁免」判定「本类是否真有该映射」。
    host_precise = {}
    if per_map and host_key:
        _pm_, _pf_ = per_map
        host_precise = dict(_pm_.get(host_key, {}))
        host_precise.update(_pf_.get(host_key, {}))

    def _jdk_exempt(name):
        """JDK 同名方法是否豁免声明侧/访问侧反向（2026-09-25 精修）。

        原实现 `if name in JDK_METHOD_NAMES: return` 是**无条件**豁免（168 个常见 JDK
        方法名，含 `reset`/`close`/`clear`/`length`…）→ 「语义名碰巧与 JDK 方法同名」的
        成员永不反向。实测后果（线上崩溃）：`game/units/f/f` 的两个方法本应反向成原版名
        `a`，却被豁免留在 `reset` → 原版保留类 `game/units/f/c` 按 `a(utility.u)` 调用 →
        `NoSuchMethodError: game.units.f.f.a(gameFramework.utility.u)` 崩游戏。

        精修只加一条**有证据的例外**：supplement 里存在**本宿主**的精确映射（该名在本类
        确为「语义名 → 混淆名」的可反向成员）时不再豁免。无精确映射者保持原豁免 ——
        零推测性扩散（避免 168 个名字在所有文件上全面放开造成大面积改动）。
        """
        if name not in JDK_METHOD_NAMES:
            return False
        if host_precise and name in host_precise:
            return False
        return True

    def decl_repl(m):
        name = m.group(2)
        sep = m.group(3)
        # ★★ 2026-09-30 第4轮（根治本轮最大单一根因）：**方法体内的局部声明不得反向**。
        #   `decl_re` 用 `^(\s*…)` 匹配任意缩进 ⇒ 方法内的 `        Object object;`
        #   也被当成「字段声明」，被字段映射改成混淆名（`object` → `b`），
        #   而**同一方法的其它使用处仍是 `object`** ⇒ javac 报 cannot find symbol。
        #   实测规模：缺失符号 Top 里 `object`(1,525)/`string3`(964)/`string4`(846)/
        #   `am2`(807)/`object2`(253) **全是 CFR 的局部变量名**；宿主
        #   `variable k2 of type l` 一个就占 3,298 条 —— 合计占 cannot find symbol 的大头。
        #   对照证据：03 的 `GameLauncher.java` L157 `Object object;` 与使用处自洽，
        #   而 reverse-src 变成 `Object b;` + 使用 `object` ⇒ 是反向管线改坏的。
        #   判据用 `_method_body_spans`：声明落在任何方法体区间内 ⇒ 原样返回。
        _pos = m.start(2)
        for _s, _e in _mbody_spans:
            if _s < _pos < _e:
                return m.group(0)
            if _s > _pos:
                break
        # ⚠️ 2026-09-29 第十七轮**已回滚**的实验（勿重做）：
        # 曾在此处加「单字符声明名一律不反向」（理由是 L1427-1428 的既定口径）。
        # 它确实修好了 `…game/units/a`（UnitFlag）的枚举常量撞名，**A 1135→1142（+7）**，
        # 但 **V5 真窗口冒烟 FAIL「uncaughtException start」、V6 BLOCKED**。
        # 根因：**源里的单字符名并不总是该成员的 stock 真名** —— 拦住改名后，
        # 新编过的类**成员名与 stock 不符**，而原版保留类仍按 stock 名调用 ⇒ 运行期崩。
        # 这是「声明侧与引用侧/真值不同步」的第三次独立实证（R13 事后改枚举常量名、
        # R14 扩大 gt_closure 覆盖、R17 本处）。**A 增量不是充分判据，必须过 V5。**
        if _jdk_exempt(name):
            # B5: JDK 方法声明豁免 (toString 等 — 枚举 toString 覆盖被改名后失去覆盖,
            # 运行时枚举解析报 Unknown value)。
            return m.group(0)
        if sep.lstrip().startswith('('):
            obf = mmap_eff.get(name, name)
        else:
            obf = fmap_eff.get(name, name)
        # H-8 遮蔽护栏 (精确): 语义名是 CFR **消歧残名** (`boolean1`), 而产物名同时
        # 以接收者形态出现在本文件 (`c.a(this, …)` 里的 `c` = 同包类) → 改名后字段 `c`
        # 会遮蔽类 `c` → javac "boolean cannot be dereferenced"。此时保持原名。
        if (_nomod_guard and obf != name and _disambig_re.match(name)
                and obf in _receiver_names):
            if stats is not None:
                stats['blocked_shadow'] = stats.get('blocked_shadow', 0) + 1
            return m.group(0)
        # H-8 声明侧产物名守卫: 改名产物名必须存在于目标类字节码, 且不得把
        # 「本来就是原版真名」的语义名改掉 (P1: rows→h, 真值含 rows)。
        if (obf != name and gt_names is not None
                and (obf not in gt_names) and (name in gt_names)):
            if stats is not None:
                stats['blocked_decl'] = stats.get('blocked_decl', 0) + 1
                stats.setdefault('blocked_list', []).append(f'{name}→{obf}')
            obf = name
        if obf != name:
            renamed_decl.append((name, obf))
        return m.group(1) + obf + sep
    src = decl_re.sub(decl_repl, src)

    # 1a-2. 扁平枚举的**裸引用同步**（2026-09-25）：
    #   CFR 把「带常量体的枚举」反成 `abstract class X { public static final /* enum */ X a
    #   = new X$1(); ... static { au = new X[]{a, b, ..., effectTypeP, ...}; } }`。
    #   修复声明正则（collect_field_decls / decl_re）后，常量**声明**会被反向成原版名
    #   （effectTypeP→p），但 CFR 合成的静态初始化数组按**裸名**引用这些常量，而反向器
    #   按设计「不碰裸 token」→ 若不同步，必然 `cannot find symbol`（整个文件编译失败 →
    #   该族回落原版，收益归零）。
    #   口径收窄到「文件里出现 /* enum */ 常量声明」这一形态 + 语义名长度 ≥3
    #   （单字母名即混淆名本身，不涉及），与既有「不碰裸 token」的安全取向不冲突。
    if '/* enum */' in src and renamed_decl:
        for sem, obf in sorted(set(renamed_decl)):
            if len(sem) >= 3:
                src = re.sub(r'(?<![\w$.])' + re.escape(sem) + r'(?![\w$])', obf, src)

    # 1b. B5 修复: 枚举常量名反向 — 枚举常量在类体"常量区" (首个方法/构造器前),
    #     形态: name, / name; / name(args), / name(args); (decl_re 不匹配该形态;
    #     常量名保持语义名 → 编译产物字段名≠原版 → 运行时 NoSuchFieldError)
    #     仅枚举类 + 常量区处理 (防误伤普通代码的缩进调用/字段)
    m_enum = re.search(r'\benum (\w+)\s*\{', src)
    if m_enum:
        body_start = m_enum.end()
        rest = src[body_start:]
        m_end = re.search(r'\n\s+(?:public|private|protected|static|final|abstract|strictfp)', rest)
        zone_len = m_end.start() if m_end else len(rest)
        zone = rest[:zone_len]
        const_re = re.compile(r'^(\s+)([A-Za-z_$][\w$]*)(\s*\([^)]*\))?(\s*[,;])', re.M)
        def crepl(mm):
            nm = mm.group(2)
            obf = fmap_eff.get(nm, nm)
            # ★ 2026-09-29 第十二轮根因修复：**枚举常量是封闭集合**，其名字必须与 stock 完全一致。
            # 原判据只在「obf 不在真值 ∧ nm 在真值」时拦截。但 `d→f` 这类**改成另一个真名**的
            # 错位映射，其 obf（`f`）本身就是 stock 的成员名 → 原判据放行 → 常量被逐位改名后
            # 与既有常量撞名。实测 `bs`：30 个常量 `a,b,c,d,e,f,g,…` 变成
            # `a,b,c,f,e,i,b,s,i,b,a,l,m,c,a,p,…`（`a/b/c/i/s` 重复）→
            # `variable X is already defined in enum Y` **197 条 / 21 个枚举全废**
            # （`bs`/`af`/`ah`/`weaponTypeEnum`…），并让 43~82 个引用它们的文件级联失败。
            # 新判据（对枚举常量严格于字段/方法声明侧）：
            #   ① 当前名**已是 stock 真名** → 一律不改（枚举集合内改名几乎必错；
            #      且引用侧用的也是真名，保持一致反而是对的）；
            #   ② 目标名**不是 stock 真名** → 不改（避免凭空造出 stock 没有的常量）。
            # 字段/方法声明侧保持原判据 —— 那里确有「声明可读名 + 引用混淆名」的自修复需求。
            # ★ 2026-09-29 第十三轮根因修复：**无真值（`gt_names is None`）时也要拦**。
            # `load_jar_member_closure()` 实测**只覆盖 1118/1698 个类** ——
            # `…game.units.custom.af` / `…custom.az` 等**不在覆盖内** ⇒ `gt_names=None`，
            # 而原判据以 `gt_names is not None` 为前提 ⇒ 整条守卫失效 ⇒ 常量被成员映射
            # 逐位改名撞名（`af` 的 18 个 `a..r` 变成 `a,b,c,f,e,f,b,h,i,b,a,l,m,c,a,p,…`）。
            # 枚举常量在源里**本来就是混淆单字母名**，在无地面真值可比对时改名**只有坏处**
            # ⇒ 保守拦下（不依赖 `gt_names`）。
            # ★ 2026-09-29 第十七轮根因修复：**单字符枚举常量一律不反向**。
            # 本项目 L1427-1428 早已确立口径「**单字符不反向 —— 03 单字符名即混淆残留**」，
            # 但该口径只作用于**访问侧** `name_to_obf`（`len(s) >= 2` 过滤），
            # **未作用于 `fmap_eff`** —— 而本段（枚举常量）恰恰读的是 `fmap_eff`。
            # 实测 `com/corrodinggames/rts/game/units/a`（可读名 `UnitFlag`）：
            # 03 源常量 `a,b,c,d,e,f,g`（7 个互不相同），`fmap_eff['g'] = 'b'` 把末常量
            # 改成 `b` → `variable b is already defined in enum a` → **整类编不过**
            # （该源由 `--exact --force` 定向重生成也修不好，因改名发生在 `reverse_members`）。
            if obf != nm and (gt_names is None or nm in gt_names or obf not in gt_names):
                if stats is not None:
                    stats['blocked_enum'] = stats.get('blocked_enum', 0) + 1
                obf = nm
            return mm.group(1) + obf + (mm.group(3) or '') + mm.group(4)
        zone2 = const_re.sub(crepl, zone)
        src = src[:body_start] + zone2 + src[body_start + zone_len:]

    # 1c. B5 修复: 枚举构造器显式 super(name, ordinal) — javac 枚举 name()=字段名 (4 参扩展
    #     构造), R8 产物 name()=字符串参数 (ini 解析用 name() 匹配语义值); 显式
    #     super(字符串参数, ordinal) 使反向枚举 name() 与 R8 一致 (实测 buildingNoShockwaveOrSmoke)
    m_enum = re.search(r'\benum (\w+)\s*\{', src)
    if m_enum:
        ename = m_enum.group(1)
        ctor_re = re.compile(
            r'(\n\s+private %s\(String (\w+), int (\w+)\)\s*\{\s*\})' % re.escape(ename))
        def ctor_repl(mm):
            return mm.group(1).replace('{}', '{ super(' + mm.group(2) + ', ' + mm.group(3) + '); }')
        src = ctor_re.subn(ctor_repl, src)[0]

    # 2. 成员访问: 点号后语义名 (this.x / obj.x / Class.x; 通用 token + dict)
    #    B5 修复: JDK 类静态调用豁免 (Math.abs 曾被全局映射误伤成 Math.a, 实测);
    #    JDK 方法名豁免 (this.a.matcher() 的 matcher 曾被反向成 a);
    #    声明行跳过 (字段/方法声明行的全限定类型如 units.am 的 .am 曾被成员映射
    #    误伤成 units.c → 字段类型错位 → 运行时 NoSuchFieldError)
    # A49（2026-09-30）：最小名称解析（import 表 + 同包默认）⇒ 短类型名 → (包, 类)
    _per_map_f = (per_map[1] if per_map else {})
    _own_pkg = None
    _pm = re.search(r'(?m)^\s*package\s+([\w.]+)\s*;', src)
    if _pm:
        _own_pkg = _pm.group(1)
    _imp2fq = {}
    for _im in re.finditer(r'(?m)^\s*import\s+((?:[\w$]+\.)*)([\w$]+)\s*;', src):
        _p, _c = _im.group(1).rstrip('.'), _im.group(2)
        if _p:
            _imp2fq[_c] = (_p, _c)
    _KW2 = {'return', 'new', 'if', 'else', 'while', 'for', 'public', 'private',
            'protected', 'static', 'final', 'class', 'void', 'int', 'float',
            'double', 'long', 'boolean', 'char', 'byte', 'short', 'this', 'super',
            'import', 'package', 'extends', 'implements', 'throw', 'case', 'switch',
            'try', 'catch', 'synchronized', 'instanceof', 'assert'}
    _vt2 = {}
    _decl_re = re.compile(
        r'(?:^|[;{}(,\s])([A-Za-z_$][\w$.]*)\s+([A-Za-z_$][\w$]*)\s*(?=[=;,)])')
    for _dm in _decl_re.finditer(src):
        _t, _v = _dm.group(1), _dm.group(2)
        if _t in _KW2 or _v in _KW2:
            continue
        _vt2.setdefault(_v, set()).add(_t)
    var2type = {k: next(iter(v)) for k, v in _vt2.items() if len(v) == 1}

    def _resolve_type(_t):
        if '.' in _t:
            _p, _c = _t.rsplit('.', 1)
            return (_p, _c)
        if _t in _imp2fq:
            return _imp2fq[_t]
        if _own_pkg:
            return (_own_pkg, _t)
        return None
    # ⚠️ 2026-10-01 第 21 轮**失败实验记录**（勿重复）：曾把接收者放宽为「标识符 + 任意下标」
    #   `([A-Za-z_$][\w$]*(?:\s*\[[^\[\]]*\])*)(?<=[\w.\]])\.(...)`，期望修掉
    #   `this.cL[n2].turretAngle` 这类「下标表达式上的成员访问」。
    #   **实测无效且有害**：`turretAngle` 错误 **414 点 / 21 类 一点没变**（重新生成的
    #   `reverse-src/.../units/e/a.java` 仍是 `.turretAngle`），而产出类 **930 → 927（-3）**。
    #   ⇒ 已回退。**真因不在正则**（见下条注释）。
    access_re = re.compile(r'([A-Za-z_$][\w$]*)(?<=[\w.])\.([A-Za-z_$][\w$]*)(?![\w$])')

    # ★★★ 2026-10-01 第 58 轮（PENDING RS-33）：**类型引用守卫**。
    #   缺陷：`access_re` 把全限定类型 `com.…game.units.am` 的接收者当成 `units`、
    #   成员名当成 `am` ⇒ 查 `name_to_obf['am']` 命中某个**成员**映射 ⇒ 改成 `units.c` ✗
    #   （实测最小复现：`public com.corrodinggames.rts.game.units.am a;` → `…units.c a;` ✗）
    #   ⇒ 与「同名成员」无关，纯属把**包链末段 + 类名**误当作「接收者.成员」。
    #   前人已在 L1926-1927 记录同类现象并做过 A30 修复，但那条只覆盖「声明行只改 this./super.」✗，
    #   而本形态的 `.am` 在**类型位置**、接收者 `units` 不在 this/super 之列 ⇒ 漏网 ✓。
    #   判据（保守、零假设）：stock 里若存在某个类的**末两段**恰为 `(pre, name)`
    #   （如 `units` + `am`），则该处是**类型引用**，一律不改名 ✓。
    def _rs_type_pairs():
        global _RS_TYPE_PAIRS
        if _RS_TYPE_PAIRS is None:
            s = set()
            try:
                with zipfile.ZipFile(GAME_LIB) as _z:
                    for _n in _z.namelist():
                        if _n.endswith('.class'):
                            _p = _n[:-len('.class')].split('/')
                            if len(_p) >= 2:
                                s.add((_p[-2], _p[-1]))
            except Exception:
                pass
            _RS_TYPE_PAIRS = s
        return _RS_TYPE_PAIRS

    def _is_type_ref(pre, name):
        return (pre, name) in _rs_type_pairs()

    def access_repl(m):
        pre, name = m.group(1), m.group(2)
        if _is_type_ref(pre, name):
            return m.group(0)
        if pre in JDK_SAFE_CLASSES:
            return m.group(0)
        # 2026-09-25 精修（实测崩溃根因的访问侧）：JDK 名豁免原本**无条件**，于是把
        # 「语义名碰巧与某个 JDK 方法同名」的成员也一并豁免（`reset` 即其一）→ 声明侧改名
        # `reset`→`a`、访问侧仍写 `reset` → javac `cannot find symbol`；而两边都不改时，
        # 原版保留的调用方按原版名 `a` 调用就 `NoSuchMethodError`（实测线上崩溃）。
        # 新判据：只有当**本文件宿主类的原版字节码确实存在该名**（gt_names 命中）时才豁免
        # —— 那是真的 JDK 覆写/保留名。`this`/`super` 接收者必然是本类，判据成立；
        # 其它接收者（可能属于别的类）保持原豁免（保守，宁可不改）。
        if name in JDK_METHOD_NAMES:
            # 2026-09-25 精修（访问侧，与声明侧同判据）：豁免原本无条件 → 声明侧改名
            # `reset`→`a` 而访问侧（`this.reset()`）仍写 `reset` 会直接编译不过。
            # 本宿主有精确映射时不再豁免；`this`/`super` 接收者必然是本类，判据成立；
            # 其它接收者（可能属于别的类）保持原豁免（保守）。
            if _jdk_exempt(name) or pre not in ('this', 'super'):
                return m.group(0)
        # 宿主优先（2026-09-26，本轮新增）：`this.X` / `super.X` 里的 X **必然是本类成员**
        # → 先用宿主精确映射（mmap_eff/fmap_eff，含 supplement 与「02 对齐学出」），
        # 命不中再退全局映射。实测缺陷：`this.profile` 这类「本类可读成员名」因不在
        # 全局映射里而原样保留 → javac `cannot find symbol`（逐文件失败族里 466 处的头号形态）。
        if pre in ('this', 'super'):
            host_obf = mmap_eff.get(name) or fmap_eff.get(name)
            if host_obf:
                return pre + '.' + host_obf
        # A49（2026-09-30）：**最小名称解析**版按接收者类型解析多值名。
        # A47/A47b 失败真因：by_clsname 只按**类名**聚合 ⇒ 跨包混同 ⇒ 「唯一」也可能错包。
        # 本版先解析短类型名为**完全限定名**（import 表 + 同包默认），再查 per_map。
        if name not in name_to_obf:
            _ty = var2type.get(pre)
            if _ty:
                _key = _resolve_type(_ty)
                if _key:
                    _obf = _per_map_f.get(_key, {}).get(name)
                    if _obf:
                        return pre + '.' + _obf
        return pre + '.' + name_to_obf.get(name, name)
    decl_start = re.compile(r'^\s*(?:public|protected|private|static|final|abstract|'
                            r'strictfp|transient|volatile|synchronized|native)')
    # 外部包链遮蔽：`org.apache.http.message.X` 里的包名段绝不能被当成员名改掉
    # （实测：supplement 有「方法 message → c」一行，于是 import 变成
    #  `org.apache.http.c.BasicNameValuePair` → 冲突遍 4 个文件 `package … does not exist`）
    ext_re = re.compile(r'\b(?:org|java|javax|android|sun|net|com\.codedisaster|'
                        r'com\.google|org\.lwjgl|org\.json|com\.badlogic|'
                        r'com\.esotericsoftware|de\.jarn)[\w.$]*')
    # A57：本类**字段**名 → 声明类型（只收带访问修饰符的声明 ⇒ 是字段而非局部变量）
    field2type = {}
    for _fdm in re.finditer(
            r'(?m)^\s*(?:public|protected|private)\s+(?:static\s+)?(?:final\s+)?'
            r'(?:volatile\s+)?(?:transient\s+)?([A-Za-z_$][\w$.]*)\s+'
            r'([A-Za-z_$][\w$]*)\s*(?=[=;])', src):
        field2type[_fdm.group(2)] = _fdm.group(1)
    if field2type:
        _fnames = '|'.join(re.escape(k) for k in sorted(field2type, key=len, reverse=True))
        _field_re = re.compile(r'this\.(' + _fnames + r')\.([A-Za-z_$][\w$]*)')
    else:
        _field_re = re.compile(r'(?!x)x')  # 永不匹配
    _cast2_re = re.compile(
        r'\(\s*\(\s*([A-Za-z_$][\w$.]*)\s*\)\s*[A-Za-z_$][\w$]*\s*\)\.\s*'
        r'([A-Za-z_$][\w$]*)')
    _cast_re = re.compile(
        r'\(\s*([A-Za-z_$][\w$.]*)\s*\)\s+[A-Za-z_$][\w$]*\s*\)\.\s*'
        r'([A-Za-z_$][\w$]*)')
    out_lines2 = []
    for line in src.split('\n'):
        # import 行只含类型引用：成员替换器一律不参与（此前会把包名段/类名误改成成员名）
        if re.match(r'^\s*import\s', line):
            out_lines2.append(line)
            continue
        if decl_start.match(line):
            # A30（2026-09-30）：声明行**不再整行跳过**，而是只对 `this.` / `super.`
            # 接收者的点号访问做成员反向 —— 类型位置不可能以 this/super 开头，
            # 故完全避开 B5 修复要保护的误伤面（`units.am` 之类是类型/包名段）。
            # 修掉的缺陷：`public ad n = this.createEditableBinding("Camera Up");`
            # 因整行跳过而访问侧不改 ⇒ 声明侧已改 `b` ⇒ cannot find symbol。
            def _this_super_sub(mm):
                pre, name = mm.group(1), mm.group(2)
                if name in JDK_METHOD_NAMES and _jdk_exempt(name):
                    return mm.group(0)
                host_obf = mmap_eff.get(name) or fmap_eff.get(name)
                if host_obf:
                    return pre + '.' + host_obf
                return pre + '.' + name_to_obf.get(name, name)
            line = re.sub(r'\b(this|super)\.([A-Za-z_$][\w$]*)', _this_super_sub, line)
        store = []
        store = []

        def _mask(m):
            store.append(m.group(0))
            return '\x00E%d\x00' % (len(store) - 1)

        masked = ext_re.sub(_mask, line)
        # A57（2026-09-30）：**本类字段链** `this.FIELD.member` —— FIELD 的类型在本文件字段
        # 声明里可查。必须在 access_re 之前执行（access_re 会把 this.j 的 j 改名，之后查不到）。
        def _field_repl(fm):
            _f, _m = fm.group(1), fm.group(2)
            _ft = field2type.get(_f)
            if not _ft:
                return fm.group(0)
            _k = _resolve_type(_ft)
            if not _k:
                return fm.group(0)
            _o = _per_map_f.get(_k, {}).get(_m)
            if not _o:
                return fm.group(0)
            return 'this.' + _f + '.' + _o
        masked = _field_re.sub(_field_repl, masked)
        done = access_re.sub(access_repl, masked)
        # A56（2026-09-30）：**强制转换接收者** `((Type) expr).member` —— Type 在源码里可见。
        # 根因：access_re 要求 `.` 前紧邻标识符且消耗接收者 ⇒ `((am) object).player` 永不匹配
        # （`.` 前是 `)`）。实测 game/n.java:L1312。
        # 修法：只改这一**确定形态**，用 A49 的名称解析定位 (包,类)，再查 per_map ⇒ 命中即换。
        # （A55 的教训：不做无类型约束的全局替换，否则过度改名。）
        def _cast_repl(cm):
            _t, _m = cm.group(1), cm.group(2)
            _k = _resolve_type(_t)
            if not _k:
                return cm.group(0)
            _o = _per_map_f.get(_k, {}).get(_m)
            if not _o:
                return cm.group(0)
            return cm.group(0)[:cm.start(2) - cm.start(0)] + _o
        done = _cast_re.sub(_cast_repl, done)
        # A68（2026-09-30）：**双层括号强转** `((T) x).member`。
        # 取证：s1276/f.java `…equalsIgnoreCase(((com…game.b.a)object622_304).teamName)`
        # A56 的正则要求「单个左括号 + 类型」，对「两个左括号」不匹配 ⇒ 该形态从未覆盖。
        done = _cast2_re.sub(_cast_repl, done)
        for i, orig in enumerate(store):
            done = done.replace('\x00E%d\x00' % i, orig)
        out_lines2.append(done)
    src = '\n'.join(out_lines2)
    # ★★ 2026-10-01 第 52 轮：本通道**不能**放在这里 —— 实测 `reverse_members` 对「无需成员改名」
    #   的文件会**提前 return**（本类即如此 ✗）⇒ 放在函数尾部等于**永不执行** ✗。
    #   已改到 `main()` 里紧跟 `restore_enum_strings` 之后调用（顺序守卫见 `restore_enum_strings` 头部的标记检查）。
    return src


def strip_unused_imports(src):
    """删除未使用的 import (反向后简单名冲突根源: 03 全限定用法 + 冗余 import 反向后撞名)。

    保持文件行序: 只过滤 import 行, 其余原样保留。
    """
    lines = src.split('\n')
    body_text = '\n'.join(l for l in lines if not l.lstrip().startswith('import '))
    kept = []
    for line in lines:
        if not line.lstrip().startswith('import '):
            kept.append(line)
            continue
        m = re.search(r'import (?:static )?([\w.$]+);\s*$', line.strip())
        if not m:
            kept.append(line)
            continue
        simple = m.group(1).split('.')[-1]
        if simple == '*':
            kept.append(line)
            continue
        pat = re.compile(r'(?<![\w.])' + re.escape(simple) + r'(?![\w$])')
        used = False
        for bl in lines:
            st = bl.lstrip()
            if st.startswith('import ') or st.startswith('//') or st.startswith('*'):
                continue
            # 行内注释不算使用 (03 注释常含语义名, 如 "GameFlag 错标修正")
            code = bl.split('//', 1)[0]
            # pat 的 lookbehind (?<![\w.]) 已排除全限定 (.Simple) 与标识符紧邻;
            # 匹配位置可为空格/括号/泛型等任何前缀 — 误删曾致 import java.io.PrintStream 丢失
            for m2 in pat.finditer(code):
                used = True
                break
            if used:
                break
        if used:
            kept.append(line)
    # 简单名冲突消解: 两个 import 简单名相同 → 删除先出现的 (03 冗余 import 常在前;
    # 被删者若代码全限定使用不受影响, 若裸用则编译报错由黑名单/后续迭代处理)
    seen_simple = {}
    deduped = []
    for line in kept:
        m = re.search(r'import (?:static )?([\w.$]+);\s*$', line.strip())
        if m:
            simple = m.group(1).split('.')[-1]
            if simple in seen_simple:
                continue
            seen_simple[simple] = line
        deduped.append(line)
    return '\n'.join(deduped)


def load_b2_supplement(jar_set, known_readable):
    """见 B2_SUPPLEMENT 注释：从 b2-03-reverse.csv 取「引用解析补充映射」。

    采信条件（三重保守）：① status == 'jar-ok'；② obf_fqn 确实存在于原版 jar；
    ③ 该可读简单名在 class-discoveries 里**没有**行（有行时以验证过的数据为准）。
    """
    out = {}
    path = ROOT / 'mappings' / 'generated' / 'b2-03-reverse.csv'
    if not path.exists():
        return out
    for r in csv.DictReader(open(path, encoding='utf-8')):
        f = (r.get('file_03') or '').strip()
        obf = (r.get('obf_fqn') or '').strip()
        if not f.endswith('.java') or not obf or r.get('status') != 'jar-ok':
            continue
        if obf.replace('.', '/') not in jar_set:
            continue
        fqn = f[:-len('.java')]
        if fqn.rsplit('/', 1)[-1] in known_readable:
            continue
        out[fqn.replace('/', '.')] = obf
    return out


def jar_class_fqns():
    out = subprocess.run(['jar', 'tf', str(GAME_LIB)], capture_output=True,
                         text=True, encoding='utf-8', errors='replace').stdout
    return {n[:-len('.class')].replace('\\', '/') for n in out.splitlines() if n.endswith('.class')}


def compute_pkg_moves(entries, jar_set, deobf_dir, mapping=None, prev_cls_dir=None, stock_jar=None):
    """逐类包迁移：把「类名 == 混淆名、但 03 包被可读化」的无映射类搬回真混淆包。

    **问题**（PENDING #54/#60）：03 树把一批混淆包名可读化了
    （`java/audio/a`→`java/audio/backend`、`java/c`→`java/input`、`custom/b`→`custom/animation` …），
    而这些类的**名字没变**。`load_mapping()` 按「可读名 → 混淆名」建索引，恒等行被跳过 →
    这类文件查不到映射 → 走「无类映射」分支 → 在**可读包**里新增一个幽灵类，
    原版那个类不被替换（同一个逻辑类在产物里分裂成两个 Java 类型）。

    **做法**：先按「有效映射行的 (pkg03 → pkg02) 投票」学出**包级映射**，再把满足全部条件的
    无映射类整类搬到真混淆包（**类名不变** → FQN 与 jar 同名 → 直接同名覆盖）：

      闸 1   `Q/<类名>.class` 确实存在于原版 jar（搬过去必然替换掉一个真类，不是凭空造类）
      闸 2a  同目录里**留在原包**的文件没有按裸名引用它
      闸 2b  它自己也没有按裸名引用同目录里**留在原包**的其它类

    闸 2 必须**双向**且用**不动点迭代**：搬走一个类可能让另一个类的闸 2 失守。
    这一条正是前三次尝试（地面真值过滤 / 家族级全量改写 / 恒等行映射）把 javac 打成 0 类的根因 ——
    03 的可读包是一个自洽世界（同包裸名互引），**部分迁移**会让裸名解析到 jar 里的原版类
    （混淆成员名）→ `cannot find symbol: variable xxx location: class y`。
    返回 {03 相对路径: 混淆包}。
    """
    votes = {}
    for e in entries:
        if e['tgt'] and not e.get('drop'):
            votes.setdefault(e['pkg03'], {})
            d = votes[e['pkg03']]
            d[e['tgt'][0]] = d.get(e['tgt'][0], 0) + 1
    pkg_learn = {}
    for p3, cnt in votes.items():
        top, n = max(cnt.items(), key=lambda kv: kv[1])
        # 阈值从「≥2 票」放宽到「≥1 票 + 占比 ≥60%」（2026-09-25）：删掉重复幻影行后，
        # 有些家族（animation / conditions / config / java.input / platform.net）只剩 1 个有效票。
        # 安全性由**闸 1**兜底 —— 只有「目标包里确实存在同名类」的文件才会被搬，
        # 学错包时最多是"不搬"，不会搬错。
        if n >= 1 and n / float(sum(cnt.values())) >= 0.6:
            pkg_learn[p3] = top

    dirs = {}
    for e in entries:
        dirs.setdefault(e['rel'].rsplit('/', 1)[0], []).append(e)

    mapping = mapping or {}
    fqn_cache = load_fqn_cache()
    moves = {}
    drops = set()
    drop_fqn = {}
    rejected = []
    all_rels = {e['rel'] for e in entries}
    # 族外引用检查用：全部 03 源文本（1,7xx 个文件，读一次即可）
    all_texts = {}
    for e in entries:
        try:
            all_texts[e['rel']] = (deobf_dir / e['rel'].replace('/', os.sep)).read_text(
                encoding='utf-8', errors='replace')
        except OSError:
            all_texts[e['rel']] = ''
    # 闸 3 的**第二半**（2026-09-25 实测补上）：包冲突不只来自 jar，也来自「本次编译将产出的类」。
    # 实测错误：`package com.corrodinggames.rts.game.units.custom.b clashes with class of same name`
    # —— 那个类 `game.units.custom.b` 并不在 jar 里，而是**别的 03 文件**（`custom/b.java`）
    # 编译出来的。故必须同时检查「本次构建会产生的全部类名」。
    src_fqns = set()
    for e in entries:
        if e.get('drop'):
            continue
        if e['tgt']:
            src_fqns.add(e['tgt'][0] + '.' + e['tgt'][1] + e['suffix2'])
        else:
            src_fqns.add(e['pkg03'] + '.' + e['cls03'])
    for d, es in dirs.items():
        p3 = es[0]['pkg03']
        family = {e['rel'] for e in es}

        def drop_safe(e):
            """该无映射文件能否安全让位（不产出）？→ (bool, 原因)

            判据：① 自身 FQN 已在 jar 里 → 是**同名替换（A 类）**，绝不能让位；
                  ② 类有**有效映射行** → 族外引用会被反向器改写成混淆全限定名 → 安全；
                  ③ 无族外引用 → 安全；
                  ④ 有族外引用 → 需能推出混淆全限定名（上一次构建的产物 class 形状匹配，或指纹缓存）。
            """
            if (e['pkg03'].replace('.', '/') + '/' + e['cls03']) in jar_set:
                return False, '自身 FQN 已在 jar（A 类同名替换）'
            cands = mapping.get(e['cls03'], [])
            if any((p2.replace('.', '/') + '/' + o2 + e['suffix2']) in jar_set for p2, o2 in cands):
                return True, '有有效映射行'
            old_fqn = e['pkg03'] + '.' + e['cls03']
            ext = [rel2 for rel2, txt in all_texts.items()
                   if rel2 not in family and old_fqn in txt]
            if not ext:
                return True, '无族外引用'
            new_fqn = None
            if prev_cls_dir and stock_jar:
                new_fqn = match_stock_fqn(
                    Path(prev_cls_dir) / (e['rel'][:-len('.java')] + '.class'), stock_jar)
            if not new_fqn:
                new_fqn = fqn_cache.get(e['rel'])
            if new_fqn and new_fqn != old_fqn:
                fqn_cache[e['rel']] = new_fqn
                drop_fqn[old_fqn] = new_fqn
                return True, '族外引用改写为 %s' % new_fqn
            return False, '%s 被 %s 引用（且未能推出混淆名）' % (old_fqn, ext[0])

        Q = pkg_learn.get(p3)
        # 回退（2026-09-25）：D-7 让位会清掉某些族的全部映射 → 无票 → 学不出 Q。
        # 此时用**形状匹配**（上一次构建的产物 class ↔ 原版 jar）从族内任一可匹配的无映射类推出 Q。
        # 实测匹配精准（`animation/i`→`custom.b.i`、`opengl/batch/c`→`gameFramework.b.a.c`、`java/platform/a`→`java.a.a`）。
        # 2026-09-25 修正：条件从 `not Q` 放宽到 `not Q or Q == p3` —— 当族内存在
        # 「包列写成可读包名」的幻影行时，票数会投给**自身包**（Q == p3）→ 闸 0 静默跳过，
        # 形状匹配回退根本没机会跑（实测 `gameFramework/opengl/batch` 因此漏掉 7 个文件的让位，
        # 残留最后 1 个幽灵 `opengl/batch/c`）。
        if (not Q or Q == p3) and prev_cls_dir and stock_jar:
            for e in es:
                if e['tgt'] or e.get('drop'):
                    continue
                fqn = match_stock_fqn(
                    Path(prev_cls_dir) / (e['rel'][:-len('.java')] + '.class'), stock_jar)
                if not fqn:
                    # 该文件此前已被让位 → 产物 class 不存在 → 用指纹缓存复用证据
                    # （否则推不出 Q → 整目录被静默跳过 → 文件又被写回、幽灵回弹）
                    fqn = fqn_cache.get(e['rel'])
                if fqn and fqn.rsplit('.', 1)[0] != p3:
                    Q = fqn.rsplit('.', 1)[0]
                    pkg_learn[p3] = Q
                    break
        if not Q or Q == p3:
            # 闸 0：目标包 == 自身包 → 空搬移（无收益），且会触发「删同包 import」等副作用 → 不做
            continue
        # 闸 3（2026-09-25 实测补上）：**目标包不能与同名类冲突**。
        # `-source 8` 下 JLS 禁止「包 X 与类 X 同名」（实测错误：
        # `package com.corrodinggames.rts.a.a clashes with class of same name`）。
        # 缺这道闸时 76 个迁移把 javac 打成 0 类（49 错）。
        #
        # 闸 3b（同日再补）：冲突判定要看**路径的每一级前缀** —— 只要「某一级前缀是 jar 里的类名」
        # 就永久无法编译（javac 8 不允许"类 f"与"包 f"并存；jar 是 R8 产出的，允许）。
        # 编译过滤器里的 `pkg 前缀 ∈ jar_classes → 跳过` 就是同一判据；迁移会**改掉包的层级**，
        # 所以必须在这里先挡住，否则搬进去的文件编译必炸（实测 9 错）。
        clash = None
        qparts = Q.split('.')
        for i in range(1, len(qparts) + 1):
            if '/'.join(qparts[:i]) in jar_set:
                clash = '.'.join(qparts[:i])
                break
        if clash or Q in src_fqns:
            rejected.append((d, d + '/*', '闸3 目标包与同名类冲突 (%s)' % (clash or Q)))
            # 闸 3 挡住 = 该包**永远无法**用 javac 8 编译（JLS 硬约束，见 PENDING #62）。
            # 此时这些无映射类若照旧输出到可读包，就会变成幽灵类（类型身份分裂）。
            # 唯一出路是「**整族不出源**」：不写盘 → 由原版提供 → 幽灵消失、行为=原版。
            # 安全前提：族内可读 FQN 没有被**族外**文件引用（族内引用随族一起消失）。
            family = {e['rel'] for e in es}
            planned = {}
            # **逐文件判定**（2026-09-25 修正）：原先只要族内任一文件无法解析就整族放弃，
            # 但这类族里**没有任何文件会被编译**（映射的落到不可编译包、无映射的被让位），
            # 故族内互引不会产生编译错误 —— 可以只让位「安全的那些」，其余保持现状（仍是幽灵，但不劣化）。
            for e in es:
                if e['tgt'] or e.get('drop'):
                    continue
                # A62（2026-09-30）：**只 drop「原版 jar 确实提供」的类**。
                # 闸3 的「整族不出源」其安全前提是「由**原版**提供」（见上方注释）。
                # 但对「03 解混淆时新建的语义包」（如 gameFramework.rendering，
                # stock jar 里 0 条），原版**根本不提供这些类** ⇒ drop 等于「谁都不提供」
                # ⇒ 148 个类消失 ⇒ 引用它们处报 cannot find symbol。
                # 故：FQN 不在原版 jar 里 ⇒ **不 drop**，让它走 no_map 分支输出到 03 原路径
                # （作为新增类进入产物；成员名保持语义名 ⇒ 与引用一致）。
                _fqn62 = ((e['pkg03'].replace('.', '/') + '/' + e['cls03'])
                          if e['pkg03'] else e['cls03'])
                if _fqn62 not in jar_set:
                    continue
                ok, why = drop_safe(e)
                if ok:
                    planned[e['rel']] = why
                else:
                    rejected.append((d, e['rel'], '让位受阻：' + why))
            if planned:
                for rel in planned:
                    drops.add(rel)
                rejected.append((d, d + '/*', '整族不出源（javac 8 无法编译进该包）%d 个文件' % len(planned)))
            continue
        texts = {}
        for e in es:
            try:
                texts[e['rel']] = (deobf_dir / e['rel'].replace('/', os.sep)).read_text(
                    encoding='utf-8', errors='replace')
            except OSError:
                texts[e['rel']] = ''

        def bare(text, name):
            return re.search(r'(?<![\w$.])' + re.escape(name) + r'(?![\w$])', text) is not None

        cand = {}
        for e in es:
            if e['tgt'] or e.get('drop'):
                continue
            # 恒等类（自身 FQN 已在 jar）**永不搬包**：它的正确输出位置就是当前位置，
            # 搬走反而会覆盖别的类（实例：`custom/aa` 被学到 `gameFramework.b`）。
            if e.get('identity'):
                continue
            # 注意：JAR_CLASS_SET 是**不带 .class 后缀**的斜杠分隔全名（见 jar_class_fqns()）
            if (Q.replace('.', '/') + '/' + e['cls03']) in jar_set:
                # 闸 4（2026-09-25 实测补上）：目标路径不得占用**其它 03 文件**的路径。
                # 缺这道闸时外来文件会覆盖同目录下另一个真类（实测：`utility/ag.java` 被写成
                # `class ag extends Exception {}`，导致 `utility/ae.java` 报 7 处类型错）。
                rel_eff = Q.replace('.', '/') + '/' + e['cls03'] + '.java'
                if rel_eff in all_rels and rel_eff != e['rel']:
                    # 闸 4：目标路径已被**另一个 03 文件**占用 → 本文件是重复生产者。
                    # 2026-09-25 升级：不再只是"拒绝"，而是**让位**（否则它会留在可读包变成幽灵类，
                    # 实测 `gameFramework/opengl/batch/c` 就是这样残留的最后一个幽灵）。
                    rejected.append((d, e['rel'], '闸4 目标路径被其它 03 文件占用 (%s)' % rel_eff))
                    ok, why = drop_safe(e)
                    if ok:
                        drops.add(e['rel'])
                        rejected.append((d, e['rel'], '重复生产者 → 让位（%s）' % why))
                    else:
                        rejected.append((d, e['rel'], '重复生产者 → 让位受阻：' + why))
                    continue
                cand[e['rel']] = e
        if not cand:
            continue
        # 不动点：反复剔除破坏包级自洽的候选
        while True:
            stayers = []
            for e in es:
                if e['rel'] in cand or e.get('drop'):
                    continue
                if e['tgt'] and e['tgt'][0] == Q:
                    continue                      # 有映射但目标同为 Q → 搬完仍在同包，不算留下
                stayers.append(e)
            stay_names = {e['cls03'] for e in stayers}
            drop = []
            for rel, e in cand.items():
                if any(bare(texts[s['rel']], e['cls03']) for s in stayers):
                    drop.append((rel, '留在原包的文件引用它'))
                    continue
                if any(bare(texts[rel], nm) for nm in stay_names):
                    drop.append((rel, '它引用了留在原包的类'))
            if not drop:
                break
            for rel, why in drop:
                del cand[rel]
                rejected.append((d, rel, why))
        for rel in cand:
            moves[rel] = Q
    save_fqn_cache(fqn_cache)
    return moves, pkg_learn, rejected, drops, drop_fqn


def _load_class_parser():
    """惰性加载 `tools/utils/class_link_check.py` 的纯 Python class 解析器（不调 javap）。"""
    import importlib.util
    p = Path(__file__).resolve().parents[1] / 'utils' / 'class_link_check.py'
    spec = importlib.util.spec_from_file_location('_rw_class_parser', str(p))
    mod = importlib.util.module_from_spec(spec)
    spec.loader.exec_module(mod)
    return mod.parse_class


def _cls_shape(info):
    from collections import Counter
    return (info['super'], Counter(d for _, d in info['fields']),
            Counter('%s%s' % (n, d) for n, d in info['methods']))


def _cls_refs(info):
    return {o for o, _n, _d, _k in info['refs'] if o != info['this'] and not o.startswith('[')}


def _jac(a, b):
    ka, kb = set(a), set(b)
    return len(ka & kb) / float(len(ka | kb)) if (ka or kb) else 1.0


_STOCK_SHAPES = None


def stock_shape_index(jar_path):
    """原版 jar 的「形状 + 引用属主」指纹索引（惰性、进程内只算一次）。"""
    global _STOCK_SHAPES
    if _STOCK_SHAPES is None:
        parse_class = _load_class_parser()
        idx = {}
        with zipfile.ZipFile(jar_path) as z:
            for n in z.namelist():
                if not n.endswith('.class'):
                    continue
                try:
                    info = parse_class(z.read(n))
                except Exception:
                    continue
                idx[n[:-6].replace('/', '.')] = (_cls_shape(info), _cls_refs(info))
        _STOCK_SHAPES = idx
    return _STOCK_SHAPES


def match_stock_fqn(prev_class_path, jar_path, min_shape=0.9, min_refs=0.5):
    """用**上一次构建的产物 class** 做「形状 + 引用」双指纹匹配，推出其原版混淆全限定名。

    用途：有些无映射类（多为 `a`/`b`/`c`/`d`/`i` 这类单字母名）**被族外按可读全限定名引用**，
    它们的目标包又被 JLS 硬约束挡住（PENDING #62）→ 直接让位会让引用悬空。
    此时用上一次构建编译出的同名 class 做指纹匹配，得到它**真正对应的原版类**，
    让位时把族外引用改写成该混淆全限定名 → 从 jar 解析 → 让位即安全。
    """
    p = Path(prev_class_path)
    if not p.exists():
        return None
    parse_class = _load_class_parser()
    try:
        info = parse_class(p.read_bytes())
    except Exception:
        return None
    if len(info['fields']) + len(info['methods']) < 3:
        return None
    sh, rf = _cls_shape(info), _cls_refs(info)
    best = None
    for fqn, (sh2, rf2) in stock_shape_index(jar_path).items():
        if sh2[0] != sh[0]:
            continue
        s = (_jac(sh[1], sh2[1]) + _jac(sh[2], sh2[2])) / 2.0
        if s < min_shape:
            continue
        r = _jac(rf, rf2)
        if r < min_refs:
            continue
        score = (s + r) / 2.0
        if best is None or score > best[1]:
            best = (fqn, score)
    return best[0] if best else None


def _fqn_cache_path():
    return BUILD / 'phantom-fqn-map.json'


def load_fqn_cache():
    """加载「03 相对路径 → 原版混淆全限定名」指纹缓存。

    为什么必须缓存（2026-09-25 实测教训）：让位依据是「用**上一次构建的产物 class**做指纹匹配」，
    而一旦让位成功，那个 class 就不再被产出 → 下一轮构建时 `prev_cls_dir` 里没有它
    → 匹配失败 → 因族外引用而无法再次让位 → **幽灵回弹**（实测 `java/platform/a` 就这样回来了）。
    把匹配结果落盘复用，即可让让位决策跨构建稳定。
    """
    try:
        return json.loads(_fqn_cache_path().read_text(encoding='utf-8'))
    except Exception:
        return {}


def save_fqn_cache(cache):
    try:
        _fqn_cache_path().write_text(json.dumps(cache, ensure_ascii=False, indent=2) + '\n',
                                     encoding='utf-8')
    except OSError:
        pass


def jar_has_target(pkg, cls_full):
    """地面真值判据：目标类是否**真的存在于原版 jar**（默认关闭，需 `RW_GROUND_TRUTH_FILTER=1`）。

    **为什么默认关闭**（2026-09-24 实测结论，务必读完再启用）：
    03 树把一批混淆包名「可读化」了（`j`→`network`、`java/audio/a`→`audio/backend`、
    `gameFramework/f`→`ui.panels` …），而 `class-discoveries.csv` 里对应有 153 行把**可读包名**
    写进了混淆包名列（实测这些包在原版 jar 里条目数为 **0**）。这些错行与 03 包**完全相等**，
    于是 D-7 的「包名前缀段数」消歧让它们恰好压过正确行胜出 → 类被改名后输出到可读包里，
    原版那个类不被替换，产物里多出一个幽灵类（PENDING #54）。

    听上去加一道「只选存在于 jar 的候选」就能修好 —— **实测会把编译打成 0 类**。原因：
    同一个可读包里的所有类原本被**整体**改到同一个可读包，互引自洽（引用按裸名解析到同包）；
    一旦只把其中一部分移到真正的混淆包，另一些留在可读包里，裸名引用就会解析到 **jar 里的
    原版类**（混淆成员名）而不是我们的反向类 → `cannot find symbol: variable xxx location: class y`。
    **正确顺序是先做完整的数据修复**（同 03 包内所有类一致映射到同一混淆包），再启用本过滤。
    修复器见 `tools/fixers/fix_obf_package_rows.py`（家族级、含冲突检测）；完整方案与实测数据
    见 docs/PENDING.md #54/#60。
    """
    if not JAR_CLASS_SET:
        return True
    if os.environ.get('RW_GROUND_TRUTH_FILTER') != '1':
        return True       # 默认关闭：需先完成 CSV 数据修复，否则破坏编译自洽
    return (pkg.replace('.', '/') + '/' + cls_full) in JAR_CLASS_SET


def readable_host_alias(mapping):
    """可读类名 → 混淆类名 的**宿主键别名** (H-8)。

    supplement.csv 的宿主类列口径混杂: 多数行写混淆名 (`aa`), 但也有行写**可读名**
    (`ResourceRate`, 见 supplement.csv:1506)。而 per_map 的键取自该列, 反向时用
    `(pkg, 混淆名)` 去查 → 这类行永远命不中, 声明侧改名**静默失效**
    (实测 `ResourceRate.storageCapacity` 未反成 `c`, 产物与原版不一致)。

    本函数用 class-discoveries 的 (可读名 → 混淆名) 关系, 把这些行的键补一份混淆名
    别名 (**只做键别名, 不新增/不删除任何映射记录**)。
    方向单向 (可读名 → 混淆名): 可读名是全局唯一键, 不会引入跨类碰撞。
    """
    alias = {}
    for readable, cands in mapping.items():
        for _pkg, obf in cands:
            alias.setdefault(readable, obf)
    return alias


def apply_host_alias(per_map, alias):
    """把 per_map 中「以可读类名作键」的记录**按语义名合并**进混淆名键。

    合并口径: 目标键上**已有**的语义名条目优先; 别名只补该键缺失的语义名。
    (不能简单跳过「目标键已存在」—— `units.aa` 已有键但缺 `storageCapacity`,
     而该语义名只出现在以可读名 `ResourceRate` 为键的那条记录里。)
    """
    per_m, per_f = per_map
    out_m = {k: dict(v) for k, v in per_m.items()}
    out_f = {k: dict(v) for k, v in per_f.items()}
    added = 0
    for src_map, dst_map in ((per_m, out_m), (per_f, out_f)):
        for (pkg, cls), sem_map in src_map.items():
            obf = alias.get(cls)
            if not obf or obf == cls:
                continue
            key = (pkg, obf)
            target = dst_map.setdefault(key, {})
            for sem, obf_name in sem_map.items():
                if sem in target:
                    continue                  # 已有条目优先 (不覆盖)
                target[sem] = obf_name
                added += 1
    return (out_m, out_f), added


def load_jar_member_closure():
    """原版 jar 的「类 → 成员名闭包(含 super 链)」地面真值索引 (H-8)。

    用途: 声明侧反向守卫 (reverse_members 的 allow_target) —— 只有**确实存在于该
    目标类字节码**的混淆名才允许被生成, 否则保持语义名。这是 P1 缺陷族
    (rows→h 等跨类误改) 的产物层根治手段。

    实现要点:
      - 地面真值 = 原版 game-lib.jar (R8 产物), 解析口径与门禁
        `tools/gates/jar_compare_gate.py` **同源** (parse_javap/javap_batch),
        避免「构建按一套字节码理解、门禁按另一套」的漂移;
      - javap 只对**类映射目标**及其 super 链求值 (实测 887 类, 数秒);
      - 结果按 jar 的 SHA256 缓存到 `.cache/jar-member-closure.json` → 之后零开销。
    """
    import hashlib
    import json
    import zipfile
    from gates.jar_compare_gate import javap_batch, parse_javap

    sha = hashlib.sha256(GAME_LIB.read_bytes()).hexdigest()
    cache_dir = ROOT / '.cache'
    cache_file = cache_dir / 'jar-member-closure.json'
    if cache_file.exists():
        try:
            blob = json.loads(cache_file.read_text(encoding='utf-8'))
            if blob.get('jar_sha256') == sha:
                return {k: set(v) for k, v in blob['closure'].items()}
        except (ValueError, KeyError):
            pass
    # 地面真值统一用**点分** FQN (实测: parse_javap 输出键为点分, javap 的 super 也是点分)
    with zipfile.ZipFile(GAME_LIB) as z:
        known_dot = {n[:-len('.class')].replace('/', '.')
                     for n in z.namelist() if n.endswith('.class')}
    member_cache = {}

    # 目标集 (点分): 类映射目标 + 其所有 `$` 内层变体
    # (脚本按「外层类映射 + 保留 $ 后缀」派生内层类, 如 Root → Root$TableData)
    bases_dot = {p.replace('/', '.') + '.' + o
                 for cands in load_mapping().values() for p, o in cands}
    # ⚠️ 2026-09-29 第十四轮**已回滚**的实验（勿重做）：把目标集扩到**全部 1,698 个 stock 类**
    # （`bases_dot |= known_dot`）看似「纯增益」，实测**产物启动即崩**：
    #   V5 真窗口冒烟 FAIL「uncaughtException start」+ V6 BLOCKED，A 且**未增**（持平 1122）。
    # 根因：该守卫（`allow_target` / 枚举常量 `gt_names`）**只作用于声明侧** ——
    # 拦住声明改名后，**引用侧仍用可读名** ⇒ 声明/引用不一致 ⇒ 运行时
    # NoSuchFieldError/NoSuchMethodError ⇒ 启动崩。
    # **「地面真值覆盖越广越安全」是错的**：扩大覆盖＝扩大声明侧拦截＝扩大声明/引用不一致面。
    # 结论：覆盖 1,118 是**当前实现的正确工作点**；要扩大必须**同时**让引用侧走同一套映射。
    # bases_dot |= known_dot
    targets_dot = {f for f in known_dot
                   if f in bases_dot or f.split('$')[0] in bases_dot}
    if not targets_dot:
        print('警告: 地面真值目标集为空 (映射库或 jar 异常)')
        return {}
    pending = set(targets_dot)
    for _ in range(4):
        if not pending:
            break
        got = javap_batch(GAME_LIB, sorted(pending), find_javap(), known=known_dot)
        for k, v in got.items():
            if v:
                member_cache[k.replace('/', '.')] = v
        sups = {v.get('super') for v in member_cache.values() if v.get('super')}
        pending = {s.replace('/', '.') for s in sups
                   if s.replace('/', '.') in known_dot
                   and s.replace('/', '.') not in member_cache}
    closure = {}
    for fqn in sorted(member_cache):
        chain, seen, cur = set(), set(), fqn
        while cur and cur not in seen and len(seen) < 12:
            seen.add(cur)
            node = member_cache.get(cur)
            if not node:
                break
            chain |= {n for n, _ in node['fields']} | {n for n, _ in node['methods']}
            cur = node.get('super')
        closure[fqn] = sorted(chain)
    try:
        cache_dir.mkdir(parents=True, exist_ok=True)
        cache_file.write_text(json.dumps(
            {'jar_sha256': sha, 'targets': len(closure), 'closure': closure},
            ensure_ascii=False, sort_keys=True), encoding='utf-8')
    except OSError as exc:
        print(f'警告: 地面真值索引写缓存失败 ({exc}) — 本次仍生效')
    return {k: set(v) for k, v in closure.items()}



def main():
    global JAR_CLASS_SET
    dry = '--apply' not in sys.argv
    skip_compile = '--skip-compile' in sys.argv

    mapping = load_mapping()  # sem类名 -> [(02包, 02混淆名)]
    # 防御: 历史脏数据包列含斜杠 (v19.117-PSR 遗留) → 归一化点分
    mapping = {k: [(p.replace('/', '.'), o) for p, o in v]
               for k, v in mapping.items()}
    JAR_CLASS_SET = jar_class_fqns()  # B5: 撞名全限定检查
    B2_SUPPLEMENT.update(load_b2_supplement(JAR_CLASS_SET, set(mapping.keys())))
    if B2_SUPPLEMENT:
        print(f'b2 引用补充映射: {len(B2_SUPPLEMENT)} 个可读类（class-discoveries 缺行）')
    mmap, fmap = load_member_map()
    # H-9 根因修复: 宿主键口径归一 (短写包名 → 全限定包; 可读类名 → 混淆类名),
    # 使 supplement 的宿主列与反向器查找键真正对齐 —— 此前 1,353 行因短写包名
    # (`f`/`m`/`game.units.custom`) 而永远命不中, 映射静默失效。
    resolve_pkg = load_host_pkg_resolve()
    readable_alias = readable_host_alias(mapping)
    per_map = load_member_map_by_class(resolve_pkg, readable_alias)
    per_map, n_alias = apply_host_alias(per_map, readable_alias)
    host_map = load_member_hosts(resolve_pkg, readable_alias)
    print(f'宿主键归一 (H-9): 解析 {len(resolve_pkg)} 个短写包名, 别名补 {n_alias} 个宿主键')
    # D-4 适用范围判据: 类映射目标 FQN 集合 (斜杠制) — 用于识别「未混淆宿主」
    OBF_TARGETS = {p.replace('/', '.').replace('.', '/') + '/' + o
                   for cands in mapping.values() for p, o in cands}
    print(f'方法映射 (无冲突): {len(mmap)} | 字段映射: {len(fmap)} | 宿主映射: {len(per_map[0])} 方法类 + {len(per_map[1])} 字段类')
    print(f'宿主证据 (D-4 声明侧约束): 方法 {len(host_map[0])} 名 / 字段 {len(host_map[1])} 名')
    # H-8: 原版 jar 成员名地面真值 (声明侧产物名守卫的判据; 按 jar SHA 缓存)
    gt_closure = load_jar_member_closure()
    print(f'地面真值索引 (H-8 声明侧守卫): {len(gt_closure)} 个类')
    print(f'宿主键别名 (H-8): 补 {n_alias} 个「可读名 → 混淆名」宿主键')
    guard_stats = {}

    # 00. 全局撞车剔除集: 先扫全部 03 文件, 检测"语义名反向成混淆名后与类内
    #     现有声明同名同参"的冲突 → 该语义名全局保持 (声明+访问均不反向)。
    #     根因: R8 字节码允许 (名+参数+返回类型) 区分方法, javac 源码不允许;
    #     且 03 中残留混淆名方法与语义化方法反向后撞车 (如 isBound→p 撞 private void p())。
    #     按文件预索引剪枝: 只检查文件中实际出现的语义名。
    from collections import defaultdict
    skip_global = set()
    skip_by_rel = defaultdict(set)      # 逐文件撞车剔除集（2026-09-26 缺陷修复，见下）
    usage = defaultdict(list)  # (obf, 参数数) -> [sem...] 第二层互撞
    comb_all = dict(fmap)
    comb_all.update(mmap)
    for java in DEOBFUSCATED_DIR.rglob('*.java'):
        rel = str(java.relative_to(DEOBFUSCATED_DIR)).replace('\\', '/')
        if rel in BLACKLIST:
            continue
        src = java.read_text(encoding='utf-8', errors='ignore')
        mdecls = collect_method_decls(src)
        fdecls = collect_field_decls(src)
        names_in_file = {nm for nm, _ in mdecls} | fdecls
        for sem, obf in comb_all.items():
            if sem not in names_in_file:
                continue
            # 方法撞车: (obf, 参数数) 已被类内同名方法占用
            sem_ns = {n for (nm, n) in mdecls if nm == sem}
            obf_ns = {n for (nm, n) in mdecls if nm == obf}
            if sem_ns & obf_ns:
                skip_global.add(sem)
                skip_by_rel[rel].add(sem)
                continue
            # 字段撞车: obf 字段名已被类内占用
            if sem in fdecls and obf in fdecls:
                skip_global.add(sem)
                skip_by_rel[rel].add(sem)
                continue
            # 第二层收集: 反向后互撞检测 (按文件分组 — 跨文件同名不冲突)
            for n in sem_ns:
                usage[(rel, obf, n)].append(sem)
    for (rel, obf, n), sems in usage.items():
        if len(sems) > 1:
            for s in sems:
                skip_global.add(s)
                skip_by_rel[rel].add(s)
    # 第二层字段: 多个语义字段映射到同一 obf (字段无反参区分) → 互撞剔除
    fusage = defaultdict(list)
    for java in DEOBFUSCATED_DIR.rglob('*.java'):
        rel = str(java.relative_to(DEOBFUSCATED_DIR)).replace('\\', '/')
        if rel in BLACKLIST:
            continue
        src = java.read_text(encoding='utf-8', errors='ignore')
        fdecls = collect_field_decls(src)
        for sem, obf in comb_all.items():
            if sem in skip_global:
                continue
            if sem in fdecls and obf not in fdecls:
                fusage[(rel, obf)].append(sem)
    for (rel, obf), sems in fusage.items():
        if len(sems) > 1:
            for s in sems:
                skip_global.add(s)
                skip_by_rel[rel].add(s)
    if skip_global:
        print(f'撞车剔除 (保持语义名): {len(skip_global)} 个名字出现在 '
              f'{len(skip_by_rel)} 个文件里（**逐文件生效**，不再全局连坐）: '
              f'{sorted(skip_global)[:20]}')

    # 01. 反向重命名全部有类映射的 03 文件
    #
    # D-7 修复: 类身份解析改为「路径 + 包/类声明」双重判据。
    # 旧实现只按 03 类声明名查 class-discoveries.csv (不看目录), 当 03 树存在
    # **跨包同名类**时会把它们一律解析到同一 obf 目标, 产生两类缺陷:
    #   ① 内层类跨包串包: .../gameFramework/rendering/LicenseValidator$2..$72.java
    #      (共 71 个, 均声明 package ...rendering) 被解析成 java/p$2..p$72.java;
    #   ② 反向目标同名竞争: .../gameFramework/utility/UnitRegistry.java 与
    #      .../game/units/UnitRegistry.java 争夺 game.units.ar (=枚举), 后者按
    #      rglob 顺序静默胜出/落败 → 落败方成为**死件** (无反向源、无产物)。
    # 新判据 (两级, 只读既有映射库, 不新增/不删除任何映射行):
    #   ① 同名竞争消歧: 对同一 (obf包, obf类) 目标的多个「直接认领者」, 保留 03 包
    #      与 obf 包最贴近者 (包完全相等 > 最长公共包前缀 > 路径字典序), 其余让位;
    #   ② 内层类归属: 走「外层类回退」的 03 文件 ($N), 其 03 包必须与该目标的顶层
    #      赢家所在包一致 (03 树中内层类与外壳同目录); 否则让位。
    # 让位者不再被静默丢弃, 而是走「无类映射」分支 —— 保留自身包/类身份输出,
    # 消除跨包误配与顺序敏感的死件。
    if REV_SRC.exists():
        shutil.rmtree(REV_SRC)
    REV_SRC.mkdir(parents=True)

    # 01a. 第一遍: 逐文件解析 (不改写, 不写盘)
    entries = []
    for java in DEOBFUSCATED_DIR.rglob('*.java'):
        rel = str(java.relative_to(DEOBFUSCATED_DIR)).replace('\\', '/')
        if rel in BLACKLIST:
            continue
        src = java.read_text(encoding='utf-8', errors='ignore')
        stem = java.stem
        # 类声明提取: 锚定行首 (注释里的 "enum ai" 曾误命中, 致类名提取错误)
        m = re.search(r'^(\s*(?:public |protected |final |abstract |strictfp |static )*(?:class|enum|interface) )([\w$]+)', src, re.M)
        cls03 = m.group(2) if m else stem
        # $N 内部类: 完整名映射优先 (如 MainUIController$TableCell→Root$TableCell),
        # 无则外层映射 + 保留 $ 后缀
        outer03 = cls03.split('$')[0]
        suffix = cls03[len(outer03):]
        pkg03 = java.parent.as_posix().replace('\\', '/').split('03-deobfuscated/')[-1].replace('/', '.')
        direct = mapping.get(cls03, [])
        if direct:
            cands, suffix2 = direct, ''
        elif suffix:
            cands, suffix2 = mapping.get(outer03, []), suffix
        else:
            cands, suffix2 = [], ''
        tgt = None
        # ── 恒等判定（2026-09-26 缺陷修复，本轮最大的单点收益）────────────────
        # 若「03 路径包 + 类声明名」这个 FQN **在原版 jar 里就存在**，那么该 03 文件
        # 的正确反向目标就是**它自己**：这类是 R8 keep 规则保住的反射/注释族与第三方库
        # （实测 476 个：`game.units.custom.logicBooleans` 214 个、`com.codedisaster.steamworks`
        #  121 个、`java.audio.lwjgl`、`appFramework`、`org.a.a.*` …），jar 里既有可读名类、
        # 又有同名的混淆类时，class-discoveries 的**非恒等行**会把它错误地指向混淆类
        # （实例：`LogicBoolean → custom.e.a` 一行，把整族 214 个文件拖进
        #  `PKGLEARN logicBooleans→custom.e` → 闸3 目标包同名类冲突 → **整族不出源**，
        #  214 个 A 类候选全部归零）。
        # 恒等优先还顺带修掉「同一简单名两个 03 文件」的误配（`custom/aa` 与 `opengl/aa`
        # 都叫 aa：前者恒等、后者才是 `gameFramework.b.aa`）。
        fqn03 = (pkg03.replace('.', '/') + '/' + cls03) if pkg03 else cls03
        identity = fqn03 in JAR_CLASS_SET
        if identity:
            cands, suffix2 = [], ''
        if cands:
            # 地面真值过滤（2026-09-24 幽灵类根因修复）：候选里若有「目标确实存在于 jar」的，
            # 只在其中消歧 —— 否则写错包名的映射行会靠「包名精确相等」压过正确行胜出。
            valid = [c for c in cands if jar_has_target(c[0], c[1] + suffix2)]
            use = valid if valid else cands
            if len(use) == 1:
                tgt = use[0]
            else:
                best, bl = None, -1
                for p2, o2 in use:
                    c = sum(1 for a, b in zip(p2.split('.'), pkg03.split('.')) if a == b)
                    if c > bl:
                        bl, best = c, (p2, o2)
                tgt = best
        entries.append(dict(java=java, rel=rel, src=src, cls03=cls03,
                            pkg03=pkg03, tgt=tgt, suffix2=suffix2, identity=identity))

    # 01b. 第二遍: 同名竞争消歧 (D-7 判据 ①) + 内层类归属约束 (D-7 判据 ②)
    #
    # 安全口径 (2026-09-21 实测收窄): 仅当候选中**恰有一个 03 包与 obf 包完全相等**
    # 时才认定其为归属者, 并让其余认领者让位。包前缀「接近度」只能用于排序、不能当
    # 归属证据 —— 实测用接近度让位会破坏大量「03 语义包名 ≠ obf 包名」的合法映射
    # (如 game/ai/AIStrategy.java → game.a.a.a), 造成 136 条编译错误。
    top_claims = defaultdict(list)
    for e in entries:
        if e['tgt'] and not e['suffix2']:
            top_claims[e['tgt']].append(e)
    primary_pkg = {}          # (obf包, obf类) -> 顶层认领者的 03 包
    exact_owner = {}          # (obf包, obf类) -> 精确包匹配的认领者 (唯 1 时才算归属)
    demoted = []              # (rel, 原因, 期望包)
    for key, es in top_claims.items():
        obf_pkg = key[0]
        exact = [e for e in es if e['pkg03'] == obf_pkg]
        primary_pkg[key] = (exact[0]['pkg03'] if exact else es[0]['pkg03'])
        if len(exact) == 1:
            exact_owner[key] = exact[0]
            if len(es) > 1:
                for e in es:
                    if e is not exact[0]:
                        e['tgt'] = None
                        demoted.append((e['rel'], '同目标顶层竞争让位 (精确包匹配归属者)', obf_pkg))
    for e in entries:
        if not e['tgt'] or not e['suffix2']:
            continue
        win = exact_owner.get(e['tgt'])
        if win is None or e['pkg03'] == win['pkg03']:
            continue
        # D-7 安全护栏: 若该内层类文件**引用了自己的外层类名**, 让位后外层名无处
        # 解析 (外层由 03 映射改名到别的包) → 必然编译失败 (实测:
        # LogicBoolean$X 让位后 "cannot find symbol class a")。此类保持既有映射。
        outer_name = e['cls03'].split('$')[0]
        refs = len(re.findall(r'(?<![\w$])' + re.escape(outer_name) + r'(?![\w$])', e['src']))
        if refs > 1:      # 1 = 类声明行自身
            continue
        demoted.append((e['rel'], '内层类归属目录不符', win['pkg03']))
        e['tgt'] = None
    # 让位者处置 (2026-09-21 实测裁定): **不再输出反向源** (等价于「被同名竞争者挤出」的
    # 既有语义), 只把裁决从 rglob 顺序改为确定性判据。理由: 让位者若改走「无类映射」
    # 分支会被当作 03 重建类编译, 而其依赖仍是按旧身份书写的 (实测 UnitRegistry.java
    # 让位后 "constructor v ... cannot be applied"; ResourceLoader.java 让位后
    # "cannot find symbol class GlobalState") → 反向构建必然报错。
    for rel, why, want in demoted:
        for e in entries:
            if e['rel'] == rel:
                e['drop'] = True
                break
    if demoted:
        print(f'类身份路径消歧 (D-7): {len(demoted)} 个 03 文件让位 → 保留自身身份')
        for rel, why, want in demoted[:10]:
            print(f'  - {rel}  ({why}; 期望包 {want})')
        if len(demoted) > 10:
            print(f'  ... 共 {len(demoted)} 条 (完整清单见 build/reverse-src 对照)')

    # 01c. 第三遍: 改写并写盘
    #
    # D-4 适用范围 (证据驱动, 2026-09-21 实测收窄): 声明侧宿主约束**只对「未混淆宿主」
    # 求值** —— 即 03 文件 ① 无类映射 ② 在原始 jar 中同名存在 ③ 其 FQN 不是任何类映射
    # 的目标 (即类名本身未被混淆)。例如 com/corrodinggames/rts/R$layout (R 资源常量类,
    # jar 中字段名就是 credits 而非 o) 与 SettingsEngine / OpenALMusic 等官方命名类。
    # 对真正的混淆类一律不求值 —— 03 树大量「语义名声明 + 混淆名引用」并存, 声明侧
    # 反向是自修复动作, 按宿主证据抑制会直接破坏编译 (实测 136 条错误)。
    def use_host_constraint(tgt, cls03, pkg03):
        """H-9 (根因修复后): 宿主约束对**所有**类生效。

        历史: D-4 时因「03 树普遍存在语义名声明 + 混淆名引用并存」会触发 136 处编译
        错误, 故把适用范围收窄为「未混淆宿主」, 使混淆类 (绝大多数) 完全不求值 ——
        这正是 P1 (`Root$TableData.rows→h` 等跨类误改) 能存活的原因。
        H-9 修好**宿主键数据口径** (短写包名 + 可读类名归一) 后, 实测把约束放开到
        全部类**编译仍 0 错误**, 且守卫拦截数 8→4 (冗余度上升)。故固化为默认行为。
        """
        return True

    # 01b-3. 逐类包迁移（2026-09-25）：把「类名即混淆名、03 包被可读化」的无映射类搬回真混淆包。
    # 详见 compute_pkg_moves 的文档串；闸 2 双向 + 不动点，保证搬完仍包级自洽。
    pkg_moves, pkg_learn, pkg_rejected, pkg_drops, drop_fqn = compute_pkg_moves(
        entries, JAR_CLASS_SET, DEOBFUSCATED_DIR, mapping,
        prev_cls_dir=REV_CLS, stock_jar=GAME_LIB)
    if pkg_learn:
        print(f'包级映射 (从有效映射行学习): {len(pkg_learn)} 条')
    if drop_fqn:
        print(f'让位引用改写: {len(drop_fqn)} 个可读全限定名 → 混淆全限定名')
    if pkg_drops:
        print(f'整族不出源 (JLS 硬约束：javac 8 无法编译进该包): {len(pkg_drops)} 个文件')
        for rel in sorted(pkg_drops)[:10]:
            print(f'  - {rel}')
    if pkg_moves:
        print(f'逐类包迁移: {len(pkg_moves)} 个无映射类搬回真混淆包 '
              f'(被闸挡下 {len(pkg_rejected)})')
        for rel, q in sorted(pkg_moves.items())[:12]:
            print(f'  - {rel} → {q}')
    if pkg_rejected:
        for d, rel, why in pkg_rejected[:8]:
            print(f'  ⛔ {rel} ({why})')
    # 决策日志转储（2026-09-25）：包迁移/让位的**全部**判定落盘，便于事后定位
    # 「某目录为何既没迁移也没让位」（此前只能靠反复构建试错，单次构建约 15 分钟，代价极高）。
    try:
        with open(BUILD / 'pkg-decision-log.txt', 'w', encoding='utf-8') as fh:
            fh.write('# 包级映射（从有效映射行学习）: %d 条\n' % len(pkg_learn))
            for k, v in sorted(pkg_learn.items()):
                fh.write('PKGLEARN %s -> %s\n' % (k, v))
            fh.write('# 逐类包迁移: %d\n' % len(pkg_moves))
            for k, v in sorted(pkg_moves.items()):
                fh.write('MOVE %s -> %s\n' % (k, v))
            fh.write('# 让位: %d\n' % len(pkg_drops))
            for k in sorted(pkg_drops):
                fh.write('DROP %s\n' % k)
            fh.write('# 让位引用改写: %d\n' % len(drop_fqn))
            for k, v in sorted(drop_fqn.items()):
                fh.write('REWRITE %s -> %s\n' % (k, v))
            fh.write('# 拒绝/说明: %d\n' % len(pkg_rejected))
            for d, rel, why in pkg_rejected:
                fh.write('REJECT %s | %s | %s\n' % (d, rel, why))
    except OSError:
        pass

    # 01b-4. 引用消歧索引（2026-09-25）：目标已定稿（含让位/包迁移）后，把
    # 「可读全限定名 → 混淆目标」落成全局索引，供 fast_reverse_source 在引用点精确解析。
    n_ref = build_ref_index(entries, pkg_learn)
    print(f'引用消歧索引: {n_ref} 个可读全限定名 → 混淆目标')

    ok = no_map = conflict = 0
    moved = 0
    written = {}
    moved_orig = {}      # 新相对路径 -> 原 03 相对路径（包迁移用；编译过滤须按**原路径**判定）
    for e in entries:
        java, rel, src, cls03, pkg03, tgt, suffix2 = (
            e['java'], e['rel'], e['src'], e['cls03'], e['pkg03'], e['tgt'], e['suffix2'])
        if e.get('drop') or e['rel'] in pkg_drops:
            conflict += 1   # D-7 确定性让位 / 整族不出源（JLS 硬约束，见 PENDING #62）
            continue
        if not tgt:
            # 第三方包（com.codedisaster.*）**原样出源，不做任何反向**（2026-09-26 实测）：
            # 它们的成员名本来就没被 R8 混淆（`SteamCallbackAdapter.callback` 就是真名），
            # 反向只会引入缺陷 —— 实测 121 个文件走反向会报 33 处
            # 「constructor X in enum … cannot be applied to given types」（枚举常量参数被
            # `complete_enum_ctor_args` 画蛇添足），而 **03 原样编译 = 0 错 / 121 class**。
            # 这 121 个类的 FQN 全部存在于原版 jar → 直接就是 121 个 A 类替换。
            if rel.startswith(NO_MEMBER_REVERSE_PKGS):
                no_map += 1
                out_path = REV_SRC / rel
                out_path.parent.mkdir(parents=True, exist_ok=True)
                out_path.write_text(src, encoding='utf-8')
                continue
            # 无类映射 (03 重建类/官方语义名类): 部分反向 — 类名/包名保持,
            # 仅反向引用 (import/全限定/裸引用/成员), 输出到原 03 路径。
            # 运行时: 重建类为新增类 (jar 无), 官方名类由 jar 原样提供。
            # 注意: 03 类名可能仍是混淆名 (如 utility/ad 类名 ad 未语义化),
            # 宿主映射按 (混淆包, 混淆类名) 匹配 — 用 03 包路径+类名做 host_key。
            no_map += 1
            src2 = strip_unused_imports(src)  # 03 源先清理 (反向后同名 import 无法区分)
            rev = fast_reverse_source(src2, cls03, (None, None), mapping, keep_cls=True)
            host_key = (pkg03, cls03)
            hm_eff = host_map if use_host_constraint(tgt, cls03, pkg03) else None
            if rel.startswith(NO_MEMBER_REVERSE_PKGS):
                # 第三方包：成员名未混淆 → 只保留类名/引用反向（不传任何成员映射）
                # 另：枚举常量参数补齐（显式 (String,int) 构造器的枚举，常量必须全带参）
                rev = complete_enum_ctor_args(rev)
                rev = reverse_members(rev, {}, {}, None, None, host_key, None,
                                      gt_members=gt_closure, gt_names=None,
                                      stats=guard_stats)
            else:
                rev = reverse_members(rev, mmap, fmap, skip_by_rel.get(rel, ()), per_map,
                                      host_key, hm_eff,
                                      gt_members=gt_closure,
                                      # ★ 2026-09-29：`load_jar_member_closure()` 的键是**点分隔**
                                      # （`a.a.a.a` / `…gameFramework.bs`）。原用 `'/'` 拼接
                                      # → `.get()` 恒为 None → 枚举常量守卫失效（同
                                      # `_wire_by_sourcetable.py` 的修复）。
                                      gt_names=gt_closure.get(host_key[0] + '.' + host_key[1]),
                                      stats=guard_stats)
            q = pkg_moves.get(rel)
            rel_eff = (q.replace('.', '/') + '/' + cls03 + '.java') if q else rel
            rev = restore_enum_strings(rev, rel_eff)
            # ★ 2026-10-01 第 52 轮：**紧跟其后**做枚举常量改名（顺序关键 ✓）——
            #   此时 `restore_enum_strings` 已按 02b 键判断完毕（我们的 03 源常量名是 INI 字符串
            #   ⇒ 不命中 ⇒ 空转 ✓，不会补实参/构造器 ✗）；随后改名成混淆名 ⇒ javac 得
            #   `(String,int)` ✓ 且 `name()` 保留 INI 字符串 ✓（与 stock 逐字吻合）。
            if _rs27_name_to_obf():
                rev = rename_enum_constants(rev, _rs27_name_to_obf())
            if q:
                # 改包不改名 → FQN 与 jar 同名 → 同名覆盖原版（消除幽灵类）
                rev = re.sub(r'(?m)^(\s*package\s+)[\w.]+(\s*;)',
                             lambda m: m.group(1) + q + m.group(2), rev, count=1)
                # 同包 import 在 Java 里本就冗余；搬包后更会指向不存在的包 → 删除
                rev = re.sub(r'(?m)^\s*import\s+(?:static\s+)?' + re.escape(pkg03) + r'\.[\w$]+;\s*$',
                             '', rev)
                out_path = REV_SRC / rel_eff
                key = str(out_path)
                if key in written and written[key] != rel:
                    conflict += 1
                    continue
                written[key] = rel
                moved_orig[rel_eff] = rel
                moved += 1
            else:
                out_path = REV_SRC / rel
            out_path.parent.mkdir(parents=True, exist_ok=True)
            out_path.write_text(rev, encoding='utf-8')
            continue
        pkg02, obf02 = tgt
        obf02_full = obf02 + suffix2
        src2 = strip_unused_imports(src)  # 03 源先清理 (反向后同名 import 无法区分, 误删正确导入)
        rev = fast_reverse_source(src2, cls03, (pkg02, obf02_full), mapping)
        hm_eff = host_map if use_host_constraint(tgt, cls03, pkg03) else None
        rev = reverse_members(rev, mmap, fmap, skip_by_rel.get(rel, ()), per_map,
                              (pkg02, obf02), hm_eff,
                              gt_members=gt_closure,
                              gt_names=gt_closure.get(pkg02 + '.' + obf02_full),
                              stats=guard_stats)
        rev = restore_enum_strings(rev, pkg02.replace('.', '/') + '/' + obf02_full + '.java')
        # ★ 同上（第 52 轮）：第二处调用点也要接上，否则走这条路径的类仍会被补回实参+构造器 ✗
        # ★ 第 67 轮（RS-39）：本分支能拿到**混淆宿主键** `(pkg02, obf02)` ✓ ——
        #   传入它即可用 `_rs27_host_map()`（supplement 直读 ✓、**不做歧义丢弃** ✓）做宿主精确改名 ✓，
        #   修掉常见词常量名（`Pre`/`Active`/`Main`/`normal` ✗）查不到的问题 ✓。
        if _rs27_name_to_obf():
            # ★ 第 68 轮（RS-40）：`host_scoped=True` ⇒ 允许**单常量**枚举 ✓
            rev = rename_enum_constants(rev, _rs27_name_to_obf((pkg02, obf02)), host_scoped=True)
        out_path = REV_SRC / pkg02.replace('.', '/') / (obf02_full + '.java')
        key = str(out_path)
        if key in written and written[key] != rel:
            conflict += 1
            continue
        out_path.parent.mkdir(parents=True, exist_ok=True)
        out_path.write_text(rev, encoding='utf-8')
        written[key] = rel
        ok += 1
    print(f'反向: {ok} 文件 (跳过 {no_map} 无类映射 + {conflict} 冲突) → {REV_SRC}'
          + (f'；其中 {moved} 个无映射类经**包迁移**放回真混淆包' if moved else ''))

    # 01d. 包迁移的**跨包引用改写**（2026-09-25 实测补上）
    #
    # 迁移只改了被搬文件自身的 package 声明；但**别的包**的文件仍按可读包路径引用它
    # （实测 `java/audio/lwjgl/OpenALMusic.java` 里 `import ...audio.backend.c;` +
    #   `throw new c(...)` → 迁移后 `backend/c` 不存在 → 4 处编译错：
    #   `constructor c in class c cannot be applied to given types` / `cannot find symbol`）。
    # 故在写盘后做一次全局改写：把被搬类的**旧全限定名**替换为新全限定名。
    if moved_orig or drop_fqn:
        old2new = {}
        for new_rel, orig_rel in moved_orig.items():
            old_fqn = orig_rel[:-len('.java')].replace('/', '.')
            new_fqn = new_rel[:-len('.java')].replace('/', '.')
            if old_fqn != new_fqn:
                old2new[old_fqn] = new_fqn
        # 让位族的族外引用：可读全限定名 → 混淆全限定名（形状匹配推出，从 jar 解析）
        for a, b in drop_fqn.items():
            old2new.setdefault(a, b)
        if old2new:
            n_rew = 0
            for p in REV_SRC.rglob('*.java'):
                txt = p.read_text(encoding='utf-8')
                new = txt
                for a, b in old2new.items():
                    if a in new:
                        new = new.replace(a, b)
                if new != txt:
                    p.write_text(new, encoding='utf-8')
                    n_rew += 1
            print(f'包迁移引用改写: {len(old2new)} 个旧全限定名 → 改写 {n_rew} 个文件')
    if guard_stats:
        print(f'声明侧产物名守卫 (H-8): 拦下 {guard_stats.get("blocked_decl", 0)} 处'
              f'字段/方法 + {guard_stats.get("blocked_enum", 0)} 处枚举常量 + '
              f'{guard_stats.get("blocked_access", 0)} 个配套访问侧映射'
              f' (产物名不在原版字节码 → 保持语义名)')
        if guard_stats.get('blocked_list'):
            print(f'  拦下明细: {sorted(set(guard_stats["blocked_list"]))[:20]}')

    if dry:
        print('[dry-run] 未编译未打包')
        return
    if skip_compile:
        print('[skip-compile]')
        return

    # 02. javac 全量编译
    javac = find_javac()
    jar_classes = jar_class_fqns()
    # 迭代跳过: 累积跳过清单 (build-skip.txt, 入库可审查) — JLS 类包同名硬限制/传递引用
    # 导致的部分文件无法编译, 从原 jar 合并 (运行时与原版一致);
    # 修复根因后可删除 build-skip.txt 重验 (文档注明)。
    skip_set = set()
    # 默认 = ROOT/build-skip.txt (行为不变); RW_BUILD_SKIP_FILE 供变体工具 (D-6) 使用
    skip_file = Path(os.environ.get('RW_BUILD_SKIP_FILE', str(ROOT / 'build-skip.txt')))
    if skip_file.exists():
        skip_set = {l.strip() for l in skip_file.read_text(encoding='utf-8').splitlines() if l.strip()}
    if skip_set:
        print(f'累积跳过 (build-skip.txt): {len(skip_set)} 个')
    # 类名与 jar 子包同名 (如 gameFramework/j.java 类 j vs j/ 包): -source 8 下
    # javac 报"类与同名类型冲突", 该类跳过编译 (jar 原样提供);
    # 引用其子类的文件 (import j.X) 同样无法编译 → 跳过 (B5 精确版: 仅 import 行 + 剥注释)
    jar_dirs = {n.rsplit('/', 1)[0] for n in jar_classes if '/' in n}
    conf_import_re = None
    conf_cls = []
    for p in REV_SRC.rglob('*.java'):
        rel2 = str(p).replace('\\', '/').split('reverse-src/')[-1]
        if rel2.endswith('.java') and rel2[:-len('.java')] in jar_dirs:
            conf_cls.append(rel2[:-len('.java')])
    if conf_cls:
        imp_pats = []
        for c in conf_cls:
            cname = c.rsplit('/', 1)[-1]
            pkg_dot = c.rsplit('/', 1)[0].replace('/', '.')
            imp_pats.append(re.escape(pkg_dot) + r'\.' + re.escape(cname) + r'\.[A-Za-z_$]')
        conf_import_re = re.compile('|'.join(imp_pats))
    files = []
    jls_files = []      # 「目标包与 jar 类同名」受阻的文件（第二遍用过滤类路径编译）
    for p in REV_SRC.rglob('*.java'):
        rel = str(p).replace('\\', '/').split('reverse-src/')[-1]
        # 包迁移过的文件：**一切按路径的过滤判据都必须用「原 03 相对路径」**。
        # 否则 build-skip.txt 的条目（按旧路径登记）全部失配、R4/jar 目录类过滤也会换判据
        # → 一批「本不该编译」的文件被拉进编译（实测 98 错：`variable n is already defined`
        # `int cannot be dereferenced` 等，全是这些文件自身的历史缺陷）。
        orig = moved_orig.get(rel, rel)
        # 第三方库 (steamworks 等) 反向源码默认不编译 — jar 原版提供, 运行时一致
        # (反编译源码引用 access$000 等合成成员, javac 无法编译)。
        # 2026-09-25 白名单：实测该族 121 个文件里**只有这 5 个当前可编译**（各自产出目标类），
        # 其余 116 个报「找不到符号」157 /「方法参数不匹配」33 类普通错误（需逐文件 REV）。
        # 这 5 个的目标 FQN 均存在于原版 jar → 纳入编译即 +5 个 A 类替换。
        # 2026-09-25：整族纳入编译（此前只放行 5 个白名单）——能编的落地为 A 类，
        # 编不了的仍由原版字节码提供，门禁兜底；枚举常量缺参已由 complete_enum_ctor_args 修好。
        if orig.startswith('com/codedisaster/') and orig not in CODEDISASTER_KEEP:
            continue
        pkg = orig[:orig.rfind('/')]
        bad = False
        parts = pkg.split('/')
        # 前缀判定必须**含完整包路径**（2026-09-25 修正 off-by-one）：
        # `range(1, len(parts))` 只查严格前缀，漏掉「包本身即 jar 类名」这种情形
        # （实测：`gameFramework/f`、`game/units/a`、`game/units/custom/b`、`java/c`、`a/a`
        #  在 jar 里**同时是类名**，写进这些包的源码在 `-source 8` 下必然报
        #  `package X clashes with class of same name`）。
        # 顶层包（`gameFramework` / `game` / `java/audio/a` / `gameFramework/utility`）都不是 jar 类，
        # 故补全不会误伤正常包（已实测逐条确认）。
        jls_prefix = None
        for i in range(1, len(parts) + 1):
            if '/'.join(parts[:i]) in jar_classes:
                bad = True
                jls_prefix = '/'.join(parts[:i])
                break
        if bad:
            # 2026-09-25 提量改造：不再丢弃，**收集**到 JLS 名单，交给第二遍编译
            # （用摘掉「冲突类」的过滤类路径副本，包/类就不再同名）。
            jls_files.append((str(p), jls_prefix))
            continue
        # 类路径与 jar 子包同名 (类 a.a.a vs 包 a.a.a/) → -source 8 真 JLS 冲突
        # (javac 17 默认可编译, -source 8 报"类与同名类型冲突"; B5 最小复现验证)
        # → 该类跳过编译 (jar 原样提供), 且引用其子类的文件也跳过 (import j.l 无法解析)
        cls_path = orig[:-len('.java')]
        if cls_path in jar_dirs:
            continue
        # 引用冲突类子类 (import <pkg>.<conf_cls>.<sub>;) → -source 8 解析失败 → 跳过
        # B5 修复: 仅匹配 import 行 + 剥行注释 (旧正则曾误伤注释 'xx.java' 与
        # 同包裸引用; 类本身引用 (import j;) 从 jar 解析可编译, 不过滤)
        if conf_import_re:
            src = p.read_text(encoding='utf-8', errors='ignore')
            src_clean = re.sub(r'//.*$', '', src, flags=re.M)
            if conf_import_re.search(src_clean):
                continue
        # B5 修复: 移除 conf_import_re/use 引用过滤 — 冲突类自身已跳过编译 (jar 原样),
        # 引用方从 classpath (原 jar) 解析, 无需跳过; 旧正则误伤 (import j.l 子包类/
        # 注释 'xx.java' 字样/包前缀含 jar 类全名) 曾致大量类误入 skip
        # 上次编译报错 → 跳过 (迭代收敛; 从原 jar 合并)
        if orig in skip_set:
            continue
        files.append(str(p))
    print(f'编译文件数: {len(files)}')
    if not files:
        print('无文件')
        return

    cp = str(ROOT / 'cache' / 'patched-classes').replace('\\', '/')
    cp += ';' + str(GAME_LIB).replace('\\', '/')
    stubs = ROOT / 'tools' / 'gates' / 'stubs'
    if stubs.exists():
        cp += ';' + str(stubs).replace('\\', '/')
    if LIBS_DIR.exists():
        for jar in sorted(LIBS_DIR.glob('*.jar')):
            cp += ';' + str(jar).replace('\\', '/')

    if REV_CLS.exists():
        shutil.rmtree(REV_CLS)
    REV_CLS.mkdir(parents=True)
    # ── 编译分遍（2026-09-25 提量改造）────────────────────────────────────────
    # 目标包在 jar 里**同时是类名**时（`gameFramework/m`、`gameFramework/j`、`game/units/a` …），
    # `-source 8` 下 javac 报 `package X clashes with class of same name` 而直接拒绝编译。
    # 实测这类文件 **548 个，其中 509 个 FQN 已在 jar**（潜在的同名替换，是现有 261 的两倍）。
    # 解法：把「冲突类」从**类路径副本**里摘掉（`build/_cp-noconflict.jar`），让这些包在 javac 眼里
    # 不再与类同名 → 这类文件单独一遍编译；两遍写进同一个 `-d` 目录（javac 允许分包输出）。
    def _conflict_prefix(rel2):
        pk = rel2[:rel2.rfind('/')]
        parts = pk.split('/')
        for i in range(1, len(parts) + 1):
            pre = '/'.join(parts[:i])
            if pre in jar_classes:
                return pre
        return None

    conflict_paths = {pre for _p, pre in jls_files if pre}
    f_main = list(files)
    f_jls = [p for p, _pre in jls_files]
    print(f'编译分遍: 常规 {len(f_main)} 文件 / JLS 冲突包 {len(f_jls)} 文件 '
          f'(涉及 {len(conflict_paths)} 个「类与包同名」的类)')

    def _run_javac(paths, classpath):
        with tempfile.NamedTemporaryFile(mode='w', suffix='.txt', delete=False, encoding='utf-8') as fh:
            for q in paths:
                fh.write(str(Path(q).relative_to(ROOT)).replace('\\', '/') + '\n')
            lf = fh.name
        try:
            return subprocess.run(
                [javac, '-encoding', 'UTF-8', '-J-Duser.language=en',
                 '-source', '8', '-target', '8',
                 '-Xmaxerrs', '100000', '-cp', classpath, '-d', str(REV_CLS).replace('\\', '/'),
                 '-proc:none', '-nowarn', '-Xlint:none', f'@{lf}'],
                capture_output=True, timeout=900, cwd=str(ROOT))
        finally:
            try:
                Path(lf).unlink()
            except OSError:
                pass

    errs = []
    if f_main:
        r = _run_javac(f_main, cp)
        errs.append(r.stderr.decode('utf-8', errors='replace') if r.stderr else '')

    jls_kept, jls_dropped = [], []
    if f_jls:
        # **按冲突类分批**（2026-09-25 关键修正）：一次性摘掉全部 34 个冲突类会把
        # `gameFramework.l`（= GlobalState）这类**核心类**也摘掉 → 大面积断裂。
        # 正确粒度：编译「包 P」的文件时，只摘掉类 P 自己，其余冲突类照旧可见。
        batches = {}
        for p, pre in jls_files:
            batches.setdefault(pre, []).append(p)
        print(f'JLS 分遍: {len(batches)} 个「类与包同名」批次（每批只摘掉自己那一个冲突类）')
        st = GAME_LIB.stat()
        for pre, batch in sorted(batches.items(), key=lambda kv: -len(kv[1])):
            nojar = BUILD / ('_cp-noconflict-%s.jar' % pre.replace('/', '_'))
            keyf = nojar.with_name(nojar.name + '.key')
            want = '%s|%s|%s' % (st.st_size, st.st_mtime_ns, pre)
            if not (nojar.exists() and keyf.exists()
                    and keyf.read_text(encoding='utf-8').strip() == want):
                with zipfile.ZipFile(GAME_LIB) as zin, \
                        zipfile.ZipFile(nojar, 'w', zipfile.ZIP_DEFLATED) as zout:
                    for item in zin.infolist():
                        if item.filename == pre + '.class':
                            continue
                        zout.writestr(item, zin.read(item.filename))
                keyf.write_text(want, encoding='utf-8')
            cp_i = str(nojar).replace('\\', '/')
            if stubs.exists():
                cp_i += ';' + str(stubs).replace('\\', '/')
            if LIBS_DIR.exists():
                for jar in sorted(LIBS_DIR.glob('*.jar')):
                    cp_i += ';' + str(jar).replace('\\', '/')
            remaining = list(batch)
            converged = False
            for _attempt in range(6):
                r2 = _run_javac(remaining, cp_i)
                e2 = r2.stderr.decode('utf-8', errors='replace') if r2.stderr else ''
                if 'error:' not in e2:
                    converged = True
                    break
                bad = {str((ROOT / m.group(1)).resolve()).replace('\\', '/')
                       for m in re.finditer(r'^(\S+\.java):\d+: error:', e2, re.M)}
                keep = [q for q in remaining
                        if str(Path(q).resolve()).replace('\\', '/') not in bad]
                jls_dropped.extend(
                    str(Path(q).relative_to(ROOT)).replace('\\', '/')
                    for q in remaining if q not in keep)
                if len(keep) == len(remaining) or not keep:
                    break
                remaining = keep
            if converged:
                jls_kept.extend(remaining)
                print(f'  ✅ {pre:<58} {len(remaining):>3}/{len(batch):<3} 文件编译通过')
            else:
                jls_dropped.extend(str(Path(q).relative_to(ROOT)).replace('\\', '/') for q in remaining)
                print(f'  ⛔ {pre:<58} 未能收敛 → 整批放弃（不影响门禁）')
        try:
            (BUILD / 'jls-kept.txt').write_text(
                '\n'.join(sorted(jls_kept)) + '\n', encoding='utf-8')
            (BUILD / 'jls-dropped.txt').write_text(
                '\n'.join(sorted(set(jls_dropped))) + '\n', encoding='utf-8')
        except OSError:
            pass
        print(f'JLS 分遍结果: 编译通过 {len(jls_kept)} 文件 / 放弃 {len(set(jls_dropped))} 文件')

    err = '\n'.join(e for e in errs if e)
    n_err = err.count('error:')
    print(f'编译: error 行数={n_err}（两遍合计）')
    if n_err:
        # 完整 stderr 转储 (错误行格式异常时排查)
        with open(BUILD / 'javac-stderr.txt', 'w', encoding='utf-8') as f:
            f.write(err)
    if n_err:
        # 完整错误写 CSV (file,line,message) 供迭代分析; 摘要前 20 条
        rows = []
        cur = None
        for line in err.splitlines():
            m = re.match(r'^(.*\.java):(\d+): error: (.*)$', line)
            if m:
                cur = [m.group(1), m.group(2), m.group(3)]
                rows.append(cur)
            elif cur and line.startswith('  symbol:') or cur and line.startswith('  location:'):
                cur[-1] += ' ' + line.strip()
        with open(BUILD / 'compile-errors.csv', 'w', encoding='utf-8', newline='') as f:
            w = csv.writer(f)
            w.writerow(['file', 'line', 'message'])
            w.writerows(rows)
        print(f'错误 CSV: {BUILD / "compile-errors.csv"} ({len(rows)} 条)')
        # 报错文件追加进累积跳过清单 (下轮不再编译)。
        #
        # ⚠️ 安全护栏 (H-8b, 2026-09-23 立规): 默认只写**已存在的清单文件**。
        # 若目标是受版本管理的 build-skip.txt 且出现**新增**条目, 则拒绝改写, 改为把
        # 新增条目写入隔离文件 build/compile-errors-skip.txt 并显式报警 —— 因为静默
        # 追加会：(a) 违反 U-10「该文件会话期内只读」; (b) 让产物**悄悄退化** (该类由
        # 原版类顶替, 缺陷被掩盖而非修复)。本会话曾两次踩到该坑。
        # 需要旧行为时设 RW_SKIP_ALLOW_APPEND=1。
        new_skip = sorted({r[0].replace('\\', '/').split('reverse-src/')[-1] for r in rows} | skip_set)
        added = [s for s in new_skip if s not in skip_set]
        protected = skip_file.name == 'build-skip.txt' and not os.environ.get('RW_SKIP_ALLOW_APPEND')
        if added and protected:
            quarantine = BUILD / 'compile-errors-skip.txt'
            quarantine.write_text('\n'.join(added) + '\n', encoding='utf-8')
            print(f'⚠️ 编译错误使 {len(added)} 个文件需跳过, 但目标清单是受版本管理的 '
                  f'{skip_file.name} → **拒绝改写** (U-10 红线)。')
            print(f'   新增条目已写入隔离文件: {quarantine}')
            for s in added[:10]:
                print(f'     + {s}')
            print(f'   如需沿用旧行为 (就地追加): 设 RW_SKIP_ALLOW_APPEND=1')
        else:
            skip_file.write_text('\n'.join(new_skip) + '\n', encoding='utf-8')
            print(f'skip-list 更新: {len(new_skip)} 个 (新增 {len(added)})')
        for row in rows[:20]:
            print(f'  {row[0]}:{row[1]}: {row[2][:110]}')
        if len(rows) > 20:
            print(f'  ... 共 {len(rows)} 条')
    else:
        # 编译成功 → 删除旧 CSV (迭代跳过列表失效, 下次全量)
        try:
            (BUILD / 'compile-errors.csv').unlink()
        except OSError:
            pass
    n_cls = len(list(REV_CLS.rglob('*.class')))
    print(f'编译产物: {n_cls} class → {REV_CLS}')

    # 03. 打包: 反向编译产物 + 原 jar 未反向类
    with zipfile.ZipFile(GAME_LIB) as zin:
        names = zin.namelist()
    covered = {str(p.relative_to(REV_CLS)).replace('\\', '/') for p in REV_CLS.rglob('*.class')}
    with zipfile.ZipFile(OUT_JAR, 'w', zipfile.ZIP_DEFLATED) as zout:
        # 反向类
        for p in REV_CLS.rglob('*.class'):
            zout.write(p, str(p.relative_to(REV_CLS)).replace('\\', '/'))
        # 原 jar 中未被反向覆盖的 (第三方/无映射/黑名单)
        with zipfile.ZipFile(GAME_LIB) as zin2:
            for n in names:
                if n.endswith('.class') and n in covered:
                    continue
                zout.writestr(n, zin2.read(n))
    print(f'打包: {OUT_JAR} ({OUT_JAR.stat().st_size} bytes)')


if __name__ == '__main__':
    main()
