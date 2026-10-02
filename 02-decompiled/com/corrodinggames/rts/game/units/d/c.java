/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.f;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.c$1;
import com.corrodinggames.rts.game.units.d.c$2;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.gameFramework.a.e;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.y;
import java.util.ArrayList;

public strictfp class c
extends i {
    static com.corrodinggames.rts.gameFramework.m.e[] a = new com.corrodinggames.rts.gameFramework.m.e[10];
    static com.corrodinggames.rts.gameFramework.m.e b = null;
    boolean c;
    int d;
    float e;
    static com.corrodinggames.rts.gameFramework.m.e f = null;
    static com.corrodinggames.rts.gameFramework.m.e[] g = new com.corrodinggames.rts.gameFramework.m.e[10];
    PointF h = new PointF();
    Rect i = new Rect();
    static s j = new c$1(145);
    static s k = new c$2(144);
    static ArrayList l = new ArrayList();

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.c);
        as2.a(this.d);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        this.c = k2.e();
        if (k2.b() >= 30) {
            this.d = k2.f();
        }
        super.a(k2);
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e v() {
        if (this.bX.k == -1) {
            return null;
        }
        return g[this.bX.R()];
    }

    public static void b() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        b = l2.bO.a(R$drawable.antinuke_launcher_dead);
        com.corrodinggames.rts.gameFramework.m.e e2 = l2.bO.a(R$drawable.antinuke_launcher);
        a = com.corrodinggames.rts.game.n.a(e2);
        e2.n();
        f = l2.bO.a(R$drawable.unit_icon_building_turrent);
        g = com.corrodinggames.rts.game.n.a(f);
    }

    @Override
    public boolean L() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.M = b;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.ab.h);
        return true;
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e d() {
        if (this.bV) {
            return b;
        }
        return a[this.bX.R()];
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e k() {
        return null;
    }

    @Override
    public void a(int n2) {
    }

    public c(boolean bl) {
        super(bl);
        this.M = a[a.length - 1];
        this.b(this.M);
        this.ck = this.cj = 24.0f;
        this.cu = this.cv = 2800.0f;
        this.n.a(-1, -1, 1, 1);
        this.o.a(-1, -1, 1, 1);
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.bT() || this.bV) {
            return;
        }
        if (this.d > 0) {
            f f3 = null;
            this.e = com.corrodinggames.rts.gameFramework.f.a(this.e, f2);
            if (this.e == 0.0f) {
                this.e = 15.0f;
                for (f f4 : com.corrodinggames.rts.game.f.a) {
                    float f5;
                    if (!f4.D || !(f4.eq > 50.0f)) continue;
                    float f6 = 2200.0f;
                    float f7 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, f4.eo, f4.ep);
                    if (!(f7 < f6 * f6) || !((f5 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, f4.n, f4.o)) < 1000000.0f) || f4.j != null && (f4.j.bX.d(this.bX) || f4.j.bX == this.bX) || this.a(f4)) continue;
                    f3 = f4;
                }
            }
            if (f3 != null) {
                this.b(f3);
            }
        }
    }

    public boolean a(f f2) {
        Object[] objectArray = com.corrodinggames.rts.game.f.a.a();
        int n2 = com.corrodinggames.rts.game.f.a.a;
        for (int i2 = 0; i2 < n2; ++i2) {
            f f3 = (f)objectArray[i2];
            if (f3 == f2 || f3.q != f2) continue;
            return true;
        }
        return false;
    }

    public void b(f f2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.d <= 0) {
            return;
        }
        --this.d;
        int n2 = 0;
        PointF pointF = this.E(n2);
        f f3 = com.corrodinggames.rts.game.f.a(this, pointF.a, pointF.b);
        f3.S(10);
        f3.P = (short)10;
        f3.R = 0;
        f3.x = 1.0f;
        f3.aC = true;
        f3.q = f2;
        f3.h = 99999.0f;
        f3.t = 0.2f;
        f3.r = 6.5f;
        f3.ar = Color.a(255, 80, 60, 180);
        f3.U = 600.0f;
        f3.aH = true;
        f3.aM = true;
        f3.aQ = true;
        f3.C = true;
        f3.aI = 80.0f;
        f3.aJ = 100.0f;
        f3.aL = 2.0f;
        l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
        com.corrodinggames.rts.gameFramework.d.e e2 = l2.bR.d(pointF.a, pointF.b, 0.0f, -1);
        if (e2 != null) {
            e2.G = 0.5f;
            e2.F = 2.1f;
            e2.ar = (short)2;
            e2.W = e2.V = 90.0f;
            e2.U = 0.0f;
        }
        float f4 = 1.5f;
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.D, 0.15f, f4, pointF.a, pointF.b);
    }

    @Override
    public PointF E(int n2) {
        bg.a(this.eo, this.ep - 9.0f);
        return bg;
    }

    @Override
    public void a(am am2, int n2) {
    }

    @Override
    public float m() {
        return 1000.0f;
    }

    @Override
    public float c(int n2) {
        return 4.0f;
    }

    @Override
    public boolean b(int n2, float f2) {
        return false;
    }

    @Override
    public boolean c(float f2) {
        return super.c(f2);
    }

    public ar K() {
        return com.corrodinggames.rts.game.units.ar.D;
    }

    @Override
    public boolean l() {
        return false;
    }

    @Override
    public float g(int n2) {
        return 1.0f;
    }

    @Override
    public float bV() {
        return super.bV();
    }

    public void M() {
        ++this.d;
    }

    @Override
    public void a(j j2) {
        if (j2.j.equals(k.N())) {
            this.M();
        }
    }

    @Override
    public com.corrodinggames.rts.game.units.a.c cm() {
        if (this.d < 4) {
            return k.N();
        }
        return com.corrodinggames.rts.game.units.a.s.i;
    }

    @Override
    public boolean ck() {
        return false;
    }

    @Override
    public ArrayList N() {
        return l;
    }

    @Override
    public void e(float f2) {
        super.e(f2);
    }

    @Override
    public void O() {
    }

    @Override
    public void cb() {
        float f2 = 990.0f;
        boolean bl = false;
        boolean bl2 = true;
        com.corrodinggames.rts.gameFramework.utility.y.a((am)this, f2, bl, bl2);
    }

    @Override
    public boolean a(l l2) {
        if (this.cG) {
            return true;
        }
        return super.a(l2);
    }

    @Override
    public float a(am am2, float f2, f f3) {
        if (f2 > 2600.0f) {
            f2 = 2600.0f;
        }
        return super.a(am2, f2, f3);
    }

    @Override
    public /* synthetic */ as r() {
        return this.K();
    }

    static {
        l.add(j);
        l.add(k);
    }
}
