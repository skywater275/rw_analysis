/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.buildings;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.buildings.AttackBehavior;
import com.corrodinggames.rts.game.units.UnitType;

final class AttackBehavior$2
extends AbstractImmediateAction {
    AttackBehavior$2(int n2) {
        super(n2);
    }


    public String getDescription() {
        return "-Dive unit underwater.";
    }


    public String getLabel() {
        return "Dive";
    }


    public boolean a(UnitInstance am2, boolean bl) {
        return ((AttackBehavior)am2).r && ((com.corrodinggames.rts.game.units.UnitType)am2).cJ();
    }
}
