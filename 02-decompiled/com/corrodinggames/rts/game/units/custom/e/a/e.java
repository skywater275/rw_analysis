/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.e.a;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.bb;
import com.corrodinggames.rts.game.units.custom.e.a.a;

public class e
extends a {
    public e() {
        this.u = true;
        this.t = true;
        this.b = "hp";
        this.c = bb.a("hp");
    }

    @Override
    public double a(am am2) {
        return am2.cu;
    }

    @Override
    public void a(am am2, double d) {
        am2.o((float)d);
    }

    @Override
    public void b(am am2, double d) {
        am2.o(am2.cu + (float)d);
    }
}
