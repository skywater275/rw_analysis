/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import java.util.HashMap;
import java.util.Locale;

public abstract class LogicBooleanLoader$LogicBooleanContextWithDefault
implements LogicBooleanLoader$LogicBooleanContext {
    @Override
    public LogicBoolean parseNextElementInChain(String string, l l2, String string2, boolean bl, String string3, String string4, LogicBoolean logicBoolean) {
        return LogicBooleanLoader$LogicBooleanContextWithDefault.defaultParseNextElementInChain(this, string, l2, string2, bl, string3, string4, logicBoolean, LogicBoolean.booleans);
    }

    public static LogicBoolean defaultParseNextElementInChain(LogicBooleanLoader$LogicBooleanContext logicBooleanLoader$LogicBooleanContext, String string, l l2, String string2, boolean bl, String string3, String string4, LogicBoolean logicBoolean, HashMap hashMap) {
        LogicBoolean logicBoolean2;
        String string5;
        String string6;
        UnitReference unitReference = UnitReference.parseSingleUnitReferenceElement(l2, string2);
        if (unitReference != null) {
            return unitReference;
        }
        int n = string2.indexOf("(");
        if (n == -1) {
            string6 = string2.toLowerCase(Locale.ROOT);
            string5 = "";
        } else {
            string6 = string2.substring(0, n).trim().toLowerCase(Locale.ROOT);
            string5 = string2.substring(n);
        }
        if (string != null) {
            string6 = string + string6;
        }
        if ((logicBoolean2 = (LogicBoolean)hashMap.get(string6)) != null) {
            String string7 = LogicBooleanLoader.fixArguments(string5);
            LogicBoolean logicBoolean3 = logicBoolean2.with(l2, string7, string6);
            logicBoolean3 = logicBoolean3.validateAndOptimize(string6, string7, string3, logicBooleanLoader$LogicBooleanContext, bl);
            return logicBoolean3;
        }
        String string8 = "";
        if (hashMap != null && hashMap.size() < 8 && hashMap.size() > 0) {
            string8 = " (Allowed functions: ";
            boolean bl2 = true;
            for (String string9 : hashMap.keySet()) {
                if (!bl2) {
                    string8 = string8 + ", ";
                }
                bl2 = false;
                string8 = string8 + string9;
            }
            string8 = string8 + ")";
        }
        if (string4 != null) {
            throw new RuntimeException("Unknown function or field:'" + string2 + "' in '" + string4 + "'" + string8);
        }
        throw new RuntimeException("Unknown function or field:'" + string2 + "'" + string8);
    }
}
