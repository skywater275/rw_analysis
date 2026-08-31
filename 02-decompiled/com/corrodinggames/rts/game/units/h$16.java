/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.a.b;
import com.corrodinggames.rts.game.units.a.h;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.n;

final class h$16
extends b {
    h$16() {
    }

    @Override
    public boolean isAvailable(s s2, am am2) {
        com.corrodinggames.rts.game.units.h h2;
        if (s2 instanceof h) {
            s2 = ((h)s2).q_();
        }
        if ((h2 = com.corrodinggames.rts.game.units.h.L()) == null) {
            return true;
        }
        n n2 = h2.G;
        if (n2 == null) {
            n2 = n.a;
        }
        if (n2 == n.a && com.corrodinggames.rts.game.units.h.a(s2, am2)) {
            return false;
        }
        if (n2 == n.d && s2 == com.corrodinggames.rts.game.units.h.h) {
            return true;
        }
        if (n2 == n.d && s2 == com.corrodinggames.rts.game.units.h.i) {
            return true;
        }
        if (n2 == n.e && s2 == com.corrodinggames.rts.game.units.h.y) {
            return true;
        }
        if (s2 == com.corrodinggames.rts.game.units.h.B && !com.corrodinggames.rts.game.units.h.B.b(am2)) {
            return false;
        }
        if (s2 == com.corrodinggames.rts.game.units.h.C && !com.corrodinggames.rts.game.units.h.C.b(am2)) {
            return false;
        }
        as as2 = s2.i();
        return n2.a(as2);
    }
}
