/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticValueBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicNumberFunction;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.utility.al;
import java.util.ArrayList;

abstract class LogicNumberFunction$LogicNumberFunctionRawArgs
extends LogicNumberFunction {
    @LogicBoolean$Parameter(type=LogicBoolean$ReturnType.number)
    public LogicBoolean value;

    LogicNumberFunction$LogicNumberFunctionRawArgs() {
    }

    @Override
    public void setArgumentsRaw(String string, ModUnitRegistry l2, String string2) {
        if (string == null || "".equals(string)) {
            this.validateNumberOfArguments(0);
            return;
        }
        ArrayList arrayList = al.a(string, ",", false);
        this.validateNumberOfArguments(arrayList.size());
        this.value = LogicBooleanLoader.parseNumberBlock(l2, (String)arrayList.get(0));
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
    public LogicBoolean validateAndOptimize(String string, String string2, String string3, LogicBooleanLoader$LogicBooleanContext logicBooleanContext, boolean bl) {
        this.validate(string, string2, string3, logicBooleanContext, bl);
        if (this.value instanceof LogicBoolean$StaticValueBoolean) {
            float f = ((LogicBoolean$StaticValueBoolean)this.value).getStaticValue();
            return new LogicBoolean$StaticValueBoolean(this.doFunction(f));
        }
        return this;
    }

    @Override
    public void validate(String string, String string2, String string3, LogicBooleanLoader$LogicBooleanContext logicBooleanContext, boolean bl) {
        super.validate(string, string2, string3, logicBooleanContext, bl);
        if (this.value == null) {
            throw new BooleanParseException("Expected parameters missing");
        }
        if (bl) {
            throw new BooleanParseException("Expected function:" + string + " to return a boolean, but it returns a number");
        }
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        return this.getName() + "(" + this.value.getMatchFailReasonForPlayer(y2) + ")";
    }

    public abstract float doFunction(float var1);
}
