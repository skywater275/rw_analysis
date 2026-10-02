/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.d.a;

import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.ab;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.d.a.a;
import com.corrodinggames.rts.game.units.d.a.b$1;
import com.corrodinggames.rts.game.units.d.a.b$2;
import com.corrodinggames.rts.game.units.d.a.b$3;
import com.corrodinggames.rts.game.units.d.a.b$4;
import com.corrodinggames.rts.game.units.d.a.c;
import com.corrodinggames.rts.game.units.d.a.d;
import com.corrodinggames.rts.game.units.d.a.e;
import com.corrodinggames.rts.game.units.d.a.f;
import com.corrodinggames.rts.game.units.d.a.g;
import com.corrodinggames.rts.game.units.d.a.h;
import com.corrodinggames.rts.game.units.d.i;
import com.corrodinggames.rts.game.units.d.j;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.y;
import java.util.ArrayList;

public strictfp class b
extends i {
    static com.corrodinggames.rts.gameFramework.m.e g = null;
    private static com.corrodinggames.rts.gameFramework.m.e a = null;
    private static com.corrodinggames.rts.gameFramework.m.e b = null;
    private static com.corrodinggames.rts.gameFramework.m.e c = null;
    private static com.corrodinggames.rts.gameFramework.m.e d = null;
    private static com.corrodinggames.rts.gameFramework.m.e e = null;
    static com.corrodinggames.rts.gameFramework.m.e[] h = new com.corrodinggames.rts.gameFramework.m.e[10];
    static com.corrodinggames.rts.gameFramework.m.e i = null;
    boolean j;
    int k;
    c l = new h(this);
    static String t = "gun";
    static String u = "gunT2";
    static String v = "gunT3";
    static String w = "artillery";
    static String x = "flamethrower";
    static String C = "aa_t1";
    static String D = "aa_t2";
    static String E = "aa_flak";
    static com.corrodinggames.rts.gameFramework.m.e F = null;
    static com.corrodinggames.rts.gameFramework.m.e[] G = new com.corrodinggames.rts.gameFramework.m.e[10];
    boolean H = true;
    float I;
    float J;
    boolean K;
    Rect dK = new Rect();
    public static s dL = new b$1(101);
    public static s dM = new b$2(104);
    public static s dN = new b$3(102);
    public static s dO = new b$4(103);
    static ArrayList dP = new ArrayList();

    public int M() {
        return this.l.b();
    }

    @Override
    public float H(int n2) {
        return this.l.h(n2);
    }

    @Override
    public void a_(String string) {
        this.b(string);
    }

    public void b(String string) {
        if (!this.l.a(string)) {
            c c2 = this.l;
            this.l = this.c(string);
            this.l.a(c2);
        }
    }

    public c c(String string) {
        if (string.equals(t)) {
            return new h(this);
        }
        if (string.equals(u)) {
            return new f(this);
        }
        if (string.equals(v)) {
            return new g(this);
        }
        if (string.equals(w)) {
            return new d(this);
        }
        if (string.equals(x)) {
            return new e(this);
        }
        return null;
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.j);
        as2.a(this.k == 1);
        as2.c(this.l.c());
        as2.a(this.k);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        boolean bl = k2.e();
        if (bl) {
            this.a(2);
        }
        if (k2.b() >= 27) {
            int n2 = this.k = k2.e() ? 1 : 0;
        }
        if (k2.b() >= 35) {
            String string = k2.l();
            if (!this.l.a(string)) {
                this.b(string);
            }
            this.k = k2.f();
        } else if (bl && !(this instanceof a)) {
            this.b(u);
        }
        super.a(k2);
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e v() {
        if (this.bX.k == -1) {
            return null;
        }
        return G[this.bX.R()];
    }

    public static void dB() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        g = l2.bO.a(R$drawable.turret_base);
        i = l2.bO.a(R$drawable.turret_base_dead);
        a = l2.bO.a(R$drawable.turret_top);
        b = l2.bO.a(R$drawable.turret_top_l2);
        c = l2.bO.a(R$drawable.turret_top_l3);
        d = l2.bO.a(R$drawable.turret_top_artillery);
        e = l2.bO.a(R$drawable.turret_top_flame);
        h = com.corrodinggames.rts.game.n.a(g);
        F = l2.bO.a(R$drawable.unit_icon_building_turrent);
        G = com.corrodinggames.rts.game.n.a(F);
    }

    @Override
    public boolean L() {
        this.M = i;
        this.S(0);
        this.bT = false;
        this.a(com.corrodinggames.rts.game.units.ab.d);
        return true;
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e d() {
        if (this.bV) {
            return i;
        }
        if (this.bX == null) {
            return h[h.length - 1];
        }
        return h[this.bX.R()];
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e k() {
        return null;
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e d(int n2) {
        return this.l.d(n2);
    }

    public b(boolean bl) {
        super(bl);
        this.T(35);
        this.U(42);
        this.ck = this.cj = 16.0f;
        this.cu = this.cv = 700.0f;
        this.M = g;
        this.cL[0].a = com.corrodinggames.rts.gameFramework.f.a(this, -180, 180);
        this.n.a(0, 0, 1, 1);
        this.o.a(0, 0, 1, 1);
    }

    public void s(float f2) {
        int n2 = 0;
        if (this.cL[n2].a()) {
            if (this.H) {
                this.I = this.cL[n2].a;
                this.H = false;
                this.J = com.corrodinggames.rts.gameFramework.f.a(this, 0, 120);
            }
            this.J += f2;
            if (this.J > 450.0f) {
                this.J = com.corrodinggames.rts.gameFramework.f.a(this, 0, 30);
                boolean bl = this.K = !this.K;
            }
            if (this.J < 120.0f) {
                if (this.K) {
                    this.a(f2 * 0.3f, this.I - 20.0f, n2);
                } else {
                    this.a(f2 * 0.3f, this.I + 20.0f, n2);
                }
            }
        } else {
            this.H = true;
        }
    }

    @Override
    public void a(float f2) {
        super.a(f2);
        if (this.bT()) {
            this.l.a(f2);
        }
    }

    @Override
    public void a(am am2, int n2) {
        this.l.a(am2, n2);
    }

    @Override
    public float m() {
        return this.l.a();
    }

    @Override
    public float b(int n2) {
        return this.l.a(n2);
    }

    @Override
    public float c(int n2) {
        return this.l.e(n2);
    }

    @Override
    public float w(int n2) {
        return this.l.f(n2);
    }

    @Override
    public boolean b(int n2, float f2) {
        return false;
    }

    @Override
    public boolean c(float f2) {
        if (!super.c(f2)) {
            return false;
        }
        if (!this.bV) {
            this.dC();
        }
        return true;
    }

    void dC() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        com.corrodinggames.rts.gameFramework.m.e e2 = null;
        int n2 = 0;
        e2 = this.d(n2);
        PointF pointF = this.G(n2);
        l2.bO.a(e2, pointF.a - com.corrodinggames.rts.gameFramework.l.B().cw, pointF.b - com.corrodinggames.rts.gameFramework.l.B().cx, this.cL[n2].a, this.f());
    }

    public ar K() {
        return com.corrodinggames.rts.game.units.ar.f;
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
        return this.l.g(n2);
    }

    @Override
    public void M(int n2) {
        if (this.b(n2) < 10.0f) {
            return;
        }
        super.M(n2);
    }

    @Override
    public void a(j j2) {
        s s2 = this.a(j2.j);
        if (s2 != null) {
            s2.f(this);
        } else {
            com.corrodinggames.rts.gameFramework.j.ad.a("specialAction=null on completeQueueItem(turret) for item.uIndex:" + j2.j + " id:" + this.eh, true);
        }
    }

    @Override
    public com.corrodinggames.rts.game.units.a.c cm() {
        if (this.M() == 1) {
            return dL.N();
        }
        if (this.l instanceof f) {
            return dM.N();
        }
        return s.i;
    }

    @Override
    public void a(ArrayList arrayList) {
        arrayList.clear();
        if (this.M() == 1) {
            arrayList.add(dN.N());
            arrayList.add(dO.N());
        }
    }

    @Override
    public void a(int n2) {
        if (n2 == 1) {
            this.j = false;
        } else if (n2 == 2 && !this.j) {
            this.j = true;
        }
    }

    @Override
    public PointF E(int n2) {
        return this.l.c(n2);
    }

    @Override
    public float bV() {
        if (this.cL[0].e > 0.0f && this.l.a(w)) {
            return 1.0f - this.cL[0].e / this.b(0);
        }
        return super.bV();
    }

    @Override
    public PointF G(int n2) {
        bh.a(super.G(n2));
        bh.b(0.0f, -5.0f);
        return bh;
    }

    @Override
    public ArrayList N() {
        return dP;
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
    public int cL() {
        return this.l.d();
    }

    @Override
    public float q(int n2) {
        return this.l.b(n2);
    }

    @Override
    public /* synthetic */ as r() {
        return this.K();
    }

    static /* synthetic */ PointF a(b b2, int n2) {
        return super.E(n2);
    }

    static /* synthetic */ com.corrodinggames.rts.gameFramework.m.e dD() {
        return a;
    }

    static /* synthetic */ com.corrodinggames.rts.gameFramework.m.e dE() {
        return b;
    }

    static /* synthetic */ PointF b(b b2, int n2) {
        return super.E(n2);
    }

    static /* synthetic */ com.corrodinggames.rts.gameFramework.m.e dF() {
        return c;
    }

    static /* synthetic */ PointF c(b b2, int n2) {
        return super.E(n2);
    }

    static /* synthetic */ com.corrodinggames.rts.gameFramework.m.e dG() {
        return d;
    }

    static /* synthetic */ com.corrodinggames.rts.gameFramework.m.e dH() {
        return e;
    }

    static /* synthetic */ void a(b b2) {
        b2.W();
    }

    static /* synthetic */ void b(b b2) {
        b2.W();
    }

    static /* synthetic */ void c(b b2) {
        b2.W();
    }

    static /* synthetic */ void d(b b2) {
        b2.W();
    }

    static {
        dP.add(dL);
        dP.add(dM);
        dP.add(dN);
        dP.add(dO);
    }
}
