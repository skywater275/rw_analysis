/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$LogicNumberOnly;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GameUtils;

public final class LogicBoolean$StaticValueBoolean
extends LogicBoolean$LogicNumberOnly {
    public static final LogicBoolean$StaticValueBoolean static_0 = new LogicBoolean$StaticValueBoolean(0.0f);
    public static final LogicBoolean$StaticValueBoolean static_neg1 = new LogicBoolean$StaticValueBoolean(-1.0f);
    public static final LogicBoolean$StaticValueBoolean static_1 = new LogicBoolean$StaticValueBoolean(1.0f);
    float staticNumber;

    public static LogicBoolean$StaticValueBoolean getStaticNumber(String string) {
        try {
            float f = Float.parseFloat(string);
            return LogicBoolean$StaticValueBoolean.getStaticNumber(f);
        }
        catch (NumberFormatException numberFormatException) {
            throw new RuntimeException("Error reading number: " + string, numberFormatException);
        }
    }

    public static LogicBoolean$StaticValueBoolean getStaticNumber(float f) {
        if (f == 0.0f) {
            return static_0;
        }
        if (f == 1.0f) {
            return static_1;
        }
        if (f == -1.0f) {
            return static_neg1;
        }
        return new LogicBoolean$StaticValueBoolean(f);
    }

    LogicBoolean$StaticValueBoolean(float f) {
        this.staticNumber = f;
    }

    @Override
    public String getName() {
        return "" + this.staticNumber;
    }

    @Override
    public final float readNumber(UnitType y2) {
        return this.staticNumber;
    }

    public float getStaticValue() {
        return this.staticNumber;
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        return GameUtils.a(this.staticNumber, 3);
    }
}
