/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.pathfinding.SpatialCallback;
import com.corrodinggames.rts.game.units.UnitType;

final class UnitType$1
extends SpatialCallback {
    UnitType$1() {
    }


    public void callback(UnitType y2, float f2, UnitInstance am2) {
        UnitType.a(y2, am2, f2, true);  // 02b y$1: y.a(var1,var3,var2,true) (y=UnitType 类名)
    }
}

