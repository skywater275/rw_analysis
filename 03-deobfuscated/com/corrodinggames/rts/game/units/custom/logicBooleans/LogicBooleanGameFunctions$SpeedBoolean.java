/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GameUtils;

public class LogicBooleanGameFunctions$SpeedBoolean
extends LogicBoolean {
    @LogicBoolean$Parameter
    public boolean atTopSpeed;

    @Override
    public boolean read(UnitType y2) {
        boolean bl = false;
        float f2 = y2.z() - 0.1f;
        if (y2.bi()) {
            float f3 = GameUtils.a(0.0f, 0.0f, y2.cc, y2.cd);
            if (f3 != 0.0f && f3 > f2 * f2) {
                bl = true;
            }
        } else if (y2.cf != 0.0f && y2.cf > f2) {
            bl = true;
        }
        return bl;
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        return "Speed";
    }
}
