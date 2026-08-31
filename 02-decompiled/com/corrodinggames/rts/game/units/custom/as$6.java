/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.as;
import com.corrodinggames.rts.game.units.custom.aw;

final class as$6
extends aw {
    as$6(int n, String string) {
        super(n, string);
    }

    @Override
    public double a(as as2) {
        return as2.k;
    }

    @Override
    public void a(as as2, double d) {
        as2.k = (float)d;
    }
}
