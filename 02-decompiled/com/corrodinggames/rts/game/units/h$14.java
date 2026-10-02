/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.l;

final class h$14
extends x {
    h$14(String string) {
        super(string);
    }

    @Override
    public String a() {
        return "Clear save history";
    }

    @Override
    public String b() {
        l l2 = l.B();
        return "Clear history";
    }

    @Override
    public boolean b(am am2) {
        l l2 = l.B();
        return l2.bl;
    }
}
