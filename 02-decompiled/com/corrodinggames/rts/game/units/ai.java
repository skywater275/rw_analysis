/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import android.graphics.Point;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.b.g;
import com.corrodinggames.rts.game.f;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.aj;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.v;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.w;

public class ai
extends v {
    static e[] a = new e[2];
    e b;
    int c;
    int d = 0;
    float e;
    float f;
    int g = 0;
    int h = 0;
    float i;
    float j;
    boolean k = false;
    float l;
    float m;
    float n;
    float o;
    float p;
    float q;
    boolean r;
    static Point s = new Point();
    public static aj t = new aj();
    Rect u = new Rect();

    public static void b() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        ai.a[0] = l2.bO.a(R$drawable.fire);
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.c);
        as2.a(this.d);
        as2.a(this.e);
        as2.c(0);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        this.c = k2.f();
        this.d = k2.f();
        this.e = k2.g();
        k2.d();
        super.a(k2);
    }

    public e d() {
        return this.b;
    }

    @Override
    public boolean e() {
        return false;
    }

    public ai(boolean bl) {
        super(bl);
        this.a(0);
        this.cj = 20.0f;
        this.ck = this.cj + 1.0f;
        this.cu = this.cv = 100.0f;
        this.cg = -90.0f;
        this.bT = false;
        this.o = 0.05f;
        this.p = 120.0f;
        this.S(3);
    }

    @Override
    public void f_() {
        this.bT = false;
    }

    public void a(int n2) {
        this.c = n2;
        if (this.c != 0) {
            throw new RuntimeException("Fire type:" + this.c + " is not supported");
        }
        this.T(20);
        this.U(20);
        this.g = 0;
        this.h = 0;
        this.b = a[0];
    }

    public void f() {
        this.k = true;
        this.i = com.corrodinggames.rts.gameFramework.f.a((w)this, -5, 5, 1);
        this.j = com.corrodinggames.rts.gameFramework.f.a((w)this, -5, 5, 2);
        this.e = com.corrodinggames.rts.gameFramework.f.a((w)this, 1, 10, 3);
        this.d = com.corrodinggames.rts.gameFramework.f.a((w)this, 0, 2, 4);
        this.f = com.corrodinggames.rts.gameFramework.f.a((w)this, 7, 13, 5);
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        b b2 = l2.bL;
        l2.bL.a(this.eo, this.ep);
        int n2 = l2.bL.T;
        int n3 = l2.bL.U;
        if (!b2.c(n2, n3)) {
            this.l = 0.0f;
            this.m = 0.0f;
            this.n = 2.0f;
            return;
        }
        g g2 = l2.bL.u.a(n2, n3);
        boolean bl = false;
        if (g2.e || g2.h || g2.k || g2.f) {
            bl = true;
        }
        if (bl) {
            this.l = 0.0f;
            this.m = 0.0f;
            this.n = 2.0f;
            return;
        }
        this.l = 5.0E-4f;
        this.m = 1.0f;
        this.n = 0.3f;
        this.o += (float)com.corrodinggames.rts.gameFramework.f.a((w)this, 0, 10, 10) / 1000.0f;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.k) {
            this.f();
        }
        if (this.o < this.m) {
            this.o += this.l * f2;
            if (this.o > this.m) {
                this.o = this.m;
            }
        }
        if (this.o > this.n) {
            this.q = (float)((double)this.q + 0.01 * (double)f2);
            if (!this.r && this.q > 1.0f || this.q > 8.0f) {
                this.q = (float)com.corrodinggames.rts.gameFramework.f.a((w)this, 0, 10, 10) / 1000.0f;
                this.k();
            }
        }
        this.e += f2;
        if (this.e > 10.0f) {
            this.e = 0.0f;
            ++this.d;
            if (this.d > 3) {
                this.d = 0;
            }
        }
        if (this.o < 0.0f) {
            this.bv();
        }
    }

    public void k() {
        this.r = true;
        this.b(-1, -1);
        this.b(0, -1);
        this.b(1, -1);
        this.b(-1, 0);
        this.b(1, 0);
        this.b(-1, 1);
        this.b(0, 1);
        this.b(1, 1);
    }

    public void b(int n2, int n3) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        float f2 = (int)(this.eo + (float)(n2 * l2.bL.n));
        float f3 = (int)(this.ep + (float)(n3 * l2.bL.o));
        ai ai2 = ai.a(f2, f3);
        if (ai2 == null) {
            ai ai3 = new ai(false);
            ai3.eo = f2;
            ai3.ep = f3;
            ai3.b(this.bX);
            l2.cc.a(ai3);
            com.corrodinggames.rts.game.n.c(ai3);
            this.r = false;
        }
    }

    public static ai a(float f2, float f3) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        t.a(f2, f3);
        l2.cc.a(f2, f3, 30.0f, null, 1.0f, t);
        return ai.t.c;
    }

    @Override
    public Rect a_(boolean bl) {
        int n2 = this.g;
        int n3 = this.h;
        dC.a(n2 += this.d * this.es, n3, n2 + this.es, n3 + this.et);
        return dC;
    }

    @Override
    public boolean c(float f2) {
        e e2 = this.d();
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        du.a(this.cF());
        du.a(0.0f, (int)(-this.eq));
        du.a(this.i, this.j);
        dv.a(this.a_(false));
        l2.bO.k();
        float f3 = du.d();
        float f4 = du.e();
        l2.bO.a(this.d(false), f3, f4);
        l2.bO.a(this.o * 2.7f, this.o * 2.7f, f3, f4);
        l2.bO.a(e2, dv, du, null);
        l2.bO.l();
        return true;
    }

    @Override
    public ao h() {
        return ao.a;
    }

    @Override
    public boolean i() {
        return false;
    }

    @Override
    public boolean Q() {
        return false;
    }

    @Override
    public boolean ak() {
        return false;
    }

    @Override
    public boolean aj() {
        return false;
    }

    @Override
    public boolean s_() {
        return true;
    }

    @Override
    public boolean c_() {
        return false;
    }

    public ar s() {
        return ar.S;
    }

    @Override
    public void n() {
        super.n();
    }

    @Override
    public float x() {
        return -1.0f;
    }

    @Override
    public boolean l() {
        return false;
    }

    @Override
    public boolean P() {
        return true;
    }

    @Override
    public float a(am am2, float f2, f f3) {
        this.o -= f2 / 100.0f;
        f2 = 0.0f;
        return super.a(am2, f2, f3);
    }

    @Override
    public /* synthetic */ as r() {
        return this.s();
    }
}
