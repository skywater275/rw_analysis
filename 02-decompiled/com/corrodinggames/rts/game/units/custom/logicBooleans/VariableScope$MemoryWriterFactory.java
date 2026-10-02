/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterElement;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterFactory;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryWriterFactory$MemoryWriterElement;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryWriterFactory$MemoryWriterElementIndex;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableMapping;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableName;

public class VariableScope$MemoryWriterFactory
extends VariableScope$CachedWriter$WriterFactory {
    l meta;
    VariableScope$VariableMapping target;
    boolean noValues;

    public VariableScope$MemoryWriterFactory(l l2, VariableScope$VariableMapping variableMapping) {
        this.meta = l2;
        this.target = variableMapping;
    }

    public VariableScope$MemoryWriterFactory(l l2) {
        this.meta = l2;
        if (l2 != null) {
            this.target = l2.r;
        }
    }

    @Override
    public VariableScope$CachedWriter$WriterElement createWriterElement(String string, String string2, String string3, String string4) {
        VariableScope$MemoryWriterFactory$MemoryWriterElement variableScope$MemoryWriterFactory$MemoryWriterElement;
        LogicBoolean$ReturnType logicBoolean$ReturnType;
        VariableScope$VariableName variableScope$VariableName;
        Object object;
        if (!string2.equals("=")) {
            throw new bo("Only '=' is supported here, got:" + string2);
        }
        if (!this.noValues) {
            if (string3 == null) {
                throw new bo("Expected a value for: " + string + " (likely missing '=')");
            }
        } else if (string3 != null) {
            throw new bo("Expected no value for: " + string + " (Remove '=" + string3 + "')");
        }
        LogicBoolean logicBoolean = null;
        if (string3 != null) {
            try {
                logicBoolean = LogicBooleanLoader.parseBooleanBlock(this.meta, string3, false);
            }
            catch (RuntimeException runtimeException) {
                throw new RuntimeException("LogicBoolean - Error: " + runtimeException.getMessage() + ", [parsing: '" + string3 + "']", runtimeException);
            }
        }
        if (this.target != null) {
            object = this.target.get(string);
            if (object == null) {
                throw new bo("Unknown variable: " + string + " (has it been defined in this unit?)");
            }
            variableScope$VariableName = object.name;
            logicBoolean$ReturnType = object.type;
        } else {
            variableScope$VariableName = VariableScope$VariableName.get(string);
            logicBoolean$ReturnType = LogicBoolean$ReturnType.undefined;
        }
        object = logicBoolean$ReturnType;
        LogicBoolean logicBoolean2 = null;
        if (string4 != null) {
            if (!LogicBoolean$ReturnType.isArrayType(logicBoolean$ReturnType)) {
                if (logicBoolean$ReturnType != LogicBoolean$ReturnType.undefined) {
                    throw new bo("Variable: " + string + " is not an array type " + (Object)((Object)logicBoolean$ReturnType) + " cannot use [] index on it.");
                }
            } else {
                object = LogicBoolean$ReturnType.getArrayBaseType(logicBoolean$ReturnType);
            }
            try {
                logicBoolean2 = LogicBooleanLoader.parseBooleanBlock(this.meta, string4, false);
            }
            catch (RuntimeException runtimeException) {
                throw new RuntimeException("Error reading " + string + "[] array index: " + runtimeException.getMessage() + ", [parsing: '" + string4 + "']", runtimeException);
            }
            if (logicBoolean2 != null && logicBoolean2.getReturnType() != LogicBoolean$ReturnType.number) {
                throw new RuntimeException("Expected " + string + "[] array index to be a number got " + (Object)((Object)logicBoolean2.getReturnType()) + " type, [parsing: '" + string4 + "']");
            }
            if (logicBoolean2 == null) {
                throw new RuntimeException("Missing " + string + "[] array index");
            }
        } else if (!(this.noValues || !LogicBoolean$ReturnType.isArrayType(logicBoolean$ReturnType) || string3 != null && "null".equalsIgnoreCase(string3.trim()))) {
            throw new bo("Variable " + string + " is an array type. Expected: NAME[INDEX]=VALUE format (or NAME=null)");
        }
        if (logicBoolean2 == null) {
            variableScope$MemoryWriterFactory$MemoryWriterElement = new VariableScope$MemoryWriterFactory$MemoryWriterElement();
            variableScope$MemoryWriterFactory$MemoryWriterElement.name = variableScope$VariableName;
            variableScope$MemoryWriterFactory$MemoryWriterElement.value = logicBoolean;
        } else {
            VariableScope$MemoryWriterFactory$MemoryWriterElementIndex variableScope$MemoryWriterFactory$MemoryWriterElementIndex = new VariableScope$MemoryWriterFactory$MemoryWriterElementIndex();
            variableScope$MemoryWriterFactory$MemoryWriterElement = variableScope$MemoryWriterFactory$MemoryWriterElementIndex;
            variableScope$MemoryWriterFactory$MemoryWriterElementIndex.name = variableScope$VariableName;
            variableScope$MemoryWriterFactory$MemoryWriterElementIndex.value = logicBoolean;
            variableScope$MemoryWriterFactory$MemoryWriterElementIndex.nameIndex = logicBoolean2;
        }
        if (object != LogicBoolean$ReturnType.undefined && logicBoolean != null && logicBoolean.getReturnType() != object) {
            if (LogicBoolean.isStaticNull(logicBoolean)) {
                boolean bl2 = LogicBoolean$ReturnType.canBeNull((LogicBoolean$ReturnType)((Object)object));
                if (!bl2) {
                    throw new bo("Variable: " + string + " of type " + object + " cannot be set to null.");
                }
            } else {
                throw new bo("Variable: " + string + " expects " + LogicBoolean$ReturnType.toUserString((LogicBoolean$ReturnType)((Object)object)) + " type getting: " + LogicBoolean$ReturnType.toUserString(logicBoolean.getReturnType()) + " from: " + string3);
            }
        }
        return variableScope$MemoryWriterFactory$MemoryWriterElement;
    }
}
