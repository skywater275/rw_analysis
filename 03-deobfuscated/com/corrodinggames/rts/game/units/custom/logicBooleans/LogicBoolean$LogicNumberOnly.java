/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GameUtils;

public abstract class LogicBoolean$LogicNumberOnly
extends LogicBoolean {
    @Override
    public LogicBoolean$ReturnType getReturnType() {
        return LogicBoolean$ReturnType.number;
    }

    @Override
    public boolean read(UnitType y2) {
        return false;
    }

    public abstract String getName();

    @Override
    public abstract float readNumber(UnitType var1);

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        String string = this.getName() + "(" + LogicBoolean$LogicNumberOnly.getAllParametersDebug(this, y2) + ")=" + GameUtils.a(this.readNumber(y2), 3) + "";
        return string;
    }
}
