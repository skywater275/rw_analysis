# -*- coding: utf-8 -*-
"""
Replay Simulator — runs a complete headless replay simulation.

Reads a .replay file, reconstructs the initial game state from the snapshot,
processes all commands in sequence, and produces a full tick-by-tick timeline.
"""
import sys, os, json, csv, time, math, gzip, struct
from typing import Dict, List, Optional, Tuple, Any
from dataclasses import dataclass, field
from collections import defaultdict

sys.path.insert(0, os.path.join(os.path.dirname(os.path.abspath(__file__)), '..'))

from core.engine_data import get_cost, get_income_rate, get_build_time, is_building
from core.engine_constants import (
    STARTING_CREDITS, FRAME_RATE, TICK_RATE, DEFAULT_SPEED_MULTIPLIER,
    RECLAIM_REFUND_RATE, CANCEL_REFUND_RATE, CC_CREDITS, ECONOMY_DISPLAY_FRAMES,
)
from simulation.engine import (
    SimulationEngine, SimulationWorld, SimulationUnit, Projectile, SpatialGrid,
    WeaponType, MoveType, Behavior,
    BUILD_SPEED_FACTORY, BUILD_SPEED_NORMAL, BUILD_MAX_NORMAL, BUILD_MAX_FACTORY,
    SHIELD_MULTIPLIER, SHIELD_ABSORB, HP_MULTIPLIER,
    PROJECTILE_SPEED, PROJECTILE_LIFETIME, COLLISION_DIST_SQ,
    BUILDER_SEARCH_RANGE, ATTACK_DETECT_RANGE,
)


@dataclass
class ReplayEvent:
    """Parsed replay command event."""
    tick: int
    event_type: str         # 'build_xxx', 'cancel_xxx', 'move', 'attack', etc.
    player_id: int
    unit_name: str
    target_x: float = 0.0
    target_y: float = 0.0
    cost: int = 0
    is_cancel: bool = False
    raw_data: bytes = field(default_factory=bytes)


class ReplaySimulator:
    """
    Complete replay simulator.

    Flow:
      1. Parse replay → extract snapshot + events
      2. Initialize SimulationEngine with teams from snapshot
      3. Sort events by tick
      4. For each tick interval:
         a. Advance simulation (economy, combat, movement)
         b. Process any events at this tick
      5. Record state snapshots at regular intervals
      6. Generate final report
    """

    def __init__(self, replay_path: str):
        self.replay_path = replay_path
        self.engine = SimulationEngine()
        self.events: List[ReplayEvent] = []
        self.timeline: List[Dict] = []
        self.sample_interval: int = 60  # sample every 60 ticks (1s @60fps)
        self.max_ticks: int = 0
        self.parser_result: Any = None

    def load(self):
        """Parse the replay file using the core parser."""
        sys.path.insert(0, os.path.join(os.path.dirname(os.path.abspath(__file__)), '..'))
        from core.rw_replay_parser import ReplayParser

        parser = ReplayParser(self.replay_path)
        parser.parse()
        self.parser_result = parser
        return parser

    def initialize_from_parser(self, parser):
        """Initialize simulation state from parsed replay data."""
        # Extract teams
        teams = {}
        for player in parser.players:
            tid = getattr(player, 'team', 'A')
            if isinstance(tid, str):
                tid = ord(tid) - ord('A')
            if tid not in teams:
                teams[tid] = []
            teams[tid].append(player)

        # Create worlds for each team
        team_ids = sorted(teams.keys())
        for i, tid in enumerate(team_ids):
            w = self.engine.create_world(tid)
            w.credits = STARTING_CREDITS

            # Spawn CC for each player on this team
            player_list = teams[tid]
            for pi, player in enumerate(player_list):
                cc_x = 400 + pi * 100
                cc_y = 400 + tid * 300
                cc = w.spawn_unit('commandCenter', x=cc_x, y=cc_y, is_building=True)
                w.cc_uid = cc.uid

        # Link enemy worlds (assume 2 teams for now)
        if len(team_ids) >= 2:
            self.engine.worlds[team_ids[0]].enemy_world = self.engine.worlds[team_ids[1]]
            self.engine.worlds[team_ids[1]].enemy_world = self.engine.worlds[team_ids[0]]

        # Extract events from commands
        self._extract_events(parser)

        # Calculate max ticks
        if self.events:
            self.max_ticks = max(e.tick for e in self.events) + 120  # 2s padding

        print(f"Initialized: {len(team_ids)} teams, {len(self.events)} events, "
              f"max_tick={self.max_ticks}")

    def _extract_events(self, parser):
        """Extract simulation events from parsed commands."""
        for block in parser.blocks:
            if not hasattr(block, 'type') or block.type != 'rc':
                continue

            tick = getattr(block, 'tick', 0)
            cmd = getattr(block, 'cmd', None)
            if cmd is None:
                continue

            unit_name = getattr(cmd, 'unit_name', '') or ''
            player_idx = getattr(cmd, 'player_index', 0)
            is_cancel = getattr(cmd, 'stop_or_undo', False)
            is_system = getattr(cmd, 'system_action', 0)

            x = getattr(cmd, 'target_x', 0.0) or 0.0
            y = getattr(cmd, 'target_y', 0.0) or 0.0

            # Determine event type
            if is_cancel:
                evt_type = f'cancel_{unit_name}'
            elif is_system:
                evt_type = f'system_{unit_name}'
            elif unit_name:
                evt_type = f'build_{unit_name}'
            else:
                evt_type = 'action'

            cost = get_cost(unit_name)
            if is_cancel:
                cost = 0

            self.events.append(ReplayEvent(
                tick=tick, event_type=evt_type, player_id=player_idx,
                unit_name=unit_name, target_x=x, target_y=y,
                cost=cost, is_cancel=is_cancel,
            ))

    def run(self, sample_interval: int = 60):
        """
        Run the full replay simulation.

        Args:
            sample_interval: How often to record state (in ticks). 60 = 1s @60fps.
        """
        self.sample_interval = sample_interval
        self.timeline = []

        if not self.events:
            print("No events to simulate")
            return

        # Sort events by tick
        events_by_tick = defaultdict(list)
        for evt in self.events:
            events_by_tick[evt.tick].append(evt)

        # Simulation loop
        delta = 1.0 / FRAME_RATE
        current_tick = 0
        max_tick = self.max_ticks

        print(f"Simulating {max_tick} ticks ({max_tick/FRAME_RATE:.1f}s) at {FRAME_RATE}fps...")

        while current_tick <= max_tick:
            # Process events at this tick
            for evt in events_by_tick.get(current_tick, []):
                self._process_event(evt)

            # Sample state
            if current_tick % sample_interval == 0:
                self._sample_state(current_tick)

            # Advance
            self.engine.tick(delta)
            current_tick += 1

        print(f"Simulation complete: {len(self.timeline)} samples recorded")

    def _process_event(self, evt: ReplayEvent):
        """Process a single replay event."""
        # Find the right world
        world = self._find_world_for_player(evt.player_id)
        if world is None:
            return

        etype = evt.event_type

        if etype.startswith('build_'):
            unit_name = evt.unit_name
            cost = get_cost(unit_name)

            if is_building(unit_name):
                # Building: start construction
                world.start_build(unit_name, evt.target_x or 400, evt.target_y or 400, cost)
            else:
                # Mobile unit: spawn immediately
                unit = world.spawn_unit(unit_name, evt.target_x or 400, evt.target_y or 400)
                world.spend_credits(cost)

        elif etype.startswith('cancel_'):
            # Find and cancel the unit
            unit_name = evt.unit_name
            for unit in list(world.units.values()):
                if unit.unit_type == unit_name and unit.build_progress < 1.0:
                    world.cancel_build(unit)
                    break

        elif etype == 'action' and evt.target_x != 0:
            # Move/attack command — find nearest unit and move it
            nearest = None
            nearest_dist = float('inf')
            for unit in world.units.values():
                if not unit.is_alive or not unit.is_mobile:
                    continue
                d = unit.distance_to_xy(evt.target_x, evt.target_y)
                if d < nearest_dist:
                    nearest_dist = d
                    nearest = unit
            if nearest:
                world.move_unit_to(nearest, evt.target_x, evt.target_y)

    def _find_world_for_player(self, player_idx: int) -> Optional[SimulationWorld]:
        """Map player index to simulation world."""
        # Simple heuristic: player 0→team A(0), player 1→team B(1)
        for tid, w in self.engine.worlds.items():
            return w  # Return first world for now
        return None

    def _sample_state(self, tick: int):
        """Record state snapshot at current tick."""
        game_time = tick / FRAME_RATE
        states = self.engine.get_all_states()

        sample = {
            'tick': tick,
            'time': game_time,
        }
        for tid, state in states.items():
            for key, val in state.items():
                sample[f'team{tid}_{key}'] = val

        self.timeline.append(sample)

    def generate_report(self) -> str:
        """Generate a human-readable simulation report."""
        lines = []
        lines.append("=" * 60)
        lines.append("Rusted Warfare v1.15 — Replay Simulation Report")
        lines.append("=" * 60)
        lines.append(f"Replay: {os.path.basename(self.replay_path)}")
        lines.append(f"Duration: {self.engine.game_time:.1f}s ({self.max_ticks} ticks)")
        lines.append(f"Events processed: {len(self.events)}")
        lines.append(f"Timeline samples: {len(self.timeline)}")
        lines.append("")

        for tid, world in self.engine.worlds.items():
            lines.append(f"--- Team {tid} ---")
            state = world.get_state()
            lines.append(f"  Final credits: {state['credits']:.0f}")
            lines.append(f"  Income rate: {state['income_rate']:.0f} raw ({state['income_per_sec']:.1f}/s)")
            lines.append(f"  Total income: {state['total_income']:.0f}")
            lines.append(f"  Total spent: {state['total_spent']:.0f}")
            lines.append(f"  Active units: {state['active_units']}")
            lines.append(f"  Total units: {state['total_units']}")
            lines.append(f"  Buildings: {state['buildings']}")
            lines.append(f"  Kills: {state['kills']}")
            lines.append(f"  Losses: {state['losses']}")
            lines.append(f"  Units built: {state['units_built']}")
            lines.append(f"  Buildings completed: {state['buildings_completed']}")

            # Verify economy
            expected = STARTING_CREDITS + state['total_income'] - state['total_spent']
            actual = state['credits']
            drift = actual - expected
            lines.append(f"  Economy drift: {drift:+.1f} (expected={expected:.0f}, actual={actual:.0f})")
            lines.append("")

        return '\n'.join(lines)

    def export_csv(self, output_path: str = None):
        """Export timeline to CSV."""
        if not self.timeline:
            return

        if output_path is None:
            base = os.path.splitext(os.path.basename(self.replay_path))[0]
            output_path = os.path.join(os.path.dirname(self.replay_path), f'{base}_sim.csv')

        if not self.timeline:
            return

        keys = self.timeline[0].keys()
        with open(output_path, 'w', newline='', encoding='utf-8') as f:
            w = csv.DictWriter(f, fieldnames=list(keys))
            w.writeheader()
            w.writerows(self.timeline)
        print(f"CSV exported to: {output_path}")

    def export_json(self, output_path: str = None):
        """Export timeline to JSON."""
        if output_path is None:
            base = os.path.splitext(os.path.basename(self.replay_path))[0]
            output_path = os.path.join(os.path.dirname(self.replay_path), f'{base}_sim.json')

        with open(output_path, 'w', encoding='utf-8') as f:
            json.dump(self.timeline, f, indent=2, default=str)
        print(f"JSON exported to: {output_path}")


def main():
    """CLI entry point."""
    import argparse
    ap = argparse.ArgumentParser(description='Rusted Warfare Replay Simulator')
    ap.add_argument('replay', help='Path to .replay file')
    ap.add_argument('--sample', type=int, default=60, help='Sample interval in ticks (default: 60)')
    ap.add_argument('--csv', action='store_true', help='Export CSV')
    ap.add_argument('--json', action='store_true', help='Export JSON')

    args = ap.parse_args()

    sim = ReplaySimulator(args.replay)
    parser = sim.load()
    sim.initialize_from_parser(parser)
    sim.run(sample_interval=args.sample)

    print(sim.generate_report())

    if args.csv:
        sim.export_csv()
    if args.json:
        sim.export_json()


if __name__ == '__main__':
    main()
