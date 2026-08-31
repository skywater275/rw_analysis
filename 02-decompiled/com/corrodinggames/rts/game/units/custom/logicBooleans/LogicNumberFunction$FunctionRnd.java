/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.utility.am;

public class LogicNumberFunction$FunctionRnd
extends LogicNumberFunction {
    @LogicBoolean.Parameter(type=LogicBoolean.ReturnType.number, positional=0, required=true)
    public LogicBoolean min;
    @LogicBoolean.Parameter(type=LogicBoolean.ReturnType.number, positional=1, required=true)
    public LogicBoolean max;
    int randomIndex;

    @Override
    public void forMeta(l l2) {
        if (l2 == null) {
            throw new am("FunctionRnd requires metadata");
        }
        ++l2.S;
        this.randomIndex = l2.S;
    }

    @Override
    public String getName() {
        return "Rnd";
    }

    @Override
    public float readNumber(y y2) {
        float f2 = this.min.readNumber(y2);
        float f3 = this.max.readNumber(y2);
        int n2 = 0;
        if (y2 != null) {
            n2 = y2.bC;
        }
        return f.b(f2, f3, this.randomIndex + n2);
    }
}
