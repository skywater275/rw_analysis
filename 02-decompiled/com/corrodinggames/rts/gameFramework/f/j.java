/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import android.graphics.Point;
import com.corrodinggames.rts.game.b.g;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.d.d;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;

public class j {
    static Point a = new Point();

    public static Point a(int n2, int n3, int n4) {
        l l2 = l.B();
        int n5 = n4;
        l2.bL.a((float)n2, (float)n3);
        int n6 = l2.bL.T;
        int n7 = l2.bL.U;
        Point point = null;
        float f2 = -1.0f;
        for (int i2 = n6 - n5; i2 <= n6 + n5; ++i2) {
            for (int i3 = n7 - n5; i3 <= n7 + n5; ++i3) {
                g g2;
                if (!l2.bL.c(i2, i3) || (g2 = l2.bL.e(i2, i3)) == null || !g2.i) continue;
                am am2 = d.b(i2, i3);
                if (am2 != null && !am2.cf()) {
                    am2 = null;
                }
                if (am2 != null) continue;
                float f3 = f.a((float)n6, (float)n7, (float)i2, (float)i3);
                if (f2 != -1.0f && !(f2 > f3)) continue;
                l2.bL.a(i2, i3);
                a.a(l2.bL.T, l2.bL.U);
                point = a;
                f2 = f3;
            }
        }
        return point;
    }
}
