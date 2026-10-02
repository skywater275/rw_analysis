/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$WrappingLogicString;
import com.corrodinggames.rts.game.units.y;

public class LogicString$Debug
extends LogicString$WrappingLogicString {
    @Override
    public String readString(y y2) {
        return this.children[0].getMatchFailReasonForPlayer(y2);
    }
}
