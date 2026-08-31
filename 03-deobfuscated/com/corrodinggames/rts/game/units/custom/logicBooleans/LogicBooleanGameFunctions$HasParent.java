/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.UnitType;

public final class LogicBooleanGameFunctions$HasParent
extends LogicBoolean {
    public TeamTag _withTag;

    @LogicBoolean$Parameter
    public void withTag(String string) {
        this._withTag = TeamTag.intern(string);
    }

    @Override
    public boolean read(UnitType y2) {
        boolean bl = false;
        UnitInstance am2 = y2.dr();
        if (am2 != null) {
            UnitConfig h2;
            bl = true;
            if (this._withTag != null && !TeamTag.deserializeTags(this._withTag, h2 = am2.getStatusEffects())) {
                bl = false;
            }
        }
        return bl;
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        return "HasParent";
    }
}
