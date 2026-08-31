/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.g;
import com.corrodinggames.rts.game.units.custom.h;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.y;

public class LogicBooleanGameFunctions$TeamTagBoolean
extends LogicBoolean {
    public g includesTag;

    @LogicBoolean.Parameter
    public void includes(String string) {
        this.includesTag = g.c(string);
    }

    @Override
    public String getMatchFailReasonForPlayer(y y2) {
        String string = "Team Tag ";
        if (this.includesTag != null) {
            string = string + " includes " + this.includesTag;
        }
        return string;
    }

    @Override
    public boolean read(y y2) {
        h h2;
        boolean bl = true;
        if (!(this.includesTag == null || (h2 = y2.bX.U()) != null && g.a(this.includesTag, h2))) {
            bl = false;
        }
        return bl;
    }
}
