/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d;

import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.f;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.game.units.d.p$1;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.a.e;
import com.corrodinggames.rts.gameFramework.d.d;
import com.corrodinggames.rts.gameFramework.d.h;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;

public class p
extends i {
    static com.corrodinggames.rts.gameFramework.m.e[] a = new com.corrodinggames.rts.gameFramework.m.e[10];
    static com.corrodinggames.rts.gameFramework.m.e[] b = new com.corrodinggames.rts.gameFramework.m.e[10];
    static com.corrodinggames.rts.gameFramework.m.e c = null;
    boolean d;
    boolean e;
    float f;
    static com.corrodinggames.rts.gameFramework.m.e g = null;
    static com.corrodinggames.rts.gameFramework.m.e[] h = new com.corrodinggames.rts.gameFramework.m.e[10];
    PointF i = new PointF();
    Rect j = new Rect();
    static s k = new p$1(102);
    static ArrayList l = new ArrayList();

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.d);
        as2.a(this.cB);
        as2.a(this.e);
        as2.a(this.f);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        this.d = k2.e();
        this.cB = k2.g();
        this.e = k2.e();
        if (k2.b() >= 38) {
            this.f = k2.g();
        }
        super.a(k2);
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e v() {
        if (this.bX.k == -1) {
            return null;
        }
        return h[this.bX.R()];
    }

    public static void b() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        c = l2.bO.a(R$drawable.laser_defence_dead);
        com.corrodinggames.rts.gameFramework.m.e e2 = l2.bO.a(R$drawable.laser_defence);
        com.corrodinggames.rts.gameFramework.m.e e3 = l2.bO.a(R$drawable.laser_defence_t2);
        a = com.corrodinggames.rts.game.n.a(e2);
        b = com.corrodinggames.rts.game.n.a(e3);
        e2.n();
        e2 = null;
        e3.n();
        e3 = null;
        g = l2.bO.a(R$drawable.unit_icon_building_turrent);
        h = com.corrodinggames.rts.game.n.a(g);
    }

    @Override
    public boolean L() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bR.a(this.eo, this.ep, this.eq);
        this.M = c;
        this.S(0);
        this.bT = false;
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.p, 0.8f, this.eo, this.ep);
        return true;
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e d() {
        if (this.bV) {
            return c;
        }
        if (this.bX == null) {
            return a[a.length - 1];
        }
        if (!this.d) {
            return a[this.bX.R()];
        }
        return b[this.bX.R()];
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e k() {
        return null;
    }

    public p(boolean bl) {
        super(bl);
        this.a(a[0], 2);
        this.cB = 1.0f;
        this.ck = this.cj = 19.0f;
        this.cu = this.cv = 500.0f;
        this.M = a[a.length - 1];
        this.n.a(0, 0, 1, 1);
        this.o.a(0, 0, 1, 1);
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (!this.bT() || this.bV) {
            return;
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        float f3 = 4.0E-4f * f2;
        if (this.d) {
            f3 += 2.0E-4f * f2;
        }
        this.cB = com.corrodinggames.rts.gameFramework.f.a(this.cB, 1.0f, f3);
        if (this.cB >= 1.0f) {
            this.e = false;
        }
        this.f -= f2;
        this.i.a(this.E(0));
        if (this.cB > 0.0f && !this.e) {
            float f4 = !this.d ? 0.11f : 0.05f;
            if (com.corrodinggames.rts.game.units.d.p.a((y)this, this.i.a, this.i.b, this.eq, this.m(), f4)) {
                this.f = 3.0f;
            }
            if (this.cB < 0.0f) {
                this.cB = 0.0f;
                this.e = true;
            }
        }
        this.s = this.e ? 1 : 0;
    }

    public static boolean a(y y2, float f2, float f3, float f4, float f5, float f6) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        float f7 = f5 * f5;
        Object[] objectArray = com.corrodinggames.rts.game.f.a.a();
        int n2 = com.corrodinggames.rts.game.f.a.a;
        for (int i2 = 0; i2 < n2; ++i2) {
            com.corrodinggames.rts.gameFramework.d.e e2;
            float f8;
            f f9 = (f)objectArray[i2];
            if (f9.A || f9.C || !(f9.J > 7.0f) && (!(f9.J > 2.0f) || !(f9.t > 8.0f)) || f9.aS || !((f8 = (f9.eo - f2) * (f9.eo - f2) + (f9.ep - f3) * (f9.ep - f3)) < f7) || f9.eq < -1.0f) continue;
            boolean bl = false;
            if (f9.l != null && y2.bX.d(f9.l.bX)) {
                bl = true;
            }
            if (!bl && f9.j != null && y2.bX.c(f9.j.bX)) {
                bl = true;
            }
            if (!bl) continue;
            com.corrodinggames.rts.gameFramework.d.e e3 = l2.bR.a(f2, f3, f4, f9.eo, f9.ep, f9.eq);
            if (e3 != null) {
                e3.W = e3.V = 10.0f;
            }
            if ((e2 = l2.bR.b(f2, f3, f4, com.corrodinggames.rts.gameFramework.d.d.a, false, com.corrodinggames.rts.gameFramework.d.h.c)) != null) {
                e2.P = 0.0f;
                e2.Q = 0.0f;
                e2.ap = 4;
                e2.W = e2.V = 39.0f;
                e2.r = true;
                e2.E = 1.3f;
                e2.G = 1.1f;
                e2.F = 0.7f;
            }
            f9.H -= 1.01f;
            if (f9.H <= 0.0f) {
                f9.d();
                e2 = l2.bR.b(f9.eo, f9.ep, f9.eq, com.corrodinggames.rts.gameFramework.d.d.a, false, com.corrodinggames.rts.gameFramework.d.h.c);
                if (e2 != null) {
                    e2.P = 0.0f;
                    e2.Q = 0.0f;
                    e2.ap = 4;
                    e2.W = e2.V = 23.0f;
                    e2.r = true;
                    e2.E = 0.9f;
                    e2.G = 0.5f;
                    e2.F = 0.2f;
                }
                float f10 = 1.0f + com.corrodinggames.rts.gameFramework.f.c(-0.07f, 0.07f);
                l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.F, 0.2f, f10, f9.eo, f9.ep);
            }
            y2.cB -= f6;
            return true;
        }
        return false;
    }

    @Override
    public PointF E(int n2) {
        bg.a(this.eo, this.ep - 13.0f);
        return bg;
    }

    @Override
    public void a(am am2, int n2) {
    }

    @Override
    public float m() {
        if (!this.d) {
            return 160.0f;
        }
        return 210.0f;
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
        return com.corrodinggames.rts.game.units.ar.y;
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
    public float bW() {
        if (this.cB != 1.0f) {
            return this.cB;
        }
        return super.bW();
    }

    @Override
    public boolean bX() {
        return this.e;
    }

    @Override
    public float bd() {
        return 1.0f;
    }

    @Override
    public void a(j j2) {
        if (j2.j.equals(k.N())) {
            com.corrodinggames.rts.game.n.b((am)this);
            this.a(2);
            com.corrodinggames.rts.game.n.c(this);
            this.W();
        }
    }

    @Override
    public c cm() {
        if (!this.d) {
            return k.N();
        }
        return com.corrodinggames.rts.game.units.a.s.i;
    }

    @Override
    public int V() {
        if (this.d) {
            return 2;
        }
        return 1;
    }

    @Override
    public void a(int n2) {
        if (n2 == 1) {
            this.d = false;
        } else if (n2 == 2 && !this.d) {
            this.d = true;
            this.cv += 900.0f;
            this.cu += 900.0f;
        }
        this.S();
    }

    @Override
    public ArrayList N() {
        return l;
    }

    @Override
    public void e(float f2) {
        super.e(f2);
        float f3 = this.m();
        com.corrodinggames.rts.gameFramework.utility.y.a((am)this, f3);
    }

    @Override
    public float cZ() {
        return com.corrodinggames.rts.gameFramework.l.B().bL.n;
    }

    @Override
    public float da() {
        return com.corrodinggames.rts.gameFramework.l.B().bL.o;
    }

    @Override
    public float db() {
        return super.db() - 8.0f;
    }

    @Override
    public /* synthetic */ as r() {
        return this.K();
    }

    static {
        l.add(k);
    }
}
