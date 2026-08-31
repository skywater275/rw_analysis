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
import com.corrodinggames.rts.gameFramework.m.e;

public strictfp class a
extends b {
    static e a = null;
    static e b = null;
    static e c = null;
    static e d = null;
    static e[] e = new e[10];
    float f;
    Rect g = new Rect();

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.m;
    }

    public static void f() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        b = l2.bO.a(R$drawable.ship);
        c = l2.bO.a(R$drawable.ship_shadow);
        a = l2.bO.a(R$drawable.ship_dead);
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
        return c;
    }

    @Override
    public e d(int n2) {
        return d;
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

    public a(boolean bl) {
        super(bl);
        this.T(24);
        this.U(22);
        this.cj = 11.0f;
        this.ck = this.cj + 0.0f;
        this.cu = this.cv = 250.0f;
        this.M = b;
        this.N = c;
        this.eq = 0.0f;
        this.f = 0.18f;
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
        this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, 20.0f, 0.3f * f2);
    }

    @Override
    public void a(am am2, int n2) {
        PointF pointF = this.E(n2);
        f f2 = com.corrodinggames.rts.game.f.a((am)this, pointF.a, pointF.b, this.eq, n2);
        PointF pointF2 = this.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.U = 30.0f;
        f2.l = am2;
        f2.h = 75.0f;
        f2.t = 6.0f;
        f2.x = 2.0f;
        f2.y = 4.0f;
        f2.ar = Color.a(250, 74, 232, 255);
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        com.corrodinggames.rts.gameFramework.d.e e2 = l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].a);
        if (e2 != null) {
            e2.aq = 10;
        }
        float f3 = 1.0f + com.corrodinggames.rts.gameFramework.f.c(-0.1f, 0.1f);
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.y, 0.14f, f3, pointF.a, pointF.b);
    }

    @Override
    public float m() {
        return 170.0f;
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
        return 2.4f;
    }

    @Override
    public float A() {
        return 3.7f;
    }

    @Override
    public float B() {
        return 0.4f;
    }

    @Override
    public float c(int n2) {
        return 3.7f;
    }

    @Override
    public boolean bm() {
        return false;
    }

    @Override
    public float w(int n2) {
        return 0.4f;
    }

    @Override
    public boolean E() {
        return false;
    }

    @Override
    public float g(int n2) {
        return 10.0f;
    }

    @Override
    public float C() {
        return 0.1f;
    }

    @Override
    public float D() {
        return 0.16f;
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
    public boolean ag() {
        return false;
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
    public float d(boolean bl) {
        return this.cL[0].a + 90.0f;
    }

    @Override
    public /* synthetic */ as r() {
        return this.b();
    }
}
