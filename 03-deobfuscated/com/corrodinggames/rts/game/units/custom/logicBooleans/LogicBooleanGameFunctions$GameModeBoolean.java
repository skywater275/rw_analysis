/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$LogicBooleanCommonLocking;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class LogicBooleanGameFunctions$GameModeBoolean
extends LogicBoolean$LogicBooleanCommonLocking {
    @LogicBoolean$Parameter
    public boolean nukesEnabled;

    @Override
    public boolean read(UnitType y2) {
        boolean bl = true;
        GlobalState l2 = GlobalState.B();
        if (this.nukesEnabled && l2.O() && l2.bX.ay.i) {
            bl = false;
        }
        return bl;
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        return "GameMode(" + (this.nukesEnabled ? "Nukes enabled" : "Nukes disabled") + ")";
    }
}
