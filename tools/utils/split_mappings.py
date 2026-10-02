#!/usr/bin/env python3
"""
split_mappings.py — 按游戏功能域拆分 supplement.csv

读取主映射数据库，将 6,726 条映射按 12 个游戏功能域分类，
每个域输出独立的 CSV 文件到 mappings/domains/。

使用方式:
    cd rw-reverse && python tools/utils/split_mappings.py

域分类基于 docs/ 中已有的 23 个系统文档 + CLASS_CATALOG 的 21 系统表。
"""

import csv
import sys
from pathlib import Path
from collections import defaultdict

# rwlib 路径
ROOT = Path(__file__).resolve().parent.parent.parent
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.mappings import load_supplement
from rwlib.config import MAPPINGS_DIR

# ── 输出目录 ────────────────────────────────────────────────────────
DOMAINS_DIR = MAPPINGS_DIR / "domains"
DOMAINS_DIR.mkdir(parents=True, exist_ok=True)

SUPPLEMENT_COLS = [
    'type', 'obfuscated_package', 'obfuscated_class',
    'obfuscated_member', 'meaningful_name', 'notes', 'verified'
]

# ── 短包名 → 完整包名 ──────────────────────────────────────────────
# supplement.csv 中存在混合命名：部分行用短包名 (f, m, d, a.a)
SHORT_PKG_MAP = {
    'f': 'com.corrodinggames.rts.gameFramework.f',       # GameUtils + InGameUI
    'm': 'com.corrodinggames.rts.gameFramework.m',       # EffectConfig / 渲染
    'd': 'com.corrodinggames.rts.game.units.d',          # 建筑 (units.d 为主)
    'a.a': 'a.a',                                        # 可靠UDP库 (root-level)
}


def normalize_pkg(pkg):
    """将短包名规范化为完整包名"""
    return SHORT_PKG_MAP.get(pkg, pkg)


def get_domain(pkg, cls):
    """按**混淆包名**返回新 14 域序号（1-14）。规则见本函数内 _PKG_DOMAIN。"""
    pkg = normalize_pkg(pkg)
    # ── 混淆包 → 新域（按**类体积**重排；由 build/_domain_bridge.json 生成）──
    _PKG_DOMAIN = {
        'a.a': 10,
        'a.a.a': 10,
        'a.a.a.g': 10,
        'android.net.http': 14,
        'com.corrodinggames.librocket': 13,
        'com.corrodinggames.librocket.scripts': 13,
        'com.corrodinggames.rts.a.a': 12,
        'com.corrodinggames.rts.appFramework': 12,
        'com.corrodinggames.rts.game': 6,
        'com.corrodinggames.rts.game.a': 5,
        'com.corrodinggames.rts.game.b': 6,
        'com.corrodinggames.rts.game.units': 1,
        'com.corrodinggames.rts.game.units.a': 2,
        'com.corrodinggames.rts.game.units.b': 2,
        'com.corrodinggames.rts.game.units.custom': 3,
        'com.corrodinggames.rts.game.units.custom.a': 3,
        'com.corrodinggames.rts.game.units.custom.b': 4,
        'com.corrodinggames.rts.game.units.custom.c': 3,
        'com.corrodinggames.rts.game.units.custom.d': 3,
        'com.corrodinggames.rts.game.units.custom.e': 4,
        'com.corrodinggames.rts.game.units.custom.f': 2,
        'com.corrodinggames.rts.game.units.d': 2,
        'com.corrodinggames.rts.game.units.d.a': 2,
        'com.corrodinggames.rts.game.units.e': 1,
        'com.corrodinggames.rts.game.units.f': 2,
        'com.corrodinggames.rts.game.units.g': 1,
        'com.corrodinggames.rts.game.units.h': 2,
        'com.corrodinggames.rts.game.units.units.d': 2,
        'com.corrodinggames.rts.gameFramework': 7,
        'com.corrodinggames.rts.gameFramework.a': 7,
        'com.corrodinggames.rts.gameFramework.b': 8,
        'com.corrodinggames.rts.gameFramework.c': 7,
        'com.corrodinggames.rts.gameFramework.d': 8,
        'com.corrodinggames.rts.gameFramework.e': 7,
        'com.corrodinggames.rts.gameFramework.f': 9,
        'com.corrodinggames.rts.gameFramework.f.a': 9,
        'com.corrodinggames.rts.gameFramework.g': 9,
        'com.corrodinggames.rts.gameFramework.h': 7,
        'com.corrodinggames.rts.gameFramework.i': 7,
        'com.corrodinggames.rts.gameFramework.j': 10,
        'com.corrodinggames.rts.gameFramework.k': 6,
        'com.corrodinggames.rts.gameFramework.m': 8,
        'com.corrodinggames.rts.gameFramework.m.e': 8,
        'com.corrodinggames.rts.gameFramework.n': 5,
        'com.corrodinggames.rts.gameFramework.utility': 11,
        'com.corrodinggames.rts.gameFramework.utility.a': 11,
        'com.corrodinggames.rts.java': 12,
        'com.corrodinggames.rts.java.audio.a': 12,
        'com.corrodinggames.rts.java.c': 12,
        'd': 2,
        'game.a.i': 5,
        'game.b.a': 6,
        'game.b.b': 6,
        'game.b.k': 6,
        'gameFramework.effects': 8,
        'gameFramework.m.e': 8,
    }

    # 完整包名精确匹配
    if pkg in _PKG_DOMAIN:
        return _PKG_DOMAIN[pkg]
    # 前缀匹配（子包，取最长前缀）
    best, best_len = None, -1
    for cand, dnum in _PKG_DOMAIN.items():
        if pkg.startswith(cand + ".") and len(cand) > best_len:
            best, best_len = dnum, len(cand)
    if best is not None:
        return best
    # 兜底：短包名还原后再试一次（normalize_pkg 已做过一次）
    return 12



# ── 域名映射 ────────────────────────────────────────────────────────
DOMAIN_NAMES = {
    1:  ('01-units', 'units'),
    2:  ('02-unit-actions', 'unit-actions'),
    3:  ('03-custom-core', 'custom-core'),
    4:  ('04-custom-logic', 'custom-logic'),
    5:  ('05-ai', 'ai'),
    6:  ('06-world', 'world'),
    7:  ('07-engine', 'engine'),
    8:  ('08-rendering', 'rendering'),
    9:  ('09-ui-audio', 'ui-audio'),
    10:  ('10-network', 'network'),
    11:  ('11-utility', 'utility'),
    12:  ('12-platform', 'platform'),
    13:  ('13-librocket', 'librocket'),
    14:  ('14-thirdparty', 'thirdparty'),
}

DOMAIN_CLASSES = {
    1:  'UnitInstance(am), UnitType(y), MovableUnit(x), WeaponType, WeaponAction, 投射物',
    2:  'GameAction(s), AttackAction(d), BuildAction(g), Command, CommandSlot, 建筑, 寻路(单位侧), 调试',
    3:  'CustomUnitType(j), ModUnitRegistry, ResourceComponent, INI 配置, 条件, 资源',
    4:  'LogicBoolean(215 类, 全为 $ 类), 动画(anim/animation), 特效, 自定义动作',
    5:  'GameWorld(a.a), AIWaveSystem, AITask, MissionParser, MissionExecutor',
    6:  'MapEngine(b.b), MapRenderer(b.c), MapLayer, TMSP, GameEngine(本级), PathFinder(k)',
    7:  'GlobalState, GameObject, ReplayEngine, GameSaver, 命令, Mods, Steam, 平台适配, 文件系统',
    8:  'EffectConfig(m), OpenGL(b), 渲染管线, 绘制特效',
    9:  'InGameUI(f.g), HUDManager, UI 面板, SoundFactory',
    10:  'NetEngine(j.ad), InputNetStream, OutputNetStream, 可靠 UDP(a.a)',
    11:  'GameUtils, RingBuffer, CustomArrayList, DataField, 序列化工具',
    12:  'AppFramework, JDK 仿真层(java.audio/input/filesystem), platform.net, 根类',
    13:  'LibRocket UI 脚本引擎(scripts)',
    14:  'Steamworks 绑定(codedisaster), org.a.*(第三方), 杂项(com)',
}

# ── InGameUI 特例处理 ────────────────────────────────────────────────
# gameFramework.f.g (InGameUI) 在渲染域，其余 gameFramework.f 在工具域

def classify_row(row):
    """返回 (domain_number, normalized_pkg)"""
    pkg = row.get('obfuscated_package', '')
    cls = row.get('obfuscated_class', '')
    norm_pkg = normalize_pkg(pkg)
    domain = get_domain(pkg, cls)
    return domain, norm_pkg



def main():
    print("=" * 60)
    print("split_mappings.py — 按功能域拆分 supplement.csv")
    print("=" * 60)

    # 加载
    header, rows = load_supplement()
    print(f"\n加载: {len(rows)} 条映射")

    # 分类
    domains = defaultdict(list)
    stats = defaultdict(lambda: {'fields': 0, 'methods': 0, 'classes': set()})

    for row in rows:
        domain, norm_pkg = classify_row(row)
        domains[domain].append(row)
        stats[domain]['fields' if row.get('type') == 'field' else 'methods'] += 1
        stats[domain]['classes'].add(f"{norm_pkg}.{row.get('obfuscated_class', '')}")

    # 写入域文件
    print(f"\n写入 {DOMAINS_DIR}:")
    total_written = 0

    for dnum in sorted(DOMAIN_NAMES.keys()):
        fname, cname = DOMAIN_NAMES[dnum]
        d_rows = domains.get(dnum, [])
        d_stats = stats[dnum]

        fpath = DOMAINS_DIR / f"{fname}.csv"
        with open(fpath, 'w', encoding='utf-8', newline='') as f:
            writer = csv.DictWriter(f, fieldnames=SUPPLEMENT_COLS, extrasaction='ignore')
            writer.writeheader()
            for row in d_rows:
                writer.writerow(row)

        total_written += len(d_rows)
        print(f"  {fname}.csv: {len(d_rows):>5} 条 "
              f"({d_stats['fields']}字段 + {d_stats['methods']}方法) "
              f"[{len(d_stats['classes'])}类] — {cname}")

    # 验证
    print(f"\n验证: 写入合计 {total_written} 条, 原始 {len(rows)} 条")
    if total_written == len(rows):
        print("[OK] 数量一致，无遗漏")
    else:
        print(f"[ERR] 差异: {len(rows) - total_written} 条未分配!")

    # 生成 README
    generate_readme(stats, total_written)
    print(f"\n[OK] README 已生成: {DOMAINS_DIR / 'README.md'}")


def generate_readme(stats, total):
    """生成 domains/README.md 索引文档"""
    lines = [
        "# mappings/domains/ — 按游戏功能域拆分的映射库",
        "",
        f"> 自动生成 | 总计 {total:,} 条映射 | {len(DOMAIN_NAMES)} 个功能域",
        "",
        "## 概览",
        "",
        "`supplement.csv` 是主映射数据库，本目录将其按游戏功能系统拆分为独立文件，",
        "便于按功能域进行针对性的解混淆工作。",
        "",
        "| # | 域文件 | 映射数 | 类数 | 核心类 |",
        "|---|--------|--------|------|--------|",
    ]

    for dnum in sorted(DOMAIN_NAMES.keys()):
        fname, cname = DOMAIN_NAMES[dnum]
        d_stats = stats[dnum]
        count = d_stats['fields'] + d_stats['methods']
        nclass = len(d_stats['classes'])
        classes = DOMAIN_CLASSES.get(dnum, '')
        lines.append(f"| {dnum} | [{fname}.csv]({fname}.csv) | {count:,} | {nclass} | {classes} |")

    lines += [
        "",
        "## 详细说明",
        "",
    ]

    descriptions = {
        1: "**单位实例、类型、武器、队伍追踪**。\n\n"
           "包含 UnitInstance(am) 的所有字段和方法、UnitType(y) 的类型树、"
           "MovableUnit(x) 的移动接口、WeaponTypeEnum(av)、WeaponAction(au)、"
           "UnitRegistry(ar) 和 UnitTypeHandle(as)。\n\n"
           "参见: `docs/01-units/UNIT-LIFECYCLE.md`, `UNIT-LOADING.md`, `WEAPON-DAMAGE.md`",

        2: "**工厂、建造队列、建筑类型**。\n\n"
           "包含 Factory(h) 的建造逻辑、CommandCenter(d.e)、ExperimentalUnit(d.d)、"
           "BuilderUnit(d.j)、Building(e.c) 基类和 Structures(d.t)。\n\n"
           "参见: `docs/02-buildings/FACTORY.md`",

        3: "**15种 GameAction + Command 序列化**。\n\n"
           "包含 GameAction(s) 基类及所有子类：Attack(d)、Build(g)、Guard、Patrol、"
           "Stop、Sell、Repair、Reclaim、Ping、MapPing、TeamChat、RallyPoint 等。"
           "Command(e) 二进制序列化和 CommandController(c)。\n\n"
           "参见: `docs/03-actions/UNIT-ACTIONS.md`, `GAME-ACTION-METHODS.md`",

        4: "**AI 玩家和任务引擎**。\n\n"
           "包含 GameWorld(a.a) 三层时钟系统（0.25/2.0/4.5s）、Zone 系统、"
           "UnitGroup 状态机；AIWaveSystem(n.f) 波次管理、AITask、MissionParser、"
           "MissionExecutor、MissionEvent、AISpawnList。\n\n"
           "参见: `docs/04-ai/AI-ARCHITECTURE.md`",

        5: "**TMX 地图加载和渲染**。\n\n"
           "包含 MapEngine(b.b)、MapRenderer(b.c)、MapLayer(b.g)、MapSpawn、"
           "战争迷雾和地形系统。\n\n"
           "参见: `docs/05-map/MAP-SYSTEM.md`",

        6: "**3层网络协议栈**。\n\n"
           "包含 NetEngine(j.ad) 主网络引擎、InputNetStream(j.k)、OutputNetStream(j.as)、"
           "PlayerConnect(j.c)；可靠UDP传输层 (a.a.*)。\n\n"
           "参见: `docs/06-network/NETWORK-STACK.md`, `NETWORK-PROTOCOL.md`",

        7: "**全局状态、主循环、回放、统计、玩家**。\n\n"
           "包含 GlobalState(l) 引擎单例、GameObject(w) 实体基类、"
           "ReplayEngine(ba) 回放、GameSaver(y) 存档、StatsManager(bg) 统计、"
           "PlayerState(n)、GameScreen(i) 主界面。\n\n"
           "参见: `docs/07-engine/GAMELOOP.md`, `MATCH-LIFECYCLE.md`",

        8: "**OpenGL ES 2.0 渲染、UI、音频**。\n\n"
           "包含 EffectConfig(m) 特效引擎、InGameUI(f.g) 游戏界面、"
           "HUDManager(d) 抬头显示、SoundFactory(a) 音频引擎、"
           "Slick2DRenderer 桌面渲染。\n\n"
           "参见: `docs/08-rendering/RENDERING.md`, `AUDIO-HUD.md`",

        9: "**Mod 系统和自定义单位**。\n\n"
           "包含 CustomUnitType(j) 自定义单位类型、ModUnitRegistry(l) Mod注册表、"
           "ResourceComponent(d.b) 资源成本、LogicBoolean 脚本引擎(215类)、"
           "INI 配置解析、TeamTag 和所有 custom.* 子包。\n\n"
           "参见: `docs/09-custom/CUSTOM-UNIT.md`, `LOGIC-ENGINE.md`, `INI-PARSING.md`",

        10: "**A* 寻路和空间查询**。\n\n"
            "包含 PathFinder(k) A*引擎、PathSolver、AStarSearch、NodePool、"
            "MovementController(f) 移动控制器、SpatialGrid(cc) 空间网格。\n\n"
            "参见: `docs/10-pathfinding/ASTAR-PATHFINDING.md`, `SPATIAL.md`",

        11: "**平台抽象层**。\n\n"
            "包含 Steamworks API 绑定、LibRocket UI 框架、AppFramework 应用框架、"
            "KeyBindingManager(ac) 按键管理、android/javax 桩代码。\n\n"
            "参见: `docs/11-platform/` (待补充)",

        12: "**引擎工具类和数据结构**。\n\n"
            "包含 GameUtils(f) 数学工具（含 360° 三角函数表）、CustomArrayList(m)、"
            "RingBuffer(g)、DequeList(o)、DataField 序列化、本地化、文件IO。\n\n"
            "参见: `docs/12-utility/DEVELOPER-COMMENTS.md`",
    }

    for dnum in sorted(DOMAIN_NAMES.keys()):
        fname, cname = DOMAIN_NAMES[dnum]
        lines.append(f"### {dnum}. {cname} (`{fname}.csv`)")
        lines.append("")
        lines.append(descriptions.get(dnum, "（待补充）"))
        lines.append("")

    # 使用说明
    lines += [
        "---",
        "",
        "## 使用说明",
        "",
        "### 与 supplement.csv 的关系",
        "- `supplement.csv` 是**唯一主数据库**，本目录的域文件是其快照",
        "- 新增映射应添加到 `supplement.csv`，然后重新运行 `split_mappings.py` 更新域文件",
        "- 域文件用于**查询特定功能域的所有映射**，不用于编辑",
        "",
        "### 重新生成",
        "```bash",
        "cd rw-reverse && python tools/utils/split_mappings.py",
        "```",
        "",
        "### 与文档的对应",
        "每个域文件对应 `docs/` 中的一个或多个系统文档，详见上方各域说明中的 `参见` 链接。",
        "",
        "---",
        "",
        "## 统计摘要",
        "",
        f"| 指标 | 数值 |",
        f"|------|------|",
        f"| 总映射数 | {total:,} |",
        f"| 功能域数 | {len(DOMAIN_NAMES)} |",
        f"| 字段映射 | {sum(s['fields'] for s in stats.values()):,} |",
        f"| 方法映射 | {sum(s['methods'] for s in stats.values()):,} |",
        f"| 覆盖类数 | {sum(len(s['classes']) for s in stats.values())} (含跨域重复) |",
        "",
        f"> 生成日期: 2026-08-10 | 工具: `tools/utils/split_mappings.py`",
    ]

    with open(DOMAINS_DIR / "README.md", 'w', encoding='utf-8') as f:
        f.write('\n'.join(lines))


if __name__ == '__main__':
    main()
