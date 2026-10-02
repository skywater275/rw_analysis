/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.y;

public class UnitReference$ActiveWaypointTargetReference
extends UnitReference {
    @Override
    public am getSingleRaw(y y2) {
        au au2 = y2.ar();
        if (au2 == null) {
            return null;
        }
        am am2 = au2.l();
        return am2;
    }

    @Override
    public String getClassDebugName() {
        return "ActiveWaypointTarget";
    }
}
