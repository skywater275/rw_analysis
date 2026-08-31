/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.ActionTargetType;
import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.Factory;
import com.corrodinggames.rts.gameFramework.ui.InGameUI;
import com.corrodinggames.rts.gameFramework.GlobalState;

public strictfp class SubmarineUnit
extends com.corrodinggames.rts.game.units.actions.AbstractImmediateAction {
    boolean a;
    boolean b;

    public SubmarineUnit(boolean bl, boolean bl2) {
        super("changeUnitTab" + bl + "d:" + bl2);
        this.a = bl;
        this.b = bl2;
    }


    @Override
    public String getLabel() {
        return this.d();
    }


    public String d() {
        Factory h2 = Factory.L();
        if (h2 == null) {
            return "<NULL>";
        }
        if (this.b) {
            return h2.G.a();
        }
        String string = "";
        if (this.a) {
            string = string + "<- ";
        }
        if (!this.a) {
            string = string + " ->";
        }
        return string;
    }

    public void n() {
        Factory h2 = Factory.L();
        if (h2 == null) {
            GlobalState.b("Editor not active");
            return;
        }
        if (this.b) {
            return;
        }
        h2.G = h2.G.a(this.a);
    }


    public String getDescription() {
        return "Change unit tab in editor";
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
