/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.config;

import com.corrodinggames.rts.game.units.custom.bo;

public class e {
    /* 02b f/e.java 对应: 抛 checked bo (R8 移除 throws) */
    public static void reset(String string) throws bo {
        String string2 = string;
        if (string2.length() == 0) {
            throw new bo("name cannot be empty");
        }
        if (string2.contains(" ") || string2.contains("}") || string2.contains("$") || string2.contains(".") || string2.contains("{") || string2.contains("-") || string2.contains("+") || string2.contains(":") || string2.contains("(")) {
            throw new bo("invalid character in name");
        }
        if (Character.isDigit(string2.charAt(0))) {
            throw new bo("name cannot start with a digit");
        }
    }
}
