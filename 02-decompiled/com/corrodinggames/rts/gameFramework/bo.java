/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.gameFramework.bn;
import com.corrodinggames.rts.gameFramework.bp;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;

public strictfp class bo {
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public long k;
    public bn l = new bn();
    private static final byte m = (byte)bp.b.ordinal();

    public void a(as as2) {
        as2.c(m);
        as2.e();
        as2.a(this.a);
        as2.a(this.b);
        as2.a(this.c);
        as2.a(this.d);
        as2.a(this.e);
        as2.a(this.f);
        as2.a(this.g);
        as2.a(this.h);
        as2.a(this.i);
        as2.a(this.j);
        as2.a(this.k);
        this.l.a(as2);
    }

    public void a(k k2) {
        byte by = k2.d();
        k2.a("stats start");
        this.a = k2.f();
        this.b = k2.f();
        this.c = k2.f();
        this.d = k2.f();
        this.e = k2.f();
        this.f = k2.f();
        this.g = k2.f();
        this.h = k2.f();
        this.i = k2.f();
        this.j = k2.f();
        this.k = k2.i();
        if (by >= bp.b.ordinal()) {
            this.l.a(k2);
        }
    }
}
