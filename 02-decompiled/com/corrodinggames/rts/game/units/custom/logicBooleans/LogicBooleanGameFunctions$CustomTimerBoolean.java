/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$TimeBoolean;
import com.corrodinggames.rts.game.units.y;

public final class LogicBooleanGameFunctions$CustomTimerBoolean
extends LogicBoolean$TimeBoolean {
    @Override
    public String getName() {
        return "CustomTimer";
    }

    @Override
    public int getTime(y y2) {
        return y2.bA;
    }
}
