/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.UnitType;


import com.corrodinggames.rts.game.units.custom.animation.UnitTrait;
import com.corrodinggames.rts.game.units.custom.TeamTag;
import com.corrodinggames.rts.game.units.custom.UnitConfig;
import com.corrodinggames.rts.game.units.custom.CustomUnitType;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.UnitType;
import com.corrodinggames.rts.gameFramework.utility.am;

public final class LogicBooleanGameFunctions$NumberOfAttachedUnitsBoolean
extends LogicBoolean$AbstractNumberBoolean {
    public TeamTag _withTag;
    short attachmentId = (short)-1;
    ModUnitRegistry meta;

    @Override
    public void forMeta(ModUnitRegistry l2) {
        if (l2 == null) {
            throw new am("NumberOfAttachedUnitsBoolean requires metadata");
        }
        this.meta = l2;
    }

    @Override
    public void validate(String string, String string2, String string3, LogicBooleanLoader$LogicBooleanContext logicBooleanContext, boolean bl) {
        super.validate(string, string2, string3, logicBooleanContext, bl);
        if (logicBooleanContext != null && logicBooleanContext != LogicBooleanLoader.defaultContextReader && this.attachmentId != -1) {
            throw new BooleanParseException("Function:" + string + " only supports use with 'self.' when using 'slot'");
        }
    }

    @LogicBoolean$Parameter
    public void withTag(String string) {
        this._withTag = TeamTag.intern(string);
    }

    @LogicBoolean$Parameter
    public void slot(String string) {
        UnitTrait n2 = this.meta.i(string);
        if (n2 == null) {
            throw new am("No attachment slot with name: " + string + " found");
        }
        this.attachmentId = n2.a();
    }

    @Override
    public String getName() {
        String string = "";
        if (this._withTag != null) {
            string = string + "tag=" + this._withTag;
        }
        if (this.attachmentId != -1) {
            string = string + " attachmentId=" + this.attachmentId;
        }
        return "NumberOfAttachedUnits(" + string + ")";
    }

    @Override
    public float getValue(UnitType y2) {
        if (!(y2 instanceof CustomUnitType)) {
            return 0.0f;
        }
        CustomUnitType j2 = (CustomUnitType) y2;
        if (j2.C == null) {
            return 0.0f;
        }
        int n2 = 0;
        Object[] objectArray = j2.C.a();
        for (int i2 = j2.C.a - 1; i2 >= 0; --i2) {
            UnitConfig h2;
            UnitType y3 = (UnitType)objectArray[i2];
            if (y3 == null || this.attachmentId != -1 && i2 != this.attachmentId || this._withTag != null && !TeamTag.deserializeTags(this._withTag, h2 = y3.de())) continue;
            ++n2;
        }
        return n2;
    }

    @Override
    public float getMaxValue(UnitType y2) {
        return 2.14748365E9f;
    }
}
