/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.b;
import com.corrodinggames.rts.game.a.d;
import com.corrodinggames.rts.game.a.e;
import com.corrodinggames.rts.game.a.f;
import com.corrodinggames.rts.game.a.j;
import com.corrodinggames.rts.game.a.k;
import com.corrodinggames.rts.game.a.l;
import com.corrodinggames.rts.game.a.o;
import com.corrodinggames.rts.game.b.g;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.av;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.gameFramework.utility.m;
import com.corrodinggames.rts.gameFramework.utility.u;
import com.corrodinggames.rts.gameFramework.utility.y;
import java.util.ArrayList;

public class i
extends o {
    float a;
    j b;
    k c;
    float d = -1.0f;
    float e;
    float f;
    float g = 100.0f;
    int h;
    float i = 50.0f;
    float j = 50.0f;
    float k;
    float l;
    float m;
    boolean n;
    boolean o;
    m p = new m();
    u q = new u();
    u r = new u();
    boolean s;
    boolean t;
    float u;
    float v = 0.0f;
    PointF w = new PointF();
    PointF x = new PointF();
    int y;
    as z;
    com.corrodinggames.rts.game.units.custom.d.b A;
    com.corrodinggames.rts.game.units.custom.d.b B;
    int C;
    int D;
    String E;
    int F;
    int G;
    boolean H = false;
    int I;
    int J;
    int K;
    int L;
    boolean M;
    ArrayList N = new ArrayList();
    as O;
    com.corrodinggames.rts.game.units.custom.d.b P;

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.b);
        as2.a(this.c);
        as2.a(this.d);
        as2.a(this.e);
        as2.a(this.f);
        as2.a(this.g);
        as2.a(this.i);
        as2.a(this.j);
        as2.a(this.k);
        as2.a(this.l);
        as2.c(4);
        as2.a(this.v);
        as2.a(this.m);
        as2.a(this.n);
        as2.a(this.o);
        as2.a(this.h);
        super.a(as2);
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.k k2) {
        this.b = (j)k2.b(j.class);
        this.c = (k)k2.b(k.class);
        this.d = k2.g();
        this.e = k2.g();
        this.f = k2.g();
        this.g = k2.g();
        this.i = k2.g();
        this.j = k2.g();
        this.k = k2.g();
        this.l = k2.g();
        byte by = k2.d();
        if (by >= 1) {
            this.v = k2.g();
        }
        if (by >= 2) {
            this.m = k2.g();
        }
        if (by >= 3) {
            this.n = k2.e();
            this.o = k2.e();
        }
        if (by >= 4) {
            this.h = k2.f();
        }
        super.a(k2);
    }

    public i(a a2, float f2, float f3) {
        super(a2, f2, f3);
    }

    public PointF a() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        PointF pointF = null;
        int n2 = (int)(this.U * l2.bL.r);
        l2.bL.a(this.S, this.T);
        int n3 = l2.bL.T;
        int n4 = l2.bL.U;
        for (int i2 = n3 - n2; i2 <= n3 + n2; ++i2) {
            for (int i3 = n4 - n2; i3 <= n4 + n2; ++i3) {
                com.corrodinggames.rts.game.units.y y2;
                g g2;
                if (!l2.bL.c(i2, i3) || (g2 = l2.bL.e(i2, i3)) == null || !g2.i) continue;
                am am2 = com.corrodinggames.rts.game.units.d.d.b(i2, i3);
                boolean bl = false;
                if (am2 == null) {
                    bl = true;
                }
                if (am2 != null && am2 instanceof com.corrodinggames.rts.game.units.y && !(y2 = (com.corrodinggames.rts.game.units.y)am2).r().p()) {
                    bl = true;
                }
                if (!bl) continue;
                l2.bL.a(i2, i3);
                if (pointF != null && com.corrodinggames.rts.gameFramework.f.a(0, 100) >= 50) continue;
                this.w.a(l2.bL.T + l2.bL.p, l2.bL.U + l2.bL.q);
                pointF = this.w;
            }
        }
        return pointF;
    }

    public void a(com.corrodinggames.rts.game.units.y y2) {
        this.q.remove(y2);
    }

    public void b() {
        this.p.clear();
        this.q.clear();
        am[] amArray = am.bE.a();
        int n2 = am.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            am am2 = amArray[i2];
            if (am2.bX != this.R || am2.bV || am2.u() || !this.a(am2)) continue;
            this.q.a(am2);
            as as2 = am2.dz;
            if (this.p.contains(as2)) continue;
            this.p.add(as2);
        }
    }

    public boolean a(as as2) {
        return this.a(as2, false, true) != null;
    }

    public boolean b(as as2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.ar && !as2.C() || as2.w()) {
            return false;
        }
        Object[] objectArray = this.p.a();
        int n2 = this.p.size();
        block0: for (int i2 = 0; i2 < n2; ++i2) {
            as as3 = (as)objectArray[i2];
            am[] amArray = this.q.a();
            int n3 = this.q.size();
            for (int i3 = 0; i3 < n3; ++i3) {
                am am2 = amArray[i3];
                if (am2.r() != as3 || !(am2 instanceof com.corrodinggames.rts.game.units.y)) continue;
                com.corrodinggames.rts.game.units.y y2 = (com.corrodinggames.rts.game.units.y)am2;
                if (!y2.b(as2, true)) continue block0;
                return true;
            }
        }
        return false;
    }

    public as c() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.R.aY) {
            return null;
        }
        int n2 = this.a(this.R.bC);
        as as2 = null;
        float f2 = -1.0f;
        for (as as3 : ar.ae) {
            if (!as3.j() || !this.b(as3)) continue;
            int n3 = this.R.a(as3, com.corrodinggames.rts.game.a.b.a);
            int n4 = this.c(as3);
            boolean bl2 = false;
            if (as3 instanceof com.corrodinggames.rts.game.units.custom.l) {
                bl2 = true;
                com.corrodinggames.rts.game.units.custom.l l3 = (com.corrodinggames.rts.game.units.custom.l)as3;
                if (l3.fL.size() != 0) {
                    for (as as4 : l3.fL) {
                        n3 += this.R.a(as4, com.corrodinggames.rts.game.a.b.a);
                        n4 += this.c(as4);
                    }
                }
            }
            float f3 = -2.0f;
            if (as3.p() && !bl2) {
                int n5 = n4;
                if (this.a() != null && com.corrodinggames.rts.gameFramework.f.a(0, 100) < 90) {
                    if (n5 == 0) {
                        f3 = this.R.o < 5000.0 ? 0.98f : 0.58f;
                    }
                    if (n5 == 1) {
                        f3 = 0.55f;
                    }
                    if (n5 == 2) {
                        f3 = 0.4f;
                    }
                    if (n5 >= 3) {
                        f3 = 0.25f / (float)n5;
                    }
                    if (n3 >= 3) {
                        f3 = (float)((double)f3 * 0.6);
                    }
                }
            }
            if (as3 == ar.b && (n3 < 5 || n2 == 0)) {
                if (n3 == 0) {
                    f3 = 0.8f;
                } else if (n4 < 2) {
                    f3 = 0.46f / (float)(n3 + n4 * 2);
                }
            }
            if (as3 == ar.d && this.R.ah() && (n3 < 5 || n2 == 0)) {
                if (n3 == 0) {
                    f3 = 0.3f;
                } else if (n4 < 1) {
                    f3 = 0.1f / (float)(n3 + n4 * 2);
                }
            }
            if (as3 == ar.c && (n3 < 5 || n2 == 0)) {
                if (n3 == 0) {
                    f3 = 0.48f;
                } else if (n4 < 2) {
                    f3 = 0.29f / (float)(n3 + n4);
                }
            }
            if (as3 == ar.f) {
                if (n4 == 0) {
                    f3 = 0.47f;
                } else if (n4 < 3) {
                    f3 = 0.35f / (float)n4;
                } else if (n4 < 4) {
                    f3 = 0.025f / (float)n4;
                }
            }
            if (as3 == ar.y && n4 == 0) {
                f3 = 0.018f;
            }
            if (as3 == ar.B && n4 == 0) {
                f3 = 0.02f;
            }
            if (as3 == ar.g) {
                if (n4 == 0) {
                    f3 = 0.42f;
                } else if (this.R.ac()) {
                    if (n4 < 4) {
                        f3 = 0.3f / (float)n4;
                    }
                } else if (n4 < 3) {
                    f3 = 0.3f / (float)n4;
                } else if (n4 < 4) {
                    f3 = 0.02f / (float)n4;
                }
            }
            if (as3 == ar.J && this.R.o > 2000.0 && n4 < 5) {
                f3 = n3 == 0 ? 0.11f : 0.07f / (0.2f * (float)n3 + (float)n4);
            }
            if (!(as3 != ar.D || l2.O() && l2.bX.ay.i || !(this.R.o > 2200.0) || n3 >= 4)) {
                if (n3 == 0) {
                    f3 = 0.06f;
                } else if (n4 < 1) {
                    f3 = 0.05f / (float)(n3 + n4 * 2);
                }
            }
            if (bl2) {
                com.corrodinggames.rts.game.units.custom.l l4 = (com.corrodinggames.rts.game.units.custom.l)as3;
                if (!(l4.fw || n3 >= l4.fx && l4.fx != -1 || n4 >= l4.fy && l4.fy != -1)) {
                    f3 = l4.fz;
                    if (n4 < l4.fA) {
                        f3 = l4.fB;
                    }
                    if (n4 == 0) {
                        f3 += l4.fC;
                    }
                    if (n3 == 0) {
                        f3 += l4.fD;
                    }
                    if (as3.p() && this.a() == null) {
                        f3 = -2.0f;
                    }
                }
            }
            if (this.R.ad() && as3 == ar.G && this.R.o > 15000.0) {
                if (n3 == 0) {
                    f3 = 0.04f;
                }
                if (this.R.o > 55000.0 && n3 == 1) {
                    f3 = 0.03f;
                }
            }
            if (!(f3 >= 0.0f) || !(f3 > f2) && !((double)com.corrodinggames.rts.gameFramework.f.c(0.0f, 1.0f) < 0.01)) continue;
            f2 = f3;
            as2 = as3;
        }
        this.f = f2;
        return as2;
    }

    public int a(d d2) {
        int n2 = 0;
        for (e e2 : d2.c) {
            n2 += this.c(e2.a);
        }
        return n2;
    }

    public int c(as as2) {
        int n2 = 0;
        u u2 = this.q;
        am[] amArray = u2.a();
        int n3 = u2.size();
        for (int i2 = 0; i2 < n3; ++i2) {
            am am2 = amArray[i2];
            if (am2.bX != this.R || am2.dz != as2 || !this.a(am2)) continue;
            ++n2;
        }
        return n2;
    }

    public int d() {
        int n2 = 0;
        u u2 = this.q;
        am[] amArray = u2.a();
        int n3 = u2.size();
        for (int i2 = 0; i2 < n3; ++i2) {
            com.corrodinggames.rts.game.units.y y2;
            am am2 = amArray[i2];
            if (am2.bX != this.R || !(am2 instanceof com.corrodinggames.rts.game.units.y) || !this.a(y2 = (com.corrodinggames.rts.game.units.y)am2, false) || y2.bM || y2.aB != null || !this.R.h(y2) || !this.R.i(y2)) continue;
            ++n2;
        }
        return n2;
    }

    public int e() {
        return this.K;
    }

    public boolean f() {
        int n2 = this.h();
        return n2 != 0;
    }

    public am g() {
        float f2 = this.U + 120.0f;
        am[] amArray = am.bE.a();
        int n2 = am.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            am am2 = amArray[i2];
            if (!(am2.eo + f2 > this.S) || !(am2.eo - f2 < this.S) || !(am2.ep + f2 > this.T) || !(am2.ep - f2 < this.T) || am2.bX == this.R || !this.a(am2, 120.0f) || !am2.bX.c(this.R) || !this.R.j(am2)) continue;
            return am2;
        }
        return null;
    }

    public int h() {
        return this.a(60.0f);
    }

    public int a(float f2) {
        int n2 = 0;
        float f3 = this.U + f2;
        am[] amArray = am.bE.a();
        int n3 = am.bE.size();
        for (int i2 = 0; i2 < n3; ++i2) {
            am am2 = amArray[i2];
            if (!(am2.eo + f3 > this.S) || !(am2.eo - f3 < this.S) || !(am2.ep + f3 > this.T) || !(am2.ep - f3 < this.T) || am2.bX == this.R || !this.a(am2, f2) || !am2.bX.c(this.R) || !am2.l() || !this.R.j(am2)) continue;
            ++n2;
        }
        return n2;
    }

    public void i() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        am am2 = this.g();
        if (am2 != null) {
            com.corrodinggames.rts.gameFramework.e e2 = l2.cf.a(this.R);
            am[] amArray = am.bE.a();
            int n2 = am.bE.size();
            for (int i2 = 0; i2 < n2; ++i2) {
                am am3 = amArray[i2];
                if (!(am3 instanceof com.corrodinggames.rts.game.units.y)) continue;
                com.corrodinggames.rts.game.units.y y2 = (com.corrodinggames.rts.game.units.y)am3;
                if (am3.bX != this.R || !this.R.b(am3, am2) || !this.R.i(y2) || !y2.aq()) continue;
                if (!am3.bM) {
                    if (!com.corrodinggames.rts.game.a.a.a(am3, this.S, this.T, 800.0f)) continue;
                    e2.a(y2);
                    continue;
                }
                if (!com.corrodinggames.rts.game.a.a.a(am3, this.S, this.T, 540.0f)) continue;
                e2.a(y2);
            }
            e2.a(am2);
        }
    }

    public boolean a(am am2) {
        return this.a(am2, false);
    }

    public boolean a(am am2, boolean bl2) {
        return am2 instanceof com.corrodinggames.rts.game.units.y && ((com.corrodinggames.rts.game.units.y)am2).aC == this && (!bl2 || this.b(am2));
    }

    public boolean a(com.corrodinggames.rts.game.units.y y2, boolean bl2) {
        return y2.aC == this && (!bl2 || this.b(y2));
    }

    public int j() {
        int n2 = 0;
        for (am am2 : this.k()) {
            if (this.R == am2.bX || !am2.bX.c(this.R) || !(am2 instanceof com.corrodinggames.rts.game.units.y) || !this.b(am2)) continue;
            ++n2;
        }
        return n2;
    }

    public com.corrodinggames.rts.game.units.f.f k() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return l2.cc.b(this.S, this.T, this.U);
    }

    private com.corrodinggames.rts.game.units.y x() {
        return this.a((as)null, (PointF)null, true);
    }

    private com.corrodinggames.rts.game.units.y y() {
        return this.f(null);
    }

    private com.corrodinggames.rts.game.units.y f(as as2) {
        return this.a(as2, null, false);
    }

    private com.corrodinggames.rts.game.units.y a(as as2, PointF pointF, boolean bl2) {
        if (this.K == 0) {
            return null;
        }
        this.y = 0;
        float f2 = Float.MAX_VALUE;
        com.corrodinggames.rts.game.units.y y2 = null;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (as2 != null && (l2.ar && !as2.C() || as2.w())) {
            return null;
        }
        am[] amArray = am.bE.a();
        int n2 = am.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            com.corrodinggames.rts.game.units.y y3;
            boolean bl3;
            am am2 = amArray[i2];
            if (am2.bX != this.R || !this.a(am2) || am2.cN != null || !am2.aj() || !(am2 instanceof com.corrodinggames.rts.game.units.y) || !this.R.i(am2) || !(bl3 = com.corrodinggames.rts.game.a.f.a(y3 = (com.corrodinggames.rts.game.units.y)am2)) || bl2 && !y3.I()) continue;
            ++this.y;
            if (as2 != null && !y3.b(as2, true)) continue;
            boolean bl4 = false;
            float f3 = -1.0f;
            if (pointF != null) {
                f3 = com.corrodinggames.rts.gameFramework.f.a(pointF.a, pointF.b, am2.eo, am2.ep);
            }
            if (y2 == null) {
                bl4 = true;
            } else {
                if (pointF != null && f3 < f2) {
                    bl4 = true;
                }
                if ((double)com.corrodinggames.rts.gameFramework.f.c(0.0f, 1.0f) < 0.2) {
                    bl4 = true;
                }
            }
            if (!bl4) continue;
            y2 = (com.corrodinggames.rts.game.units.y)am2;
            if (pointF == null) continue;
            f2 = f3;
        }
        return y2;
    }

    private com.corrodinggames.rts.game.units.y a(am am2, PointF pointF, boolean bl2) {
        if (this.L == 0) {
            return null;
        }
        float f2 = Float.MAX_VALUE;
        com.corrodinggames.rts.game.units.y y2 = null;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        am[] amArray = this.q.a();
        int n2 = this.q.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            com.corrodinggames.rts.game.units.y y3;
            boolean bl3;
            as as2;
            am am3 = amArray[i2];
            if (am3.bX != this.R || !this.a(am3) || am3.cN != null || !(as2 = am3.r()).n() || !(am3 instanceof com.corrodinggames.rts.game.units.y) || !this.R.i(am3) || !(bl3 = com.corrodinggames.rts.game.a.f.b(y3 = (com.corrodinggames.rts.game.units.y)am3)) || bl2 && !y3.I() || am2 != null && !y3.h(am2, true)) continue;
            boolean bl4 = false;
            if (as2 instanceof com.corrodinggames.rts.game.units.custom.l) {
                com.corrodinggames.rts.game.units.custom.l l3 = (com.corrodinggames.rts.game.units.custom.l)as2;
                if (l3.fH != null && !this.a(l3.fH)) continue;
            }
            float f3 = -1.0f;
            if (pointF != null) {
                f3 = com.corrodinggames.rts.gameFramework.f.a(pointF.a, pointF.b, am3.eo, am3.ep);
            }
            if (y2 == null) {
                bl4 = true;
            } else {
                if (pointF != null && f3 < f2) {
                    bl4 = true;
                }
                if ((double)com.corrodinggames.rts.gameFramework.f.c(0.0f, 1.0f) < 0.2) {
                    bl4 = true;
                }
            }
            if (!bl4) continue;
            y2 = (com.corrodinggames.rts.game.units.y)am3;
            if (pointF == null) continue;
            f2 = f3;
        }
        return y2;
    }

    private boolean g(as as2) {
        this.z = as2;
        this.A = null;
        this.B = null;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        PointF pointF = as2.p() ? this.a() : this.e(as2);
        if (pointF != null) {
            com.corrodinggames.rts.game.units.y y2 = this.a(as2, pointF, false);
            if (y2 == null) {
                return false;
            }
            if (as2 == ar.d && com.corrodinggames.rts.gameFramework.utility.y.c(pointF.a, pointF.b, ao.e) < this.R.at) {
                return false;
            }
            int n2 = 1;
            s s2 = y2.a(as2, true);
            if (s2 != null) {
                n2 = s2.t();
            } else {
                com.corrodinggames.rts.gameFramework.l.b("buildBuilding: could not find getBuildUnitAction for builder this shouldn't happen:" + as2.i());
            }
            if (!s2.b(y2) || !s2.a((am)y2, false)) {
                if (!this.R.a(s2.B(), (am)y2)) {
                    this.A = s2.B();
                    this.B = this.A.i(y2);
                }
            } else if (s2.A()) {
                com.corrodinggames.rts.gameFramework.e e2 = l2.cf.a(this.R);
                e2.a(y2);
                e2.a(s2.N(), pointF, null);
            } else {
                com.corrodinggames.rts.gameFramework.e e3 = l2.cf.a(this.R);
                e3.a(y2);
                e3.a(pointF.a, pointF.b, as2, n2);
            }
            return true;
        }
        return false;
    }

    private boolean z() {
        am[] amArray = am.bE.a();
        int n2 = am.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            com.corrodinggames.rts.game.units.y y2;
            am am2 = amArray[i2];
            if (am2.bX != this.R || !this.a(am2) || !am2.bT() || am2.u() || !(am2 instanceof com.corrodinggames.rts.game.units.y) || !(y2 = (com.corrodinggames.rts.game.units.y)am2).ai()) continue;
            return true;
        }
        return false;
    }

    public boolean a(h h2) {
        am[] amArray = this.q.a();
        int n2 = this.q.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            h h3;
            am am2 = amArray[i2];
            if (am2.bX != this.R || !am2.bT() || (h3 = am2.de()) == null || !com.corrodinggames.rts.game.units.custom.g.a(h2, h3)) continue;
            return true;
        }
        return false;
    }

    private com.corrodinggames.rts.game.units.y a(as as2, boolean bl2, boolean bl3) {
        am[] amArray = this.q.a();
        int n2 = this.q.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            am am2 = amArray[i2];
            if (am2.bX != this.R || !am2.bT() || !this.R.i(am2) || !(am2 instanceof com.corrodinggames.rts.game.units.d.l) || !(am2 instanceof com.corrodinggames.rts.game.units.y)) continue;
            com.corrodinggames.rts.game.units.y y2 = (com.corrodinggames.rts.game.units.y)am2;
            com.corrodinggames.rts.game.units.d.l l2 = (com.corrodinggames.rts.game.units.d.l)((Object)am2);
            s s2 = am2.e(as2);
            if (s2 == null || !l2.dy() && bl2 || s2.m(am2) || !s2.b(y2) || !s2.a((am)y2, false) || am2 instanceof com.corrodinggames.rts.game.units.d.e && !as2.m() && this.u() > 2 && !this.s && bl2 || bl3 && !y2.aD) continue;
            return y2;
        }
        return null;
    }

    private boolean a(d d2, boolean bl2) {
        ArrayList arrayList = d2.a();
        for (e e2 : arrayList) {
            if (!this.a(e2.a, bl2)) continue;
            return true;
        }
        return false;
    }

    private boolean a(as as2, boolean bl2) {
        int n2 = 1;
        return this.a(as2, bl2, n2);
    }

    private boolean a(as as2, boolean bl2, int n2) {
        if (n2 < 1) {
            com.corrodinggames.rts.gameFramework.l.b("AI", "buildUnit: quantity cannot be < 1");
            return false;
        }
        com.corrodinggames.rts.game.units.y y2 = this.a(as2, true, bl2);
        if (y2 == null) {
            // empty if block
        }
        if (y2 == null) {
            return false;
        }
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        s s2 = y2.e(as2);
        if (s2 == null) {
            com.corrodinggames.rts.gameFramework.l.b("AI", "buildUnit: action is null!");
            return false;
        }
        if (!s2.b(y2)) {
            com.corrodinggames.rts.gameFramework.l.b("AI", "buildUnit: isAvailable==false");
            return false;
        }
        if (!s2.a((am)y2, false)) {
            com.corrodinggames.rts.gameFramework.l.b("AI", "buildUnit: isActive==false");
            return false;
        }
        if (s2.g(y2) || s2.n_() && l2.ar) {
            return false;
        }
        for (int i2 = 0; i2 < n2; ++i2) {
            com.corrodinggames.rts.gameFramework.e e2 = l2.cf.a(this.R);
            e2.a(y2);
            e2.a(s2.z());
        }
        return true;
    }

    i l() {
        float f2 = -1.0f;
        i i2 = null;
        for (o o2 : this.R.bn) {
            i i3;
            if (!(o2 instanceof i) || (i3 = (i)o2) == this || i3.e() <= 1) continue;
            float f3 = i3.a(this);
            if (i2 != null && !(f3 < f2)) continue;
            f2 = f3;
            i2 = i3;
        }
        return i2;
    }

    public void m() {
        com.corrodinggames.rts.game.units.y y2;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        i i2 = this.l();
        if (i2 != null && i2.e() > 1 && (y2 = i2.x()) != null) {
            boolean bl2;
            PointF pointF = this.w();
            if (com.corrodinggames.rts.gameFramework.utility.y.a((am)y2, pointF.a, pointF.b) && ((bl2 = this.R.a((am)y2, pointF.a, pointF.b)) || this.R.aG != 0)) {
                com.corrodinggames.rts.gameFramework.e e2 = l2.cf.a(this.R);
                e2.a(y2);
                e2.a(pointF.a, pointF.b);
                ++this.h;
                this.g = com.corrodinggames.rts.gameFramework.f.a(1800, 2500);
                if (this.h >= 2) {
                    this.g += 11000.0f;
                }
                --i2.K;
                if (!bl2) {
                    boolean bl3 = true;
                    if (y2.aB != null) {
                        if (!y2.aB.a()) {
                            y2.aB.b(y2);
                        } else {
                            bl3 = false;
                            if (!y2.aB.G.contains(y2)) {
                                y2.aB.G.add(y2);
                            }
                        }
                    }
                    if (bl3) {
                        l l3 = new l(this.R);
                        l3.c(y2);
                        l3.S = pointF.a;
                        l3.T = pointF.b;
                    }
                    this.g = com.corrodinggames.rts.gameFramework.f.a(12000, 14000);
                }
            }
        }
    }

    private am A() {
        am[] amArray = am.bE.a();
        int n2 = am.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            am am2 = amArray[i2];
            if (am2.bX != this.R || !this.a(am2, true) || !am2.bI() || !(am2.cu < am2.cv - 1.0f) && !(am2.cm < 1.0f)) continue;
            return am2;
        }
        return null;
    }

    public void n() {
        boolean bl2;
        as as2;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.r.size() == 0) {
            return;
        }
        if (this.s) {
            return;
        }
        for (int i2 = 0; !(i2 >= 8 || (as2 = this.R.bA.c()) != null && this.a(as2) && (bl2 = this.d(as2))); ++i2) {
        }
    }

    public boolean d(as as2) {
        int n2;
        if (!(as2 instanceof com.corrodinggames.rts.game.units.custom.l)) {
            return false;
        }
        com.corrodinggames.rts.game.units.custom.l l2 = (com.corrodinggames.rts.game.units.custom.l)as2;
        if (l2.fE == -1 && l2.fF == -1) {
            return false;
        }
        int n3 = 0;
        int n4 = 0;
        boolean bl2 = l2.fG;
        am[] amArray = am.bE.a();
        int n5 = am.bE.size();
        for (n2 = 0; n2 < n5; n2 += 1) {
            am am2 = amArray[n2];
            if (am2.bX != this.R || am2.cN != null || !(am2 instanceof com.corrodinggames.rts.game.units.y) || !this.R.i(am2)) continue;
            com.corrodinggames.rts.game.units.y y2 = (com.corrodinggames.rts.game.units.y)am2;
            as as3 = am2.r();
            if (bl2 ? !as3.n() : as3 != l2 && !l2.fL.contains(as3)) continue;
            ++n4;
            if (!this.a(am2)) continue;
            ++n3;
        }
        if (l2.fE != -1 && n3 >= l2.fE) {
            return false;
        }
        if (l2.fF != -1 && n4 >= l2.fF) {
            return false;
        }
        n2 = this.a(l2, true) ? 1 : 0;
        return n2 != 0;
    }

    public void o() {
        if (this.r.size() == 0) {
            return;
        }
        am am2 = this.r();
        if (am2 != null) {
            this.x.a(am2.eo, am2.ep);
            com.corrodinggames.rts.game.units.y y2 = this.a(am2, this.x, true);
            if (y2 != null) {
                this.a(y2, am2);
            }
        }
    }

    public void q() {
        if (this.r.size() == 0) {
            return;
        }
        if (this.B != null) {
            am[] amArray = this.q.a();
            int n2 = this.q.size();
            for (int i2 = 0; i2 < n2; ++i2) {
                am am2;
                com.corrodinggames.rts.game.units.y y2;
                au au2;
                as as2;
                am am3 = amArray[i2];
                if (am3.bX != this.R || !this.a(am3) || am3.cN != null || !(as2 = am3.r()).n() || !(am3 instanceof com.corrodinggames.rts.game.units.y) || !this.R.i(am3) || (double)com.corrodinggames.rts.gameFramework.f.c(0.0f, 1.0f) > 0.3 || (au2 = (y2 = (com.corrodinggames.rts.game.units.y)am3).ar()) == null || au2.d() != av.g || (am2 = au2.i()) == null || !(am2.g() > 0.0f) || this.B.c(am2.cM())) continue;
                am am4 = this.r();
                this.a(y2, am4);
                break;
            }
        }
    }

    public am r() {
        am am2 = null;
        for (int i2 = 0; i2 < 20 && (am2 = this.r.a(com.corrodinggames.rts.gameFramework.f.a(0, this.r.size() - 1))) != null && this.B != null && !this.B.c(am2.cM()); ++i2) {
        }
        return am2;
    }

    public void a(com.corrodinggames.rts.game.units.y y2, am am2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (y2.g(am2, true)) {
            com.corrodinggames.rts.gameFramework.e e2 = l2.cf.a(this.R);
            e2.a(y2);
            e2.d(am2);
        }
    }

    public void s() {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        am am2 = this.A();
        if (am2 != null) {
            this.x.a(am2.eo, am2.ep);
            com.corrodinggames.rts.game.units.y y2 = this.a((as)null, this.x, true);
            if (y2 != null && y2.a(am2) && am2.e(y2) < 2) {
                com.corrodinggames.rts.gameFramework.e e2 = l2.cf.a(this.R);
                e2.a(y2);
                e2.b(am2);
            }
        }
    }

    public void b(float f2) {
        int n2;
        int n3;
        this.c(f2);
        int n4 = this.J;
        int n5 = this.I;
        this.b();
        this.n = this.z();
        if (this.n) {
            this.o = true;
        }
        if (n4 >= 1) {
            this.s();
        }
        if (this.M && this.I > 0) {
            this.n();
            this.q();
            this.o();
        }
        if (n4 < (n3 = 2) && this.i == 0.0f) {
            this.i = 300.0f;
            n2 = this.R.a(this.R.bz, com.corrodinggames.rts.game.a.b.a);
            if (!this.s || n2 <= 2) {
                boolean bl2;
                boolean bl3 = true;
                boolean bl4 = bl2 = com.corrodinggames.rts.gameFramework.f.a(0, 100) < 5;
                if (!bl2 && this.a(this.R.bz, bl3)) {
                    this.H = false;
                    this.i = 900.0f;
                } else {
                    if (!bl2) {
                        this.H = true;
                    }
                    if (!this.s && this.v == 0.0f && n4 < 1 && this.g == 0.0f) {
                        this.m();
                    }
                }
            }
        }
        n2 = this.j();
        if (n4 == 0 && n5 == 0) {
            this.k += f2;
            if (n2 > 2) {
                this.k += 2.0f * f2;
            }
            if (n2 > 5) {
                this.k += 4.0f * f2;
            }
        } else {
            this.k = com.corrodinggames.rts.gameFramework.f.a(this.k, f2);
        }
        if (this.k > 11000.0f) {
            this.p();
        }
        if (this.b == com.corrodinggames.rts.game.a.j.a && (n4 != 0 && n5 != 0 || n5 > 5 && n2 == 0)) {
            this.l += f2;
            if (this.l > 2000.0f) {
                this.b = com.corrodinggames.rts.game.a.j.c;
            }
        }
        this.t();
    }

    public void t() {
        if (this.b == null) {
            com.corrodinggames.rts.gameFramework.l.a("fixOverlaps: this.state==null");
            com.corrodinggames.rts.gameFramework.l.a("id:" + this.Q);
            com.corrodinggames.rts.gameFramework.l.a("x:" + this.S);
            com.corrodinggames.rts.gameFramework.l.a("y:" + this.T);
            com.corrodinggames.rts.gameFramework.l.a("radius:" + this.U);
            if (this.R != null) {
                com.corrodinggames.rts.gameFramework.l.a("team:" + this.R.k);
            }
            return;
        }
        for (o o2 : this.R.bm) {
            if (!(o2 instanceof i) || o2 == this) continue;
            i i2 = (i)o2;
            float f2 = com.corrodinggames.rts.gameFramework.f.a(this.S, this.T, i2.S, i2.T);
            if (!(f2 < 400.0f)) continue;
            if (i2.b == null) {
                com.corrodinggames.rts.gameFramework.l.a("fixOverlaps: targetBase.state==null");
                continue;
            }
            if (i2.b.a() < this.b.a()) {
                i2.p();
                continue;
            }
            this.p();
        }
    }

    public int u() {
        return this.I;
    }

    public void c(float f2) {
        this.I = 0;
        this.J = 0;
        this.L = 0;
        this.K = 0;
        this.M = false;
        this.r.clear();
        boolean bl2 = true;
        if (bl2) {
            for (am am2 : this.k()) {
                if (!(am2.g() > 0.0f) || !this.b(am2)) continue;
                this.M = true;
                this.r.a(am2);
            }
        }
        am[] amArray = am.bE.a();
        int n2 = am.bE.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            com.corrodinggames.rts.game.units.y y2;
            Object object = amArray[i2];
            if (((am)object).bX != this.R || !(object instanceof com.corrodinggames.rts.game.units.y) || !this.a(y2 = (com.corrodinggames.rts.game.units.y)object, false) || !((am)object).bT() || !this.R.i((am)object) || ((am)object).u()) continue;
            as as2 = ((am)object).r();
            if (as2.j()) {
                ++this.I;
            }
            if (as2.m()) {
                ++this.J;
                boolean bl3 = com.corrodinggames.rts.game.a.f.a(y2);
                if (bl3) {
                    ++this.K;
                }
            }
            if (as2.n()) {
                ++this.L;
            }
            if (!(object instanceof com.corrodinggames.rts.game.units.d.l)) continue;
            com.corrodinggames.rts.game.units.d.l l2 = (com.corrodinggames.rts.game.units.d.l)object;
            this.J += l2.h(ar.h);
        }
    }

    public void d(float f2) {
        this.t = this.s = this.f();
        if (this.s) {
            this.v += f2;
            this.u = 100.0f;
        } else {
            this.v = 0.0f;
        }
        if (this.v > 6000.0f) {
            this.s = false;
        }
        this.m = com.corrodinggames.rts.gameFramework.f.a(this.m, f2);
        this.e = com.corrodinggames.rts.gameFramework.f.a(this.e, f2);
        this.g = com.corrodinggames.rts.gameFramework.f.a(this.g, f2);
        this.i = com.corrodinggames.rts.gameFramework.f.a(this.i, f2);
        this.j = com.corrodinggames.rts.gameFramework.f.a(this.j, f2);
        if (this.s && this.j == 0.0f) {
            this.j = 100 + this.Q % 15;
            if (!this.R.aY) {
                this.i();
            }
        }
        if (this.e <= 0.0f) {
            boolean bl2;
            this.e = 270 + this.Q % 15;
            if (this.R.ac()) {
                this.e = 190 + this.Q % 15;
            }
            if ((double)this.f < 0.2) {
                this.e += 180.0f;
            }
            if ((double)this.f < 0.08) {
                this.e += 180.0f;
            }
            boolean bl3 = bl2 = this.y() != null;
            if (bl2) {
                as as2 = null;
                as2 = this.c();
                if (as2 != null && ((double)this.f > 0.8 || this.R.a(1300.0)) && ((double)this.f > 0.4 || this.R.a(1700.0)) && ((double)this.f > 0.2 || this.R.a(2100.0)) && ((double)this.f > 0.1 || this.R.a(2800.0)) && ((double)this.f > 0.05 || this.R.a(3100.0)) && ((double)this.f > 0.01 || this.R.a(4800.0))) {
                    ++this.C;
                    if (!this.g(as2)) {
                        this.e -= 120.0f;
                        ++this.D;
                    }
                }
            }
        }
        float f3 = this.u();
        if ((f3 /= 3.0f) < 1.0f) {
            f3 = 1.0f;
        }
        if (this.s) {
            this.d = (float)((double)this.d + (double)f2 * 0.015);
        }
        if ((double)this.f < 0.6) {
            if (this.R.bb < 2) {
                this.d = (float)((double)this.d + (double)f2 * 7.0E-4 * (double)f3);
            } else if (this.R.a(1200.0)) {
                this.d = (float)((double)this.d + (double)f2 * 1.0E-4 * (double)f3);
            }
            if (this.R.a(1600.0)) {
                this.d = (float)((double)this.d + (double)f2 * 0.001);
            }
            if (this.R.a(2200.0)) {
                this.d = (float)((double)this.d + (double)f2 * 0.001);
            }
            if (this.R.a(2600.0)) {
                this.d = (float)((double)this.d + (double)f2 * 0.001);
            }
            if (this.R.a(8000.0)) {
                this.d = (float)((double)this.d + (double)f2 * 0.005);
            }
            if (this.R.a(9000.0)) {
                this.d = (float)((double)this.d + (double)f2 * 0.01);
            }
            if (this.R.a(10100.0)) {
                this.d = (float)((double)this.d + (double)f2 * 0.01);
            }
            if (this.R.a(30000.0)) {
                this.d = (float)((double)this.d + (double)f2 * 0.05);
            }
        }
        if (this.R.a(5000.0)) {
            this.d = (float)((double)this.d + (double)f2 * 0.001);
        }
        if (!this.R.a(800.0) && !this.s && this.d > 1.2f) {
            this.d = 1.2f;
        }
        if (this.d > 3.5f) {
            this.d = 3.5f;
        }
        for (int i2 = 0; i2 < 12; ++i2) {
            this.v();
            if (!(this.d >= 3.0f)) break;
        }
    }

    public void a(ArrayList arrayList, d d2, ao ao2, int n2) {
        this.N.clear();
        for (int i2 = 0; i2 < n2; ++i2) {
            as as2 = d2.a(ao2);
            if (as2 == null || this.N.contains(as2)) continue;
            this.N.add(as2);
        }
        arrayList.addAll(this.N);
    }

    public void v() {
        int n2;
        int n3;
        float f2;
        int n4 = this.d();
        int n5 = 12;
        int n6 = 50;
        if (this.R.ac()) {
            n6 = 65;
            n5 = 16;
        }
        boolean bl2 = this.R.a(25000.0);
        boolean bl3 = false;
        ArrayList arrayList = new ArrayList();
        boolean bl4 = this.R.ai();
        boolean bl5 = this.R.aj();
        float f3 = 0.4f;
        float f4 = 0.3f;
        float f5 = 0.2f;
        if (!this.R.bh) {
            f3 = 0.1f;
            f4 = 0.5f;
            f5 = 0.4f;
        }
        if (!this.R.bi) {
            f3 = 0.2f;
            f4 = 0.1f;
            f5 = 0.7f;
        }
        ao ao2 = (f2 = com.corrodinggames.rts.gameFramework.f.c(0.0f, 1.0f)) < f3 ? ao.b : (f2 < f3 + f4 ? ao.f : ao.d);
        if (this.R.a(1300.0) && this.d >= 1.0f || this.R.a(300.0) && this.d >= 3.0f) {
            int n7;
            if (this.R.ah() && this.R.bc < n5 && (n7 = com.corrodinggames.rts.gameFramework.f.c(100)) < 35) {
                this.a(arrayList, this.R.bu, null, 2);
                if (bl2) {
                    bl3 = true;
                }
            }
            if (n4 < 3 && this.R.ba < n6) {
                if (ao2 == ao.b) {
                    this.a(arrayList, this.R.br, null, 4);
                    if (bl2) {
                        bl3 = true;
                    }
                } else if (ao2 == ao.f) {
                    this.a(arrayList, this.R.bs, null, 4);
                    if (bl2) {
                        bl3 = true;
                    }
                } else {
                    this.a(arrayList, this.R.bt, null, 4);
                    if (bl2) {
                        bl3 = true;
                    }
                }
            }
            if (this.d >= 1.0f && bl4 && this.m == 0.0f) {
                n7 = this.R.a(this.R.bx, com.corrodinggames.rts.game.a.b.a);
                int n8 = this.R.a(this.R.by, com.corrodinggames.rts.game.a.b.a);
                n3 = n7 + n8;
                n2 = this.R.ao();
                if ((this.R.a(1700.0) || n2 > 10 || this.R.bl == 0 && n2 >= 1 && n7 == 0) && (n3 < 3 || n2 > 20 && n3 < 5)) {
                    if (bl5 && n3 < 2) {
                        this.a(arrayList, this.R.bw, null, 2);
                    } else {
                        this.a(arrayList, this.R.bw, ao.d, 2);
                    }
                }
            }
        }
        if (arrayList.size() == 0) {
            ++this.F;
        }
        while (arrayList.size() != 0) {
            am am2;
            as as2 = (as)arrayList.remove(arrayList.size() - 1);
            am am3 = am.b(as2);
            n3 = 1;
            if (this.s && com.corrodinggames.rts.gameFramework.f.c(100) < 10 && (am2 = this.g()) != null && !this.R.b(am3, am2)) {
                ++this.F;
                n3 = 0;
            }
            if (n3 == 0) continue;
            n2 = 0;
            if (this.a(as2, n2 != 0)) {
                ++this.F;
                this.R.bE.a(as2);
                this.d -= 1.0f;
                if (!this.R.g(am3)) break;
                this.m = 1000.0f;
                ++this.R.bl;
                break;
            }
            ++this.G;
        }
    }

    public void a(com.corrodinggames.rts.game.units.y y2, com.corrodinggames.rts.game.units.custom.d.b b2, boolean bl2) {
        this.O = y2.r();
        if (bl2) {
            this.P = null;
            this.B = null;
        } else {
            this.P = b2;
            this.B = b2.i(y2);
        }
    }
}
