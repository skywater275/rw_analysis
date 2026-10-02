/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.effects.config;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.LocalizedString;
import com.corrodinggames.rts.game.units.custom.effects.config.ResourceConfigBase;

public class b
extends ResourceConfigBase {
    public b() {
        this.u = true;
        this.t = true;
        this.b = "ammo";
        this.c = LocalizedString.isEmpty("ammo");
    }

    @Override
    public double a(UnitInstance am2) {
        return am2.cE;
    }

    @Override
    public void a(UnitInstance am2, double d) {
        am2.cE = (int)d;
    }

    public b(UnitInstance am2, double d) {
        am2.cE = (int)((double)am2.cE + d);
    }
}
