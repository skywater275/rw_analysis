/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.a.b;
import com.corrodinggames.rts.game.units.a.s;
import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.j;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;

public class LogicBooleanActionFilter
extends b {
    LogicBoolean logicBoolean;
    j target;

    public LogicBooleanActionFilter(LogicBoolean logicBoolean, j j2) {
        this.logicBoolean = logicBoolean;
    }

    @Override
    public boolean isAvailable(s s2, am am2) {
        return this.logicBoolean.read(this.target);
    }
}
