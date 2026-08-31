/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.d;

import android.graphics.Paint;
import android.graphics.Paint$Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.ag;
import com.corrodinggames.rts.gameFramework.utility.o;

public class a {
    float a;
    float b;
    public boolean c;
    public as d;
    public n e;
    public int f = 1;
    public float g;
    public float h;
    public boolean i;
    public n j;
    public boolean k;
    public int l;
    public int m;
    public boolean n;
    public y o;
    boolean p = false;
    public boolean q = false;
    public int r;
    public float s;
    public float t = 0.04f;
    public boolean u;
    public am v;
    public static o w = new o();
    static Point x = new Point();
    static RectF y = new RectF();
    static RectF z = new RectF();
    static RectF A = new RectF();
    Paint B = new Paint();
    static Paint C;
    static Paint D;
    static RectF E;

    public a() {
        w.add(this);
        w.a();
    }

    public static void a() {
        w.clear();
    }

    public static void a(float f) {
        for (a a2 : w) {
            a2.c(f);
        }
        w.a();
    }

    public static void b(float f) {
        Object[] objectArray = w.b();
        int n2 = w.size();
        for (int i = 0; i < n2; ++i) {
            a a2 = (a)objectArray[i];
            a2.d(f);
        }
    }

    public static boolean a(n n2, int n3, int n4, int n5) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l2.bL.a(n3, n4);
        float f2 = l2.bL.T + l2.bL.p;
        float f3 = l2.bL.U + l2.bL.q;
        y.a(f2, f3, f2 + 1.0f, f3 + 1.0f);
        return com.corrodinggames.rts.gameFramework.d.a.a(n2, y, n5);
    }

    public static boolean a(n n2, y y2, int n3) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        b b2 = l2.bL;
        y = y2.a(b2, y);
        return com.corrodinggames.rts.gameFramework.d.a.a(n2, y, n3);
    }

    public static boolean a(y y2, y y3) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        b b2 = l2.bL;
        return com.corrodinggames.rts.gameFramework.f.a(y = y2.a(b2, y), z = y3.a(b2, z));
    }

    public static boolean a(n n2, RectF rectF, int n3) {
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        b b2 = l2.bL;
        RectF rectF2 = A;
        for (a a2 : w) {
            if (a2.j != n2 || !a2.n || n3 != -1 && n3 != a2.r) continue;
            am am2 = am.a(a2.d);
            if (am2 == null) {
                com.corrodinggames.rts.gameFramework.l.e("isTileRectOverBlueprint: Failed to get shared unit for: " + a2.d);
                continue;
            }
            am2.eo = a2.g;
            am2.ep = a2.h;
            if (!com.corrodinggames.rts.gameFramework.f.a(rectF2 = am2.a(b2, rectF2), rectF)) continue;
            return true;
        }
        return false;
    }

    public static a a(n n2, float f2, float f3) {
        for (a a2 : w) {
            if (a2.j != n2 || !a2.n) continue;
            float f4 = com.corrodinggames.rts.gameFramework.f.a(a2.g, a2.h, f2, f3);
            am am2 = am.a(a2.d);
            float f5 = am2.cj + 1.0f;
            if (f5 < 20.0f) {
                f5 = 20.0f;
            }
            if (!(f4 < f5 * f5)) continue;
            return a2;
        }
        return null;
    }

    public boolean b() {
        if (this.n) {
            if (this.o == null || this.o.bV) {
                return false;
            }
            if (!ar.a(this.d, this.g, this.h, 0.0f, 0.0f, this.e)) {
                return false;
            }
        } else {
            if (this.v == null) {
                return false;
            }
            if (this.v.cf()) {
                return false;
            }
        }
        return true;
    }

    public void c(float f2) {
        this.a += 1.0f;
        this.b += f2;
        boolean bl = false;
        this.s = com.corrodinggames.rts.gameFramework.f.a(this.s, this.t * f2);
        if (this.n) {
            if (this.a > 6.0f) {
                this.a = 0.0f;
                boolean bl2 = this.o.a(this.d, this.g, this.h);
                if (!this.p && bl2) {
                    this.p = true;
                }
                if (!bl2) {
                    if (this.p) {
                        bl = true;
                    } else if (this.b > 180.0f) {
                        bl = true;
                    }
                }
                if (!this.b()) {
                    bl = true;
                }
            }
        } else if (this.a > 2.0f && !this.b()) {
            bl = true;
        }
        if (bl) {
            this.c = true;
            w.b(this);
        }
    }

    public void d(float f2) {
        Rect rect;
        am am2;
        l l2 = com.corrodinggames.rts.gameFramework.l.B();
        if (l2.bs != this.j) {
            return;
        }
        if (!l2.cO.b(this.g, this.h)) {
            return;
        }
        if (this.q && !this.p) {
            return;
        }
        float f3 = 0.0f;
        float f4 = this.g;
        float f5 = this.h;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 1.0f;
        float f9 = 500.0f;
        boolean bl = false;
        boolean bl2 = false;
        if (this.n) {
            bl2 = true;
        } else {
            bl = true;
        }
        boolean bl3 = true;
        if (this.i) {
            bl3 = false;
        }
        if (bl2) {
            f3 = this.s;
            f3 = f3 <= 0.0f ? 0.0f : (this.s < 1.0f ? 1.0f - com.corrodinggames.rts.gameFramework.f.k(f3 * 90.0f) : 1.0f);
        }
        if (bl2 && this.s < 1.0f && (am2 = am.c(this.d)) != null && am2.bI() && (rect = am2.cd()) != null) {
            E.a(rect);
            com.corrodinggames.rts.gameFramework.d.a.E.b *= (float)l2.bL.o;
            com.corrodinggames.rts.gameFramework.d.a.E.d *= (float)l2.bL.o;
            com.corrodinggames.rts.gameFramework.d.a.E.a *= (float)l2.bL.n;
            com.corrodinggames.rts.gameFramework.d.a.E.c *= (float)l2.bL.n;
            float f10 = (float)(l2.bL.p - 3) + f3 * 5.0f;
            E.a(-(am2.cZ() - (float)l2.bL.p), -(am2.da() - (float)l2.bL.q));
            com.corrodinggames.rts.gameFramework.f.a(E, f10);
            float f11 = this.g - l2.cw;
            float f12 = this.h - l2.cx - f7;
            E.a(f11, f12);
            float f13 = 3.0f + f3 * 7.0f;
            Paint paint = C;
            if (this.s <= 0.0f) {
                paint = D;
            }
            l2.bO.a(com.corrodinggames.rts.gameFramework.d.a.E.a - f13, com.corrodinggames.rts.gameFramework.d.a.E.b, com.corrodinggames.rts.gameFramework.d.a.E.c + f13, com.corrodinggames.rts.gameFramework.d.a.E.b, paint);
            l2.bO.a(com.corrodinggames.rts.gameFramework.d.a.E.a - f13, com.corrodinggames.rts.gameFramework.d.a.E.d, com.corrodinggames.rts.gameFramework.d.a.E.c + f13, com.corrodinggames.rts.gameFramework.d.a.E.d, paint);
            l2.bO.a(com.corrodinggames.rts.gameFramework.d.a.E.a, com.corrodinggames.rts.gameFramework.d.a.E.b - f13, com.corrodinggames.rts.gameFramework.d.a.E.a, com.corrodinggames.rts.gameFramework.d.a.E.d + f13, paint);
            l2.bO.a(com.corrodinggames.rts.gameFramework.d.a.E.c, com.corrodinggames.rts.gameFramework.d.a.E.b - f13, com.corrodinggames.rts.gameFramework.d.a.E.c, com.corrodinggames.rts.gameFramework.d.a.E.d + f13, paint);
        }
        float f14 = 0.0f;
        if (bl2) {
            f14 -= 10.0f * f3;
        }
        ar.a(this.d, f4, f5 + f14, f6, f7, this.e, f8, f9, bl, bl2, this.f, bl3, null);
    }

    static {
        E = new RectF();
        C = new ag();
        C.a(90, 0, 0, 255);
        C.a(Paint$Style.b);
        C.a(2.0f);
        D = new ag();
        D.a(40, 0, 0, 255);
        D.a(Paint$Style.b);
        D.a(2.0f);
    }
}
