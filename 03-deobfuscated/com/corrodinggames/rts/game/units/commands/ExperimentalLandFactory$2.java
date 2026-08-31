/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;

import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.AbstractBuildAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.ExperimentalLandFactory;
import com.corrodinggames.rts.gameFramework.steam.Localization;

final class ExperimentalLandFactory$2
extends AbstractBuildAction {
    ExperimentalLandFactory$2(int n2) {
        super(n2);
    }


    public boolean g() {
        return false;
    }


    public String getDescription() {  // 02b a() = getDescription
        return Localization.a("units.extractor.upgrade.descriptionT3", new Object[0]);
    }


    public String getLabel() {  // 02b b() = getLabel
        return Localization.a("gui.actions.upgradeT3", new Object[0]);
    }


    public int getResourceCost() {  // 02b c() = getResourceCost
        return UnitRegistry.a.c(3);
    }

    @Override
    public float K() {
        return 3.0E-4f;
    }


    public boolean a(UnitInstance am2, boolean bl) {
        ExperimentalLandFactory g2 = (ExperimentalLandFactory)am2;
        if (g2.b != 2 || g2.a(this.N(), bl) > 0) {
            return false;
        }
        return super.a(am2, bl);
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
