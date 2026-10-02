/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;
import com.corrodinggames.rts.game.units.UnitType;


import com.corrodinggames.rts.game.units.custom.conditions.a;
import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.UnitType;

public final class LogicBooleanGameFunctions$NumberOfConnectionsBoolean
extends LogicBoolean$AbstractNumberBoolean {
    ModUnitRegistry meta;
    Object connectionMetadata;  // 02b: custom.c.a (03 未映射, 简化 TODO)

    @Override
    public void forMeta(ModUnitRegistry l2) {
        if (l2 == null) {
            throw new BooleanParseException("NumberOfConnectionsBoolean requires metadata");
        }
        this.meta = l2;
    }

    @LogicBoolean$Parameter
    public void name(String string) {
        this.connectionMetadata = this.meta.l(string);  // javap l.l(String)→custom.c.a
        if (this.connectionMetadata == null) {
            throw new BooleanParseException("Could not find connection type with name: " + string);
        }
    }

    @Override
    public void validate(String string, String string2, String string3, LogicBooleanLoader$LogicBooleanContext logicBooleanContext, boolean bl) {
        super.validate(string, string2, string3, logicBooleanContext, bl);
        if (logicBooleanContext != null && logicBooleanContext != LogicBooleanLoader.defaultContextReader) {
            throw new BooleanParseException("Function:" + string + " only supports use with 'self.'");
        }
        if (this.connectionMetadata == null) {
            throw new BooleanParseException("requires connection name");
        }
    }

    @Override
    public String getName() {
        return "NumberOfConnections";
    }

    @Override
    public float getValue(UnitType y2) {
        return 0.0f;  // 02b: y.dI 无字节码铁证, 简化 TODO
    }

    @Override
    public float getMaxValue(UnitType y2) {
        return 2.14748365E9f;
    }
}
