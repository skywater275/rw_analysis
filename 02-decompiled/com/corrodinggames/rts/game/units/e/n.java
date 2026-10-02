/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.e;

import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.f;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.e.j;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.utility.y;

public strictfp class n
extends j {
    static e a = null;
    static e b = null;
    static e c = null;
    static e d = null;
    static e[] e = new e[10];
    int f;
    float g;
    float h;
    Rect i = new Rect();

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.i;
    }

    public static void f() {
        l l2 = l.B();
        b = l2.bO.a(R$drawable.tank2);
        a = l2.bO.a(R$drawable.tank2_dead);
        c = l2.bO.a(R$drawable.tank2_turret);
        d = l2.bO.a(R$drawable.tank2_shadow);
        e = com.corrodinggames.rts.game.n.a(b);
    }

    @Override
    public e d() {
        if (this.bV) {
            return a;
        }
        return e[this.bX.R()];
    }

    @Override
    public e k() {
        return d;
    }

    @Override
    public boolean F() {
        return l.B().bQ.renderExtraShadows && !this.bV;
    }

    @Override
    public float G() {
        return 3.0f;
    }

    @Override
    public float H() {
        return 3.0f;
    }

    @Override
    public e d(int n2) {
        return c;
    }

    @Override
    public boolean e() {
        this.M = a;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.ab.b);
        return true;
    }

    public n(boolean bl) {
        super(bl);
        this.a(b, 3);
        this.cj = 11.0f;
        this.ck = this.cj + 1.0f;
        this.cu = this.cv = 210.0f;
        this.M = b;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (this.bV) {
            return;
        }
        if (this.cf != 0.0f) {
            this.g += f2;
            if (this.g > 1.0f) {
                this.g = 0.0f;
                ++this.f;
                if (this.f > 2) {
                    this.f = 0;
                }
            }
            if (this.cf > 0.0f && this.el) {
                this.h += f2;
                if (this.h > 9.0f) {
                    this.h = 0.0f;
                    this.K();
                }
            }
        }
    }

    public void K() {
        l l2 = l.B();
        for (int i2 = 0; i2 <= 1; ++i2) {
            float f2 = i2 == 0 ? -20 : 20;
            float f3 = this.eo + com.corrodinggames.rts.gameFramework.f.k(this.cg + 180.0f + f2) * this.cj;
            float f4 = this.ep + com.corrodinggames.rts.gameFramework.f.j(this.cg + 180.0f + f2) * this.cj;
            l2.bR.c(f3, f4, this.eq, this.cg + 180.0f, 0);
        }
    }

    @Override
    public void a(am am2, int n2) {
        PointF pointF = this.E(n2);
        f f2 = com.corrodinggames.rts.game.f.a(this, pointF.a, pointF.b);
        PointF pointF2 = this.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.U = 30.0f;
        f2.l = am2;
        f2.h = 60.0f;
        f2.t = 3.0f;
        f2.P = 1;
        f2.x = 1.0f;
        l l2 = l.B();
        l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
        l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].a);
        float f3 = 1.0f + com.corrodinggames.rts.gameFramework.f.c(-0.07f, 0.07f);
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.q, 0.3f, f3, pointF.a, pointF.b);
    }

    @Override
    public float m() {
        return 130.0f;
    }

    @Override
    public float b(int n2) {
        return 75.0f;
    }

    @Override
    public float z() {
        return 1.0f;
    }

    @Override
    public float A() {
        return 4.1f;
    }

    @Override
    public float c(int n2) {
        return 4.0f;
    }

    @Override
    public float B() {
        return 0.25f;
    }

    @Override
    public boolean c(float f2) {
        if (!super.c(f2)) {
            return false;
        }
        y.a(this);
        if (!this.bV) {
            // empty if block
        }
        return true;
    }

    @Override
    public float C() {
        return 0.07f;
    }

    @Override
    public float D() {
        return 0.17f;
    }

    @Override
    public boolean l() {
        return true;
    }

    @Override
    public boolean af() {
        return false;
    }

    @Override
    public float g(int n2) {
        return 20.0f;
    }

    @Override
    public Rect a_(boolean bl) {
        if (bl) {
            return super.a_(bl);
        }
        if (this.bV) {
            return super.a_(bl);
        }
        return super.a(bl, this.f);
    }

    @Override
    public /* synthetic */ as r() {
        return this.b();
    }
}
