#!/usr/bin/env python3
"""
dump_rc — 回放 rc 记录原始字节 dump (v19.96)

对比录制端 (ba/bb/c.java) 与解析端 (replay_parser.py) 的 Command 序列化格式,
定位版本门控差异。

Usage: python tools/utils/dump_rc.py <replay文件> [--max N]
"""
import struct
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))

# 复用 replay_parser 的 Reader
sys.path.insert(0, str(ROOT / "tools" / "utils"))
from replay_parser import Reader  # noqa: E402


def main():
    args = [a for a in sys.argv[1:] if not a.startswith("--")]
    max_records = None
    for i, a in enumerate(sys.argv):
        if a == "--max" and i + 1 < len(sys.argv):
            max_records = int(sys.argv[i + 1])
    if not args:
        print("Usage: python tools/utils/dump_rc.py <replay文件> [--max N]")
        sys.exit(1)

    data = Path(args[0]).read_bytes()
    r = Reader(data)
    magic = r.read_utf()
    game_code = r.read_int()
    version = r.read_int()
    version_str = r.read_utf()
    flag = r.read_bool()
    print(f"头: magic={magic} gameCode={game_code} version={version} verStr={version_str} flag={flag}")
    block = r.read_utf()
    payload = r.read_bytes()
    print(f"gamesave: {len(payload)} 字节")
    # 对齐
    known_tags = (b"\x00\x02rc", b"\x00\x04wait", b"\x00\x02cs", b"\x00\x02es",
                  b"\x00\x04chat", b"\x00\x03end")
    for step in range(8):
        if any(r.d[r.p + step:r.p + step + 6].startswith(t) for t in known_tags):
            r.p += step
            if step:
                print(f"对齐: +{step}")
            break

    shown = 0
    while r.tell() < len(data) - 6 and (max_records is None or shown < max_records):
        tag = r.read_utf()
        blen = r.read_int()
        sub_start = r.p
        if tag == "rc":
            frame = struct.unpack_from(">i", r.d, r.p)[0]
            print(f"\n=== rc @ {sub_start} 帧={frame} 载荷={blen} 字节 ===")
            # 十六进制 + 注释 dump
            raw = r.d[sub_start:sub_start + blen]
            for off in range(0, min(len(raw), 256), 16):
                chunk = raw[off:off + 16]
                hexstr = " ".join(f"{b:02x}" for b in chunk)
                asc = "".join(chr(b) if 32 <= b < 127 else "." for b in chunk)
                print(f"  {off:04x}: {hexstr:<48} {asc}")
            shown += 1
        r.p = sub_start + blen
    sys.exit(0)


if __name__ == "__main__":
    main()
