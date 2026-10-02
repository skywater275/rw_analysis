/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.b;

import android.graphics.PointF;
import com.corrodinggames.rts.game.f;
import com.corrodinggames.rts.game.units.custom.b.a;
import com.corrodinggames.rts.game.units.custom.bn;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.d.p;
import com.corrodinggames.rts.game.units.y;

public strictfp class k
extends a {
    public static final a a = new k();
    static final PointF b = new PointF();

    @Override
    public void b(j j2, float f2) {
        l l2 = j2.x;
        int n2 = j2.bl();
        for (int i2 = 0; i2 < n2; ++i2) {
            bn bn2 = l2.fQ[i2];
            if (bn2.aj != null && j2.cB > 0.0f && !j2.v) {
                float f3 = bn2.aj.floatValue();
                b.a(j2.E(i2));
                float f4 = j2.m();
                if (bn2.ab < 99999.0f) {
                    f4 = bn2.ab;
                }
                if (p.a((y)j2, k.b.a, k.b.b, j2.eq, f4, f3)) {
                    // empty if block
                }
                if (j2.cB < 0.0f) {
                    j2.cB = 0.0f;
                    j2.v = true;
                }
            }
            if (bn2.ak == null) continue;
            k.a(j2, bn2);
        }
    }

    public static void a(j j2, bn bn2) {
        if (!j2.a(bn2)) {
            return;
        }
        float f2 = bn2.al;
        float f3 = bn2.am;
        float f4 = bn2.an;
        f f5 = null;
        h h2 = bn2.ak;
        Object[] objectArray = f.a.a();
        int n2 = f.a.a;
        for (int i2 = 0; i2 < n2; ++i2) {
            float f6;
            float f7;
            f f8 = (f)objectArray[i2];
            if (f8.aE == null || !(f8.eq > f4) || !g.a(f8.aE, h2) || !((f7 = com.corrodinggames.rts.gameFramework.f.a(j2.eo, j2.ep, f8.eo, f8.ep)) < f3 * f3) || !((f6 = com.corrodinggames.rts.gameFramework.f.a(j2.eo, j2.ep, f8.n, f8.o)) < f2 * f2) && !(f2 < 0.0f) || f8.j != null && (f8.j.bX.d(j2.bX) || f8.j.bX == j2.bX) || f8.h <= 0.0f || k.a(f8)) continue;
            f5 = f8;
        }
        if (f5 != null) {
            j2.b(bn2);
            f f9 = j2.a(null, f5.eo, f5.ep, bn2.e, null, 0);
            f9.aC = true;
            f9.q = f5;
        }
    }

    public static boolean a(f f2) {
        Object[] objectArray = f.a.a();
        int n2 = f.a.a;
        for (int i2 = 0; i2 < n2; ++i2) {
            f f3 = (f)objectArray[i2];
            if (f3 == f2 || f3.q != f2) continue;
            return true;
        }
        return false;
    }
}
