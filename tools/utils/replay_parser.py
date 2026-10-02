#!/usr/bin/env python3
"""
replay_parser — 回放文件命令流解析 (v19.95)

回放格式 (ba.java/ba 写侧镜像 + e.java Command 反序列化 + j/k.java 流协议):
  [UTF 魔数 "rustedWarfareReplay"][int gameCode][int version][UTF 版本串][bool]
  [块 "gamesave": UTF 名 + byte[] 载荷]      ← 内嵌初始存档 (跳过)
  记录流: [UTF 标签][载荷][short 12345 标记]
    rc   = 命令: [int frame][Command 字段 (版本门控)]
    wait = [int frame]
    cs   = 校验和: [int frame][long checksum]
    es   = [int][...]
    chat = 聊天等 (后续类型)

输出 mappings/generated/replay-command-catalog.csv: 每条命令一行 (帧/队伍/类型/动作...)

Usage: python tools/utils/replay_parser.py <replay文件> [--limit N]
"""
import csv
import struct
import sys
from pathlib import Path

csv.field_size_limit(10 * 1024 * 1024)

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import GENERATED_DIR


class Reader:
    def __init__(self, data):
        self.d = data
        self.p = 0

    def tell(self):
        return self.p

    def read_utf(self):
        n = struct.unpack_from(">H", self.d, self.p)[0]
        self.p += 2
        s = self.d[self.p:self.p + n].decode("utf-8")
        self.p += n
        return s

    def read_int(self):
        v = struct.unpack_from(">i", self.d, self.p)[0]
        self.p += 4
        return v

    def read_long(self):
        v = struct.unpack_from(">q", self.d, self.p)[0]
        self.p += 8
        return v

    def read_bool(self):
        v = self.d[self.p]
        self.p += 1
        return v != 0

    def read_short(self):
        v = struct.unpack_from(">h", self.d, self.p)[0]
        self.p += 2
        return v

    def read_byte(self):
        v = self.d[self.p]
        self.p += 1
        return v

    def read_float(self):
        v = struct.unpack_from(">f", self.d, self.p)[0]
        self.p += 4
        return v

    def read_bytes(self):
        """byte[] 载荷 (int 长度 + 内容)."""
        n = struct.unpack_from(">i", self.d, self.p)[0]
        self.p += 4
        b = self.d[self.p:self.p + n]
        self.p += n
        return b


def parse_command(r, version):
    """按 e.java 的 a(k) 反序列化 (版本门控) 解析一条命令, 返回字段字典."""
    c = {}
    # b("c") 子块: [UTF 名][int 长度][字段...]
    name = r.read_utf()
    if name != "c":
        raise ValueError(f"命令子块期望 c, 得到 {name!r} (偏移 {r.tell()})")
    blen = r.read_int()
    sub = Reader(r.d[r.p:r.p + blen])
    r.p += blen
    c["team"] = sub.read_byte()                   # 队伍枚举
    if sub.read_bool():                           # 路径点 (units.au 内联字段)
        c["waypoint"] = True
        sub.read_int()                            # 路径点类型枚举 (av)
        uid = sub.read_int()                      # 单位类型 id (-1/-2/+id)
        if uid == -2:
            sub.read_utf()                        # 自定义单位名
        sub.read_float()
        sub.read_float()
        sub.read_long()
        if version >= 40:
            sub.read_byte()
        if version >= 46:
            sub.read_float()
            sub.read_float()
        if version >= 58:
            sub.read_bool()
        if version >= 65:
            sub.read_bool()
        if version >= 79:
            sub.read_bool()
        if version >= 82:
            sub.read_byte()                       # a.c 枚举
    c["flag_e"] = sub.read_bool()
    c["stopOrUndo"] = sub.read_bool()
    c["action"] = sub.read_int()                  # 特殊动作枚举
    c["attackMode"] = sub.read_int()              # 攻击模式枚举 (int 序号, -1=null)
    if sub.read_bool():                           # 目标坐标
        sub.read_float()
        sub.read_float()
    c["flag_o"] = sub.read_bool()
    n = sub.read_int()
    c["argCount"] = n
    for _ in range(n):
        sub.read_long()                           # 单位 id 等
    if version >= 16:
        if sub.read_bool():
            sub.read_byte()                       # s() = PlayerState (队伍 byte)
    if version >= 29:
        if sub.read_bool():
            sub.read_float()
            sub.read_float()
        sub.read_long()                           # o() = UnitInstance 实体 id (long)
    if version >= 33:
        sub.read_utf()                            # 动作名
    if version >= 37:
        sub.read_bool()
    if version >= 52:
        sub.read_short()
    if version >= 53:
        if sub.read_bool():
            c["hasStepRate"] = True
            sub.read_byte()
            sub.read_float()
            sub.read_float()
            sub.read_int()
        n2 = sub.read_int()
        c["subPartCount"] = n2
        for _ in range(n2):                       # d 子对象 (gameFramework.d.a(as) 写侧格式)
            sub.read_long()                       # 实体 id
            for _ in range(4):
                sub.read_float()                  # 4 坐标
            sub.read_int()                        # 帧号
            sub.read_int()                        # ao 路径类型枚举
            if sub.read_bool():                   # a != null
                if sub.read_bool():               # k.x != null
                    sub.read_utf()                # 子流名 "p"
                    sub.p += sub.read_int()       # at 压缩子流 [长度][gzip 数据]
    if version >= 80:
        sub.read_bool()
    return c


def main():
    args = [a for a in sys.argv[1:] if not a.startswith("--")]
    limit = None
    for i, a in enumerate(sys.argv):
        if a == "--limit" and i + 1 < len(sys.argv):
            limit = int(sys.argv[i + 1])
    if not args:
        print("Usage: python tools/utils/replay_parser.py <replay文件> [--limit N]")
        sys.exit(1)

    data = Path(args[0]).read_bytes()
    r = Reader(data)

    # 头
    magic = r.read_utf()
    game_code = r.read_int()
    version = r.read_int()
    version_str = r.read_utf()
    flag = r.read_bool()
    print(f"魔数={magic!r} gameCode={game_code} version={version} "
          f"版本串={version_str!r} flag={flag}")

    # gamesave 块: [UTF 名][byte[]]
    block_name = r.read_utf()
    payload = r.read_bytes()
    print(f"块 {block_name!r}: {len(payload)} 字节 (跳过)")
    # 载荷尾部含标记(12345)+"<SAVE END>", 长度可能有 0-4 字节偏差 — 扫描对齐到首个已知记录标签
    known_tags = (b"\x00\x02rc", b"\x00\x04wait", b"\x00\x02cs", b"\x00\x02es",
                  b"\x00\x04chat", b"\x00\x03end")
    for step in range(8):
        if any(r.d[r.p + step:r.p + step + 6].startswith(t) for t in known_tags):
            r.p += step
            if step:
                print(f"  对齐: +{step} 字节")
            break

    # 记录流: 每记录 = [UTF 标签][int 载荷长度][载荷]
    rows = []
    counts = {}
    checksums = []
    errors = 0
    total = 0
    while r.tell() < len(data) - 6:
        try:
            tag = r.read_utf()
        except Exception:
            break
        if not (2 <= len(tag) <= 12):
            print(f"  流失步 @ {r.tell()}: 标签异常 {tag!r}, 停止")
            break
        blen = r.read_int()
        if blen < 0 or blen > 100_000_000:
            print(f"  流失步 @ {r.tell()}: 块长度异常 {blen}, 停止")
            break
        sub = Reader(r.d[r.p:r.p + blen])
        r.p += blen

        def parse_record(tag, sub):
            if tag == "rc":
                frame = sub.read_int()
                cmd = parse_command(sub, version)
                counts["rc"] = counts.get("rc", 0) + 1
                rows.append([frame, cmd.get("team", "?"), cmd.get("action", "?"),
                             cmd.get("argCount", 0), cmd.get("stopOrUndo", ""),
                             cmd.get("waypoint", ""), cmd.get("attackMode", ""),
                             cmd.get("hasStepRate", "")])
            elif tag == "wait":
                sub.read_int()
                counts["wait"] = counts.get("wait", 0) + 1
            elif tag == "cs":
                frame = sub.read_int()
                chk = sub.read_long()
                counts["cs"] = counts.get("cs", 0) + 1
                checksums.append((frame, chk))
            elif tag == "es":
                sub.read_int()
                sub.read_bytes()             # 扩展校验和子流
                counts["es"] = counts.get("es", 0) + 1
            elif tag == "chat":
                sub.read_int()
                sub.read_int()
                sub.read_utf()
                sub.read_utf()
                counts["chat"] = counts.get("chat", 0) + 1
            elif tag == "resync":
                sub.read_int()
                sub.read_int()
                sub.read_int()
                sub.read_int()
                sub.read_bytes()
                counts["resync"] = counts.get("resync", 0) + 1
            elif tag == "end":
                counts["end"] = counts.get("end", 0) + 1
                print(f"  [end] 回放块结束 @ 偏移 {r.tell()}")
                return False
            else:
                counts[tag] = counts.get(tag, 0) + 1
                print(f"  未知记录类型: {tag!r} @ {r.tell()}")
                return False
            return True

        try:
            if not parse_record(tag, sub):
                break
        except Exception as e:
            if errors < 5:
                print(f"  [!] 记录 {tag} @ {r.p - blen} 解析跳过: {e}")
            errors += 1
        total += 1
        if limit and total >= limit:
            break

    print(f"\n记录统计: {counts}")
    print(f"标记校验错误: {errors}; 校验和记录: {len(checksums)}")
    if checksums:
        print(f"  校验和样例: {checksums[:3]}")

    out = GENERATED_DIR / "replay-command-catalog.csv"
    with open(out, "w", encoding="utf-8", newline="") as f:
        w = csv.writer(f)
        w.writerow(["frame", "team", "action", "argCount", "stopOrUndo",
                    "waypoint", "attackMode", "hasStepRate"])
        w.writerows(rows)
    print(f"[输出] {out.name}: {len(rows)} 条命令")
    sys.exit(0)


if __name__ == "__main__":
    main()
