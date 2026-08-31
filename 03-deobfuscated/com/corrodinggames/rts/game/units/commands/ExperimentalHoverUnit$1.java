/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.commands;

import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.ActionTargetType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.commands.ExperimentalHoverUnit;
import com.corrodinggames.rts.gameFramework.steam.Localization;

final class ExperimentalHoverUnit$1
extends GameAction {
    ExperimentalHoverUnit$1(int n2) {
        super(n2);
    }


    public boolean g() {
        return false;
    }


    public String getDescription() {
        return "";
    }


    public String getLabel() {
        return Localization.a("gui.actions.antiNukeCount", new Object[0]);
    }


    public int getResourceCost() {
        return 0;
    }


    public boolean a(UnitInstance am2, boolean bl) {
        return this.b(am2, false) != 0;
    }


    public int b(UnitInstance am2, boolean bl) {  // 02b c$1 L44-48
        ExperimentalHoverUnit c2 = (ExperimentalHoverUnit) am2;
        return c2.slotIndex;  // 02b c.d (字段保序)
    }

    public UnitRegistry K() {
        return null;
    }


    public ActionTargetType e() {
        return com.corrodinggames.rts.game.units.actions.ActionTargetType.a;
    }


    public ActionCategory f() {
        return com.corrodinggames.rts.game.units.actions.ActionCategory.a;
    }


    public int getLabel(UnitInstance am2, boolean bl) {
        ExperimentalHoverUnit c2 = (ExperimentalHoverUnit)am2;
        return c2.slotIndex;
    }

    @Override
    public /* synthetic */ UnitTypeHandle i() {
        return this.K();
    }
}
