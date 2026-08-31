/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction;
import com.corrodinggames.rts.game.units.y;

public class LogicNumberFunction$FunctionLength
extends LogicNumberFunction {
    @LogicBoolean.Parameter(type=LogicBoolean.ReturnType.string, positional=0, required=true)
    public LogicBoolean a;

    @Override
    public String getName() {
        return "Length";
    }

    @Override
    public float readNumber(y y2) {
        String string = this.a.readString(y2);
        if (string == null) {
            return 0.0f;
        }
        return string.length();
    }
}
