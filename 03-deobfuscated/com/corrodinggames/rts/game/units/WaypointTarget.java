/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitInstance;

public strictfp class WaypointTarget {
    UnitInstance a;
    UnitInstance b;
    boolean c;
    GameAction d;  // 02b z.d = a.s (GameAction)

    public WaypointTarget a() {
        this.a = null;
        this.b = null;
        this.c = false;
        this.d = null;
        return this;
    }
}
