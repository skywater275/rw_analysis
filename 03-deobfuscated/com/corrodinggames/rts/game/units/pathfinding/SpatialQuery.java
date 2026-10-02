/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.pathfinding;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.pathfinding.SpatialCallback;
import com.corrodinggames.rts.game.units.UnitType;

public abstract class SpatialQuery
extends SpatialCallback {
    public abstract int excludeTeam(UnitType var1);

    public abstract PlayerState onlyEnemiesOfTeam(UnitType var1);

    public PlayerState onlyTeam(UnitType y2) {
        return null;
    }

    public void setup(UnitType y2, float f) {
    }

    public UnitInstance getResult() {
        return null;
    }
}
