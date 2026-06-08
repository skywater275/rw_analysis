# -*- coding: utf-8 -*-
"""
Standalone game launcher using our reverse-engineered simulation engine.
Runs a complete game session without the official game binary.
"""
import sys, os, math, time, json
from collections import defaultdict

sys.path.insert(0, os.path.join(os.path.dirname(os.path.abspath(__file__)), '..'))

from core.engine_data import get_cost, get_income_rate, is_building, CC_INCOME_CY
from core.engine_constants import STARTING_CREDITS, FRAME_RATE, DEFAULT_SPEED_MULTIPLIER
from simulation.engine import (SimulationEngine, SimulationWorld, SimulationUnit,
    WeaponType, MoveType, Behavior, DEFAULT_SPEED, FRAME_RATE,
    SPATIAL_GRID_SIZE, SPATIAL_CELL_SIZE, ATTACK_DETECT_RANGE)


class GameSession:
    """Complete standalone game session."""

    def __init__(self, map_width=1600, map_height=1600, num_teams=2, speed=2.5,
                 starting_credits=STARTING_CREDITS, income_mult=1.0):
        self.engine = SimulationEngine()
        self.engine.speed_mult = speed
        self.engine.income_mult = income_mult

        self.map_width = map_width
        self.map_height = map_height
        self.num_teams = num_teams
        self.starting_credits = starting_credits
        self.game_time = 0.0
        self.paused = False
        self.game_over = False
        self.winner = None

        # Create team worlds
        self.worlds = {}
        for tid in range(num_teams):
            w = self.engine.create_world(tid)
            w.credits = starting_credits
            # Spawn CC at team-specific positions
            cx = map_width * (0.2 + 0.6 * tid / max(1, num_teams - 1))
            cy = map_height * (0.3 if tid % 2 == 0 else 0.7)
            cc = w.spawn_unit('commandCenter', x=cx, y=cy, is_building=True)
            w.cc_uid = cc.uid
            self.worlds[tid] = w

        # Link enemies (all-vs-all for now)
        for tid, w in self.worlds.items():
            enemies = [ww for otid, ww in self.worlds.items() if otid != tid]
            if enemies:
                w.enemy_world = enemies[0]

        # Game log
        self.log = []
        self.events_processed = 0

        print("GameSession initialized:")
        print("  Map: %dx%d" % (map_width, map_height))
        print("  Teams: %d, Speed: %.1fx, Credits: %d" % (num_teams, speed, starting_credits))
        for tid in sorted(self.worlds.keys()):
            w = self.worlds[tid]
            print("  Team %d: CC at (%.0f, %.0f), income=%.1f/s" % (
                tid, w.get_unit(w.cc_uid).x, w.get_unit(w.cc_uid).y,
                CC_INCOME_CY * 1.5 * speed))

    def build_unit(self, team_id, unit_type, x, y):
        """Issue a build command."""
        w = self.worlds.get(team_id)
        if w is None:
            return "Invalid team"

        cost = get_cost(unit_type)
        if cost <= 0:
            return "Unknown unit: %s" % unit_type

        if w.credits < cost:
            return "Insufficient credits: need %d, have %.0f" % (cost, w.credits)

        if is_building(unit_type):
            unit = w.start_build(unit_type, x, y, cost)
        else:
            if not w.spend_credits(cost):
                return "Failed to spend credits"
            unit = w.spawn_unit(unit_type, x, y)

        self.log.append("[t=%.1f] Team %d built %s at (%.0f, %.0f) for %d credits" % (
            self.game_time, team_id, unit_type, x, y, cost))
        self.events_processed += 1
        return unit

    def move_units(self, team_id, x, y, count='all'):
        """Move units to a position."""
        w = self.worlds.get(team_id)
        if w is None:
            return

        moved = 0
        for unit in w.units.values():
            if not unit.is_alive or not unit.is_mobile:
                continue
            w.move_unit_to(unit, x, y)
            moved += 1
            if isinstance(count, int) and moved >= count:
                break

        if moved > 0:
            self.log.append("[t=%.1f] Team %d moved %d units to (%.0f, %.0f)" % (
                self.game_time, team_id, moved, x, y))
        return moved

    def attack_move(self, team_id, x, y, count='all'):
        """Attack-move units to a position."""
        w = self.worlds.get(team_id)
        if w is None:
            return

        moved = 0
        for unit in w.units.values():
            if not unit.is_alive or not unit.is_mobile:
                continue
            if unit.weapon_type == WeaponType.MOVE:
                continue
            w.attack_move_unit_to(unit, x, y)
            moved += 1
            if isinstance(count, int) and moved >= count:
                break

        if moved > 0:
            self.log.append("[t=%.1f] Team %d attack-moved %d units to (%.0f, %.0f)" % (
                self.game_time, team_id, moved, x, y))
        return moved

    def tick(self):
        """Advance one frame."""
        if self.paused or self.game_over:
            return

        delta = 1.0 / FRAME_RATE
        self.engine.tick(delta)
        self.game_time += delta

        # Check victory condition every 60 ticks
        if self.engine.global_tick % 60 == 0:
            self._check_victory()

    def run_for(self, seconds):
        """Run for a given duration."""
        frames = int(seconds * FRAME_RATE)
        for _ in range(frames):
            self.tick()
            if self.game_over:
                break

    def _check_victory(self):
        """Check if any team has been eliminated."""
        alive_teams = []
        for tid, w in self.worlds.items():
            has_units = False
            for unit in w.units.values():
                if unit.is_alive and unit.is_building:
                    has_units = True
                    break
            if w.cc_uid >= 0:
                cc = w.get_unit(w.cc_uid)
                if cc and cc.is_alive:
                    has_units = True
            if has_units:
                alive_teams.append(tid)

        if len(alive_teams) <= 1 and self.game_time > 5:
            self.game_over = True
            self.winner = alive_teams[0] if alive_teams else None

    def get_state(self):
        """Get current game state."""
        return {
            'time': self.game_time,
            'tick': self.engine.global_tick,
            'paused': self.paused,
            'game_over': self.game_over,
            'winner': self.winner,
            'teams': {tid: w.get_state() for tid, w in self.worlds.items()},
        }

    def print_status(self):
        """Print current game status."""
        state = self.get_state()
        print()
        print("=" * 55)
        print("GAME STATUS  |  Time: %.1fs  |  Tick: %d  |  Speed: %.1fx" % (
            state['time'], state['tick'], self.engine.speed_mult))
        if self.paused:
            print("  *** PAUSED ***")
        if self.game_over:
            print("  *** GAME OVER — Winner: Team %s ***" % (self.winner or 'NONE'))
        print("-" * 55)
        print("  %-12s %10s %10s %8s %8s %8s" % ('Team', 'Credits', 'Income/s', 'Units', 'Kills', 'Losses'))
        print("-" * 55)
        for tid in sorted(self.worlds.keys()):
            w = self.worlds[tid]
            s = w.get_state()
            income_ps = s['income_rate'] * (FRAME_RATE / 40.0) * self.engine.speed_mult
            print("  Team %-7d %10.0f %10.1f %8d %8d %8d" % (
                tid, s['credits'], income_ps,
                s['active_units'], s['kills'], s['losses']))
        print("=" * 55)
        # Recent log
        for entry in self.log[-5:]:
            print("  %s" % entry)


def main():
    """Run a standalone game demo."""
    print("=" * 60)
    print("Rusted Warfare v1.15 — Standalone Simulation Engine")
    print("=" * 60)

    # Create game session
    game = GameSession(map_width=2000, map_height=2000, num_teams=2)

    # Demo: Team 0 builds economy, Team 1 builds military
    print()
    print("DEMO: Automated 60-second game")
    print()

    # Phase 1: Early game (0-15s) — build extractors
    for t in range(0, 15, 5):
        game.run_for(5)
        for tid in range(2):
            x = 500 + tid * 1000 + (t % 3) * 80
            y = 600 + (t % 2) * 100
            game.build_unit(tid, 'extractorT1', x, y)
            game.build_unit(tid, 'extractorT1', x + 60, y)
        game.print_status()

    # Phase 2: Mid game (15-30s) — build factories + military
    for t in range(15, 30, 5):
        game.run_for(5)
        for tid in range(2):
            fx, fy = 400 + tid * 1200, 700
            game.build_unit(tid, 'mechFactory', fx, fy)
            # Spawn mechs
            for i in range(3):
                game.build_unit(tid, 'mechGun', fx + 100 + i*40, fy + 100)
        game.print_status()

    # Phase 3: Late game (30-60s) — combat!
    game.move_units(0, 1200, 800)
    game.move_units(1, 800, 800)
    game.run_for(10)
    game.print_status()

    game.run_for(20)
    game.print_status()

    print()
    print("FINAL STATE:")
    state = game.get_state()
    for tid, ts in state['teams'].items():
        print("  Team %d: credits=%.0f, units=%d, kills=%d, losses=%d" % (
            tid, ts['credits'], ts['active_units'], ts['kills'], ts['losses']))

    if game.winner is not None:
        print("  WINNER: Team %d" % game.winner)
    else:
        print("  Result: DRAW (time limit)")

    print()
    print("Session complete. %d events processed." % game.events_processed)


if __name__ == '__main__':
    main()
