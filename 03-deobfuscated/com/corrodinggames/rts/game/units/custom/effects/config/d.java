/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.effects.config;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.LocalizedString;
import com.corrodinggames.rts.game.units.custom.effects.config.ResourceConfigBase;

public class d
extends ResourceConfigBase {
    public d() {
        this.u = true;
        this.t = true;
        this.b = "energy";
        this.c = LocalizedString.isEmpty("energy");
    }

    @Override
    public double a(UnitInstance am2) {
        return am2.cB;
    }

    @Override
    public void a(UnitInstance am2, double d2) {
        am2.cB = (float)d2;
    }

    @Override
    public void b(UnitInstance am2, double d2) {
        am2.cB = (float)((double)am2.cB + d2);
    }
}
