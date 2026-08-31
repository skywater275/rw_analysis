#!/usr/bin/env python
"""Minimal .class parser: dump method names + descriptors for a class file."""
import struct, sys

CONST = {
    1:'Utf8', 3:'Integer', 4:'Float', 5:'Long', 6:'Double', 7:'Class',
    8:'String', 9:'Fieldref', 10:'Methodref', 11:'InterfaceMethodref',
    12:'NameAndType', 15:'MethodHandle', 16:'MethodType', 17:'Dynamic',
    18:'InvokeDynamic', 19:'Module', 20:'Package',
}

def parse(path):
    with open(path, 'rb') as f:
        data = f.read()
    assert data[:4] == b'\xca\xfe\xba\xbe', 'not a class file'
    off = 8
    cp_count = struct.unpack('>H', data[off:off+2])[0]; off += 2
    cp = [None] * cp_count
    i = 1
    while i < cp_count:
        tag = data[off]; off += 1
        if tag == 1:
            ln = struct.unpack('>H', data[off:off+2])[0]; off += 2
            cp[i] = data[off:off+ln].decode('utf-8', 'replace'); off += ln
        elif tag in (3, 4):
            off += 4
        elif tag in (5, 6):
            off += 8; i += 1
        elif tag in (7, 8, 16, 19, 20):
            off += 2
        elif tag in (9, 10, 11, 12, 17, 18):
            off += 4
        elif tag == 15:
            off += 3
        else:
            raise ValueError('unknown tag %d at %d' % (tag, off))
        i += 1
    # access, this, super
    off += 6
    iface_count = struct.unpack('>H', data[off:off+2])[0]; off += 2 + 2*iface_count
    field_count = struct.unpack('>H', data[off:off+2])[0]; off += 2
    fields = []
    for _ in range(field_count):
        acc, name, desc = struct.unpack('>HHH', data[off:off+6]); off += 6
        fields.append((cp[name], cp[desc]))
        cnt = struct.unpack('>H', data[off:off+2])[0]; off += 2
        for _ in range(cnt):
            ln = struct.unpack('>I', data[off+2:off+6])[0]
            off += 6 + ln
    mcount = struct.unpack('>H', data[off:off+2])[0]; off += 2
    methods = []
    for _ in range(mcount):
        acc, name, desc = struct.unpack('>HHH', data[off:off+6]); off += 6
        methods.append((cp[name], cp[desc]))
        cnt = struct.unpack('>H', data[off:off+2])[0]; off += 2
        for _ in range(cnt):
            ln = struct.unpack('>I', data[off+2:off+6])[0]
            off += 6 + ln
    print('== %s ==' % path)
    print('-- fields (%d) --' % len(fields))
    for n, d in fields:
        print('  %s : %s' % (n, d))
    print('-- methods (%d) --' % len(methods))
    for n, d in methods:
        print('  %s %s' % (n, d))

if __name__ == '__main__':
    for p in sys.argv[1:]:
        parse(p)
