/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.effects.config;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.LocalizedString;
import com.corrodinggames.rts.game.units.custom.effects.config.ResourceConfigBase;

public class e
extends ResourceConfigBase {
    public e() {
        this.u = true;
        this.t = true;
        this.b = "hp";
        this.displayName = LocalizedString.isEmpty("hp");  // 02b e/a/e.java L13: c=displayName, bb.a=LocalizedString.isEmpty
    }

    @Override
    public double a(UnitInstance am2) {
        return am2.cu;
    }

    @Override
    public void a(UnitInstance am2, double d) {
        am2.o((float)d);
    }

    @Override
    public void b(UnitInstance am2, double d) {
        am2.o(am2.cu + (float)d);
    }
}
