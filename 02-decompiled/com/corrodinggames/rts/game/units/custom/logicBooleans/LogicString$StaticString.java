/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString;
import com.corrodinggames.rts.game.units.y;

public class LogicString$StaticString
extends LogicString {
    String text;

    public LogicString$StaticString(String string) {
        this.text = string;
    }

    @Override
    public String readString(y y2) {
        return this.text;
    }
}
