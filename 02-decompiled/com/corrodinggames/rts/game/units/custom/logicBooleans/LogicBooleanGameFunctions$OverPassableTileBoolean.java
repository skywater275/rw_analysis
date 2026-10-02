/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.ao;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$LogicBooleanCommonLocking;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.l;

public class LogicBooleanGameFunctions$OverPassableTileBoolean
extends LogicBoolean$LogicBooleanCommonLocking {
    ao movementType = ao.b;

    @LogicBoolean.Parameter
    public void type(String string) {
        this.movementType = ao.a(string, "isOverPassableTile()");
    }

    @Override
    public boolean read(y y2) {
        boolean bl = false;
        l l2 = l.B();
        if (!com.corrodinggames.rts.gameFramework.utility.y.a(y2.eo, y2.ep, this.movementType)) {
            bl = true;
        }
        return bl;
    }

    @Override
    public String getMatchFailReasonForPlayer(y y2) {
        return "OverLand";
    }
}
