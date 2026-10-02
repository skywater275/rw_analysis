/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.MovementController;
import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.bk;
import com.corrodinggames.rts.game.units.pathfinding.SpatialQuery;
import com.corrodinggames.rts.game.units.UnitType;

public class bj
extends SpatialQuery {
    com.corrodinggames.rts.game.MovementController a;
    bk b;
    UnitInstance c;
    com.corrodinggames.rts.game.MovementController d;
    UnitInstance e;


    public void setup(UnitType y2, float f) {
    }


    public int excludeTeam(UnitType y2) {
        return -2;
    }


    public PlayerState onlyEnemiesOfTeam(UnitType y2) {
        return null;
    }


    public PlayerState onlyTeam(UnitType y2) {
        return null;
    }


    public void callback(UnitType y2, float f, UnitInstance am2) {
    }
}
