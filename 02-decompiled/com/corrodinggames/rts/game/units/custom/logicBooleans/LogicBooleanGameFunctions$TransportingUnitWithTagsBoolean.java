/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.utility.m;

public class LogicBooleanGameFunctions$TransportingUnitWithTagsBoolean
extends LogicBoolean {
    public g includesTag;

    @LogicBoolean.Parameter
    public void includes(String string) {
        this.includesTag = g.c(string);
    }

    @Override
    public String getMatchFailReasonForPlayer(y y2) {
        String string = "TransportingUnitWithTags ";
        if (this.includesTag != null) {
            string = string + " includes " + this.includesTag;
        }
        return string;
    }

    @Override
    public boolean read(y y2) {
        m m2;
        boolean bl = false;
        if (this.includesTag != null && (m2 = y2.bz()) != null) {
            Object[] objectArray = m2.a();
            for (int i = 0; i < m2.a; ++i) {
                am am2 = (am)objectArray[i];
                h h2 = am2.de();
                if (h2 == null || !g.a(this.includesTag, h2)) continue;
                bl = true;
            }
        }
        return bl;
    }
}
