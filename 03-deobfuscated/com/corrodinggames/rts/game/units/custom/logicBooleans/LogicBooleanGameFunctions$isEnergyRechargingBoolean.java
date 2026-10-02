/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$LogicBooleanCommon;
import com.corrodinggames.rts.game.units.UnitType;

public final class LogicBooleanGameFunctions$isEnergyRechargingBoolean
extends LogicBoolean$LogicBooleanCommon {
    @Override
    public String getName() {
        return "isEnergyRecharging";
    }

    @Override
    public boolean read(UnitType y2) {
        return y2.player();  // 02b: var1.bX() (javap y.bX:()Z 铁证) — UnitType.player() 简化补缺
    }
}
