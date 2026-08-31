/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.actions;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.ActionTargetType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.gameFramework.steam.Localization;

public class RepairAction
extends GameAction {
    public RepairAction() {
        super("c_3");
    }


    @Override
    public String getDescription() {
        return Localization.a("gui.actions.repairTarget", new Object[0]);
    }

    @Override
    public String getLabel() {
        return Localization.a("gui.actions.repairTarget", new Object[0]);
    }


    @Override
    public int getResourceCost() {
        return 0;
    }

    @Override
    public int getLabel(UnitInstance am2, boolean bl) {
        return -1;
    }

    public UnitRegistry K() {
        return null;
    }


    public ActionTargetType e() {
        return ActionTargetType.f;
    }

    @Override
    public boolean h_() {
        return true;
    }


    public ActionCategory f() {
        return ActionCategory.f;
    }


    public boolean g() {
        return false;
    }


    public boolean h() {
        return true;
    }


    public /* synthetic */ UnitTypeHandle i() {
        return this.K();
    }
}
