/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.f.al$1;
import com.corrodinggames.rts.gameFramework.f.al$2;
import com.corrodinggames.rts.gameFramework.f.al$3;
import com.corrodinggames.rts.gameFramework.f.al$4;
import com.corrodinggames.rts.gameFramework.f.al$5;
import com.corrodinggames.rts.gameFramework.f.al$6;
import com.corrodinggames.rts.gameFramework.l;
import java.util.ArrayList;

abstract class al {
    static al a = new al$1();
    static al b = new al$2();
    static al c = new al$3();
    static al d = new al$4();
    static al e = new al$5();
    static al f = new al$6();

    al() {
    }

    public abstract boolean a(y var1);

    public static void a(ArrayList arrayList, al al2, al al3) {
        y y2;
        y y3;
        l l2 = l.B();
        if (l2.bS.q() != 1) {
            arrayList.clear();
        }
        if ((y3 = l2.bS.t()) != null) {
            if (al2.a(y3) || al3 != null && al3.a(y3)) {
                if (!arrayList.contains(y3)) {
                    arrayList.add(y3);
                }
            } else {
                arrayList.clear();
            }
        }
        if ((y2 = al.a(arrayList, al2)) == null && al3 != null) {
            y2 = al.a(arrayList, al3);
        }
        if (y2 == null) {
            arrayList.clear();
            if (y3 != null) {
                arrayList.add(y3);
            }
            if ((y2 = al.a(arrayList, al2)) == null && al3 != null) {
                y2 = al.a(arrayList, al3);
            }
        }
        if (y2 != null) {
            l2.bS.y();
            l2.bS.j(y2);
            l2.b(y2.eo, y2.ep);
            arrayList.add(y2);
        }
    }

    public static y a(ArrayList arrayList, al al2) {
        l l2 = l.B();
        y y2 = null;
        float f2 = -1.0f;
        for (am am2 : am.bE) {
            y y3;
            if (!(am2 instanceof y) || !l2.bS.m(y3 = (y)am2) || !al2.a(y3) || arrayList.contains(y3)) continue;
            float f3 = com.corrodinggames.rts.gameFramework.f.a(l2.cy + l2.cI, l2.cz + l2.cJ, y3.eo, y3.ep);
            if (y2 != null && !(f3 < f2)) continue;
            f2 = f3;
            y2 = y3;
        }
        return y2;
    }
}
