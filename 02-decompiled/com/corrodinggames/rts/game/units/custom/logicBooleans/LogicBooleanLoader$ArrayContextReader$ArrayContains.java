/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ArrayContextReader$ArrayFunction;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.utility.al;
import java.util.ArrayList;

public class LogicBooleanLoader$ArrayContextReader$ArrayContains
extends LogicBooleanLoader$ArrayContextReader$ArrayFunction {
    public LogicBoolean value;
    LogicBoolean targetArray;
    public LogicBoolean$ReturnType elementType;

    @Override
    public LogicBooleanLoader.LogicBooleanContext createContext() {
        return LogicBooleanLoader.voidNumberContextReader;
    }

    @Override
    public void setArrayTarget(LogicBoolean logicBoolean) {
        this.targetArray = logicBoolean;
        LogicBoolean$ReturnType logicBoolean$ReturnType = logicBoolean.getReturnType();
        this.elementType = LogicBoolean$ReturnType.getArrayBaseType(logicBoolean$ReturnType);
        if (this.value.getReturnType() != this.elementType) {
            throw new BooleanParseException("Expected value of type: " + (Object)((Object)this.elementType) + " (got:" + (Object)((Object)this.value.getReturnType()) + ")");
        }
    }

    @Override
    public void setArgumentsRaw(String string, l l2, String string2) {
        if (string == null || "".equals(string)) {
            this.validateNumberOfArguments(0);
            return;
        }
        ArrayList arrayList = al.a(string, ",", false);
        this.validateNumberOfArguments(arrayList.size());
        this.value = LogicBooleanLoader.parseBooleanBlock(l2, (String)arrayList.get(0), false);
        if (this.value == null) {
            throw new BooleanParseException("Expected non-null argument");
        }
    }

    public void validateNumberOfArguments(int n) {
        if (n != 1) {
            throw new BooleanParseException("Expected 1 argument");
        }
    }

    @Override
    public void validate(String string, String string2, String string3, LogicBooleanLoader.LogicBooleanContext logicBooleanContext, boolean bl) {
        super.validate(string, string2, string3, logicBooleanContext, bl);
        if (this.value == null) {
            throw new BooleanParseException("No value");
        }
    }

    @Override
    public LogicBoolean$ReturnType getReturnType() {
        return LogicBoolean$ReturnType.bool;
    }

    @Override
    public boolean read(y y2) {
        int n = LogicBooleanLoader$ArrayContextReader$ArrayContains.indexOf(y2, this.targetArray, this.value);
        return n != -1;
    }

    public static final int indexOf(y y2, LogicBoolean logicBoolean, LogicBoolean logicBoolean2) {
        block8: {
            LogicBoolean$ReturnType logicBoolean$ReturnType;
            int n2;
            block9: {
                block7: {
                    n2 = logicBoolean.getArraySize(y2);
                    logicBoolean$ReturnType = logicBoolean2.getReturnType();
                    if (logicBoolean$ReturnType != LogicBoolean$ReturnType.bool) break block7;
                    boolean bl = logicBoolean2.read(y2);
                    for (int i = 0; i < n2; ++i) {
                        LogicBoolean logicBoolean3 = logicBoolean.readArrayElement(y2, i);
                        if (logicBoolean3.read(y2) != bl) continue;
                        return i;
                    }
                    break block8;
                }
                if (logicBoolean$ReturnType != LogicBoolean$ReturnType.number) break block9;
                float f2 = logicBoolean2.readNumber(y2);
                for (int i = 0; i < n2; ++i) {
                    LogicBoolean logicBoolean4 = logicBoolean.readArrayElement(y2, i);
                    float f3 = logicBoolean4.readNumber(y2);
                    if (!f.j(f2, f3)) continue;
                    return i;
                }
                break block8;
            }
            if (logicBoolean$ReturnType != LogicBoolean$ReturnType.unit) break block8;
            am am2 = logicBoolean2.readUnit(y2);
            if (VariableScope.isMarker(am2)) {
                if (am2 == null) {
                    return -1;
                }
                float f4 = am2.eo;
                float f5 = am2.ep;
                int n3 = am2.bX.k;
                am2 = null;
                for (int i = 0; i < n2; ++i) {
                    LogicBoolean logicBoolean5 = logicBoolean.readArrayElement(y2, i);
                    am am3 = logicBoolean5.readUnit(y2);
                    if (am3 == null || !f.j(f4, am3.eo) || !f.j(f5, am3.ep) || n3 != am3.bX.k) continue;
                    return i;
                }
            } else {
                for (int i = 0; i < n2; ++i) {
                    LogicBoolean logicBoolean6 = logicBoolean.readArrayElement(y2, i);
                    am am4 = logicBoolean6.readUnit(y2);
                    if (am2 != am4) continue;
                    return i;
                }
            }
        }
        return -1;
    }

    public String getName() {
        return "contains";
    }

    @Override
    public String getMatchFailReasonForPlayer(y y2) {
        String string;
        String string2 = "";
        if (this.targetArray != null) {
            string2 = string2 + this.targetArray.getMatchFailReasonForPlayer(y2);
        }
        if ((string = null) != null) {
            string = this.value.getMatchFailReasonForPlayer(y2);
        }
        string2 = string2 + "." + this.getName() + "(" + string + ")";
        string2 = string2 + "=" + this.valueToStringDebug(y2);
        return string2;
    }
}
