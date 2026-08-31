/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.actions;

import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitInstance;

public class ActionFilter {
    public static ActionFilter emptyActionFilter = new ActionFilter();

    public boolean isAvailable(GameAction s2, UnitInstance am2) {
        return true;
    }
}
