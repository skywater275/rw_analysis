/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.gameFramework.network.SecurityHasher;
import com.corrodinggames.rts.game.units.custom.aq;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicString;
import com.corrodinggames.rts.game.units.UnitType;

public class al
extends aq {
    al(LogicBoolean logicBoolean) {
        super(logicBoolean);
    }


    String a(UnitType y2) {
        return LogicString.arrayToString(y2, this.a);
    }
}
