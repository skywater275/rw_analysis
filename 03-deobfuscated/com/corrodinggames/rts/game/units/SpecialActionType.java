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
import com.corrodinggames.rts.game.units.UnitCategory;
import com.corrodinggames.rts.game.units.UnitActionEnum;

class SpecialActionType
extends com.corrodinggames.rts.game.units.actions.GameAction {
    UnitActionEnum a;

    public SpecialActionType(UnitActionEnum r2) {
        super("SetTerrainType" + r2.ordinal());
        this.a = r2;
    }


    public boolean b(UnitInstance am2) {
        Factory h2 = Factory.L();
        if (h2 != null) {
            return h2.G == UnitCategory.c;
        }
        return true;
    }


    @Override
    public String getLabel() {
        return "Set terrain type to: " + this.a.name();
    }


    @Override
    public String getDescription() {
        return "Set " + this.a.name();
    }


    public boolean h_() {
        return false;
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
        return ActionTargetType.g;
    }


    public ActionCategory f() {
        return ActionCategory.f;
    }


    public boolean g() {
        return false;
    }


    public boolean a(UnitInstance am2, boolean bl) {
        return true;
    }


    public boolean h() {
        return true;
    }


    public boolean o() {
        return true;
    }


    public boolean a(float f2, float f3) {
        return true;
    }


    public boolean p() {
        return true;
    }


    public /* synthetic */ UnitTypeHandle i() {
        return this.n();
    }
}
