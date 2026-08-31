/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.actions;

import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.actions.ActionCategory;
import com.corrodinggames.rts.game.units.actions.ActionTargetType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.Factory;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.ui.InGameUI;
import com.corrodinggames.rts.gameFramework.GlobalState;
import com.corrodinggames.rts.gameFramework.GameObject;

public abstract class AbstractCutsceneAction
extends GameAction {
    public AbstractCutsceneAction(String string) {
        super("c__cut_" + string);
        this.g = 0.0f;
    }


    @Override
    public int getLabel(UnitInstance am2, boolean bl) {
        return -1;
    }


    @Override
    public int getResourceCost() {
        return 0;
    }

    @Override
    public UnitTypeHandle i() {
        return null;
    }


    public ActionTargetType e() {
        return ActionTargetType.i;
    }


    public ActionCategory f() {
        return ActionCategory.g;
    }


    public boolean g() {
        return false;
    }

    public UnitType K() {
        UnitType y2 = null;
        for (GameObject w2 : GameObject.er) {
            if (!(w2 instanceof UnitType)) continue;
            UnitType y3 = (UnitType) w2;
            if (!y3.cG) continue;
            y2 = y3;
        }
        return y2;
    }

    public boolean L() {
        GlobalState l2 = GlobalState.B();
        UnitType y2 = this.K();
        if (y2 != null) {
            if (y2 instanceof Factory) {
                return true;
            }
            return l2.bs == y2.player;
        }
        return false;
    }


    public String d() {
        return this.b();
    }

    @Override
    public boolean h_() {
        return false;
    }

    @Override
    public boolean s() {
        return !this.L();
    }

    @Override
    public boolean G() {
        return false;
    }

    @Override
    public float l() {
        if (!com.corrodinggames.rts.gameFramework.ui.InGameUI.bP) {
            return 1.0f;
        }
        return 1.0f;
    }
}
