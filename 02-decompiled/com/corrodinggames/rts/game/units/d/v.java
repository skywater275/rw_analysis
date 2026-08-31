/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.game.units.d.v$1;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import java.util.ArrayList;

public class v
extends i {
    static e a = null;
    static e b = null;
    static e[] c = new e[10];
    static e[] d = new e[10];
    static e e = null;
    int f = 1;
    float g = 0.0f;
    int h = 0;
    public static int i = 0;
    static s j = new v$1(102);
    static ArrayList k = new ArrayList();

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.f);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        int n2 = k2.f();
        this.a(n2);
        super.a(k2);
    }

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.N;
    }

    public static void K() {
        l l2 = l.B();
        a = l2.bO.a(R$drawable.supply_depot);
        b = l2.bO.a(R$drawable.supply_depot_t2);
        c = com.corrodinggames.rts.game.n.a(a);
        d = com.corrodinggames.rts.game.n.a(b);
        e = l2.bO.a(R$drawable.supply_depot_dead);
    }

    @Override
    public boolean L() {
        l l2 = l.B();
        l2.bR.a(this.eo, this.ep, this.eq);
        this.M = e;
        this.S(0);
        this.bT = false;
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.p, 0.8f, this.eo, this.ep);
        return false;
    }

    @Override
    public e d() {
        if (this.bV) {
            return e;
        }
        if (this.bX == null) {
            return c[c.length - 1];
        }
        if (this.f == 1) {
            return c[this.bX.R()];
        }
        return d[this.bX.R()];
    }

    @Override
    public e k() {
        return null;
    }

    public v(boolean bl) {
        super(bl);
        this.M = a;
        this.a(this.M, 1);
        this.ck = this.cj = 20.0f;
        this.cu = this.cv = 800.0f;
        this.n.a(-1, -1, 0, 0);
        this.o.a(this.n);
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.bT() || this.bV) {
            return;
        }
    }

    @Override
    public void a(j j2) {
        if (j2.j.equals(j.N())) {
            this.M();
            this.W();
        }
    }

    @Override
    public void a(int n2) {
        this.f = n2;
    }

    public void M() {
        if (this.f == 1) {
            this.f = 2;
            this.S();
        }
    }

    @Override
    public c cm() {
        if (this.f == 1) {
            return j.N();
        }
        return com.corrodinggames.rts.game.units.a.s.i;
    }

    @Override
    public ArrayList N() {
        return k;
    }

    @Override
    public /* synthetic */ as r() {
        return this.b();
    }

    static {
        k.add(j);
    }
}
