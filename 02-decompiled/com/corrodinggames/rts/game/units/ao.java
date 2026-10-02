/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import java.util.Locale;

public strictfp enum ao {
    a,
    b,
    c,
    d,
    e,
    f,
    g,
    h;


    public static ao a(String string, String string2) {
        try {
            return ao.valueOf(string.toUpperCase(Locale.ROOT));
        }
        catch (IllegalArgumentException illegalArgumentException) {
            String string3 = "";
            for (ao ao2 : ao.values()) {
                string3 = string3 + ", " + ao2.toString();
            }
            throw new IllegalArgumentException("Unknown movement type:'" + string + "' possible type:" + string3 + " on key:" + string2);
        }
    }
}
