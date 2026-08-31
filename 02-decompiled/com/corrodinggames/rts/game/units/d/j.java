/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.j.k;

public strictfp class j
extends bq {
    public int a;
    public float b;
    public b c = com.corrodinggames.rts.game.units.custom.d.b.a;
    public b d = null;
    public h e;
    public boolean f;
    public as g;
    public PointF h;
    public am i;
    public c j = s.i;
    public boolean k;
    public boolean l;
    public float m = -1.0f;
    public double n = 0.0;

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(-1);
        as2.a(this.a);
        as2.a(this.b);
        as2.a(-1);
        as2.a(this.c.a());
        as2.a(this.f);
        as2.c(this.j.a());
        as2.c(this.j.a());
        as2.b(this.i);
        as2.a(this.h);
        as2.a(this.l);
        as2.a(this.m);
        as2.a(this.g);
        this.c.a(as2);
        com.corrodinggames.rts.game.units.custom.d.b.a(as2, this.d);
        com.corrodinggames.rts.game.units.custom.g.a(this.e, as2);
    }

    public void a(k k2) {
        String.valueOf(k2.f());
        this.a = k2.f();
        this.b = k2.g();
        int n2 = 0;
        if (k2.b() >= 4) {
            this.j = com.corrodinggames.rts.game.units.a.c.a(String.valueOf(k2.f()));
        }
        if (k2.b() >= 6) {
            n2 = k2.f();
        }
        if (k2.b() >= 25) {
            this.f = k2.e();
        }
        if (k2.b() >= 33) {
            k2.l();
            this.j = com.corrodinggames.rts.game.units.a.c.a(k2.l());
        }
        if (k2.b() >= 61) {
            this.i = k2.o();
            this.h = k2.y();
        }
        if (k2.b() >= 64) {
            this.l = k2.e();
            this.m = k2.g();
        }
        if (k2.b() >= 71) {
            this.g = k2.q();
        }
        this.c = k2.b() >= 73 ? com.corrodinggames.rts.game.units.custom.d.b.b(k2) : com.corrodinggames.rts.game.units.custom.d.b.a(n2);
        if (k2.b() >= 91) {
            this.d = com.corrodinggames.rts.game.units.custom.d.b.a(k2);
        }
        if (k2.b() >= 95) {
            this.e = com.corrodinggames.rts.game.units.custom.g.a(k2);
        }
    }
}
