/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.e.a;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanGameFunctions$ResourceScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.y;

public final class LogicBooleanGameFunctions$ResourceCountBoolean
extends LogicBoolean$AbstractNumberBoolean {
    l meta;
    a type;

    @Override
    public void forMeta(l l2) {
        if (l2 == null) {
            throw new BooleanParseException("ResourceCountBoolean requires metadata");
        }
        this.meta = l2;
    }

    @LogicBoolean.Parameter(positional=0)
    public void type(String string) {
        this.type = this.meta.j(string);
        if (this.type == null) {
            throw new BooleanParseException("Could not find resource type: '" + string + "'");
        }
    }

    @Override
    public void validate(String string, String string2, String string3, LogicBooleanLoader.LogicBooleanContext logicBooleanContext, boolean bl) {
        super.validate(string, string2, string3, logicBooleanContext, bl);
        if (this.type == null) {
            // empty if block
        }
    }

    @Override
    public String getName() {
        return this.type + "";
    }

    @Override
    public float getValue(y y2) {
        if (this.type == null) {
            return 0.0f;
        }
        return (float)this.type.a(y2);
    }

    @Override
    public float getMaxValue(y y2) {
        return 2.14748365E9f;
    }

    @Override
    public LogicBooleanLoader.LogicBooleanContext createContext() {
        return new LogicBooleanGameFunctions$ResourceScope();
    }

    @Override
    public LogicBoolean$ReturnType getReturnType() {
        if (this.type == null) {
            return LogicBoolean$ReturnType.voidReturn;
        }
        if (this.greaterThan == -1.0f && this.lessThan == -1.0f && !this.full && !this.empty) {
            return LogicBoolean$ReturnType.number;
        }
        return LogicBoolean$ReturnType.bool;
    }

    @Override
    public void throwVoidReturnError(String string) {
        throw new RuntimeException("'" + string + "' requires type");
    }

    @Override
    public LogicBoolean setChild(LogicBoolean logicBoolean) {
        return logicBoolean;
    }
}
