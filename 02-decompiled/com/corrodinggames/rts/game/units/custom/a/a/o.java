/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.a.a;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.a.a;
import com.corrodinggames.rts.game.units.custom.a.d;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.bp;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.gameFramework.utility.ab;
import com.corrodinggames.rts.gameFramework.utility.m;

public class o
extends a {
    public bp a;
    public int b;
    public h c;
    public boolean d;
    public boolean e;
    public int f = -1;
    public LogicBoolean g;

    public static void a(l l2, ab ab2, String string, String string2, d d2, String string3, boolean bl2) {
        bp bp2 = bp.a(l2, ab2, string, string2 + "addUnitsIntoTransport");
        int n2 = ab2.b(string, string2 + "deleteNumUnitsFromTransport", 0);
        h h2 = com.corrodinggames.rts.game.units.custom.g.a(ab2.b(string, "deleteNumUnitsFromTransport_onlyWithTags", (String)null), null);
        boolean bl3 = ab2.a(string, string2 + "startUnloadingTransport", (Boolean)false);
        boolean bl4 = ab2.a(string, string2 + "forceUnloadTransportNow", (Boolean)false);
        int n3 = ab2.b(string, string2 + "forceUnloadTransportNow_onlyOnSlot", -1);
        LogicBoolean logicBoolean = ab2.b(l2, string, string2 + "transportTargetNow", null);
        if (n3 != -1 && !bl4) {
            throw new bo("forceUnloadTransportNow_onlyOnSlot expects forceUnloadTransportNow");
        }
        if (!bp2.b() || n2 > 0 || bl3 || bl4 || logicBoolean != null) {
            o o2 = new o();
            if (!bp2.b()) {
                o2.a = bp2;
            }
            if (n2 > 0) {
                o2.b = n2;
                o2.c = h2;
            }
            o2.d = bl3;
            o2.e = bl4;
            o2.f = n3;
            o2.g = logicBoolean;
            d2.ac.add(o2);
        }
    }

    @Override
    public boolean a(j j2, s s2, PointF pointF, am am2, int n2) {
        am am3;
        int n3;
        if (this.b != 0) {
            block0: for (n3 = 0; n3 < this.b; ++n3) {
                if (j2.B.size() <= 0) continue;
                for (int i2 = j2.B.size() - 1; i2 >= 0; --i2) {
                    am am4 = (am)j2.B.get(i2);
                    if (am4 == null) {
                        com.corrodinggames.rts.gameFramework.l.b("deleteNumUnitsFromTransport unit==null");
                        continue;
                    }
                    if (this.c != null && !com.corrodinggames.rts.game.units.custom.g.a(this.c, am4.de())) continue;
                    j2.B.remove(i2);
                    j2.D(am4);
                    if (am4 == null) continue block0;
                    am4.ci();
                    continue block0;
                }
            }
        }
        if (this.a != null) {
            m m2 = new m();
            this.a.a(m2, j2.bX, j2, false);
            for (am am4 : m2) {
                am4.eo = j2.eo;
                am4.ep = j2.ep;
                am4.eq = j2.eq;
                j2.C(am4);
            }
        }
        if (this.d) {
            j2.L();
        }
        if (this.e) {
            for (n3 = j2.B.size() - 1; n3 >= 0; --n3) {
                if (this.f != -1 && this.f != n3) continue;
                boolean bl2 = j2.B.size() % 2 == 0;
                j2.a((am)j2.B.get(n3), true, bl2);
            }
        }
        if (this.g != null && (am3 = this.g.readUnit(j2)) != null && am3.bL && j2.d(am3, true)) {
            j2.C(j2);
        }
        return true;
    }
}
