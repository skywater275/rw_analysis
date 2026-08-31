/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  android.graphics.PorterDuff$Mode
 *  android.view.Menu
 *  android.view.MenuItem
 */
package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint$Align;
import android.graphics.Paint$Style;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.Menu;
import android.view.MenuItem;
import com.corrodinggames.rts.R$drawable;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.a.d;
import com.corrodinggames.rts.game.units.a.h;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.t;
import com.corrodinggames.rts.game.units.a.u;
import com.corrodinggames.rts.game.units.a.z;
import com.corrodinggames.rts.game.units.aa;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.ad;
import com.corrodinggames.rts.gameFramework.bq;
import com.corrodinggames.rts.gameFramework.e;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.f.am;
import com.corrodinggames.rts.gameFramework.f.c;
import com.corrodinggames.rts.gameFramework.f.g;
import com.corrodinggames.rts.gameFramework.f.i;
import com.corrodinggames.rts.gameFramework.f.v;
import com.corrodinggames.rts.gameFramework.i.b;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.utility.m;
import com.corrodinggames.rts.gameFramework.w;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class a
extends bq {
    g a;
    l b;
    public boolean c = false;
    public boolean d = false;
    public boolean e;
    public float f;
    Paint g = new Paint();
    Paint h = new Paint();
    Paint i = new Paint();
    Paint j = new Paint();
    Paint k = new Paint();
    Paint l = new Paint();
    Paint m = new Paint();
    ag n;
    ag o;
    Paint p = new Paint();
    Paint q;
    Paint r;
    Rect s = new Rect();
    RectF t = new RectF();
    Rect u = new Rect();
    Rect v = new Rect();
    Rect w = new Rect();
    RectF x = new RectF();
    RectF y = new RectF();
    Rect z = new Rect();
    RectF A = new RectF();
    Rect B = new Rect();
    RectF C = new RectF();
    boolean D;
    float E;
    float F;
    float G;
    int H;
    boolean I;
    float J;
    float K;
    float L;
    float M;
    float N;
    float O;
    int P;
    float Q;
    float R;
    com.corrodinggames.rts.gameFramework.m.e S = null;
    com.corrodinggames.rts.gameFramework.m.e T = null;
    com.corrodinggames.rts.gameFramework.m.e U = null;
    com.corrodinggames.rts.gameFramework.m.e V = null;
    com.corrodinggames.rts.gameFramework.m.e W = null;
    com.corrodinggames.rts.gameFramework.m.e X = null;
    static Paint Y = new Paint();
    static PorterDuffColorFilter Z = new PorterDuffColorFilter(Color.a(200, 255, 200), PorterDuff.Mode.MULTIPLY);
    com.corrodinggames.rts.game.units.am aa;
    s ab;
    float ac;
    long ad;
    float ae;
    float af;
    String ag;
    String ah;
    String ai;
    String aj;
    String ak;
    public String al;
    String am = null;
    float an = 0.0f;
    public float ao;
    public boolean ap;
    ArrayList aq = new ArrayList();
    com.corrodinggames.rts.game.units.a.y ar = new com.corrodinggames.rts.game.units.a.y(false);
    com.corrodinggames.rts.game.units.a.y as = new com.corrodinggames.rts.game.units.a.y(true);
    d at = new d();
    ArrayList au = new ArrayList();
    ArrayList av = new ArrayList();
    m aw = new m();
    ArrayList ax = new ArrayList();
    RectF ay = new RectF();
    HashMap az = new HashMap();
    ArrayList aA = new ArrayList();
    Rect aB = new Rect();
    float aC;
    v aD = new v();

    a(l l2, g g2) {
        this.a = g2;
        this.b = l2;
        this.b();
    }

    public void a() {
        this.ag = com.corrodinggames.rts.gameFramework.h.a.a("gui.unselectall", new Object[0]);
        this.ah = com.corrodinggames.rts.gameFramework.h.a.a("gui.common.allyUnit", new Object[0]);
        this.ai = com.corrodinggames.rts.gameFramework.h.a.a("gui.common.enemyUnit", new Object[0]);
        this.aj = com.corrodinggames.rts.gameFramework.h.a.a("gui.common.neutralUnit", new Object[0]);
        this.ak = com.corrodinggames.rts.gameFramework.h.a.a("gui.infoText.ownedBy", new Object[0]);
        this.al = com.corrodinggames.rts.gameFramework.h.a.a("gui.infoText.unitCapReached", new Object[0]);
    }

    public void b() {
        this.a();
        this.S = this.b.bO.a(R$drawable.zoom_button);
        this.T = this.b.bO.a(R$drawable.lock_icon_menu);
        this.U = this.b.bO.a(R$drawable.pause);
        this.V = this.b.bO.a(R$drawable.replay_pause);
        this.W = this.b.bO.a(R$drawable.fast);
        this.X = this.b.bO.a(R$drawable.replay_leaderboard);
        Y.a(255, 30, 30, 30);
        Y.a(Z);
        Y.d(true);
        this.q = new Paint();
        this.q.a(255, 255, 255, 255);
        this.q.a(Paint$Align.a);
        this.q.c(true);
        this.q.a(true);
        this.r = new Paint();
        this.r.a(255, 255, 255, 255);
        this.r.a(Paint$Align.a);
        this.r.c(true);
        this.r.a(true);
        this.n = new ag();
        this.n.b(Color.a(190, 255, 255, 255));
        this.n.o();
        this.o = new ag();
        this.o.b(Color.a(133, 255, 255, 255));
        this.o.o();
        this.aA.clear();
        for (int j = 0; j < 10; ++j) {
            this.aA.add(new am(this, j < 3));
        }
    }

    private float p() {
        float f2 = 4.6f / this.b.cY;
        if (f2 > 4.6f) {
            f2 = 4.6f;
        }
        return f2;
    }

    private float q() {
        return this.r() / this.b.cY;
    }

    private float r() {
        if (this.b.cF / this.b.bL.i() < this.b.cH / this.b.bL.j()) {
            return this.b.cF / this.b.bL.i();
        }
        return this.b.cH / this.b.bL.j();
    }

    void a(float f2) {
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        if (this.b.bQ.showZoomButton) {
            f7 = this.b.cj * 0.7f;
            int n2 = (int)(50.0f * f7);
            int n3 = (int)this.b.cp;
            f6 = com.corrodinggames.rts.gameFramework.l.a.c();
            if (f6 > 20.0f) {
                n2 = (int)((float)n2 + (f6 - 20.0f));
            }
            if (this.D) {
                this.s.a(n2 - 4, (int)((float)n3 - 50.0f * this.b.cj), n2 + 4, (int)((float)n3 + 50.0f * this.b.cj));
                this.i.a();
                this.i.b(Color.a(255, 0, 0, 0));
                this.b.bO.b(this.s, this.i);
            }
            float f8 = n3;
            if (this.b.cV > 1.0f) {
                f8 -= (this.b.cV - 1.0f) * 3.0f * this.b.cj;
            } else {
                f5 = 20.0f;
                f8 += (this.b.cV * -f5 + f5 + 1.0f) * this.b.cj;
            }
            f5 = 48.0f * f7;
            f4 = 54.0f * f7;
            float f9 = f5 / 2.0f;
            f3 = f4 / 2.0f;
            if (f8 < f3) {
                f8 = f3;
            }
            if (f8 > this.b.cm - f3) {
                f8 = (int)(this.b.cm - f3);
            }
            this.s.a((int)((float)n2 - f9), (int)(f8 - f3), (int)((float)n2 + f9), (int)(f8 + f3));
            if (!this.D) {
                Y.a(140, 215, 215, 215);
            } else {
                Y.a(255, 255, 255, 255);
            }
            this.b.bO.a(this.S, (float)this.s.a, (float)this.s.b, Y, 0.0f, f7);
            boolean bl = this.D;
            if (!this.D && this.a.b(this.s.a, this.s.b, this.s.b(), this.s.c(), com.corrodinggames.rts.gameFramework.f.i.b)) {
                this.D = true;
                this.E = this.a.y;
            }
            if (!this.a.I) {
                this.D = false;
            }
            if (this.D) {
                this.F += f2;
                this.a.d();
                float f10 = this.a.y - this.E;
                if (f10 > 180.0f) {
                    f10 = 180.0f;
                }
                if (f10 < -180.0f) {
                    f10 = -180.0f;
                }
                if ((f10 *= this.b.cV) > 2.0f) {
                    this.b.cV -= 5.0E-4f * com.corrodinggames.rts.gameFramework.f.c(f10) * f2;
                    this.b.cW = false;
                    if (this.b.cV < this.q()) {
                        this.b.cV = this.q();
                        this.b.cW = true;
                    }
                } else if (f10 < -2.0f) {
                    this.b.cV += 5.0E-4f * com.corrodinggames.rts.gameFramework.f.c(f10) * f2;
                    this.b.cW = false;
                    if (this.b.cV > this.p()) {
                        this.b.cV = this.p();
                        this.b.cW = true;
                    }
                }
            } else {
                if (!bl || this.F < 12.0f) {
                    // empty if block
                }
                this.F = 0.0f;
            }
        }
        if (this.b.bQ.mouseSupport) {
            if (this.a.a(this.b.af(), this.b.ag()) && !this.a.L) {
                int n4 = this.b.ai();
                if (n4 != 0) {
                    this.G += (float)n4 / 120.0f * 0.18f;
                }
                if (this.G > 1.0f) {
                    this.G = 1.0f;
                }
                if (this.G < -1.0f) {
                    this.G = -1.0f;
                }
            }
            if (this.G != 0.0f) {
                f7 = 0.0032f * f2;
                if (this.G < 0.0f) {
                    f7 = -f7;
                }
                float f11 = this.G;
                this.G = com.corrodinggames.rts.gameFramework.f.a(this.G, com.corrodinggames.rts.gameFramework.f.c(f7 += this.G * 0.18f * f2));
                if (this.G == 0.0f) {
                    f7 = f11;
                }
                this.b.cV += (f7 *= this.b.cV);
                this.b.cZ = true;
                this.b.da = this.b.af();
                this.b.db = this.b.ag();
                if (f7 != 0.0f) {
                    this.b.cW = false;
                }
            }
        }
        if (this.b.bQ.gestureZoom && this.b.ac() && this.b.ae() >= 3) {
            this.R = 20.0f;
        }
        if (this.R < 10.0f) {
            this.I = false;
        }
        if (this.R > 0.0f) {
            this.R = com.corrodinggames.rts.gameFramework.f.a(this.R, f2);
            boolean bl = this.b.ac() && this.b.ae() >= 3;
            this.a.aU = 3.0f;
            float f12 = 0.0f;
            float f13 = 0.0f;
            f6 = 0.0f;
            if (bl) {
                int n5;
                for (n5 = 0; n5 < this.b.ae(); ++n5) {
                    f12 += this.b.b(n5);
                    f13 += this.b.c(n5);
                }
                f12 /= (float)this.b.ae();
                f13 /= (float)this.b.ae();
                f6 = 0.0f;
                for (n5 = 0; n5 < this.b.ae(); ++n5) {
                    f5 = this.b.b(n5);
                    f4 = this.b.c(n5);
                    f6 += com.corrodinggames.rts.gameFramework.f.b(f12, f13, f5, f4);
                }
            } else {
                f12 = this.M;
                f13 = this.N;
                f6 = this.O;
            }
            if (this.I && this.P != this.b.ae()) {
                this.I = false;
            }
            if (!this.I && bl) {
                this.I = true;
                this.J = f12;
                this.K = f13;
                this.L = f6;
                this.Q = this.b.cV;
                this.M = f12;
                this.N = f13;
                this.O = f6;
                this.P = this.b.ae();
            }
            if (bl) {
                float f14 = this.N - f13;
                f14 *= 2.0f;
                this.b.cV += (f14 *= this.b.cV) / 250.0f / this.b.cj;
                this.b.cW = false;
                f5 = this.O - f6;
                boolean bl2 = false;
                if (bl2) {
                    this.b.cV -= f5 / 350.0f / this.b.cj;
                    this.b.cW = false;
                }
                this.M = f12;
                this.N = f13;
                this.O = f6;
                this.P = this.b.ae();
                for (int i2 = 0; i2 < this.b.ae(); ++i2) {
                    f3 = this.b.b(i2);
                    float f15 = this.b.c(i2);
                    this.b.bO.a(f12, f13, f3, f15, this.a.aN);
                }
                float f16 = 6.0f;
                this.b.bO.a(f12, f13, f12, this.K, this.a.aO);
                this.b.bO.a(f12, f13, f16, this.a.aN);
            }
        }
        if (this.b.cV > this.p()) {
            this.b.cV = this.p();
            this.b.cW = true;
        }
        if (this.b.cV < this.q()) {
            this.b.cV = this.q();
            this.b.cW = true;
        }
    }

    void b(float f2) {
        int n2;
        int n3;
        boolean bl;
        this.e = false;
        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = false;
        int n4 = 7;
        if (com.corrodinggames.rts.gameFramework.l.aw()) {
            n4 = 14;
        }
        if (this.b.ac() && this.a.ac == null) {
            bl = this.a.c(this.b);
            n3 = 1;
            if (this.b.bQ.mouseOrders == 2) {
                n3 = 2;
            }
            n2 = this.b.f(n3);
            if (bl || this.b.bQ.mouseSupport && n2 != -1 && !this.a.J && !this.a.K) {
                float f3 = this.b.b(0);
                float f4 = this.b.c(0);
                if (n2 != -1) {
                    f3 = this.b.b(n2);
                    f4 = this.b.c(n2);
                }
                if (!this.c) {
                    bl3 = true;
                    this.y.a = (int)f3;
                    this.y.b = (int)f4;
                }
                this.y.c = (int)f3;
                this.y.d = (int)f4;
                if (Math.abs(this.y.a - this.y.c) > (float)n4 || Math.abs(this.y.b - this.y.d) > (float)n4) {
                    this.d = true;
                }
                bl2 = true;
            } else if (this.b.ae() == 2 && this.R == 0.0f) {
                this.y.a = (int)this.b.b(0);
                this.y.b = (int)this.b.c(0);
                this.y.c = (int)this.b.b(1);
                this.y.d = (int)this.b.c(1);
                this.d = false;
                bl2 = true;
            }
            if (bl2) {
                this.f += f2;
                if (this.f < 18.0f) {
                    bl4 = true;
                }
            } else {
                this.f = 0.0f;
            }
            if (bl2) {
                this.c = true;
                if (Math.abs(this.y.a - this.y.c) > (float)n4 || Math.abs(this.y.b - this.y.d) > (float)n4) {
                    this.z.d = (int)this.y.d;
                    this.z.b = (int)this.y.b;
                    this.z.a = (int)this.y.a;
                    this.z.c = (int)this.y.c;
                    com.corrodinggames.rts.gameFramework.f.a(this.z);
                    this.g.b(Color.a(255, 0, 255, 0));
                    this.g.a(Paint$Style.b);
                    this.g.a(1.0f);
                    this.b.bO.b(this.z, this.g);
                    this.e = true;
                }
            }
        }
        bl = false;
        n3 = 0;
        if (this.c && !bl2) {
            if (bl4 && this.b.ae() == 3) {
                n3 = 1;
            } else {
                bl = true;
            }
        }
        if (n3 != 0) {
            this.d = false;
            this.c = false;
        }
        if (bl2 && !bl4 || bl) {
            if (bl3) {
                for (w w2 : com.corrodinggames.rts.gameFramework.w.er) {
                    if (!(w2 instanceof com.corrodinggames.rts.game.units.c)) continue;
                    com.corrodinggames.rts.game.units.c c2 = (com.corrodinggames.rts.game.units.c)w2;
                    c2.cI = c2.cG;
                }
            }
            if (bl) {
                this.d = false;
                this.c = false;
            }
            this.A.a(this.y);
            com.corrodinggames.rts.gameFramework.f.a(this.A);
            if (Math.abs(this.A.a - this.A.c) > (float)n4 || Math.abs(this.A.b - this.A.d) > (float)n4) {
                com.corrodinggames.rts.game.units.c c3;
                this.A.d /= this.b.cX;
                this.A.b /= this.b.cX;
                this.A.a /= this.b.cX;
                this.A.c /= this.b.cX;
                this.A.a(this.b.cu, this.b.cv);
                this.a.aU = 4.0f;
                this.a.aV = 40.0f;
                this.a.U = false;
                n2 = this.a.a(this.b) ? 1 : 0;
                boolean bl5 = this.a.b(this.b);
                boolean bl6 = true;
                boolean bl7 = true;
                boolean bl8 = false;
                if (this.b.bQ.smartSelection_v2) {
                    for (w w3 : com.corrodinggames.rts.gameFramework.w.er) {
                        if (!(w3 instanceof y) || !this.a(c3 = (y)w3) || n2 != 0 && ((y)c3).cI) continue;
                        if (!c3.bI()) {
                            bl6 = false;
                        }
                        if (!((y)c3).aS() || !c3.l()) continue;
                        bl7 = false;
                    }
                }
                if (bl5) {
                    bl6 = true;
                }
                for (w w3 : com.corrodinggames.rts.gameFramework.w.er) {
                    if (!(w3 instanceof com.corrodinggames.rts.game.units.c)) continue;
                    c3 = (com.corrodinggames.rts.game.units.c)w3;
                    boolean bl9 = false;
                    if (this.a(c3)) {
                        bl9 = true;
                        if (!bl6 && c3.bI()) {
                            bl9 = false;
                        }
                        if (!bl7 && c3.ak() && !c3.l()) {
                            bl9 = false;
                        }
                    }
                    if (bl5) {
                        if (bl9) {
                            bl9 = !c3.cI;
                        } else if (c3.cI) {
                            bl9 = true;
                        }
                    } else if (n2 != 0 && c3.cI) {
                        bl9 = true;
                    }
                    if (bl9) {
                        this.a.j(c3);
                        if (!bl || c3.cH + 900 >= this.b.bA || (n2 != 0 || bl5) && c3.cI) continue;
                        bl8 = true;
                        continue;
                    }
                    this.a.l(c3);
                }
                if (bl8) {
                    for (w w3 : com.corrodinggames.rts.gameFramework.w.er) {
                        if (!(w3 instanceof com.corrodinggames.rts.game.units.c)) continue;
                        c3 = (com.corrodinggames.rts.game.units.c)w3;
                        c3.cH = this.b.bA;
                    }
                }
                this.a.E();
            }
        }
    }

    private boolean a(com.corrodinggames.rts.game.units.c c2) {
        if (!c2.bV && c2.cN == null) {
            float f2 = c2.eo;
            float f3 = c2.ep - c2.eq;
            if (f3 <= 0.0f) {
                f3 += c2.eq;
            }
            if (this.A.b(f2, f3) && (this.a.m(c2) || this.b.bs.b()) && !c2.t()) {
                return true;
            }
        }
        return false;
    }

    public void a(String string, int n2) {
        this.am = string;
        this.an = n2;
    }

    public void b(String string, int n2) {
        if (this.an <= 0.0f || string.equals(this.am)) {
            this.am = string;
            this.an = n2;
        }
    }

    public void a(String string) {
        if (this.an > 0.0f && string.equals(this.am)) {
            this.an = 0.0f;
        }
    }

    public void c(float f2) {
        if (this.an > 0.0f && this.am != null) {
            this.an = com.corrodinggames.rts.gameFramework.f.a(this.an, f2);
            this.b.bO.a(this.am, this.b.co, this.b.cp, this.a.aD, this.a.aI, 8.0f);
        }
    }

    public static boolean a(s s2) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        return l2.ar && s2.n_();
    }

    public void c() {
        this.H = 0;
    }

    public ad a(s s2, int n2, ArrayList arrayList) {
        t t2;
        ad[] adArray2;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (!com.corrodinggames.rts.gameFramework.l.av()) {
            return null;
        }
        if (s2.M() != null) {
            return s2.M();
        }
        if (s2 instanceof com.corrodinggames.rts.game.units.a.n) {
            return null;
        }
        if (s2 instanceof d) {
            return null;
        }
        if (s2.f() == com.corrodinggames.rts.game.units.a.t.b) {
            return l2.bT.T;
        }
        if (s2.e() == com.corrodinggames.rts.game.units.a.u.m) {
            return l2.bT.Q;
        }
        if (s2.e() == com.corrodinggames.rts.game.units.a.u.l) {
            return l2.bT.P;
        }
        if (s2.e() == com.corrodinggames.rts.game.units.a.u.e) {
            return l2.bT.R;
        }
        if (s2.f() == com.corrodinggames.rts.game.units.a.t.c) {
            int n3 = 0;
            for (ad[] adArray2 : arrayList) {
                if (adArray2 == s2 || adArray2.f() != com.corrodinggames.rts.game.units.a.t.c || !this.a.b((s)adArray2)) continue;
                ++n3;
            }
            if (n3 == 0) {
                return l2.bT.S;
            }
        }
        if ((t2 = s2.f()) == com.corrodinggames.rts.game.units.a.t.g || t2 == com.corrodinggames.rts.game.units.a.t.h || t2 == com.corrodinggames.rts.game.units.a.t.i) {
            return null;
        }
        Object object = null;
        adArray2 = l2.bT.ag;
        if (this.H < adArray2.length) {
            object = adArray2[this.H];
            ++this.H;
        }
        return object;
    }

    public ArrayList d() {
        this.au.clear();
        com.corrodinggames.rts.game.units.am[] amArray = this.a.bZ.a();
        int n2 = this.a.bZ.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            com.corrodinggames.rts.game.units.am am2 = amArray[i2];
            as as2 = am2.r();
            if (this.au.contains(as2)) continue;
            this.au.add(as2);
        }
        return this.au;
    }

    public ArrayList a(com.corrodinggames.rts.game.units.am am2, ArrayList arrayList) {
        Object object3;
        Iterator iterator;
        Object object2;
        int n2;
        int n3;
        int n4 = 0;
        this.aq.clear();
        int n5 = this.a.q();
        if (n5 == 0) {
            if (this.b.bQ.showChatAndPingShortcuts && this.b.M()) {
                this.aq.add(n4, this.a.q);
                this.aq.add(n4, this.a.r);
            }
            return this.aq;
        }
        if (com.corrodinggames.rts.gameFramework.f.g.bO && am2 != null && !(am2 instanceof com.corrodinggames.rts.game.units.h)) {
            this.aq.add(this.ar);
            this.aq.add(this.as);
        }
        if (am2 == null) {
            // empty if block
        }
        if (am2 != null) {
            n4 = this.aq.size();
            if (am2.cG) {
                ArrayList arrayList2;
                if (this.a.m(am2)) {
                    arrayList2 = am2.N();
                    if (arrayList2 != null) {
                        this.aq.addAll(arrayList2);
                    }
                } else {
                    arrayList2 = am2.N();
                    if (arrayList2 != null) {
                        this.aq.addAll(arrayList2);
                    }
                }
            }
            n3 = arrayList.size();
            for (n2 = 0; n2 < n3; ++n2) {
                com.corrodinggames.rts.game.units.am am3 = (com.corrodinggames.rts.game.units.am)arrayList.get(n2);
                if (!this.a.m(am3) || am3.r() == am2.r() && am3.V() == am2.V() || (object2 = am3.N()) == null) continue;
                iterator = ((ArrayList)object2).iterator();
                while (iterator.hasNext()) {
                    object3 = (s)iterator.next();
                    boolean bl = false;
                    for (s s2 : this.aq) {
                        if (!s2.N().equals(((s)object3).N())) continue;
                        bl = true;
                    }
                    if (bl) continue;
                    this.aq.add(object3);
                }
            }
        }
        n2 = 0;
        int n6 = arrayList.size();
        for (n3 = 0; n3 < n6; ++n3) {
            object2 = (com.corrodinggames.rts.game.units.am)arrayList.get(n3);
            if (!this.a.m((com.corrodinggames.rts.game.units.am)object2) || !(object2 instanceof y) || ((y)((Object)(iterator = (y)object2))).aS()) continue;
            n2 = 1;
        }
        com.corrodinggames.rts.game.units.am am4 = this.e();
        if (n2 == 0 && am4 != null && this.a.m(am4)) {
            this.aq.add(n4, this.a.m);
            this.aq.add(n4, this.a.n);
        }
        n6 = 0;
        if (com.corrodinggames.rts.gameFramework.f.g.bO && (this.b.bQ.showSelectedUnitsList || n5 == 1)) {
            n6 = 1;
        }
        if (com.corrodinggames.rts.gameFramework.l.at() && n5 > 0) {
            n6 = 1;
        }
        if (n6 != 0 && !(am2 instanceof com.corrodinggames.rts.game.units.h)) {
            if (n5 == 1 && am4 != null && (object2 = am4.e(true)) != null && ((m)object2).size() > 0) {
                for (int i2 = 0; i2 < ((m)object2).a; ++i2) {
                    object3 = (s)((m)object2).get(i2);
                    if (!(object3 instanceof com.corrodinggames.rts.game.units.a.g)) continue;
                    com.corrodinggames.rts.game.units.a.g g2 = (com.corrodinggames.rts.game.units.a.g)object3;
                    for (s s2 : this.aw) {
                        if (!((com.corrodinggames.rts.game.units.a.g)s2).a(g2)) continue;
                        ((m)object2).set(i2, s2);
                    }
                }
                this.aw.clear();
                iterator = ((m)object2).iterator();
                while (iterator.hasNext()) {
                    object3 = (s)iterator.next();
                    if (object3 instanceof com.corrodinggames.rts.game.units.a.g) {
                        this.aw.add((com.corrodinggames.rts.game.units.a.g)object3);
                    }
                    this.aq.add(object3);
                }
            }
            object2 = this.d();
            this.av.clear();
            iterator = ((ArrayList)object2).iterator();
            while (iterator.hasNext()) {
                object3 = (as)iterator.next();
                z z2 = object3.d();
                z2.K();
                this.av.add(z2);
            }
            Collections.sort(this.av);
            if (com.corrodinggames.rts.gameFramework.f.g.bO) {
                Collections.reverse(this.av);
            }
            for (Object object3 : this.av) {
                if (com.corrodinggames.rts.gameFramework.f.g.bO) {
                    this.aq.add(0, object3);
                    continue;
                }
                this.aq.add(object3);
            }
        }
        return this.aq;
    }

    com.corrodinggames.rts.game.units.am e() {
        if (this.a.bZ.size() > 0) {
            return this.a.bZ.a(0);
        }
        return null;
    }

    com.corrodinggames.rts.game.units.am f() {
        com.corrodinggames.rts.game.units.am am2 = null;
        if (this.a.aX > 0) {
            com.corrodinggames.rts.game.units.am[] amArray = this.a.bZ.a();
            int n2 = this.a.bZ.size();
            for (int i2 = 0; i2 < n2; ++i2) {
                com.corrodinggames.rts.game.units.am am3 = amArray[i2];
                if (!am3.cG) continue;
                if (am2 == null) {
                    am2 = am3;
                    continue;
                }
                if (!com.corrodinggames.rts.gameFramework.f.a.a(am2, am3)) {
                    am2 = null;
                    break;
                }
                if (am2.V() <= am3.V()) continue;
                am2 = am3;
            }
        }
        return am2;
    }

    public static boolean a(com.corrodinggames.rts.game.units.am am2, com.corrodinggames.rts.game.units.am am3) {
        as as2;
        as as3 = am2.r();
        if (as3 == (as2 = am3.r())) {
            return true;
        }
        if (as3 instanceof com.corrodinggames.rts.game.units.custom.l && as2 instanceof com.corrodinggames.rts.game.units.custom.l) {
            com.corrodinggames.rts.game.units.custom.l l2 = (com.corrodinggames.rts.game.units.custom.l)as3;
            com.corrodinggames.rts.game.units.custom.l l3 = (com.corrodinggames.rts.game.units.custom.l)as2;
            if (l2.fL.contains(as2)) {
                return true;
            }
            if (l2.fO != null && com.corrodinggames.rts.game.units.custom.g.a(l2.fO, l3.x())) {
                return true;
            }
            if (l3.fO != null && com.corrodinggames.rts.game.units.custom.g.a(l3.fO, l2.x())) {
                return true;
            }
        }
        return false;
    }

    ArrayList g() {
        this.ax.clear();
        com.corrodinggames.rts.game.units.am[] amArray = this.a.bZ.a();
        int n2 = this.a.bZ.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            com.corrodinggames.rts.game.units.am am2 = amArray[i2];
            if (!(am2 instanceof y)) continue;
            this.ax.add((y)am2);
        }
        return this.ax;
    }

    float h() {
        float f2 = this.b.cm / 14.0f / this.b.cj;
        f2 = com.corrodinggames.rts.gameFramework.f.b(f2, 25.0f * this.b.cj, 40.0f * this.b.cj);
        return f2;
    }

    private boolean c(s s2) {
        if (s2.s()) {
            return true;
        }
        if (s2 instanceof com.corrodinggames.rts.game.units.a.g) {
            com.corrodinggames.rts.game.units.a.g g2 = (com.corrodinggames.rts.game.units.a.g)s2;
            return this.a.m(g2.b);
        }
        ArrayList arrayList = this.g();
        com.corrodinggames.rts.game.units.a.c c2 = s2.N();
        for (y y2 : arrayList) {
            s s3 = y2.a(c2);
            if (s3 == null || !this.a.m(y2)) continue;
            return true;
        }
        return false;
    }

    private boolean a(s s2, ArrayList arrayList) {
        h h2 = null;
        if (s2 instanceof h) {
            h2 = (h)s2;
        }
        if (h2 != null && h2.d == com.corrodinggames.rts.gameFramework.f.g.cd) {
            return h2.e;
        }
        boolean bl2 = this.b(s2, arrayList);
        if (h2 != null) {
            h2.d = com.corrodinggames.rts.gameFramework.f.g.cd;
            h2.e = bl2;
        }
        return bl2;
    }

    private boolean b(s s2, ArrayList arrayList) {
        if (s2.s()) {
            return true;
        }
        if (s2 instanceof com.corrodinggames.rts.game.units.a.g) {
            com.corrodinggames.rts.game.units.a.g g2 = (com.corrodinggames.rts.game.units.a.g)s2;
            if (!g2.r(g2.b)) {
                return false;
            }
            return this.a.m(g2.b) || g2.a((com.corrodinggames.rts.game.units.am)g2.b, this.b.bs);
        }
        com.corrodinggames.rts.game.units.a.c c2 = s2.N();
        for (y y2 : arrayList) {
            s s3 = y2.a(c2);
            if (s3 == null || !s3.r(y2) || !this.a.m(y2) && !s3.a((com.corrodinggames.rts.game.units.am)y2, this.b.bs)) continue;
            return true;
        }
        return false;
    }

    private boolean c(s s2, ArrayList arrayList) {
        Object object;
        if (s2.s()) {
            return true;
        }
        if (s2 instanceof com.corrodinggames.rts.game.units.a.g && ((com.corrodinggames.rts.game.units.a.g)(object = (com.corrodinggames.rts.game.units.a.g)s2)).a((com.corrodinggames.rts.game.units.am)((com.corrodinggames.rts.game.units.a.g)object).b, true)) {
            return true;
        }
        for (y y2 : arrayList) {
            s s3 = y2.a(s2.N());
            if (s3 == null || !s3.a((com.corrodinggames.rts.game.units.am)y2, true)) continue;
            return true;
        }
        return false;
    }

    private float d(s s2, ArrayList arrayList) {
        int n2 = 0;
        float f2 = -1.0f;
        if (s2.o_()) {
            return -1.0f;
        }
        for (y y2 : arrayList) {
            float f3;
            s s3 = y2.a(s2.N());
            if (s3 == null || !((f3 = s3.p(y2)) > f2)) continue;
            f2 = f3;
            ++n2;
        }
        return f2;
    }

    private com.corrodinggames.rts.game.units.g.e d(s s2) {
        float f2 = -1.0f;
        Object object = null;
        if (s2.o_()) {
            return null;
        }
        if (s2 instanceof com.corrodinggames.rts.game.units.a.g) {
            com.corrodinggames.rts.game.units.a.g g2 = (com.corrodinggames.rts.game.units.a.g)s2;
            Object object2 = com.corrodinggames.rts.game.units.g.e.b(g2.b, s2.N());
            if (object2 != null) {
                if (f2 < (float)((com.corrodinggames.rts.game.units.g.a)object2).a()) {
                    f2 = ((com.corrodinggames.rts.game.units.g.a)object2).a();
                    object = object2;
                }
            } else {
                return null;
            }
        }
        for (Object object2 : this.a.bZ) {
            y y2;
            s s3;
            if (!(object2 instanceof y) || (s3 = (y2 = (y)object2).a(s2.N())) == null) continue;
            com.corrodinggames.rts.game.units.g.e e2 = com.corrodinggames.rts.game.units.g.e.b(y2, s2.N());
            if (e2 != null) {
                if (!(f2 < (float)e2.a())) continue;
                f2 = e2.a();
                object = e2;
                continue;
            }
            return null;
        }
        if (object == null) {
            return null;
        }
        return object;
    }

    private float e(s s2) {
        com.corrodinggames.rts.game.units.g.e e2 = this.d(s2);
        if (e2 == null) {
            return 0.0f;
        }
        return e2.c();
    }

    float b(s s2) {
        com.corrodinggames.rts.game.units.g.e e2 = this.d(s2);
        if (e2 == null) {
            return 0.0f;
        }
        return e2.d();
    }

    /*
     * Could not resolve type clashes
     */
    int d(float f2) {
        boolean bl2;
        this.ap = false;
        int n2 = 1;
        if (com.corrodinggames.rts.gameFramework.f.g.bP) {
            n2 = 2;
        }
        boolean bl3 = false;
        int n3 = 0;
        boolean bl4 = false;
        com.corrodinggames.rts.gameFramework.f.c.a(f2);
        ArrayList arrayList = this.g();
        com.corrodinggames.rts.game.units.am am2 = this.f();
        ArrayList arrayList2 = null;
        if (this.a.ac != null) {
            arrayList2 = this.a.ac.q(am2);
        }
        ArrayList arrayList3 = arrayList2 != null ? arrayList2 : this.a(am2, arrayList);
        if (am2 == null && arrayList3.size() > 0 && (am2 = this.e()) == null && com.corrodinggames.rts.game.units.custom.l.b != null) {
            am2 = com.corrodinggames.rts.game.units.am.c(com.corrodinggames.rts.game.units.custom.l.b);
        }
        this.a.t = false;
        if (arrayList3.contains(this.a.m)) {
            this.a.t = true;
        }
        if (am2 == null) {
            am2 = this.e();
        }
        boolean bl5 = true;
        if (am2 == null) {
            this.ad = -1L;
        }
        if (am2 != null && (bl2 = arrayList3.size()) > false) {
            float f3;
            int n4;
            boolean bl6;
            float f4;
            float f5;
            float f6;
            int n5;
            float f7;
            float f8;
            float f9;
            ArrayList arrayList4 = arrayList3;
            float f10 = 2.0f;
            float f11 = this.h();
            float f12 = 2.0f;
            float f13 = f11 + f12;
            boolean bl7 = false;
            if (!com.corrodinggames.rts.gameFramework.f.g.bR) {
                f9 = this.b.bW.b() + 2;
                f8 = this.b.cl - this.b.bW.c;
                f7 = this.b.bW.c;
            } else {
                f9 = this.b.bW.b;
                f8 = this.b.bW.c;
                f7 = this.b.bW.c;
                bl7 = true;
            }
            if (com.corrodinggames.rts.gameFramework.f.g.bO) {
                f11 += 15.0f * this.b.cj;
                f13 += 15.0f * this.b.cj;
                f10 = 2.0f * this.b.cj;
                if (com.corrodinggames.rts.gameFramework.l.au()) {
                    f10 = 2.0f * this.b.cj;
                }
                f13 += 2.0f;
                f12 += 2.0f;
                f9 += 3.0f;
            }
            if (!com.corrodinggames.rts.gameFramework.f.g.a) {
                n5 = 1;
                if (this.a.f != null && this.a.aX == 1 && this.a.f.cG) {
                    n5 = 0;
                }
                if (n5 != 0) {
                    f6 = this.i();
                    f9 += f6;
                    f9 += 2.0f;
                }
            }
            n5 = 0;
            f6 = 0.0f;
            float f14 = 0.0f;
            float f15 = 0.0f;
            for (s s2 : arrayList4) {
                if (!this.a(s2, arrayList)) continue;
                ++n5;
                f5 = f13 * s2.l();
                int n6 = n2;
                if (s2.m() > 0) {
                    n6 = s2.m();
                }
                f4 = f7 / (float)n6;
                bl6 = false;
                if (f14 + f4 - 0.1f >= f7) {
                    bl6 = true;
                }
                if (!bl6 && f6 > 0.0f && f5 + 0.1f < f6) {
                    bl6 = true;
                }
                if (bl6) {
                    f15 += f6;
                    f6 = 0.0f;
                    f14 = 0.0f;
                }
                if (f6 < f5) {
                    f6 = f5;
                }
                f14 += f4;
            }
            if (f14 > 0.0f) {
                f15 += f6;
            }
            float f16 = f9 + f15;
            float f17 = f9;
            f9 += 1.0f;
            f5 = this.b.bQ.showUnitGroups ? this.b.cH - 34.0f * this.b.cj : this.b.cH;
            this.ad = am2.eh;
            f9 -= (float)((int)am2.br);
            float f18 = 0.0f;
            f4 = 1.0f + f11 * 0.25f;
            bl6 = f16 - am2.br > f5 + f4;
            boolean bl8 = am2.br > f4;
            boolean bl9 = this.ap = bl6 || bl8;
            if (this.b.bQ.mouseSupport && !this.a.a(this.b.af(), this.b.ag()) && (n4 = this.b.ai()) != 0) {
                f18 = -((float)n4 / 120.0f);
            }
            float f19 = 0.0f;
            if (f18 > 0.0f) {
                this.ao = (float)((double)this.ao + 0.5 * (double)f13);
            }
            if (f18 < 0.0f) {
                this.ao = (float)((double)this.ao - 0.5 * (double)f13);
            }
            if (bl6) {
                f3 = 0.4f;
                this.s.a = (int)(f8 + 2.0f);
                this.s.c = (int)(f8 + f7 - 2.0f);
                this.s.b = (int)(f5 - f11 * f3);
                this.s.d = (int)((float)this.s.b + f11 * f3);
                if (this.a.a(this.s.a, this.s.b, this.s.b(), this.s.c(), "\\/", com.corrodinggames.rts.gameFramework.f.i.a, false, Color.a(80, 100, 150, 100), this.a.aC, null) && this.a.J()) {
                    f19 += 3.0f * f13;
                    this.a.U = false;
                }
                f5 -= f13 * f3 + 2.0f;
            }
            if (bl8) {
                f3 = 0.4f;
                this.s.a = (int)(f8 + 2.0f);
                this.s.c = (int)(f8 + f7 - 2.0f);
                this.s.b = (int)f17;
                this.s.d = (int)((float)this.s.b + f11 * f3);
                if (this.a.a(this.s.a, this.s.b, this.s.b(), this.s.c(), "/\\", com.corrodinggames.rts.gameFramework.f.i.a, false, Color.a(80, 100, 150, 100), this.a.aC, null) && this.a.J()) {
                    f19 -= 3.0f * f13;
                    this.a.U = false;
                }
                f17 += f13 * f3 + 2.0f;
            }
            this.b.bO.i();
            this.ay.a(0.0f, f17 - 1.0f, this.b.cl, f5 + 1.0f);
            this.b.bO.a(this.ay);
            if (com.corrodinggames.rts.gameFramework.l.au()) {
                if (this.ad != am2.eh) {
                    this.ae = 0.0f;
                    this.af = am2.br;
                } else if (this.ao != 0.0f) {
                    this.ae = this.ao;
                } else {
                    if (!this.a.I) {
                        this.ao += this.ae * f2;
                    }
                    this.ae = com.corrodinggames.rts.gameFramework.f.a(this.ae, f2);
                }
            }
            am2.br += this.ao + f19;
            this.ao = 0.0f;
            f3 = 0.0f;
            int n7 = (int)(f16 - f5);
            if (n7 > 0) {
                if (am2.br > (float)n7 + f3) {
                    am2.br = (float)n7 + f3;
                }
                if (am2.br < 0.0f - f3) {
                    am2.br = 0.0f - f3;
                }
            } else {
                am2.br = 0.0f;
            }
            bl3 = true;
            int n8 = -1;
            float f20 = 0.0f;
            f6 = 0.0f;
            f14 = 0.0f;
            this.c();
            for (s s3 : arrayList4) {
                Object object;
                int n9;
                int n10;
                boolean bl10;
                int n11;
                int n12;
                int n13;
                Object object2;
                ad ad2;
                float f21;
                ag ag2;
                float f22;
                float f23;
                if (!this.a(s3, arrayList)) continue;
                ++n3;
                boolean bl11 = this.c(s3, arrayList);
                ++n8;
                float f24 = f11 * s3.l();
                int n14 = n2;
                if (s3.m() > 0) {
                    n14 = s3.m();
                }
                float f25 = f7 / (float)n14;
                if (!bl7) {
                    f23 = f24;
                    f22 = f25;
                } else {
                    f23 = f25;
                    f22 = f24;
                }
                boolean bl12 = false;
                if (f14 + f22 - 0.1f > f7) {
                    bl12 = true;
                }
                if (!bl12 && f6 > 0.0f && f23 + 0.1f < f6) {
                    bl12 = true;
                }
                if (bl12) {
                    f20 += f6 + f12;
                    f6 = 0.0f;
                    f14 = 0.0f;
                }
                if (f6 < f23) {
                    f6 = f23;
                }
                if (!bl7) {
                    this.s.a = (int)(f8 + f10);
                    this.s.c = (int)((float)this.s.a + f25 - f10 * 2.0f);
                    this.s.b = (int)(f20 + f9);
                    this.s.d = (int)((float)this.s.b + f24);
                    this.s.a((int)f14, 0);
                } else {
                    this.s.a = (int)(f8 + f10 + f20);
                    this.s.c = (int)((float)this.s.a + f25 - f10 * 2.0f);
                    this.s.b = (int)f9;
                    this.s.d = (int)((float)this.s.b + f24);
                    this.s.a(0, (int)f14);
                }
                boolean bl13 = true;
                this.t.a(this.s);
                if (!this.t.b(this.ay)) {
                    bl13 = false;
                }
                f14 += f22;
                t t2 = s3.f();
                boolean bl14 = false;
                if (t2 == com.corrodinggames.rts.game.units.a.t.g || t2 == com.corrodinggames.rts.game.units.a.t.h || t2 == com.corrodinggames.rts.game.units.a.t.i) {
                    bl14 = true;
                }
                boolean bl15 = bl11;
                boolean bl16 = com.corrodinggames.rts.gameFramework.f.a.a(s3);
                boolean bl17 = s3.G();
                Paint paint = this.j;
                boolean bl18 = bl15;
                if (t2 == com.corrodinggames.rts.game.units.a.t.i) {
                    bl18 = true;
                }
                if (bl18) {
                    paint.b(Color.a(70, 100, 100, 100));
                } else {
                    paint.b(Color.a(50, 170, 100, 100));
                }
                if (bl16) {
                    paint.b(Color.a(100, 180, 100, 100));
                }
                boolean bl19 = false;
                boolean bl20 = false;
                if (this.aa == am2 && this.ab == s3) {
                    bl19 = true;
                }
                if (this.a.ac == s3) {
                    bl19 = true;
                    bl20 = true;
                }
                if (bl19) {
                    paint.b(Color.a(80, 100, 100, 200));
                }
                if (bl20) {
                    paint.b(Color.a(80, 100, 200, 100));
                }
                if (bl17) {
                    paint.c((int)((float)paint.f() * 0.7f));
                    ag2 = this.o;
                } else {
                    ag2 = this.n;
                }
                float f26 = 0.0f;
                if (bl13) {
                    f26 = com.corrodinggames.rts.gameFramework.f.c.b(am2, s3, false);
                    if (s3.f() != com.corrodinggames.rts.game.units.a.t.h) {
                        boolean bl21;
                        boolean bl22 = this.a.a(s3);
                        float f27 = 0.0f;
                        if (bl22) {
                            f21 = (float)(com.corrodinggames.rts.gameFramework.l.V() % 1000L) / 1000.0f;
                            f27 = com.corrodinggames.rts.gameFramework.f.c(com.corrodinggames.rts.gameFramework.f.k(f21 * 180.0f));
                        }
                        if (f26 != 0.0f) {
                            f21 = com.corrodinggames.rts.gameFramework.f.c(f26) * 0.7f - 0.3f;
                            f21 = com.corrodinggames.rts.gameFramework.f.b(f21, 0.0f, 1.0f);
                            int n15 = f26 > 0.0f ? Color.a(110, 210, 210, 210) : Color.a(110, 210, 110, 110);
                            bl21 = com.corrodinggames.rts.gameFramework.f.a(n15, paint.e(), f21);
                            paint = this.i;
                            paint.b(bl21 ? 1 : 0);
                        }
                        this.a.a(this.s, paint, ag2);
                        f21 = this.d(s3, arrayList);
                        if (f21 >= 0.0f) {
                            this.l.a(80, 0, 0, 100);
                            this.B.a(this.s);
                            this.B.c = (int)((float)this.B.c - (1.0f - f21) * (float)this.B.b());
                            this.b.bO.b(this.B, this.l);
                            this.m.a(190, 148, 189, 255);
                            this.b.bO.a(this.B.c, (float)this.B.b, (float)this.B.c, (float)this.B.d, this.l);
                        } else {
                            float f28 = this.e(s3);
                            if (f28 > 0.0f) {
                                this.l.a(80, 100, 0, 0);
                                this.B.a(this.s);
                                this.B.c = (int)((float)this.B.c - (1.0f - f28) * (float)this.B.b());
                                this.b.bO.b(this.B, this.l);
                                this.m.a(190, 148, 189, 255);
                                this.b.bO.a(this.B.c, (float)this.B.b, (float)this.B.c, (float)this.B.d, this.l);
                            }
                        }
                        int n16 = Color.a(255, 0, 0, 0);
                        if (com.corrodinggames.rts.gameFramework.f.g.bO) {
                            n16 = Color.a(100, 0, 0, 0);
                            if (bl17) {
                                n16 = Color.a(50, 155, 155, 155);
                            }
                        }
                        bl21 = false;
                        if (bl22) {
                            bl21 = true;
                            n16 = Color.a((int)(100.0f + 150.0f * f27), 255, 255, 255);
                        }
                        this.a.a(this.s, n16, bl21);
                    }
                }
                if ((ad2 = this.a(s3, n8, arrayList4)) != null && bl13) {
                    String string = ad2.c();
                    f21 = this.b.bO.a("A", this.a.az);
                    this.b.bO.a(string, (float)(this.s.a + 3), (float)this.s.b + f21 + 1.0f, this.a.az);
                }
                boolean bl23 = false;
                as as2 = s3.i();
                com.corrodinggames.rts.gameFramework.m.e e2 = s3.j();
                com.corrodinggames.rts.game.units.am am3 = s3.i(am2);
                if (am3 != null) {
                    as2 = am3.r();
                }
                if (e2 == null && as2 != null) {
                    e2 = as2.z();
                }
                if (e2 != null) {
                    object2 = s3.v();
                    if (object2 == null) {
                        object2 = this.B;
                        ((Rect)object2).a(0, 0, e2.m(), e2.l());
                    }
                    float f29 = (float)this.s.c() * 0.7f / (float)((Rect)object2).c();
                    n13 = (int)((float)this.s.d() - (float)((Rect)object2).b() * 0.5f * f29);
                    n12 = (int)((float)this.s.e() - (float)((Rect)object2).c() * 0.5f * f29);
                    this.p.a(100, 255, 255, 255);
                    RectF rectF = this.C;
                    rectF.a(n13, n12, (float)n13 + (float)((Rect)object2).b() * f29, (float)n12 + (float)((Rect)object2).c() * f29);
                    this.b.bO.a(e2, (Rect)object2, rectF, this.p);
                    bl23 = true;
                } else if (as2 != null) {
                    float f30 = this.s.d();
                    float f31 = this.s.e();
                    if ((double)f26 > 0.5) {
                        f31 += 1.0f;
                    }
                    if ((double)f26 < -0.5) {
                        f31 -= 1.0f;
                    }
                    float f32 = (float)this.s.c() * 0.7f;
                    float f33 = (float)this.s.c() * 0.95f;
                    if (com.corrodinggames.rts.gameFramework.f.g.bO) {
                        f32 = (float)this.s.c() * 0.4f;
                        f33 = (float)this.s.c() * 0.85f;
                    }
                    this.x.a(this.s);
                    if (this.x.b(this.ay)) {
                        this.b.bO.i();
                        this.b.bO.a(this.x);
                        com.corrodinggames.rts.game.units.ar.a(as2, f30, f31, 0.0f, 0.0f, am2.bX, f32, f33, false, false, s3.t(), am3);
                        if (am3 != null) {
                            int n17;
                            boolean bl24;
                            int n18;
                            int n19;
                            Paint paint2;
                            float f34 = am3.x();
                            float f35 = am3.bV();
                            if (f35 != -1.0f && s3.t(am2)) {
                                n11 = 120;
                                bl10 = 200 != 0;
                                n10 = com.corrodinggames.rts.gameFramework.f.b(200, 0, 0, 150);
                                n9 = com.corrodinggames.rts.gameFramework.f.b(120, 0, 0, 230);
                                object = com.corrodinggames.rts.gameFramework.utility.y.a(n10, Paint$Style.a);
                                paint2 = com.corrodinggames.rts.gameFramework.utility.y.a(n9, Paint$Style.b);
                                n19 = 3;
                                n18 = (int)(this.x.b() / 3.0f) - 3;
                                bl24 = false;
                                n17 = n18 * 2;
                                this.C.a(f30 - (float)n18, f31 + (float)bl24, f30 - (float)n18 + (float)n17 * f35, f31 + (float)bl24 + (float)n19);
                                this.b.bO.a(this.C, (Paint)object);
                                this.C.a(f30 - (float)n18, f31 + (float)bl24, f30 - (float)n18 + (float)n17, f31 + (float)bl24 + (float)n19);
                                this.b.bO.a(this.C, paint2);
                            } else if (f34 != -1.0f && s3.s(am2)) {
                                n11 = 120;
                                int n20 = 200;
                                n10 = com.corrodinggames.rts.gameFramework.f.b(200, 0, 150, 0);
                                n9 = com.corrodinggames.rts.gameFramework.f.b(120, 0, 230, 0);
                                object = com.corrodinggames.rts.gameFramework.utility.y.a(n10, Paint$Style.a);
                                paint2 = com.corrodinggames.rts.gameFramework.utility.y.a(n9, Paint$Style.b);
                                n19 = 3;
                                n18 = (int)(this.x.b() / 3.0f) - 3;
                                bl24 = false;
                                n17 = n18 * 2;
                                this.C.a(f30 - (float)n18, f31 + (float)bl24, f30 - (float)n18 + (float)n17 * f34, f31 + (float)bl24 + (float)n19);
                                this.b.bO.a(this.C, (Paint)object);
                                this.C.a(f30 - (float)n18, f31 + (float)bl24, f30 - (float)n18 + (float)n17, f31 + (float)bl24 + (float)n19);
                                this.b.bO.a(this.C, paint2);
                            }
                        }
                        this.b.bO.j();
                    }
                    bl23 = true;
                }
                object2 = s3.h(am2);
                if (object2 != null) {
                    Rect rect = s3.v();
                    if (rect == null) {
                        rect = this.B;
                        rect.a(0, 0, ((com.corrodinggames.rts.gameFramework.m.e)object2).m(), ((com.corrodinggames.rts.gameFramework.m.e)object2).l());
                    }
                    float f36 = (float)this.s.c() * 0.7f / (float)rect.c();
                    n12 = (int)((float)this.s.d() - (float)rect.b() * 0.5f * f36);
                    int n21 = (int)((float)this.s.e() - (float)rect.c() * 0.5f * f36);
                    this.p.b(s3.J());
                    RectF rectF = this.C;
                    rectF.a(n12, n21, (float)n12 + (float)rect.b() * f36, (float)n21 + (float)rect.c() * f36);
                    this.b.bO.a((com.corrodinggames.rts.gameFramework.m.e)object2, rect, rectF, this.p);
                    bl23 = true;
                }
                if (bl13) {
                    float f37;
                    String string = s3.d();
                    if (bl16) {
                        this.b.bO.a(this.T, (float)(this.s.a + 25), this.s.g(), null);
                    }
                    if ((f37 = (float)this.b.bO.b(string, this.a.aC)) > (float)(this.s.b() - 2)) {
                        float f38 = this.b.bO.b(string, this.a.aB);
                        if (f38 > (float)(this.s.b() - 2)) {
                            this.i.a(this.a.aA);
                        } else {
                            this.i.a(this.a.aB);
                        }
                    } else {
                        this.i.a(this.a.aC);
                    }
                    if (!bl18) {
                        this.i.b(Color.a(255, 0, 100, 0));
                    }
                    if (t2 == com.corrodinggames.rts.game.units.a.t.b) {
                        this.i.a(255, 255, 255, 255);
                    } else if (t2 == com.corrodinggames.rts.game.units.a.t.c || t2 == com.corrodinggames.rts.game.units.a.t.f) {
                        if (!bl18) {
                            this.i.a(255, 19, 101, 94);
                        } else {
                            this.i.a(255, 39, 202, 189);
                        }
                    } else if (t2 == com.corrodinggames.rts.game.units.a.t.d) {
                        as as3 = s3.i();
                        if (as3 != null && as3.g() > 1) {
                            if (!bl18) {
                                this.i.a(255, 117, 120, 15);
                            } else {
                                this.i.a(255, 235, 240, 30);
                            }
                        }
                    } else if (bl14) {
                        this.i.a(155, 255, 255, 255);
                    }
                    n12 = this.b.bO.a(string, this.i);
                    float f39 = this.s.g() + (float)(n12 / 2);
                    if (bl14) {
                        f39 = this.s.g();
                    }
                    if (bl23 && !string.contains("\n")) {
                        f39 = bl14 ? (float)(this.s.d - n12 / 2 - 1) : (float)(this.s.d - 6);
                    }
                    if (bl14) {
                        com.corrodinggames.rts.gameFramework.m.aa.a(string, this.s.f(), f39, this.i);
                    } else {
                        this.b.bO.a(string, this.s.f(), f39, this.i);
                    }
                }
                boolean bl25 = false;
                n13 = 0;
                n12 = 0;
                if (ad2 != null && ad2.a()) {
                    bl25 = true;
                    n12 = 1;
                }
                this.u.a(this.s);
                if (com.corrodinggames.rts.gameFramework.l.au()) {
                    com.corrodinggames.rts.gameFramework.f.b(this.u, 2.0f);
                }
                this.a.a((float)this.u.a, (float)this.u.b, (float)this.u.b(), this.u.c());
                if (!this.d && this.u.b((int)this.a.z, (int)this.a.A) && this.ay.b((int)this.a.z, (int)this.a.A)) {
                    bl4 = true;
                    if (com.corrodinggames.rts.gameFramework.l.av()) {
                        n13 = 1;
                    }
                    if ((this.a.U || this.a.I) && this.a.U && this.a.J()) {
                        this.a.U = false;
                        bl25 = true;
                    }
                }
                if (com.corrodinggames.rts.gameFramework.l.av() && this.a.ac == null) {
                    if (n13 != 0) {
                        this.aa = am2;
                        this.ab = s3;
                        this.ac = f20 + f9;
                    } else if (com.corrodinggames.rts.game.units.a.s.a(this.ab, s3)) {
                        this.aa = null;
                        this.ab = null;
                    }
                }
                boolean bl26 = false;
                if (bl25 && n12 == 0 && this.b.bQ.mouseSupport && this.b.e(2)) {
                    bl26 = true;
                }
                if (bl25) {
                    com.corrodinggames.rts.gameFramework.f.g.K();
                    if (s3.c(am2, bl26)) {
                        bl25 = false;
                    }
                    if (this.b.cb.j()) {
                        bl25 = false;
                    }
                    if (!this.c(s3)) {
                        bl25 = false;
                    }
                }
                if (bl25) {
                    if (s3.e() == com.corrodinggames.rts.game.units.a.u.a || s3.e() == com.corrodinggames.rts.game.units.a.u.c) {
                        this.a.ac = null;
                        boolean bl27 = false;
                        if (n12 != 0) {
                            bl27 = true;
                        } else if (!s3.u()) {
                            bl27 = true;
                        } else {
                            if (s3.k(am2)) {
                                bl27 = true;
                            } else if (this.aa == am2 && com.corrodinggames.rts.game.units.a.s.a(this.ab, s3)) {
                                bl27 = true;
                            }
                            this.aa = am2;
                            this.ab = s3;
                            this.ac = f20 + f9;
                        }
                        if (bl27) {
                            n11 = 1;
                            if (s3.g()) {
                                if (this.a.a(this.b)) {
                                    n11 = 5;
                                }
                                if (this.a.b(this.b)) {
                                    n11 = 10;
                                }
                            }
                            bl10 = false;
                            if (n12 == 0) {
                                n10 = 0;
                                if (am2 != null && s3.b(am2, false) != -1) {
                                    n10 = 1;
                                }
                                if (bl26 && n10 != 0) {
                                    bl10 = true;
                                }
                            }
                            if (com.corrodinggames.rts.gameFramework.f.a.a(s3)) {
                                this.b.bM.b(com.corrodinggames.rts.gameFramework.a.e.l, 0.8f);
                            } else if (!bl15 && !bl10) {
                                this.b.bM.b(com.corrodinggames.rts.gameFramework.a.e.l, 0.8f);
                            } else {
                                n10 = s3.g();
                                if (n10 != 0 && !bl10 && this.b.bs.x() <= this.b.bs.w()) {
                                    this.a.b(this.al);
                                }
                                if (n10 != 0) {
                                    if (!bl10) {
                                        this.b.bM.b(com.corrodinggames.rts.gameFramework.a.e.h, 0.5f);
                                    } else {
                                        this.b.bM.b(com.corrodinggames.rts.gameFramework.a.e.i, 0.5f);
                                    }
                                } else {
                                    this.b.bM.b(com.corrodinggames.rts.gameFramework.a.e.g, 0.8f);
                                }
                                com.corrodinggames.rts.gameFramework.f.c.a(am2, s3, bl10, false);
                                for (n9 = 0; n9 < n11; ++n9) {
                                    object = this.a.x();
                                    if (!s3.I()) {
                                        this.a.a((e)object, s3);
                                    } else {
                                        this.a.a((e)object, s3, bl10);
                                    }
                                    if (bl10) {
                                        ((e)object).g = true;
                                    }
                                    ((e)object).a(s3.z());
                                    if (bl10) continue;
                                    this.a.a(s3, null, null, (e)object);
                                }
                            }
                        }
                    } else if (s3.e() == com.corrodinggames.rts.game.units.a.u.m || s3.e() == com.corrodinggames.rts.game.units.a.u.l || s3.e() == com.corrodinggames.rts.game.units.a.u.j) {
                        if (bl26) {
                            if (s3 != null && s3.equals(this.a.ac)) {
                                this.a.l();
                            }
                        } else if (!bl15) {
                            this.b.bM.b(com.corrodinggames.rts.gameFramework.a.e.l, 0.8f);
                        } else {
                            com.corrodinggames.rts.gameFramework.f.c.a(am2, s3, false, false);
                            this.aa = null;
                            this.ab = null;
                            this.a.ac = s3;
                        }
                    } else if (s3.e() == com.corrodinggames.rts.game.units.a.u.d || s3.e() == com.corrodinggames.rts.game.units.a.u.e || s3.e() == com.corrodinggames.rts.game.units.a.u.f || s3.e() == com.corrodinggames.rts.game.units.a.u.g) {
                        boolean bl28 = false;
                        n11 = 0;
                        if (s3.e() == com.corrodinggames.rts.game.units.a.u.g) {
                            n11 = 1;
                        }
                        if (bl26 && n11 != 0) {
                            bl28 = true;
                        }
                        if (bl28) {
                            e e3 = this.a.x();
                            if (!s3.I()) {
                                this.a.a(e3, s3);
                            } else {
                                this.a.a(e3, s3, bl28);
                            }
                            e3.g = true;
                            e3.a(s3.z());
                        } else {
                            com.corrodinggames.rts.gameFramework.l.e("Clicked button: actionActive: " + bl15);
                            if (!bl15) {
                                this.b.bM.b(com.corrodinggames.rts.gameFramework.a.e.l, 0.8f);
                            } else {
                                com.corrodinggames.rts.gameFramework.f.c.a(am2, s3, false, false);
                                this.aa = null;
                                this.ab = null;
                                this.a.ac = s3;
                            }
                        }
                    } else if (s3.e() == com.corrodinggames.rts.game.units.a.u.b) {
                        if (com.corrodinggames.rts.gameFramework.f.a.a(s3)) {
                            this.b.bM.b(com.corrodinggames.rts.gameFramework.a.e.l, 0.8f);
                        } else if (!bl15) {
                            this.b.bM.b(com.corrodinggames.rts.gameFramework.a.e.l, 0.8f);
                        } else {
                            this.b.bM.b(com.corrodinggames.rts.gameFramework.a.e.g, 0.8f);
                        }
                        com.corrodinggames.rts.gameFramework.f.c.a(am2, s3, false, false);
                        this.aa = null;
                        this.ab = null;
                        if (this.a.ac == null) {
                            this.a.ai = false;
                        }
                        this.a.aa = am2;
                        this.a.ac = s3;
                        this.a.af = 0.0f;
                        this.a.aq = -99.0f;
                        this.a.ar = -99.0f;
                        if (!this.a.ae) {
                            this.a.ag = this.b.cI * this.b.cX;
                            this.a.ah = this.b.cJ * this.b.cX;
                        }
                        this.a.ae = true;
                        this.b.bL.e();
                    } else if (s3.e() == com.corrodinggames.rts.game.units.a.u.k) {
                        com.corrodinggames.rts.gameFramework.f.c.a(am2, s3, false, false);
                        s3.c(am2);
                    } else if (s3.e() == com.corrodinggames.rts.game.units.a.u.i) {
                        if (s3.C()) {
                            this.aa = am2;
                            this.ab = s3;
                            this.ac = f20 + f9;
                            this.a.ac = null;
                        }
                    } else {
                        throw new RuntimeException("unknown gui action:" + (Object)((Object)s3.e()));
                    }
                }
                if (this.ab != s3) continue;
                bl5 = bl11;
            }
            this.b.bO.j();
            this.ay.f();
        }
        if (am2 != null && am2 == this.aa) {
            if (this.ab != null) {
                bl2 = true;
                if (com.corrodinggames.rts.gameFramework.l.av()) {
                    bl2 = false;
                }
                boolean bl29 = false;
                if (this.ab.u()) {
                    bl29 = true;
                }
                if (com.corrodinggames.rts.gameFramework.l.av() && this.ab.h()) {
                    bl29 = true;
                }
                if (bl29) {
                    boolean bl30 = true;
                    if (!bl5) {
                        bl30 = false;
                    }
                    if (this.a.a(this.ab, bl2, this.aa, !bl30, true, this.ac, false)) {
                        this.aa = null;
                    }
                }
            }
        } else {
            this.aa = null;
        }
        if (com.corrodinggames.rts.gameFramework.l.av() && !bl4) {
            this.aa = null;
            this.ab = null;
        }
        return n3;
    }

    float i() {
        float f2 = this.b.cm / 14.0f / this.b.cj;
        f2 = com.corrodinggames.rts.gameFramework.f.b(f2, 25.0f * this.b.cj, 40.0f * this.b.cj);
        f2 = (float)((double)f2 * 0.9);
        return f2;
    }

    void a(float f2, int n2) {
        boolean bl2 = true;
        if (n2 == 0) {
            bl2 = true;
        }
        if (com.corrodinggames.rts.gameFramework.f.g.a) {
            bl2 = false;
        }
        if (this.a.aX > 0) {
            float f3;
            int n3;
            if (this.a.f != null && this.a.aX == 1 && this.a.f.cG) {
                bl2 = false;
            }
            if (bl2) {
                float f4 = this.i();
                if (this.a.b((int)(this.b.cl - this.b.bW.c + 2.0f), this.b.bW.b() + 2, (int)(this.b.bW.c - 4.0f), (int)f4, this.ag, com.corrodinggames.rts.gameFramework.f.i.c, false, Color.a(140, 100, 100, 100)) && !this.a.T) {
                    this.a.d();
                    this.a.l();
                    this.a.y();
                }
            }
            n n4 = null;
            boolean bl3 = false;
            this.az.clear();
            com.corrodinggames.rts.game.units.am am2 = null;
            com.corrodinggames.rts.game.units.am[] amArray = this.a.bZ.a();
            int n5 = this.a.bZ.size();
            for (n3 = 0; n3 < n5; ++n3) {
                com.corrodinggames.rts.game.units.am am3 = amArray[n3];
                if (!am3.cG) continue;
                am2 = am3;
                if (this.a.m(am3)) {
                    as as2 = am3.r();
                    Integer n6 = (Integer)this.az.get(as2);
                    if (n6 == null) {
                        this.az.put(as2, 1);
                    } else {
                        this.az.put(as2, n6 + 1);
                    }
                    bl3 = true;
                    continue;
                }
                n4 = am3.bX;
            }
            n3 = this.b.bv ? 1 : 0;
            if (n4 != null && this.b.bs != null && n4.b(this.b.bs)) {
                n3 = 1;
            }
            n5 = (int)this.h();
            int n7 = n5 + 2;
            int n8 = (int)(10.0f * this.b.cj);
            float f5 = f3 = (float)(this.b.bW.b() + n5 + 30);
            float f6 = this.b.cl - this.b.cq + (float)n8;
            f5 += 5.0f;
            if (am2 != null) {
                f5 += (float)n7;
                f5 += (float)(n7 * n2);
                if (this.a.t) {
                    f5 -= (float)(2 * n7) * 0.4f;
                }
            }
            this.s.a((int)f6, (int)f5, (int)(f6 + this.b.cq - (float)(n8 * 2)), (int)(f5 + (float)n5));
            boolean bl4 = false;
            if (!com.corrodinggames.rts.gameFramework.f.g.bQ) {
                Object object;
                Object object2;
                if (n2 < 3 && !bl3 && n4 != null) {
                    object2 = this.a.aF;
                    if (this.b.bs.d(n4)) {
                        object2 = this.a.aG;
                    }
                    object = this.a(n4);
                    this.a.a((String)object, this.s, (Paint)object2, (Paint)object2);
                    bl4 = true;
                }
                if (this.a.q() == 1 && am2 != null && (am2.cq() <= 3 || n4 != null && n3 == 0)) {
                    object2 = this.a(am2, false);
                    if (bl4) {
                        object2 = "\n" + (String)object2;
                        object2 = "\n" + (String)object2;
                        object2 = "\n" + (String)object2;
                    }
                    object = this.i;
                    ((Paint)object).a();
                    ((Paint)object).b(Color.a(50, 100, 100, 100));
                    this.a.a((String)object2, this.s, this.a.aH, this.a.aH);
                }
            }
        }
    }

    public String a(n n2) {
        String string = "";
        boolean bl2 = false;
        if (this.b.bs.b()) {
            bl2 = true;
        } else if (this.b.bs.d(n2)) {
            string = string + this.ah;
        } else if (this.b.bs.c(n2)) {
            string = string + this.ai;
        } else {
            bl2 = true;
        }
        if (bl2) {
            string = n2 == com.corrodinggames.rts.game.n.i ? string + this.aj : string + "Team - " + n2.h();
        }
        string = string + "\n";
        if (n2.v != null) {
            string = string + n2.v;
        }
        if (!n2.w && this.b.N() && n2.B()) {
            string = string + "\n";
            string = string + "(disconnected)";
        }
        return string;
    }

    public String a(com.corrodinggames.rts.game.units.am am2, boolean bl2) {
        com.corrodinggames.rts.game.units.custom.d.b b2;
        String string = "";
        if (bl2) {
            string = string + am2.r().e() + "\n";
        }
        if (am2.g() > 0.0f) {
            b2 = am2.cM();
            float f2 = am2.cu / am2.cv;
            com.corrodinggames.rts.game.units.custom.d.b b3 = com.corrodinggames.rts.game.units.custom.d.b.a(b2, f2);
            boolean bl3 = false;
            String string2 = b3.a(true, true, 3, bl3);
            string = string + string2;
        } else {
            string = string + (int)Math.ceil(am2.cu) + "/" + (int)am2.cv + "\n";
        }
        if (am2.cA != 0.0f) {
            string = string + "(" + (int)am2.cx + "/" + (int)am2.cA + ")\n";
        }
        b2 = am2.dq();
        com.corrodinggames.rts.game.units.custom.e.f f3 = am2.cz();
        if (b2 != null) {
            f3 = com.corrodinggames.rts.game.units.custom.e.f.d(f3);
            f3.a(b2);
        }
        if (!f3.c()) {
            for (com.corrodinggames.rts.game.units.custom.e.e e2 : f3.b) {
                if (e2.b == 0.0 || e2.a.a()) continue;
                string = string + e2.a.a(e2.b, true, false) + "\n";
            }
        }
        string = com.corrodinggames.rts.gameFramework.f.j(string);
        return string;
    }

    public static String a(s s2, boolean bl2) {
        com.corrodinggames.rts.game.units.a.w w2;
        float f2;
        String string = bl2 ? "\n" : " | ";
        String string2 = "";
        if (s2 instanceof com.corrodinggames.rts.game.units.a.w && (f2 = (w2 = (com.corrodinggames.rts.game.units.a.w)s2).K()) < 1.0f) {
            l l2 = com.corrodinggames.rts.gameFramework.l.B();
            float f3 = -1.0f;
            com.corrodinggames.rts.game.units.am[] amArray = l2.bS.bZ.a();
            int n2 = l2.bS.bZ.size();
            for (int i2 = 0; i2 < n2; ++i2) {
                com.corrodinggames.rts.game.units.am am2 = amArray[i2];
                float f4 = am2.cx();
                if (f3 != -1.0f && !(f4 < f3)) continue;
                f3 = f4;
            }
            if (f3 == -1.0f) {
                f3 = 1.0f;
            }
            float f5 = 1.0f / (w2.K() * f3 * 60.0f) + 1.0E-4f;
            string2 = string2 + com.corrodinggames.rts.gameFramework.f.h(f5) + string;
        }
        string2 = com.corrodinggames.rts.gameFramework.f.a(string2, string);
        return string2;
    }

    public static String a(com.corrodinggames.rts.game.units.am am2, boolean bl2, boolean bl3, boolean bl4) {
        String string;
        com.corrodinggames.rts.game.units.custom.e.f f2;
        int n2;
        float f3;
        String string2 = bl3 ? "\n" : " | ";
        String string3 = "";
        j j2 = null;
        com.corrodinggames.rts.game.units.custom.l l2 = null;
        if (am2 instanceof j) {
            j2 = (j)am2;
            l2 = j2.x;
        }
        if (bl2) {
            string3 = string3 + am2.r().e() + string2;
        }
        if (l2 == null || !l2.aO) {
            string3 = !bl4 ? string3 + "HP: " + (int)Math.ceil(am2.cu) + "/" + (int)am2.cv + string2 : string3 + "HP: " + (int)am2.cv + string2;
        }
        if (am2.cA != 0.0f) {
            string3 = !bl4 ? string3 + "Shield: " + (int)am2.cx + "/" + (int)am2.cA + string2 : string3 + "Shield: " + (int)am2.cA + string2;
        }
        if (j2 != null && (f3 = j2.y.l) >= 1.0f) {
            string3 = string3 + "Armour: " + (int)f3 + string2;
        }
        com.corrodinggames.rts.game.units.custom.d.b b2 = am2.dq();
        float f4 = am2.cy();
        if (b2 != null) {
            f4 += (float)b2.a();
        }
        if (f4 != 0.0f) {
            string3 = f4 < 0.0f ? string3 + "Income: -$" + com.corrodinggames.rts.gameFramework.f.a(-f4, 1) + string2 : string3 + "Income: +$" + com.corrodinggames.rts.gameFramework.f.a(f4, 1) + string2;
        }
        if (am2 instanceof y) {
            ArrayList arrayList;
            y y2 = (y)am2;
            if (y2.bd() != 0.0f && !bl4) {
                string3 = string3 + "Energy: " + com.corrodinggames.rts.gameFramework.f.g(am2.cB) + "/" + com.corrodinggames.rts.gameFramework.f.g(y2.bd()) + string2;
            }
            float f5 = y2.z();
            if (!y2.aR()) {
                f5 = 0.0f;
            }
            if (f5 != 0.0f) {
                string3 = string3 + "Speed: " + com.corrodinggames.rts.gameFramework.f.g(f5) + string2;
            }
            if (y2.l() && (arrayList = y2.aX()).size() > 0) {
                string3 = string3 + "Attack: ";
                n2 = 1;
                for (aa aa2 : arrayList) {
                    if (n2 == 0) {
                        string3 = string3 + ", ";
                    }
                    n2 = 0;
                    string3 = string3 + com.corrodinggames.rts.gameFramework.f.g(aa2.a);
                    if (aa2.d > 1) {
                        string3 = string3 + "x" + aa2.d;
                    }
                    string3 = string3 + "/" + com.corrodinggames.rts.gameFramework.f.g(aa2.a()) + "s";
                }
                string3 = string3 + string2;
            }
            float f6 = y2.m();
            if (!y2.l()) {
                f6 = 0.0f;
            }
            if (f6 != 0.0f) {
                string3 = string3 + "Range: " + com.corrodinggames.rts.gameFramework.f.g(f6) + string2;
            }
            if (bl4 && y2.ck()) {
                string3 = string3 + "Upgradable" + string2;
            }
        }
        if (!bl4 && am2.cU > 0) {
            string3 = string3 + "Kills: " + am2.cU + string2;
        }
        boolean bl5 = false;
        if (com.corrodinggames.rts.gameFramework.l.B().bl) {
            b b3;
            string3 = string3 + "\n";
            string3 = string3 + "--Debug--" + string2;
            as as2 = am2.r();
            string3 = string3 + "name: " + as2.i() + string2;
            if (as2 instanceof com.corrodinggames.rts.game.units.custom.l && (b3 = ((com.corrodinggames.rts.game.units.custom.l)as2).J) != null) {
                String string4 = b3.a();
                string4 = com.corrodinggames.rts.gameFramework.f.a(string4, 30);
                string3 = string3 + "(mod: " + string4 + ")" + string2;
            }
            if (am2.eh != 0L) {
                string3 = string3 + "id: " + am2.eh + string2;
            }
            if (am2.cF != 0) {
                String string5 = "";
                for (n2 = 0; n2 < 32; ++n2) {
                    if (!com.corrodinggames.rts.game.units.custom.d.b.a(am2.cF, n2)) continue;
                    if (string5.length() > 0) {
                        string5 = string5 + ",";
                    }
                    string5 = string5 + n2;
                }
                string3 = string3 + "flags: " + string5 + string2;
            }
            if (am2.cE != 0) {
                string3 = string3 + "ammo: " + am2.cE + string2;
            }
            if (!am2.cp) {
                string3 = string3 + "x: " + com.corrodinggames.rts.gameFramework.f.g(am2.eo) + string2;
                string3 = string3 + "y: " + com.corrodinggames.rts.gameFramework.f.g(am2.ep) + string2;
            }
            if (am2.cc != 0.0f || am2.cd != 0.0f) {
                string3 = string3 + "x/y speed: " + com.corrodinggames.rts.gameFramework.f.g(am2.cc) + ", " + com.corrodinggames.rts.gameFramework.f.g(am2.cd) + string2;
            }
            if (!am2.cp) {
                string3 = string3 + "height: " + com.corrodinggames.rts.gameFramework.f.g(am2.eq) + string2;
                string3 = string3 + "dir: " + com.corrodinggames.rts.gameFramework.f.g(am2.cg) + string2;
            }
            if (am2.cm < 1.0f) {
                string3 = string3 + "built: " + com.corrodinggames.rts.gameFramework.f.g(am2.cm) + string2;
            }
            if (am2 instanceof j) {
                j j3 = (j)am2;
                string3 = string3 + "frame: " + j3.a + string2;
                string3 = string3 + "drawLayer: " + j3.em + string2;
                if (j3.de() != null) {
                    string3 = string3 + "tags: " + j3.de() + string2;
                }
                if (j3.cO != null) {
                    string3 = string3 + "attachedTo: " + j3.cO.cB() + string2;
                }
                if (j3.bu != null && !j3.bu.bV) {
                    string3 = string3 + "customTarget1: " + j3.bu.cB() + string2;
                }
                if (j3.bv != null && !j3.bv.bV) {
                    string3 = string3 + "customTarget2: " + j3.bv.cB() + string2;
                }
                if (j3.bA != -9999) {
                    string3 = string3 + "customTimer: " + com.corrodinggames.rts.gameFramework.f.h((float)j3.bA / 1000.0f) + string2;
                }
                if (j3.bw != null && !j3.bw.isEmpty()) {
                    string3 = string3 + "-- memory --: " + string2 + j3.bw.debugMemory(true, true) + string2;
                }
            }
            bl5 = true;
        }
        if ((f2 = am2.df()) != null && !f2.c() && !(string = f2.a(bl3, true, 10, bl5, false)).equals("")) {
            string3 = string3 + string + string2;
        }
        string3 = com.corrodinggames.rts.gameFramework.f.a(string3, string2);
        return string3;
    }

    void j() {
        for (am am2 : this.aA) {
            am2.h = true;
        }
    }

    void k() {
        for (am am2 : this.aA) {
            am2.b();
        }
        this.am = null;
        this.an = 0.0f;
    }

    void a(int n2, int n3, int n4, String string, String string2, Paint paint, float f2) {
        int n5 = (int)((double)n4 * 2.5);
        int n6 = (int)(40.0f * this.b.cj);
        int n7 = n2 + n4 / 2;
        int n8 = (int)((float)(n3 - n6) - 35.0f * this.b.cj);
        this.aB.a(n7 - n5 / 2, n8, n5, n6);
        this.a.a(this.aB.a, this.aB.b, this.aB.c, this.aB.d, "", Color.a(180, 100, 100, 100), this.a.aC, false, null, null);
        this.s.a(this.aB.a, this.aB.b, this.aB.c, this.aB.d);
        this.s.c = (int)((float)this.s.c * f2);
        this.b.bO.c(this.s, paint);
        this.b.bO.a(string, (float)n7, (float)n8 + (this.a.aC.k() + 5.0f) * 1.0f, this.a.aC);
        this.b.bO.a(string2, (float)n7, (float)n8 + (this.a.aC.k() + 5.0f) * 2.0f, this.a.aC);
    }

    void a(float f2, boolean bl2) {
        float f3 = this.b.cj * 0.7f;
        if (com.corrodinggames.rts.gameFramework.l.au() && (double)f3 < 0.7) {
            f3 = 0.7f;
        }
        int n2 = this.U.m();
        int n3 = (int)((float)n2 * f3);
        int n4 = 4 + n3 / 2;
        int n5 = 4 + n3 / 2;
        if (this.b.g(111)) {
            boolean bl3 = false;
            if (!this.a.u) {
                bl3 = this.a.l();
            }
            if (!bl3) {
                boolean bl4 = this.a.u = !this.a.u;
            }
        }
        if (this.a.u) {
            this.aC += 0.008f * f2;
            if (this.aC > 1.0f) {
                this.aC = 0.0f;
            }
            float f4 = com.corrodinggames.rts.gameFramework.f.j(this.aC * 180.0f);
            this.h.c(150 + (int)(100.0f * f4));
        } else {
            this.aC = 0.0f;
            this.h.c(80);
        }
        this.v.a(n4, n5, n4 + n3, n5 + n3);
        this.v.a(-(n3 / 2), -(n3 / 2));
        if (bl2) {
            this.b.bO.a(this.U, (float)this.v.a, (float)this.v.b, this.h, 0.0f, f3);
            if (this.b.bQ.newRender) {
                this.B.a(this.v.d() - 4, this.v.e() - 4, this.v.d() + 4, this.v.e() + 4);
                this.p.a(100, 0, 155, 0);
                this.b.bO.b(this.B, this.p);
            }
        }
        if (com.corrodinggames.rts.gameFramework.l.au()) {
            com.corrodinggames.rts.gameFramework.f.a(this.v, 4.0f);
        }
        if (this.a.U && !this.a.T && this.v.b((int)this.a.x, (int)this.a.y)) {
            this.a.U = false;
            this.a.u = !this.a.u;
        }
        this.a.a(this.v);
        if (this.b.cb.j()) {
            com.corrodinggames.rts.gameFramework.g.a a2;
            this.h.c(80);
            if (this.b.cb.v != 1) {
                this.h.c(200);
            }
            n2 = this.W.q;
            n3 = (int)((float)n2 * this.b.cj * 1.6f);
            n4 = (int)(this.b.cF / 2.0f);
            n5 = 7 + (int)this.a.aE.k();
            String string = com.corrodinggames.rts.gameFramework.f.a((long)(this.b.by / 1000));
            this.b.bO.a(string, (float)n4, (float)n5, this.a.aE);
            this.v.a(n4 += n3 / 2 + 5, n5 += n3 / 2 + 10, n4 + n3, n5 + n3);
            this.v.a(-this.v.b() / 2, -this.v.c() / 2);
            if (bl2) {
                this.b.bO.a(this.W, (float)this.v.a, (float)this.v.b, this.h, 0.0f, (float)(n3 / n2));
            }
            if (this.a.U && !this.a.T && this.v.b((int)this.a.x, (int)this.a.y)) {
                this.a.U = false;
                this.b.cb.b();
            }
            if (this.b.bt != 1.0f && bl2) {
                this.b.bO.a("x" + this.b.bt, (float)(this.v.d() + n3 / 2), (float)this.v.e(), this.a.aC);
            }
            com.corrodinggames.rts.gameFramework.m.e e2 = this.V;
            n2 = e2.q;
            n3 = (int)((float)n2 * this.b.cj * 1.6f);
            this.v.a(n4 -= n3 + 5, n5, n4 + n3, n5 + n3);
            this.v.a(-this.v.b() / 2, -this.v.c() / 2);
            if (bl2) {
                this.b.bO.a(e2, (float)this.v.a, (float)this.v.b, this.h, 0.0f, (float)(n3 / n2));
            }
            if (this.a.U && !this.a.T && this.v.b((int)this.a.x, (int)this.a.y)) {
                this.a.U = false;
                this.b.cb.a();
            }
            e2 = this.X;
            n4 = (int)(this.b.cl - this.b.cq - (float)(n3 + 5));
            this.v.a(n4, n5, n4 + n3, n5 + n3);
            this.v.a(-this.v.b() / 2, -this.v.c() / 2);
            if (bl2) {
                this.b.bO.a(e2, (float)this.v.a, (float)this.v.b, this.h, 0.0f, (float)(n3 / n2));
            }
            if (this.a.U && !this.a.T && this.v.b((int)this.a.x, (int)this.a.y) && (a2 = this.b.cg) != null) {
                a2.c();
            }
        }
        if (this.a.u) {
            this.b.cU = false;
            int n6 = this.b.a(190);
            this.s.a = (int)(this.b.cF / 2.0f - (float)(n6 / 2));
            this.s.c = (int)(this.b.cF / 2.0f + (float)(n6 / 2));
            int n7 = this.b.a(34);
            int n8 = n7 + this.b.a(15);
            Menu menu = this.o();
            int n9 = 1 + menu.size();
            int n10 = this.b.a(50) + n8 * n9;
            this.s.b = (int)(this.b.cp - (float)(n10 / 2));
            this.s.d = (int)(this.b.cp + (float)(n10 / 2));
            if (bl2) {
                this.a.bt.c(this.b.bO, this.s);
            }
            int n11 = this.s.b + this.b.a(40);
            int n12 = this.b.a(152);
            int n13 = (int)(this.b.cF / 2.0f - (float)(n12 / 2));
            int n14 = n11;
            int n15 = Color.a(140, 100, 100, 100);
            if (this.a.a(n13, n14, n12, n7, com.corrodinggames.rts.gameFramework.h.a.a("menus.ingame.resume", new Object[0]), com.corrodinggames.rts.gameFramework.f.i.a, false, n15, this.a.aD, this.a.br)) {
                this.a.U = false;
                this.a.aV = 40.0f;
                this.a.u = false;
            }
            n14 += n8;
            for (int i2 = 0; i2 < menu.size(); ++i2) {
                MenuItem menuItem = menu.getItem(i2);
                if (this.a.a(n13, n14, n12, n7, menuItem.getTitle().toString(), com.corrodinggames.rts.gameFramework.f.i.a, false, n15, this.a.aD, this.a.br)) {
                    this.a(menuItem.getItemId());
                    this.a.U = false;
                    this.a.aV = 40.0f;
                }
                n14 += n8;
            }
            this.a.a(this.s);
        }
    }

    public void l() {
        this.a(20);
    }

    public void m() {
        this.a(21);
    }

    public void n() {
        this.a(16);
    }

    void a(int n2) {
        com.corrodinggames.rts.appFramework.f f2 = this.b.ao;
        if (f2 == null) {
            com.corrodinggames.rts.gameFramework.l.b("selectMenuOption: gameView==null");
            return;
        }
        com.corrodinggames.rts.appFramework.g g2 = f2.i();
        if (g2 == null) {
            com.corrodinggames.rts.gameFramework.l.b("selectMenuOption: inGameActivity==null");
            return;
        }
        g2.c(n2);
    }

    Menu o() {
        this.aD.clear();
        com.corrodinggames.rts.appFramework.f f2 = this.b.ao;
        if (f2 == null) {
            com.corrodinggames.rts.gameFramework.l.b("selectMenuOption: gameView==null");
            return this.aD;
        }
        com.corrodinggames.rts.appFramework.g g2 = f2.i();
        if (g2 == null) {
            com.corrodinggames.rts.gameFramework.l.b("selectMenuOption: inGameActivity==null");
            return this.aD;
        }
        g2.a(this.aD);
        return this.aD;
    }

    void e(float f2) {
        int n2;
        float f3 = 30.0f * this.b.cj;
        int n3 = n2 = (int)(this.b.cH - f3);
        int n4 = (int)(this.b.cl - this.b.cq + 10.0f);
        int n5 = (int)(this.b.cq - 20.0f) / 3;
        int n6 = n5 - 5;
        int n7 = 100;
        int n8 = 50;
        for (int i2 = 0; i2 < this.aA.size(); ++i2) {
            am am2 = (am)this.aA.get(i2);
            if (am2.h) {
                am2.e();
                am2.h = false;
            }
            am2.d();
            if (this.b.bQ.keyboardSupport && i2 < this.b.bT.ai.length) {
                if (this.b.bT.ak[i2].a()) {
                    am2.b();
                    am2.c();
                }
                if (this.b.bT.aj[i2].a()) {
                    this.a.l();
                    am2.a();
                }
                if (this.b.bT.ai[i2].a()) {
                    this.a.l();
                    this.a.y();
                    am2.a();
                }
            }
            if (!this.b.bQ.showUnitGroups || i2 >= 3) continue;
            String string = am2.a.size() == 0 ? (this.a.bN ? "Empty" : "(" + (i2 + 1) + ")") : "" + am2.a.size();
            boolean bl2 = false;
            am2.d = com.corrodinggames.rts.gameFramework.f.a(am2.d, 0.01f * f2);
            am2.e = com.corrodinggames.rts.gameFramework.f.a(am2.e, 0.01f * f2);
            am2.f = com.corrodinggames.rts.gameFramework.f.a(am2.f, 0.01f * f2);
            int n9 = Color.a(50, (int)(100.0f + am2.f * 100.0f), (int)(100.0f + am2.e * 100.0f), (int)(100.0f + am2.d * 100.0f));
            if (this.a.a(n4, n3, n6, (int)(31.0f * this.b.cj), string, com.corrodinggames.rts.gameFramework.f.i.a, true, n9) && this.a.ac == null && !this.a.T) {
                bl2 = true;
                am2.b += f2;
                this.a.d();
                float f4 = 1.0f;
                this.i.a();
                this.i.b(Color.a(120, 200, 0, 0));
                if (am2.b < 50.0f) {
                    f4 = am2.b / 50.0f;
                    this.i.b(Color.a((int)(150.0f + f4 * 40.0f), 0, 200, 0));
                    this.a(n4, n3, n6, "Select Group", "(Hold for more..)", this.i, f4);
                } else if (am2.b < 100.0f) {
                    f4 = (am2.b - 50.0f) / 50.0f;
                    this.i.b(Color.a((int)(150.0f + f4 * 40.0f), 200, 0, 0));
                    this.a(n4, n3, n6, "Add to Group", "(Hold for more..)", this.i, f4);
                } else {
                    this.a(n4, n3, n6, "Replace Group", "", this.i, 0.0f);
                }
                int n10 = (int)(31.0f * this.b.cj);
                this.s.a(n4, (int)((float)(n3 + n10) - (float)n10 * f4), n4 + n6, n3 + n10);
                this.b.bO.b(this.s, this.i);
            }
            if (!bl2) {
                if (am2.b != 0.0f && !this.a.I) {
                    if (am2.b > 100.0f) {
                        am2.b();
                        am2.c();
                        am2.f = 1.0f;
                    } else if (am2.b > 50.0f) {
                        am2.c();
                        this.a.l();
                        this.a.y();
                        am2.a();
                        am2.e = 1.0f;
                    } else if (am2.a.size() != 0) {
                        this.a.l();
                        this.a.y();
                        am2.a();
                        am2.d = 1.0f;
                    } else {
                        am2.b();
                        am2.c();
                        am2.e = 1.0f;
                    }
                }
                if (!bl2) {
                    am2.b = 0.0f;
                }
            }
            n4 += n5;
        }
    }

    @Override
    public void a(com.corrodinggames.rts.gameFramework.j.as as2) {
        int n2 = this.aA.size();
        as2.a(n2);
        for (am am2 : this.aA) {
            am2.a(as2);
        }
        as2.c(0);
    }

    public void a(k k2, boolean bl2) {
        if (!bl2) {
            this.aA.clear();
        }
        int n2 = k2.f();
        for (int i2 = 0; i2 < n2; ++i2) {
            am am2 = new am(this, i2 < 3);
            am2.a(k2);
            if (bl2) continue;
            this.aA.add(am2);
        }
        k2.d();
    }
}
