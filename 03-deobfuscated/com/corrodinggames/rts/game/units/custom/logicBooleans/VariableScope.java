/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.custom.ModifierApplier;

import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter$WriterElement;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$EmptyVariableScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryNames;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryWriter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryWriterFactory;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryWriterFactory$MemoryWriterElement;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryWriterFactory$MemoryWriterElementIndex;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableData;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataArray;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataBoolArray;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataNull;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataNumber;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataNumberArray;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataString;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataUnit;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataUnitArray;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDefinition;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableMapping;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableName;
import com.corrodinggames.rts.game.units.AmphibiousUnit;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.network.OutputNetStream;
import com.corrodinggames.rts.gameFramework.network.InputNetStream;
import java.io.IOException;

public class VariableScope {
    public static final VariableScope emptyVariableScope = new VariableScope$EmptyVariableScope();
    public static final String nullOrMissingString = "";
    static final VariableScope$VariableName[] emptyNames = new VariableScope$VariableName[0];
    static final VariableScope$VariableData[] emptyData = new VariableScope$VariableData[0];
    VariableScope$VariableName[] variableNames = emptyNames;
    VariableScope$VariableData[] variableData = emptyData;
    public static final VariableScope$VariableDataNull variableDataNull = new VariableScope$VariableDataNull();

    public boolean isEmpty() {
        for (int i = 0; i < this.variableData.length; ++i) {
            VariableScope$VariableData variableData = this.variableData[i];
            if (variableData == null) continue;
            return false;
        }
        return true;
    }

    public String debugMemory(boolean bl, boolean bl2) {
        String string = nullOrMissingString;
        for (int i = 0; i < this.variableData.length; ++i) {
            VariableScope$VariableData variableScope$VariableData = this.variableData[i];
            if (variableScope$VariableData == null) continue;
            string = string + VariableScope$VariableName.access$000(this.variableNames[i]) + "=" + variableScope$VariableData.valueToStringDebug(null);
            if (bl2) {
                string = string + " (" + variableScope$VariableData.getReturnType().name() + ")";
            }
            string = bl ? string + "\n" : string + "|";
        }
        return string;
    }

    public VariableScope$VariableData getDataObjectRaw(VariableScope$VariableName variableScope$VariableName) {
        for (int i = 0; i < this.variableData.length; ++i) {
            if (this.variableNames[i] != variableScope$VariableName) continue;
            return this.variableData[i];
        }
        return variableDataNull;
    }

    public void setArrayDataRaw(VariableScope$VariableName variableScope$VariableName, VariableScope$VariableData variableScope$VariableData, int n2) {
        VariableScope$VariableDataArray variableScope$VariableDataArray = null;
        LogicBoolean$ReturnType logicBoolean$ReturnType = LogicBoolean$ReturnType.undefined;
        if (variableScope$VariableData != null) {
            logicBoolean$ReturnType = variableScope$VariableData.getReturnType();
        }
        for (int i = 0; i < this.variableData.length; ++i) {
            if (this.variableNames[i] != variableScope$VariableName || !(this.variableData[i] instanceof VariableScope$VariableDataArray)) continue;
            VariableScope$VariableDataArray variableScope$VariableDataArray2 = (VariableScope$VariableDataArray)this.variableData[i];
            if (logicBoolean$ReturnType != LogicBoolean$ReturnType.undefined && variableScope$VariableDataArray2.getElementReturnType() != logicBoolean$ReturnType) continue;
            variableScope$VariableDataArray = variableScope$VariableDataArray2;
        }
        if (variableScope$VariableDataArray == null && variableScope$VariableData == null) {
            return;
        }
        if (variableScope$VariableDataArray == null) {
            if (logicBoolean$ReturnType == LogicBoolean$ReturnType.number) {
                variableScope$VariableDataArray = new VariableScope$VariableDataNumberArray();
            } else if (logicBoolean$ReturnType == LogicBoolean$ReturnType.bool) {
                variableScope$VariableDataArray = new VariableScope$VariableDataBoolArray();
            } else if (logicBoolean$ReturnType == LogicBoolean$ReturnType.unit) {
                variableScope$VariableDataArray = new VariableScope$VariableDataUnitArray();
            } else {
                com.corrodinggames.rts.gameFramework.GlobalState.b("Unhandled array type: " + (Object)((Object)logicBoolean$ReturnType));
                return;
            }
            this.setDataRaw(variableScope$VariableName, variableScope$VariableDataArray);
        }
        variableScope$VariableDataArray.setDataAtIndex(variableScope$VariableData, n2);
    }

    public void setDataRaw(VariableScope$VariableName variableScope$VariableName, VariableScope$VariableData variableScope$VariableData) {
        if (variableScope$VariableData == null) {
            variableScope$VariableData = variableDataNull;
        }
        for (int i = 0; i < this.variableData.length; ++i) {
            if (this.variableNames[i] != variableScope$VariableName) continue;
            this.variableData[i] = variableScope$VariableData;
            return;
        }
        VariableScope$VariableName[] variableScope$VariableNameArray = new VariableScope$VariableName[this.variableData.length + 1];
        VariableScope$VariableData[] variableScope$VariableDataArray = new VariableScope$VariableData[this.variableData.length + 1];
        for (int i = 0; i < this.variableData.length; ++i) {
            variableScope$VariableDataArray[i] = this.variableData[i];
            variableScope$VariableNameArray[i] = this.variableNames[i];
        }
        variableScope$VariableDataArray[variableScope$VariableDataArray.length - 1] = variableScope$VariableData;
        variableScope$VariableNameArray[variableScope$VariableNameArray.length - 1] = variableScope$VariableName;
        this.variableData = variableScope$VariableDataArray;
        this.variableNames = variableScope$VariableNameArray;
    }

    public void clearAllData() {
        this.variableData = emptyData;
        this.variableNames = emptyNames;
    }

    public void setUnit(VariableScope$VariableDefinition variableScope$VariableDefinition, UnitInstance am2) {
        if (variableScope$VariableDefinition.type != LogicBoolean$ReturnType.unit) {
            // empty if block
        }
        this.setDataRaw(variableScope$VariableDefinition.name, new VariableScope$VariableDataUnit(am2));
    }

    UnitInstance getUnit(VariableScope$VariableName variableScope$VariableName) {
        return this.getDataObjectRaw(variableScope$VariableName).readUnit(null);
    }

    LogicBoolean getAsLogicBoolean(VariableScope$VariableName variableScope$VariableName) {
        return this.getDataObjectRaw(variableScope$VariableName);
    }

    public void setFromLogicBoolean(VariableScope$VariableName variableScope$VariableName, UnitType y2, LogicBoolean logicBoolean, LogicBoolean logicBoolean2) {
        VariableScope$VariableData variableScope$VariableData = null;
        if (logicBoolean != null) {
            LogicBoolean$ReturnType logicBoolean$ReturnType = logicBoolean.getReturnType();
            if (logicBoolean$ReturnType == LogicBoolean$ReturnType.bool) {
                variableScope$VariableData = new VariableScope$VariableDataBoolean(logicBoolean.read(y2));
            } else if (logicBoolean$ReturnType == LogicBoolean$ReturnType.unit) {
                UnitInstance am2 = logicBoolean.readUnit(y2);
                am2 = VariableScope.getSafeUnitReferenceForStorage(am2);
                variableScope$VariableData = new VariableScope$VariableDataUnit(am2);
            } else if (logicBoolean$ReturnType == LogicBoolean$ReturnType.number) {
                variableScope$VariableData = new VariableScope$VariableDataNumber(logicBoolean.readNumber(y2));
            } else if (logicBoolean$ReturnType == LogicBoolean$ReturnType.string) {
                variableScope$VariableData = new VariableScope$VariableDataString(logicBoolean.readString(y2));
            }
        }
        if (logicBoolean2 != null) {
            int n2 = (int)logicBoolean2.readNumber(y2);
            this.setArrayDataRaw(variableScope$VariableName, variableScope$VariableData, n2);
        } else {
            this.setDataRaw(variableScope$VariableName, variableScope$VariableData);
        }
    }

    double getNumber(VariableScope$VariableName variableScope$VariableName) {
        return this.getDataObjectRaw(variableScope$VariableName).readNumber(null);
    }

    String getString(VariableScope$VariableName variableScope$VariableName) {
        return this.getDataObjectRaw(variableScope$VariableName).readString(null);
    }

    boolean getBoolean(VariableScope$VariableName variableScope$VariableName) {
        return this.getDataObjectRaw(variableScope$VariableName).read(null);
    }

    public static void writeOut(OutputNetStream as2, VariableScope variableScope) {
        if (variableScope == null) {
            as2.c(-2);
            return;
        }
        if (variableScope.variableData.length == 0) {
            as2.c(-1);
            return;
        }
        as2.c(0);
        as2.a((short)variableScope.variableData.length);
        int n2 = variableScope.variableData.length;
        for (int i = 0; i < n2; ++i) {
            VariableScope$VariableData variableScope$VariableData = variableScope.variableData[i];
            as2.c(VariableScope$VariableName.access$000(variableScope.variableNames[i]));
            boolean bl = false;
            as2.a(bl);
            if (bl) continue;
            VariableScope.writeOutDynamicData(as2, variableScope$VariableData);
        }
    }

    public static VariableScope readIn(InputNetStream k2) throws IOException {
        byte by = k2.d();
        if (by == -2) {
            return null;
        }
        if (by == -1) {
            return null;
        }
        int n2 = k2.v();
        VariableScope variableScope = new VariableScope();
        for (int i = 0; i < n2; ++i) {
            VariableScope$VariableName variableScope$VariableName = VariableScope$VariableName.get(k2.readString());
            boolean bl = k2.readBoolean();
            if (bl) continue;
            VariableScope$VariableData variableScope$VariableData = VariableScope.readInDynamicData(k2);
            variableScope.setDataRaw(variableScope$VariableName, variableScope$VariableData);
        }
        return variableScope;
    }

    public static void writeOutUnitOrPlaceholder(OutputNetStream as2, UnitInstance am2) {
        if (am2 instanceof AmphibiousUnit) {
            as2.c(1);
            as2.a(am2.eo);
            as2.a(am2.ep);
            as2.a(am2.eq);
            as2.a(am2.cg);
            as2.a(am2.player);
        } else {
            as2.c(0);
            as2.b(am2);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void writeOutDynamicData(OutputNetStream as2, VariableScope$VariableData variableScope$VariableData) {
        if (variableScope$VariableData == null) {
            as2.a((Enum)null);
            return;
        }
        LogicBoolean$ReturnType logicBoolean$ReturnType = variableScope$VariableData.getReturnType();
        as2.a(logicBoolean$ReturnType);
        if (variableScope$VariableData instanceof VariableScope$VariableDataUnit) {
            VariableScope$VariableDataUnit variableScope$VariableDataUnit = (VariableScope$VariableDataUnit)variableScope$VariableData;
            UnitInstance am2 = variableScope$VariableDataUnit.unit;
            VariableScope.writeOutUnitOrPlaceholder(as2, am2);
            return;
        } else if (variableScope$VariableData instanceof VariableScope$VariableDataBoolean) {
            as2.a(((VariableScope$VariableDataBoolean)variableScope$VariableData).bool);
            return;
        } else if (variableScope$VariableData instanceof VariableScope$VariableDataString) {
            as2.b(((VariableScope$VariableDataString)variableScope$VariableData).text);
            return;
        } else if (variableScope$VariableData instanceof VariableScope$VariableDataNumber) {
            as2.a(((VariableScope$VariableDataNumber)variableScope$VariableData).number);
            return;
        } else if (variableScope$VariableData instanceof VariableScope$VariableDataArray) {
            VariableScope$VariableDataArray variableScope$VariableDataArray = (VariableScope$VariableDataArray)variableScope$VariableData;
            as2.a(variableScope$VariableDataArray.size);
            if (variableScope$VariableDataArray instanceof VariableScope$VariableDataBoolArray) {
                VariableScope$VariableDataBoolArray variableScope$VariableDataBoolArray = (VariableScope$VariableDataBoolArray)variableScope$VariableDataArray;
                for (int i = 0; i < variableScope$VariableDataBoolArray.size; ++i) {
                    as2.a(variableScope$VariableDataBoolArray.dataArray[i]);
                }
                return;
            } else if (variableScope$VariableDataArray instanceof VariableScope$VariableDataNumberArray) {
                VariableScope$VariableDataNumberArray variableScope$VariableDataNumberArray = (VariableScope$VariableDataNumberArray)variableScope$VariableDataArray;
                for (int i = 0; i < variableScope$VariableDataNumberArray.size; ++i) {
                    as2.a(variableScope$VariableDataNumberArray.dataArray[i]);
                }
                return;
            } else {
                if (!(variableScope$VariableDataArray instanceof VariableScope$VariableDataUnitArray)) throw new RuntimeException("Unhandled array type: " + logicBoolean$ReturnType.name());
                VariableScope$VariableDataUnitArray variableScope$VariableDataUnitArray = (VariableScope$VariableDataUnitArray)variableScope$VariableDataArray;
                for (int i = 0; i < variableScope$VariableDataUnitArray.size; ++i) {
                    UnitInstance am3 = variableScope$VariableDataUnitArray.dataArray[i];
                    VariableScope.writeOutUnitOrPlaceholder(as2, am3);
                }
            }
            return;
        } else {
            if (logicBoolean$ReturnType == LogicBoolean$ReturnType.undefined) return;
            throw new RuntimeException("Unhandled type: " + logicBoolean$ReturnType.name());
        }
    }

    public static UnitInstance readInUnitOrPlaceholder(InputNetStream k2) throws IOException {
        UnitInstance am2;
        byte by = k2.d();
        if (by == 1) {
            float f2 = k2.readFloat();
            float f3 = k2.readFloat();
            float f4 = k2.readFloat();
            float f5 = k2.readFloat();
            PlayerState n2 = k2.s();  // 02b L338: n var7 = var0.s() 铁证
            am2 = AmphibiousUnit.a(n2);  // 02b L339: t.a(var7) — t=units.t=AmphibiousUnit (02b L5 import 铁证)
            am2.eo = f2;
            am2.ep = f3;
            am2.eq = f4;
            am2.cg = f5;
        } else if (by == 0) {
            am2 = k2.o();
        } else {
            throw new IOException("Unhandled unit type: " + by);
        }
        return am2;
    }

    public static VariableScope$VariableData readInDynamicData(InputNetStream k2) throws IOException {
        LogicBoolean$ReturnType logicBoolean$ReturnType = (LogicBoolean$ReturnType)k2.b(LogicBoolean$ReturnType.class);
        if (logicBoolean$ReturnType == null) {
            return null;
        }
        if (logicBoolean$ReturnType == LogicBoolean$ReturnType.unit) {
            UnitInstance am2 = VariableScope.readInUnitOrPlaceholder(k2);
            VariableScope$VariableDataUnit variableScope$VariableDataUnit = new VariableScope$VariableDataUnit(am2);
            return variableScope$VariableDataUnit;
        }
        if (logicBoolean$ReturnType == LogicBoolean$ReturnType.bool) {
            return new VariableScope$VariableDataBoolean(k2.readBoolean());
        }
        if (logicBoolean$ReturnType == LogicBoolean$ReturnType.string) {
            return new VariableScope$VariableDataString(k2.j());
        }
        if (logicBoolean$ReturnType == LogicBoolean$ReturnType.number) {
            return new VariableScope$VariableDataNumber(k2.h());
        }
        if (logicBoolean$ReturnType == LogicBoolean$ReturnType.boolArray || logicBoolean$ReturnType == LogicBoolean$ReturnType.numberArray || logicBoolean$ReturnType == LogicBoolean$ReturnType.unitArray) {
            int n2 = k2.readInt();
            if (logicBoolean$ReturnType == LogicBoolean$ReturnType.boolArray) {
                VariableScope$VariableDataBoolArray variableScope$VariableDataBoolArray = new VariableScope$VariableDataBoolArray();
                for (int i = 0; i < n2; ++i) {
                    variableScope$VariableDataBoolArray.setBooleanIndex(i, k2.readBoolean());
                }
                return variableScope$VariableDataBoolArray;
            }
            if (logicBoolean$ReturnType == LogicBoolean$ReturnType.numberArray) {
                VariableScope$VariableDataNumberArray variableScope$VariableDataNumberArray = new VariableScope$VariableDataNumberArray();
                for (int i = 0; i < n2; ++i) {
                    variableScope$VariableDataNumberArray.setNumberIndex(i, k2.readFloat());
                }
                return variableScope$VariableDataNumberArray;
            }
            if (logicBoolean$ReturnType == LogicBoolean$ReturnType.unitArray) {
                VariableScope$VariableDataUnitArray variableScope$VariableDataUnitArray = new VariableScope$VariableDataUnitArray();
                for (int i = 0; i < n2; ++i) {
                    UnitInstance am3 = VariableScope.readInUnitOrPlaceholder(k2);
                    variableScope$VariableDataUnitArray.setUnitIndex(i, am3);
                }
                return variableScope$VariableDataUnitArray;
            }
            throw new RuntimeException("Unhandled array type: " + logicBoolean$ReturnType.name());
        }
        if (logicBoolean$ReturnType == LogicBoolean$ReturnType.undefined) {
            throw new RuntimeException("Undefined type: " + logicBoolean$ReturnType.name());
        }
        throw new RuntimeException("Unhandled type: " + logicBoolean$ReturnType.name());
    }

    public static LogicBoolean$ReturnType getUserType(String string) {
        LogicBoolean$ReturnType logicBoolean$ReturnType = null;
        if (string.equals("boolean")) {
            logicBoolean$ReturnType = LogicBoolean$ReturnType.bool;
        } else if (string.equals("bool")) {
            logicBoolean$ReturnType = LogicBoolean$ReturnType.bool;
        } else if (string.equals("unit")) {
            logicBoolean$ReturnType = LogicBoolean$ReturnType.unit;
        } else if (string.equals("number")) {
            logicBoolean$ReturnType = LogicBoolean$ReturnType.number;
        } else if (string.equals("float")) {
            logicBoolean$ReturnType = LogicBoolean$ReturnType.number;
        } else if (string.equals("text")) {
            logicBoolean$ReturnType = LogicBoolean$ReturnType.string;
        } else if (string.equals("string")) {
            logicBoolean$ReturnType = LogicBoolean$ReturnType.string;
        } else if (string.equals("number[]")) {
            logicBoolean$ReturnType = LogicBoolean$ReturnType.numberArray;
        } else if (string.equals("float[]")) {
            logicBoolean$ReturnType = LogicBoolean$ReturnType.numberArray;
        } else if (string.equals("bool[]")) {
            logicBoolean$ReturnType = LogicBoolean$ReturnType.boolArray;
        } else if (string.equals("boolean[]")) {
            logicBoolean$ReturnType = LogicBoolean$ReturnType.boolArray;
        } else if (string.equals("unit[]")) {
            logicBoolean$ReturnType = LogicBoolean$ReturnType.unitArray;
        }
        return logicBoolean$ReturnType;
    }

    public static VariableScope$MemoryWriter createGenericKeyValueWriter(String string, ModUnitRegistry l2, String string2, String string3) {
        try {
            VariableScope$MemoryWriter variableScope$MemoryWriter = new VariableScope$MemoryWriter();
            VariableScope$VariableMapping variableMapping = null;
            variableScope$MemoryWriter.addWriterElements(string, new VariableScope$MemoryWriterFactory(l2, variableMapping));
            return variableScope$MemoryWriter;
        }
        catch (bo bo2) {
            throw new RuntimeException("[" + string2 + "]" + string3 + ": " + bo2.getMessage(), bo2);
        }
    }

    public static VariableScope$MemoryWriter createMemoryWriter(String string, ModUnitRegistry l2, String string2, String string3) {
        try {
            VariableScope$MemoryWriter variableScope$MemoryWriter = new VariableScope$MemoryWriter();
            variableScope$MemoryWriter.addWriterElements(string, new VariableScope$MemoryWriterFactory(l2));
            return variableScope$MemoryWriter;
        }
        catch (bo bo2) {
            throw new RuntimeException("[" + string2 + "]" + string3 + ": " + bo2.getMessage(), bo2);
        }
    }

    public static VariableScope$MemoryNames createMemoryNameList(String string, ModUnitRegistry l2, LogicBoolean$ReturnType logicBoolean$ReturnType, String string2, String string3) {
        try {
            VariableScope$MemoryWriter variableScope$MemoryWriter = new VariableScope$MemoryWriter();
            VariableScope$MemoryWriterFactory variableScope$MemoryWriterFactory = new VariableScope$MemoryWriterFactory(l2);
            variableScope$MemoryWriterFactory.noValues = true;
            variableScope$MemoryWriter.addWriterElements(string, variableScope$MemoryWriterFactory);
            VariableScope$MemoryNames variableScope$MemoryNames = new VariableScope$MemoryNames();
            for (Object object : variableScope$MemoryWriter.writers) {  // 02b L470: var5.writers.iterator() 铁证
                VariableScope$CachedWriter$WriterElement variableScope$CachedWriter$WriterElement = (VariableScope$CachedWriter$WriterElement)object;
                if (!(variableScope$CachedWriter$WriterElement instanceof VariableScope$MemoryWriterFactory$MemoryWriterElement)) {
                    throw new bo("Unexpected element reading: " + string, string2, string3);
                }
                VariableScope$MemoryWriterFactory$MemoryWriterElement variableScope$MemoryWriterFactory$MemoryWriterElement = (VariableScope$MemoryWriterFactory$MemoryWriterElement)variableScope$CachedWriter$WriterElement;
                if (variableScope$MemoryWriterFactory$MemoryWriterElement instanceof VariableScope$MemoryWriterFactory$MemoryWriterElementIndex) {
                    throw new bo("Expected memory name without an index got: " + string, string2, string3);
                }
                if (logicBoolean$ReturnType != null) {
                    VariableScope$VariableDefinition variableScope$VariableDefinition = l2.r.get(variableScope$MemoryWriterFactory$MemoryWriterElement.name);
                    if (variableScope$VariableDefinition == null) {
                        throw new bo("Failed to find defined memory: " + string, string2, string3);
                    }
                    if (variableScope$VariableDefinition.type != logicBoolean$ReturnType) {
                        throw new bo("Memory: " + string + " is type: " + (Object)((Object)variableScope$VariableDefinition.type) + " expected: " + (Object)((Object)logicBoolean$ReturnType), string2, string3);
                    }
                }
                variableScope$MemoryNames.names.add(variableScope$MemoryWriterFactory$MemoryWriterElement.name);
            }
            return variableScope$MemoryNames;
        }
        catch (bo bo2) {
            throw new RuntimeException("[" + string2 + "]" + string3 + ": " + bo2.getMessage(), bo2);
        }
    }

    public static boolean isMarker(UnitInstance am2) {
        if (am2 == null) {
            return false;
        }
        return am2 instanceof AmphibiousUnit;
    }

    public static UnitInstance getSafeUnitReferenceForStorage(UnitInstance am2) {
        if (am2 == null) {
            return null;
        }
        if (am2 instanceof AmphibiousUnit) {
            AmphibiousUnit t2 = AmphibiousUnit.a(am2.player);  // 02b L507: t.a(var0.bX) 铁证
            t2.eo = am2.eo;
            t2.ep = am2.ep;
            t2.eq = am2.eq;
            t2.cg = am2.cg;
            return t2;
        }
        return am2;
    }

    public VariableScope() {
        // v19.115p 批5 补缺: 02b VariableScope.java 无参构造铁证 (i.java new VariableScope())
        this.variableNames = emptyNames;
        this.variableData = emptyData;
    }
}
