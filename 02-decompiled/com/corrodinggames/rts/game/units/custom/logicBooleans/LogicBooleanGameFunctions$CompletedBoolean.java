/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$LogicBooleanCommonLocking;
import com.corrodinggames.rts.game.units.y;

public class LogicBooleanGameFunctions$CompletedBoolean
extends LogicBoolean$LogicBooleanCommonLocking {
    @Override
    public boolean read(y y2) {
        boolean bl = false;
        if (y2.cm >= 1.0f) {
            bl = true;
        }
        return bl;
    }

    @Override
    public String getMatchFailReasonForPlayer(y y2) {
        return "isComplete";
    }
}
