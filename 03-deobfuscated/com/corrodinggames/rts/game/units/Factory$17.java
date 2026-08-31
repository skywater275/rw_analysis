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

final class Factory$17
extends GameAction {
    Factory$17(String string) {
        super(string);
    }


    public String getDescription() {
        return "Clones units at point x50";
    }


    public String getLabel() {
        return "Unit Clone";
    }


    public int getResourceCost() {
        return 0;
    }


    public int getLabel(UnitInstance am2, boolean bl) {
        return -1;
    }

    public UnitRegistry k() {
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
        return this.k();
    }
}
