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
import com.corrodinggames.rts.gameFramework.utility.CustomArrayList;

public class LogicBooleanGameFunctions$TransportingUnitWithTagsBoolean
extends LogicBoolean {
    public TeamTag includesTag;

    @LogicBoolean$Parameter
    public void includes(String string) {
        this.includesTag = TeamTag.intern(string);
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        String string = "TransportingUnitWithTags ";
        if (this.includesTag != null) {
            string = string + " includes " + this.includesTag;
        }
        return string;
    }

    @Override
    public boolean read(UnitType y2) {
        CustomArrayList m2;
        boolean bl = false;
        if (this.includesTag != null && (m2 = y2.bz()) != null) {
            Object[] objectArray = m2.a();
            for (int i = 0; i < m2.a; ++i) {
                UnitInstance am2 = (UnitInstance) objectArray[i];
                com.corrodinggames.rts.game.units.custom.UnitConfig h2 = am2.getStatusEffects();
                if (h2 == null || !TeamTag.a(this.includesTag, h2)) continue;
                bl = true;
            }
        }
        return bl;
    }
}
