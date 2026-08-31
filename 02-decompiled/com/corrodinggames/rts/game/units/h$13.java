/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.l;

final class h$13
extends x {
    h$13(String string) {
        super(string);
    }

    @Override
    public String a() {
        return "For debugging autoTriggers. When enabled will log a message when any auto triggers fire on any selected units";
    }

    @Override
    public String b() {
        l l2 = l.B();
        if (!l2.bn) {
            return "Trigger Debug: Off";
        }
        return "Trigger Debug: On";
    }

    @Override
    public boolean b(am am2) {
        l l2 = l.B();
        return l2.bl;
    }
}
