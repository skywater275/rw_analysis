/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.a;

import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.au;
import com.corrodinggames.rts.game.units.av;
import com.corrodinggames.rts.game.units.custom.a.e;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.y;
import java.util.AbstractList;
import java.util.ArrayList;

public class f {
    static boolean a(y y2) {
        au au2;
        boolean bl = false;
        if (y2.aq()) {
            bl = true;
        }
        if (!bl && (au2 = y2.ar()) != null && au2.d() == av.g) {
            bl = true;
        }
        return bl;
    }

    static boolean b(y y2) {
        boolean bl = false;
        if (y2.aq()) {
            bl = true;
        }
        return bl;
    }

    public static Object a(AbstractList abstractList) {
        int n2 = abstractList.size();
        if (n2 == 0) {
            return null;
        }
        return abstractList.get(com.corrodinggames.rts.gameFramework.f.a(0, n2 - 1));
    }

    public static boolean a(y y2, g g2) {
        h h2;
        as as2 = y2.r();
        return as2 instanceof l && g.a(g2, h2 = ((l)as2).fv);
    }

    public static s a(a a2, y y2, e e2) {
        ArrayList arrayList = y2.N();
        ArrayList arrayList2 = a2.ap();
        for (s s2 : arrayList) {
            if (s2.v(y2) != e2) continue;
            arrayList2.add(s2);
        }
        if (arrayList2.size() > 0) {
            return (s)f.a(arrayList2);
        }
        return null;
    }
}
