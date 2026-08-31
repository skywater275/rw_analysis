/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.e.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.e.a.a;

public class d
extends a {
    public d() {
        this.u = true;
        this.t = true;
        this.b = "energy";
        this.c = bb.a("energy");
    }

    @Override
    public double a(am am2) {
        return am2.cB;
    }

    @Override
    public void a(am am2, double d2) {
        am2.cB = (float)d2;
    }

    @Override
    public void b(am am2, double d2) {
        am2.cB = (float)((double)am2.cB + d2);
    }
}
