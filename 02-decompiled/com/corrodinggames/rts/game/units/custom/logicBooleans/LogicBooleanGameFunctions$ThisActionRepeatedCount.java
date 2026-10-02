/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$LogicNumberOnly;
import com.corrodinggames.rts.game.units.y;

public class LogicBooleanGameFunctions$ThisActionRepeatedCount
extends LogicBoolean$LogicNumberOnly {
    @Override
    public float readNumber(y y2) {
        int n2 = j.dO;
        return n2;
    }

    @Override
    public String getName() {
        return "ThisActionRepeatedCount";
    }
}
