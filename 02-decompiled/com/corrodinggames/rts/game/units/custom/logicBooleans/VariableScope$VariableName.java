/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import java.util.HashMap;

public class VariableScope$VariableName {
    private static HashMap existingVariableName = new HashMap();
    private String id;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static VariableScope$VariableName getExistingOrNull(String string) {
        HashMap hashMap = existingVariableName;
        synchronized (hashMap) {
            VariableScope$VariableName variableScope$VariableName = (VariableScope$VariableName)existingVariableName.get(string);
            if (variableScope$VariableName != null) {
                return variableScope$VariableName;
            }
            return null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static VariableScope$VariableName get(String string) {
        HashMap hashMap = existingVariableName;
        synchronized (hashMap) {
            VariableScope$VariableName variableScope$VariableName = (VariableScope$VariableName)existingVariableName.get(string);
            if (variableScope$VariableName != null) {
                return variableScope$VariableName;
            }
            VariableScope$VariableName variableScope$VariableName2 = new VariableScope$VariableName();
            variableScope$VariableName2.id = string;
            existingVariableName.put(string, variableScope$VariableName2);
            return variableScope$VariableName2;
        }
    }

    public String getId() {
        return this.id;
    }

    public String toString() {
        return this.id;
    }

    static /* synthetic */ String access$000(VariableScope$VariableName variableScope$VariableName) {
        return variableScope$VariableName.id;
    }
}
