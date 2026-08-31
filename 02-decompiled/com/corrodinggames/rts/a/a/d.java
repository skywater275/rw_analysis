/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.a.a;

import com.corrodinggames.rts.a.a.l;
import com.corrodinggames.rts.a.a.n;
import com.corrodinggames.rts.gameFramework.br;
import com.corrodinggames.rts.gameFramework.f;

public class d
extends l {
    int a;

    public void a() {
        int n2;
        com.corrodinggames.rts.gameFramework.l.e("Running unit tests - maths (v3)");
        f.i(100.0f, 100.0f);
        f.i(0.0f, 100.0f);
        f.i(100.0f, 0.0f);
        f.i(0.0f, -100.0f);
        f.i(-100.0f, 0.0f);
        f.i(0.0f, 0.0f);
        com.corrodinggames.rts.gameFramework.l.e("fast_atan2 - NaN");
        f.i(Float.NaN, 0.0f);
        f.i(0.0f, Float.NaN);
        f.i(Float.NaN, Float.NaN);
        com.corrodinggames.rts.gameFramework.l.e("fast_atan2 - Max");
        f.i(Float.MAX_VALUE, 0.0f);
        f.i(Float.MIN_VALUE, 0.0f);
        f.i(0.0f, Float.MAX_VALUE);
        f.i(0.0f, Float.MIN_VALUE);
        com.corrodinggames.rts.gameFramework.l.e("fast_atan2 - NaN+Max");
        f.i(Float.MAX_VALUE, Float.NaN);
        f.i(Float.MIN_VALUE, Float.MAX_VALUE);
        f.i(Float.MAX_VALUE, Float.MIN_VALUE);
        f.i(900000.0f, 900000.0f);
        f.i(3.4028236E33f, 3.4028236E33f);
        f.i(3.4028236E34f, 3.4028236E34f);
        f.i(3.4028234E35f, 3.4028234E35f);
        f.i(3.4028236E36f, 3.4028236E36f);
        f.i(3.4028235E37f, 3.4028235E37f);
        f.i(Float.MAX_VALUE, Float.MAX_VALUE);
        com.corrodinggames.rts.gameFramework.l.e("fast_atan2 - max,max");
        f.i(Float.MAX_VALUE, Float.MAX_VALUE);
        f.i(Float.MIN_VALUE, Float.MIN_VALUE);
        com.corrodinggames.rts.gameFramework.l.e("cos/sin");
        n.a(f.k(0.0f), 1.0f);
        n.a(f.k(360.0f), 1.0f);
        n.a(f.k(10800.0f), 1.0f);
        n.a(f.k(45.0f), 0.70710677f);
        n.a(f.k(90.0f), 0.0f);
        n.a(f.k(450.0f), 0.0f);
        n.a(f.k(10890.0f), 0.0f);
        n.a(f.j(0.0f), 0.0f);
        n.a(f.j(90.0f), 1.0f);
        f.k(-999999.0f);
        f.k(999999.0f);
        f.k(Float.MAX_VALUE);
        f.k(Float.MIN_VALUE);
        f.j(Float.MAX_VALUE);
        f.j(Float.MIN_VALUE);
        com.corrodinggames.rts.gameFramework.l.e("diff sin(0):  " + String.format("%.12f", Float.valueOf(f.j(0.0f) - (float)StrictMath.sin(0.0))));
        com.corrodinggames.rts.gameFramework.l.e("diff sin(45): " + String.format("%.12f", Float.valueOf(f.j(45.0f) - (float)StrictMath.sin(0.7853981633974483))));
        com.corrodinggames.rts.gameFramework.l.e("diff sin(90): " + String.format("%.12f", Float.valueOf(f.j(90.0f) - (float)StrictMath.sin(1.5707963267948966))));
        com.corrodinggames.rts.gameFramework.l.e("diff sin(180):" + String.format("%.12f", Float.valueOf(f.j(180.0f) - (float)StrictMath.sin(Math.PI))));
        com.corrodinggames.rts.gameFramework.l.e("diff sin(360):" + String.format("%.12f", Float.valueOf(f.j(360.0f) - (float)StrictMath.sin(Math.PI * 2))));
        com.corrodinggames.rts.gameFramework.l.e("Testing squareroot");
        for (n2 = 0; n2 < 1005; ++n2) {
            n.a((float)f.a(n2), f.d(f.a((float)n2)));
        }
        n2 = 5;
        int n3 = 0;
        com.corrodinggames.rts.gameFramework.l.e("=== cos/sin tests (runs:" + n2 + ")");
        Long l2 = br.a();
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < 2000; ++j) {
                if (f.k(j) == 0.0f) {
                    ++n3;
                }
                if (f.j(j) != 0.0f) continue;
                ++n3;
            }
        }
        Long l3 = br.a();
        double d2 = br.a(l2, (long)l3);
        this.a += n3;
        com.corrodinggames.rts.gameFramework.l.e("Took: " + d2);
    }
}
