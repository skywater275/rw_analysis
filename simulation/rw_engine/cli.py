# -*- coding: utf-8 -*-
"""Command-line interface for the simulation engine."""
import sys, cmd, shlex, time
from engine import (Engine, FRAME_RATE, DEFAULT_SPEED, STARTING_CREDITS,
    get_cost, is_building, get_default_units, WeaponType, CC_INCOME_CY)

class Shell(cmd.Cmd):
    intro='Rusted Warfare v1.15 — Simulation Engine\nType help for commands.\n'
    prompt='RW> '
    def __init__(self):
        super().__init__()
        self.eng=None
    def do_start(self,arg):
        """start [teams=2] [speed=2.5] [credits=4000]"""
        a=shlex.split(arg) if arg else []
        n=int(a[0]) if len(a)>0 else 2; sp=float(a[1]) if len(a)>1 else 2.5
        cr=int(a[2]) if len(a)>2 else 4000
        self.eng=Engine(); self.eng.speed_mult=sp
        for tid in range(n):
            w=self.eng.create_world(tid); w.credits=cr
            w.spawn_unit('commandCenter',500+tid*800,500)
        for tid,w in self.eng.worlds.items():
            es=[ww for ot,ww in self.eng.worlds.items() if ot!=tid]
            w.enemy_world=es[0] if es else None
        print(f'Started: {n} teams, {sp}x speed, {cr} credits')
        self.do_status('')
    def do_status(self,_):
        if not self.eng: return print('No game. Use start.')
        for tid in sorted(self.eng.worlds):
            w=self.eng.worlds[tid]; s=w.get_state()
            print(f'  Team{tid}: {s["credits"]:.0f}cr {s["income_ps"]:.1f}/s {s["active_units"]}u K{s["kills"]}L{s["losses"]}')
    def do_run(self,arg):
        """run <seconds>"""
        if not self.eng: return print('No game.')
        try: s=float(arg.split()[0]) if arg else 10
        except: s=10
        t0=time.time(); self.eng.run_for(s)
        print(f'{s}s in {time.time()-t0:.2f}s ({s/(time.time()-t0):.0f}x)')
        self.do_status('')
    def do_build(self,arg):
        """build <team> <unit> [x] [y]"""
        if not self.eng: return print('No game.')
        a=shlex.split(arg)
        if len(a)<2: return print('build <team> <unit> [x] [y]')
        tid=int(a[0]); name=a[1]; w=self.eng.worlds.get(tid)
        if not w: return print('Bad team')
        cost=get_cost(name)
        if not cost: return print(f'Unknown: {name}. Try: {", ".join(get_default_units()[:15])}...')
        if w.credits<cost: return print(f'Need {cost}, have {w.credits:.0f}')
        x=float(a[2]) if len(a)>2 else 500+tid*800
        y=float(a[3]) if len(a)>3 else 600
        if is_building(name): w.start_build(name,x,y,cost)
        else: w.spend_credits(cost); w.spawn_unit(name,x,y)
        print(f'{name} at ({x:.0f},{y:.0f}) for {cost}')
    def do_move(self,arg):
        """move <team> <x> <y>"""
        if not self.eng: return print('No game.')
        a=shlex.split(arg)
        if len(a)<3: return print('move <team> <x> <y>')
        tid=int(a[0]); tx=float(a[1]); ty=float(a[2])
        w=self.eng.worlds.get(tid)
        if not w: return print('Bad team')
        n=sum(1 for u in w.units.values() if u.is_alive and u.is_mobile)
        for u in w.units.values():
            if u.is_alive and u.is_mobile: w.move_unit_to(u,tx,ty)
        print(f'Moved {n} units to ({tx:.0f},{ty:.0f})')
    def do_units(self,arg):
        """units [team]"""
        if not self.eng: return print('No game.')
        tid=int(arg.split()[0]) if arg else 0
        w=self.eng.worlds.get(tid)
        if not w: return print('Bad team')
        for _,u in sorted(w.units.items()):
            if u.is_alive:
                print(f'  [{u.uid}] {u.unit_type} hp={u.hp:.0f}/{u.max_hp:.0f} ({u.x:.0f},{u.y:.0f})')
    def do_list(self,_):
        """list — show all available units"""
        print(', '.join(get_default_units()))
    def do_quit(self,_): return True
    do_exit=do_quit

if __name__=='__main__': Shell().cmdloop()
