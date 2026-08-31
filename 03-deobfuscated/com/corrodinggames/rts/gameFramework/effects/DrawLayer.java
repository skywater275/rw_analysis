/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.effects;

public enum DrawLayer {
    a,
    b,
    c,
    d,
    e;


    public boolean a(DrawLayer h2) {
        if (h2 == null) {
            return true;
        }
        return this.ordinal() < h2.ordinal();
    }
}
