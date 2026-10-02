/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.gameFramework.f;

import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.gameFramework.f.ar;
import com.corrodinggames.rts.gameFramework.h.a;

strictfp class at
extends ar {
    public at(float f, float f2, as as2) {
        super(f, f2, as2);
    }

    @Override
    public String a() {
        if (this.g == null) {
            this.g = String.format(com.corrodinggames.rts.gameFramework.h.a.a("gui.log.upgradeCompleted", new Object[0]), this.a.e(), this.b);
        }
        return this.g;
    }
}
