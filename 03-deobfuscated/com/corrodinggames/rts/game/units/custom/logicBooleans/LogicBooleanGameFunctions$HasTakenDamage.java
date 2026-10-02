/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$TimeBoolean;
import com.corrodinggames.rts.game.units.UnitType;

public class LogicBooleanGameFunctions$HasTakenDamage
extends LogicBoolean$TimeBoolean {
    @Override
    public int getTime(UnitType y2) {
        return y2.bs;
    }

    @Override
    public String getName() {
        return "HasTakenDamage";
    }
}
