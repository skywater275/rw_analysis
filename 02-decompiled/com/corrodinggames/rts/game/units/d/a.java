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
import com.corrodinggames.rts.game.units.d.b;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import java.util.ArrayList;

public strictfp class a
extends i {
    static e a = null;
    static e b = null;
    static e[] c = new e[10];
    static e[] d = new e[10];
    static e e = null;
    int f = 1;
    float g = 0.0f;
    static final c h = com.corrodinggames.rts.game.units.a.c.a(String.valueOf(110));

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.f);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        if (k2.b() >= 17) {
            int n2 = k2.f();
            this.a(n2);
        }
        super.a(k2);
    }

    public static void b() {
        l l2 = l.B();
        a = l2.bO.a(R$drawable.air_factory);
        b = l2.bO.a(R$drawable.air_factory_t2);
        e = l2.bO.a(R$drawable.air_factory_dead);
        c = com.corrodinggames.rts.game.n.a(a);
        d = com.corrodinggames.rts.game.n.a(b);
    }

    public ar K() {
        return com.corrodinggames.rts.game.units.ar.c;
    }

    @Override
    public boolean L() {
        this.M = e;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.ab.d);
        return true;
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

    public a(boolean bl) {
        super(bl);
        this.M = a;
        this.T(40);
        this.U(61);
        this.ck = this.cj = 30.0f;
        this.cu = this.cv = 1000.0f;
        this.n.a(-1, -1, 1, 1);
        this.o.a(-1, -1, 1, 2);
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.bT() || this.bV) {
            return;
        }
        this.g = com.corrodinggames.rts.gameFramework.f.a(this.g, f2);
        if (this.g == 0.0f) {
            this.g = 27.0f;
            ++this.s;
            if (this.s > 4) {
                this.s = 0;
            }
        }
    }

    @Override
    public void a(j j2) {
        if (j2.j.equals(h)) {
            com.corrodinggames.rts.game.n.b((am)this);
            this.a(2);
            com.corrodinggames.rts.game.n.c(this);
            this.W();
        } else {
            super.a(j2);
        }
    }

    @Override
    public int V() {
        return this.f;
    }

    @Override
    public void a(int n2) {
        if (n2 == 1) {
            this.f = 1;
        } else if (n2 == 2 && this.f == 1) {
            this.f = 2;
        }
        this.S();
    }

    @Override
    public c cm() {
        if (this.f == 1) {
            return h;
        }
        return com.corrodinggames.rts.game.units.a.s.i;
    }

    public static void a(ArrayList arrayList, int n2) {
        arrayList.add(new o());
        if (n2 == 1) {
            arrayList.add(new b());
        }
        if (n2 > 1) {
            arrayList.add(new com.corrodinggames.rts.game.units.a.l(com.corrodinggames.rts.game.units.ar.z, 3.2f));
            arrayList.add(new com.corrodinggames.rts.game.units.a.l(com.corrodinggames.rts.game.units.ar.n, 4.0f));
            arrayList.add(new com.corrodinggames.rts.game.units.a.l(com.corrodinggames.rts.game.units.ar.M, 5.0f));
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
