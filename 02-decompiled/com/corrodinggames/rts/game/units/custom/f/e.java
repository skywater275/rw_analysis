/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.f;

import com.corrodinggames.rts.game.units.custom.bo;

public class e {
    public static void a(String string) {
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
