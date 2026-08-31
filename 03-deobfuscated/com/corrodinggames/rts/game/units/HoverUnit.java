/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.ActionTargetType;
import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.Factory;
import com.corrodinggames.rts.game.units.UnitCategory;
import com.corrodinggames.rts.gameFramework.ui.InGameUI;

public strictfp class HoverUnit
extends com.corrodinggames.rts.game.units.actions.AbstractImmediateAction {
    boolean a;
    boolean b;

    public HoverUnit(boolean bl, boolean bl2) {
        super("changeTypeFilter" + bl + "d:" + bl2);
        this.a = bl;
        this.b = bl2;
    }


    public boolean b(UnitInstance am2) {
        Factory h2 = Factory.L();
        if (h2 != null) {
            return h2.G == UnitCategory.b;
        }
        return true;
    }


    @Override
    public String getLabel() {
        if (this.b) {
            Factory h2 = Factory.L();
            if (h2 != null) {
                if (h2.F != null) {
                    return h2.F.a();
                }
                return "All types";
            }
            return "Type Filter";
        }
        if (this.a) {
            return "<- Set type";
        }
        return "Set type ->";
    }


    public String d() {
        if (!this.b) {
            if (this.a) {
                return "<-";
            }
            return "->";
        }
        Factory h2 = Factory.L();
        if (h2 == null) {
            return "NA";
        }
        if (h2.F == null) {
            return "All mods";
        }
        return h2.F.a();
    }


    public String getDescription() {
        return "Change filtered type";
    }


    public float l() {
        if (!com.corrodinggames.rts.gameFramework.ui.InGameUI.bP) {
            return 0.8f;
        }
        return 0.5f;
    }


    public int m() {
        if (this.b) {
            return 2;
        }
        return 4;
    }


    public ActionCategory f() {
        if (this.b) {
            return ActionCategory.g;
        }
        return super.f();
    }


    public ActionTargetType e() {
        if (this.b) {
            return ActionTargetType.i;
        }
        return super.e();
    }
}
