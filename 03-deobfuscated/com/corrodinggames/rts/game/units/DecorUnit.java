/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitTypeHandle;

public class DecorUnit
extends AbstractImmediateAction {
    UnitTypeHandle a;
    int b;

    public String getDescription() {
        return "b_" + this.a.v();
    }

    public String getLabel() {
        return this.a.v();
    }

    public DecorUnit(UnitTypeHandle as2, int n2, Integer n3) {
        super("b_" + as2.v());
        this.b = 1;
        this.a = as2;
        this.b = n2;
    }
}
