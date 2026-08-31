/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.UnitType;

public class LogicBooleanGameFunctions$OverCliftBoolean
extends LogicBoolean {
    @Override
    public boolean read(UnitType y2) {
        boolean bl = false;
        if (y2.cI()) {  // 02b: y.cI() — UnitType.cI() 简化补缺
            bl = true;
        }
        return bl;
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        return "OverClift";
    }
}
