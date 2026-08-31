/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.b;

import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.b.d;
import com.corrodinggames.rts.gameFramework.h.a;

final class d$2
extends x {
    d$2(int n2) {
        super(n2);
    }

    @Override
    public String a() {
        return "-Stop unloading";
    }

    @Override
    public String b() {
        return a.a("gui.actions.cancel", new Object[0]);
    }

    @Override
    public boolean a(am am2, boolean bl) {
        return ((d)am2).g;
    }

    @Override
    public boolean b(am am2) {
        return this.a(am2, false);
    }
}
