/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.a.a;
import com.corrodinggames.rts.game.units.custom.a.d;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.gameFramework.utility.ab;

public class l
extends a {
    boolean a;
    h b;
    h c;
    h d;
    h e;

    public static void a(com.corrodinggames.rts.game.units.custom.l l2, ab ab2, String string, String string2, d d2, String string3, boolean bl2) {
        Object object;
        boolean bl3 = ab2.a(string, string2 + "resetToDefaultTags", (Boolean)false);
        h h2 = ab2.a(l2, string, string2 + "temporarilyAddTags", (h)null);
        h h3 = ab2.a(l2, string, string2 + "temporarilyRemoveTags", (h)null);
        if (bl3 || h2 != null || h3 != null) {
            object = new l();
            ((l)object).a = bl3;
            ((l)object).b = h2;
            ((l)object).c = h3;
            d2.ac.add(object);
        }
        object = ab2.a(l2, string, string2 + "addGlobalTeamTags", (h)null);
        h h4 = ab2.a(l2, string, string2 + "removeGlobalTeamTags", (h)null);
        if (object != null || h4 != null) {
            l l3 = new l();
            l3.d = object;
            l3.e = h4;
            d2.ac.add(l3);
        }
    }

    @Override
    public boolean a(j j2, s s2, PointF pointF, am am2, int n2) {
        if (this.a) {
            j2.j(false);
        }
        if (this.c != null) {
            j2.b(this.c);
        }
        if (this.b != null) {
            j2.a(this.b);
        }
        if (this.d != null) {
            j2.bX.b(this.d);
        }
        if (this.e != null) {
            j2.bX.c(this.e);
        }
        return true;
    }
}
