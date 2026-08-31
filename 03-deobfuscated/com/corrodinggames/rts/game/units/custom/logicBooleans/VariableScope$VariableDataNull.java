/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableData;
import com.corrodinggames.rts.game.units.UnitType;

public class VariableScope$VariableDataNull
extends VariableScope$VariableData {
    @Override
    public LogicBoolean$ReturnType getReturnType() {
        return LogicBoolean$ReturnType.voidReturn;
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        return "null";
    }
}
