/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.a.a;
import com.corrodinggames.rts.game.units.custom.a.d;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.m;

public class k
extends a {
    public bp a;
    public bp b;

    public static void a(l l2, ab ab2, String string, String string2, d d2, String string3, boolean bl2) {
        Object object;
        bp bp2 = bp.a(l2, ab2, string, string2 + "produceUnits");
        if (!bp2.b()) {
            object = new k();
            ((k)object).a = bp2;
            d2.ac.add(object);
        }
        if (!((bp)(object = bp.a(l2, ab2, string, string2 + "spawnUnits"))).b()) {
            k k2 = new k();
            k2.b = object;
            d2.ac.add(k2);
        }
    }

    @Override
    public boolean a(j j2, s s2, PointF pointF, am am2, int n2) {
        if (this.a != null) {
            m m2 = new m();
            this.a.a(m2, j2.bX, j2, false);
            for (am am3 : m2) {
                j2.E(am3);
                j2.F(am3);
            }
        }
        if (this.b != null) {
            this.b.a(j2.eo, j2.ep, j2.eq, j2.cg, j2.bX, false, j2);
        }
        return true;
    }
}
