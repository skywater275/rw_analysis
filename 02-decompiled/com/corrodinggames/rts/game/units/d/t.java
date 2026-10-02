/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.a.o;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.game.units.d.u;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import java.util.ArrayList;

public strictfp class t
extends i {
    static e a = null;
    static e b = null;
    static e c = null;
    static e[] d = new e[10];
    static e[] e = new e[10];
    static e f = null;
    static final c g = com.corrodinggames.rts.game.units.a.c.a(String.valueOf(110));

    public static void b() {
        l l2 = l.B();
        a = l2.bO.a(R$drawable.sea_factory);
        b = l2.bO.a(R$drawable.sea_factory_t2);
        f = l2.bO.a(R$drawable.sea_factory_dead);
        d = com.corrodinggames.rts.game.n.a(a);
        e = com.corrodinggames.rts.game.n.a(b);
    }

    public ar K() {
        return com.corrodinggames.rts.game.units.ar.d;
    }

    @Override
    public boolean L() {
        this.m = null;
        this.M = f;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.ab.d);
        return true;
    }

    @Override
    public e d() {
        if (this.bV) {
            return f;
        }
        if (this.bX == null) {
            return d[d.length - 1];
        }
        if (this.r == 1) {
            return d[this.bX.R()];
        }
        return e[this.bX.R()];
    }

    @Override
    public e k() {
        return null;
    }

    public t(boolean bl) {
        super(bl);
        this.M = a;
        this.b(a);
        this.ck = this.cj = 45.0f;
        this.cu = this.cv = 1000.0f;
        this.S(2);
        this.n.a(-1, -1, 1, 2);
        this.o.a(-2, -1, 2, 4);
    }

    @Override
    public void a(j j2) {
        if (j2.j.equals(g)) {
            com.corrodinggames.rts.game.n.b((am)this);
            this.a(2);
            com.corrodinggames.rts.game.n.c(this);
            this.W();
        } else {
            super.a(j2);
        }
    }

    @Override
    public int dv() {
        return -20;
    }

    @Override
    public int V() {
        return this.r;
    }

    @Override
    public void a(int n2) {
        if (n2 == 1) {
            this.r = 1;
        } else if (n2 == 2 && this.r == 1) {
            this.r = 2;
        }
        this.S();
    }

    @Override
    public c cm() {
        if (this.r == 1) {
            return g;
        }
        return com.corrodinggames.rts.game.units.a.s.i;
    }

    public static void a(ArrayList arrayList, int n2) {
        arrayList.add(new o());
        arrayList.add(new u());
        arrayList.add(new com.corrodinggames.rts.game.units.a.l(com.corrodinggames.rts.game.units.ar.L, 1.0f));
        arrayList.add(new com.corrodinggames.rts.game.units.a.l(com.corrodinggames.rts.game.units.ar.p, 2.0f));
        arrayList.add(new com.corrodinggames.rts.game.units.a.l(com.corrodinggames.rts.game.units.ar.o, 3.0f));
        arrayList.add(new com.corrodinggames.rts.game.units.a.l(com.corrodinggames.rts.game.units.ar.s, 4.0f));
        arrayList.add(new com.corrodinggames.rts.game.units.a.l(com.corrodinggames.rts.game.units.ar.u, 5.0f));
        arrayList.add(new com.corrodinggames.rts.game.units.a.l(com.corrodinggames.rts.game.units.ar.K, 6.0f));
        if (n2 > 1) {
            // empty if block
        }
    }

    @Override
    public ArrayList N() {
        return this.K().a(this.V());
    }

    @Override
    public boolean bJ() {
        return true;
    }

    @Override
    public /* synthetic */ as r() {
        return this.K();
    }
}
