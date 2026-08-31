/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.WeaponMount;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.UnitType;

public class UnitReference$EventSourceReference
extends UnitReference {
    @Override
    public UnitInstance getSingleRaw(UnitType y2) {
        WeaponMount k2 = LogicBoolean.activeEvent;
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
