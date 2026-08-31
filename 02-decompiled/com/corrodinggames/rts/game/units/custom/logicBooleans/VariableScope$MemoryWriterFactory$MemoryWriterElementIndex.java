/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryWriterFactory$MemoryWriterElement;
import com.corrodinggames.rts.game.units.y;

public class VariableScope$MemoryWriterFactory$MemoryWriterElementIndex
extends VariableScope$MemoryWriterFactory$MemoryWriterElement {
    public LogicBoolean nameIndex;

    @Override
    public void writeToUnit(y y2) {
        if (y2.bw == null) {
            y2.bw = new VariableScope();
        }
        y2.bw.setFromLogicBoolean(this.name, y2, this.value, this.nameIndex);
    }

    @Override
    public void writeToMemory(VariableScope variableScope, y y2) {
        variableScope.setFromLogicBoolean(this.name, y2, this.value, this.nameIndex);
    }
}
