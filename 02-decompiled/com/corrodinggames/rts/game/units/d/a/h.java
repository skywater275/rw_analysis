/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d.a;

import android.graphics.Color;
import android.graphics.PointF;
import com.corrodinggames.rts.game.f;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.d.a.b;
import com.corrodinggames.rts.game.units.d.a.c;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;

strictfp class h
extends c {
    final /* synthetic */ b b;

    h(b b2) {
        this.b = b2;
        super(b2);
    }

    @Override
    public String c() {
        return com.corrodinggames.rts.game.units.d.a.b.t;
    }

    @Override
    public int d() {
        return ar.f.c();
    }

    @Override
    public e d(int n2) {
        return com.corrodinggames.rts.game.units.d.a.b.dD();
    }

    @Override
    float a() {
        return 165.0f;
    }

    @Override
    public float b(int n2) {
        return 41.0f;
    }

    @Override
    public float a(int n2) {
        return 30.0f;
    }

    @Override
    public float g(int n2) {
        return 21.0f;
    }

    @Override
    public void a(am am2, int n2) {
        PointF pointF = this.c(n2);
        f f2 = f.a(this.b, pointF.a, pointF.b);
        PointF pointF2 = this.b.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.l = am2;
        f2.h = 60.0f;
        f2.t = 5.0f;
        f2.ar = Color.a(255, 100, 30, 30);
        f2.U = this.b(n2);
        f2.P = (short)5;
        f2.x = 1.0f;
        l l2 = l.B();
        l2.bR.a(pointF.a, pointF.b, this.b.eq, -1127220);
        l2.bR.a(pointF.a, pointF.b, this.b.eq, this.b.cL[n2].a);
        float f3 = 1.0f + com.corrodinggames.rts.gameFramework.f.c(-0.07f, 0.07f);
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.t, 0.3f, f3, pointF.a, pointF.b);
    }

    @Override
    public int b() {
        return 1;
    }

    @Override
    public void a(c c2) {
    }

    @Override
    public void a(float f2) {
        this.b.s(f2);
    }
}
