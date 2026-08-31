/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.l;

final class h$6
extends x {
    h$6(String string) {
        super(string);
    }

    @Override
    public String a() {
        return "Pause Game";
    }

    @Override
    public String b() {
        l l2 = l.B();
        if (l2.bt != 0.0f) {
            return "Pause: Off";
        }
        return "Pause: On";
    }

    @Override
    public boolean c(am am2, boolean bl) {
        l l2 = l.B();
        if (l2.cb.j()) {
            // empty if block
        }
        l2.bt = l2.bt != 0.0f ? 0.0f : 1.0f;
        return false;
    }
}
