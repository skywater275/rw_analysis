/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.b;

import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.b.c;
import com.corrodinggames.rts.game.units.y;

final class c$2
extends x {
    c$2(int n2) {
        super(n2);
    }

    @Override
    public String a() {
        return "-Dive unit underwater.";
    }

    @Override
    public String b() {
        return "Dive";
    }

    @Override
    public boolean a(am am2, boolean bl) {
        return ((c)am2).r && ((y)am2).cJ();
    }
}
