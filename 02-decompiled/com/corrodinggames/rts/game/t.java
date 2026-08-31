/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;

import com.corrodinggames.rts.game.p;

public class t {
    public static final p[] a = new p[0];
    p[] b = a;
    int c = 0;

    public boolean a(p p2) {
        int n = this.c;
        p[] pArray = this.b;
        if (n == pArray.length) {
            p[] pArray2 = new p[n + (n < 6 ? 12 : n >> 1)];
            System.arraycopy(pArray, 0, pArray2, 0, n);
            pArray = pArray2;
            this.b = pArray2;
        }
        pArray[n] = p2;
        this.c = n + 1;
        return true;
    }
}
