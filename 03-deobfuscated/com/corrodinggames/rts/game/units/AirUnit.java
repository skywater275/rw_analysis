/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.ActionTargetType;
import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.ui.InGameUI;
import com.corrodinggames.rts.gameFramework.GlobalState;

public strictfp class AirUnit
extends com.corrodinggames.rts.game.units.actions.AbstractImmediateAction {
    boolean a;
    boolean b;

    public AirUnit(boolean bl, boolean bl2) {
        super("changeTeam" + bl + "d:" + bl2);
        this.a = bl;
        this.b = bl2;
    }


    @Override
    public String getLabel() {
        if (this.b) {
            return "Selected player";
        }
        if (this.a) {
            return "<- Set player";
        }
        return "Set player ->";
    }


    public String d() {
        if (!this.b) {
            if (this.a) {
                return "<-";
            }
            return "->";
        }
        GlobalState l2 = GlobalState.B();
        PlayerState n2 = null;
        java.util.Iterator iterator = l2.bS.bZ.iterator();
        while (iterator.hasNext()) {
            UnitInstance am2 = (UnitInstance) iterator.next();
            if (!(am2 instanceof UnitType)) continue;
            UnitType y2 = (UnitType) am2;
            if (!y2.cG || !l2.bS.m(y2)) continue;
            n2 = y2.player;
        }
        String object = "";
        if (n2 != null) {
            object = (String)object + "Team - " + (n2.k + 1) + "";
        }
        return object;
    }


    public String getDescription() {
        return "Change targeted player for editor";
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
