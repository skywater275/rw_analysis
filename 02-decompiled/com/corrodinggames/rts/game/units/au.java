/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.c;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.av;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.ab;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.w;

public strictfp final class au {
    av a;
    as b;
    c c;
    int d;
    float e = 1.0f;
    float f = 1.0f;
    long g = -1L;
    am h;
    public ab i;
    public boolean j;
    public float k = -1.0f;
    public float l = -1.0f;
    public boolean m;
    public boolean n;

    public boolean a(au au2) {
        return !(com.corrodinggames.rts.gameFramework.f.c(this.e - au2.e) > 3.0f) && !(com.corrodinggames.rts.gameFramework.f.c(this.f - au2.f) > 3.0f);
    }

    public boolean b(au au2) {
        if (au2 == null) {
            return false;
        }
        if (this.a != au2.a) {
            return false;
        }
        if (this.b != au2.b) {
            return false;
        }
        if (com.corrodinggames.rts.gameFramework.f.c(this.e - au2.e) > 1.0f || com.corrodinggames.rts.gameFramework.f.c(this.f - au2.f) > 1.0f) {
            return false;
        }
        if (this.d != au2.d) {
            return false;
        }
        return this.h == au2.h;
    }

    public as a() {
        return this.b;
    }

    public int b() {
        return this.d;
    }

    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.a);
        as2.a(this.b);
        as2.a(this.e);
        as2.a(this.f);
        if (this.g != -1L) {
            as2.a(this.g);
        } else {
            as2.a(this.h);
        }
        as2.c(this.d);
        as2.a(this.k);
        as2.a(this.l);
        as2.a(this.m);
        as2.a(this.j);
        as2.a(this.n);
        com.corrodinggames.rts.game.units.a.c.a(as2, this.c);
    }

    public void a(k k2) {
        this.a = (av)k2.b(av.class);
        this.b = k2.q();
        this.e = k2.g();
        this.f = k2.g();
        this.g = k2.n();
        this.h = null;
        if (k2.b() >= 40) {
            this.d = k2.d();
        }
        if (k2.b() >= 46) {
            this.k = k2.g();
            this.l = k2.g();
        }
        if (k2.b() >= 58) {
            this.m = k2.e();
        }
        if (k2.b() >= 65) {
            this.j = k2.e();
        }
        if (k2.b() >= 79) {
            this.n = k2.e();
        }
        if (k2.b() >= 82) {
            this.c = com.corrodinggames.rts.game.units.a.c.a(k2);
        }
    }

    public void c() {
        if (this.g != -1L) {
            this.h = w.a(this.g, true);
            if (this.h == null) {
                com.corrodinggames.rts.gameFramework.l.b("convertUnitIds failed");
                if (this.a != null) {
                    com.corrodinggames.rts.gameFramework.l.b("convertUnitIds: type:" + this.a.toString());
                }
                if (this.b != null) {
                    com.corrodinggames.rts.gameFramework.l.b("convertUnitIds: build:" + this.b.toString());
                }
                com.corrodinggames.rts.gameFramework.l.b("convertUnitIds: x:" + this.e + ", y:" + this.f);
            }
            this.g = -1L;
        }
    }

    public av d() {
        return this.a;
    }

    public void e() {
        this.a = av.a;
        this.b = null;
        this.d = 1;
        this.e = 2.0f;
        this.f = 2.0f;
        this.g = -1L;
        this.h = null;
        this.i = null;
        this.k = -1.0f;
        this.l = -1.0f;
        this.m = false;
        this.j = false;
        this.n = false;
        this.c = null;
    }

    public boolean f() {
        return this.a == av.b || this.a == av.d || this.a == av.g || this.a == av.e || this.a == av.i || this.a == av.k || this.a == av.m || this.a == av.n;
    }

    public float g() {
        if (this.f() && this.h != null) {
            return this.h.eo;
        }
        return this.e;
    }

    public float h() {
        if (this.f() && this.h != null) {
            return this.h.ep;
        }
        return this.f;
    }

    public am i() {
        return this.h;
    }

    public void a(float f2, float f3) {
        this.e();
        this.a = av.a;
        this.e = f2;
        this.f = f3;
    }

    public void b(float f2, float f3) {
        this.e();
        this.a = av.h;
        this.e = f2;
        this.f = f3;
    }

    public void a(am am2) {
        this.e();
        this.a = av.b;
        this.h = am2;
    }

    public void a(float f2, float f3, as as2, int n2) {
        this.e();
        this.a = av.c;
        this.e = f2;
        this.f = f3;
        this.b = as2;
        this.d = n2 = (int)((byte)n2);
    }

    public void b(am am2) {
        this.e();
        this.a = av.d;
        this.h = am2;
    }

    public void c(am am2) {
        this.e();
        this.a = av.k;
        this.h = am2;
    }

    public void d(am am2) {
        this.e();
        this.a = av.m;
        this.h = am2;
    }

    public void e(am am2) {
        this.e();
        this.a = av.n;
        this.h = am2;
    }

    public void c(float f2, float f3) {
        this.e();
        this.a = av.j;
        this.e = f2;
        this.f = f3;
    }

    public void f(am am2) {
        this.e();
        this.a = av.g;
        this.h = am2;
    }

    public void g(am am2) {
        this.e();
        this.a = av.e;
        this.h = am2;
    }

    public void h(am am2) {
        this.e();
        this.a = av.i;
        this.h = am2;
    }

    public void c(au au2) {
        this.e();
        this.a = au2.a;
        this.b = au2.b;
        this.e = au2.e;
        this.f = au2.f;
        this.h = au2.h;
        this.i = au2.i;
        this.d = au2.d;
        this.j = au2.j;
        this.c = au2.c;
    }

    public long j() {
        long l2 = 0L;
        if (this.a != null) {
            l2 += (long)this.a.ordinal();
        }
        return l2;
    }

    public void k() {
        if (this.h != null) {
            this.g = this.h.eh;
            this.h = null;
        }
        this.i = null;
    }

    public am l() {
        if (this.f()) {
            am am2 = this.i();
            return am2;
        }
        y y2 = com.corrodinggames.rts.game.n.i.t;
        y2.cg = 0.0f;
        y2.eo = this.e;
        y2.ep = this.f;
        y2.eq = 0.0f;
        return y2;
    }
}
