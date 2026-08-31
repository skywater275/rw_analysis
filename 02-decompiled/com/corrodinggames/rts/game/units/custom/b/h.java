/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.b;

import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.units.custom.b.a;
import com.corrodinggames.rts.game.units.custom.b.i;
import com.corrodinggames.rts.game.units.custom.b.n;
import com.corrodinggames.rts.game.units.custom.ba;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.m.e;
import com.corrodinggames.rts.gameFramework.utility.y;

public strictfp class h
extends a {
    public static final a a = new h();
    static final Rect b = new Rect();
    static final RectF c = new RectF();
    static final Paint d = new Paint();

    @Override
    public void b(j j2, float f2) {
        float f3;
        ba ba2;
        i i2;
        int n2;
        float f4;
        l l2 = j2.x;
        i[] iArray = j2.dT;
        if (iArray == null) {
            return;
        }
        if (f2 != 0.0f && (double)j2.f > 0.3) {
            return;
        }
        n n3 = j2.dn();
        if (n3 != null && n3.t) {
            return;
        }
        if (j2.cN != null && n3 == null) {
            for (int i3 = 0; i3 < iArray.length; ++i3) {
                i i4 = iArray[i3];
                i4.m = true;
            }
            return;
        }
        com.corrodinggames.rts.gameFramework.l l3 = com.corrodinggames.rts.gameFramework.l.B();
        float f5 = j2.cg;
        if (l2.dE) {
            f5 = j2.cL[l2.dG].a;
        }
        float f6 = j2.eo - j2.dP;
        float f7 = j2.ep - j2.dQ;
        float f8 = j2.eq - j2.dR;
        float f9 = f5 - j2.dS;
        boolean bl2 = f6 != 0.0f || f7 != 0.0f || f9 != 0.0f;
        j2.dP = j2.eo;
        j2.dQ = j2.ep;
        j2.dR = j2.eq;
        j2.dS = f5;
        PointF pointF = j2.n(15.0f);
        float f10 = pointF.a;
        float f11 = pointF.b;
        if (f10 != 0.0f || f11 != 0.0f) {
            f4 = f.a(0.0f, 0.0f, f10, f11);
            float f12 = f.d(0.0f, 0.0f, f10, f11);
            if ((f4 *= 240.0f) > 15.0f) {
                f4 = 15.0f;
            }
            f10 = f.k(f12) * f4;
            f11 = f.j(f12) * f4;
        }
        int n4 = 0;
        f4 = 0.0f;
        int n5 = 0;
        for (n2 = 0; n2 < iArray.length; ++n2) {
            i2 = iArray[n2];
            ba2 = l2.ax[n2];
            boolean bl3 = false;
            boolean bl4 = false;
            if (i2.m) {
                bl3 = true;
                i2.m = false;
                i2.o = true;
                if (i2.n) {
                    bl4 = true;
                }
                i2.n = false;
            }
            if (!ba2.h) {
                i2.d -= f8;
            }
            if (!ba2.l) {
                if (bl2) {
                    i2.b -= f6;
                    i2.c -= f7;
                    i2.o = true;
                }
            } else if (bl2 && f9 != 0.0f) {
                f.c.a(i2.b, i2.c);
                f.a(0.0f, 0.0f, f9, f.c);
                i2.b = f.c.a;
                i2.c = f.c.b;
                i2.i += f9;
                i2.o = true;
            }
            if (ba2.p) continue;
            if (ba2.T != 0.0f) {
                i2.r += ba2.T * f2;
                i2.r %= 360.0f;
            }
            if (!i2.o) continue;
            float f13 = f.k(f5);
            f3 = f.j(f5);
            float f14 = ba2.d + i2.p;
            float f15 = ba2.e + i2.q;
            i2.f = f13 * f15 - f3 * f14;
            i2.g = f3 * f15 + f13 * f14;
            if (bl3) {
                i2.b = i2.f;
                i2.c = i2.g;
                i2.i = f5 + ba2.i;
                i2.o = true;
                if (bl4) {
                    i2.b *= 0.6f;
                    i2.c *= 0.6f;
                    i2.d = -3.0f;
                }
            }
            if (!ba2.l) {
                i2.f += f10 * ba2.m;
                i2.g += f11 * ba2.m;
            }
            i2.h = f.a(i2.b, i2.c, i2.f, i2.g);
            if (i2.h > f4) {
                n5 = n2;
                f4 = i2.h;
            }
            if (!i2.k || ba2.l) continue;
            ++n4;
        }
        for (n2 = 0; n2 < iArray.length; ++n2) {
            i2 = iArray[n2];
            ba2 = l2.ax[n2];
            if (ba2.p) continue;
            float f16 = ba2.g;
            if (!ba2.h) {
                f16 -= j2.eq;
            }
            float f17 = f16 + ba2.f;
            if (i2.h > 90000.0f) {
                i2.b = ba2.d;
                i2.c = ba2.e;
            } else if (i2.h > ba2.O * ba2.O) {
                float f18 = f.d(i2.f, i2.g, i2.b, i2.c);
                i2.b = i2.f + f.k(f18) * ba2.O;
                i2.c = i2.g + f.j(f18) * ba2.O;
                i2.k = true;
            }
            if (!(i2.k || !(i2.d <= f16 + 0.1f) || n4 >= ba2.L || n2 != n5 && ba2.M)) {
                boolean bl5 = false;
                if (ba2.n) {
                    for (int i5 = 0; i5 < ba2.S.length; ++i5) {
                        if (!iArray[ba2.S[i5]].k) continue;
                        bl5 = true;
                    }
                }
                f3 = ba2.K;
                if (bl5) {
                    f3 = ba2.N;
                }
                if (i2.h > f3 * f3) {
                    i2.k = true;
                    ++n4;
                }
            }
            if (ba2.l) {
                i2.k = true;
            }
            if (i2.k) {
                if (i2.d > f17 || ba2.l) {
                    float f19 = f2 * ba2.s;
                    if (i2.h <= f19 * f19) {
                        i2.b = i2.f;
                        i2.c = i2.g;
                        i2.o = true;
                        i2.k = false;
                    } else {
                        f3 = f.d(i2.b, i2.c, i2.f, i2.g);
                        i2.b += f.k(f3) * f19;
                        i2.c += f.j(f3) * f19;
                        i2.o = true;
                    }
                    if (ba2.l && i2.d > f16) {
                        i2.d -= f2 * ba2.v;
                        if (i2.d <= f16) {
                            i2.d = f16;
                        }
                    }
                    f3 = f.c(i2.i, f5 + ba2.i, ba2.u * f2);
                    i2.i += f3;
                    i2.j = false;
                    continue;
                }
                if (i2.e < ba2.t) {
                    i2.e += f2;
                    continue;
                }
                i2.d += f2 * ba2.v;
                continue;
            }
            i2.e = 0.0f;
            if (i2.d > f16) {
                com.corrodinggames.rts.gameFramework.d.e e2;
                i2.d -= f2 * ba2.v;
                if (!(i2.d <= f16)) continue;
                i2.d = f16;
                float f20 = j2.eo + i2.b;
                f3 = j2.ep + i2.c;
                i2.l = y.c(f20, f3);
                if (!ba2.I || i2.j) continue;
                i2.j = true;
                if (i2.l) {
                    if (!l3.dd || !j2.el) continue;
                    l3.bR.a(f20, f3, i2.d, 0, 0.0f, 0.0f);
                    continue;
                }
                if (!l3.dc || !j2.el || (e2 = l3.bR.c(f20, f3, i2.d, i2.i, 0)) == null) continue;
                e2.P = 0.0f;
                e2.Q = 0.0f;
                e2.G = 1.6f;
                e2.F = 2.8f;
                continue;
            }
            if (!i2.l || !(i2.d > -3.0f + f16)) continue;
            i2.d -= f2 * 0.3f;
        }
    }

    public static void a(j j2, float f2, boolean bl2, boolean bl3) {
        i[] iArray = j2.dT;
        if (iArray == null) {
            return;
        }
        l l2 = j2.x;
        float f3 = j2.cg;
        if (l2.dE) {
            f3 = j2.cL[l2.dG].a;
        }
        com.corrodinggames.rts.gameFramework.l l3 = com.corrodinggames.rts.gameFramework.l.B();
        if (j2.cq || j2.cp) {
            for (int i2 = 0; i2 < l2.ax.length; ++i2) {
                iArray[i2].m = true;
            }
            j2.dv();
        }
        float f4 = j2.cD();
        Paint paint = null;
        boolean bl4 = l3.dg || j2.cp;
        for (int i3 = 0; i3 < iArray.length; ++i3) {
            ba ba2 = l2.ax[i3];
            if (ba2.P != bl2 && ba2.D == null || ba2.Q != bl3 || ba2.p || ba2.q != null && ba2.q.read(j2)) continue;
            i i4 = iArray[i3];
            if (i4.s <= 0.0f) continue;
            float f5 = j2.eq + i4.d;
            if (paint == null) {
                paint = j2.aN();
            }
            Paint paint2 = paint;
            float f6 = 1.0f;
            if (f5 < -0.3f) {
                f6 = (float)j2.l(f5) * 0.003921569f;
            }
            if (i4.s < 1.0f) {
                f6 *= i4.s;
            }
            if (f6 < 1.0f) {
                int n2 = (int)(255.0f * f6);
                if (paint2.f() != n2) {
                    d.a(paint2);
                    int n3 = d.f();
                    if (n3 < n2) {
                        n2 = n3;
                    }
                    d.c(n2);
                    paint2 = d;
                }
            }
            float f7 = j2.eo + i4.b - l3.cw;
            float f8 = j2.ep + i4.c - l3.cx - i4.d - j2.eq;
            com.corrodinggames.rts.gameFramework.m.y y2 = l3.bO;
            if (f4 != 1.0f) {
                y2.k();
                y2.a(f4, f4, f7, f8);
            }
            if (ba2.D != null && !bl2 && l3.df && i4.d + j2.eq > 0.0f) {
                float f9 = f8 + i4.d + j2.eq;
                y2.a(ba2.D, f7, f9, i4.i + i4.r + ba2.R, j2.R());
            }
            if (ba2.P == bl2) {
                e e2 = ba2.B;
                if (ba2.C != null) {
                    e2 = ba2.C[j2.bX.R()];
                }
                if (!ba2.H && (bl4 || ba2.G) && e2 != null) {
                    y2.a(e2, f7, f8, i4.i + i4.r + ba2.R, paint2);
                }
                e e3 = ba2.x;
                if (ba2.y != null) {
                    e3 = ba2.y[j2.bX.R()];
                }
                if (e3 != null && (bl4 || ba2.F)) {
                    float f10;
                    float f11 = f10 = e3.u;
                    float f12 = f.k(f3);
                    float f13 = f.j(f3);
                    float f14 = f12 * ba2.k - f13 * ba2.j;
                    float f15 = f13 * ba2.k + f12 * ba2.j;
                    float f16 = f.d(i4.b, i4.c, f14, f15);
                    float f17 = f.a(i4.b, i4.c, f14, f15);
                    if (f17 < (f10 - 2.0f) * (f10 - 2.0f)) {
                        f11 = f.a((int)f17);
                    }
                    y2.k();
                    y2.a(f16 + 90.0f, f7, f8);
                    b.a(0, (int)(f10 - f11), e3.p, (int)(f10 + f11));
                    c.a(f7 - (float)e3.r, f8 - f11, f7 + (float)e3.r, f8 + f11);
                    y2.a(e3, b, c, paint2);
                    y2.l();
                }
                if (ba2.H && (bl4 || ba2.G) && e2 != null) {
                    y2.a(e2, f7, f8, i4.i + i4.r + ba2.R, paint2);
                }
            }
            if (f4 == 1.0f) continue;
            y2.l();
        }
    }
}
