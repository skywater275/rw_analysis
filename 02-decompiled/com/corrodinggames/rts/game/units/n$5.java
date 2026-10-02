/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.n;
import java.util.Locale;

final class n$5
extends n {
    @Override
    public boolean a(as as2) {
        h h2 = h.L();
        if (h2 == null) {
            return false;
        }
        if (h2.H == null) {
            return false;
        }
        if (h2.I) {
            h2.I = false;
            h2.J = h2.H.toLowerCase().trim();
        }
        if (as2 == null) {
            return false;
        }
        if (as2.i() != null && as2.i().toLowerCase(Locale.ROOT).contains(h2.J)) {
            return true;
        }
        return as2.i() != null && as2.e().toLowerCase(Locale.ROOT).contains(h2.J);
    }

    @Override
    public boolean b() {
        h h2 = h.L();
        if (h2 == null) {
            return false;
        }
        return h2.H != null;
    }
}
