/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class LogicBooleanGameFunctions$GameMapWidthBoolean
extends LogicNumberFunction {
    @Override
    public String getName() {
        return "game.mapWidth";
    }

    @Override
    public float readNumber(UnitType y2) {
        GlobalState l2 = GlobalState.B();
        return l2.bL.i();
    }
}
