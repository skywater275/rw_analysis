/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.FireDecoration;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.pathfinding.SpatialQuery;
import com.corrodinggames.rts.game.units.UnitType;

public class DecorType4
extends com.corrodinggames.rts.game.units.pathfinding.SpatialQuery {
    float a;
    float b;
    public FireDecoration c;

    void aj() {
    }


    public int excludeTeam(UnitType y2) {
        return -2;
    }


    public com.corrodinggames.rts.game.PlayerState onlyEnemiesOfTeam(UnitType y2) {
        return null;
    }


    public void setup(UnitType y2, float f) {
        this.c = null;
    }

    public void a(float f, float f2) {
        this.a = f;
        this.b = f2;
    }


    public void callback(UnitType y2, float f2, UnitInstance am2) {
        if (am2 instanceof FireDecoration && !am2.isDead && am2.c(this.a, this.b, 0.0f)) {
            this.c = (FireDecoration) am2;
        }
    }
}
