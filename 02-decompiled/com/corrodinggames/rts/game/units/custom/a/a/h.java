/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.a.a;
import com.corrodinggames.rts.game.units.custom.a.d;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$CachedWriter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryNames;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$MemoryWriter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableData;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDataArray;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDefinition;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableName;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class h
extends a {
    VariableScope$CachedWriter a;
    boolean b;
    LogicBoolean c;
    LogicBoolean d;
    VariableScope$MemoryNames e;

    public static void a(l l2, ab ab2, String string, String string2, d d2, String string3, boolean bl2) {
        boolean bl3 = ab2.a(string, string2 + "swapCustomTarget1And2", (Boolean)false);
        LogicBoolean logicBoolean = ab2.b(l2, string, string2 + "setCustomTarget1", null);
        LogicBoolean logicBoolean2 = ab2.b(l2, string, string2 + "setCustomTarget2", null);
        VariableScope$MemoryWriter variableScope$MemoryWriter = null;
        String string4 = ab2.b(string, string2 + "setUnitMemory", (String)null);
        if (string4 != null) {
            variableScope$MemoryWriter = VariableScope.createMemoryWriter(string4, l2, string, string2 + "setUnitMemory");
        }
        String string5 = ab2.b(string, string2 + "shrinkArrays", (String)null);
        VariableScope$MemoryNames variableScope$MemoryNames = null;
        if (string5 != null) {
            variableScope$MemoryNames = VariableScope.createMemoryNameList(string5, l2, null, string, string2 + "shrinkArrays");
            for (VariableScope$VariableName variableScope$VariableName : variableScope$MemoryNames.names) {
                VariableScope$VariableDefinition variableScope$VariableDefinition = l2.r.get(variableScope$VariableName);
                if (variableScope$VariableDefinition == null) {
                    throw new bo("Failed to find defined memory: " + variableScope$VariableName, string, string2 + "shrinkArrays");
                }
                if (!LogicBoolean$ReturnType.isArrayType(variableScope$VariableDefinition.type)) {
                    throw new bo("Memory: " + variableScope$VariableName + " is type: " + (Object)((Object)variableScope$VariableDefinition.type) + " expected an array type", string, string2 + "shrinkArrays");
                }
                if (variableScope$VariableDefinition.type == LogicBoolean$ReturnType.numberArray || variableScope$VariableDefinition.type == LogicBoolean$ReturnType.unitArray) continue;
                throw new bo("Memory: " + variableScope$VariableName + " is type: " + (Object)((Object)variableScope$VariableDefinition.type) + " only number and unit arrays are supported by shrinkArrays", string, string2 + "shrinkArrays");
            }
        }
        if (bl3 || logicBoolean != null || logicBoolean2 != null || variableScope$MemoryWriter != null || variableScope$MemoryNames != null) {
            h h2 = new h();
            h2.a = variableScope$MemoryWriter;
            h2.b = bl3;
            h2.c = logicBoolean;
            h2.d = logicBoolean2;
            h2.e = variableScope$MemoryNames;
            d2.ac.add(h2);
        }
    }

    @Override
    public boolean a(j j2, s s2, PointF pointF, am am2, int n2) {
        am am3;
        if (this.a != null) {
            this.a.writeToUnit(j2);
        }
        if (this.b) {
            am3 = j2.bu;
            j2.bu = j2.bv;
            j2.bv = am3;
        }
        if (this.c != null) {
            am3 = this.c.readUnit(j2);
            j2.bu = am3 = VariableScope.getSafeUnitReferenceForStorage(am3);
        }
        if (this.d != null) {
            am3 = this.d.readUnit(j2);
            j2.bv = am3 = VariableScope.getSafeUnitReferenceForStorage(am3);
        }
        if (this.e != null) {
            h.a(j2, this.e);
        }
        return true;
    }

    public static void a(j j2, VariableScope$MemoryNames variableScope$MemoryNames) {
        if (j2.bw == null) {
            return;
        }
        for (VariableScope$VariableName variableScope$VariableName : variableScope$MemoryNames.names) {
            VariableScope$VariableData variableScope$VariableData = j2.bw.getDataObjectRaw(variableScope$VariableName);
            if (variableScope$VariableData == null || !(variableScope$VariableData instanceof VariableScope$VariableDataArray)) continue;
            VariableScope$VariableDataArray variableScope$VariableDataArray = (VariableScope$VariableDataArray)variableScope$VariableData;
            variableScope$VariableDataArray.shrink();
        }
    }
}
