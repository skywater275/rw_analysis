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
import com.corrodinggames.rts.game.units.commands.slots.f;
import com.corrodinggames.rts.gameFramework.steam.Localization;

final class BuildActionSlot$2
extends AbstractBuildAction {
    BuildActionSlot$2(int n2) {
        super(n2);
    }


    public boolean g() {
        return false;
    }


    public String getDescription() {
        return "-Extra attack damage, and range.\n-Large amount of HP\n-Self repair";
    }


    public String getLabel() {
        return Localization.a("gui.actions.upgradeToGunT3", new Object[0]);
    }


    public int getResourceCost() {
        return 11000;
    }

    @Override
    public float K() {
        return 3.0E-4f;
    }


    public boolean a(UnitInstance am2, boolean bl) {
        BuildActionSlot b2 = (BuildActionSlot)am2;
        if (b2.a(com.corrodinggames.rts.game.units.actions.GameAction.i, bl) > 0) {
            return false;
        }
        return super.a(am2, bl);
    }


    public boolean b(UnitInstance am2) {
        BuildActionSlot b2 = (BuildActionSlot)am2;
        return b2.l instanceof f;
    }

    public UnitRegistry L() {
        return null;
    }


    public ActionCategory f() {
        return com.corrodinggames.rts.game.units.actions.ActionCategory.c;
    }


    public void f(UnitInstance am2) {
        BuildActionSlot b2 = (BuildActionSlot)am2;
        b2.b(BuildActionSlot.v);  // 02b b$$2.b.v
        BuildActionSlot.b(b2);
    }

    @Override
    public /* synthetic */ UnitTypeHandle i() {
        return this.L();
    }
}
