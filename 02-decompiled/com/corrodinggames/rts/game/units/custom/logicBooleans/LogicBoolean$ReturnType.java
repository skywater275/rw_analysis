/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

public enum LogicBoolean$ReturnType {
    undefined,
    voidReturn,
    bool,
    number,
    unit,
    string,
    point,
    boolArray,
    numberArray,
    unitArray;


    public static boolean canBeNull(LogicBoolean$ReturnType logicBoolean$ReturnType) {
        boolean bl = false;
        if (logicBoolean$ReturnType == string) {
            bl = true;
        }
        if (logicBoolean$ReturnType == point) {
            bl = true;
        }
        if (logicBoolean$ReturnType == unit) {
            bl = true;
        }
        if (logicBoolean$ReturnType == numberArray) {
            bl = true;
        }
        if (logicBoolean$ReturnType == boolArray) {
            bl = true;
        }
        if (logicBoolean$ReturnType == unitArray) {
            bl = true;
        }
        return bl;
    }

    public static boolean isArrayType(LogicBoolean$ReturnType logicBoolean$ReturnType) {
        if (logicBoolean$ReturnType == numberArray) {
            return true;
        }
        if (logicBoolean$ReturnType == boolArray) {
            return true;
        }
        return logicBoolean$ReturnType == unitArray;
    }

    public static LogicBoolean$ReturnType getArrayBaseType(LogicBoolean$ReturnType logicBoolean$ReturnType) {
        if (logicBoolean$ReturnType == boolArray) {
            return bool;
        }
        if (logicBoolean$ReturnType == numberArray) {
            return number;
        }
        if (logicBoolean$ReturnType == unitArray) {
            return unit;
        }
        return null;
    }

    public static LogicBoolean$ReturnType getArrayTypeFromBase(LogicBoolean$ReturnType logicBoolean$ReturnType) {
        if (logicBoolean$ReturnType == bool) {
            return boolArray;
        }
        if (logicBoolean$ReturnType == number) {
            return numberArray;
        }
        if (logicBoolean$ReturnType == unit) {
            return unitArray;
        }
        return null;
    }

    public static String toUserString(LogicBoolean$ReturnType logicBoolean$ReturnType) {
        if (logicBoolean$ReturnType == null) {
            return "<NULL TYPE>";
        }
        if (logicBoolean$ReturnType == numberArray) {
            return "number[]";
        }
        if (logicBoolean$ReturnType == boolArray) {
            return "bool[]";
        }
        if (logicBoolean$ReturnType == unitArray) {
            return "unit[]";
        }
        return logicBoolean$ReturnType.name();
    }
}
