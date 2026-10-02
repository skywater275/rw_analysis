/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.gameFramework.l;

final class h$8
extends x {
    h$8(String string) {
        super(string);
    }

    @Override
    public String a() {
        return "Fast Forward 1-5x";
    }

    @Override
    public String b() {
        l l2 = l.B();
        return "Fast Forward: " + l2.bt;
    }

    @Override
    public boolean c(am am2, boolean bl) {
        l l2 = l.B();
        if (l2.cb.j()) {
            // empty if block
        }
        l2.bt = l2.bt == 1.0f ? 2.0f : (l2.bt == 2.0f ? 3.0f : (l2.bt == 3.0f ? 4.0f : (l2.bt == 4.0f ? 5.0f : (l2.bt == 5.0f ? 10.0f : 1.0f))));
        return false;
    }
}
