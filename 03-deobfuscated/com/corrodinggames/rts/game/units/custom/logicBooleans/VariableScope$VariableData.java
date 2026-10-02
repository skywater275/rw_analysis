/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.UnitType;

public abstract class VariableScope$VariableData
extends LogicBoolean {
    @Override
    public abstract LogicBoolean$ReturnType getReturnType();

    @Override
    public boolean read(UnitType y2) {
        return false;
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        return "Data(" + this.valueToStringDebug(null) + ")";
    }

    @Override
    public float readNumber(UnitType y2) {
        return 0.0f;
    }

    @Override
    public String readString(UnitType y2) {
        return "";
    }
}
