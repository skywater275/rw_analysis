/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.e;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.f;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.e.j;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.utility.y;

public strictfp class d
extends j {
    static e a = null;
    static e b = null;
    static e c = null;
    static e[] d = new e[10];
    int e;
    float f;
    Rect g = new Rect();

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.F;
    }

    public static void f() {
        l l2 = l.B();
        e e2 = l2.bO.a(R$drawable.experimental_tank);
        d = n.a(e2);
        a = l2.bO.a(R$drawable.experimental_tank_dead);
        b = l2.bO.a(R$drawable.experimental_tank_turret);
        c = com.corrodinggames.rts.game.units.e.d.a(e2, e2.m() / 2, e2.l());
    }

    @Override
    public e d() {
        if (this.bV) {
            return a;
        }
        return d[this.bX.R()];
    }

    @Override
    public e k() {
        return c;
    }

    @Override
    public boolean F() {
        return l.B().bQ.renderExtraShadows && this.eq > -2.0f && this.cm >= 1.0f;
    }

    @Override
    public float G() {
        return 4.0f;
    }

    @Override
    public float H() {
        return 4.0f;
    }

    @Override
    public e d(int n2) {
        if (this.R(n2)) {
            return null;
        }
        return b;
    }

    @Override
    public boolean e() {
        l l2 = l.B();
        this.a(com.corrodinggames.rts.game.units.ab.e);
        this.M = a;
        this.S(0);
        this.bT = false;
        return true;
    }

    public d(boolean bl) {
        super(bl);
        this.a(d[7], 2);
        this.cj = 37.0f;
        this.ck = this.cj + 1.0f;
        this.cu = this.cv = 6000.0f;
        this.M = d[7];
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.bV) {
            if (this.cl != 0.0f) {
                this.S(2);
            } else {
                this.S(4);
            }
        }
        if (this.cK) {
            this.f += f2;
            if (this.f > 5.0f) {
                this.f = 0.0f;
                this.e = 1 - this.e;
            }
        }
    }

    @Override
    public float bN() {
        return 80000.0f;
    }

    @Override
    public void a(am am2, int n2) {
        if (!this.R(n2)) {
            PointF pointF = this.E(n2);
            f f2 = com.corrodinggames.rts.game.f.a(this, pointF.a, pointF.b);
            PointF pointF2 = this.K(n2);
            f2.K = pointF2.a;
            f2.L = pointF2.b;
            f2.ar = Color.a(255, 247, 212, 129);
            f2.h = 120.0f;
            f2.t = 5.0f;
            f2.l = am2;
            f2.Y = 60.0f;
            f2.U = 40.0f;
            f2.Z = 45.0f;
            f2.aa = true;
            f2.x = 2.0f;
            f2.aQ = true;
            f2.P = (short)9;
            f2.x = 1.0f;
            f2.em = this.em;
            l l2 = l.B();
            l2.bR.a(pointF.a, pointF.b, this.eq, 16745216);
            l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].a);
            l2.bR.a(f2, -1127220);
            l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.v, 0.3f, this.eo, this.ep);
        } else {
            PointF pointF = this.E(n2);
            pointF.a(this.eo, this.ep);
            f f3 = com.corrodinggames.rts.game.f.a(this, this.eo, this.ep);
            f3.ar = Color.a(255, 230, 230, 50);
            f3.U = 60.0f;
            f3.l = am2;
            f3.h = 190.0f;
            f3.t = 3.0f;
            f3.r = 6.0f;
            f3.aH = true;
            f3.aI = 10.0f;
            f3.aJ = 15.0f;
            f3.aM = true;
            f3.aQ = true;
            f3.aG = true;
            f3.em = this.em;
            l l3 = l.B();
            l3.bM.a(com.corrodinggames.rts.gameFramework.a.e.m, 0.2f, this.eo, this.ep);
            l3.bR.a(f3, -1118720);
            l3.bR.a(pointF.a, pointF.b, this.eq, -1127220);
        }
    }

    @Override
    public boolean a(int n2, am am2, boolean bl, boolean bl2) {
        if (!bl && bl2 && !this.h(am2)) {
            return false;
        }
        return !(this.R(n2) ? !am2.i() : am2.i());
    }

    @Override
    public float m() {
        return 310.0f;
    }

    @Override
    public float b(int n2) {
        if (this.R(n2)) {
            n2 -= 4;
        }
        return 110 - n2 * 20;
    }

    @Override
    public float e(int n2) {
        if (this.R(n2)) {
            n2 -= 4;
        }
        return n2 * 20;
    }

    @Override
    public float z() {
        return 0.4f;
    }

    @Override
    public float bc() {
        return 1.0f;
    }

    @Override
    public int bh() {
        return 1;
    }

    @Override
    public float A() {
        return 0.8f;
    }

    @Override
    public float B() {
        return 0.04f;
    }

    @Override
    public float w(int n2) {
        if (this.R(n2)) {
            return 1.0f;
        }
        return 0.08f;
    }

    @Override
    public float c(int n2) {
        if (this.R(n2)) {
            return 4.5f;
        }
        return 2.5f;
    }

    @Override
    public float C() {
        return 0.03f;
    }

    @Override
    public float D() {
        return 0.08f;
    }

    @Override
    public Rect a_(boolean bl) {
        if (this.bV && !bl) {
            return super.a_(bl);
        }
        if (bl) {
            return super.a_(bl);
        }
        int n2 = 0;
        int n3 = 0;
        this.g.a(n2 += this.e * this.es, n3, n2 + this.es, n3 + this.et);
        return this.g;
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
        return 20.0f;
    }

    @Override
    public PointF G(int n2) {
        PointF pointF = super.G(n2);
        float f2 = pointF.a;
        float f3 = pointF.b;
        if (!this.R(n2)) {
            if (n2 <= 1) {
                f2 += com.corrodinggames.rts.gameFramework.f.k(this.cg) * 5.0f;
                f3 += com.corrodinggames.rts.gameFramework.f.j(this.cg) * 5.0f;
            }
            float f4 = -45 + 90 * n2;
            f2 += com.corrodinggames.rts.gameFramework.f.k(this.cg + f4) * 18.0f;
            f3 += com.corrodinggames.rts.gameFramework.f.j(this.cg + f4) * 18.0f;
        }
        bh.a(f2, f3);
        return bh;
    }

    public boolean R(int n2) {
        return n2 >= 4;
    }

    @Override
    public int bl() {
        return 6;
    }

    @Override
    public void e(float f2) {
        super.e(f2);
        float f3 = this.m();
        y.a((am)this, f3);
    }

    @Override
    public int cw() {
        return 5;
    }

    @Override
    public boolean dd() {
        return true;
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
    public /* synthetic */ as r() {
        return this.b();
    }
}
