/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.h;
import com.corrodinggames.rts.game.a.i;
import com.corrodinggames.rts.game.a.j;
import com.corrodinggames.rts.game.a.o;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.aq;
import com.corrodinggames.rts.gameFramework.e;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.m;
import com.corrodinggames.rts.gameFramework.utility.y;
import java.util.Iterator;

public class g
extends h {
    boolean a;
    String b;
    boolean c;
    boolean d;
    boolean e;
    boolean f;
    com.corrodinggames.rts.game.units.y g;
    boolean h = true;
    int i;
    int j;
    i k;
    float l = 1000.0f;
    float m = 100.0f;
    float n = 4000.0f;
    float o = 0.0f;
    float p = 1000.0f;
    boolean q = false;
    boolean r = false;
    boolean s = false;
    float t = 0.0f;
    float u = 0.0f;
    boolean v;
    am w;
    float x;
    float y;
    float z;
    int A;
    boolean B;
    public int C = -9999;
    public am D = null;
    ao E = ao.a;

    @Override
    public boolean a() {
        return this.a;
    }

    @Override
    public boolean b() {
        return !this.h;
    }

    public static g a(a a2, com.corrodinggames.rts.game.units.y y2) {
        g g2 = new g(a2, false);
        g2.a = true;
        g2.c = true;
        g2.d = true;
        g2.e = true;
        g2.g = y2;
        g2.a(y2);
        g2.A = 0;
        g2.k();
        return g2;
    }

    @Override
    public void a(as as2) {
        as2.a(this.h);
        as2.a(this.i);
        as2.a(this.j);
        int n2 = this.F.size();
        as2.a(n2);
        for (com.corrodinggames.rts.game.units.y y2 : this.F) {
            as2.a(y2);
        }
        as2.c(7);
        as2.a(false);
        as2.a(this.s);
        as2.a(this.o);
        as2.a(this.G.size());
        for (com.corrodinggames.rts.game.units.y y2 : this.G) {
            as2.a(y2);
        }
        as2.a(this.B);
        as2.a(this.a);
        as2.a(this.c);
        as2.a(this.d);
        as2.a(this.e);
        as2.a(this.f);
        as2.a(this.g);
        as2.a(this.A);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        int n2;
        this.h = k2.e();
        this.i = k2.f();
        this.j = k2.f();
        this.q();
        int n3 = k2.f();
        for (n2 = 0; n2 < n3; ++n2) {
            com.corrodinggames.rts.game.units.y y2 = k2.p();
            if (y2 == null) continue;
            this.a(y2);
        }
        n2 = k2.d();
        if (n2 >= 1) {
            k2.e();
        }
        if (n2 >= 2) {
            this.s = k2.e();
        }
        if (n2 >= 3) {
            this.o = k2.g();
        }
        if (n2 >= 4) {
            this.G.clear();
            int n4 = k2.f();
            for (int i2 = 0; i2 < n4; ++i2) {
                com.corrodinggames.rts.game.units.y y3 = k2.p();
                if (y3 == null) continue;
                this.G.add(y3);
            }
        }
        if (n2 >= 5) {
            this.B = k2.e();
        }
        if (n2 >= 6) {
            this.a = k2.e();
            this.c = k2.e();
            this.d = k2.e();
            this.e = k2.e();
            this.f = k2.e();
            this.g = k2.p();
        }
        if (n2 >= 7) {
            this.A = k2.f();
        }
        if (!this.B) {
            Iterator iterator = this.F.iterator();
            while (iterator.hasNext()) {
                com.corrodinggames.rts.game.units.y y4 = (com.corrodinggames.rts.game.units.y)iterator.next();
                if (!(y4 instanceof com.corrodinggames.rts.game.units.h.f)) continue;
                if (y4 != null && y4.aB == this) {
                    y4.aB = null;
                }
                if (y4 != null) {
                    this.G.remove(y4);
                }
                iterator.remove();
            }
        }
        super.a(k2);
    }

    public g(a a2) {
        super(a2);
    }

    public g(a a2, boolean bl) {
        this(a2);
        this.h = bl;
    }

    @Override
    protected void a(com.corrodinggames.rts.game.units.y y2) {
        super.a(y2);
        this.E = this.j();
    }

    public void c() {
        for (am am2 : am.bE) {
            if (am2.bV || am2.bX != this.R || this.A <= this.F.size() || !(am2 instanceof com.corrodinggames.rts.game.units.y)) continue;
            com.corrodinggames.rts.game.units.y y2 = (com.corrodinggames.rts.game.units.y)am2;
            if (y2.bM || y2.bN || y2.aB != null || !this.R.h(y2) || !this.R.i(y2) || (!this.B ? am2.h() == ao.e : am2.h() == ao.b)) continue;
            if (!this.R.a((am)y2, this.S, this.T) && (this.b() || com.corrodinggames.rts.gameFramework.f.a(0, 100) > 2)) continue;
            this.a(y2);
        }
    }

    public boolean d() {
        return this.A <= this.F.size();
    }

    public am a(float f2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if ((float)l2.by - f2 * 1000.0f < (float)this.C) {
            return this.D;
        }
        return null;
    }

    public am e() {
        am am2 = this.a(6.0f);
        if (am2 != null) {
            return am2;
        }
        return null;
    }

    public am f() {
        for (com.corrodinggames.rts.game.units.y y2 : this.F) {
            am am2 = y2.ab();
            if (am2 == null) continue;
            return am2;
        }
        return null;
    }

    public void a(e e2, boolean bl, am am2) {
        for (com.corrodinggames.rts.game.units.y y2 : this.F) {
            if (bl && !y2.aq() || am2 != null && !this.R.a((am)y2, am2)) continue;
            e2.a(y2);
        }
    }

    public void a(String string) {
        this.b = string;
    }

    public PointF a(am am2) {
        PointF pointF = new PointF();
        pointF.a = this.S;
        pointF.b = this.T;
        float f2 = 50.0f;
        float f3 = 100.0f;
        float f4 = (float)(Math.random() * 360.0);
        float f5 = com.corrodinggames.rts.gameFramework.f.c(f2, f3);
        pointF.a += com.corrodinggames.rts.gameFramework.f.k(f4) * f5;
        pointF.b += com.corrodinggames.rts.gameFramework.f.j(f4) * f5;
        if (am2 != null) {
            f2 = 100.0f;
            f3 = 200.0f;
            f4 = com.corrodinggames.rts.gameFramework.f.d(pointF.a, pointF.b, am2.eo, am2.ep);
            f5 = com.corrodinggames.rts.gameFramework.f.c(f2, f3);
            pointF.a += com.corrodinggames.rts.gameFramework.f.k(f4) * -f5;
            pointF.b += com.corrodinggames.rts.gameFramework.f.j(f4) * -f5;
        }
        return pointF;
    }

    @Override
    public void b(float f2) {
        am am2;
        am am3;
        super.b(f2);
        this.n();
        this.E = this.j();
        if (!this.f && (am3 = this.e()) != null && (am2 = this.f()) == null) {
            if (this.a(am3, false)) {
                this.a("fighting attacker");
                l l2 = com.corrodinggames.rts.gameFramework.l.B();
                e e2 = l2.cf.a(this.R);
                this.a(e2, true, am3);
                boolean bl = false;
                e2.a(am3.eo, am3.ep, bl);
            } else {
                this.a("flight from attacker");
                PointF pointF = this.a(am3);
                this.S = pointF.a;
                this.T = pointF.b;
                if (this.z > 200.0f) {
                    this.z = 200.0f;
                }
            }
        }
    }

    @Override
    public void c(float f2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.x += f2;
        for (Object object : this.F) {
            if (object == null || this.C >= ((com.corrodinggames.rts.game.units.y)object).bs) continue;
            this.C = ((com.corrodinggames.rts.game.units.y)object).bs;
            this.D = ((com.corrodinggames.rts.game.units.y)object).bt;
        }
        this.n();
        if (this.d()) {
            this.l = com.corrodinggames.rts.gameFramework.f.a(this.l, f2);
        } else if (this.v) {
            // empty if block
        }
        this.y = com.corrodinggames.rts.gameFramework.f.a(this.y, f2);
        this.z = com.corrodinggames.rts.gameFramework.f.a(this.z, f2);
        this.p = com.corrodinggames.rts.gameFramework.f.a(this.p, f2);
        if (!(this.v || this.r || this.d() || this.y != 0.0f)) {
            this.y = 200 + com.corrodinggames.rts.gameFramework.f.c(200);
            this.c();
        }
        if (!this.v || this.q) {
            Object object;
            if (!this.q) {
                this.n = com.corrodinggames.rts.gameFramework.f.a(this.n, f2);
                if (this.n == 0.0f) {
                    if (this.k == null) {
                        this.k = this.g();
                    }
                    if (this.k != null) {
                        object = this.k.w();
                        if (!this.a(((PointF)object).a, ((PointF)object).b)) {
                            this.n = 100.0f;
                            this.a("random move: bad target");
                        } else {
                            this.n = 4000.0f;
                            this.S = ((PointF)object).a;
                            this.T = ((PointF)object).b;
                            this.a("random move");
                        }
                    } else {
                        this.a("random move: no linked base");
                    }
                }
            }
            if (this.z == 0.0f) {
                this.z = 800.0f;
                object = l2.cf.a(this.R);
                for (com.corrodinggames.rts.game.units.y y2 : this.F) {
                    boolean bl = true;
                    if (this.c(y2) < 28900.0f) {
                        bl = false;
                    }
                    if (!this.f && y2.aj() && !y2.aq()) {
                        bl = false;
                    }
                    if (!bl) continue;
                    ((e)object).a(y2);
                }
                if (this.f) {
                    ((e)object).a(this.S, this.T);
                } else {
                    ((e)object).b(this.S, this.T);
                }
            }
        }
        if (this.h) {
            this.e(f2);
        } else {
            this.d(f2);
        }
        if (this.A == 0 && this.F.size() == 0) {
            this.p();
        }
        if (this.c && (this.g == null || this.g.bV)) {
            this.p();
        }
    }

    i g() {
        float f2 = -1.0f;
        i i2 = null;
        for (o o2 : this.R.bn) {
            if (!(o2 instanceof i)) continue;
            i i3 = (i)o2;
            if (!this.b(i3.S, i3.T)) continue;
            float f3 = i3.d(this.S, this.T);
            if (i2 != null && !(f3 < f2)) continue;
            f2 = f3;
            i2 = i3;
        }
        return i2;
    }

    public void d(float f2) {
        int n2;
        if (this.k == null || this.k.V) {
            this.k();
        }
        if (this.c && this.g != null) {
            if (this.e && !this.f) {
                if ((double)(this.g.cu / this.g.cv) < 0.5) {
                    this.f = true;
                    if (this.z > 100.0f) {
                        this.z = 100.0f;
                    }
                }
                if (this.w == null) {
                    this.k();
                }
            } else {
                if ((double)(this.g.cu / this.g.cv) > 0.6) {
                    this.f = false;
                }
                n2 = 0;
                if (this.k != null && !this.k.t) {
                    n2 = 1;
                }
                if (n2 == 0) {
                    boolean bl = true;
                    i i2 = this.R.a(this.g.h(), this.g.eo, this.g.ep, bl);
                    if (i2 != null) {
                        this.k = i2;
                    }
                    if (this.k != null) {
                        PointF pointF = this.k.w();
                        this.S = pointF.a;
                        this.T = pointF.b;
                        if (this.z > 100.0f) {
                            this.z = 100.0f;
                        }
                        this.a("moving to new base");
                    }
                }
            }
        }
        if (this.k != null) {
            for (n2 = 0; n2 < 2; ++n2) {
                if (this.p != 0.0f) continue;
                am am2 = this.k.g();
                if (am2 == null) break;
                if (!this.a(am2, false)) continue;
                this.w = am2;
                this.p = 500.0f;
                this.n = 2000.0f;
                if (!this.f) {
                    this.S = am2.eo;
                    this.T = am2.ep;
                }
                if (this.z > 100.0f) {
                    this.z = 100.0f;
                }
                this.a("defending base");
            }
            if (this.p == 0.0f) {
                this.f = false;
                this.w = null;
            }
        }
    }

    public void e(float f2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (!this.v) {
            if (this.l == 0.0f) {
                this.v = true;
                this.q = true;
            }
        } else {
            if (this.w == null || !this.w.bT() || this.w.bV || !this.r) {
                this.w = this.R.as();
                if (this.w != null && !this.a(this.w, true)) {
                    this.w = null;
                }
            }
            if (this.w != null) {
                if (this.q) {
                    this.u += f2;
                    if (!this.r) {
                        this.t = com.corrodinggames.rts.gameFramework.f.a(this.t, f2);
                        if (this.t == 0.0f) {
                            this.t = 20.0f;
                            this.h();
                        }
                    } else {
                        boolean bl = false;
                        for (com.corrodinggames.rts.game.units.y y2 : this.F) {
                            if (!(this.c(y2) > 28900.0f)) continue;
                            bl = true;
                        }
                        if (!bl) {
                            this.q = false;
                        }
                        for (com.corrodinggames.rts.game.units.y y2 : this.F) {
                            if (y2.bs <= l2.by - 1000) continue;
                            this.q = false;
                            this.a("Not staging due to damage");
                        }
                    }
                    if (this.u > 17000.0f) {
                        this.q = false;
                        this.a("attacking target");
                    }
                } else {
                    this.o += f2;
                    if (this.z == 0.0f) {
                        this.z = 800.0f;
                        boolean bl = false;
                        m m2 = new m();
                        for (com.corrodinggames.rts.game.units.y y3 : this.F) {
                            boolean bl2 = true;
                            if (this.w != null) {
                                if (!this.R.a((am)y3, this.w)) {
                                    bl2 = false;
                                }
                                if (bl2 && !aq.a(y3, this.w)) {
                                    bl2 = false;
                                }
                            }
                            if (!bl2) continue;
                            bl = true;
                            m2.add(y3);
                        }
                        if (!bl) {
                            this.q = false;
                            this.a("cannot reach main target");
                        } else {
                            e e2 = l2.cf.a(this.R);
                            e2.a(m2);
                            boolean bl3 = true;
                            if (this.w != null && com.corrodinggames.rts.gameFramework.f.a(0, 100) < 80) {
                                e2.a(this.w.eo, this.w.ep, bl3);
                            } else {
                                e2.a(this.w, bl3);
                            }
                            this.a("attacking main target");
                        }
                    }
                }
            }
        }
        if (this.v) {
            if (this.F.size() == 0) {
                this.p();
            }
            if (this.o > 1000.0f && this.F.size() < 3) {
                this.p();
            }
            if (this.o > 11000.0f) {
                this.p();
            }
        }
    }

    public void h() {
        int n2;
        float f2 = this.w.eo;
        float f3 = this.w.ep;
        float f4 = com.corrodinggames.rts.gameFramework.f.d(f2, f3, this.S, this.T);
        float f5 = com.corrodinggames.rts.gameFramework.f.b(f2, f3, this.S, this.T);
        if (com.corrodinggames.rts.gameFramework.f.a(0, 100) < 80) {
            f4 += (float)com.corrodinggames.rts.gameFramework.f.a(-110, 110);
        }
        if ((n2 = (int)((double)f5 * 0.6)) < 720) {
            n2 = 720;
        }
        float f6 = com.corrodinggames.rts.gameFramework.f.a(50, n2);
        if (com.corrodinggames.rts.gameFramework.f.a(0, 100) < 80 && f6 < 450.0f) {
            f6 = com.corrodinggames.rts.gameFramework.f.a(450, n2);
        }
        boolean bl = true;
        if (!this.a(f2 += com.corrodinggames.rts.gameFramework.f.k(f4) * f6, f3 += com.corrodinggames.rts.gameFramework.f.j(f4) * f6)) {
            bl = false;
        }
        boolean bl2 = false;
        boolean bl3 = false;
        for (com.corrodinggames.rts.game.units.y y2 : this.F) {
            if (y2.h() == ao.b) {
                bl2 = true;
            }
            if (y2.h() != ao.e) continue;
            bl3 = true;
        }
        if (bl2) {
            if (this.R.aG == 0 && !this.b(f2, f3)) {
                bl = false;
            }
            if (!this.R.a(f2, f3, this.w.eo, this.w.ep, ao.b) && com.corrodinggames.rts.gameFramework.f.a(0, 100) < 98) {
                bl = false;
            }
        }
        if (bl3) {
            if (!this.b(f2, f3)) {
                bl = false;
            }
            if (!this.R.a(f2, f3, this.w.eo, this.w.ep, ao.e)) {
                bl = false;
            }
        }
        if (bl) {
            this.S = f2;
            this.T = f3;
            this.z = 0.0f;
            this.r = true;
            this.G.clear();
            for (com.corrodinggames.rts.game.units.y y2 : this.F) {
                if (y2.h() == ao.e || this.R.a((am)y2, this.S, this.T)) continue;
                this.G.add(y2);
            }
        }
    }

    public ao i() {
        return this.E;
    }

    public ao j() {
        if (this.F.size() == 0) {
            if (this.B) {
                return ao.e;
            }
            return ao.b;
        }
        boolean bl = true;
        for (Object object : this.F) {
            Object object2 = ((am)object).h();
            if (object2 == ao.d) continue;
            bl = false;
            break;
        }
        if (bl) {
            return ao.d;
        }
        if (this.B) {
            boolean bl2 = true;
            for (Object object2 : this.F) {
                ao ao2 = ((am)object2).h();
                if (ao2 != ao.e) continue;
                bl2 = false;
            }
            if (bl2) {
                return ao.f;
            }
            return ao.e;
        }
        boolean bl3 = true;
        for (Object object2 : this.F) {
            ao ao3 = ((am)object2).h();
            if (ao3 != ao.b && ao3 != ao.g) continue;
            bl3 = false;
        }
        if (bl3) {
            return ao.f;
        }
        return ao.b;
    }

    public boolean a(float f2, float f3) {
        return !com.corrodinggames.rts.gameFramework.utility.y.a(f2, f3, this.i());
    }

    public boolean b(float f2, float f3) {
        for (com.corrodinggames.rts.game.units.y y2 : this.F) {
            if (this.R.a((am)y2, f2, f3)) continue;
            return false;
        }
        return true;
    }

    public boolean a(am am2, boolean bl) {
        for (com.corrodinggames.rts.game.units.y y2 : this.F) {
            if (!bl && !this.R.a((am)y2, am2.eo, am2.ep) || !aq.a(y2, am2)) continue;
            return true;
        }
        return false;
    }

    public void k() {
        boolean bl = true;
        PointF pointF = null;
        if (this.c && this.g != null) {
            this.S = this.g.eo;
            this.T = this.g.ep;
            this.k = this.R.c(this.g.eo, this.g.ep);
            return;
        }
        if (bl) {
            for (int i2 = 0; i2 < 7; ++i2) {
                boolean bl2;
                boolean bl3 = bl2 = i2 > 3;
                if (pointF != null) continue;
                for (o o2 : this.R.bn) {
                    if (!(o2 instanceof i)) continue;
                    i i3 = (i)o2;
                    if (i3.b != com.corrodinggames.rts.game.a.j.c || i3.u() <= 2 && !bl2 || pointF != null && com.corrodinggames.rts.gameFramework.f.c(this.R.ay + 2) != 0) continue;
                    for (int i4 = 0; i4 < 10; ++i4) {
                        if (pointF != null) continue;
                        PointF pointF2 = i3.w();
                        if (!this.a(pointF2.a, pointF2.b)) continue;
                        pointF = pointF2;
                    }
                    this.k = i3;
                }
            }
        }
        if (pointF == null) {
            pointF = this.R.am();
            this.k = null;
        }
        this.S = pointF.a;
        this.T = pointF.b;
    }
}
