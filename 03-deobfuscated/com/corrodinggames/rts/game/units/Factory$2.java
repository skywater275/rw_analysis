/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.Factory$2$1;
import com.corrodinggames.rts.gameFramework.GlobalState;

final class Factory$2
extends AbstractImmediateAction {
    Factory$2(String string) {
        super(string);
    }


    public String getDescription() {
        return "Start recording a replay to file";
    }


    public String getLabel() {
        return "Start Recording";
    }


    public String getDisplayString() {
        String string = "Start Recording";
        GlobalState l2 = GlobalState.B();
        boolean bl = l2.cb.k();
        string = !bl ? "Start Recording" : "Stop Recording";
        return string;
    }


    public boolean getDescription(UnitInstance am2, boolean bl) {
        GlobalState l2 = GlobalState.B();
        boolean bl2 = l2.cb.j();
        return !bl2;
    }


    public boolean getDescription(UnitInstance am2) {
        GlobalState l2 = GlobalState.B();
        boolean bl = l2.cb.k();
        return bl;
    }


    public boolean getResourceCost(UnitInstance am2, boolean bl) {
        GlobalState l2 = GlobalState.B();
        GlobalState.e("Start recording clicked");
        if (l2.cb.j()) {
            GlobalState.e("Already in a replay");
            return false;
        }
        l2.a(new Factory$2$1(this));
        return false;
    }
}
