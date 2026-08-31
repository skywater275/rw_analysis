/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.utility;

public class ad {
    private final Object[] a;
    private int poolIndex;
    private final boolean isFixedSize = false;

    public ad(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.a = new Object[n];
    }

    public Object isEnabled() {
        if (this.poolIndex > 0) {
            int n = this.poolIndex - 1;
            Object object = this.a[n];
            this.a[n] = null;
            --this.poolIndex;
            return object;
        }
        return null;
    }

    public boolean isEnabled(Object object) {
        if (this.poolIndex < this.a.length) {
            this.a[this.poolIndex] = object;
            ++this.poolIndex;
            return true;
        }
        return false;
    }
}
