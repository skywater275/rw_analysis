/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import com.corrodinggames.rts.game.b.b;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.k.i;
import com.corrodinggames.rts.gameFramework.k.l;

public final class aq {
    public static final Paint a = new Paint();
    static final Point b = new Point();
    static final Rect c = new Rect();
    static final PointF d = new PointF();
    static final PointF e = new PointF();
    static final PointF f = new PointF();
    static final PointF g = new PointF();
    static final PointF h = new PointF();
    static final PointF i = new PointF();
    static final PointF j = new PointF();

    private static strictfp Point a(ao ao2, int n2, int n3, int n4, int n5, int n6, int n7, int n8) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l l3 = l2.bU;
        i i2 = l3.a(ao2);
        int n9 = com.corrodinggames.rts.gameFramework.f.d(n4 - n2);
        int n10 = com.corrodinggames.rts.gameFramework.f.d(n5 - n3);
        int n11 = n2;
        int n12 = n3;
        int n13 = 1 + n9 + n10;
        int n14 = n4 > n2 ? 1 : -1;
        int n15 = n5 > n3 ? 1 : -1;
        int n16 = n9 - n10;
        n9 *= 2;
        n10 *= 2;
        int n17 = 0;
        while (n13 > 0) {
            int n18;
            int n19 = n11;
            int n20 = n12;
            if (n7 != 0 && (n18 = l3.c(i2, n19, n20)) < n7) {
                b.a(n19, n20);
                return b;
            }
            if (n6 != 0) {
                n18 = l3.b(i2, n19, n20);
                if (n18 == -1) {
                    b.a(n19, n20);
                    return b;
                }
                if (n8 > 0) {
                    --n8;
                } else {
                    n17 += n18;
                }
                if (n17 >= n6) {
                    b.a(n19, n20);
                    return b;
                }
            } else if (l3.a(i2, n19, n20)) {
                b.a(n19, n20);
                return b;
            }
            if (n16 > 0) {
                n11 += n14;
                n16 -= n10;
            } else if (n16 < 0) {
                n12 += n15;
                n16 += n9;
            } else if (n16 == 0) {
                n11 += n14;
                n12 += n15;
                n16 -= n10;
                n16 += n9;
                --n13;
            }
            --n13;
        }
        return null;
    }

    public static strictfp boolean a(ao ao2, float f2, float f3, float f4, float f5, int n2, int n3, int n4) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        boolean bl = true;
        i i2 = l2.bU.a(ao2);
        l2.bU.a(i2, true);
        return aq.b(ao2, f2, f3, f4, f5, n2, n3, n4);
    }

    public static strictfp boolean b(ao ao2, float f2, float f3, float f4, float f5, int n2, int n3, int n4) {
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        b b2 = l2.bL;
        b2.a(f2, f3);
        int n5 = b2.T;
        int n6 = b2.U;
        b2.a(f4, f5);
        int n7 = b2.T;
        int n8 = b2.U;
        Point point = aq.a(ao2, n5, n6, n7, n8, n2, n3, n4);
        return point == null;
    }

    public static strictfp PointF a(ao ao2, float f2, float f3, float f4, float f5, int n2, int n3, boolean bl) {
        boolean bl2;
        com.corrodinggames.rts.gameFramework.l l2 = com.corrodinggames.rts.gameFramework.l.B();
        l l3 = l2.bU;
        c.a(n2, n3, n2 + 1, n3 + 1);
        d.a(f2, f3);
        e.a(f4, f5);
        f.a(e);
        int n4 = -1;
        g.a(aq.c.a, aq.c.d);
        h.a(aq.c.c, aq.c.b);
        i.a(aq.c.a, aq.c.b);
        j.a(aq.c.c, aq.c.d);
        if (aq.d.b < aq.e.b) {
            boolean bl3 = bl2 = bl || !l3.a(ao2, n2, n3 - 1);
            if (bl2 && com.corrodinggames.rts.gameFramework.f.a(d, e, i, h)) {
                n4 = 3;
            }
        } else {
            boolean bl4 = bl2 = bl || !l3.a(ao2, n2, n3 + 1);
            if (bl2 && com.corrodinggames.rts.gameFramework.f.a(d, e, g, j)) {
                n4 = 1;
            }
        }
        if (aq.d.a < aq.e.a) {
            boolean bl5 = bl2 = bl || !l3.a(ao2, n2 - 1, n3);
            if (bl2 && com.corrodinggames.rts.gameFramework.f.a(d, e, i, g)) {
                n4 = 2;
            }
        } else {
            boolean bl6 = bl2 = bl || !l3.a(ao2, n2 + 1, n3);
            if (bl2 && com.corrodinggames.rts.gameFramework.f.a(d, e, h, j)) {
                n4 = 0;
            }
        }
        if (n4 == -1) {
            return null;
        }
        if (n4 == 0) {
            aq.f.a = (float)(n2 + 1) + 0.01f;
        }
        if (n4 == 2) {
            aq.f.a = (float)n2 - 0.01f;
        }
        if (n4 == 1) {
            aq.f.b = (float)(n3 + 1) + 0.01f;
        }
        if (n4 == 3) {
            aq.f.b = (float)n3 - 0.01f;
        }
        return f;
    }

    public static strictfp boolean a(y y2, am am2) {
        if (am2.cN != null) {
            return false;
        }
        if (!y2.k(am2)) {
            return false;
        }
        return am2.d((am)y2);
    }

    public static strictfp boolean b(y y2, am am2) {
        if (!aq.a(y2, am2)) {
            return false;
        }
        if (!y2.h(am2)) {
            return false;
        }
        return y2.i(am2);
    }
}
