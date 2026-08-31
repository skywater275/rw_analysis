# Deobfuscation v8.0 — Network Layer + Unit Actions + Package Mapping

> Date: 2026-06-23 | Previous: v7.2 | New: +86 class mappings (221→307)

---

## Summary

| Metric | v7.2 | v8.0 | Change |
|--------|------|------|--------|
| Class mappings | 221 | **307** | +86 |
| Readable files | 1044 (61.5%) | **1128 (66.4%)** | +84 |
| Inner class renames | 369 | 369 | — |
| Field renames | 331 | 331 | — |
| Method renames | 75 | 75 | — |
| Extends fixes | 139 | 139 | — |

---

## New: Reliable UDP Network Layer (`a/a/a/` + `a/a/`)

### Packet Types (`a/a/a/` — fully deobfuscated)

| Class | Obfuscated | Description |
|-------|-----------|-------------|
| Packet | h | Abstract base: seq/ack/len/flags + factory deserializer `b(byte[], int, int)` |
| AckPacket | a | ACK (flag 0x40, no payload) |
| DataPacket | b | DAT (flag 0x40, byte[] payload) |
| ExtendedAckPacket | c | EAK (flag 0x20, extends AckPacket, int[] seq numbers) |
| FinPacket | d | FIN (flag 0x02, connection finish) |
| NullPacket | e | NUL (flag 0x08, keep-alive) |
| ResetPacket | f | RST (flag 0x10, connection reset) |
| SynPacket | g | SYN (flag 0x80, 11-field connection setup) |
| TaskRunner | i | Runnable wrapper with wait/notify scheduling in daemon thread |

### Reliable UDP Socket Layer (`a/a/` — fully deobfuscated)

| Class | Obfuscated | Description |
|-------|-----------|-------------|
| ReliableSocket | h | Core socket extends java.net.Socket + DatagramSocket |
| ReliableServerSocket | b | UDP ServerSocket extends java.net.ServerSocket |
| ReliableServerThread | d | Daemon thread "ReliableServerSocket" processing incoming packets |
| ReliableSocketThread | m | Daemon thread "ReliableSocket" dispatching SYN/EAK/DAT/ACK |
| ReliableClientSocket | e | Client-side socket extends ReliableSocket |
| ReliableProfile | r | 11 network parameters (maxSegmentSize=300, timeout=600, maxRetrans=3) |
| ReliableInputStream | o | InputStream over reliable socket (buffered) |
| ReliableOutputStream | q | OutputStream over reliable socket (buffered + flush) |
| PacketSender | n | Runnable that flushes queued packets |
| NullPacketSender | l | Runnable that sends NUL keep-alive packets |
| ServerConnectionListener | f | Connection handler (50-connection cap) |
| SocketStats | i | Connection statistics (sent/recv/dropped) |
| ConnectionEntry | g | Simple container (long + ReliableClientSocket) |
| AddressFilter | c | Abstract socket address filter |
| DataReceiver | a | Abstract data receiver |
| SocketLifecycle | p | Interface: 4 lifecycle callbacks |
| ConnectionEventHandler | s | Interface: 5 event callbacks |

### Protocol Design
- Custom reliable UDP protocol (similar to ENet/KryoNet)
- SYN → SYN-ACK → DAT flow with ACK/EAK
- NUL keep-alive with configurable timeout
- RST for connection reset, FIN for graceful close
- 22-byte max segment size (SYN), 300-byte max data segment
- Retransmission with exponential backoff (max 3 retries)

---

## New: Unit Action System (`game/units/a/` — fully deobfuscated)

### Action ID Registry
| ID Pattern | Class | Description |
|-----------|-------|-------------|
| c_1 | RallyPointAction | Set rally point for factory |
| c_2 | ReclaimAction | Reclaim building/unit for resources |
| c_3 | RepairAction | Repair target unit/building |
| c_4 | AttackMoveAction | Attack-move to location |
| c_5 | StopAction | Stop current action |
| c_6_{type} | PingAction | Map ping (12 types via PingType enum) |
| c_7 | AttackAction | Attack target |
| c_8 | GuardAction | Guard target unit |
| c_9 | PatrolAction | Patrol between points |
| c__cut_chat | TeamChatAction | Send team chat message (UI action) |
| c__cut_ping | MapPingAction | Send map ping (UI action) |
| b_{unitId} | BuildQueueAction | Queue unit for production |
| u_{unitId} | UnitBuildAction | Build unit command (shows stats) |
| s_{unitId} | SellAction | Sell/delete unit |

### Class Hierarchy
```
GameAction (s) implements Comparable         ← priority-based action ordering
├── AttackAction (d)          c_7
├── AttackMoveAction (e)      c_4
├── GuardAction (f)           c_8
├── PatrolAction (i)          c_9
├── ReclaimAction (m)         c_2
├── RepairAction (n)          c_3
├── RallyPointAction (o)      c_1
├── StopAction (y)            c_5
├── PingAction (j)            c_6_{type}
├── AbstractBuildAction (w)
│   ├── UnitBuildAction (l)   u_{unitId}
│   └── BuildQueueAction (v)  b_{unitId}
├── SellAction (z)            s_{unitId}
├── AbstractCutsceneAction (p)
│   ├── TeamChatAction (q)    c__cut_chat
│   └── MapPingAction (r)     c__cut_ping
├── BuildAction (g)           (construction placement)
├── ActionWrapper (h)         (decorator pattern)
└── AbstractImmediateAction (x) (no-target base)
```

### Support Types
- **ActionFilter** (b): Availability checker, `emptyActionFilter` singleton
- **ActionId** (c): Interned string identifier with HashMap cache
- **UnitActionBase** (a): Top-level base for unit actions
- **PingType** (k): Enum a-l (12 map ping types, localization key `menus.ingame.ping.type.*`)
- **ActionCategory** (t): Category enum (t.a = default)
- **ActionTargetType** (u): Target type enum (u.a-u.m values)

---

## New: gameFramework Sub-Package Mapping

| Package | Files | Purpose |
|---------|-------|---------|
| a/ | 9 | Audio/Sound system (AndroidSoundFactory, SoundPool) |
| b/ | 38 | GLSL Shader programs (vertex/fragment) |
| c/ | 3 | DebugServer (port 5677, password-protected) |
| d/ | 8 | HUD/GameOverlay (Paint, RectF, build queue display) |
| e/ | 8 | FileLoader/Storage abstraction (Build.VERSION SDK checks) |
| f/ | 52 | UI/Menu system (Paint, Canvas, Rect, MenuItem) |
| g/ | 7 | DataField types (DataField, DataFieldFloat, DataFieldInt, DataFieldLong) |
| h/ | 2 | Localization/Translation (ResourceBundle, Locale, PropertyResourceBundle) |
| i/ | 3 | Version/Mod compatibility checker |
| j/ | 68 | NetworkEngine (NetEngine, InputNetStream, OutputNetStream, ChatSystem) |
| k/ | 17 | Data structures (CustomArrayList, UnitList) |
| l/ | 3 | Performance monitor (FPS tracker, file logging) |
| m/ | 106 | OpenGL ES 1.0 Renderer (GLSurfaceView.Renderer, textures, sprites) |
| n/ | 25 | AI System (AIDifficulty, AISpawnList, AITask, AIWaveSystem, MissionParser) |
| o/ | 1 | SteamEngine (Steam integration: invite, overlay, workshop) |

---

## New: Map Sub-Package (`game/b/` — fully deobfuscated)

| Class | Obfuscated | Description |
|-------|-----------|-------------|
| MapEngine | (was b) | Core map engine |
| MapLayer | (existing) | Map layer abstraction |
| MapRenderer | (existing) | Map renderer |
| MapSpawn | a | Map spawn/mission data XML parser |
| MapLayerRenderer | d | Individual map layer renderer |
| TMXMapLoader | e | TMX map file loader (GZIP, Base64, zlib) |
| MapException | f | Map-related exception |
| TileDrawer | h | Single tile renderer |
| MapLayerDef | i | Layer definition (name, width, height, spawns) |
| TilesetDef | j | Tileset data (image, tile dimensions, spacing) |
| TileEntry | k | Individual tile entry (id, image, properties) |

---

## New: AI Strategy Types (`game/a/a/`)

| Class | Obfuscated | Description |
|-------|-----------|-------------|
| AIStrategy | a | Abstract AI strategy base |
| AIStrategyResult | b | Strategy evaluation result |
| AIUnitGroupStrategy | c | Unit group strategy (has UnitRegistry) |
| AINukeStrategy | d | Nuke launcher strategy (PointF targeting) |

---

## Remaining Gaps (known limitations)

### Fully deobfuscated packages (0 obfuscated):
- `a/a/a/` (network packets), `a/a/` (reliable UDP)
- `game/a/` (AI/combat), `game/b/` (map)
- `game/units/a/` (actions), `game/units/b/` (behaviors)
- `game/units/c/` (melee), `game/units/e/` (buildings)
- `game/units/f/` (spatial), `game/units/h/` (factory)

### Partially obfuscated:
- `game/units/d/` (7/34 files: h,i,r,s,u,v,w — experimental unit types)
- `game/units/g/` (4/8 files: b,c,d,e — unit component subtypes)

### Large still-obfuscated packages (lower priority, mostly platform/third-party):
- `gameFramework/b/` (38 — shaders), `gameFramework/f/` (52 — UI)
- `gameFramework/j/` (68 — network engine), `gameFramework/k/` (17 — data structures)
- `gameFramework/m/` (106 — OpenGL rendering)
- `com/corrodinggames/rts/java/` (platform abstraction)
- `android/` and `org/` (third-party wrappers)

### Known limitation:
- ~424 single-char class files remain in platform/rendering/third-party packages
- Single-char extends clauses still cannot be auto-fixed (cross-package ambiguity)
- Method declarations retain obfuscated names (overload risk)
