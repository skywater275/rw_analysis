/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.as;
import com.corrodinggames.rts.game.units.custom.aw;
import com.corrodinggames.rts.game.units.custom.j;

final class as$16
extends aw {
    as$16(int n, String string) {
        super(n, string);
    }

    @Override
    public double a(as as2) {
        return as2.g;
    }

    @Override
    public void a(as as2, double d) {
        as2.g = (int)d;
    }

    @Override
    public void a(j j2, double d2) {
        super.a(j2, d2);
        j2.cA = (float)d2;
    }
}
