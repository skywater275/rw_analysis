/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.R;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.UnitType;

public class UnitReference$AttackingReference
extends UnitReference {
    @Override
    public UnitInstance getSingleRaw(UnitType y2) {
        UnitInstance am2 = y2.R;
        return am2;
    }

    @Override
    public String getClassDebugName() {
        return "Attacking";
    }
}
