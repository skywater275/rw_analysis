#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""游戏完全体·测试台 — MANIFEST.json 生成器。

从**实测**生成现场清单（不做任何假设），内容包括：三个 jar 形态的 SHA/条目数、
当前部署态、逆向仓哨兵（build-skip.txt）、构建产物、两道门禁的最近结果、运行时必需文件、
已知问题清单。

Usage:
    python tools/rig/make_manifest.py            # 生成/刷新 <GAME_ROOT>/MANIFEST.json
    python tools/rig/make_manifest.py --print    # 只打印，不落盘
说明：GAME_ROOT 由本文件位置推算（tools/rig/*.py 上溯三级），可用 RW_GAME_ROOT 覆盖。
"""
import argparse
import hashlib
import json
import os
import subprocess
import sys
import zipfile
from datetime import datetime
from pathlib import Path

RIG_DIR = Path(__file__).resolve().parent
REPO = RIG_DIR.parent.parent
GAME_ROOT = Path(os.environ.get("RW_GAME_ROOT") or REPO.parent)
MANIFEST = GAME_ROOT / "MANIFEST.json"

sys.path.insert(0, str(REPO / "tools"))
from rwlib.tuning import T  # noqa: E402

PROFILES = [
    ("stock", "jars/stock.jar", "原版 v1.15（编译目标 / 地面真值 / 回滚基准）"),
    ("d3-era", "jars/d3-era.jar", "D-3 期产物（2026-08-31）：回放校验和 don't match 7→0 已验的那版"),
    ("current", "jars/current.jar", "03 标准形态：当前主线；**回放已验**（2026-09-26 V0–V7 8/8 PASS，don't match 0）"),
]

RUNTIME_REQUIRED = [
    "Rusted Warfare - 64.exe",
    "Rusted Warfare.exe",
    "jvm64/bin/javaw.exe",
    "jvm64/bin/javac.exe",
    "jvm/bin/java.exe",
    "libs/lwjgl.jar",
    "libs/slick.jar",
    "libs/jinput.jar",
    "assets",
    "res",
    "font",
    "libRocketCore.dll",
    "lwjgl64.dll",
    "steam_api64.dll",
]


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


def _git_head():
    try:
        out = subprocess.run(["git", "-C", str(REPO), "rev-parse", "--short", "HEAD"],
                             capture_output=True, text=True, timeout=10)
        return out.stdout.strip() if out.returncode == 0 else ""
    except Exception:
        return ""


def ab_split():
    """当前产物的 A/B 分类：A = 与原版同名的替换类；B = 新增类。"""
    classes = REPO / "build" / "reverse-classes"
    stock = GAME_ROOT / "jars" / "stock.jar"
    if not classes.is_dir() or not stock.exists():
        return None
    with zipfile.ZipFile(stock) as z:
        orig = set(z.namelist())
    files = [str(p.relative_to(classes)).replace(os.sep, "/") for p in classes.rglob("*.class")]
    a = [f for f in files if f in orig]
    b = [f for f in files if f not in orig]
    return {"compiled": len(files), "replaced_A": len(a), "added_B": len(b)}


def gate_status():
    out = {}
    ce = REPO / "compile-errors.csv"
    if ce.exists():
        lines = [l for l in ce.read_text(encoding="utf-8", errors="replace").splitlines() if l.strip()]
        out["javac_gate"] = {
            "errors": max(0, len(lines) - 1),
            "verdict": "PASSED" if len(lines) <= 1 else "FAILED",
            "source": str(ce.relative_to(REPO)),
            "mtime": datetime.fromtimestamp(ce.stat().st_mtime).strftime("%Y-%m-%d %H:%M:%S"),
        }
    # 门禁摘要：优先读「现役输出目录」（软编码），不存在再回退旧目录（历史产物）
    # 历史缺陷：此处曾写死 `build/_jar_gate/`，而 replacement_verify 早已把输出写到
    # `build/_verify_jar_gate/` ⇒ MANIFEST 长期引用 09-26 的过期摘要。
    summ = T.path("jar_gate.out_dir") / T.get("jar_gate.summary_name")
    if not summ.exists():
        legacy = T.path("jar_gate.legacy_out_dir") / T.get("jar_gate.summary_name")
        if legacy.exists():
            summ = legacy
    if summ.exists():
        kv = {}
        for line in summ.read_text(encoding="utf-8", errors="replace").splitlines():
            if "=" in line:
                k, _, v = line.partition("=")
                kv[k.strip()] = v.strip()

        def _int(key):
            try:
                return int(kv.get(key, ""))
            except (TypeError, ValueError):
                return None

        out["jar_compare_gate"] = {
            "fail": _int("FAIL"),
            "review": _int("REVIEW"),
            "pass": _int("PASS"),
            "files": _int("files"),
            "verdict": "PASS" if _int("FAIL") == 0 else "FAIL",
            "ref_jar": kv.get("ref_jar", ""),
            "rev_jar": kv.get("rev_jar", ""),
            "source": str(summ.relative_to(REPO)),
            "mtime": datetime.fromtimestamp(summ.stat().st_mtime).strftime("%Y-%m-%d %H:%M:%S"),
        }
    return out


def main():
    ap = argparse.ArgumentParser(description="生成测试台 MANIFEST.json")
    ap.add_argument("--print", dest="dry", action="store_true", help="只打印不落盘")
    args = ap.parse_args()

    profiles = []
    for name, rel, note in PROFILES:
        p = GAME_ROOT / rel
        if not p.exists():
            print("[WARN] 缺少 %s，跳过" % rel)
            continue
        n, c = jar_stat(p)
        profiles.append({"name": name, "file": rel, "sha256": sha256(p), "entries": n, "classes": c, "note": note})
    cur = next((p for p in profiles if p["name"] == "current"), None)
    d3 = next((p for p in profiles if p["name"] == "d3-era"), None)
    stock = next((p for p in profiles if p["name"] == "stock"), None)
    if cur and d3 and stock:
        cur["added_vs_stock"] = cur["entries"] - stock["entries"]
        d3["added_vs_stock"] = d3["entries"] - stock["entries"]
        ab = ab_split()
        if ab:
            cur.update(ab)
            cur["note"] += "；A %d 替换 + B %d 新增" % (ab["replaced_A"], ab["added_B"])

    gjar = GAME_ROOT / "game-lib.jar"
    deployed_sha = sha256(gjar) if gjar.exists() else ""
    dep = next((p for p in profiles if p["sha256"] == deployed_sha), None)

    sentinels = {}
    bs = REPO / "build-skip.txt"
    if bs.exists():
        sentinels["build-skip.txt"] = {
            "sha256": sha256(bs),
            "lines": len(bs.read_text(encoding="utf-8").splitlines()),
            "note": "冻结清单哨兵；会话期内只读（U-10 红线）",
        }

    old = json.loads(MANIFEST.read_text(encoding="utf-8")) if MANIFEST.exists() else {}
    man = {
        "game": "Rusted Warfare",
        "game_version": "v1.15",
        "build_rev": "v19.133f98-h9",
        "generated_at": datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
        "game_root": str(GAME_ROOT),
        "reverse_repo": str(REPO),
        "repo_head": _git_head(),
        "deployed": {
            "file": "game-lib.jar",
            "profile": dep["name"] if dep else "UNKNOWN",
            "sha256": deployed_sha,
            "entries": dep["entries"] if dep else None,
            "classes": dep["classes"] if dep else None,
            "switched_at": (old.get("deployed") or {}).get("switched_at", ""),
        },
        "profiles": profiles,
        "sentinels": sentinels,
        "built_product": {
            "file": "build/game-lib-reverse.jar",
            "sha256": sha256(REPO / "build" / "game-lib-reverse.jar") if (REPO / "build" / "game-lib-reverse.jar").exists() else "",
        },
        "gates": gate_status(),
        "runtime_required": RUNTIME_REQUIRED,
        "rig_tools": {
            "verify": "tools/rig/verify_rig.py",
            "switch": "tools/rig/switch_jar.py --list | --to <profile> [--apply]",
            "make_manifest": "tools/rig/make_manifest.py",
            "smoke": "tools/rig/smoke_test.py [--wait N]",
            "live": "tools/rig/live_stats.py [--once --json|--append|--log F]（实时对局数据，function 通道）",
            "supervised": "tools/rig/run_game.py [--sidebar N] [--filter K]（日志监管器: 上色+归档+侧栏+崩溃判据）",
            "help": "tools/rig/rig_help.py <start|tool|banner>",
            "launchers": "启动游戏.bat [console|verbose|debug|file|overlay|stock|d3-era|current|exe] / 测试台.bat [verify|list|switch|manifest|smoke|live]（源: tools/rig/launcher/；默认 console = 实时日志窗口；verbose = 详细日志 + 实时数据窗口）",
        },
        "known_issues": [
            "current 形态已于 2026-09-26 完成回放回归（don't match 0、extraChecksum 全 (ok)）—— "
            "D-3 的 don't match 7→0 是 2026-09-21 在 d3-era(1,850 条目) 上的定向修复",
            "supplement.csv 有 172 行 verified 被挤入 notes 列（PENDING #44，独立修复批次）",
            "03 路线已于 2026-09-27 第三十四轮（暂停归档）保持 A 1075 / 63.31%（V0–V7 8/8；工作区整理 + 阶段报告）；"
            "剩余见 PENDING #142（623 未替换；P1=检查让位 6~8/轮若属级联则改族级同批注入；P4=行为验证需加多回放）",
        ],
        "switch_history": old.get("switch_history", []),
    }
    if not man["deployed"]["switched_at"]:
        man["deployed"]["switched_at"] = man["generated_at"]

    text = json.dumps(man, ensure_ascii=False, indent=2) + "\n"
    if args.dry:
        print(text)
        return 0
    MANIFEST.write_text(text, encoding="utf-8")
    print("[完成] 已写入 %s" % MANIFEST)
    print("       部署 profile = %s (%s)" % (man["deployed"]["profile"], man["deployed"]["sha256"][:16]))
    for p in profiles:
        print("       %-8s %s  条目 %-5d class %-5d" % (p["name"], p["sha256"][:16], p["entries"], p["classes"]))
    print("       门禁: %s" % json.dumps(man["gates"], ensure_ascii=False))
    return 0


if __name__ == "__main__":
    sys.exit(main())
