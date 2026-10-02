/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.UnitTypeHandle;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.custom.ActionBinding;

public class UnitReference$UnitReferenceOrUnitType {
    ActionBinding unitType;
    UnitReference unitReference;

    UnitReference$UnitReferenceOrUnitType(ActionBinding v2) {
        this.unitType = v2;
    }

    UnitReference$UnitReferenceOrUnitType(UnitReference unitReference) {
        this.unitReference = unitReference;
    }

    public UnitInstance getUnitOrSharedUnit(UnitInstance am2) {
        UnitInstance am3;
        if (this.unitType != null) {
            return UnitInstance.c(this.unitType.c());
        }
        if (this.unitReference != null && (am3 = this.unitReference.get(am2)) != null) {
            return am3;
        }
        return null;
    }

    public UnitInstance getUnitReferenceOrNull(UnitInstance am2) {
        UnitInstance am3;
        if (this.unitReference != null && (am3 = this.unitReference.get(am2)) != null) {
            return am3;
        }
        return null;
    }

    public UnitTypeHandle getTypeOrNull(UnitInstance am2) {
        UnitInstance am3;
        if (this.unitType != null) {
            return this.unitType.c();
        }
        if (this.unitReference != null && (am3 = this.unitReference.get(am2)) != null) {
            return am3.r();
        }
        return null;
    }
}
