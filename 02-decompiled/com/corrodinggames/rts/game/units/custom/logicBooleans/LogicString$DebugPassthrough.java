/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$WrappingLogicString;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.j.ad;
import com.corrodinggames.rts.gameFramework.l;

public class LogicString$DebugPassthrough
extends LogicString$WrappingLogicString {
    public void addMessage(y y2) {
        l l2 = l.B();
        if (l2.bv && l2.bl) {
            String string = "";
            if (y2 != null) {
                string = y2.r().i() + "(" + y2.eh + ") ";
            }
            String string2 = string + "DebugPassthrough: " + this.children[0].getMatchFailReasonForPlayer(y2);
            ad.a(null, string2);
        }
    }

    @Override
    public LogicBoolean.ReturnType getReturnType() {
        return this.children[0].getReturnType();
    }

    @Override
    public boolean read(y y2) {
        this.addMessage(y2);
        return this.children[0].read(y2);
    }

    @Override
    public float readNumber(y y2) {
        this.addMessage(y2);
        return this.children[0].readNumber(y2);
    }

    @Override
    public String readString(y y2) {
        this.addMessage(y2);
        return this.children[0].readString(y2);
    }

    @Override
    public am readUnit(y y2) {
        this.addMessage(y2);
        return this.children[0].readUnit(y2);
    }
}
