#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""游戏完全体·测试台 — 部署态 jar 切换器。

在三个形态之间一键切换，并同步更新 MANIFEST.json 的 deployed 段（保留切换历史）：
    stock    —— 原版 v1.15（8A550A37…）：编译目标 / 地面真值 / 回滚基准
    d3-era   —— D-3 期产物（6D47AA73…，1,850 条目）：回放校验和 don't match 7→0 已验的那版
    current  —— H-9 产物（F82B39B9…，1,777 条目）：当前主线，尚未做回放回归

Usage:
    python tools/rig/switch_jar.py --list                 # 只列出可切换形态（只读）
    python tools/rig/switch_jar.py --to d3-era            # 预演（默认 dry-run，不落盘）
    python tools/rig/switch_jar.py --to d3-era --apply    # 实际切换

安全约束：
  · 目标 jar 的 SHA 必须与 MANIFEST.json 记录的 profile 完全一致，否则拒绝切换；
  · 切换前把当前部署态备份为 jars/_deployed-prev.jar（幂等覆盖）；
  · 切换后立即复算 SHA 并写入清单，不一致则报错并以非 0 退出。
"""
import argparse
import hashlib
import json
import os
import shutil
import sys
import zipfile
from datetime import datetime
from pathlib import Path

RIG_DIR = Path(__file__).resolve().parent
REPO = RIG_DIR.parent.parent
GAME_ROOT = Path(os.environ.get("RW_GAME_ROOT") or REPO.parent)
MANIFEST = GAME_ROOT / "MANIFEST.json"


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


def main():
    ap = argparse.ArgumentParser(description="测试台部署态 jar 切换器")
    ap.add_argument("--to", help="目标形态: stock | d3-era | current")
    ap.add_argument("--list", action="store_true", help="只列出可切换形态")
    ap.add_argument("--apply", action="store_true", help="实际落盘（缺省为 dry-run 预演）")
    args = ap.parse_args()

    if not MANIFEST.exists():
        print("[FATAL] 找不到 %s" % MANIFEST)
        return 1
    man = json.loads(MANIFEST.read_text(encoding="utf-8"))
    profiles = {p["name"]: p for p in man.get("profiles", [])}
    deployed = man.get("deployed", {})

    if args.list or not args.to:
        print("当前部署: profile=%s  SHA=%s  条目=%s" % (deployed.get("profile"), deployed.get("sha256", "")[:16],
                                                      deployed.get("entries")))
        for name, p in profiles.items():
            mark = "← 当前" if name == deployed.get("profile") else ""
            print("  %-8s %s  条目 %-5s %s  %s" % (name, p["sha256"][:16], p.get("entries"), p.get("note", ""), mark))
        return 0

    if args.to not in profiles:
        print("[FATAL] 未知形态 %r，可选: %s" % (args.to, ", ".join(profiles)))
        return 1
    tgt = profiles[args.to]
    tgt_path = GAME_ROOT / tgt["file"]
    if not tgt_path.exists():
        print("[FATAL] 目标 jar 不存在: %s" % tgt_path)
        return 1
    actual = sha256(tgt_path)
    if actual != tgt["sha256"]:
        print("[FATAL] 目标 jar SHA 与清单不符，拒绝切换\n  实测 %s\n  清单 %s" % (actual, tgt["sha256"]))
        return 1
    n, c = jar_stat(tgt_path)
    if (n, c) != (tgt.get("entries"), tgt.get("classes")):
        print("[FATAL] 目标 jar 条目数与清单不符: 实测 %d/%d，清单 %s/%s" % (n, c, tgt.get("entries"), tgt.get("classes")))
        return 1

    print("目标形态 : %s" % args.to)
    print("来源 jar : %s" % tgt["file"])
    print("SHA      : %s" % actual)
    print("条目     : %d 条目 / %d class" % (n, c))
    print("当前部署 : %s (%s)" % (deployed.get("profile"), deployed.get("sha256", "")[:16]))
    if not args.apply:
        print("\n[dry-run] 未落盘。确认无误后加 --apply 实际切换。")
        return 0

    prev = GAME_ROOT / "jars" / "_deployed-prev.jar"
    if (GAME_ROOT / "game-lib.jar").exists():
        shutil.copyfile(GAME_ROOT / "game-lib.jar", prev)
        print("[备份] 当前部署态 → jars/_deployed-prev.jar")
    shutil.copyfile(tgt_path, GAME_ROOT / "game-lib.jar")
    back = sha256(GAME_ROOT / "game-lib.jar")
    if back != tgt["sha256"]:
        print("[FATAL] 切换后 SHA 校验失败: %s" % back)
        return 1

    man["deployed"] = {
        "file": "game-lib.jar",
        "profile": args.to,
        "sha256": back,
        "entries": n,
        "classes": c,
        "switched_at": datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
    }
    man.setdefault("switch_history", []).append({"at": man["deployed"]["switched_at"], "to": args.to, "sha256": back})
    MANIFEST.write_text(json.dumps(man, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    print("[完成] game-lib.jar = %s（%s 条目）" % (back[:16], n))
    print("[完成] MANIFEST.json deployed 段已更新")
    print("[提醒] 换 jar 会改变行为基线；如需可信结论，必须重跑一次真实 GUI 回放回归（P4）。")
    return 0


if __name__ == "__main__":
    sys.exit(main())
