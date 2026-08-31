/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.a;

import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.a.d;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.l;

class a$2
extends d {
    final /* synthetic */ a a;

    a$2(a a2, String string) {
        this.a = a2;
        super(a2, string);
    }

    @Override
    public boolean a(as as2) {
        am am2 = am.b(as2);
        if (as2.n()) {
            if (as2 instanceof l) {
                l l2 = (l)as2;
                if (l2.fw) {
                    return false;
                }
            }
            if (as2.o() != ao.e) {
                return true;
            }
        }
        return false;
    }
}
