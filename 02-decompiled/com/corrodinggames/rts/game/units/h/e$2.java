/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.h;

import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.h.e;

final class e$2
extends x {
    e$2(int n2) {
        super(n2);
    }

    @Override
    public String a() {
        return "-Dive unit underwater. Evades most attacks";
    }

    @Override
    public String b() {
        return "Dive";
    }

    @Override
    public boolean a(am am2, boolean bl) {
        return ((e)am2).a;
    }
}
