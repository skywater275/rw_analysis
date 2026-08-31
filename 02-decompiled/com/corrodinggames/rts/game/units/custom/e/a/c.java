/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.e.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.e.a.a;
import com.corrodinggames.rts.game.units.custom.e.b;

public class c
extends a {
    public c() {
        this.u = true;
        this.t = true;
        this.b = "credits";
        this.c = bb.a("$");
        this.o = true;
        this.q = com.corrodinggames.rts.game.units.custom.e.b.b;
    }

    @Override
    public double a(am am2) {
        return am2.bX.o;
    }

    @Override
    public void a(am am2, double d) {
        am2.bX.o = d;
    }

    @Override
    public void b(am am2, double d) {
        am2.bX.o += d;
    }

    @Override
    public String a(boolean bl) {
        return "$";
    }
}
