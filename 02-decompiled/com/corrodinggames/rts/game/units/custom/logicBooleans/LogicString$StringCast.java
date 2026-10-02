/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$StaticString;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.utility.al;
import java.util.ArrayList;

class LogicString$StringCast
extends LogicString {
    LogicBoolean child;
    LogicBoolean$ReturnType sourceType;

    @Override
    public void setArgumentsRaw(String string, l l2, String string2) {
        if (string == null || "".equals(string)) {
            this.validateNumberOfArguments(0);
            return;
        }
        ArrayList arrayList = al.a(string, ",", false);
        this.validateNumberOfArguments(arrayList.size());
        this.child = LogicBooleanLoader.parseBooleanBlock(l2, (String)arrayList.get(0), false);
        if (this.child == null) {
            throw new BooleanParseException("Expected non-null argument");
        }
    }

    public void validateNumberOfArguments(int n) {
        if (n != 1) {
            throw new BooleanParseException("Expected 1 argument");
        }
    }

    public static LogicBoolean createStringCast(LogicBoolean logicBoolean) {
        LogicString$StringCast logicString$StringCast = new LogicString$StringCast();
        logicString$StringCast.child = logicBoolean;
        return logicString$StringCast.validateAndOptimize("cast", "", "", null, false);
    }

    @Override
    public LogicBoolean validateAndOptimize(String string, String string2, String string3, LogicBooleanLoader.LogicBooleanContext logicBooleanContext, boolean bl) {
        this.sourceType = this.child.getReturnType();
        if (this.child instanceof LogicString$StaticString) {
            return this.child;
        }
        if (this.child instanceof LogicBoolean.StaticBoolean) {
            return new LogicString$StaticString(this.readString(null));
        }
        if (this.child instanceof LogicBoolean.StaticValueBoolean) {
            return new LogicString$StaticString(this.readString(null));
        }
        return super.validateAndOptimize(string, string2, string3, logicBooleanContext, bl);
    }

    @Override
    public String readString(y y2) {
        return LogicString$StringCast.castToString(this.sourceType, this.child, y2);
    }

    public static String castToString(LogicBoolean$ReturnType logicBoolean$ReturnType, LogicBoolean logicBoolean, y y2) {
        if (logicBoolean$ReturnType == LogicBoolean$ReturnType.number) {
            float f2 = logicBoolean.readNumber(y2);
            return f.a(f2, 2);
        }
        if (logicBoolean$ReturnType == LogicBoolean$ReturnType.unit) {
            return am.A(logicBoolean.readUnit(y2));
        }
        if (logicBoolean$ReturnType == LogicBoolean$ReturnType.string) {
            String string = logicBoolean.readString(y2);
            return string;
        }
        if (logicBoolean$ReturnType == LogicBoolean$ReturnType.numberArray) {
            String string = LogicString$StringCast.arrayToString(y2, logicBoolean);
            return string;
        }
        if (logicBoolean$ReturnType == LogicBoolean$ReturnType.boolArray) {
            String string = LogicString$StringCast.arrayToString(y2, logicBoolean);
            return string;
        }
        boolean bl = logicBoolean.read(y2);
        return bl ? "true" : "false";
    }
}
