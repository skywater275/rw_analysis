/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;

public abstract class CompareJoinerBoolean$CompareNumbers
extends CompareJoinerBoolean {
    @Override
    public LogicBoolean validateAndOptimize(String string, String string2, String string3, LogicBooleanLoader$LogicBooleanContext logicBooleanContext, boolean bl) {
        boolean bl2 = true;
        for (LogicBoolean logicBoolean : this.children) {
            if (logicBoolean.getReturnType() != LogicBoolean$ReturnType.number) {
                throw new BooleanParseException("Non-number type while using " + this.type());
            }
            if (logicBoolean instanceof LogicBoolean$StaticValueBoolean) continue;
            bl2 = false;
        }
        if (bl2) {
            // empty if block
        }
        return super.validateAndOptimize(string, string2, string3, logicBooleanContext, bl);
    }
}
