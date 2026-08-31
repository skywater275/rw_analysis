/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.ModUnitRegistry;
import com.corrodinggames.rts.game.units.custom.logicBooleans.BooleanParseException;

import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanGameFunctions$ResourceCountBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanScopeOnly;

public class LogicBooleanGameFunctions$ResourceScope
extends LogicBooleanLoader$LogicBooleanScopeOnly {

    public LogicBoolean parseNextElementInChain(String string, ModUnitRegistry l2, String string2, boolean bl, String string3, String string4, LogicBoolean logicBoolean) {
        String string5 = string2;
        com.corrodinggames.rts.game.units.custom.effects.LogicBoolean a2 = l2.j(string5);  // l.j→effects 铁证
        if (a2 == null) {
            throw new BooleanParseException("'" + string3 + "': Could not find resource: '" + string5 + "'");
        }
        LogicBooleanGameFunctions$ResourceCountBoolean logicBooleanGameFunctions$ResourceCountBoolean = new LogicBooleanGameFunctions$ResourceCountBoolean();
        logicBooleanGameFunctions$ResourceCountBoolean.meta = l2;
        logicBooleanGameFunctions$ResourceCountBoolean.type = a2;
        return logicBooleanGameFunctions$ResourceCountBoolean;
    }
}
