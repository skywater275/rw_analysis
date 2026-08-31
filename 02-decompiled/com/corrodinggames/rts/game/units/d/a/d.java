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

strictfp class d
extends c {
    final /* synthetic */ b b;

    d(b b2) {
        this.b = b2;
        super(b2);
    }

    @Override
    public String c() {
        return com.corrodinggames.rts.game.units.d.a.b.w;
    }

    @Override
    public int d() {
        return ar.f.c() + com.corrodinggames.rts.game.units.d.a.b.dN.c();
    }

    @Override
    public e d(int n2) {
        return com.corrodinggames.rts.game.units.d.a.b.dG();
    }

    @Override
    float a() {
        return 350.0f;
    }

    @Override
    public float a(int n2) {
        return 220.0f;
    }

    @Override
    public float b(int n2) {
        return 100.0f;
    }

    @Override
    public void a(am am2, int n2) {
        PointF pointF = this.c(n2);
        f f2 = f.a(this.b, pointF.a, pointF.b);
        PointF pointF2 = this.b.K(n2);
        f2.K = pointF2.a;
        f2.L = pointF2.b;
        f2.h = 150.0f;
        f2.t = 4.0f;
        f2.aQ = true;
        f2.ar = Color.a(255, 190, 190, 80);
        f2.R = (short)2;
        f2.P = 0;
        f2.x = 0.9f;
        PointF pointF3 = am2.a(pointF.a, pointF.b, f2.t, f2.h, this.a());
        f2.aC = true;
        f2.m = true;
        f2.n = pointF3.a;
        f2.o = pointF3.b;
        f2.Y = this.b(n2);
        f2.Z = 55.0f;
        f2.aa = true;
        l l2 = l.B();
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.r, 0.3f, pointF.a, pointF.b);
        l2.bR.a(pointF.a, pointF.b, this.b.eq, this.b.cL[n2].a);
        l2.bR.a(f2, -1118482);
        com.corrodinggames.rts.gameFramework.d.e e2 = l2.bR.a(pointF.a, pointF.b, this.b.eq, -1118482);
        if (e2 != null) {
            e2.W = e2.V = 15.0f;
        }
    }

    @Override
    public int b() {
        return 2;
    }

    @Override
    public void a(c c2) {
        this.b.cv += 300.0f;
        this.b.cu += 300.0f;
    }

    @Override
    public float e(int n2) {
        return 2.5f;
    }

    @Override
    public float f(int n2) {
        return 0.2f;
    }

    @Override
    public float h(int n2) {
        return -2.0f;
    }
}
