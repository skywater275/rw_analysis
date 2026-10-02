/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.c;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.c.a;
import com.corrodinggames.rts.game.units.custom.c.d;
import com.corrodinggames.rts.game.units.custom.c.e;
import com.corrodinggames.rts.game.units.custom.c.f;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.j.m;
import com.corrodinggames.rts.gameFramework.m.ag;

public strictfp class c {
    static final Rect a = new Rect();
    static final RectF b = new RectF();
    static final Paint c = new Paint();
    com.corrodinggames.rts.gameFramework.utility.m d = new com.corrodinggames.rts.gameFramework.utility.m();
    static Paint e = new Paint();
    public static f f;

    public void a(l l2) {
        Object[] objectArray = this.d.a();
        for (int i = this.d.a - 1; i >= 0; --i) {
            e e2 = (e)objectArray[i];
            a a2 = l2.a(e2.a.g);
            if (a2 != null) {
                e2.a = a2;
                while (e2.b.size() > e2.a.d) {
                    e2.b.remove(e2.b.size() - 1);
                }
                continue;
            }
            this.d.remove(i);
        }
    }

    public e a(a a2, boolean bl) {
        int n2 = this.d.a;
        Object[] objectArray = this.d.a();
        for (int i = 0; i < n2; ++i) {
            e e2 = (e)objectArray[i];
            if (e2.a != a2) continue;
            return e2;
        }
        if (bl) {
            e e3 = new e(a2);
            this.d.add(e3);
            return e3;
        }
        return null;
    }

    public int a(a a2) {
        e e2 = this.a(a2, false);
        if (e2 == null) {
            return 0;
        }
        return e2.b.a;
    }

    public void a(float f2, am am2) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        int n2 = this.d.a;
        if (n2 == 0) {
            return;
        }
        Object[] objectArray = this.d.a();
        for (int i2 = 0; i2 < n2; ++i2) {
            e e2 = (e)objectArray[i2];
            a a2 = e2.a;
            int n3 = e2.b.a;
            Object[] objectArray2 = e2.b.a();
            for (int i3 = n3 - 1; i3 >= 0; --i3) {
                d d2 = (d)objectArray2[i3];
                if (!d2.c) continue;
                am am3 = d2.a;
                if (a2.e != null) {
                    com.corrodinggames.rts.gameFramework.m.e e3 = a2.e;
                    float f3 = am2.eo - com.corrodinggames.rts.gameFramework.l.B().cw;
                    float f4 = am2.ep - com.corrodinggames.rts.gameFramework.l.B().cx - am2.eq - 10.0f;
                    float f5 = e3.u;
                    float f6 = com.corrodinggames.rts.gameFramework.f.d(am2.eo, am2.ep - am2.eq, am3.eo, am3.ep - am3.eq);
                    float f7 = com.corrodinggames.rts.gameFramework.f.a(am2.eo, am2.ep - am2.eq, am3.eo, am3.ep - am3.eq);
                    if (f7 < (float)((e3.q - 2) * (e3.q - 2))) {
                        f5 = com.corrodinggames.rts.gameFramework.f.a((int)f7);
                    }
                    l2.bO.k();
                    l2.bO.a(f6 + 90.0f, f3, f4);
                    a.a(0, (int)((float)e3.q - f5), e3.p, e3.q);
                    b.a(f3 - (float)e3.r, f4 - f5, f3 + (float)e3.r, f4);
                    Paint paint = ag.r;
                    if (d2.d != 0.0f) {
                        paint = c;
                        int n4 = (int)Math.abs(d2.d * 5.0f);
                        if (n4 > 250) {
                            n4 = 250;
                        }
                        paint.a(255, 255, 255 - n4, 255 - n4);
                    }
                    l2.bO.a(e3, a, b, paint);
                    l2.bO.l();
                }
                if (a2.f == null) continue;
                l2.bO.a(am2.eo - l2.cw, am2.ep - l2.cx - am2.eq, am3.eo - l2.cw, am3.ep - l2.cx - am3.eq, a2.f);
            }
        }
    }

    public void a(as as2) {
        if (this.d.a == 0) {
            as2.c(-1);
            return;
        }
        as2.c(0);
        int n2 = this.d.size();
        as2.a((short)n2);
        Object[] objectArray = this.d.a();
        for (int i2 = 0; i2 < n2; ++i2) {
            e e2 = (e)objectArray[i2];
            as2.a(e2.a.g);
            as2.a((short)e2.b.size());
            for (d d2 : e2.b) {
                as2.a(d2.a);
                as2.a(d2.b);
                as2.a(d2.c);
            }
        }
    }

    public void a(am am2, k k2) {
        byte by = k2.d();
        if (by == -1) {
            return;
        }
        int n2 = k2.v();
        this.d.clear();
        for (int i2 = 0; i2 < n2; ++i2) {
            g g2 = k2.m();
            a a2 = null;
            if (am2 instanceof j) {
                a2 = ((j)am2).x.a(g2);
            }
            e e2 = null;
            if (a2 != null) {
                e2 = new e(a2);
                this.d.add(e2);
            }
            int n3 = k2.v();
            for (int i3 = 0; i3 < n3; ++i3) {
                d d2 = new d();
                d2.a = k2.a(m.a);
                d2.b = k2.e();
                d2.c = k2.e();
                if (d2.a == null || e2 == null) continue;
                e2.b.add(d2);
            }
        }
    }

    static {
        e.a(255, 0, 0, 200);
        f = new f();
    }
}
