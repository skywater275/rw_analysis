/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.y;

public final class LogicBoolean$NotBoolean
extends LogicBoolean {
    LogicBoolean child;

    public LogicBoolean$NotBoolean(LogicBoolean logicBoolean) {
        this.child = logicBoolean;
    }

    @Override
    public boolean read(y y2) {
        return !this.child.read(y2);
    }

    @Override
    public String getMatchFailReasonForPlayer(y y2) {
        return "not(" + this.child.getMatchFailReasonForPlayer(y2) + ")";
    }
}
