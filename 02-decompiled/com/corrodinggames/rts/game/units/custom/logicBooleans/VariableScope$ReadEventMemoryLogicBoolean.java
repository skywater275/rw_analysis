/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.k;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$ReadUnitMemoryLogicBoolean;
import com.corrodinggames.rts.game.units.y;

public class VariableScope$ReadEventMemoryLogicBoolean
extends VariableScope$ReadUnitMemoryLogicBoolean {
    @Override
    public LogicBoolean getUnitMemory(y y2) {
        k k2 = LogicBoolean.activeEvent;
        VariableScope variableScope = null;
        if (k2 != null) {
            variableScope = k2.e;
        }
        if (variableScope == null) {
            return this.defaultValue;
        }
        LogicBoolean logicBoolean = variableScope.getAsLogicBoolean(this._name);
        if (logicBoolean == null) {
            return this.defaultValue;
        }
        return logicBoolean;
    }
}
