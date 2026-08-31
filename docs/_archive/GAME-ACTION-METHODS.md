# GameAction Method Mapping: Decompiled (single-char) to Deobfuscated
> ⚠️ 命名时点: 2026-06 (混淆名 era) — 文中类名为 02 混淆名; 03 侧已语义化, 对应关系查 [mappings/class-discoveries.csv](../../mappings/class-discoveries.csv)


## Class File Mappings

| Decompiled | Deobfuscated | Description |
|-----------|--------------|-------------|
| `a.java` | `UnitActionBase.java` | Unit action base (wrapper/default behavior) |
| `b.java` | `ActionFilter.java` | Action availability filter |
| `c.java` | `ActionId.java` | Action ID (string-keyed singleton) |
| `d.java` | `AttackAction.java` | Attack mode toggle action |
| `e.java` | `AttackMoveAction.java` | Attack-move command action |
| `f.java` | `GuardAction.java` | Guard unit action |
| `g.java` | `BuildAction.java` | Build action (wraps UnitBuildAction + target unit type) |
| `h.java` | `ActionWrapper.java` | Wrapper action with filter/visibility constraints |
| `i.java` | `PatrolAction.java` | Patrol action |
| `j.java` | `PingAction.java` | Map ping action |
| `k.java` | `PingType.java` | Enum of ping types (11 values) |
| `l.java` | `UnitBuildAction.java` | Unit build action (strictfp, extends AbstractBuildAction) |
| `m.java` | `ReclaimAction.java` | Reclaim resource action |
| `n.java` | `RepairAction.java` | Repair unit action |
| `o.java` | `RallyPointAction.java` | Set rally point action |
| `p.java` | `AbstractCutsceneAction.java` | Abstract base for cutscene-type actions |
| `q.java` | `TeamChatAction.java` | Send team chat action |
| `r.java` | `MapPingAction.java` | Send map ping action |
| `s.java` | `GameAction.java` | **Abstract base class** for all game actions |
| `t.java` | `ActionTargetType.java` | Enum: target type (9 values) |
| `u.java` | `ActionCategory.java` | Enum: action category (13 values) |
| `v.java` | `BuildQueueAction.java` | Build queue action (production entry) |
| `w.java` | `AbstractBuildAction.java` | Abstract base for build actions |
| `x.java` | `AbstractImmediateAction.java` | Abstract base for immediate actions |
| `y.java` | `StopAction.java` | Stop action |
| `z.java` | `SellAction.java` | Sell action |

---

## Enum Mappings

### u.java -> ActionCategory.java (13 values)

| Original | Suggested Name | Used By |
|----------|---------------|---------|
| `u.a` | `RESOURCE_COLLECTION` | AbstractImmediateAction (x) |
| `u.b` | `BUILD_QUEUE` | BuildQueueAction (v) |
| `u.c` | `BUILD` | AbstractBuildAction (w), UnitBuildAction (l) |
| `u.d` | `COMMAND` | RallyPointAction (o) |
| `u.e` | `RECLAIM` | ReclaimAction (m) |
| `u.f` | `REPAIR` | RepairAction (n) |
| `u.g` | *(unused in decompiled sources)* | - |
| `u.h` | `ATTACK_MOVE` | AttackMoveAction (e) |
| `u.i` | `MISC_UI` | StopAction (y), SellAction (z), AbstractCutsceneAction (p) |
| `u.j` | `PING` | PingAction (j) |
| `u.k` | `ATTACK_MODE` | AttackAction (d) |
| `u.l` | `GUARD` | GuardAction (f) |
| `u.m` | `PATROL` | PatrolAction (i) |

### t.java -> ActionTargetType.java (9 values)

| Original | Suggested Name | Used By |
|----------|---------------|---------|
| `t.a` | `NONE` | AttackAction, AttackMoveAction, GuardAction, PatrolAction, PingAction, AbstractImmediateAction |
| `t.b` | `LOCATION` | RallyPointAction (f() returns t.b) |
| `t.c` | *(check in j())* | Used for texture lookup in GameAction.j() |
| `t.d` | `BUILD` | UnitBuildAction (l), AbstractBuildAction (w) |
| `t.e` | `BUILD_QUEUE` | BuildQueueAction (v) |
| `t.f` | `STRUCTURE` | ReclaimAction, RepairAction |
| `t.g` | `STOP` | StopAction, SellAction, AbstractCutsceneAction |
| `t.h` | `SELL` | SellAction (conditional: l.at() && !g.bO) |
| `t.i` | `INVISIBLE` | Used as sentinel in a() rendering check |

---

## Method Mappings by Class

### GameAction (s.java) - Abstract Base Class (all subclasses share these)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `s.g` | `float` | `sortPriority` | Sort priority (-999 = unset, -100 for RallyPoint) |
| `s.h` | `a` (UnitActionBase) | `actionModifier` | Behavior modifier wrapper |
| `s.a` (private) | `c` (ActionId) | `actionId` | Unique action identifier |
| `s.b` (private) | `b` (ResourceComponent) | `cachedResourceComponent` | Cached resource cost icon |
| `m_()` | `float` | `getSortPriority()` | Compute effective sort priority |
| `a(s)` | `int` | `compareTo(GameAction)` | Sort by priority ascending |
| `b(c)` | `boolean` | `isNullOrInvalid(ActionId)` | Static: check null or "-1" id |
| `c(c)` | `boolean` | `isValidActionId(ActionId)` | Static: inverse of b(c) |
| `a(s, s)` | `boolean` | `areSameAction(GameAction, GameAction)` | Static: reference equality |
| `d(c)` | `boolean` | `matchesActionId(ActionId)` | Check if actionId == given id |
| `a(String)` | `void` | `setActionId(String)` | Set action ID from string |
| `e(c)` | `void` | `setActionId(ActionId)` | Set action ID from ActionId object |
| `N()` | `c` (ActionId) | `getActionId()` | Get current action ID |
| `z()` | `c` (ActionId) | `getActionIdAlias()` | Alias for N() |
| `O()` | `String` | `getActionIdString()` | Get ID string representation |
| `b()` | `String` | `getLabel()` | **Abstract**: action button label text |
| `a()` | `String` | `getDescription()` | **Abstract**: tooltip description |
| `P()` | `h` (KeyBinding) | `getKeyBinding()` | Get associated key binding or null |
| `d(am)` | `String` | `getLabelForUnit(UnitInstance)` | Get label for specific unit |
| `e(am)` | `String` | `getDescriptionForUnit(UnitInstance)` | Get description for specific unit |
| `c()` | `int` | `getResourceCost()` | **Abstract**: metal/cost amount |
| `B()` | `b` (ResourceComponent) | `getResourceComponent()` | Get resource cost/icon component |
| `r_()` | `b` (ResourceComponent) | `getSecondaryResourceComponent()` | Get secondary resource component |
| `b(am, boolean)` | `int` | `getBuildProgress(UnitInstance, boolean)` | **Abstract**: build progress (0-100 or -1) |
| `n_()` | `boolean` | `isBuildable()` | Whether action can be built/bought |
| `g(am)` | `boolean` | `isFilterBlocked(UnitInstance)` | Check if actionModifier blocks action |
| `j(am)` | `String` | `getFilterBlockReason(UnitInstance)` | Reason action is blocked |
| `a(am, am)` | `void` | `executeOnFilter(UnitInstance, UnitInstance)` | Execute through actionModifier |
| `d(am, boolean)` | `boolean` | `checkGameRules(UnitInstance, boolean)` | Check map/game rule restrictions |
| `k(am)` | `boolean` | `isAvailableForUnit(UnitInstance)` | Check availability for unit |
| `l(am)` | `boolean` | `isAvailableForOther(UnitInstance)` | Alternate availability check |
| `a(am, boolean)` | `boolean` | `canExecute(UnitInstance, boolean)` | Main execution feasibility check |
| `r(am)` | `boolean` | `canShowAction(UnitInstance)` | UI visibility: should show in panel |
| `u(am)` | `boolean` | `isAffordable(UnitInstance)` | Check affordability |
| `b(am)` | `boolean` | `isAffordableAction(UnitInstance)` | Check affordability via modifier |
| `a(am, n)` | `boolean` | `canTargetLocation(UnitInstance, PlayerState)` | Can target a specific location |
| `u()` | `boolean` | `isQueueable()` | Can be queued (shift-click) |
| `h()` | `boolean` | `isToggleable()` | Is a toggle action |
| `C()` | `boolean` | `isPassiveAction()` | Passive/background action |
| `D()` | `boolean` | `isActiveAction()` | Active/interactive action |
| `A()` | `boolean` | `requiresTargeting()` | Requires explicit target selection |
| `i()` | `as` (UnitTypeHandle) | `getUnitType()` | **Abstract**: associated unit type |
| `y()` | `as` (UnitTypeHandle) | `getAssociatedUnitType()` | Secondary unit type or null |
| `E()` | `as` (UnitTypeHandle) | `getBuildUnitType()` | Unit type this builds |
| `F()` | `boolean` | `isBuildActionType()` | Is this a build action |
| `t()` | `int` | `getMaxQueueCount()` | Max times this can be queued |
| `g()` | `boolean` | `isInstantAction()` | **Abstract**: executes instantly |
| `e()` | `u` (ActionCategory) | `getActionCategory()` | **Abstract**: action category enum |
| `o()` | `boolean` | `isRepeatingAction()` | Is a repeating action |
| `f()` | `t` (ActionTargetType) | `getActionTargetType()` | **Abstract**: target type enum |
| `m(am)` | `boolean` | `checkUnitTypeFilter(UnitInstance)` | Check unit type match |
| `n(am)` | `boolean` | `checkUnitTypeFilterFor(UnitInstance)` | Alternate type check |
| `v(am)` | `e` (Texture) | `getTexture(UnitInstance)` | Get action icon texture |
| `d()` | `String` | `getDisplayString()` | Full display string (with counts) |
| `h_()` | `boolean` | `showActionInUI()` | Whether to show in UI |
| `w(am)` | `String` | `getLabelForUnitTarget(UnitInstance)` | Label when targeting a unit |
| `a(am, ae, Paint, Paint)` | `void` | `renderActionUI(...)` | Render action in UI panel |
| `a(am, ae)` | `void` | `renderActionDetails(...)` | Render detail info (costs etc) |
| `c(am, boolean)` | `boolean` | `executeAction(UnitInstance, boolean)` | Execute the action |
| `f(am)` | `void` | `onActionExecuted(UnitInstance)` | Post-execution hook |
| `j()` | `e` (Texture) | `getTextureForAction()` | Get default action texture |
| `h(am)` | `e` (Texture) | `getSpecificTexture(UnitInstance)` | Get unit-specific texture |
| `J()` | `int` | `getColor()` | ARGB color for UI rendering |
| `v()` | `Rect` | `getIconRect()` | Icon rectangle coordinates |
| `i(am)` | `am` (UnitInstance) | `getTargetUnit(UnitInstance)` | Get target unit |
| `s(am)` | `boolean` | `checkAvailable(UnitInstance)` | Is available for unit |
| `t(am)` | `boolean` | `checkAvailableForAction(UnitInstance)` | Is available for another action |
| `a(am)` | `boolean` | `isAffordableSingle(UnitInstance)` | Single-param affordability |
| `s()` | `boolean` | `isAvailableInCurrentGameState()` | Available in editor/cutscene state |
| `o(am)` | `boolean` | `isVisibleForUnit(UnitInstance)` | Is visible for unit |
| `G()` | `boolean` | `isAggregateAction()` | Aggregates sub-actions |
| `c(am)` | `void` | `executeForSelected(UnitInstance)` | Execute for currently selected unit |
| `l()` | `float` | `getActionScale()` | Render scale multiplier |
| `m()` | `int` | `getMaxChargeUses()` | Max uses for charged actions |
| `H()` | `boolean` | `isChargedAction()` | Is a charged action |
| `I()` | `boolean` | `isChargedActionFor()` | Charged action variant |
| `x()` | `boolean` | `isToggleableActionWrapper()` | Wrapper for toggleable action |
| `p(am)` | `float` | `getBuildProgressForUnit(UnitInstance)` | Build progress ratio (0-1) |
| `q(am)` | `ArrayList` | `getQueuedActions(UnitInstance)` | Get queued sub-actions |
| `M()` | `ad` (KeyBinding) | `getKeyBindingRef()` | Key binding reference (cutscene) |
| `o_()` | `boolean` | `isAlwaysVisible()` | Always visible in panel |
| `Q()` | `boolean` | `hasSubActions()` | Has sub-action menu |
| `a(y y2)` | `void` | `onSelectedUnitsChange(UnitType)` | Handle unit type selection change |
| `a(float, float)` | `boolean` | `handleTouchInput(float, float)` | Handle touch input coordinates |
| `p()` | `boolean` | `isContextSensitive()` | Changes based on context |

---

### AttackAction (d.java -> AttackAction.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `d.a` | `int` | `lastUpdateTick` | Game tick of last attack mode sync |
| `d.b` | `a` (AttackMode) | `currentMode` | Current attack mode (HoldFire/ReturnFire/etc) |
| `n()` | `ar` (UnitTypeHandle) | `getUnitType()` | Always returns null |
| `q()` | `a` (AttackMode) | `getCurrentAttackMode()` | Get current mode for display |
| `r()` | `a` (AttackMode) | `getAttackModeFromSelection()` | Derive mode from selected units |
| `a(a)` | `a` (AttackMode) | `cycleAttackMode(AttackMode)` | Cycle to next attack mode |
| `b(am)` | `boolean` | `isAffordableAction(UnitInstance)` | Always returns true |
| `s()` | `boolean` | `isAvailableInCurrentGameState()` | Always returns true |

---

### AttackMoveAction (e.java -> AttackMoveAction.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `n()` | `ar` (UnitTypeHandle) | `getUnitType()` | Always returns null |

---

### GuardAction (f.java -> GuardAction.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `n()` | `ar` (UnitTypeHandle) | `getUnitType()` | Always returns null |
| `l()` | `float` | `getActionScale()` | 0.6 (old) / 0.5 (new) UI scale |
| `o_()` | `boolean` | `isAlwaysVisible()` | Always visible |

---

### BuildAction (g.java -> BuildAction.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `g.a` | `s` (GameAction) | `buildTargetPos` | Wrapped UnitBuildAction |
| `g.b` | `y` (UnitType) | `buildUnitType` | The unit type to build |
| `g.c` | `b` (ActionFilter) | `buildStages` | Build stage filter |
| `g.d` | `u` (ArrayList) | `savedSelectedUnitsCache` | Saved selection during rendering |
| `g.e` | `u` (ArrayList) | `singleUnitSelection` | Temp selection with only build type |
| `K()` | `void` | `saveSelectionCache()` | Save and replace selection |
| `L()` | `void` | `restoreSelectionCache()` | Restore original selection |
| `p_()` | `s` (GameAction) | `getWrappedAction()` | Get inner UnitBuildAction |

---

### ActionWrapper (h.java -> ActionWrapper.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `h.a` | `s` (GameAction) | `wrappedAction` | The wrapped inner action |
| `h.b` | `b` (ActionFilter) | `actionPriority` | Availability filter |
| `h.c` | `boolean` | `actionCooldown` | Apply cooldown restriction |
| `h.d` | `int` | `wrappedActionCount` | Usage counter |
| `h.e` | `boolean` | `actionActive` | Is the action active |
| `h.f` | `int` | `modColor` | ARGB color for mod label |
| `q_()` | `s` (GameAction) | `getWrappedAction()` | Get inner action |

---

### PatrolAction (i.java -> PatrolAction.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `w()` | `ar` (UnitTypeHandle) | `getUnitType()` | Always returns null |
| `s()` | `boolean` | `isAvailableInCurrentGameState()` | Always visible |

---

### PingAction (j.java -> PingAction.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `j.a` | `k` (PingType) | `pingType` | Type of ping |
| `j.b` | `ArrayList` | `allPingActions` | Static: all ping action instances |
| `j.c` | `Rect` | `iconRect` | Static: reusable icon rect |
| `K()` | `String` | `getPingTypeKey()` | Localization key for ping type |
| `a(c)` | `j` (PingAction) | `findByActionId(ActionId)` | Static: lookup ping by ID |
| `w()` | `ar` (UnitTypeHandle) | `getUnitType()` | Always returns null |

---

### PingType (k.java -> PingType.java) - Enum

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `a()` | `String` | `getPingSuffix()` | " - ping_type_name" |
| `b()` | `String` | `getLocalizedName()` | Localized ping type name |
| `c()` | `String` | `getLocalizationKey()` | "menus.ingame.ping.type.<name>" |

---

### UnitBuildAction (l.java -> UnitBuildAction.java) extends AbstractBuildAction

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `l.a` | `as` (UnitTypeHandle) | `unitType` | Unit type to build |

---

### ReclaimAction (m.java -> ReclaimAction.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `m.a` | `boolean` | `isBuildingReclaim` | True = reclaim building, false = reclaim wreckage |
| `K()` | `ar` (UnitTypeHandle) | `getUnitType()` | Always returns null |
| `o(am)` | `boolean` | `isVisibleForUnit(UnitInstance)` | Shows only for builder units (when building reclaim) |

---

### RepairAction (n.java -> RepairAction.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `K()` | `ar` (UnitTypeHandle) | `getUnitType()` | Always returns null |

---

### RallyPointAction (o.java -> RallyPointAction.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `K()` | `ar` (UnitTypeHandle) | `getUnitType()` | Always returns null |

---

### AbstractCutsceneAction (p.java -> AbstractCutsceneAction.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `K()` | `y` (UnitType) | `getFirstSelectedUnitType()` | First selected unit type |
| `L()` | `boolean` | `isEditorOrSpectator()` | True if editor or spectating |

---

### TeamChatAction (q.java -> TeamChatAction.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `c(am, boolean)` | `boolean` | `executeAction(UnitInstance, boolean)` | Opens team chat dialog |
| `M()` | `ad` (KeyBinding) | `getKeyBindingRef()` | Key binding reference |

---

### MapPingAction (r.java -> MapPingAction.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `c(am, boolean)` | `boolean` | `executeAction(UnitInstance, boolean)` | Triggers map ping mode |
| `M()` | `ad` (KeyBinding) | `getKeyBindingRef()` | Key binding reference |

---

### BuildQueueAction (v.java -> BuildQueueAction.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `v.a` | `as` (UnitTypeHandle) | `unitType` | Unit type in queue |
| `v.b` | `int` | `queueCount` | Number of queued (1/2/3 for T1/T2/T3) |
| `p(am)` | `float` | `getBuildProgressForUnit(UnitInstance)` | Build progress of first matching factory |

---

### AbstractBuildAction (w.java -> AbstractBuildAction.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `b(am, boolean)` | `int` | `getBuildProgress(UnitInstance, boolean)` | Delegates to BuilderUnit.a(actionId, boolean) |
| `p(am)` | `float` | `getBuildProgressForUnit(UnitInstance)` | Build progress ratio from CarrierUnit |
| `K()` | `float` | `getBuildSortPriority()` | Returns 0.01f (sorts below others) |
| `u()` | `boolean` | `isQueueable()` | Always true |

---

### AbstractImmediateAction (x.java -> AbstractImmediateAction.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `K()` | `ar` (UnitTypeHandle) | `getUnitType()` | Always returns null |

---

### StopAction (y.java -> StopAction.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `y.a` | `boolean` | `isAreaStop` | True = stop all, false = stop single unit info |
| `K()` | `y` (UnitType) | `getFirstSelectedUnit()` | First selected unit instance |
| `L()` | `boolean` | `isEditorOrSpectator()` | True if editor or spectating |

---

### SellAction (z.java -> SellAction.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `z.a` | `as` (UnitTypeHandle) | `unitType` | Unit type to sell |
| `z.b` | `ArrayList` | `queueList` | List of queued sell actions |
| `z.c` | `int` | `unitCount` | Count of units of this type selected |
| `z.d` | `boolean` | `hasMixedTypes` | Multiple different types selected |
| `z.e` | `y` (UnitType) | `firstUnit` | First unit of this type found |
| `z.f` | `int` | `lastUpdateTick` | Tick of last count update |
| `c(am, boolean)` | `boolean` | `executeAction(UnitInstance, boolean)` | Deselects all units of this type |
| `K()` | `void` | `refreshSelectionCount()` | Recount selected units of this type |

---

## Enum Value Details

### PingType (k.java) - 11 values

| Original | Suggested Name | Localization Key |
|----------|---------------|------------------|
| `a` | `ATTACK` | menus.ingame.ping.type.a |
| `b` | `DEFEND` | menus.ingame.ping.type.b |
| `c` | `BUILD` | menus.ingame.ping.type.c |
| `d` | `MOVE` | menus.ingame.ping.type.d |
| `e` | `RESOURCE` | menus.ingame.ping.type.e |
| `f` | `ENEMY` | menus.ingame.ping.type.f |
| `g` | `NEUTRAL` | menus.ingame.ping.type.g |
| `h` | `CUSTOM` | menus.ingame.ping.type.h |
| `i` | `ATTENTION` | menus.ingame.ping.type.i |
| `j` | `WARNING` | menus.ingame.ping.type.j |
| `k` | `INFO` | menus.ingame.ping.type.k |

---

## UnitActionBase (a.java -> UnitActionBase.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `a(am)` | `boolean` | `isAffordable(UnitInstance)` | Check if unit can afford (default: false) |
| `b(am)` | `boolean` | `isVisible(UnitInstance)` | Is visible for unit (default: false) |
| `c(am)` | `String` | `getDisabledReason(UnitInstance)` | Reason action is disabled |
| `a(am, boolean)` | `boolean` | `canExecute(UnitInstance, boolean)` | Can execute (default: true) |
| `d(am)` | `boolean` | `isBlocked(UnitInstance)` | Is blocked by filter (default: false) |
| `a()` | `b` (ResourceComponent) | `getResourceComponent()` | Get cost override (default: null) |
| `b()` | `b` (ResourceComponent) | `getSecondaryResourceComponent()` | Get secondary cost override (default: null) |
| `a(am, am)` | `void` | `execute(UnitInstance, UnitInstance)` | Execute through filter |

## ActionFilter (b.java -> ActionFilter.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `isAvailable(s, am)` | `boolean` | `isAvailable(GameAction, UnitInstance)` | Check availability (default: always true) |

## ActionId (c.java -> ActionId.java)

| Method | Return | Suggested Name | Brief Description |
|--------|--------|----------------|-------------------|
| `a(String)` | `c` (ActionId) | `getOrCreate(String)` | Static: get or create ID for string |
| `a()` | `String` | `getIdString()` | Get string value |
| `a(as, c)` | `void` | `writeToStream(OutputNetStream, ActionId)` | Static: serialize ActionId |
| `a(k)` | `c` (ActionId) | `readFromStream(InputNetStream)` | Static: deserialize ActionId |
| `a(c)` | `boolean` | `equals(ActionId)` | Check identity equality |
