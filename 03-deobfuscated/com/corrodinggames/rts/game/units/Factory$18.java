/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.ActionTargetType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;

final class Factory$18
extends GameAction {
    Factory$18(String string) {
        super(string);
    }


    public String getDescription() {
        return "Delete all units at a point";
    }


    public String getLabel() {
        return "Delete units at";
    }

    @Override
    public boolean h_() {
        return false;
    }


    public int getResourceCost() {
        return 0;
    }


    public int getLabel(UnitInstance am2, boolean bl) {
        return -1;
    }

    public UnitRegistry i_() {
        return null;
    }


    public ActionTargetType e() {
        return ActionTargetType.g;
    }


    public ActionCategory f() {
        return ActionCategory.f;
    }


    public boolean g() {
        return false;
    }


    public boolean getDescription(UnitInstance am2, boolean bl) {
        return true;
    }


    public boolean h() {
        return true;
    }

    @Override
    public /* synthetic */ UnitTypeHandle i() {
        return this.i_();
    }
}
