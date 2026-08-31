/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;

import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.AbstractBuildAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.ExperimentalSubUnit;
import com.corrodinggames.rts.gameFramework.steam.Localization;

final class ExperimentalSubUnit$1
extends AbstractBuildAction {
    ExperimentalSubUnit$1(int n2) {
        super(n2);
    }


    public boolean g() {
        return false;
    }


    public String getDescription() {  // 02b a() = getDescription
        return Localization.a("units.laserDefence.upgrade.description", new Object[0]);
    }


    public String getLabel() {  // 02b p$1 b() = getLabel
        return Localization.a("units.laserDefence.upgrade.name", new Object[0]);
    }


    public int getResourceCost() {  // 02b c() = getResourceCost
        return UnitRegistry.y.c(2);
    }

    @Override
    public float K() {
        return 3.0E-4f;
    }


    public boolean a(UnitInstance am2, boolean bl) {
        ExperimentalSubUnit p2 = (ExperimentalSubUnit)am2;
        if (p2.d || p2.a(this.N(), bl) > 0) {
            return false;
        }
        return super.a(am2, bl);
    }


    public boolean b(UnitInstance am2) {
        ExperimentalSubUnit p2 = (ExperimentalSubUnit)am2;
        return !p2.d;
    }

    public UnitRegistry L() {
        return null;
    }


    public ActionCategory f() {
        return ActionCategory.c;  // 02b t.c
    }

    @Override
    public /* synthetic */ UnitTypeHandle i() {
        return this.L();
    }
}
