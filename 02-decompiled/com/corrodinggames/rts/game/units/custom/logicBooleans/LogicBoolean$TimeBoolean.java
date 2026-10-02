/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;
import com.corrodinggames.rts.gameFramework.l;

public abstract class LogicBoolean$TimeBoolean
extends LogicBoolean {
    @LogicBoolean.Parameter
    public float laterThanSeconds = -1.0f;
    @LogicBoolean.Parameter
    public float withinSeconds = -1.0f;

    public abstract String getName();

    public abstract int getTime(y var1);

    @Override
    public LogicBoolean$ReturnType getReturnType() {
        if (this.laterThanSeconds == -1.0f && this.withinSeconds == -1.0f) {
            return LogicBoolean$ReturnType.number;
        }
        return LogicBoolean$ReturnType.bool;
    }

    @Override
    public void validate(String string, String string2, String string3, LogicBooleanLoader.LogicBooleanContext logicBooleanContext, boolean bl) {
        super.validate(string, string2, string3, logicBooleanContext, bl);
        if (bl && this.laterThanSeconds == -1.0f && this.withinSeconds == -1.0f) {
            throw new BooleanParseException("Expended laterThanSeconds, or withinSeconds argument for function:" + string + " to return a boolean");
        }
    }

    @Override
    public String getMatchFailReasonForPlayer(y y2) {
        l l2 = l.B();
        String string = this.getName() + "=" + this.msToSecondsString(l2.by - this.getTime(y2));
        if (this.laterThanSeconds != -1.0f) {
            string = string + ">" + this.msToSecondsString(this.laterThanSeconds * 1000.0f);
        }
        if (this.withinSeconds != -1.0f) {
            string = string + "<" + this.msToSecondsString(this.withinSeconds * 1000.0f);
        }
        return string;
    }

    private String msToSecondsString(float f2) {
        return f.g(f2 / 1000.0f) + "s";
    }

    @Override
    public float readNumber(y y2) {
        int n2 = this.getTime(y2);
        l l2 = l.B();
        return (float)(l2.by - n2) * 0.001f;
    }

    @Override
    public boolean read(y y2) {
        int n2 = this.getTime(y2);
        boolean bl = true;
        l l2 = l.B();
        if (this.withinSeconds > 0.0f && (float)l2.by - this.withinSeconds * 1000.0f > (float)n2) {
            bl = false;
        }
        if (this.laterThanSeconds > 0.0f && (float)l2.by - this.laterThanSeconds * 1000.0f < (float)n2) {
            bl = false;
        }
        return bl;
    }
}
