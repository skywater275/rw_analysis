/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.y;

public final class LogicBooleanGameFunctions$AmmoIncludingQueuedBoolean
extends LogicBoolean$AbstractNumberBoolean {
    @Override
    public String getName() {
        return "AmmoIncludingQueued";
    }

    @Override
    public float getValue(y y2) {
        int n2 = y2.cE;
        b b2 = y2.by();
        return n2 += b2.f;
    }

    @Override
    public float getMaxValue(y y2) {
        return 2.14748365E9f;
    }
}
