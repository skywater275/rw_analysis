/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString$WrappingLogicString;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.network.NetEngine;
import com.corrodinggames.rts.gameFramework.GlobalState;

public class LogicString$DebugPassthrough
extends LogicString$WrappingLogicString {
    public void addMessage(UnitType y2) {
        GlobalState l2 = GlobalState.B();
        if (l2.bv && l2.bl) {
            String string = "";
            if (y2 != null) {
                string = y2.r().i() + "(" + y2.eh + ") ";
            }
            String string2 = string + "DebugPassthrough: " + this.children[0].getMatchFailReasonForPlayer(y2);
            NetEngine.registerRelayServer((String) null, string2);  // 02b: j/ad.a(String,String) 铁证; (String)强转消除重载歧义
        }
    }

    @Override
    public LogicBoolean$ReturnType getReturnType() {
        return this.children[0].getReturnType();
    }

    @Override
    public boolean read(UnitType y2) {
        this.addMessage(y2);
        return this.children[0].read(y2);
    }

    @Override
    public float readNumber(UnitType y2) {
        this.addMessage(y2);
        return this.children[0].readNumber(y2);
    }

    @Override
    public String readString(UnitType y2) {
        this.addMessage(y2);
        return this.children[0].readString(y2);
    }

    @Override
    public UnitInstance readUnit(UnitType y2) {
        this.addMessage(y2);
        return this.children[0].readUnit(y2);
    }
}
