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
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.game.units.d.q$1;
import com.corrodinggames.rts.game.units.d.q$2;
import com.corrodinggames.rts.gameFramework.a.e;
import com.corrodinggames.rts.gameFramework.d.h;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.y;
import java.util.ArrayList;

public class q
extends i {
    static com.corrodinggames.rts.gameFramework.m.e[] a = new com.corrodinggames.rts.gameFramework.m.e[10];
    static com.corrodinggames.rts.gameFramework.m.e b = null;
    int c;
    static com.corrodinggames.rts.gameFramework.m.e d = null;
    static com.corrodinggames.rts.gameFramework.m.e[] e = new com.corrodinggames.rts.gameFramework.m.e[10];
    PointF f = new PointF();
    Rect g = new Rect();
    static s h = new q$1(142);
    static s i = new q$2(143);
    static ArrayList j = new ArrayList();

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.c);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        this.c = k2.f();
        super.a(k2);
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e v() {
        if (this.bX.k == -1) {
            return null;
        }
        return e[this.bX.R()];
    }

    public static void b() {
        l l2 = l.B();
        b = l2.bO.a(R$drawable.nuke_launcher_dead);
        com.corrodinggames.rts.gameFramework.m.e e2 = l2.bO.a(R$drawable.nuke_launcher);
        a = com.corrodinggames.rts.game.n.a(e2);
        e2.n();
        d = l2.bO.a(R$drawable.unit_icon_building);
        e = com.corrodinggames.rts.game.n.a(d);
    }

    @Override
    public int bp() {
        return 20;
    }

    @Override
    public boolean L() {
        l l2 = l.B();
        this.M = b;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.ab.h);
        float f2 = this.eo;
        float f3 = this.ep;
        float f4 = 0.0f;
        l2.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
        com.corrodinggames.rts.gameFramework.d.e e2 = l2.bR.a(f2, f3, this.eq, Color.a(255, 255, 255, 255));
        if (e2 != null) {
            e2.G = 8.0f;
            e2.F = 5.0f;
            e2.E = 0.9f;
            e2.W = e2.V = 20.0f;
            e2.r = true;
        }
        l2.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
        e2 = l2.bR.c(f2, f3, f4, -1127220);
        if (e2 != null) {
            e2.G = 0.2f;
            e2.F = 2.0f;
            e2.ar = (short)2;
            e2.W = e2.V = 45.0f;
            e2.U = 0.0f;
        }
        float f5 = 40.0f;
        float f6 = 120.0f;
        l2.bR.a(this.eo, this.ep, this.eq, f5, f6);
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

    public q(boolean bl) {
        super(bl);
        this.M = a[a.length - 1];
        this.b(this.M);
        this.ck = this.cj = 40.0f;
        this.cu = this.cv = 1500.0f;
        this.n.a(-2, -1, 2, 1);
        this.o.a(-2, -1, 2, 2);
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.bT() || this.bV) {
            return;
        }
    }

    @Override
    public PointF E(int n2) {
        bg.a(this.eo, this.ep - 3.0f);
        return bg;
    }

    @Override
    public void a(am am2, int n2) {
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
        return com.corrodinggames.rts.game.units.ar.C;
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

    public void a(float f2, float f3) {
        com.corrodinggames.rts.gameFramework.d.e e2;
        l l2 = l.B();
        if (this.c <= 0) {
            return;
        }
        float f4 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, f2, f3);
        if (f4 < 202500.0f) {
            if (this.bX == l2.bs) {
                l2.bS.b("Nuke target too close");
            }
            return;
        }
        --this.c;
        int n2 = 0;
        PointF pointF = this.E(n2);
        float f5 = 5.0f;
        f f6 = com.corrodinggames.rts.game.units.d.q.a((am)this, pointF.a, pointF.b, f2, f3);
        f6.i = f5;
        com.corrodinggames.rts.gameFramework.d.e e3 = l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
        if (e3 != null) {
            e3.U = f5;
            e3.G = 2.1f;
            e3.F = 2.1f;
            e3.ar = (short)2;
            e3.s = true;
            e3.t = 70.0f;
            e3.W = e3.V = 370.0f;
            e3.E = 1.0f;
        }
        if ((e2 = l2.bR.d(pointF.a, pointF.b, 0.0f, -1)) != null) {
            e2.G = 1.0f;
            e2.F = 3.1f;
            e2.ar = (short)2;
            e2.W = e2.V = 170.0f;
            e2.U = f5 + 20.0f;
        }
        float f7 = 0.8f;
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.D, 0.27f, f7, pointF.a, pointF.b);
    }

    public static f a(am am2, float f2, float f3, float f4, float f5) {
        f f6 = com.corrodinggames.rts.game.f.a(am2, f2, f3);
        f6.S(10);
        f6.P = 0;
        f6.Q = 1;
        f6.R = 1;
        f6.x = 1.0f;
        f6.aC = true;
        f6.m = true;
        f6.n = f4;
        f6.o = f5;
        f6.h = 99999.0f;
        f6.t = 0.1f;
        f6.r = 2.7f;
        f6.ar = Color.a(255, 225, 225, 225);
        f6.U = 300.0f;
        f6.aH = true;
        f6.aM = true;
        f6.aQ = true;
        f6.C = true;
        f6.D = true;
        f6.aI = 80.0f;
        f6.aJ = 100.0f;
        f6.aL = 1.1f;
        f6.Y = 5400.0f;
        f6.Z = 250.0f;
        f6.ad = true;
        f6.ae = false;
        f6.ao = true;
        f6.X = f6.W = 75.0f;
        f6.aY = true;
        l l2 = l.B();
        l2.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
        com.corrodinggames.rts.gameFramework.d.e e2 = l2.bR.a(f6, -1118720);
        if (e2 != null) {
            e2.W = e2.V = 1300.0f;
            e2.E = 0.2f;
            e2.G = 1.0f;
        }
        return f6;
    }

    public void M() {
        ++this.c;
    }

    @Override
    public void a(j j2) {
        if (j2.j.equals(i.N())) {
            this.M();
        }
    }

    @Override
    public c cm() {
        return com.corrodinggames.rts.game.units.a.s.i;
    }

    @Override
    public void a(s s2, boolean bl, PointF pointF, am am2) {
        if (bl) {
            return;
        }
        if (s2 == h) {
            if (pointF == null) {
                com.corrodinggames.rts.gameFramework.j.ad.g("action:" + h.N() + " needs point but it is missing");
                return;
            }
            this.a(pointF.a, pointF.b);
            return;
        }
        super.a(s2, bl, pointF, am2);
    }

    @Override
    public ArrayList N() {
        return j;
    }

    @Override
    public void e(float f2) {
        super.e(f2);
        com.corrodinggames.rts.gameFramework.utility.y.b((am)this, 450.0f, false);
    }

    @Override
    public /* synthetic */ as r() {
        return this.K();
    }

    static {
        j.add(h);
        j.add(i);
    }
}
