/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.b;

import com.corrodinggames.rts.game.units.custom.b.d;

public strictfp class e {
    public com.corrodinggames.rts.gameFramework.m.e a;
    public com.corrodinggames.rts.gameFramework.m.e[] b;
    public int c;
    public int d;
    public int e = 1;
    public int f = 1;

    public void a(d d2) {
        e e2 = this;
        int n = e2.a.p;
        int n2 = e2.a.q;
        e2.c = n;
        e2.d = n2;
        if (d2.K > 0) {
            e2.c = d2.K;
        } else if (d2.J > 0) {
            e2.c = n / d2.J;
        }
        if (d2.L > 0) {
            e2.d = d2.L;
        }
        if (e2.c > 0) {
            e2.f = n / e2.c;
        }
        if (e2.d > 0) {
            e2.e = n2 / e2.d;
        }
        if (e2.f <= 0) {
            e2.f = 1;
        }
        if (e2.e <= 0) {
            e2.e = 1;
        }
    }
}
