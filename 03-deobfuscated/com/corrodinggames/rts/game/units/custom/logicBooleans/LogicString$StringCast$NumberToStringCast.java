/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$StringCast;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.GameUtils;

public class LogicString$StringCast$NumberToStringCast
extends LogicString$StringCast {
    @Override
    public String readString(UnitType y2) {
        float f2 = this.child.readNumber(y2);
        return GameUtils.a(f2, 2);
    }
}
