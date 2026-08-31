/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f.a;

import android.graphics.PointF;
import android.graphics.RectF;
import com.corrodinggames.rts.gameFramework.f.a.c;
import com.corrodinggames.rts.gameFramework.f.a.h;
import com.corrodinggames.rts.gameFramework.f.a.k;
import com.corrodinggames.rts.gameFramework.f.a.m;
import com.corrodinggames.rts.gameFramework.m.y;

public abstract class l {
    static final PointF e = new PointF();
    static final RectF f = new RectF();
    float g;
    float h;
    float i = 50.0f;
    float j = 50.0f;
    float k;
    float l;
    float m;
    float n;
    float o;
    float p;
    float q;
    float r;
    boolean s = false;
    boolean t = false;
    boolean u;
    l v;
    com.corrodinggames.rts.gameFramework.utility.m w = new com.corrodinggames.rts.gameFramework.utility.m();
    m x = com.corrodinggames.rts.gameFramework.f.a.m.b;
    float y;
    float z;
    static final PointF A = new PointF();
    k B;

    public String a() {
        return this.getClass().getSimpleName();
    }

    public y d() {
        return com.corrodinggames.rts.gameFramework.l.B().bO;
    }

    public RectF a(RectF rectF, float f2, float f3) {
        rectF.a = 0.0f + f2;
        rectF.b = 0.0f + f3;
        rectF.c = 0.0f + this.i + f2;
        rectF.d = 0.0f + this.j + f3;
        return rectF;
    }

    public RectF a(RectF rectF) {
        com.corrodinggames.rts.gameFramework.f.a.l.A.a = this.g;
        com.corrodinggames.rts.gameFramework.f.a.l.A.b = this.h;
        if (this.v != null) {
            this.v.a(A);
        }
        rectF.a = 0.0f + com.corrodinggames.rts.gameFramework.f.a.l.A.a;
        rectF.b = 0.0f + com.corrodinggames.rts.gameFramework.f.a.l.A.b;
        rectF.c = 0.0f + this.i + com.corrodinggames.rts.gameFramework.f.a.l.A.a;
        rectF.d = 0.0f + this.j + com.corrodinggames.rts.gameFramework.f.a.l.A.b;
        return rectF;
    }

    public void b() {
        for (l l2 : this.w) {
            l2.b();
        }
        this.y = 0.0f;
        this.z = 0.0f;
        if (this.x != com.corrodinggames.rts.gameFramework.f.a.m.a) {
            if (this.x == com.corrodinggames.rts.gameFramework.f.a.m.b) {
                float f2 = 0.0f;
                float f3 = 0.0f;
                for (l l3 : this.w) {
                    if (l3.i > f2) {
                        f2 = l3.g();
                    }
                    f3 += l3.h();
                }
                this.y = f3;
                this.z = f2;
                com.corrodinggames.rts.gameFramework.f.a.l.b(this.z * 0.5f, this.y * 0.5f, this.w);
            } else if (this.x == com.corrodinggames.rts.gameFramework.f.a.m.c) {
                float f4 = 0.0f;
                float f5 = 0.0f;
                for (l l4 : this.w) {
                    if (l4.j > f4) {
                        f4 = l4.h();
                    }
                    f5 += l4.g();
                }
                this.y = f4;
                this.z = f5;
                com.corrodinggames.rts.gameFramework.f.a.l.a(this.z * 0.5f, this.y * 0.5f, this.w);
            } else {
                throw new RuntimeException("Unknown layout style:" + (Object)((Object)this.x));
            }
        }
        this.s = false;
    }

    public static void a(float f2, float f3, com.corrodinggames.rts.gameFramework.utility.m m2) {
        float f4 = f2;
        float f5 = f3;
        float f6 = 0.0f;
        for (l l2 : m2) {
            f6 += l2.g();
        }
        f4 = f2 - f6 * 0.5f;
        for (l l2 : m2) {
            l2.g = f4 += l2.q;
            f4 += l2.i;
            f4 += l2.r;
            l2.d(f5);
        }
    }

    public static void b(float f2, float f3, com.corrodinggames.rts.gameFramework.utility.m m2) {
        float f4 = f2;
        float f5 = f3;
        float f6 = 0.0f;
        for (l l2 : m2) {
            f6 += l2.h();
        }
        f5 = f3 - f6 * 0.5f;
        for (l l2 : m2) {
            l2.h = f5 += l2.o;
            f5 += l2.j;
            f5 += l2.p;
            l2.c(f4);
        }
    }

    public void a(PointF pointF) {
        if (this.v != null) {
            this.v.a(pointF);
        }
        pointF.a += this.g;
        pointF.b += this.h;
    }

    public void a(l l2) {
        l2.b(this);
    }

    public void b(l l2) {
        this.a(l2, false);
    }

    public void a(l l2, boolean bl) {
        if (this.v == l2) {
            return;
        }
        if (this.v != null) {
            this.v.w.remove(this);
        }
        this.v = l2;
        if (l2 != null) {
            if (!bl) {
                l2.w.add(this);
            } else {
                l2.w.add(0, this);
            }
        }
        this.e();
    }

    public void e() {
        this.s = true;
        if (this.v != null) {
            this.v.e();
        }
    }

    public void b(float f2) {
        if (this.w.size() > 0) {
            for (l l2 : this.w) {
                l2.b(f2);
            }
        }
    }

    public void f() {
        com.corrodinggames.rts.gameFramework.f.a.l.A.a = this.g;
        com.corrodinggames.rts.gameFramework.f.a.l.A.b = this.h;
        if (this.v != null) {
            this.v.a(A);
        }
        this.a(com.corrodinggames.rts.gameFramework.f.a.l.A.a, com.corrodinggames.rts.gameFramework.f.a.l.A.b);
        if (this.w.size() > 0) {
            for (l l2 : this.w) {
                l2.f();
            }
        }
    }

    public void a(float f2, float f3) {
        if (this.t) {
            y y2 = this.d();
            RectF rectF = this.a(new RectF(), f2, f3);
            com.corrodinggames.rts.gameFramework.f.a.h.m.a(y2, rectF);
        }
    }

    public void a(k k2) {
        this.B = k2;
    }

    public boolean a(c c2) {
        if (c2.a() && this.c(c2)) {
            com.corrodinggames.rts.gameFramework.l.e("UI click " + this.a());
            if (this.B != null) {
                return this.B.a(c2);
            }
            return false;
        }
        if (c2.b()) {
            this.u = this.c(c2);
            return false;
        }
        return false;
    }

    public boolean b(c c2) {
        if (this.w.size() > 0) {
            for (l l2 : this.w) {
                if (!l2.b(c2)) continue;
                return true;
            }
        }
        return this.a(c2);
    }

    public boolean c(c c2) {
        this.a(f);
        return f.b(c2.a, c2.b);
    }

    public void c(float f2) {
        this.g = f2 - this.i * 0.5f;
    }

    public void d(float f2) {
        this.h = f2 - this.j * 0.5f;
    }

    public void e(float f2) {
        this.o = f2;
        this.p = f2;
        this.q = f2;
        this.r = f2;
    }

    public void f(float f2) {
        this.k = f2;
        this.l = f2;
        this.m = f2;
        this.n = f2;
    }

    public float g() {
        return this.q + this.i + this.r;
    }

    public float h() {
        return this.o + this.j + this.p;
    }

    public void i() {
        this.b((l)null);
    }
}
