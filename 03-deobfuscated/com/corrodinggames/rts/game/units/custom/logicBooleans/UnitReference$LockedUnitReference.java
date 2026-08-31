/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.UnitType;

public class UnitReference$LockedUnitReference
extends UnitReference {
    UnitInstance target;

    public UnitReference$LockedUnitReference(UnitInstance am2) {
        this.target = am2;
    }

    @Override
    public UnitInstance getSingleRaw(UnitType y2) {
        return this.target;
    }

    @Override
    public String getClassDebugName() {
        return "unit";
    }
}
