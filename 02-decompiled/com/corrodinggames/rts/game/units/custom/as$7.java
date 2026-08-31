/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.as;
import com.corrodinggames.rts.game.units.custom.aw;
import com.corrodinggames.rts.game.units.custom.j;

final class as$7
extends aw {
    as$7(int n, String string) {
        super(n, string);
    }

    @Override
    public double a(as as2) {
        return as2.n;
    }

    @Override
    public void a(as as2, double d) {
        as2.n = (int)d;
    }

    @Override
    public void a(j j2, double d2) {
        int n2 = j2.s();
        super.a(j2, d2);
        if (j2.s() > n2 && !j2.ax) {
            j2.c(false);
        }
    }
}
