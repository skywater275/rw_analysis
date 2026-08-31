/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.b;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.f;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.b.b;
import com.corrodinggames.rts.gameFramework.l;

public strictfp class e
extends b {
    static com.corrodinggames.rts.gameFramework.m.e a = null;
    static com.corrodinggames.rts.gameFramework.m.e b = null;
    static com.corrodinggames.rts.gameFramework.m.e c = null;
    static com.corrodinggames.rts.gameFramework.m.e d = null;
    static com.corrodinggames.rts.gameFramework.m.e[] e = new com.corrodinggames.rts.gameFramework.m.e[10];
    float f = 0.0f;
    Rect g = new Rect();

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.n;
    }

    public static void f() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        b = l2.bO.a(R$drawable.gunship);
        c = l2.bO.a(R$drawable.gunship_shadow);
        a = l2.bO.a(R$drawable.gunship_dead);
        e = com.corrodinggames.rts.game.n.a(b);
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e d() {
        if (this.bV) {
            return a;
        }
        return e[this.bX.R()];
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e k() {
        return c;
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e d(int n2) {
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

    public e(boolean bl) {
        super(bl);
        this.T(25);
        this.U(35);
        this.cj = 15.0f;
        this.ck = this.cj + 0.0f;
        this.cu = this.cv = 260.0f;
        this.M = b;
        this.N = c;
        this.eq = 0.0f;
        this.S(5);
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
        this.f += 2.0f * f2;
        if (this.f > 360.0f) {
            this.f -= 360.0f;
        }
        this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, 20.0f + com.corrodinggames.rts.gameFramework.f.j(this.f) * 1.5f, 0.1f * f2);
    }

    @Override
    public PointF E(int n2) {
        float f2 = this.g(n2);
        float f3 = this.cg;
        float f4 = this.eo + com.corrodinggames.rts.gameFramework.f.k(f3) * f2;
        float f5 = this.ep + com.corrodinggames.rts.gameFramework.f.j(f3) * f2;
        bg.a(f4, f5);
        return bg;
    }

    @Override
    public float q(int n2) {
        return 35.0f;
    }

    @Override
    public void a(am am2, int n2) {
        PointF pointF = this.E(n2);
        f f2 = com.corrodinggames.rts.game.f.a((am)this, pointF.a, pointF.b, this.eq, n2);
        PointF pointF2 = this.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.ar = Color.a(255, 150, 230, 40);
        f2.U = this.q(n2);
        f2.l = am2;
        f2.h = 80.0f;
        f2.t = 4.0f;
        f2.x = 2.0f;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
        l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].a);
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.u, 0.3f, this.eo, this.ep);
    }

    @Override
    public float m() {
        return 140.0f;
    }

    @Override
    public float b(int n2) {
        return 40.0f;
    }

    @Override
    public float z() {
        if (this.eq < 15.0f) {
            return 0.0f;
        }
        return 1.4f;
    }

    @Override
    public float A() {
        return 4.0f;
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
    public float c(int n2) {
        return 99.0f;
    }

    @Override
    public boolean E() {
        return false;
    }

    @Override
    public float C() {
        return 0.2f;
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
    public boolean af() {
        return false;
    }

    @Override
    public float g(int n2) {
        return 15.0f;
    }

    @Override
    public /* synthetic */ as r() {
        return this.b();
    }
}
