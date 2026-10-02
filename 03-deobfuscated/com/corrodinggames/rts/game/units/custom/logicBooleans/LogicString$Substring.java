/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$StringCast;
import com.corrodinggames.rts.game.units.UnitType;

public class LogicString$Substring
extends LogicString {
    @LogicBoolean$Parameter(required=true, positional=0)
    public LogicBoolean text;
    @LogicBoolean$Parameter(type=LogicBoolean$ReturnType.number, required=true, positional=1)
    public LogicBoolean start;
    @LogicBoolean$Parameter(type=LogicBoolean$ReturnType.number, required=true, positional=2)
    public LogicBoolean end;

    @Override
    public LogicBoolean validateAndOptimize(String string, String string2, String string3, LogicBooleanLoader$LogicBooleanContext logicBooleanContext, boolean bl) {
        if (this.text == null) {
            throw new BooleanParseException("Expected argument text");
        }
        if (this.text.getReturnType() != LogicBoolean$ReturnType.string) {
            this.text = LogicString$StringCast.createStringCast(this.text);
        }
        return super.validateAndOptimize(string, string2, string3, logicBooleanContext, bl);
    }

    @Override
    public String readString(UnitType y2) {
        String string = this.text.readString(y2);
        int n = (int)this.start.readNumber(y2);
        int n2 = (int)this.end.readNumber(y2);
        if (n < 0) {
            n = 0;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        if (n > string.length()) {
            n = string.length();
        }
        if (n2 > string.length()) {
            n2 = string.length();
        }
        if (n2 < n) {
            n2 = n;
        }
        string = string.substring(n, n2);
        return string;
    }
}
