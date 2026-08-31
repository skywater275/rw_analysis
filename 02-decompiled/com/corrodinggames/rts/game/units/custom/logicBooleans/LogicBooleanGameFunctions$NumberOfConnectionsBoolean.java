/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.c.a;
import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$AbstractNumberBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean$Parameter;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanContext;
import com.corrodinggames.rts.game.units.y;

public final class LogicBooleanGameFunctions$NumberOfConnectionsBoolean
extends LogicBoolean$AbstractNumberBoolean {
    l meta;
    a connectionMetadata;

    @Override
    public void forMeta(l l2) {
        if (l2 == null) {
            throw new BooleanParseException("NumberOfConnectionsBoolean requires metadata");
        }
        this.meta = l2;
    }

    @LogicBoolean.Parameter
    public void name(String string) {
        this.connectionMetadata = this.meta.l(string);
        if (this.connectionMetadata == null) {
            throw new BooleanParseException("Could not find connection type with name: " + string);
        }
    }

    @Override
    public void validate(String string, String string2, String string3, LogicBooleanLoader.LogicBooleanContext logicBooleanContext, boolean bl) {
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
    public float getValue(y y2) {
        return y2.dI.a(this.connectionMetadata);
    }

    @Override
    public float getMaxValue(y y2) {
        return 2.14748365E9f;
    }
}
