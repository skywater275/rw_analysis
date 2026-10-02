/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.n;

final class n$4
extends n {
    @Override
    public boolean a(as as2) {
        if (as2 == null) {
            return false;
        }
        if (as2 instanceof l) {
            l l2 = (l)as2;
            if (l2.J == null) {
                return false;
            }
            h h2 = h.L();
            return h2 == null || h2.E == null || l2.J == h2.E;
        }
        return false;
    }
}
