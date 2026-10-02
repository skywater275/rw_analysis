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
import com.corrodinggames.rts.gameFramework.utility.y;

public strictfp class d
extends com.corrodinggames.rts.game.units.h.f {
    static e a = null;
    static e b = null;
    static e c = null;
    static e[] d = new e[10];
    static PointF e = new PointF();
    Rect f = new Rect();

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.o;
    }

    public static void f() {
        l l2 = l.B();
        b = l2.bO.a(R$drawable.scout_ship);
        a = l2.bO.a(R$drawable.scout_ship_dead);
        c = com.corrodinggames.rts.game.units.h.d.a(b, b.m(), b.l());
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
        l2.bR.b(this.eo, this.ep, this.eq);
        this.M = a;
        this.S(0);
        this.bT = false;
        return true;
    }

    public d(boolean bl) {
        super(bl);
        this.T(17);
        this.U(31);
        this.cj = 15.0f;
        this.ck = this.cj - 2.0f;
        this.cu = this.cv = 350.0f;
        this.M = b;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
    }

    @Override
    public PointF E(int n2) {
        float f2 = 6.0f;
        float f3 = this.cg;
        float f4 = this.eo + com.corrodinggames.rts.gameFramework.f.k(f3) * f2;
        float f5 = this.ep + com.corrodinggames.rts.gameFramework.f.j(f3) * f2;
        e.a(f4, f5);
        return e;
    }

    @Override
    public float q(int n2) {
        return 62.0f;
    }

    @Override
    public void a(am am2, int n2) {
        l l2 = l.B();
        PointF pointF = this.E(n2);
        if (!am2.Q()) {
            f f2 = com.corrodinggames.rts.game.f.a((am)this, pointF.a, pointF.b, this.eq, n2);
            PointF pointF2 = this.K(n2);
            f2.K = pointF2.a;
            f2.L = pointF2.b;
            f2.ar = Color.a(255, 230, 230, 50);
            f2.U = 62.0f;
            f2.l = am2;
            f2.h = 190.0f;
            f2.t = 2.0f;
            f2.aH = true;
            f2.aM = true;
            f2.aQ = true;
            l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.m, 0.8f, this.eo, this.ep);
            l2.bR.a(this.eo, this.ep, this.eq, -1118720);
            l2.bR.a(f2, -1118720);
        } else {
            f f3 = com.corrodinggames.rts.game.f.a((am)this, pointF.a, pointF.b, this.eq - 1.0f, n2);
            f3.ar = Color.a(255, 0, 0, 150);
            f3.x = 1.0f;
            f3.U = 42.0f;
            f3.l = am2;
            f3.h = 220.0f;
            f3.t = 1.9f;
            f3.aM = true;
            f3.aQ = true;
            l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.m, 0.8f, this.eo, this.ep);
            l2.bR.a(this.eo, this.ep, this.eq, -1118720);
        }
    }

    @Override
    public float m() {
        return 200.0f;
    }

    @Override
    public float b(int n2) {
        return 170.0f;
    }

    @Override
    public float z() {
        return 1.2f;
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
    public float c(int n2) {
        return 99.0f;
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
        return super.c(f2);
    }

    @Override
    public boolean l() {
        return true;
    }

    @Override
    public boolean ae() {
        return true;
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
