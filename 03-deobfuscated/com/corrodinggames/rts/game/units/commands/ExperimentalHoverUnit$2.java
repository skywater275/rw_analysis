/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;

import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.AbstractBuildAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.ExperimentalHoverUnit;
import com.corrodinggames.rts.gameFramework.steam.Localization;

final class ExperimentalHoverUnit$2
extends AbstractBuildAction {
    ExperimentalHoverUnit$2(int n2) {
        super(n2);
    }


    public boolean g() {
        return false;
    }


    public String getDescription() {
        return Localization.a("gui.actions.buildAntiNuke.description", new Object[0]);
    }


    public String getLabel() {
        return Localization.a("gui.actions.buildAntiNuke", new Object[0]);
    }


    public int getResourceCost() {
        return 4000;
    }

    @Override
    public float K() {
        return 7.0E-4f;
    }


    public boolean a(UnitInstance am2, boolean bl) {
        ExperimentalHoverUnit c2 = (ExperimentalHoverUnit)am2;
        float f2 = c2.slotIndex + c2.a(this.N(), bl);
        if (f2 >= 12.0f) {
            return false;
        }
        return super.a(am2, bl);
    }

    public UnitRegistry L() {
        return null;
    }


    public ActionCategory f() {
        return com.corrodinggames.rts.game.units.actions.ActionCategory.d;
    }

    @Override
    public /* synthetic */ UnitTypeHandle i() {
        return this.L();
    }
}
