#!/usr/bin/env python3
"""B3 全量反向构建器: 03 源码 → 混淆名 → javac → jar 替代 game-lib.jar

管线:
  1. 类名反向 (复用 runtime_patch_batch.reverse_source: package/类声明/import/全限定/裸引用/构造器)
  2. 方法/字段名反向 (supplement rev: 语义名→混淆名, 仅无跨类冲突的 91%; 冲突 9% 保持语义名)
  3. javac 全量编译 (@filelist, classpath 同 javac_gate)
  4. 打包: 反向编译产物 + 原 jar 未反向类 (第三方/无映射) → build/game-lib-reverse.jar

用法: python tools/fixers/build_reverse_jar.py [--apply] [--skip-compile]
输出: build/reverse-src/ (反向源码) / build/reverse-classes/ (编译产物) / build/game-lib-reverse.jar
"""
import csv
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
                          MAPPINGS_CSV, ROOT, SUPPLEMENT_CSV, find_javac)
from tools.fixers.runtime_patch_batch import load_mapping, reverse_source

LIBS_DIR = ROOT / 'RustedWarfare' / 'libs'

BUILD = ROOT / 'build'
REV_SRC = BUILD / 'reverse-src'
REV_CLS = BUILD / 'reverse-classes'
OUT_JAR = BUILD / 'game-lib-reverse.jar'

# B5: 反向撞名全限定检查用的 jar 类全集 (main() 初始化; 点分全名集合)
JAR_CLASS_SET = None

# 与 jar 类名冲突的包前缀 (jar 有 a/a/a.class 类 + a/a/a/ 包) — 复用 runtime_patch 策略
BLACKLIST = {
    'com/corrodinggames/rts/game/units/af.java',
    'com/corrodinggames/rts/game/units/h.java',
    'com/corrodinggames/rts/game/j.java',
    'com/corrodinggames/rts/game/units/q.java',
    'com/corrodinggames/rts/game/units/n.java',
    'com/corrodinggames/rts/game/units/u.java',
    'com/corrodinggames/rts/game/units/custom/n.java',  # DirectionType raw enum 运行时问题
}

METHOD_RE = re.compile(
    r'(?m)^\s*(?:public|protected|private)\s+(?:static\s+)?(?:final\s+)?(?:synchronized\s+)?'
    r'(?:strictfp\s+)?(?:native\s+)?(?:abstract\s+)?[\w<>\[\].,? ]+\s+(\w+)\s*\(')


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
    return mmap, fmap


def load_member_map_by_class():
    """supplement → 宿主感知成员映射: {(混淆包, 混淆类): {语义名: 混淆名}}

    同名语义方法跨类映射冲突 (如 isEnabled→a/c/e/f/l/t... 10+ 类) 被全局唯一化
    过滤后全部放弃, 运行时 NoSuchMethodError 反馈 (B4 实证: ObjectPool.isEnabled)。
    宿主感知: 声明侧按宿主类精确反向 (冲突名按宿主解析), 访问侧仍全局兜底。
    返回 (per_m, per_f): 方法/字段命名空间分离 — 字段映射不得用于方法声明
    (B4 实证: callback 字段映射误伤方法声明, 原生回调 NoSuchMethodError)。
    """
    per_m, per_f = {}, {}
    for r in csv.reader(open(SUPPLEMENT_CSV, encoding='utf-8')):
        if not r or len(r) < 5 or r[0] not in ('field', 'method'):
            continue
        # B5.6: 同上 — 可疑映射 (字节码无此成员) 不参与宿主感知反向
        if len(r) > 6 and r[6].strip() in ('suspicious-bc-missing',):
            continue
        typ, pkg, cls, obf, sem = r[0], r[1], r[2], r[3], r[4]
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
        host = (pkg.replace('/', '.'), cls)
        per = per_m if typ == 'method' else per_f
        per.setdefault(host, {}).setdefault(sem_name, set()).add(obf_name)
    clean = lambda d: {h: {s: next(iter(v)) for s, v in x.items() if len(v) == 1}
                       for h, x in d.items()}
    return clean(per_m), clean(per_f)


def restore_enum_strings(src, rel):
    """从 02b 同名文件恢复枚举常量字符串参数 (INI 解析键)。

    B4 实证: 03 中大量枚举的字符串构造参数丢失 (如 UnitState 的 a("verysmall", 0)
    被简化为 a,), 运行时 INI 解析 "Unknown value" 崩溃。
    02b 按混淆路径组织, 与反向产物路径一致 → 常量名对齐直接补参。
    """
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
        m = re.match(r'^(\s*)([A-Za-z_$][\w$]*)(,?)\s*$', line)
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


def fast_reverse_source(src, cls03, tgt, mapping, keep_cls=False):
    """快速类名反向 (交替正则一次扫描, 替代 runtime_patch 的逐条 sub)。

    同 reverse_source 语义: package/类声明/构造器/import/全限定/裸引用。
    keep_cls=True: 类名/包名保持 (03 重建类无 jar 对应), 仅反向引用 (import/全限定/裸引用)。
    """
    pkg02, obf02 = tgt
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
    # 2. 交替正则: 全部映射 (语义类名 → 02包.02名), 按名长降序; 查找用 dict O(1)
    name_to_fqn = {c03: cands[0][0] + '.' + cands[0][1] for c03, cands in mapping.items()}
    name_to_obf = {c03: cands[0][1] for c03, cands in mapping.items()}
    # import 行 (按简单名查 dict, 等价旧 1,140 项循环)
    def imp_repl(m):
        line = m.group(0)
        simple = line.rstrip(';').rsplit('.', 1)[-1].strip()
        if simple in name_to_fqn:
            return 'import ' + name_to_fqn[simple] + ';'
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
        segs = m.group(1).split('.')
        if segs[0] in ('this', 'super'):
            continue
        tail = segs[-1]
        if tail in name_to_fqn:
            fq_out.append(src[fq_last:m.start()])
            fq_out.append(name_to_fqn[tail])
            fq_last = m.end()
    if fq_out:
        src = ''.join(fq_out) + src[fq_last:]
    # 裸引用 (单词边界, 非 import 行; 字典驱动单 token 扫描, 无回溯)
    # B5 修复: 跨包类型简单名与目标同包类撞名 (如 custom/k 的 UnitInstance→am 被同包
    # custom/am 抢占, 字段类型错位 → 运行时 NoSuchFieldError) → 撞名时全限定
    out_lines = []
    pkg_classes = set()  # 目标包的同包类名 (jar 混淆名)
    if pkg02 and JAR_CLASS_SET:
        pre = pkg02.replace('.', '/') + '/'
        pkg_classes = {n[len(pre):].split('$')[0] for n in JAR_CLASS_SET if n.startswith(pre)}
    for line in src.split('\n'):
        if line.lstrip().startswith('import ') or line.lstrip().startswith('package '):
            out_lines.append(line)
            continue
        def bare_repl(m2):
            tok = m2.group(0)
            obf = name_to_obf.get(tok)
            if obf is None:
                return tok
            if pkg02:
                fqn = name_to_fqn.get(tok)
                obf_pkg = fqn.rsplit('.', 1)[0] if fqn else pkg02
                if obf_pkg != pkg02 and obf in pkg_classes:
                    return fqn  # 同包撞名 → 全限定 (成员访问形态不匹配本正则, 安全)
            return obf
        out_lines.append(re.sub(r'(?<![\w.])([A-Za-z_$][\w$]*)(?![\w$])', bare_repl, line))
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


def collect_field_decls(src):
    """收集类内字段声明名集合 — 用于字段反向撞车检测。"""
    names = set()
    for m in re.finditer(
            r'(?m)^\s*(?:(?:public|protected|private)\s+)?'
            r'(?:(?:static|final|transient|volatile|synchronized)\s+)*'
            r'(?:[\w<>\[\].,?]+\s+)+([\w$]+)\s*(?:=|;)', src):
        names.add(m.group(1))
    return names


def reverse_members(src, mmap, fmap, skip=None, per_map=None, host_key=None):
    """方法/字段名反向 — 仅成员访问形态, 不碰裸 token (lambda 参数/局部变量/类型名)。

    安全规则:
      1. 字段声明: ^修饰符 类型 semName (;|=)  — 反向 (仅用字段映射)
      2. 方法声明: ^修饰符 类型 semName(       — 反向 (仅用方法映射, 字段映射不得误伤
         同名方法 — B4 实证: callback 字段映射改坏原生回调方法)
      3. 成员访问: .semName (this.x / obj.x / Class.x)  — 反向 (合并映射)
    跳过 import/package 行; skip = 全局撞车剔除集 (声明+访问均保持语义名);
    per_map/host_key = 宿主感知映射 (冲突名按宿主类精确反向, 覆盖全局过滤)。
    """
    combined = dict(fmap)
    combined.update(mmap)
    mmap_eff = dict(mmap)
    fmap_eff = dict(fmap)
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
    if not name_to_obf:
        return src

    # 1. 字段/方法声明行 (行首修饰符后; 通用 token 扫描 + dict 查找, 无交替回溯)
    #    方法声明 (后跟 () 仅查方法映射; 字段声明 (=;) 仅查字段映射 — 命名空间隔离
    decl_re = re.compile(
        r'^(\s*(?:public|protected|private)\s+(?:static\s+)?(?:final\s+)?(?:synchronized\s+)?'
        r'(?:strictfp\s+)?(?:transient\s+)?(?:volatile\s+)?(?:[\w<>\[\].,?]+\s+)+)'
        r'([A-Za-z_$][\w$]*)(\s*[=;(])', re.M)
    def decl_repl(m):
        name = m.group(2)
        sep = m.group(3)
        if name in JDK_METHOD_NAMES:
            return m.group(0)  # B5: JDK 方法声明豁免 (toString 等 — 枚举 toString 覆盖被改
            # 名后失去覆盖, 运行时枚举解析报 Unknown value)
        if sep.lstrip().startswith('('):
            obf = mmap_eff.get(name, name)
        else:
            obf = fmap_eff.get(name, name)
        return m.group(1) + obf + sep
    src = decl_re.sub(decl_repl, src)

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
            return mm.group(1) + fmap_eff.get(mm.group(2), mm.group(2)) + (mm.group(3) or '') + mm.group(4)
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
    access_re = re.compile(r'([A-Za-z_$][\w$]*)(?<=[\w.])\.([A-Za-z_$][\w$]*)(?![\w$])')
    def access_repl(m):
        pre, name = m.group(1), m.group(2)
        if pre in JDK_SAFE_CLASSES or name in JDK_METHOD_NAMES:
            return m.group(0)
        return pre + '.' + name_to_obf.get(name, name)
    decl_start = re.compile(r'^\s*(?:public|protected|private|static|final|abstract|'
                            r'strictfp|transient|volatile|synchronized|native)')
    out_lines2 = []
    for line in src.split('\n'):
        if decl_start.match(line):
            out_lines2.append(line)
            continue
        out_lines2.append(access_re.sub(access_repl, line))
    src = '\n'.join(out_lines2)
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


def jar_class_fqns():
    out = subprocess.run(['jar', 'tf', str(GAME_LIB)], capture_output=True,
                         text=True, encoding='utf-8', errors='replace').stdout
    return {n[:-len('.class')].replace('\\', '/') for n in out.splitlines() if n.endswith('.class')}


def main():
    global JAR_CLASS_SET
    dry = '--apply' not in sys.argv
    skip_compile = '--skip-compile' in sys.argv

    mapping = load_mapping()  # sem类名 -> [(02包, 02混淆名)]
    # 防御: 历史脏数据包列含斜杠 (v19.117-PSR 遗留) → 归一化点分
    mapping = {k: [(p.replace('/', '.'), o) for p, o in v]
               for k, v in mapping.items()}
    JAR_CLASS_SET = jar_class_fqns()  # B5: 撞名全限定检查
    mmap, fmap = load_member_map()
    per_map = load_member_map_by_class()  # 宿主感知 (冲突名按宿主类精确反向)
    print(f'方法映射 (无冲突): {len(mmap)} | 字段映射: {len(fmap)} | 宿主映射: {len(per_map[0])} 方法类 + {len(per_map[1])} 字段类')

    # 00. 全局撞车剔除集: 先扫全部 03 文件, 检测"语义名反向成混淆名后与类内
    #     现有声明同名同参"的冲突 → 该语义名全局保持 (声明+访问均不反向)。
    #     根因: R8 字节码允许 (名+参数+返回类型) 区分方法, javac 源码不允许;
    #     且 03 中残留混淆名方法与语义化方法反向后撞车 (如 isBound→p 撞 private void p())。
    #     按文件预索引剪枝: 只检查文件中实际出现的语义名。
    from collections import defaultdict
    skip_global = set()
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
                continue
            # 字段撞车: obf 字段名已被类内占用
            if sem in fdecls and obf in fdecls:
                skip_global.add(sem)
                continue
            # 第二层收集: 反向后互撞检测 (按文件分组 — 跨文件同名不冲突)
            for n in sem_ns:
                usage[(rel, obf, n)].append(sem)
    for (rel, obf, n), sems in usage.items():
        if len(sems) > 1:
            for s in sems:
                skip_global.add(s)
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
    if skip_global:
        print(f'全局撞车剔除 (保持语义名): {len(skip_global)} 个: {sorted(skip_global)[:40]}')
    if skip_global:
        print(f'全局撞车剔除 (保持语义名): {len(skip_global)} 个: {sorted(skip_global)[:40]}')

    # 01. 反向重命名全部有类映射的 03 文件
    if REV_SRC.exists():
        shutil.rmtree(REV_SRC)
    REV_SRC.mkdir(parents=True)
    ok = no_map = conflict = 0
    written = {}
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
        cands = mapping.get(cls03, [])
        suffix2 = ''
        if not cands and suffix:
            cands = mapping.get(outer03, [])
            suffix2 = suffix
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
        if not tgt:
            # 无类映射 (03 重建类/官方语义名类): 部分反向 — 类名/包名保持,
            # 仅反向引用 (import/全限定/裸引用/成员), 输出到原 03 路径。
            # 运行时: 重建类为新增类 (jar 无), 官方名类由 jar 原样提供。
            # 注意: 03 类名可能仍是混淆名 (如 utility/ad 类名 ad 未语义化),
            # 宿主映射按 (混淆包, 混淆类名) 匹配 — 用 03 包路径+类名做 host_key。
            no_map += 1
            src = strip_unused_imports(src)  # 03 源先清理 (反向后同名 import 无法区分)
            rev = fast_reverse_source(src, cls03, (None, None), mapping, keep_cls=True)
            host_key = (pkg03, cls03)
            rev = reverse_members(rev, mmap, fmap, skip_global, per_map, host_key)
            rev = restore_enum_strings(rev, rel)
            out_path = REV_SRC / rel
            out_path.parent.mkdir(parents=True, exist_ok=True)
            out_path.write_text(rev, encoding='utf-8')
            continue
        pkg02, obf02 = tgt
        obf02_full = obf02 + suffix2
        src = strip_unused_imports(src)  # 03 源先清理 (反向后同名 import 无法区分, 误删正确导入)
        rev = fast_reverse_source(src, cls03, (pkg02, obf02_full), mapping)
        rev = reverse_members(rev, mmap, fmap, skip_global, per_map, (pkg02, obf02))
        rev = restore_enum_strings(rev, pkg02.replace('.', '/') + '/' + obf02_full + '.java')
        out_path = REV_SRC / pkg02.replace('.', '/') / (obf02_full + '.java')
        key = str(out_path)
        if key in written and written[key] != rel:
            conflict += 1
            continue
        out_path.parent.mkdir(parents=True, exist_ok=True)
        out_path.write_text(rev, encoding='utf-8')
        written[key] = rel
        ok += 1
    print(f'反向: {ok} 文件 (跳过 {no_map} 无类映射 + {conflict} 冲突) → {REV_SRC}')

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
    skip_file = ROOT / 'build-skip.txt'
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
    for p in REV_SRC.rglob('*.java'):
        rel = str(p).replace('\\', '/').split('reverse-src/')[-1]
        # 第三方库 (steamworks 等) 反向源码不编译 — jar 原版提供, 运行时一致
        # (反编译源码引用 access$000 等合成成员, javac 无法编译)
        if rel.startswith('com/codedisaster/'):
            continue
        pkg = rel[:rel.rfind('/')]
        bad = False
        parts = pkg.split('/')
        for i in range(1, len(parts)):
            if '/'.join(parts[:i]) in jar_classes:
                bad = True
                break
        if bad:
            continue
        # 类路径与 jar 子包同名 (类 a.a.a vs 包 a.a.a/) → -source 8 真 JLS 冲突
        # (javac 17 默认可编译, -source 8 报"类与同名类型冲突"; B5 最小复现验证)
        # → 该类跳过编译 (jar 原样提供), 且引用其子类的文件也跳过 (import j.l 无法解析)
        cls_path = rel[:-len('.java')]
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
        if rel in skip_set:
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
    with tempfile.NamedTemporaryFile(mode='w', suffix='.txt', delete=False, encoding='utf-8') as f:
        for p in files:
            f.write(str(Path(p).relative_to(ROOT)).replace('\\', '/') + '\n')
        filelist = f.name
    try:
        r = subprocess.run(
            [javac, '-encoding', 'UTF-8', '-J-Duser.language=en',
             '-source', '8', '-target', '8',
             # 官方启动器 (Rusted Warfare.exe) 用系统注册 JRE 8 (class 52);
             # 游戏自带 jvm64 为 JDK 13 (class 57) 向后兼容 class 52。
             # v19.133f98 修复: 13→8 (class 57 曾致 exe 启动 JNI 错误);
             # --add-exports 与 -target 8 不兼容 (javac 17 限制), 已移除 (B4 时冗余)
             '-Xmaxerrs', '100000', '-cp', cp, '-d', str(REV_CLS).replace('\\', '/'),
             '-proc:none', '-nowarn', '-Xlint:none', f'@{filelist}'],
            capture_output=True, timeout=900, cwd=str(ROOT))
    finally:
        try:
            Path(filelist).unlink()
        except OSError:
            pass
    err = r.stderr.decode('utf-8', errors='replace') if r.stderr else ''
    n_err = err.count('error:')
    print(f'编译: returncode={r.returncode}, error 行数={n_err}')
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
        # 报错文件追加进累积跳过清单 (下轮不再编译)
        new_skip = sorted({r[0].replace('\\', '/').split('reverse-src/')[-1] for r in rows} | skip_set)
        skip_file.write_text('\n'.join(new_skip) + '\n', encoding='utf-8')
        print(f'skip-list 更新: {len(new_skip)} 个')
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
