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
import com.corrodinggames.rts.game.units.Factory;

final class Factory$1
extends GameAction {
    Factory$1(String string) {
        super(string);
    }


    public boolean getDescription(UnitInstance am2, boolean bl) {
        return !Factory.w();
    }


    public String getDescription() {
        return "Reload all unit data from disk (for modding)";
    }


    public String getLabel() {
        return "Reload units";
    }


    public int getResourceCost() {
        return 0;
    }


    public int getLabel(UnitInstance am2, boolean bl) {
        return -1;
    }

    public UnitRegistry g_() {
        return null;
    }


    public ActionTargetType e() {
        return ActionTargetType.a;
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
    public /* synthetic */ UnitTypeHandle i() {
        return this.g_();
    }
}
