/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.PlayerState;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$LogicBooleanCommonLocking;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.UnitType;

public class LogicBooleanGameFunctions$IsOnTeam
extends LogicBoolean$LogicBooleanCommonLocking {
    int teamId = -99;

    @LogicBoolean$Parameter
    public void team(int n2) {
        if (n2 < -1 || n2 > PlayerState.c) {
            throw new BooleanParseException("Flag id must be between 0-" + PlayerState.c);
        }
        this.teamId = n2;
    }

    @Override
    public void validate(String string, String string2, String string3, LogicBooleanLoader$LogicBooleanContext logicBooleanContext, boolean bl) {
        super.validate(string, string2, string3, logicBooleanContext, bl);
        if (this.teamId == -99) {
            throw new BooleanParseException("Expended teamId argument for function:" + string);
        }
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        String string = "Team";
        string = string + "(id:" + this.teamId + ")";
        return string;
    }

    @Override
    public boolean read(UnitType y2) {
        boolean bl = true;
        if (y2.player.k != this.teamId) {
            bl = false;
        }
        return bl;
    }
}
