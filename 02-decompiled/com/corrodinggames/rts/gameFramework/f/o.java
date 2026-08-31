/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Point;
import android.graphics.Rect;
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.d.a;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.f.g;
import com.corrodinggames.rts.gameFramework.f.o$1;
import com.corrodinggames.rts.gameFramework.f.p;
import com.corrodinggames.rts.gameFramework.f.q;
import com.corrodinggames.rts.gameFramework.f.r;
import com.corrodinggames.rts.gameFramework.f.s;
import com.corrodinggames.rts.gameFramework.f.t;
import com.corrodinggames.rts.gameFramework.f.u;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.m.m;
import com.corrodinggames.rts.gameFramework.m.y;
import java.util.ArrayList;
import java.util.Iterator;

public class o {
    float a;
    float b;
    public float c = 120.0f;
    public float d = 120.0f;
    public boolean e;
    public boolean f;
    public int g;
    public int h;
    public float i;
    public float j;
    int k;
    int l;
    boolean m;
    final Paint n = new Paint();
    final Paint o = new Paint();
    final Paint p = new Paint();
    float q = 0.0f;
    float r = 0.0f;
    final Paint s = new ag();
    final Paint t = new Paint();
    final Paint u = new Paint();
    final Paint v = new Paint();
    public final Rect w = new Rect();
    final Paint x = new ag();
    final Paint y = new ag();
    final Paint z = new ag();
    final Paint A = new ag();
    final Paint B = new ag();
    final Paint C = new ag();
    final Paint D = new ag();
    final Rect E = new Rect();
    e F;
    y G;
    e H;
    y I;
    public e J;
    y K;
    float L = 0.0f;
    float M;
    float N;
    public boolean O = false;
    public boolean P = false;
    public float Q = 0.0f;
    int R = 30;
    int S = -1;
    public e T;
    public e U;
    final Rect V = new Rect();
    ag[] W;
    ag X;
    float Y;
    public final ArrayList Z = new ArrayList();
    public final ArrayList aa = new ArrayList();
    private final ArrayList ag = new ArrayList();
    Rect ab = new Rect();
    static ArrayList ac = new ArrayList();
    Point ad = new Point();
    m ae = new o$1(this);
    ArrayList af = new ArrayList();

    public void a(int n2, int n3, float f2, am am2) {
        boolean bl = am2 != null && am2.bI();
        for (q q2 : this.Z) {
            if (q2.a != bl || com.corrodinggames.rts.gameFramework.f.d(n2 - q2.b) >= 40 || com.corrodinggames.rts.gameFramework.f.d(n3 - q2.c) >= 40) continue;
            q2.d += f2;
            return;
        }
        q q3 = new q(this, f2, n2, n3, bl);
        this.Z.add(q3);
    }

    public void a(Context context) {
        this.o.a(Paint$Style.b);
        this.o.a(1.0f);
        this.s.a(255, 255, 255, 255);
        this.s.a(Paint$Style.b);
        this.s.a(1.0f);
        this.W = new ag[11];
        for (int i = 0; i <= 10; ++i) {
            this.W[i] = new ag();
            this.W[i].b(-16777216);
            this.W[i].a(Paint$Style.a);
            this.W[i].c(i * 25);
        }
        this.X = new ag();
        this.X.b(-16777216);
        this.X.a(Paint$Style.a);
        this.t.a(255, 255, 0, 0);
        this.t.a(Paint$Style.b);
        this.t.a(2.0f);
        this.u.a(155, 255, 0, 0);
        this.u.a(Paint$Style.b);
        this.u.a(2.0f);
        this.v.a(200, 12, 227, 219);
        this.v.a(Paint$Style.b);
        this.v.a(2.0f);
        this.x.b(-16711936);
        this.y.b(-256);
        this.z.b(-65536);
        this.A.b(com.corrodinggames.rts.gameFramework.f.o.a(this.x.e()));
        this.B.b(com.corrodinggames.rts.gameFramework.f.o.a(this.y.e()));
        this.C.b(com.corrodinggames.rts.gameFramework.f.o.a(this.z.e()));
        this.D.a(210, 255, 255, 255);
    }

    public static int a(int n2) {
        int n3 = Color.a(Color.a(n2), (int)((float)Color.b(n2) * 0.5f), (int)((float)Color.c(n2) * 0.5f), (int)((float)Color.d(n2) * 0.5f));
        return n3;
    }

    public void a() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (!com.corrodinggames.rts.gameFramework.f.g.bR) {
            this.a = (int)(l2.cl - (this.c + 0.0f));
            this.b = 0.0f;
        } else {
            this.a = 0.0f;
            this.b = (int)(l2.cm - (this.d + 0.0f));
        }
    }

    public int b() {
        return (int)(this.b + this.d);
    }

    public void a(b b2, boolean bl) {
        this.af.clear();
        if (bl) {
            this.m = true;
            return;
        }
        this.g = 1;
        this.h = 1;
        this.i = 1.0f;
        this.j = 1.0f;
        this.f = false;
        this.e = false;
    }

    public void c() {
        if (this.J != null) {
            this.J.o();
            this.J = null;
        }
        if (this.F != null) {
            this.F.o();
            this.F = null;
        }
        if (this.T != null) {
            this.T.o();
            this.T = null;
        }
        if (this.U != null) {
            this.U.o();
            this.U = null;
        }
        if (this.I != null) {
            this.I.q();
            this.I = null;
        }
        if (this.H != null) {
            this.H.o();
            this.H = null;
        }
        this.K = null;
        this.e = false;
    }

    public float d() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return l2.cq;
    }

    public void e() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.f();
        com.corrodinggames.rts.gameFramework.l.e("Creating minimap image buffers..");
        if (this.F == null) {
            this.F = l2.bO.a((int)this.c, (int)this.d, false);
            this.G = l2.bO.b(this.F);
        }
        if (this.J == null) {
            this.J = l2.bO.a((int)this.c, (int)this.d, false);
            this.K = l2.bO.b(this.J);
        }
        if (this.H == null) {
            this.H = l2.bO.a((int)this.c, (int)this.d, false);
            this.I = l2.bO.b(this.H);
        }
    }

    public void f() {
        this.d = this.c = this.d();
        this.a();
    }

    public void g() {
        boolean bl = true;
        long l2 = br.a();
        com.corrodinggames.rts.gameFramework.l.e("--setting up minimap--");
        l l3 = com.corrodinggames.rts.gameFramework.l.B();
        this.f();
        this.g = l3.bL.C * l3.bL.n;
        this.h = l3.bL.D * l3.bL.o;
        if (this.g <= 0) {
            this.g = 1;
        }
        if (this.h <= 0) {
            this.h = 1;
        }
        this.i = 1.0f / (float)this.g;
        this.j = 1.0f / (float)this.h;
        this.f = true;
        this.e();
        this.Z.clear();
        this.aa.clear();
        this.ag.clear();
        for (Point point : l3.bL.A) {
            this.ag.add(new t(this, point.a, point.b));
        }
        this.G.b(-16777216);
        this.K.b(-16777216);
        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = false;
        if (!bl) {
            Rect rect = new Rect(0, 0, (int)this.c, (int)this.d);
            l3.bL.u.a(this.G, 0.0f, 0.0f, 0.0f, 0.0f, this.g, this.h, this.c / (float)this.g, this.d / (float)this.h, false, false, false);
            Paint paint = new Paint();
            paint.a(50, 0, 0, 0);
            this.G.b(rect, paint);
        } else {
            int n2 = 2;
            for (int i2 = 0; i2 < n2; ++i2) {
                for (int i3 = 0; i3 < n2; ++i3) {
                    this.I.b(-16777216);
                    int n3 = (int)this.c / n2;
                    int n4 = (int)this.d / n2;
                    int n5 = this.g / n2;
                    int n6 = this.h / n2;
                    l3.bL.u.a(this.I, n5 * i2, n6 * i3, n5 * i2, n6 * i3, n5, n6, this.c / (float)n5, this.d / (float)n6, false, false, false);
                    Rect rect = new Rect(0, 0, (int)this.c, (int)this.d);
                    Rect rect2 = new Rect(n3 * i2, n4 * i3, n3 * (i2 + 1), n4 * (i3 + 1));
                    Paint paint = new Paint();
                    paint.a(true);
                    paint.d(true);
                    paint.b(true);
                    this.K.a(this.H, rect, rect2, paint);
                }
            }
            Rect rect = new Rect(0, 0, (int)this.c, (int)this.d);
            this.G.b(-16777216);
            Paint paint = new Paint();
            paint.a(true);
            paint.d(true);
            paint.b(true);
            paint.a(200, 255, 255, 255);
            this.G.a(this.J, rect, rect, paint);
        }
        this.I.b(-16777216);
        this.K.b(-16777216);
        this.M = 50.0f;
        this.a(0.0f, 1.0f);
        this.e = true;
        double d2 = br.a(l2);
        com.corrodinggames.rts.gameFramework.l.e("Minimap map render took:" + br.a(d2));
    }

    void a(float f2, float f3) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.ab.a(0, (int)(f2 * this.d), (int)this.c, (int)(f3 * this.d));
        this.I.a(this.F, this.ab, this.ab, null);
        b b2 = l2.bL;
        if (b2.E) {
            float f4;
            boolean bl = b2.G;
            ag ag2 = this.W[5];
            ag ag3 = this.W[10];
            ag ag4 = this.X;
            ag4.c(255);
            if (bl) {
                ag3 = this.W[7];
                f4 = 1.0f - (1.0f - (float)ag2.f() / 255.0f) * (1.0f - (float)ag3.f() / 255.0f);
                ag4.c((int)(f4 * 255.0f));
            }
            f4 = this.c / (float)b2.C;
            float f5 = this.d / (float)b2.D;
            int n2 = 0;
            int n3 = 0;
            int n4 = (int)(f2 * (float)b2.D) - 1;
            int n5 = (int)(f3 * (float)b2.D) + 1;
            if (n4 < 0) {
                n4 = 0;
            }
            if (n5 < 0) {
                n5 = 0;
            }
            if (n4 > l2.bL.D) {
                n4 = b2.D;
            }
            if (n5 > l2.bL.D) {
                n5 = b2.D;
            }
            int n6 = 0;
            byte[][] byArray = l2.bs.N;
            if (byArray != null) {
                int n7 = b2.C;
                Rect rect = this.V;
                for (int i2 = n4; i2 < n5; ++i2) {
                    for (int i3 = 0; i3 < n7; ++i3) {
                        int n8;
                        byte by = byArray[i3][i2];
                        if (by == 0) continue;
                        int n9 = i3;
                        for (n8 = i3; n8 < n7 - 1 && byArray[n8][i2] == by; ++n8) {
                        }
                        i3 = n8;
                        rect.a(n2 + (int)((float)n9 * f4), n3 + (int)((float)i2 * f5), n2 + (int)((float)(n8 + 1) * f4), n3 + (int)((float)(i2 + 1) * f5));
                        ag ag5 = by == 10 ? ag4 : ag2;
                        this.I.b(rect, (Paint)ag5);
                        if (++n6 <= 2) continue;
                        n6 = 0;
                    }
                }
            }
        }
        this.K.a(this.H, this.ab, this.ab, null);
        this.J.p();
        if (com.corrodinggames.rts.gameFramework.l.aY) {
            // empty if block
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static s a(int n2, Paint paint) {
        ArrayList arrayList = ac;
        synchronized (arrayList) {
            s s2 = null;
            for (s s3 : ac) {
                if (s3.d < n2 || s2 != null && s3.d >= s2.d) continue;
                s2 = s3;
            }
            if (s2 != null) {
                ac.remove(s2);
                s2.c = paint;
                return s2;
            }
        }
        return new s(n2 + 15, paint);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static void a(s s2) {
        s2.c = null;
        s2.b = 0;
        ArrayList arrayList = ac;
        synchronized (arrayList) {
            if (ac.size() < 20) {
                ac.add(s2);
                return;
            }
            Iterator iterator = ac.iterator();
            while (iterator.hasNext()) {
                s s3 = (s)iterator.next();
                if (s3.d >= s2.d) continue;
                iterator.remove();
                ac.add(s2);
                return;
            }
        }
    }

    void a(y y2, int n2, int n3, float f2, float f3) {
        int n4;
        int n5;
        int n6;
        int n7;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        int n8 = 1;
        if (this.c < 50.0f) {
            n7 = 0;
            n6 = 0;
            n5 = 1;
            n4 = 1;
            n8 = 1;
        } else if (this.c < 120.0f) {
            n7 = 0;
            n6 = 0;
            n5 = 2;
            n4 = 2;
            n8 = 2;
        } else {
            n7 = -1;
            n6 = -1;
            n5 = 2;
            n4 = 2;
            n8 = 3;
        }
        n6 += n2;
        n5 += n2;
        n4 += n3;
        n7 += n3;
        boolean bl = false;
        if (l2.bs.b() || l2.cb.j()) {
            bl = true;
        }
        for (int i2 = -1; i2 < com.corrodinggames.rts.game.n.c; ++i2) {
            int n9;
            int n10;
            Object object;
            n n11 = com.corrodinggames.rts.game.n.k(i2);
            if (n11 == null) continue;
            Paint paint = n11.ae;
            if (l2.bQ.useMinimapAllyColors) {
                if (bl) {
                    this.p.b(com.corrodinggames.rts.game.n.i(n11.r));
                    paint = this.p;
                } else if (l2.bs == n11) {
                    paint = this.x;
                } else if (l2.bs.d(n11)) {
                    paint = this.y;
                } else if (l2.bs.c(n11)) {
                    paint = this.z;
                }
            }
            int n12 = 0;
            if (n11.a(true, false) > 0) {
                object = am.bE.a();
                n10 = am.bE.size();
                for (n9 = 0; n9 < n10; ++n9) {
                    am am2 = object[n9];
                    if (am2.bX != n11 || !am2.cR) continue;
                    ++n12;
                }
            }
            if (n12 > 0) {
                paint.a((float)n8);
                object = com.corrodinggames.rts.gameFramework.f.o.a(n12, paint);
                ((s)object).e = !l2.bQ.renderWithLineWidth;
                am[] amArray = am.bE.a();
                int n13 = am.bE.size();
                for (n10 = 0; n10 < n13; ++n10) {
                    am am3 = amArray[n10];
                    if (am3.bX != n11 || !am3.cR) continue;
                    ((s)object).a(am3.cS, am3.cT);
                }
                if (((s)object).b != 0) {
                    l2.bO.a((m)object);
                }
            }
            object = n11.af;
            if (l2.bQ.useMinimapAllyColors) {
                if (bl) {
                    this.p.b(com.corrodinggames.rts.game.n.i(n11.r));
                    paint = this.p;
                } else if (l2.bs == n11) {
                    object = this.A;
                } else if (l2.bs.d(n11)) {
                    object = this.B;
                } else if (l2.bs.c(n11)) {
                    object = this.C;
                }
            }
            n9 = 0;
            Object object2 = com.corrodinggames.rts.gameFramework.d.a.w.b();
            int n14 = com.corrodinggames.rts.gameFramework.d.a.w.size();
            for (int i3 = 0; i3 < n14; ++i3) {
                a a2 = (a)object2[i3];
                if (a2.e != n11 || !a2.k) continue;
                ++n9;
            }
            if (n9 <= 0) continue;
            ((Paint)object).a((float)n8);
            object2 = com.corrodinggames.rts.gameFramework.f.o.a(n9, (Paint)object);
            Object[] objectArray = com.corrodinggames.rts.gameFramework.d.a.w.b();
            int n15 = com.corrodinggames.rts.gameFramework.d.a.w.size();
            for (n14 = 0; n14 < n15; ++n14) {
                a a3 = (a)objectArray[n14];
                if (a3.e != n11 || !a3.k) continue;
                ((s)object2).a(a3.l, a3.m);
            }
            if (((s)object2).b == 0) continue;
            l2.bO.a((m)object2);
        }
    }

    public void a(int n2, int n3, r r2) {
        p p2 = new p(this);
        p2.a = n2;
        p2.b = n3;
        p2.e = r2;
        p2.c = 0.9f;
        p2.d = 0.9f;
        this.aa.add(p2);
    }

    public void h() {
        Point point;
        Object object2;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.m = false;
        this.k = (int)this.a;
        this.l = (int)this.b;
        am[] amArray = am.bE.a();
        int n2 = am.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            am am2 = amArray[i2];
            if (!am2.bV && am2.cN == null && am2.cf() && am2.c_() && !am2.u()) {
                object2 = this.b(am2.eo, am2.ep);
                am2.cS = ((Point)object2).a;
                am2.cT = ((Point)object2).b;
                am2.cR = true;
                continue;
            }
            am2.cR = false;
        }
        Object[] objectArray = com.corrodinggames.rts.gameFramework.d.a.w.b();
        int n3 = com.corrodinggames.rts.gameFramework.d.a.w.size();
        for (n2 = 0; n2 < n3; ++n2) {
            object2 = (a)objectArray[n2];
            if (((a)object2).n || !((a)object2).u) continue;
            point = this.b(((a)object2).g, ((a)object2).h);
            ((a)object2).l = point.a;
            ((a)object2).m = point.b;
            ((a)object2).k = true;
        }
        n n4 = l2.bs;
        for (Object object2 : this.ag) {
            ((t)object2).e = false;
            if (!l2.bL.a(n4, ((t)object2).a, ((t)object2).b)) continue;
            ((t)object2).e = true;
            point = this.b(((t)object2).a * l2.bL.n, ((t)object2).b * l2.bL.o);
            ((t)object2).c = point.a;
            ((t)object2).d = point.b;
        }
    }

    public void a(float f2) {
        if (com.corrodinggames.rts.gameFramework.l.aU && !com.corrodinggames.rts.gameFramework.l.aW) {
            return;
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        this.L = com.corrodinggames.rts.gameFramework.f.a(this.L, f2);
        if (this.L == 0.0f) {
            this.L = 15.0f;
            this.h();
        }
        this.Y += f2;
        if (this.Y > 15.0f) {
            Object object = null;
            for (Object object2 : this.Z) {
                if (((q)object2).e != 0.0f) {
                    ((q)object2).d = 0.0f;
                } else if (((q)object2).d > 15.0f) {
                    ((q)object2).d = 0.0f;
                    ((q)object2).e = 300.0f;
                    p p2 = new p(this);
                    p2.a = ((q)object2).b;
                    p2.b = ((q)object2).c;
                    if (((q)object2).a) {
                        p2.e = com.corrodinggames.rts.gameFramework.f.r.a;
                    } else {
                        p2.e = com.corrodinggames.rts.gameFramework.f.r.b;
                        p2.c = 0.4f;
                        p2.d = 0.8f;
                    }
                    this.aa.add(p2);
                }
                ((q)object2).d = com.corrodinggames.rts.gameFramework.f.a(((q)object2).d, 2.0f * this.Y);
                ((q)object2).e = com.corrodinggames.rts.gameFramework.f.a(((q)object2).e, this.Y);
                if (((q)object2).d != 0.0f || ((q)object2).e != 0.0f) continue;
                object = object2;
            }
            if (object != null) {
                this.Z.remove(object);
            }
            for (Object object2 : this.aa) {
                if (((p)object2).e == com.corrodinggames.rts.gameFramework.f.r.d || !l2.cQ.b(((p)object2).a, ((p)object2).b)) continue;
                ((p)object2).c = 0.0f;
                ((p)object2).d = 0.0f;
            }
            this.Y = 0.0f;
        }
    }

    public float b(float f2) {
        return f2 * this.i * this.c;
    }

    public Point b(float f2, float f3) {
        if (!this.f) {
            this.ad.a(-1, -1);
            return this.ad;
        }
        int n2 = (int)(f2 * this.i * this.c + this.a);
        int n3 = (int)(f3 * this.j * this.d + this.b);
        this.ad.a(n2, n3);
        return this.ad;
    }

    public Point c(float f2, float f3) {
        if (f2 < this.a || f3 < this.b || f2 > this.a + this.c || f3 > this.b + this.d) {
            return null;
        }
        int n2 = (int)((f2 - this.a) / this.c * (float)this.g);
        int n3 = (int)((f3 - this.b) / this.d * (float)this.h);
        this.ad.a(n2, n3);
        return this.ad;
    }

    public float c(float f2) {
        if (f2 < this.a) {
            return this.a;
        }
        if (f2 > this.a + this.c) {
            return this.a + this.c;
        }
        return f2;
    }

    public float d(float f2) {
        if (f2 < this.b) {
            return this.b;
        }
        if (f2 > this.b + this.d) {
            return this.b + this.d;
        }
        return f2;
    }

    public void e(float f2) {
        float f3;
        Point point;
        Object object;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        y y2 = l2.bO;
        this.a();
        if (this.J != null && !com.corrodinggames.rts.gameFramework.f.e(this.c, this.d(), 5.0f)) {
            com.corrodinggames.rts.gameFramework.l.b("minimap", "minimap size has changed, reseting");
            this.c();
        }
        if (!this.e || this.J == null) {
            this.g();
        }
        if (this.k != (int)this.a || this.l != (int)this.b || this.m) {
            this.h();
        }
        if (l2.bL.E) {
            if (this.O && !this.P) {
                this.M = com.corrodinggames.rts.gameFramework.f.a(this.M, 1.0f);
                if (this.M == 0.0f) {
                    this.M = 40.0f;
                    this.O = false;
                    this.Q = 0.0f;
                    this.P = true;
                }
            }
            if (this.P) {
                this.N = com.corrodinggames.rts.gameFramework.f.a(this.N, 1.0f);
                if (this.N == 0.0f) {
                    this.N = 3.0f;
                    if (this.J != null) {
                        float f4 = this.Q - 0.005f;
                        this.Q = (float)((double)this.Q + 0.04);
                        if (f4 < 0.0f) {
                            f4 = 0.0f;
                        }
                        if (this.Q >= 1.0f) {
                            this.Q = 1.0f;
                            this.P = false;
                        }
                        this.a(f4, this.Q);
                    }
                }
            }
        }
        y2.b(this.J, this.a, this.b, this.n);
        this.w.a((int)this.a, (int)this.b, (int)(this.a + this.c), (int)((double)(this.b + this.d) - 0.4));
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        Object[] objectArray = com.corrodinggames.rts.game.f.a.a();
        int n2 = com.corrodinggames.rts.game.f.a.a;
        for (int i2 = 0; i2 < n2; ++i2) {
            object = (com.corrodinggames.rts.game.f)objectArray[i2];
            if (!((com.corrodinggames.rts.game.f)object).D) continue;
            bl3 = true;
            bl2 = true;
        }
        for (p p2 : this.aa) {
            if (p2.e == com.corrodinggames.rts.gameFramework.f.r.b) continue;
            bl = true;
            if (p2.e == com.corrodinggames.rts.gameFramework.f.r.d) continue;
            bl2 = true;
        }
        if (!bl && !bl3) {
            this.o.a(255, 100, 100, 100);
            this.o.a(1.0f);
            if (com.corrodinggames.rts.gameFramework.f.g.bO) {
                this.o.a(115, 0, 0, 0);
                this.o.a(2.0f);
            }
        } else {
            this.q += 5.0f * f2;
            if (this.q > 180.0f) {
                this.q -= 180.0f;
            }
            float f5 = com.corrodinggames.rts.gameFramework.f.j(this.q);
            if (bl3) {
                this.o.a(255, 0, (int)(0.0f + f5 * 230.0f), 0);
            } else if (!bl2) {
                this.o.a(255, 12, (int)(0.0f + f5 * 220.0f), (int)(0.0f + f5 * 220.0f));
            } else {
                this.o.a(255, (int)(0.0f + f5 * 230.0f), 0, 0);
            }
            this.o.a(2.0f);
        }
        y2.b(this.w, this.o);
        for (t t2 : this.ag) {
            if (!t2.e) continue;
            this.V.a(t2.c, t2.d, t2.c + 2, t2.d + 2);
            y2.b(this.V, this.D);
        }
        this.a(y2, 0, 0, 0.0f, 1.0f);
        if (this.af.size() != 0) {
            Iterator iterator = this.af.iterator();
            while (iterator.hasNext()) {
                u u2 = (u)iterator.next();
                if (u2.a.bV) {
                    iterator.remove();
                    continue;
                }
                object = u2.a;
                point = this.b(((am)object).eo, ((am)object).ep);
                if (((am)object).a(point.a, point.b)) continue;
                y2.a((float)point.a, (float)point.b, 4.0f, ((am)object).bX.ae);
            }
        }
        n2 = com.corrodinggames.rts.game.f.a.a;
        for (int i3 = 0; i3 < n2; ++i3) {
            object = (com.corrodinggames.rts.game.f)objectArray[i3];
            if (!((com.corrodinggames.rts.game.f)object).D && (((com.corrodinggames.rts.game.f)object).q == null || !((com.corrodinggames.rts.game.f)object).q.D) || ((com.corrodinggames.rts.game.f)object).j == null) continue;
            point = this.b(((com.corrodinggames.rts.game.f)object).eo, ((com.corrodinggames.rts.game.f)object).ep);
            f3 = 2.0f;
            if (((com.corrodinggames.rts.game.f)object).D) {
                f3 = 4.0f;
            }
            y2.a((float)point.a, (float)point.b, f3, ((com.corrodinggames.rts.game.f)object).j.bX.ae);
        }
        Object object2 = this.b(l2.cw, l2.cx);
        this.E.a = ((Point)object2).a;
        this.E.b = ((Point)object2).b;
        object2 = this.b(l2.cw + l2.cA, l2.cx + l2.cB);
        this.E.c = ((Point)object2).a;
        this.E.d = ((Point)object2).b;
        if (this.E.a < this.w.a) {
            this.E.a = this.w.a;
        }
        if (this.E.c > this.w.c) {
            this.E.c = this.w.c;
        }
        if (this.E.b < this.w.b) {
            this.E.b = this.w.b;
        }
        if (this.E.d > this.w.d) {
            this.E.d = this.w.d;
        }
        y2.b(this.E, this.s);
        this.r += 6.0f * f2;
        if (this.r > 180.0f) {
            this.r -= 180.0f;
        }
        object2 = this.aa.iterator();
        while (object2.hasNext()) {
            float f6;
            Paint paint;
            p p3 = (p)object2.next();
            object = this.b(p3.a, p3.b);
            float f7 = p3.c;
            f3 = 0.05f;
            if (p3.e == com.corrodinggames.rts.gameFramework.f.r.b) {
                paint = this.u;
                f3 = 0.03f;
                f6 = com.corrodinggames.rts.gameFramework.f.j(this.r);
                paint.a((int)(50.0f + f6 * 190.0f), (int)(50.0f + f6 * 190.0f), 0, 0);
            } else if (p3.e == com.corrodinggames.rts.gameFramework.f.r.d) {
                paint = this.v;
            } else {
                paint = this.t;
                f6 = com.corrodinggames.rts.gameFramework.f.j(this.r);
                paint.a((int)(50.0f + f6 * 190.0f), (int)(50.0f + f6 * 190.0f), 0, 0);
            }
            f7 = com.corrodinggames.rts.gameFramework.f.b(f7, f3, 1.0f);
            if (p3.e == com.corrodinggames.rts.gameFramework.f.r.b) {
                f6 = this.c * f7;
                float f8 = this.d * f7;
                y2.a(this.c((float)((Point)object).a - f6), this.d((float)((Point)object).b - f8), this.c((float)((Point)object).a + f6), this.d((float)((Point)object).b + f8), paint);
                y2.a(this.c((float)((Point)object).a + f6), this.d((float)((Point)object).b - f8), this.c((float)((Point)object).a - f6), this.d((float)((Point)object).b + f8), paint);
            } else {
                y2.a(this.c((float)((Point)object).a - this.c * f7), this.d(((Point)object).b), this.c((float)((Point)object).a + this.c * f7), this.d(((Point)object).b), paint);
                y2.a(this.c(((Point)object).a), this.d((float)((Point)object).b - this.d * f7), this.c(((Point)object).a), this.d((float)((Point)object).b + this.d * f7), paint);
            }
            p3.c = com.corrodinggames.rts.gameFramework.f.a(p3.c, 0.04f * f2);
            if (p3.c != 0.0f) continue;
            p3.d = com.corrodinggames.rts.gameFramework.f.a(p3.d, 0.005f * f2);
            if (p3.d != 0.0f) continue;
            object2.remove();
        }
    }

    public void a(am am2) {
        if (this.af.contains(am2)) {
            return;
        }
        u u2 = new u(this);
        u2.a = am2;
        this.af.add(u2);
    }
}
