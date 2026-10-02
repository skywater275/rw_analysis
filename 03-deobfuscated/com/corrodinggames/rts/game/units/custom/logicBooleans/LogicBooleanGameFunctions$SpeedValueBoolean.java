/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GameUtils;

public class LogicBooleanGameFunctions$SpeedValueBoolean
extends LogicBoolean$AbstractNumberBoolean {
    @Override
    public String getName() {
        return "Speed";
    }

    @Override
    public float getValue(UnitType y2) {
        if (y2.bi()) {
            float f2 = GameUtils.b(0.0f, 0.0f, y2.cc, y2.cd);
            return f2;
        }
        float f3 = y2.cf;
        if (f3 < 0.0f) {
            return -f3;
        }
        return f3;
    }

    @Override
    public float getMaxValue(UnitType y2) {
        return 2.14748365E9f;
    }
}
