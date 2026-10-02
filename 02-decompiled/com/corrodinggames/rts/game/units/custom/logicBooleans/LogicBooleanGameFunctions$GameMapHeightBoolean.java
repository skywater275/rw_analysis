/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.l;

public class LogicBooleanGameFunctions$GameMapHeightBoolean
extends LogicNumberFunction {
    @Override
    public String getName() {
        return "game.mapHeight";
    }

    @Override
    public float readNumber(y y2) {
        l l2 = l.B();
        return l2.bL.j();
    }
}
