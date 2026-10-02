/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.ax;
import com.corrodinggames.rts.game.units.custom.j;

final class as$17
extends ax {
    as$17(int n, String string) {
        super(n, string);
    }

    @Override
    public double a(j j2) {
        return j2.cx;
    }

    @Override
    public void b(j j2, double d2) {
        j2.cx = (float)d2;
    }

    @Override
    public void a(j j2, double d2) {
        super.a(j2, d2);
        j2.cx = (float)d2;
    }
}
