/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.y;
import com.corrodinggames.rts.gameFramework.l;

public class LogicBooleanGameFunctions$IsGameFrameBoolean
extends LogicBoolean {
    @LogicBoolean.Parameter
    public int mod;
    @LogicBoolean.Parameter
    public int equalTo;
    @LogicBoolean.Parameter
    public boolean offset;

    @LogicBoolean.Parameter
    public void mod(int n) {
        this.mod = n;
    }

    @Override
    public boolean read(y y2) {
        l l2 = l.B();
        boolean bl = this.mod >= 0 ? (this.offset ? ((long)l2.bx + y2.eh) % (long)this.mod == (long)this.equalTo : l2.bx % this.mod == this.equalTo) : (this.offset ? (long)l2.bx + y2.eh == (long)this.equalTo : l2.bx == this.equalTo);
        return bl;
    }

    @Override
    public String getMatchFailReasonForPlayer(y y2) {
        return "IsGameFrame(mod=" + this.mod + ")";
    }
}
