/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$StaticBoolean;
import com.corrodinggames.rts.game.units.y;

public final class LogicBoolean$StaticBooleanTrue
extends LogicBoolean$StaticBoolean {
    @Override
    public String getMatchFailReasonForPlayer(y y2) {
        return "true";
    }

    @Override
    public boolean read(y y2) {
        return true;
    }
}
