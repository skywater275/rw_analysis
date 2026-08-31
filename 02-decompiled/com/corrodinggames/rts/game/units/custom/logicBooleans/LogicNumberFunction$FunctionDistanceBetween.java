/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;

public class LogicNumberFunction$FunctionDistanceBetween
extends LogicNumberFunction {
    @LogicBoolean.Parameter(type=LogicBoolean.ReturnType.unit, positional=0, required=true)
    public LogicBoolean unit1;
    @LogicBoolean.Parameter(type=LogicBoolean.ReturnType.unit, positional=1, required=true)
    public LogicBoolean unit2;

    @Override
    public String getName() {
        return "DistanceBetween";
    }

    @Override
    public float readNumber(y y2) {
        am am2 = this.unit1.readUnit(y2);
        if (am2 == null) {
            return 0.0f;
        }
        float f2 = am2.eo;
        float f3 = am2.ep;
        am2 = this.unit2.readUnit(y2);
        if (am2 == null) {
            return 0.0f;
        }
        float f4 = am2.eo;
        float f5 = am2.ep;
        return f.b(f2, f3, f4, f5);
    }
}
