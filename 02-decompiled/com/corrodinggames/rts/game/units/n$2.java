/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.n;

final class n$2
extends n {
    @Override
    public boolean a(as as2) {
        h h2 = h.L();
        if (h2 != null && h2.F != null) {
            return h2.F.a(as2);
        }
        return false;
    }
}
