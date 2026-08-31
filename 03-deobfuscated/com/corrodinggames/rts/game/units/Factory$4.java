/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.GlobalState;

final class Factory$4
extends AbstractImmediateAction {
    Factory$4(String string) {
        super(string);
    }


    @Override
    public String getDescription() {
        String string = "Hide interface till the screen is clicked/pressed";
        if (GlobalState.av()) {
            string = string + "\n-Enable mouse capture to also hide the mouse";
        }
        return string;
    }


    @Override
    public String getLabel() {
        return "Hide interface";
    }


    public boolean c(UnitInstance am2, boolean bl) {
        GlobalState l2 = GlobalState.B();
        l2.cU = true;
        return false;
    }
}
