/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

strictfp enum n {
    a,
    b,
    c,
    d,
    e,
    f,
    g,
    h,
    i;


    public static n a(String string) {
        try {
            return n.valueOf(string);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            return null;
        }
    }
}
