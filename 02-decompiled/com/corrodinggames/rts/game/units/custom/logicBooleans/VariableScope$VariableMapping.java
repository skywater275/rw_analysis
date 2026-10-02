/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDefinition;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableName;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.util.Locale;

public class VariableScope$VariableMapping {
    l meta;
    m mapping = new m();

    public VariableScope$VariableDefinition create(String string, LogicBoolean$ReturnType returnType) {
        VariableScope$VariableName variableScope$VariableName = VariableScope$VariableName.get(string);
        VariableScope$VariableDefinition variableScope$VariableDefinition = this.get(variableScope$VariableName);
        if (variableScope$VariableDefinition != null) {
            throw new RuntimeException("A variable already exists with the name: " + string);
        }
        VariableScope$VariableDefinition variableScope$VariableDefinition2 = new VariableScope$VariableDefinition();
        variableScope$VariableDefinition2.name = variableScope$VariableName;
        variableScope$VariableDefinition2.type = returnType;
        this.mapping.add(variableScope$VariableDefinition2);
        return variableScope$VariableDefinition2;
    }

    public VariableScope$VariableDefinition get(String string) {
        string = string.toLowerCase(Locale.ROOT).trim();
        VariableScope$VariableName variableScope$VariableName = VariableScope$VariableName.get(string);
        for (VariableScope$VariableDefinition variableScope$VariableDefinition : this.mapping) {
            if (variableScope$VariableDefinition.name != variableScope$VariableName) continue;
            return variableScope$VariableDefinition;
        }
        return null;
    }

    public VariableScope$VariableDefinition get(VariableScope$VariableName variableScope$VariableName) {
        for (VariableScope$VariableDefinition variableScope$VariableDefinition : this.mapping) {
            if (variableScope$VariableDefinition.name != variableScope$VariableName) continue;
            return variableScope$VariableDefinition;
        }
        return null;
    }

    public boolean hasArrays() {
        for (VariableScope$VariableDefinition variableScope$VariableDefinition : this.mapping) {
            if (!LogicBoolean$ReturnType.isArrayType(variableScope$VariableDefinition.type)) continue;
            return true;
        }
        return false;
    }

    public String getListOfPossibleNames() {
        String string = null;
        for (VariableScope$VariableDefinition variableScope$VariableDefinition : this.mapping) {
            if (string == null) {
                string = "" + variableScope$VariableDefinition.name;
                continue;
            }
            string = string + ", " + variableScope$VariableDefinition.name;
        }
        return string;
    }

    public void addDefineKey(ab ab2, l l2, String string, String string2, String string3) {
        String string4 = ab2.b(string2, string3, (String)null);
        if (string4 != null && !string4.equals("")) {
            throw new RuntimeException("[" + string2 + "]" + string + ": Unexpected format");
        }
        this.defineVariablesRaw(string, string2, string3);
    }

    public void addDefineValue(l l2, String string, String string2, String string3) {
        this.defineVariablesRaw(string2, string, string3);
    }

    public void defineVariables(l l2, String string) {
        this.defineVariablesRaw("define", "", string);
    }

    public void addSingleDefine(l l2, String string, String string2, String string3, String string4) {
        String string5 = string2.trim().toLowerCase(Locale.ROOT);
        string = string.toLowerCase(Locale.ROOT).trim();
        LogicBoolean$ReturnType logicBoolean$ReturnType = VariableScope.getUserType(string5);
        if (logicBoolean$ReturnType == null) {
            throw new RuntimeException("[" + string3 + "]" + string4 + ": Unknown type: " + string5);
        }
        this.checkNameReserved(string, string3, string4);
        VariableScope$VariableDefinition variableScope$VariableDefinition = this.get(string);
        if (variableScope$VariableDefinition != null) {
            if (variableScope$VariableDefinition.type == logicBoolean$ReturnType) {
                return;
            }
            throw new RuntimeException("[" + string3 + "]" + string4 + ": A memory variable already exists with the name: " + string + " and is a different type: " + variableScope$VariableDefinition.type.name());
        }
        this.create(string, logicBoolean$ReturnType);
    }

    public void defineVariablesRaw(String string, String string2, String string3) {
        String[] stringArray;
        for (String string4 : stringArray = f.c(string3, ',')) {
            if ((string4 = string4.trim()).equals("")) continue;
            int n = string4.indexOf(" ");
            if (n == -1) {
                throw new RuntimeException("[" + string2 + "]" + string + ": Expected 'type name' in each section, got: " + string4);
            }
            String string5 = string4.substring(0, n).toLowerCase(Locale.ROOT).trim();
            String string6 = string4.substring(n, string4.length()).toLowerCase(Locale.ROOT).trim();
            this.addSingleDefine(this.meta, string6, string5, string2, string);
        }
    }

    public void checkNameReserved(String string, String string2, String string3) {
        boolean bl = false;
        if (string.equals("")) {
            bl = true;
        }
        if (string.equals("game") || string.equals("parent") || string.equals("self") || string.equals("this")) {
            bl = true;
        }
        if (string.equals("boolean") || string.equals("bool") || string.equals("unit") || string.equals("void") || string.equals("null") || string.equals("number") || string.equals("float")) {
            bl = true;
        }
        if (bl) {
            throw new RuntimeException("[" + string2 + "]" + string3 + ": Variable cannot be named: '" + string + "'");
        }
        if (string.contains(".") || string.contains("=") || string.contains("(") || string.contains(")") || string.contains("'") || string.contains("\"") || string.contains("?") || string.contains("|") || string.contains("\\") || string.contains("/") || string.contains("[") || string.contains("]") || string.contains(":") || string.contains(";")) {
            throw new RuntimeException("[" + string2 + "]" + string3 + ": Variable name has reserved symbols: '" + string + "'");
        }
        if (string.contains(" ")) {
            throw new RuntimeException("[" + string2 + "]" + string3 + ": Variable name cannot have a space: '" + string + "'");
        }
    }
}
