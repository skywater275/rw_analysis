/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.b;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.f;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.ak;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.b.b;
import com.corrodinggames.rts.game.units.b.d$1;
import com.corrodinggames.rts.game.units.b.d$2;
import com.corrodinggames.rts.game.units.e.i;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.a.e;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.util.ArrayList;

public strictfp class d
extends b
implements ak {
    static com.corrodinggames.rts.gameFramework.m.e a = null;
    static com.corrodinggames.rts.gameFramework.m.e b = null;
    static com.corrodinggames.rts.gameFramework.m.e c = null;
    static com.corrodinggames.rts.gameFramework.m.e[] d = new com.corrodinggames.rts.gameFramework.m.e[10];
    float e = 0.0f;
    float f;
    boolean g;
    m o = new m();
    Rect p = new Rect();
    public static final s q = new d$1(109);
    public static final s r = new d$2(110);
    static ArrayList s = new ArrayList();

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.e);
        as2.a(this.f);
        as2.a(this.g);
        as2.a(this.o.size());
        for (am am2 : this.o) {
            as2.a(am2);
        }
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        this.e = k2.g();
        this.f = k2.g();
        this.g = k2.e();
        this.o.clear();
        int n2 = k2.f();
        for (int i = 0; i < n2; ++i) {
            am am2 = k2.o();
            if (am2 == null) continue;
            this.o.add(am2);
        }
        super.a(k2);
    }

    @Override
    public int bY() {
        return com.corrodinggames.rts.game.units.e.i.a(this.o);
    }

    @Override
    public int bZ() {
        return 4;
    }

    public ar b() {
        return com.corrodinggames.rts.game.units.ar.z;
    }

    public static void L() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        b = l2.bO.a(R$drawable.dropship);
        c = l2.bO.a(R$drawable.dropship_shadow);
        a = l2.bO.a(R$drawable.dropship_dead);
        d = com.corrodinggames.rts.game.n.a(b);
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e d() {
        if (this.bV) {
            return a;
        }
        return d[this.bX.R()];
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e k() {
        return c;
    }

    @Override
    public com.corrodinggames.rts.gameFramework.m.e d(int n2) {
        return null;
    }

    @Override
    public boolean e() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bR.b(this.eo, this.ep, this.eq);
        this.M = a;
        this.S(0);
        this.bT = false;
        this.f(true);
        return true;
    }

    public d(boolean bl) {
        super(bl);
        this.T(45);
        this.U(47);
        this.cj = 20.0f;
        this.ck = this.cj + 0.0f;
        this.cu = this.cv = 500.0f;
        this.M = b;
        this.N = c;
        this.eq = 0.0f;
    }

    @Override
    public boolean I() {
        return true;
    }

    @Override
    public boolean i() {
        return this.eq >= 4.0f;
    }

    @Override
    public boolean ct() {
        return true;
    }

    @Override
    public void a(float f2) {
        boolean bl;
        super.a(f2);
        if (this.bV) {
            return;
        }
        boolean bl2 = this.cK();
        if (this.g && !bl2 && !this.cK && this.eq < 4.0f) {
            this.f = com.corrodinggames.rts.gameFramework.f.a(this.f, f2);
            if (this.f == 0.0f) {
                this.f = 30.0f;
                if (this.o.size() == 0) {
                    this.g = false;
                } else {
                    bl = this.o.size() % 2 == 0;
                    float f3 = this.eo + com.corrodinggames.rts.gameFramework.f.k(this.cg) * -9.0f;
                    float f4 = this.ep + com.corrodinggames.rts.gameFramework.f.j(this.cg) * -9.0f;
                    f3 += com.corrodinggames.rts.gameFramework.f.k(this.cg + 90.0f) * (float)(bl ? -7 : 7);
                    am am2 = (am)this.o.remove(this.o.size() - 1);
                    if (!com.corrodinggames.rts.gameFramework.utility.y.a(am2, f3 += com.corrodinggames.rts.gameFramework.f.j(this.cg + 90.0f) * (float)(bl ? -7 : 7), f4)) {
                        f3 += 10.0f;
                    }
                    if (!com.corrodinggames.rts.gameFramework.utility.y.a(am2, f3, f4)) {
                        f3 -= 20.0f;
                    }
                    if (!com.corrodinggames.rts.gameFramework.utility.y.a(am2, f3, f4)) {
                        f3 -= 10.0f;
                        f4 += 10.0f;
                    }
                    if (!com.corrodinggames.rts.gameFramework.utility.y.a(am2, f3, f4)) {
                        f4 -= 20.0f;
                    }
                    if (!com.corrodinggames.rts.gameFramework.utility.y.a(am2, f3, f4)) {
                        this.o.add(am2);
                    } else {
                        am2.cN = null;
                        am2.eo = f3;
                        am2.ep = f4;
                        am2.bZ += 0.1f;
                        am2.cg = this.cg + 180.0f;
                        am2.bR = this;
                        am2.bS = 45.0f;
                        if (am2 instanceof y) {
                            y y2 = (y)am2;
                            y2.az();
                            y2.d(this.eo + com.corrodinggames.rts.gameFramework.f.k(this.cg) * -66.0f, this.ep + com.corrodinggames.rts.gameFramework.f.j(this.cg) * -66.0f);
                        }
                        if (this.o.size() == 0) {
                            this.g = false;
                        }
                    }
                }
            }
        }
        this.e += 2.0f * f2;
        if (this.e > 360.0f) {
            this.e -= 360.0f;
        }
        bl = this.i();
        if (this.bT()) {
            this.eq = this.aq() && !bl2 ? com.corrodinggames.rts.gameFramework.f.a(this.eq, 2.0f, 0.4f * f2) : com.corrodinggames.rts.gameFramework.f.a(this.eq, 35.0f + com.corrodinggames.rts.gameFramework.f.j(this.e) * 1.5f, 0.35f * f2);
        }
        if (bl != this.i()) {
            this.ay = true;
            if (this.i()) {
                this.S(5);
            } else {
                this.S(2);
            }
        }
    }

    @Override
    public PointF E(int n2) {
        float f2 = this.g(n2);
        float f3 = this.cg;
        float f4 = this.eo + com.corrodinggames.rts.gameFramework.f.k(f3) * f2;
        float f5 = this.ep + com.corrodinggames.rts.gameFramework.f.j(f3) * f2;
        bg.a(f4, f5);
        return bg;
    }

    @Override
    public void a(am am2, int n2) {
        PointF pointF = this.E(n2);
        f f2 = com.corrodinggames.rts.game.f.a((am)this, pointF.a, pointF.b, this.eq, n2);
        f2.ar = Color.a(255, 150, 230, 40);
        f2.U = 35.0f;
        f2.l = am2;
        f2.h = 80.0f;
        f2.t = 4.0f;
        f2.x = 2.0f;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bR.a(pointF.a, pointF.b, this.eq, -1127220);
        l2.bR.a(pointF.a, pointF.b, this.eq, this.cL[n2].a);
        l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.u, 0.3f, this.eo, this.ep);
    }

    @Override
    public float m() {
        return 140.0f;
    }

    @Override
    public float b(int n2) {
        return 40.0f;
    }

    @Override
    public float z() {
        return 2.3f;
    }

    @Override
    public float A() {
        return 1.4f;
    }

    @Override
    public float c(int n2) {
        return 99.0f;
    }

    @Override
    public boolean E() {
        return false;
    }

    @Override
    public float C() {
        return 0.03f;
    }

    @Override
    public float D() {
        return 0.05f;
    }

    @Override
    public boolean l() {
        return false;
    }

    @Override
    public float g(int n2) {
        return 15.0f;
    }

    @Override
    public void a() {
        this.f(true);
        super.a();
    }

    public void f(boolean bl) {
        for (am am2 : this.o) {
            am2.cN = null;
            am2.eo = this.eo + com.corrodinggames.rts.gameFramework.f.k(this.cg) * -9.0f;
            am2.ep = this.ep + com.corrodinggames.rts.gameFramework.f.j(this.cg) * -9.0f;
            if (!bl) continue;
            am2.cj();
        }
        this.o.clear();
    }

    @Override
    public boolean bA() {
        return this.g;
    }

    public void M() {
        this.g = true;
        this.f = 30.0f;
    }

    public void ds() {
        this.g = false;
    }

    @Override
    public float bN() {
        return 16000.0f;
    }

    @Override
    public boolean d(am am2, boolean bl) {
        if (this.g) {
            return false;
        }
        if (!com.corrodinggames.rts.game.units.e.i.a(this.o, 4, am2)) {
            return false;
        }
        if (am2 == this) {
            return false;
        }
        if (this.bX != am2.bX && !bl) {
            return false;
        }
        return com.corrodinggames.rts.gameFramework.utility.y.a(am2, true, true);
    }

    @Override
    public boolean e(am am2, boolean bl) {
        if (!this.d(am2, bl)) {
            return false;
        }
        this.C(am2);
        return true;
    }

    public void C(am am2) {
        am2.cN = this;
        this.o.add(am2);
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bS.l(am2);
    }

    @Override
    public void e(am am2) {
        if (am2.cN == this) {
            this.o.remove(am2);
            am2.cN = null;
        } else {
            com.corrodinggames.rts.gameFramework.l.g("Unit is not being transported");
        }
    }

    @Override
    public void a(s s2, boolean bl) {
        if (s2 == q) {
            this.M();
        }
        if (s2 == r) {
            this.ds();
        }
    }

    @Override
    public int bB() {
        return this.o.size();
    }

    @Override
    public boolean cr() {
        return true;
    }

    @Override
    public c cp() {
        return q.N();
    }

    @Override
    public ArrayList N() {
        return s;
    }

    @Override
    public boolean f() {
        return !this.cK();
    }

    @Override
    public boolean j() {
        return true;
    }

    @Override
    public m bz() {
        return this.o;
    }

    @Override
    public /* synthetic */ as r() {
        return this.b();
    }

    static {
        s.add(q);
        s.add(r);
    }
}
