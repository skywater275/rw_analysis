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
import com.corrodinggames.rts.game.units.commands.ExperimentalLandFactory;
import com.corrodinggames.rts.gameFramework.steam.Localization;

final class InGameActivity$1
extends com.corrodinggames.rts.game.units.actions.AbstractBuildAction {
    InGameActivity$1(int n2) {
        super(n2);
    }


    public boolean g() {
        return false;
    }


    public String getDescription() {
        return Localization.a("units.extractor.upgrade.description", new Object[0]);
    }


    public String getLabel() {
        return Localization.a("gui.actions.upgradeT2", new Object[0]);
    }


    public int getResourceCost() {
        return com.corrodinggames.rts.game.units.UnitRegistry.a.c(2);
    }


    public float K() {
        return 6.0E-4f;
    }


    public boolean a(UnitInstance am2, boolean bl) {
        ExperimentalLandFactory g2 = (ExperimentalLandFactory) am2;
        if (g2.b != 1 || g2.a(this.N(), bl) > 0) {
            return false;
        }
        return super.a(am2, bl);
    }

    public com.corrodinggames.rts.game.units.UnitRegistry L() {
        return null;
    }


    public com.corrodinggames.rts.game.units.actions.ActionCategory f() {
        return com.corrodinggames.rts.game.units.actions.ActionCategory.c;
    }


    public /* synthetic */ UnitTypeHandle i() {
        return this.L();
    }
}
