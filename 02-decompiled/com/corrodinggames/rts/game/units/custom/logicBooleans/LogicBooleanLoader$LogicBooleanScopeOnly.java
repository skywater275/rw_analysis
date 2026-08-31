/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.y;

public abstract class LogicBooleanLoader$LogicBooleanScopeOnly
extends LogicBoolean
implements LogicBooleanLoader$LogicBooleanContext {
    @Override
    public LogicBooleanLoader$LogicBooleanContext createContext() {
        return this;
    }

    @Override
    public LogicBoolean setChild(LogicBoolean logicBoolean) {
        return logicBoolean;
    }

    @Override
    public boolean read(y y2) {
        return false;
    }

    @Override
    public LogicBoolean$ReturnType getReturnType() {
        return LogicBoolean$ReturnType.voidReturn;
    }

    @Override
    public String getMatchFailReasonForPlayer(y y2) {
        return "<scope>";
    }
}
