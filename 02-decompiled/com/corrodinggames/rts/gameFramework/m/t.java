/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.m;

import com.corrodinggames.rts.gameFramework.m.s;

public final class t {
    public int a;
    public s[] b;

    public t(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("capacity < 0: " + n);
        }
        this.b = n == 0 ? new s[]{} : new s[n];
    }

    public final boolean a(s s2) {
        int n = this.a;
        s[] sArray = this.b;
        if (n == sArray.length) {
            s[] sArray2 = new s[n + (n < 6 ? 12 : n >> 1)];
            System.arraycopy(sArray, 0, sArray2, 0, n);
            sArray = sArray2;
            this.b = sArray2;
        }
        sArray[n] = s2;
        this.a = n + 1;
        return true;
    }
}
