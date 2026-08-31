/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;

public abstract class LogicBoolean$LogicNumberOnly
extends LogicBoolean {
    @Override
    public LogicBoolean$ReturnType getReturnType() {
        return LogicBoolean$ReturnType.number;
    }

    @Override
    public boolean read(y y2) {
        return false;
    }

    public abstract String getName();

    @Override
    public abstract float readNumber(y var1);

    @Override
    public String getMatchFailReasonForPlayer(y y2) {
        String string = this.getName() + "(" + LogicBoolean$LogicNumberOnly.getAllParametersDebug(this, y2) + ")=" + f.a(this.readNumber(y2), 3) + "";
        return string;
    }
}
