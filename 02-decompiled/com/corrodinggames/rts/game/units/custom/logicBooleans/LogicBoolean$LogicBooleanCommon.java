/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.y;

public abstract class LogicBoolean$LogicBooleanCommon
extends LogicBoolean {
    public abstract String getName();

    @Override
    public String getMatchFailReasonForPlayer(y y2) {
        String string = this.getName() + "=" + (this.read(y2) ? "true" : "false") + "";
        return string;
    }
}
