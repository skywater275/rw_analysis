/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$LogicBooleanCommon;
import com.corrodinggames.rts.game.units.y;

public final class LogicBooleanGameFunctions$TeamVictoryBoolean
extends LogicBoolean$LogicBooleanCommon {
    @Override
    public String getName() {
        return "teamVictory";
    }

    @Override
    public boolean read(y y2) {
        return y2.bX.H;
    }
}
