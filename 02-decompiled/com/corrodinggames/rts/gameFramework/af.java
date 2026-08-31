/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

public abstract class af {
    public int a = -1;
    public int b = 0;
    protected boolean c;
    public boolean d;

    public abstract boolean a();

    public boolean a(af af2) {
        if (this.b != af2.b) {
            return false;
        }
        return this.a == af2.a;
    }

    public abstract boolean b();

    public abstract String c();

    public abstract boolean d();
}
