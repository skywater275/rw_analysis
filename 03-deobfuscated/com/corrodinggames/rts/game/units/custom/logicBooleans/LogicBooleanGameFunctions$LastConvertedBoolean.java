/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$TimeBoolean;
import com.corrodinggames.rts.game.units.UnitType;

public final class LogicBooleanGameFunctions$LastConvertedBoolean
extends LogicBoolean$TimeBoolean {
    @Override
    public String getName() {
        return "LastConverted";
    }

    @Override
    public int getTime(UnitType y2) {
        return y2.bB;
    }
}
