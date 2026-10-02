"""
rwlib.bytecode — javap 调用和 .class 文件操作封装

提供字节码级别的查询: 方法签名、字段列表、类文件索引。

P0.1 (2026-08-11): 添加 CJK 安全 javap 封装。
    - _copy_to_ascii_temp(): 将 .class 复制到 %TEMP% ASCII 路径
    - get_methods/get_fields 自动检测路径并走安全通道
    - 新增 get_constant_pool_strings() 提取常量池 UTF8 字符串
    - 所有调用点透明, 无需修改

使用方式:
    from rwlib.bytecode import get_methods, get_fields, index_class_files
    methods = get_methods('path/to/SomeClass.class')
    idx = index_class_files()
"""

import os
import re
import shutil
import subprocess
import struct
import tempfile
import zipfile
from pathlib import Path

from .config import CLASSES_DIR, GAME_LIB, ROOT, find_javap

# ── CJK 安全: ASCII 临时目录缓存 ──────────────────────────────────────
_ASCII_TEMP_DIR = None


def _get_ascii_temp_dir():
    """返回 ASCII-only 临时目录, 用于 javap 安全调用。懒初始化, 会话级缓存。"""
    global _ASCII_TEMP_DIR
    if _ASCII_TEMP_DIR is None or not os.path.isdir(_ASCII_TEMP_DIR):
        _ASCII_TEMP_DIR = tempfile.mkdtemp(prefix="rw_javap_")
    return _ASCII_TEMP_DIR


def _has_cjk(path):
    """检测路径是否包含 CJK 字符 (javap 在这些路径下会失败)。"""
    for ch in str(path):
        cp = ord(ch)
        if (0x4E00 <= cp <= 0x9FFF or   # CJK Unified
            0x3400 <= cp <= 0x4DBF or   # CJK Extension A
            0xF900 <= cp <= 0xFAFF or   # CJK Compatibility
            0x2F800 <= cp <= 0x2FA1F):  # CJK Compatibility Supplement
            return True
    return False


def _copy_to_ascii_temp(classfile):
    """将 .class 文件复制到 ASCII 临时目录, 返回安全路径。"""
    src = Path(classfile)
    temp_dir = Path(_get_ascii_temp_dir())
    # 用类名 + 哈希确保唯一性
    safe_name = src.name
    dest = temp_dir / safe_name
    # 只在源文件更新时复制 (缓存优化)
    if not dest.exists() or src.stat().st_mtime > dest.stat().st_mtime:
        shutil.copy2(str(src), str(dest))
    return str(dest)


def _javap_safe_output(classfile):
    """
    CJK 安全的 javap 调用。

    如果 classfile 路径含有 CJK 字符, 先复制到 ASCII 临时目录再 javap。
    返回 (stdout_text, success_bool)。
    """
    javap = find_javap()
    src_path = str(classfile)

    if _has_cjk(src_path):
        safe_path = _copy_to_ascii_temp(classfile)
    else:
        safe_path = src_path

    try:
        r = subprocess.run(
            [javap, "-p", safe_path],
            capture_output=True, text=True, timeout=10
        )
        return r.stdout, r.returncode == 0
    except (subprocess.TimeoutExpired, FileNotFoundError, OSError):
        return "", False

# javap 方法声明正则
# 匹配: [修饰符] 返回类型 方法名(参数列表)
# 例如: '  public static void a(int, float)' → ('void', 'a', 'int, float')
_JAVAP_METHOD_RE = re.compile(
    r'^\s*(?:\w+\s+)*(\w[\w<>[\].]*)\s+(\w+)\s*\(([^)]*)\)'
)

# javap 字段声明正则
# 匹配: [修饰符] 类型 字段名
_JAVAP_FIELD_RE = re.compile(
    r'^\s*(?:\w+\s+)*(\w[\w<>[\].]*)\s+(\w[\w_]+)\s*;?$'
)


def get_methods(classfile):
    """
    用 javap 获取类的所有方法签名。(P0.1: CJK 安全)

    参数:
        classfile: .class 文件路径 (str 或 Path)

    返回:
        Dict[Tuple[str, str], str]: {(方法名, 参数字符串): 返回类型}
        例如: {('a', 'int, float'): 'void', ('b', ''): 'boolean'}
    """
    stdout, ok = _javap_safe_output(classfile)
    if not ok:
        return {}

    methods = {}
    for line in stdout.split('\n'):
        m = _JAVAP_METHOD_RE.match(line.strip())
        if m:
            ret_type, name, params = m.group(1), m.group(2), m.group(3).strip()
            methods[(name, params)] = ret_type

    return methods


def get_fields(classfile):
    """
    用 javap 获取类的所有字段。(P0.1: CJK 安全)

    返回:
        Dict[str, str]: {字段名: 类型}
    """
    stdout, ok = _javap_safe_output(classfile)
    if not ok:
        return {}

    fields = {}
    for line in stdout.split('\n'):
        # 跳过方法行 (包含括号)
        if '(' in line:
            continue
        m = _JAVAP_FIELD_RE.match(line.strip())
        if m:
            ftype, fname = m.group(1), m.group(2)
            # 排除方法相关关键字
            if ftype not in ('throws', 'return', 'public', 'private', 'protected',
                            'static', 'final', 'abstract', 'synchronized', 'native',
                            'class', 'interface', 'extends', 'implements', 'package',
                            'import', 'volatile', 'transient', 'strictfp'):
                fields[fname] = ftype

    return fields


def get_methods_with_descriptors(classfile):
    """
    获取方法及其 JVM 描述符 (通过 -p -v 详细输出)。

    返回:
        Dict[str, Tuple[str, str]]: {方法名: (描述符, 返回类型)}
        描述符格式: (参数类型)返回类型, 如 (IF)Z
    """
    javap = find_javap()
    src_path = str(classfile)
    if _has_cjk(src_path):
        src_path = _copy_to_ascii_temp(classfile)

    try:
        r = subprocess.run(
            [javap, "-p", "-v", src_path],
            capture_output=True, text=True, timeout=15
        )
        if r.returncode != 0:
            return {}
    except (subprocess.TimeoutExpired, FileNotFoundError, OSError):
        return {}

    methods = {}
    current_method = None
    for line in r.stdout.split('\n'):
        line = line.strip()
        # 匹配方法声明: 修饰符 返回类型 方法名(参数);
        m = _JAVAP_METHOD_RE.match(line)
        if m:
            current_method = m.group(2)
            methods[current_method] = (None, m.group(1))  # descriptor to be filled
        # 匹配描述符行: descriptor: (IF)Z
        if current_method and line.startswith('descriptor:'):
            desc = line.split(':', 1)[1].strip()
            ret = methods.get(current_method, (None, ''))[1]
            methods[current_method] = (desc, ret)

    return methods


def get_constant_pool_strings(classfile):
    """
    提取类的常量池中所有 UTF8 字符串。(P0.1 新增)

    用于交叉验证: 字节码常量池字符串必须出现在对应源码中。
    通过 -p -v 输出解析, 避免二进制解析的复杂度。

    参数:
        classfile: .class 文件路径 (str 或 Path)

    返回:
        List[str]: 常量池中的所有 UTF8 字符串 (去重)
    """
    javap = find_javap()
    src_path = str(classfile)
    if _has_cjk(src_path):
        src_path = _copy_to_ascii_temp(classfile)

    try:
        r = subprocess.run(
            [javap, "-p", "-v", src_path],
            capture_output=True, text=True, timeout=15
        )
        if r.returncode != 0:
            return []
    except (subprocess.TimeoutExpired, FileNotFoundError, OSError):
        return []

    strings = []
    in_constant_pool = False
    for line in r.stdout.split('\n'):
        line = line.strip()
        if line.startswith('Constant pool:'):
            in_constant_pool = True
            continue
        if in_constant_pool:
            if line.startswith('{') or not line:
                continue
            if line.startswith('}'):
                break
            # 格式: #N = Utf8    someString
            m = re.match(r'^\s*#\d+\s*=\s*Utf8\s{2,}(.+)$', line)
            if m:
                s = m.group(1).strip()
                if s and len(s) > 1:  # 过滤空字符串和单字符
                    strings.append(s)

    return list(set(strings))  # 去重


def get_class_info(classfile):
    """
    获取类的完整字节码信息。(P0.1 新增 — inventory.py 核心函数)

    参数:
        classfile: .class 文件路径

    返回:
        Dict: {
            'methods': {(name, params): ret_type},
            'methods_with_desc': {name: (descriptor, ret_type)},
            'fields': {name: type},
            'strings': [utf8_strings],
            'super_class': str,
            'interfaces': [str],
        }
    """
    javap = find_javap()
    src_path = str(classfile)
    if _has_cjk(src_path):
        src_path = _copy_to_ascii_temp(classfile)

    try:
        r = subprocess.run(
            [javap, "-p", "-v", src_path],
            capture_output=True, text=True, timeout=15
        )
        if r.returncode != 0:
            return None
    except (subprocess.TimeoutExpired, FileNotFoundError, OSError):
        return None

    info = {
        'methods': {},
        'methods_with_desc': {},
        'fields': {},
        'strings': [],
        'super_class': 'java/lang/Object',
        'interfaces': [],
        'this_class': 'Unknown',
    }

    in_cp = False
    cp_strings_raw = []
    current_method_name = None

    for line in r.stdout.split('\n'):
        line_stripped = line.strip()
        raw_line = line.rstrip()

        # 常量池
        if line_stripped.startswith('Constant pool:'):
            in_cp = True
            continue
        if in_cp:
            if line_stripped.startswith('}'):
                in_cp = False
                continue
            m = re.match(r'^\s*#\d+\s*=\s*Utf8\s{2,}(.+)$', line_stripped)
            if m:
                s = m.group(1).strip()
                if s and len(s) > 1:
                    cp_strings_raw.append(s)

        # 方法声明
        m = _JAVAP_METHOD_RE.match(line_stripped)
        if m:
            ret_type, name, params = m.group(1), m.group(2), m.group(3).strip()
            info['methods'][(name, params)] = ret_type
            current_method_name = name

        # 描述符
        if current_method_name and 'descriptor:' in line_stripped:
            desc = line_stripped.split(':', 1)[1].strip()
            ret = info['methods_with_desc'].get(current_method_name, ('', ''))[1]
            info['methods_with_desc'][current_method_name] = (desc, ret)
            # 也更新 methods 条目
            for (mn, mp), rt in info['methods'].items():
                if mn == current_method_name:
                    info['methods_with_desc'][current_method_name] = (desc, rt)
                    break

        # 字段声明
        if '(' not in line_stripped:
            m = _JAVAP_FIELD_RE.match(line_stripped)
            if m:
                ftype, fname = m.group(1), m.group(2)
                if ftype not in ('throws', 'return', 'public', 'private', 'protected',
                                'static', 'final', 'abstract', 'synchronized', 'native',
                                'class', 'interface', 'extends', 'implements', 'package',
                                'import', 'volatile', 'transient', 'strictfp',
                                'SourceFile', 'BootstrapMethods', 'InnerClasses',
                                'Signature', 'Deprecated', 'EnclosingMethod',
                                'RuntimeVisibleAnnotations', 'StackMapTable',
                                'LineNumberTable', 'LocalVariableTable',
                                'LocalVariableTypeTable', 'Exceptions'):
                    info['fields'][fname] = ftype

        # 父类
        if 'super_class' in raw_line:
            m = re.search(r'super_class\s+#\d+\s*//\s*(\S+)', raw_line)
            if m:
                info['super_class'] = m.group(1)

        # this_class
        if 'this_class' in raw_line:
            m = re.search(r'this_class\s+#\d+\s*//\s*(\S+)', raw_line)
            if m:
                info['this_class'] = m.group(1)

    info['strings'] = list(set(cp_strings_raw))
    return info


def index_class_files(root=None):
    """
    索引目录下所有 .class 文件。

    参数:
        root: 搜索根目录 (默认 CLASSES_DIR)

    返回:
        Dict[str, str]: {完全限定类名: 文件路径}
        例如: {'com.corrodinggames.rts.game.units.am': 'path/to/am.class'}
    """
    root = Path(root) if root else CLASSES_DIR
    if not root.is_dir():
        return {}

    index = {}
    for cf in root.rglob("*.class"):
        # 去掉 root 前缀和 .class 后缀, 转为 FQN
        rel = cf.relative_to(root)
        fqn = str(rel).replace(os.sep, '.').replace('.class', '')
        index[fqn] = str(cf)

    return index


def index_jar_classes(jar_path=None):
    """
    索引 JAR 文件中的所有 .class 条目。

    返回:
        Dict[str, Tuple[Path, str]]: {FQN: (jar路径, jar内条目名)}
    """
    jar = Path(jar_path) if jar_path else GAME_LIB
    if not jar.exists():
        return {}

    index = {}
    with zipfile.ZipFile(jar) as zf:
        for name in zf.namelist():
            if name.endswith('.class'):
                fqn = name.replace('/', '.').replace('.class', '')
                index[fqn] = (jar, name)

    return index


def extract_class(jar_entry, output_dir=None):
    """
    从 JAR 中提取 .class 文件到临时目录。

    参数:
        jar_entry: (jar_path, entry_name) — index_jar_classes 的返回格式
        output_dir: 输出目录 (默认 CLASSES_DIR)

    返回:
        str: 提取后的 .class 文件路径, 或 None (失败时)
    """
    jar_path, entry_name = jar_entry
    output_dir = Path(output_dir) if output_dir else CLASSES_DIR

    out_path = output_dir / entry_name.replace('/', os.sep)
    if out_path.exists():
        return str(out_path)

    try:
        out_path.parent.mkdir(parents=True, exist_ok=True)
        with zipfile.ZipFile(jar_path) as zf:
            out_path.write_bytes(zf.read(entry_name))
        return str(out_path)
    except (KeyError, OSError):
        return None


def _parse_constant_pool(data):
    """
    解析 .class 文件常量池 (parse_class_binary / extract_class_refs 共享)。

    返回:
        (cp_utf8, cp_class, pos) 或 None (无效文件)
        cp_utf8[i] = CONSTANT_Utf8 字符串; cp_class[i] = CONSTANT_Class 指向的 utf8 索引;
        pos = 常量池结束后的字节偏移 (access_flags 处)
    """
    if len(data) < 10 or struct.unpack('>I', data[:4])[0] != 0xCAFEBABE:
        return None

    cp_count = struct.unpack('>H', data[8:10])[0] - 1
    pos = 10
    cp_utf8 = [None] * (cp_count + 1)
    cp_class = [None] * (cp_count + 1)

    i = 1
    while i <= cp_count and pos < len(data):
        tag = data[pos]
        pos += 1
        if tag == 1:  # CONSTANT_Utf8
            length = struct.unpack('>H', data[pos:pos+2])[0]
            pos += 2
            cp_utf8[i] = data[pos:pos+length].decode('utf-8', errors='replace')
            pos += length
        elif tag == 7:  # CONSTANT_Class
            cp_class[i] = struct.unpack('>H', data[pos:pos+2])[0]
            pos += 2
        elif tag in (3, 4, 5, 6):  # Integer/Float/Long/Double
            pos += (8 if tag in (5, 6) else 4)
            if tag in (5, 6):
                i += 1
        elif tag == 8:  # CONSTANT_String: 2字节索引 (修正: 原误并入+4组, 导致反序列化漂移)
            pos += 2
        elif tag in (9, 10, 11, 12):  # Fieldref/Methodref/InterfaceMethodref/NameAndType
            pos += 4
        elif tag in (15, 16, 17, 18):  # MethodHandle/MethodType/Dynamic/InvokeDynamic
            pos += (3 if tag == 15 else 2 if tag == 16 else 4)
        i += 1

    return cp_utf8, cp_class, pos


def parse_class_binary(filepath):
    """
    最小 .class 文件解析器 — 读取常量池和方法/字段描述符。

    这是 classdump.py 和 batch_add_v1012_render.py 中 parse_class() 的统一版本。
    两者原来逐字复制了相同的 struct.unpack 常量池解析逻辑。

    返回:
        Dict: {
            'this_class': str,    # 完全限定类名
            'super_class': str,   # 父类名
            'methods': [(name, descriptor), ...],
            'fields': [(name, descriptor), ...],
        }
    """
    with open(filepath, 'rb') as f:
        data = f.read()

    parsed = _parse_constant_pool(data)
    if not parsed:
        return None
    cp_utf8, cp_class, pos = parsed

    # 解析类名 (内部形式 → 点分)
    def class_name(cp_idx):
        if cp_idx and cp_idx < len(cp_class) and cp_class[cp_idx]:
            utf8_idx = cp_class[cp_idx]
            if utf8_idx and utf8_idx < len(cp_utf8) and cp_utf8[utf8_idx]:
                return cp_utf8[utf8_idx].replace('/', '.')
        return None

    # 跳过 access_flags + this_class + super_class
    this_class_idx = struct.unpack('>H', data[pos+2:pos+4])[0]
    super_class_idx = struct.unpack('>H', data[pos+4:pos+6])[0]
    this_class = class_name(this_class_idx) or "Unknown"
    super_class = class_name(super_class_idx) or "java/lang/Object"

    return {
        'this_class': this_class,
        'super_class': super_class,
    }


# 描述符/签名中的类引用模式: L<包/类名>; (要求至少一段包路径, 排除裸 Lint; 这类)
_DESC_CLASS_RE = re.compile(r'L([\w$]+(?:/[\w$]+)+);')


def extract_class_refs(filepath):
    """
    提取 .class 常量池引用的全部类 FQN — 确定性重命名 (R3) 的字节码真相源。
    见 extract_class_refs_from_bytes。
    """
    with open(filepath, 'rb') as f:
        return extract_class_refs_from_bytes(f.read())


def extract_class_refs_from_bytes(data):
    """
    提取 .class 字节的常量池引用类 FQN — 确定性重命名 (R3) 的字节码真相源。

    两个来源缺一不可:
    1. CONSTANT_Class (tag 7) 条目 — 代码中的直接类型引用
    2. 全部 Utf8 串中的描述符/签名 `L<FQN>;` — 泛型/数组/方法描述符中的类
       (实证: GlobalState 的 gameFramework.aa 只出现在签名字符串, 无 Class 条目)

    返回:
        Dict: {
            'this_class': str,     # 本类 FQN (点分)
            'super_class': str,    # 父类 FQN (点分)
            'classes': set[str],   # 引用的全部类 FQN (点分, 含父类/接口, 不含自身)
        } 或 None (无效文件)
    """
    parsed = _parse_constant_pool(data)
    if not parsed:
        return None
    cp_utf8, cp_class, pos = parsed

    # 类名规范化: 去数组前缀/描述符包裹, 斜杠转点
    def norm_name(raw):
        if not raw:
            return None
        raw = raw.lstrip('[')
        if raw.startswith('L') and raw.endswith(';'):
            raw = raw[1:-1]
        return raw.replace('/', '.')

    def class_name(cp_idx):
        if cp_idx and cp_idx < len(cp_class) and cp_class[cp_idx]:
            utf8_idx = cp_class[cp_idx]
            if utf8_idx and utf8_idx < len(cp_utf8) and cp_utf8[utf8_idx]:
                return norm_name(cp_utf8[utf8_idx])
        return None

    # this/super/interfaces (access_flags 2B + this 2B + super 2B + count 2B)
    this_class = class_name(struct.unpack('>H', data[pos+2:pos+4])[0]) or "Unknown"
    super_class = class_name(struct.unpack('>H', data[pos+4:pos+6])[0]) or "java.lang.Object"
    interfaces_count = struct.unpack('>H', data[pos+6:pos+8])[0]
    q = pos + 8
    refs = set()
    if super_class != 'java.lang.Object':
        refs.add(super_class)
    for _ in range(interfaces_count):
        name = class_name(struct.unpack('>H', data[q:q+2])[0])
        q += 2
        if name:
            refs.add(name)

    # 来源1: 全部 CONSTANT_Class 条目
    # 注意: cp_class[i] 存的是 utf8 索引, 直接查 cp_utf8 (class_name 期望条目号, 勿混用)
    for idx in cp_class:
        if idx and idx < len(cp_utf8) and cp_utf8[idx]:
            refs.add(norm_name(cp_utf8[idx]))

    # 来源2: 全部 Utf8 描述符/签名中的 L<FQN>;
    for s in cp_utf8:
        if s and '/' in s:
            for m in _DESC_CLASS_RE.finditer(s):
                refs.add(m.group(1).replace('/', '.'))

    refs.discard(this_class)  # 自身引用不算
    return {
        'this_class': this_class,
        'super_class': super_class,
        'classes': refs,
    }


def cleanup_ascii_temp():
    """清理 ASCII 临时目录 (P0.1)。在会话结束时调用。"""
    global _ASCII_TEMP_DIR
    if _ASCII_TEMP_DIR and os.path.isdir(_ASCII_TEMP_DIR):
        try:
            shutil.rmtree(_ASCII_TEMP_DIR)
        except OSError:
            pass
        _ASCII_TEMP_DIR = None


if __name__ == "__main__":
    print("rwlib.bytecode — 自检 (P0.1 CJK-safe)")
    class_files = index_class_files()
    print(f"  .class 文件索引: {len(class_files)} 个类")
    jar_classes = index_jar_classes()
    print(f"  JAR 类索引: {len(jar_classes)} 个类")
    if class_files:
        test = list(class_files.values())[0]
        # 测试 CJK 安全方法
        methods = get_methods(test)
        fields = get_fields(test)
        strings = get_constant_pool_strings(test)
        print(f"  测试类 ({Path(test).name}): {len(methods)} 方法, {len(fields)} 字段, {len(strings)} 字符串常量")
    cleanup_ascii_temp()
