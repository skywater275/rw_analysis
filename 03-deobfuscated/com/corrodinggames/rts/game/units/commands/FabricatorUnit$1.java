/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;

import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.AbstractBuildAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.FabricatorUnit;
import com.corrodinggames.rts.gameFramework.steam.Localization;

final class FabricatorUnit$1
extends AbstractBuildAction {
    FabricatorUnit$1(int n2) {
        super(n2);
    }


    public boolean g() {
        return false;
    }


    public String getDescription() {  // 02b v$1 a() = getDescription
        return Localization.a("units.supplyDepot.upgrade.description", new Object[0]);
    }


    public String getLabel() {  // 02b v$1 b() = getLabel
        return Localization.a("units.supplyDepot.upgrade.name", new Object[0]);
    }


    public int getResourceCost() {  // 02b c() = getResourceCost
        return 1000;
    }

    @Override
    public float K() {
        return 4.0E-4f;
    }


    public boolean a(UnitInstance am2, boolean bl) {
        FabricatorUnit v2 = (FabricatorUnit)am2;
        if (v2.f != 1 || v2.a(this.N(), bl) > 0) {
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

