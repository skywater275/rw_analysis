/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.g;

import com.corrodinggames.rts.game.units.g.a;
import com.corrodinggames.rts.game.units.g.b;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.j.as;
import com.corrodinggames.rts.gameFramework.j.k;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.utility.m;
import java.io.IOException;

public final class c {
    public static void a(y y2, float f2) {
        m m2 = y2.bp;
        if (m2 == null) {
            return;
        }
        l l2 = l.B();
        int n2 = l2.by;
        Object[] objectArray = m2.a();
        for (int i2 = m2.a - 1; i2 >= 0; --i2) {
            a a2 = (a)objectArray[i2];
            if (a2.a <= n2) {
                m2.remove(i2);
                continue;
            }
            a2.a(y2, f2);
        }
    }

    public static void a(y y2, a a2) {
        if (y2.bp == null) {
            y2.bp = new m();
        }
        if (y2.bp.size() > 1000) {
            y2.a("status effect limit reached");
            return;
        }
        y2.bp.add(a2);
    }

    public static void a(y y2, as as2) {
        m m2 = y2.bp;
        int n2 = m2 == null ? 0 : m2.size();
        as2.a((short)n2);
        if (n2 == 0) {
            return;
        }
        as2.e("s");
        Object[] objectArray = m2.a();
        for (int i2 = 0; i2 < m2.a; ++i2) {
            a a2 = (a)objectArray[i2];
            as2.a(a2.b());
            a2.a(y2, as2);
        }
        as2.a("s");
    }

    public static void a(y y2, k k2) {
        int n2 = k2.v();
        if (n2 <= 0) {
            y2.bp = null;
            return;
        }
        if (y2.bp == null) {
            y2.bp = new m();
        } else {
            y2.bp.clear();
        }
        m m2 = y2.bp;
        k2.b("s");
        for (int i2 = 0; i2 < n2; ++i2) {
            b b2 = (b)k2.b(b.class);
            if (b2 == null) {
                throw new IOException("Unknown status effect type");
            }
            a a2 = b2.a();
            a2.a(y2, k2);
            m2.add(a2);
        }
        k2.d("s");
    }
}
