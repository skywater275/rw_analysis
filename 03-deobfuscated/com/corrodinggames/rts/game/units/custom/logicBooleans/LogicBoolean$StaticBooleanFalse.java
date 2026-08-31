/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticBoolean;
import com.corrodinggames.rts.game.units.UnitType;

public final class LogicBoolean$StaticBooleanFalse
extends LogicBoolean$StaticBoolean {
    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        return "false";
    }

    @Override
    public boolean read(UnitType y2) {
        return false;
    }
}
