/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d;

import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.game.units.d.k;
import com.corrodinggames.rts.game.units.d.o;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import java.util.ArrayList;

public strictfp class m
extends i {
    static e a = null;
    static e b = null;
    static e c = null;
    static e[] d = new e[10];
    static e[] e = new e[10];
    static e f = null;
    boolean g;
    static final c h = com.corrodinggames.rts.game.units.a.c.a(String.valueOf(110));

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.g);
        as2.c(0);
        super.a(as2);
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.k k2) {
        boolean bl = k2.e();
        if (bl) {
            this.a(2);
        }
        k2.d();
        super.a(k2);
    }

    public static void b() {
        l l2 = l.B();
        a = l2.bO.a(R$drawable.land_factory_front);
        b = l2.bO.a(R$drawable.land_factory_front_t2);
        c = l2.bO.a(R$drawable.land_factory_back);
        f = l2.bO.a(R$drawable.land_factory_dead);
        d = com.corrodinggames.rts.game.n.a(a);
        e = com.corrodinggames.rts.game.n.a(b);
    }

    public ar K() {
        return com.corrodinggames.rts.game.units.ar.b;
    }

    @Override
    public boolean L() {
        l l2 = l.B();
        l2.bR.a(this.eo, this.ep, this.eq);
        this.m = null;
        this.M = f;
        this.S(0);
        this.bT = false;
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.p, 0.8f, this.eo, this.ep);
        return true;
    }

    @Override
    public void S() {
        super.S();
        this.m = this.bV ? null : c;
    }

    @Override
    public e d() {
        if (this.bV) {
            return f;
        }
        if (this.bX == null) {
            return d[d.length - 1];
        }
        if (!this.g) {
            return d[this.bX.R()];
        }
        return e[this.bX.R()];
    }

    @Override
    public e k() {
        return null;
    }

    public m(boolean bl) {
        super(bl);
        this.M = a;
        this.m = c;
        this.b(this.M);
        this.ck = this.cj = 30.0f;
        this.cu = this.cv = 1200.0f;
        this.S(3);
        this.n.a(-1, -1, 1, 1);
        this.o.a(-1, -1, 1, 3);
    }

    @Override
    public void a(j j2) {
        if (h.a(j2.j)) {
            com.corrodinggames.rts.game.n.b((am)this);
            this.a(2);
            com.corrodinggames.rts.game.n.c(this);
            this.W();
        } else {
            super.a(j2);
        }
    }

    @Override
    public void a(int n2) {
        if (n2 == 1) {
            this.g = false;
        } else if (n2 == 2 && !this.g) {
            this.g = true;
        }
        this.S();
    }

    @Override
    public c cm() {
        if (!this.g) {
            return h;
        }
        return com.corrodinggames.rts.game.units.a.s.i;
    }

    public static void a(ArrayList arrayList, int n2) {
        arrayList.add(new com.corrodinggames.rts.game.units.a.o());
        if (n2 == 1) {
            arrayList.add(new com.corrodinggames.rts.game.units.d.n());
        }
        arrayList.add(new com.corrodinggames.rts.game.units.a.l(com.corrodinggames.rts.game.units.ar.h, 1.0f));
        arrayList.add(new com.corrodinggames.rts.game.units.a.l(com.corrodinggames.rts.game.units.ar.i, 2.0f));
        arrayList.add(new com.corrodinggames.rts.game.units.a.l(com.corrodinggames.rts.game.units.ar.j, 3.0f));
        arrayList.add(new com.corrodinggames.rts.game.units.a.l(com.corrodinggames.rts.game.units.ar.k, 4.0f));
        if (n2 >= 2) {
            arrayList.add(new com.corrodinggames.rts.game.units.a.l(com.corrodinggames.rts.game.units.ar.s, 5.0f));
            arrayList.add(new com.corrodinggames.rts.game.units.a.l(com.corrodinggames.rts.game.units.ar.w, 6.0f));
            arrayList.add(new com.corrodinggames.rts.game.units.a.l(com.corrodinggames.rts.game.units.ar.x, 7.0f));
            arrayList.add(new com.corrodinggames.rts.game.units.a.l(com.corrodinggames.rts.game.units.ar.r, 8.0f));
        }
    }

    @Override
    public ArrayList N() {
        return this.K().a(this.V());
    }

    @Override
    public int V() {
        if (this.g) {
            return 2;
        }
        return 1;
    }

    @Override
    public k du() {
        return new o(this);
    }

    @Override
    public boolean bJ() {
        return true;
    }

    @Override
    public float db() {
        return super.db() - 8.0f;
    }

    @Override
    public /* synthetic */ as r() {
        return this.K();
    }
}
