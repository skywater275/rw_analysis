/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$LogicNumberOnly;
import com.corrodinggames.rts.game.units.UnitType;

public class LogicBooleanGameFunctions$ThisActionRepeatedCount
extends LogicBoolean$LogicNumberOnly {
    @Override
    public float readNumber(UnitType y2) {
        int n2 = CustomUnitType.selectionIndex;
        return n2;
    }

    @Override
    public String getName() {
        return "ThisActionRepeatedCount";
    }
}
