/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.AmphibiousUnit;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import com.corrodinggames.rts.game.units.actions.AbstractBuildAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.Structures;
import com.corrodinggames.rts.gameFramework.steam.Localization;

strictfp class UpgradeToT2Action
extends com.corrodinggames.rts.game.units.actions.AbstractBuildAction {
    public UpgradeToT2Action() {
        super(Structures.g.a());  // 02b d/u: super(t.g.a()) (t=units/d/t=Structures)
    }


    public boolean g() {
        return false;
    }


    public String getDescription() {  // 02b d/u a() = getDescription
        return "-Allows factory to build Tech 2 units";
    }


    public String getLabel() {  // 02b d/u b() = getLabel
        return Localization.a("gui.actions.upgradeT2", new Object[0]);
    }


    public int getResourceCost() {  // 02b d/u c() = getResourceCost
        return UnitRegistry.d.c(2);
    }


    public float K() {
        return 4.0E-4f;
    }


    public boolean a(UnitInstance am2, boolean bl) {
        Structures t2 = (Structures) am2;  // 02b d/u: (t)var1 (t=Structures)
        if (t2.r != 1 || t2.a(this.N(), bl) > 0) {
            return false;
        }
        return super.a(am2, bl);
    }


    public boolean b(UnitInstance am2) {
        Structures t2 = (Structures) am2;  // 02b d/u: (t)var1 (t=Structures)
        return t2.r == 1;
    }

    public UnitRegistry L() {
        return null;
    }


    public com.corrodinggames.rts.game.units.actions.ActionCategory f() {
        return com.corrodinggames.rts.game.units.actions.ActionCategory.c;
    }


    public /* synthetic */ UnitTypeHandle i() {
        return this.L();
    }
}


