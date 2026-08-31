/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.y;

public final class LogicBooleanGameFunctions$ShieldBoolean
extends LogicBoolean$AbstractNumberBoolean {
    @Override
    public String getName() {
        return "Shield";
    }

    @Override
    public float getValue(y y2) {
        return y2.cx;
    }

    @Override
    public float getMaxValue(y y2) {
        return y2.cA;
    }
}
