/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.gameFramework.GlobalState;

final class Factory$8
extends AbstractImmediateAction {
    Factory$8(String string) {
        super(string);
    }


    @Override
    public String getLabel() {
        return "Fast Forward 1-5x";
    }


    public String getDescription() {
        GlobalState l2 = GlobalState.B();
        return "Fast Forward: " + l2.bt;
    }


    public boolean c(UnitInstance am2, boolean bl) {
        GlobalState l2 = GlobalState.B();
        if (l2.cb.j()) {
            // empty if block
        }
        l2.bt = l2.bt == 1.0f ? 2.0f : (l2.bt == 2.0f ? 3.0f : (l2.bt == 3.0f ? 4.0f : (l2.bt == 4.0f ? 5.0f : (l2.bt == 5.0f ? 10.0f : 1.0f))));
        return false;
    }
}
