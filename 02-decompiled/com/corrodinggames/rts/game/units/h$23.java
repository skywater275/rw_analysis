/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.h;

final class h$23
extends x {
    h$23(String string) {
        super(string);
    }

    @Override
    public String a() {
        return "Change selected player's alliance (players with the same letter are allied)";
    }

    @Override
    public String b() {
        return "Ally:";
    }

    @Override
    public String d() {
        String string = "Ally";
        h h2 = com.corrodinggames.rts.game.units.h.L();
        if (h2 != null) {
            string = "Ally: " + h2.bX.h();
        }
        return string;
    }

    @Override
    public boolean a(am am2, boolean bl) {
        return true;
    }
}
