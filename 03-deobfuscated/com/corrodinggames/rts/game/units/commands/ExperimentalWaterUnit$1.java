/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;

import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.AbstractBuildAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.ExperimentalWaterUnit;
import com.corrodinggames.rts.gameFramework.steam.Localization;

final class ExperimentalWaterUnit$1
extends AbstractBuildAction {
    ExperimentalWaterUnit$1(int n2) {
        super(n2);
    }


    public boolean g() {
        return false;
    }


    public String getDescription() {
        return "-Allows factory to build Tech 2 units";
    }


    public String getLabel() {
        return Localization.a("gui.actions.upgradeT2", new Object[0]);
    }


    public int getResourceCost() {
        return 1500;
    }

    @Override
    public float K() {
        return 5.0E-4f;
    }


    public boolean a(UnitInstance am2, boolean bl) {
        ExperimentalWaterUnit f2 = (ExperimentalWaterUnit)am2;
        if (f2.f || f2.a(this.N(), bl) > 0) {
            return false;
        }
        return super.a(am2, bl);
    }

    public UnitRegistry L() {
        return null;
    }


    public ActionCategory f() {
        return com.corrodinggames.rts.game.units.actions.ActionCategory.c;
    }

    @Override
    public /* synthetic */ UnitTypeHandle i() {
        return this.L();
    }
}
