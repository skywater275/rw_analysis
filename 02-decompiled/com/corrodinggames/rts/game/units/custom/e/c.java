/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.e;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.d.b;
import com.corrodinggames.rts.game.units.custom.e.a;
import com.corrodinggames.rts.game.units.custom.e.e;
import com.corrodinggames.rts.game.units.custom.e.f;
import com.corrodinggames.rts.gameFramework.utility.m;

public class c {
    public final m a = new m();

    public void a(a a2) {
        if (!this.a.contains(a2)) {
            this.a.add(a2);
        }
    }

    public void a(f f2, am am2, double d) {
        int n2 = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n2; ++i) {
            e e2 = (e)objectArray[i];
            double d2 = e2.a.a(am2);
            if (!(d2 < e2.b * d)) continue;
            this.a(e2.a);
        }
    }

    public void a(b b2, am am2, double d) {
        if (!b2.k.c()) {
            this.a(b2.k, am2, d);
        }
        if (b2.b > 0 && am2.bX.o < (double)b2.b * d) {
            this.a(com.corrodinggames.rts.game.units.custom.e.a.a.D);
        }
    }

    public boolean a(f f2) {
        int n2 = f2.b.a;
        Object[] objectArray = f2.b.a();
        for (int i = 0; i < n2; ++i) {
            e e2 = (e)objectArray[i];
            if (!this.a.contains(e2.a)) continue;
            return true;
        }
        return false;
    }

    public boolean a(b b2) {
        if (b2.b > 0 && this.a.contains(com.corrodinggames.rts.game.units.custom.e.a.a.D)) {
            return true;
        }
        return !b2.k.c() && this.a(b2.k);
    }

    public void a() {
        this.a.clear();
    }
}
