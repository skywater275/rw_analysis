/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.effects.config;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.LocalizedString;
import com.corrodinggames.rts.game.units.custom.effects.config.ResourceConfigBase;

public class f
extends ResourceConfigBase {
    public f() {
        this.u = true;
        this.t = true;
        this.b = "shield";
        this.c = LocalizedString.isEmpty("shield");
    }

    @Override
    public double a(UnitInstance am2) {
        return am2.cx;
    }

    @Override
    public void a(UnitInstance am2, double d) {
        am2.cx = (float)d;
    }

    @Override
    public void b(UnitInstance am2, double d) {
        am2.cx = (float)((double)am2.cx + d);
    }
}
