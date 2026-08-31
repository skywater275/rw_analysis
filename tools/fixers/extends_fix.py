"""
Fix single-char extends/implements clauses in deobfuscated Java files.
Per-package resolution: same obfuscated char in same package maps to same class.
"""
import re, os, sys
from pathlib import Path
from collections import defaultdict
import csv

ROOT = Path(__file__).resolve().parent.parent.parent  # tools/fixers/ → ROOT
sys.path.insert(0, str(ROOT))
sys.path.insert(0, str(ROOT / "tools"))
from rwlib.config import DEOBFUSCATED_DIR as DEOBF, SUPPLEMENT_CSV as SUPP
DEOBFUSCATED = ROOT / "03-deobfuscated"
MAPPINGS = ROOT / "mappings" / "class-discoveries.csv"

def load_class_mappings():
    """Load class-discoveries.csv to build obfuscated->meaningful mapping per package."""
    pkg_map = defaultdict(dict)  # pkg -> {obf_simple: meaningful_simple}
    with open(MAPPINGS, 'r', encoding='utf-8') as f:
        reader = csv.reader(f)
        for row in reader:
            if len(row) < 4 or row[0] != 'class':
                continue
            obf_pkg = row[1]
            obf_class = row[2]
            meaningful = row[3]
            pkg_map[obf_pkg][obf_class] = meaningful
    return pkg_map

# MANUAL overrides for packages not fully covered by CSV
# Format: package_path -> {obf_char: readable_name}
MANUAL_OVERRIDES = {
    # gameFramework/m — rendering package
    "com/corrodinggames/rts/gameFramework/m": {
        'a': 'OpenGLRenderer', 'b': 'GLTextureRegion', 'e': 'Texture',
        'f': 'Sprite', 'g': 'TextureFrame', 'h': 'TeamColorTexture',
        'i': 'FileShader', 'j': 'CanvasRenderer', 'k': 'BitmapDrawer',
        'l': 'Renderer', 'm': 'DrawCommand', 'n': 'NullRenderer',
        'o': 'DrawContext', 'p': 'EffectConfig', 'q': 'ObjectPool',
        'r': 'ShaderParamHolder', 's': 'DrawStateHolder',
        't': 'DrawStateArray', 'u': 'AbstractRendererImpl',
        'v': 'CustomColorFilter', 'w': 'BlendModeEnum',
        'x': 'TextureManager', 'y': 'TextureManagerInterface',
        'z': 'NullSpriteBatchBackend',
    },
    # game/units — unit base classes
    "com/corrodinggames/rts/game/units": {
        'a': 'UnitFlag', 'b': 'PathResult', 'c': 'AbstractUnitBase',
        'd': 'ShapeProvider', 'e': 'BuildingBase', 'f': 'WaterUnit',
        'g': 'ExperimentalLandUnit', 'h': 'VeteranUnit', 'i': 'DecorUnitBase',
        'j': 'LandUnit', 'k': 'AirUnit', 'l': 'HoverUnit',
        'm': 'SubmarineUnit', 'n': 'UnitCategory', 'o': 'UnitBehaviorEnum',
        'p': 'UnitQueryFilter', 'q': 'SpecialActionType', 'r': 'UnitActionEnum',
        's': 'Position', 't': 'AmphibiousUnit', 'u': 'TimedBomb',
        'v': 'DecorUnit', 'w': 'CustomUnitBase', 'x': 'MovableUnit',
        'y': 'UnitType', 'z': 'WaypointTarget',
    },
    # game/units/d — experimental units
    "com/corrodinggames/rts/game/units/d": {
        'a': 'ExperimentalGroundUnit', 'b': 'CustomGroundUnit', 'c': 'ExperimentalHoverUnit',
        'f': 'ExperimentalWaterUnit', 'g': 'ExperimentalLandFactory',
        'h': 'PowerGeneratorUnit', 'i': 'MobileBuilderBase',
        'k': 'UnitFactoryHelper', 'm': 'ExperimentalBuilding',
        'n': 'CustomBuildingUnit', 'o': 'ExperimentalAirUnit',
        'p': 'ExperimentalSubUnit', 'q': 'UnitActionHelper',
        'r': 'RepairBayUnit', 's': 'AutoRepairCallback',
        'u': 'UpgradeToT2Action', 'v': 'FabricatorUnit',
        'w': 'ExperimentalWallUnit', 'y': 'UnitType',
    },
    # game/units/e — buildings
    "com/corrodinggames/rts/game/units/e": {
        'j': 'AbstractBuildingBase', 'h': 'AbstractSubBuilding',
        'x': 'MovableUnit', 'w': 'CustomUnitBase',
    },
    # game/units/a — commands/actions
    "com/corrodinggames/rts/game/units/a": {
        's': 'GameAction', 'p': 'AbstractCutsceneAction', 'w': 'AbstractBuildAction',
    },
    # game/units/b — behaviors
    "com/corrodinggames/rts/game/units/b": {
        'a': 'UnitBehavior', 'b': 'AbstractUnitBehavior',
        'x': 'MovableUnit', 'w': 'CustomUnitBase',
    },
    # game/a — AI/combat
    "com/corrodinggames/rts/game/a": {
        'a': 'GameWorld', 'b': 'CombatMain', 'c': 'AINukeStrategy',
        'd': 'NeutralPlayer', 'e': 'GameModeSelector', 'f': 'BuildTaskType',
        'g': 'UnitGroup', 'h': 'AIUnitGroupBase', 'i': 'BaseZone',
        'j': 'BaseZoneType', 'k': 'ZoneStageEnum', 'l': 'AIStrategyNode',
        'm': 'ProjectileManager', 'n': 'PlayerStateProxy',
        'o': 'AIStrategyNode',
    },
    # gameFramework/b — OpenGL
    "com/corrodinggames/rts/gameFramework/b": {
        'a': 'DefaultShader', 'b': 'GLObject', 'c': 'DefaultBlendMode',
        'q': 'GLUniform',
    },
    # gameFramework/n — AI spawning
    "com/corrodinggames/rts/gameFramework/n": {
        'e': 'MissionEventType', 'f': 'AISpawnController', 'g': 'AIWaveParser',
    },
    # a/a/a — network packets
    "a/a/a": {
        'h': 'Packet', 'a': 'AckPacket',
    },
    # appFramework
    "com/corrodinggames/rts/appFramework": {
        'b': 'AppActivity', 'c': 'InputConnection', 'd': 'DialogBase',
    },
    # gameFramework/e — file/storage
    "com/corrodinggames/rts/gameFramework/e": {
        'a': 'FileLoader', 'b': 'FileAccessFlags', 'c': 'StorageBackend',
        'd': 'PathStorage', 'e': 'DualStorage', 'f': 'NullStorage',
        'g': 'FilePathSanitizer', 'h': 'InputStreamHolder',
    },
    # gameFramework/d — HUD
    "com/corrodinggames/rts/gameFramework/d": {
        'a': 'GameHUD', 'b': 'CloudRenderer', 'c': 'HUDManager',
        'd': 'HUDAnchor', 'e': 'HUDElement', 'f': 'DrawEffect',
        'g': 'HUDElementRenderer', 'h': 'DrawLayer',
    },
    # gameFramework/c — debug
    "com/corrodinggames/rts/gameFramework/c": {
        'a': 'DebugServer', 'b': 'DebugSession', 'c': 'DebugMonitorTask',
    },
    # gameFramework/g — data fields
    "com/corrodinggames/rts/gameFramework/g": {
        'a': 'DataFieldProvider',
    },
    # gameFramework/f — in-game UI (parent of f/a)
    "com/corrodinggames/rts/gameFramework/f": {
        'a': 'InGameUI', 'b': 'Minimap',
    },
    # gameFramework/f/a — UI sub-package
    "com/corrodinggames/rts/gameFramework/f/a": {
        'a': 'UIPanel', 'b': 'UIButton', 'c': 'UIText',
        'd': 'UISlider', 'e': 'UIScrollView', 'f': 'UIListView',
        'g': 'UIElementBase', 'h': 'UITextField', 'i': 'UIImageView',
        'j': 'UIToggleButton', 'k': 'UILayoutBase', 'l': 'UIViewBase',
        'm': 'UILayoutParams', 'n': 'UIGridLayout',
    },
    # game — top-level game package
    "com/corrodinggames/rts/game": {
        'a': 'GameFlag', 'b': 'MapEngine', 'd': 'NeutralPlayer',
        'e': 'CommandController', 'f': 'MovementController',
        'g': 'MovementType', 'h': 'TagFilter', 'i': 'GameMode',
        'j': 'PlayerStats', 'k': 'TeamStats', 'l': 'GameScreen',
        'm': 'UnitTracker', 'n': 'PlayerState', 'o': 'CombatMain',
        'p': 'GameWorld', 'r': 'TeamManager', 's': 'GameStats',
        't': 'ResourceManager', 'u': 'Projectile', 'v': 'GameController',
        'w': 'GameObject', 'x': 'MapBorder',
    },
    # java/ — desktop platform (MERGED)
    "com/corrodinggames/rts/java": {
        'a': 'DesktopInput', 'b': 'DesktopMusic', 'c': 'DesktopFileSystem',
        'd': 'DisplayMessagePump', 'e': 'DesktopAppFramework', 'f': 'FontKey',
        'g': 'DesktopPlatform', 'h': 'DesktopRenderer', 'i': 'DesktopCanvas',
        'j': 'DesktopAudio', 'k': 'DesktopNetworking', 'l': 'DesktopUI',
        'm': 'DesktopStorage', 'n': 'DesktopPlatformImpl', 'o': 'DesktopLauncher',
        'p': 'DesktopSettings', 'q': 'DesktopConsole', 'r': 'JavaSound',
        's': 'SpriteProxy', 'y': 'GLRenderer',
    },
    # gameFramework/g — data fields
    "com/corrodinggames/rts/gameFramework/g": {
        'd': 'DataFieldCollector',
    },
    # gameFramework/f — in-game UI (MERGED)
    "com/corrodinggames/rts/gameFramework/f": {
        'a': 'InGameUI', 'b': 'Minimap', 'm': 'MessagePanel',
    },
    # gameFramework/b — OpenGL (MERGED)
    "com/corrodinggames/rts/gameFramework/b": {
        'a': 'DefaultShader', 'b': 'GLObject', 'c': 'DefaultBlendMode',
        'q': 'GLUniform', 'h': 'ShaderSource', 'j': 'BlendCallback',
        'k': 'RendererImpl', 'u': 'TextureGenerator',
    },
    # gameFramework/a — audio (MERGED)
    "com/corrodinggames/rts/gameFramework/a": {
        'a': 'AndroidSoundFactory', 'b': 'SoundSource', 'c': 'MusicTrack',
        'd': 'SoundEffect', 'e': 'AudioEngine', 'f': 'SoundPool',
        'g': 'AudioClip', 'h': 'SoundRegistry', 'i': 'Sound',
    },
    # a/a — reliable UDP
    "a/a": {
        'h': 'ReliableUDP', 's': 'ServerListener',
    },
    # librocket/scripts — third-party UI
    "com/corrodinggames/librocket/scripts": {
        'b': 'RocketDocument',
    },
    # rts/a — app context
    "com/corrodinggames/rts/a": {
        'c': 'AppContext',
    },
    # gameFramework/utility
    "com/corrodinggames/rts/gameFramework/utility": {
        'e': 'RunnableCallback', 'f': 'CallableCallback',
    },
    # org/a/a/c — third-party
    "org/a/a/c": {
        'c': 'BaseNode',
    },
    # gameFramework/j — network
    "com/corrodinggames/rts/gameFramework/j": {
        'a': 'ChatSystem', 'b': 'ChatMessage', 'c': 'SecurityHasher',
        'd': 'ReceiveWorker', 'e': 'SendWorker', 'f': 'DebugPacketBuilder',
        'g': 'GameServerInfo', 'h': 'SteamSocket', 'i': 'SteamInputStream',
        'j': 'SteamOutputStream', 'k': 'InputNetStream', 'n': 'WebAPIClient',
        'o': 'RequestTimeout', 'p': 'SelfInfoFetcher', 'q': 'ServerListLoader',
        'r': 'HttpClientPool', 's': 'UnitTypeComparator',
        'u': 'ServerListFetcher', 'v': 'ErrorReporter', 'w': 'ServerResult',
        'x': 'ServerStatus', 'y': 'MasterServerCreate', 'z': 'MasterServerRemove',
    },
    # gameFramework top-level (single-char extends targets)
    "com/corrodinggames/rts/gameFramework": {
        'w': 'GameObject', 'p': 'AssetLoader', 'o': 'KeyCodeMapper',
        'e': 'TestCaseCallback',
    },
    # gameFramework/k — pathfinding
    "com/corrodinggames/rts/gameFramework/k": {
        'a': 'Pathfinder', 'b': 'PathNode', 'c': 'NodePool',
        'd': 'SearchContext', 'e': 'PathResult', 'f': 'PathSolver',
        'g': 'TileNavigator', 'h': 'PathCostCalc', 'i': 'AStarSearch',
        'j': 'PriorityQueue', 'k': 'PathRequest',
    },
    # gameFramework/f — UI
    "com/corrodinggames/rts/gameFramework/f": {
        'a': 'UIPanel', 'b': 'UIElement', 'c': 'Minimap',
        'd': 'InGameUI', 'e': 'ButtonWidget',
    },
    # rts/a/a — test framework
    "com/corrodinggames/rts/a/a": {
        'l': 'TestCase', 'i': 'TestHelperBase',
    },
    # game/units/custom — mod system (partial)
    "com/corrodinggames/rts/game/units/custom": {
        'a': 'ModDataField', 'b': 'ModDefinition', 'c': 'ModLoader',
        'd': 'CustomUnitType', 'e': 'ResourceType', 'f': 'TeamTag',
        'g': 'UnitConfig', 'h': 'ConfigLoader',
    },
    # game/units/custom/a/a — mod sub-package
    "com/corrodinggames/rts/game/units/custom/a/a": {
        'a': 'ModActionBase', 'i': 'ModSubAction',
    },
    # game/units/custom/b — mod sub-package 2
    "com/corrodinggames/rts/game/units/custom/b": {
        'a': 'ModActionBase', 't': 'ModifierApplier',
    },
}

def get_package_from_path(dir_path):
    """Convert filesystem path back to Java package."""
    return dir_path.replace('\\', '/')

def resolve_extends(pkg, obf_char, pkg_map):
    """Resolve a single-char class name within a package context."""
    # Check manual overrides first
    for override_pkg, mapping in MANUAL_OVERRIDES.items():
        if pkg == override_pkg or pkg.startswith(override_pkg + '/'):
            if obf_char in mapping:
                return mapping[obf_char]

    # Check CSV mappings for this package
    if pkg in pkg_map and obf_char in pkg_map[pkg]:
        return pkg_map[pkg][obf_char]

    # Try parent package
    parts = pkg.rsplit('/', 1)
    if len(parts) == 2:
        parent_pkg = parts[0]
        # Check local overrides for parent
        for override_pkg, mapping in MANUAL_OVERRIDES.items():
            if parent_pkg == override_pkg:
                if obf_char in mapping:
                    return mapping[obf_char]
        # Check CSV for parent
        if parent_pkg in pkg_map and obf_char in pkg_map[parent_pkg]:
            return pkg_map[parent_pkg][obf_char]

    return None

def fix_file(filepath, pkg_map):
    """Fix extends/implements in a single Java file."""
    try:
        content = filepath.read_text(encoding='utf-8', errors='replace')
    except:
        return 0

    # Determine package
    pkg_match = re.search(r'^package\s+([\w.]+);', content, re.MULTILINE)
    if not pkg_match:
        return 0
    java_pkg = pkg_match.group(1)
    fs_pkg = java_pkg.replace('.', '/')

    modified = content
    count = 0

    # Fix "extends X" where X is a single char
    def replace_extends(m):
        nonlocal count
        char = m.group(1)
        resolved = resolve_extends(fs_pkg, char, pkg_map)
        if resolved and resolved != char and len(resolved) > 1:
            count += 1
            return f'extends {resolved} '
        return m.group(0)

    modified = re.sub(r'extends\s+([a-z])\s+', replace_extends, modified)

    # Fix "implements X" where X is a single char
    def replace_implements(m):
        nonlocal count
        char = m.group(1)
        resolved = resolve_extends(fs_pkg, char, pkg_map)
        if resolved and resolved != char and len(resolved) > 1:
            count += 1
            return f'implements {resolved} '
        return m.group(0)

    modified = re.sub(r'implements\s+([a-z])\s+', replace_implements, modified)

    if count > 0:
        filepath.write_text(modified, encoding='utf-8')

    return count

def main():
    pkg_map = load_class_mappings()
    total_fixed = 0
    files_fixed = 0

    for java_file in DEOBFUSCATED.rglob("*.java"):
        count = fix_file(java_file, pkg_map)
        if count > 0:
            files_fixed += 1
            total_fixed += count

    print(f"Fixed {total_fixed} extends/implements clauses in {files_fixed} files")

if __name__ == '__main__':
    main()
