/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.e;

import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.f;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.e.j;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;

public strictfp class o
extends j {
    static e a = null;
    static e b = null;
    static e c = null;
    static e[] d = new e[10];
    Rect e = new Rect();

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.v;
    }

    public static void f() {
        l l2 = l.B();
        b = l2.bO.a(R$drawable.tank2);
        a = l2.bO.a(R$drawable.tank2_dead);
        c = l2.bO.a(R$drawable.tank2_turret);
        d = n.a(b);
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
        return null;
    }

    @Override
    public e d(int n2) {
        return c;
    }

    @Override
    public boolean e() {
        l l2 = l.B();
        l2.bR.b(this.eo, this.ep, this.eq);
        this.M = a;
        this.S(0);
        this.bT = false;
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.o, 0.8f, this.eo, this.ep);
        this.bq();
        return true;
    }

    public o(boolean bl) {
        super(bl);
        this.T(16);
        this.U(30);
        this.cj = 11.0f;
        this.ck = this.cj + 2.0f;
        this.cu = this.cv = 350.0f;
        this.M = b;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
    }

    @Override
    public void a(am am2, int n2) {
        PointF pointF = this.E(n2);
        f f2 = f.a(this, pointF.a, pointF.b);
        f2.U = 35.0f;
        f2.l = am2;
        f2.h = 60.0f;
        f2.t = 3.0f;
        l l2 = l.B();
        l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
        l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].a);
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.q, 0.3f, pointF.a, pointF.b);
    }

    @Override
    public float m() {
        return 150.0f;
    }

    @Override
    public float b(int n2) {
        return 70.0f;
    }

    @Override
    public float z() {
        return 1.0f;
    }

    @Override
    public float A() {
        return 1.9f;
    }

    @Override
    public float c(int n2) {
        return 3.0f;
    }

    @Override
    public boolean c(float f2) {
        return super.c(f2);
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
        return false;
    }

    @Override
    public float g(int n2) {
        return 10.0f;
    }

    @Override
    public /* synthetic */ as r() {
        return this.b();
    }
}
