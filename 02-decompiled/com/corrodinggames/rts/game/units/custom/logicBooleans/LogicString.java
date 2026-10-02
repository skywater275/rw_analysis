/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.y;

public abstract class LogicString
extends LogicBoolean {
    @Override
    public LogicBoolean$ReturnType getReturnType() {
        return LogicBoolean$ReturnType.string;
    }

    @Override
    public void validate(String string, String string2, String string3, LogicBooleanLoader.LogicBooleanContext logicBooleanContext, boolean bl) {
        super.validate(string, string2, string3, logicBooleanContext, bl);
    }

    @Override
    public String getMatchFailReasonForPlayer(y y2) {
        return "TEXT";
    }

    @Override
    public boolean read(y y2) {
        return false;
    }

    @Override
    public float readNumber(y y2) {
        return 0.0f;
    }

    public static String arrayToString(y y2, LogicBoolean logicBoolean) {
        int n = logicBoolean.getArraySize(y2);
        String string = "[";
        for (int i = 0; i < n; ++i) {
            LogicBoolean logicBoolean2;
            if (i > 12) {
                string = string + ",...";
                break;
            }
            if (i != 0) {
                string = string + ",";
            }
            if ((logicBoolean2 = logicBoolean.readArrayElement(y2, i)) == null) continue;
            string = string + logicBoolean2.valueToStringDebug(y2);
        }
        string = string + "]";
        return string;
    }

    public static String arraySummaryToString(y y2, LogicBoolean logicBoolean) {
        int n = logicBoolean.getArraySize(y2);
        LogicBoolean$ReturnType logicBoolean$ReturnType = logicBoolean.getReturnType();
        LogicBoolean$ReturnType logicBoolean$ReturnType2 = LogicBoolean$ReturnType.getArrayBaseType(logicBoolean$ReturnType);
        String string = LogicBoolean$ReturnType.toUserString(logicBoolean$ReturnType2);
        String string2 = string + "[" + n + "]";
        return string2;
    }
}
