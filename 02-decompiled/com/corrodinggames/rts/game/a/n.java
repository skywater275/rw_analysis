/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.h;
import com.corrodinggames.rts.game.a.i;
import com.corrodinggames.rts.game.a.j;
import com.corrodinggames.rts.game.a.l;
import com.corrodinggames.rts.game.a.o;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.ak;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.gameFramework.e;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.utility.y;
import java.util.ArrayList;
import java.util.Iterator;

public class n
extends h {
    boolean a;
    int b;
    int c;
    i d;
    float e = 100.0f;
    float f = 4000.0f;
    float g = 100.0f;
    float h;
    float i;
    float j;
    float k;
    int l;
    h m;
    com.corrodinggames.rts.game.units.y n;
    float o = 0.0f;
    boolean p = false;
    boolean q;
    float r;
    float s;

    @Override
    public void a(as as2) {
        as2.a(this.a);
        as2.a(this.b);
        as2.a(this.c);
        int n2 = this.F.size();
        as2.a(n2);
        for (com.corrodinggames.rts.game.units.y y2 : this.F) {
            as2.a(y2);
        }
        as2.c(5);
        as2.a(this.R.a(this.m));
        as2.a(this.q);
        as2.a(this.n);
        as2.a(this.o);
        as2.a(this.p);
        as2.a(this.r);
        as2.a(this.s);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        int n2;
        this.a = k2.e();
        this.b = k2.f();
        this.c = k2.f();
        this.q();
        int n3 = k2.f();
        for (n2 = 0; n2 < n3; ++n2) {
            com.corrodinggames.rts.game.units.y y2 = k2.p();
            if (y2 == null) continue;
            if (!this.R.g(y2)) {
                com.corrodinggames.rts.gameFramework.l.b("TransporterGroup:readIn: Unit is not transporterUnit");
                continue;
            }
            this.a(y2);
        }
        n2 = k2.d();
        if (n2 >= 1) {
            this.m = (h)this.R.m(k2.f());
        }
        if (n2 >= 2) {
            this.q = k2.e();
        }
        if (n2 >= 3) {
            this.n = k2.p();
        }
        if (n2 >= 4) {
            this.o = k2.g();
            this.p = k2.e();
        }
        if (n2 >= 5) {
            this.r = k2.g();
            this.s = k2.g();
        }
        super.a(k2);
    }

    public n(a a2) {
        super(a2);
    }

    public void c() {
        for (am am2 : am.bE) {
            if (am2.bV || am2.bX != this.R || this.l <= this.F.size() || !(am2 instanceof com.corrodinggames.rts.game.units.y)) continue;
            com.corrodinggames.rts.game.units.y y2 = (com.corrodinggames.rts.game.units.y)am2;
            if (y2.bN || y2.aB != null || !this.R.g(y2) || !this.R.i(y2)) continue;
            this.a(y2);
        }
    }

    public boolean d() {
        return this.m != null;
    }

    @Override
    public void c(float f2) {
        Object object;
        Object object22;
        Object object32;
        Object object42;
        Object object5;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.h += f2;
        this.n();
        if (this.l <= this.F.size()) {
            // empty if block
        }
        this.i = com.corrodinggames.rts.gameFramework.f.a(this.i, f2);
        this.j = com.corrodinggames.rts.gameFramework.f.a(this.j, f2);
        this.k = com.corrodinggames.rts.gameFramework.f.a(this.k, f2);
        if (!this.d() && !this.q && this.l > this.F.size() && this.i == 0.0f) {
            this.i = 300.0f;
            this.c();
        }
        if (!this.d() && this.F.size() != 0) {
            if (!this.d()) {
                this.f = com.corrodinggames.rts.gameFramework.f.a(this.f, f2);
                if (this.f == 0.0f) {
                    this.f = 4000.0f;
                    if (this.d != null) {
                        object5 = this.d.w();
                        this.S = ((PointF)object5).a;
                        this.T = ((PointF)object5).b;
                    }
                }
            }
            if (this.j == 0.0f) {
                this.j = 400.0f;
                object5 = l2.cf.a(this.R);
                for (Object object42 : this.F) {
                    if (this.c((am)object42) > 28900.0f && !((com.corrodinggames.rts.game.units.y)object42).aw()) {
                        ((e)object5).a((com.corrodinggames.rts.game.units.y)object42);
                        continue;
                    }
                    object32 = (ak)object42;
                    if (object32.bB() == 0) continue;
                    object22 = ((am)object42).cp();
                    object = l2.cf.a(this.R);
                    ((e)object).a((com.corrodinggames.rts.game.units.y)object42);
                    ((e)object).a((c)object22);
                }
                ((e)object5).a(this.S, this.T);
            }
            if (this.m == null) {
                this.g = com.corrodinggames.rts.gameFramework.f.a(this.g, f2);
                if (this.g == 0.0f) {
                    this.g = 100.0f;
                    if (com.corrodinggames.rts.gameFramework.f.a(0, 100) < 80) {
                        this.a(f2, true);
                    }
                    if (this.m == null) {
                        this.a(f2, false);
                    }
                }
            }
        }
        if (this.m != null && this.m.V) {
            this.m = null;
        }
        if (!this.q) {
            if (this.m != null) {
                Iterator iterator;
                object5 = this.m.G;
                if (this.n != null && (this.n.bV || this.n.cN != null || this.n.cO != null)) {
                    ((ArrayList)object5).remove(this.n);
                    this.n = null;
                }
                if (this.n == null) {
                    iterator = ((ArrayList)object5).iterator();
                    block1: while (iterator.hasNext()) {
                        object42 = (com.corrodinggames.rts.game.units.y)iterator.next();
                        if (((com.corrodinggames.rts.game.units.y)object42).cN != null) continue;
                        for (Object object22 : this.F) {
                            if (!((am)object22).d((am)object42, false)) continue;
                            this.n = object42;
                            continue block1;
                        }
                    }
                    if (this.n == null) {
                        this.q = true;
                        this.j = 0.0f;
                        this.k = 0.0f;
                        this.r = this.m.S;
                        this.s = this.m.T;
                    }
                }
                if (this.n != null) {
                    if (this.j == 0.0f) {
                        this.j = 400.0f;
                        iterator = l2.cf.a(this.R);
                        for (Object object32 : this.F) {
                            ((e)((Object)iterator)).a((com.corrodinggames.rts.game.units.y)object32);
                        }
                        ((e)((Object)iterator)).a(this.n.eo, this.n.ep);
                    }
                    if (this.k == 0.0f) {
                        this.k = 80.0f;
                        iterator = ((ArrayList)object5).iterator();
                        block4: while (iterator.hasNext()) {
                            object42 = (com.corrodinggames.rts.game.units.y)iterator.next();
                            for (Object object22 : this.F) {
                                float f3;
                                if (!((am)object22).d((am)object42, false) || !((f3 = com.corrodinggames.rts.gameFramework.f.a(((com.corrodinggames.rts.game.units.y)object22).eo, ((com.corrodinggames.rts.game.units.y)object22).ep, ((com.corrodinggames.rts.game.units.y)object42).eo, ((com.corrodinggames.rts.game.units.y)object42).ep)) < 14400.0f)) continue;
                                e e2 = l2.cf.a(this.R);
                                e2.a((com.corrodinggames.rts.game.units.y)object42);
                                e2.e((am)object22);
                                continue block4;
                            }
                        }
                        boolean bl = false;
                        for (Object object32 : this.F) {
                            if (!((am)object32).d(this.n, false)) continue;
                            bl = true;
                        }
                        if (!bl) {
                            this.n = null;
                        }
                    }
                }
            }
        } else if (this.m == null) {
            this.e();
        } else {
            if (this.j == 0.0f) {
                this.j = 400.0f;
                float f4 = this.m.S + com.corrodinggames.rts.gameFramework.f.c(-40.0f, 40.0f);
                float f5 = this.m.T + com.corrodinggames.rts.gameFramework.f.c(-40.0f, 40.0f);
                if (this.o > 600.0f) {
                    f4 += com.corrodinggames.rts.gameFramework.f.c(-300.0f, 300.0f);
                    f5 += com.corrodinggames.rts.gameFramework.f.c(-300.0f, 300.0f);
                }
                if (this.o > 1200.0f) {
                    f4 += com.corrodinggames.rts.gameFramework.f.c(-300.0f, 300.0f);
                    f5 += com.corrodinggames.rts.gameFramework.f.c(-300.0f, 300.0f);
                }
                if (y.a(f4, f5, ao.b)) {
                    f4 += com.corrodinggames.rts.gameFramework.f.c(-100.0f, 100.0f);
                    f5 += com.corrodinggames.rts.gameFramework.f.c(-100.0f, 100.0f);
                }
                if (y.a(f4, f5, ao.b)) {
                    f4 += com.corrodinggames.rts.gameFramework.f.c(-200.0f, 200.0f);
                    f5 += com.corrodinggames.rts.gameFramework.f.c(-200.0f, 200.0f);
                }
                if (y.a(f4, f5, ao.b)) {
                    f4 += com.corrodinggames.rts.gameFramework.f.c(-200.0f, 200.0f);
                    f5 += com.corrodinggames.rts.gameFramework.f.c(-200.0f, 200.0f);
                }
                if (y.a(f4, f5, ao.b)) {
                    this.j = 30.0f;
                } else {
                    this.r = f4;
                    this.s = f5;
                    object42 = l2.cf.a(this.R);
                    object32 = this.F.iterator();
                    while (object32.hasNext()) {
                        object22 = (com.corrodinggames.rts.game.units.y)object32.next();
                        object = (ak)object22;
                        if (object.bB() != 0) {
                            float f6 = com.corrodinggames.rts.gameFramework.f.a(((com.corrodinggames.rts.game.units.y)object22).eo, ((com.corrodinggames.rts.game.units.y)object22).ep, this.r, this.s);
                            if (!(f6 > 1600.0f)) continue;
                            ((e)object42).a((com.corrodinggames.rts.game.units.y)object22);
                            continue;
                        }
                        e e3 = l2.cf.a(this.R);
                        e3.a((com.corrodinggames.rts.game.units.y)object22);
                        e3.a(this.S, this.T);
                    }
                    ((e)object42).a(this.r, this.s);
                }
            }
            if (this.k == 0.0f) {
                this.k = 100.0f;
                for (Iterator iterator : this.F) {
                    float f7 = com.corrodinggames.rts.gameFramework.f.a(((com.corrodinggames.rts.game.units.y)((Object)iterator)).eo, ((com.corrodinggames.rts.game.units.y)((Object)iterator)).ep, this.r, this.s);
                    if (!(f7 < 6400.0f)) continue;
                    this.p = true;
                    object32 = ((am)((Object)iterator)).cp();
                    object22 = l2.cf.a(this.R);
                    ((e)object22).a((com.corrodinggames.rts.game.units.y)((Object)iterator));
                    ((e)object22).a((c)object32);
                }
            }
            if (this.p) {
                this.m.o();
                this.o += f2;
            }
            boolean bl = false;
            for (com.corrodinggames.rts.game.units.y y2 : this.F) {
                if (y2.bV || (object32 = (ak)((Object)y2)).bB() == 0) continue;
                bl = true;
            }
            if (!bl || this.o > 1700.0f) {
                this.e();
            }
        }
        if (this.h > 1500.0f && this.F.size() == 0) {
            this.p();
        }
    }

    public void e() {
        this.q = false;
        this.m = null;
        this.o = 0.0f;
        this.j = 0.0f;
        this.k = 0.0f;
        this.p = false;
        this.f();
    }

    public void a(float f2, boolean bl) {
        for (o o2 : this.R.bn) {
            if (!(o2 instanceof h) || o2 instanceof n || bl && !(o2 instanceof l)) continue;
            h h2 = (h)o2;
            if (h2.G.size() == 0 || h2.m()) continue;
            this.m = h2;
            this.n = null;
            return;
        }
    }

    public i a(boolean bl) {
        i i2 = null;
        for (o o2 : this.R.bn) {
            if (!(o2 instanceof i)) continue;
            i i3 = (i)o2;
            if (i3.s && bl || i3.b != com.corrodinggames.rts.game.a.j.c) continue;
            i2 = i3;
            if (com.corrodinggames.rts.gameFramework.f.c(3) != 0) continue;
            return i2;
        }
        return i2;
    }

    public void f() {
        boolean bl = true;
        PointF pointF = null;
        if (bl) {
            this.d = this.a(true);
            if (this.d == null) {
                this.d = this.a(false);
            }
            if (this.d != null) {
                pointF = this.d.w();
            }
        }
        if (pointF == null) {
            pointF = this.R.am();
            this.d = null;
        }
        this.S = pointF.a;
        this.T = pointF.b;
    }
}
