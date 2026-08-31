/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.UnitType;

public class LogicBooleanGameFunctions$EventTagsBoolean
extends LogicBoolean {
    public TeamTag includesTag;

    @LogicBoolean$Parameter
    public void includes(String string) {
        this.includesTag = TeamTag.intern(string);
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        String string = "EventTag";
        if (this.includesTag != null) {
            string = string + " includes " + this.includesTag;
        }
        return string;
    }

    @Override
    public boolean read(UnitType y2) {
        boolean bl = true;
        if (this.includesTag != null) {
            UnitConfig h2 = null;
            if (LogicBoolean.activeEvent != null) {
                h2 = LogicBoolean.activeEvent.d;
            }
            if (h2 == null || !TeamTag.a(this.includesTag, h2)) {
                bl = false;
            }
        }
        return bl;
    }
}
