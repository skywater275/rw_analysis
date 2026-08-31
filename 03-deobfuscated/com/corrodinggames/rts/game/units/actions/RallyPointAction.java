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
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.rendering.Texture;

public class RallyPointAction
extends GameAction {
    public RallyPointAction() {
        super("c_1");
    }


    public String getDescription() {
        return Localization.a("gui.actions.setRally.description", new Object[0]);
    }

    @Override
    public String getLabel() {
        return Localization.a("gui.actions.setRally", new Object[0]);
    }


    @Override
    public int getResourceCost() {
        return 0;
    }

    @Override
    public int getLabel(UnitInstance am2, boolean bl) {
        return -1;
    }

    public UnitRegistry n() {
        return null;
    }


    public ActionTargetType e() {
        return ActionTargetType.d;
    }


    public ActionCategory f() {
        return ActionCategory.b;
    }


    public boolean g() {
        return false;
    }


    public Texture j() {
        return GlobalState.B().bS.bj;
    }


    public boolean h() {
        return true;
    }


    public /* synthetic */ UnitTypeHandle i() {
        return this.n();
    }
}
