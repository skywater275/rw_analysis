/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.UnitType;

public final class LogicBooleanGameFunctions$HpBoolean
extends LogicBoolean$AbstractNumberBoolean {
    @Override
    public String getName() {
        return "Hp";
    }

    @Override
    public float getValue(UnitType y2) {
        return y2.cu;
    }

    @Override
    public float getMaxValue(UnitType y2) {
        return y2.cv;
    }
}
