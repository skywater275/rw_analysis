/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableData;
import com.corrodinggames.rts.game.units.y;

public class VariableScope$VariableDataUnit
extends VariableScope$VariableData {
    am unit;

    public VariableScope$VariableDataUnit(am am2) {
        this.unit = am2;
    }

    @Override
    public LogicBoolean$ReturnType getReturnType() {
        return LogicBoolean$ReturnType.unit;
    }

    @Override
    public am readUnit(y y2) {
        return this.unit;
    }
}
