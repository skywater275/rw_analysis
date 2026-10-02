/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ArrayContextReader$ArrayFunction;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.utility.al;
import java.util.ArrayList;

public class LogicBooleanLoader$ArrayContextReader$ArrayGet
extends LogicBooleanLoader$ArrayContextReader$ArrayFunction {
    @LogicBoolean$Parameter(type=LogicBoolean$ReturnType.number)
    public LogicBoolean index;
    LogicBoolean targetArray;
    public LogicBoolean$ReturnType elementType;

    @Override
    public LogicBooleanLoader$LogicBooleanContext createContext() {
        return LogicBooleanLoader.voidArrayContextReader;
    }

    @Override
    public void setArrayTarget(LogicBoolean logicBoolean) {
        this.targetArray = logicBoolean;
        LogicBoolean$ReturnType logicBoolean$ReturnType = logicBoolean.getReturnType();
        this.elementType = LogicBoolean$ReturnType.getArrayBaseType(logicBoolean$ReturnType);
    }

    @Override
    public void setArgumentsRaw(String string, ModUnitRegistry l2, String string2) {
        if (string == null || "".equals(string)) {
            this.validateNumberOfArguments(0);
            return;
        }
        ArrayList arrayList = al.a(string, ",", false);
        this.validateNumberOfArguments(arrayList.size());
        this.index = LogicBooleanLoader.parseNumberBlock(l2, (String)arrayList.get(0));
        if (this.index == null) {
            throw new BooleanParseException("Expected non-null argument");
        }
    }

    public void validateNumberOfArguments(int n) {
        if (n != 1) {
            throw new BooleanParseException("Expected 1 argument");
        }
    }

    @Override
    public void validate(String string, String string2, String string3, LogicBooleanLoader$LogicBooleanContext logicBooleanContext, boolean bl) {
        super.validate(string, string2, string3, logicBooleanContext, bl);
        if (this.index == null) {
            throw new BooleanParseException("No array index");
        }
    }

    @Override
    public LogicBoolean$ReturnType getReturnType() {
        return this.elementType;
    }

    LogicBoolean readElement(UnitType y2) {
        int n2 = (int)this.index.readNumber(y2);
        if (this.targetArray == null) {
            com.corrodinggames.rts.gameFramework.GlobalState.b("ArrayGet readElement targetArray==null");
            return null;
        }
        LogicBoolean logicBoolean = this.targetArray.readArrayElement(y2, n2);
        return logicBoolean;
    }

    @Override
    public boolean read(UnitType y2) {
        LogicBoolean logicBoolean = this.readElement(y2);
        if (logicBoolean == null) {
            return false;
        }
        return logicBoolean.read(y2);
    }

    @Override
    public float readNumber(UnitType y2) {
        LogicBoolean logicBoolean = this.readElement(y2);
        if (logicBoolean == null) {
            return 0.0f;
        }
        return logicBoolean.readNumber(y2);
    }

    @Override
    public UnitInstance readUnit(UnitType y2) {
        LogicBoolean logicBoolean = this.readElement(y2);
        if (logicBoolean == null) {
            return null;
        }
        return logicBoolean.readUnit(y2);
    }

    public String getName() {
        return "get";
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        LogicBoolean logicBoolean = this.readElement(y2);
        int n2 = (int)this.index.readNumber(y2);
        String string = "";
        if (this.targetArray != null) {
            string = string + this.targetArray.getMatchFailReasonForPlayer(y2);
        }
        string = string + "." + this.getName() + "(" + n2 + ")";
        string = logicBoolean == null ? string + "=null" : string + "=" + logicBoolean.getMatchFailReasonForPlayer(y2);
        return string;
    }
}
