/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.c;

import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.f;
import com.corrodinggames.rts.game.l;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.e.j;
import com.corrodinggames.rts.gameFramework.d.d;
import com.corrodinggames.rts.gameFramework.d.h;
import com.corrodinggames.rts.gameFramework.m.e;

public class a
extends j {
    static e a = null;
    static e b = null;
    static e c = null;
    static e[] d = new e[10];
    int e = 0;
    float f = 0.0f;
    Rect g = new Rect();
    Rect h = new Rect();

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.t;
    }

    public static void f() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        b = l2.bO.a(R$drawable.ladybug);
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
        return null;
    }

    @Override
    public boolean e() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        com.corrodinggames.rts.gameFramework.d.e e2 = l2.bR.b(this.eo, this.ep, this.eq, com.corrodinggames.rts.gameFramework.d.d.i, false, com.corrodinggames.rts.gameFramework.d.h.c);
        if (e2 != null) {
            // empty if block
        }
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.A, 0.8f, this.eo, this.ep);
        l.a(this, 1);
        return false;
    }

    public a(boolean bl) {
        super(bl);
        this.T(17);
        this.U(26);
        this.cj = 5.0f;
        this.ck = this.cj + 3.0f;
        this.cu = this.cv = 130.0f;
        this.M = b;
        this.P = com.corrodinggames.rts.game.units.a.a;
    }

    @Override
    public Rect a_(boolean bl) {
        int n2 = this.e * this.es;
        int n3 = 0;
        this.g.a(n2, n3, n2 + this.es, n3 + this.et);
        return this.g;
    }

    @Override
    public boolean bP() {
        return true;
    }

    @Override
    public boolean bO() {
        return true;
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (this.cK) {
            this.e = this.e == 0 ? 1 : 0;
        }
        if (this.f != 0.0f) {
            this.f = com.corrodinggames.rts.gameFramework.f.a(this.f, f2);
            this.e = 2;
        }
    }

    @Override
    public void a(am am2, int n2) {
        com.corrodinggames.rts.game.f.a((am)this, am2, 14.0f, null, false);
        this.f = 4.0f;
        PointF pointF = this.E(n2);
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.B, 0.3f, pointF.a, pointF.b);
    }

    @Override
    public float m() {
        return 43.0f;
    }

    @Override
    public float b(int n2) {
        return 17.0f;
    }

    @Override
    public float z() {
        return 1.7f;
    }

    @Override
    public float A() {
        return 5.5f;
    }

    @Override
    public float c(int n2) {
        return 99.0f;
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
        return 7.0f;
    }

    @Override
    public boolean E() {
        return true;
    }

    @Override
    public /* synthetic */ as r() {
        return this.b();
    }
}
