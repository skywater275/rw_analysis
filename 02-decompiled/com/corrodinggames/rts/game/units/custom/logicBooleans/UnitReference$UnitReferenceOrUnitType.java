/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.am;
import com.corrodinggames.rts.game.units.as;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.custom.v;

public class UnitReference$UnitReferenceOrUnitType {
    v unitType;
    UnitReference unitReference;

    UnitReference$UnitReferenceOrUnitType(v v2) {
        this.unitType = v2;
    }

    UnitReference$UnitReferenceOrUnitType(UnitReference unitReference) {
        this.unitReference = unitReference;
    }

    public am getUnitOrSharedUnit(am am2) {
        am am3;
        if (this.unitType != null) {
            return am.c(this.unitType.c());
        }
        if (this.unitReference != null && (am3 = this.unitReference.get(am2)) != null) {
            return am3;
        }
        return null;
    }

    public am getUnitReferenceOrNull(am am2) {
        am am3;
        if (this.unitReference != null && (am3 = this.unitReference.get(am2)) != null) {
            return am3;
        }
        return null;
    }

    public as getTypeOrNull(am am2) {
        am am3;
        if (this.unitType != null) {
            return this.unitType.c();
        }
        if (this.unitReference != null && (am3 = this.unitReference.get(am2)) != null) {
            return am3.r();
        }
        return null;
    }
}
