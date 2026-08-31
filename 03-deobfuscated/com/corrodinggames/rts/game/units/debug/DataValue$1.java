/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.debug;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.debug.FactoryAction5;

final class DataValue$1
extends AbstractImmediateAction {
    DataValue$1(int n2) {
        super(n2);
    }


    @Override
    public String getDescription() {
        return "-Surface unit. Allows it to fire missiles";
    }


    @Override
    public String getLabel() {
        return "Surface";
    }


    public boolean a(UnitInstance am2, boolean bl) {
        return !((FactoryAction5) am2).actionDef;
    }
}
