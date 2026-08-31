/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.gameFramework.GlobalState;

final class Factory$10
extends AbstractImmediateAction {
    Factory$10(String string) {
        super(string);
    }


    @Override
    public String getLabel() {
        return "Show hidden unit information in tooltips including flags, ammo, tags and resources";
    }


    public String getDescription() {
        GlobalState l2 = GlobalState.B();
        if (!l2.bl) {
            return "Debug: Off";
        }
        return "Debug: On";
    }
}
