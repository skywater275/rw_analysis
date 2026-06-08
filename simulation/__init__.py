# -*- coding: utf-8 -*-
"""
Rusted Warfare v1.15 — Simulation Engine v6.0

Fully deterministic replay simulation using bytecode-verified algorithms.
Covers: economy, combat, movement, death chain, build lifecycle, upgrades.

Based on reverse engineering of 1698 classes from game-lib.jar.
"""
from .engine import SimulationEngine, SimulationUnit, SimulationWorld
from .runner import ReplaySimulator
from .standalone import GameSession

__all__ = [
    'SimulationEngine', 'SimulationUnit', 'SimulationWorld',
    'ReplaySimulator', 'GameSession',
]
