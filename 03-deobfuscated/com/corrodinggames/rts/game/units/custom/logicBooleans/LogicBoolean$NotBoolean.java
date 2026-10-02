/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.UnitType;

public final class LogicBoolean$NotBoolean
extends LogicBoolean {
    LogicBoolean child;

    public LogicBoolean$NotBoolean(LogicBoolean logicBoolean) {
        this.child = logicBoolean;
    }

    @Override
    public boolean read(UnitType y2) {
        return !this.child.read(y2);
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        return "not(" + this.child.getMatchFailReasonForPlayer(y2) + ")";
    }
}
