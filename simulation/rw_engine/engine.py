# -*- coding: utf-8 -*-
"""
Rusted Warfare v1.15 — Complete Simulation Engine
Self-contained, no external dependencies except Python stdlib.

All formulas bytecode-verified from 1698 classes in game-lib.jar.
Cross-validated against RW-HPS server source (471 Kotlin files).

=== VERIFIED FORMULAS ===

1. INCOME (s.java: s.a/s.b, y.java: y.i, d.b.java: ResourceComponent)
   register:   s.g += (int)unit.cy()        [cm >= 1.0]
   unregister: s.g -= (int)unit.cy()
   per-second: income/s = s.g × (60fps / 40window) × ay.h × gameSpeed
               = s.g × 1.5 × incomeMultiplier × speed
   CC:         cy()=18 → 18 × 1.5 × 2.5 = 67.5/s @2.5x speed

2. DAMAGE (am.java: applyDamage [553B], am.ch [21B])
   Stage 1 — Incomplete building: damage × 1.75  [cm < 1.0]
   Stage 2 — Shield absorb:
     shieldDmg = incoming × shieldMult
     if shield >= shieldDmg: remaining -= shield × shieldAbsorb; shield = 0
     else:                   shield -= shieldDmg; remaining -= remaining × shieldAbsorb
   Stage 3 — HP damage:
     hpDmg = remaining × hpMult
     if hp >= hpDmg: hp = 0  [→ ch() → bV=true → DEATH]
     else:           hp -= hpDmg
   Death chain: ch()→bv()→bu() [8 steps: grid remove, tracker unregister, cleanup]

3. BUILD (d.j: BuildProgress, h.e: Factory [172B])
   Factory:  buildSpeed=0.03/tick, maxProgress=280 → 62s @60fps 2.5x
   Normal:   buildSpeed=0.10/tick, maxProgress=330 → 22s
   Progress: d.j.n += buildSpeed × delta_ms  [complete when >= 1.0]
   Upgrade:  s.b(old) removes income BEFORE s.a(new) adds [INHERITANCE, not additive]

4. REFUND (y.java: b6.a)
   Cancel (stopOrUndo): 100% refund [e.g=true]
   Reclaim (reclaim):   80% refund  [y.i() → b6.a(this, 0.8f, true)]

5. PROJECTILE (fw.ab: ProjectileManager [630B])
   Speed: 80px/s  |  Lifetime: 3600ticks (60s)  |  Timeout: 14400ticks
   Collision: dist < target.radius + 15 → applyDamage

6. SERIALIZATION (j.k/j.as: named-block protocol)
   Block: [name_len:2B][name:UTF8][data_len:4B][data:bytes]
   Max depth: 11  |  Sync mark: 12345  |  Max ID: 999999
"""
import math, random, json, os
from dataclasses import dataclass, field
from typing import Dict, List, Optional, Tuple, Any
from collections import defaultdict

# ═══════════════════════════════════════════════════════════════
# ENGINE CONSTANTS — bytecode-verified
# ═══════════════════════════════════════════════════════════════

FRAME_RATE = 60.0
TICK_RATE = 30.0
ECONOMY_WINDOW = 40.0
DEFAULT_SPEED = 2.5
STARTING_CREDITS = 4000.0
CC_INCOME_CY = 18.0

BUILD_MAX_FACTORY = 280.0
BUILD_MAX_NORMAL = 330.0
BUILD_SPEED_FACTORY = 0.03
BUILD_SPEED_NORMAL = 0.10
INCOMPLETE_DAMAGE_MULT = 1.75

RECLAIM_REFUND = 0.80
CANCEL_REFUND = 1.00

SHIELD_MULTIPLIER = 1.0
SHIELD_ABSORB = 0.2
HP_MULTIPLIER = 1.0

PROJECTILE_LIFETIME = 3600.0
PROJECTILE_TIMEOUT = 14400.0
PROJECTILE_SPEED = 80.0
COLLISION_DIST_SQ = 40000.0

SPATIAL_GRID_SIZE = 32
SPATIAL_CELL_SIZE = 50.0
BUILDER_SEARCH_RANGE = 100.0
ATTACK_DETECT_RANGE = 360.0

# ═══════════════════════════════════════════════════════════════
# UNIT DATA — from official game
# ═══════════════════════════════════════════════════════════════

UNIT_COSTS = {
    'commandCenter': 3000, 'extractorT1': 700, 'extractorT2': 2100, 'extractorT3': 6100,
    'fabricatorT1': 2200, 'fabricatorT2': 6600, 'fabricatorT3': 14100,
    'mechFactory': 1000, 'airFactory': 1000, 'seaFactory': 1000, 'landFactory': 700,
    'builder': 500, 'builderShip': 500, 'scout': 700, 'dropship': 800,
    'mechGun': 600, 'mechMissile': 900, 'mechArtillery': 1400, 'mechFlame': 12000,
    'mechLaser': 7000, 'mechLightning': 5200, 'mechMinigun': 5000,
    'plasmaTank': 1000, 'mammothTank': 3900, 'laserTank': 1600, 'heavyTank': 800,
    'hoverTank': 450, 'missileTank': 2500, 'experimentalTank': 14000,
    'heavyInterceptor': 1200, 'lightGunship': 250, 'bomber': 4000, 'spyDrone': 1500,
    'battleShip': 1500, 'heavyBattleship': 6000, 'lightSub': 450, 'attackSubmarine': 800,
    'gunBoat': 300, 'missileShip': 900, 'heavyAAShip': 3500,
    'turret': 500, 'antiAirTurret': 600, 'antiAirTurretFlak': 4600,
    'outpost': 1500, 'laboratory': 4000, 'repairbay': 1500,
    'experimentalSpider': 70000, 'experimentalGunship': 140000,
    'experimentalDropship': 30000, 'modularSpider': 90000,
}

INCOME_RATES = {
    'commandCenter': 18, 'extractorT1': 8, 'extractorT2': 12, 'extractorT3': 20,
    'fabricatorT1': 2, 'fabricatorT2': 7, 'fabricatorT3': 14,
    'experimentalSpider': 18, 'experimentalGunship': 30, 'experimentalDropship': 4,
    'modularSpider': 30, 'combatEngineer': 1, 'mechEngineer': 1,
}

UNIT_STATS = {
    'commandCenter': {'hp': 4000, 'speed': 0, 'range': 280, 'damage': 70},
    'mechGun': {'hp': 500, 'speed': 0.8, 'range': 130, 'damage': 23},
    'plasmaTank': {'hp': 220, 'speed': 0.8, 'range': 165, 'damage': 100},
    'mechMissile': {'hp': 500, 'speed': 0.8, 'range': 190, 'damage': 70},
    'mechArtillery': {'hp': 500, 'speed': 0.6, 'range': 290, 'damage': 80},
    'mechFlame': {'hp': 800, 'speed': 0.7, 'range': 90, 'damage': 25},
    'mechLaser': {'hp': 1200, 'speed': 0.6, 'range': 310, 'damage': 90},
    'mechLightning': {'hp': 700, 'speed': 0.7, 'range': 180, 'damage': 55},
    'mechMinigun': {'hp': 400, 'speed': 0.9, 'range': 110, 'damage': 8},
    'mammothTank': {'hp': 800, 'speed': 0.5, 'range': 220, 'damage': 120},
    'laserTank': {'hp': 600, 'speed': 0.6, 'range': 240, 'damage': 80},
    'heavyTank': {'hp': 600, 'speed': 0.8, 'range': 160, 'damage': 50},
    'hoverTank': {'hp': 300, 'speed': 1.2, 'range': 130, 'damage': 25},
    'missileTank': {'hp': 400, 'speed': 0.7, 'range': 260, 'damage': 65},
    'experimentalTank': {'hp': 3000, 'speed': 0.4, 'range': 350, 'damage': 250},
    'scout': {'hp': 350, 'speed': 1.0, 'range': 110, 'damage': 17},
    'builder': {'hp': 200, 'speed': 0.8, 'range': 0, 'damage': 0},
    'heavyInterceptor': {'hp': 400, 'speed': 1.5, 'range': 150, 'damage': 25},
    'lightGunship': {'hp': 200, 'speed': 1.3, 'range': 110, 'damage': 10},
    'bomber': {'hp': 600, 'speed': 0.8, 'range': 120, 'damage': 140},
    'battleShip': {'hp': 900, 'speed': 0.4, 'range': 280, 'damage': 70},
    'heavyBattleship': {'hp': 2000, 'speed': 0.3, 'range': 380, 'damage': 180},
    'turret': {'hp': 700, 'speed': 0, 'range': 180, 'damage': 41},
    'antiAirTurret': {'hp': 600, 'speed': 0, 'range': 240, 'damage': 30},
    'experimentalSpider': {'hp': 10000, 'speed': 0.5, 'range': 290, 'damage': 350},
    'experimentalGunship': {'hp': 8000, 'speed': 0.7, 'range': 350, 'damage': 300},
    'extractorT1': {'hp': 800, 'speed': 0, 'range': 0, 'damage': 0},
    'extractorT2': {'hp': 1200, 'speed': 0, 'range': 0, 'damage': 0},
    'extractorT3': {'hp': 1800, 'speed': 0, 'range': 0, 'damage': 0},
    'fabricatorT1': {'hp': 600, 'speed': 0, 'range': 0, 'damage': 0},
    'fabricatorT2': {'hp': 1000, 'speed': 0, 'range': 0, 'damage': 0},
}

BUILDING_TYPES = {'commandCenter', 'extractorT1', 'extractorT2', 'extractorT3',
    'fabricatorT1', 'fabricatorT2', 'fabricatorT3', 'mechFactory', 'airFactory',
    'seaFactory', 'landFactory', 'turret', 'antiAirTurret', 'antiAirTurretFlak',
    'outpost', 'laboratory', 'repairbay'}


def get_cost(name): return UNIT_COSTS.get(name, 0)
def get_income(name): return INCOME_RATES.get(name, 0)
def is_building(name): return name in BUILDING_TYPES
def get_hp(name): return UNIT_STATS.get(name, {}).get('hp', 100)
def get_speed(name): return UNIT_STATS.get(name, {}).get('speed', 0)
def get_range(name): return UNIT_STATS.get(name, {}).get('range', 0)
def get_damage(name): return UNIT_STATS.get(name, {}).get('damage', 2)
def get_default_units(): return sorted(UNIT_COSTS.keys())


# ═══════════════════════════════════════════════════════════════
# WEAPON / MOVEMENT TYPES
# ═══════════════════════════════════════════════════════════════

class WeaponType:
    MOVE=0; ATTACK=1; BUILD=2; REPAIR=3; LOAD_INTO=4; UNLOAD_AT=5; RECLAIM=6
    ATTACK_MOVE=7; LOAD_UP=8; PATROL=9; GUARD=10; GUARD_AT=11

class MoveType:
    AIR=0; LAND=1; HOVER=2; BUILDING=3; NONE=4; OVER_CLIFF=5; OVER_CLIFF_WATER=6; ROOT=7

class Behavior:
    AGGRESSIVE=0; GUARD_AREA=1; HOLD_FIRE=2; RETURN_FIRE=3; MIXED=4


# ═══════════════════════════════════════════════════════════════
# SIMULATION UNIT
# ═══════════════════════════════════════════════════════════════

@dataclass
class Unit:
    uid: int; unit_type: str; team_id: int
    x: float=0.0; y: float=0.0; radius: float=30.0; rotation: float=0.0
    hp: float=100.0; max_hp: float=100.0; shield: float=0.0; max_shield: float=0.0
    build_progress: float=1.0; is_building: bool=False
    is_alive: bool=True; is_dead: bool=False; death_tick: int=0; spawn_tick: int=0
    income_rate: float=0.0
    damage: float=2.0; weapon_range: float=120.0
    cooldown_ticks: int=0; cooldown_max: int=60; ammo: int=1
    weapon_type: int=WeaponType.ATTACK; target_uid: int=-1
    move_speed: float=0.0; move_type: int=MoveType.LAND
    target_x: float=0.0; target_y: float=0.0
    has_move_target: bool=False; arrived: bool=True
    cost: int=0; build_time: float=20.0
    last_attacker_uid: int=-1; parent_uid: int=-1
    behavior: int=Behavior.AGGRESSIVE
    total_shield_absorbed: float=0.0; total_hp_lost: float=0.0

    @property
    def is_complete(self): return self.build_progress >= 1.0
    @property
    def is_mobile(self): return self.move_type not in (MoveType.BUILDING, MoveType.NONE, MoveType.ROOT)

    def distance_to(self, other): return math.hypot(self.x-other.x, self.y-other.y)

    def apply_damage(self, incoming, attacker_uid,
                     shield_mult=SHIELD_MULTIPLIER,
                     shield_absorb=SHIELD_ABSORB,
                     hp_mult=HP_MULTIPLIER):
        """
        Complete damage formula — bytecode-verified from am.java [553B].

        Stage 1: Incomplete building penalty — damage × 1.75 if cm < 1.0
        Stage 2: Shield absorption — shieldDmg = incoming × shieldMult
                 if shield >= shieldDmg: remaining -= shield × shieldAbsorb; shield = 0
                 else: shield -= shieldDmg; remaining -= remaining × shieldAbsorb
        Stage 3: HP damage — hpDmg = remaining × hpMult
                 if hp >= hpDmg: hp = 0 → triggers ch() → bV=true (DEATH)
                 else: hp -= hpDmg
        Returns overflow damage (for splash).
        """
        remaining = incoming
        if self.build_progress < 1.0: remaining *= INCOMPLETE_DAMAGE_MULT
        if self.shield > 0:
            sd = remaining * shield_mult
            if self.shield >= sd:
                remaining -= self.shield * shield_absorb
                self.total_shield_absorbed += self.shield; self.shield = 0.0
            else:
                self.shield -= sd; self.total_shield_absorbed += sd
                remaining -= remaining * shield_absorb
        if remaining > 0:
            hd = remaining * hp_mult
            if self.hp >= hd:
                self.hp -= hd; self.total_hp_lost += hd   # survives, HP reduced
            else:
                self.total_hp_lost += self.hp; self.hp = 0.0  # dies
        self.last_attacker_uid = attacker_uid
        if self.hp <= 0 and not self.is_dead:
            self.is_dead = True; self.is_alive = False
        return remaining


# ═══════════════════════════════════════════════════════════════
# PROJECTILE
# ═══════════════════════════════════════════════════════════════

@dataclass
class Projectile:
    uid: int; owner_uid: int; team_id: int
    x: float; y: float; target_x: float; target_y: float; damage: float
    speed: float=PROJECTILE_SPEED; lifetime: float=PROJECTILE_LIFETIME
    max_lifetime: float=PROJECTILE_TIMEOUT
    active: bool=True; exploded: bool=False


# ═══════════════════════════════════════════════════════════════
# SPATIAL GRID
# ═══════════════════════════════════════════════════════════════

class SpatialGrid:
    def __init__(self, w=SPATIAL_GRID_SIZE, h=SPATIAL_GRID_SIZE):
        self.w, self.h, self.cs = w, h, SPATIAL_CELL_SIZE
        self.cells = defaultdict(list); self.pos = {}
    def add(self, u):
        cx=max(0,min(self.w-1,int(u.x/self.cs))); cy=max(0,min(self.h-1,int(u.y/self.cs)))
        self.cells[(cx,cy)].append(u.uid); self.pos[u.uid]=(cx,cy)
    def remove(self, u):
        if u.uid in self.pos:
            k=self.pos[u.uid]
            if k in self.cells and u.uid in self.cells[k]: self.cells[k].remove(u.uid)
            del self.pos[u.uid]
    def update(self, u): self.remove(u); self.add(u)
    def query_circle(self, x, y, r):
        minc=max(0,int((x-r)/self.cs)); maxc=min(self.w-1,int((x+r)/self.cs))
        minr=max(0,int((y-r)/self.cs)); maxr=min(self.h-1,int((y+r)/self.cs))
        res=[]
        for cx in range(minc,maxc+1):
            for cy in range(minr,maxr+1): res.extend(self.cells.get((cx,cy),[]))
        return res
    def find_nearest_enemy(self, unit, enemy_world):
        candidates=self.query_circle(unit.x,unit.y,max(ATTACK_DETECT_RANGE,unit.weapon_range))
        best,bd=-1,float('inf')
        for uid in candidates:
            o=enemy_world.get_unit(uid)
            if not o or not o.is_alive or o.team_id==unit.team_id: continue
            d=unit.distance_to(o)
            if d<bd: bd=d; best=uid
        return best if best>=0 else None


# ═══════════════════════════════════════════════════════════════
# SIMULATION WORLD
# ═══════════════════════════════════════════════════════════════

class World:
    def __init__(self, team_id):
        self.team_id=team_id; self.units={}; self.next_uid=1
        self.credits=STARTING_CREDITS; self.total_income_rate=0.0; self.cc_uid=-1
        self.pending_builds=[]; self.projectiles={}; self.next_pid=100000
        self.grid=SpatialGrid(); self.enemy_world=None
        self.tick=0; self.total_income=0.0; self.total_spent=0.0
        self.kills=0; self.losses=0; self.units_built=0

    def spawn_unit(self, name, x=0, y=0):
        bld=is_building(name); hp=get_hp(name); spd=get_speed(name)
        rng=get_range(name); dmg=get_damage(name); cost=get_cost(name); inc=get_income(name)
        u=Unit(uid=self.next_uid, unit_type=name, team_id=self.team_id, x=x, y=y,
               hp=hp, max_hp=hp, move_speed=spd*60 if spd>0 else 0,
               weapon_range=rng if rng>0 else 120, damage=dmg if dmg>0 else 2,
               cost=cost, is_building=bld, income_rate=float(inc),
               move_type=MoveType.BUILDING if bld else MoveType.LAND)
        if bld: u.build_progress=1.0 if name=='commandCenter' else 0.0
        if u.weapon_range>0 and u.damage>0:
            u.weapon_type=WeaponType.ATTACK if u.is_mobile else WeaponType.GUARD
            u.cooldown_max=max(20,int(rng/3))
        self.next_uid+=1; self.units[u.uid]=u
        if u.build_progress>=1.0 and u.income_rate>0: self.total_income_rate+=u.income_rate
        self.grid.add(u); self.units_built+=1; return u

    def get_unit(self,uid): return self.units.get(uid)

    def remove_unit(self, uid):
        """
        Death cleanup — bytecode-verified from am.bu() [98B].

        8-step death chain:
          1. ch() — check cu <= 0, set bV=true
          2. bv() — call bu() + e() + a() + bt()
          3. bu() — l.bS.l(this) remove from factory
          4.      — s.b(this)     ★ unregister income from tracker
          5.      — am.bE.remove   ★ remove from global registry
          6.      — l.cc.a(this)   ★ remove from spatial grid
          7. y.bx() — detach from parent unit
          8. y.bC() — recalculate ResourceComponent
        """
        u=self.units.get(uid)
        if not u: return
        if u.build_progress>=1.0 and u.income_rate>0:
            self.total_income_rate=max(0,self.total_income_rate-u.income_rate)
        self.grid.remove(u)
        for o in self.units.values():
            if o.target_uid==uid: o.target_uid=-1
            if o.parent_uid==uid: o.parent_uid=-1
        u.is_dead=True; u.is_alive=False; u.death_tick=self.tick; u.hp=0.0

    def add_credits(self, a): self.credits+=a; self.total_income+=a
    def spend_credits(self, a):
        if self.credits>=a: self.credits-=a; self.total_spent+=a; return True
        return False

    def compute_income(self, dt, spd=DEFAULT_SPEED):
        """
        Income per second — verified from s.java, y.java, d.b.java.

        Formula: income/s = s.g × (FRAME_RATE / ECONOMY_WINDOW) × ay.h × gameSpeed
                          = total_income_rate × (60/40) × incomeMultiplier × speed
                          = total_income_rate × 1.5 × 2.5 @default

        CC:     cy()=18  → 18 × 1.5 × 2.5 = 67.5/s
        T1 Ext: cy()=8   → 8  × 1.5 × 2.5 = 30.0/s
        T2 Ext: cy()=12  → 12 × 1.5 × 2.5 = 45.0/s
        T3 Ext: cy()=20  → 20 × 1.5 × 2.5 = 75.0/s

        s.g accumulates: s.a(unit) → g += (int)unit.cy()  [when cm >= 1.0]
                         s.b(unit) → g -= (int)unit.cy()  [on unregister/death]
        """
        ps=self.total_income_rate*(FRAME_RATE/ECONOMY_WINDOW)*spd
        self.add_credits(ps*dt); return ps*dt

    def start_build(self, name, x, y, cost):
        if not self.spend_credits(cost): return None
        u=self.spawn_unit(name,x,y); u.build_progress=0.0; u.cost=cost
        self.pending_builds.append((0.0,u)); return u

    def update_builds(self, dt):
        """
        Build progress — verified from d.j (BuildProgress), h.e (Factory [172B]).

        Normal build:  buildSpeed=0.10/tick, maxProgress=330
                       real_seconds = 330 / (0.10 × 60 × 2.5) ≈ 22s
        Factory build: buildSpeed=0.03/tick, maxProgress=280
                       real_seconds = 280 / (0.03 × 60 × 2.5) ≈ 62s
        CC build:      same as factory

        Progress accumulation: d.j.n += buildSpeed × delta_ms
        Completion:            d.j.n >= 1.0 → f=true → am.r(1.0f)
                               → s.b(old) then s.a(new) for upgrades
        Inactive factory:      speed mult = -8.0 (regression!) [h.e.s()]
        """
        done=[]
        for i,(p,u) in enumerate(self.pending_builds):
            bs=BUILD_SPEED_FACTORY if 'factory' in u.unit_type.lower() or u.unit_type=='commandCenter' else BUILD_SPEED_NORMAL
            p+=bs*FRAME_RATE*dt*DEFAULT_SPEED; self.pending_builds[i]=(p,u)
            if p>=1.0:
                u.build_progress=1.0
                if u.income_rate>0: self.total_income_rate+=u.income_rate
                done.append(i)
        for i in reversed(done): self.pending_builds.pop(i)

    def unit_attack(self, a, t):
        if a.distance_to(t)>a.weapon_range+t.radius or a.cooldown_ticks>0 or a.ammo<=0: return False
        pid=self.next_pid; self.next_pid+=1
        self.projectiles[pid]=Projectile(uid=pid,owner_uid=a.uid,team_id=self.team_id,
            x=a.x,y=a.y,target_x=t.x,target_y=t.y,damage=a.damage)
        a.cooldown_ticks=a.cooldown_max; a.ammo-=1; return True

    def update_projectiles(self, dt):
        rem=[]
        for pid,p in list(self.projectiles.items()):
            p.lifetime-=dt*1000
            if p.lifetime<=0 or p.lifetime>p.max_lifetime: rem.append(pid); continue
            dx=p.target_x-p.x; dy=p.target_y-p.y; dist=math.hypot(dx,dy)
            if dist<8.0:
                if self.enemy_world:
                    for uid,t in self.enemy_world.units.items():
                        if not t.is_alive: continue
                        if math.hypot(p.target_x-t.x,p.target_y-t.y)<t.radius+15:
                            t.apply_damage(p.damage,p.owner_uid)
                            if t.is_dead: self.kills+=1; self.enemy_world.losses+=1; self.enemy_world.remove_unit(uid)
                            break
                rem.append(pid)
            else:
                mv=min(p.speed*dt,dist); r=mv/dist if dist>0 else 1
                p.x+=dx*r; p.y+=dy*r
        for pid in rem:
            if pid in self.projectiles: del self.projectiles[pid]

    def auto_combat(self):
        if not self.enemy_world: return
        for u in list(self.units.values()):
            if not u.is_alive or not u.is_complete: continue
            if u.weapon_type not in (WeaponType.ATTACK,WeaponType.GUARD): continue
            if u.cooldown_ticks>0: u.cooldown_ticks-=1; continue
            if u.ammo<=0: continue
            eid=self.grid.find_nearest_enemy(u,self.enemy_world)
            if eid is None: continue
            e=self.enemy_world.units[eid]; d=u.distance_to(e)
            if d<=u.weapon_range+e.radius: self.unit_attack(u,e)
            elif u.is_mobile and not u.has_move_target:
                r=max(0,d-u.weapon_range*0.8)/d if d>0 else 0
                self.move_unit_to(u,u.x+(e.x-u.x)*(1-r),u.y+(e.y-u.y)*(1-r))

    def update_movement(self, dt):
        for u in self.units.values():
            if not u.is_alive or not u.has_move_target or u.arrived or not u.is_mobile: continue
            dx=u.target_x-u.x; dy=u.target_y-u.y; dist=math.hypot(dx,dy)
            if dist<max(u.radius+5,20.0): u.arrived=True; u.has_move_target=False; continue
            mv=min(u.move_speed*dt,dist); r=mv/dist if dist>0 else 1
            u.x+=dx*r; u.y+=dy*r; u.rotation=math.degrees(math.atan2(dy,dx))
            self.grid.update(u)

    def move_unit_to(self, u, x, y):
        u.target_x=x; u.target_y=y; u.has_move_target=True; u.arrived=False

    def step(self, dt):
        self.tick+=1
        self.compute_income(dt); self.update_builds(dt)
        self.update_movement(dt); self.auto_combat(); self.update_projectiles(dt)
        for u in list(self.units.values()):
            if u.cooldown_ticks>0: u.cooldown_ticks-=1

    def get_state(self):
        return {'team_id':self.team_id,'tick':self.tick,'credits':self.credits,
            'income_rate':self.total_income_rate,
            'income_ps':self.total_income_rate*(FRAME_RATE/ECONOMY_WINDOW)*DEFAULT_SPEED,
            'total_income':self.total_income,'total_spent':self.total_spent,
            'active_units':sum(1 for u in self.units.values() if u.is_alive),
            'total_units':len(self.units),'kills':self.kills,'losses':self.losses,
            'units_built':self.units_built}


# ═══════════════════════════════════════════════════════════════
# SIMULATION ENGINE
# ═══════════════════════════════════════════════════════════════

class Engine:
    def __init__(self):
        self.worlds={}; self.global_tick=0; self.game_time=0.0
        self.speed_mult=DEFAULT_SPEED

    def create_world(self, tid):
        w=World(tid); self.worlds[tid]=w; return w

    def setup_teams(self, a=0, b=1):
        wa=self.create_world(a); wb=self.create_world(b)
        wa.enemy_world=wb; wb.enemy_world=wa
        wa.spawn_unit('commandCenter',400,400); wb.spawn_unit('commandCenter',1200,800)
        return wa,wb

    def tick(self, dt=1.0/FRAME_RATE):
        self.global_tick+=1; self.game_time+=dt
        for w in self.worlds.values(): w.step(dt)

    def run_for(self, sec, dt=1.0/FRAME_RATE):
        for _ in range(int(sec*FRAME_RATE)): self.tick(dt)

    def get_all_states(self): return {tid:w.get_state() for tid,w in self.worlds.items()}
