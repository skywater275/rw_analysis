/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$PlaceholderUnitReference;
import com.corrodinggames.rts.game.units.UnitType;

public class UnitReference$GetAsMarker
extends UnitReference$PlaceholderUnitReference {
    @Override
    public String getClassDebugName() {
        return "getAsMarker";
    }

    @Override
    public UnitInstance getSingleRaw(UnitType y2) {
        UnitType y3 = y2.player.t;
        y3.cg = y2.cg;
        y3.eo = y2.eo;
        y3.ep = y2.ep;
        y3.eq = y2.eq;
        return y3;
    }
}
