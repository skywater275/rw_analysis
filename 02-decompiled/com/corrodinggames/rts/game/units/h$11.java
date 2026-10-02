/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.a.a;
import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.gameFramework.l;

final class h$11
extends x {
    h$11(String string) {
        super(string);
    }

    @Override
    public String a() {
        return "AI debug view";
    }

    @Override
    public String b() {
        l l2 = l.B();
        if (!a.as) {
            return "AI Debug: Off";
        }
        return "AI Debug: On";
    }
}
