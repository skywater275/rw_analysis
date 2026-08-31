/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.projectiles;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitShield;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.steam.Localization;

final class ActionAddCredits$1
extends AbstractImmediateAction {
    ActionAddCredits$1(int n2) {
        super(n2);
    }


    @Override
    public String getDescription() {
        return "-Will unload all units when stopped";
    }


    @Override
    public String getLabel() {
        return Localization.a("gui.actions.unload", new Object[0]);
    }

    @Override
    public int getLabel(UnitInstance am2, boolean bl) {
        return ((UnitShield) ((Object)am2)).bB();
    }


    public boolean a(UnitInstance am2, boolean bl) {
        if (((UnitShield) ((Object)am2)).bA()) {
            return false;
        }
        return ((UnitShield) ((Object)am2)).f() && ((UnitShield) ((Object)am2)).bB() > 0;
    }


}
