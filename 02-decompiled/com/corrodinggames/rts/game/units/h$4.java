/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.l;

final class h$4
extends x {
    h$4(String string) {
        super(string);
    }

    @Override
    public String a() {
        String string = "Hide interface till the screen is clicked/pressed";
        if (l.av()) {
            string = string + "\n-Enable mouse capture to also hide the mouse";
        }
        return string;
    }

    @Override
    public String b() {
        return "Hide interface";
    }

    @Override
    public boolean c(am am2, boolean bl) {
        l l2 = l.B();
        l2.cU = true;
        return false;
    }
}
