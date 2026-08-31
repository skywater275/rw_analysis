/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.a.d;
import com.corrodinggames.rts.game.units.custom.d.c;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class a
extends com.corrodinggames.rts.game.units.custom.a.a {
    com.corrodinggames.rts.game.units.custom.e.a a;
    double b = -1.7976931348623157E308;
    com.corrodinggames.rts.game.units.custom.e.a c;
    float d = 1.0f;
    c e;
    c f;

    public static void a(l l2, ab ab2, String string, String string2, d d2, String string3, boolean bl2) {
        Object object;
        com.corrodinggames.rts.game.units.custom.e.a a2 = ab2.a(l2, string, string2 + "resourceAmount", null, true);
        if (a2 != null) {
            object = new a();
            ((a)object).a = a2;
            ((a)object).b = ab2.a(string, string2 + "resourceAmount_setValue", -1.7976931348623157E308);
            ((a)object).c = ab2.a(l2, string, string2 + "resourceAmount_addOtherResource", null, true);
            ((a)object).d = ab2.a(string, string2 + "resourceAmount_multiplyBy", Float.valueOf(1.0f)).floatValue();
            d2.ac.add(object);
        }
        object = com.corrodinggames.rts.game.units.custom.d.c.a(l2, ab2, string, string2 + "addResourcesWithLogic", null);
        c c2 = com.corrodinggames.rts.game.units.custom.d.c.a(l2, ab2, string, string2 + "setResourcesWithLogic", null);
        if (object != null || c2 != null) {
            a a3 = new a();
            a3.f = c2;
            a3.e = object;
            d2.ac.add(a3);
        }
    }

    @Override
    public boolean a(j j2, s s2, PointF pointF, am am2, int n2) {
        if (this.a != null) {
            double d2 = this.b != -1.7976931348623157E308 ? this.b : this.a.a(j2);
            if (this.c != null) {
                d2 += this.c.a(j2);
            }
            this.a.a(j2, d2 *= (double)this.d);
        }
        if (this.f != null) {
            this.f.d(j2);
        }
        if (this.e != null) {
            this.e.e(j2);
        }
        return true;
    }
}
