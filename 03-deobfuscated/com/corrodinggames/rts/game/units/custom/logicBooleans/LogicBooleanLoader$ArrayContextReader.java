/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ArrayContextReader$ArrayContains;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ArrayContextReader$ArrayFunction;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ArrayContextReader$ArrayGet;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ArrayContextReader$ArrayGetUnit;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ArrayContextReader$ArraySize;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContextWithDefault;
import java.util.HashMap;
import java.util.Locale;

public final class LogicBooleanLoader$ArrayContextReader
extends LogicBooleanLoader$LogicBooleanContextWithDefault {
    LogicBoolean$ReturnType arrayType;
    static HashMap arrayFunctions = new HashMap();
    static HashMap arrayFunctionsUnit;

    public LogicBooleanLoader$ArrayContextReader(LogicBoolean$ReturnType returnType) {
        this.arrayType = returnType;
    }

    public static void addContextFunction(HashMap hashMap, LogicBoolean logicBoolean, String ... stringArray) {
        for (String string : stringArray) {
            if (hashMap.get(string = string.toLowerCase(Locale.ROOT)) != null) {
                throw new RuntimeException("logicBoolean: " + string + " already exists");
            }
            hashMap.put(string, logicBoolean);
        }
    }

    @Override
    public LogicBoolean parseNextElementInChain(String string, ModUnitRegistry l2, String string2, boolean bl, String string3, String string4, LogicBoolean logicBoolean) {
        LogicBoolean logicBoolean2 = this.arrayType == LogicBoolean$ReturnType.unitArray ? LogicBooleanLoader$ArrayContextReader.defaultParseNextElementInChain(this, null, l2, string2, bl, string3, string4, logicBoolean, arrayFunctionsUnit) : LogicBooleanLoader$ArrayContextReader.defaultParseNextElementInChain(this, null, l2, string2, bl, string3, string4, logicBoolean, arrayFunctions);
        if (logicBoolean2 == null) {
            return null;
        }
        if (!(logicBoolean2 instanceof LogicBooleanLoader$ArrayContextReader$ArrayFunction)) {
            throw new RuntimeException("Expected array function.");
        }
        LogicBooleanLoader$ArrayContextReader$ArrayFunction logicBooleanLoader$ArrayContextReader$ArrayFunction = (LogicBooleanLoader$ArrayContextReader$ArrayFunction)logicBoolean2;
        logicBooleanLoader$ArrayContextReader$ArrayFunction.setArrayTarget(logicBoolean);
        return logicBooleanLoader$ArrayContextReader$ArrayFunction;
    }

    static {
        LogicBooleanLoader$ArrayContextReader.addContextFunction(arrayFunctions, new LogicBooleanLoader$ArrayContextReader$ArrayGet(), "get");
        LogicBooleanLoader$ArrayContextReader.addContextFunction(arrayFunctions, new LogicBooleanLoader$ArrayContextReader$ArraySize(), "size");
        LogicBooleanLoader$ArrayContextReader.addContextFunction(arrayFunctions, new LogicBooleanLoader$ArrayContextReader$ArraySize(), "length");
        LogicBooleanLoader$ArrayContextReader.addContextFunction(arrayFunctions, new LogicBooleanLoader$ArrayContextReader$ArrayContains(), "contains");
        arrayFunctionsUnit = new HashMap();
        LogicBooleanLoader$ArrayContextReader.addContextFunction(arrayFunctionsUnit, new LogicBooleanLoader$ArrayContextReader$ArrayGetUnit(), "get");
        LogicBooleanLoader$ArrayContextReader.addContextFunction(arrayFunctionsUnit, new LogicBooleanLoader$ArrayContextReader$ArraySize(), "size");
        LogicBooleanLoader$ArrayContextReader.addContextFunction(arrayFunctionsUnit, new LogicBooleanLoader$ArrayContextReader$ArraySize(), "length");
        LogicBooleanLoader$ArrayContextReader.addContextFunction(arrayFunctionsUnit, new LogicBooleanLoader$ArrayContextReader$ArrayContains(), "contains");
    }
}
