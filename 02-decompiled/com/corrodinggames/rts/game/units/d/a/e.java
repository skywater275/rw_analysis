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

strictfp class e
extends c {
    final /* synthetic */ b b;

    e(b b2) {
        this.b = b2;
        super(b2);
    }

    @Override
    public String c() {
        return com.corrodinggames.rts.game.units.d.a.b.x;
    }

    @Override
    public int d() {
        return ar.f.c() + com.corrodinggames.rts.game.units.d.a.b.dO.c();
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e d(int n2) {
        return com.corrodinggames.rts.game.units.d.a.b.dH();
    }

    @Override
    float a() {
        return 155.0f;
    }

    @Override
    public float a(int n2) {
        return 5.0f;
    }

    @Override
    public float b(int n2) {
        return 4.0f;
    }

    @Override
    public void a(am am2, int n2) {
        PointF pointF = this.c(n2);
        f f2 = f.a(this.b, pointF.a, pointF.b);
        f2.h = 60.0f;
        f2.t = 3.0f + (float)(this.b.k * 13) % 2.0f;
        f2.aR = false;
        f2.G = true;
        f2.ar = Color.a(105, 255, 255, 255);
        f2.P = (short)3;
        f2.x = 1.3f;
        PointF pointF2 = am2.a(pointF.a, pointF.b, f2.t, f2.h, this.a());
        f2.aC = true;
        f2.m = true;
        f2.n = pointF2.a;
        f2.o = pointF2.b;
        f2.n += (float)(-15 + this.b.k * 13 % 30);
        f2.o += (float)(-15 + (63 + this.b.k * 33) % 30);
        f2.em = 3;
        f2.Y = this.b(n2);
        f2.Z = 65.0f;
        f2.aa = true;
        f2.C = true;
        l l2 = l.B();
        ++this.b.k;
        if (this.b.k > 10) {
            this.b.k = 0;
            l2.bR.a(pointF.a, pointF.b, this.b.eq, this.b.cL[n2].a);
        }
    }

    @Override
    public int b() {
        return 2;
    }

    @Override
    public void a(c c2) {
        this.b.cv += 900.0f;
        this.b.cu += 900.0f;
    }

    @Override
    public void a(float f2) {
        if (this.b.cu < this.b.cv) {
            this.b.cu += 0.15f * f2;
            if (this.b.cu > this.b.cv) {
                this.b.cu = this.b.cv;
            }
        }
    }

    @Override
    public float e(int n2) {
        return 11.0f;
    }

    @Override
    public float f(int n2) {
        return 2.0f;
    }

    @Override
    public float g(int n2) {
        return 18.0f;
    }

    @Override
    public float h(int n2) {
        return 0.0f;
    }
}
