#!/usr/bin/env python3
"""
split_mappings.py — 按游戏功能域拆分 supplement.csv

读取主映射数据库，将 6,726 条映射按 12 个游戏功能域分类，
每个域输出独立的 CSV 文件到 mappings/domains/。

使用方式:
    cd rw源码逆向 && python tools/utils/split_mappings.py

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
    """
    根据包名和类名确定功能域。

    返回: int (1-12), 域序号
    """
    pkg = normalize_pkg(pkg)
    fqn = f"{pkg}.{cls}" if pkg and cls else pkg

    # 域1: 单位核心 — UnitInstance, UnitType, MovableUnit, WeaponType, UnitRegistry 等
    # 匹配 package=com.corrodinggames.rts.game.units 且 class 属于单位相关
    if pkg == 'com.corrodinggames.rts.game.units':
        unit_classes = {'am', 'y', 'x', 'ao', 'ap', 'au', 'av', 'ar', 'as',
                        'g', 'ad', 'ae', 'o', 'q', 't', 'u', 'v', 'z',
                        'aa', 'ab', 'ac', 'af', 'ag', 'ah', 'ai', 'aj',
                        'ak', 'al', 'an', 'aq', 'at', 'aw', 'ax', 'ay', 'az'}
        if cls in unit_classes or any(cls.startswith(c + '$') for c in unit_classes):
            return 1
    if pkg == 'com.corrodinggames.rts.game.units.b':
        return 1
    # game-level unit-related classes
    if fqn in ('com.corrodinggames.rts.game.g',       # Projectile
               'com.corrodinggames.rts.game.s',       # TeamUnitTracker
               'com.corrodinggames.rts.game.p',       # UnitManager
               'com.corrodinggames.rts.game.t'):      # BuildQueue
        return 1
    if pkg in ('com.corrodinggames.rts.game.aa',      # ProjectileManager
               'com.corrodinggames.rts.game.ab'):     # ProjectileManager
        return 1
    # 战斗相关 (game.a.*, 排除 GameWorld a.a)
    if pkg.startswith('com.corrodinggames.rts.game.a'):
        combat_classes = {'g', 'b', 'c', 'e', 'i'}
        if cls in combat_classes:
            return 1

    # 域2: 建筑/工厂 — Factory, CommandCenter, BuilderUnit, Building
    if any(pkg.startswith(prefix) for prefix in [
        'com.corrodinggames.rts.game.units.h',     # Factory
        'com.corrodinggames.rts.game.units.d.e',   # CommandCenter
        'com.corrodinggames.rts.game.units.d.d',   # ExperimentalUnit
        'com.corrodinggames.rts.game.units.d.j',   # BuilderUnit
        'com.corrodinggames.rts.game.units.d.l',   # CarrierUnit
        'com.corrodinggames.rts.game.units.d.t',   # Structures
        'com.corrodinggames.rts.game.units.e.c',   # Building
    ]):
        return 2
    # 建筑相关的 d 包子类
    if pkg.startswith('com.corrodinggames.rts.game.units.d.a'):  # Builder子类
        return 2
    if pkg.startswith('com.corrodinggames.rts.game.units.d'):
        return 2  # units.d 其余类→建筑域

    # 域3: 指令系统 — 15种 GameAction + Command + CommandController
    if any(pkg.startswith(prefix) for prefix in [
        'com.corrodinggames.rts.game.units.a.',   # 所有 GameAction 子类
        'com.corrodinggames.rts.game.units.a',    # GameAction 基类
    ]):
        return 3
    if any(pkg.startswith(prefix) for prefix in [
        'com.corrodinggames.rts.gameFramework.e',  # Command
        'com.corrodinggames.rts.gameFramework.c',  # CommandController
    ]):
        return 3

    # 域4: AI系统 — GameWorld + AIWaveSystem + AITask + MissionEngine
    if any(pkg.startswith(prefix) for prefix in [
        'com.corrodinggames.rts.game.a.a',        # GameWorld (AIPlayer)
        'com.corrodinggames.rts.game.a',          # AI 包其余 (战斗相关已归域1)
        'com.corrodinggames.rts.gameFramework.n', # AIWaveSystem 等
    ]):
        return 4

    # 域5: 地图系统 — MapEngine + MapRenderer + TMX
    if any(pkg.startswith(prefix) for prefix in [
        'com.corrodinggames.rts.game.b',          # MapEngine + MapRenderer
    ]):
        return 5

    # 域6: 网络通信 — NetEngine + 可靠UDP
    if any(pkg.startswith(prefix) for prefix in [
        'com.corrodinggames.rts.gameFramework.j', # NetEngine + 流
        'a.a',                                     # 可靠UDP
    ]):
        return 6

    # 域7: 引擎核心 — GlobalState + GameObject + ReplayEngine + GameSaver + Stats + PlayerState
    if any(pkg.startswith(prefix) for prefix in [
        'com.corrodinggames.rts.gameFramework.l',  # GlobalState
        'com.corrodinggames.rts.gameFramework.w',  # GameObject
        'com.corrodinggames.rts.gameFramework.ba', # ReplayEngine
        'com.corrodinggames.rts.gameFramework.bb', # BackgroundWriter
        'com.corrodinggames.rts.gameFramework.bd', # DataBlock
        'com.corrodinggames.rts.gameFramework.y',  # GameSaver
        'com.corrodinggames.rts.gameFramework.bg', # StatsManager
        'com.corrodinggames.rts.gameFramework.bn', # StatsHistory
        'com.corrodinggames.rts.gameFramework.bo', # StatsRecord
        'com.corrodinggames.rts.gameFramework.bh', # StatsSample
        'com.corrodinggames.rts.gameFramework.bl', # PeriodicTimer
        'com.corrodinggames.rts.gameFramework.bj', # StatsCategory
        'com.corrodinggames.rts.gameFramework.bq', # BaseGameObject
        'com.corrodinggames.rts.gameFramework.br', # ExtraManager
        'com.corrodinggames.rts.gameFramework.z',  # GameThread
        'com.corrodinggames.rts.game.n',           # PlayerState
        'com.corrodinggames.rts.game.i',           # GameScreen
        'com.corrodinggames.rts.game.o',           # 游戏状态
        'com.corrodinggames.rts.game.q',           # 游戏状态
        'com.corrodinggames.rts.game.r',           # 游戏状态
    ]):
        return 7

    # 域8: 渲染系统 — EffectConfig + InGameUI + Slick2D + 音频
    if any(pkg.startswith(prefix) for prefix in [
        'com.corrodinggames.rts.gameFramework.m',  # EffectConfig / 渲染
        'com.corrodinggames.rts.gameFramework.d',  # HUDManager / DrawEffect
        'com.corrodinggames.rts.gameFramework.f.g',# InGameUI (在gameFramework.f中)
        'com.corrodinggames.rts.gameFramework.a',  # SoundFactory
        'com.corrodinggames.rts.java.audio',       # Slick2D 音频
        'com.corrodinggames.rts.java',             # Slick2DRenderer
        'java.audio',                              # Java 音频桩
    ]):
        return 8

    # 域9: 自定义/Mod — CustomUnitType + ModUnitRegistry + LogicBoolean + INI
    if any(pkg.startswith(prefix) for prefix in [
        'com.corrodinggames.rts.game.units.custom',# 整个 custom 包
    ]):
        return 9

    # 域10: 寻路 — A* + SpatialGrid + MovementController
    if any(pkg.startswith(prefix) for prefix in [
        'com.corrodinggames.rts.gameFramework.k',  # PathFinder + A* engine
        'com.corrodinggames.rts.game.units.f',     # SpatialGrid
        'com.corrodinggames.rts.game.f',           # MovementController
    ]):
        return 10

    # 域11: 平台层 — steamworks + librocket + AppFramework + android/java桩
    if any(pkg.startswith(prefix) for prefix in [
        'com.codedisaster.steamworks',
        'com.corrodinggames.librocket',
        'com.corrodinggames.rts.appFramework',
        'android',
        'javax',
        'sun',
        'org',                                     # steamworks native
    ]):
        return 11
    # 平台相关的 gameFramework 子包
    if any(pkg.startswith(prefix) for prefix in [
        'com.corrodinggames.rts.gameFramework.ac', # KeyBindingManager
        'com.corrodinggames.rts.gameFramework.ad', # 输入
        'com.corrodinggames.rts.gameFramework.af', # 输入轴
        'com.corrodinggames.rts.gameFramework.ag', # 输入
        'com.corrodinggames.rts.gameFramework.ah', # 输入
        'com.corrodinggames.rts.gameFramework.i',  # ModInfo/版本检查
    ]):
        return 11

    # 域12: 工具/数据结构 — GameUtils + RingBuffer + 序列化工具 + 本地化
    if any(pkg.startswith(prefix) for prefix in [
        'com.corrodinggames.rts.gameFramework.f',  # GameUtils + InGameUI (已分离InGameUI)
        'com.corrodinggames.rts.gameFramework.utility',
        'com.corrodinggames.rts.gameFramework.g',  # DataField
        'com.corrodinggames.rts.gameFramework.u',  # 工具
        'com.corrodinggames.rts.gameFramework.z',  # 工具
        'com.corrodinggames.rts.gameFramework.ab', # 工具
        'com.corrodinggames.rts.gameFramework.e',  # 文件IO (非Command部分)
        'com.corrodinggames.rts.gameFramework.fw', # 本地化
        'com.corrodinggames.rts.gameFramework.ae', # 工具
    ]):
        return 12
    # gameFramework 剩余 → 工具域
    if pkg.startswith('com.corrodinggames.rts.gameFramework'):
        return 12

    # game 剩余 → 引擎核心
    if pkg.startswith('com.corrodinggames.rts.game'):
        return 7

    # com.corrodinggames 剩余 → 平台
    if pkg.startswith('com.corrodinggames'):
        return 11

    # 其他 → 平台
    return 11


# ── 域名映射 ────────────────────────────────────────────────────────
DOMAIN_NAMES = {
    1:  ('01-units',        '单位核心'),
    2:  ('02-buildings',    '建筑/工厂'),
    3:  ('03-actions',      '指令系统'),
    4:  ('04-ai',           'AI系统'),
    5:  ('05-map',          '地图系统'),
    6:  ('06-network',      '网络通信'),
    7:  ('07-engine',       '引擎核心'),
    8:  ('08-rendering',    '渲染与音频'),
    9:  ('09-custom',       '自定义/Mod'),
    10: ('10-pathfinding',  '寻路系统'),
    11: ('11-platform',     '平台层'),
    12: ('12-utility',      '工具/数据结构'),
}

DOMAIN_CLASSES = {
    1:  'UnitInstance(am), UnitType(y), MovableUnit(x), WeaponType, UnitRegistry, TeamUnitTracker, CombatMain',
    2:  'Factory(h), CommandCenter(d.e), BuilderUnit(d.j), Building(e.c), ExperimentalUnit(d.d)',
    3:  'GameAction(s), AttackAction(d), BuildAction(g), GuardAction, Patrol, Stop, Command(e), CommandController(c)',
    4:  'GameWorld(a.a), AIWaveSystem(n.f), AITask, MissionParser, MissionExecutor, AISpawnList',
    5:  'MapEngine(b.b), MapRenderer(b.c), MapLayer(b.g), MapSpawn, TMX解析',
    6:  'NetEngine(j.ad), InputNetStream(j.k), OutputNetStream(j.as), PlayerConnect, 可靠UDP(a.a)',
    7:  'GlobalState(l), GameObject(w), ReplayEngine(ba), GameSaver(y), StatsManager, PlayerState(n), GameScreen(i)',
    8:  'EffectConfig(m), InGameUI(f.g), HUDManager(d), SoundFactory(a), Slick2DRenderer',
    9:  'CustomUnitType(j), ModUnitRegistry(l), ResourceComponent, LogicBoolean(215类), INI解析',
    10: 'PathFinder(k), AStarSearch, SpatialGrid(cc), MovementController(f)',
    11: 'Steamworks, LibRocket, AppFramework, KeyBindingManager, android/javax桩, ModInfo',
    12: 'GameUtils(f), RingBuffer, CustomArrayList, DataField, 本地化, 文件IO, 序列化工具',
}

# ── InGameUI 特例处理 ────────────────────────────────────────────────
# gameFramework.f.g (InGameUI) 在渲染域，其余 gameFramework.f 在工具域
IN_GAME_UI_CLASSES = {'g', 'InGameUI'}


def classify_row(row):
    """返回 (domain_number, normalized_pkg)"""
    pkg = row.get('obfuscated_package', '')
    cls = row.get('obfuscated_class', '')
    norm_pkg = normalize_pkg(pkg)
    domain = get_domain(pkg, cls)

    # 特例: gameFramework.f.g (InGameUI) → 渲染域
    if norm_pkg == 'com.corrodinggames.rts.gameFramework.f':
        if cls in IN_GAME_UI_CLASSES or cls.startswith('g'):
            # 需要区分 f.g 和 f.* — cls 为 g 即 InGameUI
            if cls == 'g' or cls.startswith('g$'):
                domain = 8  # 渲染

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
        "cd rw源码逆向 && python tools/utils/split_mappings.py",
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
