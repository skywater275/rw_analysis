/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.GlobalState;

final class Factory$7
extends AbstractImmediateAction {
    Factory$7(String string) {
        super(string);
    }


    @Override
    public String getLabel() {
        return "Slow motion";
    }


    public String getDescription() {
        GlobalState l2 = GlobalState.B();
        if (l2.bt != 0.1f) {
            return "Slow motion: Off";
        }
        return "Slow motion: On";
    }


    public boolean c(UnitInstance am2, boolean bl) {
        GlobalState l2 = GlobalState.B();
        if (l2.cb.j()) {
            // empty if block
        }
        l2.bt = l2.bt == 1.0f ? 0.1f : 1.0f;
        return false;
    }
}
