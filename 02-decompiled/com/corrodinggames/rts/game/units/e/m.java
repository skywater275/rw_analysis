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
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.e.j;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.utility.y;

public strictfp class m
extends j {
    static e a = null;
    static e b = null;
    static e c = null;
    static e[] d = new e[10];
    Rect e = new Rect();

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.q;
    }

    public static void f() {
        l l2 = l.B();
        b = l2.bO.a(R$drawable.mega_tank);
        a = l2.bO.a(R$drawable.mega_tank_dead);
        c = l2.bO.a(R$drawable.mega_tank_turret);
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

    public m(boolean bl) {
        super(bl);
        this.T(20);
        this.U(25);
        this.cj = 12.0f;
        this.ck = this.cj + 1.0f;
        this.cu = this.cv = 550.0f;
        this.M = b;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
    }

    @Override
    public float bN() {
        return 7000.0f;
    }

    @Override
    public void a(am am2, int n2) {
        if (!am2.i()) {
            PointF pointF = this.E(n2);
            f f2 = f.a(this, pointF.a, pointF.b);
            f2.ar = Color.a(255, 150, 230, 40);
            f2.U = 50.0f;
            f2.l = am2;
            f2.h = 60.0f;
            f2.t = 3.0f;
            f2.x = 2.0f;
            f2.aQ = true;
            l l2 = l.B();
            l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
            l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].a);
            l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.u, 0.3f, this.eo, this.ep);
        } else {
            f f3 = f.a(this, this.eo, this.ep);
            f3.ar = Color.a(255, 230, 230, 50);
            f3.U = 40.0f;
            f3.l = am2;
            f3.h = 190.0f;
            f3.t = 4.0f;
            f3.aH = true;
            f3.aI = 10.0f;
            f3.aJ = 15.0f;
            f3.aM = true;
            f3.aQ = true;
            l l3 = l.B();
            l3.bM.a(com.corrodinggames.rts.gameFramework.a.e.m, 0.2f, this.eo, this.ep);
        }
    }

    @Override
    public float m() {
        return 140.0f;
    }

    @Override
    public float b(int n2) {
        return 70.0f;
    }

    @Override
    public float z() {
        return 0.8f;
    }

    @Override
    public float A() {
        return 1.2f;
    }

    @Override
    public float c(int n2) {
        return 2.0f;
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
        return true;
    }

    @Override
    public float g(int n2) {
        return 12.0f;
    }

    @Override
    public /* synthetic */ as r() {
        return this.b();
    }
}
