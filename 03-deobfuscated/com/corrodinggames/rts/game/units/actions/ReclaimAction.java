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
import com.corrodinggames.rts.gameFramework.ui.InGameUI;
import com.corrodinggames.rts.gameFramework.steam.Localization;

public class ReclaimAction
extends GameAction {
    boolean a;

    public ReclaimAction(boolean bl) {
        super("c_2");
        this.a = bl;
    }

    @Override
    public String getDescription() {
        if (!this.a) {
            return com.corrodinggames.rts.gameFramework.steam.Localization.a("gui.actions.reclaimBuildingTarget.description", new Object[0]);
        }
        return com.corrodinggames.rts.gameFramework.steam.Localization.a("gui.actions.reclaimTarget.description", new Object[0]);
    }

    @Override
    public String getLabel() {
        if (!this.a) {
            return com.corrodinggames.rts.gameFramework.steam.Localization.a("gui.actions.reclaimBuildingTarget", new Object[0]);
        }
        return com.corrodinggames.rts.gameFramework.steam.Localization.a("gui.actions.reclaimTarget", new Object[0]);
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
        return ActionTargetType.e;
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

    @Override
    public boolean o(UnitInstance am2) {
        if (am2 == null) {
            return true;
        }
        if (!this.a) {
            return am2.isFactoryBuilding();
        }
        return true;
    }

    @Override
    public float l() {
        if (!com.corrodinggames.rts.gameFramework.ui.InGameUI.bP) {
            return 0.6f;
        }
        return 1.0f;
    }


    public /* synthetic */ UnitTypeHandle i() {
        return this.K();
    }
}
