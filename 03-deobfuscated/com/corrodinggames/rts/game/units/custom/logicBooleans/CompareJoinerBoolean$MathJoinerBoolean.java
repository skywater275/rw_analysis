/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$MathAddJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean$StringJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.UnitType;

public abstract class CompareJoinerBoolean$MathJoinerBoolean
extends CompareJoinerBoolean {
    @Override
    public boolean read(UnitType y2) {
        return false;
    }

    @Override
    public LogicBoolean$ReturnType getReturnType() {
        return LogicBoolean$ReturnType.number;
    }

    @Override
    public LogicBoolean validateAndOptimize(String string, String string2, String string3, LogicBooleanLoader$LogicBooleanContext logicBooleanContext, boolean bl) {
        boolean bl2;
        if (this instanceof CompareJoinerBoolean$MathAddJoinerBoolean) {
            bl2 = false;
            for (LogicBoolean logicBoolean : this.children) {
                if (logicBoolean.getReturnType() != LogicBoolean$ReturnType.string) continue;
                bl2 = true;
            }
            if (bl2) {
                CompareJoinerBoolean$StringJoinerBoolean compareJoinerBoolean$StringJoinerBoolean = new CompareJoinerBoolean$StringJoinerBoolean();
                compareJoinerBoolean$StringJoinerBoolean.children = this.children;
                return compareJoinerBoolean$StringJoinerBoolean.validateAndOptimize(string, string2, string3, logicBooleanContext, bl);
            }
        }
        bl2 = true;
        for (LogicBoolean logicBoolean : this.children) {
            if (!(logicBoolean instanceof LogicBoolean$StaticValueBoolean)) {
                bl2 = false;
            }
            if (logicBoolean.getReturnType() == LogicBoolean$ReturnType.number) continue;
            throw new BooleanParseException("Unexpected type while using " + this.type() + " got: " + logicBoolean.getReturnType().name());
        }
        if (bl2) {
            float f = this.readNumber(null);
            return new LogicBoolean$StaticValueBoolean(f);
        }
        if (bl) {
            throw new BooleanParseException("Cannot return number here, expected boolean");
        }
        return super.validateAndOptimize(string, string2, string3, logicBooleanContext, bl);
    }
}
