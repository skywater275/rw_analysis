/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.h;
import com.corrodinggames.rts.game.units.h$9$1;
import com.corrodinggames.rts.game.units.n;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.l;
import com.corrodinggames.rts.gameFramework.m.e;

final class h$9
extends x {
    h$9(String string) {
        super(string);
    }

    @Override
    public e j() {
        return com.corrodinggames.rts.game.units.h.g;
    }

    @Override
    public String a() {
        return "Search for units";
    }

    @Override
    public String b() {
        l l2 = l.B();
        h h2 = com.corrodinggames.rts.game.units.h.L();
        if (h2 != null && h2.G == n.e) {
            return "Search: " + f.b(h2.H, 8);
        }
        return "Search units";
    }

    @Override
    public boolean c(am am2, boolean bl) {
        l l2 = l.B();
        if (l2.cb.i()) {
            l2.c("Reply active", "Changing search filter is currently not supported while recording a replay");
            return false;
        }
        h$9$1 h$9$1 = new h$9$1(this);
        h$9$1.b = "Search units by internal name or text title.";
        h$9$1.e = "Search units";
        h$9$1.f = "Search";
        h$9$1.g = "Cancel";
        ad.a(h$9$1);
        return false;
    }
}
