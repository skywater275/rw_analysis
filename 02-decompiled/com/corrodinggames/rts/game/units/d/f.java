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
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.f$1;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import java.util.ArrayList;

public strictfp class f
extends i {
    static e a = null;
    static e b = null;
    static e[] c = new e[10];
    static e[] d = new e[10];
    static e e = null;
    boolean f;
    static s g = new f$1(110);

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.f);
        as2.c(0);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        boolean bl = k2.e();
        if (bl) {
            this.M();
        }
        k2.d();
        super.a(k2);
    }

    public static void b() {
        l l2 = l.B();
        a = l2.bO.a(R$drawable.experimental_unit_factory_front);
        b = l2.bO.a(R$drawable.experimental_unit_factory_base);
        e = l2.bO.a(R$drawable.experimental_unit_factory_dead);
        c = com.corrodinggames.rts.game.n.a(a);
    }

    public ar K() {
        return com.corrodinggames.rts.game.units.ar.G;
    }

    @Override
    public boolean L() {
        l l2 = l.B();
        this.m = null;
        this.M = e;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.ab.h);
        return true;
    }

    @Override
    public void a(int n2) {
    }

    @Override
    public e d() {
        if (this.bV) {
            return e;
        }
        if (this.bX == null) {
            return c[c.length - 1];
        }
        if (!this.f) {
            return c[this.bX.R()];
        }
        return d[this.bX.R()];
    }

    @Override
    public void S() {
        super.S();
        this.m = this.bV ? null : b;
    }

    @Override
    public e k() {
        return null;
    }

    public f(boolean bl) {
        super(bl);
        this.M = a;
        this.m = b;
        this.b(this.M);
        this.ck = this.cj = 55.0f;
        this.cu = this.cv = 3200.0f;
        this.S(4);
        this.n.a(-2, -2, 2, 2);
        this.o.a(-2, -2, 2, 4);
    }

    @Override
    public void a(j j2) {
        if (j2.j.equals(g.N())) {
            this.M();
        } else {
            super.a(j2);
        }
    }

    public void M() {
        if (!this.f) {
            this.f = true;
            this.S();
        }
    }

    @Override
    public c cm() {
        return com.corrodinggames.rts.game.units.a.s.i;
    }

    public static void a(ArrayList arrayList, int n2) {
        arrayList.add(new o());
        arrayList.add(new com.corrodinggames.rts.game.units.a.l(com.corrodinggames.rts.game.units.ar.F, 2.0f));
        arrayList.add(new com.corrodinggames.rts.game.units.a.l(com.corrodinggames.rts.game.units.ar.O, 3.0f));
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
    public int V() {
        return 2;
    }

    @Override
    public /* synthetic */ as r() {
        return this.K();
    }
}
