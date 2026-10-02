/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.game.units.custom.bo;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterElement;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableName;
import com.corrodinggames.rts.game.units.UnitType;

public class VariableScope$MemoryWriterFactory$MemoryWriterElement
extends VariableScope$CachedWriter$WriterElement {
    public VariableScope$VariableName name;
    public LogicBoolean value;

    @Override
    public void writeToUnit(UnitType y2) throws bo {
        if (y2.bw == null) {
            y2.bw = new VariableScope();
        }
        y2.bw.setFromLogicBoolean(this.name, y2, this.value, null);
    }

    public void writeToMemory(VariableScope variableScope, UnitType y2) {
        variableScope.setFromLogicBoolean(this.name, y2, this.value, null);
    }
}
