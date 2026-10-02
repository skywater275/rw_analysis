/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString;
import com.corrodinggames.rts.game.units.y;
import java.util.Locale;

public class LogicString$LowerString
extends LogicString {
    @LogicBoolean.Parameter(required=true, positional=0)
    public LogicBoolean text;

    @Override
    public LogicBoolean validateAndOptimize(String string, String string2, String string3, LogicBooleanLoader.LogicBooleanContext logicBooleanContext, boolean bl) {
        if (this.text == null) {
            throw new BooleanParseException("Expected argument text");
        }
        if (this.text.getReturnType() != LogicBoolean$ReturnType.string) {
            throw new BooleanParseException("Expected string argument");
        }
        return super.validateAndOptimize(string, string2, string3, logicBooleanContext, bl);
    }

    @Override
    public String readString(y y2) {
        String string = this.text.readString(y2);
        string = string.toLowerCase(Locale.ROOT);
        return string;
    }
}
