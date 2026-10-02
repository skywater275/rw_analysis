/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.y;

public final class LogicBooleanGameFunctions$EnergyIncludingQueuedBoolean
extends LogicBoolean$AbstractNumberBoolean {
    @Override
    public String getName() {
        return "EnergyIncludingQueued";
    }

    @Override
    public float getValue(y y2) {
        float f2 = y2.cB;
        b b2 = y2.by();
        return f2 += b2.c;
    }

    @Override
    public float getMaxValue(y y2) {
        return y2.bd();
    }
}
