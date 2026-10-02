/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.UnitType;

public class UnitReference$SelfUnitReference
extends UnitReference {
    @Override
    public UnitInstance getSingleRaw(UnitType y2) {
        return y2;
    }

    @Override
    public String getClassDebugName() {
        return "self";
    }
}
