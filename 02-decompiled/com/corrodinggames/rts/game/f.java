/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game;

import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.af;
import com.corrodinggames.rts.game.units.custom.ay;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.z;
import com.corrodinggames.rts.game.units.d.d;
import com.corrodinggames.rts.gameFramework.a.e;
import com.corrodinggames.rts.gameFramework.az;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.utility.ai;
import com.corrodinggames.rts.gameFramework.utility.m;
import com.corrodinggames.rts.gameFramework.utility.u;
import com.corrodinggames.rts.gameFramework.utility.y;
import com.corrodinggames.rts.gameFramework.w;

public strictfp class f
extends az {
    public static final m a = new m();
    private static final f bm = new f(true);
    static com.corrodinggames.rts.gameFramework.m.e b = null;
    static com.corrodinggames.rts.gameFramework.m.e c = null;
    static com.corrodinggames.rts.gameFramework.m.e d = null;
    static final Rect e = new Rect();
    static final RectF f = new RectF();
    public com.corrodinggames.rts.game.g g = com.corrodinggames.rts.game.g.a;
    public float h;
    public float i;
    public am j;
    public short k = (short)-1;
    public am l;
    public boolean m;
    public float n;
    public float o;
    public float p;
    public f q;
    public float r = -1.0f;
    public float s = 0.1f;
    public float t;
    public float u;
    public float v;
    public float w;
    public float x = 2.0f;
    public float y = -1.0f;
    public boolean z = true;
    public boolean A;
    public boolean B;
    public boolean C;
    public boolean D;
    public boolean E;
    public float F;
    public boolean G;
    public float H = 1.0f;
    public float I;
    public float J;
    public float K;
    public float L;
    public boolean M;
    public float N;
    public float[] O;
    public short P = (short)-1;
    public short Q = (short)-1;
    public short R = 0;
    public boolean S = true;
    public boolean T;
    public float U;
    public boolean V = false;
    public float W = 0.0f;
    public float X = 0.0f;
    public float Y;
    public float Z;
    public boolean aa;
    public boolean ab = false;
    public boolean ac = false;
    public boolean ad = false;
    public boolean ae = true;
    public boolean af;
    public float ag;
    public float ah;
    public float ai = 1.0f;
    public float aj = 1.0f;
    public float ak = 1.0f;
    public float al = 1.0f;
    public float am = 1.0f;
    public float an;
    public boolean ao;
    public m ap;
    static final int aq = Color.a(255, 255, 255, 255);
    public int ar = aq;
    public boolean as;
    public boolean at;
    public w au;
    public int av = -1;
    public float aw;
    public float ax;
    public float ay;
    public float az;
    public float aA;
    public boolean aB;
    public boolean aC;
    public int aD;
    public h aE;
    public float aF;
    public boolean aG;
    public boolean aH;
    public float aI = 40.0f;
    public float aJ = 60.0f;
    public boolean aK = false;
    public float aL = 2.0f;
    public boolean aM;
    public float aN;
    public float aO;
    public com.corrodinggames.rts.gameFramework.d.e aP;
    public boolean aQ;
    public boolean aR = true;
    private boolean bn;
    public boolean aS;
    public float aT = 0.0f;
    public boolean aU;
    float aV;
    float aW;
    float aX;
    public boolean aY;
    public boolean aZ;
    public static final ag ba = new ag();
    public static final Paint bb = new Paint();
    public static final Paint bc;
    public static final Paint bd;
    public static final Paint be;
    public static final Paint bf;
    public static final Paint bg;
    public static final Paint bh;
    public static final u bi;
    public ag bj;
    public static ag bk;
    public static int bl;

    public f(boolean bl) {
        super(bl);
        if (!bl) {
            a.add(this);
        }
    }

    @Override
    public void a() {
        a.remove(this);
        super.a();
    }

    public static f a(f f2) {
        f f3 = bm;
        f3.aD = -1;
        if (f2 == null) {
            f3.am = 1.0f;
            f3.ak = 1.0f;
            f3.al = 1.0f;
            f3.an = 0.0f;
        } else {
            f3.am = f2.am;
            f3.ak = f2.ak;
            f3.al = f2.al;
            f3.an = f2.an;
        }
        return f3;
    }

    public void a(am am2, float f2, float f3, float f4) {
        this.j = am2;
        this.eo = f2;
        this.ep = f3;
        this.eq = f4;
        this.bn = false;
        this.V = false;
    }

    public void b() {
        if (this.D) {
            l l2 = com.corrodinggames.rts.gameFramework.l.B();
            com.corrodinggames.rts.gameFramework.d.e e2 = l2.bR.d(this.eo, this.ep, this.eq, 0);
            if (e2 != null) {
                e2.G = 0.7f;
                e2.F = 2.1f;
                e2.ar = (short)2;
                e2.W = e2.V = 90.0f;
            }
            l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.p, 0.8f, this.eo, this.ep);
        }
        this.a();
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        as2.a(this.h);
        as2.a(this.j);
        as2.a(this.l);
        as2.a(this.t);
        as2.a(99);
        as2.a(this.A);
        as2.a(this.B);
        as2.a(this.S);
        as2.a(this.T);
        as2.a(this.U);
        as2.a(this.Y);
        as2.a(this.Z);
        as2.a(this.ar);
        as2.a(this.aH);
        as2.a(this.aI);
        as2.a(this.aJ);
        as2.a(this.aK);
        as2.a(this.aL);
        as2.a(this.aM);
        as2.a(this.aN);
        as2.a(this.aQ);
        as2.a(this.aR);
        as2.a(this.bn);
        as2.a(this.aS);
        as2.a(this.M);
        as2.a(this.P);
        as2.a(this.r);
        as2.a(this.s);
        as2.a(this.as);
        as2.a(this.at);
        as2.a(this.az);
        as2.a(this.aA);
        as2.a(this.aB);
        as2.a(this.aC);
        as2.a(false);
        as2.a(0.0f);
        as2.a(0.0f);
        as2.a(this.E);
        as2.a(this.F);
        as2.a(this.J);
        as2.a(this.K);
        as2.a(this.L);
        as2.a(this.m);
        as2.a(this.n);
        as2.a(this.o);
        as2.a(this.C);
        as2.a(this.D);
        as2.a(this.q);
        as2.a(this.aV);
        as2.a(this.aW);
        as2.a(this.aX);
        as2.a(this.V);
        as2.a(this.W);
        as2.a(this.X);
        as2.a(this.aU);
        as2.a(this.R);
        as2.a(this.ao);
        as2.a(this.ap);
        as2.a(this.Q);
        as2.a(this.x);
        as2.a(this.aa);
        as2.a(this.ad);
        as2.a(this.G);
        as2.a(this.H);
        as2.a(this.ae);
        as2.a(this.aG);
        as2.a(this.z);
        as2.a(this.y);
        as2.a(this.aO);
        as2.a(this.i);
        as2.a(this.aY);
        as2.a(this.af);
        as2.a(this.ag);
        as2.a(this.ah);
        as2.a(this.ai);
        as2.a(this.aj);
        as2.a(0);
        as2.a(0.0f);
        as2.a(0.0f);
        as2.a((as)null);
        as2.a(0);
        as2.a(false);
        com.corrodinggames.rts.game.units.custom.g.a(this.aE, as2);
        as2.a(this.ak);
        as2.a(this.al);
        as2.a(this.ab);
        as2.a(this.ac);
        as2.a(this.an);
        as2.a(false);
        com.corrodinggames.rts.game.g.a(this.g, as2);
        boolean bl = this.au != null && !this.au.ej;
        as2.a(bl);
        if (bl) {
            as2.a(this.au);
            as2.a(this.aw);
            as2.a(this.ax);
            as2.a(this.ay);
        }
        as2.a(this.k);
        as2.a(this.aD);
        as2.a(this.am);
        as2.a(this.p);
        as2.a(this.av);
        super.a(as2);
    }

    @Override
    public void a(k k2) {
        boolean bl;
        this.h = k2.g();
        this.j = k2.o();
        this.l = k2.a(com.corrodinggames.rts.gameFramework.j.m.a);
        this.t = k2.g();
        this.x = k2.f();
        this.A = k2.e();
        this.B = k2.e();
        this.S = k2.e();
        this.T = k2.e();
        this.U = k2.g();
        this.Y = k2.g();
        this.Z = k2.g();
        this.ar = k2.f();
        this.aH = k2.e();
        this.aI = k2.g();
        this.aJ = k2.g();
        this.aK = k2.e();
        this.aL = k2.g();
        this.aM = k2.e();
        this.aN = k2.g();
        this.aQ = k2.e();
        this.aR = k2.e();
        this.bn = k2.e();
        if (k2.b() >= 7) {
            this.aS = k2.e();
        }
        if (k2.b() >= 13) {
            this.M = k2.e();
            this.P = k2.v();
        }
        if (k2.b() >= 16) {
            this.r = k2.g();
            this.s = k2.g();
        }
        if (k2.b() >= 17) {
            this.as = k2.e();
            this.at = k2.e();
            this.az = k2.g();
            this.aA = k2.g();
            this.aB = k2.e();
            this.aC = k2.e();
        }
        if (k2.b() >= 18) {
            k2.e();
            k2.g();
            k2.g();
        }
        if (k2.b() >= 28) {
            this.E = k2.e();
            this.F = k2.g();
            this.J = k2.g();
            this.K = k2.g();
            this.L = k2.g();
        }
        if (k2.b() >= 29) {
            this.m = k2.e();
            this.n = k2.g();
            this.o = k2.g();
            this.C = k2.e();
            this.D = k2.e();
            this.q = (f)k2.a(f.class);
            this.aV = k2.g();
            this.aW = k2.g();
            this.aX = k2.g();
            this.V = k2.e();
            this.W = k2.g();
            this.X = k2.g();
            this.aU = k2.e();
            this.R = k2.v();
            this.ao = k2.e();
            m m2 = new m();
            k2.a(m2, am.class);
            if (m2.size() > 0) {
                this.ap = m2;
            }
            this.Q = k2.v();
        }
        if (k2.b() >= 35) {
            this.x = k2.g();
            this.aa = k2.e();
            this.ad = k2.e();
            this.G = k2.e();
        }
        if (k2.b() >= 38) {
            this.H = k2.g();
        }
        if (k2.b() >= 39) {
            this.ae = k2.e();
        }
        if (k2.b() >= 41) {
            this.aG = k2.e();
        }
        if (k2.b() >= 43) {
            this.z = k2.e();
            this.y = k2.g();
        }
        if (k2.b() >= 44) {
            this.aO = k2.g();
        }
        if (k2.b() >= 47) {
            this.i = k2.g();
        }
        if (k2.b() >= 48) {
            this.aY = k2.e();
        }
        if (k2.b() >= 59) {
            this.af = k2.e();
            this.ag = k2.g();
            this.ah = k2.g();
            this.ai = k2.g();
        }
        if (k2.b() >= 60) {
            this.aj = k2.g();
            k2.f();
            k2.g();
            k2.g();
        }
        if (k2.b() >= 62) {
            k2.q();
            k2.f();
            k2.e();
        }
        if (k2.b() >= 63) {
            this.aE = com.corrodinggames.rts.game.units.custom.g.a(k2);
        }
        if (k2.b() >= 64) {
            this.ak = k2.g();
            this.al = k2.g();
        }
        if (k2.b() >= 66) {
            this.ab = k2.e();
            this.ac = k2.e();
        }
        if (k2.b() >= 67 && k2.b() < 78) {
            bp.a(k2, true);
        }
        if (k2.b() >= 68) {
            this.an = k2.g();
        }
        if (k2.b() >= 77) {
            k2.e();
        }
        if (k2.b() >= 78) {
            this.g = com.corrodinggames.rts.game.g.a(k2);
        }
        if (k2.b() >= 81 && (bl = k2.e())) {
            this.au = k2.a(w.class);
            this.aw = k2.g();
            this.ax = k2.g();
            this.ay = k2.g();
        }
        if (k2.b() >= 83) {
            this.k = k2.v();
            this.aD = k2.f();
        }
        if (k2.b() >= 88) {
            this.am = k2.g();
        }
        if (k2.b() >= 89) {
            this.p = k2.g();
            this.av = k2.f();
        }
        super.a(k2);
    }

    public static void c() {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        b = l2.bO.a(R$drawable.projectiles);
        c = l2.bO.a(R$drawable.projectiles2);
        d = l2.bO.a(R$drawable.projectiles_large);
    }

    public void d() {
        this.aS = true;
    }

    public static f a(am am2, float f2, float f3) {
        f f4 = new f(false);
        f4.j = am2;
        f4.eo = f2;
        f4.ep = f3;
        f4.ar = Color.a(255, 100, 30, 30);
        f4.en = am2.en + 1;
        f4.em = 4;
        return f4;
    }

    public static f a(am am2, float f2, float f3, float f4, int n2) {
        f f5 = com.corrodinggames.rts.game.f.a(am2, f2, f3);
        f5.eq = f4;
        f5.k = (short)n2;
        f5.I = com.corrodinggames.rts.gameFramework.f.b(am2, 0.0f, 1.0f, am2.bC);
        ++am2.bC;
        return f5;
    }

    public void a(am am2) {
        if (this.ag != 0.0f || this.ah != 0.0f) {
            if (am2.bI()) {
                return;
            }
            float f2 = com.corrodinggames.rts.gameFramework.f.a(this.aV, this.aW, am2.eo, am2.ep);
            float f3 = f2 > 100.0f ? com.corrodinggames.rts.gameFramework.f.d(this.aV, this.aW, am2.eo, am2.ep) : this.az;
            float f4 = this.ah;
            am2.cc += com.corrodinggames.rts.gameFramework.f.k(f3) * (f4 += this.ag / am2.bN());
            am2.cd += com.corrodinggames.rts.gameFramework.f.j(f3) * f4;
        }
    }

    public static void a(am am2, am am3, float f2, f f3, boolean bl) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.bw && f2 > 0.0f) {
            f2 = 0.0f;
        }
        if (am3 != null && !am3.bV) {
            float f4;
            float f5;
            float f6;
            if (f3 != null && f3.g.bc && am2 != null) {
                am3.e(am2.bX);
            }
            if (f3 != null) {
                if (f3.ai != 1.0f && am3.bI()) {
                    f2 *= f3.ai;
                }
                if (f3.aj != 1.0f && am3.i()) {
                    f2 *= f3.aj;
                }
            }
            if (f2 < 0.0f) {
                f6 = am3.b(am2, -f2, f3);
            } else {
                boolean bl2 = !am3.bV && am3.cu > 0.0f;
                f5 = am3.a(am2, f2, f3);
                f4 = f2;
                if (am3.J()) {
                    f4 = 0.0f;
                }
                if (f4 > 0.0f) {
                    l2.bY.a(am2, am3, f4);
                }
                if (am2 != null) {
                    am2.cV += f4;
                    if (bl2 && (am3.bV || am3.cu < 0.0f)) {
                        ++am2.cU;
                        am2.a(com.corrodinggames.rts.game.units.custom.af.d, am3);
                    }
                }
            }
            if (f3 != null && !am3.bV && (f6 = am3.bQ()) != -1.0f) {
                f5 = 100.0f;
                f4 = com.corrodinggames.rts.gameFramework.f.d(f3.eo, f3.ep, am3.eo, am3.ep);
                am3.cc += com.corrodinggames.rts.gameFramework.f.k(f4) * (f5 /= f6);
                am3.cd += com.corrodinggames.rts.gameFramework.f.j(f4) * f5;
            }
        }
    }

    public float e() {
        float f2 = 1.0f;
        if (this.J < this.F) {
            f2 = this.J / this.F;
        }
        return f2;
    }

    @Override
    public void a(float f2) {
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12;
        float f13;
        float f14;
        com.corrodinggames.rts.gameFramework.d.e e2;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.aS) {
            this.a();
        }
        if (this.l == null && !this.aC) {
            this.a();
            return;
        }
        if (this.i > 0.0f) {
            this.i = com.corrodinggames.rts.gameFramework.f.a(this.i, f2);
            if (this.i > 0.0f) {
                return;
            }
        }
        com.corrodinggames.rts.game.g g2 = this.g;
        if (this.i == 0.0f) {
            this.i = -1.0f;
            if (g2.al != null) {
                m m2 = null;
                boolean bl2 = false;
                am am2 = this.j;
                f f15 = this;
                e2 = null;
                g2.al.a(this.eo, this.ep, this.eq, this.az, am2, m2, bl2, this.aD + 1, f15, (am)((Object)e2));
            }
        }
        this.h = com.corrodinggames.rts.gameFramework.f.a(this.h, f2);
        boolean bl3 = false;
        if (this.aG) {
            if (this.l == null) {
                bl3 = true;
            } else if (this.l.bV) {
                bl3 = true;
            }
        }
        if (bl3) {
            this.a(g2.ax, g2.ay, null);
        }
        if (g2.az) {
            this.aF = com.corrodinggames.rts.gameFramework.f.a(this.aF, f2);
            if (this.aF == 0.0f) {
                this.aF = g2.aA;
                bl3 = true;
                this.a(g2.aB, g2.aC, g2.aD);
            }
        }
        if (g2.R != 0.0f || g2.S != 0.0f) {
            float f16 = g2.R;
            if (this.l != null) {
                f16 += this.l.cj * g2.S;
            }
            this.K = com.corrodinggames.rts.gameFramework.f.j((360.0f * this.I + this.J * 1.0f) % 360.0f) * f16;
            this.L = com.corrodinggames.rts.gameFramework.f.j((360.0f * this.I + this.J * 1.5f) % 360.0f) * f16;
        }
        if (this.E && this.l != null) {
            this.K = com.corrodinggames.rts.gameFramework.f.j(this.J * 1.0f % 360.0f) * this.l.cj * 0.4f;
            this.L = com.corrodinggames.rts.gameFramework.f.j(this.J * 1.5f % 360.0f) * this.l.cj * 0.4f;
            float f17 = this.l.eo + this.K;
            float f18 = this.l.ep + this.L;
            if (this.el) {
                this.aN += f2;
                this.aO += f2;
                if (this.aN > 11.0f) {
                    this.aN = com.corrodinggames.rts.gameFramework.f.c(1.0f, 4.0f);
                    boolean bl4 = false;
                    e2 = l2.bR.b(f17, f18, this.l.eq, com.corrodinggames.rts.gameFramework.d.d.a, bl4, com.corrodinggames.rts.gameFramework.d.h.b);
                    if (e2 != null) {
                        e2.aq = 0;
                        e2.ap = 0;
                        e2.ar = (short)2;
                        e2.r = true;
                        e2.E = 0.5f;
                        e2.W = 60.0f;
                        e2.V = 60.0f;
                        e2.G = 0.7f;
                        e2.F = 0.3f;
                        e2.as = false;
                        e2.P = com.corrodinggames.rts.gameFramework.f.c(-0.3f, 0.3f);
                        e2.Q = -0.9f + com.corrodinggames.rts.gameFramework.f.c(-0.3f, 0.3f);
                    }
                }
                if (this.aO > 75.0f) {
                    this.aO = com.corrodinggames.rts.gameFramework.f.c(1.0f, 20.0f);
                    l2.bR.b(f17, f18, this.l.eq);
                }
            }
        }
        float f19 = 5.0f;
        boolean bl5 = false;
        boolean bl6 = false;
        if (!this.aC) {
            f14 = this.l.eo + this.K;
            f13 = this.l.ep + this.L;
            f12 = this.l.eq;
            f11 = com.corrodinggames.rts.gameFramework.f.d(this.eo, this.ep, f14, f13);
            f10 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, f14, f13);
            f9 = f12;
            f8 = f9 - this.eq;
            f19 = this.l.cj;
            bl5 = this.l instanceof d;
            bl6 = this.l.cx > 10.0f + this.U;
        } else {
            f10 = 999.0f;
            f8 = 0.0f;
            f11 = this.az;
            f9 = 0.0f;
            if (this.q != null) {
                f14 = this.q.eo + this.K;
                f13 = this.q.ep + this.L;
                f12 = this.q.eq;
                f11 = com.corrodinggames.rts.gameFramework.f.d(this.eo, this.ep, f14, f13);
                f10 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, f14, f13);
                f9 = f12;
                f8 = f9 - this.eq;
            } else if (this.l != null) {
                f14 = this.l.eo + this.K;
                f13 = this.l.ep + this.L;
                f12 = this.l.eq;
                f11 = com.corrodinggames.rts.gameFramework.f.d(this.eo, this.ep, f14, f13);
                f10 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, f14, f13);
                f9 = f12;
                f8 = f9 - this.eq;
                f19 = this.l.cj;
                bl5 = this.l instanceof d;
                bl6 = this.l.cx > 10.0f + this.U;
            } else if (this.m) {
                f14 = this.n + this.K;
                f13 = this.o + this.L;
                f12 = this.p;
                f11 = com.corrodinggames.rts.gameFramework.f.d(this.eo, this.ep, f14, f13);
                f10 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, f14, f13);
                f9 = f12;
                f8 = f9 - this.eq;
            } else {
                f14 = this.n + this.K;
                f13 = this.o + this.L;
                f12 = 0.0f;
                f11 = com.corrodinggames.rts.gameFramework.f.d(this.eo, this.ep, f14, f13);
                f10 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, f14, f13);
                f9 = f12;
                f8 = f9 - this.eq;
            }
        }
        f14 = g2.O;
        if (f10 < 225.0f) {
            f14 = g2.P;
        }
        if (f14 >= 0.0f) {
            f13 = com.corrodinggames.rts.gameFramework.f.c(this.az, f11, f14 * f2);
            this.az += f13;
            f11 = this.az;
        } else {
            this.az = f11;
        }
        boolean bl7 = false;
        boolean bl8 = false;
        float f20 = f11;
        if (this.au != null && !this.au.ej) {
            float f21;
            if (this.av >= 0) {
                com.corrodinggames.rts.game.units.y y2 = (com.corrodinggames.rts.game.units.y)this.au;
                if (this.av >= y2.bl()) {
                    this.av = 0;
                }
                ai ai2 = y2.D(this.av);
                f7 = ai2.a;
                f6 = ai2.b;
                f21 = this.j.eq + ai2.c;
            } else {
                f7 = this.au.eo;
                f6 = this.au.ep;
                f21 = this.au.eq;
            }
            f5 = f7 - this.aw;
            f4 = f6 - this.ax;
            f3 = f21 - this.ay;
            this.eo += f5;
            this.ep += f4;
            this.eq += f3;
            this.aw = f7;
            this.ax = f6;
            this.ay = f21;
        }
        if (!this.A) {
            this.eo += this.u * f2;
            this.ep += this.v * f2;
            if (this.w != 0.0f) {
                f7 = this.w * f2;
                this.eq += f7;
                f8 = f9 - this.eq;
            }
            if (this.eq > 0.0f) {
                if (g2.G != 0.0f) {
                    this.eq -= g2.G * f2;
                    f8 = f9 - this.eq;
                }
                if (g2.H != 0.0f) {
                    this.w -= g2.H * f2;
                }
            }
            if (!this.aH || this.aI < this.eq || this.aK) {
                f7 = this.t * f2;
                bl7 = true;
                if (f10 < f7 * f7) {
                    f7 = com.corrodinggames.rts.gameFramework.f.a(f10);
                    f10 = 0.0f;
                }
                this.eo += com.corrodinggames.rts.gameFramework.f.k(f11) * f7;
                this.ep += com.corrodinggames.rts.gameFramework.f.j(f11) * f7;
            }
            if (this.aH) {
                if (this.aL < 0.0f) {
                    f7 = this.t * f2;
                    bl7 = true;
                } else {
                    f7 = this.aL * f2;
                }
                if (!this.aK) {
                    this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, this.aJ, f7);
                    if (this.eq < this.aI) {
                        f20 = -90.0f;
                    }
                    if (this.eq >= this.aJ) {
                        this.aK = true;
                    }
                } else if (f10 < 400.0f) {
                    this.eq = com.corrodinggames.rts.gameFramework.f.a(this.eq, f9, f7);
                    if (com.corrodinggames.rts.gameFramework.f.c(this.eq - f9) > 0.5f) {
                        f20 = 90.0f;
                        bl8 = true;
                    }
                }
            } else {
                f7 = f8;
                f6 = this.t * f2;
                bl7 = true;
                if (f7 != 0.0f) {
                    if ((double)f10 > 0.1) {
                        f6 = com.corrodinggames.rts.gameFramework.f.c(f7) / com.corrodinggames.rts.gameFramework.f.a(f10) * this.t * f2;
                        f6 = com.corrodinggames.rts.gameFramework.f.b(f6, this.t * f2);
                    }
                    this.eq += com.corrodinggames.rts.gameFramework.f.b(f8, f6);
                    f8 = f9 - this.eq;
                }
            }
        }
        if (bl7 && this.r > 0.0f) {
            this.t = com.corrodinggames.rts.gameFramework.f.a(this.t, this.r, this.s * f2);
        }
        if (g2.am != 0.0f) {
            f7 = com.corrodinggames.rts.gameFramework.f.j((this.J * 360.0f / g2.an + 360.0f * this.I) % 360.0f);
            f7 = f7 * g2.am * f2;
            this.eo += com.corrodinggames.rts.gameFramework.f.k(f11 + 90.0f) * f7;
            this.ep += com.corrodinggames.rts.gameFramework.f.j(f11 + 90.0f) * f7;
        }
        if (this.el && (this.aM || g2.ah != null) && !this.bn) {
            this.aN += f2;
            if (this.aN > g2.ag) {
                com.corrodinggames.rts.gameFramework.d.e e3;
                this.aN = 0.0f;
                boolean bl9 = false;
                if (this.D) {
                    bl9 = true;
                }
                if (g2.ah != null) {
                    g2.ah.a(this.eo, this.ep, this.eq, this.aT, this);
                }
                if (this.aM && (e3 = l2.bR.b(this.eo, this.ep, this.eq, com.corrodinggames.rts.gameFramework.d.d.a, bl9, com.corrodinggames.rts.gameFramework.d.h.b)) != null) {
                    if (this.eq >= 0.0f) {
                        e3.aq = 0;
                        e3.ap = 0;
                        e3.ar = (short)2;
                        e3.r = true;
                        e3.E = 0.5f;
                        e3.W = e3.V = 70.0f;
                        e3.as = true;
                        if (bl8) {
                            e3.as = false;
                        }
                        e3.Q = 0.1f;
                        e3.s = true;
                        e3.t = 5.0f;
                        e3.G = 0.5f;
                        e3.F = 1.2f;
                        e3.Y = com.corrodinggames.rts.gameFramework.f.c(-180.0f, 180.0f);
                        if (this.D) {
                            e3.G = 0.5f;
                            e3.F = 2.1f;
                        }
                    } else {
                        e3.aq = 9;
                        e3.ap = 1;
                        e3.ar = 1;
                        e3.r = true;
                        e3.E = 0.5f;
                        e3.W = 60.0f;
                        e3.V = 60.0f;
                        e3.Q = 0.1f;
                    }
                }
            }
        }
        if (!this.bn) {
            Object object;
            int n2;
            int n3;
            Object object2;
            boolean bl10 = false;
            am am3 = null;
            boolean bl11 = false;
            f5 = 6.0f;
            if (bl5 && (f5 = f19 * 0.8f) < 6.0f) {
                f5 = 6.0f;
            }
            if (bl6) {
                f5 = f19 * 1.1f;
            }
            f4 = 3.0f;
            if (this.w != 0.0f || g2.G != 0.0f) {
                f4 += com.corrodinggames.rts.gameFramework.f.c(this.w * f2) + com.corrodinggames.rts.gameFramework.f.c(g2.G * f2);
            }
            if (f10 < f5 * f5 && com.corrodinggames.rts.gameFramework.f.c(f8) < f4) {
                bl10 = true;
                am3 = this.l;
            }
            if (this.A) {
                bl10 = true;
                am3 = this.l;
            }
            if (this.af && this.h == 0.0f) {
                bl10 = true;
            }
            if (this.as) {
                f3 = this.aA + 50.0f;
                object2 = com.corrodinggames.rts.game.units.am.bE.a();
                n3 = com.corrodinggames.rts.game.units.am.bE.size();
                for (n2 = 0; n2 < n3; n2 += 1) {
                    float f22;
                    float f23;
                    object = object2[n2];
                    if (!(((am)object).eo + f3 > this.eo) || !(((am)object).eo - f3 < this.eo) || !(((am)object).ep + f3 > this.ep) || !(((am)object).ep - f3 < this.ep) || !((am)object).bT || ((am)object).i() || ((am)object).cN != null || !((f23 = com.corrodinggames.rts.gameFramework.f.a(this.eo, this.ep, ((am)object).eo, ((am)object).ep)) < (f22 = this.aA + ((am)object).cj) * f22)) continue;
                    bl10 = true;
                    am3 = object;
                }
            }
            if (this.at) {
                l2.bL.a(this.eo, this.ep);
                int n4 = l2.bL.T;
                int n5 = l2.bL.U;
                if (l2.bU.a(com.corrodinggames.rts.game.units.ao.f, n4, n5)) {
                    bl10 = true;
                    bl11 = true;
                }
            }
            if (this.aC) {
                // empty if block
            }
            if (this.aY && (this.aH && bl8 && this.eq < 30.0f || bl10) && this.j != null) {
                this.aY = false;
                com.corrodinggames.rts.game.units.u u2 = new com.corrodinggames.rts.game.units.u(false);
                u2.eo = this.eo;
                u2.ep = this.ep;
                u2.b(this.j.bX);
                u2.a = 15;
                u2.b = 360.0f;
                com.corrodinggames.rts.game.n.c(u2);
            }
            if (bl10) {
                z z2;
                this.bn = true;
                this.aV = this.eo;
                this.aW = this.ep;
                this.aX = this.eq;
                if (this.A) {
                    if (this.aC) {
                        this.aV = this.n;
                        this.aW = this.o;
                        this.aX = 0.0f;
                    }
                    if (this.l != null) {
                        this.aV = this.l.eo + this.K;
                        this.aW = this.l.ep + this.L;
                        this.aX = this.l.eq;
                    }
                }
                if (!(this.B || this.M || g2.X)) {
                    this.S = false;
                }
                boolean bl12 = false;
                if (this.l != null) {
                    bl12 = this.l.cx > 10.0f;
                }
                object2 = g2.aX;
                if (bl12) {
                    object2 = g2.aY;
                }
                if (this.l != null && (z2 = g2.a(this.l)) != null) {
                    object2 = z2;
                }
                if (object2 != null) {
                    object2.a(this.aV, this.aW, this.aX, this.aT, this.l);
                }
                if (g2.aj != null) {
                    m m3 = null;
                    n3 = 0;
                    object = this.j;
                    f f24 = this;
                    am am4 = this.l;
                    g2.aj.a(this.eo, this.ep, this.eq, this.az, (am)object, m3, n3 != 0, this.aD + 1, f24, am4);
                }
                if (g2.aZ != null && this.j != null) {
                    g2.aZ.a(this.aV, this.aW, 0.0f, this.az, this.j.bX, false, this.j);
                }
                if (g2.ba > 0 && this.j != null && this.j instanceof j) {
                    j j2 = (j)this.j;
                    for (n3 = 0; n3 < g2.ba; n3 += 1) {
                        if (j2.B == null || j2.B.size() <= 0) continue;
                        object = (am)j2.B.remove(j2.B.size() - 1);
                        com.corrodinggames.rts.gameFramework.utility.y.a((am)object, j2);
                        ((am)object).eo = this.aV;
                        ((am)object).ep = this.aW;
                        ((am)object).cg = this.az;
                        ((am)object).cd = 0.0f;
                        ((am)object).cc = 0.0f;
                        ((am)object).bZ = 0.0f;
                        ((am)object).ca = 0.0f;
                        if (object instanceof com.corrodinggames.rts.game.units.y) {
                            com.corrodinggames.rts.game.units.y y3 = (com.corrodinggames.rts.game.units.y)object;
                            y3.az();
                            y3.j(((am)object).cg);
                            if (object instanceof j) {
                                ((j)object).dF();
                            }
                        }
                        j2.D((am)object);
                    }
                }
                if (g2.bb && this.j != null) {
                    this.j.f(this.aV, this.aW);
                }
                if (!bl11 && am3 != null) {
                    if (this.E) {
                        this.bn = false;
                        float f25 = this.U / 60.0f * f2 * this.e();
                        if (this.Z == 0.0f) {
                            this.a(am3);
                        }
                        f25 = g2.a(am3, f25, true);
                        com.corrodinggames.rts.game.f.a(this.j, am3, f25, this, false);
                    } else {
                        if (this.Z == 0.0f) {
                            this.a(am3);
                        }
                        float f26 = this.U;
                        f26 = g2.a(am3, f26, false);
                        com.corrodinggames.rts.game.f.a(this.j, am3, f26, this, false);
                    }
                }
                if (this.q != null) {
                    if (g2.d) {
                        this.q.h = 0.0f;
                    } else {
                        this.q.b();
                    }
                    this.a();
                }
                if (!this.E) {
                    n2 = 1;
                    if (this.l != null && this.l.cx > 10.0f) {
                        com.corrodinggames.rts.gameFramework.d.e e4;
                        n2 = 0;
                        if (g2.aY == null && (e4 = l2.bR.d(this.aV, this.aW, this.aX, -1127220)) != null) {
                            e4.V = 10.0f;
                            e4.F = 0.5f;
                            if (this.aQ) {
                                e4.V = 25.0f;
                                e4.F = 1.0f;
                            }
                            e4.ar = (short)2;
                            e4.W = e4.V;
                        }
                    }
                    if (this.G) {
                        n2 = 0;
                        com.corrodinggames.rts.gameFramework.d.f f27 = com.corrodinggames.rts.gameFramework.d.f.b(this.eo, this.ep);
                        f27.a = 21.0f;
                    }
                    if (n2) {
                        if (!this.aQ) {
                            if (g2.aX == null) {
                                l2.bR.c(this.aV, this.aW, this.aX);
                            }
                        } else if (g2.aX == null) {
                            com.corrodinggames.rts.gameFramework.d.e e5;
                            if (this.Z > 10.0f && (e5 = l2.bR.d(this.aV, this.aW, this.aX, 0)) != null) {
                                e5.F = this.Z / 25.0f;
                                e5.E = 0.7f;
                                if (this.aX > 5.0f) {
                                    e5.ar = (short)2;
                                }
                            }
                            l2.bR.b(this.aV, this.aW, this.aX);
                            if (this.aR && !this.D) {
                                float f28 = 1.0f + com.corrodinggames.rts.gameFramework.f.c(-0.06f, 0.06f);
                                l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.n, 0.5f, f28, this.aV, this.aW);
                            }
                        }
                        if (this.D && g2.aX == null) {
                            int n6;
                            float f29 = 0.7f;
                            l2.bM.a(com.corrodinggames.rts.gameFramework.a.e.C, 1.6f, f29, this.aV, this.aW);
                            l2.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
                            object = l2.bR.a(this.aV, this.aW, this.eq, Color.a(255, 255, 255, 255));
                            if (object != null) {
                                ((com.corrodinggames.rts.gameFramework.d.e)object).G = 14.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).F = 8.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).E = 0.9f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).W = ((com.corrodinggames.rts.gameFramework.d.e)object).V = 35.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).r = true;
                            }
                            l2.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
                            object = l2.bR.c(this.aV, this.aW, this.aX, -1127220);
                            if (object != null) {
                                ((com.corrodinggames.rts.gameFramework.d.e)object).G = 1.5f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).F = 3.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).ar = (short)2;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).W = ((com.corrodinggames.rts.gameFramework.d.e)object).V = 20.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).U = 0.0f;
                            }
                            l2.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
                            object = l2.bR.c(this.aV, this.aW, this.aX, -1127220);
                            if (object != null) {
                                ((com.corrodinggames.rts.gameFramework.d.e)object).G = 0.2f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).F = 5.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).ar = (short)2;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).W = ((com.corrodinggames.rts.gameFramework.d.e)object).V = 65.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).U = 0.0f;
                            }
                            l2.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
                            object = l2.bR.a(this.aV, this.aW, this.eq, Color.a(255, 255, 255, 255));
                            if (object != null) {
                                ((com.corrodinggames.rts.gameFramework.d.e)object).G = 3.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).F = 6.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).E = 0.9f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).W = ((com.corrodinggames.rts.gameFramework.d.e)object).V = 290.0f;
                            }
                            l2.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
                            object = l2.bR.a(this.aV, this.aW, this.eq, Color.a(255, 255, 244, 230));
                            if (object != null) {
                                ((com.corrodinggames.rts.gameFramework.d.e)object).G = 2.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).F = 6.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).E = 0.5f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).W = ((com.corrodinggames.rts.gameFramework.d.e)object).V = 370.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).U = 10.0f;
                            }
                            for (n6 = 0; n6 < 1; ++n6) {
                                l2.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
                                object = l2.bR.a(this.aV, this.aW, this.eq, Color.a(255, 255, 244, 230));
                                if (object == null) continue;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).G = 0.2f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).F = 9.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).E = 0.7f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).W = ((com.corrodinggames.rts.gameFramework.d.e)object).V = 210.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).U = 20 + n6 * 110;
                            }
                            l2.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
                            object = l2.bR.a(this.aV, this.aW, this.eq, Color.a(255, 255, 255, 255));
                            if (object != null) {
                                ((com.corrodinggames.rts.gameFramework.d.e)object).G = 3.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).F = 4.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).E = 0.2f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).W = ((com.corrodinggames.rts.gameFramework.d.e)object).V = 870.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).r = true;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).U = 70.0f;
                            }
                            l2.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
                            object = l2.bR.a(this.aV, this.aW, this.eq, Color.a(255, 206, 255, 239));
                            if (object != null) {
                                ((com.corrodinggames.rts.gameFramework.d.e)object).G = 4.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).F = 1.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).E = 0.9f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).W = ((com.corrodinggames.rts.gameFramework.d.e)object).V = 320.0f;
                            }
                            l2.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
                            object = l2.bR.a(this.aV, this.aW, this.eq, Color.a(255, 255, 242, 129));
                            if (object != null) {
                                ((com.corrodinggames.rts.gameFramework.d.e)object).G = 2.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).F = 1.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).E = 1.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).W = ((com.corrodinggames.rts.gameFramework.d.e)object).V = 340.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).s = true;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).t = 20.0f;
                            }
                            l2.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
                            object = l2.bR.a(this.aV, this.aW, this.eq, Color.a(245, 255, 182, 110));
                            if (object != null) {
                                ((com.corrodinggames.rts.gameFramework.d.e)object).G = 1.5f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).F = 1.5f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).E = 0.3f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).W = ((com.corrodinggames.rts.gameFramework.d.e)object).V = 1340.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).s = true;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).t = 40.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).U = 140.0f;
                            }
                            for (n6 = 0; n6 < 4; ++n6) {
                                l2.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
                                object = l2.bR.a(this.aV, this.aW, this.eq, Color.a(225, 255, 242, 129));
                                if (object == null) continue;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).G = 1.5f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).F = 1.4f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).E = 1.3f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).W = ((com.corrodinggames.rts.gameFramework.d.e)object).V = 340.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).Q = -0.29f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).s = true;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).t = 50.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).U = 30 + n6 * 40;
                            }
                            for (n6 = 0; n6 < 2; ++n6) {
                                l2.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
                                object = l2.bR.a(this.aV, this.aW, this.eq, Color.a(185, 255, 242, 129));
                                if (object == null) continue;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).G = 1.3f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).F = 1.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).E = 1.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).W = ((com.corrodinggames.rts.gameFramework.d.e)object).V = 340.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).Q = -0.14f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).s = true;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).t = 50.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).U = 70 + n6 * 70;
                            }
                            for (n6 = 0; n6 < 4; ++n6) {
                                l2.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
                                object = l2.bR.a(this.aV, this.aW - 30.0f, this.eq, -16711936);
                                if (object == null) continue;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).G = 1.5f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).F = 2.6f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).E = 1.3f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).W = ((com.corrodinggames.rts.gameFramework.d.e)object).V = 510.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).Q = -0.2f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).s = true;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).t = 50.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).B = null;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).x = Color.a(175, 235, 235, 235);
                                ((com.corrodinggames.rts.gameFramework.d.e)object).U = 20 + n6 * 40;
                            }
                            for (n6 = 0; n6 < 2; ++n6) {
                                l2.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
                                object = l2.bR.a(this.aV, this.aW - 30.0f, this.eq, -16711936);
                                if (object == null) continue;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).G = 1.5f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).F = 3.8f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).E = 0.8f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).W = ((com.corrodinggames.rts.gameFramework.d.e)object).V = 590.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).Q = -0.2f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).s = true;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).t = 50.0f;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).B = null;
                                ((com.corrodinggames.rts.gameFramework.d.e)object).x = Color.a(105, 115, 115, 115);
                                ((com.corrodinggames.rts.gameFramework.d.e)object).U = 20 + n6 * 40;
                            }
                            for (n6 = 0; n6 < 1; ++n6) {
                                com.corrodinggames.rts.gameFramework.d.f f30 = com.corrodinggames.rts.gameFramework.d.f.a(this.aV + com.corrodinggames.rts.gameFramework.f.a(-10.0f, 10.0f, (int)this.eh), this.aW + com.corrodinggames.rts.gameFramework.f.a(-10.0f, 10.0f, (int)this.eh + n6));
                                if (f30 == null) continue;
                                f30.t = 200 + n6 * 70;
                                f30.a = 980 + n6 * 800;
                            }
                            if (!com.corrodinggames.rts.gameFramework.utility.y.d(this.aV, this.aW)) {
                                com.corrodinggames.rts.game.l.a(this.aV, this.aW, com.corrodinggames.rts.game.m.b);
                            }
                            if (com.corrodinggames.rts.gameFramework.l.aB()) {
                                if (l2.bR.m == null) {
                                    l2.bR.m = l2.bO.a(R$drawable.shockwave_normal_256, true);
                                }
                                l2.bR.b(com.corrodinggames.rts.gameFramework.d.h.e);
                                object = l2.bR.a(this.aV, this.aW, this.eq, -1);
                                if (object != null && l2.bR.m != null) {
                                    ((com.corrodinggames.rts.gameFramework.d.e)object).a = new ay(null);
                                    ((com.corrodinggames.rts.gameFramework.d.e)object).a.imageStrip = new com.corrodinggames.rts.gameFramework.d.g();
                                    ((com.corrodinggames.rts.gameFramework.d.e)object).a.imageStrip.k = true;
                                    ((com.corrodinggames.rts.gameFramework.d.e)object).a.imageStrip.i = l2.bR.m;
                                    ((com.corrodinggames.rts.gameFramework.d.e)object).a.imageStrip.b = ((com.corrodinggames.rts.gameFramework.d.e)object).a.imageStrip.i.m();
                                    ((com.corrodinggames.rts.gameFramework.d.e)object).a.imageStrip.c = ((com.corrodinggames.rts.gameFramework.d.e)object).a.imageStrip.i.l();
                                    ((com.corrodinggames.rts.gameFramework.d.e)object).ar = (short)3;
                                    ((com.corrodinggames.rts.gameFramework.d.e)object).G = 0.5f;
                                    ((com.corrodinggames.rts.gameFramework.d.e)object).F = 3.5f;
                                    ((com.corrodinggames.rts.gameFramework.d.e)object).E = 0.5f;
                                    ((com.corrodinggames.rts.gameFramework.d.e)object).W = ((com.corrodinggames.rts.gameFramework.d.e)object).V = 60.0f;
                                    ((com.corrodinggames.rts.gameFramework.d.e)object).Q = -0.2f;
                                    ((com.corrodinggames.rts.gameFramework.d.e)object).s = true;
                                    ((com.corrodinggames.rts.gameFramework.d.e)object).t = 1.0f;
                                    ((com.corrodinggames.rts.gameFramework.d.e)object).B = null;
                                    ((com.corrodinggames.rts.gameFramework.d.e)object).U = 0.0f;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (this.bn && !this.V) {
            this.W = com.corrodinggames.rts.gameFramework.f.a(this.W, f2);
            if (this.ao) {
                float f31 = 1.0f - this.W / this.X;
                this.b(f31);
            }
            if (this.W == 0.0f) {
                this.V = true;
                this.b(1.0f);
                if (!(this.B || this.M || g2.X)) {
                    this.a();
                }
            }
        }
        this.J += f2;
        if (this.h == 0.0f && (!this.bn || this.V)) {
            if (g2.ak != null) {
                m m4 = null;
                boolean bl13 = false;
                am am5 = this.j;
                f f32 = this;
                am am6 = null;
                g2.ak.a(this.eo, this.ep, this.eq, this.az, am5, m4, bl13, this.aD + 1, f32, am6);
            }
            this.a();
        }
        if (!this.aU) {
            this.aT = f20;
            this.aU = true;
        }
        float f33 = com.corrodinggames.rts.gameFramework.f.c(this.aT, f20, 12.0f * f2);
        this.aT += f33;
    }

    public void b(float f2) {
        float f3;
        boolean bl2 = false;
        if (this.g.f) {
            return;
        }
        if (this.g.e) {
            bl2 = true;
        }
        if (!bl2) {
            if (this.Y != 0.0f && this.Z > 0.0f) {
                bl2 = true;
            }
            if ((this.ag != 0.0f || this.ah != 0.0f) && this.Z > 0.0f) {
                bl2 = true;
            }
        }
        if (!bl2) {
            return;
        }
        float f4 = f3 = this.Z * f2;
        if (this.g.h) {
            f4 += 150.0f;
        }
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        bi.clear();
        l2.cc.b(this.aV, this.aW, f4, bi);
        am[] amArray = bi.a();
        int n2 = bi.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            am am2 = amArray[i2];
            this.b(am2, f2, f3);
        }
        bi.clear();
    }

    public void b(am am2, float f2, float f3) {
        float f4;
        if (am2.cN != null) {
            return;
        }
        if (this.ap != null && this.ap.contains(am2)) {
            return;
        }
        if (this.j != null) {
            n n2 = am2.bX;
            n n3 = this.j.bX;
            if (n2 != n3 && n3.d(n2)) {
                return;
            }
            if (this.aa && !n3.c(n2)) {
                return;
            }
            if (this.ab && n3.c(n2)) {
                return;
            }
        }
        if (am2.eq < -5.0f && this.aX >= -2.0f && !this.ac) {
            return;
        }
        if (this.ae) {
            boolean bl2;
            boolean bl3 = bl2 = this.aX >= 5.0f;
            if (am2.i() != bl2) {
                return;
            }
        } else if (!this.ad && am2.i()) {
            return;
        }
        if ((f4 = com.corrodinggames.rts.gameFramework.f.a(this.aV, this.aW, am2.eo, am2.ep)) > f3 * f3 && !this.g.h) {
            return;
        }
        float f5 = (float)StrictMath.sqrt(f4);
        if (this.g.h && (f5 -= am2.cj) < 0.0f) {
            f5 = 0.0f;
        }
        if (f5 > f3) {
            return;
        }
        if (f5 < this.g.j) {
            return;
        }
        this.a(f2, am2, f5);
    }

    public void a(float f2, am am2, float f3) {
        float f4 = 1.0f - f3 / this.Z;
        if ((f4 = (float)((double)f4 + 0.1)) > 1.0f) {
            f4 = 1.0f;
        }
        if (this.g.g) {
            f4 = 1.0f;
        }
        float f5 = f4 * this.Y;
        this.a(am2);
        f5 = this.g.a(am2, f5, true);
        com.corrodinggames.rts.game.f.a(this.j, am2, f5, this, true);
        if (this.ao) {
            if (this.ap == null) {
                this.ap = new m();
            }
            this.ap.add(am2);
        }
    }

    @Override
    public boolean a(l l2) {
        if (l2.cO.b(this.eo, this.ep)) {
            return true;
        }
        return (this.B || this.E || this.g.X) && this.l != null && l2.cO.b(this.l.eo, this.l.ep);
    }

    @Override
    public boolean c(float f2) {
        int n2;
        float f3;
        float f4;
        float f5;
        if (!this.S) {
            return false;
        }
        if (this.i > 0.0f) {
            return false;
        }
        com.corrodinggames.rts.game.g g2 = this.g;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        com.corrodinggames.rts.gameFramework.m.y y2 = l2.bO;
        float f6 = this.eo - l2.cw;
        float f7 = this.ep - l2.cx;
        if (this.l != null) {
            f5 = this.l.eo;
            f4 = this.l.ep;
            f3 = this.l.eq;
        } else {
            f5 = this.n;
            f4 = this.o;
            f3 = this.p;
        }
        if (!this.aZ && !this.D) {
            n2 = 0;
            if (this.A) {
                if (this.l != null) {
                    if (!l2.bL.a(this.l.eo, this.l.ep, l2.bs)) {
                        n2 = 1;
                    }
                } else if (this.m && !l2.bL.a(this.n, this.o, l2.bs)) {
                    n2 = 1;
                }
            }
            if (!l2.bL.a(this.eo, this.ep, l2.bs) && n2 == 0) {
                return false;
            }
            this.aZ = true;
        }
        if (this.E || g2.X) {
            if (g2.Y != null) {
                Paint paint = this.f();
                float f8 = 0.0f;
                float f9 = 0.0f;
                if (g2.ad != 0.0f) {
                    f9 += g2.ad * this.J;
                }
                float f10 = this.eo - l2.cw;
                float f11 = this.ep - l2.cx - this.eq;
                float f12 = f5 - l2.cw + this.K;
                float f13 = f4 - f3 - l2.cx + this.L;
                float f14 = (f12 + f10) * 0.5f;
                float f15 = (f13 + f11) * 0.5f;
                float f16 = com.corrodinggames.rts.gameFramework.f.b(f14, f15, f12, f13);
                float f17 = com.corrodinggames.rts.gameFramework.f.d(f14, f15, f12, f13);
                y2.k();
                f.a(f14 - (float)g2.Y.r, f15 - f16, f14 + (float)g2.Y.r, f15 + f16);
                y2.a(f17 + 90.0f, f14, f15);
                y2.a(g2.Y, f, paint, f8, f9, 0, 0);
                y2.l();
                if (g2.Z != null) {
                    if (g2.aa) {
                        y2.k();
                        y2.a(f17 + 90.0f, f10, f11);
                        y2.a(g2.Z, f10, f11, paint);
                        y2.l();
                    } else {
                        y2.a(g2.Z, f10, f11, paint);
                    }
                }
                if (g2.ab != null) {
                    if (g2.ac) {
                        y2.k();
                        y2.a(f17 + 90.0f, f12, f13);
                        y2.a(g2.ab, f12, f13, paint);
                        y2.l();
                    } else {
                        y2.a(g2.ab, f12, f13, paint);
                    }
                }
            } else {
                bf.c((int)(60.0f + this.e() * 60.0f));
                float f18 = f5 - l2.cw + this.K;
                float f19 = f4 - f3 - l2.cx + this.L;
                bf.a(6.0f);
                y2.a(this.eo - l2.cw, this.ep - l2.cx - this.eq, f18, f19, bf);
                bf.a(3.0f);
                y2.a(this.eo - l2.cw, this.ep - l2.cx - this.eq, f18, f19, bf);
                y2.a(f18, f19, 8.0f, bf);
                y2.a(f18, f19, 5.0f, bf);
            }
        } else if (this.B) {
            float f20 = f5 - l2.cw + this.K;
            float f21 = f4 - f3 - l2.cx + this.L;
            bd.b(this.ar);
            be.b(this.ar);
            be.c((int)((float)be.f() * 0.5f));
            y2.a(this.eo - l2.cw, this.ep - l2.cx - this.eq, f20, f21, be);
            y2.a(this.eo - l2.cw, this.ep - l2.cx - this.eq, f20, f21, bd);
            y2.a(f20, f21, 5.0f, bd);
        } else if (this.M) {
            this.N = com.corrodinggames.rts.gameFramework.f.a(this.N, f2);
            if (this.O == null) {
                this.O = new float[20];
                this.N = 0.0f;
            }
            if (this.N == 0.0f) {
                this.N = 4.0f;
                for (n2 = 0; n2 < this.O.length; ++n2) {
                    this.O[n2] = com.corrodinggames.rts.gameFramework.f.c(-10.0f, 10.0f);
                }
            }
            float f22 = this.eo - l2.cw;
            float f23 = this.ep - l2.cx - this.eq;
            float f24 = f5 - l2.cw;
            float f25 = f4 - f3 - l2.cx;
            float f26 = com.corrodinggames.rts.gameFramework.f.c(f22, f23, f24, f25);
            int n3 = this.O.length;
            if (f26 < 200.0f) {
                n3 = com.corrodinggames.rts.gameFramework.f.b(0, n3 - 5);
            } else if (f26 < 100.0f) {
                n3 = com.corrodinggames.rts.gameFramework.f.b(0, n3 - 10);
            }
            float f27 = f26 / (float)(n3 - 1);
            float f28 = com.corrodinggames.rts.gameFramework.f.d(f22, f23, f24, f25);
            float f29 = f22;
            float f30 = f23;
            float f31 = com.corrodinggames.rts.gameFramework.f.k(f28);
            float f32 = com.corrodinggames.rts.gameFramework.f.j(f28);
            for (int i2 = 0; i2 < n3; ++i2) {
                float f33 = this.O[i2];
                float f34 = f22 + f31 * (float)i2 * f27;
                float f35 = f23 + f32 * (float)i2 * f27;
                if (i2 != n3 - 1) {
                    f34 -= f32 * f33;
                    f35 += f31 * f33;
                }
                y2.a(f29, f30, f34, f35, bg);
                f29 = f34;
                f30 = f35;
            }
        } else if (this.P != -1) {
            Object object;
            com.corrodinggames.rts.gameFramework.m.e e2 = b;
            int n4 = 20;
            int n5 = 20;
            if (this.R == 1) {
                e2 = d;
                n4 = 60;
                n5 = 60;
            } else if (this.R == 2) {
                e2 = c;
                n4 = 20;
                n5 = 20;
            }
            if (g2.C != null) {
                object = g2.C;
                int n6 = g2.C.p;
                int n7 = g2.C.q;
                int n8 = 0;
                com.corrodinggames.rts.gameFramework.utility.y.a((com.corrodinggames.rts.gameFramework.m.e)object, f6, f7, 0.0f, this.aT, this.x, bc, n6, n7, n8);
            } else if (this.Q != -1 && this.z) {
                object = e2;
                int n9 = n4;
                int n10 = n5;
                com.corrodinggames.rts.gameFramework.utility.y.a((com.corrodinggames.rts.gameFramework.m.e)object, f6, f7, 0.0f, this.aT, this.x, bc, n9, n10, this.Q);
            }
            if (g2.B != null) {
                e2 = g2.B;
                n4 = g2.B.p;
                n5 = g2.B.q;
            }
            object = this.f();
            com.corrodinggames.rts.gameFramework.utility.y.a(e2, f6, f7, this.eq, this.aT, this.x, (Paint)object, n4, n5, this.P);
        } else {
            bb.b(this.ar);
            if (this.eq > 0.0f && this.z) {
                y2.a(f6, f7, this.x, bc);
            }
            y2.a(f6, f7 - this.eq, this.x, bb);
            if (this.y > 0.0f) {
                bb.c(bb.f() / 3);
                y2.a(f6, f7 - this.eq, this.y, bb);
            }
        }
        return true;
    }

    @Override
    public void a(float f2, boolean bl2) {
    }

    @Override
    public void d(float f2) {
    }

    @Override
    public void e(float f2) {
    }

    @Override
    public boolean f(float f2) {
        return false;
    }

    public Paint f() {
        Paint paint;
        if (this.ar != aq) {
            if (com.corrodinggames.rts.gameFramework.l.at()) {
                paint = this.a(this.ar);
            } else {
                paint = bb;
                paint.b(this.ar);
            }
        } else {
            paint = ba;
        }
        return paint;
    }

    public ag a(int n2) {
        if (this.bj != null) {
            return this.bj;
        }
        if (bk != null && bl == n2) {
            this.bj = bk;
            return this.bj;
        }
        ag ag2 = new ag();
        ag2.a(new LightingColorFilter(n2, 0));
        ag2.b(n2);
        bk = ag2;
        bl = n2;
        this.bj = ag2;
        return this.bj;
    }

    public void a(float f2, float f3, h h2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (this.j == null) {
            com.corrodinggames.rts.gameFramework.l.b("Projectile: cannot Retarget: source==null");
        } else {
            float f4 = this.eo + com.corrodinggames.rts.gameFramework.f.k(this.az) * f3;
            float f5 = this.ep + com.corrodinggames.rts.gameFramework.f.j(this.az) * f3;
            float f6 = f2;
            float f7 = f6 * f6;
            float f8 = -1.0f;
            com.corrodinggames.rts.game.units.y y2 = null;
            am am2 = null;
            if (this.j instanceof com.corrodinggames.rts.game.units.y) {
                y2 = (com.corrodinggames.rts.game.units.y)this.j;
                am2 = y2.ab();
            }
            for (am am3 : l2.cc.a(f4, f5, f6)) {
                if (this.j.bX == am3.bX) continue;
                boolean bl2 = true;
                if (y2 != null) {
                    bl2 = y2.b(am3, true);
                }
                if (bl2 && this.k >= 0 && y2 != null && this.k < y2.bl() && !y2.a(this.k, am3, true, false)) {
                    bl2 = false;
                }
                if (h2 != null && !com.corrodinggames.rts.game.units.custom.g.a(h2, am3.de())) {
                    bl2 = false;
                }
                if (!bl2) continue;
                float f9 = com.corrodinggames.rts.gameFramework.f.a(f4, f5, am3.eo, am3.ep);
                boolean bl3 = false;
                if (f8 == -1.0f || f9 < f8) {
                    bl3 = true;
                }
                if (am2 == am3) {
                    bl3 = true;
                }
                if (!bl3 || !(f9 < f7)) continue;
                f8 = f9;
                this.l = am3;
            }
        }
    }

    static {
        bd = new Paint();
        be = new Paint();
        bf = new Paint();
        bg = new Paint();
        bh = new Paint();
        bc = new ag();
        bc.b(-16777216);
        bc.c(108);
        bd.a(80, 255, 0, 0);
        bd.a(true);
        bd.a(5.0f);
        be.a(30, 255, 0, 0);
        be.a(true);
        be.a(8.0f);
        bf.a(80, 128, 166, 255);
        bf.a(true);
        bf.a(5.0f);
        bg.a(150, 224, 239, 255);
        bg.a(true);
        bg.a(3.0f);
        bh.a(110, 224, 239, 255);
        bh.a(true);
        bh.a(8.0f);
        bi = new u();
        bk = null;
        bl = 0;
    }
}
