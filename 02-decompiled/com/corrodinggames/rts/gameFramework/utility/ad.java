/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

public class ad {
    private final Object[] a;
    private int b;
    private final boolean c = false;

    public ad(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.a = new Object[n];
    }

    public Object a() {
        if (this.b > 0) {
            int n = this.b - 1;
            Object object = this.a[n];
            this.a[n] = null;
            --this.b;
            return object;
        }
        return null;
    }

    public boolean a(Object object) {
        if (this.b < this.a.length) {
            this.a[this.b] = object;
            ++this.b;
            return true;
        }
        return false;
    }
}
