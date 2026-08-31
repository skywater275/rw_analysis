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
import com.corrodinggames.rts.game.units.commands.FabricatorUnit;
import com.corrodinggames.rts.gameFramework.steam.Localization;

final class ModDownloader$1
extends com.corrodinggames.rts.game.units.actions.AbstractBuildAction {  // 02b d/v$1: extends a.w (ExperimentalWallUnit 为误映射)
    ModDownloader$1(int n2) {
        super(n2);
    }


    public boolean g() {
        return false;
    }


    public String getDescription() {  // 02b d/v$1.java 对应方法
        return Localization.a("units.supplyDepot.upgrade.description", new Object[0]);
    }


    public String getLabel() {  // 02b d/v$1.java 对应方法
        return Localization.a("units.supplyDepot.upgrade.name", new Object[0]);
    }


    public int getResourceCost() {  // 02b d/v$1.java 对应方法
        return 1000;
    }


    public float K() {
        return 4.0E-4f;
    }


    public boolean a(UnitInstance am2, boolean bl) {
        FabricatorUnit v2 = (FabricatorUnit) am2;  // 02b v$1: (v)var1 (v=FabricatorUnit)
        if (v2.f != 1 || v2.a(this.N(), bl) > 0) {
            return false;
        }
        return super.a(am2, bl);
    }

    public UnitRegistry L() {  // 02b v$1: ar L() (ar=UnitRegistry)
        return null;
    }


    public ActionCategory f() {  // 02b v$1: a.t f() = ActionCategory
        return ActionCategory.c;
    }


    public /* synthetic */ UnitTypeHandle i() {
        return this.L();
    }
}
