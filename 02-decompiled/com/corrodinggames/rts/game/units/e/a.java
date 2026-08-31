/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.e;

import android.graphics.Color;
import android.graphics.PointF;
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

public strictfp class a
extends j {
    static e a = null;
    static e b = null;
    static e c = null;
    static e[] d = new e[10];

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.k;
    }

    public static void f() {
        l l2 = l.B();
        a = l2.bO.a(R$drawable.artillery2);
        b = l2.bO.a(R$drawable.artillery1_dead);
        d = n.a(a);
        c = com.corrodinggames.rts.game.units.e.a.a(a);
    }

    @Override
    public e d() {
        if (this.bV) {
            return b;
        }
        return d[this.bX.R()];
    }

    @Override
    public e k() {
        return c;
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
        return null;
    }

    @Override
    public boolean e() {
        l l2 = l.B();
        this.M = b;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.ab.c);
        return true;
    }

    public a(boolean bl) {
        super(bl);
        this.T(28);
        this.U(50);
        this.ck = this.cj = 18.0f;
        this.cu = this.cv = 140.0f;
        this.M = a;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
    }

    @Override
    public void a(am am2, int n2) {
        PointF pointF = this.E(n2);
        f f2 = f.a(this, pointF.a, pointF.b);
        PointF pointF2 = this.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.h = 150.0f;
        f2.t = 4.0f;
        f2.aQ = true;
        f2.ar = Color.a(255, 190, 190, 80);
        f2.R = (short)2;
        f2.P = 1;
        f2.x = 0.9f;
        PointF pointF3 = am2.a(pointF.a, pointF.b, f2.t, f2.h, this.m());
        f2.aC = true;
        f2.m = true;
        f2.n = pointF3.a;
        f2.o = pointF3.b;
        f2.Y = 80.0f;
        f2.Z = 45.0f;
        f2.aa = true;
        l l2 = l.B();
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.r, 0.3f, pointF.a, pointF.b);
        l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].a);
        com.corrodinggames.rts.gameFramework.d.e e2 = l2.bR.a(pointF.a, pointF.b, this.eq, -1118482);
        if (e2 != null) {
            e2.W = e2.V = 15.0f;
        }
    }

    @Override
    public float bW() {
        if (this.cL[0].e > 0.0f) {
            return 1.0f - this.cL[0].e / this.b(0);
        }
        return super.bW();
    }

    @Override
    public float m() {
        return 290.0f;
    }

    @Override
    public float b(int n2) {
        return 240.0f;
    }

    @Override
    public float z() {
        return 0.9f;
    }

    @Override
    public float A() {
        return 1.7f;
    }

    @Override
    public float B() {
        return 0.05f;
    }

    @Override
    public float c(int n2) {
        return 99.0f;
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
    public boolean E() {
        return true;
    }

    @Override
    public float g(int n2) {
        return 20.0f;
    }

    @Override
    public float C() {
        return 0.05f;
    }

    @Override
    public float D() {
        return 0.12f;
    }

    @Override
    public void e(float f2) {
        super.e(f2);
        float f3 = this.m();
        y.a((am)this, f3);
    }

    @Override
    public float bN() {
        return 14000.0f;
    }

    @Override
    public /* synthetic */ as r() {
        return this.b();
    }
}
