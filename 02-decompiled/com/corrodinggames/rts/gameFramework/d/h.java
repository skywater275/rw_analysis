/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.d;

public enum h {
    a,
    b,
    c,
    d,
    e;


    public boolean a(h h2) {
        if (h2 == null) {
            return true;
        }
        return this.ordinal() < h2.ordinal();
    }
}
