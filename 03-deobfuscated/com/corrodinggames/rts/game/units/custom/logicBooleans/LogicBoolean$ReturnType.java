/*
 * ★ 2026-10-01 第 47/49 轮（PENDING RS-27/RS-28）重建：枚举常量的 name() 即 INI 字符串。
 * stock 的 <clinit> 逐条 `new X; dup; ldc "INI串"; iconst_N; invokespecial X.<init>:(String,int)`
 * ⇒ 常量由 (String,int) 直接构造、字符串是 INI 键、字段名才是混淆名。
 * 本文件写成「纯名常量（= INI 字符串）」⇒ javac 生成 (String,int) 且保留字符串 ✓；
 * 常量名由 build_reverse_jar.rename_enum_constants 按 supplement 改成混淆字段名；
 * **常量区之后的原有成员一律原样保留** ✓（第 49 轮修正：整文件覆盖会丢成员 ✗）。
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
