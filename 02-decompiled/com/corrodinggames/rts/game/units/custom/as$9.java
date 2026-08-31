/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.as;
import com.corrodinggames.rts.game.units.custom.aw;

final class as$9
extends aw {
    as$9(int n, String string) {
        super(n, string);
    }

    @Override
    public double a(as as2) {
        return as2.p;
    }

    @Override
    public void a(as as2, double d) {
        as2.p = (float)d;
    }
}
