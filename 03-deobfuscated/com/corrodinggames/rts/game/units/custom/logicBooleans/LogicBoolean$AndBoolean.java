/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$JoinerBoolean;
import com.corrodinggames.rts.game.units.UnitType;

public final class LogicBoolean$AndBoolean
extends LogicBoolean$JoinerBoolean {
    @Override
    public String type() {
        return "and";
    }

    @Override
    public boolean read(UnitType y2) {
        for (LogicBoolean logicBoolean : this.children) {
            if (logicBoolean.read(y2)) continue;
            return false;
        }
        return true;
    }
}
