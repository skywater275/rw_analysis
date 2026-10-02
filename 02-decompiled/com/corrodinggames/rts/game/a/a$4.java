/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.a;

import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.d;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.a.w;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.l;

class a$4
extends d {
    final /* synthetic */ a a;

    a$4(a a2, String string) {
        this.a = a2;
        super(a2, string);
    }

    @Override
    public boolean a(as as2) {
        am am2 = am.b(as2);
        if (am2.bI()) {
            if (as2 instanceof l) {
                l l2 = (l)as2;
                if (l2.fw) {
                    return false;
                }
            }
            boolean bl2 = false;
            for (s s2 : am2.N()) {
                as as3;
                w w2;
                if (s2 == null || !(s2 instanceof w) || (w2 = (w)s2).F() || (as3 = w2.i()) == null || as3.j()) continue;
                bl2 = true;
            }
            if (bl2) {
                return true;
            }
        }
        return false;
    }
}
