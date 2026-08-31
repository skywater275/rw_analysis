/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.UnitType;

public class UnitReference$LastDamagedByUnitReference
extends UnitReference {
    @Override
    public UnitInstance getSingleRaw(UnitType y2) {
        UnitInstance am2 = y2.bt;
        if (am2 == null || am2.isDead) {
            return null;
        }
        return am2;
    }

    @Override
    public String getClassDebugName() {
        return "lastDamagedBy";
    }
}
