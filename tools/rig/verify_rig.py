#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""游戏完全体·测试台 — 现场校验器（只读）。

校验四组判据是否与 MANIFEST.json 一致：
  ① 部署态 game-lib.jar 的 SHA 是否等于清单记录（= 现场没被手工覆盖）
  ② jars/<profile>.jar 是否存在、SHA 与条目数是否与清单一致
  ③ 运行时文件是否齐全（exe / 两套 JRE / libs / assets+res+font / DLL）
  ④ 逆向仓哨兵与产物是否与清单一致（build-skip.txt / build/game-lib-reverse.jar）

Usage:
    python tools/rig/verify_rig.py            # 逐项打印结论（退出码 0=全通过 / 1=有失败）
    python tools/rig/verify_rig.py --quiet    # 只打印失败项与汇总
    python tools/rig/verify_rig.py --json     # 输出 JSON（供其他脚本消费）

说明：GAME_ROOT 由本文件位置推算（tools/rig/*.py 上溯三级），可用 RW_GAME_ROOT 覆盖。
      本脚本不修改任何文件。
"""
import argparse
import hashlib
import json
import os
import sys
import zipfile
from pathlib import Path

RIG_DIR = Path(__file__).resolve().parent
REPO = RIG_DIR.parent.parent
GAME_ROOT = Path(os.environ.get("RW_GAME_ROOT") or REPO.parent)

sys.path.insert(0, str(REPO / "tools"))
from rwlib.tuning import T  # noqa: E402

RESULTS = []


def record(name, ok, detail=""):
    RESULTS.append({"item": name, "ok": bool(ok), "detail": str(detail)})
    return ok


def sha256(path):
    h = hashlib.sha256()
    with open(path, "rb") as f:
        for chunk in iter(lambda: f.read(1 << 20), b""):
            h.update(chunk)
    return h.hexdigest().upper()


def jar_stat(path):
    with zipfile.ZipFile(path) as z:
        names = z.namelist()
    return len(names), len([n for n in names if n.endswith(".class")])


def jar_content_sig(path):
    """逐条目内容签名：忽略 ZIP 时间戳/压缩方式，只反映**条目名 + 条目内容**。

    为什么需要它：同一份类集合重新打包后，整文件 SHA 必然变化（ZIP 内部时间戳），
    历史上「构建产物 == current」因此**每次重建都报红**（假 FAIL），长期红灯会训练人忽略该项。
    """
    h = hashlib.sha256()
    with zipfile.ZipFile(path) as z:
        for name in sorted(z.namelist()):
            h.update(name.encode("utf-8"))
            h.update(hashlib.sha256(z.read(name)).digest())
    return h.hexdigest().upper()


def main():
    ap = argparse.ArgumentParser(description="游戏完全体测试台现场校验")
    ap.add_argument("--quiet", action="store_true", help="只打印失败项")
    ap.add_argument("--json", action="store_true", help="输出 JSON")
    args = ap.parse_args()

    mpath = GAME_ROOT / "MANIFEST.json"
    if not mpath.exists():
        print("[FATAL] 找不到 %s" % mpath)
        return 1
    man = json.loads(mpath.read_text(encoding="utf-8"))

    # ① 部署态 jar
    dep = man.get("deployed", {})
    gjar = GAME_ROOT / "game-lib.jar"
    if record("部署态 game-lib.jar 存在", gjar.exists(), gjar):
        actual = sha256(gjar)
        record(
            "部署态 game-lib.jar SHA",
            actual == dep.get("sha256", ""),
            "%s (清单 %s / profile=%s)" % (actual[:16], dep.get("sha256", "")[:16], dep.get("profile")),
        )

    # ② jar 库
    for p in man.get("profiles", []):
        jp = GAME_ROOT / p["file"]
        if not record("jar 存在 %s" % p["name"], jp.exists(), p["file"]):
            continue
        record("jar SHA %s" % p["name"], sha256(jp) == p["sha256"], "%s" % p["sha256"][:16])
        n, c = jar_stat(jp)
        record(
            "jar 条目 %s" % p["name"],
            (n == p.get("entries")) and (c == p.get("classes")),
            "实测 %d 条目 / %d class（清单 %s / %s）" % (n, c, p.get("entries"), p.get("classes")),
        )

    # ③ 运行时文件
    for rel in man.get("runtime_required", []):
        record("运行时 %s" % rel, (GAME_ROOT / rel).exists(), rel)
    libs = sorted((GAME_ROOT / "libs").glob("*.jar")) if (GAME_ROOT / "libs").is_dir() else []
    record("libs/*.jar 数量", len(libs) >= 20, "实测 %d 个" % len(libs))
    dlls = sorted(GAME_ROOT.glob("*.dll"))
    record("根 DLL 数量", len(dlls) >= 20, "实测 %d 个" % len(dlls))
    for d in ("assets", "res", "font"):
        p = GAME_ROOT / d
        n = sum(1 for _ in p.rglob("*") if _.is_file()) if p.is_dir() else 0
        record("资源目录 %s" % d, n > 0, "%d 文件" % n)

    # ④ 逆向仓
    repo_ok = (REPO / "build-skip.txt").exists()
    record("逆向仓可定位", repo_ok, str(REPO))
    if repo_ok:
        sent = man.get("sentinels", {}).get("build-skip.txt", {})
        if sent:
            a = sha256(REPO / "build-skip.txt")
            record("哨兵 build-skip.txt SHA", a == sent.get("sha256", ""), "%s" % a[:16])
            lines = len((REPO / "build-skip.txt").read_text(encoding="utf-8").splitlines())
            record("哨兵 build-skip.txt 行数", lines == sent.get("lines"), "实测 %d 行（清单 %s）" % (lines, sent.get("lines")))
        cur = next((p for p in man.get("profiles", []) if p["name"] == "current"), None)
        built = REPO / "build" / "game-lib-reverse.jar"
        if cur and record("构建产物存在", built.exists(), "build/game-lib-reverse.jar"):
            cur_path = GAME_ROOT / cur["file"]
            mode = str(T.get("integrity.hash_mode", "per_entry"))
            a = sha256(built)
            if mode == "per_entry" and cur_path.exists():
                # 逐条目内容比对（默认）：同内容不同打包时间戳不再误报
                sig_built, sig_cur = jar_content_sig(built), jar_content_sig(cur_path)
                record(
                    "构建产物 == current 内容",
                    sig_built == sig_cur,
                    "逐条目签名 %s vs %s（hash_mode=per_entry）" % (sig_built[:16], sig_cur[:16]),
                )
                record(
                    "构建产物文件 SHA 与清单一致（信息项）",
                    True,
                    "%s（清单 %s；文件 SHA 随 ZIP 时间戳变化，故仅作信息）"
                    % (a[:16], cur["sha256"][:16]),
                )
            else:
                record(
                    "构建产物 == current profile",
                    a == cur["sha256"],
                    "%s (current %s)" % (a[:16], cur["sha256"][:16]),
                )

    failed = [r for r in RESULTS if not r["ok"]]
    if args.json:
        print(json.dumps({"game_root": str(GAME_ROOT), "total": len(RESULTS), "failed": len(failed), "results": RESULTS},
                         ensure_ascii=False, indent=2))
    else:
        if not args.quiet:
            for r in RESULTS:
                print("  [%s] %-28s %s" % ("OK" if r["ok"] else "FAIL", r["item"], r["detail"]))
        else:
            for r in failed:
                print("  [FAIL] %-28s %s" % (r["item"], r["detail"]))
        print("=" * 60)
        print("现场校验：%d 项，通过 %d，失败 %d  → %s" % (len(RESULTS), len(RESULTS) - len(failed), len(failed),
                                                    "PASS" if not failed else "FAIL"))
    return 1 if failed else 0


if __name__ == "__main__":
    sys.exit(main())
