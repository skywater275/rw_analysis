/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;

import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.AbstractBuildAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.UnitActionHelper;
import com.corrodinggames.rts.gameFramework.steam.Localization;

final class UnitActionHelper$2
extends AbstractBuildAction {
    UnitActionHelper$2(int n2) {
        super(n2);
    }


    public boolean g() {
        return false;
    }


    public String getDescription() {  // 02b d/q$2.java 对应方法
        return Localization.a("gui.actions.buildNuke.description", new Object[0]);
    }


    public String getLabel() {  // 02b d/q$2.java 对应方法
        return Localization.a("gui.actions.buildNuke", new Object[0]);
    }


    public int getResourceCost() {  // 02b d/q$2.java 对应方法
        return 11000;
    }

    @Override
    public float K() {
        return 3.0E-4f;
    }


    public boolean a(UnitInstance am2, boolean bl) {
        UnitActionHelper q2 = (UnitActionHelper) am2;  // 02b q$2: (q)var1
        float f2 = q2.c + q2.a(this.N(), bl);
        if (f2 >= 4.0f) {
            return false;
        }
        return super.a(am2, bl);
    }

    public UnitRegistry L() {
        return null;
    }


    public ActionCategory f() {  // 02b q$2: a.t f() = ActionCategory
        return ActionCategory.d;  // 02b: a.t.d
    }

    @Override
    public /* synthetic */ UnitTypeHandle i() {
        return this.L();
    }
}
