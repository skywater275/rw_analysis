/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.utility.m;

class c {
    am a;
    s b;
    float c;
    boolean d;
    boolean e;
    static m f = new m();

    c() {
    }

    public static void a(am am2, s s2, boolean bl, boolean bl2) {
        c c2 = com.corrodinggames.rts.gameFramework.f.c.a(am2, s2, bl2);
        if (c2 == null) {
            c2 = new c();
            f.add(c2);
        }
        c2.a = am2;
        c2.b = s2;
        c2.c = 10.0f;
        c2.d = bl;
        c2.e = bl2;
    }

    public static c a(am am2, s s2, boolean bl) {
        for (c c2 : f) {
            if (c2.a != am2 || c2.b != s2 || c2.e != bl) continue;
            return c2;
        }
        return null;
    }

    public static float b(am am2, s s2, boolean bl) {
        c c2 = com.corrodinggames.rts.gameFramework.f.c.a(am2, s2, bl);
        if (c2 != null) {
            float f = c2.c / 10.0f;
            if (c2.d) {
                f = -f;
            }
            return f;
        }
        return 0.0f;
    }

    public static void a(float f) {
        for (int i = com.corrodinggames.rts.gameFramework.f.c.f.size() - 1; i >= 0; --i) {
            c c2 = (c)com.corrodinggames.rts.gameFramework.f.c.f.get(i);
            c2.c -= f;
            if (!(c2.c <= 0.0f)) continue;
            com.corrodinggames.rts.gameFramework.f.c.f.remove(i);
        }
    }
}
