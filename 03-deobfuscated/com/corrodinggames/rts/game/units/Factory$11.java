/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.ai.AIStrategy;
import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.gameFramework.GlobalState;

final class Factory$11
extends AbstractImmediateAction {
    Factory$11(String string) {
        super(string);
    }


    public String getDescription() {
        return "AI debug view";
    }


    public String getLabel() {
        GlobalState l2 = GlobalState.B();
        if (!AIStrategy.as) {
            return "AI Debug: Off";
        }
        return "AI Debug: On";
    }
}
