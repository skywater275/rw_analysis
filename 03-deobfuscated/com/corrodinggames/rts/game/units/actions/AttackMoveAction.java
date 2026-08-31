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

public class AttackMoveAction
extends GameAction {
    public AttackMoveAction() {
        super("c_4");
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
        return ActionTargetType.h;
    }


    public ActionCategory f() {
        return ActionCategory.a;
    }


    public boolean g() {
        return false;
    }


    @Override
    public String getDescription() {
        return "Attack Move";
    }

    @Override
    public String getLabel() {
        return "Attack Move";
    }

    @Override
    public boolean h_() {
        return false;
    }


    public /* synthetic */ UnitTypeHandle i() {
        return this.n();
    }
}
