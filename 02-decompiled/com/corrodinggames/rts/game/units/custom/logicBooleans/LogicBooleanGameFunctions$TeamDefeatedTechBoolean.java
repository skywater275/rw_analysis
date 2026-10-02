/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$LogicBooleanCommon;
import com.corrodinggames.rts.game.units.y;

public final class LogicBooleanGameFunctions$TeamDefeatedTechBoolean
extends LogicBoolean$LogicBooleanCommon {
    @Override
    public String getName() {
        return "teamDefeatedTech";
    }

    @Override
    public boolean read(y y2) {
        return y2.bX.F;
    }
}
