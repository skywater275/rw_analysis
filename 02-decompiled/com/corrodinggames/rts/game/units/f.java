/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.PointF;
import com.corrodinggames.rts.game.units.al;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.x;
import com.corrodinggames.rts.gameFramework.d.d;
import com.corrodinggames.rts.gameFramework.d.e;
import com.corrodinggames.rts.gameFramework.d.h;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.aa;

public strictfp class f
extends x {
    public float a = 2000.0f;
    public float b = 0.0f;
    public float c = 0.0f;
    public float d = 2000.0f;
    public float e;
    public float f;
    public boolean g = true;
    public float h = 1.0f;
    public boolean i;
    public float j;
    static Paint k = new Paint();
    static Paint l;
    static Paint m;
    static Paint n;
    static Paint o;
    static Paint p;
    boolean q;
    static final PointF r;

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.c(0);
        as2.a(this.a);
        as2.a(this.b);
        as2.a(this.c);
        as2.a(this.d);
        as2.a(this.e);
        as2.a(this.f);
        as2.a(this.g);
        as2.a(this.h);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        k2.d();
        this.a = k2.g();
        this.b = k2.g();
        this.c = k2.g();
        this.d = k2.g();
        this.e = k2.g();
        this.f = k2.g();
        this.g = k2.e();
        this.h = k2.g();
        super.a(k2);
        if (!this.bV) {
            com.corrodinggames.rts.gameFramework.l.B().bW.a(this);
        }
    }

    public ar b() {
        if (this.q) {
            return com.corrodinggames.rts.game.units.ar.X;
        }
        return com.corrodinggames.rts.game.units.ar.W;
    }

    public static void d_() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
    }

    public f(boolean bl) {
        super(bl);
    }

    public f f() {
        for (am am2 : com.corrodinggames.rts.game.units.am.bF()) {
            if (!(am2 instanceof f) || am2.bV || am2 == this) continue;
            f f2 = (f)am2;
            if (f2.q != this.q) continue;
            return f2;
        }
        return null;
    }

    @Override
    public void a(float f2) {
        float f3;
        float f4;
        float f5;
        Object object;
        super.a(f2);
        if (this.bV) {
            return;
        }
        if (this.g) {
            this.g = false;
            object = this.f();
            if (object != null) {
                ((f)object).e = this.eo;
                ((f)object).f = this.ep;
                ((f)object).d = this.d;
                this.ci();
            } else {
                this.e = this.eo;
                this.f = this.ep;
                if (!this.q) {
                    com.corrodinggames.rts.gameFramework.l.e("DamagingBorder created " + this.e + "," + this.f + " size:" + this.d);
                }
                com.corrodinggames.rts.gameFramework.l.B().bW.a(this);
            }
        }
        if (this.q) {
            this.a = this.d;
            this.eo = this.e;
            this.ep = this.f;
        } else if (this.a > this.d) {
            this.b += 2.5E-4f * f2;
            this.a -= this.b;
            this.i = true;
            float f6 = com.corrodinggames.rts.gameFramework.f.b(this.eo, this.ep, this.e, this.f);
            f5 = com.corrodinggames.rts.gameFramework.f.d(this.eo, this.ep, this.e, this.f);
            if (f6 > 1.0f) {
                f4 = this.b;
                if (f4 > f6 * f2) {
                    f4 = f6 * f2;
                }
                this.eo += f4 * com.corrodinggames.rts.gameFramework.f.k(f5) * f2;
                this.ep += f4 * com.corrodinggames.rts.gameFramework.f.j(f5) * f2;
            }
        } else {
            this.i = false;
            this.eo = (float)((double)this.eo + (double)(this.e - this.eo) * 0.003 * (double)f2);
            this.ep = (float)((double)this.ep + (double)(this.f - this.ep) * 0.003 * (double)f2);
        }
        if (this.a < this.d) {
            this.a = this.d;
            this.b = 0.0f;
        }
        if (this.d < 0.0f) {
            this.ci();
            return;
        }
        this.c -= f2;
        if (!this.bV && this.c <= 0.0f && !this.q) {
            this.c = 2.0f;
            float f7 = this.a * com.corrodinggames.rts.gameFramework.f.k(45.0f);
            f5 = this.eo - f7;
            f4 = this.eo + f7;
            f3 = this.ep - f7;
            float f8 = this.ep + f7;
            float f9 = this.a * this.a;
            for (am am2 : com.corrodinggames.rts.game.units.am.bF()) {
                float f10;
                if (am2.eo > f5 && am2.eo < f4 && am2.ep > f3 && am2.ep < f8 || (f10 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, am2.eo, am2.ep)) < f9 || am2.bV || am2 instanceof al || am2.u() || am2.cN != null) continue;
                float f11 = 0.5f + am2.cu * 0.002f + am2.cv * 0.001f;
                am2.a(this, f11 *= this.h, null);
            }
        }
        if (!this.q) {
            object = com.corrodinggames.rts.gameFramework.l.B();
            this.j += f2;
            if (this.j > 3.0f) {
                int n2;
                this.j = 0.0f;
                int n3 = ((l)object).cu + com.corrodinggames.rts.gameFramework.f.a(0, (int)((l)object).cA);
                f3 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, (float)n3, (float)(n2 = ((l)object).cv + com.corrodinggames.rts.gameFramework.f.a(0, (int)((l)object).cB)));
                if (f3 > (this.a + 30.0f) * (this.a + 30.0f)) {
                    ((l)object).bL.a((float)n3, (float)n2);
                    int n4 = ((l)object).bL.T;
                    int n5 = ((l)object).bL.U;
                    ((l)object).bL.a(n4, n5);
                    e e2 = ((l)object).bR.b(((l)object).bL.T + 10, ((l)object).bL.U - 10 + 10, 0.0f, com.corrodinggames.rts.gameFramework.d.d.a, true, com.corrodinggames.rts.gameFramework.d.h.a);
                    if (e2 != null) {
                        e2.aq = 19;
                        e2.Y = com.corrodinggames.rts.gameFramework.f.c(-180.0f, 180.0f);
                        e2.r = true;
                        e2.ar = 1;
                        e2.E = 0.7f;
                        e2.W = e2.V = 30.0f;
                        e2.G = 0.2f;
                        e2.F = 1.2f;
                        e2.x = Color.a(255, 173, 12, 12);
                    }
                }
            }
        }
    }

    @Override
    public int s() {
        return 0;
    }

    @Override
    public boolean t() {
        return true;
    }

    @Override
    public boolean u() {
        return true;
    }

    @Override
    public boolean a(l l2) {
        return true;
    }

    @Override
    public void a(float f2, boolean bl) {
        f f3;
        Paint paint;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        float f4 = this.eo - l2.cw;
        float f5 = this.ep - l2.cx;
        Paint paint2 = paint = this.i ? m : k;
        if (this.q) {
            paint = o;
        }
        float f6 = this.a;
        if (this.g && (f3 = this.f()) != null) {
            f6 = f3.d - 300.0f;
        }
        l2.bO.a(f4, f5, f6, paint);
    }

    @Override
    public boolean a(int n2, int n3) {
        Paint paint;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bO.i();
        l2.bO.a(l2.bW.w);
        float f2 = l2.bW.b(this.a);
        Paint paint2 = paint = this.i ? n : l;
        if (this.q) {
            paint = p;
        }
        com.corrodinggames.rts.gameFramework.m.aa.a(l2.bO, n2, n3, f2, paint);
        l2.bO.j();
        return true;
    }

    @Override
    public void a(int n2) {
        this.a = n2 * 100;
        this.d = n2 * 100;
    }

    public boolean a(float f2, float f3) {
        float f4 = this.d * this.d;
        float f5 = com.corrodinggames.rts.gameFramework.f.a(this.e, this.f, f2, f3);
        return f5 >= f4;
    }

    public PointF a(float f2, float f3, float f4) {
        if (f4 > this.d) {
            f4 = this.d;
        }
        float f5 = com.corrodinggames.rts.gameFramework.f.d(this.eo, this.ep, f2, f3);
        float f6 = this.d - f4;
        float f7 = this.eo + com.corrodinggames.rts.gameFramework.f.k(f5) * f6;
        float f8 = this.ep + com.corrodinggames.rts.gameFramework.f.j(f5) * f6;
        com.corrodinggames.rts.game.units.f.r.a = f7;
        com.corrodinggames.rts.game.units.f.r.b = f8;
        return r;
    }

    @Override
    public /* synthetic */ as r() {
        return this.b();
    }

    static {
        k.a(10.0f);
        k.b(Color.a(100, 160, 0, 0));
        k.a(Paint$Style.b);
        m = new Paint();
        m.a(k);
        m.b(Color.a(180, 160, 0, 0));
        l = new Paint();
        l.a(2.0f);
        l.b(Color.a(100, 160, 0, 0));
        l.a(Paint$Style.b);
        n = new Paint();
        n.a(l);
        n.b(Color.a(180, 160, 0, 0));
        o = new Paint();
        o.a(2.0f);
        o.b(Color.a(50, 255, 255, 255));
        o.a(Paint$Style.b);
        p = new Paint(o);
        r = new PointF();
    }
}
