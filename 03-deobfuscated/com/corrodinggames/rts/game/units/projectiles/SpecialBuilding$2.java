/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.projectiles;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitShield;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.steam.Localization;

final class SpecialBuilding$2
extends AbstractImmediateAction {
    SpecialBuilding$2(int n2) {
        super(n2);
    }


    public String getDescription() {
        return "-Stop unloading";
    }


    public String getLabel() {
        return Localization.a("gui.actions.cancel", new Object[0]);
    }


    public boolean a(UnitInstance am2, boolean bl) {
        return ((UnitShield)am2).bA();
    }


    public boolean b(UnitInstance am2) {
        return this.a(am2, false);
    }
}
