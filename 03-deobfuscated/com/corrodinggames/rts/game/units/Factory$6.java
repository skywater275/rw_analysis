/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.GlobalState;

final class Factory$6
extends AbstractImmediateAction {
    Factory$6(String string) {
        super(string);
    }


    @Override
    public String getLabel() {
        return "Pause Game";
    }


    public String getDescription() {
        GlobalState l2 = GlobalState.B();
        if (l2.bt != 0.0f) {
            return "Pause: Off";
        }
        return "Pause: On";
    }


    public boolean c(UnitInstance am2, boolean bl) {
        GlobalState l2 = GlobalState.B();
        if (l2.cb.j()) {
            // empty if block
        }
        l2.bt = l2.bt != 0.0f ? 0.0f : 1.0f;
        return false;
    }
}
