/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom;

import com.corrodinggames.rts.gameFramework.network.SecurityHasher;
import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.aq;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.UnitType;

public class ap
extends aq {
    ap(LogicBoolean logicBoolean) {
        super(logicBoolean);
    }


    String a(UnitType y2) {
        UnitInstance am2 = this.a.readUnit(y2);
        return UnitInstance.f(am2, false);
    }
}
