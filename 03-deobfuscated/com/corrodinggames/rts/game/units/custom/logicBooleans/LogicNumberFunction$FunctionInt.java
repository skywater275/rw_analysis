/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction$LogicNumberFunctionRawArgs;
import com.corrodinggames.rts.game.units.UnitType;

public class LogicNumberFunction$FunctionInt
extends LogicNumberFunction$LogicNumberFunctionRawArgs {
    @Override
    public String getName() {
        return "Int";
    }

    @Override
    public float readNumber(UnitType y2) {
        float f = this.value.readNumber(y2);
        f = this.doFunction(f);
        return f;
    }

    @Override
    public float doFunction(float f) {
        return (int)f;
    }
}
