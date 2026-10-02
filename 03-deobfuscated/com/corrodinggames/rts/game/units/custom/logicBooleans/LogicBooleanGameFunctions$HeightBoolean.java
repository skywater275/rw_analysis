/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.UnitType;

public class LogicBooleanGameFunctions$HeightBoolean
extends LogicBoolean {
    @LogicBoolean$Parameter
    public boolean underwater;
    @LogicBoolean$Parameter
    public boolean ground;
    @LogicBoolean$Parameter
    public boolean flying;

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        String string = "";
        if (this.underwater) {
            string = string + "underwater";
        }
        if (this.ground) {
            string = string + "ground";
        }
        if (this.flying) {
            string = string + "flying";
        }
        if (string.equals("")) {
            string = string + "height";
        }
        return string;
    }

    @Override
    public boolean read(UnitType y2) {
        boolean bl = false;
        if (this.underwater && y2.eq < -2.0f) {
            bl = true;
        }
        if (this.ground && y2.eq > -2.0f && y2.eq < 5.0f) {
            bl = true;
        }
        if (this.flying && y2.eq > 5.0f) {
            bl = true;
        }
        return bl;
    }
}
