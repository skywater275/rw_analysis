/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;


import com.corrodinggames.rts.game.units.custom.bo;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$ParameterMapping;
import com.corrodinggames.rts.game.units.custom.logicBooleans.UnitReference;
import com.corrodinggames.rts.game.units.UnitType;

public class LogicBooleanGameFunctions$CompareUnitsBroken
extends LogicBoolean {
    ModUnitRegistry meta;
    UnitReference sameUnitAs;

    @Override
    public void forMeta(ModUnitRegistry l2) {
        if (l2 == null) {
            throw new BooleanParseException("SameUnitAs requires metadata");
        }
        this.meta = l2;
    }

    @LogicBoolean$Parameter
    public void sameUnitAs(String string) {
        try {
            this.sameUnitAs = UnitReference.parseUnitReference(this.meta, string, "", "", null, false);
        }
        catch (bo bo2) {
            throw new BooleanParseException(bo2.getMessage(), bo2);
        }
    }

    @Override
    public void validate(String string, String string2, String string3, LogicBooleanLoader$LogicBooleanContext logicBooleanContext, boolean bl) {
        super.validate(string, string2, string3, logicBooleanContext, bl);
        if (this.sameUnitAs == null) {
            LogicBooleanLoader$ParameterMapping logicBooleanLoader$ParameterMapping = this.getParameters();
            throw new BooleanParseException("Missing required parameters (Possible parameters:" + logicBooleanLoader$ParameterMapping.allParametersString + ")");
        }
    }

    @Override
    public boolean read(UnitType y2) {
        boolean bl = true;
        return bl;
    }

    @Override
    public String getMatchFailReasonForPlayer(UnitType y2) {
        return "SameUnitAs";
    }
}
