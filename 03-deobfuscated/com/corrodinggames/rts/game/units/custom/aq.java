/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.gameFramework.opengl.ShaderHandles;
import com.corrodinggames.rts.game.units.custom.ak;
import com.corrodinggames.rts.game.units.custom.al;
import com.corrodinggames.rts.game.units.custom.am;
import com.corrodinggames.rts.game.units.custom.an;
import com.corrodinggames.rts.game.units.custom.ao;
import com.corrodinggames.rts.game.units.custom.ap;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$ReturnType;

public abstract class aq
extends ShaderHandles {
    LogicBoolean a;

    aq(LogicBoolean logicBoolean) {
        this.a = logicBoolean;
    }

    static aq a(LogicBoolean logicBoolean) {
        LogicBoolean$ReturnType logicBoolean$ReturnType = logicBoolean.getReturnType();
        if (logicBoolean$ReturnType == LogicBoolean$ReturnType.number) {
            return new an(logicBoolean);
        }
        if (logicBoolean$ReturnType == LogicBoolean$ReturnType.string) {
            return new ao(logicBoolean);
        }
        if (logicBoolean$ReturnType == LogicBoolean$ReturnType.unit) {
            return new ap(logicBoolean);
        }
        if (LogicBoolean$ReturnType.isArrayType(logicBoolean$ReturnType)) {
            return new al(logicBoolean);
        }
        return new am(logicBoolean);
    }
}
