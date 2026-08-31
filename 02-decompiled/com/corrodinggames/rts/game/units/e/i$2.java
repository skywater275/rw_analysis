/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.e;

import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.game.units.ak;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.h.a;

final class i$2
extends x {
    i$2(int n2) {
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
        return ((ak)((Object)am2)).bA();
    }

    @Override
    public boolean b(am am2) {
        return this.a(am2, false);
    }
}
