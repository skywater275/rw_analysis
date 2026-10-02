/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units;

import com.corrodinggames.rts.game.units.actions.AbstractImmediateAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.Factory;

final class Factory$23
extends AbstractImmediateAction {
    Factory$23(String string) {
        super(string);
    }


    public String getDescription() {
        return "Change selected player's alliance (players with the same letter are allied)";
    }


    public String getLabel() {
        return "Ally:";
    }


    public String getDisplayString() {
        String string = "Ally";
        Factory h2 = Factory.L();
        if (h2 != null) {
            string = "Ally: " + h2.player.h();
        }
        return string;
    }


    public boolean getDescription(UnitInstance am2, boolean bl) {
        return true;
    }
}
