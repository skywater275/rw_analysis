/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.o;

final class o$1
extends o {
    @Override
    public boolean a(as as2) {
        if (as2 == null) {
            return false;
        }
        am am2 = am.c(as2);
        if (am2.bO() || as2.j()) {
            return false;
        }
        return am2.h() != ao.d && am2.h() != ao.e;
    }
}
