/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.GlobalState;

final class Factory$14
extends AbstractImmediateAction {
    Factory$14(String string) {
        super(string);
    }


    public String getDescription() {
        return "Clear save history";
    }


    public String getLabel() {
        GlobalState l2 = GlobalState.B();
        return "Clear history";
    }


    public boolean getLabel(UnitInstance am2) {
        GlobalState l2 = GlobalState.B();
        return l2.bl;
    }
}
