/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GameInput;

import com.corrodinggames.rts.game.units.MovementTypeEnum;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$LogicBooleanCommonLocking;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class LogicBooleanGameFunctions$OverPassableTileBoolean
extends LogicBoolean$LogicBooleanCommonLocking {
    GameInput movementType = null;  // 02b: units.ao=MovementType (03 未创建) 简化 TODO

    @LogicBoolean$Parameter
    public void type(String string) {
        this.movementType = null;  // 02b: ao.a(String,String) — MovementType 03 未创建, 简化 TODO
    }

    @Override
    public boolean read(UnitType y2) {
        boolean bl = false;
        GlobalState l2 = GlobalState.B();
        if (!com.corrodinggames.rts.gameFramework.utility.PathfindingUtils.a(y2.eo, y2.ep, this.movementType)) {
            bl = true;
        }
        return bl;
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        return "OverLand";
    }
}
