/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.y;

public final class LogicBooleanGameFunctions$KillsBoolean
extends LogicBoolean$AbstractNumberBoolean {
    @Override
    public String getName() {
        return "Kills";
    }

    @Override
    public float getValue(y y2) {
        return y2.cU;
    }

    @Override
    public float getMaxValue(y y2) {
        return 2.14748365E9f;
    }
}
