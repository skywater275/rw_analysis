/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterElement;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryWriterFactory$MemoryWriterElement;
import com.corrodinggames.rts.game.units.UnitType;

public class VariableScope$MemoryWriter
extends VariableScope$CachedWriter {
    public void writeToMemory(VariableScope variableScope, UnitType y2) {
        for (Object object : this.writers) {
            VariableScope$CachedWriter$WriterElement variableScope$CachedWriter$WriterElement = (VariableScope$CachedWriter$WriterElement)object;
            VariableScope$MemoryWriterFactory$MemoryWriterElement variableScope$MemoryWriterFactory$MemoryWriterElement = (VariableScope$MemoryWriterFactory$MemoryWriterElement)variableScope$CachedWriter$WriterElement;
            variableScope$MemoryWriterFactory$MemoryWriterElement.writeToMemory(variableScope, y2);
        }
    }

}
