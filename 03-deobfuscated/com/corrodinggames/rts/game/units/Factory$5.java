/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.Factory;

final class Factory$5
extends AbstractImmediateAction {
    Factory$5(String string) {
        super(string);
    }


    public String getDescription() {
        return "Freeze full high level logic for all AI forever";
    }


    public String getLabel() {
        return "Freeze AI";
    }


    public String getDisplayString() {
        boolean bl;
        String string = "Freeze AI";
        Factory h2 = Factory.L();
        if (h2 != null && (bl = h2.c)) {
            string = "Unfreeze AIs";
        }
        return string;
    }


    public boolean getDescription(UnitInstance am2, boolean bl) {
        return true;
    }
}
