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

public final class LogicBooleanGameFunctions$HasParent
extends LogicBoolean {
    public g _withTag;

    @LogicBoolean.Parameter
    public void withTag(String string) {
        this._withTag = g.c(string);
    }

    @Override
    public boolean read(y y2) {
        boolean bl = false;
        am am2 = y2.dr();
        if (am2 != null) {
            h h2;
            bl = true;
            if (this._withTag != null && !g.a(this._withTag, h2 = am2.de())) {
                bl = false;
            }
        }
        return bl;
    }

    @Override
    public String getMatchFailReasonForPlayer(y y2) {
        return "HasParent";
    }
}
