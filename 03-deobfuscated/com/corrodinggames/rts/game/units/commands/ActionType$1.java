/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.AbstractBuildAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.ExperimentalWaterUnit;
import com.corrodinggames.rts.gameFramework.steam.Localization;

final class ActionType$1
extends com.corrodinggames.rts.game.units.actions.AbstractBuildAction {  // 02b d/f$1: extends a.w (ExperimentalWallUnit 为误映射)
    ActionType$1(int n2) {
        super(n2);
    }


    public boolean g() {
        return false;
    }


    public String getDescription() {  // 02b d/f$1.java 对应方法
        return "-Allows factory to build Tech 2 units";
    }


    public String getLabel() {  // 02b d/f$1.java 对应方法
        return Localization.a("gui.actions.upgradeT2", new Object[0]);
    }


    public int getResourceCost() {  // 02b d/f$1.java 对应方法
        return 1500;
    }


    public float K() {
        return 5.0E-4f;
    }


    public boolean a(UnitInstance am2, boolean bl) {
        ExperimentalWaterUnit f2 = (ExperimentalWaterUnit) am2;  // 02b f$1: (f)var1 (f=ExperimentalWaterUnit)
        if (f2.f || f2.a(this.N(), bl) > 0) {
            return false;
        }
        return super.a(am2, bl);
    }

    public UnitRegistry L() {  // 02b f$1: ar L() (ar=UnitRegistry)
        return null;
    }


    public ActionCategory f() {  // 02b f$1: a.t f() = ActionCategory
        return ActionCategory.c;
    }


    public /* synthetic */ UnitTypeHandle i() {
        return this.L();
    }
}
