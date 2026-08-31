/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.actions;

import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.actions.ActionTargetType;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.commands.BuilderUnit;
import com.corrodinggames.rts.game.units.commands.CarrierUnit;

public abstract class AbstractBuildAction
extends GameAction {
    public AbstractBuildAction(int n2) {
        super(n2);
    }

    public AbstractBuildAction(String string) {
        super(string);
    }

    @Override
    public int getLabel(UnitInstance am2, boolean bl) {
        if (!(am2 instanceof CarrierUnit)) {
            return 99;
        }
        return ((CarrierUnit) am2).a(this.N(), bl);
    }

    @Override
    public float p(UnitInstance am2) {
        if (!(am2 instanceof CarrierUnit)) {
            return -1.0f;
        }
        CarrierUnit l2 = (CarrierUnit) am2;
        BuilderUnit j2 = l2.dw();
        if (j2 == null) {
            return -1.0f;
        }
        if (!this.d(j2.j)) {
            return -1.0f;
        }
        float f2 = j2.buildProgress;
        if (f2 < 0.0f) {
            return 0.0f;
        }
        if (f2 > 1.0f) {
            return 1.0f;
        }
        return f2;
    }

    public float K() {
        return 0.01f;
    }

    @Override
    public boolean u() {
        return true;
    }


    public ActionTargetType e() {
        return ActionTargetType.c;
    }
}
