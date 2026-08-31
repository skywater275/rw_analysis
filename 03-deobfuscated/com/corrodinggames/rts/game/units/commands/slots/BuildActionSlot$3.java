/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands.slots;

import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.AbstractBuildAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.slots.BuildActionSlot;
import com.corrodinggames.rts.gameFramework.steam.Localization;

final class BuildActionSlot$3
extends AbstractBuildAction {
    BuildActionSlot$3(int n2) {
        super(n2);
    }


    public boolean g() {
        return false;
    }


    public String getDescription() {
        return "-Large increase in range";
    }


    public String getLabel() {
        return Localization.a("gui.actions.upgradeToArtillery", new Object[0]);
    }


    public int getResourceCost() {
        return 1600;
    }

    @Override
    public float K() {
        return 4.0E-4f;
    }


    public boolean a(UnitInstance am2, boolean bl) {
        BuildActionSlot b2 = (BuildActionSlot)am2;
        if (b2.M() != 1 || b2.a(com.corrodinggames.rts.game.units.actions.GameAction.i, bl) > 0) {
            return false;
        }
        return super.a(am2, bl);
    }


    public boolean b(UnitInstance am2) {
        BuildActionSlot b2 = (BuildActionSlot)am2;
        return b2.M() == 1;
    }

    public UnitRegistry L() {
        return null;
    }


    public ActionCategory f() {
        return com.corrodinggames.rts.game.units.actions.ActionCategory.c;
    }


    public void f(UnitInstance am2) {
        BuildActionSlot b2 = (BuildActionSlot)am2;
        b2.b(BuildActionSlot.w);  // 02b b$$3.b.w
        BuildActionSlot.c(b2);
    }

    @Override
    public /* synthetic */ UnitTypeHandle i() {
        return this.L();
    }
}
