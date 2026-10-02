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
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.utility.y;

public strictfp class l
extends j {
    static e a = null;
    static e b = null;
    static e[] c = new e[10];
    static e d = null;
    public static e e = null;
    int f;
    float g;
    Rect h = new Rect();

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.E;
    }

    public static void f() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        e e2 = l2.bO.a(R$drawable.mammoth_tank);
        c = n.a(e2);
        a = l2.bO.a(R$drawable.mammoth_tank_dead);
        b = l2.bO.a(R$drawable.mammoth_tank_turret);
        e = l2.bO.a(R$drawable.lighting_charge);
        d = l.a(e2, e2.m() / 2, e2.l());
    }

    @Override
    public e d() {
        if (this.bV) {
            return a;
        }
        return c[this.bX.R()];
    }

    @Override
    public e k() {
        return d;
    }

    @Override
    public e d(int n2) {
        return b;
    }

    @Override
    public boolean F() {
        return com.corrodinggames.rts.gameFramework.l.B().bQ.renderExtraShadows && this.eq > -2.0f && !this.bV;
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
    public boolean e() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.M = a;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.ab.e);
        return true;
    }

    public l(boolean bl) {
        super(bl);
        this.a(c[7], 2);
        this.cj = 21.0f;
        this.ck = this.cj + 1.0f;
        this.cu = this.cv = 2900.0f;
        this.M = c[7];
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (this.cK) {
            this.g += f2;
            if (this.g > 3.0f) {
                this.g = 0.0f;
                this.f = 1 - this.f;
            }
        }
    }

    @Override
    public float bN() {
        return 14000.0f;
    }

    @Override
    public void a(am am2, int n2) {
        PointF pointF = this.E(n2);
        f f2 = com.corrodinggames.rts.game.f.a(this, pointF.a, pointF.b);
        f2.ar = Color.a(255, 247, 212, 129);
        f2.U = 260.0f;
        f2.l = am2;
        f2.h = 20.0f;
        f2.t = 4.0f;
        f2.x = 2.0f;
        f2.aQ = true;
        f2.A = true;
        f2.M = true;
        f2.ai = 0.5f;
        f2.ak = 1.0f;
        f2.al = 0.0f;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bR.a(pointF.a, pointF.b, this.eq, -1118482);
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.x, 0.2f, this.eo, this.ep);
    }

    @Override
    public float m() {
        return 210.0f;
    }

    @Override
    public float b(int n2) {
        return 140.0f;
    }

    @Override
    public float z() {
        return 0.5f;
    }

    @Override
    public float bc() {
        return 1.0f;
    }

    @Override
    public float A() {
        return 1.0f;
    }

    @Override
    public float B() {
        return 0.5f;
    }

    @Override
    public float w(int n2) {
        return 0.08f;
    }

    @Override
    public float c(int n2) {
        return 2.5f;
    }

    @Override
    public float C() {
        return 0.04f;
    }

    @Override
    public float D() {
        return 0.08f;
    }

    @Override
    public Rect a_(boolean bl) {
        if (this.bV && !bl) {
            return super.a_(bl);
        }
        return super.a(bl, this.f);
    }

    @Override
    public boolean c(float f2) {
        if (!super.c(f2)) {
            return false;
        }
        y.a(this);
        float f3 = this.cL[0].f / this.e(0);
        y.a(this, e, f3, 0);
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
        return 22.0f;
    }

    @Override
    public float e(int n2) {
        return 60.0f;
    }

    @Override
    public /* synthetic */ as r() {
        return this.b();
    }
}
