/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.d;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.units.custom.ay;
import com.corrodinggames.rts.gameFramework.d.c;
import com.corrodinggames.rts.gameFramework.d.g;
import com.corrodinggames.rts.gameFramework.d.h;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.ae;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.utility.y;
import com.corrodinggames.rts.gameFramework.w;
import java.io.IOException;

public final class e {
    private final c ay;
    public ay a = com.corrodinggames.rts.game.units.custom.ay.defaultEffectTemplate;
    public w b;
    public boolean c;
    public boolean d;
    public boolean e = true;
    public boolean f = false;
    public int g;
    public static int h = 1;
    public static int i = 2;
    public static int j = 3;
    public static int k = 4;
    public static int l = 5;
    public static int m = 6;
    public static int n = 7;
    public boolean o;
    public boolean p;
    public h q = com.corrodinggames.rts.gameFramework.d.h.a;
    public boolean r;
    public boolean s;
    public float t;
    public boolean u;
    public boolean v;
    public float w = 1.0f;
    public int x;
    public int y;
    public float z = -1.0f;
    public short A;
    public LightingColorFilter B = null;
    public static LightingColorFilter C = null;
    public static int D = 0;
    public float E;
    public float F;
    public float G;
    public boolean H;
    public float I;
    public float J;
    public float K;
    public boolean L;
    public float M;
    public float N;
    public float O;
    public float P;
    public float Q;
    public float R;
    public float S;
    public float T;
    public float U;
    public float V;
    public float W;
    public float X = 0.0f;
    public float Y;
    public float Z;
    public String aa;
    public Paint ab;
    public float ac;
    public float ad;
    public boolean ae;
    public int af;
    public int ag;
    public boolean ah;
    public boolean ai;
    public float aj;
    public float ak;
    public boolean al;
    public boolean am;
    public boolean an = false;
    public float ao = 0.0f;
    public int ap;
    public int aq;
    public short ar = (short)2;
    public boolean as = false;
    public ag at = com.corrodinggames.rts.gameFramework.d.e.a();
    public float au;
    public int av;
    public boolean aw;
    public static ag[] ax = new ag[128];

    protected e(c c2) {
        this.ay = c2;
    }

    public static ag a() {
        return com.corrodinggames.rts.gameFramework.utility.y.b();
    }

    public ag a(float f) {
        int n2 = (int)(f * (float)(ax.length - 1));
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 > ax.length - 1) {
            n2 = ax.length - 1;
        }
        return ax[n2];
    }

    public void b() {
        if (this.o) {
            this.o = false;
            --this.ay.a;
            com.corrodinggames.rts.gameFramework.d.c.h = true;
            if (this.a.alsoEmitEffectsOnDeath != null && this.A < 20) {
                float f = this.I;
                float f2 = this.J;
                float f3 = this.K;
                if (this.b != null) {
                    f += this.b.eo;
                    f2 += this.b.ep;
                    f3 += this.b.eq;
                }
                this.a.alsoEmitEffectsOnDeath.a(f, f2, f3, this.Y, this.b, 0, this.A);
            }
        }
    }

    public void c() {
        this.a = com.corrodinggames.rts.game.units.custom.ay.defaultEffectTemplate;
        this.q = com.corrodinggames.rts.gameFramework.d.h.a;
        this.b = null;
        this.c = false;
        this.d = false;
        this.e = true;
        this.f = false;
        this.g = 0;
        this.p = false;
        this.I = 0.0f;
        this.J = 0.0f;
        this.L = false;
        this.M = 0.0f;
        this.N = 0.0f;
        this.O = 0.0f;
        this.K = 0.0f;
        this.ar = (short)2;
        this.an = false;
        this.ao = 0.0f;
        this.ae = false;
        this.ak = 0.0f;
        this.aj = 0.0f;
        this.ag = 0;
        this.ah = false;
        this.ai = false;
        this.al = false;
        this.am = false;
        this.ap = 0;
        this.aq = 0;
        this.U = 0.0f;
        this.W = this.V = 15.0f;
        this.X = 0.0f;
        this.r = false;
        this.s = false;
        this.t = 0.0f;
        this.F = 1.0f;
        this.G = 1.0f;
        this.H = false;
        this.u = false;
        this.v = false;
        this.w = 1.0f;
        this.E = 1.0f;
        this.Y = 0.0f;
        this.Z = 0.0f;
        this.P = 0.0f;
        this.Q = 0.0f;
        this.R = 0.0f;
        this.S = 0.0f;
        this.T = 0.0f;
        this.aa = null;
        this.ab = null;
        this.ac = 0.0f;
        this.ad = 0.0f;
        this.A = 0;
        this.x = -1;
        this.B = null;
        this.y = -1;
        this.z = -1.0f;
        this.at.a((ColorFilter)null);
        this.aw = false;
        this.at.a((ae)null);
        this.as = false;
    }

    public void a(e e2) {
        this.a = e2.a;
        this.q = e2.q;
        this.g = e2.g;
        this.b = e2.b;
        this.c = e2.c;
        this.d = e2.d;
        this.e = e2.e;
        this.p = e2.p;
        this.I = e2.I;
        this.J = e2.J;
        this.L = e2.L;
        this.M = e2.M;
        this.N = e2.N;
        this.O = e2.O;
        this.K = e2.K;
        this.ar = e2.ar;
        this.an = e2.an;
        this.ao = e2.ao;
        this.ae = e2.ae;
        this.ak = e2.ak;
        this.aj = e2.aj;
        this.ag = e2.ag;
        this.ah = e2.ah;
        this.ai = e2.ai;
        this.al = e2.ah;
        this.am = e2.am;
        this.ap = e2.ap;
        this.aq = e2.aq;
        this.U = e2.U;
        this.V = e2.V;
        this.W = e2.W;
        this.X = e2.X;
        this.r = e2.r;
        this.s = e2.s;
        this.t = e2.t;
        this.F = e2.F;
        this.G = e2.G;
        this.H = e2.H;
        this.u = e2.u;
        this.v = e2.v;
        this.w = e2.w;
        this.E = e2.E;
        this.Y = e2.Y;
        this.Z = e2.Z;
        this.P = e2.P;
        this.Q = e2.Q;
        this.R = e2.R;
        this.S = e2.S;
        this.T = e2.T;
        this.aa = e2.aa;
        this.ab = e2.ab;
        this.ac = e2.ac;
        this.ad = e2.ad;
        this.A = e2.A;
        this.x = e2.x;
        this.y = e2.y;
        this.z = e2.z;
        this.B = e2.B;
        this.as = e2.as;
    }

    public void b(float f2) {
        this.U = com.corrodinggames.rts.gameFramework.f.a(this.U, f2);
        if (this.U > 0.0f) {
            return;
        }
        this.V -= f2;
        if (this.b != null && this.b.ej && !this.a.liveAfterAttachedDies) {
            this.V = -1.0f;
        }
        if (this.V < 0.0f) {
            this.b();
            return;
        }
        if (this.ae) {
            this.ak = this.al ? (this.ak -= this.aj * f2) : (this.ak += this.aj * f2);
            if (this.ah) {
                if (!this.al) {
                    if (this.ak >= (float)(this.ag + 1)) {
                        this.al = true;
                        this.ak = this.ag;
                    }
                } else if (this.ak < (float)this.af) {
                    if (!this.ai) {
                        this.b();
                        return;
                    }
                    this.al = false;
                    this.ak = this.af;
                }
            } else if (this.ak >= (float)(this.ag + 1)) {
                if (!this.ai) {
                    this.b();
                    return;
                }
                this.ak = this.af;
            }
            this.ap = (int)this.ak;
        }
        if (this.u) {
            this.R -= this.R * 0.002f * f2;
            this.P -= f2 * 0.0015f;
        }
        if (this.v) {
            if (this.K > 0.0f) {
                this.R -= 0.1f * this.w * f2;
            } else {
                if (this.R < 0.0f) {
                    this.R = -this.R;
                    this.R *= 0.5f;
                    this.R = com.corrodinggames.rts.gameFramework.f.a(this.R, 1.3f);
                }
                if (this.K < 0.0f) {
                    this.K = 0.0f;
                }
                if ((double)this.R < 0.2) {
                    this.ar = 1;
                }
                this.P = com.corrodinggames.rts.gameFramework.f.a(this.P, 0.15f * f2);
                this.Q = com.corrodinggames.rts.gameFramework.f.a(this.Q, 0.15f * f2);
                this.Z = com.corrodinggames.rts.gameFramework.f.a(this.Z, 1.0f * f2);
            }
        }
        this.I += this.P * f2;
        this.J += this.Q * f2;
        this.K += this.R * f2;
        this.Y += this.Z * f2;
        if (this.a.trailEffect != null) {
            this.X += f2;
            if (this.X > this.a.trailEffectRate) {
                this.X = 0.0f;
                if (this.A < 20) {
                    float f3 = this.I;
                    float f4 = this.J;
                    float f5 = this.K;
                    if (this.b != null) {
                        f3 += this.b.eo;
                        f4 += this.b.ep;
                        f5 += this.b.eq;
                    }
                    this.a.trailEffect.a(f3, f4, f5, this.Y, this.b, 0, this.A);
                }
            }
        }
    }

    public static void a(int n2, g g2, Rect rect) {
        int n3 = 0;
        if (n2 >= g2.h) {
            n3 += n2 / g2.h;
            n2 %= g2.h;
        }
        int n4 = g2.d + n2 * g2.f;
        int n5 = g2.e + n3 * g2.g;
        rect.a = n4;
        rect.b = n5;
        rect.c = n4 + g2.b;
        rect.d = n5 + g2.c;
    }

    public boolean a(l l2, boolean bl2) {
        ag ag2;
        LightingColorFilter lightingColorFilter;
        float f2;
        int n2;
        float f3;
        Rect rect = com.corrodinggames.rts.gameFramework.d.c.o;
        RectF rectF = com.corrodinggames.rts.gameFramework.d.c.n;
        if (this.U > 0.0f) {
            return false;
        }
        if (bl2 && this.K < 1.0f) {
            return false;
        }
        g g2 = this.a.imageStrip != null ? this.a.imageStrip : com.corrodinggames.rts.gameFramework.d.c.s[this.aq];
        if (!g2.k) {
            int n3 = this.ap;
            com.corrodinggames.rts.gameFramework.d.e.a(n3, g2, rect);
        } else {
            rect.a(0, 0, g2.i.m(), g2.i.l());
        }
        PointF pointF = !bl2 ? com.corrodinggames.rts.gameFramework.f.d(this.I, this.J, this.K) : com.corrodinggames.rts.gameFramework.f.d(this.I, this.J, 0.0f);
        boolean bl3 = this.ar == 4;
        float f4 = 1.0f;
        if (this.G != 1.0f || this.F != 1.0f || this.H) {
            boolean bl4;
            f4 = com.corrodinggames.rts.gameFramework.f.f(this.G, this.F, 1.0f - this.V / this.W);
            boolean bl5 = bl4 = this.ar != 4;
            if (this.H && bl4) {
                f4 *= 1.0f / l2.cX;
                f4 *= l2.cj;
            }
        }
        rectF.a(pointF.a, pointF.b, pointF.a + (float)rect.b(), pointF.b + (float)rect.c());
        if (this.an) {
            rectF.a(-rectF.b() / 2.0f, -rectF.c() / 2.0f);
        }
        if (this.ao != 0.0f) {
            rectF.a(0.0f, rectF.c() * this.ao * f4);
        }
        if (this.b != null) {
            if (!bl2) {
                if (!this.c) {
                    rectF.a(this.b.eo, this.b.ep - this.b.eq);
                } else {
                    rectF.a(this.b.eo, this.b.ep);
                }
            } else {
                rectF.a(this.b.eo, this.b.ep);
            }
        }
        if (!(bl3 && !this.L || com.corrodinggames.rts.gameFramework.f.a(l2.cN, rectF))) {
            return false;
        }
        if (!(this.e || bl3 || this.f)) {
            float f5 = rectF.d();
            if (!l2.bL.a(f5, f3 = rectF.e(), l2.bs)) {
                return false;
            }
            this.f = true;
        }
        if (!bl3) {
            rectF.a(-l2.cw, -l2.cx);
        }
        if (this.S != 0.0f) {
            float f6 = com.corrodinggames.rts.gameFramework.f.j((this.W - this.V) / this.T * 360.0f) * this.S;
            rectF.a(0.0f, f6);
        }
        float f7 = this.W - this.V;
        f3 = 1.0f;
        float f8 = 1.0f;
        float f9 = 1.0f;
        float f10 = 1.0f;
        boolean bl6 = false;
        if (this.x != -1) {
            f3 = (float)Color.a(this.x) * 0.003921569f;
            n2 = Color.b(this.x);
            int n4 = Color.c(this.x);
            int n5 = Color.d(this.x);
            if (n2 != 255 || n4 != 255 || n5 != 255) {
                bl6 = true;
                f8 = (float)n2 * 0.003921569f;
                f9 = (float)n4 * 0.003921569f;
                f10 = (float)n5 * 0.003921569f;
            }
        }
        if (this.z >= 0.0f) {
            float f11 = (float)Color.a(this.y) * 0.003921569f;
            float f12 = (float)Color.b(this.y) * 0.003921569f;
            float f13 = (float)Color.c(this.y) * 0.003921569f;
            float f14 = (float)Color.d(this.y) * 0.003921569f;
            if (this.z <= f7) {
                f3 = f11;
                bl6 = true;
                f8 = f12;
                f9 = f13;
                f10 = f14;
            } else {
                float f15 = f7 / this.z;
                f2 = 1.0f - f15;
                f3 = f3 * f2 + f11 * f15;
                bl6 = true;
                f8 = f8 * f2 + f12 * f15;
                f9 = f9 * f2 + f13 * f15;
                f10 = f10 * f2 + f14 * f15;
            }
        }
        f3 = this.r && f7 >= this.t ? (f3 *= this.V / (this.W - this.t) * this.E) : (this.s && f7 < this.t ? (f3 *= f7 / this.t * this.E) : (f3 *= this.E));
        if (f3 > 1.0f) {
            f3 = 1.0f;
        }
        if (f3 < 0.0f) {
            f3 = 0.0f;
        }
        n2 = 0;
        com.corrodinggames.rts.gameFramework.m.y y2 = l2.bO;
        if (this.Y != 0.0f) {
            if (n2 == 0) {
                n2 = 1;
                y2.k();
            }
            y2.a(this.Y + 90.0f, rectF.d(), rectF.e());
        }
        if (f4 != 1.0f) {
            if (n2 == 0) {
                n2 = 1;
                y2.k();
            }
            y2.a(f4, f4, rectF.d(), rectF.e());
        }
        if (bl2) {
            f3 /= 3.0f;
            f3 = com.corrodinggames.rts.gameFramework.f.b(f3, 0.0f, 1.0f);
            f8 = 0.0f;
            f9 = 0.0f;
            f10 = 0.0f;
            bl6 = true;
        }
        if (bl6 && com.corrodinggames.rts.gameFramework.l.at() && !bl2 && this.B == null) {
            int n6 = com.corrodinggames.rts.gameFramework.f.b(255, (int)(f8 * 255.0f), (int)(f9 * 255.0f), (int)(f10 * 255.0f));
            if (C != null && D == n6) {
                this.B = C;
            } else {
                C = new LightingColorFilter(n6, 0);
                D = n6;
                this.B = C;
            }
        }
        if ((lightingColorFilter = this.B) != null) {
            if (!this.aw) {
                this.at.a(lightingColorFilter);
                this.aw = true;
            }
            bl6 = true;
        } else if (this.aw) {
            this.at.a((ColorFilter)null);
            this.aw = false;
        }
        if (this.ar == 3) {
            if (com.corrodinggames.rts.gameFramework.d.c.k == null) {
                com.corrodinggames.rts.gameFramework.l.e("Loading displacement shader");
                try {
                    com.corrodinggames.rts.gameFramework.d.c.k = new ae("assets/shaders/post_displacement.frag");
                }
                catch (IOException iOException) {
                    throw new RuntimeException(iOException);
                }
            }
            if (this.ay.l != null) {
                ae ae2 = com.corrodinggames.rts.gameFramework.d.c.k;
                ae2.a("screenBase", this.ay.l);
                ae2.b("screenBaseSize", this.ay.l);
                ae2.a("u_resolution", l2.cl, l2.cm);
                ae2.a("u_offsetBy", 0.12f * l2.cX);
                this.at.a(ae2);
                bl6 = true;
            }
        }
        if (!bl6) {
            ag2 = this.a(f3);
        } else {
            ag2 = this.at;
            int n7 = com.corrodinggames.rts.gameFramework.f.b(255, (int)(f8 * 255.0f), (int)(f9 * 255.0f), (int)(f10 * 255.0f));
            f2 = this.au - f3;
            if (f2 < -0.01f || f2 > 0.01f || this.av != n7) {
                this.au = f3;
                this.av = n7;
                int n8 = com.corrodinggames.rts.gameFramework.f.b((int)(f3 * 255.0f), (int)(f8 * 255.0f), (int)(f9 * 255.0f), (int)(f10 * 255.0f));
                this.at.b(n8);
            }
        }
        if (this.aa != null) {
            Paint paint = ag2;
            if (this.ab != null) {
                paint = this.ab;
            }
            f2 = rectF.d() + this.ac;
            float f16 = rectF.e() + this.ad;
            y2.a(this.aa, f2, f16, paint);
        }
        if (this.L) {
            pointF = com.corrodinggames.rts.gameFramework.f.d(this.M, this.N, this.O);
            y2.a(rectF.a, rectF.b, pointF.a - l2.cw, pointF.b - l2.cx, this.ay.w);
        } else if (bl2) {
            if (g2.j != null) {
                y2.a(g2.j, rect, rectF, (Paint)ag2);
            }
        } else {
            y2.a(g2.i, rect, rectF, (Paint)ag2);
        }
        if (n2 != 0) {
            y2.l();
        }
        return true;
    }

    static {
        for (int i2 = 0; i2 < ax.length; ++i2) {
            com.corrodinggames.rts.gameFramework.d.e.ax[i2] = com.corrodinggames.rts.gameFramework.d.e.a();
            float f2 = (float)i2 / (float)(ax.length - 1);
            ax[i2].c((int)(f2 * 255.0f));
        }
    }
}
