/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.custom.UnitParameter;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;

public strictfp class AnimationReference {
    String a;
    UnitParameter b;
    final /* synthetic */ ModUnitRegistry c;

    public AnimationReference(ModUnitRegistry l2) {
        this.c = l2;
    }

    public void a() {
        if (this.a != null && this.b() == null) {
            throw new RuntimeException("Failed to find animation:" + this.a);
        }
    }

    public UnitParameter b() {
        if (this.a == null) {
            return null;
        }
        if (this.b != null) {
            return this.b;
        }
        for (UnitParameter f2 : (java.util.Collection<UnitParameter>) (java.util.Collection) this.c.dr) {
            if (!f2.a.equalsIgnoreCase(this.a)) continue;
            this.b = f2;
            return f2;
        }
        return null;
    }
}
