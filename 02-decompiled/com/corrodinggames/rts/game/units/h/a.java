/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.h;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.f;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.gameFramework.d.c;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.utility.y;

public strictfp class a
extends com.corrodinggames.rts.game.units.h.f {
    static e a = null;
    static e b = null;
    static e c = null;
    static e d = null;
    static e[] e = new e[10];
    Rect f = new Rect();

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.u;
    }

    @Override
    public float bN() {
        return 9000.0f;
    }

    public static void f() {
        l l2 = l.B();
        b = l2.bO.a(R$drawable.battle_ship_t2);
        a = l2.bO.a(R$drawable.battle_ship_t2_dead);
        c = l2.bO.a(R$drawable.battle_ship_t2_turret);
        e = com.corrodinggames.rts.game.n.a(b);
        d = com.corrodinggames.rts.game.units.h.a.a(b, b.m(), b.l());
    }

    @Override
    public e d() {
        if (this.bV) {
            return a;
        }
        return e[this.bX.R()];
    }

    @Override
    public e d(int n2) {
        return c;
    }

    @Override
    public e k() {
        return d;
    }

    @Override
    public boolean F() {
        return l.B().bQ.renderExtraShadows && this.eq > -2.0f;
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
    public boolean e() {
        l l2 = l.B();
        l2.bR.b(this.eo, this.ep, this.eq);
        this.M = a;
        this.S(0);
        this.bT = false;
        return true;
    }

    public a(boolean bl) {
        super(bl);
        this.b(b);
        this.ck = this.cj = 20.0f;
        this.cu = this.cv = 1200.0f;
        this.M = b;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
    }

    @Override
    public float q(int n2) {
        return 65.0f;
    }

    @Override
    public void a(am am2, int n2) {
        PointF pointF = this.E(n2);
        f f2 = com.corrodinggames.rts.game.f.a((am)this, pointF.a, pointF.b, this.eq, n2);
        PointF pointF2 = this.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.U = this.q(n2);
        f2.l = am2;
        f2.h = 80.0f;
        f2.x = 2.0f;
        f2.t = 4.0f;
        f2.S = true;
        f2.ar = Color.a(255, 180, 180, 0);
        f2.aQ = true;
        l l2 = l.B();
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.r, 0.2f, pointF.a, pointF.b);
        l2.bR.a(f2, -1118720);
        com.corrodinggames.rts.gameFramework.d.e e2 = l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].a);
        if (e2 != null) {
            com.corrodinggames.rts.gameFramework.d.c.a(e2, this);
        }
        l2.bR.a(pointF.a, pointF.b, this.eq, -1118720);
    }

    @Override
    public float m() {
        return 240.0f;
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
    public float C(int n2) {
        if (this.ci && (double)this.bc() > 0.95) {
            if (n2 == 0) {
                return this.cg + 140.0f;
            }
            return this.cg - 140.0f;
        }
        return this.cg;
    }

    @Override
    public float A() {
        return 1.8f;
    }

    @Override
    public float B() {
        return 0.08f;
    }

    @Override
    public float c(int n2) {
        return 2.5f;
    }

    @Override
    public float w(int n2) {
        return 0.08f;
    }

    @Override
    public float C() {
        return 0.03f;
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
        return false;
    }

    @Override
    public float g(int n2) {
        return 15.0f;
    }

    @Override
    public int bl() {
        return 2;
    }

    @Override
    public PointF G(int n2) {
        PointF pointF = super.G(n2);
        float f2 = pointF.a;
        float f3 = pointF.b;
        float f4 = n2 == 0 ? 22.0f : 4.0f;
        bh.a(f2 += com.corrodinggames.rts.gameFramework.f.k(this.cg) * f4, f3 += com.corrodinggames.rts.gameFramework.f.j(this.cg) * f4);
        return bh;
    }

    @Override
    public float b(int n2) {
        return 120 - n2 * 28;
    }

    @Override
    public float e(int n2) {
        return n2 * 30;
    }

    @Override
    public void e(float f2) {
        super.e(f2);
        float f3 = this.m();
        y.a((am)this, f3);
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
