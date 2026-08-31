/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$StringCast;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.f;

public class LogicString$StringCast$NumberToStringCast
extends LogicString$StringCast {
    @Override
    public String readString(y y2) {
        float f2 = this.child.readNumber(y2);
        return f.a(f2, 2);
    }
}
