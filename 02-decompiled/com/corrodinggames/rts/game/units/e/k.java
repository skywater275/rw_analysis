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

public strictfp class k
extends j {
    static e a = null;
    static e b = null;
    static e c = null;
    static e d = null;
    static e[] e = new e[10];
    static e f = null;
    Rect g = new Rect();

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.r;
    }

    public static void f() {
        l l2 = l.B();
        b = l2.bO.a(R$drawable.laser_tank_base);
        a = l2.bO.a(R$drawable.laser_tank_dead);
        c = l2.bO.a(R$drawable.laser_tank_turrent);
        d = l2.bO.a(R$drawable.laser_tank_charge);
        e = n.a(b);
        f = k.a(b, b.m(), b.l());
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
        return f;
    }

    @Override
    public boolean F() {
        return l.B().bQ.renderExtraShadows && !this.bV;
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
    public e d(int n2) {
        return c;
    }

    @Override
    public boolean e() {
        l l2 = l.B();
        this.M = a;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.ab.b);
        return true;
    }

    public k(boolean bl) {
        super(bl);
        this.a(b, 1);
        this.cj = 14.0f;
        this.ck = this.cj + 2.0f;
        this.cu = this.cv = 300.0f;
        this.M = b;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
    }

    @Override
    public float q(int n2) {
        return 450.0f;
    }

    @Override
    public void a(am am2, int n2) {
        PointF pointF = this.E(n2);
        f f2 = com.corrodinggames.rts.game.f.a(this, pointF.a, pointF.b);
        f2.U = this.q(n2);
        f2.l = am2;
        f2.h = 8.0f;
        f2.B = true;
        f2.A = true;
        f2.aQ = true;
        f2.ar = Color.a(80, 255, 0, 0);
        l l2 = l.B();
        l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
        l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].a);
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.y, 0.3f, pointF.a, pointF.b);
    }

    @Override
    public float bW() {
        if (this.cL[0].e > 0.0f) {
            return 1.0f - this.cL[0].e / this.b(0);
        }
        if (this.cL[0].f != 0.0f) {
            return this.cL[0].f / this.e(0);
        }
        return super.bW();
    }

    @Override
    public boolean bX() {
        return this.cL[0].e > 0.0f;
    }

    @Override
    public float m() {
        return 190.0f;
    }

    @Override
    public float b(int n2) {
        return 450.0f;
    }

    @Override
    public float e(int n2) {
        return 80.0f;
    }

    @Override
    public float z() {
        return 0.7f;
    }

    @Override
    public float A() {
        return 1.5f;
    }

    @Override
    public float B() {
        return 0.1f;
    }

    @Override
    public float c(int n2) {
        return 3.0f;
    }

    @Override
    public boolean c(float f2) {
        float f3;
        if (!super.c(f2)) {
            return false;
        }
        l l2 = l.B();
        y.a(this);
        if (!this.bV && (f3 = this.cL[0].f / this.e(0)) != 0.0f) {
            PointF pointF = this.E(0);
            l2.bO.i();
            l2.bO.b(pointF.a - l2.cw, pointF.b - l2.cx);
            l2.bO.a(f3, f3);
            l2.bO.a(d, 0.0f, 0.0f, null);
            l2.bO.j();
        }
        return true;
    }

    @Override
    public float C() {
        return 0.07f;
    }

    @Override
    public float D() {
        return 0.12f;
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
        return 19.0f;
    }

    @Override
    public /* synthetic */ as r() {
        return this.b();
    }
}
