/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableData;
import com.corrodinggames.rts.game.units.UnitType;

public class VariableScope$VariableDataBoolean
extends VariableScope$VariableData {
    boolean bool;

    public VariableScope$VariableDataBoolean(boolean bl) {
        this.bool = bl;
    }

    @Override
    public LogicBoolean$ReturnType getReturnType() {
        return LogicBoolean$ReturnType.bool;
    }

    @Override
    public boolean read(UnitType y2) {
        return this.bool;
    }
}
