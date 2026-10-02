/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference$PlaceholderUnitReference;
import com.corrodinggames.rts.game.units.y;

public class UnitReference$GetAsMarker
extends UnitReference$PlaceholderUnitReference {
    @Override
    public String getClassDebugName() {
        return "getAsMarker";
    }

    @Override
    public am getSingleRaw(y y2) {
        y y3 = y2.bX.t;
        y3.cg = y2.cg;
        y3.eo = y2.eo;
        y3.ep = y2.ep;
        y3.eq = y2.eq;
        return y3;
    }
}
