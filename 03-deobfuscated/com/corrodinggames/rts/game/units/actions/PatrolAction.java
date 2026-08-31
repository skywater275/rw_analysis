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

public class PatrolAction
extends GameAction {
    public PatrolAction() {
        super("c_9");
    }

    @Override
    public int getLabel(UnitInstance am2, boolean bl) {
        return -1;
    }


    @Override
    public int getResourceCost() {
        return 0;
    }

    public UnitRegistry n() {
        return null;
    }


    public ActionTargetType e() {
        return ActionTargetType.m;
    }


    public ActionCategory f() {
        return ActionCategory.a;
    }


    public boolean g() {
        return false;
    }


    @Override
    public String getDescription() {
        return Localization.a("gui.actions.patrol.description", new Object[0]);
    }

    @Override
    public String getLabel() {
        return Localization.a("gui.actions.patrol", new Object[0]);
    }

    @Override
    public boolean s() {
        return true;
    }

    @Override
    public float l() {
        if (!com.corrodinggames.rts.gameFramework.ui.InGameUI.bP) {
            return 0.6f;
        }
        return 0.5f;
    }


    public boolean h() {
        return true;
    }

    @Override
    public boolean isAlwaysVisible() {
        return true;
    }


    public /* synthetic */ UnitTypeHandle i() {
        return this.n();
    }
}
