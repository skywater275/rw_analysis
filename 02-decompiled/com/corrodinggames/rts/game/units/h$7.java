/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.l;

final class h$7
extends x {
    h$7(String string) {
        super(string);
    }

    @Override
    public String a() {
        return "Slow motion";
    }

    @Override
    public String b() {
        l l2 = l.B();
        if (l2.bt != 0.1f) {
            return "Slow motion: Off";
        }
        return "Slow motion: On";
    }

    @Override
    public boolean c(am am2, boolean bl) {
        l l2 = l.B();
        if (l2.cb.j()) {
            // empty if block
        }
        l2.bt = l2.bt == 1.0f ? 0.1f : 1.0f;
        return false;
    }
}
