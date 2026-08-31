/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

public strictfp enum DirectionType {
    a,
    b,
    c,
    d,
    e,
    f,
    g,
    h,
    i;


    public static DirectionType a(String string) {
        try {
            return DirectionType.valueOf(string);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            return null;
        }
    }
}
