/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.b;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.b.b;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;

public strictfp class f
extends b {
    static e a = null;
    static e b = null;
    static e c = null;
    static e d = null;
    static e e = null;
    static e[] f = new e[10];
    boolean g = false;
    float o;
    float p = 0.0f;
    float q;
    Rect r = new Rect();
    Rect s = new Rect();

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.p);
        as2.a(this.o);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        if (k2.b() >= 9) {
            this.p = k2.g();
            this.o = k2.g();
            if (k2.b() == 8) {
                this.g = k2.e();
            }
        } else {
            this.o = 0.5f;
        }
        super.a(k2);
    }

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.l;
    }

    public static void f() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        b = l2.bO.a(R$drawable.helicopter);
        c = l2.bO.a(R$drawable.helicopter_blades);
        d = l2.bO.a(R$drawable.helicopter_shadow);
        e = l2.bO.a(R$drawable.helicopter_shadow_blades);
        a = l2.bO.a(R$drawable.helicopter_dead);
        f = com.corrodinggames.rts.game.n.a(b);
    }

    @Override
    public e d() {
        if (this.bV) {
            return a;
        }
        return f[this.bX.R()];
    }

    @Override
    public e k() {
        return d;
    }

    @Override
    public e d(int n2) {
        return null;
    }

    @Override
    public boolean e() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bR.b(this.eo, this.ep, this.eq);
        this.M = a;
        this.S(0);
        this.bT = false;
        return true;
    }

    public f(boolean bl) {
        super(bl);
        this.T(26);
        this.U(46);
        this.cj = 13.0f;
        this.ck = this.cj + 2.0f;
        this.cu = this.cv = 150.0f;
        this.M = b;
        this.N = d;
        this.eq = 0.0f;
        this.o = 0.14f;
        this.q = 0.0f;
        this.S(5);
    }

    @Override
    public void n() {
        super.n();
        this.eq = 20.0f;
        this.o = 0.5f;
    }

    @Override
    public boolean I() {
        return true;
    }

    @Override
    public boolean i() {
        return true;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (this.bV) {
            return;
        }
        this.o = com.corrodinggames.rts.gameFramework.f.a(this.o, 0.5f, 0.003f * f2);
        this.q += 70.0f * this.o * f2;
        if (this.q >= 360.0f) {
            this.q -= 360.0f;
            this.q += (float)com.corrodinggames.rts.gameFramework.f.a(this, 0, 4);
        }
        if (this.o > 0.4f) {
            this.p += 2.0f * f2;
            if (this.p > 360.0f) {
                this.p -= 360.0f;
            }
            this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, 20.0f + com.corrodinggames.rts.gameFramework.f.j(this.p) * 1.5f, 0.1f * f2);
        }
    }

    @Override
    public void a(am am2, int n2) {
        PointF pointF = this.E(n2);
        com.corrodinggames.rts.game.f f2 = com.corrodinggames.rts.game.f.a((am)this, pointF.a, pointF.b, this.eq, n2);
        PointF pointF2 = this.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.U = 17.0f;
        f2.l = am2;
        f2.h = 30.0f;
        f2.t = 8.0f;
        f2.S = false;
        f2.ar = Color.a(255, 180, 180, 0);
        f2.A = true;
        f2.aR = false;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        float f3 = 1.0f + com.corrodinggames.rts.gameFramework.f.c(-0.08f, 0.08f);
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.s, 0.2f, f3, pointF.a, pointF.b);
        l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].a);
        l2.bR.a(pointF.a, pointF.b, this.eq, -1118720);
    }

    @Override
    public float m() {
        return 130.0f;
    }

    @Override
    public float b(int n2) {
        return 60.0f;
    }

    @Override
    public float z() {
        if (this.eq < 15.0f) {
            return 0.0f;
        }
        return 2.2f;
    }

    @Override
    public float bc() {
        return 0.1f;
    }

    @Override
    public float A() {
        return 6.0f;
    }

    @Override
    public float B() {
        return 0.4f;
    }

    @Override
    public boolean bi() {
        return true;
    }

    @Override
    public boolean bj() {
        return true;
    }

    @Override
    public float c(int n2) {
        return 16.0f;
    }

    @Override
    public Rect a_(boolean bl) {
        return super.a_(bl);
    }

    @Override
    public boolean c(float f2) {
        if (!super.c(f2)) {
            return false;
        }
        if (!this.bV) {
            Paint paint = this.aN();
            l l2 = com.corrodinggames.rts.gameFramework.l.B();
            this.s.a(0, 0, c.m(), c.l());
            float f3 = this.q;
            if (this.co) {
                // empty if block
            }
            l2.bO.a(c, this.s, this.eo - com.corrodinggames.rts.gameFramework.l.B().cw, this.ep - com.corrodinggames.rts.gameFramework.l.B().cx - this.eq, f3, paint);
        }
        return true;
    }

    @Override
    public float C() {
        return 0.07f;
    }

    @Override
    public float D() {
        return 0.1f;
    }

    @Override
    public boolean l() {
        return true;
    }

    @Override
    public float g(int n2) {
        return 7.0f;
    }

    @Override
    public /* synthetic */ as r() {
        return this.b();
    }
}
