# Bytecode Cross-Validation Report — v9.40

> Generated: 2026-06-23 | Validated: 52 classes | javap source: 01-classes/

## Summary

| Metric | Value |
|--------|-------|
| Classes validated | 52 |
| Field mappings confirmed | 2825/2963 |
| Method mappings confirmed | 450/476 |
| Phantom mappings | 164 |
| Still unmapped in BC | 863 |

## Phantom Mappings (CSV entries NOT in bytecode)

| Class | Type | Old Name | Mapped To |
|-------|------|----------|----------|
| b | field | `O` | `clipBounds` |
| b | field | `aa` | `zoomLevel` |
| b | field | `ab` | `waterPaint` |
| b | field | `ac` | `deepWaterPaint` |
| b | field | `ad` | `shorePaint` |
| b | field | `ae` | `landPaint` |
| b | field | `af` | `mountainPaint` |
| b | field | `ag` | `resourcePaint` |
| b | field | `ah` | `tileCache` |
| b | field | `ai` | `viewOffsetX` |
| b | field | `aj` | `brightness` |
| b | field | `ak` | `renderPass` |
| b | field | `am` | `overlayPaint` |
| b | field | `an` | `dirtyRect1` |
| b | field | `ao` | `dirtyRect2` |
| b | field | `ap` | `lastRenderTime` |
| b | field | `aq` | `renderTimeAccum` |
| b | field | `ar` | `avgRenderTime` |
| b | field | `k` | `tileLookupTable` |
| f | field | `aV` | `impactPointX` |
| f | field | `aW` | `impactPointY` |
| f | field | `aX` | `impactNormalX` |
| f | method | `applyImpact` | `applyImpact` |
| com.corrodinggames.rts.game.i | field | `d` | `netEngine` |
| com.corrodinggames.rts.game.i | field | `g` | `cameraController` |
| com.corrodinggames.rts.game.i | field | `h` | `uiController` |
| n | field | `ad` | `totalScore` |
| n | field | `ai` | `spawnPosition` |
| n | field | `ak` | `unitFilter` |
| n | field | `al` | `resourceTracker` |
| n | field | `aq` | `lastSaveTime` |
| n | field | `ar` | `totalPlayTime` |
| am | method | `az` | `updateState` |
| a | field | `m` | `uiColorValue` |
| a | field | `r` | `isHiddenInEditor` |
| a | field | `s` | `defaultValue` |
| a | field | `v` | `linkedResourceType` |
| d | field | `g` | `unitDisplayName` |
| d | field | `h` | `unitDescription` |
| d | field | `i` | `isLockedByTech` |
| d | field | `j` | `isUniqueUnit` |
| com.corrodinggames.rts.game.units.d.d | field | `a` | `experimentName` |
| com.corrodinggames.rts.game.units.d.d | field | `b` | `costMultiplier` |
| com.corrodinggames.rts.game.units.d.d | field | `c` | `hpMultiplier` |
| com.corrodinggames.rts.game.units.d.d | field | `d` | `damageMultiplier` |
| com.corrodinggames.rts.game.units.d.e | field | `e` | `isActiveCmdCenter` |
| h | field | `E` | `modIcon` |
| h | field | `F` | `currentProduction` |
| h | field | `G` | `ownerPlayer` |
| h | field | `H` | `customTag` |
| h | field | `I` | `isCaptured` |
| h | field | `J` | `builderRestriction` |
| h | field | `a` | `buildSlots` |
| h | field | `ax` | `autoAssignTeam` |
| h | field | `b` | `slotWorkPositions` |
| h | field | `c` | `isActive` |
| h | field | `r` | `buildTimer` |
| h | method | `h` | `completeBuild` |
| h | method | `i` | `getBuildTime` |
| h | method | `j` | `getBuildCost` |
| y | field | `Q` | `teamColorIndexCache` |
| aj | field | `a` | `bindingMapA` |
| aj | field | `b` | `bindingMapB` |
| aj | field | `c` | `debugLeft` |
| aj | field | `d` | `debugRight` |
| aj | field | `e` | `cameraUpKey` |
| aj | field | `f` | `cameraDownKey` |
| aj | field | `g` | `cameraLeftKey` |
| aj | field | `h` | `cameraRightKey` |
| aj | field | `i` | `zoomInKey` |
| aj | field | `j` | `zoomOutKey` |
| aj | field | `k` | `sendChatKey` |
| aj | field | `l` | `debugLeftAlt` |
| aj | field | `m` | `debugRightAlt` |
| aj | field | `n` | `cameraUpAlt` |
| aj | field | `o` | `cameraDownAlt` |
| aj | field | `p` | `cameraLeftAlt` |
| aj | field | `q` | `cameraRightAlt` |
| aj | field | `r` | `zoomInAlt` |
| aj | field | `s` | `zoomOutAlt` |
| aj | field | `t` | `sendChatAlt` |
| aj | field | `u` | `sendTeamChat` |
| aj | field | `v` | `pingMap` |
| aj | field | `w` | `showMenu` |
| aj | field | `x` | `saveGameKey` |
| aj | field | `y` | `deselectKey` |
| aj | field | `z` | `gotoNotification` |
| e | field | `w` | `queuedActions` |
| e | method | `m` | `undo` |
| e | method | `n` | `getTargetPosition` |
| e | method | `o` | `getTargetUnit` |
| e | method | `p` | `isValid` |
| e | method | `q` | `cancel` |
| e | method | `r` | `getCommandType` |
| com.corrodinggames.rts.gameFramework.f | method | `A` | `hasBuilderSelected` |
| com.corrodinggames.rts.gameFramework.f | method | `B` | `hasFactorySelected` |
| com.corrodinggames.rts.gameFramework.f | method | `C` | `hasTransporterSelected` |
| com.corrodinggames.rts.gameFramework.f | method | `D` | `canAllBuild` |
| com.corrodinggames.rts.gameFramework.f | method | `G` | `surrender` |
| com.corrodinggames.rts.gameFramework.f | method | `H` | `resign` |
| com.corrodinggames.rts.gameFramework.f | method | `I` | `setPingAction` |
| com.corrodinggames.rts.gameFramework.f | method | `J` | `isNotPlacing` |
| com.corrodinggames.rts.gameFramework.f | method | `K` | `invalidateUI` |
| com.corrodinggames.rts.gameFramework.f | method | `u` | `canAnySelectedBuild` |
| com.corrodinggames.rts.gameFramework.f | method | `v` | `issueStopCommand` |
| com.corrodinggames.rts.gameFramework.f | method | `w` | `createCommand` |
| com.corrodinggames.rts.gameFramework.f | method | `x` | `createCommandAll` |
| com.corrodinggames.rts.gameFramework.f | method | `y` | `clearFullSelection` |
| com.corrodinggames.rts.gameFramework.f | method | `z` | `getLastSelectedCustomType` |
| ad | field | `aD` | `receiveThread` |
| ad | field | `aE` | `receiveRunnable` |
| ad | field | `aF` | `sendThread` |
| ad | field | `aG` | `sendRunnable` |
| ad | field | `aH` | `heartbeatTimer` |
| ad | field | `aI` | `heartbeatTask` |
| ad | field | `aJ` | `connectThread` |
| ad | field | `aK` | `connectRunnable` |
| ad | field | `aL` | `encryptionHandler` |
| ad | field | `aN` | `outgoingPacketQueue` |
| ad | field | `aO` | `processingQueue` |
| ad | field | `aQ` | `threadSyncLock` |
| ad | field | `aR` | `relayServerIp` |
| ad | field | `aS` | `relayServerToken` |
| ad | field | `aZ` | `engineInitialized` |
| ad | field | `aa` | `frameSyncTimer` |
| ad | field | `at` | `syncTimerAccum` |
| ad | field | `au` | `lastSyncTimeMs` |
| ad | field | `bB` | `banCheckActive` |
| ad | field | `bD` | `pingTimer` |
| ad | field | `bF` | `relayConnection` |
| ad | field | `bn` | `desyncTimerAccum` |
| ad | field | `bo` | `syncInterval` |
| ad | field | `bp` | `syncFrameCounter` |
| ad | field | `bq` | `desyncFrameCounter` |
| ad | field | `br` | `desyncConfirmed` |
| ad | field | `bu` | `connectionPending` |
| ad | field | `by` | `shuttingDown` |
| ad | field | `bz` | `restartRequested` |
| ad | field | `f` | `playerList` |
| as | field | `b` | `dataOutput` |
| as | field | `c` | `bitBuffer` |
| k | field | `a` | `dataBuffer` |
| k | field | `b` | `readPosition` |
| k | field | `c` | `dataLength` |
| k | field | `d` | `bitBuffer` |
| l | field | `dA` | `pendingZoomLevel` |
| l | field | `dB` | `zoomListenersNotified` |
| l | field | `dC` | `zoomListeners` |
| l | field | `dJ` | `exitSyncLock` |
| l | field | `dK` | `appDataDir` |
| l | field | `dL` | `appCacheDir` |
| f | field | `A` | `waveMultiplier` |
| f | field | `B` | `emergencyMultiplier` |
| f | field | `L` | `taskCount` |
| f | field | `M` | `difficultyMultiplier` |
| f | field | `b` | `currentWave` |
| f | field | `c` | `totalWaves` |
| f | field | `u` | `waveInterval` |
| f | field | `v` | `unitCountBase` |
| f | field | `w` | `unitTypeDiversity` |
| f | field | `x` | `maxSimultaneousWaves` |
| f | field | `z` | `waveTimer` |
| y | field | `c` | `saveVersion` |
| y | field | `d` | `saveFrame` |

These 164 mappings reference members that don't exist in the actual bytecode.
They may be from: CFR decompiler artifacts, inner class confusion, or manually-added but incorrect mappings.

## Unmapped Bytecode Members


### y (com.corrodinggames.rts.game.units.y) — 124 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `A` | int |
| field | `B` | android.graphics.Paint |
| field | `C` | int |
| field | `D` | com.corrodinggames.rts.gameFramework.m.ag |
| field | `E` | com.corrodinggames.rts.gameFramework.m.ag |
| field | `O` | com.corrodinggames.rts.game.units.au[] |
| field | `P` | com.corrodinggames.rts.game.units.a |
| field | `R` | com.corrodinggames.rts.game.units.am |
| field | `S` | float |
| field | `T` | float |
| field | `U` | float |
| field | `V` | float |
| field | `W` | float |
| field | `X` | float |
| field | `Y` | float |
| field | `Z` | com.corrodinggames.rts.game.units.am |
| field | `aA` | float |
| field | `aB` | com.corrodinggames.rts.game.a.h |
| field | `aC` | com.corrodinggames.rts.game.a.i |
| field | `aD` | boolean |
| field | `aE` | com.corrodinggames.rts.gameFramework.m.ag |
| field | `aF` | com.corrodinggames.rts.gameFramework.m.ag |
| field | `aG` | android.graphics.PointF |
| field | `aH` | com.corrodinggames.rts.game.units.f.j |
| field | `aI` | byte |
| field | `aJ` | com.corrodinggames.rts.game.units.am[] |
| field | `aK` | float[] |
| field | `aL` | int |
| field | `aM` | com.corrodinggames.rts.gameFramework.utility.u |
| field | `aN` | boolean |
| field | `aO` | boolean |
| field | `aP` | com.corrodinggames.rts.game.units.ad |
| field | `aQ` | com.corrodinggames.rts.game.units.ae |
| field | `aR` | com.corrodinggames.rts.game.units.ae |
| field | `aS` | com.corrodinggames.rts.game.units.ah |
| field | `aT` | com.corrodinggames.rts.game.units.ah |
| field | `aV` | com.corrodinggames.rts.gameFramework.utility.m |
| field | `aW` | com.corrodinggames.rts.game.units.af |
| field | `aX` | android.graphics.PorterDuffColorFilter |
| field | `aY` | android.graphics.PorterDuffColorFilter |
| field | `aZ` | android.graphics.PorterDuffColorFilter |
| field | `ab` | float |
| field | `ac` | int |
| field | `ap` | boolean |
| field | `as` | boolean |
| field | `at` | com.corrodinggames.rts.game.units.af[] |
| field | `au` | com.corrodinggames.rts.gameFramework.k.c |
| field | `av` | com.corrodinggames.rts.game.units.af[] |
| field | `aw` | int |
| field | `ax` | boolean |
| field | `ay` | boolean |
| field | `az` | float |
| field | `ba` | android.graphics.PorterDuffColorFilter |
| field | `bb` | android.graphics.Paint |
| field | `bc` | android.graphics.Paint |
| field | `bd` | android.graphics.Paint |
| field | `be` | android.graphics.PointF |
| field | `bf` | com.corrodinggames.rts.gameFramework.utility.ai |
| field | `bg` | android.graphics.PointF |
| field | `bh` | android.graphics.PointF |
| field | `bi` | com.corrodinggames.rts.gameFramework.utility.ai |
| field | `bj` | android.graphics.PointF |
| field | `bk` | android.graphics.Point |
| field | `bl` | android.graphics.Point |
| field | `bm` | android.graphics.PointF |
| field | `bn` | com.corrodinggames.rts.game.units.z |
| field | `bo` | com.corrodinggames.rts.game.units.ac |
| field | `bp` | com.corrodinggames.rts.gameFramework.utility.m |
| field | `bq` | com.corrodinggames.rts.gameFramework.utility.m |
| field | `g` | com.corrodinggames.rts.game.units.au[] |
| field | `u` | boolean |
| field | `v` | int |
| field | `w` | int |
| field | `x` | com.corrodinggames.rts.gameFramework.m.ag |
| field | `y` | int |
| field | `z` | com.corrodinggames.rts.gameFramework.m.ag |
| method | `A` | [('float', ''), ('float', 'int')] |
| method | `E` | [('boolean', ''), ('android.graphics.PointF', 'int')] |
| method | `G` | [('float', ''), ('android.graphics.PointF', 'int')] |
| method | `H` | [('float', ''), ('float', 'int')] |
| method | `I` | [('boolean', ''), ('float', 'int')] |
| method | `J` | [('float', 'int')] |
| method | `K` | [('android.graphics.PointF', 'int')] |
| method | `L` | [('float', 'int')] |
| method | `M` | [('void', 'int')] |
| method | `N` | [('void', 'int')] |
| method | `U` | [('void', '')] |
| method | `W` | [('void', '')] |
| method | `Z` | [('boolean', '')] |
| method | `a` | [('void', 'com.corrodinggames.rts.game.units.y, com.corrodinggames.rts.game.units.y'), ('void', 'com.corrodinggames.rts.gameFramework.j.as'), ('void', 'com.corrodinggames.rts.gameFramework.j.k'), ('android.graphics.Paint', 'int, android.graphics.ColorFilter, boolean'), ('com.corrodinggames.rts.gameFramework.m.ag', 'boolean'), ('void', 'java.lang.String'), ('void', 'float'), ('void', 'float, com.corrodinggames.rts.gameFramework.l, float, float'), ('void', 'com.corrodinggames.rts.game.units.am, float, boolean'), ('void', 'int'), ('float', 'float, float, boolean, boolean'), ('void', 'int, float'), ('float', 'float, float, int'), ('void', 'float, com.corrodinggames.rts.game.units.au, com.corrodinggames.rts.game.units.ad'), ('void', 'float, com.corrodinggames.rts.game.units.au, com.corrodinggames.rts.game.units.ad, boolean'), ('void', 'float, com.corrodinggames.rts.game.units.am, com.corrodinggames.rts.game.units.ad, boolean'), ('void', 'float, float, float'), ('void', 'float, com.corrodinggames.rts.game.units.af, com.corrodinggames.rts.game.units.ad, com.corrodinggames.rts.game.units.au'), ('void', 'com.corrodinggames.rts.gameFramework.l, float, com.corrodinggames.rts.game.units.au, com.corrodinggames.rts.game.units.ad'), ('void', 'float, com.corrodinggames.rts.game.units.ad, com.corrodinggames.rts.game.units.au, boolean'), ('void', 'com.corrodinggames.rts.gameFramework.l, float, float'), ('void', 'com.corrodinggames.rts.gameFramework.l, float'), ('boolean', 'float, com.corrodinggames.rts.game.units.am, int'), ('boolean', 'int, com.corrodinggames.rts.game.units.am, boolean, boolean'), ('boolean', 'com.corrodinggames.rts.game.units.am'), ('com.corrodinggames.rts.game.units.a.s', 'com.corrodinggames.rts.game.units.as, boolean'), ('com.corrodinggames.rts.game.units.a.s', 'com.corrodinggames.rts.game.units.as, int, boolean'), ('boolean', 'com.corrodinggames.rts.game.units.am, boolean'), ('void', 'com.corrodinggames.rts.game.units.au'), ('boolean', 'com.corrodinggames.rts.game.units.au, boolean'), ('boolean', 'com.corrodinggames.rts.game.units.as, float, float'), ('void', 'com.corrodinggames.rts.game.units.y'), ('void', 'int, float, float'), ('void', 'float, float, int, boolean, boolean'), ('com.corrodinggames.rts.gameFramework.k.k', 'float, float, int, boolean, boolean, boolean'), ('void', 'com.corrodinggames.rts.game.units.am, int'), ('void', 'com.corrodinggames.rts.game.units.ab'), ('void', 'com.corrodinggames.rts.game.units.ab, boolean'), ('boolean', 'boolean, com.corrodinggames.rts.game.n'), ('boolean', 'com.corrodinggames.rts.game.units.am, com.corrodinggames.rts.game.n'), ('void', ''), ('boolean', 'com.corrodinggames.rts.game.units.ag'), ('void', 'com.corrodinggames.rts.game.units.a.s, boolean, float, float'), ('boolean', 'com.corrodinggames.rts.game.units.a.s, float, float'), ('void', 'com.corrodinggames.rts.game.units.am, float, int'), ('com.corrodinggames.rts.game.units.z', 'com.corrodinggames.rts.game.units.au, com.corrodinggames.rts.game.units.as, int, float, float'), ('boolean', 'com.corrodinggames.rts.game.units.y, com.corrodinggames.rts.game.units.custom.b.n'), ('com.corrodinggames.rts.game.units.custom.b.n', 'short'), ('com.corrodinggames.rts.game.units.am', 'com.corrodinggames.rts.game.units.y, float, float, float, com.corrodinggames.rts.game.units.custom.h'), ('void', 'com.corrodinggames.rts.game.units.y, com.corrodinggames.rts.game.units.am, float, boolean')] |
| method | `aS` | [('boolean', '')] |
| method | `aT` | [('int', '')] |
| method | `ai` | [('boolean', '')] |
| method | `am` | [('float', '')] |
| method | `b` | [('void', 'float'), ('void', 'com.corrodinggames.rts.game.n'), ('void', 'float, float'), ('float', 'float, float, float'), ('void', 'float, com.corrodinggames.rts.game.units.au, com.corrodinggames.rts.game.units.ad'), ('void', 'com.corrodinggames.rts.gameFramework.l, float'), ('void', 'com.corrodinggames.rts.game.units.am, int'), ('boolean', 'com.corrodinggames.rts.game.units.as, boolean'), ('boolean', 'com.corrodinggames.rts.game.units.am, boolean'), ('float', 'boolean'), ('void', 'com.corrodinggames.rts.game.units.au'), ('void', 'com.corrodinggames.rts.gameFramework.l'), ('float', 'int'), ('boolean', 'int, float'), ('java.lang.String', 'boolean, com.corrodinggames.rts.game.n'), ('void', 'com.corrodinggames.rts.game.units.am, float, int'), ('boolean', 'com.corrodinggames.rts.game.units.y')] |
| method | `bt` | [('void', '')] |
| method | `bu` | [('void', '')] |
| method | `bv` | [('void', '')] |
| method | `bw` | [('int', '')] |
| method | `c` | [('float', 'float, float'), ('void', 'float, com.corrodinggames.rts.game.units.au, com.corrodinggames.rts.game.units.ad'), ('void', 'com.corrodinggames.rts.game.units.au'), ('boolean', 'float'), ('float', 'int'), ('void', 'boolean'), ('boolean', 'com.corrodinggames.rts.game.n')] |
| method | `d` | [('com.corrodinggames.rts.gameFramework.m.e', ''), ('com.corrodinggames.rts.gameFramework.m.e', 'int'), ('void', 'float, com.corrodinggames.rts.game.units.au, com.corrodinggames.rts.game.units.ad'), ('com.corrodinggames.rts.game.units.au', 'float, float'), ('com.corrodinggames.rts.game.units.au', 'com.corrodinggames.rts.game.units.au'), ('void', 'float')] |
| method | `e` | [('void', 'float, com.corrodinggames.rts.game.units.au, com.corrodinggames.rts.game.units.ad'), ('com.corrodinggames.rts.game.units.au', 'float, float'), ('float', 'int')] |
| method | `f` | [('void', 'float, com.corrodinggames.rts.game.units.au, com.corrodinggames.rts.game.units.ad'), ('float', 'com.corrodinggames.rts.game.units.am'), ('float', 'int')] |
| method | `g` | [('void', 'float'), ('void', 'float, com.corrodinggames.rts.game.units.au, com.corrodinggames.rts.game.units.ad'), ('com.corrodinggames.rts.game.units.custom.d.b', 'com.corrodinggames.rts.game.units.am'), ('float', 'int')] |
| method | `h` | [('float', 'int'), ('void', 'float'), ('void', 'float, com.corrodinggames.rts.game.units.au, com.corrodinggames.rts.game.units.ad'), ('boolean', 'com.corrodinggames.rts.game.units.am')] |
| method | `i` | [('float', 'int'), ('void', 'float'), ('void', 'float, com.corrodinggames.rts.game.units.au, com.corrodinggames.rts.game.units.ad'), ('boolean', 'com.corrodinggames.rts.game.units.am')] |
| method | `j` | [('void', 'int'), ('void', 'float'), ('boolean', 'com.corrodinggames.rts.game.units.am')] |
| method | `k` | [('com.corrodinggames.rts.gameFramework.m.e', ''), ('void', 'float'), ('boolean', 'com.corrodinggames.rts.game.units.am'), ('com.corrodinggames.rts.game.units.au', 'int')] |
| method | `l` | [('boolean', 'com.corrodinggames.rts.game.units.am'), ('void', 'int'), ('int', 'float')] |
| method | `m` | [('void', 'com.corrodinggames.rts.game.units.am'), ('void', 'int'), ('float', ''), ('android.graphics.PointF', 'float')] |
| method | `n` | [('com.corrodinggames.rts.game.units.au', 'com.corrodinggames.rts.game.units.am'), ('void', 'int'), ('android.graphics.PointF', 'float')] |
| method | `o` | [('com.corrodinggames.rts.game.units.af', 'int'), ('float', 'com.corrodinggames.rts.game.units.am')] |
| method | `p` | [('float', 'int'), ('float', 'com.corrodinggames.rts.game.units.am')] |
| method | `q` | [('int', 'com.corrodinggames.rts.game.units.am'), ('float', 'int')] |
| method | `r` | [('boolean', 'int'), ('boolean', 'com.corrodinggames.rts.game.units.am')] |
| method | `s` | [('boolean', 'int'), ('int', '')] |
| method | `s_` | [('boolean', '')] |
| method | `t` | [('float', 'int')] |
| method | `u` | [('boolean', 'int')] |
| method | `v` | [('int', 'int')] |
| method | `w` | [('float', 'int')] |
| method | `x` | [('float', 'int')] |
| method | `y` | [('float', 'int')] |
| method | `z` | [('float', ''), ('float', 'int')] |

### am (com.corrodinggames.rts.game.units.am) — 99 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `a` | com.corrodinggames.rts.gameFramework.utility.o |
| field | `bE` | com.corrodinggames.rts.gameFramework.utility.u |
| field | `bI` | android.graphics.Paint |
| field | `bJ` | android.graphics.Paint |
| field | `bK` | android.graphics.LightingColorFilter |
| field | `bL` | boolean |
| field | `bM` | boolean |
| field | `bN` | boolean |
| field | `bO` | boolean |
| field | `bP` | boolean |
| field | `bT` | boolean |
| field | `bV` | boolean |
| field | `bW` | long |
| field | `bX` | com.corrodinggames.rts.game.n |
| field | `bY` | boolean |
| field | `bs` | int |
| field | `bt` | com.corrodinggames.rts.game.units.am |
| field | `cB` | float |
| field | `cC` | float |
| field | `cD` | float |
| field | `cE` | int |
| field | `cL` | com.corrodinggames.rts.game.units.ap[] |
| field | `cN` | com.corrodinggames.rts.game.units.am |
| field | `cg` | float |
| field | `cj` | float |
| field | `cm` | float |
| field | `cu` | float |
| field | `cv` | float |
| field | `cw` | float |
| field | `cx` | float |
| field | `cz` | float |
| field | `dA` | android.graphics.RectF |
| field | `dB` | android.graphics.RectF |
| field | `dC` | android.graphics.Rect |
| field | `dD` | android.graphics.PointF |
| field | `dE` | android.graphics.PointF |
| field | `dG` | android.graphics.PointF |
| field | `dI` | com.corrodinggames.rts.game.units.custom.c.c |
| field | `dg` | android.graphics.Paint |
| field | `dh` | android.graphics.Paint |
| field | `di` | android.graphics.Paint |
| field | `dj` | android.graphics.Paint |
| field | `dk` | android.graphics.Paint |
| field | `dm` | int |
| field | `dn` | int |
| field | `do` | float |
| field | `dp` | float |
| field | `dq` | float |
| field | `dr` | android.graphics.RectF |
| field | `ds` | android.graphics.Paint |
| field | `dt` | android.graphics.Paint |
| field | `du` | android.graphics.RectF |
| field | `dv` | android.graphics.Rect |
| field | `dw` | android.graphics.Rect |
| field | `dx` | java.util.ArrayList |
| field | `dy` | java.util.ArrayList |
| field | `dz` | com.corrodinggames.rts.game.units.as |
| method | `A` | [('java.lang.String', 'com.corrodinggames.rts.game.units.am')] |
| method | `B` | [('void', 'com.corrodinggames.rts.game.units.am')] |
| method | `J` | [('boolean', '')] |
| method | `N` | [('java.util.ArrayList', '')] |
| method | `O` | [('void', 'int'), ('void', '')] |
| method | `P` | [('void', 'int'), ('boolean', '')] |
| method | `Q` | [('void', 'int'), ('boolean', '')] |
| method | `V` | [('int', '')] |
| method | `a_` | [('android.graphics.Rect', 'boolean'), ('void', 'java.lang.String')] |
| method | `aj` | [('boolean', '')] |
| method | `ak` | [('boolean', '')] |
| method | `b` | [('com.corrodinggames.rts.game.units.am', 'com.corrodinggames.rts.game.units.as'), ('float', 'com.corrodinggames.rts.game.units.am, float, com.corrodinggames.rts.game.f'), ('void', 'com.corrodinggames.rts.game.n'), ('void', 'com.corrodinggames.rts.game.units.a.s, boolean'), ('float', 'com.corrodinggames.rts.game.units.am')] |
| method | `bC` | [('void', '')] |
| method | `bI` | [('boolean', '')] |
| method | `bd` | [('float', '')] |
| method | `bv` | [('void', '')] |
| method | `c` | [('com.corrodinggames.rts.game.units.am', 'com.corrodinggames.rts.game.units.as'), ('boolean', 'float'), ('boolean', 'float, float, float'), ('boolean', 'com.corrodinggames.rts.game.units.am, boolean'), ('float', 'com.corrodinggames.rts.game.units.am'), ('java.lang.String', ''), ('boolean', 'com.corrodinggames.rts.game.units.y'), ('void', 'boolean')] |
| method | `cH` | [('boolean', '')] |
| method | `d` | [('com.corrodinggames.rts.game.units.am', 'com.corrodinggames.rts.game.units.as'), ('float', 'boolean'), ('void', 'float'), ('boolean', 'com.corrodinggames.rts.game.n'), ('boolean', 'com.corrodinggames.rts.game.units.am, boolean'), ('int', 'com.corrodinggames.rts.game.units.y'), ('boolean', 'com.corrodinggames.rts.game.units.am')] |
| method | `dd` | [('boolean', '')] |
| method | `e` | [('void', 'float'), ('boolean', ''), ('void', 'com.corrodinggames.rts.game.n'), ('com.corrodinggames.rts.game.units.a.s', 'com.corrodinggames.rts.game.units.as'), ('boolean', 'com.corrodinggames.rts.game.units.am, boolean'), ('int', 'com.corrodinggames.rts.game.units.y'), ('com.corrodinggames.rts.gameFramework.utility.m', 'boolean')] |
| method | `f` | [('boolean', 'float'), ('float', 'com.corrodinggames.rts.game.units.as'), ('java.lang.String', 'com.corrodinggames.rts.game.units.am, boolean'), ('void', 'com.corrodinggames.rts.game.n'), ('void', 'float, float')] |
| method | `f_` | [('void', '')] |
| method | `g` | [('float', ''), ('boolean', 'com.corrodinggames.rts.game.units.am, boolean'), ('void', 'com.corrodinggames.rts.game.n')] |
| method | `h` | [('com.corrodinggames.rts.game.units.ao', ''), ('boolean', 'com.corrodinggames.rts.game.units.am, boolean'), ('void', 'float')] |
| method | `i` | [('boolean', '')] |
| method | `l` | [('boolean', '')] |
| method | `m` | [('android.graphics.PointF', 'float')] |
| method | `n` | [('void', '')] |
| method | `o` | [('void', 'float'), ('boolean', '')] |
| method | `p` | [('void', 'float'), ('boolean', '')] |
| method | `q` | [('com.corrodinggames.rts.game.units.am', 'float'), ('boolean', '')] |
| method | `r` | [('com.corrodinggames.rts.game.units.as', ''), ('void', 'float')] |
| method | `s` | [('int', 'com.corrodinggames.rts.game.units.am')] |
| method | `s_` | [('boolean', '')] |
| method | `t` | [('boolean', 'com.corrodinggames.rts.game.units.am'), ('boolean', '')] |
| method | `u` | [('int', 'com.corrodinggames.rts.game.units.am'), ('boolean', '')] |
| method | `v` | [('com.corrodinggames.rts.gameFramework.m.e', ''), ('int', 'com.corrodinggames.rts.game.units.am')] |
| method | `w` | [('boolean', 'com.corrodinggames.rts.game.units.am')] |
| method | `x` | [('float', ''), ('boolean', 'com.corrodinggames.rts.game.units.am')] |
| method | `y` | [('int', ''), ('boolean', 'com.corrodinggames.rts.game.units.am')] |
| method | `z` | [('float', 'com.corrodinggames.rts.game.units.am')] |

### com.corrodinggames.rts.gameFramework.f (com.corrodinggames.rts.gameFramework.f) — 60 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `a` | java.util.Random |
| field | `b` | java.util.Random |
| field | `c` | android.graphics.PointF |
| field | `d` | android.graphics.PointF |
| field | `e` | android.graphics.PointF |
| field | `f` | android.graphics.PointF |
| field | `g` | android.graphics.PointF |
| field | `h` | android.graphics.PointF |
| field | `i` | int |
| field | `j` | byte[] |
| field | `k` | char[] |
| field | `l` | float[] |
| field | `m` | float[] |
| field | `n` | float[] |
| field | `n` | float[] |
| field | `o` | float[] |
| field | `o` | float[] |
| field | `p` | float[] |
| field | `p` | float[] |
| field | `q` | float[] |
| field | `q` | float[] |
| field | `r` | float[] |
| field | `r` | float[] |
| field | `s` | float[] |
| field | `s` | float[] |
| field | `t` | float[] |
| field | `t` | float[] |
| field | `u` | float[] |
| field | `u` | float[] |
| method | `a` | [('void', ''), ('int', 'com.corrodinggames.rts.game.units.am, int, int'), ('float', 'com.corrodinggames.rts.game.units.am, float, float, int'), ('int', 'com.corrodinggames.rts.gameFramework.w, int, int, int'), ('float', 'float, float, int'), ('int', 'int, int, int'), ('java.lang.String', 'java.lang.String'), ('void', 'java.io.InputStream, java.io.OutputStream'), ('java.lang.String', 'java.io.InputStream'), ('float', 'float'), ('int', 'int'), ('float', 'float, float'), ('float', 'float, float, float'), ('void', 'float, float, float, android.graphics.PointF'), ('float', 'float, float, float, float'), ('int', 'int, int, int, int'), ('float', 'float, boolean'), ('boolean', 'android.graphics.PointF, android.graphics.PointF, android.graphics.PointF, android.graphics.PointF'), ('int', 'int, int'), ('void', 'android.graphics.Rect'), ('void', 'android.graphics.RectF'), ('double', 'double'), ('double', 'double, double'), ('void', 'android.graphics.RectF, float'), ('void', 'android.graphics.Rect, float'), ('java.lang.String', 'boolean'), ('java.lang.String', 'float, int'), ('java.lang.String', 'double, int'), ('java.lang.String', 'java.lang.String, int'), ('java.lang.String', 'byte[]'), ('void', 'byte[], byte[]'), ('int', 'int, int, float'), ('java.lang.String', 'java.lang.String, int, java.lang.String'), ('java.lang.String', 'java.lang.Class, int'), ('boolean', 'android.graphics.Rect, android.graphics.RectF'), ('boolean', 'android.graphics.RectF, android.graphics.RectF'), ('long', 'long, long'), ('int', 'java.lang.String, char'), ('java.lang.String', 'java.io.File'), ('java.lang.String', 'java.lang.Exception'), ('java.lang.String', 'java.lang.Exception, boolean'), ('java.lang.String', 'java.lang.String, java.lang.String'), ('java.lang.String', 'java.lang.CharSequence, java.lang.Iterable'), ('java.lang.String', 'long'), ('java.lang.String', 'java.lang.String, java.lang.String, java.lang.String'), ('boolean', 'java.lang.Integer, java.lang.Integer')] |
| method | `a` | [('void', ''), ('int', 'com.corrodinggames.rts.game.units.am, int, int'), ('float', 'com.corrodinggames.rts.game.units.am, float, float, int'), ('int', 'com.corrodinggames.rts.gameFramework.w, int, int, int'), ('float', 'float, float, int'), ('int', 'int, int, int'), ('java.lang.String', 'java.lang.String'), ('void', 'java.io.InputStream, java.io.OutputStream'), ('java.lang.String', 'java.io.InputStream'), ('float', 'float'), ('int', 'int'), ('float', 'float, float'), ('float', 'float, float, float'), ('void', 'float, float, float, android.graphics.PointF'), ('float', 'float, float, float, float'), ('int', 'int, int, int, int'), ('float', 'float, boolean'), ('boolean', 'android.graphics.PointF, android.graphics.PointF, android.graphics.PointF, android.graphics.PointF'), ('int', 'int, int'), ('void', 'android.graphics.Rect'), ('void', 'android.graphics.RectF'), ('double', 'double'), ('double', 'double, double'), ('void', 'android.graphics.RectF, float'), ('void', 'android.graphics.Rect, float'), ('java.lang.String', 'boolean'), ('java.lang.String', 'float, int'), ('java.lang.String', 'double, int'), ('java.lang.String', 'java.lang.String, int'), ('java.lang.String', 'byte[]'), ('void', 'byte[], byte[]'), ('int', 'int, int, float'), ('java.lang.String', 'java.lang.String, int, java.lang.String'), ('java.lang.String', 'java.lang.Class, int'), ('boolean', 'android.graphics.Rect, android.graphics.RectF'), ('boolean', 'android.graphics.RectF, android.graphics.RectF'), ('long', 'long, long'), ('int', 'java.lang.String, char'), ('java.lang.String', 'java.io.File'), ('java.lang.String', 'java.lang.Exception'), ('java.lang.String', 'java.lang.Exception, boolean'), ('java.lang.String', 'java.lang.String, java.lang.String'), ('java.lang.String', 'java.lang.CharSequence, java.lang.Iterable'), ('java.lang.String', 'long'), ('java.lang.String', 'java.lang.String, java.lang.String, java.lang.String'), ('boolean', 'java.lang.Integer, java.lang.Integer')] |
| method | `b` | [('float', 'com.corrodinggames.rts.game.units.am, float, float, int'), ('float', 'float, float, int'), ('float', 'float, float'), ('float', 'float, float, float'), ('int', 'int, int, int'), ('int', 'int'), ('float', 'float, float, float, float'), ('float', 'float'), ('int', 'int, int'), ('void', 'android.graphics.Rect, float'), ('java.lang.String', ''), ('java.lang.String', 'double'), ('java.lang.String', 'double, int'), ('java.lang.String', 'java.lang.String, int'), ('java.lang.String', 'java.lang.String'), ('java.lang.String', 'byte[]'), ('int', 'int, int, int, int'), ('java.lang.String', 'java.io.InputStream'), ('java.lang.String', 'java.lang.Exception'), ('java.lang.String', 'java.lang.String, java.lang.String'), ('int[]', 'long'), ('boolean', 'java.lang.String, char'), ('boolean', 'double, double')] |
| method | `b` | [('float', 'com.corrodinggames.rts.game.units.am, float, float, int'), ('float', 'float, float, int'), ('float', 'float, float'), ('float', 'float, float, float'), ('int', 'int, int, int'), ('int', 'int'), ('float', 'float, float, float, float'), ('float', 'float'), ('int', 'int, int'), ('void', 'android.graphics.Rect, float'), ('java.lang.String', ''), ('java.lang.String', 'double'), ('java.lang.String', 'double, int'), ('java.lang.String', 'java.lang.String, int'), ('java.lang.String', 'java.lang.String'), ('java.lang.String', 'byte[]'), ('int', 'int, int, int, int'), ('java.lang.String', 'java.io.InputStream'), ('java.lang.String', 'java.lang.Exception'), ('java.lang.String', 'java.lang.String, java.lang.String'), ('int[]', 'long'), ('boolean', 'java.lang.String, char'), ('boolean', 'double, double')] |
| method | `c` | [('int', 'float, float, float, float'), ('float', 'float, float, float'), ('float', 'float, float'), ('int', 'int'), ('float', 'float'), ('int', 'int, int'), ('java.lang.String', 'double'), ('java.lang.String', 'java.lang.String'), ('java.lang.String', 'java.lang.String, int'), ('byte[]', 'byte[]'), ('int', ''), ('boolean', 'java.lang.String, java.lang.String'), ('java.lang.String[]', 'java.lang.String, char')] |
| method | `c` | [('int', 'float, float, float, float'), ('float', 'float, float, float'), ('float', 'float, float'), ('int', 'int'), ('float', 'float'), ('int', 'int, int'), ('java.lang.String', 'double'), ('java.lang.String', 'java.lang.String'), ('java.lang.String', 'java.lang.String, int'), ('byte[]', 'byte[]'), ('int', ''), ('boolean', 'java.lang.String, java.lang.String'), ('java.lang.String[]', 'java.lang.String, char')] |
| method | `d` | [('float', 'float, float, float, float'), ('float', 'float, float'), ('android.graphics.PointF', 'float, float, float'), ('int', 'int'), ('float', 'float'), ('java.lang.String', 'java.lang.String'), ('java.lang.String', 'java.lang.String, int'), ('boolean', 'java.lang.String, java.lang.String')] |
| method | `d` | [('float', 'float, float, float, float'), ('float', 'float, float'), ('android.graphics.PointF', 'float, float, float'), ('int', 'int'), ('float', 'float'), ('java.lang.String', 'java.lang.String'), ('java.lang.String', 'java.lang.String, int'), ('boolean', 'java.lang.String, java.lang.String')] |
| method | `e` | [('float', 'float, float'), ('boolean', 'float, float, float'), ('float', 'float'), ('java.lang.String', 'int'), ('java.lang.String', 'java.lang.String'), ('java.lang.String', 'java.lang.String, int')] |
| method | `f` | [('float', 'float, float'), ('int', 'float'), ('byte[]', 'java.lang.String'), ('float', 'float, float, float'), ('java.lang.String', 'java.lang.String, int'), ('java.lang.String', 'int')] |
| method | `g` | [('float', 'float, float'), ('java.lang.String', 'float'), ('java.lang.String', 'int'), ('java.lang.String', 'java.lang.String')] |
| method | `h` | [('boolean', 'float, float'), ('java.lang.String', 'float'), ('java.lang.String', 'int'), ('java.lang.String', 'java.lang.String')] |
| method | `i` | [('float', 'float'), ('java.lang.String', 'java.lang.String'), ('float', 'float, float')] |
| method | `j` | [('java.lang.String', 'java.lang.String'), ('float', 'float'), ('boolean', 'float, float')] |
| method | `k` | [('java.lang.String', 'java.lang.String'), ('float', 'float'), ('boolean', 'float, float')] |
| method | `l` | [('java.lang.Integer', 'java.lang.String')] |
| method | `l` | [('java.lang.Integer', 'java.lang.String')] |
| method | `m` | [('java.lang.Long', 'java.lang.String')] |
| method | `m` | [('java.lang.Long', 'java.lang.String')] |
| method | `n` | [('boolean', 'java.lang.String')] |
| method | `n` | [('boolean', 'java.lang.String')] |
| method | `o` | [('java.lang.String', 'java.lang.String')] |
| method | `o` | [('java.lang.String', 'java.lang.String')] |
| method | `p` | [('java.lang.String', 'java.lang.String')] |
| method | `p` | [('java.lang.String', 'java.lang.String')] |
| method | `q` | [('java.lang.String', 'java.lang.String')] |
| method | `q` | [('java.lang.String', 'java.lang.String')] |
| method | `r` | [('boolean', 'java.lang.String')] |
| method | `r` | [('boolean', 'java.lang.String')] |
| method | `s` | [('boolean', 'java.lang.String')] |
| method | `s` | [('boolean', 'java.lang.String')] |

### ar (com.corrodinggames.rts.game.units.ar) — 57 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `A` | com.corrodinggames.rts.game.units.ar |
| field | `B` | com.corrodinggames.rts.game.units.ar |
| field | `C` | com.corrodinggames.rts.game.units.ar |
| field | `D` | com.corrodinggames.rts.game.units.ar |
| field | `E` | com.corrodinggames.rts.game.units.ar |
| field | `F` | com.corrodinggames.rts.game.units.ar |
| field | `G` | com.corrodinggames.rts.game.units.ar |
| field | `H` | com.corrodinggames.rts.game.units.ar |
| field | `I` | com.corrodinggames.rts.game.units.ar |
| field | `J` | com.corrodinggames.rts.game.units.ar |
| field | `K` | com.corrodinggames.rts.game.units.ar |
| field | `L` | com.corrodinggames.rts.game.units.ar |
| field | `M` | com.corrodinggames.rts.game.units.ar |
| field | `N` | com.corrodinggames.rts.game.units.ar |
| field | `O` | com.corrodinggames.rts.game.units.ar |
| field | `P` | com.corrodinggames.rts.game.units.ar |
| field | `Q` | com.corrodinggames.rts.game.units.ar |
| field | `R` | com.corrodinggames.rts.game.units.ar |
| field | `S` | com.corrodinggames.rts.game.units.ar |
| field | `T` | com.corrodinggames.rts.game.units.ar |
| field | `U` | com.corrodinggames.rts.game.units.ar |
| field | `V` | com.corrodinggames.rts.game.units.ar |
| field | `W` | com.corrodinggames.rts.game.units.ar |
| field | `X` | com.corrodinggames.rts.game.units.ar |
| field | `Y` | com.corrodinggames.rts.game.units.ar |
| field | `Z` | com.corrodinggames.rts.game.units.ar |
| field | `ae` | java.util.ArrayList |
| field | `ag` | boolean |
| field | `ai` | com.corrodinggames.rts.game.units.ar[] |
| method | `A` | [('boolean', '')] |
| method | `B` | [('com.corrodinggames.rts.game.units.custom.d.b', '')] |
| method | `a` | [('com.corrodinggames.rts.game.units.am', ''), ('com.corrodinggames.rts.game.units.am', 'boolean'), ('void', 'java.util.ArrayList, int'), ('java.util.ArrayList', 'int'), ('com.corrodinggames.rts.game.units.as', 'java.lang.String'), ('com.corrodinggames.rts.game.units.as', 'java.lang.String, boolean'), ('java.lang.String', 'java.lang.String, float'), ('java.lang.String', 'java.lang.String, float, java.lang.String'), ('java.lang.String', 'java.lang.String, java.lang.String, java.lang.String'), ('int', 'com.corrodinggames.rts.game.units.y'), ('boolean', 'com.corrodinggames.rts.game.units.as, float, float, float, float, com.corrodinggames.rts.game.n'), ('void', 'com.corrodinggames.rts.game.units.as, float, float, float, float, com.corrodinggames.rts.game.n, float, float, boolean, boolean, int, com.corrodinggames.rts.game.units.am'), ('void', 'com.corrodinggames.rts.game.units.as, float, float, float, float, com.corrodinggames.rts.game.n, float, float, boolean, boolean, int, boolean, com.corrodinggames.rts.game.units.am'), ('int', 'com.corrodinggames.rts.game.units.am')] |
| method | `b` | [('void', ''), ('int', 'int')] |
| method | `c` | [('int', ''), ('int', 'int')] |
| method | `d` | [('com.corrodinggames.rts.game.units.a.z', ''), ('com.corrodinggames.rts.game.units.custom.d.b', 'int')] |
| method | `e` | [('java.lang.String', '')] |
| method | `f` | [('java.lang.String', '')] |
| method | `g` | [('int', '')] |
| method | `h` | [('void', '')] |
| method | `i` | [('java.lang.String', '')] |
| method | `j` | [('boolean', '')] |
| method | `k` | [('boolean', '')] |
| method | `l` | [('boolean', '')] |
| method | `m` | [('boolean', '')] |
| method | `n` | [('boolean', '')] |
| method | `o` | [('com.corrodinggames.rts.game.units.ao', '')] |
| method | `p` | [('boolean', '')] |
| method | `q` | [('com.corrodinggames.rts.game.units.custom.be', '')] |
| method | `r` | [('void', '')] |
| method | `s` | [('void', '')] |
| method | `t` | [('void', '')] |
| method | `u` | [('com.corrodinggames.rts.game.units.custom.d.b', '')] |
| method | `v` | [('java.lang.String', '')] |
| method | `w` | [('boolean', '')] |
| method | `x` | [('com.corrodinggames.rts.game.units.custom.h', '')] |
| method | `y` | [('boolean', '')] |
| method | `z` | [('com.corrodinggames.rts.gameFramework.m.e', '')] |

### ad (com.corrodinggames.rts.gameFramework.j.ad) — 46 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `F` | boolean |
| field | `Q` | int |
| field | `R` | int |
| field | `X` | int |
| field | `aW` | boolean |
| field | `ag` | boolean |
| field | `ah` | int |
| field | `z` | com.corrodinggames.rts.game.n |
| method | `A` | [('void', '')] |
| method | `F` | [('void', '')] |
| method | `G` | [('void', '')] |
| method | `H` | [('void', '')] |
| method | `J` | [('void', '')] |
| method | `K` | [('void', '')] |
| method | `L` | [('void', '')] |
| method | `U` | [('void', '')] |
| method | `W` | [('com.corrodinggames.rts.gameFramework.j.c', '')] |
| method | `X` | [('void', '')] |
| method | `aA` | [('void', '')] |
| method | `aB` | [('void', '')] |
| method | `aC` | [('void', '')] |
| method | `aD` | [('void', '')] |
| method | `aE` | [('void', '')] |
| method | `aa` | [('void', '')] |
| method | `ab` | [('java.lang.String', '')] |
| method | `ac` | [('java.lang.String', '')] |
| method | `ad` | [('void', '')] |
| method | `ag` | [('void', '')] |
| method | `aj` | [('java.util.ArrayList', '')] |
| method | `ak` | [('java.lang.String', '')] |
| method | `al` | [('java.net.InetAddress', '')] |
| method | `am` | [('void', '')] |
| method | `ar` | [('void', '')] |
| method | `as` | [('void', '')] |
| method | `at` | [('java.lang.String', '')] |
| method | `au` | [('java.lang.String', '')] |
| method | `av` | [('java.lang.String', '')] |
| method | `aw` | [('boolean', '')] |
| method | `ax` | [('java.util.ArrayList', '')] |
| method | `ay` | [('void', '')] |
| method | `az` | [('void', '')] |
| method | `j` | [('java.lang.String', ''), ('void', 'java.lang.String')] |
| method | `k` | [('int', ''), ('void', 'java.lang.String')] |
| method | `q` | [('void', ''), ('void', 'java.lang.String')] |
| method | `r` | [('void', '')] |
| method | `t` | [('void', '')] |

### com.corrodinggames.rts.game.i (com.corrodinggames.rts.game.i) — 41 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `A` | android.graphics.Paint |
| field | `B` | com.corrodinggames.rts.gameFramework.bf |
| field | `C` | com.corrodinggames.rts.gameFramework.be |
| field | `D` | com.corrodinggames.rts.gameFramework.d.b |
| field | `H` | float |
| field | `I` | float |
| field | `J` | float |
| field | `U` | com.corrodinggames.rts.gameFramework.m.e |
| field | `V` | com.corrodinggames.rts.gameFramework.m.e |
| field | `Z` | java.util.ArrayList |
| field | `aa` | java.util.ArrayList |
| field | `k` | java.util.concurrent.ConcurrentLinkedQueue |
| field | `u` | java.lang.String |
| field | `w` | java.util.ArrayList |
| method | `a` | [('boolean', ''), ('boolean', 'boolean'), ('void', 'android.content.Context'), ('void', 'int, int'), ('void', 'boolean, com.corrodinggames.rts.gameFramework.s'), ('void', 'boolean, boolean, com.corrodinggames.rts.gameFramework.s'), ('void', 'float, int'), ('void', 'float'), ('void', 'com.corrodinggames.rts.gameFramework.m.l, float'), ('void', 'com.corrodinggames.rts.game.j'), ('void', 'int, int, float'), ('void', 'android.app.Activity, com.corrodinggames.rts.appFramework.f, boolean'), ('com.corrodinggames.rts.game.units.am', 'com.corrodinggames.rts.game.n'), ('boolean', 'float, float, float, float')] |
| method | `aG` | [('void', '')] |
| method | `b` | [('int', ''), ('void', 'boolean'), ('void', 'float, int'), ('void', 'com.corrodinggames.rts.game.j'), ('void', 'com.corrodinggames.rts.gameFramework.m.l, float'), ('void', 'float'), ('void', 'int, int')] |
| method | `c` | [('boolean', ''), ('void', 'float'), ('void', 'com.corrodinggames.rts.gameFramework.m.l, float'), ('int', 'boolean')] |
| method | `d` | [('boolean', ''), ('void', 'com.corrodinggames.rts.gameFramework.m.l, float'), ('void', 'float')] |
| method | `e` | [('void', '')] |
| method | `f` | [('void', '')] |
| method | `g` | [('void', '')] |
| method | `h` | [('void', '')] |
| method | `i` | [('boolean', '')] |
| method | `j` | [('boolean', '')] |
| method | `k` | [('void', '')] |
| method | `l` | [('java.lang.String', '')] |
| method | `m` | [('java.lang.String', '')] |
| method | `n` | [('boolean', '')] |
| method | `o` | [('java.lang.String', '')] |
| method | `p` | [('boolean', '')] |
| method | `q` | [('boolean', '')] |
| method | `r` | [('java.lang.String', '')] |
| method | `s` | [('void', '')] |
| method | `t` | [('java.lang.String', '')] |
| method | `u` | [('java.lang.String', '')] |
| method | `v` | [('java.lang.String', '')] |
| method | `w` | [('void', '')] |
| method | `x` | [('void', '')] |
| method | `y` | [('com.corrodinggames.rts.game.units.am', '')] |
| method | `z` | [('int', '')] |

### h (com.corrodinggames.rts.game.units.h) — 28 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| method | `A` | [('float', '')] |
| method | `B` | [('float', '')] |
| method | `C` | [('float', '')] |
| method | `D` | [('float', '')] |
| method | `E` | [('boolean', '')] |
| method | `F` | [('boolean', '')] |
| method | `G` | [('float', '')] |
| method | `H` | [('float', '')] |
| method | `I` | [('boolean', '')] |
| method | `J` | [('boolean', '')] |
| method | `K` | [('void', '')] |
| method | `L` | [('com.corrodinggames.rts.game.units.h', '')] |
| method | `M` | [('void', '')] |
| method | `N` | [('java.util.ArrayList', '')] |
| method | `O` | [('void', '')] |
| method | `P` | [('boolean', '')] |
| method | `a` | [('boolean', 'com.corrodinggames.rts.game.units.am'), ('void', 'float, com.corrodinggames.rts.game.units.d'), ('void', 'float'), ('void', 'float, boolean'), ('void', 'com.corrodinggames.rts.game.units.am, int'), ('void', 'com.corrodinggames.rts.game.units.a.s, boolean'), ('void', 'com.corrodinggames.rts.game.units.a.s, boolean, android.graphics.PointF, com.corrodinggames.rts.game.units.am'), ('boolean', 'com.corrodinggames.rts.game.units.a.s, com.corrodinggames.rts.game.units.am'), ('void', 'java.util.ArrayList, int'), ('float', 'com.corrodinggames.rts.game.units.am, float, com.corrodinggames.rts.game.f'), ('void', 'com.corrodinggames.rts.game.units.h'), ('void', 'com.corrodinggames.rts.gameFramework.j.as'), ('void', 'com.corrodinggames.rts.gameFramework.j.k')] |
| method | `b` | [('android.graphics.PointF[]', ''), ('float', 'com.corrodinggames.rts.game.units.am'), ('float', 'int')] |
| method | `b_` | [('boolean', '')] |
| method | `c` | [('boolean', 'float'), ('float', 'com.corrodinggames.rts.game.units.am'), ('float', 'int')] |
| method | `d` | [('com.corrodinggames.rts.gameFramework.m.e', ''), ('com.corrodinggames.rts.gameFramework.m.e', 'int'), ('boolean', 'com.corrodinggames.rts.game.units.am')] |
| method | `e_` | [('android.graphics.PointF[]', '')] |
| method | `r` | [('com.corrodinggames.rts.game.units.as', '')] |
| method | `u` | [('boolean', '')] |
| method | `v` | [('com.corrodinggames.rts.gameFramework.m.e', '')] |
| method | `w` | [('boolean', '')] |
| method | `y` | [('int', '')] |
| method | `z` | [('float', '')] |

### ag (com.corrodinggames.rts.game.units.custom.ag) — 28 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `g` | java.util.HashMap |
| field | `h` | java.util.HashMap |
| field | `i` | int |
| field | `j` | int |
| field | `k` | boolean |
| field | `l` | int |
| field | `m` | com.corrodinggames.rts.gameFramework.utility.m |
| field | `n` | java.util.HashMap |
| field | `o` | java.lang.Object |
| field | `p` | float |
| field | `q` | float |
| field | `r` | com.corrodinggames.rts.gameFramework.i.b |
| field | `s` | java.lang.String |
| method | `a` | [('void', 'int'), ('void', ''), ('void', 'com.corrodinggames.rts.gameFramework.m.e'), ('void', 'com.corrodinggames.rts.gameFramework.m.e[]'), ('void', 'com.corrodinggames.rts.gameFramework.a.i'), ('boolean', 'com.corrodinggames.rts.gameFramework.utility.m'), ('java.util.ArrayList', 'boolean'), ('com.corrodinggames.rts.gameFramework.utility.ab', 'java.lang.String'), ('void', 'com.corrodinggames.rts.game.units.custom.l, com.corrodinggames.rts.gameFramework.utility.ab, java.lang.String, java.lang.String, boolean'), ('void', 'com.corrodinggames.rts.game.units.custom.l, com.corrodinggames.rts.gameFramework.utility.ab, com.corrodinggames.rts.gameFramework.utility.ab, java.lang.String, int'), ('void', 'com.corrodinggames.rts.game.units.custom.l, com.corrodinggames.rts.gameFramework.utility.ab, java.lang.String, java.lang.String, int'), ('com.corrodinggames.rts.game.units.custom.bb', 'com.corrodinggames.rts.gameFramework.utility.ab, java.lang.String, java.lang.String, java.lang.String'), ('com.corrodinggames.rts.game.units.custom.aj', 'com.corrodinggames.rts.game.units.custom.l, com.corrodinggames.rts.gameFramework.utility.ab, java.lang.String, java.lang.String, java.lang.String'), ('com.corrodinggames.rts.game.units.custom.l', 'com.corrodinggames.rts.game.units.custom.l'), ('void', 'com.corrodinggames.rts.game.units.as, com.corrodinggames.rts.game.units.custom.l, boolean'), ('java.lang.String', 'java.util.ArrayList'), ('void', 'com.corrodinggames.rts.game.units.as'), ('com.corrodinggames.rts.game.units.custom.l', 'java.lang.String, com.corrodinggames.rts.gameFramework.i.b, java.lang.String, java.lang.String'), ('void', 'java.lang.String, int, boolean, com.corrodinggames.rts.gameFramework.i.b, java.lang.String, java.lang.String'), ('com.corrodinggames.rts.game.units.custom.l', 'java.lang.String, java.io.InputStream, long, com.corrodinggames.rts.gameFramework.i.b, com.corrodinggames.rts.gameFramework.utility.j, java.lang.String, java.lang.String'), ('void', 'java.lang.String, java.lang.Exception, com.corrodinggames.rts.game.units.as'), ('java.lang.String', 'com.corrodinggames.rts.gameFramework.i.b, java.lang.String, boolean'), ('void', 'java.lang.String, java.lang.Exception, com.corrodinggames.rts.gameFramework.i.b'), ('void', 'com.corrodinggames.rts.game.units.custom.l, com.corrodinggames.rts.gameFramework.utility.ab, java.lang.String, java.lang.String, java.lang.String, boolean, boolean'), ('java.lang.String', 'com.corrodinggames.rts.game.units.custom.l, java.lang.String, java.lang.String'), ('void', 'long, com.corrodinggames.rts.game.units.custom.ah'), ('com.corrodinggames.rts.gameFramework.m.e', 'java.lang.String, java.lang.String, boolean, com.corrodinggames.rts.game.units.custom.l, java.lang.String, java.lang.String'), ('com.corrodinggames.rts.gameFramework.m.e', 'java.lang.String, java.lang.String, boolean, com.corrodinggames.rts.game.units.custom.l'), ('void', 'java.lang.String, com.corrodinggames.rts.gameFramework.m.e'), ('com.corrodinggames.rts.gameFramework.a.i', 'java.lang.String, java.lang.String, com.corrodinggames.rts.game.units.custom.l'), ('boolean', 'java.lang.String, java.lang.String, java.lang.String, com.corrodinggames.rts.gameFramework.i.b'), ('java.lang.String', 'java.lang.String, java.lang.String'), ('java.util.ArrayList', 'java.lang.String, java.lang.String, java.lang.String')] |
| method | `b` | [('void', ''), ('java.lang.String', 'boolean'), ('com.corrodinggames.rts.gameFramework.utility.j', 'java.lang.String'), ('void', 'java.util.ArrayList'), ('void', 'com.corrodinggames.rts.game.units.custom.l, com.corrodinggames.rts.gameFramework.utility.ab, java.lang.String, java.lang.String, boolean'), ('com.corrodinggames.rts.gameFramework.m.e', 'java.lang.String, java.lang.String, boolean, com.corrodinggames.rts.game.units.custom.l'), ('com.corrodinggames.rts.gameFramework.a.i', 'java.lang.String, java.lang.String, com.corrodinggames.rts.game.units.custom.l'), ('void', 'com.corrodinggames.rts.game.units.custom.l')] |
| method | `c` | [('void', ''), ('boolean', 'boolean'), ('com.corrodinggames.rts.gameFramework.m.e', 'java.lang.String'), ('com.corrodinggames.rts.gameFramework.utility.j', 'java.lang.String, java.lang.String, com.corrodinggames.rts.game.units.custom.l')] |
| method | `d` | [('void', '')] |
| method | `e` | [('void', '')] |
| method | `f` | [('void', '')] |
| method | `g` | [('void', '')] |
| method | `h` | [('void', '')] |
| method | `i` | [('void', '')] |
| method | `j` | [('void', '')] |
| method | `k` | [('java.lang.String', '')] |
| method | `l` | [('java.lang.String', '')] |
| method | `m` | [('java.lang.String', '')] |
| method | `n` | [('void', '')] |
| method | `o` | [('void', '')] |

### n (com.corrodinggames.rts.game.n) — 27 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| method | `A` | [('int', '')] |
| method | `C` | [('int', '')] |
| method | `D` | [('float', '')] |
| method | `E` | [('float', '')] |
| method | `F` | [('void', '')] |
| method | `G` | [('int', '')] |
| method | `H` | [('int', '')] |
| method | `I` | [('void', '')] |
| method | `J` | [('void', '')] |
| method | `K` | [('int', '')] |
| method | `L` | [('void', '')] |
| method | `M` | [('int', '')] |
| method | `N` | [('java.lang.String', '')] |
| method | `O` | [('void', '')] |
| method | `P` | [('void', '')] |
| method | `R` | [('int', '')] |
| method | `S` | [('int', '')] |
| method | `T` | [('void', '')] |
| method | `V` | [('com.corrodinggames.rts.game.units.custom.e.f', '')] |
| method | `Y` | [('void', '')] |
| method | `ab` | [('com.corrodinggames.rts.game.units.custom.e.f', '')] |
| method | `p` | [('boolean', '')] |
| method | `r` | [('boolean', '')] |
| method | `w` | [('int', '')] |
| method | `x` | [('int', '')] |
| method | `y` | [('java.lang.String', '')] |
| method | `z` | [('java.lang.String', '')] |

### com.corrodinggames.rts.game.units.d.d (com.corrodinggames.rts.game.units.d.d) — 27 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `n` | android.graphics.Rect |
| field | `o` | android.graphics.Rect |
| field | `p` | com.corrodinggames.rts.gameFramework.m.e |
| field | `q` | com.corrodinggames.rts.gameFramework.m.e[] |
| method | `A` | [('float', '')] |
| method | `I` | [('boolean', '')] |
| method | `L` | [('boolean', '')] |
| method | `Q` | [('boolean', '')] |
| method | `R` | [('void', 'int')] |
| method | `a` | [('void', 'com.corrodinggames.rts.gameFramework.j.as'), ('void', 'com.corrodinggames.rts.gameFramework.j.k'), ('boolean', 'com.corrodinggames.rts.game.units.as, float, float, com.corrodinggames.rts.game.n'), ('boolean', 'com.corrodinggames.rts.game.units.y, com.corrodinggames.rts.game.units.as, com.corrodinggames.rts.game.units.ao, int, int, int'), ('boolean', 'com.corrodinggames.rts.game.units.y, com.corrodinggames.rts.game.units.as, com.corrodinggames.rts.game.units.ao, int, int, boolean'), ('java.lang.String', 'com.corrodinggames.rts.game.units.y, com.corrodinggames.rts.game.units.as, com.corrodinggames.rts.game.units.ao, int, int, boolean, com.corrodinggames.rts.game.n'), ('void', 'int')] |
| method | `b` | [('com.corrodinggames.rts.game.units.am', 'int, int')] |
| method | `bI` | [('boolean', '')] |
| method | `b_` | [('boolean', '')] |
| method | `c` | [('boolean', 'float')] |
| method | `cc` | [('android.graphics.Rect', '')] |
| method | `cd` | [('android.graphics.Rect', '')] |
| method | `d` | [('com.corrodinggames.rts.gameFramework.m.e', 'int'), ('void', 'float')] |
| method | `ds` | [('boolean', '')] |
| method | `dt` | [('void', '')] |
| method | `e` | [('boolean', '')] |
| method | `f` | [('android.graphics.Paint', '')] |
| method | `f_` | [('void', '')] |
| method | `g` | [('com.corrodinggames.rts.game.units.am', 'com.corrodinggames.rts.game.units.as')] |
| method | `h` | [('com.corrodinggames.rts.game.units.ao', '')] |
| method | `i` | [('boolean', '')] |
| method | `v` | [('com.corrodinggames.rts.gameFramework.m.e', '')] |
| method | `z` | [('float', '')] |

### k (com.corrodinggames.rts.gameFramework.j.k) — 27 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `f` | java.io.DataInputStream |
| field | `g` | java.util.LinkedList |
| method | `a` | [('void', ''), ('void', 'int'), ('com.corrodinggames.rts.gameFramework.w', 'java.lang.Class'), ('void', 'com.corrodinggames.rts.gameFramework.utility.m, java.lang.Class'), ('com.corrodinggames.rts.game.units.am', 'com.corrodinggames.rts.gameFramework.j.m'), ('void', 'java.lang.String'), ('void', 'java.lang.String, boolean'), ('void', 'java.lang.String, boolean, boolean'), ('java.lang.String', 'boolean, boolean')] |
| method | `b` | [('int', ''), ('void', 'int'), ('java.lang.Enum', 'java.lang.Class'), ('void', 'java.lang.String')] |
| method | `c` | [('int', ''), ('byte[]', 'java.lang.String')] |
| method | `d` | [('byte', ''), ('void', 'java.lang.String')] |
| method | `e` | [('boolean', '')] |
| method | `f` | [('int', '')] |
| method | `g` | [('float', '')] |
| method | `h` | [('double', '')] |
| method | `i` | [('long', '')] |
| method | `j` | [('java.lang.String', '')] |
| method | `k` | [('java.lang.Integer', '')] |
| method | `l` | [('java.lang.String', '')] |
| method | `m` | [('com.corrodinggames.rts.game.units.custom.g', '')] |
| method | `n` | [('long', '')] |
| method | `o` | [('com.corrodinggames.rts.game.units.am', '')] |
| method | `p` | [('com.corrodinggames.rts.game.units.y', '')] |
| method | `q` | [('com.corrodinggames.rts.game.units.as', '')] |
| method | `r` | [('com.corrodinggames.rts.game.n', '')] |
| method | `s` | [('com.corrodinggames.rts.game.n', '')] |
| method | `t` | [('byte[]', '')] |
| method | `u` | [('com.corrodinggames.rts.gameFramework.j.k', '')] |
| method | `v` | [('short', '')] |
| method | `w` | [('java.io.InputStream', '')] |
| method | `x` | [('java.lang.String', '')] |
| method | `y` | [('android.graphics.PointF', '')] |

### c (com.corrodinggames.rts.gameFramework.j.c) — 25 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `E` | int |
| field | `L` | java.lang.String |
| field | `M` | int |
| field | `N` | boolean |
| field | `O` | boolean |
| field | `P` | int |
| field | `Q` | boolean |
| field | `R` | int |
| field | `S` | long |
| field | `T` | boolean |
| field | `U` | int |
| field | `V` | int |
| field | `W` | com.corrodinggames.rts.gameFramework.j.ad |
| field | `a` | boolean |
| field | `b` | boolean |
| method | `a` | [('boolean', ''), ('void', 'java.lang.String'), ('void', 'boolean, boolean'), ('void', 'boolean, boolean, java.lang.String'), ('void', 'java.lang.String, java.lang.Throwable'), ('void', 'com.corrodinggames.rts.gameFramework.j.au'), ('void', 'com.corrodinggames.rts.gameFramework.j.c, boolean, boolean'), ('com.corrodinggames.rts.gameFramework.j.ad', 'com.corrodinggames.rts.gameFramework.j.c')] |
| method | `b` | [('int', ''), ('void', 'java.lang.String')] |
| method | `c` | [('int', ''), ('void', 'java.lang.String')] |
| method | `d` | [('void', ''), ('java.lang.String', 'java.lang.String')] |
| method | `e` | [('java.lang.String', '')] |
| method | `f` | [('java.lang.String', '')] |
| method | `g` | [('java.lang.String', '')] |
| method | `h` | [('boolean', '')] |
| method | `i` | [('void', '')] |
| method | `j` | [('void', '')] |

### com.corrodinggames.rts.game.units.d.e (com.corrodinggames.rts.game.units.d.e) — 24 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `f` | float |
| field | `g` | float |
| field | `h` | int |
| field | `i` | float |
| field | `j` | float |
| method | `K` | [('com.corrodinggames.rts.game.units.ar', '')] |
| method | `L` | [('boolean', '')] |
| method | `N` | [('java.util.ArrayList', '')] |
| method | `S` | [('void', '')] |
| method | `a` | [('void', 'com.corrodinggames.rts.gameFramework.j.as'), ('void', 'com.corrodinggames.rts.gameFramework.j.k'), ('void', 'int'), ('void', 'float'), ('void', 'com.corrodinggames.rts.game.units.am, int'), ('void', 'java.util.ArrayList, int'), ('float', 'com.corrodinggames.rts.game.units.am, float, com.corrodinggames.rts.game.f')] |
| method | `b` | [('void', ''), ('float', 'int'), ('boolean', 'int, float')] |
| method | `bJ` | [('boolean', '')] |
| method | `bp` | [('int', '')] |
| method | `c` | [('float', 'int')] |
| method | `cF` | [('android.graphics.RectF', '')] |
| method | `cy` | [('float', '')] |
| method | `d` | [('com.corrodinggames.rts.gameFramework.m.e', '')] |
| method | `e` | [('void', 'float')] |
| method | `k` | [('com.corrodinggames.rts.gameFramework.m.e', '')] |
| method | `l` | [('boolean', '')] |
| method | `m` | [('float', '')] |
| method | `q` | [('float', 'int')] |
| method | `r` | [('com.corrodinggames.rts.game.units.as', '')] |
| method | `s` | [('int', '')] |

### e (com.corrodinggames.rts.gameFramework.e) — 23 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `a` | boolean |
| field | `b` | java.lang.String |
| field | `c` | int |
| field | `d` | int |
| field | `g` | boolean |
| field | `i` | com.corrodinggames.rts.game.n |
| field | `j` | com.corrodinggames.rts.game.units.au |
| field | `k` | com.corrodinggames.rts.game.units.a.c |
| field | `n` | com.corrodinggames.rts.game.units.a |
| field | `p` | com.corrodinggames.rts.game.n |
| field | `q` | short |
| field | `r` | boolean |
| field | `s` | float |
| field | `u` | int |
| method | `b` | [('void', ''), ('void', 'float, float'), ('void', 'com.corrodinggames.rts.game.units.am')] |
| method | `c` | [('com.corrodinggames.rts.game.n', ''), ('void', 'com.corrodinggames.rts.game.units.am'), ('void', 'float, float')] |
| method | `d` | [('int', ''), ('void', 'com.corrodinggames.rts.game.units.am')] |
| method | `e` | [('boolean', ''), ('void', 'com.corrodinggames.rts.game.units.am')] |
| method | `f` | [('com.corrodinggames.rts.gameFramework.e', ''), ('void', 'com.corrodinggames.rts.game.units.am')] |
| method | `g` | [('void', '')] |
| method | `h` | [('void', '')] |
| method | `i` | [('void', '')] |
| method | `j` | [('void', '')] |

### a (com.corrodinggames.rts.game.units.custom.e.a) — 22 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `A` | java.util.ArrayList |
| field | `B` | java.util.ArrayList |
| field | `C` | java.util.ArrayList |
| field | `D` | com.corrodinggames.rts.game.units.custom.e.a |
| field | `E` | com.corrodinggames.rts.game.units.custom.e.a |
| field | `F` | com.corrodinggames.rts.game.units.custom.e.a |
| field | `G` | com.corrodinggames.rts.game.units.custom.e.a |
| field | `H` | com.corrodinggames.rts.game.units.custom.e.a |
| field | `x` | float |
| field | `y` | com.corrodinggames.rts.gameFramework.m.e |
| field | `z` | boolean |
| method | `a` | [('boolean', ''), ('java.lang.String', 'double, boolean'), ('java.lang.String', 'java.lang.String, com.corrodinggames.rts.game.units.custom.e.b'), ('java.lang.String', 'long, com.corrodinggames.rts.game.units.custom.e.b'), ('java.lang.String', 'boolean'), ('com.corrodinggames.rts.game.units.custom.e.a', 'java.lang.String'), ('com.corrodinggames.rts.game.units.custom.e.a', 'com.corrodinggames.rts.game.units.custom.e.a'), ('com.corrodinggames.rts.game.units.custom.e.a', 'java.lang.String, boolean, boolean'), ('java.lang.String', 'double'), ('java.lang.String', 'double, boolean, boolean'), ('double', 'com.corrodinggames.rts.game.units.am'), ('void', 'com.corrodinggames.rts.game.units.am, double')] |
| method | `b` | [('float', ''), ('java.lang.String', 'boolean'), ('com.corrodinggames.rts.game.units.custom.e.a', 'java.lang.String'), ('void', 'com.corrodinggames.rts.game.units.am, double')] |
| method | `c` | [('boolean', '')] |
| method | `d` | [('boolean', '')] |
| method | `e` | [('void', '')] |
| method | `f` | [('java.util.ArrayList', '')] |
| method | `g` | [('void', '')] |
| method | `h` | [('java.lang.Integer', '')] |
| method | `i` | [('java.lang.String', '')] |
| method | `j` | [('java.lang.String', '')] |
| method | `k` | [('com.corrodinggames.rts.gameFramework.m.e', '')] |

### com.corrodinggames.rts.gameFramework.n.f (com.corrodinggames.rts.gameFramework.n.f) — 21 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `E` | android.graphics.Paint |
| field | `F` | android.graphics.Paint |
| field | `G` | android.graphics.Paint |
| field | `H` | android.graphics.Paint |
| field | `I` | boolean |
| field | `J` | java.util.ArrayList |
| field | `N` | boolean |
| field | `O` | java.util.ArrayList |
| field | `g` | java.util.ArrayList |
| field | `k` | boolean |
| field | `q` | boolean |
| field | `r` | int |
| method | `a` | [('void', 'java.lang.String'), ('void', 'com.corrodinggames.rts.gameFramework.j.as'), ('void', 'com.corrodinggames.rts.gameFramework.j.k'), ('boolean', ''), ('void', 'boolean'), ('void', 'float'), ('void', 'java.util.ArrayList, java.lang.String, float'), ('void', 'java.util.ArrayList, com.corrodinggames.rts.game.units.as, float'), ('void', 'com.corrodinggames.rts.gameFramework.n.i, int, float'), ('boolean', 'com.corrodinggames.rts.gameFramework.n.l, com.corrodinggames.rts.game.units.am'), ('void', 'com.corrodinggames.rts.gameFramework.n.a'), ('void', 'int')] |
| method | `b` | [('void', 'java.lang.String'), ('boolean', ''), ('void', 'float'), ('com.corrodinggames.rts.gameFramework.n.i', 'boolean')] |
| method | `c` | [('void', ''), ('void', 'java.lang.String'), ('com.corrodinggames.rts.gameFramework.n.i', 'boolean'), ('void', 'float')] |
| method | `d` | [('com.corrodinggames.rts.gameFramework.n.a', 'java.lang.String'), ('com.corrodinggames.rts.gameFramework.n.g', '')] |
| method | `e` | [('com.corrodinggames.rts.gameFramework.n.a', 'java.lang.String'), ('void', '')] |
| method | `f` | [('android.graphics.PointF', 'java.lang.String'), ('void', '')] |
| method | `g` | [('void', 'java.lang.String'), ('boolean', '')] |
| method | `h` | [('void', 'java.lang.String'), ('boolean', '')] |
| method | `i` | [('void', 'java.lang.String')] |

### ba (com.corrodinggames.rts.gameFramework.ba) — 19 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `N` | boolean |
| field | `O` | boolean |
| field | `P` | boolean |
| field | `o` | int |
| field | `p` | int |
| field | `q` | int |
| field | `v` | int |
| method | `a` | [('void', 'java.lang.String'), ('void', 'java.lang.String, java.lang.Exception'), ('java.io.File', 'java.lang.String, boolean'), ('void', 'android.content.Context'), ('void', ''), ('void', 'int, java.lang.String, java.lang.String, int'), ('void', 'byte[], int, int, int, float, float'), ('void', 'com.corrodinggames.rts.gameFramework.e, int'), ('void', 'com.corrodinggames.rts.gameFramework.j.ak'), ('void', 'com.corrodinggames.rts.gameFramework.j.ak, boolean'), ('boolean', 'java.lang.String, java.io.File'), ('void', 'boolean'), ('void', 'float'), ('boolean', 'com.corrodinggames.rts.gameFramework.ba'), ('boolean', 'com.corrodinggames.rts.gameFramework.ba, boolean')] |
| method | `b` | [('void', 'java.lang.String'), ('void', '')] |
| method | `c` | [('void', ''), ('boolean', 'java.lang.String')] |
| method | `d` | [('void', ''), ('void', 'java.lang.String')] |
| method | `e` | [('void', ''), ('void', 'java.lang.String')] |
| method | `f` | [('long', '')] |
| method | `g` | [('void', '')] |
| method | `h` | [('boolean', '')] |
| method | `i` | [('boolean', '')] |
| method | `j` | [('boolean', '')] |
| method | `k` | [('boolean', '')] |
| method | `l` | [('void', '')] |

### f (com.corrodinggames.rts.gameFramework.n.f) — 19 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `E` | android.graphics.Paint |
| field | `F` | android.graphics.Paint |
| field | `G` | android.graphics.Paint |
| field | `H` | android.graphics.Paint |
| field | `I` | boolean |
| field | `N` | boolean |
| field | `a` | boolean |
| field | `h` | com.corrodinggames.rts.game.units.custom.bb |
| field | `l` | boolean |
| field | `y` | int |
| method | `a` | [('void', 'java.lang.String'), ('void', 'com.corrodinggames.rts.gameFramework.j.as'), ('void', 'com.corrodinggames.rts.gameFramework.j.k'), ('boolean', ''), ('void', 'boolean'), ('void', 'float'), ('void', 'java.util.ArrayList, java.lang.String, float'), ('void', 'java.util.ArrayList, com.corrodinggames.rts.game.units.as, float'), ('void', 'com.corrodinggames.rts.gameFramework.n.i, int, float'), ('boolean', 'com.corrodinggames.rts.gameFramework.n.l, com.corrodinggames.rts.game.units.am'), ('void', 'com.corrodinggames.rts.gameFramework.n.a'), ('void', 'int')] |
| method | `b` | [('void', 'java.lang.String'), ('boolean', ''), ('void', 'float'), ('com.corrodinggames.rts.gameFramework.n.i', 'boolean')] |
| method | `c` | [('void', ''), ('void', 'java.lang.String'), ('com.corrodinggames.rts.gameFramework.n.i', 'boolean'), ('void', 'float')] |
| method | `d` | [('com.corrodinggames.rts.gameFramework.n.a', 'java.lang.String'), ('com.corrodinggames.rts.gameFramework.n.g', '')] |
| method | `e` | [('com.corrodinggames.rts.gameFramework.n.a', 'java.lang.String'), ('void', '')] |
| method | `f` | [('android.graphics.PointF', 'java.lang.String'), ('void', '')] |
| method | `g` | [('void', 'java.lang.String'), ('boolean', '')] |
| method | `h` | [('void', 'java.lang.String'), ('boolean', '')] |
| method | `i` | [('void', 'java.lang.String')] |

### b (com.corrodinggames.rts.game.b.b) — 16 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `M` | byte[][] |
| field | `N` | byte[][] |
| field | `P` | java.util.ArrayList |
| field | `T` | int |
| field | `U` | int |
| field | `n` | int |
| field | `o` | int |
| field | `r` | float |
| field | `s` | float |
| field | `t` | java.util.ArrayList |
| field | `u` | com.corrodinggames.rts.game.b.e |
| field | `v` | com.corrodinggames.rts.game.b.e |
| field | `w` | com.corrodinggames.rts.game.b.e |
| field | `x` | com.corrodinggames.rts.game.b.e |
| field | `y` | com.corrodinggames.rts.game.b.e |
| field | `z` | java.util.ArrayList |

### l (com.corrodinggames.rts.gameFramework.l) — 14 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| method | `C` | [('boolean', '')] |
| method | `D` | [('boolean', '')] |
| method | `F` | [('void', '')] |
| method | `aB` | [('boolean', '')] |
| method | `aD` | [('android.content.Context', '')] |
| method | `ar` | [('boolean', '')] |
| method | `as` | [('boolean', '')] |
| method | `au` | [('boolean', '')] |
| method | `aw` | [('boolean', '')] |
| method | `r` | [('java.lang.String', '')] |
| method | `s` | [('void', '')] |
| method | `t` | [('java.lang.String', '')] |
| method | `x` | [('void', '')] |
| method | `z` | [('int', '')] |

### d (com.corrodinggames.rts.game.units.custom.e.d) — 10 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `A` | com.corrodinggames.rts.game.units.custom.e.a |
| field | `B` | com.corrodinggames.rts.gameFramework.m.e |
| field | `C` | boolean |
| field | `u` | com.corrodinggames.rts.game.units.custom.bb |
| field | `v` | java.lang.String |
| field | `w` | com.corrodinggames.rts.game.units.custom.e.a |
| field | `x` | boolean |
| field | `y` | boolean |
| field | `z` | java.lang.String |
| method | `a` | [('void', 'com.corrodinggames.rts.game.units.custom.l, com.corrodinggames.rts.gameFramework.utility.ab, java.lang.String, java.lang.String'), ('void', 'com.corrodinggames.rts.game.units.custom.l')] |

### c (com.corrodinggames.rts.gameFramework.c) — 9 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `b` | java.util.ArrayList |
| field | `c` | java.util.ArrayList |
| field | `d` | java.util.ArrayList |
| field | `e` | int |
| method | `a` | [('void', 'java.lang.String'), ('void', ''), ('com.corrodinggames.rts.gameFramework.e', 'com.corrodinggames.rts.game.n')] |
| method | `b` | [('com.corrodinggames.rts.gameFramework.e', ''), ('com.corrodinggames.rts.gameFramework.e', 'com.corrodinggames.rts.game.n')] |
| method | `c` | [('void', '')] |
| method | `d` | [('void', '')] |
| method | `e` | [('void', '')] |

### as (com.corrodinggames.rts.gameFramework.j.as) — 9 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `e` | java.util.LinkedList |
| method | `a` | [('void', ''), ('com.corrodinggames.rts.gameFramework.j.au', 'int, int'), ('void', 'boolean'), ('void', 'int'), ('void', 'float'), ('void', 'double'), ('void', 'long'), ('void', 'java.lang.Integer'), ('void', 'com.corrodinggames.rts.game.units.custom.g'), ('void', 'com.corrodinggames.rts.gameFramework.w'), ('void', 'com.corrodinggames.rts.gameFramework.utility.m'), ('void', 'com.corrodinggames.rts.game.units.am'), ('void', 'com.corrodinggames.rts.game.units.y'), ('void', 'android.graphics.PointF'), ('void', 'java.lang.Enum'), ('void', 'com.corrodinggames.rts.game.units.as'), ('void', 'com.corrodinggames.rts.gameFramework.j.c'), ('void', 'com.corrodinggames.rts.game.n'), ('void', 'java.io.File'), ('void', 'com.corrodinggames.rts.gameFramework.j.k'), ('void', 'java.io.InputStream, int'), ('void', 'java.io.ByteArrayOutputStream'), ('void', 'byte[]'), ('void', 'short'), ('void', 'java.lang.String, boolean'), ('void', 'java.lang.String')] |
| method | `b` | [('void', ''), ('com.corrodinggames.rts.gameFramework.j.au', 'int'), ('void', 'java.lang.String'), ('void', 'com.corrodinggames.rts.gameFramework.w'), ('void', 'com.corrodinggames.rts.game.units.am'), ('void', 'byte[]')] |
| method | `c` | [('java.lang.String', ''), ('void', 'int'), ('void', 'java.lang.String')] |
| method | `d` | [('byte[]', ''), ('void', 'java.lang.String')] |
| method | `e` | [('void', ''), ('void', 'java.lang.String')] |
| method | `f` | [('boolean', '')] |
| method | `g` | [('int', '')] |
| method | `h` | [('void', '')] |

### com.corrodinggames.rts.java.Main (com.corrodinggames.rts.java.Main) — 9 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `m` | com.corrodinggames.rts.java.Main |
| method | `a` | [('void', 'java.lang.String'), ('void', 'java.lang.String[]'), ('void', 'float'), ('void', 'boolean'), ('boolean', 'com.corrodinggames.rts.gameFramework.j.c, java.lang.String, java.lang.String'), ('void', 'int, java.lang.String, java.lang.String, com.corrodinggames.rts.gameFramework.j.c'), ('void', 'com.corrodinggames.rts.gameFramework.j.c, java.lang.String, java.lang.String, boolean'), ('java.lang.String', 'com.corrodinggames.rts.gameFramework.j.c, java.lang.String'), ('void', 'com.corrodinggames.rts.gameFramework.j.ae')] |
| method | `b` | [('void', 'java.lang.String'), ('void', ''), ('void', 'com.corrodinggames.rts.gameFramework.j.c, java.lang.String, java.lang.String'), ('void', 'com.corrodinggames.rts.gameFramework.j.c, java.lang.String')] |
| method | `c` | [('void', ''), ('void', 'com.corrodinggames.rts.gameFramework.j.c, java.lang.String, java.lang.String')] |
| method | `d` | [('void', '')] |
| method | `f` | [('void', '')] |
| method | `g` | [('void', '')] |
| method | `h` | [('void', '')] |
| method | `i` | [('void', '')] |

### d (com.corrodinggames.rts.gameFramework.d) — 8 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `b` | long |
| field | `c` | float |
| field | `d` | float |
| field | `e` | float |
| field | `f` | float |
| field | `g` | int |
| field | `h` | com.corrodinggames.rts.game.units.ao |
| method | `a` | [('void', 'com.corrodinggames.rts.gameFramework.j.as'), ('void', 'com.corrodinggames.rts.gameFramework.j.k')] |

### aq (com.corrodinggames.rts.gameFramework.j.aq) — 8 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `c` | int |
| field | `d` | int |
| field | `e` | int |
| field | `g` | java.lang.String |
| field | `h` | java.lang.String |
| method | `a` | [('float', 'float, float, float'), ('void', 'java.lang.String, java.util.List'), ('void', 'com.corrodinggames.rts.gameFramework.j.c')] |
| method | `b` | [('void', 'java.lang.String, java.util.List')] |
| method | `c` | [('void', 'java.lang.String, java.util.List')] |

### g (com.corrodinggames.rts.gameFramework.j.g) — 8 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `A` | int |
| method | `a` | [('boolean', '')] |
| method | `b` | [('java.lang.String', '')] |
| method | `c` | [('java.lang.String', '')] |
| method | `d` | [('boolean', '')] |
| method | `e` | [('java.lang.String', '')] |
| method | `f` | [('java.lang.String', '')] |
| method | `g` | [('boolean', '')] |

### f (com.corrodinggames.rts.game.units.custom.e.f) — 7 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| method | `a` | [('com.corrodinggames.rts.game.units.custom.e.f', ''), ('double', 'com.corrodinggames.rts.game.units.custom.e.a'), ('void', 'com.corrodinggames.rts.game.units.custom.e.f'), ('void', 'com.corrodinggames.rts.game.units.custom.e.a, double'), ('void', 'double'), ('void', 'com.corrodinggames.rts.game.units.custom.d.b, double, double'), ('void', 'com.corrodinggames.rts.game.units.custom.d.b'), ('void', 'com.corrodinggames.rts.game.units.custom.e.f, double, double'), ('void', 'com.corrodinggames.rts.game.units.custom.e.f, double'), ('com.corrodinggames.rts.game.units.custom.e.f', 'com.corrodinggames.rts.game.units.custom.e.f, com.corrodinggames.rts.game.units.custom.e.f'), ('int', 'com.corrodinggames.rts.game.units.custom.e.f, com.corrodinggames.rts.game.units.am'), ('boolean', 'com.corrodinggames.rts.game.units.custom.e.f, com.corrodinggames.rts.game.units.am, double'), ('boolean', 'com.corrodinggames.rts.game.units.custom.e.f, com.corrodinggames.rts.game.units.am, com.corrodinggames.rts.game.units.am'), ('java.lang.String', 'boolean, boolean, int, boolean, boolean'), ('void', 'com.corrodinggames.rts.gameFramework.f.ae, boolean, boolean, int, boolean, boolean, com.corrodinggames.rts.game.units.am, int'), ('void', 'com.corrodinggames.rts.gameFramework.j.as'), ('void', 'com.corrodinggames.rts.gameFramework.j.k'), ('com.corrodinggames.rts.game.units.custom.e.f', 'com.corrodinggames.rts.game.units.am'), ('java.lang.String', 'com.corrodinggames.rts.game.units.am, java.lang.String, int, boolean')] |
| method | `b` | [('void', ''), ('double', 'com.corrodinggames.rts.game.units.custom.e.a'), ('void', 'com.corrodinggames.rts.game.units.custom.e.a, double'), ('void', 'com.corrodinggames.rts.game.units.custom.d.b, double, double'), ('void', 'com.corrodinggames.rts.game.units.custom.e.f'), ('void', 'com.corrodinggames.rts.game.units.custom.e.f, double, double'), ('com.corrodinggames.rts.game.units.custom.e.f', 'com.corrodinggames.rts.game.units.custom.e.f, com.corrodinggames.rts.game.units.custom.e.f'), ('com.corrodinggames.rts.game.units.custom.e.f', 'com.corrodinggames.rts.game.units.custom.e.f, double'), ('boolean', 'com.corrodinggames.rts.game.units.custom.e.f, com.corrodinggames.rts.game.units.am'), ('void', 'com.corrodinggames.rts.game.units.custom.e.f, com.corrodinggames.rts.game.units.am, double')] |
| method | `c` | [('boolean', ''), ('void', 'com.corrodinggames.rts.game.units.custom.e.a, double'), ('void', 'com.corrodinggames.rts.game.units.custom.e.f'), ('void', 'com.corrodinggames.rts.game.units.custom.e.f, com.corrodinggames.rts.game.units.am'), ('void', 'com.corrodinggames.rts.game.units.custom.e.f, com.corrodinggames.rts.game.units.am, double'), ('void', 'com.corrodinggames.rts.game.units.custom.e.a')] |
| method | `d` | [('void', 'com.corrodinggames.rts.game.units.custom.e.a, double'), ('com.corrodinggames.rts.game.units.custom.e.f', 'com.corrodinggames.rts.game.units.custom.e.f'), ('void', 'com.corrodinggames.rts.game.units.custom.e.f, com.corrodinggames.rts.game.units.am'), ('int', '')] |
| method | `e` | [('boolean', 'com.corrodinggames.rts.game.units.custom.e.f'), ('void', '')] |
| method | `f` | [('boolean', 'com.corrodinggames.rts.game.units.custom.e.f')] |
| method | `g` | [('void', 'com.corrodinggames.rts.game.units.custom.e.f')] |

### com.corrodinggames.rts.gameFramework.bb (com.corrodinggames.rts.gameFramework.bb) — 5 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `i` | java.util.concurrent.ConcurrentLinkedQueue |
| field | `j` | long |
| field | `k` | com.corrodinggames.rts.gameFramework.ba |
| method | `a` | [('void', 'com.corrodinggames.rts.gameFramework.bd'), ('void', '')] |
| method | `b` | [('void', '')] |

### n (com.corrodinggames.rts.gameFramework.j.n) — 5 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `g` | java.lang.String |
| method | `a` | [('void', 'java.lang.String'), ('void', 'java.util.List, boolean, com.corrodinggames.rts.gameFramework.j.s'), ('void', 'java.util.List, boolean, com.corrodinggames.rts.gameFramework.j.s, java.lang.String[]'), ('java.lang.String', 'java.util.List, java.lang.String'), ('java.io.BufferedReader', 'java.util.List'), ('java.io.BufferedReader', 'java.util.List, int'), ('java.io.BufferedReader', 'java.util.List, boolean, java.lang.String[], int, boolean'), ('com.corrodinggames.rts.gameFramework.j.t', 'java.util.List, java.lang.String, boolean'), ('java.lang.String', 'byte[]'), ('void', 'java.lang.Runnable'), ('void', 'int, int'), ('void', ''), ('void', 'java.util.List, java.lang.String, java.lang.String'), ('void', 'java.lang.String, java.lang.String'), ('java.lang.String', 'int'), ('void', 'com.corrodinggames.rts.gameFramework.j.w, java.lang.String, int, java.lang.String')] |
| method | `b` | [('com.corrodinggames.rts.gameFramework.j.g', 'java.lang.String'), ('void', 'java.util.List'), ('void', '')] |
| method | `c` | [('com.corrodinggames.rts.gameFramework.j.g', 'java.lang.String'), ('void', '')] |
| method | `d` | [('void', '')] |

### s (com.corrodinggames.rts.game.s) — 4 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| method | `a` | [('void', 'com.corrodinggames.rts.game.units.am'), ('void', 'com.corrodinggames.rts.game.units.d.l'), ('com.corrodinggames.rts.game.p', 'com.corrodinggames.rts.game.units.custom.g')] |
| method | `b` | [('void', 'com.corrodinggames.rts.game.units.am'), ('void', 'com.corrodinggames.rts.game.units.d.l')] |
| method | `c` | [('void', 'com.corrodinggames.rts.game.units.am')] |
| method | `d` | [('void', 'com.corrodinggames.rts.game.units.am')] |

### g (com.corrodinggames.rts.game.units.custom.g) — 4 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `d` | com.corrodinggames.rts.game.units.custom.h |
| method | `a` | [('com.corrodinggames.rts.game.units.custom.h', 'java.lang.String'), ('com.corrodinggames.rts.game.units.custom.h', 'java.lang.String, com.corrodinggames.rts.game.units.custom.h'), ('void', 'com.corrodinggames.rts.game.units.custom.h, com.corrodinggames.rts.gameFramework.j.as'), ('com.corrodinggames.rts.game.units.custom.h', 'com.corrodinggames.rts.gameFramework.j.k'), ('boolean', 'com.corrodinggames.rts.game.units.custom.h, com.corrodinggames.rts.game.units.custom.h'), ('boolean', 'com.corrodinggames.rts.game.units.custom.g, com.corrodinggames.rts.game.units.custom.h')] |
| method | `b` | [('com.corrodinggames.rts.game.units.custom.g', 'java.lang.String'), ('boolean', 'com.corrodinggames.rts.game.units.custom.h, com.corrodinggames.rts.game.units.custom.h')] |
| method | `c` | [('com.corrodinggames.rts.game.units.custom.g', 'java.lang.String')] |

### n (com.corrodinggames.rts.game.units.n) — 3 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `g` | com.corrodinggames.rts.game.units.n[] |
| method | `a` | [('boolean', 'com.corrodinggames.rts.game.units.as'), ('java.lang.String', ''), ('com.corrodinggames.rts.game.units.n', 'boolean'), ('com.corrodinggames.rts.game.units.n', 'int, int')] |
| method | `b` | [('boolean', '')] |

### o (com.corrodinggames.rts.game.units.o) — 3 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `f` | com.corrodinggames.rts.game.units.o[] |
| method | `a` | [('boolean', 'com.corrodinggames.rts.game.units.as'), ('java.lang.String', ''), ('com.corrodinggames.rts.game.units.o', 'boolean'), ('com.corrodinggames.rts.game.units.o', 'int, int')] |
| method | `b` | [('boolean', '')] |

### com.corrodinggames.rts.gameFramework.br (com.corrodinggames.rts.gameFramework.br) — 3 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| method | `a` | [('long', ''), ('float', 'long'), ('double', 'long, long'), ('void', 'java.lang.String, long'), ('void', 'com.corrodinggames.rts.gameFramework.bs'), ('java.lang.String', 'double'), ('void', 'boolean, boolean')] |
| method | `b` | [('void', 'com.corrodinggames.rts.gameFramework.bs'), ('java.lang.String', 'double'), ('void', '')] |
| method | `c` | [('void', '')] |

### at (com.corrodinggames.rts.gameFramework.at) — 3 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `e` | com.corrodinggames.rts.gameFramework.at[] |
| method | `c` | [('void', '')] |
| method | `d` | [('java.lang.String', '')] |

### y (com.corrodinggames.rts.gameFramework.y) — 3 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| method | `a` | [('java.io.File', 'java.lang.String, boolean'), ('java.io.File', 'java.lang.String, java.lang.String, boolean'), ('void', 'com.corrodinggames.rts.gameFramework.j.as'), ('java.lang.String', 'java.lang.String'), ('void', 'java.lang.String, com.corrodinggames.rts.gameFramework.j.as'), ('boolean', 'com.corrodinggames.rts.gameFramework.j.k, boolean, boolean, boolean'), ('void', 'boolean'), ('boolean', '')] |
| method | `b` | [('void', 'java.lang.String, boolean'), ('boolean', 'java.lang.String'), ('void', '')] |
| method | `c` | [('boolean', 'java.lang.String, boolean'), ('void', '')] |

### ah (com.corrodinggames.rts.gameFramework.j.ah) — 3 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| method | `a` | [('void', ''), ('void', 'com.corrodinggames.rts.gameFramework.j.as'), ('void', 'com.corrodinggames.rts.gameFramework.j.k')] |
| method | `b` | [('java.lang.String', '')] |
| method | `c` | [('com.corrodinggames.rts.gameFramework.j.ah', '')] |

### com.corrodinggames.rts.game.b.g (com.corrodinggames.rts.game.b.g) — 2 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `n` | android.graphics.Rect |
| method | `a` | [('boolean', 'com.corrodinggames.rts.game.b.g, com.corrodinggames.rts.game.b.g'), ('com.corrodinggames.rts.game.b.g', ''), ('void', 'java.lang.String'), ('com.corrodinggames.rts.game.b.g', 'com.corrodinggames.rts.game.b.b, com.corrodinggames.rts.game.b.e, com.corrodinggames.rts.game.b.j, int, short, short, boolean'), ('void', 'com.corrodinggames.rts.gameFramework.m.y, android.graphics.RectF, float, android.graphics.Paint')] |

### r (com.corrodinggames.rts.game.units.r) — 2 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| method | `a` | [('java.lang.String', '')] |
| method | `b` | [('java.lang.String', '')] |

### bb (com.corrodinggames.rts.game.units.custom.bb) — 2 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| method | `a` | [('com.corrodinggames.rts.game.units.custom.bb', 'java.lang.String'), ('boolean', ''), ('void', 'java.lang.String, java.lang.String')] |
| method | `b` | [('com.corrodinggames.rts.game.units.custom.bb', 'java.lang.String'), ('java.lang.String', '')] |

### com.corrodinggames.rts.gameFramework.bo (com.corrodinggames.rts.gameFramework.bo) — 2 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| field | `m` | byte |
| method | `a` | [('void', 'com.corrodinggames.rts.gameFramework.j.as'), ('void', 'com.corrodinggames.rts.gameFramework.j.k')] |

### ak (com.corrodinggames.rts.gameFramework.j.ak) — 2 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| method | `a` | [('void', '')] |
| method | `b` | [('void', '')] |

### com.corrodinggames.rts.game.units.d.j (com.corrodinggames.rts.game.units.d.j) — 1 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| method | `a` | [('void', 'com.corrodinggames.rts.gameFramework.j.as'), ('void', 'com.corrodinggames.rts.gameFramework.j.k')] |

### a (com.corrodinggames.rts.gameFramework.a) — 1 unmapped

| Type | Bytecode Name | Signature/Type |
|------|--------------|---------------|
| method | `a` | [('com.corrodinggames.rts.gameFramework.b', 'byte')] |
