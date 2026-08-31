/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GameUtils;

public abstract class LogicBoolean$AbstractNumberBoolean
extends LogicBoolean {
    @LogicBoolean$Parameter
    public boolean full;
    @LogicBoolean$Parameter
    public boolean empty;
    @LogicBoolean$Parameter
    public float greaterThan = -1.0f;
    @LogicBoolean$Parameter
    public float lessThan = -1.0f;

    @LogicBoolean$Parameter
    public void equalTo(float f) {
        this.greaterThan = f - 1.0E-4f;
        this.lessThan = f + 1.0E-4f;
    }

    @Override
    public LogicBoolean$ReturnType getReturnType() {
        if (this.greaterThan == -1.0f && this.lessThan == -1.0f && !this.full && !this.empty) {
            return LogicBoolean$ReturnType.number;
        }
        return LogicBoolean$ReturnType.bool;
    }

    @Override
    public void validate(String string, String string2, String string3, LogicBooleanLoader$LogicBooleanContext logicBooleanContext, boolean bl) {
        super.validate(string, string2, string3, logicBooleanContext, bl);
        if (bl && this.greaterThan == -1.0f && this.lessThan == -1.0f && !this.full && !this.empty) {
            throw new BooleanParseException("Expected greaterThan, lessThan, full, and/or empty to be set for function:" + string + " to return a boolean");
        }
    }

    public abstract String getName();

    public abstract float getValue(UnitType var1);

    public abstract float getMaxValue(UnitType var1);

    @Override
    public float readNumber(UnitType y2) {
        return this.getValue(y2);
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        String string = this.getName() + "=" + GameUtils.a(this.getValue(y2), 3) + "";
        if (this.full) {
            string = string + "(full)";
        }
        if (this.empty) {
            string = string + "(empty)";
        }
        if (this.greaterThan != -1.0f) {
            string = string + ">" + GameUtils.a(this.greaterThan, 3);
        }
        if (this.lessThan != -1.0f) {
            string = string + "<" + GameUtils.a(this.lessThan, 3);
        }
        return string;
    }

    @Override
    public boolean read(UnitType y2) {
        float f2 = this.getValue(y2);
        boolean bl = true;
        if (this.full && !(f2 >= this.getMaxValue(y2))) {
            bl = false;
        }
        if (this.empty && !(f2 <= 0.0f)) {
            bl = false;
        }
        if (this.greaterThan != -1.0f && !(f2 > this.greaterThan)) {
            bl = false;
        }
        if (this.lessThan != -1.0f && !(f2 < this.lessThan)) {
            bl = false;
        }
        return bl;
    }
}
