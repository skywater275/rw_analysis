/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.a.x;

strictfp class i
extends x {
    public i() {
        super("addCredits");
    }

    @Override
    public String b() {
        return "Add credits";
    }

    @Override
    public String a() {
        return "Add $10000 to this team";
    }

    @Override
    public boolean h() {
        return true;
    }
}
