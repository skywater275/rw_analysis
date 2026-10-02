/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;

import com.corrodinggames.rts.game.units.UnitInstance;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.UnitType;

public class UnitReference$ChainedUnitReference
extends UnitReference {
    UnitReference[] chain;

    UnitReference$ChainedUnitReference(UnitReference[] unitReferenceArray) {
        this.chain = unitReferenceArray;
    }

    @Override
    public UnitInstance getSingleRaw(UnitType y2) {
        UnitReference[] unitReferenceArray = this.chain;
        UnitInstance am2 = y2;
        LogicBoolean.outerUnitParameterContext = y2;
        for (int i = 0; i < unitReferenceArray.length; ++i) {
            if ((am2 = unitReferenceArray[i].get(am2)) != null) continue;
            return null;
        }
        LogicBoolean.outerUnitParameterContext = null;
        return am2;
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        UnitReference[] unitReferenceArray = this.chain;
        UnitInstance am2 = y2;
        String string = "";
        if (am2 instanceof UnitType) {
            LogicBoolean.outerUnitParameterContext = (UnitType)am2;
        }
        string = string + "[";
        for (int i = 0; i < unitReferenceArray.length; ++i) {
            string = string + unitReferenceArray[i].getMatchFailReasonForPlayer(y2);
            if (i != unitReferenceArray.length - 1) {
                string = string + ",";
            }
            if ((am2 = unitReferenceArray[i].get(am2)) != null) continue;
            string = string + "<null>";
            break;
        }
        LogicBoolean.outerUnitParameterContext = null;
        string = string + "]";
        return string;
    }
}
