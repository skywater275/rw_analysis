/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.l;

public class LogicBooleanGameFunctions$GameMapWidthBoolean
extends LogicNumberFunction {
    @Override
    public String getName() {
        return "game.mapWidth";
    }

    @Override
    public float readNumber(y y2) {
        l l2 = l.B();
        return l2.bL.i();
    }
}
