/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.debug;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.debug.FactoryAction5;

final class DataValue$2
extends AbstractImmediateAction {
    DataValue$2(int n2) {
        super(n2);
    }


    @Override
    public String getDescription() {
        return "-Dive unit underwater. Evades most attacks";
    }


    @Override
    public String getLabel() {
        return "Dive";
    }


    public boolean a(UnitInstance am2, boolean bl) {
        return ((FactoryAction5) am2).actionDef;
    }
}
