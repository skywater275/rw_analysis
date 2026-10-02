/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.custom.resources.CustomActionBase;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.resources.ResourceComponent;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.UnitType;

public final class LogicBooleanGameFunctions$AmmoIncludingQueuedBoolean
extends LogicBoolean$AbstractNumberBoolean {
    @Override
    public String getName() {
        return "AmmoIncludingQueued";
    }

    @Override
    public float getValue(UnitType y2) {
        int n2 = y2.cE;
        CustomActionBase b2 = y2.by();
        return n2 += b2.f;
    }

    @Override
    public float getMaxValue(UnitType y2) {
        return 2.14748365E9f;
    }
}
