/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.e;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.e.j;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.utility.y;

public strictfp class f
extends j {
    static e a = null;
    static e b = null;
    static e[] c = new e[10];
    static e d = null;
    int e;
    float f;
    float g;
    Rect h = new Rect();

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.w;
    }

    public static void f() {
        l l2 = l.B();
        e e2 = l2.bO.a(R$drawable.heavy_tank);
        c = n.a(e2);
        a = l2.bO.a(R$drawable.heavy_tank_dead);
        b = l2.bO.a(R$drawable.heavy_tank_turret);
        d = com.corrodinggames.rts.game.units.e.f.a(e2, e2.m() / 3, e2.l());
    }

    @Override
    public e d() {
        if (this.bV) {
            return a;
        }
        return c[this.bX.R()];
    }

    @Override
    public e k() {
        return d;
    }

    @Override
    public e d(int n2) {
        return b;
    }

    @Override
    public boolean F() {
        return l.B().bQ.renderExtraShadows && !this.bV && this.cm >= 1.0f && !this.cq;
    }

    @Override
    public float G() {
        return 2.0f;
    }

    @Override
    public float H() {
        return 2.0f;
    }

    @Override
    public boolean e() {
        this.M = a;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.ab.c);
        return true;
    }

    public f(boolean bl) {
        super(bl);
        this.a(c[7], 3);
        this.cj = 15.0f;
        this.ck = this.cj + 1.0f;
        this.cu = this.cv = 600.0f;
        this.M = c[7];
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (this.bV) {
            return;
        }
        if (this.cf != 0.0f) {
            this.f += f2;
            if ((double)this.f > 1.4) {
                this.f = 0.0f;
                ++this.e;
                if (this.e > 2) {
                    this.e = 0;
                }
            }
            if (this.el) {
                this.g += f2;
                if (this.g > 9.0f) {
                    this.g = 0.0f;
                    this.K();
                }
            }
        }
    }

    public void K() {
        l l2 = l.B();
        float f2 = this.cg;
        if (this.cf < 0.0f) {
            f2 += 180.0f;
        }
        for (int i2 = 0; i2 <= 1; ++i2) {
            float f3 = i2 == 0 ? -20 : 20;
            float f4 = this.eo + com.corrodinggames.rts.gameFramework.f.k(f2 + 180.0f + f3) * this.cj;
            float f5 = this.ep + com.corrodinggames.rts.gameFramework.f.j(f2 + 180.0f + f3) * this.cj;
            l2.bR.c(f4, f5, this.eq, f2 + 180.0f, 0);
        }
    }

    @Override
    public float bN() {
        return 7000.0f;
    }

    @Override
    public float q(int n2) {
        return 50.0f;
    }

    @Override
    public void a(am am2, int n2) {
        l l2 = l.B();
        if (!am2.i()) {
            PointF pointF = this.E(n2);
            com.corrodinggames.rts.game.f f2 = com.corrodinggames.rts.game.f.a(this, pointF.a, pointF.b);
            PointF pointF2 = this.K(n2);
            f2.K = pointF2.a;
            f2.L = pointF2.b;
            f2.ar = Color.a(235, 150, 230, 40);
            f2.U = this.q(n2);
            f2.l = am2;
            f2.h = 60.0f;
            f2.t = 4.0f;
            f2.x = 2.0f;
            f2.aQ = true;
            f2.z = true;
            l2.bR.a(f2, -16716288);
            l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
            l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].a);
            l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.u, 0.3f, this.eo, this.ep);
        } else {
            PointF pointF = this.E(n2);
            pointF.a(this.eo, this.ep);
            com.corrodinggames.rts.game.f f3 = com.corrodinggames.rts.game.f.a(this, this.eo, this.ep);
            f3.ar = Color.a(255, 230, 230, 50);
            f3.U = this.q(n2);
            f3.l = am2;
            f3.h = 190.0f;
            f3.t = 0.5f;
            f3.r = 5.0f;
            f3.aH = true;
            f3.aI = 10.0f;
            f3.aJ = 15.0f;
            f3.aM = true;
            f3.aQ = true;
            f3.aG = true;
            l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.m, 0.2f, this.eo, this.ep);
            l2.bR.a(f3, -1118720);
            l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
        }
    }

    @Override
    public float m() {
        return 160.0f;
    }

    @Override
    public float b(int n2) {
        return 70.0f;
    }

    @Override
    public float z() {
        return 0.8f;
    }

    @Override
    public float bc() {
        return 1.0f;
    }

    @Override
    public float A() {
        return 1.9f;
    }

    @Override
    public float B() {
        return 0.2f;
    }

    @Override
    public float w(int n2) {
        return 0.12f;
    }

    @Override
    public float c(int n2) {
        return 3.0f;
    }

    @Override
    public float C() {
        return 0.05f;
    }

    @Override
    public float D() {
        return 0.1f;
    }

    @Override
    public boolean c(float f2) {
        if (!super.c(f2)) {
            return false;
        }
        y.a(this);
        return true;
    }

    @Override
    public boolean l() {
        return true;
    }

    @Override
    public boolean af() {
        return true;
    }

    @Override
    public float g(int n2) {
        return 21.0f;
    }

    @Override
    public Rect a_(boolean bl) {
        if (bl) {
            return super.a_(bl);
        }
        if (this.bV) {
            return super.a_(bl);
        }
        return super.a(bl, this.e);
    }

    @Override
    public float H(int n2) {
        return -2.0f;
    }

    @Override
    public float I(int n2) {
        return 4.0f;
    }

    @Override
    public float J(int n2) {
        return 12.0f;
    }

    @Override
    public void e(float f2) {
        super.e(f2);
        float f3 = this.m();
        y.a((am)this, f3);
    }

    @Override
    public /* synthetic */ as r() {
        return this.b();
    }
}
