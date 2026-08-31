# Deobfuscation v8.1 — Critical Game Packages Complete

> Date: 2026-06-23 | Previous: v8.0 | New: +17 class mappings (307→324)

---

## Summary

| Metric | v8.0 | v8.1 | Change |
|--------|------|------|--------|
| Class mappings | 307 | **324** | +17 |
| Readable files | 1128 (66.4%) | **1145 (67.4%)** | +17 |
| Critical packages fully deobfuscated | 10 | **12** | +2 |

---

## New: game/units/d/ Fully Deobfuscated (23 files)

| Class | Obfuscated | Description |
|-------|-----------|-------------|
| PowerGeneratorUnit | h | Energy production (3 tech: 2.0/7.0/14.0, R$drawable.power) |
| MobileBuilderBase | i | Abstract builder base (extends ExperimentalGroundUnit, implements l) |
| RepairBayUnit | r | Repair turret/bay (R$drawable.repair_bay, auto area heal) |
| AutoRepairCallback | s | Spatial query callback scanning for damaged units |
| UpgradeToT2Action | u | Factory tech 2 upgrade action (gui.actions.upgradeT2) |
| FabricatorUnit | v | Mobile fabricator (ar.N, 3 tech levels) |
| ExperimentalWallUnit | w | Wall building (R$drawable.wall_v, 15×15) |

## New: game/units/g/ Fully Deobfuscated (5 files)

| Class | Obfuscated | Description |
|-------|-----------|-------------|
| ComponentType | b | Enum (2 values: a/b with anonymous subclasses) |
| ComponentUpdater | c | Static utility: iterate+update+remove expired components |
| FloatComponent | d | Float-valued unit component (extends UnitComponent) |
| TimerComponent | e | Timer/cooldown component (extends UnitComponent, ActionId filter) |

## New: gameFramework/j/ Network Engine (6 key classes)

| Class | Obfuscated | Description |
|-------|-----------|-------------|
| WebAPIClient | n | Apache HttpClient-based API (Steam Workshop, mods, reports) |
| ByteArrayPacketBuilder | aw | Print-based packet builder (extends PacketBuilder, linked list queue) |
| ServerListener | ao | Multiplayer server socket listener (Runnable, IP filtering) |
| SteamSocket | h | Steam P2P socket wrapper (extends Socket) |
| SendWorker | e | Thread "SendWorker-{id}" (DataOutputStream, wait/notify) |
| UDPBroadcastListener | af | LAN discovery via UDP broadcast (ping/pong, MAGIC_GAME_ID) |

## Network Stack Now Known

```
[Application Layer]
  NetEngine (ad, 5358 lines) — main network coordinator
  PlayerConnect (b) — player connection state
  ChatSystem (a) — chat message routing
  WebAPIClient (n) — HTTP API calls

[Serialization Layer]
  InputNetStream (k) — binary deserialization
  OutputNetStream (as) — binary serialization
  ByteArrayPacketBuilder (aw) — packet assembly
  PacketBuilder (top-level as) — abstract base

[Transport Layer]
  ServerListener (ao) — incoming connections (TCP)
  UDPBroadcastListener (af) — LAN discovery (UDP)
  SteamSocket (h) — Steam P2P
  SendWorker (e) — async send thread

[Reliable UDP Layer] (a/a/ package)
  ReliableSocket, ReliableServerSocket, etc.

[Packet Layer] (a/a/a/ package)
  Packet, AckPacket, DataPacket, SynPacket, etc.
```

---

## All 12 Critical Game Packages — Fully Deobfuscated

| Package | Files | Content |
|---------|-------|---------|
| a/a/a/ | 9 | Reliable UDP packet types |
| a/a/ | 19 | Reliable UDP socket layer |
| game/a/ | 15 | AI/Combat system |
| game/b/ | 11 | Map/TMX/Tileset |
| game/units/a/ | 26 | Unit actions (15 action types) |
| game/units/b/ | 6 | Unit behaviors |
| game/units/c/ | 1 | Melee bug unit |
| game/units/d/ | 23 | Experimental/custom units |
| game/units/e/ | 15 | Building types |
| game/units/f/ | 10 | Spatial grid/query |
| game/units/g/ | 5 | Unit component system |
| game/units/h/ | 6 | Factory actions |

---

## Cross-Validation Results

All new mappings verified through:
- ✅ Bytecode-level field/method signature matching
- ✅ Imports consistency (ReliableServerSocket imported by ServerListener)
- ✅ Thread naming patterns ("SendWorker-{id}")
- ✅ Magic constants (com.corrodinggames.rts as MAGIC_GAME_ID, 9 refs)
- ✅ Resource drawable IDs (power, repair_bay, wall_v)
- ✅ GUI localization keys (gui.actions.upgradeT2)
- ✅ MASTER_MAPPING.md cross-reference (NetEngine=ad, InputNetStream=k, OutputNetStream=as)

## Remaining Gaps (lower priority)

- ~410 single-char files in platform/rendering/shaders (gameFramework/a-f, h-m)
- ~140 two-char files in network engine internals
- Single-char extends clauses (cross-package ambiguity)
- Method declarations (overload risk)
