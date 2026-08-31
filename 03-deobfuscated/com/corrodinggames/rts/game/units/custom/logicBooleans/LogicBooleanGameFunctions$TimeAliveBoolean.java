/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$TimeBoolean;
import com.corrodinggames.rts.game.units.UnitType;

public final class LogicBooleanGameFunctions$TimeAliveBoolean
extends LogicBoolean$TimeBoolean {
    @Override
    public String getName() {
        return "TimeAlive";
    }

    @Override
    public int getTime(UnitType y2) {
        return y2.bz;
    }
}
