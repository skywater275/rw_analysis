/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.buildings;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.buildings.AttackBehavior;

final class AttackBehavior$1
extends AbstractImmediateAction {
    AttackBehavior$1(int n2) {
        super(n2);
    }


    public String getDescription() {
        return "-Surface unit.";
    }


    public String getLabel() {
        return "Fly";
    }


    public boolean a(UnitInstance am2, boolean bl) {
        return !((AttackBehavior)am2).r;
    }
}
