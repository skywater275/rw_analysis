/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.GlobalState;

final class Factory$13
extends AbstractImmediateAction {
    Factory$13(String string) {
        super(string);
    }


    public String getDescription() {
        return "For debugging autoTriggers. When enabled will log a message when any auto triggers fire on any selected units";
    }


    public String getLabel() {
        GlobalState l2 = GlobalState.B();
        if (!l2.bn) {
            return "Trigger Debug: Off";
        }
        return "Trigger Debug: On";
    }


    public boolean getLabel(UnitInstance am2) {
        GlobalState l2 = GlobalState.B();
        return l2.bl;
    }
}
