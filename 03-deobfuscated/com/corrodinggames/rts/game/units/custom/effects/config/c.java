/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.effects.config;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.LocalizedString;
import com.corrodinggames.rts.game.units.custom.effects.config.ResourceConfigBase;
import com.corrodinggames.rts.game.units.custom.effects.b;

public class c
extends ResourceConfigBase {
    public c() {
        this.u = true;
        this.t = true;
        this.b = "credits";
        this.c = LocalizedString.isEmpty("$");
        this.o = true;
        this.q = com.corrodinggames.rts.game.units.custom.effects.b.b;
    }

    @Override
    public double a(UnitInstance am2) {
        return am2.player.credits;
    }

    @Override
    public void a(UnitInstance am2, double d) {
        am2.player.credits = d;
    }

    @Override
    public void b(UnitInstance am2, double d) {
        am2.player.credits += d;
    }

    @Override
    public String a(boolean bl) {
        return "$";
    }
}
