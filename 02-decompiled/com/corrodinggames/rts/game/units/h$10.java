/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.a.x;
import com.corrodinggames.rts.gameFramework.l;

final class h$10
extends x {
    h$10(String string) {
        super(string);
    }

    @Override
    public String a() {
        return "Show hidden unit information in tooltips including flags, ammo, tags and resources";
    }

    @Override
    public String b() {
        l l2 = l.B();
        if (!l2.bl) {
            return "Debug: Off";
        }
        return "Debug: On";
    }
}
