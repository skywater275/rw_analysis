#!/usr/bin/env python3
"""
parse_rc_v96 — 按 e.a(as) 写侧字节码解析 v96 录制回放的 rc 记录 (v19.96)

T0 来源: javap e.java 的 a(as) 写方法:
  [UTF "c"] team(byte) wp(bool+units.au内联) flag_e(bool) flag_g(bool)
  int -1(占位) attackMode(enum int) z(bool+2float) flag_o(bool)
  argCount(int)+longs p(bool+n序列化) l(bool+2float) m(am引用long)
  动作名(UTF) r(bool) q(short) 步速(bool+byte+2float+int) d子对象列表

Usage: python tools/utils/parse_rc_v96.py <replay文件> [--verbose]
"""
import struct
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools" / "utils"))
from replay_parser import Reader  # noqa: E402


def parse_v96(sub, verbose=False, base=0):
    """按写侧格式解析 v96 命令, 返回字段字典; 打印每字段偏移."""
    fields = {}

    def rd(name, fn, size):
        off = sub.tell()
        v = fn()
        if verbose:
            print(f"    +{off - base:3d} {name} = {v!r}")
        return v

    rd("team", sub.read_byte, 1)
    fields["wp"] = rd("wp bool", sub.read_bool, 1)
    if fields["wp"]:
        rd("wp enum", sub.read_int, 4)
        uid = rd("wp unitId", sub.read_int, 4)
        fields["wpUnitId"] = uid
        if uid == -2:
            rd("wp customName", sub.read_utf, None)
        rd("wp f1", sub.read_float, 4)
        rd("wp f2", sub.read_float, 4)
        rd("wp long", sub.read_long, 8)
        rd("wp b40", sub.read_byte, 1)
        rd("wp f46a", sub.read_float, 4)
        rd("wp f46b", sub.read_float, 4)
        rd("wp b58", sub.read_bool, 1)
        rd("wp b65", sub.read_bool, 1)
        rd("wp b79", sub.read_bool, 1)
        rd("wp b82", sub.read_byte, 1)
    fields["flag_e"] = rd("flag_e", sub.read_bool, 1)
    fields["stopOrUndo"] = rd("flag_g", sub.read_bool, 1)
    rd("int -1占位", sub.read_int, 4)
    fields["attackMode"] = rd("attackMode", sub.read_int, 4)
    fields["z"] = rd("z bool", sub.read_bool, 1)
    if fields["z"]:
        fields["zx"] = rd("z.a", sub.read_float, 4)
        fields["zy"] = rd("z.b", sub.read_float, 4)
    fields["flag_o"] = rd("flag_o", sub.read_bool, 1)
    n = rd("argCount", sub.read_int, 4)
    fields["argCount"] = n
    for i in range(n):
        rd(f"arg[{i}]", sub.read_long, 8)
    fields["p"] = rd("p bool", sub.read_bool, 1)
    if fields["p"]:
        rd("p (n序列化)", sub.read_byte, 1)  # PlayerState
    fields["l"] = rd("l bool", sub.read_bool, 1)
    if fields["l"]:
        rd("l.a", sub.read_float, 4)
        rd("l.b", sub.read_float, 4)
    fields["m"] = rd("m (UnitInstance)", sub.read_long, 8)
    fields["actionName"] = rd("动作名", sub.read_utf, None)
    fields["r"] = rd("r bool", sub.read_bool, 1)
    fields["q"] = rd("q short", sub.read_short, 2)
    fields["stepRate"] = rd("步速 bool", sub.read_bool, 1)
    if fields["stepRate"]:
        rd("步速 byte", sub.read_byte, 1)
        rd("步速 f1", sub.read_float, 4)
        rd("步速 f2", sub.read_float, 4)
        rd("步速 int", sub.read_int, 4)
    n2 = rd("d子对象数", sub.read_int, 4)
    fields["subParts"] = n2
    for i in range(n2):
        rd(f"d[{i}].long", sub.read_long, 8)
        for j in range(4):
            rd(f"d[{i}].f{j}", sub.read_float, 4)
        rd(f"d[{i}].int1", sub.read_int, 4)
        rd(f"d[{i}].enum", sub.read_int, 4)
        if rd(f"d[{i}].bool", sub.read_bool, 1):
            # k.k.a(as): [bool x!=null] + at 子流嵌入 = [UTF 名][int 长度][压缩数据]
            has_path = rd(f"d[{i}].路径bool", sub.read_bool, 1)
            fields[f"d[{i}].路径bool"] = has_path
            if has_path:
                sname = rd(f"d[{i}].子流名", sub.read_utf, None)
                fields[f"d[{i}].子流名"] = sname
                slen = rd(f"d[{i}].子流长度", sub.read_int, 4)
                sub.p += slen                 # 跳过 gzip 数据
                fields[f"d[{i}].子流长度"] = slen
    fields["flag_h"] = rd("flag_h (尾部)", sub.read_bool, 1)
    return fields


def main():
    args = [a for a in sys.argv[1:] if not a.startswith("--")]
    verbose = "--verbose" in sys.argv
    if not args:
        print("Usage: python tools/utils/parse_rc_v96.py <replay文件> [--verbose]")
        sys.exit(1)
    data = Path(args[0]).read_bytes()
    r = Reader(data)
    r.read_utf(); r.read_int(); version = r.read_int(); r.read_utf(); r.read_bool()
    print(f"version={version}")
    r.read_utf(); r.read_bytes()
    known_tags = (b"\x00\x02rc", b"\x00\x04wait", b"\x00\x02cs", b"\x00\x02es",
                  b"\x00\x04chat", b"\x00\x03end")
    for step in range(8):
        if any(r.d[r.p + step:r.p + step + 6].startswith(t) for t in known_tags):
            r.p += step
            break
    ok = 0
    fail = 0
    while r.tell() < len(data) - 6:
        tag = r.read_utf()
        blen = r.read_int()
        sub_start = r.p
        if tag == "rc":
            frame = struct.unpack_from(">i", r.d, r.p)[0]
            print(f"\n=== rc 帧={frame} 载荷={blen} ===")
            try:
                sub = Reader(r.d[r.p:r.p + blen])
                sub.read_int()          # 帧号
                name = sub.read_utf()   # "c" 子块名
                sub.read_int()          # 子块长度
                base = sub.tell()
                fields = parse_v96(sub, verbose=verbose, base=base)
                consumed = sub.tell() - base
                print(f"  消耗 {consumed}/{blen} 字节, 剩余 {blen - consumed}")
                if consumed > blen:
                    print(f"  [!] 越界: 消耗超过载荷")
                    fail += 1
                else:
                    ok += 1
            except Exception as e:
                print(f"  [!] 解析失败: {e}")
                fail += 1
        r.p = sub_start + blen
    print(f"\n成功 {ok} / 失败 {fail}")
    sys.exit(0)


if __name__ == "__main__":
    main()
