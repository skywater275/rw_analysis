/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.AbstractBuildAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.ExperimentalBuilding;
import com.corrodinggames.rts.gameFramework.steam.Localization;

strictfp class CustomBuildingUnit
extends com.corrodinggames.rts.game.units.actions.AbstractBuildAction {
    public CustomBuildingUnit() {
        super(ExperimentalBuilding.h.a());  // 02b n.java: super(m.h.a()) (m=units.d.m=ExperimentalBuilding)
    }


    public boolean g() {
        return false;
    }


    public String getDescription() {  // 02b n.java a() = getDescription
        return Localization.a("units.landFactory.upgrade.description", new Object[0]);
    }


    public String getLabel() {  // 02b n.java b() = getLabel
        return Localization.a("units.landFactory.upgrade.name", new Object[0]);
    }


    public int getResourceCost() {  // 02b n.java c() = getResourceCost
        return UnitRegistry.b.c(2);
    }


    public float K() {
        return 4.0E-4f;
    }


    public boolean a(UnitInstance am2, boolean bl) {
        ExperimentalBuilding m2 = (ExperimentalBuilding) am2;
        if (m2.g || m2.a(this.N(), bl) > 0) {
            return false;
        }
        return super.a(am2, bl);
    }

    public UnitRegistry L() {
        return null;
    }


    public ActionCategory f() {  // 02b n.java: t.c (t=units.a.t=ActionCategory)
        return ActionCategory.c;
    }


    public /* synthetic */ UnitTypeHandle i() {
        return this.L();
    }
}

