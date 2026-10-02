/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.n.a;

import com.corrodinggames.rts.game.b.f;
import com.corrodinggames.rts.game.n;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ar;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.n.a;

public class c
extends com.corrodinggames.rts.gameFramework.n.a.a {
    Integer a;
    Integer b;
    n c;
    as d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    boolean i;
    boolean j;
    int k;
    boolean l;
    boolean m;
    boolean n;
    g o;
    boolean p;

    public static c d(a a2) {
        c c2 = new c();
        c2.a = a2.d("maxUnits");
        int n2 = 1;
        if (c2.a != null) {
            n2 = 0;
        }
        c2.b = a2.a("minUnits", n2);
        c2.c = a2.a();
        as as2 = null;
        String string = a2.b("unitType");
        if (string != null && (as2 = ar.a(string)) == null) {
            a2.g("Cound not find unitType:" + string);
        }
        c2.d = as2;
        c2.e = a2.a("onlybuildings", "onlyBuildings", false);
        c2.g = a2.a("onlyMainBuildings", false);
        c2.h = a2.a("onlyOnResourcePool", false);
        c2.f = a2.a("onlyidle", "onlyIdle", false);
        c2.k = a2.a("onlyTechLevel", -1);
        c2.j = a2.a("onlyBuilders", false);
        c2.i = a2.a("onlyEmptyQueue", false);
        c2.l = a2.a("onlyAttack", false);
        c2.m = a2.a("onlyAttackAir", false);
        c2.n = a2.a("onlyIfEmpty", false);
        String string2 = a2.b("onlyWithTag");
        if (string2 != null && !string2.equals("")) {
            try {
                c2.o = com.corrodinggames.rts.game.units.custom.g.b(string2);
            }
            catch (bo bo2) {
                throw new f(bo2.getMessage());
            }
        }
        c2.p = a2.a("includeIncomplete", false);
        return c2;
    }

    @Override
    public boolean b(a a2) {
        return this.e(a2);
    }

    public boolean e(a a2) {
        int n2 = 0;
        am[] amArray = am.bE.a();
        int n3 = am.bE.size();
        for (int i = 0; i < n3; ++i) {
            am am2 = amArray[i];
            if (this.c != null && am2.bX != this.c || !(am2 instanceof y) || am2.cN != null || !a2.a(am2) || this.d != null && am2.r() != this.d) continue;
            y y2 = (y)am2;
            if (!this.p && !am2.bT() || this.l && !am2.l() || this.m && (!am2.l() || !y2.af()) || this.e && !am2.bI() || this.g && (!am2.bI() || !am2.bJ()) || this.h && !am2.r().p() || this.j && !am2.ak() || this.f && !y2.aq() || this.i && y2.a((g)null) > 0 || this.k != -1 && am2.V() != this.k || this.o != null && !com.corrodinggames.rts.game.units.custom.g.a(this.o, am2.de()) || this.n && y2.bB() > 0) continue;
            ++n2;
        }
        return n2 >= this.b && (this.a == null || n2 <= this.a);
    }
}
