/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.ActionBinding;

public strictfp class CustomUnitAnimator
extends ActionBinding {
    @Override
    public void a() throws bo {
        if (!this.e) {
            this.d = ModUnitRegistry.n(this.c);
            if (this.d == null) {
                throw new bo("Could not find customUnit target:" + this.d() + " used on:" + this.a + " in section:" + this.b);
            }
        }
    }

    public ModUnitRegistry e() {
        return (ModUnitRegistry) this.d;
    }

    @Override
    public /* synthetic */ UnitTypeHandle c() {
        return this.e();
    }
}
