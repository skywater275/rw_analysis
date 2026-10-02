/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.UnitType;

public final class LogicBooleanGameFunctions$MaxShieldBoolean
extends LogicBoolean$AbstractNumberBoolean {
    @Override
    public String getName() {
        return "maxShield";
    }

    @Override
    public float getValue(UnitType y2) {
        return y2.cA;
    }

    @Override
    public float getMaxValue(UnitType y2) {
        return 2.14748365E9f;
    }
}
