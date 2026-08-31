/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.ActionTargetType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.UnitActionHelper;
import com.corrodinggames.rts.gameFramework.steam.Localization;

final class PerformanceMonitor$1
extends com.corrodinggames.rts.game.units.actions.GameAction {  // 02b d/q$1: extends a.s (GameAction; AutoRepairCallback 涓鸿鏄犲皠)
    PerformanceMonitor$1(int n2) {
        super(n2);
    }


    public String getDescription() {  // 02b q$1 a() = getDescription
        return Localization.a("gui.actions.launchNuke", new Object[0]);
    }


    public String getLabel() {  // 02b q$1 b() = getLabel
        return Localization.a("gui.actions.launchNuke", new Object[0]);
    }


    public int getResourceCost() {  // 02b q$1 c() = getResourceCost
        return 0;
    }


    public int getLabel(UnitInstance am2, boolean bl) {  // 02b q$1 b(am,boolean) = getLabel(UnitInstance,boolean)
        UnitActionHelper q2 = (UnitActionHelper) am2;  // 02b q$1: (q)var1 (q=UnitActionHelper)
        return q2.c;
    }

    public UnitRegistry K() {  // 02b q$1: ar K() (ar=UnitRegistry)
        return null;
    }


    public ActionTargetType e() {  // 02b q$1: a.u e() = ActionTargetType
        return ActionTargetType.g;
    }


    public ActionCategory f() {  // 02b q$1: a.t f() = ActionCategory
        return ActionCategory.f;
    }


    public boolean g() {
        return false;
    }


    public boolean a(UnitInstance am2, boolean bl) {
        UnitActionHelper q2 = (UnitActionHelper) am2;  // 02b q$1: (q)var1 (q=UnitActionHelper)
        return q2.c > 0;
    }


    public /* synthetic */ UnitTypeHandle i() {
        return this.K();
    }
}

