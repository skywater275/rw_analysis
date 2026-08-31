/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.actions;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.gameFramework.PacketBuilder;

import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.ActionTargetType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitRegistry;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.gameFramework.ui.InGameUI;
import com.corrodinggames.rts.gameFramework.steam.Localization;

public class GuardAction
extends GameAction {
    public GuardAction() {
        super("c_8");
    }

    @Override
    public int getLabel(UnitInstance am2, boolean bl) {
        return -1;
    }


    @Override
    public int getResourceCost() {
        return 0;
    }

    public UnitRegistry n() {
        return null;
    }


    public ActionTargetType e() {
        return ActionTargetType.l;  // 02b a/u.l (u 为幻觉)
    }


    public ActionCategory f() {
        return ActionCategory.a;  // 02b a/t.a (t 为幻觉)
    }


    public boolean g() {
        return false;
    }


    public String getDescription() {  // 02b a() = getDescription
        return Localization.a("gui.actions.guardUnit.description", new Object[0]);  // ActionBase 为幻觉
    }

    @Override
    public String getLabel() {
        return Localization.a("gui.actions.guardUnit", new Object[0]);  // ActionBase 为幻觉
    }

    @Override
    public boolean s() {
        return true;
    }

    @Override
    public float l() {
        if (!com.corrodinggames.rts.gameFramework.ui.InGameUI.bP) {
            return 0.6f;
        }
        return 0.5f;
    }


    public boolean h() {
        return true;
    }

    @Override
    public boolean isAlwaysVisible() {
        return true;
    }


    public /* synthetic */ UnitTypeHandle i() {
        return this.n();
    }
}

