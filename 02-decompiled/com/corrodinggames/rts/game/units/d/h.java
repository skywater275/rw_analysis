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
import com.corrodinggames.rts.game.units.d.h$1;
import com.corrodinggames.rts.game.units.d.h$2;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import java.util.ArrayList;

public class h
extends i {
    static e a = null;
    static e b = null;
    static e c = null;
    static e[] d = new e[10];
    static e[] e = new e[10];
    static e[] f = new e[10];
    static e g = null;
    float h;
    float i = 0.0f;
    int j = 0;
    static s k = new h$1(102);
    static s l = new h$2(103);
    static ArrayList t = new ArrayList();

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.h);
        as2.a(this.r == 2);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        this.h = k2.g();
        boolean bl = k2.e();
        if (k2.b() < 51 && bl) {
            this.a(2);
        }
        super.a(k2);
    }

    @Override
    public void R(int n2) {
        this.a(n2);
    }

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.J;
    }

    public static void K() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        a = l2.bO.a(R$drawable.power);
        b = l2.bO.a(R$drawable.power_t2);
        c = l2.bO.a(R$drawable.power_t3);
        d = com.corrodinggames.rts.game.n.a(a);
        e = com.corrodinggames.rts.game.n.a(b);
        f = com.corrodinggames.rts.game.n.a(c);
        g = l2.bO.a(R$drawable.power_dead);
    }

    @Override
    public boolean L() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bR.a(this.eo, this.ep, this.eq);
        this.M = g;
        this.S(0);
        this.bT = false;
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.p, 0.8f, this.eo, this.ep);
        l2.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
        com.corrodinggames.rts.gameFramework.d.e e2 = l2.bR.c(this.eo, this.ep, this.eq, -1127220);
        if (e2 != null) {
            e2.G = 0.15f;
            e2.F = 1.0f;
            e2.ar = (short)2;
            e2.W = e2.V = 35.0f;
            e2.U = 0.0f;
            e2.x = -14492382;
        }
        this.bo();
        return true;
    }

    @Override
    public e d() {
        if (this.bV) {
            return g;
        }
        if (this.bX == null) {
            return d[d.length - 1];
        }
        if (this.r == 1) {
            return d[this.bX.R()];
        }
        if (this.r == 2) {
            return e[this.bX.R()];
        }
        if (this.r == 3) {
            return f[this.bX.R()];
        }
        com.corrodinggames.rts.gameFramework.l.e("Unknown tech level:" + this.r);
        return d[this.bX.R()];
    }

    @Override
    public e k() {
        return null;
    }

    public h(boolean bl) {
        super(bl);
        this.M = a;
        this.a(this.M, 3);
        this.ck = this.cj = 25.0f;
        this.cu = this.cv = 800.0f;
        this.n.a(-1, -1, 1, 1);
        this.o.a(this.n);
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.bT() || this.bV) {
            return;
        }
        this.i = com.corrodinggames.rts.gameFramework.f.a(this.i, f2);
        if (this.i == 0.0f) {
            this.i = 17.0f;
            ++this.j;
            if (this.j > 5) {
                this.j = 0;
            }
            this.s = this.j <= 2 ? this.j : 5 - this.j;
        }
        this.h += f2;
        if (this.h > com.corrodinggames.rts.game.n.ap - 0.1f) {
            this.h -= com.corrodinggames.rts.game.n.ap;
            this.bX.b(this.cy() * (com.corrodinggames.rts.game.n.ap / com.corrodinggames.rts.game.n.ao));
        }
    }

    @Override
    public float cy() {
        if (this.r == 1) {
            return 2.0f;
        }
        if (this.r == 2) {
            return 7.0f;
        }
        return 14.0f;
    }

    @Override
    public void a(j j2) {
        if (j2.j.equals(k.N())) {
            this.a(2);
            this.W();
        }
        if (j2.j.equals(l.N())) {
            this.a(3);
            this.W();
        }
    }

    @Override
    public c cm() {
        if (this.r == 1) {
            return k.N();
        }
        if (this.r == 2) {
            return l.N();
        }
        return com.corrodinggames.rts.game.units.a.s.i;
    }

    @Override
    public int V() {
        return this.r;
    }

    @Override
    public void a(int n2) {
        com.corrodinggames.rts.game.n.b((am)this);
        if (this.r > n2) {
            this.r = 1;
            this.cv = 800.0f;
            if (this.cu > this.cv) {
                this.cu = this.cv;
            }
        }
        if (this.r < 2 && n2 >= 2) {
            this.cv += 500.0f;
            this.cu += 500.0f;
        }
        if (this.r < 3 && n2 >= 3) {
            this.cv += 1300.0f;
            this.cu += 1300.0f;
        }
        this.r = n2;
        com.corrodinggames.rts.game.n.c(this);
        this.S();
    }

    @Override
    public ArrayList N() {
        return t;
    }

    @Override
    public float db() {
        return super.db() - 8.0f;
    }

    @Override
    public int bp() {
        return 12;
    }

    @Override
    public /* synthetic */ as r() {
        return this.b();
    }

    static {
        t.add(k);
        t.add(l);
    }
}
