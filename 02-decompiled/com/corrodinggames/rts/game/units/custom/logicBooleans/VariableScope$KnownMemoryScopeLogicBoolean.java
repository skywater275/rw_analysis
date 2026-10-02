/*
 * Decompiled with CFR 0.152.
 */
package com.corrodinggames.rts.game.units.custom.logicBooleans;

import com.corrodinggames.rts.game.units.custom.l;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.LogicBooleanLoader$LogicBooleanScopeOnly;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$KnownMemoryReadLogicBoolean;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope$VariableDefinition;
import java.util.Locale;

public class VariableScope$KnownMemoryScopeLogicBoolean
extends LogicBooleanLoader$LogicBooleanScopeOnly {
    @Override
    public LogicBoolean parseNextElementInChain(String string, l l2, String string2, boolean bl2, String string3, String string4, LogicBoolean logicBoolean) {
        String string5 = string2.toLowerCase(Locale.ROOT);
        VariableScope.VariableDefinition variableDefinition = l2.r.get(string5);
        if (variableDefinition == null) {
            throw new RuntimeException("Unknown variable:'" + string2 + "' in '" + string4 + "'");
        }
        VariableScope$KnownMemoryReadLogicBoolean variableScope$KnownMemoryReadLogicBoolean = new VariableScope$KnownMemoryReadLogicBoolean(variableDefinition);
        return variableScope$KnownMemoryReadLogicBoolean;
    }
}
