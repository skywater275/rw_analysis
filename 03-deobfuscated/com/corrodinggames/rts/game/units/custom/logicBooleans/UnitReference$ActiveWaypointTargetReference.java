/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.WeaponAction;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.UnitType;

public class UnitReference$ActiveWaypointTargetReference
extends UnitReference {
    @Override
    public UnitInstance getSingleRaw(UnitType y2) {
        WeaponAction au2 = y2.ar();
        if (au2 == null) {
            return null;
        }
        UnitInstance am2 = au2.l();
        return am2;
    }

    @Override
    public String getClassDebugName() {
        return "ActiveWaypointTarget";
    }
}
