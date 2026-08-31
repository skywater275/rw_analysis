/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.e.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.e.a.a;

public class b
extends a {
    public b() {
        this.u = true;
        this.t = true;
        this.b = "ammo";
        this.c = bb.a("ammo");
    }

    @Override
    public double a(am am2) {
        return am2.cE;
    }

    @Override
    public void a(am am2, double d) {
        am2.cE = (int)d;
    }

    @Override
    public void b(am am2, double d) {
        am2.cE = (int)((double)am2.cE + d);
    }
}
