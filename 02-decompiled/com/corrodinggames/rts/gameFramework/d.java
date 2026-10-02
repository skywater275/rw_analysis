/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework;

import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;

public strictfp class d {
    public com.corrodinggames.rts.gameFramework.k.k a;
    public long b;
    public float c;
    public float d;
    public float e;
    public float f;
    public int g;
    public ao h;

    public void a(as as2) {
        as2.a(this.b);
        as2.a(this.c);
        as2.a(this.d);
        as2.a(this.e);
        as2.a(this.f);
        as2.a(this.g);
        as2.a(this.h);
        as2.a(this.a != null);
        if (this.a != null) {
            this.a.a(as2);
        }
    }

    public void a(k k2) {
        this.b = k2.i();
        this.c = k2.g();
        this.d = k2.g();
        this.e = k2.g();
        this.f = k2.g();
        this.g = k2.f();
        this.h = (ao)k2.b(ao.class);
        boolean bl = k2.e();
        if (bl) {
            boolean bl2 = false;
            this.a = new com.corrodinggames.rts.gameFramework.k.k(null, bl2);
            this.a.a(k2);
        }
    }
}
