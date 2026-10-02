/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.custom.k;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.y;

public class UnitReference$EventSourceReference
extends UnitReference {
    @Override
    public am getSingleRaw(y y2) {
        k k2 = LogicBoolean.activeEvent;
        if (k2 == null) {
            return null;
        }
        return k2.c;
    }

    @Override
    public String getClassDebugName() {
        return "EventSource";
    }
}
