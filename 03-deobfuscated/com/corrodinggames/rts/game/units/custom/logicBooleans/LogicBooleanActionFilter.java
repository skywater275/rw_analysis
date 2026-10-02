/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.custom.actions.base.ActionTrigger;

import com.corrodinggames.rts.game.units.actions.ActionFilter;
import com.corrodinggames.rts.game.units.actions.GameAction;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;

public class LogicBooleanActionFilter
extends ActionFilter {
    LogicBoolean logicBoolean;
    CustomUnitType target;  // 02b L12: j target 铁证

    public LogicBooleanActionFilter(LogicBoolean logicBoolean, CustomUnitType j2) {
        this.logicBoolean = logicBoolean;
    }

    @Override
    public boolean isAvailable(GameAction s2, UnitInstance am2) {
        return this.logicBoolean.read(this.target);
    }
}
