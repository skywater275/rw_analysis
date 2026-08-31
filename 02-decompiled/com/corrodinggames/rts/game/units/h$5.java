/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.h;

final class h$5
extends x {
    h$5(String string) {
        super(string);
    }

    @Override
    public String a() {
        return "Freeze full high level logic for all AI forever";
    }

    @Override
    public String b() {
        return "Freeze AI";
    }

    @Override
    public String d() {
        boolean bl;
        String string = "Freeze AI";
        h h2 = com.corrodinggames.rts.game.units.h.L();
        if (h2 != null && (bl = h2.c)) {
            string = "Unfreeze AIs";
        }
        return string;
    }

    @Override
    public boolean a(am am2, boolean bl) {
        return true;
    }
}
