/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.CompareJoinerBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$StaticString;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$StringCast;
import com.corrodinggames.rts.game.units.UnitType;

public final class CompareJoinerBoolean$StringJoinerBoolean
extends CompareJoinerBoolean {
    @Override
    public String type() {
        return "+";
    }

    @Override
    public LogicBoolean validateAndOptimize(String string, String string2, String string3, LogicBooleanLoader$LogicBooleanContext logicBooleanContext, boolean bl) {
        int n;
        for (n = 0; n < this.children.length; ++n) {
            if (this.children[n].getReturnType() == LogicBoolean$ReturnType.string) continue;
            this.children[n] = LogicString$StringCast.createStringCast(this.children[n]);
        }
        n = 1;
        for (LogicBoolean logicBoolean : this.children) {
            if (logicBoolean instanceof LogicString$StaticString) continue;
            n = 0;
        }
        if (n != 0) {
            String string4 = this.readString(null);
            return new LogicString$StaticString(string4);
        }
        return super.validateAndOptimize(string, string2, string3, logicBooleanContext, bl);
    }

    @Override
    public String readString(UnitType y2) {
        String string = this.children[0].readString(y2);
        for (int i = 1; i < this.children.length; ++i) {
            string = string + this.children[i].readString(y2);
        }
        return string;
    }

    @Override
    public boolean read(UnitType y2) {
        return false;
    }

    @Override
    public LogicBoolean$ReturnType getReturnType() {
        return LogicBoolean$ReturnType.string;
    }
}
