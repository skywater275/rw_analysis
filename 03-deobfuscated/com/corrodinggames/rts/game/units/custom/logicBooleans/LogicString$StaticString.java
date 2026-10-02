/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString;
import com.corrodinggames.rts.game.units.UnitType;

public class LogicString$StaticString
extends LogicString {
    String text;

    public LogicString$StaticString(String string) {
        this.text = string;
    }

    @Override
    public String readString(UnitType y2) {
        return this.text;
    }
}
