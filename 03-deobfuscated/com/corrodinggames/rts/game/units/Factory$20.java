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

final class Factory$20
extends GameAction {
    Factory$20(String string) {
        super(string);
    }


    public String getDescription() {
        return "Finish all unit queues at";
    }


    public String getLabel() {
        return "Finish queue at";
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

    public UnitRegistry k_() {
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
        return this.k_();
    }
}
