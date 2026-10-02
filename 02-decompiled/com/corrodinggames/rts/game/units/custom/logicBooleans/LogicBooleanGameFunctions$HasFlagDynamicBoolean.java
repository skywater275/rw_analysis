/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanGameFunctions$HasFlagBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.y;

public class LogicBooleanGameFunctions$HasFlagDynamicBoolean
extends LogicBoolean {
    @LogicBoolean.Parameter(type=LogicBoolean.ReturnType.number, positional=0)
    public LogicBoolean id;

    @Override
    public LogicBoolean validateAndOptimize(String string, String string2, String string3, LogicBooleanLoader.LogicBooleanContext logicBooleanContext, boolean bl) {
        this.validate(string, string2, string3, logicBooleanContext, bl);
        if (this.id == null) {
            throw new BooleanParseException("Flag id must be set");
        }
        Float f = LogicBooleanGameFunctions$HasFlagDynamicBoolean.getStaticNumber(this.id);
        if (f != null) {
            LogicBooleanGameFunctions$HasFlagBoolean logicBooleanGameFunctions$HasFlagBoolean = new LogicBooleanGameFunctions$HasFlagBoolean();
            logicBooleanGameFunctions$HasFlagBoolean.id((int)f.floatValue());
            return logicBooleanGameFunctions$HasFlagBoolean;
        }
        return this;
    }

    @Override
    public boolean read(y y2) {
        int n2;
        y y3 = LogicBooleanGameFunctions$HasFlagDynamicBoolean.getParameterContext(y2);
        int n3 = (int)this.id.readNumber(y3);
        return n3 >= 0 && n3 <= 31 && LogicBooleanGameFunctions$HasFlagBoolean.isFlagSet(y2.cF, n2 = 1 << n3);
    }

    @Override
    public String getMatchFailReasonForPlayer(y y2) {
        String string = "HasFlag";
        y y3 = LogicBooleanGameFunctions$HasFlagDynamicBoolean.getParameterContext(y2);
        string = string + "(id:" + this.id.getMatchFailReasonForPlayer(y3) + ")";
        return string;
    }
}
