/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.ai.AIStrategy;
import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.Factory;
import com.corrodinggames.rts.gameFramework.GlobalState;

final class Factory$22
extends AbstractImmediateAction {
    Factory$22(String string) {
        super(string);
    }


    public String getDescription() {
        return "Freeze high level AI logic (120secs)";
    }


    public String getLabel() {
        return "Freeze AI";
    }


    public String getDisplayString() {
        String string = "Freeze AI";
        GlobalState l2 = GlobalState.B();
        Factory h2 = Factory.L();
        if (h2 != null) {
            int n2 = -1;
            if (h2.player instanceof AIStrategy) {
                AIStrategy a2 = (AIStrategy) h2.player;
                n2 = (int)a2.bG / 60;
            }
            if (n2 > 0) {
                string = string + "(" + n2 + ")";
            }
        }
        return string;
    }


    public boolean getDescription(UnitInstance am2, boolean bl) {
        return am2.player instanceof AIStrategy;
    }
}
