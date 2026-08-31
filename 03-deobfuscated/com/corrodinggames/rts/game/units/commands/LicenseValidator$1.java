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
import com.corrodinggames.rts.game.units.commands.ExperimentalSubUnit;
import com.corrodinggames.rts.gameFramework.steam.Localization;

final class LicenseValidator$1
extends com.corrodinggames.rts.game.units.actions.AbstractBuildAction {  // 02b d/p$1: extends a.w (ExperimentalWallUnit 涓鸿鏄犲皠)
    LicenseValidator$1(int n2) {
        super(n2);
    }


    public boolean g() {
        return false;
    }


    public String getDescription() {  // 02b d/p$1.java 对应方法
        return Localization.a("units.laserDefence.upgrade.description", new Object[0]);
    }


    public String getLabel() {  // 02b d/p$1.java 对应方法
        return Localization.a("units.laserDefence.upgrade.name", new Object[0]);
    }


    public int getResourceCost() {  // 02b d/p$1.java 对应方法
        return UnitRegistry.y.c(2);
    }


    public float K() {
        return 3.0E-4f;
    }


    public boolean a(UnitInstance am2, boolean bl) {
        ExperimentalSubUnit p2 = (ExperimentalSubUnit) am2;  // 02b p$1: (p)var1 (p=ExperimentalSubUnit)
        if (p2.d || p2.a(this.N(), bl) > 0) {
            return false;
        }
        return super.a(am2, bl);
    }


    public boolean b(UnitInstance am2) {
        ExperimentalSubUnit p2 = (ExperimentalSubUnit) am2;  // 02b p$1: (p)var1 (p=ExperimentalSubUnit)
        return !p2.d;
    }

    public UnitRegistry L() {  // 02b p$1: ar L() (ar=UnitRegistry)
        return null;
    }


    public ActionCategory f() {  // 02b p$1: a.t f() = ActionCategory
        return ActionCategory.c;
    }


    public /* synthetic */ UnitTypeHandle i() {
        return this.L();
    }
}

