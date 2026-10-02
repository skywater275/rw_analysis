"""rwlib.tuning — 全项目「软编码」参数中心（原硬编码常量的唯一出口）。

设计要点
--------
1. **唯一出口**：所有阈值 / 上限 / 名录 / 路径 / 超时都在本模块登记，
   业务脚本**不得再写裸字面量**（审计工具 `tools/audit/tool_selfcheck.py` 会查）。
2. **三级覆盖**：内置默认 < 项目 `tuning.json` < 环境变量 `RW_<SECTION>_<KEY>`。
   命令行 `--set` 等价于环境变量级覆盖（单次运行用）。
3. **可推导优先于可配置**：`max_depth = 0` 表示「从 jar 自动推导真实继承链深度」——
   推导值是数据事实，不是人猜的常数（历史上 `depth=8` 正是猜错一层导致的成批误判）。
4. **可审计**：`python -m rwlib.tuning --print` 打印每个键的最终值与来源，
   便于回答「这个 8 到底是哪来的」。

用法
----
    from rwlib.tuning import T
    T.get("inherit.max_depth")          # 0 表示自动推导
    T.inherit_depth()                   # → 真实最大继承链深度（自动推导并缓存）
    T.get("jar_gate.super_closure_depth")
    T.path("member_refs.report")        # → ROOT 下的绝对路径

命令行
------
    python -m rwlib.tuning --print                  # 全部键 + 来源
    python -m rwlib.tuning --json                   # 机器可读
    python -m rwlib.tuning --write-template X.json  # 生成模板（默认值）
    python -m rwlib.tuning --derive-depth [JAR]     # 打印推导出的继承链深度
    python -m rwlib.tuning --set inherit.max_depth=12 --print
"""
from __future__ import annotations

import json
import os
import sys
import zipfile
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parents[1]))

from rwlib.config import ROOT  # noqa: E402

# ── 默认值（原硬编码常量的集中落点）────────────────────────────────────
DEFAULTS = {
    # 继承链解析：历史上 `depth=8` 写死，而单位族是 9 层 → 成批假阳性。
    "inherit": {
        "max_depth": 0,               # 0 = 从 jar 自动推导（推荐）；>0 = 显式上限
        "depth_ceiling": 64,          # 推导/覆盖的绝对上限，防继承环空转
        "fallback_depth": 16,         # 推导失败时（缺 jar）的兜底
        "depth_probe_jar": "RustedWarfare/game-lib.jar",
        # 链走到 JDK 祖先时如何判定：
        #   unverifiable（推荐）= 返回 None「不可验证」，绝不报缺失
        #   missing            = 返回 False「缺失」（旧行为，已知会误报）
        "jdk_ancestor_policy": "unverifiable",
        # 走到 java/lang/Object 时用于「可判定」的成员集：
        # 命中 ⇒ True（确定存在）；否则 ⇒ False（确定缺失）。
        # `<init>` 必须在内：每个构造器都会 `invokespecial java/lang/Object.<init>()V`。
        "object_members": [
            "<init>", "getClass", "hashCode", "equals", "clone", "toString",
            "notify", "notifyAll", "wait", "finalize",
        ],
    },
    # 跨类成员引用检查
    "member_refs": {
        "report": "build/_member-ref-real.txt",
        "ref_jar": "RustedWarfare/game-lib.jar",   # 地面真值 jar（相对项目根）
        "game_root": "..",                          # 游戏根（相对项目根；libs/ jars/ 在这里）
        "extra_jars": ["libs/*.jar", "jars/*.jar"],  # 相对游戏根的补充类宇宙
        "skip_prefixes": ["java/", "javax/", "sun/", "jdk/"],
        # 原 `_revert_member_refs.py` 的 JDK_METHODS 名称白名单。
        # 修好 JDK 语义（jdk_ancestor_policy=unverifiable）后**应保持为空**：
        # 按名字过滤无法区分「JDK 继承」与「我们漏声明」，属双向失真。
        "exempt_member_names": [],
        "max_items_per_class": 200,   # 报告每类最多列多少条（防爆表）
    },
    # 逐文件 JAR 对照门禁
    "jar_gate": {
        "super_closure_depth": 0,     # 0 = 推导（原写死 8 → E2 里 318 条伪条目）
        "out_dir": "build/_verify_jar_gate",
        "legacy_out_dir": "build/_jar_gate",     # 旧路径，仅作回退读取
        "summary_name": "jar-gate-summary.txt",
        "findings_name": "jar-gate-findings.csv",
        "files_name": "jar-gate-files.csv",
    },
    # 验收标准 V0–V7
    "verify": {
        "json_out": "build/_verify-latest.json",
        "v3_allowed_missing_classes": 4,   # 已知 #56：原版基线同样存在
        "v5_seconds": 26,
        "v6_watch": 90,
        "timeout_default": 3600,
        "timeout_jar_gate": 7200,
        "timeout_link": 1800,
        "timeout_gui": 900,
        "timeout_replay": 1200,
        "timeout_env_reset": 300,
    },
    # 部署闸门
    "deploy": {
        "verify_json": "build/_verify-latest.json",
        "require_verify_fresh": True,   # 验收 JSON 必须新于产物（否则视为无验收）
        "allow_blocked": True,          # BLOCKED 只告警不放行 FAIL
        "manifest_tool": "tools/rig/make_manifest.py",
    },
    # GUI 冒烟
    "smoke": {
        "seconds": 26,
        "window_class": "LWJGL",
        "crash_markers": [
            "uncaughtException start", "onGameCrash", "NoSuchMethodError",
            "NoSuchFieldError", "VerifyError", "Exception in thread",
        ],
        "env_crash_signs": [
            "Error loading core unit", "placing a mod in 'assets/'",
            "Unknown priority:", "Unknown movement type:",
        ],
        "cause_scan_lines": 24,
        "cause_frames": 3,
        "log": "build/_smoke-gui.log",
        "crash_log": "build/_smoke-crash.txt",
        "backup": "build/_deployed-before-smoke.jar",
        "append_crash_log": True,        # 累积而非覆盖（历史教训：证据被下一轮覆盖）
    },
    # 回放回归
    "replay": {
        "watch": 90,
        "mismatch_patterns": ["don't match", "dont match"],
        "start_pattern": "Replay: Starting frame",
    },
    # 产物/清单完整性判定
    "integrity": {
        # per_entry（推荐）= 逐条目内容哈希；file = 整文件 SHA
        # 同一内容因 ZIP 时间戳不同会导致 file 模式**每次重建都报不一致**（假 FAIL）
        "hash_mode": "per_entry",
    },
    # 类继承查询/文档渲染
    "class_tree": {
        "default_depth": 3,     # rw_class_tree / render_tree 的默认展开层数
        "max_depth": 8,         # 用户可请求的最大层数（防输出爆炸）
    },
    # 工具自检
    "audit": {
        "baseline": "tools/audit/tool-selfcheck-baseline.json",
        "scan_dirs": ["build", "tools"],
        # 仓库外但同属本项目的代码（MCP 已迁到工作区根 mcp/）
        "extra_scan_dirs": ["../mcp"],
        "exclude_globs": ["**/_archive/**", "**/__pycache__/**", "**/.venv/**"],
    },
}

_ENV_PREFIX = "RW_"
_FILE_ENV = "RW_TUNING_FILE"
_DEFAULT_FILE = "tuning.json"

# 运行时覆盖与来源记录
_OVERRIDES: dict[str, object] = {}
_SOURCES: dict[str, str] = {}
_DEPTH_CACHE: dict[str, int] = {}


# ── 内部工具 ────────────────────────────────────────────────────────
def _flatten(defaults, prefix=""):
    """把嵌套默认值拍平成 {'section.key': value}。"""
    out = {}
    for key, val in defaults.items():
        path = f"{prefix}{key}"
        if isinstance(val, dict):
            out.update(_flatten(val, path + "."))
        else:
            out[path] = val
    return out


def _coerce(raw, ref):
    """按参考值的类型解释字符串覆盖值。"""
    if isinstance(ref, bool):
        return str(raw).strip().lower() in ("1", "true", "yes", "on", "y")
    if isinstance(ref, int):
        return int(str(raw).strip())
    if isinstance(ref, float):
        return float(str(raw).strip())
    if isinstance(ref, (list, dict)):
        return json.loads(raw)
    return raw


def _env_name(key):
    return _ENV_PREFIX + key.replace(".", "_").replace("-", "_").upper()


def _load():
    """默认值 → 项目 tuning.json → 环境变量 → --set 覆盖（后者胜）。"""
    flat = _flatten(DEFAULTS)
    values = dict(flat)
    for k in flat:
        _SOURCES[k] = "default"

    cfg_path = Path(os.environ.get(_FILE_ENV) or (ROOT / _DEFAULT_FILE))
    if cfg_path.exists():
        try:
            data = json.loads(cfg_path.read_text(encoding="utf-8"))
            for k, v in _flatten(data).items():
                if k in values:
                    values[k] = v
                    _SOURCES[k] = f"file:{cfg_path.name}"
        except Exception as exc:  # noqa: BLE001 — 配置坏了要看得见，不能静默
            print(f"[tuning] 警告：{cfg_path} 解析失败（{exc}），已忽略", file=sys.stderr)

    for k, ref in flat.items():
        env = os.environ.get(_env_name(k))
        if env is not None and env != "":
            try:
                values[k] = _coerce(env, ref)
                _SOURCES[k] = f"env:{_env_name(k)}"
            except Exception as exc:  # noqa: BLE001
                print(f"[tuning] 警告：{_env_name(k)}={env!r} 解析失败（{exc}）", file=sys.stderr)

    for k, v in _OVERRIDES.items():
        if k in values:
            values[k] = v
            _SOURCES[k] = "cli:--set"
    return values


_VALUES = _load()


# ── 公开 API ────────────────────────────────────────────────────────
class _Tuning:
    """软编码参数访问器（唯一出口）。"""

    def get(self, key, default=None):
        if key not in _VALUES:
            if default is not None:
                return default
            raise KeyError(f"未登记的配置键: {key}（请先在 rwlib/tuning.py 的 DEFAULTS 登记）")
        return _VALUES[key]

    def source(self, key):
        return _SOURCES.get(key, "?")

    def path(self, key):
        """相对路径 → ROOT 下的绝对路径（已是绝对路径则原样返回）。"""
        raw = str(self.get(key))
        p = Path(raw)
        return p if p.is_absolute() else (ROOT / p)

    def set(self, key, value):
        """运行时覆盖（等价 --set；已是配置键才生效）。"""
        if key not in _VALUES:
            raise KeyError(f"未登记的配置键: {key}")
        _OVERRIDES[key] = value
        _VALUES[key] = value
        _SOURCES[key] = "cli:--set"

    def section(self, name):
        prefix = name + "."
        return {k[len(prefix):]: v for k, v in _VALUES.items() if k.startswith(prefix)}

    def as_dict(self):
        return dict(_VALUES)

    # ── 继承链深度：推导优先（软编码的核心）──────────────────────────
    def derive_inherit_depth(self, jar=None):
        """从 jar 实际字节码推导「最大继承链深度」（不猜常数）。

        返回: int（链上游戏类层数，未到 Object）。推导失败 → fallback_depth。
        """
        from rwlib.bytecode import extract_class_refs_from_bytes

        jar_path = Path(jar) if jar else self.path("inherit.depth_probe_jar")
        if jar is None and not jar_path.exists():
            jar_path = ROOT.parent / "game-lib.jar"
        if not jar_path.exists():
            print(f"[tuning] 警告：{jar_path} 不存在，继承深度用兜底值 "
                  f"{self.get('inherit.fallback_depth')}", file=sys.stderr)
            return int(self.get("inherit.fallback_depth"))

        ceiling = int(self.get("inherit.depth_ceiling"))
        supers, counted = {}, 0
        with zipfile.ZipFile(jar_path) as z:
            for name in z.namelist():
                if not name.endswith(".class"):
                    continue
                try:
                    info = extract_class_refs_from_bytes(z.read(name))
                except Exception:  # noqa: BLE001 — 单类解析失败不应中断推导
                    continue
                if info:
                    supers[name[:-6].replace("/", ".")] = info["super_class"]
                    counted += 1
        best = 0
        for cls in supers:
            cur, depth = cls, 0
            seen = set()
            while cur and cur not in seen and depth < ceiling:
                seen.add(cur)
                if cur == "java.lang.Object" or cur.startswith(("java.", "javax.", "jdk.", "sun.")):
                    break
                nxt = supers.get(cur)
                if not nxt:
                    break
                depth += 1
                cur = nxt
            best = max(best, depth)
        if counted == 0:
            return int(self.get("inherit.fallback_depth"))
        return best

    def inherit_depth(self, jar=None, force=False):
        """实际使用的继承链上限：显式配置优先，`0` 走推导（带缓存）。"""
        configured = int(self.get("inherit.max_depth"))
        if configured > 0:
            return configured
        key = str(jar or self.get("inherit.depth_probe_jar"))
        if force or key not in _DEPTH_CACHE:
            _DEPTH_CACHE[key] = self.derive_inherit_depth(jar)
        return _DEPTH_CACHE[key]

    def walk_depth(self, jar=None):
        """**遍历用**的层数上限（消费方只应调用本方法，勿自己 ±1）。

        · 显式配置（>0）时视为最终上限，直接使用；
        · 自动推导时 +1 —— 推导值计的是「游戏类之间的跳数」，
          多出的这一跳用于覆盖 `java/lang/Object` 那一层。
          （历史上 `depth=8` 与真实 9 层差一层，正是漏了这一跳。）
        """
        configured = int(self.get("inherit.max_depth"))
        if configured > 0:
            return configured
        return self.inherit_depth(jar) + 1

    def game_root(self):
        """游戏根目录（相对项目根解析）。"""
        raw = str(self.get("member_refs.game_root"))
        p = Path(raw)
        return p if p.is_absolute() else (ROOT / p).resolve()

    def extra_jars(self):
        """→ 补充类宇宙 jar 路径列表（相对游戏根的 glob）。"""
        game = self.game_root()
        out = []
        for pat in self.get("member_refs.extra_jars"):
            out.extend(sorted(game.glob(pat)))
        return out

    def describe(self):
        """→ [(key, value, source)]，供 --print 与审计使用。"""
        return [(k, _VALUES[k], _SOURCES.get(k, "?")) for k in sorted(_VALUES)]


T = _Tuning()


def main(argv=None):
    argv = list(sys.argv[1:] if argv is None else argv)
    rc = 0
    if "--set" in argv:
        i = argv.index("--set")
        for pair in argv[i + 1:]:
            if "=" not in pair:
                break
            key, val = pair.split("=", 1)
            ref = _flatten(DEFAULTS).get(key)
            T.set(key, _coerce(val, ref))
    if "--write-template" in argv:
        out = Path(argv[argv.index("--write-template") + 1])
        out.write_text(json.dumps(DEFAULTS, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
        print(f"已写出模板: {out}")
    if "--derive-depth" in argv:
        jar = None
        i = argv.index("--derive-depth")
        if len(argv) > i + 1 and not argv[i + 1].startswith("--"):
            jar = argv[i + 1]
        depth = T.derive_inherit_depth(jar)
        # 推导值按「游戏类层数」计；实际遍历需要把它 +1（Object 那一跳）才安全，
        # 这就是历史上 depth=8 差一层的根因，故此处显式 +1。
        print(f"推导的继承链深度（游戏类层数）: {depth}")
        print(f"实际使用的遍历上限（+1 覆盖 Object 一跳）: {depth + 1}")
    if "--json" in argv:
        print(json.dumps({"values": T.as_dict(),
                          "sources": {k: T.source(k) for k in T.as_dict()}},
                         ensure_ascii=False, indent=2))
    elif "--print" in argv or not argv:
        print(f"rwlib.tuning — 软编码参数（{len(_VALUES)} 键）")
        print(f"  ROOT = {ROOT}")
        print(f"  配置文件 = {os.environ.get(_FILE_ENV) or (ROOT / _DEFAULT_FILE)}"
              f"{'' if Path(os.environ.get(_FILE_ENV) or (ROOT / _DEFAULT_FILE)).exists() else '（不存在，用默认值）'}")
        width = max(len(k) for k, _, _ in T.describe())
        for k, v, src in T.describe():
            print(f"  {k.ljust(width)}  {str(v)[:56].ljust(56)}  [{src}]")
    return rc


if __name__ == "__main__":
    sys.exit(main())
