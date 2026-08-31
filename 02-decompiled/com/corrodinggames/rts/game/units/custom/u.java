/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import android.graphics.PointF;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.w;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.utility.m;

public strictfp class u {
    m a = new m();
    m b;
    String c;
    String d;

    public void a(l l2) {
    }

    public void b(l l2) {
        m m2 = new m();
        for (String string : this.a) {
            s s2 = l2.h(string);
            if (s2 == null) {
                throw new bo("[" + this.d + "]" + this.c + " Could not find action:" + string + " on unit: " + l2.b());
            }
            if (s2 instanceof w) {
                m2.add((w)s2);
                continue;
            }
            throw new bo("[" + this.d + "]" + this.c + " Action:" + string + " on unit: " + l2.b() + " doesn't have the right type");
        }
        this.b = m2;
    }

    public void a(j j2, PointF pointF, am am2, int n2, int n3) {
        if (this.b == null) {
            ad.g("Action on " + j2.dt().i() + " has not been linked");
            return;
        }
        for (s s2 : this.b) {
            j2.a(s2, pointF, am2, n2, n3);
        }
    }

    public m a() {
        if (this.b == null) {
            ad.g("Action on [" + this.d + "]" + this.c + " has not been linked");
            return new m();
        }
        return this.b;
    }

    public void a(j j2, PointF pointF, am am2) {
        if (this.b == null) {
            ad.g("Action on " + j2.dt().i() + " has not been linked");
            return;
        }
        for (s s2 : this.b) {
            j2.dL.a((w)s2, false, pointF, am2);
        }
    }
}
