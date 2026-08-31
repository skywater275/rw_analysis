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
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;

public strictfp class c
extends com.corrodinggames.rts.game.units.h.f {
    static e a = null;
    static e b = null;
    static e c = null;
    static e[] d = new e[10];
    Rect e = new Rect();

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.p;
    }

    @Override
    public float bN() {
        return 1500.0f;
    }

    public static void f() {
        l l2 = l.B();
        b = l2.bO.a(R$drawable.gun_boat);
        a = l2.bO.a(R$drawable.gun_boat_dead);
        c = com.corrodinggames.rts.game.units.h.c.a(b, b.m(), b.l());
        d = com.corrodinggames.rts.game.n.a(b);
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
        return l.B().bQ.renderExtraShadows && this.eq > -2.0f;
    }

    @Override
    public float G() {
        return 1.0f;
    }

    @Override
    public float H() {
        return 1.0f;
    }

    @Override
    public e d(int n2) {
        return null;
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

    public c(boolean bl) {
        super(bl);
        this.T(15);
        this.U(27);
        this.cj = 12.0f;
        this.ck = this.cj - 2.0f;
        this.cu = this.cv = 170.0f;
        this.M = b;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
    }

    @Override
    public float q(int n2) {
        return 12.0f;
    }

    @Override
    public void a(am am2, int n2) {
        PointF pointF = this.E(n2);
        f f2 = f.a(this, pointF.a, pointF.b);
        PointF pointF2 = this.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.eq = this.eq;
        f2.U = this.q(n2);
        f2.l = am2;
        f2.h = 30.0f;
        f2.t = 8.0f;
        f2.S = false;
        f2.ar = Color.a(255, 180, 180, 0);
        l l2 = l.B();
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.s, 0.2f, pointF.a, pointF.b);
        l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].a);
        l2.bR.a(pointF.a, pointF.b, this.eq, -1118720);
    }

    @Override
    public float m() {
        return 120.0f;
    }

    @Override
    public float b(int n2) {
        return 60.0f;
    }

    @Override
    public float z() {
        return 1.5f;
    }

    @Override
    public float A() {
        return 2.8f;
    }

    @Override
    public float B() {
        return 0.35f;
    }

    @Override
    public float c(int n2) {
        return 99.0f;
    }

    @Override
    public float C() {
        return 0.06f;
    }

    @Override
    public float D() {
        return 0.2f;
    }

    @Override
    public boolean c(float f2) {
        return super.c(f2);
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
    public /* synthetic */ as r() {
        return this.b();
    }
}
