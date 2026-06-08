# Rusted Warfare v1.15 — Reverse Engineering Documentation

> CFR 0.152 decompiled source (1,698 classes) + RW-HPS server + 30 replays

## Comprehensive Reports

| Document | Description |
|----------|------------|
| [GAME_COMPLETE_ANALYSIS.md](GAME_COMPLETE_ANALYSIS.md) | 22-system full analysis (1,479 lines) |
| [LAYER_ANALYSIS.md](LAYER_ANALYSIS.md) | 7-layer full stack (945 lines) |
| [CPP_LAYER_ANALYSIS.md](CPP_LAYER_ANALYSIS.md) | C++ native layer PE/symbol/export analysis (625 lines) |
| [OBFUSCATION_MAP.md](OBFUSCATION_MAP.md) | Obfuscation→meaning mapping reference (741 lines) |
| [VERIFICATION.md](VERIFICATION.md) | Cross-verification report (732 lines) |

## Reference

| Document | Description |
|----------|------------|
| [CLASS_DICTIONARY.md](CLASS_DICTIONARY.md) | 280+ field mappings, 31 core classes |
| [CLASS_CATALOG.md](CLASS_CATALOG.md) | 1,698 class catalog |

## Domain Documents

| # | Document | Topic |
|---|----------|-------|
| 01 | [ECONOMY](01_ECONOMY.md) | Income formula, build/upgrade/refund |
| 02 | [COMBAT](02_COMBAT.md) | HP/shield/damage, death chain, projectiles |
| 03 | [MOVEMENT](03_MOVEMENT.md) | Pathfinding, spatial grid |
| 04 | [UNITS](04_UNITS.md) | Unit types, instances, custom units |
| 05 | [PLAYER_TEAM](05_PLAYER_TEAM.md) | Slots, teams, AI fill |
| 06 | [NETWORK_COMMAND](06_NETWORK_COMMAND.md) | Protocol, Command fields, replay format |
| 07 | [MAP_FOG](07_MAP_FOG.md) | TMX parsing, fog of war |
| 08 | [AI](08_AI.md) | Wave system, missions, conditions |
| 09 | [UTILITY](09_UTILITY.md) | Data structures, math utils |
| 10 | [ALGORITHMS](10_ALGORITHMS.md) | Bytecode-level algorithm restoration |
| 11 | [CONSTANTS](11_CONSTANTS.md) | All verified game constants |

## Systems

| Document | Description |
|----------|------------|
| [12_GAME_ARCHITECTURE](12_GAME_ARCHITECTURE.md) | Full architecture overview |
| [15_SIMULATION_ENGINE](15_SIMULATION_ENGINE.md) | Python reference implementation |
| [16_FILE_FORMATS](16_FILE_FORMATS.md) | .replay/.rwsave/.ini formats |
| [17_SERVER_ARCHITECTURE](17_SERVER_ARCHITECTURE.md) | RW-HPS headless server |
| [18_NETWORK_SYSTEM](18_NETWORK_SYSTEM.md) | TCP/UDP, packet types, sync |
| [19_UI_RENDERING](19_UI_RENDERING_SYSTEM.md) | LibRocket/Slick2D/OpenGL |
| [20_PLUGIN_MOD](20_PLUGIN_MOD_SYSTEM.md) | Mod/plugin lifecycle |
