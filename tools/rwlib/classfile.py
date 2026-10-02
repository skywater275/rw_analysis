"""
rwlib.classfile — 纯 Python 的 .class 解析器（跨版本对照专用）

与 rwlib.bytecode 的分工:
    bytecode.py  — 面向 javap 子进程的封装（人读输出）
    classfile.py — 直接解二进制（机器可比的规范化结构），不依赖 JDK

为什么必须自研:
    跨版本（v1.09 ↔ v1.15）对照需要「与常量池编号无关」的可比指纹。
    直接比较 Code 属性原始字节会因常量池重排而大面积假阴性，
    因此本模块把每条指令的 CP 引用**解析成符号**，再序列化成规范形
    (canonical form)。规范形相同 ⇒ 语义等价（在 JVM 指令层面）。

对外 API:
    cf = ClassFile.load(bytes)        # 解析单个 .class
    ClassFile.load_jar(path)          # → {内部名: ClassFile}
    cf.methods[i].canonical()         # 规范化字节码（bytes）
    cf.fingerprint()                  # 类级指纹 dict

规范形编码（自描述、无歧义、可哈希）:
    - 每条指令输出 "助记符 操作数…" 的紧凑文本再 utf-8 编码
    - 引用类操作数输出解析后的 类.名:描述符；常量输出其真值
    - 分支偏移统一换算成「目标指令序号」（消除指令长度差异带来的偏移漂移）

Usage:
    python -m rwlib.classfile <jar-or-class> [--dump 类名]
"""
from __future__ import annotations

import hashlib
import struct
import sys
import zipfile
from pathlib import Path

# ── 常量池 tag ────────────────────────────────────────────────────────
CONSTANT_Utf8 = 1
CONSTANT_Integer = 3
CONSTANT_Float = 4
CONSTANT_Long = 5
CONSTANT_Double = 6
CONSTANT_Class = 7
CONSTANT_String = 8
CONSTANT_Fieldref = 9
CONSTANT_Methodref = 10
CONSTANT_InterfaceMethodref = 11
CONSTANT_NameAndType = 12
CONSTANT_MethodHandle = 15
CONSTANT_MethodType = 16
CONSTANT_Dynamic = 17
CONSTANT_InvokeDynamic = 18
CONSTANT_Module = 19
CONSTANT_Package = 20

# ── 操作码长度表（操作数字节数；-1 = 变长，单独处理） ─────────────────
_OPLEN = {
    0x00: 0, 0x01: 0, 0x02: 0, 0x03: 0, 0x04: 0, 0x05: 0, 0x06: 0, 0x07: 0,
    0x08: 0, 0x09: 0, 0x0a: 0, 0x0b: 0, 0x0c: 0, 0x0d: 0, 0x0e: 0, 0x0f: 0,
    0x10: 1, 0x11: 2, 0x12: 1, 0x13: 2, 0x14: 2,
    0x15: 1, 0x16: 1, 0x17: 1, 0x18: 1, 0x19: 1,
    0x1a: 0, 0x1b: 0, 0x1c: 0, 0x1d: 0, 0x1e: 0, 0x1f: 0, 0x20: 0, 0x21: 0,
    0x22: 0, 0x23: 0, 0x24: 0, 0x25: 0, 0x26: 0, 0x27: 0, 0x28: 0, 0x29: 0,
    0x2a: 0, 0x2b: 0, 0x2c: 0, 0x2d: 0, 0x2e: 0, 0x2f: 0, 0x30: 0, 0x31: 0,
    0x32: 0, 0x33: 0, 0x34: 0, 0x35: 0, 0x36: 1, 0x37: 1, 0x38: 1, 0x39: 1,
    0x3a: 1, 0x3b: 0, 0x3c: 0, 0x3d: 0, 0x3e: 0, 0x3f: 0, 0x40: 0, 0x41: 0,
    0x42: 0, 0x43: 0, 0x44: 0, 0x45: 0, 0x46: 0, 0x47: 0, 0x48: 0, 0x49: 0,
    0x4a: 0, 0x4b: 0, 0x4c: 0, 0x4d: 0, 0x4e: 0, 0x4f: 0, 0x50: 0, 0x51: 0,
    0x52: 0, 0x53: 0, 0x54: 0, 0x55: 0, 0x56: 0, 0x57: 0, 0x58: 0, 0x59: 0,
    0x5a: 0, 0x5b: 0, 0x5c: 0, 0x5d: 0, 0x5e: 0, 0x5f: 0, 0x60: 0, 0x61: 0,
    0x62: 0, 0x63: 0, 0x64: 0, 0x65: 0, 0x66: 0, 0x67: 0, 0x68: 0, 0x69: 0,
    0x6a: 0, 0x6b: 0, 0x6c: 0, 0x6d: 0, 0x6e: 0, 0x6f: 0, 0x70: 0, 0x71: 0,
    0x72: 0, 0x73: 0, 0x74: 0, 0x75: 0, 0x76: 0, 0x77: 0, 0x78: 0, 0x79: 0,
    0x7a: 0, 0x7b: 0, 0x7c: 0, 0x7d: 0, 0x7e: 0, 0x7f: 0, 0x80: 0, 0x81: 0,
    0x82: 0, 0x83: 0, 0x84: 2, 0x85: 0, 0x86: 0, 0x87: 0, 0x88: 0, 0x89: 0,
    0x8a: 0, 0x8b: 0, 0x8c: 0, 0x8d: 0, 0x8e: 0, 0x8f: 0, 0x90: 0, 0x91: 0,
    0x92: 0, 0x93: 0, 0x94: 0, 0x95: 0, 0x96: 0, 0x97: 0, 0x98: 0,
    0x99: 2, 0x9a: 2, 0x9b: 2, 0x9c: 2, 0x9d: 2, 0x9e: 2,
    0x9f: 2, 0xa0: 2, 0xa1: 2, 0xa2: 2, 0xa3: 2, 0xa4: 2, 0xa5: 2, 0xa6: 2,
    0xa7: 2, 0xa8: 2, 0xa9: 1,
    0xaa: -1, 0xab: -1, 0xac: 0, 0xad: 0, 0xae: 0, 0xaf: 0, 0xb0: 0, 0xb1: 0,
    0xb2: 2, 0xb3: 2, 0xb4: 2, 0xb5: 2, 0xb6: 2, 0xb7: 2, 0xb8: 2, 0xb9: 4,
    0xba: 4, 0xbb: 2, 0xbc: 1, 0xbd: 2, 0xbe: 0, 0xbf: 0,
    0xc0: 2, 0xc1: 2, 0xc2: 0, 0xc3: 0, 0xc4: -2, 0xc5: 3, 0xc6: 2, 0xc7: 2,
    0xc8: 4, 0xc9: 4, 0xca: 0,
}

_MNEMONIC = {
    0x00: 'nop', 0x01: 'aconst_null', 0x02: 'iconst_m1', 0x03: 'iconst_0',
    0x04: 'iconst_1', 0x05: 'iconst_2', 0x06: 'iconst_3', 0x07: 'iconst_4',
    0x08: 'iconst_5', 0x09: 'lconst_0', 0x0a: 'lconst_1', 0x0b: 'fconst_0',
    0x0c: 'fconst_1', 0x0d: 'fconst_2', 0x0e: 'dconst_0', 0x0f: 'dconst_1',
    0x10: 'bipush', 0x11: 'sipush', 0x12: 'ldc', 0x13: 'ldc_w', 0x14: 'ldc2_w',
    0x15: 'iload', 0x16: 'lload', 0x17: 'fload', 0x18: 'dload', 0x19: 'aload',
    0x1a: 'iload_0', 0x1b: 'iload_1', 0x1c: 'iload_2', 0x1d: 'iload_3',
    0x1e: 'lload_0', 0x1f: 'lload_1', 0x20: 'lload_2', 0x21: 'lload_3',
    0x22: 'fload_0', 0x23: 'fload_1', 0x24: 'fload_2', 0x25: 'fload_3',
    0x26: 'dload_0', 0x27: 'dload_1', 0x28: 'dload_2', 0x29: 'dload_3',
    0x2a: 'aload_0', 0x2b: 'aload_1', 0x2c: 'aload_2', 0x2d: 'aload_3',
    0x2e: 'iaload', 0x2f: 'laload', 0x30: 'faload', 0x31: 'daload',
    0x32: 'aaload', 0x33: 'baload', 0x34: 'caload', 0x35: 'saload',
    0x36: 'istore', 0x37: 'lstore', 0x38: 'fstore', 0x39: 'dstore',
    0x3a: 'astore', 0x3b: 'istore_0', 0x3c: 'istore_1', 0x3d: 'istore_2',
    0x3e: 'istore_3', 0x3f: 'lstore_0', 0x40: 'lstore_1', 0x41: 'lstore_2',
    0x42: 'lstore_3', 0x43: 'fstore_0', 0x44: 'fstore_1', 0x45: 'fstore_2',
    0x46: 'fstore_3', 0x47: 'dstore_0', 0x48: 'dstore_1', 0x49: 'dstore_2',
    0x4a: 'dstore_3', 0x4b: 'astore_0', 0x4c: 'astore_1', 0x4d: 'astore_2',
    0x4e: 'astore_3', 0x4f: 'iastore', 0x50: 'lastore', 0x51: 'fastore',
    0x52: 'dastore', 0x53: 'aastore', 0x54: 'bastore', 0x55: 'castore',
    0x56: 'sastore', 0x57: 'pop', 0x58: 'pop2', 0x59: 'dup', 0x5a: 'dup_x1',
    0x5b: 'dup_x2', 0x5c: 'dup2', 0x5d: 'dup2_x1', 0x5e: 'dup2_x2', 0x5f: 'swap',
    0x60: 'iadd', 0x61: 'ladd', 0x62: 'fadd', 0x63: 'dadd', 0x64: 'isub',
    0x65: 'lsub', 0x66: 'fsub', 0x67: 'dsub', 0x68: 'imul', 0x69: 'lmul',
    0x6a: 'fmul', 0x6b: 'dmul', 0x6c: 'idiv', 0x6d: 'ldiv', 0x6e: 'fdiv',
    0x6f: 'ddiv', 0x70: 'irem', 0x71: 'lrem', 0x72: 'frem', 0x73: 'drem',
    0x74: 'ineg', 0x75: 'lneg', 0x76: 'fneg', 0x77: 'dneg', 0x78: 'ishl',
    0x79: 'lshl', 0x7a: 'ishr', 0x7b: 'lshr', 0x7c: 'iushr', 0x7d: 'lushr',
    0x7e: 'iand', 0x7f: 'land', 0x80: 'ior', 0x81: 'lor', 0x82: 'ixor',
    0x83: 'lxor', 0x84: 'iinc', 0x85: 'i2l', 0x86: 'i2f', 0x87: 'i2d',
    0x88: 'l2i', 0x89: 'l2f', 0x8a: 'l2d', 0x8b: 'f2i', 0x8c: 'f2l',
    0x8d: 'f2d', 0x8e: 'd2i', 0x8f: 'd2l', 0x90: 'd2f', 0x91: 'i2b',
    0x92: 'i2c', 0x93: 'i2s', 0x94: 'lcmp', 0x95: 'fcmpl', 0x96: 'fcmpg',
    0x97: 'dcmpl', 0x98: 'dcmpg', 0x99: 'ifeq', 0x9a: 'ifne', 0x9b: 'iflt',
    0x9c: 'ifge', 0x9d: 'ifgt', 0x9e: 'ifle', 0x9f: 'if_icmpeq',
    0xa0: 'if_icmpne', 0xa1: 'if_icmplt', 0xa2: 'if_icmpge', 0xa3: 'if_icmpgt',
    0xa4: 'if_icmple', 0xa5: 'if_acmpeq', 0xa6: 'if_acmpne', 0xa7: 'goto',
    0xa8: 'jsr', 0xa9: 'ret', 0xaa: 'tableswitch', 0xab: 'lookupswitch',
    0xac: 'ireturn', 0xad: 'lreturn', 0xae: 'freturn', 0xaf: 'dreturn',
    0xb0: 'areturn', 0xb1: 'return', 0xb2: 'getstatic', 0xb3: 'putstatic',
    0xb4: 'getfield', 0xb5: 'putfield', 0xb6: 'invokevirtual',
    0xb7: 'invokespecial', 0xb8: 'invokestatic', 0xb9: 'invokeinterface',
    0xba: 'invokedynamic', 0xbb: 'new', 0xbc: 'newarray', 0xbd: 'anewarray',
    0xbe: 'arraylength', 0xbf: 'athrow', 0xc0: 'checkcast', 0xc1: 'instanceof',
    0xc2: 'monitorenter', 0xc3: 'monitorexit', 0xc4: 'wide', 0xc5: 'multianewarray',
    0xc6: 'ifnull', 0xc7: 'ifnonnull', 0xc8: 'goto_w', 0xc9: 'jsr_w',
    0xca: 'breakpoint',
}


class ParseError(Exception):
    """字节码解析失败。"""


class _Reader:
    """大端字节流游标。"""

    __slots__ = ('d', 'o')

    def __init__(self, data: bytes):
        self.d = data
        self.o = 0

    def u1(self) -> int:
        v = self.d[self.o]
        self.o += 1
        return v

    def u2(self) -> int:
        v = struct.unpack_from('>H', self.d, self.o)[0]
        self.o += 2
        return v

    def u4(self) -> int:
        v = struct.unpack_from('>I', self.d, self.o)[0]
        self.o += 4
        return v

    def take(self, n: int) -> bytes:
        v = self.d[self.o:self.o + n]
        self.o += n
        return v

    def skip(self, n: int) -> None:
        self.o += n


class Method:
    """单个方法（含规范化字节码）。"""

    __slots__ = ('name', 'desc', 'access', 'code', 'max_stack', 'max_locals',
                 'exceptions', 'owner', '_canon', '_lits', '_refs', '_shape')

    def __init__(self, name, desc, access, code, max_stack, max_locals, exceptions, owner):
        self.name = name
        self.desc = desc
        self.access = access
        self.code = code            # 原始 code 字节（含 CP 编号）
        self.max_stack = max_stack
        self.max_locals = max_locals
        self.exceptions = exceptions
        self.owner = owner
        self._canon = None
        self._lits = None
        self._refs = None
        self._shape = None

    @property
    def key(self) -> str:
        """成员键：名 + 描述符（跨版本可比的「槽位」标识）。"""
        return '%s%s' % (self.name, self.desc)

    def canonical(self) -> bytes:
        """规范化字节码（CP 引用 → 符号；分支偏移 → 指令序号）。"""
        if self._canon is None:
            self._canon = _canonicalize(self, self.owner)
        return self._canon

    def canon_sha(self) -> str:
        return hashlib.sha1(self.canonical()).hexdigest()

    def shape(self) -> bytes:
        """版本无关的「形状」指纹：保留字面量/数值/控制流，丢弃类名与成员名。

        用途：跨版本对齐时，两个类的同名方法即使宿主类被重命名也能配对。
        """
        if self._shape is None:
            self._shape = _shapize(self, self.owner)
        return self._shape

    def shape_sha(self) -> str:
        return hashlib.sha1(self.shape()).hexdigest()

    def literals(self) -> frozenset:
        """本方法 ldc 引用的字符串字面量。"""
        self._scan()
        return self._lits

    def refs(self) -> frozenset:
        """本方法引用的 (类, 名, 描述符) 三元组（方法/字段引用）。"""
        self._scan()
        return self._refs

    def class_refs(self) -> frozenset:
        """本方法引用的类（含 new/checkcast/字段与方法宿主）。"""
        return frozenset(r[0] for r in self.refs() if r[0])

    def _scan(self):
        if self._refs is not None:
            return
        lits, refs = set(), set()
        cp = self.owner.cp
        for op, ops in _walk(self.code):
            if op in (0x12, 0x13, 0x14):
                v = cp[ops[0]]
                if isinstance(v, str) and v.startswith('\x00S'):
                    lits.add(v[2:])
            elif op in (0xb2, 0xb3, 0xb4, 0xb5, 0xb6, 0xb7, 0xb8, 0xb9):
                v = cp[ops[0]]
                if isinstance(v, tuple) and v[0] == 'ref':
                    refs.add((v[1], v[2], v[3]))
            elif op in (0xbb, 0xc0, 0xc1, 0xbd):
                v = cp[ops[0] if op != 0xbd else ops[0]]
                if isinstance(v, str) and v.startswith('\x00C'):
                    refs.add((v[2:], '', ''))
            elif op == 0xc5:
                v = cp[ops[0]]
                if isinstance(v, str) and v.startswith('\x00C'):
                    refs.add((v[2:], '', ''))
        self._lits = frozenset(lits)
        self._refs = frozenset(refs)


class Field:
    __slots__ = ('name', 'desc', 'access', 'owner')

    def __init__(self, name, desc, access, owner):
        self.name = name
        self.desc = desc
        self.access = access
        self.owner = owner

    @property
    def key(self) -> str:
        return '%s:%s' % (self.name, self.desc)


class ClassFile:
    """解析后的单个 .class。"""

    def __init__(self, internal_name: str):
        self.name = internal_name          # 内部名，形如 a/b/C（无 .class）
        self.access = 0
        self.super_name = None
        self.interfaces = []
        self.fields = []
        self.methods = []
        self.class_refs = set()            # 常量池直接引用的类
        self.lits_all = set()              # 类级字面量并集
        self.attrs = {}                    # 类级属性名 → 长度
        self.cp = []
        self.major = 0
        self.minor = 0

    # ── 便捷视图 ────────────────────────────────────────────────────
    @property
    def pkg(self) -> str:
        return self.name.rsplit('/', 1)[0] if '/' in self.name else ''

    @property
    def simple(self) -> str:
        return self.name.rsplit('/', 1)[1]

    @property
    def outer(self):
        """外部类内部名（若为内部类），否则 None。"""
        i = self.simple.find('$')
        return '%s/%s' % (self.pkg, self.simple[:i]) if i > 0 else None

    def method_map(self):
        return {m.key: m for m in self.methods}

    def field_keys(self):
        return [f.key for f in self.fields]

    def code_bytes(self) -> int:
        return sum(len(m.code) for m in self.methods)

    def fingerprint(self) -> dict:
        """类级指纹（跨版本可比，不含混淆名）。"""
        return {
            'super': self.super_name,
            'n_methods': len(self.methods),
            'n_fields': len(self.fields),
            'n_ifaces': len(self.interfaces),
            'code_bytes': self.code_bytes(),
            'method_descs': sorted(m.desc for m in self.methods),
            'field_descs': sorted(f.desc for f in self.fields),
            'lits': sorted(self.lits_all),
            'ref_classes': sorted(self.class_refs),
            'major': self.major,
        }

    # ── 解析 ────────────────────────────────────────────────────────
    @staticmethod
    def load(data: bytes) -> 'ClassFile':
        r = _Reader(data)
        if r.u4() != 0xCAFEBABE:
            raise ParseError('not a class file')
        r.u2()  # minor
        r.u2()  # major
        cp_count = r.u2()
        raw = [None] * cp_count
        i = 1
        while i < cp_count:
            tag = r.u1()
            if tag == CONSTANT_Utf8:
                n = r.u2()
                raw[i] = r.take(n).decode('utf-8', 'replace')
            elif tag == CONSTANT_Integer:
                raw[i] = struct.unpack('>i', r.take(4))[0]
            elif tag == CONSTANT_Float:
                raw[i] = struct.unpack('>f', r.take(4))[0]
            elif tag == CONSTANT_Long:
                raw[i] = struct.unpack('>q', r.take(8))[0]
                i += 1
            elif tag == CONSTANT_Double:
                raw[i] = struct.unpack('>d', r.take(8))[0]
                i += 1
            elif tag == CONSTANT_Class:
                raw[i] = ('cls', r.u2())
            elif tag == CONSTANT_String:
                raw[i] = ('str', r.u2())
            elif tag in (CONSTANT_Fieldref, CONSTANT_Methodref, CONSTANT_InterfaceMethodref):
                raw[i] = ('ref', r.u2(), r.u2())
            elif tag == CONSTANT_NameAndType:
                raw[i] = ('nt', r.u2(), r.u2())
            elif tag == CONSTANT_MethodHandle:
                r.u1()
                r.u2()
                raw[i] = ('mh',)
            elif tag == CONSTANT_MethodType:
                r.u2()
                raw[i] = ('mt',)
            elif tag in (CONSTANT_Dynamic, CONSTANT_InvokeDynamic):
                r.u2()
                r.u2()
                raw[i] = ('indy',)
            elif tag in (CONSTANT_Module, CONSTANT_Package):
                r.u2()
                raw[i] = ('mod',)
            else:
                raise ParseError('bad cp tag %d at %d' % (tag, i))
            i += 1

        def utf(k):
            v = raw[k] if 0 < k < cp_count else None
            return v if isinstance(v, str) else None

        # 解析出统一的 cp 视图：str | int | float | '\x00C名字' | '\x00S内容' | ('ref',cls,nm,desc) | ('nt',..)
        cp = [None] * cp_count
        for k in range(1, cp_count):
            v = raw[k]
            if v is None:
                continue
            if isinstance(v, (str, int, float)):
                cp[k] = v
            elif v[0] == 'cls':
                cp[k] = '\x00C' + (utf(v[1]) or '?')
            elif v[0] == 'str':
                cp[k] = '\x00S' + (utf(v[1]) or '')
            elif v[0] == 'ref':
                cn = utf(v[1]) or '?'
                nt = raw[v[2]] if v[2] < cp_count else None
                if isinstance(nt, tuple) and nt[0] == 'nt':
                    cp[k] = ('ref', cn, utf(nt[1]) or '?', utf(nt[2]) or '?')
                else:
                    cp[k] = ('ref', cn, '?', '?')
            elif v[0] == 'nt':
                cp[k] = ('nt', utf(v[1]) or '?', utf(v[2]) or '?')
            else:
                cp[k] = v

        def attr_table(rr):
            n = rr.u2()
            out = []
            for _ in range(n):
                ni = rr.u2()
                ln = rr.u4()
                out.append((utf(ni), rr.o, ln))
                rr.skip(ln)
            return out

        access = r.u2()
        this_i = r.u2()
        super_i = r.u2()
        this_name = utf(this_i) or '?'
        # this_class 指向 CONSTANT_Class → 其名字
        ci = raw[this_i]
        if isinstance(ci, tuple) and ci[0] == 'cls':
            this_name = utf(ci[1]) or this_name
        cf = ClassFile(this_name.replace('.', '/'))
        cf.cp = cp
        cf.access = access
        si = raw[super_i] if 0 < super_i < cp_count else None
        cf.super_name = (utf(si[1]).replace('.', '/')
                         if isinstance(si, tuple) and si[0] == 'cls' else None)
        for _ in range(r.u2()):
            ii = r.u2()
            iv = raw[ii]
            if isinstance(iv, tuple) and iv[0] == 'cls':
                cf.interfaces.append((utf(iv[1]) or '?').replace('.', '/'))
        for _ in range(r.u2()):
            fa = r.u2()
            fn = utf(r.u2()) or '?'
            fd = utf(r.u2()) or '?'
            attr_table(r)
            cf.fields.append(Field(fn, fd, fa, cf))
        for _ in range(r.u2()):
            ma = r.u2()
            mn = utf(r.u2()) or '?'
            md = utf(r.u2()) or '?'
            code, ms, ml, exc = b'', 0, 0, []
            for an, ao, al in attr_table(r):
                if an == 'Code':
                    save = r.o
                    r.o = ao
                    ms = r.u2()
                    ml = r.u2()
                    clen = r.u4()
                    code = r.d[r.o:r.o + clen]
                    r.o += clen
                    ne = r.u2()
                    for _e in range(ne):
                        exc.append((r.u2(), r.u2(), r.u2(), r.u2()))
                    r.o = save
            cf.methods.append(Method(mn, md, ma, code, ms, ml, exc, cf))
        for an, _ao, al in attr_table(r):
            cf.attrs[an] = cf.attrs.get(an, 0) + al

        # 汇总常量池引用与字面量
        for v in cp:
            if isinstance(v, str) and v.startswith('\x00C'):
                cf.class_refs.add(v[2:])
            elif isinstance(v, str) and v.startswith('\x00S'):
                cf.lits_all.add(v[2:])
            elif isinstance(v, tuple) and v[0] == 'ref':
                cf.class_refs.add(v[1])
        # 方法/字段描述符里出现的类也算引用
        for m in cf.methods:
            cf.class_refs.update(_desc_classes(m.desc))
        for f in cf.fields:
            cf.class_refs.update(_desc_classes(f.desc))
        cf.class_refs.discard(cf.name)
        return cf

    @staticmethod
    def load_jar(path) -> dict:
        out = {}
        with zipfile.ZipFile(path) as z:
            for info in z.infolist():
                if info.filename.endswith('.class'):
                    try:
                        c = ClassFile.load(z.read(info.filename))
                    except Exception:
                        continue
                    out[c.name] = c
        return out

    def source_file(self):
        return self.attrs.get('SourceFile')

    def dump(self, show_code=True):
        print('== %s  super=%s  ifaces=%s' % (self.name, self.super_name, self.interfaces))
        print('   fields: %d  methods: %d  code_bytes: %d' %
              (len(self.fields), len(self.methods), self.code_bytes()))
        for f in self.fields:
            print('   F %-30s %s' % (f.key, hex(f.access)))
        for m in self.methods:
            print('   M %-40s acc=%s code=%dB' % (m.key, hex(m.access), len(m.code)))
            if show_code:
                for op, ops in _walk(m.code):
                    nm = _MNEMONIC.get(op, '?%02x' % op)
                    if op == 0x12 and 0 < ops[0] < len(self.cp):
                        print('        %s %r' % (nm, self.cp[ops[0]]))
                    elif op in (0x13, 0x14, 0xb2, 0xb3, 0xb4, 0xb5, 0xb6, 0xb7, 0xb8, 0xb9,
                                0xbb, 0xc0, 0xc1, 0xbd, 0xc5) and ops and 0 < ops[0] < len(self.cp):
                        print('        %s %r' % (nm, self.cp[ops[0]]))
                    else:
                        print('        %s %s' % (nm, ops if ops else ''))


def _desc_shape(desc: str) -> str:
    """描述符形状化：L<任意类>; → L;，保留数组维度与基本类型。"""
    out, i = [], 0
    while i < len(desc):
        c = desc[i]
        if c == 'L':
            j = desc.find(';', i)
            if j < 0:
                out.append('L;')
                break
            out.append('L;')
            i = j + 1
        else:
            out.append(c)
            i += 1
    return ''.join(out)


def _shapize(m: Method, owner: ClassFile) -> bytes:
    """版本无关形状指纹：保留字面量/数值/控制流/描述符形状，丢弃一切名字。"""
    cp = owner.cp
    n = len(cp)

    def sym(idx):
        idx &= 0xFFFF
        if not (0 < idx < n):
            return '?'
        v = cp[idx]
        if isinstance(v, str):
            if v.startswith('\x00C'):
                return 'C'
            if v.startswith('\x00S'):
                return 'S:' + v[2:]
            return 'U:' + v
        if isinstance(v, tuple) and v[0] == 'ref':
            return 'R:' + _desc_shape(v[3])
        if isinstance(v, tuple) and v[0] == 'nt':
            return 'NT'
        return repr(v)

    pcs = _instr_pcs(m.code)
    pc2idx = {pc: k for k, pc in enumerate(pcs)}

    def tgt(pc, off):
        t = pc + off
        return str(pc2idx[t]) if t in pc2idx else 'abs%d' % t

    out = []
    for k, (op, ops) in enumerate(_walk(m.code)):
        pc = pcs[k]
        nm = _MNEMONIC.get(op, '?%02x' % op)
        if op in _CPREF:
            out.append('%s %s' % (nm, sym(ops[0])))
        elif op in _BRANCH:
            out.append('%s -> #%s' % (nm, tgt(pc, ops[0])))
        elif op == 0xaa:
            default, low, high, offs = ops
            out.append('tableswitch %d..%d [%s] default=#%s' % (
                low, high, ','.join('#' + tgt(pc, o) for o in (offs or ())), tgt(pc, default)))
        elif op == 0xab:
            default, pairs = ops
            body = ','.join('%d:#%s' % (kk, tgt(pc, oo)) for kk, oo in pairs)
            out.append('lookupswitch [%s] default=#%s' % (body, tgt(pc, default)))
        elif op == 0xc4:
            out.append('wide %s' % (ops,))
        else:
            out.append('%s %s' % (nm, ' '.join(str(x) for x in ops)) if ops else nm)
    return '\n'.join(out).encode('utf-8')



def _desc_classes(desc: str):
    """从 JVM 描述符中抽出引用到的类名。"""
    out, i = [], 0
    while i < len(desc):
        c = desc[i]
        if c == 'L':
            j = desc.find(';', i)
            if j < 0:
                break
            out.append(desc[i + 1:j])
            i = j + 1
        else:
            i += 1
    return out


# ── 指令流 ────────────────────────────────────────────────────────────
def _walk(code: bytes):
    """遍历 (opcode, operands) 序列；变长指令已展开。"""
    i, n = 0, len(code)
    while i < n:
        op = code[i]
        ln = _OPLEN.get(op)
        if ln is None:
            raise ParseError('unknown opcode 0x%02x at %d' % (op, i))
        if ln == -1:
            if op == 0xaa:  # tableswitch
                p = (i + 4) & ~3
                if p + 12 > n:
                    raise ParseError('truncated tableswitch')
                default = struct.unpack_from('>i', code, p)[0]
                low = struct.unpack_from('>i', code, p + 4)[0]
                high = struct.unpack_from('>i', code, p + 8)[0]
                cnt = high - low + 1
                p += 12 + 4 * cnt
                yield op, (default, low, high,
                           struct.unpack_from('>%di' % cnt, code, p - 4 * cnt) if cnt > 0 else ())
                i = p
            else:  # lookupswitch
                p = (i + 4) & ~3
                default = struct.unpack_from('>i', code, p)[0]
                npairs = struct.unpack_from('>i', code, p + 4)[0]
                p += 8
                pairs = []
                for _ in range(npairs):
                    pairs.append((struct.unpack_from('>i', code, p)[0],
                                  struct.unpack_from('>i', code, p + 4)[0]))
                    p += 8
                yield op, (default, tuple(pairs))
                i = p
        elif ln == -2:  # wide
            sub = code[i + 1]
            if sub == 0x84:
                yield op, (sub, struct.unpack_from('>H', code, i + 2)[0],
                           struct.unpack_from('>h', code, i + 4)[0])
                i += 6
            else:
                yield op, (sub, struct.unpack_from('>H', code, i + 2)[0])
                i += 4
        else:
            yield op, _operands(code, i + 1, ln, op)
            i += 1 + ln


def _operands(code, o, ln, op):
    if ln == 0:
        return ()
    if ln == 1:
        return (code[o],)
    if ln == 2:
        return (struct.unpack_from('>h', code, o)[0],)
    if ln == 3:
        return (struct.unpack_from('>H', code, o)[0], code[o + 2])
    if ln == 4:
        if op == 0xb9:
            return (struct.unpack_from('>H', code, o)[0], code[o + 2], code[o + 3])
        if op == 0xba:
            return (struct.unpack_from('>H', code, o)[0],)
        return (struct.unpack_from('>i', code, o)[0],)
    raise ParseError('bad operand len %d' % ln)


_BRANCH = frozenset([0x99, 0x9a, 0x9b, 0x9c, 0x9d, 0x9e, 0x9f, 0xa0, 0xa1, 0xa2,
                     0xa3, 0xa4, 0xa5, 0xa6, 0xa7, 0xa8, 0xc6, 0xc7, 0xc8, 0xc9])
_CPREF = frozenset([0x12, 0x13, 0x14, 0xb2, 0xb3, 0xb4, 0xb5, 0xb6, 0xb7, 0xb8,
                    0xb9, 0xbb, 0xc0, 0xc1, 0xbd, 0xc5])


def _instr_pcs(code: bytes):
    """返回每条指令的起始 pc 列表（与 _walk 顺序严格一致）。"""
    pcs, i, n = [], 0, len(code)
    while i < n:
        pcs.append(i)
        i += _ilen(code, i)
    return pcs


def _ilen(code, i):
    op = code[i]
    ln = _OPLEN.get(op)
    if ln is None:
        raise ParseError('unknown opcode 0x%02x at %d' % (op, i))
    if ln == -1:
        if op == 0xaa:
            p = (i + 4) & ~3
            low = struct.unpack_from('>i', code, p + 4)[0]
            high = struct.unpack_from('>i', code, p + 8)[0]
            return (p + 12 + 4 * (high - low + 1)) - i
        p = (i + 4) & ~3
        npairs = struct.unpack_from('>i', code, p + 4)[0]
        return (p + 8 + 8 * npairs) - i
    if ln == -2:
        return 6 if code[i + 1] == 0x84 else 4
    return 1 + ln


def _canonicalize(m: Method, owner: ClassFile) -> bytes:
    """把方法字节码转成与常量池编号、指令偏移都无关的规范形。

    - CP 引用 → 解析后的符号（C:/S:/R:）
    - 分支目标 → **指令序号**（`-> #k`），消除指令长度差异造成的偏移漂移
    - 数值常量 → 真值
    """
    cp = owner.cp
    n = len(cp)

    def sym(idx):
        idx &= 0xFFFF
        if not (0 < idx < n):
            return '?'
        v = cp[idx]
        if isinstance(v, str):
            if v.startswith('\x00C'):
                return 'C:' + v[2:]
            if v.startswith('\x00S'):
                return 'S:' + v[2:]
            return 'U:' + v
        if isinstance(v, tuple) and v[0] == 'ref':
            return 'R:%s.%s%s' % (v[1], v[2], v[3])
        if isinstance(v, tuple) and v[0] == 'nt':
            return 'NT:%s%s' % (v[1], v[2])
        return repr(v)

    pcs = _instr_pcs(m.code)
    pc2idx = {pc: k for k, pc in enumerate(pcs)}

    def tgt(pc, off):
        """绝对目标 pc → 指令序号（目标必须落在指令边界上）。"""
        t = pc + off
        if t in pc2idx:
            return str(pc2idx[t])
        return 'abs%d' % t   # 异常情况：保留绝对值，便于暴露解析问题

    out = []
    for k, (op, ops) in enumerate(_walk(m.code)):
        pc = pcs[k]
        nm = _MNEMONIC.get(op, '?%02x' % op)
        if op in _CPREF:
            out.append('%s %s' % (nm, sym(ops[0])))
        elif op in _BRANCH:
            out.append('%s -> #%s' % (nm, tgt(pc, ops[0])))
        elif op == 0xaa:
            default, low, high, offs = ops
            tgts = ','.join('#' + tgt(pc, o) for o in (offs or ()))
            out.append('tableswitch %d..%d [%s] default=#%s' % (low, high, tgts, tgt(pc, default)))
        elif op == 0xab:
            default, pairs = ops
            body = ','.join('%d:#%s' % (kk, tgt(pc, oo)) for kk, oo in pairs)
            out.append('lookupswitch [%s] default=#%s' % (body, tgt(pc, default)))
        elif op == 0xc4:
            out.append('wide %s' % (ops,))
        else:
            out.append('%s %s' % (nm, ' '.join(str(x) for x in ops)) if ops else nm)
    return '\n'.join(out).encode('utf-8')


# ── CLI ───────────────────────────────────────────────────────────────
def main():
    import argparse
    ap = argparse.ArgumentParser(description='rwlib.classfile — .class 解析/规范化')
    ap.add_argument('path', help='jar 或 .class 路径')
    ap.add_argument('--dump', default='', help='打印指定类（内部名）')
    ap.add_argument('--stats', action='store_true', help='打印 jar 统计')
    a = ap.parse_args()
    p = Path(a.path)
    if p.suffix == '.jar':
        d = ClassFile.load_jar(p)
        print('类数: %d' % len(d))
        if a.stats:
            tot_m = sum(len(c.methods) for c in d.values())
            tot_f = sum(len(c.fields) for c in d.values())
            tot_b = sum(c.code_bytes() for c in d.values())
            print('方法: %d  字段: %d  代码字节: %d' % (tot_m, tot_f, tot_b))
        if a.dump:
            d[a.dump].dump()
    else:
        ClassFile.load(p.read_bytes()).dump()
    return 0


if __name__ == '__main__':
    sys.exit(main())
