/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.buildings;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.buildings.MoveBehavior;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.steam.Localization;

final class MoveBehavior$1
extends AbstractImmediateAction {
    MoveBehavior$1(int n2) {
        super(n2);
    }


    public String getDescription() {
        return "-Will unload all units when stopped";
    }


    public String getLabel() {
        return Localization.a("gui.actions.unload", new Object[0]);
    }

    @Override
    public int getLabel(UnitInstance am2, boolean bl) {
        return ((MoveBehavior)am2).o.size();
    }


    public boolean a(UnitInstance am2, boolean bl) {
        if (((MoveBehavior)am2).g) {
            return false;
        }
        return !((com.corrodinggames.rts.game.units.UnitType)am2).cK() && ((MoveBehavior)am2).o.size() > 0;
    }
}
