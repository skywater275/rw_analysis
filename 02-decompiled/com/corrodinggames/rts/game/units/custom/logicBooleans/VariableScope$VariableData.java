/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.y;

public abstract class VariableScope$VariableData
extends LogicBoolean {
    @Override
    public abstract LogicBoolean.ReturnType getReturnType();

    @Override
    public boolean read(y y2) {
        return false;
    }

    @Override
    public String getMatchFailReasonForPlayer(y y2) {
        return "Data(" + this.valueToStringDebug(null) + ")";
    }

    @Override
    public float readNumber(y y2) {
        return 0.0f;
    }

    @Override
    public String readString(y y2) {
        return "";
    }
}
